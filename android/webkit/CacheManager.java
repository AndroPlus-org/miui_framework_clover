// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import java.io.*;
import java.util.Map;

public final class CacheManager
{
    public static class CacheResult
    {

        public String getContentDisposition()
        {
            return contentdisposition;
        }

        public long getContentLength()
        {
            return contentLength;
        }

        public String getETag()
        {
            return etag;
        }

        public String getEncoding()
        {
            return encoding;
        }

        public long getExpires()
        {
            return expires;
        }

        public String getExpiresString()
        {
            return expiresString;
        }

        public int getHttpStatusCode()
        {
            return httpStatusCode;
        }

        public InputStream getInputStream()
        {
            return inStream;
        }

        public String getLastModified()
        {
            return lastModified;
        }

        public String getLocalPath()
        {
            return localPath;
        }

        public String getLocation()
        {
            return location;
        }

        public String getMimeType()
        {
            return mimeType;
        }

        public OutputStream getOutputStream()
        {
            return outStream;
        }

        public void setContentLength(long l)
        {
            contentLength = l;
        }

        public void setEncoding(String s)
        {
            encoding = s;
        }

        public void setInputStream(InputStream inputstream)
        {
            inStream = inputstream;
        }

        long contentLength;
        String contentdisposition;
        String crossDomain;
        String encoding;
        String etag;
        long expires;
        String expiresString;
        int httpStatusCode;
        InputStream inStream;
        String lastModified;
        String localPath;
        String location;
        String mimeType;
        File outFile;
        OutputStream outStream;

        public CacheResult()
        {
        }
    }


    public CacheManager()
    {
    }

    public static boolean cacheDisabled()
    {
        return false;
    }

    public static boolean endCacheTransaction()
    {
        return false;
    }

    public static CacheResult getCacheFile(String s, Map map)
    {
        return null;
    }

    public static File getCacheFileBaseDir()
    {
        return null;
    }

    static void saveCacheFile(String s, long l, CacheResult cacheresult)
    {
        try
        {
            cacheresult.outStream.close();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return;
        }
        if(!_2D_assertionsDisabled)
            throw new AssertionError();
        else
            return;
    }

    public static void saveCacheFile(String s, CacheResult cacheresult)
    {
        saveCacheFile(s, 0L, cacheresult);
    }

    public static boolean startCacheTransaction()
    {
        return false;
    }

    static final boolean _2D_assertionsDisabled = android/webkit/CacheManager.desiredAssertionStatus() ^ true;

}
