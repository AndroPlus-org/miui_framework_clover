// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;


public class CharSequenceUtils
{

    public CharSequenceUtils()
    {
    }

    static int indexOf(CharSequence charsequence, int i, int j)
    {
        if(charsequence instanceof String)
            return ((String)charsequence).indexOf(i, j);
        int k = charsequence.length();
        int l = j;
        if(j < 0)
            l = 0;
        for(j = l; j < k; j++)
            if(charsequence.charAt(j) == i)
                return j;

        return -1;
    }

    static int indexOf(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        return charsequence.toString().indexOf(charsequence1.toString(), i);
    }

    static int lastIndexOf(CharSequence charsequence, int i, int j)
    {
        if(charsequence instanceof String)
            return ((String)charsequence).lastIndexOf(i, j);
        int k = charsequence.length();
        if(j < 0)
            return -1;
        int l = j;
        if(j >= k)
            l = k - 1;
        for(j = l; j >= 0; j--)
            if(charsequence.charAt(j) == i)
                return j;

        return -1;
    }

    static int lastIndexOf(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        return charsequence.toString().lastIndexOf(charsequence1.toString(), i);
    }

    static boolean regionMatches(CharSequence charsequence, boolean flag, int i, CharSequence charsequence1, int j, int k)
    {
        if((charsequence instanceof String) && (charsequence1 instanceof String))
            return ((String)charsequence).regionMatches(flag, i, (String)charsequence1, j, k);
        else
            return charsequence.toString().regionMatches(flag, i, charsequence1.toString(), j, k);
    }

    public static CharSequence subSequence(CharSequence charsequence, int i)
    {
        Object obj = null;
        if(charsequence == null)
            charsequence = obj;
        else
            charsequence = charsequence.subSequence(i, charsequence.length());
        return charsequence;
    }

    static char[] toCharArray(CharSequence charsequence)
    {
        if(charsequence instanceof String)
            return ((String)charsequence).toCharArray();
        int i = charsequence.length();
        char ac[] = new char[charsequence.length()];
        for(int j = 0; j < i; j++)
            ac[j] = charsequence.charAt(j);

        return ac;
    }
}
