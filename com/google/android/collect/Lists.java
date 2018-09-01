// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.collect;

import java.util.ArrayList;
import java.util.Collections;

public class Lists
{

    public Lists()
    {
    }

    public static ArrayList newArrayList()
    {
        return new ArrayList();
    }

    public static transient ArrayList newArrayList(Object aobj[])
    {
        ArrayList arraylist = new ArrayList((aobj.length * 110) / 100 + 5);
        Collections.addAll(arraylist, aobj);
        return arraylist;
    }
}
