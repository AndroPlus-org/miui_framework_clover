// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.*;
import com.android.internal.view.menu.*;
import com.android.internal.widget.DecorToolbar;
import com.android.internal.widget.ToolbarWidgetWrapper;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.widget:
//            RtlSpacingHelper, ImageButton, ImageView, ActionMenuView, 
//            TextView, ActionMenuPresenter

public class Toolbar extends ViewGroup
{
    private class ExpandedActionViewMenuPresenter
        implements MenuPresenter
    {

        public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
        {
            if(mExpandedActionView instanceof CollapsibleActionView)
                ((CollapsibleActionView)mExpandedActionView).onActionViewCollapsed();
            removeView(mExpandedActionView);
            removeView(Toolbar._2D_get1(Toolbar.this));
            mExpandedActionView = null;
            addChildrenForExpandedActionView();
            mCurrentExpandedItem = null;
            requestLayout();
            menuitemimpl.setActionViewExpanded(false);
            return true;
        }

        public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
        {
            Toolbar._2D_wrap0(Toolbar.this);
            if(Toolbar._2D_get1(Toolbar.this).getParent() != Toolbar.this)
                addView(Toolbar._2D_get1(Toolbar.this));
            mExpandedActionView = menuitemimpl.getActionView();
            mCurrentExpandedItem = menuitemimpl;
            if(mExpandedActionView.getParent() != Toolbar.this)
            {
                menubuilder = generateDefaultLayoutParams();
                menubuilder.gravity = Toolbar._2D_get0(Toolbar.this) & 0x70 | 0x800003;
                menubuilder.mViewType = 2;
                mExpandedActionView.setLayoutParams(menubuilder);
                addView(mExpandedActionView);
            }
            removeChildrenForExpandedActionView();
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
        final Toolbar this$0;

        private ExpandedActionViewMenuPresenter()
        {
            this$0 = Toolbar.this;
            super();
        }

        ExpandedActionViewMenuPresenter(ExpandedActionViewMenuPresenter expandedactionviewmenupresenter)
        {
            this();
        }
    }

    public static class LayoutParams extends android.app.ActionBar.LayoutParams
    {

        static final int CUSTOM = 0;
        static final int EXPANDED = 2;
        static final int SYSTEM = 1;
        int mViewType;

        public LayoutParams(int i)
        {
            this(-2, -1, i);
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mViewType = 0;
            gravity = 0x800013;
        }

        public LayoutParams(int i, int j, int k)
        {
            super(i, j);
            mViewType = 0;
            gravity = k;
        }

        public LayoutParams(android.app.ActionBar.LayoutParams layoutparams)
        {
            super(layoutparams);
            mViewType = 0;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mViewType = 0;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mViewType = 0;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mViewType = 0;
            copyMarginsFrom(marginlayoutparams);
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mViewType = 0;
            mViewType = layoutparams.mViewType;
        }
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
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
                return new SavedState(parcel);
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
        public int expandedMenuItemId;
        public boolean isOverflowOpen;


        public SavedState(Parcel parcel)
        {
            boolean flag = false;
            super(parcel);
            expandedMenuItemId = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            isOverflowOpen = flag;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    static int _2D_get0(Toolbar toolbar)
    {
        return toolbar.mButtonGravity;
    }

    static ImageButton _2D_get1(Toolbar toolbar)
    {
        return toolbar.mCollapseButtonView;
    }

    static OnMenuItemClickListener _2D_get2(Toolbar toolbar)
    {
        return toolbar.mOnMenuItemClickListener;
    }

    static void _2D_wrap0(Toolbar toolbar)
    {
        toolbar.ensureCollapseButtonView();
    }

    public Toolbar(Context context)
    {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x10104aa);
    }

    public Toolbar(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Toolbar(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mGravity = 0x800013;
        mTempViews = new ArrayList();
        mHiddenViews = new ArrayList();
        mTempMargins = new int[2];
        mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem menuitem)
            {
                if(Toolbar._2D_get2(Toolbar.this) != null)
                    return Toolbar._2D_get2(Toolbar.this).onMenuItemClick(menuitem);
                else
                    return false;
            }

            final Toolbar this$0;

            
            {
                this$0 = Toolbar.this;
                super();
            }
        }
;
        mShowOverflowMenuRunnable = new Runnable() {

            public void run()
            {
                showOverflowMenu();
            }

            final Toolbar this$0;

            
            {
                this$0 = Toolbar.this;
                super();
            }
        }
;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Toolbar, i, j);
        mTitleTextAppearance = context.getResourceId(4, 0);
        mSubtitleTextAppearance = context.getResourceId(5, 0);
        mNavButtonStyle = context.getResourceId(27, 0);
        mGravity = context.getInteger(0, mGravity);
        mButtonGravity = context.getInteger(23, 48);
        i = context.getDimensionPixelOffset(17, 0);
        mTitleMarginBottom = i;
        mTitleMarginTop = i;
        mTitleMarginEnd = i;
        mTitleMarginStart = i;
        i = context.getDimensionPixelOffset(18, -1);
        if(i >= 0)
            mTitleMarginStart = i;
        i = context.getDimensionPixelOffset(19, -1);
        if(i >= 0)
            mTitleMarginEnd = i;
        i = context.getDimensionPixelOffset(20, -1);
        if(i >= 0)
            mTitleMarginTop = i;
        i = context.getDimensionPixelOffset(21, -1);
        if(i >= 0)
            mTitleMarginBottom = i;
        mMaxButtonHeight = context.getDimensionPixelSize(22, -1);
        i = context.getDimensionPixelOffset(6, 0x80000000);
        j = context.getDimensionPixelOffset(7, 0x80000000);
        int k = context.getDimensionPixelSize(8, 0);
        int l = context.getDimensionPixelSize(9, 0);
        ensureContentInsets();
        mContentInsets.setAbsolute(k, l);
        if(i != 0x80000000 || j != 0x80000000)
            mContentInsets.setRelative(i, j);
        mContentInsetStartWithNavigation = context.getDimensionPixelOffset(25, 0x80000000);
        mContentInsetEndWithActions = context.getDimensionPixelOffset(26, 0x80000000);
        mCollapseIcon = context.getDrawable(24);
        mCollapseDescription = context.getText(13);
        attributeset = context.getText(1);
        if(!TextUtils.isEmpty(attributeset))
            setTitle(attributeset);
        attributeset = context.getText(3);
        if(!TextUtils.isEmpty(attributeset))
            setSubtitle(attributeset);
        mPopupContext = mContext;
        setPopupTheme(context.getResourceId(10, 0));
        attributeset = context.getDrawable(11);
        if(attributeset != null)
            setNavigationIcon(attributeset);
        attributeset = context.getText(12);
        if(!TextUtils.isEmpty(attributeset))
            setNavigationContentDescription(attributeset);
        attributeset = context.getDrawable(2);
        if(attributeset != null)
            setLogo(attributeset);
        attributeset = context.getText(16);
        if(!TextUtils.isEmpty(attributeset))
            setLogoDescription(attributeset);
        if(context.hasValue(14))
            setTitleTextColor(context.getColor(14, -1));
        if(context.hasValue(15))
            setSubtitleTextColor(context.getColor(15, -1));
        context.recycle();
    }

