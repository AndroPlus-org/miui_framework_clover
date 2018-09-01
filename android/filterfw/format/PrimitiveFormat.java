// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.format;

import android.filterfw.core.MutableFrameFormat;

public class PrimitiveFormat
{

    public PrimitiveFormat()
    {
    }

    public static MutableFrameFormat createByteFormat(int i)
    {
        return createFormat(2, i);
    }

    public static MutableFrameFormat createByteFormat(int i, int j)
    {
        return createFormat(2, i, j);
    }

    public static MutableFrameFormat createDoubleFormat(int i)
    {
        return createFormat(6, i);
    }

    public static MutableFrameFormat createDoubleFormat(int i, int j)
    {
        return createFormat(6, i, j);
    }

    public static MutableFrameFormat createFloatFormat(int i)
    {
        return createFormat(5, i);
    }

    public static MutableFrameFormat createFloatFormat(int i, int j)
    {
        return createFormat(5, i, j);
    }

    private static MutableFrameFormat createFormat(int i, int j)
    {
        MutableFrameFormat mutableframeformat = new MutableFrameFormat(i, j);
        mutableframeformat.setDimensionCount(1);
        return mutableframeformat;
    }

    private static MutableFrameFormat createFormat(int i, int j, int k)
    {
        MutableFrameFormat mutableframeformat = new MutableFrameFormat(i, k);
        mutableframeformat.setDimensions(j);
        return mutableframeformat;
    }

    public static MutableFrameFormat createInt16Format(int i)
    {
        return createFormat(3, i);
    }

    public static MutableFrameFormat createInt16Format(int i, int j)
    {
        return createFormat(3, i, j);
    }

    public static MutableFrameFormat createInt32Format(int i)
    {
        return createFormat(4, i);
    }

    public static MutableFrameFormat createInt32Format(int i, int j)
    {
        return createFormat(4, i, j);
    }
}
