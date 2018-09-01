// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiopolicy;

import android.media.*;
import java.util.Objects;

// Referenced classes of package android.media.audiopolicy:
//            AudioMixingRule

public class AudioMix
{
    public static class Builder
    {

        public AudioMix build()
            throws IllegalArgumentException
        {
            if(mRule == null)
                throw new IllegalArgumentException("Illegal null AudioMixingRule");
            if(mRouteFlags == 0)
                mRouteFlags = 2;
            if(mRouteFlags == 3)
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported route behavior combination 0x").append(Integer.toHexString(mRouteFlags)).toString());
            if(mFormat == null)
            {
                int i = AudioSystem.getPrimaryOutputSamplingRate();
                int j = i;
                if(i <= 0)
                    j = 44100;
                mFormat = (new android.media.AudioFormat.Builder()).setSampleRate(j).build();
            }
            if(mDeviceSystemType != 0 && mDeviceSystemType != 32768 && mDeviceSystemType != 0x80000100)
            {
                if((mRouteFlags & 1) == 0)
                    throw new IllegalArgumentException("Can't have audio device without flag ROUTE_FLAG_RENDER");
                if(mRule.getTargetMixType() != 0)
                    throw new IllegalArgumentException("Unsupported device on non-playback mix");
            } else
            {
                if((mRouteFlags & 1) == 1)
                    throw new IllegalArgumentException("Can't have flag ROUTE_FLAG_RENDER without an audio device");
                if((mRouteFlags & 3) == 2)
                    if(mRule.getTargetMixType() == 0)
                        mDeviceSystemType = 32768;
                    else
                    if(mRule.getTargetMixType() == 1)
                        mDeviceSystemType = 0x80000100;
                    else
                        throw new IllegalArgumentException("Unknown mixing rule type");
            }
            return new AudioMix(mRule, mFormat, mRouteFlags, mCallbackFlags, mDeviceSystemType, mDeviceAddress, null);
        }

        Builder setCallbackFlags(int i)
            throws IllegalArgumentException
        {
            if(i != 0 && (i & 1) == 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal callback flags 0x").append(Integer.toHexString(i).toUpperCase()).toString());
            } else
            {
                mCallbackFlags = i;
                return this;
            }
        }

        Builder setDevice(int i, String s)
        {
            mDeviceSystemType = i;
            mDeviceAddress = s;
            return this;
        }

        public Builder setDevice(AudioDeviceInfo audiodeviceinfo)
            throws IllegalArgumentException
        {
            if(audiodeviceinfo == null)
                throw new IllegalArgumentException("Illegal null AudioDeviceInfo argument");
            if(!audiodeviceinfo.isSink())
            {
                throw new IllegalArgumentException("Unsupported device type on mix, not a sink");
            } else
            {
                mDeviceSystemType = AudioDeviceInfo.convertDeviceTypeToInternalDevice(audiodeviceinfo.getType());
                mDeviceAddress = audiodeviceinfo.getAddress();
                return this;
            }
        }

        public Builder setFormat(AudioFormat audioformat)
            throws IllegalArgumentException
        {
            if(audioformat == null)
            {
                throw new IllegalArgumentException("Illegal null AudioFormat argument");
            } else
            {
                mFormat = audioformat;
                return this;
            }
        }

        Builder setMixingRule(AudioMixingRule audiomixingrule)
            throws IllegalArgumentException
        {
            if(audiomixingrule == null)
            {
                throw new IllegalArgumentException("Illegal null AudioMixingRule argument");
            } else
            {
                mRule = audiomixingrule;
                return this;
            }
        }

        public Builder setRouteFlags(int i)
            throws IllegalArgumentException
        {
            if(i == 0)
                throw new IllegalArgumentException("Illegal empty route flags");
            if((i & 3) == 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid route flags 0x").append(Integer.toHexString(i)).append("when configuring an AudioMix").toString());
            if((i & -4) != 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown route flags 0x").append(Integer.toHexString(i)).append("when configuring an AudioMix").toString());
            } else
            {
                mRouteFlags = i;
                return this;
            }
        }

        private int mCallbackFlags;
        private String mDeviceAddress;
        private int mDeviceSystemType;
        private AudioFormat mFormat;
        private int mRouteFlags;
        private AudioMixingRule mRule;

        Builder()
        {
            mRule = null;
            mFormat = null;
            mRouteFlags = 0;
            mCallbackFlags = 0;
            mDeviceSystemType = 0;
            mDeviceAddress = null;
        }

        public Builder(AudioMixingRule audiomixingrule)
            throws IllegalArgumentException
        {
            mRule = null;
            mFormat = null;
            mRouteFlags = 0;
            mCallbackFlags = 0;
            mDeviceSystemType = 0;
            mDeviceAddress = null;
            if(audiomixingrule == null)
            {
                throw new IllegalArgumentException("Illegal null AudioMixingRule argument");
            } else
            {
                mRule = audiomixingrule;
                return;
            }
        }
    }


    private AudioMix(AudioMixingRule audiomixingrule, AudioFormat audioformat, int i, int j, int k, String s)
    {
        mMixType = -1;
        mMixState = -1;
        mRule = audiomixingrule;
        mFormat = audioformat;
        mRouteFlags = i;
        mMixType = audiomixingrule.getTargetMixType();
        mCallbackFlags = j;
        mDeviceSystemType = k;
        audiomixingrule = s;
        if(s == null)
            audiomixingrule = new String("");
        mDeviceAddress = audiomixingrule;
    }

    AudioMix(AudioMixingRule audiomixingrule, AudioFormat audioformat, int i, int j, int k, String s, AudioMix audiomix)
    {
        this(audiomixingrule, audioformat, i, j, k, s);
    }

    AudioFormat getFormat()
    {
        return mFormat;
    }

    public int getMixState()
    {
        return mMixState;
    }

    public int getMixType()
    {
        return mMixType;
    }

    public String getRegistration()
    {
        return mDeviceAddress;
    }

    int getRouteFlags()
    {
        return mRouteFlags;
    }

    AudioMixingRule getRule()
    {
        return mRule;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mRouteFlags), mRule, Integer.valueOf(mMixType), mFormat
        });
    }

    void setRegistration(String s)
    {
        mDeviceAddress = s;
    }

    private static final int CALLBACK_FLAGS_ALL = 1;
    public static final int CALLBACK_FLAG_NOTIFY_ACTIVITY = 1;
    public static final int MIX_STATE_DISABLED = -1;
    public static final int MIX_STATE_IDLE = 0;
    public static final int MIX_STATE_MIXING = 1;
    public static final int MIX_TYPE_INVALID = -1;
    public static final int MIX_TYPE_PLAYERS = 0;
    public static final int MIX_TYPE_RECORDERS = 1;
    public static final int ROUTE_FLAG_LOOP_BACK = 2;
    public static final int ROUTE_FLAG_RENDER = 1;
    private static final int ROUTE_FLAG_SUPPORTED = 3;
    int mCallbackFlags;
    String mDeviceAddress;
    final int mDeviceSystemType;
    private AudioFormat mFormat;
    int mMixState;
    private int mMixType;
    private int mRouteFlags;
    private AudioMixingRule mRule;
}
