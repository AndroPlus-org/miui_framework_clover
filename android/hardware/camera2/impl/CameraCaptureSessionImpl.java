// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;

import android.hardware.camera2.*;
import android.hardware.camera2.dispatch.*;
import android.hardware.camera2.utils.TaskDrainer;
import android.hardware.camera2.utils.TaskSingleDrainer;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.hardware.camera2.impl:
//            CameraCaptureSessionCore, CameraDeviceImpl

public class CameraCaptureSessionImpl extends CameraCaptureSession
    implements CameraCaptureSessionCore
{
    private class AbortDrainListener
        implements android.hardware.camera2.utils.TaskDrainer.DrainListener
    {

        public void onDrained()
        {
            Object obj = CameraCaptureSessionImpl._2D_get2(CameraCaptureSessionImpl.this).mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            boolean flag = CameraCaptureSessionImpl._2D_get5(CameraCaptureSessionImpl.this);
            if(!flag)
                break MISSING_BLOCK_LABEL_28;
            obj;
            JVM INSTR monitorexit ;
            return;
            CameraCaptureSessionImpl._2D_get4(CameraCaptureSessionImpl.this).beginDrain();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final CameraCaptureSessionImpl this$0;

        private AbortDrainListener()
        {
            this$0 = CameraCaptureSessionImpl.this;
            super();
        }

        AbortDrainListener(AbortDrainListener abortdrainlistener)
        {
            this();
        }
    }

    private class IdleDrainListener
        implements android.hardware.camera2.utils.TaskDrainer.DrainListener
    {

        public void onDrained()
        {
            Object obj = CameraCaptureSessionImpl._2D_get2(CameraCaptureSessionImpl.this).mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            boolean flag = CameraCaptureSessionImpl._2D_get5(CameraCaptureSessionImpl.this);
            if(!flag)
                break MISSING_BLOCK_LABEL_28;
            obj;
            JVM INSTR monitorexit ;
            return;
            CameraCaptureSessionImpl._2D_get2(CameraCaptureSessionImpl.this).configureStreamsChecked(null, null, 0);
_L2:
            obj;
            JVM INSTR monitorexit ;
            return;
            CameraAccessException cameraaccessexception;
            cameraaccessexception;
            StringBuilder stringbuilder = JVM INSTR new #47  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("CameraCaptureSession", stringbuilder.append(CameraCaptureSessionImpl._2D_get3(CameraCaptureSessionImpl.this)).append("Exception while unconfiguring outputs: ").toString(), cameraaccessexception);
            continue; /* Loop/switch isn't completed */
            Object obj1;
            obj1;
            throw obj1;
            obj1;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final CameraCaptureSessionImpl this$0;

        private IdleDrainListener()
        {
            this$0 = CameraCaptureSessionImpl.this;
            super();
        }

        IdleDrainListener(IdleDrainListener idledrainlistener)
        {
            this();
        }
    }

    private class SequenceDrainListener
        implements android.hardware.camera2.utils.TaskDrainer.DrainListener
    {

        public void onDrained()
        {
            CameraCaptureSessionImpl._2D_get6(CameraCaptureSessionImpl.this).onClosed(CameraCaptureSessionImpl.this);
            if(CameraCaptureSessionImpl._2D_get5(CameraCaptureSessionImpl.this))
            {
                return;
            } else
            {
                CameraCaptureSessionImpl._2D_get0(CameraCaptureSessionImpl.this).beginDrain();
                return;
            }
        }

        final CameraCaptureSessionImpl this$0;

        private SequenceDrainListener()
        {
            this$0 = CameraCaptureSessionImpl.this;
            super();
        }

        SequenceDrainListener(SequenceDrainListener sequencedrainlistener)
        {
            this();
        }
    }


    static TaskSingleDrainer _2D_get0(CameraCaptureSessionImpl cameracapturesessionimpl)
    {
        return cameracapturesessionimpl.mAbortDrainer;
    }

    static boolean _2D_get1(CameraCaptureSessionImpl cameracapturesessionimpl)
    {
        return cameracapturesessionimpl.mAborting;
    }

    static CameraDeviceImpl _2D_get2(CameraCaptureSessionImpl cameracapturesessionimpl)
    {
        return cameracapturesessionimpl.mDeviceImpl;
    }

    static String _2D_get3(CameraCaptureSessionImpl cameracapturesessionimpl)
    {
        return cameracapturesessionimpl.mIdString;
    }

    static TaskSingleDrainer _2D_get4(CameraCaptureSessionImpl cameracapturesessionimpl)
    {
        return cameracapturesessionimpl.mIdleDrainer;
    }

    static boolean _2D_get5(CameraCaptureSessionImpl cameracapturesessionimpl)
    {
        return cameracapturesessionimpl.mSkipUnconfigure;
    }

    static android.hardware.camera2.CameraCaptureSession.StateCallback _2D_get6(CameraCaptureSessionImpl cameracapturesessionimpl)
    {
        return cameracapturesessionimpl.mStateCallback;
    }

    static boolean _2D_set0(CameraCaptureSessionImpl cameracapturesessionimpl, boolean flag)
    {
        cameracapturesessionimpl.mAborting = flag;
        return flag;
    }

    static void _2D_wrap0(CameraCaptureSessionImpl cameracapturesessionimpl, int i)
    {
        cameracapturesessionimpl.finishPendingSequence(i);
    }

    CameraCaptureSessionImpl(int i, Surface surface, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler, CameraDeviceImpl cameradeviceimpl, Handler handler1, boolean flag)
    {
        mClosed = false;
        mSkipUnconfigure = false;
        if(statecallback == null)
            throw new IllegalArgumentException("callback must not be null");
        mId = i;
        mIdString = String.format("Session %d: ", new Object[] {
            Integer.valueOf(mId)
        });
        mInput = surface;
        mStateHandler = CameraDeviceImpl.checkHandler(handler);
        mStateCallback = createUserStateCallbackProxy(mStateHandler, statecallback);
        mDeviceHandler = (Handler)Preconditions.checkNotNull(handler1, "deviceStateHandler must not be null");
        mDeviceImpl = (CameraDeviceImpl)Preconditions.checkNotNull(cameradeviceimpl, "deviceImpl must not be null");
        mSequenceDrainer = new TaskDrainer(mDeviceHandler, new SequenceDrainListener(null), "seq");
        mIdleDrainer = new TaskSingleDrainer(mDeviceHandler, new IdleDrainListener(null), "idle");
        mAbortDrainer = new TaskSingleDrainer(mDeviceHandler, new AbortDrainListener(null), "abort");
        if(flag)
        {
            mStateCallback.onConfigured(this);
            mConfigureSuccess = true;
        } else
        {
            mStateCallback.onConfigureFailed(this);
            mClosed = true;
            Log.e("CameraCaptureSession", (new StringBuilder()).append(mIdString).append("Failed to create capture session; configuration failed").toString());
            mConfigureSuccess = false;
        }
    }

    private int addPendingSequence(int i)
    {
        mSequenceDrainer.taskStarted(Integer.valueOf(i));
        return i;
    }

    private void checkNotClosed()
    {
        if(mClosed)
            throw new IllegalStateException("Session has been closed; further changes are illegal.");
        else
            return;
    }

    private CameraDeviceImpl.CaptureCallback createCaptureCallbackProxy(Handler handler, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback)
    {
        Object obj = new CameraDeviceImpl.CaptureCallback() {

            public void onCaptureBufferLost(CameraDevice cameradevice, CaptureRequest capturerequest, Surface surface, long l)
            {
            }

            public void onCaptureCompleted(CameraDevice cameradevice, CaptureRequest capturerequest, TotalCaptureResult totalcaptureresult)
            {
            }

            public void onCaptureFailed(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureFailure capturefailure)
            {
            }

            public void onCapturePartial(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureResult captureresult)
            {
            }

            public void onCaptureProgressed(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureResult captureresult)
            {
            }

            public void onCaptureSequenceAborted(CameraDevice cameradevice, int i)
            {
                CameraCaptureSessionImpl._2D_wrap0(CameraCaptureSessionImpl.this, i);
            }

            public void onCaptureSequenceCompleted(CameraDevice cameradevice, int i, long l)
            {
                CameraCaptureSessionImpl._2D_wrap0(CameraCaptureSessionImpl.this, i);
            }

            public void onCaptureStarted(CameraDevice cameradevice, CaptureRequest capturerequest, long l, long l1)
            {
            }

            final CameraCaptureSessionImpl this$0;

            
            {
                this$0 = CameraCaptureSessionImpl.this;
                super();
            }
        }
;
        if(capturecallback == null)
        {
            return ((CameraDeviceImpl.CaptureCallback) (obj));
        } else
        {
            obj = new InvokeDispatcher(obj);
            return new CallbackProxies.DeviceCaptureCallbackProxy(new BroadcastDispatcher(new Dispatchable[] {
                new ArgumentReplacingDispatcher(new DuckTypingDispatcher(new HandlerDispatcher(new InvokeDispatcher(capturecallback), handler), android/hardware/camera2/CameraCaptureSession$CaptureCallback), 0, this), obj
            }));
        }
    }

    private android.hardware.camera2.CameraCaptureSession.StateCallback createUserStateCallbackProxy(Handler handler, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback)
    {
        return new CallbackProxies.SessionStateCallbackProxy(new HandlerDispatcher(new InvokeDispatcher(statecallback), handler));
    }

    private void finishPendingSequence(int i)
    {
        mSequenceDrainer.taskFinished(Integer.valueOf(i));
_L1:
        return;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        Log.w("CameraCaptureSession", illegalstateexception.getMessage());
          goto _L1
    }

    public void abortCaptures()
        throws CameraAccessException
    {
        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        checkNotClosed();
        if(!mAborting)
            break MISSING_BLOCK_LABEL_55;
        StringBuilder stringbuilder = JVM INSTR new #181 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("CameraCaptureSession", stringbuilder.append(mIdString).append("abortCaptures - Session is already aborting; doing nothing").toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        mAborting = true;
        mAbortDrainer.taskStarted();
        mDeviceImpl.flush();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int capture(CaptureRequest capturerequest, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(capturerequest == null)
            throw new IllegalArgumentException("request must not be null");
        if(capturerequest.isReprocess() && isReprocessable() ^ true)
            throw new IllegalArgumentException("this capture session cannot handle reprocess requests");
        if(!mDeviceImpl.isPrivilegedApp() && capturerequest.isReprocess() && capturerequest.getReprocessableSessionId() != mId)
            throw new IllegalArgumentException("capture request was created for another session");
        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkNotClosed();
        handler = CameraDeviceImpl.checkHandler(handler, capturecallback);
        i = addPendingSequence(mDeviceImpl.capture(capturerequest, createCaptureCallbackProxy(handler, capturecallback), mDeviceHandler));
        obj;
        JVM INSTR monitorexit ;
        return i;
        capturerequest;
        throw capturerequest;
    }

    public int captureBurst(List list, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(list == null)
            throw new IllegalArgumentException("Requests must not be null");
        if(list.isEmpty())
            throw new IllegalArgumentException("Requests must have at least one element");
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            CaptureRequest capturerequest = (CaptureRequest)iterator.next();
            if(capturerequest.isReprocess())
            {
                if(!isReprocessable())
                    throw new IllegalArgumentException("This capture session cannot handle reprocess requests");
                if(capturerequest.getReprocessableSessionId() != mId)
                    throw new IllegalArgumentException("Capture request was created for another session");
            }
        }

        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkNotClosed();
        handler = CameraDeviceImpl.checkHandler(handler, capturecallback);
        i = addPendingSequence(mDeviceImpl.captureBurst(list, createCaptureCallbackProxy(handler, capturecallback), mDeviceHandler));
        obj;
        JVM INSTR monitorexit ;
        return i;
        list;
        throw list;
    }

    public void close()
    {
        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mClosed;
        if(!flag)
            break MISSING_BLOCK_LABEL_22;
        obj;
        JVM INSTR monitorexit ;
        return;
        mClosed = true;
        mDeviceImpl.stopRepeating();
_L1:
        mSequenceDrainer.beginDrain();
        obj;
        JVM INSTR monitorexit ;
        if(mInput != null)
            mInput.release();
        return;
        Object obj1;
        obj1;
        StringBuilder stringbuilder = JVM INSTR new #181 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("CameraCaptureSession", stringbuilder.append(mIdString).append("Exception while stopping repeating: ").toString(), ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
        obj1;
        mStateCallback.onClosed(this);
        obj;
        JVM INSTR monitorexit ;
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void finalizeOutputConfigurations(List list)
        throws CameraAccessException
    {
        mDeviceImpl.finalizeOutputConfigs(list);
    }

    public CameraDevice getDevice()
    {
        return mDeviceImpl;
    }

    public CameraDeviceImpl.StateCallbackKK getDeviceStateCallback()
    {
        return new CameraDeviceImpl.StateCallbackKK() {

            public void onActive(CameraDevice cameradevice)
            {
                CameraCaptureSessionImpl._2D_get4(CameraCaptureSessionImpl.this).taskStarted();
                mActive = true;
                CameraCaptureSessionImpl._2D_get6(CameraCaptureSessionImpl.this).onActive(session);
            }

            public void onBusy(CameraDevice cameradevice)
            {
                mBusy = true;
            }

            public void onDisconnected(CameraDevice cameradevice)
            {
                close();
            }

            public void onError(CameraDevice cameradevice, int i)
            {
                Log.wtf("CameraCaptureSession", (new StringBuilder()).append(CameraCaptureSessionImpl._2D_get3(CameraCaptureSessionImpl.this)).append("Got device error ").append(i).toString());
            }

            public void onIdle(CameraDevice cameradevice)
            {
                cameradevice = ((CameraDevice) (interfaceLock));
                cameradevice;
                JVM INSTR monitorenter ;
                boolean flag = CameraCaptureSessionImpl._2D_get1(CameraCaptureSessionImpl.this);
                cameradevice;
                JVM INSTR monitorexit ;
                if(!mBusy || !flag) goto _L2; else goto _L1
_L1:
                CameraCaptureSessionImpl._2D_get0(CameraCaptureSessionImpl.this).taskFinished();
                Object obj = interfaceLock;
                obj;
                JVM INSTR monitorenter ;
                CameraCaptureSessionImpl._2D_set0(CameraCaptureSessionImpl.this, false);
                obj;
                JVM INSTR monitorexit ;
_L2:
                if(mActive)
                    CameraCaptureSessionImpl._2D_get4(CameraCaptureSessionImpl.this).taskFinished();
                mBusy = false;
                mActive = false;
                CameraCaptureSessionImpl._2D_get6(CameraCaptureSessionImpl.this).onReady(session);
                return;
                obj;
                throw obj;
                cameradevice;
                throw cameradevice;
            }

            public void onOpened(CameraDevice cameradevice)
            {
                throw new AssertionError("Camera must already be open before creating a session");
            }

            public void onRequestQueueEmpty()
            {
                CameraCaptureSessionImpl._2D_get6(CameraCaptureSessionImpl.this).onCaptureQueueEmpty(session);
            }

            public void onSurfacePrepared(Surface surface)
            {
                CameraCaptureSessionImpl._2D_get6(CameraCaptureSessionImpl.this).onSurfacePrepared(session, surface);
            }

            public void onUnconfigured(CameraDevice cameradevice)
            {
            }

            private boolean mActive;
            private boolean mBusy;
            final CameraCaptureSessionImpl this$0;
            final Object val$interfaceLock;
            final CameraCaptureSession val$session;

            
            {
                this$0 = CameraCaptureSessionImpl.this;
                session = cameracapturesession;
                interfaceLock = obj;
                super();
                mBusy = false;
                mActive = false;
            }
        }
;
    }

    public Surface getInputSurface()
    {
        return mInput;
    }

    public boolean isAborting()
    {
        return mAborting;
    }

    public boolean isReprocessable()
    {
        boolean flag;
        if(mInput != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void prepare(int i, Surface surface)
        throws CameraAccessException
    {
        mDeviceImpl.prepare(i, surface);
    }

    public void prepare(Surface surface)
        throws CameraAccessException
    {
        mDeviceImpl.prepare(surface);
    }

    public void replaceSessionClose()
    {
        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        mSkipUnconfigure = true;
        close();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int setRepeatingBurst(List list, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(list == null)
            throw new IllegalArgumentException("requests must not be null");
        if(list.isEmpty())
            throw new IllegalArgumentException("requests must have at least one element");
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
            if(((CaptureRequest)iterator.next()).isReprocess())
                throw new IllegalArgumentException("repeating reprocess burst requests are not supported");

        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkNotClosed();
        handler = CameraDeviceImpl.checkHandler(handler, capturecallback);
        i = addPendingSequence(mDeviceImpl.setRepeatingBurst(list, createCaptureCallbackProxy(handler, capturecallback), mDeviceHandler));
        obj;
        JVM INSTR monitorexit ;
        return i;
        list;
        throw list;
    }

    public int setRepeatingRequest(CaptureRequest capturerequest, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(capturerequest == null)
            throw new IllegalArgumentException("request must not be null");
        if(capturerequest.isReprocess())
            throw new IllegalArgumentException("repeating reprocess requests are not supported");
        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkNotClosed();
        handler = CameraDeviceImpl.checkHandler(handler, capturecallback);
        i = addPendingSequence(mDeviceImpl.setRepeatingRequest(capturerequest, createCaptureCallbackProxy(handler, capturecallback), mDeviceHandler));
        obj;
        JVM INSTR monitorexit ;
        return i;
        capturerequest;
        throw capturerequest;
    }

    public void stopRepeating()
        throws CameraAccessException
    {
        Object obj = mDeviceImpl.mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        checkNotClosed();
        mDeviceImpl.stopRepeating();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void tearDown(Surface surface)
        throws CameraAccessException
    {
        mDeviceImpl.tearDown(surface);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "CameraCaptureSession";
    private final TaskSingleDrainer mAbortDrainer;
    private volatile boolean mAborting;
    private boolean mClosed;
    private final boolean mConfigureSuccess;
    private final Handler mDeviceHandler;
    private final CameraDeviceImpl mDeviceImpl;
    private final int mId;
    private final String mIdString;
    private final TaskSingleDrainer mIdleDrainer;
    private final Surface mInput;
    private final TaskDrainer mSequenceDrainer;
    private boolean mSkipUnconfigure;
    private final android.hardware.camera2.CameraCaptureSession.StateCallback mStateCallback;
    private final Handler mStateHandler;
}
