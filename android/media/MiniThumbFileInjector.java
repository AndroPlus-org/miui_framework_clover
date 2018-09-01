// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.nio.ByteBuffer;

class MiniThumbFileInjector
{

    MiniThumbFileInjector()
    {
    }

    public static long getPosition(long l)
    {
        return (l % 5000L) * 10000L;
    }

    public static boolean isMatch(ByteBuffer bytebuffer, long l)
    {
        boolean flag = true;
        if(bytebuffer.get() != 1 || l != bytebuffer.getLong())
            flag = false;
        return flag;
    }

    private static final int MAX_MINI_THUMB_COUNT = 5000;
}
