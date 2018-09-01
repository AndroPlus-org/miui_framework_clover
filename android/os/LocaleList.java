// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.icu.util.ULocale;
import java.util.*;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public final class LocaleList
    implements Parcelable
{

    public LocaleList(Locale locale, LocaleList localelist)
    {
        int j;
        Locale alocale[];
label0:
        {
            super();
            if(locale == null)
                throw new NullPointerException("topLocale is null");
            int i;
            int k;
            int k1;
            if(localelist == null)
                i = 0;
            else
                i = localelist.mList.length;
            j = -1;
            k = 0;
label1:
            do
            {
label2:
                {
                    k1 = j;
                    if(k < i)
                    {
                        if(!locale.equals(localelist.mList[k]))
                            break label2;
                        k1 = k;
                    }
                    if(k1 == -1)
                        k = 1;
                    else
                        k = 0;
                    j = i + k;
                    alocale = new Locale[j];
                    alocale[0] = (Locale)locale.clone();
                    if(k1 != -1)
                        break label1;
                    for(k = 0; k < i; k++)
                        alocale[k + 1] = (Locale)localelist.mList[k].clone();

                    break label0;
                }
                k++;
            } while(true);
            for(int l = 0; l < k1; l++)
                alocale[l + 1] = (Locale)localelist.mList[l].clone();

            for(int i1 = k1 + 1; i1 < i; i1++)
                alocale[i1] = (Locale)localelist.mList[i1].clone();

        }
        locale = new StringBuilder();
        for(int j1 = 0; j1 < j; j1++)
        {
            locale.append(alocale[j1].toLanguageTag());
            if(j1 < j - 1)
                locale.append(',');
        }

        mList = alocale;
        mStringRepresentation = locale.toString();
    }

    public transient LocaleList(Locale alocale[])
    {
        if(alocale.length == 0)
        {
            mList = sEmptyList;
            mStringRepresentation = "";
        } else
        {
            Locale alocale1[] = new Locale[alocale.length];
            HashSet hashset = new HashSet();
            StringBuilder stringbuilder = new StringBuilder();
            for(int i = 0; i < alocale.length; i++)
            {
                Locale locale = alocale[i];
                if(locale == null)
                    throw new NullPointerException((new StringBuilder()).append("list[").append(i).append("] is null").toString());
                if(hashset.contains(locale))
                    throw new IllegalArgumentException((new StringBuilder()).append("list[").append(i).append("] is a repetition").toString());
                locale = (Locale)locale.clone();
                alocale1[i] = locale;
                stringbuilder.append(locale.toLanguageTag());
                if(i < alocale.length - 1)
                    stringbuilder.append(',');
                hashset.add(locale);
            }

            mList = alocale1;
            mStringRepresentation = stringbuilder.toString();
        }
    }

    private Locale computeFirstMatch(Collection collection, boolean flag)
    {
        int i = computeFirstMatchIndex(collection, flag);
        if(i == -1)
            collection = null;
        else
            collection = mList[i];
        return collection;
    }

    private int computeFirstMatchIndex(Collection collection, boolean flag)
    {
        if(mList.length == 1)
            return 0;
        if(mList.length == 0)
            return -1;
        int i = 0x7fffffff;
        int j = i;
        if(flag)
        {
            int k = findFirstMatchIndex(EN_LATN);
            if(k == 0)
                return 0;
            j = i;
            if(k < 0x7fffffff)
                j = k;
        }
        collection = collection.iterator();
        do
        {
            if(!collection.hasNext())
                break;
            int l = findFirstMatchIndex(Locale.forLanguageTag((String)collection.next()));
            if(l == 0)
                return 0;
            if(l < j)
                j = l;
        } while(true);
        if(j == 0x7fffffff)
            return 0;
        else
            return j;
    }

    private int findFirstMatchIndex(Locale locale)
    {
        for(int i = 0; i < mList.length; i++)
            if(matchScore(locale, mList[i]) > 0)
                return i;

        return 0x7fffffff;
    }

    public static LocaleList forLanguageTags(String s)
    {
        if(s == null || s.equals(""))
            return getEmptyLocaleList();
        String as[] = s.split(",");
        s = new Locale[as.length];
        for(int i = 0; i < s.length; i++)
            s[i] = Locale.forLanguageTag(as[i]);

        return new LocaleList(s);
    }

    public static LocaleList getAdjustedDefault()
    {
        getDefault();
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        LocaleList localelist = sDefaultAdjustedLocaleList;
        obj;
        JVM INSTR monitorexit ;
        return localelist;
        Exception exception;
        exception;
        throw exception;
    }

    public static LocaleList getDefault()
    {
        Locale locale = Locale.getDefault();
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        LocaleList localelist;
        if(locale.equals(sLastDefaultLocale))
            break MISSING_BLOCK_LABEL_74;
        sLastDefaultLocale = locale;
        if(sDefaultLocaleList == null || !locale.equals(sDefaultLocaleList.get(0)))
            break MISSING_BLOCK_LABEL_52;
        localelist = sDefaultLocaleList;
        obj;
        JVM INSTR monitorexit ;
        return localelist;
        localelist = JVM INSTR new #2   <Class LocaleList>;
        localelist.LocaleList(locale, sLastExplicitlySetLocaleList);
        sDefaultLocaleList = localelist;
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
        localelist = sDefaultLocaleList;
        obj;
        JVM INSTR monitorexit ;
        return localelist;
        Exception exception;
        exception;
        throw exception;
    }

    public static LocaleList getEmptyLocaleList()
    {
        return sEmptyLocaleList;
    }

    private static String getLikelyScript(Locale locale)
    {
        String s = locale.getScript();
        if(!s.isEmpty())
            return s;
        else
            return ULocale.addLikelySubtags(ULocale.forLocale(locale)).getScript();
    }

    private static boolean isPseudoLocale(String s)
    {
        boolean flag;
        if(!"en-XA".equals(s))
            flag = "ar-XB".equals(s);
        else
            flag = true;
        return flag;
    }

    private static boolean isPseudoLocale(Locale locale)
    {
        boolean flag;
        if(!LOCALE_EN_XA.equals(locale))
            flag = LOCALE_AR_XB.equals(locale);
        else
            flag = true;
        return flag;
    }

    public static boolean isPseudoLocalesOnly(String as[])
    {
        if(as == null)
            return true;
        if(as.length > 3)
            return false;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s = as[j];
            if(!s.isEmpty() && isPseudoLocale(s) ^ true)
                return false;
        }

        return true;
    }

    private static int matchScore(Locale locale, Locale locale1)
    {
        boolean flag = true;
        int i = 0;
        if(locale.equals(locale1))
            return 1;
        if(!locale.getLanguage().equals(locale1.getLanguage()))
            return 0;
        if(isPseudoLocale(locale) || isPseudoLocale(locale1))
            return 0;
        String s = getLikelyScript(locale);
        if(s.isEmpty())
        {
            locale = locale.getCountry();
            if(locale.isEmpty() || locale.equals(locale1.getCountry()))
                i = 1;
            return i;
        }
        if(s.equals(getLikelyScript(locale1)))
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        return i;
    }

    public static void setDefault(LocaleList localelist)
    {
        setDefault(localelist, 0);
    }

    public static void setDefault(LocaleList localelist, int i)
    {
        if(localelist == null)
            throw new NullPointerException("locales is null");
        if(localelist.isEmpty())
            throw new IllegalArgumentException("locales is empty");
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        sLastDefaultLocale = localelist.get(i);
        Locale.setDefault(sLastDefaultLocale);
        sLastExplicitlySetLocaleList = localelist;
        sDefaultLocaleList = localelist;
        if(i != 0)
            break MISSING_BLOCK_LABEL_73;
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        localelist = JVM INSTR new #2   <Class LocaleList>;
        localelist.LocaleList(sLastDefaultLocale, sDefaultLocaleList);
        sDefaultAdjustedLocaleList = localelist;
          goto _L1
        localelist;
        throw localelist;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof LocaleList))
            return false;
        obj = ((LocaleList)obj).mList;
        if(mList.length != obj.length)
            return false;
        for(int i = 0; i < mList.length; i++)
            if(!mList[i].equals(obj[i]))
                return false;

        return true;
    }

    public Locale get(int i)
    {
        Locale locale;
        if(i >= 0 && i < mList.length)
            locale = mList[i];
        else
            locale = null;
        return locale;
    }

    public Locale getFirstMatch(String as[])
    {
        return computeFirstMatch(Arrays.asList(as), false);
    }

    public int getFirstMatchIndex(String as[])
    {
        return computeFirstMatchIndex(Arrays.asList(as), false);
    }

    public int getFirstMatchIndexWithEnglishSupported(Collection collection)
    {
        return computeFirstMatchIndex(collection, true);
    }

    public int getFirstMatchIndexWithEnglishSupported(String as[])
    {
        return getFirstMatchIndexWithEnglishSupported(((Collection) (Arrays.asList(as))));
    }

    public Locale getFirstMatchWithEnglishSupported(String as[])
    {
        return computeFirstMatch(Arrays.asList(as), true);
    }

    public int hashCode()
    {
        int i = 1;
        for(int j = 0; j < mList.length; j++)
            i = i * 31 + mList[j].hashCode();

        return i;
    }

    public int indexOf(Locale locale)
    {
        for(int i = 0; i < mList.length; i++)
            if(mList[i].equals(locale))
                return i;

        return -1;
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        if(mList.length == 0)
            flag = true;
        return flag;
    }

    public int size()
    {
        return mList.length;
    }

    public String toLanguageTags()
    {
        return mStringRepresentation;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        for(int i = 0; i < mList.length; i++)
        {
            stringbuilder.append(mList[i]);
            if(i < mList.length - 1)
                stringbuilder.append(',');
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mStringRepresentation);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public LocaleList createFromParcel(Parcel parcel)
        {
            return LocaleList.forLanguageTags(parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LocaleList[] newArray(int i)
        {
            return new LocaleList[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final Locale EN_LATN = Locale.forLanguageTag("en-Latn");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    private static LocaleList sDefaultAdjustedLocaleList = null;
    private static LocaleList sDefaultLocaleList = null;
    private static final Locale sEmptyList[] = new Locale[0];
    private static final LocaleList sEmptyLocaleList = new LocaleList(new Locale[0]);
    private static Locale sLastDefaultLocale = null;
    private static LocaleList sLastExplicitlySetLocaleList = null;
    private static final Object sLock = new Object();
    private final Locale mList[];
    private final String mStringRepresentation;

}
