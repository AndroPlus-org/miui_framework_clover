// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.os.Environment;
import android.os.FileUtils;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import libcore.io.IoUtils;

public class SystemKeyStore
{

    private SystemKeyStore()
    {
    }

    public static SystemKeyStore getInstance()
    {
        return mInstance;
    }

    private File getKeyFile(String s)
    {
        return new File(new File(Environment.getDataDirectory(), "misc/systemkeys"), (new StringBuilder()).append(s).append(".sks").toString());
    }

    public static String toHexString(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        int i = abyte0.length;
        StringBuilder stringbuilder = new StringBuilder(abyte0.length * 2);
        for(int j = 0; j < abyte0.length; j++)
        {
            String s = Integer.toString(abyte0[j] & 0xff, 16);
            String s1 = s;
            if(s.length() == 1)
                s1 = (new StringBuilder()).append("0").append(s).toString();
            stringbuilder.append(s1);
        }

        return stringbuilder.toString();
    }

    public void deleteKey(String s)
    {
        s = getKeyFile(s);
        if(!s.exists())
        {
            throw new IllegalArgumentException();
        } else
        {
            s.delete();
            return;
        }
    }

    public byte[] generateNewKey(int i, String s, String s1)
        throws NoSuchAlgorithmException
    {
        byte abyte0[];
        s1 = getKeyFile(s1);
        if(s1.exists())
            throw new IllegalArgumentException();
        s = KeyGenerator.getInstance(s);
        s.init(i, SecureRandom.getInstance("SHA1PRNG"));
        abyte0 = s.generateKey().getEncoded();
        try
        {
            if(!s1.createNewFile())
            {
                s = JVM INSTR new #76  <Class IllegalArgumentException>;
                s.IllegalArgumentException();
                throw s;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        s = JVM INSTR new #117 <Class FileOutputStream>;
        s.FileOutputStream(s1);
        s.write(abyte0);
        s.flush();
        FileUtils.sync(s);
        s.close();
        FileUtils.setPermissions(s1.getName(), 384, -1, -1);
        return abyte0;
    }

    public String generateNewKeyHexString(int i, String s, String s1)
        throws NoSuchAlgorithmException
    {
        return toHexString(generateNewKey(i, s, s1));
    }

    public byte[] retrieveKey(String s)
        throws IOException
    {
        s = getKeyFile(s);
        if(!s.exists())
            return null;
        else
            return IoUtils.readFileAsByteArray(s.toString());
    }

    public String retrieveKeyHexString(String s)
        throws IOException
    {
        return toHexString(retrieveKey(s));
    }

    private static final String KEY_FILE_EXTENSION = ".sks";
    private static final String SYSTEM_KEYSTORE_DIRECTORY = "misc/systemkeys";
    private static SystemKeyStore mInstance = new SystemKeyStore();

}
