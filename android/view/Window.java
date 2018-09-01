// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.*;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.*;
import android.transition.*;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

// Referenced classes of package android.view:
//            MotionEvent, ViewConfiguration, View, WindowManager, 
//            WindowManagerImpl, LayoutInflater, InputEvent, KeyEvent, 
//            ActionMode, Menu, MenuItem, SearchEvent, 
//            FrameMetrics

public abstract class Window
{
    public static interface Callback
    {

        public abstract boolean dispatchGenericMotionEvent(MotionEvent motionevent);

        public abstract boolean dispatchKeyEvent(KeyEvent keyevent);

        public abstract boolean dispatchKeyShortcutEvent(KeyEvent keyevent);

        public abstract boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent);

        public abstract boolean dispatchTouchEvent(MotionEvent motionevent);

        public abstract boolean dispatchTrackballEvent(MotionEvent motionevent);

        public abstract void onActionModeFinished(ActionMode actionmode);

        public abstract void onActionModeStarted(ActionMode actionmode);

        public abstract void onAttachedToWindow();

        public abstract void onContentChanged();

        public abstract boolean onCreatePanelMenu(int i, Menu menu);

        public abstract View onCreatePanelView(int i);

        public abstract void onDetachedFromWindow();

        public abstract boolean onMenuItemSelected(int i, MenuItem menuitem);

        public abstract boolean onMenuOpened(int i, Menu menu);

        public abstract void onPanelClosed(int i, Menu menu);

        public void onPointerCaptureChanged(boolean flag)
        {
        }

        public abstract boolean onPreparePanel(int i, View view, Menu menu);

        public void onProvideKeyboardShortcuts(List list, Menu menu, int i)
        {
        }

        public abstract boolean onSearchRequested();

        public abstract boolean onSearchRequested(SearchEvent searchevent);

        public abstract void onWindowAttributesChanged(WindowManager.LayoutParams layoutparams);

        public abstract void onWindowFocusChanged(boolean flag);

        public abstract ActionMode onWindowStartingActionMode(ActionMode.Callback callback);

