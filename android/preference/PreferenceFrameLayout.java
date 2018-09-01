// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

public class PreferenceFrameLayout extends FrameLayout
{
    public static class LayoutParams extends android.widget.FrameLayout.LayoutParams
    {

        public boolean removeBorders;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            removeBorders = false;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            removeBorders = false;
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PreferenceFrameLayout_Layout);
            removeBorders = context.getBoolean(0, false);
            context.recycle();
        }
    }


    public PreferenceFrameLayout(Context context)
    {
        this(context, null);
    }

    public PreferenceFrameLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x111009c);
    }

    public PreferenceFrameLayout(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public PreferenceFrameLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PreferenceFrameLayout, i, j);
        float f = context.getResources().getDisplayMetrics().density;
        i = (int)(f * 0.0F + 0.5F);
        int k = (int)(f * 0.0F + 0.5F);
        j = (int)(f * 0.0F + 0.5F);
        int l = (int)(f * 0.0F + 0.5F);
        mBorderTop = attributeset.getDimensionPixelSize(3, i);
        mBorderBottom = attributeset.getDimensionPixelSize(0, k);
        mBorderLeft = attributeset.getDimensionPixelSize(1, j);
        mBorderRight = attributeset.getDimensionPixelSize(2, l);
        attributeset.recycle();
    }

    public void addView(View view)
    {
        int i;
        int l;
        int j1;
        int k1;
        i = getPaddingTop();
        int j = getPaddingBottom();
        int k = getPaddingLeft();
        l = getPaddingRight();
        LayoutParams layoutparams;
        int i1;
        int l1;
        if(view.getLayoutParams() instanceof LayoutParams)
            layoutparams = (LayoutParams)view.getLayoutParams();
        else
            layoutparams = null;
        if(layoutparams != null && layoutparams.removeBorders)
        {
            i1 = j;
            j1 = k;
            k1 = l;
            l1 = i;
            if(mPaddingApplied)
            {
                l1 = i - mBorderTop;
                i1 = j - mBorderBottom;
                j1 = k - mBorderLeft;
                k1 = l - mBorderRight;
                mPaddingApplied = false;
            }
        } else
        {
            i1 = j;
            j1 = k;
            k1 = l;
            l1 = i;
            if(!mPaddingApplied)
            {
                l1 = i + mBorderTop;
                i1 = j + mBorderBottom;
                j1 = k + mBorderLeft;
                k1 = l + mBorderRight;
                mPaddingApplied = true;
            }
        }
        k = getPaddingTop();
        j = getPaddingBottom();
        i = getPaddingLeft();
        l = getPaddingRight();
        break MISSING_BLOCK_LABEL_136;
        if(k != l1 || j != i1 || i != j1 || l != k1)
            setPadding(j1, l1, k1, i1);
        super.addView(view);
        return;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public volatile android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    private static final int DEFAULT_BORDER_BOTTOM = 0;
    private static final int DEFAULT_BORDER_LEFT = 0;
    private static final int DEFAULT_BORDER_RIGHT = 0;
    private static final int DEFAULT_BORDER_TOP = 0;
    private final int mBorderBottom;
    private final int mBorderLeft;
    private final int mBorderRight;
    private final int mBorderTop;
    private boolean mPaddingApplied;
}
