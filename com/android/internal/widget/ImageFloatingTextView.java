// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

public class ImageFloatingTextView extends TextView
{

    public ImageFloatingTextView(Context context)
    {
        this(context, null);
    }

    public ImageFloatingTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ImageFloatingTextView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ImageFloatingTextView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mResolvedDirection = -1;
        mMaxLinesForHeight = -1;
        mFirstMeasure = true;
        mLayoutMaxLines = -1;
    }

    public int getLayoutHeight()
    {
        return getLayout().getHeight();
    }

    protected Layout makeSingleLayout(int i, android.text.BoringLayout.Metrics metrics, int j, android.text.Layout.Alignment alignment, boolean flag, android.text.TextUtils.TruncateAt truncateat, boolean flag1)
    {
        TransformationMethod transformationmethod = getTransformationMethod();
        Object obj = getText();
        metrics = ((android.text.BoringLayout.Metrics) (obj));
        if(transformationmethod != null)
            metrics = transformationmethod.getTransformation(((CharSequence) (obj)), this);
        obj = metrics;
        if(metrics == null)
            obj = "";
        obj = android.text.StaticLayout.Builder.obtain(((CharSequence) (obj)), 0, ((CharSequence) (obj)).length(), getPaint(), i).setAlignment(alignment).setTextDirection(getTextDirectionHeuristic()).setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier()).setIncludePad(getIncludeFontPadding()).setBreakStrategy(1).setHyphenationFrequency(2);
        if(mMaxLinesForHeight > 0)
            i = mMaxLinesForHeight;
        else
        if(getMaxLines() >= 0)
            i = getMaxLines();
        else
            i = 0x7fffffff;
        ((android.text.StaticLayout.Builder) (obj)).setMaxLines(i);
        mLayoutMaxLines = i;
        if(flag)
            ((android.text.StaticLayout.Builder) (obj)).setEllipsize(truncateat).setEllipsizedWidth(j);
        j = getContext().getResources().getDimensionPixelSize(0x105010d);
        metrics = null;
        if(mIndentLines > 0)
        {
            alignment = new int[mIndentLines + 1];
            i = 0;
            do
            {
                metrics = alignment;
                if(i >= mIndentLines)
                    break;
                alignment[i] = j;
                i++;
            } while(true);
        }
        if(mResolvedDirection == 1)
            ((android.text.StaticLayout.Builder) (obj)).setIndents(metrics, null);
        else
            ((android.text.StaticLayout.Builder) (obj)).setIndents(null, metrics);
        return ((android.text.StaticLayout.Builder) (obj)).build();
    }

    protected void onMeasure(int i, int j)
    {
        int k = Math.max(1, (android.view.View.MeasureSpec.getSize(j) - mPaddingTop - mPaddingBottom) / getLineHeight());
        int l = k;
        if(getMaxLines() > 0)
            l = Math.min(getMaxLines(), k);
        if(l != mMaxLinesForHeight)
        {
            mMaxLinesForHeight = l;
            if(getLayout() != null && mMaxLinesForHeight != mLayoutMaxLines)
            {
                mBlockLayouts = true;
                setHint(getHint());
                mBlockLayouts = false;
            }
        }
        super.onMeasure(i, j);
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        if(i != mResolvedDirection && isLayoutDirectionResolved())
        {
            mResolvedDirection = i;
            if(mIndentLines > 0)
                setHint(getHint());
        }
    }

    public void requestLayout()
    {
        if(!mBlockLayouts)
            super.requestLayout();
    }

    public void setHasImage(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        setNumIndentLines(byte0);
    }

    public boolean setNumIndentLines(int i)
    {
        if(mIndentLines != i)
        {
            mIndentLines = i;
            setHint(getHint());
            return true;
        } else
        {
            return false;
        }
    }

    private boolean mBlockLayouts;
    private boolean mFirstMeasure;
    private int mIndentLines;
    private int mLayoutMaxLines;
    private int mMaxLinesForHeight;
    private int mResolvedDirection;
}
