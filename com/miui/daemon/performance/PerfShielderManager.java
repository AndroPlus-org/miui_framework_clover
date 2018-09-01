// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.daemon.performance;

import android.os.*;
import com.android.internal.app.IPerfShielder;

public class PerfShielderManager
{

    public PerfShielderManager()
    {
    }

    private static boolean checkService()
    {
        boolean flag;
        if(getService() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static Long getFreeMemory()
    {
        checkService();
        long l = 0L;
        if(sPerfManager != null)
            try
            {
                l = sPerfManager.getFreeMemory();
            }
            catch(RemoteException remoteexception)
            {
                l = Process.getFreeMemory();
                remoteexception.printStackTrace();
            }
        return Long.valueOf(l);
    }

    public static IPerfShielder getService()
    {
        if(sPerfManager != null) goto _L2; else goto _L1
_L1:
        com/miui/daemon/performance/PerfShielderManager;
        JVM INSTR monitorenter ;
        if(sPerfManager != null) goto _L4; else goto _L3
_L3:
        IPerfShielder iperfshielder = com.android.internal.app.IPerfShielder.Stub.asInterface(ServiceManager.getService("perfshielder"));
        com/miui/daemon/performance/PerfShielderManager;
        JVM INSTR monitorenter ;
        sPerfManager = iperfshielder;
        com/miui/daemon/performance/PerfShielderManager;
        JVM INSTR monitorexit ;
_L4:
        com/miui/daemon/performance/PerfShielderManager;
        JVM INSTR monitorexit ;
_L2:
        return sPerfManager;
        Exception exception;
        exception;
        com/miui/daemon/performance/PerfShielderManager;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        com/miui/daemon/performance/PerfShielderManager;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static final String PerfShieldService = "perfshielder";
    private static volatile IPerfShielder sPerfManager;
}
