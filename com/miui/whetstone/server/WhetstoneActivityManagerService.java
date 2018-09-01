// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.server;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.os.*;
import android.util.*;
import com.android.internal.os.BackgroundThread;
import com.miui.whetstone.IPowerKeeperPolicy;
import com.miui.whetstone.PowerKeeperPolicy;
import com.miui.whetstone.client.WhetstoneClientManager;
import com.miui.whetstone.component.*;
import com.miui.whetstone.process.WtServiceControlEntry;
import com.miui.whetstone.strategy.WhetstoneSystemSetting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.Iterator;
import java.util.List;
import miui.util.ReflectionUtils;

public class WhetstoneActivityManagerService extends IWhetstoneActivityManager.Stub
{
    private final class PromoteLevelManagerHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 32
        //                       2 33
        //                       3 32;
               goto _L1 _L1 _L2 _L1
_L1:
            return;
_L2:
            WhetstoneActivityManagerService._2D_wrap0(WhetstoneActivityManagerService.this);
            if(true) goto _L1; else goto _L3
_L3:
        }

        final WhetstoneActivityManagerService this$0;

        public PromoteLevelManagerHandler(Looper looper)
        {
            this$0 = WhetstoneActivityManagerService.this;
            super(looper, null, true);
        }
    }


    static Class _2D_get0(WhetstoneActivityManagerService whetstoneactivitymanagerservice)
    {
        return whetstoneactivitymanagerservice.PowerManagerServiceInjector;
    }

    static PowerManager _2D_get1(WhetstoneActivityManagerService whetstoneactivitymanagerservice)
    {
        return whetstoneactivitymanagerservice.mPowerManager;
    }

    static PowerManager _2D_set0(WhetstoneActivityManagerService whetstoneactivitymanagerservice, PowerManager powermanager)
    {
        whetstoneactivitymanagerservice.mPowerManager = powermanager;
        return powermanager;
    }

    static void _2D_wrap0(WhetstoneActivityManagerService whetstoneactivitymanagerservice)
    {
        whetstoneactivitymanagerservice.handleClearDeadAppFromNative();
    }

    public WhetstoneActivityManagerService(Context context)
    {
        mUidFrozenState = new SparseBooleanArray();
        mDeviceIdleChangeReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                if(WhetstoneActivityManagerService._2D_get1(WhetstoneActivityManagerService.this) == null)
                    WhetstoneActivityManagerService._2D_set0(WhetstoneActivityManagerService.this, (PowerManager)context1.getSystemService("power"));
                boolean flag = ((Boolean)android/os/PowerManager.getDeclaredMethod("isDeviceIdleMode", new Class[0]).invoke(WhetstoneActivityManagerService._2D_get1(WhetstoneActivityManagerService.this), new Object[0])).booleanValue();
                context1 = JVM INSTR new #62  <Class StringBuilder>;
                context1.StringBuilder();
                Slog.v("whetstone.activity", context1.append("DeviceIdleMode changed to ").append(flag).toString());
                WhetstoneActivityManagerService._2D_get0(WhetstoneActivityManagerService.this).getDeclaredMethod("updateAllPartialWakeLockDisableState", new Class[0]).invoke(null, new Object[0]);
_L1:
                return;
                context1;
                Log.e("whetstone.activity", Log.getStackTraceString(context1));
                  goto _L1
            }

            final WhetstoneActivityManagerService this$0;

            
            {
                this$0 = WhetstoneActivityManagerService.this;
                super();
            }
        }
