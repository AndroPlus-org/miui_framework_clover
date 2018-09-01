// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.app.ApplicationLoaders;
import android.net.LocalSocket;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebViewFactory;
import java.io.*;
import java.lang.reflect.Method;

// Referenced classes of package com.android.internal.os:
//            ZygoteServer, ZygoteConnection, Zygote

class WebViewZygoteInit
{
    private static class WebViewZygoteConnection extends ZygoteConnection
    {

        protected void handlePreloadPackage(String s, String s1, String s2)
        {
            boolean flag;
            boolean flag1;
            boolean flag3;
            Log.i("WebViewZygoteInit", "Beginning package preload");
            s1 = ApplicationLoaders.getDefault().createAndCacheWebViewClassLoader(s, s1, s2);
            s = TextUtils.split(s, File.pathSeparator);
            int i = 0;
            for(int k = s.length; i < k; i++)
                Zygote.nativeAllowFileAcrossFork(s[i]);

            flag = false;
            flag1 = false;
            flag3 = flag;
            s = WebViewFactory.getWebViewProviderClass(s1);
            flag3 = flag;
            s1 = s.getMethod("preloadInZygote", new Class[0]);
            flag3 = flag;
            s1.setAccessible(true);
            flag3 = flag;
            if(s1.getReturnType() == Boolean.TYPE) goto _L2; else goto _L1
_L1:
            flag3 = flag;
            Log.e("WebViewZygoteInit", "Unexpected return type: preloadInZygote must return boolean");
            flag3 = flag1;
_L4:
            int j;
            boolean flag2;
            try
            {
                s = getSocketOutputStream();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new IllegalStateException("Error writing to command socket", s);
            }
            if(flag3)
                j = 1;
            else
                j = 0;
            s.writeInt(j);
            Log.i("WebViewZygoteInit", "Package preload done");
            return;
_L2:
            flag3 = flag;
            flag2 = ((Boolean)s.getMethod("preloadInZygote", new Class[0]).invoke(null, new Object[0])).booleanValue();
            flag3 = flag2;
            if(flag2) goto _L4; else goto _L3
_L3:
            flag3 = flag2;
            Log.e("WebViewZygoteInit", "preloadInZygote returned false");
            flag3 = flag2;
              goto _L4
            s;
            Log.e("WebViewZygoteInit", "Exception while preloading package", s);
              goto _L4
        }

        protected boolean isPreloadComplete()
        {
            return true;
        }

        protected void preload()
        {
        }

        WebViewZygoteConnection(LocalSocket localsocket, String s)
            throws IOException
        {
            super(localsocket, s);
        }
    }

    private static class WebViewZygoteServer extends ZygoteServer
    {

        protected ZygoteConnection createNewConnection(LocalSocket localsocket, String s)
            throws IOException
        {
            return new WebViewZygoteConnection(localsocket, s);
        }

        private WebViewZygoteServer()
        {
        }

        WebViewZygoteServer(WebViewZygoteServer webviewzygoteserver)
        {
            this();
        }
    }


    WebViewZygoteInit()
    {
    }

    public static void main(String args[])
    {
        sServer = new WebViewZygoteServer(null);
        try
        {
            Os.setpgid(0, 0);
        }
        // Misplaced declaration of an exception variable
        catch(String args[])
        {
            throw new RuntimeException("Failed to setpgid(0,0)", args);
        }
        sServer.registerServerSocket("webview_zygote");
        args = sServer.runSelectLoop(TextUtils.join(",", Build.SUPPORTED_ABIS));
        sServer.closeServerSocket();
        if(args != null)
            args.run();
        return;
        args;
        Log.e("WebViewZygoteInit", "Fatal exception:", args);
        throw args;
        args;
        sServer.closeServerSocket();
        throw args;
    }

    public static final String TAG = "WebViewZygoteInit";
    private static ZygoteServer sServer;
}
