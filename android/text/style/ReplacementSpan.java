// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            MetricAffectingSpan

public abstract class ReplacementSpan extends MetricAffectingSpan
{

    public ReplacementSpan()
    {
    }

    public abstract void draw(Canvas canvas, CharSequence charsequence, int i, int j, float f, int k, int l, 
            int i1, Paint paint);

    public abstract int getSize(Paint paint, CharSequence charsequence, int i, int j, android.graphics.Paint.FontMetricsInt fontmetricsint);

    public void updateDrawState(TextPaint textpaint)
    {
    }

    public void updateMeasureState(TextPaint textpaint)
    {
    }
}
