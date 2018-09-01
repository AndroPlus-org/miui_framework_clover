// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.*;
import com.android.internal.util.VirtualRefBasePtr;

// Referenced classes of package android.view:
//            ThreadedRenderer

final class HardwareLayer
{

    private HardwareLayer(ThreadedRenderer threadedrenderer, long l)
    {
        if(threadedrenderer == null || l == 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Either hardware renderer: ").append(threadedrenderer).append(" or deferredUpdater: ").append(l).append(" is invalid").toString());
        } else
        {
            mRenderer = threadedrenderer;
            mFinalizer = new VirtualRefBasePtr(l);
            return;
        }
    }

    static HardwareLayer adoptTextureLayer(ThreadedRenderer threadedrenderer, long l)
    {
        return new HardwareLayer(threadedrenderer, l);
    }

    private static native boolean nPrepare(long l, int i, int j, boolean flag);

    private static native void nSetLayerPaint(long l, long l1);

    private static native void nSetSurfaceTexture(long l, SurfaceTexture surfacetexture);

    private static native void nSetTransform(long l, long l1);

    private static native void nUpdateSurfaceTexture(long l);

    public boolean copyInto(Bitmap bitmap)
    {
        return mRenderer.copyLayerInto(this, bitmap);
    }

    public void destroy()
    {
        if(!isValid())
        {
            return;
        } else
        {
            mRenderer.onLayerDestroyed(this);
            mRenderer = null;
            mFinalizer.release();
            mFinalizer = null;
            return;
        }
    }

    public void detachSurfaceTexture()
    {
        mRenderer.detachSurfaceTexture(mFinalizer.get());
    }

    public long getDeferredLayerUpdater()
    {
        return mFinalizer.get();
    }

    public long getLayerHandle()
    {
        return mFinalizer.get();
    }

    public boolean isValid()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mFinalizer != null)
        {
            flag1 = flag;
            if(mFinalizer.get() != 0L)
                flag1 = true;
        }
        return flag1;
    }

    public boolean prepare(int i, int j, boolean flag)
    {
        return nPrepare(mFinalizer.get(), i, j, flag);
    }

    public void setLayerPaint(Paint paint)
    {
        long l = mFinalizer.get();
        long l1;
        if(paint != null)
            l1 = paint.getNativeInstance();
        else
            l1 = 0L;
        nSetLayerPaint(l, l1);
        mRenderer.pushLayerUpdate(this);
    }

    public void setSurfaceTexture(SurfaceTexture surfacetexture)
    {
        nSetSurfaceTexture(mFinalizer.get(), surfacetexture);
        mRenderer.pushLayerUpdate(this);
    }

    public void setTransform(Matrix matrix)
    {
        nSetTransform(mFinalizer.get(), matrix.native_instance);
        mRenderer.pushLayerUpdate(this);
    }

    public void updateSurfaceTexture()
    {
        nUpdateSurfaceTexture(mFinalizer.get());
        mRenderer.pushLayerUpdate(this);
    }

    private VirtualRefBasePtr mFinalizer;
    private ThreadedRenderer mRenderer;
}
