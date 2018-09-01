// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;

public class WifiNetworkConnectionStatistics
    implements Parcelable
{

    public WifiNetworkConnectionStatistics()
    {
    }

    public WifiNetworkConnectionStatistics(int i, int j)
    {
        numConnection = i;
        numUsage = j;
    }

    public WifiNetworkConnectionStatistics(WifiNetworkConnectionStatistics wifinetworkconnectionstatistics)
    {
        numConnection = wifinetworkconnectionstatistics.numConnection;
        numUsage = wifinetworkconnectionstatistics.numUsage;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("c=").append(numConnection);
        stringbuilder.append(" u=").append(numUsage);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(numConnection);
        parcel.writeInt(numUsage);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiNetworkConnectionStatistics createFromParcel(Parcel parcel)
        {
            return new WifiNetworkConnectionStatistics(parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiNetworkConnectionStatistics[] newArray(int i)
        {
            return new WifiNetworkConnectionStatistics[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "WifiNetworkConnnectionStatistics";
    public int numConnection;
    public int numUsage;

}
