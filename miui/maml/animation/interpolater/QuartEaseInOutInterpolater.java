// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class QuartEaseInOutInterpolater
    implements Interpolator
{

    public QuartEaseInOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        f *= 2.0F;
        if(f < 1.0F)
        {
            return 0.5F * f * f * f * f;
        } else
        {
            f -= 2.0F;
            return (f * f * f * f - 2.0F) * -0.5F;
        }
    }
}
