// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.net.Uri;
import java.security.KeyPair;

// Referenced classes of package android.webkit:
//            WebViewFactory, WebViewFactoryProvider, ValueCallback

public abstract class TokenBindingService
{
    public static abstract class TokenBindingKey
    {

        public abstract String getAlgorithm();

        public abstract KeyPair getKeyPair();

        public TokenBindingKey()
        {
        }
    }


    public TokenBindingService()
    {
    }

    public static TokenBindingService getInstance()
    {
        return WebViewFactory.getProvider().getTokenBindingService();
    }

    public abstract void deleteAllKeys(ValueCallback valuecallback);

    public abstract void deleteKey(Uri uri, ValueCallback valuecallback);

    public abstract void enableTokenBinding();

    public abstract void getKey(Uri uri, String as[], ValueCallback valuecallback);

    public static final String KEY_ALGORITHM_ECDSAP256 = "ECDSAP256";
    public static final String KEY_ALGORITHM_RSA2048_PKCS_1_5 = "RSA2048_PKCS_1.5";
    public static final String KEY_ALGORITHM_RSA2048_PSS = "RSA2048PSS";
}
