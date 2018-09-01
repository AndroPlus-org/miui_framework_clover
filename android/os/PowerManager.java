// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

// Referenced classes of package android.os:
//            RemoteException, IPowerManager, ServiceManager, IDeviceIdleController, 
//            Handler, PowerSaveState, Binder, Trace, 
//            WorkSource, IBinder

public final class PowerManager
{
    public final class WakeLock
    {

        private void acquireLocked()
        {
            mInternalCount = mInternalCount + 1;
            mExternalCount = mExternalCount + 1;
            if(!mRefCounted || mInternalCount == 1)
            {
                mHandler.removeCallbacks(mReleaser);
                Trace.asyncTraceBegin(0x20000L, mTraceName, 0);
                try
                {
                    mService.acquireWakeLock(mToken, mFlags, mTag, mPackageName, mWorkSource, mHistoryTag);
                }
                catch(RemoteException remoteexception)
                {
                    throw remoteexception.rethrowFromSystemServer();
                }
                mHeld = true;
            }
        }

        public void acquire()
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            acquireLocked();
            ibinder;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void acquire(long l)
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            acquireLocked();
            mHandler.postDelayed(mReleaser, l);
            ibinder;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        protected void finalize()
            throws Throwable
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            if(!mHeld)
                break MISSING_BLOCK_LABEL_72;
            StringBuilder stringbuilder = JVM INSTR new #56  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.wtf("PowerManager", stringbuilder.append("WakeLock finalized while still held: ").append(mTag).toString());
            Trace.asyncTraceEnd(0x20000L, mTraceName, 0);
            mService.releaseWakeLock(mToken, 0);
            ibinder;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
            obj;
            ibinder;
            JVM INSTR monitorexit ;
            throw obj;
        }

        public String getTag()
        {
            return mTag;
        }

        public boolean isHeld()
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            boolean flag = mHeld;
            ibinder;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        void lambda$_2D_android_os_PowerManager$WakeLock_56736(Runnable runnable)
        {
            runnable.run();
            release();
            return;
            runnable;
            release();
            throw runnable;
        }

        public void release()
        {
            release(0);
        }

        public void release(int i)
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            mInternalCount = mInternalCount - 1;
            if((0x10000 & i) != 0)
                break MISSING_BLOCK_LABEL_34;
            mExternalCount = mExternalCount - 1;
            if(mRefCounted && mInternalCount != 0)
                break MISSING_BLOCK_LABEL_102;
            mHandler.removeCallbacks(mReleaser);
            if(!mHeld)
                break MISSING_BLOCK_LABEL_102;
            Trace.asyncTraceEnd(0x20000L, mTraceName, 0);
            mService.releaseWakeLock(mToken, i);
            mHeld = false;
            if(mRefCounted && mExternalCount < 0)
            {
                RuntimeException runtimeexception = JVM INSTR new #164 <Class RuntimeException>;
                StringBuilder stringbuilder = JVM INSTR new #56  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                runtimeexception.RuntimeException(stringbuilder.append("WakeLock under-locked ").append(mTag).toString());
                throw runtimeexception;
            }
            break MISSING_BLOCK_LABEL_164;
            Object obj;
            obj;
            ibinder;
            JVM INSTR monitorexit ;
            throw obj;
            obj;
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
            ibinder;
            JVM INSTR monitorexit ;
        }

        public void setHistoryTag(String s)
        {
            mHistoryTag = s;
        }

        public void setReferenceCounted(boolean flag)
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            mRefCounted = flag;
            ibinder;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void setTag(String s)
        {
            mTag = s;
        }

        public void setUnimportantForLogging(boolean flag)
        {
            if(flag)
                mFlags = mFlags | 0x40000000;
            else
                mFlags = mFlags & 0xbfffffff;
        }

        public void setWorkSource(WorkSource worksource)
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            WorkSource worksource1;
            worksource1 = worksource;
            if(worksource == null)
                break MISSING_BLOCK_LABEL_24;
            worksource1 = worksource;
            if(worksource.size() == 0)
                worksource1 = null;
            if(worksource1 != null) goto _L2; else goto _L1
