// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.content.*;
import android.os.*;
import android.speech.tts.TtsEngines;
import android.util.Slog;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.os.BackgroundThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.miui.whetstone:
//            WhetstoneSleepCleanConfig, WhetstoneWakeUpManager

public class WhetstoneSleepModeController
{
    class MyHandler extends Handler
    {

        public void dispose()
        {
            removeMessages(0);
        }

        public void handleMessage(Message message)
        {
            if(message == null)
                return;
            message.what;
            JVM INSTR tableswitch 0 0: default 28
        //                       0 29;
               goto _L1 _L2
_L1:
            return;
_L2:
            WhetstoneSleepModeController._2D_wrap1(WhetstoneSleepModeController.this, message.arg1, message.arg2, WhetstoneSleepModeController._2D_get2(WhetstoneSleepModeController.this), false);
            if(true) goto _L1; else goto _L3
_L3:
        }

        final WhetstoneSleepModeController this$0;

        MyHandler(Looper looper)
        {
            this$0 = WhetstoneSleepModeController.this;
            super(looper);
        }
    }


    static int _2D_get0(WhetstoneSleepModeController whetstonesleepmodecontroller)
    {
        return whetstonesleepmodecontroller.mCurState;
    }

    static MyHandler _2D_get1(WhetstoneSleepModeController whetstonesleepmodecontroller)
    {
        return whetstonesleepmodecontroller.mHandler;
    }

    static int _2D_get2(WhetstoneSleepModeController whetstonesleepmodecontroller)
    {
        return whetstonesleepmodecontroller.mIsCharging;
    }

    static boolean _2D_get3(WhetstoneSleepModeController whetstonesleepmodecontroller)
    {
        return whetstonesleepmodecontroller.mIsProcessControlEnable;
    }

    static WhetstoneSleepCleanConfig _2D_get4(WhetstoneSleepModeController whetstonesleepmodecontroller)
    {
        return whetstonesleepmodecontroller.mSleepCleanConfig;
    }

    static WhetstoneWakeUpManager _2D_get5(WhetstoneSleepModeController whetstonesleepmodecontroller)
    {
        return whetstonesleepmodecontroller.mWakeUpManager;
    }

    static int _2D_set0(WhetstoneSleepModeController whetstonesleepmodecontroller, int i)
    {
        whetstonesleepmodecontroller.mIsCharging = i;
        return i;
    }

    static boolean _2D_set1(WhetstoneSleepModeController whetstonesleepmodecontroller, boolean flag)
    {
        whetstonesleepmodecontroller.mIsProcessControlEnable = flag;
        return flag;
    }

    static boolean _2D_set2(WhetstoneSleepModeController whetstonesleepmodecontroller, boolean flag)
    {
        whetstonesleepmodecontroller.mSleepABProcControlEnable = flag;
        return flag;
    }

    static boolean _2D_set3(WhetstoneSleepModeController whetstonesleepmodecontroller, boolean flag)
    {
        whetstonesleepmodecontroller.mSleepABTestEnable = flag;
        return flag;
    }

    static int _2D_set4(WhetstoneSleepModeController whetstonesleepmodecontroller, int i)
    {
        whetstonesleepmodecontroller.mSleepABTestType = i;
        return i;
    }

    static String _2D_wrap0(WhetstoneSleepModeController whetstonesleepmodecontroller, int i)
    {
        return whetstonesleepmodecontroller.getStateString(i);
    }

    static void _2D_wrap1(WhetstoneSleepModeController whetstonesleepmodecontroller, int i, int j, int k, boolean flag)
    {
        whetstonesleepmodecontroller.handleSleepModeChanged(i, j, k, flag);
    }

