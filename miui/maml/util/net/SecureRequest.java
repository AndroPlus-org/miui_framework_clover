// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util.net;

import android.util.Base64;
import java.io.IOException;
import java.util.*;
import javax.crypto.Cipher;

// Referenced classes of package miui.maml.util.net:
//            CipherException, InvalidResponseException, CloudCoder, AccessDeniedException, 
//            AuthenticationFailureException, SimpleRequest

public class SecureRequest
{

    public SecureRequest()
    {
    }

    private static String decryptResponse(String s, String s1)
        throws CipherException, InvalidResponseException
    {
        Cipher cipher = CloudCoder.newAESCipher(s1, 2);
        if(cipher == null)
            throw new CipherException("failed to init cipher");
        s1 = null;
        try
        {
            byte abyte0[] = cipher.doFinal(Base64.decode(s, 2));
            s = JVM INSTR new #46  <Class String>;
            s.String(abyte0, "utf-8");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s = s1;
        }
        if(s == null)
            throw new InvalidResponseException("failed to decrypt response");
        else
            return s;
    }

    public static Map encryptParams(String s, String s1, Map map, String s2)
        throws CipherException
    {
        Cipher cipher = CloudCoder.newAESCipher(s2, 1);
        if(cipher == null)
            throw new CipherException("failed to init cipher");
        HashMap hashmap = new HashMap();
        if(map != null && map.isEmpty() ^ true)
        {
            Iterator iterator = map.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                map = (java.util.Map.Entry)iterator.next();
                String s3 = (String)map.getKey();
                String s4 = (String)map.getValue();
                if(s3 != null && s4 != null)
                {
                    map = s4;
                    if(!s3.startsWith("_"))
                        try
                        {
                            map = Base64.encodeToString(cipher.doFinal(s4.getBytes("utf-8")), 2);
                        }
                        // Misplaced declaration of an exception variable
                        catch(String s)
                        {
                            throw new CipherException("failed to encrypt request params", s);
                        }
                    hashmap.put(s3, map);
                }
            } while(true);
        }
        hashmap.put("signature", CloudCoder.generateSignature(s, s1, hashmap, s2));
        return hashmap;
    }

    public static SimpleRequest.MapContent getAsMap(String s, Map map, Map map1, boolean flag, String s1)
        throws IOException, CipherException, AccessDeniedException, InvalidResponseException, AuthenticationFailureException
    {
        return SimpleRequest.convertStringToMap(getAsString(s, map, map1, flag, s1));
    }

    public static SimpleRequest.StringContent getAsString(String s, Map map, Map map1, boolean flag, String s1)
        throws IOException, CipherException, AccessDeniedException, InvalidResponseException, AuthenticationFailureException
    {
        return processStringResponse(SimpleRequest.getAsString(s, encryptParams("GET", s, map, s1), map1, flag), s1);
    }

    public static SimpleRequest.MapContent postAsMap(String s, Map map, Map map1, boolean flag, String s1)
        throws IOException, AccessDeniedException, InvalidResponseException, CipherException, AuthenticationFailureException
    {
        return SimpleRequest.convertStringToMap(postAsString(s, map, map1, flag, s1));
    }

    public static SimpleRequest.StringContent postAsString(String s, Map map, Map map1, boolean flag, String s1)
        throws IOException, CipherException, AccessDeniedException, InvalidResponseException, AuthenticationFailureException
    {
        return processStringResponse(SimpleRequest.postAsString(s, encryptParams("POST", s, map, s1), map1, flag), s1);
    }

    private static SimpleRequest.StringContent processStringResponse(SimpleRequest.StringContent stringcontent, String s)
        throws IOException, InvalidResponseException, CipherException
    {
        if(stringcontent == null)
            throw new IOException("no response from server");
        String s1 = stringcontent.getBody();
        if(s1 == null)
        {
            throw new InvalidResponseException("invalid response from server");
        } else
        {
            s = new SimpleRequest.StringContent(decryptResponse(s1, s));
            s.putHeaders(stringcontent.getHeaders());
            return s;
        }
    }

    private static final String UTF8 = "utf-8";
}
