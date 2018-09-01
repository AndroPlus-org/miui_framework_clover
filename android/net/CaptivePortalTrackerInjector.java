// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import miui.os.Build;

class CaptivePortalTrackerInjector
{

    CaptivePortalTrackerInjector()
    {
    }

    static final String getCaptivePortalServer(Context context, String s)
    {
        context = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperator();
        if(TextUtils.isEmpty(context) && Build.checkRegion("CN") || isCnFromOperator(context))
            s = "http://connect.rom.miui.com/generate_204";
        return s;
    }

    static boolean isCnFromOperator(String s)
    {
        String s1 = "";
        String s2 = s1;
        if(!TextUtils.isEmpty(s))
        {
            s2 = s1;
            if(s.length() >= 3)
                s2 = s.substring(0, 3);
        }
        return "460".equals(s2);
    }

    private static final String CN_CAPTIVE_PORTAL_SERVER = "http://connect.rom.miui.com/generate_204";
    private static final String CN_OPERATOR = "460";
}
