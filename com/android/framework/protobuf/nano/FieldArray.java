// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;


// Referenced classes of package com.android.framework.protobuf.nano:
//            FieldData

public final class FieldArray
    implements Cloneable
{

    FieldArray()
    {
        this(10);
    }

    FieldArray(int i)
    {
        mGarbage = false;
        i = idealIntArraySize(i);
        mFieldNumbers = new int[i];
        mData = new FieldData[i];
        mSize = 0;
    }

    private boolean arrayEquals(int ai[], int ai1[], int i)
    {
        for(int j = 0; j < i; j++)
            if(ai[j] != ai1[j])
                return false;

        return true;
    }

    private boolean arrayEquals(FieldData afielddata[], FieldData afielddata1[], int i)
    {
        for(int j = 0; j < i; j++)
            if(!afielddata[j].equals(afielddata1[j]))
                return false;

        return true;
    }

    private int binarySearch(int i)
    {
        int j = 0;
        for(int k = mSize - 1; j <= k;)
        {
            int l = j + k >>> 1;
            int i1 = mFieldNumbers[l];
            if(i1 < i)
                j = l + 1;
            else
            if(i1 > i)
                k = l - 1;
            else
                return l;
        }

        return j;
    }

    private void gc()
    {
        int i = mSize;
        int j = 0;
        int ai[] = mFieldNumbers;
        FieldData afielddata[] = mData;
        for(int k = 0; k < i;)
        {
            FieldData fielddata = afielddata[k];
            int l = j;
            if(fielddata != DELETED)
            {
                if(k != j)
                {
                    ai[j] = ai[k];
                    afielddata[j] = fielddata;
                    afielddata[k] = null;
                }
                l = j + 1;
            }
            k++;
            j = l;
        }

        mGarbage = false;
        mSize = j;
    }

    private int idealByteArraySize(int i)
    {
        for(int j = 4; j < 32; j++)
            if(i <= (1 << j) - 12)
                return (1 << j) - 12;

        return i;
    }

    private int idealIntArraySize(int i)
    {
        return idealByteArraySize(i * 4) / 4;
    }

    public final FieldArray clone()
    {
        int i = size();
        FieldArray fieldarray = new FieldArray(i);
        System.arraycopy(mFieldNumbers, 0, fieldarray.mFieldNumbers, 0, i);
        for(int j = 0; j < i; j++)
            if(mData[j] != null)
                fieldarray.mData[j] = mData[j].clone();

        fieldarray.mSize = i;
        return fieldarray;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    FieldData dataAt(int i)
    {
        if(mGarbage)
            gc();
        return mData[i];
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof FieldArray))
            return false;
        obj = (FieldArray)obj;
        if(size() != ((FieldArray) (obj)).size())
            return false;
        if(arrayEquals(mFieldNumbers, ((FieldArray) (obj)).mFieldNumbers, mSize))
            flag = arrayEquals(mData, ((FieldArray) (obj)).mData, mSize);
        return flag;
    }

    FieldData get(int i)
    {
        i = binarySearch(i);
        if(i < 0 || mData[i] == DELETED)
            return null;
        else
            return mData[i];
    }

    public int hashCode()
    {
        if(mGarbage)
            gc();
        int i = 17;
        for(int j = 0; j < mSize; j++)
            i = (i * 31 + mFieldNumbers[j]) * 31 + mData[j].hashCode();

        return i;
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        if(size() == 0)
            flag = true;
        return flag;
    }

    void put(int i, FieldData fielddata)
    {
        int j = binarySearch(i);
        if(j >= 0)
        {
            mData[j] = fielddata;
        } else
        {
            int k = j;
            if(k < mSize && mData[k] == DELETED)
            {
                mFieldNumbers[k] = i;
                mData[k] = fielddata;
                return;
            }
            j = k;
            if(mGarbage)
            {
                j = k;
                if(mSize >= mFieldNumbers.length)
                {
                    gc();
                    j = binarySearch(i);
                }
            }
            if(mSize >= mFieldNumbers.length)
            {
                int l = idealIntArraySize(mSize + 1);
                int ai[] = new int[l];
                FieldData afielddata[] = new FieldData[l];
                System.arraycopy(mFieldNumbers, 0, ai, 0, mFieldNumbers.length);
                System.arraycopy(mData, 0, afielddata, 0, mData.length);
                mFieldNumbers = ai;
                mData = afielddata;
            }
            if(mSize - j != 0)
            {
                System.arraycopy(mFieldNumbers, j, mFieldNumbers, j + 1, mSize - j);
                System.arraycopy(mData, j, mData, j + 1, mSize - j);
            }
            mFieldNumbers[j] = i;
            mData[j] = fielddata;
            mSize = mSize + 1;
        }
    }

    void remove(int i)
    {
        i = binarySearch(i);
        if(i >= 0 && mData[i] != DELETED)
        {
            mData[i] = DELETED;
            mGarbage = true;
        }
    }

    int size()
    {
        if(mGarbage)
            gc();
        return mSize;
    }

    private static final FieldData DELETED = new FieldData();
    private FieldData mData[];
    private int mFieldNumbers[];
    private boolean mGarbage;
    private int mSize;

}
