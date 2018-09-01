// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.shader;

import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import miui.maml.data.Variables;
import miui.maml.util.ColorParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class ShaderElement
{
    private final class GradientStop
    {

        public int getColor()
        {
            return mColorParser.getColor();
        }

        public float getPosition()
        {
            return (float)mPositionExp.evaluate();
        }

        public static final String TAG_NAME = "GradientStop";
        private ColorParser mColorParser;
        private Expression mPositionExp;
        final ShaderElement this$0;

        public GradientStop(Element element, ScreenElementRoot screenelementroot)
        {
            this$0 = ShaderElement.this;
            super();
            mColorParser = ColorParser.fromElement(mRoot.getVariables(), element);
            mPositionExp = Expression.build(mRoot.getVariables(), element.getAttribute("position"));
            if(mPositionExp == null)
                Log.e("GradientStop", "lost position attribute.");
        }
    }

    protected final class GradientStops
    {

        public void add(GradientStop gradientstop)
        {
            mGradientStopArr.add(gradientstop);
        }

        public int[] getColors()
        {
            return mColors;
        }

        public float[] getPositions()
        {
            return mPositions;
        }

        public void init()
        {
            mColors = new int[size()];
            mPositions = new float[size()];
        }

        public int size()
        {
            return mGradientStopArr.size();
        }

        public void update()
        {
            boolean flag = false;
            for(int i = 0; i < size(); i++)
            {
                int j = ((GradientStop)mGradientStopArr.get(i)).getColor();
                if(j != mColors[i])
                    flag = true;
                mColors[i] = j;
                float f = ((GradientStop)mGradientStopArr.get(i)).getPosition();
                if(f != mPositions[i])
                    flag = true;
                mPositions[i] = f;
            }

            if(flag)
                onGradientStopsChanged();
        }

        private int mColors[];
        protected ArrayList mGradientStopArr;
        private float mPositions[];
        final ShaderElement this$0;

        protected GradientStops()
        {
            this$0 = ShaderElement.this;
            super();
            mGradientStopArr = new ArrayList();
        }
    }


    public ShaderElement(Element element, ScreenElementRoot screenelementroot)
    {
        mShaderMatrix = new Matrix();
        mGradientStops = new GradientStops();
        mRoot = screenelementroot;
        Variables variables = getVariables();
        mXExp = Expression.build(variables, element.getAttribute("x"));
        mYExp = Expression.build(variables, element.getAttribute("y"));
        mTileMode = getTileMode(element.getAttribute("tile"));
        if(!element.getTagName().equalsIgnoreCase("BitmapShader"))
            loadGradientStops(element, screenelementroot);
    }

    public static android.graphics.Shader.TileMode getTileMode(String s)
    {
        if(TextUtils.isEmpty(s))
            return android.graphics.Shader.TileMode.CLAMP;
        if(s.equalsIgnoreCase("mirror"))
            return android.graphics.Shader.TileMode.MIRROR;
        if(s.equalsIgnoreCase("repeat"))
            return android.graphics.Shader.TileMode.REPEAT;
        else
            return android.graphics.Shader.TileMode.CLAMP;
    }

    private void loadGradientStops(Element element, ScreenElementRoot screenelementroot)
    {
        NodeList nodelist = element.getElementsByTagName("GradientStop");
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            element = (Element)nodelist.item(i);
            mGradientStops.add(new GradientStop(element, screenelementroot));
        }

        if(mGradientStops.size() <= 0)
        {
            Log.e("ShaderElement", "lost gradient stop.");
            return;
        } else
        {
            mGradientStops.init();
            return;
        }
    }

    public Shader getShader()
    {
        return mShader;
    }

    protected Variables getVariables()
    {
        return mRoot.getVariables();
    }

    public float getX()
    {
        double d;
        if(mXExp != null)
            d = mXExp.evaluate();
        else
            d = 0.0D;
        return (float)((double)mRoot.getScale() * d);
    }

    public float getY()
    {
        double d;
        if(mYExp != null)
            d = mYExp.evaluate();
        else
            d = 0.0D;
        return (float)((double)mRoot.getScale() * d);
    }

    public abstract void onGradientStopsChanged();

    public void updateShader()
    {
        mGradientStops.update();
        if(updateShaderMatrix())
            mShader.setLocalMatrix(mShaderMatrix);
    }

    public abstract boolean updateShaderMatrix();

    private static final String LOG_TAG = "ShaderElement";
    protected GradientStops mGradientStops;
    protected ScreenElementRoot mRoot;
    protected Shader mShader;
    protected Matrix mShaderMatrix;
    protected android.graphics.Shader.TileMode mTileMode;
    protected float mX;
    protected Expression mXExp;
    protected float mY;
    protected Expression mYExp;
}
