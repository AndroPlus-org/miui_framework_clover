// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.logging;

import android.content.Context;
import android.metrics.LogMaker;
import android.os.Build;

// Referenced classes of package com.android.internal.logging:
//            EventLogTags

public class MetricsLogger
{

    public MetricsLogger()
    {
    }

    public static void action(Context context, int i)
    {
        getLogger().action(i);
    }

    public static void action(Context context, int i, int j)
    {
        getLogger().action(i, j);
    }

    public static void action(Context context, int i, String s)
    {
        getLogger().action(i, s);
    }

    public static void action(Context context, int i, boolean flag)
    {
        getLogger().action(i, flag);
    }

    public static void action(LogMaker logmaker)
    {
        getLogger().write(logmaker);
    }

    public static void count(Context context, String s, int i)
    {
        getLogger().count(s, i);
    }

    private static MetricsLogger getLogger()
    {
        if(sMetricsLogger == null)
            sMetricsLogger = new MetricsLogger();
        return sMetricsLogger;
    }

    public static void hidden(Context context, int i)
        throws IllegalArgumentException
    {
        getLogger().hidden(i);
    }

    public static void histogram(Context context, String s, int i)
    {
        getLogger().histogram(s, i);
    }

    public static void visibility(Context context, int i, int j)
        throws IllegalArgumentException
    {
        boolean flag = false;
        if(j == 0)
            flag = true;
        visibility(context, i, flag);
    }

    public static void visibility(Context context, int i, boolean flag)
        throws IllegalArgumentException
    {
        getLogger().visibility(i, flag);
    }

    public static void visible(Context context, int i)
        throws IllegalArgumentException
    {
        getLogger().visible(i);
    }

    public void action(int i)
    {
        EventLogTags.writeSysuiAction(i, "");
        saveLog((new LogMaker(i)).setType(4).serialize());
    }

    public void action(int i, int j)
    {
        EventLogTags.writeSysuiAction(i, Integer.toString(j));
        saveLog((new LogMaker(i)).setType(4).setSubtype(j).serialize());
    }

    public void action(int i, String s)
    {
        if(Build.IS_DEBUGGABLE && i == 0)
        {
            throw new IllegalArgumentException("Must define metric category");
        } else
        {
            EventLogTags.writeSysuiAction(i, s);
            saveLog((new LogMaker(i)).setType(4).setPackageName(s).serialize());
            return;
        }
    }

    public void action(int i, boolean flag)
    {
        EventLogTags.writeSysuiAction(i, Boolean.toString(flag));
        LogMaker logmaker = (new LogMaker(i)).setType(4);
        if(flag)
            i = 1;
        else
            i = 0;
        saveLog(logmaker.setSubtype(i).serialize());
    }

    public void count(String s, int i)
    {
        EventLogTags.writeSysuiCount(s, i);
        saveLog((new LogMaker(803)).setCounterName(s).setCounterValue(i).serialize());
    }

    public void hidden(int i)
        throws IllegalArgumentException
    {
        if(Build.IS_DEBUGGABLE && i == 0)
        {
            throw new IllegalArgumentException("Must define metric category");
        } else
        {
            EventLogTags.writeSysuiViewVisibility(i, 0);
            saveLog((new LogMaker(i)).setType(2).serialize());
            return;
        }
    }

    public void histogram(String s, int i)
    {
        EventLogTags.writeSysuiHistogram(s, i);
        saveLog((new LogMaker(804)).setCounterName(s).setCounterBucket(i).setCounterValue(1).serialize());
    }

    protected void saveLog(Object aobj[])
    {
        EventLogTags.writeSysuiMultiAction(aobj);
    }

    public void visibility(int i, int j)
        throws IllegalArgumentException
    {
        boolean flag = false;
        if(j == 0)
            flag = true;
        visibility(i, flag);
    }

    public void visibility(int i, boolean flag)
        throws IllegalArgumentException
    {
        if(flag)
            visible(i);
        else
            hidden(i);
    }

    public void visible(int i)
        throws IllegalArgumentException
    {
        if(Build.IS_DEBUGGABLE && i == 0)
        {
            throw new IllegalArgumentException("Must define metric category");
        } else
        {
            EventLogTags.writeSysuiViewVisibility(i, 100);
            saveLog((new LogMaker(i)).setType(1).serialize());
            return;
        }
    }

    public void write(LogMaker logmaker)
    {
        if(logmaker.getType() == 0)
            logmaker.setType(4);
        saveLog(logmaker.serialize());
    }

    public static final int LOGTAG = 0x80004;
    public static final int VIEW_UNKNOWN = 0;
    private static MetricsLogger sMetricsLogger;
}
