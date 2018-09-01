// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.app.ActivityThread;
import android.os.*;
import android.util.Log;
import java.lang.ref.WeakReference;

public class Visualizer
{
    public static final class MeasurementPeakRms
    {

        public int mPeak;
        public int mRms;

        public MeasurementPeakRms()
        {
        }
    }

    private class NativeEventHandler extends Handler
    {

        private void handleCaptureMessage(Message message)
        {
            byte abyte0[] = ((byte []) (Visualizer._2D_get1(Visualizer.this)));
            abyte0;
            JVM INSTR monitorenter ;
            OnDataCaptureListener ondatacapturelistener = Visualizer._2D_get0(mVisualizer);
            abyte0;
            JVM INSTR monitorexit ;
            if(ondatacapturelistener == null) goto _L2; else goto _L1
_L1:
            int i;
            abyte0 = (byte[])message.obj;
            i = message.arg1;
            message.what;
            JVM INSTR tableswitch 0 1: default 64
        //                       0 98
        //                       1 114;
               goto _L3 _L4 _L5
_L3:
            Log.e("Visualizer-JAVA", (new StringBuilder()).append("Unknown native event in handleCaptureMessge: ").append(message.what).toString());
_L2:
            return;
            message;
            throw message;
_L4:
            ondatacapturelistener.onWaveFormDataCapture(mVisualizer, abyte0, i);
            continue; /* Loop/switch isn't completed */
_L5:
            ondatacapturelistener.onFftDataCapture(mVisualizer, abyte0, i);
            if(true) goto _L2; else goto _L6
_L6:
        }

        private void handleServerDiedMessage(Message message)
        {
            message = ((Message) (Visualizer._2D_get1(Visualizer.this)));
            message;
            JVM INSTR monitorenter ;
            OnServerDiedListener onserverdiedlistener = Visualizer._2D_get2(mVisualizer);
            message;
            JVM INSTR monitorexit ;
            if(onserverdiedlistener != null)
                onserverdiedlistener.onServerDied();
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void handleMessage(Message message)
        {
            if(mVisualizer == null)
                return;
            message.what;
            JVM INSTR tableswitch 0 2: default 40
        //                       0 69
        //                       1 69
        //                       2 77;
               goto _L1 _L2 _L2 _L3
_L1:
            Log.e("Visualizer-JAVA", (new StringBuilder()).append("Unknown native event: ").append(message.what).toString());
_L5:
            return;
_L2:
            handleCaptureMessage(message);
            continue; /* Loop/switch isn't completed */
_L3:
            handleServerDiedMessage(message);
            if(true) goto _L5; else goto _L4
_L4:
        }

        private Visualizer mVisualizer;
        final Visualizer this$0;

        public NativeEventHandler(Visualizer visualizer1, Looper looper)
        {
            this$0 = Visualizer.this;
            super(looper);
            mVisualizer = visualizer1;
        }
    }

    public static interface OnDataCaptureListener
    {

        public abstract void onFftDataCapture(Visualizer visualizer, byte abyte0[], int i);

        public abstract void onWaveFormDataCapture(Visualizer visualizer, byte abyte0[], int i);
    }

    public static interface OnServerDiedListener
    {

        public abstract void onServerDied();
    }


    static OnDataCaptureListener _2D_get0(Visualizer visualizer)
    {
        return visualizer.mCaptureListener;
    }

    static Object _2D_get1(Visualizer visualizer)
    {
        return visualizer.mListenerLock;
    }

    static OnServerDiedListener _2D_get2(Visualizer visualizer)
    {
        return visualizer.mServerDiedListener;
    }

