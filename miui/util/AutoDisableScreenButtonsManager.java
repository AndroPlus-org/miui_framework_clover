// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.ActivityManager;
import android.content.*;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.Slog;
import android.view.*;
import android.widget.Toast;
import java.util.List;
import miui.app.AlertDialog;
import miui.view.AutoDisableScreenbuttonsFloatView;

// Referenced classes of package miui.util:
//            AutoDisableScreenButtonsHelper, SmartCoverManager

public class AutoDisableScreenButtonsManager
{
    private class DisableButtonsSettingsObserver extends ContentObserver
    {

        void observe()
        {
            ContentResolver contentresolver = AutoDisableScreenButtonsManager._2D_get0(AutoDisableScreenButtonsManager.this).getContentResolver();
            contentresolver.registerContentObserver(android.provider.Settings.Secure.getUriFor("screen_buttons_state"), false, this, -1);
            contentresolver.registerContentObserver(android.provider.Settings.System.getUriFor("auto_disable_screen_button"), false, this, -1);
            contentresolver.registerContentObserver(android.provider.Settings.System.getUriFor("auto_disable_screen_button_cloud_setting"), false, this, -1);
            onChange(false);
        }

        public void onChange(boolean flag)
        {
            AutoDisableScreenButtonsManager._2D_wrap6(AutoDisableScreenButtonsManager.this);
        }

        final AutoDisableScreenButtonsManager this$0;

        public DisableButtonsSettingsObserver(Handler handler)
        {
            this$0 = AutoDisableScreenButtonsManager.this;
            super(handler);
        }
    }


    static Context _2D_get0(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager)
    {
        return autodisablescreenbuttonsmanager.mContext;
    }

    static AutoDisableScreenbuttonsFloatView _2D_get1(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager)
    {
        return autodisablescreenbuttonsmanager.mFloatView;
    }

    static boolean _2D_get2(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager)
    {
        return autodisablescreenbuttonsmanager.mScreenButtonsTmpDisabled;
    }

    static boolean _2D_set0(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager, boolean flag)
    {
        autodisablescreenbuttonsmanager.mStatusBarVisibleOld = flag;
        return flag;
    }

    static ComponentName _2D_wrap0(Context context)
    {
        return getRunningTopActivity(context);
    }

    static boolean _2D_wrap1(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager)
    {
        return autodisablescreenbuttonsmanager.showPromptsIfNeeds();
    }

    static void _2D_wrap2(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager, boolean flag)
    {
        autodisablescreenbuttonsmanager.saveTmpDisableButtonsStatus(flag);
    }

    static void _2D_wrap3(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager)
    {
        autodisablescreenbuttonsmanager.showFloat();
    }

    static void _2D_wrap4(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager)
    {
        autodisablescreenbuttonsmanager.showSettings();
    }

    static void _2D_wrap5(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager, CharSequence charsequence)
    {
        autodisablescreenbuttonsmanager.showToastInner(charsequence);
    }

    static void _2D_wrap6(AutoDisableScreenButtonsManager autodisablescreenbuttonsmanager)
    {
        autodisablescreenbuttonsmanager.updateSettings();
    }

    public AutoDisableScreenButtonsManager(Context context, Handler handler)
    {
        mCurUserId = 0;
        mTwice = false;
        mStatusBarVisibleOld = true;
        mHandler = new Handler();
        mContext = context;
        resetButtonsStatus();
        (new DisableButtonsSettingsObserver(handler)).observe();
    }

    private static ComponentName getRunningTopActivity(Context context)
    {
        context = (ActivityManager)context.getSystemService("activity");
        if(context == null)
            return null;
        context = context.getRunningTasks(1);
        if(context != null && context.size() > 0)
            return ((android.app.ActivityManager.RunningTaskInfo)context.get(0)).topActivity;
        else
            return null;
    }

    private void resetButtonsStatus()
    {
        saveDisableButtonsStatus(false);
        mScreenButtonsTmpDisabled = false;
    }

