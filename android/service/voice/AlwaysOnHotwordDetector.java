// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.content.Intent;
import android.hardware.soundtrigger.KeyphraseEnrollmentInfo;
import android.hardware.soundtrigger.KeyphraseMetadata;
import android.media.AudioFormat;
import android.os.*;
import android.util.Slog;
import com.android.internal.app.IVoiceInteractionManagerService;
import java.io.PrintWriter;
import java.util.Locale;

// Referenced classes of package android.service.voice:
//            IVoiceInteractionService

public class AlwaysOnHotwordDetector
{
    public static abstract class Callback
    {

        public abstract void onAvailabilityChanged(int i);

        public abstract void onDetected(EventPayload eventpayload);

        public abstract void onError();

        public abstract void onRecognitionPaused();

        public abstract void onRecognitionResumed();

        public Callback()
        {
        }
    }

    public static class EventPayload
    {

        public AudioFormat getCaptureAudioFormat()
        {
            return mAudioFormat;
        }

        public Integer getCaptureSession()
        {
            if(mCaptureAvailable)
                return Integer.valueOf(mCaptureSession);
            else
                return null;
        }

        public byte[] getTriggerAudio()
        {
            if(mTriggerAvailable)
                return mData;
            else
                return null;
        }

        private final AudioFormat mAudioFormat;
        private final boolean mCaptureAvailable;
        private final int mCaptureSession;
        private final byte mData[];
        private final boolean mTriggerAvailable;

        private EventPayload(boolean flag, boolean flag1, AudioFormat audioformat, int i, byte abyte0[])
        {
            mTriggerAvailable = flag;
            mCaptureAvailable = flag1;
            mCaptureSession = i;
            mAudioFormat = audioformat;
            mData = abyte0;
        }

        EventPayload(boolean flag, boolean flag1, AudioFormat audioformat, int i, byte abyte0[], EventPayload eventpayload)
        {
            this(flag, flag1, audioformat, i, abyte0);
        }
    }

    class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Object obj = AlwaysOnHotwordDetector._2D_get4(AlwaysOnHotwordDetector.this);
            obj;
            JVM INSTR monitorenter ;
            if(AlwaysOnHotwordDetector._2D_get0(AlwaysOnHotwordDetector.this) != -3)
                break MISSING_BLOCK_LABEL_60;
            StringBuilder stringbuilder = JVM INSTR new #29  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Slog.w("AlwaysOnHotwordDetector", stringbuilder.append("Received message: ").append(message.what).append(" for an invalid detector").toString());
            obj;
            JVM INSTR monitorexit ;
            return;
            obj;
            JVM INSTR monitorexit ;
            message.what;
            JVM INSTR tableswitch 1 5: default 100
        //                       1 111
        //                       2 128
        //                       3 148
        //                       4 161
        //                       5 174;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            super.handleMessage(message);
_L8:
            return;
            message;
            throw message;
_L2:
            AlwaysOnHotwordDetector._2D_get1(AlwaysOnHotwordDetector.this).onAvailabilityChanged(message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            AlwaysOnHotwordDetector._2D_get1(AlwaysOnHotwordDetector.this).onDetected((EventPayload)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            AlwaysOnHotwordDetector._2D_get1(AlwaysOnHotwordDetector.this).onError();
            continue; /* Loop/switch isn't completed */
_L5:
            AlwaysOnHotwordDetector._2D_get1(AlwaysOnHotwordDetector.this).onRecognitionPaused();
            continue; /* Loop/switch isn't completed */
_L6:
            AlwaysOnHotwordDetector._2D_get1(AlwaysOnHotwordDetector.this).onRecognitionResumed();
            if(true) goto _L8; else goto _L7
_L7:
        }

        final AlwaysOnHotwordDetector this$0;

        MyHandler()
        {
            this$0 = AlwaysOnHotwordDetector.this;
            super();
        }
    }

    class RefreshAvailabiltyTask extends AsyncTask
    {

