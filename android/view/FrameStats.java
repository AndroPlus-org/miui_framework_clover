// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;


public abstract class FrameStats
{

    public FrameStats()
    {
    }

    public final long getEndTimeNano()
    {
        if(getFrameCount() <= 0)
            return -1L;
        else
            return mFramesPresentedTimeNano[mFramesPresentedTimeNano.length - 1];
    }

    public final int getFrameCount()
    {
        int i;
        if(mFramesPresentedTimeNano != null)
            i = mFramesPresentedTimeNano.length;
        else
            i = 0;
        return i;
    }

    public final long getFramePresentedTimeNano(int i)
    {
        if(mFramesPresentedTimeNano == null)
            throw new IndexOutOfBoundsException();
        else
            return mFramesPresentedTimeNano[i];
    }

    public final long getRefreshPeriodNano()
    {
        return mRefreshPeriodNano;
    }

    public final long getStartTimeNano()
    {
        if(getFrameCount() <= 0)
            return -1L;
        else
            return mFramesPresentedTimeNano[0];
    }

    public static final long UNDEFINED_TIME_NANO = -1L;
    protected long mFramesPresentedTimeNano[];
    protected long mRefreshPeriodNano;
}
