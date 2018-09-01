// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import com.android.internal.policy.PhoneWindow;
import java.util.ArrayList;

public class DecorCaptionView extends ViewGroup
    implements android.view.View.OnTouchListener, android.view.GestureDetector.OnGestureListener
{

    public DecorCaptionView(Context context)
    {
        super(context);
        mOwner = null;
        mShow = false;
        mDragging = false;
        mOverlayWithAppContent = false;
        mTouchDispatchList = new ArrayList(2);
        mCloseRect = new Rect();
        mMaximizeRect = new Rect();
        init(context);
    }

    public DecorCaptionView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mOwner = null;
        mShow = false;
        mDragging = false;
        mOverlayWithAppContent = false;
        mTouchDispatchList = new ArrayList(2);
        mCloseRect = new Rect();
        mMaximizeRect = new Rect();
        init(context);
    }

    public DecorCaptionView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mOwner = null;
        mShow = false;
        mDragging = false;
        mOverlayWithAppContent = false;
        mTouchDispatchList = new ArrayList(2);
        mCloseRect = new Rect();
        mMaximizeRect = new Rect();
        init(context);
    }

    private void init(Context context)
    {
        mDragSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mGestureDetector = new GestureDetector(context, this);
    }

    private boolean isFillingScreen()
    {
        boolean flag = false;
        if(((getWindowSystemUiVisibility() | getSystemUiVisibility()) & 0xa05) != 0)
            flag = true;
        return flag;
    }

    private void maximizeWindow()
    {
        android.view.Window.WindowControllerCallback windowcontrollercallback;
        windowcontrollercallback = mOwner.getWindowControllerCallback();
        if(windowcontrollercallback == null)
            break MISSING_BLOCK_LABEL_18;
        windowcontrollercallback.exitFreeformMode();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("DecorCaptionView", "Cannot change task workspace.");
          goto _L1
    }

    private boolean passedSlop(int i, int j)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(Math.abs(i - mTouchDownX) <= mDragSlop)
            if(Math.abs(j - mTouchDownY) > mDragSlop)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private void updateCaptionVisibility()
    {
        byte byte0;
        View view;
        if(!isFillingScreen())
            byte0 = mShow ^ true;
        else
            byte0 = 1;
        view = mCaption;
        if(byte0 != 0)
            byte0 = 8;
        else
            byte0 = 0;
        view.setVisibility(byte0);
        mCaption.setOnTouchListener(this);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(!(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams))
            throw new IllegalArgumentException((new StringBuilder()).append("params ").append(layoutparams).append(" must subclass MarginLayoutParams").toString());
        if(i >= 2 || getChildCount() >= 2)
        {
            throw new IllegalStateException("DecorCaptionView can only handle 1 client view");
        } else
        {
            super.addView(view, 0, layoutparams);
            mContent = view;
            return;
        }
    }

    public ArrayList buildTouchDispatchChildList()
    {
        mTouchDispatchList.ensureCapacity(3);
        if(mCaption != null)
            mTouchDispatchList.add(mCaption);
        if(mContent != null)
            mTouchDispatchList.add(mContent);
        return mTouchDispatchList;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof android.view.ViewGroup.MarginLayoutParams;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.MarginLayoutParams(-1, -1);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.view.ViewGroup.MarginLayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new android.view.ViewGroup.MarginLayoutParams(layoutparams);
    }

    public View getCaption()
    {
        return mCaption;
    }

    public int getCaptionHeight()
    {
        int i;
        if(mCaption != null)
            i = mCaption.getHeight();
        else
            i = 0;
        return i;
    }

    public boolean isCaptionShowing()
    {
        return mShow;
    }

    public void onConfigurationChanged(boolean flag)
    {
        mShow = flag;
        updateCaptionVisibility();
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return false;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mCaption = getChildAt(0);
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag = false;
        if(motionevent.getAction() == 0)
        {
            int i = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            if(mMaximizeRect.contains(i, j))
                mClickTarget = mMaximize;
            if(mCloseRect.contains(i, j))
                mClickTarget = mClose;
        }
        if(mClickTarget != null)
            flag = true;
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(mCaption.getVisibility() != 8)
        {
            mCaption.layout(0, 0, mCaption.getMeasuredWidth(), mCaption.getMeasuredHeight());
            i = mCaption.getBottom() - mCaption.getTop();
            mMaximize.getHitRect(mMaximizeRect);
            mClose.getHitRect(mCloseRect);
        } else
        {
            i = 0;
            mMaximizeRect.setEmpty();
            mCloseRect.setEmpty();
        }
        if(mContent != null)
            if(mOverlayWithAppContent)
                mContent.layout(0, 0, mContent.getMeasuredWidth(), mContent.getMeasuredHeight());
            else
                mContent.layout(0, i, mContent.getMeasuredWidth(), mContent.getMeasuredHeight() + i);
        mOwner.notifyRestrictedCaptionAreaCallback(mMaximize.getLeft(), mMaximize.getTop(), mClose.getRight(), mClose.getBottom());
    }

    public void onLongPress(MotionEvent motionevent)
    {
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        if(mCaption.getVisibility() != 8)
        {
            measureChildWithMargins(mCaption, i, 0, j, 0);
            k = mCaption.getMeasuredHeight();
        } else
        {
            k = 0;
        }
        if(mContent != null)
            if(mOverlayWithAppContent)
                measureChildWithMargins(mContent, i, 0, j, 0);
            else
                measureChildWithMargins(mContent, i, 0, j, k);
        setMeasuredDimension(android.view.View.MeasureSpec.getSize(i), android.view.View.MeasureSpec.getSize(j));
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        if(mClickTarget != mMaximize) goto _L2; else goto _L1
_L1:
        maximizeWindow();
_L4:
        return true;
_L2:
        if(mClickTarget == mClose)
            mOwner.dispatchOnWindowDismissed(true, false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        int i;
        int j;
        boolean flag1;
        boolean flag2;
        boolean flag = true;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        if(motionevent.getToolType(motionevent.getActionIndex()) == 3)
            flag1 = true;
        else
            flag1 = false;
        if((motionevent.getButtonState() & 1) != 0)
            flag2 = true;
        else
            flag2 = false;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 76
    //                   0 102
    //                   1 197
    //                   2 141
    //                   3 197;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        if(!mDragging)
            flag = mCheckForDragging;
        return flag;
_L2:
        if(!mShow)
            return false;
        if(!flag1 || flag2)
        {
            mCheckForDragging = true;
            mTouchDownX = i;
            mTouchDownY = j;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(!mDragging && mCheckForDragging && (flag1 || passedSlop(i, j)))
        {
            mCheckForDragging = false;
            mDragging = true;
            startMovingTask(motionevent.getRawX(), motionevent.getRawY());
        }
        if(true) goto _L1; else goto _L3
_L3:
        if(mDragging)
        {
            mDragging = false;
            return mCheckForDragging ^ true;
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mClickTarget != null)
        {
            mGestureDetector.onTouchEvent(motionevent);
            int i = motionevent.getAction();
            if(i == 1 || i == 3)
                mClickTarget = null;
            return true;
        } else
        {
            return false;
        }
    }

    public void removeContentView()
    {
        if(mContent != null)
        {
            removeView(mContent);
            mContent = null;
        }
    }

    public void setPhoneWindow(PhoneWindow phonewindow, boolean flag)
    {
        mOwner = phonewindow;
        mShow = flag;
        mOverlayWithAppContent = phonewindow.isOverlayWithDecorCaptionEnabled();
        if(mOverlayWithAppContent)
            mCaption.setBackgroundColor(0);
        updateCaptionVisibility();
        mOwner.getDecorView().setOutlineProvider(ViewOutlineProvider.BOUNDS);
        mMaximize = findViewById(0x10202f4);
        mClose = findViewById(0x10201fd);
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    private static final String TAG = "DecorCaptionView";
    private View mCaption;
    private boolean mCheckForDragging;
    private View mClickTarget;
    private View mClose;
    private final Rect mCloseRect;
    private View mContent;
    private int mDragSlop;
    private boolean mDragging;
    private GestureDetector mGestureDetector;
    private View mMaximize;
    private final Rect mMaximizeRect;
    private boolean mOverlayWithAppContent;
    private PhoneWindow mOwner;
    private boolean mShow;
    private ArrayList mTouchDispatchList;
    private int mTouchDownX;
    private int mTouchDownY;
}
