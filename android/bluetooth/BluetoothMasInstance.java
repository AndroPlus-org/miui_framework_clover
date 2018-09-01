// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothMasInstance
    implements Parcelable
{
    public static final class MessageType
    {

        public static final int EMAIL = 1;
        public static final int MMS = 8;
        public static final int SMS_CDMA = 4;
        public static final int SMS_GSM = 2;

        public MessageType()
        {
        }
    }


    public BluetoothMasInstance(int i, String s, int j, int k)
    {
        mId = i;
        mName = s;
        mChannel = j;
        mMsgTypes = k;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof BluetoothMasInstance)
        {
            if(mId == ((BluetoothMasInstance)obj).mId)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public int getChannel()
    {
        return mChannel;
    }

    public int getId()
    {
        return mId;
    }

    public int getMsgTypes()
    {
        return mMsgTypes;
    }

    public String getName()
    {
        return mName;
    }

    public int hashCode()
    {
        return mId + (mChannel << 8) + (mMsgTypes << 16);
    }

    public boolean msgSupported(int i)
    {
        boolean flag = false;
        if((mMsgTypes & i) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append(Integer.toString(mId)).append(":").append(mName).append(":").append(mChannel).append(":").append(Integer.toHexString(mMsgTypes)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeInt(mChannel);
        parcel.writeInt(mMsgTypes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothMasInstance createFromParcel(Parcel parcel)
        {
            return new BluetoothMasInstance(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothMasInstance[] newArray(int i)
        {
            return new BluetoothMasInstance[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mChannel;
    private final int mId;
    private final int mMsgTypes;
    private final String mName;

}
