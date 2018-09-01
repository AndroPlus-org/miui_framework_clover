// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.params;


// Referenced classes of package org.apache.http.params:
//            CoreConnectionPNames, HttpParams

public final class HttpConnectionParams
    implements CoreConnectionPNames
{

    private HttpConnectionParams()
    {
    }

    public static int getConnectionTimeout(HttpParams httpparams)
    {
        if(httpparams == null)
            throw new IllegalArgumentException("HTTP parameters may not be null");
        else
            return httpparams.getIntParameter("http.connection.timeout", 0);
    }

    public static int getLinger(HttpParams httpparams)
    {
        if(httpparams == null)
            throw new IllegalArgumentException("HTTP parameters may not be null");
        else
            return httpparams.getIntParameter("http.socket.linger", -1);
    }

    public static int getSoTimeout(HttpParams httpparams)
    {
        if(httpparams == null)
            throw new IllegalArgumentException("HTTP parameters may not be null");
        else
            return httpparams.getIntParameter("http.socket.timeout", 0);
    }

    public static int getSocketBufferSize(HttpParams httpparams)
    {
        if(httpparams == null)
            throw new IllegalArgumentException("HTTP parameters may not be null");
        else
            return httpparams.getIntParameter("http.socket.buffer-size", -1);
    }

    public static boolean getTcpNoDelay(HttpParams httpparams)
    {
        if(httpparams == null)
            throw new IllegalArgumentException("HTTP parameters may not be null");
        else
            return httpparams.getBooleanParameter("http.tcp.nodelay", true);
    }

    public static boolean isStaleCheckingEnabled(HttpParams httpparams)
    {
        if(httpparams == null)
            throw new IllegalArgumentException("HTTP parameters may not be null");
        else
            return httpparams.getBooleanParameter("http.connection.stalecheck", true);
    }

    public static void setConnectionTimeout(HttpParams httpparams, int i)
    {
        if(httpparams == null)
        {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        } else
        {
            httpparams.setIntParameter("http.connection.timeout", i);
            return;
        }
    }

    public static void setLinger(HttpParams httpparams, int i)
    {
        if(httpparams == null)
        {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        } else
        {
            httpparams.setIntParameter("http.socket.linger", i);
            return;
        }
    }

    public static void setSoTimeout(HttpParams httpparams, int i)
    {
        if(httpparams == null)
        {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        } else
        {
            httpparams.setIntParameter("http.socket.timeout", i);
            return;
        }
    }

    public static void setSocketBufferSize(HttpParams httpparams, int i)
    {
        if(httpparams == null)
        {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        } else
        {
            httpparams.setIntParameter("http.socket.buffer-size", i);
            return;
        }
    }

    public static void setStaleCheckingEnabled(HttpParams httpparams, boolean flag)
    {
        if(httpparams == null)
        {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        } else
        {
            httpparams.setBooleanParameter("http.connection.stalecheck", flag);
            return;
        }
    }

    public static void setTcpNoDelay(HttpParams httpparams, boolean flag)
    {
        if(httpparams == null)
        {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        } else
        {
            httpparams.setBooleanParameter("http.tcp.nodelay", flag);
            return;
        }
    }
}
