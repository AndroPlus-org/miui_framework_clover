// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.Map;

// Referenced classes of package android.transition:
//            Visibility, CircularPropagation, TransitionValues, TranslationAnimationCreator

public class Explode extends Visibility
{

    public Explode()
    {
        mTempLoc = new int[2];
        setPropagation(new CircularPropagation());
    }

    public Explode(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTempLoc = new int[2];
        setPropagation(new CircularPropagation());
    }

    private static double calculateMaxDistance(View view, int i, int j)
    {
        i = Math.max(i, view.getWidth() - i);
        j = Math.max(j, view.getHeight() - j);
        return Math.hypot(i, j);
    }

    private void calculateOut(View view, Rect rect, int ai[])
    {
        view.getLocationOnScreen(mTempLoc);
        int i = mTempLoc[0];
        int j = mTempLoc[1];
        Rect rect1 = getEpicenter();
        int k;
        int l;
        int i1;
        int j1;
        double d;
        double d1;
        double d2;
        double d3;
        if(rect1 == null)
        {
            k = view.getWidth() / 2 + i + Math.round(view.getTranslationX());
            l = view.getHeight() / 2 + j + Math.round(view.getTranslationY());
        } else
        {
            k = rect1.centerX();
            l = rect1.centerY();
        }
        i1 = rect.centerX();
        j1 = rect.centerY();
        d = i1 - k;
        d1 = j1 - l;
        d2 = d;
        d3 = d1;
        if(d == 0.0D)
        {
            d2 = d;
            d3 = d1;
            if(d1 == 0.0D)
            {
                d2 = Math.random() * 2D - 1.0D;
                d3 = Math.random() * 2D - 1.0D;
            }
        }
        d1 = Math.hypot(d2, d3);
        d2 /= d1;
        d3 /= d1;
        d1 = calculateMaxDistance(view, k - i, l - j);
        ai[0] = (int)Math.round(d1 * d2);
        ai[1] = (int)Math.round(d1 * d3);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        view.getLocationOnScreen(mTempLoc);
        int i = mTempLoc[0];
        int j = mTempLoc[1];
        int k = view.getWidth();
        int l = view.getHeight();
        transitionvalues.values.put("android:explode:screenBounds", new Rect(i, j, i + k, j + l));
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
            transitionvalues = (Rect)transitionvalues1.values.get("android:explode:screenBounds");
            float f = view.getTranslationX();
            float f1 = view.getTranslationY();
            calculateOut(viewgroup, transitionvalues, mTempLoc);
            float f2 = mTempLoc[0];
            float f3 = mTempLoc[1];
            return TranslationAnimationCreator.createAnimation(view, transitionvalues1, ((Rect) (transitionvalues)).left, ((Rect) (transitionvalues)).top, f + f2, f1 + f3, f, f1, sDecelerate, this);
        }
    }

    public Animator onDisappear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null)
            return null;
        transitionvalues1 = (Rect)transitionvalues.values.get("android:explode:screenBounds");
        int i = ((Rect) (transitionvalues1)).left;
        int j = ((Rect) (transitionvalues1)).top;
        float f = view.getTranslationX();
        float f1 = view.getTranslationY();
        float f2 = f;
        float f3 = f1;
        int ai[] = (int[])transitionvalues.view.getTag(0x102046b);
        if(ai != null)
        {
            f2 = f + (float)(ai[0] - ((Rect) (transitionvalues1)).left);
            f3 = f1 + (float)(ai[1] - ((Rect) (transitionvalues1)).top);
            transitionvalues1.offsetTo(ai[0], ai[1]);
        }
        calculateOut(viewgroup, transitionvalues1, mTempLoc);
        return TranslationAnimationCreator.createAnimation(view, transitionvalues, i, j, f, f1, f2 + (float)mTempLoc[0], f3 + (float)mTempLoc[1], sAccelerate, this);
    }

    private static final String PROPNAME_SCREEN_BOUNDS = "android:explode:screenBounds";
    private static final String TAG = "Explode";
    private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();
    private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
    private int mTempLoc[];

}
