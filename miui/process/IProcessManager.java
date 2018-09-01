// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package miui.process:
//            IMiuiApplicationThread, ForegroundInfo, ProcessConfig, IActivityChangeListener, 
//            IForegroundInfoListener, ProcessCloudData

public interface IProcessManager
    extends IInterface
{

    public abstract void addMiuiApplicationThread(IMiuiApplicationThread imiuiapplicationthread, int i)
        throws RemoteException;

    public abstract IMiuiApplicationThread getForegroundApplicationThread()
        throws RemoteException;

    public abstract ForegroundInfo getForegroundInfo()
        throws RemoteException;

    public abstract List getLockedApplication(int i)
        throws RemoteException;

    public abstract boolean isLockedApplication(String s, int i)
        throws RemoteException;

    public abstract boolean kill(ProcessConfig processconfig)
        throws RemoteException;

    public abstract boolean protectCurrentProcess(boolean flag, int i)
        throws RemoteException;

    public abstract void registerActivityChangeListener(List list, List list1, IActivityChangeListener iactivitychangelistener)
        throws RemoteException;

    public abstract void registerForegroundInfoListener(IForegroundInfoListener iforegroundinfolistener)
        throws RemoteException;

    public abstract int startProcesses(List list, int i, boolean flag, int j, int k)
        throws RemoteException;

    public abstract void unregisterActivityChangeListener(IActivityChangeListener iactivitychangelistener)
        throws RemoteException;

    public abstract void unregisterForegroundInfoListener(IForegroundInfoListener iforegroundinfolistener)
        throws RemoteException;

    public abstract void updateApplicationLockedState(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void updateCloudData(ProcessCloudData processclouddata)
        throws RemoteException;

    public abstract void updateConfig(ProcessConfig processconfig)
        throws RemoteException;

    public static final int ADD_MIUI_APPLICATION_THREAD_TRANSACTION = 13;
    public static final int GET_FOREGROUND_APPLICATION_TRANSACTION = 14;
    public static final int GET_FOREGROUND_INFO_TRANSACTION = 12;
    public static final int GET_LOCKED_APPLICATION = 4;
    public static final int IS_LOCKED_APPLICATION_TRANSACTION = 9;
    public static final int KILL_TRANSACTION = 2;
    public static final int PROTECT_CURRENT_PROCESS_TRANSACTION = 7;
    public static final int REGISTER_ACTIVITY_CHANGE_TRANSACTION = 15;
    public static final int REGISTER_FOREGROUND_INFO_LISTENER = 10;
    public static final int START_PROCESSES_TRANSACTION = 6;
    public static final int UNREGISTER_ACTIVITY_CHANGE_TRANSACTION = 16;
    public static final int UNREGISTER_FOREGROUND_INFO_LISTENER = 11;
    public static final int UPDATE_APPLICATION_LOCKED_STATE = 3;
    public static final int UPDATE_CLOUD_DATA_TRANSACTION = 8;
    public static final int UPDATE_CONFIG_TRANSACTION = 5;
    public static final String descriptor = "miui.IProcessManager";
}
