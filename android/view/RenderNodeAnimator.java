// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.*;
import android.graphics.Canvas;
import android.graphics.CanvasProperty;
import android.util.SparseIntArray;
import com.android.internal.util.VirtualRefBasePtr;
import com.android.internal.view.animation.*;
import java.util.ArrayList;

// Referenced classes of package android.view:
//            View, RenderNode, DisplayListCanvas, Choreographer

public class RenderNodeAnimator extends Animator
{
    private static class DelayedAnimationHelper
        implements Runnable
    {

        private void scheduleCallback()
        {
            if(!mCallbackScheduled)
            {
                mCallbackScheduled = true;
                mChoreographer.postCallback(1, this, null);
            }
        }

        public void addDelayedAnimation(RenderNodeAnimator rendernodeanimator)
        {
            mDelayedAnims.add(rendernodeanimator);
            scheduleCallback();
        }

        public void removeDelayedAnimation(RenderNodeAnimator rendernodeanimator)
        {
            mDelayedAnims.remove(rendernodeanimator);
        }

        public void run()
        {
            long l = mChoreographer.getFrameTime();
            mCallbackScheduled = false;
            int i = 0;
            for(int j = 0; j < mDelayedAnims.size();)
            {
                RenderNodeAnimator rendernodeanimator = (RenderNodeAnimator)mDelayedAnims.get(j);
                int k = i;
                if(!RenderNodeAnimator._2D_wrap0(rendernodeanimator, l))
                {
                    if(i != j)
                        mDelayedAnims.set(i, rendernodeanimator);
                    k = i + 1;
                }
                j++;
                i = k;
            }

            for(; mDelayedAnims.size() > i; mDelayedAnims.remove(mDelayedAnims.size() - 1));
            if(mDelayedAnims.size() > 0)
                scheduleCallback();
        }

        private boolean mCallbackScheduled;
        private final Choreographer mChoreographer = Choreographer.getInstance();
        private ArrayList mDelayedAnims;

        public DelayedAnimationHelper()
        {
            mDelayedAnims = new ArrayList();
        }
    }


    static boolean _2D_wrap0(RenderNodeAnimator rendernodeanimator, long l)
    {
        return rendernodeanimator.processDelayed(l);
    }

    public RenderNodeAnimator(int i, float f)
    {
        mRenderProperty = -1;
        mState = 0;
        mUnscaledDuration = 300L;
        mUnscaledStartDelay = 0L;
        mStartDelay = 0L;
        mRenderProperty = i;
        mFinalValue = f;
        mUiThreadHandlesDelay = true;
        init(nCreateAnimator(i, f));
    }

    public RenderNodeAnimator(int i, int j, float f, float f1)
    {
        mRenderProperty = -1;
        mState = 0;
        mUnscaledDuration = 300L;
        mUnscaledStartDelay = 0L;
        mStartDelay = 0L;
        init(nCreateRevealAnimator(i, j, f, f1));
        mUiThreadHandlesDelay = true;
    }

    public RenderNodeAnimator(CanvasProperty canvasproperty, float f)
    {
        mRenderProperty = -1;
        mState = 0;
        mUnscaledDuration = 300L;
        mUnscaledStartDelay = 0L;
        mStartDelay = 0L;
        init(nCreateCanvasPropertyFloatAnimator(canvasproperty.getNativeContainer(), f));
        mUiThreadHandlesDelay = false;
    }

    public RenderNodeAnimator(CanvasProperty canvasproperty, int i, float f)
    {
        mRenderProperty = -1;
        mState = 0;
        mUnscaledDuration = 300L;
        mUnscaledStartDelay = 0L;
        mStartDelay = 0L;
        init(nCreateCanvasPropertyPaintAnimator(canvasproperty.getNativeContainer(), i, f));
        mUiThreadHandlesDelay = false;
    }

    private void applyInterpolator()
    {
        if(mInterpolator == null)
            return;
        long l;
        if(isNativeInterpolator(mInterpolator))
        {
            l = ((NativeInterpolatorFactory)mInterpolator).createNativeInterpolator();
        } else
        {
            l = nGetDuration(mNativePtr.get());
            l = FallbackLUTInterpolator.createNativeInterpolator(mInterpolator, l);
        }
        nSetInterpolator(mNativePtr.get(), l);
    }

    private static void callOnFinished(RenderNodeAnimator rendernodeanimator)
    {
        rendernodeanimator.onFinished();
    }

    private void checkMutable()
    {
        if(mState != 0)
            throw new IllegalStateException("Animator has already started, cannot change it now!");
        if(mNativePtr == null)
            throw new IllegalStateException("Animator's target has been destroyed (trying to modify an animation after activity destroy?)");
        else
            return;
    }

