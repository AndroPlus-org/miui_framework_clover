// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.os.LocaleList;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.TabStopSpan;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;

// Referenced classes of package android.text:
//            Layout, Spanned, TextDirectionHeuristics, TextUtils, 
//            TextPaint, AndroidBidi, MeasuredText, TextDirectionHeuristic, 
//            Hyphenator

public class StaticLayout extends Layout
{
    public static final class Builder
    {

        static void _2D_wrap0(Builder builder)
        {
            recycle(builder);
        }

        static void _2D_wrap1(Builder builder, LocaleList localelist)
        {
            builder.setLocales(localelist);
        }

        private long[] getHyphenators(LocaleList localelist)
        {
            int i = localelist.size();
            long al[] = new long[i];
            for(int j = 0; j < i; j++)
                al[j] = Hyphenator.get(localelist.get(j)).getNativePtr();

            return al;
        }

        public static Builder obtain(CharSequence charsequence, int i, int j, TextPaint textpaint, int k)
        {
            Builder builder = (Builder)sPool.acquire();
            Builder builder1 = builder;
            if(builder == null)
                builder1 = new Builder();
            builder1.mText = charsequence;
            builder1.mStart = i;
            builder1.mEnd = j;
            builder1.mPaint = textpaint;
            builder1.mWidth = k;
            builder1.mAlignment = Layout.Alignment.ALIGN_NORMAL;
            builder1.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            builder1.mSpacingMult = 1.0F;
            builder1.mSpacingAdd = 0.0F;
            builder1.mIncludePad = true;
            builder1.mEllipsizedWidth = k;
            builder1.mEllipsize = null;
            builder1.mMaxLines = 0x7fffffff;
            builder1.mBreakStrategy = 0;
            builder1.mHyphenationFrequency = 0;
            builder1.mJustificationMode = 0;
            builder1.mMeasuredText = MeasuredText.obtain();
            return builder1;
        }

        private static void recycle(Builder builder)
        {
            builder.mPaint = null;
            builder.mText = null;
            MeasuredText.recycle(builder.mMeasuredText);
            builder.mMeasuredText = null;
            builder.mLeftIndents = null;
            builder.mRightIndents = null;
            StaticLayout._2D_wrap4(builder.mNativePtr);
            sPool.release(builder);
        }

        private void setLocales(LocaleList localelist)
        {
            if(!localelist.equals(mLocales))
            {
                StaticLayout._2D_wrap7(mNativePtr, localelist.toLanguageTags(), getHyphenators(localelist));
                mLocales = localelist;
            }
        }

        void addMeasuredRun(int i, int j, float af[])
        {
            StaticLayout._2D_wrap2(mNativePtr, i, j, af);
        }

        void addReplacementRun(int i, int j, float f)
        {
            StaticLayout._2D_wrap3(mNativePtr, i, j, f);
        }

        float addStyleRun(TextPaint textpaint, int i, int j, boolean flag)
        {
            setLocales(textpaint.getTextLocales());
            return StaticLayout._2D_wrap0(mNativePtr, textpaint.getNativeInstance(), textpaint.mNativeTypeface, i, j, flag);
        }

        public StaticLayout build()
        {
            StaticLayout staticlayout = new StaticLayout(this, null);
            recycle(this);
            return staticlayout;
        }

        protected void finalize()
            throws Throwable
        {
            StaticLayout._2D_wrap5(mNativePtr);
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        void finish()
        {
            StaticLayout._2D_wrap4(mNativePtr);
            mText = null;
            mPaint = null;
            mLeftIndents = null;
            mRightIndents = null;
            mMeasuredText.finish();
        }

        public Builder setAlignment(Layout.Alignment alignment)
        {
            mAlignment = alignment;
            return this;
        }

        public Builder setBreakStrategy(int i)
        {
            mBreakStrategy = i;
            return this;
        }

        public Builder setEllipsize(TextUtils.TruncateAt truncateat)
        {
            mEllipsize = truncateat;
            return this;
        }

        public Builder setEllipsizedWidth(int i)
        {
            mEllipsizedWidth = i;
            return this;
        }

        public Builder setHyphenationFrequency(int i)
        {
            mHyphenationFrequency = i;
            return this;
        }

        public Builder setIncludePad(boolean flag)
        {
            mIncludePad = flag;
            return this;
        }

        public Builder setIndents(int ai[], int ai1[])
        {
            mLeftIndents = ai;
            mRightIndents = ai1;
            int i;
            int j;
            int ai2[];
            int k;
            if(ai == null)
                i = 0;
            else
                i = ai.length;
            if(ai1 == null)
                j = 0;
            else
                j = ai1.length;
            ai2 = new int[Math.max(i, j)];
            k = 0;
            while(k < ai2.length) 
            {
                int l;
                int i1;
                if(k < i)
                    l = ai[k];
                else
                    l = 0;
                if(k < j)
                    i1 = ai1[k];
                else
                    i1 = 0;
                ai2[k] = l + i1;
                k++;
            }
            StaticLayout._2D_wrap6(mNativePtr, ai2);
            return this;
        }

        public Builder setJustificationMode(int i)
        {
            mJustificationMode = i;
            return this;
        }

        public Builder setLineSpacing(float f, float f1)
        {
            mSpacingAdd = f;
            mSpacingMult = f1;
            return this;
        }

        public Builder setMaxLines(int i)
        {
            mMaxLines = i;
            return this;
        }

        public Builder setPaint(TextPaint textpaint)
        {
            mPaint = textpaint;
            return this;
        }

        public Builder setText(CharSequence charsequence)
        {
            return setText(charsequence, 0, charsequence.length());
        }

        public Builder setText(CharSequence charsequence, int i, int j)
        {
            mText = charsequence;
            mStart = i;
            mEnd = j;
            return this;
        }

        public Builder setTextDirection(TextDirectionHeuristic textdirectionheuristic)
        {
            mTextDir = textdirectionheuristic;
            return this;
        }

        public Builder setWidth(int i)
        {
            mWidth = i;
            if(mEllipsize == null)
                mEllipsizedWidth = i;
            return this;
        }

        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(3);
        Layout.Alignment mAlignment;
        int mBreakStrategy;
        TextUtils.TruncateAt mEllipsize;
        int mEllipsizedWidth;
        int mEnd;
        android.graphics.Paint.FontMetricsInt mFontMetricsInt;
        int mHyphenationFrequency;
        boolean mIncludePad;
        int mJustificationMode;
        int mLeftIndents[];
        LocaleList mLocales;
        int mMaxLines;
        MeasuredText mMeasuredText;
        long mNativePtr;
        TextPaint mPaint;
        int mRightIndents[];
        float mSpacingAdd;
        float mSpacingMult;
        int mStart;
        CharSequence mText;
        TextDirectionHeuristic mTextDir;
        int mWidth;


        private Builder()
        {
            mFontMetricsInt = new android.graphics.Paint.FontMetricsInt();
            mNativePtr = StaticLayout._2D_wrap1();
        }
    }

