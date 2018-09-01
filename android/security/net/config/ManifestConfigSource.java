// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.util.Log;
import java.util.Set;

// Referenced classes of package android.security.net.config:
//            ConfigSource, XmlConfigSource, NetworkSecurityConfig

public class ManifestConfigSource
    implements ConfigSource
{
    private static final class DefaultConfigSource
        implements ConfigSource
    {

        public NetworkSecurityConfig getDefaultConfig()
        {
            return mDefaultConfig;
        }

        public Set getPerDomainConfigs()
        {
            return null;
        }

        private final NetworkSecurityConfig mDefaultConfig;

        public DefaultConfigSource(boolean flag, int i, int j)
        {
            mDefaultConfig = NetworkSecurityConfig.getDefaultBuilder(i, j).setCleartextTrafficPermitted(flag).build();
        }
    }


    public ManifestConfigSource(Context context)
    {
        mContext = context;
        context = context.getApplicationInfo();
        mApplicationInfoFlags = ((ApplicationInfo) (context)).flags;
        mTargetSdkVersion = ((ApplicationInfo) (context)).targetSdkVersion;
        mConfigResourceId = ((ApplicationInfo) (context)).networkSecurityConfigRes;
        mTargetSandboxVesrsion = ((ApplicationInfo) (context)).targetSandboxVersion;
    }

    private ConfigSource getConfigSource()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        if(mConfigSource == null)
            break MISSING_BLOCK_LABEL_23;
        obj1 = mConfigSource;
        obj;
        JVM INSTR monitorexit ;
        return ((ConfigSource) (obj1));
        if(mConfigResourceId == 0) goto _L2; else goto _L1
_L1:
        boolean flag;
        if((mApplicationInfoFlags & 2) != 0)
            flag = true;
        else
            flag = false;
        obj1 = JVM INSTR new #71  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("NetworkSecurityConfig", ((StringBuilder) (obj1)).append("Using Network Security Config from resource ").append(mContext.getResources().getResourceEntryName(mConfigResourceId)).append(" debugBuild: ").append(flag).toString());
        obj1 = JVM INSTR new #105 <Class XmlConfigSource>;
        ((XmlConfigSource) (obj1)).XmlConfigSource(mContext, mConfigResourceId, flag, mTargetSdkVersion, mTargetSandboxVesrsion);
_L3:
        mConfigSource = ((ConfigSource) (obj1));
        obj1 = mConfigSource;
        obj;
        JVM INSTR monitorexit ;
        return ((ConfigSource) (obj1));
_L2:
        Log.d("NetworkSecurityConfig", "No Network Security Config specified, using platform default");
        if((mApplicationInfoFlags & 0x8000000) != 0)
        {
            if(mTargetSandboxVesrsion < 2)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        obj1 = new DefaultConfigSource(flag, mTargetSdkVersion, mTargetSandboxVesrsion);
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    public NetworkSecurityConfig getDefaultConfig()
    {
        return getConfigSource().getDefaultConfig();
    }

    public Set getPerDomainConfigs()
    {
        return getConfigSource().getPerDomainConfigs();
    }

    private static final boolean DBG = true;
    private static final String LOG_TAG = "NetworkSecurityConfig";
    private final int mApplicationInfoFlags;
    private final int mConfigResourceId;
    private ConfigSource mConfigSource;
    private final Context mContext;
    private final Object mLock = new Object();
    private final int mTargetSandboxVesrsion;
    private final int mTargetSdkVersion;
}
