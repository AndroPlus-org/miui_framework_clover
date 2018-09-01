// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spanned;

// Referenced classes of package android.text.style:
//            LeadingMarginSpan, LineHeightSpan

public class DrawableMarginSpan
    implements LeadingMarginSpan, LineHeightSpan
{

    public DrawableMarginSpan(Drawable drawable)
    {
        mDrawable = drawable;
    }

    public DrawableMarginSpan(Drawable drawable, int i)
    {
        mDrawable = drawable;
        mPad = i;
    }

    public void chooseHeight(CharSequence charsequence, int i, int j, int k, int l, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        if(j == ((Spanned)charsequence).getSpanEnd(this))
        {
            i = mDrawable.getIntrinsicHeight();
            j = i - ((fontmetricsint.descent + l) - fontmetricsint.ascent - k);
            if(j > 0)
                fontmetricsint.descent = fontmetricsint.descent + j;
            i -= (fontmetricsint.bottom + l) - fontmetricsint.top - k;
            if(i > 0)
                fontmetricsint.bottom = fontmetricsint.bottom + i;
        }
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int j, int k, int l, int i1, 
            CharSequence charsequence, int j1, int k1, boolean flag, Layout layout)
    {
        l = layout.getLineTop(layout.getLineForOffset(((Spanned)charsequence).getSpanStart(this)));
        j = mDrawable.getIntrinsicWidth();
        k = mDrawable.getIntrinsicHeight();
        mDrawable.setBounds(i, l, i + j, l + k);
        mDrawable.draw(canvas);
    }

    public int getLeadingMargin(boolean flag)
    {
        return mDrawable.getIntrinsicWidth() + mPad;
    }

    private Drawable mDrawable;
    private int mPad;
}
