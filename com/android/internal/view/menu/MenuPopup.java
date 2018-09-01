// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.*;
import android.widget.*;

// Referenced classes of package com.android.internal.view.menu:
//            ShowableListMenu, MenuPresenter, MenuBuilder, MenuAdapter, 
//            MenuItemImpl, MenuView

public abstract class MenuPopup
    implements ShowableListMenu, MenuPresenter, android.widget.AdapterView.OnItemClickListener
{

    public MenuPopup()
    {
    }

    protected static int measureIndividualMenuWidth(ListAdapter listadapter, ViewGroup viewgroup, Context context, int i)
    {
        int j = 0;
        Object obj = null;
        int k = 0;
        int l = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        int i1 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        int j1 = listadapter.getCount();
        int k1 = 0;
        Object obj2 = viewgroup;
        viewgroup = obj;
        while(k1 < j1) 
        {
            int l1 = listadapter.getItemViewType(k1);
            int i2 = k;
            if(l1 != k)
            {
                i2 = l1;
                viewgroup = null;
            }
            Object obj1 = obj2;
            if(obj2 == null)
                obj1 = new FrameLayout(context);
            viewgroup = listadapter.getView(k1, viewgroup, ((ViewGroup) (obj1)));
            viewgroup.measure(l, i1);
            k = viewgroup.getMeasuredWidth();
            if(k >= i)
                return i;
            l1 = j;
            if(k > j)
                l1 = k;
            k1++;
            k = i2;
            j = l1;
            obj2 = obj1;
        }
        return j;
    }

    protected static boolean shouldPreserveIconSpacing(MenuBuilder menubuilder)
    {
        boolean flag = false;
        int i = menubuilder.size();
        int j = 0;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(j < i)
                {
                    MenuItem menuitem = menubuilder.getItem(j);
                    if(!menuitem.isVisible() || menuitem.getIcon() == null)
                        break label0;
                    flag1 = true;
                }
                return flag1;
            }
            j++;
        } while(true);
    }

    protected static MenuAdapter toMenuAdapter(ListAdapter listadapter)
    {
        if(listadapter instanceof HeaderViewListAdapter)
            return (MenuAdapter)((HeaderViewListAdapter)listadapter).getWrappedAdapter();
        else
            return (MenuAdapter)listadapter;
    }

    public abstract void addMenu(MenuBuilder menubuilder);

    public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public Rect getEpicenterBounds()
    {
        return mEpicenterBounds;
    }

    public int getId()
    {
        return 0;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        throw new UnsupportedOperationException("MenuPopups manage their own views");
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        adapterview = (ListAdapter)adapterview.getAdapter();
        toMenuAdapter(adapterview).mAdapterMenu.performItemAction((MenuItem)adapterview.getItem(i), 0);
    }

    public abstract void setAnchorView(View view);

    public void setEpicenterBounds(Rect rect)
    {
        mEpicenterBounds = rect;
    }

    public abstract void setForceShowIcon(boolean flag);

    public abstract void setGravity(int i);

    public abstract void setHorizontalOffset(int i);

    public abstract void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener);

    public abstract void setShowTitle(boolean flag);

    public abstract void setVerticalOffset(int i);

    private Rect mEpicenterBounds;
}
