// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import java.util.*;

public class ArrayUtils
{

    private ArrayUtils()
    {
        throw new AssertionError();
    }

    public static boolean contains(int ai[], int i)
    {
        boolean flag;
        if(getArrayIndex(ai, i) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(Object aobj[], Object obj)
    {
        boolean flag;
        if(getArrayIndex(aobj, obj) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static int[] convertStringListToIntArray(List list, String as[], int ai[])
    {
        if(list == null)
            return null;
        list = convertStringListToIntList(list, as, ai);
        as = new int[list.size()];
        for(int i = 0; i < as.length; i++)
            as[i] = ((Integer)list.get(i)).intValue();

        return as;
    }

    public static List convertStringListToIntList(List list, String as[], int ai[])
    {
        if(list == null)
            return null;
        ArrayList arraylist = new ArrayList(list.size());
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            int i = getArrayIndex(as, (String)list.next());
            if(i >= 0 && i < ai.length)
                arraylist.add(Integer.valueOf(ai[i]));
        } while(true);
        return arraylist;
    }

    public static int getArrayIndex(int ai[], int i)
    {
        if(ai == null)
            return -1;
        for(int j = 0; j < ai.length; j++)
            if(ai[j] == i)
                return j;

        return -1;
    }

    public static int getArrayIndex(Object aobj[], Object obj)
    {
        if(aobj == null)
            return -1;
        int i = 0;
        int j = 0;
        for(int k = aobj.length; j < k; j++)
        {
            if(Objects.equals(aobj[j], obj))
                return i;
            i++;
        }

        return -1;
    }

    public static int[] toIntArray(List list)
    {
        if(list == null)
            return null;
        int ai[] = new int[list.size()];
        int i = 0;
        for(list = list.iterator(); list.hasNext();)
        {
            ai[i] = ((Integer)list.next()).intValue();
            i++;
        }

        return ai;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayUtils";
}
