// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;

public final class Range
{

    public Range(Comparable comparable, Comparable comparable1)
    {
        mLower = (Comparable)Preconditions.checkNotNull(comparable, "lower must not be null");
        mUpper = (Comparable)Preconditions.checkNotNull(comparable1, "upper must not be null");
        if(comparable.compareTo(comparable1) > 0)
            throw new IllegalArgumentException("lower must be less than or equal to upper");
        else
            return;
    }

    public static Range create(Comparable comparable, Comparable comparable1)
    {
        return new Range(comparable, comparable1);
    }

    public Comparable clamp(Comparable comparable)
    {
        Preconditions.checkNotNull(comparable, "value must not be null");
        if(comparable.compareTo(mLower) < 0)
            return mLower;
        if(comparable.compareTo(mUpper) > 0)
            return mUpper;
        else
            return comparable;
    }

    public boolean contains(Range range)
    {
        Preconditions.checkNotNull(range, "value must not be null");
        boolean flag;
        boolean flag1;
        if(range.mLower.compareTo(mLower) >= 0)
            flag = true;
        else
            flag = false;
        if(range.mUpper.compareTo(mUpper) <= 0)
            flag1 = true;
        else
            flag1 = false;
        if(!flag)
            flag1 = false;
        return flag1;
    }

    public boolean contains(Comparable comparable)
    {
        Preconditions.checkNotNull(comparable, "value must not be null");
        boolean flag;
        boolean flag1;
        if(comparable.compareTo(mLower) >= 0)
            flag = true;
        else
            flag = false;
        if(comparable.compareTo(mUpper) <= 0)
            flag1 = true;
        else
            flag1 = false;
        if(!flag)
            flag1 = false;
        return flag1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof Range)
        {
            obj = (Range)obj;
            if(mLower.equals(((Range) (obj)).mLower))
                flag = mUpper.equals(((Range) (obj)).mUpper);
            return flag;
        } else
        {
            return false;
        }
    }

    public Range extend(Range range)
    {
        Preconditions.checkNotNull(range, "range must not be null");
        int i = range.mLower.compareTo(mLower);
        int j = range.mUpper.compareTo(mUpper);
        if(i <= 0 && j >= 0)
            return range;
        if(i >= 0 && j <= 0)
            return this;
        Comparable comparable;
        if(i >= 0)
            comparable = mLower;
        else
            comparable = range.mLower;
        if(j <= 0)
            range = mUpper;
        else
            range = range.mUpper;
        return create(comparable, range);
    }

    public Range extend(Comparable comparable)
    {
        Preconditions.checkNotNull(comparable, "value must not be null");
        return extend(comparable, comparable);
    }

    public Range extend(Comparable comparable, Comparable comparable1)
    {
        Preconditions.checkNotNull(comparable, "lower must not be null");
        Preconditions.checkNotNull(comparable1, "upper must not be null");
        int i = comparable.compareTo(mLower);
        int j = comparable1.compareTo(mUpper);
        if(i >= 0 && j <= 0)
            return this;
        if(i >= 0)
            comparable = mLower;
        if(j <= 0)
            comparable1 = mUpper;
        return create(comparable, comparable1);
    }

    public Comparable getLower()
    {
        return mLower;
    }

    public Comparable getUpper()
    {
        return mUpper;
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCodeGeneric(new Comparable[] {
            mLower, mUpper
        });
    }

    public Range intersect(Range range)
    {
        Preconditions.checkNotNull(range, "range must not be null");
        int i = range.mLower.compareTo(mLower);
        int j = range.mUpper.compareTo(mUpper);
        if(i <= 0 && j >= 0)
            return this;
        if(i >= 0 && j <= 0)
            return range;
        Comparable comparable;
        if(i <= 0)
            comparable = mLower;
        else
            comparable = range.mLower;
        if(j >= 0)
            range = mUpper;
        else
            range = range.mUpper;
        return create(comparable, range);
    }

    public Range intersect(Comparable comparable, Comparable comparable1)
    {
        Preconditions.checkNotNull(comparable, "lower must not be null");
        Preconditions.checkNotNull(comparable1, "upper must not be null");
        int i = comparable.compareTo(mLower);
        int j = comparable1.compareTo(mUpper);
        if(i <= 0 && j >= 0)
            return this;
        if(i <= 0)
            comparable = mLower;
        if(j >= 0)
            comparable1 = mUpper;
        return create(comparable, comparable1);
    }

    public String toString()
    {
        return String.format("[%s, %s]", new Object[] {
            mLower, mUpper
        });
    }

    private final Comparable mLower;
    private final Comparable mUpper;
}
