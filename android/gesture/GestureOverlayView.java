// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import java.util.ArrayList;

// Referenced classes of package android.gesture:
//            Gesture, GesturePoint, GestureUtils, OrientedBoundingBox, 
//            GestureStroke

public class GestureOverlayView extends FrameLayout
{
    private class FadeOutRunnable
        implements Runnable
    {

        public void run()
        {
            if(GestureOverlayView._2D_get4(GestureOverlayView.this))
            {
                long l = AnimationUtils.currentAnimationTimeMillis() - GestureOverlayView._2D_get2(GestureOverlayView.this);
                if(l > GestureOverlayView._2D_get0(GestureOverlayView.this))
                {
                    if(fireActionPerformed)
                        GestureOverlayView._2D_wrap0(GestureOverlayView.this);
                    GestureOverlayView._2D_set4(GestureOverlayView.this, false);
                    GestureOverlayView._2D_set3(GestureOverlayView.this, false);
                    GestureOverlayView._2D_set2(GestureOverlayView.this, false);
                    GestureOverlayView._2D_get5(GestureOverlayView.this).rewind();
                    GestureOverlayView._2D_set0(GestureOverlayView.this, null);
                    GestureOverlayView._2D_wrap1(GestureOverlayView.this, 255);
                } else
                {
                    GestureOverlayView._2D_set2(GestureOverlayView.this, true);
                    float f = Math.max(0.0F, Math.min(1.0F, (float)l / (float)GestureOverlayView._2D_get0(GestureOverlayView.this)));
                    GestureOverlayView._2D_set1(GestureOverlayView.this, 1.0F - GestureOverlayView._2D_get3(GestureOverlayView.this).getInterpolation(f));
                    GestureOverlayView._2D_wrap1(GestureOverlayView.this, (int)(GestureOverlayView._2D_get1(GestureOverlayView.this) * 255F));
                    postDelayed(this, 16L);
                }
            } else
            if(resetMultipleStrokes)
            {
                GestureOverlayView._2D_set5(GestureOverlayView.this, true);
            } else
            {
                GestureOverlayView._2D_wrap0(GestureOverlayView.this);
                GestureOverlayView._2D_set2(GestureOverlayView.this, false);
                GestureOverlayView._2D_get5(GestureOverlayView.this).rewind();
                GestureOverlayView._2D_set0(GestureOverlayView.this, null);
                GestureOverlayView._2D_set4(GestureOverlayView.this, false);
                GestureOverlayView._2D_wrap1(GestureOverlayView.this, 255);
            }
            invalidate();
        }

        boolean fireActionPerformed;
        boolean resetMultipleStrokes;
        final GestureOverlayView this$0;

        private FadeOutRunnable()
        {
            this$0 = GestureOverlayView.this;
            super();
        }

        FadeOutRunnable(FadeOutRunnable fadeoutrunnable)
        {
            this();
        }
    }

    public static interface OnGestureListener
    {

        public abstract void onGesture(GestureOverlayView gestureoverlayview, MotionEvent motionevent);

        public abstract void onGestureCancelled(GestureOverlayView gestureoverlayview, MotionEvent motionevent);

        public abstract void onGestureEnded(GestureOverlayView gestureoverlayview, MotionEvent motionevent);

        public abstract void onGestureStarted(GestureOverlayView gestureoverlayview, MotionEvent motionevent);
    }

    public static interface OnGesturePerformedListener
    {

        public abstract void onGesturePerformed(GestureOverlayView gestureoverlayview, Gesture gesture);
    }

    public static interface OnGesturingListener
    {

        public abstract void onGesturingEnded(GestureOverlayView gestureoverlayview);

        public abstract void onGesturingStarted(GestureOverlayView gestureoverlayview);
    }


    static long _2D_get0(GestureOverlayView gestureoverlayview)
    {
        return gestureoverlayview.mFadeDuration;
    }

    static float _2D_get1(GestureOverlayView gestureoverlayview)
    {
        return gestureoverlayview.mFadingAlpha;
    }

    static long _2D_get2(GestureOverlayView gestureoverlayview)
    {
        return gestureoverlayview.mFadingStart;
    }

    static AccelerateDecelerateInterpolator _2D_get3(GestureOverlayView gestureoverlayview)
    {
        return gestureoverlayview.mInterpolator;
    }

    static boolean _2D_get4(GestureOverlayView gestureoverlayview)
    {
        return gestureoverlayview.mIsFadingOut;
    }

