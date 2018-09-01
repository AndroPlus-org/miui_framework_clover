// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.*;
import android.util.Slog;
import android.view.animation.Animation;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IShortcutService;
import java.io.PrintWriter;

// Referenced classes of package android.view:
//            KeyEvent, IWindowManager, Display, MotionEvent, 
//            IApplicationToken

public interface WindowManagerPolicy
{
    public static interface InputConsumer
    {

        public abstract void dismiss();
    }

    public static interface OnKeyguardExitResult
    {

        public abstract void onKeyguardExitResult(boolean flag);
    }

    public static interface PointerEventListener
    {

        public abstract void onPointerEvent(MotionEvent motionevent);

        public void onPointerEvent(MotionEvent motionevent, int i)
        {
            if(i == 0)
                onPointerEvent(motionevent);
        }
    }

    public static interface ScreenOffListener
    {

        public abstract void onScreenOff();
    }

    public static interface ScreenOnListener
    {

        public abstract void onScreenOn();
    }

    public static interface StartingSurface
    {

        public abstract void remove();
    }

    public static interface WindowManagerFuncs
    {

        public abstract InputConsumer createInputConsumer(Looper looper, String s, InputEventReceiver.Factory factory);

        public abstract int getCameraLensCoverState();

        public abstract int getDockedDividerInsetsLw();

        public abstract WindowState getInputMethodWindowLw();

        public abstract int getLidState();

        public abstract void getStackBounds(int i, Rect rect);

        public abstract Object getWindowManagerLock();

        public abstract void lockDeviceNow();

        public abstract void notifyKeyguardTrustedChanged();

        public abstract void notifyShowingDreamChanged();

        public abstract void reboot(boolean flag);

        public abstract void rebootSafeMode(boolean flag);

        public abstract void reevaluateStatusBarVisibility();

        public abstract void registerPointerEventListener(PointerEventListener pointereventlistener);

        public abstract void screenTurningOff(ScreenOffListener screenofflistener);

        public abstract void shutdown(boolean flag);

        public abstract void switchInputMethod(boolean flag);

        public abstract void unregisterPointerEventListener(PointerEventListener pointereventlistener);

        public static final int CAMERA_LENS_COVERED = 1;
        public static final int CAMERA_LENS_COVER_ABSENT = -1;
        public static final int CAMERA_LENS_UNCOVERED = 0;
        public static final int LID_ABSENT = -1;
        public static final int LID_CLOSED = 0;
        public static final int LID_OPEN = 1;
    }

    public static interface WindowState
    {

        public abstract boolean canAcquireSleepToken();

        public boolean canAddInternalSystemWindow()
        {
            return false;
        }

        public abstract boolean canAffectSystemUiFlags();

        public abstract void computeFrameLw(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, Rect rect6, 
                Rect rect7);

        public abstract IApplicationToken getAppToken();

        public abstract WindowManager.LayoutParams getAttrs();

        public abstract int getBaseType();

        public abstract Rect getContentFrameLw();

        public abstract Rect getDisplayFrameLw();

        public abstract int getDisplayId();

        public abstract Rect getFrameLw();

        public abstract Rect getGivenContentInsetsLw();

        public abstract boolean getGivenInsetsPendingLw();

        public abstract Rect getGivenVisibleInsetsLw();

        public abstract boolean getNeedsMenuLw(WindowState windowstate);

        public abstract Rect getOverscanFrameLw();

        public abstract String getOwningPackage();

        public abstract int getOwningUid();

        public abstract int getRotationAnimationHint();

        public abstract Point getShownPositionLw();

        public abstract int getStackId();

        public abstract int getSurfaceLayer();

        public abstract int getSystemUiVisibility();

        public abstract Rect getVisibleFrameLw();

        public abstract boolean hasAppShownWindows();

        public abstract boolean hasDrawnLw();

        public abstract boolean hideLw(boolean flag);

        public abstract boolean isAlive();

        public abstract boolean isAnimatingLw();

        public abstract boolean isDefaultDisplay();

        public abstract boolean isDimming();

        public abstract boolean isDisplayedLw();

        public abstract boolean isDrawnLw();

        public abstract boolean isGoneForLayoutLw();

        public abstract boolean isInMultiWindowMode();

        public abstract boolean isInputMethodWindow();

        public abstract boolean isVisibleLw();

        public abstract boolean isVoiceInteraction();

