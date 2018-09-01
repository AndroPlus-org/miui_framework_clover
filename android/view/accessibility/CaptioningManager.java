// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import java.util.*;

public class CaptioningManager
{
    public static final class CaptionStyle
    {

        public static CaptionStyle getCustomStyle(ContentResolver contentresolver)
        {
            CaptionStyle captionstyle = DEFAULT_CUSTOM;
            int i = android.provider.Settings.Secure.getInt(contentresolver, "accessibility_captioning_foreground_color", captionstyle.foregroundColor);
            int j = android.provider.Settings.Secure.getInt(contentresolver, "accessibility_captioning_background_color", captionstyle.backgroundColor);
            int k = android.provider.Settings.Secure.getInt(contentresolver, "accessibility_captioning_edge_type", captionstyle.edgeType);
            int l = android.provider.Settings.Secure.getInt(contentresolver, "accessibility_captioning_edge_color", captionstyle.edgeColor);
            int i1 = android.provider.Settings.Secure.getInt(contentresolver, "accessibility_captioning_window_color", captionstyle.windowColor);
            String s = android.provider.Settings.Secure.getString(contentresolver, "accessibility_captioning_typeface");
            contentresolver = s;
            if(s == null)
                contentresolver = captionstyle.mRawTypeface;
            return new CaptionStyle(i, j, k, l, i1, contentresolver);
        }

        public static boolean hasColor(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i >>> 24 == 0)
                if((0xffff00 & i) == 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public CaptionStyle applyStyle(CaptionStyle captionstyle)
        {
            int i;
            int j;
            int k;
            int l;
            int i1;
            if(captionstyle.hasForegroundColor())
                i = captionstyle.foregroundColor;
            else
                i = foregroundColor;
            if(captionstyle.hasBackgroundColor())
                j = captionstyle.backgroundColor;
            else
                j = backgroundColor;
            if(captionstyle.hasEdgeType())
                k = captionstyle.edgeType;
            else
                k = edgeType;
            if(captionstyle.hasEdgeColor())
                l = captionstyle.edgeColor;
            else
                l = edgeColor;
            if(captionstyle.hasWindowColor())
                i1 = captionstyle.windowColor;
            else
                i1 = windowColor;
            if(captionstyle.mRawTypeface != null)
                captionstyle = captionstyle.mRawTypeface;
            else
                captionstyle = mRawTypeface;
            return new CaptionStyle(i, j, k, l, i1, captionstyle);
        }

        public Typeface getTypeface()
        {
            if(mParsedTypeface == null && TextUtils.isEmpty(mRawTypeface) ^ true)
                mParsedTypeface = Typeface.create(mRawTypeface, 0);
            return mParsedTypeface;
        }

        public boolean hasBackgroundColor()
        {
            return mHasBackgroundColor;
        }

        public boolean hasEdgeColor()
        {
            return mHasEdgeColor;
        }

        public boolean hasEdgeType()
        {
            return mHasEdgeType;
        }

        public boolean hasForegroundColor()
        {
            return mHasForegroundColor;
        }

        public boolean hasWindowColor()
        {
            return mHasWindowColor;
        }

        private static final CaptionStyle BLACK_ON_WHITE;
        private static final int COLOR_NONE_OPAQUE = 255;
        public static final int COLOR_UNSPECIFIED = 0xffffff;
        public static final CaptionStyle DEFAULT;
        private static final CaptionStyle DEFAULT_CUSTOM;
        public static final int EDGE_TYPE_DEPRESSED = 4;
        public static final int EDGE_TYPE_DROP_SHADOW = 2;
        public static final int EDGE_TYPE_NONE = 0;
        public static final int EDGE_TYPE_OUTLINE = 1;
        public static final int EDGE_TYPE_RAISED = 3;
        public static final int EDGE_TYPE_UNSPECIFIED = -1;
        public static final CaptionStyle PRESETS[];
        public static final int PRESET_CUSTOM = -1;
        private static final CaptionStyle UNSPECIFIED;
        private static final CaptionStyle WHITE_ON_BLACK;
        private static final CaptionStyle YELLOW_ON_BLACK;
        private static final CaptionStyle YELLOW_ON_BLUE;
        public final int backgroundColor;
        public final int edgeColor;
        public final int edgeType;
        public final int foregroundColor;
        private final boolean mHasBackgroundColor;
        private final boolean mHasEdgeColor;
        private final boolean mHasEdgeType;
        private final boolean mHasForegroundColor;
        private final boolean mHasWindowColor;
        private Typeface mParsedTypeface;
        public final String mRawTypeface;
        public final int windowColor;

        static 
        {
            WHITE_ON_BLACK = new CaptionStyle(-1, 0xff000000, 0, 0xff000000, 255, null);
            BLACK_ON_WHITE = new CaptionStyle(0xff000000, -1, 0, 0xff000000, 255, null);
            YELLOW_ON_BLACK = new CaptionStyle(-256, 0xff000000, 0, 0xff000000, 255, null);
            YELLOW_ON_BLUE = new CaptionStyle(-256, 0xff0000ff, 0, 0xff000000, 255, null);
            UNSPECIFIED = new CaptionStyle(0xffffff, 0xffffff, -1, 0xffffff, 0xffffff, null);
            PRESETS = (new CaptionStyle[] {
                WHITE_ON_BLACK, BLACK_ON_WHITE, YELLOW_ON_BLACK, YELLOW_ON_BLUE, UNSPECIFIED
            });
            DEFAULT_CUSTOM = WHITE_ON_BLACK;
            DEFAULT = WHITE_ON_BLACK;
        }

        private CaptionStyle(int i, int j, int k, int l, int i1, String s)
        {
            mHasForegroundColor = hasColor(i);
            mHasBackgroundColor = hasColor(j);
            boolean flag;
            if(k != -1)
                flag = true;
            else
                flag = false;
            mHasEdgeType = flag;
            mHasEdgeColor = hasColor(l);
            mHasWindowColor = hasColor(i1);
            if(!mHasForegroundColor)
                i = -1;
            foregroundColor = i;
            if(!mHasBackgroundColor)
                j = 0xff000000;
            backgroundColor = j;
            if(!mHasEdgeType)
                k = 0;
            edgeType = k;
            if(!mHasEdgeColor)
                l = 0xff000000;
            edgeColor = l;
            if(!mHasWindowColor)
                i1 = 255;
            windowColor = i1;
            mRawTypeface = s;
        }
    }

