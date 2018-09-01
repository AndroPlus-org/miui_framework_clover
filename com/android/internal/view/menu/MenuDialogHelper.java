// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.IBinder;
import android.view.*;
import android.widget.ListAdapter;

// Referenced classes of package com.android.internal.view.menu:
//            MenuHelper, ListMenuPresenter, MenuItemImpl, MenuBuilder

public class MenuDialogHelper
    implements MenuHelper, android.content.DialogInterface.OnKeyListener, android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnDismissListener, MenuPresenter.Callback
{

    public MenuDialogHelper(MenuBuilder menubuilder)
    {
        mMenu = menubuilder;
    }

    public void dismiss()
    {
        if(mDialog != null)
            mDialog.dismiss();
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        mMenu.performItemAction((MenuItemImpl)mPresenter.getAdapter().getItem(i), 0);
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if(flag || menubuilder == mMenu)
            dismiss();
        if(mPresenterCallback != null)
            mPresenterCallback.onCloseMenu(menubuilder, flag);
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        mPresenter.onCloseMenu(mMenu, true);
    }

    public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
    {
        if(i == 82 || i == 4)
            if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                dialoginterface = mDialog.getWindow();
                if(dialoginterface != null)
                {
                    dialoginterface = dialoginterface.getDecorView();
                    if(dialoginterface != null)
                    {
                        dialoginterface = dialoginterface.getKeyDispatcherState();
                        if(dialoginterface != null)
                        {
                            dialoginterface.startTracking(keyevent, this);
                            return true;
                        }
                    }
                }
            } else
            if(keyevent.getAction() == 1 && keyevent.isCanceled() ^ true)
            {
                Object obj = mDialog.getWindow();
                if(obj != null)
                {
                    obj = ((Window) (obj)).getDecorView();
                    if(obj != null)
                    {
                        obj = ((View) (obj)).getKeyDispatcherState();
                        if(obj != null && ((android.view.KeyEvent.DispatcherState) (obj)).isTracking(keyevent))
                        {
                            mMenu.close(true);
                            dialoginterface.dismiss();
                            return true;
                        }
                    }
                }
            }
        return mMenu.performShortcut(i, keyevent, 0);
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        if(mPresenterCallback != null)
            return mPresenterCallback.onOpenSubMenu(menubuilder);
        else
            return false;
    }

    public void setPresenterCallback(MenuPresenter.Callback callback)
    {
        mPresenterCallback = callback;
    }

    public void show(IBinder ibinder)
    {
        MenuBuilder menubuilder = mMenu;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(menubuilder.getContext());
        mPresenter = new ListMenuPresenter(builder.getContext(), 0x109007c);
        mPresenter.setCallback(this);
        mMenu.addMenuPresenter(mPresenter);
        builder.setAdapter(mPresenter.getAdapter(), this);
        Object obj = menubuilder.getHeaderView();
        if(obj != null)
            builder.setCustomTitle(((View) (obj)));
        else
            builder.setIcon(menubuilder.getHeaderIcon()).setTitle(menubuilder.getHeaderTitle());
        builder.setOnKeyListener(this);
        mDialog = builder.create();
        mDialog.setOnDismissListener(this);
        obj = mDialog.getWindow().getAttributes();
        obj.type = 1003;
        if(ibinder != null)
            obj.token = ibinder;
        obj.flags = ((android.view.WindowManager.LayoutParams) (obj)).flags | 0x20000;
        mDialog.show();
    }

    private AlertDialog mDialog;
    private MenuBuilder mMenu;
    ListMenuPresenter mPresenter;
    private MenuPresenter.Callback mPresenterCallback;
}
