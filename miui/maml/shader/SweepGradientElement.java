// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.shader;

import android.graphics.Matrix;
import android.graphics.SweepGradient;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.shader:
//            ShaderElement

public class SweepGradientElement extends ShaderElement
{

    public SweepGradientElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mAngleExp = Expression.build(getVariables(), element.getAttribute("rotation"));
        mGradientStops.update();
    }

    private final float getAngle()
    {
        float f;
        if(mAngleExp != null)
            f = (float)mAngleExp.evaluate();
        else
            f = 0.0F;
        return f;
    }

    public void onGradientStopsChanged()
    {
        mX = 0.0F;
        mY = 0.0F;
        mAngle = 0.0F;
        mShader = new SweepGradient(0.0F, 0.0F, mGradientStops.getColors(), mGradientStops.getPositions());
    }

    public boolean updateShaderMatrix()
    {
        float f = getX();
        float f1 = getY();
        for(float f2 = getAngle(); f != mX || f1 != mY || f2 != mAngle;)
        {
            mX = f;
            mY = f1;
            mAngle = f2;
            mShaderMatrix.reset();
            mShaderMatrix.preTranslate(-f, -f1);
            mShaderMatrix.setRotate(f2);
            mShaderMatrix.postTranslate(f, f1);
            return true;
        }

        return false;
    }

    public static final String TAG_NAME = "SweepGradient";
    private float mAngle;
    private Expression mAngleExp;
}
