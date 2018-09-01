// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.alsa;


public class LineTokenizer
{

    public LineTokenizer(String s)
    {
        mDelimiters = s;
    }

    int nextDelimiter(String s, int i)
    {
        int j = s.length();
        do
        {
            if(i >= j || mDelimiters.indexOf(s.charAt(i)) != -1)
            {
                if(i >= j)
                    i = -1;
                return i;
            }
            i++;
        } while(true);
    }

    int nextToken(String s, int i)
    {
        int j = s.length();
        do
        {
            if(i >= j || mDelimiters.indexOf(s.charAt(i)) == -1)
            {
                if(i >= j)
                    i = -1;
                return i;
            }
            i++;
        } while(true);
    }

    public static final int kTokenNotFound = -1;
    private final String mDelimiters;
}
