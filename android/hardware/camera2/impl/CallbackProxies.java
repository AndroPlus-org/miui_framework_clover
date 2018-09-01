// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;

import android.hardware.camera2.*;
import android.hardware.camera2.dispatch.Dispatchable;
import android.hardware.camera2.dispatch.MethodNameInvoker;
import android.view.Surface;
import com.android.internal.util.Preconditions;

public class CallbackProxies
{
    public static class DeviceCaptureCallbackProxy
        implements CameraDeviceImpl.CaptureCallback
    {

        public void onCaptureBufferLost(CameraDevice cameradevice, CaptureRequest capturerequest, Surface surface, long l)
        {
            mProxy.invoke("onCaptureBufferLost", new Object[] {
                cameradevice, capturerequest, surface, Long.valueOf(l)
            });
        }

        public void onCaptureCompleted(CameraDevice cameradevice, CaptureRequest capturerequest, TotalCaptureResult totalcaptureresult)
        {
            mProxy.invoke("onCaptureCompleted", new Object[] {
                cameradevice, capturerequest, totalcaptureresult
            });
        }

        public void onCaptureFailed(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureFailure capturefailure)
        {
            mProxy.invoke("onCaptureFailed", new Object[] {
                cameradevice, capturerequest, capturefailure
            });
        }

        public void onCapturePartial(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureResult captureresult)
        {
            mProxy.invoke("onCapturePartial", new Object[] {
                cameradevice, capturerequest, captureresult
            });
        }

        public void onCaptureProgressed(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureResult captureresult)
        {
            mProxy.invoke("onCaptureProgressed", new Object[] {
                cameradevice, capturerequest, captureresult
            });
        }

        public void onCaptureSequenceAborted(CameraDevice cameradevice, int i)
        {
            mProxy.invoke("onCaptureSequenceAborted", new Object[] {
                cameradevice, Integer.valueOf(i)
            });
        }

        public void onCaptureSequenceCompleted(CameraDevice cameradevice, int i, long l)
        {
            mProxy.invoke("onCaptureSequenceCompleted", new Object[] {
                cameradevice, Integer.valueOf(i), Long.valueOf(l)
            });
        }

        public void onCaptureStarted(CameraDevice cameradevice, CaptureRequest capturerequest, long l, long l1)
        {
            mProxy.invoke("onCaptureStarted", new Object[] {
                cameradevice, capturerequest, Long.valueOf(l), Long.valueOf(l1)
            });
        }

        private final MethodNameInvoker mProxy;

        public DeviceCaptureCallbackProxy(Dispatchable dispatchable)
        {
            mProxy = new MethodNameInvoker((Dispatchable)Preconditions.checkNotNull(dispatchable, "dispatchTarget must not be null"), android/hardware/camera2/impl/CameraDeviceImpl$CaptureCallback);
        }
    }

    public static class DeviceStateCallbackProxy extends CameraDeviceImpl.StateCallbackKK
    {

        public void onActive(CameraDevice cameradevice)
        {
            mProxy.invoke("onActive", new Object[] {
                cameradevice
            });
        }

        public void onBusy(CameraDevice cameradevice)
        {
            mProxy.invoke("onBusy", new Object[] {
                cameradevice
            });
        }

        public void onClosed(CameraDevice cameradevice)
        {
            mProxy.invoke("onClosed", new Object[] {
                cameradevice
            });
        }

        public void onDisconnected(CameraDevice cameradevice)
        {
            mProxy.invoke("onDisconnected", new Object[] {
                cameradevice
            });
        }

        public void onError(CameraDevice cameradevice, int i)
        {
            mProxy.invoke("onError", new Object[] {
                cameradevice, Integer.valueOf(i)
            });
        }

        public void onIdle(CameraDevice cameradevice)
        {
            mProxy.invoke("onIdle", new Object[] {
                cameradevice
            });
        }

        public void onOpened(CameraDevice cameradevice)
        {
            mProxy.invoke("onOpened", new Object[] {
                cameradevice
            });
        }

        public void onUnconfigured(CameraDevice cameradevice)
        {
            mProxy.invoke("onUnconfigured", new Object[] {
                cameradevice
            });
        }

        private final MethodNameInvoker mProxy;

        public DeviceStateCallbackProxy(Dispatchable dispatchable)
        {
            mProxy = new MethodNameInvoker((Dispatchable)Preconditions.checkNotNull(dispatchable, "dispatchTarget must not be null"), android/hardware/camera2/impl/CameraDeviceImpl$StateCallbackKK);
        }
    }

    public static class SessionStateCallbackProxy extends android.hardware.camera2.CameraCaptureSession.StateCallback
    {

        public void onActive(CameraCaptureSession cameracapturesession)
        {
            mProxy.invoke("onActive", new Object[] {
                cameracapturesession
            });
        }

        public void onCaptureQueueEmpty(CameraCaptureSession cameracapturesession)
        {
            mProxy.invoke("onCaptureQueueEmpty", new Object[] {
                cameracapturesession
            });
        }

        public void onClosed(CameraCaptureSession cameracapturesession)
        {
            mProxy.invoke("onClosed", new Object[] {
                cameracapturesession
            });
        }

        public void onConfigureFailed(CameraCaptureSession cameracapturesession)
        {
            mProxy.invoke("onConfigureFailed", new Object[] {
                cameracapturesession
            });
        }

        public void onConfigured(CameraCaptureSession cameracapturesession)
        {
            mProxy.invoke("onConfigured", new Object[] {
                cameracapturesession
            });
        }

        public void onReady(CameraCaptureSession cameracapturesession)
        {
            mProxy.invoke("onReady", new Object[] {
                cameracapturesession
            });
        }

        public void onSurfacePrepared(CameraCaptureSession cameracapturesession, Surface surface)
        {
            mProxy.invoke("onSurfacePrepared", new Object[] {
                cameracapturesession, surface
            });
        }

        private final MethodNameInvoker mProxy;

        public SessionStateCallbackProxy(Dispatchable dispatchable)
        {
            mProxy = new MethodNameInvoker((Dispatchable)Preconditions.checkNotNull(dispatchable, "dispatchTarget must not be null"), android/hardware/camera2/CameraCaptureSession$StateCallback);
        }
    }


    private CallbackProxies()
    {
        throw new AssertionError();
    }
}
