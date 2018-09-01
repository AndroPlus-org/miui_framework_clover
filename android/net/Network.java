// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.system.*;
import com.android.okhttp.*;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;

// Referenced classes of package android.net:
//            NetworkUtils, ConnectivityManager, ProxyInfo

public class Network
    implements Parcelable
{
    private class NetworkBoundSocketFactory extends SocketFactory
    {

        private Socket connectToHost(String s, int i, SocketAddress socketaddress)
            throws IOException
        {
            InetAddress ainetaddress[];
            int j;
            ainetaddress = getAllByName(s);
            j = 0;
_L2:
            if(j >= ainetaddress.length)
                break; /* Loop/switch isn't completed */
            Socket socket = createSocket();
            if(socketaddress == null)
                break MISSING_BLOCK_LABEL_37;
            socket.bind(socketaddress);
            InetSocketAddress inetsocketaddress = JVM INSTR new #41  <Class InetSocketAddress>;
            inetsocketaddress.InetSocketAddress(ainetaddress[j], i);
            socket.connect(inetsocketaddress);
            return socket;
            IOException ioexception;
            ioexception;
            if(j == ainetaddress.length - 1)
                throw ioexception;
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            throw new UnknownHostException(s);
        }

        public Socket createSocket()
            throws IOException
        {
            Socket socket = new Socket();
            bindSocket(socket);
            return socket;
        }

        public Socket createSocket(String s, int i)
            throws IOException
        {
            return connectToHost(s, i, null);
        }

        public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
            throws IOException
        {
            return connectToHost(s, i, new InetSocketAddress(inetaddress, j));
        }

        public Socket createSocket(InetAddress inetaddress, int i)
            throws IOException
        {
            Socket socket = createSocket();
            socket.connect(new InetSocketAddress(inetaddress, i));
            return socket;
        }

        public Socket createSocket(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
            throws IOException
        {
            Socket socket = createSocket();
            socket.bind(new InetSocketAddress(inetaddress1, j));
            socket.connect(new InetSocketAddress(inetaddress, i));
            return socket;
        }

        private final int mNetId;
        final Network this$0;

        public NetworkBoundSocketFactory(int i)
        {
            this$0 = Network.this;
            super();
            mNetId = i;
        }
    }


    public Network(int i)
    {
        mNetworkBoundSocketFactory = null;
        mConnectionPool = null;
        mDns = null;
        mLock = new Object();
        netId = i;
    }

    public Network(Network network)
    {
        mNetworkBoundSocketFactory = null;
        mConnectionPool = null;
        mDns = null;
        mLock = new Object();
        netId = network.netId;
    }

    private void maybeInitHttpClient()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mDns == null)
        {
            Dns dns = JVM INSTR new #10  <Class Network$2>;
            dns.this. _cls2();
            mDns = dns;
        }
        if(mConnectionPool == null)
        {
            ConnectionPool connectionpool = JVM INSTR new #99  <Class ConnectionPool>;
            connectionpool.ConnectionPool(httpMaxConnections, httpKeepAliveDurationMs, TimeUnit.MILLISECONDS);
            mConnectionPool = connectionpool;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void bindSocket(FileDescriptor filedescriptor)
        throws IOException
    {
        try
        {
            if(!((InetSocketAddress)Os.getpeername(filedescriptor)).getAddress().isAnyLocalAddress())
            {
                SocketException socketexception = JVM INSTR new #136 <Class SocketException>;
                socketexception.SocketException("Socket is connected");
                throw socketexception;
            }
        }
        catch(ErrnoException errnoexception)
        {
            if(errnoexception.errno != OsConstants.ENOTCONN)
                throw errnoexception.rethrowAsSocketException();
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            throw new SocketException("Only AF_INET/AF_INET6 sockets supported");
        }
        int i = NetworkUtils.bindSocketToNetwork(filedescriptor.getInt$(), netId);
        if(i != 0)
            throw (new ErrnoException((new StringBuilder()).append("Binding socket to network ").append(netId).toString(), -i)).rethrowAsSocketException();
        else
            return;
    }

    public void bindSocket(DatagramSocket datagramsocket)
        throws IOException
    {
        datagramsocket.getReuseAddress();
        bindSocket(datagramsocket.getFileDescriptor$());
    }

    public void bindSocket(Socket socket)
        throws IOException
    {
        socket.getReuseAddress();
        bindSocket(socket.getFileDescriptor$());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof Network))
            return false;
        obj = (Network)obj;
        if(netId == ((Network) (obj)).netId)
            flag = true;
        return flag;
    }

    public InetAddress[] getAllByName(String s)
        throws UnknownHostException
    {
        return InetAddress.getAllByNameOnNet(s, netId);
    }

    public InetAddress getByName(String s)
        throws UnknownHostException
    {
        return InetAddress.getByNameOnNet(s, netId);
    }

    public long getNetworkHandle()
    {
        if(netId == 0)
            return 0L;
        else
            return (long)netId << 32 | 0xfacadeL;
    }

    public SocketFactory getSocketFactory()
    {
        if(mNetworkBoundSocketFactory != null) goto _L2; else goto _L1
_L1:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNetworkBoundSocketFactory == null)
        {
            NetworkBoundSocketFactory networkboundsocketfactory = JVM INSTR new #12  <Class Network$NetworkBoundSocketFactory>;
            networkboundsocketfactory.this. NetworkBoundSocketFactory(netId);
            mNetworkBoundSocketFactory = networkboundsocketfactory;
        }
        obj;
        JVM INSTR monitorexit ;
