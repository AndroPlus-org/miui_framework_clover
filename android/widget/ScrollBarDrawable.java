// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import com.android.internal.widget.ScrollBarUtils;

public class ScrollBarDrawable extends Drawable
    implements android.graphics.drawable.Drawable.Callback
{

    public ScrollBarDrawable()
    {
        mAlpha = 255;
    }

    private void drawThumb(Canvas canvas, Rect rect, int i, int j, boolean flag)
    {
        boolean flag1;
        if(!mRangeChanged)
            flag1 = mBoundsChanged;
        else
            flag1 = true;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(mVerticalThumb != null)
        {
            Drawable drawable = mVerticalThumb;
            if(flag1)
                drawable.setBounds(rect.left, rect.top + i, rect.right, rect.top + i + j);
            drawable.draw(canvas);
        }
_L4:
        return;
_L2:
        if(mHorizontalThumb != null)
        {
            Drawable drawable1 = mHorizontalThumb;
            if(flag1)
                drawable1.setBounds(rect.left + i, rect.top, rect.left + i + j, rect.bottom);
            drawable1.draw(canvas);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void drawTrack(Canvas canvas, Rect rect, boolean flag)
    {
        Drawable drawable;
        if(flag)
            drawable = mVerticalTrack;
        else
            drawable = mHorizontalTrack;
        if(drawable != null)
        {
            if(mBoundsChanged)
                drawable.setBounds(rect);
            drawable.draw(canvas);
        }
    }

    private void propagateCurrentState(Drawable drawable)
    {
        if(drawable != null)
        {
            if(mMutated)
                drawable.mutate();
            drawable.setState(getState());
            drawable.setCallback(this);
            if(mHasSetAlpha)
                drawable.setAlpha(mAlpha);
            if(mHasSetColorFilter)
                drawable.setColorFilter(mColorFilter);
        }
    }

    public void draw(Canvas canvas)
    {
        boolean flag = mVertical;
        int i = mExtent;
        int j = mRange;
        boolean flag1 = true;
        boolean flag2 = true;
        Rect rect;
        if(i <= 0 || j <= i)
        {
            if(flag)
                flag1 = mAlwaysDrawVerticalTrack;
            else
                flag1 = mAlwaysDrawHorizontalTrack;
            flag2 = false;
        }
        rect = getBounds();
        if(canvas.quickReject(rect.left, rect.top, rect.right, rect.bottom, android.graphics.Canvas.EdgeType.AA))
            return;
        if(flag1)
            drawTrack(canvas, rect, flag);
        if(flag2)
        {
            int k;
            int l;
            if(flag)
                k = rect.height();
            else
                k = rect.width();
            if(flag)
                l = rect.width();
            else
                l = rect.height();
            l = ScrollBarUtils.getThumbLength(k, l, i, j);
            drawThumb(canvas, rect, ScrollBarUtils.getThumbOffset(k, l, i, j, mOffset), l, flag);
        }
    }

    public int getAlpha()
    {
        return mAlpha;
    }

    public boolean getAlwaysDrawHorizontalTrack()
    {
        return mAlwaysDrawHorizontalTrack;
    }

    public boolean getAlwaysDrawVerticalTrack()
    {
        return mAlwaysDrawVerticalTrack;
    }

    public ColorFilter getColorFilter()
    {
        return mColorFilter;
    }

    public int getOpacity()
    {
        return -3;
    }

    public int getSize(boolean flag)
    {
        boolean flag1;
        int i;
        flag1 = false;
        i = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(mVerticalTrack == null) goto _L4; else goto _L3
_L3:
        i = mVerticalTrack.getIntrinsicWidth();
_L5:
        return i;
_L4:
        if(mVerticalThumb != null)
            i = mVerticalThumb.getIntrinsicWidth();
        if(true) goto _L5; else goto _L2
_L2:
        if(mHorizontalTrack == null) goto _L7; else goto _L6
_L6:
        i = mHorizontalTrack.getIntrinsicHeight();
_L9:
        return i;
_L7:
        i = ((flag1) ? 1 : 0);
        if(mHorizontalThumb != null)
            i = mHorizontalThumb.getIntrinsicHeight();
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void invalidateDrawable(Drawable drawable)
    {
        invalidateSelf();
    }

    public boolean isStateful()
    {
        boolean flag;
        if((mVerticalTrack == null || !mVerticalTrack.isStateful()) && (mVerticalThumb == null || !mVerticalThumb.isStateful()) && (mHorizontalTrack == null || !mHorizontalTrack.isStateful()) && (mHorizontalThumb == null || !mHorizontalThumb.isStateful()))
            flag = super.isStateful();
        else
            flag = true;
        return flag;
    }

    public volatile Drawable mutate()
    {
        return mutate();
    }

    public ScrollBarDrawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            if(mVerticalTrack != null)
                mVerticalTrack.mutate();
            if(mVerticalThumb != null)
                mVerticalThumb.mutate();
            if(mHorizontalTrack != null)
                mHorizontalTrack.mutate();
            if(mHorizontalThumb != null)
                mHorizontalThumb.mutate();
            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        mBoundsChanged = true;
    }

    protected boolean onStateChange(int ai[])
    {
        boolean flag = super.onStateChange(ai);
        boolean flag1 = flag;
        if(mVerticalTrack != null)
            flag1 = flag | mVerticalTrack.setState(ai);
        flag = flag1;
        if(mVerticalThumb != null)
            flag = flag1 | mVerticalThumb.setState(ai);
        flag1 = flag;
        if(mHorizontalTrack != null)
            flag1 = flag | mHorizontalTrack.setState(ai);
        flag = flag1;
        if(mHorizontalThumb != null)
            flag = flag1 | mHorizontalThumb.setState(ai);
        return flag;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public void setAlpha(int i)
    {
        mAlpha = i;
        mHasSetAlpha = true;
        if(mVerticalTrack != null)
            mVerticalTrack.setAlpha(i);
        if(mVerticalThumb != null)
            mVerticalThumb.setAlpha(i);
        if(mHorizontalTrack != null)
            mHorizontalTrack.setAlpha(i);
        if(mHorizontalThumb != null)
            mHorizontalThumb.setAlpha(i);
    }

    public void setAlwaysDrawHorizontalTrack(boolean flag)
    {
        mAlwaysDrawHorizontalTrack = flag;
    }

    public void setAlwaysDrawVerticalTrack(boolean flag)
    {
        mAlwaysDrawVerticalTrack = flag;
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mColorFilter = colorfilter;
        mHasSetColorFilter = true;
        if(mVerticalTrack != null)
            mVerticalTrack.setColorFilter(colorfilter);
        if(mVerticalThumb != null)
            mVerticalThumb.setColorFilter(colorfilter);
        if(mHorizontalTrack != null)
            mHorizontalTrack.setColorFilter(colorfilter);
        if(mHorizontalThumb != null)
            mHorizontalThumb.setColorFilter(colorfilter);
    }

    public void setHorizontalThumbDrawable(Drawable drawable)
    {
        if(mHorizontalThumb != null)
            mHorizontalThumb.setCallback(null);
        propagateCurrentState(drawable);
        mHorizontalThumb = drawable;
    }

    public void setHorizontalTrackDrawable(Drawable drawable)
    {
        if(mHorizontalTrack != null)
            mHorizontalTrack.setCallback(null);
        propagateCurrentState(drawable);
        mHorizontalTrack = drawable;
    }

    public void setParameters(int i, int j, int k, boolean flag)
    {
        if(mVertical != flag)
        {
            mVertical = flag;
            mBoundsChanged = true;
        }
        break MISSING_BLOCK_LABEL_20;
        if(mRange != i || mOffset != j || mExtent != k)
        {
            mRange = i;
            mOffset = j;
            mExtent = k;
            mRangeChanged = true;
        }
        return;
    }

    public void setVerticalThumbDrawable(Drawable drawable)
    {
        if(mVerticalThumb != null)
            mVerticalThumb.setCallback(null);
        propagateCurrentState(drawable);
        mVerticalThumb = drawable;
    }

    public void setVerticalTrackDrawable(Drawable drawable)
    {
        if(mVerticalTrack != null)
            mVerticalTrack.setCallback(null);
        propagateCurrentState(drawable);
        mVerticalTrack = drawable;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("ScrollBarDrawable: range=").append(mRange).append(" offset=").append(mOffset).append(" extent=").append(mExtent);
        String s;
        if(mVertical)
            s = " V";
        else
            s = " H";
        return stringbuilder.append(s).toString();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    private int mAlpha;
    private boolean mAlwaysDrawHorizontalTrack;
    private boolean mAlwaysDrawVerticalTrack;
    private boolean mBoundsChanged;
    private ColorFilter mColorFilter;
    private int mExtent;
    private boolean mHasSetAlpha;
    private boolean mHasSetColorFilter;
    private Drawable mHorizontalThumb;
    private Drawable mHorizontalTrack;
    private boolean mMutated;
    private int mOffset;
    private int mRange;
    private boolean mRangeChanged;
    private boolean mVertical;
    private Drawable mVerticalThumb;
    private Drawable mVerticalTrack;
}
