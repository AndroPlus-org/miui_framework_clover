// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.conn.ssl;

import java.io.IOException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.*;

// Referenced classes of package org.apache.http.conn.ssl:
//            X509HostnameVerifier, AndroidDistinguishedNameParser

public abstract class AbstractVerifier
    implements X509HostnameVerifier
{

    public AbstractVerifier()
    {
    }

    public static boolean acceptableCountryWildcard(String s)
    {
        boolean flag = true;
        int i = s.length();
        if(i >= 7 && i <= 9 && s.charAt(i - 3) == '.')
        {
            s = s.substring(2, i - 3);
            if(Arrays.binarySearch(BAD_COUNTRY_2LDS, s) >= 0)
                flag = false;
            return flag;
        } else
        {
            return true;
        }
    }

    public static int countDots(String s)
    {
        int i = 0;
        for(int j = 0; j < s.length();)
        {
            int k = i;
            if(s.charAt(j) == '.')
                k = i + 1;
            j++;
            i = k;
        }

        return i;
    }

    public static String[] getCNs(X509Certificate x509certificate)
    {
        List list = (new AndroidDistinguishedNameParser(x509certificate.getSubjectX500Principal())).getAllMostSpecificFirst("cn");
        if(!list.isEmpty())
        {
            x509certificate = new String[list.size()];
            list.toArray(x509certificate);
            return x509certificate;
        } else
        {
            return null;
        }
    }

    public static String[] getDNSSubjectAlts(X509Certificate x509certificate)
    {
        LinkedList linkedlist = new LinkedList();
        List list = null;
        try
        {
            x509certificate = x509certificate.getSubjectAlternativeNames();
        }
        // Misplaced declaration of an exception variable
        catch(X509Certificate x509certificate)
        {
            Logger.getLogger(org/apache/http/conn/ssl/AbstractVerifier.getName()).log(Level.FINE, "Error parsing certificate.", x509certificate);
            x509certificate = list;
        }
        if(x509certificate != null)
        {
            x509certificate = x509certificate.iterator();
            do
            {
                if(!x509certificate.hasNext())
                    break;
                list = (List)x509certificate.next();
                if(((Integer)list.get(0)).intValue() == 2)
                    linkedlist.add((String)list.get(1));
            } while(true);
        }
        if(!linkedlist.isEmpty())
        {
            x509certificate = new String[linkedlist.size()];
            linkedlist.toArray(x509certificate);
            return x509certificate;
        } else
        {
            return null;
        }
    }

    private static boolean isIPv4Address(String s)
    {
        return IPV4_PATTERN.matcher(s).matches();
    }

    public final void verify(String s, X509Certificate x509certificate)
        throws SSLException
    {
        verify(s, getCNs(x509certificate), getDNSSubjectAlts(x509certificate));
    }

    public final void verify(String s, SSLSocket sslsocket)
        throws IOException
    {
        if(s == null)
        {
            throw new NullPointerException("host to verify is null");
        } else
        {
            verify(s, (X509Certificate)sslsocket.getSession().getPeerCertificates()[0]);
            return;
        }
    }

    public final void verify(String s, String as[], String as1[], boolean flag)
        throws SSLException
    {
        Object obj = new LinkedList();
        if(as != null && as.length > 0 && as[0] != null)
            ((LinkedList) (obj)).add(as[0]);
        if(as1 != null)
        {
            int i = 0;
            for(int j = as1.length; i < j; i++)
            {
                as = as1[i];
                if(as != null)
                    ((LinkedList) (obj)).add(as);
            }

        }
        if(((LinkedList) (obj)).isEmpty())
            throw new SSLException((new StringBuilder()).append("Certificate for <").append(s).append("> doesn't contain CN or DNS subjectAlt").toString());
        as = new StringBuffer();
        as1 = s.trim().toLowerCase(Locale.ENGLISH);
        boolean flag2 = false;
        obj = ((LinkedList) (obj)).iterator();
        do
        {
            boolean flag3 = flag2;
            if(!((Iterator) (obj)).hasNext())
                break;
            String s1 = ((String)((Iterator) (obj)).next()).toLowerCase(Locale.ENGLISH);
            as.append(" <");
            as.append(s1);
            as.append('>');
            if(((Iterator) (obj)).hasNext())
                as.append(" OR");
            boolean flag1;
            if(s1.startsWith("*.") && s1.indexOf('.', 2) != -1 && acceptableCountryWildcard(s1))
                flag1 = isIPv4Address(s) ^ true;
            else
                flag1 = false;
            if(flag1)
            {
                flag2 = as1.endsWith(s1.substring(1));
                flag3 = flag2;
                if(flag2)
                {
                    flag3 = flag2;
                    if(flag)
                        if(countDots(as1) == countDots(s1))
                            flag3 = true;
                        else
                            flag3 = false;
                }
            } else
            {
                flag3 = as1.equals(s1);
            }
            flag2 = flag3;
        } while(!flag3);
        if(!flag3)
            throw new SSLException((new StringBuilder()).append("hostname in certificate didn't match: <").append(s).append("> !=").append(as).toString());
        else
            return;
    }

    public final boolean verify(String s, SSLSession sslsession)
    {
        try
        {
            verify(s, (X509Certificate)sslsession.getPeerCertificates()[0]);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return true;
    }

    private static final String BAD_COUNTRY_2LDS[] = {
        "ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", 
        "ne", "net", "or", "org"
    };
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    static 
    {
        Arrays.sort(BAD_COUNTRY_2LDS);
    }
}
