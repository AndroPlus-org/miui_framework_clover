// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.os.Handler;

// Referenced classes of package android.webkit:
//            WebMessage

public abstract class WebMessagePort
{
    public static abstract class WebMessageCallback
    {

        public void onMessage(WebMessagePort webmessageport, WebMessage webmessage)
        {
        }

        public WebMessageCallback()
        {
        }
    }


    public WebMessagePort()
    {
    }

    public abstract void close();

    public abstract void postMessage(WebMessage webmessage);

    public abstract void setWebMessageCallback(WebMessageCallback webmessagecallback);

    public abstract void setWebMessageCallback(WebMessageCallback webmessagecallback, Handler handler);
}
