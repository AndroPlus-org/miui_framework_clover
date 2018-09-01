// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

// Referenced classes of package android.transition:
//            Visibility, TransitionValues, TransitionListenerAdapter, Transition

public class Fade extends Visibility
{
    private static class FadeAnimatorListener extends AnimatorListenerAdapter
    {

        public void onAnimationEnd(Animator animator)
        {
            mView.setTransitionAlpha(1.0F);
            if(mLayerTypeChanged)
                mView.setLayerType(0, null);
        }

        public void onAnimationStart(Animator animator)
        {
            if(mView.hasOverlappingRendering() && mView.getLayerType() == 0)
            {
                mLayerTypeChanged = true;
                mView.setLayerType(2, null);
            }
        }

        private boolean mLayerTypeChanged;
        private final View mView;

        public FadeAnimatorListener(View view)
        {
            mLayerTypeChanged = false;
            mView = view;
        }
    }


    public Fade()
    {
    }

    public Fade(int i)
    {
        setMode(i);
    }

    public Fade(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Fade);
        setMode(context.getInt(0, getMode()));
        context.recycle();
    }

    private Animator createAnimation(final View view, float f, float f1)
    {
        if(f == f1)
            return null;
        view.setTransitionAlpha(f);
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, "transitionAlpha", new float[] {
            f1
        });
        if(DBG)
            Log.d("Fade", (new StringBuilder()).append("Created animator ").append(objectanimator).toString());
        objectanimator.addListener(new FadeAnimatorListener(view));
        addListener(new TransitionListenerAdapter() {

            public void onTransitionEnd(Transition transition)
            {
                view.setTransitionAlpha(1.0F);
                transition.removeListener(this);
            }

            final Fade this$0;
            final View val$view;

            
            {
                this$0 = Fade.this;
                view = view1;
                super();
            }
        }
);
        return objectanimator;
    }

    private static float getStartAlpha(TransitionValues transitionvalues, float f)
    {
        float f1 = f;
        if(transitionvalues != null)
        {
            transitionvalues = (Float)transitionvalues.values.get("android:fade:transitionAlpha");
            f1 = f;
            if(transitionvalues != null)
                f1 = transitionvalues.floatValue();
        }
        return f1;
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        super.captureStartValues(transitionvalues);
        transitionvalues.values.put("android:fade:transitionAlpha", Float.valueOf(transitionvalues.view.getTransitionAlpha()));
    }

    public Animator onAppear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(DBG)
        {
            float f;
            float f1;
            if(transitionvalues != null)
                viewgroup = transitionvalues.view;
            else
                viewgroup = null;
            Log.d("Fade", (new StringBuilder()).append("Fade.onAppear: startView, startVis, endView, endVis = ").append(viewgroup).append(", ").append(view).toString());
        }
        f = getStartAlpha(transitionvalues, 0.0F);
        f1 = f;
        if(f == 1.0F)
            f1 = 0.0F;
        return createAnimation(view, f1, 1.0F);
    }

    public Animator onDisappear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return createAnimation(view, getStartAlpha(transitionvalues, 1.0F), 0.0F);
    }

    private static boolean DBG = false;
    public static final int IN = 1;
    private static final String LOG_TAG = "Fade";
    public static final int OUT = 2;
    static final String PROPNAME_TRANSITION_ALPHA = "android:fade:transitionAlpha";

    static 
    {
        DBG = false;
    }
}
