// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.app.AppGlobals;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.SparseArray;

// Referenced classes of package android.view:
//            WindowManager, Display, ViewConfigurationInjector, WindowManagerGlobal, 
//            IWindowManager

public class ViewConfiguration
{

    public ViewConfiguration()
    {
        mEdgeSlop = 12;
        mFadingEdgeLength = 12;
        mMinimumFlingVelocity = 50;
        mMaximumFlingVelocity = 8000;
        mScrollbarSize = 4;
        mTouchSlop = 8;
        mMinScrollbarTouchTarget = 48;
        mDoubleTapTouchSlop = 8;
        mPagingTouchSlop = 16;
        mDoubleTapSlop = 100;
        mWindowTouchSlop = 16;
        mMaximumDrawingCacheSize = 0x177000;
        mOverscrollDistance = 0;
        mOverflingDistance = 6;
        mFadingMarqueeEnabled = true;
        mGlobalActionsKeyTimeout = 500L;
        mHorizontalScrollFactor = 64F;
        mVerticalScrollFactor = 64F;
    }

    private ViewConfiguration(Context context)
    {
        Resources resources;
        resources = context.getResources();
        Object obj = resources.getDisplayMetrics();
        Object obj1 = resources.getConfiguration();
        float f = ((DisplayMetrics) (obj)).density;
        if(((Configuration) (obj1)).isLayoutSizeAtLeast(4))
            f *= 1.5F;
        mEdgeSlop = (int)(12F * f + 0.5F);
        mFadingEdgeLength = (int)(12F * f + 0.5F);
        mScrollbarSize = resources.getDimensionPixelSize(0x1050044);
        mDoubleTapSlop = (int)(100F * f + 0.5F);
        mWindowTouchSlop = (int)(16F * f + 0.5F);
        obj1 = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        obj = new Point();
        ((Display) (obj1)).getRealSize(((Point) (obj)));
        mMaximumDrawingCacheSize = ((Point) (obj)).x * 4 * ((Point) (obj)).y;
        mOverscrollDistance = ViewConfigurationInjector.getOverScrollDistance(context, (int)(0.0F * f + 0.5F));
        mOverflingDistance = ViewConfigurationInjector.getOverFlingDistance(context, (int)(6F * f + 0.5F));
        if(sHasPermanentMenuKeySet) goto _L2; else goto _L1
_L1:
        resources.getInteger(0x10e008b);
        JVM INSTR tableswitch 0 2: default 220
    //                   0 220
    //                   1 362
    //                   2 375;
           goto _L3 _L3 _L4 _L5
_L3:
        context = WindowManagerGlobal.getWindowManagerService();
        try
        {
            sHasPermanentMenuKey = context.hasNavigationBar() ^ true;
            sHasPermanentMenuKeySet = true;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            sHasPermanentMenuKey = false;
        }
_L2:
        mFadingMarqueeEnabled = resources.getBoolean(0x11200bb);
        mTouchSlop = resources.getDimensionPixelSize(0x1050046);
        mMinScrollbarTouchTarget = resources.getDimensionPixelSize(0x105003b);
        mPagingTouchSlop = mTouchSlop * 2;
        mDoubleTapTouchSlop = mTouchSlop;
        mMinimumFlingVelocity = resources.getDimensionPixelSize(0x1050048);
        mMaximumFlingVelocity = resources.getDimensionPixelSize(0x1050047);
        mGlobalActionsKeyTimeout = resources.getInteger(0x10e0057);
        mHorizontalScrollFactor = resources.getDimensionPixelSize(0x1050036);
        mVerticalScrollFactor = resources.getDimensionPixelSize(0x1050045);
        return;
_L4:
        sHasPermanentMenuKey = true;
        sHasPermanentMenuKeySet = true;
        continue; /* Loop/switch isn't completed */
_L5:
        sHasPermanentMenuKey = false;
        sHasPermanentMenuKeySet = true;
        if(true) goto _L2; else goto _L6
_L6:
    }

    public static ViewConfiguration get(Context context)
    {
        ViewConfiguration viewconfiguration = ViewConfigurationInjector.get(context);
        if(viewconfiguration != null)
            return viewconfiguration;
        if(ViewConfigurationInjector.needMiuiConfiguration(context))
        {
            viewconfiguration = new ViewConfiguration(context);
            ViewConfigurationInjector.put(context, viewconfiguration);
            return viewconfiguration;
        }
        int i = (int)(context.getResources().getDisplayMetrics().density * 100F);
        ViewConfiguration viewconfiguration1 = (ViewConfiguration)sConfigurations.get(i);
        viewconfiguration = viewconfiguration1;
        if(viewconfiguration1 == null)
        {
            viewconfiguration = new ViewConfiguration(context);
            sConfigurations.put(i, viewconfiguration);
        }
        return viewconfiguration;
    }

