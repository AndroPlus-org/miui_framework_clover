// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import org.egret.plugin.mi.runtime.EgretLoader;

// Referenced classes of package android.webkit:
//            WebView

class WebViewInjector
{

    WebViewInjector()
    {
    }

    static void initEgretLoader(WebView webview, Context context)
    {
        context = new EgretLoader(context);
        if(!context.checkEgretContext())
            webview.addJavascriptInterface(context, "GameEngine");
    }
}
