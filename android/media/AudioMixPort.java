// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioPort, AudioMixPortConfig, AudioHandle, AudioGain, 
//            AudioGainConfig, AudioPortConfig

public class AudioMixPort extends AudioPort
{

    AudioMixPort(AudioHandle audiohandle, int i, int j, String s, int ai[], int ai1[], int ai2[], 
            int ai3[], AudioGain aaudiogain[])
    {
        super(audiohandle, j, s, ai, ai1, ai2, ai3, aaudiogain);
        mIoHandle = i;
    }

    public AudioMixPortConfig buildConfig(int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        return new AudioMixPortConfig(this, i, j, k, audiogainconfig);
    }

    public volatile AudioPortConfig buildConfig(int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        return buildConfig(i, j, k, audiogainconfig);
    }

    public boolean equals(Object obj)
    {
        if(obj == null || (obj instanceof AudioMixPort) ^ true)
            return false;
        AudioMixPort audiomixport = (AudioMixPort)obj;
        if(mIoHandle != audiomixport.ioHandle())
            return false;
        else
            return super.equals(obj);
    }

    public int ioHandle()
    {
        return mIoHandle;
    }

    private final int mIoHandle;
}
