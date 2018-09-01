// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.IConnectivityManager;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkStats;
import android.net.StaticIpConfiguration;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.INetworkManagementService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.system.Os;
import android.telephony.TelephonyManager;
import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtils
{

    private NetworkUtils()
    {
    }

    public static NetworkStats getAdjustedNetworkStatsTethering()
    {
        NetworkStats networkstats;
        Object obj;
        networkstats = null;
        obj = networkstats;
        if(mNMService != null)
            break MISSING_BLOCK_LABEL_23;
        obj = networkstats;
        mNMService = android.os.INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        obj = networkstats;
        networkstats = mNMService.getNetworkStatsTethering(1);
        obj = networkstats;
        if(networkstats == null) goto _L2; else goto _L1
_L1:
        obj = networkstats;
        int i = networkstats.size();
        obj = networkstats;
        if(i <= 0) goto _L2; else goto _L3
_L3:
        if(mSystemServiceClassLoader != null) goto _L5; else goto _L4
_L4:
        String s = Os.getenv("SYSTEMSERVERCLASSPATH");
        if(s == null) goto _L7; else goto _L6
_L6:
        obj = JVM INSTR new #65  <Class PathClassLoader>;
        ((PathClassLoader) (obj)).PathClassLoader(s, ClassLoader.getSystemClassLoader());
        mSystemServiceClassLoader = ((ClassLoader) (obj));
_L5:
        Class.forName("com.android.server.NetPluginDelegate", false, mSystemServiceClassLoader).getMethod("getTetherStats", new Class[] {
            android/net/NetworkStats, android/net/NetworkStats, android/net/NetworkStats
        }).invoke(null, new Object[] {
            networkstats, null, null
        });
        obj = networkstats;
_L2:
        return ((NetworkStats) (obj));
_L7:
        mSystemServiceClassLoader = Thread.currentThread().getContextClassLoader();
          goto _L5
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        classnotfoundexception = networkstats;
          goto _L2
        Exception exception1;
        exception1;
        classnotfoundexception = networkstats;
        Log.e("OverLayUtil", "an exception occurred!!", exception1);
        classnotfoundexception = networkstats;
          goto _L2
        Exception exception;
        exception;
        Log.e("OverLayUtil", "an exception occurred!!", exception);
          goto _L2
    }

    public static String getMobileIface(Context context)
    {
        Object obj;
        obj = android.net.IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
        context = null;
        obj = ((IConnectivityManager) (obj)).getLinkPropertiesForType(0);
        context = ((Context) (obj));
_L1:
        RemoteException remoteexception;
        if(context != null)
            return context.getInterfaceName();
        else
            return "";
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static ArrayList getNetworkStatsTethering()
    {
        Object obj = null;
        NetworkStats networkstats = getAdjustedNetworkStatsTethering();
        android.net.NetworkStats.Entry entry = null;
        if(networkstats != null)
        {
            ArrayList arraylist = new ArrayList();
            int i = 0;
            do
            {
                obj = arraylist;
                if(i >= networkstats.size())
                    break;
                entry = networkstats.getValues(i, entry);
                obj = new HashMap();
                ((Map) (obj)).put("uid", String.valueOf(entry.uid));
                ((Map) (obj)).put("iface", entry.iface);
                ((Map) (obj)).put("rxBytes", String.valueOf(entry.rxBytes));
                ((Map) (obj)).put("txBytes", String.valueOf(entry.txBytes));
                ((Map) (obj)).put("tag", String.valueOf(entry.tag));
                arraylist.add(obj);
                i++;
            } while(true);
        }
        return ((ArrayList) (obj));
    }

    public static boolean isVpnConnected()
    {
        boolean flag = false;
        com.android.internal.net.VpnConfig vpnconfig;
        try
        {
            vpnconfig = android.net.IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity")).getVpnConfig(UserHandle.myUserId());
        }
        catch(Exception exception)
        {
            Log.e("OverLayUtil", "isVpnConnected", exception);
            return false;
        }
        if(vpnconfig != null)
            flag = true;
        return flag;
    }

    public static void saveWifiConfiguration(Context context, InetAddress inetaddress, WifiConfiguration wificonfiguration)
        throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException
    {
        WifiManager wifimanager;
        boolean flag;
        while(context == null || inetaddress == null || wificonfiguration == null) 
        {
            Log.i("OverLayUtil", "saveWifiConfiguration:  invalidate parameter!");
            return;
        }
        wifimanager = (WifiManager)context.getSystemService("wifi");
        if(wifimanager == null)
            return;
        flag = false;
        if(wificonfiguration.getIpAssignment() != android.net.IpConfiguration.IpAssignment.STATIC) goto _L2; else goto _L1
_L1:
        StaticIpConfiguration staticipconfiguration = wificonfiguration.getStaticIpConfiguration();
        context = null;
        if(staticipconfiguration.dnsServers.size() > 0)
            context = (InetAddress)staticipconfiguration.dnsServers.get(0);
        staticipconfiguration.dnsServers.clear();
        staticipconfiguration.dnsServers.add(inetaddress);
        if(context != null)
            staticipconfiguration.dnsServers.add(context);
        flag = true;
_L4:
        if(flag)
            wifimanager.save(wificonfiguration, new android.net.wifi.WifiManager.ActionListener() {

                public void onFailure(int i)
                {
                    Log.i("OverLayUtil", (new StringBuilder()).append("Failure to save wifi configuration! reason=").append(i).toString());
                }

                public void onSuccess()
                {
                    Log.i("OverLayUtil", "save  wifi configuration success!");
                }

            }
);
        return;
_L2:
        if(wificonfiguration.getIpAssignment() == android.net.IpConfiguration.IpAssignment.DHCP)
        {
            context = new StaticIpConfiguration();
            DhcpInfo dhcpinfo = wifimanager.getDhcpInfo();
            if(dhcpinfo != null)
            {
                context.ipAddress = new LinkAddress(android.net.NetworkUtils.intToInetAddress(dhcpinfo.ipAddress), android.net.NetworkUtils.netmaskIntToPrefixLength(dhcpinfo.netmask));
                context.gateway = android.net.NetworkUtils.intToInetAddress(dhcpinfo.gateway);
                ((StaticIpConfiguration) (context)).dnsServers.add(inetaddress);
                try
                {
                    ((StaticIpConfiguration) (context)).dnsServers.add(android.net.NetworkUtils.intToInetAddress(dhcpinfo.dns1));
                }
                // Misplaced declaration of an exception variable
                catch(InetAddress inetaddress)
                {
                    ((StaticIpConfiguration) (context)).dnsServers.add(android.net.NetworkUtils.numericToInetAddress("8.8.8.8"));
                }
                wificonfiguration.setIpConfiguration(new IpConfiguration(android.net.IpConfiguration.IpAssignment.STATIC, android.net.IpConfiguration.ProxySettings.NONE, context, null));
            }
            flag = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void setMobileDataState(Context context, boolean flag)
    {
        ((TelephonyManager)context.getSystemService("phone")).setDataEnabled(flag);
    }

    public static void vpnPrepareAndAuthorize(String s)
    {
        IConnectivityManager iconnectivitymanager = android.net.IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
        int i = UserHandle.myUserId();
        if(iconnectivitymanager.prepareVpn(null, s, i))
            iconnectivitymanager.setVpnPackageAuthorization(s, i, true);
_L1:
        return;
        s;
        Log.e("OverLayUtil", "prepareAndAuthorize", s);
          goto _L1
    }

    private static final String TAG = "OverLayUtil";
    private static INetworkManagementService mNMService;
    private static ClassLoader mSystemServiceClassLoader;
}
