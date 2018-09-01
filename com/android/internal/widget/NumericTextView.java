// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.KeyEvent;
import android.widget.TextView;

public class NumericTextView extends TextView
{
    public static interface OnValueChangedListener
    {

        public abstract void onValueChanged(NumericTextView numerictextview, int i, boolean flag, boolean flag1);
    }


    public NumericTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMinValue = 0;
        mMaxValue = 99;
        mMaxCount = 2;
        mShowLeadingZeroes = true;
        setHintTextColor(getTextColors().getColorForState(StateSet.get(0), 0));
        setFocusable(true);
    }

    private boolean handleKeyUp(int i)
    {
        String s;
        if(i == 67)
        {
            if(mCount > 0)
            {
                mValue = mValue / 10;
                mCount = mCount - 1;
            }
        } else
        if(isKeyCodeNumeric(i))
        {
            if(mCount < mMaxCount)
            {
                i = numericKeyCodeToInt(i);
                i = mValue * 10 + i;
                if(i <= mMaxValue)
                {
                    mValue = i;
                    mCount = mCount + 1;
                }
            }
        } else
        {
            return false;
        }
        if(mCount > 0)
            s = String.format((new StringBuilder()).append("%0").append(mCount).append("d").toString(), new Object[] {
                Integer.valueOf(mValue)
            });
        else
            s = "";
        setText(s);
        if(mListener != null)
        {
            boolean flag;
            boolean flag1;
            if(mValue >= mMinValue)
                flag = true;
            else
                flag = false;
            if(mCount >= mMaxCount || mValue * 10 > mMaxValue)
                flag1 = true;
            else
                flag1 = false;
            mListener.onValueChanged(this, mValue, flag, flag1);
        }
        return true;
    }

    private static boolean isKeyCodeNumeric(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 7) goto _L2; else goto _L1
_L1:
        if(i != 8) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 9)
        {
            flag1 = flag;
            if(i != 10)
            {
                flag1 = flag;
                if(i != 11)
                {
                    flag1 = flag;
                    if(i != 12)
                    {
                        flag1 = flag;
                        if(i != 13)
                        {
                            flag1 = flag;
                            if(i != 14)
                            {
                                flag1 = flag;
                                if(i != 15)
                                {
                                    flag1 = flag;
                                    if(i != 16)
                                        flag1 = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static int numericKeyCodeToInt(int i)
    {
        return i - 7;
    }

    private void updateDisplayedValue()
    {
        String s;
        if(mShowLeadingZeroes)
            s = (new StringBuilder()).append("%0").append(mMaxCount).append("d").toString();
        else
            s = "%d";
        setText(String.format(s, new Object[] {
            Integer.valueOf(mValue)
        }));
    }

    private void updateMinimumWidth()
    {
        CharSequence charsequence = getText();
        int i = 0;
        for(int j = 0; j < mMaxValue;)
        {
            setText(String.format((new StringBuilder()).append("%0").append(mMaxCount).append("d").toString(), new Object[] {
                Integer.valueOf(j)
            }));
            measure(0, 0);
            int k = getMeasuredWidth();
            int l = i;
            if(k > i)
                l = k;
            j++;
            i = l;
        }

        setText(charsequence);
        setMinWidth(i);
        setMinimumWidth(i);
    }

    public final OnValueChangedListener getOnDigitEnteredListener()
    {
        return mListener;
    }

    public final int getRangeMaximum()
    {
        return mMaxValue;
    }

    public final int getRangeMinimum()
    {
        return mMinValue;
    }

    public final boolean getShowLeadingZeroes()
    {
        return mShowLeadingZeroes;
    }

    public final int getValue()
    {
        return mValue;
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        if(!flag) goto _L2; else goto _L1
_L1:
        mPreviousValue = mValue;
        mValue = 0;
        mCount = 0;
        setHint(getText());
        setText("");
_L4:
        return;
_L2:
        if(mCount == 0)
        {
            mValue = mPreviousValue;
            setText(getHint());
            setHint("");
        }
        if(mValue < mMinValue)
            mValue = mMinValue;
        setValue(mValue);
        if(mListener != null)
            mListener.onValueChanged(this, mValue, true, true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(isKeyCodeNumeric(i) || i == 67)
            flag = true;
        else
            flag = super.onKeyDown(i, keyevent);
        return flag;
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        boolean flag;
        if(isKeyCodeNumeric(i) || i == 67)
            flag = true;
        else
            flag = super.onKeyMultiple(i, j, keyevent);
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(!handleKeyUp(i))
            flag = super.onKeyUp(i, keyevent);
        else
            flag = true;
        return flag;
    }

    public final void setOnDigitEnteredListener(OnValueChangedListener onvaluechangedlistener)
    {
        mListener = onvaluechangedlistener;
    }

    public final void setRange(int i, int j)
    {
        if(mMinValue != i)
            mMinValue = i;
        if(mMaxValue != j)
        {
            mMaxValue = j;
            mMaxCount = (int)(Math.log(j) / LOG_RADIX) + 1;
            updateMinimumWidth();
            updateDisplayedValue();
        }
    }

    public final void setShowLeadingZeroes(boolean flag)
    {
        if(mShowLeadingZeroes != flag)
        {
            mShowLeadingZeroes = flag;
            updateDisplayedValue();
        }
    }

    public final void setValue(int i)
    {
        if(mValue != i)
        {
            mValue = i;
            updateDisplayedValue();
        }
    }

    private static final double LOG_RADIX = Math.log(10D);
    private static final int RADIX = 10;
    private int mCount;
    private OnValueChangedListener mListener;
    private int mMaxCount;
    private int mMaxValue;
    private int mMinValue;
    private int mPreviousValue;
    private boolean mShowLeadingZeroes;
    private int mValue;

}
