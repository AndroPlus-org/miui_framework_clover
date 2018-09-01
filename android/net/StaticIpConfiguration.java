// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.InetAddress;
import java.util.*;

// Referenced classes of package android.net:
//            LinkAddress, NetworkUtils, RouteInfo, IpPrefix, 
//            LinkProperties

public class StaticIpConfiguration
    implements Parcelable
{

    public StaticIpConfiguration()
    {
        dnsServers = new ArrayList();
    }

    public StaticIpConfiguration(StaticIpConfiguration staticipconfiguration)
    {
        this();
        if(staticipconfiguration != null)
        {
            ipAddress = staticipconfiguration.ipAddress;
            gateway = staticipconfiguration.gateway;
            dnsServers.addAll(staticipconfiguration.dnsServers);
            domains = staticipconfiguration.domains;
        }
    }

    protected static void readFromParcel(StaticIpConfiguration staticipconfiguration, Parcel parcel)
    {
        staticipconfiguration.ipAddress = (LinkAddress)parcel.readParcelable(null);
        staticipconfiguration.gateway = NetworkUtils.unparcelInetAddress(parcel);
        staticipconfiguration.dnsServers.clear();
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
            staticipconfiguration.dnsServers.add(NetworkUtils.unparcelInetAddress(parcel));

        staticipconfiguration.domains = parcel.readString();
    }

    public void clear()
    {
        ipAddress = null;
        gateway = null;
        dnsServers.clear();
        domains = null;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof StaticIpConfiguration))
            return false;
        obj = (StaticIpConfiguration)obj;
        boolean flag1 = flag;
        if(obj != null)
        {
            flag1 = flag;
            if(Objects.equals(ipAddress, ((StaticIpConfiguration) (obj)).ipAddress))
            {
                flag1 = flag;
                if(Objects.equals(gateway, ((StaticIpConfiguration) (obj)).gateway))
                {
                    flag1 = flag;
                    if(dnsServers.equals(((StaticIpConfiguration) (obj)).dnsServers))
                        flag1 = Objects.equals(domains, ((StaticIpConfiguration) (obj)).domains);
                }
            }
        }
        return flag1;
    }

    public List getRoutes(String s)
    {
        ArrayList arraylist = new ArrayList(3);
        if(ipAddress != null)
        {
            RouteInfo routeinfo = new RouteInfo(ipAddress, null, s);
            arraylist.add(routeinfo);
            if(gateway != null && routeinfo.matches(gateway) ^ true)
                arraylist.add(RouteInfo.makeHostRoute(gateway, s));
        }
        if(gateway != null)
            arraylist.add(new RouteInfo((IpPrefix)null, gateway, s));
        return arraylist;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        if(ipAddress == null)
            j = 0;
        else
            j = ipAddress.hashCode();
        if(gateway == null)
            k = 0;
        else
            k = gateway.hashCode();
        if(domains != null)
            i = domains.hashCode();
        return (((j + 611) * 47 + k) * 47 + i) * 47 + dnsServers.hashCode();
    }

    public LinkProperties toLinkProperties(String s)
    {
        LinkProperties linkproperties = new LinkProperties();
        linkproperties.setInterfaceName(s);
        if(ipAddress != null)
            linkproperties.addLinkAddress(ipAddress);
        for(s = getRoutes(s).iterator(); s.hasNext(); linkproperties.addRoute((RouteInfo)s.next()));
        for(s = dnsServers.iterator(); s.hasNext(); linkproperties.addDnsServer((InetAddress)s.next()));
        linkproperties.setDomains(domains);
        return linkproperties;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("IP address ");
        if(ipAddress != null)
            stringbuffer.append(ipAddress).append(" ");
        stringbuffer.append("Gateway ");
        if(gateway != null)
            stringbuffer.append(gateway.getHostAddress()).append(" ");
        stringbuffer.append(" DNS servers: [");
        InetAddress inetaddress;
        for(Iterator iterator = dnsServers.iterator(); iterator.hasNext(); stringbuffer.append(" ").append(inetaddress.getHostAddress()))
            inetaddress = (InetAddress)iterator.next();

        stringbuffer.append(" ] Domains ");
        if(domains != null)
            stringbuffer.append(domains);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(ipAddress, i);
        NetworkUtils.parcelInetAddress(parcel, gateway, i);
        parcel.writeInt(dnsServers.size());
        for(Iterator iterator = dnsServers.iterator(); iterator.hasNext(); NetworkUtils.parcelInetAddress(parcel, (InetAddress)iterator.next(), i));
        parcel.writeString(domains);
    }

    public static android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StaticIpConfiguration createFromParcel(Parcel parcel)
        {
            StaticIpConfiguration staticipconfiguration = new StaticIpConfiguration();
            StaticIpConfiguration.readFromParcel(staticipconfiguration, parcel);
            return staticipconfiguration;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StaticIpConfiguration[] newArray(int i)
        {
            return new StaticIpConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final ArrayList dnsServers;
    public String domains;
    public InetAddress gateway;
    public LinkAddress ipAddress;

}
