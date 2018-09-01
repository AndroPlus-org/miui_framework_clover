// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothAudioConfig
    implements Parcelable
{

    public BluetoothAudioConfig(int i, int j, int k)
    {
        mSampleRate = i;
        mChannelConfig = j;
        mAudioFormat = k;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof BluetoothAudioConfig)
        {
            obj = (BluetoothAudioConfig)obj;
            boolean flag1 = flag;
            if(((BluetoothAudioConfig) (obj)).mSampleRate == mSampleRate)
            {
                flag1 = flag;
                if(((BluetoothAudioConfig) (obj)).mChannelConfig == mChannelConfig)
                {
                    flag1 = flag;
                    if(((BluetoothAudioConfig) (obj)).mAudioFormat == mAudioFormat)
                        flag1 = true;
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public int getAudioFormat()
    {
        return mAudioFormat;
    }

    public int getChannelConfig()
    {
        return mChannelConfig;
    }

    public int getSampleRate()
    {
        return mSampleRate;
    }

    public int hashCode()
    {
        return mSampleRate | mChannelConfig << 24 | mAudioFormat << 28;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{mSampleRate:").append(mSampleRate).append(",mChannelConfig:").append(mChannelConfig).append(",mAudioFormat:").append(mAudioFormat).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSampleRate);
        parcel.writeInt(mChannelConfig);
        parcel.writeInt(mAudioFormat);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothAudioConfig createFromParcel(Parcel parcel)
        {
            return new BluetoothAudioConfig(parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothAudioConfig[] newArray(int i)
        {
            return new BluetoothAudioConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mAudioFormat;
    private final int mChannelConfig;
    private final int mSampleRate;

}
