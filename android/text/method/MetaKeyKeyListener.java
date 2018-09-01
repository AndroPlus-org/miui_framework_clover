// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.text.*;
import android.view.*;

public abstract class MetaKeyKeyListener
{

    public MetaKeyKeyListener()
    {
    }

    private static void adjust(Spannable spannable, Object obj)
    {
        int i = spannable.getSpanFlags(obj);
        if(i != 0x1000011) goto _L2; else goto _L1
_L1:
        spannable.setSpan(obj, 0, 0, 0x3000011);
_L4:
        return;
_L2:
        if(i == 0x2000011)
            spannable.removeSpan(obj);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static long adjustMetaAfterKeypress(long l)
    {
        long l1;
        if((0x10000000000L & l) != 0L)
        {
            l1 = l & 0xfffefefefffffefeL | 1L | 0x100000000L;
        } else
        {
            l1 = l;
            if((0x1000000000000L & l) != 0L)
                l1 = l & 0xfffefefefffffefeL;
        }
        if((0x20000000000L & l1) != 0L)
        {
            l = l1 & 0xfffdfdfdfffffdfdL | 2L | 0x200000000L;
        } else
        {
            l = l1;
            if((0x2000000000000L & l1) != 0L)
                l = l1 & 0xfffdfdfdfffffdfdL;
        }
        if((0x40000000000L & l) != 0L)
        {
            l1 = l & 0xfffbfbfbfffffbfbL | 4L | 0x400000000L;
        } else
        {
            l1 = l;
            if((0x4000000000000L & l) != 0L)
                l1 = l & 0xfffbfbfbfffffbfbL;
        }
        return l1;
    }

    public static void adjustMetaAfterKeypress(Spannable spannable)
    {
        adjust(spannable, CAP);
        adjust(spannable, ALT);
        adjust(spannable, SYM);
    }

    public static void clearMetaKeyState(Editable editable, int i)
    {
        if((i & 1) != 0)
            editable.removeSpan(CAP);
        if((i & 2) != 0)
            editable.removeSpan(ALT);
        if((i & 4) != 0)
            editable.removeSpan(SYM);
        if((i & 0x800) != 0)
            editable.removeSpan(SELECTING);
    }

    private static int getActive(CharSequence charsequence, Object obj, int i, int j)
    {
        if(!(charsequence instanceof Spanned))
            return 0;
        int k = ((Spanned)charsequence).getSpanFlags(obj);
        if(k == 0x4000011)
            return j;
        if(k != 0)
            return i;
        else
            return 0;
    }

    public static final int getMetaState(long l)
    {
        int i = 0;
        int j;
        if((256L & l) != 0L)
            i = 256;
        else
        if((1L & l) != 0L)
            i = 1;
        if((512L & l) != 0L)
        {
            j = i | 0x200;
        } else
        {
            j = i;
            if((2L & l) != 0L)
                j = i | 2;
        }
        if((1024L & l) != 0L)
        {
            i = j | 0x400;
        } else
        {
            i = j;
            if((4L & l) != 0L)
                i = j | 4;
        }
        return i;
    }

    public static final int getMetaState(long l, int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            return 0;

        case 1: // '\001'
            if((256L & l) != 0L)
                return 2;
            return (1L & l) == 0L ? 0 : 1;

        case 2: // '\002'
            if((512L & l) != 0L)
                return 2;
            return (2L & l) == 0L ? 0 : 1;

        case 4: // '\004'
            break;
        }
        if((1024L & l) != 0L)
            return 2;
        return (4L & l) == 0L ? 0 : 1;
    }

    public static final int getMetaState(CharSequence charsequence)
    {
        return getActive(charsequence, CAP, 1, 256) | getActive(charsequence, ALT, 2, 512) | getActive(charsequence, SYM, 4, 1024) | getActive(charsequence, SELECTING, 2048, 2048);
    }