    public Visualizer(int i)
        throws UnsupportedOperationException, RuntimeException
    {
        Object obj;
        mState = 0;
        mStateLock = new Object();
        mListenerLock = new Object();
        mNativeEventHandler = null;
        mCaptureListener = null;
        mServerDiedListener = null;
        obj = new int[1];
        Object obj1 = mStateLock;
        obj1;
        JVM INSTR monitorenter ;
        mState = 0;
        WeakReference weakreference = JVM INSTR new #105 <Class WeakReference>;
        weakreference.WeakReference(this);
        i = native_setup(weakreference, i, ((int []) (obj)), ActivityThread.currentOpPackageName());
        if(i == 0 || i == -2) goto _L2; else goto _L1
_L1:
        obj = JVM INSTR new #120 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.e("Visualizer-JAVA", ((StringBuilder) (obj)).append("Error code ").append(i).append(" when initializing Visualizer.").toString());
        i;
        JVM INSTR tableswitch -5 -5: default 148
    //                   -5 187;
           goto _L3 _L4
_L3:
        obj = JVM INSTR new #95  <Class RuntimeException>;
        StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        ((RuntimeException) (obj)).RuntimeException(stringbuilder.append("Cannot initialize Visualizer engine, error: ").append(i).toString());
        throw obj;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
_L4:
        obj = JVM INSTR new #93  <Class UnsupportedOperationException>;
        ((UnsupportedOperationException) (obj)).UnsupportedOperationException("Effect library not loaded");
        throw obj;
_L2:
        mId = obj[0];
        if(!native_getEnabled()) goto _L6; else goto _L5
_L5:
        mState = 2;
_L7:
        obj1;
        JVM INSTR monitorexit ;
        return;
_L6:
        mState = 1;
          goto _L7
    }

    public static native int[] getCaptureSizeRange();

    public static native int getMaxCaptureRate();

    private final native void native_finalize();

    private final native int native_getCaptureSize();

    private final native boolean native_getEnabled();

    private final native int native_getFft(byte abyte0[]);

    private final native int native_getMeasurementMode();

    private final native int native_getPeakRms(MeasurementPeakRms measurementpeakrms);

    private final native int native_getSamplingRate();

    private final native int native_getScalingMode();

    private final native int native_getWaveForm(byte abyte0[]);

    private static final native void native_init();

    private final native void native_release();

    private final native int native_setCaptureSize(int i);

    private final native int native_setEnabled(boolean flag);

    private final native int native_setMeasurementMode(int i);

    private final native int native_setPeriodicCapture(int i, boolean flag, boolean flag1);

    private final native int native_setScalingMode(int i);

    private final native int native_setup(Object obj, int i, int ai[], String s);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (Visualizer)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(((Visualizer) (obj)).mNativeEventHandler != null)
        {
            obj1 = ((Visualizer) (obj)).mNativeEventHandler.obtainMessage(i, j, k, obj1);
            ((Visualizer) (obj)).mNativeEventHandler.sendMessage(((Message) (obj1)));
        }
    }

    protected void finalize()
    {
        native_finalize();
    }

