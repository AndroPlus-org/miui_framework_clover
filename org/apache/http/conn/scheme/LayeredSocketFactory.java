// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// Referenced classes of package org.apache.http.conn.scheme:
//            SocketFactory

public interface LayeredSocketFactory
    extends SocketFactory
{

    public abstract Socket createSocket(Socket socket, String s, int i, boolean flag)
        throws IOException, UnknownHostException;
}
