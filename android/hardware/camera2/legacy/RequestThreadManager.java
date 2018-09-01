// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.SizeAreaComparator;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.*;
import android.util.*;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.hardware.camera2.legacy:
//            RequestQueue, CameraDeviceState, LegacyFocusStateMapper, LegacyFaceDetectMapper, 
//            CaptureCollector, RequestHandlerThread, LegacyCameraDevice, ParameterUtils, 
//            GLThreadManager, LegacyRequest, RequestHolder, LegacyResultMapper, 
//            BurstHolder, LegacyMetadataMapper

public class RequestThreadManager
{
    private static class ConfigureHolder
    {

        public final ConditionVariable condition;
        public final Collection surfaces;

        public ConfigureHolder(ConditionVariable conditionvariable, Collection collection)
        {
            condition = conditionvariable;
            surfaces = collection;
        }
    }

    public static class FpsCounter
    {

        public double checkFps()
        {
            this;
            JVM INSTR monitorenter ;
            double d = mLastFps;
            this;
            JVM INSTR monitorexit ;
            return d;
            Exception exception;
            exception;
            throw exception;
        }

        public void countAndLog()
        {
            this;
            JVM INSTR monitorenter ;
            countFrame();
            staggeredLog();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void countFrame()
        {
            this;
            JVM INSTR monitorenter ;
            mFrameCount = mFrameCount + 1;
            long l = SystemClock.elapsedRealtimeNanos();
            if(mLastTime == 0L)
                mLastTime = l;
            if(l > mLastTime + 0x3b9aca00L)
            {
                long l1 = mLastTime;
                mLastFps = (double)mFrameCount * (1000000000D / (double)(l - l1));
                mFrameCount = 0;
                mLastTime = l;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void staggeredLog()
        {
            this;
            JVM INSTR monitorenter ;
            if(mLastTime > mLastPrintTime + 0x12a05f200L)
            {
                mLastPrintTime = mLastTime;
                StringBuilder stringbuilder = JVM INSTR new #58  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("FpsCounter", stringbuilder.append("FPS for ").append(mStreamType).append(" stream: ").append(mLastFps).toString());
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static final long NANO_PER_SECOND = 0x3b9aca00L;
        private static final String TAG = "FpsCounter";
        private int mFrameCount;
        private double mLastFps;
        private long mLastPrintTime;
        private long mLastTime;
        private final String mStreamType;

        public FpsCounter(String s)
        {
            mFrameCount = 0;
            mLastTime = 0L;
            mLastPrintTime = 0L;
            mLastFps = 0.0D;
            mStreamType = s;
        }
    }


    static String _2D_get0(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.TAG;
    }

    static List _2D_get1(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mCallbackOutputs;
    }

    static List _2D_get10(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mJpegSurfaceIds;
    }

    static LegacyRequest _2D_get11(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mLastRequest;
    }

    static android.hardware.Camera.Parameters _2D_get12(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mParams;
    }

    static ConditionVariable _2D_get13(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mReceivedJpeg;
    }

    static RequestQueue _2D_get14(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mRequestQueue;
    }

    static RequestHandlerThread _2D_get15(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mRequestThread;
    }

    static Camera _2D_get2(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mCamera;
    }

    static CaptureCollector _2D_get3(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mCaptureCollector;
    }

    static CameraCharacteristics _2D_get4(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mCharacteristics;
    }

    static CameraDeviceState _2D_get5(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mDeviceState;
    }

    static LegacyFaceDetectMapper _2D_get6(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mFaceDetectMapper;
    }

    static LegacyFocusStateMapper _2D_get7(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mFocusStateMapper;
    }

    static GLThreadManager _2D_get8(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mGLThreadManager;
    }

    static Object _2D_get9(RequestThreadManager requestthreadmanager)
    {
        return requestthreadmanager.mIdleLock;
    }

    static Camera _2D_set0(RequestThreadManager requestthreadmanager, Camera camera)
    {
        requestthreadmanager.mCamera = camera;
        return camera;
    }

    static GLThreadManager _2D_set1(RequestThreadManager requestthreadmanager, GLThreadManager glthreadmanager)
    {
        requestthreadmanager.mGLThreadManager = glthreadmanager;
        return glthreadmanager;
    }

    static LegacyRequest _2D_set2(RequestThreadManager requestthreadmanager, LegacyRequest legacyrequest)
    {
        requestthreadmanager.mLastRequest = legacyrequest;
        return legacyrequest;
    }

    static android.hardware.Camera.Parameters _2D_set3(RequestThreadManager requestthreadmanager, android.hardware.Camera.Parameters parameters)
    {
        requestthreadmanager.mParams = parameters;
        return parameters;
    }

    static void _2D_wrap0(RequestThreadManager requestthreadmanager, Collection collection)
    {
        requestthreadmanager.configureOutputs(collection);
    }

    static void _2D_wrap1(RequestThreadManager requestthreadmanager, RequestHolder requestholder)
    {
        requestthreadmanager.doJpegCapturePrepare(requestholder);
    }

    static void _2D_wrap2(RequestThreadManager requestthreadmanager, RequestHolder requestholder)
    {
        requestthreadmanager.doJpegCapture(requestholder);
    }

    static void _2D_wrap3(RequestThreadManager requestthreadmanager, RequestHolder requestholder)
    {
        requestthreadmanager.doPreviewCapture(requestholder);
    }

    static void _2D_wrap4(RequestThreadManager requestthreadmanager, Collection collection)
    {
        requestthreadmanager.resetJpegSurfaceFormats(collection);
    }

    public RequestThreadManager(int i, Camera camera, CameraCharacteristics cameracharacteristics, CameraDeviceState cameradevicestate)
    {
        mPreviewRunning = false;
        mRequestQueue = new RequestQueue(mJpegSurfaceIds);
        mLastRequest = null;
        mCamera = (Camera)Preconditions.checkNotNull(camera, "camera must not be null");
        mCameraId = i;
        mCharacteristics = (CameraCharacteristics)Preconditions.checkNotNull(cameracharacteristics, "characteristics must not be null");
        camera = String.format("RequestThread-%d", new Object[] {
            Integer.valueOf(i)
        });
        TAG = camera;
        mDeviceState = (CameraDeviceState)Preconditions.checkNotNull(cameradevicestate, "deviceState must not be null");
        mFocusStateMapper = new LegacyFocusStateMapper(mCamera);
        mFaceDetectMapper = new LegacyFaceDetectMapper(mCamera, mCharacteristics);
        mCaptureCollector = new CaptureCollector(2, mDeviceState);
        mRequestThread = new RequestHandlerThread(camera, mRequestHandlerCb);
        mCamera.setErrorCallback(mErrorCallback);
    }

    private Size calculatePictureSize(List list, List list1, android.hardware.Camera.Parameters parameters)
    {
        if(list.size() != list1.size())
            throw new IllegalStateException("Input collections must be same length");
        ArrayList arraylist = new ArrayList();
        list1 = list1.iterator();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Surface surface = (Surface)iterator.next();
            list = (Size)list1.next();
            if(LegacyCameraDevice.containsSurfaceId(surface, mJpegSurfaceIds))
                arraylist.add(list);
        } while(true);
        if(!arraylist.isEmpty())
        {
            int i = -1;
            int j = -1;
            list = arraylist.iterator();
            do
            {
                if(!list.hasNext())
                    break;
                list1 = (Size)list.next();
                int k = i;
                if(list1.getWidth() > i)
                    k = list1.getWidth();
                i = k;
                if(list1.getHeight() > j)
                {
                    j = list1.getHeight();
                    i = k;
                }
            } while(true);
            list = new Size(i, j);
            parameters = ParameterUtils.convertSizeList(parameters.getSupportedPictureSizes());
            list1 = new ArrayList();
            parameters = parameters.iterator();
            do
            {
                if(!parameters.hasNext())
                    break;
                Size size = (Size)parameters.next();
                if(size.getWidth() >= i && size.getHeight() >= j)
                    list1.add(size);
            } while(true);
            if(list1.isEmpty())
                throw new AssertionError((new StringBuilder()).append("Could not find any supported JPEG sizes large enough to fit ").append(list).toString());
            list1 = (Size)Collections.min(list1, new SizeAreaComparator());
            if(!list1.equals(list))
                Log.w(TAG, String.format("configureOutputs - Will need to crop picture %s into smallest bound size %s", new Object[] {
                    list1, list
                }));
            return list1;
        } else
        {
            return null;
        }
    }

    private static boolean checkAspectRatiosMatch(Size size, Size size1)
    {
        boolean flag;
        if(Math.abs((float)size.getWidth() / (float)size.getHeight() - (float)size1.getWidth() / (float)size1.getHeight()) < 0.01F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void configureOutputs(Collection collection)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        int i;
        int j;
        try
        {
            stopPreview();
        }
        // Misplaced declaration of an exception variable
        catch(Collection collection)
        {
            Log.e(TAG, "Received device exception in configure call: ", collection);
            mDeviceState.setError(1);
            return;
        }
        try
        {
            mCamera.setPreviewTexture(null);
        }
        catch(IOException ioexception)
        {
            Log.w(TAG, "Failed to clear prior SurfaceTexture, may cause GL deadlock: ", ioexception);
        }
        // Misplaced declaration of an exception variable
        catch(Collection collection)
        {
            Log.e(TAG, "Received device exception in configure call: ", collection);
            mDeviceState.setError(1);
            return;
        }
        if(mGLThreadManager != null)
        {
            mGLThreadManager.waitUntilStarted();
            mGLThreadManager.ignoreNewFrames();
            mGLThreadManager.waitUntilIdle();
        }
        resetJpegSurfaceFormats(mCallbackOutputs);
        for(Iterator iterator = mCallbackOutputs.iterator(); iterator.hasNext();)
        {
            Surface surface1 = (Surface)iterator.next();
            try
            {
                LegacyCameraDevice.disconnectSurface(surface1);
            }
            catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception1)
            {
                Log.w(TAG, "Surface abandoned, skipping...", bufferqueueabandonedexception1);
            }
        }

        mPreviewOutputs.clear();
        mCallbackOutputs.clear();
        mJpegSurfaceIds.clear();
        mPreviewTexture = null;
        arraylist1 = new ArrayList();
        arraylist = new ArrayList();
        i = ((Integer)mCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue();
        j = ((Integer)mCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        if(collection == null) goto _L2; else goto _L1
_L1:
        collection = collection.iterator();
_L3:
        Object obj;
        Object obj2;
        if(!collection.hasNext())
            break; /* Loop/switch isn't completed */
        obj2 = (Pair)collection.next();
        obj = (Surface)((Pair) (obj2)).first;
        obj2 = (Size)((Pair) (obj2)).second;
        int k;
        k = LegacyCameraDevice.detectSurfaceType(((Surface) (obj)));
        LegacyCameraDevice.setSurfaceOrientation(((Surface) (obj)), i, j);
        switch(k)
        {
        default:
            try
            {
                LegacyCameraDevice.setScalingMode(((Surface) (obj)), 1);
                mPreviewOutputs.add(obj);
                arraylist1.add(obj2);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.w(TAG, "Surface abandoned, skipping...", ((Throwable) (obj)));
            }
            if(false)
                ;
            continue; /* Loop/switch isn't completed */

        case 33: // '!'
            break;
        }
        LegacyCameraDevice.setSurfaceFormat(((Surface) (obj)), 1);
        mJpegSurfaceIds.add(Long.valueOf(LegacyCameraDevice.getSurfaceId(((Surface) (obj)))));
        mCallbackOutputs.add(obj);
        arraylist.add(obj2);
        LegacyCameraDevice.connectSurface(((Surface) (obj)));
        if(true) goto _L3; else goto _L2
_L2:
        Object obj1;
        try
        {
            mParams = mCamera.getParameters();
        }
        // Misplaced declaration of an exception variable
        catch(Collection collection)
        {
            Log.e(TAG, "Received device exception: ", collection);
            mDeviceState.setError(1);
            return;
        }
        collection = getPhotoPreviewFpsRange(mParams.getSupportedPreviewFpsRange());
        mParams.setPreviewFpsRange(collection[0], collection[1]);
        obj1 = calculatePictureSize(mCallbackOutputs, arraylist, mParams);
        if(arraylist1.size() > 0)
        {
            Size size = SizeAreaComparator.findLargestByArea(arraylist1);
            collection = ParameterUtils.getLargestSupportedJpegSizeByArea(mParams);
            if(obj1 != null)
                collection = ((Collection) (obj1));
            List list = ParameterUtils.convertSizeList(mParams.getSupportedPreviewSizes());
            long l = size.getHeight();
            long l1 = size.getWidth();
            size = SizeAreaComparator.findLargestByArea(list);
            Iterator iterator2 = list.iterator();
            do
            {
                if(!iterator2.hasNext())
                    break;
                Size size1 = (Size)iterator2.next();
                long l2 = size1.getWidth() * size1.getHeight();
                long l3 = size.getWidth() * size.getHeight();
                if(checkAspectRatiosMatch(collection, size1) && l2 < l3 && l2 >= l * l1)
                    size = size1;
            } while(true);
            mIntermediateBufferSize = size;
            mParams.setPreviewSize(mIntermediateBufferSize.getWidth(), mIntermediateBufferSize.getHeight());
        } else
        {
            mIntermediateBufferSize = null;
        }
        if(obj1 != null)
        {
            Log.i(TAG, (new StringBuilder()).append("configureOutputs - set take picture size to ").append(obj1).toString());
            mParams.setPictureSize(((Size) (obj1)).getWidth(), ((Size) (obj1)).getHeight());
        }
        if(mGLThreadManager == null)
        {
            mGLThreadManager = new GLThreadManager(mCameraId, i, mDeviceState);
            mGLThreadManager.start();
        }
        mGLThreadManager.waitUntilStarted();
        collection = new ArrayList();
        obj1 = arraylist1.iterator();
        for(Iterator iterator1 = mPreviewOutputs.iterator(); iterator1.hasNext(); collection.add(new Pair((Surface)iterator1.next(), (Size)((Iterator) (obj1)).next())));
        mGLThreadManager.setConfigurationAndWait(collection, mCaptureCollector);
        for(collection = mPreviewOutputs.iterator(); collection.hasNext();)
        {
            Surface surface = (Surface)collection.next();
            try
            {
                LegacyCameraDevice.setSurfaceOrientation(surface, i, j);
            }
            catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception)
            {
                Log.e(TAG, "Surface abandoned, skipping setSurfaceOrientation()", bufferqueueabandonedexception);
            }
        }

        mGLThreadManager.allowNewFrames();
        mPreviewTexture = mGLThreadManager.getCurrentSurfaceTexture();
        if(mPreviewTexture != null)
            mPreviewTexture.setOnFrameAvailableListener(mPreviewCallback);
        mCamera.setParameters(mParams);
_L4:
        return;
        collection;
        Log.e(TAG, "Received device exception while configuring: ", collection);
        mDeviceState.setError(1);
          goto _L4
    }

    private void createDummySurface()
    {
        if(mDummyTexture == null || mDummySurface == null)
        {
            mDummyTexture = new SurfaceTexture(0);
            mDummyTexture.setDefaultBufferSize(640, 480);
            mDummySurface = new Surface(mDummyTexture);
        }
    }

    private void doJpegCapture(RequestHolder requestholder)
    {
        mCamera.takePicture(mJpegShutterCallback, null, mJpegCallback);
        mPreviewRunning = false;
    }

    private void doJpegCapturePrepare(RequestHolder requestholder)
        throws IOException
    {
        if(!mPreviewRunning)
        {
            createDummySurface();
            mCamera.setPreviewTexture(mDummyTexture);
            startPreview();
        }
    }

    private void doPreviewCapture(RequestHolder requestholder)
        throws IOException
    {
        if(mPreviewRunning)
            return;
        if(mPreviewTexture == null)
        {
            throw new IllegalStateException("Preview capture called with no preview surfaces configured.");
        } else
        {
            mPreviewTexture.setDefaultBufferSize(mIntermediateBufferSize.getWidth(), mIntermediateBufferSize.getHeight());
            mCamera.setPreviewTexture(mPreviewTexture);
            startPreview();
            return;
        }
    }

    private int[] getPhotoPreviewFpsRange(List list)
    {
        if(list.size() == 0)
        {
            Log.e(TAG, "No supported frame rates returned!");
            return null;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            int k1;
            int l1;
            int i2;
label0:
            {
                int ai[] = (int[])iterator.next();
                int i1 = ai[0];
                int j1 = ai[1];
                if(j1 <= j)
                {
                    k1 = k;
                    l1 = j;
                    i2 = i;
                    if(j1 != j)
                        break label0;
                    k1 = k;
                    l1 = j;
                    i2 = i;
                    if(i1 <= i)
                        break label0;
                }
                i2 = i1;
                l1 = j1;
                k1 = l;
            }
            l++;
            k = k1;
            j = l1;
            i = i2;
        }

        return (int[])list.get(k);
    }

    private void resetJpegSurfaceFormats(Collection collection)
    {
        if(collection == null)
            return;
        for(collection = collection.iterator(); collection.hasNext();)
        {
            Surface surface = (Surface)collection.next();
            if(surface == null || surface.isValid() ^ true)
                Log.w(TAG, "Jpeg surface is invalid, skipping...");
            else
                try
                {
                    LegacyCameraDevice.setSurfaceFormat(surface, 33);
                }
                catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception)
                {
                    Log.w(TAG, "Surface abandoned, skipping...", bufferqueueabandonedexception);
                }
        }

    }

    private void startPreview()
    {
        if(!mPreviewRunning)
        {
            mCamera.startPreview();
            mPreviewRunning = true;
        }
    }

    private void stopPreview()
    {
        if(mPreviewRunning)
        {
            mCamera.stopPreview();
            mPreviewRunning = false;
        }
    }

    public long cancelRepeating(int i)
    {
        return mRequestQueue.stopRepeating(i);
    }

    public void configure(Collection collection)
    {
        Handler handler = mRequestThread.waitAndGetHandler();
        ConditionVariable conditionvariable = new ConditionVariable(false);
        handler.sendMessage(handler.obtainMessage(1, 0, 0, new ConfigureHolder(conditionvariable, collection)));
        conditionvariable.block();
    }

    public long flush()
    {
        Log.i(TAG, "Flushing all pending requests.");
        long l = mRequestQueue.stopRepeating();
        mCaptureCollector.failAll();
        return l;
    }

    public void quit()
    {
        if(mQuit.getAndSet(true))
            break MISSING_BLOCK_LABEL_44;
        Handler handler = mRequestThread.waitAndGetHandler();
        handler.sendMessageAtFrontOfQueue(handler.obtainMessage(3));
        mRequestThread.quitSafely();
        mRequestThread.join();
_L1:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        Log.e(TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] {
            mRequestThread.getName(), Long.valueOf(mRequestThread.getId())
        }));
          goto _L1
    }

    public void start()
    {
        mRequestThread.start();
    }

    public SubmitInfo submitCaptureRequests(CaptureRequest acapturerequest[], boolean flag)
    {
        Handler handler = mRequestThread.waitAndGetHandler();
        Object obj = mIdleLock;
        obj;
        JVM INSTR monitorenter ;
        acapturerequest = mRequestQueue.submit(acapturerequest, flag);
        handler.sendEmptyMessage(2);
        obj;
        JVM INSTR monitorexit ;
        return acapturerequest;
        acapturerequest;
        throw acapturerequest;
    }

    private static final float ASPECT_RATIO_TOLERANCE = 0.01F;
    private static final boolean DEBUG = false;
    private static final int JPEG_FRAME_TIMEOUT = 4000;
    private static final int MAX_IN_FLIGHT_REQUESTS = 2;
    private static final int MSG_CLEANUP = 3;
    private static final int MSG_CONFIGURE_OUTPUTS = 1;
    private static final int MSG_SUBMIT_CAPTURE_REQUEST = 2;
    private static final int PREVIEW_FRAME_TIMEOUT = 1000;
    private static final int REQUEST_COMPLETE_TIMEOUT = 4000;
    private static final boolean USE_BLOB_FORMAT_OVERRIDE = true;
    private static final boolean VERBOSE = false;
    private final String TAG;
    private final List mCallbackOutputs = new ArrayList();
    private Camera mCamera;
    private final int mCameraId;
    private final CaptureCollector mCaptureCollector;
    private final CameraCharacteristics mCharacteristics;
    private final CameraDeviceState mDeviceState;
    private Surface mDummySurface;
    private SurfaceTexture mDummyTexture;
    private final android.hardware.Camera.ErrorCallback mErrorCallback = new android.hardware.Camera.ErrorCallback() {

        public void onError(int j, Camera camera1)
        {
            j;
            JVM INSTR tableswitch 2 2: default 20
        //                       2 67;
               goto _L1 _L2
_L1:
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), (new StringBuilder()).append("Received error ").append(j).append(" from the Camera1 ErrorCallback").toString());
            RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
_L4:
            return;
_L2:
            flush();
            RequestThreadManager._2D_get5(RequestThreadManager.this).setError(0);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final RequestThreadManager this$0;

            
            {
                this$0 = RequestThreadManager.this;
                super();
            }
    }
;
    private final LegacyFaceDetectMapper mFaceDetectMapper;
    private final LegacyFocusStateMapper mFocusStateMapper;
    private GLThreadManager mGLThreadManager;
    private final Object mIdleLock = new Object();
    private Size mIntermediateBufferSize;
    private final android.hardware.Camera.PictureCallback mJpegCallback = new android.hardware.Camera.PictureCallback() {

        public void onPictureTaken(byte abyte0[], Camera camera1)
        {
            Log.i(RequestThreadManager._2D_get0(RequestThreadManager.this), "Received jpeg.");
            Pair pair = RequestThreadManager._2D_get3(RequestThreadManager.this).jpegProduced();
            if(pair == null || pair.first == null)
            {
                Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Dropping jpeg frame.");
                return;
            }
            camera1 = (RequestHolder)pair.first;
            long l = ((Long)pair.second).longValue();
            camera1 = camera1.getHolderTargets().iterator();
            do
            {
                if(!camera1.hasNext())
                    break;
                Surface surface = (Surface)camera1.next();
                try
                {
                    if(LegacyCameraDevice.containsSurfaceId(surface, RequestThreadManager._2D_get10(RequestThreadManager.this)))
                    {
                        Log.i(RequestThreadManager._2D_get0(RequestThreadManager.this), "Producing jpeg buffer...");
                        int j = abyte0.length;
                        int k = LegacyCameraDevice.nativeGetJpegFooterSize();
                        LegacyCameraDevice.setNextTimestamp(surface, l);
                        LegacyCameraDevice.setSurfaceFormat(surface, 1);
                        j = (int)Math.ceil(Math.sqrt(j + k + 3 & -4)) + 15 & 0xfffffff0;
                        LegacyCameraDevice.setSurfaceDimens(surface, j, j);
                        LegacyCameraDevice.produceFrame(surface, abyte0, j, j, 33);
                    }
                }
                catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception)
                {
                    Log.w(RequestThreadManager._2D_get0(RequestThreadManager.this), "Surface abandoned, dropping frame. ", bufferqueueabandonedexception);
                }
            } while(true);
            RequestThreadManager._2D_get13(RequestThreadManager.this).open();
        }

