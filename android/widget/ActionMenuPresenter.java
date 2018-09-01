// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.*;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.view.ActionBarPolicy;
import com.android.internal.view.menu.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.widget:
//            ActionMenuView, ImageButton, ForwardingListener

public class ActionMenuPresenter extends BaseMenuPresenter
    implements android.view.ActionProvider.SubUiVisibilityListener
{
    private class ActionButtonSubmenu extends MenuPopupHelper
    {

        protected void onDismiss()
        {
            ActionMenuPresenter._2D_set0(ActionMenuPresenter.this, null);
            mOpenSubMenuId = 0;
            super.onDismiss();
        }

        final ActionMenuPresenter this$0;

        public ActionButtonSubmenu(Context context, SubMenuBuilder submenubuilder, View view)
        {
            this$0 = ActionMenuPresenter.this;
            super(context, submenubuilder, view, false, 0x1010444);
            if(!((MenuItemImpl)submenubuilder.getItem()).isActionButton())
            {
                if(ActionMenuPresenter._2D_get4(ActionMenuPresenter.this) == null)
                    context = (View)ActionMenuPresenter._2D_get3(ActionMenuPresenter.this);
                else
                    context = ActionMenuPresenter._2D_get4(ActionMenuPresenter.this);
                setAnchorView(context);
            }
            setPresenterCallback(mPopupPresenterCallback);
        }
    }

    private class ActionMenuPopupCallback extends com.android.internal.view.menu.ActionMenuItemView.PopupCallback
    {

        public ShowableListMenu getPopup()
        {
            com.android.internal.view.menu.MenuPopup menupopup = null;
            if(ActionMenuPresenter._2D_get0(ActionMenuPresenter.this) != null)
                menupopup = ActionMenuPresenter._2D_get0(ActionMenuPresenter.this).getPopup();
            return menupopup;
        }

        final ActionMenuPresenter this$0;

        private ActionMenuPopupCallback()
        {
            this$0 = ActionMenuPresenter.this;
            super();
        }

        ActionMenuPopupCallback(ActionMenuPopupCallback actionmenupopupcallback)
        {
            this();
        }
    }

    private static class ItemAnimationInfo
    {

        static final int FADE_IN = 1;
        static final int FADE_OUT = 2;
        static final int MOVE = 0;
        int animType;
        Animator animator;
        int id;
        MenuItemLayoutInfo menuItemLayoutInfo;

        ItemAnimationInfo(int i, MenuItemLayoutInfo menuitemlayoutinfo, Animator animator1, int j)
        {
            id = i;
            menuItemLayoutInfo = menuitemlayoutinfo;
            animator = animator1;
            animType = j;
        }
    }

    private static class MenuItemLayoutInfo
    {

        int left;
        int top;
        View view;

        MenuItemLayoutInfo(View view1, boolean flag)
        {
            left = view1.getLeft();
            top = view1.getTop();
            if(flag)
            {
                left = (int)((float)left + view1.getTranslationX());
                top = (int)((float)top + view1.getTranslationY());
            }
            view = view1;
        }
    }

    private class OpenOverflowRunnable
        implements Runnable
    {

        public void run()
        {
            if(ActionMenuPresenter._2D_get2(ActionMenuPresenter.this) != null)
                ActionMenuPresenter._2D_get2(ActionMenuPresenter.this).changeMenuMode();
            View view = (View)ActionMenuPresenter._2D_get3(ActionMenuPresenter.this);
            if(view != null && view.getWindowToken() != null && mPopup.tryShow())
                ActionMenuPresenter._2D_set1(ActionMenuPresenter.this, mPopup);
            ActionMenuPresenter._2D_set2(ActionMenuPresenter.this, null);
        }

        private OverflowPopup mPopup;
        final ActionMenuPresenter this$0;

        public OpenOverflowRunnable(OverflowPopup overflowpopup)
        {
            this$0 = ActionMenuPresenter.this;
            super();
            mPopup = overflowpopup;
        }
    }

    private class OverflowMenuButton extends ImageButton
        implements ActionMenuView.ActionMenuChildView
    {

        public boolean needsDividerAfter()
        {
            return false;
        }

        public boolean needsDividerBefore()
        {
            return false;
        }

        public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
            accessibilitynodeinfo.setCanOpenPopup(true);
        }

        public boolean performClick()
        {
            if(super.performClick())
            {
                return true;
            } else
            {
                playSoundEffect(0);
                showOverflowMenu();
                return true;
            }
        }

        protected boolean setFrame(int i, int j, int k, int l)
        {
            boolean flag = super.setFrame(i, j, k, l);
            Drawable drawable = getDrawable();
            Drawable drawable1 = getBackground();
            if(drawable != null && drawable1 != null)
            {
                int i1 = getWidth();
                l = getHeight();
                i = Math.max(i1, l) / 2;
                int j1 = getPaddingLeft();
                int k1 = getPaddingRight();
                j = getPaddingTop();
                k = getPaddingBottom();
                i1 = (i1 + (j1 - k1)) / 2;
                j = (l + (j - k)) / 2;
                drawable1.setHotspotBounds(i1 - i, j - i, i1 + i, j + i);
            }
            return flag;
        }

        final ActionMenuPresenter this$0;

        public OverflowMenuButton(Context context)
        {
            this$0 = ActionMenuPresenter.this;
            super(context, null, 0x10102f6);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setOnTouchListener(new _cls1(this));
        }
    }

    private class OverflowPopup extends MenuPopupHelper
    {

        protected void onDismiss()
        {
            if(ActionMenuPresenter._2D_get2(ActionMenuPresenter.this) != null)
                ActionMenuPresenter._2D_get2(ActionMenuPresenter.this).close();
            ActionMenuPresenter._2D_set1(ActionMenuPresenter.this, null);
            super.onDismiss();
        }

        final ActionMenuPresenter this$0;

        public OverflowPopup(Context context, MenuBuilder menubuilder, View view, boolean flag)
        {
            this$0 = ActionMenuPresenter.this;
            super(context, menubuilder, view, flag, 0x1010444);
            setGravity(0x800005);
            setPresenterCallback(mPopupPresenterCallback);
        }
    }

    private class PopupPresenterCallback
        implements com.android.internal.view.menu.MenuPresenter.Callback
    {

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            if(menubuilder instanceof SubMenuBuilder)
                menubuilder.getRootMenu().close(false);
            com.android.internal.view.menu.MenuPresenter.Callback callback = getCallback();
            if(callback != null)
                callback.onCloseMenu(menubuilder, flag);
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if(menubuilder == null)
                return false;
            mOpenSubMenuId = ((SubMenuBuilder)menubuilder).getItem().getItemId();
            com.android.internal.view.menu.MenuPresenter.Callback callback = getCallback();
            boolean flag;
            if(callback != null)
                flag = callback.onOpenSubMenu(menubuilder);
            else
                flag = false;
            return flag;
        }

        final ActionMenuPresenter this$0;

        private PopupPresenterCallback()
        {
            this$0 = ActionMenuPresenter.this;
            super();
        }

        PopupPresenterCallback(PopupPresenterCallback popuppresentercallback)
        {
            this();
        }
    }

    private static class SavedState
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(openSubMenuId);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int openSubMenuId;


        SavedState()
        {
        }

        SavedState(Parcel parcel)
        {
            openSubMenuId = parcel.readInt();
        }
    }


    static ActionButtonSubmenu _2D_get0(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mActionButtonPopup;
    }

    static android.view.ViewTreeObserver.OnPreDrawListener _2D_get1(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mItemAnimationPreDrawListener;
    }

    static MenuBuilder _2D_get2(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mMenu;
    }

    static MenuView _2D_get3(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mMenuView;
    }

    static OverflowMenuButton _2D_get4(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mOverflowButton;
    }

    static OverflowPopup _2D_get5(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mOverflowPopup;
    }

    static SparseArray _2D_get6(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mPostLayoutItems;
    }

    static OpenOverflowRunnable _2D_get7(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mPostedOpenRunnable;
    }

    static SparseArray _2D_get8(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mPreLayoutItems;
    }

    static List _2D_get9(ActionMenuPresenter actionmenupresenter)
    {
        return actionmenupresenter.mRunningItemAnimations;
    }

    static ActionButtonSubmenu _2D_set0(ActionMenuPresenter actionmenupresenter, ActionButtonSubmenu actionbuttonsubmenu)
    {
        actionmenupresenter.mActionButtonPopup = actionbuttonsubmenu;
        return actionbuttonsubmenu;
    }

    static OverflowPopup _2D_set1(ActionMenuPresenter actionmenupresenter, OverflowPopup overflowpopup)
    {
        actionmenupresenter.mOverflowPopup = overflowpopup;
        return overflowpopup;
    }

    static OpenOverflowRunnable _2D_set2(ActionMenuPresenter actionmenupresenter, OpenOverflowRunnable openoverflowrunnable)
    {
        actionmenupresenter.mPostedOpenRunnable = openoverflowrunnable;
        return openoverflowrunnable;
    }

    static void _2D_wrap0(ActionMenuPresenter actionmenupresenter, boolean flag)
    {
        actionmenupresenter.computeMenuItemAnimationInfo(flag);
    }

    static void _2D_wrap1(ActionMenuPresenter actionmenupresenter)
    {
        actionmenupresenter.runItemAnimations();
    }

    public ActionMenuPresenter(Context context)
    {
        super(context, 0x109001f, 0x109001e);
        mPreLayoutItems = new SparseArray();
        mPostLayoutItems = new SparseArray();
        mRunningItemAnimations = new ArrayList();
        mItemAnimationPreDrawListener = new android.view.ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw()
            {
                ActionMenuPresenter._2D_wrap0(ActionMenuPresenter.this, false);
                ((View)ActionMenuPresenter._2D_get3(ActionMenuPresenter.this)).getViewTreeObserver().removeOnPreDrawListener(this);
                ActionMenuPresenter._2D_wrap1(ActionMenuPresenter.this);
                return true;
            }

            final ActionMenuPresenter this$0;

            
            {
                this$0 = ActionMenuPresenter.this;
                super();
            }
        }
