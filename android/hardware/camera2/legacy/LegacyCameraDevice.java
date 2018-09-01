// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.ArrayUtils;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.*;
import android.util.*;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.hardware.camera2.legacy:
//            CameraDeviceState, RequestThreadManager, LegacyExceptionUtils, RequestHolder

public class LegacyCameraDevice
    implements AutoCloseable
{

    static ICameraDeviceCallbacks _2D_get0(LegacyCameraDevice legacycameradevice)
    {
        return legacycameradevice.mDeviceCallbacks;
    }

    static ConditionVariable _2D_get1(LegacyCameraDevice legacycameradevice)
    {
        return legacycameradevice.mIdle;
    }

    static Handler _2D_get2(LegacyCameraDevice legacycameradevice)
    {
        return legacycameradevice.mResultHandler;
    }

    static CaptureResultExtras _2D_wrap0(LegacyCameraDevice legacycameradevice, RequestHolder requestholder)
    {
        return legacycameradevice.getExtrasFromRequest(requestholder);
    }

    static CaptureResultExtras _2D_wrap1(LegacyCameraDevice legacycameradevice, RequestHolder requestholder, int i, Object obj)
    {
        return legacycameradevice.getExtrasFromRequest(requestholder, i, obj);
    }

    public LegacyCameraDevice(int i, Camera camera, CameraCharacteristics cameracharacteristics, ICameraDeviceCallbacks icameradevicecallbacks)
    {
        mClosed = false;
        mCameraId = i;
        mDeviceCallbacks = icameradevicecallbacks;
        TAG = String.format("CameraDevice-%d-LE", new Object[] {
            Integer.valueOf(mCameraId)
        });
        mResultThread.start();
        mResultHandler = new Handler(mResultThread.getLooper());
        mCallbackHandlerThread.start();
        mCallbackHandler = new Handler(mCallbackHandlerThread.getLooper());
        mDeviceState.setCameraDeviceCallbacks(mCallbackHandler, mStateListener);
        mStaticCharacteristics = cameracharacteristics;
        mRequestThreadManager = new RequestThreadManager(i, camera, cameracharacteristics, mDeviceState);
        mRequestThreadManager.start();
    }

    static void connectSurface(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        LegacyExceptionUtils.throwOnError(nativeConnectSurface(surface));
    }

    static boolean containsSurfaceId(Surface surface, Collection collection)
    {
        long l;
        try
        {
            l = getSurfaceId(surface);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            return false;
        }
        return collection.contains(Long.valueOf(l));
    }

    public static int detectSurfaceDataspace(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        return LegacyExceptionUtils.throwOnError(nativeDetectSurfaceDataspace(surface));
    }

    public static int detectSurfaceType(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        int i = nativeDetectSurfaceType(surface);
        int j = i;
        if(i >= 1)
        {
            j = i;
            if(i <= 5)
                j = 34;
        }
        return LegacyExceptionUtils.throwOnError(j);
    }

    static int detectSurfaceUsageFlags(Surface surface)
    {
        Preconditions.checkNotNull(surface);
        return nativeDetectSurfaceUsageFlags(surface);
    }

    static void disconnectSurface(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        if(surface == null)
        {
            return;
        } else
        {
            LegacyExceptionUtils.throwOnError(nativeDisconnectSurface(surface));
            return;
        }
    }

    static Size findClosestSize(Size size, Size asize[])
    {
        if(size == null || asize == null)
            return null;
        Size size1 = null;
        int i = 0;
        for(int j = asize.length; i < j;)
        {
            Size size3;
label0:
            {
                Size size2 = asize[i];
                if(size2.equals(size))
                    return size;
                size3 = size1;
                if(size2.getWidth() > 1920)
                    break label0;
                if(size1 != null)
                {
                    size3 = size1;
                    if(findEuclidDistSquare(size, size2) >= findEuclidDistSquare(size1, size2))
                        break label0;
                }
                size3 = size2;
            }
            i++;
            size1 = size3;
        }

        return size1;
    }

    static long findEuclidDistSquare(Size size, Size size1)
    {
        long l = size.getWidth() - size1.getWidth();
        long l1 = size.getHeight() - size1.getHeight();
        return l * l + l1 * l1;
    }

    private CaptureResultExtras getExtrasFromRequest(RequestHolder requestholder)
    {
        return getExtrasFromRequest(requestholder, -1, null);
    }

    private CaptureResultExtras getExtrasFromRequest(RequestHolder requestholder, int i, Object obj)
    {
        byte byte0 = -1;
        int j = byte0;
        if(i == 5)
        {
            obj = (Surface)obj;
            i = mConfiguredSurfaces.indexOfValue(obj);
            if(i < 0)
            {
                Log.e(TAG, "Buffer drop error reported for unknown Surface");
                j = byte0;
            } else
            {
                j = mConfiguredSurfaces.keyAt(i);
            }
        }
        if(requestholder == null)
            return new CaptureResultExtras(-1, -1, -1, -1, -1L, -1, -1);
        else
            return new CaptureResultExtras(requestholder.getRequestId(), requestholder.getSubsequeceId(), 0, 0, requestholder.getFrameNumber(), 1, j);
    }

    static long getSurfaceId(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        long l;
        try
        {
            l = nativeGetSurfaceId(surface);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            throw new LegacyExceptionUtils.BufferQueueAbandonedException();
        }
        return l;
    }

    static List getSurfaceIds(SparseArray sparsearray)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        if(sparsearray == null)
            throw new NullPointerException("Null argument surfaces");
        ArrayList arraylist = new ArrayList();
        int i = sparsearray.size();
        for(int j = 0; j < i; j++)
        {
            long l = getSurfaceId((Surface)sparsearray.valueAt(j));
            if(l == 0L)
                throw new IllegalStateException("Configured surface had null native GraphicBufferProducer pointer!");
            arraylist.add(Long.valueOf(l));
        }

        return arraylist;
    }

    static List getSurfaceIds(Collection collection)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        if(collection == null)
            throw new NullPointerException("Null argument surfaces");
        ArrayList arraylist = new ArrayList();
        long l;
        for(collection = collection.iterator(); collection.hasNext(); arraylist.add(Long.valueOf(l)))
        {
            l = getSurfaceId((Surface)collection.next());
            if(l == 0L)
                throw new IllegalStateException("Configured surface had null native GraphicBufferProducer pointer!");
        }

        return arraylist;
    }

    public static Size getSurfaceSize(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        int ai[] = new int[2];
        LegacyExceptionUtils.throwOnError(nativeDetectSurfaceDimens(surface, ai));
        return new Size(ai[0], ai[1]);
    }

    static Size getTextureSize(SurfaceTexture surfacetexture)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surfacetexture);
        int ai[] = new int[2];
        LegacyExceptionUtils.throwOnError(nativeDetectTextureDimens(surfacetexture, ai));
        return new Size(ai[0], ai[1]);
    }

    public static boolean isFlexibleConsumer(Surface surface)
    {
        int i = detectSurfaceUsageFlags(surface);
        boolean flag;
        if((0x110000 & i) == 0)
        {
            if((i & 0x903) != 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public static boolean isPreviewConsumer(Surface surface)
    {
        int i = detectSurfaceUsageFlags(surface);
        boolean flag;
        if((0x110003 & i) == 0)
        {
            if((i & 0xb00) != 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        try
        {
            detectSurfaceType(surface);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            throw new IllegalArgumentException("Surface was abandoned", surface);
        }
        return flag;
    }

    public static boolean isVideoEncoderConsumer(Surface surface)
    {
        int i = detectSurfaceUsageFlags(surface);
        boolean flag;
        if((0x100903 & i) == 0)
        {
            if((0x10000 & i) != 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        try
        {
            detectSurfaceType(surface);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            throw new IllegalArgumentException("Surface was abandoned", surface);
        }
        return flag;
    }

    private static native int nativeConnectSurface(Surface surface);

    private static native int nativeDetectSurfaceDataspace(Surface surface);

    private static native int nativeDetectSurfaceDimens(Surface surface, int ai[]);

    private static native int nativeDetectSurfaceType(Surface surface);

    private static native int nativeDetectSurfaceUsageFlags(Surface surface);

    private static native int nativeDetectTextureDimens(SurfaceTexture surfacetexture, int ai[]);

    private static native int nativeDisconnectSurface(Surface surface);

    static native int nativeGetJpegFooterSize();

    private static native long nativeGetSurfaceId(Surface surface);

    private static native int nativeProduceFrame(Surface surface, byte abyte0[], int i, int j, int k);

    private static native int nativeSetNextTimestamp(Surface surface, long l);

    private static native int nativeSetScalingMode(Surface surface, int i);

    private static native int nativeSetSurfaceDimens(Surface surface, int i, int j);

    private static native int nativeSetSurfaceFormat(Surface surface, int i);

    private static native int nativeSetSurfaceOrientation(Surface surface, int i, int j);

    static boolean needsConversion(Surface surface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        boolean flag;
        int i;
        boolean flag1;
        flag = true;
        i = detectSurfaceType(surface);
        flag1 = flag;
        if(i == 35) goto _L2; else goto _L1
_L1:
        if(i != 0x32315659) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 17)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    static void produceFrame(Surface surface, byte abyte0[], int i, int j, int k)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        Preconditions.checkNotNull(abyte0);
        Preconditions.checkArgumentPositive(i, "width must be positive.");
        Preconditions.checkArgumentPositive(j, "height must be positive.");
        LegacyExceptionUtils.throwOnError(nativeProduceFrame(surface, abyte0, i, j, k));
    }

    static void setNextTimestamp(Surface surface, long l)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        LegacyExceptionUtils.throwOnError(nativeSetNextTimestamp(surface, l));
    }

    static void setScalingMode(Surface surface, int i)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        LegacyExceptionUtils.throwOnError(nativeSetScalingMode(surface, i));
    }

    static void setSurfaceDimens(Surface surface, int i, int j)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        Preconditions.checkArgumentPositive(i, "width must be positive.");
        Preconditions.checkArgumentPositive(j, "height must be positive.");
        LegacyExceptionUtils.throwOnError(nativeSetSurfaceDimens(surface, i, j));
    }

    static void setSurfaceFormat(Surface surface, int i)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        LegacyExceptionUtils.throwOnError(nativeSetSurfaceFormat(surface, i));
    }

    static void setSurfaceOrientation(Surface surface, int i, int j)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        Preconditions.checkNotNull(surface);
        LegacyExceptionUtils.throwOnError(nativeSetSurfaceOrientation(surface, i, j));
    }

    public long cancelRequest(int i)
    {
        return mRequestThreadManager.cancelRepeating(i);
    }

    public void close()
    {
        mRequestThreadManager.quit();
        mCallbackHandlerThread.quitSafely();
        mResultThread.quitSafely();
        try
        {
            mCallbackHandlerThread.join();
        }
        catch(InterruptedException interruptedexception)
        {
            Log.e(TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] {
                mCallbackHandlerThread.getName(), Long.valueOf(mCallbackHandlerThread.getId())
            }));
        }
        try
        {
            mResultThread.join();
        }
        catch(InterruptedException interruptedexception1)
        {
            Log.e(TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] {
                mResultThread.getName(), Long.valueOf(mResultThread.getId())
            }));
        }
        mClosed = true;
    }

    public int configureOutputs(SparseArray sparsearray)
    {
        ArrayList arraylist = new ArrayList();
        if(sparsearray == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = sparsearray.size();
        j = 0;
_L19:
        if(j >= i) goto _L2; else goto _L3
_L3:
        Surface surface = (Surface)sparsearray.valueAt(j);
        if(surface == null)
        {
            Log.e(TAG, "configureOutputs - null outputs are not allowed");
            return LegacyExceptionUtils.BAD_VALUE;
        }
        if(!surface.isValid())
        {
            Log.e(TAG, "configureOutputs - invalid output surfaces are not allowed");
            return LegacyExceptionUtils.BAD_VALUE;
        }
        StreamConfigurationMap streamconfigurationmap = (StreamConfigurationMap)mStaticCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        Size size;
        int k;
        boolean flag;
        Object obj;
        Object obj1;
        try
        {
            size = getSurfaceSize(surface);
            k = detectSurfaceType(surface);
            flag = isFlexibleConsumer(surface);
            obj = streamconfigurationmap.getOutputSizes(k);
        }
        // Misplaced declaration of an exception variable
        catch(SparseArray sparsearray)
        {
            Log.e(TAG, "Surface bufferqueue is abandoned, cannot configure as output: ", sparsearray);
            return LegacyExceptionUtils.BAD_VALUE;
        }
        obj1 = obj;
        if(obj != null) goto _L5; else goto _L4
_L4:
        if(k != 34) goto _L7; else goto _L6
_L6:
        obj1 = streamconfigurationmap.getOutputSizes(35);
_L5:
        if(ArrayUtils.contains(((Object []) (obj1)), size)) goto _L9; else goto _L8
_L8:
        obj = size;
        if(!flag) goto _L11; else goto _L10
_L10:
        size = findClosestSize(size, ((Size []) (obj1)));
        obj = size;
        if(size == null) goto _L11; else goto _L12
_L12:
        obj1 = JVM INSTR new #479 <Class Pair>;
        ((Pair) (obj1)).Pair(surface, size);
        arraylist.add(obj1);
_L17:
        setSurfaceDimens(surface, size.getWidth(), size.getHeight());
        j++;
        continue; /* Loop/switch isn't completed */
_L7:
        obj1 = obj;
        if(k != 33) goto _L5; else goto _L13
_L13:
        obj1 = streamconfigurationmap.getOutputSizes(256);
          goto _L5
_L11:
        if(obj1 != null) goto _L15; else goto _L14
_L14:
        sparsearray = "format is invalid.";
_L16:
        Log.e(TAG, String.format("Surface with size (w=%d, h=%d) and format 0x%x is not valid, %s", new Object[] {
            Integer.valueOf(((Size) (obj)).getWidth()), Integer.valueOf(((Size) (obj)).getHeight()), Integer.valueOf(k), sparsearray
        }));
        return LegacyExceptionUtils.BAD_VALUE;
_L15:
        sparsearray = JVM INSTR new #490 <Class StringBuilder>;
        sparsearray.StringBuilder();
        sparsearray = sparsearray.append("size not in valid set: ").append(Arrays.toString(((Object []) (obj1)))).toString();
        if(true) goto _L16; else goto _L9
_L9:
        obj1 = JVM INSTR new #479 <Class Pair>;
        ((Pair) (obj1)).Pair(surface, size);
        arraylist.add(obj1);
          goto _L17
_L2:
        boolean flag1 = false;
        if(mDeviceState.setConfiguring())
        {
            mRequestThreadManager.configure(arraylist);
            flag1 = mDeviceState.setIdle();
        }
        if(flag1)
        {
            mConfiguredSurfaces = sparsearray;
            return 0;
        }
        return LegacyExceptionUtils.INVALID_OPERATION;
        if(true) goto _L19; else goto _L18
_L18:
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
_L2:
        return;
        Object obj;
        obj;
        String s = TAG;
        StringBuilder stringbuilder = JVM INSTR new #490 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e(s, stringbuilder.append("Got error while trying to finalize, ignoring: ").append(((ServiceSpecificException) (obj)).getMessage()).toString());
        super.finalize();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        super.finalize();
        throw obj;
    }

    public long flush()
    {
        long l = mRequestThreadManager.flush();
        waitUntilIdle();
        return l;
    }

    public boolean isClosed()
    {
        return mClosed;
    }

    public SubmitInfo submitRequest(CaptureRequest capturerequest, boolean flag)
    {
        return submitRequestList(new CaptureRequest[] {
            capturerequest
        }, flag);
    }

    public SubmitInfo submitRequestList(CaptureRequest acapturerequest[], boolean flag)
    {
        int i;
        i = 0;
        if(acapturerequest == null || acapturerequest.length == 0)
        {
            Log.e(TAG, "submitRequestList - Empty/null requests are not allowed");
            throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - Empty/null requests are not allowed");
        }
        if(mConfiguredSurfaces != null) goto _L2; else goto _L1
_L1:
        Object obj = new ArrayList();
_L6:
        int j = acapturerequest.length;
_L4:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        CaptureRequest capturerequest = acapturerequest[i];
        if(capturerequest.getTargets().isEmpty())
        {
            Log.e(TAG, "submitRequestList - Each request must have at least one Surface target");
            throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - Each request must have at least one Surface target");
        }
        for(Iterator iterator = capturerequest.getTargets().iterator(); iterator.hasNext();)
        {
            Surface surface = (Surface)iterator.next();
            if(surface == null)
            {
                Log.e(TAG, "submitRequestList - Null Surface targets are not allowed");
                throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - Null Surface targets are not allowed");
            }
            if(mConfiguredSurfaces == null)
            {
                Log.e(TAG, "submitRequestList - must configure  device with valid surfaces before submitting requests");
                throw new ServiceSpecificException(LegacyExceptionUtils.INVALID_OPERATION, "submitRequestList - must configure  device with valid surfaces before submitting requests");
            }
            if(!containsSurfaceId(surface, ((Collection) (obj))))
            {
                Log.e(TAG, "submitRequestList - cannot use a surface that wasn't configured");
                throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - cannot use a surface that wasn't configured");
            }
        }

        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        try
        {
            obj = getSurfaceIds(mConfiguredSurfaces);
        }
        // Misplaced declaration of an exception variable
        catch(CaptureRequest acapturerequest[])
        {
            throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - configured surface is abandoned.");
        }
        continue; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        mIdle.close();
        return mRequestThreadManager.submitCaptureRequests(acapturerequest, flag);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void waitUntilIdle()
    {
        mIdle.block();
    }

    private static final boolean DEBUG = false;
    private static final int GRALLOC_USAGE_HW_COMPOSER = 2048;
    private static final int GRALLOC_USAGE_HW_RENDER = 512;
    private static final int GRALLOC_USAGE_HW_TEXTURE = 256;
    private static final int GRALLOC_USAGE_HW_VIDEO_ENCODER = 0x10000;
    private static final int GRALLOC_USAGE_RENDERSCRIPT = 0x100000;
    private static final int GRALLOC_USAGE_SW_READ_OFTEN = 3;
    private static final int ILLEGAL_VALUE = -1;
    public static final int MAX_DIMEN_FOR_ROUNDING = 1920;
    public static final int NATIVE_WINDOW_SCALING_MODE_SCALE_TO_WINDOW = 1;
    private final String TAG;
    private final Handler mCallbackHandler;
    private final HandlerThread mCallbackHandlerThread = new HandlerThread("CallbackThread");
    private final int mCameraId;
    private boolean mClosed;
    private SparseArray mConfiguredSurfaces;
    private final ICameraDeviceCallbacks mDeviceCallbacks;
    private final CameraDeviceState mDeviceState = new CameraDeviceState();
    private final ConditionVariable mIdle = new ConditionVariable(true);
    private final RequestThreadManager mRequestThreadManager;
    private final Handler mResultHandler;
    private final HandlerThread mResultThread = new HandlerThread("ResultThread");
    private final CameraDeviceState.CameraDeviceStateListener mStateListener = new CameraDeviceState.CameraDeviceStateListener() {

        public void onBusy()
        {
            LegacyCameraDevice._2D_get1(LegacyCameraDevice.this).close();
        }

        public void onCaptureResult(final CameraMetadataNative result, final RequestHolder holder)
        {
            CaptureResultExtras captureresultextras = LegacyCameraDevice._2D_wrap0(LegacyCameraDevice.this, holder);
            LegacyCameraDevice._2D_get2(LegacyCameraDevice.this).post(captureresultextras. new Runnable() {

                public void run()
                {
                    try
                    {
                        LegacyCameraDevice._2D_get0(_fld0).onResultReceived(result, extras);
                        return;
                    }
                    catch(RemoteException remoteexception)
                    {
                        throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteexception);
                    }
                }

                final _cls1 this$1;
                final CaptureResultExtras val$extras;
                final RequestHolder val$holder;
                final CameraMetadataNative val$result;

            
            {
                this$1 = final__pcls1;
                holder = requestholder;
                result = camerametadatanative;
                extras = CaptureResultExtras.this;
                super();
            }
            }
);
        }

        public void onCaptureStarted(final RequestHolder holder, long l)
        {
            final CaptureResultExtras extras = LegacyCameraDevice._2D_wrap0(LegacyCameraDevice.this, holder);
            LegacyCameraDevice._2D_get2(LegacyCameraDevice.this).post(l. new Runnable() {

                public void run()
                {
                    try
                    {
                        LegacyCameraDevice._2D_get0(_fld0).onCaptureStarted(extras, timestamp);
                        return;
                    }
                    catch(RemoteException remoteexception)
                    {
                        throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteexception);
                    }
                }

                final _cls1 this$1;
                final CaptureResultExtras val$extras;
                final RequestHolder val$holder;
                final long val$timestamp;

            
            {
                this$1 = final__pcls1;
                holder = requestholder;
                extras = captureresultextras;
                timestamp = J.this;
                super();
            }
            }
);
        }

        public void onConfiguring()
        {
        }

        public void onError(final int errorCode, Object obj, final RequestHolder holder)
        {
            errorCode;
            JVM INSTR tableswitch 0 2: default 28
        //                       0 62
        //                       1 62
        //                       2 62;
               goto _L1 _L2 _L2 _L2
_L1:
            obj = LegacyCameraDevice._2D_wrap1(LegacyCameraDevice.this, holder, errorCode, obj);
            LegacyCameraDevice._2D_get2(LegacyCameraDevice.this).post(((_cls1) (obj)). new Runnable() {

                public void run()
                {
                    try
                    {
                        LegacyCameraDevice._2D_get0(_fld0).onDeviceError(errorCode, extras);
                        return;
                    }
                    catch(RemoteException remoteexception)
                    {
                        throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteexception);
                    }
                }

                final _cls1 this$1;
                final int val$errorCode;
                final CaptureResultExtras val$extras;
                final RequestHolder val$holder;

            
            {
                this$1 = final__pcls1;
                holder = requestholder;
                errorCode = i;
                extras = CaptureResultExtras.this;
                super();
            }
            }
);
            return;
_L2:
            LegacyCameraDevice._2D_get1(LegacyCameraDevice.this).open();
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void onIdle()
        {
            LegacyCameraDevice._2D_get1(LegacyCameraDevice.this).open();
            LegacyCameraDevice._2D_get2(LegacyCameraDevice.this).post(new Runnable() {

                public void run()
                {
                    try
                    {
                        LegacyCameraDevice._2D_get0(_fld0).onDeviceIdle();
                        return;
                    }
                    catch(RemoteException remoteexception)
                    {
                        throw new IllegalStateException("Received remote exception during onCameraIdle callback: ", remoteexception);
                    }
                }

                final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
            }
);
        }

        public void onRepeatingRequestError(final long lastFrameNumber, int j)
        {
            LegacyCameraDevice._2D_get2(LegacyCameraDevice.this).post(j. new Runnable() {

                public void run()
                {
                    try
                    {
                        LegacyCameraDevice._2D_get0(_fld0).onRepeatingRequestError(lastFrameNumber, repeatingRequestId);
                        return;
                    }
                    catch(RemoteException remoteexception)
                    {
                        throw new IllegalStateException("Received remote exception during onRepeatingRequestError callback: ", remoteexception);
                    }
                }

                final _cls1 this$1;
                final long val$lastFrameNumber;
                final int val$repeatingRequestId;

            
            {
                this$1 = final__pcls1;
                lastFrameNumber = l;
                repeatingRequestId = I.this;
                super();
            }
            }
);
        }

        public void onRequestQueueEmpty()
        {
            LegacyCameraDevice._2D_get2(LegacyCameraDevice.this).post(new Runnable() {

                public void run()
                {
                    try
                    {
                        LegacyCameraDevice._2D_get0(_fld0).onRequestQueueEmpty();
                        return;
                    }
                    catch(RemoteException remoteexception)
                    {
                        throw new IllegalStateException("Received remote exception during onRequestQueueEmpty callback: ", remoteexception);
                    }
                }

                final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
            }
);
        }

        final LegacyCameraDevice this$0;

            
            {
                this$0 = LegacyCameraDevice.this;
                super();
            }
    }
;
    private final CameraCharacteristics mStaticCharacteristics;
}
