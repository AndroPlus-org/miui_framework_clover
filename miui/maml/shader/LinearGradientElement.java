// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.shader;

import android.graphics.LinearGradient;
import android.graphics.Matrix;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.shader:
//            ShaderElement

public class LinearGradientElement extends ShaderElement
{

    public LinearGradientElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mEndXExp = Expression.build(screenelementroot.getVariables(), element.getAttribute("x1"));
        mEndYExp = Expression.build(screenelementroot.getVariables(), element.getAttribute("y1"));
        mGradientStops.update();
    }

    private final float getEndX()
    {
        double d;
        if(mEndXExp != null)
            d = mEndXExp.evaluate();
        else
            d = 0.0D;
        return (float)((double)mRoot.getScale() * d);
    }

    private final float getEndY()
    {
        double d;
        if(mEndYExp != null)
            d = mEndYExp.evaluate();
        else
            d = 0.0D;
        return (float)((double)mRoot.getScale() * d);
    }

    public void onGradientStopsChanged()
    {
        mX = 0.0F;
        mY = 0.0F;
        mEndX = 1.0F;
        mEndY = 1.0F;
        mShader = new LinearGradient(0.0F, 0.0F, 1.0F, 1.0F, mGradientStops.getColors(), mGradientStops.getPositions(), mTileMode);
    }

    public boolean updateShaderMatrix()
    {
        float f = getX();
        float f1 = getY();
        float f2 = getEndX();
        for(float f3 = getEndY(); f != mX || f1 != mY || f2 != mEndX || f3 != mEndY;)
        {
            mX = f;
            mY = f1;
            mEndX = f2;
            mEndY = f3;
            mShaderMatrix.reset();
            mShaderMatrix.setPolyToPoly(new float[] {
                0.0F, 0.0F, 1.0F, 1.0F
            }, 0, new float[] {
                f, f1, f2, f3
            }, 0, 2);
            return true;
        }

        return false;
    }

    public static final String TAG_NAME = "LinearGradient";
    private float mEndX;
    private Expression mEndXExp;
    private float mEndY;
    private Expression mEndYExp;
}
