// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.view.*;
import com.android.internal.view.menu.*;

// Referenced classes of package android.widget:
//            ListView, ForwardingListener

public class PopupMenu
{
    public static interface OnDismissListener
    {

        public abstract void onDismiss(PopupMenu popupmenu);
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
    }


    static OnMenuItemClickListener _2D_get0(PopupMenu popupmenu)
    {
        return popupmenu.mMenuItemClickListener;
    }

    static OnDismissListener _2D_get1(PopupMenu popupmenu)
    {
        return popupmenu.mOnDismissListener;
    }

    static MenuPopupHelper _2D_get2(PopupMenu popupmenu)
    {
        return popupmenu.mPopup;
    }

    public PopupMenu(Context context, View view)
    {
        this(context, view, 0);
    }

    public PopupMenu(Context context, View view, int i)
    {
        this(context, view, i, 0x1010300, 0);
    }

    public PopupMenu(Context context, View view, int i, int j, int k)
    {
        mContext = context;
        mAnchor = view;
        mMenu = new MenuBuilder(context);
        mMenu.setCallback(new com.android.internal.view.menu.MenuBuilder.Callback() {

            public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
            {
                if(PopupMenu._2D_get0(PopupMenu.this) != null)
                    return PopupMenu._2D_get0(PopupMenu.this).onMenuItemClick(menuitem);
                else
                    return false;
            }

            public void onMenuModeChange(MenuBuilder menubuilder)
            {
            }

            final PopupMenu this$0;

            
            {
                this$0 = PopupMenu.this;
                super();
            }
        }
);
        mPopup = new MenuPopupHelper(context, mMenu, view, false, j, k);
        mPopup.setGravity(i);
        mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {

            public void onDismiss()
            {
                if(PopupMenu._2D_get1(PopupMenu.this) != null)
                    PopupMenu._2D_get1(PopupMenu.this).onDismiss(PopupMenu.this);
            }

            final PopupMenu this$0;

            
            {
                this$0 = PopupMenu.this;
                super();
            }
        }
);
    }

    public void dismiss()
    {
        mPopup.dismiss();
    }

    public android.view.View.OnTouchListener getDragToOpenListener()
    {
        if(mDragListener == null)
            mDragListener = new ForwardingListener(mAnchor) {

                public ShowableListMenu getPopup()
                {
                    return PopupMenu._2D_get2(PopupMenu.this).getPopup();
                }

                protected boolean onForwardingStarted()
                {
                    show();
                    return true;
                }

                protected boolean onForwardingStopped()
                {
                    dismiss();
                    return true;
                }

                final PopupMenu this$0;

            
            {
                this$0 = PopupMenu.this;
                super(view);
            }
            }
;
        return mDragListener;
    }

    public int getGravity()
    {
        return mPopup.getGravity();
    }

    public Menu getMenu()
    {
        return mMenu;
    }

    public MenuInflater getMenuInflater()
    {
        return new MenuInflater(mContext);
    }

    public ListView getMenuListView()
    {
        if(!mPopup.isShowing())
            return null;
        else
            return mPopup.getPopup().getListView();
    }

    public void inflate(int i)
    {
        getMenuInflater().inflate(i, mMenu);
    }

    public void setGravity(int i)
    {
        mPopup.setGravity(i);
    }

    public void setOnDismissListener(OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onmenuitemclicklistener)
    {
        mMenuItemClickListener = onmenuitemclicklistener;
    }

    public void show()
    {
        mPopup.show();
    }

    private final View mAnchor;
    private final Context mContext;
    private android.view.View.OnTouchListener mDragListener;
    private final MenuBuilder mMenu;
    private OnMenuItemClickListener mMenuItemClickListener;
    private OnDismissListener mOnDismissListener;
    private final MenuPopupHelper mPopup;
}
