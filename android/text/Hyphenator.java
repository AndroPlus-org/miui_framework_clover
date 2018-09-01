// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.util.Log;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Locale;

// Referenced classes of package android.text:
//            StaticLayout

public class Hyphenator
{
    private static class HyphenationData
    {

        final String mLanguageTag;
        final int mMinPrefix;
        final int mMinSuffix;

        HyphenationData(String s, int i, int j)
        {
            mLanguageTag = s;
            mMinPrefix = i;
            mMinSuffix = j;
        }
    }


    private Hyphenator(long l, ByteBuffer bytebuffer)
    {
        mNativePtr = l;
        mBuffer = bytebuffer;
    }

    public static Hyphenator get(Locale locale)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (Hyphenator)sMap.get(locale);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_25;
        obj;
        JVM INSTR monitorexit ;
        return ((Hyphenator) (obj1));
        obj1 = locale.getVariant();
        if(((String) (obj1)).isEmpty())
            break MISSING_BLOCK_LABEL_80;
        Locale locale1 = JVM INSTR new #186 <Class Locale>;
        locale1.Locale(locale.getLanguage(), "", ((String) (obj1)));
        obj1 = (Hyphenator)sMap.get(locale1);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_80;
        sMap.put(locale, obj1);
        obj;
        JVM INSTR monitorexit ;
        return ((Hyphenator) (obj1));
        obj1 = JVM INSTR new #186 <Class Locale>;
        ((Locale) (obj1)).Locale(locale.getLanguage());
        obj1 = (Hyphenator)sMap.get(obj1);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_120;
        sMap.put(locale, obj1);
        obj;
        JVM INSTR monitorexit ;
        return ((Hyphenator) (obj1));
        obj1 = locale.getScript();
        if(((String) (obj1)).equals(""))
            break MISSING_BLOCK_LABEL_184;
        java.util.Locale.Builder builder = JVM INSTR new #218 <Class java.util.Locale$Builder>;
        builder.java.util.Locale.Builder();
        obj1 = builder.setLanguage("und").setScript(((String) (obj1))).build();
        obj1 = (Hyphenator)sMap.get(obj1);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_184;
        sMap.put(locale, obj1);
        obj;
        JVM INSTR monitorexit ;
        return ((Hyphenator) (obj1));
        sMap.put(locale, sEmptyHyphenator);
        obj;
        JVM INSTR monitorexit ;
        return sEmptyHyphenator;
        locale;
        throw locale;
    }

    private static File getSystemHyphenatorLocation()
    {
        return new File("/system/usr/hyphen-data");
    }

    public static void init()
    {
        sMap.put(null, null);
        for(int i = 0; i < AVAILABLE_LANGUAGES.length; i++)
        {
            HyphenationData hyphenationdata = AVAILABLE_LANGUAGES[i];
            Hyphenator hyphenator = loadHyphenator(hyphenationdata);
            if(hyphenator != null)
                sMap.put(Locale.forLanguageTag(hyphenationdata.mLanguageTag), hyphenator);
        }

        for(int j = 0; j < LOCALE_FALLBACK_DATA.length; j++)
        {
            String s1 = LOCALE_FALLBACK_DATA[j][0];
            String s = LOCALE_FALLBACK_DATA[j][1];
            sMap.put(Locale.forLanguageTag(s1), (Hyphenator)sMap.get(Locale.forLanguageTag(s)));
        }

    }

    private static Hyphenator loadHyphenator(HyphenationData hyphenationdata)
    {
        Object obj;
        obj = (new StringBuilder()).append("hyph-").append(hyphenationdata.mLanguageTag.toLowerCase(Locale.US)).append(".hyb").toString();
        obj = new File(getSystemHyphenatorLocation(), ((String) (obj)));
        if(!((File) (obj)).canRead())
        {
            Log.e(TAG, (new StringBuilder()).append("hyphenation patterns for ").append(obj).append(" not found or unreadable").toString());
            return null;
        }
        RandomAccessFile randomaccessfile;
        randomaccessfile = JVM INSTR new #298 <Class RandomAccessFile>;
        randomaccessfile.RandomAccessFile(((File) (obj)), "r");
        Object obj1 = randomaccessfile.getChannel();
        obj1 = ((FileChannel) (obj1)).map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, ((FileChannel) (obj1)).size());
        long l = StaticLayout.nLoadHyphenator(((ByteBuffer) (obj1)), 0, hyphenationdata.mMinPrefix, hyphenationdata.mMinSuffix);
        hyphenationdata = JVM INSTR new #2   <Class Hyphenator>;
        hyphenationdata.Hyphenator(l, ((ByteBuffer) (obj1)));
        randomaccessfile.close();
        return hyphenationdata;
        hyphenationdata;
        try
        {
            randomaccessfile.close();
            throw hyphenationdata;
        }
        // Misplaced declaration of an exception variable
        catch(HyphenationData hyphenationdata)
        {
            Log.e(TAG, (new StringBuilder()).append("error loading hyphenation ").append(obj).toString(), hyphenationdata);
        }
        return null;
    }

    public long getNativePtr()
    {
        return mNativePtr;
    }

    private static final HyphenationData AVAILABLE_LANGUAGES[] = {
        new HyphenationData("as", 2, 2), new HyphenationData("bg", 2, 2), new HyphenationData("bn", 2, 2), new HyphenationData("cu", 1, 2), new HyphenationData("cy", 2, 3), new HyphenationData("da", 2, 2), new HyphenationData("de-1901", 2, 2), new HyphenationData("de-1996", 2, 2), new HyphenationData("de-CH-1901", 2, 2), new HyphenationData("en-GB", 2, 3), 
        new HyphenationData("en-US", 2, 3), new HyphenationData("es", 2, 2), new HyphenationData("et", 2, 3), new HyphenationData("eu", 2, 2), new HyphenationData("fr", 2, 3), new HyphenationData("ga", 2, 3), new HyphenationData("gu", 2, 2), new HyphenationData("hi", 2, 2), new HyphenationData("hr", 2, 2), new HyphenationData("hu", 2, 2), 
        new HyphenationData("hy", 2, 2), new HyphenationData("kn", 2, 2), new HyphenationData("ml", 2, 2), new HyphenationData("mn-Cyrl", 2, 2), new HyphenationData("mr", 2, 2), new HyphenationData("nb", 2, 2), new HyphenationData("nn", 2, 2), new HyphenationData("or", 2, 2), new HyphenationData("pa", 2, 2), new HyphenationData("pt", 2, 3), 
        new HyphenationData("sl", 2, 2), new HyphenationData("ta", 2, 2), new HyphenationData("te", 2, 2), new HyphenationData("tk", 2, 2), new HyphenationData("und-Ethi", 1, 1)
    };
    private static final int DEFAULT_MIN_PREFIX = 2;
    private static final int DEFAULT_MIN_SUFFIX = 2;
    private static final int INDIC_MIN_PREFIX = 2;
    private static final int INDIC_MIN_SUFFIX = 2;
    private static final String LOCALE_FALLBACK_DATA[][];
    private static String TAG = "Hyphenator";
    static final Hyphenator sEmptyHyphenator = new Hyphenator(StaticLayout.nLoadHyphenator(null, 0, 2, 2), null);
    private static final Object sLock = new Object();
    static final HashMap sMap = new HashMap();
    private final ByteBuffer mBuffer;
    private final long mNativePtr;

    static 
    {
        String as[] = {
            "en-MH", "en-US"
        };
        String as1[] = {
            "en-UM", "en-US"
        };
        String as2[] = {
            "de", "de-1996"
        };
        String as3[] = {
            "de-LI-1901", "de-CH-1901"
        };
        String as4[] = {
            "am", "und-Ethi"
        };
        String as5[] = {
            "byn", "und-Ethi"
        };
        String as6[] = {
            "ti", "und-Ethi"
        };
        LOCALE_FALLBACK_DATA = (new String[][] {
            new String[] {
                "en-AS", "en-US"
            }, new String[] {
                "en-GU", "en-US"
            }, as, new String[] {
                "en-MP", "en-US"
            }, new String[] {
                "en-PR", "en-US"
            }, as1, new String[] {
                "en-VI", "en-US"
            }, new String[] {
                "en", "en-GB"
            }, as2, as3, new String[] {
                "no", "nb"
            }, new String[] {
                "mn", "mn-Cyrl"
            }, as4, as5, new String[] {
                "gez", "und-Ethi"
            }, as6, new String[] {
                "wal", "und-Ethi"
            }
        });
    }
}
