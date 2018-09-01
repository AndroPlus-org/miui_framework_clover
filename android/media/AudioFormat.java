// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

public final class AudioFormat
    implements Parcelable
{
    public static class Builder
    {

        public AudioFormat build()
        {
            AudioFormat audioformat = new AudioFormat(1980, null);
            AudioFormat._2D_set2(audioformat, mEncoding);
            AudioFormat._2D_set4(audioformat, mSampleRate);
            AudioFormat._2D_set1(audioformat, mChannelMask);
            AudioFormat._2D_set0(audioformat, mChannelIndexMask);
            AudioFormat._2D_set3(audioformat, mPropertySetMask);
            return audioformat;
        }

        public Builder setChannelIndexMask(int i)
        {
            if(i == 0)
                throw new IllegalArgumentException("Invalid zero channel index mask");
            if(mChannelMask != 0 && Integer.bitCount(i) != Integer.bitCount(mChannelMask))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Mismatched channel count for index mask ").append(Integer.toHexString(i).toUpperCase()).toString());
            } else
            {
                mChannelIndexMask = i;
                mPropertySetMask = mPropertySetMask | 8;
                return this;
            }
        }

        public Builder setChannelMask(int i)
        {
            if(i == 0)
                throw new IllegalArgumentException("Invalid zero channel mask");
            if(mChannelIndexMask != 0 && Integer.bitCount(i) != Integer.bitCount(mChannelIndexMask))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Mismatched channel count for mask ").append(Integer.toHexString(i).toUpperCase()).toString());
            } else
            {
                mChannelMask = i;
                mPropertySetMask = mPropertySetMask | 4;
                return this;
            }
        }

        public Builder setEncoding(int i)
            throws IllegalArgumentException
        {
            i;
            JVM INSTR lookupswitch 15: default 132
        //                       1: 159
        //                       2: 176
        //                       3: 176
        //                       4: 176
        //                       5: 176
        //                       6: 176
        //                       7: 176
        //                       8: 176
        //                       13: 176
        //                       100: 176
        //                       101: 176
        //                       102: 176
        //                       103: 176
        //                       104: 176
        //                       105: 176;
               goto _L1 _L2 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3
_L1:
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid encoding ").append(i).toString());
_L2:
            mEncoding = 2;
_L5:
            mPropertySetMask = mPropertySetMask | 1;
            return this;
_L3:
            mEncoding = i;
            if(true) goto _L5; else goto _L4
_L4:
        }

        public Builder setSampleRate(int i)
            throws IllegalArgumentException
        {
            if((i < 4000 || i > 0x2ee00) && i != 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid sample rate ").append(i).toString());
            } else
            {
                mSampleRate = i;
                mPropertySetMask = mPropertySetMask | 2;
                return this;
            }
        }

        private int mChannelIndexMask;
        private int mChannelMask;
        private int mEncoding;
        private int mPropertySetMask;
        private int mSampleRate;

        public Builder()
        {
            mEncoding = 0;
            mSampleRate = 0;
            mChannelMask = 0;
            mChannelIndexMask = 0;
            mPropertySetMask = 0;
        }

        public Builder(AudioFormat audioformat)
        {
            mEncoding = 0;
            mSampleRate = 0;
            mChannelMask = 0;
            mChannelIndexMask = 0;
            mPropertySetMask = 0;
            mEncoding = AudioFormat._2D_get2(audioformat);
            mSampleRate = AudioFormat._2D_get4(audioformat);
            mChannelMask = AudioFormat._2D_get1(audioformat);
            mChannelIndexMask = AudioFormat._2D_get0(audioformat);
            mPropertySetMask = AudioFormat._2D_get3(audioformat);
        }
    }


    static int _2D_get0(AudioFormat audioformat)
    {
        return audioformat.mChannelIndexMask;
    }

    static int _2D_get1(AudioFormat audioformat)
    {
        return audioformat.mChannelMask;
    }

    static int _2D_get2(AudioFormat audioformat)
    {
        return audioformat.mEncoding;
    }

    static int _2D_get3(AudioFormat audioformat)
    {
        return audioformat.mPropertySetMask;
    }

    static int _2D_get4(AudioFormat audioformat)
    {
        return audioformat.mSampleRate;
    }

    static int _2D_set0(AudioFormat audioformat, int i)
    {
        audioformat.mChannelIndexMask = i;
        return i;
    }

    static int _2D_set1(AudioFormat audioformat, int i)
    {
        audioformat.mChannelMask = i;
        return i;
    }

    static int _2D_set2(AudioFormat audioformat, int i)
    {
        audioformat.mEncoding = i;
        return i;
    }

    static int _2D_set3(AudioFormat audioformat, int i)
    {
        audioformat.mPropertySetMask = i;
        return i;
    }

    static int _2D_set4(AudioFormat audioformat, int i)
    {
        audioformat.mSampleRate = i;
        return i;
    }

    public AudioFormat()
    {
        throw new UnsupportedOperationException("There is no valid usage of this constructor");
    }

    private AudioFormat(int i)
    {
    }

    private AudioFormat(int i, int j, int k, int l)
    {
        mEncoding = i;
        mSampleRate = j;
        mChannelMask = k;
        mChannelIndexMask = l;
        mPropertySetMask = 15;
    }

    AudioFormat(int i, AudioFormat audioformat)
    {
        this(i);
    }

    private AudioFormat(Parcel parcel)
    {
        mPropertySetMask = parcel.readInt();
        mEncoding = parcel.readInt();
        mSampleRate = parcel.readInt();
        mChannelMask = parcel.readInt();
        mChannelIndexMask = parcel.readInt();
    }

    AudioFormat(Parcel parcel, AudioFormat audioformat)
    {
        this(parcel);
    }

    public static int channelCountFromInChannelMask(int i)
    {
        return Integer.bitCount(i);
    }

    public static int channelCountFromOutChannelMask(int i)
    {
        return Integer.bitCount(i);
    }

    public static int convertChannelOutMaskToNativeMask(int i)
    {
        return i >> 2;
    }

    public static int convertNativeChannelMaskToOutMask(int i)
    {
        return i << 2;
    }

    public static int[] filterPublicFormats(int ai[])
    {
        if(ai == null)
            return null;
        ai = Arrays.copyOf(ai, ai.length);
        int i = 0;
        for(int j = 0; j < ai.length;)
        {
            int k = i;
            if(isPublicEncoding(ai[j]))
            {
                if(i != j)
                    ai[i] = ai[j];
                k = i + 1;
            }
            j++;
            i = k;
        }

        return Arrays.copyOf(ai, i);
    }

    public static int getBytesPerSample(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad audio format ").append(i).toString());

        case 3: // '\003'
            return 1;

        case 1: // '\001'
        case 2: // '\002'
        case 13: // '\r'
            return 2;

        case 4: // '\004'
            return 4;

        case 100: // 'd'
            return 32;

        case 101: // 'e'
            return 61;

        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
            return 23;
        }
    }

    public static int inChannelMaskFromOutChannelMask(int i)
        throws IllegalArgumentException
    {
        if(i == 1)
            throw new IllegalArgumentException("Illegal CHANNEL_OUT_DEFAULT channel mask for input.");
        switch(channelCountFromOutChannelMask(i))
        {
        default:
            throw new IllegalArgumentException("Unsupported channel configuration for input.");

        case 1: // '\001'
            return 16;

        case 2: // '\002'
            return 12;
        }
    }

    public static boolean isEncodingLinearFrames(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad audio format ").append(i).toString());

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 13: // '\r'
            return true;

        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
            return false;
        }
    }

    public static boolean isEncodingLinearPcm(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad audio format ").append(i).toString());

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return true;

        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 100: // 'd'
        case 101: // 'e'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
            return false;
        }
    }

    public static boolean isPublicEncoding(int i)
    {
        switch(i)
        {
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        default:
            return false;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 13: // '\r'
            return true;
        }
    }

    public static boolean isValidEncoding(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 100: // 'd'
        case 101: // 'e'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
            return true;
        }
    }

    public static String toLogFriendlyEncoding(int i)
    {
        switch(i)
        {
        case 1: // '\001'
        default:
            return (new StringBuilder()).append("invalid encoding ").append(i).toString();

        case 0: // '\0'
            return "ENCODING_INVALID";

        case 2: // '\002'
            return "ENCODING_PCM_16BIT";

        case 3: // '\003'
            return "ENCODING_PCM_8BIT";

        case 4: // '\004'
            return "ENCODING_PCM_FLOAT";

        case 5: // '\005'
            return "ENCODING_AC3";

        case 6: // '\006'
            return "ENCODING_E_AC3";

        case 7: // '\007'
            return "ENCODING_DTS";

        case 8: // '\b'
            return "ENCODING_DTS_HD";

        case 9: // '\t'
            return "ENCODING_MP3";

        case 10: // '\n'
            return "ENCODING_AAC_LC";

        case 11: // '\013'
            return "ENCODING_AAC_HE_V1";

        case 12: // '\f'
            return "ENCODING_AAC_HE_V2";

        case 13: // '\r'
            return "ENCODING_IEC61937";

        case 14: // '\016'
            return "ENCODING_DOLBY_TRUEHD";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (AudioFormat)obj;
        if(mPropertySetMask != ((AudioFormat) (obj)).mPropertySetMask)
            return false;
        boolean flag1;
        if(((mPropertySetMask & 1) == 0 || mEncoding == ((AudioFormat) (obj)).mEncoding) && ((mPropertySetMask & 2) == 0 || mSampleRate == ((AudioFormat) (obj)).mSampleRate) && ((mPropertySetMask & 4) == 0 || mChannelMask == ((AudioFormat) (obj)).mChannelMask))
        {
            flag1 = flag;
            if((mPropertySetMask & 8) != 0)
                if(mChannelIndexMask == ((AudioFormat) (obj)).mChannelIndexMask)
                    flag1 = flag;
                else
                    flag1 = false;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    public int getChannelCount()
    {
        int i;
        int j;
        i = Integer.bitCount(getChannelIndexMask());
        j = channelCountFromOutChannelMask(getChannelMask());
        if(j != 0) goto _L2; else goto _L1
_L1:
        int k = i;
_L4:
        return k;
_L2:
        k = j;
        if(j != i)
        {
            k = j;
            if(i != 0)
                k = 0;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getChannelIndexMask()
    {
        if((mPropertySetMask & 8) == 0)
            return 0;
        else
            return mChannelIndexMask;
    }

    public int getChannelMask()
    {
        if((mPropertySetMask & 4) == 0)
            return 0;
        else
            return mChannelMask;
    }

    public int getEncoding()
    {
        if((mPropertySetMask & 1) == 0)
            return 0;
        else
            return mEncoding;
    }

    public int getPropertySetMask()
    {
        return mPropertySetMask;
    }

    public int getSampleRate()
    {
        return mSampleRate;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mPropertySetMask), Integer.valueOf(mSampleRate), Integer.valueOf(mEncoding), Integer.valueOf(mChannelMask), Integer.valueOf(mChannelIndexMask)
        });
    }

    public String toLogFriendlyString()
    {
        return String.format("%dch %dHz %s", new Object[] {
            Integer.valueOf(getChannelCount()), Integer.valueOf(mSampleRate), toLogFriendlyEncoding(mEncoding)
        });
    }

    public String toString()
    {
        return new String((new StringBuilder()).append("AudioFormat: props=").append(mPropertySetMask).append(" enc=").append(mEncoding).append(" chan=0x").append(Integer.toHexString(mChannelMask).toUpperCase()).append(" chan_index=0x").append(Integer.toHexString(mChannelIndexMask).toUpperCase()).append(" rate=").append(mSampleRate).toString());
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mPropertySetMask);
        parcel.writeInt(mEncoding);
        parcel.writeInt(mSampleRate);
        parcel.writeInt(mChannelMask);
        parcel.writeInt(mChannelIndexMask);
    }

    public static final int AUDIO_FORMAT_HAS_PROPERTY_CHANNEL_INDEX_MASK = 8;
    public static final int AUDIO_FORMAT_HAS_PROPERTY_CHANNEL_MASK = 4;
    public static final int AUDIO_FORMAT_HAS_PROPERTY_ENCODING = 1;
    public static final int AUDIO_FORMAT_HAS_PROPERTY_NONE = 0;
    public static final int AUDIO_FORMAT_HAS_PROPERTY_SAMPLE_RATE = 2;
    public static final int CHANNEL_CONFIGURATION_DEFAULT = 1;
    public static final int CHANNEL_CONFIGURATION_INVALID = 0;
    public static final int CHANNEL_CONFIGURATION_MONO = 2;
    public static final int CHANNEL_CONFIGURATION_STEREO = 3;
    public static final int CHANNEL_INVALID = 0;
    public static final int CHANNEL_IN_5POINT1 = 252;
    public static final int CHANNEL_IN_BACK = 32;
    public static final int CHANNEL_IN_BACK_PROCESSED = 512;
    public static final int CHANNEL_IN_DEFAULT = 1;
    public static final int CHANNEL_IN_FRONT = 16;
    public static final int CHANNEL_IN_FRONT_BACK = 48;
    public static final int CHANNEL_IN_FRONT_PROCESSED = 256;
    public static final int CHANNEL_IN_LEFT = 4;
    public static final int CHANNEL_IN_LEFT_PROCESSED = 64;
    public static final int CHANNEL_IN_MONO = 16;
    public static final int CHANNEL_IN_PRESSURE = 1024;
    public static final int CHANNEL_IN_RIGHT = 8;
    public static final int CHANNEL_IN_RIGHT_PROCESSED = 128;
    public static final int CHANNEL_IN_STEREO = 12;
    public static final int CHANNEL_IN_VOICE_DNLINK = 32768;
    public static final int CHANNEL_IN_VOICE_UPLINK = 16384;
    public static final int CHANNEL_IN_X_AXIS = 2048;
    public static final int CHANNEL_IN_Y_AXIS = 4096;
    public static final int CHANNEL_IN_Z_AXIS = 8192;
    public static final int CHANNEL_OUT_5POINT1 = 252;
    public static final int CHANNEL_OUT_5POINT1_SIDE = 6204;
    public static final int CHANNEL_OUT_7POINT1 = 1020;
    public static final int CHANNEL_OUT_7POINT1_SURROUND = 6396;
    public static final int CHANNEL_OUT_BACK_CENTER = 1024;
    public static final int CHANNEL_OUT_BACK_LEFT = 64;
    public static final int CHANNEL_OUT_BACK_RIGHT = 128;
    public static final int CHANNEL_OUT_DEFAULT = 1;
    public static final int CHANNEL_OUT_FRONT_CENTER = 16;
    public static final int CHANNEL_OUT_FRONT_LEFT = 4;
    public static final int CHANNEL_OUT_FRONT_LEFT_OF_CENTER = 256;
    public static final int CHANNEL_OUT_FRONT_RIGHT = 8;
    public static final int CHANNEL_OUT_FRONT_RIGHT_OF_CENTER = 512;
    public static final int CHANNEL_OUT_LOW_FREQUENCY = 32;
    public static final int CHANNEL_OUT_MONO = 4;
    public static final int CHANNEL_OUT_QUAD = 204;
    public static final int CHANNEL_OUT_QUAD_SIDE = 6156;
    public static final int CHANNEL_OUT_SIDE_LEFT = 2048;
    public static final int CHANNEL_OUT_SIDE_RIGHT = 4096;
    public static final int CHANNEL_OUT_STEREO = 12;
    public static final int CHANNEL_OUT_SURROUND = 1052;
    public static final int CHANNEL_OUT_TOP_BACK_CENTER = 0x40000;
    public static final int CHANNEL_OUT_TOP_BACK_LEFT = 0x20000;
    public static final int CHANNEL_OUT_TOP_BACK_RIGHT = 0x80000;
    public static final int CHANNEL_OUT_TOP_CENTER = 8192;
    public static final int CHANNEL_OUT_TOP_FRONT_CENTER = 32768;
    public static final int CHANNEL_OUT_TOP_FRONT_LEFT = 16384;
    public static final int CHANNEL_OUT_TOP_FRONT_RIGHT = 0x10000;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioFormat createFromParcel(Parcel parcel)
        {
            return new AudioFormat(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioFormat[] newArray(int i)
        {
            return new AudioFormat[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int ENCODING_AAC_HE_V1 = 11;
    public static final int ENCODING_AAC_HE_V2 = 12;
    public static final int ENCODING_AAC_LC = 10;
    public static final int ENCODING_AC3 = 5;
    public static final int ENCODING_AMRNB = 100;
    public static final int ENCODING_AMRWB = 101;
    public static final int ENCODING_DEFAULT = 1;
    public static final int ENCODING_DOLBY_TRUEHD = 14;
    public static final int ENCODING_DTS = 7;
    public static final int ENCODING_DTS_HD = 8;
    public static final int ENCODING_EVRC = 102;
    public static final int ENCODING_EVRCB = 103;
    public static final int ENCODING_EVRCNW = 105;
    public static final int ENCODING_EVRCWB = 104;
    public static final int ENCODING_E_AC3 = 6;
    public static final int ENCODING_IEC61937 = 13;
    public static final int ENCODING_INVALID = 0;
    public static final int ENCODING_MP3 = 9;
    public static final int ENCODING_PCM_16BIT = 2;
    public static final int ENCODING_PCM_8BIT = 3;
    public static final int ENCODING_PCM_FLOAT = 4;
    public static final int SAMPLE_RATE_HZ_MAX = 0x2ee00;
    public static final int SAMPLE_RATE_HZ_MIN = 4000;
    public static final int SAMPLE_RATE_UNSPECIFIED = 0;
    private int mChannelIndexMask;
    private int mChannelMask;
    private int mEncoding;
    private int mPropertySetMask;
    private int mSampleRate;

}
