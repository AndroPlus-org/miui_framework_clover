// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import com.android.internal.util.ArrayUtils;

// Referenced classes of package android.text:
//            TextPaint, TextUtils, Spanned, TextDirectionHeuristics, 
//            AndroidBidi, TextDirectionHeuristic

class MeasuredText
{

    private MeasuredText()
    {
        mWorkPaint = new TextPaint();
    }

    static MeasuredText obtain()
    {
        Object aobj[] = sLock;
        aobj;
        JVM INSTR monitorenter ;
        int i = sCached.length;
_L2:
        int j;
        j = i - 1;
        if(j < 0)
            break MISSING_BLOCK_LABEL_46;
        i = j;
        if(sCached[j] == null) goto _L2; else goto _L1
_L1:
        MeasuredText measuredtext;
        measuredtext = sCached[j];
        sCached[j] = null;
        aobj;
        JVM INSTR monitorexit ;
        return measuredtext;
        return new MeasuredText();
        Exception exception;
        exception;
        throw exception;
    }

    static MeasuredText recycle(MeasuredText measuredtext)
    {
        measuredtext.finish();
        Object aobj[] = sLock;
        aobj;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if(i < sCached.length)
        {
            if(sCached[i] != null)
                break MISSING_BLOCK_LABEL_43;
            sCached[i] = measuredtext;
            measuredtext.mText = null;
        }
        aobj;
        JVM INSTR monitorexit ;
        return null;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        measuredtext;
        throw measuredtext;
    }

