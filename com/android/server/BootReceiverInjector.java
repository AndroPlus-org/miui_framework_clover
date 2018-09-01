// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import android.content.Context;
import miui.mqsas.sdk.MQSEventManagerDelegate;
import miui.util.SystemAnalytics;

public class BootReceiverInjector
{

    public BootReceiverInjector()
    {
    }

    public static void onBootCompleted()
    {
        MQSEventManagerDelegate.getInstance().onBootCompleted();
    }

    public static void recordBootTime(Context context)
    {
        miui.util.SystemAnalytics.Action action = new miui.util.SystemAnalytics.Action();
        action.addParam("action", "boot");
        action.addParam("time", System.currentTimeMillis());
        SystemAnalytics.trackSystem(context, "systemserver_bootshuttime", action);
    }
}
