// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.hybrid.hook;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Slog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PkgInfoHook
{

    private PkgInfoHook()
    {
        fakeData = new ConcurrentHashMap();
    }

    public static PkgInfoHook getInstance()
    {
        if(sInstance != null) goto _L2; else goto _L1
_L1:
        com/miui/hybrid/hook/PkgInfoHook;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            PkgInfoHook pkginfohook = JVM INSTR new #2   <Class PkgInfoHook>;
            pkginfohook.PkgInfoHook();
            sInstance = pkginfohook;
        }
        com/miui/hybrid/hook/PkgInfoHook;
        JVM INSTR monitorexit ;
_L2:
        return sInstance;
        Exception exception;
        exception;
        throw exception;
    }

    public PackageInfo delete(String s)
    {
        if(TextUtils.isEmpty(s))
        {
            Slog.e("PkgInfoHook", "Expect non-null pkgName.");
            return null;
        } else
        {
            PackageInfo packageinfo = (PackageInfo)fakeData.remove(s);
            Slog.v("PkgInfoHook", (new StringBuilder()).append("remove ").append(s).append(" pInfo:").append(packageinfo).toString());
            return packageinfo;
        }
    }

    public PackageInfo hook(PackageInfo packageinfo, String s, int i)
    {
        if(!TextUtils.isEmpty(s))
        {
            PackageInfo packageinfo1 = (PackageInfo)fakeData.get(s);
            if(packageinfo1 != null)
            {
                Slog.d("PkgInfoHook", (new StringBuilder()).append("hook ").append(s).toString());
                return packageinfo1;
            }
        }
        return packageinfo;
    }

    public boolean insert(PackageInfo packageinfo)
    {
        if(packageinfo == null || TextUtils.isEmpty(packageinfo.packageName))
        {
            Slog.e("PkgInfoHook", "PkgInfoHook.insert(PackageInfo) failed.");
            return false;
        } else
        {
            fakeData.put(packageinfo.packageName, packageinfo);
            Slog.v("PkgInfoHook", (new StringBuilder()).append("insert ").append(packageinfo.packageName).toString());
            return true;
        }
    }

    public static final String TAG = "PkgInfoHook";
    private static volatile PkgInfoHook sInstance;
    private Map fakeData;
}
