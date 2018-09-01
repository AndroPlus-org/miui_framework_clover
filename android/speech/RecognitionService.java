// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech;

import android.app.Service;
import android.content.Intent;
import android.content.PermissionChecker;
import android.os.*;
import android.util.Log;
import java.lang.ref.WeakReference;

// Referenced classes of package android.speech:
//            IRecognitionListener

public abstract class RecognitionService extends Service
{
    public class Callback
    {

        static IRecognitionListener _2D_get0(Callback callback)
        {
            return callback.mListener;
        }

        public void beginningOfSpeech()
            throws RemoteException
        {
            mListener.onBeginningOfSpeech();
        }

        public void bufferReceived(byte abyte0[])
            throws RemoteException
        {
            mListener.onBufferReceived(abyte0);
        }

        public void endOfSpeech()
            throws RemoteException
        {
            mListener.onEndOfSpeech();
        }

        public void error(int i)
            throws RemoteException
        {
            Message.obtain(RecognitionService._2D_get0(RecognitionService.this), 4).sendToTarget();
            mListener.onError(i);
        }

        public int getCallingUid()
        {
            return mCallingUid;
        }

        public void partialResults(Bundle bundle)
            throws RemoteException
        {
            mListener.onPartialResults(bundle);
        }

        public void readyForSpeech(Bundle bundle)
            throws RemoteException
        {
            mListener.onReadyForSpeech(bundle);
        }

        public void results(Bundle bundle)
            throws RemoteException
        {
            Message.obtain(RecognitionService._2D_get0(RecognitionService.this), 4).sendToTarget();
            mListener.onResults(bundle);
        }

        public void rmsChanged(float f)
            throws RemoteException
        {
            mListener.onRmsChanged(f);
        }

        private final int mCallingUid;
        private final IRecognitionListener mListener;
        final RecognitionService this$0;

        private Callback(IRecognitionListener irecognitionlistener, int i)
        {
            this$0 = RecognitionService.this;
            super();
            mListener = irecognitionlistener;
            mCallingUid = i;
        }

        Callback(IRecognitionListener irecognitionlistener, int i, Callback callback)
        {
            this(irecognitionlistener, i);
        }
    }

    private static final class RecognitionServiceBinder extends IRecognitionService.Stub
    {

        public void cancel(IRecognitionListener irecognitionlistener)
        {
            RecognitionService recognitionservice = (RecognitionService)mServiceRef.get();
            if(recognitionservice != null && RecognitionService._2D_wrap0(recognitionservice, irecognitionlistener))
                RecognitionService._2D_get0(recognitionservice).sendMessage(Message.obtain(RecognitionService._2D_get0(recognitionservice), 3, irecognitionlistener));
        }

        public void clearReference()
        {
            mServiceRef.clear();
        }

        public void startListening(Intent intent, IRecognitionListener irecognitionlistener)
        {
            RecognitionService recognitionservice = (RecognitionService)mServiceRef.get();
            if(recognitionservice != null && RecognitionService._2D_wrap0(recognitionservice, irecognitionlistener))
            {
                Handler handler = RecognitionService._2D_get0(recognitionservice);
                Handler handler1 = RecognitionService._2D_get0(recognitionservice);
                recognitionservice.getClass();
                handler.sendMessage(Message.obtain(handler1, 1, recognitionservice. new StartListeningArgs(intent, irecognitionlistener, Binder.getCallingUid())));
            }
        }

        public void stopListening(IRecognitionListener irecognitionlistener)
        {
            RecognitionService recognitionservice = (RecognitionService)mServiceRef.get();
            if(recognitionservice != null && RecognitionService._2D_wrap0(recognitionservice, irecognitionlistener))
                RecognitionService._2D_get0(recognitionservice).sendMessage(Message.obtain(RecognitionService._2D_get0(recognitionservice), 2, irecognitionlistener));
        }

        private final WeakReference mServiceRef;

        public RecognitionServiceBinder(RecognitionService recognitionservice)
        {
            mServiceRef = new WeakReference(recognitionservice);
        }
    }

    private class StartListeningArgs
    {

        public final int mCallingUid;
        public final Intent mIntent;
        public final IRecognitionListener mListener;
        final RecognitionService this$0;

        public StartListeningArgs(Intent intent, IRecognitionListener irecognitionlistener, int i)
        {
            this$0 = RecognitionService.this;
            super();
            mIntent = intent;
            mListener = irecognitionlistener;
            mCallingUid = i;
        }
    }


    static Handler _2D_get0(RecognitionService recognitionservice)
    {
        return recognitionservice.mHandler;
    }

    static boolean _2D_wrap0(RecognitionService recognitionservice, IRecognitionListener irecognitionlistener)
    {
        return recognitionservice.checkPermissions(irecognitionlistener);
    }

    static void _2D_wrap1(RecognitionService recognitionservice, IRecognitionListener irecognitionlistener)
    {
        recognitionservice.dispatchCancel(irecognitionlistener);
    }

    static void _2D_wrap2(RecognitionService recognitionservice)
    {
        recognitionservice.dispatchClearCallback();
    }

    static void _2D_wrap3(RecognitionService recognitionservice, Intent intent, IRecognitionListener irecognitionlistener, int i)
    {
        recognitionservice.dispatchStartListening(intent, irecognitionlistener, i);
    }

