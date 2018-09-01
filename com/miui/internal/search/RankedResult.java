// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.search;

import android.database.MatrixCursor;
import java.util.*;

public class RankedResult extends MatrixCursor
{

    public RankedResult(String as[])
    {
        super(as);
        mScores = new TreeMap();
        mData = new ArrayList();
    }

    private void ensureData()
    {
        if(mArrayIndex != null)
            return;
        ArrayList arraylist = new ArrayList(mScores.entrySet());
        Collections.sort(arraylist, new Comparator() {

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((java.util.Map.Entry)obj, (java.util.Map.Entry)obj1);
            }

            public int compare(java.util.Map.Entry entry, java.util.Map.Entry entry1)
            {
                return -((Double)entry.getValue()).compareTo((Double)entry1.getValue());
            }

            final RankedResult this$0;

            
            {
                this$0 = RankedResult.this;
                super();
            }
        }
);
        mArrayIndex = new int[arraylist.size()];
        for(int i = 0; i < arraylist.size(); i++)
            mArrayIndex[i] = ((Integer)((java.util.Map.Entry)arraylist.get(i)).getKey()).intValue();

    }

    public void addRow(Object aobj[], double d)
    {
        mScores.put(Integer.valueOf(mScores.size()), Double.valueOf(d));
        mData.add(((Object) (aobj)));
        mArrayIndex = null;
    }

    public Object[] get(int i)
    {
        ensureData();
        return (Object[])mData.get(mArrayIndex[i]);
    }

    public int size()
    {
        ensureData();
        return mArrayIndex.length;
    }

    private int mArrayIndex[];
    private ArrayList mData;
    private TreeMap mScores;
}