;
        mContext = context;
        mSystemServiceClassLoader = Thread.currentThread().getContextClassLoader();
        Object obj;
        mExtraActivityManagerService = Class.forName("com.android.server.am.ExtraActivityManagerService", false, mSystemServiceClassLoader);
        obj = ReflectionUtils.findMethodExact(ReflectionUtils.findClass("android.os.ServiceManager", null), "getService", new Class[] {
            java/lang/String
        });
        mAM = (IBinder)((Method) (obj)).invoke(null, new Object[] {
            "activity"
        });
        obj = ReflectionUtils.findField(ReflectionUtils.findClass("com.android.server.am.ActivityManagerService", mSystemServiceClassLoader), "mPidsSelfLocked");
        if(obj == null)
            break MISSING_BLOCK_LABEL_336;
        Exception exception;
        IntentFilter intentfilter;
        try
        {
            mPidsSelfLocked = (SparseArray)((Field) (obj)).get(mAM);
            findRemoveTaskMethod();
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.e("whetstone.activity", "WhetstoneActivityManagerService", illegalargumentexception);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            Log.e("whetstone.activity", "WhetstoneActivityManagerService", nosuchmethodexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            Log.e("whetstone.activity", "WhetstoneActivityManagerService", illegalaccessexception);
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            Log.e("whetstone.activity", "WhetstoneActivityManagerService", nosuchfieldexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            Log.e("whetstone.activity", "WhetstoneActivityManagerService", classnotfoundexception);
        }
        ComponentHelper.init(mExtraActivityManagerService);
        mScheduleDestroyActivities = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "scheduleDestroyActivities", new Class[] {
            Integer.TYPE, Boolean.TYPE, java/lang/String
        });
        if(mScheduleDestroyActivities == null)
        {
            obj = JVM INSTR new #210 <Class RuntimeException>;
            ((RuntimeException) (obj)).RuntimeException("mScheduleDestroyActivities not found in AcivityManagerService");
            throw obj;
        }
        break MISSING_BLOCK_LABEL_405;
        try
        {
            PowerManagerServiceInjector = Class.forName("com.android.server.power.PowerManagerServiceInjector", false, mSystemServiceClassLoader);
            getPartialWakeLockHoldByUid = ReflectionUtils.findMethodExact(PowerManagerServiceInjector, "getPartialWakeLockHoldByUid", new Class[] {
                Integer.TYPE
            });
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            Log.e("whetstone.activity", Log.getStackTraceString(exception));
        }
        mPowerKeeperPolicy = PowerKeeperPolicy.getInstance();
        mPowerKeeperPolicy.setContext(mContext);
        mHandler = new PromoteLevelManagerHandler(BackgroundThread.get().getLooper());
        try
        {
            exception = (String)android/os/PowerManager.getDeclaredField("ACTION_DEVICE_IDLE_MODE_CHANGED").get(null);
            intentfilter = JVM INSTR new #258 <Class IntentFilter>;
            intentfilter.IntentFilter(exception);
            context.registerReceiver(mDeviceIdleChangeReceiver, intentfilter, null, mHandler);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("whetstone.activity", Log.getStackTraceString(context));
        }
        mSelf = this;
        mCm = WtComponentManager.makeInstance(mContext);
        return;
        exception;
        Log.e("whetstone.activity", Log.getStackTraceString(exception));
        exception = JVM INSTR new #210 <Class RuntimeException>;
        exception.RuntimeException("Error: can not found AcivityManagerService");
        throw exception;
        illegalargumentexception = JVM INSTR new #210 <Class RuntimeException>;
        illegalargumentexception.RuntimeException("Error: mPidsSelfLocked not found in AcivityManagerService");
        throw illegalargumentexception;
    }

    private boolean checkCallInterfacePermission()
    {
        return Binder.getCallingUid() % 0x186a0 <= 10000;
    }

    private void findRemoveTaskMethod()
    {
        if(android.os.Build.VERSION.SDK_INT <= 21)
            try
            {
                mRemoveTask = ReflectionUtils.findMethodExact(android/app/ActivityManager, "removeTask", new Class[] {
                    Integer.TYPE, Integer.TYPE
                });
                mRemoveTaskByIdLocked = null;
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", nosuchmethodexception);
            }
        else
        if(android.os.Build.VERSION.SDK_INT <= 23)
            try
            {
                mRemoveTask = ReflectionUtils.findMethodExact(android/app/ActivityManager, "removeTask", new Class[] {
                    Integer.TYPE
                });
                mRemoveTaskByIdLocked = ReflectionUtils.findMethodExact(ReflectionUtils.findClass("com.android.server.am.ActivityManagerService", mSystemServiceClassLoader), "removeTaskByIdLocked", new Class[] {
                    Integer.TYPE, Boolean.TYPE
                });
            }
            catch(NoSuchMethodException nosuchmethodexception1)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", nosuchmethodexception1);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", classnotfoundexception);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", illegalargumentexception);
            }
        else
        if(android.os.Build.VERSION.SDK_INT < 26)
            try
            {
                mRemoveTask = ReflectionUtils.findMethodExact(android/app/ActivityManager, "removeTask", new Class[] {
                    Integer.TYPE
                });
                mRemoveTaskByIdLocked = ReflectionUtils.findMethodExact(ReflectionUtils.findClass("com.android.server.am.ActivityManagerService", mSystemServiceClassLoader), "removeTaskByIdLocked", new Class[] {
                    Integer.TYPE, Boolean.TYPE, Boolean.TYPE
                });
            }
            catch(NoSuchMethodException nosuchmethodexception2)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", nosuchmethodexception2);
            }
            catch(ClassNotFoundException classnotfoundexception1)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", classnotfoundexception1);
            }
            catch(IllegalArgumentException illegalargumentexception1)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", illegalargumentexception1);
            }
        else
            try
            {
                mRemoveTask = ReflectionUtils.findMethodExact(android/app/ActivityManager, "removeTask", new Class[] {
                    Integer.TYPE
                });
                mRemoveTaskByIdLocked = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "removeTaskByIdLocked", new Class[] {
                    Integer.TYPE, Boolean.TYPE, Boolean.TYPE
                });
            }
            catch(NoSuchMethodException nosuchmethodexception3)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", nosuchmethodexception3);
            }
            catch(IllegalArgumentException illegalargumentexception2)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", illegalargumentexception2);
            }
            catch(Exception exception)
            {
                Log.e("whetstone.activity", "WhetstoneActivityManagerService", exception);
            }
        if(mRemoveTask != null)
            mRemoveTask.setAccessible(true);
        else
            Slog.e("whetstone.activity", "could not find removeTask");
        if(mRemoveTaskByIdLocked != null)
            mRemoveTaskByIdLocked.setAccessible(true);
        else
            Slog.e("whetstone.activity", "could not find removeTaskByIdLocked");
    }

    private int getProcessPidByPackageNameLocked(String s, int i)
    {
        int j = mPidsSelfLocked.size();
        Class class1 = Class.forName("com.android.server.am.ProcessRecord", false, mSystemServiceClassLoader);
        int k = j - 1;
_L3:
        if(k < 0) goto _L2; else goto _L1
_L1:
        Object obj = mPidsSelfLocked.valueAt(k);
        if(obj == null)
            continue; /* Loop/switch isn't completed */
        String s1;
        Object obj1;
        s1 = (String)ReflectionUtils.findField(class1, "processName").get(obj);
        obj1 = (Integer)ReflectionUtils.findField(class1, "userId").get(obj);
        if(!s.equals(s1) || obj1 == null)
            continue; /* Loop/switch isn't completed */
        if(((Integer) (obj1)).intValue() == i)
            return ((Integer)ReflectionUtils.findField(class1, "pid").get(obj)).intValue();
        k--;
          goto _L3
_L2:
        int l;
        l = 0;
        k = -1;
        i = j - 1;
_L6:
        if(i < 0) goto _L5; else goto _L4
_L4:
        obj = mPidsSelfLocked.valueAt(i);
        int i1;
        i1 = l;
        j = k;
        if(obj == null)
            break MISSING_BLOCK_LABEL_258;
        obj1 = (ApplicationInfo)ReflectionUtils.findField(class1, "info").get(obj);
        i1 = l;
        j = k;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_258;
        i1 = l;
        j = k;
        if(((ApplicationInfo) (obj1)).className == null)
            break MISSING_BLOCK_LABEL_258;
        i1 = l;
        j = k;
        if(!((ApplicationInfo) (obj1)).className.contains(s))
            break MISSING_BLOCK_LABEL_258;
        i1 = l + 1;
        j = ((Integer)ReflectionUtils.findField(class1, "pid").get(obj)).intValue();
        i--;
        l = i1;
        k = j;
          goto _L6
_L5:
        if(l == 1)
            return k;
        break MISSING_BLOCK_LABEL_291;
        s;
        Log.e("whetstone.activity", Log.getStackTraceString(s));
        return -1;
    }

    public static WhetstoneActivityManagerService getSingletonService()
    {
        return mSelf;
    }

    private void handleClearDeadAppFromNative()
    {
        Method method = ReflectionUtils.findMethodExact(ReflectionUtils.findClass("com.android.server.am.ActivityManagerService", mSystemServiceClassLoader), "clearDeadAppFromNative", new Class[0]);
        if(method == null)
            break MISSING_BLOCK_LABEL_37;
        method.invoke(mAM, new Object[0]);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("whetstone.activity", "handleClearDeadAppFromNative", exception);
          goto _L1
    }

    private boolean removeTaskByIdInternal(int i, boolean flag, boolean flag1)
    {
label0:
        {
            if(android.os.Build.VERSION.SDK_INT <= 21)
                return removeTaskByIdL(i, flag, true);
            try
            {
                if(((IActivityManager)mAM).checkPermission("android.permission.REMOVE_TASKS", Binder.getCallingPid(), UserHandle.getAppId(Binder.getCallingUid())) == 0)
                    break label0;
                StringBuilder stringbuilder = JVM INSTR new #387 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Slog.w("whetstone.activity", stringbuilder.append("Permission Denial: removeTaskById from pid=").append(Binder.getCallingPid()).append(", uid=").append(Binder.getCallingUid()).append(" requires ").append("android.permission.REMOVE_TASKS").toString());
            }
            catch(Exception exception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(exception));
                return false;
            }
            return false;
        }
        return removeTaskByIdLocked(i, flag, true);
    }

    private boolean removeTaskByIdL(int i, boolean flag, boolean flag1)
    {
        int j = 1;
        ActivityManager activitymanager = (ActivityManager)mContext.getSystemService("activity");
        if(mRemoveTask == null)
        {
            Slog.e("whetstone.activity", "could not find removeTaskById L");
            return false;
        }
        Method method;
        try
        {
            method = mRemoveTask;
        }
        catch(Exception exception)
        {
            Log.e("whetstone.activity", Log.getStackTraceString(exception));
            return false;
        }
        if(!flag)
            j = 0;
        flag = ((Boolean)method.invoke(activitymanager, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        })).booleanValue();
        return flag;
    }

    private boolean removeTaskByIdLocked(int i, boolean flag, boolean flag1)
    {
        long l;
        if(mRemoveTaskByIdLocked == null)
        {
            Slog.e("whetstone.activity", "could not find removeTaskById M");
            return false;
        }
        l = Binder.clearCallingIdentity();
        IBinder ibinder = mAM;
        ibinder;
        JVM INSTR monitorenter ;
        if(android.os.Build.VERSION.SDK_INT > 23) goto _L2; else goto _L1
_L1:
        flag = ((Boolean)mRemoveTaskByIdLocked.invoke(mAM, new Object[] {
            Integer.valueOf(i), Boolean.valueOf(flag)
        })).booleanValue();
_L3:
        ibinder;
        JVM INSTR monitorexit ;
        Binder.restoreCallingIdentity(l);
        return flag;
_L2:
label0:
        {
            if(android.os.Build.VERSION.SDK_INT >= 26)
                break label0;
            flag = ((Boolean)mRemoveTaskByIdLocked.invoke(mAM, new Object[] {
                Integer.valueOf(i), Boolean.valueOf(flag), Boolean.valueOf(flag1)
            })).booleanValue();
        }
          goto _L3
        flag = ((Boolean)mRemoveTaskByIdLocked.invoke(null, new Object[] {
            Integer.valueOf(i), Boolean.valueOf(flag), Boolean.valueOf(flag1)
        })).booleanValue();
          goto _L3
        Object obj;
        obj;
        ibinder;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        Log.e("whetstone.activity", Log.getStackTraceString(((Throwable) (obj))));
        Binder.restoreCallingIdentity(l);
        return false;
        obj;
        Binder.restoreCallingIdentity(l);
        throw obj;
    }

    private void updateCurrentProcessPss(int i, long l)
    {
        Message message = mHandler.obtainMessage(3);
        message.arg1 = i;
        message.arg2 = (int)l;
        mHandler.sendMessage(message);
    }

    public void addAppToServiceControlWhitelist(List list)
    {
        WtServiceControlEntry.addAppToServiceControlWhitelist(list);
    }

    public void bindWhetstoneService(IBinder ibinder)
    {
        WhetstoneClientManager.init(mContext, com.miui.whetstone.IWhetstoneClient.Stub.asInterface(ibinder), this);
    }

    public void checkApplicationsMemoryThreshold(String s, int i, long l)
    {
    }

    public boolean checkIfPackageIsLocked(String s)
    {
        return WhetstoneClientManager.checkIfPackageIsLocked(s);
    }

    public boolean checkIfPackageIsLockedWithUserId(String s, int i)
    {
        return WhetstoneClientManager.checkIfPackageIsLocked(s, i);
    }

    public transient int checkPackageState(String s, String s1, int i, int j, String s2, String s3, Object aobj[])
    {
        boolean flag = true;
        Bundle bundle = new Bundle();
        if(aobj.length > 0)
            try
            {
                bundle.putParcelable("intent", (Intent)aobj[0]);
            }
            // Misplaced declaration of an exception variable
            catch(Object aobj[])
            {
                Log.e("WhetstonePackageState", Log.getStackTraceString(((Throwable) (aobj))));
            }
        if(mCm != null)
            flag = mCm.checkPackagePolicyState(s, s1, i, j, s2, s3, Binder.getCallingUid(), Binder.getCallingPid(), bundle);
        if(flag)
            i = 1;
        else
            i = -1;
        return i;
    }

    public void clearDeadAppFromNative()
    {
        Message message = mHandler.obtainMessage(2);
        mHandler.sendMessageDelayed(message, 5000L);
    }

    public boolean distoryActivity(int i)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(mScheduleDestroyActivities == null)
            break MISSING_BLOCK_LABEL_46;
        mScheduleDestroyActivities.invoke(null, new Object[] {
            Integer.valueOf(i), Boolean.valueOf(false), "whetstone"
        });
        flag1 = true;
