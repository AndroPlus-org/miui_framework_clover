// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import java.util.*;

// Referenced classes of package android.net:
//            ParseException, Uri

public class MailTo
{

    private MailTo()
    {
        mHeaders = new HashMap();
    }

    public static boolean isMailTo(String s)
    {
        return s != null && s.startsWith("mailto:");
    }

    public static MailTo parse(String s)
        throws ParseException
    {
        if(s == null)
            throw new NullPointerException();
        if(!isMailTo(s))
            throw new ParseException("Not a mailto scheme");
        Object obj = Uri.parse(s.substring("mailto:".length()));
        MailTo mailto = new MailTo();
        s = ((Uri) (obj)).getQuery();
        if(s != null)
        {
            String as[] = s.split("&");
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                s = as[j].split("=");
                if(s.length != 0)
                {
                    HashMap hashmap = mailto.mHeaders;
                    String s1 = Uri.decode(s[0]).toLowerCase(Locale.ROOT);
                    if(s.length > 1)
                        s = Uri.decode(s[1]);
                    else
                        s = null;
                    hashmap.put(s1, s);
                }
                j++;
            }
        }
        obj = ((Uri) (obj)).getPath();
        if(obj != null)
        {
            String s2 = mailto.getTo();
            s = ((String) (obj));
            if(s2 != null)
                s = (new StringBuilder()).append(((String) (obj))).append(", ").append(s2).toString();
            mailto.mHeaders.put("to", s);
        }
        return mailto;
    }

    public String getBody()
    {
        return (String)mHeaders.get("body");
    }

    public String getCc()
    {
        return (String)mHeaders.get("cc");
    }

    public Map getHeaders()
    {
        return mHeaders;
    }

    public String getSubject()
    {
        return (String)mHeaders.get("subject");
    }

    public String getTo()
    {
        return (String)mHeaders.get("to");
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("mailto:");
        stringbuilder.append('?');
        for(Iterator iterator = mHeaders.entrySet().iterator(); iterator.hasNext(); stringbuilder.append('&'))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            stringbuilder.append(Uri.encode((String)entry.getKey()));
            stringbuilder.append('=');
            stringbuilder.append(Uri.encode((String)entry.getValue()));
        }

        return stringbuilder.toString();
    }

    private static final String BODY = "body";
    private static final String CC = "cc";
    public static final String MAILTO_SCHEME = "mailto:";
    private static final String SUBJECT = "subject";
    private static final String TO = "to";
    private HashMap mHeaders;
}