_L1:
            boolean flag;
            if(mWorkSource != null)
                flag = true;
            else
                flag = false;
            mWorkSource = null;
_L4:
            if(!flag)
                break MISSING_BLOCK_LABEL_83;
            flag = mHeld;
            if(!flag)
                break MISSING_BLOCK_LABEL_83;
            mService.updateWakeLockWorkSource(mToken, mWorkSource, mHistoryTag);
            ibinder;
            JVM INSTR monitorexit ;
            return;
_L2:
            if(mWorkSource != null)
                break MISSING_BLOCK_LABEL_124;
            flag = true;
            worksource = JVM INSTR new #180 <Class WorkSource>;
            worksource.WorkSource(worksource1);
            mWorkSource = worksource;
            continue; /* Loop/switch isn't completed */
            worksource;
            throw worksource;
            boolean flag1 = mWorkSource.diff(worksource1);
            flag = flag1;
            if(!flag1)
                continue; /* Loop/switch isn't completed */
            mWorkSource.set(worksource1);
            flag = flag1;
            if(true) goto _L4; else goto _L3
_L3:
            worksource;
            throw worksource.rethrowFromSystemServer();
        }

        public String toString()
        {
            IBinder ibinder = mToken;
            ibinder;
            JVM INSTR monitorenter ;
            Object obj;
            obj = JVM INSTR new #56  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = ((StringBuilder) (obj)).append("WakeLock{").append(Integer.toHexString(System.identityHashCode(this))).append(" held=").append(mHeld).append(", refCount=").append(mInternalCount).append("}").toString();
            ibinder;
            JVM INSTR monitorexit ;
            return ((String) (obj));
            Exception exception;
            exception;
            throw exception;
        }

        public Runnable wrap(Runnable runnable)
        {
            acquire();
            return new _.Lambda.OsaxDBgigpqjZN1F4C6nYRYm1YQ(this, runnable);
        }

        private int mExternalCount;
        private int mFlags;
        private boolean mHeld;
        private String mHistoryTag;
        private int mInternalCount;
        private final String mPackageName;
        private boolean mRefCounted;
        private final Runnable mReleaser = new _cls1();
        private String mTag;
        private final IBinder mToken = new Binder();
        private final String mTraceName;
        private WorkSource mWorkSource;
        final PowerManager this$0;

        WakeLock(int i, String s, String s1)
        {
            this$0 = PowerManager.this;
            super();
            mRefCounted = true;
            mFlags = i;
            mTag = s;
            mPackageName = s1;
            mTraceName = (new StringBuilder()).append("WakeLock (").append(mTag).append(")").toString();
        }
    }


    public PowerManager(Context context, IPowerManager ipowermanager, Handler handler)
    {
        mContext = context;
        mService = ipowermanager;
        mHandler = handler;
    }

    public static void validateWakeLockParameters(int i, String s)
    {
        switch(0xffff & i)
        {
        default:
            throw new IllegalArgumentException("Must specify a valid wake lock level.");

        case 1: // '\001'
        case 6: // '\006'
        case 10: // '\n'
        case 26: // '\032'
        case 32: // ' '
        case 64: // '@'
        case 128: 
            break;
        }
        if(s == null)
            throw new IllegalArgumentException("The tag must not be null.");
        else
            return;
    }

    public void boostScreenBrightness(long l)
    {
        try
        {
            mService.boostScreenBrightness(l);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int getDefaultScreenBrightnessForVrSetting()
    {
        return mContext.getResources().getInteger(0x10e0094);
    }

    public int getDefaultScreenBrightnessSetting()
    {
        return mContext.getResources().getInteger(0x10e0097);
    }

    public int getLastShutdownReason()
    {
        int i;
        try
        {
            i = mService.getLastShutdownReason();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getMaximumScreenBrightnessForVrSetting()
    {
        return mContext.getResources().getInteger(0x10e0095);
    }

    public int getMaximumScreenBrightnessSetting()
    {
        return mContext.getResources().getInteger(0x10e0098);
    }

    public int getMinimumScreenBrightnessForVrSetting()
    {
        return mContext.getResources().getInteger(0x10e0096);
    }

    public int getMinimumScreenBrightnessSetting()
    {
        return mContext.getResources().getInteger(0x10e0099);
    }

    public PowerSaveState getPowerSaveState(int i)
    {
        PowerSaveState powersavestate;
        try
        {
            powersavestate = mService.getPowerSaveState(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return powersavestate;
    }

    public void goToSleep(long l)
    {
        goToSleep(l, 0, 0);
    }

    public void goToSleep(long l, int i, int j)
    {
        try
        {
            mService.goToSleep(l, i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean isDeviceIdleMode()
    {
        boolean flag;
        try
        {
            flag = mService.isDeviceIdleMode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isIgnoringBatteryOptimizations(String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(mIDeviceIdleController == null)
            mIDeviceIdleController = IDeviceIdleController.Stub.asInterface(ServiceManager.getService("deviceidle"));
        this;
        JVM INSTR monitorexit ;
        boolean flag;
        try
        {
            flag = mIDeviceIdleController.isPowerSaveWhitelistApp(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
        s;
        throw s;
    }

    public boolean isInteractive()
    {
        boolean flag;
        try
        {
            flag = mService.isInteractive();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isLightDeviceIdleMode()
    {
        boolean flag;
        try
        {
            flag = mService.isLightDeviceIdleMode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isPowerSaveMode()
    {
        boolean flag;
        try
        {
            flag = mService.isPowerSaveMode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isScreenBrightnessBoosted()
    {
        return false;
    }

    public boolean isScreenOn()
    {
        return isInteractive();
    }

    public boolean isSustainedPerformanceModeSupported()
    {
        return mContext.getResources().getBoolean(0x11200b4);
    }

    public boolean isWakeLockLevelSupported(int i)
    {
        boolean flag;
        try
        {
            flag = mService.isWakeLockLevelSupported(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void nap(long l)
    {
        try
        {
            mService.nap(l);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public WakeLock newWakeLock(int i, String s)
    {
        validateWakeLockParameters(i, s);
        return new WakeLock(i, s, mContext.getOpPackageName());
    }

    public void reboot(String s)
    {
        try
        {
            mService.reboot(false, s, true);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void rebootSafeMode()
    {
        try
        {
            mService.rebootSafeMode(false, true);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setBacklightBrightness(int i)
    {
        try
        {
            mService.setTemporaryScreenBrightnessSettingOverride(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean setPowerSaveMode(boolean flag)
    {
        try
        {
            flag = mService.setPowerSaveMode(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void shutdown(boolean flag, String s, boolean flag1)
    {
        try
        {
            mService.shutdown(flag, s, flag1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void userActivity(long l, int i, int j)
    {
        try
        {
            mService.userActivity(l, i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void userActivity(long l, boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        userActivity(l, 0, i);
    }

    public void wakeUp(long l)
    {
        try
        {
            mService.wakeUp(l, "wakeUp", mContext.getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void wakeUp(long l, String s)
    {
        try
        {
            mService.wakeUp(l, s, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public static final int ACQUIRE_CAUSES_WAKEUP = 0x10000000;
    public static final String ACTION_DEVICE_IDLE_MODE_CHANGED = "android.os.action.DEVICE_IDLE_MODE_CHANGED";
    public static final String ACTION_LIGHT_DEVICE_IDLE_MODE_CHANGED = "android.os.action.LIGHT_DEVICE_IDLE_MODE_CHANGED";
    public static final String ACTION_POWER_SAVE_MODE_CHANGED = "android.os.action.POWER_SAVE_MODE_CHANGED";
    public static final String ACTION_POWER_SAVE_MODE_CHANGED_INTERNAL = "android.os.action.POWER_SAVE_MODE_CHANGED_INTERNAL";
    public static final String ACTION_POWER_SAVE_MODE_CHANGING = "android.os.action.POWER_SAVE_MODE_CHANGING";
    public static final String ACTION_POWER_SAVE_TEMP_WHITELIST_CHANGED = "android.os.action.POWER_SAVE_TEMP_WHITELIST_CHANGED";
    public static final String ACTION_POWER_SAVE_WHITELIST_CHANGED = "android.os.action.POWER_SAVE_WHITELIST_CHANGED";
    public static final String ACTION_SCREEN_BRIGHTNESS_BOOST_CHANGED = "android.os.action.SCREEN_BRIGHTNESS_BOOST_CHANGED";
    public static final int BRIGHTNESS_DEFAULT = -1;
    public static final int BRIGHTNESS_OFF = 0;
    public static final int BRIGHTNESS_ON = 255;
    public static final int DOZE_WAKE_LOCK = 64;
    public static final int DRAW_WAKE_LOCK = 128;
    public static final String EXTRA_POWER_SAVE_MODE = "mode";
    public static final int FULL_WAKE_LOCK = 26;
    public static final int GO_TO_SLEEP_FLAG_NO_DOZE = 1;
    public static final int GO_TO_SLEEP_REASON_APPLICATION = 0;
    public static final int GO_TO_SLEEP_REASON_DEVICE_ADMIN = 1;
    public static final int GO_TO_SLEEP_REASON_HDMI = 5;
    public static final int GO_TO_SLEEP_REASON_LID_SWITCH = 3;
    public static final int GO_TO_SLEEP_REASON_POWER_BUTTON = 4;
    public static final int GO_TO_SLEEP_REASON_SLEEP_BUTTON = 6;
    public static final int GO_TO_SLEEP_REASON_TIMEOUT = 2;
    public static final int ON_AFTER_RELEASE = 0x20000000;
    public static final int PARTIAL_WAKE_LOCK = 1;
    public static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    public static final String REBOOT_QUIESCENT = "quiescent";
    public static final String REBOOT_RECOVERY = "recovery";
    public static final String REBOOT_RECOVERY_UPDATE = "recovery-update";
    public static final String REBOOT_REQUESTED_BY_DEVICE_OWNER = "deviceowner";
    public static final String REBOOT_SAFE_MODE = "safemode";
    public static final int RELEASE_FLAG_TIMEOUT = 0x10000;
    public static final int RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY = 1;
    public static final int SCREEN_BRIGHT_WAKE_LOCK = 10;
    public static final int SCREEN_DIM_WAKE_LOCK = 6;
    public static final int SHUTDOWN_REASON_REBOOT = 2;
    public static final int SHUTDOWN_REASON_SHUTDOWN = 1;
    public static final int SHUTDOWN_REASON_THERMAL_SHUTDOWN = 4;
    public static final int SHUTDOWN_REASON_UNKNOWN = 0;
    public static final int SHUTDOWN_REASON_USER_REQUESTED = 3;
    public static final String SHUTDOWN_USER_REQUESTED = "userrequested";
    private static final String TAG = "PowerManager";
    public static final int UNIMPORTANT_FOR_LOGGING = 0x40000000;
    public static final int USER_ACTIVITY_EVENT_ACCESSIBILITY = 3;
    public static final int USER_ACTIVITY_EVENT_BUTTON = 1;
    public static final int USER_ACTIVITY_EVENT_OTHER = 0;
    public static final int USER_ACTIVITY_EVENT_TOUCH = 2;
    public static final int USER_ACTIVITY_FLAG_INDIRECT = 2;
    public static final int USER_ACTIVITY_FLAG_NO_CHANGE_LIGHTS = 1;
    public static final int WAKE_LOCK_LEVEL_MASK = 65535;
    final Context mContext;
    final Handler mHandler;
    IDeviceIdleController mIDeviceIdleController;
    final IPowerManager mService;

    // Unreferenced inner class android/os/PowerManager$WakeLock$1

/* anonymous class */
    class WakeLock._cls1
        implements Runnable
    {

        public void run()
        {
            release(0x10000);
        }

        final WakeLock this$1;

            
            {
                this$1 = WakeLock.this;
                super();
            }
    }

}
