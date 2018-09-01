// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtils
{

    private StringUtils()
    {
    }

    public static String encodeBase64(String s)
    {
        Object obj = null;
        try
        {
            s = s.getBytes("ISO-8859-1");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            s = obj;
        }
        return encodeBase64(((byte []) (s)));
    }

    public static String encodeBase64(byte abyte0[])
    {
        return encodeBase64(abyte0, false);
    }

    public static String encodeBase64(byte abyte0[], int i, int j, boolean flag)
    {
        int k;
        if(flag)
            k = 0;
        else
            k = 2;
        return Base64.encodeToString(abyte0, i, j, k);
    }

    public static String encodeBase64(byte abyte0[], boolean flag)
    {
        return encodeBase64(abyte0, 0, abyte0.length, flag);
    }

    public static String escapeForXML(String s)
    {
        if(s == null)
            return null;
        int i = 0;
        int j = 0;
        char ac[] = s.toCharArray();
        int k = ac.length;
        StringBuilder stringbuilder = new StringBuilder((int)((double)k * 1.3D));
        while(i < k) 
        {
            char c = ac[i];
            int l;
            if(c > '>')
                l = j;
            else
            if(c == '<')
            {
                if(i > j)
                    stringbuilder.append(ac, j, i - j);
                l = i + 1;
                stringbuilder.append(LT_ENCODE);
            } else
            if(c == '>')
            {
                if(i > j)
                    stringbuilder.append(ac, j, i - j);
                l = i + 1;
                stringbuilder.append(GT_ENCODE);
            } else
            if(c == '&')
            {
                if(i > j)
                    stringbuilder.append(ac, j, i - j);
                if(k > i + 5 && ac[i + 1] == '#' && Character.isDigit(ac[i + 2]) && Character.isDigit(ac[i + 3]) && Character.isDigit(ac[i + 4]))
                {
                    if(ac[i + 5] == ';')
                        c = '\001';
                    else
                        c = '\0';
                } else
                {
                    c = '\0';
                }
                l = j;
                if(c == 0)
                {
                    l = i + 1;
                    stringbuilder.append(AMP_ENCODE);
                }
            } else
            if(c == '"')
            {
                if(i > j)
                    stringbuilder.append(ac, j, i - j);
                l = i + 1;
                stringbuilder.append(QUOTE_ENCODE);
            } else
            {
                l = j;
                if(c == '\'')
                {
                    if(i > j)
                        stringbuilder.append(ac, j, i - j);
                    l = i + 1;
                    stringbuilder.append(APOS_ENCODE);
                }
            }
            i++;
            j = l;
        }
        if(j == 0)
            return s;
        if(i > j)
            stringbuilder.append(ac, j, i - j);
        return stringbuilder.toString();
    }

    public static byte[] getBytes(String s)
    {
        byte abyte0[];
        try
        {
            abyte0 = s.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            return s.getBytes();
        }
        return abyte0;
    }

    public static String getMd5Digest(String s)
    {
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(getBytes(s));
            s = JVM INSTR new #130 <Class BigInteger>;
            s.BigInteger(1, messagedigest.digest());
            s = String.format("%1$032X", new Object[] {
                s
            });
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
        return s;
    }

    public static boolean isValidXmlChar(char c)
    {
        boolean flag = true;
        if(c < ' ' || c > '\uD7FF') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        if(c >= '\uE000')
        {
            flag1 = flag;
            if(c <= '\uFFFD')
                continue; /* Loop/switch isn't completed */
        }
        if(c >= '\0')
        {
            flag1 = flag;
            if(c <= '\0')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = flag;
        if(c != '\t')
        {
            flag1 = flag;
            if(c != '\n')
            {
                flag1 = flag;
                if(c != '\r')
                    flag1 = false;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String randomString(int i)
    {
        if(i < 1)
            return null;
        char ac[] = new char[i];
        for(i = 0; i < ac.length; i++)
            ac[i] = numbersAndLetters[randGen.nextInt(71)];

        return new String(ac);
    }

    public static final String replace(String s, String s1, String s2)
    {
        if(s == null)
            return null;
        int i = s.indexOf(s1, 0);
        if(i >= 0)
        {
            char ac[] = s.toCharArray();
            char ac1[] = s2.toCharArray();
            int j = s1.length();
            s2 = new StringBuilder(ac.length);
            s2.append(ac, 0, i).append(ac1);
            int k = i + j;
            i = k;
            do
            {
                k = s.indexOf(s1, k);
                if(k > 0)
                {
                    s2.append(ac, i, k - i).append(ac1);
                    k += j;
                    i = k;
                } else
                {
                    s2.append(ac, i, ac.length - i);
                    return s2.toString();
                }
            } while(true);
        } else
        {
            return s;
        }
    }

    public static String stripInvalidXMLChars(String s)
    {
        if(TextUtils.isEmpty(s))
            return s;
        StringBuilder stringbuilder = new StringBuilder(s.length());
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(isValidXmlChar(c))
                stringbuilder.append(c);
        }

        return stringbuilder.toString();
    }

    public static final String unescapeFromXML(String s)
    {
        return replace(replace(replace(replace(replace(s, "&lt;", "<"), "&gt;", ">"), "&quot;", "\""), "&apos;", "'"), "&amp;", "&");
    }

    private static final char AMP_ENCODE[] = "&amp;".toCharArray();
    private static final char APOS_ENCODE[] = "&apos;".toCharArray();
    private static final char GT_ENCODE[] = "&gt;".toCharArray();
    private static final char LT_ENCODE[] = "&lt;".toCharArray();
    private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();
    private static char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Random randGen = new Random();

}
