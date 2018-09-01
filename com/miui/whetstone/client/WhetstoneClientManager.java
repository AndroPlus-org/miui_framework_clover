// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.client;

import android.content.*;
import android.os.Build;
import android.os.SystemProperties;
import android.util.Log;
import com.miui.whetstone.IWhetstoneClient;
import com.miui.whetstone.PowerKeeperPolicy;
import com.miui.whetstone.process.WtServiceControlEntry;
import com.miui.whetstone.server.WhetstoneActivityManagerService;
import com.miui.whetstone.strategy.WhetstoneSystemSetting;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import miui.util.MiuiFeatureUtils;

public abstract class WhetstoneClientManager
{

    public WhetstoneClientManager()
    {
    }

    public static boolean AppBGIdleFeatureIsEnable()
    {
        if(mWhetstoneAM != null)
            return mWhetstoneAM.getPowerKeeperPolicy().getAppBGIdleFeatureEnable();
        else
            return false;
    }

    public static boolean addComponment(ComponentName acomponentname[])
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        HashSet hashset = (HashSet)sPerformanceSet.clone();
        int i = 0;
        int j = acomponentname.length;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        hashset.add(acomponentname[i]);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        sPerformanceSet = hashset;
        obj;
        JVM INSTR monitorexit ;
        return true;
        acomponentname;
        throw acomponentname;
    }

    public static boolean checkIfPackageIsLocked(String s)
    {
        return checkIfPackageIsLocked(s, 0);
    }

    public static boolean checkIfPackageIsLocked(String s, int i)
    {
        Object obj = (List)mProcessLocked.get(Integer.valueOf(i));
        if(obj == null)
            return false;
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
            if(s.equals((String)((Iterator) (obj)).next()))
                return true;

        return false;
    }

    private static void doPerformanceLam()
    {
        if(sPowerProfile == null && BOARD_PERFORMANCE_SUPPORT)
            sPowerProfile = SystemProperties.get("persist.sys.aries.power_profile", "middle");
        if(BOARD_PERFORMANCE_SUPPORT)
            SystemProperties.set("persist.sys.aries.power_profile", "high");
    }

    private static void doResumeLam()
    {
        if(sPowerProfile != null && BOARD_PERFORMANCE_SUPPORT)
        {
            SystemProperties.set("persist.sys.aries.power_profile", sPowerProfile);
            sPowerProfile = null;
        }
    }

    public static int getAppBGIdleLevel(int i)
    {
        if(mWhetstoneAM != null)
            return mWhetstoneAM.getPowerKeeperPolicy().getAppBGIdleLevel(i);
        else
            return 0;
    }

    public static long getEmptyProcTotalMemoryInfo()
    {
        if(!CACHAED_STATISTICS_SUPPORT)
            return 0L;
        else
            return sAndroidCachePss;
    }

    public static WhetstoneSystemSetting getWhetstoneSystemSetting()
    {
        return mSetting;
    }

    public static void init(Context context, IWhetstoneClient iwhetstoneclient, WhetstoneActivityManagerService whetstoneactivitymanagerservice)
    {
        mContext = context;
        mService = iwhetstoneclient;
        mSetting = new WhetstoneSystemSetting(mContext, whetstoneactivitymanagerservice);
        mSetting.addObserver(WtServiceControlEntry.getInstance());
        mWhetstoneAM = whetstoneactivitymanagerservice;
        sCallingPid = -1;
    }

    public static boolean isAlarmAllowedLocked(int i, int j, int k, boolean flag)
    {
        return true;
    }

    public static boolean isAnimationEnable()
    {
        return isAnimationEnable(null, 0);
    }

    public static boolean isAnimationEnable(android.view.WindowManagerPolicy.WindowState windowstate)
    {
        return isAnimationEnable(windowstate, 0);
    }

    public static boolean isAnimationEnable(android.view.WindowManagerPolicy.WindowState windowstate, int i)
    {
        return sPerformanceEnable ^ true;
    }

    public static boolean isBroadcastAllowedLocked(int i, int j, String s)
    {
        return true;
    }

    public static boolean isProtectImportantApp(String s)
    {
        return isProtectImportantAppWithUid(s, 0);
    }

    public static boolean isProtectImportantAppWithUid(String s, int i)
    {
        for(Iterator iterator = protectApps.iterator(); iterator.hasNext();)
            if(((String)iterator.next()).equals(s))
                return true;

        Object obj = (List)mProcessLocked.get(Integer.valueOf(i));
        if(s == null || obj == null)
            return false;
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
            if(((String)((Iterator) (obj)).next()).equals(s))
                return true;

        return false;
    }

    public static boolean isStartServiceAllowedLocked(Intent intent, int i, String s, int j)
    {
        return true;
    }

    public static boolean isSystemProtectImportantApp(String s)
    {
        for(Iterator iterator = protectApps.iterator(); iterator.hasNext();)
            if(((String)iterator.next()).equals(s))
                return true;

        return false;
    }

    public static boolean prepareAppTransitionLam(ComponentName componentname, ComponentName componentname1)
    {
        if(!RED_SUPPORT)
            return false;
        if(componentname1 == null)
            return false;
        if(!sPerformanceSet.contains(componentname1))
            break MISSING_BLOCK_LABEL_33;
        sPerformanceEnable = true;
        doPerformanceLam();
        return true;
        try
        {
            sPerformanceEnable = false;
            doResumeLam();
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            Log.e("WhetstoneClientManager", "prepareAppTransitionLam", componentname);
        }
        return false;
    }

    public static void setCachedPidLam(int i, int j, long l)
    {
        if(!CACHAED_STATISTICS_SUPPORT)
            return;
        if(i == 0)
            sAndroidCachePss = 0L;
        sAndroidCachePss += l;
    }

    public static void setCallingProcessPid(int i)
    {
        sCallingPid = i;
    }

    public static boolean setComponment(ComponentName acomponentname[])
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        HashSet hashset;
        hashset = JVM INSTR new #103 <Class HashSet>;
        hashset.HashSet();
        int i = 0;
        int j = acomponentname.length;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        hashset.add(acomponentname[i]);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        sPerformanceSet = hashset;
        obj;
        JVM INSTR monitorexit ;
        return true;
        acomponentname;
        throw acomponentname;
    }

    public static boolean startServiceAllowed(ComponentName componentname, int i, boolean flag)
    {
        return true;
    }

    public static boolean startServiceAllowed(Context context, Intent intent, String s, int i)
    {
        return true;
    }

    public static void updateApplicationsMemoryThreshold(List list)
    {
        HashMap hashmap = mThresholds;
        hashmap;
        JVM INSTR monitorenter ;
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            String as[] = ((String)list.next()).split("#");
            if(as.length == 2)
                mThresholds.put(as[0], Integer.valueOf(as[1]));
        } while(true);
        break MISSING_BLOCK_LABEL_68;
        list;
        throw list;
        hashmap;
        JVM INSTR monitorexit ;
    }

    public static void updatePackageLockedStatus(String s, boolean flag)
    {
        updatePackageLockedStatus(s, flag, 0);
    }

    public static void updatePackageLockedStatus(String s, boolean flag, int i)
    {
        ConcurrentHashMap concurrenthashmap = mProcessLocked;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        Object obj = (List)mProcessLocked.get(Integer.valueOf(i));
        if(obj != null)
            break MISSING_BLOCK_LABEL_65;
        obj = JVM INSTR new #74  <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        if(!flag)
            break MISSING_BLOCK_LABEL_62;
        ((List) (obj)).add(s);
        mProcessLocked.put(Integer.valueOf(i), obj);
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return;
        if(!flag)
            break MISSING_BLOCK_LABEL_81;
        ((List) (obj)).add(s);
_L1:
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return;
        obj = ((List) (obj)).iterator();
        while(((Iterator) (obj)).hasNext()) 
            if(((String)((Iterator) (obj)).next()).equals(s))
                ((Iterator) (obj)).remove();
          goto _L1
        s;
        throw s;
    }

    public static void updateUserLockedAppList(List list)
    {
        updateUserLockedAppList(list, 0);
    }

    public static void updateUserLockedAppList(List list, int i)
    {
        mProcessLocked.put(Integer.valueOf(i), list);
    }

    private static final boolean BOARD_PERFORMANCE_SUPPORT;
    private static boolean CACHAED_STATISTICS_SUPPORT = false;
    private static boolean CHECK_APP_MEMORY_SUPPORT = false;
    private static final boolean DEBUG = false;
    private static final String POWER_PROFILE_CONFIG = "persist.sys.aries.power_profile";
    private static final String POWER_PROFILE_PERFORMANCE = "high";
    private static boolean RED_SUPPORT = false;
    private static final String TAG = "WhetstoneClientManager";
    private static Context mContext = null;
    private static final Object mLock = new Object();
    private static ConcurrentHashMap mProcessLocked = new ConcurrentHashMap();
    private static IWhetstoneClient mService;
    public static WhetstoneSystemSetting mSetting;
    private static HashMap mThresholds = new HashMap();
    private static WhetstoneActivityManagerService mWhetstoneAM = null;
    private static List protectApps;
    private static long sAndroidCachePss;
    private static int sCallingPid;
    private static boolean sPerformanceEnable = false;
    private static HashSet sPerformanceSet;
    private static String sPowerProfile;

    static 
    {
        RED_SUPPORT = MiuiFeatureUtils.isSystemFeatureSupported("feature_red_suport", true);
        protectApps = new ArrayList();
        protectApps.add("com.tencent.mm");
        protectApps.add("com.jeejen.family.miui");
        protectApps.add("com.google.android.gms");
        CACHAED_STATISTICS_SUPPORT = MiuiFeatureUtils.isSystemFeatureSupported("feature_cached_statistics_suport", true);
        sPerformanceSet = new HashSet();
        sPerformanceSet.add(new ComponentName("com.tencent.mm", "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI"));
        sPerformanceSet.add(new ComponentName("com.tencent.mm", "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI"));
        sPerformanceSet.add(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
        BOARD_PERFORMANCE_SUPPORT = Build.HARDWARE.equals("qcom");
        CHECK_APP_MEMORY_SUPPORT = MiuiFeatureUtils.isSystemFeatureSupported("feature_check_app_memory_suport", true);
    }
}
