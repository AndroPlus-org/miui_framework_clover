// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.*;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.*;

// Referenced classes of package android.transition:
//            TransitionValuesMaps, TransitionValues, TransitionPropagation, TransitionSet, 
//            PathMotion

public abstract class Transition
    implements Cloneable
{
    public static class AnimationInfo
    {

        String name;
        Transition transition;
        TransitionValues values;
        public View view;
        WindowId windowId;

        AnimationInfo(View view1, String s, Transition transition1, WindowId windowid, TransitionValues transitionvalues)
        {
            view = view1;
            name = s;
            values = transitionvalues;
            windowId = windowid;
            transition = transition1;
        }
    }

    private static class ArrayListManager
    {

        static ArrayList add(ArrayList arraylist, Object obj)
        {
            ArrayList arraylist1 = arraylist;
            if(arraylist == null)
                arraylist1 = new ArrayList();
            if(!arraylist1.contains(obj))
                arraylist1.add(obj);
            return arraylist1;
        }

        static ArrayList remove(ArrayList arraylist, Object obj)
        {
            ArrayList arraylist1 = arraylist;
            if(arraylist != null)
            {
                arraylist.remove(obj);
                arraylist1 = arraylist;
                if(arraylist.isEmpty())
                    arraylist1 = null;
            }
            return arraylist1;
        }

        private ArrayListManager()
        {
        }
    }

    public static abstract class EpicenterCallback
    {

        public abstract Rect onGetEpicenter(Transition transition);

        public EpicenterCallback()
        {
        }
    }

    public static interface TransitionListener
    {

        public abstract void onTransitionCancel(Transition transition);

        public abstract void onTransitionEnd(Transition transition);

        public abstract void onTransitionPause(Transition transition);

        public abstract void onTransitionResume(Transition transition);

        public abstract void onTransitionStart(Transition transition);
    }


    static ArrayList _2D_get0(Transition transition)
    {
        return transition.mCurrentAnimators;
    }

    public Transition()
    {
        mName = getClass().getName();
        mStartDelay = -1L;
        mDuration = -1L;
        mInterpolator = null;
        mTargetIds = new ArrayList();
        mTargets = new ArrayList();
        mTargetNames = null;
        mTargetTypes = null;
        mTargetIdExcludes = null;
        mTargetExcludes = null;
        mTargetTypeExcludes = null;
        mTargetNameExcludes = null;
        mTargetIdChildExcludes = null;
        mTargetChildExcludes = null;
        mTargetTypeChildExcludes = null;
        mStartValues = new TransitionValuesMaps();
        mEndValues = new TransitionValuesMaps();
        mParent = null;
        mMatchOrder = DEFAULT_MATCH_ORDER;
        mSceneRoot = null;
        mCanRemoveViews = false;
        mCurrentAnimators = new ArrayList();
        mNumInstances = 0;
        mPaused = false;
        mEnded = false;
        mListeners = null;
        mAnimators = new ArrayList();
        mPathMotion = STRAIGHT_PATH_MOTION;
    }

    public Transition(Context context, AttributeSet attributeset)
    {
        mName = getClass().getName();
        mStartDelay = -1L;
        mDuration = -1L;
        mInterpolator = null;
        mTargetIds = new ArrayList();
        mTargets = new ArrayList();
        mTargetNames = null;
        mTargetTypes = null;
        mTargetIdExcludes = null;
        mTargetExcludes = null;
        mTargetTypeExcludes = null;
        mTargetNameExcludes = null;
        mTargetIdChildExcludes = null;
        mTargetChildExcludes = null;
        mTargetTypeChildExcludes = null;
        mStartValues = new TransitionValuesMaps();
        mEndValues = new TransitionValuesMaps();
        mParent = null;
        mMatchOrder = DEFAULT_MATCH_ORDER;
        mSceneRoot = null;
        mCanRemoveViews = false;
        mCurrentAnimators = new ArrayList();
        mNumInstances = 0;
        mPaused = false;
        mEnded = false;
        mListeners = null;
        mAnimators = new ArrayList();
        mPathMotion = STRAIGHT_PATH_MOTION;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Transition);
        long l = attributeset.getInt(1, -1);
        if(l >= 0L)
            setDuration(l);
        l = attributeset.getInt(2, -1);
        if(l > 0L)
            setStartDelay(l);
        int i = attributeset.getResourceId(0, 0);
        if(i > 0)
            setInterpolator(AnimationUtils.loadInterpolator(context, i));
        context = attributeset.getString(3);
        if(context != null)
            setMatchOrder(parseMatchOrder(context));
        attributeset.recycle();
    }

    private void addUnmatched(ArrayMap arraymap, ArrayMap arraymap1)
    {
        for(int i = 0; i < arraymap.size(); i++)
        {
            TransitionValues transitionvalues = (TransitionValues)arraymap.valueAt(i);
            if(isValidTarget(transitionvalues.view))
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(null);
            }
        }

        for(int j = 0; j < arraymap1.size(); j++)
        {
            arraymap = (TransitionValues)arraymap1.valueAt(j);
            if(isValidTarget(((TransitionValues) (arraymap)).view))
            {
                mEndValuesList.add(arraymap);
                mStartValuesList.add(null);
            }
        }

    }

    static void addViewValues(TransitionValuesMaps transitionvaluesmaps, View view, TransitionValues transitionvalues)
    {
        transitionvaluesmaps.viewValues.put(view, transitionvalues);
        int i = view.getId();
        if(i >= 0)
            if(transitionvaluesmaps.idValues.indexOfKey(i) >= 0)
                transitionvaluesmaps.idValues.put(i, null);
            else
                transitionvaluesmaps.idValues.put(i, view);
        transitionvalues = view.getTransitionName();
        if(transitionvalues != null)
            if(transitionvaluesmaps.nameValues.containsKey(transitionvalues))
                transitionvaluesmaps.nameValues.put(transitionvalues, null);
            else
                transitionvaluesmaps.nameValues.put(transitionvalues, view);
        if(view.getParent() instanceof ListView)
        {
            transitionvalues = (ListView)view.getParent();
            if(transitionvalues.getAdapter().hasStableIds())
            {
                long l = transitionvalues.getItemIdAtPosition(transitionvalues.getPositionForView(view));
                if(transitionvaluesmaps.itemIdValues.indexOfKey(l) >= 0)
                {
                    view = (View)transitionvaluesmaps.itemIdValues.get(l);
                    if(view != null)
                    {
                        view.setHasTransientState(false);
                        transitionvaluesmaps.itemIdValues.put(l, null);
                    }
                } else
                {
                    view.setHasTransientState(true);
                    transitionvaluesmaps.itemIdValues.put(l, view);
                }
            }
        }
    }

    private static boolean alreadyContains(int ai[], int i)
    {
        int j = ai[i];
        for(int k = 0; k < i; k++)
            if(ai[k] == j)
                return true;

        return false;
    }

    private void captureHierarchy(View view, boolean flag)
    {
        if(view == null)
            return;
        int i = view.getId();
        if(mTargetIdExcludes != null && mTargetIdExcludes.contains(Integer.valueOf(i)))
            return;
        if(mTargetExcludes != null && mTargetExcludes.contains(view))
            return;
        if(mTargetTypeExcludes != null && view != null)
        {
            int j = mTargetTypeExcludes.size();
            for(int l = 0; l < j; l++)
                if(((Class)mTargetTypeExcludes.get(l)).isInstance(view))
                    return;

        }
        if(view.getParent() instanceof ViewGroup)
        {
            TransitionValues transitionvalues = new TransitionValues();
            transitionvalues.view = view;
            if(flag)
                captureStartValues(transitionvalues);
            else
                captureEndValues(transitionvalues);
            transitionvalues.targetedTransitions.add(this);
            capturePropagationValues(transitionvalues);
            if(flag)
                addViewValues(mStartValues, view, transitionvalues);
            else
                addViewValues(mEndValues, view, transitionvalues);
        }
        if(view instanceof ViewGroup)
        {
            if(mTargetIdChildExcludes != null && mTargetIdChildExcludes.contains(Integer.valueOf(i)))
                return;
            if(mTargetChildExcludes != null && mTargetChildExcludes.contains(view))
                return;
            if(mTargetTypeChildExcludes != null)
            {
                int k = mTargetTypeChildExcludes.size();
                for(int i1 = 0; i1 < k; i1++)
                    if(((Class)mTargetTypeChildExcludes.get(i1)).isInstance(view))
                        return;

            }
            view = (ViewGroup)view;
            for(int j1 = 0; j1 < view.getChildCount(); j1++)
                captureHierarchy(view.getChildAt(j1), flag);

        }
    }

    private static ArrayList excludeObject(ArrayList arraylist, Object obj, boolean flag)
    {
        ArrayList arraylist1 = arraylist;
        if(obj != null)
            if(flag)
                arraylist1 = ArrayListManager.add(arraylist, obj);
            else
                arraylist1 = ArrayListManager.remove(arraylist, obj);
        return arraylist1;
    }

    private static ArrayMap getRunningAnimators()
    {
        ArrayMap arraymap = (ArrayMap)sRunningAnimators.get();
        ArrayMap arraymap1 = arraymap;
        if(arraymap == null)
        {
            arraymap1 = new ArrayMap();
            sRunningAnimators.set(arraymap1);
        }
        return arraymap1;
    }

    private static boolean isValidMatch(int i)
    {
        boolean flag = true;
        if(i < 1 || i > 4)
            flag = false;
        return flag;
    }

    private static boolean isValueChanged(TransitionValues transitionvalues, TransitionValues transitionvalues1, String s)
    {
        if(transitionvalues.values.containsKey(s) != transitionvalues1.values.containsKey(s))
            return false;
        transitionvalues = ((TransitionValues) (transitionvalues.values.get(s)));
        transitionvalues1 = ((TransitionValues) (transitionvalues1.values.get(s)));
        boolean flag;
        if(transitionvalues == null && transitionvalues1 == null)
            flag = false;
        else
        if(transitionvalues == null || transitionvalues1 == null)
            flag = true;
        else
            flag = transitionvalues.equals(transitionvalues1) ^ true;
        return flag;
    }

    private void matchIds(ArrayMap arraymap, ArrayMap arraymap1, SparseArray sparsearray, SparseArray sparsearray1)
    {
        int i = sparsearray.size();
        for(int j = 0; j < i; j++)
        {
            View view = (View)sparsearray.valueAt(j);
            if(view == null || !isValidTarget(view))
                continue;
            View view1 = (View)sparsearray1.get(sparsearray.keyAt(j));
            if(view1 == null || !isValidTarget(view1))
                continue;
            TransitionValues transitionvalues = (TransitionValues)arraymap.get(view);
            TransitionValues transitionvalues1 = (TransitionValues)arraymap1.get(view1);
            if(transitionvalues != null && transitionvalues1 != null)
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(transitionvalues1);
                arraymap.remove(view);
                arraymap1.remove(view1);
            }
        }

    }

    private void matchInstances(ArrayMap arraymap, ArrayMap arraymap1)
    {
        for(int i = arraymap.size() - 1; i >= 0; i--)
        {
            View view = (View)arraymap.keyAt(i);
            if(view == null || !isValidTarget(view))
                continue;
            TransitionValues transitionvalues1 = (TransitionValues)arraymap1.remove(view);
            if(transitionvalues1 != null && transitionvalues1.view != null && isValidTarget(transitionvalues1.view))
            {
                TransitionValues transitionvalues = (TransitionValues)arraymap.removeAt(i);
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(transitionvalues1);
            }
        }

    }

    private void matchItemIds(ArrayMap arraymap, ArrayMap arraymap1, LongSparseArray longsparsearray, LongSparseArray longsparsearray1)
    {
        int i = longsparsearray.size();
        for(int j = 0; j < i; j++)
        {
            View view = (View)longsparsearray.valueAt(j);
            if(view == null || !isValidTarget(view))
                continue;
            View view1 = (View)longsparsearray1.get(longsparsearray.keyAt(j));
            if(view1 == null || !isValidTarget(view1))
                continue;
            TransitionValues transitionvalues = (TransitionValues)arraymap.get(view);
            TransitionValues transitionvalues1 = (TransitionValues)arraymap1.get(view1);
            if(transitionvalues != null && transitionvalues1 != null)
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(transitionvalues1);
                arraymap.remove(view);
                arraymap1.remove(view1);
            }
        }

    }

    private void matchNames(ArrayMap arraymap, ArrayMap arraymap1, ArrayMap arraymap2, ArrayMap arraymap3)
    {
        int i = arraymap2.size();
        for(int j = 0; j < i; j++)
        {
            View view = (View)arraymap2.valueAt(j);
            if(view == null || !isValidTarget(view))
                continue;
            View view1 = (View)arraymap3.get(arraymap2.keyAt(j));
            if(view1 == null || !isValidTarget(view1))
                continue;
            TransitionValues transitionvalues = (TransitionValues)arraymap.get(view);
            TransitionValues transitionvalues1 = (TransitionValues)arraymap1.get(view1);
            if(transitionvalues != null && transitionvalues1 != null)
            {
                mStartValuesList.add(transitionvalues);
                mEndValuesList.add(transitionvalues1);
                arraymap.remove(view);
                arraymap1.remove(view1);
            }
        }

    }

    private void matchStartAndEnd(TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1)
    {
        ArrayMap arraymap;
        ArrayMap arraymap1;
        int i;
        arraymap = new ArrayMap(transitionvaluesmaps.viewValues);
        arraymap1 = new ArrayMap(transitionvaluesmaps1.viewValues);
        i = 0;
_L7:
        if(i >= mMatchOrder.length)
            break MISSING_BLOCK_LABEL_146;
        mMatchOrder[i];
        JVM INSTR tableswitch 1 4: default 76
    //                   1 82
    //                   2 92
    //                   3 110
    //                   4 128;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_128;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        matchInstances(arraymap, arraymap1);
          goto _L8
_L3:
        matchNames(arraymap, arraymap1, transitionvaluesmaps.nameValues, transitionvaluesmaps1.nameValues);
          goto _L8
_L4:
        matchIds(arraymap, arraymap1, transitionvaluesmaps.idValues, transitionvaluesmaps1.idValues);
          goto _L8
        matchItemIds(arraymap, arraymap1, transitionvaluesmaps.itemIdValues, transitionvaluesmaps1.itemIdValues);
          goto _L8
        addUnmatched(arraymap, arraymap1);
        return;
    }

    private static int[] parseMatchOrder(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s, ",");
        s = new int[stringtokenizer.countTokens()];
        int i = 0;
        while(stringtokenizer.hasMoreTokens()) 
        {
            String s1 = stringtokenizer.nextToken().trim();
            if("id".equalsIgnoreCase(s1))
                s[i] = 3;
            else
            if("instance".equalsIgnoreCase(s1))
                s[i] = 1;
            else
            if("name".equalsIgnoreCase(s1))
                s[i] = 2;
            else
            if("viewName".equalsIgnoreCase(s1))
                s[i] = 2;
            else
            if("itemId".equalsIgnoreCase(s1))
                s[i] = 4;
            else
            if(s1.isEmpty())
            {
                s1 = new int[s.length - 1];
                System.arraycopy(s, 0, s1, 0, i);
                s = s1;
                i--;
            } else
            {
                throw new InflateException((new StringBuilder()).append("Unknown match type in matchOrder: '").append(s1).append("'").toString());
            }
            i++;
        }
        return s;
    }

    private void runAnimator(Animator animator, final ArrayMap runningAnimators)
    {
        if(animator != null)
        {
            animator.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator1)
                {
                    runningAnimators.remove(animator1);
                    Transition._2D_get0(Transition.this).remove(animator1);
                }

                public void onAnimationStart(Animator animator1)
                {
                    Transition._2D_get0(Transition.this).add(animator1);
                }

                final Transition this$0;
                final ArrayMap val$runningAnimators;

            
            {
                this$0 = Transition.this;
                runningAnimators = arraymap;
                super();
            }
            }
);
            animate(animator);
        }
    }

    public Transition addListener(TransitionListener transitionlistener)
    {
        if(mListeners == null)
            mListeners = new ArrayList();
        mListeners.add(transitionlistener);
        return this;
    }

    public Transition addTarget(int i)
    {
        if(i > 0)
            mTargetIds.add(Integer.valueOf(i));
        return this;
    }

    public Transition addTarget(View view)
    {
        mTargets.add(view);
        return this;
    }

    public Transition addTarget(Class class1)
    {
        if(class1 != null)
        {
            if(mTargetTypes == null)
                mTargetTypes = new ArrayList();
            mTargetTypes.add(class1);
        }
        return this;
    }

    public Transition addTarget(String s)
    {
        if(s != null)
        {
            if(mTargetNames == null)
                mTargetNames = new ArrayList();
            mTargetNames.add(s);
        }
        return this;
    }

    protected void animate(Animator animator)
    {
        if(animator == null)
        {
            end();
        } else
        {
            if(getDuration() >= 0L)
                animator.setDuration(getDuration());
            if(getStartDelay() >= 0L)
                animator.setStartDelay(getStartDelay() + animator.getStartDelay());
            if(getInterpolator() != null)
                animator.setInterpolator(getInterpolator());
            animator.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator1)
                {
                    end();
                    animator1.removeListener(this);
                }

                final Transition this$0;

            
            {
                this$0 = Transition.this;
                super();
            }
            }
);
            animator.start();
        }
    }

    public boolean canRemoveViews()
    {
        return mCanRemoveViews;
    }

    protected void cancel()
    {
        for(int i = mCurrentAnimators.size() - 1; i >= 0; i--)
            ((Animator)mCurrentAnimators.get(i)).cancel();

        if(mListeners != null && mListeners.size() > 0)
        {
            ArrayList arraylist = (ArrayList)mListeners.clone();
            int k = arraylist.size();
            for(int j = 0; j < k; j++)
                ((TransitionListener)arraylist.get(j)).onTransitionCancel(this);

        }
    }

    public abstract void captureEndValues(TransitionValues transitionvalues);

    void capturePropagationValues(TransitionValues transitionvalues)
    {
        if(mPropagation == null || !(transitionvalues.values.isEmpty() ^ true)) goto _L2; else goto _L1
_L1:
        String as[];
        boolean flag;
        int i;
        as = mPropagation.getPropagationProperties();
        if(as == null)
            return;
        flag = true;
        i = 0;
_L8:
        boolean flag1 = flag;
        if(i >= as.length) goto _L4; else goto _L3
_L3:
        if(transitionvalues.values.containsKey(as[i])) goto _L6; else goto _L5
_L5:
        flag1 = false;
_L4:
        if(!flag1)
            mPropagation.captureValues(transitionvalues);
_L2:
        return;
_L6:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public abstract void captureStartValues(TransitionValues transitionvalues);

    void captureValues(ViewGroup viewgroup, boolean flag)
    {
        clearValues(flag);
        if((mTargetIds.size() > 0 || mTargets.size() > 0) && (mTargetNames == null || mTargetNames.isEmpty()) && (mTargetTypes == null || mTargetTypes.isEmpty()))
        {
            int i = 0;
            while(i < mTargetIds.size()) 
            {
                View view = viewgroup.findViewById(((Integer)mTargetIds.get(i)).intValue());
                if(view != null)
                {
                    TransitionValues transitionvalues1 = new TransitionValues();
                    transitionvalues1.view = view;
                    if(flag)
                        captureStartValues(transitionvalues1);
                    else
                        captureEndValues(transitionvalues1);
                    transitionvalues1.targetedTransitions.add(this);
                    capturePropagationValues(transitionvalues1);
                    if(flag)
                        addViewValues(mStartValues, view, transitionvalues1);
                    else
                        addViewValues(mEndValues, view, transitionvalues1);
                }
                i++;
            }
            i = 0;
            while(i < mTargets.size()) 
            {
                viewgroup = (View)mTargets.get(i);
                TransitionValues transitionvalues = new TransitionValues();
                transitionvalues.view = viewgroup;
                if(flag)
                    captureStartValues(transitionvalues);
                else
                    captureEndValues(transitionvalues);
                transitionvalues.targetedTransitions.add(this);
                capturePropagationValues(transitionvalues);
                if(flag)
                    addViewValues(mStartValues, viewgroup, transitionvalues);
                else
                    addViewValues(mEndValues, viewgroup, transitionvalues);
                i++;
            }
        } else
        {
            captureHierarchy(viewgroup, flag);
        }
        if(!flag && mNameOverrides != null)
        {
            int l = mNameOverrides.size();
            viewgroup = new ArrayList(l);
            for(int j = 0; j < l; j++)
            {
                String s = (String)mNameOverrides.keyAt(j);
                viewgroup.add((View)mStartValues.nameValues.remove(s));
            }

            for(int k = 0; k < l; k++)
            {
                View view1 = (View)viewgroup.get(k);
                if(view1 != null)
                {
                    String s1 = (String)mNameOverrides.valueAt(k);
                    mStartValues.nameValues.put(s1, view1);
                }
            }

        }
    }

    void clearValues(boolean flag)
    {
        if(flag)
        {
            mStartValues.viewValues.clear();
            mStartValues.idValues.clear();
            mStartValues.itemIdValues.clear();
            mStartValues.nameValues.clear();
            mStartValuesList = null;
        } else
        {
            mEndValues.viewValues.clear();
            mEndValues.idValues.clear();
            mEndValues.itemIdValues.clear();
            mEndValues.nameValues.clear();
            mEndValuesList = null;
        }
    }

    public Transition clone()
    {
        Transition transition = null;
        Transition transition1 = (Transition)super.clone();
        transition = transition1;
        Object obj = JVM INSTR new #156 <Class ArrayList>;
        transition = transition1;
        ((ArrayList) (obj)).ArrayList();
        transition = transition1;
        transition1.mAnimators = ((ArrayList) (obj));
        transition = transition1;
        obj = JVM INSTR new #181 <Class TransitionValuesMaps>;
        transition = transition1;
        ((TransitionValuesMaps) (obj)).TransitionValuesMaps();
        transition = transition1;
        transition1.mStartValues = ((TransitionValuesMaps) (obj));
        transition = transition1;
        obj = JVM INSTR new #181 <Class TransitionValuesMaps>;
        transition = transition1;
        ((TransitionValuesMaps) (obj)).TransitionValuesMaps();
        transition = transition1;
        transition1.mEndValues = ((TransitionValuesMaps) (obj));
        transition = transition1;
        transition1.mStartValuesList = null;
        transition = transition1;
        transition1.mEndValuesList = null;
        transition = transition1;
_L2:
        return transition;
        CloneNotSupportedException clonenotsupportedexception;
        clonenotsupportedexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return null;
    }

    protected void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1, ArrayList arraylist, ArrayList arraylist1)
    {
        ArrayMap arraymap;
        long l;
        SparseLongArray sparselongarray;
        int i;
        int j;
        arraymap = getRunningAnimators();
        l = 0x7fffffffffffffffL;
        mAnimators.size();
        sparselongarray = new SparseLongArray();
        i = arraylist.size();
        j = 0;
_L2:
        Object obj;
        TransitionValues transitionvalues;
        long l1;
        if(j >= i)
            break MISSING_BLOCK_LABEL_604;
        obj = (TransitionValues)arraylist.get(j);
        transitionvaluesmaps = (TransitionValues)arraylist1.get(j);
        transitionvalues = ((TransitionValues) (obj));
        if(obj != null)
        {
            transitionvalues = ((TransitionValues) (obj));
            if(((TransitionValues) (obj)).targetedTransitions.contains(this) ^ true)
                transitionvalues = null;
        }
        obj = transitionvaluesmaps;
        if(transitionvaluesmaps != null)
        {
            obj = transitionvaluesmaps;
            if(((TransitionValues) (transitionvaluesmaps)).targetedTransitions.contains(this) ^ true)
                obj = null;
        }
        if(transitionvalues != null || obj != null)
            break; /* Loop/switch isn't completed */
        l1 = l;
_L4:
        j++;
        l = l1;
        if(true) goto _L2; else goto _L1
_L1:
        Animator animator;
        TransitionValues transitionvalues1;
        View view;
        TransitionValues transitionvalues2;
        Animator animator1;
        boolean flag;
        String as[];
        int i1;
        if(transitionvalues == null || obj == null)
            flag = true;
        else
            flag = isTransitionRequired(transitionvalues, ((TransitionValues) (obj)));
        l1 = l;
        if(!flag) goto _L4; else goto _L3
_L3:
        animator = createAnimator(viewgroup, transitionvalues, ((TransitionValues) (obj)));
        l1 = l;
        if(animator == null) goto _L4; else goto _L5
_L5:
        transitionvalues1 = null;
        if(obj == null)
            break MISSING_BLOCK_LABEL_569;
        view = ((TransitionValues) (obj)).view;
        as = getTransitionProperties();
        transitionvaluesmaps = view;
        transitionvalues2 = transitionvalues1;
        animator1 = animator;
        if(view == null) goto _L7; else goto _L6
_L6:
        transitionvaluesmaps = view;
        transitionvalues2 = transitionvalues1;
        animator1 = animator;
        if(as == null) goto _L7; else goto _L8
_L8:
        transitionvaluesmaps = view;
        transitionvalues2 = transitionvalues1;
        animator1 = animator;
        if(as.length <= 0) goto _L7; else goto _L9
_L9:
        int j1;
        int i2;
        transitionvalues1 = new TransitionValues();
        transitionvalues1.view = view;
        transitionvaluesmaps = (TransitionValues)transitionvaluesmaps1.viewValues.get(view);
        if(transitionvaluesmaps != null)
            for(i1 = 0; i1 < as.length; i1++)
                transitionvalues1.values.put(as[i1], ((TransitionValues) (transitionvaluesmaps)).values.get(as[i1]));

        i2 = arraymap.size();
        j1 = 0;
_L13:
        transitionvaluesmaps = view;
        transitionvalues2 = transitionvalues1;
        animator1 = animator;
        if(j1 >= i2) goto _L7; else goto _L10
_L10:
        transitionvaluesmaps = (AnimationInfo)arraymap.get((Animator)arraymap.keyAt(j1));
        if(((AnimationInfo) (transitionvaluesmaps)).values == null || ((AnimationInfo) (transitionvaluesmaps)).view != view || (((AnimationInfo) (transitionvaluesmaps)).name != null || getName() != null) && !((AnimationInfo) (transitionvaluesmaps)).name.equals(getName()) || !((AnimationInfo) (transitionvaluesmaps)).values.equals(transitionvalues1)) goto _L12; else goto _L11
_L11:
        animator1 = null;
        transitionvalues2 = transitionvalues1;
        transitionvaluesmaps = view;
_L7:
        l1 = l;
        if(animator1 != null)
        {
            l1 = l;
            if(mPropagation != null)
            {
                l1 = mPropagation.getStartDelay(viewgroup, this, transitionvalues, ((TransitionValues) (obj)));
                sparselongarray.put(mAnimators.size(), l1);
                l1 = Math.min(l1, l);
            }
            arraymap.put(animator1, new AnimationInfo(transitionvaluesmaps, getName(), this, viewgroup.getWindowId(), transitionvalues2));
            mAnimators.add(animator1);
        }
          goto _L4
_L12:
        j1++;
          goto _L13
        if(transitionvalues != null)
        {
            transitionvaluesmaps = transitionvalues.view;
            transitionvalues2 = transitionvalues1;
            animator1 = animator;
        } else
        {
            transitionvaluesmaps = null;
            transitionvalues2 = transitionvalues1;
            animator1 = animator;
        }
          goto _L7
        if(sparselongarray.size() != 0)
        {
            for(int k = 0; k < sparselongarray.size(); k++)
            {
                int k1 = sparselongarray.keyAt(k);
                viewgroup = (Animator)mAnimators.get(k1);
                viewgroup.setStartDelay((sparselongarray.valueAt(k) - l) + viewgroup.getStartDelay());
            }

        }
        return;
          goto _L4
    }

    protected void end()
    {
        mNumInstances = mNumInstances - 1;
        if(mNumInstances == 0)
        {
            if(mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int i = arraylist.size();
                for(int j = 0; j < i; j++)
                    ((TransitionListener)arraylist.get(j)).onTransitionEnd(this);

            }
            for(int k = 0; k < mStartValues.itemIdValues.size(); k++)
            {
                View view = (View)mStartValues.itemIdValues.valueAt(k);
                if(view != null)
                    view.setHasTransientState(false);
            }

            for(int l = 0; l < mEndValues.itemIdValues.size(); l++)
            {
                View view1 = (View)mEndValues.itemIdValues.valueAt(l);
                if(view1 != null)
                    view1.setHasTransientState(false);
            }

            mEnded = true;
        }
    }

    public Transition excludeChildren(int i, boolean flag)
    {
        if(i >= 0)
            mTargetIdChildExcludes = excludeObject(mTargetIdChildExcludes, Integer.valueOf(i), flag);
        return this;
    }

    public Transition excludeChildren(View view, boolean flag)
    {
        mTargetChildExcludes = excludeObject(mTargetChildExcludes, view, flag);
        return this;
    }

    public Transition excludeChildren(Class class1, boolean flag)
    {
        mTargetTypeChildExcludes = excludeObject(mTargetTypeChildExcludes, class1, flag);
        return this;
    }

    public Transition excludeTarget(int i, boolean flag)
    {
        if(i >= 0)
            mTargetIdExcludes = excludeObject(mTargetIdExcludes, Integer.valueOf(i), flag);
        return this;
    }

    public Transition excludeTarget(View view, boolean flag)
    {
        mTargetExcludes = excludeObject(mTargetExcludes, view, flag);
        return this;
    }

    public Transition excludeTarget(Class class1, boolean flag)
    {
        mTargetTypeExcludes = excludeObject(mTargetTypeExcludes, class1, flag);
        return this;
    }

    public Transition excludeTarget(String s, boolean flag)
    {
        mTargetNameExcludes = excludeObject(mTargetNameExcludes, s, flag);
        return this;
    }

    void forceToEnd(ViewGroup viewgroup)
    {
        ArrayMap arraymap = getRunningAnimators();
        int i = arraymap.size();
        if(viewgroup != null)
        {
            viewgroup = viewgroup.getWindowId();
            for(i--; i >= 0; i--)
            {
                AnimationInfo animationinfo = (AnimationInfo)arraymap.valueAt(i);
                if(animationinfo.view != null && viewgroup != null && viewgroup.equals(animationinfo.windowId))
                    ((Animator)arraymap.keyAt(i)).end();
            }

        }
    }

    public long getDuration()
    {
        return mDuration;
    }

    public Rect getEpicenter()
    {
        if(mEpicenterCallback == null)
            return null;
        else
            return mEpicenterCallback.onGetEpicenter(this);
    }

    public EpicenterCallback getEpicenterCallback()
    {
        return mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator()
    {
        return mInterpolator;
    }

    TransitionValues getMatchedTransitionValues(View view, boolean flag)
    {
        if(mParent != null)
            return mParent.getMatchedTransitionValues(view, flag);
        ArrayList arraylist;
        if(flag)
            arraylist = mStartValuesList;
        else
            arraylist = mEndValuesList;
        if(arraylist == null)
            return null;
        int i = arraylist.size();
        byte byte0 = -1;
        int j = 0;
        do
        {
label0:
            {
                int k = byte0;
                if(j < i)
                {
                    TransitionValues transitionvalues = (TransitionValues)arraylist.get(j);
                    if(transitionvalues == null)
                        return null;
                    if(transitionvalues.view != view)
                        break label0;
                    k = j;
                }
                view = null;
                if(k >= 0)
                {
                    if(flag)
                        view = mEndValuesList;
                    else
                        view = mStartValuesList;
                    view = (TransitionValues)view.get(k);
                }
                return view;
            }
            j++;
        } while(true);
    }

    public String getName()
    {
        return mName;
    }

    public ArrayMap getNameOverrides()
    {
        return mNameOverrides;
    }

    public PathMotion getPathMotion()
    {
        return mPathMotion;
    }

    public TransitionPropagation getPropagation()
    {
        return mPropagation;
    }

    public long getStartDelay()
    {
        return mStartDelay;
    }

    public List getTargetIds()
    {
        return mTargetIds;
    }

    public List getTargetNames()
    {
        return mTargetNames;
    }

    public List getTargetTypes()
    {
        return mTargetTypes;
    }

    public List getTargetViewNames()
    {
        return mTargetNames;
    }

    public List getTargets()
    {
        return mTargets;
    }

    public String[] getTransitionProperties()
    {
        return null;
    }

    public TransitionValues getTransitionValues(View view, boolean flag)
    {
        if(mParent != null)
            return mParent.getTransitionValues(view, flag);
        TransitionValuesMaps transitionvaluesmaps;
        if(flag)
            transitionvaluesmaps = mStartValues;
        else
            transitionvaluesmaps = mEndValues;
        return (TransitionValues)transitionvaluesmaps.viewValues.get(view);
    }

    public boolean isTransitionRequired(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(transitionvalues == null) goto _L2; else goto _L1
_L1:
        flag1 = flag;
        if(transitionvalues1 == null) goto _L2; else goto _L3
_L3:
        String as[] = getTransitionProperties();
        if(as == null) goto _L5; else goto _L4
_L4:
        int i;
        int j;
        i = as.length;
        j = 0;
_L9:
        flag1 = flag;
        if(j >= i) goto _L2; else goto _L6
_L6:
        if(!isValueChanged(transitionvalues, transitionvalues1, as[j])) goto _L8; else goto _L7
_L7:
        flag1 = true;
_L2:
        return flag1;
_L8:
        j++;
          goto _L9
_L5:
        Iterator iterator = transitionvalues.values.keySet().iterator();
_L12:
        flag1 = flag;
        if(!iterator.hasNext()) goto _L2; else goto _L10
_L10:
        if(!isValueChanged(transitionvalues, transitionvalues1, (String)iterator.next())) goto _L12; else goto _L11
_L11:
        flag1 = true;
          goto _L2
    }

    public boolean isValidTarget(View view)
    {
        if(view == null)
            return false;
        int i = view.getId();
        if(mTargetIdExcludes != null && mTargetIdExcludes.contains(Integer.valueOf(i)))
            return false;
        if(mTargetExcludes != null && mTargetExcludes.contains(view))
            return false;
        if(mTargetTypeExcludes != null && view != null)
        {
            int j = mTargetTypeExcludes.size();
            for(int k = 0; k < j; k++)
                if(((Class)mTargetTypeExcludes.get(k)).isInstance(view))
                    return false;

        }
        if(mTargetNameExcludes != null && view != null && view.getTransitionName() != null && mTargetNameExcludes.contains(view.getTransitionName()))
            return false;
        if(mTargetIds.size() == 0 && mTargets.size() == 0 && (mTargetTypes == null || mTargetTypes.isEmpty()) && (mTargetNames == null || mTargetNames.isEmpty()))
            return true;
        if(mTargetIds.contains(Integer.valueOf(i)) || mTargets.contains(view))
            return true;
        if(mTargetNames != null && mTargetNames.contains(view.getTransitionName()))
            return true;
        if(mTargetTypes != null)
        {
            for(int l = 0; l < mTargetTypes.size(); l++)
                if(((Class)mTargetTypes.get(l)).isInstance(view))
                    return true;

        }
        return false;
    }

    public void pause(View view)
    {
        if(!mEnded)
        {
            ArrayMap arraymap = getRunningAnimators();
            int i = arraymap.size();
            if(view != null)
            {
                WindowId windowid = view.getWindowId();
                for(i--; i >= 0; i--)
                {
                    view = (AnimationInfo)arraymap.valueAt(i);
                    if(((AnimationInfo) (view)).view != null && windowid != null && windowid.equals(((AnimationInfo) (view)).windowId))
                        ((Animator)arraymap.keyAt(i)).pause();
                }

            }
            if(mListeners != null && mListeners.size() > 0)
            {
                view = (ArrayList)mListeners.clone();
                int k = view.size();
                for(int j = 0; j < k; j++)
                    ((TransitionListener)view.get(j)).onTransitionPause(this);

            }
            mPaused = true;
        }
    }

    void playTransition(ViewGroup viewgroup)
    {
        mStartValuesList = new ArrayList();
        mEndValuesList = new ArrayList();
        matchStartAndEnd(mStartValues, mEndValues);
        ArrayMap arraymap = getRunningAnimators();
        int i = arraymap.size();
        WindowId windowid = viewgroup.getWindowId();
        i--;
        while(i >= 0) 
        {
            Animator animator = (Animator)arraymap.keyAt(i);
            if(animator == null)
                continue;
            AnimationInfo animationinfo = (AnimationInfo)arraymap.get(animator);
            if(animationinfo == null || animationinfo.view == null || animationinfo.windowId != windowid)
                continue;
            TransitionValues transitionvalues = animationinfo.values;
            View view = animationinfo.view;
            TransitionValues transitionvalues1 = getTransitionValues(view, true);
            TransitionValues transitionvalues2 = getMatchedTransitionValues(view, true);
            TransitionValues transitionvalues3 = transitionvalues2;
            if(transitionvalues1 == null)
            {
                transitionvalues3 = transitionvalues2;
                if(transitionvalues2 == null)
                    transitionvalues3 = (TransitionValues)mEndValues.viewValues.get(view);
            }
            boolean flag;
            if(transitionvalues1 != null || transitionvalues3 != null)
                flag = animationinfo.transition.isTransitionRequired(transitionvalues, transitionvalues3);
            else
                flag = false;
            if(flag)
                if(animator.isRunning() || animator.isStarted())
                    animator.cancel();
                else
                    arraymap.remove(animator);
            i--;
        }
        createAnimators(viewgroup, mStartValues, mEndValues, mStartValuesList, mEndValuesList);
        runAnimators();
    }

    public Transition removeListener(TransitionListener transitionlistener)
    {
        if(mListeners == null)
            return this;
        mListeners.remove(transitionlistener);
        if(mListeners.size() == 0)
            mListeners = null;
        return this;
    }

    public Transition removeTarget(int i)
    {
        if(i > 0)
            mTargetIds.remove(Integer.valueOf(i));
        return this;
    }

    public Transition removeTarget(View view)
    {
        if(view != null)
            mTargets.remove(view);
        return this;
    }

    public Transition removeTarget(Class class1)
    {
        if(class1 != null)
            mTargetTypes.remove(class1);
        return this;
    }

    public Transition removeTarget(String s)
    {
        if(s != null && mTargetNames != null)
            mTargetNames.remove(s);
        return this;
    }

    public void resume(View view)
    {
        if(mPaused)
        {
            if(!mEnded)
            {
                ArrayMap arraymap = getRunningAnimators();
                int i = arraymap.size();
                WindowId windowid = view.getWindowId();
                for(i--; i >= 0; i--)
                {
                    view = (AnimationInfo)arraymap.valueAt(i);
                    if(((AnimationInfo) (view)).view != null && windowid != null && windowid.equals(((AnimationInfo) (view)).windowId))
                        ((Animator)arraymap.keyAt(i)).resume();
                }

                if(mListeners != null && mListeners.size() > 0)
                {
                    view = (ArrayList)mListeners.clone();
                    int k = view.size();
                    for(int j = 0; j < k; j++)
                        ((TransitionListener)view.get(j)).onTransitionResume(this);

                }
            }
            mPaused = false;
        }
    }

    protected void runAnimators()
    {
        start();
        ArrayMap arraymap = getRunningAnimators();
        Iterator iterator = mAnimators.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Animator animator = (Animator)iterator.next();
            if(arraymap.containsKey(animator))
            {
                start();
                runAnimator(animator, arraymap);
            }
        } while(true);
        mAnimators.clear();
        end();
    }

    void setCanRemoveViews(boolean flag)
    {
        mCanRemoveViews = flag;
    }

    public Transition setDuration(long l)
    {
        mDuration = l;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback epicentercallback)
    {
        mEpicenterCallback = epicentercallback;
    }

    public Transition setInterpolator(TimeInterpolator timeinterpolator)
    {
        mInterpolator = timeinterpolator;
        return this;
    }

    public transient void setMatchOrder(int ai[])
    {
        if(ai == null || ai.length == 0)
        {
            mMatchOrder = DEFAULT_MATCH_ORDER;
        } else
        {
            for(int i = 0; i < ai.length; i++)
            {
                if(!isValidMatch(ai[i]))
                    throw new IllegalArgumentException("matches contains invalid value");
                if(alreadyContains(ai, i))
                    throw new IllegalArgumentException("matches contains a duplicate value");
            }

            mMatchOrder = (int[])ai.clone();
        }
    }

    public void setNameOverrides(ArrayMap arraymap)
    {
        mNameOverrides = arraymap;
    }

    public void setPathMotion(PathMotion pathmotion)
    {
        if(pathmotion == null)
            mPathMotion = STRAIGHT_PATH_MOTION;
        else
            mPathMotion = pathmotion;
    }

    public void setPropagation(TransitionPropagation transitionpropagation)
    {
        mPropagation = transitionpropagation;
    }

    Transition setSceneRoot(ViewGroup viewgroup)
    {
        mSceneRoot = viewgroup;
        return this;
    }

    public Transition setStartDelay(long l)
    {
        mStartDelay = l;
        return this;
    }

    protected void start()
    {
        if(mNumInstances == 0)
        {
            if(mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int i = arraylist.size();
                for(int j = 0; j < i; j++)
                    ((TransitionListener)arraylist.get(j)).onTransitionStart(this);

            }
            mEnded = false;
        }
        mNumInstances = mNumInstances + 1;
    }

    public String toString()
    {
        return toString("");
    }

    String toString(String s)
    {
        String s1;
label0:
        {
            s1 = (new StringBuilder()).append(s).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(": ").toString();
            s = s1;
            if(mDuration != -1L)
                s = (new StringBuilder()).append(s1).append("dur(").append(mDuration).append(") ").toString();
            s1 = s;
            if(mStartDelay != -1L)
                s1 = (new StringBuilder()).append(s).append("dly(").append(mStartDelay).append(") ").toString();
            s = s1;
            if(mInterpolator != null)
                s = (new StringBuilder()).append(s1).append("interp(").append(mInterpolator).append(") ").toString();
            if(mTargetIds.size() <= 0)
            {
                s1 = s;
                if(mTargets.size() <= 0)
                    break label0;
            }
            s1 = (new StringBuilder()).append(s).append("tgts(").toString();
            s = s1;
            if(mTargetIds.size() > 0)
            {
                int i = 0;
                do
                {
                    s = s1;
                    if(i >= mTargetIds.size())
                        break;
                    s = s1;
                    if(i > 0)
                        s = (new StringBuilder()).append(s1).append(", ").toString();
                    s1 = (new StringBuilder()).append(s).append(mTargetIds.get(i)).toString();
                    i++;
                } while(true);
            }
            s1 = s;
            if(mTargets.size() > 0)
            {
                int j = 0;
                do
                {
                    s1 = s;
                    if(j >= mTargets.size())
                        break;
                    s1 = s;
                    if(j > 0)
                        s1 = (new StringBuilder()).append(s).append(", ").toString();
                    s = (new StringBuilder()).append(s1).append(mTargets.get(j)).toString();
                    j++;
                } while(true);
            }
            s1 = (new StringBuilder()).append(s1).append(")").toString();
        }
        return s1;
    }

    static final boolean DBG = false;
    private static final int DEFAULT_MATCH_ORDER[] = {
        2, 1, 3, 4
    };
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private static final String MATCH_VIEW_NAME_STR = "viewName";
    private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() {

        public Path getPath(float f, float f1, float f2, float f3)
        {
            Path path = new Path();
            path.moveTo(f, f1);
            path.lineTo(f2, f3);
            return path;
        }

    }
