// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.hardware.input.InputManager;
import android.os.*;
import android.util.*;
import android.view.*;
import com.android.internal.content.ReferrerIntent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.app:
//            ActivityManager, Application, Activity, ActivityThread, 
//            IActivityManager, IApplicationThread, IAppTask, UiAutomation, 
//            IInstrumentationWatcher, IUiAutomationConnection

public class Instrumentation
{
    class _cls1ContextMenuRunnable
        implements Runnable
    {

        public void run()
        {
            returnValue = activity.getWindow().performContextMenuIdentifierAction(identifier, flags);
        }

        private final Activity activity;
        private final int flags;
        private final int identifier;
        boolean returnValue;
        final Instrumentation this$0;

        public _cls1ContextMenuRunnable(Activity activity1, int i, int j)
        {
            this$0 = Instrumentation.this;
            super();
            activity = activity1;
            identifier = i;
            flags = j;
        }
    }

    class _cls1MenuRunnable
        implements Runnable
    {

        public void run()
        {
            returnValue = activity.getWindow().performPanelIdentifierAction(0, identifier, flags);
        }

        private final Activity activity;
        private final int flags;
        private final int identifier;
        boolean returnValue;
        final Instrumentation this$0;

        public _cls1MenuRunnable(Activity activity1, int i, int j)
        {
            this$0 = Instrumentation.this;
            super();
            activity = activity1;
            identifier = i;
            flags = j;
        }
    }

    private final class ActivityGoing
        implements android.os.MessageQueue.IdleHandler
    {

        public final boolean queueIdle()
        {
            Object obj = Instrumentation._2D_get1(Instrumentation.this);
            obj;
            JVM INSTR monitorenter ;
            Instrumentation._2D_get2(Instrumentation.this).remove(mWaiter);
            Instrumentation._2D_get1(Instrumentation.this).notifyAll();
            obj;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            throw exception;
        }

        private final ActivityWaiter mWaiter;
        final Instrumentation this$0;

        public ActivityGoing(ActivityWaiter activitywaiter)
        {
            this$0 = Instrumentation.this;
            super();
            mWaiter = activitywaiter;
        }
    }

    public static class ActivityMonitor
    {

        public final IntentFilter getFilter()
        {
            return mWhich;
        }

        public final int getHits()
        {
            return mHits;
        }

        public final Activity getLastActivity()
        {
            return mLastActivity;
        }

        public final ActivityResult getResult()
        {
            return mResult;
        }

        final boolean ignoreMatchingSpecificIntents()
        {
            return mIgnoreMatchingSpecificIntents;
        }

        public final boolean isBlocking()
        {
            return mBlock;
        }

        final boolean match(Context context, Activity activity, Intent intent)
        {
            if(mIgnoreMatchingSpecificIntents)
                return false;
            this;
            JVM INSTR monitorenter ;
            int i;
            if(mWhich == null)
                break MISSING_BLOCK_LABEL_44;
            i = mWhich.match(context.getContentResolver(), intent, true, "Instrumentation");
            if(i >= 0)
                break MISSING_BLOCK_LABEL_44;
            this;
            JVM INSTR monitorexit ;
            return false;
            if(mClass == null) goto _L2; else goto _L1
_L1:
            context = null;
            if(activity == null) goto _L4; else goto _L3
_L3:
            context = activity.getClass().getName();
_L5:
            if(context == null)
                break MISSING_BLOCK_LABEL_86;
            boolean flag = mClass.equals(context);
            if(!(flag ^ true))
                break; /* Loop/switch isn't completed */
            this;
            JVM INSTR monitorexit ;
            return false;
_L4:
            if(intent.getComponent() != null)
                context = intent.getComponent().getClassName();
            if(true) goto _L5; else goto _L2
_L2:
            if(activity == null)
                break MISSING_BLOCK_LABEL_121;
            mLastActivity = activity;
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            return true;
            context;
            throw context;
        }

        public ActivityResult onStartActivity(Intent intent)
        {
            return null;
        }

        public final Activity waitForActivity()
        {
            this;
            JVM INSTR monitorenter ;
_L2:
            Activity activity = mLastActivity;
            if(activity != null)
                break; /* Loop/switch isn't completed */
            try
            {
                wait();
            }
            catch(InterruptedException interruptedexception) { }
            if(true) goto _L2; else goto _L1
_L1:
            interruptedexception = mLastActivity;
            mLastActivity = null;
            this;
            JVM INSTR monitorexit ;
            return interruptedexception;
            Exception exception;
            exception;
            throw exception;
        }

        public final Activity waitForActivityWithTimeout(long l)
        {
            this;
            JVM INSTR monitorenter ;
            Activity activity = mLastActivity;
            if(activity != null)
                break MISSING_BLOCK_LABEL_16;
            try
            {
                wait(l);
            }
            catch(InterruptedException interruptedexception) { }
            activity = mLastActivity;
            if(activity != null)
                break MISSING_BLOCK_LABEL_33;
            this;
            JVM INSTR monitorexit ;
            return null;
            interruptedexception = mLastActivity;
            mLastActivity = null;
            this;
            JVM INSTR monitorexit ;
            return interruptedexception;
            Exception exception;
            exception;
            throw exception;
        }

        private final boolean mBlock;
        private final String mClass;
        int mHits;
        private final boolean mIgnoreMatchingSpecificIntents;
        Activity mLastActivity;
        private final ActivityResult mResult;
        private final IntentFilter mWhich;