    public static long getDefaultActionModeHideDuration()
    {
        return 2000L;
    }

    public static int getDoubleTapMinTime()
    {
        return 40;
    }

    public static int getDoubleTapSlop()
    {
        return 100;
    }

    public static int getDoubleTapTimeout()
    {
        return 300;
    }

    public static int getEdgeSlop()
    {
        return 12;
    }

    public static int getFadingEdgeLength()
    {
        return 12;
    }

    public static long getGlobalActionKeyTimeout()
    {
        return 500L;
    }

    public static int getHoverTapSlop()
    {
        return 20;
    }

    public static int getHoverTapTimeout()
    {
        return 150;
    }

    public static int getHoverTooltipHideShortTimeout()
    {
        return 3000;
    }

    public static int getHoverTooltipHideTimeout()
    {
        return 15000;
    }

    public static int getHoverTooltipShowTimeout()
    {
        return 500;
    }

    public static int getJumpTapTimeout()
    {
        return 500;
    }

    public static int getKeyRepeatDelay()
    {
        return 50;
    }

    public static int getKeyRepeatTimeout()
    {
        return getLongPressTimeout();
    }

    public static int getLongPressTimeout()
    {
        return AppGlobals.getIntCoreSetting("long_press_timeout", 500);
    }

    public static int getLongPressTooltipHideTimeout()
    {
        return 1500;
    }

    public static int getMaximumDrawingCacheSize()
    {
        return 0x177000;
    }

    public static int getMaximumFlingVelocity()
    {
        return 8000;
    }

    public static int getMinimumFlingVelocity()
    {
        return 50;
    }

    public static int getMultiPressTimeout()
    {
        return AppGlobals.getIntCoreSetting("multi_press_timeout", 300);
    }

    public static int getPressedStateDuration()
    {
        return 64;
    }

    public static int getScrollBarFadeDuration()
    {
        return 250;
    }

    public static int getScrollBarSize()
    {
        return 4;
    }

    public static int getScrollDefaultDelay()
    {
        return 300;
    }

    public static float getScrollFriction()
    {
        return 0.015F;
    }

    public static long getSendRecurringAccessibilityEventsInterval()
    {
        return 100L;
    }

    public static int getTapTimeout()
    {
        return 100;
    }

    public static int getTouchSlop()
    {
        return 8;
    }

    public static int getWindowTouchSlop()
    {
        return 16;
    }

    public static long getZoomControlsTimeout()
    {
        return 3000L;
    }

    public long getAccessibilityShortcutKeyTimeout()
    {
        return 3000L;
    }

    public long getDeviceGlobalActionKeyTimeout()
    {
        return mGlobalActionsKeyTimeout;
    }

    public int getScaledDoubleTapSlop()
    {
        return mDoubleTapSlop;
    }

    public int getScaledDoubleTapTouchSlop()
    {
        return mDoubleTapTouchSlop;
    }

    public int getScaledEdgeSlop()
    {
        return mEdgeSlop;
    }

    public int getScaledFadingEdgeLength()
    {
        return mFadingEdgeLength;
    }

    public float getScaledHorizontalScrollFactor()
    {
        return mHorizontalScrollFactor;
    }

    public int getScaledMaximumDrawingCacheSize()
    {
        return mMaximumDrawingCacheSize;
    }

    public int getScaledMaximumFlingVelocity()
    {
        return mMaximumFlingVelocity;
    }

    public int getScaledMinScrollbarTouchTarget()
    {
        return mMinScrollbarTouchTarget;
    }

    public int getScaledMinimumFlingVelocity()
    {
        return mMinimumFlingVelocity;
    }

    public int getScaledOverflingDistance()
    {
        return mOverflingDistance;
    }

    public int getScaledOverscrollDistance()
    {
        return mOverscrollDistance;
    }

    public int getScaledPagingTouchSlop()
    {
        return mPagingTouchSlop;
    }

    public int getScaledScrollBarSize()
    {
        return mScrollbarSize;
    }

    public int getScaledScrollFactor()
    {
        return (int)mVerticalScrollFactor;
    }