_L2:
        return flag1;
        Object obj;
        obj;
        Log.e("whetstone.activity", "distoryActivity", ((Throwable) (obj)));
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("whetstone.activity", "distoryActivity", ((Throwable) (obj)));
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("whetstone.activity", "distoryActivity", ((Throwable) (obj)));
        flag1 = flag;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        int i;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        int j;
        i = 0;
        if(mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0)
        {
            printwriter.println((new StringBuilder()).append("Permission Denial: can't dump whetstone.activity service from from pid=").append(Binder.getCallingPid()).append(", uid=").append(Binder.getCallingUid()).toString());
            return;
        }
        flag = false;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        if(as == null)
            break MISSING_BLOCK_LABEL_200;
        j = as.length;
_L2:
        boolean flag5;
        String s;
        flag1 = flag2;
        flag5 = flag;
        flag3 = flag4;
        if(i >= j)
            break MISSING_BLOCK_LABEL_203;
        s = as[i];
        if(!s.equalsIgnoreCase("component"))
            break; /* Loop/switch isn't completed */
        flag3 = true;
        flag5 = flag4;
_L3:
        i++;
        flag2 = flag3;
        flag4 = flag5;
        if(true) goto _L2; else goto _L1
_L1:
label0:
        {
            if(!s.equalsIgnoreCase("powerkeeper"))
                break label0;
            flag5 = true;
            flag3 = flag2;
        }
          goto _L3
        if(s.equalsIgnoreCase("-a")) goto _L5; else goto _L4
