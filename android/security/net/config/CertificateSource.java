// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.security.cert.X509Certificate;
import java.util.Set;

public interface CertificateSource
{

    public abstract Set findAllByIssuerAndSignature(X509Certificate x509certificate);

    public abstract X509Certificate findByIssuerAndSignature(X509Certificate x509certificate);

    public abstract X509Certificate findBySubjectAndPublicKey(X509Certificate x509certificate);

    public abstract Set getCertificates();

    public abstract void handleTrustStorageUpdate();
}
