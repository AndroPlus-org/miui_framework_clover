// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.logging.testing;

import android.metrics.LogMaker;
import com.android.internal.logging.MetricsLogger;
import java.util.LinkedList;
import java.util.Queue;

public class FakeMetricsLogger extends MetricsLogger
{

    public FakeMetricsLogger()
    {
        logs = new LinkedList();
    }

    public Queue getLogs()
    {
        return logs;
    }

    public void reset()
    {
        logs.clear();
    }

    protected void saveLog(Object aobj[])
    {
        logs.offer(new LogMaker(aobj));
    }

    private Queue logs;
}
