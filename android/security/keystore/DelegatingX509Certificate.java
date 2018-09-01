// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

class DelegatingX509Certificate extends X509Certificate
{

    DelegatingX509Certificate(X509Certificate x509certificate)
    {
        mDelegate = x509certificate;
    }

    public void checkValidity()
        throws CertificateExpiredException, CertificateNotYetValidException
    {
        mDelegate.checkValidity();
    }

    public void checkValidity(Date date)
        throws CertificateExpiredException, CertificateNotYetValidException
    {
        mDelegate.checkValidity(date);
    }

    public int getBasicConstraints()
    {
        return mDelegate.getBasicConstraints();
    }

    public Set getCriticalExtensionOIDs()
    {
        return mDelegate.getCriticalExtensionOIDs();
    }

    public byte[] getEncoded()
        throws CertificateEncodingException
    {
        return mDelegate.getEncoded();
    }

    public List getExtendedKeyUsage()
        throws CertificateParsingException
    {
        return mDelegate.getExtendedKeyUsage();
    }

    public byte[] getExtensionValue(String s)
    {
        return mDelegate.getExtensionValue(s);
    }

    public Collection getIssuerAlternativeNames()
        throws CertificateParsingException
    {
        return mDelegate.getIssuerAlternativeNames();
    }

    public Principal getIssuerDN()
    {
        return mDelegate.getIssuerDN();
    }

    public boolean[] getIssuerUniqueID()
    {
        return mDelegate.getIssuerUniqueID();
    }

    public X500Principal getIssuerX500Principal()
    {
        return mDelegate.getIssuerX500Principal();
    }

    public boolean[] getKeyUsage()
    {
        return mDelegate.getKeyUsage();
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return mDelegate.getNonCriticalExtensionOIDs();
    }

    public Date getNotAfter()
    {
        return mDelegate.getNotAfter();
    }

    public Date getNotBefore()
    {
        return mDelegate.getNotBefore();
    }

    public PublicKey getPublicKey()
    {
        return mDelegate.getPublicKey();
    }

    public BigInteger getSerialNumber()
    {
        return mDelegate.getSerialNumber();
    }

    public String getSigAlgName()
    {
        return mDelegate.getSigAlgName();
    }

    public String getSigAlgOID()
    {
        return mDelegate.getSigAlgOID();
    }

    public byte[] getSigAlgParams()
    {
        return mDelegate.getSigAlgParams();
    }

    public byte[] getSignature()
    {
        return mDelegate.getSignature();
    }

    public Collection getSubjectAlternativeNames()
        throws CertificateParsingException
    {
        return mDelegate.getSubjectAlternativeNames();
    }

    public Principal getSubjectDN()
    {
        return mDelegate.getSubjectDN();
    }

    public boolean[] getSubjectUniqueID()
    {
        return mDelegate.getSubjectUniqueID();
    }

    public X500Principal getSubjectX500Principal()
    {
        return mDelegate.getSubjectX500Principal();
    }

    public byte[] getTBSCertificate()
        throws CertificateEncodingException
    {
        return mDelegate.getTBSCertificate();
    }

    public int getVersion()
    {
        return mDelegate.getVersion();
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        return mDelegate.hasUnsupportedCriticalExtension();
    }

    public String toString()
    {
        return mDelegate.toString();
    }

    public void verify(PublicKey publickey)
        throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
    {
        mDelegate.verify(publickey);
    }

    public void verify(PublicKey publickey, String s)
        throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
    {
        mDelegate.verify(publickey, s);
    }

    private final X509Certificate mDelegate;
}
