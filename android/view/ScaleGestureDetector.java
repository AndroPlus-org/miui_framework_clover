// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Handler;

// Referenced classes of package android.view:
//            InputEventConsistencyVerifier, ViewConfiguration, MotionEvent, GestureDetector

public class ScaleGestureDetector
{
    public static interface OnScaleGestureListener
    {

        public abstract boolean onScale(ScaleGestureDetector scalegesturedetector);

        public abstract boolean onScaleBegin(ScaleGestureDetector scalegesturedetector);

        public abstract void onScaleEnd(ScaleGestureDetector scalegesturedetector);
    }

    public static class SimpleOnScaleGestureListener
        implements OnScaleGestureListener
    {

        public boolean onScale(ScaleGestureDetector scalegesturedetector)
        {
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scalegesturedetector)
        {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scalegesturedetector)
        {
        }

        public SimpleOnScaleGestureListener()
        {
        }
    }


    static int _2D_set0(ScaleGestureDetector scalegesturedetector, int i)
    {
        scalegesturedetector.mAnchoredScaleMode = i;
        return i;
    }

    static float _2D_set1(ScaleGestureDetector scalegesturedetector, float f)
    {
        scalegesturedetector.mAnchoredScaleStartX = f;
        return f;
    }

    static float _2D_set2(ScaleGestureDetector scalegesturedetector, float f)
    {
        scalegesturedetector.mAnchoredScaleStartY = f;
        return f;
    }

    public ScaleGestureDetector(Context context, OnScaleGestureListener onscalegesturelistener)
    {
        this(context, onscalegesturelistener, null);
    }

    public ScaleGestureDetector(Context context, OnScaleGestureListener onscalegesturelistener, Handler handler)
    {
        mAnchoredScaleMode = 0;
        InputEventConsistencyVerifier inputeventconsistencyverifier;
        int i;
        if(InputEventConsistencyVerifier.isInstrumentationEnabled())
            inputeventconsistencyverifier = new InputEventConsistencyVerifier(this, 0);
        else
            inputeventconsistencyverifier = null;
        mInputEventConsistencyVerifier = inputeventconsistencyverifier;
        mContext = context;
        mListener = onscalegesturelistener;
        mSpanSlop = ViewConfiguration.get(context).getScaledTouchSlop() * 2;
        mMinSpan = context.getResources().getDimensionPixelSize(0x1050039);
        mHandler = handler;
        i = context.getApplicationInfo().targetSdkVersion;
        if(i > 18)
            setQuickScaleEnabled(true);
        if(i > 22)
            setStylusScaleEnabled(true);
    }

    private boolean inAnchoredScaleMode()
    {
        boolean flag = false;
        if(mAnchoredScaleMode != 0)
            flag = true;
        return flag;
    }

    public float getCurrentSpan()
    {
        return mCurrSpan;
    }

    public float getCurrentSpanX()
    {
        return mCurrSpanX;
    }

    public float getCurrentSpanY()
    {
        return mCurrSpanY;
    }

    public long getEventTime()
    {
        return mCurrTime;
    }

    public float getFocusX()
    {
        return mFocusX;
    }

    public float getFocusY()
    {
        return mFocusY;
    }

    public float getPreviousSpan()
    {
        return mPrevSpan;
    }

    public float getPreviousSpanX()
    {
        return mPrevSpanX;
    }

    public float getPreviousSpanY()
    {
        return mPrevSpanY;
    }

    public float getScaleFactor()
    {
        float f = 1.0F;
        if(inAnchoredScaleMode())
        {
            boolean flag;
            float f1;
            if(mEventBeforeOrAboveStartingGestureEvent && mCurrSpan < mPrevSpan)
                flag = true;
            else
            if(!mEventBeforeOrAboveStartingGestureEvent && mCurrSpan > mPrevSpan)
                flag = true;
            else
                flag = false;
            f1 = Math.abs(1.0F - mCurrSpan / mPrevSpan) * 0.5F;
            if(mPrevSpan > 0.0F)
                if(flag)
                    f = 1.0F + f1;
                else
                    f = 1.0F - f1;
            return f;
        }
        if(mPrevSpan > 0.0F)
            f = mCurrSpan / mPrevSpan;
        return f;
    }