    private void addCustomViewsWithGravity(List list, int i)
    {
        boolean flag;
        int j;
        int k;
        if(getLayoutDirection() == 1)
            flag = true;
        else
            flag = false;
        j = getChildCount();
        k = Gravity.getAbsoluteGravity(i, getLayoutDirection());
        list.clear();
        if(flag)
            for(i = j - 1; i >= 0; i--)
            {
                View view = getChildAt(i);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if(layoutparams.mViewType == 0 && shouldLayout(view) && getChildHorizontalGravity(layoutparams.gravity) == k)
                    list.add(view);
            }

        else
            for(i = 0; i < j; i++)
            {
                View view1 = getChildAt(i);
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                if(layoutparams1.mViewType == 0 && shouldLayout(view1) && getChildHorizontalGravity(layoutparams1.gravity) == k)
                    list.add(view1);
            }

    }

    private void addSystemView(View view, boolean flag)
    {
        Object obj = view.getLayoutParams();
        if(obj == null)
            obj = generateDefaultLayoutParams();
        else
        if(!checkLayoutParams(((android.view.ViewGroup.LayoutParams) (obj))))
            obj = generateLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        else
            obj = (LayoutParams)obj;
        obj.mViewType = 1;
        if(flag && mExpandedActionView != null)
        {
            view.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            mHiddenViews.add(view);
        } else
        {
            addView(view, ((android.view.ViewGroup.LayoutParams) (obj)));
        }
    }

