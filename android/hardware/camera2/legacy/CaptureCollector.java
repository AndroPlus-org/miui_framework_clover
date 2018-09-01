// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import android.util.*;
import android.view.Surface;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package android.hardware.camera2.legacy:
//            RequestHolder, CameraDeviceState, LegacyRequest

public class CaptureCollector
{
    private class CaptureHolder
        implements Comparable
    {

        static RequestHolder _2D_get0(CaptureHolder captureholder)
        {
            return captureholder.mRequest;
        }

        static long _2D_get1(CaptureHolder captureholder)
        {
            return captureholder.mTimestamp;
        }

        public int compareTo(CaptureHolder captureholder)
        {
            int i;
            if(mRequest.getFrameNumber() > captureholder.mRequest.getFrameNumber())
                i = 1;
            else
            if(mRequest.getFrameNumber() == captureholder.mRequest.getFrameNumber())
                i = 0;
            else
                i = -1;
            return i;
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((CaptureHolder)obj);
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(obj instanceof CaptureHolder)
            {
                flag1 = flag;
                if(compareTo((CaptureHolder)obj) == 0)
                    flag1 = true;
            }
            return flag1;
        }

        public boolean isCompleted()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(needsJpeg == isJpegCompleted())
            {
                flag1 = flag;
                if(needsPreview == isPreviewCompleted())
                    flag1 = true;
            }
            return flag1;
        }

