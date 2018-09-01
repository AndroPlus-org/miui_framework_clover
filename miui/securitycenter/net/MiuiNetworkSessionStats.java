// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.net;

import android.net.*;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;

public class MiuiNetworkSessionStats
{

    public MiuiNetworkSessionStats()
    {
        mStatsService = android.net.INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
    }

    private SparseArray buildNetworkStatsMap(NetworkTemplate networktemplate, long l, long l1)
    {
        Object obj = null;
        if(mStatsSession == null || networktemplate == null)
            return null;
        try
        {
            networktemplate = getSummaryForAllUid(networktemplate, l, l1, false);
        }
        // Misplaced declaration of an exception variable
        catch(NetworkTemplate networktemplate)
        {
            networktemplate.printStackTrace();
            networktemplate = obj;
        }
        if(networktemplate == null || networktemplate.size() == 0)
            return null;
        SparseArray sparsearray = new SparseArray(255);
        android.net.NetworkStats.Entry entry = new android.net.NetworkStats.Entry();
        int i = networktemplate.size();
        int j = 0;
        while(j < i) 
        {
            android.net.NetworkStats.Entry entry1 = networktemplate.getValues(j, entry);
            if(entry1 != null)
            {
                int k = entry1.uid;
                Map map1 = (Map)sparsearray.get(k);
                Map map = map1;
                if(map1 == null)
                {
                    map = buildStatsMap();
                    sparsearray.put(k, map);
                }
                if(entry1.set == 1)
                {
                    map.put("txForegroundBytes", Long.valueOf(((Long)map.get("txForegroundBytes")).longValue() + entry1.txBytes));
                    map.put("rxForegroundBytes", Long.valueOf(((Long)map.get("rxForegroundBytes")).longValue() + entry1.rxBytes));
                }
                map.put("txBytes", Long.valueOf(((Long)map.get("txBytes")).longValue() + entry1.txBytes));
                map.put("rxBytes", Long.valueOf(((Long)map.get("rxBytes")).longValue() + entry1.rxBytes));
            }
            j++;
        }
        return sparsearray;
    }

    private Map buildStatsMap()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("rxForegroundBytes", Long.valueOf(0L));
        hashmap.put("txForegroundBytes", Long.valueOf(0L));
        hashmap.put("txBytes", Long.valueOf(0L));
        hashmap.put("rxBytes", Long.valueOf(0L));
        return hashmap;
    }

    private NetworkTemplate buildTemplateMobileAll(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        else
            return NetworkTemplate.buildTemplateMobileAll(s);
    }

    private NetworkTemplate buildTemplateWifiWildcard()
    {
        return NetworkTemplate.buildTemplateWifiWildcard();
    }

    private NetworkStatsHistory getHistoryForUid(NetworkTemplate networktemplate, int i, int j, int k, int l)
        throws RemoteException
    {
        return mStatsSession.getHistoryForUid(networktemplate, i, j, k, l);
    }

    private long[] getHistoryStats(NetworkStatsHistory networkstatshistory, long l, long l1)
    {
        long al[] = new long[2];
        android.net.NetworkStatsHistory.Entry entry = new android.net.NetworkStatsHistory.Entry();
        if(networkstatshistory != null)
        {
            networkstatshistory = networkstatshistory.getValues(l, l1, entry);
            if(((android.net.NetworkStatsHistory.Entry) (networkstatshistory)).rxBytes >= 0L)
                al[0] = ((android.net.NetworkStatsHistory.Entry) (networkstatshistory)).rxBytes;
            else
                al[0] = 0L;
            if(((android.net.NetworkStatsHistory.Entry) (networkstatshistory)).txBytes >= 0L)
                al[1] = ((android.net.NetworkStatsHistory.Entry) (networkstatshistory)).txBytes;
            else
                al[1] = 0L;
        }
        return al;
    }

    private long getNetworkTotalBytes(NetworkTemplate networktemplate, long l, long l1)
        throws RemoteException
    {
        return mStatsService.getNetworkTotalBytes(networktemplate, l, l1);
    }

    private NetworkStats getSummaryForAllUid(NetworkTemplate networktemplate, long l, long l1, boolean flag)
        throws RemoteException
    {
        return mStatsSession.getSummaryForAllUid(networktemplate, l, l1, flag);
    }

    public void closeSession()
    {
        if(mStatsSession == null)
            break MISSING_BLOCK_LABEL_16;
        mStatsSession.close();
_L2:
        return;
        Object obj;
        obj;
        throw obj;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void forceUpdate()
    {
        try
        {
            mStatsService.forceUpdate();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException(remoteexception);
        }
    }

    public long[] getMobileHistoryForUid(String s, int i, long l, long l1)
    {
        long al[] = new long[2];
        s = buildTemplateMobileAll(s);
        if(s == null)
            return al;
        try
        {
            s = getHistoryStats(getHistoryForUid(s, i, -1, 0, 10), l, l1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            s = al;
        }
        return s;
    }

    public SparseArray getMobileSummaryForAllUid(String s, long l, long l1)
    {
        return buildNetworkStatsMap(buildTemplateMobileAll(s), l, l1);
    }

    public long getNetworkMobileTotalBytes(String s, long l, long l1)
    {
        long l2 = 0L;
        s = buildTemplateMobileAll(s);
        long l3 = l2;
        if(s != null)
            try
            {
                l3 = getNetworkTotalBytes(s, l, l1);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                l3 = l2;
            }
        return l3;
    }

    public long getNetworkWifiTotalBytes(long l, long l1)
    {
        long l2 = 0L;
        NetworkTemplate networktemplate = buildTemplateWifiWildcard();
        long l3 = l2;
        if(networktemplate != null)
            try
            {
                l3 = getNetworkTotalBytes(networktemplate, l, l1);
            }
            catch(RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
                l3 = l2;
            }
        return l3;
    }

    public long[] getWifiHistoryForUid(int i, long l, long l1)
    {
        long al[] = new long[2];
        NetworkTemplate networktemplate = buildTemplateWifiWildcard();
        if(networktemplate == null)
            return al;
        long al1[] = getHistoryStats(getHistoryForUid(networktemplate, i, -1, 0, 10), l, l1);
        al = al1;
_L2:
        return al;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public SparseArray getWifiSummaryForAllUid(long l, long l1)
    {
        return buildNetworkStatsMap(buildTemplateWifiWildcard(), l, l1);
    }

    public void openSession()
    {
        try
        {
            mStatsSession = mStatsService.openSession();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException(remoteexception);
        }
    }

    private INetworkStatsService mStatsService;
    private INetworkStatsSession mStatsSession;
}
