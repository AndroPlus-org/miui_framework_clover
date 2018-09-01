// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothHidDeviceAppQosSettings
    implements Parcelable
{

    public BluetoothHidDeviceAppQosSettings(int i, int j, int k, int l, int i1, int j1)
    {
        serviceType = i;
        tokenRate = j;
        tokenBucketSize = k;
        peakBandwidth = l;
        latency = i1;
        delayVariation = j1;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof BluetoothHidDeviceAppQosSettings)
        {
            obj = (BluetoothHidDeviceAppQosSettings)obj;
            return false;
        } else
        {
            return false;
        }
    }

    public int[] toArray()
    {
        return (new int[] {
            serviceType, tokenRate, tokenBucketSize, peakBandwidth, latency, delayVariation
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(serviceType);
        parcel.writeInt(tokenRate);
        parcel.writeInt(tokenBucketSize);
        parcel.writeInt(peakBandwidth);
        parcel.writeInt(latency);
        parcel.writeInt(delayVariation);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothHidDeviceAppQosSettings createFromParcel(Parcel parcel)
        {
            return new BluetoothHidDeviceAppQosSettings(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothHidDeviceAppQosSettings[] newArray(int i)
        {
            return new BluetoothHidDeviceAppQosSettings[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MAX = -1;
    public static final int SERVICE_BEST_EFFORT = 1;
    public static final int SERVICE_GUARANTEED = 2;
    public static final int SERVICE_NO_TRAFFIC = 0;
    public final int delayVariation;
    public final int latency;
    public final int peakBandwidth;
    public final int serviceType;
    public final int tokenBucketSize;
    public final int tokenRate;

}
