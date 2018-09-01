// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.Key;

public class AndroidKeyStoreKey
    implements Key
{

    public AndroidKeyStoreKey(String s, int i, String s1)
    {
        mAlias = s;
        mUid = i;
        mAlgorithm = s1;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AndroidKeyStoreKey)obj;
        if(mAlgorithm == null)
        {
            if(((AndroidKeyStoreKey) (obj)).mAlgorithm != null)
                return false;
        } else
        if(!mAlgorithm.equals(((AndroidKeyStoreKey) (obj)).mAlgorithm))
            return false;
        if(mAlias == null)
        {
            if(((AndroidKeyStoreKey) (obj)).mAlias != null)
                return false;
        } else
        if(!mAlias.equals(((AndroidKeyStoreKey) (obj)).mAlias))
            return false;
        return mUid == ((AndroidKeyStoreKey) (obj)).mUid;
    }

    public String getAlgorithm()
    {
        return mAlgorithm;
    }

    String getAlias()
    {
        return mAlias;
    }

    public byte[] getEncoded()
    {
        return null;
    }

    public String getFormat()
    {
        return null;
    }

    int getUid()
    {
        return mUid;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        if(mAlgorithm == null)
            j = 0;
        else
            j = mAlgorithm.hashCode();
        if(mAlias != null)
            i = mAlias.hashCode();
        return ((j + 31) * 31 + i) * 31 + mUid;
    }

    private final String mAlgorithm;
    private final String mAlias;
    private final int mUid;
}
