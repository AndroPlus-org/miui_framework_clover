// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.*;
import android.text.method.TextKeyListener;
import android.text.style.AlignmentSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineBackgroundSpan;
import android.text.style.ParagraphStyle;
import android.text.style.ReplacementSpan;
import android.text.style.TabStopSpan;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.util.Arrays;

// Referenced classes of package android.text:
//            TextDirectionHeuristics, TextPaint, Spanned, TextUtils, 
//            TextLine, SpannableStringBuilder, MeasuredText, AndroidBidi, 
//            SpanSet, TextDirectionHeuristic, GetChars, SpannableString

public abstract class Layout
{
    public static final class Alignment extends Enum
    {

        public static Alignment valueOf(String s)
        {
            return (Alignment)Enum.valueOf(android/text/Layout$Alignment, s);
        }

        public static Alignment[] values()
        {
            return $VALUES;
        }

        private static final Alignment $VALUES[];
        public static final Alignment ALIGN_CENTER;
        public static final Alignment ALIGN_LEFT;
        public static final Alignment ALIGN_NORMAL;
        public static final Alignment ALIGN_OPPOSITE;
        public static final Alignment ALIGN_RIGHT;

        static 
        {
            ALIGN_NORMAL = new Alignment("ALIGN_NORMAL", 0);
            ALIGN_OPPOSITE = new Alignment("ALIGN_OPPOSITE", 1);
            ALIGN_CENTER = new Alignment("ALIGN_CENTER", 2);
            ALIGN_LEFT = new Alignment("ALIGN_LEFT", 3);
            ALIGN_RIGHT = new Alignment("ALIGN_RIGHT", 4);
            $VALUES = (new Alignment[] {
                ALIGN_NORMAL, ALIGN_OPPOSITE, ALIGN_CENTER, ALIGN_LEFT, ALIGN_RIGHT
            });
        }

        private Alignment(String s, int i)
        {
            super(s, i);
        }
    }

    public static class Directions
    {

        public int mDirections[];

        public Directions(int ai[])
        {
            mDirections = ai;
        }
    }

    static class Ellipsizer
        implements CharSequence, GetChars
    {

        public char charAt(int i)
        {
            char ac[] = TextUtils.obtain(1);
            getChars(i, i + 1, ac, 0);
            char c = ac[0];
            TextUtils.recycle(ac);
            return c;
        }

        public void getChars(int i, int j, char ac[], int k)
        {
            int l = mLayout.getLineForOffset(i);
            int i1 = mLayout.getLineForOffset(j);
            TextUtils.getChars(mText, i, j, ac, k);
            for(; l <= i1; l++)
                Layout._2D_wrap0(mLayout, i, j, l, ac, k, mMethod);

        }

        public int length()
        {
            return mText.length();
        }

        public CharSequence subSequence(int i, int j)
        {
            char ac[] = new char[j - i];
            getChars(i, j, ac, 0);
            return new String(ac);
        }

        public String toString()
        {
            char ac[] = new char[length()];
            getChars(0, length(), ac, 0);
            return new String(ac);
        }

        Layout mLayout;
        TextUtils.TruncateAt mMethod;
        CharSequence mText;
        int mWidth;

        public Ellipsizer(CharSequence charsequence)
        {
            mText = charsequence;
        }
    }

    static class SpannedEllipsizer extends Ellipsizer
        implements Spanned
    {

        public int getSpanEnd(Object obj)
        {
            return mSpanned.getSpanEnd(obj);
        }

        public int getSpanFlags(Object obj)
        {
            return mSpanned.getSpanFlags(obj);
        }

        public int getSpanStart(Object obj)
        {
            return mSpanned.getSpanStart(obj);
        }

        public Object[] getSpans(int i, int j, Class class1)
        {
            return mSpanned.getSpans(i, j, class1);
        }

        public int nextSpanTransition(int i, int j, Class class1)
        {
            return mSpanned.nextSpanTransition(i, j, class1);
        }

        public CharSequence subSequence(int i, int j)
        {
            char ac[] = new char[j - i];
            getChars(i, j, ac, 0);
            SpannableString spannablestring = new SpannableString(new String(ac));
            TextUtils.copySpansFrom(mSpanned, i, j, java/lang/Object, spannablestring, 0);
            return spannablestring;
        }

        private Spanned mSpanned;

        public SpannedEllipsizer(CharSequence charsequence)
        {
            super(charsequence);
            mSpanned = (Spanned)charsequence;
        }
    }

    static class TabStops
    {

        public static float nextDefaultStop(float f, int i)
        {
            return (float)((int)(((float)i + f) / (float)i) * i);
        }

        float nextTab(float f)
        {
            int i = mNumStops;
            if(i > 0)
            {
                int ai[] = mStops;
                for(int j = 0; j < i; j++)
                {
                    int k = ai[j];
                    if((float)k > f)
                        return (float)k;
                }

            }
            return nextDefaultStop(f, mIncrement);
        }

        void reset(int i, Object aobj[])
        {
            mIncrement = i;
            i = 0;
            if(aobj != null)
            {
                int ai[] = mStops;
                int j = aobj.length;
                int k = 0;
                i = 0;
                while(k < j) 
                {
                    Object obj = aobj[k];
                    int ai1[];
                    if(obj instanceof TabStopSpan)
                    {
                        int l;
                        if(ai == null)
                        {
                            ai1 = new int[10];
                        } else
                        {
                            ai1 = ai;
                            if(i == ai.length)
                            {
                                ai1 = new int[i * 2];
                                int i1 = 0;
                                while(i1 < i) 
                                {
                                    ai1[i1] = ai[i1];
                                    i1++;
                                }
                            }
                        }
                        l = i + 1;
                        ai1[i] = ((TabStopSpan)obj).getTabStop();
                        i = l;
                    } else
                    {
                        ai1 = ai;
                    }
                    k++;
                    ai = ai1;
                }
                if(i > 1)
                    Arrays.sort(ai, 0, i);
                if(ai != mStops)
                    mStops = ai;
            }
            mNumStops = i;
        }

        private int mIncrement;
        private int mNumStops;
        private int mStops[];

        TabStops(int i, Object aobj[])
        {
            reset(i, aobj);
        }
    }


