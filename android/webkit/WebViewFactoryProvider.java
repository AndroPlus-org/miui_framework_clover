// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.List;

// Referenced classes of package android.webkit:
//            WebView, WebViewProvider, CookieManager, GeolocationPermissions, 
//            ServiceWorkerController, TokenBindingService, WebIconDatabase, WebStorage, 
//            WebViewDatabase, ValueCallback

public interface WebViewFactoryProvider
{
    public static interface Statics
    {

        public abstract void clearClientCertPreferences(Runnable runnable);

        public abstract void enableSlowWholeDocumentDraw();

        public abstract String findAddress(String s);

        public abstract void freeMemoryForTests();

        public abstract String getDefaultUserAgent(Context context);

        public abstract Uri getSafeBrowsingPrivacyPolicyUrl();

        public abstract void initSafeBrowsing(Context context, ValueCallback valuecallback);

        public abstract Uri[] parseFileChooserResult(int i, Intent intent);

        public abstract void setSafeBrowsingWhitelist(List list, ValueCallback valuecallback);

        public abstract void setWebContentsDebuggingEnabled(boolean flag);
    }


    public abstract WebViewProvider createWebView(WebView webview, WebView.PrivateAccess privateaccess);

    public abstract CookieManager getCookieManager();

    public abstract GeolocationPermissions getGeolocationPermissions();

    public abstract ServiceWorkerController getServiceWorkerController();

    public abstract Statics getStatics();

    public abstract TokenBindingService getTokenBindingService();

    public abstract WebIconDatabase getWebIconDatabase();

    public abstract WebStorage getWebStorage();

    public abstract WebViewDatabase getWebViewDatabase(Context context);
}
