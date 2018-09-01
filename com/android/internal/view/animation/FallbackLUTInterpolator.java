// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.animation;

import android.animation.TimeInterpolator;
import android.view.Choreographer;

// Referenced classes of package com.android.internal.view.animation:
//            NativeInterpolatorFactory, NativeInterpolatorFactoryHelper

public class FallbackLUTInterpolator
    implements NativeInterpolatorFactory, TimeInterpolator
{

    public FallbackLUTInterpolator(TimeInterpolator timeinterpolator, long l)
    {
        mSourceInterpolator = timeinterpolator;
        mLut = createLUT(timeinterpolator, l);
    }

    private static float[] createLUT(TimeInterpolator timeinterpolator, long l)
    {
        int i = (int)(Choreographer.getInstance().getFrameIntervalNanos() / 0xf4240L);
        int k = Math.min(Math.max(2, (int)Math.ceil((double)l / (double)i)), 300);
        float af[] = new float[k];
        float f = k - 1;
        for(int j = 0; j < k; j++)
            af[j] = timeinterpolator.getInterpolation((float)j / f);

        return af;
    }

    public static long createNativeInterpolator(TimeInterpolator timeinterpolator, long l)
    {
        return NativeInterpolatorFactoryHelper.createLutInterpolator(createLUT(timeinterpolator, l));
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createLutInterpolator(mLut);
    }

    public float getInterpolation(float f)
    {
        return mSourceInterpolator.getInterpolation(f);
    }

    private static final int MAX_SAMPLE_POINTS = 300;
    private final float mLut[];
    private TimeInterpolator mSourceInterpolator;
}
