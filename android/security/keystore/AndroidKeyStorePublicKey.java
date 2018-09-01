// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.PublicKey;
import java.util.Arrays;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreKey, ArrayUtils

public class AndroidKeyStorePublicKey extends AndroidKeyStoreKey
    implements PublicKey
{

    public AndroidKeyStorePublicKey(String s, int i, String s1, byte abyte0[])
    {
        super(s, i, s1);
        mEncoded = ArrayUtils.cloneIfNotEmpty(abyte0);
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!super.equals(obj))
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AndroidKeyStorePublicKey)obj;
        return Arrays.equals(mEncoded, ((AndroidKeyStorePublicKey) (obj)).mEncoded);
    }

    public byte[] getEncoded()
    {
        return ArrayUtils.cloneIfNotEmpty(mEncoded);
    }

    public String getFormat()
    {
        return "X.509";
    }

    public int hashCode()
    {
        return super.hashCode() * 31 + Arrays.hashCode(mEncoded);
    }

    private final byte mEncoded[];
}
