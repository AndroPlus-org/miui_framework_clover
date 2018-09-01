// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.shader;

import android.graphics.Matrix;
import android.graphics.RadialGradient;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.shader:
//            ShaderElement

public class RadialGradientElement extends ShaderElement
{

    public RadialGradientElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mRxExp = Expression.build(getVariables(), element.getAttribute("rX"));
        mRyExp = Expression.build(getVariables(), element.getAttribute("rY"));
        mGradientStops.update();
    }

    private final float getRx()
    {
        double d;
        if(mRxExp != null)
            d = mRxExp.evaluate();
        else
            d = 0.0D;
        return (float)((double)mRoot.getScale() * d);
    }

    private final float getRy()
    {
        double d;
        if(mRyExp != null)
            d = mRyExp.evaluate();
        else
            d = 0.0D;
        return (float)((double)mRoot.getScale() * d);
    }

    public void onGradientStopsChanged()
    {
        mX = 0.0F;
        mY = 0.0F;
        mRx = 1.0F;
        mRy = 1.0F;
        mShader = new RadialGradient(0.0F, 0.0F, 1.0F, mGradientStops.getColors(), mGradientStops.getPositions(), mTileMode);
    }

    public boolean updateShaderMatrix()
    {
        float f = getX();
        float f1 = getY();
        float f2 = getRx();
        for(float f3 = getRy(); f != mX || f1 != mY || f2 != mRx || f3 != mRy;)
        {
            mX = f;
            mY = f1;
            mRx = f2;
            mRy = f3;
            mShaderMatrix.reset();
            mShaderMatrix.preTranslate(-f, -f1);
            mShaderMatrix.setScale(f2, f3);
            mShaderMatrix.postTranslate(f, f1);
            return true;
        }

        return false;
    }

    public static final String TAG_NAME = "RadialGradient";
    private float mRx;
    private Expression mRxExp;
    private float mRy;
    private Expression mRyExp;
}
