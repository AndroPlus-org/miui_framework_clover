// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.hardware.display;

import android.content.res.Resources;
import android.os.*;
import android.util.Slog;
import miui.os.DeviceFeature;
import miui.util.FeatureParser;

// Referenced classes of package miui.hardware.display:
//            DisplayFeatureServiceProxy

public class DisplayFeatureManager
{

    static String _2D_get0()
    {
        return TAG;
    }

    static Object _2D_get1(DisplayFeatureManager displayfeaturemanager)
    {
        return displayfeaturemanager.mLock;
    }

    static DisplayFeatureServiceProxy _2D_set0(DisplayFeatureManager displayfeaturemanager, DisplayFeatureServiceProxy displayfeatureserviceproxy)
    {
        displayfeaturemanager.mProxy = displayfeatureserviceproxy;
        return displayfeatureserviceproxy;
    }

    private DisplayFeatureManager()
    {
        mLock = new Object();
        initServiceDeathRecipient();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        initProxyLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static DisplayFeatureManager getInstance()
    {
        if(sInstance != null) goto _L2; else goto _L1
_L1:
        miui/hardware/display/DisplayFeatureManager;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            DisplayFeatureManager displayfeaturemanager = JVM INSTR new #2   <Class DisplayFeatureManager>;
            displayfeaturemanager.DisplayFeatureManager();
            sInstance = displayfeaturemanager;
        }
        miui/hardware/display/DisplayFeatureManager;
        JVM INSTR monitorexit ;
_L2:
        return sInstance;
        Exception exception;
        exception;
        throw exception;
    }

    private void initProxyLocked()
    {
        if(!DeviceFeature.SUPPORT_DISPLAYFEATURE_HIDL) goto _L2; else goto _L1
_L1:
        if(CONFIG_SERVICENAME_RESOURCEID != 0) goto _L4; else goto _L3
_L3:
        Object obj = "vendor.xiaomi.hardware.displayfeature@1.0::IDisplayFeature";
_L5:
        String s = TAG;
        StringBuilder stringbuilder = JVM INSTR new #130 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.d(s, stringbuilder.append("initProxyLoced CONFIG_SERVICENAME_RESOURCEID = ").append(CONFIG_SERVICENAME_RESOURCEID).append(" hidlServiceName = ").append(((String) (obj))).toString());
        obj = HwBinder.getService(((String) (obj)), "default");
        if(obj == null)
            break MISSING_BLOCK_LABEL_95;
        ((IHwBinder) (obj)).linkToDeath(mHwBinderDeathHandler, 10001L);
        DisplayFeatureServiceProxy displayfeatureserviceproxy = JVM INSTR new #171 <Class DisplayFeatureServiceProxy>;
        displayfeatureserviceproxy.DisplayFeatureServiceProxy(obj);
        mProxy = displayfeatureserviceproxy;
_L6:
        return;
_L4:
        obj = Resources.getSystem().getString(CONFIG_SERVICENAME_RESOURCEID);
          goto _L5
_L2:
        obj = ServiceManager.getService("DisplayFeatureControl");
        if(obj != null)
            try
            {
                ((IBinder) (obj)).linkToDeath(mBinderDeathHandler, 0);
                DisplayFeatureServiceProxy displayfeatureserviceproxy1 = JVM INSTR new #171 <Class DisplayFeatureServiceProxy>;
                displayfeatureserviceproxy1.DisplayFeatureServiceProxy(obj);
                mProxy = displayfeatureserviceproxy1;
            }
            catch(RemoteException remoteexception) { }
          goto _L6
    }

