// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.util.ArrayMap;
import com.android.org.conscrypt.TrustManagerImpl;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

// Referenced classes of package android.security.net.config:
//            TrustedCertificateStoreAdapter, NetworkSecurityConfig, PinSet, Pin, 
//            TrustAnchor

public class NetworkSecurityTrustManager extends X509ExtendedTrustManager
{

    public NetworkSecurityTrustManager(NetworkSecurityConfig networksecurityconfig)
    {
        mIssuersLock = new Object();
        if(networksecurityconfig == null)
            throw new NullPointerException("config must not be null");
        mNetworkSecurityConfig = networksecurityconfig;
        try
        {
            TrustedCertificateStoreAdapter trustedcertificatestoreadapter = JVM INSTR new #37  <Class TrustedCertificateStoreAdapter>;
            trustedcertificatestoreadapter.TrustedCertificateStoreAdapter(networksecurityconfig);
            networksecurityconfig = KeyStore.getInstance(KeyStore.getDefaultType());
            networksecurityconfig.load(null);
            TrustManagerImpl trustmanagerimpl = JVM INSTR new #55  <Class TrustManagerImpl>;
            trustmanagerimpl.TrustManagerImpl(networksecurityconfig, null, trustedcertificatestoreadapter);
            mDelegate = trustmanagerimpl;
            return;
        }
        // Misplaced declaration of an exception variable
        catch(NetworkSecurityConfig networksecurityconfig)
        {
            throw new RuntimeException(networksecurityconfig);
        }
    }

    private void checkPins(List list)
        throws CertificateException
    {
        PinSet pinset;
        for(pinset = mNetworkSecurityConfig.getPins(); pinset.pins.isEmpty() || System.currentTimeMillis() > pinset.expirationTime || isPinningEnforced(list) ^ true;)
            return;

        Set set = pinset.getPinAlgorithms();
        ArrayMap arraymap = new ArrayMap(set.size());
        int i = list.size() - 1;
        do
        {
            if(i < 0)
                break;
            byte abyte0[] = ((X509Certificate)list.get(i)).getPublicKey().getEncoded();
            for(Iterator iterator = set.iterator(); iterator.hasNext();)
            {
                String s = (String)iterator.next();
                MessageDigest messagedigest = (MessageDigest)arraymap.get(s);
                MessageDigest messagedigest1 = messagedigest;
                if(messagedigest == null)
                {
                    try
                    {
                        messagedigest1 = MessageDigest.getInstance(s);
                    }
                    // Misplaced declaration of an exception variable
                    catch(List list)
                    {
                        throw new RuntimeException(list);
                    }
                    arraymap.put(s, messagedigest1);
                }
                if(pinset.pins.contains(new Pin(s, messagedigest1.digest(abyte0))))
                    return;
            }

            i--;
        } while(true);
        throw new CertificateException("Pin verification failed");
    }

    private boolean isPinningEnforced(List list)
        throws CertificateException
    {
        if(list.isEmpty())
            return false;
        list = (X509Certificate)list.get(list.size() - 1);
        list = mNetworkSecurityConfig.findTrustAnchorBySubjectAndPublicKey(list);
        if(list == null)
            throw new CertificateException("Trusted chain does not end in a TrustAnchor");
        else
            return ((TrustAnchor) (list)).overridesPins ^ true;
    }

    public void checkClientTrusted(X509Certificate ax509certificate[], String s)
        throws CertificateException
    {
        mDelegate.checkClientTrusted(ax509certificate, s);
    }

    public void checkClientTrusted(X509Certificate ax509certificate[], String s, Socket socket)
        throws CertificateException
    {
        mDelegate.checkClientTrusted(ax509certificate, s, socket);
    }

    public void checkClientTrusted(X509Certificate ax509certificate[], String s, SSLEngine sslengine)
        throws CertificateException
    {
        mDelegate.checkClientTrusted(ax509certificate, s, sslengine);
    }

    public List checkServerTrusted(X509Certificate ax509certificate[], String s, String s1)
        throws CertificateException
    {
        ax509certificate = mDelegate.checkServerTrusted(ax509certificate, s, s1);
        checkPins(ax509certificate);
        return ax509certificate;
    }

    public void checkServerTrusted(X509Certificate ax509certificate[], String s)
        throws CertificateException
    {
        checkServerTrusted(ax509certificate, s, (String)null);
    }

    public void checkServerTrusted(X509Certificate ax509certificate[], String s, Socket socket)
        throws CertificateException
    {
        checkPins(mDelegate.getTrustedChainForServer(ax509certificate, s, socket));
    }

    public void checkServerTrusted(X509Certificate ax509certificate[], String s, SSLEngine sslengine)
        throws CertificateException
    {
        checkPins(mDelegate.getTrustedChainForServer(ax509certificate, s, sslengine));
    }

    public X509Certificate[] getAcceptedIssuers()
    {
        Object obj = mIssuersLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        X509Certificate ax509certificate[];
        if(mIssuers != null)
            break MISSING_BLOCK_LABEL_78;
        obj1 = mNetworkSecurityConfig.getTrustAnchors();
        ax509certificate = new X509Certificate[((Set) (obj1)).size()];
        obj1 = ((Iterable) (obj1)).iterator();
        int i = 0;
_L2:
        if(!((Iterator) (obj1)).hasNext())
            break; /* Loop/switch isn't completed */
        ax509certificate[i] = ((TrustAnchor)((Iterator) (obj1)).next()).certificate;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mIssuers = ax509certificate;
        ax509certificate = (X509Certificate[])mIssuers.clone();
        obj;
        JVM INSTR monitorexit ;
        return ax509certificate;
        Exception exception;
        exception;
        throw exception;
    }

    public void handleTrustStorageUpdate()
    {
        Object obj = mIssuersLock;
        obj;
        JVM INSTR monitorenter ;
        mIssuers = null;
        mDelegate.handleTrustStorageUpdate();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final TrustManagerImpl mDelegate;
    private X509Certificate mIssuers[];
    private final Object mIssuersLock;
    private final NetworkSecurityConfig mNetworkSecurityConfig;
}