    public long getTimeDelta()
    {
        return mCurrTime - mPrevTime;
    }

    public boolean isInProgress()
    {
        return mInProgress;
    }

    public boolean isQuickScaleEnabled()
    {
        return mQuickScaleEnabled;
    }

    public boolean isStylusScaleEnabled()
    {
        return mStylusScaleEnabled;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        int j;
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onTouchEvent(motionevent, 0);
        mCurrTime = motionevent.getEventTime();
        i = motionevent.getActionMasked();
        if(mQuickScaleEnabled)
            mGestureDetector.onTouchEvent(motionevent);
        j = motionevent.getPointerCount();
        if((motionevent.getButtonState() & 0x20) != 0)
            flag = true;
        else
            flag = false;
        if(mAnchoredScaleMode == 2)
            flag1 = flag ^ true;
        else
            flag1 = false;
        if(i == 1 || i == 3)
            flag2 = true;
        else
            flag2 = flag1;
        if(i != 0 && !flag2) goto _L2; else goto _L1
_L1:
        if(!mInProgress) goto _L4; else goto _L3
_L3:
        mListener.onScaleEnd(this);
        mInProgress = false;
        mInitialSpan = 0.0F;
        mAnchoredScaleMode = 0;
_L5:
        if(flag2)
            return true;
        break; /* Loop/switch isn't completed */
_L4:
        if(inAnchoredScaleMode() && flag2)
        {
            mInProgress = false;
            mInitialSpan = 0.0F;
            mAnchoredScaleMode = 0;
        }
        if(true) goto _L5; else goto _L2
_L2:
        if(!mInProgress && mStylusScaleEnabled && inAnchoredScaleMode() ^ true && flag2 ^ true && flag)
        {
            mAnchoredScaleStartX = motionevent.getX();
            mAnchoredScaleStartY = motionevent.getY();
            mAnchoredScaleMode = 2;
            mInitialSpan = 0.0F;
        }
        break MISSING_BLOCK_LABEL_248;
        int i1;
        float f;
        float f1;
        float f2;
        float f3;
        int k;
        int j1;
        if(i == 0 || i == 6 || i == 5)
            flag1 = true;
        if(i == 6)
            i1 = 1;
        else
            i1 = 0;
        if(i1 != 0)
            k = motionevent.getActionIndex();
        else
            k = -1;
        f = 0.0F;
        f1 = 0.0F;
        if(i1 != 0)
            i1 = j - 1;
        else
            i1 = j;
        if(inAnchoredScaleMode())
        {
            f1 = mAnchoredScaleStartX;
            f = mAnchoredScaleStartY;
            if(motionevent.getY() < f)
                mEventBeforeOrAboveStartingGestureEvent = true;
            else
                mEventBeforeOrAboveStartingGestureEvent = false;
        } else
        {
            j1 = 0;
            while(j1 < j) 
            {
                if(k != j1)
                {
                    f += motionevent.getX(j1);
                    f1 += motionevent.getY(j1);
                }
                j1++;
            }
            f /= i1;
            f3 = f1 / (float)i1;
            f1 = f;
            f = f3;
        }
        f2 = 0.0F;
        f3 = 0.0F;
        j1 = 0;
        while(j1 < j) 
        {
            if(k != j1)
            {
                f2 += Math.abs(motionevent.getX(j1) - f1);
                f3 += Math.abs(motionevent.getY(j1) - f);
            }
            j1++;
        }
        break MISSING_BLOCK_LABEL_504;
        f2 /= i1;
        f3 /= i1;
        float f4 = f2 * 2.0F;
        f2 = f3 * 2.0F;
        int l;
        boolean flag3;
        if(inAnchoredScaleMode())
            f3 = f2;
        else
            f3 = (float)Math.hypot(f4, f2);
        flag3 = mInProgress;
        mFocusX = f1;
        mFocusY = f;
        if(!inAnchoredScaleMode() && mInProgress && (f3 < (float)mMinSpan || flag1))
        {
            mListener.onScaleEnd(this);
            mInProgress = false;
            mInitialSpan = f3;
        }
        if(flag1)
        {
            mCurrSpanX = f4;
            mPrevSpanX = f4;
            mCurrSpanY = f2;
            mPrevSpanY = f2;
            mCurrSpan = f3;
            mPrevSpan = f3;
            mInitialSpan = f3;
        }
        if(inAnchoredScaleMode())
            l = mSpanSlop;
        else
            l = mMinSpan;
        if(!mInProgress && f3 >= (float)l && (flag3 || Math.abs(f3 - mInitialSpan) > (float)mSpanSlop))
        {
            mCurrSpanX = f4;
            mPrevSpanX = f4;
            mCurrSpanY = f2;
            mPrevSpanY = f2;
            mCurrSpan = f3;
            mPrevSpan = f3;
            mPrevTime = mCurrTime;
            mInProgress = mListener.onScaleBegin(this);
        }
        if(i == 2)
        {
            mCurrSpanX = f4;
            mCurrSpanY = f2;
            mCurrSpan = f3;
            boolean flag4 = true;
            if(mInProgress)
                flag4 = mListener.onScale(this);
            if(flag4)
            {
                mPrevSpanX = mCurrSpanX;
                mPrevSpanY = mCurrSpanY;
                mPrevSpan = mCurrSpan;
                mPrevTime = mCurrTime;
            }
        }
        return true;
    }

