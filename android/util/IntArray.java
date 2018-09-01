// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers

public class IntArray
    implements Cloneable
{

    public IntArray()
    {
        this(10);
    }

    public IntArray(int i)
    {
        if(i == 0)
            mValues = EmptyArray.INT;
        else
            mValues = ArrayUtils.newUnpaddedIntArray(i);
        mSize = 0;
    }

    private IntArray(int ai[], int i)
    {
        mValues = ai;
        mSize = Preconditions.checkArgumentInRange(i, 0, ai.length, "size");
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
            int ai[];
            if(j < 6)
                i = 12;
            else
                i = j >> 1;
            i = j + i;
            if(i <= k)
                i = k;
            ai = ArrayUtils.newUnpaddedIntArray(i);
            System.arraycopy(mValues, 0, ai, 0, j);
            mValues = ai;
        }
    }

    public static IntArray fromArray(int ai[], int i)
    {
        return wrap(Arrays.copyOf(ai, i));
    }

    public static IntArray wrap(int ai[])
    {
        return new IntArray(ai, ai.length);
    }

    public void add(int i)
    {
        add(mSize, i);
    }

    public void add(int i, int j)
    {
        ensureCapacity(1);
        int k = mSize - i;
        mSize = mSize + 1;
        checkBounds(i);
        if(k != 0)
            System.arraycopy(mValues, i, mValues, i + 1, k);
        mValues[i] = j;
    }

    public void addAll(IntArray intarray)
    {
        int i = intarray.mSize;
        ensureCapacity(i);
        System.arraycopy(intarray.mValues, 0, mValues, mSize, i);
        mSize = mSize + i;
    }

    public int binarySearch(int i)
    {
        return ContainerHelpers.binarySearch(mValues, mSize, i);
    }

    public void clear()
    {
        mSize = 0;
    }

    public IntArray clone()
        throws CloneNotSupportedException
    {
        IntArray intarray = (IntArray)super.clone();
        intarray.mValues = (int[])mValues.clone();
        return intarray;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int get(int i)
    {
        checkBounds(i);
        return mValues[i];
    }

    public int indexOf(int i)
    {
        int j = mSize;
        for(int k = 0; k < j; k++)
            if(mValues[k] == i)
                return k;

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
            Arrays.fill(mValues, i, mValues.length, 0);
        else
            ensureCapacity(i - mSize);
        mSize = i;
    }

    public void set(int i, int j)
    {
        checkBounds(i);
        mValues[i] = j;
    }

    public int size()
    {
        return mSize;
    }

    public int[] toArray()
    {
        return Arrays.copyOf(mValues, mSize);
    }

    private static final int MIN_CAPACITY_INCREMENT = 12;
    private int mSize;
    private int mValues[];
}
