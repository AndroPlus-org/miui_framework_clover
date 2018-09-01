// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app;

import android.content.*;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import miui.os.Build;

// Referenced classes of package miui.app:
//            ConnectivityManagerReflector, CompatibilityP, ToggleManager

public class WifiApEnabler
{

    static boolean _2D_get0(WifiApEnabler wifiapenabler)
    {
        return wifiapenabler.mWaitForWifiStateChange;
    }

    static void _2D_wrap0(WifiApEnabler wifiapenabler, int i)
    {
        wifiapenabler.handleWifiApStateChanged(i);
    }

    static void _2D_wrap1(WifiApEnabler wifiapenabler, int i)
    {
        wifiapenabler.handleWifiStateChanged(i);
    }

    static void _2D_wrap2(WifiApEnabler wifiapenabler)
    {
        wifiapenabler.updateAirplaneMode();
    }

    public WifiApEnabler(Context context, ToggleManager togglemanager)
    {
        mContext = context;
        mConnectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        mToggleManager = togglemanager;
        mWifiManager = (WifiManager)context.getSystemService("wifi");
        mIntentFilter.addAction("android.net.conn.TETHER_STATE_CHANGED");
        mIntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        mIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        mContext.registerReceiver(mReceiver, mIntentFilter);
        updateAirplaneMode();
    }

