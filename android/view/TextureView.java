// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

// Referenced classes of package android.view:
//            View, HardwareLayer, DisplayListCanvas, ThreadedRenderer

public class TextureView extends View
{
    public static interface SurfaceTextureListener
    {

        public abstract void onSurfaceTextureAvailable(SurfaceTexture surfacetexture, int i, int j);

        public abstract boolean onSurfaceTextureDestroyed(SurfaceTexture surfacetexture);

        public abstract void onSurfaceTextureSizeChanged(SurfaceTexture surfacetexture, int i, int j);

        public abstract void onSurfaceTextureUpdated(SurfaceTexture surfacetexture);
    }


    static void _2D_wrap0(TextureView textureview)
    {
        textureview.updateLayer();
    }

    public TextureView(Context context)
    {
        super(context);
        mOpaque = true;
        mMatrix = new Matrix();
        mLock = new Object[0];
        mNativeWindowLock = new Object[0];
        mUpdateListener = new android.graphics.SurfaceTexture.OnFrameAvailableListener() {

            public void onFrameAvailable(SurfaceTexture surfacetexture)
            {
                TextureView._2D_wrap0(TextureView.this);
                invalidate();
            }

            final TextureView this$0;

            
            {
                this$0 = TextureView.this;
                super();
            }
        }
;
    }

