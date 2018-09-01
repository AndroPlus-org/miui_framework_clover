// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers

public class LongSparseArray
    implements Cloneable
{

    public LongSparseArray()
    {
        this(10);
    }

    public LongSparseArray(int i)
    {
        mGarbage = false;
        if(i == 0)
        {
            mKeys = EmptyArray.LONG;
            mValues = EmptyArray.OBJECT;
        } else
        {
            mKeys = ArrayUtils.newUnpaddedLongArray(i);
            mValues = ArrayUtils.newUnpaddedObjectArray(i);
        }
        mSize = 0;
    }

    private void gc()
    {
        int i = mSize;
        int j = 0;
        long al[] = mKeys;
        Object aobj[] = mValues;
        for(int k = 0; k < i;)
        {
            Object obj = aobj[k];
            int l = j;
            if(obj != DELETED)
            {
                if(k != j)
                {
                    al[j] = al[k];
                    aobj[j] = obj;
                    aobj[k] = null;
                }
                l = j + 1;
            }
            k++;
            j = l;
        }

        mGarbage = false;
        mSize = j;
    }

    public void append(long l, Object obj)
    {
        if(mSize != 0 && l <= mKeys[mSize - 1])
        {
            put(l, obj);
            return;
        }
        if(mGarbage && mSize >= mKeys.length)
            gc();
        mKeys = GrowingArrayUtils.append(mKeys, mSize, l);
        mValues = GrowingArrayUtils.append(mValues, mSize, obj);
        mSize = mSize + 1;
    }

    public void clear()
    {
        int i = mSize;
        Object aobj[] = mValues;
        for(int j = 0; j < i; j++)
            aobj[j] = null;

        mSize = 0;
        mGarbage = false;
    }

    public LongSparseArray clone()
    {
        LongSparseArray longsparsearray = null;
        LongSparseArray longsparsearray1 = (LongSparseArray)super.clone();
        longsparsearray = longsparsearray1;
        longsparsearray1.mKeys = (long[])mKeys.clone();
        longsparsearray = longsparsearray1;
        longsparsearray1.mValues = (Object[])mValues.clone();
        longsparsearray = longsparsearray1;
_L2:
        return longsparsearray;
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
        if(i >= 0 && mValues[i] != DELETED)
        {
            mValues[i] = DELETED;
            mGarbage = true;
        }
    }

    public Object get(long l)
    {
        return get(l, null);
    }

    public Object get(long l, Object obj)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i < 0 || mValues[i] == DELETED)
            return obj;
        else
            return mValues[i];
    }

    public int indexOfKey(long l)
    {
        if(mGarbage)
            gc();
        return ContainerHelpers.binarySearch(mKeys, mSize, l);
    }

    public int indexOfValue(Object obj)
    {
        if(mGarbage)
            gc();
        for(int i = 0; i < mSize; i++)
            if(mValues[i] == obj)
                return i;

        return -1;
    }

    public int indexOfValueByValue(Object obj)
    {
        if(mGarbage)
            gc();
        for(int i = 0; i < mSize; i++)
        {
            if(obj == null)
            {
                if(mValues[i] == null)
                    return i;
                continue;
            }
            if(obj.equals(mValues[i]))
                return i;
        }

        return -1;
    }

    public long keyAt(int i)
    {
        if(mGarbage)
            gc();
        return mKeys[i];
    }

    public void put(long l, Object obj)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i >= 0)
        {
            mValues[i] = obj;
        } else
        {
            int j = i;
            if(j < mSize && mValues[j] == DELETED)
            {
                mKeys[j] = l;
                mValues[j] = obj;
                return;
            }
            i = j;
            if(mGarbage)
            {
                i = j;
                if(mSize >= mKeys.length)
                {
                    gc();
                    i = ContainerHelpers.binarySearch(mKeys, mSize, l);
                }
            }
            mKeys = GrowingArrayUtils.insert(mKeys, mSize, i, l);
            mValues = GrowingArrayUtils.insert(mValues, mSize, i, obj);
            mSize = mSize + 1;
        }
    }

    public void remove(long l)
    {
        delete(l);
    }

    public void removeAt(int i)
    {
        if(mValues[i] != DELETED)
        {
            mValues[i] = DELETED;
            mGarbage = true;
        }
    }

    public void setValueAt(int i, Object obj)
    {
        if(mGarbage)
            gc();
        mValues[i] = obj;
    }

    public int size()
    {
        if(mGarbage)
            gc();
        return mSize;
    }

    public String toString()
    {
        if(size() <= 0)
            return "{}";
        StringBuilder stringbuilder = new StringBuilder(mSize * 28);
        stringbuilder.append('{');
        int i = 0;
        while(i < mSize) 
        {
            if(i > 0)
                stringbuilder.append(", ");
            stringbuilder.append(keyAt(i));
            stringbuilder.append('=');
            Object obj = valueAt(i);
            if(obj != this)
                stringbuilder.append(obj);
            else
                stringbuilder.append("(this Map)");
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public Object valueAt(int i)
    {
        if(mGarbage)
            gc();
        return mValues[i];
    }

    private static final Object DELETED = new Object();
    private boolean mGarbage;
    private long mKeys[];
    private int mSize;
    private Object mValues[];

}
