// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.*;
import android.view.animation.Interpolator;
import com.android.internal.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.android.internal.widget.helper:
//            ItemTouchUIUtilImpl, ItemTouchUIUtil

public class ItemTouchHelper extends com.android.internal.widget.RecyclerView.ItemDecoration
    implements com.android.internal.widget.RecyclerView.OnChildAttachStateChangeListener
{
    public static abstract class Callback
    {

        public static int convertToRelativeDirection(int i, int j)
        {
            int k = i & 0xc0c0c;
            if(k == 0)
                return i;
            i &= k;
            if(j == 0)
                return i | k << 2;
            else
                return i | k << 1 & 0xfff3f3f3 | (k << 1 & 0xc0c0c) << 2;
        }

        public static ItemTouchUIUtil getDefaultUIUtil()
        {
            return sUICallback;
        }

        private int getMaxDragScroll(RecyclerView recyclerview)
        {
            if(mCachedMaxScrollSpeed == -1)
                mCachedMaxScrollSpeed = recyclerview.getResources().getDimensionPixelSize(0x10500a6);
            return mCachedMaxScrollSpeed;
        }

        public static int makeFlag(int i, int j)
        {
            return j << i * 8;
        }

        public static int makeMovementFlags(int i, int j)
        {
            return makeFlag(0, j | i) | makeFlag(1, j) | makeFlag(2, i);
        }

        public boolean canDropOver(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder, com.android.internal.widget.RecyclerView.ViewHolder viewholder1)
        {
            return true;
        }

        public com.android.internal.widget.RecyclerView.ViewHolder chooseDropTarget(com.android.internal.widget.RecyclerView.ViewHolder viewholder, List list, int i, int j)
        {
            int k = viewholder.itemView.getWidth();
            int l = viewholder.itemView.getHeight();
            com.android.internal.widget.RecyclerView.ViewHolder viewholder1 = null;
            int i1 = -1;
            int j1 = i - viewholder.itemView.getLeft();
            int k1 = j - viewholder.itemView.getTop();
            int l1 = list.size();
            for(int i2 = 0; i2 < l1; i2++)
            {
                com.android.internal.widget.RecyclerView.ViewHolder viewholder2 = (com.android.internal.widget.RecyclerView.ViewHolder)list.get(i2);
                com.android.internal.widget.RecyclerView.ViewHolder viewholder3 = viewholder1;
                int j2 = i1;
                if(j1 > 0)
                {
                    int k2 = viewholder2.itemView.getRight() - (i + k);
                    viewholder3 = viewholder1;
                    j2 = i1;
                    if(k2 < 0)
                    {
                        viewholder3 = viewholder1;
                        j2 = i1;
                        if(viewholder2.itemView.getRight() > viewholder.itemView.getRight())
                        {
                            k2 = Math.abs(k2);
                            viewholder3 = viewholder1;
                            j2 = i1;
                            if(k2 > i1)
                            {
                                j2 = k2;
                                viewholder3 = viewholder2;
                            }
                        }
                    }
                }
                viewholder1 = viewholder3;
                i1 = j2;
                if(j1 < 0)
                {
                    int l2 = viewholder2.itemView.getLeft() - i;
                    viewholder1 = viewholder3;
                    i1 = j2;
                    if(l2 > 0)
                    {
                        viewholder1 = viewholder3;
                        i1 = j2;
                        if(viewholder2.itemView.getLeft() < viewholder.itemView.getLeft())
                        {
                            l2 = Math.abs(l2);
                            viewholder1 = viewholder3;
                            i1 = j2;
                            if(l2 > j2)
                            {
                                i1 = l2;
                                viewholder1 = viewholder2;
                            }
                        }
                    }
                }
                viewholder3 = viewholder1;
                j2 = i1;
                if(k1 < 0)
                {
                    int i3 = viewholder2.itemView.getTop() - j;
                    viewholder3 = viewholder1;
                    j2 = i1;
                    if(i3 > 0)
                    {
                        viewholder3 = viewholder1;
                        j2 = i1;
                        if(viewholder2.itemView.getTop() < viewholder.itemView.getTop())
                        {
                            i3 = Math.abs(i3);
                            viewholder3 = viewholder1;
                            j2 = i1;
                            if(i3 > i1)
                            {
                                j2 = i3;
                                viewholder3 = viewholder2;
                            }
                        }
                    }
                }
                viewholder1 = viewholder3;
                i1 = j2;
                if(k1 <= 0)
                    continue;
                int j3 = viewholder2.itemView.getBottom() - (j + l);
                viewholder1 = viewholder3;
                i1 = j2;
                if(j3 >= 0)
                    continue;
                viewholder1 = viewholder3;
                i1 = j2;
                if(viewholder2.itemView.getBottom() <= viewholder.itemView.getBottom())
                    continue;
                j3 = Math.abs(j3);
                viewholder1 = viewholder3;
                i1 = j2;
                if(j3 > j2)
                {
                    i1 = j3;
                    viewholder1 = viewholder2;
                }
            }

            return viewholder1;
        }

        public void clearView(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            sUICallback.clearView(viewholder.itemView);
        }

        public int convertToAbsoluteDirection(int i, int j)
        {
            int k = i & 0x303030;
            if(k == 0)
                return i;
            i &= k;
            if(j == 0)
                return i | k >> 2;
            else
                return i | k >> 1 & 0xffcfcfcf | (k >> 1 & 0x303030) >> 2;
        }

        final int getAbsoluteMovementFlags(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            return convertToAbsoluteDirection(getMovementFlags(recyclerview, viewholder), recyclerview.getLayoutDirection());
        }

        public long getAnimationDuration(RecyclerView recyclerview, int i, float f, float f1)
        {
            recyclerview = recyclerview.getItemAnimator();
            if(recyclerview == null)
            {
                if(i == 8)
                    i = 200;
                else
                    i = 250;
                return (long)i;
            }
            long l;
            if(i == 8)
                l = recyclerview.getMoveDuration();
            else
                l = recyclerview.getRemoveDuration();
            return l;
        }

        public int getBoundingBoxMargin()
        {
            return 0;
        }

        public float getMoveThreshold(com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            return 0.5F;
        }

        public abstract int getMovementFlags(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder);

        public float getSwipeEscapeVelocity(float f)
        {
            return f;
        }

        public float getSwipeThreshold(com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            return 0.5F;
        }

        public float getSwipeVelocityThreshold(float f)
        {
            return f;
        }

        boolean hasDragFlag(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            boolean flag = false;
            if((0xff0000 & getAbsoluteMovementFlags(recyclerview, viewholder)) != 0)
                flag = true;
            return flag;
        }

        boolean hasSwipeFlag(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            boolean flag = false;
            if((0xff00 & getAbsoluteMovementFlags(recyclerview, viewholder)) != 0)
                flag = true;
            return flag;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerview, int i, int j, int k, long l)
        {
            int i1 = getMaxDragScroll(recyclerview);
            int j1 = Math.abs(j);
            k = (int)Math.signum(j);
            float f = Math.min(1.0F, ((float)j1 * 1.0F) / (float)i);
            i = (int)((float)(k * i1) * sDragViewScrollCapInterpolator.getInterpolation(f));
            if(l > 2000L)
                f = 1.0F;
            else
                f = (float)l / 2000F;
            i = (int)((float)i * sDragScrollInterpolator.getInterpolation(f));
            if(i == 0)
            {
                if(j > 0)
                    i = 1;
                else
                    i = -1;
                return i;
            } else
            {
                return i;
            }
        }

        public boolean isItemViewSwipeEnabled()
        {
            return true;
        }

        public boolean isLongPressDragEnabled()
        {
            return true;
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder, float f, float f1, int i, boolean flag)
        {
            sUICallback.onDraw(canvas, recyclerview, viewholder.itemView, f, f1, i, flag);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder, float f, float f1, int i, boolean flag)
        {
            sUICallback.onDrawOver(canvas, recyclerview, viewholder.itemView, f, f1, i, flag);
        }

        void onDraw(Canvas canvas, RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder, List list, int i, float f, float f1)
        {
            int j = list.size();
            for(int k = 0; k < j; k++)
            {
                RecoverAnimation recoveranimation = (RecoverAnimation)list.get(k);
                recoveranimation.update();
                int i1 = canvas.save();
                onChildDraw(canvas, recyclerview, recoveranimation.mViewHolder, recoveranimation.mX, recoveranimation.mY, recoveranimation.mActionState, false);
                canvas.restoreToCount(i1);
            }

            if(viewholder != null)
            {
                int l = canvas.save();
                onChildDraw(canvas, recyclerview, viewholder, f, f1, i, true);
                canvas.restoreToCount(l);
            }
        }

        void onDrawOver(Canvas canvas, RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder, List list, int i, float f, float f1)
        {
            int j = list.size();
            for(int k = 0; k < j; k++)
            {
                RecoverAnimation recoveranimation = (RecoverAnimation)list.get(k);
                int i1 = canvas.save();
                onChildDrawOver(canvas, recyclerview, recoveranimation.mViewHolder, recoveranimation.mX, recoveranimation.mY, recoveranimation.mActionState, false);
                canvas.restoreToCount(i1);
            }

            if(viewholder != null)
            {
                int l = canvas.save();
                onChildDrawOver(canvas, recyclerview, viewholder, f, f1, i, true);
                canvas.restoreToCount(l);
            }
            boolean flag = false;
            i = j - 1;
            while(i >= 0) 
            {
                canvas = (RecoverAnimation)list.get(i);
                if(((RecoverAnimation) (canvas)).mEnded && ((RecoverAnimation) (canvas)).mIsPendingCleanup ^ true)
                    list.remove(i);
                else
                if(!((RecoverAnimation) (canvas)).mEnded)
                    flag = true;
                i--;
            }
            if(flag)
                recyclerview.invalidate();
        }

        public abstract boolean onMove(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder, com.android.internal.widget.RecyclerView.ViewHolder viewholder1);

        public void onMoved(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder, int i, com.android.internal.widget.RecyclerView.ViewHolder viewholder1, int j, int k, int l)
        {
            com.android.internal.widget.RecyclerView.LayoutManager layoutmanager = recyclerview.getLayoutManager();
            if(layoutmanager instanceof ViewDropHandler)
            {
                ((ViewDropHandler)layoutmanager).prepareForDrop(viewholder.itemView, viewholder1.itemView, k, l);
                return;
            }
            if(layoutmanager.canScrollHorizontally())
            {
                if(layoutmanager.getDecoratedLeft(viewholder1.itemView) <= recyclerview.getPaddingLeft())
                    recyclerview.scrollToPosition(j);
                if(layoutmanager.getDecoratedRight(viewholder1.itemView) >= recyclerview.getWidth() - recyclerview.getPaddingRight())
                    recyclerview.scrollToPosition(j);
            }
            if(layoutmanager.canScrollVertically())
            {
                if(layoutmanager.getDecoratedTop(viewholder1.itemView) <= recyclerview.getPaddingTop())
                    recyclerview.scrollToPosition(j);
                if(layoutmanager.getDecoratedBottom(viewholder1.itemView) >= recyclerview.getHeight() - recyclerview.getPaddingBottom())
                    recyclerview.scrollToPosition(j);
            }
        }

        public void onSelectedChanged(com.android.internal.widget.RecyclerView.ViewHolder viewholder, int i)
        {
            if(viewholder != null)
                sUICallback.onSelected(viewholder.itemView);
        }

        public abstract void onSwiped(com.android.internal.widget.RecyclerView.ViewHolder viewholder, int i);

        private static final int ABS_HORIZONTAL_DIR_FLAGS = 0xc0c0c;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
        static final int RELATIVE_DIR_FLAGS = 0x303030;
        private static final Interpolator sDragScrollInterpolator = new Interpolator() {

            public float getInterpolation(float f)
            {
                return f * f * f * f * f;
            }

        }
;
        private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() {

            public float getInterpolation(float f)
            {
                f--;
                return f * f * f * f * f + 1.0F;
            }

        }
;
        private static final ItemTouchUIUtil sUICallback = new ItemTouchUIUtilImpl();
        private int mCachedMaxScrollSpeed;


        public Callback()
        {
            mCachedMaxScrollSpeed = -1;
        }
    }

    private class ItemTouchHelperGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onDown(MotionEvent motionevent)
        {
            return true;
        }

        public void onLongPress(MotionEvent motionevent)
        {
            Object obj = findChildView(motionevent);
            if(obj != null)
            {
                obj = mRecyclerView.getChildViewHolder(((View) (obj)));
                if(obj != null)
                {
                    if(!mCallback.hasDragFlag(mRecyclerView, ((com.android.internal.widget.RecyclerView.ViewHolder) (obj))))
                        return;
                    if(motionevent.getPointerId(0) == mActivePointerId)
                    {
                        int i = motionevent.findPointerIndex(mActivePointerId);
                        float f = motionevent.getX(i);
                        float f1 = motionevent.getY(i);
                        mInitialTouchX = f;
                        mInitialTouchY = f1;
                        motionevent = ItemTouchHelper.this;
                        mDy = 0.0F;
                        motionevent.mDx = 0.0F;
                        if(mCallback.isLongPressDragEnabled())
                            select(((com.android.internal.widget.RecyclerView.ViewHolder) (obj)), 2);
                    }
                }
            }
        }

        final ItemTouchHelper this$0;

        ItemTouchHelperGestureListener()
        {
            this$0 = ItemTouchHelper.this;
            super();
        }
    }

    private class RecoverAnimation
        implements android.animation.Animator.AnimatorListener
    {

        public void cancel()
        {
            mValueAnimator.cancel();
        }

        public void onAnimationCancel(Animator animator)
        {
            setFraction(1.0F);
        }

        public void onAnimationEnd(Animator animator)
        {
            if(!mEnded)
                mViewHolder.setIsRecyclable(true);
            mEnded = true;
        }

        public void onAnimationRepeat(Animator animator)
        {
        }

        public void onAnimationStart(Animator animator)
        {
        }

        public void setDuration(long l)
        {
            mValueAnimator.setDuration(l);
        }

        public void setFraction(float f)
        {
            mFraction = f;
        }

        public void start()
        {
            mViewHolder.setIsRecyclable(false);
            mValueAnimator.start();
        }

        public void update()
        {
            if(mStartDx == mTargetX)
                mX = mViewHolder.itemView.getTranslationX();
            else
                mX = mStartDx + mFraction * (mTargetX - mStartDx);
            if(mStartDy == mTargetY)
                mY = mViewHolder.itemView.getTranslationY();
            else
                mY = mStartDy + mFraction * (mTargetY - mStartDy);
        }

        final int mActionState;
        final int mAnimationType;
        boolean mEnded;
        private float mFraction;
        public boolean mIsPendingCleanup;
        boolean mOverridden;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        private final ValueAnimator mValueAnimator = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        final com.android.internal.widget.RecyclerView.ViewHolder mViewHolder;
        float mX;
        float mY;
        final ItemTouchHelper this$0;

        RecoverAnimation(com.android.internal.widget.RecyclerView.ViewHolder viewholder, int i, int j, float f, float f1, float f2, 
                float f3)
        {
            this$0 = ItemTouchHelper.this;
            super();
            mOverridden = false;
            mEnded = false;
            mActionState = j;
            mAnimationType = i;
            mViewHolder = viewholder;
            mStartDx = f;
            mStartDy = f1;
            mTargetX = f2;
            mTargetY = f3;
            mValueAnimator.addUpdateListener(new _cls1());
            mValueAnimator.setTarget(viewholder.itemView);
            mValueAnimator.addListener(this);
            setFraction(0.0F);
        }
    }

    public static abstract class SimpleCallback extends Callback
    {

        public int getDragDirs(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            return mDefaultDragDirs;
        }

        public int getMovementFlags(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            return makeMovementFlags(getDragDirs(recyclerview, viewholder), getSwipeDirs(recyclerview, viewholder));
        }

        public int getSwipeDirs(RecyclerView recyclerview, com.android.internal.widget.RecyclerView.ViewHolder viewholder)
        {
            return mDefaultSwipeDirs;
        }

        public void setDefaultDragDirs(int i)
        {
            mDefaultDragDirs = i;
        }

        public void setDefaultSwipeDirs(int i)
        {
            mDefaultSwipeDirs = i;
        }

        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;

        public SimpleCallback(int i, int j)
        {
            mDefaultSwipeDirs = j;
            mDefaultDragDirs = i;
        }
    }

    public static interface ViewDropHandler
    {

        public abstract void prepareForDrop(View view, View view1, int i, int j);
    }


    public ItemTouchHelper(Callback callback)
    {
        mSelected = null;
        mActivePointerId = -1;
        mActionState = 0;
        mRecoverAnimations = new ArrayList();
        mChildDrawingOrderCallback = null;
        mOverdrawChild = null;
        mOverdrawChildPosition = -1;
        mCallback = callback;
    }

    private void addChildDrawingOrderCallback()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return;
        if(mChildDrawingOrderCallback == null)
            mChildDrawingOrderCallback = new com.android.internal.widget.RecyclerView.ChildDrawingOrderCallback() {

                public int onGetChildDrawingOrder(int i, int j)
                {
                    if(mOverdrawChild == null)
                        return j;
                    int k = mOverdrawChildPosition;
                    int l = k;
                    if(k == -1)
                    {
                        l = mRecyclerView.indexOfChild(mOverdrawChild);
                        mOverdrawChildPosition = l;
                    }
                    if(j == i - 1)
                        return l;
                    if(j >= l)
                        j++;
                    return j;
                }

                final ItemTouchHelper this$0;

            
            {
                this$0 = ItemTouchHelper.this;
                super();
            }
            }
