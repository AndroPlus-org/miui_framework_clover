// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.SystemClock;
import android.util.Log;
import java.net.*;
import java.util.Arrays;

// Referenced classes of package android.net:
//            EventLogTags, TrafficStats

public class SntpClient
{
    private static class InvalidServerReplyException extends Exception
    {

        public InvalidServerReplyException(String s)
        {
            super(s);
        }
    }


    public SntpClient()
    {
    }

    private static void checkValidServerReply(byte byte0, byte byte1, int i, long l)
        throws InvalidServerReplyException
    {
        if(byte0 == 3)
            throw new InvalidServerReplyException("unsynchronized server");
        if(byte1 != 4 && byte1 != 5)
            throw new InvalidServerReplyException((new StringBuilder()).append("untrusted mode: ").append(byte1).toString());
        if(i == 0 || i > 15)
            throw new InvalidServerReplyException((new StringBuilder()).append("untrusted stratum: ").append(i).toString());
        if(l == 0L)
            throw new InvalidServerReplyException("zero transmitTime");
        else
            return;
    }

    private long read32(byte abyte0[], int i)
    {
        byte byte0 = abyte0[i];
        int j = abyte0[i + 1];
        int k = abyte0[i + 2];
        int l = abyte0[i + 3];
        if((byte0 & 0x80) == 128)
            i = (byte0 & 0x7f) + 128;
        else
            i = byte0;
        if((j & 0x80) == 128)
            j = (j & 0x7f) + 128;
        if((k & 0x80) == 128)
            k = (k & 0x7f) + 128;
        if((l & 0x80) == 128)
            l = (l & 0x7f) + 128;
        return ((long)i << 24) + ((long)j << 16) + ((long)k << 8) + (long)l;
    }

    private long readTimeStamp(byte abyte0[], int i)
    {
        long l = read32(abyte0, i);
        long l1 = read32(abyte0, i + 4);
        if(l == 0L && l1 == 0L)
            return 0L;
        else
            return (l - 0x83aa7e80L) * 1000L + (l1 * 1000L) / 0x100000000L;
    }

    private void writeTimeStamp(byte abyte0[], int i, long l)
    {
        if(l == 0L)
        {
            Arrays.fill(abyte0, i, i + 8, (byte)0);
            return;
        } else
        {
            long l1 = l / 1000L;
            long l2 = l1 + 0x83aa7e80L;
            int j = i + 1;
            abyte0[i] = (byte)(int)(l2 >> 24);
            int k = j + 1;
            abyte0[j] = (byte)(int)(l2 >> 16);
            i = k + 1;
            abyte0[k] = (byte)(int)(l2 >> 8);
            j = i + 1;
            abyte0[i] = (byte)(int)(l2 >> 0);
            l = (0x100000000L * (l - 1000L * l1)) / 1000L;
            i = j + 1;
            abyte0[j] = (byte)(int)(l >> 24);
            j = i + 1;
            abyte0[i] = (byte)(int)(l >> 16);
            i = j + 1;
            abyte0[j] = (byte)(int)(l >> 8);
            abyte0[i] = (byte)(int)(Math.random() * 255D);
            return;
        }
    }

    public long getNtpTime()
    {
        return mNtpTime;
    }

    public long getNtpTimeReference()
    {
        return mNtpTimeReference;
    }

    public long getRoundTripTime()
    {
        return mRoundTripTime;
    }

    public boolean requestTime(String s, int i)
    {
        InetAddress inetaddress;
        try
        {
            inetaddress = InetAddress.getByName(s);
        }
        catch(Exception exception)
        {
            EventLogTags.writeNtpFailure(s, exception.toString());
            Log.d("SntpClient", (new StringBuilder()).append("request time failed: ").append(exception).toString());
            return false;
        }
        return requestTime(inetaddress, 123, i);
    }

