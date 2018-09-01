// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.http;

import com.android.okhttp.*;
import java.io.*;
import java.net.*;
import java.util.Map;

public final class HttpResponseCache extends ResponseCache
    implements Closeable, OkCacheContainer
{

    private HttpResponseCache(AndroidShimResponseCache androidshimresponsecache)
    {
        _flddelegate = androidshimresponsecache;
    }

    public static HttpResponseCache getInstalled()
    {
        ResponseCache responsecache = ResponseCache.getDefault();
        if(responsecache instanceof HttpResponseCache)
            return (HttpResponseCache)responsecache;
        else
            return null;
    }

    public static HttpResponseCache install(File file, long l)
        throws IOException
    {
        android/net/http/HttpResponseCache;
        JVM INSTR monitorenter ;
        Object obj;
        HttpResponseCache httpresponsecache;
        boolean flag;
        obj = ResponseCache.getDefault();
        if(!(obj instanceof HttpResponseCache))
            break MISSING_BLOCK_LABEL_49;
        httpresponsecache = (HttpResponseCache)obj;
        obj = httpresponsecache._flddelegate;
        flag = ((AndroidShimResponseCache) (obj)).isEquivalent(file, l);
        if(!flag)
            break MISSING_BLOCK_LABEL_45;
        android/net/http/HttpResponseCache;
        JVM INSTR monitorexit ;
        return httpresponsecache;
        ((AndroidShimResponseCache) (obj)).close();
        AndroidShimResponseCache androidshimresponsecache = AndroidShimResponseCache.create(file, l);
        file = JVM INSTR new #2   <Class HttpResponseCache>;
        file.HttpResponseCache(androidshimresponsecache);
        ResponseCache.setDefault(file);
        android/net/http/HttpResponseCache;
        JVM INSTR monitorexit ;
        return file;
        file;
        throw file;
    }

    public void close()
        throws IOException
    {
        if(ResponseCache.getDefault() == this)
            ResponseCache.setDefault(null);
        _flddelegate.close();
    }

    public void delete()
        throws IOException
    {
        if(ResponseCache.getDefault() == this)
            ResponseCache.setDefault(null);
        _flddelegate.delete();
    }

    public void flush()
    {
        _flddelegate.flush();
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public CacheResponse get(URI uri, String s, Map map)
        throws IOException
    {
        return _flddelegate.get(uri, s, map);
    }

    public Cache getCache()
    {
        return _flddelegate.getCache();
    }

    public int getHitCount()
    {
        return _flddelegate.getHitCount();
    }

    public int getNetworkCount()
    {
        return _flddelegate.getNetworkCount();
    }

    public int getRequestCount()
    {
        return _flddelegate.getRequestCount();
    }

    public long maxSize()
    {
        return _flddelegate.maxSize();
    }

    public CacheRequest put(URI uri, URLConnection urlconnection)
        throws IOException
    {
        return _flddelegate.put(uri, urlconnection);
    }

    public long size()
    {
        long l;
        try
        {
            l = _flddelegate.size();
        }
        catch(IOException ioexception)
        {
            return -1L;
        }
        return l;
    }

    private final AndroidShimResponseCache _flddelegate;
}
