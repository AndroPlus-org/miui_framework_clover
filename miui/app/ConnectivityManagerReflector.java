// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app;

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import miui.util.ObjectReference;
import miui.util.ReflectionUtils;

public class ConnectivityManagerReflector
{

    public ConnectivityManagerReflector()
    {
    }

    public static boolean getWifiStaSapConcurrency(WifiManager wifimanager)
    {
        boolean flag = false;
        wifimanager = ReflectionUtils.tryCallMethod(wifimanager, "getWifiStaSapConcurrency", java/lang/Boolean, new Object[0]);
        if(wifimanager != null)
            flag = ((Boolean)wifimanager.get()).booleanValue();
        return flag;
    }

    public static boolean startTethering(ConnectivityManager connectivitymanager, int i, boolean flag)
    {
        boolean flag1 = false;
        Method method = android/net/ConnectivityManager.getMethod("startTethering", new Class[] {
            Integer.TYPE, Boolean.TYPE, Class.forName("android.net.ConnectivityManager$OnStartTetheringCallback")
        });
        method.setAccessible(true);
        android.net.ConnectivityManager.OnStartTetheringCallback onstarttetheringcallback = JVM INSTR new #6   <Class ConnectivityManagerReflector$1>;
        onstarttetheringcallback._cls1();
        method.invoke(connectivitymanager, new Object[] {
            Integer.valueOf(i), Boolean.valueOf(flag), onstarttetheringcallback
        });
        flag = true;
_L2:
        return flag;
        connectivitymanager;
        Log.e("WifiManagerReflector", "Exception", connectivitymanager);
        flag = flag1;
        continue; /* Loop/switch isn't completed */
        connectivitymanager;
        Log.e("WifiManagerReflector", "InvocationTargetException", connectivitymanager);
        flag = flag1;
        continue; /* Loop/switch isn't completed */
        connectivitymanager;
        Log.e("WifiManagerReflector", "IllegalAccessException", connectivitymanager);
        flag = flag1;
        continue; /* Loop/switch isn't completed */
        connectivitymanager;
        Log.e("WifiManagerReflector", "NoSuchMethodException", connectivitymanager);
        flag = flag1;
        continue; /* Loop/switch isn't completed */
        connectivitymanager;
        Log.e("WifiManagerReflector", "ClassNotFoundException", connectivitymanager);
        flag = flag1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean stopTethering(ConnectivityManager connectivitymanager, int i)
    {
        boolean flag = false;
        Method method = android/net/ConnectivityManager.getMethod("stopTethering", new Class[] {
            Integer.TYPE
        });
        method.setAccessible(true);
        method.invoke(connectivitymanager, new Object[] {
            Integer.valueOf(i)
        });
        flag = true;
_L2:
        return flag;
        connectivitymanager;
        Log.e("WifiManagerReflector", "Exception", connectivitymanager);
        continue; /* Loop/switch isn't completed */
        connectivitymanager;
        Log.e("WifiManagerReflector", "InvocationTargetException", connectivitymanager);
        continue; /* Loop/switch isn't completed */
        connectivitymanager;
        Log.e("WifiManagerReflector", "IllegalAccessException", connectivitymanager);
        continue; /* Loop/switch isn't completed */
        connectivitymanager;
        Log.e("WifiManagerReflector", "NoSuchMethodException", connectivitymanager);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "WifiManagerReflector";
    public static final int TETHERING_WIFI = 0;

    // Unreferenced inner class miui/app/ConnectivityManagerReflector$1

/* anonymous class */
    static final class _cls1 extends android.net.ConnectivityManager.OnStartTetheringCallback
    {

    }

}
