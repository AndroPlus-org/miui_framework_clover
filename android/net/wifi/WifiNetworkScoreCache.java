// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.content.Context;
import android.net.*;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.util.LruCache;
import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package android.net.wifi:
//            ScanResult, WifiManager

public class WifiNetworkScoreCache extends android.net.INetworkScoreCache.Stub
{
    public static abstract class CacheListener
    {

        public abstract void networkCacheUpdated(List list);

        void post(List list)
        {
            mHandler.post(list. new Runnable() {

                public void run()
                {
                    networkCacheUpdated(updatedNetworks);
                }

                final CacheListener this$1;
                final List val$updatedNetworks;

            
            {
                this$1 = final_cachelistener;
                updatedNetworks = List.this;
                super();
            }
            }
);
        }

        private Handler mHandler;

        public CacheListener(Handler handler)
        {
            Preconditions.checkNotNull(handler);
            mHandler = handler;
        }
    }


    public WifiNetworkScoreCache(Context context)
    {
        this(context, null);
    }

    public WifiNetworkScoreCache(Context context, CacheListener cachelistener)
    {
        this(context, cachelistener, 100);
    }

    public WifiNetworkScoreCache(Context context, CacheListener cachelistener, int i)
    {
        mLock = new Object();
        mContext = context.getApplicationContext();
        mListener = cachelistener;
        mCache = new LruCache(i);
    }

    private String buildNetworkKey(NetworkKey networkkey)
    {
        if(networkkey == null)
            return null;
        if(networkkey.wifiKey == null)
            return null;
        if(networkkey.type == 1)
        {
            String s = networkkey.wifiKey.ssid;
            if(s == null)
                return null;
            String s1 = s;
            if(networkkey.wifiKey.bssid != null)
                s1 = (new StringBuilder()).append(s).append(networkkey.wifiKey.bssid).toString();
            return s1;
        } else
        {
            return null;
        }
    }

    private String buildNetworkKey(ScoredNetwork scorednetwork)
    {
        if(scorednetwork == null)
            return null;
        else
            return buildNetworkKey(scorednetwork.networkKey);
    }

    private String buildNetworkKey(ScanResult scanresult)
    {
        if(scanresult == null || scanresult.SSID == null)
            return null;
        StringBuilder stringbuilder = new StringBuilder("\"");
        stringbuilder.append(scanresult.SSID);
        stringbuilder.append("\"");
        if(scanresult.BSSID != null)
            stringbuilder.append(scanresult.BSSID);
        return stringbuilder.toString();
    }

    public final void clearScores()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mCache.evictAll();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected final void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        mContext.enforceCallingOrSelfPermission("android.permission.DUMP", "WifiNetworkScoreCache");
        printwriter.println(String.format("WifiNetworkScoreCache (%s/%d)", new Object[] {
            mContext.getPackageName(), Integer.valueOf(Process.myUid())
        }));
        printwriter.println("  All score curves:");
        filedescriptor = ((FileDescriptor) (mLock));
        filedescriptor;
        JVM INSTR monitorenter ;
        ScoredNetwork scorednetwork;
        StringBuilder stringbuilder;
        for(as = mCache.snapshot().values().iterator(); as.hasNext(); printwriter.println(stringbuilder.append("    ").append(scorednetwork).toString()))
        {
            scorednetwork = (ScoredNetwork)as.next();
            stringbuilder = JVM INSTR new #95  <Class StringBuilder>;
            stringbuilder.StringBuilder();
        }

        break MISSING_BLOCK_LABEL_131;
        printwriter;
        throw printwriter;
        printwriter.println("  Network scores for latest ScanResults:");
        ScanResult scanresult;
        StringBuilder stringbuilder1;
        for(as = ((WifiManager)mContext.getSystemService("wifi")).getScanResults().iterator(); as.hasNext(); printwriter.println(stringbuilder1.append("    ").append(buildNetworkKey(scanresult)).append(": ").append(getNetworkScore(scanresult)).toString()))
        {
            scanresult = (ScanResult)as.next();
            stringbuilder1 = JVM INSTR new #95  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
        }

