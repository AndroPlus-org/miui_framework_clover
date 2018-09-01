// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.animation.*;
import android.app.*;
import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.SpinnerAdapter;
import android.widget.Toolbar;
import com.android.internal.view.ActionBarPolicy;
import com.android.internal.view.menu.*;
import com.android.internal.widget.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.app:
//            NavItemSelectedListener

public class WindowDecorActionBar extends ActionBar
    implements com.android.internal.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
{
    public class ActionModeImpl extends ActionMode
        implements com.android.internal.view.menu.MenuBuilder.Callback
    {

        public boolean dispatchOnCreate()
        {
            mMenu.stopDispatchingItemsChanged();
            boolean flag = mCallback.onCreateActionMode(this, mMenu);
            mMenu.startDispatchingItemsChanged();
            return flag;
            Exception exception;
            exception;
            mMenu.startDispatchingItemsChanged();
            throw exception;
        }

        public void finish()
        {
            if(mActionMode != this)
                return;
            if(!WindowDecorActionBar._2D_wrap0(WindowDecorActionBar._2D_get7(WindowDecorActionBar.this), WindowDecorActionBar._2D_get8(WindowDecorActionBar.this), false))
            {
                mDeferredDestroyActionMode = this;
                mDeferredModeDestroyCallback = mCallback;
            } else
            {
                mCallback.onDestroyActionMode(this);
            }
            mCallback = null;
            animateToMode(false);
            WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).closeMode();
            WindowDecorActionBar._2D_get6(WindowDecorActionBar.this).getViewGroup().sendAccessibilityEvent(32);
            WindowDecorActionBar._2D_get9(WindowDecorActionBar.this).setHideOnContentScrollEnabled(mHideOnContentScroll);
            mActionMode = null;
        }

        public View getCustomView()
        {
            View view = null;
            if(mCustomView != null)
                view = (View)mCustomView.get();
            return view;
        }

        public Menu getMenu()
        {
            return mMenu;
        }

        public MenuInflater getMenuInflater()
        {
            return new MenuInflater(mActionModeContext);
        }

        public CharSequence getSubtitle()
        {
            return WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).getSubtitle();
        }

        public CharSequence getTitle()
        {
            return WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).getTitle();
        }

        public void invalidate()
        {
            if(mActionMode != this)
                return;
            mMenu.stopDispatchingItemsChanged();
            mCallback.onPrepareActionMode(this, mMenu);
            mMenu.startDispatchingItemsChanged();
            return;
            Exception exception;
            exception;
            mMenu.startDispatchingItemsChanged();
            throw exception;
        }

        public boolean isTitleOptional()
        {
            return WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
        }

        public void onCloseSubMenu(SubMenuBuilder submenubuilder)
        {
        }

        public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            if(mCallback != null)
                return mCallback.onActionItemClicked(this, menuitem);
            else
                return false;
        }

        public void onMenuModeChange(MenuBuilder menubuilder)
        {
            if(mCallback == null)
            {
                return;
            } else
            {
                invalidate();
                WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).showOverflowMenu();
                return;
            }
        }

        public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
        {
            if(mCallback == null)
                return false;
            if(!submenubuilder.hasVisibleItems())
            {
                return true;
            } else
            {
                (new MenuPopupHelper(getThemedContext(), submenubuilder)).show();
                return true;
            }
        }

        public void setCustomView(View view)
        {
            WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).setCustomView(view);
            mCustomView = new WeakReference(view);
        }

        public void setSubtitle(int i)
        {
            setSubtitle(((CharSequence) (WindowDecorActionBar._2D_get3(WindowDecorActionBar.this).getResources().getString(i))));
        }

        public void setSubtitle(CharSequence charsequence)
        {
            WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).setSubtitle(charsequence);
        }

        public void setTitle(int i)
        {
            setTitle(((CharSequence) (WindowDecorActionBar._2D_get3(WindowDecorActionBar.this).getResources().getString(i))));
        }

        public void setTitle(CharSequence charsequence)
        {
            WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).setTitle(charsequence);
        }

        public void setTitleOptionalHint(boolean flag)
        {
            super.setTitleOptionalHint(flag);
            WindowDecorActionBar._2D_get5(WindowDecorActionBar.this).setTitleOptional(flag);
        }

        private final Context mActionModeContext;
        private android.view.ActionMode.Callback mCallback;
        private WeakReference mCustomView;
        private final MenuBuilder mMenu;
        final WindowDecorActionBar this$0;

        public ActionModeImpl(Context context, android.view.ActionMode.Callback callback)
        {
            this$0 = WindowDecorActionBar.this;
            super();
            mActionModeContext = context;
            mCallback = callback;
            mMenu = (new MenuBuilder(context)).setDefaultShowAsAction(1);
            mMenu.setCallback(this);
        }
    }

    public class TabImpl extends android.app.ActionBar.Tab
    {

        public android.app.ActionBar.TabListener getCallback()
        {
            return mCallback;
        }

        public CharSequence getContentDescription()
        {
            return mContentDesc;
        }

        public View getCustomView()
        {
            return mCustomView;
        }

        public Drawable getIcon()
        {
            return mIcon;
        }

        public int getPosition()
        {
            return mPosition;
        }

        public Object getTag()
        {
            return mTag;
        }

        public CharSequence getText()
        {
            return mText;
        }

        public void select()
        {
            selectTab(this);
        }

        public android.app.ActionBar.Tab setContentDescription(int i)
        {
            return setContentDescription(WindowDecorActionBar._2D_get3(WindowDecorActionBar.this).getResources().getText(i));
        }

        public android.app.ActionBar.Tab setContentDescription(CharSequence charsequence)
        {
            mContentDesc = charsequence;
            if(mPosition >= 0)
                WindowDecorActionBar._2D_get11(WindowDecorActionBar.this).updateTab(mPosition);
            return this;
        }

        public android.app.ActionBar.Tab setCustomView(int i)
        {
            return setCustomView(LayoutInflater.from(getThemedContext()).inflate(i, null));
        }

        public android.app.ActionBar.Tab setCustomView(View view)
        {
            mCustomView = view;
            if(mPosition >= 0)
                WindowDecorActionBar._2D_get11(WindowDecorActionBar.this).updateTab(mPosition);
            return this;
        }

        public android.app.ActionBar.Tab setIcon(int i)
        {
            return setIcon(WindowDecorActionBar._2D_get3(WindowDecorActionBar.this).getDrawable(i));
        }

        public android.app.ActionBar.Tab setIcon(Drawable drawable)
        {
            mIcon = drawable;
            if(mPosition >= 0)
                WindowDecorActionBar._2D_get11(WindowDecorActionBar.this).updateTab(mPosition);
            return this;
        }

        public void setPosition(int i)
        {
            mPosition = i;
        }

        public android.app.ActionBar.Tab setTabListener(android.app.ActionBar.TabListener tablistener)
        {
            mCallback = tablistener;
            return this;
        }

        public android.app.ActionBar.Tab setTag(Object obj)
        {
            mTag = obj;
            return this;
        }

        public android.app.ActionBar.Tab setText(int i)
        {
            return setText(WindowDecorActionBar._2D_get3(WindowDecorActionBar.this).getResources().getText(i));
        }

        public android.app.ActionBar.Tab setText(CharSequence charsequence)
        {
            mText = charsequence;
            if(mPosition >= 0)
                WindowDecorActionBar._2D_get11(WindowDecorActionBar.this).updateTab(mPosition);
            return this;
        }

        private android.app.ActionBar.TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        final WindowDecorActionBar this$0;

        public TabImpl()
        {
            this$0 = WindowDecorActionBar.this;
            super();
            mPosition = -1;
        }
    }


    static ActionBarContainer _2D_get0(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mContainerView;
    }

    static boolean _2D_get1(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mContentAnimations;
    }

    static ActionBarContainer _2D_get10(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mSplitView;
    }

    static ScrollingTabContainerView _2D_get11(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mTabScrollView;
    }

    static View _2D_get2(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mContentView;
    }

    static Context _2D_get3(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mContext;
    }

    static int _2D_get4(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mContextDisplayMode;
    }

    static ActionBarContextView _2D_get5(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mContextView;
    }

    static DecorToolbar _2D_get6(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mDecorToolbar;
    }

    static boolean _2D_get7(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mHiddenByApp;
    }

    static boolean _2D_get8(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mHiddenBySystem;
    }

    static ActionBarOverlayLayout _2D_get9(WindowDecorActionBar windowdecoractionbar)
    {
        return windowdecoractionbar.mOverlayLayout;
    }

    static Animator _2D_set0(WindowDecorActionBar windowdecoractionbar, Animator animator)
    {
        windowdecoractionbar.mCurrentShowAnim = animator;
        return animator;
    }

    static boolean _2D_wrap0(boolean flag, boolean flag1, boolean flag2)
    {
        return checkShowingFlags(flag, flag1, flag2);
    }

    public WindowDecorActionBar(Activity activity)
    {
        mTabs = new ArrayList();
        mSavedTabPosition = -1;
        mMenuVisibilityListeners = new ArrayList();
        mCurWindowVisibility = 0;
        mContentAnimations = true;
        mNowShowing = true;
        mHideListener = new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                if(WindowDecorActionBar._2D_get1(WindowDecorActionBar.this) && WindowDecorActionBar._2D_get2(WindowDecorActionBar.this) != null)
                {
                    WindowDecorActionBar._2D_get2(WindowDecorActionBar.this).setTranslationY(0.0F);
                    WindowDecorActionBar._2D_get0(WindowDecorActionBar.this).setTranslationY(0.0F);
                }
                if(WindowDecorActionBar._2D_get10(WindowDecorActionBar.this) != null && WindowDecorActionBar._2D_get4(WindowDecorActionBar.this) == 1)
                    WindowDecorActionBar._2D_get10(WindowDecorActionBar.this).setVisibility(8);
                WindowDecorActionBar._2D_get0(WindowDecorActionBar.this).setVisibility(8);
                WindowDecorActionBar._2D_get0(WindowDecorActionBar.this).setTransitioning(false);
                WindowDecorActionBar._2D_set0(WindowDecorActionBar.this, null);
                completeDeferredDestroyActionMode();
                if(WindowDecorActionBar._2D_get9(WindowDecorActionBar.this) != null)
                    WindowDecorActionBar._2D_get9(WindowDecorActionBar.this).requestApplyInsets();
            }

            final WindowDecorActionBar this$0;

            
            {
                this$0 = WindowDecorActionBar.this;
                super();
            }
        }
