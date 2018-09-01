// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.EventLog;
import android.view.ContextMenu;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.view.menu:
//            MenuBuilder, MenuDialogHelper, MenuPopupHelper

public class ContextMenuBuilder extends MenuBuilder
    implements ContextMenu
{

    public ContextMenuBuilder(Context context)
    {
        super(context);
    }

    public ContextMenu setHeaderIcon(int i)
    {
        return (ContextMenu)super.setHeaderIconInt(i);
    }

    public ContextMenu setHeaderIcon(Drawable drawable)
    {
        return (ContextMenu)super.setHeaderIconInt(drawable);
    }

    public ContextMenu setHeaderTitle(int i)
    {
        return (ContextMenu)super.setHeaderTitleInt(i);
    }

    public ContextMenu setHeaderTitle(CharSequence charsequence)
    {
        return (ContextMenu)super.setHeaderTitleInt(charsequence);
    }

    public ContextMenu setHeaderView(View view)
    {
        return (ContextMenu)super.setHeaderViewInt(view);
    }

    public MenuDialogHelper showDialog(View view, IBinder ibinder)
    {
        if(view != null)
            view.createContextMenu(this);
        if(getVisibleItems().size() > 0)
        {
            EventLog.writeEvent(50001, 1);
            view = new MenuDialogHelper(this);
            view.show(ibinder);
            return view;
        } else
        {
            return null;
        }
    }

    public MenuPopupHelper showPopup(Context context, View view, float f, float f1)
    {
        if(view != null)
            view.createContextMenu(this);
        if(getVisibleItems().size() > 0)
        {
            EventLog.writeEvent(50001, 1);
            view.getLocationOnScreen(new int[2]);
            context = new MenuPopupHelper(context, this, view, false, 0x1010501);
            context.show(Math.round(f), Math.round(f1));
            return context;
        } else
        {
            return null;
        }
    }
}
