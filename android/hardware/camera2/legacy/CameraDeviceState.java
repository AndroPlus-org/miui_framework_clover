// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.os.Handler;
import android.util.Log;

// Referenced classes of package android.hardware.camera2.legacy:
//            RequestHolder

public class CameraDeviceState
{
    public static interface CameraDeviceStateListener
    {

        public abstract void onBusy();

        public abstract void onCaptureResult(CameraMetadataNative camerametadatanative, RequestHolder requestholder);

        public abstract void onCaptureStarted(RequestHolder requestholder, long l);

        public abstract void onConfiguring();

        public abstract void onError(int i, Object obj, RequestHolder requestholder);

        public abstract void onIdle();

        public abstract void onRepeatingRequestError(long l, int i);

        public abstract void onRequestQueueEmpty();
    }


    static int _2D_get0(CameraDeviceState cameradevicestate)
    {
        return cameradevicestate.mCurrentError;
    }

    static CameraDeviceStateListener _2D_get1(CameraDeviceState cameradevicestate)
    {
        return cameradevicestate.mCurrentListener;
    }

    static RequestHolder _2D_get2(CameraDeviceState cameradevicestate)
    {
        return cameradevicestate.mCurrentRequest;
    }

    public CameraDeviceState()
    {
        mCurrentState = 1;
        mCurrentError = -1;
        mCurrentRequest = null;
        mCurrentHandler = null;
        mCurrentListener = null;
    }

    private void doStateTransition(int i)
    {
        doStateTransition(i, 0L, -1);
    }

