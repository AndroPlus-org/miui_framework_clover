// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class CircEaseOutInterpolater
    implements Interpolator
{

    public CircEaseOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        f--;
        return (float)Math.sqrt(1.0F - f * f);
    }
}
