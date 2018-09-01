// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.security.cert.X509Certificate;

public final class TrustAnchor
{

    public TrustAnchor(X509Certificate x509certificate, boolean flag)
    {
        if(x509certificate == null)
        {
            throw new NullPointerException("certificate");
        } else
        {
            certificate = x509certificate;
            overridesPins = flag;
            return;
        }
    }

    public final X509Certificate certificate;
    public final boolean overridesPins;
}
