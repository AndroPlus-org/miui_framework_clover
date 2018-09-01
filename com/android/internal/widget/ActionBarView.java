// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.widget.*;
import com.android.internal.view.menu.*;
import java.util.List;

// Referenced classes of package com.android.internal.widget:
//            AbsActionBarView, DecorToolbar, ScrollingTabContainerView, ActionBarContextView

public class ActionBarView extends AbsActionBarView
    implements DecorToolbar
{
    private class ExpandedActionViewMenuPresenter
        implements MenuPresenter
    {

        public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
        {
            if(mExpandedActionView instanceof CollapsibleActionView)
                ((CollapsibleActionView)mExpandedActionView).onActionViewCollapsed();
            removeView(mExpandedActionView);
            ActionBarView._2D_get11(ActionBarView.this).removeView(ActionBarView._2D_get2(ActionBarView.this));
            mExpandedActionView = null;
            if((ActionBarView._2D_get1(ActionBarView.this) & 2) != 0)
                ActionBarView._2D_get4(ActionBarView.this).setVisibility(0);
            if((ActionBarView._2D_get1(ActionBarView.this) & 8) != 0)
                if(ActionBarView._2D_get10(ActionBarView.this) == null)
                    ActionBarView._2D_wrap0(ActionBarView.this);
                else
                    ActionBarView._2D_get10(ActionBarView.this).setVisibility(0);
            if(ActionBarView._2D_get9(ActionBarView.this) != null)
                ActionBarView._2D_get9(ActionBarView.this).setVisibility(0);
            if(ActionBarView._2D_get8(ActionBarView.this) != null)
                ActionBarView._2D_get8(ActionBarView.this).setVisibility(0);
            if(ActionBarView._2D_get0(ActionBarView.this) != null)
                ActionBarView._2D_get0(ActionBarView.this).setVisibility(0);
            ActionBarView._2D_get2(ActionBarView.this).setIcon(null);
            mCurrentExpandedItem = null;
            setHomeButtonEnabled(ActionBarView._2D_get12(ActionBarView.this));
            requestLayout();
            menuitemimpl.setActionViewExpanded(false);
            return true;
        }

        public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
        {
            mExpandedActionView = menuitemimpl.getActionView();
            ActionBarView._2D_get2(ActionBarView.this).setIcon(ActionBarView._2D_get5(ActionBarView.this).getConstantState().newDrawable(getResources()));
            mCurrentExpandedItem = menuitemimpl;
            if(mExpandedActionView.getParent() != ActionBarView.this)
                addView(mExpandedActionView);
            if(ActionBarView._2D_get2(ActionBarView.this).getParent() != ActionBarView._2D_get11(ActionBarView.this))
                ActionBarView._2D_get11(ActionBarView.this).addView(ActionBarView._2D_get2(ActionBarView.this));
            ActionBarView._2D_get4(ActionBarView.this).setVisibility(8);
            if(ActionBarView._2D_get10(ActionBarView.this) != null)
                ActionBarView._2D_get10(ActionBarView.this).setVisibility(8);
            if(ActionBarView._2D_get9(ActionBarView.this) != null)
                ActionBarView._2D_get9(ActionBarView.this).setVisibility(8);
            if(ActionBarView._2D_get8(ActionBarView.this) != null)
                ActionBarView._2D_get8(ActionBarView.this).setVisibility(8);
            if(ActionBarView._2D_get0(ActionBarView.this) != null)
                ActionBarView._2D_get0(ActionBarView.this).setVisibility(8);
            ActionBarView._2D_wrap1(ActionBarView.this, false, false);
            requestLayout();
            menuitemimpl.setActionViewExpanded(true);
            if(mExpandedActionView instanceof CollapsibleActionView)
                ((CollapsibleActionView)mExpandedActionView).onActionViewExpanded();
            return true;
        }

        public boolean flagActionItems()
        {
            return false;
        }

        public int getId()
        {
            return 0;
        }

        public MenuView getMenuView(ViewGroup viewgroup)
        {
            return null;
        }

        public void initForMenu(Context context, MenuBuilder menubuilder)
        {
            if(mMenu != null && mCurrentExpandedItem != null)
                mMenu.collapseItemActionView(mCurrentExpandedItem);
            mMenu = menubuilder;
        }

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
        }

        public void onRestoreInstanceState(Parcelable parcelable)
        {
        }

        public Parcelable onSaveInstanceState()
        {
            return null;
        }

        public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
        {
            return false;
        }

        public void setCallback(com.android.internal.view.menu.MenuPresenter.Callback callback)
        {
        }

        public void updateMenuView(boolean flag)
        {
            if(mCurrentExpandedItem == null) goto _L2; else goto _L1
_L1:
            boolean flag1;
            boolean flag2;
            flag1 = false;
            flag2 = flag1;
            if(mMenu == null) goto _L4; else goto _L3
_L3:
            int i;
            int j;
            i = mMenu.size();
            j = 0;
_L9:
            flag2 = flag1;
            if(j >= i) goto _L4; else goto _L5
_L5:
            if(mMenu.getItem(j) != mCurrentExpandedItem) goto _L7; else goto _L6
_L6:
            flag2 = true;
_L4:
            if(!flag2)
                collapseItemActionView(mMenu, mCurrentExpandedItem);
_L2:
            return;
_L7:
            j++;
            if(true) goto _L9; else goto _L8
_L8:
        }

        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;
        final ActionBarView this$0;

        private ExpandedActionViewMenuPresenter()
        {
            this$0 = ActionBarView.this;
            super();
        }

        ExpandedActionViewMenuPresenter(ExpandedActionViewMenuPresenter expandedactionviewmenupresenter)
        {
            this();
        }
    }

    private static class HomeView extends FrameLayout
    {

        private void updateUpIndicator()
        {
            if(mUpIndicator != null)
                mUpView.setImageDrawable(mUpIndicator);
            else
            if(mUpIndicatorRes != 0)
                mUpView.setImageDrawable(getContext().getDrawable(mUpIndicatorRes));
            else
                mUpView.setImageDrawable(mDefaultUpIndicator);
        }

        public boolean dispatchHoverEvent(MotionEvent motionevent)
        {
            return onHoverEvent(motionevent);
        }

        public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
        {
            onPopulateAccessibilityEvent(accessibilityevent);
            return true;
        }

        public int getStartOffset()
        {
            int i;
            if(mUpView.getVisibility() == 8)
                i = mStartOffset;
            else
                i = 0;
            return i;
        }

        public int getUpWidth()
        {
            return mUpWidth;
        }

        protected void onConfigurationChanged(Configuration configuration)
        {
            super.onConfigurationChanged(configuration);
            if(mUpIndicatorRes != 0)
                updateUpIndicator();
        }

        protected void onFinishInflate()
        {
            mUpView = (ImageView)findViewById(0x1020495);
            mIconView = (ImageView)findViewById(0x102002c);
            mDefaultUpIndicator = mUpView.getDrawable();
        }

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
            int i1 = (l - j) / 2;
            flag = isLayoutRtl();
            int j1 = getWidth();
            j = 0;
            int k1 = i;
            l = k;
            int i2;
            if(mUpView.getVisibility() != 8)
            {
                android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)mUpView.getLayoutParams();
                int l1 = mUpView.getMeasuredHeight();
                l = mUpView.getMeasuredWidth();
                i2 = layoutparams.leftMargin + l + layoutparams.rightMargin;
                int j2 = i1 - l1 / 2;
                if(flag)
                {
                    j = j1;
                    k1 = j1 - l;
                    k -= i2;
                    l = j;
                    j = k1;
                } else
                {
                    j = 0;
                    i += i2;
                }
                mUpView.layout(j, j2, l, j2 + l1);
                l = k;
                k1 = i;
                j = i2;
            }
            layoutparams = (android.widget.FrameLayout.LayoutParams)mIconView.getLayoutParams();
            k = mIconView.getMeasuredHeight();
            i2 = mIconView.getMeasuredWidth();
            i = (l - k1) / 2;
            l = Math.max(layoutparams.topMargin, i1 - k / 2);
            i = Math.max(layoutparams.getMarginStart(), i - i2 / 2);
            if(flag)
            {
                j = j1 - j - i;
                i = j - i2;
            } else
            {
                i = j + i;
                j = i + i2;
            }
            mIconView.layout(i, l, j, l + k);
        }

        protected void onMeasure(int i, int j)
        {
            int l;
            int j1;
            int k1;
            measureChildWithMargins(mUpView, i, 0, j, 0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)mUpView.getLayoutParams();
            int k = layoutparams.leftMargin + layoutparams.rightMargin;
            mUpWidth = mUpView.getMeasuredWidth();
            mStartOffset = mUpWidth + k;
            int i1;
            if(mUpView.getVisibility() == 8)
                l = 0;
            else
                l = mStartOffset;
            i1 = layoutparams.topMargin + mUpView.getMeasuredHeight() + layoutparams.bottomMargin;
            if(mIconView.getVisibility() != 8)
            {
                measureChildWithMargins(mIconView, i, l, j, 0);
                android.widget.FrameLayout.LayoutParams layoutparams1 = (android.widget.FrameLayout.LayoutParams)mIconView.getLayoutParams();
                j1 = l + (layoutparams1.leftMargin + mIconView.getMeasuredWidth() + layoutparams1.rightMargin);
                k1 = Math.max(i1, layoutparams1.topMargin + mIconView.getMeasuredHeight() + layoutparams1.bottomMargin);
            } else
            {
                j1 = l;
                k1 = i1;
                if(k < 0)
                {
                    j1 = l - k;
                    k1 = i1;
                }
            }
            k = android.view.View.MeasureSpec.getMode(i);
            i1 = android.view.View.MeasureSpec.getMode(j);
            l = android.view.View.MeasureSpec.getSize(i);
            i = android.view.View.MeasureSpec.getSize(j);
            k;
            JVM INSTR lookupswitch 2: default 224
        //                       -2147483648: 297
        //                       1073741824: 309;
               goto _L1 _L2 _L3
_L1:
            i1;
            JVM INSTR lookupswitch 2: default 252
        //                       -2147483648: 316
        //                       1073741824: 327;
               goto _L4 _L5 _L6
_L4:
            setMeasuredDimension(j1, k1);
            return;
_L2:
            j1 = Math.min(j1, l);
              goto _L1
_L3:
            j1 = l;
              goto _L1
_L5:
            k1 = Math.min(k1, i);
              goto _L4
_L6:
            k1 = i;
              goto _L4
        }

        public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
        {
            super.onPopulateAccessibilityEventInternal(accessibilityevent);
            CharSequence charsequence = getContentDescription();
            if(!TextUtils.isEmpty(charsequence))
                accessibilityevent.getText().add(charsequence);
        }

        public void setDefaultUpIndicator(Drawable drawable)
        {
            mDefaultUpIndicator = drawable;
            updateUpIndicator();
        }

        public void setIcon(Drawable drawable)
        {
            mIconView.setImageDrawable(drawable);
        }

        public void setShowIcon(boolean flag)
        {
            ImageView imageview = mIconView;
            int i;
            if(flag)
                i = 0;
            else
                i = 8;
            imageview.setVisibility(i);
        }

        public void setShowUp(boolean flag)
        {
            ImageView imageview = mUpView;
            int i;
            if(flag)
                i = 0;
            else
                i = 8;
            imageview.setVisibility(i);
        }

        public void setUpIndicator(int i)
        {
            mUpIndicatorRes = i;
            mUpIndicator = null;
            updateUpIndicator();
        }

        public void setUpIndicator(Drawable drawable)
        {
            mUpIndicator = drawable;
            mUpIndicatorRes = 0;
            updateUpIndicator();
        }

        private static final long DEFAULT_TRANSITION_DURATION = 150L;
        private Drawable mDefaultUpIndicator;
        private ImageView mIconView;
        private int mStartOffset;
        private Drawable mUpIndicator;
        private int mUpIndicatorRes;
        private ImageView mUpView;
        private int mUpWidth;

        public HomeView(Context context)
        {
            this(context, null);
        }

        public HomeView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = getLayoutTransition();
            if(context != null)
                context.setDuration(150L);
        }
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(expandedMenuItemId);
            if(isOverflowOpen)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int expandedMenuItemId;
        boolean isOverflowOpen;


        private SavedState(Parcel parcel)
        {
            boolean flag = false;
            super(parcel);
            expandedMenuItemId = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            isOverflowOpen = flag;
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    static View _2D_get0(ActionBarView actionbarview)
    {
        return actionbarview.mCustomNavView;
    }

    static int _2D_get1(ActionBarView actionbarview)
    {
        return actionbarview.mDisplayOptions;
    }

    static LinearLayout _2D_get10(ActionBarView actionbarview)
    {
        return actionbarview.mTitleLayout;
    }

    static ViewGroup _2D_get11(ActionBarView actionbarview)
    {
        return actionbarview.mUpGoerFive;
    }

    static boolean _2D_get12(ActionBarView actionbarview)
    {
        return actionbarview.mWasHomeEnabled;
    }

    static HomeView _2D_get2(ActionBarView actionbarview)
    {
        return actionbarview.mExpandedHomeLayout;
    }

    static ExpandedActionViewMenuPresenter _2D_get3(ActionBarView actionbarview)
    {
        return actionbarview.mExpandedMenuPresenter;
    }

    static HomeView _2D_get4(ActionBarView actionbarview)
    {
        return actionbarview.mHomeLayout;
    }

    static Drawable _2D_get5(ActionBarView actionbarview)
    {
        return actionbarview.mIcon;
    }

    static ActionMenuItem _2D_get6(ActionBarView actionbarview)
    {
        return actionbarview.mLogoNavItem;
    }

    static boolean _2D_get7(ActionBarView actionbarview)
    {
        return actionbarview.mMenuPrepared;
    }

    static Spinner _2D_get8(ActionBarView actionbarview)
    {
        return actionbarview.mSpinner;
    }

    static ScrollingTabContainerView _2D_get9(ActionBarView actionbarview)
    {
        return actionbarview.mTabScrollView;
    }

    static void _2D_wrap0(ActionBarView actionbarview)
    {
        actionbarview.initTitle();
    }

    static void _2D_wrap1(ActionBarView actionbarview, boolean flag, boolean flag1)
    {
        actionbarview.setHomeButtonEnabled(flag, flag1);
    }

    public ActionBarView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mDisplayOptions = -1;
        mDefaultUpDescription = 0x1040053;
        setBackgroundResource(0);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ActionBar, 0x10102ce, 0);
        mNavigationMode = typedarray.getInt(7, 0);
        mTitle = typedarray.getText(5);
        mSubtitle = typedarray.getText(9);
        mLogo = typedarray.getDrawable(6);
        mIcon = typedarray.getDrawable(0);
        attributeset = LayoutInflater.from(context);
        int i = typedarray.getResourceId(16, 0x109001a);
        mUpGoerFive = (ViewGroup)attributeset.inflate(0x109001d, this, false);
        mHomeLayout = (HomeView)attributeset.inflate(i, mUpGoerFive, false);
        mExpandedHomeLayout = (HomeView)attributeset.inflate(i, mUpGoerFive, false);
        mExpandedHomeLayout.setShowUp(true);
        mExpandedHomeLayout.setOnClickListener(mExpandedActionViewUpListener);
        mExpandedHomeLayout.setContentDescription(getResources().getText(mDefaultUpDescription));
        Drawable drawable = mUpGoerFive.getBackground();
        if(drawable != null)
            mExpandedHomeLayout.setBackground(drawable.getConstantState().newDrawable());
        mExpandedHomeLayout.setEnabled(true);
        mExpandedHomeLayout.setFocusable(true);
        mTitleStyleRes = typedarray.getResourceId(11, 0);
        mSubtitleStyleRes = typedarray.getResourceId(12, 0);
        mProgressStyle = typedarray.getResourceId(1, 0);
        mIndeterminateProgressStyle = typedarray.getResourceId(14, 0);
        mProgressBarPadding = typedarray.getDimensionPixelOffset(15, 0);
        mItemPadding = typedarray.getDimensionPixelOffset(17, 0);
        setDisplayOptions(typedarray.getInt(8, 0));
        i = typedarray.getResourceId(10, 0);
        if(i != 0)
        {
            mCustomNavView = attributeset.inflate(i, this, false);
            mNavigationMode = 0;
            setDisplayOptions(mDisplayOptions | 0x10);
        }
        mContentHeight = typedarray.getLayoutDimension(4, 0);
        typedarray.recycle();
        mLogoNavItem = new ActionMenuItem(context, 0, 0x102002c, 0, 0, mTitle);
        mUpGoerFive.setOnClickListener(mUpClickListener);
        mUpGoerFive.setClickable(true);
        mUpGoerFive.setFocusable(true);
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
    }

    private CharSequence buildHomeContentDescription()
    {
        Object obj;
        CharSequence charsequence;
        CharSequence charsequence1;
        if(mHomeDescription != null)
            obj = mHomeDescription;
        else
        if((mDisplayOptions & 4) != 0)
            obj = mContext.getResources().getText(mDefaultUpDescription);
        else
            obj = mContext.getResources().getText(0x1040050);
        charsequence = getTitle();
        charsequence1 = getSubtitle();
        if(!TextUtils.isEmpty(charsequence))
        {
            if(!TextUtils.isEmpty(charsequence1))
                obj = getResources().getString(0x1040052, new Object[] {
                    charsequence, charsequence1, obj
                });
            else
                obj = getResources().getString(0x1040051, new Object[] {
                    charsequence, obj
                });
            return ((CharSequence) (obj));
        } else
        {
            return ((CharSequence) (obj));
        }
    }

    private void configPresenters(MenuBuilder menubuilder)
    {
        if(menubuilder != null)
        {
            menubuilder.addMenuPresenter(mActionMenuPresenter, mPopupContext);
            menubuilder.addMenuPresenter(mExpandedMenuPresenter, mPopupContext);
        } else
        {
            mActionMenuPresenter.initForMenu(mPopupContext, null);
            mExpandedMenuPresenter.initForMenu(mPopupContext, null);
            mActionMenuPresenter.updateMenuView(true);
            mExpandedMenuPresenter.updateMenuView(true);
        }
    }

    private void initTitle()
    {
        if(mTitleLayout == null)
        {
            mTitleLayout = (LinearLayout)LayoutInflater.from(getContext()).inflate(0x109001c, this, false);
            mTitleView = (TextView)mTitleLayout.findViewById(0x1020181);
            mSubtitleView = (TextView)mTitleLayout.findViewById(0x1020180);
            if(mTitleStyleRes != 0)
                mTitleView.setTextAppearance(mTitleStyleRes);
            if(mTitle != null)
                mTitleView.setText(mTitle);
            if(mSubtitleStyleRes != 0)
                mSubtitleView.setTextAppearance(mSubtitleStyleRes);
            if(mSubtitle != null)
            {
                mSubtitleView.setText(mSubtitle);
                mSubtitleView.setVisibility(0);
            }
        }
        mUpGoerFive.addView(mTitleLayout);
        if(mExpandedActionView != null || TextUtils.isEmpty(mTitle) && TextUtils.isEmpty(mSubtitle))
            mTitleLayout.setVisibility(8);
        else
            mTitleLayout.setVisibility(0);
    }

    private void setHomeButtonEnabled(boolean flag, boolean flag1)
    {
        if(flag1)
            mWasHomeEnabled = flag;
        if(mExpandedActionView != null)
        {
            return;
        } else
        {
            mUpGoerFive.setEnabled(flag);
            mUpGoerFive.setFocusable(flag);
            updateHomeAccessibility(flag);
            return;
        }
    }

    private void setTitleImpl(CharSequence charsequence)
    {
        boolean flag = false;
        mTitle = charsequence;
        if(mTitleView != null)
        {
            mTitleView.setText(charsequence);
            int i;
            LinearLayout linearlayout;
            if(mExpandedActionView == null && (mDisplayOptions & 8) != 0)
            {
                if(TextUtils.isEmpty(mTitle))
                    i = TextUtils.isEmpty(mSubtitle) ^ true;
                else
                    i = 1;
            } else
            {
                i = 0;
            }
            linearlayout = mTitleLayout;
            if(i != 0)
                i = ((flag) ? 1 : 0);
            else
                i = 8;
            linearlayout.setVisibility(i);
        }
        if(mLogoNavItem != null)
            mLogoNavItem.setTitle(charsequence);
        updateHomeAccessibility(mUpGoerFive.isEnabled());
    }

    private void updateHomeAccessibility(boolean flag)
    {
        if(!flag)
        {
            mUpGoerFive.setContentDescription(null);
            mUpGoerFive.setImportantForAccessibility(2);
        } else
        {
            mUpGoerFive.setImportantForAccessibility(0);
            mUpGoerFive.setContentDescription(buildHomeContentDescription());
        }
    }

    public boolean canSplit()
    {
        return true;
    }

    public void collapseActionView()
    {
        MenuItemImpl menuitemimpl;
        if(mExpandedMenuPresenter == null)
            menuitemimpl = null;
        else
            menuitemimpl = mExpandedMenuPresenter.mCurrentExpandedItem;
        if(menuitemimpl != null)
            menuitemimpl.collapseActionView();
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.app.ActionBar.LayoutParams(0x800013);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.app.ActionBar.LayoutParams(getContext(), attributeset);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        android.view.ViewGroup.LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = generateDefaultLayoutParams();
        return layoutparams1;
    }

    public View getCustomView()
    {
        return mCustomNavView;
    }

    public int getDisplayOptions()
    {
        return mDisplayOptions;
    }

    public int getDropdownItemCount()
    {
        int i;
        if(mSpinnerAdapter != null)
            i = mSpinnerAdapter.getCount();
        else
            i = 0;
        return i;
    }

    public int getDropdownSelectedPosition()
    {
        return mSpinner.getSelectedItemPosition();
    }

    public Menu getMenu()
    {
        return mOptionsMenu;
    }

    public int getNavigationMode()
    {
        return mNavigationMode;
    }

    public CharSequence getSubtitle()
    {
        return mSubtitle;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public ViewGroup getViewGroup()
    {
        return this;
    }

    public boolean hasEmbeddedTabs()
    {
        return mIncludeTabs;
    }

    public boolean hasExpandedActionView()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mExpandedMenuPresenter != null)
        {
            flag1 = flag;
            if(mExpandedMenuPresenter.mCurrentExpandedItem != null)
                flag1 = true;
        }
        return flag1;
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

    public void initIndeterminateProgress()
    {
        mIndeterminateProgressView = new ProgressBar(mContext, null, 0, mIndeterminateProgressStyle);
        mIndeterminateProgressView.setId(0x1020394);
        mIndeterminateProgressView.setVisibility(8);
        addView(mIndeterminateProgressView);
    }

    public void initProgress()
    {
        mProgressView = new ProgressBar(mContext, null, 0, mProgressStyle);
        mProgressView.setId(0x1020395);
        mProgressView.setMax(10000);
        mProgressView.setVisibility(8);
        addView(mProgressView);
    }

    public boolean isSplit()
    {
        return mSplitActionBar;
    }

    public boolean isTitleTruncated()
    {
        if(mTitleView == null)
            return false;
        Layout layout = mTitleView.getLayout();
        if(layout == null)
            return false;
        int i = layout.getLineCount();
        for(int j = 0; j < i; j++)
            if(layout.getEllipsisCount(j) > 0)
                return true;

        return false;
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mTitleView = null;
        mSubtitleView = null;
        if(mTitleLayout != null && mTitleLayout.getParent() == mUpGoerFive)
            mUpGoerFive.removeView(mTitleLayout);
        mTitleLayout = null;
        if((mDisplayOptions & 8) != 0)
            initTitle();
        if(mHomeDescriptionRes != 0)
            setNavigationContentDescription(mHomeDescriptionRes);
        if(mTabScrollView != null && mIncludeTabs)
        {
            configuration = mTabScrollView.getLayoutParams();
            if(configuration != null)
            {
                configuration.width = -2;
                configuration.height = -1;
            }
            mTabScrollView.setAllowCollapse(true);
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeCallbacks(mTabSelector);
        if(mActionMenuPresenter != null)
        {
            mActionMenuPresenter.hideOverflowMenu();
            mActionMenuPresenter.hideSubMenus();
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mUpGoerFive.addView(mHomeLayout, 0);
        addView(mUpGoerFive);
        if(mCustomNavView != null && (mDisplayOptions & 0x10) != 0)
        {
            android.view.ViewParent viewparent = mCustomNavView.getParent();
            if(viewparent != this)
            {
                if(viewparent instanceof ViewGroup)
                    ((ViewGroup)viewparent).removeView(mCustomNavView);
                addView(mCustomNavView);
            }
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int k1;
        Object obj;
        int l1;
        Object obj1;
        int j2;
        i1 = l - j - getPaddingTop() - getPaddingBottom();
        if(i1 <= 0)
            return;
        flag = isLayoutRtl();
        int j1;
        int i2;
        if(flag)
            j1 = 1;
        else
            j1 = -1;
        if(flag)
            j = getPaddingLeft();
        else
            j = k - i - getPaddingRight();
        if(flag)
            l = k - i - getPaddingRight();
        else
            l = getPaddingLeft();
        k1 = getPaddingTop();
        if(mExpandedActionView != null)
            obj = mExpandedHomeLayout;
        else
            obj = mHomeLayout;
        if(mTitleLayout != null && mTitleLayout.getVisibility() != 8)
        {
            if((mDisplayOptions & 8) != 0)
                k = 1;
            else
                k = 0;
        } else
        {
            k = 0;
        }
        l1 = 0;
        i = l1;
        if(((HomeView) (obj)).getParent() == mUpGoerFive)
            if(((HomeView) (obj)).getVisibility() != 8)
            {
                i = ((HomeView) (obj)).getStartOffset();
            } else
            {
                i = l1;
                if(k != 0)
                    i = ((HomeView) (obj)).getUpWidth();
            }
        l = next(l + positionChild(mUpGoerFive, next(l, i, flag), k1, i1, flag), i, flag);
        i = l;
        if(mExpandedActionView != null) goto _L2; else goto _L1
_L1:
        i = l;
        mNavigationMode;
        JVM INSTR tableswitch 0 2: default 216
    //                   0 219
    //                   1 754
    //                   2 811;
           goto _L3 _L4 _L5 _L6
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        i = l;
_L2:
        k = j;
        if(mMenuView != null)
        {
            k = j;
            if(mMenuView.getParent() == this)
            {
                positionChild(mMenuView, j, k1, i1, flag ^ true);
                k = j + mMenuView.getMeasuredWidth() * j1;
            }
        }
        j = k;
        if(mIndeterminateProgressView != null)
        {
            j = k;
            if(mIndeterminateProgressView.getVisibility() != 8)
            {
                positionChild(mIndeterminateProgressView, k, k1, i1, flag ^ true);
                j = k + mIndeterminateProgressView.getMeasuredWidth() * j1;
            }
        }
        obj1 = null;
        if(mExpandedActionView != null)
        {
            obj = mExpandedActionView;
        } else
        {
            obj = obj1;
            if((mDisplayOptions & 0x10) != 0)
            {
                obj = obj1;
                if(mCustomNavView != null)
                    obj = mCustomNavView;
            }
        }
        if(obj == null) goto _L8; else goto _L7
_L7:
        i2 = getLayoutDirection();
        obj1 = ((View) (obj)).getLayoutParams();
        if(obj1 instanceof android.app.ActionBar.LayoutParams)
            obj1 = (android.app.ActionBar.LayoutParams)obj1;
        else
            obj1 = null;
        if(obj1 != null)
            l1 = ((android.app.ActionBar.LayoutParams) (obj1)).gravity;
        else
            l1 = 0x800013;
        j2 = ((View) (obj)).getMeasuredWidth();
        i1 = 0;
        k1 = 0;
        k = i;
        l = j;
        if(obj1 != null)
        {
            k = next(i, ((android.app.ActionBar.LayoutParams) (obj1)).getMarginStart(), flag);
            l = j + ((android.app.ActionBar.LayoutParams) (obj1)).getMarginEnd() * j1;
            i1 = ((android.app.ActionBar.LayoutParams) (obj1)).topMargin;
            k1 = ((android.app.ActionBar.LayoutParams) (obj1)).bottomMargin;
        }
        j = l1 & 0x800007;
        if(j == 1)
        {
            i = (mRight - mLeft - j2) / 2;
            if(flag)
            {
                if(i + j2 > k)
                    j = 5;
                else
                if(i < l)
                    j = 3;
            } else
            if(i < k)
                j = 3;
            else
            if(i + j2 > l)
                j = 5;
        } else
        if(l1 == 0)
            j = 0x800003;
        j1 = 0;
        i = j1;
        Gravity.getAbsoluteGravity(j, i2);
        JVM INSTR tableswitch 1 5: default 544
    //                   1 963
    //                   2 547
    //                   3 981
    //                   4 547
    //                   5 997;
           goto _L9 _L10 _L11 _L12 _L11 _L13
_L11:
        break; /* Loop/switch isn't completed */
_L9:
        i = j1;
_L18:
        j = l1 & 0x70;
        if(l1 == 0)
            j = 16;
        l = 0;
        j;
        JVM INSTR lookupswitch 3: default 600
    //                   16: 1019
    //                   48: 1052
    //                   80: 1063;
           goto _L14 _L15 _L16 _L17
_L17:
        break MISSING_BLOCK_LABEL_1063;
_L14:
        j = l;
_L19:
        l = ((View) (obj)).getMeasuredWidth();
        ((View) (obj)).layout(i, j, i + l, ((View) (obj)).getMeasuredHeight() + j);
        next(k, l, flag);
_L8:
        if(mProgressView != null)
        {
            mProgressView.bringToFront();
            i = mProgressView.getMeasuredHeight() / 2;
            mProgressView.layout(mProgressBarPadding, -i, mProgressBarPadding + mProgressView.getMeasuredWidth(), i);
        }
        return;
_L5:
        i = l;
        if(mListNavLayout != null)
        {
            i = l;
            if(k != 0)
                i = next(l, mItemPadding, flag);
            i = next(i + positionChild(mListNavLayout, i, k1, i1, flag), mItemPadding, flag);
        }
          goto _L2
_L6:
        i = l;
        if(mTabScrollView != null)
        {
            i = l;
            if(k != 0)
                i = next(l, mItemPadding, flag);
            i = next(i + positionChild(mTabScrollView, i, k1, i1, flag), mItemPadding, flag);
        }
          goto _L2
_L10:
        i = (mRight - mLeft - j2) / 2;
          goto _L18
_L12:
        if(flag)
            i = l;
        else
            i = k;
          goto _L18
_L13:
        if(flag)
            i = k - j2;
        else
            i = l - j2;
          goto _L18
_L15:
        j = getPaddingTop();
        j = (mBottom - mTop - getPaddingBottom() - j - ((View) (obj)).getMeasuredHeight()) / 2;
          goto _L19
_L16:
        j = getPaddingTop() + i1;
          goto _L19
        j = getHeight() - getPaddingBottom() - ((View) (obj)).getMeasuredHeight() - k1;
          goto _L19
    }

    protected void onMeasure(int i, int j)
    {
        int i1;
        int l1;
        Object obj;
        int k2;
        int i3;
        int j3;
        int k3;
        int i4;
        Object obj1;
        int k;
label0:
        {
            k = getChildCount();
            if(!mIsCollapsible)
                break label0;
            int l = 0;
            for(int j1 = 0; j1 < k;)
            {
                int i2;
label1:
                {
                    View view = getChildAt(j1);
                    i2 = l;
                    if(view.getVisibility() == 8)
                        break label1;
                    if(view == mMenuView)
                    {
                        i2 = l;
                        if(mMenuView.getChildCount() == 0)
                            break label1;
                    }
                    i2 = l;
                    if(view != mUpGoerFive)
                        i2 = l + 1;
                }
                j1++;
                l = i2;
            }

            int l2 = mUpGoerFive.getChildCount();
            int j2 = 0;
            int k1;
            for(k1 = l; j2 < l2; k1 = l)
            {
                l = k1;
                if(mUpGoerFive.getChildAt(j2).getVisibility() != 8)
                    l = k1 + 1;
                j2++;
            }

            if(k1 == 0)
            {
                setMeasuredDimension(0, 0);
                return;
            }
        }
        if(android.view.View.MeasureSpec.getMode(i) != 0x40000000)
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used ").append("with android:layout_width=\"match_parent\" (or fill_parent)").toString());
        if(android.view.View.MeasureSpec.getMode(j) != 0x80000000)
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used ").append("with android:layout_height=\"wrap_content\"").toString());
        j3 = android.view.View.MeasureSpec.getSize(i);
        int l3;
        int j4;
        int k4;
        int l4;
        int i5;
        if(mContentHeight >= 0)
            k3 = mContentHeight;
        else
            k3 = android.view.View.MeasureSpec.getSize(j);
        l3 = getPaddingTop() + getPaddingBottom();
        j = getPaddingLeft();
        i = getPaddingRight();
        i4 = k3 - l3;
        j4 = android.view.View.MeasureSpec.makeMeasureSpec(i4, 0x80000000);
        k4 = android.view.View.MeasureSpec.makeMeasureSpec(i4, 0x40000000);
        i3 = j3 - j - i;
        i1 = i3 / 2;
        l1 = i1;
        if(mTitleLayout != null && mTitleLayout.getVisibility() != 8)
        {
            if((mDisplayOptions & 8) != 0)
                k2 = 1;
            else
                k2 = 0;
        } else
        {
            k2 = 0;
        }
        if(mExpandedActionView != null)
            obj = mExpandedHomeLayout;
        else
            obj = mHomeLayout;
        obj1 = ((HomeView) (obj)).getLayoutParams();
        if(((android.view.ViewGroup.LayoutParams) (obj1)).width < 0)
            i = android.view.View.MeasureSpec.makeMeasureSpec(i3, 0x80000000);
        else
            i = android.view.View.MeasureSpec.makeMeasureSpec(((android.view.ViewGroup.LayoutParams) (obj1)).width, 0x40000000);
        ((HomeView) (obj)).measure(i, k4);
        l4 = 0;
        if(((HomeView) (obj)).getVisibility() == 8 || ((HomeView) (obj)).getParent() != mUpGoerFive) goto _L2; else goto _L1
