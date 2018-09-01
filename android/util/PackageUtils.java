// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.content.pm.Signature;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

// Referenced classes of package android.util:
//            ByteStringUtils

public final class PackageUtils
{

    private PackageUtils()
    {
    }

    public static String computeSha256Digest(byte abyte0[])
    {
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("SHA256");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            return null;
        }
        messagedigest.update(abyte0);
        return ByteStringUtils.toHexString(messagedigest.digest());
    }

    public static String computeSignaturesSha256Digest(Signature asignature[])
    {
        if(asignature.length == 1)
            return computeSha256Digest(asignature[0].toByteArray());
        else
            return computeSignaturesSha256Digest(computeSignaturesSha256Digests(asignature));
    }

    public static String computeSignaturesSha256Digest(String as[])
    {
        int i = 0;
        if(as.length == 1)
            return as[0];
        Arrays.sort(as);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        int j = as.length;
        while(i < j) 
        {
            String s = as[i];
            try
            {
                bytearrayoutputstream.write(s.getBytes());
            }
            catch(IOException ioexception) { }
            i++;
        }
        return computeSha256Digest(bytearrayoutputstream.toByteArray());
    }

    public static String[] computeSignaturesSha256Digests(Signature asignature[])
    {
        int i = asignature.length;
        String as[] = new String[i];
        for(int j = 0; j < i; j++)
            as[j] = computeSha256Digest(asignature[j].toByteArray());

        return as;
    }
}