    static Path _2D_get5(GestureOverlayView gestureoverlayview)
    {
        return gestureoverlayview.mPath;
    }

    static Gesture _2D_set0(GestureOverlayView gestureoverlayview, Gesture gesture)
    {
        gestureoverlayview.mCurrentGesture = gesture;
        return gesture;
    }

    static float _2D_set1(GestureOverlayView gestureoverlayview, float f)
    {
        gestureoverlayview.mFadingAlpha = f;
        return f;
    }

    static boolean _2D_set2(GestureOverlayView gestureoverlayview, boolean flag)
    {
        gestureoverlayview.mFadingHasStarted = flag;
        return flag;
    }

    static boolean _2D_set3(GestureOverlayView gestureoverlayview, boolean flag)
    {
        gestureoverlayview.mIsFadingOut = flag;
        return flag;
    }

    static boolean _2D_set4(GestureOverlayView gestureoverlayview, boolean flag)
    {
        gestureoverlayview.mPreviousWasGesturing = flag;
        return flag;
    }

    static boolean _2D_set5(GestureOverlayView gestureoverlayview, boolean flag)
    {
        gestureoverlayview.mResetGesture = flag;
        return flag;
    }

    static void _2D_wrap0(GestureOverlayView gestureoverlayview)
    {
        gestureoverlayview.fireOnGesturePerformed();
    }

    static void _2D_wrap1(GestureOverlayView gestureoverlayview, int i)
    {
        gestureoverlayview.setPaintAlpha(i);
    }

    public GestureOverlayView(Context context)
    {
        super(context);
        mGesturePaint = new Paint();
        mFadeDuration = 150L;
        mFadeOffset = 420L;
        mFadeEnabled = true;
        mCertainGestureColor = -256;
        mUncertainGestureColor = 0x48ffff00;
        mGestureStrokeWidth = 12F;
        mInvalidateExtraBorder = 10;
        mGestureStrokeType = 0;
        mGestureStrokeLengthThreshold = 50F;
        mGestureStrokeSquarenessTreshold = 0.275F;
        mGestureStrokeAngleThreshold = 40F;
        mOrientation = 1;
        mInvalidRect = new Rect();
        mPath = new Path();
        mGestureVisible = true;
        mIsGesturing = false;
        mPreviousWasGesturing = false;
        mInterceptEvents = true;
        mStrokeBuffer = new ArrayList(100);
        mOnGestureListeners = new ArrayList();
        mOnGesturePerformedListeners = new ArrayList();
        mOnGesturingListeners = new ArrayList();
        mIsFadingOut = false;
        mFadingAlpha = 1.0F;
        mInterpolator = new AccelerateDecelerateInterpolator();
        mFadingOut = new FadeOutRunnable(null);
        init();
    }

