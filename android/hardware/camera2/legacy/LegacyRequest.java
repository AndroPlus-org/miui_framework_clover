// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.util.Size;
import com.android.internal.util.Preconditions;

public class LegacyRequest
{

    public LegacyRequest(CameraCharacteristics cameracharacteristics, CaptureRequest capturerequest, Size size, android.hardware.Camera.Parameters parameters1)
    {
        characteristics = (CameraCharacteristics)Preconditions.checkNotNull(cameracharacteristics, "characteristics must not be null");
        captureRequest = (CaptureRequest)Preconditions.checkNotNull(capturerequest, "captureRequest must not be null");
        previewSize = (Size)Preconditions.checkNotNull(size, "previewSize must not be null");
        Preconditions.checkNotNull(parameters1, "parameters must not be null");
        parameters = Camera.getParametersCopy(parameters1);
    }

    public void setParameters(android.hardware.Camera.Parameters parameters1)
    {
        Preconditions.checkNotNull(parameters1, "parameters must not be null");
        parameters.copyFrom(parameters1);
    }

    public final CaptureRequest captureRequest;
    public final CameraCharacteristics characteristics;
    public final android.hardware.Camera.Parameters parameters;
    public final Size previewSize;
}
