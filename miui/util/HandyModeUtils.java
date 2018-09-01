// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.Display;
import android.view.WindowManager;
import miui.os.Build;

public class HandyModeUtils
{

    private HandyModeUtils(Context context)
    {
        mContext = context.getApplicationContext();
        if(mContext == null)
            mContext = context;
        mScreenSize = getScreenSize();
    }

    private float calcScreenSizeToScale(float f)
    {
        return f / mScreenSize;
    }

    private float getDefaultScreenSize()
    {
        float f;
        if(getScreenSize() > 4.5F)
            f = 4F;
        else
            f = 3.5F;
        return f;
    }

    public static HandyModeUtils getInstance(Context context)
    {
        if(sInstance != null) goto _L2; else goto _L1
_L1:
        miui/util/HandyModeUtils;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            HandyModeUtils handymodeutils = JVM INSTR new #2   <Class HandyModeUtils>;
            handymodeutils.HandyModeUtils(context);
            sInstance = handymodeutils;
        }
        miui/util/HandyModeUtils;
        JVM INSTR monitorexit ;
_L2:
        return sInstance;
        context;
        throw context;
    }

    private float getScreenSize()
    {
        WindowManager windowmanager = (WindowManager)mContext.getSystemService("window");
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowmanager.getDefaultDisplay().getMetrics(displaymetrics);
        float f = (float)displaymetrics.widthPixels / displaymetrics.xdpi;
        float f1 = (float)displaymetrics.heightPixels / displaymetrics.ydpi;
        return FloatMath.sqrt(f * f + f1 * f1);
    }

    public static boolean isFeatureVisible()
    {
        boolean flag;
        if(SUPPORTED)
            flag = Build.IS_TABLET ^ true;
        else
            flag = false;
        return flag;
    }

    public float getScale()
    {
        return calcScreenSizeToScale(getSize());
    }

    public float getSize()
    {
        float f = getDefaultScreenSize();
        float f1 = android.provider.Settings.System.getFloatForUser(mContext.getContentResolver(), "handy_mode_size", f, 0);
        float f2 = f1;
        if(!isValidSize(f1))
            f2 = f;
        return f2;
    }

    public boolean hasShowed()
    {
        return true;
    }

    public boolean isEnable()
    {
        boolean flag;
        if(isFeatureVisible())
            flag = isHandyModeEnabled();
        else
            flag = false;
        return flag;
    }

    public boolean isEnterDirect()
    {
        return true;
    }

    public boolean isHandyModeEnabled()
    {
        boolean flag = false;
        if(android.provider.Settings.System.getIntForUser(mContext.getContentResolver(), "handy_mode_state", 0, 0) != 0)
            flag = true;
        return flag;
    }

    public boolean isValidSize(float f)
    {
        boolean flag;
        if(calcScreenSizeToScale(f) < 0.88F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setEnterDirect(boolean flag)
    {
        android.provider.MiuiSettings.System.putBoolean(mContext.getContentResolver(), "handy_mode_enter_direct", flag);
    }

    public void setHandyModeStateToSettings(boolean flag)
    {
        android.provider.MiuiSettings.System.putBooleanForUser(mContext.getContentResolver(), "handy_mode_state", flag, 0);
    }

    public void setSize(float f)
    {
        android.provider.Settings.System.putFloatForUser(mContext.getContentResolver(), "handy_mode_size", f, 0);
    }

    public static final boolean DEFAULT_IS_ENTER_DIRECT = false;
    static boolean SUPPORTED = SystemProperties.getBoolean("ro.miui.has_handy_mode_sf", false);
    private static volatile HandyModeUtils sInstance;
    private Context mContext;
    private float mScreenSize;

}
