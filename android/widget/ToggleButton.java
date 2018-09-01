// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            CompoundButton

public class ToggleButton extends CompoundButton
{

    public ToggleButton(Context context)
    {
        this(context, null);
    }

    public ToggleButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101004b);
    }

    public ToggleButton(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ToggleButton(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ToggleButton, i, j);
        mTextOn = context.getText(1);
        mTextOff = context.getText(2);
        mDisabledAlpha = context.getFloat(0, 0.5F);
        syncTextState();
        context.recycle();
    }

    private void syncTextState()
    {
        boolean flag = isChecked();
        if(!flag || mTextOn == null) goto _L2; else goto _L1
_L1:
        setText(mTextOn);
_L4:
        return;
_L2:
        if(!flag && mTextOff != null)
            setText(mTextOff);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateReferenceToIndicatorDrawable(Drawable drawable)
    {
        if(drawable instanceof LayerDrawable)
            mIndicatorDrawable = ((LayerDrawable)drawable).findDrawableByLayerId(0x1020017);
        else
            mIndicatorDrawable = null;
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(mIndicatorDrawable != null)
        {
            Drawable drawable = mIndicatorDrawable;
            int i;
            if(isEnabled())
                i = 255;
            else
                i = (int)(mDisabledAlpha * 255F);
            drawable.setAlpha(i);
        }
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ToggleButton.getName();
    }

    public CharSequence getTextOff()
    {
        return mTextOff;
    }

    public CharSequence getTextOn()
    {
        return mTextOn;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        updateReferenceToIndicatorDrawable(getBackground());
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
        updateReferenceToIndicatorDrawable(drawable);
    }

    public void setChecked(boolean flag)
    {
        super.setChecked(flag);
        syncTextState();
    }

    public void setTextOff(CharSequence charsequence)
    {
        mTextOff = charsequence;
    }

    public void setTextOn(CharSequence charsequence)
    {
        mTextOn = charsequence;
    }

    private static final int NO_ALPHA = 255;
    private float mDisabledAlpha;
    private Drawable mIndicatorDrawable;
    private CharSequence mTextOff;
    private CharSequence mTextOn;
}