    private ArrayList cloneListeners()
    {
        ArrayList arraylist = getListeners();
        ArrayList arraylist1 = arraylist;
        if(arraylist != null)
            arraylist1 = (ArrayList)arraylist.clone();
        return arraylist1;
    }

    private void doStart()
    {
        if(mRenderProperty == 11)
        {
            mViewTarget.ensureTransformationInfo();
            mViewTarget.mTransformationInfo.mAlpha = mFinalValue;
        }
        moveToRunningState();
        if(mViewTarget != null)
            mViewTarget.invalidateViewProperty(true, false);
    }

    private static DelayedAnimationHelper getHelper()
    {
        DelayedAnimationHelper delayedanimationhelper = (DelayedAnimationHelper)sAnimationHelper.get();
        DelayedAnimationHelper delayedanimationhelper1 = delayedanimationhelper;
        if(delayedanimationhelper == null)
        {
            delayedanimationhelper1 = new DelayedAnimationHelper();
            sAnimationHelper.set(delayedanimationhelper1);
        }
        return delayedanimationhelper1;
    }

    private void init(long l)
    {
        mNativePtr = new VirtualRefBasePtr(l);
    }

    static boolean isNativeInterpolator(TimeInterpolator timeinterpolator)
    {
        return timeinterpolator.getClass().isAnnotationPresent(com/android/internal/view/animation/HasNativeInterpolator);
    }

    public static int mapViewPropertyToRenderProperty(int i)
    {
        return sViewPropertyAnimatorMap.get(i);
    }

    private void moveToRunningState()
    {
        mState = 2;
        if(mNativePtr != null)
            nStart(mNativePtr.get());
        notifyStartListeners();
    }

    private static native long nCreateAnimator(int i, float f);

    private static native long nCreateCanvasPropertyFloatAnimator(long l, float f);

    private static native long nCreateCanvasPropertyPaintAnimator(long l, int i, float f);

    private static native long nCreateRevealAnimator(int i, int j, float f, float f1);

    private static native void nEnd(long l);

    private static native long nGetDuration(long l);

    private static native void nSetAllowRunningAsync(long l, boolean flag);

    private static native void nSetDuration(long l, long l1);

    private static native void nSetInterpolator(long l, long l1);

    private static native void nSetListener(long l, RenderNodeAnimator rendernodeanimator);

    private static native void nSetStartDelay(long l, long l1);

    private static native void nSetStartValue(long l, float f);

    private static native void nStart(long l);

    private void notifyStartListeners()
    {
        ArrayList arraylist = cloneListeners();
        int i;
        int j;
        if(arraylist == null)
            i = 0;
        else
            i = arraylist.size();
        for(j = 0; j < i; j++)
            ((android.animation.Animator.AnimatorListener)arraylist.get(j)).onAnimationStart(this);

    }

    private boolean processDelayed(long l)
    {
        if(mStartTime == 0L)
            mStartTime = l;
        else
        if(l - mStartTime >= mStartDelay)
        {
            doStart();
            return true;
        }
        return false;
    }

    private void releaseNativePtr()
    {
        if(mNativePtr != null)
        {
            mNativePtr.release();
            mNativePtr = null;
        }
    }

    private void setTarget(RenderNode rendernode)
    {
        checkMutable();
        if(mTarget != null)
        {
            throw new IllegalStateException("Target already set!");
        } else
        {
            nSetListener(mNativePtr.get(), this);
            mTarget = rendernode;
            mTarget.addAnimator(this);
            return;
        }
    }

    public void cancel()
    {
        if(mState != 0 && mState != 3)
        {
            if(mState == 1)
            {
                getHelper().removeDelayedAnimation(this);
                moveToRunningState();
            }
            ArrayList arraylist = cloneListeners();
            int i;
            int j;
            if(arraylist == null)
                i = 0;
            else
                i = arraylist.size();
            for(j = 0; j < i; j++)
                ((android.animation.Animator.AnimatorListener)arraylist.get(j)).onAnimationCancel(this);

            end();
        }
    }

    public Animator clone()
    {
        throw new IllegalStateException("Cannot clone this animator");
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void end()
    {
        if(mState != 3)
        {
            if(mState < 2)
            {
                getHelper().removeDelayedAnimation(this);
                doStart();
            }
            if(mNativePtr != null)
            {
                nEnd(mNativePtr.get());
                if(mViewTarget != null)
                    mViewTarget.invalidateViewProperty(true, false);
            } else
            {
                onFinished();
            }
        }
    }

    public long getDuration()
    {
        return mUnscaledDuration;
    }

    public TimeInterpolator getInterpolator()
    {
        return mInterpolator;
    }

    long getNativeAnimator()
    {
        return mNativePtr.get();
    }

    public long getStartDelay()
    {
        return mUnscaledStartDelay;
    }

    public long getTotalDuration()
    {
        return mUnscaledDuration + mUnscaledStartDelay;
    }

    public boolean isRunning()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mState != 1)
            if(mState == 2)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isStarted()
    {
        boolean flag = false;
        if(mState != 0)
            flag = true;
        return flag;
    }