    public boolean requestTime(InetAddress inetaddress, int i, int j)
    {
        DatagramPacket datagrampacket;
        Object obj;
        int k;
        Object obj1;
        datagrampacket = null;
        obj = null;
        k = TrafficStats.getAndSetThreadStatsTag(-191);
        obj1 = datagrampacket;
        DatagramSocket datagramsocket = JVM INSTR new #153 <Class DatagramSocket>;
        obj1 = datagrampacket;
        datagramsocket.DatagramSocket();
        datagramsocket.setSoTimeout(j);
        obj1 = new byte[48];
        datagrampacket = JVM INSTR new #160 <Class DatagramPacket>;
        datagrampacket.DatagramPacket(((byte []) (obj1)), obj1.length, inetaddress, i);
        obj1[0] = (byte)27;
        long l;
        long l1;
        long l2;
        l = System.currentTimeMillis();
        l1 = SystemClock.elapsedRealtime();
        writeTimeStamp(((byte []) (obj1)), 40, l);
        datagramsocket.send(datagrampacket);
        datagrampacket = JVM INSTR new #160 <Class DatagramPacket>;
        datagrampacket.DatagramPacket(((byte []) (obj1)), obj1.length);
        datagramsocket.receive(datagrampacket);
        l2 = SystemClock.elapsedRealtime();
        byte byte0;
        byte byte1;
        l += l2 - l1;
        byte0 = (byte)(obj1[0] >> 6 & 3);
        byte1 = (byte)(obj1[0] & 7);
        i = obj1[1];
        long l3;
        long l4;
        long l5;
        l3 = readTimeStamp(((byte []) (obj1)), 24);
        l4 = readTimeStamp(((byte []) (obj1)), 32);
        l5 = readTimeStamp(((byte []) (obj1)), 40);
        checkValidServerReply(byte0, byte1, i & 0xff, l5);
        l1 = l2 - l1 - (l5 - l4);
        l4 = ((l4 - l3) + (l5 - l)) / 2L;
        EventLogTags.writeNtpSuccess(inetaddress.toString(), l1, l4);
        obj1 = JVM INSTR new #60  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("SntpClient", ((StringBuilder) (obj1)).append("round trip: ").append(l1).append("ms, ").append("clock offset: ").append(l4).append("ms").toString());
        mNtpTime = l + l4;
        mNtpTimeReference = l2;
        mRoundTripTime = l1;
        if(datagramsocket != null)
            datagramsocket.close();
        TrafficStats.setThreadStatsTag(k);
        return true;
        Exception exception;
        exception;
        datagramsocket = obj;
_L4:
        obj1 = datagramsocket;
        EventLogTags.writeNtpFailure(inetaddress.toString(), exception.toString());
        obj1 = datagramsocket;
        inetaddress = JVM INSTR new #60  <Class StringBuilder>;
        obj1 = datagramsocket;
        inetaddress.StringBuilder();
        obj1 = datagramsocket;
        Log.d("SntpClient", inetaddress.append("request time failed: ").append(exception).toString());
        if(datagramsocket != null)
            datagramsocket.close();
        TrafficStats.setThreadStatsTag(k);
        return false;
        inetaddress;
_L2:
        if(obj1 != null)
            ((DatagramSocket) (obj1)).close();
        TrafficStats.setThreadStatsTag(k);
        throw inetaddress;
        inetaddress;
        obj1 = datagramsocket;
        if(true) goto _L2; else goto _L1
_L1:
        exception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final boolean DBG = true;
    private static final int NTP_LEAP_NOSYNC = 3;
    private static final int NTP_MODE_BROADCAST = 5;
    private static final int NTP_MODE_CLIENT = 3;
    private static final int NTP_MODE_SERVER = 4;
    private static final int NTP_PACKET_SIZE = 48;
    private static final int NTP_PORT = 123;
    private static final int NTP_STRATUM_DEATH = 0;
    private static final int NTP_STRATUM_MAX = 15;
    private static final int NTP_VERSION = 3;
    private static final long OFFSET_1900_TO_1970 = 0x83aa7e80L;
    private static final int ORIGINATE_TIME_OFFSET = 24;
    private static final int RECEIVE_TIME_OFFSET = 32;
    private static final int REFERENCE_TIME_OFFSET = 16;
    private static final String TAG = "SntpClient";
    private static final int TRANSMIT_TIME_OFFSET = 40;
    private long mNtpTime;
    private long mNtpTimeReference;
    private long mRoundTripTime;
}
