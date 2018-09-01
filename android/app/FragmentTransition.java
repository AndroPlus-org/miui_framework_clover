// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Rect;
import android.transition.*;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.*;

// Referenced classes of package android.app:
//            Fragment, BackStackRecord, FragmentManagerImpl, FragmentHostCallback, 
//            FragmentContainer, SharedElementCallback

class FragmentTransition
{
    public static class FragmentContainerTransition
    {

        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        public FragmentContainerTransition()
        {
        }
    }


    static void _2D_wrap0(ArrayList arraylist, int i)
    {
        setViewVisibility(arraylist, i);
    }

    FragmentTransition()
    {
    }

    private static void addSharedElementsWithMatchingNames(ArrayList arraylist, ArrayMap arraymap, Collection collection)
    {
        for(int i = arraymap.size() - 1; i >= 0; i--)
        {
            View view = (View)arraymap.valueAt(i);
            if(view != null && collection.contains(view.getTransitionName()))
                arraylist.add(view);
        }

    }

    public static void addTargets(Transition transition, ArrayList arraylist)
    {
        if(transition == null)
            return;
        if(transition instanceof TransitionSet)
        {
            transition = (TransitionSet)transition;
            int i = transition.getTransitionCount();
            for(int k = 0; k < i; k++)
                addTargets(transition.getTransitionAt(k), arraylist);

        } else
        if(!hasSimpleTarget(transition) && isNullOrEmpty(transition.getTargets()))
        {
            int j = arraylist.size();
            for(int l = 0; l < j; l++)
                transition.addTarget((View)arraylist.get(l));

        }
    }

    private static void addToFirstInLastOut(BackStackRecord backstackrecord, BackStackRecord.Op op, SparseArray sparsearray, boolean flag, boolean flag1)
    {
        Fragment fragment;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        boolean flag9;
        fragment = op.fragment;
        if(fragment == null)
            return;
        int i = fragment.mContainerId;
        if(i == 0)
            return;
        int j;
        Object obj;
        if(flag)
            j = INVERSE_OPS[op.cmd];
        else
            j = op.cmd;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        flag6 = flag4;
        flag7 = flag2;
        flag8 = flag5;
        flag9 = flag3;
        j;
        JVM INSTR tableswitch 1 7: default 112
    //                   1 421
    //                   2 128
    //                   3 551
    //                   4 473
    //                   5 361
    //                   6 551
    //                   7 421;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L4 _L2
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        flag9 = flag3;
        flag8 = flag5;
        flag7 = flag2;
        flag6 = flag4;
_L8:
label0:
        {
            obj = (FragmentContainerTransition)sparsearray.get(i);
            op = ((BackStackRecord.Op) (obj));
            if(flag7)
            {
                op = ensureContainer(((FragmentContainerTransition) (obj)), sparsearray, i);
                op.lastIn = fragment;
                op.lastInIsPop = flag;
                op.lastInTransaction = backstackrecord;
            }
            if(!flag1 && flag8)
            {
                if(op != null && ((FragmentContainerTransition) (op)).firstOut == fragment)
                    op.firstOut = null;
                obj = backstackrecord.mManager;
                if(fragment.mState < 1 && ((FragmentManagerImpl) (obj)).mCurState >= 1 && ((FragmentManagerImpl) (obj)).mHost.getContext().getApplicationInfo().targetSdkVersion >= 24 && backstackrecord.mReorderingAllowed ^ true)
                {
                    ((FragmentManagerImpl) (obj)).makeActive(fragment);
                    ((FragmentManagerImpl) (obj)).moveToState(fragment, 1, 0, 0, false);
                }
            }
            obj = op;
            if(!flag6)
                break label0;
            if(op != null)
            {
                obj = op;
                if(((FragmentContainerTransition) (op)).firstOut != null)
                    break label0;
            }
            obj = ensureContainer(op, sparsearray, i);
            obj.firstOut = fragment;
            obj.firstOutIsPop = flag;
            obj.firstOutTransaction = backstackrecord;
        }
        if(!flag1 && flag9 && obj != null && ((FragmentContainerTransition) (obj)).lastIn == fragment)
            obj.lastIn = null;
        return;
_L6:
        if(flag1)
        {
            if(fragment.mHiddenChanged && fragment.mHidden ^ true)
                flag7 = fragment.mAdded;
            else
                flag7 = false;
        } else
        {
            flag7 = fragment.mHidden;
        }
        flag8 = true;
        flag6 = flag4;
        flag9 = flag3;
        continue; /* Loop/switch isn't completed */
_L2:
        if(flag1)
            flag7 = fragment.mIsNewlyAdded;
        else
        if(!fragment.mAdded)
            flag7 = fragment.mHidden ^ true;
        else
            flag7 = false;
        flag8 = true;
        flag6 = flag4;
        flag9 = flag3;
        continue; /* Loop/switch isn't completed */
_L5:
        if(flag1)
        {
            if(fragment.mHiddenChanged && fragment.mAdded)
                flag7 = fragment.mHidden;
            else
                flag7 = false;
        } else
        if(fragment.mAdded)
            flag7 = fragment.mHidden ^ true;
        else
            flag7 = false;
        flag9 = true;
        flag6 = flag7;
        flag7 = flag2;
        flag8 = flag5;
        continue; /* Loop/switch isn't completed */
_L4:
        if(!flag1)
            break; /* Loop/switch isn't completed */
        if(!fragment.mAdded && fragment.mView != null && fragment.mView.getVisibility() == 0)
        {
            if(fragment.mView.getTransitionAlpha() > 0.0F)
                flag7 = true;
            else
                flag7 = false;
        } else
        {
            flag7 = false;
        }
_L9:
        flag9 = true;
        flag6 = flag7;
        flag7 = flag2;
        flag8 = flag5;
        if(true) goto _L8; else goto _L7
_L7:
        if(fragment.mAdded)
            flag7 = fragment.mHidden ^ true;
        else
            flag7 = false;
          goto _L9
        if(true) goto _L8; else goto _L10
_L10:
    }

