// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;


public class ScrollBarUtils
{

    public ScrollBarUtils()
    {
    }

    public static int getThumbLength(int i, int j, int k, int l)
    {
        j *= 2;
        k = Math.round(((float)i * (float)k) / (float)l);
        i = k;
        if(k < j)
            i = j;
        return i;
    }

    public static int getThumbOffset(int i, int j, int k, int l, int i1)
    {
        l = Math.round(((float)(i - j) * (float)i1) / (float)(l - k));
        k = l;
        if(l > i - j)
            k = i - j;
        return k;
    }
}