    static void _2D_wrap4(RecognitionService recognitionservice, IRecognitionListener irecognitionlistener)
    {
        recognitionservice.dispatchStopListening(irecognitionlistener);
    }

    public RecognitionService()
    {
        mBinder = new RecognitionServiceBinder(this);
        mCurrentCallback = null;
    }

    private boolean checkPermissions(IRecognitionListener irecognitionlistener)
    {
        if(PermissionChecker.checkCallingOrSelfPermission(this, "android.permission.RECORD_AUDIO") == 0)
            return true;
        try
        {
            Log.e("RecognitionService", "call for recognition service without RECORD_AUDIO permissions");
            irecognitionlistener.onError(9);
        }
        // Misplaced declaration of an exception variable
        catch(IRecognitionListener irecognitionlistener)
        {
            Log.e("RecognitionService", "sending ERROR_INSUFFICIENT_PERMISSIONS message failed", irecognitionlistener);
        }
        return false;
    }

    private void dispatchCancel(IRecognitionListener irecognitionlistener)
    {
        if(mCurrentCallback != null)
            if(Callback._2D_get0(mCurrentCallback).asBinder() != irecognitionlistener.asBinder())
            {
                Log.w("RecognitionService", "cancel called by client who did not call startListening - ignoring");
            } else
            {
                onCancel(mCurrentCallback);
                mCurrentCallback = null;
            }
    }

    private void dispatchClearCallback()
    {
        mCurrentCallback = null;
    }

    private void dispatchStartListening(Intent intent, IRecognitionListener irecognitionlistener, int i)
    {
        if(mCurrentCallback == null)
        {
            try
            {
                IBinder ibinder = irecognitionlistener.asBinder();
                android.os.IBinder.DeathRecipient deathrecipient = JVM INSTR new #8   <Class RecognitionService$2>;
                deathrecipient.this. _cls2();
                ibinder.linkToDeath(deathrecipient, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Intent intent)
            {
                Log.e("RecognitionService", "dead listener on startListening");
                return;
            }
            mCurrentCallback = new Callback(irecognitionlistener, i, null);
            onStartListening(intent, mCurrentCallback);
        } else
        {
            try
            {
                irecognitionlistener.onError(8);
            }
            // Misplaced declaration of an exception variable
            catch(Intent intent)
            {
                Log.d("RecognitionService", "onError call from startListening failed");
            }
            Log.i("RecognitionService", "concurrent startListening received - ignoring this call");
        }
    }

    private void dispatchStopListening(IRecognitionListener irecognitionlistener)
    {
        if(mCurrentCallback != null) goto _L2; else goto _L1
_L1:
        irecognitionlistener.onError(5);
        Log.w("RecognitionService", "stopListening called with no preceding startListening - ignoring");
_L3:
        return;
_L2:
label0:
        {
            if(Callback._2D_get0(mCurrentCallback).asBinder() == irecognitionlistener.asBinder())
                break label0;
            irecognitionlistener.onError(8);
            Log.w("RecognitionService", "stopListening called by other caller than startListening - ignoring");
        }
          goto _L3
        try
        {
            onStopListening(mCurrentCallback);
        }
        // Misplaced declaration of an exception variable
        catch(IRecognitionListener irecognitionlistener)
        {
            Log.d("RecognitionService", "onError call from stopListening failed");
        }
          goto _L3
    }

    public final IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    protected abstract void onCancel(Callback callback);

    public void onDestroy()
    {
        mCurrentCallback = null;
        mBinder.clearReference();
        super.onDestroy();
    }

    protected abstract void onStartListening(Intent intent, Callback callback);

    protected abstract void onStopListening(Callback callback);

    private static final boolean DBG = false;
    private static final int MSG_CANCEL = 3;
    private static final int MSG_RESET = 4;
    private static final int MSG_START_LISTENING = 1;
    private static final int MSG_STOP_LISTENING = 2;
    public static final String SERVICE_INTERFACE = "android.speech.RecognitionService";
    public static final String SERVICE_META_DATA = "android.speech";
    private static final String TAG = "RecognitionService";
    private RecognitionServiceBinder mBinder;
    private Callback mCurrentCallback;
    private final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 37
        //                       2 67
        //                       3 84
        //                       4 101;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L2:
            message = (StartListeningArgs)message.obj;
            RecognitionService._2D_wrap3(RecognitionService.this, ((StartListeningArgs) (message)).mIntent, ((StartListeningArgs) (message)).mListener, ((StartListeningArgs) (message)).mCallingUid);
            continue; /* Loop/switch isn't completed */
_L3:
            RecognitionService._2D_wrap4(RecognitionService.this, (IRecognitionListener)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            RecognitionService._2D_wrap1(RecognitionService.this, (IRecognitionListener)message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            RecognitionService._2D_wrap2(RecognitionService.this);
            if(true) goto _L1; else goto _L6
_L6:
        }

        final RecognitionService this$0;

            
            {
                this$0 = RecognitionService.this;
                super();
            }
    }
;

    // Unreferenced inner class android/speech/RecognitionService$2

/* anonymous class */
    class _cls2
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            RecognitionService._2D_get0(RecognitionService.this).sendMessage(RecognitionService._2D_get0(RecognitionService.this).obtainMessage(3, listener));
        }

        final RecognitionService this$0;
        final IRecognitionListener val$listener;

            
            {
                this$0 = RecognitionService.this;
                listener = irecognitionlistener;
                super();
            }
    }

}
