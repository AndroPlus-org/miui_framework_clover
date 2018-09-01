// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.os.Environment;
import android.os.UserHandle;
import java.io.File;
import java.security.cert.X509Certificate;
import java.util.Set;

// Referenced classes of package android.security.net.config:
//            DirectoryCertificateSource

public final class UserCertificateSource extends DirectoryCertificateSource
{
    private static class NoPreloadHolder
    {

        static UserCertificateSource _2D_get0()
        {
            return INSTANCE;
        }

        private static final UserCertificateSource INSTANCE = new UserCertificateSource(null);


        private NoPreloadHolder()
        {
        }
    }


    private UserCertificateSource()
    {
        super(new File(Environment.getUserConfigDirectory(UserHandle.myUserId()), "cacerts-added"));
    }

    UserCertificateSource(UserCertificateSource usercertificatesource)
    {
        this();
    }

    public static UserCertificateSource getInstance()
    {
        return NoPreloadHolder._2D_get0();
    }

    public volatile Set findAllByIssuerAndSignature(X509Certificate x509certificate)
    {
        return super.findAllByIssuerAndSignature(x509certificate);
    }

    public volatile X509Certificate findByIssuerAndSignature(X509Certificate x509certificate)
    {
        return super.findByIssuerAndSignature(x509certificate);
    }

    public volatile X509Certificate findBySubjectAndPublicKey(X509Certificate x509certificate)
    {
        return super.findBySubjectAndPublicKey(x509certificate);
    }

    public volatile Set getCertificates()
    {
        return super.getCertificates();
    }

    public volatile void handleTrustStorageUpdate()
    {
        super.handleTrustStorageUpdate();
    }

    protected boolean isCertMarkedAsRemoved(String s)
    {
        return false;
    }
}
