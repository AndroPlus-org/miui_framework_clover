// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app;

import android.net.wifi.WifiManager;
import android.os.*;

public class CompatibilityP
{

    public CompatibilityP()
    {
    }

    public static void setTemporaryScreenAutoBrightness(float f)
    {
        android.os.IPowerManager.Stub.asInterface(ServiceManager.getService("power")).setTemporaryScreenAutoBrightnessAdjustmentSettingOverride(f);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static void setTemporaryScreenBrightness(int i)
    {
        android.os.IPowerManager.Stub.asInterface(ServiceManager.getService("power")).setTemporaryScreenBrightnessSettingOverride(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static boolean setWifiApEnabled(WifiManager wifimanager, boolean flag)
    {
        return wifimanager.setWifiApEnabled(null, flag);
    }
}
