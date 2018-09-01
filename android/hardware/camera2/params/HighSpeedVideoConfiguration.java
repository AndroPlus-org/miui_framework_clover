// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Range;
import android.util.Size;
import com.android.internal.util.Preconditions;

public final class HighSpeedVideoConfiguration
{

    public HighSpeedVideoConfiguration(int i, int j, int k, int l, int i1)
    {
        if(l < 120)
        {
            throw new IllegalArgumentException("fpsMax must be at least 120");
        } else
        {
            mFpsMax = l;
            mWidth = Preconditions.checkArgumentPositive(i, "width must be positive");
            mHeight = Preconditions.checkArgumentPositive(j, "height must be positive");
            mFpsMin = Preconditions.checkArgumentPositive(k, "fpsMin must be positive");
            mSize = new Size(mWidth, mHeight);
            mBatchSizeMax = Preconditions.checkArgumentPositive(i1, "batchSizeMax must be positive");
            mFpsRange = new Range(Integer.valueOf(mFpsMin), Integer.valueOf(mFpsMax));
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof HighSpeedVideoConfiguration)
        {
            obj = (HighSpeedVideoConfiguration)obj;
            if(mWidth == ((HighSpeedVideoConfiguration) (obj)).mWidth && mHeight == ((HighSpeedVideoConfiguration) (obj)).mHeight && mFpsMin == ((HighSpeedVideoConfiguration) (obj)).mFpsMin && mFpsMax == ((HighSpeedVideoConfiguration) (obj)).mFpsMax)
            {
                if(mBatchSizeMax != ((HighSpeedVideoConfiguration) (obj)).mBatchSizeMax)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public int getBatchSizeMax()
    {
        return mBatchSizeMax;
    }

    public int getFpsMax()
    {
        return mFpsMax;
    }

    public int getFpsMin()
    {
        return mFpsMin;
    }

    public Range getFpsRange()
    {
        return mFpsRange;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public Size getSize()
    {
        return mSize;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCode(new int[] {
            mWidth, mHeight, mFpsMin, mFpsMax
        });
    }

    private static final int HIGH_SPEED_MAX_MINIMAL_FPS = 120;
    private final int mBatchSizeMax;
    private final int mFpsMax;
    private final int mFpsMin;
    private final Range mFpsRange;
    private final int mHeight;
    private final Size mSize;
    private final int mWidth;
}
