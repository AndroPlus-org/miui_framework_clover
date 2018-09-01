// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class SineEaseInOutInterpolater
    implements Interpolator
{

    public SineEaseInOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        return (float)(Math.cos((double)f * 3.1415926535897931D) - 1.0D) * -0.5F;
    }
}
