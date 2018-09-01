// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;

public final class InputConfiguration
{

    public InputConfiguration(int i, int j, int k)
    {
        mWidth = i;
        mHeight = j;
        mFormat = k;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof InputConfiguration))
            return false;
        obj = (InputConfiguration)obj;
        return ((InputConfiguration) (obj)).getWidth() == mWidth && ((InputConfiguration) (obj)).getHeight() == mHeight && ((InputConfiguration) (obj)).getFormat() == mFormat;
    }

    public int getFormat()
    {
        return mFormat;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCode(new int[] {
            mWidth, mHeight, mFormat
        });
    }

    public String toString()
    {
        return String.format("InputConfiguration(w:%d, h:%d, format:%d)", new Object[] {
            Integer.valueOf(mWidth), Integer.valueOf(mHeight), Integer.valueOf(mFormat)
        });
    }

    private final int mFormat;
    private final int mHeight;
    private final int mWidth;
}
