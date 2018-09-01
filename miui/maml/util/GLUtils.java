// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import java.nio.*;

public class GLUtils
{

    public GLUtils()
    {
    }

    public static Buffer buildBuffer(byte abyte0[])
    {
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(abyte0.length);
        bytebuffer.order(ByteOrder.nativeOrder());
        bytebuffer.put(abyte0);
        bytebuffer.position(0);
        return bytebuffer;
    }

    public static Buffer buildBuffer(char ac[])
    {
        Object obj = ByteBuffer.allocateDirect(ac.length);
        ((ByteBuffer) (obj)).order(ByteOrder.nativeOrder());
        obj = ((ByteBuffer) (obj)).asCharBuffer();
        ((CharBuffer) (obj)).put(ac);
        ((CharBuffer) (obj)).position(0);
        return ((Buffer) (obj));
    }

    public static Buffer buildBuffer(double ad[])
    {
        Object obj = ByteBuffer.allocateDirect(ad.length * 8);
        ((ByteBuffer) (obj)).order(ByteOrder.nativeOrder());
        obj = ((ByteBuffer) (obj)).asDoubleBuffer();
        ((DoubleBuffer) (obj)).put(ad);
        ((DoubleBuffer) (obj)).position(0);
        return ((Buffer) (obj));
    }

    public static Buffer buildBuffer(float af[])
    {
        Object obj = ByteBuffer.allocateDirect(af.length * 4);
        ((ByteBuffer) (obj)).order(ByteOrder.nativeOrder());
        obj = ((ByteBuffer) (obj)).asFloatBuffer();
        ((FloatBuffer) (obj)).put(af);
        ((FloatBuffer) (obj)).position(0);
        return ((Buffer) (obj));
    }

    public static Buffer buildBuffer(int ai[])
    {
        Object obj = ByteBuffer.allocateDirect(ai.length * 4);
        ((ByteBuffer) (obj)).order(ByteOrder.nativeOrder());
        obj = ((ByteBuffer) (obj)).asIntBuffer();
        ((IntBuffer) (obj)).put(ai);
        ((IntBuffer) (obj)).position(0);
        return ((Buffer) (obj));
    }

    public static Buffer buildBuffer(long al[])
    {
        Object obj = ByteBuffer.allocateDirect(al.length * 8);
        ((ByteBuffer) (obj)).order(ByteOrder.nativeOrder());
        obj = ((ByteBuffer) (obj)).asLongBuffer();
        ((LongBuffer) (obj)).put(al);
        ((LongBuffer) (obj)).position(0);
        return ((Buffer) (obj));
    }

    public static Buffer buildBuffer(short aword0[])
    {
        Object obj = ByteBuffer.allocateDirect(aword0.length * 2);
        ((ByteBuffer) (obj)).order(ByteOrder.nativeOrder());
        obj = ((ByteBuffer) (obj)).asShortBuffer();
        ((ShortBuffer) (obj)).put(aword0);
        ((ShortBuffer) (obj)).position(0);
        return ((Buffer) (obj));
    }
}
