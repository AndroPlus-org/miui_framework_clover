// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers

public class LongSparseLongArray
    implements Cloneable
{

    public LongSparseLongArray()
    {
        this(10);
    }

    public LongSparseLongArray(int i)
    {
        if(i == 0)
        {
            mKeys = EmptyArray.LONG;
            mValues = EmptyArray.LONG;
        } else
        {
            mKeys = ArrayUtils.newUnpaddedLongArray(i);
            mValues = new long[mKeys.length];
        }
        mSize = 0;
    }

    public void append(long l, long l1)
    {
        if(mSize != 0 && l <= mKeys[mSize - 1])
        {
            put(l, l1);
            return;
        } else
        {
            mKeys = GrowingArrayUtils.append(mKeys, mSize, l);
            mValues = GrowingArrayUtils.append(mValues, mSize, l1);
            mSize = mSize + 1;
            return;
        }
    }

    public void clear()
    {
        mSize = 0;
    }

    public LongSparseLongArray clone()
    {
        LongSparseLongArray longsparselongarray = null;
        LongSparseLongArray longsparselongarray1 = (LongSparseLongArray)super.clone();
        longsparselongarray = longsparselongarray1;
        longsparselongarray1.mKeys = (long[])mKeys.clone();
        longsparselongarray = longsparselongarray1;
        longsparselongarray1.mValues = (long[])mValues.clone();
        longsparselongarray = longsparselongarray1;
_L2:
        return longsparselongarray;
        CloneNotSupportedException clonenotsupportedexception;
        clonenotsupportedexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void delete(long l)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i >= 0)
            removeAt(i);
    }

    public long get(long l)
    {
        return get(l, 0L);
    }

    public long get(long l, long l1)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i < 0)
            return l1;
        else
            return mValues[i];
    }

    public int indexOfKey(long l)
    {
        return ContainerHelpers.binarySearch(mKeys, mSize, l);
    }

    public int indexOfValue(long l)
    {
        for(int i = 0; i < mSize; i++)
            if(mValues[i] == l)
                return i;

        return -1;
    }

    public long keyAt(int i)
    {
        return mKeys[i];
    }

    public void put(long l, long l1)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i >= 0)
        {
            mValues[i] = l1;
        } else
        {
            i = i;
            mKeys = GrowingArrayUtils.insert(mKeys, mSize, i, l);
            mValues = GrowingArrayUtils.insert(mValues, mSize, i, l1);
            mSize = mSize + 1;
        }
    }

    public void removeAt(int i)
    {
        System.arraycopy(mKeys, i + 1, mKeys, i, mSize - (i + 1));
        System.arraycopy(mValues, i + 1, mValues, i, mSize - (i + 1));
        mSize = mSize - 1;
    }

    public int size()
    {
        return mSize;
    }

    public String toString()
    {
        if(size() <= 0)
            return "{}";
        StringBuilder stringbuilder = new StringBuilder(mSize * 28);
        stringbuilder.append('{');
        for(int i = 0; i < mSize; i++)
        {
            if(i > 0)
                stringbuilder.append(", ");
            stringbuilder.append(keyAt(i));
            stringbuilder.append('=');
            stringbuilder.append(valueAt(i));
        }

        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public long valueAt(int i)
    {
        return mValues[i];
    }

    private long mKeys[];
    private int mSize;
    private long mValues[];
}
