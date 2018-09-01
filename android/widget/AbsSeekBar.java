// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.widget:
//            ProgressBar

public abstract class AbsSeekBar extends ProgressBar
{

    public AbsSeekBar(Context context)
    {
        super(context);
        mTempRect = new Rect();
        mThumbTintList = null;
        mThumbTintMode = null;
        mHasThumbTint = false;
        mHasThumbTintMode = false;
        mTickMarkTintList = null;
        mTickMarkTintMode = null;
        mHasTickMarkTint = false;
        mHasTickMarkTintMode = false;
        mIsUserSeekable = true;
        mKeyProgressIncrement = 1;
    }

    public AbsSeekBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTempRect = new Rect();
        mThumbTintList = null;
        mThumbTintMode = null;
        mHasThumbTint = false;
        mHasThumbTintMode = false;
        mTickMarkTintList = null;
        mTickMarkTintMode = null;
        mHasTickMarkTint = false;
        mHasTickMarkTintMode = false;
        mIsUserSeekable = true;
        mKeyProgressIncrement = 1;
    }

    public AbsSeekBar(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AbsSeekBar(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mTempRect = new Rect();
        mThumbTintList = null;
        mThumbTintMode = null;
        mHasThumbTint = false;
        mHasThumbTintMode = false;
        mTickMarkTintList = null;
        mTickMarkTintMode = null;
        mHasTickMarkTint = false;
        mHasTickMarkTintMode = false;
        mIsUserSeekable = true;
        mKeyProgressIncrement = 1;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.SeekBar, i, j);
        setThumb(typedarray.getDrawable(0));
        if(typedarray.hasValue(4))
        {
            mThumbTintMode = Drawable.parseTintMode(typedarray.getInt(4, -1), mThumbTintMode);
            mHasThumbTintMode = true;
        }
        if(typedarray.hasValue(3))
        {
            mThumbTintList = typedarray.getColorStateList(3);
            mHasThumbTint = true;
        }
        setTickMark(typedarray.getDrawable(5));
        if(typedarray.hasValue(7))
        {
            mTickMarkTintMode = Drawable.parseTintMode(typedarray.getInt(7, -1), mTickMarkTintMode);
            mHasTickMarkTintMode = true;
        }
        if(typedarray.hasValue(6))
        {
            mTickMarkTintList = typedarray.getColorStateList(6);
            mHasTickMarkTint = true;
        }
        mSplitTrack = typedarray.getBoolean(2, false);
        setThumbOffset(typedarray.getDimensionPixelOffset(1, getThumbOffset()));
        boolean flag = typedarray.getBoolean(8, true);
        typedarray.recycle();
        if(flag)
        {
            attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Theme, 0, 0);
            mDisabledAlpha = attributeset.getFloat(3, 0.5F);
            attributeset.recycle();
        } else
        {
            mDisabledAlpha = 1.0F;
        }
        applyThumbTint();
        applyTickMarkTint();
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void applyThumbTint()
    {
        if(mThumb != null && (mHasThumbTint || mHasThumbTintMode))
        {
            mThumb = mThumb.mutate();
            if(mHasThumbTint)
                mThumb.setTintList(mThumbTintList);
            if(mHasThumbTintMode)
                mThumb.setTintMode(mThumbTintMode);
            if(mThumb.isStateful())
                mThumb.setState(getDrawableState());
        }
    }

    private void applyTickMarkTint()
    {
        if(mTickMark != null && (mHasTickMarkTint || mHasTickMarkTintMode))
        {
            mTickMark = mTickMark.mutate();
            if(mHasTickMarkTint)
                mTickMark.setTintList(mTickMarkTintList);
            if(mHasTickMarkTintMode)
                mTickMark.setTintMode(mTickMarkTintMode);
            if(mTickMark.isStateful())
                mTickMark.setState(getDrawableState());
        }
    }

    private void attemptClaimDrag()
    {
        if(mParent != null)
            mParent.requestDisallowInterceptTouchEvent(true);
    }

    private float getScale()
    {
        int i = getMin();
        int j = getMax() - i;
        float f;
        if(j > 0)
            f = (float)(getProgress() - i) / (float)j;
        else
            f = 0.0F;
        return f;
    }

    private void setHotspot(float f, float f1)
    {
        Drawable drawable = getBackground();
        if(drawable != null)
            drawable.setHotspot(f, f1);
    }

    private void setThumbPos(int i, Drawable drawable, float f, int j)
    {
        int k = mPaddingLeft;
        int l = mPaddingRight;
        int i1 = drawable.getIntrinsicWidth();
        int k1 = drawable.getIntrinsicHeight();
        k = (i - k - l - i1) + mThumbOffset * 2;
        l = (int)((float)k * f + 0.5F);
        Drawable drawable1;
        if(j == 0x80000000)
        {
            Rect rect = drawable.getBounds();
            i = rect.top;
            j = rect.bottom;
        } else
        {
            i = j;
            j += k1;
        }
        if(isLayoutRtl() && mMirrorForRtl)
            l = k - l;
        k = l + i1;
        drawable1 = getBackground();
        if(drawable1 != null)
        {
            int j1 = mPaddingLeft - mThumbOffset;
            k1 = mPaddingTop;
            drawable1.setHotspotBounds(l + j1, i + k1, k + j1, j + k1);
        }
        drawable.setBounds(l, i, k, j);
    }

    private void startDrag(MotionEvent motionevent)
    {
        setPressed(true);
        if(mThumb != null)
            invalidate(mThumb.getBounds());
        onStartTrackingTouch();
        trackTouchEvent(motionevent);
        attemptClaimDrag();
    }

    private void trackTouchEvent(MotionEvent motionevent)
    {
        int i = Math.round(motionevent.getX());
        int j = Math.round(motionevent.getY());
        int k = getWidth();
        int l = k - mPaddingLeft - mPaddingRight;
        float f = 0.0F;
        float f1;
        float f2;
        if(isLayoutRtl() && mMirrorForRtl)
        {
            if(i > k - mPaddingRight)
                f1 = 0.0F;
            else
            if(i < mPaddingLeft)
            {
                f1 = 1.0F;
            } else
            {
                f1 = (float)((l - i) + mPaddingLeft) / (float)l;
                f = mTouchProgressOffset;
            }
        } else
        if(i < mPaddingLeft)
            f1 = 0.0F;
        else
        if(i > k - mPaddingRight)
        {
            f1 = 1.0F;
        } else
        {
            f1 = (float)(i - mPaddingLeft) / (float)l;
            f = mTouchProgressOffset;
        }
        f2 = getMax() - getMin();
        setHotspot(i, j);
        setProgressInternal(Math.round(f + f2 * f1), true, false);
    }

    private void updateThumbAndTrackPos(int i, int j)
    {
        int k = j - mPaddingTop - mPaddingBottom;
        Drawable drawable = getCurrentDrawable();
        Drawable drawable1 = mThumb;
        int l = Math.min(mMaxHeight, k);
        if(drawable1 == null)
            j = 0;
        else
            j = drawable1.getIntrinsicHeight();
        if(j > l)
        {
            k = (k - j) / 2;
            int i1 = k + (j - l) / 2;
            j = k;
            k = i1;
        } else
        {
            int j1 = (k - l) / 2;
            k = j1;
            j = j1 + (l - j) / 2;
        }
        if(drawable != null)
            drawable.setBounds(0, k, i - mPaddingRight - mPaddingLeft, k + l);
        if(drawable1 != null)
            setThumbPos(i, drawable1, getScale(), j);
    }

    boolean canUserSetProgress()
    {
        boolean flag;
        if(!isIndeterminate())
            flag = isEnabled();
        else
            flag = false;
        return flag;
    }

    void drawThumb(Canvas canvas)
    {
        if(mThumb != null)
        {
            int i = canvas.save();
            canvas.translate(mPaddingLeft - mThumbOffset, mPaddingTop);
            mThumb.draw(canvas);
            canvas.restoreToCount(i);
        }
    }

    protected void drawTickMarks(Canvas canvas)
    {
        if(mTickMark != null)
        {
            int i = getMax() - getMin();
            if(i > 1)
            {
                int j = mTickMark.getIntrinsicWidth();
                int k = mTickMark.getIntrinsicHeight();
                float f;
                if(j >= 0)
                    j /= 2;
                else
                    j = 1;
                if(k >= 0)
                    k /= 2;
                else
                    k = 1;
                mTickMark.setBounds(-j, -k, j, k);
                f = (float)(getWidth() - mPaddingLeft - mPaddingRight) / (float)i;
                k = canvas.save();
                canvas.translate(mPaddingLeft, getHeight() / 2);
                for(j = 0; j <= i; j++)
                {
                    mTickMark.draw(canvas);
                    canvas.translate(f, 0.0F);
                }

                canvas.restoreToCount(k);
            }
        }
    }

    void drawTrack(Canvas canvas)
    {
        Drawable drawable = mThumb;
        if(drawable != null && mSplitTrack)
        {
            Insets insets = drawable.getOpticalInsets();
            Rect rect = mTempRect;
            drawable.copyBounds(rect);
            rect.offset(mPaddingLeft - mThumbOffset, mPaddingTop);
            rect.left = rect.left + insets.left;
            rect.right = rect.right - insets.right;
            int i = canvas.save();
            canvas.clipRect(rect, android.graphics.Region.Op.DIFFERENCE);
            super.drawTrack(canvas);
            drawTickMarks(canvas);
            canvas.restoreToCount(i);
        } else
        {
            super.drawTrack(canvas);
            drawTickMarks(canvas);
        }
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mThumb != null)
            mThumb.setHotspot(f, f1);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = getProgressDrawable();
        if(drawable != null && mDisabledAlpha < 1.0F)
        {
            int i;
            if(isEnabled())
                i = 255;
            else
                i = (int)(mDisabledAlpha * 255F);
            drawable.setAlpha(i);
        }
        drawable = mThumb;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
        drawable = mTickMark;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/AbsSeekBar.getName();
    }

    public int getKeyProgressIncrement()
    {
        return mKeyProgressIncrement;
    }

    public boolean getSplitTrack()
    {
        return mSplitTrack;
    }

    public Drawable getThumb()
    {
        return mThumb;
    }

    public int getThumbOffset()
    {
        return mThumbOffset;
    }

    public ColorStateList getThumbTintList()
    {
        return mThumbTintList;
    }

    public android.graphics.PorterDuff.Mode getThumbTintMode()
    {
        return mThumbTintMode;
    }

    public Drawable getTickMark()
    {
        return mTickMark;
    }

    public ColorStateList getTickMarkTintList()
    {
        return mTickMarkTintList;
    }

    public android.graphics.PorterDuff.Mode getTickMarkTintMode()
    {
        return mTickMarkTintMode;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mThumb != null)
            mThumb.jumpToCurrentState();
        if(mTickMark != null)
            mTickMark.jumpToCurrentState();
    }

    protected void onDraw(Canvas canvas)
    {
        this;
        JVM INSTR monitorenter ;
        super.onDraw(canvas);
        drawThumb(canvas);
        this;
        JVM INSTR monitorexit ;
        return;
        canvas;
        throw canvas;
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(isEnabled())
        {
            int i = getProgress();
            if(i > getMin())
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            if(i < getMax())
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
        }
    }

    void onKeyChange()
    {
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(!isEnabled()) goto _L2; else goto _L1
_L1:
        int j;
        int l;
        j = mKeyProgressIncrement;
        l = j;
        i;
        JVM INSTR lookupswitch 5: default 68
    //                   21: 75
    //                   22: 79
    //                   69: 75
    //                   70: 79
    //                   81: 79;
           goto _L2 _L3 _L4 _L3 _L4 _L4
_L2:
        return super.onKeyDown(i, keyevent);
_L3:
        l = -j;
_L4:
        int k = l;
        if(isLayoutRtl())
            k = -l;
        if(setProgressInternal(getProgress() + k, true, true))
        {
            onKeyChange();
            return true;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void onMeasure(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Drawable drawable = getCurrentDrawable();
        if(mThumb != null) goto _L2; else goto _L1
_L1:
        int k = 0;
_L4:
        int l;
        int i1;
        l = 0;
        i1 = 0;
        if(drawable == null)
            break MISSING_BLOCK_LABEL_72;
        l = Math.max(mMinWidth, Math.min(mMaxWidth, drawable.getIntrinsicWidth()));
        i1 = Math.max(k, Math.max(mMinHeight, Math.min(mMaxHeight, drawable.getIntrinsicHeight())));
        k = mPaddingLeft;
        int j1 = mPaddingRight;
        int k1 = mPaddingTop;
        int l1 = mPaddingBottom;
        setMeasuredDimension(resolveSizeAndState(l + (k + j1), i, 0), resolveSizeAndState(i1 + (k1 + l1), j, 0));
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        k = mThumb.getIntrinsicHeight();
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void onResolveDrawables(int i)
    {
        super.onResolveDrawables(i);
        if(mThumb != null)
            mThumb.setLayoutDirection(i);
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        Drawable drawable = mThumb;
        if(drawable != null)
        {
            setThumbPos(getWidth(), drawable, getScale(), 0x80000000);
            invalidate();
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        updateThumbAndTrackPos(i, j);
    }

    void onStartTrackingTouch()
    {
        mIsDragging = true;
    }

    void onStopTrackingTouch()
    {
        mIsDragging = false;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!mIsUserSeekable || isEnabled() ^ true)
            return false;
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 52
    //                   0 54
    //                   1 124
    //                   2 80
    //                   3 168;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return true;
_L2:
        if(isInScrollingContainer())
            mTouchDownX = motionevent.getX();
        else
            startDrag(motionevent);
        continue; /* Loop/switch isn't completed */
_L4:
        if(mIsDragging)
            trackTouchEvent(motionevent);
        else
        if(Math.abs(motionevent.getX() - mTouchDownX) > (float)mScaledTouchSlop)
            startDrag(motionevent);
        continue; /* Loop/switch isn't completed */
_L3:
        if(mIsDragging)
        {
            trackTouchEvent(motionevent);
            onStopTrackingTouch();
            setPressed(false);
        } else
        {
            onStartTrackingTouch();
            trackTouchEvent(motionevent);
            onStopTrackingTouch();
        }
        invalidate();
        continue; /* Loop/switch isn't completed */
_L5:
        if(mIsDragging)
        {
            onStopTrackingTouch();
            setPressed(false);
        }
        invalidate();
        if(true) goto _L1; else goto _L6
_L6:
    }

    void onVisualProgressChanged(int i, float f)
    {
        super.onVisualProgressChanged(i, f);
        if(i == 0x102000d)
        {
            Drawable drawable = mThumb;
            if(drawable != null)
            {
                setThumbPos(getWidth(), drawable, f, 0x80000000);
                invalidate();
            }
        }
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(super.performAccessibilityActionInternal(i, bundle))
            return true;
        if(!isEnabled())
            return false;
        switch(i)
        {
        default:
            return false;

        case 16908349: 
            if(!canUserSetProgress())
                return false;
            if(bundle == null || bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE") ^ true)
                return false;
            else
                return setProgressInternal((int)bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"), true, true);

        case 4096: 
        case 8192: 
            break;
        }
        if(!canUserSetProgress())
            return false;
        int j = Math.max(1, Math.round((float)(getMax() - getMin()) / 20F));
        int k = j;
        if(i == 8192)
            k = -j;
        if(setProgressInternal(getProgress() + k, true, true))
        {
            onKeyChange();
            return true;
        } else
        {
            return false;
        }
    }

    public void setKeyProgressIncrement(int i)
    {
        int j = i;
        if(i < 0)
            j = -i;
        mKeyProgressIncrement = j;
    }

    public void setMax(int i)
    {
        this;
        JVM INSTR monitorenter ;
        super.setMax(i);
        i = getMax() - getMin();
        if(mKeyProgressIncrement == 0 || i / mKeyProgressIncrement > 20)
            setKeyProgressIncrement(Math.max(1, Math.round((float)i / 20F)));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setMin(int i)
    {
        this;
        JVM INSTR monitorenter ;
        super.setMin(i);
        i = getMax() - getMin();
        if(mKeyProgressIncrement == 0 || i / mKeyProgressIncrement > 20)
            setKeyProgressIncrement(Math.max(1, Math.round((float)i / 20F)));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setSplitTrack(boolean flag)
    {
        mSplitTrack = flag;
        invalidate();
    }

    public void setThumb(Drawable drawable)
    {
        boolean flag;
        if(mThumb != null && drawable != mThumb)
        {
            mThumb.setCallback(null);
            flag = true;
        } else
        {
            flag = false;
        }
        if(drawable != null)
        {
            drawable.setCallback(this);
            if(canResolveLayoutDirection())
                drawable.setLayoutDirection(getLayoutDirection());
            mThumbOffset = drawable.getIntrinsicWidth() / 2;
            if(flag && (drawable.getIntrinsicWidth() != mThumb.getIntrinsicWidth() || drawable.getIntrinsicHeight() != mThumb.getIntrinsicHeight()))
                requestLayout();
        }
        mThumb = drawable;
        applyThumbTint();
        invalidate();
        if(flag)
        {
            updateThumbAndTrackPos(getWidth(), getHeight());
            if(drawable != null && drawable.isStateful())
                drawable.setState(getDrawableState());
        }
    }

    public void setThumbOffset(int i)
    {
        mThumbOffset = i;
        invalidate();
    }

    public void setThumbTintList(ColorStateList colorstatelist)
    {
        mThumbTintList = colorstatelist;
        mHasThumbTint = true;
        applyThumbTint();
    }

    public void setThumbTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mThumbTintMode = mode;
        mHasThumbTintMode = true;
        applyThumbTint();
    }

    public void setTickMark(Drawable drawable)
    {
        if(mTickMark != null)
            mTickMark.setCallback(null);
        mTickMark = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            if(drawable.isStateful())
                drawable.setState(getDrawableState());
            applyTickMarkTint();
        }
        invalidate();
    }

    public void setTickMarkTintList(ColorStateList colorstatelist)
    {
        mTickMarkTintList = colorstatelist;
        mHasTickMarkTint = true;
        applyTickMarkTint();
    }

    public void setTickMarkTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mTickMarkTintMode = mode;
        mHasTickMarkTintMode = true;
        applyTickMarkTint();
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if(drawable == mThumb || drawable == mTickMark)
            flag = true;
        else
            flag = super.verifyDrawable(drawable);
        return flag;
    }

    private static final int NO_ALPHA = 255;
    private float mDisabledAlpha;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTickMarkTint;
    private boolean mHasTickMarkTintMode;
    private boolean mIsDragging;
    boolean mIsUserSeekable;
    private int mKeyProgressIncrement;
    private int mScaledTouchSlop;
    private boolean mSplitTrack;
    private final Rect mTempRect;
    private Drawable mThumb;
    private int mThumbOffset;
    private ColorStateList mThumbTintList;
    private android.graphics.PorterDuff.Mode mThumbTintMode;
    private Drawable mTickMark;
    private ColorStateList mTickMarkTintList;
    private android.graphics.PorterDuff.Mode mTickMarkTintMode;
    private float mTouchDownX;
    float mTouchProgressOffset;
}
