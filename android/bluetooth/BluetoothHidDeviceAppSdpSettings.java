// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothHidDeviceAppSdpSettings
    implements Parcelable
{

    public BluetoothHidDeviceAppSdpSettings(String s, String s1, String s2, byte byte0, byte abyte0[])
    {
        name = s;
        description = s1;
        provider = s2;
        subclass = byte0;
        descriptors = (byte[])abyte0.clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof BluetoothHidDeviceAppSdpSettings)
        {
            obj = (BluetoothHidDeviceAppSdpSettings)obj;
            return false;
        } else
        {
            return false;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(provider);
        parcel.writeByte(subclass);
        parcel.writeByteArray(descriptors);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothHidDeviceAppSdpSettings createFromParcel(Parcel parcel)
        {
            return new BluetoothHidDeviceAppSdpSettings(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readByte(), parcel.createByteArray());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothHidDeviceAppSdpSettings[] newArray(int i)
        {
            return new BluetoothHidDeviceAppSdpSettings[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final String description;
    public final byte descriptors[];
    public final String name;
    public final String provider;
    public final byte subclass;

}
