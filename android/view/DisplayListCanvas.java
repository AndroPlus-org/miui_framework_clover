// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Bitmap;
import android.graphics.CanvasProperty;

// Referenced classes of package android.view:
//            RecordingCanvas, RenderNode, HardwareLayer

public final class DisplayListCanvas extends RecordingCanvas
{

    private DisplayListCanvas(RenderNode rendernode, int i, int j)
    {
        super(nCreateDisplayListCanvas(rendernode.mNativeRenderNode, i, j));
        mDensity = 0;
    }

    private static native void nCallDrawGLFunction(long l, long l1, Runnable runnable);

    private static native long nCreateDisplayListCanvas(long l, int i, int j);

    private static native void nDrawCircle(long l, long l1, long l2, long l3, 
            long l4);

    private static native void nDrawLayer(long l, long l1);

    private static native void nDrawRenderNode(long l, long l1);

    private static native void nDrawRoundRect(long l, long l1, long l2, long l3, 
            long l4, long l5, long l6, long l7);

    private static native long nFinishRecording(long l);

    private static native int nGetMaximumTextureHeight();

    private static native int nGetMaximumTextureWidth();

    private static native void nInsertReorderBarrier(long l, boolean flag);

    private static native void nResetDisplayListCanvas(long l, long l1, int i, int j);

    static DisplayListCanvas obtain(RenderNode rendernode, int i, int j)
    {
        if(rendernode == null)
            throw new IllegalArgumentException("node cannot be null");
        DisplayListCanvas displaylistcanvas = (DisplayListCanvas)sPool.acquire();
        if(displaylistcanvas == null)
            displaylistcanvas = new DisplayListCanvas(rendernode, i, j);
        else
            nResetDisplayListCanvas(displaylistcanvas.mNativeCanvasWrapper, rendernode.mNativeRenderNode, i, j);
        displaylistcanvas.mNode = rendernode;
        displaylistcanvas.mWidth = i;
        displaylistcanvas.mHeight = j;
        return displaylistcanvas;
    }

    public void callDrawGLFunction2(long l)
    {
        nCallDrawGLFunction(mNativeCanvasWrapper, l, null);
    }

    public void drawCircle(CanvasProperty canvasproperty, CanvasProperty canvasproperty1, CanvasProperty canvasproperty2, CanvasProperty canvasproperty3)
    {
        nDrawCircle(mNativeCanvasWrapper, canvasproperty.getNativeContainer(), canvasproperty1.getNativeContainer(), canvasproperty2.getNativeContainer(), canvasproperty3.getNativeContainer());
    }

    public void drawGLFunctor2(long l, Runnable runnable)
    {
        nCallDrawGLFunction(mNativeCanvasWrapper, l, runnable);
    }

    void drawHardwareLayer(HardwareLayer hardwarelayer)
    {
        nDrawLayer(mNativeCanvasWrapper, hardwarelayer.getLayerHandle());
    }

    public void drawRenderNode(RenderNode rendernode)
    {
        nDrawRenderNode(mNativeCanvasWrapper, rendernode.getNativeDisplayList());
    }

    public void drawRoundRect(CanvasProperty canvasproperty, CanvasProperty canvasproperty1, CanvasProperty canvasproperty2, CanvasProperty canvasproperty3, CanvasProperty canvasproperty4, CanvasProperty canvasproperty5, CanvasProperty canvasproperty6)
    {
        nDrawRoundRect(mNativeCanvasWrapper, canvasproperty.getNativeContainer(), canvasproperty1.getNativeContainer(), canvasproperty2.getNativeContainer(), canvasproperty3.getNativeContainer(), canvasproperty4.getNativeContainer(), canvasproperty5.getNativeContainer(), canvasproperty6.getNativeContainer());
    }

    long finishRecording()
    {
        return nFinishRecording(mNativeCanvasWrapper);
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getMaximumBitmapHeight()
    {
        return nGetMaximumTextureHeight();
    }

    public int getMaximumBitmapWidth()
    {
        return nGetMaximumTextureWidth();
    }

    public int getWidth()
    {
        return mWidth;
    }

    public void insertInorderBarrier()
    {
        nInsertReorderBarrier(mNativeCanvasWrapper, false);
    }

    public void insertReorderBarrier()
    {
        nInsertReorderBarrier(mNativeCanvasWrapper, true);
    }

    public boolean isHardwareAccelerated()
    {
        return true;
    }

    public boolean isOpaque()
    {
        return false;
    }

    public boolean isRecordingFor(Object obj)
    {
        boolean flag;
        if(obj == mNode)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void recycle()
    {
        mNode = null;
        sPool.release(this);
    }

    public void setBitmap(Bitmap bitmap)
    {
        throw new UnsupportedOperationException();
    }

    public void setDensity(int i)
    {
    }

    protected void throwIfCannotDraw(Bitmap bitmap)
    {
        super.throwIfCannotDraw(bitmap);
        int i = bitmap.getByteCount();
        if(i > 0x6400000)
            throw new RuntimeException((new StringBuilder()).append("Canvas: trying to draw too large(").append(i).append("bytes) bitmap.").toString());
        else
            return;
    }

    private static final int MAX_BITMAP_SIZE = 0x6400000;
    private static final int POOL_LIMIT = 25;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(25);
    private int mHeight;
    RenderNode mNode;
    private int mWidth;

}