    public int getScaledTouchSlop()
    {
        return mTouchSlop;
    }

    public float getScaledVerticalScrollFactor()
    {
        return mVerticalScrollFactor;
    }

    public int getScaledWindowTouchSlop()
    {
        return mWindowTouchSlop;
    }

    public boolean hasPermanentMenuKey()
    {
        return sHasPermanentMenuKey;
    }

    public boolean isFadingMarqueeEnabled()
    {
        return mFadingMarqueeEnabled;
    }

    private static final int A11Y_SHORTCUT_KEY_TIMEOUT = 3000;
    private static final long ACTION_MODE_HIDE_DURATION_DEFAULT = 2000L;
    private static final int DEFAULT_LONG_PRESS_TIMEOUT = 500;
    private static final int DEFAULT_MULTI_PRESS_TIMEOUT = 300;
    private static final int DOUBLE_TAP_MIN_TIME = 40;
    private static final int DOUBLE_TAP_SLOP = 100;
    private static final int DOUBLE_TAP_TIMEOUT = 300;
    private static final int DOUBLE_TAP_TOUCH_SLOP = 8;
    private static final int EDGE_SLOP = 12;
    private static final int FADING_EDGE_LENGTH = 12;
    private static final int GLOBAL_ACTIONS_KEY_TIMEOUT = 500;
    private static final int HAS_PERMANENT_MENU_KEY_AUTODETECT = 0;
    private static final int HAS_PERMANENT_MENU_KEY_FALSE = 2;
    private static final int HAS_PERMANENT_MENU_KEY_TRUE = 1;
    private static final float HORIZONTAL_SCROLL_FACTOR = 64F;
    private static final int HOVER_TAP_SLOP = 20;
    private static final int HOVER_TAP_TIMEOUT = 150;
    private static final int HOVER_TOOLTIP_HIDE_SHORT_TIMEOUT = 3000;
    private static final int HOVER_TOOLTIP_HIDE_TIMEOUT = 15000;
    private static final int HOVER_TOOLTIP_SHOW_TIMEOUT = 500;
    private static final int JUMP_TAP_TIMEOUT = 500;
    private static final int KEY_REPEAT_DELAY = 50;
    private static final int LONG_PRESS_TOOLTIP_HIDE_TIMEOUT = 1500;
    private static final int MAXIMUM_DRAWING_CACHE_SIZE = 0x177000;
    private static final int MAXIMUM_FLING_VELOCITY = 8000;
    private static final int MINIMUM_FLING_VELOCITY = 50;
    private static final int MIN_SCROLLBAR_TOUCH_TARGET = 48;
    private static final int OVERFLING_DISTANCE = 6;
    private static final int OVERSCROLL_DISTANCE = 0;
    private static final int PAGING_TOUCH_SLOP = 16;
    private static final int PRESSED_STATE_DURATION = 64;
    private static final int SCROLL_BAR_DEFAULT_DELAY = 300;
    private static final int SCROLL_BAR_FADE_DURATION = 250;
    private static final int SCROLL_BAR_SIZE = 4;
    private static final float SCROLL_FRICTION = 0.015F;
    private static final long SEND_RECURRING_ACCESSIBILITY_EVENTS_INTERVAL_MILLIS = 100L;
    private static final int TAP_TIMEOUT = 100;
    private static final int TOUCH_SLOP = 8;
    private static final float VERTICAL_SCROLL_FACTOR = 64F;
    private static final int WINDOW_TOUCH_SLOP = 16;
    private static final int ZOOM_CONTROLS_TIMEOUT = 3000;
    static final SparseArray sConfigurations = new SparseArray(2);
    private final int mDoubleTapSlop;
    private final int mDoubleTapTouchSlop;
    private final int mEdgeSlop;
    private final int mFadingEdgeLength;
    private final boolean mFadingMarqueeEnabled;
    private final long mGlobalActionsKeyTimeout;
    private final float mHorizontalScrollFactor;
    private final int mMaximumDrawingCacheSize;
    private final int mMaximumFlingVelocity;
    private final int mMinScrollbarTouchTarget;
    private final int mMinimumFlingVelocity;
    private final int mOverflingDistance;
    private final int mOverscrollDistance;
    private final int mPagingTouchSlop;
    private final int mScrollbarSize;
    private final int mTouchSlop;
    private final float mVerticalScrollFactor;
    private final int mWindowTouchSlop;
    private boolean sHasPermanentMenuKey;
    private boolean sHasPermanentMenuKeySet;

}
