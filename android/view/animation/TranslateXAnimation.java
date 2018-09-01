// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.graphics.Matrix;

// Referenced classes of package android.view.animation:
//            TranslateAnimation, Transformation

public class TranslateXAnimation extends TranslateAnimation
{

    public TranslateXAnimation(float f, float f1)
    {
        super(f, f1, 0.0F, 0.0F);
        mTmpValues = new float[9];
    }

    public TranslateXAnimation(int i, float f, int j, float f1)
    {
        super(i, f, j, f1, 0, 0.0F, 0, 0.0F);
        mTmpValues = new float[9];
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        transformation.getMatrix().getValues(mTmpValues);
        float f1 = mFromXDelta;
        float f2 = mToXDelta;
        float f3 = mFromXDelta;
        transformation.getMatrix().setTranslate(f1 + (f2 - f3) * f, mTmpValues[5]);
    }

    float mTmpValues[];
}
