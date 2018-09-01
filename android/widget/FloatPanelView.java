// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

// Referenced classes of package android.widget:
//            FrameLayout, ImageView

public class FloatPanelView extends FrameLayout
{

    public FloatPanelView(Context context)
    {
        super(context);
        init();
    }

    public FloatPanelView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init();
    }

    public FloatPanelView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init();
    }

    private ImageView getImageView(int i)
    {
        ImageView imageview = new ImageView(getContext());
        imageview.setImageResource(i);
        return imageview;
    }

    private void init()
    {
        mUpArrow = getImageView(0x110200d9);
        mDownArrow = getImageView(0x110200d8);
    }

    public ViewGroup getContent()
    {
        return mContent;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        while(mUpArrow == null || mDownArrow == null || mContent == null) 
        {
            Log.e("FloatPanelView", "couldn't find view");
            return;
        }
        mUpArrow.setVisibility(8);
        mDownArrow.setVisibility(8);
        ImageView imageview = null;
        if(mDirection == 1)
            imageview = mUpArrow;
        else
        if(mDirection == -1)
            imageview = mDownArrow;
        if(imageview != null)
        {
            int i1 = imageview.getDrawable().getIntrinsicWidth();
            int j1 = imageview.getDrawable().getIntrinsicHeight();
            int k1;
            if(mDirection == 1)
                l = j;
            else
                l = j + mContent.getMeasuredHeight();
            imageview.setVisibility(0);
            k1 = (k - i - i1) / 2 + i + mOffset;
            if(k1 < mContent.getLeft() + mLeftRoundCorner)
            {
                i = mContent.getLeft() + mLeftRoundCorner;
            } else
            {
                i = k1;
                if(k1 > k - i1 - mRightRoundCorner)
                    i = k - i1 - mRightRoundCorner;
            }
            imageview.layout(i, l, i + i1, l + j1);
            if(l == j)
                i = j1;
            else
                i = j;
            mContent.layout(mContent.getLeft(), i, mContent.getLeft() + mContent.getMeasuredWidth(), mContent.getMeasuredHeight() + i);
        } else
        {
            mContent.layout(mContent.getLeft(), mContent.getTop(), mContent.getLeft() + mContent.getMeasuredWidth(), mContent.getTop() + mContent.getMeasuredHeight());
        }
    }

    protected void onMeasure(int i, int j)
    {
        ImageView imageview = null;
        int k;
        if(mDirection == 1)
            imageview = mUpArrow;
        else
        if(mDirection == -1)
            imageview = mDownArrow;
        if(imageview == null)
            k = 0;
        else
            k = imageview.getDrawable().getIntrinsicHeight();
        mContent.measure(i, j);
        setMeasuredDimension(mContent.getMeasuredWidth(), mContent.getMeasuredHeight() + k);
    }

    public void setArrow(int i)
    {
        if(i != mDirection)
        {
            mDirection = i;
            requestLayout();
        }
    }

    public void setContent(ViewGroup viewgroup)
    {
        if(viewgroup != mContent)
        {
            removeAllViews();
            if(viewgroup != null)
            {
                addView(viewgroup);
                mContent = viewgroup;
                addView(mUpArrow);
                addView(mDownArrow);
            }
        }
    }

    public void setLeftCorner(int i)
    {
        if(i != mLeftRoundCorner)
        {
            mLeftRoundCorner = i;
            requestLayout();
        }
    }

    public void setOffset(int i)
    {
        if(mOffset != i)
        {
            mOffset = i;
            requestLayout();
        }
    }

    public void setRightCorner(int i)
    {
        if(i != mRightRoundCorner)
        {
            mRightRoundCorner = i;
            requestLayout();
        }
    }

    public static final int DOWN_ARROW = -1;
    public static final int NO_ARROW = 0;
    public static final int UP_ARROW = 1;
    private ViewGroup mContent;
    private int mDirection;
    private ImageView mDownArrow;
    private int mLeftRoundCorner;
    private int mOffset;
    private int mRightRoundCorner;
    private ImageView mUpArrow;
}
