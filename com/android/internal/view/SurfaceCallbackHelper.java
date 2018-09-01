// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.view.SurfaceHolder;

public class SurfaceCallbackHelper
{

    public SurfaceCallbackHelper(Runnable runnable)
    {
        mFinishDrawingCollected = 0;
        mFinishDrawingExpected = 0;
        mFinishDrawingRunnable = new Runnable() {

            public void run()
            {
                SurfaceCallbackHelper surfacecallbackhelper = SurfaceCallbackHelper.this;
                surfacecallbackhelper;
                JVM INSTR monitorenter ;
                int i;
                int j;
                SurfaceCallbackHelper surfacecallbackhelper1 = SurfaceCallbackHelper.this;
                surfacecallbackhelper1.mFinishDrawingCollected = surfacecallbackhelper1.mFinishDrawingCollected + 1;
                i = mFinishDrawingCollected;
                j = mFinishDrawingExpected;
                if(i >= j)
                    break MISSING_BLOCK_LABEL_48;
                surfacecallbackhelper;
                JVM INSTR monitorexit ;
                return;
                mRunnable.run();
                surfacecallbackhelper;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

            final SurfaceCallbackHelper this$0;

            
            {
                this$0 = SurfaceCallbackHelper.this;
                super();
            }
        }
;
        mRunnable = runnable;
    }

    public void dispatchSurfaceRedrawNeededAsync(SurfaceHolder surfaceholder, android.view.SurfaceHolder.Callback acallback[])
    {
        int i;
        i = 0;
        if(acallback == null || acallback.length == 0)
        {
            mRunnable.run();
            return;
        }
        this;
        JVM INSTR monitorenter ;
        mFinishDrawingExpected = acallback.length;
        mFinishDrawingCollected = 0;
        this;
        JVM INSTR monitorexit ;
        int j = acallback.length;
        while(i < j) 
        {
            android.view.SurfaceHolder.Callback callback = acallback[i];
            if(callback instanceof android.view.SurfaceHolder.Callback2)
                ((android.view.SurfaceHolder.Callback2)callback).surfaceRedrawNeededAsync(surfaceholder, mFinishDrawingRunnable);
            else
                mFinishDrawingRunnable.run();
            i++;
        }
        break MISSING_BLOCK_LABEL_97;
        surfaceholder;
        throw surfaceholder;
    }

    int mFinishDrawingCollected;
    int mFinishDrawingExpected;
    private Runnable mFinishDrawingRunnable;
    Runnable mRunnable;
}
