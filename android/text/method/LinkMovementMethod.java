// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.text.*;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

// Referenced classes of package android.text.method:
//            ScrollingMovementMethod, MovementMethod

public class LinkMovementMethod extends ScrollingMovementMethod
{

    public LinkMovementMethod()
    {
    }

    private boolean action(int i, TextView textview, Spannable spannable)
    {
        ClickableSpan aclickablespan[];
        int l1;
        int i2;
        Layout layout = textview.getLayout();
        int j = textview.getTotalPaddingTop();
        int l = textview.getTotalPaddingBottom();
        int j1 = textview.getScrollY();
        l1 = textview.getHeight();
        i2 = layout.getLineForVertical(j1);
        l1 = layout.getLineForVertical((l1 + j1) - (j + l));
        j = layout.getLineStart(i2);
        int j2 = layout.getLineEnd(l1);
        aclickablespan = (ClickableSpan[])spannable.getSpans(j, j2, android/text/style/ClickableSpan);
        l = Selection.getSelectionStart(spannable);
        i2 = Selection.getSelectionEnd(spannable);
        l1 = Math.min(l, i2);
        j1 = Math.max(l, i2);
        l = j1;
        i2 = l1;
        if(l1 < 0)
        {
            l = j1;
            i2 = l1;
            if(spannable.getSpanStart(FROM_BELOW) >= 0)
            {
                l = spannable.length();
                i2 = l;
            }
        }
        l1 = i2;
        if(i2 > j2)
        {
            l = 0x7fffffff;
            l1 = 0x7fffffff;
        }
        i2 = l;
        if(l < j)
        {
            i2 = -1;
            l1 = -1;
        }
        i;
        JVM INSTR tableswitch 1 3: default 232
    //                   1 234
    //                   2 277
    //                   3 386;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        if(l1 == i2)
            return false;
        spannable = (ClickableSpan[])spannable.getSpans(l1, i2, android/text/style/ClickableSpan);
        if(spannable.length != 1)
            return false;
        spannable[0].onClick(textview);
        continue; /* Loop/switch isn't completed */
_L3:
        int k;
        int i1;
        i1 = -1;
        k = -1;
        i = 0;
_L10:
        if(i >= aclickablespan.length) goto _L6; else goto _L5
_L5:
        int l2 = spannable.getSpanEnd(aclickablespan[i]);
        if(l2 < i2) goto _L8; else goto _L7
_L7:
        int k1;
        int k2;
        k2 = k;
        k1 = i1;
        if(l1 != i2) goto _L9; else goto _L8
_L8:
        k2 = k;
        k1 = i1;
        if(l2 > k)
        {
            k1 = spannable.getSpanStart(aclickablespan[i]);
            k2 = l2;
        }
_L9:
        i++;
        k = k2;
        i1 = k1;
          goto _L10
_L6:
        if(i1 >= 0)
        {
            Selection.setSelection(spannable, k, i1);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        i1 = 0x7fffffff;
        k = 0x7fffffff;
        i = 0;
_L16:
        if(i >= aclickablespan.length) goto _L12; else goto _L11
_L11:
        l2 = spannable.getSpanStart(aclickablespan[i]);
        if(l2 > l1) goto _L14; else goto _L13
_L13:
        k2 = k;
        k1 = i1;
        if(l1 != i2) goto _L15; else goto _L14
_L14:
        k2 = k;
        k1 = i1;
        if(l2 < i1)
        {
            k1 = l2;
            k2 = spannable.getSpanEnd(aclickablespan[i]);
        }
_L15:
        i++;
        k = k2;
        i1 = k1;
          goto _L16
_L12:
        if(k < 0x7fffffff)
        {
            Selection.setSelection(spannable, i1, k);
            return true;
        }
        if(true) goto _L1; else goto _L17
_L17:
    }

    public static MovementMethod getInstance()
    {
        if(sInstance == null)
            sInstance = new LinkMovementMethod();
        return sInstance;
    }

    public boolean canSelectArbitrarily()
    {
        return true;
    }

    protected boolean down(TextView textview, Spannable spannable)
    {
        if(action(3, textview, spannable))
            return true;
        else
            return super.down(textview, spannable);
    }

    protected boolean handleMovementKey(TextView textview, Spannable spannable, int i, int j, KeyEvent keyevent)
    {
        i;
        JVM INSTR lookupswitch 2: default 28
    //                   23: 40
    //                   66: 40;
           goto _L1 _L2 _L2
_L1:
        return super.handleMovementKey(textview, spannable, i, j, keyevent);
_L2:
        if(KeyEvent.metaStateHasNoModifiers(j) && keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0 && action(1, textview, spannable))
            return true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void initialize(TextView textview, Spannable spannable)
    {
        Selection.removeSelection(spannable);
        spannable.removeSpan(FROM_BELOW);
    }

    protected boolean left(TextView textview, Spannable spannable)
    {
        if(action(2, textview, spannable))
            return true;
        else
            return super.left(textview, spannable);
    }

    public void onTakeFocus(TextView textview, Spannable spannable, int i)
    {
        Selection.removeSelection(spannable);
        if((i & 1) != 0)
            spannable.setSpan(FROM_BELOW, 0, 0, 34);
        else
            spannable.removeSpan(FROM_BELOW);
    }

    public boolean onTouchEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        int i;
        ClickableSpan aclickablespan[];
        i = motionevent.getAction();
        if(i != 1 && i != 0)
            break MISSING_BLOCK_LABEL_165;
        int j = (int)motionevent.getX();
        int k = (int)motionevent.getY();
        int l = textview.getTotalPaddingLeft();
        int i1 = textview.getTotalPaddingTop();
        int j1 = textview.getScrollX();
        int k1 = textview.getScrollY();
        Layout layout = textview.getLayout();
        l = layout.getOffsetForHorizontal(layout.getLineForVertical((k - i1) + k1), (j - l) + j1);
        aclickablespan = (ClickableSpan[])spannable.getSpans(l, l, android/text/style/ClickableSpan);
        if(aclickablespan.length == 0) goto _L2; else goto _L1
_L1:
        if(i != 1) goto _L4; else goto _L3
_L3:
        aclickablespan[0].onClick(textview);
_L5:
        return true;
_L4:
        if(i == 0)
            Selection.setSelection(spannable, spannable.getSpanStart(aclickablespan[0]), spannable.getSpanEnd(aclickablespan[0]));
        if(true) goto _L5; else goto _L2
_L2:
        Selection.removeSelection(spannable);
        return super.onTouchEvent(textview, spannable, motionevent);
    }

    protected boolean right(TextView textview, Spannable spannable)
    {
        if(action(3, textview, spannable))
            return true;
        else
            return super.right(textview, spannable);
    }

    protected boolean up(TextView textview, Spannable spannable)
    {
        if(action(2, textview, spannable))
            return true;
        else
            return super.up(textview, spannable);
    }

    private static final int CLICK = 1;
    private static final int DOWN = 3;
    private static Object FROM_BELOW = new android.text.NoCopySpan.Concrete();
    private static final int UP = 2;
    private static LinkMovementMethod sInstance;

}