    public static abstract class CaptioningChangeListener
    {

        public void onEnabledChanged(boolean flag)
        {
        }

        public void onFontScaleChanged(float f)
        {
        }

        public void onLocaleChanged(Locale locale)
        {
        }

        public void onUserStyleChanged(CaptionStyle captionstyle)
        {
        }

        public CaptioningChangeListener()
        {
        }
    }

    private class MyContentObserver extends ContentObserver
    {

        public void onChange(boolean flag, Uri uri)
        {
            uri = uri.getPath();
            uri = uri.substring(uri.lastIndexOf('/') + 1);
            if("accessibility_captioning_enabled".equals(uri))
                CaptioningManager._2D_wrap0(CaptioningManager.this);
            else
            if("accessibility_captioning_locale".equals(uri))
                CaptioningManager._2D_wrap2(CaptioningManager.this);
            else
            if("accessibility_captioning_font_scale".equals(uri))
            {
                CaptioningManager._2D_wrap1(CaptioningManager.this);
            } else
            {
                mHandler.removeCallbacks(CaptioningManager._2D_get0(CaptioningManager.this));
                mHandler.post(CaptioningManager._2D_get0(CaptioningManager.this));
            }
        }

        private final Handler mHandler;
        final CaptioningManager this$0;

        public MyContentObserver(Handler handler)
        {
            this$0 = CaptioningManager.this;
            super(handler);
            mHandler = handler;
        }
    }


    static Runnable _2D_get0(CaptioningManager captioningmanager)
    {
        return captioningmanager.mStyleChangedRunnable;
    }

    static void _2D_wrap0(CaptioningManager captioningmanager)
    {
        captioningmanager.notifyEnabledChanged();
    }

    static void _2D_wrap1(CaptioningManager captioningmanager)
    {
        captioningmanager.notifyFontScaleChanged();
    }

    static void _2D_wrap2(CaptioningManager captioningmanager)
    {
        captioningmanager.notifyLocaleChanged();
    }

    static void _2D_wrap3(CaptioningManager captioningmanager)
    {
        captioningmanager.notifyUserStyleChanged();
    }

