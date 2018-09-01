// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.*;
import android.print.PrintDocumentAdapter;
import android.util.*;
import android.view.*;
import android.view.accessibility.*;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.AbsoluteLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.util.List;
import java.util.Map;
import miui.content.res.ThemeFontChangeHelper;

// Referenced classes of package android.webkit:
//            WebViewProvider, CookieSyncManager, WebViewInjector, WebViewFactoryProvider, 
//            WebViewFactory, IWebViewUpdateService, PluginList, URLUtil, 
//            ValueCallback, WebBackForwardList, WebMessagePort, WebSettings, 
//            WebChromeClient, WebViewClient, WebMessage, DownloadListener

public class WebView extends AbsoluteLayout
    implements android.view.ViewTreeObserver.OnGlobalFocusChangeListener, android.view.ViewGroup.OnHierarchyChangeListener, android.view.ViewDebug.HierarchyHandler
{
    public static interface FindListener
    {

        public abstract void onFindResultReceived(int i, int j, boolean flag);
    }

    private class FindListenerDistributor
        implements FindListener
    {

        static FindListener _2D_set0(FindListenerDistributor findlistenerdistributor, FindListener findlistener)
        {
            findlistenerdistributor.mFindDialogFindListener = findlistener;
            return findlistener;
        }

        static FindListener _2D_set1(FindListenerDistributor findlistenerdistributor, FindListener findlistener)
        {
            findlistenerdistributor.mUserFindListener = findlistener;
            return findlistener;
        }

        public void onFindResultReceived(int i, int j, boolean flag)
        {
            if(mFindDialogFindListener != null)
                mFindDialogFindListener.onFindResultReceived(i, j, flag);
            if(mUserFindListener != null)
                mUserFindListener.onFindResultReceived(i, j, flag);
        }

        private FindListener mFindDialogFindListener;
        private FindListener mUserFindListener;
        final WebView this$0;

        private FindListenerDistributor()
        {
            this$0 = WebView.this;
            super();
        }

        FindListenerDistributor(FindListenerDistributor findlistenerdistributor)
        {
            this();
        }
    }

    public static class HitTestResult
    {

        public String getExtra()
        {
            return mExtra;
        }

        public int getType()
        {
            return mType;
        }

        public void setExtra(String s)
        {
            mExtra = s;
        }

        public void setType(int i)
        {
            mType = i;
        }

        public static final int ANCHOR_TYPE = 1;
        public static final int EDIT_TEXT_TYPE = 9;
        public static final int EMAIL_TYPE = 4;
        public static final int GEO_TYPE = 3;
        public static final int IMAGE_ANCHOR_TYPE = 6;
        public static final int IMAGE_TYPE = 5;
        public static final int PHONE_TYPE = 2;
        public static final int SRC_ANCHOR_TYPE = 7;
        public static final int SRC_IMAGE_ANCHOR_TYPE = 8;
        public static final int UNKNOWN_TYPE = 0;
        private String mExtra;
        private int mType;

        public HitTestResult()
        {
            mType = 0;
        }
    }

    public static interface PictureListener
    {

        public abstract void onNewPicture(WebView webview, Picture picture);
    }

    public class PrivateAccess
    {

        public void awakenScrollBars(int i)
        {
            WebView._2D_wrap1(WebView.this, i);
        }

        public void awakenScrollBars(int i, boolean flag)
        {
            WebView._2D_wrap0(WebView.this, i, flag);
        }

        public float getHorizontalScrollFactor()
        {
            return WebView._2D_wrap3(WebView.this);
        }

        public int getHorizontalScrollbarHeight()
        {
            return WebView._2D_wrap5(WebView.this);
        }

        public float getVerticalScrollFactor()
        {
            return WebView._2D_wrap4(WebView.this);
        }

        public void onScrollChanged(int i, int j, int k, int l)
        {
            WebView.this.onScrollChanged(i, j, k, l);
        }

        public void overScrollBy(int i, int j, int k, int l, int i1, int j1, int k1, 
                int l1, boolean flag)
        {
            WebView._2D_wrap2(WebView.this, i, j, k, l, i1, j1, k1, l1, flag);
        }

        public void setMeasuredDimension(int i, int j)
        {
            WebView._2D_wrap6(WebView.this, i, j);
        }

        public void setScrollXRaw(int i)
        {
            WebView._2D_set0(WebView.this, i);
        }

        public void setScrollYRaw(int i)
        {
            WebView._2D_set1(WebView.this, i);
        }

        public void super_computeScroll()
        {
            WebView._2D_wrap15(WebView.this);
        }

        public boolean super_dispatchKeyEvent(KeyEvent keyevent)
        {
            return WebView._2D_wrap7(WebView.this, keyevent);
        }

        public int super_getScrollBarStyle()
        {
            return WebView._2D_wrap14(WebView.this);
        }

        public void super_onDrawVerticalScrollBar(Canvas canvas, Drawable drawable, int i, int j, int k, int l)
        {
            WebView._2D_wrap16(WebView.this, canvas, drawable, i, j, k, l);
        }

        public boolean super_onGenericMotionEvent(MotionEvent motionevent)
        {
            return WebView._2D_wrap8(WebView.this, motionevent);
        }

        public boolean super_onHoverEvent(MotionEvent motionevent)
        {
            return WebView._2D_wrap9(WebView.this, motionevent);
        }

        public boolean super_performAccessibilityAction(int i, Bundle bundle)
        {
            return WebView._2D_wrap10(WebView.this, i, bundle);
        }

        public boolean super_performLongClick()
        {
            return WebView._2D_wrap11(WebView.this);
        }

        public boolean super_requestFocus(int i, Rect rect)
        {
            return WebView._2D_wrap12(WebView.this, i, rect);
        }

        public void super_scrollTo(int i, int j)
        {
            WebView._2D_wrap17(WebView.this, i, j);
        }

        public boolean super_setFrame(int i, int j, int k, int l)
        {
            return WebView._2D_wrap13(WebView.this, i, j, k, l);
        }

        public void super_setLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            WebView._2D_wrap18(WebView.this, layoutparams);
        }

        public void super_startActivityForResult(Intent intent, int i)
        {
            WebView._2D_wrap19(WebView.this, intent, i);
        }

        final WebView this$0;

        public PrivateAccess()
        {
            this$0 = WebView.this;
            super();
        }
    }

    public static abstract class VisualStateCallback
    {

        public abstract void onComplete(long l);

        public VisualStateCallback()
        {
        }
    }

    public class WebViewTransport
    {

        public WebView getWebView()
        {
            this;
            JVM INSTR monitorenter ;
            WebView webview = mWebview;
            this;
            JVM INSTR monitorexit ;
            return webview;
            Exception exception;
            exception;
            throw exception;
        }

        public void setWebView(WebView webview)
        {
            this;
            JVM INSTR monitorenter ;
            mWebview = webview;
            this;
            JVM INSTR monitorexit ;
            return;
            webview;
            throw webview;
        }

        private WebView mWebview;
        final WebView this$0;

        public WebViewTransport()
        {
            this$0 = WebView.this;
            super();
        }
    }


    static int _2D_set0(WebView webview, int i)
    {
        webview.mScrollX = i;
        return i;
    }

    static int _2D_set1(WebView webview, int i)
    {
        webview.mScrollY = i;
        return i;
    }

    static boolean _2D_wrap0(WebView webview, int i, boolean flag)
    {
        return webview.awakenScrollBars(i, flag);
    }

    static boolean _2D_wrap1(WebView webview, int i)
    {
        return webview.awakenScrollBars(i);
    }

    static boolean _2D_wrap10(WebView webview, int i, Bundle bundle)
    {
        return webview.AbsoluteLayout.performAccessibilityActionInternal(i, bundle);
    }

    static boolean _2D_wrap11(WebView webview)
    {
        return webview.AbsoluteLayout.performLongClick();
    }

    static boolean _2D_wrap12(WebView webview, int i, Rect rect)
    {
        return webview.AbsoluteLayout.requestFocus(i, rect);
    }

    static boolean _2D_wrap13(WebView webview, int i, int j, int k, int l)
    {
        return webview.AbsoluteLayout.setFrame(i, j, k, l);
    }

    static int _2D_wrap14(WebView webview)
    {
        return webview.AbsoluteLayout.getScrollBarStyle();
    }

    static void _2D_wrap15(WebView webview)
    {
        webview.AbsoluteLayout.computeScroll();
    }

    static void _2D_wrap16(WebView webview, Canvas canvas, Drawable drawable, int i, int j, int k, int l)
    {
        webview.AbsoluteLayout.onDrawVerticalScrollBar(canvas, drawable, i, j, k, l);
    }

    static void _2D_wrap17(WebView webview, int i, int j)
    {
        webview.AbsoluteLayout.scrollTo(i, j);
    }

    static void _2D_wrap18(WebView webview, android.view.ViewGroup.LayoutParams layoutparams)
    {
        webview.AbsoluteLayout.setLayoutParams(layoutparams);
    }

    static void _2D_wrap19(WebView webview, Intent intent, int i)
    {
        webview.AbsoluteLayout.startActivityForResult(intent, i);
    }

    static boolean _2D_wrap2(WebView webview, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, boolean flag)
    {
        return webview.overScrollBy(i, j, k, l, i1, j1, k1, l1, flag);
    }

    static float _2D_wrap3(WebView webview)
    {
        return webview.getHorizontalScrollFactor();
    }

    static float _2D_wrap4(WebView webview)
    {
        return webview.getVerticalScrollFactor();
    }

    static int _2D_wrap5(WebView webview)
    {
        return webview.getHorizontalScrollbarHeight();
    }

    static void _2D_wrap6(WebView webview, int i, int j)
    {
        webview.setMeasuredDimension(i, j);
    }

    static boolean _2D_wrap7(WebView webview, KeyEvent keyevent)
    {
        return webview.AbsoluteLayout.dispatchKeyEvent(keyevent);
    }

    static boolean _2D_wrap8(WebView webview, MotionEvent motionevent)
    {
        return webview.AbsoluteLayout.onGenericMotionEvent(motionevent);
    }

    static boolean _2D_wrap9(WebView webview, MotionEvent motionevent)
    {
        return webview.AbsoluteLayout.onHoverEvent(motionevent);
    }

    public WebView(Context context)
    {
        this(context, null);
    }

    public WebView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010085);
    }

    public WebView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public WebView(Context context, AttributeSet attributeset, int i, int j)
    {
        this(context, attributeset, i, j, null, false);
    }

    protected WebView(Context context, AttributeSet attributeset, int i, int j, Map map, boolean flag)
    {
        boolean flag1 = true;
        super(context, attributeset, i, j);
        mWebViewThread = Looper.myLooper();
        if(getImportantForAutofill() == 0)
            setImportantForAutofill(1);
        if(context == null)
            throw new IllegalArgumentException("Invalid context argument");
        if(context.getApplicationInfo().targetSdkVersion < 18)
            flag1 = false;
        sEnforceThreadChecking = flag1;
        checkThread();
        ensureProviderCreated();
        mProvider.init(map, flag);
        CookieSyncManager.setGetInstanceIsAllowed();
        WebViewInjector.initEgretLoader(this, context);
        ThemeFontChangeHelper.markWebViewCreated(context);
    }

    protected WebView(Context context, AttributeSet attributeset, int i, Map map, boolean flag)
    {
        this(context, attributeset, i, 0, map, flag);
    }

    public WebView(Context context, AttributeSet attributeset, int i, boolean flag)
    {
        this(context, attributeset, i, 0, null, flag);
    }

    private void checkThread()
    {
        if(mWebViewThread != null && Looper.myLooper() != mWebViewThread)
        {
            Throwable throwable = new Throwable((new StringBuilder()).append("A WebView method was called on thread '").append(Thread.currentThread().getName()).append("'. ").append("All WebView methods must be called on the same thread. ").append("(Expected Looper ").append(mWebViewThread).append(" called on ").append(Looper.myLooper()).append(", FYI main Looper is ").append(Looper.getMainLooper()).append(")").toString());
            Log.w("WebView", Log.getStackTraceString(throwable));
            StrictMode.onWebViewMethodCalledOnWrongThread(throwable);
            if(sEnforceThreadChecking)
                throw new RuntimeException(throwable);
        }
    }

    public static void clearClientCertPreferences(Runnable runnable)
    {
        getFactory().getStatics().clearClientCertPreferences(runnable);
    }

    public static void disablePlatformNotifications()
    {
    }

    public static void enablePlatformNotifications()
    {
    }

    public static void enableSlowWholeDocumentDraw()
    {
        getFactory().getStatics().enableSlowWholeDocumentDraw();
    }

    private void ensureProviderCreated()
    {
        checkThread();
        if(mProvider == null)
            mProvider = getFactory().createWebView(this, new PrivateAccess());
    }

    public static String findAddress(String s)
    {
        return getFactory().getStatics().findAddress(s);
    }

    public static void freeMemoryForTests()
    {
        getFactory().getStatics().freeMemoryForTests();
    }

    public static PackageInfo getCurrentWebViewPackage()
    {
        PackageInfo packageinfo = WebViewFactory.getLoadedPackageInfo();
        if(packageinfo != null)
            return packageinfo;
        try
        {
            packageinfo = WebViewFactory.getUpdateService().getCurrentWebViewPackage();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return packageinfo;
    }

    private static WebViewFactoryProvider getFactory()
    {
        return WebViewFactory.getProvider();
    }

    public static PluginList getPluginList()
    {
        android/webkit/WebView;
        JVM INSTR monitorenter ;
        PluginList pluginlist = new PluginList();
        android/webkit/WebView;
        JVM INSTR monitorexit ;
        return pluginlist;
        Exception exception;
        exception;
        throw exception;
    }

    public static Uri getSafeBrowsingPrivacyPolicyUrl()
    {
        return getFactory().getStatics().getSafeBrowsingPrivacyPolicyUrl();
    }

    public static void setSafeBrowsingWhitelist(List list, ValueCallback valuecallback)
    {
        getFactory().getStatics().setSafeBrowsingWhitelist(list, valuecallback);
    }

    public static void setWebContentsDebuggingEnabled(boolean flag)
    {
        getFactory().getStatics().setWebContentsDebuggingEnabled(flag);
    }

    private void setupFindListenerIfNeeded()
    {
        if(mFindListener == null)
        {
            mFindListener = new FindListenerDistributor(null);
            mProvider.setFindListener(mFindListener);
        }
    }

    public static void startSafeBrowsing(Context context, ValueCallback valuecallback)
    {
        getFactory().getStatics().initSafeBrowsing(context, valuecallback);
    }

    public void addJavascriptInterface(Object obj, String s)
    {
        checkThread();
        mProvider.addJavascriptInterface(obj, s);
    }

    public void autofill(SparseArray sparsearray)
    {
        mProvider.getViewDelegate().autofill(sparsearray);
    }

    public boolean canGoBack()
    {
        checkThread();
        return mProvider.canGoBack();
    }

    public boolean canGoBackOrForward(int i)
    {
        checkThread();
        return mProvider.canGoBackOrForward(i);
    }

    public boolean canGoForward()
    {
        checkThread();
        return mProvider.canGoForward();
    }

    public boolean canZoomIn()
    {
        checkThread();
        return mProvider.canZoomIn();
    }

    public boolean canZoomOut()
    {
        checkThread();
        return mProvider.canZoomOut();
    }

    public Picture capturePicture()
    {
        checkThread();
        return mProvider.capturePicture();
    }

    public void clearCache(boolean flag)
    {
        checkThread();
        mProvider.clearCache(flag);
    }

    public void clearFormData()
    {
        checkThread();
        mProvider.clearFormData();
    }

    public void clearHistory()
    {
        checkThread();
        mProvider.clearHistory();
    }

    public void clearMatches()
    {
        checkThread();
        mProvider.clearMatches();
    }

    public void clearSslPreferences()
    {
        checkThread();
        mProvider.clearSslPreferences();
    }

    public void clearView()
    {
        checkThread();
        mProvider.clearView();
    }

    protected int computeHorizontalScrollOffset()
    {
        return mProvider.getScrollDelegate().computeHorizontalScrollOffset();
    }

    protected int computeHorizontalScrollRange()
    {
        return mProvider.getScrollDelegate().computeHorizontalScrollRange();
    }

    public void computeScroll()
    {
        mProvider.getScrollDelegate().computeScroll();
    }

    protected int computeVerticalScrollExtent()
    {
        return mProvider.getScrollDelegate().computeVerticalScrollExtent();
    }

    protected int computeVerticalScrollOffset()
    {
        return mProvider.getScrollDelegate().computeVerticalScrollOffset();
    }

    protected int computeVerticalScrollRange()
    {
        return mProvider.getScrollDelegate().computeVerticalScrollRange();
    }

    public WebBackForwardList copyBackForwardList()
    {
        checkThread();
        return mProvider.copyBackForwardList();
    }

    public PrintDocumentAdapter createPrintDocumentAdapter()
    {
        checkThread();
        return mProvider.createPrintDocumentAdapter("default");
    }

    public PrintDocumentAdapter createPrintDocumentAdapter(String s)
    {
        checkThread();
        return mProvider.createPrintDocumentAdapter(s);
    }

    public WebMessagePort[] createWebMessageChannel()
    {
        checkThread();
        return mProvider.createWebMessageChannel();
    }

    public void debugDump()
    {
        checkThread();
    }

    public void destroy()
    {
        checkThread();
        mProvider.destroy();
    }

    protected void dispatchDraw(Canvas canvas)
    {
        mProvider.getViewDelegate().preDispatchDraw(canvas);
        super.dispatchDraw(canvas);
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        return mProvider.getViewDelegate().dispatchKeyEvent(keyevent);
    }

    public void documentHasImages(Message message)
    {
        checkThread();
        mProvider.documentHasImages(message);
    }

    public void dumpViewHierarchyWithProperties(BufferedWriter bufferedwriter, int i)
    {
        mProvider.dumpViewHierarchyWithProperties(bufferedwriter, i);
    }

    public void emulateShiftHeld()
    {
        checkThread();
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        checkThread();
        viewhierarchyencoder.addProperty("webview:contentHeight", mProvider.getContentHeight());
        viewhierarchyencoder.addProperty("webview:contentWidth", mProvider.getContentWidth());
        viewhierarchyencoder.addProperty("webview:scale", mProvider.getScale());
        viewhierarchyencoder.addProperty("webview:title", mProvider.getTitle());
        viewhierarchyencoder.addProperty("webview:url", mProvider.getUrl());
        viewhierarchyencoder.addProperty("webview:originalUrl", mProvider.getOriginalUrl());
    }

    public void evaluateJavascript(String s, ValueCallback valuecallback)
    {
        checkThread();
        mProvider.evaluateJavaScript(s, valuecallback);
    }

    public int findAll(String s)
    {
        checkThread();
        StrictMode.noteSlowCall("findAll blocks UI: prefer findAllAsync");
        return mProvider.findAll(s);
    }

    public void findAllAsync(String s)
    {
        checkThread();
        mProvider.findAllAsync(s);
    }

    public View findFocus()
    {
        return mProvider.getViewDelegate().findFocus(super.findFocus());
    }

    public View findHierarchyView(String s, int i)
    {
        return mProvider.findHierarchyView(s, i);
    }

    public void findNext(boolean flag)
    {
        checkThread();
        mProvider.findNext(flag);
    }

    public void flingScroll(int i, int j)
    {
        checkThread();
        mProvider.flingScroll(i, j);
    }

    public void freeMemory()
    {
        checkThread();
        mProvider.freeMemory();
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/webkit/WebView.getName();
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider()
    {
        AccessibilityNodeProvider accessibilitynodeprovider = mProvider.getViewDelegate().getAccessibilityNodeProvider();
        AccessibilityNodeProvider accessibilitynodeprovider1 = accessibilitynodeprovider;
        if(accessibilitynodeprovider == null)
            accessibilitynodeprovider1 = super.getAccessibilityNodeProvider();
        return accessibilitynodeprovider1;
    }

    public SslCertificate getCertificate()
    {
        checkThread();
        return mProvider.getCertificate();
    }

    public int getContentHeight()
    {
        checkThread();
        return mProvider.getContentHeight();
    }

    public int getContentWidth()
    {
        return mProvider.getContentWidth();
    }

    public Bitmap getFavicon()
    {
        checkThread();
        return mProvider.getFavicon();
    }

    public Handler getHandler()
    {
        return mProvider.getViewDelegate().getHandler(super.getHandler());
    }

    public HitTestResult getHitTestResult()
    {
        checkThread();
        return mProvider.getHitTestResult();
    }

    public String[] getHttpAuthUsernamePassword(String s, String s1)
    {
        checkThread();
        return mProvider.getHttpAuthUsernamePassword(s, s1);
    }

    public String getOriginalUrl()
    {
        checkThread();
        return mProvider.getOriginalUrl();
    }

    public int getProgress()
    {
        checkThread();
        return mProvider.getProgress();
    }

    public boolean getRendererPriorityWaivedWhenNotVisible()
    {
        return mProvider.getRendererPriorityWaivedWhenNotVisible();
    }

    public int getRendererRequestedPriority()
    {
        return mProvider.getRendererRequestedPriority();
    }

    public float getScale()
    {
        checkThread();
        return mProvider.getScale();
    }

    public WebSettings getSettings()
    {
        checkThread();
        return mProvider.getSettings();
    }

    public TextClassifier getTextClassifier()
    {
        return mProvider.getTextClassifier();
    }

    public String getTitle()
    {
        checkThread();
        return mProvider.getTitle();
    }

    public String getTouchIconUrl()
    {
        return mProvider.getTouchIconUrl();
    }

    public String getUrl()
    {
        checkThread();
        return mProvider.getUrl();
    }

    public int getVisibleTitleHeight()
    {
        checkThread();
        return mProvider.getVisibleTitleHeight();
    }

    public WebChromeClient getWebChromeClient()
    {
        checkThread();
        return mProvider.getWebChromeClient();
    }

    public WebViewClient getWebViewClient()
    {
        checkThread();
        return mProvider.getWebViewClient();
    }

    public WebViewProvider getWebViewProvider()
    {
        return mProvider;
    }

    public View getZoomControls()
    {
        checkThread();
        return mProvider.getZoomControls();
    }

    public void goBack()
    {
        checkThread();
        mProvider.goBack();
    }

    public void goBackOrForward(int i)
    {
        checkThread();
        mProvider.goBackOrForward(i);
    }

    public void goForward()
    {
        checkThread();
        mProvider.goForward();
    }

    public void invokeZoomPicker()
    {
        checkThread();
        mProvider.invokeZoomPicker();
    }

    public boolean isPaused()
    {
        return mProvider.isPaused();
    }

    public boolean isPrivateBrowsingEnabled()
    {
        checkThread();
        return mProvider.isPrivateBrowsingEnabled();
    }

    public void loadData(String s, String s1, String s2)
    {
        checkThread();
        mProvider.loadData(s, s1, s2);
    }

    public void loadDataWithBaseURL(String s, String s1, String s2, String s3, String s4)
    {
        checkThread();
        mProvider.loadDataWithBaseURL(s, s1, s2, s3, s4);
    }

    public void loadUrl(String s)
    {
        checkThread();
        mProvider.loadUrl(s);
    }

    public void loadUrl(String s, Map map)
    {
        checkThread();
        mProvider.loadUrl(s, map);
    }

    void notifyFindDialogDismissed()
    {
        checkThread();
        mProvider.notifyFindDialogDismissed();
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        mProvider.getViewDelegate().onActivityResult(i, j, intent);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mProvider.getViewDelegate().onAttachedToWindow();
    }

    public void onChildViewAdded(View view, View view1)
    {
    }

    public void onChildViewRemoved(View view, View view1)
    {
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        mProvider.getViewDelegate().onConfigurationChanged(configuration);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        return mProvider.getViewDelegate().onCreateInputConnection(editorinfo);
    }

    protected void onDetachedFromWindowInternal()
    {
        mProvider.getViewDelegate().onDetachedFromWindow();
        super.onDetachedFromWindowInternal();
    }

    public boolean onDragEvent(DragEvent dragevent)
    {
        return mProvider.getViewDelegate().onDragEvent(dragevent);
    }

    protected void onDraw(Canvas canvas)
    {
        mProvider.getViewDelegate().onDraw(canvas);
    }

    protected void onDrawVerticalScrollBar(Canvas canvas, Drawable drawable, int i, int j, int k, int l)
    {
        mProvider.getViewDelegate().onDrawVerticalScrollBar(canvas, drawable, i, j, k, l);
    }

    public void onFinishTemporaryDetach()
    {
        super.onFinishTemporaryDetach();
        mProvider.getViewDelegate().onFinishTemporaryDetach();
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        mProvider.getViewDelegate().onFocusChanged(flag, i, rect);
        super.onFocusChanged(flag, i, rect);
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        return mProvider.getViewDelegate().onGenericMotionEvent(motionevent);
    }

    public void onGlobalFocusChanged(View view, View view1)
    {
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        return mProvider.getViewDelegate().onHoverEvent(motionevent);
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        mProvider.getViewDelegate().onInitializeAccessibilityEvent(accessibilityevent);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        mProvider.getViewDelegate().onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        return mProvider.getViewDelegate().onKeyDown(i, keyevent);
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        return mProvider.getViewDelegate().onKeyMultiple(i, j, keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        return mProvider.getViewDelegate().onKeyUp(i, keyevent);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        mProvider.getViewDelegate().onMeasure(i, j);
    }

    public void onMovedToDisplay(int i, Configuration configuration)
    {
        mProvider.getViewDelegate().onMovedToDisplay(i, configuration);
    }

    protected void onOverScrolled(int i, int j, boolean flag, boolean flag1)
    {
        mProvider.getViewDelegate().onOverScrolled(i, j, flag, flag1);
    }

    public void onPause()
    {
        checkThread();
        mProvider.onPause();
    }

    public void onProvideAutofillVirtualStructure(ViewStructure viewstructure, int i)
    {
        mProvider.getViewDelegate().onProvideAutofillVirtualStructure(viewstructure, i);
    }

    public void onProvideVirtualStructure(ViewStructure viewstructure)
    {
        mProvider.getViewDelegate().onProvideVirtualStructure(viewstructure);
    }

    public void onResume()
    {
        checkThread();
        mProvider.onResume();
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        mProvider.getViewDelegate().onScrollChanged(i, j, k, l);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        mProvider.getViewDelegate().onSizeChanged(i, j, k, l);
    }

    public void onStartTemporaryDetach()
    {
        super.onStartTemporaryDetach();
        mProvider.getViewDelegate().onStartTemporaryDetach();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return mProvider.getViewDelegate().onTouchEvent(motionevent);
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        return mProvider.getViewDelegate().onTrackballEvent(motionevent);
    }

    protected void onVisibilityChanged(View view, int i)
    {
        super.onVisibilityChanged(view, i);
        ensureProviderCreated();
        mProvider.getViewDelegate().onVisibilityChanged(view, i);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        mProvider.getViewDelegate().onWindowFocusChanged(flag);
        super.onWindowFocusChanged(flag);
    }

    protected void onWindowVisibilityChanged(int i)
    {
        super.onWindowVisibilityChanged(i);
        mProvider.getViewDelegate().onWindowVisibilityChanged(i);
    }

    public boolean overlayHorizontalScrollbar()
    {
        return true;
    }

    public boolean overlayVerticalScrollbar()
    {
        return false;
    }

    public boolean pageDown(boolean flag)
    {
        checkThread();
        return mProvider.pageDown(flag);
    }

    public boolean pageUp(boolean flag)
    {
        checkThread();
        return mProvider.pageUp(flag);
    }

    public void pauseTimers()
    {
        checkThread();
        mProvider.pauseTimers();
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        return mProvider.getViewDelegate().performAccessibilityAction(i, bundle);
    }

    public boolean performLongClick()
    {
        return mProvider.getViewDelegate().performLongClick();
    }

    public void postUrl(String s, byte abyte0[])
    {
        checkThread();
        if(URLUtil.isNetworkUrl(s))
            mProvider.postUrl(s, abyte0);
        else
            mProvider.loadUrl(s);
    }

    public void postVisualStateCallback(long l, VisualStateCallback visualstatecallback)
    {
        checkThread();
        mProvider.insertVisualStateCallback(l, visualstatecallback);
    }

    public void postWebMessage(WebMessage webmessage, Uri uri)
    {
        checkThread();
        mProvider.postMessageToMainFrame(webmessage, uri);
    }

    public void refreshPlugins(boolean flag)
    {
        checkThread();
    }

    public void reload()
    {
        checkThread();
        mProvider.reload();
    }

    public void removeJavascriptInterface(String s)
    {
        checkThread();
        mProvider.removeJavascriptInterface(s);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        return mProvider.getViewDelegate().requestChildRectangleOnScreen(view, rect, flag);
    }

    public boolean requestFocus(int i, Rect rect)
    {
        return mProvider.getViewDelegate().requestFocus(i, rect);
    }

    public void requestFocusNodeHref(Message message)
    {
        checkThread();
        mProvider.requestFocusNodeHref(message);
    }

    public void requestImageRef(Message message)
    {
        checkThread();
        mProvider.requestImageRef(message);
    }

    public boolean restorePicture(Bundle bundle, File file)
    {
        checkThread();
        return mProvider.restorePicture(bundle, file);
    }

    public WebBackForwardList restoreState(Bundle bundle)
    {
        checkThread();
        return mProvider.restoreState(bundle);
    }

    public void resumeTimers()
    {
        checkThread();
        mProvider.resumeTimers();
    }

    public void savePassword(String s, String s1, String s2)
    {
        checkThread();
        mProvider.savePassword(s, s1, s2);
    }

    public boolean savePicture(Bundle bundle, File file)
    {
        checkThread();
        return mProvider.savePicture(bundle, file);
    }

    public WebBackForwardList saveState(Bundle bundle)
    {
        checkThread();
        return mProvider.saveState(bundle);
    }

    public void saveWebArchive(String s)
    {
        checkThread();
        mProvider.saveWebArchive(s);
    }

    public void saveWebArchive(String s, boolean flag, ValueCallback valuecallback)
    {
        checkThread();
        mProvider.saveWebArchive(s, flag, valuecallback);
    }

    public void setBackgroundColor(int i)
    {
        mProvider.getViewDelegate().setBackgroundColor(i);
    }

    public void setCertificate(SslCertificate sslcertificate)
    {
        checkThread();
        mProvider.setCertificate(sslcertificate);
    }

    public void setDownloadListener(DownloadListener downloadlistener)
    {
        checkThread();
        mProvider.setDownloadListener(downloadlistener);
    }

    void setFindDialogFindListener(FindListener findlistener)
    {
        checkThread();
        setupFindListenerIfNeeded();
        FindListenerDistributor._2D_set0(mFindListener, findlistener);
    }

    public void setFindListener(FindListener findlistener)
    {
        checkThread();
        setupFindListenerIfNeeded();
        FindListenerDistributor._2D_set1(mFindListener, findlistener);
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        return mProvider.getViewDelegate().setFrame(i, j, k, l);
    }

    public void setHorizontalScrollbarOverlay(boolean flag)
    {
    }

    public void setHttpAuthUsernamePassword(String s, String s1, String s2, String s3)
    {
        checkThread();
        mProvider.setHttpAuthUsernamePassword(s, s1, s2, s3);
    }

    public void setInitialScale(int i)
    {
        checkThread();
        mProvider.setInitialScale(i);
    }

    public void setLayerType(int i, Paint paint)
    {
        super.setLayerType(i, paint);
        mProvider.getViewDelegate().setLayerType(i, paint);
    }

    public void setLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        mProvider.getViewDelegate().setLayoutParams(layoutparams);
    }

    public void setMapTrackballToArrowKeys(boolean flag)
    {
        checkThread();
        mProvider.setMapTrackballToArrowKeys(flag);
    }

    public void setNetworkAvailable(boolean flag)
    {
        checkThread();
        mProvider.setNetworkAvailable(flag);
    }

    public void setOverScrollMode(int i)
    {
        super.setOverScrollMode(i);
        ensureProviderCreated();
        mProvider.getViewDelegate().setOverScrollMode(i);
    }

    public void setPictureListener(PictureListener picturelistener)
    {
        checkThread();
        mProvider.setPictureListener(picturelistener);
    }

    public void setRendererPriorityPolicy(int i, boolean flag)
    {
        mProvider.setRendererPriorityPolicy(i, flag);
    }

    public void setScrollBarStyle(int i)
    {
        mProvider.getViewDelegate().setScrollBarStyle(i);
        super.setScrollBarStyle(i);
    }

    public void setTextClassifier(TextClassifier textclassifier)
    {
        mProvider.setTextClassifier(textclassifier);
    }

    public void setVerticalScrollbarOverlay(boolean flag)
    {
    }

    public void setWebChromeClient(WebChromeClient webchromeclient)
    {
        checkThread();
        mProvider.setWebChromeClient(webchromeclient);
    }

    public void setWebViewClient(WebViewClient webviewclient)
    {
        checkThread();
        mProvider.setWebViewClient(webviewclient);
    }

    public boolean shouldDelayChildPressedState()
    {
        return mProvider.getViewDelegate().shouldDelayChildPressedState();
    }

    public boolean showFindDialog(String s, boolean flag)
    {
        checkThread();
        return mProvider.showFindDialog(s, flag);
    }

    public void stopLoading()
    {
        checkThread();
        mProvider.stopLoading();
    }

    public void zoomBy(float f)
    {
        checkThread();
        if((double)f < 0.01D)
            throw new IllegalArgumentException("zoomFactor must be greater than 0.01.");
        if((double)f > 100D)
        {
            throw new IllegalArgumentException("zoomFactor must be less than 100.");
        } else
        {
            mProvider.zoomBy(f);
            return;
        }
    }

    public boolean zoomIn()
    {
        checkThread();
        return mProvider.zoomIn();
    }

    public boolean zoomOut()
    {
        checkThread();
        return mProvider.zoomOut();
    }

    private static final String LOGTAG = "WebView";
    public static final int RENDERER_PRIORITY_BOUND = 1;
    public static final int RENDERER_PRIORITY_IMPORTANT = 2;
    public static final int RENDERER_PRIORITY_WAIVED = 0;
    public static final String SCHEME_GEO = "geo:0,0?q=";
    public static final String SCHEME_MAILTO = "mailto:";
    public static final String SCHEME_TEL = "tel:";
    private static volatile boolean sEnforceThreadChecking = false;
    private FindListenerDistributor mFindListener;
    private WebViewProvider mProvider;
    private final Looper mWebViewThread;

}
