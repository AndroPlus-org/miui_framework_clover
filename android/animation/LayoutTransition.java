// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.view.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.*;

// Referenced classes of package android.animation:
//            PropertyValuesHolder, ObjectAnimator, Animator, ValueAnimator, 
//            TimeInterpolator, AnimatorListenerAdapter, KeyframeSet, Keyframe, 
//            Keyframes

public class LayoutTransition
{
    private static final class CleanupCallback
        implements android.view.ViewTreeObserver.OnPreDrawListener, android.view.View.OnAttachStateChangeListener
    {

        private void cleanup()
        {
            parent.getViewTreeObserver().removeOnPreDrawListener(this);
            parent.removeOnAttachStateChangeListener(this);
            if(layoutChangeListenerMap.size() > 0)
            {
                View view;
                for(Iterator iterator = layoutChangeListenerMap.keySet().iterator(); iterator.hasNext(); view.removeOnLayoutChangeListener((android.view.View.OnLayoutChangeListener)layoutChangeListenerMap.get(view)))
                    view = (View)iterator.next();

                layoutChangeListenerMap.clear();
            }
        }

        public boolean onPreDraw()
        {
            cleanup();
            return true;
        }

        public void onViewAttachedToWindow(View view)
        {
        }

        public void onViewDetachedFromWindow(View view)
        {
            cleanup();
        }

        final Map layoutChangeListenerMap;
        final ViewGroup parent;

        CleanupCallback(Map map, ViewGroup viewgroup)
        {
            layoutChangeListenerMap = map;
            parent = viewgroup;
        }
    }

    public static interface TransitionListener
    {

        public abstract void endTransition(LayoutTransition layouttransition, ViewGroup viewgroup, View view, int i);

        public abstract void startTransition(LayoutTransition layouttransition, ViewGroup viewgroup, View view, int i);
    }


    static LinkedHashMap _2D_get0(LayoutTransition layouttransition)
    {
        return layouttransition.currentAppearingAnimations;
    }

    static LinkedHashMap _2D_get1(LayoutTransition layouttransition)
    {
        return layouttransition.currentChangingAnimations;
    }

    static long _2D_get10(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingDisappearingStagger;
    }

    static TimeInterpolator _2D_get11(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingInterpolator;
    }

    static long _2D_get12(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingStagger;
    }

    static ArrayList _2D_get13(LayoutTransition layouttransition)
    {
        return layouttransition.mListeners;
    }

    static HashMap _2D_get14(LayoutTransition layouttransition)
    {
        return layouttransition.pendingAnimations;
    }

    static TimeInterpolator _2D_get15()
    {
        return sChangingAppearingInterpolator;
    }

    static TimeInterpolator _2D_get16()
    {
        return sChangingDisappearingInterpolator;
    }

    static TimeInterpolator _2D_get17()
    {
        return sChangingInterpolator;
    }

    static long _2D_get18(LayoutTransition layouttransition)
    {
        return layouttransition.staggerDelay;
    }

    static LinkedHashMap _2D_get2(LayoutTransition layouttransition)
    {
        return layouttransition.currentDisappearingAnimations;
    }

    static HashMap _2D_get3(LayoutTransition layouttransition)
    {
        return layouttransition.layoutChangeListenerMap;
    }

    static long _2D_get4(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingAppearingDelay;
    }

    static TimeInterpolator _2D_get5(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingAppearingInterpolator;
    }

    static long _2D_get6(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingAppearingStagger;
    }

    static long _2D_get7(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingDelay;
    }

    static long _2D_get8(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingDisappearingDelay;
    }

    static TimeInterpolator _2D_get9(LayoutTransition layouttransition)
    {
        return layouttransition.mChangingDisappearingInterpolator;
    }

    static long _2D_set0(LayoutTransition layouttransition, long l)
    {
        layouttransition.staggerDelay = l;
        return l;
    }

    static boolean _2D_wrap0(LayoutTransition layouttransition)
    {
        return layouttransition.hasListeners();
    }

