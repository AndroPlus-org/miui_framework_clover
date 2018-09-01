// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public class BitwiseOutputStream
{
    public static class AccessException extends Exception
    {

        public AccessException(String s)
        {
            super((new StringBuilder()).append("BitwiseOutputStream access failed: ").append(s).toString());
        }
    }


    public BitwiseOutputStream(int i)
    {
        mBuf = new byte[i];
        mEnd = i << 3;
        mPos = 0;
    }

    private void possExpand(int i)
    {
        if(mPos + i < mEnd)
        {
            return;
        } else
        {
            byte abyte0[] = new byte[mPos + i >>> 2];
            System.arraycopy(mBuf, 0, abyte0, 0, mEnd >>> 3);
            mBuf = abyte0;
            mEnd = abyte0.length << 3;
            return;
        }
    }

    public void skip(int i)
    {
        possExpand(i);
        mPos = mPos + i;
    }

    public byte[] toByteArray()
    {
        int i = mPos;
        int j;
        byte abyte0[];
        if((mPos & 7) > 0)
            j = 1;
        else
            j = 0;
        j = (i >>> 3) + j;
        abyte0 = new byte[j];
        System.arraycopy(mBuf, 0, abyte0, 0, j);
        return abyte0;
    }

    public void write(int i, int j)
        throws AccessException
    {
        if(i < 0 || i > 8)
            throw new AccessException((new StringBuilder()).append("illegal write (").append(i).append(" bits)").toString());
        possExpand(i);
        int k = mPos >>> 3;
        int l = 16 - (mPos & 7) - i;
        j = (j & -1 >>> 32 - i) << l;
        mPos = mPos + i;
        byte abyte0[] = mBuf;
        abyte0[k] = (byte)(abyte0[k] | j >>> 8);
        if(l < 8)
        {
            byte abyte1[] = mBuf;
            i = k + 1;
            abyte1[i] = (byte)(abyte1[i] | j & 0xff);
        }
    }

    public void writeByteArray(int i, byte abyte0[])
        throws AccessException
    {
        for(int j = 0; j < abyte0.length; j++)
        {
            int k = Math.min(8, i - (j << 3));
            if(k > 0)
                write(k, (byte)(abyte0[j] >>> 8 - k));
        }

    }

    private byte mBuf[];
    private int mEnd;
    private int mPos;
}
