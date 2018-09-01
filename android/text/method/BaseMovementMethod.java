// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.text.*;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

// Referenced classes of package android.text.method:
//            MovementMethod, MetaKeyKeyListener, Touch

public class BaseMovementMethod
    implements MovementMethod
{

    public BaseMovementMethod()
    {
    }

    private int getBottomLine(TextView textview)
    {
        return textview.getLayout().getLineForVertical(textview.getScrollY() + getInnerHeight(textview));
    }

    private int getCharacterWidth(TextView textview)
    {
        return (int)Math.ceil(textview.getPaint().getFontSpacing());
    }

    private int getInnerHeight(TextView textview)
    {
        return textview.getHeight() - textview.getTotalPaddingTop() - textview.getTotalPaddingBottom();
    }

    private int getInnerWidth(TextView textview)
    {
        return textview.getWidth() - textview.getTotalPaddingLeft() - textview.getTotalPaddingRight();
    }

    private int getScrollBoundsLeft(TextView textview)
    {
        Layout layout = textview.getLayout();
        int i = getTopLine(textview);
        int j = getBottomLine(textview);
        if(i > j)
            return 0;
        int k;
        int i1;
        for(k = 0x7fffffff; i <= j; k = i1)
        {
            int l = (int)Math.floor(layout.getLineLeft(i));
            i1 = k;
            if(l < k)
                i1 = l;
            i++;
        }

        return k;
    }

    private int getScrollBoundsRight(TextView textview)
    {
        Layout layout = textview.getLayout();
        int i = getTopLine(textview);
        int j = getBottomLine(textview);
        if(i > j)
            return 0;
        int k;
        int i1;
        for(k = 0x80000000; i <= j; k = i1)
        {
            int l = (int)Math.ceil(layout.getLineRight(i));
            i1 = k;
            if(l > k)
                i1 = l;
            i++;
        }

        return k;
    }

    private int getTopLine(TextView textview)
    {
        return textview.getLayout().getLineForVertical(textview.getScrollY());
    }

    protected boolean bottom(TextView textview, Spannable spannable)
    {
        return false;
    }

    public boolean canSelectArbitrarily()
    {
        return false;
    }

    protected boolean down(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean end(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected int getMovementMetaState(Spannable spannable, KeyEvent keyevent)
    {
        return KeyEvent.normalizeMetaState(MetaKeyKeyListener.getMetaState(spannable, keyevent) & 0xfffff9ff) & 0xffffff3e;
    }

    protected boolean handleMovementKey(TextView textview, Spannable spannable, int i, int j, KeyEvent keyevent)
    {
        i;
        JVM INSTR lookupswitch 8: default 76
    //                   19: 176
    //                   20: 207
    //                   21: 78
    //                   22: 127
    //                   92: 238
    //                   93: 269
    //                   122: 300
    //                   123: 333;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        return false;
_L4:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return left(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 4096))
            return leftWord(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 2))
            return lineStart(textview, spannable);
        continue; /* Loop/switch isn't completed */
_L5:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return right(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 4096))
            return rightWord(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 2))
            return lineEnd(textview, spannable);
        if(true) goto _L1; else goto _L2
_L2:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return up(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 2))
            return top(textview, spannable);
        if(true)
            continue; /* Loop/switch isn't completed */
_L3:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return down(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 2))
            return bottom(textview, spannable);
        if(true) goto _L1; else goto _L6
_L6:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return pageUp(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 2))
            return top(textview, spannable);
        if(true)
            continue; /* Loop/switch isn't completed */
_L7:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return pageDown(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 2))
            return bottom(textview, spannable);
        if(true) goto _L1; else goto _L8
_L8:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return home(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 4096))
            return top(textview, spannable);
        if(true)
            continue; /* Loop/switch isn't completed */
_L9:
        if(KeyEvent.metaStateHasNoModifiers(j))
            return end(textview, spannable);
        if(KeyEvent.metaStateHasModifiers(j, 4096))
            return bottom(textview, spannable);
        if(true) goto _L1; else goto _L10
