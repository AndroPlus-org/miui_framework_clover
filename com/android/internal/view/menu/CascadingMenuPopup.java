// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.*;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package com.android.internal.view.menu:
//            MenuPopup, MenuPresenter, MenuBuilder, MenuAdapter, 
//            SubMenuBuilder

final class CascadingMenuPopup extends MenuPopup
    implements MenuPresenter, android.view.View.OnKeyListener, android.widget.PopupWindow.OnDismissListener
{
    private static class CascadingMenuInfo
    {

        public ListView getListView()
        {
            return window.getListView();
        }

        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(MenuPopupWindow menupopupwindow, MenuBuilder menubuilder, int i)
        {
            window = menupopupwindow;
            menu = menubuilder;
            position = i;
        }
    }


    static android.view.ViewTreeObserver.OnGlobalLayoutListener _2D_get0(CascadingMenuPopup cascadingmenupopup)
    {
        return cascadingmenupopup.mGlobalLayoutListener;
    }

    static List _2D_get1(CascadingMenuPopup cascadingmenupopup)
    {
        return cascadingmenupopup.mShowingMenus;
    }

    static View _2D_get2(CascadingMenuPopup cascadingmenupopup)
    {
        return cascadingmenupopup.mShownAnchorView;
    }

    static Handler _2D_get3(CascadingMenuPopup cascadingmenupopup)
    {
        return cascadingmenupopup.mSubMenuHoverHandler;
    }

    static ViewTreeObserver _2D_get4(CascadingMenuPopup cascadingmenupopup)
    {
        return cascadingmenupopup.mTreeObserver;
    }

    static boolean _2D_set0(CascadingMenuPopup cascadingmenupopup, boolean flag)
    {
        cascadingmenupopup.mShouldCloseImmediately = flag;
        return flag;
    }

    static ViewTreeObserver _2D_set1(CascadingMenuPopup cascadingmenupopup, ViewTreeObserver viewtreeobserver)
    {
        cascadingmenupopup.mTreeObserver = viewtreeobserver;
        return viewtreeobserver;
    }

    public CascadingMenuPopup(Context context, View view, int i, int j, boolean flag)
    {
        mRawDropDownGravity = 0;
        mDropDownGravity = 0;
        mContext = (Context)Preconditions.checkNotNull(context);
        mAnchorView = (View)Preconditions.checkNotNull(view);
        mPopupStyleAttr = i;
        mPopupStyleRes = j;
        mOverflowOnly = flag;
        mForceShowIcon = false;
        mLastPosition = getInitialMenuPosition();
        context = context.getResources();
        mMenuMaxWidth = Math.max(context.getDisplayMetrics().widthPixels / 2, context.getDimensionPixelSize(0x1050041));
    }

    private MenuPopupWindow createPopupWindow()
    {
        MenuPopupWindow menupopupwindow = new MenuPopupWindow(mContext, null, mPopupStyleAttr, mPopupStyleRes);
        menupopupwindow.setHoverListener(mMenuItemHoverListener);
        menupopupwindow.setOnItemClickListener(this);
        menupopupwindow.setOnDismissListener(this);
        menupopupwindow.setAnchorView(mAnchorView);
        menupopupwindow.setDropDownGravity(mDropDownGravity);
        menupopupwindow.setModal(true);
        menupopupwindow.setInputMethodMode(2);
        return menupopupwindow;
    }

    private int findIndexOfAddedMenu(MenuBuilder menubuilder)
    {
        int i = 0;
        for(int j = mShowingMenus.size(); i < j; i++)
            if(menubuilder == ((CascadingMenuInfo)mShowingMenus.get(i)).menu)
                return i;

        return -1;
    }

    private MenuItem findMenuItemForSubmenu(MenuBuilder menubuilder, MenuBuilder menubuilder1)
    {
        int i = 0;
        for(int j = menubuilder.size(); i < j; i++)
        {
            MenuItem menuitem = menubuilder.getItem(i);
            if(menuitem.hasSubMenu() && menubuilder1 == menuitem.getSubMenu())
                return menuitem;
        }

        return null;
    }

    private View findParentViewForSubmenu(CascadingMenuInfo cascadingmenuinfo, MenuBuilder menubuilder)
    {
        menubuilder = findMenuItemForSubmenu(cascadingmenuinfo.menu, menubuilder);
        if(menubuilder == null)
            return null;
        ListView listview = cascadingmenuinfo.getListView();
        cascadingmenuinfo = listview.getAdapter();
        int i;
        byte byte0;
        int j;
        int k;
        int l;
        if(cascadingmenuinfo instanceof HeaderViewListAdapter)
        {
            cascadingmenuinfo = (HeaderViewListAdapter)cascadingmenuinfo;
            i = cascadingmenuinfo.getHeadersCount();
            cascadingmenuinfo = (MenuAdapter)cascadingmenuinfo.getWrappedAdapter();
        } else
        {
            i = 0;
            cascadingmenuinfo = (MenuAdapter)cascadingmenuinfo;
        }
        byte0 = -1;
        j = 0;
        k = cascadingmenuinfo.getCount();
label0:
        do
        {
label1:
            {
                l = byte0;
                if(j < k)
                {
                    if(menubuilder != cascadingmenuinfo.getItem(j))
                        break label1;
                    l = j;
                }
                if(l == -1)
                    return null;
                break label0;
            }
            j++;
        } while(true);
        j = (l + i) - listview.getFirstVisiblePosition();
        if(j < 0 || j >= listview.getChildCount())
            return null;
        else
            return listview.getChildAt(j);
    }

    private int getInitialMenuPosition()
    {
        int i = 1;
        if(mAnchorView.getLayoutDirection() == 1)
            i = 0;
        return i;
    }

    private int getNextMenuPosition(int i)
    {
        ListView listview = ((CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1)).getListView();
        int ai[] = new int[2];
        listview.getLocationOnScreen(ai);
        Rect rect = new Rect();
        mShownAnchorView.getWindowVisibleDisplayFrame(rect);
        if(mLastPosition == 1)
            return ai[0] + listview.getWidth() + i <= rect.right ? 1 : 0;
        return ai[0] - i >= 0 ? 0 : 1;
    }

    private void showMenu(MenuBuilder menubuilder)
    {
        Object obj = LayoutInflater.from(mContext);
        Object obj1 = new MenuAdapter(menubuilder, ((LayoutInflater) (obj)), mOverflowOnly);
        int i;
        MenuPopupWindow menupopupwindow;
        Object obj2;
        if(!isShowing() && mForceShowIcon)
            ((MenuAdapter) (obj1)).setForceShowIcon(true);
        else
        if(isShowing())
            ((MenuAdapter) (obj1)).setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(menubuilder));
        i = measureIndividualMenuWidth(((android.widget.ListAdapter) (obj1)), null, mContext, mMenuMaxWidth);
        menupopupwindow = createPopupWindow();
        menupopupwindow.setAdapter(((android.widget.ListAdapter) (obj1)));
        menupopupwindow.setContentWidth(i);
        menupopupwindow.setDropDownGravity(mDropDownGravity);
        if(mShowingMenus.size() > 0)
        {
            obj1 = (CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1);
            obj2 = findParentViewForSubmenu(((CascadingMenuInfo) (obj1)), menubuilder);
        } else
        {
            obj1 = null;
            obj2 = null;
        }
        if(obj2 != null)
        {
            menupopupwindow.setAnchorView(((View) (obj2)));
            menupopupwindow.setTouchModal(false);
            menupopupwindow.setEnterTransition(null);
            int j = getNextMenuPosition(i);
            int k;
            if(j == 1)
                k = 1;
            else
                k = 0;
            mLastPosition = j;
            if((mDropDownGravity & 5) == 5)
            {
                if(k != 0)
                    k = i;
                else
                    k = -((View) (obj2)).getWidth();
            } else
            if(k != 0)
                k = ((View) (obj2)).getWidth();
            else
                k = -i;
            menupopupwindow.setHorizontalOffset(k);
            menupopupwindow.setOverlapAnchor(true);
            menupopupwindow.setVerticalOffset(0);
        } else
        {
            if(mHasXOffset)
                menupopupwindow.setHorizontalOffset(mXOffset);
            if(mHasYOffset)
                menupopupwindow.setVerticalOffset(mYOffset);
            menupopupwindow.setEpicenterBounds(getEpicenterBounds());
        }
        obj2 = new CascadingMenuInfo(menupopupwindow, menubuilder, mLastPosition);
        mShowingMenus.add(obj2);
        menupopupwindow.show();
        obj2 = menupopupwindow.getListView();
        ((ListView) (obj2)).setOnKeyListener(this);
        if(obj1 == null && mShowTitle && menubuilder.getHeaderTitle() != null)
        {
            obj1 = (FrameLayout)((LayoutInflater) (obj)).inflate(0x10900b4, ((android.view.ViewGroup) (obj2)), false);
            obj = (TextView)((FrameLayout) (obj1)).findViewById(0x1020016);
            ((FrameLayout) (obj1)).setEnabled(false);
            ((TextView) (obj)).setText(menubuilder.getHeaderTitle());
            ((ListView) (obj2)).addHeaderView(((View) (obj1)), null, false);
            menupopupwindow.show();
        }
    }

    public void addMenu(MenuBuilder menubuilder)
    {
        menubuilder.addMenuPresenter(this, mContext);
        if(isShowing())
            showMenu(menubuilder);
        else
            mPendingMenus.add(menubuilder);
    }

    public void dismiss()
    {
        int i = mShowingMenus.size();
        if(i > 0)
        {
            CascadingMenuInfo acascadingmenuinfo[] = (CascadingMenuInfo[])mShowingMenus.toArray(new CascadingMenuInfo[i]);
            for(i--; i >= 0; i--)
            {
                CascadingMenuInfo cascadingmenuinfo = acascadingmenuinfo[i];
                if(cascadingmenuinfo.window.isShowing())
                    cascadingmenuinfo.window.dismiss();
            }

        }
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public ListView getListView()
    {
        ListView listview;
        if(mShowingMenus.isEmpty())
            listview = null;
        else
            listview = ((CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1)).getListView();
        return listview;
    }

    public boolean isShowing()
    {
        boolean flag = false;
        if(mShowingMenus.size() > 0)
            flag = ((CascadingMenuInfo)mShowingMenus.get(0)).window.isShowing();
        return flag;
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        int i = findIndexOfAddedMenu(menubuilder);
        if(i < 0)
            return;
        int j = i + 1;
        if(j < mShowingMenus.size())
            ((CascadingMenuInfo)mShowingMenus.get(j)).menu.close(false);
        CascadingMenuInfo cascadingmenuinfo = (CascadingMenuInfo)mShowingMenus.remove(i);
        cascadingmenuinfo.menu.removeMenuPresenter(this);
        if(mShouldCloseImmediately)
        {
            cascadingmenuinfo.window.setExitTransition(null);
            cascadingmenuinfo.window.setAnimationStyle(0);
        }
        cascadingmenuinfo.window.dismiss();
        i = mShowingMenus.size();
        if(i > 0)
            mLastPosition = ((CascadingMenuInfo)mShowingMenus.get(i - 1)).position;
        else
            mLastPosition = getInitialMenuPosition();
        if(i != 0) goto _L2; else goto _L1
_L1:
        dismiss();
        if(mPresenterCallback != null)
            mPresenterCallback.onCloseMenu(menubuilder, true);
        if(mTreeObserver != null)
        {
            if(mTreeObserver.isAlive())
                mTreeObserver.removeGlobalOnLayoutListener(mGlobalLayoutListener);
            mTreeObserver = null;
        }
        mShownAnchorView.removeOnAttachStateChangeListener(mAttachStateChangeListener);
        mOnDismissListener.onDismiss();
_L4:
        return;
_L2:
        if(flag)
            ((CascadingMenuInfo)mShowingMenus.get(0)).menu.close(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDismiss()
    {
        Object obj = null;
        int i = 0;
        int j = mShowingMenus.size();
        do
        {
label0:
            {
                CascadingMenuInfo cascadingmenuinfo = obj;
                if(i < j)
                {
                    cascadingmenuinfo = (CascadingMenuInfo)mShowingMenus.get(i);
                    if(cascadingmenuinfo.window.isShowing())
                        break label0;
                }
                if(cascadingmenuinfo != null)
                    cascadingmenuinfo.menu.close(false);
                return;
            }
            i++;
        } while(true);
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
        for(Iterator iterator = mShowingMenus.iterator(); iterator.hasNext();)
        {
            CascadingMenuInfo cascadingmenuinfo = (CascadingMenuInfo)iterator.next();
            if(submenubuilder == cascadingmenuinfo.menu)
            {
                cascadingmenuinfo.getListView().requestFocus();
                return true;
            }
        }

        if(submenubuilder.hasVisibleItems())
        {
            addMenu(submenubuilder);
            if(mPresenterCallback != null)
                mPresenterCallback.onOpenSubMenu(submenubuilder);
            return true;
        } else
        {
            return false;
        }
    }

    public void setAnchorView(View view)
    {
        if(mAnchorView != view)
        {
            mAnchorView = view;
            mDropDownGravity = Gravity.getAbsoluteGravity(mRawDropDownGravity, mAnchorView.getLayoutDirection());
        }
    }

    public void setCallback(MenuPresenter.Callback callback)
    {
        mPresenterCallback = callback;
    }

    public void setForceShowIcon(boolean flag)
    {
        mForceShowIcon = flag;
    }

    public void setGravity(int i)
    {
        if(mRawDropDownGravity != i)
        {
            mRawDropDownGravity = i;
            mDropDownGravity = Gravity.getAbsoluteGravity(i, mAnchorView.getLayoutDirection());
        }
    }

    public void setHorizontalOffset(int i)
    {
        mHasXOffset = true;
        mXOffset = i;
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
        mHasYOffset = true;
        mYOffset = i;
    }

    public void show()
    {
        if(isShowing())
            return;
        for(Iterator iterator = mPendingMenus.iterator(); iterator.hasNext(); showMenu((MenuBuilder)iterator.next()));
        mPendingMenus.clear();
        mShownAnchorView = mAnchorView;
        if(mShownAnchorView != null)
        {
            boolean flag;
            if(mTreeObserver == null)
                flag = true;
            else
                flag = false;
            mTreeObserver = mShownAnchorView.getViewTreeObserver();
            if(flag)
                mTreeObserver.addOnGlobalLayoutListener(mGlobalLayoutListener);
            mShownAnchorView.addOnAttachStateChangeListener(mAttachStateChangeListener);
        }
    }

    public void updateMenuView(boolean flag)
    {
        for(Iterator iterator = mShowingMenus.iterator(); iterator.hasNext(); toMenuAdapter(((CascadingMenuInfo)iterator.next()).getListView().getAdapter()).notifyDataSetChanged());
    }

    private static final int HORIZ_POSITION_LEFT = 0;
    private static final int HORIZ_POSITION_RIGHT = 1;
    private static final int SUBMENU_TIMEOUT_MS = 200;
    private View mAnchorView;
    private final android.view.View.OnAttachStateChangeListener mAttachStateChangeListener = new android.view.View.OnAttachStateChangeListener() {

        public void onViewAttachedToWindow(View view1)
        {
        }

        public void onViewDetachedFromWindow(View view1)
        {
            if(CascadingMenuPopup._2D_get4(CascadingMenuPopup.this) != null)
            {
                if(!CascadingMenuPopup._2D_get4(CascadingMenuPopup.this).isAlive())
                    CascadingMenuPopup._2D_set1(CascadingMenuPopup.this, view1.getViewTreeObserver());
                CascadingMenuPopup._2D_get4(CascadingMenuPopup.this).removeGlobalOnLayoutListener(CascadingMenuPopup._2D_get0(CascadingMenuPopup.this));
            }
            view1.removeOnAttachStateChangeListener(this);
        }

        final CascadingMenuPopup this$0;

            
            {
                this$0 = CascadingMenuPopup.this;
                super();
            }
    }
;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final android.view.ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

        public void onGlobalLayout()
        {
            if(isShowing() && CascadingMenuPopup._2D_get1(CascadingMenuPopup.this).size() > 0 && ((CascadingMenuInfo)CascadingMenuPopup._2D_get1(CascadingMenuPopup.this).get(0)).window.isModal() ^ true)
            {
                View view1 = CascadingMenuPopup._2D_get2(CascadingMenuPopup.this);
                if(view1 == null || view1.isShown() ^ true)
                {
                    dismiss();
                } else
                {
                    Iterator iterator = CascadingMenuPopup._2D_get1(CascadingMenuPopup.this).iterator();
                    while(iterator.hasNext()) 
                        ((CascadingMenuInfo)iterator.next()).window.show();
                }
            }
        }

        final CascadingMenuPopup this$0;

            
            {
                this$0 = CascadingMenuPopup.this;
                super();
            }
    }
;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener = new MenuItemHoverListener() {

        public void onItemHoverEnter(MenuBuilder menubuilder, final MenuItem item)
        {
            CascadingMenuPopup._2D_get3(CascadingMenuPopup.this).removeCallbacksAndMessages(null);
            byte byte0 = -1;
            int k = 0;
            int l = CascadingMenuPopup._2D_get1(CascadingMenuPopup.this).size();
            int i1;
label0:
            do
            {
label1:
                {
                    i1 = byte0;
                    if(k < l)
                    {
                        if(menubuilder != ((CascadingMenuInfo)CascadingMenuPopup._2D_get1(CascadingMenuPopup.this).get(k)).menu)
                            break label1;
                        i1 = k;
                    }
                    if(i1 == -1)
                        return;
                    break label0;
                }
                k++;
            } while(true);
            k = i1 + 1;
            final CascadingMenuInfo nextInfo;
            long l1;
            if(k < CascadingMenuPopup._2D_get1(CascadingMenuPopup.this).size())
                nextInfo = (CascadingMenuInfo)CascadingMenuPopup._2D_get1(CascadingMenuPopup.this).get(k);
            else
                nextInfo = null;
            item = menubuilder. new Runnable() {

                public void run()
                {
                    if(nextInfo != null)
                    {
                        CascadingMenuPopup._2D_set0(_fld0, true);
                        nextInfo.menu.close(false);
                        CascadingMenuPopup._2D_set0(_fld0, false);
                    }
                    if(item.isEnabled() && item.hasSubMenu())
                        menu.performItemAction(item, 0);
                }

                final _cls3 this$1;
                final MenuItem val$item;
                final MenuBuilder val$menu;
                final CascadingMenuInfo val$nextInfo;

            
            {
                this$1 = final__pcls3;
                nextInfo = cascadingmenuinfo;
                item = menuitem;
                menu = MenuBuilder.this;
                super();
            }
            }
;
            l1 = SystemClock.uptimeMillis();
            CascadingMenuPopup._2D_get3(CascadingMenuPopup.this).postAtTime(item, menubuilder, l1 + 200L);
        }

        public void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
        {
            CascadingMenuPopup._2D_get3(CascadingMenuPopup.this).removeCallbacksAndMessages(menubuilder);
        }

        final CascadingMenuPopup this$0;

            
            {
                this$0 = CascadingMenuPopup.this;
                super();
            }
    }
;
    private final int mMenuMaxWidth;
    private android.widget.PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List mPendingMenus = new LinkedList();
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private int mRawDropDownGravity;
    private boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    private final List mShowingMenus = new ArrayList();
    private View mShownAnchorView;
    private final Handler mSubMenuHoverHandler = new Handler();
    private ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;
}
