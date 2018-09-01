// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.View;
import android.widget.FrameLayout;

public class MaskLayout extends FrameLayout
{

    public MaskLayout(Context context)
    {
        super(context);
        mMaskColor = 0;
    }

    public MaskLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMaskColor = 0;
    }

    public MaskLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mMaskColor = 0;
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        if(mDrawTouchMask)
        {
            canvas.saveLayer(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), null, 31);
            boolean flag = super.drawChild(canvas, view, l);
            canvas.drawColor(mMaskColor, android.graphics.PorterDuff.Mode.SRC_ATOP);
            canvas.restore();
            return flag;
        } else
        {
            return super.drawChild(canvas, view, l);
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag;
        if(!StateSet.stateSetMatches(PRESSED_STATE_SET, ai))
            flag = StateSet.stateSetMatches(FOCUSED_WINDOW_FOCUSED_STATE_SET, ai);
        else
            flag = true;
        if(mDrawTouchMask != flag)
        {
            mDrawTouchMask = flag;
            invalidate();
        }
    }

    public void setMaskColor(int i)
    {
        mMaskColor = i;
    }

    private boolean mDrawTouchMask;
    private int mMaskColor;
}
