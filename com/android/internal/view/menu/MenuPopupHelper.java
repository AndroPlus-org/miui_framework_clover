// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.*;

// Referenced classes of package com.android.internal.view.menu:
//            MenuHelper, CascadingMenuPopup, MenuPopup, StandardMenuPopup, 
//            MenuBuilder

public class MenuPopupHelper
    implements MenuHelper
{

    public MenuPopupHelper(Context context, MenuBuilder menubuilder)
    {
        this(context, menubuilder, null, false, 0x1010300, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menubuilder, View view)
    {
        this(context, menubuilder, view, false, 0x1010300, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menubuilder, View view, boolean flag, int i)
    {
        this(context, menubuilder, view, flag, i, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menubuilder, View view, boolean flag, int i, int j)
    {
        mDropDownGravity = 0x800003;
        mInternalOnDismissListener = new android.widget.PopupWindow.OnDismissListener() {

            public void onDismiss()
            {
                MenuPopupHelper.this.onDismiss();
            }

            final MenuPopupHelper this$0;

            
            {
                this$0 = MenuPopupHelper.this;
                super();
            }
        }
;
        mContext = context;
        mMenu = menubuilder;
        mAnchorView = view;
        mOverflowOnly = flag;
        mPopupStyleAttr = i;
        mPopupStyleRes = j;
    }

    private MenuPopup createPopup()
    {
        Display display = ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay();
        Object obj = new Point();
        display.getRealSize(((Point) (obj)));
        boolean flag;
        if(Math.min(((Point) (obj)).x, ((Point) (obj)).y) >= mContext.getResources().getDimensionPixelSize(0x1050030))
            flag = true;
        else
            flag = false;
        if(flag)
            obj = new CascadingMenuPopup(mContext, mAnchorView, mPopupStyleAttr, mPopupStyleRes, mOverflowOnly);
        else
            obj = new StandardMenuPopup(mContext, mMenu, mAnchorView, mPopupStyleAttr, mPopupStyleRes, mOverflowOnly);
        ((MenuPopup) (obj)).addMenu(mMenu);
        ((MenuPopup) (obj)).setOnDismissListener(mInternalOnDismissListener);
        ((MenuPopup) (obj)).setAnchorView(mAnchorView);
        ((MenuPopup) (obj)).setCallback(mPresenterCallback);
        ((MenuPopup) (obj)).setForceShowIcon(mForceShowIcon);
        ((MenuPopup) (obj)).setGravity(mDropDownGravity);
        return ((MenuPopup) (obj));
    }

    private void showPopup(int i, int j, boolean flag, boolean flag1)
    {
        MenuPopup menupopup = getPopup();
        menupopup.setShowTitle(flag1);
        if(flag)
        {
            int k = i;
            if((Gravity.getAbsoluteGravity(mDropDownGravity, mAnchorView.getLayoutDirection()) & 7) == 5)
                k = i + mAnchorView.getWidth();
            menupopup.setHorizontalOffset(k);
            menupopup.setVerticalOffset(j);
            i = (int)((48F * mContext.getResources().getDisplayMetrics().density) / 2.0F);
            menupopup.setEpicenterBounds(new Rect(k - i, j - i, k + i, j + i));
        }
        menupopup.show();
    }

    public void dismiss()
    {
        if(isShowing())
            mPopup.dismiss();
    }

    public int getGravity()
    {
        return mDropDownGravity;
    }

    public MenuPopup getPopup()
    {
        if(mPopup == null)
            mPopup = createPopup();
        return mPopup;
    }

    public boolean isShowing()
    {
        boolean flag;
        if(mPopup != null)
            flag = mPopup.isShowing();
        else
            flag = false;
        return flag;
    }

    protected void onDismiss()
    {
        mPopup = null;
        if(mOnDismissListener != null)
            mOnDismissListener.onDismiss();
    }

    public void setAnchorView(View view)
    {
        mAnchorView = view;
    }

    public void setForceShowIcon(boolean flag)
    {
        mForceShowIcon = flag;
        if(mPopup != null)
            mPopup.setForceShowIcon(flag);
    }

    public void setGravity(int i)
    {
        mDropDownGravity = i;
    }

    public void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public void setPresenterCallback(MenuPresenter.Callback callback)
    {
        mPresenterCallback = callback;
        if(mPopup != null)
            mPopup.setCallback(callback);
    }

    public void show()
    {
        if(!tryShow())
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        else
            return;
    }

    public void show(int i, int j)
    {
        if(!tryShow(i, j))
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        else
            return;
    }

    public boolean tryShow()
    {
        if(isShowing())
            return true;
        if(mAnchorView == null)
        {
            return false;
        } else
        {
            showPopup(0, 0, false, false);
            return true;
        }
    }

    public boolean tryShow(int i, int j)
    {
        if(isShowing())
            return true;
        if(mAnchorView == null)
        {
            return false;
        } else
        {
            showPopup(i, j, true, true);
            return true;
        }
    }

    private static final int TOUCH_EPICENTER_SIZE_DP = 48;
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final android.widget.PopupWindow.OnDismissListener mInternalOnDismissListener;
    private final MenuBuilder mMenu;
    private android.widget.PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private MenuPopup mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
}