    public static final int getMetaState(CharSequence charsequence, int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 1: // '\001'
            return getActive(charsequence, CAP, 1, 2);

        case 2: // '\002'
            return getActive(charsequence, ALT, 1, 2);

        case 4: // '\004'
            return getActive(charsequence, SYM, 1, 2);

        case 2048: 
            return getActive(charsequence, SELECTING, 1, 2);
        }
    }

    public static final int getMetaState(CharSequence charsequence, int i, KeyEvent keyevent)
    {
        int j = keyevent.getMetaState();
        int k = j;
        if(keyevent.getKeyCharacterMap().getModifierBehavior() == 1)
            k = j | getMetaState(charsequence);
        if(2048 == i)
            return (k & 0x800) == 0 ? 0 : 1;
        else
            return getMetaState(k, i);
    }

    public static final int getMetaState(CharSequence charsequence, KeyEvent keyevent)
    {
        int i = keyevent.getMetaState();
        int j = i;
        if(keyevent.getKeyCharacterMap().getModifierBehavior() == 1)
            j = i | getMetaState(charsequence);
        return j;
    }

    public static long handleKeyDown(long l, int i, KeyEvent keyevent)
    {
        if(i == 59 || i == 60)
            return press(l, 1, 0x1010100000101L, 256L, 0x10000000000L, 0x1000000000000L, 0x100000000L);
        while(i == 57 || i == 58 || i == 78) 
            return press(l, 2, 0x2020200000202L, 512L, 0x20000000000L, 0x2000000000000L, 0x200000000L);
        if(i == 63)
            return press(l, 4, 0x4040400000404L, 1024L, 0x40000000000L, 0x4000000000000L, 0x400000000L);
        else
            return l;
    }

    public static long handleKeyUp(long l, int i, KeyEvent keyevent)
    {
        if(i == 59 || i == 60)
            return release(l, 1, 0x1010100000101L, 0x10000000000L, 0x1000000000000L, 0x100000000L, keyevent);
        while(i == 57 || i == 58 || i == 78) 
            return release(l, 2, 0x2020200000202L, 0x20000000000L, 0x2000000000000L, 0x200000000L, keyevent);
        if(i == 63)
            return release(l, 4, 0x4040400000404L, 0x40000000000L, 0x4000000000000L, 0x400000000L, keyevent);
        else
            return l;
    }

    public static boolean isMetaTracker(CharSequence charsequence, Object obj)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(obj == CAP) goto _L2; else goto _L1
