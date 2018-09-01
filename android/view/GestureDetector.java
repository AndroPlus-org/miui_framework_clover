// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package android.view:
//            ViewConfiguration, InputEventConsistencyVerifier, VelocityTracker, MotionEvent

public class GestureDetector
{
    private class GestureHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 59
        //                       2 79
        //                       3 89;
               goto _L1 _L2 _L3 _L4
_L1:
            throw new RuntimeException((new StringBuilder()).append("Unknown message ").append(message).toString());
_L2:
            GestureDetector._2D_get2(GestureDetector.this).onShowPress(GestureDetector._2D_get0(GestureDetector.this));
_L6:
            return;
_L3:
            GestureDetector._2D_wrap0(GestureDetector.this);
            continue; /* Loop/switch isn't completed */
_L4:
            if(GestureDetector._2D_get1(GestureDetector.this) != null)
                if(!GestureDetector._2D_get3(GestureDetector.this))
                    GestureDetector._2D_get1(GestureDetector.this).onSingleTapConfirmed(GestureDetector._2D_get0(GestureDetector.this));
                else
                    GestureDetector._2D_set0(GestureDetector.this, true);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final GestureDetector this$0;

        GestureHandler()
        {
            this$0 = GestureDetector.this;
            super();
        }

        GestureHandler(Handler handler)
        {
            this$0 = GestureDetector.this;
            super(handler.getLooper());
        }
    }

    public static interface OnContextClickListener
    {

        public abstract boolean onContextClick(MotionEvent motionevent);
    }

    public static interface OnDoubleTapListener
    {

        public abstract boolean onDoubleTap(MotionEvent motionevent);

        public abstract boolean onDoubleTapEvent(MotionEvent motionevent);

        public abstract boolean onSingleTapConfirmed(MotionEvent motionevent);
    }

    public static interface OnGestureListener
    {

        public abstract boolean onDown(MotionEvent motionevent);

        public abstract boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1);

        public abstract void onLongPress(MotionEvent motionevent);

        public abstract boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1);

        public abstract void onShowPress(MotionEvent motionevent);

        public abstract boolean onSingleTapUp(MotionEvent motionevent);
    }

    public static class SimpleOnGestureListener
        implements OnGestureListener, OnDoubleTapListener, OnContextClickListener
    {

        public boolean onContextClick(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onDoubleTap(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onDoubleTapEvent(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onDown(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            return false;
        }

        public void onLongPress(MotionEvent motionevent)
        {
        }

        public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            return false;
        }

        public void onShowPress(MotionEvent motionevent)
        {
        }

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onSingleTapUp(MotionEvent motionevent)
        {
            return false;
        }

        public SimpleOnGestureListener()
        {
        }
    }


    static MotionEvent _2D_get0(GestureDetector gesturedetector)
    {
        return gesturedetector.mCurrentDownEvent;
    }

    static OnDoubleTapListener _2D_get1(GestureDetector gesturedetector)
    {
        return gesturedetector.mDoubleTapListener;
    }

    static OnGestureListener _2D_get2(GestureDetector gesturedetector)
    {
        return gesturedetector.mListener;
    }

    static boolean _2D_get3(GestureDetector gesturedetector)
    {
        return gesturedetector.mStillDown;
    }

    static boolean _2D_set0(GestureDetector gesturedetector, boolean flag)
    {
        gesturedetector.mDeferConfirmSingleTap = flag;
        return flag;
    }

    static void _2D_wrap0(GestureDetector gesturedetector)
    {
        gesturedetector.dispatchLongPress();
    }

    public GestureDetector(Context context, OnGestureListener ongesturelistener)
    {
        this(context, ongesturelistener, null);
    }

    public GestureDetector(Context context, OnGestureListener ongesturelistener, Handler handler)
    {
        InputEventConsistencyVerifier inputeventconsistencyverifier = null;
        super();
        if(InputEventConsistencyVerifier.isInstrumentationEnabled())
            inputeventconsistencyverifier = new InputEventConsistencyVerifier(this, 0);
        mInputEventConsistencyVerifier = inputeventconsistencyverifier;
        if(handler != null)
            mHandler = new GestureHandler(handler);
        else
            mHandler = new GestureHandler();
        mListener = ongesturelistener;
        if(ongesturelistener instanceof OnDoubleTapListener)
            setOnDoubleTapListener((OnDoubleTapListener)ongesturelistener);
        if(ongesturelistener instanceof OnContextClickListener)
            setContextClickListener((OnContextClickListener)ongesturelistener);
        init(context);
    }

    public GestureDetector(Context context, OnGestureListener ongesturelistener, Handler handler, boolean flag)
    {
        this(context, ongesturelistener, handler);
    }

    public GestureDetector(OnGestureListener ongesturelistener)
    {
        this(null, ongesturelistener, null);
    }

    public GestureDetector(OnGestureListener ongesturelistener, Handler handler)
    {
        this(null, ongesturelistener, handler);
    }

    private void cancel()
    {
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mVelocityTracker.recycle();
        mVelocityTracker = null;
        mIsDoubleTapping = false;
        mStillDown = false;
        mAlwaysInTapRegion = false;
        mAlwaysInBiggerTapRegion = false;
        mDeferConfirmSingleTap = false;
        mInLongPress = false;
        mInContextClick = false;
        mIgnoreNextUpEvent = false;
    }

    private void cancelTaps()
    {
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mIsDoubleTapping = false;
        mAlwaysInTapRegion = false;
        mAlwaysInBiggerTapRegion = false;
        mDeferConfirmSingleTap = false;
        mInLongPress = false;
        mInContextClick = false;
        mIgnoreNextUpEvent = false;
    }

    private void dispatchLongPress()
    {
        mHandler.removeMessages(3);
        mDeferConfirmSingleTap = false;
        mInLongPress = true;
        mListener.onLongPress(mCurrentDownEvent);
    }

    private void init(Context context)
    {
        if(mListener == null)
            throw new NullPointerException("OnGestureListener must not be null");
        mIsLongpressEnabled = true;
        int i;
        int j;
        int k;
        if(context == null)
        {
            i = ViewConfiguration.getTouchSlop();
            j = i;
            k = ViewConfiguration.getDoubleTapSlop();
            mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
        } else
        {
            context = ViewConfiguration.get(context);
            i = context.getScaledTouchSlop();
            j = context.getScaledDoubleTapTouchSlop();
            k = context.getScaledDoubleTapSlop();
            mMinimumFlingVelocity = context.getScaledMinimumFlingVelocity();
            mMaximumFlingVelocity = context.getScaledMaximumFlingVelocity();
        }
        mTouchSlopSquare = i * i;
        mDoubleTapTouchSlopSquare = j * j;
        mDoubleTapSlopSquare = k * k;
    }

    private boolean isConsideredDoubleTap(MotionEvent motionevent, MotionEvent motionevent1, MotionEvent motionevent2)
    {
        boolean flag = false;
        if(!mAlwaysInBiggerTapRegion)
            return false;
        long l = motionevent2.getEventTime() - motionevent1.getEventTime();
        if(l > (long)DOUBLE_TAP_TIMEOUT || l < (long)DOUBLE_TAP_MIN_TIME)
            return false;
        int i = (int)motionevent.getX() - (int)motionevent2.getX();
        int j = (int)motionevent.getY() - (int)motionevent2.getY();
        int k;
        if((motionevent.getFlags() & 8) != 0)
            k = 1;
        else
            k = 0;
        if(k != 0)
            k = 0;
        else
            k = mDoubleTapSlopSquare;
        if(i * i + j * j < k)
            flag = true;
        return flag;
    }

    public boolean isLongpressEnabled()
    {
        return mIsLongpressEnabled;
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        int i;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onGenericMotionEvent(motionevent, 0);
        i = motionevent.getActionButton();
        motionevent.getActionMasked();
        JVM INSTR tableswitch 11 12: default 48
    //                   11 50
    //                   12 122;
           goto _L1 _L2 _L3
_L1:
        return false;
_L2:
        if(mContextClickListener != null && mInContextClick ^ true && mInLongPress ^ true && (i == 32 || i == 2) && mContextClickListener.onContextClick(motionevent))
        {
            mInContextClick = true;
            mHandler.removeMessages(2);
            mHandler.removeMessages(3);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mInContextClick && (i == 32 || i == 2))
        {
            mInContextClick = false;
            mIgnoreNextUpEvent = true;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int j;
        int k1;
        float f;
        int i2;
        float f7;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        int k2;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onTouchEvent(motionevent, 0);
        int i = motionevent.getAction();
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        boolean flag;
        int l;
        float f2;
        int j2;
        if((i & 0xff) == 6)
            flag = true;
        else
            flag = false;
        if(flag)
            l = motionevent.getActionIndex();
        else
            l = -1;
        if((motionevent.getFlags() & 8) != 0)
            k1 = 1;
        else
            k1 = 0;
        f = 0.0F;
        f2 = 0.0F;
        i2 = motionevent.getPointerCount();
        j2 = 0;
        while(j2 < i2) 
        {
            float f6;
            if(l == j2)
            {
                f6 = f2;
                f2 = f;
            } else
            {
                f += motionevent.getX(j2);
                f6 = f2 + motionevent.getY(j2);
                f2 = f;
            }
            j2++;
            f = f2;
            f2 = f6;
        }
        if(flag)
            j = i2 - 1;
        else
            j = i2;
        f /= j;
        f7 = f2 / (float)j;
        j = 0;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        k2 = ((flag3) ? 1 : 0);
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 260
    //                   0 480
    //                   1 1028
    //                   2 744
    //                   3 1336
    //                   4 264
    //                   5 294
    //                   6 329;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        k2 = ((flag3) ? 1 : 0);
_L17:
        if(k2 == 0 && mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(motionevent, 0);
        return k2;
_L7:
        mLastFocusX = f;
        mDownFocusX = f;
        mLastFocusY = f7;
        mDownFocusY = f7;
        cancelTaps();
        k2 = ((flag3) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L8:
        float f3;
        mLastFocusX = f;
        mDownFocusX = f;
        mLastFocusY = f7;
        mDownFocusY = f7;
        mVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
        j = motionevent.getActionIndex();
        k1 = motionevent.getPointerId(j);
        f3 = mVelocityTracker.getXVelocity(k1);
        f = mVelocityTracker.getYVelocity(k1);
        k1 = 0;
_L10:
        k2 = ((flag3) ? 1 : 0);
        if(k1 >= i2)
            continue; /* Loop/switch isn't completed */
        if(k1 != j)
            break; /* Loop/switch isn't completed */
_L12:
        k1++;
        int i1;
        if(true) goto _L10; else goto _L9
_L9:
        if(f3 * mVelocityTracker.getXVelocity(i1 = motionevent.getPointerId(k1)) + f * mVelocityTracker.getYVelocity(i1) >= 0.0F) goto _L12; else goto _L11
_L11:
        mVelocityTracker.clear();
        k2 = ((flag3) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L2:
        k1 = j;
        if(mDoubleTapListener != null)
        {
            k2 = mHandler.hasMessages(3);
            if(k2 != 0)
                mHandler.removeMessages(3);
            if(mCurrentDownEvent != null && mPreviousUpEvent != null && k2 != 0 && isConsideredDoubleTap(mCurrentDownEvent, mPreviousUpEvent, motionevent))
            {
                mIsDoubleTapping = true;
                k1 = mDoubleTapListener.onDoubleTap(mCurrentDownEvent) | mDoubleTapListener.onDoubleTapEvent(motionevent);
            } else
            {
                mHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
                k1 = j;
            }
        }
        mLastFocusX = f;
        mDownFocusX = f;
        mLastFocusY = f7;
        mDownFocusY = f7;
        if(mCurrentDownEvent != null)
            mCurrentDownEvent.recycle();
        mCurrentDownEvent = MotionEvent.obtain(motionevent);
        mAlwaysInTapRegion = true;
        mAlwaysInBiggerTapRegion = true;
        mStillDown = true;
        mInLongPress = false;
        mDeferConfirmSingleTap = false;
        if(mIsLongpressEnabled)
        {
            mHandler.removeMessages(2);
            mHandler.sendEmptyMessageAtTime(2, mCurrentDownEvent.getDownTime() + (long)LONGPRESS_TIMEOUT);
        }
        mHandler.sendEmptyMessageAtTime(1, mCurrentDownEvent.getDownTime() + (long)TAP_TIMEOUT);
        k2 = k1 | mListener.onDown(motionevent);
        continue; /* Loop/switch isn't completed */
_L4:
        k2 = ((flag3) ? 1 : 0);
        if(mInLongPress)
            continue; /* Loop/switch isn't completed */
        k2 = ((flag3) ? 1 : 0);
        if(mInContextClick)
            continue; /* Loop/switch isn't completed */
        float f4 = mLastFocusX - f;
        float f8 = mLastFocusY - f7;
        if(mIsDoubleTapping)
        {
            k2 = mDoubleTapListener.onDoubleTapEvent(motionevent);
            continue; /* Loop/switch isn't completed */
        }
        if(mAlwaysInTapRegion)
        {
            int j1 = (int)(f - mDownFocusX);
            int k = (int)(f7 - mDownFocusY);
            j1 = j1 * j1 + k * k;
            if(k1 != 0)
                k = 0;
            else
                k = mTouchSlopSquare;
            flag2 = flag1;
            if(j1 > k)
            {
                flag2 = mListener.onScroll(mCurrentDownEvent, motionevent, f4, f8);
                mLastFocusX = f;
                mLastFocusY = f7;
                mAlwaysInTapRegion = false;
                mHandler.removeMessages(3);
                mHandler.removeMessages(1);
                mHandler.removeMessages(2);
            }
            if(k1 != 0)
                k1 = 0;
            else
                k1 = mDoubleTapTouchSlopSquare;
            k2 = ((flag2) ? 1 : 0);
            if(j1 > k1)
            {
                mAlwaysInBiggerTapRegion = false;
                k2 = ((flag2) ? 1 : 0);
            }
            continue; /* Loop/switch isn't completed */
        }
        if(Math.abs(f4) < 1.0F)
        {
            k2 = ((flag3) ? 1 : 0);
            if(Math.abs(f8) < 1.0F)
                continue; /* Loop/switch isn't completed */
        }
        k2 = mListener.onScroll(mCurrentDownEvent, motionevent, f4, f8);
        mLastFocusX = f;
        mLastFocusY = f7;
        continue; /* Loop/switch isn't completed */
_L3:
        MotionEvent motionevent1;
        mStillDown = false;
        motionevent1 = MotionEvent.obtain(motionevent);
        if(!mIsDoubleTapping) goto _L14; else goto _L13
_L13:
        k2 = mDoubleTapListener.onDoubleTapEvent(motionevent);
_L15:
        if(mPreviousUpEvent != null)
            mPreviousUpEvent.recycle();
        mPreviousUpEvent = motionevent1;
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        mIsDoubleTapping = false;
        mDeferConfirmSingleTap = false;
        mIgnoreNextUpEvent = false;
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        continue; /* Loop/switch isn't completed */
_L14:
        if(mInLongPress)
        {
            mHandler.removeMessages(3);
            mInLongPress = false;
            k2 = ((flag2) ? 1 : 0);
            continue; /* Loop/switch isn't completed */
        }
        if(mAlwaysInTapRegion && mIgnoreNextUpEvent ^ true)
        {
            flag2 = mListener.onSingleTapUp(motionevent);
            k2 = ((flag2) ? 1 : 0);
            if(mDeferConfirmSingleTap)
            {
                k2 = ((flag2) ? 1 : 0);
                if(mDoubleTapListener != null)
                {
                    mDoubleTapListener.onSingleTapConfirmed(motionevent);
                    k2 = ((flag2) ? 1 : 0);
                }
            }
            continue; /* Loop/switch isn't completed */
        }
        k2 = ((flag2) ? 1 : 0);
        if(mIgnoreNextUpEvent)
            continue; /* Loop/switch isn't completed */
        VelocityTracker velocitytracker = mVelocityTracker;
        int l1 = motionevent.getPointerId(0);
        velocitytracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
        float f1 = velocitytracker.getYVelocity(l1);
        float f5 = velocitytracker.getXVelocity(l1);
        if(Math.abs(f1) <= (float)mMinimumFlingVelocity)
        {
            k2 = ((flag2) ? 1 : 0);
            if(Math.abs(f5) <= (float)mMinimumFlingVelocity)
                continue; /* Loop/switch isn't completed */
        }
        k2 = mListener.onFling(mCurrentDownEvent, motionevent, f5, f1);
        if(true) goto _L15; else goto _L5
_L5:
        cancel();
        k2 = ((flag3) ? 1 : 0);
        if(true) goto _L17; else goto _L16
_L16:
    }

    public void setContextClickListener(OnContextClickListener oncontextclicklistener)
    {
        mContextClickListener = oncontextclicklistener;
    }

    public void setIsLongpressEnabled(boolean flag)
    {
        mIsLongpressEnabled = flag;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener ondoubletaplistener)
    {
        mDoubleTapListener = ondoubletaplistener;
    }

    private static final int DOUBLE_TAP_MIN_TIME = ViewConfiguration.getDoubleTapMinTime();
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private static final int LONG_PRESS = 2;
    private static final int SHOW_PRESS = 1;
    private static final int TAP = 3;
    private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    private boolean mAlwaysInBiggerTapRegion;
    private boolean mAlwaysInTapRegion;
    private OnContextClickListener mContextClickListener;
    private MotionEvent mCurrentDownEvent;
    private boolean mDeferConfirmSingleTap;
    private OnDoubleTapListener mDoubleTapListener;
    private int mDoubleTapSlopSquare;
    private int mDoubleTapTouchSlopSquare;
    private float mDownFocusX;
    private float mDownFocusY;
    private final Handler mHandler;
    private boolean mIgnoreNextUpEvent;
    private boolean mInContextClick;
    private boolean mInLongPress;
    private final InputEventConsistencyVerifier mInputEventConsistencyVerifier;
    private boolean mIsDoubleTapping;
    private boolean mIsLongpressEnabled;
    private float mLastFocusX;
    private float mLastFocusY;
    private final OnGestureListener mListener;
    private int mMaximumFlingVelocity;
    private int mMinimumFlingVelocity;
    private MotionEvent mPreviousUpEvent;
    private boolean mStillDown;
    private int mTouchSlopSquare;
    private VelocityTracker mVelocityTracker;

}
