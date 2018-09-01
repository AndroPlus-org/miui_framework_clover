// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.os.Looper;
import android.os.Trace;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.animation.*;
import java.util.*;

// Referenced classes of package android.animation:
//            Animator, AnimationHandler, ArgbEvaluator, TimeInterpolator, 
//            PropertyValuesHolder, TypeEvaluator

public class ValueAnimator extends Animator
    implements AnimationHandler.AnimationFrameCallback
{
    public static interface AnimatorUpdateListener
    {

        public abstract void onAnimationUpdate(ValueAnimator valueanimator);
    }


    public ValueAnimator()
    {
        mStartTime = -1L;
        mSeekFraction = -1F;
        mResumed = false;
        mOverallFraction = 0.0F;
        mCurrentFraction = 0.0F;
        mLastFrameTime = -1L;
        mFirstFrameTime = -1L;
        mRunning = false;
        mStarted = false;
        mStartListenersCalled = false;
        mInitialized = false;
        mAnimationEndRequested = false;
        mDuration = 300L;
        mStartDelay = 0L;
        mRepeatCount = 0;
        mRepeatMode = 1;
        mSelfPulse = true;
        mSuppressSelfPulseRequested = false;
        mInterpolator = sDefaultInterpolator;
        mUpdateListeners = null;
    }

    private void addAnimationCallback(long l)
    {
        if(!mSelfPulse)
        {
            return;
        } else
        {
            getAnimationHandler().addAnimationFrameCallback(this, l);
            return;
        }
    }

    private void addOneShotCommitCallback()
    {
        if(!mSelfPulse)
        {
            return;
        } else
        {
            getAnimationHandler().addOneShotCommitCallback(this);
            return;
        }
    }

    public static boolean areAnimatorsEnabled()
    {
        boolean flag;
        if(sDurationScale == 0.0F)
            flag = true;
        else
            flag = false;
        return flag ^ true;
    }

    private float clampFraction(float f)
    {
        if(f >= 0.0F) goto _L2; else goto _L1
_L1:
        float f1 = 0.0F;
_L4:
        return f1;
_L2:
        f1 = f;
        if(mRepeatCount != -1)
            f1 = Math.min(f, mRepeatCount + 1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void endAnimation()
    {
        if(mAnimationEndRequested)
            return;
        removeAnimationCallback();
        mAnimationEndRequested = true;
        mPaused = false;
        int i;
        if((mStarted || mRunning) && mListeners != null)
            i = 1;
        else
            i = 0;
        if(i != 0 && mRunning ^ true)
            notifyStartListeners();
        mRunning = false;
        mStarted = false;
        mStartListenersCalled = false;
        mLastFrameTime = -1L;
        mFirstFrameTime = -1L;
        mStartTime = -1L;
        if(i != 0 && mListeners != null)
        {
            ArrayList arraylist = (ArrayList)mListeners.clone();
            int j = arraylist.size();
            for(i = 0; i < j; i++)
                ((Animator.AnimatorListener)arraylist.get(i)).onAnimationEnd(this, mReversing);

        }
        mReversing = false;
        if(Trace.isTagEnabled(8L))
            Trace.asyncTraceEnd(8L, getNameForTrace(), System.identityHashCode(this));
    }

    public static int getCurrentAnimationsCount()
    {
        return AnimationHandler.getAnimationCount();
    }

    private int getCurrentIteration(float f)
    {
        f = clampFraction(f);
        double d = Math.floor(f);
        double d1 = d;
        if((double)f == d)
        {
            d1 = d;
            if(f > 0.0F)
                d1 = d - 1.0D;
        }
        return (int)d1;
    }

    private float getCurrentIterationFraction(float f, boolean flag)
    {
        f = clampFraction(f);
        int i = getCurrentIteration(f);
        float f1 = f - (float)i;
        f = f1;
        if(shouldPlayBackward(i, flag))
            f = 1.0F - f1;
        return f;
    }

    public static float getDurationScale()
    {
        return sDurationScale;
    }

    public static long getFrameDelay()
    {
        AnimationHandler.getInstance();
        return AnimationHandler.getFrameDelay();
    }

    private long getScaledDuration()
    {
        return (long)((float)mDuration * sDurationScale);
    }

    private boolean isPulsingInternal()
    {
        boolean flag;
        if(mLastFrameTime >= 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void notifyStartListeners()
    {
        if(mListeners != null && mStartListenersCalled ^ true)
        {
            ArrayList arraylist = (ArrayList)mListeners.clone();
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((Animator.AnimatorListener)arraylist.get(j)).onAnimationStart(this, mReversing);

        }
        mStartListenersCalled = true;
    }

    public static transient ValueAnimator ofArgb(int ai[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setIntValues(ai);
        valueanimator.setEvaluator(ArgbEvaluator.getInstance());
        return valueanimator;
    }

    public static transient ValueAnimator ofFloat(float af[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setFloatValues(af);
        return valueanimator;
    }

    public static transient ValueAnimator ofInt(int ai[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setIntValues(ai);
        return valueanimator;
    }

    public static transient ValueAnimator ofObject(TypeEvaluator typeevaluator, Object aobj[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setObjectValues(aobj);
        valueanimator.setEvaluator(typeevaluator);
        return valueanimator;
    }

    public static transient ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder apropertyvaluesholder[])
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setValues(apropertyvaluesholder);
        return valueanimator;
    }

    private void removeAnimationCallback()
    {
        if(!mSelfPulse)
        {
            return;
        } else
        {
            getAnimationHandler().removeCallback(this);
            return;
        }
    }

    public static void setDurationScale(float f)
    {
        sDurationScale = f;
    }

    public static void setFrameDelay(long l)
    {
        AnimationHandler.getInstance();
        AnimationHandler.setFrameDelay(l);
    }

    private boolean shouldPlayBackward(int i, boolean flag)
    {
        boolean flag1 = true;
        boolean flag2 = true;
        if(i > 0 && mRepeatMode == 2 && (i < mRepeatCount + 1 || mRepeatCount == -1))
        {
            if(flag)
            {
                if(i % 2 == 0)
                    flag = flag2;
                else
                    flag = false;
                return flag;
            }
            if(i % 2 != 0)
                flag = flag1;
            else
                flag = false;
            return flag;
        } else
        {
            return flag;
        }
    }

    private void start(boolean flag)
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        mReversing = flag;
        mSelfPulse = mSuppressSelfPulseRequested ^ true;
        if(flag && mSeekFraction != -1F && mSeekFraction != 0.0F)
            if(mRepeatCount == -1)
                mSeekFraction = 1.0F - (float)((double)mSeekFraction - Math.floor(mSeekFraction));
            else
                mSeekFraction = (float)(mRepeatCount + 1) - mSeekFraction;
        mStarted = true;
        mPaused = false;
        mRunning = false;
        mAnimationEndRequested = false;
        mLastFrameTime = -1L;
        mFirstFrameTime = -1L;
        mStartTime = -1L;
        addAnimationCallback(0L);
        break MISSING_BLOCK_LABEL_130;
        if(mStartDelay == 0L || mSeekFraction >= 0.0F || mReversing)
        {
            startAnimation();
            if(mSeekFraction == -1F)
                setCurrentPlayTime(0L);
            else
                setCurrentFraction(mSeekFraction);
        }
        return;
    }

    private void startAnimation()
    {
        if(Trace.isTagEnabled(8L))
            Trace.asyncTraceBegin(8L, getNameForTrace(), System.identityHashCode(this));
        mAnimationEndRequested = false;
        initAnimation();
        mRunning = true;
        if(mSeekFraction >= 0.0F)
            mOverallFraction = mSeekFraction;
        else
            mOverallFraction = 0.0F;
        if(mListeners != null)
            notifyStartListeners();
    }

    public void addUpdateListener(AnimatorUpdateListener animatorupdatelistener)
    {
        if(mUpdateListeners == null)
            mUpdateListeners = new ArrayList();
        mUpdateListeners.add(animatorupdatelistener);
    }

    void animateBasedOnPlayTime(long l, long l1, boolean flag)
    {
        if(l < 0L || l1 < 0L)
            throw new UnsupportedOperationException("Error: Play time should never be negative.");
        initAnimation();
        if(mRepeatCount > 0)
        {
            int i = (int)(l / mDuration);
            int k = (int)(l1 / mDuration);
            if(Math.min(i, mRepeatCount) != Math.min(k, mRepeatCount) && mListeners != null)
            {
                int i1 = mListeners.size();
                for(int j = 0; j < i1; j++)
                    ((Animator.AnimatorListener)mListeners.get(j)).onAnimationRepeat(this);

            }
        }
        if(mRepeatCount != -1 && l >= (long)(mRepeatCount + 1) * mDuration)
            skipToEndValue(flag);
        else
            animateValue(getCurrentIterationFraction((float)l / (float)mDuration, flag));
    }

    boolean animateBasedOnTime(long l)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        if(!mRunning) goto _L2; else goto _L1
_L1:
        boolean flag2;
        int j;
        long l1 = getScaledDuration();
        float f;
        float f1;
        if(l1 > 0L)
            f = (float)(l - mStartTime) / (float)l1;
        else
            f = 1.0F;
        f1 = mOverallFraction;
        if((int)f > (int)f1)
            flag2 = true;
        else
            flag2 = false;
        if(f >= (float)(mRepeatCount + 1))
        {
            if(mRepeatCount != -1)
                j = 1;
            else
                j = 0;
        } else
        {
            j = 0;
        }
        if(l1 != 0L) goto _L4; else goto _L3
_L3:
        flag = true;
_L6:
        mOverallFraction = clampFraction(f);
        animateValue(getCurrentIterationFraction(mOverallFraction, mReversing));
_L2:
        return flag;
_L4:
        if(flag2 && (j ^ 1) != 0)
        {
            flag = flag1;
            if(mListeners == null)
                continue; /* Loop/switch isn't completed */
            int i = mListeners.size();
            j = 0;
            do
            {
                flag = flag1;
                if(j >= i)
                    continue; /* Loop/switch isn't completed */
                ((Animator.AnimatorListener)mListeners.get(j)).onAnimationRepeat(this);
                j++;
            } while(true);
        }
        flag = flag1;
        if(j != 0)
            flag = true;
        if(true) goto _L6; else goto _L5
_L5:
    }

    void animateValue(float f)
    {
        f = mInterpolator.getInterpolation(f);
        mCurrentFraction = f;
        int i = mValues.length;
        for(int k = 0; k < i; k++)
            mValues[k].calculateValue(f);

        if(mUpdateListeners != null)
        {
            int j = mUpdateListeners.size();
            for(int l = 0; l < j; l++)
                ((AnimatorUpdateListener)mUpdateListeners.get(l)).onAnimationUpdate(this);

        }
    }

    public boolean canReverse()
    {
        return true;
    }

    public void cancel()
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        if(mAnimationEndRequested)
            return;
        if((mStarted || mRunning) && mListeners != null)
        {
            if(!mRunning)
                notifyStartListeners();
            for(Iterator iterator = ((ArrayList)mListeners.clone()).iterator(); iterator.hasNext(); ((Animator.AnimatorListener)iterator.next()).onAnimationCancel(this));
        }
        endAnimation();
    }

    public volatile Animator clone()
    {
        return clone();
    }

    public ValueAnimator clone()
    {
        ValueAnimator valueanimator = (ValueAnimator)super.clone();
        if(mUpdateListeners != null)
            valueanimator.mUpdateListeners = new ArrayList(mUpdateListeners);
        valueanimator.mSeekFraction = -1F;
        valueanimator.mReversing = false;
        valueanimator.mInitialized = false;
        valueanimator.mStarted = false;
        valueanimator.mRunning = false;
        valueanimator.mPaused = false;
        valueanimator.mResumed = false;
        valueanimator.mStartListenersCalled = false;
        valueanimator.mStartTime = -1L;
        valueanimator.mStartTimeCommitted = false;
        valueanimator.mAnimationEndRequested = false;
        valueanimator.mPauseTime = -1L;
        valueanimator.mLastFrameTime = -1L;
        valueanimator.mFirstFrameTime = -1L;
        valueanimator.mOverallFraction = 0.0F;
        valueanimator.mCurrentFraction = 0.0F;
        valueanimator.mSelfPulse = true;
        valueanimator.mSuppressSelfPulseRequested = false;
        PropertyValuesHolder apropertyvaluesholder[] = mValues;
        if(apropertyvaluesholder != null)
        {
            int i = apropertyvaluesholder.length;
            valueanimator.mValues = new PropertyValuesHolder[i];
            valueanimator.mValuesMap = new HashMap(i);
            for(int j = 0; j < i; j++)
            {
                PropertyValuesHolder propertyvaluesholder = apropertyvaluesholder[j].clone();
                valueanimator.mValues[j] = propertyvaluesholder;
                valueanimator.mValuesMap.put(propertyvaluesholder.getPropertyName(), propertyvaluesholder);
            }

        }
        return valueanimator;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void commitAnimationFrame(long l)
    {
        if(!mStartTimeCommitted)
        {
            mStartTimeCommitted = true;
            l -= mLastFrameTime;
            if(l > 0L)
                mStartTime = mStartTime + l;
        }
    }

    public final boolean doAnimationFrame(long l)
    {
        if(mStartTime < 0L)
        {
            long l1;
            if(mReversing)
                l1 = l;
            else
                l1 = (long)((float)mStartDelay * sDurationScale) + l;
            mStartTime = l1;
        }
        if(mPaused)
        {
            mPauseTime = l;
            removeAnimationCallback();
            return false;
        }
        if(mResumed)
        {
            mResumed = false;
            if(mPauseTime > 0L)
                mStartTime = mStartTime + (l - mPauseTime);
        }
        if(!mRunning)
        {
            if(mStartTime > l && mSeekFraction == -1F)
                return false;
            mRunning = true;
            startAnimation();
        }
        if(mLastFrameTime < 0L)
        {
            if(mSeekFraction >= 0.0F)
            {
                mStartTime = l - (long)((float)getScaledDuration() * mSeekFraction);
                mSeekFraction = -1F;
            }
            mStartTimeCommitted = false;
        }
        mLastFrameTime = l;
        boolean flag = animateBasedOnTime(Math.max(l, mStartTime));
        if(flag)
            endAnimation();
        return flag;
    }

    public void end()
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        float f;
        if(!mRunning)
        {
            startAnimation();
            mStarted = true;
        } else
        if(!mInitialized)
            initAnimation();
        if(shouldPlayBackward(mRepeatCount, mReversing))
            f = 0.0F;
        else
            f = 1.0F;
        animateValue(f);
        endAnimation();
    }

    public float getAnimatedFraction()
    {
        return mCurrentFraction;
    }

    public Object getAnimatedValue()
    {
        if(mValues != null && mValues.length > 0)
            return mValues[0].getAnimatedValue();
        else
            return null;
    }

    public Object getAnimatedValue(String s)
    {
        s = (PropertyValuesHolder)mValuesMap.get(s);
        if(s != null)
            return s.getAnimatedValue();
        else
            return null;
    }

    public AnimationHandler getAnimationHandler()
    {
        return AnimationHandler.getInstance();
    }

    public long getCurrentPlayTime()
    {
        if(!mInitialized || !mStarted && mSeekFraction < 0.0F)
            return 0L;
        if(mSeekFraction >= 0.0F)
            return (long)((float)mDuration * mSeekFraction);
        float f;
        if(sDurationScale == 0.0F)
            f = 1.0F;
        else
            f = sDurationScale;
        return (long)((float)(AnimationUtils.currentAnimationTimeMillis() - mStartTime) / f);
    }

    public long getDuration()
    {
        return mDuration;
    }

    public TimeInterpolator getInterpolator()
    {
        return mInterpolator;
    }

    String getNameForTrace()
    {
        return "animator";
    }

    public int getRepeatCount()
    {
        return mRepeatCount;
    }

    public int getRepeatMode()
    {
        return mRepeatMode;
    }

    public long getStartDelay()
    {
        return mStartDelay;
    }

    public long getTotalDuration()
    {
        if(mRepeatCount == -1)
            return -1L;
        else
            return mStartDelay + mDuration * (long)(mRepeatCount + 1);
    }

    public PropertyValuesHolder[] getValues()
    {
        return mValues;
    }

    void initAnimation()
    {
        if(!mInitialized)
        {
            int i = mValues.length;
            for(int j = 0; j < i; j++)
                mValues[j].init();

            mInitialized = true;
        }
    }

    boolean isInitialized()
    {
        return mInitialized;
    }

    public boolean isRunning()
    {
        return mRunning;
    }

    public boolean isStarted()
    {
        return mStarted;
    }

    public void pause()
    {
        boolean flag = mPaused;
        super.pause();
        if(!flag && mPaused)
        {
            mPauseTime = -1L;
            mResumed = false;
        }
    }

    boolean pulseAnimationFrame(long l)
    {
        if(mSelfPulse)
            return false;
        else
            return doAnimationFrame(l);
    }

    public void removeAllUpdateListeners()
    {
        if(mUpdateListeners == null)
        {
            return;
        } else
        {
            mUpdateListeners.clear();
            mUpdateListeners = null;
            return;
        }
    }

    public void removeUpdateListener(AnimatorUpdateListener animatorupdatelistener)
    {
        if(mUpdateListeners == null)
            return;
        mUpdateListeners.remove(animatorupdatelistener);
        if(mUpdateListeners.size() == 0)
            mUpdateListeners = null;
    }

    public void resume()
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be resumed from the same thread that the animator was started on");
        if(mPaused && mResumed ^ true)
        {
            mResumed = true;
            if(mPauseTime > 0L)
                addAnimationCallback(0L);
        }
        super.resume();
    }

    public void reverse()
    {
        if(isPulsingInternal())
        {
            long l = AnimationUtils.currentAnimationTimeMillis();
            long l1 = mStartTime;
            mStartTime = l - (getScaledDuration() - (l - l1));
            mStartTimeCommitted = true;
            mReversing = mReversing ^ true;
        } else
        if(mStarted)
        {
            mReversing = mReversing ^ true;
            end();
        } else
        {
            start(true);
        }
    }

    public void setAllowRunningAsynchronously(boolean flag)
    {
    }

    public void setCurrentFraction(float f)
    {
        initAnimation();
        f = clampFraction(f);
        mStartTimeCommitted = true;
        if(isPulsingInternal())
        {
            long l = (long)((float)getScaledDuration() * f);
            mStartTime = AnimationUtils.currentAnimationTimeMillis() - l;
        } else
        {
            mSeekFraction = f;
        }
        mOverallFraction = f;
        animateValue(getCurrentIterationFraction(f, mReversing));
    }

    public void setCurrentPlayTime(long l)
    {
        float f;
        if(mDuration > 0L)
            f = (float)l / (float)mDuration;
        else
            f = 1.0F;
        setCurrentFraction(f);
    }

    public volatile Animator setDuration(long l)
    {
        return setDuration(l);
    }

    public ValueAnimator setDuration(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Animators cannot have negative duration: ").append(l).toString());
        } else
        {
            mDuration = l;
            return this;
        }
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
        if(typeevaluator != null && mValues != null && mValues.length > 0)
            mValues[0].setEvaluator(typeevaluator);
    }

    public transient void setFloatValues(float af[])
    {
        if(af == null || af.length == 0)
            return;
        if(mValues == null || mValues.length == 0)
            setValues(new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("", af)
            });
        else
            mValues[0].setFloatValues(af);
        mInitialized = false;
    }

    public transient void setIntValues(int ai[])
    {
        if(ai == null || ai.length == 0)
            return;
        if(mValues == null || mValues.length == 0)
            setValues(new PropertyValuesHolder[] {
                PropertyValuesHolder.ofInt("", ai)
            });
        else
            mValues[0].setIntValues(ai);
        mInitialized = false;
    }

    public void setInterpolator(TimeInterpolator timeinterpolator)
    {
        if(timeinterpolator != null)
            mInterpolator = timeinterpolator;
        else
            mInterpolator = new LinearInterpolator();
    }

    public transient void setObjectValues(Object aobj[])
    {
        if(aobj == null || aobj.length == 0)
            return;
        if(mValues == null || mValues.length == 0)
            setValues(new PropertyValuesHolder[] {
                PropertyValuesHolder.ofObject("", null, aobj)
            });
        else
            mValues[0].setObjectValues(aobj);
        mInitialized = false;
    }

    public void setRepeatCount(int i)
    {
        mRepeatCount = i;
    }

    public void setRepeatMode(int i)
    {
        mRepeatMode = i;
    }

    public void setStartDelay(long l)
    {
        long l1 = l;
        if(l < 0L)
        {
            Log.w("ValueAnimator", "Start delay should always be non-negative");
            l1 = 0L;
        }
        mStartDelay = l1;
    }

    public transient void setValues(PropertyValuesHolder apropertyvaluesholder[])
    {
        int i = apropertyvaluesholder.length;
        mValues = apropertyvaluesholder;
        mValuesMap = new HashMap(i);
        for(int j = 0; j < i; j++)
        {
            PropertyValuesHolder propertyvaluesholder = apropertyvaluesholder[j];
            mValuesMap.put(propertyvaluesholder.getPropertyName(), propertyvaluesholder);
        }

        mInitialized = false;
    }

    void skipToEndValue(boolean flag)
    {
        initAnimation();
        float f;
        float f1;
        if(flag)
            f = 0.0F;
        else
            f = 1.0F;
        f1 = f;
        if(mRepeatCount % 2 == 1)
        {
            f1 = f;
            if(mRepeatMode == 2)
                f1 = 0.0F;
        }
        animateValue(f1);
    }

    public void start()
    {
        start(false);
    }

    void startWithoutPulsing(boolean flag)
    {
        mSuppressSelfPulseRequested = true;
        if(flag)
            reverse();
        else
            start();
        mSuppressSelfPulseRequested = false;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("ValueAnimator@").append(Integer.toHexString(hashCode())).toString();
        String s1 = s;
        if(mValues != null)
        {
            int i = 0;
            do
            {
                s1 = s;
                if(i >= mValues.length)
                    break;
                s = (new StringBuilder()).append(s).append("\n    ").append(mValues[i].toString()).toString();
                i++;
            } while(true);
        }
        return s1;
    }

    private static final boolean DEBUG = false;
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private static final String TAG = "ValueAnimator";
    private static final TimeInterpolator sDefaultInterpolator = new AccelerateDecelerateInterpolator();
    private static float sDurationScale = 1.0F;
    private boolean mAnimationEndRequested;
    private float mCurrentFraction;
    private long mDuration;
    private long mFirstFrameTime;
    boolean mInitialized;
    private TimeInterpolator mInterpolator;
    private long mLastFrameTime;
    private float mOverallFraction;
    private long mPauseTime;
    private int mRepeatCount;
    private int mRepeatMode;
    private boolean mResumed;
    private boolean mReversing;
    private boolean mRunning;
    float mSeekFraction;
    private boolean mSelfPulse;
    private long mStartDelay;
    private boolean mStartListenersCalled;
    long mStartTime;
    boolean mStartTimeCommitted;
    private boolean mStarted;
    private boolean mSuppressSelfPulseRequested;
    ArrayList mUpdateListeners;
    PropertyValuesHolder mValues[];
    HashMap mValuesMap;

}
