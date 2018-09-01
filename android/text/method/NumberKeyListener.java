// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.icu.text.DecimalFormatSymbols;
import android.text.*;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import java.util.*;
import libcore.icu.LocaleData;

// Referenced classes of package android.text.method:
//            BaseKeyListener

public abstract class NumberKeyListener extends BaseKeyListener
    implements InputFilter
{

    public NumberKeyListener()
    {
    }

    static boolean addAmPmChars(Collection collection, Locale locale)
    {
        if(locale == null)
            return false;
        locale = LocaleData.get(locale).amPm;
        for(int i = 0; i < locale.length; i++)
        {
            for(int j = 0; j < locale[i].length();)
            {
                char c = locale[i].charAt(j);
                if(Character.isBmpCodePoint(c))
                {
                    collection.add(Character.valueOf(c));
                    j++;
                } else
                {
                    return false;
                }
            }

        }

        return true;
    }

    static boolean addDigits(Collection collection, Locale locale)
    {
        if(locale == null)
            return false;
        locale = DecimalFormatSymbols.getInstance(locale).getDigitStrings();
        for(int i = 0; i < 10; i++)
        {
            if(locale[i].length() > 1)
                return false;
            collection.add(Character.valueOf(locale[i].charAt(0)));
        }

        return true;
    }

    static boolean addFormatCharsFromSkeleton(Collection collection, Locale locale, String s, String s1)
    {
        boolean flag;
        int i;
        if(locale == null)
            return false;
        locale = DateFormat.getBestDateTimePattern(locale, s);
        flag = true;
        i = 0;
_L2:
        char c;
        boolean flag1;
        if(i >= locale.length())
            break MISSING_BLOCK_LABEL_149;
        c = locale.charAt(i);
        if(Character.isSurrogate(c))
            return false;
        flag1 = flag;
        if(c != '\'')
            break; /* Loop/switch isn't completed */
        boolean flag2 = flag ^ true;
        flag = flag2;
        if(i != 0)
        {
            flag1 = flag2;
            if(locale.charAt(i - 1) == '\'')
                break; /* Loop/switch isn't completed */
            flag = flag2;
        }
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(!flag1)
            break MISSING_BLOCK_LABEL_130;
        flag = flag1;
        if(s1.indexOf(c) != -1) goto _L4; else goto _L3
_L3:
        if("GyYuUrQqMLlwWdDFgEecabBhHKkjJCmsSAzZOvVXx".indexOf(c) != -1)
            return false;
        collection.add(Character.valueOf(c));
        flag = flag1;
          goto _L4
        return true;
    }

    static boolean addFormatCharsFromSkeletons(Collection collection, Locale locale, String as[], String s)
    {
        for(int i = 0; i < as.length; i++)
            if(!addFormatCharsFromSkeleton(collection, locale, as[i], s))
                return false;

        return true;
    }

    static char[] collectionToArray(Collection collection)
    {
        char ac[] = new char[collection.size()];
        int i = 0;
        for(collection = collection.iterator(); collection.hasNext();)
        {
            ac[i] = ((Character)collection.next()).charValue();
            i++;
        }

        return ac;
    }

    protected static boolean ok(char ac[], char c)
    {
        for(int i = ac.length - 1; i >= 0; i--)
            if(ac[i] == c)
                return true;

        return false;
    }

    public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
    {
        spanned = getAcceptedChars();
        k = i;
        do
        {
            if(k >= j || !ok(spanned, charsequence.charAt(k)))
            {
                if(k == j)
                    return null;
                break;
            }
            k++;
        } while(true);
        if(j - i == 1)
            return "";
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(charsequence, i, j);
        j -= i;
        for(j--; j >= k - i; j--)
            if(!ok(spanned, charsequence.charAt(j)))
                spannablestringbuilder.delete(j, j + 1);

        return spannablestringbuilder;
    }

    protected abstract char[] getAcceptedChars();

    protected int lookup(KeyEvent keyevent, Spannable spannable)
    {
        return keyevent.getMatch(getAcceptedChars(), getMetaState(spannable, keyevent));
    }

    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyevent)
    {
        int l;
        int i1;
label0:
        {
            int j = Selection.getSelectionStart(editable);
            l = Selection.getSelectionEnd(editable);
            i1 = Math.min(j, l);
            j = Math.max(j, l);
            if(i1 >= 0)
            {
                l = j;
                if(j >= 0)
                    break label0;
            }
            l = 0;
            i1 = 0;
            Selection.setSelection(editable, 0);
        }
        int k;
        int j1;
        if(keyevent != null)
            k = lookup(keyevent, editable);
        else
            k = 0;
        if(keyevent != null)
            j1 = keyevent.getRepeatCount();
        else
            j1 = 0;
        if(j1 == 0)
        {
            if(k != 0)
            {
                if(i1 != l)
                    Selection.setSelection(editable, l);
                editable.replace(i1, l, String.valueOf((char)k));
                adjustMetaAfterKeypress(editable);
                return true;
            }
        } else
        if(k == 48 && j1 == 1 && i1 == l && l > 0 && editable.charAt(i1 - 1) == '0')
        {
            editable.replace(i1 - 1, l, String.valueOf('+'));
            adjustMetaAfterKeypress(editable);
            return true;
        }
        adjustMetaAfterKeypress(editable);
        return super.onKeyDown(view, editable, i, keyevent);
    }

    private static final String DATE_TIME_FORMAT_SYMBOLS = "GyYuUrQqMLlwWdDFgEecabBhHKkjJCmsSAzZOvVXx";
    private static final char SINGLE_QUOTE = 39;
}
