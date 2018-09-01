// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers

public class SparseBooleanArray
    implements Cloneable
{

    public SparseBooleanArray()
    {
        this(10);
    }

    public SparseBooleanArray(int i)
    {
        if(i == 0)
        {
            mKeys = EmptyArray.INT;
            mValues = EmptyArray.BOOLEAN;
        } else
        {
            mKeys = ArrayUtils.newUnpaddedIntArray(i);
            mValues = new boolean[mKeys.length];
        }
        mSize = 0;
    }

    public void append(int i, boolean flag)
    {
        if(mSize != 0 && i <= mKeys[mSize - 1])
        {
            put(i, flag);
            return;
        } else
        {
            mKeys = GrowingArrayUtils.append(mKeys, mSize, i);
            mValues = GrowingArrayUtils.append(mValues, mSize, flag);
            mSize = mSize + 1;
            return;
        }
    }

    public void clear()
    {
        mSize = 0;
    }

    public SparseBooleanArray clone()
    {
        SparseBooleanArray sparsebooleanarray = null;
        SparseBooleanArray sparsebooleanarray1 = (SparseBooleanArray)super.clone();
        sparsebooleanarray = sparsebooleanarray1;
        sparsebooleanarray1.mKeys = (int[])mKeys.clone();
        sparsebooleanarray = sparsebooleanarray1;
        sparsebooleanarray1.mValues = (boolean[])mValues.clone();
        sparsebooleanarray = sparsebooleanarray1;
_L2:
        return sparsebooleanarray;
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
        {
            System.arraycopy(mKeys, i + 1, mKeys, i, mSize - (i + 1));
            System.arraycopy(mValues, i + 1, mValues, i, mSize - (i + 1));
            mSize = mSize - 1;
        }
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof SparseBooleanArray))
            return false;
        obj = (SparseBooleanArray)obj;
        if(mSize != ((SparseBooleanArray) (obj)).mSize)
            return false;
        for(int i = 0; i < mSize; i++)
        {
            if(mKeys[i] != ((SparseBooleanArray) (obj)).mKeys[i])
                return false;
            if(mValues[i] != ((SparseBooleanArray) (obj)).mValues[i])
                return false;
        }

        return true;
    }

    public boolean get(int i)
    {
        return get(i, false);
    }

    public boolean get(int i, boolean flag)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(i < 0)
            return flag;
        else
            return mValues[i];
    }

    public int hashCode()
    {
        int i = mSize;
        int j = 0;
        while(j < mSize) 
        {
            int k = mKeys[j];
            boolean flag;
            if(mValues[j])
                flag = true;
            else
                flag = false;
            i = k + i * 31 | flag;
            j++;
        }
        return i;
    }

    public int indexOfKey(int i)
    {
        return ContainerHelpers.binarySearch(mKeys, mSize, i);
    }

    public int indexOfValue(boolean flag)
    {
        for(int i = 0; i < mSize; i++)
            if(mValues[i] == flag)
                return i;

        return -1;
    }

    public int keyAt(int i)
    {
        return mKeys[i];
    }

    public void put(int i, boolean flag)
    {
        int j = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(j >= 0)
        {
            mValues[j] = flag;
        } else
        {
            j = j;
            mKeys = GrowingArrayUtils.insert(mKeys, mSize, j, i);
            mValues = GrowingArrayUtils.insert(mValues, mSize, j, flag);
            mSize = mSize + 1;
        }
    }

    public void removeAt(int i)
    {
        System.arraycopy(mKeys, i + 1, mKeys, i, mSize - (i + 1));
        System.arraycopy(mValues, i + 1, mValues, i, mSize - (i + 1));
        mSize = mSize - 1;
    }

    public void setKeyAt(int i, int j)
    {
        mKeys[i] = j;
    }

    public void setValueAt(int i, boolean flag)
    {
        mValues[i] = flag;
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

    public boolean valueAt(int i)
    {
        return mValues[i];
    }

    private int mKeys[];
    private int mSize;
    private boolean mValues[];
}
