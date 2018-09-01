// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.content.*;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package com.miui.whetstone:
//            IWhetstoneSysInfoService

public class WhetstoneSysInfoManager
{
    private static final class Holder
    {

        static WhetstoneSysInfoManager _2D_get0()
        {
            return INSTANCE;
        }

        private static WhetstoneSysInfoManager INSTANCE = new WhetstoneSysInfoManager(null);


        private Holder()
        {
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static IWhetstoneSysInfoService _2D_set0(WhetstoneSysInfoManager whetstonesysinfomanager, IWhetstoneSysInfoService iwhetstonesysinfoservice)
    {
        whetstonesysinfomanager.mService = iwhetstonesysinfoservice;
        return iwhetstonesysinfoservice;
    }

    private WhetstoneSysInfoManager()
    {
    }

    WhetstoneSysInfoManager(WhetstoneSysInfoManager whetstonesysinfomanager)
    {
        this();
    }

    public static WhetstoneSysInfoManager getInstance()
    {
        return Holder._2D_get0();
    }

    public void bindWhetstoneSysInfoService(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        if(mService != null)
            break MISSING_BLOCK_LABEL_81;
        Intent intent = JVM INSTR new #52  <Class Intent>;
        intent.Intent();
        Object obj = JVM INSTR new #55  <Class ComponentName>;
        ((ComponentName) (obj)).ComponentName("com.miui.whetstone", "com.miui.whetstone.sysinfo.WhetstoneSysInfoService");
        intent.setComponent(((ComponentName) (obj)));
        boolean flag = context.bindService(intent, mServiceConnection, 1);
        context = TAG;
        obj = JVM INSTR new #74  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.i(context, ((StringBuilder) (obj)).append("bindWhetstoneSysInfoService ").append(flag).toString());
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Log.w(TAG, "bindWhetstoneSysInfoService but mService is not null");
          goto _L1
        context;
        throw context;
    }

    public long getSysInfo(String s)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        l = mService.getSysInfo(s);
        this;
        JVM INSTR monitorexit ;
        return l;
        Log.e(TAG, "getSysInfo: Service is null");
_L1:
        this;
        JVM INSTR monitorexit ;
        return -1L;
        RemoteException remoteexception;
        remoteexception;
        s = TAG;
        StringBuilder stringbuilder = JVM INSTR new #74  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e(s, stringbuilder.append("getSysInfo long RemoteException e").append(remoteexception.getMessage()).toString());
          goto _L1
        s;
        throw s;
    }

    public String[] getSysInfos(String as[])
    {
        this;
        JVM INSTR monitorenter ;
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        as = mService.getSysInfos(as);
        this;
        JVM INSTR monitorexit ;
        return as;
        Log.e(TAG, "getSysInfo: Service is null");
_L1:
        this;
        JVM INSTR monitorexit ;
        return null;
        RemoteException remoteexception;
        remoteexception;
        as = TAG;
        StringBuilder stringbuilder = JVM INSTR new #74  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e(as, stringbuilder.append("getSysInfo String[] RemoteException e").append(remoteexception.getMessage()).toString());
          goto _L1
        as;
        throw as;
    }

    public void unbindWhetstoneSysInfoService(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        if(mService == null)
            break MISSING_BLOCK_LABEL_34;
        context.unbindService(mServiceConnection);
        mService = null;
        Log.i(TAG, "unbindWhetstoneSysInfoService");
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Log.w(TAG, "unbindWhetstoneSysInfoService but service is null");
          goto _L1
        context;
        throw context;
    }

    private static final long FAIL = -1L;
    private static final String TAG = com/miui/whetstone/WhetstoneSysInfoManager.getSimpleName();
    private IWhetstoneSysInfoService mService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.i(WhetstoneSysInfoManager._2D_get0(), "onServiceConnected");
            WhetstoneSysInfoManager._2D_set0(WhetstoneSysInfoManager.this, IWhetstoneSysInfoService.Stub.asInterface(ibinder));
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.i(WhetstoneSysInfoManager._2D_get0(), "onServiceDisconnected");
            WhetstoneSysInfoManager._2D_set0(WhetstoneSysInfoManager.this, null);
        }

        final WhetstoneSysInfoManager this$0;

            
            {
                this$0 = WhetstoneSysInfoManager.this;
                super();
            }
    }
;

}
