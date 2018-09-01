// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Objects;

// Referenced classes of package android.net:
//            StaticIpConfiguration, NetworkUtils, LinkAddress

public class DhcpResults extends StaticIpConfiguration
{

    static void _2D_wrap0(DhcpResults dhcpresults, Parcel parcel)
    {
        readFromParcel(dhcpresults, parcel);
    }

    public DhcpResults()
    {
    }

    public DhcpResults(DhcpResults dhcpresults)
    {
        super(dhcpresults);
        if(dhcpresults != null)
        {
            serverAddress = dhcpresults.serverAddress;
            vendorInfo = dhcpresults.vendorInfo;
            leaseDuration = dhcpresults.leaseDuration;
            mtu = dhcpresults.mtu;
        }
    }

    public DhcpResults(StaticIpConfiguration staticipconfiguration)
    {
        super(staticipconfiguration);
    }

    private static void readFromParcel(DhcpResults dhcpresults, Parcel parcel)
    {
        StaticIpConfiguration.readFromParcel(dhcpresults, parcel);
        dhcpresults.leaseDuration = parcel.readInt();
        dhcpresults.mtu = parcel.readInt();
        dhcpresults.serverAddress = (Inet4Address)NetworkUtils.unparcelInetAddress(parcel);
        dhcpresults.vendorInfo = parcel.readString();
    }

    public boolean addDns(String s)
    {
        if(!TextUtils.isEmpty(s))
            try
            {
                dnsServers.add(NetworkUtils.numericToInetAddress(s));
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.e("DhcpResults", (new StringBuilder()).append("addDns failed with addrString ").append(s).toString());
                return true;
            }
        return false;
    }

    public void clear()
    {
        super.clear();
        vendorInfo = null;
        leaseDuration = 0;
        mtu = 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof DhcpResults))
            return false;
        DhcpResults dhcpresults = (DhcpResults)obj;
        if(super.equals((StaticIpConfiguration)obj) && Objects.equals(serverAddress, dhcpresults.serverAddress) && Objects.equals(vendorInfo, dhcpresults.vendorInfo) && leaseDuration == dhcpresults.leaseDuration)
        {
            if(mtu != dhcpresults.mtu)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean hasMeteredHint()
    {
        if(vendorInfo != null)
            return vendorInfo.contains("ANDROID_METERED");
        else
            return false;
    }

    public void setDomains(String s)
    {
        domains = s;
    }

    public boolean setGateway(String s)
    {
        try
        {
            gateway = NetworkUtils.numericToInetAddress(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.e("DhcpResults", (new StringBuilder()).append("setGateway failed with addrString ").append(s).toString());
            return true;
        }
        return false;
    }

    public boolean setIpAddress(String s, int i)
    {
        try
        {
            Inet4Address inet4address = (Inet4Address)NetworkUtils.numericToInetAddress(s);
            LinkAddress linkaddress = JVM INSTR new #146 <Class LinkAddress>;
            linkaddress.LinkAddress(inet4address, i);
            ipAddress = linkaddress;
        }
        catch(Object obj)
        {
            Log.e("DhcpResults", (new StringBuilder()).append("setIpAddress failed with addrString ").append(s).append("/").append(i).toString());
            return true;
        }
        return false;
    }

    public void setLeaseDuration(int i)
    {
        leaseDuration = i;
    }

    public boolean setServerAddress(String s)
    {
        try
        {
            serverAddress = (Inet4Address)NetworkUtils.numericToInetAddress(s);
        }
        catch(Object obj)
        {
            Log.e("DhcpResults", (new StringBuilder()).append("setServerAddress failed with addrString ").append(s).toString());
            return true;
        }
        return false;
    }

    public void setVendorInfo(String s)
    {
        vendorInfo = s;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(super.toString());
        stringbuffer.append(" DHCP server ").append(serverAddress);
        stringbuffer.append(" Vendor info ").append(vendorInfo);
        stringbuffer.append(" lease ").append(leaseDuration).append(" seconds");
        if(mtu != 0)
            stringbuffer.append(" MTU ").append(mtu);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(leaseDuration);
        parcel.writeInt(mtu);
        NetworkUtils.parcelInetAddress(parcel, serverAddress, i);
        parcel.writeString(vendorInfo);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DhcpResults createFromParcel(Parcel parcel)
        {
            DhcpResults dhcpresults = new DhcpResults();
            DhcpResults._2D_wrap0(dhcpresults, parcel);
            return dhcpresults;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DhcpResults[] newArray(int i)
        {
            return new DhcpResults[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "DhcpResults";
    public int leaseDuration;
    public int mtu;
    public Inet4Address serverAddress;
    public String vendorInfo;

}
