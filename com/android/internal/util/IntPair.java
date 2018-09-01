// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public class IntPair
{

    private IntPair()
    {
    }

    public static int first(long l)
    {
        return (int)(l >> 32);
    }

    public static long of(int i, int j)
    {
        return (long)i << 32 | (long)j & 0xffffffffL;
    }

    public static int second(long l)
    {
        return (int)l;
    }
}
