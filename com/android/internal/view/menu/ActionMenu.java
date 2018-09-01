// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.view.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.android.internal.view.menu:
//            ActionMenuItem

public class ActionMenu
    implements Menu
{

    public ActionMenu(Context context)
    {
        mContext = context;
        mItems = new ArrayList();
    }

    private int findItemIndex(int i)
    {
        ArrayList arraylist = mItems;
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
            if(((ActionMenuItem)arraylist.get(k)).getItemId() == i)
                return k;

        return -1;
    }

    private ActionMenuItem findItemWithShortcut(int i, KeyEvent keyevent)
    {
        boolean flag = mIsQwerty;
        ArrayList arraylist = mItems;
        int j = arraylist.size();
        int k = keyevent.getModifiers();
        char c;
        int i1;
        for(int l = 0; l < j; l++)
        {
            keyevent = (ActionMenuItem)arraylist.get(l);
            if(flag)
                c = keyevent.getAlphabeticShortcut();
            else
                c = keyevent.getNumericShortcut();
            if(flag)
                i1 = keyevent.getAlphabeticModifiers();
            else
                i1 = keyevent.getNumericModifiers();
            if((k & 0x1100f) == (i1 & 0x1100f))
                i1 = 1;
            else
                i1 = 0;
            if(i == c && i1 != 0)
                return keyevent;
        }

        return null;
    }

    public MenuItem add(int i)
    {
        return add(0, 0, 0, i);
    }

    public MenuItem add(int i, int j, int k, int l)
    {
        return add(i, j, k, ((CharSequence) (mContext.getResources().getString(l))));
    }

    public MenuItem add(int i, int j, int k, CharSequence charsequence)
    {
        charsequence = new ActionMenuItem(getContext(), i, j, 0, k, charsequence);
        mItems.add(k, charsequence);
        return charsequence;
    }

    public MenuItem add(CharSequence charsequence)
    {
        return add(0, 0, 0, charsequence);
    }

    public int addIntentOptions(int i, int j, int k, ComponentName componentname, Intent aintent[], Intent intent, int l, 
            MenuItem amenuitem[])
    {
        PackageManager packagemanager = mContext.getPackageManager();
        List list = packagemanager.queryIntentActivityOptions(componentname, aintent, intent, 0);
        int i1;
        if(list != null)
            i1 = list.size();
        else
            i1 = 0;
        if((l & 1) == 0)
            removeGroup(i);
        l = 0;
        while(l < i1) 
        {
            ResolveInfo resolveinfo = (ResolveInfo)list.get(l);
            if(resolveinfo.specificIndex < 0)
                componentname = intent;
            else
                componentname = aintent[resolveinfo.specificIndex];
            componentname = new Intent(componentname);
            componentname.setComponent(new ComponentName(resolveinfo.activityInfo.applicationInfo.packageName, resolveinfo.activityInfo.name));
            componentname = add(i, j, k, resolveinfo.loadLabel(packagemanager)).setIcon(resolveinfo.loadIcon(packagemanager)).setIntent(componentname);
            if(amenuitem != null && resolveinfo.specificIndex >= 0)
                amenuitem[resolveinfo.specificIndex] = componentname;
            l++;
        }
        return i1;
    }

    public SubMenu addSubMenu(int i)
    {
        return null;
    }

    public SubMenu addSubMenu(int i, int j, int k, int l)
    {
        return null;
    }

    public SubMenu addSubMenu(int i, int j, int k, CharSequence charsequence)
    {
        return null;
    }

    public SubMenu addSubMenu(CharSequence charsequence)
    {
        return null;
    }

    public void clear()
    {
        mItems.clear();
    }

    public void close()
    {
    }

    public MenuItem findItem(int i)
    {
        return (MenuItem)mItems.get(findItemIndex(i));
    }

    public Context getContext()
    {
        return mContext;
    }

    public MenuItem getItem(int i)
    {
        return (MenuItem)mItems.get(i);
    }

    public boolean hasVisibleItems()
    {
        ArrayList arraylist = mItems;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            if(((ActionMenuItem)arraylist.get(j)).isVisible())
                return true;

        return false;
    }

    public boolean isShortcutKey(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(findItemWithShortcut(i, keyevent) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean performIdentifierAction(int i, int j)
    {
        i = findItemIndex(i);
        if(i < 0)
            return false;
        else
            return ((ActionMenuItem)mItems.get(i)).invoke();
    }

    public boolean performShortcut(int i, KeyEvent keyevent, int j)
    {
        keyevent = findItemWithShortcut(i, keyevent);
        if(keyevent == null)
            return false;
        else
            return keyevent.invoke();
    }

    public void removeGroup(int i)
    {
        ArrayList arraylist = mItems;
        int j = arraylist.size();
        for(int k = 0; k < j;)
            if(((ActionMenuItem)arraylist.get(k)).getGroupId() == i)
            {
                arraylist.remove(k);
                j--;
            } else
            {
                k++;
            }

    }

    public void removeItem(int i)
    {
        mItems.remove(findItemIndex(i));
    }

    public void setGroupCheckable(int i, boolean flag, boolean flag1)
    {
        ArrayList arraylist = mItems;
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
        {
            ActionMenuItem actionmenuitem = (ActionMenuItem)arraylist.get(k);
            if(actionmenuitem.getGroupId() == i)
            {
                actionmenuitem.setCheckable(flag);
                actionmenuitem.setExclusiveCheckable(flag1);
            }
        }

    }

    public void setGroupEnabled(int i, boolean flag)
    {
        ArrayList arraylist = mItems;
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
        {
            ActionMenuItem actionmenuitem = (ActionMenuItem)arraylist.get(k);
            if(actionmenuitem.getGroupId() == i)
                actionmenuitem.setEnabled(flag);
        }

    }

    public void setGroupVisible(int i, boolean flag)
    {
        ArrayList arraylist = mItems;
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
        {
            ActionMenuItem actionmenuitem = (ActionMenuItem)arraylist.get(k);
            if(actionmenuitem.getGroupId() == i)
                actionmenuitem.setVisible(flag);
        }

    }

    public void setQwertyMode(boolean flag)
    {
        mIsQwerty = flag;
    }

    public int size()
    {
        return mItems.size();
    }

    private Context mContext;
    private boolean mIsQwerty;
    private ArrayList mItems;
}
