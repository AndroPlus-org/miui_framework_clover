// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.graphics.Path;
import android.view.View;

// Referenced classes of package android.transition:
//            TransitionValues, Transition

class TranslationAnimationCreator
{
    private static class TransitionPositionListener extends AnimatorListenerAdapter
        implements Transition.TransitionListener
    {

        public void onAnimationCancel(Animator animator)
        {
            if(mTransitionPosition == null)
                mTransitionPosition = new int[2];
            mTransitionPosition[0] = Math.round((float)mStartX + mMovingView.getTranslationX());
            mTransitionPosition[1] = Math.round((float)mStartY + mMovingView.getTranslationY());
            mViewInHierarchy.setTagInternal(0x102046b, mTransitionPosition);
        }

        public void onAnimationEnd(Animator animator)
        {
        }

        public void onAnimationPause(Animator animator)
        {
            mPausedX = mMovingView.getTranslationX();
            mPausedY = mMovingView.getTranslationY();
            mMovingView.setTranslationX(mTerminalX);
            mMovingView.setTranslationY(mTerminalY);
        }

        public void onAnimationResume(Animator animator)
        {
            mMovingView.setTranslationX(mPausedX);
            mMovingView.setTranslationY(mPausedY);
        }

        public void onTransitionCancel(Transition transition)
        {
        }

        public void onTransitionEnd(Transition transition)
        {
            mMovingView.setTranslationX(mTerminalX);
            mMovingView.setTranslationY(mTerminalY);
            transition.removeListener(this);
        }

        public void onTransitionPause(Transition transition)
        {
        }

        public void onTransitionResume(Transition transition)
        {
        }

        public void onTransitionStart(Transition transition)
        {
        }

        private final View mMovingView;
        private float mPausedX;
        private float mPausedY;
        private final int mStartX;
        private final int mStartY;
        private final float mTerminalX;
        private final float mTerminalY;
        private int mTransitionPosition[];
        private final View mViewInHierarchy;

        private TransitionPositionListener(View view, View view1, int i, int j, float f, float f1)
        {
            mMovingView = view;
            mViewInHierarchy = view1;
            mStartX = i - Math.round(mMovingView.getTranslationX());
            mStartY = j - Math.round(mMovingView.getTranslationY());
            mTerminalX = f;
            mTerminalY = f1;
            mTransitionPosition = (int[])mViewInHierarchy.getTag(0x102046b);
            if(mTransitionPosition != null)
                mViewInHierarchy.setTagInternal(0x102046b, null);
        }

        TransitionPositionListener(View view, View view1, int i, int j, float f, float f1, TransitionPositionListener transitionpositionlistener)
        {
            this(view, view1, i, j, f, f1);
        }
    }


    TranslationAnimationCreator()
    {
    }

    static Animator createAnimation(View view, TransitionValues transitionvalues, int i, int j, float f, float f1, float f2, float f3, 
            TimeInterpolator timeinterpolator, Transition transition)
    {
        float f4 = view.getTranslationX();
        float f5 = view.getTranslationY();
        int ai[] = (int[])transitionvalues.view.getTag(0x102046b);
        if(ai != null)
        {
            f = (float)(ai[0] - i) + f4;
            f1 = (float)(ai[1] - j) + f5;
        }
        int k = Math.round(f - f4);
        int l = Math.round(f1 - f5);
        view.setTranslationX(f);
        view.setTranslationY(f1);
        if(f == f2 && f1 == f3)
        {
            return null;
        } else
        {
            Object obj = new Path();
            ((Path) (obj)).moveTo(f, f1);
            ((Path) (obj)).lineTo(f2, f3);
            obj = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, View.TRANSLATION_Y, ((Path) (obj)));
            view = new TransitionPositionListener(view, transitionvalues.view, i + k, j + l, f4, f5, null);
            transition.addListener(view);
            ((ObjectAnimator) (obj)).addListener(view);
            ((ObjectAnimator) (obj)).addPauseListener(view);
            ((ObjectAnimator) (obj)).setInterpolator(timeinterpolator);
            return ((Animator) (obj));
        }
    }
}
