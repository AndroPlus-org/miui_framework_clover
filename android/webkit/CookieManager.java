// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.net.WebAddress;

// Referenced classes of package android.webkit:
//            WebViewFactory, WebViewFactoryProvider, WebView, ValueCallback

public abstract class CookieManager
{

    public CookieManager()
    {
    }

    public static boolean allowFileSchemeCookies()
    {
        return getInstance().allowFileSchemeCookiesImpl();
    }

    public static CookieManager getInstance()
    {
        return WebViewFactory.getProvider().getCookieManager();
    }

    public static void setAcceptFileSchemeCookies(boolean flag)
    {
        getInstance().setAcceptFileSchemeCookiesImpl(flag);
    }

    public abstract boolean acceptCookie();

    public abstract boolean acceptThirdPartyCookies(WebView webview);

    protected abstract boolean allowFileSchemeCookiesImpl();

    protected Object clone()
        throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException("doesn't implement Cloneable");
    }

    public abstract void flush();

    public String getCookie(WebAddress webaddress)
    {
        this;
        JVM INSTR monitorenter ;
        webaddress = getCookie(webaddress.toString());
        this;
        JVM INSTR monitorexit ;
        return webaddress;
        webaddress;
        throw webaddress;
    }

    public abstract String getCookie(String s);

    public abstract String getCookie(String s, boolean flag);

    public abstract boolean hasCookies();

    public abstract boolean hasCookies(boolean flag);

    public abstract void removeAllCookie();

    public abstract void removeAllCookies(ValueCallback valuecallback);

    public abstract void removeExpiredCookie();

    public abstract void removeSessionCookie();

    public abstract void removeSessionCookies(ValueCallback valuecallback);

    public abstract void setAcceptCookie(boolean flag);

    protected abstract void setAcceptFileSchemeCookiesImpl(boolean flag);

    public abstract void setAcceptThirdPartyCookies(WebView webview, boolean flag);

    public abstract void setCookie(String s, String s1);

    public abstract void setCookie(String s, String s1, ValueCallback valuecallback);
}
