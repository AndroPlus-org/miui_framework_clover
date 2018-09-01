// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues

public class Recolor extends Transition
{

    public Recolor()
    {
    }

    public Recolor(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        transitionvalues.values.put("android:recolor:background", transitionvalues.view.getBackground());
        if(transitionvalues.view instanceof TextView)
            transitionvalues.values.put("android:recolor:textColor", Integer.valueOf(((TextView)transitionvalues.view).getCurrentTextColor()));
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
        Object obj = (Drawable)transitionvalues.values.get("android:recolor:background");
        viewgroup = (Drawable)transitionvalues1.values.get("android:recolor:background");
        if((obj instanceof ColorDrawable) && (viewgroup instanceof ColorDrawable))
        {
            ColorDrawable colordrawable = (ColorDrawable)obj;
            obj = (ColorDrawable)viewgroup;
            if(colordrawable.getColor() != ((ColorDrawable) (obj)).getColor())
            {
                ((ColorDrawable) (obj)).setColor(colordrawable.getColor());
                return ObjectAnimator.ofArgb(viewgroup, "color", new int[] {
                    colordrawable.getColor(), ((ColorDrawable) (obj)).getColor()
                });
            }
        }
        if(view instanceof TextView)
        {
            viewgroup = (TextView)view;
            int i = ((Integer)transitionvalues.values.get("android:recolor:textColor")).intValue();
            int j = ((Integer)transitionvalues1.values.get("android:recolor:textColor")).intValue();
            if(i != j)
            {
                viewgroup.setTextColor(j);
                return ObjectAnimator.ofArgb(viewgroup, "textColor", new int[] {
                    i, j
                });
            }
        }
        return null;
    }

    private static final String PROPNAME_BACKGROUND = "android:recolor:background";
    private static final String PROPNAME_TEXT_COLOR = "android:recolor:textColor";
}