    private static void bfsAddViewChildren(List list, View view)
    {
        int i = list.size();
        if(containedBeforeIndex(list, view, i))
            return;
        list.add(view);
        for(int j = i; j < list.size(); j++)
        {
            view = (View)list.get(j);
            if(!(view instanceof ViewGroup))
                continue;
            view = (ViewGroup)view;
            int k = view.getChildCount();
            for(int l = 0; l < k; l++)
            {
                View view1 = view.getChildAt(l);
                if(!containedBeforeIndex(list, view1, i))
                    list.add(view1);
            }

        }

    }

    public static void calculateFragments(BackStackRecord backstackrecord, SparseArray sparsearray, boolean flag)
    {
        int i = backstackrecord.mOps.size();
        for(int j = 0; j < i; j++)
            addToFirstInLastOut(backstackrecord, (BackStackRecord.Op)backstackrecord.mOps.get(j), sparsearray, false, flag);

    }

    private static ArrayMap calculateNameOverrides(int i, ArrayList arraylist, ArrayList arraylist1, int j, int k)
    {
        ArrayMap arraymap = new ArrayMap();
        k--;
        while(k >= j) 
        {
            Object obj = (BackStackRecord)arraylist.get(k);
            if(((BackStackRecord) (obj)).interactsWith(i))
            {
                boolean flag = ((Boolean)arraylist1.get(k)).booleanValue();
                if(((BackStackRecord) (obj)).mSharedElementSourceNames != null)
                {
                    int l = ((BackStackRecord) (obj)).mSharedElementSourceNames.size();
                    ArrayList arraylist2;
                    ArrayList arraylist3;
                    int i1;
                    if(flag)
                    {
                        arraylist2 = ((BackStackRecord) (obj)).mSharedElementSourceNames;
                        arraylist3 = ((BackStackRecord) (obj)).mSharedElementTargetNames;
                    } else
                    {
                        arraylist3 = ((BackStackRecord) (obj)).mSharedElementSourceNames;
                        arraylist2 = ((BackStackRecord) (obj)).mSharedElementTargetNames;
                    }
                    i1 = 0;
                    while(i1 < l) 
                    {
                        String s = (String)arraylist3.get(i1);
                        obj = (String)arraylist2.get(i1);
                        String s1 = (String)arraymap.remove(obj);
                        if(s1 != null)
                            arraymap.put(s, s1);
                        else
                            arraymap.put(s, obj);
                        i1++;
                    }
                }
            }
            k--;
        }
        return arraymap;
    }

    public static void calculatePopFragments(BackStackRecord backstackrecord, SparseArray sparsearray, boolean flag)
    {
        if(!backstackrecord.mManager.mContainer.onHasView())
            return;
        for(int i = backstackrecord.mOps.size() - 1; i >= 0; i--)
            addToFirstInLastOut(backstackrecord, (BackStackRecord.Op)backstackrecord.mOps.get(i), sparsearray, true, flag);

    }

    private static void callSharedElementStartEnd(Fragment fragment, Fragment fragment1, boolean flag, ArrayMap arraymap, boolean flag1)
    {
        if(flag)
            fragment = fragment1.getEnterTransitionCallback();
        else
            fragment = fragment.getEnterTransitionCallback();
        if(fragment != null)
        {
            ArrayList arraylist = new ArrayList();
            fragment1 = new ArrayList();
            int i;
            int j;
            if(arraymap == null)
                i = 0;
            else
                i = arraymap.size();
            for(j = 0; j < i; j++)
            {
                fragment1.add((String)arraymap.keyAt(j));
                arraylist.add((View)arraymap.valueAt(j));
            }

            if(flag1)
                fragment.onSharedElementStart(fragment1, arraylist, null);
            else
                fragment.onSharedElementEnd(fragment1, arraylist, null);
        }
    }

