// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.midi;

import android.media.midi.MidiReceiver;
import java.io.IOException;

// Referenced classes of package com.android.internal.midi:
//            MidiConstants

public class MidiFramer extends MidiReceiver
{

    public MidiFramer(MidiReceiver midireceiver)
    {
        TAG = "MidiFramer";
        mBuffer = new byte[3];
        mReceiver = midireceiver;
    }

    public static String formatMidiData(byte abyte0[], int i, int j)
    {
        String s = (new StringBuilder()).append("MIDI+").append(i).append(" : ").toString();
        for(int k = 0; k < j; k++)
            s = (new StringBuilder()).append(s).append(String.format("0x%02X, ", new Object[] {
                Byte.valueOf(abyte0[i + k])
            })).toString();

        return s;
    }

    public void onSend(byte abyte0[], int i, int j, long l)
        throws IOException
    {
        int k;
        int i1;
        int j1;
        if(mInSysEx)
            k = i;
        else
            k = -1;
        i1 = 0;
        j1 = i;
        while(i1 < j) 
        {
            byte byte0 = abyte0[j1];
            i = byte0 & 0xff;
            if(i >= 128)
            {
                if(i < 240)
                {
                    mRunningStatus = byte0;
                    mCount = 1;
                    mNeeded = MidiConstants.getBytesPerMessage(byte0) - 1;
                    i = k;
                } else
                if(i < 248)
                {
                    if(i == 240)
                    {
                        mInSysEx = true;
                        i = j1;
                    } else
                    if(i == 247)
                    {
                        i = k;
                        if(mInSysEx)
                        {
                            mReceiver.send(abyte0, k, (j1 - k) + 1, l);
                            mInSysEx = false;
                            i = -1;
                        }
                    } else
                    {
                        mBuffer[0] = byte0;
                        mRunningStatus = (byte)0;
                        mCount = 1;
                        mNeeded = MidiConstants.getBytesPerMessage(byte0) - 1;
                        i = k;
                    }
                } else
                {
                    i = k;
                    if(mInSysEx)
                    {
                        mReceiver.send(abyte0, k, j1 - k, l);
                        i = j1 + 1;
                    }
                    mReceiver.send(abyte0, j1, 1, l);
                }
            } else
            {
                i = k;
                if(!mInSysEx)
                {
                    byte abyte1[] = mBuffer;
                    i = mCount;
                    mCount = i + 1;
                    abyte1[i] = byte0;
                    int k1 = mNeeded - 1;
                    mNeeded = k1;
                    i = k;
                    if(k1 == 0)
                    {
                        if(mRunningStatus != 0)
                            mBuffer[0] = mRunningStatus;
                        mReceiver.send(mBuffer, 0, mCount, l);
                        mNeeded = MidiConstants.getBytesPerMessage(mBuffer[0]) - 1;
                        mCount = 1;
                        i = k;
                    }
                }
            }
            j1++;
            i1++;
            k = i;
        }
        if(k >= 0 && k < j1)
            mReceiver.send(abyte0, k, j1 - k, l);
    }

    public String TAG;
    private byte mBuffer[];
    private int mCount;
    private boolean mInSysEx;
    private int mNeeded;
    private MidiReceiver mReceiver;
    private byte mRunningStatus;
}
