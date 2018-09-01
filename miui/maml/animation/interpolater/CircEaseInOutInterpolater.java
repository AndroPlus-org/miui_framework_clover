// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class CircEaseInOutInterpolater
    implements Interpolator
{

    public CircEaseInOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        f *= 2.0F;
        if(f < 1.0F)
        {
            return (float)(Math.sqrt(1.0F - f * f) - 1.0D) * -0.5F;
        } else
        {
            f -= 2.0F;
            return (float)(Math.sqrt(1.0F - f * f) + 1.0D) * 0.5F;
        }
    }
}
