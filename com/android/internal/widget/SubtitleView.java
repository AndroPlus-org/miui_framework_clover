// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.text.*;
import android.util.AttributeSet;
import android.view.View;

public class SubtitleView extends View
{

    public SubtitleView(Context context)
    {
        this(context, null);
    }

    public SubtitleView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SubtitleView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SubtitleView(Context context, AttributeSet attributeset, int i, int j)
    {
        int k;
        super(context, attributeset);
        mLineBounds = new RectF();
        mText = new SpannableStringBuilder();
        mSpacingMult = 1.0F;
        mSpacingAdd = 0.0F;
        mInnerPaddingX = 0;
        attributeset = context.obtainStyledAttributes(attributeset, android.R.styleable.TextView, i, j);
        context = "";
        j = 15;
        k = attributeset.getIndexCount();
        i = 0;
_L7:
        int l;
        if(i >= k)
            break MISSING_BLOCK_LABEL_193;
        l = attributeset.getIndex(i);
        l;
        JVM INSTR lookupswitch 4: default 128
    //                   0: 180
    //                   18: 134
    //                   53: 144
    //                   54: 163;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        break MISSING_BLOCK_LABEL_180;
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        context = attributeset.getText(l);
          goto _L8
_L4:
        mSpacingAdd = attributeset.getDimensionPixelSize(l, (int)mSpacingAdd);
          goto _L8
_L5:
        mSpacingMult = attributeset.getFloat(l, mSpacingMult);
          goto _L8
        j = attributeset.getDimensionPixelSize(l, j);
          goto _L8
        attributeset = getContext().getResources();
        mCornerRadius = attributeset.getDimensionPixelSize(0x1050179);
        mOutlineWidth = attributeset.getDimensionPixelSize(0x105017a);
        mShadowRadius = attributeset.getDimensionPixelSize(0x105017c);
        mShadowOffsetX = attributeset.getDimensionPixelSize(0x105017b);
        mShadowOffsetY = mShadowOffsetX;
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setSubpixelText(true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setText(context);
        setTextSize(j);
        return;
    }

    private boolean computeMeasurements(int i)
    {
        if(mHasMeasurements && i == mLastMeasuredWidth)
            return true;
        i -= mPaddingLeft + mPaddingRight + mInnerPaddingX * 2;
        if(i <= 0)
        {
            return false;
        } else
        {
            mHasMeasurements = true;
            mLastMeasuredWidth = i;
            mLayout = new StaticLayout(mText, mTextPaint, i, mAlignment, mSpacingMult, mSpacingAdd, true);
            return true;
        }
    }

    protected void onDraw(Canvas canvas)
    {
        StaticLayout staticlayout;
        int i;
        int l;
        TextPaint textpaint;
        int j1;
        staticlayout = mLayout;
        if(staticlayout == null)
            return;
        i = canvas.save();
        int j = mInnerPaddingX;
        canvas.translate(mPaddingLeft + j, mPaddingTop);
        l = staticlayout.getLineCount();
        textpaint = mTextPaint;
        Paint paint = mPaint;
        RectF rectf = mLineBounds;
        if(Color.alpha(mBackgroundColor) > 0)
        {
            float f = mCornerRadius;
            float f1 = staticlayout.getLineTop(0);
            paint.setColor(mBackgroundColor);
            paint.setStyle(android.graphics.Paint.Style.FILL);
            for(int i1 = 0; i1 < l; i1++)
            {
                rectf.left = staticlayout.getLineLeft(i1) - (float)j;
                rectf.right = staticlayout.getLineRight(i1) + (float)j;
                rectf.top = f1;
                rectf.bottom = staticlayout.getLineBottom(i1);
                f1 = rectf.bottom;
                canvas.drawRoundRect(rectf, f, f, paint);
            }

        }
        j1 = mEdgeType;
        if(j1 != 1) goto _L2; else goto _L1
_L1:
        textpaint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        textpaint.setStrokeWidth(mOutlineWidth);
        textpaint.setColor(mEdgeColor);
        textpaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        for(j1 = 0; j1 < l; j1++)
            staticlayout.drawText(canvas, j1, j1);

          goto _L3
_L2:
        if(j1 != 2) goto _L5; else goto _L4
_L4:
        textpaint.setShadowLayer(mShadowRadius, mShadowOffsetX, mShadowOffsetY, mEdgeColor);
_L3:
        textpaint.setColor(mForegroundColor);
        textpaint.setStyle(android.graphics.Paint.Style.FILL);
        for(j1 = 0; j1 < l; j1++)
            staticlayout.drawText(canvas, j1, j1);

        break; /* Loop/switch isn't completed */
_L5:
        if(j1 == 3 || j1 == 4)
        {
            int k;
            float f2;
            if(j1 == 3)
                k = 1;
            else
                k = 0;
            if(k != 0)
                j1 = -1;
            else
                j1 = mEdgeColor;
            if(k != 0)
                k = mEdgeColor;
            else
                k = -1;
            f2 = mShadowRadius / 2.0F;
            textpaint.setColor(mForegroundColor);
            textpaint.setStyle(android.graphics.Paint.Style.FILL);
            textpaint.setShadowLayer(mShadowRadius, -f2, -f2, j1);
            for(j1 = 0; j1 < l; j1++)
                staticlayout.drawText(canvas, j1, j1);

            textpaint.setShadowLayer(mShadowRadius, f2, f2, k);
        }
        if(true) goto _L3; else goto _L6
_L6:
        textpaint.setShadowLayer(0.0F, 0.0F, 0.0F, 0);
        canvas.restoreToCount(i);
        return;
    }

    public void onLayout(boolean flag, int i, int j, int k, int l)
    {
        computeMeasurements(k - i);
    }

    protected void onMeasure(int i, int j)
    {
        if(computeMeasurements(android.view.View.MeasureSpec.getSize(i)))
        {
            StaticLayout staticlayout = mLayout;
            j = mPaddingLeft;
            i = mPaddingRight;
            int k = mInnerPaddingX;
            setMeasuredDimension(staticlayout.getWidth() + (j + i + k * 2), staticlayout.getHeight() + mPaddingTop + mPaddingBottom);
        } else
        {
            setMeasuredDimension(0x1000000, 0x1000000);
        }
    }

    public void setAlignment(android.text.Layout.Alignment alignment)
    {
        if(mAlignment != alignment)
        {
            mAlignment = alignment;
            mHasMeasurements = false;
            requestLayout();
            invalidate();
        }
    }

    public void setBackgroundColor(int i)
    {
        mBackgroundColor = i;
        invalidate();
    }

    public void setEdgeColor(int i)
    {
        mEdgeColor = i;
        invalidate();
    }

    public void setEdgeType(int i)
    {
        mEdgeType = i;
        invalidate();
    }

    public void setForegroundColor(int i)
    {
        mForegroundColor = i;
        invalidate();
    }

    public void setStyle(int i)
    {
        Object obj = mContext.getContentResolver();
        android.view.accessibility.CaptioningManager.CaptionStyle captionstyle;
        if(i == -1)
            obj = android.view.accessibility.CaptioningManager.CaptionStyle.getCustomStyle(((android.content.ContentResolver) (obj)));
        else
            obj = android.view.accessibility.CaptioningManager.CaptionStyle.PRESETS[i];
        captionstyle = android.view.accessibility.CaptioningManager.CaptionStyle.DEFAULT;
        if(((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).hasForegroundColor())
            i = ((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).foregroundColor;
        else
            i = captionstyle.foregroundColor;
        mForegroundColor = i;
        if(((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).hasBackgroundColor())
            i = ((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).backgroundColor;
        else
            i = captionstyle.backgroundColor;
        mBackgroundColor = i;
        if(((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).hasEdgeType())
            i = ((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).edgeType;
        else
            i = captionstyle.edgeType;
        mEdgeType = i;
        if(((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).hasEdgeColor())
            i = ((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).edgeColor;
        else
            i = captionstyle.edgeColor;
        mEdgeColor = i;
        mHasMeasurements = false;
        setTypeface(((android.view.accessibility.CaptioningManager.CaptionStyle) (obj)).getTypeface());
        requestLayout();
    }

    public void setText(int i)
    {
        setText(getContext().getText(i));
    }

    public void setText(CharSequence charsequence)
    {
        mText.clear();
        mText.append(charsequence);
        mHasMeasurements = false;
        requestLayout();
        invalidate();
    }

    public void setTextSize(float f)
    {
        if(mTextPaint.getTextSize() != f)
        {
            mTextPaint.setTextSize(f);
            mInnerPaddingX = (int)(0.125F * f + 0.5F);
            mHasMeasurements = false;
            requestLayout();
            invalidate();
        }
    }

    public void setTypeface(Typeface typeface)
    {
        if(mTextPaint.getTypeface() != typeface)
        {
            mTextPaint.setTypeface(typeface);
            mHasMeasurements = false;
            requestLayout();
            invalidate();
        }
    }

    private static final int COLOR_BEVEL_DARK = 0x80000000;
    private static final int COLOR_BEVEL_LIGHT = 0x80ffffff;
    private static final float INNER_PADDING_RATIO = 0.125F;
    private android.text.Layout.Alignment mAlignment;
    private int mBackgroundColor;
    private final float mCornerRadius;
    private int mEdgeColor;
    private int mEdgeType;
    private int mForegroundColor;
    private boolean mHasMeasurements;
    private int mInnerPaddingX;
    private int mLastMeasuredWidth;
    private StaticLayout mLayout;
    private final RectF mLineBounds;
    private final float mOutlineWidth;
    private Paint mPaint;
    private final float mShadowOffsetX;
    private final float mShadowOffsetY;
    private final float mShadowRadius;
    private float mSpacingAdd;
    private float mSpacingMult;
    private final SpannableStringBuilder mText;
    private TextPaint mTextPaint;
}
