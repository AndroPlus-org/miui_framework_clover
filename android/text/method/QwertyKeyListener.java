// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.text.*;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;

// Referenced classes of package android.text.method:
//            BaseKeyListener, CharacterPickerDialog, TextKeyListener

public class QwertyKeyListener extends BaseKeyListener
{
    static class Replaced
        implements NoCopySpan
    {

        static char[] _2D_get0(Replaced replaced)
        {
            return replaced.mText;
        }

        private char mText[];

        public Replaced(char ac[])
        {
            mText = ac;
        }
    }


    public QwertyKeyListener(TextKeyListener.Capitalize capitalize, boolean flag)
    {
        this(capitalize, flag, false);
    }

    private QwertyKeyListener(TextKeyListener.Capitalize capitalize, boolean flag, boolean flag1)
    {
        mAutoCap = capitalize;
        mAutoText = flag;
        mFullKeyboard = flag1;
    }

    public static QwertyKeyListener getInstance(boolean flag, TextKeyListener.Capitalize capitalize)
    {
        int i = capitalize.ordinal();
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        j = i * 2 + j;
        if(sInstance[j] == null)
            sInstance[j] = new QwertyKeyListener(capitalize, flag);
        return sInstance[j];
    }

    public static QwertyKeyListener getInstanceForFullKeyboard()
    {
        if(sFullKeyboardInstance == null)
            sFullKeyboardInstance = new QwertyKeyListener(TextKeyListener.Capitalize.NONE, false, true);
        return sFullKeyboardInstance;
    }

    private String getReplacement(CharSequence charsequence, int i, int j, View view)
    {
        int k = j - i;
        boolean flag = false;
        String s = AutoText.get(charsequence, i, j, view);
        Object obj = s;
        if(s == null)
        {
            view = AutoText.get(TextUtils.substring(charsequence, i, j).toLowerCase(), 0, j - i, view);
            flag = true;
            obj = view;
            if(view == null)
                return null;
        }
        int i1 = 0;
        boolean flag1 = false;
        if(flag)
        {
            int j1 = i;
            int l = ((flag1) ? 1 : 0);
            do
            {
                i1 = l;
                if(j1 >= j)
                    break;
                i1 = l;
                if(Character.isUpperCase(charsequence.charAt(j1)))
                    i1 = l + 1;
                j1++;
                l = i1;
            } while(true);
        }
        if(i1 == 0)
            view = ((View) (obj));
        else
        if(i1 == 1)
            view = toTitleCase(((String) (obj)));
        else
        if(i1 == k)
            view = ((String) (obj)).toUpperCase();
        else
            view = toTitleCase(((String) (obj)));
        if(view.length() == k && TextUtils.regionMatches(charsequence, i, view, 0, k))
            return null;
        else
            return view;
    }

    public static void markAsReplaced(Spannable spannable, int i, int j, String s)
    {
        Object aobj[] = (Replaced[])spannable.getSpans(0, spannable.length(), android/text/method/QwertyKeyListener$Replaced);
        for(int k = 0; k < aobj.length; k++)
            spannable.removeSpan(aobj[k]);

        int l = s.length();
        aobj = new char[l];
        s.getChars(0, l, ((char []) (aobj)), 0);
        spannable.setSpan(new Replaced(((char []) (aobj))), i, j, 33);
    }

    private boolean showCharacterPicker(View view, Editable editable, char c, boolean flag, int i)
    {
        String s = (String)PICKER_SETS.get(c);
        if(s == null)
            return false;
        if(i == 1)
            (new CharacterPickerDialog(view.getContext(), view, editable, s, flag)).show();
        return true;
    }

