// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.*;
import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.view:
//            View, RenderNodeAnimator, ViewRootImpl, DisplayListCanvas, 
//            SurfaceView

public class RenderNode
{
    private static class NoImagePreloadHolder
    {

        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(android/view/RenderNode.getClassLoader(), RenderNode._2D_wrap0(), 1024L);


        private NoImagePreloadHolder()
        {
        }
    }


    static long _2D_wrap0()
    {
        return nGetNativeFinalizer();
    }

    private RenderNode(long l)
    {
        mNativeRenderNode = l;
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativeRenderNode);
        mOwningView = null;
    }

    private RenderNode(String s, View view)
    {
        mNativeRenderNode = nCreate(s);
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativeRenderNode);
        mOwningView = view;
    }

    public static RenderNode adopt(long l)
    {
        return new RenderNode(l);
    }

    public static RenderNode create(String s, View view)
    {
        return new RenderNode(s, view);
    }

    private static native void nAddAnimator(long l, long l1);

    private static native long nCreate(String s);

    private static native void nEndAllAnimators(long l);

    private static native float nGetAlpha(long l);

    private static native float nGetCameraDistance(long l);

    private static native boolean nGetClipToOutline(long l);

    private static native int nGetDebugSize(long l);

    private static native float nGetElevation(long l);

    private static native void nGetInverseTransformMatrix(long l, long l1);

    private static native long nGetNativeFinalizer();

    private static native float nGetPivotX(long l);

    private static native float nGetPivotY(long l);

    private static native float nGetRotation(long l);

    private static native float nGetRotationX(long l);

    private static native float nGetRotationY(long l);

    private static native float nGetScaleX(long l);

    private static native float nGetScaleY(long l);

    private static native void nGetTransformMatrix(long l, long l1);

    private static native float nGetTranslationX(long l);

    private static native float nGetTranslationY(long l);

    private static native float nGetTranslationZ(long l);

    private static native boolean nHasIdentityMatrix(long l);

    private static native boolean nHasOverlappingRendering(long l);

    private static native boolean nHasShadow(long l);

    private static native boolean nIsPivotExplicitlySet(long l);

    private static native boolean nIsValid(long l);

    private static native boolean nOffsetLeftAndRight(long l, int i);

    private static native boolean nOffsetTopAndBottom(long l, int i);

    private static native void nOutput(long l);

    private static native void nRequestPositionUpdates(long l, SurfaceView surfaceview);

    private static native boolean nSetAlpha(long l, float f);

    private static native boolean nSetAnimationMatrix(long l, long l1);

    private static native boolean nSetBottom(long l, int i);

    private static native boolean nSetCameraDistance(long l, float f);

    private static native boolean nSetClipBounds(long l, int i, int j, int k, int i1);

    private static native boolean nSetClipBoundsEmpty(long l);

    private static native boolean nSetClipToBounds(long l, boolean flag);

    private static native boolean nSetClipToOutline(long l, boolean flag);

    private static native void nSetDisplayList(long l, long l1);

    private static native boolean nSetElevation(long l, float f);

    private static native boolean nSetHasOverlappingRendering(long l, boolean flag);

    private static native boolean nSetLayerPaint(long l, long l1);

    private static native boolean nSetLayerType(long l, int i);

    private static native boolean nSetLeft(long l, int i);

    private static native boolean nSetLeftTopRightBottom(long l, int i, int j, int k, int i1);

    private static native boolean nSetOutlineConvexPath(long l, long l1, float f);

    private static native boolean nSetOutlineEmpty(long l);

    private static native boolean nSetOutlineNone(long l);

    private static native boolean nSetOutlineRoundRect(long l, int i, int j, int k, int i1, float f, float f1);

    private static native boolean nSetPivotX(long l, float f);

    private static native boolean nSetPivotY(long l, float f);

    private static native boolean nSetProjectBackwards(long l, boolean flag);

    private static native boolean nSetProjectionReceiver(long l, boolean flag);

    private static native boolean nSetRevealClip(long l, boolean flag, float f, float f1, float f2);

    private static native boolean nSetRight(long l, int i);

    private static native boolean nSetRotation(long l, float f);

    private static native boolean nSetRotationX(long l, float f);

    private static native boolean nSetRotationY(long l, float f);

    private static native boolean nSetScaleX(long l, float f);

    private static native boolean nSetScaleY(long l, float f);

    private static native boolean nSetStaticMatrix(long l, long l1);

    private static native boolean nSetTop(long l, int i);

    private static native boolean nSetTranslationX(long l, float f);

    private static native boolean nSetTranslationY(long l, float f);

    private static native boolean nSetTranslationZ(long l, float f);

    public void addAnimator(RenderNodeAnimator rendernodeanimator)
    {
        if(mOwningView == null || mOwningView.mAttachInfo == null)
        {
            throw new IllegalStateException("Cannot start this animator on a detached view!");
        } else
        {
            nAddAnimator(mNativeRenderNode, rendernodeanimator.getNativeAnimator());
            mOwningView.mAttachInfo.mViewRootImpl.registerAnimatingRenderNode(this);
            return;
        }
    }

    public void destroy()
    {
    }

    public void discardDisplayList()
    {
        nSetDisplayList(mNativeRenderNode, 0L);
    }

    public void end(DisplayListCanvas displaylistcanvas)
    {
        long l = displaylistcanvas.finishRecording();
        nSetDisplayList(mNativeRenderNode, l);
        displaylistcanvas.recycle();
    }

    public void endAllAnimators()
    {
        nEndAllAnimators(mNativeRenderNode);
    }

    public float getAlpha()
    {
        return nGetAlpha(mNativeRenderNode);
    }

    public float getCameraDistance()
    {
        return nGetCameraDistance(mNativeRenderNode);
    }

    public boolean getClipToOutline()
    {
        return nGetClipToOutline(mNativeRenderNode);
    }

    public int getDebugSize()
    {
        return nGetDebugSize(mNativeRenderNode);
    }

    public float getElevation()
    {
        return nGetElevation(mNativeRenderNode);
    }

    public void getInverseMatrix(Matrix matrix)
    {
        nGetInverseTransformMatrix(mNativeRenderNode, matrix.native_instance);
    }

    public void getMatrix(Matrix matrix)
    {
        nGetTransformMatrix(mNativeRenderNode, matrix.native_instance);
    }

    long getNativeDisplayList()
    {
        if(!isValid())
            throw new IllegalStateException("The display list is not valid.");
        else
            return mNativeRenderNode;
    }

    public float getPivotX()
    {
        return nGetPivotX(mNativeRenderNode);
    }

    public float getPivotY()
    {
        return nGetPivotY(mNativeRenderNode);
    }

    public float getRotation()
    {
        return nGetRotation(mNativeRenderNode);
    }

    public float getRotationX()
    {
        return nGetRotationX(mNativeRenderNode);
    }

    public float getRotationY()
    {
        return nGetRotationY(mNativeRenderNode);
    }

    public float getScaleX()
    {
        return nGetScaleX(mNativeRenderNode);
    }

    public float getScaleY()
    {
        return nGetScaleY(mNativeRenderNode);
    }

    public float getTranslationX()
    {
        return nGetTranslationX(mNativeRenderNode);
    }

    public float getTranslationY()
    {
        return nGetTranslationY(mNativeRenderNode);
    }

    public float getTranslationZ()
    {
        return nGetTranslationZ(mNativeRenderNode);
    }

    public boolean hasIdentityMatrix()
    {
        return nHasIdentityMatrix(mNativeRenderNode);
    }

    public boolean hasOverlappingRendering()
    {
        return nHasOverlappingRendering(mNativeRenderNode);
    }

    public boolean hasShadow()
    {
        return nHasShadow(mNativeRenderNode);
    }

    public boolean isAttached()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mOwningView != null)
        {
            flag1 = flag;
            if(mOwningView.mAttachInfo != null)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isPivotExplicitlySet()
    {
        return nIsPivotExplicitlySet(mNativeRenderNode);
    }

    public boolean isValid()
    {
        return nIsValid(mNativeRenderNode);
    }

    public boolean offsetLeftAndRight(int i)
    {
        return nOffsetLeftAndRight(mNativeRenderNode, i);
    }

    public boolean offsetTopAndBottom(int i)
    {
        return nOffsetTopAndBottom(mNativeRenderNode, i);
    }

    public void output()
    {
        nOutput(mNativeRenderNode);
    }

    public void registerVectorDrawableAnimator(android.graphics.drawable.AnimatedVectorDrawable.VectorDrawableAnimatorRT vectordrawableanimatorrt)
    {
        if(mOwningView == null || mOwningView.mAttachInfo == null)
        {
            throw new IllegalStateException("Cannot start this animator on a detached view!");
        } else
        {
            mOwningView.mAttachInfo.mViewRootImpl.registerVectorDrawableAnimator(vectordrawableanimatorrt);
            return;
        }
    }

    public void requestPositionUpdates(SurfaceView surfaceview)
    {
        nRequestPositionUpdates(mNativeRenderNode, surfaceview);
    }

    public boolean setAlpha(float f)
    {
        return nSetAlpha(mNativeRenderNode, f);
    }

    public boolean setAnimationMatrix(Matrix matrix)
    {
        long l = mNativeRenderNode;
        long l1;
        if(matrix != null)
            l1 = matrix.native_instance;
        else
            l1 = 0L;
        return nSetAnimationMatrix(l, l1);
    }

    public boolean setBottom(int i)
    {
        return nSetBottom(mNativeRenderNode, i);
    }

    public boolean setCameraDistance(float f)
    {
        return nSetCameraDistance(mNativeRenderNode, f);
    }

    public boolean setClipBounds(Rect rect)
    {
        if(rect == null)
            return nSetClipBoundsEmpty(mNativeRenderNode);
        else
            return nSetClipBounds(mNativeRenderNode, rect.left, rect.top, rect.right, rect.bottom);
    }

    public boolean setClipToBounds(boolean flag)
    {
        return nSetClipToBounds(mNativeRenderNode, flag);
    }

    public boolean setClipToOutline(boolean flag)
    {
        return nSetClipToOutline(mNativeRenderNode, flag);
    }

    public boolean setElevation(float f)
    {
        return nSetElevation(mNativeRenderNode, f);
    }

    public boolean setHasOverlappingRendering(boolean flag)
    {
        return nSetHasOverlappingRendering(mNativeRenderNode, flag);
    }

    public boolean setLayerPaint(Paint paint)
    {
        long l = mNativeRenderNode;
        long l1;
        if(paint != null)
            l1 = paint.getNativeInstance();
        else
            l1 = 0L;
        return nSetLayerPaint(l, l1);
    }

    public boolean setLayerType(int i)
    {
        return nSetLayerType(mNativeRenderNode, i);
    }

    public boolean setLeft(int i)
    {
        return nSetLeft(mNativeRenderNode, i);
    }

    public boolean setLeftTopRightBottom(int i, int j, int k, int l)
    {
        return nSetLeftTopRightBottom(mNativeRenderNode, i, j, k, l);
    }

    public boolean setOutline(Outline outline)
    {
        if(outline == null)
            return nSetOutlineNone(mNativeRenderNode);
        switch(outline.mMode)
        {
        default:
            throw new IllegalArgumentException("Unrecognized outline?");

        case 0: // '\0'
            return nSetOutlineEmpty(mNativeRenderNode);

        case 1: // '\001'
            return nSetOutlineRoundRect(mNativeRenderNode, outline.mRect.left, outline.mRect.top, outline.mRect.right, outline.mRect.bottom, outline.mRadius, outline.mAlpha);

        case 2: // '\002'
            return nSetOutlineConvexPath(mNativeRenderNode, outline.mPath.mNativePath, outline.mAlpha);
        }
    }

    public boolean setPivotX(float f)
    {
        return nSetPivotX(mNativeRenderNode, f);
    }

    public boolean setPivotY(float f)
    {
        return nSetPivotY(mNativeRenderNode, f);
    }

    public boolean setProjectBackwards(boolean flag)
    {
        return nSetProjectBackwards(mNativeRenderNode, flag);
    }

    public boolean setProjectionReceiver(boolean flag)
    {
        return nSetProjectionReceiver(mNativeRenderNode, flag);
    }

    public boolean setRevealClip(boolean flag, float f, float f1, float f2)
    {
        return nSetRevealClip(mNativeRenderNode, flag, f, f1, f2);
    }

    public boolean setRight(int i)
    {
        return nSetRight(mNativeRenderNode, i);
    }

    public boolean setRotation(float f)
    {
        return nSetRotation(mNativeRenderNode, f);
    }

    public boolean setRotationX(float f)
    {
        return nSetRotationX(mNativeRenderNode, f);
    }

    public boolean setRotationY(float f)
    {
        return nSetRotationY(mNativeRenderNode, f);
    }

    public boolean setScaleX(float f)
    {
        return nSetScaleX(mNativeRenderNode, f);
    }

    public boolean setScaleY(float f)
    {
        return nSetScaleY(mNativeRenderNode, f);
    }

    public boolean setStaticMatrix(Matrix matrix)
    {
        return nSetStaticMatrix(mNativeRenderNode, matrix.native_instance);
    }

    public boolean setTop(int i)
    {
        return nSetTop(mNativeRenderNode, i);
    }

    public boolean setTranslationX(float f)
    {
        return nSetTranslationX(mNativeRenderNode, f);
    }

    public boolean setTranslationY(float f)
    {
        return nSetTranslationY(mNativeRenderNode, f);
    }

    public boolean setTranslationZ(float f)
    {
        return nSetTranslationZ(mNativeRenderNode, f);
    }

    public DisplayListCanvas start(int i, int j)
    {
        return DisplayListCanvas.obtain(this, i, j);
    }

    final long mNativeRenderNode;
    private final View mOwningView;
}
