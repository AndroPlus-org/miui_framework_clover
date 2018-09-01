// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            PathEffect

public class DashPathEffect extends PathEffect
{

    public DashPathEffect(float af[], float f)
    {
        if(af.length < 2)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            native_instance = nativeCreate(af, f);
            return;
        }
    }

    private static native long nativeCreate(float af[], float f);
}
