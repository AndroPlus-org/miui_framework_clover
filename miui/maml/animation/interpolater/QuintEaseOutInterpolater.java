// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class QuintEaseOutInterpolater
    implements Interpolator
{

    public QuintEaseOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        f--;
        return f * f * f * f * f + 1.0F;
    }
}
