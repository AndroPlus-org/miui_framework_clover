// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import libcore.util.EmptyArray;

public class LongArray
    implements Cloneable
{

    public LongArray()
    {
        this(10);
    }

    public LongArray(int i)
    {
        if(i == 0)
            mValues = EmptyArray.LONG;
        else
            mValues = ArrayUtils.newUnpaddedLongArray(i);
        mSize = 0;
    }

    private LongArray(long al[], int i)
    {
        mValues = al;
        mSize = Preconditions.checkArgumentInRange(i, 0, al.length, "size");
    }

    private void checkBounds(int i)
    {
        if(i < 0 || mSize <= i)
            throw new ArrayIndexOutOfBoundsException(mSize, i);
        else
            return;
    }

    private void ensureCapacity(int i)
    {
        int j = mSize;
        int k = j + i;
        if(k >= mValues.length)
        {
            long al[];
            if(j < 6)
                i = 12;
            else
                i = j >> 1;
            i = j + i;
            if(i <= k)
                i = k;
            al = ArrayUtils.newUnpaddedLongArray(i);
            System.arraycopy(mValues, 0, al, 0, j);
            mValues = al;
        }
    }

    public static LongArray fromArray(long al[], int i)
    {
        return wrap(Arrays.copyOf(al, i));
    }

    public static LongArray wrap(long al[])
    {
        return new LongArray(al, al.length);
    }

    public void add(int i, long l)
    {
        ensureCapacity(1);
        int j = mSize - i;
        mSize = mSize + 1;
        checkBounds(i);
        if(j != 0)
            System.arraycopy(mValues, i, mValues, i + 1, j);
        mValues[i] = l;
    }

    public void add(long l)
    {
        add(mSize, l);
    }

    public void addAll(LongArray longarray)
    {
        int i = longarray.mSize;
        ensureCapacity(i);
        System.arraycopy(longarray.mValues, 0, mValues, mSize, i);
        mSize = mSize + i;
    }

    public void clear()
    {
        mSize = 0;
    }

    public LongArray clone()
    {
        LongArray longarray = null;
        LongArray longarray1 = (LongArray)super.clone();
        longarray = longarray1;
        longarray1.mValues = (long[])mValues.clone();
        longarray = longarray1;
_L2:
        return longarray;
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

    public long get(int i)
    {
        checkBounds(i);
        return mValues[i];
    }

    public int indexOf(long l)
    {
        int i = mSize;
        for(int j = 0; j < i; j++)
            if(mValues[j] == l)
                return j;

        return -1;
    }

    public void remove(int i)
    {
        checkBounds(i);
        System.arraycopy(mValues, i + 1, mValues, i, mSize - i - 1);
        mSize = mSize - 1;
    }

    public void resize(int i)
    {
        Preconditions.checkArgumentNonnegative(i);
        if(i <= mValues.length)
            Arrays.fill(mValues, i, mValues.length, 0L);
        else
            ensureCapacity(i - mSize);
        mSize = i;
    }

    public void set(int i, long l)
    {
        checkBounds(i);
        mValues[i] = l;
    }

    public int size()
    {
        return mSize;
    }

    public long[] toArray()
    {
        return Arrays.copyOf(mValues, mSize);
    }

    private static final int MIN_CAPACITY_INCREMENT = 12;
    private int mSize;
    private long mValues[];
}
