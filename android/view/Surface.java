// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import dalvik.system.CloseGuard;

// Referenced classes of package android.view:
//            SurfaceControl, RenderNode, DisplayListCanvas

public class Surface
    implements Parcelable
{
    private final class CompatibleCanvas extends Canvas
    {

        public void getMatrix(Matrix matrix)
        {
            super.getMatrix(matrix);
            if(mOrigMatrix == null)
                mOrigMatrix = new Matrix();
            mOrigMatrix.set(matrix);
        }

        public void setMatrix(Matrix matrix)
        {
            if(Surface._2D_get0(Surface.this) == null || mOrigMatrix == null || mOrigMatrix.equals(matrix))
            {
                super.setMatrix(matrix);
            } else
            {
                Matrix matrix1 = new Matrix(Surface._2D_get0(Surface.this));
                matrix1.preConcat(matrix);
                super.setMatrix(matrix1);
            }
        }

        private Matrix mOrigMatrix;
        final Surface this$0;

        private CompatibleCanvas()
        {
            this$0 = Surface.this;
            super();
            mOrigMatrix = null;
        }

        CompatibleCanvas(CompatibleCanvas compatiblecanvas)
        {
            this();
        }
    }

    private final class HwuiContext
    {

        void destroy()
        {
            if(mHwuiRenderer != 0L)
            {
                Surface._2D_wrap1(mHwuiRenderer);
                mHwuiRenderer = 0L;
            }
        }

        Canvas lockCanvas(int i, int j)
        {
            if(mCanvas != null)
            {
                throw new IllegalStateException("Surface was already locked!");
            } else
            {
                mCanvas = mRenderNode.start(i, j);
                return mCanvas;
            }
        }

        void unlockAndPost(Canvas canvas)
        {
            if(canvas != mCanvas)
            {
                throw new IllegalArgumentException("canvas object must be the same instance that was previously returned by lockCanvas");
            } else
            {
                mRenderNode.end(mCanvas);
                mCanvas = null;
                Surface._2D_wrap2(mHwuiRenderer);
                return;
            }
        }

        void updateSurface()
        {
            Surface._2D_wrap3(mHwuiRenderer, mNativeObject);
        }

        private DisplayListCanvas mCanvas;
        private long mHwuiRenderer;
        private final RenderNode mRenderNode = RenderNode.create("HwuiCanvas", null);
        final Surface this$0;

        HwuiContext()
        {
            this$0 = Surface.this;
            super();
            mRenderNode.setClipToBounds(false);
            mHwuiRenderer = Surface._2D_wrap0(mRenderNode.mNativeRenderNode, mNativeObject);
        }
    }

    public static class OutOfResourcesException extends RuntimeException
    {

        public OutOfResourcesException()
        {
        }

        public OutOfResourcesException(String s)
        {
            super(s);
        }
    }


    static Matrix _2D_get0(Surface surface)
    {
        return surface.mCompatibleMatrix;
    }

    static long _2D_wrap0(long l, long l1)
    {
        return nHwuiCreate(l, l1);
    }

    static void _2D_wrap1(long l)
    {
        nHwuiDestroy(l);
    }

    static void _2D_wrap2(long l)
    {
        nHwuiDraw(l);
    }

    static void _2D_wrap3(long l, long l1)
    {
        nHwuiSetSurface(l, l1);
    }

    public Surface()
    {
        mCloseGuard = CloseGuard.get();
        mLock = new Object();
        mCanvas = new CompatibleCanvas(null);
    }

    private Surface(long l)
    {
        mCloseGuard = CloseGuard.get();
        mLock = new Object();
        mCanvas = new CompatibleCanvas(null);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        setNativeObjectLocked(l);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Surface(SurfaceTexture surfacetexture)
    {
        mCloseGuard = CloseGuard.get();
        mLock = new Object();
        mCanvas = new CompatibleCanvas(null);
        if(surfacetexture == null)
            throw new IllegalArgumentException("surfaceTexture must not be null");
        mIsSingleBuffered = surfacetexture.isSingleBuffered();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mName = surfacetexture.toString();
        setNativeObjectLocked(nativeCreateFromSurfaceTexture(surfacetexture));
        obj;
        JVM INSTR monitorexit ;
        return;
        surfacetexture;
        throw surfacetexture;
    }

    private void checkNotReleasedLocked()
    {
        if(mNativeObject == 0L)
            throw new IllegalStateException("Surface has already been released.");
        else
            return;
    }

    private static native long nHwuiCreate(long l, long l1);

    private static native void nHwuiDestroy(long l);

    private static native void nHwuiDraw(long l);

    private static native void nHwuiSetSurface(long l, long l1);

    private static native void nativeAdvancedSfComposition(long l);

    private static native void nativeAllocateBuffers(long l);

    private static native int nativeAttachAndQueueBuffer(long l, GraphicBuffer graphicbuffer);

    private static native long nativeCreateFromSurfaceControl(long l);

    private static native long nativeCreateFromSurfaceTexture(SurfaceTexture surfacetexture)
        throws OutOfResourcesException;

    private static native int nativeForceScopedDisconnect(long l);

    private static native long nativeGetFromSurfaceControl(long l);

    private static native int nativeGetHeight(long l);

    private static native long nativeGetNextFrameNumber(long l);

    private static native int nativeGetWidth(long l);

    private static native boolean nativeIsConsumerRunningBehind(long l);

    private static native boolean nativeIsValid(long l);

    private static native long nativeLockCanvas(long l, Canvas canvas, Rect rect)
        throws OutOfResourcesException;

    private static native long nativeReadFromParcel(long l, Parcel parcel);

    private static native void nativeRelease(long l);

    private static native int nativeSetAutoRefreshEnabled(long l, boolean flag);

    private static native int nativeSetScalingMode(long l, int i);

    private static native int nativeSetSharedBufferModeEnabled(long l, boolean flag);

    private static native void nativeUnlockCanvasAndPost(long l, Canvas canvas);

    private static native void nativeWriteToParcel(long l, Parcel parcel);

    public static String rotationToString(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid rotation: ").append(i).toString());

        case 0: // '\0'
            return "ROTATION_0";

        case 1: // '\001'
            return "ROTATION_90";

        case 2: // '\002'
            return "ROTATION_180";

        case 3: // '\003'
            return "ROTATION_270";
        }
    }

    private void setNativeObjectLocked(long l)
    {
        if(mNativeObject == l) goto _L2; else goto _L1
_L1:
        if(mNativeObject != 0L || l == 0L) goto _L4; else goto _L3
_L3:
        mCloseGuard.open("release");
_L6:
        mNativeObject = l;
        mGenerationId = mGenerationId + 1;
        if(mHwuiContext != null)
            mHwuiContext.updateSurface();
_L2:
        return;
_L4:
        if(mNativeObject != 0L && l == 0L)
            mCloseGuard.close();
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void unlockSwCanvasAndPost(Canvas canvas)
    {
        if(canvas != mCanvas)
            throw new IllegalArgumentException("canvas object must be the same instance that was previously returned by lockCanvas");
        if(mNativeObject != mLockedObject)
            Log.w("Surface", (new StringBuilder()).append("WARNING: Surface's mNativeObject (0x").append(Long.toHexString(mNativeObject)).append(") != mLockedObject (0x").append(Long.toHexString(mLockedObject)).append(")").toString());
        if(mLockedObject == 0L)
            throw new IllegalStateException("Surface was not locked");
        nativeUnlockCanvasAndPost(mLockedObject, canvas);
        nativeRelease(mLockedObject);
        mLockedObject = 0L;
        return;
        canvas;
        nativeRelease(mLockedObject);
        mLockedObject = 0L;
        throw canvas;
    }

    public void advancedSfComposition()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        nativeAdvancedSfComposition(mNativeObject);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void allocateBuffers()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkNotReleasedLocked();
        nativeAllocateBuffers(mNativeObject);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void attachAndQueueBuffer(GraphicBuffer graphicbuffer)
    {
        synchronized(mLock)
        {
            checkNotReleasedLocked();
            if(nativeAttachAndQueueBuffer(mNativeObject, graphicbuffer) != 0)
            {
                graphicbuffer = JVM INSTR new #247 <Class RuntimeException>;
                graphicbuffer.RuntimeException("Failed to attach and queue buffer to Surface (bad object?)");
                throw graphicbuffer;
            }
            break MISSING_BLOCK_LABEL_39;
        }
        obj;
        JVM INSTR monitorexit ;
    }

    public void copyFrom(SurfaceControl surfacecontrol)
    {
        long l;
        if(surfacecontrol == null)
            throw new IllegalArgumentException("other must not be null");
        l = surfacecontrol.mNativeObject;
        if(l == 0L)
            throw new NullPointerException("null SurfaceControl native object. Are you using a released SurfaceControl?");
        l = nativeGetFromSurfaceControl(l);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNativeObject != 0L)
            nativeRelease(mNativeObject);
        setNativeObjectLocked(l);
        obj;
        JVM INSTR monitorexit ;
        return;
        surfacecontrol;
        throw surfacecontrol;
    }

    public void createFrom(SurfaceControl surfacecontrol)
    {
        long l;
        if(surfacecontrol == null)
            throw new IllegalArgumentException("other must not be null");
        l = surfacecontrol.mNativeObject;
        if(l == 0L)
            throw new NullPointerException("null SurfaceControl native object. Are you using a released SurfaceControl?");
        l = nativeCreateFromSurfaceControl(l);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNativeObject != 0L)
            nativeRelease(mNativeObject);
        setNativeObjectLocked(l);
        obj;
        JVM INSTR monitorexit ;
        return;
        surfacecontrol;
        throw surfacecontrol;
    }

    public int describeContents()
    {
        return 0;
    }

    public void destroy()
    {
        release();
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        release();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    void forceScopedDisconnect()
    {
        synchronized(mLock)
        {
            checkNotReleasedLocked();
            if(nativeForceScopedDisconnect(mNativeObject) != 0)
            {
                RuntimeException runtimeexception = JVM INSTR new #247 <Class RuntimeException>;
                runtimeexception.RuntimeException("Failed to disconnect Surface instance (bad object?)");
                throw runtimeexception;
            }
            break MISSING_BLOCK_LABEL_39;
        }
        obj;
        JVM INSTR monitorexit ;
    }

    public int getGenerationId()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mGenerationId;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public long getNextFrameNumber()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        long l = nativeGetNextFrameNumber(mNativeObject);
        obj;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isAutoRefreshEnabled()
    {
        return mIsAutoRefreshEnabled;
    }

    public boolean isConsumerRunningBehind()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        checkNotReleasedLocked();
        flag = nativeIsConsumerRunningBehind(mNativeObject);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isSharedBufferModeEnabled()
    {
        return mIsSharedBufferModeEnabled;
    }

    public boolean isSingleBuffered()
    {
        return mIsSingleBuffered;
    }

    public boolean isValid()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        long l = mNativeObject;
        if(l != 0L)
            break MISSING_BLOCK_LABEL_22;
        obj;
        JVM INSTR monitorexit ;
        return false;
        boolean flag = nativeIsValid(mNativeObject);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public Canvas lockCanvas(Rect rect)
        throws OutOfResourcesException, IllegalArgumentException
    {
        synchronized(mLock)
        {
            checkNotReleasedLocked();
            if(mLockedObject != 0L)
            {
                rect = JVM INSTR new #109 <Class IllegalArgumentException>;
                rect.IllegalArgumentException("Surface was already locked");
                throw rect;
            }
            break MISSING_BLOCK_LABEL_38;
        }
        mLockedObject = nativeLockCanvas(mNativeObject, mCanvas, rect);
        rect = mCanvas;
        obj;
        JVM INSTR monitorexit ;
        return rect;
    }

    public Canvas lockHardwareCanvas()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Canvas canvas;
        checkNotReleasedLocked();
        if(mHwuiContext == null)
        {
            HwuiContext hwuicontext = JVM INSTR new #13  <Class Surface$HwuiContext>;
            hwuicontext.this. HwuiContext();
            mHwuiContext = hwuicontext;
        }
        canvas = mHwuiContext.lockCanvas(nativeGetWidth(mNativeObject), nativeGetHeight(mNativeObject));
        obj;
        JVM INSTR monitorexit ;
        return canvas;
        Exception exception;
        exception;
        throw exception;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag;
        flag = false;
        if(parcel == null)
            throw new IllegalArgumentException("source must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mName = parcel.readString();
        if(parcel.readInt() != 0)
            flag = true;
        mIsSingleBuffered = flag;
        setNativeObjectLocked(nativeReadFromParcel(mNativeObject, parcel));
        obj;
        JVM INSTR monitorexit ;
        return;
        parcel;
        throw parcel;
    }

    public void release()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNativeObject != 0L)
        {
            nativeRelease(mNativeObject);
            setNativeObjectLocked(0L);
        }
        if(mHwuiContext != null)
        {
            mHwuiContext.destroy();
            mHwuiContext = null;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setAutoRefreshEnabled(boolean flag)
    {
        if(mIsAutoRefreshEnabled != flag)
        {
            if(nativeSetAutoRefreshEnabled(mNativeObject, flag) != 0)
                throw new RuntimeException("Failed to set auto refresh on Surface (bad object?)");
            mIsAutoRefreshEnabled = flag;
        }
    }

    void setCompatibilityTranslator(android.content.res.CompatibilityInfo.Translator translator)
    {
        if(translator != null)
        {
            float f = translator.applicationScale;
            mCompatibleMatrix = new Matrix();
            mCompatibleMatrix.setScale(f, f);
        }
    }

    void setScalingMode(int i)
    {
        synchronized(mLock)
        {
            checkNotReleasedLocked();
            if(nativeSetScalingMode(mNativeObject, i) != 0)
            {
                IllegalArgumentException illegalargumentexception = JVM INSTR new #109 <Class IllegalArgumentException>;
                StringBuilder stringbuilder = JVM INSTR new #173 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalargumentexception.IllegalArgumentException(stringbuilder.append("Invalid scaling mode: ").append(i).toString());
                throw illegalargumentexception;
            }
            break MISSING_BLOCK_LABEL_64;
        }
        obj;
        JVM INSTR monitorexit ;
    }

    public void setSharedBufferModeEnabled(boolean flag)
    {
        if(mIsSharedBufferModeEnabled != flag)
        {
            if(nativeSetSharedBufferModeEnabled(mNativeObject, flag) != 0)
                throw new RuntimeException("Failed to set shared buffer mode on Surface (bad object?)");
            mIsSharedBufferModeEnabled = flag;
        }
    }

    public String toString()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = JVM INSTR new #173 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append("Surface(name=").append(mName).append(")/@0x").append(Integer.toHexString(System.identityHashCode(this))).toString();
        obj;
        JVM INSTR monitorexit ;
        return ((String) (obj1));
        Exception exception;
        exception;
        throw exception;
    }

    public void transferFrom(Surface surface)
    {
        if(surface == null)
            throw new IllegalArgumentException("other must not be null");
        if(surface == this) goto _L2; else goto _L1
_L1:
        Object obj = surface.mLock;
        obj;
        JVM INSTR monitorenter ;
        long l;
        l = surface.mNativeObject;
        surface.setNativeObjectLocked(0L);
        obj;
        JVM INSTR monitorexit ;
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNativeObject != 0L)
            nativeRelease(mNativeObject);
        setNativeObjectLocked(l);
        obj;
        JVM INSTR monitorexit ;
