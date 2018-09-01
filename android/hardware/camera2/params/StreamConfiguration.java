// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Size;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.hardware.camera2.params:
//            StreamConfigurationMap

public final class StreamConfiguration
{

    public StreamConfiguration(int i, int j, int k, boolean flag)
    {
        mFormat = StreamConfigurationMap.checkArgumentFormatInternal(i);
        mWidth = Preconditions.checkArgumentPositive(j, "width must be positive");
        mHeight = Preconditions.checkArgumentPositive(k, "height must be positive");
        mInput = flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof StreamConfiguration)
        {
            obj = (StreamConfiguration)obj;
            if(mFormat == ((StreamConfiguration) (obj)).mFormat && mWidth == ((StreamConfiguration) (obj)).mWidth && mHeight == ((StreamConfiguration) (obj)).mHeight)
            {
                if(mInput != ((StreamConfiguration) (obj)).mInput)
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
        int i = 1;
        int j = mFormat;
        int k = mWidth;
        int l = mHeight;
        if(!mInput)
            i = 0;
        return HashCodeHelpers.hashCode(new int[] {
            j, k, l, i
        });
    }

    public boolean isInput()
    {
        return mInput;
    }

    public boolean isOutput()
    {
        return mInput ^ true;
    }

    private final int mFormat;
    private final int mHeight;
    private final boolean mInput;
    private final int mWidth;
}
