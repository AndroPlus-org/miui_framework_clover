// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util.net;

import java.io.*;
import java.net.*;
import java.security.KeyStore;
import java.security.cert.*;
import java.util.*;
import java.util.logging.Logger;
import javax.net.ssl.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package miui.maml.util.net:
//            ObjectUtils, AccessDeniedException, AuthenticationFailureException, IOUtils

public final class SimpleRequest
{
    public static class HeaderContent
    {

        public String getHeader(String s)
        {
            return (String)headers.get(s);
        }

        public Map getHeaders()
        {
            return headers;
        }

        public void putHeader(String s, String s1)
        {
            headers.put(s, s1);
        }

        public void putHeaders(Map map)
        {
            headers.putAll(map);
        }

        public String toString()
        {
            return (new StringBuilder()).append("HeaderContent{headers=").append(headers).append('}').toString();
        }

        private final Map headers = new HashMap();

        public HeaderContent()
        {
        }
    }

    public static class MapContent extends HeaderContent
    {

        public Object getFromBody(String s)
        {
            return bodies.get(s);
        }

        public String toString()
        {
            return (new StringBuilder()).append("MapContent{bodies=").append(bodies).append('}').toString();
        }

        private Map bodies;

        public MapContent(Map map)
        {
            bodies = map;
        }
    }

    public static class StreamContent extends HeaderContent
    {

        public void closeStream()
        {
            IOUtils.closeQuietly(stream);
        }

        public InputStream getStream()
        {
            return stream;
        }

        private InputStream stream;

        public StreamContent(InputStream inputstream)
        {
            stream = inputstream;
        }
    }

    public static class StringContent extends HeaderContent
    {

        public String getBody()
        {
            return body;
        }

        public String toString()
        {
            return (new StringBuilder()).append("StringContent{body='").append(body).append('\'').append('}').toString();
        }

        private String body;

        public StringContent(String s)
        {
            body = s;
        }
    }


    public SimpleRequest()
    {
    }

    protected static String appendUrl(String s, List list)
    {
        if(s == null)
            throw new NullPointerException("origin is not allowed null");
        StringBuilder stringbuilder = new StringBuilder(s);
        if(list != null)
        {
            list = URLEncodedUtils.format(list, "utf-8");
            if(list != null && list.length() > 0)
            {
                if(s.contains("?"))
                    stringbuilder.append("&");
                else
                    stringbuilder.append("?");
                stringbuilder.append(list);
            }
        }
        return stringbuilder.toString();
    }

