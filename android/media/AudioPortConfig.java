// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioGainConfig, AudioPort

public class AudioPortConfig
{

    AudioPortConfig(AudioPort audioport, int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        mPort = audioport;
        mSamplingRate = i;
        mChannelMask = j;
        mFormat = k;
        mGain = audiogainconfig;
        mConfigMask = 0;
    }

    public int channelMask()
    {
        return mChannelMask;
    }

    public int format()
    {
        return mFormat;
    }

    public AudioGainConfig gain()
    {
        return mGain;
    }

    public AudioPort port()
    {
        return mPort;
    }

    public int samplingRate()
    {
        return mSamplingRate;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{mPort:").append(mPort).append(", mSamplingRate:").append(mSamplingRate).append(", mChannelMask: ").append(mChannelMask).append(", mFormat:").append(mFormat).append(", mGain:").append(mGain).append("}").toString();
    }

    static final int CHANNEL_MASK = 2;
    static final int FORMAT = 4;
    static final int GAIN = 8;
    static final int SAMPLE_RATE = 1;
    private final int mChannelMask;
    int mConfigMask;
    private final int mFormat;
    private final AudioGainConfig mGain;
    final AudioPort mPort;
    private final int mSamplingRate;
}
