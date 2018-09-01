// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioGain

public class AudioGainConfig
{

    AudioGainConfig(int i, AudioGain audiogain, int j, int k, int ai[], int l)
    {
        mIndex = i;
        mGain = audiogain;
        mMode = j;
        mChannelMask = k;
        mValues = ai;
        mRampDurationMs = l;
    }

    public int channelMask()
    {
        return mChannelMask;
    }

    int index()
    {
        return mIndex;
    }

    public int mode()
    {
        return mMode;
    }

    public int rampDurationMs()
    {
        return mRampDurationMs;
    }

    public int[] values()
    {
        return mValues;
    }

    private final int mChannelMask;
    AudioGain mGain;
    private final int mIndex;
    private final int mMode;
    private final int mRampDurationMs;
    private final int mValues[];
}
