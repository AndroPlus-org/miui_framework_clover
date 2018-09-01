// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import org.xmlpull.v1.XmlSerializer;

public class FastXmlSerializer
    implements XmlSerializer
{

    public FastXmlSerializer()
    {
        this(32768);
    }

    public FastXmlSerializer(int i)
    {
        mIndent = false;
        mNesting = 0;
        mLineStart = true;
        if(i <= 0)
            i = 32768;
        mBufferLen = i;
        mText = new char[mBufferLen];
        mBytes = ByteBuffer.allocate(mBufferLen);
    }

    private void append(char c)
        throws IOException
    {
        int i = mPos;
        int j = i;
        if(i >= mBufferLen - 1)
        {
            flush();
            j = mPos;
        }
        mText[j] = c;
        mPos = j + 1;
    }

    private void append(String s)
        throws IOException
    {
        append(s, 0, s.length());
    }

    private void append(String s, int i, int j)
        throws IOException
    {
        if(j > mBufferLen)
        {
            int k = i + j;
            while(i < k) 
            {
                int i1 = i + mBufferLen;
                if(i1 < k)
                    j = mBufferLen;
                else
                    j = k - i;
                append(s, i, j);
                i = i1;
            }
            return;
        }
        int l = mPos;
        int j1 = l;
        if(l + j > mBufferLen)
        {
            flush();
            j1 = mPos;
        }
        s.getChars(i, i + j, mText, j1);
        mPos = j1 + j;
    }

    private void append(char ac[], int i, int j)
        throws IOException
    {
        if(j > mBufferLen)
        {
            int k = i + j;
            while(i < k) 
            {
                int i1 = i + mBufferLen;
                if(i1 < k)
                    j = mBufferLen;
                else
                    j = k - i;
                append(ac, i, j);
                i = i1;
            }
            return;
        }
        int l = mPos;
        int j1 = l;
        if(l + j > mBufferLen)
        {
            flush();
            j1 = mPos;
        }
        System.arraycopy(ac, i, mText, j1, j);
        mPos = j1 + j;
    }

    private void appendIndent(int i)
        throws IOException
    {
        int j = i * 4;
        i = j;
        if(j > sSpace.length())
            i = sSpace.length();
        append(sSpace, 0, i);
    }

    private void escapeAndAppendString(String s)
        throws IOException
    {
        int i = s.length();
        char c = (char)ESCAPE_TABLE.length;
        String as[] = ESCAPE_TABLE;
        char c1 = '\0';
        int j = 0;
        while(j < i) 
        {
            int k = s.charAt(j);
            if(k >= c)
            {
                k = c1;
            } else
            {
                String s1 = as[k];
                k = c1;
                if(s1 != null)
                {
                    if(c1 < j)
                        append(s, c1, j - c1);
                    k = j + 1;
                    append(s1);
                }
            }
            j++;
            c1 = k;
        }
        if(c1 < j)
            append(s, c1, j - c1);
    }

    private void escapeAndAppendString(char ac[], int i, int j)
        throws IOException
    {
        char c = (char)ESCAPE_TABLE.length;
        String as[] = ESCAPE_TABLE;
        int k = i;
        int l = i;
        while(l < i + j) 
        {
            int i1 = ac[l];
            if(i1 >= c)
            {
                i1 = k;
            } else
            {
                String s = as[i1];
                i1 = k;
                if(s != null)
                {
                    if(k < l)
                        append(ac, k, l - k);
                    i1 = l + 1;
                    append(s);
                }
            }
            l++;
            k = i1;
        }
        if(k < l)
            append(ac, k, l - k);
    }

    private void flushBytes()
        throws IOException
    {
        int i = mBytes.position();
        if(i > 0)
        {
            mBytes.flip();
            mOutputStream.write(mBytes.array(), 0, i);
            mBytes.clear();
        }
    }

    public XmlSerializer attribute(String s, String s1, String s2)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        append(' ');
        if(s != null)
        {
            append(s);
            append(':');
        }
        append(s1);
        append("=\"");
        escapeAndAppendString(s2);
        append('"');
        mLineStart = false;
        return this;
    }

    public void cdsect(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void comment(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void docdecl(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void endDocument()
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        flush();
    }

    public XmlSerializer endTag(String s, String s1)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        mNesting = mNesting - 1;
        if(mInTag)
        {
            append(" />\n");
        } else
        {
            if(mIndent && mLineStart)
                appendIndent(mNesting);
            append("</");
            if(s != null)
            {
                append(s);
                append(':');
            }
            append(s1);
            append(">\n");
        }
        mLineStart = true;
        mInTag = false;
        return this;
    }

    public void entityRef(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void flush()
        throws IOException
    {
        if(mPos > 0)
        {
            if(mOutputStream != null)
            {
                CharBuffer charbuffer = CharBuffer.wrap(mText, 0, mPos);
                CoderResult coderresult = mCharset.encode(charbuffer, mBytes, true);
                do
                {
                    if(coderresult.isError())
                        throw new IOException(coderresult.toString());
                    if(!coderresult.isOverflow())
                        break;
                    flushBytes();
                    coderresult = mCharset.encode(charbuffer, mBytes, true);
                } while(true);
                flushBytes();
                mOutputStream.flush();
            } else
            {
                mWriter.write(mText, 0, mPos);
                mWriter.flush();
            }
            mPos = 0;
        }
    }

    public int getDepth()
    {
        throw new UnsupportedOperationException();
    }

    public boolean getFeature(String s)
    {
        throw new UnsupportedOperationException();
    }

    public String getName()
    {
        throw new UnsupportedOperationException();
    }

    public String getNamespace()
    {
        throw new UnsupportedOperationException();
    }

    public String getPrefix(String s, boolean flag)
        throws IllegalArgumentException
    {
        throw new UnsupportedOperationException();
    }

    public Object getProperty(String s)
    {
        throw new UnsupportedOperationException();
    }

    public void ignorableWhitespace(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void processingInstruction(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void setFeature(String s, boolean flag)
        throws IllegalArgumentException, IllegalStateException
    {
        if(s.equals("http://xmlpull.org/v1/doc/features.html#indent-output"))
        {
            mIndent = true;
            return;
        } else
        {
            throw new UnsupportedOperationException();
        }
    }

    public void setOutput(OutputStream outputstream, String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        if(outputstream == null)
            throw new IllegalArgumentException();
        try
        {
            mCharset = Charset.forName(s).newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        }
        // Misplaced declaration of an exception variable
        catch(OutputStream outputstream)
        {
            throw (UnsupportedEncodingException)(new UnsupportedEncodingException(s)).initCause(outputstream);
        }
        // Misplaced declaration of an exception variable
        catch(OutputStream outputstream)
        {
            throw (UnsupportedEncodingException)(new UnsupportedEncodingException(s)).initCause(outputstream);
        }
        mOutputStream = outputstream;
    }

    public void setOutput(Writer writer)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        mWriter = writer;
    }

    public void setPrefix(String s, String s1)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void setProperty(String s, Object obj)
        throws IllegalArgumentException, IllegalStateException
    {
        throw new UnsupportedOperationException();
    }

    public void startDocument(String s, Boolean boolean1)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("<?xml version='1.0' encoding='utf-8' standalone='");
        if(boolean1.booleanValue())
            s = "yes";
        else
            s = "no";
        append(stringbuilder.append(s).append("' ?>\n").toString());
        mLineStart = true;
    }

    public XmlSerializer startTag(String s, String s1)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        if(mInTag)
            append(">\n");
        if(mIndent)
            appendIndent(mNesting);
        mNesting = mNesting + 1;
        append('<');
        if(s != null)
        {
            append(s);
            append(':');
        }
        append(s1);
        mInTag = true;
        mLineStart = false;
        return this;
    }

    public XmlSerializer text(String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        boolean flag = false;
        if(mInTag)
        {
            append(">");
            mInTag = false;
        }
        escapeAndAppendString(s);
        if(mIndent)
        {
            boolean flag1 = flag;
            if(s.length() > 0)
            {
                flag1 = flag;
                if(s.charAt(s.length() - 1) == '\n')
                    flag1 = true;
            }
            mLineStart = flag1;
        }
        return this;
    }

    public XmlSerializer text(char ac[], int i, int j)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        boolean flag = false;
        if(mInTag)
        {
            append(">");
            mInTag = false;
        }
        escapeAndAppendString(ac, i, j);
        if(mIndent)
        {
            if(ac[(i + j) - 1] == '\n')
                flag = true;
            mLineStart = flag;
        }
        return this;
    }

    private static final int DEFAULT_BUFFER_LEN = 32768;
    private static final String ESCAPE_TABLE[] = {
        "&#0;", "&#1;", "&#2;", "&#3;", "&#4;", "&#5;", "&#6;", "&#7;", "&#8;", "&#9;", 
        "&#10;", "&#11;", "&#12;", "&#13;", "&#14;", "&#15;", "&#16;", "&#17;", "&#18;", "&#19;", 
        "&#20;", "&#21;", "&#22;", "&#23;", "&#24;", "&#25;", "&#26;", "&#27;", "&#28;", "&#29;", 
        "&#30;", "&#31;", null, null, "&quot;", null, null, null, "&amp;", null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        "&lt;", null, "&gt;", null
    };
    private static String sSpace = "                                                              ";
    private final int mBufferLen;
    private ByteBuffer mBytes;
    private CharsetEncoder mCharset;
    private boolean mInTag;
    private boolean mIndent;
    private boolean mLineStart;
    private int mNesting;
    private OutputStream mOutputStream;
    private int mPos;
    private final char mText[];
    private Writer mWriter;

}
