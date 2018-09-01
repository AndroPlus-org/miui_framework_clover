// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.SystemProperties;
import android.util.Log;
import com.android.internal.os.RoSystemProperties;
import com.android.org.conscrypt.ClientSessionContext;
import com.android.org.conscrypt.Conscrypt;
import com.android.org.conscrypt.OpenSSLContextImpl;
import com.android.org.conscrypt.OpenSSLSocketImpl;
import com.android.org.conscrypt.SSLClientSessionCache;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

// Referenced classes of package android.net:
//            SSLSessionCache

public class SSLCertificateSocketFactory extends SSLSocketFactory
{

    public SSLCertificateSocketFactory(int i)
    {
        this(i, null, true);
    }

    private SSLCertificateSocketFactory(int i, SSLSessionCache sslsessioncache, boolean flag)
    {
        Object obj = null;
        super();
        mInsecureFactory = null;
        mSecureFactory = null;
        mTrustManagers = null;
        mKeyManagers = null;
        mNpnProtocols = null;
        mAlpnProtocols = null;
        mChannelIdPrivateKey = null;
        mHandshakeTimeoutMillis = i;
        if(sslsessioncache == null)
            sslsessioncache = obj;
        else
            sslsessioncache = sslsessioncache.mSessionCache;
        mSessionCache = sslsessioncache;
        mSecure = flag;
    }

    private static OpenSSLSocketImpl castToOpenSSLSocket(Socket socket)
    {
        if(!(socket instanceof OpenSSLSocketImpl))
            throw new IllegalArgumentException((new StringBuilder()).append("Socket not created by this factory: ").append(socket).toString());
        else
            return (OpenSSLSocketImpl)socket;
    }

    public static SocketFactory getDefault(int i)
    {
        return new SSLCertificateSocketFactory(i, null, true);
    }

    public static SSLSocketFactory getDefault(int i, SSLSessionCache sslsessioncache)
    {
        return new SSLCertificateSocketFactory(i, sslsessioncache, true);
    }

    private SSLSocketFactory getDelegate()
    {
        this;
        JVM INSTR monitorenter ;
        if(mSecure && !isSslCheckRelaxed())
            break MISSING_BLOCK_LABEL_77;
        if(mInsecureFactory != null) goto _L2; else goto _L1
_L1:
        if(!mSecure)
            break MISSING_BLOCK_LABEL_61;
        Log.w("SSLCertificateSocketFactory", "*** BYPASSING SSL SECURITY CHECKS (socket.relaxsslcheck=yes) ***");
_L3:
        mInsecureFactory = makeSocketFactory(mKeyManagers, INSECURE_TRUST_MANAGER);
_L2:
        SSLSocketFactory sslsocketfactory = mInsecureFactory;
        this;
        JVM INSTR monitorexit ;
        return sslsocketfactory;
        Log.w("SSLCertificateSocketFactory", "Bypassing SSL security checks at caller's request");
          goto _L3
        Exception exception;
        exception;
        throw exception;
        if(mSecureFactory == null)
            mSecureFactory = makeSocketFactory(mKeyManagers, mTrustManagers);
        exception = mSecureFactory;
        this;
        JVM INSTR monitorexit ;
        return exception;
    }

    public static org.apache.http.conn.ssl.SSLSocketFactory getHttpSocketFactory(int i, SSLSessionCache sslsessioncache)
    {
        return new org.apache.http.conn.ssl.SSLSocketFactory(new SSLCertificateSocketFactory(i, sslsessioncache, true));
    }

    public static SSLSocketFactory getInsecure(int i, SSLSessionCache sslsessioncache)
    {
        return new SSLCertificateSocketFactory(i, sslsessioncache, false);
    }

    private static boolean isSslCheckRelaxed()
    {
        boolean flag = false;
        if(RoSystemProperties.DEBUGGABLE)
            flag = SystemProperties.getBoolean("socket.relaxsslcheck", false);
        return flag;
    }

