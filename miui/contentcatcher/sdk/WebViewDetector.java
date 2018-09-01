// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk;

import java.lang.ref.WeakReference;

public class WebViewDetector
{
    public static interface Callback
    {

        public abstract void onWebContentCatched(String s);
    }


    public WebViewDetector()
    {
    }

    public static WebViewDetector getInstance()
    {
        if(sDetector != null) goto _L2; else goto _L1
_L1:
        miui/contentcatcher/sdk/WebViewDetector;
        JVM INSTR monitorenter ;
        if(sDetector == null)
        {
            WebViewDetector webviewdetector = JVM INSTR new #2   <Class WebViewDetector>;
            webviewdetector.WebViewDetector();
            sDetector = webviewdetector;
        }
        miui/contentcatcher/sdk/WebViewDetector;
        JVM INSTR monitorexit ;
_L2:
        return sDetector;
        Exception exception;
        exception;
        throw exception;
    }

    public void onCatch(String s)
    {
        if(mCallbackRef != null)
        {
            Callback callback = (Callback)mCallbackRef.get();
            if(callback != null)
                callback.onWebContentCatched(s);
        }
    }

    public void setActiveCallback(Callback callback)
    {
        mCallbackRef = new WeakReference(callback);
    }

    public void setCustomDetector(WebViewDetector webviewdetector)
    {
        miui/contentcatcher/sdk/WebViewDetector;
        JVM INSTR monitorenter ;
        sDetector = webviewdetector;
        miui/contentcatcher/sdk/WebViewDetector;
        JVM INSTR monitorexit ;
        return;
        webviewdetector;
        throw webviewdetector;
    }

    public static final String DETECTOR_NAME_IN_JS = "MiWebViewDetector";
    public static final String TAG = "WebViewDetector";
    private static volatile WebViewDetector sDetector;
    private Callback mCallback;
    private WeakReference mCallbackRef;
}
