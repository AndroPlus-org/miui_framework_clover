// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.IBinder;
import android.util.Log;
import java.net.*;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.media:
//            MediaHTTPConnection, IMediaHTTPConnection

public class MediaHTTPService extends IMediaHTTPService.Stub
{

    public MediaHTTPService(List list)
    {
        mCookieStoreInitialized = new Boolean(false);
        mCookies = list;
        Log.v("MediaHTTPService", (new StringBuilder()).append("MediaHTTPService(").append(this).append("): Cookies: ").append(list).toString());
    }

    static IBinder createHttpServiceBinderIfNecessary(String s)
    {
        return createHttpServiceBinderIfNecessary(s, null);
    }

    static IBinder createHttpServiceBinderIfNecessary(String s, List list)
    {
        if(s.startsWith("http://") || s.startsWith("https://"))
            return (new MediaHTTPService(list)).asBinder();
        if(s.startsWith("widevine://"))
            Log.d("MediaHTTPService", "Widevine classic is no longer supported");
        return null;
    }

    public IMediaHTTPConnection makeHTTPConnection()
    {
        Boolean boolean1 = mCookieStoreInitialized;
        boolean1;
        JVM INSTR monitorenter ;
        Object obj;
        if(mCookieStoreInitialized.booleanValue())
            break MISSING_BLOCK_LABEL_271;
        obj = CookieHandler.getDefault();
        if(obj != null)
            break MISSING_BLOCK_LABEL_172;
        obj = JVM INSTR new #99  <Class CookieManager>;
        ((CookieManager) (obj)).CookieManager();
        CookieHandler.setDefault(((CookieHandler) (obj)));
        StringBuilder stringbuilder = JVM INSTR new #29  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.v("MediaHTTPService", stringbuilder.append("makeHTTPConnection: CookieManager created: ").append(obj).toString());
_L2:
        CookieStore cookiestore;
        Iterator iterator;
        if(mCookies == null)
            break MISSING_BLOCK_LABEL_215;
        if(!(obj instanceof CookieManager))
            break MISSING_BLOCK_LABEL_207;
        cookiestore = ((CookieManager)obj).getCookieStore();
        iterator = mCookies.iterator();
_L1:
        HttpCookie httpcookie;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_215;
        httpcookie = (HttpCookie)iterator.next();
        cookiestore.add(null, httpcookie);
          goto _L1
        Exception exception;
        exception;
        StringBuilder stringbuilder3 = JVM INSTR new #29  <Class StringBuilder>;
        stringbuilder3.StringBuilder();
        Log.v("MediaHTTPService", stringbuilder3.append("makeHTTPConnection: CookieStore.add").append(exception).toString());
          goto _L1
        obj;
        throw obj;
        StringBuilder stringbuilder1 = JVM INSTR new #29  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.v("MediaHTTPService", stringbuilder1.append("makeHTTPConnection: CookieHandler (").append(obj).append(") exists.").toString());
          goto _L2
        Log.w("MediaHTTPService", "makeHTTPConnection: The installed CookieHandler is not a CookieManager. Can\u2019t add the provided cookies to the cookie store.");
        mCookieStoreInitialized = Boolean.valueOf(true);
        StringBuilder stringbuilder2 = JVM INSTR new #29  <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        Log.v("MediaHTTPService", stringbuilder2.append("makeHTTPConnection(").append(this).append("): cookieHandler: ").append(obj).append(" Cookies: ").append(mCookies).toString());
        boolean1;
        JVM INSTR monitorexit ;
        return new MediaHTTPConnection();
    }

    private static final String TAG = "MediaHTTPService";
    private Boolean mCookieStoreInitialized;
    private List mCookies;
}