    private static ArrayMap captureInSharedElements(ArrayMap arraymap, TransitionSet transitionset, FragmentContainerTransition fragmentcontainertransition)
    {
        Object obj = fragmentcontainertransition.lastIn;
        View view;
        for(view = ((Fragment) (obj)).getView(); arraymap.isEmpty() || transitionset == null || view == null;)
        {
            arraymap.clear();
            return null;
        }

        ArrayMap arraymap1 = new ArrayMap();
        view.findNamedViews(arraymap1);
        transitionset = fragmentcontainertransition.lastInTransaction;
        if(fragmentcontainertransition.lastInIsPop)
        {
            fragmentcontainertransition = ((Fragment) (obj)).getExitTransitionCallback();
            transitionset = ((BackStackRecord) (transitionset)).mSharedElementSourceNames;
        } else
        {
            fragmentcontainertransition = ((Fragment) (obj)).getEnterTransitionCallback();
            transitionset = ((BackStackRecord) (transitionset)).mSharedElementTargetNames;
        }
        if(transitionset != null)
            arraymap1.retainAll(transitionset);
        if(transitionset != null && fragmentcontainertransition != null)
        {
            fragmentcontainertransition.onMapSharedElements(transitionset, arraymap1);
            int i = transitionset.size() - 1;
            while(i >= 0) 
            {
                obj = (String)transitionset.get(i);
                fragmentcontainertransition = (View)arraymap1.get(obj);
                if(fragmentcontainertransition == null)
                {
                    fragmentcontainertransition = findKeyForValue(arraymap, ((String) (obj)));
                    if(fragmentcontainertransition != null)
                        arraymap.remove(fragmentcontainertransition);
                } else
                if(!((String) (obj)).equals(fragmentcontainertransition.getTransitionName()))
                {
                    obj = findKeyForValue(arraymap, ((String) (obj)));
                    if(obj != null)
                        arraymap.put(obj, fragmentcontainertransition.getTransitionName());
                }
                i--;
            }
        } else
        {
            retainValues(arraymap, arraymap1);
        }
        return arraymap1;
    }

    private static ArrayMap captureOutSharedElements(ArrayMap arraymap, TransitionSet transitionset, FragmentContainerTransition fragmentcontainertransition)
    {
        if(arraymap.isEmpty() || transitionset == null)
        {
            arraymap.clear();
            return null;
        }
        Object obj = fragmentcontainertransition.firstOut;
        ArrayMap arraymap1 = new ArrayMap();
        ((Fragment) (obj)).getView().findNamedViews(arraymap1);
        transitionset = fragmentcontainertransition.firstOutTransaction;
        if(fragmentcontainertransition.firstOutIsPop)
        {
            fragmentcontainertransition = ((Fragment) (obj)).getEnterTransitionCallback();
            transitionset = ((BackStackRecord) (transitionset)).mSharedElementTargetNames;
        } else
        {
            fragmentcontainertransition = ((Fragment) (obj)).getExitTransitionCallback();
            transitionset = ((BackStackRecord) (transitionset)).mSharedElementSourceNames;
        }
        arraymap1.retainAll(transitionset);
        if(fragmentcontainertransition != null)
        {
            fragmentcontainertransition.onMapSharedElements(transitionset, arraymap1);
            int i = transitionset.size() - 1;
            while(i >= 0) 
            {
                obj = (String)transitionset.get(i);
                fragmentcontainertransition = (View)arraymap1.get(obj);
                if(fragmentcontainertransition == null)
                    arraymap.remove(obj);
                else
                if(!((String) (obj)).equals(fragmentcontainertransition.getTransitionName()))
                {
                    obj = (String)arraymap.remove(obj);
                    arraymap.put(fragmentcontainertransition.getTransitionName(), obj);
                }
                i--;
            }
        } else
        {
            arraymap.retainAll(arraymap1.keySet());
        }
        return arraymap1;
    }

    private static Transition cloneTransition(Transition transition)
    {
        Transition transition1 = transition;
        if(transition != null)
            transition1 = transition.clone();
        return transition1;
    }

