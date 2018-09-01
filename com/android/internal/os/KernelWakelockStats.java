// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import java.util.HashMap;

public class KernelWakelockStats extends HashMap
{
    public static class Entry
    {

        public int mCount;
        public long mTotalTime;
        public int mVersion;

        Entry(int i, long l, int j)
        {
            mCount = i;
            mTotalTime = l;
            mVersion = j;
        }
    }


    public KernelWakelockStats()
    {
    }

    int kernelWakelockVersion;
}
