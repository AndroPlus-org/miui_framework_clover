// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.widget:
//            LinearLayout

public class TouchPanelLayout extends LinearLayout
{

    public TouchPanelLayout(Context context)
    {
        super(context);
        mTemRect = new Rect();
    }

    public TouchPanelLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTemRect = new Rect();
    }

    public TouchPanelLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mTemRect = new Rect();
    }

    private void checkChildState(int i, int j)
    {
        int k = getChildCount();
        for(int l = 0; l < k; l++)
        {
            View view = getChildAt(l);
            if(view.getVisibility() == 0)
            {
                view.getHitRect(mTemRect);
                view.setPressed(mTemRect.contains(i, j));
            }
        }

    }

    private void resetChildState(boolean flag)
    {
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            if(flag && view.isPressed())
                view.performClick();
            view.setPressed(false);
        }

    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        int i;
        int j;
        int k;
        boolean flag;
        i = motionevent.getActionMasked();
        j = (int)motionevent.getX();
        k = (int)motionevent.getY();
        flag = false;
        i;
        JVM INSTR tableswitch 0 2: default 48
    //                   0 56
    //                   1 66
    //                   2 56;
           goto _L1 _L2 _L3 _L2
_L1:
        resetChildState(flag);
_L4:
        return true;
_L2:
        checkChildState(j, k);
        if(true) goto _L4; else goto _L3
_L3:
        flag = true;
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        resetChildState(false);
    }

    private Rect mTemRect;
}
