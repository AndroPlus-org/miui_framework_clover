// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues, TransitionUtils

public class ChangeScroll extends Transition
{

    public ChangeScroll()
    {
    }

    public ChangeScroll(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        transitionvalues.values.put("android:changeScroll:x", Integer.valueOf(transitionvalues.view.getScrollX()));
        transitionvalues.values.put("android:changeScroll:y", Integer.valueOf(transitionvalues.view.getScrollY()));
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null || transitionvalues1 == null)
            return null;
        View view = transitionvalues1.view;
        int i = ((Integer)transitionvalues.values.get("android:changeScroll:x")).intValue();
        int j = ((Integer)transitionvalues1.values.get("android:changeScroll:x")).intValue();
        int k = ((Integer)transitionvalues.values.get("android:changeScroll:y")).intValue();
        int l = ((Integer)transitionvalues1.values.get("android:changeScroll:y")).intValue();
        viewgroup = null;
        transitionvalues = null;
        if(i != j)
        {
            view.setScrollX(i);
            viewgroup = ObjectAnimator.ofInt(view, "scrollX", new int[] {
                i, j
            });
        }
        if(k != l)
        {
            view.setScrollY(k);
            transitionvalues = ObjectAnimator.ofInt(view, "scrollY", new int[] {
                k, l
            });
        }
        return TransitionUtils.mergeAnimators(viewgroup, transitionvalues);
    }

    public String[] getTransitionProperties()
    {
        return PROPERTIES;
    }

    private static final String PROPERTIES[] = {
        "android:changeScroll:x", "android:changeScroll:y"
    };
    private static final String PROPNAME_SCROLL_X = "android:changeScroll:x";
    private static final String PROPNAME_SCROLL_Y = "android:changeScroll:y";

}
