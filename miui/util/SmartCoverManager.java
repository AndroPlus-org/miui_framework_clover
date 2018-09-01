// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.*;
import android.os.*;
import android.util.Slog;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package miui.util:
//            FeatureParser

public class SmartCoverManager
{
    private class PowerManagerWrapper
    {

        private transient boolean callMethod(String s, Object aobj[], Class aclass[])
        {
            Object obj = null;
            try
            {
                s = mPowerManager.getClass().getMethod(s, aclass);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                s = obj;
            }
            if(s == null)
                return false;
            s.setAccessible(true);
            try
            {
                s.invoke(mPowerManager, aobj);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                return false;
            }
            return true;
        }

        private boolean isAutoBrightnessMode()
        {
            boolean flag = true;
            if(1 != android.provider.Settings.System.getIntForUser(SmartCoverManager._2D_get0(SmartCoverManager.this), "screen_brightness_mode", 0, SmartCoverManager._2D_get1(SmartCoverManager.this)))
                flag = false;
            return flag;
        }

        private void restoreScreenBrightnessByLid()
        {
            IPowerManager ipowermanager = android.os.IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
            if(!isAutoBrightnessMode())
                break MISSING_BLOCK_LABEL_44;
            ipowermanager.setTemporaryScreenAutoBrightnessAdjustmentSettingOverride(android.provider.Settings.System.getFloatForUser(SmartCoverManager._2D_get0(SmartCoverManager.this), "screen_auto_brightness_adj", (0.0F / 0.0F), SmartCoverManager._2D_get1(SmartCoverManager.this)));
_L1:
            return;
            try
            {
                ipowermanager.setTemporaryScreenBrightnessSettingOverride(android.provider.Settings.System.getIntForUser(SmartCoverManager._2D_get0(SmartCoverManager.this), "screen_brightness", mPowerManager.getDefaultScreenBrightnessSetting(), SmartCoverManager._2D_get1(SmartCoverManager.this)));
            }
            catch(Exception exception)
            {
                Slog.e("SmartCoverManager", "exception", exception);
            }
              goto _L1
        }

        void goToSleep()
        {
            long l = SystemClock.uptimeMillis();
            Class class1 = Long.TYPE;
            Class class2 = Integer.TYPE;
            Class class3 = Integer.TYPE;
            if(!callMethod("goToSleep", new Object[] {
        Long.valueOf(l), Integer.valueOf(3), Integer.valueOf(1)
    }, new Class[] {
        class1, class2, class3
    }))
                mPowerManager.goToSleep(SystemClock.uptimeMillis());
        }

        void wakeUp()
        {
            restoreScreenBrightnessByLid();
            long l = SystemClock.uptimeMillis();
            Class class1 = Long.TYPE;
            if(!callMethod("wakeUp", new Object[] {
        Long.valueOf(l), "lid switch open"
    }, new Class[] {
        class1, java/lang/String
    }))
                mPowerManager.wakeUp(SystemClock.uptimeMillis());
        }

        final String GO_TO_SLEEP = "goToSleep";
        final int GO_TO_SLEEP_FLAG_NO_DOZE = 1;
        final int GO_TO_SLEEP_REASON_LID_SWITCH = 3;
        final String WAKE_UP = "wakeUp";
        PowerManager mPowerManager;
        final SmartCoverManager this$0;

        PowerManagerWrapper(PowerManager powermanager)
        {
            this$0 = SmartCoverManager.this;
            super();
            mPowerManager = powermanager;
        }
    }


    static ContentResolver _2D_get0(SmartCoverManager smartcovermanager)
    {
        return smartcovermanager.mContentResolver;
    }

    static int _2D_get1(SmartCoverManager smartcovermanager)
    {
        return smartcovermanager.mCurrentUserId;
    }

    public SmartCoverManager()
    {
        mSmartCoverLidOpen = true;
        mNeedResetTimeout = false;
    }

