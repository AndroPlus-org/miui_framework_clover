// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.*;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.*;
import android.widget.LinearLayout;
import java.util.Locale;

// Referenced classes of package com.android.internal.view.menu:
//            MenuBuilder, SubMenuBuilder

public final class MenuItemImpl
    implements MenuItem
{

    static MenuBuilder _2D_get0(MenuItemImpl menuitemimpl)
    {
        return menuitemimpl.mMenu;
    }

    MenuItemImpl(MenuBuilder menubuilder, int i, int j, int k, int l, CharSequence charsequence, int i1)
    {
        mShortcutNumericModifiers = 4096;
        mShortcutAlphabeticModifiers = 4096;
        mIconResId = 0;
        mIconTintList = null;
        mIconTintMode = null;
        mHasIconTint = false;
        mHasIconTintMode = false;
        mNeedToApplyIconTint = false;
        mFlags = 16;
        mShowAsAction = 0;
        mIsActionViewExpanded = false;
        String s = menubuilder.getContext().getResources().getConfiguration().locale.toString();
        if(sPrependShortcutLabel == null || s.equals(sLanguage) ^ true)
        {
            sLanguage = s;
            sPrependShortcutLabel = menubuilder.getContext().getResources().getString(0x1040546);
            sEnterShortcutLabel = menubuilder.getContext().getResources().getString(0x10403a7);
            sDeleteShortcutLabel = menubuilder.getContext().getResources().getString(0x10403a6);
            sSpaceShortcutLabel = menubuilder.getContext().getResources().getString(0x10403a8);
        }
        mMenu = menubuilder;
        mId = j;
        mGroup = i;
        mCategoryOrder = k;
        mOrdering = l;
        mTitle = charsequence;
        mShowAsAction = i1;
    }

    private Drawable applyIconTintIfNecessary(Drawable drawable)
    {
        Drawable drawable1;
label0:
        {
            drawable1 = drawable;
            if(drawable == null)
                break label0;
            drawable1 = drawable;
            if(!mNeedToApplyIconTint)
                break label0;
            if(!mHasIconTint)
            {
                drawable1 = drawable;
                if(!mHasIconTintMode)
                    break label0;
            }
            drawable1 = drawable.mutate();
            if(mHasIconTint)
                drawable1.setTintList(mIconTintList);
            if(mHasIconTintMode)
                drawable1.setTintMode(mIconTintMode);
            mNeedToApplyIconTint = false;
        }
        return drawable1;
    }

    public void actionFormatChanged()
    {
        mMenu.onItemActionRequestChanged(this);
    }

    public boolean collapseActionView()
    {
        if((mShowAsAction & 8) == 0)
            return false;
        if(mActionView == null)
            return true;
        if(mOnActionExpandListener == null || mOnActionExpandListener.onMenuItemActionCollapse(this))
            return mMenu.collapseItemActionView(this);
        else
            return false;
    }

    public boolean expandActionView()
    {
        if(!hasCollapsibleActionView())
            return false;
        if(mOnActionExpandListener == null || mOnActionExpandListener.onMenuItemActionExpand(this))
            return mMenu.expandItemActionView(this);
        else
            return false;
    }

    public ActionProvider getActionProvider()
    {
        return mActionProvider;
    }

    public View getActionView()
    {
        if(mActionView != null)
            return mActionView;
        if(mActionProvider != null)
        {
            mActionView = mActionProvider.onCreateActionView(this);
            return mActionView;
        } else
        {
            return null;
        }
    }

    public int getAlphabeticModifiers()
    {
        return mShortcutAlphabeticModifiers;
    }

    public char getAlphabeticShortcut()
    {
        return mShortcutAlphabeticChar;
    }

    Runnable getCallback()
    {
        return mItemCallback;
    }

    public CharSequence getContentDescription()
    {
        return mContentDescription;
    }

    public int getGroupId()
    {
        return mGroup;
    }

    public Drawable getIcon()
    {
        if(mIconDrawable != null)
            return applyIconTintIfNecessary(mIconDrawable);
        if(mIconResId != 0)
        {
            Drawable drawable = mMenu.getContext().getDrawable(mIconResId);
            mIconResId = 0;
            mIconDrawable = drawable;
            return applyIconTintIfNecessary(drawable);
        } else
        {
            return null;
        }
    }

    public ColorStateList getIconTintList()
    {
        return mIconTintList;
    }

    public android.graphics.PorterDuff.Mode getIconTintMode()
    {
        return mIconTintMode;
    }

    public Intent getIntent()
    {
        return mIntent;
    }

    public int getItemId()
    {
        return mId;
    }

    public android.view.ContextMenu.ContextMenuInfo getMenuInfo()
    {
        return mMenuInfo;
    }

    public int getNumericModifiers()
    {
        return mShortcutNumericModifiers;
    }

    public char getNumericShortcut()
    {
        return mShortcutNumericChar;
    }

    public int getOrder()
    {
        return mCategoryOrder;
    }

    public int getOrdering()
    {
        return mOrdering;
    }

    char getShortcut()
    {
        char c2;
        if(mMenu.isQwertyMode())
        {
            char c = mShortcutAlphabeticChar;
            c2 = c;
        } else
        {
            char c1 = mShortcutNumericChar;
            c2 = c1;
        }
        return c2;
    }

    String getShortcutLabel()
    {
        char c;
        StringBuilder stringbuilder;
        c = getShortcut();
        if(c == 0)
            return "";
        stringbuilder = new StringBuilder(sPrependShortcutLabel);
        c;
        JVM INSTR lookupswitch 3: default 60
    //                   8: 82
    //                   10: 71
    //                   32: 93;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuilder.append(c);
_L6:
        return stringbuilder.toString();
_L3:
        stringbuilder.append(sEnterShortcutLabel);
        continue; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append(sDeleteShortcutLabel);
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append(sSpaceShortcutLabel);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public SubMenu getSubMenu()
    {
        return mSubMenu;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public CharSequence getTitleCondensed()
    {
        CharSequence charsequence;
        if(mTitleCondensed != null)
            charsequence = mTitleCondensed;
        else
            charsequence = mTitle;
        return charsequence;
    }

    CharSequence getTitleForItemView(MenuView.ItemView itemview)
    {
        if(itemview != null && itemview.prefersCondensedTitle())
            itemview = getTitleCondensed();
        else
            itemview = getTitle();
        return itemview;
    }

    public CharSequence getTooltipText()
    {
        return mTooltipText;
    }

    public boolean hasCollapsibleActionView()
    {
        boolean flag = false;
        if((mShowAsAction & 8) != 0)
        {
            if(mActionView == null && mActionProvider != null)
                mActionView = mActionProvider.onCreateActionView(this);
            if(mActionView != null)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean hasSubMenu()
    {
        boolean flag;
        if(mSubMenu != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean invoke()
    {
        if(mClickListener != null && mClickListener.onMenuItemClick(this))
            return true;
        if(mMenu.dispatchMenuItemSelected(mMenu, this))
            return true;
        if(mItemCallback != null)
        {
            mItemCallback.run();
            return true;
        }
        if(mIntent == null)
            break MISSING_BLOCK_LABEL_91;
        mMenu.getContext().startActivity(mIntent);
        return true;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", activitynotfoundexception);
        return mActionProvider != null && mActionProvider.onPerformDefaultAction();
    }

    public boolean isActionButton()
    {
        boolean flag;
        if((mFlags & 0x20) == 32)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isActionViewExpanded()
    {
        return mIsActionViewExpanded;
    }

    public boolean isCheckable()
    {
        boolean flag = true;
        if((mFlags & 1) != 1)
            flag = false;
        return flag;
    }

    public boolean isChecked()
    {
        boolean flag;
        if((mFlags & 2) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEnabled()
    {
        boolean flag = false;
        if((mFlags & 0x10) != 0)
            flag = true;
        return flag;
    }

    public boolean isExclusiveCheckable()
    {
        boolean flag = false;
        if((mFlags & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean isVisible()
    {
        boolean flag = false;
        boolean flag1 = false;
        if(mActionProvider != null && mActionProvider.overridesItemVisibility())
        {
            if((mFlags & 8) == 0)
                flag1 = mActionProvider.isVisible();
            return flag1;
        }
        flag1 = flag;
        if((mFlags & 8) == 0)
            flag1 = true;
        return flag1;
    }

    public boolean requestsActionButton()
    {
        boolean flag = true;
        if((mShowAsAction & 1) != 1)
            flag = false;
        return flag;
    }

    public boolean requiresActionButton()
    {
        boolean flag;
        if((mShowAsAction & 2) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean requiresOverflow()
    {
        boolean flag;
        if((mShowAsAction & 0x80000000) == 0x80000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public MenuItem setActionProvider(ActionProvider actionprovider)
    {
        if(mActionProvider != null)
            mActionProvider.reset();
        mActionView = null;
        mActionProvider = actionprovider;
        mMenu.onItemsChanged(true);
        if(mActionProvider != null)
            mActionProvider.setVisibilityListener(new android.view.ActionProvider.VisibilityListener() {

                public void onActionProviderVisibilityChanged(boolean flag)
                {
                    MenuItemImpl._2D_get0(MenuItemImpl.this).onItemVisibleChanged(MenuItemImpl.this);
                }

                final MenuItemImpl this$0;

            
            {
                this$0 = MenuItemImpl.this;
                super();
            }
            }
);
        return this;
    }

    public MenuItem setActionView(int i)
    {
        Context context = mMenu.getContext();
        setActionView(LayoutInflater.from(context).inflate(i, new LinearLayout(context), false));
        return this;
    }

    public MenuItem setActionView(View view)
    {
        mActionView = view;
        mActionProvider = null;
        if(view != null && view.getId() == -1 && mId > 0)
            view.setId(mId);
        mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public void setActionViewExpanded(boolean flag)
    {
        mIsActionViewExpanded = flag;
        mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char c)
    {
        if(mShortcutAlphabeticChar == c)
        {
            return this;
        } else
        {
            mShortcutAlphabeticChar = Character.toLowerCase(c);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setAlphabeticShortcut(char c, int i)
    {
        if(mShortcutAlphabeticChar == c && mShortcutAlphabeticModifiers == i)
        {
            return this;
        } else
        {
            mShortcutAlphabeticChar = Character.toLowerCase(c);
            mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setCallback(Runnable runnable)
    {
        mItemCallback = runnable;
        return this;
    }

    public MenuItem setCheckable(boolean flag)
    {
        int i = mFlags;
        int j = mFlags;
        boolean flag1;
        if(flag)
            flag1 = true;
        else
            flag1 = false;
        mFlags = flag1 | j & -2;
        if(i != mFlags)
            mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setChecked(boolean flag)
    {
        if((mFlags & 4) != 0)
            mMenu.setExclusiveItemChecked(this);
        else
            setCheckedInt(flag);
        return this;
    }

    void setCheckedInt(boolean flag)
    {
        int i = mFlags;
        int j = mFlags;
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        mFlags = byte0 | j & -3;
        if(i != mFlags)
            mMenu.onItemsChanged(false);
    }

    public MenuItem setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setEnabled(boolean flag)
    {
        if(flag)
            mFlags = mFlags | 0x10;
        else
            mFlags = mFlags & 0xffffffef;
        mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        mFlags = byte0 | i & -5;
    }

    public MenuItem setIcon(int i)
    {
        mIconDrawable = null;
        mIconResId = i;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(Drawable drawable)
    {
        mIconResId = 0;
        mIconDrawable = drawable;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorstatelist)
    {
        mIconTintList = colorstatelist;
        mHasIconTint = true;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mIconTintMode = mode;
        mHasIconTintMode = true;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent intent)
    {
        mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean flag)
    {
        if(flag)
            mFlags = mFlags | 0x20;
        else
            mFlags = mFlags & 0xffffffdf;
    }

    void setMenuInfo(android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        mMenuInfo = contextmenuinfo;
    }

    public MenuItem setNumericShortcut(char c)
    {
        if(mShortcutNumericChar == c)
        {
            return this;
        } else
        {
            mShortcutNumericChar = c;
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setNumericShortcut(char c, int i)
    {
        if(mShortcutNumericChar == c && mShortcutNumericModifiers == i)
        {
            return this;
        } else
        {
            mShortcutNumericChar = c;
            mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setOnActionExpandListener(android.view.MenuItem.OnActionExpandListener onactionexpandlistener)
    {
        mOnActionExpandListener = onactionexpandlistener;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener)
    {
        mClickListener = onmenuitemclicklistener;
        return this;
    }

    public MenuItem setShortcut(char c, char c1)
    {
        mShortcutNumericChar = c;
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char c, char c1, int i, int j)
    {
        mShortcutNumericChar = c;
        mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(j);
        mMenu.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i)
    {
        switch(i & 3)
        {
        default:
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            mShowAsAction = i;
            break;
        }
        mMenu.onItemActionRequestChanged(this);
    }

    public MenuItem setShowAsActionFlags(int i)
    {
        setShowAsAction(i);
        return this;
    }

    void setSubMenu(SubMenuBuilder submenubuilder)
    {
        mSubMenu = submenubuilder;
        submenubuilder.setHeaderTitle(getTitle());
    }

    public MenuItem setTitle(int i)
    {
        return setTitle(((CharSequence) (mMenu.getContext().getString(i))));
    }

    public MenuItem setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        mMenu.onItemsChanged(false);
        if(mSubMenu != null)
            mSubMenu.setHeaderTitle(charsequence);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charsequence)
    {
        mTitleCondensed = charsequence;
        if(charsequence == null)
            charsequence = mTitle;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setTooltipText(CharSequence charsequence)
    {
        mTooltipText = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setVisible(boolean flag)
    {
        if(setVisibleInt(flag))
            mMenu.onItemVisibleChanged(this);
        return this;
    }

    boolean setVisibleInt(boolean flag)
    {
        boolean flag1 = false;
        int i = mFlags;
        int j = mFlags;
        byte byte0;
        if(flag)
            byte0 = 0;
        else
            byte0 = 8;
        mFlags = byte0 | j & -9;
        flag = flag1;
        if(i != mFlags)
            flag = true;
        return flag;
    }

    public boolean shouldShowIcon()
    {
        return mMenu.getOptionalIconsVisible();
    }

    boolean shouldShowShortcut()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mMenu.isShortcutsVisible())
        {
            flag1 = flag;
            if(getShortcut() != 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean showsTextAsAction()
    {
        boolean flag;
        if((mShowAsAction & 4) == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        String s = null;
        if(mTitle != null)
            s = mTitle.toString();
        return s;
    }

    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 32;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private static String sDeleteShortcutLabel;
    private static String sEnterShortcutLabel;
    private static String sLanguage;
    private static String sPrependShortcutLabel;
    private static String sSpaceShortcutLabel;
    private ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private android.view.MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mFlags;
    private final int mGroup;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private int mIconResId;
    private ColorStateList mIconTintList;
    private android.graphics.PorterDuff.Mode mIconTintMode;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded;
    private Runnable mItemCallback;
    private MenuBuilder mMenu;
    private android.view.ContextMenu.ContextMenuInfo mMenuInfo;
    private boolean mNeedToApplyIconTint;
    private android.view.MenuItem.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;
}
