// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.*;

// Referenced classes of package com.android.internal.app:
//            LocalePicker, LocaleHelper

public class LocaleStore
{
    public static class LocaleInfo
    {

        static boolean _2D_get0(LocaleInfo localeinfo)
        {
            return localeinfo.mIsPseudo;
        }

        static int _2D_get1(LocaleInfo localeinfo)
        {
            return localeinfo.mSuggestionFlags;
        }

        static boolean _2D_set0(LocaleInfo localeinfo, boolean flag)
        {
            localeinfo.mIsPseudo = flag;
            return flag;
        }

        static int _2D_set1(LocaleInfo localeinfo, int i)
        {
            localeinfo.mSuggestionFlags = i;
            return i;
        }

        static boolean _2D_wrap0(LocaleInfo localeinfo, int i)
        {
            return localeinfo.isSuggestionOfType(i);
        }

        static String _2D_wrap1(LocaleInfo localeinfo)
        {
            return localeinfo.getLangScriptKey();
        }

        private String getLangScriptKey()
        {
            if(mLangScriptKey == null)
            {
                Object obj = getParent(LocaleHelper.addLikelySubtags(mLocale));
                if(obj == null)
                    obj = mLocale.toLanguageTag();
                else
                    obj = ((Locale) (obj)).toLanguageTag();
                mLangScriptKey = ((String) (obj));
            }
            return mLangScriptKey;
        }

        private static Locale getParent(Locale locale)
        {
            if(locale.getCountry().isEmpty())
                return null;
            else
                return (new java.util.Locale.Builder()).setLocale(locale).setRegion("").build();
        }

        private boolean isSuggestionOfType(int i)
        {
            boolean flag = false;
            if(!mIsTranslated)
                return false;
            if((mSuggestionFlags & i) == i)
                flag = true;
            return flag;
        }

        public boolean getChecked()
        {
            return mIsChecked;
        }

        String getContentDescription(boolean flag)
        {
            if(flag)
                return getFullCountryNameInUiLanguage();
            else
                return getFullNameInUiLanguage();
        }

        String getFullCountryNameInUiLanguage()
        {
            return LocaleHelper.getDisplayCountry(mLocale);
        }

        String getFullCountryNameNative()
        {
            if(mFullCountryNameNative == null)
                mFullCountryNameNative = LocaleHelper.getDisplayCountry(mLocale, mLocale);
            return mFullCountryNameNative;
        }

        public String getFullNameInUiLanguage()
        {
            return LocaleHelper.getDisplayName(mLocale, true);
        }

        public String getFullNameNative()
        {
            if(mFullNameNative == null)
                mFullNameNative = LocaleHelper.getDisplayName(mLocale, mLocale, true);
            return mFullNameNative;
        }

        public String getId()
        {
            return mId;
        }

        String getLabel(boolean flag)
        {
            if(flag)
                return getFullCountryNameNative();
            else
                return getFullNameNative();
        }

        public Locale getLocale()
        {
            return mLocale;
        }

        public Locale getParent()
        {
            return mParent;
        }

        boolean isSuggested()
        {
            boolean flag = false;
            if(!mIsTranslated)
                return false;
            if(mSuggestionFlags != 0)
                flag = true;
            return flag;
        }

        public boolean isTranslated()
        {
            return mIsTranslated;
        }

        public void setChecked(boolean flag)
        {
            mIsChecked = flag;
        }

        public void setTranslated(boolean flag)
        {
            mIsTranslated = flag;
        }

        public String toString()
        {
            return mId;
        }

        private static final int SUGGESTION_TYPE_CFG = 2;
        private static final int SUGGESTION_TYPE_NONE = 0;
        private static final int SUGGESTION_TYPE_SIM = 1;
        private String mFullCountryNameNative;
        private String mFullNameNative;
        private final String mId;
        private boolean mIsChecked;
        private boolean mIsPseudo;
        private boolean mIsTranslated;
        private String mLangScriptKey;
        private final Locale mLocale;
        private final Locale mParent;
        private int mSuggestionFlags;

        private LocaleInfo(String s)
        {
            this(Locale.forLanguageTag(s));
        }

        LocaleInfo(String s, LocaleInfo localeinfo)
        {
            this(s);
        }

        private LocaleInfo(Locale locale)
        {
            mLocale = locale;
            mId = locale.toLanguageTag();
            mParent = getParent(locale);
            mIsChecked = false;
            mSuggestionFlags = 0;
            mIsTranslated = false;
            mIsPseudo = false;
        }

        LocaleInfo(Locale locale, LocaleInfo localeinfo)
        {
            this(locale);
        }
    }


    public LocaleStore()
    {
    }

