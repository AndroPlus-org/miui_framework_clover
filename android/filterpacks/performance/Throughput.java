// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.performance;


public class Throughput
{

    public Throughput(int i, int j, int k, int l)
    {
        mTotalFrames = i;
        mPeriodFrames = j;
        mPeriodTime = k;
        mPixels = l;
    }

    public float getFramesPerSecond()
    {
        return (float)mPeriodFrames / (float)mPeriodTime;
    }

    public float getNanosPerPixel()
    {
        return (float)((((double)mPeriodTime / (double)mPeriodFrames) * 1000000D) / (double)mPixels);
    }

    public int getPeriodFrameCount()
    {
        return mPeriodFrames;
    }

    public int getPeriodTime()
    {
        return mPeriodTime;
    }

    public int getTotalFrameCount()
    {
        return mTotalFrames;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getFramesPerSecond()).append(" FPS").toString();
    }

    private final int mPeriodFrames;
    private final int mPeriodTime;
    private final int mPixels;
    private final int mTotalFrames;
}