;
        mRecyclerView.setChildDrawingOrderCallback(mChildDrawingOrderCallback);
    }

    private int checkHorizontalSwipe(com.android.internal.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        if((i & 0xc) != 0)
        {
            byte byte0;
            if(mDx > 0.0F)
                byte0 = 8;
            else
                byte0 = 4;
            if(mVelocityTracker != null && mActivePointerId > -1)
            {
                mVelocityTracker.computeCurrentVelocity(1000, mCallback.getSwipeVelocityThreshold(mMaxSwipeVelocity));
                float f = mVelocityTracker.getXVelocity(mActivePointerId);
                float f2 = mVelocityTracker.getYVelocity(mActivePointerId);
                byte byte1;
                if(f > 0.0F)
                    byte1 = 8;
                else
                    byte1 = 4;
                f = Math.abs(f);
                if((byte1 & i) != 0 && byte0 == byte1 && f >= mCallback.getSwipeEscapeVelocity(mSwipeEscapeVelocity) && f > Math.abs(f2))
                    return byte1;
            }
            float f1 = mRecyclerView.getWidth();
            float f3 = mCallback.getSwipeThreshold(viewholder);
            if((i & byte0) != 0 && Math.abs(mDx) > f1 * f3)
                return byte0;
        }
        return 0;
    }

    private int checkVerticalSwipe(com.android.internal.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        if((i & 3) != 0)
        {
            byte byte0;
            if(mDy > 0.0F)
                byte0 = 2;
            else
                byte0 = 1;
            if(mVelocityTracker != null && mActivePointerId > -1)
            {
                mVelocityTracker.computeCurrentVelocity(1000, mCallback.getSwipeVelocityThreshold(mMaxSwipeVelocity));
                float f = mVelocityTracker.getXVelocity(mActivePointerId);
                float f2 = mVelocityTracker.getYVelocity(mActivePointerId);
                byte byte1;
                if(f2 > 0.0F)
                    byte1 = 2;
                else
                    byte1 = 1;
                f2 = Math.abs(f2);
                if((byte1 & i) != 0 && byte1 == byte0 && f2 >= mCallback.getSwipeEscapeVelocity(mSwipeEscapeVelocity) && f2 > Math.abs(f))
                    return byte1;
            }
            float f3 = mRecyclerView.getHeight();
            float f1 = mCallback.getSwipeThreshold(viewholder);
            if((i & byte0) != 0 && Math.abs(mDy) > f3 * f1)
                return byte0;
        }
        return 0;
    }

    private void destroyCallbacks()
    {
        mRecyclerView.removeItemDecoration(this);
        mRecyclerView.removeOnItemTouchListener(mOnItemTouchListener);
        mRecyclerView.removeOnChildAttachStateChangeListener(this);
        for(int i = mRecoverAnimations.size() - 1; i >= 0; i--)
        {
            RecoverAnimation recoveranimation = (RecoverAnimation)mRecoverAnimations.get(0);
            mCallback.clearView(mRecyclerView, recoveranimation.mViewHolder);
        }

        mRecoverAnimations.clear();
        mOverdrawChild = null;
        mOverdrawChildPosition = -1;
        releaseVelocityTracker();
    }

    private List findSwapTargets(com.android.internal.widget.RecyclerView.ViewHolder viewholder)
    {
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        View view;
        int i;
        com.android.internal.widget.RecyclerView.LayoutManager layoutmanager;
        int l1;
        if(mSwapTargets == null)
        {
            mSwapTargets = new ArrayList();
            mDistances = new ArrayList();
        } else
        {
            mSwapTargets.clear();
            mDistances.clear();
        }
        i = mCallback.getBoundingBoxMargin();
        j = Math.round(mSelectedStartX + mDx) - i;
        k = Math.round(mSelectedStartY + mDy) - i;
        l = viewholder.itemView.getWidth() + j + i * 2;
        i1 = viewholder.itemView.getHeight() + k + i * 2;
        j1 = (j + l) / 2;
        k1 = (k + i1) / 2;
        layoutmanager = mRecyclerView.getLayoutManager();
        l1 = layoutmanager.getChildCount();
        i = 0;
        if(i >= l1)
            break; /* Loop/switch isn't completed */
        view = layoutmanager.getChildAt(i);
        if(view != viewholder.itemView && view.getBottom() >= k && view.getTop() <= i1 && view.getRight() >= j && view.getLeft() <= l)
        {
            com.android.internal.widget.RecyclerView.ViewHolder viewholder1 = mRecyclerView.getChildViewHolder(view);
            if(mCallback.canDropOver(mRecyclerView, mSelected, viewholder1))
            {
                int i2 = Math.abs(j1 - (view.getLeft() + view.getRight()) / 2);
                int j2 = Math.abs(k1 - (view.getTop() + view.getBottom()) / 2);
                int l2 = i2 * i2 + j2 * j2;
                i2 = 0;
                int i3 = mSwapTargets.size();
                for(int k2 = 0; k2 < i3 && l2 > ((Integer)mDistances.get(k2)).intValue(); k2++)
                    i2++;

                mSwapTargets.add(i2, viewholder1);
                mDistances.add(i2, Integer.valueOf(l2));
            }
        }
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_134;
_L1:
        return mSwapTargets;
    }

    private com.android.internal.widget.RecyclerView.ViewHolder findSwipedView(MotionEvent motionevent)
    {
        com.android.internal.widget.RecyclerView.LayoutManager layoutmanager = mRecyclerView.getLayoutManager();
        if(mActivePointerId == -1)
            return null;
        int i = motionevent.findPointerIndex(mActivePointerId);
        float f = motionevent.getX(i);
        float f1 = mInitialTouchX;
        float f2 = motionevent.getY(i);
        float f3 = mInitialTouchY;
        f = Math.abs(f - f1);
        f3 = Math.abs(f2 - f3);
        if(f < (float)mSlop && f3 < (float)mSlop)
            return null;
        if(f > f3 && layoutmanager.canScrollHorizontally())
            return null;
        if(f3 > f && layoutmanager.canScrollVertically())
            return null;
        motionevent = findChildView(motionevent);
        if(motionevent == null)
            return null;
        else
            return mRecyclerView.getChildViewHolder(motionevent);
    }

    private void getSelectedDxDy(float af[])
    {
        if((mSelectedFlags & 0xc) != 0)
            af[0] = (mSelectedStartX + mDx) - (float)mSelected.itemView.getLeft();
        else
            af[0] = mSelected.itemView.getTranslationX();
        if((mSelectedFlags & 3) != 0)
            af[1] = (mSelectedStartY + mDy) - (float)mSelected.itemView.getTop();
        else
            af[1] = mSelected.itemView.getTranslationY();
    }

    private static boolean hitTest(View view, float f, float f1, float f2, float f3)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(f >= f2)
        {
            flag1 = flag;
            if(f <= (float)view.getWidth() + f2)
            {
                flag1 = flag;
                if(f1 >= f3)
                {
                    flag1 = flag;
                    if(f1 <= (float)view.getHeight() + f3)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    private void initGestureDetector()
    {
        if(mGestureDetector != null)
        {
            return;
        } else
        {
            mGestureDetector = new GestureDetector(mRecyclerView.getContext(), new ItemTouchHelperGestureListener());
            return;
        }
    }

    private void releaseVelocityTracker()
    {
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private void setupCallbacks()
    {
        mSlop = ViewConfiguration.get(mRecyclerView.getContext()).getScaledTouchSlop();
        mRecyclerView.addItemDecoration(this);
        mRecyclerView.addOnItemTouchListener(mOnItemTouchListener);
        mRecyclerView.addOnChildAttachStateChangeListener(this);
        initGestureDetector();
    }

    private int swipeIfNecessary(com.android.internal.widget.RecyclerView.ViewHolder viewholder)
    {
        if(mActionState == 2)
            return 0;
        int i = mCallback.getMovementFlags(mRecyclerView, viewholder);
        int j = (mCallback.convertToAbsoluteDirection(i, mRecyclerView.getLayoutDirection()) & 0xff00) >> 8;
        if(j == 0)
            return 0;
        i = (i & 0xff00) >> 8;
        if(Math.abs(mDx) > Math.abs(mDy))
        {
            int k = checkHorizontalSwipe(viewholder, j);
            if(k > 0)
                if((i & k) == 0)
                    return Callback.convertToRelativeDirection(k, mRecyclerView.getLayoutDirection());
                else
                    return k;
            i = checkVerticalSwipe(viewholder, j);
            if(i > 0)
                return i;
        } else
        {
            int l = checkVerticalSwipe(viewholder, j);
            if(l > 0)
                return l;
            j = checkHorizontalSwipe(viewholder, j);
            if(j > 0)
                if((i & j) == 0)
                    return Callback.convertToRelativeDirection(j, mRecyclerView.getLayoutDirection());
                else
                    return j;
        }
        return 0;
    }

    public void attachToRecyclerView(RecyclerView recyclerview)
    {
        if(mRecyclerView == recyclerview)
            return;
        if(mRecyclerView != null)
            destroyCallbacks();
        mRecyclerView = recyclerview;
        if(mRecyclerView != null)
        {
            recyclerview = recyclerview.getResources();
            mSwipeEscapeVelocity = recyclerview.getDimension(0x10500a8);
            mMaxSwipeVelocity = recyclerview.getDimension(0x10500a7);
            setupCallbacks();
        }
    }

    boolean checkSelectForSwipe(int i, MotionEvent motionevent, int j)
    {
        while(mSelected != null || i != 2 || mActionState == 2 || mCallback.isItemViewSwipeEnabled() ^ true) 
            return false;
        if(mRecyclerView.getScrollState() == 1)
            return false;
        com.android.internal.widget.RecyclerView.ViewHolder viewholder = findSwipedView(motionevent);
        if(viewholder == null)
            return false;
        i = (0xff00 & mCallback.getAbsoluteMovementFlags(mRecyclerView, viewholder)) >> 8;
        if(i == 0)
            return false;
        float f = motionevent.getX(j);
        float f1 = motionevent.getY(j);
        f -= mInitialTouchX;
        f1 -= mInitialTouchY;
        float f2 = Math.abs(f);
        float f3 = Math.abs(f1);
        if(f2 < (float)mSlop && f3 < (float)mSlop)
            return false;
        if(f2 > f3)
        {
            if(f < 0.0F && (i & 4) == 0)
                return false;
            if(f > 0.0F && (i & 8) == 0)
                return false;
        } else
        {
            if(f1 < 0.0F && (i & 1) == 0)
                return false;
            if(f1 > 0.0F && (i & 2) == 0)
                return false;
        }
        mDy = 0.0F;
        mDx = 0.0F;
        mActivePointerId = motionevent.getPointerId(0);
        select(viewholder, 1);
        return true;
    }

    int endRecoverAnimation(com.android.internal.widget.RecyclerView.ViewHolder viewholder, boolean flag)
    {
        for(int i = mRecoverAnimations.size() - 1; i >= 0; i--)
        {
            RecoverAnimation recoveranimation = (RecoverAnimation)mRecoverAnimations.get(i);
            if(recoveranimation.mViewHolder == viewholder)
            {
                recoveranimation.mOverridden = recoveranimation.mOverridden | flag;
                if(!recoveranimation.mEnded)
                    recoveranimation.cancel();
                mRecoverAnimations.remove(i);
                return recoveranimation.mAnimationType;
            }
        }

        return 0;
    }

    RecoverAnimation findAnimation(MotionEvent motionevent)
    {
        if(mRecoverAnimations.isEmpty())
            return null;
        motionevent = findChildView(motionevent);
        for(int i = mRecoverAnimations.size() - 1; i >= 0; i--)
        {
            RecoverAnimation recoveranimation = (RecoverAnimation)mRecoverAnimations.get(i);
            if(recoveranimation.mViewHolder.itemView == motionevent)
                return recoveranimation;
        }

        return null;
    }

    View findChildView(MotionEvent motionevent)
    {
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        if(mSelected != null)
        {
            motionevent = mSelected.itemView;
            if(hitTest(motionevent, f, f1, mSelectedStartX + mDx, mSelectedStartY + mDy))
                return motionevent;
        }
        for(int i = mRecoverAnimations.size() - 1; i >= 0; i--)
        {
            RecoverAnimation recoveranimation = (RecoverAnimation)mRecoverAnimations.get(i);
            motionevent = recoveranimation.mViewHolder.itemView;
            if(hitTest(motionevent, f, f1, recoveranimation.mX, recoveranimation.mY))
                return motionevent;
        }

        return mRecyclerView.findChildViewUnder(f, f1);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, com.android.internal.widget.RecyclerView.State state)
    {
        rect.setEmpty();
    }

    boolean hasRunningRecoverAnim()
    {
        int i = mRecoverAnimations.size();
        for(int j = 0; j < i; j++)
            if(!((RecoverAnimation)mRecoverAnimations.get(j)).mEnded)
                return true;

        return false;
    }

    void moveIfNecessary(com.android.internal.widget.RecyclerView.ViewHolder viewholder)
    {
        if(mRecyclerView.isLayoutRequested())
            return;
        if(mActionState != 2)
            return;
        float f = mCallback.getMoveThreshold(viewholder);
        int i = (int)(mSelectedStartX + mDx);
        int j = (int)(mSelectedStartY + mDy);
        if((float)Math.abs(j - viewholder.itemView.getTop()) < (float)viewholder.itemView.getHeight() * f && (float)Math.abs(i - viewholder.itemView.getLeft()) < (float)viewholder.itemView.getWidth() * f)
            return;
        Object obj = findSwapTargets(viewholder);
        if(((List) (obj)).size() == 0)
            return;
        obj = mCallback.chooseDropTarget(viewholder, ((List) (obj)), i, j);
        if(obj == null)
        {
            mSwapTargets.clear();
            mDistances.clear();
            return;
        }
        int k = ((com.android.internal.widget.RecyclerView.ViewHolder) (obj)).getAdapterPosition();
        int l = viewholder.getAdapterPosition();
        if(mCallback.onMove(mRecyclerView, viewholder, ((com.android.internal.widget.RecyclerView.ViewHolder) (obj))))
            mCallback.onMoved(mRecyclerView, viewholder, l, ((com.android.internal.widget.RecyclerView.ViewHolder) (obj)), k, i, j);
    }

    void obtainVelocityTracker()
    {
        if(mVelocityTracker != null)
            mVelocityTracker.recycle();
        mVelocityTracker = VelocityTracker.obtain();
    }

    public void onChildViewAttachedToWindow(View view)
    {
    }

    public void onChildViewDetachedFromWindow(View view)
    {
        removeChildDrawingOrderCallbackIfNecessary(view);
        view = mRecyclerView.getChildViewHolder(view);
        if(view == null)
            return;
        if(mSelected == null || view != mSelected) goto _L2; else goto _L1
_L1:
        select(null, 0);
_L4:
        return;
_L2:
        endRecoverAnimation(view, false);
        if(mPendingCleanup.remove(((com.android.internal.widget.RecyclerView.ViewHolder) (view)).itemView))
            mCallback.clearView(mRecyclerView, view);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerview, com.android.internal.widget.RecyclerView.State state)
    {
        mOverdrawChildPosition = -1;
        float f = 0.0F;
        float f1 = 0.0F;
        if(mSelected != null)
        {
            getSelectedDxDy(mTmpPosition);
            f = mTmpPosition[0];
            f1 = mTmpPosition[1];
        }
        mCallback.onDraw(canvas, recyclerview, mSelected, mRecoverAnimations, mActionState, f, f1);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerview, com.android.internal.widget.RecyclerView.State state)
    {
        float f = 0.0F;
        float f1 = 0.0F;
        if(mSelected != null)
        {
            getSelectedDxDy(mTmpPosition);
            f = mTmpPosition[0];
            f1 = mTmpPosition[1];
        }
        mCallback.onDrawOver(canvas, recyclerview, mSelected, mRecoverAnimations, mActionState, f, f1);
    }

    void postDispatchSwipe(final RecoverAnimation anim, final int swipeDir)
    {
        mRecyclerView.post(new Runnable() {

            public void run()
            {
                if(mRecyclerView != null && mRecyclerView.isAttachedToWindow() && anim.mOverridden ^ true && anim.mViewHolder.getAdapterPosition() != -1)
                {
                    com.android.internal.widget.RecyclerView.ItemAnimator itemanimator = mRecyclerView.getItemAnimator();
                    if((itemanimator == null || itemanimator.isRunning(null) ^ true) && hasRunningRecoverAnim() ^ true)
                        mCallback.onSwiped(anim.mViewHolder, swipeDir);
                    else
                        mRecyclerView.post(this);
                }
            }

            final ItemTouchHelper this$0;
            final RecoverAnimation val$anim;
            final int val$swipeDir;

            
            {
                this$0 = ItemTouchHelper.this;
                anim = recoveranimation;
                swipeDir = i;
                super();
            }
        }
);
    }

    void removeChildDrawingOrderCallbackIfNecessary(View view)
    {
        if(view == mOverdrawChild)
        {
            mOverdrawChild = null;
            if(mChildDrawingOrderCallback != null)
                mRecyclerView.setChildDrawingOrderCallback(null);
        }
    }

    boolean scrollIfNecessary()
    {
        if(mSelected == null)
        {
            mDragScrollStartTimeInMs = 0x8000000000000000L;
            return false;
        }
        long l = System.currentTimeMillis();
        long l1;
        com.android.internal.widget.RecyclerView.LayoutManager layoutmanager;
        int i;
        int j;
        int k;
        int i1;
        if(mDragScrollStartTimeInMs == 0x8000000000000000L)
            l1 = 0L;
        else
            l1 = l - mDragScrollStartTimeInMs;
        layoutmanager = mRecyclerView.getLayoutManager();
        if(mTmpRect == null)
            mTmpRect = new Rect();
        i = 0;
        j = 0;
        layoutmanager.calculateItemDecorationsForChild(mSelected.itemView, mTmpRect);
        k = i;
        if(layoutmanager.canScrollHorizontally())
        {
            i1 = (int)(mSelectedStartX + mDx);
            k = i1 - mTmpRect.left - mRecyclerView.getPaddingLeft();
            if(mDx >= 0.0F || k >= 0)
            {
                k = i;
                if(mDx > 0.0F)
                {
                    i1 = (mSelected.itemView.getWidth() + i1 + mTmpRect.right) - (mRecyclerView.getWidth() - mRecyclerView.getPaddingRight());
                    k = i;
                    if(i1 > 0)
                        k = i1;
                }
            }
        }
        i = j;
        if(layoutmanager.canScrollVertically())
        {
            i1 = (int)(mSelectedStartY + mDy);
            i = i1 - mTmpRect.top - mRecyclerView.getPaddingTop();
            if(mDy >= 0.0F || i >= 0)
            {
                i = j;
                if(mDy > 0.0F)
                {
                    i1 = (mSelected.itemView.getHeight() + i1 + mTmpRect.bottom) - (mRecyclerView.getHeight() - mRecyclerView.getPaddingBottom());
                    i = j;
                    if(i1 > 0)
                        i = i1;
                }
            }
        }
        j = k;
        if(k != 0)
            j = mCallback.interpolateOutOfBoundsScroll(mRecyclerView, mSelected.itemView.getWidth(), k, mRecyclerView.getWidth(), l1);
        k = i;
        if(i != 0)
            k = mCallback.interpolateOutOfBoundsScroll(mRecyclerView, mSelected.itemView.getHeight(), i, mRecyclerView.getHeight(), l1);
        if(j != 0 || k != 0)
        {
            if(mDragScrollStartTimeInMs == 0x8000000000000000L)
                mDragScrollStartTimeInMs = l;
            mRecyclerView.scrollBy(j, k);
            return true;
        } else
        {
            mDragScrollStartTimeInMs = 0x8000000000000000L;
            return false;
        }
    }

    void select(com.android.internal.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        int j;
        int k;
        int l;
        if(viewholder == mSelected && i == mActionState)
            return;
        mDragScrollStartTimeInMs = 0x8000000000000000L;
        j = mActionState;
        endRecoverAnimation(viewholder, true);
        mActionState = i;
        if(i == 2)
        {
            mOverdrawChild = viewholder.itemView;
            addChildDrawingOrderCallback();
        }
        k = 0;
        l = 0;
        if(mSelected == null) goto _L2; else goto _L1
_L1:
        final Object final_viewholder;
        float f;
        float f1;
        final_viewholder = mSelected;
        if(((com.android.internal.widget.RecyclerView.ViewHolder) (final_viewholder)).itemView.getParent() == null)
            break MISSING_BLOCK_LABEL_510;
        float f2;
        float f3;
        if(j == 2)
            l = 0;
        else
            l = swipeIfNecessary(((com.android.internal.widget.RecyclerView.ViewHolder) (final_viewholder)));
        releaseVelocityTracker();
        l;
        JVM INSTR lookupswitch 6: default 160
    //                   1: 469
    //                   2: 469
    //                   4: 445
    //                   8: 445
    //                   16: 445
    //                   32: 445;
           goto _L3 _L4 _L4 _L5 _L5 _L5 _L5
_L3:
        f = 0.0F;
        f1 = 0.0F;
_L6:
        if(j == 2)
            k = 8;
        else
        if(l > 0)
            k = 2;
        else
            k = 4;
        getSelectedDxDy(mTmpPosition);
        f2 = mTmpPosition[0];
        f3 = mTmpPosition[1];
        final_viewholder = new RecoverAnimation(k, j, f2, f3, f, f1, l, ((com.android.internal.widget.RecyclerView.ViewHolder) (final_viewholder))) {

            public void onAnimationEnd(Animator animator)
            {
                super.onAnimationEnd(animator);
                if(mOverridden)
                    return;
                if(swipeDir > 0) goto _L2; else goto _L1
_L1:
                mCallback.clearView(mRecyclerView, prevSelected);
_L4:
                if(mOverdrawChild == prevSelected.itemView)
                    removeChildDrawingOrderCallbackIfNecessary(prevSelected.itemView);
                return;
_L2:
                mPendingCleanup.add(prevSelected.itemView);
                mIsPendingCleanup = true;
                if(swipeDir > 0)
                    postDispatchSwipe(this, swipeDir);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ItemTouchHelper this$0;
            final com.android.internal.widget.RecyclerView.ViewHolder val$prevSelected;
            final int val$swipeDir;

            
            {
                this$0 = final_itemtouchhelper1;
                swipeDir = k;
                prevSelected = viewholder1;
                super(final_viewholder, i, j, f, f1, f2, f3);
            }
        }
;
        ((RecoverAnimation) (final_viewholder)).setDuration(mCallback.getAnimationDuration(mRecyclerView, k, f - f2, f1 - f3));
        mRecoverAnimations.add(final_viewholder);
        ((RecoverAnimation) (final_viewholder)).start();
        k = 1;
_L7:
        mSelected = null;
_L2:
        if(viewholder != null)
        {
            mSelectedFlags = (mCallback.getAbsoluteMovementFlags(mRecyclerView, viewholder) & (1 << i * 8 + 8) - 1) >> mActionState * 8;
            mSelectedStartX = viewholder.itemView.getLeft();
            mSelectedStartY = viewholder.itemView.getTop();
            mSelected = viewholder;
            if(i == 2)
                mSelected.itemView.performHapticFeedback(0);
        }
        viewholder = mRecyclerView.getParent();
        if(viewholder != null)
        {
            boolean flag;
            if(mSelected != null)
                flag = true;
            else
                flag = false;
            viewholder.requestDisallowInterceptTouchEvent(flag);
        }
        if(k == 0)
            mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
        mCallback.onSelectedChanged(mSelected, mActionState);
        mRecyclerView.invalidate();
        return;
_L5:
        f1 = 0.0F;
        f = Math.signum(mDx) * (float)mRecyclerView.getWidth();
          goto _L6
_L4:
        f = 0.0F;
        f1 = Math.signum(mDy) * (float)mRecyclerView.getHeight();
          goto _L6
        removeChildDrawingOrderCallbackIfNecessary(((com.android.internal.widget.RecyclerView.ViewHolder) (final_viewholder)).itemView);
        mCallback.clearView(mRecyclerView, ((com.android.internal.widget.RecyclerView.ViewHolder) (final_viewholder)));
        k = l;
          goto _L7
    }

    public void startDrag(com.android.internal.widget.RecyclerView.ViewHolder viewholder)
    {
        if(!mCallback.hasDragFlag(mRecyclerView, viewholder))
        {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            return;
        }
        if(viewholder.itemView.getParent() != mRecyclerView)
        {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        } else
        {
            obtainVelocityTracker();
            mDy = 0.0F;
            mDx = 0.0F;
            select(viewholder, 2);
            return;
        }
    }

    public void startSwipe(com.android.internal.widget.RecyclerView.ViewHolder viewholder)
    {
        if(!mCallback.hasSwipeFlag(mRecyclerView, viewholder))
        {
            Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
            return;
        }
        if(viewholder.itemView.getParent() != mRecyclerView)
        {
            Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
            return;
        } else
        {
            obtainVelocityTracker();
            mDy = 0.0F;
            mDx = 0.0F;
            select(viewholder, 1);
            return;
        }
    }

    void updateDxDy(MotionEvent motionevent, int i, int j)
    {
        float f = motionevent.getX(j);
        float f1 = motionevent.getY(j);
        mDx = f - mInitialTouchX;
        mDy = f1 - mInitialTouchY;
        if((i & 4) == 0)
            mDx = Math.max(0.0F, mDx);
        if((i & 8) == 0)
            mDx = Math.min(0.0F, mDx);
        if((i & 1) == 0)
            mDy = Math.max(0.0F, mDy);
        if((i & 2) == 0)
            mDy = Math.min(0.0F, mDy);
    }

    static final int ACTION_MODE_DRAG_MASK = 0xff0000;
    private static final int ACTION_MODE_IDLE_MASK = 255;
    static final int ACTION_MODE_SWIPE_MASK = 65280;
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    static final int ACTIVE_POINTER_ID_NONE = -1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    static final boolean DEBUG = false;
    static final int DIRECTION_FLAG_COUNT = 8;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    private static final int PIXELS_PER_SECOND = 1000;
    public static final int RIGHT = 8;
    public static final int START = 16;
    static final String TAG = "ItemTouchHelper";
    public static final int UP = 1;
    int mActionState;
    int mActivePointerId;
    Callback mCallback;
    private com.android.internal.widget.RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private List mDistances;
    private long mDragScrollStartTimeInMs;
    float mDx;
    float mDy;
    GestureDetector mGestureDetector;
    float mInitialTouchX;
    float mInitialTouchY;
    float mMaxSwipeVelocity;
    private final com.android.internal.widget.RecyclerView.OnItemTouchListener mOnItemTouchListener = new com.android.internal.widget.RecyclerView.OnItemTouchListener() {

        public boolean onInterceptTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
        {
            boolean flag = true;
            mGestureDetector.onTouchEvent(motionevent);
            int i = motionevent.getActionMasked();
            if(i == 0)
            {
                mActivePointerId = motionevent.getPointerId(0);
                mInitialTouchX = motionevent.getX();
                mInitialTouchY = motionevent.getY();
                obtainVelocityTracker();
                if(mSelected == null)
                {
                    recyclerview = findAnimation(motionevent);
                    if(recyclerview != null)
                    {
                        ItemTouchHelper itemtouchhelper = ItemTouchHelper.this;
                        itemtouchhelper.mInitialTouchX = itemtouchhelper.mInitialTouchX - ((RecoverAnimation) (recyclerview)).mX;
                        itemtouchhelper = ItemTouchHelper.this;
                        itemtouchhelper.mInitialTouchY = itemtouchhelper.mInitialTouchY - ((RecoverAnimation) (recyclerview)).mY;
                        endRecoverAnimation(((RecoverAnimation) (recyclerview)).mViewHolder, true);
                        if(mPendingCleanup.remove(((RecoverAnimation) (recyclerview)).mViewHolder.itemView))
                            mCallback.clearView(mRecyclerView, ((RecoverAnimation) (recyclerview)).mViewHolder);
                        select(((RecoverAnimation) (recyclerview)).mViewHolder, ((RecoverAnimation) (recyclerview)).mActionState);
                        updateDxDy(motionevent, mSelectedFlags, 0);
                    }
                }
            } else
            if(i == 3 || i == 1)
            {
                mActivePointerId = -1;
                select(null, 0);
            } else
            if(mActivePointerId != -1)
            {
                int j = motionevent.findPointerIndex(mActivePointerId);
                if(j >= 0)
                    checkSelectForSwipe(i, motionevent, j);
            }
            if(mVelocityTracker != null)
                mVelocityTracker.addMovement(motionevent);
            if(mSelected == null)
                flag = false;
            return flag;
        }

        public void onRequestDisallowInterceptTouchEvent(boolean flag)
        {
            if(!flag)
            {
                return;
            } else
            {
                select(null, 0);
                return;
            }
        }

        public void onTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
        {
            int i;
            int k;
            mGestureDetector.onTouchEvent(motionevent);
            if(mVelocityTracker != null)
                mVelocityTracker.addMovement(motionevent);
            if(mActivePointerId == -1)
                return;
            i = motionevent.getActionMasked();
            k = motionevent.findPointerIndex(mActivePointerId);
            if(k >= 0)
                checkSelectForSwipe(i, motionevent, k);
            recyclerview = mSelected;
            if(recyclerview == null)
                return;
            i;
            JVM INSTR tableswitch 1 6: default 132
        //                       1 226
        //                       2 133
        //                       3 206
        //                       4 132
        //                       5 132
        //                       6 246;
               goto _L1 _L2 _L3 _L4 _L1 _L1 _L5
_L1:
            return;
_L3:
            if(k >= 0)
            {
                updateDxDy(motionevent, mSelectedFlags, k);
                moveIfNecessary(recyclerview);
                mRecyclerView.removeCallbacks(mScrollRunnable);
                mScrollRunnable.run();
                mRecyclerView.invalidate();
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if(mVelocityTracker != null)
                mVelocityTracker.clear();
_L2:
            select(null, 0);
            mActivePointerId = -1;
            continue; /* Loop/switch isn't completed */
_L5:
            int j = motionevent.getActionIndex();
            if(motionevent.getPointerId(j) == mActivePointerId)
            {
                int l;
                if(j == 0)
                    l = 1;
                else
                    l = 0;
                mActivePointerId = motionevent.getPointerId(l);
                updateDxDy(motionevent, mSelectedFlags, j);
            }
            if(true) goto _L1; else goto _L6
_L6:
        }

        final ItemTouchHelper this$0;

            
            {
                this$0 = ItemTouchHelper.this;
                super();
            }
    }
;
    View mOverdrawChild;
    int mOverdrawChildPosition;
    final List mPendingCleanup = new ArrayList();
    List mRecoverAnimations;
    RecyclerView mRecyclerView;
    final Runnable mScrollRunnable = new Runnable() {

        public void run()
        {
            if(mSelected != null && scrollIfNecessary())
            {
                if(mSelected != null)
                    moveIfNecessary(mSelected);
                mRecyclerView.removeCallbacks(mScrollRunnable);
                mRecyclerView.postOnAnimation(this);
            }
        }

        final ItemTouchHelper this$0;

            
            {
                this$0 = ItemTouchHelper.this;
                super();
            }
    }
;
    com.android.internal.widget.RecyclerView.ViewHolder mSelected;
    int mSelectedFlags;
    float mSelectedStartX;
    float mSelectedStartY;
    private int mSlop;
    private List mSwapTargets;
    float mSwipeEscapeVelocity;
    private final float mTmpPosition[] = new float[2];
    private Rect mTmpRect;
    VelocityTracker mVelocityTracker;

    // Unreferenced inner class com/android/internal/widget/helper/ItemTouchHelper$RecoverAnimation$1

/* anonymous class */
    class RecoverAnimation._cls1
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        public void onAnimationUpdate(ValueAnimator valueanimator)
        {
            setFraction(valueanimator.getAnimatedFraction());
        }

        final RecoverAnimation this$1;

            
            {
                this$1 = RecoverAnimation.this;
                super();
            }
    }

}
