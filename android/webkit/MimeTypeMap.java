// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.text.TextUtils;
import java.util.regex.Pattern;
import libcore.net.MimeUtils;

// Referenced classes of package android.webkit:
//            URLUtil

public class MimeTypeMap
{

    private MimeTypeMap()
    {
    }

    public static String getFileExtensionFromUrl(String s)
    {
        if(!TextUtils.isEmpty(s))
        {
            int i = s.lastIndexOf('#');
            String s1 = s;
            if(i > 0)
                s1 = s.substring(0, i);
            i = s1.lastIndexOf('?');
            s = s1;
            if(i > 0)
                s = s1.substring(0, i);
            i = s.lastIndexOf('/');
            if(i >= 0)
                s = s.substring(i + 1);
            if(!s.isEmpty() && Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", s))
            {
                int j = s.lastIndexOf('.');
                if(j >= 0)
                    return s.substring(j + 1);
            }
        }
        return "";
    }

    public static MimeTypeMap getSingleton()
    {
        return sMimeTypeMap;
    }

    private static String mimeTypeFromExtension(String s)
    {
        return MimeUtils.guessMimeTypeFromExtension(s);
    }

    public String getExtensionFromMimeType(String s)
    {
        return MimeUtils.guessExtensionFromMimeType(s);
    }

    public String getMimeTypeFromExtension(String s)
    {
        return MimeUtils.guessMimeTypeFromExtension(s);
    }

    public boolean hasExtension(String s)
    {
        return MimeUtils.hasExtension(s);
    }

    public boolean hasMimeType(String s)
    {
        return MimeUtils.hasMimeType(s);
    }

    String remapGenericMimeType(String s, String s1, String s2)
    {
        if(!"text/plain".equals(s) && !"application/octet-stream".equals(s)) goto _L2; else goto _L1
_L1:
        String s3 = null;
        if(s2 != null)
            s3 = URLUtil.parseContentDisposition(s2);
        if(s3 != null)
            s1 = s3;
        s2 = getMimeTypeFromExtension(getFileExtensionFromUrl(s1));
        s1 = s;
        if(s2 != null)
            s1 = s2;
_L4:
        return s1;
_L2:
        if("text/vnd.wap.wml".equals(s))
        {
            s1 = "text/plain";
        } else
        {
            s1 = s;
            if("application/vnd.wap.xhtml+xml".equals(s))
                s1 = "application/xhtml+xml";
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final MimeTypeMap sMimeTypeMap = new MimeTypeMap();

}