    public GestureOverlayView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x111003e);
    }

    public GestureOverlayView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public GestureOverlayView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mGesturePaint = new Paint();
        mFadeDuration = 150L;
        mFadeOffset = 420L;
        mFadeEnabled = true;
        mCertainGestureColor = -256;
        mUncertainGestureColor = 0x48ffff00;
        mGestureStrokeWidth = 12F;
        mInvalidateExtraBorder = 10;
        mGestureStrokeType = 0;
        mGestureStrokeLengthThreshold = 50F;
        mGestureStrokeSquarenessTreshold = 0.275F;
        mGestureStrokeAngleThreshold = 40F;
        mOrientation = 1;
        mInvalidRect = new Rect();
        mPath = new Path();
        mGestureVisible = true;
        mIsGesturing = false;
        mPreviousWasGesturing = false;
        mInterceptEvents = true;
        mStrokeBuffer = new ArrayList(100);
        mOnGestureListeners = new ArrayList();
        mOnGesturePerformedListeners = new ArrayList();
        mOnGesturingListeners = new ArrayList();
        mIsFadingOut = false;
        mFadingAlpha = 1.0F;
        mInterpolator = new AccelerateDecelerateInterpolator();
        mFadingOut = new FadeOutRunnable(null);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.GestureOverlayView, i, j);
        mGestureStrokeWidth = context.getFloat(1, mGestureStrokeWidth);
        mInvalidateExtraBorder = Math.max(1, (int)mGestureStrokeWidth - 1);
        mCertainGestureColor = context.getColor(2, mCertainGestureColor);
        mUncertainGestureColor = context.getColor(3, mUncertainGestureColor);
        mFadeDuration = context.getInt(5, (int)mFadeDuration);
        mFadeOffset = context.getInt(4, (int)mFadeOffset);
        mGestureStrokeType = context.getInt(6, mGestureStrokeType);
        mGestureStrokeLengthThreshold = context.getFloat(7, mGestureStrokeLengthThreshold);
        mGestureStrokeAngleThreshold = context.getFloat(9, mGestureStrokeAngleThreshold);
        mGestureStrokeSquarenessTreshold = context.getFloat(8, mGestureStrokeSquarenessTreshold);
        mInterceptEvents = context.getBoolean(10, mInterceptEvents);
        mFadeEnabled = context.getBoolean(11, mFadeEnabled);
        mOrientation = context.getInt(0, mOrientation);
        context.recycle();
        init();
    }

    private void cancelGesture(MotionEvent motionevent)
    {
        ArrayList arraylist = mOnGestureListeners;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            ((OnGestureListener)arraylist.get(j)).onGestureCancelled(this, motionevent);

        clear(false);
    }

    private void clear(boolean flag, boolean flag1, boolean flag2)
    {
        setPaintAlpha(255);
        removeCallbacks(mFadingOut);
        mResetGesture = false;
        mFadingOut.fireActionPerformed = flag1;
        mFadingOut.resetMultipleStrokes = false;
        if(flag && mCurrentGesture != null)
        {
            mFadingAlpha = 1.0F;
            mIsFadingOut = true;
            mFadingHasStarted = false;
            mFadingStart = AnimationUtils.currentAnimationTimeMillis() + mFadeOffset;
            postDelayed(mFadingOut, mFadeOffset);
        } else
        {
            mFadingAlpha = 1.0F;
            mIsFadingOut = false;
            mFadingHasStarted = false;
            if(flag2)
            {
                mCurrentGesture = null;
                mPath.rewind();
                invalidate();
            } else
            if(flag1)
                postDelayed(mFadingOut, mFadeOffset);
            else
            if(mGestureStrokeType == 1)
            {
                mFadingOut.resetMultipleStrokes = true;
                postDelayed(mFadingOut, mFadeOffset);
            } else
            {
                mCurrentGesture = null;
                mPath.rewind();
                invalidate();
            }
        }
    }

    private void fireOnGesturePerformed()
    {
        ArrayList arraylist = mOnGesturePerformedListeners;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            ((OnGesturePerformedListener)arraylist.get(j)).onGesturePerformed(this, mCurrentGesture);

    }

    private void init()
    {
        setWillNotDraw(false);
        Paint paint = mGesturePaint;
        paint.setAntiAlias(true);
        paint.setColor(mCertainGestureColor);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        paint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        paint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        paint.setStrokeWidth(mGestureStrokeWidth);
        paint.setDither(true);
        mCurrentColor = mCertainGestureColor;
        setPaintAlpha(255);
    }

    private boolean processEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 38
    //                   1 73
    //                   2 49
    //                   3 92;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return false;
_L2:
        touchDown(motionevent);
        invalidate();
        return true;
