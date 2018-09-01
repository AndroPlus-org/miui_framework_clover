// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.util.ArraySet;
import android.util.Log;
import com.android.org.conscrypt.Hex;
import com.android.org.conscrypt.NativeCrypto;
import java.io.*;
import java.security.cert.*;
import java.util.Collections;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import libcore.io.IoUtils;

// Referenced classes of package android.security.net.config:
//            CertificateSource

abstract class DirectoryCertificateSource
    implements CertificateSource
{
    private static interface CertSelector
    {

        public abstract boolean match(X509Certificate x509certificate);
    }


    protected DirectoryCertificateSource(File file)
    {
        mDir = file;
        try
        {
            mCertFactory = CertificateFactory.getInstance("X.509");
            return;
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", file);
        }
    }

    private X509Certificate findCert(X500Principal x500principal, CertSelector certselector)
    {
        String s;
        int i;
        s = getHash(x500principal);
        i = 0;
_L2:
        Object obj;
label0:
        {
            if(i >= 0)
            {
                obj = (new StringBuilder()).append(s).append(".").append(i).toString();
                if((new File(mDir, ((String) (obj)))).exists())
                    break label0;
            }
            return null;
        }
        if(!isCertMarkedAsRemoved(((String) (obj))))
            break; /* Loop/switch isn't completed */
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        obj = readCertificate(((String) (obj)));
        if(obj != null && x500principal.equals(((X509Certificate) (obj)).getSubjectX500Principal()) && certselector.match(((X509Certificate) (obj))))
            return ((X509Certificate) (obj));
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private Set findCerts(X500Principal x500principal, CertSelector certselector)
    {
        String s;
        Object obj;
        int i;
        s = getHash(x500principal);
        obj = null;
        i = 0;
_L2:
label0:
        {
            Object obj1;
            if(i >= 0)
            {
                obj1 = (new StringBuilder()).append(s).append(".").append(i).toString();
                if((new File(mDir, ((String) (obj1)))).exists())
                    break label0;
            }
            X509Certificate x509certificate;
            if(obj == null)
                obj = Collections.emptySet();
            return ((Set) (obj));
        }
        if(!isCertMarkedAsRemoved(((String) (obj1))))
            break; /* Loop/switch isn't completed */
        obj1 = obj;
_L3:
        i++;
        obj = obj1;
        if(true) goto _L2; else goto _L1
_L1:
        x509certificate = readCertificate(((String) (obj1)));
        obj1 = obj;
        if(x509certificate != null)
        {
            obj1 = obj;
            if(x500principal.equals(x509certificate.getSubjectX500Principal()))
            {
                obj1 = obj;
                if(certselector.match(x509certificate))
                {
                    obj1 = obj;
                    if(obj == null)
                        obj1 = new ArraySet();
                    ((Set) (obj1)).add(x509certificate);
                }
            }
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private String getHash(X500Principal x500principal)
    {
        return Hex.intToHexString(NativeCrypto.X509_NAME_hash_old(x500principal), 8);
    }

    private X509Certificate readCertificate(String s)
    {
        Object obj;
        StringBuilder stringbuilder;
        Object obj2;
        obj = null;
        stringbuilder = null;
        obj2 = obj;
        BufferedInputStream bufferedinputstream = JVM INSTR new #146 <Class BufferedInputStream>;
        obj2 = obj;
        FileInputStream fileinputstream = JVM INSTR new #148 <Class FileInputStream>;
        obj2 = obj;
        File file = JVM INSTR new #81  <Class File>;
        obj2 = obj;
        file.File(mDir, s);
        obj2 = obj;
        fileinputstream.FileInputStream(file);
        obj2 = obj;
        bufferedinputstream.BufferedInputStream(fileinputstream);
        obj2 = (X509Certificate)mCertFactory.generateCertificate(bufferedinputstream);
        IoUtils.closeQuietly(bufferedinputstream);
        return ((X509Certificate) (obj2));
        Object obj1;
        obj1;
        bufferedinputstream = stringbuilder;
_L4:
        obj2 = bufferedinputstream;
        stringbuilder = JVM INSTR new #65  <Class StringBuilder>;
        obj2 = bufferedinputstream;
        stringbuilder.StringBuilder();
        obj2 = bufferedinputstream;
        Log.e("DirectoryCertificateSrc", stringbuilder.append("Failed to read certificate from ").append(s).toString(), ((Throwable) (obj1)));
        IoUtils.closeQuietly(bufferedinputstream);
        return null;
        s;
_L2:
        IoUtils.closeQuietly(((AutoCloseable) (obj2)));
        throw s;
        s;
        obj2 = bufferedinputstream;
        if(true) goto _L2; else goto _L1
_L1:
        obj1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Set findAllByIssuerAndSignature(final X509Certificate cert)
    {
        return findCerts(cert.getIssuerX500Principal(), new CertSelector() {

            public boolean match(X509Certificate x509certificate)
            {
                try
                {
                    cert.verify(x509certificate.getPublicKey());
                }
                // Misplaced declaration of an exception variable
                catch(X509Certificate x509certificate)
                {
                    return false;
                }
                return true;
            }

            final DirectoryCertificateSource this$0;
            final X509Certificate val$cert;

            
            {
                this$0 = DirectoryCertificateSource.this;
                cert = x509certificate;
                super();
            }
        }
);
    }

    public X509Certificate findByIssuerAndSignature(final X509Certificate cert)
    {
        return findCert(cert.getIssuerX500Principal(), new CertSelector() {

            public boolean match(X509Certificate x509certificate)
            {
                try
                {
                    cert.verify(x509certificate.getPublicKey());
                }
                // Misplaced declaration of an exception variable
                catch(X509Certificate x509certificate)
                {
                    return false;
                }
                return true;
            }

            final DirectoryCertificateSource this$0;
            final X509Certificate val$cert;

            
            {
                this$0 = DirectoryCertificateSource.this;
                cert = x509certificate;
                super();
            }
        }
);
    }

    public X509Certificate findBySubjectAndPublicKey(final X509Certificate cert)
    {
        return findCert(cert.getSubjectX500Principal(), new CertSelector() {

            public boolean match(X509Certificate x509certificate)
            {
                return x509certificate.getPublicKey().equals(cert.getPublicKey());
            }

            final DirectoryCertificateSource this$0;
            final X509Certificate val$cert;

            
            {
                this$0 = DirectoryCertificateSource.this;
                cert = x509certificate;
                super();
            }
        }
);
    }

    public Set getCertificates()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        if(mCertificates == null)
            break MISSING_BLOCK_LABEL_23;
        obj1 = mCertificates;
        obj;
        JVM INSTR monitorexit ;
        return ((Set) (obj1));
        String as[];
        obj1 = JVM INSTR new #116 <Class ArraySet>;
        ((ArraySet) (obj1)).ArraySet();
        if(!mDir.isDirectory())
            break MISSING_BLOCK_LABEL_114;
        as = mDir.list();
        int i = 0;
        int j = as.length;
_L2:
        Object obj2;
        if(i >= j)
            break MISSING_BLOCK_LABEL_114;
        obj2 = as[i];
        if(!isCertMarkedAsRemoved(((String) (obj2))))
            break; /* Loop/switch isn't completed */
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        obj2 = readCertificate(((String) (obj2)));
        if(obj2 == null) goto _L4; else goto _L3
_L3:
        ((Set) (obj1)).add(obj2);
          goto _L4
        obj1;
        throw obj1;
        mCertificates = ((Set) (obj1));
        obj1 = mCertificates;
        obj;
        JVM INSTR monitorexit ;
        return ((Set) (obj1));
    }

    public void handleTrustStorageUpdate()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mCertificates = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected abstract boolean isCertMarkedAsRemoved(String s);

    private static final String LOG_TAG = "DirectoryCertificateSrc";
    private final CertificateFactory mCertFactory;
    private Set mCertificates;
    private final File mDir;
    private final Object mLock = new Object();
}