    private void ensureCollapseButtonView()
    {
        if(mCollapseButtonView == null)
        {
            mCollapseButtonView = new ImageButton(getContext(), null, 0, mNavButtonStyle);
            mCollapseButtonView.setImageDrawable(mCollapseIcon);
            mCollapseButtonView.setContentDescription(mCollapseDescription);
            LayoutParams layoutparams = generateDefaultLayoutParams();
            layoutparams.gravity = mButtonGravity & 0x70 | 0x800003;
            layoutparams.mViewType = 2;
            mCollapseButtonView.setLayoutParams(layoutparams);
            mCollapseButtonView.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    collapseActionView();
                }

                final Toolbar this$0;

            
            {
                this$0 = Toolbar.this;
                super();
            }
            }
);
        }
    }

    private void ensureContentInsets()
    {
        if(mContentInsets == null)
            mContentInsets = new RtlSpacingHelper();
    }

    private void ensureLogoView()
    {
        if(mLogoView == null)
            mLogoView = new ImageView(getContext());
    }

    private void ensureMenu()
    {
        ensureMenuView();
        if(mMenuView.peekMenu() == null)
        {
            MenuBuilder menubuilder = (MenuBuilder)mMenuView.getMenu();
            if(mExpandedMenuPresenter == null)
                mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
            mMenuView.setExpandedActionViewsExclusive(true);
            menubuilder.addMenuPresenter(mExpandedMenuPresenter, mPopupContext);
        }
    }

    private void ensureMenuView()
    {
        if(mMenuView == null)
        {
            mMenuView = new ActionMenuView(getContext());
            mMenuView.setPopupTheme(mPopupTheme);
            mMenuView.setOnMenuItemClickListener(mMenuViewItemClickListener);
            mMenuView.setMenuCallbacks(mActionMenuPresenterCallback, mMenuBuilderCallback);
            LayoutParams layoutparams = generateDefaultLayoutParams();
            layoutparams.gravity = mButtonGravity & 0x70 | 0x800005;
            mMenuView.setLayoutParams(layoutparams);
            addSystemView(mMenuView, false);
        }
    }

    private void ensureNavButtonView()
    {
        if(mNavButtonView == null)
        {
            mNavButtonView = new ImageButton(getContext(), null, 0, mNavButtonStyle);
            LayoutParams layoutparams = generateDefaultLayoutParams();
            layoutparams.gravity = mButtonGravity & 0x70 | 0x800003;
            mNavButtonView.setLayoutParams(layoutparams);
        }
    }

    private int getChildHorizontalGravity(int i)
    {
        int j = getLayoutDirection();
        i = Gravity.getAbsoluteGravity(i, j) & 7;
        switch(i)
        {
        case 2: // '\002'
        case 4: // '\004'
        default:
            if(j == 1)
                i = 5;
            else
                i = 3;
            return i;

        case 1: // '\001'
        case 3: // '\003'
        case 5: // '\005'
            return i;
        }
    }

    private int getChildTop(View view, int i)
    {
        LayoutParams layoutparams;
        int j;
        int k;
        int l;
        int i1;
        layoutparams = (LayoutParams)view.getLayoutParams();
        j = view.getMeasuredHeight();
        if(i > 0)
            i = (j - i) / 2;
        else
            i = 0;
        getChildVerticalGravity(layoutparams.gravity);
        JVM INSTR lookupswitch 3: default 68
    //                   16: 68
    //                   48: 123
    //                   80: 130;
           goto _L1 _L1 _L2 _L3
_L1:
        k = getPaddingTop();
        i = getPaddingBottom();
        l = getHeight();
        i1 = (l - k - i - j) / 2;
        if(i1 >= layoutparams.topMargin) goto _L5; else goto _L4
_L4:
        i = layoutparams.topMargin;
_L7:
        return k + i;
_L2:
        return getPaddingTop() - i;
_L3:
        return getHeight() - getPaddingBottom() - j - layoutparams.bottomMargin - i;
_L5:
        j = l - i - j - i1 - k;
        i = i1;
        if(j < layoutparams.bottomMargin)
            i = Math.max(0, i1 - (layoutparams.bottomMargin - j));
        if(true) goto _L7; else goto _L6
_L6:
    }

    private int getChildVerticalGravity(int i)
    {
        i &= 0x70;
        switch(i)
        {
        default:
            return mGravity & 0x70;

        case 16: // '\020'
        case 48: // '0'
        case 80: // 'P'
            return i;
        }
    }

    private int getHorizontalMargins(View view)
    {
        view = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        return view.getMarginStart() + view.getMarginEnd();
    }

    private MenuInflater getMenuInflater()
    {
        return new MenuInflater(getContext());
    }

    private int getVerticalMargins(View view)
    {
        view = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        return ((android.view.ViewGroup.MarginLayoutParams) (view)).topMargin + ((android.view.ViewGroup.MarginLayoutParams) (view)).bottomMargin;
    }

    private int getViewListMeasuredWidth(List list, int ai[])
    {
        int i = ai[0];
        int j = ai[1];
        int k = 0;
        int l = list.size();
        for(int i1 = 0; i1 < l; i1++)
        {
            View view = (View)list.get(i1);
            ai = (LayoutParams)view.getLayoutParams();
            i = ((LayoutParams) (ai)).leftMargin - i;
            j = ((LayoutParams) (ai)).rightMargin - j;
            int j1 = Math.max(0, i);
            int k1 = Math.max(0, j);
            i = Math.max(0, -i);
            j = Math.max(0, -j);
            k += view.getMeasuredWidth() + j1 + k1;
        }

        return k;
    }

    private boolean isChildOrHidden(View view)
    {
        boolean flag;
        if(view.getParent() != this)
            flag = mHiddenViews.contains(view);
        else
            flag = true;
        return flag;
    }

    private static boolean isCustomView(View view)
    {
        boolean flag;
        if(((LayoutParams)view.getLayoutParams()).mViewType == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private int layoutChildLeft(View view, int i, int ai[], int j)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int k = layoutparams.leftMargin - ai[0];
        i += Math.max(0, k);
        ai[0] = Math.max(0, -k);
        j = getChildTop(view, j);
        k = view.getMeasuredWidth();
        view.layout(i, j, i + k, view.getMeasuredHeight() + j);
        return i + (layoutparams.rightMargin + k);
    }

    private int layoutChildRight(View view, int i, int ai[], int j)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int k = layoutparams.rightMargin - ai[1];
        i -= Math.max(0, k);
        ai[1] = Math.max(0, -k);
        j = getChildTop(view, j);
        k = view.getMeasuredWidth();
        view.layout(i - k, j, i, view.getMeasuredHeight() + j);
        return i - (layoutparams.leftMargin + k);
    }

    private int measureChildCollapseMargins(View view, int i, int j, int k, int l, int ai[])
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int i1 = marginlayoutparams.leftMargin - ai[0];
        int j1 = marginlayoutparams.rightMargin - ai[1];
        int k1 = Math.max(0, i1) + Math.max(0, j1);
        ai[0] = Math.max(0, -i1);
        ai[1] = Math.max(0, -j1);
        view.measure(getChildMeasureSpec(i, mPaddingLeft + mPaddingRight + k1 + j, marginlayoutparams.width), getChildMeasureSpec(k, mPaddingTop + mPaddingBottom + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin + l, marginlayoutparams.height));
        return view.getMeasuredWidth() + k1;
    }

    private void measureChildConstrained(View view, int i, int j, int k, int l, int i1)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int j1 = getChildMeasureSpec(i, mPaddingLeft + mPaddingRight + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin + j, marginlayoutparams.width);
        j = getChildMeasureSpec(k, mPaddingTop + mPaddingBottom + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin + l, marginlayoutparams.height);
        k = android.view.View.MeasureSpec.getMode(j);
        i = j;
        if(k != 0x40000000)
        {
            i = j;
            if(i1 >= 0)
            {
                if(k != 0)
                    i = Math.min(android.view.View.MeasureSpec.getSize(j), i1);
                else
                    i = i1;
                i = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
            }
        }
        view.measure(j1, i);
    }

    private void postShowOverflowMenu()
    {
        removeCallbacks(mShowOverflowMenuRunnable);
        post(mShowOverflowMenuRunnable);
    }

    private boolean shouldCollapse()
    {
        if(!mCollapsible)
            return false;
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(shouldLayout(view) && view.getMeasuredWidth() > 0 && view.getMeasuredHeight() > 0)
                return false;
        }

        return true;
    }

    private boolean shouldLayout(View view)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(view != null)
        {
            flag1 = flag;
            if(view.getParent() == this)
            {
                flag1 = flag;
                if(view.getVisibility() != 8)
                    flag1 = true;
            }
        }
        return flag1;
    }

    void addChildrenForExpandedActionView()
    {
        for(int i = mHiddenViews.size() - 1; i >= 0; i--)
            addView((View)mHiddenViews.get(i));

        mHiddenViews.clear();
    }

    public boolean canShowOverflowMenu()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(getVisibility() == 0)
        {
            flag1 = flag;
            if(mMenuView != null)
                flag1 = mMenuView.isOverflowReserved();
        }
        return flag1;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        boolean flag;
        if(super.checkLayoutParams(layoutparams))
            flag = layoutparams instanceof LayoutParams;
        else
            flag = false;
        return flag;
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

    public void dismissPopupMenus()
    {
        if(mMenuView != null)
            mMenuView.dismissPopupMenus();
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(layoutparams instanceof LayoutParams)
            return new LayoutParams((LayoutParams)layoutparams);
        if(layoutparams instanceof android.app.ActionBar.LayoutParams)
            return new LayoutParams((android.app.ActionBar.LayoutParams)layoutparams);
        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        else
            return new LayoutParams(layoutparams);
    }

    public int getContentInsetEnd()
    {
        int i;
        if(mContentInsets != null)
            i = mContentInsets.getEnd();
        else
            i = 0;
        return i;
    }

    public int getContentInsetEndWithActions()
    {
        int i;
        if(mContentInsetEndWithActions != 0x80000000)
            i = mContentInsetEndWithActions;
        else
            i = getContentInsetEnd();
        return i;
    }

    public int getContentInsetLeft()
    {
        int i;
        if(mContentInsets != null)
            i = mContentInsets.getLeft();
        else
            i = 0;
        return i;
    }

    public int getContentInsetRight()
    {
        int i;
        if(mContentInsets != null)
            i = mContentInsets.getRight();
        else
            i = 0;
        return i;
    }

    public int getContentInsetStart()
    {
        int i;
        if(mContentInsets != null)
            i = mContentInsets.getStart();
        else
            i = 0;
        return i;
    }

    public int getContentInsetStartWithNavigation()
    {
        int i;
        if(mContentInsetStartWithNavigation != 0x80000000)
            i = mContentInsetStartWithNavigation;
        else
            i = getContentInsetStart();
        return i;
    }

    public int getCurrentContentInsetEnd()
    {
        boolean flag = false;
        int i;
        if(mMenuView != null)
        {
            MenuBuilder menubuilder = mMenuView.peekMenu();
            if(menubuilder != null)
                flag = menubuilder.hasVisibleItems();
            else
                flag = false;
        }
        if(flag)
            i = Math.max(getContentInsetEnd(), Math.max(mContentInsetEndWithActions, 0));
        else
            i = getContentInsetEnd();
        return i;
    }

    public int getCurrentContentInsetLeft()
    {
        int i;
        if(isLayoutRtl())
            i = getCurrentContentInsetEnd();
        else
            i = getCurrentContentInsetStart();
        return i;
    }

    public int getCurrentContentInsetRight()
    {
        int i;
        if(isLayoutRtl())
            i = getCurrentContentInsetStart();
        else
            i = getCurrentContentInsetEnd();
        return i;
    }

    public int getCurrentContentInsetStart()
    {
        int i;
        if(getNavigationIcon() != null)
            i = Math.max(getContentInsetStart(), Math.max(mContentInsetStartWithNavigation, 0));
        else
            i = getContentInsetStart();
        return i;
    }

    public Drawable getLogo()
    {
        Drawable drawable = null;
        if(mLogoView != null)
            drawable = mLogoView.getDrawable();
        return drawable;
    }

    public CharSequence getLogoDescription()
    {
        CharSequence charsequence = null;
        if(mLogoView != null)
            charsequence = mLogoView.getContentDescription();
        return charsequence;
    }

    public Menu getMenu()
    {
        ensureMenu();
        return mMenuView.getMenu();
    }

    public CharSequence getNavigationContentDescription()
    {
        CharSequence charsequence = null;
        if(mNavButtonView != null)
            charsequence = mNavButtonView.getContentDescription();
        return charsequence;
    }

    public Drawable getNavigationIcon()
    {
        Drawable drawable = null;
        if(mNavButtonView != null)
            drawable = mNavButtonView.getDrawable();
        return drawable;
    }

    public View getNavigationView()
    {
        return mNavButtonView;
    }

    ActionMenuPresenter getOuterActionMenuPresenter()
    {
        return mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon()
    {
        ensureMenu();
        return mMenuView.getOverflowIcon();
    }

    Context getPopupContext()
    {
        return mPopupContext;
    }

    public int getPopupTheme()
    {
        return mPopupTheme;
    }

    public CharSequence getSubtitle()
    {
        return mSubtitleText;
    }

    public CharSequence getTitle()
    {
        return mTitleText;
    }

    public int getTitleMarginBottom()
    {
        return mTitleMarginBottom;
    }

    public int getTitleMarginEnd()
    {
        return mTitleMarginEnd;
    }

    public int getTitleMarginStart()
    {
        return mTitleMarginStart;
    }

    public int getTitleMarginTop()
    {
        return mTitleMarginTop;
    }

    public DecorToolbar getWrapper()
    {
        if(mWrapper == null)
            mWrapper = new ToolbarWidgetWrapper(this, true);
        return mWrapper;
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

    public boolean hideOverflowMenu()
    {
        boolean flag;
        if(mMenuView != null)
            flag = mMenuView.hideOverflowMenu();
        else
            flag = false;
        return flag;
    }

    public void inflateMenu(int i)
    {
        getMenuInflater().inflate(i, getMenu());
    }

    public boolean isOverflowMenuShowPending()
    {
        boolean flag;
        if(mMenuView != null)
            flag = mMenuView.isOverflowMenuShowPending();
        else
            flag = false;
        return flag;
    }

    public boolean isOverflowMenuShowing()
    {
        boolean flag;
        if(mMenuView != null)
            flag = mMenuView.isOverflowMenuShowing();
        else
            flag = false;
        return flag;
    }

    public boolean isTitleTruncated()
    {
        if(mTitleTextView == null)
            return false;
        Layout layout = mTitleTextView.getLayout();
        if(layout == null)
            return false;
        int i = layout.getLineCount();
        for(int j = 0; j < i; j++)
            if(layout.getEllipsisCount(j) > 0)
                return true;

        return false;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        Object obj = getParent();
        do
        {
label0:
            {
                if(obj != null && (obj instanceof ViewGroup))
                {
                    obj = (ViewGroup)obj;
                    if(!((ViewGroup) (obj)).isKeyboardNavigationCluster())
                        break label0;
                    setKeyboardNavigationCluster(false);
                    if(((ViewGroup) (obj)).getTouchscreenBlocksFocus())
                        setTouchscreenBlocksFocus(false);
                }
                return;
            }
            obj = ((ViewGroup) (obj)).getParent();
        } while(true);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeCallbacks(mShowOverflowMenuRunnable);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int ai[];
        int j3;
        boolean flag1;
        Object obj;
        int k3;
        Object obj1;
        int i1;
        if(getLayoutDirection() == 1)
            i1 = 1;
        else
            i1 = 0;
        k1 = getWidth();
        l1 = getHeight();
        i2 = getPaddingLeft();
        j2 = getPaddingRight();
        k2 = getPaddingTop();
        l2 = getPaddingBottom();
        i = i2;
        i3 = k1 - j2;
        ai = mTempMargins;
        ai[1] = 0;
        ai[0] = 0;
        k = getMinimumHeight();
        if(k >= 0)
            j3 = Math.min(k, l - j);
        else
            j3 = 0;
        j = i;
        k = i3;
        if(shouldLayout(mNavButtonView))
            if(i1 != 0)
            {
                k = layoutChildRight(mNavButtonView, i3, ai, j3);
                j = i;
            } else
            {
                j = layoutChildLeft(mNavButtonView, i2, ai, j3);
                k = i3;
            }
        l = j;
        i = k;
        if(shouldLayout(mCollapseButtonView))
            if(i1 != 0)
            {
                i = layoutChildRight(mCollapseButtonView, k, ai, j3);
                l = j;
            } else
            {
                l = layoutChildLeft(mCollapseButtonView, j, ai, j3);
                i = k;
            }
        k = l;
        j = i;
        if(shouldLayout(mMenuView))
            if(i1 != 0)
            {
                k = layoutChildLeft(mMenuView, l, ai, j3);
                j = i;
            } else
            {
                j = layoutChildRight(mMenuView, i, ai, j3);
                k = l;
            }
        l = getCurrentContentInsetLeft();
        i = getCurrentContentInsetRight();
        ai[0] = Math.max(0, l - k);
        ai[1] = Math.max(0, i - (k1 - j2 - j));
        k = Math.max(k, l);
        l = Math.min(j, k1 - j2 - i);
        j = k;
        i = l;
        if(shouldLayout(mExpandedActionView))
            if(i1 != 0)
            {
                i = layoutChildRight(mExpandedActionView, l, ai, j3);
                j = k;
            } else
            {
                j = layoutChildLeft(mExpandedActionView, k, ai, j3);
                i = l;
            }
        l = j;
        i3 = i;
        if(shouldLayout(mLogoView))
            if(i1 != 0)
            {
                i3 = layoutChildRight(mLogoView, i, ai, j3);
                l = j;
            } else
            {
                l = layoutChildLeft(mLogoView, j, ai, j3);
                i3 = i;
            }
        flag1 = shouldLayout(mTitleTextView);
        flag = shouldLayout(mSubtitleTextView);
        i = 0;
        if(flag1)
        {
            LayoutParams layoutparams = (LayoutParams)mTitleTextView.getLayoutParams();
            i = layoutparams.topMargin + mTitleTextView.getMeasuredHeight() + layoutparams.bottomMargin + 0;
        }
        k3 = i;
        if(flag)
        {
            LayoutParams layoutparams1 = (LayoutParams)mSubtitleTextView.getLayoutParams();
            k3 = i + (layoutparams1.topMargin + mSubtitleTextView.getMeasuredHeight() + layoutparams1.bottomMargin);
        }
        if(flag1) goto _L2; else goto _L1
_L1:
        j = l;
        i = i3;
        if(!flag) goto _L3; else goto _L2
_L2:
        if(flag1)
            obj = mTitleTextView;
        else
            obj = mSubtitleTextView;
        if(flag)
            obj1 = mSubtitleTextView;
        else
            obj1 = mTitleTextView;
        obj = (LayoutParams)((View) (obj)).getLayoutParams();
        obj1 = (LayoutParams)((View) (obj1)).getLayoutParams();
        if(flag1 && mTitleTextView.getMeasuredWidth() > 0)
            k = 1;
        else
        if(flag && mSubtitleTextView.getMeasuredWidth() > 0)
            k = 1;
        else
            k = 0;
        mGravity & 0x70;
        JVM INSTR lookupswitch 3: default 576
    //                   16: 576
    //                   48: 1064
    //                   80: 1138;
           goto _L4 _L4 _L5 _L6
_L4:
        j = (l1 - k2 - l2 - k3) / 2;
        if(j < ((LayoutParams) (obj)).topMargin + mTitleMarginTop)
        {
            i = ((LayoutParams) (obj)).topMargin + mTitleMarginTop;
        } else
        {
            k3 = l1 - l2 - k3 - j - k2;
            i = j;
            if(k3 < ((LayoutParams) (obj)).bottomMargin + mTitleMarginBottom)
                i = Math.max(0, j - ((((LayoutParams) (obj1)).bottomMargin + mTitleMarginBottom) - k3));
        }
        i = k2 + i;
_L13:
        if(i1 != 0)
        {
            if(k != 0)
                j = mTitleMarginStart;
            else
                j = 0;
            j -= ai[1];
            i3 -= Math.max(0, j);
            ai[1] = Math.max(0, -j);
            i1 = i3;
            j = i3;
            k3 = i;
            if(flag1)
            {
                obj = (LayoutParams)mTitleTextView.getLayoutParams();
                i1 = i3 - mTitleTextView.getMeasuredWidth();
                k3 = i + mTitleTextView.getMeasuredHeight();
                mTitleTextView.layout(i1, i, i3, k3);
                i1 -= mTitleMarginEnd;
                k3 += ((LayoutParams) (obj)).bottomMargin;
            }
            k2 = j;
            if(flag)
            {
                obj = (LayoutParams)mSubtitleTextView.getLayoutParams();
                k3 += ((LayoutParams) (obj)).topMargin;
                i = mSubtitleTextView.getMeasuredWidth();
                j = k3 + mSubtitleTextView.getMeasuredHeight();
                mSubtitleTextView.layout(i3 - i, k3, i3, j);
                k2 = i3 - mTitleMarginEnd;
                i = ((LayoutParams) (obj)).bottomMargin;
            }
            j = l;
            i = i3;
            if(k != 0)
            {
                i = Math.min(i1, k2);
                j = l;
            }
        } else
        {
            int j1;
            int l3;
            if(k != 0)
                j = mTitleMarginStart;
            else
                j = 0;
            j1 = j - ai[0];
            j = l + Math.max(0, j1);
            ai[0] = Math.max(0, -j1);
            l = j;
            j1 = j;
            l3 = i;
            if(flag1)
            {
                LayoutParams layoutparams2 = (LayoutParams)mTitleTextView.getLayoutParams();
                l = j + mTitleTextView.getMeasuredWidth();
                l3 = i + mTitleTextView.getMeasuredHeight();
                mTitleTextView.layout(j, i, l, l3);
                l += mTitleMarginEnd;
                l3 += layoutparams2.bottomMargin;
            }
            if(flag)
            {
                LayoutParams layoutparams3 = (LayoutParams)mSubtitleTextView.getLayoutParams();
                l3 += layoutparams3.topMargin;
                j1 = j + mSubtitleTextView.getMeasuredWidth();
                i = l3 + mSubtitleTextView.getMeasuredHeight();
                mSubtitleTextView.layout(j, l3, j1, i);
                j1 += mTitleMarginEnd;
                i = layoutparams3.bottomMargin;
            }
            i = i3;
            if(k != 0)
            {
                j = Math.max(l, j1);
                i = i3;
            }
        }
_L3:
        addCustomViewsWithGravity(mTempViews, 3);
        l = mTempViews.size();
        for(k = 0; k < l; k++)
            j = layoutChildLeft((View)mTempViews.get(k), j, ai, j3);

        addCustomViewsWithGravity(mTempViews, 5);
        i3 = mTempViews.size();
        l = 0;
        k = i;
        for(i = l; i < i3; i++)
            k = layoutChildRight((View)mTempViews.get(i), k, ai, j3);

        addCustomViewsWithGravity(mTempViews, 1);
        i = getViewListMeasuredWidth(mTempViews, ai);
        l = (i2 + (k1 - i2 - j2) / 2) - i / 2;
        i3 = l + i;
          goto _L7
_L5:
        i = getPaddingTop() + ((LayoutParams) (obj)).topMargin + mTitleMarginTop;
        continue; /* Loop/switch isn't completed */
_L6:
        i = l1 - l2 - ((LayoutParams) (obj1)).bottomMargin - mTitleMarginBottom - k3;
        continue; /* Loop/switch isn't completed */
_L7:
        if(l >= j) goto _L9; else goto _L8
_L8:
        i = j;
_L11:
        k = mTempViews.size();
        for(j = 0; j < k; j++)
            i = layoutChildLeft((View)mTempViews.get(j), i, ai, j3);

        break; /* Loop/switch isn't completed */
_L9:
        i = l;
        if(i3 > k)
            i = l - (i3 - k);
        if(true) goto _L11; else goto _L10
_L10:
        mTempViews.clear();
        return;
        if(true) goto _L13; else goto _L12
_L12:
    }

    protected void onMeasure(int i, int j)
    {
        int k = 0;
        int l = 0;
        int ai[] = mTempMargins;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(isLayoutRtl())
        {
            i1 = 1;
            j1 = 0;
        } else
        {
            i1 = 0;
            j1 = 1;
        }
        k1 = 0;
        if(shouldLayout(mNavButtonView))
        {
            measureChildConstrained(mNavButtonView, i, 0, j, 0, mMaxButtonHeight);
            k1 = mNavButtonView.getMeasuredWidth() + getHorizontalMargins(mNavButtonView);
            k = Math.max(0, mNavButtonView.getMeasuredHeight() + getVerticalMargins(mNavButtonView));
            l = combineMeasuredStates(0, mNavButtonView.getMeasuredState());
        }
        l1 = l;
        i2 = k;
        if(shouldLayout(mCollapseButtonView))
        {
            measureChildConstrained(mCollapseButtonView, i, 0, j, 0, mMaxButtonHeight);
            k1 = mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(mCollapseButtonView);
            i2 = Math.max(k, mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(mCollapseButtonView));
            l1 = combineMeasuredStates(l, mCollapseButtonView.getMeasuredState());
        }
        l = getCurrentContentInsetStart();
        j2 = Math.max(l, k1) + 0;
        ai[i1] = Math.max(0, l - k1);
        k1 = 0;
        k = l1;
        l = i2;
        if(shouldLayout(mMenuView))
        {
            measureChildConstrained(mMenuView, i, j2, j, 0, mMaxButtonHeight);
            k1 = mMenuView.getMeasuredWidth() + getHorizontalMargins(mMenuView);
            l = Math.max(i2, mMenuView.getMeasuredHeight() + getVerticalMargins(mMenuView));
            k = combineMeasuredStates(l1, mMenuView.getMeasuredState());
        }
        i2 = getCurrentContentInsetEnd();
        i1 = j2 + Math.max(i2, k1);
        ai[j1] = Math.max(0, i2 - k1);
        j1 = i1;
        i2 = k;
        l1 = l;
        if(shouldLayout(mExpandedActionView))
        {
            j1 = i1 + measureChildCollapseMargins(mExpandedActionView, i, i1, j, 0, ai);
            l1 = Math.max(l, mExpandedActionView.getMeasuredHeight() + getVerticalMargins(mExpandedActionView));
            i2 = combineMeasuredStates(k, mExpandedActionView.getMeasuredState());
        }
        k1 = j1;
        k = i2;
        l = l1;
        if(shouldLayout(mLogoView))
        {
            k1 = j1 + measureChildCollapseMargins(mLogoView, i, j1, j, 0, ai);
            l = Math.max(l1, mLogoView.getMeasuredHeight() + getVerticalMargins(mLogoView));
            k = combineMeasuredStates(i2, mLogoView.getMeasuredState());
        }
        j2 = getChildCount();
        l1 = 0;
        j1 = l;
        i2 = k;
        while(l1 < j2) 
        {
            View view = getChildAt(l1);
            l = k1;
            k = i2;
            i1 = j1;
            if(((LayoutParams)view.getLayoutParams()).mViewType == 0)
                if(shouldLayout(view) ^ true)
                {
                    i1 = j1;
                    k = i2;
                    l = k1;
                } else
                {
                    l = k1 + measureChildCollapseMargins(view, i, k1, j, 0, ai);
                    i1 = Math.max(j1, view.getMeasuredHeight() + getVerticalMargins(view));
                    k = combineMeasuredStates(i2, view.getMeasuredState());
                }
            l1++;
            k1 = l;
            i2 = k;
            j1 = i1;
        }
        k = 0;
        l = 0;
        int k2 = mTitleMarginTop + mTitleMarginBottom;
        int l2 = mTitleMarginStart + mTitleMarginEnd;
        l1 = i2;
        if(shouldLayout(mTitleTextView))
        {
            measureChildCollapseMargins(mTitleTextView, i, k1 + l2, j, k2, ai);
            k = mTitleTextView.getMeasuredWidth() + getHorizontalMargins(mTitleTextView);
            l = mTitleTextView.getMeasuredHeight() + getVerticalMargins(mTitleTextView);
            l1 = combineMeasuredStates(i2, mTitleTextView.getMeasuredState());
        }
        i1 = l1;
        j2 = l;
        i2 = k;
        if(shouldLayout(mSubtitleTextView))
        {
            i2 = Math.max(k, measureChildCollapseMargins(mSubtitleTextView, i, k1 + l2, j, l + k2, ai));
            j2 = l + (mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(mSubtitleTextView));
            i1 = combineMeasuredStates(l1, mSubtitleTextView.getMeasuredState());
        }
        l1 = Math.max(j1, j2);
        j1 = getPaddingLeft();
        j2 = getPaddingRight();
        k = getPaddingTop();
        l = getPaddingBottom();
        i2 = resolveSizeAndState(Math.max(k1 + i2 + (j1 + j2), getSuggestedMinimumWidth()), i, 0xff000000 & i1);
        i = resolveSizeAndState(Math.max(l1 + (k + l), getSuggestedMinimumHeight()), j, i1 << 16);
        if(shouldCollapse())
            i = 0;
        setMeasuredDimension(i2, i);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if(mMenuView != null)
            parcelable = mMenuView.peekMenu();
        else
            parcelable = null;
        if(savedstate.expandedMenuItemId != 0 && mExpandedMenuPresenter != null && parcelable != null)
        {
            parcelable = parcelable.findItem(savedstate.expandedMenuItemId);
            if(parcelable != null)
                parcelable.expandActionView();
        }
        if(savedstate.isOverflowOpen)
            postShowOverflowMenu();
    }

    public void onRtlPropertiesChanged(int i)
    {
        boolean flag = true;
        super.onRtlPropertiesChanged(i);
        ensureContentInsets();
        RtlSpacingHelper rtlspacinghelper = mContentInsets;
        if(i != 1)
            flag = false;
        rtlspacinghelper.setDirection(flag);
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if(mExpandedMenuPresenter != null && mExpandedMenuPresenter.mCurrentExpandedItem != null)
            savedstate.expandedMenuItemId = mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        savedstate.isOverflowOpen = isOverflowMenuShowing();
        return savedstate;
    }

    protected void onSetLayoutParams(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(!checkLayoutParams(layoutparams))
            view.setLayoutParams(generateLayoutParams(layoutparams));
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if(i == 0)
            mEatingTouch = false;
        if(!mEatingTouch)
        {
            boolean flag = super.onTouchEvent(motionevent);
            if(i == 0 && flag ^ true)
                mEatingTouch = true;
        }
        if(i == 1 || i == 3)
            mEatingTouch = false;
        return true;
    }

    void removeChildrenForExpandedActionView()
    {
        for(int i = getChildCount() - 1; i >= 0; i--)
        {
            View view = getChildAt(i);
            if(((LayoutParams)view.getLayoutParams()).mViewType != 2 && view != mMenuView)
            {
                removeViewAt(i);
                mHiddenViews.add(view);
            }
        }

    }

    public void setCollapsible(boolean flag)
    {
        mCollapsible = flag;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i)
    {
        int j = i;
        if(i < 0)
            j = 0x80000000;
        if(j != mContentInsetEndWithActions)
        {
            mContentInsetEndWithActions = j;
            if(getNavigationIcon() != null)
                requestLayout();
        }
    }

    public void setContentInsetStartWithNavigation(int i)
    {
        int j = i;
        if(i < 0)
            j = 0x80000000;
        if(j != mContentInsetStartWithNavigation)
        {
            mContentInsetStartWithNavigation = j;
            if(getNavigationIcon() != null)
                requestLayout();
        }
    }

    public void setContentInsetsAbsolute(int i, int j)
    {
        ensureContentInsets();
        mContentInsets.setAbsolute(i, j);
    }

    public void setContentInsetsRelative(int i, int j)
    {
        ensureContentInsets();
        mContentInsets.setRelative(i, j);
    }

    public void setLogo(int i)
    {
        setLogo(getContext().getDrawable(i));
    }

    public void setLogo(Drawable drawable)
    {
        if(drawable == null) goto _L2; else goto _L1
_L1:
        ensureLogoView();
        if(!isChildOrHidden(mLogoView))
            addSystemView(mLogoView, true);
_L4:
        if(mLogoView != null)
            mLogoView.setImageDrawable(drawable);
        return;
_L2:
        if(mLogoView != null && isChildOrHidden(mLogoView))
        {
            removeView(mLogoView);
            mHiddenViews.remove(mLogoView);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setLogoDescription(int i)
    {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charsequence)
    {
        if(!TextUtils.isEmpty(charsequence))
            ensureLogoView();
        if(mLogoView != null)
            mLogoView.setContentDescription(charsequence);
    }

    public void setMenu(MenuBuilder menubuilder, ActionMenuPresenter actionmenupresenter)
    {
        if(menubuilder == null && mMenuView == null)
            return;
        ensureMenuView();
        MenuBuilder menubuilder1 = mMenuView.peekMenu();
        if(menubuilder1 == menubuilder)
            return;
        if(menubuilder1 != null)
        {
            menubuilder1.removeMenuPresenter(mOuterActionMenuPresenter);
            menubuilder1.removeMenuPresenter(mExpandedMenuPresenter);
        }
        if(mExpandedMenuPresenter == null)
            mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
        actionmenupresenter.setExpandedActionViewsExclusive(true);
        if(menubuilder != null)
        {
            menubuilder.addMenuPresenter(actionmenupresenter, mPopupContext);
            menubuilder.addMenuPresenter(mExpandedMenuPresenter, mPopupContext);
        } else
        {
            actionmenupresenter.initForMenu(mPopupContext, null);
            mExpandedMenuPresenter.initForMenu(mPopupContext, null);
            actionmenupresenter.updateMenuView(true);
            mExpandedMenuPresenter.updateMenuView(true);
        }
        mMenuView.setPopupTheme(mPopupTheme);
        mMenuView.setPresenter(actionmenupresenter);
        mOuterActionMenuPresenter = actionmenupresenter;
    }

    public void setMenuCallbacks(com.android.internal.view.menu.MenuPresenter.Callback callback, com.android.internal.view.menu.MenuBuilder.Callback callback1)
    {
        mActionMenuPresenterCallback = callback;
        mMenuBuilderCallback = callback1;
        if(mMenuView != null)
            mMenuView.setMenuCallbacks(callback, callback1);
    }

    public void setNavigationContentDescription(int i)
    {
        CharSequence charsequence;
        if(i != 0)
            charsequence = getContext().getText(i);
        else
            charsequence = null;
        setNavigationContentDescription(charsequence);
    }

    public void setNavigationContentDescription(CharSequence charsequence)
    {
        if(!TextUtils.isEmpty(charsequence))
            ensureNavButtonView();
        if(mNavButtonView != null)
            mNavButtonView.setContentDescription(charsequence);
    }

    public void setNavigationIcon(int i)
    {
        setNavigationIcon(getContext().getDrawable(i));
    }

    public void setNavigationIcon(Drawable drawable)
    {
        if(drawable == null) goto _L2; else goto _L1
_L1:
        ensureNavButtonView();
        if(!isChildOrHidden(mNavButtonView))
            addSystemView(mNavButtonView, true);
_L4:
        if(mNavButtonView != null)
            mNavButtonView.setImageDrawable(drawable);
        return;
_L2:
        if(mNavButtonView != null && isChildOrHidden(mNavButtonView))
        {
            removeView(mNavButtonView);
            mHiddenViews.remove(mNavButtonView);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setNavigationOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        ensureNavButtonView();
        mNavButtonView.setOnClickListener(onclicklistener);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onmenuitemclicklistener)
    {
        mOnMenuItemClickListener = onmenuitemclicklistener;
    }

    public void setOverflowIcon(Drawable drawable)
    {
        ensureMenu();
        mMenuView.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i)
    {
        if(mPopupTheme != i)
        {
            mPopupTheme = i;
            if(i == 0)
                mPopupContext = mContext;
            else
                mPopupContext = new ContextThemeWrapper(mContext, i);
        }
    }

    public void setSubtitle(int i)
    {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charsequence)
    {
        if(TextUtils.isEmpty(charsequence)) goto _L2; else goto _L1
_L1:
        if(mSubtitleTextView == null)
        {
            mSubtitleTextView = new TextView(getContext());
            mSubtitleTextView.setSingleLine();
            mSubtitleTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            if(mSubtitleTextAppearance != 0)
                mSubtitleTextView.setTextAppearance(mSubtitleTextAppearance);
            if(mSubtitleTextColor != 0)
                mSubtitleTextView.setTextColor(mSubtitleTextColor);
        }
        if(!isChildOrHidden(mSubtitleTextView))
            addSystemView(mSubtitleTextView, true);
_L4:
        if(mSubtitleTextView != null)
            mSubtitleTextView.setText(charsequence);
        mSubtitleText = charsequence;
        return;
_L2:
        if(mSubtitleTextView != null && isChildOrHidden(mSubtitleTextView))
        {
            removeView(mSubtitleTextView);
            mHiddenViews.remove(mSubtitleTextView);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setSubtitleTextAppearance(Context context, int i)
    {
        mSubtitleTextAppearance = i;
        if(mSubtitleTextView != null)
            mSubtitleTextView.setTextAppearance(i);
    }

    public void setSubtitleTextColor(int i)
    {
        mSubtitleTextColor = i;
        if(mSubtitleTextView != null)
            mSubtitleTextView.setTextColor(i);
    }

    public void setTitle(int i)
    {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charsequence)
    {
        if(TextUtils.isEmpty(charsequence)) goto _L2; else goto _L1
_L1:
        if(mTitleTextView == null)
        {
            mTitleTextView = new TextView(getContext());
            mTitleTextView.setSingleLine();
            mTitleTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            if(mTitleTextAppearance != 0)
                mTitleTextView.setTextAppearance(mTitleTextAppearance);
            if(mTitleTextColor != 0)
                mTitleTextView.setTextColor(mTitleTextColor);
        }
        if(!isChildOrHidden(mTitleTextView))
            addSystemView(mTitleTextView, true);
_L4:
        if(mTitleTextView != null)
            mTitleTextView.setText(charsequence);
        mTitleText = charsequence;
        return;
_L2:
        if(mTitleTextView != null && isChildOrHidden(mTitleTextView))
        {
            removeView(mTitleTextView);
            mHiddenViews.remove(mTitleTextView);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setTitleMargin(int i, int j, int k, int l)
    {
        mTitleMarginStart = i;
        mTitleMarginTop = j;
        mTitleMarginEnd = k;
        mTitleMarginBottom = l;
        requestLayout();
    }

    public void setTitleMarginBottom(int i)
    {
        mTitleMarginBottom = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i)
    {
        mTitleMarginEnd = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i)
    {
        mTitleMarginStart = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i)
    {
        mTitleMarginTop = i;
        requestLayout();
    }

    public void setTitleTextAppearance(Context context, int i)
    {
        mTitleTextAppearance = i;
        if(mTitleTextView != null)
            mTitleTextView.setTextAppearance(i);
    }

    public void setTitleTextColor(int i)
    {
        mTitleTextColor = i;
        if(mTitleTextView != null)
            mTitleTextView.setTextColor(i);
    }

    public boolean showOverflowMenu()
    {
        boolean flag;
        if(mMenuView != null)
            flag = mMenuView.showOverflowMenu();
        else
            flag = false;
        return flag;
    }

    private static final String TAG = "Toolbar";
    private com.android.internal.view.menu.MenuPresenter.Callback mActionMenuPresenterCallback;
    private int mButtonGravity;
    private ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private com.android.internal.view.menu.MenuBuilder.Callback mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private int mNavButtonStyle;
    private ImageButton mNavButtonView;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int mTempMargins[];
    private final ArrayList mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;
}