    private void initServiceDeathRecipient()
    {
        if(DeviceFeature.SUPPORT_DISPLAYFEATURE_HIDL)
            mHwBinderDeathHandler = new android.os.IHwBinder.DeathRecipient() {

                public void serviceDied(long l)
                {
                    Object obj = DisplayFeatureManager._2D_get1(DisplayFeatureManager.this);
                    obj;
                    JVM INSTR monitorenter ;
                    Slog.v(DisplayFeatureManager._2D_get0(), "hwbinder service binderDied!");
                    DisplayFeatureManager._2D_set0(DisplayFeatureManager.this, null);
                    obj;
                    JVM INSTR monitorexit ;
                    return;
                    Exception exception;
                    exception;
                    throw exception;
                }

                final DisplayFeatureManager this$0;

            
            {
                this$0 = DisplayFeatureManager.this;
                super();
            }
            }
;
        else
            mBinderDeathHandler = new android.os.IBinder.DeathRecipient() {

                public void binderDied()
                {
                    Object obj = DisplayFeatureManager._2D_get1(DisplayFeatureManager.this);
                    obj;
                    JVM INSTR monitorenter ;
                    Slog.v(DisplayFeatureManager._2D_get0(), "binder service binderDied!");
                    DisplayFeatureManager._2D_set0(DisplayFeatureManager.this, null);
                    obj;
                    JVM INSTR monitorexit ;
                    return;
                    Exception exception;
                    exception;
                    throw exception;
                }

                final DisplayFeatureManager this$0;

            
            {
                this$0 = DisplayFeatureManager.this;
                super();
            }
            }
;
    }

    public int getColorPrefer()
    {
        return SystemProperties.getInt("persist.sys.display_prefer", 2);
    }

    public int getScreenGamut()
    {
        return SystemProperties.getInt("persist.sys.gamut_mode", 0);
    }

    public boolean isAdEnable()
    {
        return SystemProperties.getBoolean("persist.sys.ltm_enable", true);
    }

    public void setScreenEffect(int i, int j)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        String s = TAG;
        StringBuilder stringbuilder = JVM INSTR new #130 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.d(s, stringbuilder.append("setScreenEffect mode=").append(i).append(" value=").append(j).toString());
        if(mProxy == null)
            initProxyLocked();
        if(mProxy != null)
            mProxy.setFeature(0, i, j, 255);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int CONFIG_SERVICENAME_RESOURCEID = Resources.getSystem().getIdentifier("config_displayFeatureHidlServiceName", "string", "android");
    public static final int DEFALUT_GAMUT_MODE = 0;
    public static final int DEFALUT_SCREEN_COLOR = 2;
    private static final String DISPLAY_FEATURE_SERVICE = "DisplayFeatureControl";
    public static final int EXT_COLOR_PROC_STATE = 15;
    private static final String HIDL_SERVICENAME_DEFAULT = "vendor.xiaomi.hardware.displayfeature@1.0::IDisplayFeature";
    public static final String PROPERTY_ASSERTIVE_DISPLAY = "persist.sys.ltm_enable";
    public static final String PROPERTY_GAMUT_MODE = "persist.sys.gamut_mode";
    public static final String PROPERTY_SCREEN_COLOR = "persist.sys.display_prefer";
    public static final String PROPERTY_SCREEN_SATURATION = "persist.sys.display_ce";
    public static final int SCREEN_ADAPT = 0;
    public static final int SCREEN_ENHANCE = 1;
    public static final int SCREEN_EYECARE = 3;
    public static final int SCREEN_HIGHLIGHT = 11;
    public static final int SCREEN_MONOCHROME = 4;
    public static final int SCREEN_NIGHTLIGHT = 9;
    public static final int SCREEN_STANDARD = 2;
    public static final int SCREEN_SUNLIGHT = 8;
    private static final boolean SUPPORT_SET_FEATURE = FeatureParser.getBoolean("support_screen_effect", false) ^ true;
    private static String TAG = "DisplayFeatureManager";
    private static volatile DisplayFeatureManager sInstance;
    private android.os.IBinder.DeathRecipient mBinderDeathHandler;
    private android.os.IHwBinder.DeathRecipient mHwBinderDeathHandler;
    private Object mLock;
    private DisplayFeatureServiceProxy mProxy;

}
