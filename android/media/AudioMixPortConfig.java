// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioPortConfig, AudioMixPort, AudioGainConfig, AudioPort

public class AudioMixPortConfig extends AudioPortConfig
{

    AudioMixPortConfig(AudioMixPort audiomixport, int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        super(audiomixport, i, j, k, audiogainconfig);
    }

    public AudioMixPort port()
    {
        return (AudioMixPort)mPort;
    }

    public volatile AudioPort port()
    {
        return port();
    }
}
