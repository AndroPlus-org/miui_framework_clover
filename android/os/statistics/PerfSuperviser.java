// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Process;

// Referenced classes of package android.os.statistics:
//            PerfSupervisionSettings, PerfEventReporter

public class PerfSuperviser
{

    public PerfSuperviser()
    {
    }

    public static void init(boolean flag, boolean flag1)
    {
        android/os/statistics/PerfSuperviser;
        JVM INSTR monitorenter ;
        int i = Process.myPpid();
        if(i == 1)
            break MISSING_BLOCK_LABEL_16;
        android/os/statistics/PerfSuperviser;
        JVM INSTR monitorexit ;
        return;
        if(!sInitialized)
        {
            nativeInit(flag, flag1);
            sInitialized = true;
        }
        android/os/statistics/PerfSuperviser;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static native void nativeInit(boolean flag, boolean flag1);

    private static native void nativeStart(boolean flag, int i, int j, int k, int l, int i1);

    public static native void setThreadPerfSupervisionOn(boolean flag);

    public static void start(boolean flag)
    {
        android/os/statistics/PerfSuperviser;
        JVM INSTR monitorenter ;
        boolean flag1 = sInitialized;
        if(flag1)
            break MISSING_BLOCK_LABEL_15;
        android/os/statistics/PerfSuperviser;
        JVM INSTR monitorexit ;
        return;
        int i = Process.myPpid();
        if(i != 1)
            break MISSING_BLOCK_LABEL_28;
        android/os/statistics/PerfSuperviser;
        JVM INSTR monitorexit ;
        return;
        if(!sStarted)
        {
            MY_PID = Process.myPid();
            PerfSupervisionSettings.notifySupervisionReady();
            if(PerfSupervisionSettings.isSupervisionOn())
            {
                nativeStart(flag, PerfSupervisionSettings.getSupervisionLevel(), PerfSupervisionSettings.sPerfSupervisionSoftThreshold, PerfSupervisionSettings.sPerfSupervisionHardThreshold, PerfSupervisionSettings.MIN_SOFT_THRESHOLD_MS, PerfSupervisionSettings.sPerfSupervisionDivisionRatio);
                PerfEventReporter.start();
            }
            sStarted = true;
        }
        android/os/statistics/PerfSuperviser;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static final boolean DEBUGGING = false;
    public static int MY_PID = 0;
    public static final String TAG = "MiuiPerfSuperviser";
    private static volatile boolean sInitialized = false;
    private static volatile boolean sStarted = false;

}
