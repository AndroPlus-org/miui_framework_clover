// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import java.util.List;

// Referenced classes of package android.hardware.camera2:
//            CameraCaptureSession, CameraAccessException, CaptureRequest

public abstract class CameraConstrainedHighSpeedCaptureSession extends CameraCaptureSession
{

    public CameraConstrainedHighSpeedCaptureSession()
    {
    }

    public abstract List createHighSpeedRequestList(CaptureRequest capturerequest)
        throws CameraAccessException;
}
