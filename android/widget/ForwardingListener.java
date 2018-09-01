// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.os.SystemClock;
import android.view.*;
import com.android.internal.view.menu.ShowableListMenu;

// Referenced classes of package android.widget:
//            DropDownListView

public abstract class ForwardingListener
    implements android.view.View.OnTouchListener, android.view.View.OnAttachStateChangeListener
{
    private class DisallowIntercept
        implements Runnable
    {

        public void run()
        {
            ViewParent viewparent = ForwardingListener._2D_get0(ForwardingListener.this).getParent();
            if(viewparent != null)
                viewparent.requestDisallowInterceptTouchEvent(true);
        }

        final ForwardingListener this$0;

        private DisallowIntercept()
        {
            this$0 = ForwardingListener.this;
            super();
        }

        DisallowIntercept(DisallowIntercept disallowintercept)
        {
            this();
        }
    }

    private class TriggerLongPress
        implements Runnable
    {

        public void run()
        {
            ForwardingListener._2D_wrap0(ForwardingListener.this);
        }

        final ForwardingListener this$0;

        private TriggerLongPress()
        {
            this$0 = ForwardingListener.this;
            super();
        }

        TriggerLongPress(TriggerLongPress triggerlongpress)
        {
            this();
        }
    }


    static View _2D_get0(ForwardingListener forwardinglistener)
    {
        return forwardinglistener.mSrc;
    }

    static void _2D_wrap0(ForwardingListener forwardinglistener)
    {
        forwardinglistener.onLongPress();
    }

    public ForwardingListener(View view)
    {
        mSrc = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        mScaledTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        mLongPressTimeout = (mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private void clearCallbacks()
    {
        if(mTriggerLongPress != null)
            mSrc.removeCallbacks(mTriggerLongPress);
        if(mDisallowIntercept != null)
            mSrc.removeCallbacks(mDisallowIntercept);
    }

    private void onLongPress()
    {
        clearCallbacks();
        View view = mSrc;
        if(!view.isEnabled() || view.isLongClickable())
            return;
        if(!onForwardingStarted())
        {
            return;
        } else
        {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long l = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            view.onTouchEvent(motionevent);
            motionevent.recycle();
            mForwarding = true;
            return;
        }
    }

    private boolean onTouchForwarded(MotionEvent motionevent)
    {
        View view = mSrc;
        Object obj = getPopup();
        if(obj == null || ((ShowableListMenu) (obj)).isShowing() ^ true)
            return false;
        DropDownListView dropdownlistview = (DropDownListView)((ShowableListMenu) (obj)).getListView();
        if(dropdownlistview == null || dropdownlistview.isShown() ^ true)
            return false;
        obj = MotionEvent.obtainNoHistory(motionevent);
        view.toGlobalMotionEvent(((MotionEvent) (obj)));
        dropdownlistview.toLocalMotionEvent(((MotionEvent) (obj)));
        boolean flag = dropdownlistview.onForwardedEvent(((MotionEvent) (obj)), mActivePointerId);
        ((MotionEvent) (obj)).recycle();
        int i = motionevent.getActionMasked();
        boolean flag1;
        if(i != 1)
        {
            if(i != 3)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = false;
        }
        if(!flag)
            flag1 = false;
        return flag1;
    }

    private boolean onTouchObserved(MotionEvent motionevent)
    {
        View view;
        view = mSrc;
        if(!view.isEnabled())
            return false;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 48
    //                   0 50
    //                   1 180
    //                   2 130
    //                   3 180;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        return false;
_L2:
        mActivePointerId = motionevent.getPointerId(0);
        if(mDisallowIntercept == null)
            mDisallowIntercept = new DisallowIntercept(null);
        view.postDelayed(mDisallowIntercept, mTapTimeout);
        if(mTriggerLongPress == null)
            mTriggerLongPress = new TriggerLongPress(null);
        view.postDelayed(mTriggerLongPress, mLongPressTimeout);
        continue; /* Loop/switch isn't completed */
_L4:
        int i = motionevent.findPointerIndex(mActivePointerId);
        if(i >= 0 && !view.pointInView(motionevent.getX(i), motionevent.getY(i), mScaledTouchSlop))
        {
            clearCallbacks();
            view.getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        clearCallbacks();
        if(true) goto _L1; else goto _L5
_L5:
    }

    public abstract ShowableListMenu getPopup();

    protected boolean onForwardingStarted()
    {
        ShowableListMenu showablelistmenu = getPopup();
        if(showablelistmenu != null && showablelistmenu.isShowing() ^ true)
            showablelistmenu.show();
        return true;
    }

    protected boolean onForwardingStopped()
    {
        ShowableListMenu showablelistmenu = getPopup();
        if(showablelistmenu != null && showablelistmenu.isShowing())
            showablelistmenu.dismiss();
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag = mForwarding;
        boolean flag1;
        boolean flag2;
        if(flag)
        {
            if(!onTouchForwarded(motionevent))
                flag1 = onForwardingStopped() ^ true;
            else
                flag1 = true;
        } else
        {
            if(onTouchObserved(motionevent))
                flag2 = onForwardingStarted();
            else
                flag2 = false;
            flag1 = flag2;
            if(flag2)
            {
                long l = SystemClock.uptimeMillis();
                view = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
                mSrc.onTouchEvent(view);
                view.recycle();
                flag1 = flag2;
            }
        }
        mForwarding = flag1;
        if(!flag1)
            flag2 = flag;
        else
            flag2 = true;
        return flag2;
    }

    public void onViewAttachedToWindow(View view)
    {
    }

    public void onViewDetachedFromWindow(View view)
    {
        mForwarding = false;
        mActivePointerId = -1;
        if(mDisallowIntercept != null)
            mSrc.removeCallbacks(mDisallowIntercept);
    }

    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    private final View mSrc;
    private final int mTapTimeout = ViewConfiguration.getTapTimeout();
    private Runnable mTriggerLongPress;
}
