// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.KeyStoreException;

interface KeyStoreCryptoOperationStreamer
{

    public abstract byte[] doFinal(byte abyte0[], int i, int j, byte abyte1[], byte abyte2[])
        throws KeyStoreException;

    public abstract long getConsumedInputSizeBytes();

    public abstract long getProducedOutputSizeBytes();

    public abstract byte[] update(byte abyte0[], int i, int j)
        throws KeyStoreException;
}
