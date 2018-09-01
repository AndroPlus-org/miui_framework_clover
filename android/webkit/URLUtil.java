// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.net.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.webkit:
//            MimeTypeMap

public final class URLUtil
{

    public URLUtil()
    {
    }

    public static String composeSearchUrl(String s, String s1, String s2)
    {
        int i = s1.indexOf(s2);
        if(i < 0)
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s1.substring(0, i));
        try
        {
            stringbuilder.append(URLEncoder.encode(s, "utf-8"));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        stringbuilder.append(s1.substring(s2.length() + i));
        return stringbuilder.toString();
    }

    public static byte[] decode(byte abyte0[])
        throws IllegalArgumentException
    {
        byte abyte1[];
        int i;
label0:
        {
            if(abyte0.length == 0)
                return new byte[0];
            abyte1 = new byte[abyte0.length];
            i = 0;
            int j = 0;
            do
            {
                if(j >= abyte0.length)
                    break label0;
                byte byte0 = abyte0[j];
                int k = byte0;
                int l = j;
                if(byte0 == 37)
                {
                    if(abyte0.length - j <= 2)
                        break;
                    k = (byte)(parseHex(abyte0[j + 1]) * 16 + parseHex(abyte0[j + 2]));
                    l = j + 2;
                }
                abyte1[i] = (byte)k;
                j = l + 1;
                i++;
            } while(true);
            throw new IllegalArgumentException("Invalid format");
        }
        abyte0 = new byte[i];
        System.arraycopy(abyte1, 0, abyte0, 0, i);
        return abyte0;
    }

