// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;

import android.app.ActivityThread;
import android.hardware.camera2.*;
import android.hardware.camera2.params.*;
import android.hardware.camera2.utils.SubmitInfo;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.*;
import android.util.*;
import android.view.Surface;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.hardware.camera2.impl:
//            CameraCaptureSessionCore, ICameraDeviceUserWrapper, CameraConstrainedHighSpeedCaptureSessionImpl, CameraCaptureSessionImpl, 
//            CameraMetadataNative, CaptureResultExtras

public class CameraDeviceImpl extends CameraDevice
    implements android.os.IBinder.DeathRecipient
{
    public class CameraDeviceCallbacks extends android.hardware.camera2.ICameraDeviceCallbacks.Stub
    {

        private void onCaptureErrorLocked(int i, CaptureResultExtras captureresultextras)
        {
            int j = captureresultextras.getRequestId();
            int k = captureresultextras.getSubsequenceId();
            long l = captureresultextras.getFrameNumber();
            final CaptureCallbackHolder holder = (CaptureCallbackHolder)CameraDeviceImpl._2D_get3(CameraDeviceImpl.this).get(j);
            final CaptureRequest request = holder.getRequest(k);
            if(i == 5)
            {
                captureresultextras = ((OutputConfiguration)CameraDeviceImpl._2D_get4(CameraDeviceImpl.this).get(captureresultextras.getErrorStreamId())).getSurfaces().iterator();
                do
                {
                    if(!captureresultextras.hasNext())
                        break;
                    final Surface surface = (Surface)captureresultextras.next();
                    if(request.containsTarget(surface))
                    {
                        surface = l. new Runnable() {

                            public void run()
                            {
                                if(!CameraDeviceImpl._2D_wrap1(_fld0))
                                    holder.getCallback().onCaptureBufferLost(_fld0, request, surface, frameNumber);
                            }

                            final CameraDeviceCallbacks this$1;
                            final long val$frameNumber;
                            final CaptureCallbackHolder val$holder;
                            final CaptureRequest val$request;
                            final Surface val$surface;

            
            {
                this$1 = final_cameradevicecallbacks;
                holder = capturecallbackholder;
                request = capturerequest;
                surface = surface1;
                frameNumber = J.this;
                super();
            }
                        }
;
                        holder.getHandler().post(surface);
                    }
                } while(true);
            } else
            {
                boolean flag;
                if(i == 4)
                    flag = true;
                else
                    flag = false;
                if(CameraDeviceImpl._2D_get5(CameraDeviceImpl.this) != null && CameraDeviceImpl._2D_get5(CameraDeviceImpl.this).isAborting())
                    i = 1;
                else
                    i = 0;
                captureresultextras = (new CaptureFailure(request, i, flag, j, l)). new Runnable() {

                    public void run()
                    {
                        if(!CameraDeviceImpl._2D_wrap1(_fld0))
                            holder.getCallback().onCaptureFailed(_fld0, request, failure);
                    }

                    final CameraDeviceCallbacks this$1;
                    final CaptureFailure val$failure;
                    final CaptureCallbackHolder val$holder;
                    final CaptureRequest val$request;

            
            {
                this$1 = final_cameradevicecallbacks;
                holder = capturecallbackholder;
                request = capturerequest;
                failure = CaptureFailure.this;
                super();
            }
                }
;
                CameraDeviceImpl._2D_get8(CameraDeviceImpl.this).updateTracker(l, true, request.isReprocess());
                CameraDeviceImpl._2D_wrap2(CameraDeviceImpl.this);
                holder.getHandler().post(captureresultextras);
            }
        }

        public IBinder asBinder()
        {
            return this;
        }

        public void onCaptureStarted(CaptureResultExtras captureresultextras, long l)
        {
            int i;
            long l1;
            i = captureresultextras.getRequestId();
            l1 = captureresultextras.getFrameNumber();
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_42;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj1 = (CaptureCallbackHolder)CameraDeviceImpl._2D_get3(CameraDeviceImpl.this).get(i);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_68;
            obj;
            JVM INSTR monitorexit ;
            return;
            boolean flag = CameraDeviceImpl._2D_wrap1(CameraDeviceImpl.this);
            if(!flag)
                break MISSING_BLOCK_LABEL_86;
            obj;
            JVM INSTR monitorexit ;
            return;
            Handler handler = ((CaptureCallbackHolder) (obj1)).getHandler();
            Runnable runnable = JVM INSTR new #11  <Class CameraDeviceImpl$CameraDeviceCallbacks$2>;
            l.l1. _cls2();
            handler.post(runnable);
            obj;
            JVM INSTR monitorexit ;
            return;
            captureresultextras;
            throw captureresultextras;
        }

        public void onDeviceError(int i, CaptureResultExtras captureresultextras)
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            ICameraDeviceUserWrapper icameradeviceuserwrapper = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(icameradeviceuserwrapper != null)
                break MISSING_BLOCK_LABEL_27;
            obj;
            JVM INSTR monitorexit ;
            return;
            i;
            JVM INSTR tableswitch 0 5: default 68
        //                       0 146
        //                       1 105
        //                       2 105
        //                       3 177
        //                       4 177
        //                       5 177;
               goto _L1 _L2 _L3 _L3 _L4 _L4 _L4
_L1:
            captureresultextras = CameraDeviceImpl._2D_get0(CameraDeviceImpl.this);
            StringBuilder stringbuilder = JVM INSTR new #168 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e(captureresultextras, stringbuilder.append("Unknown error from camera device: ").append(i).toString());
_L3:
            CameraDeviceImpl._2D_set1(CameraDeviceImpl.this, true);
            if(i == 1)
                i = 4;
            else
                i = 5;
            captureresultextras = JVM INSTR new #9   <Class CameraDeviceImpl$CameraDeviceCallbacks$1>;
            i. super();
            CameraDeviceImpl._2D_get7(CameraDeviceImpl.this).post(captureresultextras);
_L5:
            obj;
            JVM INSTR monitorexit ;
            return;
_L2:
            CameraDeviceImpl._2D_get7(CameraDeviceImpl.this).post(CameraDeviceImpl._2D_get1(CameraDeviceImpl.this));
              goto _L5
            captureresultextras;
            throw captureresultextras;
_L4:
            onCaptureErrorLocked(i, captureresultextras);
              goto _L5
        }

        public void onDeviceIdle()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            ICameraDeviceUserWrapper icameradeviceuserwrapper = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(icameradeviceuserwrapper != null)
                break MISSING_BLOCK_LABEL_25;
            obj;
            JVM INSTR monitorexit ;
            return;
            if(!CameraDeviceImpl._2D_get9(CameraDeviceImpl.this))
                CameraDeviceImpl._2D_get7(CameraDeviceImpl.this).post(CameraDeviceImpl._2D_get2(CameraDeviceImpl.this));
            CameraDeviceImpl._2D_set0(CameraDeviceImpl.this, true);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onPrepared(int i)
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            OutputConfiguration outputconfiguration;
            Object obj1;
            outputconfiguration = (OutputConfiguration)CameraDeviceImpl._2D_get4(CameraDeviceImpl.this).get(i);
            obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 == null)
                return;
            break MISSING_BLOCK_LABEL_49;
            obj1;
            throw obj1;
            if(outputconfiguration == null)
            {
                Log.w(CameraDeviceImpl._2D_get0(CameraDeviceImpl.this), "onPrepared invoked for unknown output Surface");
                return;
            }
            for(Iterator iterator = outputconfiguration.getSurfaces().iterator(); iterator.hasNext(); ((StateCallbackKK) (obj1)).onSurfacePrepared((Surface)iterator.next()));
            return;
        }

        public void onRepeatingRequestError(long l, int i)
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            int j;
            if(CameraDeviceImpl._2D_get10(CameraDeviceImpl.this) == null)
                break MISSING_BLOCK_LABEL_37;
            j = CameraDeviceImpl._2D_get11(CameraDeviceImpl.this);
            if(j != -1)
                break MISSING_BLOCK_LABEL_41;
            obj;
            JVM INSTR monitorexit ;
            return;
            CameraDeviceImpl._2D_wrap3(CameraDeviceImpl.this, CameraDeviceImpl._2D_get11(CameraDeviceImpl.this), l);
            if(CameraDeviceImpl._2D_get11(CameraDeviceImpl.this) == i)
                CameraDeviceImpl._2D_set2(CameraDeviceImpl.this, -1);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onRequestQueueEmpty()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 == null)
            {
                return;
            } else
            {
                ((StateCallbackKK) (obj1)).onRequestQueueEmpty();
                return;
            }
            obj1;
            throw obj1;
        }

        public void onResultReceived(CameraMetadataNative camerametadatanative, final CaptureResultExtras resultExtras)
            throws RemoteException
        {
            int i;
            long l;
            i = resultExtras.getRequestId();
            l = resultExtras.getFrameNumber();
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            final CameraMetadataNative resultCopy = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(resultCopy != null)
                break MISSING_BLOCK_LABEL_41;
            obj;
            JVM INSTR monitorexit ;
            return;
            final CaptureCallbackHolder holder;
            final CaptureRequest request;
            camerametadatanative.set(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE, (Size)CameraDeviceImpl._2D_wrap0(CameraDeviceImpl.this).get(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE));
            holder = (CaptureCallbackHolder)CameraDeviceImpl._2D_get3(CameraDeviceImpl.this).get(i);
            request = holder.getRequest(resultExtras.getSubsequenceId());
            boolean flag;
            boolean flag1;
            if(resultExtras.getPartialResultCount() < CameraDeviceImpl._2D_get13(CameraDeviceImpl.this))
                flag = true;
            else
                flag = false;
            flag1 = request.isReprocess();
            if(holder != null)
                break MISSING_BLOCK_LABEL_147;
            CameraDeviceImpl._2D_get8(CameraDeviceImpl.this).updateTracker(l, null, flag, flag1);
            obj;
            JVM INSTR monitorexit ;
            return;
            if(!CameraDeviceImpl._2D_wrap1(CameraDeviceImpl.this))
                break MISSING_BLOCK_LABEL_178;
            CameraDeviceImpl._2D_get8(CameraDeviceImpl.this).updateTracker(l, null, flag, flag1);
            obj;
            JVM INSTR monitorexit ;
            return;
            if(!holder.hasBatchedOutputs()) goto _L2; else goto _L1
_L1:
            resultCopy = JVM INSTR new #270 <Class CameraMetadataNative>;
            resultCopy.CameraMetadataNative(camerametadatanative);
_L7:
            if(!flag) goto _L4; else goto _L3
_L3:
            Object obj1;
            obj1 = JVM INSTR new #291 <Class CaptureResult>;
            ((CaptureResult) (obj1)).CaptureResult(camerametadatanative, request, resultExtras);
            camerametadatanative = JVM INSTR new #13  <Class CameraDeviceImpl$CameraDeviceCallbacks$3>;
            request.obj1. _cls3();
            resultExtras = ((CaptureResultExtras) (obj1));
_L5:
            holder.getHandler().post(camerametadatanative);
            CameraDeviceImpl._2D_get8(CameraDeviceImpl.this).updateTracker(l, resultExtras, flag, flag1);
            if(flag)
                break MISSING_BLOCK_LABEL_276;
            CameraDeviceImpl._2D_wrap2(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            return;
_L2:
            resultCopy = null;
            continue; /* Loop/switch isn't completed */
_L4:
            final List partialResults = CameraDeviceImpl._2D_get8(CameraDeviceImpl.this).popPartialResults(l);
            final long sensorTimestamp = ((Long)camerametadatanative.get(CaptureResult.SENSOR_TIMESTAMP)).longValue();
            final Range fpsRange = (Range)request.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
            final int subsequenceId = resultExtras.getSubsequenceId();
            obj1 = JVM INSTR new #324 <Class TotalCaptureResult>;
            ((TotalCaptureResult) (obj1)).TotalCaptureResult(camerametadatanative, request, resultExtras, partialResults, holder.getSessionId());
            camerametadatanative = ((_cls4) (obj1)). new Runnable() {

                public void run()
                {
                    if(!CameraDeviceImpl._2D_wrap1(_fld0))
                        if(holder.hasBatchedOutputs())
                        {
                            for(int i = 0; i < holder.getRequestCount(); i++)
                            {
                                resultCopy.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(sensorTimestamp - ((long)(subsequenceId - i) * 0x3b9aca00L) / (long)((Integer)fpsRange.getUpper()).intValue()));
                                TotalCaptureResult totalcaptureresult = new TotalCaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(i), resultExtras, partialResults, holder.getSessionId());
                                holder.getCallback().onCaptureCompleted(_fld0, holder.getRequest(i), totalcaptureresult);
                            }

                        } else
                        {
                            holder.getCallback().onCaptureCompleted(_fld0, request, resultAsCapture);
                        }
                }

                final CameraDeviceCallbacks this$1;
                final Range val$fpsRange;
                final CaptureCallbackHolder val$holder;
                final List val$partialResults;
                final CaptureRequest val$request;
                final TotalCaptureResult val$resultAsCapture;
                final CameraMetadataNative val$resultCopy;
                final CaptureResultExtras val$resultExtras;
                final long val$sensorTimestamp;
                final int val$subsequenceId;

            
            {
                this$1 = final_cameradevicecallbacks;
                holder = capturecallbackholder;
                resultCopy = camerametadatanative;
                sensorTimestamp = l;
                subsequenceId = i;
                fpsRange = range;
                resultExtras = captureresultextras;
                partialResults = list;
                request = capturerequest;
                resultAsCapture = TotalCaptureResult.this;
                super();
            }
            }
