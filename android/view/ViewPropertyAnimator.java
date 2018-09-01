// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.*;
import java.util.*;

// Referenced classes of package android.view:
//            View, RenderNode, ViewPropertyAnimatorRT

public class ViewPropertyAnimator
{
    private class AnimatorEventListener
        implements android.animation.Animator.AnimatorListener, android.animation.ValueAnimator.AnimatorUpdateListener
    {

        public void onAnimationCancel(Animator animator)
        {
            if(ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this) != null)
                ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this).onAnimationCancel(animator);
            if(ViewPropertyAnimator._2D_get2(ViewPropertyAnimator.this) != null)
                ViewPropertyAnimator._2D_get2(ViewPropertyAnimator.this).remove(animator);
        }

        public void onAnimationEnd(Animator animator)
        {
            mView.setHasTransientState(false);
            if(ViewPropertyAnimator._2D_get0(ViewPropertyAnimator.this) != null)
            {
                Runnable runnable = (Runnable)ViewPropertyAnimator._2D_get0(ViewPropertyAnimator.this).get(animator);
                if(runnable != null)
                    runnable.run();
                ViewPropertyAnimator._2D_get0(ViewPropertyAnimator.this).remove(animator);
            }
            if(ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this) != null)
                ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this).onAnimationEnd(animator);
            if(ViewPropertyAnimator._2D_get2(ViewPropertyAnimator.this) != null)
            {
                Runnable runnable1 = (Runnable)ViewPropertyAnimator._2D_get2(ViewPropertyAnimator.this).get(animator);
                if(runnable1 != null)
                    runnable1.run();
                ViewPropertyAnimator._2D_get2(ViewPropertyAnimator.this).remove(animator);
            }
            ViewPropertyAnimator._2D_get1(ViewPropertyAnimator.this).remove(animator);
        }

        public void onAnimationRepeat(Animator animator)
        {
            if(ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this) != null)
                ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this).onAnimationRepeat(animator);
        }

        public void onAnimationStart(Animator animator)
        {
            if(ViewPropertyAnimator._2D_get4(ViewPropertyAnimator.this) != null)
            {
                Runnable runnable = (Runnable)ViewPropertyAnimator._2D_get4(ViewPropertyAnimator.this).get(animator);
                if(runnable != null)
                    runnable.run();
                ViewPropertyAnimator._2D_get4(ViewPropertyAnimator.this).remove(animator);
            }
            if(ViewPropertyAnimator._2D_get3(ViewPropertyAnimator.this) != null)
            {
                Runnable runnable1 = (Runnable)ViewPropertyAnimator._2D_get3(ViewPropertyAnimator.this).get(animator);
                if(runnable1 != null)
                    runnable1.run();
                ViewPropertyAnimator._2D_get3(ViewPropertyAnimator.this).remove(animator);
            }
            if(ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this) != null)
                ViewPropertyAnimator._2D_get5(ViewPropertyAnimator.this).onAnimationStart(animator);
        }

        public void onAnimationUpdate(ValueAnimator valueanimator)
        {
            PropertyBundle propertybundle = (PropertyBundle)ViewPropertyAnimator._2D_get1(ViewPropertyAnimator.this).get(valueanimator);
            if(propertybundle == null)
                return;
            boolean flag = mView.isHardwareAccelerated();
            boolean flag1 = false;
            boolean flag2 = false;
            if(!flag)
                mView.invalidateParentCaches();
            float f = valueanimator.getAnimatedFraction();
            int i = propertybundle.mPropertyMask;
            if((i & 0x7ff) != 0)
                mView.invalidateViewProperty(flag, false);
            ArrayList arraylist = propertybundle.mNameValuesHolder;
            if(arraylist != null)
            {
                int j = arraylist.size();
                int k = 0;
                do
                {
                    flag1 = flag2;
                    if(k >= j)
                        break;
                    NameValuesHolder namevaluesholder = (NameValuesHolder)arraylist.get(k);
                    float f1 = namevaluesholder.mFromValue + namevaluesholder.mDeltaValue * f;
                    if(namevaluesholder.mNameConstant == 2048)
                        flag2 = mView.setAlphaNoInvalidation(f1);
                    else
                        ViewPropertyAnimator._2D_wrap0(ViewPropertyAnimator.this, namevaluesholder.mNameConstant, f1);
                    k++;
                } while(true);
            }
            if((i & 0x7ff) != 0 && !flag)
            {
                View view = mView;
                view.mPrivateFlags = view.mPrivateFlags | 0x20;
            }
            if(flag1)
                mView.invalidate(true);
            else
                mView.invalidateViewProperty(false, false);
            if(ViewPropertyAnimator._2D_get6(ViewPropertyAnimator.this) != null)
                ViewPropertyAnimator._2D_get6(ViewPropertyAnimator.this).onAnimationUpdate(valueanimator);
        }

        final ViewPropertyAnimator this$0;

        private AnimatorEventListener()
        {
            this$0 = ViewPropertyAnimator.this;
            super();
        }

        AnimatorEventListener(AnimatorEventListener animatoreventlistener)
        {
            this();
        }
    }

    static class NameValuesHolder
    {

        float mDeltaValue;
        float mFromValue;
        int mNameConstant;

        NameValuesHolder(int i, float f, float f1)
        {
            mNameConstant = i;
            mFromValue = f;
            mDeltaValue = f1;
        }
    }

    private static class PropertyBundle
    {

        boolean cancel(int i)
        {
            if((mPropertyMask & i) != 0 && mNameValuesHolder != null)
            {
                int j = mNameValuesHolder.size();
                for(int k = 0; k < j; k++)
                    if(((NameValuesHolder)mNameValuesHolder.get(k)).mNameConstant == i)
                    {
                        mNameValuesHolder.remove(k);
                        mPropertyMask = mPropertyMask & i;
                        return true;
                    }

            }
            return false;
        }

        ArrayList mNameValuesHolder;
        int mPropertyMask;

        PropertyBundle(int i, ArrayList arraylist)
        {
            mPropertyMask = i;
            mNameValuesHolder = arraylist;
        }
    }


    static HashMap _2D_get0(ViewPropertyAnimator viewpropertyanimator)
    {
        return viewpropertyanimator.mAnimatorCleanupMap;
    }

    static HashMap _2D_get1(ViewPropertyAnimator viewpropertyanimator)
    {
        return viewpropertyanimator.mAnimatorMap;
    }

    static HashMap _2D_get2(ViewPropertyAnimator viewpropertyanimator)
    {
        return viewpropertyanimator.mAnimatorOnEndMap;
    }

    static HashMap _2D_get3(ViewPropertyAnimator viewpropertyanimator)
    {
        return viewpropertyanimator.mAnimatorOnStartMap;
    }

    static HashMap _2D_get4(ViewPropertyAnimator viewpropertyanimator)
    {
        return viewpropertyanimator.mAnimatorSetupMap;
    }

    static android.animation.Animator.AnimatorListener _2D_get5(ViewPropertyAnimator viewpropertyanimator)
    {
        return viewpropertyanimator.mListener;
    }

    static android.animation.ValueAnimator.AnimatorUpdateListener _2D_get6(ViewPropertyAnimator viewpropertyanimator)
    {
        return viewpropertyanimator.mUpdateListener;
    }

    static void _2D_wrap0(ViewPropertyAnimator viewpropertyanimator, int i, float f)
    {
        viewpropertyanimator.setValue(i, f);
    }

    static void _2D_wrap1(ViewPropertyAnimator viewpropertyanimator)
    {
        viewpropertyanimator.startAnimation();
    }

    ViewPropertyAnimator(View view)
    {
        mDurationSet = false;
        mStartDelay = 0L;
        mStartDelaySet = false;
        mInterpolatorSet = false;
        mListener = null;
        mUpdateListener = null;
        mAnimatorEventListener = new AnimatorEventListener(null);
        mPendingAnimations = new ArrayList();
        mAnimationStarter = new Runnable() {

            public void run()
            {
                ViewPropertyAnimator._2D_wrap1(ViewPropertyAnimator.this);
            }

            final ViewPropertyAnimator this$0;

            
            {
                this$0 = ViewPropertyAnimator.this;
                super();
            }
        }
;
        mAnimatorMap = new HashMap();
        mView = view;
        view.ensureTransformationInfo();
    }

    private void animateProperty(int i, float f)
    {
        float f1 = getValue(i);
        animatePropertyBy(i, f1, f - f1);
    }

    private void animatePropertyBy(int i, float f)
    {
        animatePropertyBy(i, getValue(i), f);
    }

    private void animatePropertyBy(int i, float f, float f1)
    {
        if(mAnimatorMap.size() > 0)
        {
            Object obj = null;
            Iterator iterator = mAnimatorMap.keySet().iterator();
            Animator animator;
            PropertyBundle propertybundle;
            do
            {
                animator = obj;
                if(!iterator.hasNext())
                    break;
                animator = (Animator)iterator.next();
                propertybundle = (PropertyBundle)mAnimatorMap.get(animator);
            } while(!propertybundle.cancel(i) || propertybundle.mPropertyMask != 0);
            if(animator != null)
                animator.cancel();
        }
        NameValuesHolder namevaluesholder = new NameValuesHolder(i, f, f1);
        mPendingAnimations.add(namevaluesholder);
        mView.removeCallbacks(mAnimationStarter);
        mView.postOnAnimation(mAnimationStarter);
    }

    private float getValue(int i)
    {
        RenderNode rendernode = mView.mRenderNode;
        switch(i)
        {
        default:
            return 0.0F;

        case 1: // '\001'
            return rendernode.getTranslationX();

        case 2: // '\002'
            return rendernode.getTranslationY();

        case 4: // '\004'
            return rendernode.getTranslationZ();

        case 32: // ' '
            return rendernode.getRotation();

        case 64: // '@'
            return rendernode.getRotationX();

        case 128: 
            return rendernode.getRotationY();

        case 8: // '\b'
            return rendernode.getScaleX();

        case 16: // '\020'
            return rendernode.getScaleY();

        case 256: 
            return (float)mView.mLeft + rendernode.getTranslationX();

        case 512: 
            return (float)mView.mTop + rendernode.getTranslationY();

        case 1024: 
            return rendernode.getElevation() + rendernode.getTranslationZ();

        case 2048: 
            return mView.mTransformationInfo.mAlpha;
        }
    }

    private void setValue(int i, float f)
    {
        View.TransformationInfo transformationinfo;
        RenderNode rendernode;
        transformationinfo = mView.mTransformationInfo;
        rendernode = mView.mRenderNode;
        i;
        JVM INSTR lookupswitch 12: default 124
    //                   1: 125
    //                   2: 135
    //                   4: 145
    //                   8: 185
    //                   16: 195
    //                   32: 155
    //                   64: 165
    //                   128: 175
    //                   256: 205
    //                   512: 224
    //                   1024: 243
    //                   2048: 259;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        return;
_L2:
        rendernode.setTranslationX(f);
        continue; /* Loop/switch isn't completed */
_L3:
        rendernode.setTranslationY(f);
        continue; /* Loop/switch isn't completed */
_L4:
        rendernode.setTranslationZ(f);
        continue; /* Loop/switch isn't completed */
_L7:
        rendernode.setRotation(f);
        continue; /* Loop/switch isn't completed */
_L8:
        rendernode.setRotationX(f);
        continue; /* Loop/switch isn't completed */
_L9:
        rendernode.setRotationY(f);
        continue; /* Loop/switch isn't completed */
_L5:
        rendernode.setScaleX(f);
        continue; /* Loop/switch isn't completed */
_L6:
        rendernode.setScaleY(f);
        continue; /* Loop/switch isn't completed */
_L10:
        rendernode.setTranslationX(f - (float)mView.mLeft);
        continue; /* Loop/switch isn't completed */
_L11:
        rendernode.setTranslationY(f - (float)mView.mTop);
        continue; /* Loop/switch isn't completed */
_L12:
        rendernode.setTranslationZ(f - rendernode.getElevation());
        continue; /* Loop/switch isn't completed */
_L13:
        transformationinfo.mAlpha = f;
        rendernode.setAlpha(f);
        if(true) goto _L1; else goto _L14
_L14:
    }

    private void startAnimation()
    {
        if(mRTBackend != null && mRTBackend.startAnimation(this))
            return;
        mView.setHasTransientState(true);
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            1.0F
        });
        ArrayList arraylist = (ArrayList)mPendingAnimations.clone();
        mPendingAnimations.clear();
        int i = 0;
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
            i |= ((NameValuesHolder)arraylist.get(k)).mNameConstant;

        mAnimatorMap.put(valueanimator, new PropertyBundle(i, arraylist));
        if(mPendingSetupAction != null)
        {
            mAnimatorSetupMap.put(valueanimator, mPendingSetupAction);
            mPendingSetupAction = null;
        }
        if(mPendingCleanupAction != null)
        {
            mAnimatorCleanupMap.put(valueanimator, mPendingCleanupAction);
            mPendingCleanupAction = null;
        }
        if(mPendingOnStartAction != null)
        {
            mAnimatorOnStartMap.put(valueanimator, mPendingOnStartAction);
            mPendingOnStartAction = null;
        }
        if(mPendingOnEndAction != null)
        {
            mAnimatorOnEndMap.put(valueanimator, mPendingOnEndAction);
            mPendingOnEndAction = null;
        }
        valueanimator.addUpdateListener(mAnimatorEventListener);
        valueanimator.addListener(mAnimatorEventListener);
        if(mStartDelaySet)
            valueanimator.setStartDelay(mStartDelay);
        if(mDurationSet)
            valueanimator.setDuration(mDuration);
        if(mInterpolatorSet)
            valueanimator.setInterpolator(mInterpolator);
        valueanimator.start();
    }

    public ViewPropertyAnimator alpha(float f)
    {
        animateProperty(2048, f);
        return this;
    }

    public ViewPropertyAnimator alphaBy(float f)
    {
        animatePropertyBy(2048, f);
        return this;
    }

    public void cancel()
    {
        if(mAnimatorMap.size() > 0)
        {
            for(Iterator iterator = ((HashMap)mAnimatorMap.clone()).keySet().iterator(); iterator.hasNext(); ((Animator)iterator.next()).cancel());
        }
        mPendingAnimations.clear();
        mPendingSetupAction = null;
        mPendingCleanupAction = null;
        mPendingOnStartAction = null;
        mPendingOnEndAction = null;
        mView.removeCallbacks(mAnimationStarter);
        if(mRTBackend != null)
            mRTBackend.cancelAll();
    }

    public long getDuration()
    {
        if(mDurationSet)
            return mDuration;
        if(mTempValueAnimator == null)
            mTempValueAnimator = new ValueAnimator();
        return mTempValueAnimator.getDuration();
    }

    public TimeInterpolator getInterpolator()
    {
        if(mInterpolatorSet)
            return mInterpolator;
        if(mTempValueAnimator == null)
            mTempValueAnimator = new ValueAnimator();
        return mTempValueAnimator.getInterpolator();
    }

    android.animation.Animator.AnimatorListener getListener()
    {
        return mListener;
    }

    public long getStartDelay()
    {
        if(mStartDelaySet)
            return mStartDelay;
        else
            return 0L;
    }

    android.animation.ValueAnimator.AnimatorUpdateListener getUpdateListener()
    {
        return mUpdateListener;
    }

    boolean hasActions()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mPendingSetupAction != null) goto _L2; else goto _L1
