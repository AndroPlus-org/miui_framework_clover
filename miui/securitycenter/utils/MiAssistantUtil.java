// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.utils;

import android.content.Context;
import android.content.Intent;
import android.net.*;
import android.os.*;
import android.util.Log;

public class MiAssistantUtil
{

    private MiAssistantUtil()
    {
    }

    public static String getActiveInterfaceName()
    {
        Object obj;
        LinkProperties linkproperties;
        obj = android.net.IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
        linkproperties = null;
        obj = ((IConnectivityManager) (obj)).getActiveLinkProperties();
        linkproperties = ((LinkProperties) (obj));
_L1:
        RemoteException remoteexception;
        if(linkproperties != null)
        {
            return linkproperties.getInterfaceName();
        } else
        {
            Log.e("MiAssistantManager", "activeLink is null");
            return "null";
        }
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static void usbnet0Down(Context context)
    {
        Intent intent = new Intent("miui.intent.action.USB_SHARE_NET_STATE_CHANGE");
        NetworkInfo networkinfo = new NetworkInfo(9, 0, "ETHERNET", "");
        networkinfo.setIsAvailable(false);
        networkinfo.setDetailedState(android.net.NetworkInfo.DetailedState.DISCONNECTED, null, null);
        intent.putExtra("networkinfo", networkinfo);
        intent.putExtra("linkProperties", new LinkProperties());
        context.sendBroadcast(intent);
        context = android.os.INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        context.setInterfaceDown("usbnet0");
_L1:
        return;
        context;
        Log.w("MiAssistantManager", "disable usbnet0 error");
          goto _L1
    }

    private static final String EXTRA_LINK_PROPERTIES = "linkProperties";
    private static final String EXTRA_NETWORK_INFO = "networkinfo";
    private static final String INTERFACE_USBNET0 = "usbnet0";
    private static final String TAG = "MiAssistantManager";
    private static final String USB_SHARE_NET_STATE_CHANGE = "miui.intent.action.USB_SHARE_NET_STATE_CHANGE";
}
