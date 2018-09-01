// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.graphics.Paint;
import android.icu.lang.UCharacter;
import android.text.*;
import android.text.style.ReplacementSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package android.text.method:
//            MetaKeyKeyListener, KeyListener, WordIterator

public abstract class BaseKeyListener extends MetaKeyKeyListener
    implements KeyListener
{

    private static int[] _2D_getandroid_2D_text_2D_method_2D_TextKeyListener$CapitalizeSwitchesValues()
    {
        if(_2D_android_2D_text_2D_method_2D_TextKeyListener$CapitalizeSwitchesValues != null)
            return _2D_android_2D_text_2D_method_2D_TextKeyListener$CapitalizeSwitchesValues;
        int ai[] = new int[TextKeyListener.Capitalize.values().length];
        try
        {
            ai[TextKeyListener.Capitalize.CHARACTERS.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[TextKeyListener.Capitalize.NONE.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[TextKeyListener.Capitalize.SENTENCES.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[TextKeyListener.Capitalize.WORDS.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_text_2D_method_2D_TextKeyListener$CapitalizeSwitchesValues = ai;
        return ai;
    }

    public BaseKeyListener()
    {
    }

    private static int adjustReplacementSpan(CharSequence charsequence, int i, boolean flag)
    {
        if(!(charsequence instanceof Spanned))
            return i;
        ReplacementSpan areplacementspan[] = (ReplacementSpan[])((Spanned)charsequence).getSpans(i, i, android/text/style/ReplacementSpan);
        int j = 0;
        int k = i;
        while(j < areplacementspan.length) 
        {
            int l = ((Spanned)charsequence).getSpanStart(areplacementspan[j]);
            int i1 = ((Spanned)charsequence).getSpanEnd(areplacementspan[j]);
            i = k;
            if(l < k)
            {
                i = k;
                if(i1 > k)
                    if(flag)
                        i = l;
                    else
                        i = i1;
            }
            j++;
            k = i;
        }
        return k;
    }

    private boolean backspaceOrForwardDelete(View view, Editable editable, int i, KeyEvent keyevent, boolean flag)
    {
        int j;
        if(!KeyEvent.metaStateHasNoModifiers(keyevent.getMetaState() & 0xffff8f0c))
            return false;
        if(deleteSelection(view, editable))
            return true;
        boolean flag1;
        if((keyevent.getMetaState() & 0x1000) != 0)
            i = 1;
        else
            i = 0;
        if(getMetaState(editable, 1, keyevent) == 1)
            j = 1;
        else
            j = 0;
        if(getMetaState(editable, 2, keyevent) == 1)
            flag1 = true;
        else
            flag1 = false;
        if(i != 0)
            if(flag1 || j)
                return false;
            else
                return deleteUntilWordBoundary(view, editable, flag);
        if(flag1 && deleteLine(view, editable))
            return true;
        j = Selection.getSelectionEnd(editable);
        if(!flag)
            break MISSING_BLOCK_LABEL_232;
        if(!(view instanceof TextView)) goto _L2; else goto _L1
_L1:
        view = ((TextView)view).getPaint();
_L3:
        i = getOffsetForForwardDeleteKey(editable, j, view);
_L4:
        if(j != i)
        {
            editable.delete(Math.min(j, i), Math.max(j, i));
            return true;
        } else
        {
            return false;
        }
_L2:
        keyevent = ((KeyEvent) (mLock));
        keyevent;
        JVM INSTR monitorenter ;
        if(sCachedPaint == null)
        {
            view = JVM INSTR new #144 <Class Paint>;
            view.Paint();
            sCachedPaint = view;
        }
        view = sCachedPaint;
        keyevent;
        JVM INSTR monitorexit ;
          goto _L3
        view;
        throw view;
        i = getOffsetForBackspaceKey(editable, j);
          goto _L4
    }

    private boolean deleteLine(View view, Editable editable)
    {
        if(view instanceof TextView)
        {
            view = ((TextView)view).getLayout();
            if(view != null)
            {
                int i = view.getLineForOffset(Selection.getSelectionStart(editable));
                int j = view.getLineStart(i);
                i = view.getLineEnd(i);
                if(i != j)
                {
                    editable.delete(j, i);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean deleteSelection(View view, Editable editable)
    {
        int i = Selection.getSelectionStart(editable);
        int j = Selection.getSelectionEnd(editable);
        int k = j;
        int l = i;
        if(j < i)
        {
            k = i;
            l = j;
        }
        if(l != k)
        {
            editable.delete(l, k);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean deleteUntilWordBoundary(View view, Editable editable, boolean flag)
    {
        int i;
        i = Selection.getSelectionStart(editable);
        if(i != Selection.getSelectionEnd(editable))
            return false;
        while(!flag && i == 0 || flag && i == editable.length()) 
            return false;
        WordIterator worditerator = null;
        if(view instanceof TextView)
            worditerator = ((TextView)view).getWordIterator();
        view = worditerator;
        if(worditerator == null)
            view = new WordIterator();
        if(!flag) goto _L2; else goto _L1
_L1:
        int j1;
        int j = i;
        view.setCharSequence(editable, i, editable.length());
        int l = view.following(i);
        i = j;
        j1 = l;
        if(l == -1)
        {
            j1 = editable.length();
            i = j;
        }
_L4:
        editable.delete(i, j1);
        return true;
_L2:
        int k = i;
        view.setCharSequence(editable, 0, i);
        int i1 = view.preceding(i);
        i = i1;
        j1 = k;
        if(i1 == -1)
        {
            i = 0;
            j1 = k;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static int getOffsetForBackspaceKey(CharSequence charsequence, int i)
    {
        int j;
        int k;
        int l;
        int i1;
        if(i <= 1)
            return 0;
        j = 0;
        k = 0;
        l = 0;
        i1 = i;
_L16:
        int j1;
        j1 = Character.codePointBefore(charsequence, i1);
        i1 -= Character.charCount(j1);
        l;
        JVM INSTR tableswitch 0 12: default 104
    //                   0 137
    //                   1 278
    //                   2 360
    //                   3 410
    //                   4 439
    //                   5 489
    //                   6 518
    //                   7 580
    //                   8 608
    //                   9 682
    //                   10 300
    //                   11 330
    //                   12 722;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_722;
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("state ").append(l).append(" is unknown").toString());
_L2:
        int k1;
        k1 = Character.charCount(j1);
        if(j1 == 10)
            j1 = 1;
        else
        if(isVariationSelector(j1))
            j1 = 6;
        else
        if(Emoji.isRegionalIndicatorSymbol(j1))
            j1 = 10;
        else
        if(Emoji.isEmojiModifier(j1))
            j1 = 4;
        else
        if(j1 == Emoji.COMBINING_ENCLOSING_KEYCAP)
            j1 = 2;
        else
        if(Emoji.isEmoji(j1))
            j1 = 7;
        else
        if(j1 == Emoji.CANCEL_TAG)
            j1 = 12;
        else
            j1 = 13;
_L17:
        if(i1 <= 0)
            break; /* Loop/switch isn't completed */
        j = k1;
        l = j1;
        if(j1 != 13) goto _L16; else goto _L15
_L15:
        return adjustReplacementSpan(charsequence, i - k1, true);
_L3:
        k1 = j;
        if(j1 == 13)
            k1 = j + 1;
        j1 = 13;
          goto _L17
_L12:
        if(Emoji.isRegionalIndicatorSymbol(j1))
        {
            k1 = j + 2;
            j1 = 11;
        } else
        {
            j1 = 13;
            k1 = j;
        }
          goto _L17
_L13:
        if(Emoji.isRegionalIndicatorSymbol(j1))
        {
            k1 = j - 2;
            j1 = 10;
        } else
        {
            j1 = 13;
            k1 = j;
        }
          goto _L17
_L4:
        if(isVariationSelector(j1))
        {
            k = Character.charCount(j1);
            j1 = 3;
            k1 = j;
        } else
        {
            k1 = j;
            if(Emoji.isKeycapBase(j1))
                k1 = j + Character.charCount(j1);
            j1 = 13;
        }
          goto _L17
_L5:
        k1 = j;
        if(Emoji.isKeycapBase(j1))
            k1 = j + (Character.charCount(j1) + k);
        j1 = 13;
          goto _L17
_L6:
        if(isVariationSelector(j1))
        {
            k = Character.charCount(j1);
            j1 = 5;
            k1 = j;
        } else
        {
            k1 = j;
            if(Emoji.isEmojiModifierBase(j1))
                k1 = j + Character.charCount(j1);
            j1 = 13;
        }
          goto _L17
_L7:
        k1 = j;
        if(Emoji.isEmojiModifierBase(j1))
            k1 = j + (Character.charCount(j1) + k);
        j1 = 13;
          goto _L17
_L8:
        if(Emoji.isEmoji(j1))
        {
            k1 = j + Character.charCount(j1);
            j1 = 7;
        } else
        {
            k1 = j;
            if(!isVariationSelector(j1))
            {
                k1 = j;
                if(UCharacter.getCombiningClass(j1) == 0)
                    k1 = j + Character.charCount(j1);
            }
            j1 = 13;
        }
          goto _L17
_L9:
        if(j1 == Emoji.ZERO_WIDTH_JOINER)
        {
            j1 = 8;
            k1 = j;
        } else
        {
            j1 = 13;
            k1 = j;
        }
          goto _L17
_L10:
        if(Emoji.isEmoji(j1))
        {
            k1 = j + (Character.charCount(j1) + 1);
            if(Emoji.isEmojiModifier(j1))
                j1 = 4;
            else
                j1 = 7;
        } else
        if(isVariationSelector(j1))
        {
            k = Character.charCount(j1);
            j1 = 9;
            k1 = j;
        } else
        {
            j1 = 13;
            k1 = j;
        }
          goto _L17
_L11:
        if(Emoji.isEmoji(j1))
        {
            k1 = j + (k + 1 + Character.charCount(j1));
            k = 0;
            j1 = 7;
        } else
        {
            j1 = 13;
            k1 = j;
        }
          goto _L17
        if(Emoji.isTagSpecChar(j1))
        {
            k1 = j + 2;
            j1 = l;
        } else
        if(Emoji.isEmoji(j1))
        {
            k1 = j + Character.charCount(j1);
            j1 = 13;
        } else
        {
            k1 = 2;
            j1 = 13;
        }
          goto _L17
    }

    private static int getOffsetForForwardDeleteKey(CharSequence charsequence, int i, Paint paint)
    {
        int j = charsequence.length();
        if(i >= j - 1)
            return j;
        else
            return adjustReplacementSpan(charsequence, paint.getTextRunCursor(charsequence, i, j, 0, i, 0), false);
    }

    private static boolean isVariationSelector(int i)
    {
        return UCharacter.hasBinaryProperty(i, 36);
    }

    static int makeTextContentType(TextKeyListener.Capitalize capitalize, boolean flag)
    {
        int i = 1;
        _2D_getandroid_2D_text_2D_method_2D_TextKeyListener$CapitalizeSwitchesValues()[capitalize.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 50
    //                   2 64
    //                   3 57;
           goto _L1 _L2 _L3 _L4
_L1:
        int j = i;
        if(flag)
            j = i | 0x8000;
        return j;
_L2:
        i = 4097;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 8193;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 16385;
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean backspace(View view, Editable editable, int i, KeyEvent keyevent)
    {
        return backspaceOrForwardDelete(view, editable, i, keyevent, false);
    }

    public boolean forwardDelete(View view, Editable editable, int i, KeyEvent keyevent)
    {
        return backspaceOrForwardDelete(view, editable, i, keyevent, true);
    }

    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR lookupswitch 2: default 28
    //                   67: 42
    //                   112: 56;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_56;
_L1:
        boolean flag = false;
_L4:
        if(flag)
        {
            adjustMetaAfterKeypress(editable);
            return true;
        } else
        {
            return super.onKeyDown(view, editable, i, keyevent);
        }
_L2:
        flag = backspace(view, editable, i, keyevent);
          goto _L4
        flag = forwardDelete(view, editable, i, keyevent);
          goto _L4
    }

    public boolean onKeyOther(View view, Editable editable, KeyEvent keyevent)
    {
        if(keyevent.getAction() != 2 || keyevent.getKeyCode() != 0)
            return false;
        int i = Selection.getSelectionStart(editable);
        int j = Selection.getSelectionEnd(editable);
        int k = j;
        int l = i;
        if(j < i)
        {
            l = j;
            k = i;
        }
        view = keyevent.getCharacters();
        if(view == null)
        {
            return false;
        } else
        {
            editable.replace(l, k, view);
            return true;
        }
    }

    private static final int _2D_android_2D_text_2D_method_2D_TextKeyListener$CapitalizeSwitchesValues[];
    private static final int CARRIAGE_RETURN = 13;
    private static final int LINE_FEED = 10;
    static final Object OLD_SEL_START = new android.text.NoCopySpan.Concrete();
    static Paint sCachedPaint = null;
    private final Object mLock = new Object();

}
