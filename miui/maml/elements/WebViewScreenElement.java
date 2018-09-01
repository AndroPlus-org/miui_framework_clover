// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.webkit.*;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import miui.maml.data.Variables;
import miui.maml.util.TextFormatter;
import miui.net.ConnectivityHelper;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            AnimatedScreenElement

public class WebViewScreenElement extends AnimatedScreenElement
{
    private class MamlInterface
    {

        public void doAction(String s)
        {
            performAction(s);
        }

        public double getDouble(int i)
        {
            return getVariables().getDouble(i);
        }

        public double getDouble(String s)
        {
            return getVariables().getDouble(s);
        }

        public Object getObj(int i)
        {
            return getVariables().get(i);
        }

        public Object getObj(String s)
        {
            return getVariables().get(s);
        }

        public String getString(int i)
        {
            return getVariables().getString(i);
        }

        public String getString(String s)
        {
            return getVariables().getString(s);
        }

        public void putDouble(String s, double d)
        {
            getVariables().put(s, d);
        }

        public void putInt(String s, int i)
        {
            getVariables().put(s, i);
        }

        public void putObj(String s, Object obj)
        {
            getVariables().put(s, obj);
        }

        public void putString(String s, String s1)
        {
            getVariables().put(s, s1);
        }

        public int registerDoubleVariable(String s)
        {
            return getVariables().registerDoubleVariable(s);
        }

        public int registerVariable(String s)
        {
            return getVariables().registerVariable(s);
        }

        final WebViewScreenElement this$0;

        private MamlInterface()
        {
            this$0 = WebViewScreenElement.this;
            super();
        }

        MamlInterface(MamlInterface mamlinterface)
        {
            this();
        }
    }


    static boolean _2D_get0(WebViewScreenElement webviewscreenelement)
    {
        return webviewscreenelement.mCachePage;
    }

    static android.view.ViewGroup.LayoutParams _2D_get1(WebViewScreenElement webviewscreenelement)
    {
        return webviewscreenelement.mLayoutParams;
    }

    static boolean _2D_get2(WebViewScreenElement webviewscreenelement)
    {
        return webviewscreenelement.mViewAdded;
    }

    static WebView _2D_get3(WebViewScreenElement webviewscreenelement)
    {
        return webviewscreenelement.mWebView;
    }

    static boolean _2D_set0(WebViewScreenElement webviewscreenelement, boolean flag)
    {
        webviewscreenelement.mViewAdded = flag;
        return flag;
    }

    static boolean _2D_wrap0(WebViewScreenElement webviewscreenelement, android.view.ViewGroup.LayoutParams layoutparams)
    {
        return webviewscreenelement.updateLayoutParams(layoutparams);
    }

