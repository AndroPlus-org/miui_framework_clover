// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.content.Context;
import java.security.Provider;
import java.security.Security;
import libcore.net.NetworkSecurityPolicy;

// Referenced classes of package android.security.net.config:
//            ApplicationConfig, ManifestConfigSource, ConfigNetworkSecurityPolicy

public final class NetworkSecurityConfigProvider extends Provider
{

    public NetworkSecurityConfigProvider()
    {
        super("AndroidNSSP", 1.0D, "Android Network Security Policy Provider");
        put("TrustManagerFactory.PKIX", (new StringBuilder()).append(PREFIX).append("RootTrustManagerFactorySpi").toString());
        put("Alg.Alias.TrustManagerFactory.X509", "PKIX");
    }

    public static void install(Context context)
    {
        context = new ApplicationConfig(new ManifestConfigSource(context));
        ApplicationConfig.setDefaultInstance(context);
        int i = Security.insertProviderAt(new NetworkSecurityConfigProvider(), 1);
        if(i != 1)
        {
            throw new RuntimeException((new StringBuilder()).append("Failed to install provider as highest priority provider. Provider was installed at position ").append(i).toString());
        } else
        {
            NetworkSecurityPolicy.setInstance(new ConfigNetworkSecurityPolicy(context));
            return;
        }
    }

    private static final String PREFIX = (new StringBuilder()).append(android/security/net/config/NetworkSecurityConfigProvider.getPackage().getName()).append(".").toString();

}
