// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.steganography;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BitmapEncoder
{

    public BitmapEncoder()
    {
    }

    public static long bytesToLong(byte abyte0[])
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(8);
        bytebuffer.put(abyte0);
        bytebuffer.flip();
        return bytebuffer.getLong();
    }

    public static byte[] createHeader(long l)
    {
        int i = 0;
        byte abyte0[] = new byte[12];
        abyte0[0] = (byte)91;
        int j = 1 + 1;
        abyte0[1] = (byte)91;
        byte abyte1[] = longToBytes(l);
        for(int k = abyte1.length; i < k;)
        {
            abyte0[j] = abyte1[i];
            i++;
            j++;
        }

        i = j + 1;
        abyte0[j] = (byte)93;
        abyte0[i] = (byte)93;
        return abyte0;
    }

    public static byte[] decode(Bitmap bitmap)
    {
        return decodeBitmapToByteArray(bitmap, 12, (int)bytesToLong(Arrays.copyOfRange(decodeBitmapToByteArray(bitmap, 0, 12), 2, 10)));
    }

    private static byte[] decodeBitmapToByteArray(Bitmap bitmap, int i, int j)
    {
        byte abyte0[];
        int k;
        int l;
        int i1;
        int ai[];
        int j1;
        abyte0 = new byte[j];
        k = bitmap.getWidth();
        l = bitmap.getHeight();
        j = 0;
        i1 = 0;
        ai = new int[3];
        j1 = 0;
_L13:
        if(j1 >= l) goto _L2; else goto _L1
_L1:
        int k1 = 0;
_L11:
        int l1;
        int i2;
        l1 = j;
        i2 = i1;
        if(k1 >= k) goto _L4; else goto _L3
_L3:
        int j2;
        i2 = bitmap.getPixel(k1, j1);
        ai[0] = Color.red(i2) % 2;
        ai[1] = Color.green(i2) % 2;
        ai[2] = Color.blue(i2) % 2;
        j2 = 0;
        i2 = i1;
        l1 = j;
_L9:
        j = l1;
        i1 = i2;
        if(j2 >= 3) goto _L6; else goto _L5
_L5:
        if(i2 >= i)
        {
            if(ai[j2] == 1)
                j = (byte)(abyte0[i2 - i] | 1 << l1);
            else
                j = (byte)(abyte0[i2 - i] & 1 << l1);
            abyte0[i2 - i] = (byte)j;
        }
        j = ++l1;
        i1 = i2;
        if(l1 == 8)
        {
            j = 0;
            i1 = i2 + 1;
        }
        if(i1 - i < abyte0.length) goto _L7; else goto _L6
_L6:
        if(i1 - i < abyte0.length)
            break; /* Loop/switch isn't completed */
        i2 = i1;
        l1 = j;
_L4:
        if(i2 - i < abyte0.length)
            break; /* Loop/switch isn't completed */
_L2:
        return abyte0;
_L7:
        j2++;
        l1 = j;
        i2 = i1;
        if(true) goto _L9; else goto _L8
_L8:
        k1++;
        if(true) goto _L11; else goto _L10
_L10:
        j1++;
        j = l1;
        i1 = i2;
        if(true) goto _L13; else goto _L12
_L12:
    }

    public static Bitmap encode(Bitmap bitmap, byte abyte0[])
    {
        byte abyte1[] = createHeader(abyte0.length);
        byte abyte2[] = abyte0;
        if(abyte0.length % 24 != 0)
            abyte2 = Arrays.copyOf(abyte0, abyte0.length + (24 - abyte0.length % 24));
        return encodeByteArrayIntoBitmap(bitmap, abyte1, abyte2);
    }

    private static Bitmap encodeByteArrayIntoBitmap(Bitmap bitmap, byte abyte0[], byte abyte1[])
    {
        Bitmap bitmap1 = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true);
        int i = 0;
        int j = 0;
        int k = bitmap.getWidth();
        bitmap.getHeight();
        int l = 0;
        int ai[] = new int[3];
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        for(int i1 = 0; i1 < abyte0.length + abyte1.length; i1++)
        {
            int j1 = 0;
            while(j1 < 8) 
            {
                int l1;
                if(i1 < abyte0.length)
                    ai[l] = abyte0[i1] >> j1 & 1;
                else
                    ai[l] = abyte1[i1 - abyte0.length] >> j1 & 1;
                if(l == 2)
                {
                    int k1 = bitmap.getPixel(i, j);
                    l1 = Color.red(k1);
                    l = Color.green(k1);
                    int i2 = Color.blue(k1);
                    k1 = l1;
                    if(l1 % 2 == 1 - ai[0])
                        k1 = l1 + 1;
                    l1 = l;
                    if(l % 2 == 1 - ai[1])
                        l1 = l + 1;
                    l = i2;
                    if(i2 % 2 == 1 - ai[2])
                        l = i2 + 1;
                    i2 = k1;
                    if(k1 == 256)
                        i2 = 254;
                    k1 = l1;
                    if(l1 == 256)
                        k1 = 254;
                    l1 = l;
                    if(l == 256)
                        l1 = 254;
                    bitmap1.setPixel(i, j, Color.argb(255, i2, k1, l1));
                    k1 = i + 1;
                    l1 = k1;
                    l = j;
                    if(k1 == k)
                    {
                        l1 = 0;
                        l = j + 1;
                    }
                    k1 = 0;
                    j = l;
                    l = k1;
                } else
                {
                    l++;
                    l1 = i;
                }
                j1++;
                i = l1;
            }
        }

        return bitmap1;
    }

    private static byte[] longToBytes(long l)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(8);
        bytebuffer.putLong(l);
        return bytebuffer.array();
    }

    public static String printBinaryString(byte abyte0[])
    {
        String s = "";
        int i = 0;
        for(int j = abyte0.length; i < j; i++)
        {
            byte byte0 = abyte0[i];
            s = (new StringBuilder()).append(s).append("").append(byte0).append(",").toString();
        }

        return s;
    }

    public static final int HEADER_SIZE = 12;
}