;
        mShowListener = new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                WindowDecorActionBar._2D_set0(WindowDecorActionBar.this, null);
                WindowDecorActionBar._2D_get0(WindowDecorActionBar.this).requestLayout();
            }

            final WindowDecorActionBar this$0;

            
            {
                this$0 = WindowDecorActionBar.this;
                super();
            }
        }
;
        mUpdateListener = new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator)
            {
                ((View)WindowDecorActionBar._2D_get0(WindowDecorActionBar.this).getParent()).invalidate();
            }

            final WindowDecorActionBar this$0;

            
            {
                this$0 = WindowDecorActionBar.this;
                super();
            }
        }
;
        mActivity = activity;
        activity = activity.getWindow().getDecorView();
        boolean flag = mActivity.getWindow().hasFeature(9);
        init(activity);
        if(!flag)
            mContentView = activity.findViewById(0x1020002);
    }

    public WindowDecorActionBar(Dialog dialog)
    {
        mTabs = new ArrayList();
        mSavedTabPosition = -1;
        mMenuVisibilityListeners = new ArrayList();
        mCurWindowVisibility = 0;
        mContentAnimations = true;
        mNowShowing = true;
        mHideListener = new _cls1();
        mShowListener = new _cls2();
        mUpdateListener = new _cls3();
        mDialog = dialog;
        init(dialog.getWindow().getDecorView());
    }

    public WindowDecorActionBar(View view)
    {
        mTabs = new ArrayList();
        mSavedTabPosition = -1;
        mMenuVisibilityListeners = new ArrayList();
        mCurWindowVisibility = 0;
        mContentAnimations = true;
        mNowShowing = true;
        mHideListener = new _cls1();
        mShowListener = new _cls2();
        mUpdateListener = new _cls3();
        if(!_2D_assertionsDisabled && !view.isInEditMode())
        {
            throw new AssertionError();
        } else
        {
            init(view);
            return;
        }
    }

    private static boolean checkShowingFlags(boolean flag, boolean flag1, boolean flag2)
    {
        if(flag2)
            return true;
        return !flag && !flag1;
    }

    private void cleanupTabs()
    {
        if(mSelectedTab != null)
            selectTab(null);
        mTabs.clear();
        if(mTabScrollView != null)
            mTabScrollView.removeAllTabs();
        mSavedTabPosition = -1;
    }

    private void configureTab(android.app.ActionBar.Tab tab, int i)
    {
        tab = (TabImpl)tab;
        if(tab.getCallback() == null)
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        tab.setPosition(i);
        mTabs.add(i, tab);
        int j = mTabs.size();
        for(i++; i < j; i++)
            ((TabImpl)mTabs.get(i)).setPosition(i);

    }

    private void ensureTabsExist()
    {
        if(mTabScrollView != null)
            return;
        ScrollingTabContainerView scrollingtabcontainerview = new ScrollingTabContainerView(mContext);
        if(mHasEmbeddedTabs)
        {
            scrollingtabcontainerview.setVisibility(0);
            mDecorToolbar.setEmbeddedTabView(scrollingtabcontainerview);
        } else
        {
            if(getNavigationMode() == 2)
            {
                scrollingtabcontainerview.setVisibility(0);
                if(mOverlayLayout != null)
                    mOverlayLayout.requestApplyInsets();
            } else
            {
                scrollingtabcontainerview.setVisibility(8);
            }
            mContainerView.setTabContainer(scrollingtabcontainerview);
        }
        mTabScrollView = scrollingtabcontainerview;
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

    private void hideForActionMode()
    {
        if(mShowingForMode)
        {
            mShowingForMode = false;
            if(mOverlayLayout != null)
                mOverlayLayout.setShowingForActionMode(false);
            updateVisibility(false);
        }
    }

    private void init(View view)
    {
        mOverlayLayout = (ActionBarOverlayLayout)view.findViewById(0x102021d);
        if(mOverlayLayout != null)
            mOverlayLayout.setActionBarVisibilityCallback(this);
        mDecorToolbar = getDecorToolbar(view.findViewById(0x102017d));
        mContextView = (ActionBarContextView)view.findViewById(0x1020182);
        mContainerView = (ActionBarContainer)view.findViewById(0x102017e);
        for(mSplitView = (ActionBarContainer)view.findViewById(0x102040f); mDecorToolbar == null || mContextView == null || mContainerView == null;)
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used ").append("with a compatible window decor layout").toString());

        mContext = mDecorToolbar.getContext();
        int i;
        boolean flag;
        if(mDecorToolbar.isSplit())
            i = 1;
        else
            i = 0;
        mContextDisplayMode = i;
        if((mDecorToolbar.getDisplayOptions() & 4) != 0)
            flag = true;
        else
            flag = false;
        if(flag)
            mDisplayHomeAsUpSet = true;
        view = ActionBarPolicy.get(mContext);
        if(view.enableHomeButtonByDefault())
            flag = true;
        setHomeButtonEnabled(flag);
        setHasEmbeddedTabs(view.hasEmbeddedTabs());
        view = mContext.obtainStyledAttributes(null, com.android.internal.R.styleable.ActionBar, 0x10102ce, 0);
        if(view.getBoolean(21, false))
            setHideOnContentScrollEnabled(true);
        i = view.getDimensionPixelSize(20, 0);
        if(i != 0)
            setElevation(i);
        view.recycle();
    }

    private void setHasEmbeddedTabs(boolean flag)
    {
        mHasEmbeddedTabs = flag;
        Object obj;
        boolean flag1;
        if(!mHasEmbeddedTabs)
        {
            mDecorToolbar.setEmbeddedTabView(null);
            mContainerView.setTabContainer(mTabScrollView);
        } else
        {
            mContainerView.setTabContainer(null);
            mDecorToolbar.setEmbeddedTabView(mTabScrollView);
        }
        if(getNavigationMode() == 2)
            flag = true;
        else
            flag = false;
        if(mTabScrollView != null)
            if(flag)
            {
                mTabScrollView.setVisibility(0);
                if(mOverlayLayout != null)
                    mOverlayLayout.requestApplyInsets();
            } else
            {
                mTabScrollView.setVisibility(8);
            }
        obj = mDecorToolbar;
        if(!mHasEmbeddedTabs)
            flag1 = flag;
        else
            flag1 = false;
        ((DecorToolbar) (obj)).setCollapsible(flag1);
        obj = mOverlayLayout;
        if(mHasEmbeddedTabs)
            flag = false;
        ((ActionBarOverlayLayout) (obj)).setHasNonEmbeddedTabs(flag);
    }

    private boolean shouldAnimateContextView()
    {
        return mContainerView.isLaidOut();
    }

    private void showForActionMode()
    {
        if(!mShowingForMode)
        {
            mShowingForMode = true;
            if(mOverlayLayout != null)
                mOverlayLayout.setShowingForActionMode(true);
            updateVisibility(false);
        }
    }

    private void updateVisibility(boolean flag)
    {
        if(!checkShowingFlags(mHiddenByApp, mHiddenBySystem, mShowingForMode)) goto _L2; else goto _L1
_L1:
        if(!mNowShowing)
        {
            mNowShowing = true;
            doShow(flag);
        }
_L4:
        return;
_L2:
        if(mNowShowing)
        {
            mNowShowing = false;
            doHide(flag);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void addOnMenuVisibilityListener(android.app.ActionBar.OnMenuVisibilityListener onmenuvisibilitylistener)
    {
        mMenuVisibilityListeners.add(onmenuvisibilitylistener);
    }

    public void addTab(android.app.ActionBar.Tab tab)
    {
        addTab(tab, mTabs.isEmpty());
    }

    public void addTab(android.app.ActionBar.Tab tab, int i)
    {
        addTab(tab, i, mTabs.isEmpty());
    }

    public void addTab(android.app.ActionBar.Tab tab, int i, boolean flag)
    {
        ensureTabsExist();
        mTabScrollView.addTab(tab, i, flag);
        configureTab(tab, i);
        if(flag)
            selectTab(tab);
    }

    public void addTab(android.app.ActionBar.Tab tab, boolean flag)
    {
        ensureTabsExist();
        mTabScrollView.addTab(tab, flag);
        configureTab(tab, mTabs.size());
        if(flag)
            selectTab(tab);
    }

    void animateToMode(boolean flag)
    {
        if(flag)
            showForActionMode();
        else
            hideForActionMode();
        if(shouldAnimateContextView())
        {
            Animator animator;
            Animator animator1;
            AnimatorSet animatorset;
            if(flag)
            {
                animator = mDecorToolbar.setupAnimatorToVisibility(8, 100L);
                animator1 = mContextView.setupAnimatorToVisibility(0, 200L);
            } else
            {
                animator1 = mDecorToolbar.setupAnimatorToVisibility(0, 200L);
                animator = mContextView.setupAnimatorToVisibility(8, 100L);
            }
            animatorset = new AnimatorSet();
            animatorset.playSequentially(new Animator[] {
                animator, animator1
            });
            animatorset.start();
        } else
        if(flag)
        {
            mDecorToolbar.setVisibility(8);
            mContextView.setVisibility(0);
        } else
        {
            mDecorToolbar.setVisibility(0);
            mContextView.setVisibility(8);
        }
    }

    public boolean collapseActionView()
    {
        if(mDecorToolbar != null && mDecorToolbar.hasExpandedActionView())
        {
            mDecorToolbar.collapseActionView();
            return true;
        } else
        {
            return false;
        }
    }

    void completeDeferredDestroyActionMode()
    {
        if(mDeferredModeDestroyCallback != null)
        {
            mDeferredModeDestroyCallback.onDestroyActionMode(mDeferredDestroyActionMode);
            mDeferredDestroyActionMode = null;
            mDeferredModeDestroyCallback = null;
        }
    }

    public void dispatchMenuVisibilityChanged(boolean flag)
    {
        if(flag == mLastMenuVisibility)
            return;
        mLastMenuVisibility = flag;
        int i = mMenuVisibilityListeners.size();
        for(int j = 0; j < i; j++)
            ((android.app.ActionBar.OnMenuVisibilityListener)mMenuVisibilityListeners.get(j)).onMenuVisibilityChanged(flag);

    }

    public void doHide(boolean flag)
    {
        if(mCurrentShowAnim != null)
            mCurrentShowAnim.end();
        if(mCurWindowVisibility == 0 && (mShowHideAnimationEnabled || flag))
        {
            mContainerView.setAlpha(1.0F);
            mContainerView.setTransitioning(true);
            AnimatorSet animatorset = new AnimatorSet();
            float f = -mContainerView.getHeight();
            float f1 = f;
            if(flag)
            {
                int ai[] = new int[2];
                ai[0] = 0;
                ai[1] = 0;
                mContainerView.getLocationInWindow(ai);
                f1 = f - (float)ai[1];
            }
            Object obj = ObjectAnimator.ofFloat(mContainerView, View.TRANSLATION_Y, new float[] {
                f1
            });
            ((ObjectAnimator) (obj)).addUpdateListener(mUpdateListener);
            obj = animatorset.play(((Animator) (obj)));
            if(mContentAnimations && mContentView != null)
                ((android.animation.AnimatorSet.Builder) (obj)).with(ObjectAnimator.ofFloat(mContentView, View.TRANSLATION_Y, new float[] {
                    0.0F, f1
                }));
            if(mSplitView != null && mSplitView.getVisibility() == 0)
            {
                mSplitView.setAlpha(1.0F);
                ((android.animation.AnimatorSet.Builder) (obj)).with(ObjectAnimator.ofFloat(mSplitView, View.TRANSLATION_Y, new float[] {
                    (float)mSplitView.getHeight()
                }));
            }
            animatorset.setInterpolator(AnimationUtils.loadInterpolator(mContext, 0x10c0002));
            animatorset.setDuration(250L);
            animatorset.addListener(mHideListener);
            mCurrentShowAnim = animatorset;
            animatorset.start();
        } else
        {
            mHideListener.onAnimationEnd(null);
        }
    }

    public void doShow(boolean flag)
    {
        if(mCurrentShowAnim != null)
            mCurrentShowAnim.end();
        mContainerView.setVisibility(0);
        if(mCurWindowVisibility == 0 && (mShowHideAnimationEnabled || flag))
        {
            mContainerView.setTranslationY(0.0F);
            float f = -mContainerView.getHeight();
            float f1 = f;
            if(flag)
            {
                int ai[] = new int[2];
                ai[0] = 0;
                ai[1] = 0;
                mContainerView.getLocationInWindow(ai);
                f1 = f - (float)ai[1];
            }
            mContainerView.setTranslationY(f1);
            AnimatorSet animatorset = new AnimatorSet();
            Object obj = ObjectAnimator.ofFloat(mContainerView, View.TRANSLATION_Y, new float[] {
                0.0F
            });
            ((ObjectAnimator) (obj)).addUpdateListener(mUpdateListener);
            obj = animatorset.play(((Animator) (obj)));
            if(mContentAnimations && mContentView != null)
                ((android.animation.AnimatorSet.Builder) (obj)).with(ObjectAnimator.ofFloat(mContentView, View.TRANSLATION_Y, new float[] {
                    f1, 0.0F
                }));
            if(mSplitView != null && mContextDisplayMode == 1)
            {
                mSplitView.setTranslationY(mSplitView.getHeight());
                mSplitView.setVisibility(0);
                ((android.animation.AnimatorSet.Builder) (obj)).with(ObjectAnimator.ofFloat(mSplitView, View.TRANSLATION_Y, new float[] {
                    0.0F
                }));
            }
            animatorset.setInterpolator(AnimationUtils.loadInterpolator(mContext, 0x10c0003));
            animatorset.setDuration(250L);
            animatorset.addListener(mShowListener);
            mCurrentShowAnim = animatorset;
            animatorset.start();
        } else
        {
            mContainerView.setAlpha(1.0F);
            mContainerView.setTranslationY(0.0F);
            if(mContentAnimations && mContentView != null)
                mContentView.setTranslationY(0.0F);
            if(mSplitView != null && mContextDisplayMode == 1)
            {
                mSplitView.setAlpha(1.0F);
                mSplitView.setTranslationY(0.0F);
                mSplitView.setVisibility(0);
            }
            mShowListener.onAnimationEnd(null);
        }
        if(mOverlayLayout != null)
            mOverlayLayout.requestApplyInsets();
    }

    public void enableContentAnimations(boolean flag)
    {
        mContentAnimations = flag;
    }

    public View getCustomView()
    {
        return mDecorToolbar.getCustomView();
    }

    public int getDisplayOptions()
    {
        return mDecorToolbar.getDisplayOptions();
    }

    public float getElevation()
    {
        return mContainerView.getElevation();
    }

    public int getHeight()
    {
        return mContainerView.getHeight();
    }

    public int getHideOffset()
    {
        return mOverlayLayout.getActionBarHideOffset();
    }

    public int getNavigationItemCount()
    {
        switch(mDecorToolbar.getNavigationMode())
        {
        default:
            return 0;

        case 2: // '\002'
            return mTabs.size();

        case 1: // '\001'
            return mDecorToolbar.getDropdownItemCount();
        }
    }

    public int getNavigationMode()
    {
        return mDecorToolbar.getNavigationMode();
    }

    public int getSelectedNavigationIndex()
    {
        int i = -1;
        switch(mDecorToolbar.getNavigationMode())
        {
        default:
            return -1;

        case 2: // '\002'
            if(mSelectedTab != null)
                i = mSelectedTab.getPosition();
            return i;

        case 1: // '\001'
            return mDecorToolbar.getDropdownSelectedPosition();
        }
    }

    public android.app.ActionBar.Tab getSelectedTab()
    {
        return mSelectedTab;
    }

    public CharSequence getSubtitle()
    {
        return mDecorToolbar.getSubtitle();
    }

    public android.app.ActionBar.Tab getTabAt(int i)
    {
        return (android.app.ActionBar.Tab)mTabs.get(i);
    }

    public int getTabCount()
    {
        return mTabs.size();
    }

    public Context getThemedContext()
    {
        if(mThemedContext == null)
        {
            TypedValue typedvalue = new TypedValue();
            mContext.getTheme().resolveAttribute(0x1010397, typedvalue, true);
            int i = typedvalue.resourceId;
            if(i != 0 && mContext.getThemeResId() != i)
                mThemedContext = new ContextThemeWrapper(mContext, i);
            else
                mThemedContext = mContext;
        }
        return mThemedContext;
    }

    public CharSequence getTitle()
    {
        return mDecorToolbar.getTitle();
    }

    public boolean hasIcon()
    {
        return mDecorToolbar.hasIcon();
    }

    public boolean hasLogo()
    {
        return mDecorToolbar.hasLogo();
    }

    public void hide()
    {
        if(!mHiddenByApp)
        {
            mHiddenByApp = true;
            updateVisibility(false);
        }
    }

    public void hideForSystem()
    {
        if(!mHiddenBySystem)
        {
            mHiddenBySystem = true;
            updateVisibility(true);
        }
    }

    public boolean isHideOnContentScrollEnabled()
    {
        return mOverlayLayout.isHideOnContentScrollEnabled();
    }

    public boolean isShowing()
    {
        boolean flag;
        int i;
        flag = true;
        i = getHeight();
        if(!mNowShowing) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
        if(i == 0) goto _L4; else goto _L3
_L3:
        if(getHideOffset() >= i) goto _L2; else goto _L5
_L5:
        flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = false;
        if(true) goto _L4; else goto _L6
_L6:
    }

    public boolean isTitleTruncated()
    {
        boolean flag;
        if(mDecorToolbar != null)
            flag = mDecorToolbar.isTitleTruncated();
        else
            flag = false;
        return flag;
    }

    public android.app.ActionBar.Tab newTab()
    {
        return new TabImpl();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        setHasEmbeddedTabs(ActionBarPolicy.get(mContext).hasEmbeddedTabs());
    }

    public void onContentScrollStarted()
    {
        if(mCurrentShowAnim != null)
        {
            mCurrentShowAnim.cancel();
            mCurrentShowAnim = null;
        }
    }

    public void onContentScrollStopped()
    {
    }

    public void onWindowVisibilityChanged(int i)
    {
        mCurWindowVisibility = i;
    }

    public void removeAllTabs()
    {
        cleanupTabs();
    }

    public void removeOnMenuVisibilityListener(android.app.ActionBar.OnMenuVisibilityListener onmenuvisibilitylistener)
    {
        mMenuVisibilityListeners.remove(onmenuvisibilitylistener);
    }

    public void removeTab(android.app.ActionBar.Tab tab)
    {
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i)
    {
        if(mTabScrollView == null)
            return;
        int j;
        TabImpl tabimpl;
        int k;
        if(mSelectedTab != null)
            j = mSelectedTab.getPosition();
        else
            j = mSavedTabPosition;
        mTabScrollView.removeTabAt(i);
        tabimpl = (TabImpl)mTabs.remove(i);
        if(tabimpl != null)
            tabimpl.setPosition(-1);
        k = mTabs.size();
        for(int l = i; l < k; l++)
            ((TabImpl)mTabs.get(l)).setPosition(l);

        if(j == i)
        {
            android.app.ActionBar.Tab tab;
            if(mTabs.isEmpty())
                tab = null;
            else
                tab = (android.app.ActionBar.Tab)mTabs.get(Math.max(0, i - 1));
            selectTab(tab);
        }
    }

    public void selectTab(android.app.ActionBar.Tab tab)
    {
        int i;
        FragmentTransaction fragmenttransaction;
        i = -1;
        if(getNavigationMode() != 2)
        {
            if(tab != null)
                i = tab.getPosition();
            mSavedTabPosition = i;
            return;
        }
        if(mDecorToolbar.getViewGroup().isInEditMode())
            fragmenttransaction = null;
        else
            fragmenttransaction = mActivity.getFragmentManager().beginTransaction().disallowAddToBackStack();
        if(mSelectedTab != tab) goto _L2; else goto _L1
_L1:
        if(mSelectedTab != null)
        {
            mSelectedTab.getCallback().onTabReselected(mSelectedTab, fragmenttransaction);
            mTabScrollView.animateToTab(tab.getPosition());
        }
_L4:
        if(fragmenttransaction != null && fragmenttransaction.isEmpty() ^ true)
            fragmenttransaction.commit();
        return;
_L2:
        ScrollingTabContainerView scrollingtabcontainerview = mTabScrollView;
        if(tab != null)
            i = tab.getPosition();
        scrollingtabcontainerview.setTabSelected(i);
        if(mSelectedTab != null)
            mSelectedTab.getCallback().onTabUnselected(mSelectedTab, fragmenttransaction);
        mSelectedTab = (TabImpl)tab;
        if(mSelectedTab != null)
            mSelectedTab.getCallback().onTabSelected(mSelectedTab, fragmenttransaction);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        mContainerView.setPrimaryBackground(drawable);
    }

    public void setCustomView(int i)
    {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i, mDecorToolbar.getViewGroup(), false));
    }

    public void setCustomView(View view)
    {
        mDecorToolbar.setCustomView(view);
    }

    public void setCustomView(View view, android.app.ActionBar.LayoutParams layoutparams)
    {
        view.setLayoutParams(layoutparams);
        mDecorToolbar.setCustomView(view);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean flag)
    {
        if(!mDisplayHomeAsUpSet)
            setDisplayHomeAsUpEnabled(flag);
    }

    public void setDisplayHomeAsUpEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 4);
    }

    public void setDisplayOptions(int i)
    {
        if((i & 4) != 0)
            mDisplayHomeAsUpSet = true;
        mDecorToolbar.setDisplayOptions(i);
    }

    public void setDisplayOptions(int i, int j)
    {
        int k = mDecorToolbar.getDisplayOptions();
        if((j & 4) != 0)
            mDisplayHomeAsUpSet = true;
        mDecorToolbar.setDisplayOptions(i & j | j & k);
    }

    public void setDisplayShowCustomEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 16;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 16);
    }

    public void setDisplayShowHomeEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 2);
    }

    public void setDisplayShowTitleEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 8;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 8);
    }

    public void setDisplayUseLogoEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        setDisplayOptions(i, 1);
    }

    public void setElevation(float f)
    {
        mContainerView.setElevation(f);
        if(mSplitView != null)
            mSplitView.setElevation(f);
    }

    public void setHideOffset(int i)
    {
        if(i != 0 && mOverlayLayout.isInOverlayMode() ^ true)
        {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        } else
        {
            mOverlayLayout.setActionBarHideOffset(i);
            return;
        }
    }

    public void setHideOnContentScrollEnabled(boolean flag)
    {
        if(flag && mOverlayLayout.isInOverlayMode() ^ true)
        {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        } else
        {
            mHideOnContentScroll = flag;
            mOverlayLayout.setHideOnContentScrollEnabled(flag);
            return;
        }
    }

    public void setHomeActionContentDescription(int i)
    {
        mDecorToolbar.setNavigationContentDescription(i);
    }

    public void setHomeActionContentDescription(CharSequence charsequence)
    {
        mDecorToolbar.setNavigationContentDescription(charsequence);
    }

    public void setHomeAsUpIndicator(int i)
    {
        mDecorToolbar.setNavigationIcon(i);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        mDecorToolbar.setNavigationIcon(drawable);
    }

    public void setHomeButtonEnabled(boolean flag)
    {
        mDecorToolbar.setHomeButtonEnabled(flag);
    }

    public void setIcon(int i)
    {
        mDecorToolbar.setIcon(i);
    }

    public void setIcon(Drawable drawable)
    {
        mDecorToolbar.setIcon(drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinneradapter, android.app.ActionBar.OnNavigationListener onnavigationlistener)
    {
        mDecorToolbar.setDropdownParams(spinneradapter, new NavItemSelectedListener(onnavigationlistener));
    }

    public void setLogo(int i)
    {
        mDecorToolbar.setLogo(i);
    }

    public void setLogo(Drawable drawable)
    {
        mDecorToolbar.setLogo(drawable);
    }

    public void setNavigationMode(int i)
    {
        boolean flag;
        int j;
        flag = false;
        j = mDecorToolbar.getNavigationMode();
        j;
        JVM INSTR tableswitch 2 2: default 32
    //                   2 146;
           goto _L1 _L2
_L1:
        if(j != i && mHasEmbeddedTabs ^ true && mOverlayLayout != null)
            mOverlayLayout.requestFitSystemWindows();
        mDecorToolbar.setNavigationMode(i);
        i;
        JVM INSTR tableswitch 2 2: default 88
    //                   2 171;
           goto _L3 _L4
_L3:
        Object obj = mDecorToolbar;
        boolean flag1;
        if(i == 2)
            flag1 = mHasEmbeddedTabs ^ true;
        else
            flag1 = false;
        ((DecorToolbar) (obj)).setCollapsible(flag1);
        obj = mOverlayLayout;
        flag1 = flag;
        if(i == 2)
            flag1 = mHasEmbeddedTabs ^ true;
        ((ActionBarOverlayLayout) (obj)).setHasNonEmbeddedTabs(flag1);
        return;
_L2:
        mSavedTabPosition = getSelectedNavigationIndex();
        selectTab(null);
        mTabScrollView.setVisibility(8);
          goto _L1
_L4:
        ensureTabsExist();
        mTabScrollView.setVisibility(0);
        if(mSavedTabPosition != -1)
        {
            setSelectedNavigationItem(mSavedTabPosition);
            mSavedTabPosition = -1;
        }
          goto _L3
    }

    public void setSelectedNavigationItem(int i)
    {
        mDecorToolbar.getNavigationMode();
        JVM INSTR tableswitch 1 2: default 32
    //                   1 59
    //                   2 43;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
_L3:
        selectTab((android.app.ActionBar.Tab)mTabs.get(i));
_L5:
        return;
_L2:
        mDecorToolbar.setDropdownSelectedPosition(i);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setShowHideAnimationEnabled(boolean flag)
    {
        mShowHideAnimationEnabled = flag;
        if(!flag && mCurrentShowAnim != null)
            mCurrentShowAnim.end();
    }

    public void setSplitBackgroundDrawable(Drawable drawable)
    {
        if(mSplitView != null)
            mSplitView.setSplitBackground(drawable);
    }

    public void setStackedBackgroundDrawable(Drawable drawable)
    {
        mContainerView.setStackedBackground(drawable);
    }

    public void setSubtitle(int i)
    {
        setSubtitle(((CharSequence) (mContext.getString(i))));
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mDecorToolbar.setSubtitle(charsequence);
    }

    public void setTitle(int i)
    {
        setTitle(((CharSequence) (mContext.getString(i))));
    }

    public void setTitle(CharSequence charsequence)
    {
        mDecorToolbar.setTitle(charsequence);
    }

    public void setWindowTitle(CharSequence charsequence)
    {
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public void show()
    {
        if(mHiddenByApp)
        {
            mHiddenByApp = false;
            updateVisibility(false);
        }
    }

    public void showForSystem()
    {
        if(mHiddenBySystem)
        {
            mHiddenBySystem = false;
            updateVisibility(true);
        }
    }

    public ActionMode startActionMode(android.view.ActionMode.Callback callback)
    {
        if(mActionMode != null)
            mActionMode.finish();
        mOverlayLayout.setHideOnContentScrollEnabled(false);
        mContextView.killMode();
        callback = new ActionModeImpl(mContextView.getContext(), callback);
        if(callback.dispatchOnCreate())
        {
            mActionMode = callback;
            callback.invalidate();
            mContextView.initForMode(callback);
            animateToMode(true);
            if(mSplitView != null && mContextDisplayMode == 1 && mSplitView.getVisibility() != 0)
            {
                mSplitView.setVisibility(0);
                if(mOverlayLayout != null)
                    mOverlayLayout.requestApplyInsets();
            }
            mContextView.sendAccessibilityEvent(32);
            return callback;
        } else
        {
            return null;
        }
    }

    static final boolean _2D_assertionsDisabled = com/android/internal/app/WindowDecorActionBar.desiredAssertionStatus() ^ true;
    private static final int CONTEXT_DISPLAY_NORMAL = 0;
    private static final int CONTEXT_DISPLAY_SPLIT = 1;
    private static final long FADE_IN_DURATION_MS = 200L;
    private static final long FADE_OUT_DURATION_MS = 100L;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    ActionMode mActionMode;
    private Activity mActivity;
    private ActionBarContainer mContainerView;
    private boolean mContentAnimations;
    private View mContentView;
    private Context mContext;
    private int mContextDisplayMode;
    private ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    private Animator mCurrentShowAnim;
    private DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    android.view.ActionMode.Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    private boolean mHiddenByApp;
    private boolean mHiddenBySystem;
    final android.animation.Animator.AnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList mMenuVisibilityListeners;
    private boolean mNowShowing;
    private ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    final android.animation.Animator.AnimatorListener mShowListener;
    private boolean mShowingForMode;
    private ActionBarContainer mSplitView;
    private ScrollingTabContainerView mTabScrollView;
    private ArrayList mTabs;
    private Context mThemedContext;
    final android.animation.ValueAnimator.AnimatorUpdateListener mUpdateListener;

}