    public LayoutTransition()
    {
        mDisappearingAnim = null;
        mAppearingAnim = null;
        mChangingAppearingAnim = null;
        mChangingDisappearingAnim = null;
        mChangingAnim = null;
        mChangingAppearingDuration = DEFAULT_DURATION;
        mChangingDisappearingDuration = DEFAULT_DURATION;
        mChangingDuration = DEFAULT_DURATION;
        mAppearingDuration = DEFAULT_DURATION;
        mDisappearingDuration = DEFAULT_DURATION;
        mAppearingDelay = DEFAULT_DURATION;
        mDisappearingDelay = 0L;
        mChangingAppearingDelay = 0L;
        mChangingDisappearingDelay = DEFAULT_DURATION;
        mChangingDelay = 0L;
        mChangingAppearingStagger = 0L;
        mChangingDisappearingStagger = 0L;
        mChangingStagger = 0L;
        mAppearingInterpolator = sAppearingInterpolator;
        mDisappearingInterpolator = sDisappearingInterpolator;
        mChangingAppearingInterpolator = sChangingAppearingInterpolator;
        mChangingDisappearingInterpolator = sChangingDisappearingInterpolator;
        mChangingInterpolator = sChangingInterpolator;
        mTransitionTypes = 15;
        mAnimateParentHierarchy = true;
        if(defaultChangeIn == null)
        {
            PropertyValuesHolder propertyvaluesholder = PropertyValuesHolder.ofInt("left", new int[] {
                0, 1
            });
            PropertyValuesHolder propertyvaluesholder1 = PropertyValuesHolder.ofInt("top", new int[] {
                0, 1
            });
            PropertyValuesHolder propertyvaluesholder2 = PropertyValuesHolder.ofInt("right", new int[] {
                0, 1
            });
            PropertyValuesHolder propertyvaluesholder3 = PropertyValuesHolder.ofInt("bottom", new int[] {
                0, 1
            });
            PropertyValuesHolder propertyvaluesholder4 = PropertyValuesHolder.ofInt("scrollX", new int[] {
                0, 1
            });
            PropertyValuesHolder propertyvaluesholder5 = PropertyValuesHolder.ofInt("scrollY", new int[] {
                0, 1
            });
            defaultChangeIn = ObjectAnimator.ofPropertyValuesHolder((Object)null, new PropertyValuesHolder[] {
                propertyvaluesholder, propertyvaluesholder1, propertyvaluesholder2, propertyvaluesholder3, propertyvaluesholder4, propertyvaluesholder5
            });
            defaultChangeIn.setDuration(DEFAULT_DURATION);
            defaultChangeIn.setStartDelay(mChangingAppearingDelay);
            defaultChangeIn.setInterpolator(mChangingAppearingInterpolator);
            defaultChangeOut = defaultChangeIn.clone();
            defaultChangeOut.setStartDelay(mChangingDisappearingDelay);
            defaultChangeOut.setInterpolator(mChangingDisappearingInterpolator);
            defaultChange = defaultChangeIn.clone();
            defaultChange.setStartDelay(mChangingDelay);
            defaultChange.setInterpolator(mChangingInterpolator);
            defaultFadeIn = ObjectAnimator.ofFloat(null, "alpha", new float[] {
                0.0F, 1.0F
            });
            defaultFadeIn.setDuration(DEFAULT_DURATION);
            defaultFadeIn.setStartDelay(mAppearingDelay);
            defaultFadeIn.setInterpolator(mAppearingInterpolator);
            defaultFadeOut = ObjectAnimator.ofFloat(null, "alpha", new float[] {
                1.0F, 0.0F
            });
            defaultFadeOut.setDuration(DEFAULT_DURATION);
            defaultFadeOut.setStartDelay(mDisappearingDelay);
            defaultFadeOut.setInterpolator(mDisappearingInterpolator);
        }
        mChangingAppearingAnim = defaultChangeIn;
        mChangingDisappearingAnim = defaultChangeOut;
        mChangingAnim = defaultChange;
        mAppearingAnim = defaultFadeIn;
        mDisappearingAnim = defaultFadeOut;
    }

    private void addChild(ViewGroup viewgroup, View view, boolean flag)
    {
        if(viewgroup.getWindowVisibility() != 0)
            return;
        if((mTransitionTypes & 1) == 1)
            cancel(3);
        if(flag && (mTransitionTypes & 4) == 4)
        {
            cancel(0);
            cancel(4);
        }
        if(hasListeners() && (mTransitionTypes & 1) == 1)
        {
            for(Iterator iterator = ((ArrayList)mListeners.clone()).iterator(); iterator.hasNext(); ((TransitionListener)iterator.next()).startTransition(this, viewgroup, view, 2));
        }
        if(flag && (mTransitionTypes & 4) == 4)
            runChangeTransition(viewgroup, view, 2);
        if((mTransitionTypes & 1) == 1)
            runAppearingTransition(viewgroup, view);
    }

