// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import java.util.*;

// Referenced classes of package android.hardware.camera2:
//            CaptureResult, CaptureRequest

public final class TotalCaptureResult extends CaptureResult
{

    public TotalCaptureResult(CameraMetadataNative camerametadatanative, int i)
    {
        super(camerametadatanative, i);
        mPartialResults = new ArrayList();
        mSessionId = -1;
    }

    public TotalCaptureResult(CameraMetadataNative camerametadatanative, CaptureRequest capturerequest, CaptureResultExtras captureresultextras, List list, int i)
    {
        super(camerametadatanative, capturerequest, captureresultextras);
        if(list == null)
            mPartialResults = new ArrayList();
        else
            mPartialResults = list;
        mSessionId = i;
    }

    public List getPartialResults()
    {
        return Collections.unmodifiableList(mPartialResults);
    }

    public int getSessionId()
    {
        return mSessionId;
    }

    private final List mPartialResults;
    private final int mSessionId;
}
