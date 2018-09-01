// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


public class Xfermode
{

    public Xfermode()
    {
        porterDuffMode = DEFAULT;
    }

    static final int DEFAULT;
    int porterDuffMode;

    static 
    {
        DEFAULT = PorterDuff.Mode.SRC_OVER.nativeInt;
    }
}