_L2:
        return;
        surface;
        throw surface;
        surface;
        throw surface;
    }

    public void unlockCanvas(Canvas canvas)
    {
        throw new UnsupportedOperationException();
    }

    public void unlockCanvasAndPost(Canvas canvas)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkNotReleasedLocked();
        if(mHwuiContext == null)
            break MISSING_BLOCK_LABEL_29;
        mHwuiContext.unlockAndPost(canvas);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        unlockSwCanvasAndPost(canvas);
          goto _L1
        canvas;
        throw canvas;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        j = 0;
        if(parcel == null)
            throw new IllegalArgumentException("dest must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        parcel.writeString(mName);
        if(mIsSingleBuffered)
            j = 1;
        parcel.writeInt(j);
        nativeWriteToParcel(mNativeObject, parcel);
        obj;
        JVM INSTR monitorexit ;
        if((i & 1) != 0)
            release();
        return;
        parcel;
        throw parcel;
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Surface createFromParcel(Parcel parcel)
        {
            Surface surface;
            try
            {
                surface = JVM INSTR new #9   <Class Surface>;
                surface.Surface();
                surface.readFromParcel(parcel);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                Log.e("Surface", "Exception creating surface from parcel", parcel);
                return null;
            }
            return surface;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Surface[] newArray(int i)
        {
            return new Surface[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    public static final int SCALING_MODE_FREEZE = 0;
    public static final int SCALING_MODE_NO_SCALE_CROP = 3;
    public static final int SCALING_MODE_SCALE_CROP = 2;
    public static final int SCALING_MODE_SCALE_TO_WINDOW = 1;
    private static final String TAG = "Surface";
    private final Canvas mCanvas;
    private final CloseGuard mCloseGuard;
    private Matrix mCompatibleMatrix;
    private int mGenerationId;
    private HwuiContext mHwuiContext;
    private boolean mIsAutoRefreshEnabled;
    private boolean mIsSharedBufferModeEnabled;
    private boolean mIsSingleBuffered;
    final Object mLock;
    private long mLockedObject;
    private String mName;
    long mNativeObject;

}
