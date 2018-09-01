// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.Context;
import android.content.res.Resources;
import java.util.*;
import miui.os.Build;

class LocalePickerInjector
{

    LocalePickerInjector()
    {
    }

    static String getDisplayLanguage(Locale locale, String as[], String as1[], String s)
    {
        locale = locale.toString();
        for(int i = 0; i < as.length; i++)
            if(as[i].equals(locale))
                return as1[i];

        return s;
    }

    static void removeDuplicate(List list)
    {
        if(list == null)
            return;
        for(int i = list.size() - 1; i > 0; i--)
            if(list.get(i) != null && ((String)list.get(i)).equals(list.get(i - 1)))
                list.remove(i);

    }

    static void sortLocaleInfos(LocalePicker.LocaleInfo alocaleinfo[], Context context)
    {
        int i;
        if(Build.IS_INTERNATIONAL_BUILD)
            i = 0x1109000d;
        else
            i = 0x1109000e;
        Arrays.sort(alocaleinfo, new Comparator(context.getResources().getStringArray(i)) {

            private int findTopLocale(String as[], LocalePicker.LocaleInfo localeinfo)
            {
                for(int j = 0; j < as.length; j++)
                    if(as[j].equals(localeinfo.getLocale().toString()))
                        return j;

                return as.length;
            }

            public int compare(LocalePicker.LocaleInfo localeinfo, LocalePicker.LocaleInfo localeinfo1)
            {
                return findTopLocale(topLocales, localeinfo) - findTopLocale(topLocales, localeinfo1);
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((LocalePicker.LocaleInfo)obj, (LocalePicker.LocaleInfo)obj1);
            }

            final String val$topLocales[];

            
            {
                topLocales = as;
                super();
            }
        }
);
    }
}
