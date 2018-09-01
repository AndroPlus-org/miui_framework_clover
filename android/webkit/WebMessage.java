// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


// Referenced classes of package android.webkit:
//            WebMessagePort

public class WebMessage
{

    public WebMessage(String s)
    {
        mData = s;
    }

    public WebMessage(String s, WebMessagePort awebmessageport[])
    {
        mData = s;
        mPorts = awebmessageport;
    }

    public String getData()
    {
        return mData;
    }

    public WebMessagePort[] getPorts()
    {
        return mPorts;
    }

    private String mData;
    private WebMessagePort mPorts[];
}
