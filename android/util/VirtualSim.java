// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemProperties;
import miui.os.Build;

public final class VirtualSim
{

    public VirtualSim()
    {
    }

    private static boolean isActivityExist(Context context, Intent intent)
    {
        return context.getPackageManager().resolveActivity(intent, 0) != null;
    }

    public static boolean isSupportMiSim(Context context)
    {
        Intent intent = new Intent();
        intent.setClassName("com.miui.virtualsim", "com.miui.mimobile.ui.MmRouterActivity");
        return isActivityExist(context, intent) && !"LTE-CMCC".equals(SystemProperties.get("persist.radio.modem")) && !Build.IS_INTERNATIONAL_BUILD;
    }

    public static boolean isSupportVirtualSim(Context context)
    {
        Intent intent = new Intent();
        intent.setClassName("com.miui.virtualsim", "com.miui.virtualsim.ui.MainActivity");
        return isActivityExist(context, intent);
    }
}