        private int internalGetInitialAvailability()
        {
            Object obj = AlwaysOnHotwordDetector._2D_get4(AlwaysOnHotwordDetector.this);
            obj;
            JVM INSTR monitorenter ;
            int i = AlwaysOnHotwordDetector._2D_get0(AlwaysOnHotwordDetector.this);
            if(i != -3)
                break MISSING_BLOCK_LABEL_29;
            obj;
            JVM INSTR monitorexit ;
            return -3;
            obj;
            JVM INSTR monitorexit ;
            Object obj1 = null;
            obj = AlwaysOnHotwordDetector._2D_get5(AlwaysOnHotwordDetector.this).getDspModuleProperties(AlwaysOnHotwordDetector._2D_get6(AlwaysOnHotwordDetector.this));
            obj1 = obj;
_L2:
            if(obj1 == null)
                return -2;
            break; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            throw exception;
            RemoteException remoteexception;
            remoteexception;
            Slog.w("AlwaysOnHotwordDetector", "RemoteException in getDspProperties!", remoteexception);
            if(true) goto _L2; else goto _L1
_L1:
            return AlwaysOnHotwordDetector._2D_get2(AlwaysOnHotwordDetector.this) != null ? 0 : -1;
        }

        private boolean internalGetIsEnrolled(int i, Locale locale)
        {
            boolean flag;
            try
            {
                flag = AlwaysOnHotwordDetector._2D_get5(AlwaysOnHotwordDetector.this).isEnrolledForKeyphrase(AlwaysOnHotwordDetector._2D_get6(AlwaysOnHotwordDetector.this), i, locale.toLanguageTag());
            }
            // Misplaced declaration of an exception variable
            catch(Locale locale)
            {
                Slog.w("AlwaysOnHotwordDetector", "RemoteException in listRegisteredKeyphraseSoundModels!", locale);
                return false;
            }
            return flag;
        }

        public volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        public transient Void doInBackground(Void avoid[])
        {
            int i = internalGetInitialAvailability();
            if(i != 0 && i != 1) goto _L2; else goto _L1
_L1:
            int j;
            if(!internalGetIsEnrolled(AlwaysOnHotwordDetector._2D_get2(AlwaysOnHotwordDetector.this).id, AlwaysOnHotwordDetector._2D_get3(AlwaysOnHotwordDetector.this)))
                j = 1;
            else
                j = 2;
_L3:
            avoid = ((Void []) (AlwaysOnHotwordDetector._2D_get4(AlwaysOnHotwordDetector.this)));
            avoid;
            JVM INSTR monitorenter ;
            AlwaysOnHotwordDetector._2D_set0(AlwaysOnHotwordDetector.this, j);
            AlwaysOnHotwordDetector._2D_wrap0(AlwaysOnHotwordDetector.this);
            avoid;
            JVM INSTR monitorexit ;
            return null;
_L2:
            j = i;
            if(i != 2) goto _L3; else goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        final AlwaysOnHotwordDetector this$0;

        RefreshAvailabiltyTask()
        {
            this$0 = AlwaysOnHotwordDetector.this;
            super();
        }
    }

    static final class SoundTriggerListener extends android.hardware.soundtrigger.IRecognitionStatusCallback.Stub
    {

        public void onError(int i)
        {
            Slog.i("AlwaysOnHotwordDetector", (new StringBuilder()).append("onError: ").append(i).toString());
            mHandler.sendEmptyMessage(3);
        }

        public void onGenericSoundTriggerDetected(android.hardware.soundtrigger.SoundTrigger.GenericRecognitionEvent genericrecognitionevent)
        {
            Slog.w("AlwaysOnHotwordDetector", (new StringBuilder()).append("Generic sound trigger event detected at AOHD: ").append(genericrecognitionevent).toString());
        }

        public void onKeyphraseDetected(android.hardware.soundtrigger.SoundTrigger.KeyphraseRecognitionEvent keyphraserecognitionevent)
        {
            Slog.i("AlwaysOnHotwordDetector", "onDetected");
            Message.obtain(mHandler, 2, new EventPayload(keyphraserecognitionevent.triggerInData, keyphraserecognitionevent.captureAvailable, keyphraserecognitionevent.captureFormat, keyphraserecognitionevent.captureSession, keyphraserecognitionevent.data, null)).sendToTarget();
        }

