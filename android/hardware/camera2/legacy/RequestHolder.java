// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package android.hardware.camera2.legacy:
//            LegacyCameraDevice

public class RequestHolder
{
    public static final class Builder
    {

        private boolean jpegType(Surface surface)
            throws LegacyExceptionUtils.BufferQueueAbandonedException
        {
            return LegacyCameraDevice.containsSurfaceId(surface, mJpegSurfaceIds);
        }

        private int numJpegTargets(CaptureRequest capturerequest)
        {
            int i;
            i = 0;
            capturerequest = capturerequest.getTargets().iterator();
_L2:
            Surface surface;
            if(!capturerequest.hasNext())
                break; /* Loop/switch isn't completed */
            surface = (Surface)capturerequest.next();
            boolean flag = jpegType(surface);
            if(flag)
                i++;
            continue; /* Loop/switch isn't completed */
            LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception;
            bufferqueueabandonedexception;
            Log.d("RequestHolder", "Surface abandoned, skipping...", bufferqueueabandonedexception);
            if(true) goto _L2; else goto _L1
_L1:
            return i;
        }

        private int numPreviewTargets(CaptureRequest capturerequest)
        {
            int i;
            i = 0;
            capturerequest = capturerequest.getTargets().iterator();
_L2:
            Surface surface;
            if(!capturerequest.hasNext())
                break; /* Loop/switch isn't completed */
            surface = (Surface)capturerequest.next();
            boolean flag = previewType(surface);
            if(flag)
                i++;
            continue; /* Loop/switch isn't completed */
            LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception;
            bufferqueueabandonedexception;
            Log.d("RequestHolder", "Surface abandoned, skipping...", bufferqueueabandonedexception);
            if(true) goto _L2; else goto _L1
_L1:
            return i;
        }

        private boolean previewType(Surface surface)
            throws LegacyExceptionUtils.BufferQueueAbandonedException
        {
            return jpegType(surface) ^ true;
        }

        public RequestHolder build(long l)
        {
            return new RequestHolder(mRequestId, mSubsequenceId, mRequest, mRepeating, l, mNumJpegTargets, mNumPreviewTargets, mJpegSurfaceIds, null);
        }

        private final Collection mJpegSurfaceIds;
        private final int mNumJpegTargets;
        private final int mNumPreviewTargets;
        private final boolean mRepeating;
        private final CaptureRequest mRequest;
        private final int mRequestId;
        private final int mSubsequenceId;

        public Builder(int i, int j, CaptureRequest capturerequest, boolean flag, Collection collection)
        {
            Preconditions.checkNotNull(capturerequest, "request must not be null");
            mRequestId = i;
            mSubsequenceId = j;
            mRequest = capturerequest;
            mRepeating = flag;
            mJpegSurfaceIds = collection;
            mNumJpegTargets = numJpegTargets(mRequest);
            mNumPreviewTargets = numPreviewTargets(mRequest);
        }
    }


    private RequestHolder(int i, int j, CaptureRequest capturerequest, boolean flag, long l, int k, 
            int i1, Collection collection)
    {
        mFailed = false;
        mOutputAbandoned = false;
        mRepeating = flag;
        mRequest = capturerequest;
        mRequestId = i;
        mSubsequeceId = j;
        mFrameNumber = l;
        mNumJpegTargets = k;
        mNumPreviewTargets = i1;
        mJpegSurfaceIds = collection;
    }

    RequestHolder(int i, int j, CaptureRequest capturerequest, boolean flag, long l, int k, 
            int i1, Collection collection, RequestHolder requestholder)
    {
        this(i, j, capturerequest, flag, l, k, i1, collection);
    }

    public void failRequest()
    {
        Log.w("RequestHolder", (new StringBuilder()).append("Capture failed for request: ").append(getRequestId()).toString());
        mFailed = true;
    }

    public long getFrameNumber()
    {
        return mFrameNumber;
    }

    public Collection getHolderTargets()
    {
        return getRequest().getTargets();
    }

    public CaptureRequest getRequest()
    {
        return mRequest;
    }

    public int getRequestId()
    {
        return mRequestId;
    }

    public int getSubsequeceId()
    {
        return mSubsequeceId;
    }

    public boolean hasJpegTargets()
    {
        boolean flag = false;
        if(mNumJpegTargets > 0)
            flag = true;
        return flag;
    }

    public boolean hasPreviewTargets()
    {
        boolean flag = false;
        if(mNumPreviewTargets > 0)
            flag = true;
        return flag;
    }

    public boolean isOutputAbandoned()
    {
        return mOutputAbandoned;
    }

    public boolean isRepeating()
    {
        return mRepeating;
    }

    public boolean jpegType(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        return LegacyCameraDevice.containsSurfaceId(surface, mJpegSurfaceIds);
    }

    public int numJpegTargets()
    {
        return mNumJpegTargets;
    }

    public int numPreviewTargets()
    {
        return mNumPreviewTargets;
    }

    public boolean requestFailed()
    {
        return mFailed;
    }

    public void setOutputAbandoned()
    {
        mOutputAbandoned = true;
    }

    private static final String TAG = "RequestHolder";
    private volatile boolean mFailed;
    private final long mFrameNumber;
    private final Collection mJpegSurfaceIds;
    private final int mNumJpegTargets;
    private final int mNumPreviewTargets;
    private boolean mOutputAbandoned;
    private final boolean mRepeating;
    private final CaptureRequest mRequest;
    private final int mRequestId;
    private final int mSubsequeceId;
}
