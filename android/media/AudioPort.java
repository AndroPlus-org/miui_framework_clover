// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioPortConfig, AudioHandle, AudioGain, AudioGainConfig

public class AudioPort
{

    AudioPort(AudioHandle audiohandle, int i, String s, int ai[], int ai1[], int ai2[], int ai3[], 
            AudioGain aaudiogain[])
    {
        mHandle = audiohandle;
        mRole = i;
        mName = s;
        mSamplingRates = ai;
        mChannelMasks = ai1;
        mChannelIndexMasks = ai2;
        mFormats = ai3;
        mGains = aaudiogain;
    }

    public AudioPortConfig activeConfig()
    {
        return mActiveConfig;
    }

    public AudioPortConfig buildConfig(int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        return new AudioPortConfig(this, i, j, k, audiogainconfig);
    }

    public int[] channelIndexMasks()
    {
        return mChannelIndexMasks;
    }

    public int[] channelMasks()
    {
        return mChannelMasks;
    }

    public boolean equals(Object obj)
    {
        if(obj == null || (obj instanceof AudioPort) ^ true)
        {
            return false;
        } else
        {
            obj = (AudioPort)obj;
            return mHandle.equals(((AudioPort) (obj)).handle());
        }
    }

    public int[] formats()
    {
        return mFormats;
    }

    AudioGain gain(int i)
    {
        if(i < 0 || i >= mGains.length)
            return null;
        else
            return mGains[i];
    }

    public AudioGain[] gains()
    {
        return mGains;
    }

    AudioHandle handle()
    {
        return mHandle;
    }

    public int hashCode()
    {
        return mHandle.hashCode();
    }

    public int id()
    {
        return mHandle.id();
    }

    public String name()
    {
        return mName;
    }

    public int role()
    {
        return mRole;
    }

    public int[] samplingRates()
    {
        return mSamplingRates;
    }

    public String toString()
    {
        String s = Integer.toString(mRole);
        mRole;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 77
    //                   1 83
    //                   2 89;
           goto _L1 _L2 _L3 _L4
_L1:
        return (new StringBuilder()).append("{mHandle: ").append(mHandle).append(", mRole: ").append(s).append("}").toString();
_L2:
        s = "NONE";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "SOURCE";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "SINK";
        if(true) goto _L1; else goto _L5
_L5:
    }

    public static final int ROLE_NONE = 0;
    public static final int ROLE_SINK = 2;
    public static final int ROLE_SOURCE = 1;
    private static final String TAG = "AudioPort";
    public static final int TYPE_DEVICE = 1;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_SESSION = 3;
    public static final int TYPE_SUBMIX = 2;
    private AudioPortConfig mActiveConfig;
    private final int mChannelIndexMasks[];
    private final int mChannelMasks[];
    private final int mFormats[];
    private final AudioGain mGains[];
    AudioHandle mHandle;
    private final String mName;
    protected final int mRole;
    private final int mSamplingRates[];
}
