// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues

public class ChangeClipBounds extends Transition
{

    public ChangeClipBounds()
    {
    }

    public ChangeClipBounds(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        Object obj = transitionvalues.view;
        if(((View) (obj)).getVisibility() == 8)
            return;
        Rect rect = ((View) (obj)).getClipBounds();
        transitionvalues.values.put("android:clipBounds:clip", rect);
        if(rect == null)
        {
            obj = new Rect(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
            transitionvalues.values.put("android:clipBounds:bounds", obj);
        }
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
        Rect rect;
        Rect rect1;
        boolean flag;
        while(transitionvalues == null || transitionvalues1 == null || transitionvalues.values.containsKey("android:clipBounds:clip") ^ true || transitionvalues1.values.containsKey("android:clipBounds:clip") ^ true) 
            return null;
        rect = (Rect)transitionvalues.values.get("android:clipBounds:clip");
        rect1 = (Rect)transitionvalues1.values.get("android:clipBounds:clip");
        if(rect1 == null)
            flag = true;
        else
            flag = false;
        if(rect == null && rect1 == null)
            return null;
        if(rect != null) goto _L2; else goto _L1
_L1:
        viewgroup = (Rect)transitionvalues.values.get("android:clipBounds:bounds");
        transitionvalues = rect1;
_L4:
        if(viewgroup.equals(transitionvalues))
            return null;
        break; /* Loop/switch isn't completed */
_L2:
        transitionvalues = rect1;
        viewgroup = rect;
        if(rect1 == null)
        {
            transitionvalues = (Rect)transitionvalues1.values.get("android:clipBounds:bounds");
            viewgroup = rect;
        }
        if(true) goto _L4; else goto _L3
_L3:
        transitionvalues1.view.setClipBounds(viewgroup);
        RectEvaluator rectevaluator = new RectEvaluator(new Rect());
        viewgroup = ObjectAnimator.ofObject(transitionvalues1.view, "clipBounds", rectevaluator, new Object[] {
            viewgroup, transitionvalues
        });
        if(flag)
            viewgroup.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    endView.setClipBounds(null);
                }

                final ChangeClipBounds this$0;
                final View val$endView;

            
            {
                this$0 = ChangeClipBounds.this;
                endView = view;
                super();
            }
            }
);
        return viewgroup;
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
    private static final String PROPNAME_CLIP = "android:clipBounds:clip";
    private static final String TAG = "ChangeTransform";
    private static final String sTransitionProperties[] = {
        "android:clipBounds:clip"
    };

}
