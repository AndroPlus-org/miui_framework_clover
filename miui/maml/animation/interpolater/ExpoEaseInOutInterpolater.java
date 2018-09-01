// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class ExpoEaseInOutInterpolater
    implements Interpolator
{

    public ExpoEaseInOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        if(f == 0.0F)
            return 0.0F;
        if(f == 1.0F)
            return 1.0F;
        f *= 2.0F;
        if(f < 1.0F)
            return (float)Math.pow(2D, (f - 1.0F) * 10F) * 0.5F;
        else
            return (float)(-Math.pow(2D, -10F * (f - 1.0F)) + 2D) * 0.5F;
    }
}
