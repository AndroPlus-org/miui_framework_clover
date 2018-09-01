// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.view.*;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.view.menu:
//            MenuPresenter, MenuView, MenuBuilder, MenuItemImpl, 
//            SubMenuBuilder

public abstract class BaseMenuPresenter
    implements MenuPresenter
{

    public BaseMenuPresenter(Context context, int i, int j)
    {
        mSystemContext = context;
        mSystemInflater = LayoutInflater.from(context);
        mMenuLayoutRes = i;
        mItemLayoutRes = j;
    }

    protected void addItemView(View view, int i)
    {
        ViewGroup viewgroup = (ViewGroup)view.getParent();
        if(viewgroup != null)
            viewgroup.removeView(view);
        ((ViewGroup)mMenuView).addView(view, i);
    }

    public abstract void bindItemView(MenuItemImpl menuitemimpl, MenuView.ItemView itemview);

    public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public MenuView.ItemView createItemView(ViewGroup viewgroup)
    {
        return (MenuView.ItemView)mSystemInflater.inflate(mItemLayoutRes, viewgroup, false);
    }

    public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    protected boolean filterLeftoverView(ViewGroup viewgroup, int i)
    {
        viewgroup.removeViewAt(i);
        return true;
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public MenuPresenter.Callback getCallback()
    {
        return mCallback;
    }

    public int getId()
    {
        return mId;
    }

    public View getItemView(MenuItemImpl menuitemimpl, View view, ViewGroup viewgroup)
    {
        if(view instanceof MenuView.ItemView)
            view = (MenuView.ItemView)view;
        else
            view = createItemView(viewgroup);
        bindItemView(menuitemimpl, view);
        return (View)view;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        if(mMenuView == null)
        {
            mMenuView = (MenuView)mSystemInflater.inflate(mMenuLayoutRes, viewgroup, false);
            mMenuView.initialize(mMenu);
            updateMenuView(true);
        }
        return mMenuView;
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mMenu = menubuilder;
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if(mCallback != null)
            mCallback.onCloseMenu(menubuilder, flag);
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        if(mCallback != null)
            return mCallback.onOpenSubMenu(submenubuilder);
        else
            return false;
    }

    public void setCallback(MenuPresenter.Callback callback)
    {
        mCallback = callback;
    }

    public void setId(int i)
    {
        mId = i;
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl menuitemimpl)
    {
        return true;
    }

    public void updateMenuView(boolean flag)
    {
        ViewGroup viewgroup = (ViewGroup)mMenuView;
        if(viewgroup == null)
            return;
        int i = 0;
        int j = 0;
        if(mMenu != null)
        {
            mMenu.flagActionItems();
            ArrayList arraylist = mMenu.getVisibleItems();
            int k = arraylist.size();
            int l = 0;
            do
            {
                i = j;
                if(l >= k)
                    break;
                MenuItemImpl menuitemimpl = (MenuItemImpl)arraylist.get(l);
                i = j;
                if(shouldIncludeItem(j, menuitemimpl))
                {
                    View view = viewgroup.getChildAt(j);
                    MenuItemImpl menuitemimpl1;
                    View view1;
                    if(view instanceof MenuView.ItemView)
                        menuitemimpl1 = ((MenuView.ItemView)view).getItemData();
                    else
                        menuitemimpl1 = null;
                    view1 = getItemView(menuitemimpl, view, viewgroup);
                    if(menuitemimpl != menuitemimpl1)
                    {
                        view1.setPressed(false);
                        view1.jumpDrawablesToCurrentState();
                    }
                    if(view1 != view)
                        addItemView(view1, j);
                    i = j + 1;
                }
                l++;
                j = i;
            } while(true);
        }
        do
        {
            if(i >= viewgroup.getChildCount())
                break;
            if(!filterLeftoverView(viewgroup, i))
                i++;
        } while(true);
    }

    private MenuPresenter.Callback mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;
}
