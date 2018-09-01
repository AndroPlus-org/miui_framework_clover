// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import java.util.Arrays;

public class ContextHubMessage
{

    public ContextHubMessage(int i, int j, byte abyte0[])
    {
        mType = i;
        mVersion = j;
        mData = Arrays.copyOf(abyte0, abyte0.length);
    }

    private ContextHubMessage(Parcel parcel)
    {
        mType = parcel.readInt();
        mVersion = parcel.readInt();
        mData = new byte[parcel.readInt()];
        parcel.readByteArray(mData);
    }

    ContextHubMessage(Parcel parcel, ContextHubMessage contexthubmessage)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getData()
    {
        return Arrays.copyOf(mData, mData.length);
    }

    public int getMsgType()
    {
        return mType;
    }

    public int getVersion()
    {
        return mVersion;
    }

    public void setMsgData(byte abyte0[])
    {
        mData = Arrays.copyOf(abyte0, abyte0.length);
    }

    public void setMsgType(int i)
    {
        mType = i;
    }

    public void setVersion(int i)
    {
        mVersion = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeInt(mVersion);
        parcel.writeInt(mData.length);
        parcel.writeByteArray(mData);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ContextHubMessage createFromParcel(Parcel parcel)
        {
            return new ContextHubMessage(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ContextHubMessage[] newArray(int i)
        {
            return new ContextHubMessage[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ContextHubMessage";
    private byte mData[];
    private int mType;
    private int mVersion;

}