    static class LineBreaks
    {

        private static final int INITIAL_SIZE = 16;
        public int breaks[];
        public int flags[];
        public float widths[];

        LineBreaks()
        {
            breaks = new int[16];
            widths = new float[16];
            flags = new int[16];
        }
    }


    static float _2D_wrap0(long l, long l1, long l2, int i, int j, 
            boolean flag)
    {
        return nAddStyleRun(l, l1, l2, i, j, flag);
    }

    static long _2D_wrap1()
    {
        return nNewBuilder();
    }

    static void _2D_wrap2(long l, int i, int j, float af[])
    {
        nAddMeasuredRun(l, i, j, af);
    }

    static void _2D_wrap3(long l, int i, int j, float f)
    {
        nAddReplacementRun(l, i, j, f);
    }

    static void _2D_wrap4(long l)
    {
        nFinishBuilder(l);
    }

    static void _2D_wrap5(long l)
    {
        nFreeBuilder(l);
    }

    static void _2D_wrap6(long l, int ai[])
    {
        nSetIndents(l, ai);
    }

    static void _2D_wrap7(long l, String s, long al[])
    {
        nSetLocales(l, s, al);
    }

    private StaticLayout(Builder builder)
    {
        Object obj;
        if(builder.mEllipsize == null)
            obj = builder.mText;
        else
        if(builder.mText instanceof Spanned)
            obj = new Layout.SpannedEllipsizer(builder.mText);
        else
            obj = new Layout.Ellipsizer(builder.mText);
        super(((CharSequence) (obj)), builder.mPaint, builder.mWidth, builder.mAlignment, builder.mSpacingMult, builder.mSpacingAdd);
        mMaxLineHeight = -1;
        mMaximumVisibleLineCount = 0x7fffffff;
        if(builder.mEllipsize != null)
        {
            obj = (Layout.Ellipsizer)getText();
            obj.mLayout = this;
            obj.mWidth = builder.mEllipsizedWidth;
            obj.mMethod = builder.mEllipsize;
            mEllipsizedWidth = builder.mEllipsizedWidth;
            mColumns = 6;
        } else
        {
            mColumns = 4;
            mEllipsizedWidth = builder.mWidth;
        }
        mLineDirections = (Layout.Directions[])ArrayUtils.newUnpaddedArray(android/text/Layout$Directions, mColumns * 2);
        mLines = new int[mLineDirections.length];
        mMaximumVisibleLineCount = builder.mMaxLines;
        mLeftIndents = builder.mLeftIndents;
        mRightIndents = builder.mRightIndents;
        setJustificationMode(builder.mJustificationMode);
        generate(builder, builder.mIncludePad, builder.mIncludePad);
    }

    StaticLayout(Builder builder, StaticLayout staticlayout)
    {
        this(builder);
    }

    StaticLayout(CharSequence charsequence)
    {
        super(charsequence, null, 0, null, 0.0F, 0.0F);
        mMaxLineHeight = -1;
        mMaximumVisibleLineCount = 0x7fffffff;
        mColumns = 6;
        mLineDirections = (Layout.Directions[])ArrayUtils.newUnpaddedArray(android/text/Layout$Directions, mColumns * 2);
        mLines = new int[mLineDirections.length];
    }

    public StaticLayout(CharSequence charsequence, int i, int j, TextPaint textpaint, int k, Layout.Alignment alignment, float f, 
            float f1, boolean flag)
    {
        this(charsequence, i, j, textpaint, k, alignment, f, f1, flag, null, 0);
    }

    public StaticLayout(CharSequence charsequence, int i, int j, TextPaint textpaint, int k, Layout.Alignment alignment, float f, 
            float f1, boolean flag, TextUtils.TruncateAt truncateat, int l)
    {
        this(charsequence, i, j, textpaint, k, alignment, TextDirectionHeuristics.FIRSTSTRONG_LTR, f, f1, flag, truncateat, l, 0x7fffffff);
    }

