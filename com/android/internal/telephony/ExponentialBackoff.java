// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.Handler;
import android.os.Looper;

public class ExponentialBackoff
{

    public ExponentialBackoff(long l, long l1, int i, Handler handler, Runnable runnable)
    {
        mRetryCounter = 0;
        mStartDelayMs = l;
        mMaximumDelayMs = l1;
        mMultiplier = i;
        mHandler = handler;
        mRunnable = runnable;
    }

    public ExponentialBackoff(long l, long l1, int i, Looper looper, Runnable runnable)
    {
        this(l, l1, i, new Handler(looper), runnable);
    }

    public long getCurrentDelay()
    {
        return mCurrentDelayMs;
    }

    public void notifyFailed()
    {
        mRetryCounter = mRetryCounter + 1;
        long l = Math.min(mMaximumDelayMs, (long)((double)mStartDelayMs * Math.pow(mMultiplier, mRetryCounter)));
        mCurrentDelayMs = (long)(((Math.random() + 1.0D) / 2D) * (double)l);
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, mCurrentDelayMs);
    }

    public void start()
    {
        mRetryCounter = 0;
        mCurrentDelayMs = mStartDelayMs;
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, mCurrentDelayMs);
    }

    public void stop()
    {
        mRetryCounter = 0;
        mHandler.removeCallbacks(mRunnable);
    }

    private long mCurrentDelayMs;
    private Handler mHandler;
    private long mMaximumDelayMs;
    private int mMultiplier;
    private int mRetryCounter;
    private Runnable mRunnable;
    private long mStartDelayMs;
}
