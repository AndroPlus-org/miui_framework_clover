// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.*;
import android.text.*;
import android.text.style.*;
import android.util.SparseArray;

// Referenced classes of package android.content.res:
//            Resources, ColorStateList

final class StringBlock
{
    private static class Height
        implements android.text.style.LineHeightSpan.WithDensity
    {

        public void chooseHeight(CharSequence charsequence, int i, int j, int k, int l, android.graphics.Paint.FontMetricsInt fontmetricsint)
        {
            chooseHeight(charsequence, i, j, k, l, fontmetricsint, null);
        }

        public void chooseHeight(CharSequence charsequence, int i, int j, int k, int l, android.graphics.Paint.FontMetricsInt fontmetricsint, TextPaint textpaint)
        {
            j = mSize;
            i = j;
            if(textpaint != null)
                i = (int)((float)j * textpaint.density);
            if(fontmetricsint.bottom - fontmetricsint.top < i)
            {
                fontmetricsint.top = fontmetricsint.bottom - i;
                fontmetricsint.ascent = fontmetricsint.ascent - i;
            } else
            {
                if(sProportion == 0.0F)
                {
                    textpaint = new Paint();
                    textpaint.setTextSize(100F);
                    charsequence = new Rect();
                    textpaint.getTextBounds("ABCDEFG", 0, 7, charsequence);
                    sProportion = (float)((Rect) (charsequence)).top / textpaint.ascent();
                }
                j = (int)Math.ceil((float)(-fontmetricsint.top) * sProportion);
                if(i - fontmetricsint.descent >= j)
                {
                    fontmetricsint.top = fontmetricsint.bottom - i;
                    fontmetricsint.ascent = fontmetricsint.descent - i;
                } else
                if(i >= j)
                {
                    j = -j;
                    fontmetricsint.ascent = j;
                    fontmetricsint.top = j;
                    i = fontmetricsint.top + i;
                    fontmetricsint.descent = i;
                    fontmetricsint.bottom = i;
                } else
                {
                    i = -i;
                    fontmetricsint.ascent = i;
                    fontmetricsint.top = i;
                    fontmetricsint.descent = 0;
                    fontmetricsint.bottom = 0;
                }
            }
        }

        private static float sProportion = 0.0F;
        private int mSize;


        public Height(int i)
        {
            mSize = i;
        }
    }

    static final class StyleIDs
    {

        static int _2D_get0(StyleIDs styleids)
        {
            return styleids.bigId;
        }

        static int _2D_get1(StyleIDs styleids)
        {
            return styleids.boldId;
        }

        static int _2D_get10(StyleIDs styleids)
        {
            return styleids.underlineId;
        }

        static int _2D_get2(StyleIDs styleids)
        {
            return styleids.italicId;
        }

        static int _2D_get3(StyleIDs styleids)
        {
            return styleids.listItemId;
        }

        static int _2D_get4(StyleIDs styleids)
        {
            return styleids.marqueeId;
        }

        static int _2D_get5(StyleIDs styleids)
        {
            return styleids.smallId;
        }

        static int _2D_get6(StyleIDs styleids)
        {
            return styleids.strikeId;
        }

        static int _2D_get7(StyleIDs styleids)
        {
            return styleids.subId;
        }

        static int _2D_get8(StyleIDs styleids)
        {
            return styleids.supId;
        }

        static int _2D_get9(StyleIDs styleids)
        {
            return styleids.ttId;
        }

        static int _2D_set0(StyleIDs styleids, int i)
        {
            styleids.bigId = i;
            return i;
        }

        static int _2D_set1(StyleIDs styleids, int i)
        {
            styleids.boldId = i;
            return i;
        }

        static int _2D_set10(StyleIDs styleids, int i)
        {
            styleids.underlineId = i;
            return i;
        }

        static int _2D_set2(StyleIDs styleids, int i)
        {
            styleids.italicId = i;
            return i;
        }

        static int _2D_set3(StyleIDs styleids, int i)
        {
            styleids.listItemId = i;
            return i;
        }

        static int _2D_set4(StyleIDs styleids, int i)
        {
            styleids.marqueeId = i;
            return i;
        }

        static int _2D_set5(StyleIDs styleids, int i)
        {
            styleids.smallId = i;
            return i;
        }

        static int _2D_set6(StyleIDs styleids, int i)
        {
            styleids.strikeId = i;
            return i;
        }

