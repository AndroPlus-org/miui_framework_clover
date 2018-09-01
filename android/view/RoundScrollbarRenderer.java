// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.*;

// Referenced classes of package android.view:
//            View

class RoundScrollbarRenderer
{

    public RoundScrollbarRenderer(View view)
    {
        mThumbPaint.setAntiAlias(true);
        mThumbPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        mThumbPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mTrackPaint.setAntiAlias(true);
        mTrackPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        mTrackPaint.setStyle(android.graphics.Paint.Style.STROKE);
        mParent = view;
    }

    private static int applyAlpha(int i, float f)
    {
        return Color.argb((int)((float)Color.alpha(i) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    private static float clamp(float f, float f1, float f2)
    {
        if(f < f1)
            return f1;
        if(f > f2)
            return f2;
        else
            return f;
    }

    private void setThumbColor(int i)
    {
        if(mThumbPaint.getColor() != i)
            mThumbPaint.setColor(i);
    }

    private void setTrackColor(int i)
    {
        if(mTrackPaint.getColor() != i)
            mTrackPaint.setColor(i);
    }

    public void drawRoundScrollbars(Canvas canvas, float f, Rect rect)
    {
        if(f == 0.0F)
            return;
        float f1 = mParent.computeVerticalScrollRange();
        float f2 = mParent.computeVerticalScrollExtent();
        if(f2 <= 0.0F || f1 <= f2)
        {
            return;
        } else
        {
            float f4 = Math.max(0, mParent.computeVerticalScrollOffset());
            float f5 = mParent.computeVerticalScrollExtent();
            float f3 = (float)mParent.getWidth() * 0.02F;
            mThumbPaint.setStrokeWidth(f3);
            mTrackPaint.setStrokeWidth(f3);
            setThumbColor(applyAlpha(0x4cffffff, f));
            setTrackColor(applyAlpha(0x26ffffff, f));
            f = clamp((f5 / f1) * 90F, 6F, 16F);
            f1 = clamp(((90F - f) * f4) / (f1 - f5) - 45F, -45F, 45F - f);
            mRect.set((float)rect.left - f3 / 2.0F, rect.top, (float)rect.right - f3 / 2.0F, rect.bottom);
            canvas.drawArc(mRect, -45F, 90F, false, mTrackPaint);
            canvas.drawArc(mRect, f1, f, false, mThumbPaint);
            return;
        }
    }

    private static final int DEFAULT_THUMB_COLOR = 0x4cffffff;
    private static final int DEFAULT_TRACK_COLOR = 0x26ffffff;
    private static final int MAX_SCROLLBAR_ANGLE_SWIPE = 16;
    private static final int MIN_SCROLLBAR_ANGLE_SWIPE = 6;
    private static final int SCROLLBAR_ANGLE_RANGE = 90;
    private static final float WIDTH_PERCENTAGE = 0.02F;
    private final View mParent;
    private final RectF mRect = new RectF();
    private final Paint mThumbPaint = new Paint();
    private final Paint mTrackPaint = new Paint();
}
