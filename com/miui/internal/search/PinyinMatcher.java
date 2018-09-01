// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.search;

import java.util.ArrayList;
import java.util.Comparator;

// Referenced classes of package com.miui.internal.search:
//            SearchUtils

public class PinyinMatcher
{

    public PinyinMatcher()
    {
    }

    public static int pinyinLevenshtein(String s, String s1)
    {
        return SearchUtils.Levenshtein(splitPinyin(s, false), splitPinyin(s1, false), new Comparator() {

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((String)obj, (String)obj1);
            }

            public int compare(String s2, String s3)
            {
                return 0;
            }

        }
);
    }

    public static String[] splitPinyin(String s, boolean flag)
    {
        ArrayList arraylist;
        if(flag)
            throw new IllegalArgumentException("Correction not supported yet");
        arraylist = new ArrayList();
_L8:
        String as[];
        int i;
        int j;
        if(s.length() <= 0)
            break MISSING_BLOCK_LABEL_171;
        as = SHENGMU;
        i = as.length;
        j = 0;
_L5:
        String s1 = s;
        if(j >= i) goto _L2; else goto _L1
_L1:
        s1 = as[j];
        if(!s.startsWith(s1)) goto _L4; else goto _L3
_L3:
        arraylist.add(s1);
        s1 = s.substring(s1.length());
_L2:
        as = YUNMU;
        i = as.length;
        j = 0;
_L6:
        s = s1;
        if(j >= i)
            continue; /* Loop/switch isn't completed */
        s = as[j];
        if(s1.startsWith(s))
        {
            arraylist.set(arraylist.size() - 1, ((String)arraylist.get(arraylist.size() - 1)).concat(s));
            s = s1.substring(s.length());
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_165;
_L4:
        j++;
          goto _L5
        j++;
          goto _L6
        return (String[])arraylist.toArray(new String[arraylist.size()]);
        if(true) goto _L8; else goto _L7
_L7:
    }

    private static String SHENGMU[] = {
        "b", "p", "m", "f", "d", "t", "n", "l", "g", "k", 
        "h", "j", "q", "x", "z", "c", "s", "zh", "ch", "sh", 
        "r", "y", "w"
    };
    private static String YUNMU[] = {
        "a", "o", "e", "i", "u", "v", "ai", "ei", "ui", "ao", 
        "ou", "iu", "ia", "ie", "ve", "er", "an", "en", "in", "un", 
        "vn", "ang", "eng", "ing", "ong", "iao", "ian", "iang", "iong", "ua", 
        "uo", "uai", "uan", "van", "uang"
    };

}
