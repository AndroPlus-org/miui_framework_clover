// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app;

import android.app.*;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.*;
import android.net.wifi.*;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.view.IWindowManager;
import android.view.Window;
import android.widget.*;
import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.*;
import miui.os.*;
import miui.provider.ExtraTelephony;
import miui.securityspace.CrossUserUtils;
import miui.telephony.TelephonyManager;
import miui.util.*;

// Referenced classes of package miui.app:
//            WifiApEnabler, CompatibilityP, ConnectivityManagerReflector

public class ToggleManager
{
    public static interface OnToggleChangedListener
    {

        public abstract void OnToggleChanged(int i);
    }

    public static interface OnToggleOrderChangedListener
    {

        public abstract void OnToggleOrderChanged();
    }

    private final class WorkHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag;
            boolean flag1;
            int i;
            boolean flag2;
            flag = true;
            flag1 = true;
            i = 1;
            flag2 = true;
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 1 5: default 52
        //                       1 53
        //                       2 83
        //                       3 116
        //                       4 157
        //                       5 199;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            return;
_L2:
            WifiManager wifimanager = ToggleManager._2D_get8();
            if(message.arg1 != 1)
                flag2 = false;
            wifimanager.setWifiEnabled(flag2);
            continue; /* Loop/switch isn't completed */
_L3:
            ToggleManager togglemanager = ToggleManager.this;
            boolean flag3;
            if(message.arg1 == 1)
                flag3 = flag;
            else
                flag3 = false;
            ToggleManager._2D_wrap3(togglemanager, flag3);
            continue; /* Loop/switch isn't completed */
_L4:
            ToggleManager togglemanager1 = ToggleManager.this;
            i = message.arg1;
            boolean flag4;
            if(message.arg2 == 1)
                flag4 = flag1;
            else
                flag4 = false;
            ToggleManager._2D_wrap1(togglemanager1, i, flag4);
            continue; /* Loop/switch isn't completed */
_L5:
            boolean flag5 = ToggleManager._2D_wrap0(ToggleManager.this);
            message = ToggleManager._2D_get3(ToggleManager.this);
            if(!flag5)
                i = 0;
            message.obtainMessage(2, i, 0).sendToTarget();
            continue; /* Loop/switch isn't completed */
_L6:
            ToggleManager._2D_wrap4(ToggleManager.this);
            if(true) goto _L1; else goto _L7
_L7:
        }

        private static final int MSG_APPLY_BRIGHTNESS = 3;
        private static final int MSG_TOGGLE_BLUETOOTH = 2;
        private static final int MSG_TOGGLE_SYNC = 5;
        private static final int MSG_TOGGLE_WIFI = 1;
        private static final int MSG_UPDATE_SYNC = 4;
        final ToggleManager this$0;

        public WorkHandler(Looper looper)
        {
            this$0 = ToggleManager.this;
            super(looper);
        }
    }


    static Handler _2D_get0(ToggleManager togglemanager)
    {
        return togglemanager.mBgHandler;
    }

    static Context _2D_get1(ToggleManager togglemanager)
    {
        return togglemanager.mContext;
    }

    static int _2D_get2(ToggleManager togglemanager)
    {
        return togglemanager.mCurrentUserId;
    }

    static Handler _2D_get3(ToggleManager togglemanager)
    {
        return togglemanager.mHandler;
    }

    static MobileDataUtils _2D_get4(ToggleManager togglemanager)
    {
        return togglemanager.mMobileDataUtils;
    }

    static ContentResolver _2D_get5(ToggleManager togglemanager)
    {
        return togglemanager.mResolver;
    }

    static List _2D_get6(ToggleManager togglemanager)
    {
        return togglemanager.mToggleOrderChangedListener;
    }

    static Runnable _2D_get7(ToggleManager togglemanager)
    {
        return togglemanager.mUpdateSyncStateRunnable;
    }

    static WifiManager _2D_get8()
    {
        return mWifiManager;
    }

    static boolean _2D_get9()
    {
        return sHasMiDrop;
    }

    static boolean _2D_set0(ToggleManager togglemanager, boolean flag)
    {
        togglemanager.mIsSimMissing = flag;
        return flag;
    }

    static boolean _2D_set1(ToggleManager togglemanager, boolean flag)
    {
        togglemanager.mMobilePolicyEnable = flag;
        return flag;
    }

    static boolean _2D_set2(boolean flag)
    {
        sHasMiDrop = flag;
        return flag;
    }

    static boolean _2D_wrap0(ToggleManager togglemanager)
    {
        return togglemanager.isSyncOn();
    }

    static void _2D_wrap1(ToggleManager togglemanager, int i, boolean flag)
    {
        togglemanager.applyBrightnessIntenal(i, flag);
    }

    static void _2D_wrap10(ToggleManager togglemanager)
    {
        togglemanager.updateFlightModeToggle();
    }

    static void _2D_wrap11(ToggleManager togglemanager)
    {
        togglemanager.updateGpsToggle();
    }

    static void _2D_wrap12(ToggleManager togglemanager)
    {
        togglemanager.updateMiDropToggle();
    }

    static void _2D_wrap13(ToggleManager togglemanager)
    {
        togglemanager.updatePaperModeToggle();
    }

    static void _2D_wrap14(ToggleManager togglemanager)
    {
        togglemanager.updatePowerModeToggle();
    }

    static void _2D_wrap15(ToggleManager togglemanager)
    {
        togglemanager.updateQuietModeToggle();
    }

    static void _2D_wrap16(ToggleManager togglemanager)
    {
        togglemanager.updateScreenButtonState();
    }

    static void _2D_wrap17(ToggleManager togglemanager)
    {
        togglemanager.updateSyncToggle();
    }

    static void _2D_wrap18(ToggleManager togglemanager, boolean flag)
    {
        togglemanager.updateSyncToggle(flag);
    }

    static void _2D_wrap19(ToggleManager togglemanager)
    {
        togglemanager.updateTorchToggle();
    }

    static void _2D_wrap2(ToggleManager togglemanager)
    {
        togglemanager.queryBrightnessStatus();
    }

    static void _2D_wrap20(ToggleManager togglemanager)
    {
        togglemanager.verifyBluetoothState();
    }

    static void _2D_wrap3(ToggleManager togglemanager, boolean flag)
    {
        togglemanager.toggleBluetooth(flag);
    }

    static void _2D_wrap4(ToggleManager togglemanager)
    {
        togglemanager.toggleSyncIntenal();
    }

    static void _2D_wrap5(ToggleManager togglemanager)
    {
        togglemanager.updateAccelerometerToggle();
    }

    static void _2D_wrap6(ToggleManager togglemanager)
    {
        togglemanager.updateBatterySaverToggle();
    }

    static void _2D_wrap7(ToggleManager togglemanager)
    {
        togglemanager.updateBluetoothToggle();
    }

    static void _2D_wrap8(ToggleManager togglemanager)
    {
        togglemanager.updateBrightnessToggle();
    }

    static void _2D_wrap9(ToggleManager togglemanager)
    {
        togglemanager.updateDataToggle();
    }

    private ToggleManager(Context context)
    {
        mCurrentUserId = 0;
        mBluetoothAdapter = null;
        mIsSimMissing = false;
        mPackageChangeReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                context1 = intent.getAction();
                if(!"android.intent.action.PACKAGE_ADDED".equals(context1)) goto _L2; else goto _L1
_L1:
                if(!ToggleManager._2D_get9() && "com.xiaomi.midrop".equals(intent.getData().getSchemeSpecificPart()))
                    ToggleManager._2D_set2(true);
_L4:
                return;
_L2:
                if("android.intent.action.PACKAGE_REMOVED".equals(context1) && ToggleManager._2D_get9() && "com.xiaomi.midrop".equals(intent.getData().getSchemeSpecificPart()))
                    ToggleManager._2D_set2(false);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super();
            }
        }
;
        mBroadcastReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                context1 = intent.getAction();
                if(!"android.net.wifi.WIFI_STATE_CHANGED".equals(context1) && !"android.net.wifi.STATE_CHANGE".equals(context1) && !"android.intent.action.LOCALE_CHANGED".equals(context1)) goto _L2; else goto _L1
_L1:
                updateWifiToggle(intent);
_L4:
                return;
_L2:
                if("android.bluetooth.adapter.action.STATE_CHANGED".equals(context1))
                {
                    if(!ToggleManager._2D_get0(ToggleManager.this).hasMessages(2))
                        ToggleManager._2D_wrap7(ToggleManager.this);
                } else
                if("android.media.RINGER_MODE_CHANGED".equals(context1))
                {
                    updateRingerToggle();
                    updateVibrateToggle();
                } else
                if("android.intent.action.AIRPLANE_MODE".equals(context1))
                    ToggleManager._2D_wrap10(ToggleManager.this);
                else
                if(context1.equals("android.intent.action.SIM_STATE_CHANGED"))
                {
                    ToggleManager._2D_set0(ToggleManager.this, TelephonyManager.getDefault().hasIccCard() ^ true);
                    ToggleManager._2D_wrap9(ToggleManager.this);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super();
            }
        }
