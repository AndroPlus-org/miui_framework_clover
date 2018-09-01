// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.util.Pair;
import java.util.*;
import javax.net.ssl.X509TrustManager;

// Referenced classes of package android.security.net.config:
//            ConfigSource, RootTrustManager, Domain, NetworkSecurityConfig

public final class ApplicationConfig
{

    public ApplicationConfig(ConfigSource configsource)
    {
        mConfigSource = configsource;
        mInitialized = false;
    }

    private void ensureInitialized()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mInitialized;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        mConfigs = mConfigSource.getPerDomainConfigs();
        mDefaultConfig = mConfigSource.getDefaultConfig();
        mConfigSource = null;
        RootTrustManager roottrustmanager = JVM INSTR new #52  <Class RootTrustManager>;
        roottrustmanager.RootTrustManager(this);
        mTrustManager = roottrustmanager;
        mInitialized = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static ApplicationConfig getDefaultInstance()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        ApplicationConfig applicationconfig = sInstance;
        obj;
        JVM INSTR monitorexit ;
        return applicationconfig;
        Exception exception;
        exception;
        throw exception;
    }

    public static void setDefaultInstance(ApplicationConfig applicationconfig)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        sInstance = applicationconfig;
        obj;
        JVM INSTR monitorexit ;
        return;
        applicationconfig;
        throw applicationconfig;
    }

    public NetworkSecurityConfig getConfigForHostname(String s)
    {
        ensureInitialized();
        if(s == null || s.isEmpty() || mConfigs == null)
            return mDefaultConfig;
        if(s.charAt(0) == '.')
            throw new IllegalArgumentException("hostname must not begin with a .");
        s = s.toLowerCase(Locale.US);
        String s1 = s;
        if(s.charAt(s.length() - 1) == '.')
            s1 = s.substring(0, s.length() - 1);
        s = null;
        Iterator iterator = mConfigs.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            Domain domain = (Domain)pair.first;
            NetworkSecurityConfig networksecurityconfig = (NetworkSecurityConfig)pair.second;
            if(domain.hostname.equals(s1))
                return networksecurityconfig;
            if(domain.subdomainsIncluded && s1.endsWith(domain.hostname) && s1.charAt(s1.length() - domain.hostname.length() - 1) == '.')
                if(s == null)
                    s = pair;
                else
                if(domain.hostname.length() > ((Domain)((Pair) (s)).first).hostname.length())
                    s = pair;
        } while(true);
        if(s != null)
            return (NetworkSecurityConfig)((Pair) (s)).second;
        else
            return mDefaultConfig;
    }

    public X509TrustManager getTrustManager()
    {
        ensureInitialized();
        return mTrustManager;
    }

    public void handleTrustStorageUpdate()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mInitialized;
        if(flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        mDefaultConfig.handleTrustStorageUpdate();
        if(mConfigs != null)
        {
            HashSet hashset = JVM INSTR new #150 <Class HashSet>;
            hashset.HashSet(mConfigs.size());
            Iterator iterator = mConfigs.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Pair pair = (Pair)iterator.next();
                if(hashset.add((NetworkSecurityConfig)pair.second))
                    ((NetworkSecurityConfig)pair.second).handleTrustStorageUpdate();
            } while(true);
        }
        break MISSING_BLOCK_LABEL_119;
        Exception exception;
        exception;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
    }

    public boolean hasPerDomainConfigs()
    {
        ensureInitialized();
        boolean flag;
        if(mConfigs != null)
            flag = mConfigs.isEmpty() ^ true;
        else
            flag = false;
        return flag;
    }

    public boolean isCleartextTrafficPermitted()
    {
label0:
        {
            ensureInitialized();
            if(mConfigs == null)
                break label0;
            Iterator iterator = mConfigs.iterator();
            do
                if(!iterator.hasNext())
                    break label0;
            while(((NetworkSecurityConfig)((Pair)iterator.next()).second).isCleartextTrafficPermitted());
            return false;
        }
        return mDefaultConfig.isCleartextTrafficPermitted();
    }

    public boolean isCleartextTrafficPermitted(String s)
    {
        return getConfigForHostname(s).isCleartextTrafficPermitted();
    }

    private static ApplicationConfig sInstance;
    private static Object sLock = new Object();
    private ConfigSource mConfigSource;
    private Set mConfigs;
    private NetworkSecurityConfig mDefaultConfig;
    private boolean mInitialized;
    private final Object mLock = new Object();
    private X509TrustManager mTrustManager;

}