        static int _2D_set7(StyleIDs styleids, int i)
        {
            styleids.subId = i;
            return i;
        }

        static int _2D_set8(StyleIDs styleids, int i)
        {
            styleids.supId = i;
            return i;
        }

        static int _2D_set9(StyleIDs styleids, int i)
        {
            styleids.ttId = i;
            return i;
        }

        private int bigId;
        private int boldId;
        private int italicId;
        private int listItemId;
        private int marqueeId;
        private int smallId;
        private int strikeId;
        private int subId;
        private int supId;
        private int ttId;
        private int underlineId;

        StyleIDs()
        {
            boldId = -1;
            italicId = -1;
            underlineId = -1;
            ttId = -1;
            bigId = -1;
            smallId = -1;
            subId = -1;
            supId = -1;
            strikeId = -1;
            listItemId = -1;
            marqueeId = -1;
        }
    }


    StringBlock(long l, boolean flag)
    {
        mStyleIDs = null;
        mNative = l;
        mUseSparse = flag;
        mOwnsNative = false;
    }

    public StringBlock(byte abyte0[], int i, int j, boolean flag)
    {
        mStyleIDs = null;
        mNative = nativeCreate(abyte0, i, j);
        mUseSparse = flag;
        mOwnsNative = true;
    }

    public StringBlock(byte abyte0[], boolean flag)
    {
        mStyleIDs = null;
        mNative = nativeCreate(abyte0, 0, abyte0.length);
        mUseSparse = flag;
        mOwnsNative = true;
    }

    private static void addParagraphSpan(Spannable spannable, Object obj, int i, int j)
    {
        int k;
        int l;
        k = spannable.length();
        l = i;
        if(i == 0) goto _L2; else goto _L1
_L1:
        l = i;
        if(i == k) goto _L2; else goto _L3
_L3:
        l = i;
        if(spannable.charAt(i - 1) == '\n') goto _L2; else goto _L4
_L4:
        i--;
_L12:
        l = i;
        if(i <= 0) goto _L2; else goto _L5
_L5:
        if(spannable.charAt(i - 1) != '\n') goto _L7; else goto _L6
_L6:
        l = i;
_L2:
        int i1 = j;
        if(j == 0) goto _L9; else goto _L8
_L8:
        i1 = j;
        if(j == k) goto _L9; else goto _L10
_L10:
        i1 = j;
        if(spannable.charAt(j - 1) == '\n') goto _L9; else goto _L11
_L11:
        i = j + 1;
_L13:
        i1 = i;
        if(i < k)
        {
            if(spannable.charAt(i - 1) != '\n')
                break MISSING_BLOCK_LABEL_151;
            i1 = i;
        }
_L9:
        spannable.setSpan(obj, l, i1, 51);
        return;
_L7:
        i--;
          goto _L12
        i++;
          goto _L13
    }