        public boolean isJpegCompleted()
        {
            boolean flag;
            if((mReceivedFlags & 3) == 3)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isPreviewCompleted()
        {
            boolean flag;
            if((mReceivedFlags & 0xc) == 12)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void setJpegFailed()
        {
            if(!needsJpeg || isJpegCompleted())
            {
                return;
            } else
            {
                mFailedJpeg = true;
                mReceivedFlags = mReceivedFlags | 1;
                mReceivedFlags = mReceivedFlags | 2;
                tryComplete();
                return;
            }
        }

        public void setJpegProduced()
        {
            if(!needsJpeg)
                throw new IllegalStateException("setJpegProduced called for capture with no jpeg targets.");
            if(isCompleted())
            {
                throw new IllegalStateException("setJpegProduced called on already completed request.");
            } else
            {
                mReceivedFlags = mReceivedFlags | 1;
                tryComplete();
                return;
            }
        }

        public void setJpegTimestamp(long l)
        {
            if(!needsJpeg)
                throw new IllegalStateException("setJpegTimestamp called for capture with no jpeg targets.");
            if(isCompleted())
                throw new IllegalStateException("setJpegTimestamp called on already completed request.");
            mReceivedFlags = mReceivedFlags | 2;
            if(mTimestamp == 0L)
                mTimestamp = l;
            if(!mHasStarted)
            {
                mHasStarted = true;
                CaptureCollector._2D_get0(CaptureCollector.this).setCaptureStart(mRequest, mTimestamp, -1);
            }
            tryComplete();
        }

        public void setPreviewFailed()
        {
            if(!needsPreview || isPreviewCompleted())
            {
                return;
            } else
            {
                mFailedPreview = true;
                mReceivedFlags = mReceivedFlags | 4;
                mReceivedFlags = mReceivedFlags | 8;
                tryComplete();
                return;
            }
        }

        public void setPreviewProduced()
        {
            if(!needsPreview)
                throw new IllegalStateException("setPreviewProduced called for capture with no preview targets.");
            if(isCompleted())
            {
                throw new IllegalStateException("setPreviewProduced called on already completed request.");
            } else
            {
                mReceivedFlags = mReceivedFlags | 4;
                tryComplete();
                return;
            }
        }

        public void setPreviewTimestamp(long l)
        {
            if(!needsPreview)
                throw new IllegalStateException("setPreviewTimestamp called for capture with no preview targets.");
            if(isCompleted())
                throw new IllegalStateException("setPreviewTimestamp called on already completed request.");
            mReceivedFlags = mReceivedFlags | 8;
            if(mTimestamp == 0L)
                mTimestamp = l;
            if(!needsJpeg && !mHasStarted)
            {
                mHasStarted = true;
                CaptureCollector._2D_get0(CaptureCollector.this).setCaptureStart(mRequest, mTimestamp, -1);
            }
            tryComplete();
        }

        public void tryComplete()
        {
            if(!mPreviewCompleted && needsPreview && isPreviewCompleted())
            {
                CaptureCollector._2D_wrap0(CaptureCollector.this);
                mPreviewCompleted = true;
            }
            if(!isCompleted() || !(mCompleted ^ true)) goto _L2; else goto _L1
_L1:
            if(!mFailedPreview && !mFailedJpeg) goto _L4; else goto _L3
_L3:
            if(mHasStarted) goto _L6; else goto _L5
_L5:
            mRequest.failRequest();
            CaptureCollector._2D_get0(CaptureCollector.this).setCaptureStart(mRequest, mTimestamp, 3);
_L4:
            CaptureCollector._2D_wrap1(CaptureCollector.this, this);
            mCompleted = true;
_L2:
            return;
_L6:
            Iterator iterator = mRequest.getRequest().getTargets().iterator();
_L9:
            Object obj;
            if(!iterator.hasNext())
                continue; /* Loop/switch isn't completed */
            obj = (Surface)iterator.next();
            if(!mRequest.jpegType(((Surface) (obj)))) goto _L8; else goto _L7
_L7:
            if(mFailedJpeg)
                CaptureCollector._2D_get0(CaptureCollector.this).setCaptureResult(mRequest, null, 5, obj);
              goto _L9
            obj;
            Log.e("CaptureCollector", (new StringBuilder()).append("Unexpected exception when querying Surface: ").append(obj).toString());
              goto _L9
_L8:
            if(!mFailedPreview) goto _L9; else goto _L10
_L10:
            CaptureCollector._2D_get0(CaptureCollector.this).setCaptureResult(mRequest, null, 5, obj);
              goto _L9
            if(true) goto _L4; else goto _L11
_L11:
        }

        private boolean mCompleted;
        private boolean mFailedJpeg;
        private boolean mFailedPreview;
        private boolean mHasStarted;
        private final LegacyRequest mLegacy;
        private boolean mPreviewCompleted;
        private int mReceivedFlags;
        private final RequestHolder mRequest;
        private long mTimestamp;
        public final boolean needsJpeg;
        public final boolean needsPreview;
        final CaptureCollector this$0;

        public CaptureHolder(RequestHolder requestholder, LegacyRequest legacyrequest)
        {
            this$0 = CaptureCollector.this;
            super();
            mTimestamp = 0L;
            mReceivedFlags = 0;
            mHasStarted = false;
            mFailedJpeg = false;
            mFailedPreview = false;
            mCompleted = false;
            mPreviewCompleted = false;
            mRequest = requestholder;
            mLegacy = legacyrequest;
            needsJpeg = requestholder.hasJpegTargets();
            needsPreview = requestholder.hasPreviewTargets();
        }
    }


    static CameraDeviceState _2D_get0(CaptureCollector capturecollector)
    {
        return capturecollector.mDeviceState;
    }

    static void _2D_wrap0(CaptureCollector capturecollector)
    {
        capturecollector.onPreviewCompleted();
    }

    static void _2D_wrap1(CaptureCollector capturecollector, CaptureHolder captureholder)
    {
        capturecollector.onRequestCompleted(captureholder);
    }

    public CaptureCollector(int i, CameraDeviceState cameradevicestate)
    {
        mInFlight = 0;
        mInFlightPreviews = 0;
        mMaxInFlight = i;
        mPreviewCaptureQueue = new ArrayDeque(mMaxInFlight);
        mPreviewProduceQueue = new ArrayDeque(mMaxInFlight);
        mIsEmpty = mLock.newCondition();
        mNotFull = mLock.newCondition();
        mPreviewsEmpty = mLock.newCondition();
        mDeviceState = cameradevicestate;
    }

    private void onPreviewCompleted()
    {
        mInFlightPreviews = mInFlightPreviews - 1;
        if(mInFlightPreviews < 0)
            throw new IllegalStateException("More preview captures completed than requests queued.");
        if(mInFlightPreviews == 0)
            mPreviewsEmpty.signalAll();
    }

    private void onRequestCompleted(CaptureHolder captureholder)
    {
        CaptureHolder._2D_get0(captureholder);
        mInFlight = mInFlight - 1;
        if(mInFlight < 0)
            throw new IllegalStateException("More captures completed than requests queued.");
        mCompletedRequests.add(captureholder);
        mActiveRequests.remove(captureholder);
        mNotFull.signalAll();
        if(mInFlight == 0)
            mIsEmpty.signalAll();
    }

    private boolean removeRequestIfCompleted(RequestHolder requestholder, MutableLong mutablelong)
    {
        int i = 0;
        for(Iterator iterator = mCompletedRequests.iterator(); iterator.hasNext();)
        {
            CaptureHolder captureholder = (CaptureHolder)iterator.next();
            if(CaptureHolder._2D_get0(captureholder).equals(requestholder))
            {
                mutablelong.value = CaptureHolder._2D_get1(captureholder);
                mCompletedRequests.remove(i);
                return true;
            }
            i++;
        }

        return false;
    }

    public void failAll()
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
_L1:
        CaptureHolder captureholder = (CaptureHolder)mActiveRequests.pollFirst();
        if(captureholder == null)
            break MISSING_BLOCK_LABEL_42;
        captureholder.setPreviewFailed();
        captureholder.setJpegFailed();
          goto _L1
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
        mPreviewCaptureQueue.clear();
        mPreviewProduceQueue.clear();
        mJpegCaptureQueue.clear();
        mJpegProduceQueue.clear();
        reentrantlock.unlock();
        return;
    }

