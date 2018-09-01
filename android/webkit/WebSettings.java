// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;

// Referenced classes of package android.webkit:
//            WebViewFactory, WebViewFactoryProvider

public abstract class WebSettings
{
    public static final class LayoutAlgorithm extends Enum
    {

        public static LayoutAlgorithm valueOf(String s)
        {
            return (LayoutAlgorithm)Enum.valueOf(android/webkit/WebSettings$LayoutAlgorithm, s);
        }

        public static LayoutAlgorithm[] values()
        {
            return $VALUES;
        }

        private static final LayoutAlgorithm $VALUES[];
        public static final LayoutAlgorithm NARROW_COLUMNS;
        public static final LayoutAlgorithm NORMAL;
        public static final LayoutAlgorithm SINGLE_COLUMN;
        public static final LayoutAlgorithm TEXT_AUTOSIZING;

        static 
        {
            NORMAL = new LayoutAlgorithm("NORMAL", 0);
            SINGLE_COLUMN = new LayoutAlgorithm("SINGLE_COLUMN", 1);
            NARROW_COLUMNS = new LayoutAlgorithm("NARROW_COLUMNS", 2);
            TEXT_AUTOSIZING = new LayoutAlgorithm("TEXT_AUTOSIZING", 3);
            $VALUES = (new LayoutAlgorithm[] {
                NORMAL, SINGLE_COLUMN, NARROW_COLUMNS, TEXT_AUTOSIZING
            });
        }