;
            resultExtras = ((CaptureResultExtras) (obj1));
              goto _L5
            camerametadatanative;
            throw camerametadatanative;
            if(true) goto _L7; else goto _L6
_L6:
        }

        final CameraDeviceImpl this$0;

        public CameraDeviceCallbacks()
        {
            this$0 = CameraDeviceImpl.this;
            super();
        }
    }

    public static interface CaptureCallback
    {

        public abstract void onCaptureBufferLost(CameraDevice cameradevice, CaptureRequest capturerequest, Surface surface, long l);

        public abstract void onCaptureCompleted(CameraDevice cameradevice, CaptureRequest capturerequest, TotalCaptureResult totalcaptureresult);

        public abstract void onCaptureFailed(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureFailure capturefailure);

        public abstract void onCapturePartial(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureResult captureresult);

        public abstract void onCaptureProgressed(CameraDevice cameradevice, CaptureRequest capturerequest, CaptureResult captureresult);

        public abstract void onCaptureSequenceAborted(CameraDevice cameradevice, int i);

        public abstract void onCaptureSequenceCompleted(CameraDevice cameradevice, int i, long l);

        public abstract void onCaptureStarted(CameraDevice cameradevice, CaptureRequest capturerequest, long l, long l1);

        public static final int NO_FRAMES_CAPTURED = -1;
    }

    static class CaptureCallbackHolder
    {

        public CaptureCallback getCallback()
        {
            return mCallback;
        }

        public Handler getHandler()
        {
            return mHandler;
        }

        public CaptureRequest getRequest()
        {
            return getRequest(0);
        }

        public CaptureRequest getRequest(int i)
        {
            if(i >= mRequestList.size())
                throw new IllegalArgumentException(String.format("Requested subsequenceId %d is larger than request list size %d.", new Object[] {
                    Integer.valueOf(i), Integer.valueOf(mRequestList.size())
                }));
            if(i < 0)
                throw new IllegalArgumentException(String.format("Requested subsequenceId %d is negative", new Object[] {
                    Integer.valueOf(i)
                }));
            else
                return (CaptureRequest)mRequestList.get(i);
        }

        public int getRequestCount()
        {
            return mRequestList.size();
        }

        public int getSessionId()
        {
            return mSessionId;
        }

        public boolean hasBatchedOutputs()
        {
            return mHasBatchedOutputs;
        }

        public boolean isRepeating()
        {
            return mRepeating;
        }

        private final CaptureCallback mCallback;
        private final Handler mHandler;
        private final boolean mHasBatchedOutputs;
        private final boolean mRepeating;
        private final List mRequestList;
        private final int mSessionId;

        CaptureCallbackHolder(CaptureCallback capturecallback, List list, Handler handler, boolean flag, int i)
        {
            boolean flag1;
            if(capturecallback == null || handler == null)
                throw new UnsupportedOperationException("Must have a valid handler and a valid callback");
            mRepeating = flag;
            mHandler = handler;
            mRequestList = new ArrayList(list);
            mCallback = capturecallback;
            mSessionId = i;
            flag1 = true;
            i = 0;
_L7:
            flag = flag1;
            if(i >= list.size()) goto _L2; else goto _L1
_L1:
            capturecallback = (CaptureRequest)list.get(i);
            if(capturecallback.isPartOfCRequestList()) goto _L4; else goto _L3
_L3:
            flag = false;
_L2:
            mHasBatchedOutputs = flag;
            return;
_L4:
            if(i != 0 || capturecallback.getTargets().size() == 2)
                break; /* Loop/switch isn't completed */
            flag = false;
            if(true) goto _L2; else goto _L5
_L5:
            i++;
            if(true) goto _L7; else goto _L6
_L6:
        }
    }

    public class FrameNumberTracker
    {

        private void update()
        {
            Iterator iterator = mFutureErrorMap.entrySet().iterator();
            do
                if(iterator.hasNext())
                {
                    Object obj = (java.util.Map.Entry)iterator.next();
                    Long long1 = (Long)((java.util.Map.Entry) (obj)).getKey();
                    Boolean boolean1 = (Boolean)((java.util.Map.Entry) (obj)).getValue();
                    obj = Boolean.valueOf(true);
                    if(boolean1.booleanValue())
                    {
                        if(long1.longValue() == mCompletedReprocessFrameNumber + 1L)
                            mCompletedReprocessFrameNumber = long1.longValue();
                        else
                        if(!mSkippedReprocessFrameNumbers.isEmpty() && long1 == mSkippedReprocessFrameNumbers.element())
                        {
                            mCompletedReprocessFrameNumber = long1.longValue();
                            mSkippedReprocessFrameNumbers.remove();
                        } else
                        {
                            obj = Boolean.valueOf(false);
                        }
                    } else
                    if(long1.longValue() == mCompletedFrameNumber + 1L)
                        mCompletedFrameNumber = long1.longValue();
                    else
                    if(!mSkippedRegularFrameNumbers.isEmpty() && long1 == mSkippedRegularFrameNumbers.element())
                    {
                        mCompletedFrameNumber = long1.longValue();
                        mSkippedRegularFrameNumbers.remove();
                    } else
                    {
                        obj = Boolean.valueOf(false);
                    }
                    if(((Boolean) (obj)).booleanValue())
                        iterator.remove();
                } else
                {
                    return;
                }
            while(true);
        }

        private void updateCompletedFrameNumber(long l)
            throws IllegalArgumentException
        {
            if(l <= mCompletedFrameNumber)
                throw new IllegalArgumentException((new StringBuilder()).append("frame number ").append(l).append(" is a repeat").toString());
            if(l <= mCompletedReprocessFrameNumber)
            {
                if(mSkippedRegularFrameNumbers.isEmpty() || l < ((Long)mSkippedRegularFrameNumbers.element()).longValue())
                    throw new IllegalArgumentException((new StringBuilder()).append("frame number ").append(l).append(" is a repeat").toString());
                if(l > ((Long)mSkippedRegularFrameNumbers.element()).longValue())
                    throw new IllegalArgumentException((new StringBuilder()).append("frame number ").append(l).append(" comes out of order. Expecting ").append(mSkippedRegularFrameNumbers.element()).toString());
                mSkippedRegularFrameNumbers.remove();
            } else
            {
                long l1 = Math.max(mCompletedFrameNumber, mCompletedReprocessFrameNumber) + 1L;
                while(l1 < l) 
                {
                    mSkippedReprocessFrameNumbers.add(Long.valueOf(l1));
                    l1++;
                }
            }
            mCompletedFrameNumber = l;
        }

        private void updateCompletedReprocessFrameNumber(long l)
            throws IllegalArgumentException
        {
            if(l < mCompletedReprocessFrameNumber)
                throw new IllegalArgumentException((new StringBuilder()).append("frame number ").append(l).append(" is a repeat").toString());
            if(l < mCompletedFrameNumber)
            {
                if(mSkippedReprocessFrameNumbers.isEmpty() || l < ((Long)mSkippedReprocessFrameNumbers.element()).longValue())
                    throw new IllegalArgumentException((new StringBuilder()).append("frame number ").append(l).append(" is a repeat").toString());
                if(l > ((Long)mSkippedReprocessFrameNumbers.element()).longValue())
                    throw new IllegalArgumentException((new StringBuilder()).append("frame number ").append(l).append(" comes out of order. Expecting ").append(mSkippedReprocessFrameNumbers.element()).toString());
                mSkippedReprocessFrameNumbers.remove();
            } else
            {
                long l1 = Math.max(mCompletedFrameNumber, mCompletedReprocessFrameNumber) + 1L;
                while(l1 < l) 
                {
                    mSkippedRegularFrameNumbers.add(Long.valueOf(l1));
                    l1++;
                }
            }
            mCompletedReprocessFrameNumber = l;
        }

        public long getCompletedFrameNumber()
        {
            return mCompletedFrameNumber;
        }

        public long getCompletedReprocessFrameNumber()
        {
            return mCompletedReprocessFrameNumber;
        }

        public List popPartialResults(long l)
        {
            return (List)mPartialResults.remove(Long.valueOf(l));
        }

        public void updateTracker(long l, CaptureResult captureresult, boolean flag, boolean flag1)
        {
            if(!flag)
            {
                updateTracker(l, false, flag1);
                return;
            }
            if(captureresult == null)
                return;
            List list = (List)mPartialResults.get(Long.valueOf(l));
            Object obj = list;
            if(list == null)
            {
                obj = new ArrayList();
                mPartialResults.put(Long.valueOf(l), obj);
            }
            ((List) (obj)).add(captureresult);
        }

        public void updateTracker(long l, boolean flag, boolean flag1)
        {
            if(flag)
            {
                mFutureErrorMap.put(Long.valueOf(l), Boolean.valueOf(flag1));
            } else
            {
label0:
                {
                    if(!flag1)
                        break label0;
                    try
                    {
                        updateCompletedReprocessFrameNumber(l);
                    }
                    catch(IllegalArgumentException illegalargumentexception)
                    {
                        Log.e(CameraDeviceImpl._2D_get0(CameraDeviceImpl.this), illegalargumentexception.getMessage());
                    }
                }
            }
_L1:
            update();
            return;
            updateCompletedFrameNumber(l);
              goto _L1
        }

        private long mCompletedFrameNumber;
        private long mCompletedReprocessFrameNumber;
        private final TreeMap mFutureErrorMap = new TreeMap();
        private final HashMap mPartialResults = new HashMap();
        private final LinkedList mSkippedRegularFrameNumbers = new LinkedList();
        private final LinkedList mSkippedReprocessFrameNumbers = new LinkedList();
        final CameraDeviceImpl this$0;

        public FrameNumberTracker()
        {
            this$0 = CameraDeviceImpl.this;
            super();
            mCompletedFrameNumber = -1L;
            mCompletedReprocessFrameNumber = -1L;
        }
    }

    static class RequestLastFrameNumbersHolder
    {

        public long getLastFrameNumber()
        {
            return Math.max(mLastRegularFrameNumber, mLastReprocessFrameNumber);
        }

        public long getLastRegularFrameNumber()
        {
            return mLastRegularFrameNumber;
        }

        public long getLastReprocessFrameNumber()
        {
            return mLastReprocessFrameNumber;
        }

        public int getRequestId()
        {
            return mRequestId;
        }

        private final long mLastRegularFrameNumber;
        private final long mLastReprocessFrameNumber;
        private final int mRequestId;

        public RequestLastFrameNumbersHolder(int i, long l)
        {
            mLastRegularFrameNumber = l;
            mLastReprocessFrameNumber = -1L;
            mRequestId = i;
        }

        public RequestLastFrameNumbersHolder(List list, SubmitInfo submitinfo)
        {
            long l;
            long l1;
            long l2;
            int i;
            l = -1L;
            l1 = -1L;
            l2 = submitinfo.getLastFrameNumber();
            if(submitinfo.getLastFrameNumber() < (long)(list.size() - 1))
                throw new IllegalArgumentException((new StringBuilder()).append("lastFrameNumber: ").append(submitinfo.getLastFrameNumber()).append(" should be at least ").append(list.size() - 1).append(" for the number of ").append(" requests in the list: ").append(list.size()).toString());
            i = list.size() - 1;
_L8:
            long l3;
            long l4;
            l3 = l;
            l4 = l1;
            if(i < 0) goto _L2; else goto _L1
_L1:
            CaptureRequest capturerequest = (CaptureRequest)list.get(i);
            if(!capturerequest.isReprocess() || l1 != -1L) goto _L4; else goto _L3
_L3:
            l4 = l2;
            l3 = l;
_L6:
            if(l4 == -1L || l3 == -1L)
                break; /* Loop/switch isn't completed */
_L2:
            mLastRegularFrameNumber = l3;
            mLastReprocessFrameNumber = l4;
            mRequestId = submitinfo.getRequestId();
            return;
_L4:
            l3 = l;
            l4 = l1;
            if(!capturerequest.isReprocess())
            {
                l3 = l;
                l4 = l1;
                if(l == -1L)
                {
                    l3 = l2;
                    l4 = l1;
                }
            }
            if(true) goto _L6; else goto _L5
_L5:
            l2--;
            i--;
            l = l3;
            l1 = l4;
            if(true) goto _L8; else goto _L7
_L7:
        }
    }

    public static abstract class StateCallbackKK extends android.hardware.camera2.CameraDevice.StateCallback
    {

        public void onActive(CameraDevice cameradevice)
        {
        }

        public void onBusy(CameraDevice cameradevice)
        {
        }

        public void onIdle(CameraDevice cameradevice)
        {
        }

        public void onRequestQueueEmpty()
        {
        }

        public void onSurfacePrepared(Surface surface)
        {
        }

        public void onUnconfigured(CameraDevice cameradevice)
        {
        }

        public StateCallbackKK()
        {
        }
    }


    static String _2D_get0(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.TAG;
    }

    static Runnable _2D_get1(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mCallOnDisconnected;
    }

    static ICameraDeviceUserWrapper _2D_get10(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mRemoteDevice;
    }

    static int _2D_get11(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mRepeatingRequestId;
    }

    static StateCallbackKK _2D_get12(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mSessionStateCallback;
    }

    static int _2D_get13(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mTotalPartialCount;
    }

    static Runnable _2D_get2(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mCallOnIdle;
    }

    static SparseArray _2D_get3(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mCaptureCallbackMap;
    }

    static SparseArray _2D_get4(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mConfiguredOutputs;
    }

    static CameraCaptureSessionCore _2D_get5(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mCurrentSession;
    }

    static android.hardware.camera2.CameraDevice.StateCallback _2D_get6(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mDeviceCallback;
    }

    static Handler _2D_get7(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mDeviceHandler;
    }

    static FrameNumberTracker _2D_get8(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mFrameNumberTracker;
    }

    static boolean _2D_get9(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.mIdle;
    }

    static boolean _2D_set0(CameraDeviceImpl cameradeviceimpl, boolean flag)
    {
        cameradeviceimpl.mIdle = flag;
        return flag;
    }

    static boolean _2D_set1(CameraDeviceImpl cameradeviceimpl, boolean flag)
    {
        cameradeviceimpl.mInError = flag;
        return flag;
    }

    static int _2D_set2(CameraDeviceImpl cameradeviceimpl, int i)
    {
        cameradeviceimpl.mRepeatingRequestId = i;
        return i;
    }

    static CameraCharacteristics _2D_wrap0(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.getCharacteristics();
    }

    static boolean _2D_wrap1(CameraDeviceImpl cameradeviceimpl)
    {
        return cameradeviceimpl.isClosed();
    }

    static void _2D_wrap2(CameraDeviceImpl cameradeviceimpl)
    {
        cameradeviceimpl.checkAndFireSequenceComplete();
    }

    static void _2D_wrap3(CameraDeviceImpl cameradeviceimpl, int i, long l)
    {
        cameradeviceimpl.checkEarlyTriggerSequenceComplete(i, l);
    }

    public CameraDeviceImpl(String s, android.hardware.camera2.CameraDevice.StateCallback statecallback, Handler handler, CameraCharacteristics cameracharacteristics, int i)
    {
        customOpMode = 0;
        mInError = false;
        mIdle = true;
        mRepeatingRequestId = -1;
        mConfiguredInput = new java.util.AbstractMap.SimpleEntry(Integer.valueOf(-1), null);
        mNextSessionId = 0;
        mIsPrivilegedApp = false;
        for(mCallOnDisconnected = new Runnable() {

        public void run()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_25;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
                ((StateCallbackKK) (obj1)).onDisconnected(CameraDeviceImpl.this);
            CameraDeviceImpl._2D_get6(CameraDeviceImpl.this).onDisconnected(CameraDeviceImpl.this);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
            }
    }
; s == null || statecallback == null || handler == null || cameracharacteristics == null;)
            throw new IllegalArgumentException("Null argument given");

        mCameraId = s;
        mDeviceCallback = statecallback;
        mDeviceHandler = handler;
        mCharacteristics = cameracharacteristics;
        mAppTargetSdkVersion = i;
        statecallback = String.format("CameraDevice-JV-%s", new Object[] {
            mCameraId
        });
        s = statecallback;
        if(statecallback.length() > 23)
            s = statecallback.substring(0, 23);
        TAG = s;
        s = (Integer)mCharacteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
        if(s == null)
            mTotalPartialCount = 1;
        else
            mTotalPartialCount = s.intValue();
        mIsPrivilegedApp = checkPrivilegedAppList();
    }

    private void checkAndFireSequenceComplete()
    {
        long l;
        long l1;
        Iterator iterator;
        l = mFrameNumberTracker.getCompletedFrameNumber();
        l1 = mFrameNumberTracker.getCompletedReprocessFrameNumber();
        iterator = mRequestLastFrameNumbersList.iterator();
_L6:
        final RequestLastFrameNumbersHolder requestLastFrameNumbers;
        boolean flag;
        final int requestId;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_246;
        requestLastFrameNumbers = (RequestLastFrameNumbersHolder)iterator.next();
        flag = false;
        requestId = requestLastFrameNumbers.getRequestId();
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        if(mRemoteDevice != null)
            break MISSING_BLOCK_LABEL_90;
        Log.w(TAG, "Camera closed while checking sequences");
        obj;
        JVM INSTR monitorexit ;
        return;
        int i = mCaptureCallbackMap.indexOfKey(requestId);
        if(i < 0) goto _L2; else goto _L1
_L1:
        final CaptureCallbackHolder holder = (CaptureCallbackHolder)mCaptureCallbackMap.valueAt(i);
_L4:
        boolean flag1;
        flag1 = flag;
        if(holder == null)
            break MISSING_BLOCK_LABEL_177;
        long l2;
        long l3;
        l2 = requestLastFrameNumbers.getLastRegularFrameNumber();
        l3 = requestLastFrameNumbers.getLastReprocessFrameNumber();
        flag1 = flag;
        if(l2 > l)
            break MISSING_BLOCK_LABEL_177;
        flag1 = flag;
        if(l3 > l1)
            break MISSING_BLOCK_LABEL_177;
        flag1 = true;
        mCaptureCallbackMap.removeAt(i);
        obj;
        JVM INSTR monitorexit ;
        if(holder == null || flag1)
            iterator.remove();
        if(flag1)
        {
            obj = new Runnable() {

                public void run()
                {
                    if(!CameraDeviceImpl._2D_wrap1(CameraDeviceImpl.this))
                        holder.getCallback().onCaptureSequenceCompleted(CameraDeviceImpl.this, requestId, requestLastFrameNumbers.getLastFrameNumber());
                }

                final CameraDeviceImpl this$0;
                final CaptureCallbackHolder val$holder;
                final int val$requestId;
                final RequestLastFrameNumbersHolder val$requestLastFrameNumbers;

            
            {
                this$0 = CameraDeviceImpl.this;
                requestId = i;
                holder = capturecallbackholder;
                requestLastFrameNumbers = requestlastframenumbersholder;
                super();
            }
            }
;
            holder.getHandler().post(((Runnable) (obj)));
        }
        continue; /* Loop/switch isn't completed */
_L2:
        holder = null;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void checkEarlyTriggerSequenceComplete(final int requestId, long l)
    {
        if(l == -1L)
        {
            int i = mCaptureCallbackMap.indexOfKey(requestId);
            final CaptureCallbackHolder holder;
            if(i >= 0)
                holder = (CaptureCallbackHolder)mCaptureCallbackMap.valueAt(i);
            else
                holder = null;
            if(holder != null)
                mCaptureCallbackMap.removeAt(i);
            if(holder != null)
            {
                Runnable runnable = new Runnable() {

                    public void run()
                    {
                        if(!CameraDeviceImpl._2D_wrap1(CameraDeviceImpl.this))
                            holder.getCallback().onCaptureSequenceAborted(CameraDeviceImpl.this, requestId);
                    }

                    final CameraDeviceImpl this$0;
                    final CaptureCallbackHolder val$holder;
                    final int val$requestId;

            
            {
                this$0 = CameraDeviceImpl.this;
                requestId = i;
                holder = capturecallbackholder;
                super();
            }
                }
;
                holder.getHandler().post(runnable);
            } else
            {
                Log.w(TAG, String.format("did not register callback to request %d", new Object[] {
                    Integer.valueOf(requestId)
                }));
            }
        } else
        {
            mRequestLastFrameNumbersList.add(new RequestLastFrameNumbersHolder(requestId, l));
            checkAndFireSequenceComplete();
        }
    }

    static Handler checkHandler(Handler handler)
    {
        Handler handler1 = handler;
        if(handler == null)
        {
            handler = Looper.myLooper();
            if(handler == null)
                throw new IllegalArgumentException("No handler given, and current thread has no looper!");
            handler1 = new Handler(handler);
        }
        return handler1;
    }

    static Handler checkHandler(Handler handler, Object obj)
    {
        if(obj != null)
            return checkHandler(handler);
        else
            return handler;
    }

    private void checkIfCameraClosedOrInError()
        throws CameraAccessException
    {
        if(mRemoteDevice == null)
            throw new IllegalStateException("CameraDevice was already closed");
        if(mInError)
            throw new CameraAccessException(3, "The camera device has encountered a serious error");
        else
            return;
    }

    private void checkInputConfiguration(InputConfiguration inputconfiguration)
    {
        boolean flag = false;
        if(inputconfiguration != null)
        {
            StreamConfigurationMap streamconfigurationmap = (StreamConfigurationMap)mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if(isPrivilegedApp())
            {
                Log.w(TAG, "ignore input format/size check for white listed app");
                return;
            }
            Object aobj[] = streamconfigurationmap.getInputFormats();
            boolean flag1 = false;
            int i = aobj.length;
            for(int j = 0; j < i; j++)
                if(aobj[j] == inputconfiguration.getFormat())
                    flag1 = true;

            if(!flag1)
                throw new IllegalArgumentException((new StringBuilder()).append("input format ").append(inputconfiguration.getFormat()).append(" is not valid").toString());
            flag1 = false;
            aobj = streamconfigurationmap.getInputSizes(inputconfiguration.getFormat());
            i = aobj.length;
            int k = ((flag) ? 1 : 0);
            for(flag = flag1; k < i; flag = flag1)
            {
                Size size = aobj[k];
                flag1 = flag;
                if(inputconfiguration.getWidth() == size.getWidth())
                {
                    flag1 = flag;
                    if(inputconfiguration.getHeight() == size.getHeight())
                        flag1 = true;
                }
                k++;
            }

            if(!flag)
                throw new IllegalArgumentException((new StringBuilder()).append("input size ").append(inputconfiguration.getWidth()).append("x").append(inputconfiguration.getHeight()).append(" is not valid").toString());
        }
    }

    private boolean checkPrivilegedAppList()
    {
label0:
        {
            String s = ActivityThread.currentOpPackageName();
            Object obj = SystemProperties.get("persist.camera.privapp.list");
            if(((String) (obj)).length() <= 0)
                break label0;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(((String) (obj)));
            obj = simplestringsplitter.iterator();
            do
                if(!((Iterator) (obj)).hasNext())
                    break label0;
            while(!s.equals((String)((Iterator) (obj)).next()));
            return true;
        }
        return false;
    }

    private void createCaptureSessionInternal(InputConfiguration inputconfiguration, List list, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler, int i)
        throws CameraAccessException
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        checkIfCameraClosedOrInError();
        boolean flag;
        if(i == 1)
            flag = true;
        else
            flag = false;
        if(!flag || inputconfiguration == null)
            break MISSING_BLOCK_LABEL_56;
        inputconfiguration = JVM INSTR new #268 <Class IllegalArgumentException>;
        inputconfiguration.IllegalArgumentException("Constrained high speed session doesn't support input configuration yet.");
        throw inputconfiguration;
        inputconfiguration;
        obj;
        JVM INSTR monitorexit ;
        throw inputconfiguration;
        if(mCurrentSession != null)
            mCurrentSession.replaceSessionClose();
        Object obj1;
        Object obj2;
        obj1 = null;
        obj2 = null;
        boolean flag2;
        boolean flag1;
        CameraAccessException cameraaccessexception;
        try
        {
            flag1 = configureStreamsChecked(inputconfiguration, list, i);
        }
        // Misplaced declaration of an exception variable
        catch(CameraAccessException cameraaccessexception)
        {
            flag2 = false;
            list = null;
            continue; /* Loop/switch isn't completed */
        }
        flag2 = flag1;
        list = obj2;
        cameraaccessexception = obj1;
        if(!flag1)
            break MISSING_BLOCK_LABEL_135;
        flag2 = flag1;
        list = obj2;
        cameraaccessexception = obj1;
        if(inputconfiguration == null)
            break MISSING_BLOCK_LABEL_135;
        list = mRemoteDevice.getInputSurface();
        cameraaccessexception = obj1;
        flag2 = flag1;
_L3:
        if(!flag)
            break MISSING_BLOCK_LABEL_201;
        inputconfiguration = JVM INSTR new #531 <Class CameraConstrainedHighSpeedCaptureSessionImpl>;
        i = mNextSessionId;
        mNextSessionId = i + 1;
        inputconfiguration.CameraConstrainedHighSpeedCaptureSessionImpl(i, statecallback, handler, this, mDeviceHandler, flag2, mCharacteristics);
_L1:
        mCurrentSession = inputconfiguration;
        if(cameraaccessexception == null)
            break MISSING_BLOCK_LABEL_239;
        throw cameraaccessexception;
        i = mNextSessionId;
        mNextSessionId = i + 1;
        inputconfiguration = new CameraCaptureSessionImpl(i, list, statecallback, handler, this, mDeviceHandler, flag2);
          goto _L1
        mSessionStateCallback = mCurrentSession.getDeviceStateCallback();
        obj;
        JVM INSTR monitorexit ;
        return;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private CameraCharacteristics getCharacteristics()
    {
        return mCharacteristics;
    }

    private boolean isClosed()
    {
        return mClosing.get();
    }

    private void overrideEnableZsl(CameraMetadataNative camerametadatanative, boolean flag)
    {
        if((Boolean)camerametadatanative.get(CaptureRequest.CONTROL_ENABLE_ZSL) == null)
        {
            return;
        } else
        {
            camerametadatanative.set(CaptureRequest.CONTROL_ENABLE_ZSL, Boolean.valueOf(flag));
            return;
        }
    }

    private int submitCaptureRequest(List list, CaptureCallback capturecallback, Handler handler, boolean flag)
        throws CameraAccessException
    {
        Handler handler1;
        handler1 = checkHandler(handler, capturecallback);
        handler = list.iterator();
_L2:
        Object obj;
        if(!handler.hasNext())
            break MISSING_BLOCK_LABEL_104;
        obj = (CaptureRequest)handler.next();
        if(((CaptureRequest) (obj)).getTargets().isEmpty())
            throw new IllegalArgumentException("Each request must have at least one Surface target");
        obj = ((CaptureRequest) (obj)).getTargets().iterator();
_L4:
        if(!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        if((Surface)((Iterator) (obj)).next() != null) goto _L4; else goto _L3
_L3:
        throw new IllegalArgumentException("Null Surface targets are not allowed");
        handler = ((Handler) (mInterfaceLock));
        handler;
        JVM INSTR monitorenter ;
        checkIfCameraClosedOrInError();
        if(!flag)
            break MISSING_BLOCK_LABEL_124;
        stopRepeating();
        CaptureRequest acapturerequest[] = (CaptureRequest[])list.toArray(new CaptureRequest[list.size()]);
        acapturerequest = mRemoteDevice.submitRequestList(acapturerequest, flag);
        if(capturecallback == null)
            break MISSING_BLOCK_LABEL_205;
        SparseArray sparsearray = mCaptureCallbackMap;
        int i = acapturerequest.getRequestId();
        CaptureCallbackHolder capturecallbackholder = JVM INSTR new #48  <Class CameraDeviceImpl$CaptureCallbackHolder>;
        capturecallbackholder.CaptureCallbackHolder(capturecallback, list, handler1, flag, mNextSessionId - 1);
        sparsearray.put(i, capturecallbackholder);
        if(!flag)
            break MISSING_BLOCK_LABEL_276;
        if(mRepeatingRequestId != -1)
            checkEarlyTriggerSequenceComplete(mRepeatingRequestId, acapturerequest.getLastFrameNumber());
        mRepeatingRequestId = acapturerequest.getRequestId();
_L5:
        int j;
        if(mIdle)
            mDeviceHandler.post(mCallOnActive);
        mIdle = false;
        j = acapturerequest.getRequestId();
        handler;
        JVM INSTR monitorexit ;
        return j;
        capturecallback = mRequestLastFrameNumbersList;
        RequestLastFrameNumbersHolder requestlastframenumbersholder = JVM INSTR new #54  <Class CameraDeviceImpl$RequestLastFrameNumbersHolder>;
        requestlastframenumbersholder.RequestLastFrameNumbersHolder(list, acapturerequest);
        capturecallback.add(requestlastframenumbersholder);
          goto _L5
        list;
        throw list;
    }

    private void waitUntilIdle()
        throws CameraAccessException
    {
        synchronized(mInterfaceLock)
        {
            checkIfCameraClosedOrInError();
            if(mRepeatingRequestId != -1)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #414 <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("Active repeating request ongoing");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_37;
        }
        mRemoteDevice.waitUntilIdle();
        obj;
        JVM INSTR monitorexit ;
    }

    public void binderDied()
    {
        Log.w(TAG, (new StringBuilder()).append("CameraDevice ").append(mCameraId).append(" died unexpectedly").toString());
        if(mRemoteDevice == null)
        {
            return;
        } else
        {
            mInError = true;
            Runnable runnable = new Runnable() {

                public void run()
                {
                    if(!CameraDeviceImpl._2D_wrap1(CameraDeviceImpl.this))
                        CameraDeviceImpl._2D_get6(CameraDeviceImpl.this).onError(CameraDeviceImpl.this, 5);
                }

                final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
            }
            }
;
            mDeviceHandler.post(runnable);
            return;
        }
    }

    public int capture(CaptureRequest capturerequest, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(capturerequest);
        return submitCaptureRequest(arraylist, capturecallback, handler, false);
    }

    public int captureBurst(List list, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(list == null || list.isEmpty())
            throw new IllegalArgumentException("At least one request must be given");
        else
            return submitCaptureRequest(list, capturecallback, handler, false);
    }

    public void close()
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mClosing.getAndSet(true);
        if(!flag)
            break MISSING_BLOCK_LABEL_23;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mRemoteDevice != null)
        {
            mRemoteDevice.disconnect();
            mRemoteDevice.unlinkToDeath(this, 0);
        }
        if(mRemoteDevice != null || mInError)
            mDeviceHandler.post(mCallOnClosed);
        mRemoteDevice = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void configureOutputs(List list)
        throws CameraAccessException
    {
        ArrayList arraylist = new ArrayList(list.size());
        for(list = list.iterator(); list.hasNext(); arraylist.add(new OutputConfiguration((Surface)list.next())));
        configureStreamsChecked(null, arraylist, 0);
    }

    public boolean configureStreamsChecked(InputConfiguration inputconfiguration, List list, int i)
        throws CameraAccessException
    {
        Object obj;
        obj = list;
        if(list == null)
            obj = new ArrayList();
        if(((List) (obj)).size() == 0 && inputconfiguration != null)
            throw new IllegalArgumentException("cannot configure an input stream without any output streams");
        checkInputConfiguration(inputconfiguration);
        list = ((List) (mInterfaceLock));
        list;
        JVM INSTR monitorenter ;
        Object obj1;
        Object obj2;
        checkIfCameraClosedOrInError();
        obj1 = JVM INSTR new #670 <Class HashSet>;
        ((HashSet) (obj1)).HashSet(((Collection) (obj)));
        obj2 = JVM INSTR new #241 <Class ArrayList>;
        ((ArrayList) (obj2)).ArrayList();
        int j = 0;
_L2:
        Object obj3;
        if(j >= mConfiguredOutputs.size())
            break MISSING_BLOCK_LABEL_174;
        int j1 = mConfiguredOutputs.keyAt(j);
        obj3 = (OutputConfiguration)mConfiguredOutputs.valueAt(j);
        if(((List) (obj)).contains(obj3) && !((OutputConfiguration) (obj3)).isDeferredConfiguration())
            break; /* Loop/switch isn't completed */
        ((List) (obj2)).add(Integer.valueOf(j1));
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        ((HashSet) (obj1)).remove(obj3);
          goto _L3
        inputconfiguration;
        throw inputconfiguration;
        mDeviceHandler.post(mCallOnBusy);
        stopRepeating();
        waitUntilIdle();
        mRemoteDevice.beginConfigure();
        obj3 = (InputConfiguration)mConfiguredInput.getValue();
        if(inputconfiguration == obj3)
            break MISSING_BLOCK_LABEL_327;
        if(inputconfiguration == null)
            break MISSING_BLOCK_LABEL_234;
        if(!(inputconfiguration.equals(obj3) ^ true))
            break MISSING_BLOCK_LABEL_327;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_280;
        mRemoteDevice.deleteStream(((Integer)mConfiguredInput.getKey()).intValue());
        java.util.AbstractMap.SimpleEntry simpleentry = JVM INSTR new #228 <Class java.util.AbstractMap$SimpleEntry>;
        simpleentry.java.util.AbstractMap.SimpleEntry(Integer.valueOf(-1), null);
        mConfiguredInput = simpleentry;
        if(inputconfiguration == null)
            break MISSING_BLOCK_LABEL_327;
        int k = mRemoteDevice.createInputStream(inputconfiguration.getWidth(), inputconfiguration.getHeight(), inputconfiguration.getFormat());
        java.util.AbstractMap.SimpleEntry simpleentry1 = JVM INSTR new #228 <Class java.util.AbstractMap$SimpleEntry>;
        simpleentry1.java.util.AbstractMap.SimpleEntry(Integer.valueOf(k), inputconfiguration);
        mConfiguredInput = simpleentry1;
        for(obj2 = ((Iterable) (obj2)).iterator(); ((Iterator) (obj2)).hasNext(); mConfiguredOutputs.delete(inputconfiguration.intValue()))
        {
            inputconfiguration = (Integer)((Iterator) (obj2)).next();
            mRemoteDevice.deleteStream(inputconfiguration.intValue());
        }

          goto _L4
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        inputconfiguration = TAG;
        obj1 = JVM INSTR new #446 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.w(inputconfiguration, ((StringBuilder) (obj1)).append("Stream configuration failed due to: ").append(illegalargumentexception.getMessage()).toString());
        if(true) goto _L6; else goto _L5
_L5:
        if(((List) (obj)).size() <= 0) goto _L6; else goto _L7
_L7:
        mDeviceHandler.post(mCallOnIdle);
_L11:
        list;
        JVM INSTR monitorexit ;
        return false;
_L4:
        for(inputconfiguration = ((Iterable) (obj)).iterator(); inputconfiguration.hasNext();)
        {
            OutputConfiguration outputconfiguration = (OutputConfiguration)inputconfiguration.next();
            if(((HashSet) (obj1)).contains(outputconfiguration))
            {
                int l = mRemoteDevice.createStream(outputconfiguration);
                mConfiguredOutputs.put(l, outputconfiguration);
            }
        }

          goto _L8
        inputconfiguration;
        if(inputconfiguration.getReason() == 4)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #414 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("The camera is currently busy. You must wait until the previous operation completes.", inputconfiguration);
            throw illegalstateexception;
        }
          goto _L9
        inputconfiguration;
        if(true)
            break MISSING_BLOCK_LABEL_653;
        if(((List) (obj)).size() <= 0)
            break MISSING_BLOCK_LABEL_653;
        mDeviceHandler.post(mCallOnIdle);
