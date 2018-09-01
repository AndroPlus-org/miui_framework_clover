// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.net;

import android.net.NetworkStats;
import android.net.TrafficStats;
import com.android.internal.net.NetworkStatsFactory;
import java.util.*;
import miui.securitycenter.NetworkUtils;

public class NetworkStatWrapper
{

    private NetworkStatWrapper()
    {
    }

    public static long getRxBytes(String s)
    {
        return TrafficStats.getRxBytes(s);
    }

    public static ArrayList getStatsInfo()
    {
        Object obj;
        NetworkStats networkstats;
        obj = mStatsFactory.readNetworkStatsDetail();
        networkstats = NetworkUtils.getAdjustedNetworkStatsTethering();
        if(networkstats == null)
            break MISSING_BLOCK_LABEL_27;
        if(networkstats.size() > 0)
            ((NetworkStats) (obj)).combineAllValues(networkstats);
        if(mPreSnapshot != null) goto _L2; else goto _L1
_L1:
        mPreSnapshot = ((NetworkStats) (obj));
_L6:
        return mStatsInfo;
_L2:
        mStatsInfo.clear();
        networkstats = NetworkStats.subtract(((NetworkStats) (obj)), mPreSnapshot, null, null);
        mPreSnapshot = ((NetworkStats) (obj));
        int i;
        obj = null;
        if(networkstats == null)
            continue; /* Loop/switch isn't completed */
        i = 0;
_L4:
        if(i >= networkstats.size())
            continue; /* Loop/switch isn't completed */
        obj = networkstats.getValues(i, ((android.net.NetworkStats.Entry) (obj)));
        HashMap hashmap = JVM INSTR new #71  <Class HashMap>;
        hashmap.HashMap();
        hashmap.put("uid", String.valueOf(((android.net.NetworkStats.Entry) (obj)).uid));
        hashmap.put("iface", ((android.net.NetworkStats.Entry) (obj)).iface);
        hashmap.put("rxBytes", String.valueOf(((android.net.NetworkStats.Entry) (obj)).rxBytes));
        hashmap.put("txBytes", String.valueOf(((android.net.NetworkStats.Entry) (obj)).txBytes));
        hashmap.put("tag", String.valueOf(((android.net.NetworkStats.Entry) (obj)).tag));
        mStatsInfo.add(hashmap);
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        exception.printStackTrace();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static long getTxBytes(String s)
    {
        return TrafficStats.getTxBytes(s);
    }

    private static NetworkStats mPreSnapshot = null;
    private static NetworkStatsFactory mStatsFactory = new NetworkStatsFactory();
    private static ArrayList mStatsInfo = new ArrayList();

}