        private LayoutAlgorithm(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class PluginState extends Enum
    {

        public static PluginState valueOf(String s)
        {
            return (PluginState)Enum.valueOf(android/webkit/WebSettings$PluginState, s);
        }

        public static PluginState[] values()
        {
            return $VALUES;
        }

        private static final PluginState $VALUES[];
        public static final PluginState OFF;
        public static final PluginState ON;
        public static final PluginState ON_DEMAND;

        static 
        {
            ON = new PluginState("ON", 0);
            ON_DEMAND = new PluginState("ON_DEMAND", 1);
            OFF = new PluginState("OFF", 2);
            $VALUES = (new PluginState[] {
                ON, ON_DEMAND, OFF
            });
        }

        private PluginState(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class RenderPriority extends Enum
    {

        public static RenderPriority valueOf(String s)
        {
            return (RenderPriority)Enum.valueOf(android/webkit/WebSettings$RenderPriority, s);
        }

        public static RenderPriority[] values()
        {
            return $VALUES;
        }

        private static final RenderPriority $VALUES[];
        public static final RenderPriority HIGH;
        public static final RenderPriority LOW;
        public static final RenderPriority NORMAL;

        static 
        {
            NORMAL = new RenderPriority("NORMAL", 0);
            HIGH = new RenderPriority("HIGH", 1);
            LOW = new RenderPriority("LOW", 2);
            $VALUES = (new RenderPriority[] {
                NORMAL, HIGH, LOW
            });
        }

        private RenderPriority(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class TextSize extends Enum
    {

        public static TextSize valueOf(String s)
        {
            return (TextSize)Enum.valueOf(android/webkit/WebSettings$TextSize, s);
        }

        public static TextSize[] values()
        {
            return $VALUES;
        }

        private static final TextSize $VALUES[];
        public static final TextSize LARGER;
        public static final TextSize LARGEST;
        public static final TextSize NORMAL;
        public static final TextSize SMALLER;
        public static final TextSize SMALLEST;
        int value;

        static 
        {
            SMALLEST = new TextSize("SMALLEST", 0, 50);
            SMALLER = new TextSize("SMALLER", 1, 75);
            NORMAL = new TextSize("NORMAL", 2, 100);
            LARGER = new TextSize("LARGER", 3, 150);
            LARGEST = new TextSize("LARGEST", 4, 200);
            $VALUES = (new TextSize[] {
                SMALLEST, SMALLER, NORMAL, LARGER, LARGEST
            });
        }

        private TextSize(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class ZoomDensity extends Enum
    {

        public static ZoomDensity valueOf(String s)
        {
            return (ZoomDensity)Enum.valueOf(android/webkit/WebSettings$ZoomDensity, s);
        }

        public static ZoomDensity[] values()
        {
            return $VALUES;
        }

        public int getValue()
        {
            return value;
        }

        private static final ZoomDensity $VALUES[];
        public static final ZoomDensity CLOSE;
        public static final ZoomDensity FAR;
        public static final ZoomDensity MEDIUM;
        int value;

        static 
        {
            FAR = new ZoomDensity("FAR", 0, 150);
            MEDIUM = new ZoomDensity("MEDIUM", 1, 100);
            CLOSE = new ZoomDensity("CLOSE", 2, 75);
            $VALUES = (new ZoomDensity[] {
                FAR, MEDIUM, CLOSE
            });
        }

        private ZoomDensity(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public WebSettings()
    {
    }

    public static String getDefaultUserAgent(Context context)
    {
        return WebViewFactory.getProvider().getStatics().getDefaultUserAgent(context);
    }

    public abstract boolean enableSmoothTransition();

    public abstract boolean getAcceptThirdPartyCookies();

    public abstract boolean getAllowContentAccess();

    public abstract boolean getAllowFileAccess();

    public abstract boolean getAllowFileAccessFromFileURLs();

    public abstract boolean getAllowUniversalAccessFromFileURLs();

    public abstract boolean getBlockNetworkImage();

    public abstract boolean getBlockNetworkLoads();

    public abstract boolean getBuiltInZoomControls();

    public abstract int getCacheMode();

    public abstract String getCursiveFontFamily();

    public abstract boolean getDatabaseEnabled();

    public abstract String getDatabasePath();

    public abstract int getDefaultFixedFontSize();

    public abstract int getDefaultFontSize();

    public abstract String getDefaultTextEncodingName();

    public abstract ZoomDensity getDefaultZoom();

    public abstract int getDisabledActionModeMenuItems();

    public abstract boolean getDisplayZoomControls();

    public abstract boolean getDomStorageEnabled();

    public abstract String getFantasyFontFamily();

    public abstract String getFixedFontFamily();

    public abstract boolean getJavaScriptCanOpenWindowsAutomatically();

    public abstract boolean getJavaScriptEnabled();

    public abstract LayoutAlgorithm getLayoutAlgorithm();

    public abstract boolean getLightTouchEnabled();

    public abstract boolean getLoadWithOverviewMode();

    public abstract boolean getLoadsImagesAutomatically();

    public abstract boolean getMediaPlaybackRequiresUserGesture();

    public abstract int getMinimumFontSize();

    public abstract int getMinimumLogicalFontSize();

    public abstract int getMixedContentMode();

    public abstract boolean getNavDump();

    public abstract boolean getOffscreenPreRaster();

    public abstract PluginState getPluginState();

    public abstract boolean getPluginsEnabled();

    public String getPluginsPath()
    {
        return "";
    }

    public abstract boolean getSafeBrowsingEnabled();

    public abstract String getSansSerifFontFamily();

    public abstract boolean getSaveFormData();

    public abstract boolean getSavePassword();

    public abstract String getSerifFontFamily();

    public abstract String getStandardFontFamily();

    public TextSize getTextSize()
    {
        int i = 0;
        this;
        JVM INSTR monitorenter ;
        TextSize textsize;
        int j;
        textsize = null;
        j = 0x7fffffff;
        int k;
        TextSize atextsize[];
        int l;
        k = getTextZoom();
        atextsize = TextSize.values();
        l = atextsize.length;
_L3:
        if(i >= l) goto _L2; else goto _L1
_L1:
        TextSize textsize1 = atextsize[i];
        int i1 = Math.abs(k - textsize1.value);
        if(i1 != 0)
            break MISSING_BLOCK_LABEL_60;
        this;
        JVM INSTR monitorexit ;
        return textsize1;
        int j1 = j;
        if(i1 < j)
        {
            j1 = i1;
            textsize = textsize1;
        }
        i++;
        j = j1;
          goto _L3
_L2:
        if(textsize == null) goto _L5; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return textsize;
_L5:
        textsize = TextSize.NORMAL;
        if(true) goto _L4; else goto _L6
_L6:
        Exception exception;
        exception;
        throw exception;
    }

    public abstract int getTextZoom();

    public boolean getUseDoubleTree()
    {
        return false;
    }

    public abstract boolean getUseWebViewBackgroundForOverscrollBackground();

    public abstract boolean getUseWideViewPort();

    public abstract int getUserAgent();

    public abstract String getUserAgentString();

    public abstract boolean getVideoOverlayForEmbeddedEncryptedVideoEnabled();

    public abstract void setAcceptThirdPartyCookies(boolean flag);

    public abstract void setAllowContentAccess(boolean flag);

    public abstract void setAllowFileAccess(boolean flag);

    public abstract void setAllowFileAccessFromFileURLs(boolean flag);

    public abstract void setAllowUniversalAccessFromFileURLs(boolean flag);

    public abstract void setAppCacheEnabled(boolean flag);

    public abstract void setAppCacheMaxSize(long l);

    public abstract void setAppCachePath(String s);

    public abstract void setBlockNetworkImage(boolean flag);

    public abstract void setBlockNetworkLoads(boolean flag);

    public abstract void setBuiltInZoomControls(boolean flag);

    public abstract void setCacheMode(int i);

    public abstract void setCursiveFontFamily(String s);

    public abstract void setDatabaseEnabled(boolean flag);

    public abstract void setDatabasePath(String s);

    public abstract void setDefaultFixedFontSize(int i);

    public abstract void setDefaultFontSize(int i);

    public abstract void setDefaultTextEncodingName(String s);

    public abstract void setDefaultZoom(ZoomDensity zoomdensity);

    public abstract void setDisabledActionModeMenuItems(int i);

    public abstract void setDisplayZoomControls(boolean flag);

    public abstract void setDomStorageEnabled(boolean flag);

    public abstract void setEnableSmoothTransition(boolean flag);

    public abstract void setFantasyFontFamily(String s);

    public abstract void setFixedFontFamily(String s);

    public abstract void setGeolocationDatabasePath(String s);

    public abstract void setGeolocationEnabled(boolean flag);

    public abstract void setJavaScriptCanOpenWindowsAutomatically(boolean flag);

    public abstract void setJavaScriptEnabled(boolean flag);

    public abstract void setLayoutAlgorithm(LayoutAlgorithm layoutalgorithm);

    public abstract void setLightTouchEnabled(boolean flag);

    public abstract void setLoadWithOverviewMode(boolean flag);

    public abstract void setLoadsImagesAutomatically(boolean flag);

    public abstract void setMediaPlaybackRequiresUserGesture(boolean flag);

    public abstract void setMinimumFontSize(int i);

    public abstract void setMinimumLogicalFontSize(int i);

    public abstract void setMixedContentMode(int i);

    public abstract void setNavDump(boolean flag);

    public abstract void setNeedInitialFocus(boolean flag);

    public abstract void setOffscreenPreRaster(boolean flag);

    public abstract void setPluginState(PluginState pluginstate);

    public abstract void setPluginsEnabled(boolean flag);

    public void setPluginsPath(String s)
    {
    }

    public abstract void setRenderPriority(RenderPriority renderpriority);

    public abstract void setSafeBrowsingEnabled(boolean flag);

    public abstract void setSansSerifFontFamily(String s);

    public abstract void setSaveFormData(boolean flag);

    public abstract void setSavePassword(boolean flag);

    public abstract void setSerifFontFamily(String s);

    public abstract void setStandardFontFamily(String s);

    public abstract void setSupportMultipleWindows(boolean flag);

    public abstract void setSupportZoom(boolean flag);

    public void setTextSize(TextSize textsize)
    {
        this;
        JVM INSTR monitorenter ;
        setTextZoom(textsize.value);
        this;
        JVM INSTR monitorexit ;
        return;
        textsize;
        throw textsize;
    }

    public abstract void setTextZoom(int i);

    public void setUseDoubleTree(boolean flag)
    {
    }

    public abstract void setUseWebViewBackgroundForOverscrollBackground(boolean flag);

    public abstract void setUseWideViewPort(boolean flag);

    public abstract void setUserAgent(int i);

    public abstract void setUserAgentString(String s);

    public abstract void setVideoOverlayForEmbeddedEncryptedVideoEnabled(boolean flag);

    public abstract boolean supportMultipleWindows();

    public abstract boolean supportZoom();

    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;
    public static final int MENU_ITEM_NONE = 0;
    public static final int MENU_ITEM_PROCESS_TEXT = 4;
    public static final int MENU_ITEM_SHARE = 1;
    public static final int MENU_ITEM_WEB_SEARCH = 2;
    public static final int MIXED_CONTENT_ALWAYS_ALLOW = 0;
    public static final int MIXED_CONTENT_COMPATIBILITY_MODE = 2;
    public static final int MIXED_CONTENT_NEVER_ALLOW = 1;
}
