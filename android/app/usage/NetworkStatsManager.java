// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.Context;
import android.net.*;
import android.os.*;
import android.util.Log;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.app.usage:
//            NetworkStats

public class NetworkStatsManager
{
    private static class CallbackHandler extends Handler
    {

        private static Object getObject(Message message, String s)
        {
            return message.getData().getParcelable(s);
        }

        public void handleMessage(Message message)
        {
            DataUsageRequest datausagerequest = (DataUsageRequest)getObject(message, "DataUsageRequest");
            message.what;
            JVM INSTR tableswitch 0 1: default 36
        //                       0 37
        //                       1 90;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            if(mCallback != null)
                mCallback.onThresholdReached(mNetworkType, mSubscriberId);
            else
                Log.e("NetworkStatsManager", (new StringBuilder()).append("limit reached with released callback for ").append(datausagerequest).toString());
            continue; /* Loop/switch isn't completed */
_L3:
            mCallback = null;
            if(true) goto _L1; else goto _L4
_L4:
        }

        private UsageCallback mCallback;
        private final int mNetworkType;
        private final String mSubscriberId;

        CallbackHandler(Looper looper, int i, String s, UsageCallback usagecallback)
        {
            super(looper);
            mNetworkType = i;
            mSubscriberId = s;
            mCallback = usagecallback;
        }
    }

    public static abstract class UsageCallback
    {

        static DataUsageRequest _2D_get0(UsageCallback usagecallback)
        {
            return usagecallback.request;
        }

        static DataUsageRequest _2D_set0(UsageCallback usagecallback, DataUsageRequest datausagerequest)
        {
            usagecallback.request = datausagerequest;
            return datausagerequest;
        }

        public abstract void onThresholdReached(int i, String s);

        private DataUsageRequest request;

        public UsageCallback()
        {
        }
    }


    public NetworkStatsManager(Context context)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mContext = context;
        setPollOnOpen(true);
    }

    private static NetworkTemplate createTemplate(int i, String s)
    {
        i;
        JVM INSTR tableswitch 0 1: default 24
    //                   0 68
    //                   1 75;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Cannot create template for network type ").append(i).append(", subscriberId '").append(NetworkIdentity.scrubSubscriberId(s)).append("'.").toString());
_L2:
        s = NetworkTemplate.buildTemplateMobileAll(s);
_L5:
        return s;
_L3:
        s = NetworkTemplate.buildTemplateWifiWildcard();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public NetworkStats queryDetails(int i, String s, long l, long l1)
        throws SecurityException, RemoteException
    {
        try
        {
            s = createTemplate(i, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        s = new NetworkStats(mContext, s, mFlags, l, l1);
        s.startUserUidEnumeration();
        return s;
    }

    public NetworkStats queryDetailsForUid(int i, String s, long l, long l1, int j)
        throws SecurityException, RemoteException
    {
        return queryDetailsForUidTag(i, s, l, l1, j, 0);
    }

    public NetworkStats queryDetailsForUidTag(int i, String s, long l, long l1, int j, 
            int k)
        throws SecurityException
    {
        s = createTemplate(i, s);
        NetworkStats networkstats;
        try
        {
            networkstats = JVM INSTR new #113 <Class NetworkStats>;
            networkstats.NetworkStats(mContext, s, mFlags, l, l1);
            networkstats.startHistoryEnumeration(j, k);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("NetworkStatsManager", (new StringBuilder()).append("Error while querying stats for uid=").append(j).append(" tag=").append(k).toString(), s);
            return null;
        }
        return networkstats;
    }

    public NetworkStats querySummary(int i, String s, long l, long l1)
        throws SecurityException, RemoteException
    {
        try
        {
            s = createTemplate(i, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        s = new NetworkStats(mContext, s, mFlags, l, l1);
        s.startSummaryEnumeration();
        return s;
    }

    public NetworkStats.Bucket querySummaryForDevice(int i, String s, long l, long l1)
        throws SecurityException, RemoteException
    {
        NetworkStats networkstats;
        try
        {
            s = createTemplate(i, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        networkstats = new NetworkStats(mContext, s, mFlags, l, l1);
        s = networkstats.getDeviceSummaryForNetwork();
        networkstats.close();
        return s;
    }

    public NetworkStats.Bucket querySummaryForUser(int i, String s, long l, long l1)
        throws SecurityException, RemoteException
    {
        try
        {
            s = createTemplate(i, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        s = new NetworkStats(mContext, s, mFlags, l, l1);
        s.startSummaryEnumeration();
        s.close();
        return s.getSummaryAggregate();
    }

    public void registerUsageCallback(int i, String s, long l, UsageCallback usagecallback)
    {
        registerUsageCallback(i, s, l, usagecallback, null);
    }

    public void registerUsageCallback(int i, String s, long l, UsageCallback usagecallback, Handler handler)
    {
        Preconditions.checkNotNull(usagecallback, "UsageCallback cannot be null");
        DataUsageRequest datausagerequest;
        if(handler == null)
            handler = Looper.myLooper();
        else
            handler = handler.getLooper();
        datausagerequest = new DataUsageRequest(0, createTemplate(i, s), l);
        try
        {
            Object obj = JVM INSTR new #6   <Class NetworkStatsManager$CallbackHandler>;
            ((CallbackHandler) (obj)).CallbackHandler(handler, i, s, usagecallback);
            INetworkStatsService inetworkstatsservice = mService;
            s = mContext.getOpPackageName();
            handler = JVM INSTR new #192 <Class Messenger>;
            handler.Messenger(((Handler) (obj)));
            obj = JVM INSTR new #197 <Class Binder>;
            ((Binder) (obj)).Binder();
            UsageCallback._2D_set0(usagecallback, inetworkstatsservice.registerUsageCallback(s, datausagerequest, handler, ((android.os.IBinder) (obj))));
            if(UsageCallback._2D_get0(usagecallback) == null)
                Log.e("NetworkStatsManager", "Request from callback is null; should not happen");
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setAugmentWithSubscriptionPlan(boolean flag)
    {
        if(flag)
            mFlags = mFlags | 2;
        else
            mFlags = mFlags & -3;
    }

    public void setPollOnOpen(boolean flag)
    {
        if(flag)
            mFlags = mFlags | 1;
        else
            mFlags = mFlags & -2;
    }

    public void unregisterUsageCallback(UsageCallback usagecallback)
    {
        while(usagecallback == null || UsageCallback._2D_get0(usagecallback) == null || UsageCallback._2D_get0(usagecallback).requestId == 0) 
            throw new IllegalArgumentException("Invalid UsageCallback");
        try
        {
            mService.unregisterUsageRequest(UsageCallback._2D_get0(usagecallback));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UsageCallback usagecallback)
        {
            throw usagecallback.rethrowFromSystemServer();
        }
    }

    public static final int CALLBACK_LIMIT_REACHED = 0;
    public static final int CALLBACK_RELEASED = 1;
    private static final boolean DBG = false;
    public static final int FLAG_AUGMENT_WITH_SUBSCRIPTION_PLAN = 2;
    public static final int FLAG_POLL_ON_OPEN = 1;
    private static final String TAG = "NetworkStatsManager";
    private final Context mContext;
    private int mFlags;
    private final INetworkStatsService mService = android.net.INetworkStatsService.Stub.asInterface(ServiceManager.getServiceOrThrow("netstats"));
}