        public abstract boolean showLw(boolean flag);
    }


    public abstract StartingSurface addSplashScreen(IBinder ibinder, String s, int i, CompatibilityInfo compatibilityinfo, CharSequence charsequence, int j, int k, 
            int l, int i1, Configuration configuration, int j1);

    public abstract void adjustConfigurationLw(Configuration configuration, int i, int j);

    public abstract int adjustSystemUiVisibilityLw(int i);

    public abstract void adjustWindowParamsLw(WindowManager.LayoutParams layoutparams);

    public abstract boolean allowAppAnimationsLw();

    public abstract void applyPostLayoutPolicyLw(WindowState windowstate, WindowManager.LayoutParams layoutparams, WindowState windowstate1, WindowState windowstate2);

    public abstract void beginLayoutLw(boolean flag, int i, int j, int k, int l);

    public abstract void beginPostLayoutPolicyLw(int i, int j);

    public abstract boolean canBeHiddenByKeyguardLw(WindowState windowstate);

    public abstract boolean canDismissBootAnimation();

    public abstract boolean canMagnifyWindow(int i);

    public abstract int checkAddPermission(WindowManager.LayoutParams layoutparams, int ai[]);

    public abstract boolean checkShowToOwnerOnly(WindowManager.LayoutParams layoutparams);

    public abstract Animation createHiddenByKeyguardExit(boolean flag, boolean flag1);

    public abstract Animation createKeyguardWallpaperExit(boolean flag);

    public abstract void dismissKeyguardLw(IKeyguardDismissCallback ikeyguarddismisscallback);

    public abstract KeyEvent dispatchUnhandledKey(WindowState windowstate, KeyEvent keyevent, int i);

    public abstract void dump(String s, PrintWriter printwriter, String as[]);

    public abstract void enableKeyguard(boolean flag);

    public abstract void enableScreenAfterBoot();

    public abstract void exitKeyguardSecurely(OnKeyguardExitResult onkeyguardexitresult);

    public abstract void finishLayoutLw();

    public abstract int finishPostLayoutPolicyLw();

    public abstract void finishedGoingToSleep(int i);

    public abstract void finishedWakingUp();

    public abstract int focusChangedLw(WindowState windowstate, WindowState windowstate1);

    public abstract int getConfigDisplayHeight(int i, int j, int k, int l, int i1);

    public abstract int getConfigDisplayWidth(int i, int j, int k, int l, int i1);

    public abstract void getContentRectLw(Rect rect);

    public abstract int getInputMethodWindowVisibleHeightLw();

    public abstract boolean getInsetHintLw(WindowManager.LayoutParams layoutparams, Rect rect, int i, int j, int k, Rect rect1, Rect rect2, 
            Rect rect3);

    public abstract int getMaxWallpaperLayer();

    public abstract int getNavBarPosition();

    public abstract int getNonDecorDisplayHeight(int i, int j, int k, int l, int i1);

    public abstract int getNonDecorDisplayWidth(int i, int j, int k, int l, int i1);

    public abstract void getNonDecorInsetsLw(int i, int j, int k, Rect rect);

    public abstract void getStableInsetsLw(int i, int j, int k, Rect rect);

    public int getSubWindowLayerFromTypeLw(int i)
    {
        switch(i)
        {
        default:
            Slog.e("WindowManager", (new StringBuilder()).append("Unknown sub-window type: ").append(i).toString());
            return 0;

        case 1000: 
        case 1003: 
            return 1;

        case 1001: 
            return -2;

        case 1004: 
            return -1;

        case 1002: 
            return 2;

        case 1005: 
            return 3;
        }
    }

    public abstract int getSystemDecorLayerLw();

    public abstract int getUserRotationMode();

    public int getWindowLayerFromTypeLw(int i)
    {
        if(WindowManager.LayoutParams.isSystemAlertWindowType(i))
            throw new IllegalArgumentException("Use getWindowLayerFromTypeLw() or getWindowLayerLw() for alert window types");
        else
            return getWindowLayerFromTypeLw(i, false);
    }

