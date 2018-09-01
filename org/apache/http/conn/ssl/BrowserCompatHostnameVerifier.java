// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.conn.ssl;

import javax.net.ssl.SSLException;

// Referenced classes of package org.apache.http.conn.ssl:
//            AbstractVerifier

public class BrowserCompatHostnameVerifier extends AbstractVerifier
{

    public BrowserCompatHostnameVerifier()
    {
    }

    public final String toString()
    {
        return "BROWSER_COMPATIBLE";
    }

    public final void verify(String s, String as[], String as1[])
        throws SSLException
    {
        verify(s, as, as1, false);
    }
}
