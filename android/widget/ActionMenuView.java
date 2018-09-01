// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.view.menu.*;

// Referenced classes of package android.widget:
//            LinearLayout, ActionMenuPresenter

public class ActionMenuView extends LinearLayout
    implements com.android.internal.view.menu.MenuBuilder.ItemInvoker, MenuView
{
    public static interface ActionMenuChildView
    {

        public abstract boolean needsDividerAfter();

        public abstract boolean needsDividerBefore();
    }

    private class ActionMenuPresenterCallback
        implements com.android.internal.view.menu.MenuPresenter.Callback
    {

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            return false;
        }

        final ActionMenuView this$0;

        private ActionMenuPresenterCallback()
        {
            this$0 = ActionMenuView.this;
            super();
        }

        ActionMenuPresenterCallback(ActionMenuPresenterCallback actionmenupresentercallback)
        {
            this();
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams
    {

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            super.encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.addProperty("layout:overFlowButton", isOverflowButton);
            viewhierarchyencoder.addProperty("layout:cellsUsed", cellsUsed);
            viewhierarchyencoder.addProperty("layout:extraPixels", extraPixels);
            viewhierarchyencoder.addProperty("layout:expandable", expandable);
            viewhierarchyencoder.addProperty("layout:preventEdgeOffset", preventEdgeOffset);
        }

        public int cellsUsed;
        public boolean expandable;
        public boolean expanded;
        public int extraPixels;
        public boolean isOverflowButton;
        public boolean preventEdgeOffset;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            isOverflowButton = false;
        }

        public LayoutParams(int i, int j, boolean flag)
        {
            super(i, j);
            isOverflowButton = flag;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            isOverflowButton = layoutparams.isOverflowButton;
        }
    }

    private class MenuBuilderCallback
        implements com.android.internal.view.menu.MenuBuilder.Callback
    {

        public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            boolean flag;
            if(ActionMenuView._2D_get1(ActionMenuView.this) != null)
                flag = ActionMenuView._2D_get1(ActionMenuView.this).onMenuItemClick(menuitem);
            else
                flag = false;
            return flag;
        }

        public void onMenuModeChange(MenuBuilder menubuilder)
        {
            if(ActionMenuView._2D_get0(ActionMenuView.this) != null)
                ActionMenuView._2D_get0(ActionMenuView.this).onMenuModeChange(menubuilder);
        }

        final ActionMenuView this$0;

        private MenuBuilderCallback()
        {
            this$0 = ActionMenuView.this;
            super();
        }

        MenuBuilderCallback(MenuBuilderCallback menubuildercallback)
        {
            this();
        }
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
    }


    static com.android.internal.view.menu.MenuBuilder.Callback _2D_get0(ActionMenuView actionmenuview)
    {
        return actionmenuview.mMenuBuilderCallback;
    }

    static OnMenuItemClickListener _2D_get1(ActionMenuView actionmenuview)
    {
        return actionmenuview.mOnMenuItemClickListener;
    }

    public ActionMenuView(Context context)
    {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        mMinCellSize = (int)(56F * f);
        mGeneratedItemPadding = (int)(4F * f);
        mPopupContext = context;
        mPopupTheme = 0;
    }

    static int measureChildForCells(View view, int i, int j, int k, int l)
    {
        boolean flag;
label0:
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            int i1 = android.view.View.MeasureSpec.makeMeasureSpec(android.view.View.MeasureSpec.getSize(k) - l, android.view.View.MeasureSpec.getMode(k));
            ActionMenuItemView actionmenuitemview;
            if(view instanceof ActionMenuItemView)
                actionmenuitemview = (ActionMenuItemView)view;
            else
                actionmenuitemview = null;
            if(actionmenuitemview != null)
                flag = actionmenuitemview.hasText();
            else
                flag = false;
            l = 0;
            k = l;
            if(j <= 0)
                break label0;
            if(flag)
            {
                k = l;
                if(j < 2)
                    break label0;
            }
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i * j, 0x80000000), i1);
            l = view.getMeasuredWidth();
            k = l / i;
            j = k;
            if(l % i != 0)
                j = k + 1;
            k = j;
            if(flag)
            {
                k = j;
                if(j < 2)
                    k = 2;
            }
        }
        if(layoutparams.isOverflowButton)
            flag = false;
        layoutparams.expandable = flag;
        layoutparams.cellsUsed = k;
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k * i, 0x40000000), i1);
        return k;
    }

    private void onMeasureExactFormat(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(j);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = android.view.View.MeasureSpec.getSize(j);
        int j1 = getPaddingLeft();
        i = getPaddingRight();
        int l1 = getPaddingTop() + getPaddingBottom();
        int i2 = getChildMeasureSpec(j, l1, -2);
        int j2 = l - (j1 + i);
        j = j2 / mMinCellSize;
        i = mMinCellSize;
        if(j == 0)
        {
            setMeasuredDimension(j2, 0);
            return;
        }
        int k2 = mMinCellSize + (j2 % i) / j;
        l = 0;
        int l2 = 0;
        int i3 = 0;
        int j3 = 0;
        boolean flag = false;
        long l3 = 0L;
        int k3 = getChildCount();
        int i4 = 0;
        while(i4 < k3) 
        {
            View view = getChildAt(i4);
            long l4;
            boolean flag1;
            if(view.getVisibility() == 8)
            {
                l4 = l3;
                flag1 = flag;
            } else
            {
                boolean flag2 = view instanceof ActionMenuItemView;
                int k4 = j3 + 1;
                if(flag2)
                    view.setPadding(mGeneratedItemPadding, 0, mGeneratedItemPadding, 0);
                LayoutParams layoutparams2 = (LayoutParams)view.getLayoutParams();
                layoutparams2.expanded = false;
                layoutparams2.extraPixels = 0;
                layoutparams2.cellsUsed = 0;
                layoutparams2.expandable = false;
                layoutparams2.leftMargin = 0;
                layoutparams2.rightMargin = 0;
                int j5;
                int k5;
                int j6;
                int l6;
                if(flag2)
                    flag2 = ((ActionMenuItemView)view).hasText();
                else
                    flag2 = false;
                layoutparams2.preventEdgeOffset = flag2;
                if(layoutparams2.isOverflowButton)
                    i = 1;
                else
                    i = j;
                j5 = measureChildForCells(view, k2, i, i2, l1);
                k5 = Math.max(l2, j5);
                i = i3;
                if(layoutparams2.expandable)
                    i = i3 + 1;
                if(layoutparams2.isOverflowButton)
                    flag = true;
                j6 = j - j5;
                l6 = Math.max(l, view.getMeasuredHeight());
                j = j6;
                i3 = i;
                flag1 = flag;
                l2 = k5;
                l = l6;
                l4 = l3;
                j3 = k4;
                if(j5 == 1)
                {
                    l4 = l3 | (long)(1 << i4);
                    j = j6;
                    i3 = i;
                    flag1 = flag;
                    l2 = k5;
                    l = l6;
                    j3 = k4;
                }
            }
            i4++;
            flag = flag1;
            l3 = l4;
        }
        int j4;
        if(flag && j3 == 2)
            i4 = 1;
        else
            i4 = 0;
        i = 0;
        j4 = j;
        do
        {
label0:
            {
label1:
                {
label2:
                    {
                        long l5 = l3;
                        int i5;
                        long l7;
                        if(i3 > 0)
                        {
                            l5 = l3;
                            if(j4 > 0)
                            {
                                i5 = 0x7fffffff;
                                l7 = 0L;
                                int i7 = 0;
                                int i6 = 0;
                                while(i6 < k3) 
                                {
                                    LayoutParams layoutparams3 = (LayoutParams)getChildAt(i6).getLayoutParams();
                                    int k6;
                                    if(!layoutparams3.expandable)
                                    {
                                        l5 = l7;
                                        j = i7;
                                        k6 = i5;
                                    } else
                                    if(layoutparams3.cellsUsed < i5)
                                    {
                                        k6 = layoutparams3.cellsUsed;
                                        l5 = 1 << i6;
                                        j = 1;
                                    } else
                                    {
                                        k6 = i5;
                                        j = i7;
                                        l5 = l7;
                                        if(layoutparams3.cellsUsed == i5)
                                        {
                                            l5 = l7 | (long)(1 << i6);
                                            j = i7 + 1;
                                            k6 = i5;
                                        }
                                    }
                                    i6++;
                                    i5 = k6;
                                    i7 = j;
                                    l7 = l5;
                                }
                                l3 |= l7;
                                if(i7 <= j4)
                                    break label2;
                                l5 = l3;
                            }
                        }
                        int k1;
                        float f;
                        float f1;
                        if(!flag && j3 == 1)
                            k1 = 1;
                        else
                            k1 = 0;
                        j = i;
                        if(j4 <= 0)
                            break label0;
                        j = i;
                        if(l5 == 0L)
                            break label0;
                        if(j4 >= j3 - 1 && k1 == 0)
                        {
                            j = i;
                            if(l2 <= 1)
                                break label0;
                        }
                        f = Long.bitCount(l5);
                        f1 = f;
                        if(k1 == 0)
                        {
                            float f2 = f;
                            if((1L & l5) != 0L)
                            {
                                f2 = f;
                                if(!((LayoutParams)getChildAt(0).getLayoutParams()).preventEdgeOffset)
                                    f2 = f - 0.5F;
                            }
                            f1 = f2;
                            if(((long)(1 << k3 - 1) & l5) != 0L)
                            {
                                f1 = f2;
                                if(!((LayoutParams)getChildAt(k3 - 1).getLayoutParams()).preventEdgeOffset)
                                    f1 = f2 - 0.5F;
                            }
                        }
                        if(f1 > 0.0F)
                            k1 = (int)((float)(j4 * k2) / f1);
                        else
                            k1 = 0;
                        i3 = 0;
                        while(i3 < k3) 
                        {
                            View view1;
                            LayoutParams layoutparams4;
                            if(((long)(1 << i3) & l5) == 0L)
                            {
                                j = i;
                            } else
                            {
                                View view2 = getChildAt(i3);
                                LayoutParams layoutparams = (LayoutParams)view2.getLayoutParams();
                                if(view2 instanceof ActionMenuItemView)
                                {
                                    layoutparams.extraPixels = k1;
                                    layoutparams.expanded = true;
                                    if(i3 == 0 && layoutparams.preventEdgeOffset ^ true)
                                        layoutparams.leftMargin = -k1 / 2;
                                    j = 1;
                                } else
                                if(layoutparams.isOverflowButton)
                                {
                                    layoutparams.extraPixels = k1;
                                    layoutparams.expanded = true;
                                    layoutparams.rightMargin = -k1 / 2;
                                    j = 1;
                                } else
                                {
                                    if(i3 != 0)
                                        layoutparams.leftMargin = k1 / 2;
                                    j = i;
                                    if(i3 != k3 - 1)
                                    {
                                        layoutparams.rightMargin = k1 / 2;
                                        j = i;
                                    }
                                }
                            }
                            i3++;
                            i = j;
                        }
                        break label1;
                    }
                    i = 0;
                    while(i < k3) 
                    {
                        view1 = getChildAt(i);
                        layoutparams4 = (LayoutParams)view1.getLayoutParams();
                        if(((long)(1 << i) & l7) == 0L)
                        {
                            j = j4;
                            l5 = l3;
                            if(layoutparams4.cellsUsed == i5 + 1)
                            {
                                l5 = l3 | (long)(1 << i);
                                j = j4;
                            }
                        } else
                        {
                            if(i4 && layoutparams4.preventEdgeOffset && j4 == 1)
                                view1.setPadding(mGeneratedItemPadding + k2, 0, mGeneratedItemPadding, 0);
                            layoutparams4.cellsUsed = layoutparams4.cellsUsed + 1;
                            layoutparams4.expanded = true;
                            j = j4 - 1;
                            l5 = l3;
                        }
                        i++;
                        j4 = j;
                        l3 = l5;
                    }
                    i = 1;
                    continue;
                }
                j = i;
            }
            if(j != 0)
            {
                i = 0;
                while(i < k3) 
                {
                    View view3 = getChildAt(i);
                    LayoutParams layoutparams1 = (LayoutParams)view3.getLayoutParams();
                    if(layoutparams1.expanded)
                        view3.measure(android.view.View.MeasureSpec.makeMeasureSpec(layoutparams1.cellsUsed * k2 + layoutparams1.extraPixels, 0x40000000), i2);
                    i++;
                }
            }
            i = i1;
            if(k != 0x40000000)
                i = l;
            setMeasuredDimension(j2, i);
            return;
        } while(true);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        boolean flag;
        if(layoutparams != null)
            flag = layoutparams instanceof LayoutParams;
        else
            flag = false;
        return flag;
    }

    public void dismissPopupMenus()
    {
        if(mPresenter != null)
            mPresenter.dismissPopupMenus();
    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        return false;
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        LayoutParams layoutparams = new LayoutParams(-2, -2);
        layoutparams.gravity = 16;
        return layoutparams;
    }

    protected volatile LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
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
        if(layoutparams != null)
        {
            if(layoutparams instanceof LayoutParams)
                layoutparams = new LayoutParams((LayoutParams)layoutparams);
            else
                layoutparams = new LayoutParams(layoutparams);
            if(((LayoutParams) (layoutparams)).gravity <= 0)
                layoutparams.gravity = 16;
            return layoutparams;
        } else
        {
            return generateDefaultLayoutParams();
        }
    }

    public volatile LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public LayoutParams generateOverflowButtonLayoutParams()
    {
        LayoutParams layoutparams = generateDefaultLayoutParams();
        layoutparams.isOverflowButton = true;
        return layoutparams;
    }

    public Menu getMenu()
    {
        if(mMenu == null)
        {
            Object obj = getContext();
            mMenu = new MenuBuilder(((Context) (obj)));
            mMenu.setCallback(new MenuBuilderCallback(null));
            mPresenter = new ActionMenuPresenter(((Context) (obj)));
            mPresenter.setReserveOverflow(true);
            ActionMenuPresenter actionmenupresenter = mPresenter;
            if(mActionMenuPresenterCallback != null)
                obj = mActionMenuPresenterCallback;
            else
                obj = new ActionMenuPresenterCallback(null);
            actionmenupresenter.setCallback(((com.android.internal.view.menu.MenuPresenter.Callback) (obj)));
            mMenu.addMenuPresenter(mPresenter, mPopupContext);
            mPresenter.setMenuView(this);
        }
        return mMenu;
    }

    public Drawable getOverflowIcon()
    {
        getMenu();
        return mPresenter.getOverflowIcon();
    }

    public int getPopupTheme()
    {
        return mPopupTheme;
    }

    public int getWindowAnimations()
    {
        return 0;
    }

    protected boolean hasDividerBeforeChildAt(int i)
    {
        if(i == 0)
            return false;
        View view = getChildAt(i - 1);
        View view1 = getChildAt(i);
        boolean flag = false;
        boolean flag1 = flag;
        if(i < getChildCount())
        {
            flag1 = flag;
            if(view instanceof ActionMenuChildView)
                flag1 = ((ActionMenuChildView)view).needsDividerAfter();
        }
        flag = flag1;
        if(i > 0)
        {
            flag = flag1;
            if(view1 instanceof ActionMenuChildView)
                flag = flag1 | ((ActionMenuChildView)view1).needsDividerBefore();
        }
        return flag;
    }

    public boolean hideOverflowMenu()
    {
        boolean flag;
        if(mPresenter != null)
            flag = mPresenter.hideOverflowMenu();
        else
            flag = false;
        return flag;
    }

    public void initialize(MenuBuilder menubuilder)
    {
        mMenu = menubuilder;
    }

    public boolean invokeItem(MenuItemImpl menuitemimpl)
    {
        return mMenu.performItemAction(menuitemimpl, 0);
    }

    public boolean isOverflowMenuShowPending()
    {
        boolean flag;
        if(mPresenter != null)
            flag = mPresenter.isOverflowMenuShowPending();
        else
            flag = false;
        return flag;
    }

    public boolean isOverflowMenuShowing()
    {
        boolean flag;
        if(mPresenter != null)
            flag = mPresenter.isOverflowMenuShowing();
        else
            flag = false;
        return flag;
    }

    public boolean isOverflowReserved()
    {
        return mReserveOverflow;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(mPresenter != null)
        {
            mPresenter.updateMenuView(false);
            if(mPresenter.isOverflowMenuShowing())
            {
                mPresenter.hideOverflowMenu();
                mPresenter.showOverflowMenu();
            }
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(!mFormatItems)
        {
            super.onLayout(flag, i, j, k, l);
            return;
        }
        int i1 = getChildCount();
        int j1 = (l - j) / 2;
        int k1 = getDividerWidth();
        int l1 = 0;
        l = 0;
        j = k - i - getPaddingRight() - getPaddingLeft();
        int k2 = 0;
        flag = isLayoutRtl();
        int l2 = 0;
        while(l2 < i1) 
        {
            View view = getChildAt(l2);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams1 = (LayoutParams)view.getLayoutParams();
                if(layoutparams1.isOverflowButton)
                {
                    int k3 = view.getMeasuredWidth();
                    k2 = k3;
                    if(hasDividerBeforeChildAt(l2))
                        k2 = k3 + k1;
                    int i4 = view.getMeasuredHeight();
                    int j4;
                    int k4;
                    if(flag)
                    {
                        j4 = getPaddingLeft() + layoutparams1.leftMargin;
                        k3 = j4 + k2;
                    } else
                    {
                        k3 = getWidth() - getPaddingRight() - layoutparams1.rightMargin;
                        j4 = k3 - k2;
                    }
                    k4 = j1 - i4 / 2;
                    view.layout(j4, k4, k3, k4 + i4);
                    j -= k2;
                    k2 = 1;
                } else
                {
                    int l3 = view.getMeasuredWidth() + layoutparams1.leftMargin + layoutparams1.rightMargin;
                    l1 += l3;
                    l3 = j - l3;
                    j = l1;
                    if(hasDividerBeforeChildAt(l2))
                        j = l1 + k1;
                    l++;
                    l1 = j;
                    j = l3;
                }
            }
            l2++;
        }
        if(i1 == 1 && (k2 ^ 1) != 0)
        {
            View view2 = getChildAt(0);
            l = view2.getMeasuredWidth();
            j = view2.getMeasuredHeight();
            i = (k - i) / 2 - l / 2;
            k = j1 - j / 2;
            view2.layout(i, k, i + l, k + j);
            return;
        }
        if(k2 != 0)
            i = 0;
        else
            i = 1;
        i = l - i;
        if(i > 0)
            i = j / i;
        else
            i = 0;
        l = Math.max(0, i);
        if(flag)
        {
            k = getWidth() - getPaddingRight();
            i = 0;
            while(i < i1) 
            {
                View view1 = getChildAt(i);
                LayoutParams layoutparams2 = (LayoutParams)view1.getLayoutParams();
                j = k;
                if(view1.getVisibility() != 8)
                    if(layoutparams2.isOverflowButton)
                    {
                        j = k;
                    } else
                    {
                        int i3 = k - layoutparams2.rightMargin;
                        k = view1.getMeasuredWidth();
                        j = view1.getMeasuredHeight();
                        int i2 = j1 - j / 2;
                        view1.layout(i3 - k, i2, i3, i2 + j);
                        j = i3 - (layoutparams2.leftMargin + k + l);
                    }
                i++;
                k = j;
            }
        } else
        {
            j = getPaddingLeft();
            i = 0;
            while(i < i1) 
            {
                View view3 = getChildAt(i);
                LayoutParams layoutparams = (LayoutParams)view3.getLayoutParams();
                k = j;
                if(view3.getVisibility() != 8)
                    if(layoutparams.isOverflowButton)
                    {
                        k = j;
                    } else
                    {
                        int j3 = j + layoutparams.leftMargin;
                        j = view3.getMeasuredWidth();
                        int j2 = view3.getMeasuredHeight();
                        k = j1 - j2 / 2;
                        view3.layout(j3, k, j3 + j, k + j2);
                        k = j3 + (layoutparams.rightMargin + j + l);
                    }
                i++;
                j = k;
            }
        }
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag = mFormatItems;
        boolean flag1;
        int k;
        int i1;
        if(android.view.View.MeasureSpec.getMode(i) == 0x40000000)
            flag1 = true;
        else
            flag1 = false;
        mFormatItems = flag1;
        if(flag != mFormatItems)
            mFormatItemsWidth = 0;
        k = android.view.View.MeasureSpec.getSize(i);
        if(mFormatItems && mMenu != null && k != mFormatItemsWidth)
        {
            mFormatItemsWidth = k;
            mMenu.onItemsChanged(true);
        }
        i1 = getChildCount();
        if(mFormatItems && i1 > 0)
        {
            onMeasureExactFormat(i, j);
        } else
        {
            for(int l = 0; l < i1; l++)
            {
                LayoutParams layoutparams = (LayoutParams)getChildAt(l).getLayoutParams();
                layoutparams.rightMargin = 0;
                layoutparams.leftMargin = 0;
            }

            super.onMeasure(i, j);
        }
    }

    public MenuBuilder peekMenu()
    {
        return mMenu;
    }

    public void setExpandedActionViewsExclusive(boolean flag)
    {
        mPresenter.setExpandedActionViewsExclusive(flag);
    }

    public void setMenuCallbacks(com.android.internal.view.menu.MenuPresenter.Callback callback, com.android.internal.view.menu.MenuBuilder.Callback callback1)
    {
        mActionMenuPresenterCallback = callback;
        mMenuBuilderCallback = callback1;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onmenuitemclicklistener)
    {
        mOnMenuItemClickListener = onmenuitemclicklistener;
    }

    public void setOverflowIcon(Drawable drawable)
    {
        getMenu();
        mPresenter.setOverflowIcon(drawable);
    }

    public void setOverflowReserved(boolean flag)
    {
        mReserveOverflow = flag;
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

    public void setPresenter(ActionMenuPresenter actionmenupresenter)
    {
        mPresenter = actionmenupresenter;
        mPresenter.setMenuView(this);
    }

    public boolean showOverflowMenu()
    {
        boolean flag;
        if(mPresenter != null)
            flag = mPresenter.showOverflowMenu();
        else
            flag = false;
        return flag;
    }

    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private com.android.internal.view.menu.MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    private com.android.internal.view.menu.MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;
}
