// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.app.IAlarmManager;
import android.util.Slog;

// Referenced classes of package android.os:
//            RemoteException, ServiceManager

public final class SystemClock
{

    private SystemClock()
    {
    }

    public static native long currentThreadTimeMicro();

    public static native long currentThreadTimeMillis();

    public static native long currentTimeMicro();

    public static native long elapsedRealtime();

    public static native long elapsedRealtimeNanos();

    public static boolean setCurrentTimeMillis(long l)
    {
        IAlarmManager ialarmmanager;
        ialarmmanager = android.app.IAlarmManager.Stub.asInterface(ServiceManager.getService("alarm"));
        if(ialarmmanager == null)
            return false;
        boolean flag = ialarmmanager.setTime(l);
        return flag;
        Object obj;
        obj;
        Slog.e("SystemClock", "Unable to set RTC", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Slog.e("SystemClock", "Unable to set RTC", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void sleep(long l)
    {
        long l1;
        long l2;
        boolean flag;
        l1 = uptimeMillis();
        l2 = l;
        flag = false;
_L2:
        Thread.sleep(l2);
        boolean flag1 = flag;
_L3:
        long l3 = (l1 + l) - uptimeMillis();
        l2 = l3;
        flag = flag1;
        if(l3 <= 0L)
        {
            if(flag1)
                Thread.currentThread().interrupt();
            return;
        }
        if(true) goto _L2; else goto _L1
_L1:
        InterruptedException interruptedexception;
        interruptedexception;
        flag1 = true;
          goto _L3
    }

    public static native long uptimeMillis();

    private static final String TAG = "SystemClock";
}