    private void doStateTransition(int i, final long timestamp, final int error)
    {
        if(i != mCurrentState)
        {
            String s = "UNKNOWN";
            String s1 = s;
            if(i >= 0)
            {
                s1 = s;
                if(i < sStateNames.length)
                    s1 = sStateNames[i];
            }
            Log.i("CameraDeviceState", (new StringBuilder()).append("Legacy camera service transitioning to state ").append(s1).toString());
        }
        if(i != 0 && i != 3 && mCurrentState != i && mCurrentHandler != null && mCurrentListener != null)
            mCurrentHandler.post(new Runnable() {

                public void run()
                {
                    CameraDeviceState._2D_get1(CameraDeviceState.this).onBusy();
                }

                final CameraDeviceState this$0;

            
            {
                this$0 = CameraDeviceState.this;
                super();
            }
            }
);
        i;
        JVM INSTR tableswitch 0 4: default 148
    //                   0 175
    //                   1 148
    //                   2 218
    //                   3 321
    //                   4 432;
           goto _L1 _L2 _L1 _L3 _L4 _L5
_L1:
        throw new IllegalStateException((new StringBuilder()).append("Transition to unknown state: ").append(i).toString());
_L2:
        if(mCurrentState != 0 && mCurrentHandler != null && mCurrentListener != null)
            mCurrentHandler.post(new Runnable() {

                public void run()
                {
                    CameraDeviceState._2D_get1(CameraDeviceState.this).onError(CameraDeviceState._2D_get0(CameraDeviceState.this), null, CameraDeviceState._2D_get2(CameraDeviceState.this));
                }

                final CameraDeviceState this$0;

            
            {
                this$0 = CameraDeviceState.this;
                super();
            }
            }
);
        mCurrentState = 0;
_L7:
        return;
_L3:
        if(mCurrentState != 1 && mCurrentState != 3)
        {
            Log.e("CameraDeviceState", (new StringBuilder()).append("Cannot call configure while in state: ").append(mCurrentState).toString());
            mCurrentError = 1;
            doStateTransition(0);
        } else
        {
            if(mCurrentState != 2 && mCurrentHandler != null && mCurrentListener != null)
                mCurrentHandler.post(new Runnable() {

                    public void run()
                    {
                        CameraDeviceState._2D_get1(CameraDeviceState.this).onConfiguring();
                    }

                    final CameraDeviceState this$0;

            
            {
                this$0 = CameraDeviceState.this;
                super();
            }
                }
);
            mCurrentState = 2;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(mCurrentState != 3)
            if(mCurrentState != 2 && mCurrentState != 4)
            {
                Log.e("CameraDeviceState", (new StringBuilder()).append("Cannot call idle while in state: ").append(mCurrentState).toString());
                mCurrentError = 1;
                doStateTransition(0);
            } else
            {
                if(mCurrentState != 3 && mCurrentHandler != null && mCurrentListener != null)
                    mCurrentHandler.post(new Runnable() {

                        public void run()
                        {
                            CameraDeviceState._2D_get1(CameraDeviceState.this).onIdle();
                        }

                        final CameraDeviceState this$0;

            
            {
                this$0 = CameraDeviceState.this;
                super();
            }
                    }
);
                mCurrentState = 3;
            }
        continue; /* Loop/switch isn't completed */
_L5:
        if(mCurrentState != 3 && mCurrentState != 4)
        {
            Log.e("CameraDeviceState", (new StringBuilder()).append("Cannot call capture while in state: ").append(mCurrentState).toString());
            mCurrentError = 1;
            doStateTransition(0);
        } else
        {
            if(mCurrentHandler != null && mCurrentListener != null)
                if(error != -1)
                    mCurrentHandler.post(new Runnable() {

                        public void run()
                        {
                            CameraDeviceState._2D_get1(CameraDeviceState.this).onError(error, null, CameraDeviceState._2D_get2(CameraDeviceState.this));
                        }

                        final CameraDeviceState this$0;
                        final int val$error;

            
            {
                this$0 = CameraDeviceState.this;
                error = i;
                super();
            }
                    }
);
                else
                    mCurrentHandler.post(new Runnable() {

                        public void run()
                        {
                            CameraDeviceState._2D_get1(CameraDeviceState.this).onCaptureStarted(CameraDeviceState._2D_get2(CameraDeviceState.this), timestamp);
                        }

                        final CameraDeviceState this$0;
                        final long val$timestamp;

            
            {
                this$0 = CameraDeviceState.this;
                timestamp = l;
                super();
            }
                    }
);
            mCurrentState = 4;
        }
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void setCameraDeviceCallbacks(Handler handler, CameraDeviceStateListener cameradevicestatelistener)
    {
        this;
        JVM INSTR monitorenter ;
        mCurrentHandler = handler;
        mCurrentListener = cameradevicestatelistener;
        this;
        JVM INSTR monitorexit ;
        return;
        handler;
        throw handler;
    }

    public boolean setCaptureResult(RequestHolder requestholder, CameraMetadataNative camerametadatanative)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = setCaptureResult(requestholder, camerametadatanative, -1, null);
        this;
        JVM INSTR monitorexit ;
        return flag;
        requestholder;
        throw requestholder;
    }

    public boolean setCaptureResult(RequestHolder requestholder, CameraMetadataNative camerametadatanative, int i, Object obj)
    {
        boolean flag = true;
        boolean flag1 = true;
        this;
        JVM INSTR monitorenter ;
        if(mCurrentState == 4)
            break MISSING_BLOCK_LABEL_77;
        requestholder = JVM INSTR new #101 <Class StringBuilder>;
        requestholder.StringBuilder();
        Log.e("CameraDeviceState", requestholder.append("Cannot receive result while in state: ").append(mCurrentState).toString());
        mCurrentError = 1;
        doStateTransition(0);
        i = mCurrentError;
        if(i != -1)
            flag1 = false;
        this;
        JVM INSTR monitorexit ;
        return flag1;
        if(mCurrentHandler == null || mCurrentListener == null)
            break MISSING_BLOCK_LABEL_123;
        if(i == -1)
            break MISSING_BLOCK_LABEL_142;
        camerametadatanative = mCurrentHandler;
        Runnable runnable = JVM INSTR new #6   <Class CameraDeviceState$1>;
        runnable.this. _cls1();
        camerametadatanative.post(runnable);
_L1:
        i = mCurrentError;
        if(i == -1)
            flag1 = flag;
        else
            flag1 = false;
        this;
        JVM INSTR monitorexit ;
        return flag1;
        obj = mCurrentHandler;
        Runnable runnable1 = JVM INSTR new #10  <Class CameraDeviceState$2>;
        runnable1.this. _cls2();
        ((Handler) (obj)).post(runnable1);
          goto _L1
        requestholder;
        throw requestholder;
    }

    public boolean setCaptureStart(RequestHolder requestholder, long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        mCurrentRequest = requestholder;
        doStateTransition(4, l, i);
        i = mCurrentError;
        boolean flag;
        if(i == -1)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        requestholder;
        throw requestholder;
    }

    public boolean setConfiguring()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        doStateTransition(2);
        i = mCurrentError;
        boolean flag;
        if(i == -1)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void setError(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mCurrentError = i;
        doStateTransition(0);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean setIdle()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        doStateTransition(3);
        i = mCurrentError;
        boolean flag;
        if(i == -1)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void setRepeatingRequestError(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        Handler handler = mCurrentHandler;
        Runnable runnable = JVM INSTR new #12  <Class CameraDeviceState$3>;
        runnable.this. _cls3();
        handler.post(runnable);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setRequestQueueEmpty()
    {
        this;
        JVM INSTR monitorenter ;
        Handler handler = mCurrentHandler;
        Runnable runnable = JVM INSTR new #14  <Class CameraDeviceState$4>;
        runnable.this. _cls4();
        handler.post(runnable);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final boolean DEBUG = false;
    public static final int NO_CAPTURE_ERROR = -1;
    private static final int STATE_CAPTURING = 4;
    private static final int STATE_CONFIGURING = 2;
    private static final int STATE_ERROR = 0;
    private static final int STATE_IDLE = 3;
    private static final int STATE_UNCONFIGURED = 1;
    private static final String TAG = "CameraDeviceState";
    private static final String sStateNames[] = {
        "ERROR", "UNCONFIGURED", "CONFIGURING", "IDLE", "CAPTURING"
    };
    private int mCurrentError;
    private Handler mCurrentHandler;
    private CameraDeviceStateListener mCurrentListener;
    private RequestHolder mCurrentRequest;
    private int mCurrentState;


    // Unreferenced inner class android/hardware/camera2/legacy/CameraDeviceState$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            CameraDeviceState._2D_get1(CameraDeviceState.this).onError(captureError, captureErrorArg, request);
        }

        final CameraDeviceState this$0;
        final int val$captureError;
        final Object val$captureErrorArg;
        final RequestHolder val$request;

            
            {
                this$0 = CameraDeviceState.this;
                captureError = i;
                captureErrorArg = obj;
                request = requestholder;
                super();
            }
    }


    // Unreferenced inner class android/hardware/camera2/legacy/CameraDeviceState$2

/* anonymous class */
    class _cls2
        implements Runnable
    {

        public void run()
        {
            CameraDeviceState._2D_get1(CameraDeviceState.this).onCaptureResult(result, request);
        }

        final CameraDeviceState this$0;
        final RequestHolder val$request;
        final CameraMetadataNative val$result;

            
            {
                this$0 = CameraDeviceState.this;
                result = camerametadatanative;
                request = requestholder;
                super();
            }
    }


    // Unreferenced inner class android/hardware/camera2/legacy/CameraDeviceState$3

/* anonymous class */
    class _cls3
        implements Runnable
    {

        public void run()
        {
            CameraDeviceState._2D_get1(CameraDeviceState.this).onRepeatingRequestError(lastFrameNumber, repeatingRequestId);
        }

        final CameraDeviceState this$0;
        final long val$lastFrameNumber;
        final int val$repeatingRequestId;

            
            {
                this$0 = CameraDeviceState.this;
                lastFrameNumber = l;
                repeatingRequestId = i;
                super();
            }
    }


    // Unreferenced inner class android/hardware/camera2/legacy/CameraDeviceState$4

/* anonymous class */
    class _cls4
        implements Runnable
    {

        public void run()
        {
            CameraDeviceState._2D_get1(CameraDeviceState.this).onRequestQueueEmpty();
        }

        final CameraDeviceState this$0;

            
            {
                this$0 = CameraDeviceState.this;
                super();
            }
    }

}
