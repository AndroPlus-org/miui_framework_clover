// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class SdpRecord
    implements Parcelable
{

    public SdpRecord(int i, byte abyte0[])
    {
        mRawData = abyte0;
        mRawSize = i;
    }

    public SdpRecord(Parcel parcel)
    {
        mRawSize = parcel.readInt();
        mRawData = new byte[mRawSize];
        parcel.readByteArray(mRawData);
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getRawData()
    {
        return mRawData;
    }

    public int getRawSize()
    {
        return mRawSize;
    }

    public String toString()
    {
        return (new StringBuilder()).append("BluetoothSdpRecord [rawData=").append(Arrays.toString(mRawData)).append(", rawSize=").append(mRawSize).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRawSize);
        parcel.writeByteArray(mRawData);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SdpRecord createFromParcel(Parcel parcel)
        {
            return new SdpRecord(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SdpRecord[] newArray(int i)
        {
            return new SdpRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final byte mRawData[];
    private final int mRawSize;

}