    float addStyleRun(TextPaint textpaint, int i, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        if(fontmetricsint != null)
            textpaint.getFontMetricsInt(fontmetricsint);
        int j = mPos;
        mPos = j + i;
        fontmetricsint = null;
        if(mBuilder == null || textpaint.getClass() != android/text/TextPaint)
            fontmetricsint = mWidths;
        if(mEasy)
        {
            boolean flag;
            float f2;
            if(mDir != 1)
                flag = true;
            else
                flag = false;
            if(fontmetricsint != null)
            {
                float f = textpaint.getTextRunAdvances(mChars, j, i, j, i, flag, fontmetricsint, j);
                f2 = f;
                if(mBuilder != null)
                {
                    mBuilder.addMeasuredRun(j, j + i, fontmetricsint);
                    f2 = f;
                }
            } else
            {
                f2 = mBuilder.addStyleRun(textpaint, j, j + i, flag);
            }
            return f2;
        }
        float f3 = 0.0F;
        byte byte0 = mLevels[j];
        int l = j;
        int i1 = j + 1;
        int j1 = j + i;
        i = i1;
        i1 = l;
        do
        {
            int k;
            float f1;
            byte byte1;
label0:
            {
                if(i != j1)
                {
                    k = i1;
                    byte1 = byte0;
                    f1 = f3;
                    if(mLevels[i] == byte0)
                        break label0;
                }
                boolean flag1;
                if((byte0 & 1) != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(fontmetricsint != null)
                {
                    f1 = f3 + textpaint.getTextRunAdvances(mChars, i1, i - i1, i1, i - i1, flag1, fontmetricsint, i1);
                    f3 = f1;
                    if(mBuilder != null)
                    {
                        mBuilder.addMeasuredRun(i1, i, fontmetricsint);
                        f3 = f1;
                    }
                } else
                {
                    f3 += mBuilder.addStyleRun(textpaint, i1, i, flag1);
                }
                if(i == j1)
                    return f3;
                k = i;
                byte1 = mLevels[i];
                f1 = f3;
            }
            i++;
            i1 = k;
            byte0 = byte1;
            f3 = f1;
        } while(true);
    }

    float addStyleRun(TextPaint textpaint, MetricAffectingSpan ametricaffectingspan[], int i, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        TextPaint textpaint1 = mWorkPaint;
        textpaint1.set(textpaint);
        textpaint1.baselineShift = 0;
        textpaint = null;
        int j = 0;
        while(j < ametricaffectingspan.length) 
        {
            MetricAffectingSpan metricaffectingspan = ametricaffectingspan[j];
            if(metricaffectingspan instanceof ReplacementSpan)
                textpaint = (ReplacementSpan)metricaffectingspan;
            else
                metricaffectingspan.updateMeasureState(textpaint1);
            j++;
        }
        float f;
        if(textpaint == null)
        {
            f = addStyleRun(textpaint1, i, fontmetricsint);
        } else
        {
            f = textpaint.getSize(textpaint1, mText, mTextStart + mPos, mTextStart + mPos + i, fontmetricsint);
            if(mBuilder == null)
            {
                textpaint = mWidths;
                textpaint[mPos] = f;
                int k = mPos + 1;
                for(int l = mPos; k < l + i; k++)
                    textpaint[k] = 0.0F;

            } else
            {
                mBuilder.addReplacementRun(mPos, mPos + i, f);
            }
            mPos = mPos + i;
        }
        if(fontmetricsint != null)
            if(textpaint1.baselineShift < 0)
            {
                fontmetricsint.ascent = fontmetricsint.ascent + textpaint1.baselineShift;
                fontmetricsint.top = fontmetricsint.top + textpaint1.baselineShift;
            } else
            {
                fontmetricsint.descent = fontmetricsint.descent + textpaint1.baselineShift;
                fontmetricsint.bottom = fontmetricsint.bottom + textpaint1.baselineShift;
            }
        return f;
    }

    int breakText(int i, boolean flag, float f)
    {
        float af[];
label0:
        {
            af = mWidths;
            if(!flag)
                break label0;
            int j = 0;
            int l;
label1:
            do
            {
label2:
                {
                    l = j;
                    if(j < i)
                    {
                        f -= af[j];
                        if(f >= 0.0F)
                            break label2;
                    }
                    for(l = j; l > 0 && mChars[l - 1] == ' '; l--);
                    break label1;
                }
                j++;
            } while(true);
            return l;
        }
        int k = i - 1;
        int i1;
label3:
        do
        {
label4:
            {
                i1 = k;
                if(k >= 0)
                {
                    f -= af[k];
                    if(f >= 0.0F)
                        break label4;
                }
                for(i1 = k; i1 < i - 1 && (mChars[i1 + 1] == ' ' || af[i1 + 1] == 0.0F); i1++);
                break label3;
            }
            k--;
        } while(true);
        return i - i1 - 1;
    }

    void finish()
    {
        mText = null;
        mBuilder = null;
        if(mLen > 1000)
        {
            mWidths = null;
            mChars = null;
            mLevels = null;
        }
    }

    float measure(int i, int j)
    {
        float f = 0.0F;
        float af[] = mWidths;
        for(; i < j; i++)
            f += af[i];

        return f;
    }

    void setPara(CharSequence charsequence, int i, int j, TextDirectionHeuristic textdirectionheuristic, StaticLayout.Builder builder)
    {
        int k;
        mBuilder = builder;
        mText = charsequence;
        mTextStart = i;
        k = j - i;
        mLen = k;
        mPos = 0;
        if(mWidths == null || mWidths.length < k)
            mWidths = ArrayUtils.newUnpaddedFloatArray(k);
        if(mChars == null || mChars.length < k)
            mChars = ArrayUtils.newUnpaddedCharArray(k);
        TextUtils.getChars(charsequence, i, j, mChars, 0);
        if(charsequence instanceof Spanned)
        {
            builder = (Spanned)charsequence;
            charsequence = (ReplacementSpan[])builder.getSpans(i, j, android/text/style/ReplacementSpan);
            for(int l = 0; l < charsequence.length; l++)
            {
                int i1 = builder.getSpanStart(charsequence[l]) - i;
                int j1 = builder.getSpanEnd(charsequence[l]) - i;
                j = i1;
                if(i1 < 0)
                    j = 0;
                i1 = j1;
                if(j1 > k)
                    i1 = k;
                for(; j < i1; j++)
                    mChars[j] = (char)65532;

            }

        }
        if(textdirectionheuristic != TextDirectionHeuristics.LTR && textdirectionheuristic != TextDirectionHeuristics.FIRSTSTRONG_LTR && textdirectionheuristic != TextDirectionHeuristics.ANYRTL_LTR || !TextUtils.doesNotNeedBidi(mChars, 0, k)) goto _L2; else goto _L1
_L1:
        mDir = 1;
        mEasy = true;
_L4:
        return;
_L2:
        if(mLevels == null || mLevels.length < k)
            mLevels = ArrayUtils.newUnpaddedByteArray(k);
        if(textdirectionheuristic != TextDirectionHeuristics.LTR)
            break; /* Loop/switch isn't completed */
        i = 1;
_L5:
        mDir = AndroidBidi.bidi(i, mChars, mLevels, k, false);
        mEasy = false;
        if(true) goto _L4; else goto _L3
_L3:
        if(textdirectionheuristic == TextDirectionHeuristics.RTL)
            i = -1;
        else
        if(textdirectionheuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR)
            i = 2;
        else
        if(textdirectionheuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL)
            i = -2;
        else
        if(textdirectionheuristic.isRtl(mChars, 0, k))
            i = -1;
        else
            i = 1;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    void setPos(int i)
    {
        mPos = i - mTextStart;
    }

    private static final boolean localLOGV = false;
    private static final MeasuredText sCached[] = new MeasuredText[3];
    private static final Object sLock[] = new Object[0];
    private StaticLayout.Builder mBuilder;
    char mChars[];
    int mDir;
    boolean mEasy;
    int mLen;
    byte mLevels[];
    private int mPos;
    CharSequence mText;
    int mTextStart;
    float mWidths[];
    private TextPaint mWorkPaint;

}
