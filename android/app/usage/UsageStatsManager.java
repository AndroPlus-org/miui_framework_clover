// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArrayMap;
import java.util.*;

// Referenced classes of package android.app.usage:
//            UsageEvents, IUsageStatsManager, UsageStats

public final class UsageStatsManager
{

    public UsageStatsManager(Context context, IUsageStatsManager iusagestatsmanager)
    {
        mContext = context;
        mService = iusagestatsmanager;
    }

    public boolean isAppInactive(String s)
    {
        boolean flag;
        try
        {
            flag = mService.isAppInactive(s, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    public void onCarrierPrivilegedAppsChanged()
    {
        mService.onCarrierPrivilegedAppsChanged();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Map queryAndAggregateUsageStats(long l, long l1)
    {
        List list = queryUsageStats(4, l, l1);
        if(list.isEmpty())
            return Collections.emptyMap();
        ArrayMap arraymap = new ArrayMap();
        int i = list.size();
        int j = 0;
        while(j < i) 
        {
            UsageStats usagestats = (UsageStats)list.get(j);
            UsageStats usagestats1 = (UsageStats)arraymap.get(usagestats.getPackageName());
            if(usagestats1 == null)
                arraymap.put(usagestats.mPackageName, usagestats);
            else
                usagestats1.add(usagestats);
            j++;
        }
        return arraymap;
    }

    public List queryConfigurations(int i, long l, long l1)
    {
        Object obj = mService.queryConfigurationStats(i, l, l1, mContext.getOpPackageName());
        if(obj == null)
            break MISSING_BLOCK_LABEL_39;
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        return Collections.emptyList();
    }

    public UsageEvents queryEvents(long l, long l1)
    {
        UsageEvents usageevents = mService.queryEvents(l, l1, mContext.getOpPackageName());
        if(usageevents != null)
            return usageevents;
        break MISSING_BLOCK_LABEL_30;
        RemoteException remoteexception;
        remoteexception;
        return sEmptyResults;
    }

    public List queryUsageStats(int i, long l, long l1)
    {
        Object obj = mService.queryUsageStats(i, l, l1, mContext.getOpPackageName());
        if(obj == null)
            break MISSING_BLOCK_LABEL_39;
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        return Collections.emptyList();
    }

    public List queryUsageStatsAsUser(int i, long l, long l1, int j)
    {
        Object obj = mService.queryUsageStatsAsUser(i, l, l1, mContext.getOpPackageName(), j);
        if(obj == null)
            break MISSING_BLOCK_LABEL_41;
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        return Collections.emptyList();
    }

    public void reportChooserSelection(String s, int i, String s1, String as[], String s2)
    {
        mService.reportChooserSelection(s, i, s1, as, s2);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setAppInactive(String s, boolean flag)
    {
        mService.setAppInactive(s, flag, UserHandle.myUserId());
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void whitelistAppTemporarily(String s, long l, UserHandle userhandle)
    {
        mService.whitelistAppTemporarily(s, l, userhandle.getIdentifier());
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int INTERVAL_BEST = 4;
    public static final int INTERVAL_COUNT = 4;
    public static final int INTERVAL_DAILY = 0;
    public static final int INTERVAL_MONTHLY = 2;
    public static final int INTERVAL_WEEKLY = 1;
    public static final int INTERVAL_YEARLY = 3;
    private static final UsageEvents sEmptyResults = new UsageEvents();
    private final Context mContext;
    private final IUsageStatsManager mService;

}
