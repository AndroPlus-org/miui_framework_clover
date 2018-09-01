// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.view.*;

// Referenced classes of package android.webkit:
//            WebView, ClientCertRequest, WebResourceRequest, WebResourceError, 
//            HttpAuthHandler, SslErrorHandler, SafeBrowsingResponse, WebResourceResponse, 
//            RenderProcessGoneDetail

public class WebViewClient
{

    public WebViewClient()
    {
    }

    private void onUnhandledInputEventInternal(WebView webview, InputEvent inputevent)
    {
        webview = webview.getViewRootImpl();
        if(webview != null)
            webview.dispatchUnhandledInputEvent(inputevent);
    }

    public void doUpdateVisitedHistory(WebView webview, String s, boolean flag)
    {
    }

    public void onFormResubmission(WebView webview, Message message, Message message1)
    {
        message.sendToTarget();
    }

    public void onLoadResource(WebView webview, String s)
    {
    }

    public void onPageCommitVisible(WebView webview, String s)
    {
    }

    public void onPageFinished(WebView webview, String s)
    {
    }

    public void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
    }

    public void onReceivedClientCertRequest(WebView webview, ClientCertRequest clientcertrequest)
    {
        clientcertrequest.cancel();
    }

    public void onReceivedError(WebView webview, int i, String s, String s1)
    {
    }

    public void onReceivedError(WebView webview, WebResourceRequest webresourcerequest, WebResourceError webresourceerror)
    {
        if(webresourcerequest.isForMainFrame())
            onReceivedError(webview, webresourceerror.getErrorCode(), webresourceerror.getDescription().toString(), webresourcerequest.getUrl().toString());
    }

    public void onReceivedHttpAuthRequest(WebView webview, HttpAuthHandler httpauthhandler, String s, String s1)
    {
        httpauthhandler.cancel();
    }

    public void onReceivedHttpError(WebView webview, WebResourceRequest webresourcerequest, WebResourceResponse webresourceresponse)
    {
    }

    public void onReceivedLoginRequest(WebView webview, String s, String s1, String s2)
    {
    }

    public void onReceivedSslError(WebView webview, SslErrorHandler sslerrorhandler, SslError sslerror)
    {
        sslerrorhandler.cancel();
    }

    public boolean onRenderProcessGone(WebView webview, RenderProcessGoneDetail renderprocessgonedetail)
    {
        return false;
    }

    public void onSafeBrowsingHit(WebView webview, WebResourceRequest webresourcerequest, int i, SafeBrowsingResponse safebrowsingresponse)
    {
        safebrowsingresponse.showInterstitial(true);
    }

    public void onScaleChanged(WebView webview, float f, float f1)
    {
    }

    public void onTooManyRedirects(WebView webview, Message message, Message message1)
    {
        message.sendToTarget();
    }

    public void onUnhandledInputEvent(WebView webview, InputEvent inputevent)
    {
        if(inputevent instanceof KeyEvent)
        {
            onUnhandledKeyEvent(webview, (KeyEvent)inputevent);
            return;
        } else
        {
            onUnhandledInputEventInternal(webview, inputevent);
            return;
        }
    }

    public void onUnhandledKeyEvent(WebView webview, KeyEvent keyevent)
    {
        onUnhandledInputEventInternal(webview, keyevent);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webview, WebResourceRequest webresourcerequest)
    {
        return shouldInterceptRequest(webview, webresourcerequest.getUrl().toString());
    }

    public WebResourceResponse shouldInterceptRequest(WebView webview, String s)
    {
        return null;
    }

    public boolean shouldOverrideKeyEvent(WebView webview, KeyEvent keyevent)
    {
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView webview, WebResourceRequest webresourcerequest)
    {
        return shouldOverrideUrlLoading(webview, webresourcerequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        return false;
    }

    public static final int ERROR_AUTHENTICATION = -4;
    public static final int ERROR_BAD_URL = -12;
    public static final int ERROR_CONNECT = -6;
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    public static final int ERROR_FILE = -13;
    public static final int ERROR_FILE_NOT_FOUND = -14;
    public static final int ERROR_HOST_LOOKUP = -2;
    public static final int ERROR_IO = -7;
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    public static final int ERROR_REDIRECT_LOOP = -9;
    public static final int ERROR_TIMEOUT = -8;
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    public static final int ERROR_UNKNOWN = -1;
    public static final int ERROR_UNSAFE_RESOURCE = -16;
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;
    public static final int SAFE_BROWSING_THREAT_MALWARE = 1;
    public static final int SAFE_BROWSING_THREAT_PHISHING = 2;
    public static final int SAFE_BROWSING_THREAT_UNKNOWN = 0;
    public static final int SAFE_BROWSING_THREAT_UNWANTED_SOFTWARE = 3;
}
