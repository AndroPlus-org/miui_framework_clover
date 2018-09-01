// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk;

import android.content.pm.ParceledListSlice;
import android.os.*;
import android.util.Slog;
import java.util.List;
import miui.mqsas.IMQSService;
import miui.mqsas.sdk.event.AnrEvent;
import miui.mqsas.sdk.event.BootEvent;
import miui.mqsas.sdk.event.JavaExceptionEvent;
import miui.mqsas.sdk.event.PackageEvent;
import miui.mqsas.sdk.event.ScreenOnEvent;
import miui.mqsas.sdk.event.WatchdogEvent;

public class MQSEventManagerDelegate
{

    static String _2D_get0()
    {
        return TAG;
    }

    static IMQSService _2D_set0(MQSEventManagerDelegate mqseventmanagerdelegate, IMQSService imqsservice)
    {
        mqseventmanagerdelegate.mService = imqsservice;
        return imqsservice;
    }

    private MQSEventManagerDelegate()
    {
        mDeathHandler = new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                Slog.w(MQSEventManagerDelegate._2D_get0(), "Mqsas binderDied!");
                MQSEventManagerDelegate._2D_set0(MQSEventManagerDelegate.this, null);
            }

            final MQSEventManagerDelegate this$0;

            
            {
                this$0 = MQSEventManagerDelegate.this;
                super();
            }
        }
;
    }

    public static MQSEventManagerDelegate getInstance()
    {
        miui/mqsas/sdk/MQSEventManagerDelegate;
        JVM INSTR monitorenter ;
        MQSEventManagerDelegate mqseventmanagerdelegate1;
        if(sInstance == null)
        {
            MQSEventManagerDelegate mqseventmanagerdelegate = JVM INSTR new #2   <Class MQSEventManagerDelegate>;
            mqseventmanagerdelegate.MQSEventManagerDelegate();
            sInstance = mqseventmanagerdelegate;
        }
        mqseventmanagerdelegate1 = sInstance;
        miui/mqsas/sdk/MQSEventManagerDelegate;
        JVM INSTR monitorexit ;
        return mqseventmanagerdelegate1;
        Exception exception;
        exception;
        throw exception;
    }

    public void dialogButtonChecked(int i, int j, String s, boolean flag)
    {
        if(DEBUG)
            Slog.i(TAG, "dialogButtonChecked");
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_38;
        imqsservice.dialogButtonChecked(i, j, s, flag);
_L1:
        return;
        s;
        Slog.e(TAG, (new StringBuilder()).append("reportBrightnessEvents error happened:").append(s.toString()).toString());
          goto _L1
    }

    public void dumpBugReport()
    {
        if(DEBUG)
            Slog.i(TAG, "dumpBugReport");
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_30;
        imqsservice.dumpBugReport();
_L1:
        return;
        Exception exception;
        exception;
        Slog.e(TAG, (new StringBuilder()).append("dumpBugReport error happened:").append(exception.toString()).toString());
          goto _L1
    }

    protected IMQSService getMQSService()
    {
        this;
        JVM INSTR monitorenter ;
        IMQSService imqsservice;
        if(mService != null)
            break MISSING_BLOCK_LABEL_49;
        mService = miui.mqsas.IMQSService.Stub.asInterface(ServiceManager.getService("miui.mqsas.MQSService"));
        imqsservice = mService;
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_71;
        mService.asBinder().linkToDeath(mDeathHandler, 0);
_L1:
        imqsservice = mService;
        this;
        JVM INSTR monitorexit ;
        return imqsservice;
        Object obj;
        obj;
        ((Exception) (obj)).printStackTrace();
          goto _L1
        obj;
        throw obj;
        Slog.e(TAG, "failed to get MQSService.");
          goto _L1
    }

    public String getOnlineRuleMatched(String s, String s1)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getOnlineRuleMatched:").append(s).append(" ").append(s1).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice != null)
            try
            {
                imqsservice.getOnlineRuleMatched(s, s1);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Slog.e(TAG, (new StringBuilder()).append("getOnlineRuleMatched error happened:").append(s.toString()).toString());
            }
        return null;
    }

    public void onBootCompleted()
    {
        if(DEBUG)
            Slog.i(TAG, "onBootCompleted");
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_30;
        imqsservice.onBootCompleted();
_L1:
        return;
        Exception exception;
        exception;
        Slog.e(TAG, (new StringBuilder()).append("onBootCompleted error happened:").append(exception.toString()).toString());
          goto _L1
    }

    public void reportAnrEvent(AnrEvent anrevent)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportAnrEvent:").append(anrevent.toString()).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_51;
        imqsservice.reportAnrEvent(anrevent);
_L1:
        return;
        anrevent;
        Slog.e(TAG, (new StringBuilder()).append("reportAnrEvent error happened:").append(anrevent.toString()).toString());
          goto _L1
    }

    public void reportBluetoothEvent(int i, String s)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportBluetoothEvent:").append(i).append(" ").append(s).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_58;
        imqsservice.reportBluetoothEvent(i, s);
_L1:
        return;
        s;
        Slog.e(TAG, (new StringBuilder()).append("reportBluetoothEvent error happened:").append(s.toString()).toString());
          goto _L1
    }

    public void reportBootEvent(BootEvent bootevent)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportBootEvent:").append(bootevent.toString()).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_51;
        imqsservice.reportBootEvent(bootevent);
_L1:
        return;
        bootevent;
        Slog.e(TAG, (new StringBuilder()).append("reportBootEvent error happened:").append(bootevent.toString()).toString());
          goto _L1
    }

    public void reportBroadcastEvent(ParceledListSlice parceledlistslice)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportBroadcastEvent:").append(parceledlistslice.getList().toString()).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_54;
        imqsservice.reportBroadcastEvent(parceledlistslice);
