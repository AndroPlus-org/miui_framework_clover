// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import java.util.Iterator;
import java.util.List;

public class ListUtils
{

    private ListUtils()
    {
        throw new AssertionError();
    }

    public static boolean listContains(List list, Object obj)
    {
        if(list == null)
            return false;
        else
            return list.contains(obj);
    }

    public static boolean listElementsEqualTo(List list, Object obj)
    {
        boolean flag = false;
        if(list == null)
            return false;
        if(list.size() == 1)
            flag = list.contains(obj);
        return flag;
    }

    public static Object listSelectFirstFrom(List list, Object aobj[])
    {
        if(list == null)
            return null;
        int i = 0;
        for(int j = aobj.length; i < j; i++)
        {
            Object obj = aobj[i];
            if(list.contains(obj))
                return obj;
        }

        return null;
    }

    public static String listToString(List list)
    {
        if(list == null)
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('[');
        int i = list.size();
        int j = 0;
        for(list = list.iterator(); list.hasNext();)
        {
            stringbuilder.append(list.next());
            if(j != i - 1)
                stringbuilder.append(',');
            j++;
        }

        stringbuilder.append(']');
        return stringbuilder.toString();
    }
}
