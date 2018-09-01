// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;


public class WifiAwareUtils
{

    public WifiAwareUtils()
    {
    }

    public static boolean validatePassphrase(String s)
    {
        while(s == null || s.length() < 8 || s.length() > 63) 
            return false;
        return true;
    }

    public static boolean validatePmk(byte abyte0[])
    {
        return abyte0 != null && abyte0.length == 32;
    }

    public static void validateServiceName(byte abyte0[])
        throws IllegalArgumentException
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("Invalid service name - null");
        if(abyte0.length < 1 || abyte0.length > 255)
            throw new IllegalArgumentException("Invalid service name length - must be between 1 and 255 bytes (UTF-8 encoding)");
        for(int i = 0; i < abyte0.length; i++)
        {
            byte byte0 = abyte0[i];
            if((byte0 & 0x80) == 0 && (byte0 < 48 || byte0 > 57) && (byte0 < 97 || byte0 > 122) && (byte0 < 65 || byte0 > 90) && byte0 != 45 && byte0 != 46)
                throw new IllegalArgumentException("Invalid service name - illegal characters, allowed = (0-9, a-z,A-Z, -, .)");
        }

    }
}
