// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

// Referenced classes of package android.transition:
//            VisibilityPropagation, Transition, TransitionValues

public class CircularPropagation extends VisibilityPropagation
{

    public CircularPropagation()
    {
        mPropagationSpeed = 3F;
    }

    private static double distance(float f, float f1, float f2, float f3)
    {
        return Math.hypot(f2 - f, f3 - f1);
    }

    public long getStartDelay(ViewGroup viewgroup, Transition transition, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null && transitionvalues1 == null)
            return 0L;
        int i = 1;
        if(transitionvalues1 == null || getViewVisibility(transitionvalues) == 0)
        {
            i = -1;
            transitionvalues1 = transitionvalues;
        }
        int j = getViewX(transitionvalues1);
        int k = getViewY(transitionvalues1);
        transitionvalues = transition.getEpicenter();
        int l;
        int i1;
        double d;
        long l1;
        long l2;
        if(transitionvalues != null)
        {
            l = transitionvalues.centerX();
            i1 = transitionvalues.centerY();
        } else
        {
            transitionvalues = new int[2];
            viewgroup.getLocationOnScreen(transitionvalues);
            l = Math.round((float)(transitionvalues[0] + viewgroup.getWidth() / 2) + viewgroup.getTranslationX());
            i1 = Math.round((float)(transitionvalues[1] + viewgroup.getHeight() / 2) + viewgroup.getTranslationY());
        }
        d = distance(j, k, l, i1) / distance(0.0F, 0.0F, viewgroup.getWidth(), viewgroup.getHeight());
        l1 = transition.getDuration();
        l2 = l1;
        if(l1 < 0L)
            l2 = 300L;
        return Math.round((double)((float)((long)i * l2) / mPropagationSpeed) * d);
    }

    public void setPropagationSpeed(float f)
    {
        if(f == 0.0F)
        {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        } else
        {
            mPropagationSpeed = f;
            return;
        }
    }

    private static final String TAG = "CircularPropagation";
    private float mPropagationSpeed;
}
