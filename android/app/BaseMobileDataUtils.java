// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.telephony.TelephonyManager;

public class BaseMobileDataUtils
{

    public BaseMobileDataUtils()
    {
    }

    public Uri getMobileDataUri()
    {
        return android.provider.Settings.Global.getUriFor("mobile_data");
    }

    public String getSubscriberId(Context context)
    {
        return ((TelephonyManager)context.getSystemService("phone")).getSubscriberId();
    }

    public boolean isMobileEnable(Context context)
    {
        return ((ConnectivityManager)context.getSystemService("connectivity")).getMobileDataEnabled();
    }

    public void onMobileDataChange(Context context)
    {
    }
}