    public StaticLayout(CharSequence charsequence, int i, int j, TextPaint textpaint, int k, Layout.Alignment alignment, TextDirectionHeuristic textdirectionheuristic, 
            float f, float f1, boolean flag)
    {
        this(charsequence, i, j, textpaint, k, alignment, textdirectionheuristic, f, f1, flag, null, 0, 0x7fffffff);
    }

    public StaticLayout(CharSequence charsequence, int i, int j, TextPaint textpaint, int k, Layout.Alignment alignment, TextDirectionHeuristic textdirectionheuristic, 
            float f, float f1, boolean flag, TextUtils.TruncateAt truncateat, int l, int i1)
    {
        Object obj;
        if(truncateat == null)
            obj = charsequence;
        else
        if(charsequence instanceof Spanned)
            obj = new Layout.SpannedEllipsizer(charsequence);
        else
            obj = new Layout.Ellipsizer(charsequence);
        super(((CharSequence) (obj)), textpaint, k, alignment, textdirectionheuristic, f, f1);
        mMaxLineHeight = -1;
        mMaximumVisibleLineCount = 0x7fffffff;
        charsequence = Builder.obtain(charsequence, i, j, textpaint, k).setAlignment(alignment).setTextDirection(textdirectionheuristic).setLineSpacing(f1, f).setIncludePad(flag).setEllipsizedWidth(l).setEllipsize(truncateat).setMaxLines(i1);
        if(truncateat != null)
        {
            textpaint = (Layout.Ellipsizer)getText();
            textpaint.mLayout = this;
            textpaint.mWidth = l;
            textpaint.mMethod = truncateat;
            mEllipsizedWidth = l;
            mColumns = 6;
        } else
        {
            mColumns = 4;
            mEllipsizedWidth = k;
        }
        mLineDirections = (Layout.Directions[])ArrayUtils.newUnpaddedArray(android/text/Layout$Directions, mColumns * 2);
        mLines = new int[mLineDirections.length];
        mMaximumVisibleLineCount = i1;
        generate(charsequence, ((Builder) (charsequence)).mIncludePad, ((Builder) (charsequence)).mIncludePad);
        Builder._2D_wrap0(charsequence);
    }

    public StaticLayout(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, boolean flag)
    {
        this(charsequence, 0, charsequence.length(), textpaint, i, alignment, f, f1, flag);
    }

    public StaticLayout(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, TextDirectionHeuristic textdirectionheuristic, float f, float f1, 
            boolean flag)
    {
        this(charsequence, 0, charsequence.length(), textpaint, i, alignment, textdirectionheuristic, f, f1, flag);
    }

