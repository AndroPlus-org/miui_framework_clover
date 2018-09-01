// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.util.Arrays;

public final class Pin
{

    public Pin(String s, byte abyte0[])
    {
        digestAlgorithm = s;
        digest = abyte0;
        mHashCode = Arrays.hashCode(abyte0) ^ s.hashCode();
    }

    public static int getDigestLength(String s)
    {
        if("SHA-256".equalsIgnoreCase(s))
            return 32;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported digest algorithm: ").append(s).toString());
    }

    public static boolean isSupportedDigestAlgorithm(String s)
    {
        return "SHA-256".equalsIgnoreCase(s);
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof Pin))
            return false;
        obj = (Pin)obj;
        if(((Pin) (obj)).hashCode() != mHashCode)
            return false;
        if(!Arrays.equals(digest, ((Pin) (obj)).digest))
            return false;
        return digestAlgorithm.equals(((Pin) (obj)).digestAlgorithm);
    }

    public int hashCode()
    {
        return mHashCode;
    }

    public final byte digest[];
    public final String digestAlgorithm;
    private final int mHashCode;
}
