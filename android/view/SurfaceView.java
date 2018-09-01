// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.*;
import android.os.*;
import android.util.AttributeSet;
import android.util.Log;
import com.android.internal.view.SurfaceCallbackHelper;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package android.view:
//            View, Surface, RenderNode, ViewRootImpl, 
//            SurfaceControl, ViewParent, ViewTreeObserver, SurfaceSession, 
//            SurfaceHolder

public class SurfaceView extends View
    implements ViewRootImpl.WindowStoppedCallback
{
    class SurfaceControlWithBackground extends SurfaceControl
    {

        public void deferTransactionUntil(IBinder ibinder, long l)
        {
            super.deferTransactionUntil(ibinder, l);
            mBackgroundControl.deferTransactionUntil(ibinder, l);
        }

        public void deferTransactionUntil(Surface surface, long l)
        {
            super.deferTransactionUntil(surface, l);
            mBackgroundControl.deferTransactionUntil(surface, l);
        }

        public void destroy()
        {
            super.destroy();
            mBackgroundControl.destroy();
        }

        public void hide()
        {
            super.hide();
            mVisible = false;
            updateBackgroundVisibility();
        }

        public void release()
        {
            super.release();
            mBackgroundControl.release();
        }

        public void setAlpha(float f)
        {
            super.setAlpha(f);
            mBackgroundControl.setAlpha(f);
        }

        public void setFinalCrop(Rect rect)
        {
            super.setFinalCrop(rect);
            mBackgroundControl.setFinalCrop(rect);
        }

        public void setLayer(int i)
        {
            super.setLayer(i);
            mBackgroundControl.setLayer(-3);
        }

        public void setLayerStack(int i)
        {
            super.setLayerStack(i);
            mBackgroundControl.setLayerStack(i);
        }

        public void setMatrix(float f, float f1, float f2, float f3)
        {
            super.setMatrix(f, f1, f2, f3);
            mBackgroundControl.setMatrix(f, f1, f2, f3);
        }

        public void setOpaque(boolean flag)
        {
            super.setOpaque(flag);
            mOpaque = flag;
            updateBackgroundVisibility();
        }

        public void setPosition(float f, float f1)
        {
            super.setPosition(f, f1);
            mBackgroundControl.setPosition(f, f1);
        }

        public void setSecure(boolean flag)
        {
            super.setSecure(flag);
        }

        public void setSize(int i, int j)
        {
            super.setSize(i, j);
            mBackgroundControl.setSize(i, j);
        }

        public void setTransparentRegionHint(Region region)
        {
            super.setTransparentRegionHint(region);
            mBackgroundControl.setTransparentRegionHint(region);
        }

        public void setWindowCrop(Rect rect)
        {
            super.setWindowCrop(rect);
            mBackgroundControl.setWindowCrop(rect);
        }

        public void show()
        {
            super.show();
            mVisible = true;
            updateBackgroundVisibility();
        }

        void updateBackgroundVisibility()
        {
            if(mOpaque && mVisible)
                mBackgroundControl.show();
            else
                mBackgroundControl.hide();
        }

        private SurfaceControl mBackgroundControl;
        private boolean mOpaque;
        public boolean mVisible;
        final SurfaceView this$0;

        public SurfaceControlWithBackground(SurfaceSession surfacesession, String s, int i, int j, int k, int l)
            throws Exception
        {
            this$0 = SurfaceView.this;
            super(surfacesession, s, i, j, k, l);
            mOpaque = true;
            mVisible = false;
            mBackgroundControl = new SurfaceControl(surfacesession, (new StringBuilder()).append("Background for - ").append(s).toString(), i, j, -1, l | 0x20000);
            boolean flag;
            if((l & 0x400) != 0)
                flag = true;
            else
                flag = false;
            mOpaque = flag;
        }
    }


    static void _2D_wrap0(SurfaceView surfaceview, Runnable runnable)
    {
        surfaceview.runOnUiThread(runnable);
    }

    public SurfaceView(Context context)
    {
        this(context, null);
    }

    public SurfaceView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SurfaceView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SurfaceView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mCallbacks = new ArrayList();
        mLocation = new int[2];
        mSurfaceLock = new ReentrantLock();
        mSurface = new Surface();
        mDrawingStopped = true;
        mDrawFinished = false;
        mScreenRect = new Rect();
        mTmpRect = new Rect();
        mConfiguration = new Configuration();
        mSubLayer = -2;
        mIsCreating = false;
        mRtHandlingPositionUpdates = false;
        mScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {

            public void onScrollChanged()
            {
                updateSurface();
            }

            final SurfaceView this$0;

            
            {
                this$0 = SurfaceView.this;
                super();
            }
        }
;
        mDrawListener = new ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw()
            {
                boolean flag = false;
                SurfaceView surfaceview = SurfaceView.this;
                boolean flag1 = flag;
                if(getWidth() > 0)
                {
                    flag1 = flag;
                    if(getHeight() > 0)
                        flag1 = true;
                }
                surfaceview.mHaveFrame = flag1;
                updateSurface();
                return true;
            }

            final SurfaceView this$0;

            
            {
                this$0 = SurfaceView.this;
                super();
            }
        }
