// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            PathEffect

public class CornerPathEffect extends PathEffect
{

    public CornerPathEffect(float f)
    {
        native_instance = nativeCreate(f);
    }

    private static native long nativeCreate(float f);
}