_L4:
        flag3 = flag2;
        flag5 = flag4;
        if(!s.equalsIgnoreCase("all")) goto _L3; else goto _L5
_L5:
        flag = true;
        flag3 = flag2;
        flag5 = flag4;
          goto _L3
        flag5 = true;
        if(flag3 || flag5)
        {
            PowerKeeperPolicy powerkeeperpolicy = getPowerKeeperPolicy();
            if(powerkeeperpolicy != null)
                powerkeeperpolicy.dump(filedescriptor, printwriter, as);
        }
        if(flag1 || flag5)
            mCm.dump(filedescriptor, printwriter, as);
        return;
    }

    public long getAndroidCachedEmptyProcessMemory()
    {
        return WhetstoneClientManager.getEmptyProcTotalMemoryInfo();
    }

    public String[] getBackgroundAPPS()
    {
        return null;
    }

    public boolean getConnProviderNames(String s, int i, List list)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        flag = false;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = flag;
        if(mExtraActivityManagerService == null) goto _L2; else goto _L1
_L1:
        List list1;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        boolean flag9;
        list1 = null;
        flag6 = flag4;
        flag7 = flag1;
        flag8 = flag2;
        flag9 = flag3;
        if(mGetConnProviderNames != null)
            break MISSING_BLOCK_LABEL_97;
        flag6 = flag4;
        flag7 = flag1;
        flag8 = flag2;
        flag9 = flag3;
        mGetConnProviderNames = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "getConnProviderNamesLocked", new Class[] {
            java/lang/String, Integer.TYPE
        });
        flag6 = flag4;
        flag7 = flag1;
        flag8 = flag2;
        flag9 = flag3;
        if(mGetConnProviderNames == null)
            break MISSING_BLOCK_LABEL_164;
        flag6 = flag4;
        flag7 = flag1;
        flag8 = flag2;
        flag9 = flag3;
        list1 = (List)mGetConnProviderNames.invoke(null, new Object[] {
            s, Integer.valueOf(i)
        });
        if(list1 == null) goto _L4; else goto _L3
