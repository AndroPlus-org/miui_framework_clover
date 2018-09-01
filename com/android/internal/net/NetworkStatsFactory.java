// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.net;

import android.net.NetworkStats;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.ArrayMap;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.ProcFileReader;
import com.android.server.NetworkManagementSocketTagger;
import java.io.*;
import java.net.ProtocolException;
import libcore.io.IoUtils;

public class NetworkStatsFactory
{

    public NetworkStatsFactory()
    {
        this(new File("/proc/"));
    }

    public NetworkStatsFactory(File file)
    {
        mStatsXtIfaceAll = new File(file, "net/xt_qtaguid/iface_stat_all");
        mStatsXtIfaceFmt = new File(file, "net/xt_qtaguid/iface_stat_fmt");
        mStatsXtUid = new File(file, "net/xt_qtaguid/stats");
    }

    public static NetworkStats javaReadNetworkStatsDetail(File file, int i, String as[], int j)
        throws IOException
    {
        android.os.StrictMode.ThreadPolicy threadpolicy;
        NetworkStats networkstats;
        android.net.NetworkStats.Entry entry;
        int k;
        boolean flag;
        int l;
        int i1;
        Object obj;
        StringBuilder stringbuilder;
        Object obj1;
        threadpolicy = StrictMode.allowThreadDiskReads();
        networkstats = new NetworkStats(SystemClock.elapsedRealtime(), 24);
        entry = new android.net.NetworkStats.Entry();
        k = 1;
        flag = true;
        l = 1;
        i1 = 1;
        obj = null;
        stringbuilder = null;
        obj1 = obj;
        Object obj2 = JVM INSTR new #94  <Class ProcFileReader>;
        obj1 = obj;
        FileInputStream fileinputstream = JVM INSTR new #96  <Class FileInputStream>;
        obj1 = obj;
        fileinputstream.FileInputStream(file);
        obj1 = obj;
        ((ProcFileReader) (obj2)).ProcFileReader(fileinputstream);
        ((ProcFileReader) (obj2)).finishLine();
_L5:
        k = l;
        if(!((ProcFileReader) (obj2)).hasMoreData()) goto _L2; else goto _L1
_L1:
        k = l;
        l = ((ProcFileReader) (obj2)).nextInt();
        if(l == i1 + 1) goto _L4; else goto _L3
_L3:
        k = l;
        as = JVM INSTR new #113 <Class ProtocolException>;
        k = l;
        file = JVM INSTR new #115 <Class StringBuilder>;
        k = l;
        file.StringBuilder();
        k = l;
        as.ProtocolException(file.append("inconsistent idx=").append(l).append(" after lastIdx=").append(i1).toString());
        k = l;
        try
        {
            throw as;
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            file = ((File) (obj2));
        }
_L7:
        obj1 = file;
        obj2 = JVM INSTR new #113 <Class ProtocolException>;
        obj1 = file;
        stringbuilder = JVM INSTR new #115 <Class StringBuilder>;
        obj1 = file;
        stringbuilder.StringBuilder();
        obj1 = file;
        ((ProtocolException) (obj2)).ProtocolException(stringbuilder.append("problem parsing idx ").append(k).toString(), as);
        obj1 = file;
        throw obj2;
        file;
_L6:
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        StrictMode.setThreadPolicy(threadpolicy);
        throw file;
_L4:
        i1 = l;
        k = l;
        entry.iface = ((ProcFileReader) (obj2)).nextString();
        k = l;
        entry.tag = NetworkManagementSocketTagger.kernelToTag(((ProcFileReader) (obj2)).nextString());
        k = l;
        entry.uid = ((ProcFileReader) (obj2)).nextInt();
        k = l;
        entry.set = ((ProcFileReader) (obj2)).nextInt();
        k = l;
        entry.rxBytes = ((ProcFileReader) (obj2)).nextLong();
        k = l;
        entry.rxPackets = ((ProcFileReader) (obj2)).nextLong();
        k = l;
        entry.txBytes = ((ProcFileReader) (obj2)).nextLong();
        k = l;
        entry.txPackets = ((ProcFileReader) (obj2)).nextLong();
        if(as == null)
            break MISSING_BLOCK_LABEL_394;
        k = l;
        if(!ArrayUtils.contains(as, entry.iface))
            break MISSING_BLOCK_LABEL_442;
        if(i == -1)
            break MISSING_BLOCK_LABEL_412;
        k = l;
        if(i != entry.uid)
            break MISSING_BLOCK_LABEL_442;
        if(j == -1)
            break MISSING_BLOCK_LABEL_430;
        k = l;
        if(j != entry.tag)
            break MISSING_BLOCK_LABEL_442;
        k = l;
        networkstats.addValues(entry);
        k = l;
        ((ProcFileReader) (obj2)).finishLine();
          goto _L5
        file;
        obj1 = obj2;
          goto _L6
_L2:
        IoUtils.closeQuietly(((AutoCloseable) (obj2)));
        StrictMode.setThreadPolicy(threadpolicy);
        return networkstats;
        as;
        k = ((flag) ? 1 : 0);
        file = stringbuilder;
          goto _L7
    }