    private static ArrayList configureEnteringExitingViews(Transition transition, Fragment fragment, ArrayList arraylist, View view)
    {
        ArrayList arraylist1 = null;
        if(transition != null)
        {
            ArrayList arraylist2 = new ArrayList();
            fragment = fragment.getView();
            if(fragment != null)
                fragment.captureTransitioningViews(arraylist2);
            if(arraylist != null)
                arraylist2.removeAll(arraylist);
            arraylist1 = arraylist2;
            if(!arraylist2.isEmpty())
            {
                arraylist2.add(view);
                addTargets(transition, arraylist2);
                arraylist1 = arraylist2;
            }
        }
        return arraylist1;
    }

    private static TransitionSet configureSharedElementsOrdered(ViewGroup viewgroup, View view, ArrayMap arraymap, FragmentContainerTransition fragmentcontainertransition, ArrayList arraylist, ArrayList arraylist1, Transition transition, Transition transition1)
    {
        Fragment fragment = fragmentcontainertransition.lastIn;
        Fragment fragment1 = fragmentcontainertransition.firstOut;
        if(fragment == null || fragment1 == null)
            return null;
        boolean flag = fragmentcontainertransition.lastInIsPop;
        TransitionSet transitionset;
        ArrayMap arraymap1;
        if(arraymap.isEmpty())
            transitionset = null;
        else
            transitionset = getSharedElementTransition(fragment, fragment1, flag);
        arraymap1 = captureOutSharedElements(arraymap, transitionset, fragmentcontainertransition);
        if(arraymap.isEmpty())
            transitionset = null;
        else
            arraylist.addAll(arraymap1.values());
        if(transition == null && transition1 == null && transitionset == null)
            return null;
        callSharedElementStartEnd(fragment, fragment1, flag, arraymap1, true);
        if(transitionset != null)
        {
            Rect rect = new Rect();
            setSharedElementTargets(transitionset, view, arraylist);
            setOutEpicenter(transitionset, transition1, arraymap1, fragmentcontainertransition.firstOutIsPop, fragmentcontainertransition.firstOutTransaction);
            transition1 = rect;
            if(transition != null)
            {
                transition.setEpicenterCallback(new android.transition.Transition.EpicenterCallback(rect) {

                    public Rect onGetEpicenter(Transition transition2)
                    {
                        if(inEpicenter.isEmpty())
                            return null;
                        else
                            return inEpicenter;
                    }

                    final Rect val$inEpicenter;

            
            {
                inEpicenter = rect;
                super();
            }
                }
);
                transition1 = rect;
            }
        } else
        {
            transition1 = null;
        }
        OneShotPreDrawListener.add(viewgroup, new _.Lambda._cls3eJ3p8XnIxdVOnT82Ns3R0V5ZQE._cls2(flag, arraymap, transitionset, fragmentcontainertransition, arraylist1, view, fragment, fragment1, arraylist, transition, transition1));
        return transitionset;
    }

    private static TransitionSet configureSharedElementsReordered(ViewGroup viewgroup, View view, ArrayMap arraymap, FragmentContainerTransition fragmentcontainertransition, ArrayList arraylist, ArrayList arraylist1, Transition transition, Transition transition1)
    {
        Fragment fragment = fragmentcontainertransition.lastIn;
        Fragment fragment1 = fragmentcontainertransition.firstOut;
        if(fragment != null)
            fragment.getView().setVisibility(0);
        if(fragment == null || fragment1 == null)
            return null;
        boolean flag = fragmentcontainertransition.lastInIsPop;
        Object obj;
        ArrayMap arraymap1;
        ArrayMap arraymap2;
        if(arraymap.isEmpty())
            obj = null;
        else
            obj = getSharedElementTransition(fragment, fragment1, flag);
        arraymap1 = captureOutSharedElements(arraymap, ((TransitionSet) (obj)), fragmentcontainertransition);
        arraymap2 = captureInSharedElements(arraymap, ((TransitionSet) (obj)), fragmentcontainertransition);
        if(arraymap.isEmpty())
        {
            arraymap = null;
            if(arraymap1 != null)
                arraymap1.clear();
            obj = arraymap;
            if(arraymap2 != null)
            {
                arraymap2.clear();
                obj = arraymap;
            }
        } else
        {
            addSharedElementsWithMatchingNames(arraylist, arraymap1, arraymap.keySet());
            addSharedElementsWithMatchingNames(arraylist1, arraymap2, arraymap.values());
        }
        if(transition == null && transition1 == null && obj == null)
            return null;
        callSharedElementStartEnd(fragment, fragment1, flag, arraymap1, true);
        if(obj != null)
        {
            arraylist1.add(view);
            setSharedElementTargets(((TransitionSet) (obj)), view, arraylist);
            setOutEpicenter(((TransitionSet) (obj)), transition1, arraymap1, fragmentcontainertransition.firstOutIsPop, fragmentcontainertransition.firstOutTransaction);
            arraylist = new Rect();
            fragmentcontainertransition = getInEpicenterView(arraymap2, fragmentcontainertransition, transition, flag);
            view = fragmentcontainertransition;
            arraymap = arraylist;
            if(fragmentcontainertransition != null)
            {
                transition.setEpicenterCallback(new android.transition.Transition.EpicenterCallback(arraylist) {

                    public Rect onGetEpicenter(Transition transition2)
                    {
                        return epicenter;
                    }

                    final Rect val$epicenter;

            
            {
                epicenter = rect;
                super();
            }
                }
);
                arraymap = arraylist;
                view = fragmentcontainertransition;
            }
        } else
        {
            arraymap = null;
            view = null;
        }
        OneShotPreDrawListener.add(viewgroup, new _.Lambda._cls3eJ3p8XnIxdVOnT82Ns3R0V5ZQE._cls1(flag, fragment, fragment1, arraymap2, view, arraymap));
        return ((TransitionSet) (obj));
    }

