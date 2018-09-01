// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.widget:
//            ListView, TextView

public class DropDownListView extends ListView
{
    private class ResolveHoverRunnable
        implements Runnable
    {

        public void cancel()
        {
            DropDownListView._2D_set0(DropDownListView.this, null);
            removeCallbacks(this);
        }

        public void post()
        {
            DropDownListView.this.post(this);
        }

        public void run()
        {
            DropDownListView._2D_set0(DropDownListView.this, null);
            drawableStateChanged();
        }

        final DropDownListView this$0;

        private ResolveHoverRunnable()
        {
            this$0 = DropDownListView.this;
            super();
        }

        ResolveHoverRunnable(ResolveHoverRunnable resolvehoverrunnable)
        {
            this();
        }
    }


    static ResolveHoverRunnable _2D_set0(DropDownListView dropdownlistview, ResolveHoverRunnable resolvehoverrunnable)
    {
        dropdownlistview.mResolveHoverRunnable = resolvehoverrunnable;
        return resolvehoverrunnable;
    }

    public DropDownListView(Context context, boolean flag)
    {
        this(context, flag, 0x101006d);
    }

    public DropDownListView(Context context, boolean flag, int i)
    {
        super(context, null, i);
        mHijackFocus = flag;
        setCacheColorHint(0);
    }

    private void clearPressedItem()
    {
        mDrawsInPressedState = false;
        setPressed(false);
        updateSelectorState();
        View view = getChildAt(mMotionPosition - mFirstPosition);
        if(view != null)
            view.setPressed(false);
    }

    private void setPressedItem(View view, int i, float f, float f1)
    {
        mDrawsInPressedState = true;
        drawableHotspotChanged(f, f1);
        if(!isPressed())
            setPressed(true);
        if(mDataChanged)
            layoutChildren();
        View view1 = getChildAt(mMotionPosition - mFirstPosition);
        if(view1 != null && view1 != view && view1.isPressed())
            view1.setPressed(false);
        mMotionPosition = i;
        view.drawableHotspotChanged(f - (float)view.getLeft(), f1 - (float)view.getTop());
        if(!view.isPressed())
            view.setPressed(true);
        setSelectedPositionInt(i);
        positionSelectorLikeTouch(i, view, f, f1);
        refreshDrawableState();
    }

    protected void drawableStateChanged()
    {
        if(mResolveHoverRunnable == null)
            super.drawableStateChanged();
    }

    public boolean hasFocus()
    {
        boolean flag;
        if(!mHijackFocus)
            flag = super.hasFocus();
        else
            flag = true;
        return flag;
    }

    public boolean hasWindowFocus()
    {
        boolean flag;
        if(!mHijackFocus)
            flag = super.hasWindowFocus();
        else
            flag = true;
        return flag;
    }

    public boolean isFocused()
    {
        boolean flag;
        if(!mHijackFocus)
            flag = super.isFocused();
        else
            flag = true;
        return flag;
    }

    public boolean isInTouchMode()
    {
        boolean flag;
        if(!mHijackFocus || !mListSelectionHidden)
            flag = super.isInTouchMode();
        else
            flag = true;
        return flag;
    }

    View obtainView(int i, boolean aflag[])
    {
        aflag = super.obtainView(i, aflag);
        if(aflag instanceof TextView)
            ((TextView)aflag).setHorizontallyScrolling(true);
        return aflag;
    }

    public boolean onForwardedEvent(MotionEvent motionevent, int i)
    {
        boolean flag;
        boolean flag1;
        boolean flag3;
        int j;
        flag = true;
        flag1 = true;
        flag3 = false;
        j = motionevent.getActionMasked();
        j;
        JVM INSTR tableswitch 1 3: default 44
    //                   1 114
    //                   2 116
    //                   3 106;
           goto _L1 _L2 _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_116;
_L1:
        flag = flag1;
        i = ((flag3) ? 1 : 0);
_L5:
        if(!flag || i != 0)
            clearPressedItem();
        boolean flag2;
        int k;
        int l;
        View view;
        if(flag)
        {
            if(mScrollHelper == null)
                mScrollHelper = new com.android.internal.widget.AutoScrollHelper.AbsListViewAutoScroller(this);
            mScrollHelper.setEnabled(true);
            mScrollHelper.onTouch(this, motionevent);
        } else
        if(mScrollHelper != null)
            mScrollHelper.setEnabled(false);
        return flag;
_L4:
        flag = false;
        i = ((flag3) ? 1 : 0);
          goto _L5
_L2:
        flag = false;
        k = motionevent.findPointerIndex(i);
        if(k < 0)
        {
            flag = false;
            i = ((flag3) ? 1 : 0);
        } else
        {
            i = (int)motionevent.getX(k);
            l = (int)motionevent.getY(k);
            k = pointToPosition(i, l);
            if(k == -1)
            {
                i = 1;
            } else
            {
                view = getChildAt(k - getFirstVisiblePosition());
                setPressedItem(view, k, i, l);
                flag2 = true;
                i = ((flag3) ? 1 : 0);
                flag = flag2;
                if(j == 1)
                {
                    performItemClick(view, k, getItemIdAtPosition(k));
                    i = ((flag3) ? 1 : 0);
                    flag = flag2;
                }
            }
        }
          goto _L5
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        i = motionevent.getActionMasked();
        if(i == 10 && mResolveHoverRunnable == null)
        {
            mResolveHoverRunnable = new ResolveHoverRunnable(null);
            mResolveHoverRunnable.post();
        }
        flag = super.onHoverEvent(motionevent);
        if(i != 9 && i != 7) goto _L2; else goto _L1
_L1:
        int j = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
        if(j != -1 && j != mSelectedPosition)
        {
            motionevent = getChildAt(j - getFirstVisiblePosition());
            if(motionevent.isEnabled())
            {
                requestFocus();
                positionSelector(j, motionevent);
                setSelectedPositionInt(j);
                setNextSelectedPositionInt(j);
            }
            updateSelectorState();
        }
_L4:
        return flag;
_L2:
        if(!super.shouldShowSelector())
        {
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mResolveHoverRunnable != null)
            mResolveHoverRunnable.cancel();
        return super.onTouchEvent(motionevent);
    }

    public void setListSelectionHidden(boolean flag)
    {
        mListSelectionHidden = flag;
    }

    boolean shouldShowSelector()
    {
        boolean flag;
        if(!isHovered())
            flag = super.shouldShowSelector();
        else
            flag = true;
        return flag;
    }

    boolean touchModeDrawsInPressedState()
    {
        boolean flag;
        if(!mDrawsInPressedState)
            flag = super.touchModeDrawsInPressedState();
        else
            flag = true;
        return flag;
    }

    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ResolveHoverRunnable mResolveHoverRunnable;
    private com.android.internal.widget.AutoScrollHelper.AbsListViewAutoScroller mScrollHelper;
}
