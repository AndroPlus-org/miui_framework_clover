// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.RemoteException;
import android.os.ServiceManager;

// Referenced classes of package android.app:
//            IUiModeManager

public class UiModeManager
{

    UiModeManager()
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mService = IUiModeManager.Stub.asInterface(ServiceManager.getServiceOrThrow("uimode"));
    }

    public void disableCarMode(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.disableCarMode(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void enableCarMode(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.enableCarMode(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public int getCurrentModeType()
    {
        if(mService != null)
        {
            int i;
            try
            {
                i = mService.getCurrentModeType();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 1;
        }
    }

    public int getNightMode()
    {
        if(mService != null)
        {
            int i;
            try
            {
                i = mService.getNightMode();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return -1;
        }
    }

    public boolean isNightModeLocked()
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isNightModeLocked();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return true;
        }
    }

    public boolean isUiModeLocked()
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isUiModeLocked();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return true;
        }
    }

    public void setNightMode(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.setNightMode(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public static String ACTION_ENTER_CAR_MODE = "android.app.action.ENTER_CAR_MODE";
    public static String ACTION_ENTER_DESK_MODE = "android.app.action.ENTER_DESK_MODE";
    public static String ACTION_EXIT_CAR_MODE = "android.app.action.EXIT_CAR_MODE";
    public static String ACTION_EXIT_DESK_MODE = "android.app.action.EXIT_DESK_MODE";
    public static final int DISABLE_CAR_MODE_GO_HOME = 1;
    public static final int ENABLE_CAR_MODE_ALLOW_SLEEP = 2;
    public static final int ENABLE_CAR_MODE_GO_CAR_HOME = 1;
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_NO = 1;
    public static final int MODE_NIGHT_YES = 2;
    private static final String TAG = "UiModeManager";
    private IUiModeManager mService;

}