    private boolean hasListeners()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mListeners != null)
        {
            flag1 = flag;
            if(mListeners.size() > 0)
                flag1 = true;
        }
        return flag1;
    }

    private void removeChild(ViewGroup viewgroup, View view, boolean flag)
    {
        if(viewgroup.getWindowVisibility() != 0)
            return;
        if((mTransitionTypes & 2) == 2)
            cancel(2);
        if(flag && (mTransitionTypes & 8) == 8)
        {
            cancel(1);
            cancel(4);
        }
        if(hasListeners() && (mTransitionTypes & 2) == 2)
        {
            for(Iterator iterator = ((ArrayList)mListeners.clone()).iterator(); iterator.hasNext(); ((TransitionListener)iterator.next()).startTransition(this, viewgroup, view, 3));
        }
        if(flag && (mTransitionTypes & 8) == 8)
            runChangeTransition(viewgroup, view, 3);
        if((mTransitionTypes & 2) == 2)
            runDisappearingTransition(viewgroup, view);
    }

    private void runAppearingTransition(final ViewGroup parent, final View child)
    {
        Object obj = (Animator)currentDisappearingAnimations.get(child);
        if(obj != null)
            ((Animator) (obj)).cancel();
        if(mAppearingAnim == null)
        {
            if(hasListeners())
                for(obj = ((ArrayList)mListeners.clone()).iterator(); ((Iterator) (obj)).hasNext(); ((TransitionListener)((Iterator) (obj)).next()).endTransition(this, parent, child, 2));
            return;
        }
        obj = mAppearingAnim.clone();
        ((Animator) (obj)).setTarget(child);
        ((Animator) (obj)).setStartDelay(mAppearingDelay);
        ((Animator) (obj)).setDuration(mAppearingDuration);
        if(mAppearingInterpolator != sAppearingInterpolator)
            ((Animator) (obj)).setInterpolator(mAppearingInterpolator);
        if(obj instanceof ObjectAnimator)
            ((ObjectAnimator)obj).setCurrentPlayTime(0L);
        ((Animator) (obj)).addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                LayoutTransition._2D_get0(LayoutTransition.this).remove(child);
                if(LayoutTransition._2D_wrap0(LayoutTransition.this))
                    for(animator = ((ArrayList)LayoutTransition._2D_get13(LayoutTransition.this).clone()).iterator(); animator.hasNext(); ((TransitionListener)animator.next()).endTransition(LayoutTransition.this, parent, child, 2));
            }

            final LayoutTransition this$0;
            final View val$child;
            final ViewGroup val$parent;

            
            {
                this$0 = LayoutTransition.this;
                child = view;
                parent = viewgroup;
                super();
            }
        }
);
        currentAppearingAnimations.put(child, obj);
        ((Animator) (obj)).start();
    }

    private void runChangeTransition(ViewGroup viewgroup, View view, int i)
    {
        Animator animator;
        ObjectAnimator objectanimator;
        animator = null;
        objectanimator = null;
        i;
        JVM INSTR tableswitch 2 4: default 32
    //                   2 41
    //                   3 61
    //                   4 81;
           goto _L1 _L2 _L3 _L4
_L1:
        long l = 0L;
_L6:
        if(animator == null)
            return;
        break; /* Loop/switch isn't completed */
_L2:
        animator = mChangingAppearingAnim;
        l = mChangingAppearingDuration;
        objectanimator = defaultChangeIn;
        continue; /* Loop/switch isn't completed */
_L3:
        animator = mChangingDisappearingAnim;
        l = mChangingDisappearingDuration;
        objectanimator = defaultChangeOut;
        continue; /* Loop/switch isn't completed */
_L4:
        animator = mChangingAnim;
        l = mChangingDuration;
        objectanimator = defaultChange;
        if(true) goto _L6; else goto _L5
_L5:
        staggerDelay = 0L;
        ViewTreeObserver viewtreeobserver = viewgroup.getViewTreeObserver();
        if(!viewtreeobserver.isAlive())
            return;
        int j = viewgroup.getChildCount();
        for(int k = 0; k < j; k++)
        {
            View view1 = viewgroup.getChildAt(k);
            if(view1 != view)
                setupChangeAnimation(viewgroup, i, animator, l, view1);
        }

        if(mAnimateParentHierarchy)
            for(view = viewgroup; view != null;)
            {
                android.view.ViewParent viewparent = view.getParent();
                if(viewparent instanceof ViewGroup)
                {
                    setupChangeAnimation((ViewGroup)viewparent, i, objectanimator, l, view);
                    view = (ViewGroup)viewparent;
                } else
                {
                    view = null;
                }
            }

        view = new CleanupCallback(layoutChangeListenerMap, viewgroup);
        viewtreeobserver.addOnPreDrawListener(view);
        viewgroup.addOnAttachStateChangeListener(view);
        return;
    }

    private void runDisappearingTransition(ViewGroup viewgroup, final View child)
    {
        Object obj = (Animator)currentAppearingAnimations.get(child);
        if(obj != null)
            ((Animator) (obj)).cancel();
        if(mDisappearingAnim == null)
        {
            if(hasListeners())
                for(obj = ((ArrayList)mListeners.clone()).iterator(); ((Iterator) (obj)).hasNext(); ((TransitionListener)((Iterator) (obj)).next()).endTransition(this, viewgroup, child, 3));
            return;
        }
        obj = mDisappearingAnim.clone();
        ((Animator) (obj)).setStartDelay(mDisappearingDelay);
        ((Animator) (obj)).setDuration(mDisappearingDuration);
        if(mDisappearingInterpolator != sDisappearingInterpolator)
            ((Animator) (obj)).setInterpolator(mDisappearingInterpolator);
        ((Animator) (obj)).setTarget(child);
        ((Animator) (obj)).addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                LayoutTransition._2D_get2(LayoutTransition.this).remove(child);
                child.setAlpha(preAnimAlpha);
                if(LayoutTransition._2D_wrap0(LayoutTransition.this))
                    for(animator = ((ArrayList)LayoutTransition._2D_get13(LayoutTransition.this).clone()).iterator(); animator.hasNext(); ((TransitionListener)animator.next()).endTransition(LayoutTransition.this, parent, child, 3));
            }

            final LayoutTransition this$0;
            final View val$child;
            final ViewGroup val$parent;
            final float val$preAnimAlpha;

            
            {
                this$0 = LayoutTransition.this;
                child = view;
                preAnimAlpha = f;
                parent = viewgroup;
                super();
            }
        }
);
        if(obj instanceof ObjectAnimator)
            ((ObjectAnimator)obj).setCurrentPlayTime(0L);
        currentDisappearingAnimations.put(child, obj);
        ((Animator) (obj)).start();
    }

    private void setupChangeAnimation(final ViewGroup parent, final int changeReason, final Animator anim, final long duration, final View child)
    {
        if(layoutChangeListenerMap.get(child) != null)
            return;
        if(child.getWidth() == 0 && child.getHeight() == 0)
            return;
        anim = anim.clone();
        anim.setTarget(child);
        anim.setupStartValues();
        final android.view.View.OnLayoutChangeListener listener = (Animator)pendingAnimations.get(child);
        if(listener != null)
        {
            listener.cancel();
            pendingAnimations.remove(child);
        }
        pendingAnimations.put(child, anim);
        listener = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        }).setDuration(100L + duration);
        listener.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                LayoutTransition._2D_get14(LayoutTransition.this).remove(child);
            }

            final LayoutTransition this$0;
            final View val$child;

            
            {
                this$0 = LayoutTransition.this;
                child = view;
                super();
            }
        }
);
        listener.start();
        listener = new android.view.View.OnLayoutChangeListener() {

            public void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                    int k1, int l1)
            {
                long l2;
label0:
                {
                    anim.setupEndValues();
                    if(!(anim instanceof ValueAnimator))
                        break label0;
                    i = 0;
                    view = ((ValueAnimator)anim).getValues();
                    j = 0;
                    do
                    {
                        if(j >= view.length)
                            break;
                        KeyframeSet keyframeset = view[j];
                        if(((PropertyValuesHolder) (keyframeset)).mKeyframes instanceof KeyframeSet)
                        {
                            keyframeset = (KeyframeSet)((PropertyValuesHolder) (keyframeset)).mKeyframes;
                            if(keyframeset.mFirstKeyframe == null || keyframeset.mLastKeyframe == null || keyframeset.mFirstKeyframe.getValue().equals(keyframeset.mLastKeyframe.getValue()) ^ true)
                                i = 1;
                        } else
                        if(!((PropertyValuesHolder) (keyframeset)).mKeyframes.getValue(0.0F).equals(((PropertyValuesHolder) (keyframeset)).mKeyframes.getValue(1.0F)))
                            i = 1;
                        j++;
                    } while(true);
                    if(i == 0)
                        return;
                }
                l2 = 0L;
                changeReason;
                JVM INSTR tableswitch 2 4: default 188
            //                           2 324
            //                           3 401
            //                           4 478;
                   goto _L1 _L2 _L3 _L4
_L1:
                anim.setStartDelay(l2);
                anim.setDuration(duration);
                view = (Animator)LayoutTransition._2D_get1(LayoutTransition.this).get(child);
                if(view != null)
                    view.cancel();
                if((Animator)LayoutTransition._2D_get14(LayoutTransition.this).get(child) != null)
                    LayoutTransition._2D_get14(LayoutTransition.this).remove(child);
                LayoutTransition._2D_get1(LayoutTransition.this).put(child, anim);
                parent.requestTransitionStart(LayoutTransition.this);
                child.removeOnLayoutChangeListener(this);
                LayoutTransition._2D_get3(LayoutTransition.this).remove(child);
                return;
_L2:
                long l3 = LayoutTransition._2D_get4(LayoutTransition.this) + LayoutTransition._2D_get18(LayoutTransition.this);
                view = LayoutTransition.this;
                LayoutTransition._2D_set0(view, LayoutTransition._2D_get18(view) + LayoutTransition._2D_get6(LayoutTransition.this));
                l2 = l3;
                if(LayoutTransition._2D_get5(LayoutTransition.this) != LayoutTransition._2D_get15())
                {
                    anim.setInterpolator(LayoutTransition._2D_get5(LayoutTransition.this));
                    l2 = l3;
                }
                continue; /* Loop/switch isn't completed */
_L3:
                long l4 = LayoutTransition._2D_get8(LayoutTransition.this) + LayoutTransition._2D_get18(LayoutTransition.this);
                view = LayoutTransition.this;
                LayoutTransition._2D_set0(view, LayoutTransition._2D_get18(view) + LayoutTransition._2D_get10(LayoutTransition.this));
                l2 = l4;
                if(LayoutTransition._2D_get9(LayoutTransition.this) != LayoutTransition._2D_get16())
                {
                    anim.setInterpolator(LayoutTransition._2D_get9(LayoutTransition.this));
                    l2 = l4;
                }
                continue; /* Loop/switch isn't completed */
_L4:
                long l5 = LayoutTransition._2D_get7(LayoutTransition.this) + LayoutTransition._2D_get18(LayoutTransition.this);
                view = LayoutTransition.this;
                LayoutTransition._2D_set0(view, LayoutTransition._2D_get18(view) + LayoutTransition._2D_get12(LayoutTransition.this));
                l2 = l5;
                if(LayoutTransition._2D_get11(LayoutTransition.this) != LayoutTransition._2D_get17())
                {
                    anim.setInterpolator(LayoutTransition._2D_get11(LayoutTransition.this));
                    l2 = l5;
                }
                if(true) goto _L1; else goto _L5
_L5:
            }

            final LayoutTransition this$0;
            final Animator val$anim;
            final int val$changeReason;
            final View val$child;
            final long val$duration;
            final ViewGroup val$parent;

            
            {
                this$0 = LayoutTransition.this;
                anim = animator;
                changeReason = i;
                duration = l;
                child = view;
                parent = viewgroup;
                super();
            }
        }