    private WhetstoneSleepModeController(Context context)
    {
        mHandler = null;
        mSleepModeReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("onReceive action : ").append(intent.getAction()).toString());
                if(intent != null && "action_sleep_state_changed".equals(intent.getAction()))
                {
                    int i = intent.getIntExtra("pre_state", 0);
                    int j = intent.getIntExtra("cur_state", 0);
                    WhetstoneSleepModeController._2D_set0(WhetstoneSleepModeController.this, intent.getIntExtra("charging", 1));
                    WhetstoneSleepModeController._2D_set4(WhetstoneSleepModeController.this, intent.getIntExtra("ab_type", 0));
                    WhetstoneSleepModeController._2D_set3(WhetstoneSleepModeController.this, intent.getBooleanExtra("ab_enable", false));
                    WhetstoneSleepModeController._2D_set2(WhetstoneSleepModeController.this, intent.getBooleanExtra("ab_proc_enable", false));
                    Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("receive sleep changed, pre = ").append(WhetstoneSleepModeController._2D_wrap0(WhetstoneSleepModeController.this, i)).append(", cur = ").append(WhetstoneSleepModeController._2D_wrap0(WhetstoneSleepModeController.this, j)).append(", charging = ").append(WhetstoneSleepModeController._2D_get2(WhetstoneSleepModeController.this)).toString());
                    context1 = new Message();
                    context1.what = 0;
                    context1.arg1 = i;
                    context1.arg2 = j;
                    WhetstoneSleepModeController._2D_get1(WhetstoneSleepModeController.this).sendMessage(context1);
                }
                if(intent != null && "sleep_control_cloud".equals(intent.getAction()))
                {
                    WhetstoneSleepModeController._2D_set1(WhetstoneSleepModeController.this, intent.getBooleanExtra("enable_process_control", false));
                    ArrayList arraylist = intent.getStringArrayListExtra("process_white_list");
                    ArrayList arraylist1 = intent.getStringArrayListExtra("disable_rtc_list");
                    ArrayList arraylist2 = intent.getStringArrayListExtra("clean_white_list");
                    context1 = intent.getStringArrayListExtra("start_white_list");
                    Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("Process Control Enable : ").append(WhetstoneSleepModeController._2D_get3(WhetstoneSleepModeController.this)).toString());
                    WhetstoneSleepModeController._2D_get4(WhetstoneSleepModeController.this).addProtectedProcessList(arraylist);
                    setSleepCleanConfig(arraylist2, context1);
                    WhetstoneSleepModeController._2D_get5(WhetstoneSleepModeController.this).setDisableRTCList(arraylist1);
                }
                if(intent != null && "android.intent.action.USER_PRESENT".equals(intent.getAction()) && WhetstoneSleepModeController._2D_get0(WhetstoneSleepModeController.this) != 0)
                {
                    context1 = new Message();
                    context1.what = 0;
                    context1.arg1 = WhetstoneSleepModeController._2D_get0(WhetstoneSleepModeController.this);
                    context1.arg2 = 0;
                    WhetstoneSleepModeController._2D_get1(WhetstoneSleepModeController.this).sendMessage(context1);
                }
                if(intent != null && "sleep_wakeup_dump".equals(intent.getAction()))
                {
                    Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("Process Control Enable : ").append(WhetstoneSleepModeController._2D_get3(WhetstoneSleepModeController.this)).toString());
                    WhetstoneSleepModeController._2D_get5(WhetstoneSleepModeController.this).dump();
                    WhetstoneSleepModeController._2D_get4(WhetstoneSleepModeController.this).dump();
                }
            }

            final WhetstoneSleepModeController this$0;

            
            {
                this$0 = WhetstoneSleepModeController.this;
                super();
            }
        }
;
        mContext = context;
        mCurState = 0;
        mHandler = new MyHandler(BackgroundThread.getHandler().getLooper());
        registerSleepModeChangeReceiver();
        mIsProcessControlEnable = false;
        mSleepCleanConfig = new WhetstoneSleepCleanConfig();
        mWakeUpManager = WhetstoneWakeUpManager.getInstance(mContext);
    }

    public static WhetstoneSleepModeController getInstance(Context context)
    {
        if(mSleepModeController != null) goto _L2; else goto _L1
_L1:
        com/miui/whetstone/WhetstoneSleepModeController;
        JVM INSTR monitorenter ;
        if(mSleepModeController == null)
        {
            WhetstoneSleepModeController whetstonesleepmodecontroller = JVM INSTR new #2   <Class WhetstoneSleepModeController>;
            whetstonesleepmodecontroller.WhetstoneSleepModeController(context);
            mSleepModeController = whetstonesleepmodecontroller;
        }
        com/miui/whetstone/WhetstoneSleepModeController;
        JVM INSTR monitorexit ;
_L2:
        return mSleepModeController;
        context;
        throw context;
    }

    private String getStateString(int i)
    {
        switch(i)
        {
        default:
            return "unknown";

        case 0: // '\0'
            return "no sleep";

        case 1: // '\001'
            return "light1";

        case 3: // '\003'
            return "light2";

        case 2: // '\002'
            return "deep";
        }
    }

    private void handleSleepModeChanged(int i, int j, int k, boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCurState != j)
            break MISSING_BLOCK_LABEL_51;
        StringBuilder stringbuilder = JVM INSTR new #222 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.d("WhetstoneSleepModeController", stringbuilder.append("already in state:").append(j).toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mCurState != i)
        {
            StringBuilder stringbuilder1 = JVM INSTR new #222 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Slog.e("WhetstoneSleepModeController", stringbuilder1.append("sleep state not sync: mCurState=").append(getStateString(mCurState)).append(",preState=").append(getStateString(i)).toString());
        }
        StringBuilder stringbuilder2 = JVM INSTR new #222 <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        Slog.d("WhetstoneSleepModeController", stringbuilder2.append("state change: ").append(getStateString(mCurState)).append("->").append(getStateString(j)).toString());
        mCurState = j;
        mSleepCleanConfig.setSleepState(j);
        j;
        JVM INSTR tableswitch 0 3: default 204
    //                   0 208
    //                   1 227
    //                   2 204
    //                   3 204;
           goto _L1 _L2 _L3 _L1 _L1
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        mWakeUpManager.onSleepModeEnter(false);
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L3:
        if(k != 0) goto _L1; else goto _L4
