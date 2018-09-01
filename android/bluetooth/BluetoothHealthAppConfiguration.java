// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothHealthAppConfiguration
    implements Parcelable
{

    BluetoothHealthAppConfiguration(String s, int i)
    {
        mName = s;
        mDataType = i;
        mRole = 2;
        mChannelType = 12;
    }

    BluetoothHealthAppConfiguration(String s, int i, int j, int k)
    {
        mName = s;
        mDataType = i;
        mRole = j;
        mChannelType = k;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof BluetoothHealthAppConfiguration)
        {
            obj = (BluetoothHealthAppConfiguration)obj;
            if(mName == null)
                return false;
            boolean flag1 = flag;
            if(mName.equals(((BluetoothHealthAppConfiguration) (obj)).getName()))
            {
                flag1 = flag;
                if(mDataType == ((BluetoothHealthAppConfiguration) (obj)).getDataType())
                {
                    flag1 = flag;
                    if(mRole == ((BluetoothHealthAppConfiguration) (obj)).getRole())
                    {
                        flag1 = flag;
                        if(mChannelType == ((BluetoothHealthAppConfiguration) (obj)).getChannelType())
                            flag1 = true;
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public int getChannelType()
    {
        return mChannelType;
    }

    public int getDataType()
    {
        return mDataType;
    }

    public String getName()
    {
        return mName;
    }

    public int getRole()
    {
        return mRole;
    }

    public int hashCode()
    {
        int i;
        if(mName != null)
            i = mName.hashCode();
        else
            i = 0;
        return (((i + 527) * 31 + mDataType) * 31 + mRole) * 31 + mChannelType;
    }

    public String toString()
    {
        return (new StringBuilder()).append("BluetoothHealthAppConfiguration [mName = ").append(mName).append(",mDataType = ").append(mDataType).append(", mRole = ").append(mRole).append(",mChannelType = ").append(mChannelType).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mName);
        parcel.writeInt(mDataType);
        parcel.writeInt(mRole);
        parcel.writeInt(mChannelType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothHealthAppConfiguration createFromParcel(Parcel parcel)
        {
            return new BluetoothHealthAppConfiguration(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothHealthAppConfiguration[] newArray(int i)
        {
            return new BluetoothHealthAppConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mChannelType;
    private final int mDataType;
    private final String mName;
    private final int mRole;

}
