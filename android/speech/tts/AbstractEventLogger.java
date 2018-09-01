// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.os.SystemClock;

abstract class AbstractEventLogger
{

    AbstractEventLogger(int i, int j, String s)
    {
        mPlaybackStartTime = -1L;
        mRequestProcessingStartTime = -1L;
        mEngineStartTime = -1L;
        mEngineCompleteTime = -1L;
        mLogWritten = false;
        mCallerUid = i;
        mCallerPid = j;
        mServiceApp = s;
    }

    protected abstract void logFailure(int i);

    protected abstract void logSuccess(long l, long l1, long l2);

    public void onAudioDataWritten()
    {
        if(mPlaybackStartTime == -1L)
            mPlaybackStartTime = SystemClock.elapsedRealtime();
    }

    public void onCompleted(int i)
    {
        if(mLogWritten)
            return;
        mLogWritten = true;
        SystemClock.elapsedRealtime();
        while(i != 0 || mPlaybackStartTime == -1L || mEngineCompleteTime == -1L) 
        {
            logFailure(i);
            return;
        }
        logSuccess(mPlaybackStartTime - mReceivedTime, mEngineStartTime - mRequestProcessingStartTime, mEngineCompleteTime - mRequestProcessingStartTime);
    }

    public void onEngineComplete()
    {
        mEngineCompleteTime = SystemClock.elapsedRealtime();
    }

    public void onEngineDataReceived()
    {
        if(mEngineStartTime == -1L)
            mEngineStartTime = SystemClock.elapsedRealtime();
    }

    public void onRequestProcessingStart()
    {
        mRequestProcessingStartTime = SystemClock.elapsedRealtime();
    }

    protected final int mCallerPid;
    protected final int mCallerUid;
    private volatile long mEngineCompleteTime;
    private volatile long mEngineStartTime;
    private boolean mLogWritten;
    protected long mPlaybackStartTime;
    protected final long mReceivedTime = SystemClock.elapsedRealtime();
    private volatile long mRequestProcessingStartTime;
    protected final String mServiceApp;
}
