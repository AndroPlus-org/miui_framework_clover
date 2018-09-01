// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.net:
//            ParseException

public class WebAddress
{

    public WebAddress(String s)
        throws ParseException
    {
        if(s == null)
            throw new NullPointerException();
        mScheme = "";
        mHost = "";
        mPort = -1;
        mPath = "/";
        mAuthInfo = "";
        s = sAddressPattern.matcher(s);
        if(!s.matches()) goto _L2; else goto _L1
_L1:
        String s1 = s.group(1);
        if(s1 != null)
            mScheme = s1.toLowerCase(Locale.ROOT);
        s1 = s.group(2);
        if(s1 != null)
            mAuthInfo = s1;
        s1 = s.group(3);
        if(s1 != null)
            mHost = s1;
        s1 = s.group(4);
        if(s1 != null && s1.length() > 0)
            try
            {
                mPort = Integer.parseInt(s1);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new ParseException("Bad port");
            }
        s = s.group(5);
        if(s != null && s.length() > 0)
            if(s.charAt(0) == '/')
                mPath = s;
            else
                mPath = (new StringBuilder()).append("/").append(s).toString();
        if(mPort != 443 || !mScheme.equals("")) goto _L4; else goto _L3
_L3:
        mScheme = "https";
_L6:
        if(mScheme.equals(""))
            mScheme = "http";
        return;
_L2:
        throw new ParseException("Bad address");
_L4:
        if(mPort == -1)
            if(mScheme.equals("https"))
                mPort = 443;
            else
                mPort = 80;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public String getAuthInfo()
    {
        return mAuthInfo;
    }

    public String getHost()
    {
        return mHost;
    }

    public String getPath()
    {
        return mPath;
    }

    public int getPort()
    {
        return mPort;
    }

    public String getScheme()
    {
        return mScheme;
    }

    public void setAuthInfo(String s)
    {
        mAuthInfo = s;
    }

    public void setHost(String s)
    {
        mHost = s;
    }

    public void setPath(String s)
    {
        mPath = s;
    }

    public void setPort(int i)
    {
        mPort = i;
    }

    public void setScheme(String s)
    {
        mScheme = s;
    }

    public String toString()
    {
        String s2;
label0:
        {
            String s = "";
            if(mPort == 443 || !mScheme.equals("https"))
            {
                s2 = s;
                if(mPort == 80)
                    break label0;
                s2 = s;
                if(!mScheme.equals("http"))
                    break label0;
            }
            s2 = (new StringBuilder()).append(":").append(Integer.toString(mPort)).toString();
        }
        String s1 = "";
        if(mAuthInfo.length() > 0)
            s1 = (new StringBuilder()).append(mAuthInfo).append("@").toString();
        return (new StringBuilder()).append(mScheme).append("://").append(s1).append(mHost).append(s2).append(mPath).toString();
    }

    static final int MATCH_GROUP_AUTHORITY = 2;
    static final int MATCH_GROUP_HOST = 3;
    static final int MATCH_GROUP_PATH = 5;
    static final int MATCH_GROUP_PORT = 4;
    static final int MATCH_GROUP_SCHEME = 1;
    static Pattern sAddressPattern = Pattern.compile("(?:(http|https|file)\\:\\/\\/)?(?:([-A-Za-z0-9$_.+!*'(),;?&=]+(?:\\:[-A-Za-z0-9$_.+!*'(),;?&=]+)?)@)?([a-zA-Z0-9\240-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF%_-][a-zA-Z0-9\240-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF%_\\.-]*|\\[[0-9a-fA-F:\\.]+\\])?(?:\\:([0-9]*))?(\\/?[^#]*)?.*", 2);
    private String mAuthInfo;
    private String mHost;
    private String mPath;
    private int mPort;
    private String mScheme;

}
