// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.*;

// Referenced classes of package android.security.net.config:
//            ApplicationConfig, NetworkSecurityConfig, NetworkSecurityTrustManager

public class RootTrustManager extends X509ExtendedTrustManager
{

    public RootTrustManager(ApplicationConfig applicationconfig)
    {
        if(applicationconfig == null)
        {
            throw new NullPointerException("config must not be null");
        } else
        {
            mConfig = applicationconfig;
            return;
        }
    }

    public void checkClientTrusted(X509Certificate ax509certificate[], String s)
        throws CertificateException
    {
        mConfig.getConfigForHostname("").getTrustManager().checkClientTrusted(ax509certificate, s);
    }

    public void checkClientTrusted(X509Certificate ax509certificate[], String s, Socket socket)
        throws CertificateException
    {
        mConfig.getConfigForHostname("").getTrustManager().checkClientTrusted(ax509certificate, s, socket);
    }

    public void checkClientTrusted(X509Certificate ax509certificate[], String s, SSLEngine sslengine)
        throws CertificateException
    {
        mConfig.getConfigForHostname("").getTrustManager().checkClientTrusted(ax509certificate, s, sslengine);
    }

    public List checkServerTrusted(X509Certificate ax509certificate[], String s, String s1)
        throws CertificateException
    {
        if(s1 == null && mConfig.hasPerDomainConfigs())
            throw new CertificateException("Domain specific configurations require that the hostname be provided");
        else
            return mConfig.getConfigForHostname(s1).getTrustManager().checkServerTrusted(ax509certificate, s, s1);
    }

    public void checkServerTrusted(X509Certificate ax509certificate[], String s)
        throws CertificateException
    {
        if(mConfig.hasPerDomainConfigs())
        {
            throw new CertificateException("Domain specific configurations require that hostname aware checkServerTrusted(X509Certificate[], String, String) is used");
        } else
        {
            mConfig.getConfigForHostname("").getTrustManager().checkServerTrusted(ax509certificate, s);
            return;
        }
    }

    public void checkServerTrusted(X509Certificate ax509certificate[], String s, Socket socket)
        throws CertificateException
    {
        if(socket instanceof SSLSocket)
        {
            Object obj = ((SSLSocket)socket).getHandshakeSession();
            if(obj == null)
                throw new CertificateException("Not in handshake; no session available");
            obj = ((SSLSession) (obj)).getPeerHost();
            mConfig.getConfigForHostname(((String) (obj))).getTrustManager().checkServerTrusted(ax509certificate, s, socket);
        } else
        {
            checkServerTrusted(ax509certificate, s);
        }
    }

    public void checkServerTrusted(X509Certificate ax509certificate[], String s, SSLEngine sslengine)
        throws CertificateException
    {
        Object obj = sslengine.getHandshakeSession();
        if(obj == null)
        {
            throw new CertificateException("Not in handshake; no session available");
        } else
        {
            obj = ((SSLSession) (obj)).getPeerHost();
            mConfig.getConfigForHostname(((String) (obj))).getTrustManager().checkServerTrusted(ax509certificate, s, sslengine);
            return;
        }
    }

    public X509Certificate[] getAcceptedIssuers()
    {
        return mConfig.getConfigForHostname("").getTrustManager().getAcceptedIssuers();
    }

    public boolean isSameTrustConfiguration(String s, String s1)
    {
        return mConfig.getConfigForHostname(s).equals(mConfig.getConfigForHostname(s1));
    }

    private final ApplicationConfig mConfig;
}
