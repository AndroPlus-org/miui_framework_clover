// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            DrawFilter

public class PaintFlagsDrawFilter extends DrawFilter
{

    public PaintFlagsDrawFilter(int i, int j)
    {
        mNativeInt = nativeConstructor(i, j);
    }

    private static native long nativeConstructor(int i, int j);
}