    private void calculateEllipsis(int i, int j, float af[], int k, float f, TextUtils.TruncateAt truncateat, int l, 
            float f1, TextPaint textpaint, boolean flag)
    {
        float f2;
        float f3;
        boolean flag1;
        int i1;
        int j1;
        f2 = f - getTotalInsets(l);
        if(f1 <= f2 && flag ^ true)
        {
            mLines[mColumns * l + 4] = 0;
            mLines[mColumns * l + 5] = 0;
            return;
        }
        char ac[];
        if(truncateat == TextUtils.TruncateAt.END_SMALL)
            ac = TextUtils.ELLIPSIS_TWO_DOTS;
        else
            ac = TextUtils.ELLIPSIS_NORMAL;
        f3 = textpaint.measureText(ac, 0, 1);
        flag1 = false;
        i1 = 0;
        j1 = j - i;
        if(truncateat != TextUtils.TruncateAt.START) goto _L2; else goto _L1
_L1:
        if(mMaximumVisibleLineCount != 1) goto _L4; else goto _L3
_L3:
        f = 0.0F;
        j = j1;
        do
        {
            i1 = j;
            if(j <= 0)
                break;
            f1 = af[((j - 1) + i) - k];
            if(f1 + f + f3 > f2)
            {
                do
                {
                    i1 = j;
                    if(j >= j1)
                        break;
                    i1 = j;
                    if(af[(j + i) - k] != 0.0F)
                        break;
                    j++;
                } while(true);
                break;
            }
            f += f1;
            j--;
        } while(true);
        j = 0;
        i = i1;
_L13:
        mEllipsized = true;
        mLines[mColumns * l + 4] = j;
        mLines[mColumns * l + 5] = i;
        return;
_L4:
        i = i1;
        j = ((flag1) ? 1 : 0);
        if(Log.isLoggable("StaticLayout", 5))
        {
            Log.w("StaticLayout", "Start Ellipsis only supported with one line");
            i = i1;
            j = ((flag1) ? 1 : 0);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        f = 0.0F;
        j = 0;
_L10:
        if(j >= j1) goto _L6; else goto _L5
_L5:
        f1 = af[(j + i) - k];
        if(f1 + f + f3 <= f2) goto _L7; else goto _L6
_L6:
        k = j;
        i1 = j1 - j;
        i = i1;
        j = k;
        if(flag)
        {
            i = i1;
            j = k;
            if(i1 == 0)
            {
                i = i1;
                j = k;
                if(j1 > 0)
                {
                    j = j1 - 1;
                    i = 1;
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(truncateat == TextUtils.TruncateAt.END || truncateat == TextUtils.TruncateAt.MARQUEE || truncateat == TextUtils.TruncateAt.END_SMALL) goto _L9; else goto _L8
_L8:
        float f4;
        if(mMaximumVisibleLineCount != 1)
            break MISSING_BLOCK_LABEL_559;
        f1 = 0.0F;
        f = 0.0F;
        f4 = (f2 - f3) / 2.0F;
        j = j1;
_L11:
        float f6;
        i1 = j;
        if(j <= 0)
            break MISSING_BLOCK_LABEL_497;
        f6 = af[((j - 1) + i) - k];
        if(f6 + f > f4)
        {
            do
            {
                i1 = j;
                if(j >= j1)
                    break;
                i1 = j;
                if(af[(j + i) - k] != 0.0F)
                    break;
                j++;
            } while(true);
            break MISSING_BLOCK_LABEL_497;
        }
        break MISSING_BLOCK_LABEL_484;
_L7:
        f += f1;
        j++;
          goto _L10
        f += f6;
        j--;
          goto _L11
        j = 0;
        do
        {
            float f5;
label0:
            {
                if(j < i1)
                {
                    f5 = af[(j + i) - k];
                    if(f5 + f1 <= f2 - f3 - f)
                        break label0;
                }
                k = j;
                i = i1 - j;
                j = k;
                continue; /* Loop/switch isn't completed */
            }
            f1 += f5;
            j++;
        } while(true);
          goto _L10
        i = i1;
        j = ((flag1) ? 1 : 0);
        if(Log.isLoggable("StaticLayout", 5))
        {
            Log.w("StaticLayout", "Middle Ellipsis only supported with one line");
            i = i1;
            j = ((flag1) ? 1 : 0);
        }
        if(true) goto _L13; else goto _L12
_L12:
    }

    private float getTotalInsets(int i)
    {
        int j = 0;
        if(mLeftIndents != null)
            j = mLeftIndents[Math.min(i, mLeftIndents.length - 1)];
        int k = j;
        if(mRightIndents != null)
            k = j + mRightIndents[Math.min(i, mRightIndents.length - 1)];
        return (float)k;
    }

    private static native void nAddMeasuredRun(long l, int i, int j, float af[]);

    private static native void nAddReplacementRun(long l, int i, int j, float f);

    private static native float nAddStyleRun(long l, long l1, long l2, int i, int j, 
            boolean flag);

    private static native int nComputeLineBreaks(long l, LineBreaks linebreaks, int ai[], float af[], int ai1[], int i);

    private static native void nFinishBuilder(long l);

    private static native void nFreeBuilder(long l);

    private static native void nGetWidths(long l, float af[]);

    static native long nLoadHyphenator(ByteBuffer bytebuffer, int i, int j, int k);

    private static native long nNewBuilder();

    private static native void nSetIndents(long l, int ai[]);

    private static native void nSetLocales(long l, String s, long al[]);

    private static native void nSetupParagraph(long l, char ac[], int i, float f, int j, float f1, int ai[], 
            int k, int i1, int j1, boolean flag);

    private int out(CharSequence charsequence, int i, int j, int k, int l, int i1, int j1, 
            int k1, float f, float f1, LineHeightSpan alineheightspan[], int ai[], android.graphics.Paint.FontMetricsInt fontmetricsint, int l1, 
            boolean flag, byte abyte0[], int i2, boolean flag1, int j2, boolean flag2, boolean flag3, 
            char ac[], float af[], int k2, TextUtils.TruncateAt truncateat, float f2, float f3, TextPaint textpaint, 
            boolean flag4)
    {
        int l2 = mLineCount;
        int i3 = l2 * mColumns;
        int j3 = mColumns + i3 + 1;
        int ai1[] = mLines;
        int ai2[] = ai1;
        if(j3 >= ai1.length)
        {
            ai2 = (Layout.Directions[])ArrayUtils.newUnpaddedArray(android/text/Layout$Directions, GrowingArrayUtils.growSize(j3));
            System.arraycopy(mLineDirections, 0, ai2, 0, mLineDirections.length);
            mLineDirections = ai2;
            ai2 = new int[ai2.length];
            System.arraycopy(ai1, 0, ai2, 0, ai1.length);
            mLines = ai2;
        }
        int k3 = k;
        int l3 = l;
        int i4 = i1;
        j3 = j1;
        if(alineheightspan != null)
        {
            fontmetricsint.ascent = k;
            fontmetricsint.descent = l;
            fontmetricsint.top = i1;
            fontmetricsint.bottom = j1;
            k = 0;
            while(k < alineheightspan.length) 
            {
                if(alineheightspan[k] instanceof android.text.style.LineHeightSpan.WithDensity)
                    ((android.text.style.LineHeightSpan.WithDensity)alineheightspan[k]).chooseHeight(charsequence, i, j, ai[k], k1, fontmetricsint, textpaint);
                else
                    alineheightspan[k].chooseHeight(charsequence, i, j, ai[k], k1, fontmetricsint);
                k++;
            }
            k3 = fontmetricsint.ascent;
            l3 = fontmetricsint.descent;
            i4 = fontmetricsint.top;
            j3 = fontmetricsint.bottom;
        }
        if(l2 == 0)
            l = 1;
        else
            l = 0;
        if(l2 + 1 == mMaximumVisibleLineCount)
            i1 = 1;
        else
            i1 = 0;
        if(truncateat != null)
        {
            boolean flag5;
            if(flag4 && mLineCount + 1 == mMaximumVisibleLineCount)
                flag5 = true;
            else
                flag5 = false;
            if((mMaximumVisibleLineCount == 1 && flag4 || l != 0 && flag4 ^ true) && truncateat != TextUtils.TruncateAt.MARQUEE)
                k = 1;
            else
            if(l == 0 && (i1 != 0 || flag4 ^ true))
            {
                if(truncateat == TextUtils.TruncateAt.END)
                    k = 1;
                else
                    k = 0;
            } else
            {
                k = 0;
            }
            if(k != 0)
                calculateEllipsis(i, j, af, k2, f2, truncateat, l2, f3, textpaint, flag5);
        }
        if(mEllipsized)
        {
            k = 1;
        } else
        {
            if(k2 != j2 && j2 > 0)
            {
                if(charsequence.charAt(j2 - 1) == '\n')
                    k = 1;
                else
                    k = 0;
            } else
            {
                k = 0;
            }
            if(j == j2 && (k ^ 1) != 0)
                k = 1;
            else
            if(i == j2 && k != 0)
                k = 1;
            else
                k = 0;
        }
        j1 = k3;
        if(l != 0)
        {
            if(flag3)
                mTopPadding = i4 - k3;
            j1 = k3;
            if(flag2)
                j1 = i4;
        }
        l = l3;
        if(k != 0)
        {
            if(flag3)
                mBottomPadding = j3 - l3;
            l = l3;
            if(flag2)
                l = j3;
        }
        if(flag && (k ^ 1) != 0)
        {
            double d = (float)(l - j1) * (f - 1.0F) + f1;
            if(d >= 0.0D)
                k = (int)(0.5D + d);
            else
                k = -(int)(-d + 0.5D);
        } else
        {
            k = 0;
        }
        ai2[i3 + 0] = i;
        ai2[i3 + 1] = k1;
        ai2[i3 + 2] = l + k;
        if(!mEllipsized && i1 != 0)
        {
            if(flag2)
                i1 = j3;
            else
                i1 = l;
            mMaxLineHeight = (i1 - j1) + k1;
        }
        k = k1 + ((l - j1) + k);
        ai2[mColumns + i3 + 0] = j;
        ai2[mColumns + i3 + 1] = k;
        l = i3 + 0;
        ai2[l] = ai2[l] | 0x20000000 & l1;
        ai2[i3 + 3] = l1;
        l = i3 + 0;
        ai2[l] = ai2[l] | i2 << 30;
        charsequence = DIRS_ALL_LEFT_TO_RIGHT;
        if(flag1)
            mLineDirections[l2] = charsequence;
        else
            mLineDirections[l2] = AndroidBidi.directions(i2, abyte0, i - k2, ac, i - k2, j - i);
        mLineCount = mLineCount + 1;
        return k;
    }

    void generate(Builder builder, boolean flag, boolean flag1)
    {
        CharSequence charsequence;
        int i;
        int j;
        TextPaint textpaint;
        TextDirectionHeuristic textdirectionheuristic;
        float f;
        float f1;
        float f2;
        TextUtils.TruncateAt truncateat;
        LineBreaks linebreaks;
        int ai[];
        int ai1[];
        int l;
        int i1;
        boolean flag2;
        android.graphics.Paint.FontMetricsInt fontmetricsint;
        int ai2[];
        MeasuredText measuredtext;
        Spanned spanned;
        int j1;
        int k1;
        int l1;
        int j2;
        int l2;
        LineHeightSpan alineheightspan[];
        int ai3[];
        int j3;
        int i4;
        charsequence = builder.mText;
        i = builder.mStart;
        j = builder.mEnd;
        textpaint = builder.mPaint;
        int k = builder.mWidth;
        textdirectionheuristic = builder.mTextDir;
        f = builder.mSpacingMult;
        f1 = builder.mSpacingAdd;
        f2 = builder.mEllipsizedWidth;
        truncateat = builder.mEllipsize;
        linebreaks = new LineBreaks();
        ai = new int[4];
        ai1 = new int[16];
        Builder._2D_wrap1(builder, textpaint.getTextLocales());
        mLineCount = 0;
        mEllipsized = false;
        LeadingMarginSpan aleadingmarginspan[];
        if(mMaximumVisibleLineCount < 1)
            l = 0;
        else
            l = -1;
        mMaxLineHeight = l;
        i1 = 0;
        if(f != 1.0F || f1 != 0.0F)
            flag2 = true;
        else
            flag2 = false;
        fontmetricsint = builder.mFontMetricsInt;
        ai2 = null;
        measuredtext = builder.mMeasuredText;
        spanned = null;
        if(charsequence instanceof Spanned)
            spanned = (Spanned)charsequence;
        l = i;
_L15:
        j1 = i1;
        if(l > j)
            break MISSING_BLOCK_LABEL_2033;
        j1 = TextUtils.indexOf(charsequence, '\n', l, j);
        if(j1 < 0)
            k1 = j;
        else
            k1 = j1 + 1;
        l1 = 1;
        j2 = 1;
        l2 = k;
        j1 = k;
        alineheightspan = null;
        ai3 = ai2;
        j3 = l2;
        i4 = j1;
        if(spanned == null) goto _L2; else goto _L1
_L1:
        char ac[];
        aleadingmarginspan = (LeadingMarginSpan[])getParagraphSpans(spanned, l, k1, android/text/style/LeadingMarginSpan);
        for(l1 = 0; l1 < aleadingmarginspan.length;)
        {
            alineheightspan = aleadingmarginspan[l1];
            l2 -= aleadingmarginspan[l1].getLeadingMargin(true);
            i4 = j1 - aleadingmarginspan[l1].getLeadingMargin(false);
            j1 = j2;
            if(alineheightspan instanceof android.text.style.LeadingMarginSpan.LeadingMarginSpan2)
                j1 = Math.max(j2, ((android.text.style.LeadingMarginSpan.LeadingMarginSpan2)alineheightspan).getLeadingMarginLineCount());
            l1++;
            j2 = j1;
            j1 = i4;
        }

        ac = (LineHeightSpan[])getParagraphSpans(spanned, l, k1, android/text/style/LineHeightSpan);
        if(ac.length != 0) goto _L4; else goto _L3
_L3:
        int ai4[];
        float af[];
        byte abyte0[];
        int k4;
        boolean flag3;
        alineheightspan = null;
        i4 = j1;
        j3 = l2;
        ai3 = ai2;
        l1 = j2;
          goto _L2
_L4:
        if(ai2 == null) goto _L6; else goto _L5
_L5:
        ai4 = ai2;
        if(ai2.length >= ac.length) goto _L7; else goto _L6
_L6:
        ai4 = ArrayUtils.newUnpaddedIntArray(ac.length);
_L7:
        l4 = 0;
_L9:
        l1 = j2;
        alineheightspan = ac;
        ai3 = ai4;
        j3 = l2;
        i4 = j1;
        if(l4 >= ac.length) goto _L2; else goto _L8
_L8:
        l1 = spanned.getSpanStart(ac[l4]);
        if(l1 < l)
            ai4[l4] = getLineTop(getLineForOffset(l1));
        else
            ai4[l4] = i1;
        l4++;
          goto _L9
_L2:
        float af1[];
        float f3;
        float f4;
        int k5;
        measuredtext.setPara(charsequence, l, k1, textdirectionheuristic, builder);
        ac = measuredtext.mChars;
        af = measuredtext.mWidths;
        abyte0 = measuredtext.mLevels;
        k4 = measuredtext.mDir;
        flag3 = measuredtext.mEasy;
        ai4 = null;
        ai2 = ai4;
        if(spanned != null)
        {
            TabStopSpan atabstopspan[] = (TabStopSpan[])getParagraphSpans(spanned, l, k1, android/text/style/TabStopSpan);
            ai2 = ai4;
            if(atabstopspan.length > 0)
            {
                ai2 = new int[atabstopspan.length];
                int l4;
                for(j1 = 0; j1 < atabstopspan.length; j1++)
                    ai2[j1] = atabstopspan[j1].getTabStop();

                Arrays.sort(ai2, 0, ai2.length);
            }
        }
        long l5 = builder.mNativePtr;
        f3 = j3;
        f4 = i4;
        j1 = builder.mBreakStrategy;
        j2 = builder.mHyphenationFrequency;
        boolean flag4;
        if(builder.mJustificationMode != 0)
            flag4 = true;
        else
            flag4 = false;
        nSetupParagraph(l5, ac, k1 - l, f3, l1, f4, ai2, 20, j1, j2, flag4);
        if(mLeftIndents != null || mRightIndents != null)
        {
            int k3;
            if(mLeftIndents == null)
                j1 = 0;
            else
                j1 = mLeftIndents.length;
            if(mRightIndents == null)
                j2 = 0;
            else
                j2 = mRightIndents.length;
            k3 = Math.max(1, Math.max(j1, j2) - mLineCount);
            ai2 = new int[k3];
            l2 = 0;
            while(l2 < k3) 
            {
                if(mLeftIndents == null)
                    l1 = 0;
                else
                    l1 = mLeftIndents[Math.min(mLineCount + l2, j1 - 1)];
                if(mRightIndents == null)
                    i4 = 0;
                else
                    i4 = mRightIndents[Math.min(mLineCount + l2, j2 - 1)];
                ai2[l2] = l1 + i4;
                l2++;
            }
            nSetIndents(builder.mNativePtr, ai2);
        }
        l2 = 0;
        j2 = 0;
        j1 = l;
        while(j1 < k1) 
        {
            ai2 = ai1;
            if(l2 * 4 >= ai1.length)
            {
                ai2 = new int[l2 * 4 * 2];
                System.arraycopy(ai1, 0, ai2, 0, l2 * 4);
            }
            ai4 = ai;
            if(j2 >= ai.length)
            {
                ai4 = new int[j2 * 2];
                System.arraycopy(ai, 0, ai4, 0, j2);
            }
            if(spanned == null)
            {
                l1 = k1;
                measuredtext.addStyleRun(textpaint, k1 - j1, fontmetricsint);
                j1 = l1;
            } else
            {
                l1 = spanned.nextSpanTransition(j1, k1, android/text/style/MetricAffectingSpan);
                measuredtext.addStyleRun(textpaint, (MetricAffectingSpan[])TextUtils.removeEmptySpans((MetricAffectingSpan[])spanned.getSpans(j1, l1, android/text/style/MetricAffectingSpan), spanned, android/text/style/MetricAffectingSpan), l1 - j1, fontmetricsint);
                j1 = l1;
            }
            ai2[l2 * 4 + 0] = fontmetricsint.top;
            ai2[l2 * 4 + 1] = fontmetricsint.bottom;
            ai2[l2 * 4 + 2] = fontmetricsint.ascent;
            ai2[l2 * 4 + 3] = fontmetricsint.descent;
            l2++;
            ai4[j2] = j1;
            j2++;
            ai1 = ai2;
            ai = ai4;
        }
        nGetWidths(builder.mNativePtr, af);
        l1 = nComputeLineBreaks(builder.mNativePtr, linebreaks, linebreaks.breaks, linebreaks.widths, linebreaks.flags, linebreaks.breaks.length);
        ai2 = linebreaks.breaks;
        af1 = linebreaks.widths;
        ai4 = linebreaks.flags;
        i4 = mMaximumVisibleLineCount - mLineCount;
        if(truncateat != null)
        {
            if(truncateat != TextUtils.TruncateAt.END)
            {
                if(mMaximumVisibleLineCount == 1)
                {
                    if(truncateat != TextUtils.TruncateAt.MARQUEE)
                        j1 = 1;
                    else
                        j1 = 0;
                } else
                {
                    j1 = 0;
                }
            } else
            {
                j1 = 1;
            }
        } else
        {
            j1 = 0;
        }
        k5 = l1;
        if(i4 <= 0)
            break MISSING_BLOCK_LABEL_1476;
        k5 = l1;
        if(i4 >= l1)
            break MISSING_BLOCK_LABEL_1476;
        k5 = l1;
        if(j1 == 0)
            break MISSING_BLOCK_LABEL_1476;
        f4 = 0.0F;
        l2 = 0;
        j2 = i4 - 1;
_L11:
        if(j2 >= l1)
            break MISSING_BLOCK_LABEL_1440;
        if(j2 != l1 - 1)
            break; /* Loop/switch isn't completed */
        f3 = f4 + af1[j2];
_L13:
        l2 |= ai4[j2] & 0x20000000;
        j2++;
        f4 = f3;
        if(true) goto _L11; else goto _L10
_L10:
        if(j2 == 0)
            j1 = 0;
        else
            j1 = ai2[j2 - 1];
        f3 = f4;
        if(j1 >= ai2[j2]) goto _L13; else goto _L12
_L12:
        f4 += af[j1];
        j1++;
        break MISSING_BLOCK_LABEL_1398;
          goto _L13
        ai2[i4 - 1] = ai2[l1 - 1];
        af1[i4 - 1] = f4;
        ai4[i4 - 1] = l2;
        k5 = i4;
        int i6 = l;
        int i3 = 0;
        int k2 = 0;
        int j4 = 0;
        int i2 = 0;
        int l3 = 0;
        j1 = 0;
        int j6 = l;
        int k6 = 0;
        do
        {
            if(j6 >= k1)
                break;
            int l6 = ai[k6];
            fontmetricsint.top = ai1[l3 * 4 + 0];
            fontmetricsint.bottom = ai1[l3 * 4 + 1];
            fontmetricsint.ascent = ai1[l3 * 4 + 2];
            fontmetricsint.descent = ai1[l3 * 4 + 3];
            int i7 = l3 + 1;
            l3 = i3;
            if(fontmetricsint.top < i3)
                l3 = fontmetricsint.top;
            int i5 = j4;
            if(fontmetricsint.ascent < j4)
                i5 = fontmetricsint.ascent;
            int j7 = i2;
            if(fontmetricsint.descent > i2)
                j7 = fontmetricsint.descent;
            int l7 = k2;
            int j8 = j1;
            if(fontmetricsint.bottom > k2)
            {
                l7 = fontmetricsint.bottom;
                j8 = j1;
            }
            int l8;
            int i9;
            do
            {
                l8 = i6;
                j4 = i5;
                i2 = j7;
                i3 = l3;
                k2 = l7;
                i9 = i1;
                j1 = j8;
                if(j8 >= k5)
                    break;
                l8 = i6;
                j4 = i5;
                i2 = j7;
                i3 = l3;
                k2 = l7;
                i9 = i1;
                j1 = j8;
                if(ai2[j8] + l >= j6)
                    break;
                j8++;
            } while(true);
            while(j1 < k5 && ai2[j1] + l <= l6) 
            {
                int i8 = l + ai2[j1];
                int j5;
                boolean flag5;
                int k7;
                int k8;
                if(i8 < j)
                    flag5 = true;
                else
                    flag5 = false;
                k8 = out(charsequence, l8, i8, j4, i2, i3, k2, i9, f, f1, alineheightspan, ai3, fontmetricsint, ai4[j1], flag2, abyte0, k4, flag3, j, flag, flag1, ac, af, l, truncateat, f2, af1[j1], textpaint, flag5);
                if(i8 < l6)
                {
                    l3 = fontmetricsint.top;
                    i1 = fontmetricsint.bottom;
                    k7 = fontmetricsint.ascent;
                    j5 = fontmetricsint.descent;
                } else
                {
                    j5 = 0;
                    k7 = 0;
                    i1 = 0;
                    l3 = 0;
                }
                j6 = j1 + 1;
                l8 = i8;
                j4 = k7;
                i2 = j5;
                i3 = l3;
                k2 = i1;
                i9 = k8;
                j1 = j6;
                if(mLineCount >= mMaximumVisibleLineCount)
                {
                    l8 = i8;
                    j4 = k7;
                    i2 = j5;
                    i3 = l3;
                    k2 = i1;
                    i9 = k8;
                    j1 = j6;
                    if(mEllipsized)
                        return;
                }
            }
            j6 = l6;
            k6++;
            i6 = l8;
            i1 = i9;
            l3 = i7;
        } while(true);
        if(k1 != j)
            break MISSING_BLOCK_LABEL_2165;
        j1 = i1;
        if((j == i || charsequence.charAt(j - 1) == '\n') && mLineCount < mMaximumVisibleLineCount)
        {
            measuredtext.setPara(charsequence, j, j, textdirectionheuristic, builder);
            textpaint.getFontMetricsInt(fontmetricsint);
            out(charsequence, j, j, fontmetricsint.ascent, fontmetricsint.descent, fontmetricsint.top, fontmetricsint.bottom, j1, f, f1, null, null, fontmetricsint, 0, flag2, measuredtext.mLevels, measuredtext.mDir, measuredtext.mEasy, j, flag, flag1, null, null, i, truncateat, f2, 0.0F, textpaint, false);
        }
        return;
        l = k1;
        ai2 = ai3;
        if(true) goto _L15; else goto _L14
_L14:
    }

    public int getBottomPadding()
    {
        return mBottomPadding;
    }

    public int getEllipsisCount(int i)
    {
        if(mColumns < 6)
            return 0;
        else
            return mLines[mColumns * i + 5];
    }

    public int getEllipsisStart(int i)
    {
        if(mColumns < 6)
            return 0;
        else
            return mLines[mColumns * i + 4];
    }

    public int getEllipsizedWidth()
    {
        return mEllipsizedWidth;
    }

    public int getHeight(boolean flag)
    {
        if(flag && mLineCount >= mMaximumVisibleLineCount && mMaxLineHeight == -1 && Log.isLoggable("StaticLayout", 5))
            Log.w("StaticLayout", (new StringBuilder()).append("maxLineHeight should not be -1.  maxLines:").append(mMaximumVisibleLineCount).append(" lineCount:").append(mLineCount).toString());
        int i;
        if(flag && mLineCount >= mMaximumVisibleLineCount && mMaxLineHeight != -1)
            i = mMaxLineHeight;
        else
            i = super.getHeight();
        return i;
    }

    public int getHyphen(int i)
    {
        return mLines[mColumns * i + 3] & 0xff;
    }

    public int getIndentAdjust(int i, Layout.Alignment alignment)
    {
        if(alignment == Layout.Alignment.ALIGN_LEFT)
            if(mLeftIndents == null)
                return 0;
            else
                return mLeftIndents[Math.min(i, mLeftIndents.length - 1)];
        if(alignment == Layout.Alignment.ALIGN_RIGHT)
            if(mRightIndents == null)
                return 0;
            else
                return -mRightIndents[Math.min(i, mRightIndents.length - 1)];
        if(alignment == Layout.Alignment.ALIGN_CENTER)
        {
            int j = 0;
            if(mLeftIndents != null)
                j = mLeftIndents[Math.min(i, mLeftIndents.length - 1)];
            int k = 0;
            if(mRightIndents != null)
                k = mRightIndents[Math.min(i, mRightIndents.length - 1)];
            return j - k >> 1;
        } else
        {
            throw new AssertionError((new StringBuilder()).append("unhandled alignment ").append(alignment).toString());
        }
    }

    public boolean getLineContainsTab(int i)
    {
        boolean flag = false;
        if((mLines[mColumns * i + 0] & 0x20000000) != 0)
            flag = true;
        return flag;
    }

    public int getLineCount()
    {
        return mLineCount;
    }

    public int getLineDescent(int i)
    {
        return mLines[mColumns * i + 2];
    }

    public final Layout.Directions getLineDirections(int i)
    {
        return mLineDirections[i];
    }

    public int getLineForVertical(int i)
    {
        int j = mLineCount;
        int k = -1;
        int ai[] = mLines;
        while(j - k > 1) 
        {
            int l = j + k >> 1;
            if(ai[mColumns * l + 1] > i)
                j = l;
            else
                k = l;
        }
        if(k < 0)
            return 0;
        else
            return k;
    }

    public int getLineStart(int i)
    {
        return mLines[mColumns * i + 0] & 0x1fffffff;
    }

    public int getLineTop(int i)
    {
        return mLines[mColumns * i + 1];
    }

    public int getParagraphDirection(int i)
    {
        return mLines[mColumns * i + 0] >> 30;
    }

    public int getTopPadding()
    {
        return mTopPadding;
    }

    private static final char CHAR_NEW_LINE = 10;
    private static final int COLUMNS_ELLIPSIZE = 6;
    private static final int COLUMNS_NORMAL = 4;
    private static final int DEFAULT_MAX_LINE_HEIGHT = -1;
    private static final int DESCENT = 2;
    private static final int DIR = 0;
    private static final int DIR_SHIFT = 30;
    private static final int ELLIPSIS_COUNT = 5;
    private static final int ELLIPSIS_START = 4;
    private static final double EXTRA_ROUNDING = 0.5D;
    private static final int HYPHEN = 3;
    private static final int HYPHEN_MASK = 255;
    private static final int START = 0;
    private static final int START_MASK = 0x1fffffff;
    private static final int TAB = 0;
    private static final int TAB_INCREMENT = 20;
    private static final int TAB_MASK = 0x20000000;
    static final String TAG = "StaticLayout";
    private static final int TOP = 1;
    private int mBottomPadding;
    private int mColumns;
    private boolean mEllipsized;
    private int mEllipsizedWidth;
    private int mLeftIndents[];
    private int mLineCount;
    private Layout.Directions mLineDirections[];
    private int mLines[];
    private int mMaxLineHeight;
    private int mMaximumVisibleLineCount;
    private int mRightIndents[];
    private int mTopPadding;
}
