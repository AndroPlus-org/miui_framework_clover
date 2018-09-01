// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioGainConfig

public class AudioGain
{

    AudioGain(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2)
    {
        mIndex = i;
        mMode = j;
        mChannelMask = k;
        mMinValue = l;
        mMaxValue = i1;
        mDefaultValue = j1;
        mStepValue = k1;
        mRampDurationMinMs = l1;
        mRampDurationMaxMs = i2;
    }

    public AudioGainConfig buildConfig(int i, int j, int ai[], int k)
    {
        return new AudioGainConfig(mIndex, this, i, j, ai, k);
    }

    public int channelMask()
    {
        return mChannelMask;
    }

    public int defaultValue()
    {
        return mDefaultValue;
    }

    public int maxValue()
    {
        return mMaxValue;
    }

    public int minValue()
    {
        return mMinValue;
    }

    public int mode()
    {
        return mMode;
    }

    public int rampDurationMaxMs()
    {
        return mRampDurationMaxMs;
    }

    public int rampDurationMinMs()
    {
        return mRampDurationMinMs;
    }

    public int stepValue()
    {
        return mStepValue;
    }

    public static final int MODE_CHANNELS = 2;
    public static final int MODE_JOINT = 1;
    public static final int MODE_RAMP = 4;
    private final int mChannelMask;
    private final int mDefaultValue;
    private final int mIndex;
    private final int mMaxValue;
    private final int mMinValue;
    private final int mMode;
    private final int mRampDurationMaxMs;
    private final int mRampDurationMinMs;
    private final int mStepValue;
}
