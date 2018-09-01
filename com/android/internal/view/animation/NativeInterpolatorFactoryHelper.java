// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.animation;


public final class NativeInterpolatorFactoryHelper
{

    private NativeInterpolatorFactoryHelper()
    {
    }

    public static native long createAccelerateDecelerateInterpolator();

    public static native long createAccelerateInterpolator(float f);

    public static native long createAnticipateInterpolator(float f);

    public static native long createAnticipateOvershootInterpolator(float f);

    public static native long createBounceInterpolator();

    public static native long createCycleInterpolator(float f);

    public static native long createDecelerateInterpolator(float f);

    public static native long createLinearInterpolator();

    public static native long createLutInterpolator(float af[]);

    public static native long createOvershootInterpolator(float f);

    public static native long createPathInterpolator(float af[], float af1[]);
}
