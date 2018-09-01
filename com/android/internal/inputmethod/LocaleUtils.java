// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.inputmethod;

import android.icu.util.ULocale;
import android.os.LocaleList;
import android.text.TextUtils;
import java.util.*;

public final class LocaleUtils
{
    public static interface LocaleExtractor
    {

        public abstract Locale get(Object obj);
    }

    private static final class ScoreEntry
        implements Comparable
    {

        private static int compare(byte abyte0[], byte abyte1[])
        {
            for(int i = 0; i < abyte0.length; i++)
            {
                if(abyte0[i] > abyte1[i])
                    return 1;
                if(abyte0[i] < abyte1[i])
                    return -1;
            }

            return 0;
        }

        private void set(byte abyte0[], int i)
        {
            for(int j = 0; j < mScore.length; j++)
                mScore[j] = abyte0[j];

            mIndex = i;
        }

        public int compareTo(ScoreEntry scoreentry)
        {
            return compare(mScore, scoreentry.mScore) * -1;
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((ScoreEntry)obj);
        }

        public void updateIfBetter(byte abyte0[], int i)
        {
            if(compare(mScore, abyte0) == -1)
                set(abyte0, i);
        }

        public int mIndex;
        public final byte mScore[];

        ScoreEntry(byte abyte0[], int i)
        {
            mIndex = -1;
            mScore = new byte[abyte0.length];
            set(abyte0, i);
        }
    }


    public LocaleUtils()
    {
    }

    private static byte calculateMatchingSubScore(ULocale ulocale, ULocale ulocale1)
    {
        if(ulocale.equals(ulocale1))
            return 3;
        String s = ulocale.getScript();
        if(s.isEmpty() || s.equals(ulocale1.getScript()) ^ true)
            return 1;
        ulocale = ulocale.getCountry();
        return ((byte)(!ulocale.isEmpty() && !(ulocale.equals(ulocale1.getCountry()) ^ true) ? 3 : 2));
    }

    public static void filterByLanguage(List list, LocaleExtractor localeextractor, LocaleList localelist, ArrayList arraylist)
    {
        if(localelist.isEmpty())
            return;
        int i = localelist.size();
        HashMap hashmap = new HashMap();
        byte abyte0[] = new byte[i];
        ULocale aulocale[] = new ULocale[i];
        int j = list.size();
        int k = 0;
        while(k < j) 
        {
            Object obj = localeextractor.get(list.get(k));
            if(obj != null)
            {
                boolean flag = true;
                int l = 0;
                while(l < i) 
                {
                    Locale locale = localelist.get(l);
                    boolean flag1;
                    if(!TextUtils.equals(((Locale) (obj)).getLanguage(), locale.getLanguage()))
                    {
                        abyte0[l] = (byte)0;
                        flag1 = flag;
                    } else
                    {
                        if(aulocale[l] == null)
                            aulocale[l] = ULocale.addLikelySubtags(ULocale.forLocale(locale));
                        abyte0[l] = calculateMatchingSubScore(aulocale[l], ULocale.addLikelySubtags(ULocale.forLocale(((Locale) (obj)))));
                        flag1 = flag;
                        if(flag)
                        {
                            flag1 = flag;
                            if(abyte0[l] != 0)
                                flag1 = false;
                        }
                    }
                    l++;
                    flag = flag1;
                }
                if(!flag)
                {
                    obj = ((Locale) (obj)).getLanguage();
                    ScoreEntry scoreentry = (ScoreEntry)hashmap.get(obj);
                    if(scoreentry == null)
                        hashmap.put(obj, new ScoreEntry(abyte0, k));
                    else
                        scoreentry.updateIfBetter(abyte0, k);
                }
            }
            k++;
        }
        localeextractor = (ScoreEntry[])hashmap.values().toArray(new ScoreEntry[hashmap.size()]);
        Arrays.sort(localeextractor);
        k = 0;
        for(int i1 = localeextractor.length; k < i1; k++)
            arraylist.add(list.get(((ScoreEntry) (localeextractor[k])).mIndex));

    }
}
