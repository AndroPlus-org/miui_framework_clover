// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

// Referenced classes of package org.apache.http.conn.ssl:
//            AllowAllHostnameVerifier, BrowserCompatHostnameVerifier, StrictHostnameVerifier, X509HostnameVerifier

public class SSLSocketFactory
    implements LayeredSocketFactory
{
    private static class NoPreloadHolder
    {

        static SSLSocketFactory _2D_get0()
        {
            return DEFAULT_FACTORY;
        }

        private static final SSLSocketFactory DEFAULT_FACTORY = new SSLSocketFactory(null);


        private NoPreloadHolder()
        {
        }
    }


    private SSLSocketFactory()
    {
        hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        sslcontext = null;
        socketfactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        nameResolver = null;
    }

    public SSLSocketFactory(String s, KeyStore keystore, String s1, KeyStore keystore1, SecureRandom securerandom, HostNameResolver hostnameresolver)
        throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        String s2 = s;
        if(s == null)
            s2 = "TLS";
        s = null;
        if(keystore != null)
            s = createKeyManagers(keystore, s1);
        keystore = null;
        if(keystore1 != null)
            keystore = createTrustManagers(keystore1);
        sslcontext = SSLContext.getInstance(s2);
        sslcontext.init(s, keystore, securerandom);
        socketfactory = sslcontext.getSocketFactory();
        nameResolver = hostnameresolver;
    }

    public SSLSocketFactory(KeyStore keystore)
        throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        this("TLS", null, null, keystore, null, null);
    }

    public SSLSocketFactory(KeyStore keystore, String s)
        throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        this("TLS", keystore, s, null, null, null);
    }

    public SSLSocketFactory(KeyStore keystore, String s, KeyStore keystore1)
        throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        this("TLS", keystore, s, keystore1, null, null);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory sslsocketfactory)
    {
        hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        sslcontext = null;
        socketfactory = sslsocketfactory;
        nameResolver = null;
    }

    SSLSocketFactory(SSLSocketFactory sslsocketfactory)
    {
        this();
    }

    private static KeyManager[] createKeyManagers(KeyStore keystore, String s)
        throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException
    {
        char ac[] = null;
        if(keystore == null)
            throw new IllegalArgumentException("Keystore may not be null");
        KeyManagerFactory keymanagerfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        if(s != null)
            ac = s.toCharArray();
        keymanagerfactory.init(keystore, ac);
        return keymanagerfactory.getKeyManagers();
    }

    private static TrustManager[] createTrustManagers(KeyStore keystore)
        throws KeyStoreException, NoSuchAlgorithmException
    {
        if(keystore == null)
        {
            throw new IllegalArgumentException("Keystore may not be null");
        } else
        {
            TrustManagerFactory trustmanagerfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustmanagerfactory.init(keystore);
            return trustmanagerfactory.getTrustManagers();
        }
    }

    public static SSLSocketFactory getSocketFactory()
    {
        return NoPreloadHolder._2D_get0();
    }

    public Socket connectSocket(Socket socket, String s, int i, InetAddress inetaddress, int j, HttpParams httpparams)
        throws IOException
    {
        if(s == null)
            throw new IllegalArgumentException("Target host may not be null.");
        if(httpparams == null)
            throw new IllegalArgumentException("Parameters may not be null.");
        SSLSocket sslsocket;
        int l;
        if(socket == null)
            socket = createSocket();
        sslsocket = (SSLSocket)socket;
        if(inetaddress != null || j > 0)
        {
            int k = j;
            if(j < 0)
                k = 0;
            sslsocket.bind(new InetSocketAddress(inetaddress, k));
        }
        j = HttpConnectionParams.getConnectionTimeout(httpparams);
        l = HttpConnectionParams.getSoTimeout(httpparams);
        if(nameResolver != null)
            socket = new InetSocketAddress(nameResolver.resolve(s), i);
        else
            socket = new InetSocketAddress(s, i);
        sslsocket.connect(socket, j);
        sslsocket.setSoTimeout(l);
        try
        {
            sslsocket.startHandshake();
            hostnameVerifier.verify(s, sslsocket);
        }
        // Misplaced declaration of an exception variable
        catch(Socket socket)
        {
            try
            {
                sslsocket.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
            throw socket;
        }
        return sslsocket;
    }

    public Socket createSocket()
        throws IOException
    {
        return (SSLSocket)socketfactory.createSocket();
    }

    public Socket createSocket(Socket socket, String s, int i, boolean flag)
        throws IOException, UnknownHostException
    {
        socket = (SSLSocket)socketfactory.createSocket(socket, s, i, flag);
        socket.startHandshake();
        hostnameVerifier.verify(s, socket);
        return socket;
    }

    public X509HostnameVerifier getHostnameVerifier()
    {
        return hostnameVerifier;
    }

    public boolean isSecure(Socket socket)
        throws IllegalArgumentException
    {
        if(socket == null)
            throw new IllegalArgumentException("Socket may not be null.");
        if(!(socket instanceof SSLSocket))
            throw new IllegalArgumentException("Socket not created by this factory.");
        if(socket.isClosed())
            throw new IllegalArgumentException("Socket is closed.");
        else
            return true;
    }

    public void setHostnameVerifier(X509HostnameVerifier x509hostnameverifier)
    {
        if(x509hostnameverifier == null)
        {
            throw new IllegalArgumentException("Hostname verifier may not be null");
        } else
        {
            hostnameVerifier = x509hostnameverifier;
            return;
        }
    }

    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    public static final String TLS = "TLS";
    private X509HostnameVerifier hostnameVerifier;
    private final HostNameResolver nameResolver;
    private final javax.net.ssl.SSLSocketFactory socketfactory;
    private final SSLContext sslcontext;

}
