// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.security.KeyStore;
import java.util.Set;

// Referenced classes of package android.security.net.config:
//            ConfigSource, CertificatesEntryRef, KeyStoreCertificateSource, NetworkSecurityConfig

class KeyStoreConfigSource
    implements ConfigSource
{

    public KeyStoreConfigSource(KeyStore keystore)
    {
        mConfig = (new NetworkSecurityConfig.Builder()).addCertificatesEntryRef(new CertificatesEntryRef(new KeyStoreCertificateSource(keystore), false)).build();
    }

    public NetworkSecurityConfig getDefaultConfig()
    {
        return mConfig;
    }

    public Set getPerDomainConfigs()
    {
        return null;
    }

    private final NetworkSecurityConfig mConfig;
}
