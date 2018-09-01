// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.util.ArraySet;
import com.android.org.conscrypt.TrustedCertificateIndex;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package android.security.net.config:
//            CertificateSource

class KeyStoreCertificateSource
    implements CertificateSource
{

    public KeyStoreCertificateSource(KeyStore keystore)
    {
        mKeyStore = keystore;
    }

    private void ensureInitialized()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = mCertificates;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj2;
        Enumeration enumeration;
        obj2 = JVM INSTR new #32  <Class TrustedCertificateIndex>;
        ((TrustedCertificateIndex) (obj2)).TrustedCertificateIndex();
        obj1 = JVM INSTR new #35  <Class ArraySet>;
        ((ArraySet) (obj1)).ArraySet(mKeyStore.size());
        enumeration = mKeyStore.aliases();
_L2:
        Object obj3;
        do
        {
            if(!enumeration.hasMoreElements())
                break MISSING_BLOCK_LABEL_130;
            obj3 = (String)enumeration.nextElement();
            obj3 = (X509Certificate)mKeyStore.getCertificate(((String) (obj3)));
        } while(obj3 == null);
        ((Set) (obj1)).add(obj3);
        ((TrustedCertificateIndex) (obj2)).index(((X509Certificate) (obj3)));
        if(true) goto _L2; else goto _L1
_L1:
        obj2;
        obj1 = JVM INSTR new #78  <Class RuntimeException>;
        ((RuntimeException) (obj1)).RuntimeException("Failed to load certificates from KeyStore", ((Throwable) (obj2)));
        throw obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        mIndex = ((TrustedCertificateIndex) (obj2));
        mCertificates = ((Set) (obj1));
        obj;
        JVM INSTR monitorexit ;
    }

    public Set findAllByIssuerAndSignature(X509Certificate x509certificate)
    {
        ensureInitialized();
        Object obj = mIndex.findAllByIssuerAndSignature(x509certificate);
        if(((Set) (obj)).isEmpty())
            return Collections.emptySet();
        x509certificate = new ArraySet(((Set) (obj)).size());
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); x509certificate.add(((TrustAnchor)((Iterator) (obj)).next()).getTrustedCert()));
        return x509certificate;
    }

    public X509Certificate findByIssuerAndSignature(X509Certificate x509certificate)
    {
        ensureInitialized();
        x509certificate = mIndex.findByIssuerAndSignature(x509certificate);
        if(x509certificate == null)
            return null;
        else
            return x509certificate.getTrustedCert();
    }

    public X509Certificate findBySubjectAndPublicKey(X509Certificate x509certificate)
    {
        ensureInitialized();
        x509certificate = mIndex.findBySubjectAndPublicKey(x509certificate);
        if(x509certificate == null)
            return null;
        else
            return x509certificate.getTrustedCert();
    }

    public Set getCertificates()
    {
        ensureInitialized();
        return mCertificates;
    }

    public void handleTrustStorageUpdate()
    {
    }

    private Set mCertificates;
    private TrustedCertificateIndex mIndex;
    private final KeyStore mKeyStore;
    private final Object mLock = new Object();
}