        public void onRecognitionPaused()
        {
            Slog.i("AlwaysOnHotwordDetector", "onRecognitionPaused");
            mHandler.sendEmptyMessage(4);
        }

        public void onRecognitionResumed()
        {
            Slog.i("AlwaysOnHotwordDetector", "onRecognitionResumed");
            mHandler.sendEmptyMessage(5);
        }

        private final Handler mHandler;

        public SoundTriggerListener(Handler handler)
        {
            mHandler = handler;
        }
    }


    static int _2D_get0(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        return alwaysonhotworddetector.mAvailability;
    }

    static Callback _2D_get1(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        return alwaysonhotworddetector.mExternalCallback;
    }

    static KeyphraseMetadata _2D_get2(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        return alwaysonhotworddetector.mKeyphraseMetadata;
    }

    static Locale _2D_get3(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        return alwaysonhotworddetector.mLocale;
    }

    static Object _2D_get4(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        return alwaysonhotworddetector.mLock;
    }

    static IVoiceInteractionManagerService _2D_get5(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        return alwaysonhotworddetector.mModelManagementService;
    }

    static IVoiceInteractionService _2D_get6(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        return alwaysonhotworddetector.mVoiceInteractionService;
    }

    static int _2D_set0(AlwaysOnHotwordDetector alwaysonhotworddetector, int i)
    {
        alwaysonhotworddetector.mAvailability = i;
        return i;
    }

    static void _2D_wrap0(AlwaysOnHotwordDetector alwaysonhotworddetector)
    {
        alwaysonhotworddetector.notifyStateChangedLocked();
    }

    public AlwaysOnHotwordDetector(String s, Locale locale, Callback callback, KeyphraseEnrollmentInfo keyphraseenrollmentinfo, IVoiceInteractionService ivoiceinteractionservice, IVoiceInteractionManagerService ivoiceinteractionmanagerservice)
    {
        mAvailability = 0;
        mText = s;
        mLocale = locale;
        mKeyphraseEnrollmentInfo = keyphraseenrollmentinfo;
        mKeyphraseMetadata = mKeyphraseEnrollmentInfo.getKeyphraseMetadata(s, locale);
        mExternalCallback = callback;
        mInternalCallback = new SoundTriggerListener(mHandler);
        mVoiceInteractionService = ivoiceinteractionservice;
        mModelManagementService = ivoiceinteractionmanagerservice;
        (new RefreshAvailabiltyTask()).execute(new Void[0]);
    }

    private Intent getManageIntentLocked(int i)
    {
        if(mAvailability == -3)
            throw new IllegalStateException("getManageIntent called on an invalid detector");
        if(mAvailability != 2 && mAvailability != 1)
            throw new UnsupportedOperationException("Managing the given keyphrase is not supported");
        else
            return mKeyphraseEnrollmentInfo.getManageKeyphraseIntent(i, mText, mLocale);
    }

    private int getSupportedRecognitionModesLocked()
    {
        if(mAvailability == -3)
            throw new IllegalStateException("getSupportedRecognitionModes called on an invalid detector");
        if(mAvailability != 2 && mAvailability != 1)
            throw new UnsupportedOperationException("Getting supported recognition modes for the keyphrase is not supported");
        else
            return mKeyphraseMetadata.recognitionModeFlags;
    }

    private void notifyStateChangedLocked()
    {
        Message message = Message.obtain(mHandler, 1);
        message.arg1 = mAvailability;
        message.sendToTarget();
    }

