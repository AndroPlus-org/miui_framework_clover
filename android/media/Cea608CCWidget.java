// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.text.*;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package android.media:
//            ClosedCaptionWidget

class Cea608CCWidget extends ClosedCaptionWidget
    implements Cea608CCParser.DisplayListener
{
    private static class CCLayout extends LinearLayout
        implements ClosedCaptionWidget.ClosedCaptionLayout
    {

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
            k -= i;
            l -= j;
            int i1;
            if(k * 3 >= l * 4)
            {
                j = (l * 4) / 3;
                i = l;
            } else
            {
                j = k;
                i = (k * 3) / 4;
            }
            j = (int)((float)j * 0.9F);
            i1 = (int)((float)i * 0.9F);
            k = (k - j) / 2;
            l = (l - i1) / 2;
            for(i = 0; i < 15; i++)
                mLineBoxes[i].layout(k, (i1 * i) / 15 + l, k + j, ((i + 1) * i1) / 15 + l);

        }

        protected void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            j = getMeasuredWidth();
            i = getMeasuredHeight();
            int k;
            if(j * 3 >= i * 4)
                j = (i * 4) / 3;
            else
                i = (j * 3) / 4;
            k = (int)((float)j * 0.9F);
            j = android.view.View.MeasureSpec.makeMeasureSpec((int)((float)i * 0.9F) / 15, 0x40000000);
            k = android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000);
            for(i = 0; i < 15; i++)
                mLineBoxes[i].measure(k, j);

        }

        public void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
        {
            for(int i = 0; i < 15; i++)
                mLineBoxes[i].setCaptionStyle(captionstyle);

        }

        public void setFontScale(float f)
        {
        }

        void update(SpannableStringBuilder aspannablestringbuilder[])
        {
            int i = 0;
            while(i < 15) 
            {
                if(aspannablestringbuilder[i] != null)
                {
                    mLineBoxes[i].setText(aspannablestringbuilder[i], android.widget.TextView.BufferType.SPANNABLE);
                    mLineBoxes[i].setVisibility(0);
                } else
                {
                    mLineBoxes[i].setVisibility(4);
                }
                i++;
            }
        }

        private static final int MAX_ROWS = 15;
        private static final float SAFE_AREA_RATIO = 0.9F;
        private final CCLineBox mLineBoxes[] = new CCLineBox[15];

        CCLayout(Context context)
        {
            super(context);
            setGravity(0x800003);
            setOrientation(1);
            for(int i = 0; i < 15; i++)
            {
                mLineBoxes[i] = new CCLineBox(getContext());
                addView(mLineBoxes[i], -2, -2);
            }

        }
    }

    private static class CCLineBox extends TextView
    {

        private void drawEdgeOutline(Canvas canvas)
        {
            TextPaint textpaint = getPaint();
            android.graphics.Paint.Style style = textpaint.getStyle();
            android.graphics.Paint.Join join = textpaint.getStrokeJoin();
            float f = textpaint.getStrokeWidth();
            setTextColor(mEdgeColor);
            textpaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
            textpaint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
            textpaint.setStrokeWidth(mOutlineWidth);
            super.onDraw(canvas);
            setTextColor(mTextColor);
            textpaint.setStyle(style);
            textpaint.setStrokeJoin(join);
            textpaint.setStrokeWidth(f);
            setBackgroundSpans(0);
            super.onDraw(canvas);
            setBackgroundSpans(mBgColor);
        }

        private void drawEdgeRaisedOrDepressed(Canvas canvas)
        {
            TextPaint textpaint = getPaint();
            android.graphics.Paint.Style style = textpaint.getStyle();
            textpaint.setStyle(android.graphics.Paint.Style.FILL);
            int i;
            int j;
            float f;
            if(mEdgeType == 3)
                i = 1;
            else
                i = 0;
            if(i != 0)
                j = -1;
            else
                j = mEdgeColor;
            if(i != 0)
                i = mEdgeColor;
            else
                i = -1;
            f = mShadowRadius / 2.0F;
            setShadowLayer(mShadowRadius, -f, -f, j);
            super.onDraw(canvas);
            setBackgroundSpans(0);
            setShadowLayer(mShadowRadius, f, f, i);
            super.onDraw(canvas);
            textpaint.setStyle(style);
            setBackgroundSpans(mBgColor);
        }

        private void setBackgroundSpans(int i)
        {
            Object obj = getText();
            if(obj instanceof Spannable)
            {
                obj = (Spannable)obj;
                Cea608CCParser.MutableBackgroundColorSpan amutablebackgroundcolorspan[] = (Cea608CCParser.MutableBackgroundColorSpan[])((Spannable) (obj)).getSpans(0, ((Spannable) (obj)).length(), android/media/Cea608CCParser$MutableBackgroundColorSpan);
                for(int j = 0; j < amutablebackgroundcolorspan.length; j++)
                    amutablebackgroundcolorspan[j].setBackgroundColor(i);

            }
        }

        protected void onDraw(Canvas canvas)
        {
            while(mEdgeType == -1 || mEdgeType == 0 || mEdgeType == 2) 
            {
                super.onDraw(canvas);
                return;
            }
            if(mEdgeType == 1)
                drawEdgeOutline(canvas);
            else
                drawEdgeRaisedOrDepressed(canvas);
        }

        protected void onMeasure(int i, int j)
        {
            float f = (float)android.view.View.MeasureSpec.getSize(j) * 0.75F;
            setTextSize(0, f);
            mOutlineWidth = 0.1F * f + 1.0F;
            mShadowRadius = 0.05F * f + 1.0F;
            mShadowOffset = mShadowRadius;
            setScaleX(1.0F);
            getPaint().getTextBounds("1234567890123456789012345678901234", 0, "1234567890123456789012345678901234".length(), Cea608CCWidget._2D_get0());
            f = Cea608CCWidget._2D_get0().width();
            setScaleX((float)android.view.View.MeasureSpec.getSize(i) / f);
            super.onMeasure(i, j);
        }

        void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
        {
            mTextColor = captionstyle.foregroundColor;
            mBgColor = captionstyle.backgroundColor;
            mEdgeType = captionstyle.edgeType;
            mEdgeColor = captionstyle.edgeColor;
            setTextColor(mTextColor);
            if(mEdgeType == 2)
                setShadowLayer(mShadowRadius, mShadowOffset, mShadowOffset, mEdgeColor);
            else
                setShadowLayer(0.0F, 0.0F, 0.0F, 0);
            invalidate();
        }

        private static final float EDGE_OUTLINE_RATIO = 0.1F;
        private static final float EDGE_SHADOW_RATIO = 0.05F;
        private static final float FONT_PADDING_RATIO = 0.75F;
        private int mBgColor;
        private int mEdgeColor;
        private int mEdgeType;
        private float mOutlineWidth;
        private float mShadowOffset;
        private float mShadowRadius;
        private int mTextColor;

        CCLineBox(Context context)
        {
            super(context);
            mTextColor = -1;
            mBgColor = 0xff000000;
            mEdgeType = 0;
            mEdgeColor = 0;
            setGravity(17);
            setBackgroundColor(0);
            setTextColor(-1);
            setTypeface(Typeface.MONOSPACE);
            setVisibility(4);
            context = getContext().getResources();
            mOutlineWidth = context.getDimensionPixelSize(0x105017a);
            mShadowRadius = context.getDimensionPixelSize(0x105017c);
            mShadowOffset = context.getDimensionPixelSize(0x105017b);
        }
    }


    static Rect _2D_get0()
    {
        return mTextBounds;
    }

    public Cea608CCWidget(Context context)
    {
        this(context, null);
    }

    public Cea608CCWidget(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public Cea608CCWidget(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Cea608CCWidget(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public ClosedCaptionWidget.ClosedCaptionLayout createCaptionLayout(Context context)
    {
        return new CCLayout(context);
    }

    public android.view.accessibility.CaptioningManager.CaptionStyle getCaptionStyle()
    {
        return mCaptionStyle;
    }

    public void onDisplayChanged(SpannableStringBuilder aspannablestringbuilder[])
    {
        ((CCLayout)mClosedCaptionLayout).update(aspannablestringbuilder);
        if(mListener != null)
            mListener.onChanged(this);
    }

    private static final String mDummyText = "1234567890123456789012345678901234";
    private static final Rect mTextBounds = new Rect();

}
