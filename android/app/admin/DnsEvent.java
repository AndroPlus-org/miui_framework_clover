// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

// Referenced classes of package android.app.admin:
//            NetworkEvent

public final class DnsEvent extends NetworkEvent
    implements Parcelable
{

    private DnsEvent(Parcel parcel)
    {
        hostname = parcel.readString();
        ipAddresses = parcel.createStringArray();
        ipAddressesCount = parcel.readInt();
        packageName = parcel.readString();
        timestamp = parcel.readLong();
    }

    DnsEvent(Parcel parcel, DnsEvent dnsevent)
    {
        this(parcel);
    }

    public DnsEvent(String s, String as[], int i, String s1, long l)
    {
        super(s1, l);
        hostname = s;
        ipAddresses = as;
        ipAddressesCount = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getHostname()
    {
        return hostname;
    }

    public List getInetAddresses()
    {
        int i = 0;
        if(ipAddresses == null || ipAddresses.length == 0)
            return Collections.emptyList();
        ArrayList arraylist = new ArrayList(ipAddresses.length);
        String as[] = ipAddresses;
        int j = as.length;
        while(i < j) 
        {
            String s = as[i];
            try
            {
                arraylist.add(InetAddress.getByName(s));
            }
            catch(UnknownHostException unknownhostexception) { }
            i++;
        }
        return arraylist;
    }

    public int getTotalResolvedAddressCount()
    {
        return ipAddressesCount;
    }

    public String toString()
    {
        String s = hostname;
        String s1;
        if(ipAddresses == null)
            s1 = "NONE";
        else
            s1 = String.join(" ", ipAddresses);
        return String.format("DnsEvent(%s, %s, %d, %d, %s)", new Object[] {
            s, s1, Integer.valueOf(ipAddressesCount), Long.valueOf(timestamp), packageName
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(1);
        parcel.writeString(hostname);
        parcel.writeStringArray(ipAddresses);
        parcel.writeInt(ipAddressesCount);
        parcel.writeString(packageName);
        parcel.writeLong(timestamp);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DnsEvent createFromParcel(Parcel parcel)
        {
            if(parcel.readInt() != 1)
                return null;
            else
                return new DnsEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DnsEvent[] newArray(int i)
        {
            return new DnsEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String hostname;
    private final String ipAddresses[];
    private final int ipAddressesCount;

}