_L10:
    }

    protected boolean home(TextView textview, Spannable spannable)
    {
        return false;
    }

    public void initialize(TextView textview, Spannable spannable)
    {
    }

    protected boolean left(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean leftWord(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean lineEnd(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean lineStart(TextView textview, Spannable spannable)
    {
        return false;
    }

    public boolean onGenericMotionEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        if((motionevent.getSource() & 2) == 0) goto _L2; else goto _L1
_L1:
        motionevent.getAction();
        JVM INSTR tableswitch 8 8: default 32
    //                   8 34;
           goto _L2 _L3
_L2:
        return false;
_L3:
        float f;
        float f1;
        boolean flag;
        boolean flag1;
        if((motionevent.getMetaState() & 1) != 0)
        {
            f = 0.0F;
            f1 = motionevent.getAxisValue(9);
        } else
        {
            f = -motionevent.getAxisValue(9);
            f1 = motionevent.getAxisValue(10);
        }
        flag = false;
        if(f1 < 0.0F)
            flag = scrollLeft(textview, spannable, (int)Math.ceil(-f1));
        else
        if(f1 > 0.0F)
            flag = scrollRight(textview, spannable, (int)Math.ceil(f1));
        if(f < 0.0F)
        {
            flag1 = flag | scrollUp(textview, spannable, (int)Math.ceil(-f));
        } else
        {
            flag1 = flag;
            if(f > 0.0F)
                flag1 = flag | scrollDown(textview, spannable, (int)Math.ceil(f));
        }
        return flag1;
    }

    public boolean onKeyDown(TextView textview, Spannable spannable, int i, KeyEvent keyevent)
    {
        boolean flag = handleMovementKey(textview, spannable, i, getMovementMetaState(spannable, keyevent), keyevent);
        if(flag)
        {
            MetaKeyKeyListener.adjustMetaAfterKeypress(spannable);
            MetaKeyKeyListener.resetLockedMeta(spannable);
        }
        return flag;
    }

    public boolean onKeyOther(TextView textview, Spannable spannable, KeyEvent keyevent)
    {
        int i = getMovementMetaState(spannable, keyevent);
        int j = keyevent.getKeyCode();
        if(j != 0 && keyevent.getAction() == 2)
        {
            int k = keyevent.getRepeatCount();
            boolean flag = false;
            int l = 0;
            do
            {
                if(l >= k || !handleMovementKey(textview, spannable, j, i, keyevent))
                {
                    if(flag)
                    {
                        MetaKeyKeyListener.adjustMetaAfterKeypress(spannable);
                        MetaKeyKeyListener.resetLockedMeta(spannable);
                    }
                    return flag;
                }
                flag = true;
                l++;
            } while(true);
        } else
        {
            return false;
        }
    }

    public boolean onKeyUp(TextView textview, Spannable spannable, int i, KeyEvent keyevent)
    {
        return false;
    }

    public void onTakeFocus(TextView textview, Spannable spannable, int i)
    {
    }

    public boolean onTouchEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        return false;
    }

    public boolean onTrackballEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        return false;
    }

    protected boolean pageDown(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean pageUp(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean right(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean rightWord(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean scrollBottom(TextView textview, Spannable spannable)
    {
        spannable = textview.getLayout();
        int i = spannable.getLineCount();
        if(getBottomLine(textview) <= i - 1)
        {
            Touch.scrollTo(textview, spannable, textview.getScrollX(), spannable.getLineTop(i) - getInnerHeight(textview));
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollDown(TextView textview, Spannable spannable, int i)
    {
        spannable = textview.getLayout();
        int j = getInnerHeight(textview);
        int k = textview.getScrollY() + j;
        int l = spannable.getLineForVertical(k);
        int i1 = l;
        if(spannable.getLineTop(l + 1) < k + 1)
            i1 = l + 1;
        l = spannable.getLineCount() - 1;
        if(i1 <= l)
        {
            i = Math.min((i1 + i) - 1, l);
            Touch.scrollTo(textview, spannable, textview.getScrollX(), spannable.getLineTop(i + 1) - j);
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollLeft(TextView textview, Spannable spannable, int i)
    {
        int j = getScrollBoundsLeft(textview);
        int k = textview.getScrollX();
        if(k > j)
        {
            textview.scrollTo(Math.max(k - getCharacterWidth(textview) * i, j), textview.getScrollY());
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollLineEnd(TextView textview, Spannable spannable)
    {
        int i = getScrollBoundsRight(textview) - getInnerWidth(textview);
        if(textview.getScrollX() < i)
        {
            textview.scrollTo(i, textview.getScrollY());
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollLineStart(TextView textview, Spannable spannable)
    {
        int i = getScrollBoundsLeft(textview);
        if(textview.getScrollX() > i)
        {
            textview.scrollTo(i, textview.getScrollY());
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollPageDown(TextView textview, Spannable spannable)
    {
        spannable = textview.getLayout();
        int i = getInnerHeight(textview);
        int j = spannable.getLineForVertical(textview.getScrollY() + i + i);
        if(j <= spannable.getLineCount() - 1)
        {
            Touch.scrollTo(textview, spannable, textview.getScrollX(), spannable.getLineTop(j + 1) - i);
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollPageUp(TextView textview, Spannable spannable)
    {
        spannable = textview.getLayout();
        int i = spannable.getLineForVertical(textview.getScrollY() - getInnerHeight(textview));
        if(i >= 0)
        {
            Touch.scrollTo(textview, spannable, textview.getScrollX(), spannable.getLineTop(i));
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollRight(TextView textview, Spannable spannable, int i)
    {
        int j = getScrollBoundsRight(textview) - getInnerWidth(textview);
        int k = textview.getScrollX();
        if(k < j)
        {
            textview.scrollTo(Math.min(getCharacterWidth(textview) * i + k, j), textview.getScrollY());
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollTop(TextView textview, Spannable spannable)
    {
        spannable = textview.getLayout();
        if(getTopLine(textview) >= 0)
        {
            Touch.scrollTo(textview, spannable, textview.getScrollX(), spannable.getLineTop(0));
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean scrollUp(TextView textview, Spannable spannable, int i)
    {
        spannable = textview.getLayout();
        int j = textview.getScrollY();
        int k = spannable.getLineForVertical(j);
        int l = k;
        if(spannable.getLineTop(k) == j)
            l = k - 1;
        if(l >= 0)
        {
            i = Math.max((l - i) + 1, 0);
            Touch.scrollTo(textview, spannable, textview.getScrollX(), spannable.getLineTop(i));
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean top(TextView textview, Spannable spannable)
    {
        return false;
    }

    protected boolean up(TextView textview, Spannable spannable)
    {
        return false;
    }
}
