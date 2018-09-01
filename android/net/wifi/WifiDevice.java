// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;

public class WifiDevice
    implements Parcelable
{

    public WifiDevice()
    {
        deviceAddress = "";
        deviceName = "";
        deviceState = 0;
    }

    public WifiDevice(String s, boolean flag)
    {
        deviceAddress = "";
        deviceName = "";
        deviceState = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        deviceState = 1;
_L4:
        deviceAddress = s;
        return;
_L2:
        if(flag)
            deviceState = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null || (obj instanceof WifiDevice) ^ true)
            return false;
        obj = (WifiDevice)obj;
        if(deviceAddress == null)
        {
            if(((WifiDevice) (obj)).deviceAddress == null)
                flag = true;
            return flag;
        } else
        {
            return deviceAddress.equals(((WifiDevice) (obj)).deviceAddress);
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(deviceAddress);
        parcel.writeString(deviceName);
        parcel.writeInt(deviceState);
    }

    public static final int CONNECTED = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiDevice createFromParcel(Parcel parcel)
        {
            WifiDevice wifidevice = new WifiDevice();
            wifidevice.deviceAddress = parcel.readString();
            wifidevice.deviceName = parcel.readString();
            wifidevice.deviceState = parcel.readInt();
            return wifidevice;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiDevice[] newArray(int i)
        {
            return new WifiDevice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DISCONNECTED = 0;
    public String deviceAddress;
    public String deviceName;
    public int deviceState;

}
