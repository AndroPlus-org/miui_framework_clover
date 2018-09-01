// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioPortConfig, AudioDevicePort, AudioGainConfig, AudioPort

public class AudioDevicePortConfig extends AudioPortConfig
{

    AudioDevicePortConfig(AudioDevicePort audiodeviceport, int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        super(audiodeviceport, i, j, k, audiogainconfig);
    }

    AudioDevicePortConfig(AudioDevicePortConfig audiodeviceportconfig)
    {
        this(audiodeviceportconfig.port(), audiodeviceportconfig.samplingRate(), audiodeviceportconfig.channelMask(), audiodeviceportconfig.format(), audiodeviceportconfig.gain());
    }

    public AudioDevicePort port()
    {
        return (AudioDevicePort)mPort;
    }

    public volatile AudioPort port()
    {
        return port();
    }
}