        public ActivityMonitor()
        {
            mHits = 0;
            mLastActivity = null;
            mWhich = null;
            mClass = null;
            mResult = null;
            mBlock = false;
            mIgnoreMatchingSpecificIntents = true;
        }

        public ActivityMonitor(IntentFilter intentfilter, ActivityResult activityresult, boolean flag)
        {
            mHits = 0;
            mLastActivity = null;
            mWhich = intentfilter;
            mClass = null;
            mResult = activityresult;
            mBlock = flag;
            mIgnoreMatchingSpecificIntents = false;
        }

        public ActivityMonitor(String s, ActivityResult activityresult, boolean flag)
        {
            mHits = 0;
            mLastActivity = null;
            mWhich = null;
            mClass = s;
            mResult = activityresult;
            mBlock = flag;
            mIgnoreMatchingSpecificIntents = false;
        }
    }

    public static final class ActivityResult
    {

        public int getResultCode()
        {
            return mResultCode;
        }

        public Intent getResultData()
        {
            return mResultData;
        }

        private final int mResultCode;
        private final Intent mResultData;

        public ActivityResult(int i, Intent intent)
        {
            mResultCode = i;
            mResultData = intent;
        }
    }

    private static final class ActivityWaiter
    {

        public Activity activity;
        public final Intent intent;

        public ActivityWaiter(Intent intent1)
        {
            intent = intent1;
        }
    }

    private static final class EmptyRunnable
        implements Runnable
    {

        public void run()
        {
        }

        private EmptyRunnable()
        {
        }

        EmptyRunnable(EmptyRunnable emptyrunnable)
        {
            this();
        }
    }

    private static final class Idler
        implements android.os.MessageQueue.IdleHandler
    {

        public final boolean queueIdle()
        {
            if(mCallback != null)
                mCallback.run();
            this;
            JVM INSTR monitorenter ;
            mIdle = true;
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            throw exception;
        }

        public void waitForIdle()
        {
            this;
            JVM INSTR monitorenter ;
_L2:
            boolean flag = mIdle;
            if(flag)
                break; /* Loop/switch isn't completed */
            try
            {
                wait();
            }
            catch(InterruptedException interruptedexception) { }
            if(true) goto _L2; else goto _L1
_L1:
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private final Runnable mCallback;
        private boolean mIdle;

        public Idler(Runnable runnable)
        {
            mCallback = runnable;
            mIdle = false;
        }
    }

    private final class InstrumentationThread extends Thread
    {

        public void run()
        {
            try
            {
                Process.setThreadPriority(-8);
            }
            catch(RuntimeException runtimeexception)
            {
                Log.w("Instrumentation", (new StringBuilder()).append("Exception setting priority of instrumentation thread ").append(Process.myTid()).toString(), runtimeexception);
            }
            if(Instrumentation._2D_get0(Instrumentation.this))
                startPerformanceSnapshot();
            onStart();
        }

        final Instrumentation this$0;

        public InstrumentationThread(String s)
        {
            this$0 = Instrumentation.this;
            super(s);
        }
    }

    private static final class SyncRunnable
        implements Runnable
    {

        public void run()
        {
            mTarget.run();
            this;
            JVM INSTR monitorenter ;
            mComplete = true;
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void waitForComplete()
        {
            this;
            JVM INSTR monitorenter ;
_L2:
            boolean flag = mComplete;
            if(flag)
                break; /* Loop/switch isn't completed */
            try
            {
                wait();
            }
            catch(InterruptedException interruptedexception) { }
            if(true) goto _L2; else goto _L1
_L1:
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private boolean mComplete;
        private final Runnable mTarget;

        public SyncRunnable(Runnable runnable)
        {
            mTarget = runnable;
        }
    }


    static boolean _2D_get0(Instrumentation instrumentation)
    {
        return instrumentation.mAutomaticPerformanceSnapshots;
    }

    static Object _2D_get1(Instrumentation instrumentation)
    {
        return instrumentation.mSync;
    }

    static List _2D_get2(Instrumentation instrumentation)
    {
        return instrumentation.mWaitingActivities;
    }

    public Instrumentation()
    {
        mThread = null;
        mMessageQueue = null;
        mAutomaticPerformanceSnapshots = false;
        mPerfMetrics = new Bundle();
    }

    private void addValue(String s, int i, Bundle bundle)
    {
        if(bundle.containsKey(s))
        {
            s = bundle.getIntegerArrayList(s);
            if(s != null)
                s.add(Integer.valueOf(i));
        } else
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(Integer.valueOf(i));
            bundle.putIntegerArrayList(s, arraylist);
        }
    }

    private void checkInstrumenting(String s)
    {
        if(mInstrContext == null)
            throw new RuntimeException((new StringBuilder()).append(s).append(" cannot be called outside of instrumented processes").toString());
        else
            return;
    }

    public static void checkStartActivityResult(int i, Object obj)
    {
        if(!ActivityManager.isStartResultFatalError(i))
            return;
        switch(i)
        {
        case -98: 
        default:
            throw new AndroidRuntimeException((new StringBuilder()).append("Unknown error code ").append(i).append(" when starting ").append(obj).toString());

        case -92: 
        case -91: 
            if((obj instanceof Intent) && ((Intent)obj).getComponent() != null)
                throw new ActivityNotFoundException((new StringBuilder()).append("Unable to find explicit activity class ").append(((Intent)obj).getComponent().toShortString()).append("; have you declared this activity in your AndroidManifest.xml?").toString());
            else
                throw new ActivityNotFoundException((new StringBuilder()).append("No Activity found to handle ").append(obj).toString());

        case -94: 
            throw new SecurityException((new StringBuilder()).append("Not allowed to start activity ").append(obj).toString());

        case -93: 
            throw new AndroidRuntimeException("FORWARD_RESULT_FLAG used while also requesting a result");

        case -95: 
            throw new IllegalArgumentException("PendingIntent is not an activity");

        case -97: 
            throw new SecurityException((new StringBuilder()).append("Starting under voice control not allowed for: ").append(obj).toString());

        case -99: 
            throw new IllegalStateException("Session calling startVoiceActivity does not match active session");

        case -100: 
            throw new IllegalStateException("Cannot start voice activity on a hidden session");

        case -89: 
            throw new IllegalStateException("Session calling startAssistantActivity does not match active session");

        case -90: 
            throw new IllegalStateException("Cannot start assistant activity on a hidden session");

        case -96: 
            throw new AndroidRuntimeException((new StringBuilder()).append("Activity could not be started for ").append(obj).toString());
        }
    }

    public static Application newApplication(Class class1, Context context)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        class1 = (Application)class1.newInstance();
        class1.attach(context);
        return class1;
    }

    private void postPerformCreate(Activity activity)
    {
        if(mActivityMonitors == null) goto _L2; else goto _L1
_L1:
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int i = mActivityMonitors.size();
        int j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((ActivityMonitor)mActivityMonitors.get(j)).match(activity, activity, activity.getIntent());
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        return;
        activity;
        throw activity;
    }

    private void prePerformCreate(Activity activity)
    {
        if(mWaitingActivities == null)
            break MISSING_BLOCK_LABEL_103;
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int i = mWaitingActivities.size();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        ActivityWaiter activitywaiter = (ActivityWaiter)mWaitingActivities.get(j);
        if(activitywaiter.intent.filterEquals(activity.getIntent()))
        {
            activitywaiter.activity = activity;
            MessageQueue messagequeue = mMessageQueue;
            ActivityGoing activitygoing = JVM INSTR new #12  <Class Instrumentation$ActivityGoing>;
            activitygoing.this. ActivityGoing(activitywaiter);
            messagequeue.addIdleHandler(activitygoing);
        }
        j++;
          goto _L3
_L2:
        return;
        activity;
        throw activity;
    }

    private final void validateNotAppThread()
    {
        if(Looper.myLooper() == Looper.getMainLooper())
            throw new RuntimeException("This method can not be called from the main application thread");
        else
            return;
    }

    public TestLooperManager acquireLooperManager(Looper looper)
    {
        checkInstrumenting("acquireLooperManager");
        return new TestLooperManager(looper);
    }

    public ActivityMonitor addMonitor(IntentFilter intentfilter, ActivityResult activityresult, boolean flag)
    {
        intentfilter = new ActivityMonitor(intentfilter, activityresult, flag);
        addMonitor(((ActivityMonitor) (intentfilter)));
        return intentfilter;
    }

    public ActivityMonitor addMonitor(String s, ActivityResult activityresult, boolean flag)
    {
        s = new ActivityMonitor(s, activityresult, flag);
        addMonitor(((ActivityMonitor) (s)));
        return s;
    }

    public void addMonitor(ActivityMonitor activitymonitor)
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        if(mActivityMonitors == null)
        {
            ArrayList arraylist = JVM INSTR new #124 <Class ArrayList>;
            arraylist.ArrayList();
            mActivityMonitors = arraylist;
        }
        mActivityMonitors.add(activitymonitor);
        obj;
        JVM INSTR monitorexit ;
        return;
        activitymonitor;
        throw activitymonitor;
    }

