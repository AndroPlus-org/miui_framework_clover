// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.http;

import android.security.net.config.UserCertificateSource;
import com.android.org.conscrypt.TrustManagerImpl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;

public class X509TrustManagerExtensions
{

    public X509TrustManagerExtensions(X509TrustManager x509trustmanager)
        throws IllegalArgumentException
    {
        if(x509trustmanager instanceof TrustManagerImpl)
        {
            mDelegate = (TrustManagerImpl)x509trustmanager;
            mTrustManager = null;
            mCheckServerTrusted = null;
            mIsSameTrustConfiguration = null;
            return;
        }
        mTrustManager = x509trustmanager;
        Object obj;
        try
        {
            mCheckServerTrusted = x509trustmanager.getClass().getMethod("checkServerTrusted", new Class[] {
                [Ljava/security/cert/X509Certificate;, java/lang/String, java/lang/String
            });
        }
        // Misplaced declaration of an exception variable
        catch(X509TrustManager x509trustmanager)
        {
            throw new IllegalArgumentException("Required method checkServerTrusted(X509Certificate[], String, String, String) missing");
        }
        obj = null;
        try
        {
            x509trustmanager = x509trustmanager.getClass().getMethod("isSameTrustConfiguration", new Class[] {
                java/lang/String, java/lang/String
            });
        }
        // Misplaced declaration of an exception variable
        catch(X509TrustManager x509trustmanager)
        {
            x509trustmanager = obj;
        }
        mIsSameTrustConfiguration = x509trustmanager;
    }

    public List checkServerTrusted(X509Certificate ax509certificate[], String s, String s1)
        throws CertificateException
    {
        if(mDelegate != null)
            return mDelegate.checkServerTrusted(ax509certificate, s, s1);
        try
        {
            ax509certificate = (List)mCheckServerTrusted.invoke(mTrustManager, new Object[] {
                ax509certificate, s, s1
            });
        }
        // Misplaced declaration of an exception variable
        catch(X509Certificate ax509certificate[])
        {
            throw new CertificateException("Failed to call checkServerTrusted", ax509certificate);
        }
        // Misplaced declaration of an exception variable
        catch(X509Certificate ax509certificate[])
        {
            if(ax509certificate.getCause() instanceof CertificateException)
                throw (CertificateException)ax509certificate.getCause();
            if(ax509certificate.getCause() instanceof RuntimeException)
                throw (RuntimeException)ax509certificate.getCause();
            else
                throw new CertificateException("checkServerTrusted failed", ax509certificate.getCause());
        }
        return ax509certificate;
    }

    public boolean isSameTrustConfiguration(String s, String s1)
    {
        if(mIsSameTrustConfiguration == null)
            return true;
        boolean flag;
        try
        {
            flag = ((Boolean)mIsSameTrustConfiguration.invoke(mTrustManager, new Object[] {
                s, s1
            })).booleanValue();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("Failed to call isSameTrustConfiguration", s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            if(s.getCause() instanceof RuntimeException)
                throw (RuntimeException)s.getCause();
            else
                throw new RuntimeException("isSameTrustConfiguration failed", s.getCause());
        }
        return flag;
    }

    public boolean isUserAddedCertificate(X509Certificate x509certificate)
    {
        boolean flag;
        if(UserCertificateSource.getInstance().findBySubjectAndPublicKey(x509certificate) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private final Method mCheckServerTrusted;
    private final TrustManagerImpl mDelegate = null;
    private final Method mIsSameTrustConfiguration;
    private final X509TrustManager mTrustManager;
}
