// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.text.*;
import com.android.i18n.phonenumbers.AsYouTypeFormatter;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import java.util.Locale;

// Referenced classes of package android.telephony:
//            PhoneNumberUtils

public class PhoneNumberFormattingTextWatcher
    implements TextWatcher
{

    public PhoneNumberFormattingTextWatcher()
    {
        this(Locale.getDefault().getCountry());
    }

    public PhoneNumberFormattingTextWatcher(String s)
    {
        mSelfChange = false;
        if(s == null)
        {
            throw new IllegalArgumentException();
        } else
        {
            mFormatter = PhoneNumberUtil.getInstance().getAsYouTypeFormatter(s);
            return;
        }
    }

    private String getFormattedNumber(char c, boolean flag)
    {
        String s;
        if(flag)
            s = mFormatter.inputDigitAndRememberPosition(c);
        else
            s = mFormatter.inputDigit(c);
        return s;
    }

    private boolean hasSeparator(CharSequence charsequence, int i, int j)
    {
        for(int k = i; k < i + j; k++)
            if(!PhoneNumberUtils.isNonSeparator(charsequence.charAt(k)))
                return true;

        return false;
    }

    private String reformat(CharSequence charsequence, int i)
    {
        String s = null;
        mFormatter.clear();
        char c = '\0';
        boolean flag = false;
        int j = charsequence.length();
        int k = 0;
        char c1;
        for(c1 = c; k < j; c1 = c)
        {
            char c2 = charsequence.charAt(k);
            String s1 = s;
            boolean flag1 = flag;
            c = c1;
            if(PhoneNumberUtils.isNonSeparator(c2))
            {
                flag1 = flag;
                if(c1 != 0)
                {
                    s = getFormattedNumber(c1, flag);
                    flag1 = false;
                }
                c = c2;
                s1 = s;
            }
            flag = flag1;
            if(k == i - 1)
                flag = true;
            k++;
            s = s1;
        }

        if(c1 != 0)
            s = getFormattedNumber(c1, flag);
        return s;
    }

    private void stopFormatting()
    {
        mStopFormatting = true;
        mFormatter.clear();
    }

    public void afterTextChanged(Editable editable)
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        if(!mStopFormatting)
            break MISSING_BLOCK_LABEL_33;
        if(editable.length() == 0)
            flag = false;
        mStopFormatting = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        flag = mSelfChange;
        if(!flag)
            break MISSING_BLOCK_LABEL_45;
        this;
        JVM INSTR monitorexit ;
        return;
        String s = reformat(editable, Selection.getSelectionEnd(editable));
        if(s == null)
            break MISSING_BLOCK_LABEL_117;
        int i = mFormatter.getRememberedPosition();
        mSelfChange = true;
        editable.replace(0, editable.length(), s, 0, s.length());
        if(s.equals(editable.toString()))
            Selection.setSelection(editable, i);
        mSelfChange = false;
        PhoneNumberUtils.ttsSpanAsPhoneNumber(editable, 0, editable.length());
        this;
        JVM INSTR monitorexit ;
        return;
        editable;
        throw editable;
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if(mSelfChange || mStopFormatting)
            return;
        if(j > 0 && hasSeparator(charsequence, i, j))
            stopFormatting();
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if(mSelfChange || mStopFormatting)
            return;
        if(k > 0 && hasSeparator(charsequence, i, k))
            stopFormatting();
    }

    private AsYouTypeFormatter mFormatter;
    private boolean mSelfChange;
    private boolean mStopFormatting;
}
