// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.Os;
import android.system.OsConstants;
import android.webkit.WebViewZygote;
import dalvik.system.VMRuntime;

// Referenced classes of package android.os:
//            ZygoteProcess, UserHandle, StrictMode, ProcessInjector

public class Process
{
    public static final class ProcessStartResult
    {

        public int pid;
        public boolean usingWrapper;

        public ProcessStartResult()
        {
        }
    }


    public Process()
    {
    }

    public static final native long getElapsedCpuTime();

    public static final native int[] getExclusiveCores();

    public static final native long getFreeMemory();

    public static final native int getGidForName(String s);

    public static final int getParentPid(int i)
    {
        long al[] = new long[1];
        al[0] = -1L;
        readProcLines((new StringBuilder()).append("/proc/").append(i).append("/status").toString(), new String[] {
            "PPid:"
        }, al);
        return (int)al[0];
    }

    public static final native int[] getPids(String s, int ai[]);

    public static final native int[] getPidsForCommands(String as[]);

    public static final native int getProcessGroup(int i)
        throws IllegalArgumentException, SecurityException;

    public static final native long getPss(int i);

    public static final long getStartElapsedRealtime()
    {
        return sStartElapsedRealtime;
    }

    public static final long getStartUptimeMillis()
    {
        return sStartUptimeMillis;
    }

    public static final int getThreadGroupLeader(int i)
    {
        long al[] = new long[1];
        al[0] = -1L;
        readProcLines((new StringBuilder()).append("/proc/").append(i).append("/status").toString(), new String[] {
            "Tgid:"
        }, al);
        return (int)al[0];
    }

    public static final native int getThreadPriority(int i)
        throws IllegalArgumentException;

    public static final native int getThreadScheduler(int i)
        throws IllegalArgumentException;

    public static final native long getTotalMemory();

    public static final native int getUidForName(String s);

    public static final int getUidForPid(int i)
    {
        long al[] = new long[1];
        al[0] = -1L;
        readProcLines((new StringBuilder()).append("/proc/").append(i).append("/status").toString(), new String[] {
            "Uid:"
        }, al);
        return (int)al[0];
    }

    public static final boolean is64Bit()
    {
        return VMRuntime.getRuntime().is64Bit();
    }

    public static boolean isApplicationUid(int i)
    {
        return UserHandle.isApp(i);
    }

    public static final boolean isIsolated()
    {
        return isIsolated(myUid());
    }

    public static final boolean isIsolated(int i)
    {
        boolean flag = false;
        i = UserHandle.getAppId(i);
        boolean flag1 = flag;
        if(i >= 0x182b8)
        {
            flag1 = flag;
            if(i <= 0x1869f)
                flag1 = true;
        }
        return flag1;
    }

