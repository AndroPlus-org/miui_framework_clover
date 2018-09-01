// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.content.*;
import android.content.pm.*;
import android.miui.AppOpsUtils;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import com.android.internal.app.IAppOpsService;
import com.android.internal.app.IWakePathCallback;
import java.io.PrintWriter;
import java.util.*;
import miui.os.Build;
import miui.securityspace.XSpaceUserHandle;

// Referenced classes of package miui.security:
//            WakePathRuleInfo, AppRunningControlManager

public class WakePathChecker
{
    private class WakePathRuleData
    {

        Map mAllowedStartActivityRulesMap;
        List mWakePathRuleInfosList;
        List mWakePathWhiteList;
        final WakePathChecker this$0;

        WakePathRuleData()
        {
            this$0 = WakePathChecker.this;
            super();
            mWakePathRuleInfosList = new ArrayList(4);
            for(int i = 0; i < 4; i++)
                mWakePathRuleInfosList.add(null);

        }
    }


    static void _2D_wrap0(WakePathChecker wakepathchecker, Context context)
    {
        wakepathchecker.updateLauncherPackageNames(context);
    }

    private WakePathChecker()
    {
        mCallListLogLocker = new Object();
        mTrackCallListLogEnabled = Build.IS_STABLE_VERSION ^ true;
        mUserWakePathRuleDataMap = new HashMap();
        mWakePathConfirmDialogWhitelist = new ArrayList();
        mWakePathCallerWhiteList = new ArrayList();
        mWakePathConfirmDialogCallerWhitelist = new ArrayList();
        mBindServiceCheckActions = new ArrayList();
        mLauncherPackageNames = new ArrayList();
        if(mTrackCallListLogEnabled)
            mCallListLogMap = new HashMap(200);
        mWakePathConfirmDialogWhitelist.add("com.mfashiongallery.express");
        mWakePathConfirmDialogWhitelist.add("com.mi.dlabs.vr.thor");
        mWakePathCallerWhiteList.add("com.miui.home");
        mWakePathCallerWhiteList.add("com.miui.securitycenter");
        mBindServiceCheckActions.add("miui.action.CAMERA_EMPTY_SERVICE");
        mBindServiceCheckActions.add("android.media.browse.MediaBrowserService");
        mAppOpsService = com.android.internal.app.IAppOpsService.Stub.asInterface(ServiceManager.getService("appops"));
    }

    public static WakePathChecker getInstance()
    {
        miui/security/WakePathChecker;
        JVM INSTR monitorenter ;
        WakePathChecker wakepathchecker1;
        if(sInstance == null)
        {
            WakePathChecker wakepathchecker = JVM INSTR new #2   <Class WakePathChecker>;
            wakepathchecker.WakePathChecker();
            sInstance = wakepathchecker;
        }
        wakepathchecker1 = sInstance;
        miui/security/WakePathChecker;
        JVM INSTR monitorexit ;
        return wakepathchecker1;
        Exception exception;
        exception;
        throw exception;
    }

    private WakePathRuleData getWakePathRuleDataByUser(int i)
    {
        int j;
label0:
        {
            if(!XSpaceUserHandle.isXSpaceUserId(i))
            {
                j = i;
                if(i != -1)
                    break label0;
            }
            j = 0;
        }
        Map map = mUserWakePathRuleDataMap;
        map;
        JVM INSTR monitorenter ;
        WakePathRuleData wakepathruledata = (WakePathRuleData)mUserWakePathRuleDataMap.get(Integer.valueOf(j));
        WakePathRuleData wakepathruledata1;
        wakepathruledata1 = wakepathruledata;
        if(wakepathruledata != null)
            break MISSING_BLOCK_LABEL_77;
        wakepathruledata1 = JVM INSTR new #8   <Class WakePathChecker$WakePathRuleData>;
        wakepathruledata1.this. WakePathRuleData();
        mUserWakePathRuleDataMap.put(Integer.valueOf(j), wakepathruledata1);
        map;
        JVM INSTR monitorexit ;
        return wakepathruledata1;
        Exception exception;
        exception;
        throw exception;
    }

