// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.SpinnerAdapter;
import android.widget.Toolbar;
import com.android.internal.view.menu.MenuBuilder;
import com.android.internal.widget.DecorToolbar;
import com.android.internal.widget.ToolbarWidgetWrapper;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.app:
//            NavItemSelectedListener

public class ToolbarActionBar extends ActionBar
{
    private final class ActionMenuPresenterCallback
        implements com.android.internal.view.menu.MenuPresenter.Callback
    {

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            if(mClosingActionMenu)
                return;
            mClosingActionMenu = true;
            ToolbarActionBar._2D_get0(ToolbarActionBar.this).dismissPopupMenus();
            if(ToolbarActionBar._2D_get2(ToolbarActionBar.this) != null)
                ToolbarActionBar._2D_get2(ToolbarActionBar.this).onPanelClosed(8, menubuilder);
            mClosingActionMenu = false;
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if(ToolbarActionBar._2D_get2(ToolbarActionBar.this) != null)
            {
                ToolbarActionBar._2D_get2(ToolbarActionBar.this).onMenuOpened(8, menubuilder);
                return true;
            } else
            {
                return false;
            }
        }

        private boolean mClosingActionMenu;
        final ToolbarActionBar this$0;

        private ActionMenuPresenterCallback()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }

        ActionMenuPresenterCallback(ActionMenuPresenterCallback actionmenupresentercallback)
        {
            this();
        }
    }

    private final class MenuBuilderCallback
        implements com.android.internal.view.menu.MenuBuilder.Callback
    {

        public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menubuilder)
        {
            if(ToolbarActionBar._2D_get2(ToolbarActionBar.this) == null) goto _L2; else goto _L1
_L1:
            if(!ToolbarActionBar._2D_get0(ToolbarActionBar.this).isOverflowMenuShowing()) goto _L4; else goto _L3
_L3:
            ToolbarActionBar._2D_get2(ToolbarActionBar.this).onPanelClosed(8, menubuilder);
_L2:
            return;
_L4:
            if(ToolbarActionBar._2D_get2(ToolbarActionBar.this).onPreparePanel(0, null, menubuilder))
                ToolbarActionBar._2D_get2(ToolbarActionBar.this).onMenuOpened(8, menubuilder);
            if(true) goto _L2; else goto _L5
_L5:
        }

        final ToolbarActionBar this$0;

        private MenuBuilderCallback()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }

        MenuBuilderCallback(MenuBuilderCallback menubuildercallback)
        {
            this();
        }
    }

    private class ToolbarCallbackWrapper extends WindowCallbackWrapper
    {

        public View onCreatePanelView(int i)
        {
            if(i == 0)
                return new View(ToolbarActionBar._2D_get0(ToolbarActionBar.this).getContext());
            else
                return super.onCreatePanelView(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu)
        {
            boolean flag = super.onPreparePanel(i, view, menu);
            if(flag && ToolbarActionBar._2D_get1(ToolbarActionBar.this) ^ true)
            {
                ToolbarActionBar._2D_get0(ToolbarActionBar.this).setMenuPrepared();
                ToolbarActionBar._2D_set0(ToolbarActionBar.this, true);
            }
            return flag;
        }

        final ToolbarActionBar this$0;

        public ToolbarCallbackWrapper(android.view.Window.Callback callback)
        {
            this$0 = ToolbarActionBar.this;
            super(callback);
        }
    }


    static DecorToolbar _2D_get0(ToolbarActionBar toolbaractionbar)
    {
        return toolbaractionbar.mDecorToolbar;
    }

    static boolean _2D_get1(ToolbarActionBar toolbaractionbar)
    {
        return toolbaractionbar.mToolbarMenuPrepared;
    }

    static android.view.Window.Callback _2D_get2(ToolbarActionBar toolbaractionbar)
    {
        return toolbaractionbar.mWindowCallback;
    }

    static boolean _2D_set0(ToolbarActionBar toolbaractionbar, boolean flag)
    {
        toolbaractionbar.mToolbarMenuPrepared = flag;
        return flag;
    }

    public ToolbarActionBar(Toolbar toolbar, CharSequence charsequence, android.view.Window.Callback callback)
    {
        mMenuVisibilityListeners = new ArrayList();
        mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        mWindowCallback = new ToolbarCallbackWrapper(callback);
        mDecorToolbar.setWindowCallback(mWindowCallback);
        toolbar.setOnMenuItemClickListener(mMenuClicker);
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public void addOnMenuVisibilityListener(android.app.ActionBar.OnMenuVisibilityListener onmenuvisibilitylistener)
    {
        mMenuVisibilityListeners.add(onmenuvisibilitylistener);
    }

    public void addTab(android.app.ActionBar.Tab tab)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(android.app.ActionBar.Tab tab, int i)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(android.app.ActionBar.Tab tab, int i, boolean flag)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(android.app.ActionBar.Tab tab, boolean flag)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public boolean closeOptionsMenu()
    {
        return mDecorToolbar.hideOverflowMenu();
    }

    public boolean collapseActionView()
    {
        if(mDecorToolbar.hasExpandedActionView())
        {
            mDecorToolbar.collapseActionView();
            return true;
        } else
        {
            return false;
        }
    }

    public void dispatchMenuVisibilityChanged(boolean flag)
    {
        if(flag == mLastMenuVisibility)
            return;
        mLastMenuVisibility = flag;
        int i = mMenuVisibilityListeners.size();
        for(int j = 0; j < i; j++)
            ((android.app.ActionBar.OnMenuVisibilityListener)mMenuVisibilityListeners.get(j)).onMenuVisibilityChanged(flag);

    }

    public View getCustomView()
    {
        return mDecorToolbar.getCustomView();
    }

    public int getDisplayOptions()
    {
        return mDecorToolbar.getDisplayOptions();
    }

    public float getElevation()
    {
        return mDecorToolbar.getViewGroup().getElevation();
    }

    public int getHeight()
    {
        return mDecorToolbar.getHeight();
    }

    public int getNavigationItemCount()
    {
        return 0;
    }

    public int getNavigationMode()
    {
        return 0;
    }

    public int getSelectedNavigationIndex()
    {
        return -1;
    }

    public android.app.ActionBar.Tab getSelectedTab()
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public CharSequence getSubtitle()
    {
        return mDecorToolbar.getSubtitle();
    }

    public android.app.ActionBar.Tab getTabAt(int i)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public int getTabCount()
    {
        return 0;
    }

    public Context getThemedContext()
    {
        return mDecorToolbar.getContext();
    }

    public CharSequence getTitle()
    {
        return mDecorToolbar.getTitle();
    }

    public android.view.Window.Callback getWrappedWindowCallback()
    {
        return mWindowCallback;
    }

    public void hide()
    {
        mDecorToolbar.setVisibility(8);
    }

    public boolean invalidateOptionsMenu()
    {
        mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
        mDecorToolbar.getViewGroup().postOnAnimation(mMenuInvalidator);
        return true;
    }

    public boolean isShowing()
    {
        boolean flag = false;
        if(mDecorToolbar.getVisibility() == 0)
            flag = true;
        return flag;
    }

    public boolean isTitleTruncated()
    {
        return super.isTitleTruncated();
    }

    public android.app.ActionBar.Tab newTab()
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy()
    {
        mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        Menu menu = mDecorToolbar.getMenu();
        if(menu != null)
        {
            int j;
            boolean flag;
            if(keyevent != null)
                j = keyevent.getDeviceId();
            else
                j = -1;
            if(KeyCharacterMap.load(j).getKeyboardType() != 1)
                flag = true;
            else
                flag = false;
            menu.setQwertyMode(flag);
            return menu.performShortcut(i, keyevent, 0);
        } else
        {
            return false;
        }
    }

    public boolean onMenuKeyEvent(KeyEvent keyevent)
    {
        if(keyevent.getAction() == 1)
            openOptionsMenu();
        return true;
    }

    public boolean openOptionsMenu()
    {
        return mDecorToolbar.showOverflowMenu();
    }

    void populateOptionsMenu()
    {
        MenuBuilder menubuilder;
        if(!mMenuCallbackSet)
        {
            mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(null), new MenuBuilderCallback(null));
            mMenuCallbackSet = true;
        }
        Menu menu = mDecorToolbar.getMenu();
        if(menu instanceof MenuBuilder)
            menubuilder = (MenuBuilder)menu;
        else
            menubuilder = null;
        if(menubuilder != null)
            menubuilder.stopDispatchingItemsChanged();
        menu.clear();
        if(!mWindowCallback.onCreatePanelMenu(0, menu) || mWindowCallback.onPreparePanel(0, null, menu) ^ true)
            menu.clear();
        if(menubuilder != null)
            menubuilder.startDispatchingItemsChanged();
        return;
        Exception exception;
        exception;
        if(menubuilder != null)
            menubuilder.startDispatchingItemsChanged();
        throw exception;
    }

    public void removeAllTabs()
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeOnMenuVisibilityListener(android.app.ActionBar.OnMenuVisibilityListener onmenuvisibilitylistener)
    {
        mMenuVisibilityListeners.remove(onmenuvisibilitylistener);
    }

    public void removeTab(android.app.ActionBar.Tab tab)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeTabAt(int i)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void selectTab(android.app.ActionBar.Tab tab)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        mDecorToolbar.setBackgroundDrawable(drawable);
    }

    public void setCustomView(int i)
    {
        setCustomView(LayoutInflater.from(mDecorToolbar.getContext()).inflate(i, mDecorToolbar.getViewGroup(), false));
    }

    public void setCustomView(View view)
    {
        setCustomView(view, new android.app.ActionBar.LayoutParams(-2, -2));
    }

    public void setCustomView(View view, android.app.ActionBar.LayoutParams layoutparams)
    {
        if(view != null)
            view.setLayoutParams(layoutparams);
        mDecorToolbar.setCustomView(view);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean flag)
    {
    }

    public void setDisplayHomeAsUpEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 4);
    }

    public void setDisplayOptions(int i)
    {
        setDisplayOptions(i, -1);
    }

    public void setDisplayOptions(int i, int j)
    {
        int k = mDecorToolbar.getDisplayOptions();
        mDecorToolbar.setDisplayOptions(i & j | j & k);
    }

    public void setDisplayShowCustomEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 16;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 16);
    }

    public void setDisplayShowHomeEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 2);
    }

    public void setDisplayShowTitleEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 8;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 8);
    }

    public void setDisplayUseLogoEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        setDisplayOptions(i, 1);
    }

    public void setElevation(float f)
    {
        mDecorToolbar.getViewGroup().setElevation(f);
    }

    public void setHomeActionContentDescription(int i)
    {
        mDecorToolbar.setNavigationContentDescription(i);
    }

    public void setHomeActionContentDescription(CharSequence charsequence)
    {
        mDecorToolbar.setNavigationContentDescription(charsequence);
    }

    public void setHomeAsUpIndicator(int i)
    {
        mDecorToolbar.setNavigationIcon(i);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        mDecorToolbar.setNavigationIcon(drawable);
    }

    public void setHomeButtonEnabled(boolean flag)
    {
    }

    public void setIcon(int i)
    {
        mDecorToolbar.setIcon(i);
    }

    public void setIcon(Drawable drawable)
    {
        mDecorToolbar.setIcon(drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinneradapter, android.app.ActionBar.OnNavigationListener onnavigationlistener)
    {
        mDecorToolbar.setDropdownParams(spinneradapter, new NavItemSelectedListener(onnavigationlistener));
    }

    public void setLogo(int i)
    {
        mDecorToolbar.setLogo(i);
    }

    public void setLogo(Drawable drawable)
    {
        mDecorToolbar.setLogo(drawable);
    }

    public void setNavigationMode(int i)
    {
        if(i == 2)
        {
            throw new IllegalArgumentException("Tabs not supported in this configuration");
        } else
        {
            mDecorToolbar.setNavigationMode(i);
            return;
        }
    }

    public void setSelectedNavigationItem(int i)
    {
        switch(mDecorToolbar.getNavigationMode())
        {
        default:
            throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");

        case 1: // '\001'
            mDecorToolbar.setDropdownSelectedPosition(i);
            break;
        }
    }

    public void setShowHideAnimationEnabled(boolean flag)
    {
    }

    public void setSplitBackgroundDrawable(Drawable drawable)
    {
    }

    public void setStackedBackgroundDrawable(Drawable drawable)
    {
    }

    public void setSubtitle(int i)
    {
        DecorToolbar decortoolbar = mDecorToolbar;
        CharSequence charsequence;
        if(i != 0)
            charsequence = mDecorToolbar.getContext().getText(i);
        else
            charsequence = null;
        decortoolbar.setSubtitle(charsequence);
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mDecorToolbar.setSubtitle(charsequence);
    }

    public void setTitle(int i)
    {
        DecorToolbar decortoolbar = mDecorToolbar;
        CharSequence charsequence;
        if(i != 0)
            charsequence = mDecorToolbar.getContext().getText(i);
        else
            charsequence = null;
        decortoolbar.setTitle(charsequence);
    }

    public void setTitle(CharSequence charsequence)
    {
        mDecorToolbar.setTitle(charsequence);
    }

    public void setWindowTitle(CharSequence charsequence)
    {
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public void show()
    {
        mDecorToolbar.setVisibility(0);
    }

    public ActionMode startActionMode(android.view.ActionMode.Callback callback)
    {
        return null;
    }

    private DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private boolean mMenuCallbackSet;
    private final android.widget.Toolbar.OnMenuItemClickListener mMenuClicker = new android.widget.Toolbar.OnMenuItemClickListener() {

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            return ToolbarActionBar._2D_get2(ToolbarActionBar.this).onMenuItemSelected(0, menuitem);
        }

        final ToolbarActionBar this$0;

            
            {
                this$0 = ToolbarActionBar.this;
                super();
            }
    }
;
    private final Runnable mMenuInvalidator = new Runnable() {

        public void run()
        {
            populateOptionsMenu();
        }

        final ToolbarActionBar this$0;

            
            {
                this$0 = ToolbarActionBar.this;
                super();
            }
    }
;
    private ArrayList mMenuVisibilityListeners;
    private boolean mToolbarMenuPrepared;
    private android.view.Window.Callback mWindowCallback;
}
