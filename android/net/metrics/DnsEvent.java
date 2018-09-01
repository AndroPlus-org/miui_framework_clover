// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.net.NetworkCapabilities;
import com.android.internal.util.BitUtils;
import java.util.Arrays;

public final class DnsEvent
{

    public DnsEvent(int i, long l, int j)
    {
        netId = i;
        transports = l;
        eventTypes = new byte[j];
        returnCodes = new byte[j];
        latenciesMs = new int[j];
    }

    public void addResult(byte byte0, byte byte1, int i)
    {
        if(eventCount >= 20000)
            return;
        if(eventCount == eventTypes.length)
            resize((int)((double)eventCount * 1.3999999999999999D));
        eventTypes[eventCount] = byte0;
        returnCodes[eventCount] = byte1;
        latenciesMs[eventCount] = i;
        eventCount = eventCount + 1;
    }

    public void resize(int i)
    {
        eventTypes = Arrays.copyOf(eventTypes, i);
        returnCodes = Arrays.copyOf(returnCodes, i);
        latenciesMs = Arrays.copyOf(latenciesMs, i);
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("DnsEvent(")).append(netId).append(", ");
        int ai[] = BitUtils.unpackBits(transports);
        int i = 0;
        for(int j = ai.length; i < j; i++)
            stringbuilder.append(NetworkCapabilities.transportNameOf(ai[i])).append(", ");

        return stringbuilder.append(eventCount).append(" events)").toString();
    }

    private static final int SIZE_LIMIT = 20000;
    public int eventCount;
    public byte eventTypes[];
    public int latenciesMs[];
    public final int netId;
    public byte returnCodes[];
    public final long transports;
}