    public static final String guessFileName(String s, String s1, String s2)
    {
        String s3 = null;
        String s4 = null;
        Object obj = null;
        if(s1 != null)
        {
            s1 = parseContentDisposition(s1);
            s3 = s1;
            if(s1 != null)
            {
                int i = s1.lastIndexOf('/') + 1;
                s3 = s1;
                if(i > 0)
                    s3 = s1.substring(i);
            }
        }
        s1 = s3;
        if(s3 == null)
        {
            String s5 = Uri.decode(s);
            s1 = s3;
            if(s5 != null)
            {
                int j = s5.indexOf('?');
                s = s5;
                if(j > 0)
                    s = s5.substring(0, j);
                s1 = s3;
                if(!s.endsWith("/"))
                {
                    int k = s.lastIndexOf('/') + 1;
                    s1 = s3;
                    if(k > 0)
                        s1 = s.substring(k);
                }
            }
        }
        s3 = s1;
        if(s1 == null)
            s3 = "downloadfile";
        int l = s3.indexOf('.');
        if(l < 0)
        {
            s1 = obj;
            if(s2 != null)
            {
                s = MimeTypeMap.getSingleton().getExtensionFromMimeType(s2);
                s1 = s;
                if(s != null)
                    s1 = (new StringBuilder()).append(".").append(s).toString();
            }
            s = s1;
            s4 = s3;
            if(s1 == null)
                if(s2 != null && s2.toLowerCase(Locale.ROOT).startsWith("text/"))
                {
                    if(s2.equalsIgnoreCase("text/html"))
                    {
                        s = ".html";
                        s4 = s3;
                    } else
                    {
                        s = ".txt";
                        s4 = s3;
                    }
                } else
                {
                    s = ".bin";
                    s4 = s3;
                }
        } else
        {
            s = s4;
            if(s2 != null)
            {
                int i1 = s3.lastIndexOf('.');
                s1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s3.substring(i1 + 1));
                s = s4;
                if(s1 != null)
                {
                    s = s4;
                    if(s1.equalsIgnoreCase(s2) ^ true)
                    {
                        s1 = MimeTypeMap.getSingleton().getExtensionFromMimeType(s2);
                        s = s1;
                        if(s1 != null)
                            s = (new StringBuilder()).append(".").append(s1).toString();
                    }
                }
            }
            s1 = s;
            if(s == null)
                s1 = s3.substring(l);
            s4 = s3.substring(0, l);
            s = s1;
        }
        return (new StringBuilder()).append(s4).append(s).toString();
    }

    public static String guessUrl(String s)
    {
        if(s.length() == 0)
            return s;
        if(s.startsWith("about:"))
            return s;
        if(s.startsWith("data:"))
            return s;
        if(s.startsWith("file:"))
            return s;
        if(s.startsWith("javascript:"))
            return s;
        Object obj = s;
        if(s.endsWith("."))
            obj = s.substring(0, s.length() - 1);
        try
        {
            obj = new WebAddress(((String) (obj)));
        }
        catch(ParseException parseexception)
        {
            return s;
        }
        if(((WebAddress) (obj)).getHost().indexOf('.') == -1)
            ((WebAddress) (obj)).setHost((new StringBuilder()).append("www.").append(((WebAddress) (obj)).getHost()).append(".com").toString());
        return ((WebAddress) (obj)).toString();
    }

    public static boolean isAboutUrl(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.startsWith("about:");
        else
            flag = false;
        return flag;
    }

    public static boolean isAssetUrl(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.startsWith("file:///android_asset/");
        else
            flag = false;
        return flag;
    }

    public static boolean isContentUrl(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.startsWith("content:");
        else
            flag = false;
        return flag;
    }

    public static boolean isCookielessProxyUrl(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.startsWith("file:///cookieless_proxy/");
        else
            flag = false;
        return flag;
    }

    public static boolean isDataUrl(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.startsWith("data:");
        else
            flag = false;
        return flag;
    }

    public static boolean isFileUrl(String s)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(s != null)
        {
            flag1 = flag;
            if(s.startsWith("file://"))
            {
                flag1 = flag;
                if(s.startsWith("file:///android_asset/") ^ true)
                    flag1 = s.startsWith("file:///cookieless_proxy/") ^ true;
            }
        }
        return flag1;
    }

    public static boolean isHttpUrl(String s)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(s != null)
        {
            flag1 = flag;
            if(s.length() > 6)
                flag1 = s.substring(0, 7).equalsIgnoreCase("http://");
        }
        return flag1;
    }

    public static boolean isHttpsUrl(String s)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(s != null)
        {
            flag1 = flag;
            if(s.length() > 7)
                flag1 = s.substring(0, 8).equalsIgnoreCase("https://");
        }
        return flag1;
    }

    public static boolean isJavaScriptUrl(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.startsWith("javascript:");
        else
            flag = false;
        return flag;
    }

    public static boolean isNetworkUrl(String s)
    {
        if(s == null || s.length() == 0)
            return false;
        boolean flag;
        if(!isHttpUrl(s))
            flag = isHttpsUrl(s);
        else
            flag = true;
        return flag;
    }

    public static boolean isResourceUrl(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.startsWith("file:///android_res/");
        else
            flag = false;
        return flag;
    }

    public static boolean isValidUrl(String s)
    {
        if(s == null || s.length() == 0)
            return false;
        boolean flag;
        if(!isAssetUrl(s) && !isResourceUrl(s) && !isFileUrl(s) && !isAboutUrl(s) && !isHttpUrl(s) && !isHttpsUrl(s) && !isJavaScriptUrl(s))
            flag = isContentUrl(s);
        else
            flag = true;
        return flag;
    }

    static String parseContentDisposition(String s)
    {
        s = CONTENT_DISPOSITION_PATTERN.matcher(s);
        if(!s.find())
            break MISSING_BLOCK_LABEL_24;
        s = s.group(2);
        return s;
        s;
        return null;
    }

    private static int parseHex(byte byte0)
    {
        if(byte0 >= 48 && byte0 <= 57)
            return byte0 - 48;
        if(byte0 >= 65 && byte0 <= 70)
            return (byte0 - 65) + 10;
        if(byte0 >= 97 && byte0 <= 102)
            return (byte0 - 97) + 10;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid hex char '").append(byte0).append("'").toString());
    }

    public static String stripAnchor(String s)
    {
        int i = s.indexOf('#');
        if(i != -1)
            return s.substring(0, i);
        else
            return s;
    }

    static boolean verifyURLEncoding(String s)
    {
        int i;
        int j;
        i = s.length();
        if(i == 0)
            return false;
        j = s.indexOf('%');
_L2:
        if(j < 0 || j >= i)
            break MISSING_BLOCK_LABEL_78;
        if(j >= i - 2)
            break; /* Loop/switch isn't completed */
        j++;
        try
        {
            parseHex((byte)s.charAt(j));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        j++;
        parseHex((byte)s.charAt(j));
        j = s.indexOf('%', j + 1);
        if(true) goto _L2; else goto _L1
_L1:
        return false;
        return true;
    }

    static final String ASSET_BASE = "file:///android_asset/";
    static final String CONTENT_BASE = "content:";
    private static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*(\"?)([^\"]*)\\1\\s*$", 2);
    static final String FILE_BASE = "file://";
    private static final String LOGTAG = "webkit";
    static final String PROXY_BASE = "file:///cookieless_proxy/";
    static final String RESOURCE_BASE = "file:///android_res/";
    private static final boolean TRACE = false;

}