_L4:
        mWakeUpManager.onSleepModeEnter(true);
          goto _L1
    }

    private void registerSleepModeChangeReceiver()
    {
        if(DEBUG)
            Slog.d("WhetstoneSleepModeController", "register sleep mode receiver");
        IntentFilter intentfilter = new IntentFilter("action_sleep_state_changed");
        intentfilter.addAction("sleep_control_cloud");
        intentfilter.addAction("sleep_wakeup_dump");
        intentfilter.addAction("android.intent.action.USER_PRESENT");
        mContext.registerReceiver(mSleepModeReceiver, intentfilter);
    }

    private void sendExitSleepModeBroadCast(int i)
    {
        Intent intent = new Intent("exit_sleep_mode_action");
        intent.putExtra("exit_sleep_mode_reason", i);
        mContext.sendStickyBroadcastAsUser(intent, UserHandle.CURRENT);
    }

    public boolean checkIfExitSleepMode(int i)
    {
        if(mCurState != 0)
        {
            Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("exit the sleep mode reason is ").append(i).toString());
            handleSleepModeChanged(mCurState, 0, mIsCharging, false);
            sendExitSleepModeBroadCast(i);
            return true;
        } else
        {
            return false;
        }
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("=========== dump SleepModeController ============");
    }

    public void exitSleepModeByActivity()
    {
        if(mCurState != 0)
        {
            Slog.d("WhetstoneSleepModeController", "exitSleepModeByActivity");
            mCurState = 0;
            WhetstoneWakeUpManager.getInstance(mContext).onSleepModeEnter(false);
            sendExitSleepModeBroadCast(1);
        }
    }

    public boolean isForbidProcessStartBySleepMode(String s, String s1, int i)
    {
label0:
        {
            if(!mIsProcessControlEnable)
                return false;
            if(!s.startsWith("com.google.android.marvin.talkback"))
            {
                WhetstoneSleepCleanConfig whetstonesleepcleanconfig = mSleepCleanConfig;
                boolean flag;
                if((i & 1) != 0)
                    flag = true;
                else
                    flag = false;
                if(!whetstonesleepcleanconfig.checkIfProcessStartAllow(s1, s, flag))
                    break label0;
            }
            Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("whtetstone process start is allow by sleep mode : ").append(s).append(", processName : ").append(s1).toString());
            return false;
        }
        Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("whtetstone process start is forbid by sleep mode : ").append(s).append(", processName : ").append(s1).toString());
        return true;
    }

    public boolean isInCharging()
    {
        boolean flag = true;
        if(mIsCharging != 1)
            flag = false;
        return flag;
    }

    public boolean isInSleep()
    {
        return mCurState != 0;
    }

    public boolean isProcessControlEnable()
    {
        return mIsProcessControlEnable;
    }

    public boolean isSleepABProcControlEnable()
    {
        return mSleepABProcControlEnable;
    }

    public boolean isSleepABTestEnableByType(int i)
    {
        if(!mSleepABTestEnable)
            return false;
        return i == mSleepABTestType;
    }

    public boolean isSleepModeEnableByType(int i)
    {
        if(!mSleepABTestEnable)
            return false;
        return i == mSleepABTestType;
    }

    public void setSleepCleanConfig(ArrayList arraylist, ArrayList arraylist1)
    {
        if(arraylist != null)
        {
            String s;
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); mSleepCleanConfig.addPackageControlCleanInfo2Config(s, 2))
                s = (String)iterator.next();

            Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("Sleep Clean WhiteList : ").append(arraylist.toString()).toString());
        }
        if(arraylist1 != null)
        {
            for(Iterator iterator1 = arraylist1.iterator(); iterator1.hasNext(); mSleepCleanConfig.addPackageControlStartInfo2Config(arraylist, 2))
                arraylist = (String)iterator1.next();

            Slog.d("WhetstoneSleepModeController", (new StringBuilder()).append("Sleep Start WhiteList : ").append(arraylist1.toString()).toString());
        }
        arraylist = (AccessibilityManager)mContext.getSystemService("accessibility");
        if(arraylist != null && arraylist.isEnabled() && arraylist.isTouchExplorationEnabled())
        {
            arraylist = (new TtsEngines(mContext)).getDefaultEngine();
            mSleepCleanConfig.addPackageControlInfo2Config(arraylist, 2, 2);
            Slog.i("WhetstoneSleepModeController", (new StringBuilder()).append("TtsEngines : ").append(arraylist).toString());
        }
    }

    private static final String ACTION_SLEEP_CONTROL_CLOUD = "sleep_control_cloud";
    private static final String ACTION_SLEEP_STATE_CHANGED = "action_sleep_state_changed";
    private static final String ACTION_SLEEP_WAKEUP_DUMP = "sleep_wakeup_dump";
    private static final boolean DEBUG;
    public static final String EXIT_SLEEP_MODE_ACTION = "exit_sleep_mode_action";
    public static final String EXIT_SLEEP_MODE_REASON = "exit_sleep_mode_reason";
    public static final int EXIT_SLEEP_MODE_REASON_TYPE = 1;
    private static final String GOOGLE_TALKBACK_PACKAGE = "com.google.android.marvin.talkback";
    public static final String INTENT_EXTRA_ABTEST_ENABLE = "ab_enable";
    public static final String INTENT_EXTRA_ABTEST_PROC_ENABLE = "ab_proc_enable";
    public static final String INTENT_EXTRA_ABTEST_TYPE = "ab_type";
    public static final String INTENT_EXTRA_CHARGING = "charging";
    public static final String INTENT_EXTRA_CLEAN_WHITE_LIST = "clean_white_list";
    public static final String INTENT_EXTRA_CUR_STATE = "cur_state";
    public static final String INTENT_EXTRA_DISABLE_RTC_LIST = "disable_rtc_list";
    public static final String INTENT_EXTRA_PRE_STATE = "pre_state";
    public static final String INTENT_EXTRA_PROCESS_CONTROL_ENABLE = "enable_process_control";
    public static final String INTENT_EXTRA_PROCESS_WHITE_LIST = "process_white_list";
    public static final String INTENT_EXTRA_START_WHITE_LIST = "start_white_list";
    private static final int MSG_MODE_CHANGED = 0;
    public static final int REASON_SLEEP_EXIT_BY_ACTIVITY = 1;
    private static final int STATE_DEEP_SLEEP = 2;
    public static final int STATE_IS_CHARGING = 1;
    private static final int STATE_LIGHT_SLEEP1 = 1;
    private static final int STATE_LIGHT_SLEEP2 = 3;
    public static final int STATE_NO_CHARGING = 0;
    private static final int STATE_NO_SLEEP = 0;
    private static final String TAG = "WhetstoneSleepModeController";
    public static final String THIRD_PARTY_RTC_WAKEUP_RECORD_PROP = "persist.sys.rtc.wakeup_record";
    public static final int TYPE_BATTERY_COMPARE_TEST = 1;
    public static final int TYPE_DEFAULT_COMPARE_TEST = 0;
    public static final int TYPE_MODEL_COMPARE_TEST = 2;
    public static final int TYPE_STRATEGY_COMPARE_TEST = 3;
    private static volatile WhetstoneSleepModeController mSleepModeController;
    private Context mContext;
    private int mCurState;
    private MyHandler mHandler;
    private int mIsCharging;
    private boolean mIsProcessControlEnable;
    private final Object mLock = new Object();
    private boolean mSleepABProcControlEnable;
    private boolean mSleepABTestEnable;
    private int mSleepABTestType;
    private WhetstoneSleepCleanConfig mSleepCleanConfig;
    BroadcastReceiver mSleepModeReceiver;
    private WhetstoneWakeUpManager mWakeUpManager;

    static 
    {
        DEBUG = Build.IS_DEBUGGABLE;
    }
}
