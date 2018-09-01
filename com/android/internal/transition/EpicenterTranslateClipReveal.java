// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.transition;

import android.animation.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import java.util.Map;

// Referenced classes of package com.android.internal.transition:
//            TransitionConstants

public class EpicenterTranslateClipReveal extends Visibility
{
    private static class State
    {

        int lower;
        float trans;
        int upper;

        public State()
        {
        }

        public State(int i, int j, float f)
        {
            lower = i;
            upper = j;
            trans = f;
        }
    }

    private static class StateEvaluator
        implements TypeEvaluator
    {

        public State evaluate(float f, State state, State state1)
        {
            mTemp.upper = state.upper + (int)((float)(state1.upper - state.upper) * f);
            mTemp.lower = state.lower + (int)((float)(state1.lower - state.lower) * f);
            mTemp.trans = state.trans + (float)(int)((state1.trans - state.trans) * f);
            return mTemp;
        }

        public volatile Object evaluate(float f, Object obj, Object obj1)
        {
            return evaluate(f, (State)obj, (State)obj1);
        }

        private final State mTemp;

        private StateEvaluator()
        {
            mTemp = new State();
        }

        StateEvaluator(StateEvaluator stateevaluator)
        {
            this();
        }
    }

    private static class StateProperty extends Property
    {

        public State get(View view)
        {
            Rect rect = mTempRect;
            if(!view.getClipBounds(rect))
                rect.setEmpty();
            State state = mTempState;
            if(mTargetDimension == 120)
            {
                state.trans = view.getTranslationX();
                state.lower = rect.left + (int)state.trans;
                state.upper = rect.right + (int)state.trans;
            } else
            {
                state.trans = view.getTranslationY();
                state.lower = rect.top + (int)state.trans;
                state.upper = rect.bottom + (int)state.trans;
            }
            return state;
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void set(View view, State state)
        {
            Rect rect = mTempRect;
            if(view.getClipBounds(rect))
            {
                if(mTargetDimension == 120)
                {
                    rect.left = state.lower - (int)state.trans;
                    rect.right = state.upper - (int)state.trans;
                } else
                {
                    rect.top = state.lower - (int)state.trans;
                    rect.bottom = state.upper - (int)state.trans;
                }
                view.setClipBounds(rect);
            }
            if(mTargetDimension == 120)
                view.setTranslationX(state.trans);
            else
                view.setTranslationY(state.trans);
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((View)obj, (State)obj1);
        }

        public static final char TARGET_X = 120;
        public static final char TARGET_Y = 121;
        private final int mTargetDimension;
        private final Rect mTempRect = new Rect();
        private final State mTempState = new State();

        public StateProperty(char c)
        {
            super(com/android/internal/transition/EpicenterTranslateClipReveal$State, (new StringBuilder()).append("state_").append(c).toString());
            mTargetDimension = c;
        }
    }


    public EpicenterTranslateClipReveal()
    {
        mInterpolatorX = null;
        mInterpolatorY = null;
        mInterpolatorZ = null;
    }

    public EpicenterTranslateClipReveal(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.EpicenterTranslateClipReveal, 0, 0);
        int i = attributeset.getResourceId(0, 0);
        if(i != 0)
            mInterpolatorX = AnimationUtils.loadInterpolator(context, i);
        else
            mInterpolatorX = TransitionConstants.LINEAR_OUT_SLOW_IN;
        i = attributeset.getResourceId(1, 0);
        if(i != 0)
            mInterpolatorY = AnimationUtils.loadInterpolator(context, i);
        else
            mInterpolatorY = TransitionConstants.FAST_OUT_SLOW_IN;
        i = attributeset.getResourceId(2, 0);
        if(i != 0)
            mInterpolatorZ = AnimationUtils.loadInterpolator(context, i);
        else
            mInterpolatorZ = TransitionConstants.FAST_OUT_SLOW_IN;
        attributeset.recycle();
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        Object obj = transitionvalues.view;
        if(((View) (obj)).getVisibility() == 8)
        {
            return;
        } else
        {
            Rect rect = new Rect(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
            transitionvalues.values.put("android:epicenterReveal:bounds", rect);
            transitionvalues.values.put("android:epicenterReveal:translateX", Float.valueOf(((View) (obj)).getTranslationX()));
            transitionvalues.values.put("android:epicenterReveal:translateY", Float.valueOf(((View) (obj)).getTranslationY()));
            transitionvalues.values.put("android:epicenterReveal:translateZ", Float.valueOf(((View) (obj)).getTranslationZ()));
            transitionvalues.values.put("android:epicenterReveal:z", Float.valueOf(((View) (obj)).getZ()));
            obj = ((View) (obj)).getClipBounds();
            transitionvalues.values.put("android:epicenterReveal:clip", obj);
            return;
        }
    }

