// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.*;
import android.view.*;
import android.widget.OverScroller;
import android.widget.Toolbar;

// Referenced classes of package com.android.internal.widget:
//            DecorContentParent, DecorToolbar, ActionBarContainer, ActionBarContextView

public class ActionBarOverlayLayout extends ViewGroup
    implements DecorContentParent
{
    public static interface ActionBarVisibilityCallback
    {

        public abstract void enableContentAnimations(boolean flag);

        public abstract void hideForSystem();

        public abstract void onContentScrollStarted();

        public abstract void onContentScrollStopped();

        public abstract void onWindowVisibilityChanged(int i);

        public abstract void showForSystem();
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
        }
    }


    static ActionBarContainer _2D_get0(ActionBarOverlayLayout actionbaroverlaylayout)
    {
        return actionbaroverlaylayout.mActionBarBottom;
    }

    static ActionBarContainer _2D_get1(ActionBarOverlayLayout actionbaroverlaylayout)
    {
        return actionbaroverlaylayout.mActionBarTop;
    }

    static android.animation.Animator.AnimatorListener _2D_get2(ActionBarOverlayLayout actionbaroverlaylayout)
    {
        return actionbaroverlaylayout.mBottomAnimatorListener;
    }

    static android.animation.Animator.AnimatorListener _2D_get3(ActionBarOverlayLayout actionbaroverlaylayout)
    {
        return actionbaroverlaylayout.mTopAnimatorListener;
    }

    static boolean _2D_set0(ActionBarOverlayLayout actionbaroverlaylayout, boolean flag)
    {
        actionbaroverlaylayout.mAnimatingForFling = flag;
        return flag;
    }

    static ViewPropertyAnimator _2D_set1(ActionBarOverlayLayout actionbaroverlaylayout, ViewPropertyAnimator viewpropertyanimator)
    {
        actionbaroverlaylayout.mCurrentActionBarBottomAnimator = viewpropertyanimator;
        return viewpropertyanimator;
    }

    static ViewPropertyAnimator _2D_set2(ActionBarOverlayLayout actionbaroverlaylayout, ViewPropertyAnimator viewpropertyanimator)
    {
        actionbaroverlaylayout.mCurrentActionBarTopAnimator = viewpropertyanimator;
        return viewpropertyanimator;
    }

    static void _2D_wrap0(ActionBarOverlayLayout actionbaroverlaylayout)
    {
        actionbaroverlaylayout.haltActionBarHideOffsetAnimations();
    }

    public ActionBarOverlayLayout(Context context)
    {
        super(context);
        mWindowVisibility = 0;
        mBaseContentInsets = new Rect();
        mLastBaseContentInsets = new Rect();
        mContentInsets = new Rect();
        mBaseInnerInsets = new Rect();
        mLastBaseInnerInsets = new Rect();
        mInnerInsets = new Rect();
        mLastInnerInsets = new Rect();
        ACTION_BAR_ANIMATE_DELAY = 600;
        mTopAnimatorListener = new AnimatorListenerAdapter() {

            public void onAnimationCancel(Animator animator)
            {
                ActionBarOverlayLayout._2D_set2(ActionBarOverlayLayout.this, null);
                ActionBarOverlayLayout._2D_set0(ActionBarOverlayLayout.this, false);
            }

            public void onAnimationEnd(Animator animator)
            {
                ActionBarOverlayLayout._2D_set2(ActionBarOverlayLayout.this, null);
                ActionBarOverlayLayout._2D_set0(ActionBarOverlayLayout.this, false);
            }

            final ActionBarOverlayLayout this$0;

            
            {
                this$0 = ActionBarOverlayLayout.this;
                super();
            }
        }
;
        mBottomAnimatorListener = new AnimatorListenerAdapter() {

            public void onAnimationCancel(Animator animator)
            {
                ActionBarOverlayLayout._2D_set1(ActionBarOverlayLayout.this, null);
                ActionBarOverlayLayout._2D_set0(ActionBarOverlayLayout.this, false);
            }

            public void onAnimationEnd(Animator animator)
            {
                ActionBarOverlayLayout._2D_set1(ActionBarOverlayLayout.this, null);
                ActionBarOverlayLayout._2D_set0(ActionBarOverlayLayout.this, false);
            }

            final ActionBarOverlayLayout this$0;

            
            {
                this$0 = ActionBarOverlayLayout.this;
                super();
            }
        }
;
        mRemoveActionBarHideOffset = new Runnable() {

            public void run()
            {
                ActionBarOverlayLayout._2D_wrap0(ActionBarOverlayLayout.this);
                ActionBarOverlayLayout._2D_set2(ActionBarOverlayLayout.this, ActionBarOverlayLayout._2D_get1(ActionBarOverlayLayout.this).animate().translationY(0.0F).setListener(ActionBarOverlayLayout._2D_get3(ActionBarOverlayLayout.this)));
                if(ActionBarOverlayLayout._2D_get0(ActionBarOverlayLayout.this) != null && ActionBarOverlayLayout._2D_get0(ActionBarOverlayLayout.this).getVisibility() != 8)
                    ActionBarOverlayLayout._2D_set1(ActionBarOverlayLayout.this, ActionBarOverlayLayout._2D_get0(ActionBarOverlayLayout.this).animate().translationY(0.0F).setListener(ActionBarOverlayLayout._2D_get2(ActionBarOverlayLayout.this)));
            }

            final ActionBarOverlayLayout this$0;

            
            {
                this$0 = ActionBarOverlayLayout.this;
                super();
            }
        }
;
        mAddActionBarHideOffset = new Runnable() {

            public void run()
            {
                ActionBarOverlayLayout._2D_wrap0(ActionBarOverlayLayout.this);
                ActionBarOverlayLayout._2D_set2(ActionBarOverlayLayout.this, ActionBarOverlayLayout._2D_get1(ActionBarOverlayLayout.this).animate().translationY(-ActionBarOverlayLayout._2D_get1(ActionBarOverlayLayout.this).getHeight()).setListener(ActionBarOverlayLayout._2D_get3(ActionBarOverlayLayout.this)));
                if(ActionBarOverlayLayout._2D_get0(ActionBarOverlayLayout.this) != null && ActionBarOverlayLayout._2D_get0(ActionBarOverlayLayout.this).getVisibility() != 8)
                    ActionBarOverlayLayout._2D_set1(ActionBarOverlayLayout.this, ActionBarOverlayLayout._2D_get0(ActionBarOverlayLayout.this).animate().translationY(ActionBarOverlayLayout._2D_get0(ActionBarOverlayLayout.this).getHeight()).setListener(ActionBarOverlayLayout._2D_get2(ActionBarOverlayLayout.this)));
            }

            final ActionBarOverlayLayout this$0;

            
            {
                this$0 = ActionBarOverlayLayout.this;
                super();
            }
        }
;
        init(context);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mWindowVisibility = 0;
        mBaseContentInsets = new Rect();
        mLastBaseContentInsets = new Rect();
        mContentInsets = new Rect();
        mBaseInnerInsets = new Rect();
        mLastBaseInnerInsets = new Rect();
        mInnerInsets = new Rect();
        mLastInnerInsets = new Rect();
        ACTION_BAR_ANIMATE_DELAY = 600;
        mTopAnimatorListener = new _cls1();
        mBottomAnimatorListener = new _cls2();
        mRemoveActionBarHideOffset = new _cls3();
        mAddActionBarHideOffset = new _cls4();
        init(context);
    }

    private void addActionBarHideOffset()
    {
        haltActionBarHideOffsetAnimations();
        mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View view, Rect rect, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        boolean flag4 = false;
        view = (LayoutParams)view.getLayoutParams();
        boolean flag5 = flag4;
        if(flag)
        {
            flag5 = flag4;
            if(((LayoutParams) (view)).leftMargin != rect.left)
            {
                flag5 = true;
                view.leftMargin = rect.left;
            }
        }
        flag = flag5;
        if(flag1)
        {
            flag = flag5;
            if(((LayoutParams) (view)).topMargin != rect.top)
            {
                flag = true;
                view.topMargin = rect.top;
            }
        }
        flag1 = flag;
        if(flag3)
        {
            flag1 = flag;
            if(((LayoutParams) (view)).rightMargin != rect.right)
            {
                flag1 = true;
                view.rightMargin = rect.right;
            }
        }
        flag = flag1;
        if(flag2)
        {
            flag = flag1;
            if(((LayoutParams) (view)).bottomMargin != rect.bottom)
            {
                flag = true;
                view.bottomMargin = rect.bottom;
            }
        }
        return flag;
    }

    private DecorToolbar getDecorToolbar(View view)
    {
        if(view instanceof DecorToolbar)
            return (DecorToolbar)view;
        if(view instanceof Toolbar)
            return ((Toolbar)view).getWrapper();
        else
            throw new IllegalStateException((new StringBuilder()).append("Can't make a decor toolbar out of ").append(view.getClass().getSimpleName()).toString());
    }

    private void haltActionBarHideOffsetAnimations()
    {
        removeCallbacks(mRemoveActionBarHideOffset);
        removeCallbacks(mAddActionBarHideOffset);
        if(mCurrentActionBarTopAnimator != null)
            mCurrentActionBarTopAnimator.cancel();
        if(mCurrentActionBarBottomAnimator != null)
            mCurrentActionBarBottomAnimator.cancel();
    }

    private void init(Context context)
    {
        boolean flag = true;
        TypedArray typedarray = getContext().getTheme().obtainStyledAttributes(ATTRS);
        mActionBarHeight = typedarray.getDimensionPixelSize(0, 0);
        mWindowContentOverlay = typedarray.getDrawable(1);
        boolean flag1;
        if(mWindowContentOverlay == null)
            flag1 = true;
        else
            flag1 = false;
        setWillNotDraw(flag1);
        typedarray.recycle();
        if(context.getApplicationInfo().targetSdkVersion < 19)
            flag1 = flag;
        else
            flag1 = false;
        mIgnoreWindowContentOverlay = flag1;
        mFlingEstimator = new OverScroller(context);
    }

    private void postAddActionBarHideOffset()
    {
        haltActionBarHideOffsetAnimations();
        postDelayed(mAddActionBarHideOffset, 600L);
    }

    private void postRemoveActionBarHideOffset()
    {
        haltActionBarHideOffsetAnimations();
        postDelayed(mRemoveActionBarHideOffset, 600L);
    }

    private void removeActionBarHideOffset()
    {
        haltActionBarHideOffsetAnimations();
        mRemoveActionBarHideOffset.run();
    }

    private boolean shouldHideActionBarOnFling(float f, float f1)
    {
        boolean flag = false;
        mFlingEstimator.fling(0, 0, 0, (int)f1, 0, 0, 0x80000000, 0x7fffffff);
        if(mFlingEstimator.getFinalY() > mActionBarTop.getHeight())
            flag = true;
        return flag;
    }

    public boolean canShowOverflowMenu()
    {
        pullChildren();
        return mDecorToolbar.canShowOverflowMenu();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public void dismissPopups()
    {
        pullChildren();
        mDecorToolbar.dismissPopupMenus();
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(mWindowContentOverlay != null && mIgnoreWindowContentOverlay ^ true)
        {
            int i;
            if(mActionBarTop.getVisibility() == 0)
                i = (int)((float)mActionBarTop.getBottom() + mActionBarTop.getTranslationY() + 0.5F);
            else
                i = 0;
            mWindowContentOverlay.setBounds(0, i, getWidth(), mWindowContentOverlay.getIntrinsicHeight() + i);
            mWindowContentOverlay.draw(canvas);
        }
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -1);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public int getActionBarHideOffset()
    {
        int i;
        if(mActionBarTop != null)
            i = -(int)mActionBarTop.getTranslationY();
        else
            i = 0;
        return i;
    }

    public CharSequence getTitle()
    {
        pullChildren();
        return mDecorToolbar.getTitle();
    }

    public boolean hasIcon()
    {
        pullChildren();
        return mDecorToolbar.hasIcon();
    }

    public boolean hasLogo()
    {
        pullChildren();
        return mDecorToolbar.hasLogo();
    }

    public boolean hideOverflowMenu()
    {
        pullChildren();
        return mDecorToolbar.hideOverflowMenu();
    }

    public void initFeature(int i)
    {
        pullChildren();
        i;
        JVM INSTR lookupswitch 3: default 40
    //                   2: 41
    //                   5: 53
    //                   9: 65;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        mDecorToolbar.initProgress();
        continue; /* Loop/switch isn't completed */
_L3:
        mDecorToolbar.initIndeterminateProgress();
        continue; /* Loop/switch isn't completed */
_L4:
        setOverlayMode(true);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean isHideOnContentScrollEnabled()
    {
        return mHideOnContentScroll;
    }

    public boolean isInOverlayMode()
    {
        return mOverlayMode;
    }

    public boolean isOverflowMenuShowPending()
    {
        pullChildren();
        return mDecorToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing()
    {
        pullChildren();
        return mDecorToolbar.isOverflowMenuShowing();
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowinsets)
    {
        pullChildren();
        if((getWindowSystemUiVisibility() & 0x100) == 0);
        windowinsets = windowinsets.getSystemWindowInsets();
        boolean flag = applyInsets(mActionBarTop, windowinsets, true, true, false, true);
        boolean flag1 = flag;
        if(mActionBarBottom != null)
            flag1 = flag | applyInsets(mActionBarBottom, windowinsets, true, false, true, true);
        mBaseInnerInsets.set(windowinsets);
        computeFitSystemWindows(mBaseInnerInsets, mBaseContentInsets);
        if(!mLastBaseInnerInsets.equals(mBaseInnerInsets))
        {
            flag1 = true;
            mLastBaseContentInsets.set(mBaseContentInsets);
        }
        if(!mLastBaseContentInsets.equals(mBaseContentInsets))
        {
            flag1 = true;
            mLastBaseContentInsets.set(mBaseContentInsets);
        }
        if(flag1)
            requestLayout();
        return WindowInsets.CONSUMED;
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        init(getContext());
        requestApplyInsets();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = getChildCount();
        int j1 = getPaddingLeft();
        getPaddingRight();
        int k1 = getPaddingTop();
        int l1 = getPaddingBottom();
        i = 0;
        while(i < i1) 
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int i2 = view.getMeasuredWidth();
                int j2 = view.getMeasuredHeight();
                int k2 = j1 + layoutparams.leftMargin;
                if(view == mActionBarBottom)
                    k = l - j - l1 - j2 - layoutparams.bottomMargin;
                else
                    k = k1 + layoutparams.topMargin;
                view.layout(k2, k, k2 + i2, k + j2);
            }
            i++;
        }
    }

    protected void onMeasure(int i, int j)
    {
        pullChildren();
        int k = 0;
        boolean flag = false;
        measureChildWithMargins(mActionBarTop, i, 0, j, 0);
        LayoutParams layoutparams = (LayoutParams)mActionBarTop.getLayoutParams();
        int l = Math.max(0, mActionBarTop.getMeasuredWidth() + layoutparams.leftMargin + layoutparams.rightMargin);
        int i1 = Math.max(0, mActionBarTop.getMeasuredHeight() + layoutparams.topMargin + layoutparams.bottomMargin);
        int j1 = combineMeasuredStates(0, mActionBarTop.getMeasuredState());
        int k1 = j1;
        int l1 = i1;
        int i2 = l;
        if(mActionBarBottom != null)
        {
            measureChildWithMargins(mActionBarBottom, i, 0, j, 0);
            LayoutParams layoutparams1 = (LayoutParams)mActionBarBottom.getLayoutParams();
            i2 = Math.max(l, mActionBarBottom.getMeasuredWidth() + layoutparams1.leftMargin + layoutparams1.rightMargin);
            l1 = Math.max(i1, mActionBarBottom.getMeasuredHeight() + layoutparams1.topMargin + layoutparams1.bottomMargin);
            k1 = combineMeasuredStates(j1, mActionBarBottom.getMeasuredState());
        }
        boolean flag1;
        if((getWindowSystemUiVisibility() & 0x100) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            j1 = mActionBarHeight;
            k = j1;
            if(mHasNonEmbeddedTabs)
            {
                k = j1;
                if(mActionBarTop.getTabContainer() != null)
                    k = j1 + mActionBarHeight;
            }
        } else
        if(mActionBarTop.getVisibility() != 8)
            k = mActionBarTop.getMeasuredHeight();
        j1 = ((flag) ? 1 : 0);
        if(mDecorToolbar.isSplit())
        {
            j1 = ((flag) ? 1 : 0);
            Object obj;
            if(mActionBarBottom != null)
                if(flag1)
                    j1 = mActionBarHeight;
                else
                    j1 = mActionBarBottom.getMeasuredHeight();
        }
        mContentInsets.set(mBaseContentInsets);
        mInnerInsets.set(mBaseInnerInsets);
        if(!mOverlayMode && flag1 ^ true)
        {
            obj = mContentInsets;
            obj.top = ((Rect) (obj)).top + k;
            obj = mContentInsets;
            obj.bottom = ((Rect) (obj)).bottom + j1;
        } else
        {
            Rect rect = mInnerInsets;
            rect.top = rect.top + k;
            rect = mInnerInsets;
            rect.bottom = rect.bottom + j1;
        }
        applyInsets(mContent, mContentInsets, true, true, true, true);
        if(!mLastInnerInsets.equals(mInnerInsets))
        {
            mLastInnerInsets.set(mInnerInsets);
            mContent.dispatchApplyWindowInsets(new WindowInsets(mInnerInsets));
        }
        measureChildWithMargins(mContent, i, 0, j, 0);
        obj = (LayoutParams)mContent.getLayoutParams();
        k = Math.max(i2, mContent.getMeasuredWidth() + ((LayoutParams) (obj)).leftMargin + ((LayoutParams) (obj)).rightMargin);
        l1 = Math.max(l1, mContent.getMeasuredHeight() + ((LayoutParams) (obj)).topMargin + ((LayoutParams) (obj)).bottomMargin);
        j1 = combineMeasuredStates(k1, mContent.getMeasuredState());
        i2 = getPaddingLeft();
        k1 = getPaddingRight();
        l1 = Math.max(l1 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight());
        setMeasuredDimension(resolveSizeAndState(Math.max(k + (i2 + k1), getSuggestedMinimumWidth()), i, j1), resolveSizeAndState(l1, j, j1 << 16));
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        if(!mHideOnContentScroll || flag ^ true)
            return false;
        if(shouldHideActionBarOnFling(f, f1))
            addActionBarHideOffset();
        else
            removeActionBarHideOffset();
        mAnimatingForFling = true;
        return true;
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        mHideOnContentScrollReference = mHideOnContentScrollReference + j;
        setActionBarHideOffset(mHideOnContentScrollReference);
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        super.onNestedScrollAccepted(view, view1, i);
        mHideOnContentScrollReference = getActionBarHideOffset();
        haltActionBarHideOffsetAnimations();
        if(mActionBarVisibilityCallback != null)
            mActionBarVisibilityCallback.onContentScrollStarted();
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        if((i & 2) == 0 || mActionBarTop.getVisibility() != 0)
            return false;
        else
            return mHideOnContentScroll;
    }

    public void onStopNestedScroll(View view)
    {
        super.onStopNestedScroll(view);
        if(mHideOnContentScroll && mAnimatingForFling ^ true)
            if(mHideOnContentScrollReference <= mActionBarTop.getHeight())
                postRemoveActionBarHideOffset();
            else
                postAddActionBarHideOffset();
        if(mActionBarVisibilityCallback != null)
            mActionBarVisibilityCallback.onContentScrollStopped();
    }

    public void onWindowSystemUiVisibilityChanged(int i)
    {
        super.onWindowSystemUiVisibilityChanged(i);
        pullChildren();
        int j = mLastSystemUiVisibility;
        mLastSystemUiVisibility = i;
        boolean flag;
        boolean flag1;
        if((i & 4) == 0)
            flag = true;
        else
            flag = false;
        if((i & 0x100) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(mActionBarVisibilityCallback != null)
        {
            mActionBarVisibilityCallback.enableContentAnimations(flag1 ^ true);
            if(flag || flag1 ^ true)
                mActionBarVisibilityCallback.showForSystem();
            else
                mActionBarVisibilityCallback.hideForSystem();
        }
        if(((j ^ i) & 0x100) != 0 && mActionBarVisibilityCallback != null)
            requestApplyInsets();
    }

    protected void onWindowVisibilityChanged(int i)
    {
        super.onWindowVisibilityChanged(i);
        mWindowVisibility = i;
        if(mActionBarVisibilityCallback != null)
            mActionBarVisibilityCallback.onWindowVisibilityChanged(i);
    }

    void pullChildren()
    {
        if(mContent == null)
        {
            mContent = findViewById(0x1020002);
            mActionBarTop = (ActionBarContainer)findViewById(0x102017e);
            mDecorToolbar = getDecorToolbar(findViewById(0x102017d));
            mActionBarBottom = (ActionBarContainer)findViewById(0x102040f);
        }
    }

    public void restoreToolbarHierarchyState(SparseArray sparsearray)
    {
        pullChildren();
        mDecorToolbar.restoreHierarchyState(sparsearray);
    }

    public void saveToolbarHierarchyState(SparseArray sparsearray)
    {
        pullChildren();
        mDecorToolbar.saveHierarchyState(sparsearray);
    }

    public void setActionBarHideOffset(int i)
    {
        haltActionBarHideOffsetAnimations();
        int j = mActionBarTop.getHeight();
        i = Math.max(0, Math.min(i, j));
        mActionBarTop.setTranslationY(-i);
        if(mActionBarBottom != null && mActionBarBottom.getVisibility() != 8)
        {
            float f = (float)i / (float)j;
            i = (int)((float)mActionBarBottom.getHeight() * f);
            mActionBarBottom.setTranslationY(i);
        }
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionbarvisibilitycallback)
    {
        mActionBarVisibilityCallback = actionbarvisibilitycallback;
        if(getWindowToken() != null)
        {
            mActionBarVisibilityCallback.onWindowVisibilityChanged(mWindowVisibility);
            if(mLastSystemUiVisibility != 0)
            {
                onWindowSystemUiVisibilityChanged(mLastSystemUiVisibility);
                requestApplyInsets();
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean flag)
    {
        mHasNonEmbeddedTabs = flag;
    }

    public void setHideOnContentScrollEnabled(boolean flag)
    {
        if(flag != mHideOnContentScroll)
        {
            mHideOnContentScroll = flag;
            if(!flag)
            {
                stopNestedScroll();
                haltActionBarHideOffsetAnimations();
                setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int i)
    {
        pullChildren();
        mDecorToolbar.setIcon(i);
    }

    public void setIcon(Drawable drawable)
    {
        pullChildren();
        mDecorToolbar.setIcon(drawable);
    }

    public void setLogo(int i)
    {
        pullChildren();
        mDecorToolbar.setLogo(i);
    }

    public void setMenu(Menu menu, com.android.internal.view.menu.MenuPresenter.Callback callback)
    {
        pullChildren();
        mDecorToolbar.setMenu(menu, callback);
    }

    public void setMenuPrepared()
    {
        pullChildren();
        mDecorToolbar.setMenuPrepared();
    }

    public void setOverlayMode(boolean flag)
    {
        boolean flag1 = false;
        mOverlayMode = flag;
        boolean flag2 = flag1;
        if(flag)
        {
            flag2 = flag1;
            if(getContext().getApplicationInfo().targetSdkVersion < 19)
                flag2 = true;
        }
        mIgnoreWindowContentOverlay = flag2;
    }

    public void setShowingForActionMode(boolean flag)
    {
        if(flag)
        {
            if((getWindowSystemUiVisibility() & 0x500) == 1280)
                setDisabledSystemUiVisibility(4);
        } else
        {
            setDisabledSystemUiVisibility(0);
        }
    }

    public void setUiOptions(int i)
    {
        boolean flag;
        flag = false;
        boolean flag1;
        ActionBarContextView actionbarcontextview;
        if((i & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            flag = getContext().getResources().getBoolean(0x11200f0);
        if(!flag) goto _L2; else goto _L1
_L1:
        pullChildren();
        if(mActionBarBottom == null || !mDecorToolbar.canSplit()) goto _L4; else goto _L3
_L3:
        mDecorToolbar.setSplitView(mActionBarBottom);
        mDecorToolbar.setSplitToolbar(flag);
        mDecorToolbar.setSplitWhenNarrow(flag1);
        actionbarcontextview = (ActionBarContextView)findViewById(0x1020182);
        actionbarcontextview.setSplitView(mActionBarBottom);
        actionbarcontextview.setSplitToolbar(flag);
        actionbarcontextview.setSplitWhenNarrow(flag1);
_L2:
        return;
_L4:
        if(flag)
            Log.e("ActionBarOverlayLayout", "Requested split action bar with incompatible window decor! Ignoring request.");
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setWindowCallback(android.view.Window.Callback callback)
    {
        pullChildren();
        mDecorToolbar.setWindowCallback(callback);
    }

    public void setWindowTitle(CharSequence charsequence)
    {
        pullChildren();
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    public boolean showOverflowMenu()
    {
        pullChildren();
        return mDecorToolbar.showOverflowMenu();
    }

    public static final Property ACTION_BAR_HIDE_OFFSET = new IntProperty("actionBarHideOffset") {

        public Integer get(ActionBarOverlayLayout actionbaroverlaylayout)
        {
            return Integer.valueOf(actionbaroverlaylayout.getActionBarHideOffset());
        }

        public volatile Object get(Object obj)
        {
            return get((ActionBarOverlayLayout)obj);
        }

        public void setValue(ActionBarOverlayLayout actionbaroverlaylayout, int i)
        {
            actionbaroverlaylayout.setActionBarHideOffset(i);
        }

        public volatile void setValue(Object obj, int i)
        {
            setValue((ActionBarOverlayLayout)obj, i);
        }

    }
;
    static final int ATTRS[] = {
        0x10102eb, 0x1010059
    };
    private static final String TAG = "ActionBarOverlayLayout";
    private final int ACTION_BAR_ANIMATE_DELAY;
    private ActionBarContainer mActionBarBottom;
    private int mActionBarHeight;
    private ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    private boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private final Rect mBaseInnerInsets;
    private final android.animation.Animator.AnimatorListener mBottomAnimatorListener;
    private View mContent;
    private final Rect mContentInsets;
    private ViewPropertyAnimator mCurrentActionBarBottomAnimator;
    private ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private OverScroller mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private final Rect mLastBaseInnerInsets;
    private final Rect mLastInnerInsets;
    private int mLastSystemUiVisibility;
    private boolean mOverlayMode;
    private final Runnable mRemoveActionBarHideOffset;
    private final android.animation.Animator.AnimatorListener mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;

}
