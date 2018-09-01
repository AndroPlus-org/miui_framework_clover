// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import java.lang.ref.WeakReference;
import java.util.HashMap;

// Referenced classes of package android.widget:
//            AdapterViewAnimator, ImageView, FrameLayout

public class StackView extends AdapterViewAnimator
{
    private static class HolographicHelper
    {

        Bitmap createClickOutline(View view, int i)
        {
            return createOutline(view, 1, i);
        }

        Bitmap createOutline(View view, int i, int j)
        {
            mHolographicPaint.setColor(j);
            if(i == 0)
                mBlurPaint.setMaskFilter(mSmallBlurMaskFilter);
            else
            if(i == 1)
                mBlurPaint.setMaskFilter(mLargeBlurMaskFilter);
            if(view.getMeasuredWidth() == 0 || view.getMeasuredHeight() == 0)
            {
                return null;
            } else
            {
                Bitmap bitmap = Bitmap.createBitmap(view.getResources().getDisplayMetrics(), view.getMeasuredWidth(), view.getMeasuredHeight(), android.graphics.Bitmap.Config.ARGB_8888);
                mCanvas.setBitmap(bitmap);
                float f = view.getRotationX();
                float f1 = view.getRotation();
                float f2 = view.getTranslationY();
                float f3 = view.getTranslationX();
                view.setRotationX(0.0F);
                view.setRotation(0.0F);
                view.setTranslationY(0.0F);
                view.setTranslationX(0.0F);
                view.draw(mCanvas);
                view.setRotationX(f);
                view.setRotation(f1);
                view.setTranslationY(f2);
                view.setTranslationX(f3);
                drawOutline(mCanvas, bitmap);
                mCanvas.setBitmap(null);
                return bitmap;
            }
        }

        Bitmap createResOutline(View view, int i)
        {
            return createOutline(view, 0, i);
        }

        void drawOutline(Canvas canvas, Bitmap bitmap)
        {
            int ai[] = mTmpXY;
            Bitmap bitmap1 = bitmap.extractAlpha(mBlurPaint, ai);
            mMaskCanvas.setBitmap(bitmap1);
            mMaskCanvas.drawBitmap(bitmap, -ai[0], -ai[1], mErasePaint);
            canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
            canvas.setMatrix(mIdentityMatrix);
            canvas.drawBitmap(bitmap1, ai[0], ai[1], mHolographicPaint);
            mMaskCanvas.setBitmap(null);
            bitmap1.recycle();
        }

        private static final int CLICK_FEEDBACK = 1;
        private static final int RES_OUT = 0;
        private final Paint mBlurPaint = new Paint();
        private final Canvas mCanvas = new Canvas();
        private float mDensity;
        private final Paint mErasePaint = new Paint();
        private final Paint mHolographicPaint = new Paint();
        private final Matrix mIdentityMatrix = new Matrix();
        private BlurMaskFilter mLargeBlurMaskFilter;
        private final Canvas mMaskCanvas = new Canvas();
        private BlurMaskFilter mSmallBlurMaskFilter;
        private final int mTmpXY[] = new int[2];

