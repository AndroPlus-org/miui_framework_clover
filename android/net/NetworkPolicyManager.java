// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.DebugUtils;
import java.util.Iterator;

// Referenced classes of package android.net:
//            NetworkPolicy, INetworkPolicyManager, INetworkPolicyListener

public class NetworkPolicyManager
{

    public NetworkPolicyManager(Context context, INetworkPolicyManager inetworkpolicymanager)
    {
        if(inetworkpolicymanager == null)
        {
            throw new IllegalArgumentException("missing INetworkPolicyManager");
        } else
        {
            mContext = context;
            mService = inetworkpolicymanager;
            return;
        }
    }

    public static Iterator cycleIterator(NetworkPolicy networkpolicy)
    {
        return networkpolicy.cycleIterator();
    }

    public static NetworkPolicyManager from(Context context)
    {
        return (NetworkPolicyManager)context.getSystemService("netpolicy");
    }

    public static boolean isProcStateAllowedWhileIdleOrPowerSaveMode(int i)
    {
        boolean flag;
        if(i <= 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isProcStateAllowedWhileOnRestrictBackground(int i)
    {
        boolean flag;
        if(i <= 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isUidValidForPolicy(Context context, int i)
    {
        return UserHandle.isApp(i);
    }

    public static String resolveNetworkId(WifiConfiguration wificonfiguration)
    {
        if(wificonfiguration.isPasspoint())
            wificonfiguration = wificonfiguration.providerFriendlyName;
        else
            wificonfiguration = wificonfiguration.SSID;
        return WifiInfo.removeDoubleQuotes(wificonfiguration);
    }

    public static String resolveNetworkId(String s)
    {
        return WifiInfo.removeDoubleQuotes(s);
    }

    public static String uidPoliciesToString(int i)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(i).append(" (");
        if(i == 0)
            stringbuilder.append("NONE");
        else
            stringbuilder.append(DebugUtils.flagsToString(android/net/NetworkPolicyManager, "POLICY_", i));
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public static String uidRulesToString(int i)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(i).append(" (");
        if(i == 0)
            stringbuilder.append("NONE");
        else
            stringbuilder.append(DebugUtils.flagsToString(android/net/NetworkPolicyManager, "RULE_", i));
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void addUidPolicy(int i, int j)
    {
        try
        {
            mService.addUidPolicy(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void factoryReset(String s)
    {
        try
        {
            mService.factoryReset(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public NetworkPolicy[] getNetworkPolicies()
    {
        NetworkPolicy anetworkpolicy[];
        try
        {
            anetworkpolicy = mService.getNetworkPolicies(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return anetworkpolicy;
    }

    public boolean getRestrictBackground()
    {
        boolean flag;
        try
        {
            flag = mService.getRestrictBackground();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public int getUidPolicy(int i)
    {
        try
        {
            i = mService.getUidPolicy(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int[] getUidsWithPolicy(int i)
    {
        int ai[];
        try
        {
            ai = mService.getUidsWithPolicy(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ai;
    }

    public void registerListener(INetworkPolicyListener inetworkpolicylistener)
    {
        try
        {
            mService.registerListener(inetworkpolicylistener);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(INetworkPolicyListener inetworkpolicylistener)
        {
            throw inetworkpolicylistener.rethrowFromSystemServer();
        }
    }

    public void removeUidPolicy(int i, int j)
    {
        try
        {
            mService.removeUidPolicy(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setNetworkPolicies(NetworkPolicy anetworkpolicy[])
    {
        try
        {
            mService.setNetworkPolicies(anetworkpolicy);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(NetworkPolicy anetworkpolicy[])
        {
            throw anetworkpolicy.rethrowFromSystemServer();
        }
    }

    public void setRestrictBackground(boolean flag)
    {
        try
        {
            mService.setRestrictBackground(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setUidPolicy(int i, int j)
    {
        try
        {
            mService.setUidPolicy(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void unregisterListener(INetworkPolicyListener inetworkpolicylistener)
    {
        try
        {
            mService.unregisterListener(inetworkpolicylistener);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(INetworkPolicyListener inetworkpolicylistener)
        {
            throw inetworkpolicylistener.rethrowFromSystemServer();
        }
    }

    private static final boolean ALLOW_PLATFORM_APP_POLICY = true;
    public static final String EXTRA_NETWORK_TEMPLATE = "android.net.NETWORK_TEMPLATE";
    public static final int FIREWALL_CHAIN_DOZABLE = 1;
    public static final String FIREWALL_CHAIN_NAME_DOZABLE = "dozable";
    public static final String FIREWALL_CHAIN_NAME_NONE = "none";
    public static final String FIREWALL_CHAIN_NAME_POWERSAVE = "powersave";
    public static final String FIREWALL_CHAIN_NAME_STANDBY = "standby";
    public static final int FIREWALL_CHAIN_NONE = 0;
    public static final int FIREWALL_CHAIN_POWERSAVE = 3;
    public static final int FIREWALL_CHAIN_STANDBY = 2;
    public static final int FIREWALL_RULE_ALLOW = 1;
    public static final int FIREWALL_RULE_DEFAULT = 0;
    public static final int FIREWALL_RULE_DENY = 2;
    public static final int FIREWALL_TYPE_BLACKLIST = 1;
    public static final int FIREWALL_TYPE_WHITELIST = 0;
    public static final int MASK_ALL_NETWORKS = 240;
    public static final int MASK_METERED_NETWORKS = 15;
    public static final int POLICY_ALLOW_METERED_BACKGROUND = 4;
    public static final int POLICY_NONE = 0;
    public static final int POLICY_REJECT_METERED_BACKGROUND = 1;
    public static final int RULE_ALLOW_ALL = 32;
    public static final int RULE_ALLOW_METERED = 1;
    public static final int RULE_NONE = 0;
    public static final int RULE_REJECT_ALL = 64;
    public static final int RULE_REJECT_METERED = 4;
    public static final int RULE_TEMPORARY_ALLOW_METERED = 2;
    private final Context mContext;
    private INetworkPolicyManager mService;
}
