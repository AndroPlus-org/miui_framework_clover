// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.net.wifi:
//            WifiConfiguration

public final class SupplicantStateTrackerInjector
{

    public SupplicantStateTrackerInjector()
    {
    }

    public static void handleConnectNetwork(int i)
    {
        sNetid = i;
        sNetworksDisabledDuringConnect = true;
    }

    public static void handleNetworkConnectionComplete()
    {
        sNetworksDisabledDuringConnect = false;
        sNetid = -1;
    }

    public static void handleNetworkConnectionFailure(Context context, List list, int i)
    {
        if(sNetworksDisabledDuringConnect && sNetid == i)
        {
            sNetworksDisabledDuringConnect = false;
            sNetid = -1;
            if(i == -1 || list == null)
                return;
        } else
        {
            return;
        }
        Object obj = null;
        Iterator iterator = list.iterator();
        do
        {
            list = obj;
            if(!iterator.hasNext())
                break;
            list = (WifiConfiguration)iterator.next();
        } while(((WifiConfiguration) (list)).networkId != i);
        if(list == null || ((WifiConfiguration) (list)).status != 1)
        {
            return;
        } else
        {
            Intent intent = new Intent("miui.intent.action.WIFI_CONNECTION_FAILURE");
            intent.addFlags(0x14000000);
            intent.putExtra("wifiConfiguration", list);
            context.sendBroadcastAsUser(intent, UserHandle.ALL);
            return;
        }
    }

    public static boolean isConformAuthFailure(int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(j > 0)
        {
            flag1 = flag;
            if(sNetworksDisabledDuringConnect)
            {
                flag1 = flag;
                if(i != -1)
                {
                    flag1 = flag;
                    if(sNetid == i)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    private static int sNetid = -1;
    private static boolean sNetworksDisabledDuringConnect;

}
