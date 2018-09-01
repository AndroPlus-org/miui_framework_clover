// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.os.SystemClock;

// Referenced classes of package com.android.internal.util:
//            Preconditions

public class TokenBucket
{

    public TokenBucket(int i, int j)
    {
        this(i, j, j);
    }

    public TokenBucket(int i, int j, int k)
    {
        mFillDelta = Preconditions.checkArgumentPositive(i, "deltaMs must be strictly positive");
        mCapacity = Preconditions.checkArgumentPositive(j, "capacity must be strictly positive");
        mAvailable = Math.min(Preconditions.checkArgumentNonnegative(k), mCapacity);
        mLastFill = scaledTime();
    }

    private void fill()
    {
        long l = scaledTime();
        int i = (int)(l - mLastFill);
        mAvailable = Math.min(mCapacity, mAvailable + i);
        mLastFill = l;
    }

    private long scaledTime()
    {
        return SystemClock.elapsedRealtime() / (long)mFillDelta;
    }

    public int available()
    {
        fill();
        return mAvailable;
    }

    public int capacity()
    {
        return mCapacity;
    }

    public int get(int i)
    {
        fill();
        if(i <= 0)
            return 0;
        if(i > mAvailable)
        {
            i = mAvailable;
            mAvailable = 0;
            return i;
        } else
        {
            mAvailable = mAvailable - i;
            return i;
        }
    }

    public boolean get()
    {
        boolean flag = true;
        if(get(1) != 1)
            flag = false;
        return flag;
    }

    public boolean has()
    {
        boolean flag = false;
        fill();
        if(mAvailable > 0)
            flag = true;
        return flag;
    }

    public void reset(int i)
    {
        Preconditions.checkArgumentNonnegative(i);
        mAvailable = Math.min(i, mCapacity);
        mLastFill = scaledTime();
    }

    private int mAvailable;
    private final int mCapacity;
    private final int mFillDelta;
    private long mLastFill;
}
