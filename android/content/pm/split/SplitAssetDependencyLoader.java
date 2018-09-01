// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm.split;

import android.content.pm.PackageParser;
import android.content.res.AssetManager;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import libcore.io.IoUtils;

// Referenced classes of package android.content.pm.split:
//            SplitDependencyLoader, SplitAssetLoader

public class SplitAssetDependencyLoader extends SplitDependencyLoader
    implements SplitAssetLoader
{

    public SplitAssetDependencyLoader(android.content.pm.PackageParser.PackageLite packagelite, SparseArray sparsearray, int i)
    {
        super(sparsearray);
        mSplitPaths = new String[packagelite.splitCodePaths.length + 1];
        mSplitPaths[0] = packagelite.baseCodePath;
        System.arraycopy(packagelite.splitCodePaths, 0, mSplitPaths, 1, packagelite.splitCodePaths.length);
        mFlags = i;
        mCachedPaths = new String[mSplitPaths.length][];
        mCachedAssetManagers = new AssetManager[mSplitPaths.length];
    }

    private static AssetManager createAssetManagerWithPaths(String as[], int i)
        throws android.content.pm.PackageParser.PackageParserException
    {
        AssetManager assetmanager;
        int j;
        String s;
        assetmanager = new AssetManager();
        int k;
        StringBuilder stringbuilder;
        try
        {
            assetmanager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, android.os.Build.VERSION.RESOURCES_SDK_INT);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            IoUtils.closeQuietly(assetmanager);
            throw as;
        }
        j = 0;
        k = as.length;
_L2:
        if(j >= k)
            break; /* Loop/switch isn't completed */
        s = as[j];
        if((i & 4) == 0)
            break MISSING_BLOCK_LABEL_109;
        if(PackageParser.isApkPath(s) ^ true)
        {
            as = JVM INSTR new #55  <Class android.content.pm.PackageParser$PackageParserException>;
            stringbuilder = JVM INSTR new #77  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            as.android.content.pm.PackageParser.PackageParserException(-100, stringbuilder.append("Invalid package file: ").append(s).toString());
            throw as;
        }
        if(assetmanager.addAssetPath(s) == 0)
        {
            android.content.pm.PackageParser.PackageParserException packageparserexception = JVM INSTR new #55  <Class android.content.pm.PackageParser$PackageParserException>;
            as = JVM INSTR new #77  <Class StringBuilder>;
            as.StringBuilder();
            packageparserexception.android.content.pm.PackageParser.PackageParserException(-101, as.append("Failed adding asset path: ").append(s).toString());
            throw packageparserexception;
        }
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return assetmanager;
    }

    public void close()
        throws Exception
    {
        AssetManager aassetmanager[] = mCachedAssetManagers;
        int i = 0;
        for(int j = aassetmanager.length; i < j; i++)
            IoUtils.closeQuietly(aassetmanager[i]);

    }

    protected void constructSplit(int i, int ai[], int j)
        throws android.content.pm.PackageParser.PackageParserException
    {
        boolean flag = false;
        ArrayList arraylist = new ArrayList();
        if(j >= 0)
            Collections.addAll(arraylist, mCachedPaths[j]);
        arraylist.add(mSplitPaths[i]);
        int l = ai.length;
        for(j = ((flag) ? 1 : 0); j < l; j++)
        {
            int k = ai[j];
            arraylist.add(mSplitPaths[k]);
        }

        mCachedPaths[i] = (String[])arraylist.toArray(new String[arraylist.size()]);
        mCachedAssetManagers[i] = createAssetManagerWithPaths(mCachedPaths[i], mFlags);
    }

    public AssetManager getBaseAssetManager()
        throws android.content.pm.PackageParser.PackageParserException
    {
        loadDependenciesForSplit(0);
        return mCachedAssetManagers[0];
    }

    public AssetManager getSplitAssetManager(int i)
        throws android.content.pm.PackageParser.PackageParserException
    {
        loadDependenciesForSplit(i + 1);
        return mCachedAssetManagers[i + 1];
    }

    protected boolean isSplitCached(int i)
    {
        boolean flag;
        if(mCachedAssetManagers[i] != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private AssetManager mCachedAssetManagers[];
    private String mCachedPaths[][];
    private final int mFlags;
    private final String mSplitPaths[];
}
