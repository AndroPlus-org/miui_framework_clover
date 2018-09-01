// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.hardware.camera2.params.InputConfiguration;
import android.os.Handler;
import java.util.List;

// Referenced classes of package android.hardware.camera2:
//            CameraAccessException, TotalCaptureResult

public abstract class CameraDevice
    implements AutoCloseable
{
    public static abstract class StateCallback
    {

        public void onClosed(CameraDevice cameradevice)
        {
        }

        public abstract void onDisconnected(CameraDevice cameradevice);

        public abstract void onError(CameraDevice cameradevice, int i);

        public abstract void onOpened(CameraDevice cameradevice);

        public static final int ERROR_CAMERA_DEVICE = 4;
        public static final int ERROR_CAMERA_DISABLED = 3;
        public static final int ERROR_CAMERA_IN_USE = 1;
        public static final int ERROR_CAMERA_SERVICE = 5;
        public static final int ERROR_MAX_CAMERAS_IN_USE = 2;

        public StateCallback()
        {
        }
    }


    public CameraDevice()
    {
    }

    public abstract void close();

    public abstract CaptureRequest.Builder createCaptureRequest(int i)
        throws CameraAccessException;

    public abstract void createCaptureSession(List list, CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException;

    public abstract void createCaptureSessionByOutputConfigurations(List list, CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException;

    public abstract void createConstrainedHighSpeedCaptureSession(List list, CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException;

    public abstract void createCustomCaptureSession(InputConfiguration inputconfiguration, List list, int i, CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException;

    public abstract CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult totalcaptureresult)
        throws CameraAccessException;

    public abstract void createReprocessableCaptureSession(InputConfiguration inputconfiguration, List list, CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException;

    public abstract void createReprocessableCaptureSessionByConfigurations(InputConfiguration inputconfiguration, List list, CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException;

    public abstract String getId();

    public abstract void setVendorStreamConfigMode(int i)
        throws CameraAccessException;

    public static final int SESSION_OPERATION_MODE_CONSTRAINED_HIGH_SPEED = 1;
    public static final int SESSION_OPERATION_MODE_NORMAL = 0;
    public static final int SESSION_OPERATION_MODE_VENDOR_START = 32768;
    public static final int TEMPLATE_MANUAL = 6;
    public static final int TEMPLATE_PREVIEW = 1;
    public static final int TEMPLATE_RECORD = 3;
    public static final int TEMPLATE_STILL_CAPTURE = 2;
    public static final int TEMPLATE_VIDEO_SNAPSHOT = 4;
    public static final int TEMPLATE_ZERO_SHUTTER_LAG = 5;
}
