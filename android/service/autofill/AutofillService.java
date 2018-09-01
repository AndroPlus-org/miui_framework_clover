// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.autofill.AutofillManager;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;

// Referenced classes of package android.service.autofill:
//            FillRequest, FillCallback, IFillCallback, SaveRequest, 
//            SaveCallback, ISaveCallback, IAutoFillService, FillEventHistory

public abstract class AutofillService extends Service
{

    static HandlerCaller _2D_get0(AutofillService autofillservice)
    {
        return autofillservice.mHandlerCaller;
    }

    public AutofillService()
    {
    }

    public final FillEventHistory getFillEventHistory()
    {
        AutofillManager autofillmanager = (AutofillManager)getSystemService(android/view/autofill/AutofillManager);
        if(autofillmanager == null)
            return null;
        else
            return autofillmanager.getFillEventHistory();
    }

    void lambda$_2D_android_service_autofill_AutofillService_26559(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 4: default 36
    //                   1 62
    //                   2 174
    //                   3 69
    //                   4 128;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Log.w("AutofillService", (new StringBuilder()).append("MyCallbacks received invalid message type: ").append(message).toString());
_L7:
        return;
_L2:
        onConnected();
        continue; /* Loop/switch isn't completed */
_L4:
        SomeArgs someargs = (SomeArgs)message.obj;
        FillRequest fillrequest = (FillRequest)someargs.arg1;
        message = (CancellationSignal)someargs.arg2;
        FillCallback fillcallback = new FillCallback((IFillCallback)someargs.arg3, fillrequest.getId());
        someargs.recycle();
        onFillRequest(fillrequest, message, fillcallback);
        continue; /* Loop/switch isn't completed */
_L5:
        SomeArgs someargs1 = (SomeArgs)message.obj;
        SaveRequest saverequest = (SaveRequest)someargs1.arg1;
        message = new SaveCallback((ISaveCallback)someargs1.arg2);
        someargs1.recycle();
        onSaveRequest(saverequest, message);
        continue; /* Loop/switch isn't completed */
_L3:
        onDisconnected();
        if(true) goto _L7; else goto _L6
_L6:
    }

    public final IBinder onBind(Intent intent)
    {
        if("android.service.autofill.AutofillService".equals(intent.getAction()))
        {
            return mInterface.asBinder();
        } else
        {
            Log.w("AutofillService", (new StringBuilder()).append("Tried to bind to wrong intent: ").append(intent).toString());
            return null;
        }
    }

    public void onConnected()
    {
    }

    public void onCreate()
    {
        super.onCreate();
        mHandlerCaller = new HandlerCaller(null, Looper.getMainLooper(), mHandlerCallback, true);
    }

    public void onDisconnected()
    {
    }

    public abstract void onFillRequest(FillRequest fillrequest, CancellationSignal cancellationsignal, FillCallback fillcallback);

    public abstract void onSaveRequest(SaveRequest saverequest, SaveCallback savecallback);

    private static final int MSG_CONNECT = 1;
    private static final int MSG_DISCONNECT = 2;
    private static final int MSG_ON_FILL_REQUEST = 3;
    private static final int MSG_ON_SAVE_REQUEST = 4;
    public static final String SERVICE_INTERFACE = "android.service.autofill.AutofillService";
    public static final String SERVICE_META_DATA = "android.autofill";
    private static final String TAG = "AutofillService";
    private final com.android.internal.os.HandlerCaller.Callback mHandlerCallback = new _.Lambda.svbjmB3NFhHnuZrn67G14PFSJlY(this);
    private HandlerCaller mHandlerCaller;
    private final IAutoFillService mInterface = new IAutoFillService.Stub() {

        public void onConnectedStateChanged(boolean flag)
        {
            if(flag)
                AutofillService._2D_get0(AutofillService.this).obtainMessage(1).sendToTarget();
            else
                AutofillService._2D_get0(AutofillService.this).obtainMessage(2).sendToTarget();
        }

        public void onFillRequest(FillRequest fillrequest, IFillCallback ifillcallback)
        {
            android.os.ICancellationSignal icancellationsignal = CancellationSignal.createTransport();
            try
            {
                ifillcallback.onCancellable(icancellationsignal);
            }
            catch(RemoteException remoteexception)
            {
                remoteexception.rethrowFromSystemServer();
            }
            AutofillService._2D_get0(AutofillService.this).obtainMessageOOO(3, fillrequest, CancellationSignal.fromTransport(icancellationsignal), ifillcallback).sendToTarget();
        }

        public void onSaveRequest(SaveRequest saverequest, ISaveCallback isavecallback)
        {
            AutofillService._2D_get0(AutofillService.this).obtainMessageOO(4, saverequest, isavecallback).sendToTarget();
        }

        final AutofillService this$0;

            
            {
                this$0 = AutofillService.this;
                super();
            }
    }
;
}
