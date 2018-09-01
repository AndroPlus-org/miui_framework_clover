// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.os.IBinder;

// Referenced classes of package android.app:
//            ActivityManager, IActivityManager, PendingIntent

public abstract class ActivityManagerNative
{

    public ActivityManagerNative()
    {
    }

    public static IActivityManager asInterface(IBinder ibinder)
    {
        return IActivityManager.Stub.asInterface(ibinder);
    }

    public static void broadcastStickyIntent(Intent intent, String s, int i)
    {
        broadcastStickyIntent(intent, s, -1, i);
    }

    public static void broadcastStickyIntent(Intent intent, String s, int i, int j)
    {
        ActivityManager.broadcastStickyIntent(intent, i, j);
    }

    public static IActivityManager getDefault()
    {
        return ActivityManager.getService();
    }

    public static boolean isSystemReady()
    {
        return ActivityManager.isSystemReady();
    }

    public static void noteAlarmFinish(PendingIntent pendingintent, int i, String s)
    {
        ActivityManager.noteAlarmFinish(pendingintent, i, s);
    }

    public static void noteAlarmStart(PendingIntent pendingintent, int i, String s)
    {
        ActivityManager.noteAlarmStart(pendingintent, i, s);
    }

    public static void noteWakeupAlarm(PendingIntent pendingintent, int i, String s, String s1)
    {
        ActivityManager.noteWakeupAlarm(pendingintent, i, s, s1);
    }
}
