// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.util.List;

// Referenced classes of package android.net:
//            INetworkScoreService, NetworkScorerAppData, INetworkScoreCache, NetworkKey, 
//            ScoredNetwork

public class NetworkScoreManager
{

    public NetworkScoreManager(Context context)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mContext = context;
    }

    public boolean clearScores()
        throws SecurityException
    {
        boolean flag;
        try
        {
            flag = mService.clearScores();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void disableScoring()
        throws SecurityException
    {
        try
        {
            mService.disableScoring();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public NetworkScorerAppData getActiveScorer()
    {
        NetworkScorerAppData networkscorerappdata;
        try
        {
            networkscorerappdata = mService.getActiveScorer();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return networkscorerappdata;
    }

    public String getActiveScorerPackage()
    {
        String s;
        try
        {
            s = mService.getActiveScorerPackage();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public List getAllValidScorers()
    {
        List list;
        try
        {
            list = mService.getAllValidScorers();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public boolean isCallerActiveScorer(int i)
    {
        boolean flag;
        try
        {
            flag = mService.isCallerActiveScorer(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void registerNetworkScoreCache(int i, INetworkScoreCache inetworkscorecache)
    {
        registerNetworkScoreCache(i, inetworkscorecache, 0);
    }

    public void registerNetworkScoreCache(int i, INetworkScoreCache inetworkscorecache, int j)
    {
        try
        {
            mService.registerNetworkScoreCache(i, inetworkscorecache, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(INetworkScoreCache inetworkscorecache)
        {
            throw inetworkscorecache.rethrowFromSystemServer();
        }
    }

    public boolean requestScores(NetworkKey anetworkkey[])
        throws SecurityException
    {
        boolean flag;
        try
        {
            flag = mService.requestScores(anetworkkey);
        }
        // Misplaced declaration of an exception variable
        catch(NetworkKey anetworkkey[])
        {
            throw anetworkkey.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean setActiveScorer(String s)
        throws SecurityException
    {
        boolean flag;
        try
        {
            flag = mService.setActiveScorer(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void unregisterNetworkScoreCache(int i, INetworkScoreCache inetworkscorecache)
    {
        try
        {
            mService.unregisterNetworkScoreCache(i, inetworkscorecache);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(INetworkScoreCache inetworkscorecache)
        {
            throw inetworkscorecache.rethrowFromSystemServer();
        }
    }

    public boolean updateScores(ScoredNetwork ascorednetwork[])
        throws SecurityException
    {
        boolean flag;
        try
        {
            flag = mService.updateScores(ascorednetwork);
        }
        // Misplaced declaration of an exception variable
        catch(ScoredNetwork ascorednetwork[])
        {
            throw ascorednetwork.rethrowFromSystemServer();
        }
        return flag;
    }

    public static final String ACTION_CHANGE_ACTIVE = "android.net.scoring.CHANGE_ACTIVE";
    public static final String ACTION_CUSTOM_ENABLE = "android.net.scoring.CUSTOM_ENABLE";
    public static final String ACTION_RECOMMEND_NETWORKS = "android.net.action.RECOMMEND_NETWORKS";
    public static final String ACTION_SCORER_CHANGED = "android.net.scoring.SCORER_CHANGED";
    public static final String ACTION_SCORE_NETWORKS = "android.net.scoring.SCORE_NETWORKS";
    public static final int CACHE_FILTER_CURRENT_NETWORK = 1;
    public static final int CACHE_FILTER_NONE = 0;
    public static final int CACHE_FILTER_SCAN_RESULTS = 2;
    public static final String EXTRA_NETWORKS_TO_SCORE = "networksToScore";
    public static final String EXTRA_NEW_SCORER = "newScorer";
    public static final String EXTRA_PACKAGE_NAME = "packageName";
    public static final String NETWORK_AVAILABLE_NOTIFICATION_CHANNEL_ID_META_DATA = "android.net.wifi.notification_channel_id_network_available";
    public static final int RECOMMENDATIONS_ENABLED_FORCED_OFF = -1;
    public static final int RECOMMENDATIONS_ENABLED_OFF = 0;
    public static final int RECOMMENDATIONS_ENABLED_ON = 1;
    public static final String RECOMMENDATION_SERVICE_LABEL_META_DATA = "android.net.scoring.recommendation_service_label";
    public static final String USE_OPEN_WIFI_PACKAGE_META_DATA = "android.net.wifi.use_open_wifi_package";
    private final Context mContext;
    private final INetworkScoreService mService = INetworkScoreService.Stub.asInterface(ServiceManager.getServiceOrThrow("network_score"));
}
