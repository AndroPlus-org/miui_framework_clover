// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.utils;

import android.app.*;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.res.*;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

public class SecurityCenterHelper
{

    private SecurityCenterHelper()
    {
    }

    public static void collapseStatusPanels(Context context)
    {
        ((StatusBarManager)context.getSystemService("statusbar")).collapsePanels();
    }

    public static void forceStopPackage(ActivityManager activitymanager, String s)
    {
        activitymanager.forceStopPackage(s);
        if(UserHandle.myUserId() != 0)
            return;
        if(android.os.Build.VERSION.SDK_INT < 20)
            return;
        if(AppGlobals.getPackageManager().getApplicationInfo(s, 0, 999) != null)
            ActivityManagerNative.getDefault().forceStopPackage(s, 999);
_L1:
        return;
        activitymanager;
        activitymanager.printStackTrace();
          goto _L1
    }

    public static XmlResourceParser getApnsXml(Context context)
    {
        try
        {
            context = context.getResources().getXml(0x1170000);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return null;
        }
        return context;
    }

    public static int getBrightnessDimInt(Context context)
    {
        return context.getResources().getInteger(0x11070004);
    }

    public static String getLabel(Context context, String s, ApplicationInfo applicationinfo)
    {
        Resources resources;
        Object obj;
        Object obj1;
        Object obj2;
        resources = context.getResources();
        obj = null;
        obj1 = null;
        obj2 = null;
        context = obj;
        AssetManager assetmanager = JVM INSTR new #98  <Class AssetManager>;
        context = obj;
        assetmanager.AssetManager();
        int i;
        assetmanager.addAssetPath(s);
        s = JVM INSTR new #82  <Class Resources>;
        s.Resources(assetmanager, resources.getDisplayMetrics(), resources.getConfiguration());
        i = applicationinfo.labelRes;
        context = obj2;
        if(i == 0)
            break MISSING_BLOCK_LABEL_78;
        context = s.getText(applicationinfo.labelRes);
_L7:
        s = context;
        if(context != null)
            break MISSING_BLOCK_LABEL_96;
        if(applicationinfo.nonLocalizedLabel == null)
            break MISSING_BLOCK_LABEL_115;
        s = applicationinfo.nonLocalizedLabel;
_L1:
        context = s.toString();
        if(assetmanager != null)
            assetmanager.close();
        return context;
        s = applicationinfo.packageName;
          goto _L1
        context;
        s = obj1;
_L5:
        context = s;
        Log.e("getLabel", "getLabel error");
        if(s != null)
            s.close();
        return "";
        s;
        applicationinfo = context;
_L3:
        if(applicationinfo != null)
            applicationinfo.close();
        throw s;
        context;
        applicationinfo = assetmanager;
        s = context;
        if(true) goto _L3; else goto _L2
_L2:
        context;
        s = assetmanager;
        if(true) goto _L5; else goto _L4
_L4:
        context;
        context = obj2;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static long[] getProcessPss(int ai[])
    {
        try
        {
            ai = ActivityManagerNative.getDefault().getProcessPss(ai);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            ai.printStackTrace();
            return null;
        }
        return ai;
    }

    public static UserHandle getUserAll()
    {
        return UserHandle.ALL;
    }

    public static int getUserId(int i)
    {
        return UserHandle.getUserId(i);
    }

    public static boolean isAutomaticBrightnessAvailable(Context context)
    {
        return context.getResources().getBoolean(0x110a0001);
    }

    public static boolean packageHasActiveAdmins(DevicePolicyManager devicepolicymanager, String s)
    {
        return devicepolicymanager.packageHasActiveAdmins(s);
    }
}
