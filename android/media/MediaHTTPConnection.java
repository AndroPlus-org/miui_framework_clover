// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.net.NetworkUtils;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import java.io.*;
import java.net.*;
import java.util.*;

public class MediaHTTPConnection extends IMediaHTTPConnection.Stub
{

    public MediaHTTPConnection()
    {
        mCurrentOffset = -1L;
        mURL = null;
        mHeaders = null;
        mConnection = null;
        mTotalSize = -1L;
        mInputStream = null;
        mAllowCrossDomainRedirect = true;
        mAllowCrossProtocolRedirect = true;
        if(CookieHandler.getDefault() == null)
            Log.w("MediaHTTPConnection", "MediaHTTPConnection: Unexpected. No CookieHandler found.");
        native_setup();
    }

    private Map convertHeaderStringToMap(String s)
    {
        HashMap hashmap = new HashMap();
        s = s.split("\r\n");
        int i = s.length;
        for(int j = 0; j < i; j++)
        {
            String s1 = s[j];
            int k = s1.indexOf(":");
            if(k < 0)
                continue;
            String s2 = s1.substring(0, k);
            s1 = s1.substring(k + 1);
            if(!filterOutInternalHeaders(s2, s1))
                hashmap.put(s2, s1);
        }

        return hashmap;
    }

    private boolean filterOutInternalHeaders(String s, String s1)
    {
        if("android-allow-cross-domain-redirect".equalsIgnoreCase(s))
        {
            mAllowCrossDomainRedirect = parseBoolean(s1);
            mAllowCrossProtocolRedirect = mAllowCrossDomainRedirect;
            return true;
        } else
        {
            return false;
        }
    }

    private static final boolean isLocalHost(URL url)
    {
        if(url == null)
            return false;
        url = url.getHost();
        if(url == null)
            return false;
        if(url.equalsIgnoreCase("localhost"))
            return true;
        boolean flag;
        try
        {
            flag = NetworkUtils.numericToInetAddress(url).isLoopbackAddress();
        }
        // Misplaced declaration of an exception variable
        catch(URL url)
        {
            return false;
        }
label0:
        {
            if(flag)
                return true;
            break label0;
        }
    }

    private final native void native_finalize();

    private final native IBinder native_getIMemory();

    private static final native void native_init();

    private final native int native_readAt(long l, int i);

    private final native void native_setup();

