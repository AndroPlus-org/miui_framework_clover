// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.IBinder;
import android.os.SystemClock;
import android.util.EventLog;
import dalvik.system.VMRuntime;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class BinderInternal
{
    static final class GcWatcher
    {

        protected void finalize()
            throws Throwable
        {
            BinderInternal.handleGc();
            BinderInternal.sLastGcTime = SystemClock.uptimeMillis();
            ArrayList arraylist = BinderInternal.sGcWatchers;
            arraylist;
            JVM INSTR monitorenter ;
            BinderInternal.sTmpWatchers = (Runnable[])BinderInternal.sGcWatchers.toArray(BinderInternal.sTmpWatchers);
            arraylist;
            JVM INSTR monitorexit ;
            for(int i = 0; i < BinderInternal.sTmpWatchers.length; i++)
                if(BinderInternal.sTmpWatchers[i] != null)
                    BinderInternal.sTmpWatchers[i].run();

            break MISSING_BLOCK_LABEL_71;
            Exception exception;
            exception;
            throw exception;
            BinderInternal.sGcWatcher = new WeakReference(new GcWatcher());
            return;
        }

        GcWatcher()
        {
        }
    }


    public BinderInternal()
    {
    }

    public static void addGcWatcher(Runnable runnable)
    {
        ArrayList arraylist = sGcWatchers;
        arraylist;
        JVM INSTR monitorenter ;
        sGcWatchers.add(runnable);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    public static final native void disableBackgroundScheduling(boolean flag);

    static void forceBinderGc()
    {
        forceGc("Binder");
    }

    public static void forceGc(String s)
    {
        EventLog.writeEvent(2741, s);
        VMRuntime.getRuntime().requestConcurrentGC();
    }

    public static final native IBinder getContextObject();

    public static long getLastGcTime()
    {
        return sLastGcTime;
    }

    static final native void handleGc();

    public static final native void joinThreadPool();

    public static final native void setMaxThreads(int i);

    static WeakReference sGcWatcher = new WeakReference(new GcWatcher());
    static ArrayList sGcWatchers = new ArrayList();
    static long sLastGcTime;
    static Runnable sTmpWatchers[] = new Runnable[1];

}
