// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues

public class Rotate extends Transition
{

    public Rotate()
    {
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        transitionvalues.values.put("android:rotate:rotation", Float.valueOf(transitionvalues.view.getRotation()));
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        transitionvalues.values.put("android:rotate:rotation", Float.valueOf(transitionvalues.view.getRotation()));
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null || transitionvalues1 == null)
            return null;
        viewgroup = transitionvalues1.view;
        float f = ((Float)transitionvalues.values.get("android:rotate:rotation")).floatValue();
        float f1 = ((Float)transitionvalues1.values.get("android:rotate:rotation")).floatValue();
        if(f != f1)
        {
            viewgroup.setRotation(f);
            return ObjectAnimator.ofFloat(viewgroup, View.ROTATION, new float[] {
                f, f1
            });
        } else
        {
            return null;
        }
    }

    private static final String PROPNAME_ROTATION = "android:rotate:rotation";
}
