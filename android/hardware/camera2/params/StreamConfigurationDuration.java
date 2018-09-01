// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Size;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.hardware.camera2.params:
//            StreamConfigurationMap

public final class StreamConfigurationDuration
{

    public StreamConfigurationDuration(int i, int j, int k, long l)
    {
        mFormat = StreamConfigurationMap.checkArgumentFormatInternal(i);
        mWidth = Preconditions.checkArgumentPositive(j, "width must be positive");
        mHeight = Preconditions.checkArgumentPositive(k, "height must be positive");
        mDurationNs = Preconditions.checkArgumentNonnegative(l, "durationNs must be non-negative");
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof StreamConfigurationDuration)
        {
            obj = (StreamConfigurationDuration)obj;
            if(mFormat == ((StreamConfigurationDuration) (obj)).mFormat && mWidth == ((StreamConfigurationDuration) (obj)).mWidth && mHeight == ((StreamConfigurationDuration) (obj)).mHeight)
            {
                if(mDurationNs != ((StreamConfigurationDuration) (obj)).mDurationNs)
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

    public long getDuration()
    {
        return mDurationNs;
    }

    public final int getFormat()
    {
        return mFormat;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public Size getSize()
    {
        return new Size(mWidth, mHeight);
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCode(new int[] {
            mFormat, mWidth, mHeight, (int)mDurationNs, (int)(mDurationNs >>> 32)
        });
    }

    private final long mDurationNs;
    private final int mFormat;
    private final int mHeight;
    private final int mWidth;
}