    protected void onFinished()
    {
        if(mState == 0)
        {
            releaseNativePtr();
            return;
        }
        if(mState == 1)
        {
            getHelper().removeDelayedAnimation(this);
            notifyStartListeners();
        }
        mState = 3;
        ArrayList arraylist = cloneListeners();
        int i;
        int j;
        if(arraylist == null)
            i = 0;
        else
            i = arraylist.size();
        for(j = 0; j < i; j++)
            ((android.animation.Animator.AnimatorListener)arraylist.get(j)).onAnimationEnd(this);

        releaseNativePtr();
    }

    public void pause()
    {
        throw new UnsupportedOperationException();
    }

    public void resume()
    {
        throw new UnsupportedOperationException();
    }

    public void setAllowRunningAsynchronously(boolean flag)
    {
        checkMutable();
        nSetAllowRunningAsync(mNativePtr.get(), flag);
    }

    public volatile Animator setDuration(long l)
    {
        return setDuration(l);
    }

    public RenderNodeAnimator setDuration(long l)
    {
        checkMutable();
        if(l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("duration must be positive; ").append(l).toString());
        } else
        {
            mUnscaledDuration = l;
            nSetDuration(mNativePtr.get(), (long)((float)l * ValueAnimator.getDurationScale()));
            return this;
        }
    }

    public void setInterpolator(TimeInterpolator timeinterpolator)
    {
        checkMutable();
        mInterpolator = timeinterpolator;
    }

    public void setStartDelay(long l)
    {
        checkMutable();
        if(l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("startDelay must be positive; ").append(l).toString());
        } else
        {
            mUnscaledStartDelay = l;
            mStartDelay = (long)(ValueAnimator.getDurationScale() * (float)l);
            return;
        }
    }

    public void setStartValue(float f)
    {
        checkMutable();
        nSetStartValue(mNativePtr.get(), f);
    }

    public void setTarget(Canvas canvas)
    {
        if(!(canvas instanceof DisplayListCanvas))
        {
            throw new IllegalArgumentException("Not a GLES20RecordingCanvas");
        } else
        {
            setTarget(((DisplayListCanvas)canvas).mNode);
            return;
        }
    }

    public void setTarget(View view)
    {
        mViewTarget = view;
        setTarget(mViewTarget.mRenderNode);
    }

    public void start()
    {
        if(mTarget == null)
            throw new IllegalStateException("Missing target!");
        if(mState != 0)
            throw new IllegalStateException("Already started!");
        mState = 1;
        applyInterpolator();
        if(mNativePtr == null)
            cancel();
        else
        if(mStartDelay <= 0L || mUiThreadHandlesDelay ^ true)
        {
            nSetStartDelay(mNativePtr.get(), mStartDelay);
            doStart();
        } else
        {
            getHelper().addDelayedAnimation(this);
        }
    }

    public static final int ALPHA = 11;
    public static final int LAST_VALUE = 11;
    public static final int PAINT_ALPHA = 1;
    public static final int PAINT_STROKE_WIDTH = 0;
    public static final int ROTATION = 5;
    public static final int ROTATION_X = 6;
    public static final int ROTATION_Y = 7;
    public static final int SCALE_X = 3;
    public static final int SCALE_Y = 4;
    private static final int STATE_DELAYED = 1;
    private static final int STATE_FINISHED = 3;
    private static final int STATE_PREPARE = 0;
    private static final int STATE_RUNNING = 2;
    public static final int TRANSLATION_X = 0;
    public static final int TRANSLATION_Y = 1;
    public static final int TRANSLATION_Z = 2;
    public static final int X = 8;
    public static final int Y = 9;
    public static final int Z = 10;
    private static ThreadLocal sAnimationHelper = new ThreadLocal();
    private static final SparseIntArray sViewPropertyAnimatorMap = new SparseIntArray(15) {

            
            {
                put(1, 0);
                put(2, 1);
                put(4, 2);
                put(8, 3);
                put(16, 4);
                put(32, 5);
                put(64, 6);
                put(128, 7);
                put(256, 8);
                put(512, 9);
                put(1024, 10);
                put(2048, 11);
            }
    }
;
    private float mFinalValue;
    private TimeInterpolator mInterpolator;
    private VirtualRefBasePtr mNativePtr;
    private int mRenderProperty;
    private long mStartDelay;
    private long mStartTime;
    private int mState;
    private RenderNode mTarget;
    private final boolean mUiThreadHandlesDelay;
    private long mUnscaledDuration;
    private long mUnscaledStartDelay;
    private View mViewTarget;

}
