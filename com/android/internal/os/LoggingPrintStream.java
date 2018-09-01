// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.Formatter;
import java.util.Locale;

abstract class LoggingPrintStream extends PrintStream
{

    protected LoggingPrintStream()
    {
        super(new OutputStream() {

            public void write(int i)
                throws IOException
            {
                throw new AssertionError();
            }

        }
);
        formatter = new Formatter(builder, null);
    }

    private void flush(boolean flag)
    {
        int i = builder.length();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            int k = builder.indexOf("\n", j);
            if(k == -1)
                break;
            log(builder.substring(j, k));
            j = k + 1;
        } while(true);
        if(flag)
        {
            if(j < i)
                log(builder.substring(j));
            builder.setLength(0);
        } else
        {
            builder.delete(0, j);
        }
    }

    public PrintStream append(char c)
    {
        this;
        JVM INSTR monitorenter ;
        print(c);
        this;
        JVM INSTR monitorexit ;
        return this;
        Exception exception;
        exception;
        throw exception;
    }

    public PrintStream append(CharSequence charsequence)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(charsequence);
        flush(false);
        this;
        JVM INSTR monitorexit ;
        return this;
        charsequence;
        throw charsequence;
    }

    public PrintStream append(CharSequence charsequence, int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(charsequence, i, j);
        flush(false);
        this;
        JVM INSTR monitorexit ;
        return this;
        charsequence;
        throw charsequence;
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public boolean checkError()
    {
        return false;
    }

    public void close()
    {
    }

    public void flush()
    {
        this;
        JVM INSTR monitorenter ;
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public transient PrintStream format(String s, Object aobj[])
    {
        return format(Locale.getDefault(), s, aobj);
    }

    public transient PrintStream format(Locale locale, String s, Object aobj[])
    {
        this;
        JVM INSTR monitorenter ;
        if(s != null)
            break MISSING_BLOCK_LABEL_23;
        locale = JVM INSTR new #111 <Class NullPointerException>;
        locale.NullPointerException("format");
        throw locale;
        locale;
        this;
        JVM INSTR monitorexit ;
        throw locale;
        formatter.format(locale, s, aobj);
        flush(false);
        this;
        JVM INSTR monitorexit ;
        return this;
    }

    protected abstract void log(String s);

    public void print(char c)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(c);
        if(c != '\n')
            break MISSING_BLOCK_LABEL_22;
        flush(false);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void print(double d)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(d);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void print(float f)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(f);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void print(int i)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void print(long l)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void print(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(obj);
        flush(false);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    public void print(String s)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(s);
        flush(false);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void print(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(flag);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void print(char ac[])
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(ac);
        flush(false);
        this;
        JVM INSTR monitorexit ;
        return;
        ac;
        throw ac;
    }

    public transient PrintStream printf(String s, Object aobj[])
    {
        return format(s, aobj);
    }

    public transient PrintStream printf(Locale locale, String s, Object aobj[])
    {
        return format(locale, s, aobj);
    }

    public void println()
    {
        this;
        JVM INSTR monitorenter ;
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void println(char c)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(c);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void println(double d)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(d);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void println(float f)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(f);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void println(int i)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(i);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void println(long l)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(l);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void println(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(obj);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    public void println(String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(builder.length() != 0 || s == null)
            break MISSING_BLOCK_LABEL_79;
        int i = s.length();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        int k = s.indexOf('\n', j);
        if(k == -1)
            break; /* Loop/switch isn't completed */
        log(s.substring(j, k));
        j = k + 1;
        if(true) goto _L2; else goto _L1
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_76;
        log(s.substring(j));
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
        builder.append(s);
        flush(true);
          goto _L3
        s;
        throw s;
    }

    public void println(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(flag);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void println(char ac[])
    {
        this;
        JVM INSTR monitorenter ;
        builder.append(ac);
        flush(true);
        this;
        JVM INSTR monitorexit ;
        return;
        ac;
        throw ac;
    }

    protected void setError()
    {
    }

    public void write(int i)
    {
        write(new byte[] {
            (byte)i
        }, 0, 1);
    }

    public void write(byte abyte0[])
    {
        write(abyte0, 0, abyte0.length);
    }

    public void write(byte abyte0[], int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        if(decoder == null)
        {
            encodedBytes = ByteBuffer.allocate(80);
            decodedChars = CharBuffer.allocate(80);
            decoder = Charset.defaultCharset().newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        }
        j = i + j;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        int k;
        k = Math.min(encodedBytes.remaining(), j - i);
        encodedBytes.put(abyte0, i, k);
        i += k;
        encodedBytes.flip();
        CoderResult coderresult;
        do
        {
            coderresult = decoder.decode(encodedBytes, decodedChars, false);
            decodedChars.flip();
            builder.append(decodedChars);
            decodedChars.clear();
        } while(coderresult.isOverflow());
        encodedBytes.compact();
        if(true) goto _L2; else goto _L1
        abyte0;
        throw abyte0;
_L1:
        flush(false);
        this;
        JVM INSTR monitorexit ;
    }

    private final StringBuilder builder = new StringBuilder();
    private CharBuffer decodedChars;
    private CharsetDecoder decoder;
    private ByteBuffer encodedBytes;
    private final Formatter formatter;
}
