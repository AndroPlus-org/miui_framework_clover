// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.text.*;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

// Referenced classes of package android.text.method:
//            MetaKeyKeyListener

public class Touch
{
    private static class DragState
        implements NoCopySpan
    {

        public boolean mFarEnough;
        public int mScrollX;
        public int mScrollY;
        public boolean mUsed;
        public float mX;
        public float mY;

        public DragState(float f, float f1, int i, int j)
        {
            mX = f;
            mY = f1;
            mScrollX = i;
            mScrollY = j;
        }
    }


    private Touch()
    {
    }

    public static int getInitialScrollX(TextView textview, Spannable spannable)
    {
        textview = (DragState[])spannable.getSpans(0, spannable.length(), android/text/method/Touch$DragState);
        int i;
        if(textview.length > 0)
            i = ((DragState) (textview[0])).mScrollX;
        else
            i = -1;
        return i;
    }

    public static int getInitialScrollY(TextView textview, Spannable spannable)
    {
        textview = (DragState[])spannable.getSpans(0, spannable.length(), android/text/method/Touch$DragState);
        int i;
        if(textview.length > 0)
            i = ((DragState) (textview[0])).mScrollY;
        else
            i = -1;
        return i;
    }

    public static boolean onTouchEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 2: default 32
    //                   0 34
    //                   1 114
    //                   2 177;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        DragState adragstate[] = (DragState[])spannable.getSpans(0, spannable.length(), android/text/method/Touch$DragState);
        for(int i = 0; i < adragstate.length; i++)
            spannable.removeSpan(adragstate[i]);

        spannable.setSpan(new DragState(motionevent.getX(), motionevent.getY(), textview.getScrollX(), textview.getScrollY()), 0, 0, 17);
        return true;
_L3:
        textview = (DragState[])spannable.getSpans(0, spannable.length(), android/text/method/Touch$DragState);
        for(int j = 0; j < textview.length; j++)
            spannable.removeSpan(textview[j]);

        return textview.length > 0 && ((DragState) (textview[0])).mUsed;
_L4:
        DragState adragstate1[] = (DragState[])spannable.getSpans(0, spannable.length(), android/text/method/Touch$DragState);
        if(adragstate1.length > 0)
        {
            if(!adragstate1[0].mFarEnough)
            {
                int k = ViewConfiguration.get(textview.getContext()).getScaledTouchSlop();
                if(Math.abs(motionevent.getX() - adragstate1[0].mX) >= (float)k || Math.abs(motionevent.getY() - adragstate1[0].mY) >= (float)k)
                    adragstate1[0].mFarEnough = true;
            }
            if(adragstate1[0].mFarEnough)
            {
                adragstate1[0].mUsed = true;
                int l;
                float f;
                float f1;
                int i1;
                int j1;
                int k1;
                int l1;
                int i2;
                if((motionevent.getMetaState() & 1) != 0 || MetaKeyKeyListener.getMetaState(spannable, 1) == 1)
                    l = 1;
                else
                if(MetaKeyKeyListener.getMetaState(spannable, 2048) != 0)
                    l = 1;
                else
                    l = 0;
                if(l != 0)
                {
                    f = motionevent.getX() - adragstate1[0].mX;
                    f1 = motionevent.getY() - adragstate1[0].mY;
                } else
                {
                    f = adragstate1[0].mX - motionevent.getX();
                    f1 = adragstate1[0].mY - motionevent.getY();
                }
                adragstate1[0].mX = motionevent.getX();
                adragstate1[0].mY = motionevent.getY();
                l = textview.getScrollX();
                i1 = (int)f;
                j1 = textview.getScrollY();
                k1 = (int)f1;
                l1 = textview.getTotalPaddingTop();
                i2 = textview.getTotalPaddingBottom();
                spannable = textview.getLayout();
                j1 = Math.max(Math.min(j1 + k1, spannable.getHeight() - (textview.getHeight() - (l1 + i2))), 0);
                l1 = textview.getScrollX();
                k1 = textview.getScrollY();
                scrollTo(textview, spannable, l + i1, j1);
                if(l1 != textview.getScrollX() || k1 != textview.getScrollY())
                    textview.cancelLongPress();
                return true;
            }
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public static void scrollTo(TextView textview, Layout layout, int i, int j)
    {
        int k = textview.getTotalPaddingLeft();
        int l = textview.getTotalPaddingRight();
        int j1 = textview.getWidth() - (k + l);
        int k1 = layout.getLineForVertical(j);
        android.text.Layout.Alignment alignment = layout.getParagraphAlignment(k1);
        boolean flag;
        int i2;
        int j2;
        if(layout.getParagraphDirection(k1) > 0)
            flag = true;
        else
            flag = false;
        if(textview.getHorizontallyScrolling())
        {
            k = textview.getTotalPaddingTop();
            int i1 = textview.getTotalPaddingBottom();
            int l1 = layout.getLineForVertical((textview.getHeight() + j) - (k + i1));
            i1 = 0x7fffffff;
            k = 0;
            do
            {
                i2 = i1;
                j2 = k;
                if(k1 > l1)
                    break;
                i1 = (int)Math.min(i1, layout.getLineLeft(k1));
                k = (int)Math.max(k, layout.getLineRight(k1));
                k1++;
            } while(true);
        } else
        {
            i2 = 0;
            j2 = j1;
        }
        k = j2 - i2;
        if(k < j1)
        {
            if(alignment == android.text.Layout.Alignment.ALIGN_CENTER)
                i = i2 - (j1 - k) / 2;
            else
            if(flag && alignment == android.text.Layout.Alignment.ALIGN_OPPOSITE || !flag && alignment == android.text.Layout.Alignment.ALIGN_NORMAL || alignment == android.text.Layout.Alignment.ALIGN_RIGHT)
                i = i2 - (j1 - k);
            else
                i = i2;
        } else
        {
            i = Math.max(Math.min(i, j2 - j1), i2);
        }
        textview.scrollTo(i, j);
    }
}