    private void trackCallListInfo(String s, String s1, String s2, String s3, int i)
    {
        Object obj = mCallListLogLocker;
        obj;
        JVM INSTR monitorenter ;
        if(!TextUtils.isEmpty(s2) && !TextUtils.isEmpty(s3))
            break MISSING_BLOCK_LABEL_68;
        s1 = TAG;
        s = JVM INSTR new #178 <Class StringBuilder>;
        s.StringBuilder();
        Slog.w(s1, s.append("MIUILOG-WAKEPATH trackCallListInfo: invalid parameter caller=").append(s2).append(" callee=").append(s3).toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mCallListLogMap == null) goto _L2; else goto _L1
_L1:
        int j = mCallListLogMap.size();
        if(j < 200)
            break MISSING_BLOCK_LABEL_98;
        obj;
        JVM INSTR monitorexit ;
        return;
        j = WakePathRuleInfo.getHashCode(s, s1, s2, s3);
        if(j != 0)
            break MISSING_BLOCK_LABEL_195;
        String s4 = TAG;
        StringBuilder stringbuilder = JVM INSTR new #178 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(s4, stringbuilder.append("MIUILOG-WAKEPATH trackCallListInfo: hashCode == 0,(action =").append(s).append(" className=").append(s1).append(" caller=").append(s2).append(" callee=").append(s3).append(" wakeType=").append(i).append(")").toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        WakePathRuleInfo wakepathruleinfo = (WakePathRuleInfo)mCallListLogMap.get(Integer.valueOf(j));
        if(wakepathruleinfo != null) goto _L2; else goto _L3
_L3:
        WakePathRuleInfo wakepathruleinfo1;
        wakepathruleinfo1 = JVM INSTR new #202 <Class WakePathRuleInfo>;
        wakepathruleinfo1.WakePathRuleInfo(s, s1, s2, s3, i, 0);
        s = wakepathruleinfo1;
_L6:
        if(s == null) goto _L2; else goto _L4
_L4:
        mCallListLogMap.put(Integer.valueOf(j), s);
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        s.printStackTrace();
        s = wakepathruleinfo;
        if(true) goto _L6; else goto _L5
_L5:
        s;
        throw s;
    }