    public void failNextJpeg()
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
        CaptureHolder captureholder;
        CaptureHolder captureholder1;
        captureholder = (CaptureHolder)mJpegCaptureQueue.peek();
        captureholder1 = (CaptureHolder)mJpegProduceQueue.peek();
        if(captureholder != null) goto _L2; else goto _L1
_L1:
        if(captureholder1 == null)
            break MISSING_BLOCK_LABEL_70;
        mJpegCaptureQueue.remove(captureholder1);
        mJpegProduceQueue.remove(captureholder1);
        mActiveRequests.remove(captureholder1);
        captureholder1.setJpegFailed();
        reentrantlock.unlock();
        return;
_L2:
        if(captureholder1 == null)
        {
            captureholder1 = captureholder;
            continue; /* Loop/switch isn't completed */
        }
        int i = captureholder.compareTo(captureholder1);
        if(i <= 0)
            captureholder1 = captureholder;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
    }

    public void failNextPreview()
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
        CaptureHolder captureholder;
        CaptureHolder captureholder1;
        captureholder = (CaptureHolder)mPreviewCaptureQueue.peek();
        captureholder1 = (CaptureHolder)mPreviewProduceQueue.peek();
        if(captureholder != null) goto _L2; else goto _L1
_L1:
        if(captureholder1 == null)
            break MISSING_BLOCK_LABEL_70;
        mPreviewCaptureQueue.remove(captureholder1);
        mPreviewProduceQueue.remove(captureholder1);
        mActiveRequests.remove(captureholder1);
        captureholder1.setPreviewFailed();
        reentrantlock.unlock();
        return;