    public void setQuickScaleEnabled(boolean flag)
    {
        mQuickScaleEnabled = flag;
        if(mQuickScaleEnabled && mGestureDetector == null)
        {
            GestureDetector.SimpleOnGestureListener simpleongesturelistener = new GestureDetector.SimpleOnGestureListener() {

                public boolean onDoubleTap(MotionEvent motionevent)
                {
                    ScaleGestureDetector._2D_set1(ScaleGestureDetector.this, motionevent.getX());
                    ScaleGestureDetector._2D_set2(ScaleGestureDetector.this, motionevent.getY());
                    ScaleGestureDetector._2D_set0(ScaleGestureDetector.this, 1);
                    return true;
                }

                final ScaleGestureDetector this$0;

            
            {
                this$0 = ScaleGestureDetector.this;
                super();
            }
            }
;
            mGestureDetector = new GestureDetector(mContext, simpleongesturelistener, mHandler);
        }
    }

    public void setStylusScaleEnabled(boolean flag)
    {
        mStylusScaleEnabled = flag;
    }

    private static final int ANCHORED_SCALE_MODE_DOUBLE_TAP = 1;
    private static final int ANCHORED_SCALE_MODE_NONE = 0;
    private static final int ANCHORED_SCALE_MODE_STYLUS = 2;
    private static final float SCALE_FACTOR = 0.5F;
    private static final String TAG = "ScaleGestureDetector";
    private static final long TOUCH_STABILIZE_TIME = 128L;
    private int mAnchoredScaleMode;
    private float mAnchoredScaleStartX;
    private float mAnchoredScaleStartY;
    private final Context mContext;
    private float mCurrSpan;
    private float mCurrSpanX;
    private float mCurrSpanY;
    private long mCurrTime;
    private boolean mEventBeforeOrAboveStartingGestureEvent;
    private float mFocusX;
    private float mFocusY;
    private GestureDetector mGestureDetector;
    private final Handler mHandler;
    private boolean mInProgress;
    private float mInitialSpan;
    private final InputEventConsistencyVerifier mInputEventConsistencyVerifier;
    private final OnScaleGestureListener mListener;
    private int mMinSpan;
    private float mPrevSpan;
    private float mPrevSpanX;
    private float mPrevSpanY;
    private long mPrevTime;
    private boolean mQuickScaleEnabled;
    private int mSpanSlop;
    private boolean mStylusScaleEnabled;
}
