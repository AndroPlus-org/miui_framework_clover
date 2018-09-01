// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.lang.UCharacter;
import android.icu.text.CaseMap;
import android.icu.text.Edits;
import android.icu.util.ULocale;
import android.os.*;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AccessibilityClickableSpan;
import android.text.style.AccessibilityURLSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.CharacterStyle;
import android.text.style.EasyEditSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LocaleSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.SpellCheckSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuggestionRangeSpan;
import android.text.style.SuggestionSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TtsSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.util.Printer;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

// Referenced classes of package android.text:
//            TextDirectionHeuristics, MeasuredText, SpannableStringBuilder, Spanned, 
//            SpannedString, Spannable, TextPaint, SpannableString, 
//            GetChars, BidiFormatter, ParcelableSpan, TextDirectionHeuristic, 
//            AndroidCharacter, Annotation

public class TextUtils
{
    public static interface EllipsizeCallback
    {

        public abstract void ellipsized(int i, int j);
    }

    private static class Reverser
        implements CharSequence, GetChars
    {

        public char charAt(int i)
        {
            return (char)UCharacter.getMirror(mSource.charAt(mEnd - 1 - i));
        }

        public void getChars(int i, int j, char ac[], int k)
        {
            TextUtils.getChars(mSource, mStart + i, mStart + j, ac, k);
            AndroidCharacter.mirror(ac, 0, j - i);
            int l = j - i;
            j = (j - i) / 2;
            for(i = 0; i < j; i++)
            {
                int i1 = ac[k + i];
                ac[k + i] = ac[(k + l) - i - 1];
                ac[(k + l) - i - 1] = (char)i1;
            }

        }

        public int length()
        {
            return mEnd - mStart;
        }

        public CharSequence subSequence(int i, int j)
        {
            char ac[] = new char[j - i];
            getChars(i, j, ac, 0);
            return new String(ac);
        }

        public String toString()
        {
            return subSequence(0, length()).toString();
        }

        private int mEnd;
        private CharSequence mSource;
        private int mStart;

        public Reverser(CharSequence charsequence, int i, int j)
        {
            mSource = charsequence;
            mStart = i;
            mEnd = j;
        }
    }

    public static class SimpleStringSplitter
        implements StringSplitter, Iterator
    {

        public boolean hasNext()
        {
            boolean flag;
            if(mPosition < mLength)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Iterator iterator()
        {
            return this;
        }

        public volatile Object next()
        {
            return next();
        }

        public String next()
        {
            int i = mString.indexOf(mDelimiter, mPosition);
            int j = i;
            if(i == -1)
                j = mLength;
            String s = mString.substring(mPosition, j);
            mPosition = j + 1;
            return s;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public void setString(String s)
        {
            mString = s;
            mPosition = 0;
            mLength = mString.length();
        }

        private char mDelimiter;
        private int mLength;
        private int mPosition;
        private String mString;

        public SimpleStringSplitter(char c)
        {
            mDelimiter = c;
        }
    }

    public static interface StringSplitter
        extends Iterable
    {

        public abstract void setString(String s);
    }

    public static final class TruncateAt extends Enum
    {

        public static TruncateAt valueOf(String s)
        {
            return (TruncateAt)Enum.valueOf(android/text/TextUtils$TruncateAt, s);
        }

        public static TruncateAt[] values()
        {
            return $VALUES;
        }

        private static final TruncateAt $VALUES[];
        public static final TruncateAt END;
        public static final TruncateAt END_SMALL;
        public static final TruncateAt MARQUEE;
        public static final TruncateAt MIDDLE;
        public static final TruncateAt START;

        static 
        {
            START = new TruncateAt("START", 0);
            MIDDLE = new TruncateAt("MIDDLE", 1);
            END = new TruncateAt("END", 2);
            MARQUEE = new TruncateAt("MARQUEE", 3);
            END_SMALL = new TruncateAt("END_SMALL", 4);
            $VALUES = (new TruncateAt[] {
                START, MIDDLE, END, MARQUEE, END_SMALL
            });
        }

        private TruncateAt(String s, int i)
        {
            super(s, i);
        }
    }


    static void _2D_wrap0(Parcel parcel, Spannable spannable, Object obj)
    {
        readSpan(parcel, spannable, obj);
    }

    private TextUtils()
    {
    }

    public static CharSequence commaEllipsize(CharSequence charsequence, TextPaint textpaint, float f, String s, String s1)
    {
        return commaEllipsize(charsequence, textpaint, f, s, s1, TextDirectionHeuristics.FIRSTSTRONG_LTR);
    }

    public static CharSequence commaEllipsize(CharSequence charsequence, TextPaint textpaint, float f, String s, String s1, TextDirectionHeuristic textdirectionheuristic)
    {
        MeasuredText measuredtext = MeasuredText.obtain();
        int i;
        float f1;
        i = charsequence.length();
        f1 = setPara(measuredtext, textpaint, charsequence, 0, i, textdirectionheuristic);
        if(f1 <= f)
        {
            MeasuredText.recycle(measuredtext);
            return charsequence;
        }
        char ac[] = measuredtext.mChars;
        int j;
        int l;
        String s2;
        int k1;
        int l1;
        j = 0;
        for(int k = 0; k < i;)
        {
            int i1 = j;
            if(ac[k] == ',')
                i1 = j + 1;
            k++;
            j = i1;
        }

        j++;
        l = 0;
        s2 = "";
        k1 = 0;
        l1 = 0;
        float af[];
        MeasuredText measuredtext1;
        af = measuredtext.mWidths;
        measuredtext1 = MeasuredText.obtain();
        int j1 = 0;
_L5:
        int i2;
        int j2;
        int k2;
        String s3;
        if(j1 >= i)
            break MISSING_BLOCK_LABEL_352;
        i2 = (int)((float)k1 + af[j1]);
        j2 = l1;
        k2 = l;
        s3 = s2;
        k1 = j;
        if(ac[j1] != ',') goto _L2; else goto _L1
_L1:
        l1++;
        if(--j != 1)
            break MISSING_BLOCK_LABEL_307;
        Object obj;
        obj = JVM INSTR new #179 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append(" ").append(s).toString();
_L3:
        measuredtext1.setPara(((CharSequence) (obj)), 0, ((String) (obj)).length(), textdirectionheuristic, null);
        f1 = measuredtext1.addStyleRun(textpaint, measuredtext1.mLen, null);
        j2 = l1;
        k2 = l;
        s3 = s2;
        k1 = j;
        if((float)i2 + f1 <= f)
        {
            k2 = j1 + 1;
            k1 = j;
            s3 = ((String) (obj));
            j2 = l1;
        }
_L2:
        j1++;
        l1 = j2;
        l = k2;
        s2 = s3;
        j = k1;
        k1 = i2;
        continue; /* Loop/switch isn't completed */
        obj = JVM INSTR new #179 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append(" ").append(String.format(s1, new Object[] {
            Integer.valueOf(j)
        })).toString();
          goto _L3
        MeasuredText.recycle(measuredtext1);
        textpaint = JVM INSTR new #213 <Class SpannableStringBuilder>;
        textpaint.SpannableStringBuilder(s2);
        textpaint.insert(0, charsequence, 0, l);
        MeasuredText.recycle(measuredtext);
        return textpaint;
        charsequence;
        MeasuredText.recycle(measuredtext);
        throw charsequence;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static transient CharSequence concat(CharSequence acharsequence[])
    {
        boolean flag;
label0:
        {
            flag = false;
            boolean flag1 = false;
            if(acharsequence.length == 0)
                return "";
            if(acharsequence.length == 1)
                return acharsequence[0];
            boolean flag2 = false;
            int i = acharsequence.length;
            int j = 0;
            SpannableStringBuilder spannablestringbuilder;
label1:
            do
            {
label2:
                {
                    int l = ((flag2) ? 1 : 0);
                    if(j < i)
                    {
                        if(!(acharsequence[j] instanceof Spanned))
                            break label2;
                        l = 1;
                    }
                    if(!l)
                        break label0;
                    spannablestringbuilder = new SpannableStringBuilder();
                    l = acharsequence.length;
                    for(j = ((flag1) ? 1 : 0); j < l; j++)
                    {
                        CharSequence charsequence = acharsequence[j];
                        Object obj = charsequence;
                        if(charsequence == null)
                            obj = "null";
                        spannablestringbuilder.append(((CharSequence) (obj)));
                    }

                    break label1;
                }
                j++;
            } while(true);
            return new SpannedString(spannablestringbuilder);
        }
        StringBuilder stringbuilder = new StringBuilder();
        int i1 = acharsequence.length;
        for(int k = ((flag) ? 1 : 0); k < i1; k++)
            stringbuilder.append(acharsequence[k]);

        return stringbuilder.toString();
    }