        filedescriptor;
        JVM INSTR monitorexit ;
    }

    public boolean getMeteredHint(ScanResult scanresult)
    {
        scanresult = getScoredNetwork(scanresult);
        boolean flag;
        if(scanresult != null)
            flag = ((ScoredNetwork) (scanresult)).meteredHint;
        else
            flag = false;
        return flag;
    }

    public int getNetworkScore(ScanResult scanresult)
    {
        byte byte0 = -128;
        ScoredNetwork scorednetwork = getScoredNetwork(scanresult);
        byte byte2 = byte0;
        if(scorednetwork != null)
        {
            byte2 = byte0;
            if(scorednetwork.rssiCurve != null)
            {
                byte byte1 = scorednetwork.rssiCurve.lookupScore(scanresult.level);
                byte2 = byte1;
                if(DBG)
                {
                    Log.d("WifiNetworkScoreCache", (new StringBuilder()).append("getNetworkScore found scored network ").append(scorednetwork.networkKey).append(" score ").append(Integer.toString(byte1)).append(" RSSI ").append(scanresult.level).toString());
                    byte2 = byte1;
                }
            }
        }
        return byte2;
    }

    public int getNetworkScore(ScanResult scanresult, boolean flag)
    {
        byte byte0 = -128;
        ScoredNetwork scorednetwork = getScoredNetwork(scanresult);
        byte byte2 = byte0;
        if(scorednetwork != null)
        {
            byte2 = byte0;
            if(scorednetwork.rssiCurve != null)
            {
                byte byte1 = scorednetwork.rssiCurve.lookupScore(scanresult.level, flag);
                byte2 = byte1;
                if(DBG)
                {
                    Log.d("WifiNetworkScoreCache", (new StringBuilder()).append("getNetworkScore found scored network ").append(scorednetwork.networkKey).append(" score ").append(Integer.toString(byte1)).append(" RSSI ").append(scanresult.level).append(" isActiveNetwork ").append(flag).toString());
                    byte2 = byte1;
                }
            }
        }
        return byte2;
    }

    public ScoredNetwork getScoredNetwork(NetworkKey networkkey)
    {
        Object obj;
        obj = buildNetworkKey(networkkey);
        if(obj == null)
        {
            if(DBG)
                Log.d("WifiNetworkScoreCache", (new StringBuilder()).append("Could not build key string for Network Key: ").append(networkkey).toString());
            return null;
        }
        networkkey = ((NetworkKey) (mLock));
        networkkey;
        JVM INSTR monitorenter ;
        obj = (ScoredNetwork)mCache.get(obj);
        networkkey;
        JVM INSTR monitorexit ;
        return ((ScoredNetwork) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public ScoredNetwork getScoredNetwork(ScanResult scanresult)
    {
        Object obj;
        obj = buildNetworkKey(scanresult);
        if(obj == null)
            return null;
        scanresult = ((ScanResult) (mLock));
        scanresult;
        JVM INSTR monitorenter ;
        obj = (ScoredNetwork)mCache.get(obj);
        scanresult;
        JVM INSTR monitorexit ;
        return ((ScoredNetwork) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public boolean hasScoreCurve(ScanResult scanresult)
    {
        boolean flag = false;
        scanresult = getScoredNetwork(scanresult);
        boolean flag1 = flag;
        if(scanresult != null)
        {
            flag1 = flag;
            if(((ScoredNetwork) (scanresult)).rssiCurve != null)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isScoredNetwork(ScanResult scanresult)
    {
        boolean flag;
        if(getScoredNetwork(scanresult) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void registerListener(CacheListener cachelistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mListener = cachelistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        cachelistener;
        throw cachelistener;
    }

    public void unregisterListener()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mListener = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void updateScores(List list)
    {
        boolean flag;
        if(list == null || list.isEmpty())
            return;
        if(DBG)
            Log.d("WifiNetworkScoreCache", (new StringBuilder()).append("updateScores list size=").append(list.size()).toString());
        flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = list.iterator();
_L2:
        ScoredNetwork scorednetwork;
        Object obj1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_166;
        scorednetwork = (ScoredNetwork)iterator.next();
        obj1 = buildNetworkKey(scorednetwork);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_149;
        if(!DBG) goto _L2; else goto _L1
_L1:
        obj1 = JVM INSTR new #95  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("WifiNetworkScoreCache", ((StringBuilder) (obj1)).append("Failed to build network key for ScoredNetwork").append(scorednetwork).toString());
          goto _L2
        list;
        throw list;
        mCache.put(obj1, scorednetwork);
        flag = true;
          goto _L2
        if(mListener == null || !flag)
            break MISSING_BLOCK_LABEL_185;
        mListener.post(list);
        obj;
        JVM INSTR monitorexit ;
    }

    private static final boolean DBG = Log.isLoggable("WifiNetworkScoreCache", 3);
    private static final int DEFAULT_MAX_CACHE_SIZE = 100;
    public static final int INVALID_NETWORK_SCORE = -128;
    private static final String TAG = "WifiNetworkScoreCache";
    private final LruCache mCache;
    private final Context mContext;
    private CacheListener mListener;
    private final Object mLock;

}