    private boolean checkSmartCoverEnable()
    {
        mSmartCoverMode = SystemProperties.getInt("persist.sys.smartcover_mode", -1);
        if(mSmartCoverMode == 0)
        {
            mSmartCoverLidOpen = true;
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean deviceDisableKeysWhenLidClose()
    {
        boolean flag = true;
        if(IS_D4)
        {
            flag = false;
            Slog.i("SmartCoverManager", (new StringBuilder()).append("Device: ").append(Build.DEVICE).append(" not disable keys when LidClose.").toString());
        }
        return flag;
    }

    private boolean enableInSmallWinMode(boolean flag)
    {
        boolean flag1;
        ContentResolver contentresolver;
        if(2 <= mSmartCoverMode)
            flag1 = true;
        else
            flag1 = false;
        contentresolver = mContext.getContentResolver();
        if(flag)
            flag = flag1;
        else
            flag = false;
        android.provider.MiuiSettings.System.putBooleanForUser(contentresolver, "is_small_window", flag, mCurrentUserId);
        return flag1;
    }

    private void guideSmartCoverSettingIfNeeded(boolean flag)
    {
        if(flag || MULTI ^ true)
            return;
        if(android.provider.MiuiSettings.System.getBooleanForUser(mContentResolver, "smart_cover_key", true, 0) && isDeviceProvisioned(mContext))
        {
            android.provider.MiuiSettings.System.putBooleanForUser(mContentResolver, "smart_cover_key", false, 0);
            mContext.startActivity((new Intent("miui.intent.action.SMART_COVER_GUIDE")).setComponent(new ComponentName("com.android.settings", "com.android.settings.MiuiSmartCoverGuideActivity")).setFlags(0x10000000));
        }
    }

    private void handleLidSwitchChanged(boolean flag, boolean flag1)
    {
        mSmartCoverLidOpen = flag;
        if(!flag)
            setScreenOffByLid(true);
        if(flag1)
            mContext.sendBroadcastAsUser((new Intent("miui.intent.action.SMART_COVER")).putExtra("is_smart_cover_open", flag), UserHandle.CURRENT);
        if(enableInSmallWinMode(flag ^ true))
            if(!flag)
                mPowerManagerWrapper.goToSleep();
            else
                mPowerManagerWrapper.wakeUp();
        updateScreenOffTimeoutIfNeeded(flag);
    }

    private static boolean isDeviceProvisioned(Context context)
    {
        boolean flag = false;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0)
            flag = true;
        return flag;
    }

    private void setScreenOffByLid(boolean flag)
    {
        String s;
        if(flag)
            s = "true";
        else
            s = "false";
        SystemProperties.set("sys.keyguard.screen_off_by_lid", s);
_L1:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        Slog.e("SmartCoverManager", (new StringBuilder()).append("Set screen off by lid:").append(runtimeexception).toString());
          goto _L1
    }

    private void triggerScreenOffTimeout(boolean flag)
    {
        ContentResolver contentresolver = mContentResolver;
        int i;
        if(flag)
            i = 15000;
        else
            i = 0x7fffffff;
        android.provider.Settings.System.putInt(contentresolver, "screen_off_timeout", i);
        mNeedResetTimeout = flag;
        android.provider.MiuiSettings.System.putBooleanForUser(mContentResolver, "need_reset_screen_off_timeout", mNeedResetTimeout, -2);
    }

    private void updateScreenOffTimeoutIfNeeded(boolean flag)
    {
        boolean flag1;
        if(0x7fffffffL == android.provider.Settings.System.getLong(mContentResolver, "screen_off_timeout", 15000L))
            flag1 = true;
        else
            flag1 = false;
        if(!flag1 || !(flag ^ true)) goto _L2; else goto _L1
_L1:
        triggerScreenOffTimeout(true);
_L4:
        return;
_L2:
        if(flag && mNeedResetTimeout)
            triggerScreenOffTimeout(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void dump(String s, PrintWriter printwriter)
    {
        printwriter.print(s);
        printwriter.print("mSmartCoverLidOpen=");
        printwriter.println(mSmartCoverLidOpen);
        printwriter.print(s);
        printwriter.print("mSmartCoverMode=");
        printwriter.println(mSmartCoverMode);
    }

    public boolean enableLidAfterBoot(int i)
    {
        checkSmartCoverEnable();
        if(-1 == mSmartCoverMode)
            android.provider.MiuiSettings.System.setSmartCoverMode(MULTI ^ true);
        boolean flag = false;
        boolean flag1;
        if(2 <= mSmartCoverMode)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            if(i == 0)
                flag = true;
            else
                flag = false;
        enableInSmallWinMode(flag);
        if(android.provider.MiuiSettings.System.getBooleanForUser(mContentResolver, "need_reset_screen_off_timeout", false, -2))
        {
            android.provider.Settings.System.putInt(mContentResolver, "screen_off_timeout", 0x7fffffff);
            android.provider.MiuiSettings.System.putBooleanForUser(mContentResolver, "need_reset_screen_off_timeout", false, -2);
        }
        return flag1;
    }

    public boolean getSmartCoverLidOpen()
    {
        return mSmartCoverLidOpen;
    }

    public int getSmartCoverMode()
    {
        return mSmartCoverMode;
    }

    public void init(Context context, PowerManager powermanager)
    {
        mContext = context;
        mContentResolver = context.getContentResolver();
        mPowerManagerWrapper = new PowerManagerWrapper(powermanager);
        checkSmartCoverEnable();
        mCurrentUserId = 0;
    }

    public boolean notifyLidSwitchChanged(boolean flag, boolean flag1)
    {
        boolean flag2 = checkSmartCoverEnable();
        guideSmartCoverSettingIfNeeded(flag2);
        if(!flag2)
        {
            return false;
        } else
        {
            handleLidSwitchChanged(flag, flag1);
            return true;
        }
    }

    public boolean notifyScreenTurningOn()
    {
        boolean flag = false;
        if(mSmartCoverLidOpen)
            setScreenOffByLid(false);
        boolean flag1 = flag;
        if(!mSmartCoverLidOpen)
        {
            flag1 = flag;
            if(mContext != null)
                flag1 = android.provider.MiuiSettings.System.isInSmallWindowMode(mContext);
        }
        return flag1;
    }

    public void onUserSwitch(int i)
    {
        if(mCurrentUserId == i)
        {
            return;
        } else
        {
            mCurrentUserId = i;
            enableInSmallWinMode(mSmartCoverLidOpen ^ true);
            return;
        }
    }

    private static final String ACTION_SMART_COVER_GUIDE = "miui.intent.action.SMART_COVER_GUIDE";
    private static final String EXCEPTION = "exception";
    private static final boolean IS_D4;
    private static final int LID_CLOSE_SCREEN_OFF_TIMEOUT_VALUE = 15000;
    private static final String LID_SWITCH_OPEN = "lid switch open";
    private static final boolean MULTI = FeatureParser.getBoolean("support_multiple_small_win_cover", false);
    private static final String POWER = "power";
    private static final String SETTINGS_PKG = "com.android.settings";
    private static final String SMART_COVER_GUIDE_ACTIVITY = "com.android.settings.MiuiSmartCoverGuideActivity";
    private static final String SMART_COVER_LID_OPEN = "mSmartCoverLidOpen=";
    private static final String SMART_COVER_MODE = "mSmartCoverMode=";
    private static final String SUPPORT_MULTIPLE_SMALL_WIN_COVER = "support_multiple_small_win_cover";
    private static final String TAG = "SmartCoverManager";
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mCurrentUserId;
    private boolean mNeedResetTimeout;
    private PowerManagerWrapper mPowerManagerWrapper;
    private boolean mSmartCoverLidOpen;
    private int mSmartCoverMode;

    static 
    {
        boolean flag;
        if(!"oxygen".equals(Build.DEVICE))
            flag = "oxygen".equals(Build.PRODUCT);
        else
            flag = true;
        IS_D4 = flag;
    }
}