    public int getWindowLayerFromTypeLw(int i, boolean flag)
    {
        byte byte0 = 11;
        byte byte1 = 10;
        if(i >= 1 && i <= 99)
            return 2;
        switch(i)
        {
        case 2004: 
        case 2025: 
        case 2028: 
        case 2029: 
        default:
            Slog.e("WindowManager", (new StringBuilder()).append("Unknown window type: ").append(i).toString());
            return 2;

        case 2013: 
            return 1;

        case 2030: 
        case 2037: 
            return 2;

        case 2034: 
            return 2;

        case 2035: 
            return 2;

        case 2002: 
            return 3;

        case 2001: 
        case 2033: 
            return 4;

        case 2031: 
            return 5;

        case 2022: 
            return 6;

        case 2008: 
            return 7;

        case 2005: 
            return 8;

        case 2007: 
            return 9;

        case 2003: 
            if(!flag)
                byte0 = 10;
            return byte0;

        case 2038: 
            return 12;

        case 2023: 
            return 13;

        case 2011: 
            return 14;

        case 2012: 
            return 15;

        case 2017: 
            return 17;

        case 2000: 
            return 18;

        case 2014: 
            return 19;

        case 2009: 
            return 20;

        case 2020: 
            return 21;

        case 2006: 
            if(flag)
                byte0 = 22;
            return byte0;

        case 2019: 
            return 23;

        case 2024: 
            return 24;

        case 2036: 
            return 25;

        case 2010: 
            i = byte1;
            if(flag)
                i = 26;
            return i;

        case 2027: 
            return 27;

        case 2026: 
            return 28;

        case 2016: 
            return 29;

        case 2032: 
            return 30;

        case 2015: 
            return 31;

        case 2021: 
            return 32;

        case 2018: 
            return 33;
        }
    }

    public int getWindowLayerLw(WindowState windowstate)
    {
        return getWindowLayerFromTypeLw(windowstate.getBaseType(), windowstate.canAddInternalSystemWindow());
    }

    public abstract boolean hasNavigationBar();

    public abstract void hideBootMessages();

    public abstract boolean inKeyguardRestrictedKeyInputMode();

    public abstract void init(Context context, IWindowManager iwindowmanager, WindowManagerFuncs windowmanagerfuncs);

    public abstract long interceptKeyBeforeDispatching(WindowState windowstate, KeyEvent keyevent, int i);

    public abstract int interceptKeyBeforeQueueing(KeyEvent keyevent, int i);

    public abstract int interceptMotionBeforeQueueingNonInteractive(long l, int i);

    public abstract boolean isDefaultOrientationForced();

    public abstract boolean isDockSideAllowed(int i);

    public abstract boolean isKeyguardDrawnLw();

    public abstract boolean isKeyguardHostWindow(WindowManager.LayoutParams layoutparams);

    public abstract boolean isKeyguardLocked();

    public abstract boolean isKeyguardOccluded();

    public abstract boolean isKeyguardSecure(int i);

    public abstract boolean isKeyguardShowingAndNotOccluded();

    public abstract boolean isKeyguardTrustedLw();

    public abstract boolean isNavBarForcedShownLw(WindowState windowstate);

    public abstract boolean isScreenOn();

    public abstract boolean isShowingDreamLw();

    public abstract boolean isTopLevelWindow(int i);

    public abstract void keepScreenOnStartedLw();

    public abstract void keepScreenOnStoppedLw();

    public abstract void layoutWindowLw(WindowState windowstate, WindowState windowstate1);

    public abstract void lockNow(Bundle bundle);

    public abstract void notifyCameraLensCoverSwitchChanged(long l, boolean flag);

    public abstract void notifyLidSwitchChanged(long l, boolean flag);

    public abstract boolean okToAnimate();

    public abstract void onConfigurationChanged();

    public abstract void onKeyguardOccludedChangedLw(boolean flag);

    public abstract void onSystemUiStarted();

    public abstract boolean performHapticFeedbackLw(WindowState windowstate, int i, boolean flag);

    public abstract int prepareAddWindowLw(WindowState windowstate, WindowManager.LayoutParams layoutparams);

    public abstract void registerShortcutKey(long l, IShortcutService ishortcutservice)
        throws RemoteException;

    public abstract void removeWindowLw(WindowState windowstate);

    public abstract int rotationForOrientationLw(int i, int j);

    public abstract boolean rotationHasCompatibleMetricsLw(int i, int j);

    public abstract void screenTurnedOff();

    public abstract void screenTurnedOn();

    public abstract void screenTurningOff(ScreenOffListener screenofflistener);

    public abstract void screenTurningOn(ScreenOnListener screenonlistener);

    public abstract int selectAnimationLw(WindowState windowstate, int i);