    private int startRecognitionLocked(int i)
    {
        android.hardware.soundtrigger.SoundTrigger.KeyphraseRecognitionExtra keyphraserecognitionextra = new android.hardware.soundtrigger.SoundTrigger.KeyphraseRecognitionExtra(mKeyphraseMetadata.id, mKeyphraseMetadata.recognitionModeFlags, 0, new android.hardware.soundtrigger.SoundTrigger.ConfidenceLevel[0]);
        boolean flag;
        boolean flag1;
        IVoiceInteractionManagerService ivoiceinteractionmanagerservice;
        IVoiceInteractionService ivoiceinteractionservice;
        int j;
        String s;
        SoundTriggerListener soundtriggerlistener;
        android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig;
        if((i & 1) != 0)
            flag = true;
        else
            flag = false;
        if((i & 2) != 0)
            flag1 = true;
        else
            flag1 = false;
        i = 0x80000000;
        ivoiceinteractionmanagerservice = mModelManagementService;
        ivoiceinteractionservice = mVoiceInteractionService;
        j = mKeyphraseMetadata.id;
        s = mLocale.toLanguageTag();
        soundtriggerlistener = mInternalCallback;
        recognitionconfig = JVM INSTR new #207 <Class android.hardware.soundtrigger.SoundTrigger$RecognitionConfig>;
        recognitionconfig.android.hardware.soundtrigger.SoundTrigger.RecognitionConfig(flag, flag1, new android.hardware.soundtrigger.SoundTrigger.KeyphraseRecognitionExtra[] {
            keyphraserecognitionextra
        }, null);
        j = ivoiceinteractionmanagerservice.startRecognition(ivoiceinteractionservice, j, s, soundtriggerlistener, recognitionconfig);
        i = j;
_L2:
        if(i != 0)
            Slog.w("AlwaysOnHotwordDetector", (new StringBuilder()).append("startRecognition() failed with error code ").append(i).toString());
        return i;
        RemoteException remoteexception;
        remoteexception;
        Slog.w("AlwaysOnHotwordDetector", "RemoteException in startRecognition!", remoteexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private int stopRecognitionLocked()
    {
        int i = 0x80000000;
        int j;
        try
        {
            j = mModelManagementService.stopRecognition(mVoiceInteractionService, mKeyphraseMetadata.id, mInternalCallback);
        }
        catch(RemoteException remoteexception)
        {
            Slog.w("AlwaysOnHotwordDetector", "RemoteException in stopRecognition!", remoteexception);
            j = i;
        }
        if(j != 0)
            Slog.w("AlwaysOnHotwordDetector", (new StringBuilder()).append("stopRecognition() failed with error code ").append(j).toString());
        return j;
    }

    public Intent createEnrollIntent()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Intent intent = getManageIntentLocked(0);
        obj;
        JVM INSTR monitorexit ;
        return intent;
        Exception exception;
        exception;
        throw exception;
    }

    public Intent createReEnrollIntent()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Intent intent = getManageIntentLocked(1);
        obj;
        JVM INSTR monitorexit ;
        return intent;
        Exception exception;
        exception;
        throw exception;
    }

    public Intent createUnEnrollIntent()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Intent intent = getManageIntentLocked(2);
        obj;
        JVM INSTR monitorexit ;
        return intent;
        Exception exception;
        exception;
        throw exception;
    }

    public void dump(String s, PrintWriter printwriter)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        printwriter.print(s);
        printwriter.print("Text=");
        printwriter.println(mText);
        printwriter.print(s);
        printwriter.print("Locale=");
        printwriter.println(mLocale);
        printwriter.print(s);
        printwriter.print("Availability=");
        printwriter.println(mAvailability);
        printwriter.print(s);
        printwriter.print("KeyphraseMetadata=");
        printwriter.println(mKeyphraseMetadata);
        printwriter.print(s);
        printwriter.print("EnrollmentInfo=");
        printwriter.println(mKeyphraseEnrollmentInfo);
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public int getSupportedRecognitionModes()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = getSupportedRecognitionModesLocked();
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    void invalidate()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mAvailability = -3;
        notifyStateChangedLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void onSoundModelsChanged()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mAvailability != -3 && mAvailability != -2) goto _L2; else goto _L1