_L1:
        l4 = ((HomeView) (obj)).getMeasuredWidth();
        i = l4 + ((HomeView) (obj)).getStartOffset();
        j = Math.max(0, i3 - i);
        i1 = Math.max(0, j - i);
_L10:
        i3 = j;
        i = l1;
        if(mMenuView != null)
        {
            i3 = j;
            i = l1;
            if(mMenuView.getParent() == this)
            {
                i3 = measureChildView(mMenuView, j, k4, 0);
                i = Math.max(0, l1 - mMenuView.getMeasuredWidth());
            }
        }
        l1 = i3;
        k4 = i;
        if(mIndeterminateProgressView != null)
        {
            l1 = i3;
            k4 = i;
            if(mIndeterminateProgressView.getVisibility() != 8)
            {
                l1 = measureChildView(mIndeterminateProgressView, i3, j4, 0);
                k4 = Math.max(0, i - mIndeterminateProgressView.getMeasuredWidth());
            }
        }
        i = l1;
        j = i1;
        if(mExpandedActionView != null) goto _L4; else goto _L3
_L3:
        mNavigationMode;
        JVM INSTR tableswitch 1 2: default 652
    //                   1 1069
    //                   2 1174;
           goto _L5 _L6 _L7
_L5:
        j = i1;
        i = l1;
