// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.text.TextUtils;
import miui.net.ConnectivityHelper;
import miui.telephony.TelephonyManagerUtil;

public abstract class DeviceId
{

    private DeviceId()
    {
    }

    public static String get()
    {
        String s = TelephonyManagerUtil.getDeviceId();
        if(!TextUtils.isEmpty(s))
            return s;
        if(ConnectivityHelper.getInstance().isWifiOnly())
            s = ConnectivityHelper.getInstance().getMacAddress();
        String s1 = s;
        if(s == null)
            s1 = "";
        return s1;
    }
}