        public abstract ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i);
    }

    public static interface OnFrameMetricsAvailableListener
    {

        public abstract void onFrameMetricsAvailable(Window window, FrameMetrics framemetrics, int i);
    }

    public static interface OnRestrictedCaptionAreaChangedListener
    {

        public abstract void onRestrictedCaptionAreaChanged(Rect rect);
    }

    public static interface OnWindowDismissedCallback
    {

        public abstract void onWindowDismissed(boolean flag, boolean flag1);
    }

    public static interface OnWindowSwipeDismissedCallback
    {

        public abstract void onWindowSwipeDismissed();
    }

    public static interface WindowControllerCallback
    {

        public abstract void enterPictureInPictureModeIfPossible();

        public abstract void exitFreeformMode()
            throws RemoteException;

        public abstract int getWindowStackId()
            throws RemoteException;

        public abstract boolean isTaskRoot();
    }


    public Window(Context context)
    {
        mIsActive = false;
        mHasChildren = false;
        mCloseOnTouchOutside = false;
        mSetCloseOnTouchOutside = false;
        mForcedWindowFlags = 0;
        mHaveWindowFormat = false;
        mHaveDimAmount = false;
        mDefaultWindowFormat = -1;
        mHasSoftInputMode = false;
        mOverlayWithDecorCaptionEnabled = false;
        mCloseOnSwipeEnabled = false;
        mContext = context;
        int i = getDefaultFeatures(context);
        mLocalFeatures = i;
        mFeatures = i;
    }

    public static int getDefaultFeatures(Context context)
    {
        int i = 0;
        context = context.getResources();
        if(context.getBoolean(0x112003c))
            i = 1;
        int j = i;
        if(context.getBoolean(0x112003b))
            j = i | 0x40;
        return j;
    }

    private boolean isOutOfBounds(Context context, MotionEvent motionevent)
    {
        boolean flag;
        int i;
        int j;
        int k;
        boolean flag1;
        flag = true;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        k = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        context = getDecorView();
        flag1 = flag;
        if(i < -k) goto _L2; else goto _L1
_L1:
        if(j >= -k) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i <= context.getWidth() + k)
        {
            flag1 = flag;
            if(j <= context.getHeight() + k)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void setPrivateFlags(int i, int j)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.privateFlags = layoutparams.privateFlags & j | i & j;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutparams);

    public void addExtraFlags(int i)
    {
        setExtraFlags(i, i);
    }

    public void addFlags(int i)
    {
        setFlags(i, i);
    }

    public final void addOnFrameMetricsAvailableListener(OnFrameMetricsAvailableListener onframemetricsavailablelistener, Handler handler)
    {
        View view = getDecorView();
        if(view == null)
            throw new IllegalStateException("can't observe a Window without an attached view");
        if(onframemetricsavailablelistener == null)
        {
            throw new NullPointerException("listener cannot be null");
        } else
        {
            view.addFrameMetricsListener(this, onframemetricsavailablelistener, handler);
            return;
        }
    }

    public void addPrivateFlags(int i)
    {
        setPrivateFlags(i, i);
    }

    void adjustLayoutParamsForSubWindow(WindowManager.LayoutParams layoutparams)
    {
        CharSequence charsequence = layoutparams.getTitle();
        if(layoutparams.type < 1000 || layoutparams.type > 1999) goto _L2; else goto _L1
_L1:
        if(layoutparams.token == null)
        {
            View view = peekDecorView();
            if(view != null)
                layoutparams.token = view.getWindowToken();
        }
        if(charsequence == null || charsequence.length() == 0)
        {
            StringBuilder stringbuilder = new StringBuilder(32);
            if(layoutparams.type == 1001)
                stringbuilder.append("Media");
            else
            if(layoutparams.type == 1004)
                stringbuilder.append("MediaOvr");
            else
            if(layoutparams.type == 1000)
                stringbuilder.append("Panel");
            else
            if(layoutparams.type == 1002)
                stringbuilder.append("SubPanel");
            else
            if(layoutparams.type == 1005)
                stringbuilder.append("AboveSubPanel");
            else
            if(layoutparams.type == 1003)
                stringbuilder.append("AtchDlg");
            else
                stringbuilder.append(layoutparams.type);
            if(mAppName != null)
                stringbuilder.append(":").append(mAppName);
            layoutparams.setTitle(stringbuilder);
        }
_L4:
        if(layoutparams.packageName == null)
            layoutparams.packageName = mContext.getPackageName();
        if(mHardwareAccelerated || (mWindowAttributes.flags & 0x1000000) != 0)
            layoutparams.flags = layoutparams.flags | 0x1000000;
        return;
_L2:
        if(layoutparams.type >= 2000 && layoutparams.type <= 2999)
        {
            if(charsequence == null || charsequence.length() == 0)
            {
                StringBuilder stringbuilder1 = new StringBuilder(32);
                stringbuilder1.append("Sys").append(layoutparams.type);
                if(mAppName != null)
                    stringbuilder1.append(":").append(mAppName);
                layoutparams.setTitle(stringbuilder1);
            }
        } else
        {
            if(layoutparams.token == null)
            {
                IBinder ibinder;
                if(mContainer == null)
                    ibinder = mAppToken;
                else
                    ibinder = mContainer.mAppToken;
                layoutparams.token = ibinder;
            }
            if((charsequence == null || charsequence.length() == 0) && mAppName != null)
                layoutparams.setTitle(mAppName);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public abstract void alwaysReadCloseOnTouchAttr();

    public abstract void clearContentView();

    public void clearExtraFlags(int i)
    {
        setExtraFlags(0, i);
    }

    public void clearFlags(int i)
    {
        setFlags(0, i);
    }

    public abstract void closeAllPanels();

    public abstract void closePanel(int i);

    public final void destroy()
    {
        mDestroyed = true;
    }

    public final void dispatchOnWindowDismissed(boolean flag, boolean flag1)
    {
        if(mOnWindowDismissedCallback != null)
            mOnWindowDismissedCallback.onWindowDismissed(flag, flag1);
    }

    public final void dispatchOnWindowSwipeDismissed()
    {
        if(mOnWindowSwipeDismissedCallback != null)
            mOnWindowSwipeDismissedCallback.onWindowSwipeDismissed();
    }

    protected void dispatchWindowAttributesChanged(WindowManager.LayoutParams layoutparams)
    {
        if(mCallback != null)
            mCallback.onWindowAttributesChanged(layoutparams);
    }

    public View findViewById(int i)
    {
        return getDecorView().findViewById(i);
    }

    public boolean getAllowEnterTransitionOverlap()
    {
        return true;
    }

    public boolean getAllowReturnTransitionOverlap()
    {
        return true;
    }

    public final WindowManager.LayoutParams getAttributes()
    {
        return mWindowAttributes;
    }

    public final Callback getCallback()
    {
        return mCallback;
    }

    public int getColorMode()
    {
        return getAttributes().getColorMode();
    }

    public final Window getContainer()
    {
        return mContainer;
    }

    public Scene getContentScene()
    {
        return null;
    }

    public final Context getContext()
    {
        return mContext;
    }

    public abstract View getCurrentFocus();

    public abstract View getDecorView();

    public float getElevation()
    {
        return 0.0F;
    }

    public Transition getEnterTransition()
    {
        return null;
    }

    public Transition getExitTransition()
    {
        return null;
    }

    protected final int getFeatures()
    {
        return mFeatures;
    }

    protected final int getForcedWindowFlags()
    {
        return mForcedWindowFlags;
    }

    public abstract LayoutInflater getLayoutInflater();

    protected final int getLocalFeatures()
    {
        return mLocalFeatures;
    }

    public MediaController getMediaController()
    {
        return null;
    }

    public abstract int getNavigationBarColor();

    public Transition getReenterTransition()
    {
        return null;
    }

    public Transition getReturnTransition()
    {
        return null;
    }

    public Transition getSharedElementEnterTransition()
    {
        return null;
    }

    public Transition getSharedElementExitTransition()
    {
        return null;
    }

    public Transition getSharedElementReenterTransition()
    {
        return null;
    }

    public Transition getSharedElementReturnTransition()
    {
        return null;
    }

    public boolean getSharedElementsUseOverlay()
    {
        return true;
    }

    public abstract int getStatusBarColor();

    public long getTransitionBackgroundFadeDuration()
    {
        return 0L;
    }

    public TransitionManager getTransitionManager()
    {
        return null;
    }

    public abstract int getVolumeControlStream();

    public final WindowControllerCallback getWindowControllerCallback()
    {
        return mWindowControllerCallback;
    }

    public WindowManager getWindowManager()
    {
        return mWindowManager;
    }

    public final TypedArray getWindowStyle()
    {
        this;
        JVM INSTR monitorenter ;
        TypedArray typedarray;
        if(mWindowStyle == null)
            mWindowStyle = mContext.obtainStyledAttributes(com.android.internal.R.styleable.Window);
        typedarray = mWindowStyle;
        this;
        JVM INSTR monitorexit ;
        return typedarray;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean hasChildren()
    {
        return mHasChildren;
    }

    public boolean hasFeature(int i)
    {
        boolean flag = true;
        if((getFeatures() & 1 << i) == 0)
            flag = false;
        return flag;
    }

    protected final boolean hasSoftInputMode()
    {
        return mHasSoftInputMode;
    }

    protected boolean haveDimAmount()
    {
        return mHaveDimAmount;
    }

    public void injectInputEvent(InputEvent inputevent)
    {
    }

    public abstract void invalidatePanelMenu(int i);

    public final boolean isActive()
    {
        return mIsActive;
    }

    public boolean isCloseOnSwipeEnabled()
    {
        return mCloseOnSwipeEnabled;
    }

    public final boolean isDestroyed()
    {
        return mDestroyed;
    }

    public abstract boolean isFloating();

    public boolean isOverlayWithDecorCaptionEnabled()
    {
        return mOverlayWithDecorCaptionEnabled;
    }

    public abstract boolean isShortcutKey(int i, KeyEvent keyevent);

    public boolean isWideColorGamut()
    {
        boolean flag;
        if(getColorMode() == 1)
            flag = getContext().getResources().getConfiguration().isScreenWideColorGamut();
        else
            flag = false;
        return flag;
    }

    public final void makeActive()
    {
        if(mContainer != null)
        {
            if(mContainer.mActiveChild != null)
                mContainer.mActiveChild.mIsActive = false;
            mContainer.mActiveChild = this;
        }
        mIsActive = true;
        onActive();
    }

    public void notifyRestrictedCaptionAreaCallback(int i, int j, int k, int l)
    {
        if(mOnRestrictedCaptionAreaChangedListener != null)
        {
            mRestrictedCaptionAreaRect.set(i, j, k, l);
            mOnRestrictedCaptionAreaChangedListener.onRestrictedCaptionAreaChanged(mRestrictedCaptionAreaRect);
        }
    }

    protected abstract void onActive();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onMultiWindowModeChanged();

    public abstract void onPictureInPictureModeChanged(boolean flag);

    public abstract void openPanel(int i, KeyEvent keyevent);

    public abstract View peekDecorView();

    public abstract boolean performContextMenuIdentifierAction(int i, int j);

    public abstract boolean performPanelIdentifierAction(int i, int j, int k);

    public abstract boolean performPanelShortcut(int i, int j, KeyEvent keyevent, int k);

    protected void removeFeature(int i)
    {
        int j = 1 << i;
        mFeatures = mFeatures & j;
        int k = mLocalFeatures;
        i = j;
        if(mContainer != null)
            i = j & mContainer.mFeatures;
        mLocalFeatures = k & i;
    }

    public final void removeOnFrameMetricsAvailableListener(OnFrameMetricsAvailableListener onframemetricsavailablelistener)
    {
        if(getDecorView() != null)
            getDecorView().removeFrameMetricsListener(onframemetricsavailablelistener);
    }

    public abstract void reportActivityRelaunched();

    public boolean requestFeature(int i)
    {
        int j = 1 << i;
        mFeatures = mFeatures | j;
        int k = mLocalFeatures;
        boolean flag;
        if(mContainer != null)
            i = mContainer.mFeatures & j;
        else
            i = j;
        mLocalFeatures = i | k;
        if((mFeatures & j) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public abstract void restoreHierarchyState(Bundle bundle);

    public abstract Bundle saveHierarchyState();

    public void setAllowEnterTransitionOverlap(boolean flag)
    {
    }

    public void setAllowReturnTransitionOverlap(boolean flag)
    {
    }

    public void setAttributes(WindowManager.LayoutParams layoutparams)
    {
        mWindowAttributes.copyFrom(layoutparams);
        dispatchWindowAttributesChanged(mWindowAttributes);
    }

    public abstract void setBackgroundDrawable(Drawable drawable);

    public void setBackgroundDrawableResource(int i)
    {
        setBackgroundDrawable(mContext.getDrawable(i));
    }

    public void setCallback(Callback callback)
    {
        mCallback = callback;
    }

    public abstract void setChildDrawable(int i, Drawable drawable);

    public abstract void setChildInt(int i, int j);

    public void setClipToOutline(boolean flag)
    {
    }

    public void setCloseOnSwipeEnabled(boolean flag)
    {
        mCloseOnSwipeEnabled = flag;
    }

    public void setCloseOnTouchOutside(boolean flag)
    {
        mCloseOnTouchOutside = flag;
        mSetCloseOnTouchOutside = true;
    }

    public void setCloseOnTouchOutsideIfNotSet(boolean flag)
    {
        if(!mSetCloseOnTouchOutside)
        {
            mCloseOnTouchOutside = flag;
            mSetCloseOnTouchOutside = true;
        }
    }

    public void setColorMode(int i)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.setColorMode(i);
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setContainer(Window window)
    {
        mContainer = window;
        if(window != null)
        {
            mFeatures = mFeatures | 2;
            mLocalFeatures = mLocalFeatures | 2;
            window.mHasChildren = true;
        }
    }

    public abstract void setContentView(int i);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutparams);

    public abstract void setDecorCaptionShade(int i);

    public void setDefaultIcon(int i)
    {
    }

    public void setDefaultLogo(int i)
    {
    }

    protected void setDefaultWindowFormat(int i)
    {
        mDefaultWindowFormat = i;
        if(!mHaveWindowFormat)
        {
            WindowManager.LayoutParams layoutparams = getAttributes();
            layoutparams.format = i;
            dispatchWindowAttributesChanged(layoutparams);
        }
    }

    public void setDimAmount(float f)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.dimAmount = f;
        mHaveDimAmount = true;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setDisableWallpaperTouchEvents(boolean flag)
    {
        char c;
        if(flag)
            c = '\u0800';
        else
            c = '\0';
        setPrivateFlags(c, 2048);
    }

    public void setElevation(float f)
    {
    }

    public void setEnterTransition(Transition transition)
    {
    }

    public void setExitTransition(Transition transition)
    {
    }

    public void setExtraFlags(int i, int j)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.extraFlags = layoutparams.extraFlags & j | i & j;
        if(mCallback != null)
            mCallback.onWindowAttributesChanged(layoutparams);
    }

    public abstract void setFeatureDrawable(int i, Drawable drawable);

    public abstract void setFeatureDrawableAlpha(int i, int j);

    public abstract void setFeatureDrawableResource(int i, int j);

    public abstract void setFeatureDrawableUri(int i, Uri uri);

    public abstract void setFeatureInt(int i, int j);

    public void setFlags(int i, int j)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.flags = layoutparams.flags & j | i & j;
        mForcedWindowFlags = mForcedWindowFlags | j;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setFormat(int i)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        if(i != 0)
        {
            layoutparams.format = i;
            mHaveWindowFormat = true;
        } else
        {
            layoutparams.format = mDefaultWindowFormat;
            mHaveWindowFormat = false;
        }
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setGravity(int i)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.gravity = i;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setIcon(int i)
    {
    }

    public void setLayout(int i, int j)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.width = i;
        layoutparams.height = j;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setLocalFocus(boolean flag, boolean flag1)
    {
    }

    public void setLogo(int i)
    {
    }

    public void setMediaController(MediaController mediacontroller)
    {
    }

    public abstract void setNavigationBarColor(int i);

    protected void setNeedsMenuKey(int i)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.needsMenuKey = i;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public final void setOnWindowDismissedCallback(OnWindowDismissedCallback onwindowdismissedcallback)
    {
        mOnWindowDismissedCallback = onwindowdismissedcallback;
    }

    public final void setOnWindowSwipeDismissedCallback(OnWindowSwipeDismissedCallback onwindowswipedismissedcallback)
    {
        mOnWindowSwipeDismissedCallback = onwindowswipedismissedcallback;
    }

    public void setOverlayWithDecorCaptionEnabled(boolean flag)
    {
        mOverlayWithDecorCaptionEnabled = flag;
    }

    public void setReenterTransition(Transition transition)
    {
    }

    public abstract void setResizingCaptionDrawable(Drawable drawable);

    public final void setRestrictedCaptionAreaListener(OnRestrictedCaptionAreaChangedListener onrestrictedcaptionareachangedlistener)
    {
        Rect rect = null;
        mOnRestrictedCaptionAreaChangedListener = onrestrictedcaptionareachangedlistener;
        if(onrestrictedcaptionareachangedlistener != null)
            rect = new Rect();
        mRestrictedCaptionAreaRect = rect;
    }

    public void setReturnTransition(Transition transition)
    {
    }

    public void setSharedElementEnterTransition(Transition transition)
    {
    }

    public void setSharedElementExitTransition(Transition transition)
    {
    }

    public void setSharedElementReenterTransition(Transition transition)
    {
    }

    public void setSharedElementReturnTransition(Transition transition)
    {
    }

    public void setSharedElementsUseOverlay(boolean flag)
    {
    }

    public void setSoftInputMode(int i)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        if(i != 0)
        {
            layoutparams.softInputMode = i;
            mHasSoftInputMode = true;
        } else
        {
            mHasSoftInputMode = false;
        }
        dispatchWindowAttributesChanged(layoutparams);
    }

    public abstract void setStatusBarColor(int i);

    public void setSustainedPerformanceMode(boolean flag)
    {
        int i;
        if(flag)
            i = 0x40000;
        else
            i = 0;
        setPrivateFlags(i, 0x40000);
    }

    public void setTheme(int i)
    {
    }

    public abstract void setTitle(CharSequence charsequence);

    public abstract void setTitleColor(int i);

    public void setTransitionBackgroundFadeDuration(long l)
    {
    }

    public void setTransitionManager(TransitionManager transitionmanager)
    {
        throw new UnsupportedOperationException();
    }

    public void setType(int i)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.type = i;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setUiOptions(int i)
    {
    }

    public void setUiOptions(int i, int j)
    {
    }

    public abstract void setVolumeControlStream(int i);

    public void setWindowAnimations(int i)
    {
        WindowManager.LayoutParams layoutparams = getAttributes();
        layoutparams.windowAnimations = i;
        dispatchWindowAttributesChanged(layoutparams);
    }

    public final void setWindowControllerCallback(WindowControllerCallback windowcontrollercallback)
    {
        mWindowControllerCallback = windowcontrollercallback;
    }

    public void setWindowManager(WindowManager windowmanager, IBinder ibinder, String s)
    {
        setWindowManager(windowmanager, ibinder, s, false);
    }

    public void setWindowManager(WindowManager windowmanager, IBinder ibinder, String s, boolean flag)
    {
        mAppToken = ibinder;
        mAppName = s;
        if(!flag)
            flag = SystemProperties.getBoolean("persist.sys.ui.hw", false);
        else
            flag = true;
        mHardwareAccelerated = flag;
        ibinder = windowmanager;
        if(windowmanager == null)
            ibinder = (WindowManager)mContext.getSystemService("window");
        mWindowManager = ((WindowManagerImpl)ibinder).createLocalWindowManager(this);
    }

    public boolean shouldCloseOnTouch(Context context, MotionEvent motionevent)
    {
        boolean flag;
        if(motionevent.getAction() != 0 || !isOutOfBounds(context, motionevent))
        {
            if(motionevent.getAction() == 4)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return mCloseOnTouchOutside && peekDecorView() != null && flag;
    }

    public abstract boolean superDispatchGenericMotionEvent(MotionEvent motionevent);

    public abstract boolean superDispatchKeyEvent(KeyEvent keyevent);

    public abstract boolean superDispatchKeyShortcutEvent(KeyEvent keyevent);

    public abstract boolean superDispatchTouchEvent(MotionEvent motionevent);

    public abstract boolean superDispatchTrackballEvent(MotionEvent motionevent);

    public abstract void takeInputQueue(InputQueue.Callback callback);

    public abstract void takeKeyEvents(boolean flag);

    public abstract void takeSurface(SurfaceHolder.Callback2 callback2);

    public abstract void togglePanel(int i, KeyEvent keyevent);

    public static final int DECOR_CAPTION_SHADE_AUTO = 0;
    public static final int DECOR_CAPTION_SHADE_DARK = 2;
    public static final int DECOR_CAPTION_SHADE_LIGHT = 1;
    protected static final int DEFAULT_FEATURES = 65;
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_ACTIVITY_TRANSITIONS = 13;
    public static final int FEATURE_CONTENT_TRANSITIONS = 12;
    public static final int FEATURE_CONTEXT_MENU = 6;
    public static final int FEATURE_CUSTOM_TITLE = 7;
    public static final int FEATURE_INDETERMINATE_PROGRESS = 5;
    public static final int FEATURE_LEFT_ICON = 3;
    public static final int FEATURE_MAX = 13;
    public static final int FEATURE_NO_TITLE = 1;
    public static final int FEATURE_OPTIONS_PANEL = 0;
    public static final int FEATURE_PROGRESS = 2;
    public static final int FEATURE_RIGHT_ICON = 4;
    public static final int FEATURE_SWIPE_TO_DISMISS = 11;
    public static final int ID_ANDROID_CONTENT = 0x1020002;
    public static final String NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME = "android:navigation:background";
    public static final int PROGRESS_END = 10000;
    public static final int PROGRESS_INDETERMINATE_OFF = -4;
    public static final int PROGRESS_INDETERMINATE_ON = -3;
    public static final int PROGRESS_SECONDARY_END = 30000;
    public static final int PROGRESS_SECONDARY_START = 20000;
    public static final int PROGRESS_START = 0;
    public static final int PROGRESS_VISIBILITY_OFF = -2;
    public static final int PROGRESS_VISIBILITY_ON = -1;
    private static final String PROPERTY_HARDWARE_UI = "persist.sys.ui.hw";
    public static final String STATUS_BAR_BACKGROUND_TRANSITION_NAME = "android:status:background";
    private Window mActiveChild;
    private String mAppName;
    private IBinder mAppToken;
    private Callback mCallback;
    private boolean mCloseOnSwipeEnabled;
    private boolean mCloseOnTouchOutside;
    private Window mContainer;
    private final Context mContext;
    private int mDefaultWindowFormat;
    private boolean mDestroyed;
    private int mFeatures;
    private int mForcedWindowFlags;
    private boolean mHardwareAccelerated;
    private boolean mHasChildren;
    private boolean mHasSoftInputMode;
    private boolean mHaveDimAmount;
    private boolean mHaveWindowFormat;
    private boolean mIsActive;
    private int mLocalFeatures;
    private OnRestrictedCaptionAreaChangedListener mOnRestrictedCaptionAreaChangedListener;
    private OnWindowDismissedCallback mOnWindowDismissedCallback;
    private OnWindowSwipeDismissedCallback mOnWindowSwipeDismissedCallback;
    private boolean mOverlayWithDecorCaptionEnabled;
    private Rect mRestrictedCaptionAreaRect;
    private boolean mSetCloseOnTouchOutside;
    private final WindowManager.LayoutParams mWindowAttributes = new WindowManager.LayoutParams();
    private WindowControllerCallback mWindowControllerCallback;
    private WindowManager mWindowManager;
    private TypedArray mWindowStyle;
}