    public TextureView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mOpaque = true;
        mMatrix = new Matrix();
        mLock = new Object[0];
        mNativeWindowLock = new Object[0];
        mUpdateListener = new _cls1();
    }

    public TextureView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mOpaque = true;
        mMatrix = new Matrix();
        mLock = new Object[0];
        mNativeWindowLock = new Object[0];
        mUpdateListener = new _cls1();
    }

    public TextureView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mOpaque = true;
        mMatrix = new Matrix();
        mLock = new Object[0];
        mNativeWindowLock = new Object[0];
        mUpdateListener = new _cls1();
    }

    private void applyTransformMatrix()
    {
        if(mMatrixChanged && mLayer != null)
        {
            mLayer.setTransform(mMatrix);
            mMatrixChanged = false;
        }
    }

    private void applyUpdate()
    {
        if(mLayer == null)
            return;
        Object aobj[] = mLock;
        aobj;
        JVM INSTR monitorenter ;
        if(!mUpdateLayer)
            break MISSING_BLOCK_LABEL_77;
        mUpdateLayer = false;
        aobj;
        JVM INSTR monitorexit ;
        mLayer.prepare(getWidth(), getHeight(), mOpaque);
        mLayer.updateSurfaceTexture();
        if(mListener != null)
            mListener.onSurfaceTextureUpdated(mSurface);
        return;
        aobj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void destroyHardwareLayer()
    {
        if(mLayer != null)
        {
            mLayer.detachSurfaceTexture();
            mLayer.destroy();
            mLayer = null;
            mMatrixChanged = true;
        }
    }

    private native void nCreateNativeWindow(SurfaceTexture surfacetexture);

    private native void nDestroyNativeWindow();

    private static native boolean nLockCanvas(long l, Canvas canvas, Rect rect);

    private static native void nUnlockCanvasAndPost(long l, Canvas canvas);

    private void releaseSurfaceTexture()
    {
        boolean flag;
        if(mSurface == null)
            break MISSING_BLOCK_LABEL_64;
        flag = true;
        if(mListener != null)
            flag = mListener.onSurfaceTextureDestroyed(mSurface);
        Object aobj[] = mNativeWindowLock;
        aobj;
        JVM INSTR monitorenter ;
        nDestroyNativeWindow();
        aobj;
        JVM INSTR monitorexit ;
        if(flag)
            mSurface.release();
        mSurface = null;
        mHadSurface = true;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void updateLayer()
    {
        Object aobj[] = mLock;
        aobj;
        JVM INSTR monitorenter ;
        mUpdateLayer = true;
        aobj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void updateLayerAndInvalidate()
    {
        Object aobj[] = mLock;
        aobj;
        JVM INSTR monitorenter ;
        mUpdateLayer = true;
        aobj;
        JVM INSTR monitorexit ;
        invalidate();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void buildLayer()
    {
    }

    protected void destroyHardwareResources()
    {
        super.destroyHardwareResources();
        destroyHardwareLayer();
    }

    public final void draw(Canvas canvas)
    {
        mPrivateFlags = mPrivateFlags & 0xff9fffff | 0x20;
        if(canvas.isHardwareAccelerated())
        {
            canvas = (DisplayListCanvas)canvas;
            HardwareLayer hardwarelayer = getHardwareLayer();
            if(hardwarelayer != null)
            {
                applyUpdate();
                applyTransformMatrix();
                mLayer.setLayerPaint(mLayerPaint);
                canvas.drawHardwareLayer(hardwarelayer);
            }
        }
    }

    public Bitmap getBitmap()
    {
        return getBitmap(getWidth(), getHeight());
    }

    public Bitmap getBitmap(int i, int j)
    {
        if(isAvailable() && i > 0 && j > 0)
            return getBitmap(Bitmap.createBitmap(getResources().getDisplayMetrics(), i, j, android.graphics.Bitmap.Config.ARGB_8888));
        else
            return null;
    }

    public Bitmap getBitmap(Bitmap bitmap)
    {
        if(bitmap != null && isAvailable())
        {
            applyUpdate();
            applyTransformMatrix();
            if(mLayer == null && mUpdateSurface)
                getHardwareLayer();
            if(mLayer != null)
                mLayer.copyInto(bitmap);
        }
        return bitmap;
    }

    HardwareLayer getHardwareLayer()
    {
        if(mLayer == null)
        {
            if(mAttachInfo == null || mAttachInfo.mThreadedRenderer == null)
                return null;
            mLayer = mAttachInfo.mThreadedRenderer.createTextureLayer();
            boolean flag;
            if(mSurface == null)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                mSurface = new SurfaceTexture(false);
                nCreateNativeWindow(mSurface);
            }
            mLayer.setSurfaceTexture(mSurface);
            mSurface.setDefaultBufferSize(getWidth(), getHeight());
            mSurface.setOnFrameAvailableListener(mUpdateListener, mAttachInfo.mHandler);
            if(mListener != null && flag)
                mListener.onSurfaceTextureAvailable(mSurface, getWidth(), getHeight());
            mLayer.setLayerPaint(mLayerPaint);
        }
        if(mUpdateSurface)
        {
            mUpdateSurface = false;
            updateLayer();
            mMatrixChanged = true;
            mLayer.setSurfaceTexture(mSurface);
            mSurface.setDefaultBufferSize(getWidth(), getHeight());
        }
        return mLayer;
    }

    public int getLayerType()
    {
        return 2;
    }

    public SurfaceTexture getSurfaceTexture()
    {
        return mSurface;
    }

    public SurfaceTextureListener getSurfaceTextureListener()
    {
        return mListener;
    }

    public Matrix getTransform(Matrix matrix)
    {
        Matrix matrix1 = matrix;
        if(matrix == null)
            matrix1 = new Matrix();
        matrix1.set(mMatrix);
        return matrix1;
    }

    public boolean isAvailable()
    {
        boolean flag;
        if(mSurface != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isOpaque()
    {
        return mOpaque;
    }

    public Canvas lockCanvas()
    {
        return lockCanvas(null);
    }

    public Canvas lockCanvas(Rect rect)
    {
        if(!isAvailable())
            return null;
        if(mCanvas == null)
            mCanvas = new Canvas();
        Object aobj[] = mNativeWindowLock;
        aobj;
        JVM INSTR monitorenter ;
        boolean flag = nLockCanvas(mNativeWindow, mCanvas, rect);
        if(flag)
            break MISSING_BLOCK_LABEL_55;
        aobj;
        JVM INSTR monitorexit ;
        return null;
        aobj;
        JVM INSTR monitorexit ;
        mSaveCount = mCanvas.save();
        return mCanvas;
        rect;
        throw rect;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(!isHardwareAccelerated())
            Log.w("TextureView", "A TextureView or a subclass can only be used with hardware acceleration enabled.");
        if(mHadSurface)
        {
            invalidate(true);
            mHadSurface = false;
        }
    }

    protected void onDetachedFromWindowInternal()
    {
        destroyHardwareLayer();
        releaseSurfaceTexture();
        super.onDetachedFromWindowInternal();
    }

    protected final void onDraw(Canvas canvas)
    {
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if(mSurface != null)
        {
            mSurface.setDefaultBufferSize(getWidth(), getHeight());
            updateLayer();
            if(mListener != null)
                mListener.onSurfaceTextureSizeChanged(mSurface, getWidth(), getHeight());
        }
    }

    protected void onVisibilityChanged(View view, int i)
    {
        super.onVisibilityChanged(view, i);
        if(mSurface != null)
            if(i == 0)
            {
                if(mLayer != null)
                    mSurface.setOnFrameAvailableListener(mUpdateListener, mAttachInfo.mHandler);
                updateLayerAndInvalidate();
            } else
            {
                mSurface.setOnFrameAvailableListener(null);
            }
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        if(drawable != null && sTextureViewIgnoresDrawableSetters ^ true)
            throw new UnsupportedOperationException("TextureView doesn't support displaying a background drawable");
        else
            return;
    }

    public void setForeground(Drawable drawable)
    {
        if(drawable != null && sTextureViewIgnoresDrawableSetters ^ true)
            throw new UnsupportedOperationException("TextureView doesn't support displaying a foreground drawable");
        else
            return;
    }

    public void setLayerPaint(Paint paint)
    {
        if(paint != mLayerPaint)
        {
            mLayerPaint = paint;
            invalidate();
        }
    }

    public void setLayerType(int i, Paint paint)
    {
        setLayerPaint(paint);
    }

    public void setOpaque(boolean flag)
    {
        if(flag != mOpaque)
        {
            mOpaque = flag;
            if(mLayer != null)
                updateLayerAndInvalidate();
        }
    }

    public void setSurfaceTexture(SurfaceTexture surfacetexture)
    {
        if(surfacetexture == null)
            throw new NullPointerException("surfaceTexture must not be null");
        if(surfacetexture == mSurface)
            throw new IllegalArgumentException("Trying to setSurfaceTexture to the same SurfaceTexture that's already set.");
        if(surfacetexture.isReleased())
            throw new IllegalArgumentException("Cannot setSurfaceTexture to a released SurfaceTexture");
        if(mSurface != null)
        {
            nDestroyNativeWindow();
            mSurface.release();
        }
        mSurface = surfacetexture;
        nCreateNativeWindow(mSurface);
        if((mViewFlags & 0xc) == 0 && mLayer != null)
            mSurface.setOnFrameAvailableListener(mUpdateListener, mAttachInfo.mHandler);
        mUpdateSurface = true;
        invalidateParentIfNeeded();
    }

    public void setSurfaceTextureListener(SurfaceTextureListener surfacetexturelistener)
    {
        mListener = surfacetexturelistener;
    }

    public void setTransform(Matrix matrix)
    {
        mMatrix.set(matrix);
        mMatrixChanged = true;
        invalidateParentIfNeeded();
    }

    public void unlockCanvasAndPost(Canvas canvas)
    {
        if(mCanvas == null || canvas != mCanvas) goto _L2; else goto _L1
_L1:
        canvas.restoreToCount(mSaveCount);
        mSaveCount = 0;
        Object aobj[] = mNativeWindowLock;
        aobj;
        JVM INSTR monitorenter ;
        nUnlockCanvasAndPost(mNativeWindow, mCanvas);
        aobj;
        JVM INSTR monitorexit ;
_L2:
        return;
        canvas;
        throw canvas;
    }

    private static final String LOG_TAG = "TextureView";
    private Canvas mCanvas;
    private boolean mHadSurface;
    private HardwareLayer mLayer;
    private SurfaceTextureListener mListener;
    private final Object mLock[];
    private final Matrix mMatrix;
    private boolean mMatrixChanged;
    private long mNativeWindow;
    private final Object mNativeWindowLock[];
    private boolean mOpaque;
    private int mSaveCount;
    private SurfaceTexture mSurface;
    private boolean mUpdateLayer;
    private final android.graphics.SurfaceTexture.OnFrameAvailableListener mUpdateListener;
    private boolean mUpdateSurface;
}
