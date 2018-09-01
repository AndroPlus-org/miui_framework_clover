// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.util.Log;
import com.android.org.conscrypt.*;
import java.io.File;
import java.io.IOException;
import javax.net.ssl.SSLContext;

public final class SSLSessionCache
{

    public SSLSessionCache(Context context)
    {
        File file;
        file = context.getDir("sslcache", 0);
        context = null;
        SSLClientSessionCache sslclientsessioncache = FileClientSessionCache.usingDirectory(file);
        context = sslclientsessioncache;
_L2:
        mSessionCache = context;
        return;
        IOException ioexception;
        ioexception;
        Log.w("SSLSessionCache", (new StringBuilder()).append("Unable to create SSL session cache in ").append(file).toString(), ioexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public SSLSessionCache(File file)
        throws IOException
    {
        mSessionCache = FileClientSessionCache.usingDirectory(file);
    }

    public SSLSessionCache(Object obj)
    {
        mSessionCache = (SSLClientSessionCache)obj;
    }

    public static void install(SSLSessionCache sslsessioncache, SSLContext sslcontext)
    {
        Object obj = null;
        javax.net.ssl.SSLSessionContext sslsessioncontext = sslcontext.getClientSessionContext();
        if(sslsessioncontext instanceof ClientSessionContext)
        {
            sslcontext = (ClientSessionContext)sslsessioncontext;
            if(sslsessioncache == null)
                sslsessioncache = obj;
            else
                sslsessioncache = sslsessioncache.mSessionCache;
            sslcontext.setPersistentCache(sslsessioncache);
            return;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Incompatible SSLContext: ").append(sslcontext).toString());
        }
    }

    private static final String TAG = "SSLSessionCache";
    final SSLClientSessionCache mSessionCache;
}