    public void addResults(Bundle bundle)
    {
        IActivityManager iactivitymanager = ActivityManager.getService();
        try
        {
            iactivitymanager.addInstrumentationResults(mThread.getApplicationThread(), bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
    }

    public void callActivityOnCreate(Activity activity, Bundle bundle)
    {
        prePerformCreate(activity);
        activity.performCreate(bundle);
        postPerformCreate(activity);
    }

    public void callActivityOnCreate(Activity activity, Bundle bundle, PersistableBundle persistablebundle)
    {
        prePerformCreate(activity);
        activity.performCreate(bundle, persistablebundle);
        postPerformCreate(activity);
    }

    public void callActivityOnDestroy(Activity activity)
    {
        activity.performDestroy();
    }

    public void callActivityOnNewIntent(Activity activity, Intent intent)
    {
        activity.performNewIntent(intent);
    }

    public void callActivityOnNewIntent(Activity activity, ReferrerIntent referrerintent)
    {
        Intent intent;
        String s;
        intent = null;
        s = activity.mReferrer;
        if(referrerintent == null)
            break MISSING_BLOCK_LABEL_20;
        activity.mReferrer = referrerintent.mReferrer;
        if(referrerintent == null)
            break MISSING_BLOCK_LABEL_33;
        intent = JVM INSTR new #174 <Class Intent>;
        intent.Intent(referrerintent);
        callActivityOnNewIntent(activity, intent);
        activity.mReferrer = s;
        return;
        referrerintent;
        activity.mReferrer = s;
        throw referrerintent;
    }

    public void callActivityOnPause(Activity activity)
    {
        activity.performPause();
    }

    public void callActivityOnPostCreate(Activity activity, Bundle bundle)
    {
        activity.onPostCreate(bundle);
    }

    public void callActivityOnPostCreate(Activity activity, Bundle bundle, PersistableBundle persistablebundle)
    {
        activity.onPostCreate(bundle, persistablebundle);
    }

    public void callActivityOnRestart(Activity activity)
    {
        activity.onRestart();
    }

    public void callActivityOnRestoreInstanceState(Activity activity, Bundle bundle)
    {
        activity.performRestoreInstanceState(bundle);
    }

    public void callActivityOnRestoreInstanceState(Activity activity, Bundle bundle, PersistableBundle persistablebundle)
    {
        activity.performRestoreInstanceState(bundle, persistablebundle);
    }

    public void callActivityOnResume(Activity activity)
    {
        activity.mResumed = true;
        activity.onResume();
        if(mActivityMonitors == null) goto _L2; else goto _L1
_L1:
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int i = mActivityMonitors.size();
        int j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((ActivityMonitor)mActivityMonitors.get(j)).match(activity, activity, activity.getIntent());
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        return;
        activity;
        throw activity;
    }

    public void callActivityOnSaveInstanceState(Activity activity, Bundle bundle)
    {
        activity.performSaveInstanceState(bundle);
    }

    public void callActivityOnSaveInstanceState(Activity activity, Bundle bundle, PersistableBundle persistablebundle)
    {
        activity.performSaveInstanceState(bundle, persistablebundle);
    }

    public void callActivityOnStart(Activity activity)
    {
        activity.onStart();
    }

    public void callActivityOnStop(Activity activity)
    {
        activity.onStop();
    }

    public void callActivityOnUserLeaving(Activity activity)
    {
        activity.performUserLeaving();
    }

    public void callApplicationOnCreate(Application application)
    {
        application.onCreate();
    }

    public boolean checkMonitorHit(ActivityMonitor activitymonitor, int i)
    {
        waitForIdleSync();
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int j = activitymonitor.getHits();
        if(j >= i)
            break MISSING_BLOCK_LABEL_27;
        obj;
        JVM INSTR monitorexit ;
        return false;
        mActivityMonitors.remove(activitymonitor);
        obj;
        JVM INSTR monitorexit ;
        return true;
        activitymonitor;
        throw activitymonitor;
    }

    public void endPerformanceSnapshot()
    {
        if(!isProfiling())
            mPerfMetrics = mPerformanceCollector.endSnapshot();
    }

    public void execStartActivities(Context context, IBinder ibinder, IBinder ibinder1, Activity activity, Intent aintent[], Bundle bundle)
    {
        execStartActivitiesAsUser(context, ibinder, ibinder1, activity, aintent, bundle, UserHandle.myUserId());
    }

    public void execStartActivitiesAsUser(Context context, IBinder ibinder, IBinder ibinder1, Activity activity, Intent aintent[], Bundle bundle, int i)
    {
        IApplicationThread iapplicationthread;
        SeempLog.record_str(378, aintent.toString());
        iapplicationthread = (IApplicationThread)ibinder;
        if(mActivityMonitors == null) goto _L2; else goto _L1
_L1:
        activity = ((Activity) (mSync));
        activity;
        JVM INSTR monitorenter ;
        int j = mActivityMonitors.size();
        int k = 0;
_L3:
        if(k >= j)
            break; /* Loop/switch isn't completed */
        ActivityMonitor activitymonitor = (ActivityMonitor)mActivityMonitors.get(k);
        ibinder = null;
        if(activitymonitor.ignoreMatchingSpecificIntents())
            ibinder = activitymonitor.onStartActivity(aintent[0]);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_111;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        activity;
        JVM INSTR monitorexit ;
        return;
        boolean flag;
        if(!activitymonitor.match(context, null, aintent[0]))
            break MISSING_BLOCK_LABEL_153;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        flag = activitymonitor.isBlocking();
        if(!flag)
            break; /* Loop/switch isn't completed */
        activity;
        JVM INSTR monitorexit ;
        return;
        k++;
        if(true) goto _L3; else goto _L2
_L2:
        int l;
        try
        {
            ibinder = new String[aintent.length];
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Failure from system", context);
        }
        l = 0;
        if(l >= aintent.length)
            break; /* Loop/switch isn't completed */
        aintent[l].migrateExtraStreamToClipData();
        aintent[l].prepareToLeaveProcess(context);
        ibinder[l] = aintent[l].resolveTypeIfNeeded(context.getContentResolver());
        l++;
        if(true) goto _L5; else goto _L4
_L5:
        break MISSING_BLOCK_LABEL_172;
        context;
        throw context;
_L4:
        checkStartActivityResult(ActivityManager.getService().startActivities(iapplicationthread, context.getBasePackageName(), aintent, ibinder, ibinder1, bundle, i), aintent[0]);
        return;
    }

    public ActivityResult execStartActivity(Context context, IBinder ibinder, IBinder ibinder1, Activity activity, Intent intent, int i, Bundle bundle)
    {
        IApplicationThread iapplicationthread;
        Object obj;
        int k;
        ActivityMonitor activitymonitor;
        SeempLog.record_str(377, intent.toString());
        iapplicationthread = (IApplicationThread)ibinder;
        int j;
        if(activity != null)
            ibinder = activity.onProvideReferrer();
        else
            ibinder = null;
        if(ibinder != null)
            intent.putExtra("android.intent.extra.REFERRER", ibinder);
        if(mActivityMonitors == null)
            break MISSING_BLOCK_LABEL_201;
        obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        j = mActivityMonitors.size();
        k = 0;
_L7:
        if(k >= j) goto _L2; else goto _L1
_L1:
        activitymonitor = (ActivityMonitor)mActivityMonitors.get(k);
        ibinder = null;
        if(activitymonitor.ignoreMatchingSpecificIntents())
            ibinder = activitymonitor.onStartActivity(intent);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_140;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        obj;
        JVM INSTR monitorexit ;
        return ibinder;
        if(!activitymonitor.match(context, null, intent))
            continue; /* Loop/switch isn't completed */
        activitymonitor.mHits = activitymonitor.mHits + 1;
        if(!activitymonitor.isBlocking())
            break; /* Loop/switch isn't completed */
        if(i < 0) goto _L4; else goto _L3
_L3:
        context = activitymonitor.getResult();
_L6:
        obj;
        JVM INSTR monitorexit ;
        return context;
_L4:
        context = null;
        if(true) goto _L6; else goto _L5
_L5:
        k++;
          goto _L7
_L2:
        String s;
        try
        {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(context);
            ibinder = JVM INSTR new #138 <Class StringBuilder>;
            ibinder.StringBuilder();
            Slog.i("Timeline", ibinder.append("Timeline: Activity_launch_request time:").append(SystemClock.uptimeMillis()).toString());
            ibinder = ActivityManager.getService();
            obj = context.getBasePackageName();
            s = intent.resolveTypeIfNeeded(context.getContentResolver());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Failure from system", context);
        }
        if(activity == null)
            break MISSING_BLOCK_LABEL_312;
        context = activity.mEmbeddedID;
_L8:
        checkStartActivityResult(ibinder.startActivity(iapplicationthread, ((String) (obj)), intent, s, ibinder1, context, i, 0, null, bundle), intent);
        return null;
        context;
        throw context;
        context = null;
          goto _L8
    }

    public ActivityResult execStartActivity(Context context, IBinder ibinder, IBinder ibinder1, String s, Intent intent, int i, Bundle bundle)
    {
        IApplicationThread iapplicationthread;
        SeempLog.record_str(377, intent.toString());
        iapplicationthread = (IApplicationThread)ibinder;
        if(mActivityMonitors == null)
            break MISSING_BLOCK_LABEL_171;
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int j = mActivityMonitors.size();
        int k = 0;
_L7:
        if(k >= j) goto _L2; else goto _L1
_L1:
        ActivityMonitor activitymonitor = (ActivityMonitor)mActivityMonitors.get(k);
        ibinder = null;
        if(activitymonitor.ignoreMatchingSpecificIntents())
            ibinder = activitymonitor.onStartActivity(intent);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_110;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        obj;
        JVM INSTR monitorexit ;
        return ibinder;
        if(!activitymonitor.match(context, null, intent))
            continue; /* Loop/switch isn't completed */
        activitymonitor.mHits = activitymonitor.mHits + 1;
        if(!activitymonitor.isBlocking())
            break; /* Loop/switch isn't completed */
        if(i < 0) goto _L4; else goto _L3
_L3:
        context = activitymonitor.getResult();
_L6:
        obj;
        JVM INSTR monitorexit ;
        return context;
_L4:
        context = null;
        if(true) goto _L6; else goto _L5
_L5:
        k++;
          goto _L7
_L2:
        try
        {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(context);
            checkStartActivityResult(ActivityManager.getService().startActivity(iapplicationthread, context.getBasePackageName(), intent, intent.resolveTypeIfNeeded(context.getContentResolver()), ibinder1, s, i, 0, null, bundle), intent);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Failure from system", context);
        }
        return null;
        context;
        throw context;
    }

    public ActivityResult execStartActivity(Context context, IBinder ibinder, IBinder ibinder1, String s, Intent intent, int i, Bundle bundle, 
            UserHandle userhandle)
    {
        IApplicationThread iapplicationthread;
        SeempLog.record_str(377, intent.toString());
        iapplicationthread = (IApplicationThread)ibinder;
        if(mActivityMonitors == null)
            break MISSING_BLOCK_LABEL_171;
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int j = mActivityMonitors.size();
        int k = 0;
_L7:
        if(k >= j) goto _L2; else goto _L1
_L1:
        ActivityMonitor activitymonitor = (ActivityMonitor)mActivityMonitors.get(k);
        ibinder = null;
        if(activitymonitor.ignoreMatchingSpecificIntents())
            ibinder = activitymonitor.onStartActivity(intent);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_110;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        obj;
        JVM INSTR monitorexit ;
        return ibinder;
        if(!activitymonitor.match(context, null, intent))
            continue; /* Loop/switch isn't completed */
        activitymonitor.mHits = activitymonitor.mHits + 1;
        if(!activitymonitor.isBlocking())
            break; /* Loop/switch isn't completed */
        if(i < 0) goto _L4; else goto _L3
_L3:
        context = activitymonitor.getResult();
_L6:
        obj;
        JVM INSTR monitorexit ;
        return context;
_L4:
        context = null;
        if(true) goto _L6; else goto _L5
_L5:
        k++;
          goto _L7
_L2:
        try
        {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(context);
            checkStartActivityResult(ActivityManager.getService().startActivityAsUser(iapplicationthread, context.getBasePackageName(), intent, intent.resolveTypeIfNeeded(context.getContentResolver()), ibinder1, s, i, 0, null, bundle, userhandle.getIdentifier()), intent);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Failure from system", context);
        }
        return null;
        context;
        throw context;
    }

    public ActivityResult execStartActivityAsCaller(Context context, IBinder ibinder, IBinder ibinder1, Activity activity, Intent intent, int i, Bundle bundle, 
            boolean flag, int j)
    {
        IApplicationThread iapplicationthread;
        SeempLog.record_str(379, intent.toString());
        iapplicationthread = (IApplicationThread)ibinder;
        if(mActivityMonitors == null)
            break MISSING_BLOCK_LABEL_171;
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int k = mActivityMonitors.size();
        int l = 0;
_L7:
        if(l >= k) goto _L2; else goto _L1
_L1:
        ActivityMonitor activitymonitor = (ActivityMonitor)mActivityMonitors.get(l);
        ibinder = null;
        if(activitymonitor.ignoreMatchingSpecificIntents())
            ibinder = activitymonitor.onStartActivity(intent);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_110;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        obj;
        JVM INSTR monitorexit ;
        return ibinder;
        if(!activitymonitor.match(context, null, intent))
            continue; /* Loop/switch isn't completed */
        activitymonitor.mHits = activitymonitor.mHits + 1;
        if(!activitymonitor.isBlocking())
            break; /* Loop/switch isn't completed */
        if(i < 0) goto _L4; else goto _L3
_L3:
        context = activitymonitor.getResult();
_L6:
        obj;
        JVM INSTR monitorexit ;
        return context;
_L4:
        context = null;
        if(true) goto _L6; else goto _L5
_L5:
        l++;
          goto _L7
_L2:
        String s;
        try
        {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(context);
            ibinder = ActivityManager.getService();
            obj = context.getBasePackageName();
            s = intent.resolveTypeIfNeeded(context.getContentResolver());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Failure from system", context);
        }
        if(activity == null)
            break MISSING_BLOCK_LABEL_255;
        context = activity.mEmbeddedID;
_L8:
        checkStartActivityResult(ibinder.startActivityAsCaller(iapplicationthread, ((String) (obj)), intent, s, ibinder1, context, i, 0, null, bundle, flag, j), intent);
        return null;
        context;
        throw context;
        context = null;
          goto _L8
    }

    public void execStartActivityFromAppTask(Context context, IBinder ibinder, IAppTask iapptask, Intent intent, Bundle bundle)
    {
        IApplicationThread iapplicationthread;
        SeempLog.record_str(380, intent.toString());
        iapplicationthread = (IApplicationThread)ibinder;
        if(mActivityMonitors == null) goto _L2; else goto _L1
_L1:
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int i = mActivityMonitors.size();
        int j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ActivityMonitor activitymonitor = (ActivityMonitor)mActivityMonitors.get(j);
        ibinder = null;
        if(activitymonitor.ignoreMatchingSpecificIntents())
            ibinder = activitymonitor.onStartActivity(intent);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_109;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        obj;
        JVM INSTR monitorexit ;
        return;
        boolean flag;
        if(!activitymonitor.match(context, null, intent))
            break MISSING_BLOCK_LABEL_149;
        activitymonitor.mHits = activitymonitor.mHits + 1;
        flag = activitymonitor.isBlocking();
        if(!flag)
            break; /* Loop/switch isn't completed */
        obj;
        JVM INSTR monitorexit ;
        return;
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        try
        {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(context);
            checkStartActivityResult(iapptask.startActivity(iapplicationthread.asBinder(), context.getBasePackageName(), intent, intent.resolveTypeIfNeeded(context.getContentResolver()), bundle), intent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Failure from system", context);
        }
        context;
        throw context;
    }

    public void finish(int i, Bundle bundle)
    {
        if(mAutomaticPerformanceSnapshots)
            endPerformanceSnapshot();
        Bundle bundle1 = bundle;
        if(mPerfMetrics != null)
        {
            bundle1 = bundle;
            if(bundle == null)
                bundle1 = new Bundle();
            bundle1.putAll(mPerfMetrics);
        }
        if(mUiAutomation != null && mUiAutomation.isDestroyed() ^ true)
        {
            mUiAutomation.disconnect();
            mUiAutomation = null;
        }
        mThread.finishInstrumentation(i, bundle1);
    }

    public Bundle getAllocCounts()
    {
        Bundle bundle = new Bundle();
        bundle.putLong("global_alloc_count", Debug.getGlobalAllocCount());
        bundle.putLong("global_alloc_size", Debug.getGlobalAllocSize());
        bundle.putLong("global_freed_count", Debug.getGlobalFreedCount());
        bundle.putLong("global_freed_size", Debug.getGlobalFreedSize());
        bundle.putLong("gc_invocation_count", Debug.getGlobalGcInvocationCount());
        return bundle;
    }

    public Bundle getBinderCounts()
    {
        Bundle bundle = new Bundle();
        bundle.putLong("sent_transactions", Debug.getBinderSentTransactions());
        bundle.putLong("received_transactions", Debug.getBinderReceivedTransactions());
        return bundle;
    }

    public ComponentName getComponentName()
    {
        return mComponent;
    }

    public Context getContext()
    {
        return mInstrContext;
    }

    public String getProcessName()
    {
        return mThread.getProcessName();
    }

    public Context getTargetContext()
    {
        return mAppContext;
    }

    public UiAutomation getUiAutomation()
    {
        return getUiAutomation(0);
    }

    public UiAutomation getUiAutomation(int i)
    {
        boolean flag;
        if(mUiAutomation != null)
            flag = mUiAutomation.isDestroyed();
        else
            flag = true;
        if(mUiAutomationConnection != null)
        {
            if(!flag && mUiAutomation.getFlags() == i)
                return mUiAutomation;
            if(flag)
                mUiAutomation = new UiAutomation(getTargetContext().getMainLooper(), mUiAutomationConnection);
            else
                mUiAutomation.disconnect();
            mUiAutomation.connect(i);
            return mUiAutomation;
        } else
        {
            return null;
        }
    }

    final void init(ActivityThread activitythread, Context context, Context context1, ComponentName componentname, IInstrumentationWatcher iinstrumentationwatcher, IUiAutomationConnection iuiautomationconnection)
    {
        mThread = activitythread;
        mThread.getLooper();
        mMessageQueue = Looper.myQueue();
        mInstrContext = context;
        mAppContext = context1;
        mComponent = componentname;
        mWatcher = iinstrumentationwatcher;
        mUiAutomationConnection = iuiautomationconnection;
    }

    public boolean invokeContextMenuAction(Activity activity, int i, int j)
    {
        validateNotAppThread();
        sendKeySync(new KeyEvent(0, 23));
        waitForIdleSync();
        try
        {
            Thread.sleep(ViewConfiguration.getLongPressTimeout());
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            Log.e("Instrumentation", "Could not sleep for long press timeout", activity);
            return false;
        }
        sendKeySync(new KeyEvent(1, 23));
        waitForIdleSync();
        activity = new _cls1ContextMenuRunnable(activity, i, j);
        runOnMainSync(activity);
        return ((_cls1ContextMenuRunnable) (activity)).returnValue;
    }

    public boolean invokeMenuActionSync(Activity activity, int i, int j)
    {
        activity = new _cls1MenuRunnable(activity, i, j);
        runOnMainSync(activity);
        return ((_cls1MenuRunnable) (activity)).returnValue;
    }

    public boolean isProfiling()
    {
        return mThread.isProfiling();
    }

    public Activity newActivity(Class class1, Context context, IBinder ibinder, Application application, Intent intent, ActivityInfo activityinfo, CharSequence charsequence, 
            Activity activity, String s, Object obj)
        throws InstantiationException, IllegalAccessException
    {
        class1 = (Activity)class1.newInstance();
        class1.attach(context, null, this, ibinder, 0, application, intent, activityinfo, charsequence, activity, s, (Activity.NonConfigurationInstances)obj, new Configuration(), null, null, null, null);
        return class1;
    }

    public Activity newActivity(ClassLoader classloader, String s, Intent intent)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return (Activity)classloader.loadClass(s).newInstance();
    }

    public Application newApplication(ClassLoader classloader, String s, Context context)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return newApplication(classloader.loadClass(s), context);
    }

    public void onCreate(Bundle bundle)
    {
    }

    public void onDestroy()
    {
    }

    public boolean onException(Object obj, Throwable throwable)
    {
        return false;
    }

    public void onStart()
    {
    }

    public void removeMonitor(ActivityMonitor activitymonitor)
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        mActivityMonitors.remove(activitymonitor);
        obj;
        JVM INSTR monitorexit ;
        return;
        activitymonitor;
        throw activitymonitor;
    }

