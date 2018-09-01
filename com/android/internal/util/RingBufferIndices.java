// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public class RingBufferIndices
{

    public RingBufferIndices(int i)
    {
        mCapacity = i;
    }

    public int add()
    {
        if(mSize < mCapacity)
        {
            int i = mSize;
            mSize = mSize + 1;
            return i;
        }
        int j = mStart;
        mStart = mStart + 1;
        if(mStart == mCapacity)
            mStart = 0;
        return j;
    }

    public void clear()
    {
        mStart = 0;
        mSize = 0;
    }

    public int indexOf(int i)
    {
        int j = mStart + i;
        i = j;
        if(j >= mCapacity)
            i = j - mCapacity;
        return i;
    }

    public int size()
    {
        return mSize;
    }

    private final int mCapacity;
    private int mSize;
    private int mStart;
}
