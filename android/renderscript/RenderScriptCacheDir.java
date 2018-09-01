// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import java.io.File;

public class RenderScriptCacheDir
{

    public RenderScriptCacheDir()
    {
    }

    public static void setupDiskCache(File file)
    {
        mCacheDir = file;
    }

    static File mCacheDir;
}