_L3:
        flag6 = flag4;
        flag7 = flag1;
        flag8 = flag2;
        flag9 = flag3;
        if(list1.size() <= 0) goto _L4; else goto _L5
_L5:
        boolean flag10;
        flag1 = true;
        flag4 = true;
        flag2 = true;
        flag3 = true;
        flag10 = true;
        flag = true;
        if(list == null) goto _L7; else goto _L6
_L6:
        flag5 = flag;
        flag6 = flag1;
        flag7 = flag2;
        flag8 = flag3;
        flag9 = flag10;
        if(list.size() <= 0)
            break MISSING_BLOCK_LABEL_336;
        flag6 = flag1;
        flag7 = flag2;
        flag8 = flag3;
        flag9 = flag10;
        s = list.iterator();
_L9:
        flag5 = flag;
        flag6 = flag1;
        flag7 = flag2;
        flag8 = flag3;
        flag9 = flag10;
        if(!s.hasNext())
            break MISSING_BLOCK_LABEL_336;
        flag6 = flag1;
        flag7 = flag2;
        flag8 = flag3;
        flag9 = flag10;
        if(list1.contains((String)s.next())) goto _L9; else goto _L8
_L8:
        flag5 = false;
        flag6 = flag5;
        flag7 = flag5;
        flag8 = flag5;
        flag9 = flag5;
        list.clear();
        flag6 = flag5;
        flag7 = flag5;
        flag8 = flag5;
        flag9 = flag5;
        s = list1.iterator();
_L10:
        flag6 = flag5;
        flag4 = flag5;
        flag7 = flag5;
        flag8 = flag5;
        flag9 = flag5;
        if(!s.hasNext())
            break; /* Loop/switch isn't completed */
        flag6 = flag5;
        flag7 = flag5;
        flag8 = flag5;
        flag9 = flag5;
        list.add((String)s.next());
        if(true) goto _L10; else goto _L7
        s;
        Log.e("whetstone.activity", Log.getStackTraceString(s));
        flag5 = flag6;
_L2:
        return flag5;
_L7:
        return flag4;
_L4:
        flag5 = flag;
        if(list == null)
            continue; /* Loop/switch isn't completed */
        flag6 = flag4;
        flag7 = flag1;
        flag8 = flag2;
        flag9 = flag3;
        list.clear();
        flag5 = flag;
        continue; /* Loop/switch isn't completed */
        s;
        Log.e("whetstone.activity", Log.getStackTraceString(s));
        flag5 = flag7;
        continue; /* Loop/switch isn't completed */
        s;
        Log.e("whetstone.activity", Log.getStackTraceString(s));
        flag5 = flag8;
        continue; /* Loop/switch isn't completed */
        s;
        Log.e("whetstone.activity", Log.getStackTraceString(s));
        flag5 = flag9;
        if(true) goto _L2; else goto _L11
