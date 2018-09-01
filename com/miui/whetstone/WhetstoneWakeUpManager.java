// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.app.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.*;
import android.util.Slog;
import com.android.internal.os.BackgroundThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.miui.whetstone:
//            WhetstoneWakeUpRecord, WhetstoneSleepModeController

public class WhetstoneWakeUpManager
{
    class WakeUpHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 29;
               goto _L1 _L2 _L2
_L1:
            return;
_L2:
            if(!WhetstoneSleepModeController.getInstance(WhetstoneWakeUpManager._2D_get0(WhetstoneWakeUpManager.this)).isInSleep())
                return;
            int i = message.arg1;
            try
            {
                Iterator iterator = ActivityManagerNative.getDefault().getRunningAppProcesses().iterator();
                do
                {
                    do
                    {
                        if(!iterator.hasNext())
                            continue; /* Loop/switch isn't completed */
                        message = (android.app.ActivityManager.RunningAppProcessInfo)iterator.next();
                    } while(((android.app.ActivityManager.RunningAppProcessInfo) (message)).uid != i);
                    StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    Slog.d("WhetstoneWakeUpManager", stringbuilder.append("kill RTC wake up app uid : ").append(i).toString());
                    ActivityManagerNative.getDefault().killPids(new int[] {
                        ((android.app.ActivityManager.RunningAppProcessInfo) (message)).pid
                    }, "whetstone : sleep clean", true);
                } while(true);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Slog.d("WhetstoneWakeUpManager", (new StringBuilder()).append("WakeUpHandler killRtc : ").append(message.getMessage()).toString());
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        final WhetstoneWakeUpManager this$0;

        public WakeUpHandler(Looper looper)
        {
            this$0 = WhetstoneWakeUpManager.this;
            super(looper);
        }
    }


    static Context _2D_get0(WhetstoneWakeUpManager whetstonewakeupmanager)
    {
        return whetstonewakeupmanager.mContext;
    }

    public WhetstoneWakeUpManager(Context context)
    {
        mWakeUpInfoList = new ConcurrentHashMap();
        mContext = context;
        mAlarmManager = (AlarmManager)mContext.getSystemService("alarm");
        mHandler = new WakeUpHandler(BackgroundThread.getHandler().getLooper());
        mInstance = this;
        mDisableApp = new ArrayList();
    }

    public static WhetstoneWakeUpManager getInstance(Context context)
    {
        if(mInstance != null) goto _L2; else goto _L1
_L1:
        com/miui/whetstone/WhetstoneWakeUpManager;
        JVM INSTR monitorenter ;
        if(mInstance == null)
        {
            WhetstoneWakeUpManager whetstonewakeupmanager = JVM INSTR new #2   <Class WhetstoneWakeUpManager>;
            whetstonewakeupmanager.WhetstoneWakeUpManager(context);
            mInstance = whetstonewakeupmanager;
        }
        com/miui/whetstone/WhetstoneWakeUpManager;
        JVM INSTR monitorexit ;
_L2:
        return mInstance;
        context;
        throw context;
    }

    private boolean isBelongToBlackList(String s)
    {
        if(s == null)
            return false;
        for(Iterator iterator = mDisableApp.iterator(); iterator.hasNext();)
            if(s.equals((String)iterator.next()))
                return true;

        return false;
    }

