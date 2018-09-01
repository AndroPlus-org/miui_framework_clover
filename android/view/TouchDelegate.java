// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;

// Referenced classes of package android.view:
//            View, ViewConfiguration, MotionEvent

public class TouchDelegate
{

    public TouchDelegate(Rect rect, View view)
    {
        mBounds = rect;
        mSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        mSlopBounds = new Rect(rect);
        mSlopBounds.inset(-mSlop, -mSlop);
        mDelegateView = view;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        int j;
        boolean flag;
        boolean flag1;
        boolean flag2;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        flag = false;
        flag1 = true;
        flag2 = false;
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 56
    //                   0 107
    //                   1 138
    //                   2 138
    //                   3 187;
           goto _L1 _L2 _L3 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_187;
_L1:
        boolean flag3 = flag1;
_L5:
        if(flag)
        {
            View view = mDelegateView;
            boolean flag4;
            if(flag3)
            {
                motionevent.setLocation(view.getWidth() / 2, view.getHeight() / 2);
            } else
            {
                int k = mSlop;
                motionevent.setLocation(-(k * 2), -(k * 2));
            }
            flag2 = view.dispatchTouchEvent(motionevent);
        }
        return flag2;
_L2:
        flag3 = flag1;
        if(mBounds.contains(i, j))
        {
            mDelegateTargeted = true;
            flag = true;
            flag3 = flag1;
        }
          goto _L5
_L3:
        flag4 = mDelegateTargeted;
        flag3 = flag1;
        flag = flag4;
        if(flag4)
        {
            flag3 = flag1;
            flag = flag4;
            if(!mSlopBounds.contains(i, j))
            {
                flag3 = false;
                flag = flag4;
            }
        }
          goto _L5
        flag = mDelegateTargeted;
        mDelegateTargeted = false;
        flag3 = flag1;
          goto _L5
    }

    public static final int ABOVE = 1;
    public static final int BELOW = 2;
    public static final int TO_LEFT = 4;
    public static final int TO_RIGHT = 8;
    private Rect mBounds;
    private boolean mDelegateTargeted;
    private View mDelegateView;
    private int mSlop;
    private Rect mSlopBounds;
}