    public void runOnMainSync(Runnable runnable)
    {
        validateNotAppThread();
        runnable = new SyncRunnable(runnable);
        mThread.getHandler().post(runnable);
        runnable.waitForComplete();
    }

    public void sendCharacterSync(int i)
    {
        sendKeySync(new KeyEvent(0, i));
        sendKeySync(new KeyEvent(1, i));
    }

    public void sendKeyDownUpSync(int i)
    {
        sendKeySync(new KeyEvent(0, i));
        sendKeySync(new KeyEvent(1, i));
    }

    public void sendKeySync(KeyEvent keyevent)
    {
        validateNotAppThread();
        long l = keyevent.getDownTime();
        long l1 = keyevent.getEventTime();
        int i = keyevent.getAction();
        int j = keyevent.getKeyCode();
        int k = keyevent.getRepeatCount();
        int i1 = keyevent.getMetaState();
        int j1 = keyevent.getDeviceId();
        int k1 = keyevent.getScanCode();
        int i2 = keyevent.getSource();
        int j2 = keyevent.getFlags();
        int k2 = i2;
        if(i2 == 0)
            k2 = 257;
        long l2 = l1;
        if(l1 == 0L)
            l2 = SystemClock.uptimeMillis();
        l1 = l;
        if(l == 0L)
            l1 = l2;
        keyevent = new KeyEvent(l1, l2, i, j, k, i1, j1, k1, j2 | 8, k2);
        InputManager.getInstance().injectInputEvent(keyevent, 2);
    }

