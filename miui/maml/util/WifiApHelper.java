// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiApHelper
{

    public WifiApHelper(Context context)
    {
        mContext = context;
        mWifiManager = (WifiManager)mContext.getSystemService("wifi");
    }

    public void setWifiApEnabled(boolean flag)
    {
        Log.d("WifiApHelper", (new StringBuilder()).append("setWifiApEnabled() enable=").append(flag).toString());
        mWifiManager.setWifiApEnabled(null, flag);
    }

    private static final String TAG = "WifiApHelper";
    private Context mContext;
    private WifiManager mWifiManager;
}