    public static native int nativeReadNetworkStatsDetail(NetworkStats networkstats, String s, int i, String as[], int j);

    public static void noteStackedIface(String s, String s1)
    {
        ArrayMap arraymap = sStackedIfaces;
        arraymap;
        JVM INSTR monitorenter ;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_22;
        sStackedIfaces.put(s, s1);
_L1:
        arraymap;
        JVM INSTR monitorexit ;
        return;
        sStackedIfaces.remove(s);
          goto _L1
        s;
        throw s;
    }

    private NetworkStats readNetworkStatsDetailInternal(int i, String as[], int j, NetworkStats networkstats)
        throws IOException
    {
        NetworkStats networkstats1;
        if(networkstats != null)
        {
            networkstats1 = networkstats;
            networkstats.setElapsedRealtime(SystemClock.elapsedRealtime());
        } else
        {
            networkstats1 = new NetworkStats(SystemClock.elapsedRealtime(), -1);
        }
        if(nativeReadNetworkStatsDetail(networkstats1, mStatsXtUid.getAbsolutePath(), i, as, j) != 0)
            throw new IOException("Failed to parse network stats");
        else
            return networkstats1;
    }

    public void assertEquals(NetworkStats networkstats, NetworkStats networkstats1)
    {
        if(networkstats.size() != networkstats1.size())
            throw new AssertionError((new StringBuilder()).append("Expected size ").append(networkstats.size()).append(", actual size ").append(networkstats1.size()).toString());
        android.net.NetworkStats.Entry entry = null;
        android.net.NetworkStats.Entry entry1 = null;
        for(int i = 0; i < networkstats.size(); i++)
        {
            entry = networkstats.getValues(i, entry);
            entry1 = networkstats1.getValues(i, entry1);
            if(!entry.equals(entry1))
                throw new AssertionError((new StringBuilder()).append("Expected row ").append(i).append(": ").append(entry).append(", actual row ").append(entry1).toString());
        }

    }

    public NetworkStats readNetworkStatsDetail()
        throws IOException
    {
        return readNetworkStatsDetail(-1, null, -1, null);
    }

