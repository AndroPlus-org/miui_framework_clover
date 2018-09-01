// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class BluetoothCodecConfig
    implements Parcelable
{

    public BluetoothCodecConfig(int i, int j, int k, int l, int i1, long l1, 
            long l2, long l3, long l4)
    {
        mCodecType = i;
        mCodecPriority = j;
        mSampleRate = k;
        mBitsPerSample = l;
        mChannelMode = i1;
        mCodecSpecific1 = l1;
        mCodecSpecific2 = l2;
        mCodecSpecific3 = l3;
        mCodecSpecific4 = l4;
    }

    private static String appendCapabilityToString(String s, String s1)
    {
        if(s == null)
            return s1;
        else
            return (new StringBuilder()).append(s).append("|").append(s1).toString();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof BluetoothCodecConfig)
        {
            obj = (BluetoothCodecConfig)obj;
            boolean flag1 = flag;
            if(((BluetoothCodecConfig) (obj)).mCodecType == mCodecType)
            {
                flag1 = flag;
                if(((BluetoothCodecConfig) (obj)).mCodecPriority == mCodecPriority)
                {
                    flag1 = flag;
                    if(((BluetoothCodecConfig) (obj)).mSampleRate == mSampleRate)
                    {
                        flag1 = flag;
                        if(((BluetoothCodecConfig) (obj)).mBitsPerSample == mBitsPerSample)
                        {
                            flag1 = flag;
                            if(((BluetoothCodecConfig) (obj)).mChannelMode == mChannelMode)
                            {
                                flag1 = flag;
                                if(((BluetoothCodecConfig) (obj)).mCodecSpecific1 == mCodecSpecific1)
                                {
                                    flag1 = flag;
                                    if(((BluetoothCodecConfig) (obj)).mCodecSpecific2 == mCodecSpecific2)
                                    {
                                        flag1 = flag;
                                        if(((BluetoothCodecConfig) (obj)).mCodecSpecific3 == mCodecSpecific3)
                                        {
                                            flag1 = flag;
                                            if(((BluetoothCodecConfig) (obj)).mCodecSpecific4 == mCodecSpecific4)
                                                flag1 = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public int getBitsPerSample()
    {
        return mBitsPerSample;
    }

    public int getChannelMode()
    {
        return mChannelMode;
    }

    public String getCodecName()
    {
        switch(mCodecType)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN CODEC(").append(mCodecType).append(")").toString();

        case 0: // '\0'
            return "SBC";

        case 1: // '\001'
            return "AAC";

        case 2: // '\002'
            return "aptX";

        case 3: // '\003'
            return "aptX HD";

        case 4: // '\004'
            return "LDAC";

        case 1000000: 
            return "INVALID CODEC";
        }
    }

    public int getCodecPriority()
    {
        return mCodecPriority;
    }

    public long getCodecSpecific1()
    {
        return mCodecSpecific1;
    }

    public long getCodecSpecific2()
    {
        return mCodecSpecific2;
    }

    public long getCodecSpecific3()
    {
        return mCodecSpecific3;
    }

    public long getCodecSpecific4()
    {
        return mCodecSpecific4;
    }

    public int getCodecType()
    {
        return mCodecType;
    }

    public int getSampleRate()
    {
        return mSampleRate;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mCodecType), Integer.valueOf(mCodecPriority), Integer.valueOf(mSampleRate), Integer.valueOf(mBitsPerSample), Integer.valueOf(mChannelMode), Long.valueOf(mCodecSpecific1), Long.valueOf(mCodecSpecific2), Long.valueOf(mCodecSpecific3), Long.valueOf(mCodecSpecific4)
        });
    }

    public boolean isMandatoryCodec()
    {
        boolean flag = false;
        if(mCodecType == 0)
            flag = true;
        return flag;
    }

    public boolean isValid()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mSampleRate != 0)
        {
            flag1 = flag;
            if(mBitsPerSample != 0)
            {
                flag1 = flag;
                if(mChannelMode != 0)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public boolean sameAudioFeedingParameters(BluetoothCodecConfig bluetoothcodecconfig)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(bluetoothcodecconfig != null)
        {
            flag1 = flag;
            if(bluetoothcodecconfig.mSampleRate == mSampleRate)
            {
                flag1 = flag;
                if(bluetoothcodecconfig.mBitsPerSample == mBitsPerSample)
                {
                    flag1 = flag;
                    if(bluetoothcodecconfig.mChannelMode == mChannelMode)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public void setCodecPriority(int i)
    {
        mCodecPriority = i;
    }

    public String toString()
    {
        String s = null;
        if(mSampleRate == 0)
            s = appendCapabilityToString(null, "NONE");
        String s1 = s;
        if((mSampleRate & 1) != 0)
            s1 = appendCapabilityToString(s, "44100");
        s = s1;
        if((mSampleRate & 2) != 0)
            s = appendCapabilityToString(s1, "48000");
        String s2 = s;
        if((mSampleRate & 4) != 0)
            s2 = appendCapabilityToString(s, "88200");
        s1 = s2;
        if((mSampleRate & 8) != 0)
            s1 = appendCapabilityToString(s2, "96000");
        s = s1;
        if((mSampleRate & 0x10) != 0)
            s = appendCapabilityToString(s1, "176400");
        s2 = s;
        if((mSampleRate & 0x20) != 0)
            s2 = appendCapabilityToString(s, "192000");
        s1 = null;
        if(mBitsPerSample == 0)
            s1 = appendCapabilityToString(null, "NONE");
        s = s1;
        if((mBitsPerSample & 1) != 0)
            s = appendCapabilityToString(s1, "16");
        s1 = s;
        if((mBitsPerSample & 2) != 0)
            s1 = appendCapabilityToString(s, "24");
        String s3 = s1;
        if((mBitsPerSample & 4) != 0)
            s3 = appendCapabilityToString(s1, "32");
        s = null;
        if(mChannelMode == 0)
            s = appendCapabilityToString(null, "NONE");
        s1 = s;
        if((mChannelMode & 1) != 0)
            s1 = appendCapabilityToString(s, "MONO");
        s = s1;
        if((mChannelMode & 2) != 0)
            s = appendCapabilityToString(s1, "STEREO");
        return (new StringBuilder()).append("{codecName:").append(getCodecName()).append(",mCodecType:").append(mCodecType).append(",mCodecPriority:").append(mCodecPriority).append(",mSampleRate:").append(String.format("0x%x", new Object[] {
            Integer.valueOf(mSampleRate)
        })).append("(").append(s2).append(")").append(",mBitsPerSample:").append(String.format("0x%x", new Object[] {
            Integer.valueOf(mBitsPerSample)
        })).append("(").append(s3).append(")").append(",mChannelMode:").append(String.format("0x%x", new Object[] {
            Integer.valueOf(mChannelMode)
        })).append("(").append(s).append(")").append(",mCodecSpecific1:").append(mCodecSpecific1).append(",mCodecSpecific2:").append(mCodecSpecific2).append(",mCodecSpecific3:").append(mCodecSpecific3).append(",mCodecSpecific4:").append(mCodecSpecific4).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCodecType);
        parcel.writeInt(mCodecPriority);
        parcel.writeInt(mSampleRate);
        parcel.writeInt(mBitsPerSample);
        parcel.writeInt(mChannelMode);
        parcel.writeLong(mCodecSpecific1);
        parcel.writeLong(mCodecSpecific2);
        parcel.writeLong(mCodecSpecific3);
        parcel.writeLong(mCodecSpecific4);
    }

    public static final int BITS_PER_SAMPLE_16 = 1;
    public static final int BITS_PER_SAMPLE_24 = 2;
    public static final int BITS_PER_SAMPLE_32 = 4;
    public static final int BITS_PER_SAMPLE_NONE = 0;
    public static final int CHANNEL_MODE_MONO = 1;
    public static final int CHANNEL_MODE_NONE = 0;
    public static final int CHANNEL_MODE_STEREO = 2;
    public static final int CODEC_PRIORITY_DEFAULT = 0;
    public static final int CODEC_PRIORITY_DISABLED = -1;
    public static final int CODEC_PRIORITY_HIGHEST = 0xf4240;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothCodecConfig createFromParcel(Parcel parcel)
        {
            return new BluetoothCodecConfig(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readLong(), parcel.readLong());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothCodecConfig[] newArray(int i)
        {
            return new BluetoothCodecConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int SAMPLE_RATE_176400 = 16;
    public static final int SAMPLE_RATE_192000 = 32;
    public static final int SAMPLE_RATE_44100 = 1;
    public static final int SAMPLE_RATE_48000 = 2;
    public static final int SAMPLE_RATE_88200 = 4;
    public static final int SAMPLE_RATE_96000 = 8;
    public static final int SAMPLE_RATE_NONE = 0;
    public static final int SOURCE_CODEC_TYPE_AAC = 1;
    public static final int SOURCE_CODEC_TYPE_APTX = 2;
    public static final int SOURCE_CODEC_TYPE_APTX_HD = 3;
    public static final int SOURCE_CODEC_TYPE_INVALID = 0xf4240;
    public static final int SOURCE_CODEC_TYPE_LDAC = 4;
    public static final int SOURCE_CODEC_TYPE_MAX = 5;
    public static final int SOURCE_CODEC_TYPE_SBC = 0;
    private final int mBitsPerSample;
    private final int mChannelMode;
    private int mCodecPriority;
    private final long mCodecSpecific1;
    private final long mCodecSpecific2;
    private final long mCodecSpecific3;
    private final long mCodecSpecific4;
    private final int mCodecType;
    private final int mSampleRate;

}
