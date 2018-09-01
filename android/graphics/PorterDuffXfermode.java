// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Xfermode

public class PorterDuffXfermode extends Xfermode
{

    public PorterDuffXfermode(PorterDuff.Mode mode)
    {
        porterDuffMode = mode.nativeInt;
    }
}
