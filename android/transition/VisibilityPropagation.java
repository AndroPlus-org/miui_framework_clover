// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.view.View;
import java.util.Map;

// Referenced classes of package android.transition:
//            TransitionPropagation, TransitionValues

public abstract class VisibilityPropagation extends TransitionPropagation
{

    public VisibilityPropagation()
    {
    }

    private static int getViewCoordinate(TransitionValues transitionvalues, int i)
    {
        if(transitionvalues == null)
            return -1;
        transitionvalues = (int[])transitionvalues.values.get("android:visibilityPropagation:center");
        if(transitionvalues == null)
            return -1;
        else
            return transitionvalues[i];
    }

    public void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        Integer integer = (Integer)transitionvalues.values.get("android:visibility:visibility");
        Integer integer1 = integer;
        if(integer == null)
            integer1 = Integer.valueOf(view.getVisibility());
        transitionvalues.values.put("android:visibilityPropagation:visibility", integer1);
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        ai[0] = ai[0] + Math.round(view.getTranslationX());
        ai[0] = ai[0] + view.getWidth() / 2;
        ai[1] = ai[1] + Math.round(view.getTranslationY());
        ai[1] = ai[1] + view.getHeight() / 2;
        transitionvalues.values.put("android:visibilityPropagation:center", ai);
    }

    public String[] getPropagationProperties()
    {
        return VISIBILITY_PROPAGATION_VALUES;
    }

    public int getViewVisibility(TransitionValues transitionvalues)
    {
        if(transitionvalues == null)
            return 8;
        transitionvalues = (Integer)transitionvalues.values.get("android:visibilityPropagation:visibility");
        if(transitionvalues == null)
            return 8;
        else
            return transitionvalues.intValue();
    }

    public int getViewX(TransitionValues transitionvalues)
    {
        return getViewCoordinate(transitionvalues, 0);
    }

    public int getViewY(TransitionValues transitionvalues)
    {
        return getViewCoordinate(transitionvalues, 1);
    }

    private static final String PROPNAME_VIEW_CENTER = "android:visibilityPropagation:center";
    private static final String PROPNAME_VISIBILITY = "android:visibilityPropagation:visibility";
    private static final String VISIBILITY_PROPAGATION_VALUES[] = {
        "android:visibilityPropagation:visibility", "android:visibilityPropagation:center"
    };

}