    private void updateLauncherPackageNames(Context context)
    {
        Object obj;
        obj = new ArrayList();
        Object obj1 = new Intent("android.intent.action.MAIN");
        ((Intent) (obj1)).addCategory("android.intent.category.HOME");
        try
        {
            ResolveInfo resolveinfo;
            StringBuilder stringbuilder;
            for(context = context.getPackageManager().queryIntentActivities(((Intent) (obj1)), 0).iterator(); context.hasNext(); Slog.i(((String) (obj1)), stringbuilder.append("updateLauncherPackageNames =").append(resolveinfo.activityInfo.packageName).toString()))
            {
                resolveinfo = (ResolveInfo)context.next();
                ((List) (obj)).add(resolveinfo.activityInfo.packageName);
                obj1 = TAG;
                stringbuilder = JVM INSTR new #178 <Class StringBuilder>;
                stringbuilder.StringBuilder();
            }

        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Slog.e(TAG, "updateLauncherPackageNames", context);
        }
        context = mLauncherPackageNames;
        context;
        JVM INSTR monitorenter ;
        mLauncherPackageNames.clear();
        if(obj == null)
            break MISSING_BLOCK_LABEL_205;
        if(((List) (obj)).size() > 0)
        {
            String s;
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); mLauncherPackageNames.add(s))
                s = (String)((Iterator) (obj)).next();

        }
        break MISSING_BLOCK_LABEL_205;
        Exception exception;
        exception;
        throw exception;
        context;
        JVM INSTR monitorexit ;
    }

    private int wakeTypeToRuleInfosListIndex(int i)
    {
        byte byte0 = -1;
        if(i != 8) goto _L2; else goto _L1
_L1:
        byte0 = 3;
_L4:
        return byte0;
_L2:
        if(i == 1)
            byte0 = 0;
        else
        if(i == 4)
            byte0 = 2;
        else
        if(i == 2)
            byte0 = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean checkAllowStartActivity(String s, String s1, int i)
    {
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
            return true;
        Object obj = mWakePathConfirmDialogWhitelist;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mWakePathConfirmDialogWhitelist.contains(s1);
        if(!flag)
            break MISSING_BLOCK_LABEL_47;
        obj;
        JVM INSTR monitorexit ;
        return true;
        obj;
        JVM INSTR monitorexit ;
        obj = mLauncherPackageNames;
        obj;
        JVM INSTR monitorenter ;
        flag = mLauncherPackageNames.contains(s);
        if(!flag)
            break MISSING_BLOCK_LABEL_87;
        obj;
        JVM INSTR monitorexit ;
        return true;
        s;
        throw s;
        obj;
        JVM INSTR monitorexit ;
        obj = mWakePathConfirmDialogCallerWhitelist;
        obj;
        JVM INSTR monitorenter ;
        flag = mWakePathConfirmDialogCallerWhitelist.contains(s);
        if(!flag)
            break MISSING_BLOCK_LABEL_127;
        obj;
        JVM INSTR monitorexit ;
        return true;
        s;
        throw s;
        obj;
        JVM INSTR monitorexit ;
        obj = getWakePathRuleDataByUser(i);
        obj;
        JVM INSTR monitorenter ;
        if(((WakePathRuleData) (obj)).mWakePathWhiteList == null || ((WakePathRuleData) (obj)).mWakePathWhiteList.size() <= 0)
            break MISSING_BLOCK_LABEL_190;
        flag = ((WakePathRuleData) (obj)).mWakePathWhiteList.contains(s1);
        if(!flag)
            break MISSING_BLOCK_LABEL_190;
        obj;
        JVM INSTR monitorexit ;
        return true;
        s;
        throw s;
        if(((WakePathRuleData) (obj)).mAllowedStartActivityRulesMap == null)
            break MISSING_BLOCK_LABEL_236;
        s1 = (List)((WakePathRuleData) (obj)).mAllowedStartActivityRulesMap.get(s1);
        if(s1 == null)
            break MISSING_BLOCK_LABEL_236;
        flag = s1.contains(s);
        if(flag)
            return true;
        obj;
        JVM INSTR monitorexit ;
        return false;
        s;
        throw s;
    }

    public boolean checkBroadcastWakePath(Intent intent, String s, ApplicationInfo applicationinfo, ResolveInfo resolveinfo, int i)
    {
        if(intent == null || TextUtils.isEmpty(s))
            return true;
        String s1 = "";
        String s2 = "";
        String s3 = "";
        byte byte0 = -1;
        String s4 = s3;
        applicationinfo = s1;
        if(intent != null)
        {
            String s5 = intent.getAction();
            s2 = s5;
            s4 = s3;
            applicationinfo = s1;
            if(intent.getComponent() != null)
            {
                s4 = intent.getComponent().getClassName();
                applicationinfo = intent.getComponent().getPackageName();
                s2 = s5;
            }
        }
        String s6 = s4;
        intent = applicationinfo;
        int j = byte0;
        if(resolveinfo != null)
        {
            s6 = s4;
            intent = applicationinfo;
            j = byte0;
            if(resolveinfo.activityInfo != null)
            {
                j = byte0;
                if(resolveinfo.activityInfo.applicationInfo != null)
                {
                    applicationinfo = resolveinfo.activityInfo.applicationInfo.packageName;
                    j = resolveinfo.activityInfo.applicationInfo.uid;
                }
                s6 = resolveinfo.activityInfo.name;
                intent = applicationinfo;
            }
        }
        if(TextUtils.equals(intent, s))
            return true;
        else
            return matchWakePathRule(s2, s6, s, intent, j, 2, i) ^ true;
    }

    public void dump(PrintWriter printwriter)
    {
        if(printwriter == null)
            return;
        printwriter.println("========================================WAKEPATH DUMP BEGIN========================================");
        Object obj = mUserWakePathRuleDataMap;
        obj;
        JVM INSTR monitorenter ;
        if(mUserWakePathRuleDataMap.size() <= 0) goto _L2; else goto _L1
_L1:
        Iterator iterator = mUserWakePathRuleDataMap.keySet().iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        Integer integer;
        Object obj1;
        integer = (Integer)iterator.next();
        obj1 = (WakePathRuleData)mUserWakePathRuleDataMap.get(integer);
        printwriter.println("----------------------------------------");
        if(obj1 == null) goto _L5; else goto _L4
_L4:
        StringBuilder stringbuilder = JVM INSTR new #178 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        printwriter.println(stringbuilder.append("userId=").append(integer).toString());
        if(((WakePathRuleData) (obj1)).mWakePathWhiteList == null) goto _L7; else goto _L6
_L6:
        StringBuilder stringbuilder1 = JVM INSTR new #178 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        printwriter.println(stringbuilder1.append("whitelist=").append(((WakePathRuleData) (obj1)).mWakePathWhiteList.toString()).toString());
_L12:
        int i = 0;
_L11:
        if(i >= 4) goto _L5; else goto _L8
_L8:
        if(((WakePathRuleData) (obj1)).mWakePathRuleInfosList.get(i) != null) goto _L10; else goto _L9
_L9:
        StringBuilder stringbuilder2 = JVM INSTR new #178 <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        printwriter.println(stringbuilder2.append("rule info index=").append(i).append(" size=0").toString());
_L13:
        i++;
          goto _L11
_L7:
        printwriter.println("whitelist is null.");
          goto _L12
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj;
        Slog.e(TAG, "dump", ((Throwable) (obj)));
_L14:
        printwriter.println("========================================WAKEPATH DUMP END========================================");
        return;
_L10:
        StringBuilder stringbuilder3 = JVM INSTR new #178 <Class StringBuilder>;
        stringbuilder3.StringBuilder();
        printwriter.println(stringbuilder3.append("rule info index=").append(i).append(" size=").append(((List)((WakePathRuleData) (obj1)).mWakePathRuleInfosList.get(i)).size()).toString());
          goto _L13
_L2:
        obj;
        JVM INSTR monitorexit ;
          goto _L14
    }

    public ParceledListSlice getWakePathCallListLog()
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        if(!mTrackCallListLogEnabled) goto _L2; else goto _L1
_L1:
        Object obj2 = mCallListLogLocker;
        obj2;
        JVM INSTR monitorenter ;
        obj = obj1;
        if(mCallListLogMap == null)
            break MISSING_BLOCK_LABEL_53;
        obj = JVM INSTR new #84  <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList(mCallListLogMap.values());
        mCallListLogMap.clear();
        obj2;
        JVM INSTR monitorexit ;
_L2:
        if(obj == null)
            return null;
        else
            return new ParceledListSlice(((List) (obj)));
        obj;
_L4:
        obj2;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void init(Context context)
    {
        updateLauncherPackageNames(context);
    }

    public boolean matchWakePathRule(String s, String s1, String s2, String s3, int i, int j, int k)
    {
        if(AppRunningControlManager.matchRule(s3, j))
        {
            Slog.w(TAG, (new StringBuilder()).append("AppRunningControl, Reject userId= ").append(k).append(" caller= ").append(s2).append(" callee= ").append(s3).append(" classname=").append(s1).append(" action=").append(s).append(" wakeType=").append(j).toString());
            return true;
        }
        if(mTrackCallListLogEnabled)
            trackCallListInfo(s, s1, s2, s3, j);
        WakePathRuleData wakepathruledata = getWakePathRuleDataByUser(k);
        wakepathruledata;
        JVM INSTR monitorenter ;
        if(!mWakePathCallerWhiteList.contains(s2) && (wakepathruledata.mWakePathWhiteList == null || wakepathruledata.mWakePathWhiteList.size() <= 0 || !wakepathruledata.mWakePathWhiteList.contains(s3)))
            break MISSING_BLOCK_LABEL_213;
        s = mCallback;
        if(s == null)
            break MISSING_BLOCK_LABEL_194;
        mCallback.onAllowCall(s2, s3, j, k);
_L2:
        wakepathruledata;
        JVM INSTR monitorexit ;
        return false;
        s;
        s.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        throw s;
        if(j != 8 || i <= 0 || s3 == null)
            break MISSING_BLOCK_LABEL_385;
        long l;
        if(!mBindServiceCheckActions.contains(s) || !(AppOpsUtils.isXOptMode() ^ true) || mAppOpsService == null)
            break MISSING_BLOCK_LABEL_385;
        l = Binder.clearCallingIdentity();
        if(mAppOpsService.checkOperation(10008, i, s3) == 0)
            break MISSING_BLOCK_LABEL_380;
        String s4 = TAG;
        StringBuilder stringbuilder = JVM INSTR new #178 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.w(s4, stringbuilder.append("MIUILOG-AutoStart, BindService Reject userId= ").append(k).append(" caller= ").append(s2).append(" callee= ").append(s3).append(" classname=").append(s1).append(" action=").append(s).append(" wakeType=").append(j).toString());
        Binder.restoreCallingIdentity(l);
        wakepathruledata;
        JVM INSTR monitorexit ;
        return true;
        Binder.restoreCallingIdentity(l);
_L3:
        i = wakeTypeToRuleInfosListIndex(j);
        if(i >= 0 && i < 4)
            break MISSING_BLOCK_LABEL_449;
        Slog.e(TAG, "MIUILOG-WAKEPATH invalid parameter");
        wakepathruledata;
        JVM INSTR monitorexit ;
        return false;
        Object obj;
        obj;
        Log.e(TAG, "checkOperation", ((Throwable) (obj)));
        Binder.restoreCallingIdentity(l);
          goto _L3
        s;
        Binder.restoreCallingIdentity(l);
        throw s;
        Object obj1 = (List)wakepathruledata.mWakePathRuleInfosList.get(i);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_485;
        i = ((List) (obj1)).size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_490;
        wakepathruledata;
        JVM INSTR monitorexit ;
        return false;
        int i1 = ((List) (obj1)).size();
        i = 0;
_L6:
        if(i >= i1)
            break; /* Loop/switch isn't completed */
        if(!((WakePathRuleInfo)((List) (obj1)).get(i)).equals(s, s1, s2, s3, j))
            break MISSING_BLOCK_LABEL_664;
        obj1 = mCallback;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_561;
        mCallback.onRejectCall(s2, s3, j, k);
_L4:
        String s5 = TAG;
        StringBuilder stringbuilder1 = JVM INSTR new #178 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Slog.w(s5, stringbuilder1.append("MIUILOG-WAKEPATH: call was rejected by wakepath. userId= ").append(k).append(" caller= ").append(s2).append(" callee= ").append(s3).append(" classname=").append(s1).append(" action=").append(s).append(" wakeType=").append(j).toString());
        wakepathruledata;
        JVM INSTR monitorexit ;
        return true;
        stringbuilder1;
        stringbuilder1.printStackTrace();
          goto _L4
        i++;
        if(true) goto _L6; else goto _L5
_L5:
        s = mCallback;
        if(s == null)
            break MISSING_BLOCK_LABEL_695;
        mCallback.onAllowCall(s2, s3, j, k);
_L7:
        wakepathruledata;
        JVM INSTR monitorexit ;
        return false;
        s;
        s.printStackTrace();
          goto _L7
    }

    public void onPackageAdded(final Context context)
    {
        (new Thread() {

            public void run()
            {
                WakePathChecker._2D_wrap0(WakePathChecker.this, context);
            }

            final WakePathChecker this$0;
            final Context val$context;

            
            {
                this$0 = WakePathChecker.this;
                context = context1;
                super();
            }
        }
).start();
    }

    public void pushWakePathConfirmDialogWhiteList(int i, List list)
    {
        if(list == null || list.size() == 0)
            return;
        if(i != 1) goto _L2; else goto _L1
_L1:
        List list1 = mWakePathConfirmDialogWhitelist;
        list1;
        JVM INSTR monitorenter ;
        mWakePathConfirmDialogWhitelist.clear();
        mWakePathConfirmDialogWhitelist.addAll(list);
        list = list1;
_L6:
        list;
        JVM INSTR monitorexit ;
_L4:
        return;
        list;
        throw list;
_L2:
        if(i != 2) goto _L4; else goto _L3
_L3:
        list1 = mWakePathConfirmDialogCallerWhitelist;
        list1;
        JVM INSTR monitorenter ;
        mWakePathConfirmDialogCallerWhitelist.clear();
        mWakePathConfirmDialogCallerWhitelist.addAll(list);
        list = list1;
        if(true) goto _L6; else goto _L5
_L5:
        list;
        throw list;
    }

    public void pushWakePathRuleInfos(int i, List list, int j)
    {
        WakePathRuleData wakepathruledata;
        int k = 0;
        Object obj = TAG;
        Object obj1 = (new StringBuilder()).append("MIUILOG-WAKEPATH pushWakePathRuleInfos: wakeType=").append(i).append(" userId=").append(j).append(" size=");
        WakePathRuleInfo wakepathruleinfo;
        if(list != null)
            k = list.size();
        Slog.i(((String) (obj)), ((StringBuilder) (obj1)).append(k).toString());
        wakepathruledata = getWakePathRuleDataByUser(j);
        wakepathruledata;
        JVM INSTR monitorenter ;
        if(i != 17) goto _L2; else goto _L1
_L1:
        obj1 = JVM INSTR new #79  <Class HashMap>;
        ((HashMap) (obj1)).HashMap();
        wakepathruledata.mAllowedStartActivityRulesMap = ((Map) (obj1));
        if(list == null) goto _L4; else goto _L3
_L3:
        i = 0;
_L5:
        if(i >= list.size())
            break; /* Loop/switch isn't completed */
        wakepathruleinfo = (WakePathRuleInfo)list.get(i);
        obj = (List)wakepathruledata.mAllowedStartActivityRulesMap.get(wakepathruleinfo.getCalleeExpress());
        obj1 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_181;
        obj1 = JVM INSTR new #84  <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList();
        wakepathruledata.mAllowedStartActivityRulesMap.put(wakepathruleinfo.getCalleeExpress(), obj1);
        ((List) (obj1)).add(wakepathruleinfo.getCallerExpress());
        i++;
        if(true) goto _L5; else goto _L4
_L2:
        i = wakeTypeToRuleInfosListIndex(i);
        if(i < 0 || i >= 4) goto _L4; else goto _L6
_L6:
        wakepathruledata.mWakePathRuleInfosList.set(i, list);
_L4:
        wakepathruledata;
        JVM INSTR monitorexit ;
        return;
        list;
        throw list;
    }

    public void pushWakePathWhiteList(List list, int i)
    {
        Object obj;
        obj = TAG;
        StringBuilder stringbuilder = (new StringBuilder()).append("MIUILOG-WAKEPATH pushWakePathWhiteList: userId=").append(i).append(" size=");
        int j;
        if(list == null)
            j = 0;
        else
            j = list.size();
        Slog.i(((String) (obj)), stringbuilder.append(j).toString());
        obj = getWakePathRuleDataByUser(i);
        obj;
        JVM INSTR monitorenter ;
        obj.mWakePathWhiteList = list;
        obj;
        JVM INSTR monitorexit ;
        return;
        list;
        throw list;
    }

    public void registerWakePathCallback(IWakePathCallback iwakepathcallback)
    {
        mCallback = iwakepathcallback;
    }

    public void removeWakePathData(int i)
    {
        Slog.i(TAG, (new StringBuilder()).append("MIUILOG-WAKEPATH removeWakePathData: userId=").append(i).toString());
        if(i == 0 || XSpaceUserHandle.isXSpaceUserId(i))
            return;
        Map map = mUserWakePathRuleDataMap;
        map;
        JVM INSTR monitorenter ;
        WakePathRuleData wakepathruledata = (WakePathRuleData)mUserWakePathRuleDataMap.get(Integer.valueOf(i));
        if(wakepathruledata == null)
            break MISSING_BLOCK_LABEL_78;
        mUserWakePathRuleDataMap.remove(wakepathruledata);
        map;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setTrackWakePathCallListLogEnabled(boolean flag)
    {
        if(flag)
            return;
        mTrackCallListLogEnabled = flag;
        if(mTrackCallListLogEnabled) goto _L2; else goto _L1
_L1:
        Object obj = mCallListLogLocker;
        obj;
        JVM INSTR monitorenter ;
        if(mCallListLogMap != null)
        {
            mCallListLogMap.clear();
            mCallListLogMap = null;
        }
        obj;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int CALL_LIST_LOG_MAP_MAX_SIZE = 200;
    private static final int GET_CONTENT_PROVIDER_RULE_INFOS_LIST_INDEX = 2;
    private static final int RULE_INFOS_LIST_COUNT = 4;
    private static final int SEND_BROADCAST_RULE_INFOS_LIST_INDEX = 1;
    private static final int START_ACTIVITY_RULE_INFOS_LIST_INDEX = 0;
    private static final int START_SERVICE_RULE_INFOS_LIST_INDEX = 3;
    private static final String TAG = miui/security/WakePathChecker.getSimpleName();
    public static final int WAKEPATH_CONFIRM_DIALOG_WHITELIST_TYPE_CALLEE = 1;
    public static final int WAKEPATH_CONFIRM_DIALOG_WHITELIST_TYPE_CALLER = 2;
    private static WakePathChecker sInstance;
    private IAppOpsService mAppOpsService;
    private List mBindServiceCheckActions;
    Object mCallListLogLocker;
    Map mCallListLogMap;
    private IWakePathCallback mCallback;
    List mLauncherPackageNames;
    boolean mTrackCallListLogEnabled;
    private Map mUserWakePathRuleDataMap;
    private List mWakePathCallerWhiteList;
    private List mWakePathConfirmDialogCallerWhitelist;
    private List mWakePathConfirmDialogWhitelist;

}