    public void sendPointerSync(MotionEvent motionevent)
    {
        validateNotAppThread();
        if((motionevent.getSource() & 2) == 0)
            motionevent.setSource(4098);
        InputManager.getInstance().injectInputEvent(motionevent, 2);
    }

    public void sendStatus(int i, Bundle bundle)
    {
        if(mWatcher == null)
            break MISSING_BLOCK_LABEL_22;
        mWatcher.instrumentationStatus(mComponent, i, bundle);
_L1:
        return;
        bundle;
        mWatcher = null;
          goto _L1
    }

    public void sendStringSync(String s)
    {
        if(s == null)
            return;
        s = KeyCharacterMap.load(-1).getEvents(s.toCharArray());
        if(s != null)
        {
            for(int i = 0; i < s.length; i++)
                sendKeySync(KeyEvent.changeTimeRepeat(s[i], SystemClock.uptimeMillis(), 0));

        }
    }

    public void sendTrackballEventSync(MotionEvent motionevent)
    {
        validateNotAppThread();
        if((motionevent.getSource() & 4) == 0)
            motionevent.setSource(0x10004);
        InputManager.getInstance().injectInputEvent(motionevent, 2);
    }

    public void setAutomaticPerformanceSnapshots()
    {
        mAutomaticPerformanceSnapshots = true;
        mPerformanceCollector = new PerformanceCollector();
    }

