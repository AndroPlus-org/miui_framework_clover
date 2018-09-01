// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.util.Arrays;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers

public class SparseIntArray
    implements Cloneable
{

    public SparseIntArray()
    {
        this(10);
    }

    public SparseIntArray(int i)
    {
        if(i == 0)
        {
            mKeys = EmptyArray.INT;
            mValues = EmptyArray.INT;
        } else
        {
            mKeys = ArrayUtils.newUnpaddedIntArray(i);
            mValues = new int[mKeys.length];
        }
        mSize = 0;
    }

    public void append(int i, int j)
    {
        if(mSize != 0 && i <= mKeys[mSize - 1])
        {
            put(i, j);
            return;
        } else
        {
            mKeys = GrowingArrayUtils.append(mKeys, mSize, i);
            mValues = GrowingArrayUtils.append(mValues, mSize, j);
            mSize = mSize + 1;
            return;
        }
    }

    public void clear()
    {
        mSize = 0;
    }

    public SparseIntArray clone()
    {
        SparseIntArray sparseintarray = null;
        SparseIntArray sparseintarray1 = (SparseIntArray)super.clone();
        sparseintarray = sparseintarray1;
        sparseintarray1.mKeys = (int[])mKeys.clone();
        sparseintarray = sparseintarray1;
        sparseintarray1.mValues = (int[])mValues.clone();
        sparseintarray = sparseintarray1;
_L2:
        return sparseintarray;
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

    public int[] copyKeys()
    {
        if(size() == 0)
            return null;
        else
            return Arrays.copyOf(mKeys, size());
    }

    public void delete(int i)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(i >= 0)
            removeAt(i);
    }

    public int get(int i)
    {
        return get(i, 0);
    }

    public int get(int i, int j)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(i < 0)
            return j;
        else
            return mValues[i];
    }

    public int indexOfKey(int i)
    {
        return ContainerHelpers.binarySearch(mKeys, mSize, i);
    }

    public int indexOfValue(int i)
    {
        for(int j = 0; j < mSize; j++)
            if(mValues[j] == i)
                return j;

        return -1;
    }

    public int keyAt(int i)
    {
        return mKeys[i];
    }

    public void put(int i, int j)
    {
        int k = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(k >= 0)
        {
            mValues[k] = j;
        } else
        {
            k = k;
            mKeys = GrowingArrayUtils.insert(mKeys, mSize, k, i);
            mValues = GrowingArrayUtils.insert(mValues, mSize, k, j);
            mSize = mSize + 1;
        }
    }

    public void removeAt(int i)
    {
        System.arraycopy(mKeys, i + 1, mKeys, i, mSize - (i + 1));
        System.arraycopy(mValues, i + 1, mValues, i, mSize - (i + 1));
        mSize = mSize - 1;
    }

    public void setValueAt(int i, int j)
    {
        mValues[i] = j;
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

    public int valueAt(int i)
    {
        return mValues[i];
    }

    private int mKeys[];
    private int mSize;
    private int mValues[];
}
