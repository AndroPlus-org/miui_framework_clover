// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.os.*;
import android.util.MathUtils;

public class ProgressReporter
{

    public ProgressReporter(int i)
    {
        mState = 0;
        mProgress = 0;
        mExtras = new Bundle();
        mId = i;
    }

    private void notifyFinished(int i, Bundle bundle)
    {
        int j = mListeners.beginBroadcast() - 1;
        while(j >= 0) 
        {
            try
            {
                ((IProgressListener)mListeners.getBroadcastItem(j)).onFinished(i, bundle);
            }
            catch(RemoteException remoteexception) { }
            j--;
        }
        mListeners.finishBroadcast();
    }

    private void notifyProgress(int i, int j, Bundle bundle)
    {
        int k = mListeners.beginBroadcast() - 1;
        while(k >= 0) 
        {
            try
            {
                ((IProgressListener)mListeners.getBroadcastItem(k)).onProgress(i, j, bundle);
            }
            catch(RemoteException remoteexception) { }
            k--;
        }
        mListeners.finishBroadcast();
    }

    private void notifyStarted(int i, Bundle bundle)
    {
        int j = mListeners.beginBroadcast() - 1;
        while(j >= 0) 
        {
            try
            {
                ((IProgressListener)mListeners.getBroadcastItem(j)).onStarted(i, bundle);
            }
            catch(RemoteException remoteexception) { }
            j--;
        }
        mListeners.finishBroadcast();
    }

    public void addListener(IProgressListener iprogresslistener)
    {
        if(iprogresslistener == null)
            return;
        this;
        JVM INSTR monitorenter ;
        int i;
        mListeners.register(iprogresslistener);
        i = mState;
        i;
        JVM INSTR tableswitch 0 2: default 48
    //                   0 48
    //                   1 51
    //                   2 87;
           goto _L1 _L1 _L2 _L3
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        try
        {
            iprogresslistener.onStarted(mId, null);
            iprogresslistener.onProgress(mId, mProgress, mExtras);
        }
        // Misplaced declaration of an exception variable
        catch(IProgressListener iprogresslistener) { }
        continue; /* Loop/switch isn't completed */
_L3:
        try
        {
            iprogresslistener.onFinished(mId, null);
        }
        // Misplaced declaration of an exception variable
        catch(IProgressListener iprogresslistener) { }
        if(true) goto _L1; else goto _L4
_L4:
        iprogresslistener;
        throw iprogresslistener;
    }

    public void endSegment(int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        mProgress = mSegmentRange[0] + mSegmentRange[1];
        mSegmentRange = ai;
        this;
        JVM INSTR monitorexit ;
        return;
        ai;
        throw ai;
    }

    public void finish()
    {
        this;
        JVM INSTR monitorenter ;
        mState = 2;
        notifyFinished(mId, null);
        mListeners.kill();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    int getProgress()
    {
        return mProgress;
    }

    int[] getSegmentRange()
    {
        return mSegmentRange;
    }

    public void setProgress(int i)
    {
        setProgress(i, 100, null);
    }

    public void setProgress(int i, int j)
    {
        setProgress(i, j, null);
    }

    public void setProgress(int i, int j, CharSequence charsequence)
    {
        this;
        JVM INSTR monitorenter ;
        if(mState != 1)
        {
            charsequence = JVM INSTR new #101 <Class IllegalStateException>;
            charsequence.IllegalStateException("Must be started to change progress");
            throw charsequence;
        }
        break MISSING_BLOCK_LABEL_27;
        charsequence;
        this;
        JVM INSTR monitorexit ;
        throw charsequence;
        mProgress = mSegmentRange[0] + MathUtils.constrain((mSegmentRange[1] * i) / j, 0, mSegmentRange[1]);
        if(charsequence == null)
            break MISSING_BLOCK_LABEL_72;
        mExtras.putCharSequence("android.intent.extra.TITLE", charsequence);
        notifyProgress(mId, mProgress, mExtras);
        this;
        JVM INSTR monitorexit ;
    }

    public void setProgress(int i, CharSequence charsequence)
    {
        setProgress(i, 100, charsequence);
    }

    public void start()
    {
        this;
        JVM INSTR monitorenter ;
        mState = 1;
        notifyStarted(mId, null);
        notifyProgress(mId, mProgress, mExtras);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int[] startSegment(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int ai[];
        ai = mSegmentRange;
        mSegmentRange = (new int[] {
            mProgress, (mSegmentRange[1] * i) / 100
        });
        this;
        JVM INSTR monitorexit ;
        return ai;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int STATE_FINISHED = 2;
    private static final int STATE_INIT = 0;
    private static final int STATE_STARTED = 1;
    private Bundle mExtras;
    private final int mId;
    private final RemoteCallbackList mListeners = new RemoteCallbackList();
    private int mProgress;
    private int mSegmentRange[] = {
        0, 100
    };
    private int mState;
}
