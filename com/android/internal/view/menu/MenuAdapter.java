// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.view.*;
import android.widget.BaseAdapter;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.view.menu:
//            MenuBuilder, MenuItemImpl, ListMenuItemView

public class MenuAdapter extends BaseAdapter
{

    public MenuAdapter(MenuBuilder menubuilder, LayoutInflater layoutinflater, boolean flag)
    {
        mExpandedIndex = -1;
        mOverflowOnly = flag;
        mInflater = layoutinflater;
        mAdapterMenu = menubuilder;
        findExpandedIndex();
    }

    void findExpandedIndex()
    {
        MenuItemImpl menuitemimpl = mAdapterMenu.getExpandedItem();
        if(menuitemimpl != null)
        {
            ArrayList arraylist = mAdapterMenu.getNonActionItems();
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                if((MenuItemImpl)arraylist.get(j) == menuitemimpl)
                {
                    mExpandedIndex = j;
                    return;
                }

        }
        mExpandedIndex = -1;
    }

    public MenuBuilder getAdapterMenu()
    {
        return mAdapterMenu;
    }

    public int getCount()
    {
        ArrayList arraylist;
        if(mOverflowOnly)
            arraylist = mAdapterMenu.getNonActionItems();
        else
            arraylist = mAdapterMenu.getVisibleItems();
        if(mExpandedIndex < 0)
            return arraylist.size();
        else
            return arraylist.size() - 1;
    }

    public boolean getForceShowIcon()
    {
        return mForceShowIcon;
    }

    public MenuItemImpl getItem(int i)
    {
        ArrayList arraylist;
        int j;
        if(mOverflowOnly)
            arraylist = mAdapterMenu.getNonActionItems();
        else
            arraylist = mAdapterMenu.getVisibleItems();
        j = i;
        if(mExpandedIndex >= 0)
        {
            j = i;
            if(i >= mExpandedIndex)
                j = i + 1;
        }
        return (MenuItemImpl)arraylist.get(j);
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = view;
        if(view == null)
            view1 = mInflater.inflate(0x10900b5, viewgroup, false);
        view = (MenuView.ItemView)view1;
        if(mForceShowIcon)
            ((ListMenuItemView)view1).setForceShowIcon(true);
        view.initialize(getItem(i), 0);
        return view1;
    }

    public void notifyDataSetChanged()
    {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public void setForceShowIcon(boolean flag)
    {
        mForceShowIcon = flag;
    }

    static final int ITEM_LAYOUT = 0x10900b5;
    MenuBuilder mAdapterMenu;
    private int mExpandedIndex;
    private boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final boolean mOverflowOnly;
}
