// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.midi;


public final class MidiConstants
{

    public MidiConstants()
    {
    }

    public static boolean allowRunningStatus(byte byte0)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(byte0 >= -128)
        {
            flag1 = flag;
            if(byte0 < -16)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean cancelsRunningStatus(byte byte0)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(byte0 >= -16)
        {
            flag1 = flag;
            if(byte0 <= -9)
                flag1 = true;
        }
        return flag1;
    }

    public static int getBytesPerMessage(byte byte0)
    {
        byte0 &= 0xff;
        if(byte0 >= 240)
            return SYSTEM_BYTE_LENGTHS[byte0 & 0xf];
        if(byte0 >= 128)
            return CHANNEL_BYTE_LENGTHS[(byte0 >> 4) - 8];
        else
            return 0;
    }

    public static boolean isAllActiveSensing(byte abyte0[], int i, int j)
    {
        boolean flag = false;
        int k = 0;
        for(int l = 0; l < j;)
        {
            int i1 = k;
            if(abyte0[i + l] != -2)
                i1 = k + 1;
            l++;
            k = i1;
        }

        if(k == 0)
            flag = true;
        return flag;
    }

    public static final int CHANNEL_BYTE_LENGTHS[] = {
        3, 3, 3, 3, 2, 2, 3
    };
    public static final byte STATUS_ACTIVE_SENSING = -2;
    public static final byte STATUS_CHANNEL_MASK = 15;
    public static final byte STATUS_CHANNEL_PRESSURE = -48;
    public static final byte STATUS_COMMAND_MASK = -16;
    public static final byte STATUS_CONTINUE = -5;
    public static final byte STATUS_CONTROL_CHANGE = -80;
    public static final byte STATUS_END_SYSEX = -9;
    public static final byte STATUS_MIDI_TIME_CODE = -15;
    public static final byte STATUS_NOTE_OFF = -128;
    public static final byte STATUS_NOTE_ON = -112;
    public static final byte STATUS_PITCH_BEND = -32;
    public static final byte STATUS_POLYPHONIC_AFTERTOUCH = -96;
    public static final byte STATUS_PROGRAM_CHANGE = -64;
    public static final byte STATUS_RESET = -1;
    public static final byte STATUS_SONG_POSITION = -14;
    public static final byte STATUS_SONG_SELECT = -13;
    public static final byte STATUS_START = -6;
    public static final byte STATUS_STOP = -4;
    public static final byte STATUS_SYSTEM_EXCLUSIVE = -16;
    public static final byte STATUS_TIMING_CLOCK = -8;
    public static final byte STATUS_TUNE_REQUEST = -10;
    public static final int SYSTEM_BYTE_LENGTHS[] = {
        1, 2, 3, 2, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1
    };

}
