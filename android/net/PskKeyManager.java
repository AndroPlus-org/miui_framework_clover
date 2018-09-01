// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import com.android.org.conscrypt.PSKKeyManager;
import java.net.Socket;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLEngine;

public abstract class PskKeyManager
    implements PSKKeyManager
{

    public PskKeyManager()
    {
    }

    public String chooseClientKeyIdentity(String s, Socket socket)
    {
        return "";
    }

    public String chooseClientKeyIdentity(String s, SSLEngine sslengine)
    {
        return "";
    }

    public String chooseServerKeyIdentityHint(Socket socket)
    {
        return null;
    }

    public String chooseServerKeyIdentityHint(SSLEngine sslengine)
    {
        return null;
    }

    public SecretKey getKey(String s, String s1, Socket socket)
    {
        return null;
    }

    public SecretKey getKey(String s, String s1, SSLEngine sslengine)
    {
        return null;
    }

    public static final int MAX_IDENTITY_HINT_LENGTH_BYTES = 128;
    public static final int MAX_IDENTITY_LENGTH_BYTES = 128;
    public static final int MAX_KEY_LENGTH_BYTES = 256;
}
