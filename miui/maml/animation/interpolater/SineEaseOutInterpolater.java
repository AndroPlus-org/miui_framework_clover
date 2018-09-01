// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class SineEaseOutInterpolater
    implements Interpolator
{

    public SineEaseOutInterpolater()
    {
    }

    public float getInterpolation(float f)
    {
        return (float)Math.sin((double)f * 1.5707963267948966D);
    }
}
