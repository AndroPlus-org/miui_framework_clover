// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class LinearLayoutWithDefaultTouchRecepient extends LinearLayout
{

    public LinearLayoutWithDefaultTouchRecepient(Context context)
    {
        super(context);
        mTempRect = new Rect();
    }

    public LinearLayoutWithDefaultTouchRecepient(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTempRect = new Rect();
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if(mDefaultTouchRecepient == null)
            return super.dispatchTouchEvent(motionevent);
        if(super.dispatchTouchEvent(motionevent))
        {
            return true;
        } else
        {
            mTempRect.set(0, 0, 0, 0);
            offsetRectIntoDescendantCoords(mDefaultTouchRecepient, mTempRect);
            motionevent.setLocation(motionevent.getX() + (float)mTempRect.left, motionevent.getY() + (float)mTempRect.top);
            return mDefaultTouchRecepient.dispatchTouchEvent(motionevent);
        }
    }

    public void setDefaultTouchRecepient(View view)
    {
        mDefaultTouchRecepient = view;
    }

    private View mDefaultTouchRecepient;
    private final Rect mTempRect;
}
