// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public class HexDump
{

    public HexDump()
    {
    }

    public static StringBuilder appendByteAsHex(StringBuilder stringbuilder, byte byte0, boolean flag)
    {
        char ac[];
        if(flag)
            ac = HEX_DIGITS;
        else
            ac = HEX_LOWER_CASE_DIGITS;
        stringbuilder.append(ac[byte0 >> 4 & 0xf]);
        stringbuilder.append(ac[byte0 & 0xf]);
        return stringbuilder;
    }

    public static String dumpHexString(byte abyte0[])
    {
        return dumpHexString(abyte0, 0, abyte0.length);
    }

    public static String dumpHexString(byte abyte0[], int i, int j)
    {
        StringBuilder stringbuilder = new StringBuilder();
        byte abyte1[] = new byte[16];
        int k = 0;
        stringbuilder.append("\n0x");
        stringbuilder.append(toHexString(i));
        for(int l = i; l < i + j;)
        {
            int i1 = k;
            if(k == 16)
            {
                stringbuilder.append(" ");
                k = 0;
                while(k < 16) 
                {
                    if(abyte1[k] > 32 && abyte1[k] < 126)
                        stringbuilder.append(new String(abyte1, k, 1));
                    else
                        stringbuilder.append(".");
                    k++;
                }
                stringbuilder.append("\n0x");
                stringbuilder.append(toHexString(l));
                i1 = 0;
            }
            k = abyte0[l];
            stringbuilder.append(" ");
            stringbuilder.append(HEX_DIGITS[k >>> 4 & 0xf]);
            stringbuilder.append(HEX_DIGITS[k & 0xf]);
            abyte1[i1] = (byte)k;
            l++;
            k = i1 + 1;
        }

        if(k != 16)
        {
            for(i = 0; i < (16 - k) * 3 + 1; i++)
                stringbuilder.append(" ");

            i = 0;
            while(i < k) 
            {
                if(abyte1[i] > 32 && abyte1[i] < 126)
                    stringbuilder.append(new String(abyte1, i, 1));
                else
                    stringbuilder.append(".");
                i++;
            }
        }
        return stringbuilder.toString();
    }

    public static byte[] hexStringToByteArray(String s)
    {
        int i = s.length();
        byte abyte0[] = new byte[i / 2];
        for(int j = 0; j < i; j += 2)
            abyte0[j / 2] = (byte)(toByte(s.charAt(j)) << 4 | toByte(s.charAt(j + 1)));

        return abyte0;
    }

    private static int toByte(char c)
    {
        if(c >= '0' && c <= '9')
            return c - 48;
        if(c >= 'A' && c <= 'F')
            return (c - 65) + 10;
        if(c >= 'a' && c <= 'f')
            return (c - 97) + 10;
        else
            throw new RuntimeException((new StringBuilder()).append("Invalid hex char '").append(c).append("'").toString());
    }

    public static byte[] toByteArray(byte byte0)
    {
        return (new byte[] {
            byte0
        });
    }

    public static byte[] toByteArray(int i)
    {
        byte byte0 = (byte)(i & 0xff);
        byte byte1 = (byte)(i >> 8 & 0xff);
        byte byte2 = (byte)(i >> 16 & 0xff);
        return (new byte[] {
            (byte)(i >> 24 & 0xff), byte2, byte1, byte0
        });
    }

    public static String toHexString(byte byte0)
    {
        return toHexString(toByteArray(byte0));
    }

    public static String toHexString(int i)
    {
        return toHexString(toByteArray(i));
    }

    public static String toHexString(byte abyte0[])
    {
        return toHexString(abyte0, 0, abyte0.length, true);
    }

    public static String toHexString(byte abyte0[], int i, int j)
    {
        return toHexString(abyte0, i, j, true);
    }

    public static String toHexString(byte abyte0[], int i, int j, boolean flag)
    {
        char ac[];
        char ac1[];
        int k;
        int l;
        if(flag)
            ac = HEX_DIGITS;
        else
            ac = HEX_LOWER_CASE_DIGITS;
        ac1 = new char[j * 2];
        k = i;
        l = 0;
        for(; k < i + j; k++)
        {
            byte byte0 = abyte0[k];
            int i1 = l + 1;
            ac1[l] = ac[byte0 >>> 4 & 0xf];
            l = i1 + 1;
            ac1[i1] = ac[byte0 & 0xf];
        }

        return new String(ac1);
    }

    public static String toHexString(byte abyte0[], boolean flag)
    {
        return toHexString(abyte0, 0, abyte0.length, flag);
    }

    private static final char HEX_DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };
    private static final char HEX_LOWER_CASE_DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };

}
