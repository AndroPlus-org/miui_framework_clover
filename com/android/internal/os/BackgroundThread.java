// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;

public final class BackgroundThread extends HandlerThread
{

    private BackgroundThread()
    {
        super("android.bg", 10);
    }

    private static void ensureThreadLocked()
    {
        if(sInstance == null)
        {
            sInstance = new BackgroundThread();
            sInstance.start();
            sInstance.getLooper().setTraceTag(64L);
            sHandler = new Handler(sInstance.getLooper());
        }
    }

    public static BackgroundThread get()
    {
        com/android/internal/os/BackgroundThread;
        JVM INSTR monitorenter ;
        BackgroundThread backgroundthread;
        ensureThreadLocked();
        backgroundthread = sInstance;
        com/android/internal/os/BackgroundThread;
        JVM INSTR monitorexit ;
        return backgroundthread;
        Exception exception;
        exception;
        throw exception;
    }

    public static Handler getHandler()
    {
        com/android/internal/os/BackgroundThread;
        JVM INSTR monitorenter ;
        Handler handler;
        ensureThreadLocked();
        handler = sHandler;
        com/android/internal/os/BackgroundThread;
        JVM INSTR monitorexit ;
        return handler;
        Exception exception;
        exception;
        throw exception;
    }

    private static Handler sHandler;
    private static BackgroundThread sInstance;
}
