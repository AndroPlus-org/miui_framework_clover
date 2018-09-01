// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.utils;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class WebViewUtils
{
    static class NativeWebViewUtils
    {

        public static void addJavascriptInterface(View view, Object obj, String s)
        {
            if(view == null || obj == null || s == null)
                break MISSING_BLOCK_LABEL_28;
            if(view instanceof WebView)
                ((WebView)view).addJavascriptInterface(obj, s);
_L1:
            return;
            view;
            Log.w("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
        }

        public static void setJavaScriptEnabled(View view, boolean flag)
        {
            if(view == null)
                break MISSING_BLOCK_LABEL_29;
            if(!(view instanceof WebView))
                break MISSING_BLOCK_LABEL_29;
            view = ((WebView)view).getSettings();
            if(view == null)
                return;
            view.setJavaScriptEnabled(flag);
_L1:
            return;
            view;
            Log.w("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
        }

        NativeWebViewUtils()
        {
        }
    }

    static class ThirdWebViewUtils
    {

        public static void addJavascriptInterface(View view, Object obj, String s)
        {
            Method method = view.getClass().getMethod("addJavascriptInterface", new Class[] {
                java/lang/Object, java/lang/String
            });
            if(method == null)
                break MISSING_BLOCK_LABEL_51;
            method.setAccessible(true);
            method.invoke(view, new Object[] {
                obj, s
            });
_L1:
            return;
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
            view = view.getTargetException();
            Log.e("WebViewUtils", (new StringBuilder()).append("InvocationTargetException-target:").append(view).toString());
              goto _L1
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
        }

        public static void setJavaScriptEnabled(View view, boolean flag)
        {
            Method method = view.getClass().getMethod("getSettings", new Class[0]);
            if(method == null)
                return;
            method.setAccessible(true);
            view = ((View) (method.invoke(view, new Object[0])));
            if(view == null)
                return;
            method = view.getClass().getMethod("setJavaScriptEnabled", new Class[] {
                Boolean.TYPE
            });
            if(method == null)
                return;
            method.setAccessible(true);
            method.invoke(view, new Object[] {
                Boolean.valueOf(flag)
            });
_L1:
            return;
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
            view = view.getTargetException();
            Log.e("WebViewUtils", (new StringBuilder()).append("InvocationTargetException-target:").append(view).toString());
              goto _L1
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
            view;
            Log.e("WebViewUtils", (new StringBuilder()).append("e:").append(view).toString());
              goto _L1
        }

        ThirdWebViewUtils()
        {
        }
    }

    private static class WebViewClassMatcher
    {

        static void _2D_wrap0(WebViewClassMatcher webviewclassmatcher, String s)
        {
            webviewclassmatcher.addToMatchList(s);
        }

        static void _2D_wrap1(WebViewClassMatcher webviewclassmatcher, String s)
        {
            webviewclassmatcher.removeFromMatchList(s);
        }

        private void addToMatchList(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(!mMatchList.contains(s))
                mMatchList.add(s);
            this;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        private void removeFromMatchList(String s)
        {
            this;
            JVM INSTR monitorenter ;
            mMatchList.remove(s);
            this;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public List mMatchList;

        private WebViewClassMatcher()
        {
            mMatchList = new ArrayList();
        }

        WebViewClassMatcher(WebViewClassMatcher webviewclassmatcher)
        {
            this();
        }
    }


    public WebViewUtils()
    {
    }

    public static void addToWebViewClassMatcher(String s)
    {
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorenter ;
        boolean flag = TextUtils.isEmpty(s);
        if(flag)
            break MISSING_BLOCK_LABEL_16;
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return;
        int i = s.length();
        if(i < 50 && i > 5)
            break MISSING_BLOCK_LABEL_36;
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return;
        WebViewClassMatcher awebviewclassmatcher[];
        WebViewClassMatcher webviewclassmatcher;
        if(mWebViewClassMatcherArray[i] != null)
            break MISSING_BLOCK_LABEL_64;
        awebviewclassmatcher = mWebViewClassMatcherArray;
        webviewclassmatcher = JVM INSTR new #12  <Class WebViewUtils$WebViewClassMatcher>;
        webviewclassmatcher.WebViewClassMatcher(null);
        awebviewclassmatcher[i] = webviewclassmatcher;
        WebViewClassMatcher._2D_wrap0(mWebViewClassMatcherArray[i], s);
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public static boolean checkViewMatchedByClassName(View view, String s)
    {
        if(view == null || TextUtils.isEmpty(s))
            return true;
        for(view = view.getClass(); view != null; view = view.getSuperclass())
            if(s.equals(view.getName()))
                return true;

        return false;
    }

    private static boolean foundFromWebViewClassMatcher(String s, WebViewClassMatcher awebviewclassmatcher[])
    {
        int i = s.length();
        if(i >= 50)
            return false;
        awebviewclassmatcher = awebviewclassmatcher[i];
        if(awebviewclassmatcher == null)
            return false;
        i -= 5;
        for(awebviewclassmatcher = ((WebViewClassMatcher) (awebviewclassmatcher)).mMatchList.iterator(); awebviewclassmatcher.hasNext();)
        {
            String s1 = (String)awebviewclassmatcher.next();
            if(s.charAt(0) == s1.charAt(0) && s.charAt(i) == s1.charAt(i) && s.equals(s1))
                return true;
        }

        return false;
    }

    public static void initWebViewJsInterface(View view, Object obj, String s)
    {
        if(view instanceof WebView)
            NativeWebViewUtils.addJavascriptInterface(view, obj, s);
        else
            ThirdWebViewUtils.addJavascriptInterface(view, obj, s);
    }

    private static boolean isSystemView(String s)
    {
        return !TextUtils.isEmpty(s) && (s.startsWith("android.view") || s.startsWith("android.widget"));
    }

    public static boolean isWebView(View view)
    {
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorenter ;
        if(view == null)
            return false;
        view = view.getClass();
_L2:
        if(view == null)
            break; /* Loop/switch isn't completed */
        boolean flag = foundFromWebViewClassMatcher(view.getName(), mWebViewClassMatcherArray);
        if(!flag)
            break MISSING_BLOCK_LABEL_41;
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return true;
        view = view.getSuperclass();
        if(true) goto _L2; else goto _L1
_L1:
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return false;
        view;
        throw view;
    }

    public static void removeFromWebViewClassMatcher(String s)
    {
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorenter ;
        boolean flag = TextUtils.isEmpty(s);
        if(flag)
            break MISSING_BLOCK_LABEL_16;
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return;
        int i = s.length();
        if(i < 50 && i > 5)
            break MISSING_BLOCK_LABEL_36;
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return;
        if(mWebViewClassMatcherArray[i] != null)
            WebViewClassMatcher._2D_wrap1(mWebViewClassMatcherArray[i], s);
        miui/contentcatcher/sdk/utils/WebViewUtils;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    private static final int COMPARISON_CHAR_INDEX_FROM_TAIL = 5;
    private static final boolean DBG = false;
    private static final int MATCHER_ARRAY_SIZE = 50;
    private static final String TAG = "WebViewUtils";
    public static final String WEBVIEW_NAME_ANDROID = "android.webkit.WebView";
    public static final String WEBVIEW_NAME_BAIDU = "com.baidu.webkit.sdk.WebView";
    public static final String WEBVIEW_NAME_BAIDU1 = "com.baidu.blink.WebView";
    public static final String WEBVIEW_NAME_MIUI = "com.miui.webkit.WebView";
    public static final String WEBVIEW_NAME_SOGOU = "sogou.webkit.WebView";
    public static final String WEBVIEW_NAME_TENCENT = "com.tencent.smtt.webkit.WebView";
    public static final String WEBVIEW_NAME_UC = "com.uc.webview.export.WebView";
    private static final WebViewClassMatcher mWebViewClassMatcherArray[];

    static 
    {
        mWebViewClassMatcherArray = new WebViewClassMatcher[50];
        mWebViewClassMatcherArray[20] = new WebViewClassMatcher(null);
        mWebViewClassMatcherArray[20].mMatchList.add("sogou.webkit.WebView");
        mWebViewClassMatcherArray[22] = new WebViewClassMatcher(null);
        mWebViewClassMatcherArray[22].mMatchList.add("android.webkit.WebView");
        mWebViewClassMatcherArray[23] = new WebViewClassMatcher(null);
        mWebViewClassMatcherArray[23].mMatchList.add("com.miui.webkit.WebView");
        mWebViewClassMatcherArray[23].mMatchList.add("com.baidu.blink.WebView");
        mWebViewClassMatcherArray[28] = new WebViewClassMatcher(null);
        mWebViewClassMatcherArray[28].mMatchList.add("com.baidu.webkit.sdk.WebView");
        mWebViewClassMatcherArray[29] = new WebViewClassMatcher(null);
        mWebViewClassMatcherArray[29].mMatchList.add("com.uc.webview.export.WebView");
        mWebViewClassMatcherArray[31] = new WebViewClassMatcher(null);
        mWebViewClassMatcherArray[31].mMatchList.add("com.tencent.smtt.webkit.WebView");
    }
}
