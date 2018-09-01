// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.net.NetworkCapabilities;
import android.os.Parcel;
import android.os.Parcelable;

public final class DefaultNetworkEvent
    implements Parcelable
{

    public DefaultNetworkEvent(int i, int ai[], int j, boolean flag, boolean flag1)
    {
        netId = i;
        transportTypes = ai;
        prevNetId = j;
        prevIPv4 = flag;
        prevIPv6 = flag1;
    }

    private DefaultNetworkEvent(Parcel parcel)
    {
        boolean flag = true;
        super();
        netId = parcel.readInt();
        transportTypes = parcel.createIntArray();
        prevNetId = parcel.readInt();
        boolean flag1;
        if(parcel.readByte() > 0)
            flag1 = true;
        else
            flag1 = false;
        prevIPv4 = flag1;
        if(parcel.readByte() > 0)
            flag1 = flag;
        else
            flag1 = false;
        prevIPv6 = flag1;
    }

    DefaultNetworkEvent(Parcel parcel, DefaultNetworkEvent defaultnetworkevent)
    {
        this(parcel);
    }

    private String ipSupport()
    {
        if(prevIPv4 && prevIPv6)
            return "DUAL";
        if(prevIPv6)
            return "IPv6";
        if(prevIPv4)
            return "IPv4";
        else
            return "NONE";
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        String s = String.valueOf(prevNetId);
        String s1 = String.valueOf(netId);
        String s2 = s;
        if(prevNetId != 0)
            s2 = (new StringBuilder()).append(s).append(":").append(ipSupport()).toString();
        s = s1;
        if(netId != 0)
            s = (new StringBuilder()).append(s1).append(":").append(NetworkCapabilities.transportNamesOf(transportTypes)).toString();
        return String.format("DefaultNetworkEvent(%s -> %s)", new Object[] {
            s2, s
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(netId);
        parcel.writeIntArray(transportTypes);
        parcel.writeInt(prevNetId);
        int j;
        if(prevIPv4)
        {
            boolean flag = true;
            j = ((flag) ? 1 : 0);
        } else
        {
            boolean flag1 = false;
            j = ((flag1) ? 1 : 0);
        }
        parcel.writeByte(j);
        if(prevIPv6)
        {
            j = i;
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DefaultNetworkEvent createFromParcel(Parcel parcel)
        {
            return new DefaultNetworkEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DefaultNetworkEvent[] newArray(int i)
        {
            return new DefaultNetworkEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int netId;
    public final boolean prevIPv4;
    public final boolean prevIPv6;
    public final int prevNetId;
    public final int transportTypes[];

}
