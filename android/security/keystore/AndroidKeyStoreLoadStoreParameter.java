// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;


class AndroidKeyStoreLoadStoreParameter
    implements java.security.KeyStore.LoadStoreParameter
{

    AndroidKeyStoreLoadStoreParameter(int i)
    {
        mUid = i;
    }

    public java.security.KeyStore.ProtectionParameter getProtectionParameter()
    {
        return null;
    }

    int getUid()
    {
        return mUid;
    }

    private final int mUid;
}