;
        mRequestedVisible = false;
        mWindowVisibility = false;
        mLastWindowVisibility = false;
        mViewVisibility = false;
        mWindowStopped = false;
        mRequestedWidth = -1;
        mRequestedHeight = -1;
        mRequestedFormat = 4;
        mHaveFrame = false;
        mSurfaceCreated = false;
        mLastLockTime = 0L;
        mVisible = false;
        mWindowSpaceLeft = -1;
        mWindowSpaceTop = -1;
        mSurfaceWidth = -1;
        mSurfaceHeight = -1;
        mFormat = -1;
        mSurfaceFrame = new Rect();
        mLastSurfaceWidth = -1;
        mLastSurfaceHeight = -1;
        mSurfaceFlags = 4;
        mRTLastReportedPosition = new Rect();
        mSurfaceHolder = new SurfaceHolder() {

            private Canvas internalLockCanvas(Rect rect, boolean flag)
            {
                Object obj;
                Canvas canvas;
                mSurfaceLock.lock();
                obj = null;
                canvas = obj;
                if(mDrawingStopped) goto _L2; else goto _L1
_L1:
                canvas = obj;
                if(mSurfaceControl == null) goto _L2; else goto _L3
_L3:
                if(!flag) goto _L5; else goto _L4
_L4:
                try
                {
                    canvas = mSurface.lockHardwareCanvas();
                }
                // Misplaced declaration of an exception variable
                catch(Rect rect)
                {
                    Log.e("SurfaceHolder", "Exception locking surface", rect);
                    canvas = obj;
                }
_L2:
                if(canvas != null)
                {
                    mLastLockTime = SystemClock.uptimeMillis();
                    return canvas;
                }
                break; /* Loop/switch isn't completed */
_L5:
                canvas = mSurface.lockCanvas(rect);
                if(true) goto _L2; else goto _L6
_L6:
                long l = SystemClock.uptimeMillis();
                long l1 = mLastLockTime + 100L;
                long l2 = l;
                if(l1 > l)
                {
                    try
                    {
                        Thread.sleep(l1 - l);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Rect rect) { }
                    l2 = SystemClock.uptimeMillis();
                }
                mLastLockTime = l2;
                mSurfaceLock.unlock();
                return null;
            }

            public void addCallback(SurfaceHolder.Callback callback)
            {
                ArrayList arraylist = mCallbacks;
                arraylist;
                JVM INSTR monitorenter ;
                if(!mCallbacks.contains(callback))
                    mCallbacks.add(callback);
                arraylist;
                JVM INSTR monitorexit ;
                return;
                callback;
                throw callback;
            }

            public Surface getSurface()
            {
                return mSurface;
            }

            public Rect getSurfaceFrame()
            {
                return mSurfaceFrame;
            }

            public boolean isCreating()
            {
                return mIsCreating;
            }

            void lambda$_2D_android_view_SurfaceView$3_40425(boolean flag)
            {
                SurfaceView.this.setKeepScreenOn(flag);
            }

            public Canvas lockCanvas()
            {
                return internalLockCanvas(null, false);
            }

            public Canvas lockCanvas(Rect rect)
            {
                return internalLockCanvas(rect, false);
            }

            public Canvas lockHardwareCanvas()
            {
                return internalLockCanvas(null, true);
            }

            public void removeCallback(SurfaceHolder.Callback callback)
            {
                ArrayList arraylist = mCallbacks;
                arraylist;
                JVM INSTR monitorenter ;
                mCallbacks.remove(callback);
                arraylist;
                JVM INSTR monitorexit ;
                return;
                callback;
                throw callback;
            }

            public void setFixedSize(int k, int l)
            {
                if(mRequestedWidth != k || mRequestedHeight != l)
                {
                    mRequestedWidth = k;
                    mRequestedHeight = l;
                    requestLayout();
                }
            }

            public void setFormat(int k)
            {
                int l = k;
                if(k == -1)
                    l = 4;
                mRequestedFormat = l;
                if(mSurfaceControl != null)
                    updateSurface();
            }

            public void setKeepScreenOn(boolean flag)
            {
                SurfaceView._2D_wrap0(SurfaceView.this, new _.Lambda.P6MTGFSudLpwrqb6oVD8FdorW1c(flag, this));
            }

            public void setSizeFromLayout()
            {
                if(mRequestedWidth != -1 || mRequestedHeight != -1)
                {
                    SurfaceView surfaceview = SurfaceView.this;
                    mRequestedHeight = -1;
                    surfaceview.mRequestedWidth = -1;
                    requestLayout();
                }
            }

            public void setType(int k)
            {
            }

            public void unlockCanvasAndPost(Canvas canvas)
            {
                mSurface.unlockCanvasAndPost(canvas);
                mSurfaceLock.unlock();
            }

            private static final String LOG_TAG = "SurfaceHolder";
            final SurfaceView this$0;

            
            {
                this$0 = SurfaceView.this;
                super();
            }
        }
