// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.*;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.view.menu:
//            BaseMenuPresenter, IconMenuItemView, IconMenuView, MenuItemImpl, 
//            SubMenuBuilder, MenuDialogHelper, MenuBuilder

public class IconMenuPresenter extends BaseMenuPresenter
{
    class SubMenuPresenterCallback
        implements MenuPresenter.Callback
    {

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            mOpenSubMenuId = 0;
            if(mOpenSubMenu != null)
            {
                mOpenSubMenu.dismiss();
                mOpenSubMenu = null;
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if(menubuilder != null)
                mOpenSubMenuId = ((SubMenuBuilder)menubuilder).getItem().getItemId();
            return false;
        }

        final IconMenuPresenter this$0;

        SubMenuPresenterCallback()
        {
            this$0 = IconMenuPresenter.this;
            super();
        }
    }


    public IconMenuPresenter(Context context)
    {
        super(new ContextThemeWrapper(context, 0x10303e2), 0x109006b, 0x109006a);
        mMaxItems = -1;
        mSubMenuPresenterCallback = new SubMenuPresenterCallback();
    }

    protected void addItemView(View view, int i)
    {
        IconMenuItemView iconmenuitemview = (IconMenuItemView)view;
        IconMenuView iconmenuview = (IconMenuView)mMenuView;
        iconmenuitemview.setIconMenuView(iconmenuview);
        iconmenuitemview.setItemInvoker(iconmenuview);
        iconmenuitemview.setBackgroundDrawable(iconmenuview.getItemBackgroundDrawable());
        super.addItemView(view, i);
    }

    public void bindItemView(MenuItemImpl menuitemimpl, MenuView.ItemView itemview)
    {
        itemview = (IconMenuItemView)itemview;
        itemview.setItemData(menuitemimpl);
        itemview.initialize(menuitemimpl.getTitleForItemView(itemview), menuitemimpl.getIcon());
        int i;
        if(menuitemimpl.isVisible())
            i = 0;
        else
            i = 8;
        itemview.setVisibility(i);
        itemview.setEnabled(itemview.isEnabled());
        itemview.setLayoutParams(itemview.getTextAppropriateLayoutParams());
    }

    protected boolean filterLeftoverView(ViewGroup viewgroup, int i)
    {
        if(viewgroup.getChildAt(i) != mMoreView)
            return super.filterLeftoverView(viewgroup, i);
        else
            return false;
    }

    public int getNumActualItemsShown()
    {
        return ((IconMenuView)mMenuView).getNumActualItemsShown();
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
        super.initForMenu(context, menubuilder);
        mMaxItems = -1;
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        restoreHierarchyState((Bundle)parcelable);
    }

    public Parcelable onSaveInstanceState()
    {
        if(mMenuView == null)
            return null;
        Bundle bundle = new Bundle();
        saveHierarchyState(bundle);
        if(mOpenSubMenuId > 0)
            bundle.putInt("android:menu:icon:submenu", mOpenSubMenuId);
        return bundle;
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        if(!submenubuilder.hasVisibleItems())
        {
            return false;
        } else
        {
            MenuDialogHelper menudialoghelper = new MenuDialogHelper(submenubuilder);
            menudialoghelper.setPresenterCallback(mSubMenuPresenterCallback);
            menudialoghelper.show(null);
            mOpenSubMenu = menudialoghelper;
            mOpenSubMenuId = submenubuilder.getItem().getItemId();
            super.onSubMenuSelected(submenubuilder);
            return true;
        }
    }

    public void restoreHierarchyState(Bundle bundle)
    {
        SparseArray sparsearray = bundle.getSparseParcelableArray("android:menu:icon");
        if(sparsearray != null)
            ((View)mMenuView).restoreHierarchyState(sparsearray);
        int i = bundle.getInt("android:menu:icon:submenu", 0);
        if(i > 0 && mMenu != null)
        {
            bundle = mMenu.findItem(i);
            if(bundle != null)
                onSubMenuSelected((SubMenuBuilder)bundle.getSubMenu());
        }
    }

    public void saveHierarchyState(Bundle bundle)
    {
        SparseArray sparsearray = new SparseArray();
        if(mMenuView != null)
            ((View)mMenuView).saveHierarchyState(sparsearray);
        bundle.putSparseParcelableArray("android:menu:icon", sparsearray);
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl menuitemimpl)
    {
        boolean flag;
        if(mMenu.getNonActionItems().size() == mMaxItems && i < mMaxItems)
            i = 1;
        else
        if(i < mMaxItems - 1)
            i = 1;
        else
            i = 0;
        if(i != 0)
            flag = menuitemimpl.isActionButton() ^ true;
        else
            flag = false;
        return flag;
    }

    public void updateMenuView(boolean flag)
    {
        IconMenuView iconmenuview = (IconMenuView)mMenuView;
        if(mMaxItems < 0)
            mMaxItems = iconmenuview.getMaxItems();
        ArrayList arraylist = mMenu.getNonActionItems();
        int i;
        if(arraylist.size() > mMaxItems)
            i = 1;
        else
            i = 0;
        super.updateMenuView(flag);
        if(i != 0 && (mMoreView == null || mMoreView.getParent() != iconmenuview))
        {
            if(mMoreView == null)
            {
                mMoreView = iconmenuview.createMoreItemView();
                mMoreView.setBackgroundDrawable(iconmenuview.getItemBackgroundDrawable());
            }
            iconmenuview.addView(mMoreView);
        } else
        if(i == 0 && mMoreView != null)
            iconmenuview.removeView(mMoreView);
        if(i != 0)
            i = mMaxItems - 1;
        else
            i = arraylist.size();
        iconmenuview.setNumActualItemsShown(i);
    }

    private static final String OPEN_SUBMENU_KEY = "android:menu:icon:submenu";
    private static final String VIEWS_TAG = "android:menu:icon";
    private int mMaxItems;
    private IconMenuItemView mMoreView;
    MenuDialogHelper mOpenSubMenu;
    int mOpenSubMenuId;
    SubMenuPresenterCallback mSubMenuPresenterCallback;
}
