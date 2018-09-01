// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


class ContainerHelpers
{

    ContainerHelpers()
    {
    }

    static int binarySearch(int ai[], int i, int j)
    {
        boolean flag = false;
        int l = i - 1;
        for(i = ((flag) ? 1 : 0); i <= l;)
        {
            int k = i + l >>> 1;
            int i1 = ai[k];
            if(i1 < j)
                i = k + 1;
            else
            if(i1 > j)
                l = k - 1;
            else
                return k;
        }

        return i;
    }

    static int binarySearch(long al[], int i, long l)
    {
        boolean flag = false;
        int k = i - 1;
        i = ((flag) ? 1 : 0);
        for(int j = k; i <= j;)
        {
            int i1 = i + j >>> 1;
            long l1 = al[i1];
            if(l1 < l)
                i = i1 + 1;
            else
            if(l1 > l)
                j = i1 - 1;
            else
                return i1;
        }

        return i;
    }
}
