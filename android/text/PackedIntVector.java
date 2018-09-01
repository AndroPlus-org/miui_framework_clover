// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;

public class PackedIntVector
{

    public PackedIntVector(int i)
    {
        mColumns = i;
        mRows = 0;
        mRowGapStart = 0;
        mRowGapLength = mRows;
        mValues = null;
        mValueGap = new int[i * 2];
    }

    private final void growBuffer()
    {
        int i = mColumns;
        int ai[] = ArrayUtils.newUnpaddedIntArray(GrowingArrayUtils.growSize(size()) * i);
        int j = ai.length / i;
        int ai1[] = mValueGap;
        int k = mRowGapStart;
        int l = mRows - (mRowGapLength + k);
        if(mValues != null)
        {
            System.arraycopy(mValues, 0, ai, 0, i * k);
            System.arraycopy(mValues, (mRows - l) * i, ai, (j - l) * i, l * i);
        }
        for(int i1 = 0; i1 < i; i1++)
        {
            if(ai1[i1] < k)
                continue;
            ai1[i1] = ai1[i1] + (j - mRows);
            if(ai1[i1] < k)
                ai1[i1] = k;
        }

        mRowGapLength = mRowGapLength + (j - mRows);
        mRows = j;
        mValues = ai;
    }

    private final void moveRowGapTo(int i)
    {
        if(i == mRowGapStart)
            return;
        if(i > mRowGapStart)
        {
            int j = mRowGapLength;
            int l = mRowGapStart;
            int j1 = mRowGapLength;
            int l1 = mColumns;
            int ai[] = mValueGap;
            int ai2[] = mValues;
            int j2 = mRowGapStart + mRowGapLength;
            for(int l2 = j2; l2 < j2 + ((j + i) - (l + j1)); l2++)
            {
                int j3 = (l2 - j2) + mRowGapStart;
                for(int k3 = 0; k3 < l1; k3++)
                {
                    int i4 = ai2[l2 * l1 + k3];
                    int k4 = i4;
                    if(l2 >= ai[k3])
                        k4 = i4 + ai[k3 + l1];
                    i4 = k4;
                    if(j3 >= ai[k3])
                        i4 = k4 - ai[k3 + l1];
                    ai2[j3 * l1 + k3] = i4;
                }

            }

        } else
        {
            int k1 = mRowGapStart - i;
            int k = mColumns;
            int ai3[] = mValueGap;
            int ai1[] = mValues;
            int i2 = mRowGapStart;
            int k2 = mRowGapLength;
            for(int i3 = (i + k1) - 1; i3 >= i; i3--)
            {
                int i1 = ((i3 - i) + (i2 + k2)) - k1;
                for(int l3 = 0; l3 < k; l3++)
                {
                    int j4 = ai1[i3 * k + l3];
                    int l4 = j4;
                    if(i3 >= ai3[l3])
                        l4 = j4 + ai3[l3 + k];
                    j4 = l4;
                    if(i1 >= ai3[l3])
                        j4 = l4 - ai3[l3 + k];
                    ai1[i1 * k + l3] = j4;
                }

            }

        }
        mRowGapStart = i;
    }

    private final void moveValueGapTo(int i, int j)
    {
        int ai[] = mValueGap;
        int ai1[] = mValues;
        int k = mColumns;
        if(j == ai[i])
            return;
        if(j > ai[i])
        {
            for(int l = ai[i]; l < j; l++)
            {
                int j1 = l * k + i;
                ai1[j1] = ai1[j1] + ai[i + k];
            }

        } else
        {
            for(int i1 = j; i1 < ai[i]; i1++)
            {
                int k1 = i1 * k + i;
                ai1[k1] = ai1[k1] - ai[i + k];
            }

        }
        ai[i] = j;
    }

    private void setValueInternal(int i, int j, int k)
    {
        int l = i;
        if(i >= mRowGapStart)
            l = i + mRowGapLength;
        int ai[] = mValueGap;
        i = k;
        if(l >= ai[j])
            i = k - ai[mColumns + j];
        mValues[mColumns * l + j] = i;
    }

    public void adjustValuesBelow(int i, int j, int k)
    {
        while((i | j) < 0 || i > size() || j >= width()) 
            throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(", ").append(j).toString());
        int l = i;
        if(i >= mRowGapStart)
            l = i + mRowGapLength;
        moveValueGapTo(j, l);
        int ai[] = mValueGap;
        i = mColumns + j;
        ai[i] = ai[i] + k;
    }

    public void deleteAt(int i, int j)
    {
        if((i | j) < 0 || i + j > size())
        {
            throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(", ").append(j).toString());
        } else
        {
            moveRowGapTo(i + j);
            mRowGapStart = mRowGapStart - j;
            mRowGapLength = mRowGapLength + j;
            return;
        }
    }

    public int getValue(int i, int j)
    {
        int k;
        for(k = mColumns; (i | j) < 0 || i >= size() || j >= k;)
            throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(", ").append(j).toString());

        int l = i;
        if(i >= mRowGapStart)
            l = i + mRowGapLength;
        int i1 = mValues[l * k + j];
        int ai[] = mValueGap;
        i = i1;
        if(l >= ai[j])
            i = i1 + ai[j + k];
        return i;
    }

    public void insertAt(int i, int ai[])
    {
        if(i < 0 || i > size())
            throw new IndexOutOfBoundsException((new StringBuilder()).append("row ").append(i).toString());
        if(ai != null && ai.length < width())
            throw new IndexOutOfBoundsException((new StringBuilder()).append("value count ").append(ai.length).toString());
        moveRowGapTo(i);
        if(mRowGapLength == 0)
            growBuffer();
        mRowGapStart = mRowGapStart + 1;
        mRowGapLength = mRowGapLength - 1;
        if(ai == null)
        {
            for(int j = mColumns - 1; j >= 0; j--)
                setValueInternal(i, j, 0);

        } else
        {
            for(int k = mColumns - 1; k >= 0; k--)
                setValueInternal(i, k, ai[k]);

        }
    }

    public void setValue(int i, int j, int k)
    {
        while((i | j) < 0 || i >= size() || j >= mColumns) 
            throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(", ").append(j).toString());
        int l = i;
        if(i >= mRowGapStart)
            l = i + mRowGapLength;
        int ai[] = mValueGap;
        i = k;
        if(l >= ai[j])
            i = k - ai[mColumns + j];
        mValues[mColumns * l + j] = i;
    }

    public int size()
    {
        return mRows - mRowGapLength;
    }

    public int width()
    {
        return mColumns;
    }

    private final int mColumns;
    private int mRowGapLength;
    private int mRowGapStart;
    private int mRows;
    private int mValueGap[];
    private int mValues[];
}
