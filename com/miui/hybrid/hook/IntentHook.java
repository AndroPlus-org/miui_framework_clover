// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.hybrid.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Slog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IntentHook
{
    private static class RedirectRule
    {

        String callingPkg;
        Bundle clsNameMap;
        String originDestPkg;
        String redirectPkg;

        private RedirectRule()
        {
        }

        RedirectRule(RedirectRule redirectrule)
        {
            this();
        }
    }


    private IntentHook()
    {
        ruleMap = new ConcurrentHashMap();
    }

    public static IntentHook getInstance()
    {
        if(sInstance != null) goto _L2; else goto _L1
_L1:
        com/miui/hybrid/hook/IntentHook;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            IntentHook intenthook = JVM INSTR new #2   <Class IntentHook>;
            intenthook.IntentHook();
            sInstance = intenthook;
        }
        com/miui/hybrid/hook/IntentHook;
        JVM INSTR monitorexit ;
_L2:
        return sInstance;
        Exception exception;
        exception;
        throw exception;
    }

    private RedirectRule getRedirectRule(Intent intent, String s)
    {
        if(intent == null)
            return null;
        if(TextUtils.isEmpty(s))
            return null;
        intent = intent.getComponent();
        if(intent == null)
            return null;
        if(TextUtils.isEmpty(intent.getPackageName()))
            return null;
        s = (Map)ruleMap.get(s);
        if(s == null)
            return null;
        else
            return (RedirectRule)s.get(intent.getPackageName());
    }

    public String delete(String s, String s1)
    {
        Object obj = null;
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
        {
            Slog.e("IntentHook", (new StringBuilder()).append("IntentHook.delete(").append(s).append(", ").append(s1).append(") failed.").toString());
            return null;
        }
        s = (Map)ruleMap.get(s);
        if(s != null)
        {
            RedirectRule redirectrule = (RedirectRule)s.remove(s1);
            Slog.v("IntentHook", (new StringBuilder()).append("remove ").append(s1).append(" redirectRule:").append(redirectrule).toString());
            s = obj;
            if(redirectrule != null)
                s = redirectrule.redirectPkg;
            return s;
        } else
        {
            return null;
        }
    }

    public boolean insert(String s, String s1, String s2, Bundle bundle)
    {
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2))
        {
            Slog.e("IntentHook", (new StringBuilder()).append("IntentHook.insert(").append(s).append(", ").append(s1).append(", ").append(s2).append(", ").append(bundle).append(") failed.").toString());
            return false;
        }
        RedirectRule redirectrule = new RedirectRule(null);
        redirectrule.callingPkg = s;
        redirectrule.originDestPkg = s1;
        redirectrule.redirectPkg = s2;
        redirectrule.clsNameMap = bundle;
        Map map = (Map)ruleMap.get(s);
        bundle = map;
        if(map == null)
        {
            bundle = new ConcurrentHashMap();
            ruleMap.put(s, bundle);
        }
        bundle.put(s1, redirectrule);
        Slog.v("IntentHook", (new StringBuilder()).append("insert rule ").append(s1).append(" -> ").append(s2).append(" for ").append(s).toString());
        return true;
    }

    public Intent redirect(Intent intent, String s)
    {
        Object obj = getRedirectRule(intent, s);
        if(obj == null)
            return intent;
        Object obj1 = intent.getComponent();
        s = ((ComponentName) (obj1)).getPackageName();
        obj1 = ((ComponentName) (obj1)).getClassName();
        String s1 = ((RedirectRule) (obj)).redirectPkg;
        Slog.d("IntentHook", (new StringBuilder()).append("redirect intent: package ").append(s).append(" -> ").append(s1).toString());
        s = ((String) (obj1));
        if(((RedirectRule) (obj)).clsNameMap != null)
        {
            obj = ((RedirectRule) (obj)).clsNameMap.getString(((String) (obj1)));
            if(!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                s = ((String) (obj));
                Slog.d("IntentHook", (new StringBuilder()).append("redirect intent: class ").append(((String) (obj1))).append(" -> ").append(((String) (obj))).toString());
            } else
            {
                Slog.d("IntentHook", (new StringBuilder()).append("ignore class part, ").append(((String) (obj))).append(" is not a valid className.").toString());
            }
        } else
        {
            Slog.d("IntentHook", "ignore class part, rule.clsMapper is null.");
        }
        intent.setComponent(new ComponentName(s1, s));
        return intent;
    }

    public static final String TAG = "IntentHook";
    private static volatile IntentHook sInstance;
    private Map ruleMap;
}