;
        mAttachStateChangeListener = new android.view.View.OnAttachStateChangeListener() {

            public void onViewAttachedToWindow(View view)
            {
            }

            public void onViewDetachedFromWindow(View view)
            {
                ((View)ActionMenuPresenter._2D_get3(ActionMenuPresenter.this)).getViewTreeObserver().removeOnPreDrawListener(ActionMenuPresenter._2D_get1(ActionMenuPresenter.this));
                ActionMenuPresenter._2D_get8(ActionMenuPresenter.this).clear();
                ActionMenuPresenter._2D_get6(ActionMenuPresenter.this).clear();
            }

            final ActionMenuPresenter this$0;

            
            {
                this$0 = ActionMenuPresenter.this;
                super();
            }
        }
;
    }

    private void computeMenuItemAnimationInfo(boolean flag)
    {
        ViewGroup viewgroup = (ViewGroup)mMenuView;
        int i = viewgroup.getChildCount();
        SparseArray sparsearray;
        int j;
        if(flag)
            sparsearray = mPreLayoutItems;
        else
            sparsearray = mPostLayoutItems;
        for(j = 0; j < i; j++)
        {
            View view = viewgroup.getChildAt(j);
            int k = view.getId();
            if(k > 0 && view.getWidth() != 0 && view.getHeight() != 0)
                sparsearray.put(k, new MenuItemLayoutInfo(view, flag));
        }

    }

    private View findViewForItem(MenuItem menuitem)
    {
        ViewGroup viewgroup = (ViewGroup)mMenuView;
        if(viewgroup == null)
            return null;
        int i = viewgroup.getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = viewgroup.getChildAt(j);
            if((view instanceof com.android.internal.view.menu.MenuView.ItemView) && ((com.android.internal.view.menu.MenuView.ItemView)view).getItemData() == menuitem)
                return view;
        }

        return null;
    }

    private void runItemAnimations()
    {
        int i = 0;
        while(i < mPreLayoutItems.size()) 
        {
            int k = mPreLayoutItems.keyAt(i);
            final Object menuItemLayoutInfoPre = (MenuItemLayoutInfo)mPreLayoutItems.get(k);
            int i1 = mPostLayoutItems.indexOfKey(k);
            if(i1 >= 0)
            {
                MenuItemLayoutInfo menuitemlayoutinfo = (MenuItemLayoutInfo)mPostLayoutItems.valueAt(i1);
                Object obj = null;
                Object obj1 = null;
                if(((MenuItemLayoutInfo) (menuItemLayoutInfoPre)).left != menuitemlayoutinfo.left)
                    obj = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, new float[] {
                        (float)(((MenuItemLayoutInfo) (menuItemLayoutInfoPre)).left - menuitemlayoutinfo.left), 0.0F
                    });
                if(((MenuItemLayoutInfo) (menuItemLayoutInfoPre)).top != menuitemlayoutinfo.top)
                    obj1 = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[] {
                        (float)(((MenuItemLayoutInfo) (menuItemLayoutInfoPre)).top - menuitemlayoutinfo.top), 0.0F
                    });
                if(obj != null || obj1 != null)
                {
                    for(int j1 = 0; j1 < mRunningItemAnimations.size(); j1++)
                    {
                        menuItemLayoutInfoPre = (ItemAnimationInfo)mRunningItemAnimations.get(j1);
                        if(((ItemAnimationInfo) (menuItemLayoutInfoPre)).id == k && ((ItemAnimationInfo) (menuItemLayoutInfoPre)).animType == 0)
                            ((ItemAnimationInfo) (menuItemLayoutInfoPre)).animator.cancel();
                    }

                    if(obj != null)
                    {
                        if(obj1 != null)
                            obj = ObjectAnimator.ofPropertyValuesHolder(menuitemlayoutinfo.view, new PropertyValuesHolder[] {
                                obj, obj1
                            });
                        else
                            obj = ObjectAnimator.ofPropertyValuesHolder(menuitemlayoutinfo.view, new PropertyValuesHolder[] {
                                obj
                            });
                    } else
                    {
                        obj = ObjectAnimator.ofPropertyValuesHolder(menuitemlayoutinfo.view, new PropertyValuesHolder[] {
                            obj1
                        });
                    }
                    ((ObjectAnimator) (obj)).setDuration(150L);
                    ((ObjectAnimator) (obj)).start();
                    obj1 = new ItemAnimationInfo(k, menuitemlayoutinfo, ((Animator) (obj)), 0);
                    mRunningItemAnimations.add(obj1);
                    ((ObjectAnimator) (obj)).addListener(new AnimatorListenerAdapter() {

                        public void onAnimationEnd(Animator animator)
                        {
                            int j2 = 0;
                            do
                            {
label0:
                                {
                                    if(j2 < ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).size())
                                    {
                                        if(((ItemAnimationInfo)ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).get(j2)).animator != animator)
                                            break label0;
                                        ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).remove(j2);
                                    }
                                    return;
                                }
                                j2++;
                            } while(true);
                        }

                        final ActionMenuPresenter this$0;

            
            {
                this$0 = ActionMenuPresenter.this;
                super();
            }
                    }
);
                }
                mPostLayoutItems.remove(k);
            } else
            {
                float f = 1.0F;
                for(int k1 = 0; k1 < mRunningItemAnimations.size();)
                {
                    ItemAnimationInfo itemanimationinfo = (ItemAnimationInfo)mRunningItemAnimations.get(k1);
                    float f2 = f;
                    if(itemanimationinfo.id == k)
                    {
                        f2 = f;
                        if(itemanimationinfo.animType == 1)
                        {
                            f2 = itemanimationinfo.menuItemLayoutInfo.view.getAlpha();
                            itemanimationinfo.animator.cancel();
                        }
                    }
                    k1++;
                    f = f2;
                }

                ObjectAnimator objectanimator = ObjectAnimator.ofFloat(((MenuItemLayoutInfo) (menuItemLayoutInfoPre)).view, View.ALPHA, new float[] {
                    f, 0.0F
                });
                ((ViewGroup)mMenuView).getOverlay().add(((MenuItemLayoutInfo) (menuItemLayoutInfoPre)).view);
                objectanimator.setDuration(150L);
                objectanimator.start();
                ItemAnimationInfo itemanimationinfo2 = new ItemAnimationInfo(k, ((MenuItemLayoutInfo) (menuItemLayoutInfoPre)), objectanimator, 2);
                mRunningItemAnimations.add(itemanimationinfo2);
                objectanimator.addListener(new AnimatorListenerAdapter() {

                    public void onAnimationEnd(Animator animator)
                    {
                        int j2 = 0;
                        do
                        {
label0:
                            {
                                if(j2 < ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).size())
                                {
                                    if(((ItemAnimationInfo)ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).get(j2)).animator != animator)
                                        break label0;
                                    ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).remove(j2);
                                }
                                ((ViewGroup)ActionMenuPresenter._2D_get3(ActionMenuPresenter.this)).getOverlay().remove(menuItemLayoutInfoPre.view);
                                return;
                            }
                            j2++;
                        } while(true);
                    }

                    final ActionMenuPresenter this$0;
                    final MenuItemLayoutInfo val$menuItemLayoutInfoPre;

            
            {
                this$0 = ActionMenuPresenter.this;
                menuItemLayoutInfoPre = menuitemlayoutinfo;
                super();
            }
                }
);
            }
            i++;
        }
        for(int j = 0; j < mPostLayoutItems.size(); j++)
        {
            int l = mPostLayoutItems.keyAt(j);
            int l1 = mPostLayoutItems.indexOfKey(l);
            if(l1 < 0)
                continue;
            Object obj2 = (MenuItemLayoutInfo)mPostLayoutItems.valueAt(l1);
            float f3 = 0.0F;
            for(int i2 = 0; i2 < mRunningItemAnimations.size();)
            {
                ItemAnimationInfo itemanimationinfo1 = (ItemAnimationInfo)mRunningItemAnimations.get(i2);
                float f1 = f3;
                if(itemanimationinfo1.id == l)
                {
                    f1 = f3;
                    if(itemanimationinfo1.animType == 2)
                    {
                        f1 = itemanimationinfo1.menuItemLayoutInfo.view.getAlpha();
                        itemanimationinfo1.animator.cancel();
                    }
                }
                i2++;
                f3 = f1;
            }

            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(((MenuItemLayoutInfo) (obj2)).view, View.ALPHA, new float[] {
                f3, 1.0F
            });
            objectanimator1.start();
            objectanimator1.setDuration(150L);
            obj2 = new ItemAnimationInfo(l, ((MenuItemLayoutInfo) (obj2)), objectanimator1, 1);
            mRunningItemAnimations.add(obj2);
            objectanimator1.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    int j2 = 0;
                    do
                    {
label0:
                        {
                            if(j2 < ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).size())
                            {
                                if(((ItemAnimationInfo)ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).get(j2)).animator != animator)
                                    break label0;
                                ActionMenuPresenter._2D_get9(ActionMenuPresenter.this).remove(j2);
                            }
                            return;
                        }
                        j2++;
                    } while(true);
                }

                final ActionMenuPresenter this$0;

            
            {
                this$0 = ActionMenuPresenter.this;
                super();
            }
            }
);
        }

        mPreLayoutItems.clear();
        mPostLayoutItems.clear();
    }

    private void setupItemAnimations()
    {
        computeMenuItemAnimationInfo(true);
        ((View)mMenuView).getViewTreeObserver().addOnPreDrawListener(mItemAnimationPreDrawListener);
    }

    public void bindItemView(MenuItemImpl menuitemimpl, com.android.internal.view.menu.MenuView.ItemView itemview)
    {
        itemview.initialize(menuitemimpl, 0);
        menuitemimpl = (ActionMenuView)mMenuView;
        itemview = (ActionMenuItemView)itemview;
        itemview.setItemInvoker(menuitemimpl);
        if(mPopupCallback == null)
            mPopupCallback = new ActionMenuPopupCallback(null);
        itemview.setPopupCallback(mPopupCallback);
    }

    public boolean dismissPopupMenus()
    {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean filterLeftoverView(ViewGroup viewgroup, int i)
    {
        if(viewgroup.getChildAt(i) == mOverflowButton)
            return false;
        else
            return super.filterLeftoverView(viewgroup, i);
    }

    public boolean flagActionItems()
    {
        ArrayList arraylist;
        int i;
        int l;
        int j1;
        int k1;
        ViewGroup viewgroup;
        int l1;
        int l2;
        int i3;
        SparseBooleanArray sparsebooleanarray;
        int i4;
        boolean flag;
label0:
        {
            int j;
            int i2;
            boolean flag1;
            if(mMenu != null)
            {
                arraylist = mMenu.getVisibleItems();
                i = arraylist.size();
            } else
            {
                arraylist = null;
                i = 0;
            }
            j = mMaxItems;
            j1 = mActionItemWidthLimit;
            k1 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            viewgroup = (ViewGroup)mMenuView;
            l1 = 0;
            i2 = 0;
            flag = false;
            flag1 = false;
            i3 = 0;
            while(i3 < i) 
            {
                MenuItemImpl menuitemimpl = (MenuItemImpl)arraylist.get(i3);
                int j3;
                if(menuitemimpl.requiresActionButton())
                    l1++;
                else
                if(menuitemimpl.requestsActionButton())
                    i2++;
                else
                    flag1 = true;
                j3 = j;
                if(mExpandedActionViewsExclusive)
                {
                    j3 = j;
                    if(menuitemimpl.isActionViewExpanded())
                        j3 = 0;
                }
                i3++;
                j = j3;
            }
            i3 = j;
            if(!mReserveOverflow)
                break label0;
            if(!flag1)
            {
                i3 = j;
                if(l1 + i2 <= j)
                    break label0;
            }
            i3 = j - 1;
        }
        i3 -= l1;
        sparsebooleanarray = mActionButtonGroups;
        sparsebooleanarray.clear();
        i4 = 0;
        l1 = 0;
        if(mStrictWidthLimit)
        {
            l1 = j1 / mMinCellSize;
            int k = mMinCellSize;
            i4 = mMinCellSize + (j1 % k) / l1;
        }
        l = 0;
        l2 = j1;
        j1 = l;
        l = ((flag) ? 1 : 0);
_L2:
        int j2;
        MenuItemImpl menuitemimpl1;
        if(j1 >= i)
            break MISSING_BLOCK_LABEL_769;
        menuitemimpl1 = (MenuItemImpl)arraylist.get(j1);
        if(!menuitemimpl1.requiresActionButton())
            break; /* Loop/switch isn't completed */
        View view = getItemView(menuitemimpl1, null, viewgroup);
        int k3;
        if(mStrictWidthLimit)
            l1 -= ActionMenuView.measureChildForCells(view, i4, l1, k1, 0);
        else
            view.measure(k1, k1);
        k3 = view.getMeasuredWidth();
        j2 = l2 - k3;
        l2 = l;
        if(l == 0)
            l2 = k3;
        l = menuitemimpl1.getGroupId();
        if(l != 0)
            sparsebooleanarray.put(l, true);
        menuitemimpl1.setIsActionButton(true);
        l = l2;
_L5:
        j1++;
        l2 = j2;
        if(true) goto _L2; else goto _L1
_L1:
        int j4;
        boolean flag2;
        if(!menuitemimpl1.requestsActionButton())
            break MISSING_BLOCK_LABEL_756;
        j4 = menuitemimpl1.getGroupId();
        flag2 = sparsebooleanarray.get(j4);
        int k2;
        int l3;
        boolean flag3;
        boolean flag4;
        if((i3 > 0 || flag2) && l2 > 0)
        {
            if(!mStrictWidthLimit || l1 > 0)
                flag3 = true;
            else
                flag3 = false;
        } else
        {
            flag3 = false;
        }
        k2 = l1;
        l3 = l;
        flag4 = flag3;
        j2 = l2;
        if(flag3)
        {
            View view1 = getItemView(menuitemimpl1, null, viewgroup);
            if(mStrictWidthLimit)
            {
                l3 = ActionMenuView.measureChildForCells(view1, i4, l1, k1, 0);
                j2 = l1 - l3;
                l1 = j2;
                if(l3 == 0)
                {
                    flag3 = false;
                    l1 = j2;
                }
            } else
            {
                view1.measure(k1, k1);
            }
            k2 = view1.getMeasuredWidth();
            j2 = l2 - k2;
            l3 = l;
            if(l == 0)
                l3 = k2;
            if(mStrictWidthLimit)
            {
                if(j2 >= 0)
                    l = 1;
                else
                    l = 0;
                flag4 = flag3 & l;
                k2 = l1;
            } else
            {
                if(j2 + l3 > 0)
                    l = 1;
                else
                    l = 0;
                flag4 = flag3 & l;
                k2 = l1;
            }
        }
        if(!flag4 || j4 == 0) goto _L4; else goto _L3
_L3:
        sparsebooleanarray.put(j4, true);
        l = i3;
_L7:
        i3 = l;
        if(flag4)
            i3 = l - 1;
        menuitemimpl1.setIsActionButton(flag4);
        l1 = k2;
        l = l3;
          goto _L5
_L4:
        l = i3;
        if(!flag2) goto _L7; else goto _L6
_L6:
        sparsebooleanarray.put(j4, false);
        l1 = 0;
_L9:
        l = i3;
        if(l1 >= j1) goto _L7; else goto _L8
_L8:
        MenuItemImpl menuitemimpl2 = (MenuItemImpl)arraylist.get(l1);
        int i1 = i3;
        if(menuitemimpl2.getGroupId() == j4)
        {
            i1 = i3;
            if(menuitemimpl2.isActionButton())
                i1 = i3 + 1;
            menuitemimpl2.setIsActionButton(false);
        }
        l1++;
        i3 = i1;
          goto _L9
          goto _L7
        menuitemimpl1.setIsActionButton(false);
        j2 = l2;
          goto _L5
        return true;
    }

    public View getItemView(MenuItemImpl menuitemimpl, View view, ViewGroup viewgroup)
    {
        View view1 = menuitemimpl.getActionView();
        if(view1 == null || menuitemimpl.hasCollapsibleActionView())
            view1 = super.getItemView(menuitemimpl, view, viewgroup);
        byte byte0;
        if(menuitemimpl.isActionViewExpanded())
            byte0 = 8;
        else
            byte0 = 0;
        view1.setVisibility(byte0);
        view = (ActionMenuView)viewgroup;
        menuitemimpl = view1.getLayoutParams();
        if(!view.checkLayoutParams(menuitemimpl))
            view1.setLayoutParams(view.generateLayoutParams(menuitemimpl));
        return view1;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        MenuView menuview = mMenuView;
        viewgroup = super.getMenuView(viewgroup);
        if(menuview != viewgroup)
        {
            ((ActionMenuView)viewgroup).setPresenter(this);
            if(menuview != null)
                ((View)menuview).removeOnAttachStateChangeListener(mAttachStateChangeListener);
            ((View)viewgroup).addOnAttachStateChangeListener(mAttachStateChangeListener);
        }
        return viewgroup;
    }

    public Drawable getOverflowIcon()
    {
        if(mOverflowButton != null)
            return mOverflowButton.getDrawable();
        if(mPendingOverflowIconSet)
            return mPendingOverflowIcon;
        else
            return null;
    }

    public boolean hideOverflowMenu()
    {
        if(mPostedOpenRunnable != null && mMenuView != null)
        {
            ((View)mMenuView).removeCallbacks(mPostedOpenRunnable);
            mPostedOpenRunnable = null;
            return true;
        }
        OverflowPopup overflowpopup = mOverflowPopup;
        if(overflowpopup != null)
        {
            overflowpopup.dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean hideSubMenus()
    {
        if(mActionButtonPopup != null)
        {
            mActionButtonPopup.dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
        super.initForMenu(context, menubuilder);
        menubuilder = context.getResources();
        context = ActionBarPolicy.get(context);
        if(!mReserveOverflowSet)
            mReserveOverflow = context.showsOverflowMenuButton();
        if(!mWidthLimitSet)
            mWidthLimit = context.getEmbeddedMenuWidthLimit();
        if(!mMaxItemsSet)
            mMaxItems = context.getMaxActionButtons();
        int i = mWidthLimit;
        if(mReserveOverflow)
        {
            if(mOverflowButton == null)
            {
                mOverflowButton = new OverflowMenuButton(mSystemContext);
                if(mPendingOverflowIconSet)
                {
                    mOverflowButton.setImageDrawable(mPendingOverflowIcon);
                    mPendingOverflowIcon = null;
                    mPendingOverflowIconSet = false;
                }
                int j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                mOverflowButton.measure(j, j);
            }
            i -= mOverflowButton.getMeasuredWidth();
        } else
        {
            mOverflowButton = null;
        }
        mActionItemWidthLimit = i;
        mMinCellSize = (int)(menubuilder.getDisplayMetrics().density * 56F);
    }

    public boolean isOverflowMenuShowPending()
    {
        boolean flag;
        if(mPostedOpenRunnable == null)
            flag = isOverflowMenuShowing();
        else
            flag = true;
        return flag;
    }

    public boolean isOverflowMenuShowing()
    {
        boolean flag;
        if(mOverflowPopup != null)
            flag = mOverflowPopup.isShowing();
        else
            flag = false;
        return flag;
    }

    public boolean isOverflowReserved()
    {
        return mReserveOverflow;
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        dismissPopupMenus();
        super.onCloseMenu(menubuilder, flag);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if(!mMaxItemsSet)
            mMaxItems = ActionBarPolicy.get(mContext).getMaxActionButtons();
        if(mMenu != null)
            mMenu.onItemsChanged(true);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        if(((SavedState) (parcelable)).openSubMenuId > 0)
        {
            parcelable = mMenu.findItem(((SavedState) (parcelable)).openSubMenuId);
            if(parcelable != null)
                onSubMenuSelected((SubMenuBuilder)parcelable.getSubMenu());
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState();
        savedstate.openSubMenuId = mOpenSubMenuId;
        return savedstate;
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        if(!submenubuilder.hasVisibleItems())
            return false;
        Object obj;
        for(obj = submenubuilder; ((SubMenuBuilder) (obj)).getParentMenu() != mMenu; obj = (SubMenuBuilder)((SubMenuBuilder) (obj)).getParentMenu());
        obj = findViewForItem(((SubMenuBuilder) (obj)).getItem());
        if(obj == null)
            return false;
        mOpenSubMenuId = submenubuilder.getItem().getItemId();
        boolean flag = false;
        int i = submenubuilder.size();
        int j = 0;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(j < i)
                {
                    MenuItem menuitem = submenubuilder.getItem(j);
                    if(!menuitem.isVisible() || menuitem.getIcon() == null)
                        break label0;
                    flag1 = true;
                }
                mActionButtonPopup = new ActionButtonSubmenu(mContext, submenubuilder, ((View) (obj)));
                mActionButtonPopup.setForceShowIcon(flag1);
                mActionButtonPopup.show();
                super.onSubMenuSelected(submenubuilder);
                return true;
            }
            j++;
        } while(true);
    }

    public void onSubUiVisibilityChanged(boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        super.onSubMenuSelected(null);
_L4:
        return;
_L2:
        if(mMenu != null)
            mMenu.close(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setExpandedActionViewsExclusive(boolean flag)
    {
        mExpandedActionViewsExclusive = flag;
    }

    public void setItemLimit(int i)
    {
        mMaxItems = i;
        mMaxItemsSet = true;
    }

    public void setMenuView(ActionMenuView actionmenuview)
    {
        if(actionmenuview != mMenuView)
        {
            if(mMenuView != null)
                ((View)mMenuView).removeOnAttachStateChangeListener(mAttachStateChangeListener);
            mMenuView = actionmenuview;
            actionmenuview.initialize(mMenu);
            actionmenuview.addOnAttachStateChangeListener(mAttachStateChangeListener);
        }
    }

    public void setOverflowIcon(Drawable drawable)
    {
        if(mOverflowButton != null)
        {
            mOverflowButton.setImageDrawable(drawable);
        } else
        {
            mPendingOverflowIconSet = true;
            mPendingOverflowIcon = drawable;
        }
    }

    public void setReserveOverflow(boolean flag)
    {
        mReserveOverflow = flag;
        mReserveOverflowSet = true;
    }

    public void setWidthLimit(int i, boolean flag)
    {
        mWidthLimit = i;
        mStrictWidthLimit = flag;
        mWidthLimitSet = true;
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl menuitemimpl)
    {
        return menuitemimpl.isActionButton();
    }

    public boolean showOverflowMenu()
    {
        if(mReserveOverflow && isOverflowMenuShowing() ^ true && mMenu != null && mMenuView != null && mPostedOpenRunnable == null && mMenu.getNonActionItems().isEmpty() ^ true)
        {
            mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(mContext, mMenu, mOverflowButton, true));
            ((View)mMenuView).post(mPostedOpenRunnable);
            super.onSubMenuSelected(null);
            return true;
        } else
        {
            return false;
        }
    }

    public void updateMenuView(boolean flag)
    {
        Object obj = (ViewGroup)((View)mMenuView).getParent();
        super.updateMenuView(flag);
        ((View)mMenuView).requestLayout();
        if(mMenu != null)
        {
            obj = mMenu.getActionItems();
            int i = ((ArrayList) (obj)).size();
            for(int j = 0; j < i; j++)
            {
                ActionProvider actionprovider = ((MenuItemImpl)((ArrayList) (obj)).get(j)).getActionProvider();
                if(actionprovider != null)
                    actionprovider.setSubUiVisibilityListener(this);
            }

        }
        boolean flag1;
        int k;
        if(mMenu != null)
            obj = mMenu.getNonActionItems();
        else
            obj = null;
        flag1 = false;
        k = ((flag1) ? 1 : 0);
        if(mReserveOverflow)
        {
            k = ((flag1) ? 1 : 0);
            if(obj != null)
            {
                k = ((ArrayList) (obj)).size();
                if(k == 1)
                    k = ((MenuItemImpl)((ArrayList) (obj)).get(0)).isActionViewExpanded() ^ true;
                else
                if(k > 0)
                    k = 1;
                else
                    k = 0;
            }
        }
        if(k == 0) goto _L2; else goto _L1
_L1:
        if(mOverflowButton == null)
            mOverflowButton = new OverflowMenuButton(mSystemContext);
        obj = (ViewGroup)mOverflowButton.getParent();
        if(obj != mMenuView)
        {
            if(obj != null)
                ((ViewGroup) (obj)).removeView(mOverflowButton);
            obj = (ActionMenuView)mMenuView;
            ((ActionMenuView) (obj)).addView(mOverflowButton, ((ActionMenuView) (obj)).generateOverflowButtonLayoutParams());
        }
_L4:
        ((ActionMenuView)mMenuView).setOverflowReserved(mReserveOverflow);
        return;
_L2:
        if(mOverflowButton != null && mOverflowButton.getParent() == mMenuView)
            ((ViewGroup)mMenuView).removeView(mOverflowButton);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final boolean ACTIONBAR_ANIMATIONS_ENABLED = false;
    private static final int ITEM_ANIMATION_DURATION = 150;
    private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    private ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private android.view.View.OnAttachStateChangeListener mAttachStateChangeListener;
    private boolean mExpandedActionViewsExclusive;
    private android.view.ViewTreeObserver.OnPreDrawListener mItemAnimationPreDrawListener;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    private OverflowMenuButton mOverflowButton;
    private OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback(null);
    private SparseArray mPostLayoutItems;
    private OpenOverflowRunnable mPostedOpenRunnable;
    private SparseArray mPreLayoutItems;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private List mRunningItemAnimations;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    // Unreferenced inner class android/widget/ActionMenuPresenter$OverflowMenuButton$1

/* anonymous class */
    class OverflowMenuButton._cls1 extends ForwardingListener
    {

        public ShowableListMenu getPopup()
        {
            if(ActionMenuPresenter._2D_get5(_fld0) == null)
                return null;
            else
                return ActionMenuPresenter._2D_get5(_fld0).getPopup();
        }

        public boolean onForwardingStarted()
        {
            showOverflowMenu();
            return true;
        }

        public boolean onForwardingStopped()
        {
            if(ActionMenuPresenter._2D_get7(_fld0) != null)
            {
                return false;
            } else
            {
                hideOverflowMenu();
                return true;
            }
        }

        final OverflowMenuButton this$1;

            
            {
                this$1 = OverflowMenuButton.this;
                super(view);
            }
    }

}
