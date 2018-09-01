// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.os.*;
import android.view.Surface;
import dalvik.system.VMRuntime;
import java.lang.ref.WeakReference;
import java.nio.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.media:
//            ImageUtils, Image

public class ImageReader
    implements AutoCloseable
{
    private final class ListenerHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message = ((Message) (ImageReader._2D_get4(ImageReader.this)));
            message;
            JVM INSTR monitorenter ;
            OnImageAvailableListener onimageavailablelistener = ImageReader._2D_get3(ImageReader.this);
            message;
            JVM INSTR monitorexit ;
            message = ((Message) (ImageReader._2D_get0(ImageReader.this)));
            message;
            JVM INSTR monitorenter ;
            boolean flag = ImageReader._2D_get2(ImageReader.this);
            message;
            JVM INSTR monitorexit ;
            if(onimageavailablelistener != null && flag)
                onimageavailablelistener.onImageAvailable(ImageReader.this);
            return;
            Exception exception;
            exception;
            throw exception;
            exception;
            throw exception;
        }

        final ImageReader this$0;

        public ListenerHandler(Looper looper)
        {
            this$0 = ImageReader.this;
            super(looper, null, true);
        }
    }

    public static interface OnImageAvailableListener
    {

        public abstract void onImageAvailable(ImageReader imagereader);
    }

    private class SurfaceImage extends Image
    {

        static SurfacePlane[] _2D_set0(SurfaceImage surfaceimage, SurfacePlane asurfaceplane[])
        {
            surfaceimage.mPlanes = asurfaceplane;
            return asurfaceplane;
        }

        static void _2D_wrap0(SurfaceImage surfaceimage)
        {
            surfaceimage.clearSurfacePlanes();
        }

        static void _2D_wrap1(SurfaceImage surfaceimage, boolean flag)
        {
            surfaceimage.setDetached(flag);
        }

        private void clearSurfacePlanes()
        {
            if(mIsImageValid && mPlanes != null)
            {
                for(int i = 0; i < mPlanes.length; i++)
                    if(mPlanes[i] != null)
                    {
                        SurfacePlane._2D_wrap0(mPlanes[i]);
                        mPlanes[i] = null;
                    }

            }
        }

        private synchronized native SurfacePlane[] nativeCreatePlanes(int i, int j);

        private synchronized native int nativeGetFormat(int i);

        private synchronized native int nativeGetHeight();

        private synchronized native int nativeGetWidth();

        private void setDetached(boolean flag)
        {
            throwISEIfImageIsInvalid();
            mIsDetached.getAndSet(flag);
        }

        public void close()
        {
            ImageReader._2D_wrap0(ImageReader.this, this);
        }

        protected final void finalize()
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

        public int getFormat()
        {
            throwISEIfImageIsInvalid();
            int i = getImageFormat();
            if(i != 34)
                i = nativeGetFormat(i);
            mFormat = i;
            return mFormat;
        }

        public int getHeight()
        {
            throwISEIfImageIsInvalid();
            getFormat();
            JVM INSTR lookupswitch 3: default 44
        //                       36: 51
        //                       256: 51
        //                       257: 51;
               goto _L1 _L2 _L2 _L2
_L1:
            int i = nativeGetHeight();
_L4:
            return i;
_L2:
            i = ImageReader.this.getHeight();
            if(true) goto _L4; else goto _L3
_L3:
        }

        long getNativeContext()
        {
            throwISEIfImageIsInvalid();
            return mNativeBuffer;
        }

        ImageReader getOwner()
        {
            throwISEIfImageIsInvalid();
            return ImageReader.this;
        }

        volatile Object getOwner()
        {
            return getOwner();
        }

        public Image.Plane[] getPlanes()
        {
            throwISEIfImageIsInvalid();
            if(mPlanes == null)
                mPlanes = nativeCreatePlanes(ImageReader._2D_get5(ImageReader.this), ImageReader._2D_get1(ImageReader.this));
            return (Image.Plane[])mPlanes.clone();
        }

        public ImageReader getReader()
        {
            return ImageReader.this;
        }

        public long getTimestamp()
        {
            throwISEIfImageIsInvalid();
            return mTimestamp;
        }

        public int getWidth()
        {
            throwISEIfImageIsInvalid();
            getFormat();
            JVM INSTR lookupswitch 3: default 44
        //                       36: 51
        //                       256: 51
        //                       257: 51;
               goto _L1 _L2 _L2 _L2
_L1:
            int i = nativeGetWidth();
_L4:
            return i;
_L2:
            i = ImageReader.this.getWidth();
            if(true) goto _L4; else goto _L3
_L3:
        }

        boolean isAttachable()
        {
            throwISEIfImageIsInvalid();
            return mIsDetached.get();
        }

        public void setTimestamp(long l)
        {
            throwISEIfImageIsInvalid();
            mTimestamp = l;
        }

        private int mFormat;
        private AtomicBoolean mIsDetached;
        private long mNativeBuffer;
        private SurfacePlane mPlanes[];
        private long mTimestamp;
        final ImageReader this$0;

        public SurfaceImage(int i)
        {
            this$0 = ImageReader.this;
            super();
            mFormat = 0;
            mIsDetached = new AtomicBoolean(false);
            mFormat = i;
        }
    }

    private class SurfaceImage.SurfacePlane extends Image.Plane
    {

        static void _2D_wrap0(SurfaceImage.SurfacePlane surfaceplane)
        {
            surfaceplane.clearBuffer();
        }

        private void clearBuffer()
        {
            if(mBuffer == null)
                return;
            if(mBuffer.isDirect())
                NioUtils.freeDirectBuffer(mBuffer);
            mBuffer = null;
        }

        public ByteBuffer getBuffer()
        {
            throwISEIfImageIsInvalid();
            return mBuffer;
        }

        public int getPixelStride()
        {
            throwISEIfImageIsInvalid();
            if(ImageReader._2D_get1(_fld0) == 36)
                throw new UnsupportedOperationException("getPixelStride is not supported for RAW_PRIVATE plane");
            else
                return mPixelStride;
        }

        public int getRowStride()
        {
            throwISEIfImageIsInvalid();
            if(ImageReader._2D_get1(_fld0) == 36)
                throw new UnsupportedOperationException("getRowStride is not supported for RAW_PRIVATE plane");
            else
                return mRowStride;
        }

        private ByteBuffer mBuffer;
        private final int mPixelStride;
        private final int mRowStride;
        final SurfaceImage this$1;

        private SurfaceImage.SurfacePlane(int i, int j, ByteBuffer bytebuffer)
        {
            this$1 = SurfaceImage.this;
            super();
            mRowStride = i;
            mPixelStride = j;
            mBuffer = bytebuffer;
            mBuffer.order(ByteOrder.nativeOrder());
        }
    }


    static Object _2D_get0(ImageReader imagereader)
    {
        return imagereader.mCloseLock;
    }

    static int _2D_get1(ImageReader imagereader)
    {
        return imagereader.mFormat;
    }

    static boolean _2D_get2(ImageReader imagereader)
    {
        return imagereader.mIsReaderValid;
    }

    static OnImageAvailableListener _2D_get3(ImageReader imagereader)
    {
        return imagereader.mListener;
    }

    static Object _2D_get4(ImageReader imagereader)
    {
        return imagereader.mListenerLock;
    }

    static int _2D_get5(ImageReader imagereader)
    {
        return imagereader.mNumPlanes;
    }

    static void _2D_wrap0(ImageReader imagereader, Image image)
    {
        imagereader.releaseImage(image);
    }

    protected ImageReader(int i, int j, int k, int l, long l1)
    {
        mIsReaderValid = false;
        mAcquiredImages = new CopyOnWriteArrayList();
        mWidth = i;
        mHeight = j;
        mFormat = k;
        mMaxImages = l;
        if(i < 1 || j < 1)
            throw new IllegalArgumentException("The image dimensions must be positive");
        if(mMaxImages < 1)
            throw new IllegalArgumentException("Maximum outstanding image count must be at least 1");
        if(k == 17)
        {
            throw new IllegalArgumentException("NV21 format is not supported");
        } else
        {
            mNumPlanes = ImageUtils.getNumPlanesForFormat(mFormat);
            nativeInit(new WeakReference(this), i, j, k, l, l1);
            mSurface = nativeGetSurface();
            mIsReaderValid = true;
            mEstimatedNativeAllocBytes = ImageUtils.getEstimatedNativeAllocBytes(i, j, k, 1);
            VMRuntime.getRuntime().registerNativeAllocation(mEstimatedNativeAllocBytes);
            return;
        }
    }

    private int acquireNextSurfaceImage(SurfaceImage surfaceimage)
    {
        Object obj = mCloseLock;
        obj;
        JVM INSTR monitorenter ;
        int i = 1;
        if(mIsReaderValid)
            i = nativeImageSetup(surfaceimage);
        i;
        JVM INSTR tableswitch 0 2: default 48
    //                   0 87
    //                   1 92
    //                   2 92;
           goto _L1 _L2 _L3 _L3
_L1:
        AssertionError assertionerror = JVM INSTR new #162 <Class AssertionError>;
        surfaceimage = JVM INSTR new #164 <Class StringBuilder>;
        surfaceimage.StringBuilder();
        assertionerror.AssertionError(surfaceimage.append("Unknown nativeImageSetup return code ").append(i).toString());
        throw assertionerror;
        surfaceimage;
        obj;
        JVM INSTR monitorexit ;
        throw surfaceimage;
_L2:
        surfaceimage.mIsImageValid = true;
_L3:
        if(i != 0) goto _L5; else goto _L4
_L4:
        mAcquiredImages.add(surfaceimage);
_L5:
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    private static boolean isFormatUsageCombinationAllowed(int i, long l)
    {
        boolean flag = true;
        if(!ImageFormat.isPublicFormat(i) && PixelFormat.isPublicFormat(i) ^ true)
            return false;
        if(l == 0L)
            return false;
        if(i == 34)
        {
            boolean flag1;
            if(l == 256L)
                i = 1;
            else
                i = 0;
            if(i != 0 || l == 0x10000L)
                i = 1;
            else
                i = 0;
            if(i != 0 || l == 0x10100L)
                flag1 = true;
            else
                flag1 = false;
            return flag1;
        }
        boolean flag2 = flag;
        if(l != 2L)
            if(l == 3L)
                flag2 = flag;
            else
                flag2 = false;
        return flag2;
    }

    private boolean isImageOwnedbyMe(Image image)
    {
        boolean flag = false;
        if(!(image instanceof SurfaceImage))
            return false;
        if(((SurfaceImage)image).getReader() == this)
            flag = true;
        return flag;
    }

    private static native void nativeClassInit();

    private synchronized native void nativeClose();

    private synchronized native int nativeDetachImage(Image image);

    private synchronized native void nativeDiscardFreeBuffers();

    private synchronized native Surface nativeGetSurface();

    private synchronized native int nativeImageSetup(Image image);

    private synchronized native void nativeInit(Object obj, int i, int j, int k, int l, long l1);

    private synchronized native void nativeReleaseImage(Image image);

    public static ImageReader newInstance(int i, int j, int k, int l)
    {
        return new ImageReader(i, j, k, l, 0L);
    }

    public static ImageReader newInstance(int i, int j, int k, int l, long l1)
    {
        if(!isFormatUsageCombinationAllowed(k, l1))
            throw new IllegalArgumentException((new StringBuilder()).append("Format usage combination is not supported: format = ").append(k).append(", usage = ").append(l1).toString());
        else
            return new ImageReader(i, j, k, l, l1);
    }

    private static void postEventFromNative(Object obj)
    {
        Object obj1;
        obj1 = (ImageReader)((WeakReference)obj).get();
        if(obj1 == null)
            return;
        obj = ((ImageReader) (obj1)).mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        obj1 = ((ImageReader) (obj1)).mListenerHandler;
        obj;
        JVM INSTR monitorexit ;
        if(obj1 != null)
            ((Handler) (obj1)).sendEmptyMessage(0);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void releaseImage(Image image)
    {
        if(!(image instanceof SurfaceImage))
            throw new IllegalArgumentException("This image was not produced by an ImageReader");
        SurfaceImage surfaceimage = (SurfaceImage)image;
        if(!surfaceimage.mIsImageValid)
            return;
        if(surfaceimage.getReader() != this || mAcquiredImages.contains(image) ^ true)
        {
            throw new IllegalArgumentException("This image was not produced by this ImageReader");
        } else
        {
            SurfaceImage._2D_wrap0(surfaceimage);
            nativeReleaseImage(image);
            surfaceimage.mIsImageValid = false;
            mAcquiredImages.remove(image);
            return;
        }
    }

    public Image acquireLatestImage()
    {
        Image image2;
        Image image = acquireNextImage();
        image2 = image;
        if(image == null)
            return null;
          goto _L1
_L3:
        image2.close();
        Image image1;
        image2 = image1;
_L1:
        if((image1 = acquireNextImageNoThrowISE()) != null) goto _L3; else goto _L2
_L2:
        return image2;
        Exception exception;
        exception;
        if(image2 != null)
            image2.close();
        throw exception;
    }

    public Image acquireNextImage()
    {
        SurfaceImage surfaceimage = new SurfaceImage(mFormat);
        int i = acquireNextSurfaceImage(surfaceimage);
        switch(i)
        {
        default:
            throw new AssertionError((new StringBuilder()).append("Unknown nativeImageSetup return code ").append(i).toString());

        case 0: // '\0'
            return surfaceimage;

        case 1: // '\001'
            return null;

        case 2: // '\002'
            throw new IllegalStateException(String.format("maxImages (%d) has already been acquired, call #close before acquiring more.", new Object[] {
                Integer.valueOf(mMaxImages)
            }));
        }
    }

    public Image acquireNextImageNoThrowISE()
    {
        SurfaceImage surfaceimage = new SurfaceImage(mFormat);
        if(acquireNextSurfaceImage(surfaceimage) != 0)
            surfaceimage = null;
        return surfaceimage;
    }

    public void close()
    {
        setOnImageAvailableListener(null, null);
        if(mSurface != null)
            mSurface.release();
        Object obj = mCloseLock;
        obj;
        JVM INSTR monitorenter ;
        mIsReaderValid = false;
        for(Iterator iterator = mAcquiredImages.iterator(); iterator.hasNext(); ((Image)iterator.next()).close());
        break MISSING_BLOCK_LABEL_71;
        Exception exception;
        exception;
        throw exception;
        mAcquiredImages.clear();
        nativeClose();
        if(mEstimatedNativeAllocBytes > 0)
        {
            VMRuntime.getRuntime().registerNativeFree(mEstimatedNativeAllocBytes);
            mEstimatedNativeAllocBytes = 0;
        }
        obj;
        JVM INSTR monitorexit ;
    }

    void detachImage(Image image)
    {
        if(image == null)
            throw new IllegalArgumentException("input image must not be null");
        if(!isImageOwnedbyMe(image))
            throw new IllegalArgumentException("Trying to detach an image that is not owned by this ImageReader");
        SurfaceImage surfaceimage = (SurfaceImage)image;
        surfaceimage.throwISEIfImageIsInvalid();
        if(surfaceimage.isAttachable())
        {
            throw new IllegalStateException("Image was already detached from this ImageReader");
        } else
        {
            nativeDetachImage(image);
            SurfaceImage._2D_wrap0(surfaceimage);
            SurfaceImage._2D_set0(surfaceimage, null);
            SurfaceImage._2D_wrap1(surfaceimage, true);
            return;
        }
    }

    public void discardFreeBuffers()
    {
        Object obj = mCloseLock;
        obj;
        JVM INSTR monitorenter ;
        nativeDiscardFreeBuffers();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
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

    public int getHeight()
    {
        return mHeight;
    }

    public int getImageFormat()
    {
        return mFormat;
    }

    public int getMaxImages()
    {
        return mMaxImages;
    }

    public Surface getSurface()
    {
        return mSurface;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public void setOnImageAvailableListener(OnImageAvailableListener onimageavailablelistener, Handler handler)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(onimageavailablelistener == null)
            break MISSING_BLOCK_LABEL_93;
        if(handler == null)
            break MISSING_BLOCK_LABEL_42;
        handler = handler.getLooper();
_L1:
        if(handler != null)
            break MISSING_BLOCK_LABEL_49;
        onimageavailablelistener = JVM INSTR new #109 <Class IllegalArgumentException>;
        onimageavailablelistener.IllegalArgumentException("handler is null but the current thread is not a looper");
        throw onimageavailablelistener;
        onimageavailablelistener;
        obj;
        JVM INSTR monitorexit ;
        throw onimageavailablelistener;
        handler = Looper.myLooper();
          goto _L1
        if(mListenerHandler == null || mListenerHandler.getLooper() != handler)
        {
            ListenerHandler listenerhandler = JVM INSTR new #8   <Class ImageReader$ListenerHandler>;
            listenerhandler.this. ListenerHandler(handler);
            mListenerHandler = listenerhandler;
        }
        mListener = onimageavailablelistener;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        mListener = null;
        mListenerHandler = null;
          goto _L2
    }

    private static final int ACQUIRE_MAX_IMAGES = 2;
    private static final int ACQUIRE_NO_BUFS = 1;
    private static final int ACQUIRE_SUCCESS = 0;
    private static final long BUFFER_USAGE_UNKNOWN = 0L;
    private List mAcquiredImages;
    private final Object mCloseLock = new Object();
    private int mEstimatedNativeAllocBytes;
    private final int mFormat;
    private final int mHeight;
    private boolean mIsReaderValid;
    private OnImageAvailableListener mListener;
    private ListenerHandler mListenerHandler;
    private final Object mListenerLock = new Object();
    private final int mMaxImages;
    private long mNativeContext;
    private final int mNumPlanes;
    private final Surface mSurface;
    private final int mWidth;

    static 
    {
        System.loadLibrary("media_jni");
        nativeClassInit();
    }
}
