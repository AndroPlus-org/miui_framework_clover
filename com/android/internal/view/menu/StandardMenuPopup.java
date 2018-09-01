// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.android.internal.util.Preconditions;

// Referenced classes of package com.android.internal.view.menu:
//            MenuPopup, MenuPresenter, MenuAdapter, MenuBuilder, 
//            SubMenuBuilder, MenuPopupHelper

final class StandardMenuPopup extends MenuPopup
    implements android.widget.PopupWindow.OnDismissListener, android.widget.AdapterView.OnItemClickListener, MenuPresenter, android.view.View.OnKeyListener
{

    static android.view.ViewTreeObserver.OnGlobalLayoutListener _2D_get0(StandardMenuPopup standardmenupopup)
    {
        return standardmenupopup.mGlobalLayoutListener;
    }

    static MenuPopupWindow _2D_get1(StandardMenuPopup standardmenupopup)
    {
        return standardmenupopup.mPopup;
    }

    static View _2D_get2(StandardMenuPopup standardmenupopup)
    {
        return standardmenupopup.mShownAnchorView;
    }

    static ViewTreeObserver _2D_get3(StandardMenuPopup standardmenupopup)
    {
        return standardmenupopup.mTreeObserver;
    }

    static ViewTreeObserver _2D_set0(StandardMenuPopup standardmenupopup, ViewTreeObserver viewtreeobserver)
    {
        standardmenupopup.mTreeObserver = viewtreeobserver;
        return viewtreeobserver;
    }

    public StandardMenuPopup(Context context, MenuBuilder menubuilder, View view, int i, int j, boolean flag)
    {
        mDropDownGravity = 0;
        mContext = (Context)Preconditions.checkNotNull(context);
        mMenu = menubuilder;
        mOverflowOnly = flag;
        mAdapter = new MenuAdapter(menubuilder, LayoutInflater.from(context), mOverflowOnly);
        mPopupStyleAttr = i;
        mPopupStyleRes = j;
        Resources resources = context.getResources();
        mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(0x1050041));
        mAnchorView = view;
        mPopup = new MenuPopupWindow(mContext, null, mPopupStyleAttr, mPopupStyleRes);
        menubuilder.addMenuPresenter(this, context);
    }

    private boolean tryShow()
    {
        if(isShowing())
            return true;
        if(mWasDismissed || mAnchorView == null)
            return false;
        mShownAnchorView = mAnchorView;
        mPopup.setOnDismissListener(this);
        mPopup.setOnItemClickListener(this);
        mPopup.setAdapter(mAdapter);
        mPopup.setModal(true);
        Object obj = mShownAnchorView;
        boolean flag;
        if(mTreeObserver == null)
            flag = true;
        else
            flag = false;
        mTreeObserver = ((View) (obj)).getViewTreeObserver();
        if(flag)
            mTreeObserver.addOnGlobalLayoutListener(mGlobalLayoutListener);
        ((View) (obj)).addOnAttachStateChangeListener(mAttachStateChangeListener);
        mPopup.setAnchorView(((View) (obj)));
        mPopup.setDropDownGravity(mDropDownGravity);
        if(!mHasContentWidth)
        {
            mContentWidth = measureIndividualMenuWidth(mAdapter, null, mContext, mPopupMaxWidth);
            mHasContentWidth = true;
        }
        mPopup.setContentWidth(mContentWidth);
        mPopup.setInputMethodMode(2);
        mPopup.setEpicenterBounds(getEpicenterBounds());
        mPopup.show();
        obj = mPopup.getListView();
        ((ListView) (obj)).setOnKeyListener(this);
        if(mShowTitle && mMenu.getHeaderTitle() != null)
        {
            FrameLayout framelayout = (FrameLayout)LayoutInflater.from(mContext).inflate(0x10900b4, ((android.view.ViewGroup) (obj)), false);
            TextView textview = (TextView)framelayout.findViewById(0x1020016);
            if(textview != null)
                textview.setText(mMenu.getHeaderTitle());
            framelayout.setEnabled(false);
            ((ListView) (obj)).addHeaderView(framelayout, null, false);
            mPopup.show();
        }
        return true;
    }

    public void addMenu(MenuBuilder menubuilder)
    {
    }

    public void dismiss()
    {
        if(isShowing())
            mPopup.dismiss();
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public ListView getListView()
    {
        return mPopup.getListView();
    }

    public boolean isShowing()
    {
        boolean flag;
        if(!mWasDismissed)
            flag = mPopup.isShowing();
        else
            flag = false;
        return flag;
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if(menubuilder != mMenu)
            return;
        dismiss();
        if(mPresenterCallback != null)
            mPresenterCallback.onCloseMenu(menubuilder, flag);
    }

    public void onDismiss()
    {
        mWasDismissed = true;
        mMenu.close();
        if(mTreeObserver != null)
        {
            if(!mTreeObserver.isAlive())
                mTreeObserver = mShownAnchorView.getViewTreeObserver();
            mTreeObserver.removeGlobalOnLayoutListener(mGlobalLayoutListener);
            mTreeObserver = null;
        }
        mShownAnchorView.removeOnAttachStateChangeListener(mAttachStateChangeListener);
        if(mOnDismissListener != null)
            mOnDismissListener.onDismiss();
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if(keyevent.getAction() == 1 && i == 82)
        {
            dismiss();
            return true;
        } else
        {
            return false;
        }
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
        if(submenubuilder.hasVisibleItems())
        {
            MenuPopupHelper menupopuphelper = new MenuPopupHelper(mContext, submenubuilder, mShownAnchorView, mOverflowOnly, mPopupStyleAttr, mPopupStyleRes);
            menupopuphelper.setPresenterCallback(mPresenterCallback);
            menupopuphelper.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(submenubuilder));
            menupopuphelper.setGravity(mDropDownGravity);
            menupopuphelper.setOnDismissListener(mOnDismissListener);
            mOnDismissListener = null;
            mMenu.close(false);
            if(menupopuphelper.tryShow(mPopup.getHorizontalOffset(), mPopup.getVerticalOffset()))
            {
                if(mPresenterCallback != null)
                    mPresenterCallback.onOpenSubMenu(submenubuilder);
                return true;
            }
        }
        return false;
    }

    public void setAnchorView(View view)
    {
        mAnchorView = view;
    }

    public void setCallback(MenuPresenter.Callback callback)
    {
        mPresenterCallback = callback;
    }

    public void setForceShowIcon(boolean flag)
    {
        mAdapter.setForceShowIcon(flag);
    }

    public void setGravity(int i)
    {
        mDropDownGravity = i;
    }

    public void setHorizontalOffset(int i)
    {
        mPopup.setHorizontalOffset(i);
    }

    public void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public void setShowTitle(boolean flag)
    {
        mShowTitle = flag;
    }

    public void setVerticalOffset(int i)
    {
        mPopup.setVerticalOffset(i);
    }

    public void show()
    {
        if(!tryShow())
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        else
            return;
    }

    public void updateMenuView(boolean flag)
    {
        mHasContentWidth = false;
        if(mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private final android.view.View.OnAttachStateChangeListener mAttachStateChangeListener = new android.view.View.OnAttachStateChangeListener() {

        public void onViewAttachedToWindow(View view1)
        {
        }

        public void onViewDetachedFromWindow(View view1)
        {
            if(StandardMenuPopup._2D_get3(StandardMenuPopup.this) != null)
            {
                if(!StandardMenuPopup._2D_get3(StandardMenuPopup.this).isAlive())
                    StandardMenuPopup._2D_set0(StandardMenuPopup.this, view1.getViewTreeObserver());
                StandardMenuPopup._2D_get3(StandardMenuPopup.this).removeGlobalOnLayoutListener(StandardMenuPopup._2D_get0(StandardMenuPopup.this));
            }
            view1.removeOnAttachStateChangeListener(this);
        }

        final StandardMenuPopup this$0;

            
            {
                this$0 = StandardMenuPopup.this;
                super();
            }
    }
;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    private final android.view.ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

        public void onGlobalLayout()
        {
            if(isShowing() && StandardMenuPopup._2D_get1(StandardMenuPopup.this).isModal() ^ true)
            {
                View view1 = StandardMenuPopup._2D_get2(StandardMenuPopup.this);
                if(view1 == null || view1.isShown() ^ true)
                    dismiss();
                else
                    StandardMenuPopup._2D_get1(StandardMenuPopup.this).show();
            }
        }

        final StandardMenuPopup this$0;

            
            {
                this$0 = StandardMenuPopup.this;
                super();
            }
    }
;
    private boolean mHasContentWidth;
    private final MenuBuilder mMenu;
    private android.widget.PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final MenuPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private boolean mShowTitle;
    private View mShownAnchorView;
    private ViewTreeObserver mTreeObserver;
    private boolean mWasDismissed;
}