_L1:
        if(mPendingCleanupAction == null) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mPendingOnStartAction == null)
        {
            flag1 = flag;
            if(mPendingOnEndAction == null)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public ViewPropertyAnimator rotation(float f)
    {
        animateProperty(32, f);
        return this;
    }

    public ViewPropertyAnimator rotationBy(float f)
    {
        animatePropertyBy(32, f);
        return this;
    }

    public ViewPropertyAnimator rotationX(float f)
    {
        animateProperty(64, f);
        return this;
    }

    public ViewPropertyAnimator rotationXBy(float f)
    {
        animatePropertyBy(64, f);
        return this;
    }

    public ViewPropertyAnimator rotationY(float f)
    {
        animateProperty(128, f);
        return this;
    }

    public ViewPropertyAnimator rotationYBy(float f)
    {
        animatePropertyBy(128, f);
        return this;
    }

    public ViewPropertyAnimator scaleX(float f)
    {
        animateProperty(8, f);
        return this;
    }

    public ViewPropertyAnimator scaleXBy(float f)
    {
        animatePropertyBy(8, f);
        return this;
    }

    public ViewPropertyAnimator scaleY(float f)
    {
        animateProperty(16, f);
        return this;
    }

    public ViewPropertyAnimator scaleYBy(float f)
    {
        animatePropertyBy(16, f);
        return this;
    }

    public ViewPropertyAnimator setDuration(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Animators cannot have negative duration: ").append(l).toString());
        } else
        {
            mDurationSet = true;
            mDuration = l;
            return this;
        }
    }

    public ViewPropertyAnimator setInterpolator(TimeInterpolator timeinterpolator)
    {
        mInterpolatorSet = true;
        mInterpolator = timeinterpolator;
        return this;
    }

    public ViewPropertyAnimator setListener(android.animation.Animator.AnimatorListener animatorlistener)
    {
        mListener = animatorlistener;
        return this;
    }

    public ViewPropertyAnimator setStartDelay(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Animators cannot have negative start delay: ").append(l).toString());
        } else
        {
            mStartDelaySet = true;
            mStartDelay = l;
            return this;
        }
    }

    public ViewPropertyAnimator setUpdateListener(android.animation.ValueAnimator.AnimatorUpdateListener animatorupdatelistener)
    {
        mUpdateListener = animatorupdatelistener;
        return this;
    }

    public void start()
    {
        mView.removeCallbacks(mAnimationStarter);
        startAnimation();
    }

    public ViewPropertyAnimator translationX(float f)
    {
        animateProperty(1, f);
        return this;
    }

    public ViewPropertyAnimator translationXBy(float f)
    {
        animatePropertyBy(1, f);
        return this;
    }

    public ViewPropertyAnimator translationY(float f)
    {
        animateProperty(2, f);
        return this;
    }

    public ViewPropertyAnimator translationYBy(float f)
    {
        animatePropertyBy(2, f);
        return this;
    }

    public ViewPropertyAnimator translationZ(float f)
    {
        animateProperty(4, f);
        return this;
    }

    public ViewPropertyAnimator translationZBy(float f)
    {
        animatePropertyBy(4, f);
        return this;
    }

    public ViewPropertyAnimator withEndAction(Runnable runnable)
    {
        mPendingOnEndAction = runnable;
        if(runnable != null && mAnimatorOnEndMap == null)
            mAnimatorOnEndMap = new HashMap();
        return this;
    }

    public ViewPropertyAnimator withLayer()
    {
        mPendingSetupAction = new Runnable() {

            public void run()
            {
                mView.setLayerType(2, null);
                if(mView.isAttachedToWindow())
                    mView.buildLayer();
            }

            final ViewPropertyAnimator this$0;

            
            {
                this$0 = ViewPropertyAnimator.this;
                super();
            }
        }
;
        mPendingCleanupAction = new Runnable() {

            public void run()
            {
                mView.setLayerType(currentLayerType, null);
            }

            final ViewPropertyAnimator this$0;
            final int val$currentLayerType;

            
            {
                this$0 = ViewPropertyAnimator.this;
                currentLayerType = i;
                super();
            }
        }
;
        if(mAnimatorSetupMap == null)
            mAnimatorSetupMap = new HashMap();
        if(mAnimatorCleanupMap == null)
            mAnimatorCleanupMap = new HashMap();
        return this;
    }

    public ViewPropertyAnimator withStartAction(Runnable runnable)
    {
        mPendingOnStartAction = runnable;
        if(runnable != null && mAnimatorOnStartMap == null)
            mAnimatorOnStartMap = new HashMap();
        return this;
    }

    public ViewPropertyAnimator x(float f)
    {
        animateProperty(256, f);
        return this;
    }

    public ViewPropertyAnimator xBy(float f)
    {
        animatePropertyBy(256, f);
        return this;
    }

    public ViewPropertyAnimator y(float f)
    {
        animateProperty(512, f);
        return this;
    }

    public ViewPropertyAnimator yBy(float f)
    {
        animatePropertyBy(512, f);
        return this;
    }

    public ViewPropertyAnimator z(float f)
    {
        animateProperty(1024, f);
        return this;
    }

    public ViewPropertyAnimator zBy(float f)
    {
        animatePropertyBy(1024, f);
        return this;
    }

    static final int ALPHA = 2048;
    static final int NONE = 0;
    static final int ROTATION = 32;
    static final int ROTATION_X = 64;
    static final int ROTATION_Y = 128;
    static final int SCALE_X = 8;
    static final int SCALE_Y = 16;
    private static final int TRANSFORM_MASK = 2047;
    static final int TRANSLATION_X = 1;
    static final int TRANSLATION_Y = 2;
    static final int TRANSLATION_Z = 4;
    static final int X = 256;
    static final int Y = 512;
    static final int Z = 1024;
    private Runnable mAnimationStarter;
    private HashMap mAnimatorCleanupMap;
    private AnimatorEventListener mAnimatorEventListener;
    private HashMap mAnimatorMap;
    private HashMap mAnimatorOnEndMap;
    private HashMap mAnimatorOnStartMap;
    private HashMap mAnimatorSetupMap;
    private long mDuration;
    private boolean mDurationSet;
    private TimeInterpolator mInterpolator;
    private boolean mInterpolatorSet;
    private android.animation.Animator.AnimatorListener mListener;
    ArrayList mPendingAnimations;
    private Runnable mPendingCleanupAction;
    private Runnable mPendingOnEndAction;
    private Runnable mPendingOnStartAction;
    private Runnable mPendingSetupAction;
    private ViewPropertyAnimatorRT mRTBackend;
    private long mStartDelay;
    private boolean mStartDelaySet;
    private ValueAnimator mTempValueAnimator;
    private android.animation.ValueAnimator.AnimatorUpdateListener mUpdateListener;
    final View mView;
}