;
        mRenderNode.requestPositionUpdates(this);
        setWillNotDraw(true);
    }

    private Rect getParentSurfaceInsets()
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl == null)
            return null;
        else
            return viewrootimpl.mWindowAttributes.surfaceInsets;
    }

    private SurfaceHolder.Callback[] getSurfaceCallbacks()
    {
        ArrayList arraylist = mCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        SurfaceHolder.Callback acallback[];
        acallback = new SurfaceHolder.Callback[mCallbacks.size()];
        mCallbacks.toArray(acallback);
        arraylist;
        JVM INSTR monitorexit ;
        return acallback;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean isAboveParent()
    {
        boolean flag = false;
        if(mSubLayer >= 0)
            flag = true;
        return flag;
    }

    private void onDrawFinished()
    {
        if(mDeferredDestroySurfaceControl != null)
        {
            mDeferredDestroySurfaceControl.destroy();
            mDeferredDestroySurfaceControl = null;
        }
        runOnUiThread(new _.Lambda.XmA8Y30pNAdQP9ujRlGx1qfDHH8((byte)0, this));
    }

    private void performDrawFinished()
    {
        if(mPendingReportDraws > 0)
        {
            mDrawFinished = true;
            if(mAttachedToWindow)
            {
                notifyDrawFinished();
                invalidate();
            }
        } else
        {
            Log.e("SurfaceView", (new StringBuilder()).append(System.identityHashCode(this)).append("finished drawing").append(" but no pending report draw (extra call").append(" to draw completion runnable?)").toString());
        }
    }

    private void runOnUiThread(Runnable runnable)
    {
        Handler handler = getHandler();
        if(handler != null && handler.getLooper() != Looper.myLooper())
            handler.post(runnable);
        else
            runnable.run();
    }

    private void setParentSpaceRectangle(Rect rect, long l)
    {
        ViewRootImpl viewrootimpl;
        viewrootimpl = getViewRootImpl();
        SurfaceControl.openTransaction();
        if(l <= 0L)
            break MISSING_BLOCK_LABEL_28;
        mSurfaceControl.deferTransactionUntil(viewrootimpl.mSurface, l);
        mSurfaceControl.setPosition(rect.left, rect.top);
        mSurfaceControl.setMatrix((float)rect.width() / (float)mSurfaceWidth, 0.0F, 0.0F, (float)rect.height() / (float)mSurfaceHeight);
        SurfaceControl.closeTransaction();
        return;
        rect;
        SurfaceControl.closeTransaction();
        throw rect;
    }

    private void updateOpaqueFlag()
    {
        if(!PixelFormat.formatHasAlpha(mRequestedFormat))
            mSurfaceFlags = mSurfaceFlags | 0x400;
        else
            mSurfaceFlags = mSurfaceFlags & 0xfffffbff;
    }

    private void updateRequestedVisibility()
    {
        boolean flag;
        if(mViewVisibility && mWindowVisibility)
            flag = mWindowStopped ^ true;
        else
            flag = false;
        mRequestedVisible = flag;
    }

    void _2D_android_view_SurfaceView_2D_mthref_2D_0()
    {
        onDrawFinished();
    }

    protected void dispatchDraw(Canvas canvas)
    {
        if(mDrawFinished && isAboveParent() ^ true && (mPrivateFlags & 0x80) == 128)
            canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        super.dispatchDraw(canvas);
    }

    public void draw(Canvas canvas)
    {
        if(mDrawFinished && isAboveParent() ^ true && (mPrivateFlags & 0x80) == 0)
            canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        super.draw(canvas);
    }

    public boolean gatherTransparentRegion(Region region)
    {
        boolean flag;
        if(isAboveParent() || mDrawFinished ^ true)
            return super.gatherTransparentRegion(region);
        flag = true;
        if((mPrivateFlags & 0x80) != 0) goto _L2; else goto _L1
_L1:
        boolean flag1 = super.gatherTransparentRegion(region);
_L4:
        if(PixelFormat.formatHasAlpha(mRequestedFormat))
            flag1 = false;
        return flag1;
_L2:
        flag1 = flag;
        if(region != null)
        {
            int i = getWidth();
            int j = getHeight();
            flag1 = flag;
            if(i > 0)
            {
                flag1 = flag;
                if(j > 0)
                {
                    getLocationInWindow(mLocation);
                    int k = mLocation[0];
                    int l = mLocation[1];
                    region.op(k, l, k + i, l + j, android.graphics.Region.Op.UNION);
                    flag1 = flag;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public SurfaceHolder getHolder()
    {
        return mSurfaceHolder;
    }

    public boolean isFixedSize()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mRequestedWidth == -1)
            if(mRequestedHeight != -1)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    void lambda$_2D_android_view_SurfaceView_32158()
    {
        performDrawFinished();
    }

    void notifyDrawFinished()
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl != null)
            viewrootimpl.pendingDrawFinished();
        mPendingReportDraws = mPendingReportDraws - 1;
    }

    protected void onAttachedToWindow()
    {
        boolean flag = false;
        super.onAttachedToWindow();
        getViewRootImpl().addWindowStoppedCallback(this);
        mWindowStopped = false;
        if(getVisibility() == 0)
            flag = true;
        mViewVisibility = flag;
        updateRequestedVisibility();
        mAttachedToWindow = true;
        mParent.requestTransparentRegion(this);
        if(!mGlobalListenersAdded)
        {
            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
            viewtreeobserver.addOnScrollChangedListener(mScrollChangedListener);
            viewtreeobserver.addOnPreDrawListener(mDrawListener);
            mGlobalListenersAdded = true;
        }
    }

    protected void onDetachedFromWindow()
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl != null)
            viewrootimpl.removeWindowStoppedCallback(this);
        mAttachedToWindow = false;
        if(mGlobalListenersAdded)
        {
            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
            viewtreeobserver.removeOnScrollChangedListener(mScrollChangedListener);
            viewtreeobserver.removeOnPreDrawListener(mDrawListener);
            mGlobalListenersAdded = false;
        }
        while(mPendingReportDraws > 0) 
            notifyDrawFinished();
        mRequestedVisible = false;
        updateSurface();
        if(mSurfaceControl != null)
            mSurfaceControl.destroy();
        mSurfaceControl = null;
        mHaveFrame = false;
        super.onDetachedFromWindow();
    }

    protected void onMeasure(int i, int j)
    {
        if(mRequestedWidth >= 0)
            i = resolveSizeAndState(mRequestedWidth, i, 0);
        else
            i = getDefaultSize(0, i);
        if(mRequestedHeight >= 0)
            j = resolveSizeAndState(mRequestedHeight, j, 0);
        else
            j = getDefaultSize(0, j);
        setMeasuredDimension(i, j);
    }

    protected void onWindowVisibilityChanged(int i)
    {
        boolean flag = false;
        super.onWindowVisibilityChanged(i);
        if(i == 0)
            flag = true;
        mWindowVisibility = flag;
        updateRequestedVisibility();
        updateSurface();
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag = super.setFrame(i, j, k, l);
        updateSurface();
        return flag;
    }

    public void setSecure(boolean flag)
    {
        if(flag)
            mSurfaceFlags = mSurfaceFlags | 0x80;
        else
            mSurfaceFlags = mSurfaceFlags & 0xffffff7f;
    }

    public void setVisibility(int i)
    {
        boolean flag = false;
        super.setVisibility(i);
        if(i == 0)
            flag = true;
        mViewVisibility = flag;
        if(mWindowVisibility && mViewVisibility)
            flag = mWindowStopped ^ true;
        else
            flag = false;
        if(flag != mRequestedVisible)
            requestLayout();
        mRequestedVisible = flag;
        updateSurface();
    }

    public void setWindowType(int i)
    {
        if(getContext().getApplicationInfo().targetSdkVersion >= 26)
            throw new UnsupportedOperationException("SurfaceView#setWindowType() has never been a public API.");
        if(i == 1000)
        {
            Log.e("SurfaceView", "If you are calling SurfaceView#setWindowType(TYPE_APPLICATION_PANEL) just to make the SurfaceView to be placed on top of its window, you must call setZOrderOnTop(true) instead.", new Throwable());
            setZOrderOnTop(true);
            return;
        } else
        {
            Log.e("SurfaceView", (new StringBuilder()).append("SurfaceView#setWindowType(int) is deprecated and now does nothing. type=").append(i).toString(), new Throwable());
            return;
        }
    }

    public void setZOrderMediaOverlay(boolean flag)
    {
        int i;
        if(flag)
            i = -1;
        else
            i = -2;
        mSubLayer = i;
    }

    public void setZOrderOnTop(boolean flag)
    {
        if(flag)
            mSubLayer = 1;
        else
            mSubLayer = -2;
    }

    public final void surfacePositionLost_uiRtSync(long l)
    {
        mRTLastReportedPosition.setEmpty();
        if(mSurfaceControl == null)
            return;
        if(!mRtHandlingPositionUpdates)
            break MISSING_BLOCK_LABEL_62;
        mRtHandlingPositionUpdates = false;
        if(mScreenRect.isEmpty() || !(mScreenRect.equals(mRTLastReportedPosition) ^ true))
            break MISSING_BLOCK_LABEL_62;
        setParentSpaceRectangle(mScreenRect, l);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("SurfaceView", "Exception configuring surface", exception);
          goto _L1
    }

    protected void updateSurface()
    {
        ViewRootImpl viewrootimpl;
        int i;
        int j;
        int k;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        Object obj;
        SurfaceHolder.Callback acallback[];
        int i1;
        if(!mHaveFrame)
            return;
        for(viewrootimpl = getViewRootImpl(); viewrootimpl == null || viewrootimpl.mSurface == null || viewrootimpl.mSurface.isValid() ^ true;)
            return;

        mTranslator = viewrootimpl.mTranslator;
        if(mTranslator != null)
            mSurface.setCompatibilityTranslator(mTranslator);
        i = mRequestedWidth;
        j = i;
        if(i <= 0)
            j = getWidth();
        i = mRequestedHeight;
        k = i;
        if(i <= 0)
            k = getHeight();
        SurfaceSession surfacesession;
        StringBuilder stringbuilder;
        int j1;
        int l1;
        if(mFormat != mRequestedFormat)
            flag2 = true;
        else
            flag2 = false;
        if(mVisible != mRequestedVisible)
            flag3 = true;
        else
            flag3 = false;
        if(mSurfaceControl == null || flag2 || flag3)
            flag4 = mRequestedVisible;
        else
            flag4 = false;
        if(mSurfaceWidth != j || mSurfaceHeight != k)
            flag5 = true;
        else
            flag5 = false;
        if(mWindowVisibility != mLastWindowVisibility)
            i = 1;
        else
            i = 0;
        flag6 = false;
        if(!flag4 && !flag2 && !flag5 && !flag3 && !i)
            break MISSING_BLOCK_LABEL_1440;
        getLocationInWindow(mLocation);
        flag7 = mRequestedVisible;
        mVisible = flag7;
        mWindowSpaceLeft = mLocation[0];
        mWindowSpaceTop = mLocation[1];
        mSurfaceWidth = j;
        mSurfaceHeight = k;
        mFormat = mRequestedFormat;
        mLastWindowVisibility = mWindowVisibility;
        mScreenRect.left = mWindowSpaceLeft;
        mScreenRect.top = mWindowSpaceTop;
        mScreenRect.right = mWindowSpaceLeft + getWidth();
        mScreenRect.bottom = mWindowSpaceTop + getHeight();
        if(mTranslator != null)
            mTranslator.translateRectInAppWindowToScreen(mScreenRect);
        obj = getParentSurfaceInsets();
        mScreenRect.offset(((Rect) (obj)).left, ((Rect) (obj)).top);
        if(!flag4) goto _L2; else goto _L1
_L1:
        obj = JVM INSTR new #592 <Class SurfaceSession>;
        ((SurfaceSession) (obj)).SurfaceSession(viewrootimpl.mSurface);
        mSurfaceSession = ((SurfaceSession) (obj));
        mDeferredDestroySurfaceControl = mSurfaceControl;
        updateOpaqueFlag();
        obj = JVM INSTR new #14  <Class SurfaceView$SurfaceControlWithBackground>;
        surfacesession = mSurfaceSession;
        stringbuilder = JVM INSTR new #266 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        ((SurfaceControlWithBackground) (obj)).this. SurfaceControlWithBackground(surfacesession, stringbuilder.append("SurfaceView - ").append(viewrootimpl.getTitle().toString()).toString(), mSurfaceWidth, mSurfaceHeight, mFormat, mSurfaceFlags);
        mSurfaceControl = ((SurfaceControl) (obj));
_L18:
        mSurfaceLock.lock();
        mDrawingStopped = flag7 ^ true;
        SurfaceControl.openTransaction();
        mSurfaceControl.setLayer(mSubLayer);
        if(!mViewVisibility) goto _L4; else goto _L3
_L3:
        mSurfaceControl.show();
_L19:
        if(flag5 || flag4)
            break MISSING_BLOCK_LABEL_543;
        if(!(mRtHandlingPositionUpdates ^ true))
            break MISSING_BLOCK_LABEL_603;
        mSurfaceControl.setPosition(mScreenRect.left, mScreenRect.top);
        mSurfaceControl.setMatrix((float)mScreenRect.width() / (float)mSurfaceWidth, 0.0F, 0.0F, (float)mScreenRect.height() / (float)mSurfaceHeight);
        if(!flag5)
            break MISSING_BLOCK_LABEL_623;
        mSurfaceControl.setSize(mSurfaceWidth, mSurfaceHeight);
        SurfaceControl.closeTransaction();
        if(flag5 || flag4)
            flag6 = true;
        mSurfaceFrame.left = 0;
        mSurfaceFrame.top = 0;
        if(mTranslator != null) goto _L6; else goto _L5
_L5:
        mSurfaceFrame.right = mSurfaceWidth;
        mSurfaceFrame.bottom = mSurfaceHeight;
_L20:
        i1 = mSurfaceFrame.right;
        j1 = mSurfaceFrame.bottom;
        if(mLastSurfaceWidth == i1)
        {
            if(mLastSurfaceHeight != j1)
                i = 1;
            else
                i = 0;
        } else
        {
            i = 1;
        }
        mLastSurfaceWidth = i1;
        mLastSurfaceHeight = j1;
        mSurfaceLock.unlock();
        if(!flag7) goto _L8; else goto _L7
_L7:
        i1 = mDrawFinished ^ true;
_L21:
        surfacesession = null;
        obj = surfacesession;
        if(!mSurfaceCreated) goto _L10; else goto _L9
_L9:
        if(flag4) goto _L12; else goto _L11
_L11:
        obj = surfacesession;
        if(flag7) goto _L10; else goto _L13
_L13:
        obj = surfacesession;
        if(!flag3) goto _L10; else goto _L12
_L12:
        mSurfaceCreated = false;
        obj = surfacesession;
        if(!mSurface.isValid()) goto _L10; else goto _L14
_L14:
        acallback = getSurfaceCallbacks();
        j1 = 0;
        l1 = acallback.length;
_L16:
        if(j1 >= l1)
            break; /* Loop/switch isn't completed */
        acallback[j1].surfaceDestroyed(mSurfaceHolder);
        j1++;
        if(true) goto _L16; else goto _L15
_L2:
        obj = mSurfaceControl;
        if(obj != null) goto _L18; else goto _L17
_L17:
        return;
_L4:
        mSurfaceControl.hide();
          goto _L19
        obj;
        SurfaceControl.closeTransaction();
        throw obj;
        obj;
        try
        {
            mSurfaceLock.unlock();
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("SurfaceView", "Exception configuring surface", ((Throwable) (obj)));
        }
_L32:
        return;
_L6:
        float f = mTranslator.applicationInvertedScale;
        mSurfaceFrame.right = (int)((float)mSurfaceWidth * f + 0.5F);
        mSurfaceFrame.bottom = (int)((float)mSurfaceHeight * f + 0.5F);
          goto _L20
_L8:
        i1 = 0;
          goto _L21
_L15:
        obj = acallback;
        if(!mSurface.isValid()) goto _L10; else goto _L22
_L22:
        mSurface.forceScopedDisconnect();
        obj = acallback;
_L10:
        if(!flag4)
            break MISSING_BLOCK_LABEL_1045;
        mSurface.copyFrom(mSurfaceControl);
        if(!flag5)
            break MISSING_BLOCK_LABEL_1076;
        if(getContext().getApplicationInfo().targetSdkVersion < 26)
            mSurface.createFrom(mSurfaceControl);
        if(!flag7)
            break MISSING_BLOCK_LABEL_1338;
        if(!mSurface.isValid())
            break MISSING_BLOCK_LABEL_1338;
        Object obj1 = obj;
        if(mSurfaceCreated) goto _L24; else goto _L23
_L23:
        if(flag4) goto _L26; else goto _L25
_L25:
        obj1 = obj;
        if(!flag3) goto _L24; else goto _L26
_L26:
        mSurfaceCreated = true;
        mIsCreating = true;
        Object obj2;
        obj2 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_1141;
        obj2 = getSurfaceCallbacks();
        int k1 = 0;
        int i2 = obj2.length;
_L27:
        obj1 = obj2;
        if(k1 >= i2)
            break; /* Loop/switch isn't completed */
        obj2[k1].surfaceCreated(mSurfaceHolder);
        k1++;
        if(true) goto _L27; else goto _L24
_L24:
        if(flag4 || flag2 || flag5 || flag3) goto _L29; else goto _L28
_L28:
        obj = obj1;
        if(!i) goto _L30; else goto _L29
_L29:
        obj2 = obj1;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_1223;
        obj2 = getSurfaceCallbacks();
        i = 0;
        int l = obj2.length;
_L31:
        obj = obj2;
        if(i >= l)
            break; /* Loop/switch isn't completed */
        obj2[i].surfaceChanged(mSurfaceHolder, mFormat, j, k);
        i++;
        if(true) goto _L31; else goto _L30
_L30:
        if(!(flag6 | i1))
            break MISSING_BLOCK_LABEL_1338;
        obj1 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_1289;
        obj1 = getSurfaceCallbacks();
        mPendingReportDraws = mPendingReportDraws + 1;
        viewrootimpl.drawPending();
        SurfaceCallbackHelper surfacecallbackhelper = JVM INSTR new #662 <Class SurfaceCallbackHelper>;
        _.Lambda.XmA8Y30pNAdQP9ujRlGx1qfDHH8 xma8y30pnadqp9ujrlgx1qfdhh8 = JVM INSTR new #250 <Class _$Lambda$XmA8Y30pNAdQP9ujRlGx1qfDHH8>;
        xma8y30pnadqp9ujrlgx1qfdhh8._.Lambda.XmA8Y30pNAdQP9ujRlGx1qfDHH8((byte)1, this);
        surfacecallbackhelper.SurfaceCallbackHelper(xma8y30pnadqp9ujrlgx1qfdhh8);
        surfacecallbackhelper.dispatchSurfaceRedrawNeededAsync(mSurfaceHolder, ((SurfaceHolder.Callback []) (obj1)));
        mIsCreating = false;
        if(mSurfaceControl != null && mSurfaceCreated ^ true)
        {
            mSurface.release();
            if(!mWindowStopped)
            {
                mSurfaceControl.destroy();
                mSurfaceControl = null;
            }
        }
          goto _L32
        Exception exception;
        exception;
        mIsCreating = false;
        if(mSurfaceControl != null && mSurfaceCreated ^ true)
        {
            mSurface.release();
            if(!mWindowStopped)
            {
                mSurfaceControl.destroy();
                mSurfaceControl = null;
            }
        }
        throw exception;
        getLocationInSurface(mLocation);
        boolean flag;
        boolean flag1;
        if(mWindowSpaceLeft == mLocation[0])
        {
            if(mWindowSpaceTop != mLocation[1])
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        if(getWidth() == mScreenRect.width())
        {
            if(getHeight() != mScreenRect.height())
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        if(flag || flag1)
        {
            mWindowSpaceLeft = mLocation[0];
            mWindowSpaceTop = mLocation[1];
            mLocation[0] = getWidth();
            mLocation[1] = getHeight();
            mScreenRect.set(mWindowSpaceLeft, mWindowSpaceTop, mWindowSpaceLeft + mLocation[0], mWindowSpaceTop + mLocation[1]);
            if(mTranslator != null)
                mTranslator.translateRectInAppWindowToScreen(mScreenRect);
            if(mSurfaceControl == null)
                return;
            if(!isHardwareAccelerated() || mRtHandlingPositionUpdates ^ true)
                try
                {
                    setParentSpaceRectangle(mScreenRect, -1L);
                }
                catch(Exception exception1)
                {
                    Log.e("SurfaceView", "Exception configuring surface", exception1);
                }
        }
          goto _L32
    }

    public final void updateSurfacePosition_renderWorker(long l, int i, int j, int k, int i1)
    {
        if(mSurfaceControl == null)
            return;
        mRtHandlingPositionUpdates = true;
        if(mRTLastReportedPosition.left == i && mRTLastReportedPosition.top == j && mRTLastReportedPosition.right == k && mRTLastReportedPosition.bottom == i1)
            return;
        mRTLastReportedPosition.set(i, j, k, i1);
        setParentSpaceRectangle(mRTLastReportedPosition, l);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("SurfaceView", "Exception from repositionChild", exception);
          goto _L1
    }

    public void windowStopped(boolean flag)
    {
        mWindowStopped = flag;
        updateRequestedVisibility();
        updateSurface();
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "SurfaceView";
    private boolean mAttachedToWindow;
    final ArrayList mCallbacks;
    final Configuration mConfiguration;
    SurfaceControl mDeferredDestroySurfaceControl;
    boolean mDrawFinished;
    private final ViewTreeObserver.OnPreDrawListener mDrawListener;
    boolean mDrawingStopped;
    int mFormat;
    private boolean mGlobalListenersAdded;
    boolean mHaveFrame;
    boolean mIsCreating;
    long mLastLockTime;
    int mLastSurfaceHeight;
    int mLastSurfaceWidth;
    boolean mLastWindowVisibility;
    final int mLocation[];
    private int mPendingReportDraws;
    private Rect mRTLastReportedPosition;
    int mRequestedFormat;
    int mRequestedHeight;
    boolean mRequestedVisible;
    int mRequestedWidth;
    private volatile boolean mRtHandlingPositionUpdates;
    final Rect mScreenRect;
    private final ViewTreeObserver.OnScrollChangedListener mScrollChangedListener;
    int mSubLayer;
    final Surface mSurface;
    SurfaceControl mSurfaceControl;
    boolean mSurfaceCreated;
    private int mSurfaceFlags;
    final Rect mSurfaceFrame;
    int mSurfaceHeight;
    private final SurfaceHolder mSurfaceHolder;
    final ReentrantLock mSurfaceLock;
    SurfaceSession mSurfaceSession;
    int mSurfaceWidth;
    final Rect mTmpRect;
    private android.content.res.CompatibilityInfo.Translator mTranslator;
    boolean mViewVisibility;
    boolean mVisible;
    int mWindowSpaceLeft;
    int mWindowSpaceTop;
    boolean mWindowStopped;
    boolean mWindowVisibility;
}