_L4:
        obj1 = null;
        if(mExpandedActionView != null)
        {
            obj = mExpandedActionView;
        } else
        {
            obj = obj1;
            if((mDisplayOptions & 0x10) != 0)
            {
                obj = obj1;
                if(mCustomNavView != null)
                    obj = mCustomNavView;
            }
        }
        i1 = i;
        if(obj != null)
        {
            android.view.ViewGroup.LayoutParams layoutparams = generateLayoutParams(((View) (obj)).getLayoutParams());
            if(layoutparams instanceof android.app.ActionBar.LayoutParams)
                obj1 = (android.app.ActionBar.LayoutParams)layoutparams;
            else
                obj1 = null;
            k2 = 0;
            l1 = 0;
            if(obj1 != null)
            {
                k2 = ((android.app.ActionBar.LayoutParams) (obj1)).leftMargin + ((android.app.ActionBar.LayoutParams) (obj1)).rightMargin;
                l1 = ((android.app.ActionBar.LayoutParams) (obj1)).topMargin + ((android.app.ActionBar.LayoutParams) (obj1)).bottomMargin;
            }
            if(mContentHeight <= 0)
                i1 = 0x80000000;
            else
            if(layoutparams.height != -2)
                i1 = 0x40000000;
            else
                i1 = 0x80000000;
            i3 = i4;
            if(layoutparams.height >= 0)
                i3 = Math.min(layoutparams.height, i4);
            i5 = Math.max(0, i3 - l1);
            if(layoutparams.width != -2)
                l1 = 0x40000000;
            else
                l1 = 0x80000000;
            if(layoutparams.width >= 0)
                i3 = Math.min(layoutparams.width, i);
            else
                i3 = i;
            j4 = Math.max(0, i3 - k2);
            if(obj1 != null)
                i4 = ((android.app.ActionBar.LayoutParams) (obj1)).gravity;
            else
                i4 = 0x800013;
            i3 = j4;
            if((i4 & 7) == 1)
            {
                i3 = j4;
                if(layoutparams.width == -1)
                    i3 = Math.min(j, k4) * 2;
            }
            ((View) (obj)).measure(android.view.View.MeasureSpec.makeMeasureSpec(i3, l1), android.view.View.MeasureSpec.makeMeasureSpec(i5, i1));
            i1 = i - (((View) (obj)).getMeasuredWidth() + k2);
        }
        measureChildView(mUpGoerFive, i1 + l4, android.view.View.MeasureSpec.makeMeasureSpec(mContentHeight, 0x40000000), 0);
        if(mTitleLayout != null)
            Math.max(0, j - mTitleLayout.getMeasuredWidth());
        if(mContentHeight > 0) goto _L9; else goto _L8
