// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.view.menu:
//            MenuPresenter, ExpandedMenuView, MenuBuilder, SubMenuBuilder, 
//            MenuDialogHelper, MenuItemImpl, MenuView

public class ListMenuPresenter
    implements MenuPresenter, android.widget.AdapterView.OnItemClickListener
{
    private class MenuAdapter extends BaseAdapter
    {

        void findExpandedIndex()
        {
            MenuItemImpl menuitemimpl = mMenu.getExpandedItem();
            if(menuitemimpl != null)
            {
                ArrayList arraylist = mMenu.getNonActionItems();
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

        public int getCount()
        {
            int i = mMenu.getNonActionItems().size() - ListMenuPresenter._2D_get0(ListMenuPresenter.this);
            if(mExpandedIndex < 0)
                return i;
            else
                return i - 1;
        }

        public MenuItemImpl getItem(int i)
        {
            ArrayList arraylist = mMenu.getNonActionItems();
            int j = i + ListMenuPresenter._2D_get0(ListMenuPresenter.this);
            i = j;
            if(mExpandedIndex >= 0)
            {
                i = j;
                if(j >= mExpandedIndex)
                    i = j + 1;
            }
            return (MenuItemImpl)arraylist.get(i);
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
                view1 = mInflater.inflate(mItemLayoutRes, viewgroup, false);
            ((MenuView.ItemView)view1).initialize(getItem(i), 0);
            return view1;
        }

        public void notifyDataSetChanged()
        {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }

        private int mExpandedIndex;
        final ListMenuPresenter this$0;

        public MenuAdapter()
        {
            this$0 = ListMenuPresenter.this;
            super();
            mExpandedIndex = -1;
            findExpandedIndex();
        }
    }


    static int _2D_get0(ListMenuPresenter listmenupresenter)
    {
        return listmenupresenter.mItemIndexOffset;
    }

    public ListMenuPresenter(int i, int j)
    {
        mItemLayoutRes = i;
        mThemeRes = j;
    }

    public ListMenuPresenter(Context context, int i)
    {
        this(i, 0);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public ListAdapter getAdapter()
    {
        if(mAdapter == null)
            mAdapter = new MenuAdapter();
        return mAdapter;
    }

    public int getId()
    {
        return mId;
    }

    int getItemIndexOffset()
    {
        return mItemIndexOffset;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        if(mMenuView == null)
        {
            mMenuView = (ExpandedMenuView)mInflater.inflate(0x109005d, viewgroup, false);
            if(mAdapter == null)
                mAdapter = new MenuAdapter();
            mMenuView.setAdapter(mAdapter);
            mMenuView.setOnItemClickListener(this);
        }
        return mMenuView;
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
        if(mThemeRes == 0) goto _L2; else goto _L1
_L1:
        mContext = new ContextThemeWrapper(context, mThemeRes);
        mInflater = LayoutInflater.from(mContext);
_L4:
        mMenu = menubuilder;
        if(mAdapter != null)
            mAdapter.notifyDataSetChanged();
        return;
_L2:
        if(mContext != null)
        {
            mContext = context;
            if(mInflater == null)
                mInflater = LayoutInflater.from(mContext);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if(mCallback != null)
            mCallback.onCloseMenu(menubuilder, flag);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        mMenu.performItemAction(mAdapter.getItem(i), this, 0);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        restoreHierarchyState((Bundle)parcelable);
    }

    public Parcelable onSaveInstanceState()
    {
        if(mMenuView == null)
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            saveHierarchyState(bundle);
            return bundle;
        }
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        if(!submenubuilder.hasVisibleItems())
            return false;
        (new MenuDialogHelper(submenubuilder)).show(null);
        if(mCallback != null)
            mCallback.onOpenSubMenu(submenubuilder);
        return true;
    }

    public void restoreHierarchyState(Bundle bundle)
    {
        bundle = bundle.getSparseParcelableArray("android:menu:list");
        if(bundle != null)
            mMenuView.restoreHierarchyState(bundle);
    }

    public void saveHierarchyState(Bundle bundle)
    {
        SparseArray sparsearray = new SparseArray();
        if(mMenuView != null)
            mMenuView.saveHierarchyState(sparsearray);
        bundle.putSparseParcelableArray("android:menu:list", sparsearray);
    }

    public void setCallback(MenuPresenter.Callback callback)
    {
        mCallback = callback;
    }

    public void setId(int i)
    {
        mId = i;
    }

    public void setItemIndexOffset(int i)
    {
        mItemIndexOffset = i;
        if(mMenuView != null)
            updateMenuView(false);
    }

    public void updateMenuView(boolean flag)
    {
        if(mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    private static final String TAG = "ListMenuPresenter";
    public static final String VIEWS_TAG = "android:menu:list";
    MenuAdapter mAdapter;
    private MenuPresenter.Callback mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    private int mItemIndexOffset;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes;
}
