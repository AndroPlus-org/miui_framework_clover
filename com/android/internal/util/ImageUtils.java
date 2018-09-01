// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageUtils
{

    public ImageUtils()
    {
    }

    public static Bitmap buildScaledBitmap(Drawable drawable, int i, int j)
    {
        if(drawable == null)
            return null;
        int k = drawable.getIntrinsicWidth();
        int l = drawable.getIntrinsicHeight();
        if(k <= i && l <= j && (drawable instanceof BitmapDrawable))
            return ((BitmapDrawable)drawable).getBitmap();
        if(l <= 0 || k <= 0)
        {
            return null;
        } else
        {
            float f = Math.min(1.0F, Math.min((float)i / (float)k, (float)j / (float)l));
            i = (int)((float)k * f);
            j = (int)((float)l * f);
            Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, i, j);
            drawable.draw(canvas);
            return bitmap;
        }
    }

    private void ensureBufferSize(int i)
    {
        if(mTempBuffer == null || mTempBuffer.length < i)
            mTempBuffer = new int[i];
    }

    public static boolean isGrayscale(int i)
    {
        boolean flag = true;
        if((i >> 24 & 0xff) < 50)
            return true;
        int j = i >> 16 & 0xff;
        int k = i >> 8 & 0xff;
        i &= 0xff;
        if(Math.abs(j - k) < 20 && Math.abs(j - i) < 20)
        {
            if(Math.abs(k - i) >= 20)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean isGrayscale(Bitmap bitmap)
    {
        int l;
        int i1;
        Bitmap bitmap1;
label0:
        {
            int i = bitmap.getHeight();
            int j = bitmap.getWidth();
            if(i <= 64)
            {
                l = j;
                i1 = i;
                bitmap1 = bitmap;
                if(j <= 64)
                    break label0;
            }
            if(mTempCompactBitmap == null)
            {
                mTempCompactBitmap = Bitmap.createBitmap(64, 64, android.graphics.Bitmap.Config.ARGB_8888);
                mTempCompactBitmapCanvas = new Canvas(mTempCompactBitmap);
                mTempCompactBitmapPaint = new Paint(1);
                mTempCompactBitmapPaint.setFilterBitmap(true);
            }
            mTempMatrix.reset();
            mTempMatrix.setScale(64F / (float)j, 64F / (float)i, 0.0F, 0.0F);
            mTempCompactBitmapCanvas.drawColor(0, android.graphics.PorterDuff.Mode.SRC);
            mTempCompactBitmapCanvas.drawBitmap(bitmap, mTempMatrix, mTempCompactBitmapPaint);
            bitmap1 = mTempCompactBitmap;
            i1 = 64;
            l = 64;
        }
        int k = i1 * l;
        ensureBufferSize(k);
        bitmap1.getPixels(mTempBuffer, 0, l, 0, 0, l, i1);
        for(int j1 = 0; j1 < k; j1++)
            if(!isGrayscale(mTempBuffer[j1]))
                return false;

        return true;
    }

    private static final int ALPHA_TOLERANCE = 50;
    private static final int COMPACT_BITMAP_SIZE = 64;
    private static final int TOLERANCE = 20;
    private int mTempBuffer[];
    private Bitmap mTempCompactBitmap;
    private Canvas mTempCompactBitmapCanvas;
    private Paint mTempCompactBitmapPaint;
    private final Matrix mTempMatrix = new Matrix();
}
