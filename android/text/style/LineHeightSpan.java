// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            ParagraphStyle, WrapTogetherSpan

public interface LineHeightSpan
    extends ParagraphStyle, WrapTogetherSpan
{
    public static interface WithDensity
        extends LineHeightSpan
    {

        public abstract void chooseHeight(CharSequence charsequence, int i, int j, int k, int l, android.graphics.Paint.FontMetricsInt fontmetricsint, TextPaint textpaint);
    }


    public abstract void chooseHeight(CharSequence charsequence, int i, int j, int k, int l, android.graphics.Paint.FontMetricsInt fontmetricsint);
}
