// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import libcore.util.EmptyArray;

public abstract class ArrayUtils
{

    private ArrayUtils()
    {
    }

    public static byte[] cloneIfNotEmpty(byte abyte0[])
    {
        if(abyte0 != null && abyte0.length > 0)
            abyte0 = (byte[])abyte0.clone();
        return abyte0;
    }

    public static String[] cloneIfNotEmpty(String as[])
    {
        if(as != null && as.length > 0)
            as = (String[])as.clone();
        return as;
    }

    public static byte[] concat(byte abyte0[], int i, int j, byte abyte1[], int k, int l)
    {
        if(j == 0)
            return subarray(abyte1, k, l);
        if(l == 0)
        {
            return subarray(abyte0, i, j);
        } else
        {
            byte abyte2[] = new byte[j + l];
            System.arraycopy(abyte0, i, abyte2, 0, j);
            System.arraycopy(abyte1, k, abyte2, j, l);
            return abyte2;
        }
    }

    public static byte[] concat(byte abyte0[], byte abyte1[])
    {
        int i;
        int j;
        if(abyte0 != null)
            i = abyte0.length;
        else
            i = 0;
        if(abyte1 != null)
            j = abyte1.length;
        else
            j = 0;
        return concat(abyte0, 0, i, abyte1, 0, j);
    }

    public static int[] concat(int ai[], int ai1[])
    {
        if(ai == null || ai.length == 0)
            return ai1;
        if(ai1 == null || ai1.length == 0)
        {
            return ai;
        } else
        {
            int ai2[] = new int[ai.length + ai1.length];
            System.arraycopy(ai, 0, ai2, 0, ai.length);
            System.arraycopy(ai1, 0, ai2, ai.length, ai1.length);
            return ai2;
        }
    }

    public static String[] nullToEmpty(String as[])
    {
        if(as == null)
            as = EmptyArray.STRING;
        return as;
    }

    public static byte[] subarray(byte abyte0[], int i, int j)
    {
        if(j == 0)
            return EmptyArray.BYTE;
        if(i == 0 && j == abyte0.length)
        {
            return abyte0;
        } else
        {
            byte abyte1[] = new byte[j];
            System.arraycopy(abyte0, i, abyte1, 0, j);
            return abyte1;
        }
    }
}