    private SSLSocketFactory makeSocketFactory(KeyManager akeymanager[], TrustManager atrustmanager[])
    {
        try
        {
            OpenSSLContextImpl opensslcontextimpl = (OpenSSLContextImpl)Conscrypt.newPreferredSSLContextSpi();
            opensslcontextimpl.engineInit(akeymanager, atrustmanager, null);
            opensslcontextimpl.engineGetClientSessionContext().setPersistentCache(mSessionCache);
            akeymanager = opensslcontextimpl.engineGetSocketFactory();
        }
        // Misplaced declaration of an exception variable
        catch(KeyManager akeymanager[])
        {
            Log.wtf("SSLCertificateSocketFactory", akeymanager);
            return (SSLSocketFactory)SSLSocketFactory.getDefault();
        }
        return akeymanager;
    }

    static transient byte[] toLengthPrefixedList(byte abyte0[][])
    {
        if(abyte0.length == 0)
            throw new IllegalArgumentException("items.length == 0");
        int i = 0;
        int j = abyte0.length;
        for(int l = 0; l < j; l++)
        {
            byte abyte1[] = abyte0[l];
            if(abyte1.length == 0 || abyte1.length > 255)
                throw new IllegalArgumentException((new StringBuilder()).append("s.length == 0 || s.length > 255: ").append(abyte1.length).toString());
            i += abyte1.length + 1;
        }

        byte abyte3[] = new byte[i];
        int j1 = abyte0.length;
        i = 0;
        int i1 = 0;
        for(; i < j1; i++)
        {
            byte abyte2[] = abyte0[i];
            abyte3[i1] = (byte)abyte2.length;
            int k1 = abyte2.length;
            int k = 0;
            for(i1++; k < k1; i1++)
            {
                abyte3[i1] = abyte2[k];
                k++;
            }

        }

        return abyte3;
    }

    public static void verifyHostname(Socket socket, String s)
        throws IOException
    {
        if(!(socket instanceof SSLSocket))
            throw new IllegalArgumentException("Attempt to verify non-SSL socket");
        if(!isSslCheckRelaxed())
        {
            socket = (SSLSocket)socket;
            socket.startHandshake();
            socket = socket.getSession();
            if(socket == null)
                throw new SSLException("Cannot verify SSL socket without session");
            if(!HttpsURLConnection.getDefaultHostnameVerifier().verify(s, socket))
                throw new SSLPeerUnverifiedException((new StringBuilder()).append("Cannot verify hostname: ").append(s).toString());
        }
    }

    public Socket createSocket()
        throws IOException
    {
        OpenSSLSocketImpl opensslsocketimpl = (OpenSSLSocketImpl)getDelegate().createSocket();
        opensslsocketimpl.setNpnProtocols(mNpnProtocols);
        opensslsocketimpl.setAlpnProtocols(mAlpnProtocols);
        opensslsocketimpl.setHandshakeTimeout(mHandshakeTimeoutMillis);
        opensslsocketimpl.setChannelIdPrivateKey(mChannelIdPrivateKey);
        return opensslsocketimpl;
    }

    public Socket createSocket(String s, int i)
        throws IOException
    {
        OpenSSLSocketImpl opensslsocketimpl = (OpenSSLSocketImpl)getDelegate().createSocket(s, i);
        opensslsocketimpl.setNpnProtocols(mNpnProtocols);
        opensslsocketimpl.setAlpnProtocols(mAlpnProtocols);
        opensslsocketimpl.setHandshakeTimeout(mHandshakeTimeoutMillis);
        opensslsocketimpl.setChannelIdPrivateKey(mChannelIdPrivateKey);
        if(mSecure)
            verifyHostname(opensslsocketimpl, s);
        return opensslsocketimpl;
    }

    public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException
    {
        inetaddress = (OpenSSLSocketImpl)getDelegate().createSocket(s, i, inetaddress, j);
        inetaddress.setNpnProtocols(mNpnProtocols);
        inetaddress.setAlpnProtocols(mAlpnProtocols);
        inetaddress.setHandshakeTimeout(mHandshakeTimeoutMillis);
        inetaddress.setChannelIdPrivateKey(mChannelIdPrivateKey);
        if(mSecure)
            verifyHostname(inetaddress, s);
        return inetaddress;
    }