    public int checkIfAppBeAllowedStartForWakeUpControl(int i, String s, PendingIntent pendingintent)
    {
        int j;
        boolean flag;
        if(s == null)
        {
            Slog.d("WhetstoneWakeUpManager", "the packageName is null, so reject the start");
            return 0;
        }
        if(isBelongToBlackList(s))
        {
            Slog.d("WhetstoneWakeUpManager", (new StringBuilder()).append("the package belong to black list, so reject the start ").append(s).toString());
            return 1;
        }
        j = -1;
        flag = false;
        ConcurrentHashMap concurrenthashmap = mWakeUpInfoList;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        int k = j;
        if(mWakeUpInfoList == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = mWakeUpInfoList.entrySet().iterator();
_L6:
        k = j;
        Iterator iterator1;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        iterator1 = ((List)((java.util.Map.Entry)iterator.next()).getValue()).iterator();
_L4:
        boolean flag1;
        flag1 = flag;
        k = j;
        Object obj;
        if(!iterator1.hasNext())
            break MISSING_BLOCK_LABEL_185;
        obj = (WhetstoneWakeUpRecord)iterator1.next();
        if(obj == null) goto _L4; else goto _L3
_L3:
        if(!s.equals(((WhetstoneWakeUpRecord) (obj)).packageName)) goto _L4; else goto _L5
_L5:
        k = ((WhetstoneWakeUpRecord) (obj)).uid;
        flag1 = true;
        flag = flag1;
        j = k;
        if(!flag1) goto _L6; else goto _L2
_L2:
        obj = (List)mWakeUpInfoList.get(Integer.valueOf(k));
        if(obj != null || k <= 0)
            break MISSING_BLOCK_LABEL_258;
        s = JVM INSTR new #130 <Class StringBuilder>;
        s.StringBuilder();
        Slog.d("WhetstoneWakeUpManager", s.append("dont get the rtc wakeUp info for uid ").append(k).toString());
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return 0;
        if(k <= 0)
            break MISSING_BLOCK_LABEL_526;
        iterator1 = ((List) (obj)).iterator();
_L8:
        if(!iterator1.hasNext())
            break MISSING_BLOCK_LABEL_526;
        obj = (WhetstoneWakeUpRecord)iterator1.next();
        if(obj == null || pendingintent == null)
            continue; /* Loop/switch isn't completed */
        if(!pendingintent.equals(((WhetstoneWakeUpRecord) (obj)).intent))
            continue; /* Loop/switch isn't completed */
        ((WhetstoneWakeUpRecord) (obj)).increaseWakeUpCount();
        if(i != 1000)
            break MISSING_BLOCK_LABEL_332;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return 0;
        if(((WhetstoneWakeUpRecord) (obj)).getWakeUpCount() <= 3)
            break MISSING_BLOCK_LABEL_360;
        mAlarmManager.cancel(pendingintent);
        obj.status = true;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return 1;
        s = Message.obtain();
        s.what = 2;
        s.arg1 = i;
        mHandler.sendMessageDelayed(s, 5000L);
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return 2;
        if(pendingintent != null || obj == null) goto _L8; else goto _L7
_L7:
        if(!s.equals(((WhetstoneWakeUpRecord) (obj)).packageName)) goto _L8; else goto _L9
_L9:
        ((WhetstoneWakeUpRecord) (obj)).increaseWakeUpCount();
        if(((WhetstoneWakeUpRecord) (obj)).getWakeUpCount() <= 3)
            break MISSING_BLOCK_LABEL_464;
        pendingintent = JVM INSTR new #130 <Class StringBuilder>;
        pendingintent.StringBuilder();
        Slog.d("WhetstoneWakeUpManager", pendingintent.append("the package excess max wake up count : ").append(s).toString());
        obj.status = true;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return 1;
        pendingintent = JVM INSTR new #130 <Class StringBuilder>;
        pendingintent.StringBuilder();
        Slog.d("WhetstoneWakeUpManager", pendingintent.append("the package allow to start in sleep mode : ").append(s).toString());
        s = Message.obtain();
        s.what = 2;
        s.arg1 = ((WhetstoneWakeUpRecord) (obj)).uid;
        mHandler.sendMessageDelayed(s, 5000L);
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return 2;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return 0;
        s;
        throw s;
    }

    public boolean checkIfAppBelongToRtcWakeUp(int i, String s)
    {
        boolean flag;
        if(s == null)
        {
            Slog.d("WhetstoneWakeUpManager", "the packageName is null, so reject the start");
            return false;
        }
        if(isBelongToBlackList(s))
        {
            Slog.d("WhetstoneWakeUpManager", (new StringBuilder()).append("the package belong to black list, so reject the start ").append(s).toString());
            return false;
        }
        i = -1;
        flag = false;
        ConcurrentHashMap concurrenthashmap = mWakeUpInfoList;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        int j = i;
        if(mWakeUpInfoList == null) goto _L2; else goto _L1
_L1:
        Object obj = mWakeUpInfoList.entrySet().iterator();
_L6:
        j = i;
        Iterator iterator;
        if(!((Iterator) (obj)).hasNext())
            break; /* Loop/switch isn't completed */
        iterator = ((List)((java.util.Map.Entry)((Iterator) (obj)).next()).getValue()).iterator();
_L4:
        boolean flag1;
        flag1 = flag;
        j = i;
        Object obj1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_179;
        obj1 = (WhetstoneWakeUpRecord)iterator.next();
        if(obj1 == null) goto _L4; else goto _L3
_L3:
        if(!s.equals(((WhetstoneWakeUpRecord) (obj1)).packageName)) goto _L4; else goto _L5
_L5:
        j = ((WhetstoneWakeUpRecord) (obj1)).uid;
        flag1 = true;
        flag = flag1;
        i = j;
        if(!flag1) goto _L6; else goto _L2
_L2:
        obj = (List)mWakeUpInfoList.get(Integer.valueOf(j));
        if(obj != null || j <= 0)
            break MISSING_BLOCK_LABEL_250;
        s = JVM INSTR new #130 <Class StringBuilder>;
        s.StringBuilder();
        Slog.d("WhetstoneWakeUpManager", s.append("dont get the rtc wakeUp info for uid ").append(j).toString());
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return false;
        if(j <= 0)
            break MISSING_BLOCK_LABEL_312;
        obj1 = ((List) (obj)).iterator();
_L8:
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break MISSING_BLOCK_LABEL_312;
            obj = (WhetstoneWakeUpRecord)((Iterator) (obj1)).next();
        } while(obj == null);
        boolean flag2 = s.equals(((WhetstoneWakeUpRecord) (obj)).packageName);
        if(!flag2) goto _L8; else goto _L7
_L7:
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return true;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return false;
        s;
        throw s;
    }

