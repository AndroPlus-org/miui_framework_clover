// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.widget:
//            AbsSeekBar

public class RatingBar extends AbsSeekBar
{
    public static interface OnRatingBarChangeListener
    {

        public abstract void onRatingChanged(RatingBar ratingbar, float f, boolean flag);
    }


    public RatingBar(Context context)
    {
        this(context, null);
    }

    public RatingBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101007c);
    }

    public RatingBar(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public RatingBar(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mNumStars = 5;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RatingBar, i, j);
        i = context.getInt(0, mNumStars);
        setIsIndicator(context.getBoolean(3, mIsUserSeekable ^ true));
        float f = context.getFloat(1, -1F);
        float f1 = context.getFloat(2, -1F);
        context.recycle();
        if(i > 0 && i != mNumStars)
            setNumStars(i);
        if(f1 >= 0.0F)
            setStepSize(f1);
        else
            setStepSize(0.5F);
        if(f >= 0.0F)
            setRating(f);
        mTouchProgressOffset = 0.6F;
    }

    private float getProgressPerStar()
    {
        if(mNumStars > 0)
            return ((float)getMax() * 1.0F) / (float)mNumStars;
        else
            return 1.0F;
    }

    private void updateSecondaryProgress(int i)
    {
        float f = getProgressPerStar();
        if(f > 0.0F)
            setSecondaryProgress((int)(Math.ceil((float)i / f) * (double)f));
    }

    boolean canUserSetProgress()
    {
        boolean flag;
        if(super.canUserSetProgress())
            flag = isIndicator() ^ true;
        else
            flag = false;
        return flag;
    }

    void dispatchRatingChange(boolean flag)
    {
        if(mOnRatingBarChangeListener != null)
            mOnRatingBarChangeListener.onRatingChanged(this, getRating(), flag);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/RatingBar.getName();
    }

    Shape getDrawableShape()
    {
        return new RectShape();
    }

    public int getNumStars()
    {
        return mNumStars;
    }

    public OnRatingBarChangeListener getOnRatingBarChangeListener()
    {
        return mOnRatingBarChangeListener;
    }

    public float getRating()
    {
        return (float)getProgress() / getProgressPerStar();
    }

    public float getStepSize()
    {
        return (float)getNumStars() / (float)getMax();
    }

    public boolean isIndicator()
    {
        return mIsUserSeekable ^ true;
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(canUserSetProgress())
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
    }

    void onKeyChange()
    {
        super.onKeyChange();
        dispatchRatingChange(true);
    }

    protected void onMeasure(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        super.onMeasure(i, j);
        if(mSampleWidth > 0)
            setMeasuredDimension(resolveSizeAndState(mSampleWidth * mNumStars, i, 0), getMeasuredHeight());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void onProgressRefresh(float f, boolean flag, int i)
    {
        super.onProgressRefresh(f, flag, i);
        updateSecondaryProgress(i);
        if(!flag)
            dispatchRatingChange(false);
    }

    void onStartTrackingTouch()
    {
        mProgressOnStartTracking = getProgress();
        super.onStartTrackingTouch();
    }

    void onStopTrackingTouch()
    {
        super.onStopTrackingTouch();
        if(getProgress() != mProgressOnStartTracking)
            dispatchRatingChange(true);
    }

    public void setIsIndicator(boolean flag)
    {
        mIsUserSeekable = flag ^ true;
        if(flag)
            setFocusable(16);
        else
            setFocusable(1);
    }

    public void setMax(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(i <= 0)
            return;
        super.setMax(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setNumStars(int i)
    {
        if(i <= 0)
        {
            return;
        } else
        {
            mNumStars = i;
            requestLayout();
            return;
        }
    }

    public void setOnRatingBarChangeListener(OnRatingBarChangeListener onratingbarchangelistener)
    {
        mOnRatingBarChangeListener = onratingbarchangelistener;
    }

    public void setRating(float f)
    {
        setProgress(Math.round(getProgressPerStar() * f));
    }

    public void setStepSize(float f)
    {
        if(f <= 0.0F)
        {
            return;
        } else
        {
            f = (float)mNumStars / f;
            int i = (int)((f / (float)getMax()) * (float)getProgress());
            setMax((int)f);
            setProgress(i);
            return;
        }
    }

    private int mNumStars;
    private OnRatingBarChangeListener mOnRatingBarChangeListener;
    private int mProgressOnStartTracking;
}