    public CaptioningManager(Context context)
    {
        mContentResolver = context.getContentResolver();
        mContentObserver = new MyContentObserver(new Handler(context.getMainLooper()));
    }

    private void notifyEnabledChanged()
    {
        boolean flag = isEnabled();
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((CaptioningChangeListener)iterator.next()).onEnabledChanged(flag));
        break MISSING_BLOCK_LABEL_52;
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    private void notifyFontScaleChanged()
    {
        float f = getFontScale();
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((CaptioningChangeListener)iterator.next()).onFontScaleChanged(f));
        break MISSING_BLOCK_LABEL_52;
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    private void notifyLocaleChanged()
    {
        Locale locale = getLocale();
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((CaptioningChangeListener)iterator.next()).onLocaleChanged(locale));
        break MISSING_BLOCK_LABEL_52;
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    private void notifyUserStyleChanged()
    {
        CaptionStyle captionstyle = getUserStyle();
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((CaptioningChangeListener)iterator.next()).onUserStyleChanged(captionstyle));
        break MISSING_BLOCK_LABEL_52;
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    private void registerObserver(String s)
    {
        mContentResolver.registerContentObserver(android.provider.Settings.Secure.getUriFor(s), false, mContentObserver);
    }

    public void addCaptioningChangeListener(CaptioningChangeListener captioningchangelistener)
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        if(mListeners.isEmpty())
        {
            registerObserver("accessibility_captioning_enabled");
            registerObserver("accessibility_captioning_foreground_color");
            registerObserver("accessibility_captioning_background_color");
            registerObserver("accessibility_captioning_window_color");
            registerObserver("accessibility_captioning_edge_type");
            registerObserver("accessibility_captioning_edge_color");
            registerObserver("accessibility_captioning_typeface");
            registerObserver("accessibility_captioning_font_scale");
            registerObserver("accessibility_captioning_locale");
            registerObserver("accessibility_captioning_preset");
        }
        mListeners.add(captioningchangelistener);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        captioningchangelistener;
        throw captioningchangelistener;
    }

    public final float getFontScale()
    {
        return android.provider.Settings.Secure.getFloat(mContentResolver, "accessibility_captioning_font_scale", 1.0F);
    }

    public final Locale getLocale()
    {
        String s = getRawLocale();
        if(TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        String as[] = s.split("_");
        as.length;
        JVM INSTR tableswitch 1 3: default 48
    //                   1 81
    //                   2 67
    //                   3 50;
           goto _L2 _L3 _L4 _L5
_L2:
        return null;
_L5:
        return new Locale(as[0], as[1], as[2]);
_L4:
        return new Locale(as[0], as[1]);
_L3:
        return new Locale(as[0]);
    }

    public final String getRawLocale()
    {
        return android.provider.Settings.Secure.getString(mContentResolver, "accessibility_captioning_locale");
    }

    public int getRawUserStyle()
    {
        return android.provider.Settings.Secure.getInt(mContentResolver, "accessibility_captioning_preset", 0);
    }

    public CaptionStyle getUserStyle()
    {
        int i = getRawUserStyle();
        if(i == -1)
            return CaptionStyle.getCustomStyle(mContentResolver);
        else
            return CaptionStyle.PRESETS[i];
    }

    public final boolean isEnabled()
    {
        boolean flag = true;
        if(android.provider.Settings.Secure.getInt(mContentResolver, "accessibility_captioning_enabled", 0) != 1)
            flag = false;
        return flag;
    }

    public void removeCaptioningChangeListener(CaptioningChangeListener captioningchangelistener)
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        mListeners.remove(captioningchangelistener);
        if(mListeners.isEmpty())
            mContentResolver.unregisterContentObserver(mContentObserver);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        captioningchangelistener;
        throw captioningchangelistener;
    }

    private static final int DEFAULT_ENABLED = 0;
    private static final float DEFAULT_FONT_SCALE = 1F;
    private static final int DEFAULT_PRESET = 0;
    private final ContentObserver mContentObserver;
    private final ContentResolver mContentResolver;
    private final ArrayList mListeners = new ArrayList();
    private final Runnable mStyleChangedRunnable = new Runnable() {

        public void run()
        {
            CaptioningManager._2D_wrap3(CaptioningManager.this);
        }

        final CaptioningManager this$0;

            
            {
                this$0 = CaptioningManager.this;
                super();
            }
    }
;
}
