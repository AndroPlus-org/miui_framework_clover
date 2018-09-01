// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.util.LongSparseArray;

public class TimeSparseArray extends LongSparseArray
{

    public TimeSparseArray()
    {
    }

    public TimeSparseArray(int i)
    {
        super(i);
    }

    public int closestIndexOnOrAfter(long l)
    {
        int i = size();
        int j = 0;
        int k = i - 1;
        int i1 = -1;
        long l1 = -1L;
        while(j <= k) 
        {
            i1 = j + (k - j) / 2;
            l1 = keyAt(i1);
            if(l > l1)
                j = i1 + 1;
            else
            if(l < l1)
                k = i1 - 1;
            else
                return i1;
        }
        if(l < l1)
            return i1;
        if(l > l1 && j < i)
            return j;
        else
            return -1;
    }

    public int closestIndexOnOrBefore(long l)
    {
        int i = closestIndexOnOrAfter(l);
        if(i < 0)
            return size() - 1;
        if(keyAt(i) == l)
            return i;
        else
            return i - 1;
    }
}
