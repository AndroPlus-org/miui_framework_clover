// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.view:
//            ActionProvider, View, SubMenu

public interface MenuItem
{
    public static interface OnActionExpandListener
    {

        public abstract boolean onMenuItemActionCollapse(MenuItem menuitem);

        public abstract boolean onMenuItemActionExpand(MenuItem menuitem);
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
    }


    public abstract boolean collapseActionView();

    public abstract boolean expandActionView();

    public abstract ActionProvider getActionProvider();

    public abstract View getActionView();

    public int getAlphabeticModifiers()
    {
        return 4096;
    }

    public abstract char getAlphabeticShortcut();

    public CharSequence getContentDescription()
    {
        return null;
    }

    public abstract int getGroupId();

    public abstract Drawable getIcon();

    public ColorStateList getIconTintList()
    {
        return null;
    }

    public android.graphics.PorterDuff.Mode getIconTintMode()
    {
        return null;
    }

    public abstract Intent getIntent();

    public abstract int getItemId();

    public abstract ContextMenu.ContextMenuInfo getMenuInfo();

    public int getNumericModifiers()
    {
        return 4096;
    }

    public abstract char getNumericShortcut();

    public abstract int getOrder();

    public abstract SubMenu getSubMenu();

    public abstract CharSequence getTitle();

    public abstract CharSequence getTitleCondensed();

    public CharSequence getTooltipText()
    {
        return null;
    }

    public abstract boolean hasSubMenu();

    public abstract boolean isActionViewExpanded();

    public abstract boolean isCheckable();

    public abstract boolean isChecked();

    public abstract boolean isEnabled();

    public abstract boolean isVisible();

    public boolean requiresOverflow()
    {
        return false;
    }

    public abstract MenuItem setActionProvider(ActionProvider actionprovider);

    public abstract MenuItem setActionView(int i);

    public abstract MenuItem setActionView(View view);

    public abstract MenuItem setAlphabeticShortcut(char c);

    public MenuItem setAlphabeticShortcut(char c, int i)
    {
        if((0x1100f & i) == 4096)
            return setAlphabeticShortcut(c);
        else
            return this;
    }

    public abstract MenuItem setCheckable(boolean flag);

    public abstract MenuItem setChecked(boolean flag);

    public MenuItem setContentDescription(CharSequence charsequence)
    {
        return this;
    }

    public abstract MenuItem setEnabled(boolean flag);

    public abstract MenuItem setIcon(int i);

    public abstract MenuItem setIcon(Drawable drawable);

    public MenuItem setIconTintList(ColorStateList colorstatelist)
    {
        return this;
    }

    public MenuItem setIconTintMode(android.graphics.PorterDuff.Mode mode)
    {
        return this;
    }

    public abstract MenuItem setIntent(Intent intent);

    public abstract MenuItem setNumericShortcut(char c);

    public MenuItem setNumericShortcut(char c, int i)
    {
        if((0x1100f & i) == 4096)
            return setNumericShortcut(c);
        else
            return this;
    }

    public abstract MenuItem setOnActionExpandListener(OnActionExpandListener onactionexpandlistener);

    public abstract MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onmenuitemclicklistener);

    public abstract MenuItem setShortcut(char c, char c1);

    public MenuItem setShortcut(char c, char c1, int i, int j)
    {
        if((j & 0x1100f) == 4096 && (i & 0x1100f) == 4096)
            return setShortcut(c, c1);
        else
            return this;
    }

    public abstract void setShowAsAction(int i);

    public abstract MenuItem setShowAsActionFlags(int i);

    public abstract MenuItem setTitle(int i);

    public abstract MenuItem setTitle(CharSequence charsequence);

    public abstract MenuItem setTitleCondensed(CharSequence charsequence);

    public MenuItem setTooltipText(CharSequence charsequence)
    {
        return this;
    }

    public abstract MenuItem setVisible(boolean flag);

    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    public static final int SHOW_AS_OVERFLOW_ALWAYS = 0x80000000;
}