_L1:
        if(obj != ALT) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(obj != SYM)
        {
            flag1 = flag;
            if(obj != SELECTING)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isSelectingMetaTracker(CharSequence charsequence, Object obj)
    {
        boolean flag;
        if(obj == SELECTING)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static long press(long l, int i, long l1, long l2, long l3, long l4, long l5)
    {
        if((l & l3) == 0L) goto _L2; else goto _L1
_L1:
        l4 = l;
_L4:
        return l4;
_L2:
        if((l & l4) != 0L)
        {
            l4 = l1 & l | (long)i | l2;
        } else
        {
            l4 = l;
            if((l & l5) == 0L)
                if((l & l2) != 0L)
                    l4 = l & l1;
                else
                    l4 = l | ((long)i | l3);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void press(Editable editable, Object obj)
    {
        int i = editable.getSpanFlags(obj);
        if(i == 0x1000011) goto _L2; else goto _L1
_L1:
        if(i != 0x2000011) goto _L4; else goto _L3
_L3:
        editable.setSpan(obj, 0, 0, 0x4000011);
_L2:
        return;
_L4:
        if(i != 0x3000011)
            if(i == 0x4000011)
                editable.removeSpan(obj);
            else
                editable.setSpan(obj, 0, 0, 0x1000011);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static long release(long l, int i, long l1, long l2, long l3, long l4, KeyEvent keyevent)
    {
        keyevent.getKeyCharacterMap().getModifierBehavior();
        JVM INSTR tableswitch 1 1: default 28
    //                   1 34;
           goto _L1 _L2
_L1:
        l1 = l & l1;
_L4:
        return l1;
_L2:
        if((l & l4) != 0L)
        {
            l1 = l & l1;
        } else
        {
            l1 = l;
            if((l & l2) != 0L)
                l1 = l | ((long)i | l3);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void release(Editable editable, Object obj, KeyEvent keyevent)
    {
        int i = editable.getSpanFlags(obj);
        keyevent.getKeyCharacterMap().getModifierBehavior();
        JVM INSTR tableswitch 1 1: default 36
    //                   1 44;
           goto _L1 _L2
_L1:
        editable.removeSpan(obj);
_L4:
        return;
_L2:
        if(i == 0x3000011)
            editable.removeSpan(obj);
        else
        if(i == 0x1000011)
            editable.setSpan(obj, 0, 0, 0x2000011);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void resetLock(Spannable spannable, Object obj)
    {
        if(spannable.getSpanFlags(obj) == 0x4000011)
            spannable.removeSpan(obj);
    }

    public static long resetLockedMeta(long l)
    {
        long l1 = l;
        if((256L & l) != 0L)
            l1 = l & 0xfffefefefffffefeL;
        l = l1;
        if((512L & l1) != 0L)
            l = l1 & 0xfffdfdfdfffffdfdL;
        l1 = l;
        if((1024L & l) != 0L)
            l1 = l & 0xfffbfbfbfffffbfbL;
        return l1;
    }

    protected static void resetLockedMeta(Spannable spannable)
    {
        resetLock(spannable, CAP);
        resetLock(spannable, ALT);
        resetLock(spannable, SYM);
        resetLock(spannable, SELECTING);
    }

    public static void resetMetaState(Spannable spannable)
    {
        spannable.removeSpan(CAP);
        spannable.removeSpan(ALT);
        spannable.removeSpan(SYM);
        spannable.removeSpan(SELECTING);
    }

    public static void startSelecting(View view, Spannable spannable)
    {
        spannable.setSpan(SELECTING, 0, 0, 0x1000011);
    }

    public static void stopSelecting(View view, Spannable spannable)
    {
        spannable.removeSpan(SELECTING);
    }

    public long clearMetaKeyState(long l, int i)
    {
        long l1 = l;
        if((i & 1) != 0)
        {
            l1 = l;
            if((256L & l) != 0L)
                l1 = l & 0xfffefefefffffefeL;
        }
        l = l1;
        if((i & 2) != 0)
        {
            l = l1;
            if((512L & l1) != 0L)
                l = l1 & 0xfffdfdfdfffffdfdL;
        }
        l1 = l;
        if((i & 4) != 0)
        {
            l1 = l;
            if((1024L & l) != 0L)
                l1 = l & 0xfffbfbfbfffffbfbL;
        }
        return l1;
    }

    public void clearMetaKeyState(View view, Editable editable, int i)
    {
        clearMetaKeyState(editable, i);
    }

    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyevent)
    {
        if(i == 59 || i == 60)
        {
            press(editable, CAP);
            return true;
        }
        while(i == 57 || i == 58 || i == 78) 
        {
            press(editable, ALT);
            return true;
        }
        if(i == 63)
        {
            press(editable, SYM);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyevent)
    {
        if(i == 59 || i == 60)
        {
            release(editable, CAP, keyevent);
            return true;
        }
        while(i == 57 || i == 58 || i == 78) 
        {
            release(editable, ALT, keyevent);
            return true;
        }
        if(i == 63)
        {
            release(editable, SYM, keyevent);
            return true;
        } else
        {
            return false;
        }
    }

    private static final Object ALT = new android.text.NoCopySpan.Concrete();
    private static final Object CAP = new android.text.NoCopySpan.Concrete();
    private static final int LOCKED = 0x4000011;
    private static final int LOCKED_RETURN_VALUE = 2;
    public static final int META_ALT_LOCKED = 512;
    private static final long META_ALT_MASK = 0x2020200000202L;
    public static final int META_ALT_ON = 2;
    private static final long META_ALT_PRESSED = 0x20000000000L;
    private static final long META_ALT_RELEASED = 0x2000000000000L;
    private static final long META_ALT_USED = 0x200000000L;
    public static final int META_CAP_LOCKED = 256;
    private static final long META_CAP_PRESSED = 0x10000000000L;
    private static final long META_CAP_RELEASED = 0x1000000000000L;
    private static final long META_CAP_USED = 0x100000000L;
    public static final int META_SELECTING = 2048;
    private static final long META_SHIFT_MASK = 0x1010100000101L;
    public static final int META_SHIFT_ON = 1;
    public static final int META_SYM_LOCKED = 1024;
    private static final long META_SYM_MASK = 0x4040400000404L;
    public static final int META_SYM_ON = 4;
    private static final long META_SYM_PRESSED = 0x40000000000L;
    private static final long META_SYM_RELEASED = 0x4000000000000L;
    private static final long META_SYM_USED = 0x400000000L;
    private static final int PRESSED = 0x1000011;
    private static final int PRESSED_RETURN_VALUE = 1;
    private static final int RELEASED = 0x2000011;
    private static final Object SELECTING = new android.text.NoCopySpan.Concrete();
    private static final Object SYM = new android.text.NoCopySpan.Concrete();
    private static final int USED = 0x3000011;

}
