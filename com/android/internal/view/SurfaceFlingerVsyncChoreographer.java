// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.Handler;
import android.os.Message;
import android.view.Choreographer;
import android.view.Display;

public class SurfaceFlingerVsyncChoreographer
{

    public SurfaceFlingerVsyncChoreographer(Handler handler, Display display, Choreographer choreographer)
    {
        mHandler = handler;
        mChoreographer = choreographer;
        mSurfaceFlingerOffsetMs = calculateAppSurfaceFlingerVsyncOffsetMs(display);
    }

    private long calculateAppSurfaceFlingerVsyncOffsetMs(Display display)
    {
        return Math.max(0L, ((long)(1E+009F / display.getRefreshRate()) - (display.getPresentationDeadlineNanos() - 0xf4240L) - display.getAppVsyncOffsetNanos()) / 0xf4240L);
    }

    private long calculateDelay()
    {
        long l = System.nanoTime();
        long l1 = mChoreographer.getLastFrameTimeNanos();
        return mSurfaceFlingerOffsetMs - (l - l1) / 0xf4240L;
    }

    public long getSurfaceFlingerOffsetMs()
    {
        return mSurfaceFlingerOffsetMs;
    }

    public void scheduleAtSfVsync(Handler handler, Message message)
    {
        long l = calculateDelay();
        if(l <= 0L)
        {
            handler.handleMessage(message);
        } else
        {
            message.setAsynchronous(true);
            handler.sendMessageDelayed(message, l);
        }
    }

    public void scheduleAtSfVsync(Runnable runnable)
    {
        long l = calculateDelay();
        if(l <= 0L)
            runnable.run();
        else
            mHandler.postDelayed(runnable, l);
    }

    private static final long ONE_MS_IN_NS = 0xf4240L;
    private static final long ONE_S_IN_NS = 0x3b9aca00L;
    private final Choreographer mChoreographer;
    private final Handler mHandler;
    private long mSurfaceFlingerOffsetMs;
}
