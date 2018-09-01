// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.os.Handler;
import android.view.Surface;
import java.util.List;

// Referenced classes of package android.hardware.camera2:
//            CameraAccessException, CaptureRequest, CameraDevice, TotalCaptureResult, 
//            CaptureFailure, CaptureResult

public abstract class CameraCaptureSession
    implements AutoCloseable
{
    public static abstract class CaptureCallback
    {

        public void onCaptureBufferLost(CameraCaptureSession cameracapturesession, CaptureRequest capturerequest, Surface surface, long l)
        {
        }

        public void onCaptureCompleted(CameraCaptureSession cameracapturesession, CaptureRequest capturerequest, TotalCaptureResult totalcaptureresult)
        {
        }

        public void onCaptureFailed(CameraCaptureSession cameracapturesession, CaptureRequest capturerequest, CaptureFailure capturefailure)
        {
        }

        public void onCapturePartial(CameraCaptureSession cameracapturesession, CaptureRequest capturerequest, CaptureResult captureresult)
        {
        }

        public void onCaptureProgressed(CameraCaptureSession cameracapturesession, CaptureRequest capturerequest, CaptureResult captureresult)
        {
        }

        public void onCaptureSequenceAborted(CameraCaptureSession cameracapturesession, int i)
        {
        }

        public void onCaptureSequenceCompleted(CameraCaptureSession cameracapturesession, int i, long l)
        {
        }

        public void onCaptureStarted(CameraCaptureSession cameracapturesession, CaptureRequest capturerequest, long l, long l1)
        {
        }

        public static final int NO_FRAMES_CAPTURED = -1;

        public CaptureCallback()
        {
        }
    }

    public static abstract class StateCallback
    {

        public void onActive(CameraCaptureSession cameracapturesession)
        {
        }

        public void onCaptureQueueEmpty(CameraCaptureSession cameracapturesession)
        {
        }

        public void onClosed(CameraCaptureSession cameracapturesession)
        {
        }

        public abstract void onConfigureFailed(CameraCaptureSession cameracapturesession);

        public abstract void onConfigured(CameraCaptureSession cameracapturesession);

        public void onReady(CameraCaptureSession cameracapturesession)
        {
        }

        public void onSurfacePrepared(CameraCaptureSession cameracapturesession, Surface surface)
        {
        }

        public StateCallback()
        {
        }
    }


    public CameraCaptureSession()
    {
    }

    public abstract void abortCaptures()
        throws CameraAccessException;

    public abstract int capture(CaptureRequest capturerequest, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException;

    public abstract int captureBurst(List list, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException;

    public abstract void close();

    public abstract void finalizeOutputConfigurations(List list)
        throws CameraAccessException;

    public abstract CameraDevice getDevice();

    public abstract Surface getInputSurface();

    public abstract boolean isReprocessable();

    public abstract void prepare(int i, Surface surface)
        throws CameraAccessException;

    public abstract void prepare(Surface surface)
        throws CameraAccessException;

    public abstract int setRepeatingBurst(List list, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException;

    public abstract int setRepeatingRequest(CaptureRequest capturerequest, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException;

    public abstract void stopRepeating()
        throws CameraAccessException;

    public abstract void tearDown(Surface surface)
        throws CameraAccessException;

    public static final int SESSION_ID_NONE = -1;
}
