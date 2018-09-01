// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.*;
import android.text.style.ParagraphStyle;

// Referenced classes of package android.text:
//            Layout, TextUtils, TextDirectionHeuristics, TextDirectionHeuristic, 
//            Spanned, TextLine, TextPaint

public class BoringLayout extends Layout
    implements TextUtils.EllipsizeCallback
{
    public static class Metrics extends android.graphics.Paint.FontMetricsInt
    {

        static void _2D_wrap0(Metrics metrics)
        {
            metrics.reset();
        }

        private void reset()
        {
            top = 0;
            bottom = 0;
            ascent = 0;
            descent = 0;
            width = 0;
            leading = 0;
        }

        public String toString()
        {
            return (new StringBuilder()).append(super.toString()).append(" width=").append(width).toString();
        }

        public int width;

        public Metrics()
        {
        }
    }


    public BoringLayout(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, Metrics metrics, 
            boolean flag)
    {
        super(charsequence, textpaint, i, alignment, f, f1);
        mEllipsizedWidth = i;
        mEllipsizedStart = 0;
        mEllipsizedCount = 0;
        init(charsequence, textpaint, i, alignment, f, f1, metrics, flag, true);
    }

    public BoringLayout(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, Metrics metrics, 
            boolean flag, TextUtils.TruncateAt truncateat, int j)
    {
        super(charsequence, textpaint, i, alignment, f, f1);
        boolean flag1;
        if(truncateat == null || truncateat == TextUtils.TruncateAt.MARQUEE)
        {
            mEllipsizedWidth = i;
            mEllipsizedStart = 0;
            mEllipsizedCount = 0;
            flag1 = true;
        } else
        {
            replaceWith(TextUtils.ellipsize(charsequence, textpaint, j, truncateat, true, this), textpaint, i, alignment, f, f1);
            mEllipsizedWidth = j;
            flag1 = false;
        }
        init(getText(), textpaint, i, alignment, f, f1, metrics, flag, flag1);
    }

    private static boolean hasAnyInterestingChars(CharSequence charsequence, int i)
    {
        char ac[];
        int j;
        ac = TextUtils.obtain(500);
        j = 0;
_L8:
        if(j >= i) goto _L2; else goto _L1
_L1:
        int k;
        k = Math.min(j + 500, i);
        TextUtils.getChars(charsequence, j, k, ac, 0);
        int l = 0;
_L7:
        char c;
        if(l >= k - j)
            continue; /* Loop/switch isn't completed */
        c = ac[l];
        if(c != '\n' && c != '\t') goto _L4; else goto _L3
_L3:
        boolean flag;
        TextUtils.recycle(ac);
        return true;
_L4:
        if(flag = TextUtils.couldAffectRtl(c)) goto _L3; else goto _L5
_L5:
        l++;
        if(true) goto _L7; else goto _L6
_L6:
        j += 500;
          goto _L8
_L2:
        TextUtils.recycle(ac);
        return false;
        charsequence;
        TextUtils.recycle(ac);
        throw charsequence;
    }

    public static Metrics isBoring(CharSequence charsequence, TextPaint textpaint)
    {
        return isBoring(charsequence, textpaint, TextDirectionHeuristics.FIRSTSTRONG_LTR, null);
    }

    public static Metrics isBoring(CharSequence charsequence, TextPaint textpaint, Metrics metrics)
    {
        return isBoring(charsequence, textpaint, TextDirectionHeuristics.FIRSTSTRONG_LTR, metrics);
    }

    public static Metrics isBoring(CharSequence charsequence, TextPaint textpaint, TextDirectionHeuristic textdirectionheuristic, Metrics metrics)
    {
        int i = charsequence.length();
        if(hasAnyInterestingChars(charsequence, i))
            return null;
        if(textdirectionheuristic != null && textdirectionheuristic.isRtl(charsequence, 0, i))
            return null;
        if((charsequence instanceof Spanned) && ((Spanned)charsequence).getSpans(0, i, android/text/style/ParagraphStyle).length > 0)
            return null;
        textdirectionheuristic = metrics;
        if(metrics == null)
            textdirectionheuristic = new Metrics();
        else
            Metrics._2D_wrap0(metrics);
        metrics = TextLine.obtain();
        metrics.set(textpaint, charsequence, 0, i, 1, Layout.DIRS_ALL_LEFT_TO_RIGHT, false, null);
        textdirectionheuristic.width = (int)Math.ceil(metrics.metrics(textdirectionheuristic));
        TextLine.recycle(metrics);
        return textdirectionheuristic;
    }

    public static BoringLayout make(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, Metrics metrics, boolean flag)
    {
        return new BoringLayout(charsequence, textpaint, i, alignment, f, f1, metrics, flag);
    }

    public static BoringLayout make(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, Metrics metrics, boolean flag, 
            TextUtils.TruncateAt truncateat, int j)
    {
        return new BoringLayout(charsequence, textpaint, i, alignment, f, f1, metrics, flag, truncateat, j);
    }

    public void draw(Canvas canvas, Path path, Paint paint, int i)
    {
        if(mDirect != null && path == null)
            canvas.drawText(mDirect, 0.0F, mBottom - mDesc, mPaint);
        else
            super.draw(canvas, path, paint, i);
    }

    public void ellipsized(int i, int j)
    {
        mEllipsizedStart = i;
        mEllipsizedCount = j - i;
    }

    public int getBottomPadding()
    {
        return mBottomPadding;
    }

    public int getEllipsisCount(int i)
    {
        return mEllipsizedCount;
    }

    public int getEllipsisStart(int i)
    {
        return mEllipsizedStart;
    }

    public int getEllipsizedWidth()
    {
        return mEllipsizedWidth;
    }

    public int getHeight()
    {
        return mBottom;
    }

    public boolean getLineContainsTab(int i)
    {
        return false;
    }

    public int getLineCount()
    {
        return 1;
    }

    public int getLineDescent(int i)
    {
        return mDesc;
    }

    public final Layout.Directions getLineDirections(int i)
    {
        return Layout.DIRS_ALL_LEFT_TO_RIGHT;
    }

    public float getLineMax(int i)
    {
        return mMax;
    }

    public int getLineStart(int i)
    {
        if(i == 0)
            return 0;
        else
            return getText().length();
    }

    public int getLineTop(int i)
    {
        if(i == 0)
            return 0;
        else
            return mBottom;
    }

    public float getLineWidth(int i)
    {
        float f;
        if(i == 0)
            f = mMax;
        else
            f = 0.0F;
        return f;
    }

    public int getParagraphDirection(int i)
    {
        return 1;
    }

    public int getTopPadding()
    {
        return mTopPadding;
    }

    void init(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, Metrics metrics, 
            boolean flag, boolean flag1)
    {
        if((charsequence instanceof String) && alignment == Layout.Alignment.ALIGN_NORMAL)
            mDirect = charsequence.toString();
        else
            mDirect = null;
        mPaint = textpaint;
        if(flag)
        {
            i = metrics.bottom - metrics.top;
            mDesc = metrics.bottom;
        } else
        {
            i = metrics.descent - metrics.ascent;
            mDesc = metrics.descent;
        }
        mBottom = i;
        if(flag1)
        {
            mMax = metrics.width;
        } else
        {
            alignment = TextLine.obtain();
            alignment.set(textpaint, charsequence, 0, charsequence.length(), 1, Layout.DIRS_ALL_LEFT_TO_RIGHT, false, null);
            mMax = (int)Math.ceil(alignment.metrics(null));
            TextLine.recycle(alignment);
        }
        if(flag)
        {
            mTopPadding = metrics.top - metrics.ascent;
            mBottomPadding = metrics.bottom - metrics.descent;
        }
    }

    public BoringLayout replaceOrMake(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, Metrics metrics, 
            boolean flag)
    {
        replaceWith(charsequence, textpaint, i, alignment, f, f1);
        mEllipsizedWidth = i;
        mEllipsizedStart = 0;
        mEllipsizedCount = 0;
        init(charsequence, textpaint, i, alignment, f, f1, metrics, flag, true);
        return this;
    }

    public BoringLayout replaceOrMake(CharSequence charsequence, TextPaint textpaint, int i, Layout.Alignment alignment, float f, float f1, Metrics metrics, 
            boolean flag, TextUtils.TruncateAt truncateat, int j)
    {
        boolean flag1;
        if(truncateat == null || truncateat == TextUtils.TruncateAt.MARQUEE)
        {
            replaceWith(charsequence, textpaint, i, alignment, f, f1);
            mEllipsizedWidth = i;
            mEllipsizedStart = 0;
            mEllipsizedCount = 0;
            flag1 = true;
        } else
        {
            replaceWith(TextUtils.ellipsize(charsequence, textpaint, j, truncateat, true, this), textpaint, i, alignment, f, f1);
            mEllipsizedWidth = j;
            flag1 = false;
        }
        init(getText(), textpaint, i, alignment, f, f1, metrics, flag, flag1);
        return this;
    }

    int mBottom;
    private int mBottomPadding;
    int mDesc;
    private String mDirect;
    private int mEllipsizedCount;
    private int mEllipsizedStart;
    private int mEllipsizedWidth;
    private float mMax;
    private Paint mPaint;
    private int mTopPadding;
}