        final RequestThreadManager this$0;

            
            {
                this$0 = RequestThreadManager.this;
                super();
            }
    }
;
    private final android.hardware.Camera.ShutterCallback mJpegShutterCallback = new android.hardware.Camera.ShutterCallback() {

        public void onShutter()
        {
            RequestThreadManager._2D_get3(RequestThreadManager.this).jpegCaptured(SystemClock.elapsedRealtimeNanos());
        }

        final RequestThreadManager this$0;

            
            {
                this$0 = RequestThreadManager.this;
                super();
            }
    }
;
    private final List mJpegSurfaceIds = new ArrayList();
    private LegacyRequest mLastRequest;
    private android.hardware.Camera.Parameters mParams;
    private final FpsCounter mPrevCounter = new FpsCounter("Incoming Preview");
    private final android.graphics.SurfaceTexture.OnFrameAvailableListener mPreviewCallback = new android.graphics.SurfaceTexture.OnFrameAvailableListener() {

        public void onFrameAvailable(SurfaceTexture surfacetexture)
        {
            RequestThreadManager._2D_get8(RequestThreadManager.this).queueNewFrame();
        }

        final RequestThreadManager this$0;

            
            {
                this$0 = RequestThreadManager.this;
                super();
            }
    }
;
    private final List mPreviewOutputs = new ArrayList();
    private boolean mPreviewRunning;
    private SurfaceTexture mPreviewTexture;
    private final AtomicBoolean mQuit = new AtomicBoolean(false);
    private final ConditionVariable mReceivedJpeg = new ConditionVariable(false);
    private final FpsCounter mRequestCounter = new FpsCounter("Incoming Requests");
    private final android.os.Handler.Callback mRequestHandlerCb = new android.os.Handler.Callback() {

        public boolean handleMessage(Message message)
        {
            if(mCleanup)
                return true;
            message.what;
            JVM INSTR tableswitch -1 3: default 48
        //                       -1 203
        //                       0 48
        //                       1 83
        //                       2 238
        //                       3 1265;
               goto _L1 _L2 _L1 _L3 _L4 _L5
_L2:
            break MISSING_BLOCK_LABEL_203;
_L1:
            throw new AssertionError((new StringBuilder()).append("Unhandled message ").append(message.what).append(" on RequestThread.").toString());
_L3:
            message = (ConfigureHolder)message.obj;
            int j;
            if(((ConfigureHolder) (message)).surfaces != null)
                j = ((ConfigureHolder) (message)).surfaces.size();
            else
                j = 0;
            Log.i(RequestThreadManager._2D_get0(RequestThreadManager.this), (new StringBuilder()).append("Configure outputs: ").append(j).append(" surfaces configured.").toString());
            try
            {
                if(!RequestThreadManager._2D_get3(RequestThreadManager.this).waitForEmpty(4000L, TimeUnit.MILLISECONDS))
                {
                    Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Timed out while queueing configure request.");
                    RequestThreadManager._2D_get3(RequestThreadManager.this).failAll();
                }
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Interrupted while waiting for requests to complete.");
                RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
                continue; /* Loop/switch isn't completed */
            }
            RequestThreadManager._2D_wrap0(RequestThreadManager.this, ((ConfigureHolder) (message)).surfaces);
            ((ConfigureHolder) (message)).condition.open();
_L23:
            return true;
_L4:
            Object obj;
            boolean flag1;
            Object obj1;
            obj = RequestThreadManager._2D_get15(RequestThreadManager.this).getHandler();
            flag1 = false;
            obj1 = RequestThreadManager._2D_get14(RequestThreadManager.this).getNext();
            message = ((Message) (obj1));
            if(obj1 != null) goto _L7; else goto _L6
_L6:
            try
            {
                if(!RequestThreadManager._2D_get3(RequestThreadManager.this).waitForEmpty(4000L, TimeUnit.MILLISECONDS))
                {
                    Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Timed out while waiting for prior requests to complete.");
                    RequestThreadManager._2D_get3(RequestThreadManager.this).failAll();
                }
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Interrupted while waiting for requests to complete: ", message);
                RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
                continue; /* Loop/switch isn't completed */
            }
            obj1 = RequestThreadManager._2D_get9(RequestThreadManager.this);
            obj1;
            JVM INSTR monitorenter ;
            message = RequestThreadManager._2D_get14(RequestThreadManager.this).getNext();
            if(message != null) goto _L9; else goto _L8
_L8:
            RequestThreadManager._2D_get5(RequestThreadManager.this).setIdle();
            obj1;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
_L9:
            obj1;
            JVM INSTR monitorexit ;
_L7:
            if(message != null)
            {
                ((Handler) (obj)).sendEmptyMessage(2);
                if(message.isQueueEmpty())
                    RequestThreadManager._2D_get5(RequestThreadManager.this).setRequestQueueEmpty();
            }
            obj1 = message.getBurstHolder();
            obj = ((BurstHolder) (obj1)).produceRequestHolders(message.getFrameNumber().longValue()).iterator();
_L16:
            if(!((Iterator) (obj)).hasNext()) goto _L11; else goto _L10
_L10:
            boolean flag;
            Object obj2;
            Object obj3;
            message = (RequestHolder)((Iterator) (obj)).next();
            obj2 = message.getRequest();
            flag = false;
            boolean flag2 = false;
            if(RequestThreadManager._2D_get11(RequestThreadManager.this) != null && RequestThreadManager._2D_get11(RequestThreadManager.this).captureRequest == obj2)
                break MISSING_BLOCK_LABEL_604;
            obj3 = ParameterUtils.convertSize(RequestThreadManager._2D_get12(RequestThreadManager.this).getPreviewSize());
            obj3 = new LegacyRequest(RequestThreadManager._2D_get4(RequestThreadManager.this), ((CaptureRequest) (obj2)), ((Size) (obj3)), RequestThreadManager._2D_get12(RequestThreadManager.this));
            LegacyMetadataMapper.convertRequestMetadata(((LegacyRequest) (obj3)));
            flag = flag2;
            if(RequestThreadManager._2D_get12(RequestThreadManager.this).same(((LegacyRequest) (obj3)).parameters)) goto _L13; else goto _L12
_L12:
            RequestThreadManager._2D_get2(RequestThreadManager.this).setParameters(((LegacyRequest) (obj3)).parameters);
            flag = true;
            RequestThreadManager._2D_set3(RequestThreadManager.this, ((LegacyRequest) (obj3)).parameters);
_L13:
            RequestThreadManager._2D_set2(RequestThreadManager.this, ((LegacyRequest) (obj3)));
            if(RequestThreadManager._2D_get3(RequestThreadManager.this).queueRequest(message, RequestThreadManager._2D_get11(RequestThreadManager.this), 4000L, TimeUnit.MILLISECONDS)) goto _L15; else goto _L14
_L14:
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Timed out while queueing capture request.");
            message.failRequest();
            RequestThreadManager._2D_get5(RequestThreadManager.this).setCaptureStart(message, 0L, 3);
              goto _L16
            message;
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Received device exception during capture call: ", message);
            RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
_L11:
            if(flag1 && ((BurstHolder) (obj1)).isRepeating())
            {
                long l = cancelRepeating(((BurstHolder) (obj1)).getRequestId());
                RequestThreadManager._2D_get5(RequestThreadManager.this).setRepeatingRequestError(l, ((BurstHolder) (obj1)).getRequestId());
            }
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
            obj2;
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Exception while setting camera parameters: ", ((Throwable) (obj2)));
            message.failRequest();
            RequestThreadManager._2D_get5(RequestThreadManager.this).setCaptureStart(message, 0L, 3);
              goto _L16
_L15:
            if(message.hasPreviewTargets())
                RequestThreadManager._2D_wrap3(RequestThreadManager.this, message);
            if(!message.hasJpegTargets()) goto _L18; else goto _L17
_L17:
            for(; !RequestThreadManager._2D_get3(RequestThreadManager.this).waitForPreviewsEmpty(1000L, TimeUnit.MILLISECONDS); RequestThreadManager._2D_get3(RequestThreadManager.this).failNextPreview())
                Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Timed out while waiting for preview requests to complete.");

              goto _L19
            message;
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Interrupted during capture: ", message);
            RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
              goto _L11
_L19:
            RequestThreadManager._2D_get13(RequestThreadManager.this).close();
            RequestThreadManager._2D_wrap1(RequestThreadManager.this, message);
_L18:
            RequestThreadManager._2D_get6(RequestThreadManager.this).processFaceDetectMode(((CaptureRequest) (obj2)), RequestThreadManager._2D_get12(RequestThreadManager.this));
            RequestThreadManager._2D_get7(RequestThreadManager.this).processRequestTriggers(((CaptureRequest) (obj2)), RequestThreadManager._2D_get12(RequestThreadManager.this));
            if(message.hasJpegTargets())
            {
                RequestThreadManager._2D_wrap2(RequestThreadManager.this, message);
                if(!RequestThreadManager._2D_get13(RequestThreadManager.this).block(4000L))
                {
                    Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Hit timeout for jpeg callback!");
                    RequestThreadManager._2D_get3(RequestThreadManager.this).failNextJpeg();
                }
            }
            if(!flag) goto _L21; else goto _L20
_L20:
            RequestThreadManager._2D_set3(RequestThreadManager.this, RequestThreadManager._2D_get2(RequestThreadManager.this).getParameters());
            RequestThreadManager._2D_get11(RequestThreadManager.this).setParameters(RequestThreadManager._2D_get12(RequestThreadManager.this));
_L21:
            obj2 = new MutableLong(0L);
            if(!RequestThreadManager._2D_get3(RequestThreadManager.this).waitForRequestCompleted(message, 4000L, TimeUnit.MILLISECONDS, ((MutableLong) (obj2))))
            {
                Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Timed out while waiting for request to complete.");
                RequestThreadManager._2D_get3(RequestThreadManager.this).failAll();
            }
            obj2 = mMapper.cachedConvertResultMetadata(RequestThreadManager._2D_get11(RequestThreadManager.this), ((MutableLong) (obj2)).value);
            RequestThreadManager._2D_get7(RequestThreadManager.this).mapResultTriggers(((android.hardware.camera2.impl.CameraMetadataNative) (obj2)));
            RequestThreadManager._2D_get6(RequestThreadManager.this).mapResultFaces(((android.hardware.camera2.impl.CameraMetadataNative) (obj2)), RequestThreadManager._2D_get11(RequestThreadManager.this));
            if(!message.requestFailed())
                RequestThreadManager._2D_get5(RequestThreadManager.this).setCaptureResult(message, ((android.hardware.camera2.impl.CameraMetadataNative) (obj2)));
            if(message.isOutputAbandoned())
                flag1 = true;
              goto _L16
            message;
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Received device exception during capture call: ", message);
            RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
              goto _L11
            message;
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Received device exception: ", message);
            RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
              goto _L11
            message;
            Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Interrupted waiting for request completion: ", message);
            RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
              goto _L11
_L5:
            mCleanup = true;
            try
            {
                if(!RequestThreadManager._2D_get3(RequestThreadManager.this).waitForEmpty(4000L, TimeUnit.MILLISECONDS))
                {
                    Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Timed out while queueing cleanup request.");
                    RequestThreadManager._2D_get3(RequestThreadManager.this).failAll();
                }
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e(RequestThreadManager._2D_get0(RequestThreadManager.this), "Interrupted while waiting for requests to complete: ", message);
                RequestThreadManager._2D_get5(RequestThreadManager.this).setError(1);
            }
            if(RequestThreadManager._2D_get8(RequestThreadManager.this) != null)
            {
                RequestThreadManager._2D_get8(RequestThreadManager.this).quit();
                RequestThreadManager._2D_set1(RequestThreadManager.this, null);
            }
            if(RequestThreadManager._2D_get2(RequestThreadManager.this) != null)
            {
                RequestThreadManager._2D_get2(RequestThreadManager.this).release();
                RequestThreadManager._2D_set0(RequestThreadManager.this, null);
            }
            RequestThreadManager._2D_wrap4(RequestThreadManager.this, RequestThreadManager._2D_get1(RequestThreadManager.this));
            if(true) goto _L23; else goto _L22
_L22:
        }

        private boolean mCleanup;
        private final LegacyResultMapper mMapper = new LegacyResultMapper();
        final RequestThreadManager this$0;

            
            {
                this$0 = RequestThreadManager.this;
                super();
                mCleanup = false;
            }
    }
;
    private final RequestQueue mRequestQueue;
    private final RequestHandlerThread mRequestThread;
}
