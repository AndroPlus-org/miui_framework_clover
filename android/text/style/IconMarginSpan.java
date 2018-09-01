// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.*;
import android.text.Layout;
import android.text.Spanned;

// Referenced classes of package android.text.style:
//            LeadingMarginSpan, LineHeightSpan

public class IconMarginSpan
    implements LeadingMarginSpan, LineHeightSpan
{

    public IconMarginSpan(Bitmap bitmap)
    {
        mBitmap = bitmap;
    }

    public IconMarginSpan(Bitmap bitmap, int i)
    {
        mBitmap = bitmap;
        mPad = i;
    }

    public void chooseHeight(CharSequence charsequence, int i, int j, int k, int l, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        if(j == ((Spanned)charsequence).getSpanEnd(this))
        {
            j = mBitmap.getHeight();
            i = j - ((fontmetricsint.descent + l) - fontmetricsint.ascent - k);
            if(i > 0)
                fontmetricsint.descent = fontmetricsint.descent + i;
            i = j - ((fontmetricsint.bottom + l) - fontmetricsint.top - k);
            if(i > 0)
                fontmetricsint.bottom = fontmetricsint.bottom + i;
        }
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int j, int k, int l, int i1, 
            CharSequence charsequence, int j1, int k1, boolean flag, Layout layout)
    {
        l = layout.getLineTop(layout.getLineForOffset(((Spanned)charsequence).getSpanStart(this)));
        k = i;
        if(j < 0)
            k = i - mBitmap.getWidth();
        canvas.drawBitmap(mBitmap, k, l, paint);
    }

    public int getLeadingMargin(boolean flag)
    {
        return mBitmap.getWidth() + mPad;
    }

    private Bitmap mBitmap;
    private int mPad;
}
