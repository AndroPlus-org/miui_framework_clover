// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.logging;

import android.util.EventLog;

public class EventLogTags
{

    private EventLogTags()
    {
    }

    public static void writeSysuiAction(int i, String s)
    {
        EventLog.writeEvent(0x80000, new Object[] {
            Integer.valueOf(i), s
        });
    }

    public static void writeSysuiCount(String s, int i)
    {
        EventLog.writeEvent(0x80002, new Object[] {
            s, Integer.valueOf(i)
        });
    }

    public static void writeSysuiHistogram(String s, int i)
    {
        EventLog.writeEvent(0x80003, new Object[] {
            s, Integer.valueOf(i)
        });
    }

    public static void writeSysuiMultiAction(Object aobj[])
    {
        EventLog.writeEvent(0x80004, aobj);
    }

    public static void writeSysuiViewVisibility(int i, int j)
    {
        EventLog.writeEvent(0x7ffff, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
    }

    public static final int SYSUI_ACTION = 0x80000;
    public static final int SYSUI_COUNT = 0x80002;
    public static final int SYSUI_HISTOGRAM = 0x80003;
    public static final int SYSUI_MULTI_ACTION = 0x80004;
    public static final int SYSUI_VIEW_VISIBILITY = 0x7ffff;
}