    public void setInTouchMode(boolean flag)
    {
        android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window")).setInTouchMode(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void start()
    {
        if(mRunner != null)
        {
            throw new RuntimeException("Instrumentation already started");
        } else
        {
            mRunner = new InstrumentationThread((new StringBuilder()).append("Instr: ").append(getClass().getName()).toString());
            mRunner.start();
            return;
        }
    }

    public Activity startActivitySync(Intent intent)
    {
        SeempLog.record_str(376, intent.toString());
        validateNotAppThread();
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        Intent intent1;
        intent1 = JVM INSTR new #174 <Class Intent>;
        intent1.Intent(intent);
        intent = intent1.resolveActivityInfo(getTargetContext().getPackageManager(), 0);
        if(intent != null) goto _L2; else goto _L1
_L1:
        intent = JVM INSTR new #136 <Class RuntimeException>;
        StringBuilder stringbuilder = JVM INSTR new #138 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        intent.RuntimeException(stringbuilder.append("Unable to resolve activity for: ").append(intent1).toString());
        throw intent;
        intent;
_L4:
        obj;
        JVM INSTR monitorexit ;
        throw intent;
_L2:
        String s = mThread.getProcessName();
        if(!((ActivityInfo) (intent)).processName.equals(s))
        {
            RuntimeException runtimeexception = JVM INSTR new #136 <Class RuntimeException>;
            StringBuilder stringbuilder1 = JVM INSTR new #138 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            runtimeexception.RuntimeException(stringbuilder1.append("Intent in process ").append(s).append(" resolved to different process ").append(((ActivityInfo) (intent)).processName).append(": ").append(intent1).toString());
            throw runtimeexception;
        }
        ComponentName componentname = JVM INSTR new #184 <Class ComponentName>;
        componentname.ComponentName(((ActivityInfo) (intent)).applicationInfo.packageName, ((ActivityInfo) (intent)).name);
        intent1.setComponent(componentname);
        intent = JVM INSTR new #21  <Class Instrumentation$ActivityWaiter>;
        intent.ActivityWaiter(intent1);
        if(mWaitingActivities == null)
        {
            ArrayList arraylist = JVM INSTR new #124 <Class ArrayList>;
            arraylist.ArrayList();
            mWaitingActivities = arraylist;
        }
        mWaitingActivities.add(intent);
        getTargetContext().startActivity(intent1);
        do
            try
            {
                mSync.wait();
            }
            catch(InterruptedException interruptedexception) { }
        while(mWaitingActivities.contains(intent));
        intent = ((ActivityWaiter) (intent)).activity;
        obj;
        JVM INSTR monitorexit ;
        return intent;
        intent;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void startAllocCounting()
    {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().runFinalization();
        Runtime.getRuntime().gc();
        Debug.resetAllCounts();
        Debug.startAllocCounting();
    }

    public void startPerformanceSnapshot()
    {
        if(!isProfiling())
            mPerformanceCollector.beginSnapshot(null);
    }

    public void startProfiling()
    {
        if(mThread.isProfiling())
        {
            File file = new File(mThread.getProfileFilePath());
            file.getParentFile().mkdirs();
            Debug.startMethodTracing(file.toString(), 0x800000);
        }
    }

    public void stopAllocCounting()
    {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().runFinalization();
        Runtime.getRuntime().gc();
        Debug.stopAllocCounting();
    }

    public void stopProfiling()
    {
        if(mThread.isProfiling())
            Debug.stopMethodTracing();
    }

    public void waitForIdle(Runnable runnable)
    {
        mMessageQueue.addIdleHandler(new Idler(runnable));
        mThread.getHandler().post(new EmptyRunnable(null));
    }

    public void waitForIdleSync()
    {
        validateNotAppThread();
        Idler idler = new Idler(null);
        mMessageQueue.addIdleHandler(idler);
        mThread.getHandler().post(new EmptyRunnable(null));
        idler.waitForIdle();
    }

    public Activity waitForMonitor(ActivityMonitor activitymonitor)
    {
        Activity activity = activitymonitor.waitForActivity();
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        mActivityMonitors.remove(activitymonitor);
        obj;
        JVM INSTR monitorexit ;
        return activity;
        activitymonitor;
        throw activitymonitor;
    }

    public Activity waitForMonitorWithTimeout(ActivityMonitor activitymonitor, long l)
    {
        Activity activity = activitymonitor.waitForActivityWithTimeout(l);
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        mActivityMonitors.remove(activitymonitor);
        obj;
        JVM INSTR monitorexit ;
        return activity;
        activitymonitor;
        throw activitymonitor;
    }

    public static final String REPORT_KEY_IDENTIFIER = "id";
    public static final String REPORT_KEY_STREAMRESULT = "stream";
    private static final String TAG = "Instrumentation";
    private List mActivityMonitors;
    private Context mAppContext;
    private boolean mAutomaticPerformanceSnapshots;
    private ComponentName mComponent;
    private Context mInstrContext;
    private MessageQueue mMessageQueue;
    private Bundle mPerfMetrics;
    private PerformanceCollector mPerformanceCollector;
    private Thread mRunner;
    private final Object mSync = new Object();
    private ActivityThread mThread;
    private UiAutomation mUiAutomation;
    private IUiAutomationConnection mUiAutomationConnection;
    private List mWaitingActivities;
    private IInstrumentationWatcher mWatcher;
}
