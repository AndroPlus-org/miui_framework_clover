// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import java.io.IOException;

public abstract class MidiReceiver
{

    public MidiReceiver()
    {
        mMaxMessageSize = 0x7fffffff;
    }

    public MidiReceiver(int i)
    {
        mMaxMessageSize = i;
    }

    public void flush()
        throws IOException
    {
        onFlush();
    }

    public final int getMaxMessageSize()
    {
        return mMaxMessageSize;
    }

    public void onFlush()
        throws IOException
    {
    }

    public abstract void onSend(byte abyte0[], int i, int j, long l)
        throws IOException;

    public void send(byte abyte0[], int i, int j)
        throws IOException
    {
        send(abyte0, i, j, 0L);
    }

    public void send(byte abyte0[], int i, int j, long l)
        throws IOException
    {
        int k = getMaxMessageSize();
        while(j > 0) 
        {
            int i1;
            if(j > k)
                i1 = k;
            else
                i1 = j;
            onSend(abyte0, i, i1, l);
            i += i1;
            j -= i1;
        }
    }

    private final int mMaxMessageSize;
}