    protected static MapContent convertStringToMap(StringContent stringcontent)
    {
        String s;
        Object obj;
        if(stringcontent == null)
            return null;
        s = stringcontent.getBody();
        obj = null;
        JSONObject jsonobject;
        jsonobject = JVM INSTR new #113 <Class JSONObject>;
        jsonobject.JSONObject(s);
        obj = jsonobject;
_L1:
        JSONException jsonexception;
        if(obj == null)
        {
            return null;
        } else
        {
            obj = new MapContent(ObjectUtils.jsonToMap(((JSONObject) (obj))));
            ((MapContent) (obj)).putHeaders(stringcontent.getHeaders());
            return ((MapContent) (obj));
        }
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public static MapContent getAsMap(String s, Map map, Map map1, boolean flag)
        throws IOException, AccessDeniedException, AuthenticationFailureException
    {
        return convertStringToMap(getAsString(s, map, map1, flag));
    }

    public static StreamContent getAsStream(String s, Map map, Map map1)
        throws IOException, AccessDeniedException, AuthenticationFailureException
    {
        int i;
        boolean flag = needIgnore12306CA(map);
        Object obj = appendUrl(s, ObjectUtils.mapToPairs(map));
        s = makeConn(((String) (obj)), map1, flag);
        if(s == null)
        {
            log.severe("failed to create URLConnection");
            throw new IOException("failed to create connection");
        }
        try
        {
            s.setDoInput(true);
            s.setRequestMethod("GET");
            s.setInstanceFollowRedirects(true);
            s.connect();
            i = s.getResponseCode();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IOException("protocol error");
        }
        if(i != 200)
            break MISSING_BLOCK_LABEL_150;
        map = s.getHeaderFields();
        map1 = JVM INSTR new #200 <Class CookieManager>;
        map1.CookieManager();
        obj = URI.create(((String) (obj)));
        map1.put(((URI) (obj)), map);
        map1 = parseCookies(map1.getCookieStore().get(((URI) (obj))));
        map1.putAll(ObjectUtils.listToMap(map));
        map = JVM INSTR new #14  <Class SimpleRequest$StreamContent>;
        map.StreamContent(s.getInputStream());
        map.putHeaders(map1);
        return map;
        if(i != 403)
            break MISSING_BLOCK_LABEL_181;
        s = JVM INSTR new #139 <Class AccessDeniedException>;
        s.AccessDeniedException("access denied, encrypt error or user is forbidden to access the resource");
        throw s;
        if(i != 401 && i != 400)
            break MISSING_BLOCK_LABEL_229;
        s = JVM INSTR new #141 <Class AuthenticationFailureException>;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        s.AuthenticationFailureException(map.append("authentication failure for get, code: ").append(i).toString());
        throw s;
        map1 = log;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        map1.info(map.append("http status error when GET: ").append(i).toString());
        if(i != 301)
            break MISSING_BLOCK_LABEL_319;
        map = log;
        map1 = JVM INSTR new #74  <Class StringBuilder>;
        map1.StringBuilder();
        map.info(map1.append("unexpected redirect from ").append(s.getURL().getHost()).append(" to ").append(s.getHeaderField("Location")).toString());
        s = JVM INSTR new #137 <Class IOException>;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        s.IOException(map.append("unexpected http res code: ").append(i).toString());
        throw s;
    }

    public static StringContent getAsString(String s, Map map, Map map1, boolean flag)
        throws IOException, AccessDeniedException, AuthenticationFailureException
    {
        Object obj;
        boolean flag1 = needIgnore12306CA(map);
        obj = appendUrl(s, ObjectUtils.mapToPairs(map));
        s = makeConn(((String) (obj)), map1, flag1);
        if(s == null)
        {
            log.severe("failed to create URLConnection");
            throw new IOException("failed to create connection");
        }
        int i;
        s.setDoInput(true);
        s.setRequestMethod("GET");
        s.connect();
        i = s.getResponseCode();
        if(i != 200 && i != 302)
            break MISSING_BLOCK_LABEL_259;
        map1 = s.getHeaderFields();
        map = JVM INSTR new #200 <Class CookieManager>;
        map.CookieManager();
        obj = URI.create(((String) (obj)));
        map.put(((URI) (obj)), map1);
        map = parseCookies(map.getCookieStore().get(((URI) (obj))));
        map.putAll(ObjectUtils.listToMap(map1));
        map1 = JVM INSTR new #74  <Class StringBuilder>;
        map1.StringBuilder();
        if(!flag)
            break MISSING_BLOCK_LABEL_232;
        obj = JVM INSTR new #281 <Class BufferedReader>;
        InputStreamReader inputstreamreader = JVM INSTR new #283 <Class InputStreamReader>;
        inputstreamreader.InputStreamReader(s.getInputStream());
        ((BufferedReader) (obj)).BufferedReader(inputstreamreader, 1024);
_L1:
        String s1 = ((BufferedReader) (obj)).readLine();
        if(s1 == null)
            break MISSING_BLOCK_LABEL_227;
        map1.append(s1);
          goto _L1
        map;
        try
        {
            IOUtils.closeQuietly(((java.io.Reader) (obj)));
            throw map;
        }
        // Misplaced declaration of an exception variable
        catch(Map map) { }
        map = JVM INSTR new #137 <Class IOException>;
        map.IOException("protocol error");
        throw map;
        map;
        s.disconnect();
        throw map;
        IOUtils.closeQuietly(((java.io.Reader) (obj)));
        obj = JVM INSTR new #17  <Class SimpleRequest$StringContent>;
        ((StringContent) (obj)).StringContent(map1.toString());
        ((StringContent) (obj)).putHeaders(map);
        s.disconnect();
        return ((StringContent) (obj));
        if(i != 403)
            break MISSING_BLOCK_LABEL_279;
        map = JVM INSTR new #139 <Class AccessDeniedException>;
        map.AccessDeniedException("access denied, encrypt error or user is forbidden to access the resource");
        throw map;
        if(i != 401 && i != 400)
            break MISSING_BLOCK_LABEL_327;
        map1 = JVM INSTR new #141 <Class AuthenticationFailureException>;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        map1.AuthenticationFailureException(map.append("authentication failure for get, code: ").append(i).toString());
        throw map1;
        map1 = log;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        map1.info(map.append("http status error when GET: ").append(i).toString());
        if(i != 301)
            break MISSING_BLOCK_LABEL_417;
        map1 = log;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        map1.info(map.append("unexpected redirect from ").append(s.getURL().getHost()).append(" to ").append(s.getHeaderField("Location")).toString());
        map = JVM INSTR new #137 <Class IOException>;
        map1 = JVM INSTR new #74  <Class StringBuilder>;
        map1.StringBuilder();
        map.IOException(map1.append("unexpected http res code: ").append(i).toString());
        throw map;
    }

    protected static String joinMap(Map map, String s)
    {
        if(map == null)
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        map = map.entrySet();
        int i = 0;
        for(map = map.iterator(); map.hasNext();)
        {
            Object obj = (java.util.Map.Entry)map.next();
            if(i > 0)
                stringbuilder.append(s);
            String s1 = (String)((java.util.Map.Entry) (obj)).getKey();
            obj = (String)((java.util.Map.Entry) (obj)).getValue();
            stringbuilder.append(s1);
            stringbuilder.append("=");
            stringbuilder.append(((String) (obj)));
            i++;
        }

        return stringbuilder.toString();
    }

    protected static HttpURLConnection makeConn(String s, Map map)
    {
        return makeConn(s, map, false);
    }

    protected static HttpURLConnection makeConn(String s, Map map, boolean flag)
    {
        KeyStore keystore = null;
        URL url;
        url = JVM INSTR new #267 <Class URL>;
        url.URL(s);
        s = url;
_L2:
        if(s == null)
        {
            log.severe("failed to init url");
            return null;
        }
        break; /* Loop/switch isn't completed */
        s;
        s.printStackTrace();
        s = keystore;
        if(true) goto _L2; else goto _L1
_L1:
        HttpURLConnection httpurlconnection;
        SSLContext sslcontext;
        Object obj;
        ByteArrayInputStream bytearrayinputstream;
        try
        {
            httpurlconnection = (HttpURLConnection)s.openConnection();
            httpurlconnection.setInstanceFollowRedirects(false);
            httpurlconnection.setConnectTimeout(30000);
            httpurlconnection.setReadTimeout(30000);
            httpurlconnection.setUseCaches(false);
            httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            return null;
        }
        if(map == null)
            break MISSING_BLOCK_LABEL_108;
        httpurlconnection.setRequestProperty("Cookie", joinMap(map, "; "));
        if(!"kyfw.12306.cn".equals(s.getHost()) || !(httpurlconnection instanceof HttpsURLConnection)) goto _L4; else goto _L3
_L3:
        s = (HttpsURLConnection)httpurlconnection;
        sslcontext = SSLContext.getInstance("TLS");
        if(!flag) goto _L6; else goto _L5
_L5:
        map = JVM INSTR new #6   <Class SimpleRequest$1>;
        map._cls1();
        sslcontext.init(null, new TrustManager[] {
            map
        }, null);
_L7:
        s.setSSLSocketFactory(sslcontext.getSocketFactory());
_L4:
        return httpurlconnection;
_L6:
        obj = CertificateFactory.getInstance("X.509");
        keystore = null;
        map = JVM INSTR new #411 <Class BufferedInputStream>;
        bytearrayinputstream = JVM INSTR new #413 <Class ByteArrayInputStream>;
        bytearrayinputstream.ByteArrayInputStream("-----BEGIN CERTIFICATE-----\nMIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4XDTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb29bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHletne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDovLzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Ptx1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQog555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n-----END CERTIFICATE-----".getBytes());
        map.BufferedInputStream(bytearrayinputstream);
        obj = ((CertificateFactory) (obj)).generateCertificate(map);
        keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null, null);
        keystore.setCertificateEntry("ca", ((java.security.cert.Certificate) (obj)));
        obj = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        ((TrustManagerFactory) (obj)).init(keystore);
        sslcontext.init(null, ((TrustManagerFactory) (obj)).getTrustManagers(), null);
        IOUtils.closeQuietly(map);
          goto _L7
        s;
        map = keystore;
_L9:
        IOUtils.closeQuietly(map);
        throw s;
        s;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static boolean needIgnore12306CA(Map map)
    {
        if(map != null && Boolean.TRUE.toString().equalsIgnoreCase((String)map.get("ignore12306ca")))
        {
            map.remove("ignore12306ca");
            return true;
        } else
        {
            return false;
        }
    }

    protected static Map parseCookies(List list)
    {
        HashMap hashmap = new HashMap();
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            Object obj = (HttpCookie)list.next();
            if(!((HttpCookie) (obj)).hasExpired())
            {
                String s = ((HttpCookie) (obj)).getName();
                obj = ((HttpCookie) (obj)).getValue();
                if(s != null)
                    hashmap.put(s, obj);
            }
        } while(true);
        return hashmap;
    }

