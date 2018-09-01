// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech;

import android.content.*;
import android.content.pm.PackageManager;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.SeempLog;
import java.util.*;

// Referenced classes of package android.speech:
//            IRecognitionService, RecognitionListener

public class SpeechRecognizer
{
    private class Connection
        implements ServiceConnection
    {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            SpeechRecognizer._2D_set1(SpeechRecognizer.this, IRecognitionService.Stub.asInterface(ibinder));
            for(; !SpeechRecognizer._2D_get1(SpeechRecognizer.this).isEmpty(); SpeechRecognizer._2D_get0(SpeechRecognizer.this).sendMessage((Message)SpeechRecognizer._2D_get1(SpeechRecognizer.this).poll()));
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            SpeechRecognizer._2D_set1(SpeechRecognizer.this, null);
            SpeechRecognizer._2D_set0(SpeechRecognizer.this, null);
            SpeechRecognizer._2D_get1(SpeechRecognizer.this).clear();
        }

        final SpeechRecognizer this$0;

        private Connection()
        {
            this$0 = SpeechRecognizer.this;
            super();
        }

        Connection(Connection connection)
        {
            this();
        }
    }

    private static class InternalListener extends IRecognitionListener.Stub
    {

        static RecognitionListener _2D_get0(InternalListener internallistener)
        {
            return internallistener.mInternalListener;
        }

        static RecognitionListener _2D_set0(InternalListener internallistener, RecognitionListener recognitionlistener)
        {
            internallistener.mInternalListener = recognitionlistener;
            return recognitionlistener;
        }

        public void onBeginningOfSpeech()
        {
            Message.obtain(mInternalHandler, 1).sendToTarget();
        }

        public void onBufferReceived(byte abyte0[])
        {
            Message.obtain(mInternalHandler, 2, abyte0).sendToTarget();
        }

        public void onEndOfSpeech()
        {
            Message.obtain(mInternalHandler, 3).sendToTarget();
        }

        public void onError(int i)
        {
            Message.obtain(mInternalHandler, 4, Integer.valueOf(i)).sendToTarget();
        }

        public void onEvent(int i, Bundle bundle)
        {
            Message.obtain(mInternalHandler, 9, i, i, bundle).sendToTarget();
        }

        public void onPartialResults(Bundle bundle)
        {
            Message.obtain(mInternalHandler, 7, bundle).sendToTarget();
        }

        public void onReadyForSpeech(Bundle bundle)
        {
            Message.obtain(mInternalHandler, 5, bundle).sendToTarget();
        }

        public void onResults(Bundle bundle)
        {
            Message.obtain(mInternalHandler, 6, bundle).sendToTarget();
        }

        public void onRmsChanged(float f)
        {
            Message.obtain(mInternalHandler, 8, Float.valueOf(f)).sendToTarget();
        }

        private static final int MSG_BEGINNING_OF_SPEECH = 1;
        private static final int MSG_BUFFER_RECEIVED = 2;
        private static final int MSG_END_OF_SPEECH = 3;
        private static final int MSG_ERROR = 4;
        private static final int MSG_ON_EVENT = 9;
        private static final int MSG_PARTIAL_RESULTS = 7;
        private static final int MSG_READY_FOR_SPEECH = 5;
        private static final int MSG_RESULTS = 6;
        private static final int MSG_RMS_CHANGED = 8;
        private final Handler mInternalHandler;
        private RecognitionListener mInternalListener;

        private InternalListener()
        {
            mInternalHandler = new _cls1();
        }

        InternalListener(InternalListener internallistener)
        {
            this();
        }
    }


    static Handler _2D_get0(SpeechRecognizer speechrecognizer)
    {
        return speechrecognizer.mHandler;
    }

    static Queue _2D_get1(SpeechRecognizer speechrecognizer)
    {
        return speechrecognizer.mPendingTasks;
    }

    static Connection _2D_set0(SpeechRecognizer speechrecognizer, Connection connection)
    {
        speechrecognizer.mConnection = connection;
        return connection;
    }

    static IRecognitionService _2D_set1(SpeechRecognizer speechrecognizer, IRecognitionService irecognitionservice)
    {
        speechrecognizer.mService = irecognitionservice;
        return irecognitionservice;
    }

    static void _2D_wrap0(SpeechRecognizer speechrecognizer)
    {
        speechrecognizer.handleCancelMessage();
    }

    static void _2D_wrap1(SpeechRecognizer speechrecognizer, RecognitionListener recognitionlistener)
    {
        speechrecognizer.handleChangeListener(recognitionlistener);
    }

    static void _2D_wrap2(SpeechRecognizer speechrecognizer, Intent intent)
    {
        speechrecognizer.handleStartListening(intent);
    }

    static void _2D_wrap3(SpeechRecognizer speechrecognizer)
    {
        speechrecognizer.handleStopMessage();
    }

    private SpeechRecognizer(Context context, ComponentName componentname)
    {
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 4: default 36
            //                           1 37
            //                           2 54
            //                           3 64
            //                           4 74;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L2:
                SpeechRecognizer._2D_wrap2(SpeechRecognizer.this, (Intent)message.obj);
                continue; /* Loop/switch isn't completed */
_L3:
                SpeechRecognizer._2D_wrap3(SpeechRecognizer.this);
                continue; /* Loop/switch isn't completed */
_L4:
                SpeechRecognizer._2D_wrap0(SpeechRecognizer.this);
                continue; /* Loop/switch isn't completed */
_L5:
                SpeechRecognizer._2D_wrap1(SpeechRecognizer.this, (RecognitionListener)message.obj);
                if(true) goto _L1; else goto _L6
_L6:
            }

            final SpeechRecognizer this$0;

            
            {
                this$0 = SpeechRecognizer.this;
                super();
            }
        }
