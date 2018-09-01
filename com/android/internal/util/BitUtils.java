// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.IntFunction;
import libcore.util.Objects;

// Referenced classes of package com.android.internal.util:
//            Preconditions

public final class BitUtils
{

    private BitUtils()
    {
    }

    public static long bitAt(int i)
    {
        return 1L << i;
    }

    public static int bytesToBEInt(byte abyte0[])
    {
        return (uint8(abyte0[0]) << 24) + (uint8(abyte0[1]) << 16) + (uint8(abyte0[2]) << 8) + uint8(abyte0[3]);
    }

    public static int bytesToLEInt(byte abyte0[])
    {
        return Integer.reverseBytes(bytesToBEInt(abyte0));
    }

    public static String flagsToString(int i, IntFunction intfunction)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(int j = 0; i != 0; j++)
        {
            int k = 1 << Integer.numberOfTrailingZeros(i);
            i &= k;
            if(j > 0)
                stringbuilder.append(", ");
            stringbuilder.append((String)intfunction.apply(k));
        }

        TextUtils.wrap(stringbuilder, "[", "]");
        return stringbuilder.toString();
    }

    public static int getUint16(ByteBuffer bytebuffer, int i)
    {
        return uint16(bytebuffer.getShort(i));
    }

    public static long getUint32(ByteBuffer bytebuffer, int i)
    {
        return uint32(bytebuffer.getInt(i));
    }

    public static int getUint8(ByteBuffer bytebuffer, int i)
    {
        return uint8(bytebuffer.get(i));
    }

    public static boolean isBitSet(long l, int i)
    {
        boolean flag;
        if((bitAt(i) & l) != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean maskedEquals(byte byte0, byte byte1, byte byte2)
    {
        boolean flag;
        if((byte0 & byte2) == (byte1 & byte2))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean maskedEquals(long l, long l1, long l2)
    {
        boolean flag;
        if((l & l2) == (l1 & l2))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean maskedEquals(UUID uuid, UUID uuid1, UUID uuid2)
    {
        if(uuid2 == null)
            return Objects.equal(uuid, uuid1);
        boolean flag;
        if(maskedEquals(uuid.getLeastSignificantBits(), uuid1.getLeastSignificantBits(), uuid2.getLeastSignificantBits()))
            flag = maskedEquals(uuid.getMostSignificantBits(), uuid1.getMostSignificantBits(), uuid2.getMostSignificantBits());
        else
            flag = false;
        return flag;
    }

    public static boolean maskedEquals(byte abyte0[], byte abyte1[], byte abyte2[])
    {
        boolean flag = true;
        if(abyte0 == null || abyte1 == null)
        {
            if(abyte0 != abyte1)
                flag = false;
            return flag;
        }
        if(abyte0.length == abyte1.length)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "Inputs must be of same size");
        if(abyte2 == null)
            return Arrays.equals(abyte0, abyte1);
        if(abyte0.length == abyte2.length)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "Mask must be of same size as inputs");
        for(int i = 0; i < abyte2.length; i++)
            if(!maskedEquals(abyte0[i], abyte1[i], abyte2[i]))
                return false;

        return true;
    }

    public static long packBits(int ai[])
    {
        long l = 0L;
        int i = 0;
        for(int j = ai.length; i < j; i++)
            l |= 1 << ai[i];

        return l;
    }

    public static void put(ByteBuffer bytebuffer, int i, byte abyte0[])
    {
        int j = bytebuffer.position();
        bytebuffer.position(i);
        bytebuffer.put(abyte0);
        bytebuffer.position(j);
    }

    public static int uint16(short word0)
    {
        return 0xffff & word0;
    }

    public static long uint32(int i)
    {
        return (long)i & 0xffffffffL;
    }

    public static int uint8(byte byte0)
    {
        return byte0 & 0xff;
    }

    public static int[] unpackBits(long l)
    {
        int ai[] = new int[Long.bitCount(l)];
        int i = 0;
        int j = 0;
        while(l > 0L) 
        {
            if((l & 1L) == 1L)
            {
                int k = j + 1;
                ai[j] = i;
                j = k;
            }
            l >>= 1;
            i++;
        }
        return ai;
    }
}
