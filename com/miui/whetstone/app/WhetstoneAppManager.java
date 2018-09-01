// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.SystemProperties;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.miui.whetstone.app:
//            WhetstoneApplicationThread

public class WhetstoneAppManager
{

    private WhetstoneAppManager()
    {
        mApplicationThread = new WhetstoneApplicationThread();
        mHasInit = false;
        checkInit();
    }

    private WhetstoneAppManager(Context context)
    {
        this();
        attach(context);
    }

    public static void addBitmap(Bitmap bitmap)
    {
    }

    public static void addBitmapDrawable(BitmapDrawable bitmapdrawable)
    {
    }

    public static void addDrawable(Drawable drawable)
    {
    }

    private void checkInit()
    {
    }

    public static WhetstoneAppManager getInstance()
    {
        com/miui/whetstone/app/WhetstoneAppManager;
        JVM INSTR monitorenter ;
        WhetstoneAppManager whetstoneappmanager1;
        if(_sInstance == null)
        {
            WhetstoneAppManager whetstoneappmanager = JVM INSTR new #2   <Class WhetstoneAppManager>;
            whetstoneappmanager.WhetstoneAppManager();
            _sInstance = whetstoneappmanager;
        }
        whetstoneappmanager1 = _sInstance;
        com/miui/whetstone/app/WhetstoneAppManager;
        JVM INSTR monitorexit ;
        return whetstoneappmanager1;
        Exception exception;
        exception;
        throw exception;
    }

    public static WhetstoneAppManager getInstance(Context context)
    {
        com/miui/whetstone/app/WhetstoneAppManager;
        JVM INSTR monitorenter ;
        if(_sInstance == null)
        {
            WhetstoneAppManager whetstoneappmanager = JVM INSTR new #2   <Class WhetstoneAppManager>;
            whetstoneappmanager.WhetstoneAppManager(context);
            _sInstance = whetstoneappmanager;
        }
        context = _sInstance;
        com/miui/whetstone/app/WhetstoneAppManager;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public static boolean getLeakCanaryWorksProperty()
    {
        return SystemProperties.get("persist.sys.mem_leak_debug", "false").equalsIgnoreCase("true");
    }

    public static boolean handleTrimMemory(int i)
    {
        return true;
    }

    public static boolean isEnable()
    {
        return false;
    }

    public static void restoreDirectBitmap(Bitmap bitmap)
    {
    }

    public static void setHardwareRendererIfNeeded()
    {
    }

    public static void setWhetstonePackageRecordInfo(List list, boolean flag)
    {
    }

    public static void trimHeapSizeIfNeeded(ApplicationInfo applicationinfo)
    {
    }

    public void attach(Context context)
    {
    }

    public void onCreate(Application application)
    {
    }

    public void onResume(Activity activity)
    {
    }

    private static final String TAG = "WhetstoneAppManager";
    private static final int UNINIT = -2;
    private static WhetstoneAppManager _sInstance;
    private static final Object sMutex = new Object();
    private static List sPackages = new ArrayList();
    private IBinder mApplicationThread;
    private Context mContex;
    public boolean mEnable;
    public boolean mHasInit;

}