    public NetworkStats readNetworkStatsDetail(int i, String as[], int j, NetworkStats networkstats)
        throws IOException
    {
        NetworkStats networkstats1 = readNetworkStatsDetailInternal(i, as, j, networkstats);
        as = sStackedIfaces;
        as;
        JVM INSTR monitorenter ;
        networkstats = new ArrayMap(sStackedIfaces);
        as;
        JVM INSTR monitorexit ;
        NetworkStats networkstats2;
        networkstats2 = new NetworkStats(0L, networkstats.size());
        as = null;
        i = 0;
        while(i < networkstats1.size()) 
        {
            as = networkstats1.getValues(i, as);
            if(((android.net.NetworkStats.Entry) (as)).iface != null && !(((android.net.NetworkStats.Entry) (as)).iface.startsWith("v4-") ^ true))
            {
                Object obj = (String)networkstats.get(((android.net.NetworkStats.Entry) (as)).iface);
                if(obj != null)
                {
                    obj = new android.net.NetworkStats.Entry(((String) (obj)), 0, 0, 0, 0L, 0L, 0L, 0L, 0L);
                    obj.rxBytes = ((android.net.NetworkStats.Entry) (obj)).rxBytes - (((android.net.NetworkStats.Entry) (as)).rxBytes + ((android.net.NetworkStats.Entry) (as)).rxPackets * 20L);
                    obj.txBytes = ((android.net.NetworkStats.Entry) (obj)).txBytes - (((android.net.NetworkStats.Entry) (as)).txBytes + ((android.net.NetworkStats.Entry) (as)).txPackets * 20L);
                    obj.rxPackets = ((android.net.NetworkStats.Entry) (obj)).rxPackets - ((android.net.NetworkStats.Entry) (as)).rxPackets;
                    obj.txPackets = ((android.net.NetworkStats.Entry) (obj)).txPackets - ((android.net.NetworkStats.Entry) (as)).txPackets;
                    networkstats2.combineValues(((android.net.NetworkStats.Entry) (obj)));
                    as.rxBytes = ((android.net.NetworkStats.Entry) (as)).rxPackets * 20L;
                    as.txBytes = ((android.net.NetworkStats.Entry) (as)).txPackets * 20L;
                    as.rxPackets = 0L;
                    as.txPackets = 0L;
                    networkstats1.combineValues(as);
                }
            }
            i++;
        }
        break MISSING_BLOCK_LABEL_269;
        networkstats;
        throw networkstats;
        networkstats1.combineAllValues(networkstats2);
        return networkstats1;
    }

