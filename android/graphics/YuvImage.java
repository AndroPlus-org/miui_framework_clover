// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import java.io.OutputStream;

// Referenced classes of package android.graphics:
//            Rect

public class YuvImage
{

    public YuvImage(byte abyte0[], int i, int j, int k, int ai[])
    {
        if(i != 17 && i != 20)
            throw new IllegalArgumentException("only support ImageFormat.NV21 and ImageFormat.YUY2 for now");
        if(j <= 0 || k <= 0)
            throw new IllegalArgumentException("width and height must large than 0");
        if(abyte0 == null)
            throw new IllegalArgumentException("yuv cannot be null");
        if(ai == null)
            mStrides = calculateStrides(j, i);
        else
            mStrides = ai;
        mData = abyte0;
        mFormat = i;
        mWidth = j;
        mHeight = k;
    }

    private void adjustRectangle(Rect rect)
    {
        int i = rect.width();
        int j = rect.height();
        int k = i;
        if(mFormat == 17)
        {
            k = i & -2;
            rect.left = rect.left & -2;
            rect.top = rect.top & -2;
            rect.right = rect.left + k;
            rect.bottom = rect.top + (j & -2);
        }
        if(mFormat == 20)
        {
            rect.left = rect.left & -2;
            rect.right = rect.left + (k & -2);
        }
    }

    private int[] calculateStrides(int i, int j)
    {
        if(j == 17)
            return (new int[] {
                i, i
            });
        if(j == 20)
            return (new int[] {
                i * 2
            });
        else
            return null;
    }

    private static native boolean nativeCompressToJpeg(byte abyte0[], int i, int j, int k, int ai[], int ai1[], int l, OutputStream outputstream, 
            byte abyte1[]);

    int[] calculateOffsets(int i, int j)
    {
        if(mFormat == 17)
            return (new int[] {
                mStrides[0] * j + i, mHeight * mStrides[0] + (j / 2) * mStrides[1] + (i / 2) * 2
            });
        if(mFormat == 20)
            return (new int[] {
                mStrides[0] * j + (i / 2) * 4
            });
        else
            return null;
    }

    public boolean compressToJpeg(Rect rect, int i, OutputStream outputstream)
    {
        if(!(new Rect(0, 0, mWidth, mHeight)).contains(rect))
            throw new IllegalArgumentException("rectangle is not inside the image");
        if(i < 0 || i > 100)
            throw new IllegalArgumentException("quality must be 0..100");
        if(outputstream == null)
        {
            throw new IllegalArgumentException("stream cannot be null");
        } else
        {
            adjustRectangle(rect);
            int ai[] = calculateOffsets(rect.left, rect.top);
            return nativeCompressToJpeg(mData, mFormat, rect.width(), rect.height(), ai, mStrides, i, outputstream, new byte[4096]);
        }
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int[] getStrides()
    {
        return mStrides;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public byte[] getYuvData()
    {
        return mData;
    }

    public int getYuvFormat()
    {
        return mFormat;
    }

    private static final int WORKING_COMPRESS_STORAGE = 4096;
    private byte mData[];
    private int mFormat;
    private int mHeight;
    private int mStrides[];
    private int mWidth;
}