    private void handleWifiApStateChanged(int i)
    {
        i;
        JVM INSTR tableswitch 10 13: default 32
    //                   10 73
    //                   11 86
    //                   12 47
    //                   13 60;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        mOpen = false;
        mStatusChanging = false;
_L7:
        updateToggle();
        return;
_L4:
        mOpen = true;
        mStatusChanging = true;
        continue; /* Loop/switch isn't completed */
_L5:
        mOpen = true;
        mStatusChanging = false;
        continue; /* Loop/switch isn't completed */
_L2:
        mOpen = false;
        mStatusChanging = true;
        continue; /* Loop/switch isn't completed */
_L3:
        mOpen = false;
        if(!mWaitForWifiStateChange)
            mStatusChanging = false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void handleWifiStateChanged(int i)
    {
        i;
        JVM INSTR tableswitch 3 4: default 24
    //                   3 25
    //                   4 25;
           goto _L1 _L2 _L2
_L1:
        return;
_L2:
        mWaitForWifiStateChange = false;
        mStatusChanging = false;
        updateToggle();
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void initWifiTethering()
    {
        mWifiConfig = mWifiManager.getWifiApConfiguration();
        if(mWifiConfig != null && mContext.getString(0x1108004a).equals(mWifiConfig.SSID))
        {
            WifiConfiguration wificonfiguration = mWifiConfig;
            String s;
            if(Build.IS_CM_CUSTOMIZATION_TEST)
                s = Build.DEVICE;
            else
                s = android.provider.MiuiSettings.System.getDeviceName(mContext);
            wificonfiguration.SSID = s;
            mWifiManager.setWifiApConfiguration(mWifiConfig);
        }
    }

    private void setSoftapEnabledWithConnectivityManager(boolean flag)
    {
        Log.d("WifiApEnabler", (new StringBuilder()).append("setSoftapEnabledWithConnectivityManager() enable=").append(flag).toString());
        boolean flag1;
        if(flag)
            flag1 = ConnectivityManagerReflector.startTethering(mConnectivityManager, 0, true);
        else
            flag1 = ConnectivityManagerReflector.stopTethering(mConnectivityManager, 0);
        updateToggle();
        if(!flag1)
            Log.e("WifiApEnabler", (new StringBuilder()).append("setSoftapEnabledWithConnectivityManager() enable=").append(flag).append(";result=").append(flag1).toString());
    }

    private void setSoftapEnabledWithWifiManager(boolean flag)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        int i = mWifiManager.getWifiState();
        if(android.os.Build.VERSION.SDK_INT < 23 && flag && (i == 2 || i == 3))
        {
            mWifiManager.setWifiEnabled(false);
            android.provider.Settings.Global.putInt(contentresolver, "wifi_saved_state", 1);
        }
        if(CompatibilityP.setWifiApEnabled(mWifiManager, flag))
        {
            mStatusChanging = true;
            mOpen = flag;
            updateToggle();
        }
        if(android.os.Build.VERSION.SDK_INT < 23 && flag ^ true && android.provider.Settings.Global.getInt(contentresolver, "wifi_saved_state", 0) == 1)
        {
            mWaitForWifiStateChange = true;
            mWifiManager.setWifiEnabled(true);
            android.provider.Settings.Global.putInt(contentresolver, "wifi_saved_state", 0);
        }
    }

    private void updateAirplaneMode()
    {
        boolean flag = false;
        if(android.provider.Settings.System.getInt(mContext.getContentResolver(), "airplane_mode_on", 0) != 0)
            flag = true;
        mDisabledByAirplane = flag;
        updateToggle();
    }

    private void updateToggle()
    {
        updateWifiApToggle(true);
    }

    boolean isWifiApDisabled()
    {
        boolean flag;
        if(!mStatusChanging)
            flag = mDisabledByAirplane;
        else
            flag = true;
        return flag;
    }

    boolean isWifiApOn()
    {
        boolean flag;
        if(mWifiManager.getWifiApState() == 13)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void setSoftapEnabled(boolean flag)
    {
        if(flag)
            initWifiTethering();
        if(android.os.Build.VERSION.SDK_INT < 24)
            setSoftapEnabledWithWifiManager(flag);
        else
            setSoftapEnabledWithConnectivityManager(flag);
    }

    public void toggleWifiAp()
    {
        if(!ToggleManager.isDisabled(24))
            if(mOpen)
                setSoftapEnabled(false);
            else
                setSoftapEnabled(true);
    }

    public void unregisterReceiver()
    {
        mContext.unregisterReceiver(mReceiver);
    }

    void updateWifiApToggle(boolean flag)
    {
        mToggleManager.updateToggleStatus(24, mOpen);
        ToggleManager togglemanager = mToggleManager;
        boolean flag1;
        int i;
        if(!mStatusChanging)
            flag1 = mDisabledByAirplane;
        else
            flag1 = true;
        togglemanager.updateToggleDisabled(24, flag1);
        togglemanager = mToggleManager;
        if(mOpen)
            i = 0x110200c6;
        else
            i = 0x110200c5;
        togglemanager.updateToggleImage(24, i);
        if(flag && mToggleManager.useWifiApForMiDrop())
            mToggleManager.updateMiDropToggle(false);
    }

    private static final String TAG = "WifiApEnabler";
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private boolean mDisabledByAirplane;
    private final IntentFilter mIntentFilter = new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED");
    private boolean mOpen;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            context1 = intent.getAction();
            if(!"android.net.wifi.WIFI_AP_STATE_CHANGED".equals(context1)) goto _L2; else goto _L1
_L1:
            WifiApEnabler._2D_wrap0(WifiApEnabler.this, intent.getIntExtra("wifi_state", 14));
_L4:
            return;
_L2:
            if("android.intent.action.AIRPLANE_MODE".equals(context1))
                WifiApEnabler._2D_wrap2(WifiApEnabler.this);
            else
            if("android.net.wifi.WIFI_STATE_CHANGED".equals(context1) && WifiApEnabler._2D_get0(WifiApEnabler.this))
                WifiApEnabler._2D_wrap1(WifiApEnabler.this, intent.getIntExtra("wifi_state", 4));
            if(true) goto _L4; else goto _L3
_L3:
        }

        final WifiApEnabler this$0;

            
            {
                this$0 = WifiApEnabler.this;
                super();
            }
    }
;
    private boolean mStatusChanging;
    private ToggleManager mToggleManager;
    private boolean mWaitForWifiStateChange;
    private WifiConfiguration mWifiConfig;
    private WifiManager mWifiManager;
}
