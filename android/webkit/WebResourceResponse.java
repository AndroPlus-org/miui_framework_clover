// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Map;

public class WebResourceResponse
{

    public WebResourceResponse(String s, String s1, int i, String s2, Map map, InputStream inputstream)
    {
        this(s, s1, inputstream);
        setStatusCodeAndReasonPhrase(i, s2);
        setResponseHeaders(map);
    }

    public WebResourceResponse(String s, String s1, InputStream inputstream)
    {
        mMimeType = s;
        mEncoding = s1;
        setData(inputstream);
    }

    public WebResourceResponse(boolean flag, String s, String s1, int i, String s2, Map map, InputStream inputstream)
    {
        mImmutable = flag;
        mMimeType = s;
        mEncoding = s1;
        mStatusCode = i;
        mReasonPhrase = s2;
        mResponseHeaders = map;
        mInputStream = inputstream;
    }

    private void checkImmutable()
    {
        if(mImmutable)
            throw new IllegalStateException("This WebResourceResponse instance is immutable");
        else
            return;
    }

    public InputStream getData()
    {
        return mInputStream;
    }

    public String getEncoding()
    {
        return mEncoding;
    }

    public String getMimeType()
    {
        return mMimeType;
    }

    public String getReasonPhrase()
    {
        return mReasonPhrase;
    }

    public Map getResponseHeaders()
    {
        return mResponseHeaders;
    }

    public int getStatusCode()
    {
        return mStatusCode;
    }

    public void setData(InputStream inputstream)
    {
        checkImmutable();
        if(inputstream != null && java/io/StringBufferInputStream.isAssignableFrom(inputstream.getClass()))
        {
            throw new IllegalArgumentException("StringBufferInputStream is deprecated and must not be passed to a WebResourceResponse");
        } else
        {
            mInputStream = inputstream;
            return;
        }
    }

    public void setEncoding(String s)
    {
        checkImmutable();
        mEncoding = s;
    }

    public void setMimeType(String s)
    {
        checkImmutable();
        mMimeType = s;
    }

    public void setResponseHeaders(Map map)
    {
        checkImmutable();
        mResponseHeaders = map;
    }

    public void setStatusCodeAndReasonPhrase(int i, String s)
    {
        checkImmutable();
        if(i < 100)
            throw new IllegalArgumentException("statusCode can't be less than 100.");
        if(i > 599)
            throw new IllegalArgumentException("statusCode can't be greater than 599.");
        if(i > 299 && i < 400)
            throw new IllegalArgumentException("statusCode can't be in the [300, 399] range.");
        if(s == null)
            throw new IllegalArgumentException("reasonPhrase can't be null.");
        if(s.trim().isEmpty())
            throw new IllegalArgumentException("reasonPhrase can't be empty.");
        for(int j = 0; j < s.length(); j++)
            if(s.charAt(j) > '\177')
                throw new IllegalArgumentException("reasonPhrase can't contain non-ASCII characters.");

        mStatusCode = i;
        mReasonPhrase = s;
    }

    private String mEncoding;
    private boolean mImmutable;
    private InputStream mInputStream;
    private String mMimeType;
    private String mReasonPhrase;
    private Map mResponseHeaders;
    private int mStatusCode;
}
