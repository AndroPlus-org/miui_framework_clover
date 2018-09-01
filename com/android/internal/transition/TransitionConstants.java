// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.transition;

import android.animation.TimeInterpolator;
import android.view.animation.PathInterpolator;

class TransitionConstants
{

    TransitionConstants()
    {
    }

    static final TimeInterpolator FAST_OUT_SLOW_IN = new PathInterpolator(0.4F, 0.0F, 0.2F, 1.0F);
    static final TimeInterpolator LINEAR_OUT_SLOW_IN = new PathInterpolator(0.0F, 0.0F, 0.2F, 1.0F);

}