;
    private static ThreadLocal sRunningAnimators = new ThreadLocal();
    ArrayList mAnimators;
    boolean mCanRemoveViews;
    private ArrayList mCurrentAnimators;
    long mDuration;
    private TransitionValuesMaps mEndValues;
    ArrayList mEndValuesList;
    private boolean mEnded;
    EpicenterCallback mEpicenterCallback;
    TimeInterpolator mInterpolator;
    ArrayList mListeners;
    int mMatchOrder[];
    private String mName;
    ArrayMap mNameOverrides;
    int mNumInstances;
    TransitionSet mParent;
    PathMotion mPathMotion;
    boolean mPaused;
    TransitionPropagation mPropagation;
    ViewGroup mSceneRoot;
    long mStartDelay;
    private TransitionValuesMaps mStartValues;
    ArrayList mStartValuesList;
    ArrayList mTargetChildExcludes;
    ArrayList mTargetExcludes;
    ArrayList mTargetIdChildExcludes;
    ArrayList mTargetIdExcludes;
    ArrayList mTargetIds;
    ArrayList mTargetNameExcludes;
    ArrayList mTargetNames;
    ArrayList mTargetTypeChildExcludes;
    ArrayList mTargetTypeExcludes;
    ArrayList mTargetTypes;
    ArrayList mTargets;

}