    public Socket createSocket(InetAddress inetaddress, int i)
        throws IOException
    {
        inetaddress = (OpenSSLSocketImpl)getDelegate().createSocket(inetaddress, i);
        inetaddress.setNpnProtocols(mNpnProtocols);
        inetaddress.setAlpnProtocols(mAlpnProtocols);
        inetaddress.setHandshakeTimeout(mHandshakeTimeoutMillis);
        inetaddress.setChannelIdPrivateKey(mChannelIdPrivateKey);
        return inetaddress;
    }

    public Socket createSocket(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
        throws IOException
    {
        inetaddress = (OpenSSLSocketImpl)getDelegate().createSocket(inetaddress, i, inetaddress1, j);
        inetaddress.setNpnProtocols(mNpnProtocols);
        inetaddress.setAlpnProtocols(mAlpnProtocols);
        inetaddress.setHandshakeTimeout(mHandshakeTimeoutMillis);
        inetaddress.setChannelIdPrivateKey(mChannelIdPrivateKey);
        return inetaddress;
    }

    public Socket createSocket(Socket socket, String s, int i, boolean flag)
        throws IOException
    {
        socket = (OpenSSLSocketImpl)getDelegate().createSocket(socket, s, i, flag);
        socket.setNpnProtocols(mNpnProtocols);
        socket.setAlpnProtocols(mAlpnProtocols);
        socket.setHandshakeTimeout(mHandshakeTimeoutMillis);
        socket.setChannelIdPrivateKey(mChannelIdPrivateKey);
        if(mSecure)
            verifyHostname(socket, s);
        return socket;
    }

    public byte[] getAlpnSelectedProtocol(Socket socket)
    {
        return castToOpenSSLSocket(socket).getAlpnSelectedProtocol();
    }

    public String[] getDefaultCipherSuites()
    {
        return getDelegate().getDefaultCipherSuites();
    }

    public byte[] getNpnSelectedProtocol(Socket socket)
    {
        return castToOpenSSLSocket(socket).getNpnSelectedProtocol();
    }

    public String[] getSupportedCipherSuites()
    {
        return getDelegate().getSupportedCipherSuites();
    }

    public void setAlpnProtocols(byte abyte0[][])
    {
        mAlpnProtocols = toLengthPrefixedList(abyte0);
    }

    public void setChannelIdPrivateKey(PrivateKey privatekey)
    {
        mChannelIdPrivateKey = privatekey;
    }

    public void setHostname(Socket socket, String s)
    {
        castToOpenSSLSocket(socket).setHostname(s);
    }

    public void setKeyManagers(KeyManager akeymanager[])
    {
        mKeyManagers = akeymanager;
        mSecureFactory = null;
        mInsecureFactory = null;
    }

    public void setNpnProtocols(byte abyte0[][])
    {
        mNpnProtocols = toLengthPrefixedList(abyte0);
    }

    public void setSoWriteTimeout(Socket socket, int i)
        throws SocketException
    {
        castToOpenSSLSocket(socket).setSoWriteTimeout(i);
    }

    public void setTrustManagers(TrustManager atrustmanager[])
    {
        mTrustManagers = atrustmanager;
        mSecureFactory = null;
    }

    public void setUseSessionTickets(Socket socket, boolean flag)
    {
        castToOpenSSLSocket(socket).setUseSessionTickets(flag);
    }

    private static final TrustManager INSECURE_TRUST_MANAGER[] = {
        new X509TrustManager() {

            public void checkClientTrusted(X509Certificate ax509certificate[], String s)
            {
            }

            public void checkServerTrusted(X509Certificate ax509certificate[], String s)
            {
            }

            public X509Certificate[] getAcceptedIssuers()
            {
                return null;
            }

        }

    };
    private static final String TAG = "SSLCertificateSocketFactory";
    private byte mAlpnProtocols[];
    private PrivateKey mChannelIdPrivateKey;
    private final int mHandshakeTimeoutMillis;
    private SSLSocketFactory mInsecureFactory;
    private KeyManager mKeyManagers[];
    private byte mNpnProtocols[];
    private final boolean mSecure;
    private SSLSocketFactory mSecureFactory;
    private final SSLClientSessionCache mSessionCache;
    private TrustManager mTrustManagers[];

}
