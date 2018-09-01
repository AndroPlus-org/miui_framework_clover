// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.security.*;
import javax.net.ssl.*;

// Referenced classes of package android.security.net.config:
//            ApplicationConfig, KeyStoreConfigSource, NetworkSecurityConfig

public class RootTrustManagerFactorySpi extends TrustManagerFactorySpi
{
    public static final class ApplicationConfigParameters
        implements ManagerFactoryParameters
    {

        public final ApplicationConfig config;

        public ApplicationConfigParameters(ApplicationConfig applicationconfig)
        {
            config = applicationconfig;
        }
    }


    public RootTrustManagerFactorySpi()
    {
    }

    public TrustManager[] engineGetTrustManagers()
    {
        if(mApplicationConfig == null)
            throw new IllegalStateException("TrustManagerFactory not initialized");
        else
            return (new TrustManager[] {
                mApplicationConfig.getTrustManager()
            });
    }

    public void engineInit(KeyStore keystore)
        throws KeyStoreException
    {
        if(keystore != null)
            mApplicationConfig = new ApplicationConfig(new KeyStoreConfigSource(keystore));
        else
            mApplicationConfig = ApplicationConfig.getDefaultInstance();
    }

    public void engineInit(ManagerFactoryParameters managerfactoryparameters)
        throws InvalidAlgorithmParameterException
    {
        if(!(managerfactoryparameters instanceof ApplicationConfigParameters))
        {
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Unsupported spec: ").append(managerfactoryparameters).append(". Only ").append(android/security/net/config/RootTrustManagerFactorySpi$ApplicationConfigParameters.getName()).append(" supported").toString());
        } else
        {
            mApplicationConfig = ((ApplicationConfigParameters)managerfactoryparameters).config;
            return;
        }
    }

    private ApplicationConfig mApplicationConfig;
    private NetworkSecurityConfig mConfig;
}
