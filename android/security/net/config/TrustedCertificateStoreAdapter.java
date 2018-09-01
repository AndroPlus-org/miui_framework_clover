// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import com.android.org.conscrypt.TrustedCertificateStore;
import java.io.File;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

// Referenced classes of package android.security.net.config:
//            NetworkSecurityConfig, TrustAnchor

public class TrustedCertificateStoreAdapter extends TrustedCertificateStore
{

    public TrustedCertificateStoreAdapter(NetworkSecurityConfig networksecurityconfig)
    {
        mConfig = networksecurityconfig;
    }

    public Set aliases()
    {
        throw new UnsupportedOperationException();
    }

    public Set allSystemAliases()
    {
        throw new UnsupportedOperationException();
    }

    public boolean containsAlias(String s)
    {
        throw new UnsupportedOperationException();
    }

    public Set findAllIssuers(X509Certificate x509certificate)
    {
        return mConfig.findAllCertificatesByIssuerAndSignature(x509certificate);
    }

    public X509Certificate findIssuer(X509Certificate x509certificate)
    {
        x509certificate = mConfig.findTrustAnchorByIssuerAndSignature(x509certificate);
        if(x509certificate == null)
            return null;
        else
            return ((TrustAnchor) (x509certificate)).certificate;
    }

    public Certificate getCertificate(String s)
    {
        throw new UnsupportedOperationException();
    }

    public Certificate getCertificate(String s, boolean flag)
    {
        throw new UnsupportedOperationException();
    }

    public String getCertificateAlias(Certificate certificate)
    {
        throw new UnsupportedOperationException();
    }

    public String getCertificateAlias(Certificate certificate, boolean flag)
    {
        throw new UnsupportedOperationException();
    }

    public File getCertificateFile(File file, X509Certificate x509certificate)
    {
        throw new UnsupportedOperationException();
    }

    public Date getCreationDate(String s)
    {
        throw new UnsupportedOperationException();
    }

    public X509Certificate getTrustAnchor(X509Certificate x509certificate)
    {
        x509certificate = mConfig.findTrustAnchorBySubjectAndPublicKey(x509certificate);
        if(x509certificate == null)
            return null;
        else
            return ((TrustAnchor) (x509certificate)).certificate;
    }

    public boolean isUserAddedCertificate(X509Certificate x509certificate)
    {
        x509certificate = mConfig.findTrustAnchorBySubjectAndPublicKey(x509certificate);
        if(x509certificate == null)
            return false;
        else
            return ((TrustAnchor) (x509certificate)).overridesPins;
    }

    public Set userAliases()
    {
        throw new UnsupportedOperationException();
    }

    private final NetworkSecurityConfig mConfig;
}
