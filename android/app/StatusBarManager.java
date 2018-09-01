// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.os.*;
import android.util.Slog;
import com.android.internal.statusbar.IStatusBarService;

public class StatusBarManager
{

    StatusBarManager(Context context)
    {
        mToken = new Binder();
        mContext = context;
    }

    private IStatusBarService getService()
    {
        this;
        JVM INSTR monitorenter ;
        IStatusBarService istatusbarservice;
        if(mService == null)
        {
            mService = com.android.internal.statusbar.IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
            if(mService == null)
                Slog.w("StatusBarManager", "warning: no STATUS_BAR_SERVICE");
        }
        istatusbarservice = mService;
        this;
        JVM INSTR monitorexit ;
        return istatusbarservice;
        Exception exception;
        exception;
        throw exception;
    }

    public static String windowStateToString(int i)
    {
        if(i == 1)
            return "WINDOW_STATE_HIDING";
        if(i == 2)
            return "WINDOW_STATE_HIDDEN";
        if(i == 0)
            return "WINDOW_STATE_SHOWING";
        else
            return "WINDOW_STATE_UNKNOWN";
    }

    public void collapsePanels()
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_15;
        istatusbarservice.collapsePanels();
    }

    public void disable(int i)
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_27;
        istatusbarservice.disable(i, mToken, mContext.getPackageName());
    }

    public void disable2(int i)
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_27;
        istatusbarservice.disable2(i, mToken, mContext.getPackageName());
    }

    public void expandNotificationsPanel()
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_15;
        istatusbarservice.expandNotificationsPanel();
    }

    public void expandSettingsPanel()
    {
        expandSettingsPanel(null);
    }

    public void expandSettingsPanel(String s)
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_16;
        istatusbarservice.expandSettingsPanel(s);
    }

    public void removeIcon(String s)
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_16;
        istatusbarservice.removeIcon(s);
    }

    public void setIcon(String s, int i, int j, String s1)
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_30;
        istatusbarservice.setIcon(s, mContext.getPackageName(), i, j, s1);
    }

    public void setIconVisibility(String s, boolean flag)
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_17;
        istatusbarservice.setIconVisibility(s, flag);
    }

    public void setStatus(int i, String s, Bundle bundle)
    {
        IStatusBarService istatusbarservice;
        try
        {
            istatusbarservice = getService();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
        if(istatusbarservice == null)
            break MISSING_BLOCK_LABEL_25;
        istatusbarservice.setStatus(i, mToken, s, bundle);
    }

    public static final int CAMERA_LAUNCH_SOURCE_LIFT_TRIGGER = 2;
    public static final int CAMERA_LAUNCH_SOURCE_POWER_DOUBLE_TAP = 1;
    public static final int CAMERA_LAUNCH_SOURCE_WIGGLE = 0;
    public static final int DISABLE2_MASK = 1;
    public static final int DISABLE2_NONE = 0;
    public static final int DISABLE2_QUICK_SETTINGS = 1;
    public static final int DISABLE_BACK = 0x400000;
    public static final int DISABLE_CLOCK = 0x800000;
    public static final int DISABLE_EXPAND = 0x10000;
    public static final int DISABLE_HOME = 0x200000;
    public static final int DISABLE_MASK = 0x3ff0000;
    public static final int DISABLE_NAVIGATION = 0x1200000;
    public static final int DISABLE_NONE = 0;
    public static final int DISABLE_NOTIFICATION_ALERTS = 0x40000;
    public static final int DISABLE_NOTIFICATION_ICONS = 0x20000;
    public static final int DISABLE_NOTIFICATION_TICKER = 0x80000;
    public static final int DISABLE_RECENT = 0x1000000;
    public static final int DISABLE_SEARCH = 0x2000000;
    public static final int DISABLE_SYSTEM_INFO = 0x100000;
    public static final int NAVIGATION_HINT_BACK_ALT = 1;
    public static final int NAVIGATION_HINT_IME_SHOWN = 2;
    public static final int WINDOW_NAVIGATION_BAR = 2;
    public static final int WINDOW_STATE_HIDDEN = 2;
    public static final int WINDOW_STATE_HIDING = 1;
    public static final int WINDOW_STATE_SHOWING = 0;
    public static final int WINDOW_STATUS_BAR = 1;
    private Context mContext;
    private IStatusBarService mService;
    private IBinder mToken;
}