_L11:
    }

    public String getPackageNamebyPid(int i)
    {
        Object obj = null;
        SparseArray sparsearray = mPidsSelfLocked;
        sparsearray;
        JVM INSTR monitorenter ;
        Object obj1 = mPidsSelfLocked.get(i);
        Object obj2;
        obj2 = obj;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_69;
        obj1 = (ApplicationInfo)ReflectionUtils.findField(Class.forName("com.android.server.am.ProcessRecord", false, mSystemServiceClassLoader), "info").get(obj1);
        obj2 = obj;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_69;
        obj2 = ((ApplicationInfo) (obj1)).packageName;
_L1:
        sparsearray;
        JVM INSTR monitorexit ;
        return ((String) (obj2));
        obj2;
        Log.e("whetstone.activity", "getPackageNamebyPid", ((Throwable) (obj2)));
        obj2 = obj;
          goto _L1
        Object obj3;
        obj3;
        throw obj3;
        obj3;
        Log.e("whetstone.activity", "getPackageNamebyPid", ((Throwable) (obj3)));
        obj3 = obj;
          goto _L1
        obj3;
        Log.e("whetstone.activity", "getPackageNamebyPid", ((Throwable) (obj3)));
        obj3 = obj;
          goto _L1
        obj3;
        Log.e("whetstone.activity", "getPackageNamebyPid", ((Throwable) (obj3)));
        obj3 = obj;
          goto _L1
    }

    public int getPartialWakeLockHoldByUid(int i)
        throws RemoteException
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        int j;
        int k;
        int l;
        flag = false;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        if(getPartialWakeLockHoldByUid == null)
        {
            Log.v("whetstone.activity", "whetstone.activity getPartialWakeLockHoldByUid == null");
            return -1;
        }
        j = ((flag) ? 1 : 0);
        k = ((flag1) ? 1 : 0);
        l = ((flag2) ? 1 : 0);
        Object obj = (Integer)getPartialWakeLockHoldByUid.invoke(PowerManagerServiceInjector, new Object[] {
            Integer.valueOf(i)
        });
        i = ((flag3) ? 1 : 0);
        if(obj == null)
            break MISSING_BLOCK_LABEL_89;
        j = ((flag) ? 1 : 0);
        k = ((flag1) ? 1 : 0);
        l = ((flag2) ? 1 : 0);
        i = ((Integer) (obj)).intValue();
        j = i;
        k = i;
        l = i;
        obj = JVM INSTR new #387 <Class StringBuilder>;
        j = i;
        k = i;
        l = i;
        ((StringBuilder) (obj)).StringBuilder();
        j = i;
        k = i;
        l = i;
        try
        {
            Log.v("whetstone.activity", ((StringBuilder) (obj)).append("whetstone.activity ret = ").append(i).toString());
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.e("whetstone.activity", "getPartialWakeLockHoldByUid", illegalargumentexception);
            i = l;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            Log.e("whetstone.activity", "getPartialWakeLockHoldByUid", illegalaccessexception);
            i = k;
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Log.e("whetstone.activity", "getPartialWakeLockHoldByUid", invocationtargetexception);
            i = j;
        }
        return i;
    }

    public ComponentPolicyInfo getPolicy(int i)
    {
        return mCm.getPolicy(i);
    }

    public volatile IPowerKeeperPolicy getPowerKeeperPolicy()
        throws RemoteException
    {
        return getPowerKeeperPolicy();
    }

    public PowerKeeperPolicy getPowerKeeperPolicy()
    {
        return mPowerKeeperPolicy;
    }

    public boolean getProcessReceiverState(int i)
    {
        byte byte0;
        int j;
        byte0 = -1;
        j = byte0;
        if(mExtraActivityManagerService == null)
            break MISSING_BLOCK_LABEL_64;
        Method method = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "getProcStateByPid", new Class[] {
            Integer.TYPE
        });
        boolean flag;
        j = byte0;
        if(method != null)
            try
            {
                j = ((Integer)method.invoke(null, new Object[] {
                    Integer.valueOf(i)
                })).intValue();
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(illegalargumentexception));
                j = byte0;
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(nosuchmethodexception));
                j = byte0;
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(illegalaccessexception));
                j = byte0;
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(invocationtargetexception));
                j = byte0;
            }
        if(j == 12)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getSystemPid()
        throws RemoteException
    {
        return Process.myPid();
    }

    public boolean getUidFrozenState(int i)
    {
        if(!UserHandle.isApp(i))
            return false;
        SparseBooleanArray sparsebooleanarray = mUidFrozenState;
        sparsebooleanarray;
        JVM INSTR monitorenter ;
        boolean flag = mUidFrozenState.get(i, false);
        sparsebooleanarray;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isAlarmAllowedLocked(int i, int j, int k)
    {
        if(D)
            Log.d("whetstone.activity", (new StringBuilder()).append("isAlarmAllowedLocked() for pid = ").append(i).append(" uid= ").append(j).append(", type =").append(k).toString());
        if(!getPowerKeeperPolicy().isAlarmAllowedLocked(i, j, k))
        {
            if(D)
                Log.d("whetstone.activity", (new StringBuilder()).append("Alarm restrict for pid = ").append(i).append(", uid = ").append(j).append(", type = ").append(k).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public boolean isBroadcastAllowedLocked(int i, int j, String s)
    {
        if(D)
            Log.d("whetstone.activity", (new StringBuilder()).append("isBroadcastAllowedLocked() for pid = ").append(i).append(" uid= ").append(j).append(", type =").append(s).toString());
        if(!getPowerKeeperPolicy().isBroadcastAllowedLocked(i, j, s))
        {
            if(D)
                Log.d("whetstone.activity", (new StringBuilder()).append("Broadcast restrict for pid = ").append(i).append(", uid = ").append(j).append(", type = ").append(s).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public boolean isProcessExecutingServices(int i)
    {
        boolean flag;
        int j;
        flag = false;
        j = ((flag) ? 1 : 0);
        if(mExtraActivityManagerService == null)
            break MISSING_BLOCK_LABEL_64;
        Method method = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "getExecutingServicesSize", new Class[] {
            Integer.TYPE
        });
        boolean flag1;
        j = ((flag) ? 1 : 0);
        if(method != null)
            try
            {
                j = ((Integer)method.invoke(null, new Object[] {
                    Integer.valueOf(i)
                })).intValue();
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(illegalargumentexception));
                j = ((flag) ? 1 : 0);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(nosuchmethodexception));
                j = ((flag) ? 1 : 0);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(illegalaccessexception));
                j = ((flag) ? 1 : 0);
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                Log.e("whetstone.activity", Log.getStackTraceString(invocationtargetexception));
                j = ((flag) ? 1 : 0);
            }
        if(j > 0)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
    }

    public void policyEnableWithModule(String s, boolean flag)
    {
        mCm.policyEnableWithModule(s, flag);
    }

    public void policyEnableWithSetter(String s, boolean flag)
    {
        mCm.policyEnableWithSetter(s, flag);
    }

    public boolean putUidFrozenState(int i, int j)
    {
        boolean flag;
        flag = false;
        if(!UserHandle.isApp(i))
            return false;
        SparseBooleanArray sparsebooleanarray = mUidFrozenState;
        sparsebooleanarray;
        JVM INSTR monitorenter ;
        SparseBooleanArray sparsebooleanarray1 = mUidFrozenState;
        if(1 == j)
            flag = true;
        sparsebooleanarray1.put(i, flag);
        sparsebooleanarray;
        JVM INSTR monitorexit ;
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    public void removeAppFromServiceControlWhitelist(String s)
    {
        WtServiceControlEntry.removeAppFromServiceControlWhitelist(s);
    }

    public void removePackagePoliciesByPackageInfos(List list)
    {
        mCm.removePackagePoliciesByPackageInfos(list);
    }

    public void removePackagesPolicies(int ai[])
    {
        mCm.removePackagesPolicies(ai);
    }

    public void removePackagesPoliciesWithModule(String s)
    {
        mCm.removePackagesPoliciesBySetter(s);
    }

    public void removePackagesPoliciesWithSetter(String s)
    {
        mCm.removePackagesPoliciesBySetter(s);
    }

    public boolean removeTaskById(int i, boolean flag)
    {
        return removeTaskByIdInternal(i, flag, true);
    }

    public boolean scheduleStopService(String s, ComponentName componentname)
    {
        return false;
    }

    public boolean scheduleTrimMemory(int i, int j)
    {
        if(mExtraActivityManagerService == null)
            break MISSING_BLOCK_LABEL_75;
        Method method = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "scheduleTrimMemory", new Class[] {
            Integer.TYPE, Integer.TYPE
        });
        if(method == null)
            break MISSING_BLOCK_LABEL_75;
        method.invoke(null, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
        return true;
        Object obj;
        obj;
        Log.e("whetstone.activity", Log.getStackTraceString(((Throwable) (obj))));
_L2:
        return false;
        obj;
        Log.e("whetstone.activity", Log.getStackTraceString(((Throwable) (obj))));
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("whetstone.activity", Log.getStackTraceString(((Throwable) (obj))));
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("whetstone.activity", Log.getStackTraceString(((Throwable) (obj))));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setPerformanceComponents(ComponentName acomponentname[])
    {
        return WhetstoneClientManager.setComponment(acomponentname);
    }

    public void setWhetstonePackageInfo(List list, boolean flag)
    {
    }

    public void updateApplicationByLockedState(String s, boolean flag)
    {
        updateApplicationByLockedStateWithUserId(s, flag, 0);
    }

    public void updateApplicationByLockedStateWithUserId(String s, boolean flag, int i)
    {
        int j;
        if(!checkCallInterfacePermission())
            return;
        if(s == null)
            return;
        if(WhetstoneClientManager.isSystemProtectImportantApp(s))
        {
            WhetstoneClientManager.updatePackageLockedStatus(s, flag, i);
            return;
        }
        j = Binder.getCallingPid();
        SparseArray sparsearray = mPidsSelfLocked;
        sparsearray;
        JVM INSTR monitorenter ;
        int k = getProcessPidByPackageNameLocked(s, i);
        if(k != -1)
            break MISSING_BLOCK_LABEL_59;
        sparsearray;
        JVM INSTR monitorexit ;
        return;
        Object obj = mPidsSelfLocked.get(k);
        if(obj == null) goto _L2; else goto _L1
_L1:
        Class class1;
        Class class2;
        Integer integer;
        Integer integer1;
        int l;
        class1 = Class.forName("com.android.server.am.ProcessRecord", false, mSystemServiceClassLoader);
        class2 = Class.forName("com.android.server.am.ProcessList", false, mSystemServiceClassLoader);
        integer = (Integer)ReflectionUtils.findField(class1, "maxAdj").get(obj);
        integer1 = (Integer)ReflectionUtils.getStaticObjectField(class2, "HEAVY_WEIGHT_APP_ADJ", Integer.TYPE);
        l = integer.intValue();
        if(!flag) goto _L4; else goto _L3
_L3:
        if(integer.intValue() > integer1.intValue())
        {
            ReflectionUtils.findField(class1, "maxAdj").set(obj, integer1);
            l = integer1.intValue();
        }
_L5:
        obj = JVM INSTR new #387 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("whetstone.activity", ((StringBuilder) (obj)).append("MaxAdj Changed: ").append(k).append(" From: ").append(integer).append(" to: ").append(l).append(" by: ").append(j).toString());
_L2:
        WhetstoneClientManager.updatePackageLockedStatus(s, flag, i);
_L6:
        sparsearray;
        JVM INSTR monitorexit ;
        return;
_L4:
        Integer integer2 = (Integer)ReflectionUtils.getStaticObjectField(class2, "UNKNOWN_ADJ", Integer.TYPE);
        ReflectionUtils.findField(class1, "maxAdj").set(obj, integer2);
        l = integer2.intValue();
          goto _L5
        s;
        Log.e("whetstone.activity", Log.getStackTraceString(s));
          goto _L6
        s;
        throw s;
          goto _L5
    }

    public void updateApplicationsMemoryThreshold(List list)
    {
        WhetstoneClientManager.updateApplicationsMemoryThreshold(list);
    }

    public void updateCheckList(List list)
    {
        mCm.updateCheckList(list);
    }

    public void updateFrameworkCommonConfig(String s)
    {
        WhetstoneClientManager.mSetting.updateFrameworkCommonConfig(s);
    }

    public void updateInputMethod(String s, boolean flag)
    {
        mCm.updateInputMethod(s, flag);
    }

    public void updateIntentsWhiteList(String s, List list, boolean flag)
    {
        mCm.updateCheckIntents(s, list, flag);
    }

    public int[] updatePackagesPolicies(List list)
    {
        return mCm.updatePackagesPolicies(list);
    }

    public void updateUserLockedAppList(List list)
    {
        if(!checkCallInterfacePermission())
        {
            return;
        } else
        {
            WhetstoneClientManager.updateUserLockedAppList(list);
            return;
        }
    }

    public void updateUserLockedAppListWithUserId(List list, int i)
    {
        WhetstoneClientManager.updateUserLockedAppList(list, i);
    }

    public static final String APP_SERVICE_NAME = "miui.whetstone";
    public static final boolean D = Log.isLoggable("whetstone.activity", 3);
    private static final int FROZEN_APP = 1;
    private static final int MSG_SYSTEM_UPDATE_CURRENT_PROCESS_PSS = 3;
    private static final int MSG_USER_CLEAR_DEAD_NATIVE_PROCESS = 2;
    private static final int MSG_USER_REMOVE_PROMOTE_LEVEL = 1;
    private static final int PER_USER_RANGE = 0x186a0;
    public static final int PROMOTE_LEVEL_HIGH = 2;
    public static final int PROMOTE_LEVEL_MIDDLE = 1;
    public static final int PROMOTE_LEVEL_NORMAL = 0;
    public static final String SERVICE = "whetstone.activity";
    private static final String TAG = "whetstone.activity";
    private static WhetstoneActivityManagerService mSelf;
    private Class PowerManagerServiceInjector;
    private Method getPartialWakeLockHoldByUid;
    private IBinder mAM;
    private WtComponentManager mCm;
    private Context mContext;
    private BroadcastReceiver mDeviceIdleChangeReceiver;
    private Class mExtraActivityManagerService;
    private Method mGetConnProviderNames;
    private PromoteLevelManagerHandler mHandler;
    private SparseArray mPidsSelfLocked;
    private PowerKeeperPolicy mPowerKeeperPolicy;
    private PowerManager mPowerManager;
    private Method mRemoveTask;
    private Method mRemoveTaskByIdLocked;
    private Method mScheduleDestroyActivities;
    private ClassLoader mSystemServiceClassLoader;
    private final SparseBooleanArray mUidFrozenState;

}
