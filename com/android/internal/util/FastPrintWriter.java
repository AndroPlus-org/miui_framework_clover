// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.util.Log;
import android.util.Printer;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class FastPrintWriter extends PrintWriter
{
    private static class DummyWriter extends Writer
    {

        public void close()
            throws IOException
        {
            throw new UnsupportedOperationException("Shouldn't be here");
        }

        public void flush()
            throws IOException
        {
            close();
        }

        public void write(char ac[], int i, int j)
            throws IOException
        {
            close();
        }

        private DummyWriter()
        {
        }

        DummyWriter(DummyWriter dummywriter)
        {
            this();
        }
    }


    public FastPrintWriter(Printer printer)
    {
        this(printer, 512);
    }

    public FastPrintWriter(Printer printer, int i)
    {
        super(new DummyWriter(null), true);
        if(printer == null)
        {
            throw new NullPointerException("pr is null");
        } else
        {
            mBufferLen = i;
            mText = new char[i];
            mBytes = null;
            mOutputStream = null;
            mWriter = null;
            mPrinter = printer;
            mAutoFlush = true;
            mSeparator = System.lineSeparator();
            initDefaultEncoder();
            return;
        }
    }

    public FastPrintWriter(OutputStream outputstream)
    {
        this(outputstream, false, 8192);
    }

    public FastPrintWriter(OutputStream outputstream, boolean flag)
    {
        this(outputstream, flag, 8192);
    }

    public FastPrintWriter(OutputStream outputstream, boolean flag, int i)
    {
        super(new DummyWriter(null), flag);
        if(outputstream == null)
        {
            throw new NullPointerException("out is null");
        } else
        {
            mBufferLen = i;
            mText = new char[i];
            mBytes = ByteBuffer.allocate(mBufferLen);
            mOutputStream = outputstream;
            mWriter = null;
            mPrinter = null;
            mAutoFlush = flag;
            mSeparator = System.lineSeparator();
            initDefaultEncoder();
            return;
        }
    }

    public FastPrintWriter(Writer writer)
    {
        this(writer, false, 8192);
    }

    public FastPrintWriter(Writer writer, boolean flag)
    {
        this(writer, flag, 8192);
    }

    public FastPrintWriter(Writer writer, boolean flag, int i)
    {
        super(new DummyWriter(null), flag);
        if(writer == null)
        {
            throw new NullPointerException("wr is null");
        } else
        {
            mBufferLen = i;
            mText = new char[i];
            mBytes = null;
            mOutputStream = null;
            mWriter = writer;
            mPrinter = null;
            mAutoFlush = flag;
            mSeparator = System.lineSeparator();
            initDefaultEncoder();
            return;
        }
    }

    private void appendLocked(char c)
        throws IOException
    {
        int i = mPos;
        int j = i;
        if(i >= mBufferLen - 1)
        {
            flushLocked();
            j = mPos;
        }
        mText[j] = c;
        mPos = j + 1;
    }

    private void appendLocked(String s, int i, int j)
        throws IOException
    {
        int k = mBufferLen;
        if(j > k)
        {
            int l = i + j;
            while(i < l) 
            {
                int j1 = i + k;
                if(j1 < l)
                    j = k;
                else
                    j = l - i;
                appendLocked(s, i, j);
                i = j1;
            }
            return;
        }
        int i1 = mPos;
        int k1 = i1;
        if(i1 + j > k)
        {
            flushLocked();
            k1 = mPos;
        }
        s.getChars(i, i + j, mText, k1);
        mPos = k1 + j;
    }

    private void appendLocked(char ac[], int i, int j)
        throws IOException
    {
        int k = mBufferLen;
        if(j > k)
        {
            int l = i + j;
            while(i < l) 
            {
                int j1 = i + k;
                if(j1 < l)
                    j = k;
                else
                    j = l - i;
                appendLocked(ac, i, j);
                i = j1;
            }
            return;
        }
        int i1 = mPos;
        int k1 = i1;
        if(i1 + j > k)
        {
            flushLocked();
            k1 = mPos;
        }
        System.arraycopy(ac, i, mText, k1, j);
        mPos = k1 + j;
    }

    private void flushBytesLocked()
        throws IOException
    {
        if(!mIoError)
        {
            int i = mBytes.position();
            if(i > 0)
            {
                mBytes.flip();
                mOutputStream.write(mBytes.array(), 0, i);
                mBytes.clear();
            }
        }
    }

    private void flushLocked()
        throws IOException
    {
        if(mPos <= 0) goto _L2; else goto _L1
_L1:
        if(mOutputStream == null) goto _L4; else goto _L3
_L3:
        CharBuffer charbuffer = CharBuffer.wrap(mText, 0, mPos);
        CoderResult coderresult = mCharset.encode(charbuffer, mBytes, true);
        do
        {
            if(mIoError)
                break;
            if(coderresult.isError())
                throw new IOException(coderresult.toString());
            if(!coderresult.isOverflow())
                break;
            flushBytesLocked();
            coderresult = mCharset.encode(charbuffer, mBytes, true);
        } while(true);
        if(!mIoError)
        {
            flushBytesLocked();
            mOutputStream.flush();
        }
_L6:
        mPos = 0;
_L2:
        return;
_L4:
        if(mWriter != null)
        {
            if(!mIoError)
            {
                mWriter.write(mText, 0, mPos);
                mWriter.flush();
            }
        } else
        {
            int i = 0;
            int j = mSeparator.length();
            if(j >= mPos)
                j = mPos;
            for(; i < j && mText[mPos - 1 - i] == mSeparator.charAt(mSeparator.length() - 1 - i); i++);
            if(i >= mPos)
                mPrinter.println("");
            else
                mPrinter.println(new String(mText, 0, mPos - i));
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    private final void initDefaultEncoder()
    {
        mCharset = Charset.defaultCharset().newEncoder();
        mCharset.onMalformedInput(CodingErrorAction.REPLACE);
        mCharset.onUnmappableCharacter(CodingErrorAction.REPLACE);
    }

    private final void initEncoder(String s)
        throws UnsupportedEncodingException
    {
        try
        {
            mCharset = Charset.forName(s).newEncoder();
        }
        catch(Exception exception)
        {
            throw new UnsupportedEncodingException(s);
        }
        mCharset.onMalformedInput(CodingErrorAction.REPLACE);
        mCharset.onUnmappableCharacter(CodingErrorAction.REPLACE);
    }

    public PrintWriter append(CharSequence charsequence, int i, int j)
    {
        Object obj = charsequence;
        if(charsequence == null)
            obj = "null";
        charsequence = ((CharSequence) (obj)).subSequence(i, j).toString();
        write(charsequence, 0, charsequence.length());
        return this;
    }

    public volatile Writer append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public boolean checkError()
    {
        flush();
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mIoError;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    protected void clearError()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        mIoError = false;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void close()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        flushLocked();
        if(mOutputStream == null) goto _L2; else goto _L1
_L1:
        mOutputStream.close();
_L4:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mWriter == null) goto _L4; else goto _L3
_L3:
        mWriter.close();
          goto _L4
        Object obj1;
        obj1;
        Log.w("FastPrintWriter", "Write failure", ((Throwable) (obj1)));
        setError();
          goto _L4
        obj1;
        throw obj1;
    }

    public void flush()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        flushLocked();
        if(mIoError) goto _L2; else goto _L1
_L1:
        if(mOutputStream == null) goto _L4; else goto _L3
_L3:
        mOutputStream.flush();
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        if(mWriter == null) goto _L2; else goto _L5
_L5:
        mWriter.flush();
          goto _L2
        Object obj1;
        obj1;
        Log.w("FastPrintWriter", "Write failure", ((Throwable) (obj1)));
        setError();
          goto _L2
        obj1;
        throw obj1;
    }

    public void print(char c)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        appendLocked(c);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.w("FastPrintWriter", "Write failure", ((Throwable) (obj1)));
        setError();
          goto _L1
        obj1;
        throw obj1;
    }

    public void print(int i)
    {
        if(i == 0)
            print("0");
        else
            super.print(i);
    }

    public void print(long l)
    {
        if(l == 0L)
            print("0");
        else
            super.print(l);
    }

    public void print(String s)
    {
        String s1;
        s1 = s;
        if(s == null)
            s1 = String.valueOf((Object)null);
        s = ((String) (lock));
        s;
        JVM INSTR monitorenter ;
        appendLocked(s1, 0, s1.length());
_L1:
        s;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        Log.w("FastPrintWriter", "Write failure", ((Throwable) (obj)));
        setError();
          goto _L1
        obj;
        throw obj;
    }

    public void print(char ac[])
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        appendLocked(ac, 0, ac.length);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        ac;
        Log.w("FastPrintWriter", "Write failure", ac);
        setError();
          goto _L1
        ac;
        throw ac;
    }

    public void println()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        appendLocked(mSeparator, 0, mSeparator.length());
        if(mAutoFlush)
            flushLocked();
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.w("FastPrintWriter", "Write failure", ((Throwable) (obj1)));
        setError();
          goto _L1
        obj1;
        throw obj1;
    }

    public void println(char c)
    {
        print(c);
        println();
    }

    public void println(int i)
    {
        if(i == 0)
            println("0");
        else
            super.println(i);
    }

    public void println(long l)
    {
        if(l == 0L)
            println("0");
        else
            super.println(l);
    }

    public void println(char ac[])
    {
        print(ac);
        println();
    }

    protected void setError()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        mIoError = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void write(int i)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        char c = (char)i;
        appendLocked(c);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.w("FastPrintWriter", "Write failure", ((Throwable) (obj1)));
        setError();
          goto _L1
        obj1;
        throw obj1;
    }

    public void write(String s)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        appendLocked(s, 0, s.length());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        Log.w("FastPrintWriter", "Write failure", s);
        setError();
          goto _L1
        s;
        throw s;
    }

    public void write(String s, int i, int j)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        appendLocked(s, i, j);
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        Log.w("FastPrintWriter", "Write failure", s);
        setError();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        throw s;
    }

    public void write(char ac[], int i, int j)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        appendLocked(ac, i, j);
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        ac;
        Log.w("FastPrintWriter", "Write failure", ac);
        setError();
        if(true) goto _L2; else goto _L1
_L1:
        ac;
        throw ac;
    }

    private final boolean mAutoFlush;
    private final int mBufferLen;
    private final ByteBuffer mBytes;
    private CharsetEncoder mCharset;
    private boolean mIoError;
    private final OutputStream mOutputStream;
    private int mPos;
    private final Printer mPrinter;
    private final String mSeparator;
    private final char mText[];
    private final Writer mWriter;
}