_L2:
        return mNetworkBoundSocketFactory;
        Exception exception;
        exception;
        throw exception;
    }

    public int hashCode()
    {
        return netId * 11;
    }

    public URLConnection openConnection(URL url)
        throws IOException
    {
        Object obj = ConnectivityManager.getInstanceOrNull();
        if(obj == null)
            throw new IOException("No ConnectivityManager yet constructed, please construct one");
        obj = ((ConnectivityManager) (obj)).getProxyForNetwork(this);
        if(obj != null)
            obj = ((ProxyInfo) (obj)).makeProxy();
        else
            obj = Proxy.NO_PROXY;
        return openConnection(url, ((Proxy) (obj)));
    }

    public URLConnection openConnection(URL url, Proxy proxy)
        throws IOException
    {
        if(proxy == null)
            throw new IllegalArgumentException("proxy is null");
        maybeInitHttpClient();
        Object obj = url.getProtocol();
        if(((String) (obj)).equals("http"))
            proxy = HttpHandler.createHttpOkUrlFactory(proxy);
        else
        if(((String) (obj)).equals("https"))
            proxy = HttpsHandler.createHttpsOkUrlFactory(proxy);
        else
            throw new MalformedURLException((new StringBuilder()).append("Invalid URL or unrecognized protocol ").append(((String) (obj))).toString());
        obj = proxy.client();
        ((OkHttpClient) (obj)).setSocketFactory(getSocketFactory()).setConnectionPool(mConnectionPool);
        ((OkHttpClient) (obj)).setDns(mDns);
        return proxy.open(url);
    }

    public String toString()
    {
        return Integer.toString(netId);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(netId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Network createFromParcel(Parcel parcel)
        {
            return new Network(parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Network[] newArray(int j)
        {
            return new Network[j];
        }

        public volatile Object[] newArray(int j)
        {
            return newArray(j);
        }

    }
;
    private static final boolean httpKeepAlive;
    private static final long httpKeepAliveDurationMs = Long.parseLong(System.getProperty("http.keepAliveDuration", "300000"));
    private static final int httpMaxConnections;
    private volatile ConnectionPool mConnectionPool;
    private volatile Dns mDns;
    private final Object mLock;
    private volatile NetworkBoundSocketFactory mNetworkBoundSocketFactory;
    public final int netId;

    static 
    {
        httpKeepAlive = Boolean.parseBoolean(System.getProperty("http.keepAlive", "true"));
        int i;
        if(httpKeepAlive)
            i = Integer.parseInt(System.getProperty("http.maxConnections", "5"));
        else
            i = 0;
        httpMaxConnections = i;
    }

    // Unreferenced inner class android/net/Network$2

/* anonymous class */
    class _cls2
        implements Dns
    {

        public List lookup(String s)
            throws UnknownHostException
        {
            return Arrays.asList(getAllByName(s));
        }

        final Network this$0;

            
            {
                this$0 = Network.this;
                super();
            }
    }

}