    private static int[] _2D_getandroid_2D_text_2D_Layout$AlignmentSwitchesValues()
    {
        if(_2D_android_2D_text_2D_Layout$AlignmentSwitchesValues != null)
            return _2D_android_2D_text_2D_Layout$AlignmentSwitchesValues;
        int ai[] = new int[Alignment.values().length];
        try
        {
            ai[Alignment.ALIGN_CENTER.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Alignment.ALIGN_LEFT.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Alignment.ALIGN_NORMAL.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Alignment.ALIGN_OPPOSITE.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Alignment.ALIGN_RIGHT.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_text_2D_Layout$AlignmentSwitchesValues = ai;
        return ai;
    }

    static void _2D_wrap0(Layout layout, int i, int j, int k, char ac[], int l, TextUtils.TruncateAt truncateat)
    {
        layout.ellipsize(i, j, k, ac, l, truncateat);
    }

    protected Layout(CharSequence charsequence, TextPaint textpaint, int i, Alignment alignment, float f, float f1)
    {
        this(charsequence, textpaint, i, alignment, TextDirectionHeuristics.FIRSTSTRONG_LTR, f, f1);
    }

    protected Layout(CharSequence charsequence, TextPaint textpaint, int i, Alignment alignment, TextDirectionHeuristic textdirectionheuristic, float f, float f1)
    {
        mAlignment = Alignment.ALIGN_NORMAL;
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Layout: ").append(i).append(" < 0").toString());
        if(textpaint != null)
        {
            textpaint.bgColor = 0;
            textpaint.baselineShift = 0;
        }
        mText = charsequence;
        mPaint = textpaint;
        mWidth = i;
        mAlignment = alignment;
        mSpacingMult = f;
        mSpacingAdd = f1;
        mSpannedText = charsequence instanceof Spanned;
        mTextDir = textdirectionheuristic;
    }

    private void addSelection(int i, int j, int k, int l, int i1, Path path)
    {
        int j1 = getLineStart(i);
        int k1 = getLineEnd(i);
        Directions directions = getLineDirections(i);
        int i2 = k1;
        if(k1 > j1)
        {
            i2 = k1;
            if(mText.charAt(k1 - 1) == '\n')
                i2 = k1 - 1;
        }
        for(int l1 = 0; l1 < directions.mDirections.length; l1 += 2)
        {
            int j2 = j1 + directions.mDirections[l1];
            int k2 = j2 + (directions.mDirections[l1 + 1] & 0x3ffffff);
            int l2 = k2;
            if(k2 > i2)
                l2 = i2;
            if(j > l2 || k < j2)
                continue;
            k2 = Math.max(j, j2);
            l2 = Math.min(k, l2);
            if(k2 != l2)
            {
                float f = getHorizontal(k2, false, i, false);
                float f1 = getHorizontal(l2, true, i, false);
                float f2 = Math.min(f, f1);
                f1 = Math.max(f, f1);
                path.addRect(f2, l, f1, i1, android.graphics.Path.Direction.CW);
            }
        }

    }

    private void ellipsize(int i, int j, int k, char ac[], int l, TextUtils.TruncateAt truncateat)
    {
        int i1 = getEllipsisCount(k);
        if(i1 == 0)
            return;
        int j1 = getEllipsisStart(k);
        int k1 = getLineStart(k);
        k = j1;
        while(k < j1 + i1) 
        {
            int l1;
            int i2;
            if(k == j1)
                l1 = getEllipsisChar(truncateat);
            else
                l1 = 65279;
            i2 = k + k1;
            if(i2 >= i && i2 < j)
                ac[(l + i2) - i] = (char)l1;
            k++;
        }
    }

    public static float getDesiredWidth(CharSequence charsequence, int i, int j, TextPaint textpaint)
    {
        return getDesiredWidth(charsequence, i, j, textpaint, TextDirectionHeuristics.FIRSTSTRONG_LTR);
    }

    public static float getDesiredWidth(CharSequence charsequence, int i, int j, TextPaint textpaint, TextDirectionHeuristic textdirectionheuristic)
    {
        float f;
        float f2;
        for(f = 0.0F; i <= j; f = f2)
        {
            int k = TextUtils.indexOf(charsequence, '\n', i, j);
            int l = k;
            if(k < 0)
                l = j;
            float f1 = measurePara(textpaint, charsequence, i, l, textdirectionheuristic);
            f2 = f;
            if(f1 > f)
                f2 = f1;
            i = l + 1;
        }

        return f;
    }

    public static float getDesiredWidth(CharSequence charsequence, TextPaint textpaint)
    {
        return getDesiredWidth(charsequence, 0, charsequence.length(), textpaint);
    }

    private char getEllipsisChar(TextUtils.TruncateAt truncateat)
    {
        char c2;
        if(truncateat == TextUtils.TruncateAt.END_SMALL)
        {
            char c = TextUtils.ELLIPSIS_TWO_DOTS[0];
            c2 = c;
        } else
        {
            char c1 = TextUtils.ELLIPSIS_NORMAL[0];
            c2 = c1;
        }
        return c2;
    }

    private float getHorizontal(int i, boolean flag)
    {
        float f;
        if(flag)
            f = getPrimaryHorizontal(i);
        else
            f = getSecondaryHorizontal(i);
        return f;
    }

    private float getHorizontal(int i, boolean flag, int j, boolean flag1)
    {
        int k = getLineStart(j);
        int l = getLineEnd(j);
        int i1 = getParagraphDirection(j);
        boolean flag2 = getLineContainsTab(j);
        Directions directions = getLineDirections(j);
        TextLine textline = null;
        TabStops tabstops = textline;
        if(flag2)
        {
            tabstops = textline;
            if(mText instanceof Spanned)
            {
                TabStopSpan atabstopspan[] = (TabStopSpan[])getParagraphSpans((Spanned)mText, k, l, android/text/style/TabStopSpan);
                tabstops = textline;
                if(atabstopspan.length > 0)
                    tabstops = new TabStops(20, atabstopspan);
            }
        }
        textline = TextLine.obtain();
        textline.set(mPaint, mText, k, l, i1, directions, flag2, tabstops);
        float f = textline.measure(i - k, flag, null);
        TextLine.recycle(textline);
        float f1 = f;
        if(flag1)
        {
            f1 = f;
            if(f > (float)mWidth)
                f1 = mWidth;
        }
        return (float)getLineStartPos(j, getParagraphLeft(j), getParagraphRight(j)) + f1;
    }

    private float getHorizontal(int i, boolean flag, boolean flag1)
    {
        return getHorizontal(i, flag, getLineForOffset(i), flag1);
    }

    private float getJustifyWidth(int i)
    {
        Alignment alignment;
        int j;
        boolean flag;
        int k;
        int l;
        ParagraphStyle aparagraphstyle[];
        Object obj;
        int i1;
        Object obj1;
        alignment = mAlignment;
        j = 0;
        flag = false;
        k = mWidth;
        l = getParagraphDirection(i);
        aparagraphstyle = NO_PARA_SPANS;
        obj = alignment;
        i1 = k;
        obj1 = aparagraphstyle;
        if(!mSpannedText) goto _L2; else goto _L1
_L1:
        int j1;
        boolean flag2;
        obj1 = (Spanned)mText;
        j1 = getLineStart(i);
        boolean flag1;
        Alignment alignment1;
        int k1;
        int l1;
        if(j1 == 0 || mText.charAt(j1 - 1) == '\n')
            flag1 = true;
        else
            flag1 = false;
        alignment1 = alignment;
        if(!flag1) goto _L4; else goto _L3
_L3:
        obj = (ParagraphStyle[])getParagraphSpans(((Spanned) (obj1)), j1, ((Spanned) (obj1)).nextSpanTransition(j1, mText.length(), android/text/style/ParagraphStyle), android/text/style/ParagraphStyle);
        j1 = obj.length - 1;
_L15:
        alignment1 = alignment;
        aparagraphstyle = ((ParagraphStyle []) (obj));
        if(j1 < 0) goto _L4; else goto _L5
_L5:
        if(!(obj[j1] instanceof AlignmentSpan)) goto _L7; else goto _L6
_L6:
        alignment1 = ((AlignmentSpan)obj[j1]).getAlignment();
        aparagraphstyle = ((ParagraphStyle []) (obj));
_L4:
        k1 = aparagraphstyle.length;
        j1 = 0;
_L13:
        flag2 = flag1;
        if(j1 >= k1) goto _L9; else goto _L8
_L8:
        if(!(aparagraphstyle[j1] instanceof android.text.style.LeadingMarginSpan.LeadingMarginSpan2)) goto _L11; else goto _L10
_L10:
        i1 = ((android.text.style.LeadingMarginSpan.LeadingMarginSpan2)aparagraphstyle[j1]).getLeadingMarginLineCount();
        if(i >= getLineForOffset(((Spanned) (obj1)).getSpanStart(aparagraphstyle[j1])) + i1) goto _L11; else goto _L12
_L12:
        flag2 = true;
_L9:
        l1 = 0;
        j1 = ((flag) ? 1 : 0);
        do
        {
            j = j1;
            obj = alignment1;
            i1 = k;
            obj1 = aparagraphstyle;
            if(l1 >= k1)
                break;
            i1 = j1;
            j = k;
            if(aparagraphstyle[l1] instanceof LeadingMarginSpan)
            {
                obj = (LeadingMarginSpan)aparagraphstyle[l1];
                if(l == -1)
                {
                    j = k - ((LeadingMarginSpan) (obj)).getLeadingMargin(flag2);
                    i1 = j1;
                } else
                {
                    i1 = j1 + ((LeadingMarginSpan) (obj)).getLeadingMargin(flag2);
                    j = k;
                }
            }
            l1++;
            j1 = i1;
            k = j;
        } while(true);
        break; /* Loop/switch isn't completed */
_L7:
        j1--;
        continue; /* Loop/switch isn't completed */
_L11:
        j1++;
        if(true) goto _L13; else goto _L2
_L2:
        if(getLineContainsTab(i))
            new TabStops(20, ((Object []) (obj1)));
        Object obj2;
        if(obj == Alignment.ALIGN_LEFT)
        {
            if(l == 1)
                obj2 = Alignment.ALIGN_NORMAL;
            else
                obj2 = Alignment.ALIGN_OPPOSITE;
        } else
        if(obj == Alignment.ALIGN_RIGHT)
        {
            if(l == 1)
                obj2 = Alignment.ALIGN_OPPOSITE;
            else
                obj2 = Alignment.ALIGN_NORMAL;
        } else
        {
            obj2 = obj;
        }
        if(obj2 == Alignment.ALIGN_NORMAL)
        {
            if(l == 1)
                i = getIndentAdjust(i, Alignment.ALIGN_LEFT);
            else
                i = -getIndentAdjust(i, Alignment.ALIGN_RIGHT);
        } else
        if(obj2 == Alignment.ALIGN_OPPOSITE)
        {
            if(l == 1)
                i = -getIndentAdjust(i, Alignment.ALIGN_RIGHT);
            else
                i = getIndentAdjust(i, Alignment.ALIGN_LEFT);
        } else
        {
            i = getIndentAdjust(i, Alignment.ALIGN_CENTER);
        }
        return (float)(i1 - j - i);
        if(true) goto _L15; else goto _L14
_L14:
    }

    private float getLineExtent(int i, TabStops tabstops, boolean flag)
    {
        int j = getLineStart(i);
        int k;
        Directions directions;
        int l;
        TextLine textline;
        float f;
        if(flag)
            k = getLineEnd(i);
        else
            k = getLineVisibleEnd(i);
        flag = getLineContainsTab(i);
        directions = getLineDirections(i);
        l = getParagraphDirection(i);
        textline = TextLine.obtain();
        mPaint.setHyphenEdit(getHyphen(i));
        textline.set(mPaint, mText, j, k, l, directions, flag, tabstops);
        if(isJustificationRequired(i))
            textline.justify(getJustifyWidth(i));
        f = textline.metrics(null);
        mPaint.setHyphenEdit(0);
        TextLine.recycle(textline);
        return f;
    }

    private float getLineExtent(int i, boolean flag)
    {
        int j = getLineStart(i);
        int k;
        TextLine textline;
        TabStops tabstops;
        Directions directions;
        if(flag)
            k = getLineEnd(i);
        else
            k = getLineVisibleEnd(i);
        flag = getLineContainsTab(i);
        textline = null;
        tabstops = textline;
        if(flag)
        {
            tabstops = textline;
            if(mText instanceof Spanned)
            {
                TabStopSpan atabstopspan[] = (TabStopSpan[])getParagraphSpans((Spanned)mText, j, k, android/text/style/TabStopSpan);
                tabstops = textline;
                if(atabstopspan.length > 0)
                    tabstops = new TabStops(20, atabstopspan);
            }
        }
        directions = getLineDirections(i);
        if(directions == null)
            return 0.0F;
        int l = getParagraphDirection(i);
        textline = TextLine.obtain();
        mPaint.setHyphenEdit(getHyphen(i));
        textline.set(mPaint, mText, j, k, l, directions, flag, tabstops);
        if(isJustificationRequired(i))
            textline.justify(getJustifyWidth(i));
        float f = textline.metrics(null);
        mPaint.setHyphenEdit(0);
        TextLine.recycle(textline);
        return f;
    }

    private int getLineStartPos(int i, int j, int k)
    {
        Alignment alignment = getParagraphAlignment(i);
        int l = getParagraphDirection(i);
        Alignment alignment1;
        if(alignment == Alignment.ALIGN_LEFT)
        {
            if(l == 1)
                alignment1 = Alignment.ALIGN_NORMAL;
            else
                alignment1 = Alignment.ALIGN_OPPOSITE;
        } else
        {
            alignment1 = alignment;
            if(alignment == Alignment.ALIGN_RIGHT)
                if(l == 1)
                    alignment1 = Alignment.ALIGN_OPPOSITE;
                else
                    alignment1 = Alignment.ALIGN_NORMAL;
        }
        if(alignment1 == Alignment.ALIGN_NORMAL)
        {
            if(l == 1)
                i = j + getIndentAdjust(i, Alignment.ALIGN_LEFT);
            else
                i = k + getIndentAdjust(i, Alignment.ALIGN_RIGHT);
        } else
        {
            Object obj1 = null;
            Object obj = obj1;
            if(mSpannedText)
            {
                obj = obj1;
                if(getLineContainsTab(i))
                {
                    obj = (Spanned)mText;
                    int i1 = getLineStart(i);
                    TabStopSpan atabstopspan[] = (TabStopSpan[])getParagraphSpans(((Spanned) (obj)), i1, ((Spanned) (obj)).nextSpanTransition(i1, ((Spanned) (obj)).length(), android/text/style/TabStopSpan), android/text/style/TabStopSpan);
                    obj = obj1;
                    if(atabstopspan.length > 0)
                        obj = new TabStops(20, atabstopspan);
                }
            }
            int j1 = (int)getLineExtent(i, ((TabStops) (obj)), false);
            if(alignment1 == Alignment.ALIGN_OPPOSITE)
            {
                if(l == 1)
                    i = (k - j1) + getIndentAdjust(i, Alignment.ALIGN_RIGHT);
                else
                    i = (j - j1) + getIndentAdjust(i, Alignment.ALIGN_LEFT);
            } else
            {
                i = (j + k) - (j1 & -2) >> getIndentAdjust(i, Alignment.ALIGN_CENTER) + 1;
            }
        }
        return i;
    }

    private int getLineVisibleEnd(int i, int j, int k)
    {
        CharSequence charsequence;
        int l;
        charsequence = mText;
        l = k;
        if(i == getLineCount() - 1)
            return k;
_L4:
        char c;
        if(l <= j)
            break; /* Loop/switch isn't completed */
        c = charsequence.charAt(l - 1);
        if(c == '\n')
            return l - 1;
        if(TextLine.isLineEndSpace(c)) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        l--;
        if(true) goto _L4; else goto _L3
_L3:
        return l;
    }

    private int getOffsetAtStartOf(int i)
    {
        if(i == 0)
            return 0;
        CharSequence charsequence = mText;
        int j = charsequence.charAt(i);
        int k = i;
        if(j >= '\uDC00')
        {
            k = i;
            if(j <= '\uDFFF')
            {
                j = charsequence.charAt(i - 1);
                k = i;
                if(j >= '\uD800')
                {
                    k = i;
                    if(j <= '\uDBFF')
                        k = i - 1;
                }
            }
        }
        j = k;
        if(mSpannedText)
        {
            ReplacementSpan areplacementspan[] = (ReplacementSpan[])((Spanned)charsequence).getSpans(k, k, android/text/style/ReplacementSpan);
            i = 0;
            do
            {
                j = k;
                if(i >= areplacementspan.length)
                    break;
                int l = ((Spanned)charsequence).getSpanStart(areplacementspan[i]);
                int i1 = ((Spanned)charsequence).getSpanEnd(areplacementspan[i]);
                j = k;
                if(l < k)
                {
                    j = k;
                    if(i1 > k)
                        j = l;
                }
                i++;
                k = j;
            } while(true);
        }
        return j;
    }

    private int getOffsetToLeftRightOf(int i, boolean flag)
    {
        int j;
        int k;
        int j1;
        int k1;
        j = getLineForOffset(i);
        k = getLineStart(j);
        int l = getLineEnd(j);
        int i1 = getParagraphDirection(j);
        j1 = 0;
        boolean flag1;
        int l1;
        int i2;
        Directions directions;
        TextLine textline;
        if(i1 == -1)
            flag1 = true;
        else
            flag1 = false;
        if(flag == flag1)
            k1 = 1;
        else
            k1 = 0;
        if(k1 == 0) goto _L2; else goto _L1
_L1:
        k1 = j;
        if(i != l) goto _L4; else goto _L3
_L3:
        if(j >= getLineCount() - 1) goto _L6; else goto _L5
_L5:
        j1 = 1;
        k1 = j + 1;
_L4:
        j = i1;
        flag1 = flag;
        if(j1 != 0)
        {
            l1 = getLineStart(k1);
            j1 = getLineEnd(k1);
            i2 = getParagraphDirection(k1);
            k = l1;
            l = j1;
            j = i1;
            flag1 = flag;
            if(i2 != i1)
            {
                flag1 = flag ^ true;
                j = i2;
                l = j1;
                k = l1;
            }
        }
        directions = getLineDirections(k1);
        textline = TextLine.obtain();
        textline.set(mPaint, mText, k, l, j, directions, false, null);
        i = textline.getOffsetToLeftRightOf(i - k, flag1);
        TextLine.recycle(textline);
        return k + i;
_L6:
        return i;
_L2:
        k1 = j;
        if(i == k)
            if(j > 0)
            {
                j1 = 1;
                k1 = j - 1;
            } else
            {
                return i;
            }
        if(true) goto _L4; else goto _L7
_L7:
    }

    private int getParagraphLeadingMargin(int i)
    {
        if(!mSpannedText)
            return 0;
        Spanned spanned = (Spanned)mText;
        int j = getLineStart(i);
        LeadingMarginSpan aleadingmarginspan[] = (LeadingMarginSpan[])getParagraphSpans(spanned, j, spanned.nextSpanTransition(j, getLineEnd(i), android/text/style/LeadingMarginSpan), android/text/style/LeadingMarginSpan);
        if(aleadingmarginspan.length == 0)
            return 0;
        boolean flag = false;
        boolean flag1;
        boolean flag2;
        if(j != 0)
        {
            if(spanned.charAt(j - 1) == '\n')
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        j = 0;
        flag2 = flag1;
        while(j < aleadingmarginspan.length) 
        {
            flag1 = flag2;
            if(aleadingmarginspan[j] instanceof android.text.style.LeadingMarginSpan.LeadingMarginSpan2)
            {
                boolean flag3;
                if(i < getLineForOffset(spanned.getSpanStart(aleadingmarginspan[j])) + ((android.text.style.LeadingMarginSpan.LeadingMarginSpan2)aleadingmarginspan[j]).getLeadingMarginLineCount())
                    flag3 = true;
                else
                    flag3 = false;
                flag1 = flag2 | flag3;
            }
            j++;
            flag2 = flag1;
        }
        j = 0;
        i = ((flag) ? 1 : 0);
        for(; j < aleadingmarginspan.length; j++)
            i += aleadingmarginspan[j].getLeadingMargin(flag2);

        return i;
    }

    static Object[] getParagraphSpans(Spanned spanned, int i, int j, Class class1)
    {
        if(i == j && i > 0)
            return ArrayUtils.emptyArray(class1);
        if(spanned instanceof SpannableStringBuilder)
            return ((SpannableStringBuilder)spanned).getSpans(i, j, class1, false);
        else
            return spanned.getSpans(i, j, class1);
    }

    private boolean isJustificationRequired(int i)
    {
        boolean flag = false;
        if(mJustificationMode == 0)
            return false;
        i = getLineEnd(i);
        boolean flag1 = flag;
        if(i < mText.length())
        {
            flag1 = flag;
            if(mText.charAt(i - 1) != '\n')
                flag1 = true;
        }
        return flag1;
    }

    static float measurePara(TextPaint textpaint, CharSequence charsequence, int i, int j, TextDirectionHeuristic textdirectionheuristic)
    {
        MeasuredText measuredtext;
        TextLine textline;
        measuredtext = MeasuredText.obtain();
        textline = TextLine.obtain();
        measuredtext.setPara(charsequence, i, j, textdirectionheuristic, null);
        if(!measuredtext.mEasy) goto _L2; else goto _L1
_L1:
        textdirectionheuristic = DIRS_ALL_LEFT_TO_RIGHT;
        int k = 1;
_L6:
        Object aobj[];
        int l;
        aobj = measuredtext.mChars;
        l = measuredtext.mLen;
        boolean flag;
        Object obj;
        int i1;
        int j1;
        flag = false;
        obj = null;
        i1 = 0;
        j1 = 0;
        LeadingMarginSpan aleadingmarginspan[];
        if(!(charsequence instanceof Spanned))
            break; /* Loop/switch isn't completed */
        aleadingmarginspan = (LeadingMarginSpan[])getParagraphSpans((Spanned)charsequence, i, j, android/text/style/LeadingMarginSpan);
        int k1 = 0;
        int l1 = aleadingmarginspan.length;
_L4:
        i1 = j1;
        if(k1 >= l1)
            break; /* Loop/switch isn't completed */
        j1 += aleadingmarginspan[k1].getLeadingMargin(true);
        k1++;
        if(true) goto _L4; else goto _L3
_L3:
        break; /* Loop/switch isn't completed */
_L2:
        textdirectionheuristic = AndroidBidi.directions(measuredtext.mDir, measuredtext.mLevels, 0, measuredtext.mChars, 0, measuredtext.mLen);
        k = measuredtext.mDir;
        if(true) goto _L6; else goto _L5
_L5:
        j1 = 0;
_L8:
        Object obj1;
        boolean flag1;
        flag1 = flag;
        obj1 = obj;
        if(j1 >= l)
            break MISSING_BLOCK_LABEL_275;
        if(aobj[j1] != '\t')
            break MISSING_BLOCK_LABEL_326;
        flag = true;
        flag1 = flag;
        obj1 = obj;
        if(!(charsequence instanceof Spanned))
            break MISSING_BLOCK_LABEL_275;
        obj1 = (Spanned)charsequence;
        aobj = (TabStopSpan[])getParagraphSpans(((Spanned) (obj1)), i, ((Spanned) (obj1)).nextSpanTransition(i, j, android/text/style/TabStopSpan), android/text/style/TabStopSpan);
        flag1 = flag;
        obj1 = obj;
        if(aobj.length <= 0)
            break MISSING_BLOCK_LABEL_275;
        obj1 = JVM INSTR new #18  <Class Layout$TabStops>;
        ((TabStops) (obj1)).TabStops(20, aobj);
        flag1 = flag;
        textline.set(textpaint, charsequence, i, j, k, textdirectionheuristic, flag1, ((TabStops) (obj1)));
        float f = i1;
        float f1 = Math.abs(textline.metrics(null));
        TextLine.recycle(textline);
        MeasuredText.recycle(measuredtext);
        return f + f1;
        j1++;
        if(true) goto _L8; else goto _L7
_L7:
        textpaint;
        TextLine.recycle(textline);
        MeasuredText.recycle(measuredtext);
        throw textpaint;
    }

    static float nextTab(CharSequence charsequence, int i, int j, float f, Object aobj[])
    {
        float f1 = 3.402823E+038F;
        boolean flag = false;
        if(charsequence instanceof Spanned)
        {
            Object aobj1[] = aobj;
            if(aobj == null)
            {
                aobj1 = getParagraphSpans((Spanned)charsequence, i, j, android/text/style/TabStopSpan);
                flag = true;
            }
            i = 0;
            while(i < aobj1.length) 
            {
                float f2;
                if(!flag && !(aobj1[i] instanceof TabStopSpan))
                {
                    f2 = f1;
                } else
                {
                    j = ((TabStopSpan)aobj1[i]).getTabStop();
                    f2 = f1;
                    if((float)j < f1)
                    {
                        f2 = f1;
                        if((float)j > f)
                            f2 = j;
                    }
                }
                i++;
                f1 = f2;
            }
            if(f1 != 3.402823E+038F)
                return f1;
        }
        return (float)((int)((f + 20F) / 20F) * 20);
    }

    private boolean primaryIsTrailingPrevious(int i)
    {
        boolean flag;
        int j;
        int l;
        int i1;
        int ai[];
        byte byte0;
        int j1;
        flag = true;
        j = getLineForOffset(i);
        l = getLineStart(j);
        i1 = getLineEnd(j);
        ai = getLineDirections(j).mDirections;
        byte0 = -1;
        j1 = 0;
_L7:
        int k1 = byte0;
        if(j1 >= ai.length) goto _L2; else goto _L1
_L1:
        int i2;
        i2 = l + ai[j1];
        int k2 = i2 + (ai[j1 + 1] & 0x3ffffff);
        k1 = k2;
        if(k2 > i1)
            k1 = i1;
        if(i < i2 || i >= k1) goto _L4; else goto _L3
_L3:
        if(i > i2)
            return false;
        k1 = ai[j1 + 1] >>> 26 & 0x3f;
_L2:
        j1 = k1;
        if(k1 == -1)
            if(getParagraphDirection(j) == 1)
                j1 = 0;
            else
                j1 = 1;
        byte0 = -1;
        if(i != l) goto _L6; else goto _L5
_L5:
        if(getParagraphDirection(j) == 1)
            i = 0;
        else
            i = 1;
_L8:
        int k;
        int l1;
        int j2;
        int l2;
        if(i >= j1)
            flag = false;
        return flag;
_L4:
        j1 += 2;
          goto _L7
_L6:
        k = i - 1;
        l1 = 0;
_L9:
        i = byte0;
        if(l1 < ai.length)
        {
label0:
            {
                j2 = l + ai[l1];
                l2 = j2 + (ai[l1 + 1] & 0x3ffffff);
                i = l2;
                if(l2 > i1)
                    i = i1;
                if(k < j2 || k >= i)
                    break label0;
                i = ai[l1 + 1] >>> 26 & 0x3f;
            }
        }
          goto _L8
        l1 += 2;
          goto _L9
    }

    public void draw(Canvas canvas)
    {
        draw(canvas, null, null, 0);
    }

    public void draw(Canvas canvas, Path path, Paint paint, int i)
    {
        long l = getLineRangeForDraw(canvas);
        int j = TextUtils.unpackRangeStartFromLong(l);
        int k = TextUtils.unpackRangeEndFromLong(l);
        if(k < 0)
        {
            return;
        } else
        {
            drawBackground(canvas, path, paint, i, j, k);
            drawText(canvas, j, k);
            return;
        }
    }

    public void drawBackground(Canvas canvas, Path path, Paint paint, int i, int j, int k)
    {
label0:
        {
            if(!mSpannedText)
                break label0;
            if(mLineBackgroundSpans == null)
                mLineBackgroundSpans = new SpanSet(android/text/style/LineBackgroundSpan);
            Spanned spanned = (Spanned)mText;
            int l = spanned.length();
            mLineBackgroundSpans.init(spanned, 0, l);
            if(mLineBackgroundSpans.numberOfSpans > 0)
            {
                int i1 = getLineTop(j);
                int j1 = getLineStart(j);
                ParagraphStyle aparagraphstyle[] = NO_PARA_SPANS;
                boolean flag = false;
                TextPaint textpaint = mPaint;
                int l1 = 0;
                int i2 = mWidth;
                int j2 = j;
                j = ((flag) ? 1 : 0);
label1:
                do
                {
                    int k1;
                    int k2;
                    int l2;
                    int i3;
                    int j3;
                    int k3;
                    int l3;
                    ParagraphStyle aparagraphstyle1[];
label2:
                    {
                        k2 = j1;
                        l2 = i1;
                        if(j2 > k)
                            break label1;
                        i3 = getLineStart(j2 + 1);
                        j1 = i3;
                        j3 = getLineTop(j2 + 1);
                        k1 = j3;
                        k3 = getLineDescent(j2);
                        l3 = l1;
                        aparagraphstyle1 = aparagraphstyle;
                        if(k2 < l1)
                            break label2;
                        int i4 = mLineBackgroundSpans.getNextTransition(k2, l);
                        j = 0;
                        i1 = 0;
                        if(k2 == i3)
                        {
                            l3 = i4;
                            aparagraphstyle1 = aparagraphstyle;
                            if(k2 != 0)
                                break label2;
                        }
                        l1 = 0;
                        do
                        {
                            l3 = i4;
                            aparagraphstyle1 = aparagraphstyle;
                            j = i1;
                            if(l1 >= mLineBackgroundSpans.numberOfSpans)
                                break;
                            aparagraphstyle1 = aparagraphstyle;
                            j = i1;
                            if(mLineBackgroundSpans.spanStarts[l1] < i3)
                                if(mLineBackgroundSpans.spanEnds[l1] <= k2)
                                {
                                    j = i1;
                                    aparagraphstyle1 = aparagraphstyle;
                                } else
                                {
                                    aparagraphstyle1 = (ParagraphStyle[])GrowingArrayUtils.append(aparagraphstyle, i1, ((LineBackgroundSpan[])mLineBackgroundSpans.spans)[l1]);
                                    j = i1 + 1;
                                }
                            l1++;
                            aparagraphstyle = aparagraphstyle1;
                            i1 = j;
                        } while(true);
                    }
                    for(i1 = 0; i1 < j; i1++)
                        ((LineBackgroundSpan)aparagraphstyle1[i1]).drawBackground(canvas, textpaint, 0, i2, l2, j3 - k3, j3, spanned, k2, i3, j2);

                    j2++;
                    i1 = k1;
                    l1 = l3;
                    aparagraphstyle = aparagraphstyle1;
                } while(true);
            }
            mLineBackgroundSpans.recycle();
        }
        if(path != null)
        {
            if(i != 0)
                canvas.translate(0.0F, i);
            canvas.drawPath(path, paint);
            if(i != 0)
                canvas.translate(0.0F, -i);
        }
    }

    public void drawText(Canvas canvas, int i, int j)
    {
        int k;
        int l;
        Object obj;
        int i1;
        TextPaint textpaint;
        CharSequence charsequence;
        Object obj1;
        int j1;
        TextLine textline;
        int k1;
        Object obj2;
        k = getLineTop(i);
        l = getLineStart(i);
        obj = NO_PARA_SPANS;
        i1 = 0;
        textpaint = mPaint;
        charsequence = mText;
        obj1 = mAlignment;
        j1 = 0;
        textline = TextLine.obtain();
        k1 = i;
        obj2 = null;
_L22:
        int l1;
        int i2;
        l1 = l;
        i2 = k;
        if(k1 > j) goto _L2; else goto _L1
_L1:
        int j2;
        boolean flag;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        boolean flag1;
        int i4;
        int j4;
        Object obj3;
        int k4;
        ParagraphStyle aparagraphstyle[];
        j2 = getLineStart(k1 + 1);
        flag = isJustificationRequired(k1);
        k2 = getLineVisibleEnd(k1, l1, j2);
        l2 = getLineTop(k1 + 1);
        i3 = l2;
        j3 = l2 - getLineDescent(k1);
        k3 = getParagraphDirection(k1);
        l3 = 0;
        flag1 = false;
        i4 = mWidth;
        j4 = i4;
        obj3 = obj1;
        k4 = i1;
        aparagraphstyle = ((ParagraphStyle []) (obj));
        l = j1;
        if(!mSpannedText) goto _L4; else goto _L3
_L3:
        boolean flag2;
        boolean flag5;
        obj3 = (Spanned)charsequence;
        l = charsequence.length();
        Alignment alignment;
        int l4;
        ParagraphStyle aparagraphstyle1[];
        int j5;
        boolean flag4;
        int k5;
        if(l1 == 0 || charsequence.charAt(l1 - 1) == '\n')
            flag2 = true;
        else
            flag2 = false;
        alignment = ((Alignment) (obj1));
        l4 = i1;
        aparagraphstyle1 = ((ParagraphStyle []) (obj));
        k = j1;
        if(l1 < i1) goto _L6; else goto _L5
_L5:
        if(k1 == i) goto _L8; else goto _L7
_L7:
        alignment = ((Alignment) (obj1));
        l4 = i1;
        aparagraphstyle1 = ((ParagraphStyle []) (obj));
        k = j1;
        if(!flag2) goto _L6; else goto _L8
_L8:
        l4 = ((Spanned) (obj3)).nextSpanTransition(l1, l, android/text/style/ParagraphStyle);
        aparagraphstyle1 = (ParagraphStyle[])getParagraphSpans(((Spanned) (obj3)), l1, l4, android/text/style/ParagraphStyle);
        obj = mAlignment;
        j1 = aparagraphstyle1.length - 1;
_L20:
        alignment = ((Alignment) (obj));
        if(j1 < 0) goto _L10; else goto _L9
_L9:
        if(!(aparagraphstyle1[j1] instanceof AlignmentSpan)) goto _L12; else goto _L11
_L11:
        alignment = ((AlignmentSpan)aparagraphstyle1[j1]).getAlignment();
_L10:
        k = 0;
_L6:
        j5 = aparagraphstyle1.length;
        flag4 = flag2;
        j1 = 0;
_L19:
        flag5 = flag4;
        if(j1 >= j5) goto _L14; else goto _L13
_L13:
        if(!(aparagraphstyle1[j1] instanceof android.text.style.LeadingMarginSpan.LeadingMarginSpan2)) goto _L16; else goto _L15
_L15:
        i1 = ((android.text.style.LeadingMarginSpan.LeadingMarginSpan2)aparagraphstyle1[j1]).getLeadingMarginLineCount();
        if(k1 >= getLineForOffset(((Spanned) (obj3)).getSpanStart(aparagraphstyle1[j1])) + i1) goto _L16; else goto _L17
_L17:
        flag5 = true;
_L14:
        k5 = 0;
        j1 = ((flag1) ? 1 : 0);
        i1 = i4;
        do
        {
            j4 = i1;
            l3 = j1;
            obj3 = alignment;
            k4 = l4;
            aparagraphstyle = aparagraphstyle1;
            l = k;
            if(k5 >= j5)
                break;
            l = i1;
            k4 = j1;
            if(aparagraphstyle1[k5] instanceof LeadingMarginSpan)
            {
                obj = (LeadingMarginSpan)aparagraphstyle1[k5];
                if(k3 == -1)
                {
                    ((LeadingMarginSpan) (obj)).drawLeadingMargin(canvas, textpaint, i1, k3, i2, j3, l2, charsequence, l1, k2, flag2, this);
                    l = i1 - ((LeadingMarginSpan) (obj)).getLeadingMargin(flag5);
                    k4 = j1;
                } else
                {
                    ((LeadingMarginSpan) (obj)).drawLeadingMargin(canvas, textpaint, j1, k3, i2, j3, l2, charsequence, l1, k2, flag2, this);
                    k4 = j1 + ((LeadingMarginSpan) (obj)).getLeadingMargin(flag5);
                    l = i1;
                }
            }
            k5++;
            i1 = l;
            j1 = k4;
        } while(true);
        break; /* Loop/switch isn't completed */
_L12:
        j1--;
          goto _L18
_L16:
        j1++;
        if(true) goto _L19; else goto _L4
_L4:
        boolean flag3 = getLineContainsTab(k1);
        int i5;
        Object obj4;
        if(flag3)
        {
            if((l ^ 1) != 0)
            {
                if(obj2 == null)
                {
                    obj = new TabStops(20, aparagraphstyle);
                } else
                {
                    ((TabStops) (obj2)).reset(20, aparagraphstyle);
                    obj = obj2;
                }
                i5 = 1;
            } else
            {
                obj = obj2;
                i5 = l;
            }
        } else
        {
            obj = obj2;
            i5 = l;
        }
        obj4 = obj3;
        if(obj4 == Alignment.ALIGN_LEFT)
        {
            if(k3 == 1)
                obj2 = Alignment.ALIGN_NORMAL;
            else
                obj2 = Alignment.ALIGN_OPPOSITE;
        } else
        {
            obj2 = obj4;
            if(obj4 == Alignment.ALIGN_RIGHT)
                if(k3 == 1)
                    obj2 = Alignment.ALIGN_OPPOSITE;
                else
                    obj2 = Alignment.ALIGN_NORMAL;
        }
        if(obj2 == Alignment.ALIGN_NORMAL)
        {
            if(k3 == 1)
            {
                j1 = getIndentAdjust(k1, Alignment.ALIGN_LEFT);
                k = l3 + j1;
            } else
            {
                j1 = -getIndentAdjust(k1, Alignment.ALIGN_RIGHT);
                k = j4 - j1;
            }
        } else
        {
            k = (int)getLineExtent(k1, ((TabStops) (obj)), false);
            if(obj2 == Alignment.ALIGN_OPPOSITE)
            {
                if(k3 == 1)
                {
                    j1 = -getIndentAdjust(k1, Alignment.ALIGN_RIGHT);
                    k = j4 - k - j1;
                } else
                {
                    j1 = getIndentAdjust(k1, Alignment.ALIGN_LEFT);
                    k = (l3 - k) + j1;
                }
            } else
            {
                j1 = getIndentAdjust(k1, Alignment.ALIGN_CENTER);
                k = ((j4 + l3) - (k & -2) >> 1) + j1;
            }
        }
        textpaint.setHyphenEdit(getHyphen(k1));
        obj2 = getLineDirections(k1);
        if(obj2 == DIRS_ALL_LEFT_TO_RIGHT && mSpannedText ^ true && flag3 ^ true && flag ^ true)
        {
            canvas.drawText(charsequence, l1, k2, k, j3, textpaint);
        } else
        {
            textline.set(textpaint, charsequence, l1, k2, k3, ((Directions) (obj2)), flag3, ((TabStops) (obj)));
            if(flag)
                textline.justify(j4 - l3 - j1);
            textline.draw(canvas, k, i2, j3, l2);
        }
        textpaint.setHyphenEdit(0);
        k1++;
        obj2 = obj;
        obj1 = obj3;
        k = i3;
        l = j2;
        i1 = k4;
        obj = aparagraphstyle;
        j1 = i5;
        continue; /* Loop/switch isn't completed */
_L18:
        if(true) goto _L20; else goto _L2
_L2:
        TextLine.recycle(textline);
        return;
        if(true) goto _L22; else goto _L21
_L21:
    }

    public final Alignment getAlignment()
    {
        return mAlignment;
    }

    public abstract int getBottomPadding();

    public void getCursorPath(int i, Path path, CharSequence charsequence)
    {
        int j;
        float f;
        int i1;
        int j1;
        int k1;
        int l1;
        float f2;
label0:
        {
            path.reset();
            j = getLineForOffset(i);
            int k = getLineTop(j);
            int l = getLineTop(j + 1);
            boolean flag = shouldClampCursor(j);
            f = getPrimaryHorizontal(i, flag) - 0.5F;
            float f1;
            int i2;
            if(isLevelBoundary(i))
                f1 = getSecondaryHorizontal(i, flag) - 0.5F;
            else
                f1 = f;
            i1 = TextKeyListener.getMetaState(charsequence, 1) | TextKeyListener.getMetaState(charsequence, 2048);
            j1 = TextKeyListener.getMetaState(charsequence, 2);
            k1 = 0;
            if(i1 == 0)
            {
                l1 = l;
                j = k;
                if(j1 == 0)
                    break label0;
            }
            i2 = l - k >> 2;
            i = k;
            if(j1 != 0)
                i = k + i2;
            l1 = l;
            k1 = i2;
            j = i;
            if(i1 != 0)
            {
                l1 = l - i2;
                j = i;
                k1 = i2;
            }
        }
        f2 = f;
        if(f < 0.5F)
            f2 = 0.5F;
        f = f1;
        if(f1 < 0.5F)
            f = 0.5F;
        if(Float.compare(f2, f) == 0)
        {
            path.moveTo(f2, j);
            path.lineTo(f2, l1);
        } else
        {
            path.moveTo(f2, j);
            path.lineTo(f2, j + l1 >> 1);
            path.moveTo(f, j + l1 >> 1);
            path.lineTo(f, l1);
        }
        if(i1 == 2)
        {
            path.moveTo(f, l1);
            path.lineTo(f - (float)k1, l1 + k1);
            path.lineTo(f, l1);
            path.lineTo((float)k1 + f, l1 + k1);
        } else
        if(i1 == 1)
        {
            path.moveTo(f, l1);
            path.lineTo(f - (float)k1, l1 + k1);
            path.moveTo(f - (float)k1, (float)(l1 + k1) - 0.5F);
            path.lineTo((float)k1 + f, (float)(l1 + k1) - 0.5F);
            path.moveTo((float)k1 + f, l1 + k1);
            path.lineTo(f, l1);
        }
        if(j1 == 2)
        {
            path.moveTo(f2, j);
            path.lineTo(f2 - (float)k1, j - k1);
            path.lineTo(f2, j);
            path.lineTo((float)k1 + f2, j - k1);
        } else
        if(j1 == 1)
        {
            path.moveTo(f2, j);
            path.lineTo(f2 - (float)k1, j - k1);
            path.moveTo(f2 - (float)k1, (float)(j - k1) + 0.5F);
            path.lineTo((float)k1 + f2, (float)(j - k1) + 0.5F);
            path.moveTo((float)k1 + f2, j - k1);
            path.lineTo(f2, j);
        }
    }

    public abstract int getEllipsisCount(int i);

    public abstract int getEllipsisStart(int i);

    public int getEllipsizedWidth()
    {
        return mWidth;
    }

    public int getHeight()
    {
        return getLineTop(getLineCount());
    }

    public int getHeight(boolean flag)
    {
        return getHeight();
    }

    public int getHyphen(int i)
    {
        return 0;
    }

    public int getIndentAdjust(int i, Alignment alignment)
    {
        return 0;
    }

    public final int getLineAscent(int i)
    {
        return getLineTop(i) - (getLineTop(i + 1) - getLineDescent(i));
    }

    public final int getLineBaseline(int i)
    {
        return getLineTop(i + 1) - getLineDescent(i);
    }

    public final int getLineBottom(int i)
    {
        return getLineTop(i + 1);
    }

    public int getLineBounds(int i, Rect rect)
    {
        if(rect != null)
        {
            rect.left = 0;
            rect.top = getLineTop(i);
            rect.right = mWidth;
            rect.bottom = getLineTop(i + 1);
        }
        return getLineBaseline(i);
    }

    public abstract boolean getLineContainsTab(int i);

    public abstract int getLineCount();

    public abstract int getLineDescent(int i);

    public abstract Directions getLineDirections(int i);

    public final int getLineEnd(int i)
    {
        return getLineStart(i + 1);
    }

    public int getLineForOffset(int i)
    {
        int j = getLineCount();
        int k;
        for(k = -1; j - k > 1;)
        {
            int l = (j + k) / 2;
            if(getLineStart(l) > i)
                j = l;
            else
                k = l;
        }

        if(k < 0)
            return 0;
        else
            return k;
    }

    public int getLineForVertical(int i)
    {
        int j = getLineCount();
        int k;
        for(k = -1; j - k > 1;)
        {
            int l = (j + k) / 2;
            if(getLineTop(l) > i)
                j = l;
            else
                k = l;
        }

        if(k < 0)
            return 0;
        else
            return k;
    }

    public float getLineLeft(int i)
    {
        int j = getParagraphDirection(i);
        Alignment alignment = getParagraphAlignment(i);
        if(alignment == Alignment.ALIGN_LEFT)
            return 0.0F;
        if(alignment == Alignment.ALIGN_NORMAL)
            if(j == -1)
                return (float)getParagraphRight(i) - getLineMax(i);
            else
                return 0.0F;
        if(alignment == Alignment.ALIGN_RIGHT)
            return (float)mWidth - getLineMax(i);
        if(alignment == Alignment.ALIGN_OPPOSITE)
        {
            if(j == -1)
                return 0.0F;
            else
                return (float)mWidth - getLineMax(i);
        } else
        {
            int k = getParagraphLeft(i);
            return (float)((getParagraphRight(i) - k - ((int)getLineMax(i) & -2)) / 2 + k);
        }
    }

    public float getLineMax(int i)
    {
        float f = getParagraphLeadingMargin(i);
        float f1 = getLineExtent(i, false);
        if(f1 < 0.0F)
            f1 = -f1;
        return f + f1;
    }

    public long getLineRangeForDraw(Canvas canvas)
    {
        Rect rect = sTempRect;
        rect;
        JVM INSTR monitorenter ;
        long l;
        if(canvas.getClipBounds(sTempRect))
            break MISSING_BLOCK_LABEL_26;
        l = TextUtils.packRangeInLong(0, -1);
        rect;
        JVM INSTR monitorexit ;
        return l;
        int i;
        int j;
        i = sTempRect.top;
        j = sTempRect.bottom;
        rect;
        JVM INSTR monitorexit ;
        i = Math.max(i, 0);
        j = Math.min(getLineTop(getLineCount()), j);
        if(i >= j)
            return TextUtils.packRangeInLong(0, -1);
        else
            return TextUtils.packRangeInLong(getLineForVertical(i), getLineForVertical(j));
        canvas;
        throw canvas;
    }

    public float getLineRight(int i)
    {
        int j = getParagraphDirection(i);
        Alignment alignment = getParagraphAlignment(i);
        if(alignment == Alignment.ALIGN_LEFT)
            return (float)getParagraphLeft(i) + getLineMax(i);
        if(alignment == Alignment.ALIGN_NORMAL)
            if(j == -1)
                return (float)mWidth;
            else
                return (float)getParagraphLeft(i) + getLineMax(i);
        if(alignment == Alignment.ALIGN_RIGHT)
            return (float)mWidth;
        if(alignment == Alignment.ALIGN_OPPOSITE)
        {
            if(j == -1)
                return getLineMax(i);
            else
                return (float)mWidth;
        } else
        {
            int l = getParagraphLeft(i);
            int k = getParagraphRight(i);
            return (float)(k - (k - l - ((int)getLineMax(i) & -2)) / 2);
        }
    }

    public abstract int getLineStart(int i);

    public abstract int getLineTop(int i);

    public int getLineVisibleEnd(int i)
    {
        return getLineVisibleEnd(i, getLineStart(i), getLineStart(i + 1));
    }

    public float getLineWidth(int i)
    {
        float f = getParagraphLeadingMargin(i);
        float f1 = getLineExtent(i, true);
        if(f1 < 0.0F)
            f1 = -f1;
        return f + f1;
    }

    public int getOffsetForHorizontal(int i, float f)
    {
        return getOffsetForHorizontal(i, f, true);
    }

    public int getOffsetForHorizontal(int i, float f, boolean flag)
    {
        int j = getLineEnd(i);
        int k = getLineStart(i);
        Directions directions = getLineDirections(i);
        TextLine textline = TextLine.obtain();
        textline.set(mPaint, mText, k, j, getParagraphDirection(i), directions, false, null);
        float f1;
        if(i == getLineCount() - 1)
            i = j;
        else
            i = textline.getOffsetToLeftRightOf(j - k, isRtlCharAt(j - 1) ^ true) + k;
        j = k;
        f1 = Math.abs(getHorizontal(k, flag) - f);
        for(int l = 0; l < directions.mDirections.length; l += 2)
        {
            int i1 = k + directions.mDirections[l];
            int j1 = i1 + (directions.mDirections[l + 1] & 0x3ffffff);
            boolean flag1;
            int k1;
            int i2;
            int j2;
            if((directions.mDirections[l + 1] & 0x4000000) != 0)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
                k1 = -1;
            else
                k1 = 1;
            i2 = j1;
            if(j1 > i)
                i2 = i;
            j2 = (i2 - 1) + 1;
            for(j1 = (i1 + 1) - 1; j2 - j1 > 1;)
            {
                int l2 = (j2 + j1) / 2;
                if(getHorizontal(getOffsetAtStartOf(l2), flag) * (float)k1 >= (float)k1 * f)
                    j2 = l2;
                else
                    j1 = l2;
            }

            k1 = j1;
            if(j1 < i1 + 1)
                k1 = i1 + 1;
            j1 = j;
            float f2 = f1;
            if(k1 < i2)
            {
                int k2 = textline.getOffsetToLeftRightOf(k1 - k, flag1) + k;
                int i3 = textline.getOffsetToLeftRightOf(k2 - k, flag1 ^ true) + k;
                j1 = j;
                f2 = f1;
                if(i3 >= i1)
                {
                    j1 = j;
                    f2 = f1;
                    if(i3 < i2)
                    {
                        f2 = Math.abs(getHorizontal(i3, flag) - f);
                        float f3 = f2;
                        int l1 = i3;
                        if(k2 < i2)
                        {
                            float f5 = Math.abs(getHorizontal(k2, flag) - f);
                            f3 = f2;
                            l1 = i3;
                            if(f5 < f2)
                            {
                                f3 = f5;
                                l1 = k2;
                            }
                        }
                        j1 = j;
                        f2 = f1;
                        if(f3 < f1)
                        {
                            f2 = f3;
                            j1 = l1;
                        }
                    }
                }
            }
            float f4 = Math.abs(getHorizontal(i1, flag) - f);
            j = j1;
            f1 = f2;
            if(f4 < f2)
            {
                f1 = f4;
                j = i1;
            }
        }

        if(Math.abs(getHorizontal(i, flag) - f) <= f1)
            j = i;
        TextLine.recycle(textline);
        return j;
    }

    public int getOffsetToLeftOf(int i)
    {
        return getOffsetToLeftRightOf(i, true);
    }

    public int getOffsetToRightOf(int i)
    {
        return getOffsetToLeftRightOf(i, false);
    }

    public final TextPaint getPaint()
    {
        return mPaint;
    }

    public final Alignment getParagraphAlignment(int i)
    {
        Alignment alignment = mAlignment;
        Alignment alignment1 = alignment;
        if(mSpannedText)
        {
            AlignmentSpan aalignmentspan[] = (AlignmentSpan[])getParagraphSpans((Spanned)mText, getLineStart(i), getLineEnd(i), android/text/style/AlignmentSpan);
            i = aalignmentspan.length;
            alignment1 = alignment;
            if(i > 0)
                alignment1 = aalignmentspan[i - 1].getAlignment();
        }
        return alignment1;
    }

    public abstract int getParagraphDirection(int i);

    public final int getParagraphLeft(int i)
    {
        if(getParagraphDirection(i) == -1 || mSpannedText ^ true)
            return 0;
        else
            return getParagraphLeadingMargin(i);
    }

    public final int getParagraphRight(int i)
    {
        int j = mWidth;
        if(getParagraphDirection(i) == 1 || mSpannedText ^ true)
            return j;
        else
            return j - getParagraphLeadingMargin(i);
    }

    public float getPrimaryHorizontal(int i)
    {
        return getPrimaryHorizontal(i, false);
    }

    public float getPrimaryHorizontal(int i, boolean flag)
    {
        return getHorizontal(i, primaryIsTrailingPrevious(i), flag);
    }

    public long getRunRange(int i)
    {
        int j = getLineForOffset(i);
        Directions directions = getLineDirections(j);
        if(directions == DIRS_ALL_LEFT_TO_RIGHT || directions == DIRS_ALL_RIGHT_TO_LEFT)
            return TextUtils.packRangeInLong(0, getLineEnd(j));
        int ai[] = directions.mDirections;
        int k = getLineStart(j);
        for(int l = 0; l < ai.length; l += 2)
        {
            int i1 = k + ai[l];
            int j1 = i1 + (ai[l + 1] & 0x3ffffff);
            if(i >= i1 && i < j1)
                return TextUtils.packRangeInLong(i1, j1);
        }

        return TextUtils.packRangeInLong(0, getLineEnd(j));
    }

    public float getSecondaryHorizontal(int i)
    {
        return getSecondaryHorizontal(i, false);
    }

    public float getSecondaryHorizontal(int i, boolean flag)
    {
        return getHorizontal(i, primaryIsTrailingPrevious(i) ^ true, flag);
    }

    public void getSelectionPath(int i, int j, Path path)
    {
        path.reset();
        if(i == j)
            return;
        int k = i;
        int l = j;
        if(j < i)
        {
            l = i;
            k = j;
        }
        i = getLineForOffset(k);
        j = getLineForOffset(l);
        int i1 = getLineTop(i);
        int j1 = getLineBottom(j);
        if(i == j)
        {
            addSelection(i, k, l, i1, j1, path);
        } else
        {
            float f = mWidth;
            addSelection(i, k, getLineEnd(i), i1, getLineBottom(i), path);
            if(getParagraphDirection(i) == -1)
                path.addRect(getLineLeft(i), i1, 0.0F, getLineBottom(i), android.graphics.Path.Direction.CW);
            else
                path.addRect(getLineRight(i), i1, f, getLineBottom(i), android.graphics.Path.Direction.CW);
            for(i++; i < j; i++)
            {
                k = getLineTop(i);
                int k1 = getLineBottom(i);
                path.addRect(0.0F, k, f, k1, android.graphics.Path.Direction.CW);
            }

            i = getLineTop(j);
            k = getLineBottom(j);
            addSelection(j, getLineStart(j), l, i, k, path);
            if(getParagraphDirection(j) == -1)
                path.addRect(f, i, getLineRight(j), k, android.graphics.Path.Direction.CW);
            else
                path.addRect(0.0F, i, getLineLeft(j), k, android.graphics.Path.Direction.CW);
        }
    }

    public final float getSpacingAdd()
    {
        return mSpacingAdd;
    }

    public final float getSpacingMultiplier()
    {
        return mSpacingMult;
    }

    public final CharSequence getText()
    {
        return mText;
    }

    public final TextDirectionHeuristic getTextDirectionHeuristic()
    {
        return mTextDir;
    }

    public abstract int getTopPadding();

    public final int getWidth()
    {
        return mWidth;
    }

    public final void increaseWidthTo(int i)
    {
        if(i < mWidth)
        {
            throw new RuntimeException("attempted to reduce Layout width");
        } else
        {
            mWidth = i;
            return;
        }
    }

    public boolean isLevelBoundary(int i)
    {
        boolean flag = true;
        int j = getLineForOffset(i);
        Directions directions = getLineDirections(j);
        if(directions == DIRS_ALL_LEFT_TO_RIGHT || directions == DIRS_ALL_RIGHT_TO_LEFT)
            return false;
        int ai[] = directions.mDirections;
        int k = getLineStart(j);
        int l = getLineEnd(j);
        if(i == k || i == l)
        {
            int i1;
            if(getParagraphDirection(j) == 1)
                i1 = 0;
            else
                i1 = 1;
            if(i == k)
                i = 0;
            else
                i = ai.length - 2;
            if((ai[i + 1] >>> 26 & 0x3f) == i1)
                flag = false;
            return flag;
        }
        for(int j1 = 0; j1 < ai.length; j1 += 2)
            if(i - k == ai[j1])
                return true;

        return false;
    }

    public boolean isRtlCharAt(int i)
    {
        boolean flag = true;
        int j = getLineForOffset(i);
        Directions directions = getLineDirections(j);
        if(directions == DIRS_ALL_LEFT_TO_RIGHT)
            return false;
        if(directions == DIRS_ALL_RIGHT_TO_LEFT)
            return true;
        int ai[] = directions.mDirections;
        int l = getLineStart(j);
        for(int k = 0; k < ai.length; k += 2)
        {
            int i1 = l + ai[k];
            int j1 = ai[k + 1];
            if(i >= i1 && i < i1 + (j1 & 0x3ffffff))
            {
                if((ai[k + 1] >>> 26 & 0x3f & 1) == 0)
                    flag = false;
                return flag;
            }
        }

        return false;
    }

    protected final boolean isSpanned()
    {
        return mSpannedText;
    }

    void replaceWith(CharSequence charsequence, TextPaint textpaint, int i, Alignment alignment, float f, float f1)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Layout: ").append(i).append(" < 0").toString());
        } else
        {
            mText = charsequence;
            mPaint = textpaint;
            mWidth = i;
            mAlignment = alignment;
            mSpacingMult = f;
            mSpacingAdd = f1;
            mSpannedText = charsequence instanceof Spanned;
            return;
        }
    }

    protected void setJustificationMode(int i)
    {
        mJustificationMode = i;
    }

    public boolean shouldClampCursor(int i)
    {
        boolean flag = true;
        switch(_2D_getandroid_2D_text_2D_Layout$AlignmentSwitchesValues()[getParagraphAlignment(i).ordinal()])
        {
        default:
            return false;

        case 1: // '\001'
            return true;

        case 2: // '\002'
            break;
        }
        if(getParagraphDirection(i) <= 0)
            flag = false;
        return flag;
    }

    private static final int _2D_android_2D_text_2D_Layout$AlignmentSwitchesValues[];
    public static final int BREAK_STRATEGY_BALANCED = 2;
    public static final int BREAK_STRATEGY_HIGH_QUALITY = 1;
    public static final int BREAK_STRATEGY_SIMPLE = 0;
    public static final Directions DIRS_ALL_LEFT_TO_RIGHT = new Directions(new int[] {
        0, 0x3ffffff
    });
    public static final Directions DIRS_ALL_RIGHT_TO_LEFT = new Directions(new int[] {
        0, 0x7ffffff
    });
    public static final int DIR_LEFT_TO_RIGHT = 1;
    static final int DIR_REQUEST_DEFAULT_LTR = 2;
    static final int DIR_REQUEST_DEFAULT_RTL = -2;
    static final int DIR_REQUEST_LTR = 1;
    static final int DIR_REQUEST_RTL = -1;
    public static final int DIR_RIGHT_TO_LEFT = -1;
    public static final int HYPHENATION_FREQUENCY_FULL = 2;
    public static final int HYPHENATION_FREQUENCY_NONE = 0;
    public static final int HYPHENATION_FREQUENCY_NORMAL = 1;
    public static final int JUSTIFICATION_MODE_INTER_WORD = 1;
    public static final int JUSTIFICATION_MODE_NONE = 0;
    private static final ParagraphStyle NO_PARA_SPANS[] = (ParagraphStyle[])ArrayUtils.emptyArray(android/text/style/ParagraphStyle);
    static final int RUN_LENGTH_MASK = 0x3ffffff;
    static final int RUN_LEVEL_MASK = 63;
    static final int RUN_LEVEL_SHIFT = 26;
    static final int RUN_RTL_FLAG = 0x4000000;
    private static final int TAB_INCREMENT = 20;
    private static final Rect sTempRect = new Rect();
    private Alignment mAlignment;
    private int mJustificationMode;
    private SpanSet mLineBackgroundSpans;
    private TextPaint mPaint;
    private float mSpacingAdd;
    private float mSpacingMult;
    private boolean mSpannedText;
    private CharSequence mText;
    private TextDirectionHeuristic mTextDir;
    private int mWidth;

}