        HolographicHelper(Context context)
        {
            mDensity = context.getResources().getDisplayMetrics().density;
            mHolographicPaint.setFilterBitmap(true);
            mHolographicPaint.setMaskFilter(TableMaskFilter.CreateClipTable(0, 30));
            mErasePaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_OUT));
            mErasePaint.setFilterBitmap(true);
            mSmallBlurMaskFilter = new BlurMaskFilter(mDensity * 2.0F, android.graphics.BlurMaskFilter.Blur.NORMAL);
            mLargeBlurMaskFilter = new BlurMaskFilter(mDensity * 4F, android.graphics.BlurMaskFilter.Blur.NORMAL);
        }
    }

    class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        Rect getInvalidateRect()
        {
            return invalidateRect;
        }

        void invalidateGlobalRegion(View view, Rect rect)
        {
            globalInvalidateRect.set(rect);
            globalInvalidateRect.union(0, 0, getWidth(), getHeight());
            rect = view;
            boolean flag;
            if(view.getParent() != null)
                flag = view.getParent() instanceof View;
            else
                flag = false;
            if(!flag)
                return;
            boolean flag1 = true;
            parentRect.set(0, 0, 0, 0);
            for(; rect.getParent() != null && (rect.getParent() instanceof View) && parentRect.contains(globalInvalidateRect) ^ true; rect.invalidate(globalInvalidateRect.left, globalInvalidateRect.top, globalInvalidateRect.right, globalInvalidateRect.bottom))
            {
                if(!flag1)
                    globalInvalidateRect.offset(rect.getLeft() - rect.getScrollX(), rect.getTop() - rect.getScrollY());
                flag1 = false;
                rect = (View)rect.getParent();
                parentRect.set(rect.getScrollX(), rect.getScrollY(), rect.getWidth() + rect.getScrollX(), rect.getHeight() + rect.getScrollY());
            }

            rect.invalidate(globalInvalidateRect.left, globalInvalidateRect.top, globalInvalidateRect.right, globalInvalidateRect.bottom);
        }

        void resetInvalidateRect()
        {
            invalidateRect.set(0, 0, 0, 0);
        }

        public void setHorizontalOffset(int i)
        {
            setOffsets(i, verticalOffset);
        }

        public void setOffsets(int i, int j)
        {
            int k = i - horizontalOffset;
            horizontalOffset = i;
            int l = j - verticalOffset;
            verticalOffset = j;
            if(mView != null)
            {
                mView.requestLayout();
                i = Math.min(mView.getLeft() + k, mView.getLeft());
                j = Math.max(mView.getRight() + k, mView.getRight());
                k = Math.min(mView.getTop() + l, mView.getTop());
                l = Math.max(mView.getBottom() + l, mView.getBottom());
                invalidateRectf.set(i, k, j, l);
                float f = -invalidateRectf.left;
                float f1 = -invalidateRectf.top;
                invalidateRectf.offset(f, f1);
                mView.getMatrix().mapRect(invalidateRectf);
                invalidateRectf.offset(-f, -f1);
                invalidateRect.set((int)Math.floor(invalidateRectf.left), (int)Math.floor(invalidateRectf.top), (int)Math.ceil(invalidateRectf.right), (int)Math.ceil(invalidateRectf.bottom));
                invalidateGlobalRegion(mView, invalidateRect);
            }
        }

        public void setVerticalOffset(int i)
        {
            setOffsets(horizontalOffset, i);
        }

        private final Rect globalInvalidateRect;
        int horizontalOffset;
        private final Rect invalidateRect;
        private final RectF invalidateRectf;
        View mView;
        private final Rect parentRect;
        final StackView this$0;
        int verticalOffset;

        LayoutParams(Context context, AttributeSet attributeset)
        {
            this$0 = StackView.this;
            super(context, attributeset);
            parentRect = new Rect();
            invalidateRect = new Rect();
            invalidateRectf = new RectF();
            globalInvalidateRect = new Rect();
            horizontalOffset = 0;
            verticalOffset = 0;
            width = 0;
            height = 0;
        }

        LayoutParams(View view)
        {
            this$0 = StackView.this;
            super(0, 0);
            parentRect = new Rect();
            invalidateRect = new Rect();
            invalidateRectf = new RectF();
            globalInvalidateRect = new Rect();
            width = 0;
            height = 0;
            horizontalOffset = 0;
            verticalOffset = 0;
            mView = view;
        }
    }

    private static class StackFrame extends FrameLayout
    {

        boolean cancelSliderAnimator()
        {
            if(sliderAnimator != null)
            {
                ObjectAnimator objectanimator = (ObjectAnimator)sliderAnimator.get();
                if(objectanimator != null)
                {
                    objectanimator.cancel();
                    return true;
                }
            }
            return false;
        }

        boolean cancelTransformAnimator()
        {
            if(transformAnimator != null)
            {
                ObjectAnimator objectanimator = (ObjectAnimator)transformAnimator.get();
                if(objectanimator != null)
                {
                    objectanimator.cancel();
                    return true;
                }
            }
            return false;
        }

        void setSliderAnimator(ObjectAnimator objectanimator)
        {
            sliderAnimator = new WeakReference(objectanimator);
        }

        void setTransformAnimator(ObjectAnimator objectanimator)
        {
            transformAnimator = new WeakReference(objectanimator);
        }

        WeakReference sliderAnimator;
        WeakReference transformAnimator;

        public StackFrame(Context context)
        {
            super(context);
        }
    }

    private class StackSlider
    {

        private float cubic(float f)
        {
            return (float)(Math.pow(2.0F * f - 1.0F, 3D) + 1.0D) / 2.0F;
        }

        private float getDuration(boolean flag, float f)
        {
            if(mView != null)
            {
                LayoutParams layoutparams = (LayoutParams)mView.getLayoutParams();
                float f1 = (float)Math.hypot(layoutparams.horizontalOffset, layoutparams.verticalOffset);
                float f2 = (float)Math.hypot(StackView._2D_get1(StackView.this), (float)StackView._2D_get1(StackView.this) * 0.4F);
                float f3 = f1;
                if(f1 > f2)
                    f3 = f2;
                if(f == 0.0F)
                {
                    if(flag)
                        f = 1.0F - f3 / f2;
                    else
                        f = f3 / f2;
                    return f * 400F;
                }
                if(flag)
                    f = f3 / Math.abs(f);
                else
                    f = (f2 - f3) / Math.abs(f);
                if(f < 50F || f > 400F)
                    return getDuration(flag, 0.0F);
                else
                    return f;
            } else
            {
                return 0.0F;
            }
        }

        private float highlightAlphaInterpolator(float f)
        {
            if(f < 0.4F)
                return cubic(f / 0.4F) * 0.85F;
            else
                return cubic(1.0F - (f - 0.4F) / 0.6F) * 0.85F;
        }

        private float rotationInterpolator(float f)
        {
            if(f < 0.2F)
                return 0.0F;
            else
                return (f - 0.2F) / 0.8F;
        }

        private float viewAlphaInterpolator(float f)
        {
            if(f > 0.3F)
                return (f - 0.3F) / 0.7F;
            else
                return 0.0F;
        }

        float getDurationForNeutralPosition()
        {
            return getDuration(false, 0.0F);
        }

        float getDurationForNeutralPosition(float f)
        {
            return getDuration(false, f);
        }

        float getDurationForOffscreenPosition()
        {
            return getDuration(true, 0.0F);
        }

        float getDurationForOffscreenPosition(float f)
        {
            return getDuration(true, f);
        }

        public float getXProgress()
        {
            return mXProgress;
        }

        public float getYProgress()
        {
            return mYProgress;
        }

        void setMode(int i)
        {
            mMode = i;
        }

        void setView(View view)
        {
            mView = view;
        }

        public void setXProgress(float f)
        {
            f = Math.max(-2F, Math.min(2.0F, f));
            mXProgress = f;
            if(mView == null)
            {
                return;
            } else
            {
                LayoutParams layoutparams = (LayoutParams)mView.getLayoutParams();
                LayoutParams layoutparams1 = (LayoutParams)StackView._2D_get0(StackView.this).getLayoutParams();
                f *= 0.2F;
                layoutparams.setHorizontalOffset(Math.round((float)StackView._2D_get1(StackView.this) * f));
                layoutparams1.setHorizontalOffset(Math.round((float)StackView._2D_get1(StackView.this) * f));
                return;
            }
        }

        public void setYProgress(float f)
        {
            LayoutParams layoutparams;
            LayoutParams layoutparams1;
            int i;
            f = Math.max(0.0F, Math.min(1.0F, f));
            mYProgress = f;
            if(mView == null)
                return;
            layoutparams = (LayoutParams)mView.getLayoutParams();
            layoutparams1 = (LayoutParams)StackView._2D_get0(StackView.this).getLayoutParams();
            if(StackView._2D_get2(StackView.this) == 0)
                i = 1;
            else
                i = -1;
            if(Float.compare(0.0F, mYProgress) != 0 && Float.compare(1.0F, mYProgress) != 0)
            {
                if(mView.getLayerType() == 0)
                    mView.setLayerType(2, null);
            } else
            if(mView.getLayerType() != 0)
                mView.setLayerType(0, null);
            mMode;
            JVM INSTR tableswitch 0 2: default 132
        //                       0 161
        //                       1 426
        //                       2 359;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            layoutparams.setVerticalOffset(Math.round(-f * (float)i * (float)StackView._2D_get1(StackView.this)));
            layoutparams1.setVerticalOffset(Math.round(-f * (float)i * (float)StackView._2D_get1(StackView.this)));
            StackView._2D_get0(StackView.this).setAlpha(highlightAlphaInterpolator(f));
            float f1 = viewAlphaInterpolator(1.0F - f);
            if(mView.getAlpha() == 0.0F && f1 != 0.0F && mView.getVisibility() != 0)
                mView.setVisibility(0);
            else
            if(f1 == 0.0F && mView.getAlpha() != 0.0F && mView.getVisibility() == 0)
                mView.setVisibility(4);
            mView.setAlpha(f1);
            mView.setRotationX((float)i * 90F * rotationInterpolator(f));
            StackView._2D_get0(StackView.this).setRotationX((float)i * 90F * rotationInterpolator(f));
            continue; /* Loop/switch isn't completed */
_L4:
            f *= 0.2F;
            layoutparams.setVerticalOffset(Math.round((float)(-i) * f * (float)StackView._2D_get1(StackView.this)));
            layoutparams1.setVerticalOffset(Math.round((float)(-i) * f * (float)StackView._2D_get1(StackView.this)));
            StackView._2D_get0(StackView.this).setAlpha(highlightAlphaInterpolator(f));
            continue; /* Loop/switch isn't completed */
_L3:
            f = (1.0F - f) * 0.2F;
            layoutparams.setVerticalOffset(Math.round((float)i * f * (float)StackView._2D_get1(StackView.this)));
            layoutparams1.setVerticalOffset(Math.round((float)i * f * (float)StackView._2D_get1(StackView.this)));
            StackView._2D_get0(StackView.this).setAlpha(highlightAlphaInterpolator(f));
            if(true) goto _L1; else goto _L5
_L5:
        }

        static final int BEGINNING_OF_STACK_MODE = 1;
        static final int END_OF_STACK_MODE = 2;
        static final int NORMAL_MODE = 0;
        int mMode;
        View mView;
        float mXProgress;
        float mYProgress;
        final StackView this$0;

        public StackSlider()
        {
            this$0 = StackView.this;
            super();
            mMode = 0;
        }

        public StackSlider(StackSlider stackslider)
        {
            this$0 = StackView.this;
            super();
            mMode = 0;
            mView = stackslider.mView;
            mYProgress = stackslider.mYProgress;
            mXProgress = stackslider.mXProgress;
            mMode = stackslider.mMode;
        }
    }


    static ImageView _2D_get0(StackView stackview)
    {
        return stackview.mHighlight;
    }

    static int _2D_get1(StackView stackview)
    {
        return stackview.mSlideAmount;
    }

    static int _2D_get2(StackView stackview)
    {
        return stackview.mStackMode;
    }

    public StackView(Context context)
    {
        this(context, null);
    }

    public StackView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101043e);
    }

    public StackView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public StackView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        TAG = "StackView";
        mTouchRect = new Rect();
        mYVelocity = 0;
        mSwipeGestureType = 0;
        mTransitionIsSetup = false;
        mClickFeedbackIsValid = false;
        mFirstLayoutHappened = false;
        mLastInteractionTime = 0L;
        stackInvalidateRect = new Rect();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.StackView, i, j);
        mResOutColor = context.getColor(1, 0);
        mClickColor = context.getColor(0, 0);
        context.recycle();
        initStackView();
    }

    private void beginGestureIfNeeded(float f)
    {
        boolean flag = true;
        if((int)Math.abs(f) > mTouchSlop && mSwipeGestureType == 0)
        {
            int i;
            if(f < 0.0F)
                i = 1;
            else
                i = 2;
            cancelLongPress();
            requestDisallowInterceptTouchEvent(true);
            if(mAdapter == null)
                return;
            int j = getCount();
            int k;
            byte byte0;
            boolean flag1;
            View view;
            if(mStackMode == 0)
            {
                if(i == 2)
                    k = 0;
                else
                    k = 1;
            } else
            if(i == 2)
                k = 1;
            else
                k = 0;
            if(mLoopViews && j == 1)
            {
                if(mStackMode == 0 && i == 1)
                    byte0 = 1;
                else
                if(mStackMode == 1 && i == 2)
                    byte0 = 1;
                else
                    byte0 = 0;
            } else
            {
                byte0 = 0;
            }
            if(mLoopViews && j == 1)
            {
                if(mStackMode == 1 && i == 1)
                    flag1 = true;
                else
                if(mStackMode == 0 && i == 2)
                    flag1 = true;
                else
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
            if(mLoopViews && flag1 ^ true && (byte0 ^ 1) != 0)
                byte0 = 0;
            else
            if(mCurrentWindowStartUnbounded + k == -1 || flag1)
            {
                k++;
                byte0 = 1;
            } else
            if(mCurrentWindowStartUnbounded + k == j - 1 || byte0 != 0)
                byte0 = 2;
            else
                byte0 = 0;
            if(byte0 != 0)
                flag = false;
            mTransitionIsSetup = flag;
            view = getViewAtRelativeIndex(k);
            if(view == null)
                return;
            setupStackSlider(view, byte0);
            mSwipeGestureType = i;
            cancelHandleClick();
        }
    }

    private void handlePointerUp(MotionEvent motionevent)
    {
        int i;
        i = (int)(motionevent.getY(motionevent.findPointerIndex(mActivePointerId)) - mInitialY);
        mLastInteractionTime = System.currentTimeMillis();
        if(mVelocityTracker != null)
        {
            mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
            mYVelocity = (int)mVelocityTracker.getYVelocity(mActivePointerId);
        }
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        if(i <= mSwipeThreshold || mSwipeGestureType != 2 || mStackSlider.mMode != 0) goto _L2; else goto _L1
_L1:
        mSwipeGestureType = 0;
        if(mStackMode == 0)
            showPrevious();
        else
            showNext();
        mHighlight.bringToFront();
_L4:
        mActivePointerId = -1;
        mSwipeGestureType = 0;
        return;
_L2:
        if(i < -mSwipeThreshold && mSwipeGestureType == 1 && mStackSlider.mMode == 0)
        {
            mSwipeGestureType = 0;
            if(mStackMode == 0)
                showNext();
            else
                showPrevious();
            mHighlight.bringToFront();
        } else
        if(mSwipeGestureType == 1)
        {
            int j;
            float f;
            StackSlider stackslider;
            if(mStackMode == 1)
                j = 1;
            else
                j = 0;
            f = j;
            if(mStackMode == 0 || mStackSlider.mMode != 0)
                j = Math.round(mStackSlider.getDurationForNeutralPosition());
            else
                j = Math.round(mStackSlider.getDurationForOffscreenPosition());
            stackslider = new StackSlider(mStackSlider);
            motionevent = PropertyValuesHolder.ofFloat("YProgress", new float[] {
                f
            });
            motionevent = ObjectAnimator.ofPropertyValuesHolder(stackslider, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("XProgress", new float[] {
                    0.0F
                }), motionevent
            });
            motionevent.setDuration(j);
            motionevent.setInterpolator(new LinearInterpolator());
            motionevent.start();
        } else
        if(mSwipeGestureType == 2)
        {
            int k;
            float f1;
            PropertyValuesHolder propertyvaluesholder;
            if(mStackMode == 1)
                k = 0;
            else
                k = 1;
            f1 = k;
            if(mStackMode == 1 || mStackSlider.mMode != 0)
                k = Math.round(mStackSlider.getDurationForNeutralPosition());
            else
                k = Math.round(mStackSlider.getDurationForOffscreenPosition());
            motionevent = new StackSlider(mStackSlider);
            propertyvaluesholder = PropertyValuesHolder.ofFloat("YProgress", new float[] {
                f1
            });
            motionevent = ObjectAnimator.ofPropertyValuesHolder(motionevent, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("XProgress", new float[] {
                    0.0F
                }), propertyvaluesholder
            });
            motionevent.setDuration(k);
            motionevent.start();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initStackView()
    {
        configureViewAnimator(5, 1);
        setStaticTransformationsEnabled(true);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(getContext());
        mTouchSlop = viewconfiguration.getScaledTouchSlop();
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mActivePointerId = -1;
        mHighlight = new ImageView(getContext());
        mHighlight.setLayoutParams(new LayoutParams(mHighlight));
        addViewInLayout(mHighlight, -1, new LayoutParams(mHighlight));
        mClickFeedback = new ImageView(getContext());
        mClickFeedback.setLayoutParams(new LayoutParams(mClickFeedback));
        addViewInLayout(mClickFeedback, -1, new LayoutParams(mClickFeedback));
        mClickFeedback.setVisibility(4);
        mStackSlider = new StackSlider();
        if(sHolographicHelper == null)
            sHolographicHelper = new HolographicHelper(mContext);
        setClipChildren(false);
        setClipToPadding(false);
        mStackMode = 1;
        mWhichChild = -1;
        mFramePadding = (int)Math.ceil(4F * mContext.getResources().getDisplayMetrics().density);
    }

    private void measureChildren()
    {
        int i = getChildCount();
        int j = getMeasuredWidth();
        int k = getMeasuredHeight();
        int l = Math.round((float)j * 0.9F) - mPaddingLeft - mPaddingRight;
        int i1 = Math.round((float)k * 0.9F) - mPaddingTop - mPaddingBottom;
        int j1 = 0;
        int k1 = 0;
        for(int l1 = 0; l1 < i;)
        {
            View view = getChildAt(l1);
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(l, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x80000000));
            int i2 = k1;
            int j2 = j1;
            if(view != mHighlight)
            {
                i2 = k1;
                j2 = j1;
                if(view != mClickFeedback)
                {
                    j2 = view.getMeasuredWidth();
                    int k2 = view.getMeasuredHeight();
                    int l2 = j1;
                    if(j2 > j1)
                        l2 = j2;
                    i2 = k1;
                    j2 = l2;
                    if(k2 > k1)
                    {
                        i2 = k2;
                        j2 = l2;
                    }
                }
            }
            l1++;
            k1 = i2;
            j1 = j2;
        }

        mNewPerspectiveShiftX = (float)j * 0.1F;
        mNewPerspectiveShiftY = (float)k * 0.1F;
        if(j1 > 0 && i > 0 && j1 < l)
            mNewPerspectiveShiftX = j - j1;
        if(k1 > 0 && i > 0 && k1 < i1)
            mNewPerspectiveShiftY = k - k1;
    }

    private void onLayout()
    {
        if(!mFirstLayoutHappened)
        {
            mFirstLayoutHappened = true;
            updateChildTransforms();
        }
        int i = Math.round((float)getMeasuredHeight() * 0.7F);
        if(mSlideAmount != i)
        {
            mSlideAmount = i;
            mSwipeThreshold = Math.round((float)i * 0.2F);
        }
        if(Float.compare(mPerspectiveShiftY, mNewPerspectiveShiftY) != 0 || Float.compare(mPerspectiveShiftX, mNewPerspectiveShiftX) != 0)
        {
            mPerspectiveShiftY = mNewPerspectiveShiftY;
            mPerspectiveShiftX = mNewPerspectiveShiftX;
            updateChildTransforms();
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if(motionevent.getPointerId(i) == mActivePointerId)
        {
            int j;
            View view;
            if(mSwipeGestureType == 2)
                j = 0;
            else
                j = 1;
            view = getViewAtRelativeIndex(j);
            if(view == null)
                return;
            for(int k = 0; k < motionevent.getPointerCount(); k++)
            {
                if(k == i)
                    continue;
                float f = motionevent.getX(k);
                float f1 = motionevent.getY(k);
                mTouchRect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                if(!mTouchRect.contains(Math.round(f), Math.round(f1)))
                    continue;
                float f2 = motionevent.getX(i);
                float f3 = motionevent.getY(i);
                mInitialY = mInitialY + (f1 - f3);
                mInitialX = mInitialX + (f - f2);
                mActivePointerId = motionevent.getPointerId(k);
                if(mVelocityTracker != null)
                    mVelocityTracker.clear();
                return;
            }

            handlePointerUp(motionevent);
        }
    }

    private void pacedScroll(boolean flag)
    {
        if(System.currentTimeMillis() - mLastScrollTime > 100L)
        {
            if(flag)
                showPrevious();
            else
                showNext();
            mLastScrollTime = System.currentTimeMillis();
        }
    }

    private void setupStackSlider(View view, int i)
    {
        mStackSlider.setMode(i);
        if(view != null)
        {
            mHighlight.setImageBitmap(sHolographicHelper.createResOutline(view, mResOutColor));
            mHighlight.setRotation(view.getRotation());
            mHighlight.setTranslationY(view.getTranslationY());
            mHighlight.setTranslationX(view.getTranslationX());
            mHighlight.bringToFront();
            view.bringToFront();
            mStackSlider.setView(view);
            view.setVisibility(0);
        }
    }

    private void transformViewAtIndex(int i, View view, boolean flag)
    {
        float f = mPerspectiveShiftY;
        float f1 = mPerspectiveShiftX;
        float f2;
        float f3;
        if(mStackMode == 1)
        {
            int j = mMaxNumActiveViews - i - 1;
            i = j;
            if(j == mMaxNumActiveViews - 1)
                i = j - 1;
        } else
        {
            int k = i - 1;
            i = k;
            if(k < 0)
                i = k + 1;
        }
        f2 = ((float)i * 1.0F) / (float)(mMaxNumActiveViews - 2);
        f3 = 1.0F - (1.0F - f2) * 0.0F;
        f = f2 * f + (f3 - 1.0F) * (((float)getMeasuredHeight() * 0.9F) / 2.0F);
        f1 = (1.0F - f2) * f1 + (1.0F - f3) * (((float)getMeasuredWidth() * 0.9F) / 2.0F);
        if(view instanceof StackFrame)
            ((StackFrame)view).cancelTransformAnimator();
        if(flag)
        {
            Object obj = PropertyValuesHolder.ofFloat("translationX", new float[] {
                f1
            });
            PropertyValuesHolder propertyvaluesholder = PropertyValuesHolder.ofFloat("translationY", new float[] {
                f
            });
            obj = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("scaleX", new float[] {
                    f3
                }), PropertyValuesHolder.ofFloat("scaleY", new float[] {
                    f3
                }), propertyvaluesholder, obj
            });
            ((ObjectAnimator) (obj)).setDuration(100L);
            if(view instanceof StackFrame)
                ((StackFrame)view).setTransformAnimator(((ObjectAnimator) (obj)));
            ((ObjectAnimator) (obj)).start();
        } else
        {
            view.setTranslationX(f1);
            view.setTranslationY(f);
            view.setScaleX(f3);
            view.setScaleY(f3);
        }
    }

    private void updateChildTransforms()
    {
        for(int i = 0; i < getNumActiveViews(); i++)
        {
            View view = getViewAtRelativeIndex(i);
            if(view != null)
                transformViewAtIndex(i, view, false);
        }

    }

    public void advance()
    {
        long l = System.currentTimeMillis();
        long l1 = mLastInteractionTime;
        if(mAdapter == null)
            return;
        if(getCount() == 1 && mLoopViews)
            return;
        if(mSwipeGestureType == 0 && l - l1 > 5000L)
            showNext();
    }

    void applyTransformForChildAtIndex(View view, int i)
    {
    }

    volatile android.view.ViewGroup.LayoutParams createOrReuseLayoutParams(View view)
    {
        return createOrReuseLayoutParams(view);
    }

    LayoutParams createOrReuseLayoutParams(View view)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if(layoutparams instanceof LayoutParams)
        {
            view = (LayoutParams)layoutparams;
            view.setHorizontalOffset(0);
            view.setVerticalOffset(0);
            view.width = 0;
            view.width = 0;
            return view;
        } else
        {
            return new LayoutParams(view);
        }
    }

    protected void dispatchDraw(Canvas canvas)
    {
        boolean flag = false;
        canvas.getClipBounds(stackInvalidateRect);
        int i = getChildCount();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            View view = getChildAt(j);
            Object obj = (LayoutParams)view.getLayoutParams();
            if(((LayoutParams) (obj)).horizontalOffset == 0 && ((LayoutParams) (obj)).verticalOffset == 0 || view.getAlpha() == 0.0F || view.getVisibility() != 0)
                ((LayoutParams) (obj)).resetInvalidateRect();
            obj = ((LayoutParams) (obj)).getInvalidateRect();
            if(!((Rect) (obj)).isEmpty())
            {
                flag = true;
                stackInvalidateRect.union(((Rect) (obj)));
            }
            j++;
        } while(true);
        if(flag)
        {
            canvas.save(2);
            canvas.clipRect(stackInvalidateRect, android.graphics.Region.Op.UNION);
            super.dispatchDraw(canvas);
            canvas.restore();
        } else
        {
            super.dispatchDraw(canvas);
        }
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/StackView.getName();
    }

    FrameLayout getFrameForChild()
    {
        StackFrame stackframe = new StackFrame(mContext);
        stackframe.setPadding(mFramePadding, mFramePadding, mFramePadding, mFramePadding);
        return stackframe;
    }

    void hideTapFeedback(View view)
    {
        mClickFeedback.setVisibility(4);
        invalidate();
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        if((motionevent.getSource() & 2) == 0) goto _L2; else goto _L1
_L1:
        motionevent.getAction();
        JVM INSTR tableswitch 8 8: default 32
    //                   8 38;
           goto _L2 _L3
_L2:
        return super.onGenericMotionEvent(motionevent);
_L3:
        float f = motionevent.getAxisValue(9);
        if(f < 0.0F)
        {
            pacedScroll(false);
            return true;
        }
        if(f > 0.0F)
        {
            pacedScroll(true);
            return true;
        }
        if(true) goto _L2; else goto _L4
_L4:
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        boolean flag = true;
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(getChildCount() <= 1)
            flag = false;
        accessibilitynodeinfo.setScrollable(flag);
        if(isEnabled())
        {
            if(getDisplayedChild() < getChildCount() - 1)
                accessibilitynodeinfo.addAction(4096);
            if(getDisplayedChild() > 0)
                accessibilitynodeinfo.addAction(8192);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag = false;
        motionevent.getAction() & 0xff;
        JVM INSTR tableswitch 0 6: default 52
    //                   0 63
    //                   1 149
    //                   2 99
    //                   3 149
    //                   4 52
    //                   5 52
    //                   6 141;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L1 _L5
_L1:
        if(mSwipeGestureType != 0)
            flag = true;
        return flag;
_L2:
        if(mActivePointerId == -1)
        {
            mInitialX = motionevent.getX();
            mInitialY = motionevent.getY();
            mActivePointerId = motionevent.getPointerId(0);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int i = motionevent.findPointerIndex(mActivePointerId);
        if(i == -1)
        {
            Log.d("StackView", "Error: No data for our primary pointer.");
            return false;
        }
        beginGestureIfNeeded(motionevent.getY(i) - mInitialY);
        continue; /* Loop/switch isn't completed */
_L5:
        onSecondaryPointerUp(motionevent);
        continue; /* Loop/switch isn't completed */
_L3:
        mActivePointerId = -1;
        mSwipeGestureType = 0;
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        checkForAndHandleDataChanged();
        j = getChildCount();
        for(i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            int i1 = mPaddingLeft;
            int j1 = view.getMeasuredWidth();
            l = mPaddingTop;
            k = view.getMeasuredHeight();
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            view.layout(mPaddingLeft + layoutparams.horizontalOffset, mPaddingTop + layoutparams.verticalOffset, layoutparams.horizontalOffset + (i1 + j1), layoutparams.verticalOffset + (l + k));
        }

        onLayout();
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        int l = android.view.View.MeasureSpec.getSize(j);
        int i1 = android.view.View.MeasureSpec.getMode(i);
        int j1 = android.view.View.MeasureSpec.getMode(j);
        boolean flag;
        if(mReferenceChildWidth != -1 && mReferenceChildHeight != -1)
            flag = true;
        else
            flag = false;
        if(j1 == 0)
        {
            if(flag)
                i = Math.round((float)mReferenceChildHeight * 2.111111F) + mPaddingTop + mPaddingBottom;
            else
                i = 0;
        } else
        {
            i = l;
            if(j1 == 0x80000000)
                if(flag)
                {
                    i = Math.round((float)mReferenceChildHeight * 2.111111F) + mPaddingTop + mPaddingBottom;
                    if(i > l)
                        i = l | 0x1000000;
                } else
                {
                    i = 0;
                }
        }
        if(i1 == 0)
        {
            if(flag)
                j = Math.round((float)mReferenceChildWidth * 2.111111F) + mPaddingLeft + mPaddingRight;
            else
                j = 0;
        } else
        {
            j = k;
            if(j1 == 0x80000000)
                if(flag)
                {
                    j = mReferenceChildWidth + mPaddingLeft + mPaddingRight;
                    if(j > k)
                        j = k | 0x1000000;
                } else
                {
                    j = 0;
                }
        }
        setMeasuredDimension(j, i);
        measureChildren();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        float f;
        float f1;
        float f4;
        super.onTouchEvent(motionevent);
        i = motionevent.getAction();
        int j = motionevent.findPointerIndex(mActivePointerId);
        if(j == -1)
        {
            Log.d("StackView", "Error: No data for our primary pointer.");
            return false;
        }
        f = motionevent.getY(j);
        f1 = motionevent.getX(j);
        f -= mInitialY;
        f4 = mInitialX;
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        i & 0xff;
        JVM INSTR tableswitch 1 6: default 132
    //                   1 290
    //                   2 134
    //                   3 306
    //                   4 132
    //                   5 132
    //                   6 298;
           goto _L1 _L2 _L3 _L4 _L1 _L1 _L5
_L1:
        return true;
_L3:
        beginGestureIfNeeded(f);
        f4 = (f1 - f4) / ((float)mSlideAmount * 1.0F);
        if(mSwipeGestureType == 2)
        {
            float f2 = ((f - (float)mTouchSlop * 1.0F) / (float)mSlideAmount) * 1.0F;
            f = f2;
            if(mStackMode == 1)
                f = 1.0F - f2;
            mStackSlider.setYProgress(1.0F - f);
            mStackSlider.setXProgress(f4);
            return true;
        }
        if(mSwipeGestureType == 1)
        {
            float f3 = (-((float)mTouchSlop * 1.0F + f) / (float)mSlideAmount) * 1.0F;
            f = f3;
            if(mStackMode == 1)
                f = 1.0F - f3;
            mStackSlider.setYProgress(f);
            mStackSlider.setXProgress(f4);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        handlePointerUp(motionevent);
        continue; /* Loop/switch isn't completed */
_L5:
        onSecondaryPointerUp(motionevent);
        continue; /* Loop/switch isn't completed */
_L4:
        mActivePointerId = -1;
        mSwipeGestureType = 0;
        if(true) goto _L1; else goto _L6
_L6:
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(super.performAccessibilityActionInternal(i, bundle))
            return true;
        if(!isEnabled())
            return false;
        switch(i)
        {
        default:
            return false;

        case 4096: 
            if(getDisplayedChild() < getChildCount() - 1)
            {
                showNext();
                return true;
            } else
            {
                return false;
            }

        case 8192: 
            break;
        }
        if(getDisplayedChild() > 0)
        {
            showPrevious();
            return true;
        } else
        {
            return false;
        }
    }

    public void showNext()
    {
        if(mSwipeGestureType != 0)
            return;
        if(!mTransitionIsSetup)
        {
            View view = getViewAtRelativeIndex(1);
            if(view != null)
            {
                setupStackSlider(view, 0);
                mStackSlider.setYProgress(0.0F);
                mStackSlider.setXProgress(0.0F);
            }
        }
        super.showNext();
    }

    void showOnly(int i, boolean flag)
    {
        super.showOnly(i, flag);
        for(i = mCurrentWindowEnd; i >= mCurrentWindowStart; i--)
        {
            int j = modulo(i, getWindowSize());
            if((AdapterViewAnimator.ViewAndMetaData)mViewsMap.get(Integer.valueOf(j)) == null)
                continue;
            View view = ((AdapterViewAnimator.ViewAndMetaData)mViewsMap.get(Integer.valueOf(j))).view;
            if(view != null)
                view.bringToFront();
        }

        if(mHighlight != null)
            mHighlight.bringToFront();
        mTransitionIsSetup = false;
        mClickFeedbackIsValid = false;
    }

    public void showPrevious()
    {
        if(mSwipeGestureType != 0)
            return;
        if(!mTransitionIsSetup)
        {
            View view = getViewAtRelativeIndex(0);
            if(view != null)
            {
                setupStackSlider(view, 0);
                mStackSlider.setYProgress(1.0F);
                mStackSlider.setXProgress(0.0F);
            }
        }
        super.showPrevious();
    }

    void showTapFeedback(View view)
    {
        updateClickFeedback();
        mClickFeedback.setVisibility(0);
        mClickFeedback.bringToFront();
        invalidate();
    }

    void transformViewForTransition(int i, int j, final View view, boolean flag)
    {
        if(!flag)
        {
            ((StackFrame)view).cancelSliderAnimator();
            view.setRotationX(0.0F);
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            layoutparams.setVerticalOffset(0);
            layoutparams.setHorizontalOffset(0);
        }
        if(i != -1 || j != getNumActiveViews() - 1) goto _L2; else goto _L1
_L1:
        transformViewAtIndex(j, view, false);
        view.setVisibility(0);
        view.setAlpha(1.0F);
_L4:
        if(j != -1)
            transformViewAtIndex(j, view, flag);
        return;
_L2:
        if(i == 0 && j == 1)
        {
            ((StackFrame)view).cancelSliderAnimator();
            view.setVisibility(0);
            i = Math.round(mStackSlider.getDurationForNeutralPosition(mYVelocity));
            Object obj = new StackSlider(mStackSlider);
            ((StackSlider) (obj)).setView(view);
            if(flag)
            {
                PropertyValuesHolder propertyvaluesholder = PropertyValuesHolder.ofFloat("YProgress", new float[] {
                    0.0F
                });
                obj = ObjectAnimator.ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofFloat("XProgress", new float[] {
                        0.0F
                    }), propertyvaluesholder
                });
                ((ObjectAnimator) (obj)).setDuration(i);
                ((ObjectAnimator) (obj)).setInterpolator(new LinearInterpolator());
                ((StackFrame)view).setSliderAnimator(((ObjectAnimator) (obj)));
                ((ObjectAnimator) (obj)).start();
            } else
            {
                ((StackSlider) (obj)).setYProgress(0.0F);
                ((StackSlider) (obj)).setXProgress(0.0F);
            }
        } else
        if(i == 1 && j == 0)
        {
            ((StackFrame)view).cancelSliderAnimator();
            i = Math.round(mStackSlider.getDurationForOffscreenPosition(mYVelocity));
            StackSlider stackslider = new StackSlider(mStackSlider);
            stackslider.setView(view);
            if(flag)
            {
                Object obj1 = PropertyValuesHolder.ofFloat("YProgress", new float[] {
                    1.0F
                });
                obj1 = ObjectAnimator.ofPropertyValuesHolder(stackslider, new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofFloat("XProgress", new float[] {
                        0.0F
                    }), obj1
                });
                ((ObjectAnimator) (obj1)).setDuration(i);
                ((ObjectAnimator) (obj1)).setInterpolator(new LinearInterpolator());
                ((StackFrame)view).setSliderAnimator(((ObjectAnimator) (obj1)));
                ((ObjectAnimator) (obj1)).start();
            } else
            {
                stackslider.setYProgress(1.0F);
                stackslider.setXProgress(0.0F);
            }
        } else
        if(j == 0)
        {
            view.setAlpha(0.0F);
            view.setVisibility(4);
        } else
        if((i == 0 || i == 1) && j > 1)
        {
            view.setVisibility(0);
            view.setAlpha(1.0F);
            view.setRotationX(0.0F);
            LayoutParams layoutparams1 = (LayoutParams)view.getLayoutParams();
            layoutparams1.setVerticalOffset(0);
            layoutparams1.setHorizontalOffset(0);
        } else
        if(i == -1)
        {
            view.setAlpha(1.0F);
            view.setVisibility(0);
        } else
        if(j == -1)
            if(flag)
                postDelayed(new Runnable() {

                    public void run()
                    {
                        view.setAlpha(0.0F);
                    }

                    final StackView this$0;
                    final View val$view;

            
            {
                this$0 = StackView.this;
                view = view1;
                super();
            }
                }
, 100L);
            else
                view.setAlpha(0.0F);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void updateClickFeedback()
    {
        if(!mClickFeedbackIsValid)
        {
            View view = getViewAtRelativeIndex(1);
            if(view != null)
            {
                mClickFeedback.setImageBitmap(sHolographicHelper.createClickOutline(view, mClickColor));
                mClickFeedback.setTranslationX(view.getTranslationX());
                mClickFeedback.setTranslationY(view.getTranslationY());
            }
            mClickFeedbackIsValid = true;
        }
    }

    private static final int DEFAULT_ANIMATION_DURATION = 400;
    private static final int FRAME_PADDING = 4;
    private static final int GESTURE_NONE = 0;
    private static final int GESTURE_SLIDE_DOWN = 2;
    private static final int GESTURE_SLIDE_UP = 1;
    private static final int INVALID_POINTER = -1;
    private static final int ITEMS_SLIDE_DOWN = 1;
    private static final int ITEMS_SLIDE_UP = 0;
    private static final int MINIMUM_ANIMATION_DURATION = 50;
    private static final int MIN_TIME_BETWEEN_INTERACTION_AND_AUTOADVANCE = 5000;
    private static final long MIN_TIME_BETWEEN_SCROLLS = 100L;
    private static final int NUM_ACTIVE_VIEWS = 5;
    private static final float PERSPECTIVE_SCALE_FACTOR = 0F;
    private static final float PERSPECTIVE_SHIFT_FACTOR_X = 0.1F;
    private static final float PERSPECTIVE_SHIFT_FACTOR_Y = 0.1F;
    private static final float SLIDE_UP_RATIO = 0.7F;
    private static final int STACK_RELAYOUT_DURATION = 100;
    private static final float SWIPE_THRESHOLD_RATIO = 0.2F;
    private static HolographicHelper sHolographicHelper;
    private final String TAG;
    private int mActivePointerId;
    private int mClickColor;
    private ImageView mClickFeedback;
    private boolean mClickFeedbackIsValid;
    private boolean mFirstLayoutHappened;
    private int mFramePadding;
    private ImageView mHighlight;
    private float mInitialX;
    private float mInitialY;
    private long mLastInteractionTime;
    private long mLastScrollTime;
    private int mMaximumVelocity;
    private float mNewPerspectiveShiftX;
    private float mNewPerspectiveShiftY;
    private float mPerspectiveShiftX;
    private float mPerspectiveShiftY;
    private int mResOutColor;
    private int mSlideAmount;
    private int mStackMode;
    private StackSlider mStackSlider;
    private int mSwipeGestureType;
    private int mSwipeThreshold;
    private final Rect mTouchRect;
    private int mTouchSlop;
    private boolean mTransitionIsSetup;
    private VelocityTracker mVelocityTracker;
    private int mYVelocity;
    private final Rect stackInvalidateRect;
}
