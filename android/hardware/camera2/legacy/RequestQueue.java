// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.SubmitInfo;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.hardware.camera2.legacy:
//            BurstHolder

public class RequestQueue
{
    public final class RequestQueueEntry
    {

        public BurstHolder getBurstHolder()
        {
            return mBurstHolder;
        }

        public Long getFrameNumber()
        {
            return mFrameNumber;
        }

        public boolean isQueueEmpty()
        {
            return mQueueEmpty;
        }

        private final BurstHolder mBurstHolder;
        private final Long mFrameNumber;
        private final boolean mQueueEmpty;
        final RequestQueue this$0;

        public RequestQueueEntry(BurstHolder burstholder, Long long1, boolean flag)
        {
            this$0 = RequestQueue.this;
            super();
            mBurstHolder = burstholder;
            mFrameNumber = long1;
            mQueueEmpty = flag;
        }
    }


    public RequestQueue(List list)
    {
        mRepeatingRequest = null;
        mCurrentFrameNumber = 0L;
        mCurrentRepeatingFrameNumber = -1L;
        mCurrentRequestId = 0;
        mJpegSurfaceIds = list;
    }

    private long calculateLastFrame(int i)
    {
        long l = mCurrentFrameNumber;
        for(Iterator iterator = mRequestQueue.iterator(); iterator.hasNext();)
        {
            BurstHolder burstholder = (BurstHolder)iterator.next();
            long l1 = l + (long)burstholder.getNumberOfRequests();
            l = l1;
            if(burstholder.getRequestId() == i)
                return l1 - 1L;
        }

        throw new IllegalStateException("At least one request must be in the queue to calculate frame number");
    }

    public RequestQueueEntry getNext()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = (BurstHolder)mRequestQueue.poll();
        if(obj == null) goto _L2; else goto _L1
_L1:
        if(mRequestQueue.size() != 0) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L5:
        BurstHolder burstholder;
        burstholder = ((BurstHolder) (obj));
        if(obj != null)
            break MISSING_BLOCK_LABEL_63;
        burstholder = ((BurstHolder) (obj));
        if(mRepeatingRequest != null)
        {
            burstholder = mRepeatingRequest;
            mCurrentRepeatingFrameNumber = mCurrentFrameNumber + (long)burstholder.getNumberOfRequests();
        }
        if(burstholder != null)
            break; /* Loop/switch isn't completed */
        this;
        JVM INSTR monitorexit ;
        return null;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
        obj = JVM INSTR new #6   <Class RequestQueue$RequestQueueEntry>;
        ((RequestQueueEntry) (obj)).this. RequestQueueEntry(burstholder, Long.valueOf(mCurrentFrameNumber), flag);
        mCurrentFrameNumber = mCurrentFrameNumber + (long)burstholder.getNumberOfRequests();
        this;
        JVM INSTR monitorexit ;
        return ((RequestQueueEntry) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public long stopRepeating()
    {
        this;
        JVM INSTR monitorenter ;
        if(mRepeatingRequest != null)
            break MISSING_BLOCK_LABEL_23;
        Log.e("RequestQueue", "cancel failed: no repeating request exists.");
        this;
        JVM INSTR monitorexit ;
        return -1L;
        long l = stopRepeating(mRepeatingRequest.getRequestId());
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    public long stopRepeating(int i)
    {
        this;
        JVM INSTR monitorenter ;
        long l = -1L;
        if(mRepeatingRequest == null || mRepeatingRequest.getRequestId() != i) goto _L2; else goto _L1
_L1:
        mRepeatingRequest = null;
        if(mCurrentRepeatingFrameNumber != -1L) goto _L4; else goto _L3
_L3:
        l = -1L;
_L7:
        mCurrentRepeatingFrameNumber = -1L;
        Log.i("RequestQueue", "Repeating capture request cancelled.");
_L5:
        this;
        JVM INSTR monitorexit ;
        return l;
_L4:
        l = mCurrentRepeatingFrameNumber - 1L;
        continue; /* Loop/switch isn't completed */
_L2:
        StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("RequestQueue", stringbuilder.append("cancel failed: no repeating request exists for request id: ").append(i).toString());
          goto _L5
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public SubmitInfo submit(CaptureRequest acapturerequest[], boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        BurstHolder burstholder;
        i = mCurrentRequestId;
        mCurrentRequestId = i + 1;
        burstholder = JVM INSTR new #70  <Class BurstHolder>;
        burstholder.BurstHolder(i, flag, acapturerequest, mJpegSurfaceIds);
        long l = -1L;
        if(!burstholder.isRepeating()) goto _L2; else goto _L1
_L1:
        Log.i("RequestQueue", "Repeating capture request set.");
        if(mRepeatingRequest == null) goto _L4; else goto _L3
_L3:
        if(mCurrentRepeatingFrameNumber != -1L) goto _L6; else goto _L5
_L5:
        l = -1L;
_L4:
        mCurrentRepeatingFrameNumber = -1L;
        mRepeatingRequest = burstholder;
_L7:
        acapturerequest = new SubmitInfo(i, l);
        this;
        JVM INSTR monitorexit ;
        return acapturerequest;
_L6:
        l = mCurrentRepeatingFrameNumber - 1L;
        continue; /* Loop/switch isn't completed */
_L2:
        mRequestQueue.offer(burstholder);
        l = calculateLastFrame(burstholder.getRequestId());
          goto _L7
        acapturerequest;
        throw acapturerequest;
        if(true) goto _L4; else goto _L8
_L8:
    }

    private static final long INVALID_FRAME = -1L;
    private static final String TAG = "RequestQueue";
    private long mCurrentFrameNumber;
    private long mCurrentRepeatingFrameNumber;
    private int mCurrentRequestId;
    private final List mJpegSurfaceIds;
    private BurstHolder mRepeatingRequest;
    private final ArrayDeque mRequestQueue = new ArrayDeque();
}
