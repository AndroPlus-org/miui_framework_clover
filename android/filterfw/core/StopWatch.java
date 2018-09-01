// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.os.SystemClock;
import android.util.Log;

class StopWatch
{

    public StopWatch(String s)
    {
        STOP_WATCH_LOGGING_PERIOD = 200;
        TAG = "MFF";
        mName = s;
        mStartTime = -1L;
        mTotalTime = 0L;
        mNumCalls = 0;
    }

    public void start()
    {
        if(mStartTime != -1L)
        {
            throw new RuntimeException("Calling start with StopWatch already running");
        } else
        {
            mStartTime = SystemClock.elapsedRealtime();
            return;
        }
    }

    public void stop()
    {
        if(mStartTime == -1L)
            throw new RuntimeException("Calling stop with StopWatch already stopped");
        long l = SystemClock.elapsedRealtime();
        mTotalTime = mTotalTime + (l - mStartTime);
        mNumCalls = mNumCalls + 1;
        mStartTime = -1L;
        if(mNumCalls % STOP_WATCH_LOGGING_PERIOD == 0)
        {
            Log.i(TAG, (new StringBuilder()).append("AVG ms/call ").append(mName).append(": ").append(String.format("%.1f", new Object[] {
                Float.valueOf(((float)mTotalTime * 1.0F) / (float)mNumCalls)
            })).toString());
            mTotalTime = 0L;
            mNumCalls = 0;
        }
    }

    private int STOP_WATCH_LOGGING_PERIOD;
    private String TAG;
    private String mName;
    private int mNumCalls;
    private long mStartTime;
    private long mTotalTime;
}
