// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.graphics.Rect;
import android.text.*;
import android.view.*;
import android.widget.TextView;

// Referenced classes of package android.text.method:
//            BaseMovementMethod, MovementMethod, MetaKeyKeyListener, WordIterator, 
//            Touch

public class ArrowKeyMovementMethod extends BaseMovementMethod
    implements MovementMethod
{

    public ArrowKeyMovementMethod()
    {
    }

    private static int getCurrentLineTop(Spannable spannable, Layout layout)
    {
        return layout.getLineTop(layout.getLineForOffset(Selection.getSelectionEnd(spannable)));
    }

    public static MovementMethod getInstance()
    {
        if(sInstance == null)
            sInstance = new ArrowKeyMovementMethod();
        return sInstance;
    }

    private static int getPageHeight(TextView textview)
    {
        Rect rect = new Rect();
        int i;
        if(textview.getGlobalVisibleRect(rect))
            i = rect.height();
        else
            i = 0;
        return i;
    }

    private static boolean isSelecting(Spannable spannable)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(MetaKeyKeyListener.getMetaState(spannable, 1) != 1)
            if(MetaKeyKeyListener.getMetaState(spannable, 2048) != 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    protected boolean bottom(TextView textview, Spannable spannable)
    {
        if(isSelecting(spannable))
            Selection.extendSelection(spannable, spannable.length());
        else
            Selection.setSelection(spannable, spannable.length());
        return true;
    }

    public boolean canSelectArbitrarily()
    {
        return true;
    }

    protected boolean down(TextView textview, Spannable spannable)
    {
        textview = textview.getLayout();
        if(isSelecting(spannable))
            return Selection.extendDown(spannable, textview);
        else
            return Selection.moveDown(spannable, textview);
    }

    protected boolean end(TextView textview, Spannable spannable)
    {
        return lineEnd(textview, spannable);
    }

    protected boolean handleMovementKey(TextView textview, Spannable spannable, int i, int j, KeyEvent keyevent)
    {
        i;
        JVM INSTR tableswitch 23 23: default 20
    //                   23 32;
           goto _L1 _L2
_L1:
        return super.handleMovementKey(textview, spannable, i, j, keyevent);
_L2:
        if(KeyEvent.metaStateHasNoModifiers(j) && keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0 && MetaKeyKeyListener.getMetaState(spannable, 2048, keyevent) != 0)
            return textview.showContextMenu();
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected boolean home(TextView textview, Spannable spannable)
    {
        return lineStart(textview, spannable);
    }

    public void initialize(TextView textview, Spannable spannable)
    {
        Selection.setSelection(spannable, 0);
    }

    protected boolean left(TextView textview, Spannable spannable)
    {
        textview = textview.getLayout();
        if(isSelecting(spannable))
            return Selection.extendLeft(spannable, textview);
        else
            return Selection.moveLeft(spannable, textview);
    }

    protected boolean leftWord(TextView textview, Spannable spannable)
    {
        int i = textview.getSelectionEnd();
        textview = textview.getWordIterator();
        textview.setCharSequence(spannable, i, i);
        return Selection.moveToPreceding(spannable, textview, isSelecting(spannable));
    }

    protected boolean lineEnd(TextView textview, Spannable spannable)
    {
        textview = textview.getLayout();
        if(isSelecting(spannable))
            return Selection.extendToRightEdge(spannable, textview);
        else
            return Selection.moveToRightEdge(spannable, textview);
    }

    protected boolean lineStart(TextView textview, Spannable spannable)
    {
        textview = textview.getLayout();
        if(isSelecting(spannable))
            return Selection.extendToLeftEdge(spannable, textview);
        else
            return Selection.moveToLeftEdge(spannable, textview);
    }

    public void onTakeFocus(TextView textview, Spannable spannable, int i)
    {
        if((i & 0x82) != 0)
        {
            if(textview.getLayout() == null)
                Selection.setSelection(spannable, spannable.length());
        } else
        {
            Selection.setSelection(spannable, spannable.length());
        }
    }

    public boolean onTouchEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        int i;
        int k;
        int i1;
        boolean flag;
        boolean flag1;
        i = -1;
        k = -1;
        i1 = motionevent.getAction();
        if(i1 == 1)
        {
            i = Touch.getInitialScrollX(textview, spannable);
            k = Touch.getInitialScrollY(textview, spannable);
        }
        flag = isSelecting(spannable);
        flag1 = Touch.onTouchEvent(textview, spannable, motionevent);
        if(textview.didTouchFocusSelect())
            return flag1;
        if(i1 != 0) goto _L2; else goto _L1
_L1:
        if(isSelecting(spannable))
        {
            if(!textview.isFocused() && !textview.requestFocus())
                return flag1;
            i = textview.getOffsetForPosition(motionevent.getX(), motionevent.getY());
            spannable.setSpan(LAST_TAP_DOWN, i, i, 34);
            textview.getParent().requestDisallowInterceptTouchEvent(true);
        }
_L4:
        return flag1;
_L2:
        if(!textview.isFocused()) goto _L4; else goto _L3
_L3:
        if(i1 != 2)
            continue; /* Loop/switch isn't completed */
        if(!isSelecting(spannable) || !flag1) goto _L4; else goto _L5
_L5:
        k = spannable.getSpanStart(LAST_TAP_DOWN);
        textview.cancelLongPress();
        i = textview.getOffsetForPosition(motionevent.getX(), motionevent.getY());
        Selection.setSelection(spannable, Math.min(k, i), Math.max(k, i));
        return true;
        if(i1 != 1) goto _L4; else goto _L6
_L6:
        while(k >= 0 && k != textview.getScrollY() || i >= 0 && i != textview.getScrollX()) 
        {
            textview.moveCursorToVisibleOffset();
            return true;
        }
        if(flag)
        {
            int l = spannable.getSpanStart(LAST_TAP_DOWN);
            int j = textview.getOffsetForPosition(motionevent.getX(), motionevent.getY());
            Selection.setSelection(spannable, Math.min(l, j), Math.max(l, j));
            spannable.removeSpan(LAST_TAP_DOWN);
        }
        MetaKeyKeyListener.adjustMetaAfterKeypress(spannable);
        MetaKeyKeyListener.resetLockedMeta(spannable);
        return true;
    }

    protected boolean pageDown(TextView textview, Spannable spannable)
    {
        Layout layout;
        boolean flag;
        int i;
        int j;
        boolean flag1;
        layout = textview.getLayout();
        flag = isSelecting(spannable);
        i = getCurrentLineTop(spannable, layout);
        j = getPageHeight(textview);
        flag1 = false;
_L2:
        int k = Selection.getSelectionEnd(spannable);
        if(flag)
            Selection.extendDown(spannable, layout);
        else
            Selection.moveDown(spannable, layout);
        if(Selection.getSelectionEnd(spannable) != k)
        {
            boolean flag2 = true;
            flag1 = true;
            if(getCurrentLineTop(spannable, layout) < i + j)
                continue; /* Loop/switch isn't completed */
            flag1 = flag2;
        }
        return flag1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected boolean pageUp(TextView textview, Spannable spannable)
    {
        Layout layout;
        boolean flag;
        int i;
        int j;
        boolean flag1;
        layout = textview.getLayout();
        flag = isSelecting(spannable);
        i = getCurrentLineTop(spannable, layout);
        j = getPageHeight(textview);
        flag1 = false;
_L2:
        int k = Selection.getSelectionEnd(spannable);
        if(flag)
            Selection.extendUp(spannable, layout);
        else
            Selection.moveUp(spannable, layout);
        if(Selection.getSelectionEnd(spannable) != k)
        {
            boolean flag2 = true;
            flag1 = true;
            if(getCurrentLineTop(spannable, layout) > i - j)
                continue; /* Loop/switch isn't completed */
            flag1 = flag2;
        }
        return flag1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected boolean right(TextView textview, Spannable spannable)
    {
        textview = textview.getLayout();
        if(isSelecting(spannable))
            return Selection.extendRight(spannable, textview);
        else
            return Selection.moveRight(spannable, textview);
    }

    protected boolean rightWord(TextView textview, Spannable spannable)
    {
        int i = textview.getSelectionEnd();
        textview = textview.getWordIterator();
        textview.setCharSequence(spannable, i, i);
        return Selection.moveToFollowing(spannable, textview, isSelecting(spannable));
    }

    protected boolean top(TextView textview, Spannable spannable)
    {
        if(isSelecting(spannable))
            Selection.extendSelection(spannable, 0);
        else
            Selection.setSelection(spannable, 0);
        return true;
    }

    protected boolean up(TextView textview, Spannable spannable)
    {
        textview = textview.getLayout();
        if(isSelecting(spannable))
            return Selection.extendUp(spannable, textview);
        else
            return Selection.moveUp(spannable, textview);
    }

    private static final Object LAST_TAP_DOWN = new Object();
    private static ArrowKeyMovementMethod sInstance;

}
