// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.content.res.ConstantState;
import android.util.StateSet;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.animation:
//            Animator, AnimatorListenerAdapter

public class StateListAnimator
    implements Cloneable
{
    private static class StateListAnimatorConstantState extends ConstantState
    {

        public int getChangingConfigurations()
        {
            return mChangingConf;
        }

        public StateListAnimator newInstance()
        {
            StateListAnimator statelistanimator = mAnimator.clone();
            StateListAnimator._2D_set0(statelistanimator, this);
            return statelistanimator;
        }

        public volatile Object newInstance()
        {
            return newInstance();
        }

        final StateListAnimator mAnimator;
        int mChangingConf;

        public StateListAnimatorConstantState(StateListAnimator statelistanimator)
        {
            mAnimator = statelistanimator;
            StateListAnimator._2D_set0(mAnimator, this);
            mChangingConf = mAnimator.getChangingConfigurations();
        }
    }

    public static class Tuple
    {

        public Animator getAnimator()
        {
            return mAnimator;
        }

        public int[] getSpecs()
        {
            return mSpecs;
        }

        final Animator mAnimator;
        final int mSpecs[];

        private Tuple(int ai[], Animator animator)
        {
            mSpecs = ai;
            mAnimator = animator;
        }

        Tuple(int ai[], Animator animator, Tuple tuple)
        {
            this(ai, animator);
        }
    }


    static Animator _2D_get0(StateListAnimator statelistanimator)
    {
        return statelistanimator.mRunningAnimator;
    }

    static StateListAnimatorConstantState _2D_set0(StateListAnimator statelistanimator, StateListAnimatorConstantState statelistanimatorconstantstate)
    {
        statelistanimator.mConstantState = statelistanimatorconstantstate;
        return statelistanimatorconstantstate;
    }

    static Animator _2D_set1(StateListAnimator statelistanimator, Animator animator)
    {
        statelistanimator.mRunningAnimator = animator;
        return animator;
    }

    public StateListAnimator()
    {
        mTuples = new ArrayList();
        mLastMatch = null;
        mRunningAnimator = null;
        initAnimatorListener();
    }

    private void cancel()
    {
        if(mRunningAnimator != null)
        {
            mRunningAnimator.cancel();
            mRunningAnimator = null;
        }
    }

    private void clearTarget()
    {
        int i = mTuples.size();
        for(int j = 0; j < i; j++)
            ((Tuple)mTuples.get(j)).mAnimator.setTarget(null);

        mViewRef = null;
        mLastMatch = null;
        mRunningAnimator = null;
    }

    private void initAnimatorListener()
    {
        mAnimatorListener = new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                animator.setTarget(null);
                if(StateListAnimator._2D_get0(StateListAnimator.this) == animator)
                    StateListAnimator._2D_set1(StateListAnimator.this, null);
            }

            final StateListAnimator this$0;

            
            {
                this$0 = StateListAnimator.this;
                super();
            }
        }
;
    }

    private void start(Tuple tuple)
    {
        tuple.mAnimator.setTarget(getTarget());
        mRunningAnimator = tuple.mAnimator;
        mRunningAnimator.start();
    }

    public void addState(int ai[], Animator animator)
    {
        ai = new Tuple(ai, animator, null);
        ((Tuple) (ai)).mAnimator.addListener(mAnimatorListener);
        mTuples.add(ai);
        mChangingConfigurations = mChangingConfigurations | animator.getChangingConfigurations();
    }

    public void appendChangingConfigurations(int i)
    {
        mChangingConfigurations = mChangingConfigurations | i;
    }

    public StateListAnimator clone()
    {
        StateListAnimator statelistanimator;
        Tuple tuple;
        int i;
        int j;
        Animator animator;
        try
        {
            statelistanimator = (StateListAnimator)super.clone();
            ArrayList arraylist = JVM INSTR new #47  <Class ArrayList>;
            arraylist.ArrayList(mTuples.size());
            statelistanimator.mTuples = arraylist;
            statelistanimator.mLastMatch = null;
            statelistanimator.mRunningAnimator = null;
            statelistanimator.mViewRef = null;
            statelistanimator.mAnimatorListener = null;
            statelistanimator.initAnimatorListener();
            i = mTuples.size();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError("cannot clone state list animator", clonenotsupportedexception);
        }
        j = 0;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        tuple = (Tuple)mTuples.get(j);
        animator = tuple.mAnimator.clone();
        animator.removeListener(mAnimatorListener);
        statelistanimator.addState(tuple.mSpecs, animator);
        j++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_63;
_L1:
        statelistanimator.setChangingConfigurations(getChangingConfigurations());
        return statelistanimator;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public ConstantState createConstantState()
    {
        return new StateListAnimatorConstantState(this);
    }

    public int getChangingConfigurations()
    {
        return mChangingConfigurations;
    }

    public Animator getRunningAnimator()
    {
        return mRunningAnimator;
    }

    public View getTarget()
    {
        View view = null;
        if(mViewRef != null)
            view = (View)mViewRef.get();
        return view;
    }

    public ArrayList getTuples()
    {
        return mTuples;
    }

    public void jumpToCurrentState()
    {
        if(mRunningAnimator != null)
            mRunningAnimator.end();
    }

    public void setChangingConfigurations(int i)
    {
        mChangingConfigurations = i;
    }

    public void setState(int ai[])
    {
        Object obj = null;
        int i = mTuples.size();
        int j = 0;
        Tuple tuple;
label0:
        do
        {
label1:
            {
                tuple = obj;
                if(j < i)
                {
                    tuple = (Tuple)mTuples.get(j);
                    if(!StateSet.stateSetMatches(tuple.mSpecs, ai))
                        break label1;
                }
                if(tuple == mLastMatch)
                    return;
                break label0;
            }
            j++;
        } while(true);
        if(mLastMatch != null)
            cancel();
        mLastMatch = tuple;
        if(tuple != null)
            start(tuple);
    }

    public void setTarget(View view)
    {
        View view1 = getTarget();
        if(view1 == view)
            return;
        if(view1 != null)
            clearTarget();
        if(view != null)
            mViewRef = new WeakReference(view);
    }

    private AnimatorListenerAdapter mAnimatorListener;
    private int mChangingConfigurations;
    private StateListAnimatorConstantState mConstantState;
    private Tuple mLastMatch;
    private Animator mRunningAnimator;
    private ArrayList mTuples;
    private WeakReference mViewRef;
}
