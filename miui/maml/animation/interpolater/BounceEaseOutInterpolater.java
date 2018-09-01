// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class BounceEaseOutInterpolater
    implements Interpolator
{

    public BounceEaseOutInterpolater()
    {
    }

    public static float getInterpolationImp(float f)
    {
        if((double)f < 0.36363636363636365D)
            return 7.5625F * f * f;
        if((double)f < 0.72727272727272729D)
        {
            double d = (double)f - 0.54545454545454541D;
            return (float)(d * 7.5625D * (double)(float)d + 0.75D);
        }
        if((double)f < 0.90909090909090906D)
        {
            double d1 = (double)f - 0.81818181818181823D;
            return (float)(d1 * 7.5625D * (double)(float)d1 + 0.9375D);
        } else
        {
            double d2 = (double)f - 0.95454545454545459D;
            return (float)(d2 * 7.5625D * (double)(float)d2 + 0.984375D);
        }
    }

    public float getInterpolation(float f)
    {
        return getInterpolationImp(f);
    }
}
