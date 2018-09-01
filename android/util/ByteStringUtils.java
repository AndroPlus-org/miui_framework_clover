// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


public final class ByteStringUtils
{

    private ByteStringUtils()
    {
    }

    public static byte[] fromHexToByteArray(String s)
    {
        while(s == null || s.length() == 0 || s.length() % 2 != 0) 
            return null;
        char ac[] = s.toCharArray();
        s = new byte[ac.length / 2];
        for(int i = 0; i < s.length; i++)
            s[i] = (byte)(getIndex(ac[i * 2]) << 4 & 0xf0 | getIndex(ac[i * 2 + 1]) & 0xf);

        return s;
    }

    private static int getIndex(char c)
    {
        for(int i = 0; i < HEX_ARRAY.length; i++)
            if(HEX_ARRAY[i] == c)
                return i;

        return -1;
    }

    public static String toHexString(byte abyte0[])
    {
        while(abyte0 == null || abyte0.length == 0 || abyte0.length % 2 != 0) 
            return null;
        int i = abyte0.length;
        char ac[] = new char[i * 2];
        for(int j = 0; j < i; j++)
        {
            int k = abyte0[j] & 0xff;
            ac[j * 2] = HEX_ARRAY[k >>> 4];
            ac[j * 2 + 1] = HEX_ARRAY[k & 0xf];
        }

        return new String(ac);
    }

    private static final char HEX_ARRAY[] = "0123456789ABCDEF".toCharArray();

}
