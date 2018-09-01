// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.content.res.ConstantState;
import java.util.ArrayList;

// Referenced classes of package android.animation:
//            TimeInterpolator

public abstract class Animator
    implements Cloneable
{
    private static class AnimatorConstantState extends ConstantState
    {

        public int getChangingConfigurations()
        {
            return mChangingConf;
        }

        public Animator newInstance()
        {
            Animator animator = mAnimator.clone();
            Animator._2D_set0(animator, this);
            return animator;
        }

        public volatile Object newInstance()
        {
            return newInstance();
        }

        final Animator mAnimator;
        int mChangingConf;

        public AnimatorConstantState(Animator animator)
        {
            mAnimator = animator;
            Animator._2D_set0(mAnimator, this);
            mChangingConf = mAnimator.getChangingConfigurations();
        }
    }

    public static interface AnimatorListener
    {

        public abstract void onAnimationCancel(Animator animator);

        public abstract void onAnimationEnd(Animator animator);

        public void onAnimationEnd(Animator animator, boolean flag)
        {
            onAnimationEnd(animator);
        }

        public abstract void onAnimationRepeat(Animator animator);

        public abstract void onAnimationStart(Animator animator);

        public void onAnimationStart(Animator animator, boolean flag)
        {
            onAnimationStart(animator);
        }
    }

    public static interface AnimatorPauseListener
    {

        public abstract void onAnimationPause(Animator animator);

        public abstract void onAnimationResume(Animator animator);
    }


    static AnimatorConstantState _2D_set0(Animator animator, AnimatorConstantState animatorconstantstate)
    {
        animator.mConstantState = animatorconstantstate;
        return animatorconstantstate;
    }

    public Animator()
    {
        mListeners = null;
        mPauseListeners = null;
        mPaused = false;
        mChangingConfigurations = 0;
    }

    public void addListener(AnimatorListener animatorlistener)
    {
        if(mListeners == null)
            mListeners = new ArrayList();
        mListeners.add(animatorlistener);
    }

    public void addPauseListener(AnimatorPauseListener animatorpauselistener)
    {
        if(mPauseListeners == null)
            mPauseListeners = new ArrayList();
        mPauseListeners.add(animatorpauselistener);
    }

    void animateBasedOnPlayTime(long l, long l1, boolean flag)
    {
    }

    public void appendChangingConfigurations(int i)
    {
        mChangingConfigurations = mChangingConfigurations | i;
    }

    public boolean canReverse()
    {
        return false;
    }

    public void cancel()
    {
    }

    public Animator clone()
    {
        Animator animator;
        try
        {
            animator = (Animator)super.clone();
            if(mListeners != null)
            {
                ArrayList arraylist = JVM INSTR new #51  <Class ArrayList>;
                arraylist.ArrayList(mListeners);
                animator.mListeners = arraylist;
            }
            if(mPauseListeners != null)
            {
                ArrayList arraylist1 = JVM INSTR new #51  <Class ArrayList>;
                arraylist1.ArrayList(mPauseListeners);
                animator.mPauseListeners = arraylist1;
            }
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError();
        }
        return animator;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public ConstantState createConstantState()
    {
        return new AnimatorConstantState(this);
    }

    public void end()
    {
    }

    public int getChangingConfigurations()
    {
        return mChangingConfigurations;
    }

    public abstract long getDuration();

    public TimeInterpolator getInterpolator()
    {
        return null;
    }

    public ArrayList getListeners()
    {
        return mListeners;
    }

    public abstract long getStartDelay();

    public long getTotalDuration()
    {
        long l = getDuration();
        if(l == -1L)
            return -1L;
        else
            return getStartDelay() + l;
    }

    boolean isInitialized()
    {
        return true;
    }

    public boolean isPaused()
    {
        return mPaused;
    }

    public abstract boolean isRunning();

    public boolean isStarted()
    {
        return isRunning();
    }

    public void pause()
    {
        if(isStarted() && mPaused ^ true)
        {
            mPaused = true;
            if(mPauseListeners != null)
            {
                ArrayList arraylist = (ArrayList)mPauseListeners.clone();
                int i = arraylist.size();
                for(int j = 0; j < i; j++)
                    ((AnimatorPauseListener)arraylist.get(j)).onAnimationPause(this);

            }
        }
    }

    boolean pulseAnimationFrame(long l)
    {
        return false;
    }

    public void removeAllListeners()
    {
        if(mListeners != null)
        {
            mListeners.clear();
            mListeners = null;
        }
        if(mPauseListeners != null)
        {
            mPauseListeners.clear();
            mPauseListeners = null;
        }
    }

    public void removeListener(AnimatorListener animatorlistener)
    {
        if(mListeners == null)
            return;
        mListeners.remove(animatorlistener);
        if(mListeners.size() == 0)
            mListeners = null;
    }

    public void removePauseListener(AnimatorPauseListener animatorpauselistener)
    {
        if(mPauseListeners == null)
            return;
        mPauseListeners.remove(animatorpauselistener);
        if(mPauseListeners.size() == 0)
            mPauseListeners = null;
    }

    public void resume()
    {
        if(mPaused)
        {
            mPaused = false;
            if(mPauseListeners != null)
            {
                ArrayList arraylist = (ArrayList)mPauseListeners.clone();
                int i = arraylist.size();
                for(int j = 0; j < i; j++)
                    ((AnimatorPauseListener)arraylist.get(j)).onAnimationResume(this);

            }
        }
    }

    public void reverse()
    {
        throw new IllegalStateException("Reverse is not supported");
    }

    public void setAllowRunningAsynchronously(boolean flag)
    {
    }

    public void setChangingConfigurations(int i)
    {
        mChangingConfigurations = i;
    }

    public abstract Animator setDuration(long l);

    public abstract void setInterpolator(TimeInterpolator timeinterpolator);

    public abstract void setStartDelay(long l);

    public void setTarget(Object obj)
    {
    }

    public void setupEndValues()
    {
    }

    public void setupStartValues()
    {
    }

    void skipToEndValue(boolean flag)
    {
    }

    public void start()
    {
    }

    void startWithoutPulsing(boolean flag)
    {
        if(flag)
            reverse();
        else
            start();
    }

    public static final long DURATION_INFINITE = -1L;
    int mChangingConfigurations;
    private AnimatorConstantState mConstantState;
    ArrayList mListeners;
    ArrayList mPauseListeners;
    boolean mPaused;
}
