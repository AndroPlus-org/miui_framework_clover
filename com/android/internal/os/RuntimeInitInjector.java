// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.Build;
import miui.mqsas.sdk.MQSEventManagerDelegate;
import miui.mqsas.sdk.event.JavaExceptionEvent;

public class RuntimeInitInjector
{

    public RuntimeInitInjector()
    {
    }

    public static String getDefaultUserAgent()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        stringbuilder.append("Dalvik/");
        stringbuilder.append(System.getProperty("java.vm.version"));
        stringbuilder.append(" (Linux; U; Android ");
        String s = android.os.Build.VERSION.RELEASE;
        if(s.length() <= 0)
            s = "1.0";
        stringbuilder.append(s);
        if("REL".equals(android.os.Build.VERSION.CODENAME))
        {
            s = Build.MODEL;
            if(s.length() > 0)
            {
                stringbuilder.append("; ");
                stringbuilder.append(s);
            }
        }
        s = android.os.Build.VERSION.INCREMENTAL;
        if(s.length() > 0)
        {
            stringbuilder.append(" MIUI/");
            stringbuilder.append(s);
        }
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public static void onJE(int i, String s, String s1, String s2, Throwable throwable, String s3, boolean flag)
    {
        android.app.ApplicationErrorReport.CrashInfo crashinfo = new android.app.ApplicationErrorReport.CrashInfo(throwable);
        throwable = new JavaExceptionEvent();
        throwable.setPid(i);
        throwable.setProcessName(s);
        throwable.setPackageName(s1);
        throwable.setTimeStamp(System.currentTimeMillis());
        throwable.setThreadName(s2);
        throwable.setPrefix(s3);
        throwable.setSystem(flag);
        throwable.setStackTrace(crashinfo.stackTrace);
        throwable.setExceptionClassName(crashinfo.exceptionClassName);
        throwable.setExceptionMessage(crashinfo.exceptionMessage);
        MQSEventManagerDelegate.getInstance().reportJavaExceptionEvent(throwable);
    }
}
