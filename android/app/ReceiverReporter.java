// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import miui.mqsas.sdk.MQSEventManagerDelegate;
import miui.mqsas.sdk.event.BroadcastEvent;
import miui.os.Build;

// Referenced classes of package android.app:
//            ActivityThread

public class ReceiverReporter
{
    private static final class ReceiverHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 0: default 24
        //                       0 33;
               goto _L1 _L2
_L1:
            Log.w("MIUI-ReceiverReporter", "wrong message received of BRReportHandler");
_L4:
            return;
_L2:
            message = (ParceledListSlice)message.obj;
            if(message != null)
                try
                {
                    if(ReceiverReporter._2D_wrap0())
                        MQSEventManagerDelegate.getInstance().reportBroadcastEvent(message);
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    Log.e("MIUI-ReceiverReporter", "report message record error.", message);
                }
            if(true) goto _L4; else goto _L3
_L3:
        }

        static final int RECEIVER_RECORDS = 0;

        public ReceiverHandler(Looper looper)
        {
            super(looper, null);
        }
    }

    private static class ReceiverObj
    {

        public boolean equals(Object obj)
        {
            if(obj instanceof ReceiverObj)
            {
                obj = (ReceiverObj)obj;
                boolean flag;
                if(action.equals(((ReceiverObj) (obj)).action) && packageName.equals(((ReceiverObj) (obj)).packageName))
                    flag = receiver.equals(((ReceiverObj) (obj)).receiver);
                else
                    flag = false;
                return flag;
            } else
            {
                return super.equals(obj);
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("action: ").append(action).append(", packageName: ").append(packageName).append(", receiver: ").append(receiver).toString();
        }

        private String action;
        private String packageName;
        private String receiver;

        public ReceiverObj(String s, String s1, String s2)
        {
            action = s;
            packageName = s1;
            receiver = s2;
        }
    }


    static boolean _2D_wrap0()
    {
        return isSystemBootCompleted();
    }

    public ReceiverReporter()
    {
    }

    private static String currentPackageName()
    {
        if(TextUtils.isEmpty(sPackageName))
        {
            String s = ActivityThread.currentPackageName();
            if(TextUtils.isEmpty(s))
                sPackageName = "android";
            else
                sPackageName = s;
        }
        return sPackageName;
    }

    private static String currentProcessName()
    {
        if(TextUtils.isEmpty(sProcessName))
        {
            String s = ActivityThread.currentProcessName();
            if(TextUtils.isEmpty(s))
                sProcessName = "system_server";
            else
                sProcessName = s;
        }
        return sProcessName;
    }

    static Handler getReceiverHandler()
    {
        if(mReHandler != null) goto _L2; else goto _L1
_L1:
        Object obj = mObject;
        obj;
        JVM INSTR monitorenter ;
        if(mReHandler == null)
        {
            Object obj1 = JVM INSTR new #115 <Class HandlerThread>;
            ((HandlerThread) (obj1)).HandlerThread("receiver-thread");
            mReThread = ((HandlerThread) (obj1));
            mReThread.start();
            obj1 = JVM INSTR new #6   <Class ReceiverReporter$ReceiverHandler>;
            ((ReceiverHandler) (obj1)).ReceiverHandler(mReThread.getLooper());
            mReHandler = ((ReceiverHandler) (obj1));
        }
        obj;
        JVM INSTR monitorexit ;
_L2:
        return mReHandler;
        Exception exception;
        exception;
        throw exception;
    }

    private static boolean isSystemBootCompleted()
    {
        if(!sSystemBootCompleted)
            sSystemBootCompleted = "1".equals(SystemProperties.get("sys.boot_completed"));
        return sSystemBootCompleted;
    }

    static void onReceiverFinished(Intent intent, long l, long l1, long l2, String s, 
            boolean flag)
    {
        if(IS_STABLE_VERSION)
            return;
        String s1 = intent.getAction();
        intent = s1;
        if(s1 == null)
            intent = "null";
        String s2 = currentPackageName();
        if(l1 - l >= mDispatchThreshold || l2 - l1 >= mHandleThreshold)
        {
            BroadcastEvent broadcastevent = new BroadcastEvent();
            broadcastevent.setType(65);
            broadcastevent.setAction(intent);
            broadcastevent.setEnTime(l);
            broadcastevent.setDisTime(l1);
            broadcastevent.setFinTime(l2);
            broadcastevent.setBrReceiver(s);
            broadcastevent.setQuWorked(flag);
            broadcastevent.setPid(Process.myPid());
            broadcastevent.setProcessName(currentProcessName());
            broadcastevent.setPackageName(s2);
            broadcastevent.setTimeStamp(System.currentTimeMillis());
            broadcastevent.setSystem("android".equals(s2));
            int i;
            if(mIndex < 0 || mIndex > 30)
                i = 0;
            else
                i = mIndex;
            mIndex = i;
            intent = new ReceiverObj(intent, s2, s);
            if(mIndex != 0 && mIndex <= 30 && mReceiverMap.contains(intent))
            {
                i = mReceiverMap.indexOf(intent);
                RE_LIST.set(i, broadcastevent);
            } else
            {
                if(mIndex >= 30)
                {
                    s = Message.obtain();
                    s.what = 0;
                    s.obj = new ParceledListSlice((ArrayList)RE_LIST.clone());
                    getReceiverHandler().sendMessage(s);
                    RE_LIST.clear();
                    mReceiverMap.clear();
                    mIndex = 0;
                }
                mIndex++;
                mReceiverMap.add(intent);
                RE_LIST.add(broadcastevent);
            }
        }
    }

    private static final boolean DEBUG = false;
    private static final boolean IS_STABLE_VERSION;
    private static final int MAX_QUANTITY = 30;
    private static ArrayList RE_LIST = new ArrayList();
    private static final String TAG = "MIUI-ReceiverReporter";
    public static long mDispatchThreshold = SystemProperties.getLong("persist.receiver.dispatch", 3000L);
    private static long mHandleThreshold = SystemProperties.getLong("persist.receiver.handle", 2000L);
    private static int mIndex = 0;
    private static final Object mObject = new Object();
    private static volatile ReceiverHandler mReHandler = null;
    private static HandlerThread mReThread = null;
    private static ArrayList mReceiverMap = new ArrayList();
    private static String sPackageName = null;
    private static String sProcessName = null;
    private static boolean sSystemBootCompleted;

    static 
    {
        IS_STABLE_VERSION = Build.IS_STABLE_VERSION;
    }
}
