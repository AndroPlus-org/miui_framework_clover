// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.util.*;
import com.android.internal.view.menu.MenuItemImpl;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.view:
//            ActionProvider, InflateException, Menu, MenuItem, 
//            SubMenu, View

public class MenuInflater
{
    private static class InflatedOnMenuItemClickListener
        implements MenuItem.OnMenuItemClickListener
    {

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            try
            {
                if(mMethod.getReturnType() == Boolean.TYPE)
                    return ((Boolean)mMethod.invoke(mRealOwner, new Object[] {
                        menuitem
                    })).booleanValue();
                mMethod.invoke(mRealOwner, new Object[] {
                    menuitem
                });
            }
            // Misplaced declaration of an exception variable
            catch(MenuItem menuitem)
            {
                throw new RuntimeException(menuitem);
            }
            return true;
        }

        private static final Class PARAM_TYPES[] = {
            android/view/MenuItem
        };
        private Method mMethod;
        private Object mRealOwner;


        public InflatedOnMenuItemClickListener(Object obj, String s)
        {
            mRealOwner = obj;
            Class class1 = obj.getClass();
            try
            {
                mMethod = class1.getMethod(s, PARAM_TYPES);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                s = new InflateException((new StringBuilder()).append("Couldn't resolve menu item onClick handler ").append(s).append(" in class ").append(class1.getName()).toString());
            }
            s.initCause(((Throwable) (obj)));
            throw s;
        }
    }

    private class MenuState
    {

        static ActionProvider _2D_get0(MenuState menustate)
        {
            return menustate.itemActionProvider;
        }

        private char getShortcut(String s)
        {
            if(s == null)
                return '\0';
            else
                return s.charAt(0);
        }

        private Object newInstance(String s, Class aclass[], Object aobj[])
        {
            try
            {
                aclass = MenuInflater._2D_get4(MenuInflater.this).getClassLoader().loadClass(s).getConstructor(aclass);
                aclass.setAccessible(true);
                aclass = ((Class []) (aclass.newInstance(aobj)));
            }
            // Misplaced declaration of an exception variable
            catch(Class aclass[])
            {
                Log.w("MenuInflater", (new StringBuilder()).append("Cannot instantiate class: ").append(s).toString(), aclass);
                return null;
            }
            return aclass;
        }

        private void setItem(MenuItem menuitem)
        {
            boolean flag = false;
            MenuItem menuitem1 = menuitem.setChecked(itemChecked).setVisible(itemVisible).setEnabled(itemEnabled);
            if(itemCheckable >= 1)
                flag = true;
            menuitem1.setCheckable(flag).setTitleCondensed(itemTitleCondensed).setIcon(itemIconResId).setAlphabeticShortcut(itemAlphabeticShortcut, itemAlphabeticModifiers).setNumericShortcut(itemNumericShortcut, itemNumericModifiers);
            if(itemShowAsAction >= 0)
                menuitem.setShowAsAction(itemShowAsAction);
            if(itemIconTintMode != null)
                menuitem.setIconTintMode(itemIconTintMode);
            if(itemIconTintList != null)
                menuitem.setIconTintList(itemIconTintList);
            if(itemListenerMethodName != null)
            {
                if(MenuInflater._2D_get4(MenuInflater.this).isRestricted())
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                menuitem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(MenuInflater._2D_wrap0(MenuInflater.this), itemListenerMethodName));
            }
            if(menuitem instanceof MenuItemImpl)
            {
                MenuItemImpl menuitemimpl = (MenuItemImpl)menuitem;
                if(itemCheckable >= 2)
                    menuitemimpl.setExclusiveCheckable(true);
            }
            boolean flag1 = false;
            if(itemActionViewClassName != null)
            {
                menuitem.setActionView((View)newInstance(itemActionViewClassName, MenuInflater._2D_get1(), MenuInflater._2D_get3(MenuInflater.this)));
                flag1 = true;
            }
            if(itemActionViewLayout > 0)
                if(!flag1)
                    menuitem.setActionView(itemActionViewLayout);
                else
                    Log.w("MenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            if(itemActionProvider != null)
                menuitem.setActionProvider(itemActionProvider);
            menuitem.setContentDescription(itemContentDescription);
            menuitem.setTooltipText(itemTooltipText);
        }

        public MenuItem addItem()
        {
            itemAdded = true;
            MenuItem menuitem = menu.add(groupId, itemId, itemCategoryOrder, itemTitle);
            setItem(menuitem);
            return menuitem;
        }

        public SubMenu addSubMenuItem()
        {
            itemAdded = true;
            SubMenu submenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
            setItem(submenu.getItem());
            return submenu;
        }

        public boolean hasAddedItem()
        {
            return itemAdded;
        }

        public void readGroup(AttributeSet attributeset)
        {
            attributeset = MenuInflater._2D_get4(MenuInflater.this).obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MenuGroup);
            groupId = attributeset.getResourceId(1, 0);
            groupCategory = attributeset.getInt(3, 0);
            groupOrder = attributeset.getInt(4, 0);
            groupCheckable = attributeset.getInt(5, 0);
            groupVisible = attributeset.getBoolean(2, true);
            groupEnabled = attributeset.getBoolean(0, true);
            attributeset.recycle();
        }

        public void readItem(AttributeSet attributeset)
        {
            attributeset = MenuInflater._2D_get4(MenuInflater.this).obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MenuItem);
            itemId = attributeset.getResourceId(2, 0);
            itemCategoryOrder = 0xffff0000 & attributeset.getInt(5, groupCategory) | 0xffff & attributeset.getInt(6, groupOrder);
            itemTitle = attributeset.getText(7);
            itemTitleCondensed = attributeset.getText(8);
            itemIconResId = attributeset.getResourceId(0, 0);
            int i;
            if(attributeset.hasValue(22))
                itemIconTintMode = Drawable.parseTintMode(attributeset.getInt(22, -1), itemIconTintMode);
            else
                itemIconTintMode = null;
            if(attributeset.hasValue(21))
                itemIconTintList = attributeset.getColorStateList(21);
            else
                itemIconTintList = null;
            itemAlphabeticShortcut = getShortcut(attributeset.getString(9));
            itemAlphabeticModifiers = attributeset.getInt(19, 4096);
            itemNumericShortcut = getShortcut(attributeset.getString(10));
            itemNumericModifiers = attributeset.getInt(20, 4096);
            if(attributeset.hasValue(11))
            {
                if(attributeset.getBoolean(11, false))
                    i = 1;
                else
                    i = 0;
                itemCheckable = i;
            } else
            {
                itemCheckable = groupCheckable;
            }
            itemChecked = attributeset.getBoolean(3, false);
            itemVisible = attributeset.getBoolean(4, groupVisible);
            itemEnabled = attributeset.getBoolean(1, groupEnabled);
            itemShowAsAction = attributeset.getInt(14, -1);
            itemListenerMethodName = attributeset.getString(12);
            itemActionViewLayout = attributeset.getResourceId(15, 0);
            itemActionViewClassName = attributeset.getString(16);
            itemActionProviderClassName = attributeset.getString(17);
            if(itemActionProviderClassName != null)
                i = 1;
            else
                i = 0;
            if(i != 0 && itemActionViewLayout == 0 && itemActionViewClassName == null)
            {
                itemActionProvider = (ActionProvider)newInstance(itemActionProviderClassName, MenuInflater._2D_get0(), MenuInflater._2D_get2(MenuInflater.this));
            } else
            {
                if(i != 0)
                    Log.w("MenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                itemActionProvider = null;
            }
            itemContentDescription = attributeset.getText(13);
            itemTooltipText = attributeset.getText(18);
            attributeset.recycle();
            itemAdded = false;
        }

        public void resetGroup()
        {
            groupId = 0;
            groupCategory = 0;
            groupOrder = 0;
            groupCheckable = 0;
            groupVisible = true;
            groupEnabled = true;
        }

        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        private ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList;
        private android.graphics.PorterDuff.Mode itemIconTintMode;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;
        final MenuInflater this$0;

        public MenuState(Menu menu1)
        {
            this$0 = MenuInflater.this;
            super();
            itemIconTintList = null;
            itemIconTintMode = null;
            menu = menu1;
            resetGroup();
        }
    }


    static Class[] _2D_get0()
    {
        return ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE;
    }

    static Class[] _2D_get1()
    {
        return ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    }

    static Object[] _2D_get2(MenuInflater menuinflater)
    {
        return menuinflater.mActionProviderConstructorArguments;
    }

    static Object[] _2D_get3(MenuInflater menuinflater)
    {
        return menuinflater.mActionViewConstructorArguments;
    }

    static Context _2D_get4(MenuInflater menuinflater)
    {
        return menuinflater.mContext;
    }

    static Object _2D_wrap0(MenuInflater menuinflater)
    {
        return menuinflater.getRealOwner();
    }

    public MenuInflater(Context context)
    {
        mContext = context;
        mActionViewConstructorArguments = (new Object[] {
            context
        });
        mActionProviderConstructorArguments = mActionViewConstructorArguments;
    }

    public MenuInflater(Context context, Object obj)
    {
        mContext = context;
        mRealOwner = obj;
        mActionViewConstructorArguments = (new Object[] {
            context
        });
        mActionProviderConstructorArguments = mActionViewConstructorArguments;
    }

    private Object findRealOwner(Object obj)
    {
        if(obj instanceof Activity)
            return obj;
        if(obj instanceof ContextWrapper)
            return findRealOwner(((ContextWrapper)obj).getBaseContext());
        else
            return obj;
    }

    private Object getRealOwner()
    {
        if(mRealOwner == null)
            mRealOwner = findRealOwner(mContext);
        return mRealOwner;
    }

    private void parseMenu(XmlPullParser xmlpullparser, AttributeSet attributeset, Menu menu)
        throws XmlPullParserException, IOException
    {
        MenuState menustate;
        int i;
        boolean flag;
        Menu menu1;
        menustate = new MenuState(menu);
        i = xmlpullparser.getEventType();
        flag = false;
        menu1 = null;
_L7:
        if(i != 2) goto _L2; else goto _L1
_L1:
        menu = xmlpullparser.getName();
        if(!menu.equals("menu")) goto _L4; else goto _L3
_L3:
        int j = xmlpullparser.next();
_L8:
        int k;
        i = 0;
        k = j;
_L6:
        int l;
        if(i != 0)
            break MISSING_BLOCK_LABEL_514;
        switch(k)
        {
        default:
            menu = menu1;
            l = i;
            j = ((flag) ? 1 : 0);
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_504;

        case 2: // '\002'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
            break MISSING_BLOCK_LABEL_310;
        }
_L9:
        k = xmlpullparser.next();
        flag = j;
        i = l;
        menu1 = menu;
        if(true) goto _L6; else goto _L5
_L4:
        throw new RuntimeException((new StringBuilder()).append("Expecting menu, got ").append(menu).toString());
_L2:
        i = xmlpullparser.next();
        j = i;
        if(i == 1) goto _L8; else goto _L7
_L5:
        j = ((flag) ? 1 : 0);
        l = i;
        menu = menu1;
        if(!flag)
        {
            menu = xmlpullparser.getName();
            if(menu.equals("group"))
            {
                menustate.readGroup(attributeset);
                j = ((flag) ? 1 : 0);
                l = i;
                menu = menu1;
            } else
            if(menu.equals("item"))
            {
                menustate.readItem(attributeset);
                j = ((flag) ? 1 : 0);
                l = i;
                menu = menu1;
            } else
            if(menu.equals("menu"))
            {
                menu = menustate.addSubMenuItem();
                registerMenu(menu, attributeset);
                parseMenu(xmlpullparser, attributeset, menu);
                j = ((flag) ? 1 : 0);
                l = i;
                menu = menu1;
            } else
            {
                j = 1;
                l = i;
            }
        }
          goto _L9
        String s = xmlpullparser.getName();
        if(flag && s.equals(menu1))
        {
            j = 0;
            menu = null;
            l = i;
        } else
        if(s.equals("group"))
        {
            menustate.resetGroup();
            j = ((flag) ? 1 : 0);
            l = i;
            menu = menu1;
        } else
        if(s.equals("item"))
        {
            j = ((flag) ? 1 : 0);
            l = i;
            menu = menu1;
            if(!menustate.hasAddedItem())
                if(MenuState._2D_get0(menustate) != null && MenuState._2D_get0(menustate).hasSubMenu())
                {
                    registerMenu(menustate.addSubMenuItem(), attributeset);
                    j = ((flag) ? 1 : 0);
                    l = i;
                    menu = menu1;
                } else
                {
                    registerMenu(menustate.addItem(), attributeset);
                    j = ((flag) ? 1 : 0);
                    l = i;
                    menu = menu1;
                }
        } else
        {
            j = ((flag) ? 1 : 0);
            l = i;
            menu = menu1;
            if(s.equals("menu"))
            {
                l = 1;
                j = ((flag) ? 1 : 0);
                menu = menu1;
            }
        }
          goto _L9
        throw new RuntimeException("Unexpected end of document");
    }

    private void registerMenu(MenuItem menuitem, AttributeSet attributeset)
    {
    }

    private void registerMenu(SubMenu submenu, AttributeSet attributeset)
    {
    }

    Context getContext()
    {
        return mContext;
    }

    public void inflate(int i, Menu menu)
    {
        Object obj;
        Object obj1;
        XmlResourceParser xmlresourceparser;
        obj = null;
        obj1 = null;
        xmlresourceparser = null;
        XmlResourceParser xmlresourceparser1 = mContext.getResources().getLayout(i);
        xmlresourceparser = xmlresourceparser1;
        obj = xmlresourceparser1;
        obj1 = xmlresourceparser1;
        parseMenu(xmlresourceparser1, Xml.asAttributeSet(xmlresourceparser1), menu);
        if(xmlresourceparser1 != null)
            xmlresourceparser1.close();
        return;
        menu;
        obj = xmlresourceparser;
        obj1 = JVM INSTR new #200 <Class InflateException>;
        obj = xmlresourceparser;
        ((InflateException) (obj1)).InflateException("Error inflating menu XML", menu);
        obj = xmlresourceparser;
        throw obj1;
        menu;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw menu;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        obj = obj1;
        menu = JVM INSTR new #200 <Class InflateException>;
        obj = obj1;
        menu.InflateException("Error inflating menu XML", xmlpullparserexception);
        obj = obj1;
        throw menu;
    }

    private static final Class ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE[] = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    private static final Class ACTION_VIEW_CONSTRUCTOR_SIGNATURE[] = {
        android/content/Context
    };
    private static final String LOG_TAG = "MenuInflater";
    private static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    private final Object mActionProviderConstructorArguments[];
    private final Object mActionViewConstructorArguments[];
    private Context mContext;
    private Object mRealOwner;

}
