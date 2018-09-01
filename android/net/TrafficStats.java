// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.os.*;
import com.android.server.NetworkManagementSocketTagger;
import dalvik.system.SocketTagger;
import java.net.*;

// Referenced classes of package android.net:
//            INetworkStatsSession, INetworkStatsService, NetworkStats

public class TrafficStats
{

    public TrafficStats()
    {
    }

    public static void clearThreadStatsTag()
    {
        NetworkManagementSocketTagger.setThreadSocketStatsTag(-1);
    }

    public static void clearThreadStatsUid()
    {
        NetworkManagementSocketTagger.setThreadSocketStatsUid(-1);
    }

    public static void closeQuietly(INetworkStatsSession inetworkstatssession)
    {
        if(inetworkstatssession == null)
            break MISSING_BLOCK_LABEL_10;
        inetworkstatssession.close();
_L2:
        return;
        inetworkstatssession;
        if(true) goto _L2; else goto _L1
_L1:
        inetworkstatssession;
        throw inetworkstatssession;
    }

    public static int getAndSetThreadStatsTag(int i)
    {
        return NetworkManagementSocketTagger.setThreadSocketStatsTag(i);
    }

    private static NetworkStats getDataLayerSnapshotForUid(Context context)
    {
        int i = Process.myUid();
        try
        {
            context = getStatsService().getDataLayerSnapshotForUid(i);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        return context;
    }

    private static String[] getMobileIfaces()
    {
        String as[];
        try
        {
            as = getStatsService().getMobileIfaces();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public static long getMobileRxBytes()
    {
        long l = 0L;
        String as[] = getMobileIfaces();
        int i = 0;
        for(int j = as.length; i < j; i++)
            l += getRxBytes(as[i]);

        return l;
    }

    public static long getMobileRxPackets()
    {
        long l = 0L;
        String as[] = getMobileIfaces();
        int i = 0;
        for(int j = as.length; i < j; i++)
            l += getRxPackets(as[i]);

        return l;
    }

    public static long getMobileTcpRxPackets()
    {
        long l = 0L;
        String as[] = getMobileIfaces();
        int i = 0;
        for(int j = as.length; i < j;)
        {
            long l1 = nativeGetIfaceStat(as[i], 4);
            long l2 = l;
            if(l1 != -1L)
                l2 = l + l1;
            i++;
            l = l2;
        }

        return l;
    }

    public static long getMobileTcpTxPackets()
    {
        long l = 0L;
        String as[] = getMobileIfaces();
        int i = 0;
        for(int j = as.length; i < j;)
        {
            long l1 = nativeGetIfaceStat(as[i], 5);
            long l2 = l;
            if(l1 != -1L)
                l2 = l + l1;
            i++;
            l = l2;
        }

        return l;
    }

    public static long getMobileTxBytes()
    {
        long l = 0L;
        String as[] = getMobileIfaces();
        int i = 0;
        for(int j = as.length; i < j; i++)
            l += getTxBytes(as[i]);

        return l;
    }

    public static long getMobileTxPackets()
    {
        long l = 0L;
        String as[] = getMobileIfaces();
        int i = 0;
        for(int j = as.length; i < j; i++)
            l += getTxPackets(as[i]);

        return l;
    }

    public static long getRxBytes(String s)
    {
        return nativeGetIfaceStat(s, 0);
    }

    public static long getRxPackets(String s)
    {
        return nativeGetIfaceStat(s, 1);
    }

    private static INetworkStatsService getStatsService()
    {
        android/net/TrafficStats;
        JVM INSTR monitorenter ;
        INetworkStatsService inetworkstatsservice;
        if(sStatsService == null)
            sStatsService = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
        inetworkstatsservice = sStatsService;
        android/net/TrafficStats;
        JVM INSTR monitorexit ;
        return inetworkstatsservice;
        Exception exception;
        exception;
        throw exception;
    }

    public static int getThreadStatsTag()
    {
        return NetworkManagementSocketTagger.getThreadSocketStatsTag();
    }

    public static long getTotalRxBytes()
    {
        return nativeGetTotalStat(0);
    }

    public static long getTotalRxPackets()
    {
        return nativeGetTotalStat(1);
    }

    public static long getTotalTxBytes()
    {
        return nativeGetTotalStat(2);
    }

    public static long getTotalTxPackets()
    {
        return nativeGetTotalStat(3);
    }

    public static long getTxBytes(String s)
    {
        return nativeGetIfaceStat(s, 2);
    }

    public static long getTxPackets(String s)
    {
        return nativeGetIfaceStat(s, 3);
    }

    public static long getUidRxBytes(int i)
    {
        int j = Process.myUid();
        if(j == 1000 || j == i)
            return nativeGetUidStat(i, 0);
        else
            return -1L;
    }

    public static long getUidRxPackets(int i)
    {
        int j = Process.myUid();
        if(j == 1000 || j == i)
            return nativeGetUidStat(i, 1);
        else
            return -1L;
    }

    public static long getUidTcpRxBytes(int i)
    {
        return -1L;
    }

    public static long getUidTcpRxSegments(int i)
    {
        return -1L;
    }

    public static long getUidTcpTxBytes(int i)
    {
        return -1L;
    }

    public static long getUidTcpTxSegments(int i)
    {
        return -1L;
    }

    public static long getUidTxBytes(int i)
    {
        int j = Process.myUid();
        if(j == 1000 || j == i)
            return nativeGetUidStat(i, 2);
        else
            return -1L;
    }

    public static long getUidTxPackets(int i)
    {
        int j = Process.myUid();
        if(j == 1000 || j == i)
            return nativeGetUidStat(i, 3);
        else
            return -1L;
    }

    public static long getUidUdpRxBytes(int i)
    {
        return -1L;
    }

    public static long getUidUdpRxPackets(int i)
    {
        return -1L;
    }

    public static long getUidUdpTxBytes(int i)
    {
        return -1L;
    }

    public static long getUidUdpTxPackets(int i)
    {
        return -1L;
    }

    public static void incrementOperationCount(int i)
    {
        incrementOperationCount(getThreadStatsTag(), i);
    }

    public static void incrementOperationCount(int i, int j)
    {
        int k = Process.myUid();
        try
        {
            getStatsService().incrementOperationCount(k, i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    private static native long nativeGetIfaceStat(String s, int i);

    private static native long nativeGetTotalStat(int i);

    private static native long nativeGetUidStat(int i, int j);

    public static void setThreadStatsTag(int i)
    {
        NetworkManagementSocketTagger.setThreadSocketStatsTag(i);
    }

    public static void setThreadStatsTagApp()
    {
        setThreadStatsTag(-251);
    }

    public static void setThreadStatsTagBackup()
    {
        setThreadStatsTag(-253);
    }

    public static void setThreadStatsTagRestore()
    {
        setThreadStatsTag(-252);
    }

    public static void setThreadStatsUid(int i)
    {
        NetworkManagementSocketTagger.setThreadSocketStatsUid(i);
    }

    public static void startDataProfiling(Context context)
    {
        synchronized(sProfilingLock)
        {
            if(sActiveProfilingStart != null)
            {
                context = JVM INSTR new #221 <Class IllegalStateException>;
                context.IllegalStateException("already profiling data");
                throw context;
            }
            break MISSING_BLOCK_LABEL_29;
        }
        sActiveProfilingStart = getDataLayerSnapshotForUid(context);
        obj;
        JVM INSTR monitorexit ;
    }

    public static NetworkStats stopDataProfiling(Context context)
    {
        synchronized(sProfilingLock)
        {
            if(sActiveProfilingStart == null)
            {
                context = JVM INSTR new #221 <Class IllegalStateException>;
                context.IllegalStateException("not profiling data");
                throw context;
            }
            break MISSING_BLOCK_LABEL_29;
        }
        context = NetworkStats.subtract(getDataLayerSnapshotForUid(context), sActiveProfilingStart, null, null);
        sActiveProfilingStart = null;
        obj;
        JVM INSTR monitorexit ;
        return context;
    }

    public static void tagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        SocketTagger.get().tag(datagramsocket);
    }

    public static void tagSocket(Socket socket)
        throws SocketException
    {
        SocketTagger.get().tag(socket);
    }

    public static void untagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        SocketTagger.get().untag(datagramsocket);
    }

    public static void untagSocket(Socket socket)
        throws SocketException
    {
        SocketTagger.get().untag(socket);
    }

    public static final long GB_IN_BYTES = 0x40000000L;
    public static final long KB_IN_BYTES = 1024L;
    public static final long MB_IN_BYTES = 0x100000L;
    public static final long PB_IN_BYTES = 0x4000000000000L;
    public static final int TAG_SYSTEM_APP = -251;
    public static final int TAG_SYSTEM_BACKUP = -253;
    public static final int TAG_SYSTEM_DHCP = -192;
    public static final int TAG_SYSTEM_DOWNLOAD = -255;
    public static final int TAG_SYSTEM_GPS = -188;
    public static final int TAG_SYSTEM_MEDIA = -254;
    public static final int TAG_SYSTEM_NEIGHBOR = -189;
    public static final int TAG_SYSTEM_NTP = -191;
    public static final int TAG_SYSTEM_PAC = -187;
    public static final int TAG_SYSTEM_PROBE = -190;
    public static final int TAG_SYSTEM_RESTORE = -252;
    public static final long TB_IN_BYTES = 0x10000000000L;
    private static final int TYPE_RX_BYTES = 0;
    private static final int TYPE_RX_PACKETS = 1;
    private static final int TYPE_TCP_RX_PACKETS = 4;
    private static final int TYPE_TCP_TX_PACKETS = 5;
    private static final int TYPE_TX_BYTES = 2;
    private static final int TYPE_TX_PACKETS = 3;
    public static final int UID_REMOVED = -4;
    public static final int UID_TETHERING = -5;
    public static final int UNSUPPORTED = -1;
    private static NetworkStats sActiveProfilingStart;
    private static Object sProfilingLock = new Object();
    private static INetworkStatsService sStatsService;

}
