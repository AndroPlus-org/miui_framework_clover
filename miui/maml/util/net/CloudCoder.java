// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util.net;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CloudCoder
{

    public CloudCoder()
    {
    }

    public static String generateSignature(String s, String s1, Map map, String s2)
    {
        if(TextUtils.isEmpty(s2))
            throw new InvalidParameterException("security is not nullable");
        ArrayList arraylist = new ArrayList();
        if(s != null)
            arraylist.add(s.toUpperCase());
        if(s1 != null)
            arraylist.add(Uri.parse(s1).getEncodedPath());
        if(map != null && map.isEmpty() ^ true)
            for(s = (new TreeMap(map)).entrySet().iterator(); s.hasNext(); arraylist.add(String.format("%s=%s", new Object[] {
    s1.getKey(), s1.getValue()
})))
                s1 = (java.util.Map.Entry)s.next();

        arraylist.add(s2);
        boolean flag = true;
        s = new StringBuilder();
        for(map = arraylist.iterator(); map.hasNext();)
        {
            s1 = (String)map.next();
            if(!flag)
                s.append('&');
            s.append(s1);
            flag = false;
        }

        return hash4SHA1(s.toString());
    }

    public static String hash4SHA1(String s)
    {
        s = Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(s.getBytes("UTF-8")), 2);
        return s;
        s;
        s.printStackTrace();
_L2:
        throw new IllegalStateException("failed to SHA1");
        s;
        s.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Cipher newAESCipher(String s, int i)
    {
        SecretKeySpec secretkeyspec = new SecretKeySpec(Base64.decode(s, 2), "AES");
        s = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivparameterspec = JVM INSTR new #190 <Class IvParameterSpec>;
        ivparameterspec.IvParameterSpec("0102030405060708".getBytes());
        s.init(i, secretkeyspec, ivparameterspec);
        return s;
        s;
        s.printStackTrace();
_L2:
        return null;
        s;
        s.printStackTrace();
        continue; /* Loop/switch isn't completed */
        s;
        s.printStackTrace();
        continue; /* Loop/switch isn't completed */
        s;
        s.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final Integer INT_0 = Integer.valueOf(0);
    private static final String RC4_ALGORITHM_NAME = "RC4";

}
