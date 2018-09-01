// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.text.TextUtils;
import com.android.internal.util.ArrayUtils;
import java.util.*;
import java.util.function.Function;

public class SettingsStringUtil
{
    public static abstract class ColonDelimitedSet extends HashSet
    {

        protected abstract Object itemFromString(String s);

        protected String itemToString(Object obj)
        {
            return String.valueOf(obj);
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            Iterator iterator = iterator();
            if(iterator.hasNext())
            {
                stringbuilder.append(itemToString(iterator.next()));
                for(; iterator.hasNext(); stringbuilder.append(itemToString(iterator.next())))
                    stringbuilder.append(":");

            }
            return stringbuilder.toString();
        }

        public ColonDelimitedSet(String s)
        {
            s = TextUtils.split(TextUtils.emptyIfNull(s), ":");
            int i = 0;
            for(int j = s.length; i < j; i++)
                add(itemFromString(s[i]));

        }
    }

    public static class ColonDelimitedSet.OfStrings extends ColonDelimitedSet
    {

        public static String add(String s, String s1)
        {
            ColonDelimitedSet.OfStrings ofstrings = new ColonDelimitedSet.OfStrings(s);
            if(ofstrings.contains(s1))
            {
                return s;
            } else
            {
                ofstrings.add(s1);
                return ofstrings.toString();
            }
        }

        public static String addAll(String s, Collection collection)
        {
            ColonDelimitedSet.OfStrings ofstrings = new ColonDelimitedSet.OfStrings(s);
            if(ofstrings.addAll(collection))
                s = ofstrings.toString();
            return s;
        }

        public static boolean contains(String s, String s1)
        {
            boolean flag;
            if(ArrayUtils.indexOf(TextUtils.split(s, ":"), s1) != -1)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static String remove(String s, String s1)
        {
            ColonDelimitedSet.OfStrings ofstrings = new ColonDelimitedSet.OfStrings(s);
            if(!ofstrings.contains(s1))
            {
                return s;
            } else
            {
                ofstrings.remove(s1);
                return ofstrings.toString();
            }
        }

        protected volatile Object itemFromString(String s)
        {
            return itemFromString(s);
        }

        protected String itemFromString(String s)
        {
            return s;
        }

        public ColonDelimitedSet.OfStrings(String s)
        {
            super(s);
        }
    }

    public static class ComponentNameSet extends ColonDelimitedSet
    {

        public static String add(String s, ComponentName componentname)
        {
            ComponentNameSet componentnameset = new ComponentNameSet(s);
            if(componentnameset.contains(componentname))
            {
                return s;
            } else
            {
                componentnameset.add(componentname);
                return componentnameset.toString();
            }
        }

        public static boolean contains(String s, ComponentName componentname)
        {
            return ColonDelimitedSet.OfStrings.contains(s, componentname.flattenToString());
        }

        public static String remove(String s, ComponentName componentname)
        {
            ComponentNameSet componentnameset = new ComponentNameSet(s);
            if(!componentnameset.contains(componentname))
            {
                return s;
            } else
            {
                componentnameset.remove(componentname);
                return componentnameset.toString();
            }
        }

        protected ComponentName itemFromString(String s)
        {
            return ComponentName.unflattenFromString(s);
        }

        protected volatile Object itemFromString(String s)
        {
            return itemFromString(s);
        }

        protected String itemToString(ComponentName componentname)
        {
            return componentname.flattenToString();
        }

        protected volatile String itemToString(Object obj)
        {
            return itemToString((ComponentName)obj);
        }

        public ComponentNameSet(String s)
        {
            super(s);
        }
    }

    public static class SettingStringHelper
    {

        public boolean modify(Function function)
        {
            return write((String)function.apply(read()));
        }

        public String read()
        {
            return Settings.Secure.getStringForUser(mContentResolver, mSettingName, mUserId);
        }

        public boolean write(String s)
        {
            return Settings.Secure.putStringForUser(mContentResolver, mSettingName, s, mUserId);
        }

        private final ContentResolver mContentResolver;
        private final String mSettingName;
        private final int mUserId;

        public SettingStringHelper(ContentResolver contentresolver, String s, int i)
        {
            mContentResolver = contentresolver;
            mUserId = i;
            mSettingName = s;
        }
    }


    private SettingsStringUtil()
    {
    }

    public static final String DELIMITER = ":";
}