    private boolean parseBoolean(String s)
    {
        boolean flag = true;
        boolean flag1 = true;
        long l;
        try
        {
            l = Long.parseLong(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            boolean flag2 = flag;
            if(!"true".equalsIgnoreCase(s))
                flag2 = "yes".equalsIgnoreCase(s);
            return flag2;
        }
        if(l == 0L)
            flag1 = false;
        return flag1;
    }

    private int readAt(long l, byte abyte0[], int i)
    {
        StrictMode.setThreadPolicy((new android.os.StrictMode.ThreadPolicy.Builder()).permitAll().build());
        int j;
        int k;
        try
        {
            if(l != mCurrentOffset)
                seekTo(l);
            j = mInputStream.read(abyte0, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("MediaHTTPConnection", (new StringBuilder()).append("readAt ").append(l).append(" / ").append(i).append(" => ").append(abyte0).toString());
            return -1010;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("MediaHTTPConnection", (new StringBuilder()).append("readAt ").append(l).append(" / ").append(i).append(" => ").append(abyte0).toString());
            return -1010;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("MediaHTTPConnection", (new StringBuilder()).append("readAt ").append(l).append(" / ").append(i).append(" => ").append(abyte0).toString());
            return -1010;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            return -1;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            return -1;
        }
        k = j;
        if(j == -1)
            k = 0;
        mCurrentOffset = mCurrentOffset + (long)k;
        return k;
    }

    private void seekTo(long l)
        throws IOException
    {
        int i;
        Object obj;
        teardownConnection();
        i = 0;
        boolean flag;
        Iterator iterator;
        try
        {
            obj = mURL;
            flag = isLocalHost(((URL) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            mTotalSize = -1L;
            teardownConnection();
            mCurrentOffset = -1L;
            throw obj;
        }
_L6:
        if(!flag) goto _L2; else goto _L1
_L1:
        mConnection = (HttpURLConnection)((URL) (obj)).openConnection(Proxy.NO_PROXY);
_L4:
        mConnection.setConnectTimeout(30000);
        mConnection.setInstanceFollowRedirects(mAllowCrossDomainRedirect);
        if(mHeaders != null)
            for(iterator = mHeaders.entrySet().iterator(); iterator.hasNext(); mConnection.setRequestProperty((String)((java.util.Map.Entry) (obj)).getKey(), (String)((java.util.Map.Entry) (obj)).getValue()))
                obj = (java.util.Map.Entry)iterator.next();

        break MISSING_BLOCK_LABEL_173;
_L2:
        mConnection = (HttpURLConnection)((URL) (obj)).openConnection();
        if(true) goto _L4; else goto _L3
_L3:
        if(l <= 0L)
            break MISSING_BLOCK_LABEL_224;
        HttpURLConnection httpurlconnection = mConnection;
        obj = JVM INSTR new #212 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        httpurlconnection.setRequestProperty("Range", ((StringBuilder) (obj)).append("bytes=").append(l).append("-").toString());
        int j = mConnection.getResponseCode();
        if(j == 300 || j == 301 || j == 302 || j == 303 || j == 307)
            break MISSING_BLOCK_LABEL_381;
        if(mAllowCrossDomainRedirect)
            mURL = mConnection.getURL();
        if(j != 206)
            break MISSING_BLOCK_LABEL_711;
        obj = mConnection.getHeaderField("Content-Range");
        mTotalSize = -1L;
        if(obj == null)
            break MISSING_BLOCK_LABEL_354;
        i = ((String) (obj)).lastIndexOf('/');
        if(i < 0)
            break MISSING_BLOCK_LABEL_354;
        obj = ((String) (obj)).substring(i + 1);
        URL url;
        int k;
        boolean flag1;
        try
        {
            mTotalSize = Long.parseLong(((String) (obj)));
        }
        catch(NumberFormatException numberformatexception) { }
        if(l <= 0L || j == 206)
            break MISSING_BLOCK_LABEL_747;
        obj = JVM INSTR new #175 <Class ProtocolException>;
        ((ProtocolException) (obj)).ProtocolException();
        throw obj;
        k = i + 1;
        if(k <= 20)
            break MISSING_BLOCK_LABEL_432;
        NoRouteToHostException noroutetohostexception = JVM INSTR new #177 <Class NoRouteToHostException>;
        obj = JVM INSTR new #212 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        noroutetohostexception.NoRouteToHostException(((StringBuilder) (obj)).append("Too many redirects: ").append(k).toString());
        throw noroutetohostexception;
        obj = mConnection.getRequestMethod();
        if(j != 307)
            break MISSING_BLOCK_LABEL_491;
        if(((String) (obj)).equals("GET") ^ true && ((String) (obj)).equals("HEAD") ^ true)
        {
            obj = JVM INSTR new #177 <Class NoRouteToHostException>;
            ((NoRouteToHostException) (obj)).NoRouteToHostException("Invalid redirect");
            throw obj;
        }
        obj = mConnection.getHeaderField("Location");
        if(obj != null)
            break MISSING_BLOCK_LABEL_524;
        obj = JVM INSTR new #177 <Class NoRouteToHostException>;
        ((NoRouteToHostException) (obj)).NoRouteToHostException("Invalid redirect");
        throw obj;
        url = JVM INSTR new #136 <Class URL>;
        url.URL(mURL, ((String) (obj)));
        if(!url.getProtocol().equals("https") && url.getProtocol().equals("http") ^ true)
        {
            obj = JVM INSTR new #177 <Class NoRouteToHostException>;
            ((NoRouteToHostException) (obj)).NoRouteToHostException("Unsupported protocol redirect");
            throw obj;
        }
        flag1 = mURL.getProtocol().equals(url.getProtocol());
        if(mAllowCrossProtocolRedirect || !(flag1 ^ true))
            break MISSING_BLOCK_LABEL_633;
        obj = JVM INSTR new #177 <Class NoRouteToHostException>;
        ((NoRouteToHostException) (obj)).NoRouteToHostException("Cross-protocol redirects are disallowed");
        throw obj;
        flag1 = mURL.getHost().equals(url.getHost());
        if(mAllowCrossDomainRedirect || !(flag1 ^ true))
            break MISSING_BLOCK_LABEL_680;
        obj = JVM INSTR new #177 <Class NoRouteToHostException>;
        ((NoRouteToHostException) (obj)).NoRouteToHostException("Cross-domain redirects are disallowed");
        throw obj;
        i = k;
        obj = url;
        if(j == 307) goto _L6; else goto _L5
_L5:
        mURL = url;
        i = k;
        obj = url;
          goto _L6
        if(j == 200)
            break MISSING_BLOCK_LABEL_732;
        IOException ioexception = JVM INSTR new #181 <Class IOException>;
        ioexception.IOException();
        throw ioexception;
        mTotalSize = mConnection.getContentLength();
        break MISSING_BLOCK_LABEL_354;
        BufferedInputStream bufferedinputstream = JVM INSTR new #362 <Class BufferedInputStream>;
        bufferedinputstream.BufferedInputStream(mConnection.getInputStream());
        mInputStream = bufferedinputstream;
        mCurrentOffset = l;
        return;
          goto _L6
    }

    private void teardownConnection()
    {
        if(mConnection != null)
        {
            if(mInputStream != null)
            {
                try
                {
                    mInputStream.close();
                }
                catch(IOException ioexception) { }
                mInputStream = null;
            }
            mConnection.disconnect();
            mConnection = null;
            mCurrentOffset = -1L;
        }
    }

    public IBinder connect(String s, String s1)
    {
        try
        {
            disconnect();
            mAllowCrossDomainRedirect = true;
            URL url = JVM INSTR new #136 <Class URL>;
            url.URL(s);
            mURL = url;
            mHeaders = convertHeaderStringToMap(s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return native_getIMemory();
    }

    public void disconnect()
    {
        teardownConnection();
        mHeaders = null;
        mURL = null;
    }

    protected void finalize()
    {
        native_finalize();
    }

    public String getMIMEType()
    {
        if(mConnection == null)
            try
            {
                seekTo(0L);
            }
            catch(IOException ioexception)
            {
                return "application/octet-stream";
            }
        return mConnection.getContentType();
    }

    public long getSize()
    {
        if(mConnection == null)
            try
            {
                seekTo(0L);
            }
            catch(IOException ioexception)
            {
                return -1L;
            }
        return mTotalSize;
    }

    public String getUri()
    {
        return mURL.toString();
    }

    public int readAt(long l, int i)
    {
        return native_readAt(l, i);
    }

    private static final int CONNECT_TIMEOUT_MS = 30000;
    private static final int HTTP_TEMP_REDIRECT = 307;
    private static final int MAX_REDIRECTS = 20;
    private static final String TAG = "MediaHTTPConnection";
    private static final boolean VERBOSE = false;
    private boolean mAllowCrossDomainRedirect;
    private boolean mAllowCrossProtocolRedirect;
    private HttpURLConnection mConnection;
    private long mCurrentOffset;
    private Map mHeaders;
    private InputStream mInputStream;
    private long mNativeContext;
    private long mTotalSize;
    private URL mURL;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
