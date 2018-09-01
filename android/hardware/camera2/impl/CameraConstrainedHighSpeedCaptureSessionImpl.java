// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;

import android.hardware.camera2.*;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.Handler;
import android.util.Range;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.hardware.camera2.impl:
//            CameraCaptureSessionCore, CameraCaptureSessionImpl, CameraMetadataNative, CameraDeviceImpl

public class CameraConstrainedHighSpeedCaptureSessionImpl extends CameraConstrainedHighSpeedCaptureSession
    implements CameraCaptureSessionCore
{
    private class WrapperCallback extends android.hardware.camera2.CameraCaptureSession.StateCallback
    {

        public void onActive(CameraCaptureSession cameracapturesession)
        {
            mCallback.onActive(CameraConstrainedHighSpeedCaptureSessionImpl.this);
        }

        public void onCaptureQueueEmpty(CameraCaptureSession cameracapturesession)
        {
            mCallback.onCaptureQueueEmpty(CameraConstrainedHighSpeedCaptureSessionImpl.this);
        }

        public void onClosed(CameraCaptureSession cameracapturesession)
        {
            mCallback.onClosed(CameraConstrainedHighSpeedCaptureSessionImpl.this);
        }

        public void onConfigureFailed(CameraCaptureSession cameracapturesession)
        {
            mCallback.onConfigureFailed(CameraConstrainedHighSpeedCaptureSessionImpl.this);
        }

        public void onConfigured(CameraCaptureSession cameracapturesession)
        {
            mCallback.onConfigured(CameraConstrainedHighSpeedCaptureSessionImpl.this);
        }

        public void onReady(CameraCaptureSession cameracapturesession)
        {
            mCallback.onReady(CameraConstrainedHighSpeedCaptureSessionImpl.this);
        }

        public void onSurfacePrepared(CameraCaptureSession cameracapturesession, Surface surface)
        {
            mCallback.onSurfacePrepared(CameraConstrainedHighSpeedCaptureSessionImpl.this, surface);
        }

        private final android.hardware.camera2.CameraCaptureSession.StateCallback mCallback;
        final CameraConstrainedHighSpeedCaptureSessionImpl this$0;

        public WrapperCallback(android.hardware.camera2.CameraCaptureSession.StateCallback statecallback)
        {
            this$0 = CameraConstrainedHighSpeedCaptureSessionImpl.this;
            super();
            mCallback = statecallback;
        }
    }


    CameraConstrainedHighSpeedCaptureSessionImpl(int i, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler, CameraDeviceImpl cameradeviceimpl, Handler handler1, boolean flag, CameraCharacteristics cameracharacteristics)
    {
        mCharacteristics = cameracharacteristics;
        mSessionImpl = new CameraCaptureSessionImpl(i, null, new WrapperCallback(statecallback), handler, cameradeviceimpl, handler1, flag);
    }

    private boolean isConstrainedHighSpeedRequestList(List list)
    {
        Preconditions.checkCollectionNotEmpty(list, "High speed request list");
        for(list = list.iterator(); list.hasNext();)
            if(!((CaptureRequest)list.next()).isPartOfCRequestList())
                return false;

        return true;
    }

    public void abortCaptures()
        throws CameraAccessException
    {
        mSessionImpl.abortCaptures();
    }

    public int capture(CaptureRequest capturerequest, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
    }

    public int captureBurst(List list, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(!isConstrainedHighSpeedRequestList(list))
            throw new IllegalArgumentException("Only request lists created by createHighSpeedRequestList() can be submitted to a constrained high speed capture session");
        else
            return mSessionImpl.captureBurst(list, capturecallback, handler);
    }

    public void close()
    {
        mSessionImpl.close();
    }

    public List createHighSpeedRequestList(CaptureRequest capturerequest)
        throws CameraAccessException
    {
        if(capturerequest == null)
            throw new IllegalArgumentException("Input capture request must not be null");
        Collection collection = capturerequest.getTargets();
        Object obj = (Range)capturerequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
        SurfaceUtils.checkConstrainedHighSpeedSurfaces(collection, ((Range) (obj)), (StreamConfigurationMap)mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP));
        int i = ((Integer)((Range) (obj)).getUpper()).intValue() / 30;
        ArrayList arraylist = new ArrayList();
        android.hardware.camera2.CaptureRequest.Builder builder = new android.hardware.camera2.CaptureRequest.Builder(new CameraMetadataNative(capturerequest.getNativeCopy()), false, -1);
        builder.setTag(capturerequest.getTag());
        Iterator iterator = collection.iterator();
        obj = (Surface)iterator.next();
        android.hardware.camera2.CaptureRequest.Builder builder1;
        int j;
        if(collection.size() == 1 && SurfaceUtils.isSurfaceForHwVideoEncoder(((Surface) (obj))))
            builder.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(1));
        else
            builder.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(3));
        builder.setPartOfCHSRequestList(true);
        builder1 = null;
        if(collection.size() == 2)
        {
            builder1 = new android.hardware.camera2.CaptureRequest.Builder(new CameraMetadataNative(capturerequest.getNativeCopy()), false, -1);
            builder1.setTag(capturerequest.getTag());
            builder1.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(3));
            builder1.addTarget(((Surface) (obj)));
            Surface surface = (Surface)iterator.next();
            builder1.addTarget(surface);
            builder1.setPartOfCHSRequestList(true);
            capturerequest = ((CaptureRequest) (obj));
            if(!SurfaceUtils.isSurfaceForHwVideoEncoder(((Surface) (obj))))
                capturerequest = surface;
            builder.addTarget(capturerequest);
            capturerequest = builder1;
        } else
        {
            builder.addTarget(((Surface) (obj)));
            capturerequest = builder1;
        }
        j = 0;
        while(j < i) 
        {
            if(j == 0 && capturerequest != null)
                arraylist.add(capturerequest.build());
            else
                arraylist.add(builder.build());
            j++;
        }
        return Collections.unmodifiableList(arraylist);
    }

    public void finalizeOutputConfigurations(List list)
        throws CameraAccessException
    {
        mSessionImpl.finalizeOutputConfigurations(list);
    }

    public CameraDevice getDevice()
    {
        return mSessionImpl.getDevice();
    }

    public CameraDeviceImpl.StateCallbackKK getDeviceStateCallback()
    {
        return mSessionImpl.getDeviceStateCallback();
    }

    public Surface getInputSurface()
    {
        return null;
    }

    public boolean isAborting()
    {
        return mSessionImpl.isAborting();
    }

    public boolean isReprocessable()
    {
        return false;
    }

    public void prepare(int i, Surface surface)
        throws CameraAccessException
    {
        mSessionImpl.prepare(i, surface);
    }

    public void prepare(Surface surface)
        throws CameraAccessException
    {
        mSessionImpl.prepare(surface);
    }

    public void replaceSessionClose()
    {
        mSessionImpl.replaceSessionClose();
    }

    public int setRepeatingBurst(List list, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(!isConstrainedHighSpeedRequestList(list))
            throw new IllegalArgumentException("Only request lists created by createHighSpeedRequestList() can be submitted to a constrained high speed capture session");
        else
            return mSessionImpl.setRepeatingBurst(list, capturecallback, handler);
    }

    public int setRepeatingRequest(CaptureRequest capturerequest, android.hardware.camera2.CameraCaptureSession.CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
    }

    public void stopRepeating()
        throws CameraAccessException
    {
        mSessionImpl.stopRepeating();
    }

    public void tearDown(Surface surface)
        throws CameraAccessException
    {
        mSessionImpl.tearDown(surface);
    }

    private final CameraCharacteristics mCharacteristics;
    private final CameraCaptureSessionImpl mSessionImpl;
}
