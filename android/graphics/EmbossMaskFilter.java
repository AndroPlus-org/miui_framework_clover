// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            MaskFilter

public class EmbossMaskFilter extends MaskFilter
{

    public EmbossMaskFilter(float af[], float f, float f1, float f2)
    {
        if(af.length < 3)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            native_instance = nativeConstructor(af, f, f1, f2);
            return;
        }
    }

    private static native long nativeConstructor(float af[], float f, float f1, float f2);
}