;
        mTogglOrderObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                if(ToggleManager._2D_get6(ToggleManager.this).size() > 0)
                {
                    for(Iterator iterator = ToggleManager._2D_get6(ToggleManager.this).iterator(); iterator.hasNext(); ((OnToggleOrderChangedListener)iterator.next()).OnToggleOrderChanged());
                }
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mFlightModeObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap10(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mMobileDataEnableObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_get4(ToggleManager.this).onMobileDataChange(ToggleManager._2D_get1(ToggleManager.this));
                ToggleManager._2D_wrap9(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mMobilePolicyEnableObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                flag1 = true;
                ToggleManager togglemanager = ToggleManager.this;
                if(android.provider.Settings.Secure.getIntForUser(ToggleManager._2D_get5(ToggleManager.this), "mobile_policy", 1, ToggleManager._2D_get2(ToggleManager.this)) != 1)
                    flag1 = false;
                ToggleManager._2D_set1(togglemanager, flag1);
                ToggleManager._2D_wrap9(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mTorchEnableObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap19(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mScreenButtonStateObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap16(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mLocationAllowedObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap11(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mAccelerometerRotationObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap5(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mBrightnessObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap2(ToggleManager.this);
                ToggleManager._2D_wrap8(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mPowerModeObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap14(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mBatterySaverObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap6(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mPaperModeObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap13(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mMiDropObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                ToggleManager._2D_wrap12(ToggleManager.this);
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mVibrateEnableObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag1)
            {
                updateVibrateToggle();
            }

            final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(handler);
            }
        }
;
        mWifiEnable = false;
        mWifiConnected = false;
        mWifiChanging = false;
        mWifiSsid = null;
        mBluetoothDelay = false;
        mContext = context;
        mBgThread = new HandlerThread("ToggleManager", 10);
        mBgThread.start();
        mBgHandler = new WorkHandler(mBgThread.getLooper());
        boolean flag = "com.android.systemui".equals(context.getApplicationInfo().packageName);
        int i;
        ConnectivityManager connectivitymanager;
        if(flag)
            i = ActivityManager.getCurrentUser();
        else
            i = UserHandle.myUserId();
        mCurrentUserId = i;
        mResolver = mContext.getContentResolver();
        mResource = mContext.getResources();
        mToggleChangedListener = new ArrayList();
        mToggleOrderChangedListener = new ArrayList();
        mMobileDataUtils = MobileDataUtils.getInstance();
        mWifiManager = (WifiManager)mContext.getSystemService("wifi");
        connectivitymanager = (ConnectivityManager)mContext.getSystemService("connectivity");
        mBrightnessAutoAvailable = mResource.getBoolean(0x110a0001);
        if(connectivitymanager.getTetherableWifiRegexs().length != 0)
            i = 1;
        else
            i = 0;
        if(i != 0)
            mWifiApEnabler = new WifiApEnabler(context, this);
        registerListener(flag);
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            sLongClickActions[25] = 0x11080028;
    }

    private static void addIfUnselected(ArrayList arraylist, int i)
    {
        if(!arraylist.contains(Integer.valueOf(i)))
            arraylist.add(Integer.valueOf(i));
    }

    private void applyBrightnessIntenal(int i, boolean flag)
    {
        if(!mBrightnessAutoMode || !USE_SCREEN_AUTO_BRIGHTNESS_ADJUSTMENT) goto _L2; else goto _L1
_L1:
        if(!"pinecone".equals(AUTO_BRIGHTNESS_OPTIMIZE_STRATEGY)) goto _L4; else goto _L3
_L3:
        CompatibilityP.setTemporaryScreenBrightness(i + MINIMUM_BACKLIGHT);
_L6:
        return;
_L4:
        float f = ((float)i * 2.0F) / (float)RANGE - 1.0F;
        CompatibilityP.setTemporaryScreenAutoBrightness(f);
        if(flag)
            android.provider.Settings.System.putFloatForUser(mResolver, "screen_auto_brightness_adj", f, mCurrentUserId);
        continue; /* Loop/switch isn't completed */
_L2:
        i += MINIMUM_BACKLIGHT;
        CompatibilityP.setTemporaryScreenBrightness(i);
        if(flag)
            android.provider.Settings.System.putIntForUser(mResolver, "screen_brightness", i, mCurrentUserId);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static ToggleManager createInstance(Context context)
    {
        if(sToggleManager == null)
            sToggleManager = new ToggleManager(context.getApplicationContext());
        return sToggleManager;
    }

    private boolean ensureBluetoothAdapter()
    {
        if(mBluetoothAdapter == null)
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean flag;
        if(mBluetoothAdapter != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static void filterToggle(Context context, ArrayList arraylist, int i)
    {
        context = context.getApplicationContext();
        miui/app/ToggleManager;
        JVM INSTR monitorenter ;
        boolean flag = sStaticFieldsInited;
        if(flag)
            break MISSING_BLOCK_LABEL_131;
        sHasVibrator = ((Vibrator)context.getSystemService("vibrator")).hasVibrator();
        ConnectivityManager connectivitymanager;
        if(context.getPackageManager().queryIntentActivities(getCastIntent(), 0xd0000).size() > 0)
            flag = true;
        else
            flag = false;
        sHasCast = flag;
        connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        if(connectivitymanager.getTetherableWifiRegexs().length != 0)
            flag = true;
        else
            flag = false;
        try
        {
            sWifiApAvailable = flag;
            sHasMobileData = connectivitymanager.isNetworkSupported(0);
            sHasGpsFeature = context.getPackageManager().hasSystemFeature("android.hardware.location.gps");
        }
        catch(Exception exception) { }
        if(context.getPackageManager().getApplicationInfo("com.xiaomi.midrop", 0) != null)
            flag = true;
        else
            flag = false;
        try
        {
            sHasMiDrop = flag;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
        sStaticFieldsInited = true;
        miui/app/ToggleManager;
        JVM INSTR monitorexit ;
        if(!FeatureParser.getBoolean("support_power_mode", false))
            arraylist.remove(Integer.valueOf(23));
        if(!sWifiApAvailable)
            arraylist.remove(Integer.valueOf(24));
        if(!Build.hasCameraFlash(context))
            arraylist.remove(Integer.valueOf(11));
        if(!sHasMobileData)
        {
            arraylist.remove(Integer.valueOf(1));
            arraylist.remove(Integer.valueOf(25));
        }
        if(!sHasVibrator)
            arraylist.remove(Integer.valueOf(6));
        if(!sHasGpsFeature)
            arraylist.remove(Integer.valueOf(7));
        if(!FeatureParser.getBoolean("support_screen_paper_mode", false))
            arraylist.remove(Integer.valueOf(26));
        if(!sHasMiDrop || Build.IS_INTERNATIONAL_BUILD)
            arraylist.remove(Integer.valueOf(27));
        if(!sHasCast)
            arraylist.remove(Integer.valueOf(28));
        if(!(isMiPad() ^ true))
            arraylist.remove(Integer.valueOf(30));
        filterToggleByUser(context, arraylist, i);
        return;
        context;
        throw context;
    }

    private static void filterToggleByUser(Context context, ArrayList arraylist, int i)
    {
        boolean flag = false;
        if(i != 0)
        {
            context = sRemoveByMultiUserList;
            int j = context.length;
            for(i = ((flag) ? 1 : 0); i < j; i++)
                arraylist.remove(Integer.valueOf(context[i]));

        }
    }

    public static ArrayList getAllToggles(Context context)
    {
        ArrayList arraylist = new ArrayList(31);
        arraylist.add(Integer.valueOf(9));
        arraylist.add(Integer.valueOf(2));
        arraylist.add(Integer.valueOf(25));
        arraylist.add(Integer.valueOf(18));
        arraylist.add(Integer.valueOf(3));
        arraylist.add(Integer.valueOf(6));
        arraylist.add(Integer.valueOf(5));
        arraylist.add(Integer.valueOf(7));
        arraylist.add(Integer.valueOf(26));
        arraylist.add(Integer.valueOf(15));
        arraylist.add(Integer.valueOf(1));
        arraylist.add(Integer.valueOf(22));
        arraylist.add(Integer.valueOf(11));
        arraylist.add(Integer.valueOf(20));
        arraylist.add(Integer.valueOf(10));
        arraylist.add(Integer.valueOf(23));
        arraylist.add(Integer.valueOf(24));
        arraylist.add(Integer.valueOf(8));
        arraylist.add(Integer.valueOf(27));
        arraylist.add(Integer.valueOf(28));
        arraylist.add(Integer.valueOf(30));
        filterToggle(context, arraylist, getUserId(context));
        return arraylist;
    }

    private static Intent getCastIntent()
    {
        Intent intent = new Intent("miui.intent.action.MIPLAY");
        intent.addFlags(0x10000000);
        intent.addFlags(0x20000000);
        return intent;
    }

    public static ArrayList getDefaultToggleOrder(Context context)
    {
        int i = getUserId(context);
        ArrayList arraylist = new ArrayList();
        validateToggleOrder(context, arraylist, isListStyle(context, i), i);
        return arraylist;
    }

    public static int getEditFixedPosition(Context context)
    {
        return context.getResources().getInteger(0x11070000);
    }

    public static int getGeneralImage(int i)
    {
        return sToggleGeneralImages[i];
    }

    public static int getImage(int i)
    {
        return sToggleImages[i];
    }

    public static Drawable getImageDrawable(int i, Context context)
    {
        return getImageDrawable(i, getStatus(i), context);
    }

    public static Drawable getImageDrawable(int i, boolean flag, Context context)
    {
        Drawable drawable = context.getResources().getDrawable(getImageResource(i, flag));
        Object obj = drawable;
        if(!flag)
        {
            Drawable drawable1 = context.getResources().getDrawable(0x110200b1);
            obj = Bitmap.createBitmap(drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(((Bitmap) (obj)));
            drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
            drawable1.draw(canvas);
            drawable.setBounds((drawable1.getIntrinsicWidth() - drawable.getIntrinsicWidth()) / 2, (drawable1.getIntrinsicHeight() - drawable.getIntrinsicHeight()) / 2, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            obj = new BitmapDrawable(context.getResources(), ((Bitmap) (obj)));
        }
        return ((Drawable) (obj));
    }

    public static int getImageResource(int i, boolean flag)
    {
        if(flag)
            i = sToggleOnImages[i];
        else
            i = sToggleOffImages[i];
        return i;
    }

    public static int getName(int i)
    {
        return sToggleNames[i];
    }

    private int getRotation(IWindowManager iwindowmanager)
    {
        if(android.os.Build.VERSION.SDK_INT < 26)
            iwindowmanager = ReflectionUtils.tryCallMethod(iwindowmanager, "getRotation", java/lang/Integer, new Object[0]);
        else
            iwindowmanager = ReflectionUtils.tryCallMethod(iwindowmanager, "getDefaultDisplayRotation", java/lang/Integer, new Object[0]);
        return ((Integer)iwindowmanager.get()).intValue();
    }

    public static boolean getStatus(int i)
    {
        return sToggleStatus[i];
    }

    public static String getStatusName(int i, Resources resources)
    {
        Object obj = sToggleStatusNames.get(Integer.valueOf(i));
        if(obj instanceof Integer)
            return resources.getString(((Integer)obj).intValue());
        else
            return obj.toString();
    }

    public static int getToggleIdFromString(String s)
    {
        if(!sToggleStringToId.containsKey(s))
            return -1;
        else
            return ((Integer)sToggleStringToId.get(s)).intValue();
    }

    private static String getToggleOrderSettingID(Context context)
    {
        if(isListStyle(context))
            context = "status_bar_toggle_list_order_new";
        else
            context = "status_bar_toggle_page_order";
        return context;
    }

    public static String getToggleStringFromId(int i)
    {
        for(Iterator iterator = sToggleStringToId.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(i == ((Integer)entry.getValue()).intValue())
                return (String)entry.getKey();
        }

        return null;
    }

    private static int getUserId(Context context)
    {
        int i;
        if("com.android.systemui".equals(context.getApplicationInfo().packageName))
            i = ActivityManager.getCurrentUser();
        else
            i = UserHandle.myUserId();
        return i;
    }

    public static ArrayList getUserSelectedToggleOrder(Context context)
    {
        return getUserSelectedToggleOrder(context, getUserId(context));
    }

    public static ArrayList getUserSelectedToggleOrder(Context context, int i)
    {
        return getUserSelectedToggleOrder(context, isListStyle(context, i), i);
    }

    public static ArrayList getUserSelectedToggleOrder(Context context, boolean flag)
    {
        return getUserSelectedToggleOrder(context, flag, getUserId(context));
    }

    public static ArrayList getUserSelectedToggleOrder(Context context, boolean flag, int i)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        Object obj;
        int j;
        int k;
        if(flag)
            obj = "status_bar_toggle_list_order_new";
        else
            obj = "status_bar_toggle_page_order";
        obj = android.provider.Settings.System.getStringForUser(context.getContentResolver(), ((String) (obj)), i);
        if(TextUtils.isEmpty(((CharSequence) (obj)))) goto _L2; else goto _L1
_L1:
        obj = ((String) (obj)).split(" ");
        j = 0;
_L3:
        if(j >= obj.length)
            break; /* Loop/switch isn't completed */
        k = Integer.valueOf(obj[j]).intValue();
        if(getName(k) != 0)
            arraylist.add(Integer.valueOf(k));
        j++;
        if(true) goto _L3; else goto _L2
        Exception exception;
        exception;
        arraylist.clear();
_L2:
        validateToggleOrder(context, arraylist, flag, i);
        return arraylist;
    }

    private String huntForSsid(WifiInfo wifiinfo)
    {
        Object obj;
label0:
        {
            String s = wifiinfo.getSSID();
            obj = s;
            if(s != null)
                break label0;
            Iterator iterator = mWifiManager.getConfiguredNetworks().iterator();
            do
            {
                obj = s;
                if(!iterator.hasNext())
                    break label0;
                obj = (WifiConfiguration)iterator.next();
            } while(((WifiConfiguration) (obj)).networkId != wifiinfo.getNetworkId());
            obj = ((WifiConfiguration) (obj)).SSID;
        }
        wifiinfo = ((WifiInfo) (obj));
        if(obj != null)
        {
            wifiinfo = ((WifiInfo) (obj));
            if("<unknown ssid>".equals(obj))
                wifiinfo = null;
        }
        return wifiinfo;
    }

    public static void initDrawable(int i, Drawable drawable)
    {
    }

    public static boolean isDisabled(int i)
    {
        return sToggleDisabled[i];
    }

    public static boolean isListStyle(Context context)
    {
        return isListStyle(context, getUserId(context));
    }

    public static boolean isListStyle(Context context, int i)
    {
        boolean flag = false;
        if(android.provider.Settings.System.getIntForUser(context.getContentResolver(), "status_bar_style_type", 0, i) == 0)
            flag = true;
        return flag;
    }

    private static boolean isMiPad()
    {
        return Build.IS_TABLET;
    }

    private boolean isSyncOn()
    {
        boolean flag;
        try
        {
            flag = ((Boolean)android/content/ContentResolver.getMethod("getMasterSyncAutomaticallyAsUser", new Class[] {
                Integer.TYPE
            }).invoke(null, new Object[] {
                Integer.valueOf(mCurrentUserId)
            })).booleanValue();
        }
        catch(Exception exception)
        {
            Log.i("ToggleManager", "getMasterSyncAutomaticallyAsUser not found.");
            return ContentResolver.getMasterSyncAutomatically();
        }
        return flag;
    }

    public static boolean isValid(Context context, int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i < 32)
            {
                flag1 = flag;
                if(getName(i) != 0)
                    flag1 = true;
            }
        }
        return flag1;
    }

    private boolean longClickScreenshot()
    {
        Object obj = null;
        String s = null;
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Screenshots");
        File file1 = obj;
        if(file.exists())
        {
            file1 = obj;
            if(file.isDirectory())
            {
                File afile[] = file.listFiles(new FilenameFilter() {

                    public boolean accept(File file2, String s1)
                    {
                        file2 = s1.toLowerCase();
                        return file2.endsWith("png") || file2.endsWith("jpg") || file2.endsWith("jpeg");
                    }

                    final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super();
            }
                }
);
                if(afile == null)
                    return false;
                long l = 0L;
                int i = afile.length;
                int j = 0;
                do
                {
                    file1 = s;
                    if(j >= i)
                        break;
                    file1 = afile[j];
                    long l1 = l;
                    if(file1.lastModified() > l)
                    {
                        l1 = file1.lastModified();
                        s = file1.getAbsolutePath();
                    }
                    j++;
                    l = l1;
                } while(true);
            }
        }
        if(TextUtils.isEmpty(file1))
        {
            return false;
        } else
        {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(new File(file1)), "image/*");
            intent.setFlags(0x10000000);
            mContext.startActivityAsUser(intent, UserHandle.CURRENT);
            return true;
        }
    }

    public static Uri maybeAddUserId(Uri uri, int i)
    {
        if(android.os.Build.VERSION.SDK_INT < 21)
            return uri;
        if(uri == null)
            return null;
        if(i != -2 && "content".equals(uri.getScheme()) && !uriHasUserId(uri))
        {
            android.net.Uri.Builder builder = uri.buildUpon();
            builder.encodedAuthority((new StringBuilder()).append("").append(i).append("@").append(uri.getEncodedAuthority()).toString());
            return builder.build();
        } else
        {
            return uri;
        }
    }

    private void queryBrightnessStatus()
    {
        boolean flag = true;
        if(mBrightnessAutoAvailable)
        {
            if(1 != android.provider.Settings.System.getIntForUser(mResolver, "screen_brightness_mode", 0, mCurrentUserId))
                flag = false;
        } else
        {
            flag = false;
        }
        mBrightnessAutoMode = flag;
        mBrightnessManualLevel = android.provider.Settings.System.getIntForUser(mResolver, "screen_brightness", 102, mCurrentUserId);
        mBrightnessAutoLevel = ((android.provider.Settings.System.getFloatForUser(mResolver, "screen_auto_brightness_adj", 0.0F, mCurrentUserId) + 1.0F) * (float)RANGE) / 2.0F;
    }

    private void registerListener(boolean flag)
    {
        int i;
        UserHandle userhandle;
        IntentFilter intentfilter;
        if(flag)
            i = -1;
        else
            i = UserHandle.myUserId();
        if(flag)
            userhandle = UserHandle.ALL;
        else
            userhandle = new UserHandle(i);
        intentfilter = new IntentFilter();
        intentfilter.addAction("android.media.RINGER_MODE_CHANGED");
        intentfilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentfilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentfilter.addAction("android.net.wifi.STATE_CHANGE");
        intentfilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentfilter.addAction("android.intent.action.LOCALE_CHANGED");
        intentfilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        mContext.registerReceiverAsUser(mBroadcastReceiver, userhandle, intentfilter, null, null);
        intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentfilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentfilter.addDataScheme("package");
        mContext.registerReceiverAsUser(mPackageChangeReceiver, userhandle, intentfilter, null, null);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("status_bar_toggle_list_order_new"), false, mTogglOrderObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("status_bar_toggle_page_order"), false, mTogglOrderObserver, i);
        mStatusChangeListenerHandle = ContentResolver.addStatusChangeListener(0x7fffffff, mSyncStatusObserver);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("accelerometer_rotation"), false, mAccelerometerRotationObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.Global.getUriFor("airplane_mode_on"), false, mFlightModeObserver);
        mResolver.registerContentObserver(android.provider.Settings.Secure.getUriFor("location_providers_allowed"), false, mLocationAllowedObserver, i);
        mMobileDataUtils.registerContentObserver(mContext, mMobileDataEnableObserver);
        mResolver.registerContentObserver(android.provider.Settings.Secure.getUriFor("mobile_policy"), false, mMobilePolicyEnableObserver, i);
        if(!SUPPORT_AUTO_BRIGHTNESS_OPTIMIZE)
        {
            mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("screen_brightness"), false, mBrightnessObserver, i);
            mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("screen_auto_brightness_adj"), false, mBrightnessObserver, i);
        }
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("screen_brightness_mode"), false, mBrightnessObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.Secure.getUriFor("screen_buttons_state"), false, mScreenButtonStateObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.Global.getUriFor("torch_state"), false, mTorchEnableObserver, -1);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("vibrate_in_silent"), false, mVibrateEnableObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("vibrate_in_normal"), false, mVibrateEnableObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("power_mode"), false, mPowerModeObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("POWER_SAVE_MODE_OPEN"), false, mBatterySaverObserver, i);
        ExtraTelephony.registerQuietModeEnableListener(mContext, mQuietModeObserver);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("screen_paper_mode_enabled"), false, mPaperModeObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.Global.getUriFor("key_midrop_enabled"), false, mMiDropObserver, i);
        updateAllToggles(mCurrentUserId);
    }

    private String removeDoubleQuotes(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        int i = s.length();
        if(i > 1 && s.charAt(0) == '"' && s.charAt(i - 1) == '"')
            return s.substring(1, i - 1);
        else
            return s;
    }

    private void setBrightnessMode()
    {
        ContentResolver contentresolver = mResolver;
        int i;
        if(mBrightnessAutoMode)
            i = 1;
        else
            i = 0;
        android.provider.Settings.System.putIntForUser(contentresolver, "screen_brightness_mode", i, mCurrentUserId);
    }

    public static void setUserSelectedToggleOrderStatic(Context context, ArrayList arraylist)
    {
        setUserSelectedToggleOrderStatic(context, arraylist, isListStyle(context));
    }

    public static void setUserSelectedToggleOrderStatic(Context context, ArrayList arraylist, boolean flag)
    {
        String s;
        StringBuilder stringbuilder;
        if(flag)
            s = "status_bar_toggle_list_order_new";
        else
            s = "status_bar_toggle_page_order";
        validateToggleOrder(context, arraylist, flag, getUserId(context));
        stringbuilder = new StringBuilder(96);
        for(int i = 0; i < arraylist.size(); i++)
        {
            stringbuilder.append(((Integer)arraylist.get(i)).toString());
            stringbuilder.append(" ");
        }

        android.provider.Settings.System.putStringForUser(context.getContentResolver(), s, stringbuilder.toString(), getUserId(context));
    }

    private void showToast(int i, int j)
    {
        showToast(((CharSequence) (mContext.getString(i))), j);
    }

    private void showToast(CharSequence charsequence, int i)
    {
        Toast toast = Toast.makeText(mContext, charsequence, i);
        toast.setType(2006);
        charsequence = toast.getWindowParams();
        charsequence.privateFlags = ((android.view.WindowManager.LayoutParams) (charsequence)).privateFlags | 0x10;
        toast.show();
    }

    private void toggleAccelerometer()
    {
        IWindowManager iwindowmanager = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        if(mAccelerometer) goto _L2; else goto _L1
_L1:
        iwindowmanager.thawRotation();
_L4:
        return;
_L2:
        int i = getRotation(iwindowmanager);
        if(i == 0 || 2 == i)
            break MISSING_BLOCK_LABEL_47;
        showToast(0x11080029, 1);
        iwindowmanager.freezeRotation(-1);
        continue; /* Loop/switch isn't completed */
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void toggleAutoBrightness()
    {
        if(mBrightnessAutoMode)
        {
            mBrightnessAutoMode = false;
        } else
        {
            boolean flag;
            if(mBrightnessAutoAvailable)
                flag = true;
            else
                flag = false;
            mBrightnessAutoMode = flag;
        }
        if(!mBrightnessAutoMode && "pinecone".equals(AUTO_BRIGHTNESS_OPTIMIZE_STRATEGY))
        {
            ObjectReference objectreference = ReflectionUtils.tryCallMethod(android.os.IPowerManager.Stub.asInterface(ServiceManager.getService("power")), "getScreenBrightness", java/lang/Integer, new Object[0]);
            if(objectreference != null)
                android.provider.Settings.System.putIntForUser(mResolver, "screen_brightness", ((Integer)objectreference.get()).intValue(), mCurrentUserId);
        }
        setBrightnessMode();
    }

    private void toggleBatterySaverToggle()
    {
        mBatterySaveMode = mBatterySaveMode ^ true;
        Bundle bundle = new Bundle();
        bundle.putBoolean("POWER_SAVE_MODE_OPEN", mBatterySaveMode);
        Uri uri = maybeAddUserId(Uri.parse("content://com.miui.powercenter.powersaver"), mCurrentUserId);
        mResolver.call(uri, "changePowerMode", null, bundle);
    }

    private void toggleBluetooth()
    {
        boolean flag = false;
        mBluetoothEnable = mBluetoothEnable ^ true;
        updateToggleStatus(2, mBluetoothEnable);
        int i;
        Object obj;
        Handler handler;
        if(mBluetoothEnable)
            i = 0x11020098;
        else
            i = 0x11020097;
        updateToggleImage(2, i);
        mHandler.removeMessages(1);
        mBgHandler.removeMessages(2);
        obj = mBgHandler;
        if(mBluetoothEnable)
            i = 1;
        else
            i = 0;
        obj = ((Handler) (obj)).obtainMessage(2, i, 0);
        handler = mBgHandler;
        i = ((flag) ? 1 : 0);
        if(mBluetoothDelay)
            i = 500;
        handler.sendMessageDelayed(((Message) (obj)), i);
    }

    private void toggleBluetooth(boolean flag)
    {
        boolean flag1 = true;
        if(ensureBluetoothAdapter())
        {
            int i = mBluetoothAdapter.getState();
            boolean flag2;
            if(i != 11)
            {
                if(i == 13)
                    flag2 = true;
                else
                    flag2 = false;
            } else
            {
                flag2 = true;
            }
            if(!flag2)
            {
                if(flag && i != 12)
                {
                    mBluetoothDelay = true;
                    mBluetoothAdapter.enable();
                } else
                if(!flag && i != 10)
                {
                    mBluetoothDelay = true;
                    mBluetoothAdapter.disable();
                } else
                {
                    mBluetoothDelay = false;
                }
            } else
            {
                Object obj = mBgHandler;
                int j;
                if(flag)
                    j = ((flag1) ? 1 : 0);
                else
                    j = 0;
                obj = ((Handler) (obj)).obtainMessage(2, j, 0);
                mBgHandler.sendMessageDelayed(((Message) (obj)), 100L);
            }
        }
    }

    private boolean toggleData()
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(isDisabled(1)) goto _L2; else goto _L1
_L1:
        if(!mMobilePolicyEnable) goto _L4; else goto _L3
_L3:
        mMobileDataEnable = mMobileDataEnable ^ true;
        mMobileDataUtils.enableMobileData(mContext, mMobileDataEnable);
        flag1 = flag;
_L2:
        return flag1;
_L4:
        Object obj = mMobileDataUtils.getSubscriberId(mContext);
        flag1 = flag;
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = NetworkTemplate.buildTemplateMobileAll(((String) (obj)));
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.networkassistant.ui.activity.NetworkOverLimitActivity"));
            intent.addFlags(0x10000000);
            intent.putExtra("android.net.NETWORK_TEMPLATE", ((android.os.Parcelable) (obj)));
            mContext.startActivityAsUser(intent, UserHandle.CURRENT);
            flag1 = true;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void toggleEdit()
    {
        String s;
        Intent intent;
        if(isListStyle(mContext))
            s = "com.android.settings.ToggleArrangementFragment";
        else
            s = "com.android.settings.TogglePositionFragment";
        intent = new Intent();
        intent.setFlags(0x14000000);
        intent.setAction("android.intent.action.MAIN");
        intent.putExtra(":android:show_fragment", s);
        intent.putExtra(":android:no_headers", true);
        intent.setClassName("com.android.settings", "com.android.settings.SubSettings");
        mContext.startActivityAsUser(intent, UserHandle.CURRENT);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("ToggleManager", (new StringBuilder()).append("toggleEdit() Exception=").append(exception).toString());
          goto _L1
    }

    private void toggleFlightMode()
    {
        mFlightMode = mFlightMode ^ true;
        Object obj = mResolver;
        int i;
        if(mFlightMode)
            i = 1;
        else
            i = 0;
        android.provider.Settings.Global.putInt(((ContentResolver) (obj)), "airplane_mode_on", i);
        obj = new Intent("android.intent.action.AIRPLANE_MODE");
        ((Intent) (obj)).addFlags(0x20000000);
        ((Intent) (obj)).putExtra("state", mFlightMode);
        mContext.sendBroadcastAsUser(((Intent) (obj)), UserHandle.ALL);
    }

    private void toggleGps()
    {
        android.provider.Settings.Secure.setLocationProviderEnabledForUser(mResolver, "gps", mGpsEnable ^ true, mCurrentUserId);
    }

    private void toggleMiDrop()
    {
        if(!isDisabled(27))
        {
            mMiDropChanging = true;
            boolean flag = isDisplayMiDropOn();
            Object obj;
            StringBuilder stringbuilder;
            if(flag)
                obj = "miui.intent.action.midrop_off";
            else
                obj = "miui.intent.action.midrop_on";
            obj = new Intent(((String) (obj)));
            ((Intent) (obj)).setComponent(new ComponentName("com.xiaomi.midrop", "com.xiaomi.midrop.startup.StartupReceiver"));
            ((Intent) (obj)).addFlags(0x10000000);
            mContext.sendBroadcastAsUser(((Intent) (obj)), UserHandle.CURRENT);
            stringbuilder = (new StringBuilder()).append("MiDrop: toggle MiDrop to ");
            if(flag)
                obj = "OFF";
            else
                obj = "ON";
            Log.d("ToggleManager", stringbuilder.append(((String) (obj))).toString());
            updateMiDropToggle(true);
        }
    }

    private void togglePaperMode()
    {
        mPaperMode = mPaperMode ^ true;
        ContentResolver contentresolver = mResolver;
        int i;
        if(mPaperMode)
            i = 1;
        else
            i = 0;
        android.provider.Settings.System.putIntForUser(contentresolver, "screen_paper_mode_enabled", i, mCurrentUserId);
    }

    private void togglePowerMode()
    {
        Intent intent;
        if("high".equals(mPowerMode))
            mPowerMode = "middle";
        else
            mPowerMode = "high";
        SystemProperties.set("persist.sys.aries.power_profile", mPowerMode);
        android.provider.Settings.System.putStringForUser(mResolver, "power_mode", mPowerMode, mCurrentUserId);
        intent = new Intent("miui.intent.action.POWER_MODE_CHANGE");
        mContext.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }

    private void toggleQuietMode()
    {
        int i = 1;
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
        {
            int j;
            Context context;
            if(mZenMode != 1)
                j = 1;
            else
                j = 0;
            context = mContext;
            if(j == 0)
                i = 0;
            android.provider.MiuiSettings.SilenceMode.setSilenceMode(context, i, null);
            if(j != 0)
                j = 1;
            else
                j = 0;
            android.provider.MiuiSettings.SilenceMode.reportRingerModeInfo("silence_DND", android.provider.MiuiSettings.SilenceMode.MISTAT_RINGERMODE_LIST[j], "0", System.currentTimeMillis());
            return;
        } else
        {
            mQuietMode = mQuietMode ^ true;
            android.provider.MiuiSettings.AntiSpam.setQuietMode(mContext, mQuietMode);
            return;
        }
    }

    private void toggleRinger()
    {
        byte byte0 = 4;
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
        {
            byte byte1;
            Context context;
            if(mZenMode != 4)
                byte1 = 1;
            else
                byte1 = 0;
            context = mContext;
            if(byte1 == 0)
                byte0 = 0;
            android.provider.MiuiSettings.SilenceMode.setSilenceMode(context, byte0, null);
            if(byte1 != 0)
                byte1 = 4;
            else
                byte1 = 0;
            android.provider.MiuiSettings.SilenceMode.reportRingerModeInfo("silence_DND", android.provider.MiuiSettings.SilenceMode.MISTAT_RINGERMODE_LIST[byte1], "0", System.currentTimeMillis());
            return;
        } else
        {
            AudioManagerHelper.toggleSilent(mContext, 4);
            return;
        }
    }

    private void toggleScreenButtonState()
    {
        int i = 0x110800c1;
        mScreenButtonDisabled = mScreenButtonDisabled ^ true;
        ContentResolver contentresolver;
        if(android.provider.Settings.Secure.getIntForUser(mResolver, "screen_buttons_has_been_disabled", 0, mCurrentUserId) == 0)
        {
            android.provider.Settings.Secure.putIntForUser(mResolver, "screen_buttons_has_been_disabled", 1, mCurrentUserId);
            AlertDialog alertdialog = (new android.app.AlertDialog.Builder(mContext, miui.R.style.Theme_Light_Dialog_Alert)).setMessage(0x110800c1).setPositiveButton(0x104000a, null).create();
            alertdialog.getWindow().setType(2010);
            alertdialog.getWindow().addPrivateFlags(16);
            alertdialog.show();
        } else
        {
            if(!mScreenButtonDisabled)
                i = 0x110800c0;
            showToast(i, 0);
        }
        contentresolver = mResolver;
        if(mScreenButtonDisabled)
            i = 1;
        else
            i = 0;
        android.provider.Settings.Secure.putIntForUser(contentresolver, "screen_buttons_state", i, mCurrentUserId);
    }

    private void toggleScreenshot()
    {
        ((StatusBarManager)mContext.getSystemService("statusbar")).collapsePanels();
        mContext.sendBroadcastAsUser(new Intent("android.intent.action.CAPTURE_SCREENSHOT"), UserHandle.CURRENT);
    }

    private void toggleSync()
    {
        mBgHandler.removeMessages(5);
        mBgHandler.sendEmptyMessage(5);
    }

    private void toggleSyncIntenal()
    {
        android/content/ContentResolver.getMethod("setMasterSyncAutomaticallyAsUser", new Class[] {
            Boolean.TYPE, Integer.TYPE
        }).invoke(null, new Object[] {
            Boolean.valueOf(isSyncOn() ^ true), Integer.valueOf(mCurrentUserId)
        });
_L1:
        return;
        Exception exception;
        exception;
        Log.i("ToggleManager", "setMasterSyncAutomaticallyAsUser not found.");
        ContentResolver.setMasterSyncAutomatically(ContentResolver.getMasterSyncAutomatically() ^ true);
          goto _L1
    }

    private void toggleTorch()
    {
        Intent intent = new Intent("miui.intent.action.TOGGLE_TORCH");
        intent.putExtra("miui.intent.extra.IS_TOGGLE", true);
        mContext.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }

    private void toggleVibrate()
    {
        AudioManagerHelper.toggleVibrateSetting(mContext);
    }

    private void updateAccelerometerToggle()
    {
        boolean flag = false;
        if(android.provider.Settings.System.getIntForUser(mResolver, "accelerometer_rotation", 0, mCurrentUserId) != 0)
            flag = true;
        mAccelerometer = flag;
        updateToggleStatus(3, mAccelerometer ^ true);
        int i;
        if(mAccelerometer)
            i = 0x110200b9;
        else
            i = 0x110200ba;
        updateToggleImage(3, i);
    }

    private void updateBatterySaverToggle()
    {
        boolean flag = false;
        Log.d("ToggleManager", (new StringBuilder()).append("updateBatterySaverToggle() old mode=").append(mBatterySaveMode).toString());
        if(android.provider.Settings.System.getIntForUser(mResolver, "POWER_SAVE_MODE_OPEN", 0, mCurrentUserId) != 0)
            flag = true;
        mBatterySaveMode = flag;
        Log.d("ToggleManager", (new StringBuilder()).append("updateBatterySaverToggle() new mode=").append(mBatterySaveMode).toString());
        updateToggleStatus(30, mBatterySaveMode);
        updateToggleImage(30, getImageResource(30, mBatterySaveMode));
    }

    private void updateBluetoothToggle()
    {
        if(ensureBluetoothAdapter())
        {
            int i = mBluetoothAdapter.getState();
            boolean flag;
            if(mBluetoothAdapter.isEnabled() || i == 11)
                flag = true;
            else
                flag = false;
            if(mBluetoothDelay)
                flag = mBluetoothEnable;
            mBluetoothEnable = flag;
            updateToggleStatus(2, mBluetoothEnable);
            updateToggleDisabled(2, false);
            if(i == 12 || i == 10)
            {
                int j;
                if(mBluetoothEnable)
                    j = 0x11020098;
                else
                    j = 0x11020097;
                updateToggleImage(2, j);
                if(mBluetoothDelay)
                {
                    mBluetoothDelay = false;
                    mHandler.removeMessages(1);
                    mHandler.sendEmptyMessageDelayed(1, 1000L);
                }
            }
        }
    }

    private void updateBrightnessToggle()
    {
        int i;
        if(mBrightnessAutoMode)
            i = 0x11020099;
        else
            i = 0x1102009a;
        updateToggleStatus(22, mBrightnessAutoMode);
        updateToggleImage(22, i);
    }

    private void updateDataToggle()
    {
        mMobileDataEnable = mMobileDataUtils.isMobileEnable(mContext);
        boolean flag;
        boolean flag1;
        int i;
        if(mMobileDataEnable && mMobilePolicyEnable)
        {
            if(!mFlightMode)
                flag = mIsSimMissing;
            else
                flag = true;
            flag ^= true;
        } else
        {
            flag = false;
        }
        Log.d("ToggleManager", (new StringBuilder()).append("mMobileDataEnable=").append(mMobileDataEnable).append(";mMobilePolicyEnable=").append(mMobilePolicyEnable).append(";mFlightMode=").append(mFlightMode).append(";mIsSimMissing=").append(mIsSimMissing).toString());
        updateToggleStatus(1, flag);
        if(!mFlightMode)
            flag1 = mIsSimMissing;
        else
            flag1 = true;
        updateToggleDisabled(1, flag1);
        if(flag)
            i = 0x1102009e;
        else
            i = 0x1102009d;
        updateToggleImage(1, i);
    }

    private void updateFlightModeToggle()
    {
        boolean flag = false;
        if(android.provider.Settings.Global.getInt(mResolver, "airplane_mode_on", 0) != 0)
            flag = true;
        mFlightMode = flag;
        updateToggleStatus(9, mFlightMode);
        int i;
        if(mFlightMode)
            i = 0x110200a4;
        else
            i = 0x110200a3;
        updateToggleImage(9, i);
        updateDataToggle();
    }

    private void updateGpsToggle()
    {
        mGpsEnable = android.provider.Settings.Secure.isLocationProviderEnabledForUser(mResolver, "gps", mCurrentUserId);
        updateToggleStatus(7, mGpsEnable);
        int i;
        if(mGpsEnable)
            i = 0x110200a6;
        else
            i = 0x110200a5;
        updateToggleImage(7, i);
    }

    public static void updateImageView(int i, ImageView imageview)
    {
        updateImageView(i, imageview, 0);
    }

    public static void updateImageView(int i, ImageView imageview, int j)
    {
        if(imageview != null)
        {
            Drawable drawable = getImageDrawable(i, imageview.getContext());
            if(Color.alpha(j) != 0)
                drawable.setColorFilter(j, android.graphics.PorterDuff.Mode.SRC_IN);
            imageview.setImageDrawable(drawable);
            initDrawable(i, drawable);
        }
    }

    private void updateMiDropToggle()
    {
        boolean flag = false;
        int i = android.provider.Settings.Global.getInt(mResolver, "key_midrop_enabled", 0);
        if(i == 2 || i == 3)
            flag = true;
        mMiDropChanging = flag;
        Log.d("ToggleManager", (new StringBuilder()).append("MiDrop: isMiDropDisabled:").append(isDisabled(27)).append(" isWifiAPDisabled:").append(isDisabled(24)).append(" mMiDropChanging:").append(mMiDropChanging).append(" setting:").append(i).toString());
        updateMiDropToggle(true);
    }

    private void updatePaperModeToggle()
    {
        boolean flag = false;
        if(android.provider.Settings.System.getIntForUser(mResolver, "screen_paper_mode_enabled", 0, mCurrentUserId) != 0)
            flag = true;
        mPaperMode = flag;
        updateToggleStatus(26, mPaperMode);
        int i;
        if(mPaperMode)
            i = 0x110200b4;
        else
            i = 0x110200b3;
        updateToggleImage(26, i);
    }

    private void updatePowerModeToggle()
    {
        mPowerMode = android.provider.Settings.System.getStringForUser(mResolver, "power_mode", mCurrentUserId);
        if(TextUtils.isEmpty(mPowerMode))
            mPowerMode = "middle";
        boolean flag = "high".equals(mPowerMode);
        updateToggleStatus(23, flag);
        int i;
        if(flag)
            i = 0x110200b6;
        else
            i = 0x110200b5;
        updateToggleImage(23, i);
    }

    private void updateQuietModeToggle()
    {
        boolean flag = true;
        mZenMode = android.provider.MiuiSettings.SilenceMode.getZenMode(mContext);
        int i;
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
        {
            if(mZenMode != 1)
                flag = false;
        } else
        {
            flag = android.provider.MiuiSettings.AntiSpam.isQuietModeEnable(mContext);
        }
        mQuietMode = flag;
        updateToggleStatus(25, mQuietMode);
        if(mQuietMode)
            i = 0x110200b8;
        else
            i = 0x110200b7;
        updateToggleImage(25, i);
    }

    private void updateScreenButtonState()
    {
        boolean flag = false;
        if(android.provider.Settings.Secure.getIntForUser(mResolver, "screen_buttons_state", 0, mCurrentUserId) != 0)
            flag = true;
        mScreenButtonDisabled = flag;
        updateToggleStatus(20, mScreenButtonDisabled);
        int i;
        if(mScreenButtonDisabled)
            i = 0x110200bb;
        else
            i = 0x110200bc;
        updateToggleImage(20, i);
    }

    private void updateSyncToggle()
    {
        mBgHandler.removeMessages(4);
        mBgHandler.sendEmptyMessage(4);
    }

    private void updateSyncToggle(boolean flag)
    {
        updateToggleStatus(8, flag);
        int i;
        if(flag)
            i = 0x110200c0;
        else
            i = 0x110200bf;
        updateToggleImage(8, i);
    }

    public static void updateTextView(int i, TextView textview)
    {
        if(textview != null)
            textview.setText(getStatusName(i, textview.getResources()));
    }

    private void updateTorchToggle()
    {
        boolean flag = true;
        int i;
        if(android.provider.Settings.Global.getInt(mContext.getContentResolver(), "torch_state", 0) != 1)
            flag = false;
        mTorchEnable = flag;
        updateToggleStatus(11, mTorchEnable);
        if(mTorchEnable)
            i = 0x110200c2;
        else
            i = 0x110200c1;
        updateToggleImage(11, i);
    }

    public static boolean uriHasUserId(Uri uri)
    {
        if(uri == null)
            return false;
        else
            return TextUtils.isEmpty(uri.getUserInfo()) ^ true;
    }

    private static void validateEditPositionInList(Context context, List list)
    {
        if(list.indexOf(Integer.valueOf(29)) != getEditFixedPosition(context) || list.lastIndexOf(Integer.valueOf(29)) != getEditFixedPosition(context))
        {
            for(; list.contains(Integer.valueOf(29)); list.remove(list.indexOf(Integer.valueOf(29))));
            if(list.size() <= getEditFixedPosition(context))
                list.add(Integer.valueOf(29));
            else
                list.add(getEditFixedPosition(context), Integer.valueOf(29));
        }
    }

    private static void validateEditPositionInPage(Context context, List list)
    {
        if(list.indexOf(Integer.valueOf(29)) != list.lastIndexOf(Integer.valueOf(29)) || list.indexOf(Integer.valueOf(29)) != list.size() - 1)
        {
            for(; list.contains(Integer.valueOf(29)); list.remove(list.indexOf(Integer.valueOf(29))));
            list.add(Integer.valueOf(29));
        }
    }

    private static void validateToggleList(Context context, ArrayList arraylist, int i)
    {
        addIfUnselected(arraylist, 1);
        addIfUnselected(arraylist, 15);
        addIfUnselected(arraylist, 11);
        addIfUnselected(arraylist, 5);
        addIfUnselected(arraylist, 18);
        addIfUnselected(arraylist, 2);
        addIfUnselected(arraylist, 22);
        addIfUnselected(arraylist, 9);
        addIfUnselected(arraylist, 10);
        addIfUnselected(arraylist, 3);
        addIfUnselected(arraylist, 27);
        addIfUnselected(arraylist, 7);
        addIfUnselected(arraylist, 6);
        addIfUnselected(arraylist, 24);
        addIfUnselected(arraylist, 26);
        addIfUnselected(arraylist, 8);
        addIfUnselected(arraylist, 25);
        addIfUnselected(arraylist, 23);
        addIfUnselected(arraylist, 20);
        addIfUnselected(arraylist, 28);
        addIfUnselected(arraylist, 30);
        filterToggle(context, arraylist, i);
        validateEditPositionInList(context, arraylist);
    }

    public static void validateToggleOrder(Context context, ArrayList arraylist, boolean flag, int i)
    {
        if(flag)
            validateToggleList(context, arraylist, i);
        else
            validateTogglePage(context, arraylist, i);
    }

    private static void validateTogglePage(Context context, ArrayList arraylist, int i)
    {
        addIfUnselected(arraylist, 5);
        addIfUnselected(arraylist, 3);
        addIfUnselected(arraylist, 10);
        addIfUnselected(arraylist, 2);
        addIfUnselected(arraylist, 1);
        addIfUnselected(arraylist, 15);
        addIfUnselected(arraylist, 27);
        addIfUnselected(arraylist, 22);
        addIfUnselected(arraylist, 11);
        addIfUnselected(arraylist, 9);
        addIfUnselected(arraylist, 18);
        addIfUnselected(arraylist, 6);
        addIfUnselected(arraylist, 28);
        addIfUnselected(arraylist, 26);
        addIfUnselected(arraylist, 25);
        addIfUnselected(arraylist, 7);
        addIfUnselected(arraylist, 20);
        addIfUnselected(arraylist, 23);
        addIfUnselected(arraylist, 24);
        addIfUnselected(arraylist, 8);
        addIfUnselected(arraylist, 30);
        filterToggle(context, arraylist, i);
        validateEditPositionInPage(context, arraylist);
    }

    private void verifyBluetoothState()
    {
        if(ensureBluetoothAdapter())
        {
            int i = mBluetoothAdapter.getState();
            boolean flag;
            if(mBluetoothAdapter.isEnabled() || i == 11)
                flag = true;
            else
                flag = false;
            if(mBluetoothEnable != flag)
            {
                mBluetoothEnable = flag;
                updateToggleStatus(2, mBluetoothEnable);
                int j;
                if(mBluetoothEnable)
                    j = 0x11020098;
                else
                    j = 0x11020097;
                updateToggleImage(2, j);
            }
        }
    }

    public void applyBrightness(int i, boolean flag)
    {
        mBgHandler.removeMessages(3);
        Handler handler = mBgHandler;
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        handler.obtainMessage(3, i, j).sendToTarget();
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("  - ToggleManager ------");
        printwriter.println("  - wifi ---");
        printwriter.print("  mWifiEnable=");
        printwriter.println(mWifiEnable);
        printwriter.print("  mWifiConnected=");
        printwriter.println(mWifiConnected);
        printwriter.print("  mWifiChanging=");
        printwriter.println(mWifiChanging);
        printwriter.print("  mWifiSsid=");
        printwriter.println(mWifiSsid);
        printwriter.println("  - data ---");
        printwriter.print("  mMobileDataEnable=");
        printwriter.println(mMobileDataEnable);
        printwriter.print("  mMobilePolicyEnable=");
        printwriter.println(mMobilePolicyEnable);
        printwriter.print("  mIsSimMissing=");
        printwriter.println(mIsSimMissing);
        printwriter.print("  mFlightMode=");
        printwriter.println(mFlightMode);
        printwriter.println("  - toggles ---");
        for(int i = 0; i < 32; i++)
        {
            printwriter.print("  Toggle:");
            printwriter.print(i);
            printwriter.print("  Status:");
            printwriter.println(sToggleStatus[i]);
        }

        if(mToggleChangedListener.size() > 0)
        {
            printwriter.println("  - listeners ---");
            for(int j = mToggleChangedListener.size() - 1; j >= 0; j--)
            {
                filedescriptor = (OnToggleChangedListener)((WeakReference)mToggleChangedListener.get(j)).get();
                printwriter.print("  listener:");
                printwriter.println(filedescriptor);
            }

        }
    }

    public int getCurBrightness()
    {
        if(mBrightnessAutoMode && USE_SCREEN_AUTO_BRIGHTNESS_ADJUSTMENT)
        {
            if("pinecone".equals(AUTO_BRIGHTNESS_OPTIMIZE_STRATEGY))
            {
                int i = ((PowerManager)mContext.getSystemService("power")).getDefaultScreenBrightnessSetting();
                android.os.IPowerManager ipowermanager = android.os.IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
                ObjectReference objectreference = null;
                if(ipowermanager != null)
                    objectreference = ReflectionUtils.tryCallMethod(ipowermanager, "getScreenBrightness", java/lang/Integer, new Object[0]);
                else
                    Slog.d("ToggleManager", "pm is null");
                if(objectreference != null)
                    i = ((Integer)objectreference.get()).intValue();
                return i - MINIMUM_BACKLIGHT;
            } else
            {
                return (int)mBrightnessAutoLevel;
            }
        } else
        {
            return mBrightnessManualLevel - MINIMUM_BACKLIGHT;
        }
    }

    public boolean isBrightnessAutoMode()
    {
        return mBrightnessAutoMode;
    }

    boolean isDisplayMiDropOn()
    {
        boolean flag = true;
        int i = android.provider.Settings.Global.getInt(mResolver, "key_midrop_enabled", 0);
        if(i != 2)
            if(i == 1 && useWifiApForMiDrop())
                flag = mWifiApEnabler.isWifiApOn();
            else
                flag = false;
        return flag;
    }

    public void onDestroy()
    {
        mContext.unregisterReceiver(mBroadcastReceiver);
        mContext.unregisterReceiver(mPackageChangeReceiver);
        mResolver.unregisterContentObserver(mTogglOrderObserver);
        mResolver.unregisterContentObserver(mMobileDataEnableObserver);
        mResolver.unregisterContentObserver(mMobilePolicyEnableObserver);
        mResolver.unregisterContentObserver(mTorchEnableObserver);
        mResolver.unregisterContentObserver(mScreenButtonStateObserver);
        mResolver.unregisterContentObserver(mLocationAllowedObserver);
        mResolver.unregisterContentObserver(mAccelerometerRotationObserver);
        mResolver.unregisterContentObserver(mBrightnessObserver);
        mResolver.unregisterContentObserver(mVibrateEnableObserver);
        mResolver.unregisterContentObserver(mPowerModeObserver);
        mResolver.unregisterContentObserver(mBatterySaverObserver);
        ExtraTelephony.unRegisterQuietModeEnableListener(mContext, mQuietModeObserver);
        mResolver.unregisterContentObserver(mPaperModeObserver);
        ContentResolver.removeStatusChangeListener(mStatusChangeListenerHandle);
        mToggleChangedListener.clear();
        mToggleOrderChangedListener.clear();
        if(mWifiApEnabler != null)
            mWifiApEnabler.unregisterReceiver();
        mBgThread.quitSafely();
    }

    public boolean performToggle(int i)
    {
        boolean flag;
        boolean flag1;
        StringBuilder stringbuilder = (new StringBuilder()).append("performToggle:").append(i).append(" state:");
        Object obj;
        if(isValid(mContext, i))
            obj = Boolean.valueOf(sToggleStatus[i]);
        else
            obj = "";
        Log.d("ToggleManager", stringbuilder.append(obj).toString());
        flag = false;
        flag1 = flag;
        i;
        JVM INSTR tableswitch 1 30: default 200
    //                   1 236
    //                   2 225
    //                   3 304
    //                   4 204
    //                   5 293
    //                   6 358
    //                   7 256
    //                   8 336
    //                   9 245
    //                   10 267
    //                   11 347
    //                   12 204
    //                   13 204
    //                   14 204
    //                   15 369
    //                   16 204
    //                   17 204
    //                   18 326
    //                   19 204
    //                   20 315
    //                   21 204
    //                   22 214
    //                   23 380
    //                   24 402
    //                   25 425
    //                   26 436
    //                   27 447
    //                   28 458
    //                   29 468
    //                   30 391;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L5 _L5 _L5 _L13 _L5 _L5 _L14 _L5 _L15 _L5 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        flag1 = flag;
_L26:
        return flag1;
_L16:
        toggleAutoBrightness();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L3:
        toggleBluetooth();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L2:
        flag1 = toggleData();
        continue; /* Loop/switch isn't completed */
_L10:
        toggleFlightMode();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L8:
        toggleGps();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L11:
        ((PowerManager)mContext.getSystemService("power")).goToSleep(SystemClock.uptimeMillis());
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L6:
        toggleRinger();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L4:
        toggleAccelerometer();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L15:
        toggleScreenButtonState();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L14:
        toggleScreenshot();
        flag1 = true;
        continue; /* Loop/switch isn't completed */
_L9:
        toggleSync();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L12:
        toggleTorch();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L7:
        toggleVibrate();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L13:
        toggleWifi();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L17:
        togglePowerMode();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L24:
        toggleBatterySaverToggle();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L18:
        flag1 = flag;
        if(mWifiApEnabler != null)
        {
            mWifiApEnabler.toggleWifiAp();
            flag1 = flag;
        }
        continue; /* Loop/switch isn't completed */
_L19:
        toggleQuietMode();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L20:
        togglePaperMode();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L21:
        toggleMiDrop();
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L22:
        toggleCast();
        flag1 = true;
        continue; /* Loop/switch isn't completed */
_L23:
        toggleEdit();
        flag1 = true;
        if(true) goto _L26; else goto _L25
_L25:
    }

    public void removeToggleChangedListener(OnToggleChangedListener ontogglechangedlistener)
    {
        for(int i = mToggleChangedListener.size() - 1; i >= 0; i--)
        {
            WeakReference weakreference = (WeakReference)mToggleChangedListener.get(i);
            if(weakreference.get() == null || ontogglechangedlistener.equals(weakreference.get()))
                mToggleChangedListener.remove(i);
        }

    }

    public void removeToggleOrderChangeListener(OnToggleOrderChangedListener ontoggleorderchangedlistener)
    {
        mToggleOrderChangedListener.remove(ontoggleorderchangedlistener);
    }

    public void setOnToggleChangedListener(OnToggleChangedListener ontogglechangedlistener)
    {
        mToggleChangedListener.add(new WeakReference(ontogglechangedlistener));
    }

    public void setOnToggleOrderChangeListener(OnToggleOrderChangedListener ontoggleorderchangedlistener)
    {
        mToggleOrderChangedListener.add(ontoggleorderchangedlistener);
    }

    public void setUserSelectedToggleOrder(ArrayList arraylist)
    {
        setUserSelectedToggleOrderStatic(mContext, arraylist);
    }

    public boolean startLongClickAction(int i)
    {
        if(18 == i)
            return longClickScreenshot();
        if(1 == i && (isDisabled(i) || CrossUserUtils.getCurrentUserId() != 0))
            return false;
        int j = sLongClickActions[i];
        if(j == 0)
            return false;
        Object obj = mContext.getResources().getString(j);
        if(obj == null)
            return false;
        obj = ComponentName.unflattenFromString(((String) (obj)));
        if(obj == null)
            return false;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(((ComponentName) (obj)));
        if(1 == i)
            intent.putExtra(":miui:starting_window_label", "");
        intent.setFlags(0x14000000);
        try
        {
            mContext.startActivityAsUser(intent, UserHandle.CURRENT);
        }
        catch(Exception exception)
        {
            Log.e("ToggleManager", (new StringBuilder()).append("start activity exception, component = ").append(obj).toString());
        }
        return true;
    }

    void toggleCast()
    {
        mContext.startActivityAsUser(getCastIntent(), UserHandle.CURRENT);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    void toggleWifi()
    {
        if(!isDisabled(15))
        {
            int i;
            int j;
            Handler handler;
            if(mWifiManager.getWifiState() != 3)
                i = 1;
            else
                i = 0;
            j = mWifiManager.getWifiApState();
            if(i != 0 && ConnectivityManagerReflector.getWifiStaSapConcurrency(mWifiManager) ^ true && (j == 12 || j == 13) && mWifiApEnabler != null)
                mWifiApEnabler.setSoftapEnabled(false);
            mBgHandler.removeMessages(1);
            handler = mBgHandler;
            if(i != 0)
                i = 1;
            else
                i = 0;
            handler.obtainMessage(1, i, 0).sendToTarget();
        }
    }

    public void updateAllToggles(int i)
    {
        mCurrentUserId = i;
        queryBrightnessStatus();
        updateBluetoothToggle();
        updateRingerToggle();
        updateWifiToggle(null);
        updateSyncToggle();
        updateAccelerometerToggle();
        updateFlightModeToggle();
        updateGpsToggle();
        mMobilePolicyEnableObserver.onChange(true);
        updateBrightnessToggle();
        updateScreenButtonState();
        updateTorchToggle();
        updateVibrateToggle();
        updatePowerModeToggle();
        updateBatterySaverToggle();
        updateQuietModeToggle();
        updatePaperModeToggle();
        updateMiDropToggle();
    }

    void updateMiDropToggle(boolean flag)
    {
        if(flag && useWifiApForMiDrop())
            mWifiApEnabler.updateWifiApToggle(false);
        boolean flag1 = isDisplayMiDropOn();
        int i;
        if(useWifiApForMiDrop())
            flag = mWifiApEnabler.isWifiApDisabled();
        else
            flag = false;
        Log.d("ToggleManager", (new StringBuilder()).append("MiDrop: updateMiDropToggle(boolean) isMiDropOn = ").append(flag1).append(" isWifiApDisabled = ").append(flag).append(" mMiDropChanging = ").append(mMiDropChanging).append(" mWifiChanging = ").append(mWifiChanging).toString());
        if(!flag && !mMiDropChanging)
            flag = mWifiChanging;
        else
            flag = true;
        updateToggleDisabled(27, flag);
        updateToggleStatus(27, flag1);
        if(flag1)
            i = 0x110200aa;
        else
            i = 0x110200a9;
        updateToggleImage(27, i);
    }

    public void updateRingerToggle()
    {
        mZenMode = android.provider.MiuiSettings.SilenceMode.getZenMode(mContext);
        boolean flag;
        int i;
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
        {
            if(mZenMode == 4)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = AudioManagerHelper.isSilentEnabled(mContext);
        }
        updateToggleStatus(5, flag);
        if(flag)
            i = 0x110200ac;
        else
            i = 0x110200ab;
        updateToggleImage(5, i);
    }

    protected void updateToggleDisabled(int i, boolean flag)
    {
        sToggleDisabled[i] = flag;
    }

    protected void updateToggleImage(int i, int j)
    {
        sToggleImages[i] = j;
        if(mToggleChangedListener.size() > 0)
        {
            j = mToggleChangedListener.size() - 1;
            while(j >= 0) 
            {
                OnToggleChangedListener ontogglechangedlistener = (OnToggleChangedListener)((WeakReference)mToggleChangedListener.get(j)).get();
                if(ontogglechangedlistener == null)
                {
                    Log.w("ToggleManager", (new StringBuilder()).append("listener is null:").append(j).toString());
                    mToggleChangedListener.remove(j);
                } else
                {
                    ontogglechangedlistener.OnToggleChanged(i);
                }
                j--;
            }
        }
    }

    protected void updateToggleStatus(int i, boolean flag)
    {
        sToggleStatus[i] = flag;
    }

    protected void updateToggleStatusName(int i, Object obj)
    {
        sToggleStatusNames.put(Integer.valueOf(i), obj);
    }

    public void updateVibrateToggle()
    {
        boolean flag = AudioManagerHelper.isVibrateEnabled(mContext);
        updateToggleStatus(6, flag);
        int i;
        if(flag)
            i = 0x110200c4;
        else
            i = 0x110200c3;
        updateToggleImage(6, i);
    }

    void updateWifiToggle(Intent intent)
    {
        byte byte0 = -1;
        String s;
        int i;
        if(intent != null)
            s = intent.getAction();
        else
            s = "";
        i = byte0;
        if(intent != null)
            if("android.net.wifi.WIFI_STATE_CHANGED".equals(s))
            {
                i = intent.getIntExtra("wifi_state", 4);
                boolean flag;
                if(i != 3)
                {
                    if(i == 2)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = true;
                }
                mWifiEnable = flag;
                if(i != 2)
                {
                    if(i == 0)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = true;
                }
                mWifiChanging = flag;
                updateMiDropToggle(false);
            } else
            {
                i = byte0;
                if("android.net.wifi.STATE_CHANGE".equals(s))
                {
                    NetworkInfo networkinfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
                    boolean flag1;
                    if(networkinfo != null)
                        flag1 = networkinfo.isConnected();
                    else
                        flag1 = false;
                    mWifiConnected = flag1;
                    if(mWifiConnected)
                    {
                        WifiInfo wifiinfo = (WifiInfo)intent.getParcelableExtra("wifiInfo");
                        intent = wifiinfo;
                        if(wifiinfo == null)
                            intent = mWifiManager.getConnectionInfo();
                        if(intent != null)
                            mWifiSsid = removeDoubleQuotes(huntForSsid(intent));
                        else
                            mWifiSsid = null;
                        mWifiEnable = true;
                        mWifiChanging = false;
                        i = byte0;
                    } else
                    {
                        mWifiSsid = null;
                        i = byte0;
                    }
                }
            }
        Log.d("ToggleManager", String.format("updateWifiToggle wifiState=%d mWifiConnected=%b action=%s", new Object[] {
            Integer.valueOf(i), Boolean.valueOf(mWifiConnected), s
        }));
        if(mWifiSsid == null)
            intent = mContext.getResources().getString(0x1108000d);
        else
            intent = mWifiSsid;
        updateToggleStatusName(15, intent);
        updateToggleStatus(15, mWifiEnable);
        updateToggleDisabled(15, mWifiChanging);
        if(mWifiEnable)
            i = 0x110200c8;
        else
            i = 0x110200c7;
        updateToggleImage(15, i);
    }

    boolean useWifiApForMiDrop()
    {
        boolean flag;
        flag = false;
        if(sHasMiDrop)
            break MISSING_BLOCK_LABEL_30;
        boolean flag1;
        if(mContext.getPackageManager().getApplicationInfo("com.xiaomi.midrop", 0) != null)
            flag1 = true;
        else
            flag1 = false;
        try
        {
            sHasMiDrop = flag1;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
        flag1 = flag;
        if(mWifiApEnabler != null)
            flag1 = sHasMiDrop;
        return flag1;
    }

    public static final int ALPHA_DEFAULT = 255;
    public static final int ALPHA_HALF = 125;
    public static final String AUTO_BRIGHTNESS_OPTIMIZE_STRATEGY = FeatureParser.getString("auto_brightness_optimize_strategy");
    public static final String COMPONENT_NAME_MIDROP_STARTUP_RECEIVER = "com.xiaomi.midrop.startup.StartupReceiver";
    public static final int DEFAULT_BACKLIGHT = 102;
    private static final int EXECUTE_TOGGLE_BLUETOOTH_DELAY_TIME = 500;
    private static final String KEY_POWER_MODE_OPEN = "POWER_SAVE_MODE_OPEN";
    public static final int MAXIMUM_BACKLIGHT = 255;
    public static final String META_DATA_KEY_FRAGMENT_CLASS = "com.android.settings.FRAGMENT_CLASS";
    private static final String METHOD_CHANGE_POWER_MODE = "changePowerMode";
    public static final int MINIMUM_BACKLIGHT;
    public static final String MIUI_BRIGHTNESS_OPT_STRATEGY = "miui";
    private static final int MSG_UPDATE_SYNC_TOGGLE = 2;
    private static final int MSG_VERIFY_BLUETOOTH_STATE = 1;
    public static final String PINECONE_BRIGHTNESS_OPT_STRATEGY = "pinecone";
    public static final String PKG_NAME_MIDROP = "com.xiaomi.midrop";
    private static final String POWER_MODE_URI_PATH = "content://com.miui.powercenter.powersaver";
    private static final String PROCESS_NAME_SYSTEM_UI = "com.android.systemui";
    public static final int RANGE;
    private static final String SETTINGS_MIDROP = "key_midrop_enabled";
    public static final boolean SUPPORT_AUTO_BRIGHTNESS_OPTIMIZE;
    static final String TAG = "ToggleManager";
    public static final int TOGGLE_ADVANCED_SYNC = 19;
    public static final int TOGGLE_AUTO_BRIGHTNESS = 22;
    public static final int TOGGLE_BATTERY_SAVER = 30;
    public static final int TOGGLE_BLUETOOTH = 2;
    public static final int TOGGLE_BRIGHTNESS = 4;
    public static final int TOGGLE_CAST = 28;
    public static final int TOGGLE_COUNT = 32;
    public static final int TOGGLE_DATA = 1;
    public static final int TOGGLE_DIVIDER = 0;
    public static final int TOGGLE_DRIVE_MODE = 21;
    public static final int TOGGLE_EDIT = 29;
    public static final int TOGGLE_FLIGHT_MODE = 9;
    public static final int TOGGLE_GPS = 7;
    public static final int TOGGLE_LOCK = 10;
    public static final int TOGGLE_MIDROP = 27;
    public static final int TOGGLE_NETWORK_TYPE = 17;
    public static final int TOGGLE_PAPER_MODE = 26;
    public static final int TOGGLE_POWER_MODE = 23;
    public static final int TOGGLE_QUIET_MODE = 25;
    public static final int TOGGLE_REBOOT = 12;
    public static final int TOGGLE_RINGER = 5;
    public static final int TOGGLE_ROTATE = 3;
    public static final int TOGGLE_SCREENSHOT = 18;
    public static final int TOGGLE_SCREEN_BUTTON = 20;
    public static final int TOGGLE_SHUTDOWN = 13;
    public static final int TOGGLE_SYNC = 8;
    public static final int TOGGLE_TORCH = 11;
    public static final int TOGGLE_VIBRATE = 6;
    public static final int TOGGLE_WIFI = 15;
    public static final int TOGGLE_WIFI_AP = 24;
    public static final boolean USE_SCREEN_AUTO_BRIGHTNESS_ADJUSTMENT = SystemProperties.getBoolean("persist.power.useautobrightadj", true);
    private static final int VERIFY_BLUETOOTH_STATE_DELAY_TIME = 1000;
    private static WifiApEnabler mWifiApEnabler;
    private static WifiManager mWifiManager;
    private static volatile boolean sHasCast = false;
    private static volatile boolean sHasGpsFeature = false;
    private static boolean sHasMiDrop;
    private static volatile boolean sHasMobileData = false;
    private static volatile boolean sHasVibrator = false;
    private static int sLongClickActions[];
    private static final int sRemoveByMultiUserList[] = {
        24, 27
    };
    private static volatile boolean sStaticFieldsInited = false;
    private static boolean sToggleDisabled[] = new boolean[32];
    private static int sToggleGeneralImages[];
    private static int sToggleImages[];
    private static ToggleManager sToggleManager = null;
    private static int sToggleNames[];
    private static int sToggleOffImages[];
    private static int sToggleOnImages[];
    private static boolean sToggleStatus[] = new boolean[32];
    private static HashMap sToggleStatusNames;
    private static HashMap sToggleStringToId;
    private static volatile boolean sWifiApAvailable = false;
    private boolean mAccelerometer;
    private final ContentObserver mAccelerometerRotationObserver;
    private boolean mBatterySaveMode;
    private final ContentObserver mBatterySaverObserver;
    private Handler mBgHandler;
    private HandlerThread mBgThread;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mBluetoothDelay;
    private boolean mBluetoothEnable;
    private boolean mBrightnessAutoAvailable;
    private float mBrightnessAutoLevel;
    private boolean mBrightnessAutoMode;
    private int mBrightnessManualLevel;
    private final ContentObserver mBrightnessObserver;
    private BroadcastReceiver mBroadcastReceiver;
    private Context mContext;
    private int mCurrentUserId;
    private boolean mFlightMode;
    private final ContentObserver mFlightModeObserver;
    private boolean mGpsEnable;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            boolean flag1;
            flag1 = true;
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 1 2: default 32
        //                       1 33
        //                       2 43;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            ToggleManager._2D_wrap20(ToggleManager.this);
            continue; /* Loop/switch isn't completed */
_L3:
            ToggleManager togglemanager = ToggleManager.this;
            if(message.arg1 != 1)
                flag1 = false;
            ToggleManager._2D_wrap18(togglemanager, flag1);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super(looper);
            }
    }
;
    private boolean mIsSimMissing;
    private final ContentObserver mLocationAllowedObserver;
    private boolean mMiDropChanging;
    private final ContentObserver mMiDropObserver;
    private boolean mMobileDataEnable;
    private final ContentObserver mMobileDataEnableObserver;
    private MobileDataUtils mMobileDataUtils;
    private boolean mMobilePolicyEnable;
    private final ContentObserver mMobilePolicyEnableObserver;
    private BroadcastReceiver mPackageChangeReceiver;
    private boolean mPaperMode;
    private final ContentObserver mPaperModeObserver;
    private String mPowerMode;
    private final ContentObserver mPowerModeObserver;
    private boolean mQuietMode;
    private final miui.provider.ExtraTelephony.QuietModeEnableListener mQuietModeObserver = new miui.provider.ExtraTelephony.QuietModeEnableListener() {

        public void onQuietModeEnableChange(boolean flag1)
        {
            ToggleManager._2D_wrap15(ToggleManager.this);
        }

        final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super();
            }
    }
;
    private final ContentResolver mResolver;
    private final Resources mResource;
    private boolean mScreenButtonDisabled;
    private final ContentObserver mScreenButtonStateObserver;
    private Object mStatusChangeListenerHandle;
    private final SyncStatusObserver mSyncStatusObserver = new SyncStatusObserver() {

        public void onStatusChanged(int j)
        {
            ToggleManager._2D_get3(ToggleManager.this).removeCallbacks(ToggleManager._2D_get7(ToggleManager.this));
            ToggleManager._2D_get3(ToggleManager.this).postDelayed(ToggleManager._2D_get7(ToggleManager.this), 300L);
        }

        final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super();
            }
    }
