// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.ContentResolver;
import android.content.Context;
import java.util.Iterator;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.data:
//            VariableBinder, Variables

public class SettingsBinder extends VariableBinder
{
    private static final class Category extends Enum
    {

        public static Category valueOf(String s)
        {
            return (Category)Enum.valueOf(miui/maml/data/SettingsBinder$Category, s);
        }

        public static Category[] values()
        {
            return $VALUES;
        }

        private static final Category $VALUES[];
        public static final Category Secure;
        public static final Category System;

        static 
        {
            Secure = new Category("Secure", 0);
            System = new Category("System", 1);
            $VALUES = (new Category[] {
                Secure, System
            });
        }

        private Category(String s, int i)
        {
            super(s, i);
        }
    }

    private class Variable extends VariableBinder.Variable
    {

        private static int[] _2D_getmiui_2D_maml_2D_data_2D_SettingsBinder$CategorySwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_data_2D_SettingsBinder$CategorySwitchesValues != null)
                return _2D_miui_2D_maml_2D_data_2D_SettingsBinder$CategorySwitchesValues;
            int ai[] = new int[Category.values().length];
            try
            {
                ai[Category.Secure.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Category.System.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_data_2D_SettingsBinder$CategorySwitchesValues = ai;
            return ai;
        }

        public void query()
        {
            _2D_getmiui_2D_maml_2D_data_2D_SettingsBinder$CategorySwitchesValues()[mCategory.ordinal()];
            JVM INSTR tableswitch 1 2: default 32
        //                       1 190
        //                       2 33;
               goto _L1 _L2 _L3
_L5:
            return;
_L3:
            switch(mType)
            {
            case 2: // '\002'
                String s = android.provider.Settings.System.getString(SettingsBinder._2D_get0(SettingsBinder.this), mKey);
                String s2 = s;
                if(s == null)
                    s2 = mDefStringValue;
                set(s2);
                break;

            case 5: // '\005'
            case 6: // '\006'
                set(android.provider.Settings.System.getFloat(SettingsBinder._2D_get0(SettingsBinder.this), mKey, (float)mDefNumberValue));
                break;

            case 3: // '\003'
                set(android.provider.Settings.System.getInt(SettingsBinder._2D_get0(SettingsBinder.this), mKey, (int)mDefNumberValue));
                break;

            case 4: // '\004'
                set(android.provider.Settings.System.getLong(SettingsBinder._2D_get0(SettingsBinder.this), mKey, (long)mDefNumberValue));
                break;
            }
_L1:
            if(true) goto _L5; else goto _L4
_L4:
_L2:
            switch(mType)
            {
            case 2: // '\002'
                String s1 = android.provider.Settings.Secure.getString(SettingsBinder._2D_get0(SettingsBinder.this), mKey);
                String s3 = s1;
                if(s1 == null)
                    s3 = mDefStringValue;
                set(s3);
                break;

            case 5: // '\005'
            case 6: // '\006'
                set(android.provider.Settings.Secure.getFloat(SettingsBinder._2D_get0(SettingsBinder.this), mKey, (float)mDefNumberValue));
                break;

            case 3: // '\003'
                set(android.provider.Settings.Secure.getInt(SettingsBinder._2D_get0(SettingsBinder.this), mKey, (int)mDefNumberValue));
                break;

            case 4: // '\004'
                set(android.provider.Settings.Secure.getLong(SettingsBinder._2D_get0(SettingsBinder.this), mKey, (long)mDefNumberValue));
                break;
            }
            if(true) goto _L5; else goto _L6
_L6:
        }

        private static final int _2D_miui_2D_maml_2D_data_2D_SettingsBinder$CategorySwitchesValues[];
        final int $SWITCH_TABLE$miui$maml$data$SettingsBinder$Category[];
        public Category mCategory;
        public String mKey;
        final SettingsBinder this$0;

        public Variable(Element element, Variables variables)
        {
            this$0 = SettingsBinder.this;
            super(element, variables);
            if("secure".equals(element.getAttribute("category")))
                settingsbinder = Category.Secure;
            else
                settingsbinder = Category.System;
            mCategory = SettingsBinder.this;
            mKey = element.getAttribute("key");
        }
    }


    static ContentResolver _2D_get0(SettingsBinder settingsbinder)
    {
        return settingsbinder.mContentResolver;
    }

    public SettingsBinder(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mContentResolver = mRoot.getContext().mContext.getContentResolver();
        if(element != null)
        {
            loadVariables(element);
            mConst = "false".equalsIgnoreCase(element.getAttribute("const")) ^ true;
        }
    }

    protected Variable onLoadVariable(Element element)
    {
        return new Variable(element, getContext().mVariables);
    }

    protected volatile VariableBinder.Variable onLoadVariable(Element element)
    {
        return onLoadVariable(element);
    }

    public void refresh()
    {
        super.refresh();
        startQuery();
    }

    public void resume()
    {
        super.resume();
        if(!mConst)
            startQuery();
    }

    public void startQuery()
    {
        for(Iterator iterator = mVariables.iterator(); iterator.hasNext(); ((Variable)(VariableBinder.Variable)iterator.next()).query());
        onUpdateComplete();
    }

    public static final String TAG_NAME = "SettingsBinder";
    private boolean mConst;
    private ContentResolver mContentResolver;
}
