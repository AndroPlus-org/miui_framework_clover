// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers

public class SparseLongArray
    implements Cloneable
{

    public SparseLongArray()
    {
        this(10);
    }

    public SparseLongArray(int i)
    {
        if(i == 0)
        {
            mKeys = EmptyArray.INT;
            mValues = EmptyArray.LONG;
        } else
        {
            mValues = ArrayUtils.newUnpaddedLongArray(i);
            mKeys = new int[mValues.length];
        }
        mSize = 0;
    }

    public void append(int i, long l)
    {
        if(mSize != 0 && i <= mKeys[mSize - 1])
        {
            put(i, l);
            return;
        } else
        {
            mKeys = GrowingArrayUtils.append(mKeys, mSize, i);
            mValues = GrowingArrayUtils.append(mValues, mSize, l);
            mSize = mSize + 1;
            return;
        }
    }

    public void clear()
    {
        mSize = 0;
    }

    public SparseLongArray clone()
    {
        SparseLongArray sparselongarray = null;
        SparseLongArray sparselongarray1 = (SparseLongArray)super.clone();
        sparselongarray = sparselongarray1;
        sparselongarray1.mKeys = (int[])mKeys.clone();
        sparselongarray = sparselongarray1;
        sparselongarray1.mValues = (long[])mValues.clone();
        sparselongarray = sparselongarray1;
_L2:
        return sparselongarray;
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

    public void delete(int i)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(i >= 0)
            removeAt(i);
    }

    public long get(int i)
    {
        return get(i, 0L);
    }

    public long get(int i, long l)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(i < 0)
            return l;
        else
            return mValues[i];
    }

    public int indexOfKey(int i)
    {
        return ContainerHelpers.binarySearch(mKeys, mSize, i);
    }

    public int indexOfValue(long l)
    {
        for(int i = 0; i < mSize; i++)
            if(mValues[i] == l)
                return i;

        return -1;
    }

    public int keyAt(int i)
    {
        return mKeys[i];
    }

    public void put(int i, long l)
    {
        int j = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(j >= 0)
        {
            mValues[j] = l;
        } else
        {
            j = j;
            mKeys = GrowingArrayUtils.insert(mKeys, mSize, j, i);
            mValues = GrowingArrayUtils.insert(mValues, mSize, j, l);
            mSize = mSize + 1;
        }
    }

    public void removeAt(int i)
    {
        System.arraycopy(mKeys, i + 1, mKeys, i, mSize - (i + 1));
        System.arraycopy(mValues, i + 1, mValues, i, mSize - (i + 1));
        mSize = mSize - 1;
    }

    public void removeAtRange(int i, int j)
    {
        j = Math.min(j, mSize - i);
        System.arraycopy(mKeys, i + j, mKeys, i, mSize - (i + j));
        System.arraycopy(mValues, i + j, mValues, i, mSize - (i + j));
        mSize = mSize - j;
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

    private int mKeys[];
    private int mSize;
    private long mValues[];
}