_L2:
        if(captureholder1 == null)
        {
            captureholder1 = captureholder;
            continue; /* Loop/switch isn't completed */
        }
        int i = captureholder.compareTo(captureholder1);
        if(i <= 0)
            captureholder1 = captureholder;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
    }

    public boolean hasPendingPreviewCaptures()
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
        boolean flag = mPreviewCaptureQueue.isEmpty();
        reentrantlock.unlock();
        return flag ^ true;
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
    }

    public RequestHolder jpegCaptured(long l)
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
        Object obj = (CaptureHolder)mJpegCaptureQueue.poll();
        if(obj != null)
            break MISSING_BLOCK_LABEL_40;
        Log.w("CaptureCollector", "jpegCaptured called with no jpeg request on queue!");
        reentrantlock.unlock();
        return null;
        ((CaptureHolder) (obj)).setJpegTimestamp(l);
        obj = CaptureHolder._2D_get0(((CaptureHolder) (obj)));
        reentrantlock.unlock();
        return ((RequestHolder) (obj));
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
    }

    public Pair jpegProduced()
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
        Object obj = (CaptureHolder)mJpegProduceQueue.poll();
        if(obj != null)
            break MISSING_BLOCK_LABEL_38;
        Log.w("CaptureCollector", "jpegProduced called with no jpeg request on queue!");
        reentrantlock.unlock();
        return null;
        ((CaptureHolder) (obj)).setJpegProduced();
        obj = new Pair(CaptureHolder._2D_get0(((CaptureHolder) (obj))), Long.valueOf(CaptureHolder._2D_get1(((CaptureHolder) (obj)))));
        reentrantlock.unlock();
        return ((Pair) (obj));
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
    }

    public Pair previewCaptured(long l)
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
        Object obj = (CaptureHolder)mPreviewCaptureQueue.poll();
        if(obj == null)
        {
            reentrantlock.unlock();
            return null;
        }
        ((CaptureHolder) (obj)).setPreviewTimestamp(l);
        obj = new Pair(CaptureHolder._2D_get0(((CaptureHolder) (obj))), Long.valueOf(CaptureHolder._2D_get1(((CaptureHolder) (obj)))));
        reentrantlock.unlock();
        return ((Pair) (obj));
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
    }

    public RequestHolder previewProduced()
    {
        ReentrantLock reentrantlock;
        reentrantlock = mLock;
        reentrantlock.lock();
        Object obj = (CaptureHolder)mPreviewProduceQueue.poll();
        if(obj != null)
            break MISSING_BLOCK_LABEL_39;
        Log.w("CaptureCollector", "previewProduced called with no preview request on queue!");
        reentrantlock.unlock();
        return null;
        ((CaptureHolder) (obj)).setPreviewProduced();
        obj = CaptureHolder._2D_get0(((CaptureHolder) (obj)));
        reentrantlock.unlock();
        return ((RequestHolder) (obj));
        Exception exception;
        exception;
        reentrantlock.unlock();
        throw exception;
    }

    public boolean queueRequest(RequestHolder requestholder, LegacyRequest legacyrequest, long l, TimeUnit timeunit)
        throws InterruptedException
    {
        long l1;
        legacyrequest = new CaptureHolder(requestholder, legacyrequest);
        l1 = timeunit.toNanos(l);
        requestholder = mLock;
        requestholder.lock();
        if(((CaptureHolder) (legacyrequest)).needsJpeg) goto _L2; else goto _L1
_L1:
        boolean flag = ((CaptureHolder) (legacyrequest)).needsPreview;
_L4:
        if(flag)
            break; /* Loop/switch isn't completed */
        legacyrequest = JVM INSTR new #118 <Class IllegalStateException>;
        legacyrequest.IllegalStateException("Request must target at least one output surface!");
        throw legacyrequest;
        legacyrequest;
        requestholder.unlock();
        throw legacyrequest;
_L2:
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        l = l1;
        if(!((CaptureHolder) (legacyrequest)).needsJpeg)
            break MISSING_BLOCK_LABEL_140;
        l = l1;
_L5:
        int i = mInFlight;
        if(i <= 0)
            break MISSING_BLOCK_LABEL_122;
        if(l <= 0L)
        {
            requestholder.unlock();
            return false;
        }
        l = mIsEmpty.awaitNanos(l);
          goto _L5
        mJpegCaptureQueue.add(legacyrequest);
        mJpegProduceQueue.add(legacyrequest);
        if(!((CaptureHolder) (legacyrequest)).needsPreview)
            break MISSING_BLOCK_LABEL_220;
_L6:
        int j;
        j = mInFlight;
        i = mMaxInFlight;
        if(j < i)
            break MISSING_BLOCK_LABEL_192;
        if(l <= 0L)
        {
            requestholder.unlock();
            return false;
        }
        l = mNotFull.awaitNanos(l);
          goto _L6
        mPreviewCaptureQueue.add(legacyrequest);
        mPreviewProduceQueue.add(legacyrequest);
        mInFlightPreviews = mInFlightPreviews + 1;
        mActiveRequests.add(legacyrequest);
        mInFlight = mInFlight + 1;
        requestholder.unlock();
        return true;
    }

    public boolean waitForEmpty(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        ReentrantLock reentrantlock;
        l = timeunit.toNanos(l);
        reentrantlock = mLock;
        reentrantlock.lock();
_L2:
        int i = mInFlight;
        if(i <= 0)
            break; /* Loop/switch isn't completed */
        if(l <= 0L)
        {
            reentrantlock.unlock();
            return false;
        }
        l = mIsEmpty.awaitNanos(l);
        if(true) goto _L2; else goto _L1
_L1:
        reentrantlock.unlock();
        return true;
        timeunit;
        reentrantlock.unlock();
        throw timeunit;
    }

    public boolean waitForPreviewsEmpty(long l, TimeUnit timeunit)
        throws InterruptedException
    {
        ReentrantLock reentrantlock;
        l = timeunit.toNanos(l);
        reentrantlock = mLock;
        reentrantlock.lock();
_L2:
        int i = mInFlightPreviews;
        if(i <= 0)
            break; /* Loop/switch isn't completed */
        if(l <= 0L)
        {
            reentrantlock.unlock();
            return false;
        }
        l = mPreviewsEmpty.awaitNanos(l);
        if(true) goto _L2; else goto _L1
_L1:
        reentrantlock.unlock();
        return true;
        timeunit;
        reentrantlock.unlock();
        throw timeunit;
    }

    public boolean waitForRequestCompleted(RequestHolder requestholder, long l, TimeUnit timeunit, MutableLong mutablelong)
        throws InterruptedException
    {
        l = timeunit.toNanos(l);
        timeunit = mLock;
        timeunit.lock();
_L2:
        boolean flag = removeRequestIfCompleted(requestholder, mutablelong);
        if(flag)
            break; /* Loop/switch isn't completed */
        if(l <= 0L)
        {
            timeunit.unlock();
            return false;
        }
        l = mNotFull.awaitNanos(l);
        if(true) goto _L2; else goto _L1
_L1:
        timeunit.unlock();
        return true;
        requestholder;
        timeunit.unlock();
        throw requestholder;
    }

    private static final boolean DEBUG = false;
    private static final int FLAG_RECEIVED_ALL_JPEG = 3;
    private static final int FLAG_RECEIVED_ALL_PREVIEW = 12;
    private static final int FLAG_RECEIVED_JPEG = 1;
    private static final int FLAG_RECEIVED_JPEG_TS = 2;
    private static final int FLAG_RECEIVED_PREVIEW = 4;
    private static final int FLAG_RECEIVED_PREVIEW_TS = 8;
    private static final int MAX_JPEGS_IN_FLIGHT = 1;
    private static final String TAG = "CaptureCollector";
    private final TreeSet mActiveRequests = new TreeSet();
    private final ArrayList mCompletedRequests = new ArrayList();
    private final CameraDeviceState mDeviceState;
    private int mInFlight;
    private int mInFlightPreviews;
    private final Condition mIsEmpty;
    private final ArrayDeque mJpegCaptureQueue = new ArrayDeque(1);
    private final ArrayDeque mJpegProduceQueue = new ArrayDeque(1);
    private final ReentrantLock mLock = new ReentrantLock();
    private final int mMaxInFlight;
    private final Condition mNotFull;
    private final ArrayDeque mPreviewCaptureQueue;
    private final ArrayDeque mPreviewProduceQueue;
    private final Condition mPreviewsEmpty;
}
