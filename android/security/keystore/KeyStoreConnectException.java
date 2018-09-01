// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.security.ProviderException;

public class KeyStoreConnectException extends ProviderException
{

    public KeyStoreConnectException()
    {
        super("Failed to communicate with keystore service");
    }
}