    private static void addSuggestedLocalesForRegion(Locale locale)
    {
        if(locale == null)
            return;
        locale = locale.getCountry();
        if(locale.isEmpty())
            return;
        Iterator iterator = sLocaleCache.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            LocaleInfo localeinfo = (LocaleInfo)iterator.next();
            if(locale.equals(localeinfo.getLocale().getCountry()))
                LocaleInfo._2D_set1(localeinfo, LocaleInfo._2D_get1(localeinfo) | 1);
        } while(true);
    }

    public static void fillCache(Context context)
    {
        String as[];
        HashSet hashset;
        int i;
        int k;
        if(sFullyInitialized)
            return;
        Set set = getSimCountries(context);
        String as1[] = LocalePicker.getSupportedLocales(context);
        i = 0;
        for(int j = as1.length; i < j; i++)
        {
            Object obj = as1[i];
            if(((String) (obj)).isEmpty())
                throw new IllformedLocaleException("Bad locale entry in locale_config.xml");
            obj = new LocaleInfo(((String) (obj)), null);
            if(set.contains(((LocaleInfo) (obj)).getLocale().getCountry()))
                LocaleInfo._2D_set1(((LocaleInfo) (obj)), LocaleInfo._2D_get1(((LocaleInfo) (obj))) | 1);
            sLocaleCache.put(((LocaleInfo) (obj)).getId(), obj);
            obj = ((LocaleInfo) (obj)).getParent();
            if(obj == null)
                continue;
            String s = ((Locale) (obj)).toLanguageTag();
            if(!sLocaleCache.containsKey(s))
                sLocaleCache.put(s, new LocaleInfo(((Locale) (obj)), null));
        }

        boolean flag;
        int l;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0)
            flag = true;
        else
            flag = false;
        as1 = LocalePicker.getPseudoLocales();
        k = 0;
        l = as1.length;
        while(k < l) 
        {
            context = getLocaleInfo(Locale.forLanguageTag(as1[k]));
            if(flag)
            {
                context.setTranslated(true);
                LocaleInfo._2D_set0(context, true);
                LocaleInfo._2D_set1(context, LocaleInfo._2D_get1(context) | 1);
            } else
            {
                sLocaleCache.remove(context.getId());
            }
            k++;
        }
        hashset = new HashSet();
        as = LocalePicker.getSystemAssetLocales();
        flag = false;
        k = as.length;
_L9:
        if(flag >= k) goto _L2; else goto _L1
_L1:
        LocaleInfo localeinfo1;
        String s1;
        localeinfo1 = new LocaleInfo(as[flag], null);
        s1 = localeinfo1.getLocale().getCountry();
        if(s1.isEmpty()) goto _L4; else goto _L3
_L3:
        context = null;
        if(!sLocaleCache.containsKey(localeinfo1.getId())) goto _L6; else goto _L5
_L5:
        context = (LocaleInfo)sLocaleCache.get(localeinfo1.getId());
_L7:
        if(context != null)
            LocaleInfo._2D_set1(context, LocaleInfo._2D_get1(context) | 2);
_L4:
        hashset.add(LocaleInfo._2D_wrap1(localeinfo1));
        flag++;
        continue; /* Loop/switch isn't completed */
_L6:
        s1 = (new StringBuilder()).append(LocaleInfo._2D_wrap1(localeinfo1)).append("-").append(s1).toString();
        if(sLocaleCache.containsKey(s1))
            context = (LocaleInfo)sLocaleCache.get(s1);
        if(true) goto _L7; else goto _L2
_L2:
        LocaleInfo localeinfo;
        for(context = sLocaleCache.values().iterator(); context.hasNext(); localeinfo.setTranslated(hashset.contains(LocaleInfo._2D_wrap1(localeinfo))))
            localeinfo = (LocaleInfo)context.next();

        addSuggestedLocalesForRegion(Locale.getDefault());
        sFullyInitialized = true;
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static int getLevel(Set set, LocaleInfo localeinfo, boolean flag)
    {
        if(set.contains(localeinfo.getId()))
            return 0;
        if(LocaleInfo._2D_get0(localeinfo))
            return 2;
        if(flag && localeinfo.isTranslated() ^ true)
            return 0;
        return localeinfo.getParent() == null ? 0 : 2;
    }

    public static Set getLevelLocales(Context context, Set set, LocaleInfo localeinfo, boolean flag)
    {
        fillCache(context);
        HashSet hashset;
        Iterator iterator;
        if(localeinfo == null)
            context = null;
        else
            context = localeinfo.getId();
        hashset = new HashSet();
        iterator = sLocaleCache.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            LocaleInfo localeinfo1 = (LocaleInfo)iterator.next();
            if(getLevel(set, localeinfo1, flag) == 2)
                if(localeinfo != null)
                {
                    if(context.equals(localeinfo1.getParent().toLanguageTag()))
                        hashset.add(localeinfo1);
                } else
                if(LocaleInfo._2D_wrap0(localeinfo1, 1))
                    hashset.add(localeinfo1);
                else
                    hashset.add(getLocaleInfo(localeinfo1.getParent()));
        } while(true);
        return hashset;
    }

    public static LocaleInfo getLocaleInfo(Locale locale)
    {
        String s = locale.toLanguageTag();
        if(!sLocaleCache.containsKey(s))
        {
            locale = new LocaleInfo(locale, null);
            sLocaleCache.put(s, locale);
        } else
        {
            locale = (LocaleInfo)sLocaleCache.get(s);
        }
        return locale;
    }

    private static Set getSimCountries(Context context)
    {
        HashSet hashset = new HashSet();
        context = TelephonyManager.from(context);
        if(context != null)
        {
            String s = context.getSimCountryIso().toUpperCase(Locale.US);
            if(!s.isEmpty())
                hashset.add(s);
            context = context.getNetworkCountryIso().toUpperCase(Locale.US);
            if(!context.isEmpty())
                hashset.add(context);
        }
        return hashset;
    }

    public static void updateSimCountries(Context context)
    {
        Set set = getSimCountries(context);
        context = sLocaleCache.values().iterator();
        do
        {
            if(!context.hasNext())
                break;
            LocaleInfo localeinfo = (LocaleInfo)context.next();
            if(set.contains(localeinfo.getLocale().getCountry()))
                LocaleInfo._2D_set1(localeinfo, LocaleInfo._2D_get1(localeinfo) | 1);
        } while(true);
    }

    private static boolean sFullyInitialized = false;
    private static final HashMap sLocaleCache = new HashMap();

}
