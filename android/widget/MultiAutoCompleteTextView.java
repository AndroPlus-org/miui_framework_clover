// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.text.*;
import android.text.method.QwertyKeyListener;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            AutoCompleteTextView, Filter

public class MultiAutoCompleteTextView extends AutoCompleteTextView
{
    public static class CommaTokenizer
        implements Tokenizer
    {

        public int findTokenEnd(CharSequence charsequence, int i)
        {
            int j;
            for(j = charsequence.length(); i < j; i++)
                if(charsequence.charAt(i) == ',')
                    return i;

            return j;
        }

        public int findTokenStart(CharSequence charsequence, int i)
        {
            int j = i;
            int k;
            do
            {
                k = j;
                if(j <= 0)
                    break;
                k = j;
                if(charsequence.charAt(j - 1) == ',')
                    break;
                j--;
            } while(true);
            for(; k < i && charsequence.charAt(k) == ' '; k++);
            return k;
        }

        public CharSequence terminateToken(CharSequence charsequence)
        {
            int i;
            for(i = charsequence.length(); i > 0 && charsequence.charAt(i - 1) == ' '; i--);
            if(i > 0 && charsequence.charAt(i - 1) == ',')
                return charsequence;
            if(charsequence instanceof Spanned)
            {
                SpannableString spannablestring = new SpannableString((new StringBuilder()).append(charsequence).append(", ").toString());
                TextUtils.copySpansFrom((Spanned)charsequence, 0, charsequence.length(), java/lang/Object, spannablestring, 0);
                return spannablestring;
            } else
            {
                return (new StringBuilder()).append(charsequence).append(", ").toString();
            }
        }

        public CommaTokenizer()
        {
        }
    }

    public static interface Tokenizer
    {

        public abstract int findTokenEnd(CharSequence charsequence, int i);

        public abstract int findTokenStart(CharSequence charsequence, int i);

        public abstract CharSequence terminateToken(CharSequence charsequence);
    }


    public MultiAutoCompleteTextView(Context context)
    {
        this(context, null);
    }

    public MultiAutoCompleteTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101006b);
    }

    public MultiAutoCompleteTextView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public MultiAutoCompleteTextView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public boolean enoughToFilter()
    {
        Editable editable = getText();
        int i = getSelectionEnd();
        if(i < 0 || mTokenizer == null)
            return false;
        return i - mTokenizer.findTokenStart(editable, i) >= getThreshold();
    }

    void finishInit()
    {
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/MultiAutoCompleteTextView.getName();
    }

    protected void performFiltering(CharSequence charsequence, int i)
    {
        if(!enoughToFilter()) goto _L2; else goto _L1
_L1:
        int j = getSelectionEnd();
        performFiltering(charsequence, mTokenizer.findTokenStart(charsequence, j), j, i);
_L4:
        return;
_L2:
        dismissDropDown();
        charsequence = getFilter();
        if(charsequence != null)
            charsequence.filter(null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void performFiltering(CharSequence charsequence, int i, int j, int k)
    {
        getFilter().filter(charsequence.subSequence(i, j), this);
    }

    public void performValidation()
    {
        AutoCompleteTextView.Validator validator = getValidator();
        if(validator == null || mTokenizer == null)
            return;
        Editable editable = getText();
        int i = getText().length();
        while(i > 0) 
        {
            int j = mTokenizer.findTokenStart(editable, i);
            CharSequence charsequence = editable.subSequence(j, mTokenizer.findTokenEnd(editable, j));
            if(TextUtils.isEmpty(charsequence))
                editable.replace(j, i, "");
            else
            if(!validator.isValid(charsequence))
                editable.replace(j, i, mTokenizer.terminateToken(validator.fixText(charsequence)));
            i = j;
        }
    }

    protected void replaceText(CharSequence charsequence)
    {
        clearComposingText();
        int i = getSelectionEnd();
        int j = mTokenizer.findTokenStart(getText(), i);
        Editable editable = getText();
        QwertyKeyListener.markAsReplaced(editable, j, i, TextUtils.substring(editable, j, i));
        editable.replace(j, i, mTokenizer.terminateToken(charsequence));
    }

    public void setTokenizer(Tokenizer tokenizer)
    {
        mTokenizer = tokenizer;
    }

    private Tokenizer mTokenizer;
}
