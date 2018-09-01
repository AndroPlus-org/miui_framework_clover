// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;


class MidiPortImpl
{

    MidiPortImpl()
    {
    }

    public static int getDataOffset(byte abyte0[], int i)
    {
        return 1;
    }

    public static int getDataSize(byte abyte0[], int i)
    {
        return i - 9;
    }

    public static long getPacketTimestamp(byte abyte0[], int i)
    {
        long l = 0L;
        boolean flag = false;
        int j = i;
        for(i = ((flag) ? 1 : 0); i < 8; i++)
        {
            j--;
            l = l << 8 | (long)(abyte0[j] & 0xff);
        }

        return l;
    }

    public static int getPacketType(byte abyte0[], int i)
    {
        return abyte0[0];
    }

    public static int packData(byte abyte0[], int i, int j, long l, byte abyte1[])
    {
        int k = j;
        if(j > 1015)
            k = 1015;
        abyte1[0] = (byte)1;
        System.arraycopy(abyte0, i, abyte1, 1, k);
        j = 0;
        for(i = k + 1; j < 8; i++)
        {
            abyte1[i] = (byte)(int)l;
            l >>= 8;
            j++;
        }

        return i;
    }

    public static int packFlush(byte abyte0[])
    {
        abyte0[0] = (byte)2;
        return 1;
    }

    private static final int DATA_PACKET_OVERHEAD = 9;
    public static final int MAX_PACKET_DATA_SIZE = 1015;
    public static final int MAX_PACKET_SIZE = 1024;
    public static final int PACKET_TYPE_DATA = 1;
    public static final int PACKET_TYPE_FLUSH = 2;
    private static final String TAG = "MidiPort";
    private static final int TIMESTAMP_SIZE = 8;
}
