// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Random;

public final class BluetoothHidDeviceAppConfiguration
    implements Parcelable
{

    BluetoothHidDeviceAppConfiguration()
    {
        mHash = (new Random()).nextLong();
    }

    BluetoothHidDeviceAppConfiguration(long l)
    {
        mHash = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof BluetoothHidDeviceAppConfiguration)
        {
            obj = (BluetoothHidDeviceAppConfiguration)obj;
            if(mHash == ((BluetoothHidDeviceAppConfiguration) (obj)).mHash)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mHash);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothHidDeviceAppConfiguration createFromParcel(Parcel parcel)
        {
            return new BluetoothHidDeviceAppConfiguration(parcel.readLong());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothHidDeviceAppConfiguration[] newArray(int i)
        {
            return new BluetoothHidDeviceAppConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final long mHash;

}
