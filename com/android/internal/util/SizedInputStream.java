// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.io.IOException;
import java.io.InputStream;
import libcore.io.Streams;

public class SizedInputStream extends InputStream
{

    public SizedInputStream(InputStream inputstream, long l)
    {
        mWrapped = inputstream;
        mLength = l;
    }

    public void close()
        throws IOException
    {
        super.close();
        mWrapped.close();
    }

    public int read()
        throws IOException
    {
        return Streams.readSingleByte(this);
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if(mLength <= 0L)
            return -1;
        int k = j;
        if((long)j > mLength)
            k = (int)mLength;
        i = mWrapped.read(abyte0, i, k);
        if(i == -1)
        {
            if(mLength > 0L)
                throw new IOException((new StringBuilder()).append("Unexpected EOF; expected ").append(mLength).append(" more bytes").toString());
        } else
        {
            mLength = mLength - (long)i;
        }
        return i;
    }

    private long mLength;
    private final InputStream mWrapped;
}
