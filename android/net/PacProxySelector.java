// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.ServiceManager;
import android.util.Log;
import com.android.net.IProxyService;
import com.google.android.collect.Lists;
import java.io.IOException;
import java.net.*;
import java.util.List;

public class PacProxySelector extends ProxySelector
{

    public PacProxySelector()
    {
        mProxyService = com.android.net.IProxyService.Stub.asInterface(ServiceManager.getService("com.android.net.IProxyService"));
        if(mProxyService == null)
            Log.e("PacProxySelector", "PacManager: no proxy service");
        mDefaultList = Lists.newArrayList(new Proxy[] {
            Proxy.NO_PROXY
        });
    }

    private static List parseResponse(String s)
    {
        int i = 0;
        s = s.split(";");
        java.util.ArrayList arraylist = Lists.newArrayList();
        int j = s.length;
        while(i < j) 
        {
            Object obj = s[i].trim();
            if(((String) (obj)).equals("DIRECT"))
                arraylist.add(Proxy.NO_PROXY);
            else
            if(((String) (obj)).startsWith("PROXY "))
            {
                obj = proxyFromHostPort(java.net.Proxy.Type.HTTP, ((String) (obj)).substring("PROXY ".length()));
                if(obj != null)
                    arraylist.add(obj);
            } else
            if(((String) (obj)).startsWith("SOCKS "))
            {
                obj = proxyFromHostPort(java.net.Proxy.Type.SOCKS, ((String) (obj)).substring("SOCKS ".length()));
                if(obj != null)
                    arraylist.add(obj);
            }
            i++;
        }
        if(arraylist.size() == 0)
            arraylist.add(Proxy.NO_PROXY);
        return arraylist;
    }

    private static Proxy proxyFromHostPort(java.net.Proxy.Type type, String s)
    {
        try
        {
            String as[] = s.split(":");
            type = new Proxy(type, InetSocketAddress.createUnresolved(as[0], Integer.parseInt(as[1])));
        }
        // Misplaced declaration of an exception variable
        catch(java.net.Proxy.Type type)
        {
            Log.d("PacProxySelector", (new StringBuilder()).append("Unable to parse proxy ").append(s).append(" ").append(type).toString());
            return null;
        }
        return type;
    }

    public void connectFailed(URI uri, SocketAddress socketaddress, IOException ioexception)
    {
    }

    public List select(URI uri)
    {
        Object obj;
        URI uri1;
        URI uri2;
        URI uri3;
        if(mProxyService == null)
            mProxyService = com.android.net.IProxyService.Stub.asInterface(ServiceManager.getService("com.android.net.IProxyService"));
        if(mProxyService == null)
        {
            Log.e("PacProxySelector", "select: no proxy service return NO_PROXY");
            return Lists.newArrayList(new Proxy[] {
                Proxy.NO_PROXY
            });
        }
        obj = null;
        uri1 = uri;
        uri2 = uri;
        uri3 = uri;
        if("http".equalsIgnoreCase(uri.getScheme()))
            break MISSING_BLOCK_LABEL_107;
        uri2 = uri;
        uri3 = uri;
        uri1 = JVM INSTR new #177 <Class URI>;
        uri2 = uri;
        uri3 = uri;
        uri1.URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), "/", null, null);
        uri2 = uri1;
        uri3 = uri1;
        try
        {
            uri = uri1.toURL().toString();
        }
        // Misplaced declaration of an exception variable
        catch(URI uri)
        {
            uri = uri3.getHost();
            uri1 = uri3;
        }
        // Misplaced declaration of an exception variable
        catch(URI uri)
        {
            uri = uri2.getHost();
            uri1 = uri2;
        }
        try
        {
            uri = mProxyService.resolvePacFile(uri1.getHost(), uri);
        }
        // Misplaced declaration of an exception variable
        catch(URI uri)
        {
            Log.e("PacProxySelector", "Error resolving PAC File", uri);
            uri = obj;
        }
        if(uri == null)
            return mDefaultList;
        else
            return parseResponse(uri);
    }

    private static final String PROXY = "PROXY ";
    public static final String PROXY_SERVICE = "com.android.net.IProxyService";
    private static final String SOCKS = "SOCKS ";
    private static final String TAG = "PacProxySelector";
    private final List mDefaultList;
    private IProxyService mProxyService;
}