_L2:
        j = i3;
        if(k2 == 0) goto _L10; else goto _L1
_L6:
        i = l1;
        j = i1;
        if(mListNavLayout != null)
        {
            if(k2 != 0)
                i = mItemPadding * 2;
            else
                i = mItemPadding;
            j = Math.max(0, l1 - i);
            k2 = Math.max(0, i1 - i);
            mListNavLayout.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(i4, 0x40000000));
            i1 = mListNavLayout.getMeasuredWidth();
            i = Math.max(0, j - i1);
            j = Math.max(0, k2 - i1);
        }
          goto _L4
_L7:
        i = l1;
        j = i1;
        if(mTabScrollView != null)
        {
            if(k2 != 0)
                i = mItemPadding * 2;
            else
                i = mItemPadding;
            j = Math.max(0, l1 - i);
            k2 = Math.max(0, i1 - i);
            mTabScrollView.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(i4, 0x40000000));
            i1 = mTabScrollView.getMeasuredWidth();
            i = Math.max(0, j - i1);
            j = Math.max(0, k2 - i1);
        }
          goto _L4
_L8:
        j = 0;
        for(i = 0; i < k;)
        {
            k2 = getChildAt(i).getMeasuredHeight() + l3;
            i1 = j;
            if(k2 > j)
                i1 = k2;
            i++;
            j = i1;
        }

        setMeasuredDimension(j3, j);
