// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.util.ArrayMap;
import android.util.SparseArray;

public class ProcessMap
{

    public ProcessMap()
    {
    }

    public Object get(String s, int i)
    {
        s = (SparseArray)mMap.get(s);
        if(s == null)
            return null;
        else
            return s.get(i);
    }

    public ArrayMap getMap()
    {
        return mMap;
    }

    public Object put(String s, int i, Object obj)
    {
        SparseArray sparsearray = (SparseArray)mMap.get(s);
        SparseArray sparsearray1 = sparsearray;
        if(sparsearray == null)
        {
            sparsearray1 = new SparseArray(2);
            mMap.put(s, sparsearray1);
        }
        sparsearray1.put(i, obj);
        return obj;
    }

    public Object remove(String s, int i)
    {
        SparseArray sparsearray = (SparseArray)mMap.get(s);
        if(sparsearray != null)
        {
            Object obj = sparsearray.removeReturnOld(i);
            if(sparsearray.size() == 0)
                mMap.remove(s);
            return obj;
        } else
        {
            return null;
        }
    }

    public int size()
    {
        return mMap.size();
    }

    final ArrayMap mMap = new ArrayMap();
}
