// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm.split;

import android.content.pm.PackageParser;
import android.content.res.AssetManager;
import com.android.internal.util.ArrayUtils;
import libcore.io.IoUtils;

// Referenced classes of package android.content.pm.split:
//            SplitAssetLoader

public class DefaultSplitAssetLoader
    implements SplitAssetLoader
{

    public DefaultSplitAssetLoader(android.content.pm.PackageParser.PackageLite packagelite, int i)
    {
        mBaseCodePath = packagelite.baseCodePath;
        mSplitCodePaths = packagelite.splitCodePaths;
        mFlags = i;
    }

    private static void loadApkIntoAssetManager(AssetManager assetmanager, String s, int i)
        throws android.content.pm.PackageParser.PackageParserException
    {
        if((i & 4) != 0 && PackageParser.isApkPath(s) ^ true)
            throw new android.content.pm.PackageParser.PackageParserException(-100, (new StringBuilder()).append("Invalid package file: ").append(s).toString());
        if(assetmanager.addAssetPath(s) == 0)
            throw new android.content.pm.PackageParser.PackageParserException(-101, (new StringBuilder()).append("Failed adding asset path: ").append(s).toString());
        else
            return;
    }

    public void close()
        throws Exception
    {
        if(mCachedAssetManager != null)
            IoUtils.closeQuietly(mCachedAssetManager);
    }

    public AssetManager getBaseAssetManager()
        throws android.content.pm.PackageParser.PackageParserException
    {
        AssetManager assetmanager;
        AssetManager assetmanager1;
        if(mCachedAssetManager != null)
            return mCachedAssetManager;
        assetmanager = new AssetManager();
        assetmanager1 = assetmanager;
        assetmanager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, android.os.Build.VERSION.RESOURCES_SDK_INT);
        assetmanager1 = assetmanager;
        loadApkIntoAssetManager(assetmanager, mBaseCodePath, mFlags);
        assetmanager1 = assetmanager;
        if(ArrayUtils.isEmpty(mSplitCodePaths)) goto _L2; else goto _L1
_L1:
        assetmanager1 = assetmanager;
        String as[] = mSplitCodePaths;
        int i;
        i = 0;
        assetmanager1 = assetmanager;
        int j = as.length;
_L3:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        assetmanager1 = assetmanager;
        loadApkIntoAssetManager(assetmanager, as[i], mFlags);
        i++;
        if(true) goto _L3; else goto _L2
_L2:
        assetmanager1 = assetmanager;
        mCachedAssetManager = assetmanager;
        assetmanager1 = null;
        assetmanager = mCachedAssetManager;
        return assetmanager;
        Exception exception;
        exception;
        if(assetmanager1 != null)
            IoUtils.closeQuietly(assetmanager1);
        throw exception;
    }

    public AssetManager getSplitAssetManager(int i)
        throws android.content.pm.PackageParser.PackageParserException
    {
        return getBaseAssetManager();
    }

    private final String mBaseCodePath;
    private AssetManager mCachedAssetManager;
    private final int mFlags;
    private final String mSplitCodePaths[];
}
