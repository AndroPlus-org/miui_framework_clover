// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;

public class WifiWakeReasonAndCounts
    implements Parcelable
{

    public WifiWakeReasonAndCounts()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(" totalCmdEventWake ").append(totalCmdEventWake);
        stringbuffer.append(" totalDriverFwLocalWake ").append(totalDriverFwLocalWake);
        stringbuffer.append(" totalRxDataWake ").append(totalRxDataWake);
        stringbuffer.append(" rxUnicast ").append(rxUnicast);
        stringbuffer.append(" rxMulticast ").append(rxMulticast);
        stringbuffer.append(" rxBroadcast ").append(rxBroadcast);
        stringbuffer.append(" icmp ").append(icmp);
        stringbuffer.append(" icmp6 ").append(icmp6);
        stringbuffer.append(" icmp6Ra ").append(icmp6Ra);
        stringbuffer.append(" icmp6Na ").append(icmp6Na);
        stringbuffer.append(" icmp6Ns ").append(icmp6Ns);
        stringbuffer.append(" ipv4RxMulticast ").append(ipv4RxMulticast);
        stringbuffer.append(" ipv6Multicast ").append(ipv6Multicast);
        stringbuffer.append(" otherRxMulticast ").append(otherRxMulticast);
        for(int i = 0; i < cmdEventWakeCntArray.length; i++)
            stringbuffer.append((new StringBuilder()).append(" cmdEventWakeCntArray[").append(i).append("] ").append(cmdEventWakeCntArray[i]).toString());

        for(int j = 0; j < driverFWLocalWakeCntArray.length; j++)
            stringbuffer.append((new StringBuilder()).append(" driverFWLocalWakeCntArray[").append(j).append("] ").append(driverFWLocalWakeCntArray[j]).toString());

        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(totalCmdEventWake);
        parcel.writeInt(totalDriverFwLocalWake);
        parcel.writeInt(totalRxDataWake);
        parcel.writeInt(rxUnicast);
        parcel.writeInt(rxMulticast);
        parcel.writeInt(rxBroadcast);
        parcel.writeInt(icmp);
        parcel.writeInt(icmp6);
        parcel.writeInt(icmp6Ra);
        parcel.writeInt(icmp6Na);
        parcel.writeInt(icmp6Ns);
        parcel.writeInt(ipv4RxMulticast);
        parcel.writeInt(ipv6Multicast);
        parcel.writeInt(otherRxMulticast);
        parcel.writeIntArray(cmdEventWakeCntArray);
        parcel.writeIntArray(driverFWLocalWakeCntArray);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiWakeReasonAndCounts createFromParcel(Parcel parcel)
        {
            WifiWakeReasonAndCounts wifiwakereasonandcounts = new WifiWakeReasonAndCounts();
            wifiwakereasonandcounts.totalCmdEventWake = parcel.readInt();
            wifiwakereasonandcounts.totalDriverFwLocalWake = parcel.readInt();
            wifiwakereasonandcounts.totalRxDataWake = parcel.readInt();
            wifiwakereasonandcounts.rxUnicast = parcel.readInt();
            wifiwakereasonandcounts.rxMulticast = parcel.readInt();
            wifiwakereasonandcounts.rxBroadcast = parcel.readInt();
            wifiwakereasonandcounts.icmp = parcel.readInt();
            wifiwakereasonandcounts.icmp6 = parcel.readInt();
            wifiwakereasonandcounts.icmp6Ra = parcel.readInt();
            wifiwakereasonandcounts.icmp6Na = parcel.readInt();
            wifiwakereasonandcounts.icmp6Ns = parcel.readInt();
            wifiwakereasonandcounts.ipv4RxMulticast = parcel.readInt();
            wifiwakereasonandcounts.ipv6Multicast = parcel.readInt();
            wifiwakereasonandcounts.otherRxMulticast = parcel.readInt();
            parcel.readIntArray(wifiwakereasonandcounts.cmdEventWakeCntArray);
            parcel.readIntArray(wifiwakereasonandcounts.driverFWLocalWakeCntArray);
            return wifiwakereasonandcounts;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiWakeReasonAndCounts[] newArray(int i)
        {
            return new WifiWakeReasonAndCounts[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "WifiWakeReasonAndCounts";
    public int cmdEventWakeCntArray[];
    public int driverFWLocalWakeCntArray[];
    public int icmp;
    public int icmp6;
    public int icmp6Na;
    public int icmp6Ns;
    public int icmp6Ra;
    public int ipv4RxMulticast;
    public int ipv6Multicast;
    public int otherRxMulticast;
    public int rxBroadcast;
    public int rxMulticast;
    public int rxUnicast;
    public int totalCmdEventWake;
    public int totalDriverFwLocalWake;
    public int totalRxDataWake;

}
