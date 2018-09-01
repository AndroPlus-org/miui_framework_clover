// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;

// Referenced classes of package android.text:
//            TextPaint, SpanSet, TextUtils, Spanned, 
//            Layout

class TextLine
{
    private static final class DecorationInfo
    {

        public DecorationInfo copyInfo()
        {
            DecorationInfo decorationinfo = new DecorationInfo();
            decorationinfo.isStrikeThruText = isStrikeThruText;
            decorationinfo.isUnderlineText = isUnderlineText;
            decorationinfo.underlineColor = underlineColor;
            decorationinfo.underlineThickness = underlineThickness;
            return decorationinfo;
        }

        public boolean hasDecoration()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(!isStrikeThruText)
            {
                flag1 = flag;
                if(!isUnderlineText)
                    if(underlineColor != 0)
                        flag1 = flag;
                    else
                        flag1 = false;
            }
            return flag1;
        }

        public int end;
        public boolean isStrikeThruText;
        public boolean isUnderlineText;
        public int start;
        public int underlineColor;
        public float underlineThickness;

        private DecorationInfo()
        {
            start = -1;
            end = -1;
        }

        DecorationInfo(DecorationInfo decorationinfo)
        {
            this();
        }
    }


    TextLine()
    {
    }

    private int adjustHyphenEdit(int i, int j, int k)
    {
        int l = k;
        if(i > 0)
            l = k & 0xffffffe7;
        i = l;
        if(j < mLen)
            i = l & -8;
        return i;
    }

    private int countStretchableSpaces(int i, int j)
    {
        int k = 0;
        for(; i < j; i = nextStretchableSpace(i + 1, j))
            k++;

        return k;
    }

    private float drawRun(Canvas canvas, int i, int j, boolean flag, float f, int k, int l, 
            int i1, boolean flag1)
    {
        boolean flag2;
        if(mDir == 1)
            flag2 = true;
        else
            flag2 = false;
        if(flag2 == flag)
        {
            float f1 = -measureRun(i, j, j, flag, null);
            handleRun(i, j, j, flag, canvas, f + f1, k, l, i1, null, false);
            return f1;
        } else
        {
            return handleRun(i, j, j, flag, canvas, f, k, l, i1, null, flag1);
        }
    }

    private static void drawStroke(TextPaint textpaint, Canvas canvas, int i, float f, float f1, float f2, float f3, float f4)
    {
        f = (float)textpaint.baselineShift + f4 + f;
        int j = textpaint.getColor();
        android.graphics.Paint.Style style = textpaint.getStyle();
        boolean flag = textpaint.isAntiAlias();
        textpaint.setStyle(android.graphics.Paint.Style.FILL);
        textpaint.setAntiAlias(true);
        textpaint.setColor(i);
        canvas.drawRect(f2, f, f3, f + f1, textpaint);
        textpaint.setStyle(style);
        textpaint.setColor(j);
        textpaint.setAntiAlias(flag);
    }

    private void drawTextRun(Canvas canvas, TextPaint textpaint, int i, int j, int k, int l, boolean flag, 
            float f, int i1)
    {
        if(mCharsValid)
        {
            canvas.drawTextRun(mChars, i, j - i, k, l - k, f, i1, flag, textpaint);
        } else
        {
            int j1 = mStart;
            canvas.drawTextRun(mText, j1 + i, j1 + j, j1 + k, j1 + l, f, i1, flag, textpaint);
        }
    }

    private static void expandMetricsFromPaint(android.graphics.Paint.FontMetricsInt fontmetricsint, TextPaint textpaint)
    {
        int i = fontmetricsint.top;
        int j = fontmetricsint.ascent;
        int k = fontmetricsint.descent;
        int l = fontmetricsint.bottom;
        int i1 = fontmetricsint.leading;
        textpaint.getFontMetricsInt(fontmetricsint);
        updateMetrics(fontmetricsint, i, j, k, l, i1);
    }

    private void extractDecorationInfo(TextPaint textpaint, DecorationInfo decorationinfo)
    {
        decorationinfo.isStrikeThruText = textpaint.isStrikeThruText();
        if(decorationinfo.isStrikeThruText)
            textpaint.setStrikeThruText(false);
        decorationinfo.isUnderlineText = textpaint.isUnderlineText();
        if(decorationinfo.isUnderlineText)
            textpaint.setUnderlineText(false);
        decorationinfo.underlineColor = textpaint.underlineColor;
        decorationinfo.underlineThickness = textpaint.underlineThickness;
        textpaint.setUnderlineText(0, 0.0F);
    }

    private int getOffsetBeforeAfter(int i, int j, int k, boolean flag, int l, boolean flag1)
    {
        TextPaint textpaint;
label0:
        {
            if(i >= 0)
            {
                if(flag1)
                    i = mLen;
                else
                    i = 0;
                if(l != i)
                    break label0;
            }
            if(flag1)
                return TextUtils.getOffsetAfter(mText, mStart + l) - mStart;
            else
                return TextUtils.getOffsetBefore(mText, mStart + l) - mStart;
        }
        textpaint = mWorkPaint;
        textpaint.set(mPaint);
        textpaint.setWordSpacing(mAddedWidth);
        i = j;
        if(mSpanned != null) goto _L2; else goto _L1
_L1:
        int i1 = i;
_L3:
        int j1;
        MetricAffectingSpan ametricaffectingspan[];
        ReplacementSpan replacementspan;
        MetricAffectingSpan metricaffectingspan;
        if(flag)
            i = 1;
        else
            i = 0;
        if(flag1)
            j = 0;
        else
            j = 2;
        if(mCharsValid)
            return textpaint.getTextRunCursor(mChars, i1, k - i1, i, l, j);
        else
            return textpaint.getTextRunCursor(mText, mStart + i1, mStart + k, i, mStart + l, j) - mStart;
_L2:
        if(flag1)
            i1 = l + 1;
        else
            i1 = l;
        j1 = mStart;
_L4:
label1:
        {
            j = mSpanned.nextSpanTransition(mStart + i, j1 + k, android/text/style/MetricAffectingSpan) - mStart;
            if(j < i1)
                break label1;
            ametricaffectingspan = (MetricAffectingSpan[])TextUtils.removeEmptySpans((MetricAffectingSpan[])mSpanned.getSpans(mStart + i, mStart + j, android/text/style/MetricAffectingSpan), mSpanned, android/text/style/MetricAffectingSpan);
            i1 = i;
            k = j;
            if(ametricaffectingspan.length > 0)
            {
                replacementspan = null;
                k = 0;
                while(k < ametricaffectingspan.length) 
                {
                    metricaffectingspan = ametricaffectingspan[k];
                    if(metricaffectingspan instanceof ReplacementSpan)
                        replacementspan = (ReplacementSpan)metricaffectingspan;
                    else
                        metricaffectingspan.updateMeasureState(textpaint);
                    k++;
                }
                break MISSING_BLOCK_LABEL_309;
            }
        }
          goto _L3
        i = j;
          goto _L4
        i1 = i;
        k = j;
        if(replacementspan != null)
        {
            if(flag1)
                i = j;
            return i;
        }
          goto _L3
    }

    private float getRunAdvance(TextPaint textpaint, int i, int j, int k, int l, boolean flag, int i1)
    {
        if(mCharsValid)
        {
            return textpaint.getRunAdvance(mChars, i, j, k, l, flag, i1);
        } else
        {
            int j1 = mStart;
            return textpaint.getRunAdvance(mText, j1 + i, j1 + j, j1 + k, j1 + l, flag, j1 + i1);
        }
    }

    private float handleReplacement(ReplacementSpan replacementspan, TextPaint textpaint, int i, int j, boolean flag, Canvas canvas, float f, 
            int k, int l, int i1, android.graphics.Paint.FontMetricsInt fontmetricsint, boolean flag1)
    {
label0:
        {
            float f1 = 0.0F;
            int j1 = mStart + i;
            int k1 = mStart + j;
            float f2;
            if(!flag1)
            {
                f2 = f1;
                if(canvas == null)
                    break label0;
                f2 = f1;
                if(!flag)
                    break label0;
            }
            int l1 = 0;
            int i2 = 0;
            int j2 = 0;
            j = 0;
            int k2 = 0;
            if(fontmetricsint != null)
                i = 1;
            else
                i = 0;
            if(i != 0)
            {
                l1 = fontmetricsint.top;
                i2 = fontmetricsint.ascent;
                j2 = fontmetricsint.descent;
                j = fontmetricsint.bottom;
                k2 = fontmetricsint.leading;
            }
            f1 = replacementspan.getSize(textpaint, mText, j1, k1, fontmetricsint);
            f2 = f1;
            if(i != 0)
            {
                updateMetrics(fontmetricsint, l1, i2, j2, j, k2);
                f2 = f1;
            }
        }
        if(canvas != null)
        {
            f1 = f;
            if(flag)
                f1 = f - f2;
            replacementspan.draw(canvas, mText, j1, k1, f1, k, l, i1, textpaint);
        }
        f = f2;
        if(flag)
            f = -f2;
        return f;
    }

    private float handleRun(int i, int j, int k, boolean flag, Canvas canvas, float f, int l, 
            int i1, int j1, android.graphics.Paint.FontMetricsInt fontmetricsint, boolean flag1)
    {
        if(j < i || j > k)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("measureLimit (").append(j).append(") is out of ").append("start (").append(i).append(") and limit (").append(k).append(") bounds").toString());
        if(i == j)
        {
            canvas = mWorkPaint;
            canvas.set(mPaint);
            if(fontmetricsint != null)
                expandMetricsFromPaint(fontmetricsint, canvas);
            return 0.0F;
        }
        boolean flag2;
        if(mSpanned == null)
        {
            flag2 = false;
        } else
        {
            mMetricAffectingSpanSpanSet.init(mSpanned, mStart + i, mStart + k);
            mCharacterStyleSpanSet.init(mSpanned, mStart + i, mStart + k);
            if(mMetricAffectingSpanSpanSet.numberOfSpans == 0)
            {
                if(mCharacterStyleSpanSet.numberOfSpans != 0)
                    flag2 = true;
                else
                    flag2 = false;
            } else
            {
                flag2 = true;
            }
        }
        if(!flag2)
        {
            TextPaint textpaint = mWorkPaint;
            textpaint.set(mPaint);
            textpaint.setHyphenEdit(adjustHyphenEdit(i, k, textpaint.getHyphenEdit()));
            return handleText(textpaint, i, k, i, k, flag, canvas, f, l, i1, j1, fontmetricsint, flag1, j, null);
        }
        float f1 = f;
        while(i < j) 
        {
            TextPaint textpaint1 = mWorkPaint;
            textpaint1.set(mPaint);
            int i2 = mMetricAffectingSpanSpanSet.getNextTransition(mStart + i, mStart + k) - mStart;
            int j2 = Math.min(i2, j);
            ReplacementSpan replacementspan = null;
            int k1 = 0;
            while(k1 < mMetricAffectingSpanSpanSet.numberOfSpans) 
            {
                Object obj = replacementspan;
                if(mMetricAffectingSpanSpanSet.spanStarts[k1] < mStart + j2)
                    if(mMetricAffectingSpanSpanSet.spanEnds[k1] <= mStart + i)
                    {
                        obj = replacementspan;
                    } else
                    {
                        obj = ((MetricAffectingSpan[])mMetricAffectingSpanSpanSet.spans)[k1];
                        if(obj instanceof ReplacementSpan)
                        {
                            obj = (ReplacementSpan)obj;
                        } else
                        {
                            ((MetricAffectingSpan) (obj)).updateDrawState(textpaint1);
                            obj = replacementspan;
                        }
                    }
                k1++;
                replacementspan = ((ReplacementSpan) (obj));
            }
            if(replacementspan != null)
            {
                boolean flag3;
                if(flag1 || j2 < j)
                    flag3 = true;
                else
                    flag3 = false;
                f1 += handleReplacement(replacementspan, textpaint1, i, j2, flag, canvas, f1, l, i1, j1, fontmetricsint, flag3);
            } else
            {
                TextPaint textpaint2 = mActivePaint;
                textpaint2.set(mPaint);
                int k2 = i;
                int l2 = j2;
                DecorationInfo decorationinfo1 = mDecorationInfo;
                mDecorations.clear();
                int l1 = i;
                float f2 = f1;
                while(l1 < j2) 
                {
                    int i3 = mCharacterStyleSpanSet.getNextTransition(mStart + l1, mStart + i2) - mStart;
                    int j3 = Math.min(i3, j2);
                    textpaint1.set(mPaint);
                    int k3 = 0;
                    while(k3 < mCharacterStyleSpanSet.numberOfSpans) 
                    {
                        if(mCharacterStyleSpanSet.spanStarts[k3] < mStart + j3 && mCharacterStyleSpanSet.spanEnds[k3] > mStart + l1)
                            ((CharacterStyle[])mCharacterStyleSpanSet.spans)[k3].updateDrawState(textpaint1);
                        k3++;
                    }
                    extractDecorationInfo(textpaint1, decorationinfo1);
                    if(l1 == i)
                    {
                        textpaint2.set(textpaint1);
                        f1 = f2;
                        k3 = k2;
                    } else
                    {
                        k3 = k2;
                        f1 = f2;
                        if(!textpaint1.hasEqualAttributes(textpaint2))
                        {
                            textpaint2.setHyphenEdit(adjustHyphenEdit(k2, l2, mPaint.getHyphenEdit()));
                            boolean flag4;
                            if(flag1 || l2 < j)
                                flag4 = true;
                            else
                                flag4 = false;
                            f1 = f2 + handleText(textpaint2, k2, l2, i, i2, flag, canvas, f2, l, i1, j1, fontmetricsint, flag4, Math.min(l2, j2), mDecorations);
                            k3 = l1;
                            textpaint2.set(textpaint1);
                            mDecorations.clear();
                        }
                    }
                    l2 = i3;
                    if(decorationinfo1.hasDecoration())
                    {
                        DecorationInfo decorationinfo = decorationinfo1.copyInfo();
                        decorationinfo.start = l1;
                        decorationinfo.end = i3;
                        mDecorations.add(decorationinfo);
                    }
                    l1 = i3;
                    k2 = k3;
                    f2 = f1;
                }
                textpaint2.setHyphenEdit(adjustHyphenEdit(k2, l2, mPaint.getHyphenEdit()));
                boolean flag5;
                if(flag1 || l2 < j)
                    flag5 = true;
                else
                    flag5 = false;
                f1 = f2 + handleText(textpaint2, k2, l2, i, i2, flag, canvas, f2, l, i1, j1, fontmetricsint, flag5, Math.min(l2, j2), mDecorations);
            }
            i = i2;
        }
        return f1 - f;
    }

    private float handleText(TextPaint textpaint, int i, int j, int k, int l, boolean flag, Canvas canvas, 
            float f, int i1, int j1, int k1, android.graphics.Paint.FontMetricsInt fontmetricsint, boolean flag1, int l1, 
            ArrayList arraylist)
    {
        float f1;
        float f2;
        textpaint.setWordSpacing(mAddedWidth);
        if(fontmetricsint != null)
            expandMetricsFromPaint(fontmetricsint, textpaint);
        if(j == i)
            return 0.0F;
        f1 = 0.0F;
        int i2;
        int j2;
        if(arraylist == null)
            i2 = 0;
        else
            i2 = arraylist.size();
        if(flag1) goto _L2; else goto _L1
_L1:
        f2 = f1;
        if(canvas == null) goto _L4; else goto _L3
_L3:
        if(textpaint.bgColor == 0 && i2 == 0) goto _L5; else goto _L2
_L2:
        f2 = getRunAdvance(textpaint, i, j, k, l, flag, l1);
          goto _L4
_L5:
        f2 = f1;
        if(!flag) goto _L4; else goto _L2
_L4:
        if(canvas == null)
            break MISSING_BLOCK_LABEL_462;
        if(flag)
        {
            f1 = f - f2;
        } else
        {
            f1 = f;
            f += f2;
        }
        if(textpaint.bgColor != 0)
        {
            j2 = textpaint.getColor();
            fontmetricsint = textpaint.getStyle();
            textpaint.setColor(textpaint.bgColor);
            textpaint.setStyle(android.graphics.Paint.Style.FILL);
            canvas.drawRect(f1, i1, f, k1, textpaint);
            textpaint.setStyle(fontmetricsint);
            textpaint.setColor(j2);
        }
        if(i2 != 0)
        {
            i1 = 0;
            while(i1 < i2) 
            {
                fontmetricsint = (DecorationInfo)arraylist.get(i1);
                j2 = Math.max(((DecorationInfo) (fontmetricsint)).start, i);
                k1 = Math.min(((DecorationInfo) (fontmetricsint)).end, l1);
                float f3 = getRunAdvance(textpaint, i, j, k, l, flag, j2);
                float f4 = getRunAdvance(textpaint, i, j, k, l, flag, k1);
                float f5;
                if(flag)
                {
                    f5 = f - f4;
                    f3 = f - f3;
                } else
                {
                    f5 = f1 + f3;
                    f3 = f1 + f4;
                }
                if(((DecorationInfo) (fontmetricsint)).underlineColor != 0)
                    drawStroke(textpaint, canvas, ((DecorationInfo) (fontmetricsint)).underlineColor, textpaint.getUnderlinePosition(), ((DecorationInfo) (fontmetricsint)).underlineThickness, f5, f3, j1);
                if(((DecorationInfo) (fontmetricsint)).isUnderlineText)
                {
                    f4 = Math.max(textpaint.getUnderlineThickness(), 1.0F);
                    drawStroke(textpaint, canvas, textpaint.getColor(), textpaint.getUnderlinePosition(), f4, f5, f3, j1);
                }
                if(((DecorationInfo) (fontmetricsint)).isStrikeThruText)
                {
                    f4 = Math.max(textpaint.getStrikeThruThickness(), 1.0F);
                    drawStroke(textpaint, canvas, textpaint.getColor(), textpaint.getStrikeThruPosition(), f4, f5, f3, j1);
                }
                i1++;
            }
        }
        drawTextRun(canvas, textpaint, i, j, k, l, flag, f1, j1 + textpaint.baselineShift);
        f = f2;
        if(flag)
            f = -f2;
        return f;
    }

    public static boolean isLineEndSpace(char c)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(c == ' ') goto _L2; else goto _L1
