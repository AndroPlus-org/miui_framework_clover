// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class QuadEaseInOutInterpolater
    implements Interpolator
{

    public QuadEaseInOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        f *= 2.0F;
        if(f < 1.0F)
        {
            return 0.5F * f * f;
        } else
        {
            f--;
            return ((f - 2.0F) * f - 1.0F) * -0.5F;
        }
    }
}