    public static MapContent postAsMap(String s, Map map, Map map1, boolean flag)
        throws IOException, AccessDeniedException, AuthenticationFailureException
    {
        return convertStringToMap(postAsString(s, map, map1, flag));
    }

    public static StringContent postAsString(String s, Map map, Map map1, boolean flag)
        throws IOException, AccessDeniedException, AuthenticationFailureException
    {
        map1 = makeConn(s, map1, needIgnore12306CA(map));
        if(map1 == null)
        {
            log.severe("failed to create URLConnection");
            throw new IOException("failed to create connection");
        }
        map1.setDoInput(true);
        map1.setDoOutput(true);
        map1.setRequestMethod("POST");
        map1.connect();
        map = ObjectUtils.mapToPairs(map);
        if(map == null)
            break MISSING_BLOCK_LABEL_101;
        Object obj;
        obj = URLEncodedUtils.format(map, "utf-8");
        java.io.OutputStream outputstream = map1.getOutputStream();
        map = JVM INSTR new #512 <Class BufferedOutputStream>;
        map.BufferedOutputStream(outputstream);
        map.write(((String) (obj)).getBytes("utf-8"));
        IOUtils.closeQuietly(map);
        int i = map1.getResponseCode();
        if(i != 200 && i != 302)
            break MISSING_BLOCK_LABEL_305;
        map = map1.getHeaderFields();
        CookieManager cookiemanager = JVM INSTR new #200 <Class CookieManager>;
        cookiemanager.CookieManager();
        s = URI.create(s);
        cookiemanager.put(s, map);
        s = parseCookies(cookiemanager.getCookieStore().get(s));
        s.putAll(ObjectUtils.listToMap(map));
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        if(!flag)
            break MISSING_BLOCK_LABEL_278;
        Object obj1;
        obj1 = JVM INSTR new #281 <Class BufferedReader>;
        obj = JVM INSTR new #283 <Class InputStreamReader>;
        ((InputStreamReader) (obj)).InputStreamReader(map1.getInputStream());
        ((BufferedReader) (obj1)).BufferedReader(((java.io.Reader) (obj)), 1024);
_L1:
        obj = ((BufferedReader) (obj1)).readLine();
        if(obj == null)
            break MISSING_BLOCK_LABEL_273;
        map.append(((String) (obj)));
          goto _L1
        s;
        try
        {
            IOUtils.closeQuietly(((java.io.Reader) (obj1)));
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s = JVM INSTR new #137 <Class IOException>;
        s.IOException("protocol error");
        throw s;
        s;
        map1.disconnect();
        throw s;
        s;
        IOUtils.closeQuietly(map);
        throw s;
        IOUtils.closeQuietly(((java.io.Reader) (obj1)));
        obj1 = JVM INSTR new #17  <Class SimpleRequest$StringContent>;
        ((StringContent) (obj1)).StringContent(map.toString());
        ((StringContent) (obj1)).putHeaders(s);
        map1.disconnect();
        return ((StringContent) (obj1));
        if(i != 403)
            break MISSING_BLOCK_LABEL_325;
        s = JVM INSTR new #139 <Class AccessDeniedException>;
        s.AccessDeniedException("access denied, encrypt error or user is forbidden to access the resource");
        throw s;
        if(i != 401 && i != 400)
            break MISSING_BLOCK_LABEL_374;
        map = JVM INSTR new #141 <Class AuthenticationFailureException>;
        s = JVM INSTR new #74  <Class StringBuilder>;
        s.StringBuilder();
        map.AuthenticationFailureException(s.append("authentication failure for post, code: ").append(i).toString());
        throw map;
        s = log;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        s.info(map.append("http status error when POST: ").append(i).toString());
        if(i != 301)
            break MISSING_BLOCK_LABEL_464;
        map = log;
        s = JVM INSTR new #74  <Class StringBuilder>;
        s.StringBuilder();
        map.info(s.append("unexpected redirect from ").append(map1.getURL().getHost()).append(" to ").append(map1.getHeaderField("Location")).toString());
        s = JVM INSTR new #137 <Class IOException>;
        map = JVM INSTR new #74  <Class StringBuilder>;
        map.StringBuilder();
        s.IOException(map.append("unexpected http res code: ").append(i).toString());
        throw s;
    }

    private static final String CER_12306 = "-----BEGIN CERTIFICATE-----\nMIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4XDTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb29bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHletne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDovLzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Ptx1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQog555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n-----END CERTIFICATE-----";
    private static final boolean DEBUG = false;
    private static final String HOST_12306 = "kyfw.12306.cn";
    public static final String LOCATION = "Location";
    private static final String PARAM_IGNORE_12306_CA = "ignore12306ca";
    private static final int TIMEOUT = 30000;
    public static final String UTF8 = "utf-8";
    private static final Logger log = Logger.getLogger(miui/maml/util/net/SimpleRequest.getSimpleName());
    private static String sUserAgent;


    // Unreferenced inner class miui/maml/util/net/SimpleRequest$1

/* anonymous class */
    static final class _cls1
        implements X509TrustManager
    {

        public void checkClientTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

        public void checkServerTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }

    }

}
