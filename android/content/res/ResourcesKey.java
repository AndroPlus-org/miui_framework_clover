// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Objects;

// Referenced classes of package android.content.res:
//            Configuration, CompatibilityInfo

public final class ResourcesKey
{

    public ResourcesKey(String s, String as[], String as1[], String as2[], int i, Configuration configuration, CompatibilityInfo compatibilityinfo)
    {
        mResDir = s;
        mSplitResDirs = as;
        mOverlayDirs = as1;
        mLibDirs = as2;
        mDisplayId = i;
        if(configuration == null)
            configuration = Configuration.EMPTY;
        mOverrideConfiguration = new Configuration(configuration);
        if(compatibilityinfo == null)
            compatibilityinfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        mCompatInfo = compatibilityinfo;
        mHash = ((((((Objects.hashCode(mResDir) + 527) * 31 + Arrays.hashCode(mSplitResDirs)) * 31 + Arrays.hashCode(mOverlayDirs)) * 31 + Arrays.hashCode(mLibDirs)) * 31 + mDisplayId) * 31 + Objects.hashCode(mOverrideConfiguration)) * 31 + Objects.hashCode(mCompatInfo);
    }

    private static boolean anyStartsWith(String as[], String s)
    {
        if(as != null)
        {
            int i = as.length;
            for(int j = 0; j < i; j++)
            {
                String s1 = as[j];
                if(s1 != null && s1.startsWith(s))
                    return true;
            }

        }
        return false;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof ResourcesKey))
            return false;
        obj = (ResourcesKey)obj;
        if(mHash != ((ResourcesKey) (obj)).mHash)
            return false;
        if(!Objects.equals(mResDir, ((ResourcesKey) (obj)).mResDir))
            return false;
        if(!Arrays.equals(mSplitResDirs, ((ResourcesKey) (obj)).mSplitResDirs))
            return false;
        if(!Arrays.equals(mOverlayDirs, ((ResourcesKey) (obj)).mOverlayDirs))
            return false;
        if(!Arrays.equals(mLibDirs, ((ResourcesKey) (obj)).mLibDirs))
            return false;
        if(mDisplayId != ((ResourcesKey) (obj)).mDisplayId)
            return false;
        if(!Objects.equals(mOverrideConfiguration, ((ResourcesKey) (obj)).mOverrideConfiguration))
            return false;
        return Objects.equals(mCompatInfo, ((ResourcesKey) (obj)).mCompatInfo);
    }

    public boolean hasOverrideConfiguration()
    {
        return Configuration.EMPTY.equals(mOverrideConfiguration) ^ true;
    }

    public int hashCode()
    {
        return mHash;
    }

    public boolean isPathReferenced(String s)
    {
        boolean flag = true;
        if(mResDir != null && mResDir.startsWith(s))
            return true;
        boolean flag1 = flag;
        if(!anyStartsWith(mSplitResDirs, s))
        {
            flag1 = flag;
            if(!anyStartsWith(mOverlayDirs, s))
                flag1 = anyStartsWith(mLibDirs, s);
        }
        return flag1;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("ResourcesKey{");
        stringbuilder.append(" mHash=").append(Integer.toHexString(mHash));
        stringbuilder.append(" mResDir=").append(mResDir);
        stringbuilder.append(" mSplitDirs=[");
        if(mSplitResDirs != null)
            stringbuilder.append(TextUtils.join(",", mSplitResDirs));
        stringbuilder.append("]");
        stringbuilder.append(" mOverlayDirs=[");
        if(mOverlayDirs != null)
            stringbuilder.append(TextUtils.join(",", mOverlayDirs));
        stringbuilder.append("]");
        stringbuilder.append(" mLibDirs=[");
        if(mLibDirs != null)
            stringbuilder.append(TextUtils.join(",", mLibDirs));
        stringbuilder.append("]");
        stringbuilder.append(" mDisplayId=").append(mDisplayId);
        stringbuilder.append(" mOverrideConfig=").append(Configuration.resourceQualifierString(mOverrideConfiguration));
        stringbuilder.append(" mCompatInfo=").append(mCompatInfo);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public final CompatibilityInfo mCompatInfo;
    public final int mDisplayId;
    private final int mHash;
    public final String mLibDirs[];
    public final String mOverlayDirs[];
    public final Configuration mOverrideConfiguration;
    public final String mResDir;
    public final String mSplitResDirs[];
}
