// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.content.Context;

public final class KeyStoreParameter
    implements java.security.KeyStore.ProtectionParameter
{
    public static final class Builder
    {

        public KeyStoreParameter build()
        {
            return new KeyStoreParameter(mFlags, null);
        }

        public Builder setEncryptionRequired(boolean flag)
        {
            if(flag)
                mFlags = mFlags | 1;
            else
                mFlags = mFlags & -2;
            return this;
        }

        private int mFlags;

        public Builder(Context context)
        {
            if(context == null)
                throw new NullPointerException("context == null");
            else
                return;
        }
    }


    private KeyStoreParameter(int i)
    {
        mFlags = i;
    }

    KeyStoreParameter(int i, KeyStoreParameter keystoreparameter)
    {
        this(i);
    }

    public int getFlags()
    {
        return mFlags;
    }

    public boolean isEncryptionRequired()
    {
        boolean flag = false;
        if((mFlags & 1) != 0)
            flag = true;
        return flag;
    }

    private final int mFlags;
}
