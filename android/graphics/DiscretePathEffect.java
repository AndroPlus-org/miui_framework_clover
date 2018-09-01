// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            PathEffect

public class DiscretePathEffect extends PathEffect
{

    public DiscretePathEffect(float f, float f1)
    {
        native_instance = nativeCreate(f, f1);
    }

    private static native long nativeCreate(float f, float f1);
}
