// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.icu.text.ListFormatter;
import android.icu.util.ULocale;
import android.os.LocaleList;
import android.text.TextUtils;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import libcore.icu.ICU;

public class LocaleHelper
{
    public static final class LocaleInfoComparator
        implements Comparator
    {

        private String removePrefixForCompare(Locale locale, String s)
        {
            if("ar".equals(locale.getLanguage()) && s.startsWith("\u0627\u0644"))
                return s.substring("\u0627\u0644".length());
            else
                return s;
        }

        public int compare(LocaleStore.LocaleInfo localeinfo, LocaleStore.LocaleInfo localeinfo1)
        {
            if(localeinfo.isSuggested() == localeinfo1.isSuggested())
                return mCollator.compare(removePrefixForCompare(localeinfo.getLocale(), localeinfo.getLabel(mCountryMode)), removePrefixForCompare(localeinfo1.getLocale(), localeinfo1.getLabel(mCountryMode)));
            byte byte0;
            if(localeinfo.isSuggested())
                byte0 = -1;
            else
                byte0 = 1;
            return byte0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((LocaleStore.LocaleInfo)obj, (LocaleStore.LocaleInfo)obj1);
        }

        private static final String PREFIX_ARABIC = "\u0627\u0644";
        private final Collator mCollator;
        private final boolean mCountryMode;

        public LocaleInfoComparator(Locale locale, boolean flag)
        {
            mCollator = Collator.getInstance(locale);
            mCountryMode = flag;
        }
    }


    public LocaleHelper()
    {
    }

    public static Locale addLikelySubtags(Locale locale)
    {
        return ICU.addLikelySubtags(locale);
    }

    public static String getDisplayCountry(Locale locale)
    {
        return ULocale.getDisplayCountry(locale.toLanguageTag(), ULocale.getDefault());
    }

    public static String getDisplayCountry(Locale locale, Locale locale1)
    {
        return ULocale.getDisplayCountry(locale.toLanguageTag(), ULocale.forLocale(locale1));
    }

    public static String getDisplayLocaleList(LocaleList localelist, Locale locale, int i)
    {
        if(locale == null)
            locale = Locale.getDefault();
        boolean flag;
        int j;
        int k;
        String as[];
        if(localelist.size() > i)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            j = i;
            k = i + 1;
        } else
        {
            j = localelist.size();
            k = j;
        }
        as = new String[k];
        for(k = 0; k < j; k++)
            as[k] = getDisplayName(localelist.get(k), locale, false);

        if(flag)
            as[i] = TextUtils.ELLIPSIS_STRING;
        return ListFormatter.getInstance(locale).format(as);
    }

    public static String getDisplayName(Locale locale, Locale locale1, boolean flag)
    {
        Object obj = ULocale.forLocale(locale1);
        if(shouldUseDialectName(locale))
            locale = ULocale.getDisplayNameWithDialect(locale.toLanguageTag(), ((ULocale) (obj)));
        else
            locale = ULocale.getDisplayName(locale.toLanguageTag(), ((ULocale) (obj)));
        obj = locale;
        if(flag)
            obj = toSentenceCase(locale, locale1);
        return ((String) (obj));
    }

    public static String getDisplayName(Locale locale, boolean flag)
    {
        return getDisplayName(locale, Locale.getDefault(), flag);
    }

    public static String normalizeForSearch(String s, Locale locale)
    {
        return s.toUpperCase();
    }

    private static boolean shouldUseDialectName(Locale locale)
    {
        locale = locale.getLanguage();
        boolean flag;
        if(!"fa".equals(locale) && !"ro".equals(locale))
            flag = "zh".equals(locale);
        else
            flag = true;
        return flag;
    }

    public static String toSentenceCase(String s, Locale locale)
    {
        if(s.isEmpty())
        {
            return s;
        } else
        {
            int i = s.offsetByCodePoints(0, 1);
            return (new StringBuilder()).append(s.substring(0, i).toUpperCase(locale)).append(s.substring(i)).toString();
        }
    }
}