_L1:
        if(c != '\t') goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(c == '\u1680')
            continue; /* Loop/switch isn't completed */
        if('\u2000' <= c && c <= '\u200A')
        {
            flag1 = flag;
            if(c != '\u2007')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = flag;
        if(c != '\u205F')
        {
            flag1 = flag;
            if(c != '\u3000')
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean isStretchableWhitespace(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 32)
            if(i == 160)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private float measureRun(int i, int j, int k, boolean flag, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        return handleRun(i, j, k, flag, null, 0.0F, 0, 0, 0, fontmetricsint, true);
    }

    private int nextStretchableSpace(int i, int j)
    {
        char c;
        for(; i < j; i++)
        {
            if(mCharsValid)
                c = mChars[i];
            else
                c = mText.charAt(mStart + i);
            if(isStretchableWhitespace(c))
                return i;
        }

        return j;
    }

    static TextLine obtain()
    {
        TextLine atextline[] = sCached;
        atextline;
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
        TextLine textline;
        textline = sCached[j];
        sCached[j] = null;
        atextline;
        JVM INSTR monitorexit ;
        return textline;
        return new TextLine();
        Exception exception;
        exception;
        throw exception;
    }

    static TextLine recycle(TextLine textline)
    {
        textline.mText = null;
        textline.mPaint = null;
        textline.mDirections = null;
        textline.mSpanned = null;
        textline.mTabs = null;
        textline.mChars = null;
        textline.mMetricAffectingSpanSpanSet.recycle();
        textline.mCharacterStyleSpanSet.recycle();
        textline.mReplacementSpanSpanSet.recycle();
        TextLine atextline[] = sCached;
        atextline;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if(i < sCached.length)
        {
            if(sCached[i] != null)
                break MISSING_BLOCK_LABEL_85;
            sCached[i] = textline;
        }
        atextline;
        JVM INSTR monitorexit ;
        return null;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        textline;
        throw textline;
    }

    static void updateMetrics(android.graphics.Paint.FontMetricsInt fontmetricsint, int i, int j, int k, int l, int i1)
    {
        fontmetricsint.top = Math.min(fontmetricsint.top, i);
        fontmetricsint.ascent = Math.min(fontmetricsint.ascent, j);
        fontmetricsint.descent = Math.max(fontmetricsint.descent, k);
        fontmetricsint.bottom = Math.max(fontmetricsint.bottom, l);
        fontmetricsint.leading = Math.max(fontmetricsint.leading, i1);
    }

    void draw(Canvas canvas, float f, int i, int j, int k)
    {
        float f1;
        int ai[];
        int l;
        int i1;
        if(!mHasTabs)
        {
            if(mDirections == Layout.DIRS_ALL_LEFT_TO_RIGHT)
            {
                drawRun(canvas, 0, mLen, false, f, i, j, k, false);
                return;
            }
            if(mDirections == Layout.DIRS_ALL_RIGHT_TO_LEFT)
            {
                drawRun(canvas, 0, mLen, true, f, i, j, k, false);
                return;
            }
        }
        f1 = 0.0F;
        ai = mDirections.mDirections;
        l = ai.length;
        i1 = 0;
_L7:
        int j1;
        int k1;
        int l1;
        boolean flag;
        int i2;
        int j2;
        int k2;
        float f2;
        if(i1 >= ai.length)
            break; /* Loop/switch isn't completed */
        j1 = ai[i1];
        k1 = j1 + (ai[i1 + 1] & 0x3ffffff);
        l1 = k1;
        if(k1 > mLen)
            l1 = mLen;
        if((ai[i1 + 1] & 0x4000000) != 0)
            flag = true;
        else
            flag = false;
        i2 = j1;
        if(!mHasTabs)
            j1 = l1;
_L2:
        if(j1 > l1)
            break MISSING_BLOCK_LABEL_438;
        j2 = 0;
        k1 = j2;
        if(!mHasTabs)
            break; /* Loop/switch isn't completed */
        k1 = j2;
        if(j1 >= l1)
            break; /* Loop/switch isn't completed */
        j2 = mChars[j1];
        k1 = j2;
        if(j2 < '\uD800')
            break; /* Loop/switch isn't completed */
        k1 = j2;
        if(j2 >= '\uDC00')
            break; /* Loop/switch isn't completed */
        k1 = j2;
        if(j1 + 1 >= l1)
            break; /* Loop/switch isn't completed */
        j2 = Character.codePointAt(mChars, j1);
        k1 = j2;
        if(j2 <= 65535)
            break; /* Loop/switch isn't completed */
        k2 = j1 + 1;
        f2 = f1;
        j2 = i2;
_L5:
        j1 = k2 + 1;
        i2 = j2;
        f1 = f2;
        if(true) goto _L2; else goto _L1
_L1:
        if(j1 == l1) goto _L4; else goto _L3
_L3:
        j2 = i2;
        k2 = j1;
        f2 = f1;
        if(k1 != 9) goto _L5; else goto _L4
_L4:
        boolean flag1;
        if(i1 != l - 2 || j1 != mLen)
            flag1 = true;
        else
            flag1 = false;
        f2 = f1 + drawRun(canvas, i2, j1, flag, f + f1, i, j, k, flag1);
        f1 = f2;
        if(k1 == 9)
            f1 = (float)mDir * nextTab((float)mDir * f2);
        j2 = j1 + 1;
        k2 = j1;
        f2 = f1;
          goto _L5
        i1 += 2;
        if(true) goto _L7; else goto _L6
_L6:
    }

    int getOffsetToLeftRightOf(int i, boolean flag)
    {
        int j;
        int ai[];
        int k;
        int l;
        int i1;
        int j1;
        byte byte0;
        boolean flag2;
        int k1;
        int l1;
        j = mLen;
        boolean flag1;
        if(mDir == -1)
            flag1 = true;
        else
            flag1 = false;
        ai = mDirections.mDirections;
        k = 0;
        l = 0;
        i1 = 0;
        j1 = j;
        byte0 = -1;
        flag2 = false;
        if(i == 0)
        {
            k1 = -2;
            k = l;
            j1 = byte0;
        } else
        {
label0:
            {
                if(i != j)
                    break label0;
                k1 = ai.length;
                j1 = byte0;
                k = l;
            }
        }
_L6:
        if(flag == flag1)
            l = 1;
        else
            l = 0;
        if(l != 0)
            i = 2;
        else
            i = -2;
        k1 += i;
        if(k1 >= 0 && k1 < ai.length)
        {
            i1 = ai[k1] + 0;
            i = i1 + (ai[k1 + 1] & 0x3ffffff);
            l = i;
            if(i > j)
                l = j;
            l1 = ai[k1 + 1] >>> 26 & 0x3f;
            boolean flag3;
            if((l1 & 1) != 0)
                flag3 = true;
            else
                flag3 = false;
            if(flag == flag3)
                flag2 = true;
            else
                flag2 = false;
            if(j1 == -1)
            {
                int i2;
                int j2;
                int k2;
                int l2;
                boolean flag4;
                if(flag2)
                    i = i1;
                else
                    i = l;
                j1 = getOffsetBeforeAfter(k1, i1, l, flag3, i, flag2);
                if(!flag2)
                    l = i1;
                i = j1;
                if(j1 != l)
                    break MISSING_BLOCK_LABEL_705;
                k = l1;
                continue; /* Loop/switch isn't completed */
            }
            break MISSING_BLOCK_LABEL_687;
        }
        if(j1 == -1)
        {
            if(l != 0)
                i = mLen + 1;
            else
                i = -1;
        } else
        {
            i = j1;
            if(j1 <= j)
                if(l != 0)
                    i = j;
                else
                    i = 0;
        }
          goto _L1
        k1 = 0;
_L8:
        i2 = k1;
        l = j1;
        l1 = k;
        flag3 = flag2;
        if(k1 >= ai.length) goto _L3; else goto _L2
_L2:
        j2 = ai[k1] + 0;
        l = j1;
        if(i < j2)
            break MISSING_BLOCK_LABEL_611;
        l = j2 + (ai[k1 + 1] & 0x3ffffff);
        j1 = l;
        if(l > j)
            j1 = j;
        l = j1;
        if(i >= j1)
            break MISSING_BLOCK_LABEL_611;
        k2 = ai[k1 + 1] >>> 26 & 0x3f;
        i2 = k1;
        i1 = j2;
        l = j1;
        l1 = k2;
        flag3 = flag2;
        if(i != j2) goto _L3; else goto _L4
_L4:
        l2 = i - 1;
        k = 0;
_L7:
        i2 = k1;
        i1 = j2;
        l = j1;
        l1 = k2;
        flag3 = flag2;
        if(k < ai.length)
        {
            i1 = ai[k] + 0;
            if(l2 < i1)
                break MISSING_BLOCK_LABEL_605;
            l1 = i1 + (ai[k + 1] & 0x3ffffff);
            l = l1;
            if(l1 > j)
                l = j;
            if(l2 >= l)
                break MISSING_BLOCK_LABEL_605;
            l1 = ai[k + 1] >>> 26 & 0x3f;
            if(l1 >= k2)
                break MISSING_BLOCK_LABEL_605;
            i2 = k;
            flag3 = true;
        }
_L3:
        k1 = i2;
        j1 = byte0;
        k = l1;
        if(i2 == ai.length)
            continue; /* Loop/switch isn't completed */
        if((l1 & 1) != 0)
            flag2 = true;
        else
            flag2 = false;
        if(flag == flag2)
            flag4 = true;
        else
            flag4 = false;
        if(flag4)
            j1 = l;
        else
            j1 = i1;
        if(i != j1)
            break; /* Loop/switch isn't completed */
        k1 = i2;
        j1 = byte0;
        k = l1;
        if(flag4 == flag3) goto _L6; else goto _L5
_L5:
        j2 = getOffsetBeforeAfter(i2, i1, l, flag2, i, flag4);
        if(flag4)
            i = l;
        else
            i = i1;
        k1 = i2;
        j1 = j2;
        k = l1;
        if(j2 != i)
            return j2;
        continue; /* Loop/switch isn't completed */
        k += 2;
          goto _L7
        k1 += 2;
        i1 = j2;
        j1 = l;
          goto _L8
        i = j1;
        if(l1 < k)
            if(flag2)
                i = i1;
            else
                i = l;
_L10:
        return i;
_L1:
        if(true) goto _L10; else goto _L9
_L9:
        if(true) goto _L6; else goto _L11
_L11:
    }

    void justify(float f)
    {
        int i;
        for(i = mLen; i > 0 && isLineEndSpace(mText.charAt((mStart + i) - 1)); i--);
        int j = countStretchableSpaces(0, i);
        if(j == 0)
        {
            return;
        } else
        {
            mAddedWidth = (f - Math.abs(measure(i, false, null))) / (float)j;
            return;
        }
    }

    float measure(int i, boolean flag, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        int j;
        float f;
        char ac[];
        int ai[];
        int k;
        if(flag)
            j = i - 1;
        else
            j = i;
        if(j < 0)
            return 0.0F;
        f = 0.0F;
        if(!mHasTabs)
        {
            if(mDirections == Layout.DIRS_ALL_LEFT_TO_RIGHT)
                return measureRun(0, i, mLen, false, fontmetricsint);
            if(mDirections == Layout.DIRS_ALL_RIGHT_TO_LEFT)
                return measureRun(0, i, mLen, true, fontmetricsint);
        }
        ac = mChars;
        ai = mDirections.mDirections;
        k = 0;
_L7:
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        float f1;
        if(k >= ai.length)
            break; /* Loop/switch isn't completed */
        l = ai[k];
        i1 = l + (ai[k + 1] & 0x3ffffff);
        j1 = i1;
        if(i1 > mLen)
            j1 = mLen;
        if((ai[k + 1] & 0x4000000) != 0)
            flag = true;
        else
            flag = false;
        k1 = l;
        if(!mHasTabs)
            l = j1;
_L2:
        if(l > j1)
            break MISSING_BLOCK_LABEL_544;
        l1 = 0;
        i1 = l1;
        if(!mHasTabs)
            break; /* Loop/switch isn't completed */
        i1 = l1;
        if(l >= j1)
            break; /* Loop/switch isn't completed */
        l1 = ac[l];
        i1 = l1;
        if(l1 < '\uD800')
            break; /* Loop/switch isn't completed */
        i1 = l1;
        if(l1 >= '\uDC00')
            break; /* Loop/switch isn't completed */
        i1 = l1;
        if(l + 1 >= j1)
            break; /* Loop/switch isn't completed */
        l1 = Character.codePointAt(ac, l);
        i1 = l1;
        if(l1 <= 65535)
            break; /* Loop/switch isn't completed */
        i2 = l + 1;
        f1 = f;
        l1 = k1;
_L5:
        l = i2 + 1;
        k1 = l1;
        f = f1;
        if(true) goto _L2; else goto _L1
_L1:
        if(l == j1) goto _L4; else goto _L3
_L3:
        l1 = k1;
        i2 = l;
        f1 = f;
        if(i1 != 9) goto _L5; else goto _L4
_L4:
        boolean flag1;
        if(j >= k1 && j < l)
            l1 = 1;
        else
            l1 = 0;
        if(mDir == -1)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 == flag)
            i2 = 1;
        else
            i2 = 0;
        if(l1 != 0 && i2 != 0)
            return f + measureRun(k1, i, l, flag, fontmetricsint);
        f1 = measureRun(k1, l, l, flag, fontmetricsint);
        if(i2 == 0)
            f1 = -f1;
        f1 = f + f1;
        if(l1 != 0)
            return f1 + measureRun(k1, i, l, flag, null);
        f = f1;
        if(i1 == 9)
        {
            if(i == l)
                return f1;
            f1 = (float)mDir * nextTab((float)mDir * f1);
            f = f1;
            if(j == l)
                return f1;
        }
        l1 = l + 1;
        i2 = l;
        f1 = f;
          goto _L5
        k += 2;
        if(true) goto _L7; else goto _L6
_L6:
        return f;
    }

    float metrics(android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        return measure(mLen, false, fontmetricsint);
    }

    float nextTab(float f)
    {
        if(mTabs != null)
            return mTabs.nextTab(f);
        else
            return Layout.TabStops.nextDefaultStop(f, 20);
    }

    void set(TextPaint textpaint, CharSequence charsequence, int i, int j, int k, Layout.Directions directions, boolean flag, 
            Layout.TabStops tabstops)
    {
        mPaint = textpaint;
        mText = charsequence;
        mStart = i;
        mLen = j - i;
        mDir = k;
        mDirections = directions;
        if(mDirections == null)
            throw new IllegalArgumentException("Directions cannot be null");
        mHasTabs = flag;
        mSpanned = null;
        k = 0;
        if(charsequence instanceof Spanned)
        {
            mSpanned = (Spanned)charsequence;
            mReplacementSpanSpanSet.init(mSpanned, i, j);
            if(mReplacementSpanSpanSet.numberOfSpans > 0)
                k = 1;
            else
                k = 0;
        }
        if(k != 0 || flag || directions != Layout.DIRS_ALL_LEFT_TO_RIGHT)
            flag = true;
        else
            flag = false;
        mCharsValid = flag;
        if(mCharsValid)
        {
            if(mChars == null || mChars.length < mLen)
                mChars = ArrayUtils.newUnpaddedCharArray(mLen);
            TextUtils.getChars(charsequence, i, j, mChars, 0);
            if(k != 0)
            {
                textpaint = mChars;
                int l;
                for(k = i; k < j; k = l)
                {
                    l = mReplacementSpanSpanSet.getNextTransition(k, j);
                    if(!mReplacementSpanSpanSet.hasSpansIntersecting(k, l))
                        continue;
                    textpaint[k - i] = (char)65532;
                    for(k = (k - i) + 1; k < l - i; k++)
                        textpaint[k] = (char)65279;

                }

            }
        }
        mTabs = tabstops;
        mAddedWidth = 0.0F;
    }

    private static final boolean DEBUG = false;
    private static final int TAB_INCREMENT = 20;
    private static final TextLine sCached[] = new TextLine[3];
    private final TextPaint mActivePaint = new TextPaint();
    private float mAddedWidth;
    private final SpanSet mCharacterStyleSpanSet = new SpanSet(android/text/style/CharacterStyle);
    private char mChars[];
    private boolean mCharsValid;
    private final DecorationInfo mDecorationInfo = new DecorationInfo(null);
    private final ArrayList mDecorations = new ArrayList();
    private int mDir;
    private Layout.Directions mDirections;
    private boolean mHasTabs;
    private int mLen;
    private final SpanSet mMetricAffectingSpanSpanSet = new SpanSet(android/text/style/MetricAffectingSpan);
    private TextPaint mPaint;
    private final SpanSet mReplacementSpanSpanSet = new SpanSet(android/text/style/ReplacementSpan);
    private Spanned mSpanned;
    private int mStart;
    private Layout.TabStops mTabs;
    private CharSequence mText;
    private final TextPaint mWorkPaint = new TextPaint();

}