    private static void configureTransitionsOrdered(FragmentManagerImpl fragmentmanagerimpl, int i, FragmentContainerTransition fragmentcontainertransition, View view, ArrayMap arraymap)
    {
        ViewGroup viewgroup = null;
        if(fragmentmanagerimpl.mContainer.onHasView())
            viewgroup = (ViewGroup)fragmentmanagerimpl.mContainer.onFindViewById(i);
        if(viewgroup == null)
            return;
        Fragment fragment = fragmentcontainertransition.lastIn;
        Object obj = fragmentcontainertransition.firstOut;
        boolean flag = fragmentcontainertransition.lastInIsPop;
        boolean flag1 = fragmentcontainertransition.firstOutIsPop;
        Transition transition = getEnterTransition(fragment, flag);
        fragmentmanagerimpl = getExitTransition(((Fragment) (obj)), flag1);
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        TransitionSet transitionset = configureSharedElementsOrdered(viewgroup, view, arraymap, fragmentcontainertransition, arraylist, arraylist1, transition, fragmentmanagerimpl);
        if(transition == null && transitionset == null && fragmentmanagerimpl == null)
            return;
        obj = configureEnteringExitingViews(fragmentmanagerimpl, ((Fragment) (obj)), arraylist, view);
        if(obj == null || ((ArrayList) (obj)).isEmpty())
            fragmentmanagerimpl = null;
        if(transition != null)
            transition.addTarget(view);
        fragmentcontainertransition = mergeTransitions(transition, fragmentmanagerimpl, transitionset, fragment, fragmentcontainertransition.lastInIsPop);
        if(fragmentcontainertransition != null)
        {
            fragmentcontainertransition.setNameOverrides(arraymap);
            arraymap = new ArrayList();
            scheduleRemoveTargets(fragmentcontainertransition, transition, arraymap, fragmentmanagerimpl, ((ArrayList) (obj)), transitionset, arraylist1);
            scheduleTargetChange(viewgroup, fragment, view, arraylist1, transition, arraymap, fragmentmanagerimpl, ((ArrayList) (obj)));
            TransitionManager.beginDelayedTransition(viewgroup, fragmentcontainertransition);
        }
    }

    private static void configureTransitionsReordered(FragmentManagerImpl fragmentmanagerimpl, int i, FragmentContainerTransition fragmentcontainertransition, View view, ArrayMap arraymap)
    {
        ViewGroup viewgroup = null;
        if(fragmentmanagerimpl.mContainer.onHasView())
            viewgroup = (ViewGroup)fragmentmanagerimpl.mContainer.onFindViewById(i);
        if(viewgroup == null)
            return;
        Object obj = fragmentcontainertransition.lastIn;
        fragmentmanagerimpl = fragmentcontainertransition.firstOut;
        boolean flag = fragmentcontainertransition.lastInIsPop;
        boolean flag1 = fragmentcontainertransition.firstOutIsPop;
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        Transition transition = getEnterTransition(((Fragment) (obj)), flag);
        Transition transition1 = getExitTransition(fragmentmanagerimpl, flag1);
        TransitionSet transitionset = configureSharedElementsReordered(viewgroup, view, arraymap, fragmentcontainertransition, arraylist1, arraylist, transition, transition1);
        if(transition == null && transitionset == null && transition1 == null)
            return;
        fragmentcontainertransition = configureEnteringExitingViews(transition1, fragmentmanagerimpl, arraylist1, view);
        view = configureEnteringExitingViews(transition, ((Fragment) (obj)), arraylist, view);
        setViewVisibility(view, 4);
        obj = mergeTransitions(transition, transition1, transitionset, ((Fragment) (obj)), flag);
        if(obj != null)
        {
            replaceHide(transition1, fragmentmanagerimpl, fragmentcontainertransition);
            ((Transition) (obj)).setNameOverrides(arraymap);
            scheduleRemoveTargets(((Transition) (obj)), transition, view, transition1, fragmentcontainertransition, transitionset, arraylist);
            TransitionManager.beginDelayedTransition(viewgroup, ((Transition) (obj)));
            setViewVisibility(view, 0);
            if(transitionset != null)
            {
                transitionset.getTargets().clear();
                transitionset.getTargets().addAll(arraylist);
                replaceTargets(transitionset, arraylist1, arraylist);
            }
        }
    }

