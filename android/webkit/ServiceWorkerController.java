// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


// Referenced classes of package android.webkit:
//            WebViewFactory, WebViewFactoryProvider, ServiceWorkerWebSettings, ServiceWorkerClient

public abstract class ServiceWorkerController
{

    public ServiceWorkerController()
    {
    }

    public static ServiceWorkerController getInstance()
    {
        return WebViewFactory.getProvider().getServiceWorkerController();
    }

    public abstract ServiceWorkerWebSettings getServiceWorkerWebSettings();

    public abstract void setServiceWorkerClient(ServiceWorkerClient serviceworkerclient);
}