    private CharSequence applyStyles(String s, int ai[], StyleIDs styleids)
    {
        int i;
        if(ai.length == 0)
            return s;
        s = new SpannableString(s);
        i = 0;
_L1:
        String s1;
        if(i >= ai.length)
            break MISSING_BLOCK_LABEL_931;
        int j = ai[i];
        if(j == StyleIDs._2D_get1(styleids))
            s.setSpan(new StyleSpan(1), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get2(styleids))
            s.setSpan(new StyleSpan(2), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get10(styleids))
            s.setSpan(new UnderlineSpan(), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get9(styleids))
            s.setSpan(new TypefaceSpan("monospace"), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get0(styleids))
            s.setSpan(new RelativeSizeSpan(1.25F), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get5(styleids))
            s.setSpan(new RelativeSizeSpan(0.8F), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get7(styleids))
            s.setSpan(new SubscriptSpan(), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get8(styleids))
            s.setSpan(new SuperscriptSpan(), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get6(styleids))
            s.setSpan(new StrikethroughSpan(), ai[i + 1], ai[i + 2] + 1, 33);
        else
        if(j == StyleIDs._2D_get3(styleids))
            addParagraphSpan(s, new BulletSpan(10), ai[i + 1], ai[i + 2] + 1);
        else
        if(j == StyleIDs._2D_get4(styleids))
        {
            s.setSpan(android.text.TextUtils.TruncateAt.MARQUEE, ai[i + 1], ai[i + 2] + 1, 18);
        } else
        {
            s1 = nativeGetString(mNative, j);
            if(s1.startsWith("font;"))
            {
                String s2 = subtag(s1, ";height=");
                if(s2 != null)
                    addParagraphSpan(s, new Height(Integer.parseInt(s2)), ai[i + 1], ai[i + 2] + 1);
                s2 = subtag(s1, ";size=");
                if(s2 != null)
                    s.setSpan(new AbsoluteSizeSpan(Integer.parseInt(s2), true), ai[i + 1], ai[i + 2] + 1, 33);
                s2 = subtag(s1, ";fgcolor=");
                if(s2 != null)
                    s.setSpan(getColor(s2, true), ai[i + 1], ai[i + 2] + 1, 33);
                s2 = subtag(s1, ";color=");
                if(s2 != null)
                    s.setSpan(getColor(s2, true), ai[i + 1], ai[i + 2] + 1, 33);
                s2 = subtag(s1, ";bgcolor=");
                if(s2 != null)
                    s.setSpan(getColor(s2, false), ai[i + 1], ai[i + 2] + 1, 33);
                s1 = subtag(s1, ";face=");
                if(s1 != null)
                    s.setSpan(new TypefaceSpan(s1), ai[i + 1], ai[i + 2] + 1, 33);
            } else
            {
                if(!s1.startsWith("a;"))
                    continue; /* Loop/switch isn't completed */
                s1 = subtag(s1, ";href=");
                if(s1 != null)
                    s.setSpan(new URLSpan(s1), ai[i + 1], ai[i + 2] + 1, 33);
            }
        }
_L3:
        i += 3;
          goto _L1
        if(!s1.startsWith("annotation;")) goto _L3; else goto _L2
_L2:
        int l;
        int i1;
        l = s1.length();
        i1 = s1.indexOf(';');
_L6:
        if(i1 >= l) goto _L3; else goto _L4
_L4:
        int j1 = s1.indexOf('=', i1);
        if(j1 < 0) goto _L3; else goto _L5
_L5:
        int k1 = s1.indexOf(';', j1);
        int k = k1;
        if(k1 < 0)
            k = l;
        s.setSpan(new Annotation(s1.substring(i1 + 1, j1), s1.substring(j1 + 1, k)), ai[i + 1], ai[i + 2] + 1, 33);
        i1 = k;
          goto _L6
        return new SpannedString(s);
    }

    private static CharacterStyle getColor(String s, boolean flag)
    {
        int i = 0xff000000;
        int j = i;
        if(!TextUtils.isEmpty(s))
            if(s.startsWith("@"))
            {
                Resources resources = Resources.getSystem();
                int k = resources.getIdentifier(s.substring(1), "color", "android");
                j = i;
                if(k != 0)
                {
                    s = resources.getColorStateList(k, null);
                    if(flag)
                        return new TextAppearanceSpan(null, 0, 0, s, null);
                    j = s.getDefaultColor();
                }
            } else
            {
                try
                {
                    j = Color.parseColor(s);
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    j = 0xff000000;
                }
            }
        if(flag)
            return new ForegroundColorSpan(j);
        else
            return new BackgroundColorSpan(j);
    }

    private static native long nativeCreate(byte abyte0[], int i, int j);

    private static native void nativeDestroy(long l);

    private static native int nativeGetSize(long l);

    private static native String nativeGetString(long l, int i);

    private static native int[] nativeGetStyle(long l, int i);

    private static String subtag(String s, String s1)
    {
        int i = s.indexOf(s1);
        if(i < 0)
            return null;
        int j = i + s1.length();
        i = s.indexOf(';', j);
        if(i < 0)
            return s.substring(j);
        else
            return s.substring(j, i);
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        if(mOwnsNative)
            nativeDestroy(mNative);
        return;
        Exception exception;
        exception;
        if(mOwnsNative)
            nativeDestroy(mNative);
        throw exception;
    }