    private void saveDisableButtonsStatus(boolean flag)
    {
        mScreenButtonsDisabled = flag;
        ContentResolver contentresolver = mContext.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        android.provider.Settings.Secure.putIntForUser(contentresolver, "screen_buttons_state", i, mCurUserId);
    }

    private void saveTmpDisableButtonsStatus(boolean flag)
    {
        mScreenButtonsTmpDisabled = flag;
        if(mScreenButtonsDisabled)
            return;
        ContentResolver contentresolver = mContext.getContentResolver();
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        android.provider.Settings.Secure.putIntForUser(contentresolver, "screen_buttons_state", byte0, mCurUserId);
    }

    private void showFloat()
    {
        Log.d("AutoDisableScreenButtonsManager", "showing auto disable screen buttons float window...");
        if(mFloatView == null)
        {
            mFloatView = AutoDisableScreenbuttonsFloatView.inflate(mContext);
            mFloatView.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    AutoDisableScreenButtonsManager._2D_get1(AutoDisableScreenButtonsManager.this).dismiss();
                    AutoDisableScreenButtonsManager._2D_wrap2(AutoDisableScreenButtonsManager.this, true);
                    AutoDisableScreenButtonsManager._2D_wrap1(AutoDisableScreenButtonsManager.this);
                }

                final AutoDisableScreenButtonsManager this$0;

            
            {
                this$0 = AutoDisableScreenButtonsManager.this;
                super();
            }
            }
);
            mFloatView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

                public boolean onLongClick(View view)
                {
                    AutoDisableScreenButtonsManager._2D_get1(AutoDisableScreenButtonsManager.this).dismiss();
                    AutoDisableScreenButtonsManager._2D_wrap4(AutoDisableScreenButtonsManager.this);
                    return true;
                }

                final AutoDisableScreenButtonsManager this$0;

            
            {
                this$0 = AutoDisableScreenButtonsManager.this;
                super();
            }
            }
);
        }
        mFloatView.show();
    }

    private boolean showPromptsIfNeeds()
    {
        Object obj = AutoDisableScreenButtonsHelper.getValue(mContext, "ADSB_NOT_SHOW_PROMPTS");
        boolean flag;
        if(obj == null)
            flag = false;
        else
            flag = ((Boolean)obj).booleanValue();
        if(flag)
        {
            return false;
        } else
        {
            AlertDialog alertdialog = (new miui.app.AlertDialog.Builder(mContext)).setTitle(0x110800c3).setMessage(0x110800c4).setCheckBox(true, mContext.getString(0x110800c6)).setCancelable(true).setPositiveButton(0x110800c5, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if(((AlertDialog)dialoginterface).isChecked())
                        AutoDisableScreenButtonsHelper.setValue(AutoDisableScreenButtonsManager._2D_get0(AutoDisableScreenButtonsManager.this), "ADSB_NOT_SHOW_PROMPTS", Boolean.valueOf(true));
                }

                final AutoDisableScreenButtonsManager this$0;

            
            {
                this$0 = AutoDisableScreenButtonsManager.this;
                super();
            }
            }
).create();
            alertdialog.getWindow().setType(2003);
            alertdialog.show();
            return true;
        }
    }

    private void showSettings()
    {
        Intent intent;
        intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(SettingsActionComponent);
        intent.setFlags(0x10000000);
        mContext.startActivity(intent);
_L1:
        return;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        Log.e("AutoDisableScreenButtonsManager", (new StringBuilder()).append("start activity exception, component = ").append(SettingsActionComponent).toString());
          goto _L1
    }

    private void showToast(final CharSequence text, Handler handler)
    {
        if(handler != null)
            handler.post(new Runnable() {

                public void run()
                {
                    AutoDisableScreenButtonsManager._2D_wrap5(AutoDisableScreenButtonsManager.this, text);
                }

                final AutoDisableScreenButtonsManager this$0;
                final CharSequence val$text;

            
            {
                this$0 = AutoDisableScreenButtonsManager.this;
                text = charsequence;
                super();
            }
            }
);
        else
            showToastInner(text);
    }

    private void showToast(boolean flag, Handler handler)
    {
        Context context = mContext;
        int i;
        if(flag)
            i = 0x110800c0;
        else
            i = 0x110800c1;
        showToast(((CharSequence) (context.getString(i))), handler);
    }

    private void showToastInner(CharSequence charsequence)
    {
        charsequence = Toast.makeText(mContext, charsequence, 0);
        charsequence.setType(2006);
        android.view.WindowManager.LayoutParams layoutparams = charsequence.getWindowParams();
        layoutparams.privateFlags = layoutparams.privateFlags | 0x10;
        charsequence.show();
    }

    private void updateSettings()
    {
        Object obj = mContext.getContentResolver();
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        android.provider.Settings.Secure.getIntForUser(((ContentResolver) (obj)), "screen_buttons_state", 0, mCurUserId);
        JVM INSTR tableswitch 0 1: default 48
    //                   0 123
    //                   1 136;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_136;
_L4:
        String s = android.provider.MiuiSettings.System.getStringForUser(((ContentResolver) (obj)), "auto_disable_screen_button", mCurUserId);
        if(s == null)
            break MISSING_BLOCK_LABEL_86;
        if(s.equals(mUserSetting) ^ true)
        {
            mUserSetting = s;
            AutoDisableScreenButtonsHelper.updateUserJson(s);
        }
        obj = android.provider.Settings.System.getString(((ContentResolver) (obj)), "auto_disable_screen_button_cloud_setting");
        if(obj == null)
            break MISSING_BLOCK_LABEL_120;
        if(((String) (obj)).equals(mCloudConfig) ^ true)
        {
            mCloudConfig = ((String) (obj));
            AutoDisableScreenButtonsHelper.updateCloudJson(((String) (obj)));
        }
        obj1;
        JVM INSTR monitorexit ;
        return;
_L2:
        mScreenButtonsDisabled = false;
          goto _L4
        Exception exception;
        exception;
        throw exception;
        mScreenButtonsDisabled = true;
          goto _L4
    }

    public boolean handleDisableButtons(int i, boolean flag, boolean flag1, boolean flag2, KeyEvent keyevent)
    {
        boolean flag3;
        boolean flag4;
        boolean flag5;
        flag3 = false;
        flag4 = keyevent.getDevice().isVirtual();
        if((keyevent.getFlags() & 0x40) != 0)
            flag5 = true;
        else
            flag5 = false;
        i;
        JVM INSTR lookupswitch 5: default 80
    //                   3: 129
    //                   4: 88
    //                   82: 88
    //                   84: 129
    //                   187: 88;
           goto _L1 _L2 _L3 _L3 _L2 _L3
_L1:
        return false;
_L3:
        if(!flag1 || !(flag4 ^ true)) goto _L2; else goto _L4
_L4:
        Slog.i("AutoDisableScreenButtonsManager", (new StringBuilder()).append("disableForSingleKey keyCode:").append(i).toString());
        flag1 = true;
_L6:
        return flag1;
_L2:
        if(flag4)
        {
            flag1 = flag3;
            if(!flag5)
                continue; /* Loop/switch isn't completed */
        }
        if(flag2 && SmartCoverManager.deviceDisableKeysWhenLidClose())
        {
            Slog.i("AutoDisableScreenButtonsManager", (new StringBuilder()).append("disableForLidClose keyCode:").append(i).toString());
            flag1 = true;
        } else
        {
            flag1 = flag3;
            if(screenButtonsInterceptKey(i, flag))
            {
                Slog.i("AutoDisableScreenButtonsManager", (new StringBuilder()).append("screenButtonsDisabled keyCode:").append(i).toString());
                flag1 = true;
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean isScreenButtonsDisabled()
    {
        boolean flag;
        if(!mScreenButtonsDisabled)
            flag = mScreenButtonsTmpDisabled;
        else
            flag = true;
        return flag;
    }

    public void onStatusBarVisibilityChange(final boolean visible)
    {
        if(visible != mStatusBarVisibleOld)
            mHandler.post(new Runnable() {

                public void run()
                {
                    if(!visible) goto _L2; else goto _L1
_L1:
                    if(AutoDisableScreenButtonsManager._2D_get2(AutoDisableScreenButtonsManager.this))
                        AutoDisableScreenButtonsManager._2D_wrap2(AutoDisableScreenButtonsManager.this, false);
                    if(AutoDisableScreenButtonsManager._2D_get1(AutoDisableScreenButtonsManager.this) != null)
                        AutoDisableScreenButtonsManager._2D_get1(AutoDisableScreenButtonsManager.this).dismiss();
_L4:
                    AutoDisableScreenButtonsManager._2D_set0(AutoDisableScreenButtonsManager.this, visible);
                    return;
_L2:
                    Object obj = AutoDisableScreenButtonsManager._2D_wrap0(AutoDisableScreenButtonsManager._2D_get0(AutoDisableScreenButtonsManager.this));
                    if(obj != null)
                    {
                        obj = ((ComponentName) (obj)).getPackageName();
                        int i = AutoDisableScreenButtonsHelper.getAppFlag(AutoDisableScreenButtonsManager._2D_get0(AutoDisableScreenButtonsManager.this), ((String) (obj)));
                        if(i == 2)
                            AutoDisableScreenButtonsManager._2D_wrap2(AutoDisableScreenButtonsManager.this, true);
                        else
                        if(i == 1)
                            AutoDisableScreenButtonsManager._2D_wrap3(AutoDisableScreenButtonsManager.this);
                    }
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final AutoDisableScreenButtonsManager this$0;
                final boolean val$visible;

            
            {
                this$0 = AutoDisableScreenButtonsManager.this;
                visible = flag;
                super();
            }
            }
);
    }

    public void onUserSwitch(int i)
    {
        if(mCurUserId != i)
        {
            mCurUserId = i;
            updateSettings();
        }
    }

    public void resetTmpButtonsStatus()
    {
        mScreenButtonsTmpDisabled = false;
    }

    public boolean screenButtonsInterceptKey(int i, boolean flag)
    {
        if(!isScreenButtonsDisabled())
            return false;
        if(flag)
        {
            long l = SystemClock.elapsedRealtime();
            if(l - mScreenButtonPressedTime < 2000L && mScreenButtonPressedKeyCode == i && mTwice)
            {
                mTwice = false;
                resetButtonsStatus();
                return false;
            }
            mScreenButtonPressedTime = l;
            mScreenButtonPressedKeyCode = i;
            mTwice = true;
            if(l - mToastShowTime > 2000L)
            {
                mToastShowTime = l;
                showToast(mContext.getString(0x110800c2), mHandler);
            }
        }
        return true;
    }

    private static final int ENABLE_KEY_PRESS_INTERVAL = 2000;
    private static final String PREF_ADSB_NOT_SHOW_PROMPTS = "ADSB_NOT_SHOW_PROMPTS";
    private static final ComponentName SettingsActionComponent = ComponentName.unflattenFromString("com.android.settings/.AutoDisableScreenButtonsAppListSettingsActivity");
    private static final String TAG = "AutoDisableScreenButtonsManager";
    private static final int TMP_DISABLE_BUTTON = 2;
    private String mCloudConfig;
    private Context mContext;
    private int mCurUserId;
    private AutoDisableScreenbuttonsFloatView mFloatView;
    private Handler mHandler;
    private final Object mLock = new Object();
    private int mScreenButtonPressedKeyCode;
    private long mScreenButtonPressedTime;
    private boolean mScreenButtonsDisabled;
    private boolean mScreenButtonsTmpDisabled;
    private boolean mStatusBarVisibleOld;
    private long mToastShowTime;
    private boolean mTwice;
    private String mUserSetting;

}
