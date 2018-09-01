// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

// Referenced classes of package android.net:
//            StaticIpConfiguration, ProxyInfo

public class IpConfiguration
    implements Parcelable
{
    public static final class IpAssignment extends Enum
    {

        public static IpAssignment valueOf(String s)
        {
            return (IpAssignment)Enum.valueOf(android/net/IpConfiguration$IpAssignment, s);
        }

        public static IpAssignment[] values()
        {
            return $VALUES;
        }

        private static final IpAssignment $VALUES[];
        public static final IpAssignment DHCP;
        public static final IpAssignment STATIC;
        public static final IpAssignment UNASSIGNED;

        static 
        {
            STATIC = new IpAssignment("STATIC", 0);
            DHCP = new IpAssignment("DHCP", 1);
            UNASSIGNED = new IpAssignment("UNASSIGNED", 2);
            $VALUES = (new IpAssignment[] {
                STATIC, DHCP, UNASSIGNED
            });
        }

        private IpAssignment(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class ProxySettings extends Enum
    {

        public static ProxySettings valueOf(String s)
        {
            return (ProxySettings)Enum.valueOf(android/net/IpConfiguration$ProxySettings, s);
        }

        public static ProxySettings[] values()
        {
            return $VALUES;
        }

        private static final ProxySettings $VALUES[];
        public static final ProxySettings NONE;
        public static final ProxySettings PAC;
        public static final ProxySettings STATIC;
        public static final ProxySettings UNASSIGNED;

        static 
        {
            NONE = new ProxySettings("NONE", 0);
            STATIC = new ProxySettings("STATIC", 1);
            UNASSIGNED = new ProxySettings("UNASSIGNED", 2);
            PAC = new ProxySettings("PAC", 3);
            $VALUES = (new ProxySettings[] {
                NONE, STATIC, UNASSIGNED, PAC
            });
        }

        private ProxySettings(String s, int i)
        {
            super(s, i);
        }
    }


    public IpConfiguration()
    {
        init(IpAssignment.UNASSIGNED, ProxySettings.UNASSIGNED, null, null);
    }

    public IpConfiguration(IpAssignment ipassignment, ProxySettings proxysettings, StaticIpConfiguration staticipconfiguration, ProxyInfo proxyinfo)
    {
        init(ipassignment, proxysettings, staticipconfiguration, proxyinfo);
    }

    public IpConfiguration(IpConfiguration ipconfiguration)
    {
        this();
        if(ipconfiguration != null)
            init(ipconfiguration.ipAssignment, ipconfiguration.proxySettings, ipconfiguration.staticIpConfiguration, ipconfiguration.httpProxy);
    }

    private void init(IpAssignment ipassignment, ProxySettings proxysettings, StaticIpConfiguration staticipconfiguration, ProxyInfo proxyinfo)
    {
        Object obj = null;
        ipAssignment = ipassignment;
        proxySettings = proxysettings;
        if(staticipconfiguration == null)
            ipassignment = null;
        else
            ipassignment = new StaticIpConfiguration(staticipconfiguration);
        staticIpConfiguration = ipassignment;
        if(proxyinfo == null)
            ipassignment = obj;
        else
            ipassignment = new ProxyInfo(proxyinfo);
        httpProxy = ipassignment;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof IpConfiguration))
            return false;
        obj = (IpConfiguration)obj;
        boolean flag1 = flag;
        if(ipAssignment == ((IpConfiguration) (obj)).ipAssignment)
        {
            flag1 = flag;
            if(proxySettings == ((IpConfiguration) (obj)).proxySettings)
            {
                flag1 = flag;
                if(Objects.equals(staticIpConfiguration, ((IpConfiguration) (obj)).staticIpConfiguration))
                    flag1 = Objects.equals(httpProxy, ((IpConfiguration) (obj)).httpProxy);
            }
        }
        return flag1;
    }

    public ProxyInfo getHttpProxy()
    {
        return httpProxy;
    }

    public IpAssignment getIpAssignment()
    {
        return ipAssignment;
    }

    public ProxySettings getProxySettings()
    {
        return proxySettings;
    }

    public StaticIpConfiguration getStaticIpConfiguration()
    {
        return staticIpConfiguration;
    }

    public int hashCode()
    {
        int i;
        if(staticIpConfiguration != null)
            i = staticIpConfiguration.hashCode();
        else
            i = 0;
        return i + 13 + ipAssignment.ordinal() * 17 + proxySettings.ordinal() * 47 + httpProxy.hashCode() * 83;
    }

    public void setHttpProxy(ProxyInfo proxyinfo)
    {
        httpProxy = proxyinfo;
    }

    public void setIpAssignment(IpAssignment ipassignment)
    {
        ipAssignment = ipassignment;
    }

    public void setProxySettings(ProxySettings proxysettings)
    {
        proxySettings = proxysettings;
    }

    public void setStaticIpConfiguration(StaticIpConfiguration staticipconfiguration)
    {
        staticIpConfiguration = staticipconfiguration;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("IP assignment: ").append(ipAssignment.toString());
        stringbuilder.append("\n");
        if(staticIpConfiguration != null)
        {
            stringbuilder.append("Static configuration: ").append(staticIpConfiguration.toString());
            stringbuilder.append("\n");
        }
        stringbuilder.append("Proxy settings: ").append(proxySettings.toString());
        stringbuilder.append("\n");
        if(httpProxy != null)
        {
            stringbuilder.append("HTTP proxy: ").append(httpProxy.toString());
            stringbuilder.append("\n");
        }
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(ipAssignment.name());
        parcel.writeString(proxySettings.name());
        parcel.writeParcelable(staticIpConfiguration, i);
        parcel.writeParcelable(httpProxy, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpConfiguration createFromParcel(Parcel parcel)
        {
            IpConfiguration ipconfiguration = new IpConfiguration();
            ipconfiguration.ipAssignment = IpAssignment.valueOf(parcel.readString());
            ipconfiguration.proxySettings = ProxySettings.valueOf(parcel.readString());
            ipconfiguration.staticIpConfiguration = (StaticIpConfiguration)parcel.readParcelable(null);
            ipconfiguration.httpProxy = (ProxyInfo)parcel.readParcelable(null);
            return ipconfiguration;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpConfiguration[] newArray(int i)
        {
            return new IpConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "IpConfiguration";
    public ProxyInfo httpProxy;
    public IpAssignment ipAssignment;
    public ProxySettings proxySettings;
    public StaticIpConfiguration staticIpConfiguration;

}