    private static String toTitleCase(String s)
    {
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public int getInputType()
    {
        return makeTextContentType(mAutoCap, mAutoText);
    }

    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyevent)
    {
        int j;
        int j1;
        int k1;
label0:
        {
            j = 0;
            if(view != null)
                j = TextKeyListener.getInstance().getPrefs(view.getContext());
            int k = Selection.getSelectionStart(editable);
            j1 = Selection.getSelectionEnd(editable);
            k1 = Math.min(k, j1);
            k = Math.max(k, j1);
            if(k1 >= 0)
            {
                j1 = k;
                if(k >= 0)
                    break label0;
            }
            j1 = 0;
            k1 = 0;
            Selection.setSelection(editable, 0, 0);
        }
        int l1 = editable.getSpanStart(TextKeyListener.ACTIVE);
        int i2 = editable.getSpanEnd(TextKeyListener.ACTIVE);
        int j2 = keyevent.getUnicodeChar(getMetaState(editable, keyevent));
        if(!mFullKeyboard)
        {
            int l = keyevent.getRepeatCount();
            if(l > 0 && k1 == j1 && k1 > 0)
            {
                char c = editable.charAt(k1 - 1);
                if((c == j2 || c == Character.toUpperCase(j2)) && view != null && showCharacterPicker(view, editable, c, false, l))
                {
                    resetMetaState(editable);
                    return true;
                }
            }
        }
        if(j2 == 61185)
        {
            if(view != null)
                showCharacterPicker(view, editable, '\uEF01', true, 1);
            resetMetaState(editable);
            return true;
        }
        int i1 = j2;
        int i3 = k1;
        if(j2 == 61184)
        {
            if(k1 == j1)
            {
                i3 = j1;
                do
                {
                    i1 = i3;
                    if(i3 <= 0)
                        break;
                    i1 = i3;
                    if(j1 - i3 >= 4)
                        break;
                    i1 = i3;
                    if(Character.digit(editable.charAt(i3 - 1), 16) < 0)
                        break;
                    i3--;
                } while(true);
            } else
            {
                i1 = k1;
            }
            int k2 = -1;
            int l2;
            int j3;
            int k3;
            Replaced areplaced[];
            String s;
            try
            {
                i3 = Integer.parseInt(TextUtils.substring(editable, i1, j1), 16);
            }
            catch(NumberFormatException numberformatexception)
            {
                i3 = k2;
            }
            if(i3 >= 0)
            {
                k1 = i1;
                Selection.setSelection(editable, k1, j1);
                i1 = i3;
                i3 = k1;
            } else
            {
                i1 = 0;
                i3 = k1;
            }
        }
        if(i1 == 0) goto _L2; else goto _L1
_L1:
        k1 = 0;
        i = i1;
        if((0x80000000 & i1) != 0)
        {
            k1 = 1;
            i = i1 & 0x7fffffff;
        }
        j3 = k1;
        i1 = i;
        k3 = i3;
        if(l1 == i3)
        {
            j3 = k1;
            i1 = i;
            k3 = i3;
            if(i2 == j1)
            {
                i1 = 0;
                i2 = k1;
                k2 = i;
                l1 = i1;
                if(j1 - i3 - 1 == 0)
                {
                    j3 = KeyEvent.getDeadChar(editable.charAt(i3), i);
                    i2 = k1;
                    k2 = i;
                    l1 = i1;
                    if(j3 != 0)
                    {
                        k2 = j3;
                        l1 = 1;
                        i2 = 0;
                    }
                }
                j3 = i2;
                i1 = k2;
                k3 = i3;
                if(l1 == 0)
                {
                    Selection.setSelection(editable, j1);
                    editable.removeSpan(TextKeyListener.ACTIVE);
                    k3 = j1;
                    i1 = k2;
                    j3 = i2;
                }
            }
        }
        i = i1;
        if((j & 1) != 0)
        {
            i = i1;
            if(Character.isLowerCase(i1))
            {
                i = i1;
                if(TextKeyListener.shouldCap(mAutoCap, editable, k3))
                {
                    k1 = editable.getSpanEnd(TextKeyListener.CAPPED);
                    i = editable.getSpanFlags(TextKeyListener.CAPPED);
                    if(k1 == k3 && (i >> 16 & 0xffff) == i1)
                    {
                        editable.removeSpan(TextKeyListener.CAPPED);
                        i = i1;
                    } else
                    {
                        k1 = i1 << 16;
                        i = Character.toUpperCase(i1);
                        if(k3 == 0)
                            editable.setSpan(TextKeyListener.CAPPED, 0, 0, k1 | 0x11);
                        else
                            editable.setSpan(TextKeyListener.CAPPED, k3 - 1, k3, k1 | 0x21);
                    }
                }
            }
        }
        if(k3 != j1)
            Selection.setSelection(editable, j1);
        editable.setSpan(OLD_SEL_START, k3, k3, 17);
        editable.replace(k3, j1, String.valueOf((char)i));
        k1 = editable.getSpanStart(OLD_SEL_START);
        j1 = Selection.getSelectionEnd(editable);
        if(k1 < j1)
        {
            editable.setSpan(TextKeyListener.LAST_TYPED, k1, j1, 33);
            if(j3 != 0)
            {
                Selection.setSelection(editable, k1, j1);
                editable.setSpan(TextKeyListener.ACTIVE, k1, j1, 33);
            }
        }
        adjustMetaAfterKeypress(editable);
        if((j & 2) == 0 || !mAutoText || i != 32 && i != 9 && i != 10 && i != 44 && i != 46 && i != 33 && i != 63 && i != 34 && Character.getType(i) != 22 || editable.getSpanEnd(TextKeyListener.INHIBIT_REPLACEMENT) == k1) goto _L4; else goto _L3
_L3:
        i = k1;
_L9:
        if(i <= 0) goto _L6; else goto _L5
_L5:
        l2 = editable.charAt(i - 1);
        if(l2 == '\'' || !(Character.isLetter(l2) ^ true)) goto _L7; else goto _L6
_L6:
        view = getReplacement(editable, i, k1, view);
        if(view != null)
        {
            keyevent = (Replaced[])editable.getSpans(0, editable.length(), android/text/method/QwertyKeyListener$Replaced);
            for(j1 = 0; j1 < keyevent.length; j1++)
                editable.removeSpan(keyevent[j1]);

            keyevent = new char[k1 - i];
            TextUtils.getChars(editable, i, k1, keyevent, 0);
            editable.setSpan(new Replaced(keyevent), i, k1, 33);
            editable.replace(i, k1, view);
        }
          goto _L4
_L7:
        i--;
        continue; /* Loop/switch isn't completed */
_L4:
        if((j & 4) != 0 && mAutoText)
        {
            j1 = Selection.getSelectionEnd(editable);
            if(j1 - 3 >= 0 && editable.charAt(j1 - 1) == ' ' && editable.charAt(j1 - 2) == ' ')
            {
                k1 = editable.charAt(j1 - 3);
                i = j1 - 3;
                for(l2 = k1; i > 0 && (l2 == 34 || Character.getType(l2) == 22); l2 = k1)
                {
                    k1 = editable.charAt(i - 1);
                    i--;
                }

                if(Character.isLetter(l2) || Character.isDigit(l2))
                    editable.replace(j1 - 2, j1 - 1, ".");
            }
        }
        return true;
_L2:
        if(i == 67 && (keyevent.hasNoModifiers() || keyevent.hasModifiers(2)) && i3 == j1)
        {
            k1 = 1;
            j1 = k1;
            if(editable.getSpanEnd(TextKeyListener.LAST_TYPED) == i3)
            {
                j1 = k1;
                if(editable.charAt(i3 - 1) != '\n')
                    j1 = 2;
            }
            areplaced = (Replaced[])editable.getSpans(i3 - j1, i3, android/text/method/QwertyKeyListener$Replaced);
            if(areplaced.length > 0)
            {
                j1 = editable.getSpanStart(areplaced[0]);
                k1 = editable.getSpanEnd(areplaced[0]);
                s = new String(Replaced._2D_get0(areplaced[0]));
                editable.removeSpan(areplaced[0]);
                if(i3 >= k1)
                {
                    editable.setSpan(TextKeyListener.INHIBIT_REPLACEMENT, k1, k1, 34);
                    editable.replace(j1, k1, s);
                    i = editable.getSpanStart(TextKeyListener.INHIBIT_REPLACEMENT);
                    if(i - 1 >= 0)
                        editable.setSpan(TextKeyListener.INHIBIT_REPLACEMENT, i - 1, i, 33);
                    else
                        editable.removeSpan(TextKeyListener.INHIBIT_REPLACEMENT);
                    adjustMetaAfterKeypress(editable);
                    return true;
                } else
                {
                    adjustMetaAfterKeypress(editable);
                    return super.onKeyDown(view, editable, i, keyevent);
                }
            }
        }
        return super.onKeyDown(view, editable, i, keyevent);
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static SparseArray PICKER_SETS;
    private static QwertyKeyListener sFullKeyboardInstance;
    private static QwertyKeyListener sInstance[] = new QwertyKeyListener[TextKeyListener.Capitalize.values().length * 2];
    private TextKeyListener.Capitalize mAutoCap;
    private boolean mAutoText;
    private boolean mFullKeyboard;

    static 
    {
        PICKER_SETS = new SparseArray();
        PICKER_SETS.put(65, "\300\301\302\304\306\303\305\u0104\u0100");
        PICKER_SETS.put(67, "\307\u0106\u010C");
        PICKER_SETS.put(68, "\u010E");
        PICKER_SETS.put(69, "\310\311\312\313\u0118\u011A\u0112");
        PICKER_SETS.put(71, "\u011E");
        PICKER_SETS.put(76, "\u0141");
        PICKER_SETS.put(73, "\314\315\316\317\u012A\u0130");
        PICKER_SETS.put(78, "\321\u0143\u0147");
        PICKER_SETS.put(79, "\330\u0152\325\322\323\324\326\u014C");
        PICKER_SETS.put(82, "\u0158");
        PICKER_SETS.put(83, "\u015A\u0160\u015E");
        PICKER_SETS.put(84, "\u0164");
        PICKER_SETS.put(85, "\331\332\333\334\u016E\u016A");
        PICKER_SETS.put(89, "\335\u0178");
        PICKER_SETS.put(90, "\u0179\u017B\u017D");
        PICKER_SETS.put(97, "\340\341\342\344\346\343\345\u0105\u0101");
        PICKER_SETS.put(99, "\347\u0107\u010D");
        PICKER_SETS.put(100, "\u010F");
        PICKER_SETS.put(101, "\350\351\352\353\u0119\u011B\u0113");
        PICKER_SETS.put(103, "\u011F");
        PICKER_SETS.put(105, "\354\355\356\357\u012B\u0131");
        PICKER_SETS.put(108, "\u0142");
        PICKER_SETS.put(110, "\361\u0144\u0148");
        PICKER_SETS.put(111, "\370\u0153\365\362\363\364\366\u014D");
        PICKER_SETS.put(114, "\u0159");
        PICKER_SETS.put(115, "\247\337\u015B\u0161\u015F");
        PICKER_SETS.put(116, "\u0165");
        PICKER_SETS.put(117, "\371\372\373\374\u016F\u016B");
        PICKER_SETS.put(121, "\375\377");
        PICKER_SETS.put(122, "\u017A\u017C\u017E");
        PICKER_SETS.put(61185, "\u2026\245\u2022\256\251\261[]{}\\|");
        PICKER_SETS.put(47, "\\");
        PICKER_SETS.put(49, "\271\275\u2153\274\u215B");
        PICKER_SETS.put(50, "\262\u2154");
        PICKER_SETS.put(51, "\263\276\u215C");
        PICKER_SETS.put(52, "\u2074");
        PICKER_SETS.put(53, "\u215D");
        PICKER_SETS.put(55, "\u215E");
        PICKER_SETS.put(48, "\u207F\u2205");
        PICKER_SETS.put(36, "\242\243\u20AC\245\u20A3\u20A4\u20B1");
        PICKER_SETS.put(37, "\u2030");
        PICKER_SETS.put(42, "\u2020\u2021");
        PICKER_SETS.put(45, "\u2013\u2014");
        PICKER_SETS.put(43, "\261");
        PICKER_SETS.put(40, "[{<");
        PICKER_SETS.put(41, "]}>");
        PICKER_SETS.put(33, "\241");
        PICKER_SETS.put(34, "\u201C\u201D\253\273\u02DD");
        PICKER_SETS.put(63, "\277");
        PICKER_SETS.put(44, "\u201A\u201E");
        PICKER_SETS.put(61, "\u2260\u2248\u221E");
        PICKER_SETS.put(60, "\u2264\253\u2039");
        PICKER_SETS.put(62, "\u2265\273\u203A");
    }
}
