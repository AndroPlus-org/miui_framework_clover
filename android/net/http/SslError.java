// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.http;

import java.security.cert.X509Certificate;

// Referenced classes of package android.net.http:
//            SslCertificate

public class SslError
{

    public SslError(int i, SslCertificate sslcertificate)
    {
        this(i, sslcertificate, "");
    }

    public SslError(int i, SslCertificate sslcertificate, String s)
    {
        if(!_2D_assertionsDisabled && sslcertificate == null)
            throw new AssertionError();
        if(!_2D_assertionsDisabled && s == null)
        {
            throw new AssertionError();
        } else
        {
            addError(i);
            mCertificate = sslcertificate;
            mUrl = s;
            return;
        }
    }

    public SslError(int i, X509Certificate x509certificate)
    {
        this(i, x509certificate, "");
    }

    public SslError(int i, X509Certificate x509certificate, String s)
    {
        this(i, new SslCertificate(x509certificate), s);
    }

    public static SslError SslErrorFromChromiumErrorCode(int i, SslCertificate sslcertificate, String s)
    {
        if(!_2D_assertionsDisabled && (i < -299 || i > -200))
            throw new AssertionError();
        if(i == -200)
            return new SslError(2, sslcertificate, s);
        if(i == -201)
            return new SslError(4, sslcertificate, s);
        if(i == -202)
            return new SslError(3, sslcertificate, s);
        else
            return new SslError(5, sslcertificate, s);
    }

    public boolean addError(int i)
    {
        boolean flag;
        if(i >= 0 && i < 6)
            flag = true;
        else
            flag = false;
        if(flag)
            mErrors = mErrors | 1 << i;
        return flag;
    }

    public SslCertificate getCertificate()
    {
        return mCertificate;
    }

    public int getPrimaryError()
    {
        if(mErrors != 0)
        {
            for(int i = 5; i >= 0; i--)
                if((mErrors & 1 << i) != 0)
                    return i;

            if(!_2D_assertionsDisabled)
                throw new AssertionError();
        }
        return -1;
    }

    public String getUrl()
    {
        return mUrl;
    }

    public boolean hasError(int i)
    {
        boolean flag;
        boolean flag1;
        if(i >= 0 && i < 6)
            flag = true;
        else
            flag = false;
        flag1 = flag;
        if(flag)
            if((mErrors & 1 << i) != 0)
                flag1 = true;
            else
                flag1 = false;
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("primary error: ").append(getPrimaryError()).append(" certificate: ").append(getCertificate()).append(" on URL: ").append(getUrl()).toString();
    }

    static final boolean _2D_assertionsDisabled = android/net/http/SslError.desiredAssertionStatus() ^ true;
    public static final int SSL_DATE_INVALID = 4;
    public static final int SSL_EXPIRED = 1;
    public static final int SSL_IDMISMATCH = 2;
    public static final int SSL_INVALID = 5;
    public static final int SSL_MAX_ERROR = 6;
    public static final int SSL_NOTYETVALID = 0;
    public static final int SSL_UNTRUSTED = 3;
    final SslCertificate mCertificate;
    int mErrors;
    final String mUrl;

}