_L1:
        Slog.w("AlwaysOnHotwordDetector", "Received onSoundModelsChanged for an unsupported keyphrase/config");
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mAvailability == -1) goto _L1; else goto _L3
_L3:
        stopRecognitionLocked();
        RefreshAvailabiltyTask refreshavailabiltytask = JVM INSTR new #15  <Class AlwaysOnHotwordDetector$RefreshAvailabiltyTask>;
        refreshavailabiltytask.this. RefreshAvailabiltyTask();
        refreshavailabiltytask.execute(new Void[0]);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean startRecognition(int i)
    {
        boolean flag;
label0:
        {
            flag = false;
            synchronized(mLock)
            {
                if(mAvailability == -3)
                {
                    IllegalStateException illegalstateexception = JVM INSTR new #148 <Class IllegalStateException>;
                    illegalstateexception.IllegalStateException("startRecognition called on an invalid detector");
                    throw illegalstateexception;
                }
                break label0;
            }
        }
        if(mAvailability != 2)
        {
            UnsupportedOperationException unsupportedoperationexception = JVM INSTR new #155 <Class UnsupportedOperationException>;
            unsupportedoperationexception.UnsupportedOperationException("Recognition for the given keyphrase is not supported");
            throw unsupportedoperationexception;
        }
        i = startRecognitionLocked(i);
        if(i == 0)
            flag = true;
        obj;
        JVM INSTR monitorexit ;
        return flag;
    }

    public boolean stopRecognition()
    {
        boolean flag;
label0:
        {
            flag = false;
            synchronized(mLock)
            {
                if(mAvailability == -3)
                {
                    IllegalStateException illegalstateexception = JVM INSTR new #148 <Class IllegalStateException>;
                    illegalstateexception.IllegalStateException("stopRecognition called on an invalid detector");
                    throw illegalstateexception;
                }
                break label0;
            }
        }
        int i;
        if(mAvailability != 2)
        {
            UnsupportedOperationException unsupportedoperationexception = JVM INSTR new #155 <Class UnsupportedOperationException>;
            unsupportedoperationexception.UnsupportedOperationException("Recognition for the given keyphrase is not supported");
            throw unsupportedoperationexception;
        }
        i = stopRecognitionLocked();
        if(i == 0)
            flag = true;
        obj;
        JVM INSTR monitorexit ;
        return flag;
    }

    static final boolean DBG = false;
    public static final int MANAGE_ACTION_ENROLL = 0;
    public static final int MANAGE_ACTION_RE_ENROLL = 1;
    public static final int MANAGE_ACTION_UN_ENROLL = 2;
    private static final int MSG_AVAILABILITY_CHANGED = 1;
    private static final int MSG_DETECTION_ERROR = 3;
    private static final int MSG_DETECTION_PAUSE = 4;
    private static final int MSG_DETECTION_RESUME = 5;
    private static final int MSG_HOTWORD_DETECTED = 2;
    public static final int RECOGNITION_FLAG_ALLOW_MULTIPLE_TRIGGERS = 2;
    public static final int RECOGNITION_FLAG_CAPTURE_TRIGGER_AUDIO = 1;
    public static final int RECOGNITION_FLAG_NONE = 0;
    public static final int RECOGNITION_MODE_USER_IDENTIFICATION = 2;
    public static final int RECOGNITION_MODE_VOICE_TRIGGER = 1;
    public static final int STATE_HARDWARE_UNAVAILABLE = -2;
    private static final int STATE_INVALID = -3;
    public static final int STATE_KEYPHRASE_ENROLLED = 2;
    public static final int STATE_KEYPHRASE_UNENROLLED = 1;
    public static final int STATE_KEYPHRASE_UNSUPPORTED = -1;
    private static final int STATE_NOT_READY = 0;
    private static final int STATUS_ERROR = 0x80000000;
    private static final int STATUS_OK = 0;
    static final String TAG = "AlwaysOnHotwordDetector";
    private int mAvailability;
    private final Callback mExternalCallback;
    private final Handler mHandler = new MyHandler();
    private final SoundTriggerListener mInternalCallback;
    private final KeyphraseEnrollmentInfo mKeyphraseEnrollmentInfo;
    private final KeyphraseMetadata mKeyphraseMetadata;
    private final Locale mLocale;
    private final Object mLock = new Object();
    private final IVoiceInteractionManagerService mModelManagementService;
    private final String mText;
    private final IVoiceInteractionService mVoiceInteractionService;
}
