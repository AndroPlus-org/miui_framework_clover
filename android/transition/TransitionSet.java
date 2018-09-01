// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.transition:
//            Transition, TransitionValues, TransitionValuesMaps, PathMotion, 
//            TransitionPropagation, TransitionListenerAdapter

public class TransitionSet extends Transition
{
    static class TransitionSetListener extends TransitionListenerAdapter
    {

        public void onTransitionEnd(Transition transition)
        {
            TransitionSet transitionset = mTransitionSet;
            transitionset.mCurrentListeners = transitionset.mCurrentListeners - 1;
            if(mTransitionSet.mCurrentListeners == 0)
            {
                mTransitionSet.mStarted = false;
                mTransitionSet.end();
            }
            transition.removeListener(this);
        }

        public void onTransitionStart(Transition transition)
        {
            if(!mTransitionSet.mStarted)
            {
                mTransitionSet.start();
                mTransitionSet.mStarted = true;
            }
        }

        TransitionSet mTransitionSet;

        TransitionSetListener(TransitionSet transitionset)
        {
            mTransitionSet = transitionset;
        }
    }


    public TransitionSet()
    {
        mTransitions = new ArrayList();
        mPlayTogether = true;
        mStarted = false;
    }

    public TransitionSet(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTransitions = new ArrayList();
        mPlayTogether = true;
        mStarted = false;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TransitionSet);
        setOrdering(context.getInt(0, 0));
        context.recycle();
    }

    private void setupStartEndListeners()
    {
        TransitionSetListener transitionsetlistener = new TransitionSetListener(this);
        for(Iterator iterator = mTransitions.iterator(); iterator.hasNext(); ((Transition)iterator.next()).addListener(transitionsetlistener));
        mCurrentListeners = mTransitions.size();
    }

    public volatile Transition addListener(Transition.TransitionListener transitionlistener)
    {
        return addListener(transitionlistener);
    }

    public TransitionSet addListener(Transition.TransitionListener transitionlistener)
    {
        return (TransitionSet)super.addListener(transitionlistener);
    }

    public volatile Transition addTarget(int i)
    {
        return addTarget(i);
    }

    public volatile Transition addTarget(View view)
    {
        return addTarget(view);
    }

    public volatile Transition addTarget(Class class1)
    {
        return addTarget(class1);
    }

    public volatile Transition addTarget(String s)
    {
        return addTarget(s);
    }

    public TransitionSet addTarget(int i)
    {
        for(int j = 0; j < mTransitions.size(); j++)
            ((Transition)mTransitions.get(j)).addTarget(i);

        return (TransitionSet)super.addTarget(i);
    }

    public TransitionSet addTarget(View view)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).addTarget(view);

        return (TransitionSet)super.addTarget(view);
    }

    public TransitionSet addTarget(Class class1)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).addTarget(class1);

        return (TransitionSet)super.addTarget(class1);
    }

    public TransitionSet addTarget(String s)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).addTarget(s);

        return (TransitionSet)super.addTarget(s);
    }

    public TransitionSet addTransition(Transition transition)
    {
        if(transition != null)
        {
            mTransitions.add(transition);
            transition.mParent = this;
            if(mDuration >= 0L)
                transition.setDuration(mDuration);
        }
        return this;
    }

    protected void cancel()
    {
        super.cancel();
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).cancel();

    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        if(isValidTarget(transitionvalues.view))
        {
            Iterator iterator = mTransitions.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Transition transition = (Transition)iterator.next();
                if(transition.isValidTarget(transitionvalues.view))
                {
                    transition.captureEndValues(transitionvalues);
                    transitionvalues.targetedTransitions.add(transition);
                }
            } while(true);
        }
    }

    void capturePropagationValues(TransitionValues transitionvalues)
    {
        super.capturePropagationValues(transitionvalues);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).capturePropagationValues(transitionvalues);

    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        if(isValidTarget(transitionvalues.view))
        {
            Iterator iterator = mTransitions.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Transition transition = (Transition)iterator.next();
                if(transition.isValidTarget(transitionvalues.view))
                {
                    transition.captureStartValues(transitionvalues);
                    transitionvalues.targetedTransitions.add(transition);
                }
            } while(true);
        }
    }

    public volatile Transition clone()
    {
        return clone();
    }

    public TransitionSet clone()
    {
        TransitionSet transitionset = (TransitionSet)super.clone();
        transitionset.mTransitions = new ArrayList();
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            transitionset.addTransition(((Transition)mTransitions.get(j)).clone());

        return transitionset;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    protected void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1, ArrayList arraylist, ArrayList arraylist1)
    {
        long l = getStartDelay();
        int i = mTransitions.size();
        int j = 0;
        while(j < i) 
        {
            Transition transition = (Transition)mTransitions.get(j);
            if(l > 0L && (mPlayTogether || j == 0))
            {
                long l1 = transition.getStartDelay();
                if(l1 > 0L)
                    transition.setStartDelay(l + l1);
                else
                    transition.setStartDelay(l);
            }
            transition.createAnimators(viewgroup, transitionvaluesmaps, transitionvaluesmaps1, arraylist, arraylist1);
            j++;
        }
    }

    public Transition excludeTarget(int i, boolean flag)
    {
        for(int j = 0; j < mTransitions.size(); j++)
            ((Transition)mTransitions.get(j)).excludeTarget(i, flag);

        return super.excludeTarget(i, flag);
    }

    public Transition excludeTarget(View view, boolean flag)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).excludeTarget(view, flag);

        return super.excludeTarget(view, flag);
    }

    public Transition excludeTarget(Class class1, boolean flag)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).excludeTarget(class1, flag);

        return super.excludeTarget(class1, flag);
    }

    public Transition excludeTarget(String s, boolean flag)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).excludeTarget(s, flag);

        return super.excludeTarget(s, flag);
    }

    void forceToEnd(ViewGroup viewgroup)
    {
        super.forceToEnd(viewgroup);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).forceToEnd(viewgroup);

    }

    public int getOrdering()
    {
        int i;
        if(mPlayTogether)
            i = 0;
        else
            i = 1;
        return i;
    }

    public Transition getTransitionAt(int i)
    {
        if(i < 0 || i >= mTransitions.size())
            return null;
        else
            return (Transition)mTransitions.get(i);
    }

    public int getTransitionCount()
    {
        return mTransitions.size();
    }

    public void pause(View view)
    {
        super.pause(view);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).pause(view);

    }

    public volatile Transition removeListener(Transition.TransitionListener transitionlistener)
    {
        return removeListener(transitionlistener);
    }

    public TransitionSet removeListener(Transition.TransitionListener transitionlistener)
    {
        return (TransitionSet)super.removeListener(transitionlistener);
    }

    public volatile Transition removeTarget(int i)
    {
        return removeTarget(i);
    }

    public volatile Transition removeTarget(View view)
    {
        return removeTarget(view);
    }

    public volatile Transition removeTarget(Class class1)
    {
        return removeTarget(class1);
    }

    public volatile Transition removeTarget(String s)
    {
        return removeTarget(s);
    }

    public TransitionSet removeTarget(int i)
    {
        for(int j = 0; j < mTransitions.size(); j++)
            ((Transition)mTransitions.get(j)).removeTarget(i);

        return (TransitionSet)super.removeTarget(i);
    }

    public TransitionSet removeTarget(View view)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).removeTarget(view);

        return (TransitionSet)super.removeTarget(view);
    }

    public TransitionSet removeTarget(Class class1)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).removeTarget(class1);

        return (TransitionSet)super.removeTarget(class1);
    }

    public TransitionSet removeTarget(String s)
    {
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).removeTarget(s);

        return (TransitionSet)super.removeTarget(s);
    }

    public TransitionSet removeTransition(Transition transition)
    {
        mTransitions.remove(transition);
        transition.mParent = null;
        return this;
    }

    public void resume(View view)
    {
        super.resume(view);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).resume(view);

    }

    protected void runAnimators()
    {
        if(mTransitions.isEmpty())
        {
            start();
            end();
            return;
        }
        setupStartEndListeners();
        int i = mTransitions.size();
        if(!mPlayTogether)
        {
            for(int j = 1; j < i; j++)
                ((Transition)mTransitions.get(j - 1)).addListener(new TransitionListenerAdapter() {

                    public void onTransitionEnd(Transition transition1)
                    {
                        nextTransition.runAnimators();
                        transition1.removeListener(this);
                    }

                    final TransitionSet this$0;
                    final Transition val$nextTransition;

            
            {
                this$0 = TransitionSet.this;
                nextTransition = transition;
                super();
            }
                }
);

            Transition transition = (Transition)mTransitions.get(0);
            if(transition != null)
                transition.runAnimators();
        } else
        {
            int k = 0;
            while(k < i) 
            {
                ((Transition)mTransitions.get(k)).runAnimators();
                k++;
            }
        }
    }

    void setCanRemoveViews(boolean flag)
    {
        super.setCanRemoveViews(flag);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).setCanRemoveViews(flag);

    }

    public volatile Transition setDuration(long l)
    {
        return setDuration(l);
    }

    public TransitionSet setDuration(long l)
    {
        super.setDuration(l);
        if(mDuration >= 0L && mTransitions != null)
        {
            int i = mTransitions.size();
            for(int j = 0; j < i; j++)
                ((Transition)mTransitions.get(j)).setDuration(l);

        }
        return this;
    }

    public void setEpicenterCallback(Transition.EpicenterCallback epicentercallback)
    {
        super.setEpicenterCallback(epicentercallback);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).setEpicenterCallback(epicentercallback);

    }

    public volatile Transition setInterpolator(TimeInterpolator timeinterpolator)
    {
        return setInterpolator(timeinterpolator);
    }

    public TransitionSet setInterpolator(TimeInterpolator timeinterpolator)
    {
        return (TransitionSet)super.setInterpolator(timeinterpolator);
    }

    public TransitionSet setOrdering(int i)
    {
        i;
        JVM INSTR tableswitch 0 1: default 24
    //                   0 59
    //                   1 52;
           goto _L1 _L2 _L3
_L1:
        throw new AndroidRuntimeException((new StringBuilder()).append("Invalid parameter for TransitionSet ordering: ").append(i).toString());
_L3:
        mPlayTogether = false;
_L5:
        return this;
_L2:
        mPlayTogether = true;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setPathMotion(PathMotion pathmotion)
    {
        super.setPathMotion(pathmotion);
        for(int i = 0; i < mTransitions.size(); i++)
            ((Transition)mTransitions.get(i)).setPathMotion(pathmotion);

    }

    public void setPropagation(TransitionPropagation transitionpropagation)
    {
        super.setPropagation(transitionpropagation);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).setPropagation(transitionpropagation);

    }

    volatile Transition setSceneRoot(ViewGroup viewgroup)
    {
        return setSceneRoot(viewgroup);
    }

    TransitionSet setSceneRoot(ViewGroup viewgroup)
    {
        super.setSceneRoot(viewgroup);
        int i = mTransitions.size();
        for(int j = 0; j < i; j++)
            ((Transition)mTransitions.get(j)).setSceneRoot(viewgroup);

        return this;
    }

    public volatile Transition setStartDelay(long l)
    {
        return setStartDelay(l);
    }

    public TransitionSet setStartDelay(long l)
    {
        return (TransitionSet)super.setStartDelay(l);
    }

    String toString(String s)
    {
        String s1 = super.toString(s);
        for(int i = 0; i < mTransitions.size(); i++)
            s1 = (new StringBuilder()).append(s1).append("\n").append(((Transition)mTransitions.get(i)).toString((new StringBuilder()).append(s).append("  ").toString())).toString();

        return s1;
    }

    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    ArrayList mTransitions;
}
