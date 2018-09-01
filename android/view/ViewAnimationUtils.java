// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.Animator;
import android.animation.RevealAnimator;

// Referenced classes of package android.view:
//            View

public final class ViewAnimationUtils
{

    private ViewAnimationUtils()
    {
    }

    public static Animator createCircularReveal(View view, int i, int j, float f, float f1)
    {
        return new RevealAnimator(view, i, j, f, f1);
    }
}
