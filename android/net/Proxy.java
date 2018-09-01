// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.text.TextUtils;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.net:
//            NetworkUtils, Uri, ProxyInfo, PacProxySelector, 
//            ConnectivityManager

public final class Proxy
{

    public Proxy()
    {
    }

    public static final String getDefaultHost()
    {
        String s = System.getProperty("http.proxyHost");
        if(TextUtils.isEmpty(s))
            return null;
        else
            return s;
    }

    public static final int getDefaultPort()
    {
        if(getDefaultHost() == null)
            return -1;
        int i;
        try
        {
            i = Integer.parseInt(System.getProperty("http.proxyPort"));
        }
        catch(NumberFormatException numberformatexception)
        {
            return -1;
        }
        return i;
    }

    public static final String getHost(Context context)
    {
        context = getProxy(context, null);
        if(context == java.net.Proxy.NO_PROXY)
            return null;
        try
        {
            context = ((InetSocketAddress)context.address()).getHostName();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        return context;
    }

    public static final int getPort(Context context)
    {
        context = getProxy(context, null);
        if(context == java.net.Proxy.NO_PROXY)
            return -1;
        int i;
        try
        {
            i = ((InetSocketAddress)context.address()).getPort();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return -1;
        }
        return i;
    }

    public static final java.net.Proxy getProxy(Context context, String s)
    {
        if(s != null && isLocalHost("") ^ true)
        {
            context = URI.create(s);
            context = ProxySelector.getDefault().select(context);
            if(context.size() > 0)
                return (java.net.Proxy)context.get(0);
        }
        return java.net.Proxy.NO_PROXY;
    }

    private static final boolean isLocalHost(String s)
    {
        if(s == null)
            return false;
        if(s == null)
            break MISSING_BLOCK_LABEL_36;
        if(s.equalsIgnoreCase("localhost"))
            return true;
        boolean flag = NetworkUtils.numericToInetAddress(s).isLoopbackAddress();
        if(flag)
            return true;
        break MISSING_BLOCK_LABEL_36;
        s;
        return false;
    }

    public static final void setHttpProxySystemProperty(ProxyInfo proxyinfo)
    {
        String s = null;
        String s1 = null;
        String s2 = null;
        Uri uri = Uri.EMPTY;
        if(proxyinfo != null)
        {
            s = proxyinfo.getHost();
            s1 = Integer.toString(proxyinfo.getPort());
            s2 = proxyinfo.getExclusionListAsString();
            uri = proxyinfo.getPacFileUrl();
        }
        setHttpProxySystemProperty(s, s1, s2, uri);
    }

    public static final void setHttpProxySystemProperty(String s, String s1, String s2, Uri uri)
    {
        String s3 = s2;
        if(s2 != null)
            s3 = s2.replace(",", "|");
        if(s != null)
        {
            System.setProperty("http.proxyHost", s);
            System.setProperty("https.proxyHost", s);
        } else
        {
            System.clearProperty("http.proxyHost");
            System.clearProperty("https.proxyHost");
        }
        if(s1 != null)
        {
            System.setProperty("http.proxyPort", s1);
            System.setProperty("https.proxyPort", s1);
        } else
        {
            System.clearProperty("http.proxyPort");
            System.clearProperty("https.proxyPort");
        }
        if(s3 != null)
        {
            System.setProperty("http.nonProxyHosts", s3);
            System.setProperty("https.nonProxyHosts", s3);
        } else
        {
            System.clearProperty("http.nonProxyHosts");
            System.clearProperty("https.nonProxyHosts");
        }
        if(!Uri.EMPTY.equals(uri))
            ProxySelector.setDefault(new PacProxySelector());
        else
            ProxySelector.setDefault(sDefaultProxySelector);
    }

    public static int validate(String s, String s1, String s2)
    {
        Matcher matcher = HOSTNAME_PATTERN.matcher(s);
        s2 = EXCLLIST_PATTERN.matcher(s2);
        if(!matcher.matches())
            return 2;
        if(!s2.matches())
            return 5;
        if(s.length() > 0 && s1.length() == 0)
            return 3;
        if(s1.length() > 0)
        {
            if(s.length() == 0)
                return 1;
            int i;
            try
            {
                i = Integer.parseInt(s1);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return 4;
            }
            if(i <= 0 || i > 65535)
                return 4;
        }
        return 0;
    }

    private static final Pattern EXCLLIST_PATTERN = Pattern.compile("^$|^[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*(\\.[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*)*(,[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*(\\.[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*)*)*$");
    private static final String EXCLLIST_REGEXP = "^$|^[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*(\\.[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*)*(,[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*(\\.[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*)*)*$";
    private static final String EXCL_REGEX = "[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*(\\.[a-zA-Z0-9*]+(\\-[a-zA-Z0-9*]+)*)*";
    public static final String EXTRA_PROXY_INFO = "android.intent.extra.PROXY_INFO";
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^$|^[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*(\\.[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*)*$");
    private static final String HOSTNAME_REGEXP = "^$|^[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*(\\.[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*)*$";
    private static final String NAME_IP_REGEX = "[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*(\\.[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*)*";
    public static final String PROXY_CHANGE_ACTION = "android.intent.action.PROXY_CHANGE";
    public static final int PROXY_EXCLLIST_INVALID = 5;
    public static final int PROXY_HOSTNAME_EMPTY = 1;
    public static final int PROXY_HOSTNAME_INVALID = 2;
    public static final int PROXY_PORT_EMPTY = 3;
    public static final int PROXY_PORT_INVALID = 4;
    public static final int PROXY_VALID = 0;
    private static final String TAG = "Proxy";
    private static ConnectivityManager sConnectivityManager = null;
    private static final ProxySelector sDefaultProxySelector = ProxySelector.getDefault();

}