_L12:
        if(mContextView != null)
            mContextView.setContentHeight(getMeasuredHeight());
        if(mProgressView != null && mProgressView.getVisibility() != 8)
            mProgressView.measure(android.view.View.MeasureSpec.makeMeasureSpec(j3 - mProgressBarPadding * 2, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x80000000));
        return;
_L9:
        setMeasuredDimension(j3, k3);
        if(true) goto _L12; else goto _L11
_L11:
          goto _L4
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if(savedstate.expandedMenuItemId != 0 && mExpandedMenuPresenter != null && mOptionsMenu != null)
        {
            parcelable = mOptionsMenu.findItem(savedstate.expandedMenuItemId);
            if(parcelable != null)
                parcelable.expandActionView();
        }
        if(savedstate.isOverflowOpen)
            postShowOverflowMenu();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if(mExpandedMenuPresenter != null && mExpandedMenuPresenter.mCurrentExpandedItem != null)
            savedstate.expandedMenuItemId = mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        savedstate.isOverflowOpen = isOverflowMenuShowing();
        return savedstate;
    }

    public void setCollapsible(boolean flag)
    {
        mIsCollapsible = flag;
    }

    public void setContextView(ActionBarContextView actionbarcontextview)
    {
        mContextView = actionbarcontextview;
    }

    public void setCustomView(View view)
    {
        boolean flag;
        if((mDisplayOptions & 0x10) != 0)
            flag = true;
        else
            flag = false;
        if(mCustomNavView != null && flag)
            removeView(mCustomNavView);
        mCustomNavView = view;
        if(mCustomNavView != null && flag)
            addView(mCustomNavView);
    }

    public void setDefaultNavigationContentDescription(int i)
    {
        if(mDefaultUpDescription == i)
        {
            return;
        } else
        {
            mDefaultUpDescription = i;
            updateHomeAccessibility(mUpGoerFive.isEnabled());
            return;
        }
    }

    public void setDefaultNavigationIcon(Drawable drawable)
    {
        mHomeLayout.setDefaultUpIndicator(drawable);
    }

    public void setDisplayOptions(int i)
    {
        int j;
        if(mDisplayOptions == -1)
            j = -1;
        else
            j = i ^ mDisplayOptions;
        mDisplayOptions = i;
        if((j & 0x3f) != 0)
        {
            boolean flag;
            int k;
            if((j & 4) != 0)
            {
                HomeView homeview;
                if((i & 4) != 0)
                    flag = true;
                else
                    flag = false;
                mHomeLayout.setShowUp(flag);
                if(flag)
                    setHomeButtonEnabled(true);
            }
            if((j & 1) != 0)
            {
                Drawable drawable;
                if(mLogo != null && (i & 1) != 0)
                    k = 1;
                else
                    k = 0;
                homeview = mHomeLayout;
                if(k != 0)
                    drawable = mLogo;
                else
                    drawable = mIcon;
                homeview.setIcon(drawable);
            }
            if((j & 8) != 0)
                if((i & 8) != 0)
                    initTitle();
                else
                    mUpGoerFive.removeView(mTitleLayout);
            if((i & 2) != 0)
                flag = true;
            else
                flag = false;
            if((mDisplayOptions & 4) != 0)
                k = 1;
            else
                k = 0;
            if(flag)
                k = 0;
            mHomeLayout.setShowIcon(flag);
            if((flag || k != 0) && mExpandedActionView == null)
                k = 0;
            else
                k = 8;
            mHomeLayout.setVisibility(k);
            if((j & 0x10) != 0 && mCustomNavView != null)
                if((i & 0x10) != 0)
                    addView(mCustomNavView);
                else
                    removeView(mCustomNavView);
            if(mTitleLayout != null && (j & 0x20) != 0)
                if((i & 0x20) != 0)
                {
                    mTitleView.setSingleLine(false);
                    mTitleView.setMaxLines(2);
                } else
                {
                    mTitleView.setMaxLines(1);
                    mTitleView.setSingleLine(true);
                }
            requestLayout();
        } else
        {
            invalidate();
        }
        updateHomeAccessibility(mUpGoerFive.isEnabled());
    }

    public void setDropdownParams(SpinnerAdapter spinneradapter, android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener)
    {
        mSpinnerAdapter = spinneradapter;
        mNavItemSelectedListener = onitemselectedlistener;
        if(mSpinner != null)
        {
            mSpinner.setAdapter(spinneradapter);
            mSpinner.setOnItemSelectedListener(onitemselectedlistener);
        }
    }

    public void setDropdownSelectedPosition(int i)
    {
        mSpinner.setSelection(i);
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingtabcontainerview)
    {
        if(mTabScrollView != null)
            removeView(mTabScrollView);
        mTabScrollView = scrollingtabcontainerview;
        boolean flag;
        if(scrollingtabcontainerview != null)
            flag = true;
        else
            flag = false;
        mIncludeTabs = flag;
        if(mIncludeTabs && mNavigationMode == 2)
        {
            addView(mTabScrollView);
            android.view.ViewGroup.LayoutParams layoutparams = mTabScrollView.getLayoutParams();
            layoutparams.width = -2;
            layoutparams.height = -1;
            scrollingtabcontainerview.setAllowCollapse(true);
        }
    }

    public void setHomeButtonEnabled(boolean flag)
    {
        setHomeButtonEnabled(flag, true);
    }

    public void setIcon(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = mContext.getDrawable(i);
        else
            drawable = null;
        setIcon(drawable);
    }

    public void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        if(drawable != null && ((mDisplayOptions & 1) == 0 || mLogo == null))
            mHomeLayout.setIcon(drawable);
        if(mExpandedActionView != null)
            mExpandedHomeLayout.setIcon(mIcon.getConstantState().newDrawable(getResources()));
    }

    public void setLogo(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = mContext.getDrawable(i);
        else
            drawable = null;
        setLogo(drawable);
    }

    public void setLogo(Drawable drawable)
    {
        mLogo = drawable;
        if(drawable != null && (mDisplayOptions & 1) != 0)
            mHomeLayout.setIcon(drawable);
    }

    public void setMenu(Menu menu, com.android.internal.view.menu.MenuPresenter.Callback callback)
    {
        if(menu == mOptionsMenu)
            return;
        if(mOptionsMenu != null)
        {
            mOptionsMenu.removeMenuPresenter(mActionMenuPresenter);
            mOptionsMenu.removeMenuPresenter(mExpandedMenuPresenter);
        }
        menu = (MenuBuilder)menu;
        mOptionsMenu = menu;
        if(mMenuView != null)
        {
            ViewGroup viewgroup = (ViewGroup)mMenuView.getParent();
            if(viewgroup != null)
                viewgroup.removeView(mMenuView);
        }
        if(mActionMenuPresenter == null)
        {
            mActionMenuPresenter = new ActionMenuPresenter(mContext);
            mActionMenuPresenter.setCallback(callback);
            mActionMenuPresenter.setId(0x1020185);
            mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
        }
        callback = new android.view.ViewGroup.LayoutParams(-2, -1);
        if(!mSplitActionBar)
        {
            mActionMenuPresenter.setExpandedActionViewsExclusive(getResources().getBoolean(0x1120002));
            configPresenters(menu);
            menu = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
            ViewGroup viewgroup1 = (ViewGroup)menu.getParent();
            if(viewgroup1 != null && viewgroup1 != this)
                viewgroup1.removeView(menu);
            addView(menu, callback);
        } else
        {
            mActionMenuPresenter.setExpandedActionViewsExclusive(false);
            mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
            mActionMenuPresenter.setItemLimit(0x7fffffff);
            callback.width = -1;
            callback.height = -2;
            configPresenters(menu);
            menu = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
            if(mSplitView != null)
            {
                ViewGroup viewgroup2 = (ViewGroup)menu.getParent();
                if(viewgroup2 != null && viewgroup2 != mSplitView)
                    viewgroup2.removeView(menu);
                menu.setVisibility(getAnimatedVisibility());
                mSplitView.addView(menu, callback);
            } else
            {
                menu.setLayoutParams(callback);
            }
        }
        mMenuView = menu;
    }

    public void setMenuCallbacks(com.android.internal.view.menu.MenuPresenter.Callback callback, com.android.internal.view.menu.MenuBuilder.Callback callback1)
    {
        if(mActionMenuPresenter != null)
            mActionMenuPresenter.setCallback(callback);
        if(mOptionsMenu != null)
            mOptionsMenu.setCallback(callback1);
    }

    public void setMenuPrepared()
    {
        mMenuPrepared = true;
    }

    public void setNavigationContentDescription(int i)
    {
        mHomeDescriptionRes = i;
        CharSequence charsequence;
        if(i != 0)
            charsequence = getResources().getText(i);
        else
            charsequence = null;
        mHomeDescription = charsequence;
        updateHomeAccessibility(mUpGoerFive.isEnabled());
    }

    public void setNavigationContentDescription(CharSequence charsequence)
    {
        mHomeDescription = charsequence;
        updateHomeAccessibility(mUpGoerFive.isEnabled());
    }

    public void setNavigationIcon(int i)
    {
        mHomeLayout.setUpIndicator(i);
    }

    public void setNavigationIcon(Drawable drawable)
    {
        mHomeLayout.setUpIndicator(drawable);
    }

    public void setNavigationMode(int i)
    {
        int j = mNavigationMode;
        if(i == j) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 66
    //                   2 84;
           goto _L3 _L4 _L5
_L3:
        i;
        JVM INSTR tableswitch 1 2: default 56
    //                   1 109
    //                   2 240;
           goto _L6 _L7 _L8
_L6:
        mNavigationMode = i;
        requestLayout();
_L2:
        return;
_L4:
        if(mListNavLayout != null)
            removeView(mListNavLayout);
          goto _L3
_L5:
        if(mTabScrollView != null && mIncludeTabs)
            removeView(mTabScrollView);
          goto _L3
_L7:
        if(mSpinner == null)
        {
            mSpinner = new Spinner(mContext, null, 0x10102d7);
            mSpinner.setId(0x102017f);
            mListNavLayout = new LinearLayout(mContext, null, 0x10102f4);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -1);
            layoutparams.gravity = 17;
            mListNavLayout.addView(mSpinner, layoutparams);
        }
        if(mSpinner.getAdapter() != mSpinnerAdapter)
            mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(mNavItemSelectedListener);
        addView(mListNavLayout);
          goto _L6