_L12:
        throw inputconfiguration;
_L8:
        int i1 = customOpMode;
        mRemoteDevice.endConfigure(i | i1 << 16);
        if(false)
            break MISSING_BLOCK_LABEL_621;
        if(((List) (obj)).size() <= 0)
            break MISSING_BLOCK_LABEL_621;
        mDeviceHandler.post(mCallOnIdle);
_L10:
        list;
        JVM INSTR monitorexit ;
        return true;
        mDeviceHandler.post(mCallOnUnconfigured);
          goto _L10
_L9:
        throw inputconfiguration;
_L6:
        mDeviceHandler.post(mCallOnUnconfigured);
          goto _L11
        mDeviceHandler.post(mCallOnUnconfigured);
          goto _L12
    }

    public android.hardware.camera2.CaptureRequest.Builder createCaptureRequest(int i)
        throws CameraAccessException
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        checkIfCameraClosedOrInError();
        obj1 = mRemoteDevice.createDefaultRequest(i);
        if(mAppTargetSdkVersion >= 26 && i == 2)
            break MISSING_BLOCK_LABEL_40;
        overrideEnableZsl(((CameraMetadataNative) (obj1)), false);
        obj1 = new android.hardware.camera2.CaptureRequest.Builder(((CameraMetadataNative) (obj1)), false, -1);
        obj;
        JVM INSTR monitorexit ;
        return ((android.hardware.camera2.CaptureRequest.Builder) (obj1));
        Exception exception;
        exception;
        throw exception;
    }

    public void createCaptureSession(List list, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException
    {
        ArrayList arraylist = new ArrayList(list.size());
        for(list = list.iterator(); list.hasNext(); arraylist.add(new OutputConfiguration((Surface)list.next())));
        createCaptureSessionInternal(null, arraylist, statecallback, handler, 0);
    }

    public void createCaptureSessionByOutputConfigurations(List list, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException
    {
        createCaptureSessionInternal(null, new ArrayList(list), statecallback, handler, 0);
    }

    public void createConstrainedHighSpeedCaptureSession(List list, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException
    {
        while(list == null || list.size() == 0 || list.size() > 2) 
            throw new IllegalArgumentException("Output surface list must not be null and the size must be no more than 2");
        SurfaceUtils.checkConstrainedHighSpeedSurfaces(list, null, (StreamConfigurationMap)getCharacteristics().get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP));
        ArrayList arraylist = new ArrayList(list.size());
        for(list = list.iterator(); list.hasNext(); arraylist.add(new OutputConfiguration((Surface)list.next())));
        createCaptureSessionInternal(null, arraylist, statecallback, handler, 1);
    }

    public void createCustomCaptureSession(InputConfiguration inputconfiguration, List list, int i, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException
    {
        ArrayList arraylist = new ArrayList();
        for(list = list.iterator(); list.hasNext(); arraylist.add(new OutputConfiguration((OutputConfiguration)list.next())));
        createCaptureSessionInternal(inputconfiguration, arraylist, statecallback, handler, i);
    }

    public android.hardware.camera2.CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult totalcaptureresult)
        throws CameraAccessException
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        checkIfCameraClosedOrInError();
        CameraMetadataNative camerametadatanative = JVM INSTR new #556 <Class CameraMetadataNative>;
        camerametadatanative.CameraMetadataNative(totalcaptureresult.getNativeCopy());
        totalcaptureresult = new android.hardware.camera2.CaptureRequest.Builder(camerametadatanative, true, totalcaptureresult.getSessionId());
        obj;
        JVM INSTR monitorexit ;
        return totalcaptureresult;
        totalcaptureresult;
        throw totalcaptureresult;
    }

    public void createReprocessableCaptureSession(InputConfiguration inputconfiguration, List list, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException
    {
        if(inputconfiguration == null)
            throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
        ArrayList arraylist = new ArrayList(list.size());
        for(list = list.iterator(); list.hasNext(); arraylist.add(new OutputConfiguration((Surface)list.next())));
        createCaptureSessionInternal(inputconfiguration, arraylist, statecallback, handler, 0);
    }

    public void createReprocessableCaptureSessionByConfigurations(InputConfiguration inputconfiguration, List list, android.hardware.camera2.CameraCaptureSession.StateCallback statecallback, Handler handler)
        throws CameraAccessException
    {
        if(inputconfiguration == null)
            throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
        if(list == null)
            throw new IllegalArgumentException("Output configurations cannot be null when creating a reprocessable capture session");
        ArrayList arraylist = new ArrayList();
        for(list = list.iterator(); list.hasNext(); arraylist.add(new OutputConfiguration((OutputConfiguration)list.next())));
        createCaptureSessionInternal(inputconfiguration, arraylist, statecallback, handler, 0);
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void finalizeOutputConfigs(List list)
        throws CameraAccessException
    {
        if(list == null || list.size() == 0)
            throw new IllegalArgumentException("deferred config is null or empty");
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        list = list.iterator();
_L3:
        Object obj1;
        if(!list.hasNext())
            break MISSING_BLOCK_LABEL_200;
        obj1 = (OutputConfiguration)list.next();
        byte byte0;
        int i;
        byte0 = -1;
        i = 0;
_L2:
        int j = byte0;
        if(i < mConfiguredOutputs.size())
        {
            if(!((OutputConfiguration) (obj1)).equals(mConfiguredOutputs.valueAt(i)))
                break MISSING_BLOCK_LABEL_130;
            j = mConfiguredOutputs.keyAt(i);
        }
        if(j != -1)
            break; /* Loop/switch isn't completed */
        list = JVM INSTR new #268 <Class IllegalArgumentException>;
        list.IllegalArgumentException("Deferred config is not part of this session");
        throw list;
        list;
        obj;
        JVM INSTR monitorexit ;
        throw list;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(((OutputConfiguration) (obj1)).getSurfaces().size() == 0)
        {
            list = JVM INSTR new #268 <Class IllegalArgumentException>;
            obj1 = JVM INSTR new #446 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            list.IllegalArgumentException(((StringBuilder) (obj1)).append("The final config for stream ").append(j).append(" must have at least 1 surface").toString());
            throw list;
        }
        mRemoteDevice.finalizeOutputConfigurations(j, ((OutputConfiguration) (obj1)));
          goto _L3
        obj;
        JVM INSTR monitorexit ;
    }

    public void flush()
        throws CameraAccessException
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        checkIfCameraClosedOrInError();
        mDeviceHandler.post(mCallOnBusy);
        if(!mIdle)
            break MISSING_BLOCK_LABEL_45;
        mDeviceHandler.post(mCallOnIdle);
        obj;
        JVM INSTR monitorexit ;
        return;
        long l = mRemoteDevice.flush();
        if(mRepeatingRequestId != -1)
        {
            checkEarlyTriggerSequenceComplete(mRepeatingRequestId, l);
            mRepeatingRequestId = -1;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public CameraDeviceCallbacks getCallbacks()
    {
        return mCallbacks;
    }

    public String getId()
    {
        return mCameraId;
    }

    public boolean isPrivilegedApp()
    {
        return mIsPrivilegedApp;
    }

    public void prepare(int i, Surface surface)
        throws CameraAccessException
    {
        if(surface == null)
            throw new IllegalArgumentException("Surface is null");
        if(i <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid maxCount given: ").append(i).toString());
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        byte byte0;
        int j;
        byte0 = -1;
        j = 0;
_L2:
        int k = byte0;
        if(j < mConfiguredOutputs.size())
        {
            if(surface != ((OutputConfiguration)mConfiguredOutputs.valueAt(j)).getSurface())
                break MISSING_BLOCK_LABEL_130;
            k = mConfiguredOutputs.keyAt(j);
        }
        if(k != -1)
            break; /* Loop/switch isn't completed */
        surface = JVM INSTR new #268 <Class IllegalArgumentException>;
        surface.IllegalArgumentException("Surface is not part of this session");
        throw surface;
        surface;
        obj;
        JVM INSTR monitorexit ;
        throw surface;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        mRemoteDevice.prepare2(i, k);
        obj;
        JVM INSTR monitorexit ;
    }

    public void prepare(Surface surface)
        throws CameraAccessException
    {
        if(surface == null)
            throw new IllegalArgumentException("Surface is null");
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        byte byte0;
        int i;
        byte0 = -1;
        i = 0;
_L2:
        int j = byte0;
        if(i < mConfiguredOutputs.size())
        {
            if(!((OutputConfiguration)mConfiguredOutputs.valueAt(i)).getSurfaces().contains(surface))
                break MISSING_BLOCK_LABEL_101;
            j = mConfiguredOutputs.keyAt(i);
        }
        if(j != -1)
            break; /* Loop/switch isn't completed */
        surface = JVM INSTR new #268 <Class IllegalArgumentException>;
        surface.IllegalArgumentException("Surface is not part of this session");
        throw surface;
        surface;
        obj;
        JVM INSTR monitorexit ;
        throw surface;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mRemoteDevice.prepare(j);
        obj;
        JVM INSTR monitorexit ;
    }

    public void setRemoteDevice(ICameraDeviceUser icameradeviceuser)
        throws CameraAccessException
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mInError;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        ICameraDeviceUserWrapper icameradeviceuserwrapper = JVM INSTR new #525 <Class ICameraDeviceUserWrapper>;
        icameradeviceuserwrapper.ICameraDeviceUserWrapper(icameradeviceuser);
        mRemoteDevice = icameradeviceuserwrapper;
        icameradeviceuser = icameradeviceuser.asBinder();
        if(icameradeviceuser == null)
            break MISSING_BLOCK_LABEL_55;
        icameradeviceuser.linkToDeath(this, 0);
        mDeviceHandler.post(mCallOnOpened);
        mDeviceHandler.post(mCallOnUnconfigured);
        obj;
        JVM INSTR monitorexit ;
        return;
        icameradeviceuser;
        mDeviceHandler.post(mCallOnDisconnected);
        icameradeviceuser = JVM INSTR new #412 <Class CameraAccessException>;
        icameradeviceuser.CameraAccessException(2, "The camera device has encountered a serious error");
        throw icameradeviceuser;
        icameradeviceuser;
        obj;
        JVM INSTR monitorexit ;
        throw icameradeviceuser;
    }

    public void setRemoteFailure(ServiceSpecificException servicespecificexception)
    {
        byte byte0;
        boolean flag;
        byte0 = 4;
        flag = true;
        servicespecificexception.errorCode;
        JVM INSTR tableswitch 4 10: default 52
    //                   4 147
    //                   5 52
    //                   6 142
    //                   7 132
    //                   8 137
    //                   9 52
    //                   10 152;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L1 _L6
_L1:
        Log.e(TAG, (new StringBuilder()).append("Unexpected failure in opening camera device: ").append(servicespecificexception.errorCode).append(servicespecificexception.getMessage()).toString());
_L8:
        servicespecificexception = ((ServiceSpecificException) (mInterfaceLock));
        servicespecificexception;
        JVM INSTR monitorenter ;
        mInError = true;
        Handler handler = mDeviceHandler;
        Runnable runnable = JVM INSTR new #26  <Class CameraDeviceImpl$8>;
        runnable.this. _cls8();
        handler.post(runnable);
        servicespecificexception;
        JVM INSTR monitorexit ;
        return;
_L4:
        byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        byte0 = 2;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 3;
        continue; /* Loop/switch isn't completed */
_L2:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 4;
        if(true) goto _L8; else goto _L7
_L7:
        Exception exception;
        exception;
        throw exception;
    }

    public int setRepeatingBurst(List list, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        if(list == null || list.isEmpty())
            throw new IllegalArgumentException("At least one request must be given");
        else
            return submitCaptureRequest(list, capturecallback, handler, true);
    }

    public int setRepeatingRequest(CaptureRequest capturerequest, CaptureCallback capturecallback, Handler handler)
        throws CameraAccessException
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(capturerequest);
        return submitCaptureRequest(arraylist, capturecallback, handler, true);
    }

    public void setSessionListener(StateCallbackKK statecallbackkk)
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        mSessionStateCallback = statecallbackkk;
        obj;
        JVM INSTR monitorexit ;
        return;
        statecallbackkk;
        throw statecallbackkk;
    }

    public void setVendorStreamConfigMode(int i)
    {
        customOpMode = i;
    }

    public void stopRepeating()
        throws CameraAccessException
    {
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkIfCameraClosedOrInError();
        if(mRepeatingRequestId == -1)
            break MISSING_BLOCK_LABEL_44;
        i = mRepeatingRequestId;
        mRepeatingRequestId = -1;
        long l;
        try
        {
            l = mRemoteDevice.cancelRequest(i);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return;
        }
        checkEarlyTriggerSequenceComplete(i, l);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void tearDown(Surface surface)
        throws CameraAccessException
    {
        if(surface == null)
            throw new IllegalArgumentException("Surface is null");
        Object obj = mInterfaceLock;
        obj;
        JVM INSTR monitorenter ;
        byte byte0;
        int i;
        byte0 = -1;
        i = 0;
_L2:
        int j = byte0;
        if(i < mConfiguredOutputs.size())
        {
            if(surface != ((OutputConfiguration)mConfiguredOutputs.valueAt(i)).getSurface())
                break MISSING_BLOCK_LABEL_96;
            j = mConfiguredOutputs.keyAt(i);
        }
        if(j != -1)
            break; /* Loop/switch isn't completed */
        surface = JVM INSTR new #268 <Class IllegalArgumentException>;
        surface.IllegalArgumentException("Surface is not part of this session");
        throw surface;
        surface;
        obj;
        JVM INSTR monitorexit ;
        throw surface;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mRemoteDevice.tearDown(j);
        obj;
        JVM INSTR monitorexit ;
    }

    private static final long NANO_PER_SECOND = 0x3b9aca00L;
    private static final int REQUEST_ID_NONE = -1;
    private final boolean DEBUG = false;
    private final String TAG;
    private int customOpMode;
    private final int mAppTargetSdkVersion;
    private final Runnable mCallOnActive = new Runnable() {

        public void run()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_25;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
                ((StateCallbackKK) (obj1)).onActive(CameraDeviceImpl.this);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
            }
    }
;
    private final Runnable mCallOnBusy = new Runnable() {

        public void run()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_25;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
                ((StateCallbackKK) (obj1)).onBusy(CameraDeviceImpl.this);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
            }
    }
;
    private final Runnable mCallOnClosed = new Runnable() {

        public void run()
        {
            if(mClosedOnce)
                throw new AssertionError("Don't post #onClosed more than once");
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            StateCallbackKK statecallbackkk = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(statecallbackkk != null)
                statecallbackkk.onClosed(CameraDeviceImpl.this);
            CameraDeviceImpl._2D_get6(CameraDeviceImpl.this).onClosed(CameraDeviceImpl.this);
            mClosedOnce = true;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private boolean mClosedOnce;
        final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
                mClosedOnce = false;
            }
    }
;
    private final Runnable mCallOnDisconnected;
    private final Runnable mCallOnIdle = new Runnable() {

        public void run()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_25;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
                ((StateCallbackKK) (obj1)).onIdle(CameraDeviceImpl.this);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
            }
    }
