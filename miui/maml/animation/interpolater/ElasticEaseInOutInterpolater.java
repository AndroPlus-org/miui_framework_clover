// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class ElasticEaseInOutInterpolater
    implements Interpolator
{

    public ElasticEaseInOutInterpolater()
    {
        mPriod = 0.45F;
        mAmplitude = 0.0F;
    }

    public ElasticEaseInOutInterpolater(float f, float f1)
    {
        mPriod = f;
        mAmplitude = f1;
    }

    public float getInterpolation(float f)
    {
        float f1 = mAmplitude;
        if(f == 0.0F)
            return 0.0F;
        float f2 = f / 0.5F;
        if(f2 == 2.0F)
            return 1.0F;
        if(f1 < 1.0F)
        {
            f1 = 1.0F;
            f = mPriod / 4F;
        } else
        {
            f = (float)(((double)mPriod / 6.2831853071795862D) * Math.asin(1.0F / f1));
        }
        if(f2 < 1.0F)
        {
            double d = f1;
            f1 = f2 - 1.0F;
            return (float)(d * Math.pow(2D, 10F * f1) * Math.sin(((double)(f1 - f) * 6.2831853071795862D) / (double)mPriod)) * -0.5F;
        } else
        {
            double d1 = f1;
            f1 = f2 - 1.0F;
            return (float)(d1 * Math.pow(2D, -10F * f1) * Math.sin(((double)(f1 - f) * 6.2831853071795862D) / (double)mPriod) * 0.5D + 1.0D);
        }
    }

    private float mAmplitude;
    private float mPriod;
}