;
        mContext = context;
        mServiceComponent = componentname;
    }

    private static void checkIsCalledFromMainThread()
    {
        if(Looper.myLooper() != Looper.getMainLooper())
            throw new RuntimeException("SpeechRecognizer should be used only from the application's main thread");
        else
            return;
    }

    private boolean checkOpenConnection()
    {
        if(mService != null)
        {
            return true;
        } else
        {
            mListener.onError(5);
            Log.e("SpeechRecognizer", "not connected to the recognition service");
            return false;
        }
    }

    public static SpeechRecognizer createSpeechRecognizer(Context context)
    {
        return createSpeechRecognizer(context, null);
    }

    public static SpeechRecognizer createSpeechRecognizer(Context context, ComponentName componentname)
    {
        if(context == null)
        {
            throw new IllegalArgumentException("Context cannot be null)");
        } else
        {
            checkIsCalledFromMainThread();
            return new SpeechRecognizer(context, componentname);
        }
    }

    private void handleCancelMessage()
    {
        if(!checkOpenConnection())
            return;
        mService.cancel(mListener);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("SpeechRecognizer", "cancel() failed", remoteexception);
        mListener.onError(5);
          goto _L1
    }

    private void handleChangeListener(RecognitionListener recognitionlistener)
    {
        InternalListener._2D_set0(mListener, recognitionlistener);
    }

    private void handleStartListening(Intent intent)
    {
        if(!checkOpenConnection())
            return;
        mService.startListening(intent, mListener);
_L1:
        return;
        intent;
        Log.e("SpeechRecognizer", "startListening() failed", intent);
        mListener.onError(5);
          goto _L1
    }

    private void handleStopMessage()
    {
        if(!checkOpenConnection())
            return;
        mService.stopListening(mListener);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("SpeechRecognizer", "stopListening() failed", remoteexception);
        mListener.onError(5);
          goto _L1
    }

    public static boolean isRecognitionAvailable(Context context)
    {
        boolean flag = false;
        context = context.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 0);
        boolean flag1 = flag;
        if(context != null)
        {
            flag1 = flag;
            if(context.size() != 0)
                flag1 = true;
        }
        return flag1;
    }

    private void putMessage(Message message)
    {
        if(mService == null)
            mPendingTasks.offer(message);
        else
            mHandler.sendMessage(message);
    }

    public void cancel()
    {
        checkIsCalledFromMainThread();
        putMessage(Message.obtain(mHandler, 3));
    }

    public void destroy()
    {
        if(mService != null)
            try
            {
                mService.cancel(mListener);
            }
            catch(RemoteException remoteexception) { }
        if(mConnection != null)
            mContext.unbindService(mConnection);
        mPendingTasks.clear();
        mService = null;
        mConnection = null;
        InternalListener._2D_set0(mListener, null);
    }

    public void setRecognitionListener(RecognitionListener recognitionlistener)
    {
        checkIsCalledFromMainThread();
        putMessage(Message.obtain(mHandler, 4, recognitionlistener));
    }

    public void startListening(Intent intent)
    {
        SeempLog.record(72);
        if(intent == null)
            throw new IllegalArgumentException("intent must not be null");
        checkIsCalledFromMainThread();
        if(mConnection == null)
        {
            mConnection = new Connection(null);
            Intent intent1 = new Intent("android.speech.RecognitionService");
            if(mServiceComponent == null)
            {
                String s = android.provider.Settings.Secure.getString(mContext.getContentResolver(), "voice_recognition_service");
                if(TextUtils.isEmpty(s))
                {
                    Log.e("SpeechRecognizer", "no selected voice recognition service");
                    mListener.onError(5);
                    return;
                }
                intent1.setComponent(ComponentName.unflattenFromString(s));
            } else
            {
                intent1.setComponent(mServiceComponent);
            }
            if(!mContext.bindService(intent1, mConnection, 1))
            {
                Log.e("SpeechRecognizer", "bind to recognition service failed");
                mConnection = null;
                mService = null;
                mListener.onError(5);
                return;
            }
        }
        putMessage(Message.obtain(mHandler, 1, intent));
    }

    public void stopListening()
    {
        checkIsCalledFromMainThread();
        putMessage(Message.obtain(mHandler, 2));
    }

    public static final String CONFIDENCE_SCORES = "confidence_scores";
    private static final boolean DBG = false;
    public static final int ERROR_AUDIO = 3;
    public static final int ERROR_CLIENT = 5;
    public static final int ERROR_INSUFFICIENT_PERMISSIONS = 9;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_NETWORK_TIMEOUT = 1;
    public static final int ERROR_NO_MATCH = 7;
    public static final int ERROR_RECOGNIZER_BUSY = 8;
    public static final int ERROR_SERVER = 4;
    public static final int ERROR_SPEECH_TIMEOUT = 6;
    private static final int MSG_CANCEL = 3;
    private static final int MSG_CHANGE_LISTENER = 4;
    private static final int MSG_START = 1;
    private static final int MSG_STOP = 2;
    public static final String RESULTS_RECOGNITION = "results_recognition";
    private static final String TAG = "SpeechRecognizer";
    private Connection mConnection;
    private final Context mContext;
    private Handler mHandler;
    private final InternalListener mListener = new InternalListener(null);
    private final Queue mPendingTasks = new LinkedList();
    private IRecognitionService mService;
    private final ComponentName mServiceComponent;

    // Unreferenced inner class android/speech/SpeechRecognizer$InternalListener$1