    public NetworkStats readNetworkStatsSummaryDev()
        throws IOException
    {
        android.os.StrictMode.ThreadPolicy threadpolicy;
        NetworkStats networkstats;
        android.net.NetworkStats.Entry entry;
        Object obj;
        ProtocolException protocolexception;
        Object obj2;
        threadpolicy = StrictMode.allowThreadDiskReads();
        networkstats = new NetworkStats(SystemClock.elapsedRealtime(), 6);
        entry = new android.net.NetworkStats.Entry();
        obj = null;
        protocolexception = null;
        obj2 = obj;
        Object obj3 = JVM INSTR new #94  <Class ProcFileReader>;
        obj2 = obj;
        FileInputStream fileinputstream = JVM INSTR new #96  <Class FileInputStream>;
        obj2 = obj;
        fileinputstream.FileInputStream(mStatsXtIfaceAll);
        obj2 = obj;
        ((ProcFileReader) (obj3)).ProcFileReader(fileinputstream);
_L3:
        if(!((ProcFileReader) (obj3)).hasMoreData()) goto _L2; else goto _L1
_L1:
        entry.iface = ((ProcFileReader) (obj3)).nextString();
        entry.uid = -1;
        entry.set = -1;
        entry.tag = 0;
        Object obj1;
        boolean flag;
        if(((ProcFileReader) (obj3)).nextInt() != 0)
            flag = true;
        else
            flag = false;
        entry.rxBytes = ((ProcFileReader) (obj3)).nextLong();
        entry.rxPackets = ((ProcFileReader) (obj3)).nextLong();
        entry.txBytes = ((ProcFileReader) (obj3)).nextLong();
        entry.txPackets = ((ProcFileReader) (obj3)).nextLong();
        if(!flag)
            break MISSING_BLOCK_LABEL_213;
        entry.rxBytes = entry.rxBytes + ((ProcFileReader) (obj3)).nextLong();
        entry.rxPackets = entry.rxPackets + ((ProcFileReader) (obj3)).nextLong();
        entry.txBytes = entry.txBytes + ((ProcFileReader) (obj3)).nextLong();
        entry.txPackets = entry.txPackets + ((ProcFileReader) (obj3)).nextLong();
        networkstats.addValues(entry);
        ((ProcFileReader) (obj3)).finishLine();
          goto _L3
        obj1;
_L7:
        obj2 = obj3;
        protocolexception = JVM INSTR new #113 <Class ProtocolException>;
        obj2 = obj3;
        protocolexception.ProtocolException("problem parsing stats", ((Throwable) (obj1)));
        obj2 = obj3;
        throw protocolexception;
        obj3;
        obj1 = obj2;
_L5:
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        StrictMode.setThreadPolicy(threadpolicy);
        throw obj3;
_L2:
        IoUtils.closeQuietly(((AutoCloseable) (obj3)));
        StrictMode.setThreadPolicy(threadpolicy);
        return networkstats;
        Exception exception;
        exception;
        obj1 = obj3;
        obj3 = exception;
        if(true) goto _L5; else goto _L4
_L4:
        obj1;
        obj3 = protocolexception;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public NetworkStats readNetworkStatsSummaryXt()
        throws IOException
    {
        android.os.StrictMode.ThreadPolicy threadpolicy;
        NetworkStats networkstats;
        android.net.NetworkStats.Entry entry;
        Object obj;
        ProtocolException protocolexception;
        Object obj2;
        threadpolicy = StrictMode.allowThreadDiskReads();
        if(!mStatsXtIfaceFmt.exists())
            return null;
        networkstats = new NetworkStats(SystemClock.elapsedRealtime(), 6);
        entry = new android.net.NetworkStats.Entry();
        obj = null;
        protocolexception = null;
        obj2 = obj;
        Object obj3 = JVM INSTR new #94  <Class ProcFileReader>;
        obj2 = obj;
        FileInputStream fileinputstream = JVM INSTR new #96  <Class FileInputStream>;
        obj2 = obj;
        fileinputstream.FileInputStream(mStatsXtIfaceFmt);
        obj2 = obj;
        ((ProcFileReader) (obj3)).ProcFileReader(fileinputstream);
        ((ProcFileReader) (obj3)).finishLine();
        for(; ((ProcFileReader) (obj3)).hasMoreData(); ((ProcFileReader) (obj3)).finishLine())
        {
            entry.iface = ((ProcFileReader) (obj3)).nextString();
            entry.uid = -1;
            entry.set = -1;
            entry.tag = 0;
            entry.rxBytes = ((ProcFileReader) (obj3)).nextLong();
            entry.rxPackets = ((ProcFileReader) (obj3)).nextLong();
            entry.txBytes = ((ProcFileReader) (obj3)).nextLong();
            entry.txPackets = ((ProcFileReader) (obj3)).nextLong();
            networkstats.addValues(entry);
        }

          goto _L1
        Object obj1;
        obj1;
_L5:
        obj2 = obj3;
        protocolexception = JVM INSTR new #113 <Class ProtocolException>;
        obj2 = obj3;
        protocolexception.ProtocolException("problem parsing stats", ((Throwable) (obj1)));
        obj2 = obj3;
        throw protocolexception;
        obj3;
        obj1 = obj2;
_L3:
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        StrictMode.setThreadPolicy(threadpolicy);
        throw obj3;
_L1:
        IoUtils.closeQuietly(((AutoCloseable) (obj3)));
        StrictMode.setThreadPolicy(threadpolicy);
        return networkstats;
        Exception exception;
        exception;
        obj1 = obj3;
        obj3 = exception;
        if(true) goto _L3; else goto _L2
_L2:
        obj1;
        obj3 = protocolexception;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static final String CLATD_INTERFACE_PREFIX = "v4-";
    private static final int IPV4V6_HEADER_DELTA = 20;
    private static final boolean SANITY_CHECK_NATIVE = false;
    private static final String TAG = "NetworkStatsFactory";
    private static final boolean USE_NATIVE_PARSING = true;
    private static final ArrayMap sStackedIfaces = new ArrayMap();
    private final File mStatsXtIfaceAll;
    private final File mStatsXtIfaceFmt;
    private final File mStatsXtUid;

}
