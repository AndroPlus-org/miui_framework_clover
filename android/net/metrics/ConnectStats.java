// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.net.NetworkCapabilities;
import android.system.OsConstants;
import android.util.IntArray;
import android.util.SparseIntArray;
import com.android.internal.util.BitUtils;
import com.android.internal.util.TokenBucket;

public class ConnectStats
{

    public ConnectStats(int i, long l, TokenBucket tokenbucket, int j)
    {
        connectCount = 0;
        connectBlockingCount = 0;
        ipv6ConnectCount = 0;
        netId = i;
        transports = l;
        mLatencyTb = tokenbucket;
        mMaxLatencyRecords = j;
    }

    private void countConnect(int i, String s)
    {
        connectCount = connectCount + 1;
        if(!isNonBlocking(i))
            connectBlockingCount = connectBlockingCount + 1;
        if(isIPv6(s))
            ipv6ConnectCount = ipv6ConnectCount + 1;
    }

    private void countError(int i)
    {
        int j = errnos.get(i, 0);
        errnos.put(i, j + 1);
    }

    private void countLatency(int i, int j)
    {
        if(isNonBlocking(i))
            return;
        if(!mLatencyTb.get())
            return;
        if(latencies.size() >= mMaxLatencyRecords)
        {
            return;
        } else
        {
            latencies.add(j);
            return;
        }
    }

    private static boolean isIPv6(String s)
    {
        return s.contains(":");
    }

    private static boolean isNonBlocking(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != EINPROGRESS)
            if(i == EALREADY)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static boolean isSuccess(int i)
    {
        boolean flag;
        if(i != 0)
            flag = isNonBlocking(i);
        else
            flag = true;
        return flag;
    }

    public void addEvent(int i, int j, String s)
    {
        if(isSuccess(i))
        {
            countConnect(i, s);
            countLatency(i, j);
        } else
        {
            countError(i);
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("ConnectStats(")).append(netId).append(", ");
        int ai[] = BitUtils.unpackBits(transports);
        int i = ai.length;
        for(int j = 0; j < i; j++)
            stringbuilder.append(NetworkCapabilities.transportNameOf(ai[j])).append(", ");

        stringbuilder.append(String.format("%d success, ", new Object[] {
            Integer.valueOf(connectCount)
        }));
        stringbuilder.append(String.format("%d blocking, ", new Object[] {
            Integer.valueOf(connectBlockingCount)
        }));
        stringbuilder.append(String.format("%d IPv6 dst", new Object[] {
            Integer.valueOf(ipv6ConnectCount)
        }));
        for(int k = 0; k < errnos.size(); k++)
            stringbuilder.append(String.format(", %s: %d", new Object[] {
                OsConstants.errnoName(errnos.keyAt(k)), Integer.valueOf(errnos.valueAt(k))
            }));

        return stringbuilder.append(")").toString();
    }

    private static final int EALREADY;
    private static final int EINPROGRESS;
    public int connectBlockingCount;
    public int connectCount;
    public final SparseIntArray errnos = new SparseIntArray();
    public int ipv6ConnectCount;
    public final IntArray latencies = new IntArray();
    public final TokenBucket mLatencyTb;
    public final int mMaxLatencyRecords;
    public final int netId;
    public final long transports;

    static 
    {
        EALREADY = OsConstants.EALREADY;
        EINPROGRESS = OsConstants.EINPROGRESS;
    }
}
