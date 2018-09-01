// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;

// Referenced classes of package android.webkit:
//            WebViewFactory, WebViewFactoryProvider

public abstract class WebViewDatabase
{

    public WebViewDatabase()
    {
    }

    public static WebViewDatabase getInstance(Context context)
    {
        return WebViewFactory.getProvider().getWebViewDatabase(context);
    }

    public abstract void clearFormData();

    public abstract void clearHttpAuthUsernamePassword();

    public abstract void clearUsernamePassword();

    public abstract String[] getHttpAuthUsernamePassword(String s, String s1);

    public abstract boolean hasFormData();

    public abstract boolean hasHttpAuthUsernamePassword();

    public abstract boolean hasUsernamePassword();

    public abstract void setHttpAuthUsernamePassword(String s, String s1, String s2, String s3);

    protected static final String LOGTAG = "webviewdatabase";
}