_L1:
        return;
        parceledlistslice;
        Slog.e(TAG, (new StringBuilder()).append("reportBroadcastEvent error happened:").append(parceledlistslice.toString()).toString());
          goto _L1
    }

    public void reportConnectExceptionEvent(int i, int j)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportConnectExceptionEvent: ").append(i).append(" ").append(j).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_58;
        imqsservice.reportConnectExceptionEvent(i, j);
_L1:
        return;
        Exception exception;
        exception;
        Slog.e(TAG, (new StringBuilder()).append("reportConnectExceptionEvent error happened:").append(exception.toString()).toString());
          goto _L1
    }

    public void reportEvent(String s, String s1, boolean flag)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportEvent:").append(s).append(" ").append(s1).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_62;
        imqsservice.reportEvent(s, s1, flag);
_L1:
        return;
        s;
        Slog.e(TAG, (new StringBuilder()).append("reportEvent error happened:").append(s.toString()).toString());
          goto _L1
    }

    public void reportEvents(String s, List list, boolean flag)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportEvents:").append(s).append(" ").append(list).toString());
        IMQSService imqsservice;
        ParceledListSlice parceledlistslice;
        imqsservice = getMQSService();
        parceledlistslice = JVM INSTR new #177 <Class ParceledListSlice>;
        parceledlistslice.ParceledListSlice(list);
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_74;
        imqsservice.reportEvents(s, parceledlistslice, flag);
_L1:
        return;
        s;
        Slog.e(TAG, (new StringBuilder()).append("reportEvents error happened:").append(s.toString()).toString());
          goto _L1
    }

    public void reportJavaExceptionEvent(JavaExceptionEvent javaexceptionevent)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportJEEvent:").append(javaexceptionevent.toString()).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_51;
        imqsservice.reportJavaExceptionEvent(javaexceptionevent);
_L1:
        return;
        javaexceptionevent;
        Slog.e(TAG, (new StringBuilder()).append("reportJEEvent error happened:").append(javaexceptionevent.toString()).toString());
          goto _L1
    }

    public void reportKillProcessEvents(ParceledListSlice parceledlistslice)
    {
        if(DEBUG)
            Slog.i(TAG, "reportKillProcessEvents");
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_31;
        imqsservice.reportKillProcessEvents(parceledlistslice);
_L1:
        return;
        parceledlistslice;
        Slog.e(TAG, (new StringBuilder()).append("reportKillProcessEvents error happened:").append(parceledlistslice.toString()).toString());
          goto _L1
    }

    public void reportPackageEvent(PackageEvent packageevent)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportPackageEvent:").append(packageevent.toString()).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_51;
        imqsservice.reportPackageEvent(packageevent);
_L1:
        return;
        packageevent;
        Slog.e(TAG, (new StringBuilder()).append("reportPackageEvent error happened:").append(packageevent.toString()).toString());
          goto _L1
    }

    public void reportPackageForegroundEvents(ParceledListSlice parceledlistslice)
    {
        if(DEBUG)
            Slog.i(TAG, "reportPackageForegroundEvents");
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_31;
        imqsservice.reportPackageForegroundEvents(parceledlistslice);
_L1:
        return;
        parceledlistslice;
        Slog.e(TAG, (new StringBuilder()).append("reportPackageForegroundEvents error happened:").append(parceledlistslice.toString()).toString());
          goto _L1
    }

    public void reportScreenOnEvent(ScreenOnEvent screenonevent)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportScreenOnEvent: event =").append(screenonevent).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_49;
        imqsservice.reportScreenOnEvent(screenonevent);
_L1:
        return;
        screenonevent;
        Slog.e(TAG, (new StringBuilder()).append("reportScreenOnEvent error happened:").append(screenonevent.toString()).toString());
          goto _L1
    }

    public void reportSimpleEvent(int i, String s)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportSimpleEvent:").append(i).append(" ").append(s).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_59;
        imqsservice.reportSimpleEvent(i, s);
_L1:
        return;
        s;
        Slog.e(TAG, (new StringBuilder()).append("reportSimpleEvent error happened:").append(s.toString()).toString());
          goto _L1
    }

    public void reportTelephonyEvent(int i, String s)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportTelephonyEvent:").append(i).append(" ").append(s).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_59;
        imqsservice.reportTelephonyEvent(i, s);
_L1:
        return;
        s;
        Slog.e(TAG, (new StringBuilder()).append("reportTelephonyEvent error happened:").append(s.toString()).toString());
          goto _L1
    }

    public void reportWatchdogEvent(WatchdogEvent watchdogevent)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("reportJWDTEvent:").append(watchdogevent.toString()).toString());
        IMQSService imqsservice = getMQSService();
        if(imqsservice == null)
            break MISSING_BLOCK_LABEL_52;
        imqsservice.reportWatchdogEvent(watchdogevent);
_L1:
        return;
        watchdogevent;
        Slog.e(TAG, (new StringBuilder()).append("reportJWDTEvent error happened:").append(watchdogevent.toString()).toString());
          goto _L1
    }

    private static boolean DEBUG = false;
    private static final String MQS_SERVICE_NAME = "miui.mqsas.MQSService";
    private static final String TAG = miui/mqsas/sdk/MQSEventManagerDelegate.getSimpleName();
    private static MQSEventManagerDelegate sInstance = null;
    android.os.IBinder.DeathRecipient mDeathHandler;
    private IMQSService mService;

    static 
    {
        DEBUG = false;
    }
}
