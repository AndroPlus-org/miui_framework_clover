// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.format;

import android.filterfw.core.MutableFrameFormat;
import android.graphics.Bitmap;

public class ImageFormat
{

    public ImageFormat()
    {
    }

    public static int bytesPerSampleForColorspace(int i)
    {
        switch(i)
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("Unknown colorspace id ").append(i).append("!").toString());

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 3;

        case 3: // '\003'
            return 4;

        case 4: // '\004'
            return 3;
        }
    }

    public static MutableFrameFormat create(int i)
    {
        return create(0, 0, i, bytesPerSampleForColorspace(i), 0);
    }

    public static MutableFrameFormat create(int i, int j)
    {
        return create(0, 0, i, bytesPerSampleForColorspace(i), j);
    }

    public static MutableFrameFormat create(int i, int j, int k, int l)
    {
        return create(i, j, k, bytesPerSampleForColorspace(k), l);
    }

    public static MutableFrameFormat create(int i, int j, int k, int l, int i1)
    {
        MutableFrameFormat mutableframeformat = new MutableFrameFormat(2, i1);
        mutableframeformat.setDimensions(i, j);
        mutableframeformat.setBytesPerSample(l);
        mutableframeformat.setMetaValue("colorspace", Integer.valueOf(k));
        if(i1 == 1)
            mutableframeformat.setObjectClass(android/graphics/Bitmap);
        return mutableframeformat;
    }

    public static final int COLORSPACE_GRAY = 1;
    public static final String COLORSPACE_KEY = "colorspace";
    public static final int COLORSPACE_RGB = 2;
    public static final int COLORSPACE_RGBA = 3;
    public static final int COLORSPACE_YUV = 4;
}
