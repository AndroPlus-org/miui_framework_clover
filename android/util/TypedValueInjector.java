// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


// Referenced classes of package android.util:
//            DisplayMetrics

class TypedValueInjector
{

    TypedValueInjector()
    {
    }

    static float miuiScale(float f, DisplayMetrics displaymetrics)
    {
        return displaymetrics.scaledDensity * f;
    }
}