    public int getCaptureSize()
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState == 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("getCaptureSize() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_53;
        }
        int i = native_getCaptureSize();
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public boolean getEnabled()
    {
        synchronized(mStateLock)
        {
            if(mState == 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("getEnabled() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_53;
        }
        boolean flag = native_getEnabled();
        obj;
        JVM INSTR monitorexit ;
        return flag;
    }

    public int getFft(byte abyte0[])
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState != 2)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                abyte0 = JVM INSTR new #120 <Class StringBuilder>;
                abyte0.StringBuilder();
                illegalstateexception.IllegalStateException(abyte0.append("getFft() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_54;
        }
        int i = native_getFft(abyte0);
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int getMeasurementMode()
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState == 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("getMeasurementMode() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_53;
        }
        int i = native_getMeasurementMode();
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int getMeasurementPeakRms(MeasurementPeakRms measurementpeakrms)
    {
label0:
        {
            if(measurementpeakrms == null)
            {
                Log.e("Visualizer-JAVA", "Cannot store measurements in a null object");
                return -4;
            }
            synchronized(mStateLock)
            {
                if(mState != 2)
                {
                    IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                    measurementpeakrms = JVM INSTR new #120 <Class StringBuilder>;
                    measurementpeakrms.StringBuilder();
                    illegalstateexception.IllegalStateException(measurementpeakrms.append("getMeasurementPeakRms() called in wrong state: ").append(mState).toString());
                    throw illegalstateexception;
                }
                break label0;
            }
        }
        int i = native_getPeakRms(measurementpeakrms);
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int getSamplingRate()
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState == 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("getSamplingRate() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_53;
        }
        int i = native_getSamplingRate();
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int getScalingMode()
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState == 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("getScalingMode() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_53;
        }
        int i = native_getScalingMode();
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int getWaveForm(byte abyte0[])
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState != 2)
            {
                abyte0 = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                abyte0.IllegalStateException(stringbuilder.append("getWaveForm() called in wrong state: ").append(mState).toString());
                throw abyte0;
            }
            break MISSING_BLOCK_LABEL_54;
        }
        int i = native_getWaveForm(abyte0);
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public void release()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        native_release();
        mState = 0;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int setCaptureSize(int i)
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState != 1)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("setCaptureSize() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_59;
        }
        i = native_setCaptureSize(i);
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int setDataCaptureListener(OnDataCaptureListener ondatacapturelistener, int i, boolean flag, boolean flag1)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mCaptureListener = ondatacapturelistener;
        obj;
        JVM INSTR monitorexit ;
        if(ondatacapturelistener == null)
        {
            flag = false;
            flag1 = false;
        }
        int j = native_setPeriodicCapture(i, flag, flag1);
        i = j;
        if(j == 0)
        {
            i = j;
            if(ondatacapturelistener != null)
            {
                i = j;
                if(mNativeEventHandler == null)
                {
                    ondatacapturelistener = Looper.myLooper();
                    if(ondatacapturelistener != null)
                    {
                        mNativeEventHandler = new NativeEventHandler(this, ondatacapturelistener);
                        i = j;
                    } else
                    {
                        ondatacapturelistener = Looper.getMainLooper();
                        if(ondatacapturelistener != null)
                        {
                            mNativeEventHandler = new NativeEventHandler(this, ondatacapturelistener);
                            i = j;
                        } else
                        {
                            mNativeEventHandler = null;
                            i = -3;
                        }
                    }
                }
            }
        }
        return i;
        ondatacapturelistener;
        throw ondatacapturelistener;
    }

    public int setEnabled(boolean flag)
        throws IllegalStateException
    {
        byte byte0;
        int i;
label0:
        {
            byte0 = 2;
            synchronized(mStateLock)
            {
                if(mState == 0)
                {
                    IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                    StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    illegalstateexception.IllegalStateException(stringbuilder.append("setEnabled() called in wrong state: ").append(mState).toString());
                    throw illegalstateexception;
                }
                break label0;
            }
        }
        i = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(mState != 1) goto _L2; else goto _L3
_L3:
        i = native_setEnabled(flag);
        int j = i;
        if(i != 0)
            break MISSING_BLOCK_LABEL_112;
        int k;
        if(flag)
            j = byte0;
        else
            j = 1;
        mState = j;
        j = i;
_L5:
        obj;
        JVM INSTR monitorexit ;
        return j;
_L2:
        j = i;
        if(flag) goto _L5; else goto _L4
_L4:
        k = mState;
        j = i;
        if(k != 2) goto _L5; else goto _L3
    }

    public int setMeasurementMode(int i)
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState == 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("setMeasurementMode() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_59;
        }
        i = native_setMeasurementMode(i);
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int setScalingMode(int i)
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState == 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #198 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("setScalingMode() called in wrong state: ").append(mState).toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_59;
        }
        i = native_setScalingMode(i);
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public int setServerDiedListener(OnServerDiedListener onserverdiedlistener)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mServerDiedListener = onserverdiedlistener;
        obj;
        JVM INSTR monitorexit ;
        return 0;
        onserverdiedlistener;
        throw onserverdiedlistener;
    }

    public static final int ALREADY_EXISTS = -2;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -4;
    public static final int ERROR_DEAD_OBJECT = -7;
    public static final int ERROR_INVALID_OPERATION = -5;
    public static final int ERROR_NO_INIT = -3;
    public static final int ERROR_NO_MEMORY = -6;
    public static final int MEASUREMENT_MODE_NONE = 0;
    public static final int MEASUREMENT_MODE_PEAK_RMS = 1;
    private static final int NATIVE_EVENT_FFT_CAPTURE = 1;
    private static final int NATIVE_EVENT_PCM_CAPTURE = 0;
    private static final int NATIVE_EVENT_SERVER_DIED = 2;
    public static final int SCALING_MODE_AS_PLAYED = 1;
    public static final int SCALING_MODE_NORMALIZED = 0;
    public static final int STATE_ENABLED = 2;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_UNINITIALIZED = 0;
    public static final int SUCCESS = 0;
    private static final String TAG = "Visualizer-JAVA";
    private OnDataCaptureListener mCaptureListener;
    private int mId;
    private long mJniData;
    private final Object mListenerLock;
    private NativeEventHandler mNativeEventHandler;
    private long mNativeVisualizer;
    private OnServerDiedListener mServerDiedListener;
    private int mState;
    private final Object mStateLock;

    static 
    {
        System.loadLibrary("audioeffect_jni");
        native_init();
    }
}
