// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

public class IndentingPrintWriter extends PrintWriter
{

    public IndentingPrintWriter(Writer writer, String s)
    {
        this(writer, s, -1);
    }

    public IndentingPrintWriter(Writer writer, String s, int i)
    {
        super(writer);
        mIndentBuilder = new StringBuilder();
        mEmptyLine = true;
        mSingleChar = new char[1];
        mSingleIndent = s;
        mWrapLength = i;
    }

    private void maybeWriteIndent()
    {
        if(mEmptyLine)
        {
            mEmptyLine = false;
            if(mIndentBuilder.length() != 0)
            {
                if(mCurrentIndent == null)
                    mCurrentIndent = mIndentBuilder.toString().toCharArray();
                super.write(mCurrentIndent, 0, mCurrentIndent.length);
            }
        }
    }

    public void decreaseIndent()
    {
        mIndentBuilder.delete(0, mSingleIndent.length());
        mCurrentIndent = null;
    }

    public void increaseIndent()
    {
        mIndentBuilder.append(mSingleIndent);
        mCurrentIndent = null;
    }

    public void printHexPair(String s, int i)
    {
        print((new StringBuilder()).append(s).append("=0x").append(Integer.toHexString(i)).append(" ").toString());
    }

    public void printPair(String s, Object obj)
    {
        print((new StringBuilder()).append(s).append("=").append(String.valueOf(obj)).append(" ").toString());
    }

    public void printPair(String s, Object aobj[])
    {
        print((new StringBuilder()).append(s).append("=").append(Arrays.toString(aobj)).append(" ").toString());
    }

    public void println()
    {
        write(10);
    }

    public void write(int i)
    {
        mSingleChar[0] = (char)i;
        write(mSingleChar, 0, 1);
    }

    public void write(String s, int i, int j)
    {
        char ac[] = new char[j];
        s.getChars(i, j - i, ac, 0);
        write(ac, 0, j);
    }

    public void write(char ac[], int i, int j)
    {
        int k = mIndentBuilder.length();
        int l = i;
        int i1 = i;
        while(i1 < i + j) 
        {
            int j1 = i1 + 1;
            char c = ac[i1];
            mCurrentLength = mCurrentLength + 1;
            i1 = l;
            if(c == '\n')
            {
                maybeWriteIndent();
                super.write(ac, l, j1 - l);
                i1 = j1;
                mEmptyLine = true;
                mCurrentLength = 0;
            }
            l = i1;
            if(mWrapLength <= 0)
                continue;
            l = i1;
            if(mCurrentLength >= mWrapLength - k)
                if(!mEmptyLine)
                {
                    super.write(10);
                    mEmptyLine = true;
                    mCurrentLength = j1 - i1;
                    l = i1;
                } else
                {
                    maybeWriteIndent();
                    super.write(ac, i1, j1 - i1);
                    super.write(10);
                    mEmptyLine = true;
                    l = j1;
                    mCurrentLength = 0;
                }
            i1 = j1;
        }
        if(l != i1)
        {
            maybeWriteIndent();
            super.write(ac, l, i1 - l);
        }
    }

    private char mCurrentIndent[];
    private int mCurrentLength;
    private boolean mEmptyLine;
    private StringBuilder mIndentBuilder;
    private char mSingleChar[];
    private final String mSingleIndent;
    private final int mWrapLength;
}
