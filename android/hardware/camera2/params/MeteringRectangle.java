// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Size;
import com.android.internal.util.Preconditions;

public final class MeteringRectangle
{

    public MeteringRectangle(int i, int j, int k, int l, int i1)
    {
        mX = Preconditions.checkArgumentNonnegative(i, "x must be nonnegative");
        mY = Preconditions.checkArgumentNonnegative(j, "y must be nonnegative");
        mWidth = Preconditions.checkArgumentNonnegative(k, "width must be nonnegative");
        mHeight = Preconditions.checkArgumentNonnegative(l, "height must be nonnegative");
        mWeight = Preconditions.checkArgumentInRange(i1, 0, 1000, "meteringWeight");
    }

    public MeteringRectangle(Point point, Size size, int i)
    {
        Preconditions.checkNotNull(point, "xy must not be null");
        Preconditions.checkNotNull(size, "dimensions must not be null");
        mX = Preconditions.checkArgumentNonnegative(point.x, "x must be nonnegative");
        mY = Preconditions.checkArgumentNonnegative(point.y, "y must be nonnegative");
        mWidth = Preconditions.checkArgumentNonnegative(size.getWidth(), "width must be nonnegative");
        mHeight = Preconditions.checkArgumentNonnegative(size.getHeight(), "height must be nonnegative");
        mWeight = Preconditions.checkArgumentNonnegative(i, "meteringWeight must be nonnegative");
    }

    public MeteringRectangle(Rect rect, int i)
    {
        Preconditions.checkNotNull(rect, "rect must not be null");
        mX = Preconditions.checkArgumentNonnegative(rect.left, "rect.left must be nonnegative");
        mY = Preconditions.checkArgumentNonnegative(rect.top, "rect.top must be nonnegative");
        mWidth = Preconditions.checkArgumentNonnegative(rect.width(), "rect.width must be nonnegative");
        mHeight = Preconditions.checkArgumentNonnegative(rect.height(), "rect.height must be nonnegative");
        mWeight = Preconditions.checkArgumentNonnegative(i, "meteringWeight must be nonnegative");
    }

    public boolean equals(MeteringRectangle meteringrectangle)
    {
        boolean flag = false;
        if(meteringrectangle == null)
            return false;
        boolean flag1 = flag;
        if(mX == meteringrectangle.mX)
        {
            flag1 = flag;
            if(mY == meteringrectangle.mY)
            {
                flag1 = flag;
                if(mWidth == meteringrectangle.mWidth)
                {
                    flag1 = flag;
                    if(mHeight == meteringrectangle.mHeight)
                    {
                        flag1 = flag;
                        if(mWeight == meteringrectangle.mWeight)
                            flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof MeteringRectangle)
            flag = equals((MeteringRectangle)obj);
        else
            flag = false;
        return flag;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getMeteringWeight()
    {
        return mWeight;
    }

    public Rect getRect()
    {
        return new Rect(mX, mY, mX + mWidth, mY + mHeight);
    }

    public Size getSize()
    {
        return new Size(mWidth, mHeight);
    }

    public Point getUpperLeftPoint()
    {
        return new Point(mX, mY);
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int getX()
    {
        return mX;
    }

    public int getY()
    {
        return mY;
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCode(new int[] {
            mX, mY, mWidth, mHeight, mWeight
        });
    }

    public String toString()
    {
        return String.format("(x:%d, y:%d, w:%d, h:%d, wt:%d)", new Object[] {
            Integer.valueOf(mX), Integer.valueOf(mY), Integer.valueOf(mWidth), Integer.valueOf(mHeight), Integer.valueOf(mWeight)
        });
    }

    public static final int METERING_WEIGHT_DONT_CARE = 0;
    public static final int METERING_WEIGHT_MAX = 1000;
    public static final int METERING_WEIGHT_MIN = 0;
    private final int mHeight;
    private final int mWeight;
    private final int mWidth;
    private final int mX;
    private final int mY;
}
