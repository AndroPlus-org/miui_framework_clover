// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.io.*;
import java.net.ProtocolException;
import java.nio.charset.StandardCharsets;

public class ProcFileReader
    implements Closeable
{

    public ProcFileReader(InputStream inputstream)
        throws IOException
    {
        this(inputstream, 4096);
    }

    public ProcFileReader(InputStream inputstream, int i)
        throws IOException
    {
        mStream = inputstream;
        mBuffer = new byte[i];
        fillBuf();
    }

    private void consumeBuf(int i)
        throws IOException
    {
        System.arraycopy(mBuffer, i, mBuffer, 0, mTail - i);
        mTail = mTail - i;
        if(mTail == 0)
            fillBuf();
    }

    private int fillBuf()
        throws IOException
    {
        int i = mBuffer.length - mTail;
        if(i == 0)
            throw new IOException("attempting to fill already-full buffer");
        i = mStream.read(mBuffer, mTail, i);
        if(i != -1)
            mTail = mTail + i;
        return i;
    }

    private NumberFormatException invalidLong(int i)
    {
        return new NumberFormatException((new StringBuilder()).append("invalid long: ").append(new String(mBuffer, 0, i, StandardCharsets.US_ASCII)).toString());
    }

    private int nextTokenIndex()
        throws IOException
    {
        if(mLineFinished)
            return -1;
        do
        {
            for(int i = 0; i < mTail; i++)
            {
                byte byte0 = mBuffer[i];
                if(byte0 == 10)
                {
                    mLineFinished = true;
                    return i;
                }
                if(byte0 == 32)
                    return i;
            }

        } while(fillBuf() > 0);
        throw new ProtocolException("End of stream while looking for token boundary");
    }

    private long parseAndConsumeLong(int i)
        throws IOException
    {
        boolean flag;
        long l;
        int j;
        if(mBuffer[0] == 45)
            flag = true;
        else
            flag = false;
        l = 0L;
        if(flag)
            j = 1;
        else
            j = 0;
        for(; j < i; j++)
        {
            int k = mBuffer[j] - 48;
            if(k < 0 || k > 9)
                throw invalidLong(i);
            long l1 = 10L * l - (long)k;
            if(l1 > l)
                throw invalidLong(i);
            l = l1;
        }

        consumeBuf(i + 1);
        if(!flag)
            l = -l;
        return l;
    }

    private String parseAndConsumeString(int i)
        throws IOException
    {
        String s = new String(mBuffer, 0, i, StandardCharsets.US_ASCII);
        consumeBuf(i + 1);
        return s;
    }

    public void close()
        throws IOException
    {
        mStream.close();
    }

    public void finishLine()
        throws IOException
    {
        if(mLineFinished)
        {
            mLineFinished = false;
            return;
        }
        do
        {
            for(int i = 0; i < mTail; i++)
                if(mBuffer[i] == 10)
                {
                    consumeBuf(i + 1);
                    return;
                }

        } while(fillBuf() > 0);
        throw new ProtocolException("End of stream while looking for line boundary");
    }

    public boolean hasMoreData()
    {
        boolean flag = false;
        if(mTail > 0)
            flag = true;
        return flag;
    }

    public int nextInt()
        throws IOException
    {
        long l = nextLong();
        if(l > 0x7fffffffL || l < 0xffffffff80000000L)
            throw new NumberFormatException("parsed value larger than integer");
        else
            return (int)l;
    }

    public long nextLong()
        throws IOException
    {
        int i = nextTokenIndex();
        if(i == -1)
            throw new ProtocolException("Missing required long");
        else
            return parseAndConsumeLong(i);
    }

    public long nextOptionalLong(long l)
        throws IOException
    {
        int i = nextTokenIndex();
        if(i == -1)
            return l;
        else
            return parseAndConsumeLong(i);
    }

    public String nextString()
        throws IOException
    {
        int i = nextTokenIndex();
        if(i == -1)
            throw new ProtocolException("Missing required string");
        else
            return parseAndConsumeString(i);
    }

    private final byte mBuffer[];
    private boolean mLineFinished;
    private final InputStream mStream;
    private int mTail;
}