    public WebViewScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mUseNetwork = 2;
        mWindowContext = screenelementroot.getContext().mContext;
        mWebView = new WebView(mWindowContext);
        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView webview, String s)
            {
                webview.loadUrl(s);
                return true;
            }

            final WebViewScreenElement this$0;

            
            {
                this$0 = WebViewScreenElement.this;
                super();
            }
        }
);
        mWebView.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                mRoot.onUIInteractive(WebViewScreenElement.this, "touch");
                return false;
            }

            final WebViewScreenElement this$0;

            
            {
                this$0 = WebViewScreenElement.this;
                super();
            }
        }
);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setInitialScale(100);
        screenelementroot = element.getAttribute("userAgent");
        if(!TextUtils.isEmpty(screenelementroot))
            mWebView.getSettings().setUserAgentString(screenelementroot);
        mWebView.addJavascriptInterface(new MamlInterface(null), "maml");
        mLayoutParams = new android.view.ViewGroup.LayoutParams(-1, -1);
        mHandler = getContext().getHandler();
        screenelementroot = getVariables();
        Expression expression = Expression.build(screenelementroot, element.getAttribute("uriExp"));
        mUriFormatter = new TextFormatter(screenelementroot, element.getAttribute("uri"), expression);
        mCachePage = Boolean.parseBoolean(element.getAttribute("cachePage"));
        element = element.getAttribute("useNetwork");
        if(TextUtils.isEmpty(element) || "all".equalsIgnoreCase(element))
            mUseNetwork = 2;
        else
        if("wifi".equalsIgnoreCase(element))
            mUseNetwork = 1;
        else
            mUseNetworkExp = Expression.build(screenelementroot, element);
    }

    private boolean canUseNetwork()
    {
        if(mUseNetwork == 2)
            return true;
        return mUseNetwork == 1 && ConnectivityHelper.getInstance().isWifiConnected();
    }

    private final void finishWebView()
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                mRoot.getViewManager().removeView(WebViewScreenElement._2D_get3(WebViewScreenElement.this));
                WebViewScreenElement._2D_set0(WebViewScreenElement.this, false);
                if(WebViewScreenElement._2D_get0(WebViewScreenElement.this))
                    WebViewScreenElement._2D_get3(WebViewScreenElement.this).onPause();
                else
                    WebViewScreenElement._2D_get3(WebViewScreenElement.this).loadUrl("about:blank");
            }

            final WebViewScreenElement this$0;

            
            {
                this$0 = WebViewScreenElement.this;
                super();
            }
        }
);
    }

    private final void initWebView()
    {
        if(!mViewAdded || mCachePage)
            mHandler.post(new Runnable() {

                public void run()
                {
                    if(WebViewScreenElement._2D_get2(WebViewScreenElement.this)) goto _L2; else goto _L1
_L1:
                    WebViewScreenElement._2D_wrap0(WebViewScreenElement.this, WebViewScreenElement._2D_get1(WebViewScreenElement.this));
                    Log.d("MAML WebViewScreenElement", "addWebView");
                    mRoot.getViewManager().addView(WebViewScreenElement._2D_get3(WebViewScreenElement.this), WebViewScreenElement._2D_get1(WebViewScreenElement.this));
                    WebViewScreenElement._2D_set0(WebViewScreenElement.this, true);
_L4:
                    return;
_L2:
                    if(WebViewScreenElement._2D_get0(WebViewScreenElement.this))
                        WebViewScreenElement._2D_get3(WebViewScreenElement.this).onResume();
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final WebViewScreenElement this$0;

            
            {
                this$0 = WebViewScreenElement.this;
                super();
            }
            }
);
    }

    private void pauseWebView(final boolean pause)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                if(pause)
                    WebViewScreenElement._2D_get3(WebViewScreenElement.this).onPause();
                else
                    WebViewScreenElement._2D_get3(WebViewScreenElement.this).onResume();
            }

            final WebViewScreenElement this$0;
            final boolean val$pause;

            
            {
                this$0 = WebViewScreenElement.this;
                pause = flag;
                super();
            }
        }
);
    }

    private boolean updateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        boolean flag = false;
        int i = (int)getWidth();
        if(layoutparams.width != i)
        {
            layoutparams.width = i;
            flag = true;
        }
        i = (int)getHeight();
        if(layoutparams.height != i)
        {
            layoutparams.height = i;
            flag = true;
        }
        return flag;
    }

    private final void updateView()
    {
        if(mViewAdded)
        {
            float f = getAbsoluteLeft();
            mWebView.setX(f);
            f = getAbsoluteTop();
            mWebView.setY(f);
            if(updateLayoutParams(mLayoutParams))
                mWebView.setLayoutParams(mLayoutParams);
        }
    }

    protected void doRender(Canvas canvas)
    {
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        String s = mUriFormatter.getText();
        if(!TextUtils.isEmpty(s) && TextUtils.equals(mCurUrl, s) ^ true)
        {
            Log.d("MAML WebViewScreenElement", (new StringBuilder()).append("loadUrl: ").append(s).toString());
            loadUrl(s);
        }
        updateView();
    }

    public void finish()
    {
        super.finish();
        finishWebView();
        if(!mCachePage)
            mCurUrl = null;
    }

    public void goBack()
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                WebViewScreenElement._2D_get3(WebViewScreenElement.this).goBack();
            }

            final WebViewScreenElement this$0;

            
            {
                this$0 = WebViewScreenElement.this;
                super();
            }
        }
);
    }

    public void init()
    {
        super.init();
        if(mUseNetworkExp != null)
            mUseNetwork = (int)mUseNetworkExp.evaluate();
        if(mRoot.getViewManager() != null)
            initWebView();
        else
            Log.e("MAML WebViewScreenElement", "ViewManager must be set before init");
    }

    public void loadUrl(final String url)
    {
        if(!canUseNetwork() && url.startsWith("http"))
        {
            Log.d("MAML WebViewScreenElement", (new StringBuilder()).append("loadUrl canceled due to useNetwork setting.").append(url).toString());
            return;
        } else
        {
            mCurUrl = url;
            mHandler.post(new Runnable() {

                public void run()
                {
                    WebViewScreenElement._2D_get3(WebViewScreenElement.this).loadUrl(url);
                }

                final WebViewScreenElement this$0;
                final String val$url;

            
            {
                this$0 = WebViewScreenElement.this;
                url = s;
                super();
            }
            }
);
            return;
        }
    }

    protected void onVisibilityChange(final boolean _v)
    {
        super.onVisibilityChange(_v);
        mHandler.post(new Runnable() {

            public void run()
            {
                WebView webview = WebViewScreenElement._2D_get3(WebViewScreenElement.this);
                int i;
                if(_v)
                    i = 0;
                else
                    i = 4;
                webview.setVisibility(i);
            }

            final WebViewScreenElement this$0;
            final boolean val$_v;

            
            {
                this$0 = WebViewScreenElement.this;
                _v = flag;
                super();
            }
        }
);
    }

    public void pause()
    {
        super.pause();
        if(mViewAdded)
            pauseWebView(true);
    }

    public void reload()
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                WebViewScreenElement._2D_get3(WebViewScreenElement.this).reload();
            }

            final WebViewScreenElement this$0;

            
            {
                this$0 = WebViewScreenElement.this;
                super();
            }
        }
);
    }

    public void render(Canvas canvas)
    {
    }

    public void resume()
    {
        super.resume();
        if(mViewAdded)
            pauseWebView(false);
    }

    public void runjs(final String jsfun)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                WebViewScreenElement._2D_get3(WebViewScreenElement.this).loadUrl((new StringBuilder()).append("javascript:").append(jsfun).toString());
            }

            final WebViewScreenElement this$0;
            final String val$jsfun;

            
            {
                this$0 = WebViewScreenElement.this;
                jsfun = s;
                super();
            }
        }
);
    }

    private static final String LOG_TAG = "MAML WebViewScreenElement";
    public static final String TAG_NAME = "WebView";
    private static final int USE_NETWORK_ALL = 2;
    private static final int USE_NETWORK_WIFI = 1;
    private boolean mCachePage;
    private String mCurUrl;
    private Handler mHandler;
    private android.view.ViewGroup.LayoutParams mLayoutParams;
    private TextFormatter mUriFormatter;
    private int mUseNetwork;
    private Expression mUseNetworkExp;
    private boolean mViewAdded;
    private WebView mWebView;
    private Context mWindowContext;
}
