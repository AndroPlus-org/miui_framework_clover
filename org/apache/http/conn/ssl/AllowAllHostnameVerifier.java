// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.conn.ssl;


// Referenced classes of package org.apache.http.conn.ssl:
//            AbstractVerifier

public class AllowAllHostnameVerifier extends AbstractVerifier
{

    public AllowAllHostnameVerifier()
    {
    }

    public final String toString()
    {
        return "ALLOW_ALL";
    }

    public final void verify(String s, String as[], String as1[])
    {
    }
}
