// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm.split;

import android.content.res.AssetManager;

public interface SplitAssetLoader
    extends AutoCloseable
{

    public abstract AssetManager getBaseAssetManager()
        throws android.content.pm.PackageParser.PackageParserException;

    public abstract AssetManager getSplitAssetManager(int i)
        throws android.content.pm.PackageParser.PackageParserException;
}
