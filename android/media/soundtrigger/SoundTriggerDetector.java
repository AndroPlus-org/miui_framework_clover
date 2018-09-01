// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.soundtrigger;

import android.media.AudioFormat;
import android.os.*;
import android.util.Slog;
import com.android.internal.app.ISoundTriggerService;
import java.io.PrintWriter;
import java.util.UUID;

public final class SoundTriggerDetector
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

        public byte[] getData()
        {
            if(!mTriggerAvailable)
                return mData;
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

    private class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(SoundTriggerDetector._2D_get0(SoundTriggerDetector.this) == null)
            {
                Slog.w("SoundTriggerDetector", (new StringBuilder()).append("Received message: ").append(message.what).append(" for NULL callback.").toString());
                return;
            }
            message.what;
            JVM INSTR tableswitch 2 5: default 80
        //                       2 86
        //                       3 106
        //                       4 119
        //                       5 132;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            super.handleMessage(message);
_L7:
            return;
_L2:
            SoundTriggerDetector._2D_get0(SoundTriggerDetector.this).onDetected((EventPayload)message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            SoundTriggerDetector._2D_get0(SoundTriggerDetector.this).onError();
            continue; /* Loop/switch isn't completed */
_L4:
            SoundTriggerDetector._2D_get0(SoundTriggerDetector.this).onRecognitionPaused();
            continue; /* Loop/switch isn't completed */
_L5:
            SoundTriggerDetector._2D_get0(SoundTriggerDetector.this).onRecognitionResumed();
            if(true) goto _L7; else goto _L6
_L6:
        }

        final SoundTriggerDetector this$0;

        MyHandler()
        {
            this$0 = SoundTriggerDetector.this;
            super();
        }

        MyHandler(Looper looper)
        {
            this$0 = SoundTriggerDetector.this;
            super(looper);
        }
    }

    private class RecognitionCallback extends android.hardware.soundtrigger.IRecognitionStatusCallback.Stub
    {

        public void onError(int i)
        {
            Slog.d("SoundTriggerDetector", (new StringBuilder()).append("onError()").append(i).toString());
            SoundTriggerDetector._2D_get1(SoundTriggerDetector.this).sendEmptyMessage(3);
        }

        public void onGenericSoundTriggerDetected(android.hardware.soundtrigger.SoundTrigger.GenericRecognitionEvent genericrecognitionevent)
        {
            Slog.d("SoundTriggerDetector", (new StringBuilder()).append("onGenericSoundTriggerDetected()").append(genericrecognitionevent).toString());
            Message.obtain(SoundTriggerDetector._2D_get1(SoundTriggerDetector.this), 2, new EventPayload(genericrecognitionevent.triggerInData, genericrecognitionevent.captureAvailable, genericrecognitionevent.captureFormat, genericrecognitionevent.captureSession, genericrecognitionevent.data, null)).sendToTarget();
        }

        public void onKeyphraseDetected(android.hardware.soundtrigger.SoundTrigger.KeyphraseRecognitionEvent keyphraserecognitionevent)
        {
            Slog.e("SoundTriggerDetector", (new StringBuilder()).append("Ignoring onKeyphraseDetected() called for ").append(keyphraserecognitionevent).toString());
        }

        public void onRecognitionPaused()
        {
            Slog.d("SoundTriggerDetector", "onRecognitionPaused()");
            SoundTriggerDetector._2D_get1(SoundTriggerDetector.this).sendEmptyMessage(4);
        }

        public void onRecognitionResumed()
        {
            Slog.d("SoundTriggerDetector", "onRecognitionResumed()");
            SoundTriggerDetector._2D_get1(SoundTriggerDetector.this).sendEmptyMessage(5);
        }

        final SoundTriggerDetector this$0;

        private RecognitionCallback()
        {
            this$0 = SoundTriggerDetector.this;
            super();
        }

        RecognitionCallback(RecognitionCallback recognitioncallback)
        {
            this();
        }
    }


    static Callback _2D_get0(SoundTriggerDetector soundtriggerdetector)
    {
        return soundtriggerdetector.mCallback;
    }

    static Handler _2D_get1(SoundTriggerDetector soundtriggerdetector)
    {
        return soundtriggerdetector.mHandler;
    }

    SoundTriggerDetector(ISoundTriggerService isoundtriggerservice, UUID uuid, Callback callback, Handler handler)
    {
        mSoundTriggerService = isoundtriggerservice;
        mSoundModelId = uuid;
        mCallback = callback;
        if(handler == null)
            mHandler = new MyHandler();
        else
            mHandler = new MyHandler(handler.getLooper());
    }

    public void dump(String s, PrintWriter printwriter)
    {
        s = ((String) (mLock));
        s;
        JVM INSTR monitorenter ;
    }

    public boolean startRecognition(int i)
    {
        boolean flag = false;
        boolean flag1;
        boolean flag2;
        if((i & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        if((i & 2) != 0)
            flag2 = true;
        else
            flag2 = false;
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #94  <Class ParcelUuid>;
            parceluuid.ParcelUuid(mSoundModelId);
            RecognitionCallback recognitioncallback = mRecognitionCallback;
            android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig = JVM INSTR new #99  <Class android.hardware.soundtrigger.SoundTrigger$RecognitionConfig>;
            recognitionconfig.android.hardware.soundtrigger.SoundTrigger.RecognitionConfig(flag1, flag2, null, null);
            i = isoundtriggerservice.startRecognition(parceluuid, recognitioncallback, recognitionconfig);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        flag1 = flag;
        if(i == 0)
            flag1 = true;
        return flag1;
    }

    public boolean stopRecognition()
    {
        boolean flag = false;
        int i;
        try
        {
            ISoundTriggerService isoundtriggerservice = mSoundTriggerService;
            ParcelUuid parceluuid = JVM INSTR new #94  <Class ParcelUuid>;
            parceluuid.ParcelUuid(mSoundModelId);
            i = isoundtriggerservice.stopRecognition(parceluuid, mRecognitionCallback);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        if(i == 0)
            flag = true;
        return flag;
    }

    private static final boolean DBG = false;
    private static final int MSG_AVAILABILITY_CHANGED = 1;
    private static final int MSG_DETECTION_ERROR = 3;
    private static final int MSG_DETECTION_PAUSE = 4;
    private static final int MSG_DETECTION_RESUME = 5;
    private static final int MSG_SOUND_TRIGGER_DETECTED = 2;
    public static final int RECOGNITION_FLAG_ALLOW_MULTIPLE_TRIGGERS = 2;
    public static final int RECOGNITION_FLAG_CAPTURE_TRIGGER_AUDIO = 1;
    public static final int RECOGNITION_FLAG_NONE = 0;
    private static final String TAG = "SoundTriggerDetector";
    private final Callback mCallback;
    private final Handler mHandler;
    private final Object mLock = new Object();
    private final RecognitionCallback mRecognitionCallback = new RecognitionCallback(null);
    private final UUID mSoundModelId;
    private final ISoundTriggerService mSoundTriggerService;
}
