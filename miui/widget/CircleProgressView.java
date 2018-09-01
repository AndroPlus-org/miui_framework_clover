// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class CircleProgressView extends FrameLayout
{

    public CircleProgressView(Context context)
    {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public CircleProgressView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mMaxProgress = MAX_PROGRESS;
        setBackgroundResource(0x11020020);
        setProgressResource(0x11020021);
        mPaint = new Paint();
        mPaint.setStyle(android.graphics.Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(0);
        mPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
    }

    public int getMaxProgress()
    {
        return mMaxProgress;
    }

    public int getProgress()
    {
        return mCurProgress;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mProgressDrawable != null)
        {
            mMemBitmap.eraseColor(0);
            mProgressDrawable.draw(mMemCanvas);
            mMemCanvas.drawArc(mArcRect, 270 - mAngle, mAngle, true, mPaint);
            canvas.drawBitmap(mMemBitmap, (getWidth() - mMemBitmap.getWidth()) / 2, (getHeight() - mMemBitmap.getHeight()) / 2, null);
        }
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        setMeasuredDimension(Math.max(getMeasuredWidth(), getSuggestedMinimumWidth()), Math.max(getMeasuredHeight(), getSuggestedMinimumHeight()));
    }

    public void setMaxProgress(int i)
    {
        if(i > 0 && mMaxProgress != i)
        {
            mMaxProgress = i;
            setProgress(mCurProgress);
        }
    }

    public void setProgress(int i)
    {
        mCurProgress = Math.min(i, mMaxProgress);
        mCurProgress = Math.max(0, mCurProgress);
        i = ((mMaxProgress - mCurProgress) * 360) / mMaxProgress;
        if(i != mAngle)
        {
            Log.i(TAG, (new StringBuilder()).append("progress:").append(mCurProgress).toString());
            mAngle = i;
            invalidate();
        }
    }

    public void setProgressResource(int i)
    {
        mProgressDrawable = mContext.getResources().getDrawable(i);
        i = mProgressDrawable.getIntrinsicWidth();
        int j = mProgressDrawable.getIntrinsicHeight();
        mProgressDrawable.setBounds(0, 0, i, j);
        mMemBitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        mMemCanvas = new Canvas(mMemBitmap);
        mArcRect = new RectF(0.0F, 0.0F, i, j);
        requestLayout();
    }

    private static int MAX_PROGRESS = 100;
    private static String TAG = "CircleProgressView";
    private int mAngle;
    private RectF mArcRect;
    private int mCurProgress;
    private int mMaxProgress;
    private Bitmap mMemBitmap;
    private Canvas mMemCanvas;
    private Paint mPaint;
    private Drawable mProgressDrawable;

}