    private static Animator createRectAnimator(View view, State state, State state1, float f, State state2, State state3, float f1, TransitionValues transitionvalues, 
            TimeInterpolator timeinterpolator, TimeInterpolator timeinterpolator1, TimeInterpolator timeinterpolator2)
    {
        StateEvaluator stateevaluator = new StateEvaluator(null);
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Z, new float[] {
            f, f1
        });
        if(timeinterpolator2 != null)
            objectanimator.setInterpolator(timeinterpolator2);
        state = ObjectAnimator.ofObject(view, new StateProperty('x'), stateevaluator, new State[] {
            state, state2
        });
        if(timeinterpolator != null)
            state.setInterpolator(timeinterpolator);
        state1 = ObjectAnimator.ofObject(view, new StateProperty('y'), stateevaluator, new State[] {
            state1, state3
        });
        if(timeinterpolator1 != null)
            state1.setInterpolator(timeinterpolator1);
        view = new AnimatorListenerAdapter(view, (Rect)transitionvalues.values.get("android:epicenterReveal:clip")) {

            public void onAnimationEnd(Animator animator)
            {
                view.setClipBounds(terminalClip);
            }

            final Rect val$terminalClip;
            final View val$view;

            
            {
                view = view1;
                terminalClip = rect;
                super();
            }
        }
;
        state2 = new AnimatorSet();
        state2.playTogether(new Animator[] {
            state, state1, objectanimator
        });
        state2.addListener(view);
        return state2;
    }

    private Rect getBestRect(TransitionValues transitionvalues)
    {
        Rect rect = (Rect)transitionvalues.values.get("android:epicenterReveal:clip");
        if(rect == null)
            return (Rect)transitionvalues.values.get("android:epicenterReveal:bounds");
        else
            return rect;
    }

    private Rect getEpicenterOrCenter(Rect rect)
    {
        Rect rect1 = getEpicenter();
        if(rect1 != null)
        {
            return rect1;
        } else
        {
            int i = rect.centerX();
            int j = rect.centerY();
            return new Rect(i, j, i, j);
        }
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        super.captureEndValues(transitionvalues);
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        super.captureStartValues(transitionvalues);
        captureValues(transitionvalues);
    }

    public Animator onAppear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues1 == null)
        {
            return null;
        } else
        {
            transitionvalues = (Rect)transitionvalues1.values.get("android:epicenterReveal:bounds");
            viewgroup = getEpicenterOrCenter(transitionvalues);
            float f = viewgroup.centerX() - transitionvalues.centerX();
            float f1 = viewgroup.centerY() - transitionvalues.centerY();
            float f2 = 0.0F - ((Float)transitionvalues1.values.get("android:epicenterReveal:z")).floatValue();
            view.setTranslationX(f);
            view.setTranslationY(f1);
            view.setTranslationZ(f2);
            float f3 = ((Float)transitionvalues1.values.get("android:epicenterReveal:translateX")).floatValue();
            float f4 = ((Float)transitionvalues1.values.get("android:epicenterReveal:translateY")).floatValue();
            float f5 = ((Float)transitionvalues1.values.get("android:epicenterReveal:translateZ")).floatValue();
            Rect rect = getBestRect(transitionvalues1);
            viewgroup = getEpicenterOrCenter(rect);
            view.setClipBounds(viewgroup);
            State state = new State(((Rect) (viewgroup)).left, ((Rect) (viewgroup)).right, f);
            transitionvalues = new State(rect.left, rect.right, f3);
            return createRectAnimator(view, state, new State(((Rect) (viewgroup)).top, ((Rect) (viewgroup)).bottom, f1), f2, transitionvalues, new State(rect.top, rect.bottom, f4), f5, transitionvalues1, mInterpolatorX, mInterpolatorY, mInterpolatorZ);
        }
    }

    public Animator onDisappear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null)
        {
            return null;
        } else
        {
            Rect rect = (Rect)transitionvalues1.values.get("android:epicenterReveal:bounds");
            viewgroup = getEpicenterOrCenter(rect);
            float f = viewgroup.centerX() - rect.centerX();
            float f1 = viewgroup.centerY() - rect.centerY();
            float f2 = ((Float)transitionvalues.values.get("android:epicenterReveal:z")).floatValue();
            float f3 = ((Float)transitionvalues1.values.get("android:epicenterReveal:translateX")).floatValue();
            float f4 = ((Float)transitionvalues1.values.get("android:epicenterReveal:translateY")).floatValue();
            float f5 = ((Float)transitionvalues1.values.get("android:epicenterReveal:translateZ")).floatValue();
            transitionvalues = getBestRect(transitionvalues);
            rect = getEpicenterOrCenter(transitionvalues);
            view.setClipBounds(transitionvalues);
            State state = new State(((Rect) (transitionvalues)).left, ((Rect) (transitionvalues)).right, f3);
            viewgroup = new State(rect.left, rect.right, f);
            return createRectAnimator(view, state, new State(((Rect) (transitionvalues)).top, ((Rect) (transitionvalues)).bottom, f4), f5, viewgroup, new State(rect.top, rect.bottom, f1), 0.0F - f2, transitionvalues1, mInterpolatorX, mInterpolatorY, mInterpolatorZ);
        }
    }

    private static final String PROPNAME_BOUNDS = "android:epicenterReveal:bounds";
    private static final String PROPNAME_CLIP = "android:epicenterReveal:clip";
    private static final String PROPNAME_TRANSLATE_X = "android:epicenterReveal:translateX";
    private static final String PROPNAME_TRANSLATE_Y = "android:epicenterReveal:translateY";
    private static final String PROPNAME_TRANSLATE_Z = "android:epicenterReveal:translateZ";
    private static final String PROPNAME_Z = "android:epicenterReveal:z";
    private final TimeInterpolator mInterpolatorX;
    private final TimeInterpolator mInterpolatorY;
    private final TimeInterpolator mInterpolatorZ;
}
