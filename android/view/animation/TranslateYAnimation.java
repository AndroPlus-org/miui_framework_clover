// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.graphics.Matrix;

// Referenced classes of package android.view.animation:
//            TranslateAnimation, Transformation

public class TranslateYAnimation extends TranslateAnimation
{

    public TranslateYAnimation(float f, float f1)
    {
        super(0.0F, 0.0F, f, f1);
        mTmpValues = new float[9];
    }

    public TranslateYAnimation(int i, float f, int j, float f1)
    {
        super(0, 0.0F, 0, 0.0F, i, f, j, f1);
        mTmpValues = new float[9];
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        transformation.getMatrix().getValues(mTmpValues);
        float f1 = mFromYDelta;
        float f2 = mToYDelta;
        float f3 = mFromYDelta;
        transformation.getMatrix().setTranslate(mTmpValues[2], f1 + (f2 - f3) * f);
    }

    float mTmpValues[];
}
