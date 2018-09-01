// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

public class LineBreakBufferedWriter extends PrintWriter
{

    public LineBreakBufferedWriter(Writer writer, int i)
    {
        this(writer, i, 16);
    }

    public LineBreakBufferedWriter(Writer writer, int i, int j)
    {
        super(writer);
        lastNewline = -1;
        buffer = new char[Math.min(j, i)];
        bufferIndex = 0;
        bufferSize = i;
        lineSeparator = System.getProperty("line.separator");
    }

    private void appendToBuffer(String s, int i, int j)
    {
        if(bufferIndex + j > buffer.length)
            ensureCapacity(bufferIndex + j);
        s.getChars(i, i + j, buffer, bufferIndex);
        bufferIndex = bufferIndex + j;
    }

    private void appendToBuffer(char ac[], int i, int j)
    {
        if(bufferIndex + j > buffer.length)
            ensureCapacity(bufferIndex + j);
        System.arraycopy(ac, i, buffer, bufferIndex, j);
        bufferIndex = bufferIndex + j;
    }

    private void ensureCapacity(int i)
    {
        int j = buffer.length * 2 + 2;
        int k = j;
        if(j < i)
            k = i;
        buffer = Arrays.copyOf(buffer, k);
    }

    private void removeFromBuffer(int i)
    {
        i = bufferIndex - i;
        if(i > 0)
        {
            System.arraycopy(buffer, bufferIndex - i, buffer, 0, i);
            bufferIndex = i;
        } else
        {
            bufferIndex = 0;
        }
    }

    private void writeBuffer(int i)
    {
        if(i > 0)
            super.write(buffer, 0, i);
    }

    public void flush()
    {
        writeBuffer(bufferIndex);
        bufferIndex = 0;
        super.flush();
    }

    public void println()
    {
        write(lineSeparator);
    }

    public void write(int i)
    {
        if(bufferIndex < buffer.length)
        {
            buffer[bufferIndex] = (char)i;
            bufferIndex = bufferIndex + 1;
            if((char)i == '\n')
                lastNewline = bufferIndex;
        } else
        {
            write(new char[] {
                (char)i
            }, 0, 1);
        }
    }

    public void write(String s, int i, int j)
    {
        while(bufferIndex + j > bufferSize) 
        {
            int k = -1;
            int l = bufferSize;
            int i1 = bufferIndex;
            for(int j1 = 0; j1 < l - i1;)
            {
                int i2 = k;
                if(s.charAt(i + j1) == '\n')
                {
                    if(bufferIndex + j1 >= bufferSize)
                        break;
                    i2 = j1;
                }
                j1++;
                k = i2;
            }

            if(k != -1)
            {
                appendToBuffer(s, i, k);
                writeBuffer(bufferIndex);
                bufferIndex = 0;
                lastNewline = -1;
                i += k + 1;
                j -= k + 1;
            } else
            if(lastNewline != -1)
            {
                writeBuffer(lastNewline);
                removeFromBuffer(lastNewline + 1);
                lastNewline = -1;
            } else
            {
                int k1 = bufferSize - bufferIndex;
                appendToBuffer(s, i, k1);
                writeBuffer(bufferIndex);
                bufferIndex = 0;
                i += k1;
                j -= k1;
            }
        }
        if(j <= 0) goto _L2; else goto _L1
_L1:
        int l1;
        appendToBuffer(s, i, j);
        l1 = j - 1;
_L7:
        if(l1 < 0) goto _L2; else goto _L3
_L3:
        if(s.charAt(i + l1) != '\n') goto _L5; else goto _L4
_L4:
        lastNewline = (bufferIndex - j) + l1;
_L2:
        return;
_L5:
        l1--;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void write(char ac[], int i, int j)
    {
        while(bufferIndex + j > bufferSize) 
        {
            int k = -1;
            int l = bufferSize;
            int i1 = bufferIndex;
            for(int j1 = 0; j1 < l - i1;)
            {
                int i2 = k;
                if(ac[i + j1] == '\n')
                {
                    if(bufferIndex + j1 >= bufferSize)
                        break;
                    i2 = j1;
                }
                j1++;
                k = i2;
            }

            if(k != -1)
            {
                appendToBuffer(ac, i, k);
                writeBuffer(bufferIndex);
                bufferIndex = 0;
                lastNewline = -1;
                i += k + 1;
                j -= k + 1;
            } else
            if(lastNewline != -1)
            {
                writeBuffer(lastNewline);
                removeFromBuffer(lastNewline + 1);
                lastNewline = -1;
            } else
            {
                int k1 = bufferSize - bufferIndex;
                appendToBuffer(ac, i, k1);
                writeBuffer(bufferIndex);
                bufferIndex = 0;
                i += k1;
                j -= k1;
            }
        }
        if(j <= 0) goto _L2; else goto _L1
_L1:
        int l1;
        appendToBuffer(ac, i, j);
        l1 = j - 1;
_L7:
        if(l1 < 0) goto _L2; else goto _L3
_L3:
        if(ac[i + l1] != '\n') goto _L5; else goto _L4
_L4:
        lastNewline = (bufferIndex - j) + l1;
_L2:
        return;
_L5:
        l1--;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private char buffer[];
    private int bufferIndex;
    private final int bufferSize;
    private int lastNewline;
    private final String lineSeparator;
}
