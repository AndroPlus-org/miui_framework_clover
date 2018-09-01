// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import java.io.*;
import java.util.Arrays;

public class LimitedLengthInputStream extends FilterInputStream
{

    public LimitedLengthInputStream(InputStream inputstream, long l, long l1)
        throws IOException
    {
        super(inputstream);
        if(inputstream == null)
            throw new IOException("in == null");
        if(l < 0L)
            throw new IOException("offset < 0");
        if(l1 < 0L)
            throw new IOException("length < 0");
        if(l1 > 0x7fffffffffffffffL - l)
        {
            throw new IOException("offset + length > Long.MAX_VALUE");
        } else
        {
            mEnd = l + l1;
            skip(l);
            mOffset = l;
            return;
        }
    }

    public int read()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        long l1;
        l = mOffset;
        l1 = mEnd;
        if(l < l1)
            break MISSING_BLOCK_LABEL_22;
        this;
        JVM INSTR monitorexit ;
        return -1;
        int i;
        mOffset = mOffset + 1L;
        i = super.read();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int read(byte abyte0[])
        throws IOException
    {
        return read(abyte0, 0, abyte0.length);
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if(mOffset >= mEnd)
            return -1;
        Arrays.checkOffsetAndCount(abyte0.length, i, j);
        if(mOffset > 0x7fffffffffffffffL - (long)j)
            throw new IOException((new StringBuilder()).append("offset out of bounds: ").append(mOffset).append(" + ").append(j).toString());
        int k = j;
        if(mOffset + (long)j > mEnd)
            k = (int)(mEnd - mOffset);
        i = super.read(abyte0, i, k);
        mOffset = mOffset + (long)i;
        return i;
    }

    private final long mEnd;
    private long mOffset;
}
