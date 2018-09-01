// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.transition.Transition;
import android.util.AttributeSet;
import android.view.*;
import com.android.internal.view.menu.*;

// Referenced classes of package android.widget:
//            ListPopupWindow, MenuItemHoverListener, PopupWindow, DropDownListView, 
//            HeaderViewListAdapter

public class MenuPopupWindow extends ListPopupWindow
    implements MenuItemHoverListener
{
    public static class MenuDropDownListView extends DropDownListView
    {

        public void clearSelection()
        {
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
        }

        public boolean onHoverEvent(MotionEvent motionevent)
        {
            if(mHoverListener != null)
            {
                Object obj = getAdapter();
                int i;
                MenuItem menuitem;
                MenuItemImpl menuitemimpl;
                if(obj instanceof HeaderViewListAdapter)
                {
                    obj = (HeaderViewListAdapter)obj;
                    i = ((HeaderViewListAdapter) (obj)).getHeadersCount();
                    obj = (MenuAdapter)((HeaderViewListAdapter) (obj)).getWrappedAdapter();
                } else
                {
                    i = 0;
                    obj = (MenuAdapter)obj;
                }
                menuitem = null;
                menuitemimpl = menuitem;
                if(motionevent.getAction() != 10)
                {
                    int j = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
                    menuitemimpl = menuitem;
                    if(j != -1)
                    {
                        i = j - i;
                        menuitemimpl = menuitem;
                        if(i >= 0)
                        {
                            menuitemimpl = menuitem;
                            if(i < ((MenuAdapter) (obj)).getCount())
                                menuitemimpl = ((MenuAdapter) (obj)).getItem(i);
                        }
                    }
                }
                menuitem = mHoveredMenuItem;
                if(menuitem != menuitemimpl)
                {
                    obj = ((MenuAdapter) (obj)).getAdapterMenu();
                    if(menuitem != null)
                        mHoverListener.onItemHoverExit(((MenuBuilder) (obj)), menuitem);
                    mHoveredMenuItem = menuitemimpl;
                    if(menuitemimpl != null)
                        mHoverListener.onItemHoverEnter(((MenuBuilder) (obj)), menuitemimpl);
                }
            }
            return super.onHoverEvent(motionevent);
        }

        public boolean onKeyDown(int i, KeyEvent keyevent)
        {
            ListMenuItemView listmenuitemview = (ListMenuItemView)getSelectedView();
            if(listmenuitemview != null && i == mAdvanceKey)
            {
                if(listmenuitemview.isEnabled() && listmenuitemview.getItemData().hasSubMenu())
                    performItemClick(listmenuitemview, getSelectedItemPosition(), getSelectedItemId());
                return true;
            }
            if(listmenuitemview != null && i == mRetreatKey)
            {
                setSelectedPositionInt(-1);
                setNextSelectedPositionInt(-1);
                ((MenuAdapter)getAdapter()).getAdapterMenu().close(false);
                return true;
            } else
            {
                return super.onKeyDown(i, keyevent);
            }
        }

        public void setHoverListener(MenuItemHoverListener menuitemhoverlistener)
        {
            mHoverListener = menuitemhoverlistener;
        }

        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public MenuDropDownListView(Context context, boolean flag)
        {
            super(context, flag);
            if(context.getResources().getConfiguration().getLayoutDirection() == 1)
            {
                mAdvanceKey = 21;
                mRetreatKey = 22;
            } else
            {
                mAdvanceKey = 22;
                mRetreatKey = 21;
            }
        }
    }


    public MenuPopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    DropDownListView createDropDownListView(Context context, boolean flag)
    {
        context = new MenuDropDownListView(context, flag);
        context.setHoverListener(this);
        return context;
    }

    public void onItemHoverEnter(MenuBuilder menubuilder, MenuItem menuitem)
    {
        if(mHoverListener != null)
            mHoverListener.onItemHoverEnter(menubuilder, menuitem);
    }

    public void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
    {
        if(mHoverListener != null)
            mHoverListener.onItemHoverExit(menubuilder, menuitem);
    }

    public void setEnterTransition(Transition transition)
    {
        mPopup.setEnterTransition(transition);
    }

    public void setExitTransition(Transition transition)
    {
        mPopup.setExitTransition(transition);
    }

    public void setHoverListener(MenuItemHoverListener menuitemhoverlistener)
    {
        mHoverListener = menuitemhoverlistener;
    }

    public void setTouchModal(boolean flag)
    {
        mPopup.setTouchModal(flag);
    }

    private MenuItemHoverListener mHoverListener;
}
