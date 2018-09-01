// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import libcore.net.NetworkSecurityPolicy;

// Referenced classes of package android.security.net.config:
//            ApplicationConfig

public class ConfigNetworkSecurityPolicy extends NetworkSecurityPolicy
{

    public ConfigNetworkSecurityPolicy(ApplicationConfig applicationconfig)
    {
        mConfig = applicationconfig;
    }

    public boolean isCertificateTransparencyVerificationRequired(String s)
    {
        return false;
    }

    public boolean isCleartextTrafficPermitted()
    {
        return mConfig.isCleartextTrafficPermitted();
    }

    public boolean isCleartextTrafficPermitted(String s)
    {
        return mConfig.isCleartextTrafficPermitted(s);
    }

    private final ApplicationConfig mConfig;
}