    public void dump()
    {
        Object obj;
        Slog.d("WhetstoneWakeUpManager", "===========dump the smart power wake up package info start");
        obj = "";
        ConcurrentHashMap concurrenthashmap = mWakeUpInfoList;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        Iterator iterator;
        if(mWakeUpInfoList == null)
            break MISSING_BLOCK_LABEL_217;
        iterator = mWakeUpInfoList.entrySet().iterator();
_L2:
        Iterator iterator1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_217;
        iterator1 = ((List)((java.util.Map.Entry)iterator.next()).getValue()).iterator();
        Object obj1 = obj;
_L4:
        obj = obj1;
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        WhetstoneWakeUpRecord whetstonewakeuprecord = (WhetstoneWakeUpRecord)iterator1.next();
        if(whetstonewakeuprecord == null) goto _L4; else goto _L3
_L3:
        String as[];
        obj = JVM INSTR new #130 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.d("WhetstoneWakeUpManager", ((StringBuilder) (obj)).append("the wake up record is :").append(whetstonewakeuprecord.toString()).toString());
        as = mContext.getPackageManager().getPackagesForUid(whetstonewakeuprecord.uid);
        obj = obj1;
        if(as == null)
            break MISSING_BLOCK_LABEL_174;
        obj = obj1;
        if(as.length > 0)
            obj = as[0];
        obj1 = JVM INSTR new #130 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Slog.d("WhetstoneWakeUpManager", ((StringBuilder) (obj1)).append("the package name is : ").append(((String) (obj))).toString());
        obj1 = obj;
          goto _L4
        Exception exception;
        exception;
        throw exception;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        Slog.d("WhetstoneWakeUpManager", "===========dump the smart power wake up package info end");
        return;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("===========dump the smart power wake up package info start");
        as = "";
        ConcurrentHashMap concurrenthashmap = mWakeUpInfoList;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        Iterator iterator;
        if(mWakeUpInfoList == null)
            break MISSING_BLOCK_LABEL_207;
        iterator = mWakeUpInfoList.entrySet().iterator();
_L2:
        Iterator iterator1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_207;
        iterator1 = ((List)((java.util.Map.Entry)iterator.next()).getValue()).iterator();
        filedescriptor = as;
_L4:
        as = filedescriptor;
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        WhetstoneWakeUpRecord whetstonewakeuprecord = (WhetstoneWakeUpRecord)iterator1.next();
        if(whetstonewakeuprecord == null) goto _L4; else goto _L3
_L3:
        String as1[];
        as = JVM INSTR new #130 <Class StringBuilder>;
        as.StringBuilder();
        printwriter.println(as.append("the wake up record is :").append(whetstonewakeuprecord.toString()).toString());
        as1 = mContext.getPackageManager().getPackagesForUid(whetstonewakeuprecord.uid);
        as = filedescriptor;
        if(as1 == null)
            break MISSING_BLOCK_LABEL_171;
        as = filedescriptor;
        if(as1.length > 0)
            as = as1[0];
        filedescriptor = JVM INSTR new #130 <Class StringBuilder>;
        filedescriptor.StringBuilder();
        printwriter.println(filedescriptor.append("the package name is : ").append(as).toString());
        filedescriptor = as;
          goto _L4
        filedescriptor;
        throw filedescriptor;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        printwriter.println("===========dump the smart power wake up package info end");
        return;
    }

