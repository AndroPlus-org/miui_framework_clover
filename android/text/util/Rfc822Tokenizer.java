// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.util;

import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package android.text.util:
//            Rfc822Token

public class Rfc822Tokenizer
    implements android.widget.MultiAutoCompleteTextView.Tokenizer
{

    public Rfc822Tokenizer()
    {
    }

    private static void crunch(StringBuilder stringbuilder)
    {
        int i = 0;
        int k;
        for(k = stringbuilder.length(); i < k;)
            if(stringbuilder.charAt(i) == 0)
            {
                if(i == 0 || i == k - 1 || stringbuilder.charAt(i - 1) == ' ' || stringbuilder.charAt(i - 1) == 0 || stringbuilder.charAt(i + 1) == ' ' || stringbuilder.charAt(i + 1) == 0)
                {
                    stringbuilder.deleteCharAt(i);
                    k--;
                } else
                {
                    i++;
                }
            } else
            {
                i++;
            }

        for(int j = 0; j < k; j++)
            if(stringbuilder.charAt(j) == 0)
                stringbuilder.setCharAt(j, ' ');

    }

    public static void tokenize(CharSequence charsequence, Collection collection)
    {
        StringBuilder stringbuilder;
        StringBuilder stringbuilder1;
        StringBuilder stringbuilder2;
        int i;
        int j;
        stringbuilder = new StringBuilder();
        stringbuilder1 = new StringBuilder();
        stringbuilder2 = new StringBuilder();
        i = 0;
        j = charsequence.length();
_L7:
        char c;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        c = charsequence.charAt(i);
        if(c != ',' && c != ';') goto _L2; else goto _L1
_L1:
        for(i++; i < j && charsequence.charAt(i) == ' '; i++);
        crunch(stringbuilder);
        if(stringbuilder1.length() <= 0) goto _L4; else goto _L3
_L3:
        collection.add(new Rfc822Token(stringbuilder.toString(), stringbuilder1.toString(), stringbuilder2.toString()));
_L5:
        stringbuilder.setLength(0);
        stringbuilder1.setLength(0);
        stringbuilder2.setLength(0);
        continue; /* Loop/switch isn't completed */
_L4:
        if(stringbuilder.length() > 0)
            collection.add(new Rfc822Token(null, stringbuilder.toString(), stringbuilder2.toString()));
        if(true) goto _L5; else goto _L2
_L2:
        if(c == '"')
        {
            int k = i + 1;
            do
            {
                i = k;
                if(k >= j)
                    continue; /* Loop/switch isn't completed */
                c = charsequence.charAt(k);
                if(c == '"')
                {
                    i = k + 1;
                    continue; /* Loop/switch isn't completed */
                }
                if(c == '\\')
                {
                    if(k + 1 < j)
                        stringbuilder.append(charsequence.charAt(k + 1));
                    k += 2;
                } else
                {
                    stringbuilder.append(c);
                    k++;
                }
            } while(true);
        }
        if(c == '(')
        {
            int j1 = 1;
            int l = i + 1;
            do
            {
                i = l;
                if(l >= j)
                    continue; /* Loop/switch isn't completed */
                i = l;
                if(j1 <= 0)
                    continue; /* Loop/switch isn't completed */
                c = charsequence.charAt(l);
                if(c == ')')
                {
                    if(j1 > 1)
                        stringbuilder2.append(c);
                    j1--;
                    l++;
                } else
                if(c == '(')
                {
                    stringbuilder2.append(c);
                    j1++;
                    l++;
                } else
                if(c == '\\')
                {
                    if(l + 1 < j)
                        stringbuilder2.append(charsequence.charAt(l + 1));
                    l += 2;
                } else
                {
                    stringbuilder2.append(c);
                    l++;
                }
            } while(true);
        }
        if(c == '<')
        {
            int i1 = i + 1;
            do
            {
                i = i1;
                if(i1 >= j)
                    continue; /* Loop/switch isn't completed */
                c = charsequence.charAt(i1);
                if(c == '>')
                {
                    i = i1 + 1;
                    continue; /* Loop/switch isn't completed */
                }
                stringbuilder1.append(c);
                i1++;
            } while(true);
        }
        if(c == ' ')
        {
            stringbuilder.append('\0');
            i++;
        } else
        {
            stringbuilder.append(c);
            i++;
        }
        if(true) goto _L7; else goto _L6
_L6:
        crunch(stringbuilder);
        if(stringbuilder1.length() <= 0) goto _L9; else goto _L8
_L8:
        collection.add(new Rfc822Token(stringbuilder.toString(), stringbuilder1.toString(), stringbuilder2.toString()));
_L11:
        return;
_L9:
        if(stringbuilder.length() > 0)
            collection.add(new Rfc822Token(null, stringbuilder.toString(), stringbuilder2.toString()));
        if(true) goto _L11; else goto _L10
_L10:
    }

    public static Rfc822Token[] tokenize(CharSequence charsequence)
    {
        ArrayList arraylist = new ArrayList();
        tokenize(charsequence, ((Collection) (arraylist)));
        return (Rfc822Token[])arraylist.toArray(new Rfc822Token[arraylist.size()]);
    }

    public int findTokenEnd(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
label0:
        do
        {
            if(i >= j)
                break;
            int k = charsequence.charAt(i);
            if(k == 44 || k == 59)
                return i;
            if(k == 34)
            {
                k = i + 1;
                do
                {
                    i = k;
                    if(k >= j)
                        continue label0;
                    i = charsequence.charAt(k);
                    if(i == 34)
                    {
                        i = k + 1;
                        continue label0;
                    }
                    if(i == 92 && k + 1 < j)
                        k += 2;
                    else
                        k++;
                } while(true);
            }
            if(k == 40)
            {
                int i1 = 1;
                k = i + 1;
                do
                {
                    i = k;
                    if(k >= j)
                        continue label0;
                    i = k;
                    if(i1 <= 0)
                        continue label0;
                    i = charsequence.charAt(k);
                    if(i == 41)
                    {
                        i1--;
                        k++;
                    } else
                    if(i == 40)
                    {
                        i1++;
                        k++;
                    } else
                    if(i == 92 && k + 1 < j)
                        k += 2;
                    else
                        k++;
                } while(true);
            }
            if(k == 60)
            {
                int l = i + 1;
                do
                {
                    i = l;
                    if(l >= j)
                        continue label0;
                    if(charsequence.charAt(l) == '>')
                    {
                        i = l + 1;
                        continue label0;
                    }
                    l++;
                } while(true);
            }
            i++;
        } while(true);
        return i;
    }

    public int findTokenStart(CharSequence charsequence, int i)
    {
        int j = 0;
        int k = 0;
        do
        {
            if(k >= i)
                break;
            int l = findTokenEnd(charsequence, k);
            k = l;
            if(l < i)
            {
                for(l++; l < i && charsequence.charAt(l) == ' '; l++);
                k = l;
                if(l < i)
                {
                    j = l;
                    k = l;
                }
            }
        } while(true);
        return j;
    }

    public CharSequence terminateToken(CharSequence charsequence)
    {
        return (new StringBuilder()).append(charsequence).append(", ").toString();
    }
}
