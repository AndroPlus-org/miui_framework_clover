// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public class BitwiseInputStream
{
    public static class AccessException extends Exception
    {

        public AccessException(String s)
        {
            super((new StringBuilder()).append("BitwiseInputStream access failed: ").append(s).toString());
        }
    }


    public BitwiseInputStream(byte abyte0[])
    {
        mBuf = abyte0;
        mEnd = abyte0.length << 3;
        mPos = 0;
    }

    public int available()
    {
        return mEnd - mPos;
    }

    public int read(int i)
        throws AccessException
    {
        int j = mPos >>> 3;
        int k;
        for(k = 16 - (mPos & 7) - i; i < 0 || i > 8 || mPos + i > mEnd;)
            throw new AccessException((new StringBuilder()).append("illegal read (pos ").append(mPos).append(", end ").append(mEnd).append(", bits ").append(i).append(")").toString());

        int l = (mBuf[j] & 0xff) << 8;
        int i1 = l;
        if(k < 8)
            i1 = l | mBuf[j + 1] & 0xff;
        mPos = mPos + i;
        return i1 >>> k & -1 >>> 32 - i;
    }

    public byte[] readByteArray(int i)
        throws AccessException
    {
        int j = 0;
        if((i & 7) > 0)
            j = 1;
        int l = (i >>> 3) + j;
        byte abyte0[] = new byte[l];
        for(int k = 0; k < l; k++)
        {
            int i1 = Math.min(8, i - (k << 3));
            abyte0[k] = (byte)(read(i1) << 8 - i1);
        }

        return abyte0;
    }

    public void skip(int i)
        throws AccessException
    {
        if(mPos + i > mEnd)
        {
            throw new AccessException((new StringBuilder()).append("illegal skip (pos ").append(mPos).append(", end ").append(mEnd).append(", bits ").append(i).append(")").toString());
        } else
        {
            mPos = mPos + i;
            return;
        }
    }

    private byte mBuf[];
    private int mEnd;
    private int mPos;
}
