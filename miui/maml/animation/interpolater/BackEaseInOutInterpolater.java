// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class BackEaseInOutInterpolater
    implements Interpolator
{

    public BackEaseInOutInterpolater()
    {
        mFactor = 1.70158F;
    }

    public BackEaseInOutInterpolater(float f)
    {
        mFactor = f;
    }

    public float getInterpolation(float f)
    {
        float f1 = mFactor;
        f *= 2.0F;
        if(f < 1.0F)
        {
            double d = f * f;
            double d2 = (double)f1 * 1.5249999999999999D;
            f1 = (float)d2;
            return (float)(d * ((d2 + 1.0D) * (double)f - (double)f1) * 0.5D);
        } else
        {
            f -= 2.0F;
            double d3 = f * f;
            double d1 = (double)f1 * 1.5249999999999999D;
            f1 = (float)d1;
            return (float)((d3 * ((d1 + 1.0D) * (double)f + (double)f1) + 2D) * 0.5D);
        }
    }

    private final float mFactor;
}
