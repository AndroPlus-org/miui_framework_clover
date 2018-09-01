// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.view.MenuItem;
import com.android.internal.view.menu.MenuBuilder;

public interface MenuItemHoverListener
{

    public abstract void onItemHoverEnter(MenuBuilder menubuilder, MenuItem menuitem);

    public abstract void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem);
}
