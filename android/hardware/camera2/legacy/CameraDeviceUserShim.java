// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.hardware.camera2.*;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.*;
import android.system.OsConstants;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;

// Referenced classes of package android.hardware.camera2.legacy:
//            LegacyExceptionUtils, LegacyMetadataMapper, LegacyCameraDevice

public class CameraDeviceUserShim
    implements ICameraDeviceUser
{
    private static class CameraCallbackThread
        implements ICameraDeviceCallbacks
    {

        static ICameraDeviceCallbacks _2D_get0(CameraCallbackThread cameracallbackthread)
        {
            return cameracallbackthread.mCallbacks;
        }

        private Handler getHandler()
        {
            if(mHandler == null)
                mHandler = new CallbackHandler(mHandlerThread.getLooper());
            return mHandler;
        }

        public IBinder asBinder()
        {
            return null;
        }

        public void close()
        {
            mHandlerThread.quitSafely();
        }

        public void onCaptureStarted(CaptureResultExtras captureresultextras, long l)
        {
            captureresultextras = getHandler().obtainMessage(2, (int)(l & 0xffffffffL), (int)(l >> 32 & 0xffffffffL), captureresultextras);
            getHandler().sendMessage(captureresultextras);
        }

        public void onDeviceError(int i, CaptureResultExtras captureresultextras)
        {
            captureresultextras = getHandler().obtainMessage(0, i, 0, captureresultextras);
            getHandler().sendMessage(captureresultextras);
        }

        public void onDeviceIdle()
        {
            Message message = getHandler().obtainMessage(1);
            getHandler().sendMessage(message);
        }

        public void onPrepared(int i)
        {
            Message message = getHandler().obtainMessage(4, i, 0);
            getHandler().sendMessage(message);
        }

        public void onRepeatingRequestError(long l, int i)
        {
            Message message = getHandler().obtainMessage(5, ((Object) (new Object[] {
                Long.valueOf(l), Integer.valueOf(i)
            })));
            getHandler().sendMessage(message);
        }

        public void onRequestQueueEmpty()
        {
            Message message = getHandler().obtainMessage(6, 0, 0);
            getHandler().sendMessage(message);
        }

        public void onResultReceived(CameraMetadataNative camerametadatanative, CaptureResultExtras captureresultextras)
        {
            camerametadatanative = getHandler().obtainMessage(3, ((Object) (new Object[] {
                camerametadatanative, captureresultextras
            })));
            getHandler().sendMessage(camerametadatanative);
        }

        private static final int CAMERA_ERROR = 0;
        private static final int CAMERA_IDLE = 1;
        private static final int CAPTURE_STARTED = 2;
        private static final int PREPARED = 4;
        private static final int REPEATING_REQUEST_ERROR = 5;
        private static final int REQUEST_QUEUE_EMPTY = 6;
        private static final int RESULT_RECEIVED = 3;
        private final ICameraDeviceCallbacks mCallbacks;
        private Handler mHandler;
        private final HandlerThread mHandlerThread = new HandlerThread("LegacyCameraCallback");

        public CameraCallbackThread(ICameraDeviceCallbacks icameradevicecallbacks)
        {
            mCallbacks = icameradevicecallbacks;
            mHandlerThread.start();
        }
    }

    private class CameraCallbackThread.CallbackHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 6: default 48
        //                       0 114
        //                       1 144
        //                       2 159
        //                       3 213
        //                       4 252
        //                       5 275
        //                       6 324;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L8:
            break MISSING_BLOCK_LABEL_324;
_L1:
            RemoteException remoteexception;
            IllegalArgumentException illegalargumentexception = JVM INSTR new #32  <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #34  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalargumentexception.IllegalArgumentException(stringbuilder.append("Unknown callback message ").append(message.what).toString());
            throw illegalargumentexception;
_L2:
            int i = message.arg1;
            CaptureResultExtras captureresultextras1 = (CaptureResultExtras)message.obj;
            CameraCallbackThread._2D_get0(CameraCallbackThread.this).onDeviceError(i, captureresultextras1);
_L9:
            return;
_L3:
            try
            {
                CameraCallbackThread._2D_get0(CameraCallbackThread.this).onDeviceIdle();
            }
            // Misplaced declaration of an exception variable
            catch(RemoteException remoteexception)
            {
                throw new IllegalStateException((new StringBuilder()).append("Received remote exception during camera callback ").append(message.what).toString(), remoteexception);
            }
              goto _L9
_L4:
            long l = message.arg2;
            long l1 = message.arg1;
            CaptureResultExtras captureresultextras2 = (CaptureResultExtras)message.obj;
            CameraCallbackThread._2D_get0(CameraCallbackThread.this).onCaptureStarted(captureresultextras2, (l & 0xffffffffL) << 32 | l1 & 0xffffffffL);
              goto _L9
_L5:
            Object aobj[] = (Object[])message.obj;
            CameraMetadataNative camerametadatanative = (CameraMetadataNative)aobj[0];
            CaptureResultExtras captureresultextras = (CaptureResultExtras)aobj[1];
            CameraCallbackThread._2D_get0(CameraCallbackThread.this).onResultReceived(camerametadatanative, captureresultextras);
              goto _L9
_L6:
            int j = message.arg1;
            CameraCallbackThread._2D_get0(CameraCallbackThread.this).onPrepared(j);
              goto _L9
_L7:
            Object aobj1[] = (Object[])message.obj;
            long l2 = ((Long)aobj1[0]).longValue();
            int k = ((Integer)aobj1[1]).intValue();
            CameraCallbackThread._2D_get0(CameraCallbackThread.this).onRepeatingRequestError(l2, k);
              goto _L9
            CameraCallbackThread._2D_get0(CameraCallbackThread.this).onRequestQueueEmpty();
              goto _L9
        }

        final CameraCallbackThread this$1;

        public CameraCallbackThread.CallbackHandler(Looper looper)
        {
            this$1 = CameraCallbackThread.this;
            super(looper);
        }
    }

    private static class CameraLooper
        implements Runnable, AutoCloseable
    {

        public void close()
        {
            if(mLooper == null)
                return;
            mLooper.quitSafely();
            try
            {
                mThread.join();
            }
            catch(InterruptedException interruptedexception)
            {
                throw new AssertionError(interruptedexception);
            }
            mLooper = null;
        }

        public Camera getCamera()
        {
            return mCamera;
        }

        public void run()
        {
            Looper.prepare();
            mLooper = Looper.myLooper();
            mInitErrors = mCamera.cameraInitUnspecified(mCameraId);
            mStartDone.open();
            Looper.loop();
        }

        public int waitForOpen(int i)
        {
            if(!mStartDone.block(i))
            {
                Log.e("CameraDeviceUserShim", "waitForOpen - Camera failed to open after timeout of 5000 ms");
                try
                {
                    mCamera.release();
                }
                catch(RuntimeException runtimeexception)
                {
                    Log.e("CameraDeviceUserShim", "connectBinderShim - Failed to release camera after timeout ", runtimeexception);
                }
                throw new ServiceSpecificException(10);
            } else
            {
                return mInitErrors;
            }
        }

        private final Camera mCamera = Camera.openUninitialized();
        private final int mCameraId;
        private volatile int mInitErrors;
        private Looper mLooper;
        private final ConditionVariable mStartDone = new ConditionVariable();
        private final Thread mThread = new Thread(this);

        public CameraLooper(int i)
        {
            mCameraId = i;
            mThread.start();
        }
    }


    protected CameraDeviceUserShim(int i, LegacyCameraDevice legacycameradevice, CameraCharacteristics cameracharacteristics, CameraLooper cameralooper, CameraCallbackThread cameracallbackthread)
    {
        mLegacyDevice = legacycameradevice;
        mConfiguring = false;
        mCameraCharacteristics = cameracharacteristics;
        mCameraInit = cameralooper;
        mCameraCallbacks = cameracallbackthread;
        mSurfaceIdCounter = 0;
    }

    public static CameraDeviceUserShim connectBinderShim(ICameraDeviceCallbacks icameradevicecallbacks, int i)
    {
        CameraLooper cameralooper = new CameraLooper(i);
        icameradevicecallbacks = new CameraCallbackThread(icameradevicecallbacks);
        int j = cameralooper.waitForOpen(5000);
        Camera camera = cameralooper.getCamera();
        LegacyExceptionUtils.throwOnServiceError(j);
        camera.disableShutterSound();
        android.hardware.Camera.CameraInfo camerainfo = new android.hardware.Camera.CameraInfo();
        Camera.getCameraInfo(i, camerainfo);
        Object obj;
        try
        {
            obj = camera.getParameters();
        }
        // Misplaced declaration of an exception variable
        catch(ICameraDeviceCallbacks icameradevicecallbacks)
        {
            throw new ServiceSpecificException(10, (new StringBuilder()).append("Unable to get initial parameters: ").append(icameradevicecallbacks.getMessage()).toString());
        }
        obj = LegacyMetadataMapper.createCharacteristics(((android.hardware.Camera.Parameters) (obj)), camerainfo);
        return new CameraDeviceUserShim(i, new LegacyCameraDevice(i, camera, ((CameraCharacteristics) (obj)), icameradevicecallbacks), ((CameraCharacteristics) (obj)), cameralooper, icameradevicecallbacks);
    }

    private static int translateErrorsFromCamera1(int i)
    {
        if(i == -OsConstants.EACCES)
            return 1;
        else
            return i;
    }

    public IBinder asBinder()
    {
        return null;
    }

    public void beginConfigure()
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot begin configure, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot begin configure, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot begin configure, configuration change already in progress.");
                    ServiceSpecificException servicespecificexception = JVM INSTR new #120 <Class ServiceSpecificException>;
                    servicespecificexception.ServiceSpecificException(10, "Cannot begin configure, configuration change already in progress.");
                    throw servicespecificexception;
                }
                break label0;
            }
        }
        mConfiguring = true;
        obj;
        JVM INSTR monitorexit ;
    }

    public long cancelRequest(int i)
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot cancel request, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot cancel request, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot cancel request, configuration change in progress.");
                    ServiceSpecificException servicespecificexception = JVM INSTR new #120 <Class ServiceSpecificException>;
                    servicespecificexception.ServiceSpecificException(10, "Cannot cancel request, configuration change in progress.");
                    throw servicespecificexception;
                }
                break label0;
            }
        }
        return mLegacyDevice.cancelRequest(i);
    }

    public CameraMetadataNative createDefaultRequest(int i)
    {
        if(mLegacyDevice.isClosed())
        {
            Log.e("CameraDeviceUserShim", "Cannot create default request, device has been closed.");
            throw new ServiceSpecificException(4, "Cannot create default request, device has been closed.");
        }
        CameraMetadataNative camerametadatanative;
        try
        {
            camerametadatanative = LegacyMetadataMapper.createRequestTemplate(mCameraCharacteristics, i);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.e("CameraDeviceUserShim", "createDefaultRequest - invalid templateId specified");
            throw new ServiceSpecificException(3, "createDefaultRequest - invalid templateId specified");
        }
        return camerametadatanative;
    }

    public int createInputStream(int i, int j, int k)
    {
        Log.e("CameraDeviceUserShim", "Creating input stream is not supported on legacy devices");
        throw new ServiceSpecificException(10, "Creating input stream is not supported on legacy devices");
    }

    public int createStream(OutputConfiguration outputconfiguration)
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot create stream, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot create stream, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(!mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot create stream, beginConfigure hasn't been called yet.");
                    outputconfiguration = JVM INSTR new #120 <Class ServiceSpecificException>;
                    outputconfiguration.ServiceSpecificException(10, "Cannot create stream, beginConfigure hasn't been called yet.");
                    throw outputconfiguration;
                }
                break label0;
            }
        }
        int i;
        if(outputconfiguration.getRotation() != 0)
        {
            Log.e("CameraDeviceUserShim", "Cannot create stream, stream rotation is not supported.");
            outputconfiguration = JVM INSTR new #120 <Class ServiceSpecificException>;
            outputconfiguration.ServiceSpecificException(3, "Cannot create stream, stream rotation is not supported.");
            throw outputconfiguration;
        }
        i = mSurfaceIdCounter + 1;
        mSurfaceIdCounter = i;
        mSurfaces.put(i, outputconfiguration.getSurface());
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public void deleteStream(int i)
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot delete stream, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot delete stream, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(!mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot delete stream, no configuration change in progress.");
                    ServiceSpecificException servicespecificexception = JVM INSTR new #120 <Class ServiceSpecificException>;
                    servicespecificexception.ServiceSpecificException(10, "Cannot delete stream, no configuration change in progress.");
                    throw servicespecificexception;
                }
                break label0;
            }
        }
        int j = mSurfaces.indexOfKey(i);
        if(j >= 0)
            break MISSING_BLOCK_LABEL_134;
        Object obj1 = JVM INSTR new #122 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append("Cannot delete stream, stream id ").append(i).append(" doesn't exist.").toString();
        Log.e("CameraDeviceUserShim", ((String) (obj1)));
        ServiceSpecificException servicespecificexception1 = JVM INSTR new #120 <Class ServiceSpecificException>;
        servicespecificexception1.ServiceSpecificException(3, ((String) (obj1)));
        throw servicespecificexception1;
        mSurfaces.removeAt(j);
        obj;
        JVM INSTR monitorexit ;
    }

    public void disconnect()
    {
        if(mLegacyDevice.isClosed())
            Log.w("CameraDeviceUserShim", "Cannot disconnect, device has already been closed.");
        mLegacyDevice.close();
        mCameraInit.close();
        mCameraCallbacks.close();
        return;
        Exception exception;
        exception;
        mCameraInit.close();
        mCameraCallbacks.close();
        throw exception;
    }

    public void endConfigure(int i)
    {
        if(!mLegacyDevice.isClosed())
            break MISSING_BLOCK_LABEL_48;
        Log.e("CameraDeviceUserShim", "Cannot end configure, device has been closed.");
        Object obj = mConfigureLock;
        obj;
        JVM INSTR monitorenter ;
        mConfiguring = false;
        obj;
        JVM INSTR monitorexit ;
        throw new ServiceSpecificException(4, "Cannot end configure, device has been closed.");
        Exception exception1;
        exception1;
        throw exception1;
        if(i == 0)
            break MISSING_BLOCK_LABEL_90;
        Log.e("CameraDeviceUserShim", "LEGACY devices do not support this operating mode");
        Object obj2 = mConfigureLock;
        obj2;
        JVM INSTR monitorenter ;
        mConfiguring = false;
        obj2;
        JVM INSTR monitorexit ;
        throw new ServiceSpecificException(3, "LEGACY devices do not support this operating mode");
        Exception exception;
        exception;
        throw exception;
label0:
        {
            obj3 = null;
            synchronized(mConfigureLock)
            {
                if(!mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot end configure, no configuration change in progress.");
                    obj3 = JVM INSTR new #120 <Class ServiceSpecificException>;
                    ((ServiceSpecificException) (obj3)).ServiceSpecificException(10, "Cannot end configure, no configuration change in progress.");
                    throw obj3;
                }
                break label0;
            }
        }
        if(mSurfaces != null)
            obj3 = mSurfaces.clone();
        mConfiguring = false;
        obj1;
        JVM INSTR monitorexit ;
        mLegacyDevice.configureOutputs(((SparseArray) (obj3)));
        return;
    }

    public void finalizeOutputConfigurations(int i, OutputConfiguration outputconfiguration)
    {
        Log.e("CameraDeviceUserShim", "Finalizing output configuration is not supported on legacy devices");
        throw new ServiceSpecificException(10, "Finalizing output configuration is not supported on legacy devices");
    }

    public long flush()
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot flush, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot flush, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot flush, configuration change in progress.");
                    ServiceSpecificException servicespecificexception = JVM INSTR new #120 <Class ServiceSpecificException>;
                    servicespecificexception.ServiceSpecificException(10, "Cannot flush, configuration change in progress.");
                    throw servicespecificexception;
                }
                break label0;
            }
        }
        return mLegacyDevice.flush();
    }

    public CameraMetadataNative getCameraInfo()
    {
        Log.e("CameraDeviceUserShim", "getCameraInfo unimplemented.");
        return null;
    }

    public Surface getInputSurface()
    {
        Log.e("CameraDeviceUserShim", "Getting input surface is not supported on legacy devices");
        throw new ServiceSpecificException(10, "Getting input surface is not supported on legacy devices");
    }

    public void prepare(int i)
    {
        if(mLegacyDevice.isClosed())
        {
            Log.e("CameraDeviceUserShim", "Cannot prepare stream, device has been closed.");
            throw new ServiceSpecificException(4, "Cannot prepare stream, device has been closed.");
        } else
        {
            mCameraCallbacks.onPrepared(i);
            return;
        }
    }

    public void prepare2(int i, int j)
    {
        prepare(j);
    }

    public SubmitInfo submitRequest(CaptureRequest capturerequest, boolean flag)
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot submit request, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot submit request, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot submit request, configuration change in progress.");
                    capturerequest = JVM INSTR new #120 <Class ServiceSpecificException>;
                    capturerequest.ServiceSpecificException(10, "Cannot submit request, configuration change in progress.");
                    throw capturerequest;
                }
                break label0;
            }
        }
        return mLegacyDevice.submitRequest(capturerequest, flag);
    }

    public SubmitInfo submitRequestList(CaptureRequest acapturerequest[], boolean flag)
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot submit request list, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot submit request list, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot submit request, configuration change in progress.");
                    acapturerequest = JVM INSTR new #120 <Class ServiceSpecificException>;
                    acapturerequest.ServiceSpecificException(10, "Cannot submit request, configuration change in progress.");
                    throw acapturerequest;
                }
                break label0;
            }
        }
        return mLegacyDevice.submitRequestList(acapturerequest, flag);
    }

    public void tearDown(int i)
    {
        if(mLegacyDevice.isClosed())
        {
            Log.e("CameraDeviceUserShim", "Cannot tear down stream, device has been closed.");
            throw new ServiceSpecificException(4, "Cannot tear down stream, device has been closed.");
        } else
        {
            return;
        }
    }

    public void waitUntilIdle()
        throws RemoteException
    {
label0:
        {
            if(mLegacyDevice.isClosed())
            {
                Log.e("CameraDeviceUserShim", "Cannot wait until idle, device has been closed.");
                throw new ServiceSpecificException(4, "Cannot wait until idle, device has been closed.");
            }
            synchronized(mConfigureLock)
            {
                if(mConfiguring)
                {
                    Log.e("CameraDeviceUserShim", "Cannot wait until idle, configuration change in progress.");
                    ServiceSpecificException servicespecificexception = JVM INSTR new #120 <Class ServiceSpecificException>;
                    servicespecificexception.ServiceSpecificException(10, "Cannot wait until idle, configuration change in progress.");
                    throw servicespecificexception;
                }
                break label0;
            }
        }
        mLegacyDevice.waitUntilIdle();
    }

    private static final boolean DEBUG = false;
    private static final int OPEN_CAMERA_TIMEOUT_MS = 5000;
    private static final String TAG = "CameraDeviceUserShim";
    private final CameraCallbackThread mCameraCallbacks;
    private final CameraCharacteristics mCameraCharacteristics;
    private final CameraLooper mCameraInit;
    private final Object mConfigureLock = new Object();
    private boolean mConfiguring;
    private final LegacyCameraDevice mLegacyDevice;
    private int mSurfaceIdCounter;
    private final SparseArray mSurfaces = new SparseArray();
}