    public static void copySpansFrom(Spanned spanned, int i, int j, Class class1, Spannable spannable, int k)
    {
        Object obj = class1;
        if(class1 == null)
            obj = java/lang/Object;
        class1 = ((Class) (spanned.getSpans(i, j, ((Class) (obj)))));
        for(int l = 0; l < class1.length; l++)
        {
            int i1 = spanned.getSpanStart(class1[l]);
            int j1 = spanned.getSpanEnd(class1[l]);
            int k1 = spanned.getSpanFlags(class1[l]);
            int l1 = i1;
            if(i1 < i)
                l1 = i;
            i1 = j1;
            if(j1 > j)
                i1 = j;
            spannable.setSpan(class1[l], (l1 - i) + k, (i1 - i) + k, k1);
        }

    }

    static boolean couldAffectRtl(char c)
    {
        boolean flag = true;
        if('\u0590' > c || c > '\u08FF') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(c == '\u200E')
            continue; /* Loop/switch isn't completed */
        flag1 = flag;
        if(c == '\u200F')
            continue; /* Loop/switch isn't completed */
        if('\u202A' <= c)
        {
            flag1 = flag;
            if(c <= '\u202E')
                continue; /* Loop/switch isn't completed */
        }
        if('\u2066' <= c)
        {
            flag1 = flag;
            if(c <= '\u2069')
                continue; /* Loop/switch isn't completed */
        }
        if('\uD800' <= c)
        {
            flag1 = flag;
            if(c <= '\uDFFF')
                continue; /* Loop/switch isn't completed */
        }
        if('\uFB1D' <= c)
        {
            flag1 = flag;
            if(c <= '\uFDFF')
                continue; /* Loop/switch isn't completed */
        }
        if('\uFE70' <= c)
        {
            flag1 = flag;
            if(c <= '\uFEFE')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean delimitedStringContains(String s, char c, String s1)
    {
        if(isEmpty(s) || isEmpty(s1))
            return false;
        int i = -1;
        int j = s.length();
label0:
        do
        {
            int k;
            do
            {
                k = s.indexOf(s1, i + 1);
                if(k == -1)
                    break label0;
                if(k <= 0)
                    break;
                i = k;
            } while(s.charAt(k - 1) != c);
            int l = k + s1.length();
            if(l == j)
                return true;
            i = k;
            if(s.charAt(l) == c)
                return true;
        } while(true);
        return false;
    }

    static boolean doesNotNeedBidi(char ac[], int i, int j)
    {
        for(int k = i; k < i + j; k++)
            if(couldAffectRtl(ac[k]))
                return false;

        return true;
    }

    public static void dumpSpans(CharSequence charsequence, Printer printer, String s)
    {
        if(charsequence instanceof Spanned)
        {
            Spanned spanned = (Spanned)charsequence;
            Object aobj[] = spanned.getSpans(0, charsequence.length(), java/lang/Object);
            for(int i = 0; i < aobj.length; i++)
            {
                Object obj = aobj[i];
                printer.println((new StringBuilder()).append(s).append(charsequence.subSequence(spanned.getSpanStart(obj), spanned.getSpanEnd(obj))).append(": ").append(Integer.toHexString(System.identityHashCode(obj))).append(" ").append(obj.getClass().getCanonicalName()).append(" (").append(spanned.getSpanStart(obj)).append("-").append(spanned.getSpanEnd(obj)).append(") fl=#").append(spanned.getSpanFlags(obj)).toString());
            }

        } else
        {
            printer.println((new StringBuilder()).append(s).append(charsequence).append(": (no spans)").toString());
        }
    }

    public static CharSequence ellipsize(CharSequence charsequence, TextPaint textpaint, float f, TruncateAt truncateat)
    {
        return ellipsize(charsequence, textpaint, f, truncateat, false, null);
    }

    public static CharSequence ellipsize(CharSequence charsequence, TextPaint textpaint, float f, TruncateAt truncateat, boolean flag, EllipsizeCallback ellipsizecallback)
    {
        TextDirectionHeuristic textdirectionheuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        String s;
        if(truncateat == TruncateAt.END_SMALL)
            s = ELLIPSIS_TWO_DOTS_STRING;
        else
            s = ELLIPSIS_STRING;
        return ellipsize(charsequence, textpaint, f, truncateat, flag, ellipsizecallback, textdirectionheuristic, s);
    }

    public static CharSequence ellipsize(CharSequence charsequence, TextPaint textpaint, float f, TruncateAt truncateat, boolean flag, EllipsizeCallback ellipsizecallback, TextDirectionHeuristic textdirectionheuristic, String s)
    {
        int i;
        MeasuredText measuredtext;
        i = charsequence.length();
        measuredtext = MeasuredText.obtain();
        if(setPara(measuredtext, textpaint, charsequence, 0, charsequence.length(), textdirectionheuristic) > f)
            break MISSING_BLOCK_LABEL_56;
        if(ellipsizecallback == null)
            break MISSING_BLOCK_LABEL_48;
        ellipsizecallback.ellipsized(0, 0);
        MeasuredText.recycle(measuredtext);
        return charsequence;
        f -= textpaint.measureText(s);
        int j = i;
        if(f >= 0.0F) goto _L2; else goto _L1
_L1:
        int k = 0;
_L10:
        if(ellipsizecallback == null)
            break MISSING_BLOCK_LABEL_94;
        ellipsizecallback.ellipsized(k, j);
        truncateat = measuredtext.mChars;
        if(!(charsequence instanceof Spanned)) goto _L4; else goto _L3
_L3:
        textpaint = (Spanned)charsequence;
_L8:
        int l = i - (j - k);
        if(!flag) goto _L6; else goto _L5
_L5:
        if(l <= 0)
            break MISSING_BLOCK_LABEL_152;
        l = k + 1;
        truncateat[k] = s.charAt(0);
        k = l;
        for(; k < j; k++)
            truncateat[k] = (char)65279;

          goto _L7
_L2:
        if(truncateat != TruncateAt.START)
            break MISSING_BLOCK_LABEL_199;
        j = i - measuredtext.breakText(i, false, f);
        k = 0;
        continue; /* Loop/switch isn't completed */
        if(truncateat == TruncateAt.END || truncateat == TruncateAt.END_SMALL)
        {
            k = measuredtext.breakText(i, true, f);
            continue; /* Loop/switch isn't completed */
        }
        j = i - measuredtext.breakText(i, false, f / 2.0F);
        k = measuredtext.breakText(j, true, f - measuredtext.measure(j, i));
        continue; /* Loop/switch isn't completed */
_L4:
        textpaint = null;
          goto _L8
_L7:
        charsequence = JVM INSTR new #114 <Class String>;
        charsequence.String(truncateat, 0, i);
        if(textpaint == null)
        {
            MeasuredText.recycle(measuredtext);
            return charsequence;
        }
        truncateat = JVM INSTR new #368 <Class SpannableString>;
        truncateat.SpannableString(charsequence);
        copySpansFrom(textpaint, 0, i, java/lang/Object, truncateat, 0);
        MeasuredText.recycle(measuredtext);
        return truncateat;
_L6:
        if(l == 0)
        {
            MeasuredText.recycle(measuredtext);
            return "";
        }
        if(textpaint != null)
            break MISSING_BLOCK_LABEL_400;
        charsequence = JVM INSTR new #179 <Class StringBuilder>;
        charsequence.StringBuilder(s.length() + l);
        charsequence.append(truncateat, 0, k);
        charsequence.append(s);
        charsequence.append(truncateat, j, i - j);
        charsequence = charsequence.toString();
        MeasuredText.recycle(measuredtext);
        return charsequence;
        textpaint = JVM INSTR new #213 <Class SpannableStringBuilder>;
        textpaint.SpannableStringBuilder();
        textpaint.append(charsequence, 0, k);
        textpaint.append(s);
        textpaint.append(charsequence, j, i);
        MeasuredText.recycle(measuredtext);
        return textpaint;
        charsequence;
        MeasuredText.recycle(measuredtext);
        throw charsequence;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static String emptyIfNull(String s)
    {
        String s1 = s;
        if(s == null)
            s1 = "";
        return s1;
    }

    public static boolean equals(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == charsequence1)
            return true;
        if(charsequence != null && charsequence1 != null)
        {
            int i = charsequence.length();
            if(i == charsequence1.length())
            {
                if((charsequence instanceof String) && (charsequence1 instanceof String))
                    return charsequence.equals(charsequence1);
                for(int j = 0; j < i; j++)
                    if(charsequence.charAt(j) != charsequence1.charAt(j))
                        return false;

                return true;
            }
        }
        return false;
    }

    public static transient CharSequence expandTemplate(CharSequence charsequence, CharSequence acharsequence[])
    {
        int i;
        if(acharsequence.length > 9)
            throw new IllegalArgumentException("max of 9 values are supported");
        charsequence = new SpannableStringBuilder(charsequence);
        i = 0;
_L2:
        char c;
        if(i >= charsequence.length())
            break MISSING_BLOCK_LABEL_137;
        if(charsequence.charAt(i) != '^')
            break MISSING_BLOCK_LABEL_236;
        c = charsequence.charAt(i + 1);
        if(c != '^')
            break MISSING_BLOCK_LABEL_78;
        charsequence.delete(i + 1, i + 2);
        i++;
        continue; /* Loop/switch isn't completed */
        int j;
        if(!Character.isDigit(c))
            break MISSING_BLOCK_LABEL_236;
        j = Character.getNumericValue(c) - 1;
        if(j >= 0)
            break MISSING_BLOCK_LABEL_139;
        try
        {
            acharsequence = JVM INSTR new #394 <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #179 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            acharsequence.IllegalArgumentException(stringbuilder.append("template requests value ^").append(j + 1).toString());
            throw acharsequence;
        }
        // Misplaced declaration of an exception variable
        catch(CharSequence acharsequence[]) { }
        return charsequence;
        if(j >= acharsequence.length)
        {
            IllegalArgumentException illegalargumentexception = JVM INSTR new #394 <Class IllegalArgumentException>;
            StringBuilder stringbuilder1 = JVM INSTR new #179 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            illegalargumentexception.IllegalArgumentException(stringbuilder1.append("template requests value ^").append(j + 1).append("; only ").append(acharsequence.length).append(" provided").toString());
            throw illegalargumentexception;
        }
        charsequence.replace(i, i + 2, acharsequence[j]);
        j = acharsequence[j].length();
        i += j;
        continue; /* Loop/switch isn't completed */
        i++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String firstNotEmpty(String s, String s1)
    {
        if(isEmpty(s))
            s = (String)Preconditions.checkStringNotEmpty(s1);
        return s;
    }

    public static CharSequence formatSelectedCount(int i)
    {
        return Resources.getSystem().getQuantityString(0x1150019, i, new Object[] {
            Integer.valueOf(i)
        });
    }

    public static int getCapsMode(CharSequence charsequence, int i, int j)
    {
        char c;
        if(i < 0)
            return 0;
        c = '\0';
        if((j & 0x1000) != 0)
            c = '\u1000';
        if((j & 0x6000) == 0)
            return c;
        int k;
label0:
        do
        {
label1:
            {
                if(i > 0)
                {
                    char c1 = charsequence.charAt(i - 1);
                    if(c1 == '"' || c1 == '\'' || Character.getType(c1) == 21)
                        break label1;
                }
                k = i;
                do
                {
                    if(k <= 0)
                        break;
                    char c4 = charsequence.charAt(k - 1);
                    if(c4 != ' ' && c4 != '\t')
                        break;
                    k--;
                } while(true);
                break label0;
            }
            i--;
        } while(true);
        if(k == 0 || charsequence.charAt(k - 1) == '\n')
            return c | 0x2000;
        if((j & 0x4000) == 0)
        {
            j = c;
            if(i != k)
                j = c | 0x2000;
            return j;
        }
        j = k;
        if(i == k)
            return c;
        char c2;
        for(; j > 0 && ((c2 = charsequence.charAt(j - 1)) == '"' || c2 == '\'' || Character.getType(c2) == 22); j--);
        if(j <= 0) goto _L2; else goto _L1
_L1:
        i = charsequence.charAt(j - 1);
          goto _L3
_L6:
        if(i != 46) goto _L5; else goto _L4
_L4:
        i = j - 2;
_L7:
        if(i >= 0)
        {
            char c3 = charsequence.charAt(i);
            if(c3 == '.')
                return c;
            if(Character.isLetter(c3))
                break MISSING_BLOCK_LABEL_298;
        }
          goto _L5
_L3:
        if(i == 46 || i == 63 || i == 33) goto _L6; else goto _L2
_L2:
        return c;
_L5:
        return c | 0x4000;
        i--;
          goto _L7
    }

    public static void getChars(CharSequence charsequence, int i, int j, char ac[], int k)
    {
        Class class1 = charsequence.getClass();
        if(class1 != java/lang/String) goto _L2; else goto _L1
_L1:
        ((String)charsequence).getChars(i, j, ac, k);
_L4:
        return;
_L2:
        if(class1 == java/lang/StringBuffer)
        {
            ((StringBuffer)charsequence).getChars(i, j, ac, k);
            continue; /* Loop/switch isn't completed */
        }
        if(class1 == java/lang/StringBuilder)
        {
            ((StringBuilder)charsequence).getChars(i, j, ac, k);
            continue; /* Loop/switch isn't completed */
        }
        if(!(charsequence instanceof GetChars))
            break; /* Loop/switch isn't completed */
        ((GetChars)charsequence).getChars(i, j, ac, k);
        if(true) goto _L4; else goto _L3
_L3:
        int l = i;
        i = k;
        while(l < j) 
        {
            ac[i] = charsequence.charAt(l);
            l++;
            i++;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public static int getLayoutDirectionFromLocale(Locale locale)
    {
        int i = 0;
        if(locale != null && locale.equals(Locale.ROOT) ^ true && ULocale.forLocale(locale).isRightToLeft() || SystemProperties.getBoolean("debug.force_rtl", false))
            i = 1;
        return i;
    }

    public static int getOffsetAfter(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
        if(i == j)
            return j;
        if(i == j - 1)
            return j;
        j = charsequence.charAt(i);
        int l;
        if(j >= '\uD800' && j <= '\uDBFF')
        {
            int k = charsequence.charAt(i + 1);
            ReplacementSpan areplacementspan[];
            int i1;
            int j1;
            if(k >= '\uDC00' && k <= '\uDFFF')
                i += 2;
            else
                i++;
        } else
        {
            i++;
        }
        l = i;
        if(charsequence instanceof Spanned)
        {
            areplacementspan = (ReplacementSpan[])((Spanned)charsequence).getSpans(i, i, android/text/style/ReplacementSpan);
            k = 0;
            do
            {
                l = i;
                if(k >= areplacementspan.length)
                    break;
                i1 = ((Spanned)charsequence).getSpanStart(areplacementspan[k]);
                j1 = ((Spanned)charsequence).getSpanEnd(areplacementspan[k]);
                l = i;
                if(i1 < i)
                {
                    l = i;
                    if(j1 > i)
                        l = j1;
                }
                k++;
                i = l;
            } while(true);
        }
        return l;
    }

    public static int getOffsetBefore(CharSequence charsequence, int i)
    {
        if(i == 0)
            return 0;
        if(i == 1)
            return 0;
        char c = charsequence.charAt(i - 1);
        int k;
        if(c >= '\uDC00' && c <= '\uDFFF')
        {
            int j = charsequence.charAt(i - 2);
            ReplacementSpan areplacementspan[];
            int l;
            int i1;
            if(j >= '\uD800' && j <= '\uDBFF')
                i -= 2;
            else
                i--;
        } else
        {
            i--;
        }
        k = i;
        if(charsequence instanceof Spanned)
        {
            areplacementspan = (ReplacementSpan[])((Spanned)charsequence).getSpans(i, i, android/text/style/ReplacementSpan);
            j = 0;
            do
            {
                k = i;
                if(j >= areplacementspan.length)
                    break;
                l = ((Spanned)charsequence).getSpanStart(areplacementspan[j]);
                i1 = ((Spanned)charsequence).getSpanEnd(areplacementspan[j]);
                k = i;
                if(l < i)
                {
                    k = i;
                    if(i1 > i)
                        k = l;
                }
                j++;
                i = k;
            } while(true);
        }
        return k;
    }

    public static CharSequence getReverse(CharSequence charsequence, int i, int j)
    {
        return new Reverser(charsequence, i, j);
    }

    public static int getTrimmedLength(CharSequence charsequence)
    {
        int i = charsequence.length();
        int j;
        for(j = 0; j < i && charsequence.charAt(j) <= ' '; j++);
        for(; i > j && charsequence.charAt(i - 1) <= ' '; i--);
        return i - j;
    }

    public static boolean hasStyleSpan(Spanned spanned)
    {
        boolean flag;
        Class aclass[];
        int i;
        if(spanned != null)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag);
        aclass = new Class[3];
        aclass[0] = android/text/style/CharacterStyle;
        aclass[1] = android/text/style/ParagraphStyle;
        aclass[2] = android/text/style/UpdateAppearance;
        i = aclass.length;
        for(int j = 0; j < i; j++)
        {
            Class class1 = aclass[j];
            if(spanned.nextSpanTransition(-1, spanned.length(), class1) < spanned.length())
                return true;
        }

        return false;
    }

    public static String htmlEncode(String s)
    {
        StringBuilder stringbuilder;
        int i;
        stringbuilder = new StringBuilder();
        i = 0;
_L8:
        char c;
        if(i >= s.length())
            break MISSING_BLOCK_LABEL_143;
        c = s.charAt(i);
        c;
        JVM INSTR lookupswitch 5: default 76
    //                   34: 132
    //                   38: 110
    //                   39: 121
    //                   60: 88
    //                   62: 99;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
        break MISSING_BLOCK_LABEL_132;
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuilder.append(c);
_L9:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        stringbuilder.append("&lt;");
          goto _L9
_L6:
        stringbuilder.append("&gt;");
          goto _L9
_L3:
        stringbuilder.append("&amp;");
          goto _L9
_L4:
        stringbuilder.append("&#39;");
          goto _L9
        stringbuilder.append("&quot;");
          goto _L9
        return stringbuilder.toString();
    }

    public static int indexOf(CharSequence charsequence, char c)
    {
        return indexOf(charsequence, c, 0);
    }

    public static int indexOf(CharSequence charsequence, char c, int i)
    {
        if(charsequence.getClass() == java/lang/String)
            return ((String)charsequence).indexOf(c, i);
        else
            return indexOf(charsequence, c, i, charsequence.length());
    }

    public static int indexOf(CharSequence charsequence, char c, int i, int j)
    {
        char ac[] = charsequence.getClass();
          goto _L1
_L6:
        int k;
        ac = obtain(500);
        k = i;
_L7:
        if(k >= j) goto _L3; else goto _L2
_L2:
        int l = k + 500;
        i = l;
        if(l > j)
            i = j;
        getChars(charsequence, k, i, ac, 0);
          goto _L4
_L1:
        if((charsequence instanceof GetChars) || ac == java/lang/StringBuffer || ac == java/lang/StringBuilder || ac == java/lang/String) goto _L6; else goto _L5
_L4:
        for(int i1 = 0; i1 < i - k; i1++)
            if(ac[i1] == c)
            {
                recycle(ac);
                return i1 + k;
            }

        k = i;
          goto _L7
_L3:
        recycle(ac);
        return -1;
_L5:
        do
        {
            if(i >= j)
                break MISSING_BLOCK_LABEL_156;
            if(charsequence.charAt(i) == c)
                return i;
            i++;
        } while(true);
        return -1;
          goto _L4
    }

    public static int indexOf(CharSequence charsequence, CharSequence charsequence1)
    {
        return indexOf(charsequence, charsequence1, 0, charsequence.length());
    }

    public static int indexOf(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        return indexOf(charsequence, charsequence1, i, charsequence.length());
    }

    public static int indexOf(CharSequence charsequence, CharSequence charsequence1, int i, int j)
    {
        int k = charsequence1.length();
        if(k == 0)
            return i;
        char c = charsequence1.charAt(0);
        do
        {
            i = indexOf(charsequence, c, i);
            if(i > j - k)
                return -1;
            if(i < 0)
                return -1;
            if(regionMatches(charsequence, i, charsequence1, 0, k))
                return i;
            i++;
        } while(true);
    }

    public static boolean isDigitsOnly(CharSequence charsequence)
    {
        int i = charsequence.length();
        int k;
        for(int j = 0; j < i; j += Character.charCount(k))
        {
            k = Character.codePointAt(charsequence, j);
            if(!Character.isDigit(k))
                return false;
        }

        return true;
    }

    public static boolean isEmpty(CharSequence charsequence)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(charsequence != null)
            if(charsequence.length() == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isGraphic(char c)
    {
        boolean flag = false;
        int i = Character.getType(c);
        boolean flag1 = flag;
        if(i != 15)
        {
            flag1 = flag;
            if(i != 16)
            {
                flag1 = flag;
                if(i != 19)
                {
                    flag1 = flag;
                    if(i != 0)
                    {
                        flag1 = flag;
                        if(i != 13)
                        {
                            flag1 = flag;
                            if(i != 14)
                            {
                                flag1 = flag;
                                if(i != 12)
                                    flag1 = true;
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public static boolean isGraphic(CharSequence charsequence)
    {
        int i = charsequence.length();
        int k;
        for(int j = 0; j < i; j += Character.charCount(k))
        {
            k = Character.codePointAt(charsequence, j);
            int l = Character.getType(k);
            if(l != 15 && l != 16 && l != 19 && l != 0 && l != 13 && l != 14 && l != 12)
                return true;
        }

        return false;
    }

    public static boolean isPrintableAscii(char c)
    {
        boolean flag = true;
        if(' ' > c || c > '~') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(c != '\r')
        {
            flag1 = flag;
            if(c != '\n')
                flag1 = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isPrintableAsciiOnly(CharSequence charsequence)
    {
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!isPrintableAscii(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static String join(CharSequence charsequence, Iterable iterable)
    {
        StringBuilder stringbuilder = new StringBuilder();
        iterable = iterable.iterator();
        if(iterable.hasNext())
        {
            stringbuilder.append(iterable.next());
            for(; iterable.hasNext(); stringbuilder.append(iterable.next()))
                stringbuilder.append(charsequence);

        }
        return stringbuilder.toString();
    }

    public static String join(CharSequence charsequence, Object aobj[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = true;
        int i = 0;
        int j = aobj.length;
        while(i < j) 
        {
            Object obj = aobj[i];
            if(flag)
                flag = false;
            else
                stringbuilder.append(charsequence);
            stringbuilder.append(obj);
            i++;
        }
        return stringbuilder.toString();
    }

    public static int lastIndexOf(CharSequence charsequence, char c)
    {
        return lastIndexOf(charsequence, c, charsequence.length() - 1);
    }

    public static int lastIndexOf(CharSequence charsequence, char c, int i)
    {
        if(charsequence.getClass() == java/lang/String)
            return ((String)charsequence).lastIndexOf(c, i);
        else
            return lastIndexOf(charsequence, c, 0, i);
    }

    public static int lastIndexOf(CharSequence charsequence, char c, int i, int j)
    {
        int k;
        char ac[];
        if(j < 0)
            return -1;
        k = j;
        if(j >= charsequence.length())
            k = charsequence.length() - 1;
        k++;
        ac = charsequence.getClass();
          goto _L1
_L6:
        ac = obtain(500);
_L7:
        if(i >= k) goto _L3; else goto _L2
_L2:
        int l = k - 500;
        j = l;
        if(l < i)
            j = i;
        getChars(charsequence, j, k, ac, 0);
          goto _L4
_L1:
        if((charsequence instanceof GetChars) || ac == java/lang/StringBuffer || ac == java/lang/StringBuilder || ac == java/lang/String) goto _L6; else goto _L5
_L5:
        j = k - 1;
        break MISSING_BLOCK_LABEL_147;
_L4:
        for(k = k - j - 1; k >= 0; k--)
            if(ac[k] == c)
            {
                recycle(ac);
                return k + j;
            }

        k = j;
          goto _L7
_L3:
        recycle(ac);
        return -1;
        do
        {
            if(j < i)
                break MISSING_BLOCK_LABEL_190;
            if(charsequence.charAt(j) == c)
                return j;
            j--;
        } while(true);
        return -1;
          goto _L4
    }

    public static int length(String s)
    {
        int i;
        if(isEmpty(s))
            i = 0;
        else
            i = s.length();
        return i;
    }

    public static CharSequence listEllipsize(Context context, List list, String s, TextPaint textpaint, float f, int i)
    {
        if(list == null)
            return "";
        int j = list.size();
        if(j == 0)
            return "";
        BidiFormatter bidiformatter;
        SpannableStringBuilder spannablestringbuilder;
        int ai[];
        if(context == null)
        {
            context = null;
            bidiformatter = BidiFormatter.getInstance();
        } else
        {
            context = context.getResources();
            bidiformatter = BidiFormatter.getInstance(context.getConfiguration().getLocales().get(0));
        }
        spannablestringbuilder = new SpannableStringBuilder();
        ai = new int[j];
        for(int k = 0; k < j; k++)
        {
            spannablestringbuilder.append(bidiformatter.unicodeWrap((CharSequence)list.get(k)));
            if(k != j - 1)
                spannablestringbuilder.append(s);
            ai[k] = spannablestringbuilder.length();
        }

        int i1;
        for(int l = j - 1; l >= 0; l--)
        {
            spannablestringbuilder.delete(ai[l], spannablestringbuilder.length());
            i1 = j - l - 1;
            if(i1 > 0)
            {
                if(context == null)
                    list = ELLIPSIS_STRING;
                else
                    list = context.getQuantityString(i, i1, new Object[] {
                        Integer.valueOf(i1)
                    });
                spannablestringbuilder.append(bidiformatter.unicodeWrap(list));
            }
            if(textpaint.measureText(spannablestringbuilder, 0, spannablestringbuilder.length()) <= f)
                return spannablestringbuilder;
        }

        return "";
    }

    public static String nullIfEmpty(String s)
    {
        String s1 = s;
        if(isEmpty(s))
            s1 = null;
        return s1;
    }

    static char[] obtain(int i)
    {
        char ac[] = ((char []) (sLock));
        ac;
        JVM INSTR monitorenter ;
        char ac1[];
        ac1 = sTemp;
        sTemp = null;
        ac;
        JVM INSTR monitorexit ;
label0:
        {
            if(ac1 != null)
            {
                ac = ac1;
                if(ac1.length >= i)
                    break label0;
            }
            ac = ArrayUtils.newUnpaddedCharArray(i);
        }
        return ac;
        Exception exception;
        exception;
        throw exception;
    }

    public static long packRangeInLong(int i, int j)
    {
        return (long)i << 32 | (long)j;
    }

    private static void readSpan(Parcel parcel, Spannable spannable, Object obj)
    {
        spannable.setSpan(obj, parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    static void recycle(char ac[])
    {
        if(ac.length > 1000)
            return;
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        sTemp = ac;
        obj;
        JVM INSTR monitorexit ;
        return;
        ac;
        throw ac;
    }

    public static boolean regionMatches(CharSequence charsequence, int i, CharSequence charsequence1, int j, int k)
    {
        int l = k * 2;
        if(l < k)
            throw new IndexOutOfBoundsException();
        char ac[] = obtain(l);
        getChars(charsequence, i, i + k, ac, 0);
        getChars(charsequence1, j, j + k, ac, k);
        boolean flag = true;
        i = 0;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(i < k)
                {
                    if(ac[i] == ac[i + k])
                        break label0;
                    flag1 = false;
                }
                recycle(ac);
                return flag1;
            }
            i++;
        } while(true);
    }

    public static Object[] removeEmptySpans(Object aobj[], Spanned spanned, Class class1)
    {
        Object aobj1[] = null;
        int i = 0;
        int j = 0;
        while(j < aobj.length) 
        {
            Object obj = aobj[j];
            Object aobj2[];
            int k;
            if(spanned.getSpanStart(obj) == spanned.getSpanEnd(obj))
            {
                aobj2 = aobj1;
                k = i;
                if(aobj1 == null)
                {
                    aobj2 = (Object[])Array.newInstance(class1, aobj.length - 1);
                    System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj2)), 0, j);
                    k = j;
                }
            } else
            {
                aobj2 = aobj1;
                k = i;
                if(aobj1 != null)
                {
                    aobj1[i] = obj;
                    k = i + 1;
                    aobj2 = aobj1;
                }
            }
            j++;
            aobj1 = aobj2;
            i = k;
        }
        if(aobj1 != null)
        {
            aobj = (Object[])Array.newInstance(class1, i);
            System.arraycopy(((Object) (aobj1)), 0, ((Object) (aobj)), 0, i);
            return aobj;
        } else
        {
            return aobj;
        }
    }

    public static CharSequence replace(CharSequence charsequence, String as[], CharSequence acharsequence[])
    {
        charsequence = new SpannableStringBuilder(charsequence);
        for(int i = 0; i < as.length; i++)
        {
            int k = indexOf(charsequence, as[i]);
            if(k >= 0)
                charsequence.setSpan(as[i], k, as[i].length() + k, 33);
        }

        for(int j = 0; j < as.length; j++)
        {
            int i1 = charsequence.getSpanStart(as[j]);
            int l = charsequence.getSpanEnd(as[j]);
            if(i1 >= 0)
                charsequence.replace(i1, l, acharsequence[j]);
        }

        return charsequence;
    }

    public static String safeIntern(String s)
    {
        String s1 = null;
        if(s != null)
            s1 = s.intern();
        return s1;
    }

    private static float setPara(MeasuredText measuredtext, TextPaint textpaint, CharSequence charsequence, int i, int j, TextDirectionHeuristic textdirectionheuristic)
    {
        int k;
        float f;
        measuredtext.setPara(charsequence, i, j, textdirectionheuristic, null);
        if(charsequence instanceof Spanned)
            charsequence = (Spanned)charsequence;
        else
            charsequence = null;
        k = j - i;
        if(charsequence != null) goto _L2; else goto _L1
_L1:
        f = measuredtext.addStyleRun(textpaint, k, null);
_L4:
        return f;
_L2:
        float f1 = 0.0F;
        i = 0;
        do
        {
            f = f1;
            if(i >= k)
                continue;
            j = charsequence.nextSpanTransition(i, k, android/text/style/MetricAffectingSpan);
            f1 += measuredtext.addStyleRun(textpaint, (MetricAffectingSpan[])removeEmptySpans((MetricAffectingSpan[])charsequence.getSpans(i, j, android/text/style/MetricAffectingSpan), charsequence, android/text/style/MetricAffectingSpan), j - i, null);
            i = j;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String[] split(String s, String s1)
    {
        if(s.length() == 0)
            return EMPTY_STRING_ARRAY;
        else
            return s.split(s1, -1);
    }

    public static String[] split(String s, Pattern pattern)
    {
        if(s.length() == 0)
            return EMPTY_STRING_ARRAY;
        else
            return pattern.split(s, -1);
    }

    public static CharSequence stringOrSpannedString(CharSequence charsequence)
    {
        if(charsequence == null)
            return null;
        if(charsequence instanceof SpannedString)
            return charsequence;
        if(charsequence instanceof Spanned)
            return new SpannedString(charsequence);
        else
            return charsequence.toString();
    }

    public static String substring(CharSequence charsequence, int i, int j)
    {
        if(charsequence instanceof String)
            return ((String)charsequence).substring(i, j);
        if(charsequence instanceof StringBuilder)
            return ((StringBuilder)charsequence).substring(i, j);
        if(charsequence instanceof StringBuffer)
        {
            return ((StringBuffer)charsequence).substring(i, j);
        } else
        {
            char ac[] = obtain(j - i);
            getChars(charsequence, i, j, ac, 0);
            charsequence = new String(ac, 0, j - i);
            recycle(ac);
            return charsequence;
        }
    }

    public static CharSequence toUpperCase(Locale locale, CharSequence charsequence, boolean flag)
    {
        Object obj = new Edits();
        if(!flag)
        {
            locale = (StringBuilder)CaseMap.toUpper().apply(locale, charsequence, new StringBuilder(), ((Edits) (obj)));
            if(((Edits) (obj)).hasChanges())
                charsequence = locale;
            return charsequence;
        }
        locale = (SpannableStringBuilder)CaseMap.toUpper().apply(locale, charsequence, new SpannableStringBuilder(), ((Edits) (obj)));
        if(!((Edits) (obj)).hasChanges())
            return charsequence;
        obj = ((Edits) (obj)).getFineIterator();
        int i = charsequence.length();
        Spanned spanned = (Spanned)charsequence;
        charsequence = ((CharSequence) (spanned.getSpans(0, i, java/lang/Object)));
        int j = 0;
        int k = charsequence.length;
        while(j < k) 
        {
            Object obj1 = charsequence[j];
            int l = spanned.getSpanStart(obj1);
            int i1 = spanned.getSpanEnd(obj1);
            int j1 = spanned.getSpanFlags(obj1);
            if(l == i)
                l = locale.length();
            else
                l = toUpperMapToDest(((android.icu.text.Edits.Iterator) (obj)), l);
            if(i1 == i)
                i1 = locale.length();
            else
                i1 = toUpperMapToDest(((android.icu.text.Edits.Iterator) (obj)), i1);
            locale.setSpan(obj1, l, i1, j1);
            j++;
        }
        return locale;
    }

    private static int toUpperMapToDest(android.icu.text.Edits.Iterator iterator, int i)
    {
        iterator.findSourceIndex(i);
        if(i == iterator.sourceIndex())
            return iterator.destinationIndex();
        if(iterator.hasChange())
            return iterator.destinationIndex() + iterator.newLength();
        else
            return iterator.destinationIndex() + (i - iterator.sourceIndex());
    }

    public static CharSequence trimNoCopySpans(CharSequence charsequence)
    {
        if(charsequence != null && (charsequence instanceof Spanned))
            return new SpannableStringBuilder(charsequence);
        else
            return charsequence;
    }

    public static int unpackRangeEndFromLong(long l)
    {
        return (int)(0xffffffffL & l);
    }

    public static int unpackRangeStartFromLong(long l)
    {
        return (int)(l >>> 32);
    }

    public static void wrap(StringBuilder stringbuilder, String s, String s1)
    {
        stringbuilder.insert(0, s);
        stringbuilder.append(s1);
    }

    public static void writeToParcel(CharSequence charsequence, Parcel parcel, int i)
    {
        if(charsequence instanceof Spanned)
        {
            parcel.writeInt(0);
            parcel.writeString(charsequence.toString());
            Spanned spanned = (Spanned)charsequence;
            Object aobj[] = spanned.getSpans(0, charsequence.length(), java/lang/Object);
            int j = 0;
            while(j < aobj.length) 
            {
                Object obj = aobj[j];
                Object obj1 = aobj[j];
                charsequence = ((CharSequence) (obj1));
                if(obj1 instanceof CharacterStyle)
                    charsequence = ((CharacterStyle)obj1).getUnderlying();
                if(charsequence instanceof ParcelableSpan)
                {
                    charsequence = (ParcelableSpan)charsequence;
                    int k = charsequence.getSpanTypeIdInternal();
                    if(k < 1 || k > 26)
                    {
                        Log.e("TextUtils", (new StringBuilder()).append("External class \"").append(charsequence.getClass().getSimpleName()).append("\" is attempting to use the frameworks-only ParcelableSpan").append(" interface").toString());
                    } else
                    {
                        parcel.writeInt(k);
                        charsequence.writeToParcelInternal(parcel, i);
                        writeWhere(parcel, spanned, obj);
                    }
                }
                j++;
            }
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            if(charsequence != null)
                parcel.writeString(charsequence.toString());
            else
                parcel.writeString(null);
        }
    }

    private static void writeWhere(Parcel parcel, Spanned spanned, Object obj)
    {
        parcel.writeInt(spanned.getSpanStart(obj));
        parcel.writeInt(spanned.getSpanEnd(obj));
        parcel.writeInt(spanned.getSpanFlags(obj));
    }

    public static final int ABSOLUTE_SIZE_SPAN = 16;
    public static final int ACCESSIBILITY_CLICKABLE_SPAN = 25;
    public static final int ACCESSIBILITY_URL_SPAN = 26;
    public static final int ALIGNMENT_SPAN = 1;
    public static final int ANNOTATION = 18;
    public static final int BACKGROUND_COLOR_SPAN = 12;
    public static final int BULLET_SPAN = 8;
    public static final int CAP_MODE_CHARACTERS = 4096;
    public static final int CAP_MODE_SENTENCES = 16384;
    public static final int CAP_MODE_WORDS = 8192;
    public static final android.os.Parcelable.Creator CHAR_SEQUENCE_CREATOR = new android.os.Parcelable.Creator() {

        public CharSequence createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            Object obj = parcel.readString();
            if(obj == null)
                return null;
            if(i == 1)
                return ((CharSequence) (obj));
            obj = new SpannableString(((CharSequence) (obj)));
            do
            {
                int j = parcel.readInt();
                if(j == 0)
                    return ((CharSequence) (obj));
                switch(j)
                {
                default:
                    throw new RuntimeException((new StringBuilder()).append("bogus span encoding ").append(j).toString());

                case 1: // '\001'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new android.text.style.AlignmentSpan.Standard(parcel));
                    break;

                case 2: // '\002'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new ForegroundColorSpan(parcel));
                    break;

                case 3: // '\003'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new RelativeSizeSpan(parcel));
                    break;

                case 4: // '\004'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new ScaleXSpan(parcel));
                    break;

                case 5: // '\005'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new StrikethroughSpan(parcel));
                    break;

                case 6: // '\006'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new UnderlineSpan(parcel));
                    break;

                case 7: // '\007'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new StyleSpan(parcel));
                    break;

                case 8: // '\b'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new BulletSpan(parcel));
                    break;

                case 9: // '\t'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new QuoteSpan(parcel));
                    break;

                case 10: // '\n'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new android.text.style.LeadingMarginSpan.Standard(parcel));
                    break;

                case 11: // '\013'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new URLSpan(parcel));
                    break;

                case 12: // '\f'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new BackgroundColorSpan(parcel));
                    break;

                case 13: // '\r'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new TypefaceSpan(parcel));
                    break;

                case 14: // '\016'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new SuperscriptSpan(parcel));
                    break;

                case 15: // '\017'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new SubscriptSpan(parcel));
                    break;

                case 16: // '\020'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new AbsoluteSizeSpan(parcel));
                    break;

                case 17: // '\021'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new TextAppearanceSpan(parcel));
                    break;

                case 18: // '\022'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new Annotation(parcel));
                    break;

                case 19: // '\023'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new SuggestionSpan(parcel));
                    break;

                case 20: // '\024'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new SpellCheckSpan(parcel));
                    break;

                case 21: // '\025'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new SuggestionRangeSpan(parcel));
                    break;

                case 22: // '\026'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new EasyEditSpan(parcel));
                    break;

                case 23: // '\027'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new LocaleSpan(parcel));
                    break;

                case 24: // '\030'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new TtsSpan(parcel));
                    break;

                case 25: // '\031'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new AccessibilityClickableSpan(parcel));
                    break;

                case 26: // '\032'
                    TextUtils._2D_wrap0(parcel, ((Spannable) (obj)), new AccessibilityURLSpan(parcel));
                    break;
                }
            } while(true);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CharSequence[] newArray(int i)
        {
            return new CharSequence[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int EASY_EDIT_SPAN = 22;
    static final char ELLIPSIS_NORMAL[] = {
        '\u2026'
    };
    public static final String ELLIPSIS_STRING = new String(ELLIPSIS_NORMAL);
    static final char ELLIPSIS_TWO_DOTS[] = {
        '\u2025'
    };
    private static final String ELLIPSIS_TWO_DOTS_STRING = new String(ELLIPSIS_TWO_DOTS);
    private static String EMPTY_STRING_ARRAY[] = new String[0];
    public static final int FIRST_SPAN = 1;
    public static final int FOREGROUND_COLOR_SPAN = 2;
    public static final int LAST_SPAN = 26;
    public static final int LEADING_MARGIN_SPAN = 10;
    public static final int LOCALE_SPAN = 23;
    public static final int QUOTE_SPAN = 9;
    public static final int RELATIVE_SIZE_SPAN = 3;
    public static final int SCALE_X_SPAN = 4;
    public static final int SPELL_CHECK_SPAN = 20;
    public static final int STRIKETHROUGH_SPAN = 5;
    public static final int STYLE_SPAN = 7;
    public static final int SUBSCRIPT_SPAN = 15;
    public static final int SUGGESTION_RANGE_SPAN = 21;
    public static final int SUGGESTION_SPAN = 19;
    public static final int SUPERSCRIPT_SPAN = 14;
    private static final String TAG = "TextUtils";
    public static final int TEXT_APPEARANCE_SPAN = 17;
    public static final int TTS_SPAN = 24;
    public static final int TYPEFACE_SPAN = 13;
    public static final int UNDERLINE_SPAN = 6;
    public static final int URL_SPAN = 11;
    private static final char ZWNBS_CHAR = 65279;
    private static Object sLock = new Object();
    private static char sTemp[] = null;

}
