// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.*;
import android.widget.*;
import com.android.internal.view.menu.ActionMenuItem;
import com.android.internal.view.menu.MenuBuilder;

// Referenced classes of package com.android.internal.widget:
//            DecorToolbar, ScrollingTabContainerView

public class ToolbarWidgetWrapper
    implements DecorToolbar
{

    static boolean _2D_get0(ToolbarWidgetWrapper toolbarwidgetwrapper)
    {
        return toolbarwidgetwrapper.mMenuPrepared;
    }

    static CharSequence _2D_get1(ToolbarWidgetWrapper toolbarwidgetwrapper)
    {
        return toolbarwidgetwrapper.mTitle;
    }

    static Toolbar _2D_get2(ToolbarWidgetWrapper toolbarwidgetwrapper)
    {
        return toolbarwidgetwrapper.mToolbar;
    }

    static android.view.Window.Callback _2D_get3(ToolbarWidgetWrapper toolbarwidgetwrapper)
    {
        return toolbarwidgetwrapper.mWindowCallback;
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean flag)
    {
        this(toolbar, flag, 0x1040053);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean flag, int i)
    {
        mNavigationMode = 0;
        mDefaultNavigationContentDescription = 0;
        mToolbar = toolbar;
        mTitle = toolbar.getTitle();
        mSubtitle = toolbar.getSubtitle();
        boolean flag1;
        if(mTitle != null)
            flag1 = true;
        else
            flag1 = false;
        mTitleSet = flag1;
        mNavIcon = mToolbar.getNavigationIcon();
        toolbar = toolbar.getContext().obtainStyledAttributes(null, com.android.internal.R.styleable.ActionBar, 0x10102ce, 0);
        mDefaultNavigationIcon = toolbar.getDrawable(13);
        if(flag)
        {
            Object obj = toolbar.getText(5);
            if(!TextUtils.isEmpty(((CharSequence) (obj))))
                setTitle(((CharSequence) (obj)));
            obj = toolbar.getText(9);
            if(!TextUtils.isEmpty(((CharSequence) (obj))))
                setSubtitle(((CharSequence) (obj)));
            obj = toolbar.getDrawable(6);
            if(obj != null)
                setLogo(((Drawable) (obj)));
            obj = toolbar.getDrawable(0);
            if(obj != null)
                setIcon(((Drawable) (obj)));
            if(mNavIcon == null && mDefaultNavigationIcon != null)
                setNavigationIcon(mDefaultNavigationIcon);
            setDisplayOptions(toolbar.getInt(8, 0));
            int j = toolbar.getResourceId(10, 0);
            if(j != 0)
            {
                setCustomView(LayoutInflater.from(mToolbar.getContext()).inflate(j, mToolbar, false));
                setDisplayOptions(mDisplayOpts | 0x10);
            }
            j = toolbar.getLayoutDimension(4, 0);
            if(j > 0)
            {
                android.view.ViewGroup.LayoutParams layoutparams = mToolbar.getLayoutParams();
                layoutparams.height = j;
                mToolbar.setLayoutParams(layoutparams);
            }
            int k = toolbar.getDimensionPixelOffset(22, -1);
            j = toolbar.getDimensionPixelOffset(23, -1);
            if(k >= 0 || j >= 0)
                mToolbar.setContentInsetsRelative(Math.max(k, 0), Math.max(j, 0));
            j = toolbar.getResourceId(11, 0);
            if(j != 0)
                mToolbar.setTitleTextAppearance(mToolbar.getContext(), j);
            j = toolbar.getResourceId(12, 0);
            if(j != 0)
                mToolbar.setSubtitleTextAppearance(mToolbar.getContext(), j);
            j = toolbar.getResourceId(26, 0);
            if(j != 0)
                mToolbar.setPopupTheme(j);
        } else
        {
            mDisplayOpts = detectDisplayOptions();
        }
        toolbar.recycle();
        setDefaultNavigationContentDescription(i);
        mHomeDescription = mToolbar.getNavigationContentDescription();
        mToolbar.setNavigationOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(ToolbarWidgetWrapper._2D_get3(ToolbarWidgetWrapper.this) != null && ToolbarWidgetWrapper._2D_get0(ToolbarWidgetWrapper.this))
                    ToolbarWidgetWrapper._2D_get3(ToolbarWidgetWrapper.this).onMenuItemSelected(0, mNavItem);
            }

            final ActionMenuItem mNavItem;
            final ToolbarWidgetWrapper this$0;

            
            {
                this$0 = ToolbarWidgetWrapper.this;
                super();
                mNavItem = new ActionMenuItem(ToolbarWidgetWrapper._2D_get2(ToolbarWidgetWrapper.this).getContext(), 0, 0x102002c, 0, 0, ToolbarWidgetWrapper._2D_get1(ToolbarWidgetWrapper.this));
            }
        }
);
    }

    private int detectDisplayOptions()
    {
        byte byte0 = 11;
        if(mToolbar.getNavigationIcon() != null)
        {
            byte0 = 15;
            mDefaultNavigationIcon = mToolbar.getNavigationIcon();
        }
        return byte0;
    }

    private void ensureSpinner()
    {
        if(mSpinner == null)
        {
            mSpinner = new Spinner(getContext(), null, 0x10102d7);
            android.widget.Toolbar.LayoutParams layoutparams = new android.widget.Toolbar.LayoutParams(-2, -2, 0x800013);
            mSpinner.setLayoutParams(layoutparams);
        }
    }

    private void setTitleInt(CharSequence charsequence)
    {
        mTitle = charsequence;
        if((mDisplayOpts & 8) != 0)
            mToolbar.setTitle(charsequence);
    }

    private void updateHomeAccessibility()
    {
        if((mDisplayOpts & 4) != 0)
            if(TextUtils.isEmpty(mHomeDescription))
                mToolbar.setNavigationContentDescription(mDefaultNavigationContentDescription);
            else
                mToolbar.setNavigationContentDescription(mHomeDescription);
    }

    private void updateNavigationIcon()
    {
        if((mDisplayOpts & 4) != 0)
        {
            Toolbar toolbar = mToolbar;
            Drawable drawable;
            if(mNavIcon != null)
                drawable = mNavIcon;
            else
                drawable = mDefaultNavigationIcon;
            toolbar.setNavigationIcon(drawable);
        } else
        {
            mToolbar.setNavigationIcon(null);
        }
    }

    private void updateToolbarLogo()
    {
        Drawable drawable = null;
        if((mDisplayOpts & 2) != 0)
            if((mDisplayOpts & 1) != 0)
            {
                if(mLogo != null)
                    drawable = mLogo;
                else
                    drawable = mIcon;
            } else
            {
                drawable = mIcon;
            }
        mToolbar.setLogo(drawable);
    }

    public void animateToVisibility(int i)
    {
        Animator animator = setupAnimatorToVisibility(i, 200L);
        if(animator != null)
            animator.start();
    }

    public boolean canShowOverflowMenu()
    {
        return mToolbar.canShowOverflowMenu();
    }

    public boolean canSplit()
    {
        return false;
    }

    public void collapseActionView()
    {
        mToolbar.collapseActionView();
    }

    public void dismissPopupMenus()
    {
        mToolbar.dismissPopupMenus();
    }

    public Context getContext()
    {
        return mToolbar.getContext();
    }

    public View getCustomView()
    {
        return mCustomView;
    }

    public int getDisplayOptions()
    {
        return mDisplayOpts;
    }

    public int getDropdownItemCount()
    {
        int i;
        if(mSpinner != null)
            i = mSpinner.getCount();
        else
            i = 0;
        return i;
    }

    public int getDropdownSelectedPosition()
    {
        int i;
        if(mSpinner != null)
            i = mSpinner.getSelectedItemPosition();
        else
            i = 0;
        return i;
    }

    public int getHeight()
    {
        return mToolbar.getHeight();
    }

    public Menu getMenu()
    {
        return mToolbar.getMenu();
    }

    public int getNavigationMode()
    {
        return mNavigationMode;
    }

    public CharSequence getSubtitle()
    {
        return mToolbar.getSubtitle();
    }

    public CharSequence getTitle()
    {
        return mToolbar.getTitle();
    }

    public ViewGroup getViewGroup()
    {
        return mToolbar;
    }

    public int getVisibility()
    {
        return mToolbar.getVisibility();
    }

    public boolean hasEmbeddedTabs()
    {
        boolean flag;
        if(mTabView != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasExpandedActionView()
    {
        return mToolbar.hasExpandedActionView();
    }

    public boolean hasIcon()
    {
        boolean flag;
        if(mIcon != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasLogo()
    {
        boolean flag;
        if(mLogo != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hideOverflowMenu()
    {
        return mToolbar.hideOverflowMenu();
    }

    public void initIndeterminateProgress()
    {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void initProgress()
    {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public boolean isOverflowMenuShowPending()
    {
        return mToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing()
    {
        return mToolbar.isOverflowMenuShowing();
    }

    public boolean isSplit()
    {
        return false;
    }

    public boolean isTitleTruncated()
    {
        return mToolbar.isTitleTruncated();
    }

    public void restoreHierarchyState(SparseArray sparsearray)
    {
        mToolbar.restoreHierarchyState(sparsearray);
    }

    public void saveHierarchyState(SparseArray sparsearray)
    {
        mToolbar.saveHierarchyState(sparsearray);
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        mToolbar.setBackgroundDrawable(drawable);
    }

    public void setCollapsible(boolean flag)
    {
        mToolbar.setCollapsible(flag);
    }

    public void setCustomView(View view)
    {
        if(mCustomView != null && (mDisplayOpts & 0x10) != 0)
            mToolbar.removeView(mCustomView);
        mCustomView = view;
        if(view != null && (mDisplayOpts & 0x10) != 0)
            mToolbar.addView(mCustomView);
    }

    public void setDefaultNavigationContentDescription(int i)
    {
        if(i == mDefaultNavigationContentDescription)
            return;
        mDefaultNavigationContentDescription = i;
        if(TextUtils.isEmpty(mToolbar.getNavigationContentDescription()))
            setNavigationContentDescription(mDefaultNavigationContentDescription);
    }

    public void setDefaultNavigationIcon(Drawable drawable)
    {
        if(mDefaultNavigationIcon != drawable)
        {
            mDefaultNavigationIcon = drawable;
            updateNavigationIcon();
        }
    }

    public void setDisplayOptions(int i)
    {
        int j = mDisplayOpts ^ i;
        mDisplayOpts = i;
        if(j != 0)
        {
            if((j & 4) != 0)
            {
                if((i & 4) != 0)
                    updateHomeAccessibility();
                updateNavigationIcon();
            }
            if((j & 3) != 0)
                updateToolbarLogo();
            if((j & 8) != 0)
                if((i & 8) != 0)
                {
                    mToolbar.setTitle(mTitle);
                    mToolbar.setSubtitle(mSubtitle);
                } else
                {
                    mToolbar.setTitle(null);
                    mToolbar.setSubtitle(null);
                }
            if((j & 0x10) != 0 && mCustomView != null)
                if((i & 0x10) != 0)
                    mToolbar.addView(mCustomView);
                else
                    mToolbar.removeView(mCustomView);
        }
    }

    public void setDropdownParams(SpinnerAdapter spinneradapter, android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener)
    {
        ensureSpinner();
        mSpinner.setAdapter(spinneradapter);
        mSpinner.setOnItemSelectedListener(onitemselectedlistener);
    }

    public void setDropdownSelectedPosition(int i)
    {
        if(mSpinner == null)
        {
            throw new IllegalStateException("Can't set dropdown selected position without an adapter");
        } else
        {
            mSpinner.setSelection(i);
            return;
        }
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingtabcontainerview)
    {
        if(mTabView != null && mTabView.getParent() == mToolbar)
            mToolbar.removeView(mTabView);
        mTabView = scrollingtabcontainerview;
        if(scrollingtabcontainerview != null && mNavigationMode == 2)
        {
            mToolbar.addView(mTabView, 0);
            android.widget.Toolbar.LayoutParams layoutparams = (android.widget.Toolbar.LayoutParams)mTabView.getLayoutParams();
            layoutparams.width = -2;
            layoutparams.height = -2;
            layoutparams.gravity = 0x800053;
            scrollingtabcontainerview.setAllowCollapse(true);
        }
    }

    public void setHomeButtonEnabled(boolean flag)
    {
    }

    public void setIcon(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = getContext().getDrawable(i);
        else
            drawable = null;
        setIcon(drawable);
    }

    public void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        updateToolbarLogo();
    }

    public void setLogo(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = getContext().getDrawable(i);
        else
            drawable = null;
        setLogo(drawable);
    }

    public void setLogo(Drawable drawable)
    {
        mLogo = drawable;
        updateToolbarLogo();
    }

    public void setMenu(Menu menu, com.android.internal.view.menu.MenuPresenter.Callback callback)
    {
        if(mActionMenuPresenter == null)
        {
            mActionMenuPresenter = new ActionMenuPresenter(mToolbar.getContext());
            mActionMenuPresenter.setId(0x1020185);
        }
        mActionMenuPresenter.setCallback(callback);
        mToolbar.setMenu((MenuBuilder)menu, mActionMenuPresenter);
    }

    public void setMenuCallbacks(com.android.internal.view.menu.MenuPresenter.Callback callback, com.android.internal.view.menu.MenuBuilder.Callback callback1)
    {
        mToolbar.setMenuCallbacks(callback, callback1);
    }

    public void setMenuPrepared()
    {
        mMenuPrepared = true;
    }

    public void setNavigationContentDescription(int i)
    {
        Object obj;
        if(i == 0)
            obj = null;
        else
            obj = getContext().getString(i);
        setNavigationContentDescription(((CharSequence) (obj)));
    }

    public void setNavigationContentDescription(CharSequence charsequence)
    {
        mHomeDescription = charsequence;
        updateHomeAccessibility();
    }

    public void setNavigationIcon(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = mToolbar.getContext().getDrawable(i);
        else
            drawable = null;
        setNavigationIcon(drawable);
    }

    public void setNavigationIcon(Drawable drawable)
    {
        mNavIcon = drawable;
        updateNavigationIcon();
    }

    public void setNavigationMode(int i)
    {
        int j = mNavigationMode;
        if(i == j) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 92
    //                   2 127;
           goto _L3 _L4 _L5
_L3:
        mNavigationMode = i;
        i;
        JVM INSTR tableswitch 0 2: default 64
    //                   0 178
    //                   1 162
    //                   2 179;
           goto _L6 _L2 _L7 _L8
_L6:
        throw new IllegalArgumentException((new StringBuilder()).append("Invalid navigation mode ").append(i).toString());
_L4:
        if(mSpinner != null && mSpinner.getParent() == mToolbar)
            mToolbar.removeView(mSpinner);
          goto _L3
_L5:
        if(mTabView != null && mTabView.getParent() == mToolbar)
            mToolbar.removeView(mTabView);
          goto _L3
_L7:
        ensureSpinner();
        mToolbar.addView(mSpinner, 0);
_L2:
        return;
_L8:
        if(mTabView != null)
        {
            mToolbar.addView(mTabView, 0);
            android.widget.Toolbar.LayoutParams layoutparams = (android.widget.Toolbar.LayoutParams)mTabView.getLayoutParams();
            layoutparams.width = -2;
            layoutparams.height = -2;
            layoutparams.gravity = 0x800053;
        }
        if(true) goto _L2; else goto _L9
_L9:
    }

    public void setSplitToolbar(boolean flag)
    {
        if(flag)
            throw new UnsupportedOperationException("Cannot split an android.widget.Toolbar");
        else
            return;
    }

    public void setSplitView(ViewGroup viewgroup)
    {
    }

    public void setSplitWhenNarrow(boolean flag)
    {
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mSubtitle = charsequence;
        if((mDisplayOpts & 8) != 0)
            mToolbar.setSubtitle(charsequence);
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitleSet = true;
        setTitleInt(charsequence);
    }

    public void setVisibility(int i)
    {
        mToolbar.setVisibility(i);
    }

    public void setWindowCallback(android.view.Window.Callback callback)
    {
        mWindowCallback = callback;
    }

    public void setWindowTitle(CharSequence charsequence)
    {
        if(!mTitleSet)
            setTitleInt(charsequence);
    }

    public Animator setupAnimatorToVisibility(int i, long l)
    {
        if(i == 8)
        {
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(mToolbar, View.ALPHA, new float[] {
                1.0F, 0.0F
            });
            objectanimator.setDuration(l);
            objectanimator.addListener(new AnimatorListenerAdapter() {

                public void onAnimationCancel(Animator animator)
                {
                    mCanceled = true;
                }

                public void onAnimationEnd(Animator animator)
                {
                    if(!mCanceled)
                        ToolbarWidgetWrapper._2D_get2(ToolbarWidgetWrapper.this).setVisibility(8);
                }

                private boolean mCanceled;
                final ToolbarWidgetWrapper this$0;

            
            {
                this$0 = ToolbarWidgetWrapper.this;
                super();
                mCanceled = false;
            }
            }
);
            return objectanimator;
        }
        if(i == 0)
        {
            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(mToolbar, View.ALPHA, new float[] {
                0.0F, 1.0F
            });
            objectanimator1.setDuration(l);
            objectanimator1.addListener(new AnimatorListenerAdapter() {

                public void onAnimationStart(Animator animator)
                {
                    ToolbarWidgetWrapper._2D_get2(ToolbarWidgetWrapper.this).setVisibility(0);
                }

                final ToolbarWidgetWrapper this$0;

            
            {
                this$0 = ToolbarWidgetWrapper.this;
                super();
            }
            }
);
            return objectanimator1;
        } else
        {
            return null;
        }
    }

    public boolean showOverflowMenu()
    {
        return mToolbar.showOverflowMenu();
    }

    private static final int AFFECTS_LOGO_MASK = 3;
    private static final long DEFAULT_FADE_DURATION_MS = 200L;
    private static final String TAG = "ToolbarWidgetWrapper";
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    private boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private Spinner mSpinner;
    private CharSequence mSubtitle;
    private View mTabView;
    private CharSequence mTitle;
    private boolean mTitleSet;
    private Toolbar mToolbar;
    private android.view.Window.Callback mWindowCallback;
}
