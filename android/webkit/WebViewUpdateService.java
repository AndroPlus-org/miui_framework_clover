// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.os.RemoteException;

// Referenced classes of package android.webkit:
//            IWebViewUpdateService, WebViewFactory, WebViewProviderInfo

public final class WebViewUpdateService
{

    private WebViewUpdateService()
    {
    }

    public static WebViewProviderInfo[] getAllWebViewPackages()
    {
        WebViewProviderInfo awebviewproviderinfo[];
        try
        {
            awebviewproviderinfo = getUpdateService().getAllWebViewPackages();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return awebviewproviderinfo;
    }

    public static String getCurrentWebViewPackageName()
    {
        String s;
        try
        {
            s = getUpdateService().getCurrentWebViewPackageName();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    private static IWebViewUpdateService getUpdateService()
    {
        return WebViewFactory.getUpdateService();
    }

    public static WebViewProviderInfo[] getValidWebViewPackages()
    {
        WebViewProviderInfo awebviewproviderinfo[];
        try
        {
            awebviewproviderinfo = getUpdateService().getValidWebViewPackages();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return awebviewproviderinfo;
    }
}
