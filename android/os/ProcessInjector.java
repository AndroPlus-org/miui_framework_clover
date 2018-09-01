// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import java.lang.reflect.Method;

// Referenced classes of package android.os:
//            Process

class ProcessInjector
{

    ProcessInjector()
    {
    }

    static void reportKillProcessEvent(int i)
    {
        if(mAm == null)
            mAm = Class.forName("android.app.ActivityManagerNative").getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
        mAm.getClass().getDeclaredMethod("reportKillProcessEvent", new Class[] {
            Integer.TYPE, Integer.TYPE
        }).invoke(mAm, new Object[] {
            Integer.valueOf(Process.myPid()), Integer.valueOf(i)
        });
_L1:
        return;
        Exception exception;
        exception;
        Log.e("ProcessInjector", "error while reportKillProcessEvent to system server!", exception);
          goto _L1
    }

    private static final String TAG = "ProcessInjector";
    private static Object mAm;
}
