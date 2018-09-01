// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.animation;

import android.animation.LayoutTransition;

public class LayoutTransitionUtil
{

    public LayoutTransitionUtil()
    {
    }

    public static void cancel(LayoutTransition layouttransition)
    {
        layouttransition.cancel();
    }

    public static void endChangingAnimations(LayoutTransition layouttransition)
    {
        layouttransition.endChangingAnimations();
    }
}
