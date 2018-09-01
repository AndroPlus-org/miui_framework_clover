// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.ContentResolver;
import android.graphics.Bitmap;

// Referenced classes of package android.webkit:
//            WebViewFactory, WebViewFactoryProvider

public abstract class WebIconDatabase
{
    public static interface IconListener
    {

        public abstract void onReceivedIcon(String s, Bitmap bitmap);
    }


    public WebIconDatabase()
    {
    }

    public static WebIconDatabase getInstance()
    {
        return WebViewFactory.getProvider().getWebIconDatabase();
    }

    public abstract void bulkRequestIconForPageUrl(ContentResolver contentresolver, String s, IconListener iconlistener);

    public abstract void close();

    public abstract void open(String s);

    public abstract void releaseIconForPageUrl(String s);

    public abstract void removeAllIcons();

    public abstract void requestIconForPageUrl(String s, IconListener iconlistener);

    public abstract void retainIconForPageUrl(String s);
}
