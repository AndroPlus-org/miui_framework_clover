// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.hybrid.hook;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Slog;

// Referenced classes of package com.miui.hybrid.hook:
//            CallingPkgHook, PkgInfoHook, IntentHook

public class HookClient
{

    public HookClient()
    {
    }

    public static String hookGetCallingPkg(String s, String s1)
    {
        String s2 = s1;
        String s3 = CallingPkgHook.getInstance().getHookCallingPkg(s, s1);
        s = s3;
_L2:
        return s;
        Throwable throwable;
        throwable;
        Slog.e("HookClient", (new StringBuilder()).append("Exception happened when hook getCallingPkg for hostApp:").append(s).append(", originCallingPkg:").append(s1).toString(), throwable);
        s = s2;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static PackageInfo hookPkgInfo(PackageInfo packageinfo, String s, int i)
    {
        PackageInfo packageinfo1 = packageinfo;
        try
        {
            packageinfo = PkgInfoHook.getInstance().hook(packageinfo, s, i);
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo)
        {
            packageinfo.printStackTrace();
            Slog.e("HookClient", (new StringBuilder()).append("Exception happened when hookPackageInfo for ").append(s).toString());
            packageinfo = packageinfo1;
        }
        return packageinfo;
    }

    public static Intent redirectStartActivity(Intent intent, String s)
    {
        Intent intent1 = intent;
        try
        {
            intent = IntentHook.getInstance().redirect(intent, s);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            intent.printStackTrace();
            Slog.e("HookClient", (new StringBuilder()).append("Exception happened when redirect intent for ").append(s).toString());
            intent = intent1;
        }
        return intent;
    }

    private static final String TAG = "HookClient";
}