/* anonymous class */
    class InternalListener._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            if(InternalListener._2D_get0(InternalListener.this) == null)
                return;
            message.what;
            JVM INSTR tableswitch 1 9: default 64
        //                       1 65
        //                       2 80
        //                       3 102
        //                       4 117
        //                       5 142
        //                       6 164
        //                       7 186
        //                       8 208
        //                       9 233;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            return;
_L2:
            InternalListener._2D_get0(InternalListener.this).onBeginningOfSpeech();
            continue; /* Loop/switch isn't completed */
_L3:
            InternalListener._2D_get0(InternalListener.this).onBufferReceived((byte[])message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            InternalListener._2D_get0(InternalListener.this).onEndOfSpeech();
            continue; /* Loop/switch isn't completed */
_L5:
            InternalListener._2D_get0(InternalListener.this).onError(((Integer)message.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L6:
            InternalListener._2D_get0(InternalListener.this).onReadyForSpeech((Bundle)message.obj);
            continue; /* Loop/switch isn't completed */
_L7:
            InternalListener._2D_get0(InternalListener.this).onResults((Bundle)message.obj);
            continue; /* Loop/switch isn't completed */
_L8:
            InternalListener._2D_get0(InternalListener.this).onPartialResults((Bundle)message.obj);
            continue; /* Loop/switch isn't completed */
_L9:
            InternalListener._2D_get0(InternalListener.this).onRmsChanged(((Float)message.obj).floatValue());
            continue; /* Loop/switch isn't completed */
_L10:
            InternalListener._2D_get0(InternalListener.this).onEvent(message.arg1, (Bundle)message.obj);
            if(true) goto _L1; else goto _L11
_L11:
        }

        final InternalListener this$1;

            
            {
                this$1 = InternalListener.this;
                super();
            }
    }

}
