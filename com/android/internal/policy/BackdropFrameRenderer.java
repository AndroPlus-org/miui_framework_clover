// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.*;

// Referenced classes of package com.android.internal.policy:
//            DecorView

public class BackdropFrameRenderer extends Thread
    implements android.view.Choreographer.FrameCallback
{

    public BackdropFrameRenderer(DecorView decorview, ThreadedRenderer threadedrenderer, Rect rect, Drawable drawable, Drawable drawable1, Drawable drawable2, int i, 
            int j, boolean flag, Rect rect1, Rect rect2, int k)
    {
        setName("ResizeFrame");
        mRenderer = threadedrenderer;
        onResourcesLoaded(decorview, drawable, drawable1, drawable2, i, j);
        mFrameAndBackdropNode = RenderNode.create("FrameAndBackdropNode", null);
        mRenderer.addRenderNode(mFrameAndBackdropNode, true);
        mTargetRect.set(rect);
        mFullscreen = flag;
        mOldFullscreen = flag;
        mSystemInsets.set(rect1);
        mStableInsets.set(rect2);
        mOldSystemInsets.set(rect1);
        mOldStableInsets.set(rect2);
        mResizeMode = k;
        start();
    }

    private void addSystemBarNodeIfNeeded()
    {
        if(mSystemBarBackgroundNode != null)
        {
            return;
        } else
        {
            mSystemBarBackgroundNode = RenderNode.create("SystemBarBackgroundNode", null);
            mRenderer.addRenderNode(mSystemBarBackgroundNode, false);
            return;
        }
    }

    private void doFrameUncheckedLocked()
    {
        mNewTargetRect.set(mTargetRect);
        break MISSING_BLOCK_LABEL_11;
        if(!mNewTargetRect.equals(mOldTargetRect) || mOldFullscreen != mFullscreen || mStableInsets.equals(mOldStableInsets) ^ true || mSystemInsets.equals(mOldSystemInsets) ^ true || mReportNextDraw)
        {
            mOldFullscreen = mFullscreen;
            mOldTargetRect.set(mNewTargetRect);
            mOldSystemInsets.set(mSystemInsets);
            mOldStableInsets.set(mStableInsets);
            redrawLocked(mNewTargetRect, mFullscreen, mSystemInsets, mStableInsets);
        }
        return;
    }

    private void drawColorViews(int i, int j, int k, int l, boolean flag, Rect rect, Rect rect1)
    {
        if(mSystemBarBackgroundNode == null)
            return;
        android.view.DisplayListCanvas displaylistcanvas = mSystemBarBackgroundNode.start(k, l);
        mSystemBarBackgroundNode.setLeftTopRightBottom(i, j, i + k, j + l);
        j = DecorView.getColorViewTopInset(mStableInsets.top, mSystemInsets.top);
        if(mStatusBarColor != null)
        {
            mStatusBarColor.setBounds(0, 0, i + k, j);
            mStatusBarColor.draw(displaylistcanvas);
        }
        if(mNavigationBarColor != null && flag)
        {
            DecorView.getNavigationBarRect(k, l, rect1, rect, mTmpRect);
            mNavigationBarColor.setBounds(mTmpRect);
            mNavigationBarColor.draw(displaylistcanvas);
        }
        mSystemBarBackgroundNode.end(displaylistcanvas);
        mRenderer.drawRenderNode(mSystemBarBackgroundNode);
    }

    private void pingRenderLocked(boolean flag)
    {
        if(mChoreographer != null && flag ^ true)
            mChoreographer.postFrameCallback(this);
        else
            doFrameUncheckedLocked();
    }

    private void redrawLocked(Rect rect, boolean flag, Rect rect1, Rect rect2)
    {
        int i = mDecorView.getCaptionHeight();
        if(i != 0)
            mLastCaptionHeight = i;
        while(mLastCaptionHeight == 0 && mDecorView.isShowingCaption() || mLastContentWidth == 0 || mLastContentHeight == 0) 
            return;
        int j = mLastXOffset + rect.left;
        int k = mLastYOffset + rect.top;
        i = rect.width();
        int l = rect.height();
        mFrameAndBackdropNode.setLeftTopRightBottom(j, k, j + i, k + l);
        android.view.DisplayListCanvas displaylistcanvas = mFrameAndBackdropNode.start(i, l);
        if(mUserCaptionBackgroundDrawable != null)
            rect = mUserCaptionBackgroundDrawable;
        else
            rect = mCaptionBackgroundDrawable;
        if(rect != null)
        {
            rect.setBounds(0, 0, j + i, mLastCaptionHeight + k);
            rect.draw(displaylistcanvas);
        }
        if(mResizingBackgroundDrawable != null)
        {
            mResizingBackgroundDrawable.setBounds(0, mLastCaptionHeight, j + i, k + l);
            mResizingBackgroundDrawable.draw(displaylistcanvas);
        }
        mFrameAndBackdropNode.end(displaylistcanvas);
        drawColorViews(j, k, i, l, flag, rect1, rect2);
        mRenderer.drawRenderNode(mFrameAndBackdropNode);
        reportDrawIfNeeded();
    }

    private void reportDrawIfNeeded()
    {
        if(mReportNextDraw)
        {
            if(mDecorView.isAttachedToWindow())
                mDecorView.getViewRootImpl().reportDrawFinish();
            mReportNextDraw = false;
        }
    }

    public void doFrame(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(mRenderer != null)
            break MISSING_BLOCK_LABEL_22;
        reportDrawIfNeeded();
        Looper.myLooper().quit();
        this;
        JVM INSTR monitorexit ;
        return;
        doFrameUncheckedLocked();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onConfigurationChange()
    {
        this;
        JVM INSTR monitorenter ;
        if(mRenderer != null)
        {
            mOldTargetRect.set(0, 0, 0, 0);
            pingRenderLocked(false);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean onContentDrawn(int i, int j, int k, int l)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        boolean flag1;
        if(mLastContentWidth == 0)
            flag1 = true;
        else
            flag1 = false;
        mLastContentWidth = k;
        mLastContentHeight = l - mLastCaptionHeight;
        mLastXOffset = i;
        mLastYOffset = j;
        mRenderer.setContentDrawBounds(mLastXOffset, mLastYOffset, mLastXOffset + mLastContentWidth, mLastYOffset + mLastCaptionHeight + mLastContentHeight);
        if(!flag1) goto _L2; else goto _L1
_L1:
        if(mLastCaptionHeight != 0) goto _L4; else goto _L3
_L3:
        flag = mDecorView.isShowingCaption();
        flag ^= true;
_L2:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L4:
        flag = true;
        if(true) goto _L2; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public void onRequestDraw(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mReportNextDraw = flag;
        mOldTargetRect.set(0, 0, 0, 0);
        pingRenderLocked(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void onResourcesLoaded(DecorView decorview, Drawable drawable, Drawable drawable1, Drawable drawable2, int i, int j)
    {
        mDecorView = decorview;
        if(drawable != null && drawable.getConstantState() != null)
            decorview = drawable.getConstantState().newDrawable();
        else
            decorview = null;
        mResizingBackgroundDrawable = decorview;
        if(drawable1 != null && drawable1.getConstantState() != null)
            decorview = drawable1.getConstantState().newDrawable();
        else
            decorview = null;
        mCaptionBackgroundDrawable = decorview;
        if(drawable2 != null && drawable2.getConstantState() != null)
            decorview = drawable2.getConstantState().newDrawable();
        else
            decorview = null;
        mUserCaptionBackgroundDrawable = decorview;
        if(mCaptionBackgroundDrawable == null)
            mCaptionBackgroundDrawable = mResizingBackgroundDrawable;
        if(i != 0)
        {
            mStatusBarColor = new ColorDrawable(i);
            addSystemBarNodeIfNeeded();
        } else
        {
            mStatusBarColor = null;
        }
        if(j != 0)
        {
            mNavigationBarColor = new ColorDrawable(j);
            addSystemBarNodeIfNeeded();
        } else
        {
            mNavigationBarColor = null;
        }
    }

    public void releaseRenderer()
    {
        this;
        JVM INSTR monitorenter ;
        if(mRenderer != null)
        {
            mRenderer.setContentDrawBounds(0, 0, 0, 0);
            mRenderer.removeRenderNode(mFrameAndBackdropNode);
            if(mSystemBarBackgroundNode != null)
                mRenderer.removeRenderNode(mSystemBarBackgroundNode);
            mRenderer = null;
            pingRenderLocked(false);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void run()
    {
        Looper.prepare();
        this;
        JVM INSTR monitorenter ;
        mChoreographer = Choreographer.getInstance();
        this;
        JVM INSTR monitorexit ;
        Looper.loop();
        releaseRenderer();
        this;
        JVM INSTR monitorenter ;
        mChoreographer = null;
        Choreographer.releaseInstance();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        releaseRenderer();
        throw exception;
        exception;
        throw exception;
    }

    public void setTargetRect(Rect rect, boolean flag, Rect rect1, Rect rect2)
    {
        this;
        JVM INSTR monitorenter ;
        mFullscreen = flag;
        mTargetRect.set(rect);
        mSystemInsets.set(rect1);
        mStableInsets.set(rect2);
        pingRenderLocked(false);
        this;
        JVM INSTR monitorexit ;
        return;
        rect;
        throw rect;
    }

    void setUserCaptionBackgroundDrawable(Drawable drawable)
    {
        mUserCaptionBackgroundDrawable = drawable;
    }

    private Drawable mCaptionBackgroundDrawable;
    private Choreographer mChoreographer;
    private DecorView mDecorView;
    private RenderNode mFrameAndBackdropNode;
    private boolean mFullscreen;
    private int mLastCaptionHeight;
    private int mLastContentHeight;
    private int mLastContentWidth;
    private int mLastXOffset;
    private int mLastYOffset;
    private ColorDrawable mNavigationBarColor;
    private final Rect mNewTargetRect = new Rect();
    private boolean mOldFullscreen;
    private final Rect mOldStableInsets = new Rect();
    private final Rect mOldSystemInsets = new Rect();
    private final Rect mOldTargetRect = new Rect();
    private ThreadedRenderer mRenderer;
    private boolean mReportNextDraw;
    private final int mResizeMode;
    private Drawable mResizingBackgroundDrawable;
    private final Rect mStableInsets = new Rect();
    private ColorDrawable mStatusBarColor;
    private RenderNode mSystemBarBackgroundNode;
    private final Rect mSystemInsets = new Rect();
    private final Rect mTargetRect = new Rect();
    private final Rect mTmpRect = new Rect();
    private Drawable mUserCaptionBackgroundDrawable;
}