    public CharSequence get(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(mStrings == null) goto _L2; else goto _L1
_L1:
        Object obj = mStrings[i];
        if(obj == null) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return ((CharSequence) (obj));
_L2:
        if(mSparseStrings == null) goto _L6; else goto _L5
_L5:
        obj = (CharSequence)mSparseStrings.get(i);
        if(obj == null) goto _L4; else goto _L7
_L7:
        this;
        JVM INSTR monitorexit ;
        return ((CharSequence) (obj));
_L6:
        int j = nativeGetSize(mNative);
        if(!mUseSparse || j <= 250) goto _L9; else goto _L8
_L8:
        obj = JVM INSTR new #298 <Class SparseArray>;
        ((SparseArray) (obj)).SparseArray();
        mSparseStrings = ((SparseArray) (obj));
_L4:
        String s = nativeGetString(mNative, i);
        obj = s;
        int ai[] = nativeGetStyle(mNative, i);
        if(ai == null)
            break MISSING_BLOCK_LABEL_579;
        if(mStyleIDs == null)
        {
            obj = JVM INSTR new #9   <Class StringBlock$StyleIDs>;
            ((StyleIDs) (obj)).StyleIDs();
            mStyleIDs = ((StyleIDs) (obj));
        }
        j = 0;
_L13:
        if(j >= ai.length)
            break; /* Loop/switch isn't completed */
        int k = ai[j];
        if(k != StyleIDs._2D_get1(mStyleIDs) && k != StyleIDs._2D_get2(mStyleIDs)) goto _L11; else goto _L10
_L10:
        j += 3;
        if(true) goto _L13; else goto _L12
_L9:
        mStrings = new CharSequence[j];
          goto _L4
        obj;
        throw obj;
_L11:
        if(k == StyleIDs._2D_get10(mStyleIDs) || k == StyleIDs._2D_get9(mStyleIDs) || k == StyleIDs._2D_get0(mStyleIDs) || k == StyleIDs._2D_get5(mStyleIDs) || k == StyleIDs._2D_get7(mStyleIDs) || k == StyleIDs._2D_get8(mStyleIDs) || k == StyleIDs._2D_get6(mStyleIDs) || k == StyleIDs._2D_get3(mStyleIDs) || k == StyleIDs._2D_get4(mStyleIDs)) goto _L10; else goto _L14
_L14:
        obj = nativeGetString(mNative, k);
        if(!((String) (obj)).equals("b"))
            break MISSING_BLOCK_LABEL_336;
        StyleIDs._2D_set1(mStyleIDs, k);
          goto _L10
label0:
        {
            if(!((String) (obj)).equals("i"))
                break label0;
            StyleIDs._2D_set2(mStyleIDs, k);
        }
          goto _L10
label1:
        {
            if(!((String) (obj)).equals("u"))
                break label1;
            StyleIDs._2D_set10(mStyleIDs, k);
        }
          goto _L10
label2:
        {
            if(!((String) (obj)).equals("tt"))
                break label2;
            StyleIDs._2D_set9(mStyleIDs, k);
        }
          goto _L10
label3:
        {
            if(!((String) (obj)).equals("big"))
                break label3;
            StyleIDs._2D_set0(mStyleIDs, k);
        }
          goto _L10
label4:
        {
            if(!((String) (obj)).equals("small"))
                break label4;
            StyleIDs._2D_set5(mStyleIDs, k);
        }
          goto _L10
label5:
        {
            if(!((String) (obj)).equals("sup"))
                break label5;
            StyleIDs._2D_set8(mStyleIDs, k);
        }
          goto _L10
label6:
        {
            if(!((String) (obj)).equals("sub"))
                break label6;
            StyleIDs._2D_set7(mStyleIDs, k);
        }
          goto _L10
label7:
        {
            if(!((String) (obj)).equals("strike"))
                break label7;
            StyleIDs._2D_set6(mStyleIDs, k);
        }
          goto _L10
        if(!((String) (obj)).equals("li")) goto _L16; else goto _L15
_L15:
        StyleIDs._2D_set3(mStyleIDs, k);
          goto _L10
_L16:
        if(!((String) (obj)).equals("marquee")) goto _L10; else goto _L17
_L17:
        StyleIDs._2D_set4(mStyleIDs, k);
          goto _L10
_L12:
        obj = applyStyles(s, ai, mStyleIDs);
        if(mStrings == null)
            break MISSING_BLOCK_LABEL_597;
        mStrings[i] = ((CharSequence) (obj));
_L18:
        this;
        JVM INSTR monitorexit ;
        return ((CharSequence) (obj));
        mSparseStrings.put(i, obj);
          goto _L18
    }

    private static final String TAG = "AssetManager";
    private static final boolean localLOGV = false;
    private final long mNative;
    private final boolean mOwnsNative;
    private SparseArray mSparseStrings;
    private CharSequence mStrings[];
    StyleIDs mStyleIDs;
    private final boolean mUseSparse;
}
