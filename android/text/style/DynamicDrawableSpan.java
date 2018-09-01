// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

// Referenced classes of package android.text.style:
//            ReplacementSpan

public abstract class DynamicDrawableSpan extends ReplacementSpan
{

    public DynamicDrawableSpan()
    {
        mVerticalAlignment = 0;
    }

    protected DynamicDrawableSpan(int i)
    {
        mVerticalAlignment = i;
    }

    private Drawable getCachedDrawable()
    {
        Object obj = mDrawableRef;
        Drawable drawable = null;
        if(obj != null)
            drawable = (Drawable)((WeakReference) (obj)).get();
        obj = drawable;
        if(drawable == null)
        {
            obj = getDrawable();
            mDrawableRef = new WeakReference(obj);
        }
        return ((Drawable) (obj));
    }

    public void draw(Canvas canvas, CharSequence charsequence, int i, int j, float f, int k, int l, 
            int i1, Paint paint)
    {
        charsequence = getCachedDrawable();
        canvas.save();
        j = i1 - charsequence.getBounds().bottom;
        i = j;
        if(mVerticalAlignment == 1)
            i = j - paint.getFontMetricsInt().descent;
        canvas.translate(f, i);
        charsequence.draw(canvas);
        canvas.restore();
    }

    public abstract Drawable getDrawable();

    public int getSize(Paint paint, CharSequence charsequence, int i, int j, android.graphics.Paint.FontMetricsInt fontmetricsint)
    {
        paint = getCachedDrawable().getBounds();
        if(fontmetricsint != null)
        {
            fontmetricsint.ascent = -((Rect) (paint)).bottom;
            fontmetricsint.descent = 0;
            fontmetricsint.top = fontmetricsint.ascent;
            fontmetricsint.bottom = 0;
        }
        return ((Rect) (paint)).right;
    }

    public int getVerticalAlignment()
    {
        return mVerticalAlignment;
    }

    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    private static final String TAG = "DynamicDrawableSpan";
    private WeakReference mDrawableRef;
    protected final int mVerticalAlignment;
}
