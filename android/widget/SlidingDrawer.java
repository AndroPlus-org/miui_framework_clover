// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;

public class SlidingDrawer extends ViewGroup
{
    private class DrawerToggler
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(SlidingDrawer._2D_get1(SlidingDrawer.this))
                return;
            if(SlidingDrawer._2D_get0(SlidingDrawer.this))
                animateToggle();
            else
                toggle();
        }

        final SlidingDrawer this$0;

        private DrawerToggler()
        {
            this$0 = SlidingDrawer.this;
            super();
        }

        DrawerToggler(DrawerToggler drawertoggler)
        {
            this();
        }
    }

    public static interface OnDrawerCloseListener
    {

        public abstract void onDrawerClosed();
    }

    public static interface OnDrawerOpenListener
    {

        public abstract void onDrawerOpened();
    }

    public static interface OnDrawerScrollListener
    {

        public abstract void onScrollEnded();

        public abstract void onScrollStarted();
    }


    static boolean _2D_get0(SlidingDrawer slidingdrawer)
    {
        return slidingdrawer.mAnimateOnClick;
    }

    static boolean _2D_get1(SlidingDrawer slidingdrawer)
    {
        return slidingdrawer.mLocked;
    }

    static void _2D_wrap0(SlidingDrawer slidingdrawer)
    {
        slidingdrawer.doAnimation();
    }

    public SlidingDrawer(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SlidingDrawer(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SlidingDrawer(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mFrame = new Rect();
        mInvalidate = new Rect();
        mSlidingRunnable = new Runnable() {

            public void run()
            {
                SlidingDrawer._2D_wrap0(SlidingDrawer.this);
            }

            final SlidingDrawer this$0;

            
            {
                this$0 = SlidingDrawer.this;
                super();
            }
        }
;
        context = context.obtainStyledAttributes(attributeset, android.R.styleable.SlidingDrawer, i, j);
        boolean flag;
        if(context.getInt(0, 1) == 1)
            flag = true;
        else
            flag = false;
        mVertical = flag;
        mBottomOffset = (int)context.getDimension(1, 0.0F);
        mTopOffset = (int)context.getDimension(2, 0.0F);
        mAllowSingleTap = context.getBoolean(3, true);
        mAnimateOnClick = context.getBoolean(6, true);
        i = context.getResourceId(4, 0);
        if(i == 0)
            throw new IllegalArgumentException("The handle attribute is required and must refer to a valid child.");
        j = context.getResourceId(5, 0);
        if(j == 0)
            throw new IllegalArgumentException("The content attribute is required and must refer to a valid child.");
        if(i == j)
        {
            throw new IllegalArgumentException("The content and handle attributes must refer to different children.");
        } else
        {
            mHandleId = i;
            mContentId = j;
            float f = getResources().getDisplayMetrics().density;
            mTapThreshold = (int)(6F * f + 0.5F);
            mMaximumTapVelocity = (int)(100F * f + 0.5F);
            mMaximumMinorVelocity = (int)(150F * f + 0.5F);
            mMaximumMajorVelocity = (int)(200F * f + 0.5F);
            mMaximumAcceleration = (int)(2000F * f + 0.5F);
            mVelocityUnits = (int)(1000F * f + 0.5F);
            context.recycle();
            setAlwaysDrawnWithCacheEnabled(false);
            return;
        }
    }

    private void animateClose(int i, boolean flag)
    {
        prepareTracking(i);
        performFling(i, mMaximumAcceleration, true, flag);
    }

    private void animateOpen(int i, boolean flag)
    {
        prepareTracking(i);
        performFling(i, -mMaximumAcceleration, true, flag);
    }

    private void closeDrawer()
    {
        moveHandle(-10002);
        mContent.setVisibility(8);
        mContent.destroyDrawingCache();
        if(!mExpanded)
            return;
        mExpanded = false;
        if(mOnDrawerCloseListener != null)
            mOnDrawerCloseListener.onDrawerClosed();
    }

    private void doAnimation()
    {
        if(mAnimating)
        {
            incrementAnimation();
            float f = mAnimationPosition;
            int i = mBottomOffset;
            int j;
            if(mVertical)
                j = getHeight();
            else
                j = getWidth();
            if(f >= (float)((j + i) - 1))
            {
                mAnimating = false;
                closeDrawer();
            } else
            if(mAnimationPosition < (float)mTopOffset)
            {
                mAnimating = false;
                openDrawer();
            } else
            {
                moveHandle((int)mAnimationPosition);
                mCurrentAnimationTime = mCurrentAnimationTime + 16L;
                postDelayed(mSlidingRunnable, 16L);
            }
        }
    }

    private void incrementAnimation()
    {
        long l = SystemClock.uptimeMillis();
        float f = (float)(l - mAnimationLastTime) / 1000F;
        float f1 = mAnimationPosition;
        float f2 = mAnimatedVelocity;
        float f3 = mAnimatedAcceleration;
        mAnimationPosition = f2 * f + f1 + 0.5F * f3 * f * f;
        mAnimatedVelocity = f3 * f + f2;
        mAnimationLastTime = l;
    }

    private void moveHandle(int i)
    {
        View view = mHandle;
        if(!mVertical) goto _L2; else goto _L1
_L1:
        if(i != -10001) goto _L4; else goto _L3
_L3:
        view.offsetTopAndBottom(mTopOffset - view.getTop());
        invalidate();
_L9:
        return;
_L4:
        int j;
        int k;
        if(i == -10002)
        {
            view.offsetTopAndBottom((mBottomOffset + mBottom) - mTop - mHandleHeight - view.getTop());
            invalidate();
            continue; /* Loop/switch isn't completed */
        }
        j = view.getTop();
        k = i - j;
        if(i >= mTopOffset) goto _L6; else goto _L5
_L5:
        i = mTopOffset - j;
_L7:
        view.offsetTopAndBottom(i);
        Rect rect = mFrame;
        Rect rect2 = mInvalidate;
        view.getHitRect(rect);
        rect2.set(rect);
        rect2.union(rect.left, rect.top - i, rect.right, rect.bottom - i);
        rect2.union(0, rect.bottom - i, getWidth(), (rect.bottom - i) + mContent.getHeight());
        invalidate(rect2);
        continue; /* Loop/switch isn't completed */
_L6:
        i = k;
        if(k > (mBottomOffset + mBottom) - mTop - mHandleHeight - j)
            i = (mBottomOffset + mBottom) - mTop - mHandleHeight - j;
        if(true) goto _L7; else goto _L2
_L2:
        if(i == -10001)
        {
            view.offsetLeftAndRight(mTopOffset - view.getLeft());
            invalidate();
            continue; /* Loop/switch isn't completed */
        }
        if(i == -10002)
        {
            view.offsetLeftAndRight((mBottomOffset + mRight) - mLeft - mHandleWidth - view.getLeft());
            invalidate();
            continue; /* Loop/switch isn't completed */
        }
        j = view.getLeft();
        k = i - j;
        if(i >= mTopOffset)
            break; /* Loop/switch isn't completed */
        i = mTopOffset - j;
_L10:
        view.offsetLeftAndRight(i);
        Rect rect1 = mFrame;
        Rect rect3 = mInvalidate;
        view.getHitRect(rect1);
        rect3.set(rect1);
        rect3.union(rect1.left - i, rect1.top, rect1.right - i, rect1.bottom);
        rect3.union(rect1.right - i, 0, (rect1.right - i) + mContent.getWidth(), getHeight());
        invalidate(rect3);
        if(true) goto _L9; else goto _L8
_L8:
        i = k;
        if(k > (mBottomOffset + mRight) - mLeft - mHandleWidth - j)
            i = (mBottomOffset + mRight) - mLeft - mHandleWidth - j;
          goto _L10
        if(true) goto _L9; else goto _L11
_L11:
    }

    private void openDrawer()
    {
        moveHandle(-10001);
        mContent.setVisibility(0);
        if(mExpanded)
            return;
        mExpanded = true;
        if(mOnDrawerOpenListener != null)
            mOnDrawerOpenListener.onDrawerOpened();
    }

    private void performFling(int i, float f, boolean flag, boolean flag1)
    {
        mAnimationPosition = i;
        mAnimatedVelocity = f;
        if(!mExpanded) goto _L2; else goto _L1
_L1:
        if(flag || f > (float)mMaximumMajorVelocity) goto _L4; else goto _L3
_L3:
        int j = mTopOffset;
        int k;
        long l1;
        if(mVertical)
            k = mHandleHeight;
        else
            k = mHandleWidth;
        if(i <= k + j || f <= (float)(-mMaximumMajorVelocity)) goto _L5; else goto _L4
_L4:
        mAnimatedAcceleration = mMaximumAcceleration;
        if(f < 0.0F)
            mAnimatedVelocity = 0.0F;
_L7:
        l1 = SystemClock.uptimeMillis();
        mAnimationLastTime = l1;
        mCurrentAnimationTime = l1 + 16L;
        mAnimating = true;
        removeCallbacks(mSlidingRunnable);
        postDelayed(mSlidingRunnable, 16L);
        stopTracking(flag1);
        return;
_L5:
        mAnimatedAcceleration = -mMaximumAcceleration;
        if(f > 0.0F)
            mAnimatedVelocity = 0.0F;
        continue; /* Loop/switch isn't completed */
_L2:
label0:
        {
            if(flag)
                break label0;
            if(f <= (float)mMaximumMajorVelocity)
            {
                int l;
                if(mVertical)
                    l = getHeight();
                else
                    l = getWidth();
                if(i <= l / 2 || f <= (float)(-mMaximumMajorVelocity))
                    break label0;
            }
            mAnimatedAcceleration = mMaximumAcceleration;
            if(f < 0.0F)
                mAnimatedVelocity = 0.0F;
            continue; /* Loop/switch isn't completed */
        }
        mAnimatedAcceleration = -mMaximumAcceleration;
        if(f > 0.0F)
            mAnimatedVelocity = 0.0F;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void prepareContent()
    {
        if(mAnimating)
            return;
        View view = mContent;
        if(view.isLayoutRequested())
            if(mVertical)
            {
                int i = mHandleHeight;
                int j = mBottom;
                int l = mTop;
                int i1 = mTopOffset;
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(mRight - mLeft, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j - l - i - i1, 0x40000000));
                view.layout(0, mTopOffset + i, view.getMeasuredWidth(), mTopOffset + i + view.getMeasuredHeight());
            } else
            {
                int k = mHandle.getWidth();
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(mRight - mLeft - k - mTopOffset, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(mBottom - mTop, 0x40000000));
                view.layout(mTopOffset + k, 0, mTopOffset + k + view.getMeasuredWidth(), view.getMeasuredHeight());
            }
        view.getViewTreeObserver().dispatchOnPreDraw();
        if(!view.isHardwareAccelerated())
            view.buildDrawingCache();
        view.setVisibility(8);
    }

    private void prepareTracking(int i)
    {
        mTracking = true;
        mVelocityTracker = VelocityTracker.obtain();
        if(mExpanded ^ true)
        {
            mAnimatedAcceleration = mMaximumAcceleration;
            mAnimatedVelocity = mMaximumMajorVelocity;
            int j = mBottomOffset;
            int k;
            long l;
            if(mVertical)
            {
                i = getHeight();
                k = mHandleHeight;
            } else
            {
                i = getWidth();
                k = mHandleWidth;
            }
            mAnimationPosition = (i - k) + j;
            moveHandle((int)mAnimationPosition);
            mAnimating = true;
            removeCallbacks(mSlidingRunnable);
            l = SystemClock.uptimeMillis();
            mAnimationLastTime = l;
            mCurrentAnimationTime = 16L + l;
            mAnimating = true;
        } else
        {
            if(mAnimating)
            {
                mAnimating = false;
                removeCallbacks(mSlidingRunnable);
            }
            moveHandle(i);
        }
    }

    private void stopTracking(boolean flag)
    {
        mHandle.setPressed(false);
        mTracking = false;
        if(flag && mOnDrawerScrollListener != null)
            mOnDrawerScrollListener.onScrollEnded();
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public void animateClose()
    {
        prepareContent();
        OnDrawerScrollListener ondrawerscrolllistener = mOnDrawerScrollListener;
        if(ondrawerscrolllistener != null)
            ondrawerscrolllistener.onScrollStarted();
        int i;
        if(mVertical)
            i = mHandle.getTop();
        else
            i = mHandle.getLeft();
        animateClose(i, false);
        if(ondrawerscrolllistener != null)
            ondrawerscrolllistener.onScrollEnded();
    }

    public void animateOpen()
    {
        prepareContent();
        OnDrawerScrollListener ondrawerscrolllistener = mOnDrawerScrollListener;
        if(ondrawerscrolllistener != null)
            ondrawerscrolllistener.onScrollStarted();
        int i;
        if(mVertical)
            i = mHandle.getTop();
        else
            i = mHandle.getLeft();
        animateOpen(i, false);
        sendAccessibilityEvent(32);
        if(ondrawerscrolllistener != null)
            ondrawerscrolllistener.onScrollEnded();
    }

    public void animateToggle()
    {
        if(!mExpanded)
            animateOpen();
        else
            animateClose();
    }

    public void close()
    {
        closeDrawer();
        invalidate();
        requestLayout();
    }

    protected void dispatchDraw(Canvas canvas)
    {
        boolean flag;
        long l;
        View view;
        boolean flag1;
        flag = false;
        l = getDrawingTime();
        view = mHandle;
        flag1 = mVertical;
        drawChild(canvas, view, l);
        if(!mTracking && !mAnimating) goto _L2; else goto _L1
_L1:
        android.graphics.Bitmap bitmap = mContent.getDrawingCache();
        if(bitmap != null)
        {
            if(flag1)
                canvas.drawBitmap(bitmap, 0.0F, view.getBottom(), null);
            else
                canvas.drawBitmap(bitmap, view.getRight(), 0.0F, null);
        } else
        {
            canvas.save();
            int i;
            float f;
            if(flag1)
                i = 0;
            else
                i = view.getLeft() - mTopOffset;
            f = i;
            i = ((flag) ? 1 : 0);
            if(flag1)
                i = view.getTop() - mTopOffset;
            canvas.translate(f, i);
            drawChild(canvas, mContent, l);
            canvas.restore();
        }
_L4:
        return;
_L2:
        if(mExpanded)
            drawChild(canvas, mContent, l);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/SlidingDrawer.getName();
    }

    public View getContent()
    {
        return mContent;
    }

    public View getHandle()
    {
        return mHandle;
    }

    public boolean isMoving()
    {
        boolean flag;
        if(!mTracking)
            flag = mAnimating;
        else
            flag = true;
        return flag;
    }

    public boolean isOpened()
    {
        return mExpanded;
    }

    public void lock()
    {
        mLocked = true;
    }

    protected void onFinishInflate()
    {
        mHandle = findViewById(mHandleId);
        if(mHandle == null)
            throw new IllegalArgumentException("The handle attribute is must refer to an existing child.");
        mHandle.setOnClickListener(new DrawerToggler(null));
        mContent = findViewById(mContentId);
        if(mContent == null)
        {
            throw new IllegalArgumentException("The content attribute is must refer to an existing child.");
        } else
        {
            mContent.setVisibility(8);
            return;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if(mLocked)
            return false;
        int i = motionevent.getAction();
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        Rect rect = mFrame;
        View view = mHandle;
        view.getHitRect(rect);
        if(!mTracking && rect.contains((int)f, (int)f1) ^ true)
            return false;
        if(i == 0)
        {
            mTracking = true;
            view.setPressed(true);
            prepareContent();
            if(mOnDrawerScrollListener != null)
                mOnDrawerScrollListener.onScrollStarted();
            if(mVertical)
            {
                int j = mHandle.getTop();
                mTouchDelta = (int)f1 - j;
                prepareTracking(j);
            } else
            {
                int k = mHandle.getLeft();
                mTouchDelta = (int)f - k;
                prepareTracking(k);
            }
            mVelocityTracker.addMovement(motionevent);
        }
        return true;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(mTracking)
            return;
        i = k - i;
        j = l - j;
        View view = mHandle;
        l = view.getMeasuredWidth();
        int i1 = view.getMeasuredHeight();
        View view1 = mContent;
        if(mVertical)
        {
            k = (i - l) / 2;
            if(mExpanded)
                i = mTopOffset;
            else
                i = (j - i1) + mBottomOffset;
            view1.layout(0, mTopOffset + i1, view1.getMeasuredWidth(), mTopOffset + i1 + view1.getMeasuredHeight());
            j = i;
            i = k;
        } else
        {
            if(mExpanded)
                i = mTopOffset;
            else
                i = (i - l) + mBottomOffset;
            j = (j - i1) / 2;
            view1.layout(mTopOffset + l, 0, mTopOffset + l + view1.getMeasuredWidth(), view1.getMeasuredHeight());
        }
        view.layout(i, j, i + l, j + i1);
        mHandleHeight = view.getHeight();
        mHandleWidth = view.getWidth();
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = android.view.View.MeasureSpec.getMode(j);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        if(k == 0 || i1 == 0)
            throw new RuntimeException("SlidingDrawer cannot have UNSPECIFIED dimensions");
        View view = mHandle;
        measureChild(view, i, j);
        if(mVertical)
        {
            i = view.getMeasuredHeight();
            j = mTopOffset;
            mContent.measure(android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j1 - i - j, 0x40000000));
        } else
        {
            j = view.getMeasuredWidth();
            i = mTopOffset;
            mContent.measure(android.view.View.MeasureSpec.makeMeasureSpec(l - j - i, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x40000000));
        }
        setMeasuredDimension(l, j1);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mLocked)
            return true;
        if(!mTracking) goto _L2; else goto _L1
_L1:
        mVelocityTracker.addMovement(motionevent);
        motionevent.getAction();
        JVM INSTR tableswitch 1 3: default 56
    //                   1 112
    //                   2 78
    //                   3 112;
           goto _L3 _L4 _L5 _L4
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_112;
_L2:
        boolean flag;
        float f;
        VelocityTracker velocitytracker;
        float f1;
        float f2;
        int i;
        float f3;
        int j;
        float f4;
        if(!mTracking && !mAnimating)
            flag = super.onTouchEvent(motionevent);
        else
            flag = true;
        return flag;
_L5:
        if(mVertical)
            f = motionevent.getY();
        else
            f = motionevent.getX();
        moveHandle((int)f - mTouchDelta);
          goto _L2
        velocitytracker = mVelocityTracker;
        velocitytracker.computeCurrentVelocity(mVelocityUnits);
        f1 = velocitytracker.getYVelocity();
        f2 = velocitytracker.getXVelocity();
        flag = mVertical;
        if(flag)
        {
            if(f1 < 0.0F)
                i = 1;
            else
                i = 0;
            f3 = f2;
            if(f2 < 0.0F)
                f3 = -f2;
            j = i;
            f = f3;
            f4 = f1;
            if(f3 > (float)mMaximumMinorVelocity)
            {
                f = mMaximumMinorVelocity;
                f4 = f1;
                j = i;
            }
        } else
        {
            if(f2 < 0.0F)
                i = 1;
            else
                i = 0;
            f3 = f1;
            if(f1 < 0.0F)
                f3 = -f1;
            j = i;
            f = f2;
            f4 = f3;
            if(f3 > (float)mMaximumMinorVelocity)
            {
                f4 = mMaximumMinorVelocity;
                j = i;
                f = f2;
            }
        }
        f4 = (float)Math.hypot(f, f4);
        f = f4;
        if(j != 0)
            f = -f4;
        j = mHandle.getTop();
        i = mHandle.getLeft();
        if(Math.abs(f) < (float)mMaximumTapVelocity)
        {
            if(flag ? mExpanded && j < mTapThreshold + mTopOffset || !mExpanded && j > (mBottomOffset + mBottom) - mTop - mHandleHeight - mTapThreshold : mExpanded && i < mTapThreshold + mTopOffset || !mExpanded && i > (mBottomOffset + mRight) - mLeft - mHandleWidth - mTapThreshold)
            {
                if(mAllowSingleTap)
                {
                    playSoundEffect(0);
                    if(mExpanded)
                    {
                        if(flag)
                            i = j;
                        animateClose(i, true);
                    } else
                    {
                        if(!flag)
                            j = i;
                        animateOpen(j, true);
                    }
                } else
                {
                    if(!flag)
                        j = i;
                    performFling(j, f, false, true);
                }
            } else
            {
                if(flag)
                    i = j;
                performFling(i, f, false, true);
            }
        } else
        {
            if(!flag)
                j = i;
            performFling(j, f, false, true);
        }
          goto _L2
    }

    public void open()
    {
        openDrawer();
        invalidate();
        requestLayout();
        sendAccessibilityEvent(32);
    }

    public void setOnDrawerCloseListener(OnDrawerCloseListener ondrawercloselistener)
    {
        mOnDrawerCloseListener = ondrawercloselistener;
    }

    public void setOnDrawerOpenListener(OnDrawerOpenListener ondraweropenlistener)
    {
        mOnDrawerOpenListener = ondraweropenlistener;
    }

    public void setOnDrawerScrollListener(OnDrawerScrollListener ondrawerscrolllistener)
    {
        mOnDrawerScrollListener = ondrawerscrolllistener;
    }

    public void toggle()
    {
        if(!mExpanded)
            openDrawer();
        else
            closeDrawer();
        invalidate();
        requestLayout();
    }

    public void unlock()
    {
        mLocked = false;
    }

    private static final int ANIMATION_FRAME_DURATION = 16;
    private static final int COLLAPSED_FULL_CLOSED = -10002;
    private static final int EXPANDED_FULL_OPEN = -10001;
    private static final float MAXIMUM_ACCELERATION = 2000F;
    private static final float MAXIMUM_MAJOR_VELOCITY = 200F;
    private static final float MAXIMUM_MINOR_VELOCITY = 150F;
    private static final float MAXIMUM_TAP_VELOCITY = 100F;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    private static final int TAP_THRESHOLD = 6;
    private static final int VELOCITY_UNITS = 1000;
    private boolean mAllowSingleTap;
    private boolean mAnimateOnClick;
    private float mAnimatedAcceleration;
    private float mAnimatedVelocity;
    private boolean mAnimating;
    private long mAnimationLastTime;
    private float mAnimationPosition;
    private int mBottomOffset;
    private View mContent;
    private final int mContentId;
    private long mCurrentAnimationTime;
    private boolean mExpanded;
    private final Rect mFrame;
    private View mHandle;
    private int mHandleHeight;
    private final int mHandleId;
    private int mHandleWidth;
    private final Rect mInvalidate;
    private boolean mLocked;
    private final int mMaximumAcceleration;
    private final int mMaximumMajorVelocity;
    private final int mMaximumMinorVelocity;
    private final int mMaximumTapVelocity;
    private OnDrawerCloseListener mOnDrawerCloseListener;
    private OnDrawerOpenListener mOnDrawerOpenListener;
    private OnDrawerScrollListener mOnDrawerScrollListener;
    private final Runnable mSlidingRunnable;
    private final int mTapThreshold;
    private int mTopOffset;
    private int mTouchDelta;
    private boolean mTracking;
    private VelocityTracker mVelocityTracker;
    private final int mVelocityUnits;
    private boolean mVertical;
}
