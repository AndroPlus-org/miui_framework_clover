// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            PathEffect

public class SumPathEffect extends PathEffect
{

    public SumPathEffect(PathEffect patheffect, PathEffect patheffect1)
    {
        native_instance = nativeCreate(patheffect.native_instance, patheffect1.native_instance);
    }

    private static native long nativeCreate(long l, long l1);
}