    public static final boolean isThreadInProcess(int i, int j)
    {
        StrictMode.ThreadPolicy threadpolicy = StrictMode.allowThreadDiskReads();
        boolean flag;
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #172 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            flag = Os.access(stringbuilder.append("/proc/").append(i).append("/task/").append(j).toString(), OsConstants.F_OK);
        }
        catch(Exception exception)
        {
            StrictMode.setThreadPolicy(threadpolicy);
            return false;
        }
        if(flag)
        {
            StrictMode.setThreadPolicy(threadpolicy);
            return true;
        } else
        {
            StrictMode.setThreadPolicy(threadpolicy);
            return false;
        }
        Exception exception1;
        exception1;
        StrictMode.setThreadPolicy(threadpolicy);
        throw exception1;
    }

    public static final void killProcess(int i)
    {
        ProcessInjector.reportKillProcessEvent(i);
        sendSignal(i, 9);
    }

    public static final native int killProcessGroup(int i, int j);

    public static final void killProcessQuiet(int i)
    {
        sendSignalQuiet(i, 9);
    }

    public static final int myPid()
    {
        return Os.getpid();
    }

    public static final int myPpid()
    {
        return Os.getppid();
    }

    public static final int myTid()
    {
        return Os.gettid();
    }

    public static final int myUid()
    {
        return Os.getuid();
    }

    public static UserHandle myUserHandle()
    {
        return UserHandle.of(UserHandle.getUserId(myUid()));
    }

    public static final native boolean parseProcLine(byte abyte0[], int i, int j, int ai[], String as[], long al[], float af[]);

    public static final native boolean readProcFile(String s, int ai[], String as[], long al[], float af[]);

    public static final native void readProcLines(String s, String as[], long al[]);

    public static final native void removeAllProcessGroups();

    public static final native void sendSignal(int i, int j);

    public static final native void sendSignalQuiet(int i, int j);

    public static final native void setArgV0(String s);

    public static final native void setCanSelfBackground(boolean flag);

    public static final native void setCgroupProcsProcessGroup(int i, int j, int k)
        throws IllegalArgumentException, SecurityException;

    public static final native int setGid(int i);

    public static final native void setProcessGroup(int i, int j)
        throws IllegalArgumentException, SecurityException;

    public static final void setStartTimes(long l, long l1)
    {
        sStartElapsedRealtime = l;
        sStartUptimeMillis = l1;
    }

    public static final native boolean setSwappiness(int i, boolean flag);

    public static final native void setThreadGroup(int i, int j)
        throws IllegalArgumentException, SecurityException;

    public static final native void setThreadGroupAndCpuset(int i, int j)
        throws IllegalArgumentException, SecurityException;

    public static final native void setThreadPriority(int i)
        throws IllegalArgumentException, SecurityException;

    public static final native void setThreadPriority(int i, int j)
        throws IllegalArgumentException, SecurityException;

    public static final native void setThreadScheduler(int i, int j, int k)
        throws IllegalArgumentException;

    public static final native int setUid(int i);

    public static final ProcessStartResult start(String s, String s1, int i, int j, int ai[], int k, int l, int i1, 
            String s2, String s3, String s4, String s5, String s6, String as[])
    {
        return zygoteProcess.start(s, s1, i, j, ai, k, l, i1, s2, s3, s4, s5, s6, as);
    }

    public static final ProcessStartResult startWebView(String s, String s1, int i, int j, int ai[], int k, int l, int i1, 
            String s2, String s3, String s4, String s5, String s6, String as[])
    {
        return WebViewZygote.getProcess().start(s, s1, i, j, ai, k, l, i1, s2, s3, s4, s5, s6, as);
    }

    public static final boolean supportsProcesses()
    {
        return true;
    }

    public static final int AUDIOSERVER_UID = 1041;
    public static final int BLUETOOTH_UID = 1002;
    public static final int CAMERASERVER_UID = 1047;
    public static final int DRM_UID = 1019;
    public static final int FIRST_APPLICATION_CACHE_GID = 20000;
    public static final int FIRST_APPLICATION_UID = 10000;
    public static final int FIRST_ISOLATED_UID = 0x182b8;
    public static final int FIRST_SHARED_APPLICATION_GID = 50000;
    public static final int KEYSTORE_UID = 1017;
    public static final int LAST_APPLICATION_CACHE_GID = 29999;
    public static final int LAST_APPLICATION_UID = 19999;
    public static final int LAST_ISOLATED_UID = 0x1869f;
    public static final int LAST_SHARED_APPLICATION_GID = 59999;
    private static final String LOG_TAG = "Process";
    public static final int LOG_UID = 1007;
    public static final int MEDIA_RW_GID = 1023;
    public static final int MEDIA_UID = 1013;
    public static final int NFC_UID = 1027;
    public static final int NOBODY_UID = 9999;
    public static final int OTA_UPDATE_UID = 1061;
    public static final int PACKAGE_INFO_GID = 1032;
    public static final int PHONE_UID = 1001;
    public static final int PROC_CHAR = 2048;
    public static final int PROC_COMBINE = 256;
    public static final int PROC_OUT_FLOAT = 16384;
    public static final int PROC_OUT_LONG = 8192;
    public static final int PROC_OUT_STRING = 4096;
    public static final int PROC_PARENS = 512;
    public static final int PROC_QUOTES = 1024;
    public static final int PROC_SPACE_TERM = 32;
    public static final int PROC_TAB_TERM = 9;
    public static final int PROC_TERM_MASK = 255;
    public static final int PROC_ZERO_TERM = 0;
    public static final int ROOT_UID = 0;
    public static final int SCHED_BATCH = 3;
    public static final int SCHED_FIFO = 1;
    public static final int SCHED_IDLE = 5;
    public static final int SCHED_OTHER = 0;
    public static final int SCHED_RESET_ON_FORK = 0x40000000;
    public static final int SCHED_RR = 2;
    public static final String SECONDARY_ZYGOTE_SOCKET = "zygote_secondary";
    public static final int SHARED_RELRO_UID = 1037;
    public static final int SHARED_USER_GID = 9997;
    public static final int SHELL_UID = 2000;
    public static final int SIGNAL_KILL = 9;
    public static final int SIGNAL_QUIT = 3;
    public static final int SIGNAL_USR1 = 10;
    public static final int SYSTEM_UID = 1000;
    public static final int THREAD_GROUP_AUDIO_APP = 3;
    public static final int THREAD_GROUP_AUDIO_SYS = 4;
    public static final int THREAD_GROUP_BG_NONINTERACTIVE = 0;
    public static final int THREAD_GROUP_DEFAULT = -1;
    public static final int THREAD_GROUP_FG_LIMITED = 10;
    public static final int THREAD_GROUP_FG_SERVICE = 9;
    private static final int THREAD_GROUP_FOREGROUND = 1;
    public static final int THREAD_GROUP_RT_APP = 6;
    public static final int THREAD_GROUP_SYSTEM = 2;
    public static final int THREAD_GROUP_TOP_APP = 5;
    public static final int THREAD_PRIORITY_AUDIO = -16;
    public static final int THREAD_PRIORITY_BACKGROUND = 10;
    public static final int THREAD_PRIORITY_DEFAULT = 0;
    public static final int THREAD_PRIORITY_DISPLAY = -4;
    public static final int THREAD_PRIORITY_FOREGROUND = -2;
    public static final int THREAD_PRIORITY_LESS_FAVORABLE = 1;
    public static final int THREAD_PRIORITY_LOWEST = 19;
    public static final int THREAD_PRIORITY_MORE_FAVORABLE = -1;
    public static final int THREAD_PRIORITY_URGENT_AUDIO = -19;
    public static final int THREAD_PRIORITY_URGENT_DISPLAY = -8;
    public static final int VPN_UID = 1016;
    public static final int WEBVIEW_ZYGOTE_UID = 1051;
    public static final int WIFI_UID = 1010;
    public static final String ZYGOTE_SOCKET = "zygote";
    private static long sStartElapsedRealtime;
    private static long sStartUptimeMillis;
    public static final ZygoteProcess zygoteProcess = new ZygoteProcess("zygote", "zygote_secondary");

}
