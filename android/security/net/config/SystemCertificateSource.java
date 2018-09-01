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

public final class SystemCertificateSource extends DirectoryCertificateSource
{
    private static class NoPreloadHolder
    {

        static SystemCertificateSource _2D_get0()
        {
            return INSTANCE;
        }

        private static final SystemCertificateSource INSTANCE = new SystemCertificateSource(null);


        private NoPreloadHolder()
        {
        }
    }


    private SystemCertificateSource()
    {
        super(new File((new StringBuilder()).append(System.getenv("ANDROID_ROOT")).append("/etc/security/cacerts").toString()));
        mUserRemovedCaDir = new File(Environment.getUserConfigDirectory(UserHandle.myUserId()), "cacerts-removed");
    }

    SystemCertificateSource(SystemCertificateSource systemcertificatesource)
    {
        this();
    }

    public static SystemCertificateSource getInstance()
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
        return (new File(mUserRemovedCaDir, s)).exists();
    }

    private final File mUserRemovedCaDir;
}