    private static boolean containedBeforeIndex(List list, View view, int i)
    {
        for(int j = 0; j < i; j++)
            if(list.get(j) == view)
                return true;

        return false;
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition fragmentcontainertransition, SparseArray sparsearray, int i)
    {
        FragmentContainerTransition fragmentcontainertransition1 = fragmentcontainertransition;
        if(fragmentcontainertransition == null)
        {
            fragmentcontainertransition1 = new FragmentContainerTransition();
            sparsearray.put(i, fragmentcontainertransition1);
        }
        return fragmentcontainertransition1;
    }

    private static String findKeyForValue(ArrayMap arraymap, String s)
    {
        int i = arraymap.size();
        for(int j = 0; j < i; j++)
            if(s.equals(arraymap.valueAt(j)))
                return (String)arraymap.keyAt(j);

        return null;
    }

    private static Transition getEnterTransition(Fragment fragment, boolean flag)
    {
        if(fragment == null)
            return null;
        if(flag)
            fragment = fragment.getReenterTransition();
        else
            fragment = fragment.getEnterTransition();
        return cloneTransition(fragment);
    }

    private static Transition getExitTransition(Fragment fragment, boolean flag)
    {
        if(fragment == null)
            return null;
        if(flag)
            fragment = fragment.getReturnTransition();
        else
            fragment = fragment.getExitTransition();
        return cloneTransition(fragment);
    }

