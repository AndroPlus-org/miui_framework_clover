// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.io.PrintStream;
import libcore.util.EmptyArray;

class PackedObjectVector
{

    public PackedObjectVector(int i)
    {
        mColumns = i;
        mValues = EmptyArray.OBJECT;
        mRows = 0;
        mRowGapStart = 0;
        mRowGapLength = mRows;
    }

    private void growBuffer()
    {
        Object aobj[] = ArrayUtils.newUnpaddedObjectArray(GrowingArrayUtils.growSize(size()) * mColumns);
        int i = aobj.length / mColumns;
        int j = mRows - (mRowGapStart + mRowGapLength);
        System.arraycopy(((Object) (mValues)), 0, ((Object) (aobj)), 0, mColumns * mRowGapStart);
        System.arraycopy(((Object) (mValues)), (mRows - j) * mColumns, ((Object) (aobj)), (i - j) * mColumns, mColumns * j);
        mRowGapLength = mRowGapLength + (i - mRows);
        mRows = i;
        mValues = aobj;
    }

    private void moveRowGapTo(int i)
    {
        if(i == mRowGapStart)
            return;
        if(i > mRowGapStart)
        {
            int j = mRowGapLength;
            int l = mRowGapStart;
            int j1 = mRowGapLength;
            for(int l1 = mRowGapStart + mRowGapLength; l1 < mRowGapStart + mRowGapLength + ((j + i) - (l + j1)); l1++)
            {
                int j2 = mRowGapStart;
                int k2 = mRowGapLength;
                int l2 = mRowGapStart;
                for(int i3 = 0; i3 < mColumns; i3++)
                {
                    Object obj = mValues[mColumns * l1 + i3];
                    mValues[mColumns * ((l1 - (j2 + k2)) + l2) + i3] = obj;
                }

            }

        } else
        {
            int i1 = mRowGapStart - i;
            for(int i2 = (i + i1) - 1; i2 >= i; i2--)
            {
                int k1 = mRowGapStart;
                int k = mRowGapLength;
                for(int j3 = 0; j3 < mColumns; j3++)
                {
                    Object obj1 = mValues[mColumns * i2 + j3];
                    mValues[mColumns * (((i2 - i) + k1 + k) - i1) + j3] = obj1;
                }

            }

        }
        mRowGapStart = i;
    }

    public void deleteAt(int i, int j)
    {
        moveRowGapTo(i + j);
        mRowGapStart = mRowGapStart - j;
        mRowGapLength = mRowGapLength + j;
        i = mRowGapLength;
        size();
    }

    public void dump()
    {
        for(int i = 0; i < mRows; i++)
        {
            int j = 0;
            while(j < mColumns) 
            {
                Object obj = mValues[mColumns * i + j];
                if(i < mRowGapStart || i >= mRowGapStart + mRowGapLength)
                    System.out.print((new StringBuilder()).append(obj).append(" ").toString());
                else
                    System.out.print((new StringBuilder()).append("(").append(obj).append(") ").toString());
                j++;
            }
            System.out.print(" << \n");
        }

        System.out.print("-----\n\n");
    }

    public Object getValue(int i, int j)
    {
        int k = i;
        if(i >= mRowGapStart)
            k = i + mRowGapLength;
        return mValues[mColumns * k + j];
    }

    public void insertAt(int i, Object aobj[])
    {
        moveRowGapTo(i);
        if(mRowGapLength == 0)
            growBuffer();
        mRowGapStart = mRowGapStart + 1;
        mRowGapLength = mRowGapLength - 1;
        if(aobj == null)
        {
            for(int j = 0; j < mColumns; j++)
                setValue(i, j, null);

        } else
        {
            for(int k = 0; k < mColumns; k++)
                setValue(i, k, aobj[k]);

        }
    }

    public void setValue(int i, int j, Object obj)
    {
        int k = i;
        if(i >= mRowGapStart)
            k = i + mRowGapLength;
        mValues[mColumns * k + j] = obj;
    }

    public int size()
    {
        return mRows - mRowGapLength;
    }

    public int width()
    {
        return mColumns;
    }

    private int mColumns;
    private int mRowGapLength;
    private int mRowGapStart;
    private int mRows;
    private Object mValues[];
}
