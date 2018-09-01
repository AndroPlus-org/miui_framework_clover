// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.*;

// Referenced classes of package android.util:
//            Base64DataException

public class Base64InputStream extends FilterInputStream
{

    public Base64InputStream(InputStream inputstream, int i)
    {
        this(inputstream, i, false);
    }

    public Base64InputStream(InputStream inputstream, int i, boolean flag)
    {
        super(inputstream);
        eof = false;
        inputBuffer = new byte[2048];
        if(flag)
            coder = new Base64.Encoder(i, null);
        else
            coder = new Base64.Decoder(i, null);
        coder.output = new byte[coder.maxOutputSize(2048)];
        outputStart = 0;
        outputEnd = 0;
    }

    private void refill()
        throws IOException
    {
        if(eof)
            return;
        int i = in.read(inputBuffer);
        boolean flag;
        if(i == -1)
        {
            eof = true;
            flag = coder.process(EMPTY, 0, 0, true);
        } else
        {
            flag = coder.process(inputBuffer, 0, i, false);
        }
        if(!flag)
        {
            throw new Base64DataException("bad base-64");
        } else
        {
            outputEnd = coder.op;
            outputStart = 0;
            return;
        }
    }

    public int available()
    {
        return outputEnd - outputStart;
    }

    public void close()
        throws IOException
    {
        in.close();
        inputBuffer = null;
    }

    public void mark(int i)
    {
        throw new UnsupportedOperationException();
    }

    public boolean markSupported()
    {
        return false;
    }

    public int read()
        throws IOException
    {
        if(outputStart >= outputEnd)
            refill();
        if(outputStart >= outputEnd)
        {
            return -1;
        } else
        {
            byte abyte0[] = coder.output;
            int i = outputStart;
            outputStart = i + 1;
            return abyte0[i] & 0xff;
        }
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if(outputStart >= outputEnd)
            refill();
        if(outputStart >= outputEnd)
        {
            return -1;
        } else
        {
            j = Math.min(j, outputEnd - outputStart);
            System.arraycopy(coder.output, outputStart, abyte0, i, j);
            outputStart = outputStart + j;
            return j;
        }
    }

    public void reset()
    {
        throw new UnsupportedOperationException();
    }

    public long skip(long l)
        throws IOException
    {
        if(outputStart >= outputEnd)
            refill();
        if(outputStart >= outputEnd)
        {
            return 0L;
        } else
        {
            l = Math.min(l, outputEnd - outputStart);
            outputStart = (int)((long)outputStart + l);
            return l;
        }
    }

    private static final int BUFFER_SIZE = 2048;
    private static byte EMPTY[] = new byte[0];
    private final Base64.Coder coder;
    private boolean eof;
    private byte inputBuffer[];
    private int outputEnd;
    private int outputStart;

}
