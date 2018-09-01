// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.*;
import android.text.method.DialerKeyListener;
import android.text.method.TextKeyListener;
import android.util.AttributeSet;
import android.view.KeyEvent;

// Referenced classes of package android.widget:
//            RelativeLayout, EditText, ImageView

public class DialerFilter extends RelativeLayout
{

    public DialerFilter(Context context)
    {
        super(context);
    }

    public DialerFilter(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void makeDigitsPrimary()
    {
        if(mPrimary == mLetters)
            swapPrimaryAndHint(false);
    }

    private void makeLettersPrimary()
    {
        if(mPrimary == mDigits)
            swapPrimaryAndHint(true);
    }

    private void swapPrimaryAndHint(boolean flag)
    {
        Editable editable = mLetters.getText();
        Editable editable1 = mDigits.getText();
        android.text.method.KeyListener keylistener = mLetters.getKeyListener();
        android.text.method.KeyListener keylistener1 = mDigits.getKeyListener();
        if(flag)
        {
            mLetters = mPrimary;
            mDigits = mHint;
        } else
        {
            mLetters = mHint;
            mDigits = mPrimary;
        }
        mLetters.setKeyListener(keylistener);
        mLetters.setText(editable);
        editable = mLetters.getText();
        Selection.setSelection(editable, editable.length());
        mDigits.setKeyListener(keylistener1);
        mDigits.setText(editable1);
        editable1 = mDigits.getText();
        Selection.setSelection(editable1, editable1.length());
        mPrimary.setFilters(mInputFilters);
        mHint.setFilters(mInputFilters);
    }

    public void append(String s)
    {
        mMode;
        JVM INSTR tableswitch 1 5: default 40
    //                   1 41
    //                   2 89
    //                   3 72
    //                   4 72
    //                   5 89;
           goto _L1 _L2 _L3 _L4 _L4 _L3
_L1:
        return;
_L2:
        mDigits.getText().append(s);
        mLetters.getText().append(s);
        continue; /* Loop/switch isn't completed */
_L4:
        mDigits.getText().append(s);
        continue; /* Loop/switch isn't completed */
_L3:
        mLetters.getText().append(s);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void clearText()
    {
        mLetters.getText().clear();
        mDigits.getText().clear();
        if(mIsQwerty)
            setMode(1);
        else
            setMode(4);
    }

    public CharSequence getDigits()
    {
        if(mDigits.getVisibility() == 0)
            return mDigits.getText();
        else
            return "";
    }

    public CharSequence getFilterText()
    {
        if(mMode != 4)
            return getLetters();
        else
            return getDigits();
    }

    public CharSequence getLetters()
    {
        if(mLetters.getVisibility() == 0)
            return mLetters.getText();
        else
            return "";
    }

    public int getMode()
    {
        return mMode;
    }

    public boolean isQwertyKeyboard()
    {
        return mIsQwerty;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mInputFilters = (new InputFilter[] {
            new android.text.InputFilter.AllCaps()
        });
        mHint = (EditText)findViewById(0x1020005);
        if(mHint == null)
            throw new IllegalStateException("DialerFilter must have a child EditText named hint");
        mHint.setFilters(mInputFilters);
        mLetters = mHint;
        mLetters.setKeyListener(TextKeyListener.getInstance());
        mLetters.setMovementMethod(null);
        mLetters.setFocusable(false);
        mPrimary = (EditText)findViewById(0x102000c);
        if(mPrimary == null)
        {
            throw new IllegalStateException("DialerFilter must have a child EditText named primary");
        } else
        {
            mPrimary.setFilters(mInputFilters);
            mDigits = mPrimary;
            mDigits.setKeyListener(DialerKeyListener.getInstance());
            mDigits.setMovementMethod(null);
            mDigits.setFocusable(false);
            mIcon = (ImageView)findViewById(0x1020006);
            setFocusable(true);
            mIsQwerty = true;
            setMode(1);
            return;
        }
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        if(mIcon != null)
        {
            rect = mIcon;
            if(flag)
                i = 0;
            else
                i = 8;
            rect.setVisibility(i);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        i;
        JVM INSTR lookupswitch 7: default 72
    //                   19: 115
    //                   20: 115
    //                   21: 115
    //                   22: 115
    //                   23: 115
    //                   66: 115
    //                   67: 127;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L3
_L1:
        mMode;
        JVM INSTR tableswitch 1 5: default 112
    //                   1 329
    //                   2 438
    //                   3 424
    //                   4 424
    //                   5 438;
           goto _L4 _L5 _L6 _L7 _L7 _L6
_L4:
        flag1 = flag;
          goto _L2
_L9:
        if(!flag1)
            return super.onKeyDown(i, keyevent);
        else
            return true;
_L3:
        switch(mMode)
        {
        default:
            flag1 = flag;
            break;

        case 1: // '\001'
            flag1 = mDigits.onKeyDown(i, keyevent) & mLetters.onKeyDown(i, keyevent);
            break;

        case 2: // '\002'
            flag = mLetters.onKeyDown(i, keyevent);
            flag1 = flag;
            if(mLetters.getText().length() == mDigits.getText().length())
            {
                setMode(1);
                flag1 = flag;
            }
            break;

        case 3: // '\003'
            if(mDigits.getText().length() == mLetters.getText().length())
            {
                mLetters.onKeyDown(i, keyevent);
                setMode(1);
            }
            flag1 = mDigits.onKeyDown(i, keyevent);
            break;

        case 4: // '\004'
            flag1 = mDigits.onKeyDown(i, keyevent);
            break;

        case 5: // '\005'
            flag1 = mLetters.onKeyDown(i, keyevent);
            break;
        }
_L2:
        if(true) goto _L9; else goto _L8
_L8:
_L5:
        flag = mLetters.onKeyDown(i, keyevent);
        if(!KeyEvent.isModifierKey(i)) goto _L11; else goto _L10
_L10:
        mDigits.onKeyDown(i, keyevent);
        flag1 = true;
          goto _L9
_L11:
        if(!keyevent.isPrintingKey() && i != 62) goto _L13; else goto _L12
_L12:
        if(keyevent.getMatch(DialerKeyListener.CHARACTERS) != 0)
        {
            flag1 = flag & mDigits.onKeyDown(i, keyevent);
        } else
        {
            setMode(2);
            flag1 = flag;
        }
        break; /* Loop/switch isn't completed */
_L13:
        flag1 = flag;
        if(i != 61) goto _L9; else goto _L12
_L7:
        flag1 = mDigits.onKeyDown(i, keyevent);
          goto _L9
_L6:
        flag1 = mLetters.onKeyDown(i, keyevent);
          goto _L9
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag = mLetters.onKeyUp(i, keyevent);
        boolean flag1 = mDigits.onKeyUp(i, keyevent);
        if(flag)
            flag1 = true;
        return flag1;
    }

    protected void onModeChange(int i, int j)
    {
    }

    public void removeFilterWatcher(TextWatcher textwatcher)
    {
        Editable editable;
        if(mMode != 4)
            editable = mLetters.getText();
        else
            editable = mDigits.getText();
        editable.removeSpan(textwatcher);
    }

    public void setDigitsWatcher(TextWatcher textwatcher)
    {
        Editable editable = mDigits.getText();
        ((Spannable)editable).setSpan(textwatcher, 0, editable.length(), 18);
    }

    public void setFilterWatcher(TextWatcher textwatcher)
    {
        if(mMode != 4)
            setLettersWatcher(textwatcher);
        else
            setDigitsWatcher(textwatcher);
    }

    public void setLettersWatcher(TextWatcher textwatcher)
    {
        Editable editable = mLetters.getText();
        ((Spannable)editable).setSpan(textwatcher, 0, editable.length(), 18);
    }

    public void setMode(int i)
    {
        i;
        JVM INSTR tableswitch 1 5: default 36
    //                   1 53
    //                   2 147
    //                   3 124
    //                   4 76
    //                   5 100;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        int j = mMode;
        mMode = i;
        onModeChange(j, i);
        return;
_L2:
        makeDigitsPrimary();
        mLetters.setVisibility(0);
        mDigits.setVisibility(0);
        continue; /* Loop/switch isn't completed */
_L5:
        makeDigitsPrimary();
        mLetters.setVisibility(8);
        mDigits.setVisibility(0);
        continue; /* Loop/switch isn't completed */
_L6:
        makeLettersPrimary();
        mLetters.setVisibility(0);
        mDigits.setVisibility(8);
        continue; /* Loop/switch isn't completed */
_L4:
        makeDigitsPrimary();
        mLetters.setVisibility(4);
        mDigits.setVisibility(0);
        continue; /* Loop/switch isn't completed */
_L3:
        makeLettersPrimary();
        mLetters.setVisibility(0);
        mDigits.setVisibility(4);
        if(true) goto _L1; else goto _L7
_L7:
    }

    public static final int DIGITS_AND_LETTERS = 1;
    public static final int DIGITS_AND_LETTERS_NO_DIGITS = 2;
    public static final int DIGITS_AND_LETTERS_NO_LETTERS = 3;
    public static final int DIGITS_ONLY = 4;
    public static final int LETTERS_ONLY = 5;
    EditText mDigits;
    EditText mHint;
    ImageView mIcon;
    InputFilter mInputFilters[];
    private boolean mIsQwerty;
    EditText mLetters;
    int mMode;
    EditText mPrimary;
}