    public abstract void selectRotationAnimationLw(int ai[]);

    public abstract void setCurrentOrientationLw(int i);

    public abstract void setCurrentUserLw(int i);

    public void setDismissImeOnBackKeyPressed(boolean flag)
    {
    }

    public abstract void setDisplayOverscan(Display display, int i, int j, int k, int l);

    public abstract void setInitialDisplaySize(Display display, int i, int j, int k);

    public abstract void setLastInputMethodWindowLw(WindowState windowstate, WindowState windowstate1);

    public abstract void setPipVisibilityLw(boolean flag);

    public abstract void setRecentsVisibilityLw(boolean flag);

    public abstract void setRotationLw(int i);

    public abstract void setSafeMode(boolean flag);

    public abstract void setSwitchingUser(boolean flag);

    public abstract void setUserRotationMode(int i, int j);

    public abstract boolean shouldRotateSeamlessly(int i, int j);

    public abstract void showBootMessage(CharSequence charsequence, boolean flag);

    public abstract void showGlobalActions();

    public abstract void showRecentApps(boolean flag);

    public abstract void startKeyguardExitAnimation(long l, long l1);

    public abstract void startedGoingToSleep(int i);

    public abstract void startedWakingUp();

    public abstract void systemBooted();

    public abstract void systemReady();

    public abstract void userActivity();

    public abstract boolean validateRotationAnimationLw(int i, int j, boolean flag);

    public static final String ACTION_HDMI_PLUGGED = "android.intent.action.HDMI_PLUGGED";
    public static final int ACTION_PASS_TO_USER = 1;
    public static final int APPLICATION_ABOVE_SUB_PANEL_SUBLAYER = 3;
    public static final int APPLICATION_LAYER = 2;
    public static final int APPLICATION_MEDIA_OVERLAY_SUBLAYER = -1;
    public static final int APPLICATION_MEDIA_SUBLAYER = -2;
    public static final int APPLICATION_PANEL_SUBLAYER = 1;
    public static final int APPLICATION_SUB_PANEL_SUBLAYER = 2;
    public static final String EXTRA_FROM_HOME_KEY = "android.intent.extra.FROM_HOME_KEY";
    public static final String EXTRA_HDMI_PLUGGED_STATE = "state";
    public static final int FINISH_LAYOUT_REDO_ANIM = 8;
    public static final int FINISH_LAYOUT_REDO_CONFIG = 2;
    public static final int FINISH_LAYOUT_REDO_LAYOUT = 1;
    public static final int FINISH_LAYOUT_REDO_WALLPAPER = 4;
    public static final int FLAG_DISABLE_KEY_REPEAT = 0x8000000;
    public static final int FLAG_FILTERED = 0x4000000;
    public static final int FLAG_INJECTED = 0x1000000;
    public static final int FLAG_INTERACTIVE = 0x20000000;
    public static final int FLAG_PASS_TO_USER = 0x40000000;
    public static final int FLAG_TRUSTED = 0x2000000;
    public static final int FLAG_VIRTUAL = 2;
    public static final int FLAG_WAKE = 1;
    public static final int KEYGUARD_GOING_AWAY_FLAG_NO_WINDOW_ANIMATIONS = 2;
    public static final int KEYGUARD_GOING_AWAY_FLAG_TO_SHADE = 1;
    public static final int KEYGUARD_GOING_AWAY_FLAG_WITH_WALLPAPER = 4;
    public static final int NAV_BAR_BOTTOM = 4;
    public static final int NAV_BAR_LEFT = 1;
    public static final int NAV_BAR_RIGHT = 2;
    public static final int OFF_BECAUSE_OF_ADMIN = 1;
    public static final int OFF_BECAUSE_OF_TIMEOUT = 3;
    public static final int OFF_BECAUSE_OF_USER = 2;
    public static final int PRESENCE_EXTERNAL = 2;
    public static final int PRESENCE_INTERNAL = 1;
    public static final int TRANSIT_ENTER = 1;
    public static final int TRANSIT_EXIT = 2;
    public static final int TRANSIT_HIDE = 4;
    public static final int TRANSIT_PREVIEW_DONE = 5;
    public static final int TRANSIT_SHOW = 3;
    public static final int USER_ROTATION_FREE = 0;
    public static final int USER_ROTATION_LOCKED = 1;
    public static final boolean WATCH_POINTER = false;
}
