// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers

public class SparseArray
    implements Cloneable
{

    public SparseArray()
    {
        this(10);
    }

    public SparseArray(int i)
    {
        mGarbage = false;
        if(i == 0)
        {
            mKeys = EmptyArray.INT;
            mValues = EmptyArray.OBJECT;
        } else
        {
            mValues = ArrayUtils.newUnpaddedObjectArray(i);
            mKeys = new int[mValues.length];
        }
        mSize = 0;
    }

    private void gc()
    {
        int i = mSize;
        int j = 0;
        int ai[] = mKeys;
        Object aobj[] = mValues;
        for(int k = 0; k < i;)
        {
            Object obj = aobj[k];
            int l = j;
            if(obj != DELETED)
            {
                if(k != j)
                {
                    ai[j] = ai[k];
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

    public void append(int i, Object obj)
    {
        if(mSize != 0 && i <= mKeys[mSize - 1])
        {
            put(i, obj);
            return;
        }
        if(mGarbage && mSize >= mKeys.length)
            gc();
        mKeys = GrowingArrayUtils.append(mKeys, mSize, i);
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

    public SparseArray clone()
    {
        SparseArray sparsearray = null;
        SparseArray sparsearray1 = (SparseArray)super.clone();
        sparsearray = sparsearray1;
        sparsearray1.mKeys = (int[])mKeys.clone();
        sparsearray = sparsearray1;
        sparsearray1.mValues = (Object[])mValues.clone();
        sparsearray = sparsearray1;
_L2:
        return sparsearray;
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
        if(i >= 0 && mValues[i] != DELETED)
        {
            mValues[i] = DELETED;
            mGarbage = true;
        }
    }

    public Object get(int i)
    {
        return get(i, null);
    }

    public Object get(int i, Object obj)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(i < 0 || mValues[i] == DELETED)
            return obj;
        else
            return mValues[i];
    }

    public int indexOfKey(int i)
    {
        if(mGarbage)
            gc();
        return ContainerHelpers.binarySearch(mKeys, mSize, i);
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

    public int keyAt(int i)
    {
        if(mGarbage)
            gc();
        return mKeys[i];
    }

    public void put(int i, Object obj)
    {
        int j = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(j >= 0)
        {
            mValues[j] = obj;
        } else
        {
            int k = j;
            if(k < mSize && mValues[k] == DELETED)
            {
                mKeys[k] = i;
                mValues[k] = obj;
                return;
            }
            j = k;
            if(mGarbage)
            {
                j = k;
                if(mSize >= mKeys.length)
                {
                    gc();
                    j = ContainerHelpers.binarySearch(mKeys, mSize, i);
                }
            }
            mKeys = GrowingArrayUtils.insert(mKeys, mSize, j, i);
            mValues = GrowingArrayUtils.insert(mValues, mSize, j, obj);
            mSize = mSize + 1;
        }
    }

    public void remove(int i)
    {
        delete(i);
    }

    public void removeAt(int i)
    {
        if(mValues[i] != DELETED)
        {
            mValues[i] = DELETED;
            mGarbage = true;
        }
    }

    public void removeAtRange(int i, int j)
    {
        for(j = Math.min(mSize, i + j); i < j; i++)
            removeAt(i);

    }

    public Object removeReturnOld(int i)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if(i >= 0 && mValues[i] != DELETED)
        {
            Object obj = mValues[i];
            mValues[i] = DELETED;
            mGarbage = true;
            return obj;
        } else
        {
            return null;
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
    private int mKeys[];
    private int mSize;
    private Object mValues[];

}