_L4:
        if(mIsListeningForGestures)
        {
            motionevent = touchMove(motionevent);
            if(motionevent != null)
                invalidate(motionevent);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mIsListeningForGestures)
        {
            touchUp(motionevent, false);
            invalidate();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(mIsListeningForGestures)
        {
            touchUp(motionevent, true);
            invalidate();
            return true;
        }
        if(true) goto _L1; else goto _L6
_L6:
    }

    private void setCurrentColor(int i)
    {
        mCurrentColor = i;
        if(mFadingHasStarted)
            setPaintAlpha((int)(mFadingAlpha * 255F));
        else
            setPaintAlpha(255);
        invalidate();
    }

    private void setPaintAlpha(int i)
    {
        int j = mCurrentColor;
        mGesturePaint.setColor((mCurrentColor << 8) >>> 8 | ((j >>> 24) * (i + (i >> 7)) >> 8) << 24);
    }

    private void touchDown(MotionEvent motionevent)
    {
        float f;
        float f1;
        mIsListeningForGestures = true;
        f = motionevent.getX();
        f1 = motionevent.getY();
        mX = f;
        mY = f1;
        mTotalLength = 0.0F;
        mIsGesturing = false;
        if(mGestureStrokeType != 0 && !mResetGesture) goto _L2; else goto _L1
_L1:
        if(mHandleGestureActions)
            setCurrentColor(mUncertainGestureColor);
        mResetGesture = false;
        mCurrentGesture = null;
        mPath.rewind();
_L8:
        if(!mFadingHasStarted) goto _L4; else goto _L3
_L3:
        cancelClearAnimation();
_L6:
        if(mCurrentGesture == null)
            mCurrentGesture = new Gesture();
        mStrokeBuffer.add(new GesturePoint(f, f1, motionevent.getEventTime()));
        mPath.moveTo(f, f1);
        int i = mInvalidateExtraBorder;
        mInvalidRect.set((int)f - i, (int)f1 - i, (int)f + i, (int)f1 + i);
        mCurveEndX = f;
        mCurveEndY = f1;
        ArrayList arraylist = mOnGestureListeners;
        int k = arraylist.size();
        for(int j = 0; j < k; j++)
            ((OnGestureListener)arraylist.get(j)).onGestureStarted(this, motionevent);

        break; /* Loop/switch isn't completed */
_L2:
        if((mCurrentGesture == null || mCurrentGesture.getStrokesCount() == 0) && mHandleGestureActions)
            setCurrentColor(mUncertainGestureColor);
        continue; /* Loop/switch isn't completed */
_L4:
        if(mIsFadingOut)
        {
            setPaintAlpha(255);
            mIsFadingOut = false;
            mFadingHasStarted = false;
            removeCallbacks(mFadingOut);
        }
        if(true) goto _L6; else goto _L5
_L5:
        return;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private Rect touchMove(MotionEvent motionevent)
    {
        Object obj = null;
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        float f2 = mX;
        float f4 = mY;
        float f5 = Math.abs(f - f2);
        float f6 = Math.abs(f1 - f4);
        if(f5 >= 3F || f6 >= 3F)
        {
            Rect rect = mInvalidRect;
            int i = mInvalidateExtraBorder;
            rect.set((int)mCurveEndX - i, (int)mCurveEndY - i, (int)mCurveEndX + i, (int)mCurveEndY + i);
            float f8 = (f + f2) / 2.0F;
            mCurveEndX = f8;
            float f9 = (f1 + f4) / 2.0F;
            mCurveEndY = f9;
            mPath.quadTo(f2, f4, f8, f9);
            rect.union((int)f2 - i, (int)f4 - i, (int)f2 + i, (int)f4 + i);
            rect.union((int)f8 - i, (int)f9 - i, (int)f8 + i, (int)f9 + i);
            mX = f;
            mY = f1;
            mStrokeBuffer.add(new GesturePoint(f, f1, motionevent.getEventTime()));
            if(mHandleGestureActions && mIsGesturing ^ true)
            {
                mTotalLength = mTotalLength + (float)Math.hypot(f5, f6);
                if(mTotalLength > mGestureStrokeLengthThreshold)
                {
                    obj = GestureUtils.computeOrientedBoundingBox(mStrokeBuffer);
                    float f3 = Math.abs(((OrientedBoundingBox) (obj)).orientation);
                    float f7 = f3;
                    if(f3 > 90F)
                        f7 = 180F - f3;
                    if(((OrientedBoundingBox) (obj)).squareness > mGestureStrokeSquarenessTreshold || (mOrientation != 1 ? f7 > mGestureStrokeAngleThreshold : f7 < mGestureStrokeAngleThreshold))
                    {
                        mIsGesturing = true;
                        setCurrentColor(mCertainGestureColor);
                        obj = mOnGesturingListeners;
                        int j = ((ArrayList) (obj)).size();
                        for(i = 0; i < j; i++)
                            ((OnGesturingListener)((ArrayList) (obj)).get(i)).onGesturingStarted(this);

                    }
                }
            }
            ArrayList arraylist = mOnGestureListeners;
            int k = arraylist.size();
            i = 0;
            do
            {
                obj = rect;
                if(i >= k)
                    break;
                ((OnGestureListener)arraylist.get(i)).onGesture(this, motionevent);
                i++;
            } while(true);
        }
        return ((Rect) (obj));
    }

    private void touchUp(MotionEvent motionevent, boolean flag)
    {
        mIsListeningForGestures = false;
        if(mCurrentGesture != null)
        {
            mCurrentGesture.addStroke(new GestureStroke(mStrokeBuffer));
            if(!flag)
            {
                ArrayList arraylist = mOnGestureListeners;
                int i = arraylist.size();
                for(int j = 0; j < i; j++)
                    ((OnGestureListener)arraylist.get(j)).onGestureEnded(this, motionevent);

                int k;
                boolean flag1;
                if(mHandleGestureActions)
                    flag = mFadeEnabled;
                else
                    flag = false;
                if(mHandleGestureActions)
                    flag1 = mIsGesturing;
                else
                    flag1 = false;
                clear(flag, flag1, false);
            } else
            {
                cancelGesture(motionevent);
            }
        } else
        {
            cancelGesture(motionevent);
        }
        mStrokeBuffer.clear();
        mPreviousWasGesturing = mIsGesturing;
        mIsGesturing = false;
        motionevent = mOnGesturingListeners;
        i = motionevent.size();
        for(k = 0; k < i; k++)
            ((OnGesturingListener)motionevent.get(k)).onGesturingEnded(this);

    }

    public void addOnGestureListener(OnGestureListener ongesturelistener)
    {
        mOnGestureListeners.add(ongesturelistener);
    }

    public void addOnGesturePerformedListener(OnGesturePerformedListener ongestureperformedlistener)
    {
        mOnGesturePerformedListeners.add(ongestureperformedlistener);
        if(mOnGesturePerformedListeners.size() > 0)
            mHandleGestureActions = true;
    }

    public void addOnGesturingListener(OnGesturingListener ongesturinglistener)
    {
        mOnGesturingListeners.add(ongesturinglistener);
    }

    public void cancelClearAnimation()
    {
        setPaintAlpha(255);
        mIsFadingOut = false;
        mFadingHasStarted = false;
        removeCallbacks(mFadingOut);
        mPath.rewind();
        mCurrentGesture = null;
    }

    public void cancelGesture()
    {
        mIsListeningForGestures = false;
        mCurrentGesture.addStroke(new GestureStroke(mStrokeBuffer));
        long l = SystemClock.uptimeMillis();
        MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        ArrayList arraylist = mOnGestureListeners;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            ((OnGestureListener)arraylist.get(j)).onGestureCancelled(this, motionevent);

        motionevent.recycle();
        clear(false);
        mIsGesturing = false;
        mPreviousWasGesturing = false;
        mStrokeBuffer.clear();
        arraylist = mOnGesturingListeners;
        i = arraylist.size();
        for(int k = 0; k < i; k++)
            ((OnGesturingListener)arraylist.get(k)).onGesturingEnded(this);

    }

    public void clear(boolean flag)
    {
        clear(flag, false, true);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if(isEnabled())
        {
            boolean flag;
            if(mIsGesturing || mCurrentGesture != null && mCurrentGesture.getStrokesCount() > 0 && mPreviousWasGesturing)
                flag = mInterceptEvents;
            else
                flag = false;
            processEvent(motionevent);
            if(flag)
                motionevent.setAction(3);
            super.dispatchTouchEvent(motionevent);
            return true;
        } else
        {
            return super.dispatchTouchEvent(motionevent);
        }
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(mCurrentGesture != null && mGestureVisible)
            canvas.drawPath(mPath, mGesturePaint);
    }

    public ArrayList getCurrentStroke()
    {
        return mStrokeBuffer;
    }

    public long getFadeOffset()
    {
        return mFadeOffset;
    }

    public Gesture getGesture()
    {
        return mCurrentGesture;
    }

    public int getGestureColor()
    {
        return mCertainGestureColor;
    }

    public Paint getGesturePaint()
    {
        return mGesturePaint;
    }

    public Path getGesturePath()
    {
        return mPath;
    }

    public Path getGesturePath(Path path)
    {
        path.set(mPath);
        return path;
    }

    public float getGestureStrokeAngleThreshold()
    {
        return mGestureStrokeAngleThreshold;
    }

    public float getGestureStrokeLengthThreshold()
    {
        return mGestureStrokeLengthThreshold;
    }

    public float getGestureStrokeSquarenessTreshold()
    {
        return mGestureStrokeSquarenessTreshold;
    }

    public int getGestureStrokeType()
    {
        return mGestureStrokeType;
    }

    public float getGestureStrokeWidth()
    {
        return mGestureStrokeWidth;
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public int getUncertainGestureColor()
    {
        return mUncertainGestureColor;
    }

    public boolean isEventsInterceptionEnabled()
    {
        return mInterceptEvents;
    }

    public boolean isFadeEnabled()
    {
        return mFadeEnabled;
    }

    public boolean isGestureVisible()
    {
        return mGestureVisible;
    }

    public boolean isGesturing()
    {
        return mIsGesturing;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        cancelClearAnimation();
    }

    public void removeAllOnGestureListeners()
    {
        mOnGestureListeners.clear();
    }

    public void removeAllOnGesturePerformedListeners()
    {
        mOnGesturePerformedListeners.clear();
        mHandleGestureActions = false;
    }

    public void removeAllOnGesturingListeners()
    {
        mOnGesturingListeners.clear();
    }

    public void removeOnGestureListener(OnGestureListener ongesturelistener)
    {
        mOnGestureListeners.remove(ongesturelistener);
    }

    public void removeOnGesturePerformedListener(OnGesturePerformedListener ongestureperformedlistener)
    {
        mOnGesturePerformedListeners.remove(ongestureperformedlistener);
        if(mOnGesturePerformedListeners.size() <= 0)
            mHandleGestureActions = false;
    }

    public void removeOnGesturingListener(OnGesturingListener ongesturinglistener)
    {
        mOnGesturingListeners.remove(ongesturinglistener);
    }

    public void setEventsInterceptionEnabled(boolean flag)
    {
        mInterceptEvents = flag;
    }

    public void setFadeEnabled(boolean flag)
    {
        mFadeEnabled = flag;
    }

    public void setFadeOffset(long l)
    {
        mFadeOffset = l;
    }

    public void setGesture(Gesture gesture)
    {
        if(mCurrentGesture != null)
            clear(false);
        setCurrentColor(mCertainGestureColor);
        mCurrentGesture = gesture;
        gesture = mCurrentGesture.toPath();
        RectF rectf = new RectF();
        gesture.computeBounds(rectf, true);
        mPath.rewind();
        mPath.addPath(gesture, -rectf.left + ((float)getWidth() - rectf.width()) / 2.0F, -rectf.top + ((float)getHeight() - rectf.height()) / 2.0F);
        mResetGesture = true;
        invalidate();
    }

    public void setGestureColor(int i)
    {
        mCertainGestureColor = i;
    }

    public void setGestureStrokeAngleThreshold(float f)
    {
        mGestureStrokeAngleThreshold = f;
    }

    public void setGestureStrokeLengthThreshold(float f)
    {
        mGestureStrokeLengthThreshold = f;
    }

    public void setGestureStrokeSquarenessTreshold(float f)
    {
        mGestureStrokeSquarenessTreshold = f;
    }

    public void setGestureStrokeType(int i)
    {
        mGestureStrokeType = i;
    }

    public void setGestureStrokeWidth(float f)
    {
        mGestureStrokeWidth = f;
        mInvalidateExtraBorder = Math.max(1, (int)f - 1);
        mGesturePaint.setStrokeWidth(f);
    }

    public void setGestureVisible(boolean flag)
    {
        mGestureVisible = flag;
    }

    public void setOrientation(int i)
    {
        mOrientation = i;
    }

    public void setUncertainGestureColor(int i)
    {
        mUncertainGestureColor = i;
    }

    private static final boolean DITHER_FLAG = true;
    private static final int FADE_ANIMATION_RATE = 16;
    private static final boolean GESTURE_RENDERING_ANTIALIAS = true;
    public static final int GESTURE_STROKE_TYPE_MULTIPLE = 1;
    public static final int GESTURE_STROKE_TYPE_SINGLE = 0;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    private int mCertainGestureColor;
    private int mCurrentColor;
    private Gesture mCurrentGesture;
    private float mCurveEndX;
    private float mCurveEndY;
    private long mFadeDuration;
    private boolean mFadeEnabled;
    private long mFadeOffset;
    private float mFadingAlpha;
    private boolean mFadingHasStarted;
    private final FadeOutRunnable mFadingOut;
    private long mFadingStart;
    private final Paint mGesturePaint;
    private float mGestureStrokeAngleThreshold;
    private float mGestureStrokeLengthThreshold;
    private float mGestureStrokeSquarenessTreshold;
    private int mGestureStrokeType;
    private float mGestureStrokeWidth;
    private boolean mGestureVisible;
    private boolean mHandleGestureActions;
    private boolean mInterceptEvents;
    private final AccelerateDecelerateInterpolator mInterpolator;
    private final Rect mInvalidRect;
    private int mInvalidateExtraBorder;
    private boolean mIsFadingOut;
    private boolean mIsGesturing;
    private boolean mIsListeningForGestures;
    private final ArrayList mOnGestureListeners;
    private final ArrayList mOnGesturePerformedListeners;
    private final ArrayList mOnGesturingListeners;
    private int mOrientation;
    private final Path mPath;
    private boolean mPreviousWasGesturing;
    private boolean mResetGesture;
    private final ArrayList mStrokeBuffer;
    private float mTotalLength;
    private int mUncertainGestureColor;
    private float mX;
    private float mY;
}
