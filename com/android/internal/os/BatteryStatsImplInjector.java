// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;


public class BatteryStatsImplInjector
{

    public BatteryStatsImplInjector()
    {
    }

    public static String getProcessName(String s)
    {
        if(s != null)
            return s.split("#for#")[0].trim();
        else
            return s;
    }
}