;
    private final ContentObserver mTogglOrderObserver;
    private List mToggleChangedListener;
    private List mToggleOrderChangedListener;
    private boolean mTorchEnable;
    private final ContentObserver mTorchEnableObserver;
    private final Runnable mUpdateSyncStateRunnable = new Runnable() {

        public void run()
        {
            ToggleManager._2D_wrap17(ToggleManager.this);
        }

        final ToggleManager this$0;

            
            {
                this$0 = ToggleManager.this;
                super();
            }
    }
;
    private final ContentObserver mVibrateEnableObserver;
    boolean mWifiChanging;
    boolean mWifiConnected;
    boolean mWifiEnable;
    String mWifiSsid;
    private int mZenMode;

    static 
    {
        SUPPORT_AUTO_BRIGHTNESS_OPTIMIZE = DeviceFeature.SUPPORT_AUTO_BRIGHTNESS_OPTIMIZE;
        sToggleStringToId = new HashMap();
        sToggleStatusNames = new HashMap();
        sToggleStringToId.put("bluetooth", Integer.valueOf(2));
        sToggleStringToId.put("brightness_mode", Integer.valueOf(22));
        sToggleStringToId.put("data", Integer.valueOf(1));
        sToggleStringToId.put("flight_mode", Integer.valueOf(9));
        sToggleStringToId.put("gps", Integer.valueOf(7));
        sToggleStringToId.put("lock", Integer.valueOf(10));
        sToggleStringToId.put("power_mode", Integer.valueOf(23));
        sToggleStringToId.put("quiet_mode", Integer.valueOf(25));
        sToggleStringToId.put("rotate", Integer.valueOf(3));
        sToggleStringToId.put("ringer_mode", Integer.valueOf(5));
        sToggleStringToId.put("screenshot", Integer.valueOf(18));
        sToggleStringToId.put("screen_button", Integer.valueOf(20));
        sToggleStringToId.put("sync_mode", Integer.valueOf(8));
        sToggleStringToId.put("torch", Integer.valueOf(11));
        sToggleStringToId.put("vibration_mode", Integer.valueOf(6));
        sToggleStringToId.put("wifi", Integer.valueOf(15));
        sToggleStringToId.put("wifi_ap", Integer.valueOf(24));
        sToggleStringToId.put("paper_mode", Integer.valueOf(26));
        sToggleStringToId.put("midrop", Integer.valueOf(27));
        sToggleStringToId.put("cast", Integer.valueOf(28));
        sToggleStringToId.put("battery_safer", Integer.valueOf(30));
        sToggleStringToId.put("edit", Integer.valueOf(29));
        sToggleNames = new int[32];
        sToggleNames[22] = 0x11080001;
        sToggleNames[2] = 0x11080000;
        sToggleNames[1] = 0x11080002;
        sToggleNames[9] = 0x11080003;
        sToggleNames[7] = 0x11080004;
        sToggleNames[10] = 0x11080005;
        sToggleNames[5] = 0x11080006;
        sToggleNames[23] = 0x1108000e;
        sToggleNames[3] = 0x11080007;
        sToggleNames[20] = 0x11080008;
        sToggleNames[18] = 0x11080009;
        sToggleNames[8] = 0x1108000a;
        sToggleNames[11] = 0x1108000b;
        sToggleNames[6] = 0x1108000c;
        sToggleNames[15] = 0x1108000d;
        sToggleNames[24] = 0x1108000f;
        sToggleNames[25] = 0x11080010;
        sToggleNames[26] = 0x11080011;
        sToggleNames[27] = 0x11080012;
        sToggleNames[28] = 0x11080013;
        sToggleNames[30] = 0x11080015;
        sToggleNames[29] = 0x11080014;
        for(int i = 0; i < 32; i++)
            sToggleStatusNames.put(Integer.valueOf(i), Integer.valueOf(sToggleNames[i]));

        sLongClickActions = new int[32];
        sLongClickActions[22] = 0x11080019;
        sLongClickActions[1] = 0x11080017;
        sLongClickActions[2] = 0x11080018;
        sLongClickActions[9] = 0x1108001a;
        sLongClickActions[7] = 0x1108001b;
        sLongClickActions[5] = 0x1108001c;
        sLongClickActions[23] = 0x11080021;
        sLongClickActions[3] = 0x1108001d;
        sLongClickActions[8] = 0x1108001e;
        sLongClickActions[6] = 0x1108001f;
        sLongClickActions[15] = 0x11080020;
        sLongClickActions[24] = 0x11080022;
        sLongClickActions[25] = 0x11080023;
        sLongClickActions[26] = 0x11080024;
        sLongClickActions[27] = 0x11080025;
        sLongClickActions[30] = 0x11080026;
        sToggleImages = new int[32];
        sToggleImages[22] = 0x11020099;
        sToggleImages[2] = 0x11020098;
        sToggleImages[1] = 0x1102009e;
        sToggleImages[9] = 0x110200a4;
        sToggleImages[7] = 0x110200a6;
        sToggleImages[5] = 0x110200ac;
        sToggleImages[23] = 0x110200b6;
        sToggleImages[3] = 0x110200ba;
        sToggleImages[20] = 0x110200bb;
        sToggleImages[8] = 0x110200c0;
        sToggleImages[11] = 0x110200c2;
        sToggleImages[6] = 0x110200c4;
        sToggleImages[15] = 0x110200c8;
        sToggleImages[24] = 0x110200c6;
        sToggleImages[25] = 0x110200b8;
        sToggleImages[26] = 0x110200b4;
        sToggleImages[27] = 0x110200aa;
        sToggleImages[30] = 0x11020096;
        sToggleImages[10] = 0x110200a7;
        sToggleImages[18] = 0x110200bd;
        sToggleImages[28] = 0x1102009b;
        sToggleImages[29] = 0x110200a2;
        sToggleGeneralImages = new int[32];
        for(int j = 0; j < 32; j++)
            sToggleGeneralImages[j] = sToggleImages[j];

        sToggleGeneralImages[10] = 0x110200a8;
        sToggleGeneralImages[18] = 0x110200be;
        sToggleGeneralImages[28] = 0x1102009c;
        sToggleOnImages = new int[32];
        for(int k = 0; k < 32; k++)
            sToggleOnImages[k] = sToggleImages[k];

        sToggleOffImages = new int[32];
        sToggleOffImages[22] = 0x1102009a;
        sToggleOffImages[2] = 0x11020097;
        sToggleOffImages[1] = 0x1102009d;
        sToggleOffImages[9] = 0x110200a3;
        sToggleOffImages[7] = 0x110200a5;
        sToggleOffImages[5] = 0x110200ab;
        sToggleOffImages[23] = 0x110200b5;
        sToggleOffImages[3] = 0x110200b9;
        sToggleOffImages[20] = 0x110200bc;
        sToggleOffImages[8] = 0x110200bf;
        sToggleOffImages[11] = 0x110200c1;
        sToggleOffImages[6] = 0x110200c3;
        sToggleOffImages[15] = 0x110200c7;
        sToggleOffImages[24] = 0x110200c5;
        sToggleOffImages[25] = 0x110200b7;
        sToggleOffImages[26] = 0x110200b3;
        sToggleOffImages[27] = 0x110200a9;
        sToggleOffImages[30] = 0x11020095;
        sToggleOffImages[10] = 0x110200a7;
        sToggleOffImages[18] = 0x110200bd;
        sToggleOffImages[28] = 0x1102009b;
        sToggleOffImages[29] = 0x110200a1;
        MINIMUM_BACKLIGHT = Resources.getSystem().getInteger(0x11070005);
        RANGE = 255 - MINIMUM_BACKLIGHT;
    }
}