    public void onSleepModeEnter(boolean flag)
    {
        ConcurrentHashMap concurrenthashmap = mWakeUpInfoList;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        if(mWakeUpInfoList == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = mWakeUpInfoList.entrySet().iterator();
_L5:
        Iterator iterator1;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        iterator1 = ((List)((java.util.Map.Entry)iterator.next()).getValue()).iterator();
_L4:
        WhetstoneWakeUpRecord whetstonewakeuprecord;
        do
        {
            if(!iterator1.hasNext())
                break; /* Loop/switch isn't completed */
            whetstonewakeuprecord = (WhetstoneWakeUpRecord)iterator1.next();
        } while(whetstonewakeuprecord == null);
        whetstonewakeuprecord.wakeup_count = 0;
        whetstonewakeuprecord.status = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L5; else goto _L2
        Exception exception;
        exception;
        throw exception;
_L2:
        concurrenthashmap;
        JVM INSTR monitorexit ;
    }

    public void recordRTCWakeupInfo(int i, PendingIntent pendingintent, boolean flag)
    {
        if(pendingintent == null || i == 1000)
        {
            Slog.e("WhetstoneWakeUpManager", "error:record rtc wakeUp info pending intent is null");
            return;
        }
        String as[] = mContext.getPackageManager().getPackagesForUid(i);
        String s = "";
        pendingintent = s;
        if(as != null)
        {
            pendingintent = s;
            if(as.length > 0)
                pendingintent = as[0];
        }
        ConcurrentHashMap concurrenthashmap = mWakeUpInfoList;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        Object obj = (List)mWakeUpInfoList.get(Integer.valueOf(i));
        if(!flag)
            break MISSING_BLOCK_LABEL_145;
        if(obj != null)
            break MISSING_BLOCK_LABEL_219;
        obj = JVM INSTR new #85  <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        WhetstoneWakeUpRecord whetstonewakeuprecord = JVM INSTR new #158 <Class WhetstoneWakeUpRecord>;
        whetstonewakeuprecord.WhetstoneWakeUpRecord(i, null, pendingintent);
        ((List) (obj)).add(whetstonewakeuprecord);
        mWakeUpInfoList.put(Integer.valueOf(i), obj);
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return;
        if(obj != null)
            break MISSING_BLOCK_LABEL_163;
        Slog.e("WhetstoneWakeUpManager", "remove the wakeUp info, but wakeUp info is null");
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return;
        Iterator iterator = ((Iterable) (obj)).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            pendingintent = (WhetstoneWakeUpRecord)iterator.next();
            if(((WhetstoneWakeUpRecord) (pendingintent)).uid == i)
                ((List) (obj)).remove(pendingintent);
        } while(true);
        break MISSING_BLOCK_LABEL_219;
        pendingintent;
        throw pendingintent;
        concurrenthashmap;
        JVM INSTR monitorexit ;
    }

    public void setDisableRTCList(ArrayList arraylist)
    {
        if(arraylist == null)
        {
            Slog.d("WhetstoneWakeUpManager", "disableRtcList == null, return");
            return;
        } else
        {
            Slog.d("WhetstoneWakeUpManager", (new StringBuilder()).append("Disable RTC List : ").append(arraylist.toString()).toString());
            mDisableApp = arraylist;
            return;
        }
    }

    private static final int DELAY_STOP_RTC_APP_TIME = 5000;
    public static final int MAX_WAKE_UP_COUNT = 3;
    private static final int MESSAGE_KILL_RTC_WAKE_UP_APP = 2;
    private static final int MESSAGE_STOP_RTC_APP = 1;
    private static final String TAG = "WhetstoneWakeUpManager";
    private static final String THIRD_PARTY_RTC_WAKEUP_RECORD_PROP = "persist.sys.rtc.wakeup_record";
    private static volatile WhetstoneWakeUpManager mInstance;
    private AlarmManager mAlarmManager;
    private Context mContext;
    private ArrayList mDisableApp;
    private Handler mHandler;
    private ConcurrentHashMap mWakeUpInfoList;
}