;
        anim.addListener(new AnimatorListenerAdapter() {

            public void onAnimationCancel(Animator animator)
            {
                child.removeOnLayoutChangeListener(listener);
                LayoutTransition._2D_get3(LayoutTransition.this).remove(child);
            }

            public void onAnimationEnd(Animator animator)
            {
                LayoutTransition._2D_get1(LayoutTransition.this).remove(child);
                if(LayoutTransition._2D_wrap0(LayoutTransition.this))
                {
                    Iterator iterator = ((ArrayList)LayoutTransition._2D_get13(LayoutTransition.this).clone()).iterator();
                    while(iterator.hasNext()) 
                    {
                        TransitionListener transitionlistener = (TransitionListener)iterator.next();
                        LayoutTransition layouttransition = LayoutTransition.this;
                        animator = parent;
                        View view = child;
                        int i;
                        if(changeReason == 2)
                            i = 0;
                        else
                        if(changeReason == 3)
                            i = 1;
                        else
                            i = 4;
                        transitionlistener.endTransition(layouttransition, animator, view, i);
                    }
                }
            }

            public void onAnimationStart(Animator animator)
            {
                if(LayoutTransition._2D_wrap0(LayoutTransition.this))
                {
                    animator = ((ArrayList)LayoutTransition._2D_get13(LayoutTransition.this).clone()).iterator();
                    while(animator.hasNext()) 
                    {
                        TransitionListener transitionlistener = (TransitionListener)animator.next();
                        LayoutTransition layouttransition = LayoutTransition.this;
                        ViewGroup viewgroup = parent;
                        View view = child;
                        int i;
                        if(changeReason == 2)
                            i = 0;
                        else
                        if(changeReason == 3)
                            i = 1;
                        else
                            i = 4;
                        transitionlistener.startTransition(layouttransition, viewgroup, view, i);
                    }
                }
            }

            final LayoutTransition this$0;
            final int val$changeReason;
            final View val$child;
            final android.view.View.OnLayoutChangeListener val$listener;
            final ViewGroup val$parent;

            
            {
                this$0 = LayoutTransition.this;
                parent = viewgroup;
                child = view;
                changeReason = i;
                listener = onlayoutchangelistener;
                super();
            }
        }
);
        child.addOnLayoutChangeListener(listener);
        layoutChangeListenerMap.put(child, listener);
    }

    public void addChild(ViewGroup viewgroup, View view)
    {
        addChild(viewgroup, view, true);
    }

    public void addTransitionListener(TransitionListener transitionlistener)
    {
        if(mListeners == null)
            mListeners = new ArrayList();
        mListeners.add(transitionlistener);
    }

    public void cancel()
    {
        if(currentChangingAnimations.size() > 0)
        {
            for(Iterator iterator = ((LinkedHashMap)currentChangingAnimations.clone()).values().iterator(); iterator.hasNext(); ((Animator)iterator.next()).cancel());
            currentChangingAnimations.clear();
        }
        if(currentAppearingAnimations.size() > 0)
        {
            for(Iterator iterator1 = ((LinkedHashMap)currentAppearingAnimations.clone()).values().iterator(); iterator1.hasNext(); ((Animator)iterator1.next()).end());
            currentAppearingAnimations.clear();
        }
        if(currentDisappearingAnimations.size() > 0)
        {
            for(Iterator iterator2 = ((LinkedHashMap)currentDisappearingAnimations.clone()).values().iterator(); iterator2.hasNext(); ((Animator)iterator2.next()).end());
            currentDisappearingAnimations.clear();
        }
    }

    public void cancel(int i)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 37
    //                   1 37
    //                   2 100
    //                   3 163
    //                   4 37;
           goto _L1 _L2 _L2 _L3 _L4 _L2
