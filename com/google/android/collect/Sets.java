// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.collect;

import android.util.ArraySet;
import java.util.*;

public class Sets
{

    public Sets()
    {
    }

    public static ArraySet newArraySet()
    {
        return new ArraySet();
    }

    public static transient ArraySet newArraySet(Object aobj[])
    {
        ArraySet arrayset = new ArraySet((aobj.length * 4) / 3 + 1);
        Collections.addAll(arrayset, aobj);
        return arrayset;
    }

    public static HashSet newHashSet()
    {
        return new HashSet();
    }

    public static transient HashSet newHashSet(Object aobj[])
    {
        HashSet hashset = new HashSet((aobj.length * 4) / 3 + 1);
        Collections.addAll(hashset, aobj);
        return hashset;
    }

    public static SortedSet newSortedSet()
    {
        return new TreeSet();
    }

    public static transient SortedSet newSortedSet(Object aobj[])
    {
        TreeSet treeset = new TreeSet();
        Collections.addAll(treeset, aobj);
        return treeset;
    }
}
