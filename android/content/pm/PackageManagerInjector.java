// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.app.ActivityThread;
import android.app.Application;
import java.util.HashSet;
import java.util.Set;
import miui.os.Build;

// Referenced classes of package android.content.pm:
//            PackageManager

public class PackageManagerInjector
{

    public PackageManagerInjector()
    {
    }

    public static String getPermissionControllerPackageName()
    {
        Application application;
        String s;
        application = ActivityThread.currentApplication();
        s = ActivityThread.currentPackageName();
        if(!Build.IS_INTERNATIONAL_BUILD && (!Build.IS_TABLET || android.os.Build.VERSION.SDK_INT >= 26)) goto _L2; else goto _L1
_L1:
        boolean flag;
        return application.getPackageManager().getPermissionControllerPackageName();
_L2:
        if(flag = sCtsPackage.contains(s)) goto _L1; else goto _L3
_L3:
        return "com.lbe.security.miui";
        Exception exception;
        exception;
        exception.printStackTrace();
        if(true) goto _L3; else goto _L4
_L4:
    }

    private static Set sCtsPackage;

    static 
    {
        sCtsPackage = new HashSet();
        sCtsPackage.add("com.android.cts.usepermission");
        sCtsPackage.add("com.android.cts.permissionapp");
    }
}
