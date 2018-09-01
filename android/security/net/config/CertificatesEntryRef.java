// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.util.ArraySet;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package android.security.net.config:
//            CertificateSource, TrustAnchor

public final class CertificatesEntryRef
{

    public CertificatesEntryRef(CertificateSource certificatesource, boolean flag)
    {
        mSource = certificatesource;
        mOverridesPins = flag;
    }

    public Set findAllCertificatesByIssuerAndSignature(X509Certificate x509certificate)
    {
        return mSource.findAllByIssuerAndSignature(x509certificate);
    }

    public TrustAnchor findByIssuerAndSignature(X509Certificate x509certificate)
    {
        x509certificate = mSource.findByIssuerAndSignature(x509certificate);
        if(x509certificate == null)
            return null;
        else
            return new TrustAnchor(x509certificate, mOverridesPins);
    }

    public TrustAnchor findBySubjectAndPublicKey(X509Certificate x509certificate)
    {
        x509certificate = mSource.findBySubjectAndPublicKey(x509certificate);
        if(x509certificate == null)
            return null;
        else
            return new TrustAnchor(x509certificate, mOverridesPins);
    }

    public Set getTrustAnchors()
    {
        ArraySet arrayset = new ArraySet();
        for(Iterator iterator = mSource.getCertificates().iterator(); iterator.hasNext(); arrayset.add(new TrustAnchor((X509Certificate)iterator.next(), mOverridesPins)));
        return arrayset;
    }

    public void handleTrustStorageUpdate()
    {
        mSource.handleTrustStorageUpdate();
    }

    boolean overridesPins()
    {
        return mOverridesPins;
    }

    private final boolean mOverridesPins;
    private final CertificateSource mSource;
}