_L1:
        return;
_L2:
        if(currentChangingAnimations.size() > 0)
        {
            for(Iterator iterator = ((LinkedHashMap)currentChangingAnimations.clone()).values().iterator(); iterator.hasNext(); ((Animator)iterator.next()).cancel());
            currentChangingAnimations.clear();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(currentAppearingAnimations.size() > 0)
        {
            for(Iterator iterator1 = ((LinkedHashMap)currentAppearingAnimations.clone()).values().iterator(); iterator1.hasNext(); ((Animator)iterator1.next()).end());
            currentAppearingAnimations.clear();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(currentDisappearingAnimations.size() > 0)
        {
            for(Iterator iterator2 = ((LinkedHashMap)currentDisappearingAnimations.clone()).values().iterator(); iterator2.hasNext(); ((Animator)iterator2.next()).end());
            currentDisappearingAnimations.clear();
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void disableTransitionType(int i)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 65
    //                   1 79
    //                   2 37
    //                   3 51
    //                   4 93;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L4:
        mTransitionTypes = mTransitionTypes & -2;
        continue; /* Loop/switch isn't completed */
_L5:
        mTransitionTypes = mTransitionTypes & -3;
        continue; /* Loop/switch isn't completed */
_L2:
        mTransitionTypes = mTransitionTypes & -5;
        continue; /* Loop/switch isn't completed */
_L3:
        mTransitionTypes = mTransitionTypes & -9;
        continue; /* Loop/switch isn't completed */
_L6:
        mTransitionTypes = mTransitionTypes & 0xffffffef;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void enableTransitionType(int i)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 63
    //                   1 76
    //                   2 37
    //                   3 50
    //                   4 90;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L4:
        mTransitionTypes = mTransitionTypes | 1;
        continue; /* Loop/switch isn't completed */
_L5:
        mTransitionTypes = mTransitionTypes | 2;
        continue; /* Loop/switch isn't completed */
_L2:
        mTransitionTypes = mTransitionTypes | 4;
        continue; /* Loop/switch isn't completed */
_L3:
        mTransitionTypes = mTransitionTypes | 8;
        continue; /* Loop/switch isn't completed */
_L6:
        mTransitionTypes = mTransitionTypes | 0x10;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void endChangingAnimations()
    {
        Animator animator;
        for(Iterator iterator = ((LinkedHashMap)currentChangingAnimations.clone()).values().iterator(); iterator.hasNext(); animator.end())
        {
            animator = (Animator)iterator.next();
            animator.start();
        }

        currentChangingAnimations.clear();
    }

    public Animator getAnimator(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return mChangingAppearingAnim;

        case 1: // '\001'
            return mChangingDisappearingAnim;

        case 4: // '\004'
            return mChangingAnim;

        case 2: // '\002'
            return mAppearingAnim;

        case 3: // '\003'
            return mDisappearingAnim;
        }
    }

    public long getDuration(int i)
    {
        switch(i)
        {
        default:
            return 0L;

        case 0: // '\0'
            return mChangingAppearingDuration;

        case 1: // '\001'
            return mChangingDisappearingDuration;

        case 4: // '\004'
            return mChangingDuration;

        case 2: // '\002'
            return mAppearingDuration;

        case 3: // '\003'
            return mDisappearingDuration;
        }
    }

    public TimeInterpolator getInterpolator(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return mChangingAppearingInterpolator;

        case 1: // '\001'
            return mChangingDisappearingInterpolator;

        case 4: // '\004'
            return mChangingInterpolator;

        case 2: // '\002'
            return mAppearingInterpolator;

        case 3: // '\003'
            return mDisappearingInterpolator;
        }
    }

    public long getStagger(int i)
    {
        switch(i)
        {
        case 2: // '\002'
        case 3: // '\003'
        default:
            return 0L;

        case 0: // '\0'
            return mChangingAppearingStagger;

        case 1: // '\001'
            return mChangingDisappearingStagger;

        case 4: // '\004'
            return mChangingStagger;
        }
    }

    public long getStartDelay(int i)
    {
        switch(i)
        {
        default:
            return 0L;

        case 0: // '\0'
            return mChangingAppearingDelay;

        case 1: // '\001'
            return mChangingDisappearingDelay;

        case 4: // '\004'
            return mChangingDelay;

        case 2: // '\002'
            return mAppearingDelay;

        case 3: // '\003'
            return mDisappearingDelay;
        }
    }

    public List getTransitionListeners()
    {
        return mListeners;
    }

    public void hideChild(ViewGroup viewgroup, View view)
    {
        removeChild(viewgroup, view, true);
    }

    public void hideChild(ViewGroup viewgroup, View view, int i)
    {
        boolean flag;
        if(i == 8)
            flag = true;
        else
            flag = false;
        removeChild(viewgroup, view, flag);
    }

    public boolean isChangingLayout()
    {
        boolean flag = false;
        if(currentChangingAnimations.size() > 0)
            flag = true;
        return flag;
    }

    public boolean isRunning()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(currentChangingAnimations.size() > 0) goto _L2; else goto _L1
_L1:
        if(currentAppearingAnimations.size() <= 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(currentDisappearingAnimations.size() <= 0)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean isTransitionTypeEnabled(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        switch(i)
        {
        default:
            return false;

        case 2: // '\002'
            if((mTransitionTypes & 1) != 1)
                flag4 = false;
            return flag4;

        case 3: // '\003'
            if((mTransitionTypes & 2) == 2)
                flag4 = flag;
            else
                flag4 = false;
            return flag4;

        case 0: // '\0'
            if((mTransitionTypes & 4) == 4)
                flag4 = flag1;
            else
                flag4 = false;
            return flag4;

        case 1: // '\001'
            if((mTransitionTypes & 8) == 8)
                flag4 = flag2;
            else
                flag4 = false;
            return flag4;

        case 4: // '\004'
            break;
        }
        if((mTransitionTypes & 0x10) == 16)
            flag4 = flag3;
        else
            flag4 = false;
        return flag4;
    }

    public void layoutChange(ViewGroup viewgroup)
    {
        if(viewgroup.getWindowVisibility() != 0)
            return;
        if((mTransitionTypes & 0x10) == 16 && isRunning() ^ true)
            runChangeTransition(viewgroup, null, 4);
    }

    public void removeChild(ViewGroup viewgroup, View view)
    {
        removeChild(viewgroup, view, true);
    }

    public void removeTransitionListener(TransitionListener transitionlistener)
    {
        if(mListeners == null)
        {
            return;
        } else
        {
            mListeners.remove(transitionlistener);
            return;
        }
    }

    public void setAnimateParentHierarchy(boolean flag)
    {
        mAnimateParentHierarchy = flag;
    }

    public void setAnimator(int i, Animator animator)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 37
    //                   1 45
    //                   2 61
    //                   3 69
    //                   4 53;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        mChangingAppearingAnim = animator;
        continue; /* Loop/switch isn't completed */
_L3:
        mChangingDisappearingAnim = animator;
        continue; /* Loop/switch isn't completed */
_L6:
        mChangingAnim = animator;
        continue; /* Loop/switch isn't completed */
_L4:
        mAppearingAnim = animator;
        continue; /* Loop/switch isn't completed */
_L5:
        mDisappearingAnim = animator;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void setDuration(int i, long l)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 37
    //                   1 45
    //                   2 61
    //                   3 69
    //                   4 53;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        mChangingAppearingDuration = l;
        continue; /* Loop/switch isn't completed */
_L3:
        mChangingDisappearingDuration = l;
        continue; /* Loop/switch isn't completed */
_L6:
        mChangingDuration = l;
        continue; /* Loop/switch isn't completed */
_L4:
        mAppearingDuration = l;
        continue; /* Loop/switch isn't completed */
_L5:
        mDisappearingDuration = l;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void setDuration(long l)
    {
        mChangingAppearingDuration = l;
        mChangingDisappearingDuration = l;
        mChangingDuration = l;
        mAppearingDuration = l;
        mDisappearingDuration = l;
    }

    public void setInterpolator(int i, TimeInterpolator timeinterpolator)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 37
    //                   1 45
    //                   2 61
    //                   3 69
    //                   4 53;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        mChangingAppearingInterpolator = timeinterpolator;
        continue; /* Loop/switch isn't completed */
_L3:
        mChangingDisappearingInterpolator = timeinterpolator;
        continue; /* Loop/switch isn't completed */
_L6:
        mChangingInterpolator = timeinterpolator;
        continue; /* Loop/switch isn't completed */
_L4:
        mAppearingInterpolator = timeinterpolator;
        continue; /* Loop/switch isn't completed */
_L5:
        mDisappearingInterpolator = timeinterpolator;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void setStagger(int i, long l)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 37
    //                   1 45
    //                   2 36
    //                   3 36
    //                   4 53;
           goto _L1 _L2 _L3 _L1 _L1 _L4
_L1:
        return;
_L2:
        mChangingAppearingStagger = l;
        continue; /* Loop/switch isn't completed */
_L3:
        mChangingDisappearingStagger = l;
        continue; /* Loop/switch isn't completed */
_L4:
        mChangingStagger = l;
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void setStartDelay(int i, long l)
    {
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 37
    //                   1 45
    //                   2 61
    //                   3 69
    //                   4 53;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        mChangingAppearingDelay = l;
        continue; /* Loop/switch isn't completed */
_L3:
        mChangingDisappearingDelay = l;
        continue; /* Loop/switch isn't completed */
_L6:
        mChangingDelay = l;
        continue; /* Loop/switch isn't completed */
_L4:
        mAppearingDelay = l;
        continue; /* Loop/switch isn't completed */
_L5:
        mDisappearingDelay = l;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void showChild(ViewGroup viewgroup, View view)
    {
        addChild(viewgroup, view, true);
    }

    public void showChild(ViewGroup viewgroup, View view, int i)
    {
        boolean flag;
        if(i == 8)
            flag = true;
        else
            flag = false;
        addChild(viewgroup, view, flag);
    }

    public void startChangingAnimations()
    {
        Animator animator;
        for(Iterator iterator = ((LinkedHashMap)currentChangingAnimations.clone()).values().iterator(); iterator.hasNext(); animator.start())
        {
            animator = (Animator)iterator.next();
            if(animator instanceof ObjectAnimator)
                ((ObjectAnimator)animator).setCurrentPlayTime(0L);
        }

    }

    private static TimeInterpolator ACCEL_DECEL_INTERPOLATOR;
    public static final int APPEARING = 2;
    public static final int CHANGE_APPEARING = 0;
    public static final int CHANGE_DISAPPEARING = 1;
    public static final int CHANGING = 4;
    private static TimeInterpolator DECEL_INTERPOLATOR;
    private static long DEFAULT_DURATION = 0L;
    public static final int DISAPPEARING = 3;
    private static final int FLAG_APPEARING = 1;
    private static final int FLAG_CHANGE_APPEARING = 4;
    private static final int FLAG_CHANGE_DISAPPEARING = 8;
    private static final int FLAG_CHANGING = 16;
    private static final int FLAG_DISAPPEARING = 2;
    private static ObjectAnimator defaultChange;
    private static ObjectAnimator defaultChangeIn;
    private static ObjectAnimator defaultChangeOut;
    private static ObjectAnimator defaultFadeIn;
    private static ObjectAnimator defaultFadeOut;
    private static TimeInterpolator sAppearingInterpolator;
    private static TimeInterpolator sChangingAppearingInterpolator;
    private static TimeInterpolator sChangingDisappearingInterpolator;
    private static TimeInterpolator sChangingInterpolator;
    private static TimeInterpolator sDisappearingInterpolator;
    private final LinkedHashMap currentAppearingAnimations = new LinkedHashMap();
    private final LinkedHashMap currentChangingAnimations = new LinkedHashMap();
    private final LinkedHashMap currentDisappearingAnimations = new LinkedHashMap();
    private final HashMap layoutChangeListenerMap = new HashMap();
    private boolean mAnimateParentHierarchy;
    private Animator mAppearingAnim;
    private long mAppearingDelay;
    private long mAppearingDuration;
    private TimeInterpolator mAppearingInterpolator;
    private Animator mChangingAnim;
    private Animator mChangingAppearingAnim;
    private long mChangingAppearingDelay;
    private long mChangingAppearingDuration;
    private TimeInterpolator mChangingAppearingInterpolator;
    private long mChangingAppearingStagger;
    private long mChangingDelay;
    private Animator mChangingDisappearingAnim;
    private long mChangingDisappearingDelay;
    private long mChangingDisappearingDuration;
    private TimeInterpolator mChangingDisappearingInterpolator;
    private long mChangingDisappearingStagger;
    private long mChangingDuration;
    private TimeInterpolator mChangingInterpolator;
    private long mChangingStagger;
    private Animator mDisappearingAnim;
    private long mDisappearingDelay;
    private long mDisappearingDuration;
    private TimeInterpolator mDisappearingInterpolator;
    private ArrayList mListeners;
    private int mTransitionTypes;
    private final HashMap pendingAnimations = new HashMap();
    private long staggerDelay;

    static 
    {
        DEFAULT_DURATION = 300L;
        ACCEL_DECEL_INTERPOLATOR = new AccelerateDecelerateInterpolator();
        DECEL_INTERPOLATOR = new DecelerateInterpolator();
        sAppearingInterpolator = ACCEL_DECEL_INTERPOLATOR;
        sDisappearingInterpolator = ACCEL_DECEL_INTERPOLATOR;
        sChangingAppearingInterpolator = DECEL_INTERPOLATOR;
        sChangingDisappearingInterpolator = DECEL_INTERPOLATOR;
        sChangingInterpolator = DECEL_INTERPOLATOR;
    }
}
