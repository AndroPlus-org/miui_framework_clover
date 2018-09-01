// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package android.transition:
//            TransitionSet, Fade, ChangeBounds

public class AutoTransition extends TransitionSet
{

    public AutoTransition()
    {
        init();
    }

    public AutoTransition(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init();
    }

    private void init()
    {
        setOrdering(1);
        addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
    }
}
