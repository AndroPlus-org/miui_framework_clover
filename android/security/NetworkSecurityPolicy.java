// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.content.Context;
import android.security.net.config.ApplicationConfig;
import android.security.net.config.ManifestConfigSource;

// Referenced classes of package android.security:
//            FrameworkNetworkSecurityPolicy

public class NetworkSecurityPolicy
{

    private NetworkSecurityPolicy()
    {
    }

    public static ApplicationConfig getApplicationConfigForPackage(Context context, String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return new ApplicationConfig(new ManifestConfigSource(context.createPackageContext(s, 0)));
    }

    public static NetworkSecurityPolicy getInstance()
    {
        return INSTANCE;
    }

    public void handleTrustStorageUpdate()
    {
        ApplicationConfig applicationconfig = ApplicationConfig.getDefaultInstance();
        if(applicationconfig != null)
            applicationconfig.handleTrustStorageUpdate();
    }

    public boolean isCleartextTrafficPermitted()
    {
        return libcore.net.NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    public boolean isCleartextTrafficPermitted(String s)
    {
        return libcore.net.NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(s);
    }

    public void setCleartextTrafficPermitted(boolean flag)
    {
        libcore.net.NetworkSecurityPolicy.setInstance(new FrameworkNetworkSecurityPolicy(flag));
    }

    private static final NetworkSecurityPolicy INSTANCE = new NetworkSecurityPolicy();

}