_L8:
        if(mTabScrollView != null && mIncludeTabs)
            addView(mTabScrollView);
          goto _L6
    }

    public void setSplitToolbar(boolean flag)
    {
        if(mSplitActionBar != flag)
        {
            if(mMenuView != null)
            {
                ViewGroup viewgroup = (ViewGroup)mMenuView.getParent();
                if(viewgroup != null)
                    viewgroup.removeView(mMenuView);
                if(flag)
                {
                    if(mSplitView != null)
                        mSplitView.addView(mMenuView);
                    mMenuView.getLayoutParams().width = -1;
                } else
                {
                    addView(mMenuView);
                    mMenuView.getLayoutParams().width = -2;
                }
                mMenuView.requestLayout();
            }
            if(mSplitView != null)
            {
                viewgroup = mSplitView;
                int i;
                if(flag)
                    i = 0;
                else
                    i = 8;
                viewgroup.setVisibility(i);
            }
            if(mActionMenuPresenter != null)
                if(!flag)
                {
                    mActionMenuPresenter.setExpandedActionViewsExclusive(getResources().getBoolean(0x1120002));
                } else
                {
                    mActionMenuPresenter.setExpandedActionViewsExclusive(false);
                    mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    mActionMenuPresenter.setItemLimit(0x7fffffff);
                }
            super.setSplitToolbar(flag);
        }
    }

    public void setSubtitle(CharSequence charsequence)
    {
        boolean flag = false;
        mSubtitle = charsequence;
        if(mSubtitleView != null)
        {
            mSubtitleView.setText(charsequence);
            TextView textview = mSubtitleView;
            int i;
            if(charsequence != null)
                i = 0;
            else
                i = 8;
            textview.setVisibility(i);
            if(mExpandedActionView == null && (mDisplayOptions & 8) != 0)
            {
                if(TextUtils.isEmpty(mTitle))
                    i = TextUtils.isEmpty(mSubtitle) ^ true;
                else
                    i = 1;
            } else
            {
                i = 0;
            }
            charsequence = mTitleLayout;
            if(i != 0)
                i = ((flag) ? 1 : 0);
            else
                i = 8;
            charsequence.setVisibility(i);
        }
        updateHomeAccessibility(mUpGoerFive.isEnabled());
    }

    public void setTitle(CharSequence charsequence)
    {
        mUserTitle = true;
        setTitleImpl(charsequence);
    }

    public void setWindowCallback(android.view.Window.Callback callback)
    {
        mWindowCallback = callback;
    }

    public void setWindowTitle(CharSequence charsequence)
    {
        if(!mUserTitle)
            setTitleImpl(charsequence);
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    private static final int DEFAULT_CUSTOM_GRAVITY = 0x800013;
    public static final int DISPLAY_DEFAULT = 0;
    private static final int DISPLAY_RELAYOUT_MASK = 63;
    private static final String TAG = "ActionBarView";
    private ActionBarContextView mContextView;
    private View mCustomNavView;
    private int mDefaultUpDescription;
    private int mDisplayOptions;
    View mExpandedActionView;
    private final android.view.View.OnClickListener mExpandedActionViewUpListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            view = ActionBarView._2D_get3(ActionBarView.this).mCurrentExpandedItem;
            if(view != null)
                view.collapseActionView();
        }

        final ActionBarView this$0;

            
            {
                this$0 = ActionBarView.this;
                super();
            }
    }
