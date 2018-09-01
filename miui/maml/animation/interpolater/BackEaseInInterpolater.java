// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.view.animation.Interpolator;

public class BackEaseInInterpolater
    implements Interpolator
{

    public BackEaseInInterpolater()
    {
        mFactor = 1.70158F;
    }

    public BackEaseInInterpolater(float f)
    {
        mFactor = f;
    }

    public float getInterpolation(float f)
    {
        return f * f * ((mFactor + 1.0F) * f - mFactor);
    }

    private final float mFactor;
}
