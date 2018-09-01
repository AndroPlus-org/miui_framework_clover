// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class BackEaseOutInterpolater
    implements Interpolator
{

    public BackEaseOutInterpolater()
    {
        mFactor = 1.70158F;
    }

    public BackEaseOutInterpolater(float f)
    {
        mFactor = f;
    }

    public float getInterpolation(float f)
    {
        f--;
        return f * f * ((mFactor + 1.0F) * f + mFactor) + 1.0F;
    }

    private final float mFactor;
}
