// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package miui.process:
//            ProcessManagerNative, IProcessManager, IMiuiApplicationThread, ForegroundInfo, 
//            ProcessConfig, IActivityChangeListener, IForegroundInfoListener, ProcessCloudData

public class ProcessManager
{

    public ProcessManager()
    {
    }

    public static void addMiuiApplicationThread(IMiuiApplicationThread imiuiapplicationthread)
    {
        IProcessManager iprocessmanager = ProcessManagerNative.getDefault();
        if(iprocessmanager == null)
            break MISSING_BLOCK_LABEL_18;
        iprocessmanager.addMiuiApplicationThread(imiuiapplicationthread, Process.myPid());
_L1:
        return;
        imiuiapplicationthread;
        imiuiapplicationthread.printStackTrace();
          goto _L1
    }

    public static IMiuiApplicationThread getForegroundApplicationThread()
    {
        Object obj = null;
        IProcessManager iprocessmanager = ProcessManagerNative.getDefault();
        Object obj1 = obj;
        if(iprocessmanager != null)
            try
            {
                obj1 = iprocessmanager.getForegroundApplicationThread();
            }
            catch(RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
                remoteexception = obj;
            }
        return ((IMiuiApplicationThread) (obj1));
    }

    public static ForegroundInfo getForegroundInfo()
    {
        ForegroundInfo foregroundinfo = null;
        ForegroundInfo foregroundinfo1 = ProcessManagerNative.getDefault().getForegroundInfo();
        foregroundinfo = foregroundinfo1;
_L2:
        return foregroundinfo;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static List getLockedApplication(int i)
    {
        List list;
        try
        {
            list = ProcessManagerNative.getDefault().getLockedApplication(i);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return null;
        }
        return list;
    }

    public static boolean isLockedApplication(String s, int i)
    {
        boolean flag;
        try
        {
            flag = ProcessManagerNative.getDefault().isLockedApplication(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            return false;
        }
        return flag;
    }

    public static boolean kill(ProcessConfig processconfig)
    {
        boolean flag = false;
        boolean flag1 = ProcessManagerNative.getDefault().kill(processconfig);
        flag = flag1;
_L2:
        return flag;
        processconfig;
        processconfig.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean protectCurrentProcess(boolean flag)
    {
        boolean flag1 = false;
        try
        {
            flag = ProcessManagerNative.getDefault().protectCurrentProcess(flag, 0);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            flag = flag1;
        }
        return flag;
    }

    public static boolean protectCurrentProcess(boolean flag, int i)
    {
        boolean flag1 = false;
        try
        {
            flag = ProcessManagerNative.getDefault().protectCurrentProcess(flag, i);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            flag = flag1;
        }
        return flag;
    }

    public static void registerActivityChangeListener(List list, List list1, IActivityChangeListener iactivitychangelistener)
    {
        ProcessManagerNative.getDefault().registerActivityChangeListener(list, list1, iactivitychangelistener);
_L1:
        return;
        list;
        list.printStackTrace();
          goto _L1
    }

    public static void registerForegroundInfoListener(IForegroundInfoListener iforegroundinfolistener)
    {
        ProcessManagerNative.getDefault().registerForegroundInfoListener(iforegroundinfolistener);
_L1:
        return;
        iforegroundinfolistener;
        iforegroundinfolistener.printStackTrace();
          goto _L1
    }

    public static boolean startProcess(String s, int i, int j)
    {
        return startProcess(s, false, i, j);
    }

    public static boolean startProcess(String s, boolean flag, int i, int j)
    {
        boolean flag1 = true;
        if(TextUtils.isEmpty(s))
        {
            Log.e("ProcessManager", "packageName cannot be null!");
            return false;
        }
        ArrayList arraylist = new ArrayList(1);
        arraylist.add(s);
        if(startProcesses(arraylist, 1, flag, i, j) == 1)
            flag = flag1;
        else
            flag = false;
        return flag;
    }

    public static int startProcesses(List list, int i, boolean flag, int j, int k)
    {
        if(list == null || list.size() == 0)
        {
            Log.e("ProcessManager", "processNames cannot be null!");
            return 0;
        }
        if(list.size() < i || i <= 0)
        {
            Log.e("ProcessManager", "illegal start number!");
            return 0;
        }
        boolean flag1 = false;
        try
        {
            i = ProcessManagerNative.getDefault().startProcesses(list, i, flag, j, k);
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            list.printStackTrace();
            i = ((flag1) ? 1 : 0);
        }
        return i;
    }

    public static void unregisterActivityChanageListener(IActivityChangeListener iactivitychangelistener)
    {
        ProcessManagerNative.getDefault().unregisterActivityChangeListener(iactivitychangelistener);
_L1:
        return;
        iactivitychangelistener;
        iactivitychangelistener.printStackTrace();
          goto _L1
    }

    public static void unregisterForegroundInfoListener(IForegroundInfoListener iforegroundinfolistener)
    {
        ProcessManagerNative.getDefault().unregisterForegroundInfoListener(iforegroundinfolistener);
_L1:
        return;
        iforegroundinfolistener;
        iforegroundinfolistener.printStackTrace();
          goto _L1
    }

    public static void updateApplicationLockedState(String s, int i, boolean flag)
    {
        ProcessManagerNative.getDefault().updateApplicationLockedState(s, i, flag);
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public static void updateCloudData(ProcessCloudData processclouddata)
    {
        if(processclouddata == null)
            break MISSING_BLOCK_LABEL_14;
        ProcessManagerNative.getDefault().updateCloudData(processclouddata);
_L1:
        return;
        try
        {
            Log.e("ProcessManager", "cloudData is null!");
        }
        // Misplaced declaration of an exception variable
        catch(ProcessCloudData processclouddata)
        {
            processclouddata.printStackTrace();
        }
          goto _L1
    }

    public static void updateConfig(ProcessConfig processconfig)
    {
        ProcessManagerNative.getDefault().updateConfig(processconfig);
_L1:
        return;
        processconfig;
        processconfig.printStackTrace();
          goto _L1
    }

    public static final int AI_MAX_ADJ;
    public static final int AI_MAX_PROCESS_STATE = 10;
    public static final boolean DEBUG = true;
    public static final int DEFAULT_MAX_ADJ;
    public static final int DEFAULT_PROCESS_STATE = 18;
    public static final int FLAG_START_PROCESS_AI = 1;
    public static final int LOCKED_MAX_ADJ;
    public static final int LOCKED_MAX_PROCESS_STATE = 10;
    public static final int PROTECT_MAX_ADJ;
    public static final int PROTECT_MAX_PROCESS_STATE = 10;
    public static final String SERVICE_NAME = "ProcessManager";
    private static final int SINGLE_COUNT = 1;
    public static final String TAG = "ProcessManager";

    static 
    {
        char c = '\u0190';
        int i;
        if(android.os.Build.VERSION.SDK_INT > 23)
            i = 400;
        else
            i = 4;
        LOCKED_MAX_ADJ = i;
        if(android.os.Build.VERSION.SDK_INT > 23)
            i = 400;
        else
            i = 4;
        AI_MAX_ADJ = i;
        if(android.os.Build.VERSION.SDK_INT > 23)
            i = c;
        else
            i = 4;
        PROTECT_MAX_ADJ = i;
        if(android.os.Build.VERSION.SDK_INT > 23)
            i = 1001;
        else
            i = 16;
        DEFAULT_MAX_ADJ = i;
    }
}
