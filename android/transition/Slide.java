// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.Map;

// Referenced classes of package android.transition:
//            Visibility, TransitionValues, TranslationAnimationCreator, SidePropagation

public class Slide extends Visibility
{
    private static interface CalculateSlide
    {

        public abstract float getGoneX(ViewGroup viewgroup, View view, float f);

        public abstract float getGoneY(ViewGroup viewgroup, View view, float f);
    }

    private static abstract class CalculateSlideHorizontal
        implements CalculateSlide
    {

        public float getGoneY(ViewGroup viewgroup, View view, float f)
        {
            return view.getTranslationY();
        }

        private CalculateSlideHorizontal()
        {
        }

        CalculateSlideHorizontal(CalculateSlideHorizontal calculateslidehorizontal)
        {
            this();
        }
    }

    private static abstract class CalculateSlideVertical
        implements CalculateSlide
    {

        public float getGoneX(ViewGroup viewgroup, View view, float f)
        {
            return view.getTranslationX();
        }

        private CalculateSlideVertical()
        {
        }

        CalculateSlideVertical(CalculateSlideVertical calculateslidevertical)
        {
            this();
        }
    }


    public Slide()
    {
        mSlideCalculator = sCalculateBottom;
        mSlideEdge = 80;
        mSlideFraction = 1.0F;
        setSlideEdge(80);
    }

    public Slide(int i)
    {
        mSlideCalculator = sCalculateBottom;
        mSlideEdge = 80;
        mSlideFraction = 1.0F;
        setSlideEdge(i);
    }

    public Slide(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mSlideCalculator = sCalculateBottom;
        mSlideEdge = 80;
        mSlideFraction = 1.0F;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Slide);
        int i = context.getInt(0, 80);
        context.recycle();
        setSlideEdge(i);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        transitionvalues.values.put("android:slide:screenPosition", ai);
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

    public int getSlideEdge()
    {
        return mSlideEdge;
    }

    public Animator onAppear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues1 == null)
        {
            return null;
        } else
        {
            transitionvalues = (int[])transitionvalues1.values.get("android:slide:screenPosition");
            float f = view.getTranslationX();
            float f1 = view.getTranslationY();
            float f2 = mSlideCalculator.getGoneX(viewgroup, view, mSlideFraction);
            float f3 = mSlideCalculator.getGoneY(viewgroup, view, mSlideFraction);
            return TranslationAnimationCreator.createAnimation(view, transitionvalues1, transitionvalues[0], transitionvalues[1], f2, f3, f, f1, sDecelerate, this);
        }
    }

    public Animator onDisappear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null)
        {
            return null;
        } else
        {
            transitionvalues1 = (int[])transitionvalues.values.get("android:slide:screenPosition");
            float f = view.getTranslationX();
            float f1 = view.getTranslationY();
            float f2 = mSlideCalculator.getGoneX(viewgroup, view, mSlideFraction);
            float f3 = mSlideCalculator.getGoneY(viewgroup, view, mSlideFraction);
            return TranslationAnimationCreator.createAnimation(view, transitionvalues, transitionvalues1[0], transitionvalues1[1], f, f1, f2, f3, sAccelerate, this);
        }
    }

    public void setSlideEdge(int i)
    {
        i;
        JVM INSTR lookupswitch 6: default 60
    //                   3: 70
    //                   5: 111
    //                   48: 101
    //                   80: 121
    //                   8388611: 131
    //                   8388613: 141;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        throw new IllegalArgumentException("Invalid slide direction");
_L2:
        mSlideCalculator = sCalculateLeft;
_L9:
        mSlideEdge = i;
        SidePropagation sidepropagation = new SidePropagation();
        sidepropagation.setSide(i);
        setPropagation(sidepropagation);
        return;
_L4:
        mSlideCalculator = sCalculateTop;
        continue; /* Loop/switch isn't completed */
_L3:
        mSlideCalculator = sCalculateRight;
        continue; /* Loop/switch isn't completed */
_L5:
        mSlideCalculator = sCalculateBottom;
        continue; /* Loop/switch isn't completed */
_L6:
        mSlideCalculator = sCalculateStart;
        continue; /* Loop/switch isn't completed */
_L7:
        mSlideCalculator = sCalculateEnd;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void setSlideFraction(float f)
    {
        mSlideFraction = f;
    }

    private static final String PROPNAME_SCREEN_POSITION = "android:slide:screenPosition";
    private static final String TAG = "Slide";
    private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();
    private static final CalculateSlide sCalculateBottom = new CalculateSlideVertical() {

        public float getGoneY(ViewGroup viewgroup, View view, float f)
        {
            return view.getTranslationY() + (float)viewgroup.getHeight() * f;
        }

    }
;
    private static final CalculateSlide sCalculateEnd = new CalculateSlideHorizontal() {

        public float getGoneX(ViewGroup viewgroup, View view, float f)
        {
            boolean flag;
            if(viewgroup.getLayoutDirection() == 1)
                flag = true;
            else
                flag = false;
            if(flag)
                f = view.getTranslationX() - (float)viewgroup.getWidth() * f;
            else
                f = view.getTranslationX() + (float)viewgroup.getWidth() * f;
            return f;
        }

    }
;
    private static final CalculateSlide sCalculateLeft = new CalculateSlideHorizontal() {

        public float getGoneX(ViewGroup viewgroup, View view, float f)
        {
            return view.getTranslationX() - (float)viewgroup.getWidth() * f;
        }

    }
;
    private static final CalculateSlide sCalculateRight = new CalculateSlideHorizontal() {

        public float getGoneX(ViewGroup viewgroup, View view, float f)
        {
            return view.getTranslationX() + (float)viewgroup.getWidth() * f;
        }

    }
;
    private static final CalculateSlide sCalculateStart = new CalculateSlideHorizontal() {

        public float getGoneX(ViewGroup viewgroup, View view, float f)
        {
            boolean flag;
            if(viewgroup.getLayoutDirection() == 1)
                flag = true;
            else
                flag = false;
            if(flag)
                f = view.getTranslationX() + (float)viewgroup.getWidth() * f;
            else
                f = view.getTranslationX() - (float)viewgroup.getWidth() * f;
            return f;
        }

    }
;
    private static final CalculateSlide sCalculateTop = new CalculateSlideVertical() {

        public float getGoneY(ViewGroup viewgroup, View view, float f)
        {
            return view.getTranslationY() - (float)viewgroup.getHeight() * f;
        }

    }
;
    private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
    private CalculateSlide mSlideCalculator;
    private int mSlideEdge;
    private float mSlideFraction;

}