;
    private final Runnable mCallOnOpened = new Runnable() {

        public void run()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_25;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
                ((StateCallbackKK) (obj1)).onOpened(CameraDeviceImpl.this);
            CameraDeviceImpl._2D_get6(CameraDeviceImpl.this).onOpened(CameraDeviceImpl.this);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
            }
    }
;
    private final Runnable mCallOnUnconfigured = new Runnable() {

        public void run()
        {
            Object obj = mInterfaceLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = CameraDeviceImpl._2D_get10(CameraDeviceImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_25;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj1 = CameraDeviceImpl._2D_get12(CameraDeviceImpl.this);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
                ((StateCallbackKK) (obj1)).onUnconfigured(CameraDeviceImpl.this);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final CameraDeviceImpl this$0;

            
            {
                this$0 = CameraDeviceImpl.this;
                super();
            }
    }
;
    private final CameraDeviceCallbacks mCallbacks = new CameraDeviceCallbacks();
    private final String mCameraId;
    private final SparseArray mCaptureCallbackMap = new SparseArray();
    private final CameraCharacteristics mCharacteristics;
    private final AtomicBoolean mClosing = new AtomicBoolean();
    private java.util.AbstractMap.SimpleEntry mConfiguredInput;
    private final SparseArray mConfiguredOutputs = new SparseArray();
    private CameraCaptureSessionCore mCurrentSession;
    private final android.hardware.camera2.CameraDevice.StateCallback mDeviceCallback;
    private final Handler mDeviceHandler;
    private final FrameNumberTracker mFrameNumberTracker = new FrameNumberTracker();
    private boolean mIdle;
    private boolean mInError;
    final Object mInterfaceLock = new Object();
    private boolean mIsPrivilegedApp;
    private int mNextSessionId;
    private ICameraDeviceUserWrapper mRemoteDevice;
    private int mRepeatingRequestId;
    private final List mRequestLastFrameNumbersList = new ArrayList();
    private volatile StateCallbackKK mSessionStateCallback;
    private final int mTotalPartialCount;

    // Unreferenced inner class android/hardware/camera2/impl/CameraDeviceImpl$8

/* anonymous class */
    class _cls8
        implements Runnable
    {

        public void run()
        {
            if(isError)
                CameraDeviceImpl._2D_get6(CameraDeviceImpl.this).onError(CameraDeviceImpl.this, code);
            else
                CameraDeviceImpl._2D_get6(CameraDeviceImpl.this).onDisconnected(CameraDeviceImpl.this);
        }

        final CameraDeviceImpl this$0;
        final int val$code;
        final boolean val$isError;

            
            {
                this$0 = CameraDeviceImpl.this;
                isError = flag;
                code = i;
                super();
            }
    }


    // Unreferenced inner class android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$1

/* anonymous class */
    class CameraDeviceCallbacks._cls1
        implements Runnable
    {

        public void run()
        {
            if(!CameraDeviceImpl._2D_wrap1(_fld0))
                CameraDeviceImpl._2D_get6(_fld0).onError(_fld0, publicErrorCode);
        }

        final CameraDeviceCallbacks this$1;
        final int val$publicErrorCode;

            
            {
                this$1 = final_cameradevicecallbacks;
                publicErrorCode = I.this;
                super();
            }
    }


    // Unreferenced inner class android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$2

/* anonymous class */
    class CameraDeviceCallbacks._cls2
        implements Runnable
    {

        public void run()
        {
            if(!CameraDeviceImpl._2D_wrap1(_fld0))
            {
                int i = resultExtras.getSubsequenceId();
                Object obj = holder.getRequest(i);
                if(holder.hasBatchedOutputs())
                {
                    obj = (Range)((CaptureRequest) (obj)).get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                    for(int j = 0; j < holder.getRequestCount(); j++)
                        holder.getCallback().onCaptureStarted(_fld0, holder.getRequest(j), timestamp - (0x3b9aca00L * (long)(i - j)) / (long)((Integer)((Range) (obj)).getUpper()).intValue(), frameNumber - (long)(i - j));

                } else
                {
                    holder.getCallback().onCaptureStarted(_fld0, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
                }
            }
        }

        final CameraDeviceCallbacks this$1;
        final long val$frameNumber;
        final CaptureCallbackHolder val$holder;
        final CaptureResultExtras val$resultExtras;
        final long val$timestamp;

            
            {
                this$1 = final_cameradevicecallbacks;
                resultExtras = captureresultextras;
                holder = capturecallbackholder;
                timestamp = l;
                frameNumber = J.this;
                super();
            }
    }


    // Unreferenced inner class android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$3

/* anonymous class */
    class CameraDeviceCallbacks._cls3
        implements Runnable
    {

        public void run()
        {
            if(!CameraDeviceImpl._2D_wrap1(_fld0))
                if(holder.hasBatchedOutputs())
                {
                    for(int i = 0; i < holder.getRequestCount(); i++)
                    {
                        CaptureResult captureresult = new CaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(i), resultExtras);
                        holder.getCallback().onCaptureProgressed(_fld0, holder.getRequest(i), captureresult);
                    }

                } else
                {
                    holder.getCallback().onCaptureProgressed(_fld0, request, resultAsCapture);
                }
        }

        final CameraDeviceCallbacks this$1;
        final CaptureCallbackHolder val$holder;
        final CaptureRequest val$request;
        final CaptureResult val$resultAsCapture;
        final CameraMetadataNative val$resultCopy;
        final CaptureResultExtras val$resultExtras;

            
            {
                this$1 = final_cameradevicecallbacks;
                holder = capturecallbackholder;
                resultCopy = camerametadatanative;
                resultExtras = captureresultextras;
                request = capturerequest;
                resultAsCapture = CaptureResult.this;
                super();
            }
    }

}
