// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class ElasticEaseOutInterpolater
    implements Interpolator
{

    public ElasticEaseOutInterpolater()
    {
        mPriod = 0.3F;
        mAmplitude = 0.0F;
    }

    public ElasticEaseOutInterpolater(float f, float f1)
    {
        mPriod = f;
        mAmplitude = f1;
    }

    public float getInterpolation(float f)
    {
        float f1 = mAmplitude;
        if(f == 0.0F)
            return 0.0F;
        if(f == 1.0F)
            return 1.0F;
        float f2;
        if(f1 < 1.0F)
        {
            f1 = 1.0F;
            f2 = mPriod / 4F;
        } else
        {
            f2 = (float)(((double)mPriod / 6.2831853071795862D) * Math.asin(1.0F / f1));
        }
        return (float)((double)f1 * Math.pow(2D, -10F * f) * Math.sin(((double)(f - f2) * 6.2831853071795862D) / (double)mPriod) + 1.0D);
    }

    private float mAmplitude;
    private float mPriod;
}
