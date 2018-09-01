// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;

import android.hardware.camera2.*;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Surface;

// Referenced classes of package android.hardware.camera2.impl:
//            CameraMetadataNative

public class ICameraDeviceUserWrapper
{

    public ICameraDeviceUserWrapper(ICameraDeviceUser icameradeviceuser)
    {
        if(icameradeviceuser == null)
        {
            throw new NullPointerException("Remote device may not be null");
        } else
        {
            mRemoteDevice = icameradeviceuser;
            return;
        }
    }

    public void beginConfigure()
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.beginConfigure();
            return;
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
    }

    public long cancelRequest(int i)
        throws CameraAccessException
    {
        long l;
        try
        {
            l = mRemoteDevice.cancelRequest(i);
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
        return l;
    }

    public CameraMetadataNative createDefaultRequest(int i)
        throws CameraAccessException
    {
        CameraMetadataNative camerametadatanative;
        try
        {
            camerametadatanative = mRemoteDevice.createDefaultRequest(i);
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
        return camerametadatanative;
    }

    public int createInputStream(int i, int j, int k)
        throws CameraAccessException
    {
        try
        {
            i = mRemoteDevice.createInputStream(i, j, k);
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
        return i;
    }

    public int createStream(OutputConfiguration outputconfiguration)
        throws CameraAccessException
    {
        int i;
        try
        {
            i = mRemoteDevice.createStream(outputconfiguration);
        }
        // Misplaced declaration of an exception variable
        catch(OutputConfiguration outputconfiguration)
        {
            CameraManager.throwAsPublicException(outputconfiguration);
            throw new UnsupportedOperationException("Unexpected exception", outputconfiguration);
        }
        return i;
    }

    public void deleteStream(int i)
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.deleteStream(i);
            return;
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
    }

    public void disconnect()
    {
        mRemoteDevice.disconnect();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void endConfigure(int i)
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.endConfigure(i);
            return;
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
    }

    public void finalizeOutputConfigurations(int i, OutputConfiguration outputconfiguration)
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.finalizeOutputConfigurations(i, outputconfiguration);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(OutputConfiguration outputconfiguration)
        {
            CameraManager.throwAsPublicException(outputconfiguration);
        }
        throw new UnsupportedOperationException("Unexpected exception", outputconfiguration);
    }

    public long flush()
        throws CameraAccessException
    {
        long l;
        try
        {
            l = mRemoteDevice.flush();
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
        return l;
    }

    public CameraMetadataNative getCameraInfo()
        throws CameraAccessException
    {
        CameraMetadataNative camerametadatanative;
        try
        {
            camerametadatanative = mRemoteDevice.getCameraInfo();
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
        return camerametadatanative;
    }

    public Surface getInputSurface()
        throws CameraAccessException
    {
        Surface surface;
        try
        {
            surface = mRemoteDevice.getInputSurface();
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
        return surface;
    }

    public void prepare(int i)
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.prepare(i);
            return;
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
    }

    public void prepare2(int i, int j)
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.prepare2(i, j);
            return;
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
    }

    public SubmitInfo submitRequest(CaptureRequest capturerequest, boolean flag)
        throws CameraAccessException
    {
        try
        {
            capturerequest = mRemoteDevice.submitRequest(capturerequest, flag);
        }
        // Misplaced declaration of an exception variable
        catch(CaptureRequest capturerequest)
        {
            CameraManager.throwAsPublicException(capturerequest);
            throw new UnsupportedOperationException("Unexpected exception", capturerequest);
        }
        return capturerequest;
    }

    public SubmitInfo submitRequestList(CaptureRequest acapturerequest[], boolean flag)
        throws CameraAccessException
    {
        try
        {
            acapturerequest = mRemoteDevice.submitRequestList(acapturerequest, flag);
        }
        // Misplaced declaration of an exception variable
        catch(CaptureRequest acapturerequest[])
        {
            CameraManager.throwAsPublicException(acapturerequest);
            throw new UnsupportedOperationException("Unexpected exception", acapturerequest);
        }
        return acapturerequest;
    }

    public void tearDown(int i)
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.tearDown(i);
            return;
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
    }

    public void unlinkToDeath(android.os.IBinder.DeathRecipient deathrecipient, int i)
    {
        if(mRemoteDevice.asBinder() != null)
            mRemoteDevice.asBinder().unlinkToDeath(deathrecipient, i);
    }

    public void waitUntilIdle()
        throws CameraAccessException
    {
        try
        {
            mRemoteDevice.waitUntilIdle();
            return;
        }
        catch(Throwable throwable)
        {
            CameraManager.throwAsPublicException(throwable);
            throw new UnsupportedOperationException("Unexpected exception", throwable);
        }
    }

    private final ICameraDeviceUser mRemoteDevice;
}
