// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.content.Context;
import android.content.res.Resources;
import android.util.ArraySet;
import com.android.org.conscrypt.TrustedCertificateIndex;
import java.security.cert.*;
import java.util.*;
import libcore.io.IoUtils;

// Referenced classes of package android.security.net.config:
//            CertificateSource

public class ResourceCertificateSource
    implements CertificateSource
{

    public ResourceCertificateSource(int i, Context context)
    {
        mResourceId = i;
        mContext = context;
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
        obj2 = JVM INSTR new #36  <Class ArraySet>;
        ((ArraySet) (obj2)).ArraySet();
        Object obj3;
        java.io.InputStream inputstream;
        Object obj4;
        obj3 = null;
        inputstream = null;
        obj4 = inputstream;
        obj1 = obj3;
        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        obj4 = inputstream;
        obj1 = obj3;
        inputstream = mContext.getResources().openRawResource(mResourceId);
        obj4 = inputstream;
        obj1 = inputstream;
        obj3 = certificatefactory.generateCertificates(inputstream);
        IoUtils.closeQuietly(inputstream);
        obj1 = JVM INSTR new #69  <Class TrustedCertificateIndex>;
        ((TrustedCertificateIndex) (obj1)).TrustedCertificateIndex();
        for(Iterator iterator = ((Iterable) (obj3)).iterator(); iterator.hasNext(); ((TrustedCertificateIndex) (obj1)).index((X509Certificate)obj4))
        {
            obj4 = (Certificate)iterator.next();
            ((Set) (obj2)).add((X509Certificate)obj4);
        }

        break MISSING_BLOCK_LABEL_225;
        obj1;
        throw obj1;
        CertificateException certificateexception;
        certificateexception;
        obj1 = obj4;
        obj3 = JVM INSTR new #102 <Class RuntimeException>;
        obj1 = obj4;
        obj2 = JVM INSTR new #104 <Class StringBuilder>;
        obj1 = obj4;
        ((StringBuilder) (obj2)).StringBuilder();
        obj1 = obj4;
        ((RuntimeException) (obj3)).RuntimeException(((StringBuilder) (obj2)).append("Failed to load trust anchors from id ").append(mResourceId).toString(), certificateexception);
        obj1 = obj4;
        throw obj3;
        Exception exception;
        exception;
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        throw exception;
        mCertificates = ((Set) (obj2));
        mIndex = ((TrustedCertificateIndex) (obj1));
        mContext = null;
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
    private Context mContext;
    private TrustedCertificateIndex mIndex;
    private final Object mLock = new Object();
    private final int mResourceId;
}
