// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            AudioPort, AudioManager, AudioDevicePortConfig, AudioSystem, 
//            AudioHandle, AudioGain, AudioGainConfig, AudioPortConfig

public class AudioDevicePort extends AudioPort
{

    AudioDevicePort(AudioHandle audiohandle, String s, int ai[], int ai1[], int ai2[], int ai3[], AudioGain aaudiogain[], 
            int i, String s1)
    {
        int j;
        if(AudioManager.isInputDevice(i))
            j = 1;
        else
            j = 2;
        super(audiohandle, j, s, ai, ai1, ai2, ai3, aaudiogain);
        mType = i;
        mAddress = s1;
    }

    public String address()
    {
        return mAddress;
    }

    public AudioDevicePortConfig buildConfig(int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        return new AudioDevicePortConfig(this, i, j, k, audiogainconfig);
    }

    public volatile AudioPortConfig buildConfig(int i, int j, int k, AudioGainConfig audiogainconfig)
    {
        return buildConfig(i, j, k, audiogainconfig);
    }

    public boolean equals(Object obj)
    {
        if(obj == null || (obj instanceof AudioDevicePort) ^ true)
            return false;
        AudioDevicePort audiodeviceport = (AudioDevicePort)obj;
        if(mType != audiodeviceport.type())
            return false;
        if(mAddress == null && audiodeviceport.address() != null)
            return false;
        if(!mAddress.equals(audiodeviceport.address()))
            return false;
        else
            return super.equals(obj);
    }

    public String toString()
    {
        String s;
        if(mRole == 1)
            s = AudioSystem.getInputDeviceName(mType);
        else
            s = AudioSystem.getOutputDeviceName(mType);
        return (new StringBuilder()).append("{").append(super.toString()).append(", mType: ").append(s).append(", mAddress: ").append(mAddress).append("}").toString();
    }

    public int type()
    {
        return mType;
    }

    private final String mAddress;
    private final int mType;
}
