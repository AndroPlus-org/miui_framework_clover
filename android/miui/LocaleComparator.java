// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.miui;

import java.util.Comparator;
import java.util.Locale;
import miui.os.Build;

public class LocaleComparator
    implements Comparator
{

    public LocaleComparator()
    {
    }

    public int compare(com.android.internal.app.LocalePicker.LocaleInfo localeinfo, com.android.internal.app.LocalePicker.LocaleInfo localeinfo1)
    {
        int i = findTopLocale(localeinfo);
        int j = findTopLocale(localeinfo1);
        if(i == j)
            j = localeinfo.compareTo(localeinfo1);
        else
            j = i - j;
        return j;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((com.android.internal.app.LocalePicker.LocaleInfo)obj, (com.android.internal.app.LocalePicker.LocaleInfo)obj1);
    }

    public int findTopLocale(com.android.internal.app.LocalePicker.LocaleInfo localeinfo)
    {
        for(int i = 0; i < TOP_LOCALES.length; i++)
            if(TOP_LOCALES[i].equals(localeinfo.getLocale().toString()))
                return i;

        return NON_TOP_LOCALE_INDEX;
    }

    private static final int NON_TOP_LOCALE_INDEX;
    private static final String TOP_LOCALES[];

    static 
    {
        if(Build.IS_INTERNATIONAL_BUILD)
            TOP_LOCALES = (new String[] {
                "zh_CN", "en_US", "en_GB", "hi_IN", "in_ID", "ms_MY", "vi_VN", "zh_TW", "th_TH"
            });
        else
            TOP_LOCALES = (new String[] {
                "zh_CN", "zh_TW", "en_US"
            });
        NON_TOP_LOCALE_INDEX = TOP_LOCALES.length;
    }
}
