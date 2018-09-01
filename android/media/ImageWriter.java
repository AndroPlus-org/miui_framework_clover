// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.*;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.*;
import android.util.Size;
import android.view.Surface;
import dalvik.system.VMRuntime;
import java.lang.ref.WeakReference;
import java.nio.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.media:
//            ImageUtils, Image, ImageReader

public class ImageWriter
    implements AutoCloseable
{
    private final class ListenerHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message = ((Message) (ImageWriter._2D_get1(ImageWriter.this)));
            message;
            JVM INSTR monitorenter ;
            OnImageReleasedListener onimagereleasedlistener = ImageWriter._2D_get0(ImageWriter.this);
            message;
            JVM INSTR monitorexit ;
            if(onimagereleasedlistener != null)
                onimagereleasedlistener.onImageReleased(ImageWriter.this);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final ImageWriter this$0;

        public ListenerHandler(Looper looper)
        {
            this$0 = ImageWriter.this;
            super(looper, null, true);
        }
    }

    public static interface OnImageReleasedListener
    {

        public abstract void onImageReleased(ImageWriter imagewriter);
    }

    private static class WriterSurfaceImage extends Image
    {

        static void _2D_wrap0(WriterSurfaceImage writersurfaceimage)
        {
            writersurfaceimage.clearSurfacePlanes();
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

        private synchronized native int nativeGetFormat();

        private synchronized native int nativeGetHeight();

        private synchronized native int nativeGetWidth();

        public void close()
        {
            if(mIsImageValid)
                ImageWriter._2D_wrap0(getOwner(), this);
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
            if(mFormat == -1)
                mFormat = nativeGetFormat();
            return mFormat;
        }

        public int getHeight()
        {
            throwISEIfImageIsInvalid();
            if(mHeight == -1)
                mHeight = nativeGetHeight();
            return mHeight;
        }

        long getNativeContext()
        {
            throwISEIfImageIsInvalid();
            return mNativeBuffer;
        }

        ImageWriter getOwner()
        {
            throwISEIfImageIsInvalid();
            return mOwner;
        }

        volatile Object getOwner()
        {
            return getOwner();
        }

        public Image.Plane[] getPlanes()
        {
            throwISEIfImageIsInvalid();
            if(mPlanes == null)
                mPlanes = nativeCreatePlanes(ImageUtils.getNumPlanesForFormat(getFormat()), getOwner().getFormat());
            return (Image.Plane[])mPlanes.clone();
        }

        public long getTimestamp()
        {
            throwISEIfImageIsInvalid();
            return mTimestamp;
        }

        public int getWidth()
        {
            throwISEIfImageIsInvalid();
            if(mWidth == -1)
                mWidth = nativeGetWidth();
            return mWidth;
        }

        boolean isAttachable()
        {
            throwISEIfImageIsInvalid();
            return false;
        }

        public void setTimestamp(long l)
        {
            throwISEIfImageIsInvalid();
            mTimestamp = l;
        }

        private final long DEFAULT_TIMESTAMP = 0x8000000000000000L;
        private int mFormat;
        private int mHeight;
        private long mNativeBuffer;
        private int mNativeFenceFd;
        private ImageWriter mOwner;
        private SurfacePlane mPlanes[];
        private long mTimestamp;
        private int mWidth;

        public WriterSurfaceImage(ImageWriter imagewriter)
        {
            mNativeFenceFd = -1;
            mHeight = -1;
            mWidth = -1;
            mFormat = -1;
            mTimestamp = 0x8000000000000000L;
            mOwner = imagewriter;
        }
    }

    private class WriterSurfaceImage.SurfacePlane extends Image.Plane
    {

        static void _2D_wrap0(WriterSurfaceImage.SurfacePlane surfaceplane)
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
            return mPixelStride;
        }

        public int getRowStride()
        {
            throwISEIfImageIsInvalid();
            return mRowStride;
        }

        private ByteBuffer mBuffer;
        private final int mPixelStride;
        private final int mRowStride;
        final WriterSurfaceImage this$1;

        private WriterSurfaceImage.SurfacePlane(int i, int j, ByteBuffer bytebuffer)
        {
            this$1 = WriterSurfaceImage.this;
            super();
            mRowStride = i;
            mPixelStride = j;
            mBuffer = bytebuffer;
            mBuffer.order(ByteOrder.nativeOrder());
        }
    }


    static OnImageReleasedListener _2D_get0(ImageWriter imagewriter)
    {
        return imagewriter.mListener;
    }

    static Object _2D_get1(ImageWriter imagewriter)
    {
        return imagewriter.mListenerLock;
    }

    static void _2D_wrap0(ImageWriter imagewriter, Image image)
    {
        imagewriter.abortImage(image);
    }

    protected ImageWriter(Surface surface, int i, int j)
    {
        mDequeuedImages = new CopyOnWriteArrayList();
        if(surface == null || i < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal input argument: surface ").append(surface).append(", maxImages: ").append(i).toString());
        mMaxImages = i;
        int k = j;
        if(j == 0)
            k = SurfaceUtils.getSurfaceFormat(surface);
        mNativeContext = nativeInit(new WeakReference(this), surface, i, k);
        surface = SurfaceUtils.getSurfaceSize(surface);
        mEstimatedNativeAllocBytes = ImageUtils.getEstimatedNativeAllocBytes(surface.getWidth(), surface.getHeight(), k, 1);
        VMRuntime.getRuntime().registerNativeAllocation(mEstimatedNativeAllocBytes);
    }

    private void abortImage(Image image)
    {
        if(image == null)
            throw new IllegalArgumentException("image shouldn't be null");
        if(!mDequeuedImages.contains(image))
            throw new IllegalStateException("It is illegal to abort some image that is not dequeued yet");
        WriterSurfaceImage writersurfaceimage = (WriterSurfaceImage)image;
        if(!writersurfaceimage.mIsImageValid)
        {
            return;
        } else
        {
            cancelImage(mNativeContext, image);
            mDequeuedImages.remove(image);
            WriterSurfaceImage._2D_wrap0(writersurfaceimage);
            writersurfaceimage.mIsImageValid = false;
            return;
        }
    }

    private void attachAndQueueInputImage(Image image)
    {
        if(image == null)
            throw new IllegalArgumentException("image shouldn't be null");
        if(isImageOwnedByMe(image))
            throw new IllegalArgumentException("Can not attach an image that is owned ImageWriter already");
        if(!image.isAttachable())
        {
            throw new IllegalStateException("Image was not detached from last owner, or image  is not detachable");
        } else
        {
            Rect rect = image.getCropRect();
            nativeAttachAndQueueImage(mNativeContext, image.getNativeContext(), image.getFormat(), image.getTimestamp(), rect.left, rect.top, rect.right, rect.bottom);
            return;
        }
    }

    private synchronized native void cancelImage(long l, Image image);

    private boolean isImageOwnedByMe(Image image)
    {
        if(!(image instanceof WriterSurfaceImage))
            return false;
        return ((WriterSurfaceImage)image).getOwner() == this;
    }

    private synchronized native int nativeAttachAndQueueImage(long l, long l1, int i, long l2, 
            int j, int k, int i1, int j1);

    private static native void nativeClassInit();

    private synchronized native void nativeClose(long l);

    private synchronized native void nativeDequeueInputImage(long l, Image image);

    private synchronized native long nativeInit(Object obj, Surface surface, int i, int j);

    private synchronized native void nativeQueueInputImage(long l, Image image, long l1, int i, int j, 
            int k, int i1);

    public static ImageWriter newInstance(Surface surface, int i)
    {
        return new ImageWriter(surface, i, 0);
    }

    public static ImageWriter newInstance(Surface surface, int i, int j)
    {
        if(!ImageFormat.isPublicFormat(j) && PixelFormat.isPublicFormat(j) ^ true)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid format is specified: ").append(j).toString());
        else
            return new ImageWriter(surface, i, j);
    }

    private static void postEventFromNative(Object obj)
    {
        Object obj1;
        obj1 = (ImageWriter)((WeakReference)obj).get();
        if(obj1 == null)
            return;
        obj = ((ImageWriter) (obj1)).mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        obj1 = ((ImageWriter) (obj1)).mListenerHandler;
        obj;
        JVM INSTR monitorexit ;
        if(obj1 != null)
            ((Handler) (obj1)).sendEmptyMessage(0);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void close()
    {
        setOnImageReleasedListener(null, null);
        for(Iterator iterator = mDequeuedImages.iterator(); iterator.hasNext(); ((Image)iterator.next()).close());
        mDequeuedImages.clear();
        nativeClose(mNativeContext);
        mNativeContext = 0L;
        if(mEstimatedNativeAllocBytes > 0)
        {
            VMRuntime.getRuntime().registerNativeFree(mEstimatedNativeAllocBytes);
            mEstimatedNativeAllocBytes = 0;
        }
    }

    public Image dequeueInputImage()
    {
        if(mWriterFormat == 34)
            throw new IllegalStateException("PRIVATE format ImageWriter doesn't support this operation since the images are inaccessible to the application!");
        if(mDequeuedImages.size() >= mMaxImages)
        {
            throw new IllegalStateException((new StringBuilder()).append("Already dequeued max number of Images ").append(mMaxImages).toString());
        } else
        {
            WriterSurfaceImage writersurfaceimage = new WriterSurfaceImage(this);
            nativeDequeueInputImage(mNativeContext, writersurfaceimage);
            mDequeuedImages.add(writersurfaceimage);
            writersurfaceimage.mIsImageValid = true;
            return writersurfaceimage;
        }
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

    public int getFormat()
    {
        return mWriterFormat;
    }

    public int getMaxImages()
    {
        return mMaxImages;
    }

    public void queueInputImage(Image image)
    {
        if(image == null)
            throw new IllegalArgumentException("image shouldn't be null");
        boolean flag = isImageOwnedByMe(image);
        if(flag && ((WriterSurfaceImage)image).mIsImageValid ^ true)
            throw new IllegalStateException("Image from ImageWriter is invalid");
        if(!flag)
            if(!(image.getOwner() instanceof ImageReader))
            {
                throw new IllegalArgumentException("Only images from ImageReader can be queued to ImageWriter, other image source is not supported yet!");
            } else
            {
                ((ImageReader)image.getOwner()).detachImage(image);
                attachAndQueueInputImage(image);
                image.close();
                return;
            }
        Rect rect = image.getCropRect();
        nativeQueueInputImage(mNativeContext, image, image.getTimestamp(), rect.left, rect.top, rect.right, rect.bottom);
        if(flag)
        {
            mDequeuedImages.remove(image);
            image = (WriterSurfaceImage)image;
            WriterSurfaceImage._2D_wrap0(image);
            image.mIsImageValid = false;
        }
    }

    public void setOnImageReleasedListener(OnImageReleasedListener onimagereleasedlistener, Handler handler)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(onimagereleasedlistener == null)
            break MISSING_BLOCK_LABEL_93;
        if(handler == null)
            break MISSING_BLOCK_LABEL_42;
        handler = handler.getLooper();
_L1:
        if(handler != null)
            break MISSING_BLOCK_LABEL_49;
        onimagereleasedlistener = JVM INSTR new #72  <Class IllegalArgumentException>;
        onimagereleasedlistener.IllegalArgumentException("handler is null but the current thread is not a looper");
        throw onimagereleasedlistener;
        onimagereleasedlistener;
        obj;
        JVM INSTR monitorexit ;
        throw onimagereleasedlistener;
        handler = Looper.myLooper();
          goto _L1
        if(mListenerHandler == null || mListenerHandler.getLooper() != handler)
        {
            ListenerHandler listenerhandler = JVM INSTR new #8   <Class ImageWriter$ListenerHandler>;
            listenerhandler.this. ListenerHandler(handler);
            mListenerHandler = listenerhandler;
        }
        mListener = onimagereleasedlistener;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        mListener = null;
        mListenerHandler = null;
          goto _L2
    }

    private List mDequeuedImages;
    private int mEstimatedNativeAllocBytes;
    private OnImageReleasedListener mListener;
    private ListenerHandler mListenerHandler;
    private final Object mListenerLock = new Object();
    private final int mMaxImages;
    private long mNativeContext;
    private int mWriterFormat;

    static 
    {
        System.loadLibrary("media_jni");
        nativeClassInit();
    }
}
