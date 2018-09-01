// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public class CharSequences
{

    public CharSequences()
    {
    }

    public static int compareToIgnoreCase(CharSequence charsequence, CharSequence charsequence1)
    {
        int i = charsequence.length();
        int j = charsequence1.length();
        int k;
        int l;
        int i1;
        if(i < j)
        {
            k = i;
            l = 0;
            i1 = 0;
        } else
        {
            k = j;
            l = 0;
            i1 = 0;
        }
        for(; i1 < k; i1++)
        {
            int j1 = Character.toLowerCase(charsequence.charAt(i1)) - Character.toLowerCase(charsequence1.charAt(l));
            if(j1 != 0)
                return j1;
            l++;
        }

        return i - j;
    }

    public static boolean equals(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence.length() != charsequence1.length())
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(charsequence.charAt(j) != charsequence1.charAt(j))
                return false;

        return true;
    }

    public static CharSequence forAsciiBytes(byte abyte0[])
    {
        return new CharSequence(abyte0) {

            public char charAt(int i)
            {
                return (char)bytes[i];
            }

            public int length()
            {
                return bytes.length;
            }

            public CharSequence subSequence(int i, int j)
            {
                return CharSequences.forAsciiBytes(bytes, i, j);
            }

            public String toString()
            {
                return new String(bytes);
            }

            final byte val$bytes[];

            
            {
                bytes = abyte0;
                super();
            }
        }
;
    }

    public static CharSequence forAsciiBytes(byte abyte0[], int i, int j)
    {
        validate(i, j, abyte0.length);
        return new CharSequence(abyte0, i, j) {

            public char charAt(int k)
            {
                return (char)bytes[start + k];
            }

            public int length()
            {
                return end - start;
            }

            public CharSequence subSequence(int k, int l)
            {
                k -= start;
                l -= start;
                CharSequences.validate(k, l, length());
                return CharSequences.forAsciiBytes(bytes, k, l);
            }

            public String toString()
            {
                return new String(bytes, start, length());
            }

            final byte val$bytes[];
            final int val$end;
            final int val$start;

            
            {
                bytes = abyte0;
                start = i;
                end = j;
                super();
            }
        }
;
    }

    static void validate(int i, int j, int k)
    {
        if(i < 0)
            throw new IndexOutOfBoundsException();
        if(j < 0)
            throw new IndexOutOfBoundsException();
        if(j > k)
            throw new IndexOutOfBoundsException();
        if(i > j)
            throw new IndexOutOfBoundsException();
        else
            return;
    }
}