;
    private HomeView mExpandedHomeLayout;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private CharSequence mHomeDescription;
    private int mHomeDescriptionRes;
    private HomeView mHomeLayout;
    private Drawable mIcon;
    private boolean mIncludeTabs;
    private final int mIndeterminateProgressStyle;
    private ProgressBar mIndeterminateProgressView;
    private boolean mIsCollapsible;
    private int mItemPadding;
    private LinearLayout mListNavLayout;
    private Drawable mLogo;
    private ActionMenuItem mLogoNavItem;
    private boolean mMenuPrepared;
    private android.widget.AdapterView.OnItemSelectedListener mNavItemSelectedListener;
    private int mNavigationMode;
    private MenuBuilder mOptionsMenu;
    private int mProgressBarPadding;
    private final int mProgressStyle;
    private ProgressBar mProgressView;
    private Spinner mSpinner;
    private SpinnerAdapter mSpinnerAdapter;
    private CharSequence mSubtitle;
    private final int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private ScrollingTabContainerView mTabScrollView;
    private Runnable mTabSelector;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private final int mTitleStyleRes;
    private TextView mTitleView;
    private final android.view.View.OnClickListener mUpClickListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            if(ActionBarView._2D_get7(ActionBarView.this))
                mWindowCallback.onMenuItemSelected(0, ActionBarView._2D_get6(ActionBarView.this));
        }

        final ActionBarView this$0;

            
            {
                this$0 = ActionBarView.this;
                super();
            }
    }
;
    private ViewGroup mUpGoerFive;
    private boolean mUserTitle;
    private boolean mWasHomeEnabled;
    android.view.Window.Callback mWindowCallback;
}
