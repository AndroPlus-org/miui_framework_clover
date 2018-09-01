// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.os.Handler;
import android.os.SystemClock;
import android.text.*;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;

// Referenced classes of package android.text.method:
//            BaseKeyListener, TextKeyListener, KeyListener

public class MultiTapKeyListener extends BaseKeyListener
    implements SpanWatcher
{
    private class Timeout extends Handler
        implements Runnable
    {

        static Editable _2D_set0(Timeout timeout, Editable editable)
        {
            timeout.mBuffer = editable;
            return editable;
        }

        public void run()
        {
            Editable editable = mBuffer;
            if(editable != null)
            {
                int i = Selection.getSelectionStart(editable);
                int j = Selection.getSelectionEnd(editable);
                int k = editable.getSpanStart(TextKeyListener.ACTIVE);
                int l = editable.getSpanEnd(TextKeyListener.ACTIVE);
                if(i == k && j == l)
                    Selection.setSelection(editable, Selection.getSelectionEnd(editable));
                editable.removeSpan(this);
            }
        }

        private Editable mBuffer;
        final MultiTapKeyListener this$0;

        public Timeout(Editable editable)
        {
            this$0 = MultiTapKeyListener.this;
            super();
            mBuffer = editable;
            mBuffer.setSpan(this, 0, mBuffer.length(), 18);
            postAtTime(this, SystemClock.uptimeMillis() + 2000L);
        }
    }


    public MultiTapKeyListener(TextKeyListener.Capitalize capitalize, boolean flag)
    {
        mCapitalize = capitalize;
        mAutoText = flag;
    }

    public static MultiTapKeyListener getInstance(boolean flag, TextKeyListener.Capitalize capitalize)
    {
        int i = capitalize.ordinal();
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        j = i * 2 + j;
        if(sInstance[j] == null)
            sInstance[j] = new MultiTapKeyListener(capitalize, flag);
        return sInstance[j];
    }

    private static void removeTimeouts(Spannable spannable)
    {
        Timeout atimeout[] = (Timeout[])spannable.getSpans(0, spannable.length(), android/text/method/MultiTapKeyListener$Timeout);
        for(int i = 0; i < atimeout.length; i++)
        {
            Timeout timeout = atimeout[i];
            timeout.removeCallbacks(timeout);
            Timeout._2D_set0(timeout, null);
            spannable.removeSpan(timeout);
        }

    }

    public int getInputType()
    {
        return makeTextContentType(mCapitalize, mAutoText);
    }

    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyevent)
    {
        int j = 0;
        if(view != null)
            j = TextKeyListener.getInstance().getPrefs(view.getContext());
        int k = Selection.getSelectionStart(editable);
        int l = Selection.getSelectionEnd(editable);
        int i1 = Math.min(k, l);
        int j1 = Math.max(k, l);
        k = editable.getSpanStart(TextKeyListener.ACTIVE);
        int k1 = editable.getSpanEnd(TextKeyListener.ACTIVE);
        l = (editable.getSpanFlags(TextKeyListener.ACTIVE) & 0xff000000) >>> 24;
        if(k == i1 && k1 == j1 && j1 - i1 == 1 && l >= 0 && l < sRecs.size())
        {
            if(i == 17)
            {
                char c = editable.charAt(i1);
                if(Character.isLowerCase(c))
                {
                    editable.replace(i1, j1, String.valueOf(c).toUpperCase());
                    removeTimeouts(editable);
                    new Timeout(editable);
                    return true;
                }
                if(Character.isUpperCase(c))
                {
                    editable.replace(i1, j1, String.valueOf(c).toLowerCase());
                    removeTimeouts(editable);
                    new Timeout(editable);
                    return true;
                }
            }
            if(sRecs.indexOfKey(i) == l)
            {
                String s = (String)sRecs.valueAt(l);
                k = s.indexOf(editable.charAt(i1));
                if(k >= 0)
                {
                    i = (k + 1) % s.length();
                    editable.replace(i1, j1, s, i, i + 1);
                    removeTimeouts(editable);
                    new Timeout(editable);
                    return true;
                }
            }
            l = sRecs.indexOfKey(i);
            k = l;
            if(l >= 0)
            {
                Selection.setSelection(editable, j1, j1);
                i1 = j1;
                k = l;
            }
        } else
        {
            k = sRecs.indexOfKey(i);
        }
        if(k < 0)
            break MISSING_BLOCK_LABEL_643;
        view = (String)sRecs.valueAt(k);
        k1 = 0;
        l = k1;
        if((j & 1) == 0) goto _L2; else goto _L1
_L1:
        l = k1;
        if(!TextKeyListener.shouldCap(mCapitalize, editable, i1)) goto _L2; else goto _L3
_L3:
        i = 0;
_L8:
        l = k1;
        if(i >= view.length()) goto _L2; else goto _L4
_L4:
        if(!Character.isUpperCase(view.charAt(i))) goto _L6; else goto _L5
_L5:
        l = i;
_L2:
        if(i1 != j1)
            Selection.setSelection(editable, j1);
        editable.setSpan(OLD_SEL_START, i1, i1, 17);
        editable.replace(i1, j1, view, l, l + 1);
        j = editable.getSpanStart(OLD_SEL_START);
        i = Selection.getSelectionEnd(editable);
        if(i != j)
        {
            Selection.setSelection(editable, j, i);
            editable.setSpan(TextKeyListener.LAST_TYPED, j, i, 33);
            editable.setSpan(TextKeyListener.ACTIVE, j, i, k << 24 | 0x21);
        }
        removeTimeouts(editable);
        new Timeout(editable);
        if(editable.getSpanStart(this) >= 0)
            break MISSING_BLOCK_LABEL_641;
        view = (KeyListener[])editable.getSpans(0, editable.length(), android/text/method/KeyListener);
        i = 0;
        for(j = view.length; i < j; i++)
            editable.removeSpan(view[i]);

        break; /* Loop/switch isn't completed */
_L6:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        editable.setSpan(this, 0, editable.length(), 18);
        return true;
        return super.onKeyDown(view, editable, i, keyevent);
    }

    public void onSpanAdded(Spannable spannable, Object obj, int i, int j)
    {
    }

    public void onSpanChanged(Spannable spannable, Object obj, int i, int j, int k, int l)
    {
        if(obj == Selection.SELECTION_END)
        {
            spannable.removeSpan(TextKeyListener.ACTIVE);
            removeTimeouts(spannable);
        }
    }

    public void onSpanRemoved(Spannable spannable, Object obj, int i, int j)
    {
    }

    private static MultiTapKeyListener sInstance[] = new MultiTapKeyListener[TextKeyListener.Capitalize.values().length * 2];
    private static final SparseArray sRecs;
    private boolean mAutoText;
    private TextKeyListener.Capitalize mCapitalize;

    static 
    {
        sRecs = new SparseArray();
        sRecs.put(8, ".,1!@#$%^&*:/?'=()");
        sRecs.put(9, "abc2ABC");
        sRecs.put(10, "def3DEF");
        sRecs.put(11, "ghi4GHI");
        sRecs.put(12, "jkl5JKL");
        sRecs.put(13, "mno6MNO");
        sRecs.put(14, "pqrs7PQRS");
        sRecs.put(15, "tuv8TUV");
        sRecs.put(16, "wxyz9WXYZ");
        sRecs.put(7, "0+");
        sRecs.put(18, " ");
    }
}
