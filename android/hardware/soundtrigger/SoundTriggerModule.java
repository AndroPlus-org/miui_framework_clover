// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.soundtrigger;

import android.os.*;
import java.lang.ref.WeakReference;

public class SoundTriggerModule
{
    private class NativeEventHandlerDelegate
    {

        Handler handler()
        {
            return mHandler;
        }

        private final Handler mHandler;
        final SoundTriggerModule this$0;

        NativeEventHandlerDelegate(SoundTrigger.StatusListener statuslistener, Handler handler1)
        {
            this$0 = SoundTriggerModule.this;
            super();
            if(handler1 != null)
                soundtriggermodule = handler1.getLooper();
            else
                soundtriggermodule = Looper.getMainLooper();
            if(SoundTriggerModule.this != null)
                mHandler = new _cls1(statuslistener);
            else
                mHandler = null;
        }
    }


    SoundTriggerModule(int i, SoundTrigger.StatusListener statuslistener, Handler handler)
    {
        mId = i;
        mEventHandlerDelegate = new NativeEventHandlerDelegate(statuslistener, handler);
        native_setup(new WeakReference(this));
    }

    private native void native_finalize();

    private native void native_setup(Object obj);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (SoundTriggerModule)((WeakReference)obj).get();
        if(obj == null)
            return;
        obj = ((SoundTriggerModule) (obj)).mEventHandlerDelegate;
        if(obj != null)
        {
            obj = ((NativeEventHandlerDelegate) (obj)).handler();
            if(obj != null)
                ((Handler) (obj)).sendMessage(((Handler) (obj)).obtainMessage(i, j, k, obj1));
        }
    }

    public native void detach();

    protected void finalize()
    {
        native_finalize();
    }

    public native int loadSoundModel(SoundTrigger.SoundModel soundmodel, int ai[]);

    public native int startRecognition(int i, SoundTrigger.RecognitionConfig recognitionconfig);

    public native int stopRecognition(int i);

    public native int unloadSoundModel(int i);

    private static final int EVENT_RECOGNITION = 1;
    private static final int EVENT_SERVICE_DIED = 2;
    private static final int EVENT_SERVICE_STATE_CHANGE = 4;
    private static final int EVENT_SOUNDMODEL = 3;
    private NativeEventHandlerDelegate mEventHandlerDelegate;
    private int mId;
    private long mNativeContext;

    // Unreferenced inner class android/hardware/soundtrigger/SoundTriggerModule$NativeEventHandlerDelegate$1

/* anonymous class */
    class NativeEventHandlerDelegate._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 37
        //                       2 112
        //                       3 63
        //                       4 89;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L2:
            if(listener != null)
                listener.onRecognition((SoundTrigger.RecognitionEvent)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            if(listener != null)
                listener.onSoundModelUpdate((SoundTrigger.SoundModelEvent)message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            if(listener != null)
                listener.onServiceStateChange(message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            if(listener != null)
                listener.onServiceDied();
            if(true) goto _L1; else goto _L6
_L6:
        }

        final NativeEventHandlerDelegate this$1;
        final SoundTrigger.StatusListener val$listener;

            
            {
                this$1 = final_nativeeventhandlerdelegate;
                listener = statuslistener;
                super(Looper.this);
            }
    }

}
