// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.hybrid.hook;

import android.text.TextUtils;
import android.util.Slog;
import java.util.HashMap;
import java.util.Map;

public class CallingPkgHook
{
    private static class Holder
    {

        static CallingPkgHook INSTANCE = new CallingPkgHook();


        private Holder()
        {
        }
    }


    public CallingPkgHook()
    {
        mHookData = new HashMap();
    }

    public static CallingPkgHook getInstance()
    {
        return Holder.INSTANCE;
    }

    public boolean add(String s, String s1, String s2)
    {
        if(TextUtils.isEmpty(s))
        {
            Slog.e("CallingPkgHook", (new StringBuilder()).append("Fail to add rule, invalid hostApp:").append(s).toString());
            return false;
        }
        if(TextUtils.isEmpty(s1))
        {
            Slog.e("CallingPkgHook", (new StringBuilder()).append("Fail to add rule, invalid originCallingPkg:").append(s1).toString());
            return false;
        }
        if(TextUtils.isEmpty(s2))
        {
            Slog.e("CallingPkgHook", (new StringBuilder()).append("Fail to add rule, invalid hookCallingPkg:").append(s2).toString());
            return false;
        }
        Map map = mHookData;
        map;
        JVM INSTR monitorenter ;
        Map map1 = (Map)mHookData.get(s);
        Object obj;
        obj = map1;
        if(map1 != null)
            break MISSING_BLOCK_LABEL_158;
        obj = JVM INSTR new #20  <Class HashMap>;
        ((HashMap) (obj)).HashMap();
        mHookData.put(s, obj);
        ((Map) (obj)).put(s1, s2);
        map;
        JVM INSTR monitorexit ;
        Slog.v("CallingPkgHook", (new StringBuilder()).append("Add hook callingPkg success, hostApp:").append(s).append(" originCallingPkg:").append(s1).append(" hookCallingPkg:").append(s2).toString());
        return true;
        s;
        throw s;
    }

    public String getHookCallingPkg(String s, String s1)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        Map map = mHookData;
        map;
        JVM INSTR monitorenter ;
        Object obj = (Map)mHookData.get(s);
        if(obj == null || s1 == null)
            break MISSING_BLOCK_LABEL_113;
        String s2;
        if(!((Map) (obj)).containsKey(s1))
            break MISSING_BLOCK_LABEL_113;
        s2 = (String)((Map) (obj)).get(s1);
        obj = JVM INSTR new #40  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.v("CallingPkgHook", ((StringBuilder) (obj)).append("MIUILOG-HybridPlatform hook getCallingPkg, ").append(s1).append("->").append(s2).append(", hostApp:").append(s).toString());
        map;
        JVM INSTR monitorexit ;
        return s2;
        map;
        JVM INSTR monitorexit ;
_L2:
        return s1;
        s;
        throw s;
    }

    public boolean remove(String s, String s1)
    {
        if(TextUtils.isEmpty(s))
        {
            Slog.e("CallingPkgHook", (new StringBuilder()).append("Fail to remove rule, invalid hostApp:").append(s).toString());
            return false;
        }
        if(TextUtils.isEmpty(s1))
        {
            Slog.e("CallingPkgHook", (new StringBuilder()).append("Fail to remove rule, invalid originCallingPkg:").append(s1).toString());
            return false;
        }
        Map map = mHookData;
        map;
        JVM INSTR monitorenter ;
        Map map1 = (Map)mHookData.get(s);
        if(map1 != null)
            break MISSING_BLOCK_LABEL_126;
        s1 = JVM INSTR new #40  <Class StringBuilder>;
        s1.StringBuilder();
        Slog.v("CallingPkgHook", s1.append("Fail to remove rule, pkgNameMap not found by hostApp:").append(s).toString());
        map;
        JVM INSTR monitorexit ;
        return false;
        String s2;
        s2 = (String)map1.remove(s1);
        if(map1.isEmpty())
        {
            StringBuilder stringbuilder = JVM INSTR new #40  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Slog.v("CallingPkgHook", stringbuilder.append("All rule for hostApp:").append(s).append(" removed.").toString());
            mHookData.remove(s);
        }
        map;
        JVM INSTR monitorexit ;
        if(s2 == null)
        {
            Slog.v("CallingPkgHook", (new StringBuilder()).append("Fail to remove rule, hookCallingPkg not found by originCallingPkg:").append(s1).append(",").append(" hostApp:").append(s).toString());
            return false;
        } else
        {
            Slog.v("CallingPkgHook", (new StringBuilder()).append("Remove rule success, hostApp:").append(s).append(", ").append("originCallingPkg:").append(s1).append(", ").append("hookCallingPkg:").append(s2).toString());
            return true;
        }
        s;
        throw s;
    }

    public static final String TAG = "CallingPkgHook";
    private Map mHookData;
}