    private static View getInEpicenterView(ArrayMap arraymap, FragmentContainerTransition fragmentcontainertransition, Transition transition, boolean flag)
    {
        fragmentcontainertransition = fragmentcontainertransition.lastInTransaction;
        if(transition != null && arraymap != null && ((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames != null && ((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames.isEmpty() ^ true)
        {
            if(flag)
                fragmentcontainertransition = (String)((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames.get(0);
            else
                fragmentcontainertransition = (String)((BackStackRecord) (fragmentcontainertransition)).mSharedElementTargetNames.get(0);
            return (View)arraymap.get(fragmentcontainertransition);
        } else
        {
            return null;
        }
    }

    private static TransitionSet getSharedElementTransition(Fragment fragment, Fragment fragment1, boolean flag)
    {
        if(fragment == null || fragment1 == null)
            return null;
        if(flag)
            fragment = fragment1.getSharedElementReturnTransition();
        else
            fragment = fragment.getSharedElementEnterTransition();
        fragment = cloneTransition(fragment);
        if(fragment == null)
        {
            return null;
        } else
        {
            fragment1 = new TransitionSet();
            fragment1.addTransition(fragment);
            return fragment1;
        }
    }

    private static boolean hasSimpleTarget(Transition transition)
    {
        boolean flag;
        if(isNullOrEmpty(transition.getTargetIds()) && !(isNullOrEmpty(transition.getTargetNames()) ^ true))
            flag = isNullOrEmpty(transition.getTargetTypes()) ^ true;
        else
            flag = true;
        return flag;
    }

    private static boolean isNullOrEmpty(List list)
    {
        boolean flag;
        if(list != null)
            flag = list.isEmpty();
        else
            flag = true;
        return flag;
    }

    static void lambda$_2D_android_app_FragmentTransition_16724(ArrayList arraylist)
    {
        setViewVisibility(arraylist, 4);
    }

    static void lambda$_2D_android_app_FragmentTransition_18686(Transition transition, View view, Fragment fragment, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, Transition transition1)
    {
        if(transition != null)
        {
            transition.removeTarget(view);
            arraylist1.addAll(configureEnteringExitingViews(transition, fragment, arraylist, view));
        }
        if(arraylist2 != null)
        {
            if(transition1 != null)
            {
                transition = new ArrayList();
                transition.add(view);
                replaceTargets(transition1, arraylist2, transition);
            }
            arraylist2.clear();
            arraylist2.add(view);
        }
    }

    static void lambda$_2D_android_app_FragmentTransition_26843(Fragment fragment, Fragment fragment1, boolean flag, ArrayMap arraymap, View view, Rect rect)
    {
        callSharedElementStartEnd(fragment, fragment1, flag, arraymap, false);
        if(view != null)
            view.getBoundsOnScreen(rect);
    }

    static void lambda$_2D_android_app_FragmentTransition_32404(ArrayMap arraymap, TransitionSet transitionset, FragmentContainerTransition fragmentcontainertransition, ArrayList arraylist, View view, Fragment fragment, Fragment fragment1, boolean flag, 
            ArrayList arraylist1, Transition transition, Rect rect)
    {
        arraymap = captureInSharedElements(arraymap, transitionset, fragmentcontainertransition);
        if(arraymap != null)
        {
            arraylist.addAll(arraymap.values());
            arraylist.add(view);
        }
        callSharedElementStartEnd(fragment, fragment1, flag, arraymap, false);
        if(transitionset != null)
        {
            transitionset.getTargets().clear();
            transitionset.getTargets().addAll(arraylist);
            replaceTargets(transitionset, arraylist1, arraylist);
            arraymap = getInEpicenterView(arraymap, fragmentcontainertransition, transition, flag);
            if(arraymap != null)
                arraymap.getBoundsOnScreen(rect);
        }
    }

    private static Transition mergeTransitions(Transition transition, Transition transition1, Transition transition2, Fragment fragment, boolean flag)
    {
        boolean flag1 = true;
        boolean flag2 = flag1;
        if(transition != null)
        {
            flag2 = flag1;
            if(transition1 != null)
            {
                flag2 = flag1;
                if(fragment != null)
                    if(flag)
                        flag2 = fragment.getAllowReturnTransitionOverlap();
                    else
                        flag2 = fragment.getAllowEnterTransitionOverlap();
            }
        }
        if(!flag2) goto _L2; else goto _L1
_L1:
        fragment = new TransitionSet();
        if(transition != null)
            fragment.addTransition(transition);
        if(transition1 != null)
            fragment.addTransition(transition1);
        if(transition2 != null)
            fragment.addTransition(transition2);
        transition = fragment;
_L5:
        return transition;
_L2:
        fragment = null;
        if(transition1 == null || transition == null) goto _L4; else goto _L3
_L3:
        transition1 = (new TransitionSet()).addTransition(transition1).addTransition(transition).setOrdering(1);
_L6:
        if(transition2 != null)
        {
            transition = new TransitionSet();
            if(transition1 != null)
                transition.addTransition(transition1);
            transition.addTransition(transition2);
        } else
        {
            transition = transition1;
        }
        if(true) goto _L5; else goto _L4
_L4:
        if(transition1 == null)
        {
            transition1 = fragment;
            if(transition != null)
                transition1 = transition;
        }
          goto _L6
    }

    private static void replaceHide(Transition transition, Fragment fragment, ArrayList arraylist)
    {
        if(fragment != null && transition != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged)
        {
            fragment.setHideReplaced(true);
            View view = fragment.getView();
            OneShotPreDrawListener.add(fragment.mContainer, new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)3, arraylist));
            transition.addListener(new TransitionListenerAdapter(view, arraylist) {

                public void onTransitionEnd(Transition transition1)
                {
                    transition1.removeListener(this);
                    fragmentView.setVisibility(8);
                    FragmentTransition._2D_wrap0(exitingViews, 0);
                }

                final ArrayList val$exitingViews;
                final View val$fragmentView;

            
            {
                fragmentView = view;
                exitingViews = arraylist;
                super();
            }
            }
);
        }
    }

    public static void replaceTargets(Transition transition, ArrayList arraylist, ArrayList arraylist1)
    {
        if(transition instanceof TransitionSet)
        {
            transition = (TransitionSet)transition;
            int i = transition.getTransitionCount();
            for(int k = 0; k < i; k++)
                replaceTargets(transition.getTransitionAt(k), arraylist, arraylist1);

        } else
        if(!hasSimpleTarget(transition))
        {
            List list = transition.getTargets();
            if(list != null && list.size() == arraylist.size() && list.containsAll(arraylist))
            {
                int j;
                int l;
                if(arraylist1 == null)
                    l = 0;
                else
                    l = arraylist1.size();
                for(j = 0; j < l; j++)
                    transition.addTarget((View)arraylist1.get(j));

                for(int i1 = arraylist.size() - 1; i1 >= 0; i1--)
                    transition.removeTarget((View)arraylist.get(i1));

            }
        }
    }

    private static void retainValues(ArrayMap arraymap, ArrayMap arraymap1)
    {
        for(int i = arraymap.size() - 1; i >= 0; i--)
            if(!arraymap1.containsKey((String)arraymap.valueAt(i)))
                arraymap.removeAt(i);

    }

    private static void scheduleRemoveTargets(Transition transition, Transition transition1, ArrayList arraylist, Transition transition2, ArrayList arraylist1, TransitionSet transitionset, ArrayList arraylist2)
    {
        transition.addListener(new TransitionListenerAdapter(transition1, arraylist, transition2, arraylist1, transitionset, arraylist2) {

            public void onTransitionStart(Transition transition3)
            {
                if(enterTransition != null)
                    FragmentTransition.replaceTargets(enterTransition, enteringViews, null);
                if(exitTransition != null)
                    FragmentTransition.replaceTargets(exitTransition, exitingViews, null);
                if(sharedElementTransition != null)
                    FragmentTransition.replaceTargets(sharedElementTransition, sharedElementsIn, null);
            }

            final Transition val$enterTransition;
            final ArrayList val$enteringViews;
            final Transition val$exitTransition;
            final ArrayList val$exitingViews;
            final TransitionSet val$sharedElementTransition;
            final ArrayList val$sharedElementsIn;

            
            {
                enterTransition = transition;
                enteringViews = arraylist;
                exitTransition = transition1;
                exitingViews = arraylist1;
                sharedElementTransition = transitionset;
                sharedElementsIn = arraylist2;
                super();
            }
        }
);
    }

    private static void scheduleTargetChange(ViewGroup viewgroup, Fragment fragment, View view, ArrayList arraylist, Transition transition, ArrayList arraylist1, Transition transition1, ArrayList arraylist2)
    {
        OneShotPreDrawListener.add(viewgroup, new _.Lambda._cls3eJ3p8XnIxdVOnT82Ns3R0V5ZQE(transition, view, fragment, arraylist, arraylist1, arraylist2, transition1));
    }

    private static void setEpicenter(Transition transition, View view)
    {
        if(view != null)
        {
            Rect rect = new Rect();
            view.getBoundsOnScreen(rect);
            transition.setEpicenterCallback(new android.transition.Transition.EpicenterCallback(rect) {

                public Rect onGetEpicenter(Transition transition1)
                {
                    return epicenter;
                }

                final Rect val$epicenter;

            
            {
                epicenter = rect;
                super();
            }
            }
);
        }
    }

    private static void setOutEpicenter(TransitionSet transitionset, Transition transition, ArrayMap arraymap, boolean flag, BackStackRecord backstackrecord)
    {
        if(backstackrecord.mSharedElementSourceNames != null && backstackrecord.mSharedElementSourceNames.isEmpty() ^ true)
        {
            if(flag)
                backstackrecord = (String)backstackrecord.mSharedElementTargetNames.get(0);
            else
                backstackrecord = (String)backstackrecord.mSharedElementSourceNames.get(0);
            arraymap = (View)arraymap.get(backstackrecord);
            setEpicenter(transitionset, arraymap);
            if(transition != null)
                setEpicenter(transition, arraymap);
        }
    }

    private static void setSharedElementTargets(TransitionSet transitionset, View view, ArrayList arraylist)
    {
        List list = transitionset.getTargets();
        list.clear();
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            bfsAddViewChildren(list, (View)arraylist.get(j));

        list.add(view);
        arraylist.add(view);
        addTargets(transitionset, arraylist);
    }

    private static void setViewVisibility(ArrayList arraylist, int i)
    {
        if(arraylist == null)
            return;
        for(int j = arraylist.size() - 1; j >= 0; j--)
            ((View)arraylist.get(j)).setVisibility(i);

    }

    static void startTransitions(FragmentManagerImpl fragmentmanagerimpl, ArrayList arraylist, ArrayList arraylist1, int i, int j, boolean flag)
    {
        if(fragmentmanagerimpl.mCurState < 1)
            return;
        SparseArray sparsearray = new SparseArray();
        int k = i;
        while(k < j) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(k);
            if(((Boolean)arraylist1.get(k)).booleanValue())
                calculatePopFragments(backstackrecord, sparsearray, flag);
            else
                calculateFragments(backstackrecord, sparsearray, flag);
            k++;
        }
        if(sparsearray.size() != 0)
        {
            View view = new View(fragmentmanagerimpl.mHost.getContext());
            int i1 = sparsearray.size();
            int l = 0;
            while(l < i1) 
            {
                int j1 = sparsearray.keyAt(l);
                ArrayMap arraymap = calculateNameOverrides(j1, arraylist, arraylist1, i, j);
                FragmentContainerTransition fragmentcontainertransition = (FragmentContainerTransition)sparsearray.valueAt(l);
                if(flag)
                    configureTransitionsReordered(fragmentmanagerimpl, j1, fragmentcontainertransition, view, arraymap);
                else
                    configureTransitionsOrdered(fragmentmanagerimpl, j1, fragmentcontainertransition, view, arraymap);
                l++;
            }
        }
    }

    private static final int INVERSE_OPS[] = {
        0, 3, 0, 1, 5, 4, 7, 6, 9, 8
    };

}
