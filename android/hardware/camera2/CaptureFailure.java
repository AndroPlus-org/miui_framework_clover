// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;


// Referenced classes of package android.hardware.camera2:
//            CaptureRequest

public class CaptureFailure
{

    public CaptureFailure(CaptureRequest capturerequest, int i, boolean flag, int j, long l)
    {
        mRequest = capturerequest;
        mReason = i;
        mDropped = flag;
        mSequenceId = j;
        mFrameNumber = l;
    }

    public long getFrameNumber()
    {
        return mFrameNumber;
    }

    public int getReason()
    {
        return mReason;
    }

    public CaptureRequest getRequest()
    {
        return mRequest;
    }

    public int getSequenceId()
    {
        return mSequenceId;
    }

    public boolean wasImageCaptured()
    {
        return mDropped ^ true;
    }

    public static final int REASON_ERROR = 0;
    public static final int REASON_FLUSHED = 1;
    private final boolean mDropped;
    private final long mFrameNumber;
    private final int mReason;
    private final CaptureRequest mRequest;
    private final int mSequenceId;
}
