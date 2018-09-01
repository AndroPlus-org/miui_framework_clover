// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.app.*;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.media.session.MediaSessionLegacyHelper;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.transition.*;
import android.util.*;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.android.internal.view.menu.*;
import com.android.internal.widget.DecorContentParent;
import com.android.internal.widget.SwipeDismissLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.policy:
//            DecorView, DecorContext, PhoneWindowInjector

public class PhoneWindow extends Window
    implements com.android.internal.view.menu.MenuBuilder.Callback
{
    private final class ActionMenuPresenterCallback
        implements com.android.internal.view.menu.MenuPresenter.Callback
    {

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            checkCloseActionMenu(menubuilder);
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            android.view.Window.Callback callback = getCallback();
            if(callback != null)
            {
                callback.onMenuOpened(8, menubuilder);
                return true;
            } else
            {
                return false;
            }
        }

        final PhoneWindow this$0;

        private ActionMenuPresenterCallback()
        {
            this$0 = PhoneWindow.this;
            super();
        }

        ActionMenuPresenterCallback(ActionMenuPresenterCallback actionmenupresentercallback)
        {
            this();
        }
    }

    private static final class DrawableFeatureState
    {

        int alpha;
        Drawable child;
        Drawable cur;
        int curAlpha;
        Drawable def;
        final int featureId;
        Drawable local;
        int resid;
        Uri uri;

        DrawableFeatureState(int i)
        {
            alpha = 255;
            curAlpha = 255;
            featureId = i;
        }
    }

    static final class PanelFeatureState
    {

        void applyFrozenState()
        {
            if(menu != null && frozenMenuState != null)
            {
                menu.restorePresenterStates(frozenMenuState);
                frozenMenuState = null;
            }
        }

        public void clearMenuPresenters()
        {
            if(menu != null)
            {
                menu.removeMenuPresenter(iconMenuPresenter);
                menu.removeMenuPresenter(listMenuPresenter);
            }
            iconMenuPresenter = null;
            listMenuPresenter = null;
        }

        MenuView getIconMenuView(Context context, com.android.internal.view.menu.MenuPresenter.Callback callback)
        {
            if(menu == null)
                return null;
            if(iconMenuPresenter == null)
            {
                iconMenuPresenter = new IconMenuPresenter(context);
                iconMenuPresenter.setCallback(callback);
                iconMenuPresenter.setId(0x1020293);
                menu.addMenuPresenter(iconMenuPresenter);
            }
            return iconMenuPresenter.getMenuView(decorView);
        }

        MenuView getListMenuView(Context context, com.android.internal.view.menu.MenuPresenter.Callback callback)
        {
            if(menu == null)
                return null;
            if(!isCompact)
                getIconMenuView(context, callback);
            if(listMenuPresenter == null)
            {
                listMenuPresenter = new ListMenuPresenter(0x109007c, listPresenterTheme);
                listMenuPresenter.setCallback(callback);
                listMenuPresenter.setId(0x10202e3);
                menu.addMenuPresenter(listMenuPresenter);
            }
            if(iconMenuPresenter != null)
                listMenuPresenter.setItemIndexOffset(iconMenuPresenter.getNumActualItemsShown());
            return listMenuPresenter.getMenuView(decorView);
        }

        public boolean hasPanelItems()
        {
            boolean flag = true;
            if(shownPanelView == null)
                return false;
            if(createdPanelView != null)
                return true;
            if(isCompact || isInExpandedMode)
            {
                if(listMenuPresenter.getAdapter().getCount() > 0)
                    flag = true;
                else
                    flag = false;
                return flag;
            }
            if(((ViewGroup)shownPanelView).getChildCount() <= 0)
                flag = false;
            return flag;
        }

        public boolean isInListMode()
        {
            boolean flag;
            if(!isInExpandedMode)
                flag = isCompact;
            else
                flag = true;
            return flag;
        }

        void onRestoreInstanceState(Parcelable parcelable)
        {
            parcelable = (SavedState)parcelable;
            featureId = ((SavedState) (parcelable)).featureId;
            wasLastOpen = ((SavedState) (parcelable)).isOpen;
            wasLastExpanded = ((SavedState) (parcelable)).isInExpandedMode;
            frozenMenuState = ((SavedState) (parcelable)).menuState;
            createdPanelView = null;
            shownPanelView = null;
            decorView = null;
        }

        Parcelable onSaveInstanceState()
        {
            SavedState savedstate = new SavedState(null);
            savedstate.featureId = featureId;
            savedstate.isOpen = isOpen;
            savedstate.isInExpandedMode = isInExpandedMode;
            if(menu != null)
            {
                savedstate.menuState = new Bundle();
                menu.savePresenterStates(savedstate.menuState);
            }
            return savedstate;
        }

        void setMenu(MenuBuilder menubuilder)
        {
            if(menubuilder == menu)
                return;
            if(menu != null)
            {
                menu.removeMenuPresenter(iconMenuPresenter);
                menu.removeMenuPresenter(listMenuPresenter);
            }
            menu = menubuilder;
            if(menubuilder != null)
            {
                if(iconMenuPresenter != null)
                    menubuilder.addMenuPresenter(iconMenuPresenter);
                if(listMenuPresenter != null)
                    menubuilder.addMenuPresenter(listMenuPresenter);
            }
        }

        void setStyle(Context context)
        {
            context = context.obtainStyledAttributes(com.android.internal.R.styleable.Theme);
            background = context.getResourceId(46, 0);
            fullBackground = context.getResourceId(47, 0);
            windowAnimations = context.getResourceId(93, 0);
            isCompact = context.getBoolean(306, false);
            listPresenterTheme = context.getResourceId(307, 0x10303d1);
            context.recycle();
        }

        int background;
        View createdPanelView;
        DecorView decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int fullBackground;
        int gravity;
        IconMenuPresenter iconMenuPresenter;
        boolean isCompact;
        boolean isHandled;
        boolean isInExpandedMode;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        int listPresenterTheme;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastExpanded;
        boolean wasLastOpen;
        int windowAnimations;
        int x;
        int y;

        PanelFeatureState(int i)
        {
            featureId = i;
            refreshDecorView = false;
        }
    }

    private static class PanelFeatureState.SavedState
        implements Parcelable
    {

        static PanelFeatureState.SavedState _2D_wrap0(Parcel parcel)
        {
            return readFromParcel(parcel);
        }

        private static PanelFeatureState.SavedState readFromParcel(Parcel parcel)
        {
            boolean flag = true;
            PanelFeatureState.SavedState savedstate = new PanelFeatureState.SavedState();
            savedstate.featureId = parcel.readInt();
            boolean flag1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            savedstate.isOpen = flag1;
            if(parcel.readInt() == 1)
                flag1 = flag;
            else
                flag1 = false;
            savedstate.isInExpandedMode = flag1;
            if(savedstate.isOpen)
                savedstate.menuState = parcel.readBundle();
            return savedstate;
        }

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeInt(featureId);
            if(isOpen)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(isInExpandedMode)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            if(isOpen)
                parcel.writeBundle(menuState);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PanelFeatureState.SavedState createFromParcel(Parcel parcel)
            {
                return PanelFeatureState.SavedState._2D_wrap0(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PanelFeatureState.SavedState[] newArray(int i)
            {
                return new PanelFeatureState.SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int featureId;
        boolean isInExpandedMode;
        boolean isOpen;
        Bundle menuState;


        private PanelFeatureState.SavedState()
        {
        }

        PanelFeatureState.SavedState(PanelFeatureState.SavedState savedstate)
        {
            this();
        }
    }

    private class PanelMenuPresenterCallback
        implements com.android.internal.view.menu.MenuPresenter.Callback
    {

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            MenuBuilder menubuilder1 = menubuilder.getRootMenu();
            boolean flag1;
            PhoneWindow phonewindow;
            if(menubuilder1 != menubuilder)
                flag1 = true;
            else
                flag1 = false;
            phonewindow = PhoneWindow.this;
            if(flag1)
                menubuilder = menubuilder1;
            menubuilder = phonewindow.findMenuPanel(menubuilder);
            if(menubuilder != null)
                if(flag1)
                {
                    PhoneWindow._2D_wrap0(PhoneWindow.this, ((PanelFeatureState) (menubuilder)).featureId, menubuilder, menubuilder1);
                    closePanel(menubuilder, true);
                } else
                {
                    closePanel(menubuilder, flag);
                }
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if(menubuilder == null && hasFeature(8))
            {
                android.view.Window.Callback callback = getCallback();
                if(callback != null && isDestroyed() ^ true)
                    callback.onMenuOpened(8, menubuilder);
            }
            return true;
        }

        final PhoneWindow this$0;

        private PanelMenuPresenterCallback()
        {
            this$0 = PhoneWindow.this;
            super();
        }

        PanelMenuPresenterCallback(PanelMenuPresenterCallback panelmenupresentercallback)
        {
            this();
        }
    }

    public static final class PhoneWindowMenuCallback
        implements com.android.internal.view.menu.MenuBuilder.Callback, com.android.internal.view.menu.MenuPresenter.Callback
    {

        private void onCloseSubMenu(MenuBuilder menubuilder)
        {
            android.view.Window.Callback callback = mWindow.getCallback();
            if(callback != null && mWindow.isDestroyed() ^ true)
                callback.onPanelClosed(6, menubuilder.getRootMenu());
        }

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            if(menubuilder.getRootMenu() != menubuilder)
                onCloseSubMenu(menubuilder);
            if(flag)
            {
                android.view.Window.Callback callback = mWindow.getCallback();
                if(callback != null && mWindow.isDestroyed() ^ true)
                    callback.onPanelClosed(6, menubuilder);
                if(menubuilder == mWindow.mContextMenu)
                    PhoneWindow._2D_wrap1(mWindow);
                if(mSubMenuHelper != null)
                {
                    mSubMenuHelper.dismiss();
                    mSubMenuHelper = null;
                }
            }
        }

        public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            menubuilder = mWindow.getCallback();
            boolean flag;
            if(menubuilder != null && mWindow.isDestroyed() ^ true)
                flag = menubuilder.onMenuItemSelected(6, menuitem);
            else
                flag = false;
            return flag;
        }

        public void onMenuModeChange(MenuBuilder menubuilder)
        {
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if(menubuilder == null)
                return false;
            menubuilder.setCallback(this);
            if(mShowDialogForSubmenu)
            {
                mSubMenuHelper = new MenuDialogHelper(menubuilder);
                mSubMenuHelper.show(null);
                return true;
            } else
            {
                return false;
            }
        }

        public void setShowDialogForSubmenu(boolean flag)
        {
            mShowDialogForSubmenu = flag;
        }

        private static final int FEATURE_ID = 6;
        private boolean mShowDialogForSubmenu;
        private MenuDialogHelper mSubMenuHelper;
        private final PhoneWindow mWindow;

        public PhoneWindowMenuCallback(PhoneWindow phonewindow)
        {
            mWindow = phonewindow;
        }
    }

    static class RotationWatcher extends android.view.IRotationWatcher.Stub
    {

        public void addWindow(PhoneWindow phonewindow)
        {
            ArrayList arraylist = mWindows;
            arraylist;
            JVM INSTR monitorenter ;
            boolean flag = mIsWatching;
            if(flag)
                break MISSING_BLOCK_LABEL_57;
            WindowManagerHolder.sWindowManager.watchRotation(this, phonewindow.getContext().getDisplay().getDisplayId());
            Handler handler = JVM INSTR new #69  <Class Handler>;
            handler.Handler();
            mHandler = handler;
            mIsWatching = true;
_L1:
            ArrayList arraylist1 = mWindows;
            WeakReference weakreference = JVM INSTR new #74  <Class WeakReference>;
            weakreference.WeakReference(phonewindow);
            arraylist1.add(weakreference);
            arraylist;
            JVM INSTR monitorexit ;
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("PhoneWindow", "Couldn't start watching for device rotation", remoteexception);
              goto _L1
            phonewindow;
            throw phonewindow;
        }

        void dispatchRotationChanged()
        {
            ArrayList arraylist = mWindows;
            arraylist;
            JVM INSTR monitorenter ;
            int i = 0;
_L1:
            PhoneWindow phonewindow;
            if(i >= mWindows.size())
                break MISSING_BLOCK_LABEL_69;
            phonewindow = (PhoneWindow)((WeakReference)mWindows.get(i)).get();
            if(phonewindow == null)
                break MISSING_BLOCK_LABEL_52;
            phonewindow.onOptionsPanelRotationChanged();
            i++;
              goto _L1
            mWindows.remove(i);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
        }

        public void onRotationChanged(int i)
            throws RemoteException
        {
            mHandler.post(mRotationChanged);
        }

        public void removeWindow(PhoneWindow phonewindow)
        {
            ArrayList arraylist = mWindows;
            arraylist;
            JVM INSTR monitorenter ;
            int i = 0;
_L2:
            PhoneWindow phonewindow1;
            if(i >= mWindows.size())
                break; /* Loop/switch isn't completed */
            phonewindow1 = (PhoneWindow)((WeakReference)mWindows.get(i)).get();
            if(phonewindow1 != null && phonewindow1 != phonewindow)
                break MISSING_BLOCK_LABEL_67;
            mWindows.remove(i);
            continue; /* Loop/switch isn't completed */
            phonewindow;
            throw phonewindow;
            i++;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private Handler mHandler;
        private boolean mIsWatching;
        private final Runnable mRotationChanged = new _cls1();
        private final ArrayList mWindows = new ArrayList();

        RotationWatcher()
        {
        }
    }

    static class WindowManagerHolder
    {

        static final IWindowManager sWindowManager = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));


        WindowManagerHolder()
        {
        }
    }


    static int _2D_get0(PhoneWindow phonewindow)
    {
        return phonewindow.mInvalidatePanelMenuFeatures;
    }

    static int _2D_set0(PhoneWindow phonewindow, int i)
    {
        phonewindow.mInvalidatePanelMenuFeatures = i;
        return i;
    }

    static boolean _2D_set1(PhoneWindow phonewindow, boolean flag)
    {
        phonewindow.mInvalidatePanelMenuPosted = flag;
        return flag;
    }

    static void _2D_wrap0(PhoneWindow phonewindow, int i, PanelFeatureState panelfeaturestate, Menu menu)
    {
        phonewindow.callOnPanelClosed(i, panelfeaturestate, menu);
    }

    static void _2D_wrap1(PhoneWindow phonewindow)
    {
        phonewindow.dismissContextMenu();
    }

    public PhoneWindow(Context context)
    {
        super(context);
        mContextMenuCallback = new PhoneWindowMenuCallback(this);
        mMinWidthMajor = new TypedValue();
        mMinWidthMinor = new TypedValue();
        mForceDecorInstall = false;
        mContentParentExplicitlySet = false;
        mBackgroundResource = 0;
        mBackgroundFallbackResource = 0;
        mLoadElevation = true;
        mFrameResource = 0;
        mTextColor = 0;
        mStatusBarColor = 0;
        mNavigationBarColor = 0;
        mNavigationBarDividerColor = 0;
        mForcedStatusBarColor = false;
        mForcedNavigationBarColor = false;
        mTitle = null;
        mTitleColor = 0;
        mAlwaysReadCloseOnTouchAttr = false;
        mVolumeControlStreamType = 0x80000000;
        mUiOptions = 0;
        mInvalidatePanelMenuRunnable = new Runnable() {

            public void run()
            {
                for(int i = 0; i <= 13; i++)
                    if((PhoneWindow._2D_get0(PhoneWindow.this) & 1 << i) != 0)
                        doInvalidatePanelMenu(i);

                PhoneWindow._2D_set1(PhoneWindow.this, false);
                PhoneWindow._2D_set0(PhoneWindow.this, 0);
            }

            final PhoneWindow this$0;

            
            {
                this$0 = PhoneWindow.this;
                super();
            }
        }
;
        mEnterTransition = null;
        mReturnTransition = USE_DEFAULT_TRANSITION;
        mExitTransition = null;
        mReenterTransition = USE_DEFAULT_TRANSITION;
        mSharedElementEnterTransition = null;
        mSharedElementReturnTransition = USE_DEFAULT_TRANSITION;
        mSharedElementExitTransition = null;
        mSharedElementReenterTransition = USE_DEFAULT_TRANSITION;
        mBackgroundFadeDurationMillis = -1L;
        mTheme = -1;
        mDecorCaptionShade = 0;
        mUseDecorContext = false;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public PhoneWindow(Context context, Window window, android.view.ViewRootImpl.ActivityConfigCallback activityconfigcallback)
    {
        this(context);
        mUseDecorContext = true;
        if(window != null)
        {
            mDecor = (DecorView)window.getDecorView();
            mElevation = window.getElevation();
            mLoadElevation = false;
            mForceDecorInstall = true;
            getAttributes().token = window.getAttributes().token;
        }
        boolean flag;
        boolean flag1;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "force_resizable_activities", 0) != 0)
            flag = true;
        else
            flag = false;
        if(!flag)
            flag1 = context.getPackageManager().hasSystemFeature("android.software.picture_in_picture");
        else
            flag1 = true;
        mSupportsPictureInPicture = flag1;
        mActivityConfigCallback = activityconfigcallback;
    }

    private void callOnPanelClosed(int i, PanelFeatureState panelfeaturestate, Menu menu)
    {
        android.view.Window.Callback callback = getCallback();
        if(callback == null)
            return;
        PanelFeatureState panelfeaturestate1 = panelfeaturestate;
        Object obj = menu;
        if(menu == null)
        {
            PanelFeatureState panelfeaturestate2 = panelfeaturestate;
            if(panelfeaturestate == null)
            {
                panelfeaturestate2 = panelfeaturestate;
                if(i >= 0)
                {
                    panelfeaturestate2 = panelfeaturestate;
                    if(i < mPanels.length)
                        panelfeaturestate2 = mPanels[i];
                }
            }
            panelfeaturestate1 = panelfeaturestate2;
            obj = menu;
            if(panelfeaturestate2 != null)
            {
                obj = panelfeaturestate2.menu;
                panelfeaturestate1 = panelfeaturestate2;
            }
        }
        if(panelfeaturestate1 != null && panelfeaturestate1.isOpen ^ true)
            return;
        if(!isDestroyed())
            callback.onPanelClosed(i, ((Menu) (obj)));
    }

    private static void clearMenuViews(PanelFeatureState panelfeaturestate)
    {
        panelfeaturestate.createdPanelView = null;
        panelfeaturestate.refreshDecorView = true;
        panelfeaturestate.clearMenuPresenters();
    }

    private void closeContextMenu()
    {
        this;
        JVM INSTR monitorenter ;
        if(mContextMenu != null)
        {
            mContextMenu.close();
            dismissContextMenu();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void dismissContextMenu()
    {
        this;
        JVM INSTR monitorenter ;
        mContextMenu = null;
        if(mContextMenuHelper != null)
        {
            mContextMenuHelper.dismiss();
            mContextMenuHelper = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private ProgressBar getCircularProgressBar(boolean flag)
    {
        if(mCircularProgressBar != null)
            return mCircularProgressBar;
        if(mContentParent == null && flag)
            installDecor();
        mCircularProgressBar = (ProgressBar)findViewById(0x1020394);
        if(mCircularProgressBar != null)
            mCircularProgressBar.setVisibility(4);
        return mCircularProgressBar;
    }

    private DrawableFeatureState getDrawableState(int i, boolean flag)
    {
        DrawableFeatureState adrawablefeaturestate1[];
label0:
        {
            if((getFeatures() & 1 << i) == 0)
                if(!flag)
                    return null;
                else
                    throw new RuntimeException("The feature has not been requested");
            DrawableFeatureState adrawablefeaturestate[] = mDrawables;
            if(adrawablefeaturestate != null)
            {
                adrawablefeaturestate1 = adrawablefeaturestate;
                if(adrawablefeaturestate.length > i)
                    break label0;
            }
            DrawableFeatureState adrawablefeaturestate2[] = new DrawableFeatureState[i + 1];
            if(adrawablefeaturestate != null)
                System.arraycopy(adrawablefeaturestate, 0, adrawablefeaturestate2, 0, adrawablefeaturestate.length);
            adrawablefeaturestate1 = adrawablefeaturestate2;
            mDrawables = adrawablefeaturestate2;
        }
        DrawableFeatureState drawablefeaturestate = adrawablefeaturestate1[i];
        DrawableFeatureState drawablefeaturestate1 = drawablefeaturestate;
        if(drawablefeaturestate == null)
        {
            drawablefeaturestate1 = new DrawableFeatureState(i);
            adrawablefeaturestate1[i] = drawablefeaturestate1;
        }
        return drawablefeaturestate1;
    }

    private ProgressBar getHorizontalProgressBar(boolean flag)
    {
        if(mHorizontalProgressBar != null)
            return mHorizontalProgressBar;
        if(mContentParent == null && flag)
            installDecor();
        mHorizontalProgressBar = (ProgressBar)findViewById(0x1020395);
        if(mHorizontalProgressBar != null)
            mHorizontalProgressBar.setVisibility(4);
        return mHorizontalProgressBar;
    }

    private KeyguardManager getKeyguardManager()
    {
        if(mKeyguardManager == null)
            mKeyguardManager = (KeyguardManager)getContext().getSystemService("keyguard");
        return mKeyguardManager;
    }

    private ImageView getLeftIconView()
    {
        if(mLeftIconView != null)
            return mLeftIconView;
        if(mContentParent == null)
            installDecor();
        ImageView imageview = (ImageView)findViewById(0x10202d9);
        mLeftIconView = imageview;
        return imageview;
    }

    private int getOptionsPanelGravity()
    {
        int i;
        try
        {
            i = WindowManagerHolder.sWindowManager.getPreferredOptionsPanelGravity();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("PhoneWindow", "Couldn't getOptionsPanelGravity; using default", remoteexception);
            return 81;
        }
        return i;
    }

    private PanelFeatureState getPanelState(int i, boolean flag, PanelFeatureState panelfeaturestate)
    {
        PanelFeatureState apanelfeaturestate1[];
label0:
        {
            if((getFeatures() & 1 << i) == 0)
                if(!flag)
                    return null;
                else
                    throw new RuntimeException("The feature has not been requested");
            PanelFeatureState apanelfeaturestate[] = mPanels;
            if(apanelfeaturestate != null)
            {
                apanelfeaturestate1 = apanelfeaturestate;
                if(apanelfeaturestate.length > i)
                    break label0;
            }
            PanelFeatureState apanelfeaturestate2[] = new PanelFeatureState[i + 1];
            if(apanelfeaturestate != null)
                System.arraycopy(apanelfeaturestate, 0, apanelfeaturestate2, 0, apanelfeaturestate.length);
            apanelfeaturestate1 = apanelfeaturestate2;
            mPanels = apanelfeaturestate2;
        }
        PanelFeatureState panelfeaturestate2 = apanelfeaturestate1[i];
        PanelFeatureState panelfeaturestate1 = panelfeaturestate2;
        if(panelfeaturestate2 == null)
        {
            if(panelfeaturestate == null)
                panelfeaturestate = new PanelFeatureState(i);
            apanelfeaturestate1[i] = panelfeaturestate;
            panelfeaturestate1 = panelfeaturestate;
        }
        return panelfeaturestate1;
    }

    private ImageView getRightIconView()
    {
        if(mRightIconView != null)
            return mRightIconView;
        if(mContentParent == null)
            installDecor();
        ImageView imageview = (ImageView)findViewById(0x10203b1);
        mRightIconView = imageview;
        return imageview;
    }

    private Transition getTransition(Transition transition, Transition transition1, int i)
    {
        if(transition != transition1)
            return transition;
        i = getWindowStyle().getResourceId(i, -1);
        transition = transition1;
        if(i != -1)
        {
            transition = transition1;
            if(i != 0x10f0000)
            {
                transition1 = TransitionInflater.from(getContext()).inflateTransition(i);
                transition = transition1;
                if(transition1 instanceof TransitionSet)
                {
                    transition = transition1;
                    if(((TransitionSet)transition1).getTransitionCount() == 0)
                        transition = null;
                }
            }
        }
        return transition;
    }

    private ViewRootImpl getViewRootImpl()
    {
        if(mDecor != null)
        {
            ViewRootImpl viewrootimpl = mDecor.getViewRootImpl();
            if(viewrootimpl != null)
                return viewrootimpl;
        }
        throw new IllegalStateException("view not added");
    }

    private void hideProgressBars(ProgressBar progressbar, ProgressBar progressbar1)
    {
        int i = getLocalFeatures();
        Animation animation = AnimationUtils.loadAnimation(getContext(), 0x10a0001);
        animation.setDuration(1000L);
        if((i & 0x20) != 0 && progressbar1 != null && progressbar1.getVisibility() == 0)
        {
            progressbar1.startAnimation(animation);
            progressbar1.setVisibility(4);
        }
        if((i & 4) != 0 && progressbar != null && progressbar.getVisibility() == 0)
        {
            progressbar.startAnimation(animation);
            progressbar.setVisibility(4);
        }
    }

    private void installDecor()
    {
        mForceDecorInstall = false;
        if(mDecor == null)
        {
            mDecor = generateDecor(-1);
            mDecor.setDescendantFocusability(0x40000);
            mDecor.setIsRootNamespace(true);
            if(!mInvalidatePanelMenuPosted && mInvalidatePanelMenuFeatures != 0)
                mDecor.postOnAnimation(mInvalidatePanelMenuRunnable);
        } else
        {
            mDecor.setWindow(this);
        }
        if(mContentParent == null)
        {
            mContentParent = generateLayout(mDecor);
            mDecor.makeOptionalFitsSystemWindows();
            Object obj = (DecorContentParent)mDecor.findViewById(0x102021d);
            if(obj != null)
            {
                mDecorContentParent = ((DecorContentParent) (obj));
                mDecorContentParent.setWindowCallback(getCallback());
                if(mDecorContentParent.getTitle() == null)
                    mDecorContentParent.setWindowTitle(mTitle);
                int i = getLocalFeatures();
                for(int j = 0; j < 13; j++)
                    if((1 << j & i) != 0)
                        mDecorContentParent.initFeature(j);

                mDecorContentParent.setUiOptions(mUiOptions);
                int k;
                if((mResourcesSetFlags & 1) != 0 || mIconRes != 0 && mDecorContentParent.hasIcon() ^ true)
                    mDecorContentParent.setIcon(mIconRes);
                else
                if((mResourcesSetFlags & 1) == 0 && mIconRes == 0 && mDecorContentParent.hasIcon() ^ true)
                {
                    mDecorContentParent.setIcon(getContext().getPackageManager().getDefaultActivityIcon());
                    mResourcesSetFlags = mResourcesSetFlags | 4;
                }
                if((mResourcesSetFlags & 2) != 0 || mLogoRes != 0 && mDecorContentParent.hasLogo() ^ true)
                    mDecorContentParent.setLogo(mLogoRes);
                obj = getPanelState(0, false);
                if(!isDestroyed() && (obj == null || ((PanelFeatureState) (obj)).menu == null) && mIsStartingWindow ^ true)
                    invalidatePanelMenu(8);
            } else
            {
                mTitleView = (TextView)findViewById(0x1020016);
                if(mTitleView != null)
                    if((getLocalFeatures() & 2) != 0)
                    {
                        View view = findViewById(0x1020457);
                        if(view != null)
                            view.setVisibility(8);
                        else
                            mTitleView.setVisibility(8);
                        mContentParent.setForeground(null);
                    } else
                    {
                        mTitleView.setText(mTitle);
                    }
            }
            if(mDecor.getBackground() == null && mBackgroundFallbackResource != 0)
                mDecor.setBackgroundFallback(mBackgroundFallbackResource);
            if(hasFeature(13))
            {
                if(mTransitionManager == null)
                {
                    k = getWindowStyle().getResourceId(27, 0);
                    if(k != 0)
                        mTransitionManager = TransitionInflater.from(getContext()).inflateTransitionManager(k, mContentParent);
                    else
                        mTransitionManager = new TransitionManager();
                }
                mEnterTransition = getTransition(mEnterTransition, null, 28);
                mReturnTransition = getTransition(mReturnTransition, USE_DEFAULT_TRANSITION, 40);
                mExitTransition = getTransition(mExitTransition, null, 29);
                mReenterTransition = getTransition(mReenterTransition, USE_DEFAULT_TRANSITION, 41);
                mSharedElementEnterTransition = getTransition(mSharedElementEnterTransition, null, 30);
                mSharedElementReturnTransition = getTransition(mSharedElementReturnTransition, USE_DEFAULT_TRANSITION, 42);
                mSharedElementExitTransition = getTransition(mSharedElementExitTransition, null, 31);
                mSharedElementReenterTransition = getTransition(mSharedElementReenterTransition, USE_DEFAULT_TRANSITION, 43);
                if(mAllowEnterTransitionOverlap == null)
                    mAllowEnterTransitionOverlap = Boolean.valueOf(getWindowStyle().getBoolean(33, true));
                if(mAllowReturnTransitionOverlap == null)
                    mAllowReturnTransitionOverlap = Boolean.valueOf(getWindowStyle().getBoolean(32, true));
                if(mBackgroundFadeDurationMillis < 0L)
                    mBackgroundFadeDurationMillis = getWindowStyle().getInteger(37, 300);
                if(mSharedElementsUseOverlay == null)
                    mSharedElementsUseOverlay = Boolean.valueOf(getWindowStyle().getBoolean(44, true));
            }
        }
    }

    private boolean isTvUserSetupComplete()
    {
        boolean flag = false;
        boolean flag1;
        if(android.provider.Settings.Secure.getInt(getContext().getContentResolver(), "user_setup_complete", 0) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(android.provider.Settings.Secure.getInt(getContext().getContentResolver(), "tv_user_setup_complete", 0) != 0)
            flag = true;
        return flag1 & flag;
    }

    private boolean launchDefaultSearch(KeyEvent keyevent)
    {
        if(getContext().getPackageManager().hasSystemFeature("android.software.leanback") && isTvUserSetupComplete() ^ true)
            return false;
        android.view.Window.Callback callback = getCallback();
        boolean flag;
        if(callback == null || isDestroyed())
        {
            flag = false;
        } else
        {
            sendCloseSystemWindows("search");
            int i = keyevent.getDeviceId();
            SearchEvent searchevent = null;
            if(i != 0)
                searchevent = new SearchEvent(InputDevice.getDevice(i));
            try
            {
                flag = callback.onSearchRequested(searchevent);
            }
            catch(AbstractMethodError abstractmethoderror)
            {
                Log.e("PhoneWindow", (new StringBuilder()).append("WindowCallback ").append(callback.getClass().getName()).append(" does not implement").append(" method onSearchRequested(SearchEvent); fa").toString(), abstractmethoderror);
                flag = callback.onSearchRequested();
            }
        }
        if(!flag && (getContext().getResources().getConfiguration().uiMode & 0xf) == 4)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("android.intent.extra.ASSIST_INPUT_DEVICE_ID", keyevent.getDeviceId());
            return ((SearchManager)getContext().getSystemService("search")).launchLegacyAssist(null, UserHandle.myUserId(), bundle);
        } else
        {
            return flag;
        }
    }

    private Drawable loadImageURI(Uri uri)
    {
        Drawable drawable;
        try
        {
            drawable = Drawable.createFromStream(getContext().getContentResolver().openInputStream(uri), null);
        }
        catch(Exception exception)
        {
            Log.w("PhoneWindow", (new StringBuilder()).append("Unable to open content: ").append(uri).toString());
            return null;
        }
        return drawable;
    }

    private void openPanel(PanelFeatureState panelfeaturestate, KeyEvent keyevent)
    {
        if(panelfeaturestate.isOpen || isDestroyed())
            return;
        if(panelfeaturestate.featureId == 0)
        {
            Context context = getContext();
            boolean flag;
            boolean flag1;
            boolean flag2;
            if((context.getResources().getConfiguration().screenLayout & 0xf) == 4)
                flag = true;
            else
                flag = false;
            if(context.getApplicationInfo().targetSdkVersion >= 11)
                flag1 = true;
            else
                flag1 = false;
            flag2 = flag;
            if(flag)
                flag2 = false;
            if(flag2 && flag1)
                return;
        }
        android.view.Window.Callback callback = getCallback();
        if(callback != null && callback.onMenuOpened(panelfeaturestate.featureId, panelfeaturestate.menu) ^ true)
        {
            closePanel(panelfeaturestate, true);
            return;
        }
        WindowManager windowmanager = getWindowManager();
        if(windowmanager == null)
            return;
        if(!preparePanel(panelfeaturestate, keyevent))
            return;
        byte byte0 = -2;
        int i;
        if(panelfeaturestate.decorView == null || panelfeaturestate.refreshDecorView)
        {
            if(panelfeaturestate.decorView == null)
            {
                if(!initializePanelDecor(panelfeaturestate) || panelfeaturestate.decorView == null)
                    return;
            } else
            if(panelfeaturestate.refreshDecorView && panelfeaturestate.decorView.getChildCount() > 0)
                panelfeaturestate.decorView.removeAllViews();
            if(!initializePanelContent(panelfeaturestate) || panelfeaturestate.hasPanelItems() ^ true)
                return;
            Object obj = panelfeaturestate.shownPanelView.getLayoutParams();
            keyevent = ((KeyEvent) (obj));
            if(obj == null)
                keyevent = new android.view.ViewGroup.LayoutParams(-2, -2);
            if(((android.view.ViewGroup.LayoutParams) (keyevent)).width == -1)
            {
                i = panelfeaturestate.fullBackground;
                byte0 = -1;
            } else
            {
                i = panelfeaturestate.background;
            }
            panelfeaturestate.decorView.setWindowBackground(getContext().getDrawable(i));
            obj = panelfeaturestate.shownPanelView.getParent();
            if(obj != null && (obj instanceof ViewGroup))
                ((ViewGroup)obj).removeView(panelfeaturestate.shownPanelView);
            panelfeaturestate.decorView.addView(panelfeaturestate.shownPanelView, keyevent);
            i = byte0;
            if(!panelfeaturestate.shownPanelView.hasFocus())
            {
                panelfeaturestate.shownPanelView.requestFocus();
                i = byte0;
            }
        } else
        if(!panelfeaturestate.isInListMode())
        {
            i = -1;
        } else
        {
            i = byte0;
            if(panelfeaturestate.createdPanelView != null)
            {
                keyevent = panelfeaturestate.createdPanelView.getLayoutParams();
                i = byte0;
                if(keyevent != null)
                {
                    i = byte0;
                    if(((android.view.ViewGroup.LayoutParams) (keyevent)).width == -1)
                        i = -1;
                }
            }
        }
        panelfeaturestate.isHandled = false;
        keyevent = new android.view.WindowManager.LayoutParams(i, -2, panelfeaturestate.x, panelfeaturestate.y, 1003, 0x820000, panelfeaturestate.decorView.mDefaultOpacity);
        if(panelfeaturestate.isCompact)
        {
            keyevent.gravity = getOptionsPanelGravity();
            sRotationWatcher.addWindow(this);
        } else
        {
            keyevent.gravity = panelfeaturestate.gravity;
        }
        keyevent.windowAnimations = panelfeaturestate.windowAnimations;
        windowmanager.addView(panelfeaturestate.decorView, keyevent);
        panelfeaturestate.isOpen = true;
    }

    private void registerSwipeCallbacks(ViewGroup viewgroup)
    {
        if(!(viewgroup instanceof SwipeDismissLayout))
        {
            Log.w("PhoneWindow", (new StringBuilder()).append("contentParent is not a SwipeDismissLayout: ").append(viewgroup).toString());
            return;
        } else
        {
            viewgroup = (SwipeDismissLayout)viewgroup;
            viewgroup.setOnDismissedListener(new com.android.internal.widget.SwipeDismissLayout.OnDismissedListener() {

                public void onDismissed(SwipeDismissLayout swipedismisslayout)
                {
                    dispatchOnWindowSwipeDismissed();
                    dispatchOnWindowDismissed(false, true);
                }

                final PhoneWindow this$0;

            
            {
                this$0 = PhoneWindow.this;
                super();
            }
            }
);
            viewgroup.setOnSwipeProgressChangedListener(new com.android.internal.widget.SwipeDismissLayout.OnSwipeProgressChangedListener() {

                public void onSwipeCancelled(SwipeDismissLayout swipedismisslayout)
                {
                    swipedismisslayout = getAttributes();
                    if(((android.view.WindowManager.LayoutParams) (swipedismisslayout)).x != 0 || ((android.view.WindowManager.LayoutParams) (swipedismisslayout)).alpha != 1.0F)
                    {
                        swipedismisslayout.x = 0;
                        swipedismisslayout.alpha = 1.0F;
                        setAttributes(swipedismisslayout);
                        setFlags(1024, 1536);
                    }
                }

                public void onSwipeProgressChanged(SwipeDismissLayout swipedismisslayout, float f, float f1)
                {
                    swipedismisslayout = getAttributes();
                    swipedismisslayout.x = (int)f1;
                    swipedismisslayout.alpha = f;
                    setAttributes(swipedismisslayout);
                    char c;
                    if(((android.view.WindowManager.LayoutParams) (swipedismisslayout)).x == 0)
                        c = '\u0400';
                    else
                        c = '\u0200';
                    setFlags(c, 1536);
                }

                final PhoneWindow this$0;

            
            {
                this$0 = PhoneWindow.this;
                super();
            }
            }
);
            return;
        }
    }

    private void reopenMenu(boolean flag)
    {
        if(mDecorContentParent == null || !mDecorContentParent.canShowOverflowMenu() || ViewConfiguration.get(getContext()).hasPermanentMenuKey() && !mDecorContentParent.isOverflowMenuShowPending()) goto _L2; else goto _L1
_L1:
        android.view.Window.Callback callback = getCallback();
        if(mDecorContentParent.isOverflowMenuShowing() && !(flag ^ true)) goto _L4; else goto _L3
_L3:
        if(callback != null && isDestroyed() ^ true)
        {
            if(mInvalidatePanelMenuPosted && (mInvalidatePanelMenuFeatures & 1) != 0)
            {
                mDecor.removeCallbacks(mInvalidatePanelMenuRunnable);
                mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState panelfeaturestate1 = getPanelState(0, false);
            if(panelfeaturestate1 != null && panelfeaturestate1.menu != null && panelfeaturestate1.refreshMenuContent ^ true && callback.onPreparePanel(0, panelfeaturestate1.createdPanelView, panelfeaturestate1.menu))
            {
                callback.onMenuOpened(8, panelfeaturestate1.menu);
                mDecorContentParent.showOverflowMenu();
            }
        }
_L5:
        return;
_L4:
        mDecorContentParent.hideOverflowMenu();
        PanelFeatureState panelfeaturestate2 = getPanelState(0, false);
        if(panelfeaturestate2 != null && callback != null && isDestroyed() ^ true)
            callback.onPanelClosed(8, panelfeaturestate2.menu);
        if(true) goto _L5; else goto _L2
_L2:
        PanelFeatureState panelfeaturestate = getPanelState(0, false);
        if(panelfeaturestate == null)
            return;
        if(flag)
            flag = panelfeaturestate.isInExpandedMode ^ true;
        else
            flag = panelfeaturestate.isInExpandedMode;
        panelfeaturestate.refreshDecorView = true;
        closePanel(panelfeaturestate, false);
        panelfeaturestate.isInExpandedMode = flag;
        openPanel(panelfeaturestate, null);
        return;
    }

    private void restorePanelState(SparseArray sparsearray)
    {
        int i = sparsearray.size() - 1;
        while(i >= 0) 
        {
            int j = sparsearray.keyAt(i);
            PanelFeatureState panelfeaturestate = getPanelState(j, false);
            if(panelfeaturestate != null)
            {
                panelfeaturestate.onRestoreInstanceState((Parcelable)sparsearray.get(j));
                invalidatePanelMenu(j);
            }
            i--;
        }
    }

    private void savePanelState(SparseArray sparsearray)
    {
        PanelFeatureState apanelfeaturestate[] = mPanels;
        if(apanelfeaturestate == null)
            return;
        for(int i = apanelfeaturestate.length - 1; i >= 0; i--)
            if(apanelfeaturestate[i] != null)
                sparsearray.put(i, apanelfeaturestate[i].onSaveInstanceState());

    }

    public static void sendCloseSystemWindows(Context context, String s)
    {
        if(!ActivityManager.isSystemReady())
            break MISSING_BLOCK_LABEL_15;
        ActivityManager.getService().closeSystemDialogs(s);
_L2:
        return;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void showProgressBars(ProgressBar progressbar, ProgressBar progressbar1)
    {
        int i = getLocalFeatures();
        if((i & 0x20) != 0 && progressbar1 != null && progressbar1.getVisibility() == 4)
            progressbar1.setVisibility(0);
        if((i & 4) != 0 && progressbar != null && progressbar.getProgress() < 10000)
            progressbar.setVisibility(0);
    }

    private void transitionTo(Scene scene)
    {
        if(mContentScene == null)
            scene.enter();
        else
            mTransitionManager.transitionTo(scene);
        mContentScene = scene;
    }

    private void updateDrawable(int i, DrawableFeatureState drawablefeaturestate, boolean flag)
    {
        int j;
        Drawable drawable;
        if(mContentParent == null)
            return;
        j = 1 << i;
        if((getFeatures() & j) == 0 && flag ^ true)
            return;
        drawable = null;
        if(drawablefeaturestate != null)
        {
            drawable = drawablefeaturestate.child;
            Drawable drawable1 = drawable;
            if(drawable == null)
                drawable1 = drawablefeaturestate.local;
            drawable = drawable1;
            if(drawable1 == null)
                drawable = drawablefeaturestate.def;
        }
        if((getLocalFeatures() & j) != 0) goto _L2; else goto _L1
_L1:
        if(getContainer() != null && (isActive() || flag))
            getContainer().setChildDrawable(i, drawable);
_L4:
        return;
_L2:
        if(drawablefeaturestate != null && (drawablefeaturestate.cur != drawable || drawablefeaturestate.curAlpha != drawablefeaturestate.alpha))
        {
            drawablefeaturestate.cur = drawable;
            drawablefeaturestate.curAlpha = drawablefeaturestate.alpha;
            onDrawableChanged(i, drawable, drawablefeaturestate.alpha);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateInt(int i, int j, boolean flag)
    {
        if(mContentParent == null)
            return;
        int k = 1 << i;
        if((getFeatures() & k) == 0 && flag ^ true)
            return;
        if((getLocalFeatures() & k) == 0)
        {
            if(getContainer() != null)
                getContainer().setChildInt(i, j);
        } else
        {
            onIntChanged(i, j);
        }
    }

    private void updateProgressBars(int i)
    {
        ProgressBar progressbar;
        ProgressBar progressbar1;
        int j;
        progressbar = getCircularProgressBar(true);
        progressbar1 = getHorizontalProgressBar(true);
        j = getLocalFeatures();
        if(i != -1) goto _L2; else goto _L1
_L1:
        if((j & 4) != 0)
            if(progressbar1 != null)
            {
                i = progressbar1.getProgress();
                if(progressbar1.isIndeterminate() || i < 10000)
                    i = 0;
                else
                    i = 4;
                progressbar1.setVisibility(i);
            } else
            {
                Log.e("PhoneWindow", "Horizontal progress bar not located in current window decor");
            }
        if((j & 0x20) != 0)
            if(progressbar != null)
                progressbar.setVisibility(0);
            else
                Log.e("PhoneWindow", "Circular progress bar not located in current window decor");
_L4:
        return;
_L2:
        if(i == -2)
        {
            if((j & 4) != 0)
                if(progressbar1 != null)
                    progressbar1.setVisibility(8);
                else
                    Log.e("PhoneWindow", "Horizontal progress bar not located in current window decor");
            if((j & 0x20) != 0)
                if(progressbar != null)
                    progressbar.setVisibility(8);
                else
                    Log.e("PhoneWindow", "Circular progress bar not located in current window decor");
        } else
        if(i == -3)
        {
            if(progressbar1 != null)
                progressbar1.setIndeterminate(true);
            else
                Log.e("PhoneWindow", "Horizontal progress bar not located in current window decor");
        } else
        if(i == -4)
        {
            if(progressbar1 != null)
                progressbar1.setIndeterminate(false);
            else
                Log.e("PhoneWindow", "Horizontal progress bar not located in current window decor");
        } else
        if(i >= 0 && i <= 10000)
        {
            if(progressbar1 != null)
                progressbar1.setProgress(i + 0);
            else
                Log.e("PhoneWindow", "Horizontal progress bar not located in current window decor");
            if(i < 10000)
                showProgressBars(progressbar1, progressbar);
            else
                hideProgressBars(progressbar1, progressbar);
        } else
        if(20000 <= i && i <= 30000)
        {
            if(progressbar1 != null)
                progressbar1.setSecondaryProgress(i - 20000);
            else
                Log.e("PhoneWindow", "Horizontal progress bar not located in current window decor");
            showProgressBars(progressbar1, progressbar);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(mContentParent == null)
            installDecor();
        if(hasFeature(12))
            Log.v("PhoneWindow", "addContentView does not support content transitions");
        mContentParent.addView(view, layoutparams);
        mContentParent.requestApplyInsets();
        view = getCallback();
        if(view != null && isDestroyed() ^ true)
            view.onContentChanged();
    }

    public void alwaysReadCloseOnTouchAttr()
    {
        mAlwaysReadCloseOnTouchAttr = true;
    }

    void checkCloseActionMenu(Menu menu)
    {
        if(mClosingActionMenu)
            return;
        mClosingActionMenu = true;
        mDecorContentParent.dismissPopups();
        android.view.Window.Callback callback = getCallback();
        if(callback != null && isDestroyed() ^ true)
            callback.onPanelClosed(8, menu);
        mClosingActionMenu = false;
    }

    public void clearContentView()
    {
        if(mDecor != null)
            mDecor.clearContentView();
    }

    public final void closeAllPanels()
    {
        if(getWindowManager() == null)
            return;
        PanelFeatureState apanelfeaturestate[] = mPanels;
        int i;
        int j;
        if(apanelfeaturestate != null)
            i = apanelfeaturestate.length;
        else
            i = 0;
        for(j = 0; j < i; j++)
        {
            PanelFeatureState panelfeaturestate = apanelfeaturestate[j];
            if(panelfeaturestate != null)
                closePanel(panelfeaturestate, true);
        }

        closeContextMenu();
    }

    public final void closePanel(int i)
    {
        if(i == 0 && mDecorContentParent != null && mDecorContentParent.canShowOverflowMenu() && ViewConfiguration.get(getContext()).hasPermanentMenuKey() ^ true)
            mDecorContentParent.hideOverflowMenu();
        else
        if(i == 6)
            closeContextMenu();
        else
            closePanel(getPanelState(i, true), true);
    }

    public final void closePanel(PanelFeatureState panelfeaturestate, boolean flag)
    {
        if(flag && panelfeaturestate.featureId == 0 && mDecorContentParent != null && mDecorContentParent.isOverflowMenuShowing())
        {
            checkCloseActionMenu(panelfeaturestate.menu);
            return;
        }
        WindowManager windowmanager = getWindowManager();
        if(windowmanager != null && panelfeaturestate.isOpen)
        {
            if(panelfeaturestate.decorView != null)
            {
                windowmanager.removeView(panelfeaturestate.decorView);
                if(panelfeaturestate.isCompact)
                    sRotationWatcher.removeWindow(this);
            }
            if(flag)
                callOnPanelClosed(panelfeaturestate.featureId, panelfeaturestate, null);
        }
        panelfeaturestate.isPrepared = false;
        panelfeaturestate.isHandled = false;
        panelfeaturestate.isOpen = false;
        panelfeaturestate.shownPanelView = null;
        if(panelfeaturestate.isInExpandedMode)
        {
            panelfeaturestate.refreshDecorView = true;
            panelfeaturestate.isInExpandedMode = false;
        }
        if(mPreparedPanel == panelfeaturestate)
        {
            mPreparedPanel = null;
            mPanelChordingKey = 0;
        }
    }

    protected void dispatchWindowAttributesChanged(android.view.WindowManager.LayoutParams layoutparams)
    {
        super.dispatchWindowAttributesChanged(layoutparams);
        if(mDecor != null)
            mDecor.updateColorViews(null, true);
    }

    void doInvalidatePanelMenu(int i)
    {
        PanelFeatureState panelfeaturestate = getPanelState(i, false);
        if(panelfeaturestate == null)
            return;
        if(panelfeaturestate.menu != null)
        {
            Bundle bundle = new Bundle();
            panelfeaturestate.menu.saveActionViewStates(bundle);
            if(bundle.size() > 0)
                panelfeaturestate.frozenActionViewState = bundle;
            panelfeaturestate.menu.stopDispatchingItemsChanged();
            panelfeaturestate.menu.clear();
        }
        panelfeaturestate.refreshMenuContent = true;
        panelfeaturestate.refreshDecorView = true;
        if((i == 8 || i == 0) && mDecorContentParent != null)
        {
            PanelFeatureState panelfeaturestate1 = getPanelState(0, false);
            if(panelfeaturestate1 != null)
            {
                panelfeaturestate1.isPrepared = false;
                preparePanel(panelfeaturestate1, null);
            }
        }
    }

    void doPendingInvalidatePanelMenu()
    {
        if(mInvalidatePanelMenuPosted)
        {
            mDecor.removeCallbacks(mInvalidatePanelMenuRunnable);
            mInvalidatePanelMenuRunnable.run();
        }
    }

    public PanelFeatureState findMenuPanel(Menu menu)
    {
        PanelFeatureState apanelfeaturestate[] = mPanels;
        int i;
        int j;
        if(apanelfeaturestate != null)
            i = apanelfeaturestate.length;
        else
            i = 0;
        for(j = 0; j < i; j++)
        {
            PanelFeatureState panelfeaturestate = apanelfeaturestate[j];
            if(panelfeaturestate != null && panelfeaturestate.menu == menu)
                return panelfeaturestate;
        }

        return null;
    }

    protected DecorView generateDecor(int i)
    {
        if(!mUseDecorContext) goto _L2; else goto _L1
_L1:
        Object obj = getContext().getApplicationContext();
        if(obj != null) goto _L4; else goto _L3
_L3:
        obj = getContext();
_L6:
        return new DecorView(((Context) (obj)), i, this, getAttributes());
_L4:
        DecorContext decorcontext = new DecorContext(((Context) (obj)), getContext().getResources());
        obj = decorcontext;
        if(mTheme != -1)
        {
            decorcontext.setTheme(mTheme);
            obj = decorcontext;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        obj = getContext();
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected ViewGroup generateLayout(DecorView decorview)
    {
        Object obj = getWindowStyle();
        mIsFloating = ((TypedArray) (obj)).getBoolean(4, false);
        int i = 0x10100 & getForcedWindowFlags();
        boolean flag;
        Object obj1;
        int j;
        boolean flag1;
        int k;
        boolean flag2;
        if(mIsFloating)
        {
            setLayout(-2, -2);
            setFlags(0, i);
        } else
        {
            setFlags(0x10100, i);
        }
        if(((TypedArray) (obj)).getBoolean(3, false))
            requestFeature(1);
        else
        if(((TypedArray) (obj)).getBoolean(15, false))
            requestFeature(8);
        if(((TypedArray) (obj)).getBoolean(17, false))
            requestFeature(9);
        if(((TypedArray) (obj)).getBoolean(16, false))
            requestFeature(10);
        if(((TypedArray) (obj)).getBoolean(25, false))
            requestFeature(11);
        if(((TypedArray) (obj)).getBoolean(9, false))
            setFlags(1024, getForcedWindowFlags() & 0x400);
        if(((TypedArray) (obj)).getBoolean(23, false))
            setFlags(0x4000000, getForcedWindowFlags() & 0x4000000);
        if(((TypedArray) (obj)).getBoolean(24, false))
            setFlags(0x8000000, getForcedWindowFlags() & 0x8000000);
        if(((TypedArray) (obj)).getBoolean(22, false))
            setFlags(0x2000000, getForcedWindowFlags() & 0x2000000);
        if(((TypedArray) (obj)).getBoolean(14, false))
            setFlags(0x100000, getForcedWindowFlags() & 0x100000);
        if(getContext().getApplicationInfo().targetSdkVersion >= 11)
            flag = true;
        else
            flag = false;
        if(((TypedArray) (obj)).getBoolean(18, flag))
            setFlags(0x800000, getForcedWindowFlags() & 0x800000);
        ((TypedArray) (obj)).getValue(19, mMinWidthMajor);
        ((TypedArray) (obj)).getValue(20, mMinWidthMinor);
        if(((TypedArray) (obj)).hasValue(54))
        {
            if(mFixedWidthMajor == null)
                mFixedWidthMajor = new TypedValue();
            ((TypedArray) (obj)).getValue(54, mFixedWidthMajor);
        }
        if(((TypedArray) (obj)).hasValue(55))
        {
            if(mFixedWidthMinor == null)
                mFixedWidthMinor = new TypedValue();
            ((TypedArray) (obj)).getValue(55, mFixedWidthMinor);
        }
        if(((TypedArray) (obj)).hasValue(52))
        {
            if(mFixedHeightMajor == null)
                mFixedHeightMajor = new TypedValue();
            ((TypedArray) (obj)).getValue(52, mFixedHeightMajor);
        }
        if(((TypedArray) (obj)).hasValue(53))
        {
            if(mFixedHeightMinor == null)
                mFixedHeightMinor = new TypedValue();
            ((TypedArray) (obj)).getValue(53, mFixedHeightMinor);
        }
        if(((TypedArray) (obj)).getBoolean(26, false))
            requestFeature(12);
        if(((TypedArray) (obj)).getBoolean(45, false))
            requestFeature(13);
        mIsTranslucent = ((TypedArray) (obj)).getBoolean(5, false);
        obj1 = getContext();
        j = ((Context) (obj1)).getApplicationInfo().targetSdkVersion;
        if(j < 11)
            i = 1;
        else
            i = 0;
        if(j < 14)
            k = 1;
        else
            k = 0;
        if(j < 21)
            flag1 = true;
        else
            flag1 = false;
        flag2 = ((Context) (obj1)).getResources().getBoolean(0x11200f1);
        if(hasFeature(8))
            flag = hasFeature(1);
        else
            flag = true;
        if(i != 0 || k != 0 && flag2 && flag)
            setNeedsMenuKey(1);
        else
            setNeedsMenuKey(2);
        if(!mForcedStatusBarColor)
            mStatusBarColor = ((TypedArray) (obj)).getColor(35, 0xff000000);
        if(!mForcedNavigationBarColor)
        {
            mNavigationBarColor = ((TypedArray) (obj)).getColor(36, 0xff000000);
            mNavigationBarDividerColor = ((TypedArray) (obj)).getColor(50, 0);
            PhoneWindowInjector.onNavigationBarColorChange(this);
        }
        obj1 = getAttributes();
        if(!mIsFloating)
        {
            if(!flag1 && ((TypedArray) (obj)).getBoolean(34, false))
                setFlags(0x80000000, getForcedWindowFlags() & 0x80000000);
            if(mDecor.mForceWindowDrawsStatusBarBackground)
                obj1.privateFlags = ((android.view.WindowManager.LayoutParams) (obj1)).privateFlags | 0x20000;
        }
        if(((TypedArray) (obj)).getBoolean(46, false))
            decorview.setSystemUiVisibility(decorview.getSystemUiVisibility() | 0x2000);
        if(((TypedArray) (obj)).getBoolean(49, false))
            decorview.setSystemUiVisibility(decorview.getSystemUiVisibility() | 0x10);
        if((mAlwaysReadCloseOnTouchAttr || getContext().getApplicationInfo().targetSdkVersion >= 11) && ((TypedArray) (obj)).getBoolean(21, false))
            setCloseOnTouchOutsideIfNotSet(true);
        if(!hasSoftInputMode())
            obj1.softInputMode = ((TypedArray) (obj)).getInt(13, ((android.view.WindowManager.LayoutParams) (obj1)).softInputMode);
        if(((TypedArray) (obj)).getBoolean(11, mIsFloating))
        {
            if((getForcedWindowFlags() & 2) == 0)
                obj1.flags = ((android.view.WindowManager.LayoutParams) (obj1)).flags | 2;
            if(!haveDimAmount())
                obj1.dimAmount = ((TypedArray) (obj)).getFloat(0, 0.5F);
        }
        if(((android.view.WindowManager.LayoutParams) (obj1)).windowAnimations == 0)
            obj1.windowAnimations = ((TypedArray) (obj)).getResourceId(8, 0);
        if(getContainer() == null)
        {
            if(mBackgroundDrawable == null)
            {
                if(mBackgroundResource == 0)
                    mBackgroundResource = ((TypedArray) (obj)).getResourceId(1, 0);
                if(mFrameResource == 0)
                    mFrameResource = ((TypedArray) (obj)).getResourceId(2, 0);
                mBackgroundFallbackResource = ((TypedArray) (obj)).getResourceId(47, 0);
            }
            if(mLoadElevation)
                mElevation = ((TypedArray) (obj)).getDimension(38, 0.0F);
            mClipToOutline = ((TypedArray) (obj)).getBoolean(39, false);
            mTextColor = ((TypedArray) (obj)).getColor(7, 0);
        }
        k = getLocalFeatures();
        if((k & 0x800) != 0)
        {
            i = 0x10900e6;
            setCloseOnSwipeEnabled(true);
        } else
        if((k & 0x18) != 0)
        {
            if(mIsFloating)
            {
                decorview = new TypedValue();
                getContext().getTheme().resolveAttribute(0x1110026, decorview, true);
                i = ((TypedValue) (decorview)).resourceId;
            } else
            {
                i = 0x10900e8;
            }
            removeFeature(8);
        } else
        if((k & 0x24) != 0 && (k & 0x100) == 0)
            i = 0x10900e3;
        else
        if((k & 0x80) != 0)
        {
            if(mIsFloating)
            {
                decorview = new TypedValue();
                getContext().getTheme().resolveAttribute(0x1110023, decorview, true);
                i = ((TypedValue) (decorview)).resourceId;
            } else
            {
                i = 0x10900e2;
            }
            removeFeature(8);
        } else
        if((k & 2) == 0)
        {
            if(mIsFloating)
            {
                decorview = new TypedValue();
                getContext().getTheme().resolveAttribute(0x1110025, decorview, true);
                i = ((TypedValue) (decorview)).resourceId;
            } else
            if((k & 0x100) != 0)
                i = ((TypedArray) (obj)).getResourceId(51, 0x10900e1);
            else
                i = 0x10900e7;
        } else
        if((k & 0x400) != 0)
            i = 0x10900e5;
        else
            i = 0x10900e4;
        mDecor.startChanging();
        mDecor.onResourcesLoaded(mLayoutInflater, i);
        obj = (ViewGroup)findViewById(0x1020002);
        if(obj == null)
            throw new RuntimeException("Window couldn't find content container view");
        if((k & 0x20) != 0)
        {
            decorview = getCircularProgressBar(false);
            if(decorview != null)
                decorview.setIndeterminate(true);
        }
        if((k & 0x800) != 0)
            registerSwipeCallbacks(((ViewGroup) (obj)));
        if(getContainer() == null)
        {
            if(mBackgroundResource != 0)
                decorview = getContext().getDrawable(mBackgroundResource);
            else
                decorview = mBackgroundDrawable;
            mDecor.setWindowBackground(decorview);
            if(mFrameResource != 0)
                decorview = getContext().getDrawable(mFrameResource);
            else
                decorview = null;
            mDecor.setWindowFrame(decorview);
            mDecor.setElevation(mElevation);
            mDecor.setClipToOutline(mClipToOutline);
            if(mTitle != null)
                setTitle(mTitle);
            if(mTitleColor == 0)
                mTitleColor = mTextColor;
            setTitleColor(mTitleColor);
        }
        mDecor.finishChanging();
        return ((ViewGroup) (obj));
    }

    public boolean getAllowEnterTransitionOverlap()
    {
        boolean flag;
        if(mAllowEnterTransitionOverlap == null)
            flag = true;
        else
            flag = mAllowEnterTransitionOverlap.booleanValue();
        return flag;
    }

    public boolean getAllowReturnTransitionOverlap()
    {
        boolean flag;
        if(mAllowReturnTransitionOverlap == null)
            flag = true;
        else
            flag = mAllowReturnTransitionOverlap.booleanValue();
        return flag;
    }

    AudioManager getAudioManager()
    {
        if(mAudioManager == null)
            mAudioManager = (AudioManager)getContext().getSystemService("audio");
        return mAudioManager;
    }

    public Scene getContentScene()
    {
        return mContentScene;
    }

    public View getCurrentFocus()
    {
        View view = null;
        if(mDecor != null)
            view = mDecor.findFocus();
        return view;
    }

    int getDecorCaptionShade()
    {
        return mDecorCaptionShade;
    }

    public final View getDecorView()
    {
        if(mDecor == null || mForceDecorInstall)
            installDecor();
        return mDecor;
    }

    public float getElevation()
    {
        return mElevation;
    }

    public Transition getEnterTransition()
    {
        return mEnterTransition;
    }

    public Transition getExitTransition()
    {
        return mExitTransition;
    }

    public LayoutInflater getLayoutInflater()
    {
        return mLayoutInflater;
    }

    int getLocalFeaturesPrivate()
    {
        return super.getLocalFeatures();
    }

    public MediaController getMediaController()
    {
        return mMediaController;
    }

    public int getNavigationBarColor()
    {
        return mNavigationBarColor;
    }

    PanelFeatureState getPanelState(int i, boolean flag)
    {
        return getPanelState(i, flag, null);
    }

    public Transition getReenterTransition()
    {
        Transition transition;
        if(mReenterTransition == USE_DEFAULT_TRANSITION)
            transition = getExitTransition();
        else
            transition = mReenterTransition;
        return transition;
    }

    public Transition getReturnTransition()
    {
        Transition transition;
        if(mReturnTransition == USE_DEFAULT_TRANSITION)
            transition = getEnterTransition();
        else
            transition = mReturnTransition;
        return transition;
    }

    public Transition getSharedElementEnterTransition()
    {
        return mSharedElementEnterTransition;
    }

    public Transition getSharedElementExitTransition()
    {
        return mSharedElementExitTransition;
    }

    public Transition getSharedElementReenterTransition()
    {
        Transition transition;
        if(mSharedElementReenterTransition == USE_DEFAULT_TRANSITION)
            transition = getSharedElementExitTransition();
        else
            transition = mSharedElementReenterTransition;
        return transition;
    }

    public Transition getSharedElementReturnTransition()
    {
        Transition transition;
        if(mSharedElementReturnTransition == USE_DEFAULT_TRANSITION)
            transition = getSharedElementEnterTransition();
        else
            transition = mSharedElementReturnTransition;
        return transition;
    }

    public boolean getSharedElementsUseOverlay()
    {
        boolean flag;
        if(mSharedElementsUseOverlay == null)
            flag = true;
        else
            flag = mSharedElementsUseOverlay.booleanValue();
        return flag;
    }

    public int getStatusBarColor()
    {
        return mStatusBarColor;
    }

    public long getTransitionBackgroundFadeDuration()
    {
        long l;
        if(mBackgroundFadeDurationMillis < 0L)
            l = 300L;
        else
            l = mBackgroundFadeDurationMillis;
        return l;
    }

    public TransitionManager getTransitionManager()
    {
        return mTransitionManager;
    }

    public int getVolumeControlStream()
    {
        return mVolumeControlStreamType;
    }

    protected boolean initializePanelContent(PanelFeatureState panelfeaturestate)
    {
        if(panelfeaturestate.createdPanelView != null)
        {
            panelfeaturestate.shownPanelView = panelfeaturestate.createdPanelView;
            return true;
        }
        if(panelfeaturestate.menu == null)
            return false;
        if(mPanelMenuPresenterCallback == null)
            mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(null);
        MenuView menuview;
        if(panelfeaturestate.isInListMode())
            menuview = panelfeaturestate.getListMenuView(getContext(), mPanelMenuPresenterCallback);
        else
            menuview = panelfeaturestate.getIconMenuView(getContext(), mPanelMenuPresenterCallback);
        panelfeaturestate.shownPanelView = (View)menuview;
        if(panelfeaturestate.shownPanelView != null)
        {
            int i = menuview.getWindowAnimations();
            if(i != 0)
                panelfeaturestate.windowAnimations = i;
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean initializePanelDecor(PanelFeatureState panelfeaturestate)
    {
        panelfeaturestate.decorView = generateDecor(panelfeaturestate.featureId);
        panelfeaturestate.gravity = 81;
        panelfeaturestate.setStyle(getContext());
        TypedArray typedarray = getContext().obtainStyledAttributes(null, com.android.internal.R.styleable.Window, 0, panelfeaturestate.listPresenterTheme);
        float f = typedarray.getDimension(38, 0.0F);
        if(f != 0.0F)
            panelfeaturestate.decorView.setElevation(f);
        typedarray.recycle();
        return true;
    }

    protected boolean initializePanelMenu(PanelFeatureState panelfeaturestate)
    {
label0:
        {
            Context context = getContext();
            Object obj;
            if(panelfeaturestate.featureId != 0)
            {
                obj = context;
                if(panelfeaturestate.featureId != 8)
                    break label0;
            }
            obj = context;
            if(mDecorContentParent != null)
            {
                TypedValue typedvalue = new TypedValue();
                android.content.res.Resources.Theme theme = context.getTheme();
                theme.resolveAttribute(0x1010431, typedvalue, true);
                obj = null;
                Object obj1;
                if(typedvalue.resourceId != 0)
                {
                    obj = context.getResources().newTheme();
                    ((android.content.res.Resources.Theme) (obj)).setTo(theme);
                    ((android.content.res.Resources.Theme) (obj)).applyStyle(typedvalue.resourceId, true);
                    ((android.content.res.Resources.Theme) (obj)).resolveAttribute(0x1010397, typedvalue, true);
                } else
                {
                    theme.resolveAttribute(0x1010397, typedvalue, true);
                }
                obj1 = obj;
                if(typedvalue.resourceId != 0)
                {
                    obj1 = obj;
                    if(obj == null)
                    {
                        obj1 = context.getResources().newTheme();
                        ((android.content.res.Resources.Theme) (obj1)).setTo(theme);
                    }
                    ((android.content.res.Resources.Theme) (obj1)).applyStyle(typedvalue.resourceId, true);
                }
                obj = context;
                if(obj1 != null)
                {
                    obj = new ContextThemeWrapper(context, 0);
                    ((Context) (obj)).getTheme().setTo(((android.content.res.Resources.Theme) (obj1)));
                }
            }
        }
        obj = new MenuBuilder(((Context) (obj)));
        ((MenuBuilder) (obj)).setCallback(this);
        panelfeaturestate.setMenu(((MenuBuilder) (obj)));
        return true;
    }

    public void injectInputEvent(InputEvent inputevent)
    {
        getViewRootImpl().dispatchInputEvent(inputevent);
    }

    public void invalidatePanelMenu(int i)
    {
        mInvalidatePanelMenuFeatures = mInvalidatePanelMenuFeatures | 1 << i;
        if(!mInvalidatePanelMenuPosted && mDecor != null)
        {
            mDecor.postOnAnimation(mInvalidatePanelMenuRunnable);
            mInvalidatePanelMenuPosted = true;
        }
    }

    public boolean isFloating()
    {
        return mIsFloating;
    }

    public boolean isShortcutKey(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        PanelFeatureState panelfeaturestate = getPanelState(0, false);
        boolean flag1 = flag;
        if(panelfeaturestate != null)
        {
            flag1 = flag;
            if(panelfeaturestate.menu != null)
                flag1 = panelfeaturestate.menu.isShortcutKey(i, keyevent);
        }
        return flag1;
    }

    boolean isShowingWallpaper()
    {
        boolean flag = false;
        if((getAttributes().flags & 0x100000) != 0)
            flag = true;
        return flag;
    }

    public boolean isTranslucent()
    {
        return mIsTranslucent;
    }

    protected void onActive()
    {
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if(mDecorContentParent == null)
        {
            PanelFeatureState panelfeaturestate = getPanelState(0, false);
            if(panelfeaturestate != null && panelfeaturestate.menu != null)
                if(panelfeaturestate.isOpen)
                {
                    configuration = new Bundle();
                    if(panelfeaturestate.iconMenuPresenter != null)
                        panelfeaturestate.iconMenuPresenter.saveHierarchyState(configuration);
                    if(panelfeaturestate.listMenuPresenter != null)
                        panelfeaturestate.listMenuPresenter.saveHierarchyState(configuration);
                    clearMenuViews(panelfeaturestate);
                    reopenMenu(false);
                    if(panelfeaturestate.iconMenuPresenter != null)
                        panelfeaturestate.iconMenuPresenter.restoreHierarchyState(configuration);
                    if(panelfeaturestate.listMenuPresenter != null)
                        panelfeaturestate.listMenuPresenter.restoreHierarchyState(configuration);
                } else
                {
                    clearMenuViews(panelfeaturestate);
                }
        }
    }

    protected void onDrawableChanged(int i, Drawable drawable, int j)
    {
        ImageView imageview;
        if(i == 3)
            imageview = getLeftIconView();
        else
        if(i == 4)
            imageview = getRightIconView();
        else
            return;
        if(drawable != null)
        {
            drawable.setAlpha(j);
            imageview.setImageDrawable(drawable);
            imageview.setVisibility(0);
        } else
        {
            imageview.setVisibility(8);
        }
    }

    protected void onIntChanged(int i, int j)
    {
        if(i != 2 && i != 5) goto _L2; else goto _L1
_L1:
        updateProgressBars(j);
_L4:
        return;
_L2:
        if(i == 7)
        {
            FrameLayout framelayout = (FrameLayout)findViewById(0x1020457);
            if(framelayout != null)
                mLayoutInflater.inflate(j, framelayout);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected boolean onKeyDown(int i, int j, KeyEvent keyevent)
    {
        android.view.KeyEvent.DispatcherState dispatcherstate;
        if(mDecor != null)
            dispatcherstate = mDecor.getKeyDispatcherState();
        else
            dispatcherstate = null;
        j;
        JVM INSTR lookupswitch 16: default 156
    //                   4: 293
    //                   24: 164
    //                   25: 164
    //                   79: 254
    //                   82: 276
    //                   85: 254
    //                   86: 254
    //                   87: 254
    //                   88: 254
    //                   89: 254
    //                   90: 254
    //                   91: 254
    //                   126: 254
    //                   127: 254
    //                   130: 254
    //                   164: 164;
           goto _L1 _L2 _L3 _L3 _L4 _L5 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L3
_L1:
        return false;
_L3:
        if(mMediaController == null) goto _L7; else goto _L6
_L6:
        i = 0;
        j;
        JVM INSTR lookupswitch 3: default 208
    //                   24: 219
    //                   25: 224
    //                   164: 229;
           goto _L8 _L9 _L10 _L11
_L8:
        mMediaController.adjustVolume(i, 1);
_L12:
        return true;
_L9:
        i = 1;
          goto _L8
_L10:
        i = -1;
          goto _L8
_L11:
        i = 101;
          goto _L8
_L7:
        MediaSessionLegacyHelper.getHelper(getContext()).sendVolumeKeyEvent(keyevent, mVolumeControlStreamType, false);
          goto _L12
_L4:
        return mMediaController != null && mMediaController.dispatchMediaButtonEvent(keyevent);
_L5:
        j = i;
        if(i < 0)
            j = 0;
        onKeyDownPanel(j, keyevent);
        return true;
_L2:
        if(keyevent.getRepeatCount() <= 0 && i >= 0)
        {
            if(dispatcherstate != null)
                dispatcherstate.startTracking(keyevent, this);
            return true;
        }
        if(true) goto _L1; else goto _L13
_L13:
    }

    public final boolean onKeyDownPanel(int i, KeyEvent keyevent)
    {
        int j = keyevent.getKeyCode();
        if(keyevent.getRepeatCount() == 0)
        {
            mPanelChordingKey = j;
            PanelFeatureState panelfeaturestate = getPanelState(i, false);
            if(panelfeaturestate != null && panelfeaturestate.isOpen ^ true)
                return preparePanel(panelfeaturestate, keyevent);
        }
        return false;
    }

    protected boolean onKeyUp(int i, int j, KeyEvent keyevent)
    {
        android.view.KeyEvent.DispatcherState dispatcherstate;
        if(mDecor != null)
            dispatcherstate = mDecor.getKeyDispatcherState();
        else
            dispatcherstate = null;
        if(dispatcherstate != null)
            dispatcherstate.handleUpEvent(keyevent);
        j;
        JVM INSTR lookupswitch 18: default 184
    //                   4: 286
    //                   24: 192
    //                   25: 192
    //                   79: 248
    //                   82: 270
    //                   84: 342
    //                   85: 248
    //                   86: 248
    //                   87: 248
    //                   88: 248
    //                   89: 248
    //                   90: 248
    //                   91: 248
    //                   126: 248
    //                   127: 248
    //                   130: 248
    //                   164: 231
    //                   171: 376;
           goto _L1 _L2 _L3 _L3 _L4 _L5 _L6 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L7 _L8
_L1:
        return false;
_L3:
        if(mMediaController != null)
            mMediaController.adjustVolume(0, 4116);
        else
            MediaSessionLegacyHelper.getHelper(getContext()).sendVolumeKeyEvent(keyevent, mVolumeControlStreamType, false);
        return true;
_L7:
        MediaSessionLegacyHelper.getHelper(getContext()).sendVolumeKeyEvent(keyevent, 0x80000000, false);
        return true;
_L4:
        return mMediaController != null && mMediaController.dispatchMediaButtonEvent(keyevent);
_L5:
        j = i;
        if(i < 0)
            j = 0;
        onKeyUpPanel(j, keyevent);
        return true;
_L2:
        if(i >= 0 && keyevent.isTracking() && keyevent.isCanceled() ^ true)
        {
            if(i == 0)
            {
                keyevent = getPanelState(i, false);
                if(keyevent != null && ((PanelFeatureState) (keyevent)).isInExpandedMode)
                {
                    reopenMenu(true);
                    return true;
                }
            }
            closePanel(i);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(!getKeyguardManager().inKeyguardRestrictedInputMode())
        {
            if(keyevent.isTracking() && keyevent.isCanceled() ^ true)
                launchDefaultSearch(keyevent);
            return true;
        }
        if(true) goto _L1; else goto _L8
_L8:
        if(mSupportsPictureInPicture && keyevent.isCanceled() ^ true)
            getWindowControllerCallback().enterPictureInPictureModeIfPossible();
        return true;
    }

    public final void onKeyUpPanel(int i, KeyEvent keyevent)
    {
        if(mPanelChordingKey != 0)
        {
            mPanelChordingKey = 0;
            PanelFeatureState panelfeaturestate;
            for(panelfeaturestate = getPanelState(i, false); keyevent.isCanceled() || mDecor != null && mDecor.mPrimaryActionMode != null || panelfeaturestate == null;)
                return;

            boolean flag = false;
            boolean flag1;
            if(i == 0 && mDecorContentParent != null && mDecorContentParent.canShowOverflowMenu() && ViewConfiguration.get(getContext()).hasPermanentMenuKey() ^ true)
            {
                if(!mDecorContentParent.isOverflowMenuShowing())
                {
                    flag1 = flag;
                    if(!isDestroyed())
                    {
                        flag1 = flag;
                        if(preparePanel(panelfeaturestate, keyevent))
                            flag1 = mDecorContentParent.showOverflowMenu();
                    }
                } else
                {
                    flag1 = mDecorContentParent.hideOverflowMenu();
                }
            } else
            if(panelfeaturestate.isOpen || panelfeaturestate.isHandled)
            {
                flag1 = panelfeaturestate.isOpen;
                closePanel(panelfeaturestate, true);
            } else
            {
                flag1 = flag;
                if(panelfeaturestate.isPrepared)
                {
                    boolean flag2 = true;
                    if(panelfeaturestate.refreshMenuContent)
                    {
                        panelfeaturestate.isPrepared = false;
                        flag2 = preparePanel(panelfeaturestate, keyevent);
                    }
                    flag1 = flag;
                    if(flag2)
                    {
                        EventLog.writeEvent(50001, 0);
                        openPanel(panelfeaturestate, keyevent);
                        flag1 = true;
                    }
                }
            }
            if(flag1)
            {
                keyevent = (AudioManager)getContext().getSystemService("audio");
                if(keyevent != null)
                    keyevent.playSoundEffect(0);
                else
                    Log.w("PhoneWindow", "Couldn't get audio manager");
            }
        }
    }

    public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        android.view.Window.Callback callback = getCallback();
        if(callback != null && isDestroyed() ^ true)
        {
            menubuilder = findMenuPanel(menubuilder.getRootMenu());
            if(menubuilder != null)
                return callback.onMenuItemSelected(((PanelFeatureState) (menubuilder)).featureId, menuitem);
        }
        return false;
    }

    public void onMenuModeChange(MenuBuilder menubuilder)
    {
        reopenMenu(true);
    }

    public void onMultiWindowModeChanged()
    {
        if(mDecor != null)
            mDecor.onConfigurationChanged(getContext().getResources().getConfiguration());
    }

    void onOptionsPanelRotationChanged()
    {
        PanelFeatureState panelfeaturestate = getPanelState(0, false);
        if(panelfeaturestate == null)
            return;
        android.view.WindowManager.LayoutParams layoutparams;
        if(panelfeaturestate.decorView != null)
            layoutparams = (android.view.WindowManager.LayoutParams)panelfeaturestate.decorView.getLayoutParams();
        else
            layoutparams = null;
        if(layoutparams != null)
        {
            layoutparams.gravity = getOptionsPanelGravity();
            WindowManager windowmanager = getWindowManager();
            if(windowmanager != null)
                windowmanager.updateViewLayout(panelfeaturestate.decorView, layoutparams);
        }
    }

    public void onPictureInPictureModeChanged(boolean flag)
    {
        if(mDecor != null)
            mDecor.updatePictureInPictureOutlineProvider(flag);
    }

    void onViewRootImplSet(ViewRootImpl viewrootimpl)
    {
        viewrootimpl.setActivityConfigCallback(mActivityConfigCallback);
    }

    public final void openPanel(int i, KeyEvent keyevent)
    {
        if(i == 0 && mDecorContentParent != null && mDecorContentParent.canShowOverflowMenu() && ViewConfiguration.get(getContext()).hasPermanentMenuKey() ^ true)
            mDecorContentParent.showOverflowMenu();
        else
            openPanel(getPanelState(i, true), keyevent);
    }

    void openPanelsAfterRestore()
    {
        PanelFeatureState apanelfeaturestate[] = mPanels;
        if(apanelfeaturestate == null)
            return;
        for(int i = apanelfeaturestate.length - 1; i >= 0; i--)
        {
            PanelFeatureState panelfeaturestate = apanelfeaturestate[i];
            if(panelfeaturestate == null)
                continue;
            panelfeaturestate.applyFrozenState();
            if(!panelfeaturestate.isOpen && panelfeaturestate.wasLastOpen)
            {
                panelfeaturestate.isInExpandedMode = panelfeaturestate.wasLastExpanded;
                openPanel(panelfeaturestate, null);
            }
        }

    }

    public final View peekDecorView()
    {
        return mDecor;
    }

    public boolean performContextMenuIdentifierAction(int i, int j)
    {
        boolean flag;
        if(mContextMenu != null)
            flag = mContextMenu.performIdentifierAction(i, j);
        else
            flag = false;
        return flag;
    }

    public boolean performPanelIdentifierAction(int i, int j, int k)
    {
        PanelFeatureState panelfeaturestate = getPanelState(i, true);
        if(!preparePanel(panelfeaturestate, new KeyEvent(0, 82)))
            return false;
        if(panelfeaturestate.menu == null)
            return false;
        boolean flag = panelfeaturestate.menu.performIdentifierAction(j, k);
        if(mDecorContentParent == null)
            closePanel(panelfeaturestate, true);
        return flag;
    }

    public boolean performPanelShortcut(int i, int j, KeyEvent keyevent, int k)
    {
        return performPanelShortcut(getPanelState(i, false), j, keyevent, k);
    }

    boolean performPanelShortcut(PanelFeatureState panelfeaturestate, int i, KeyEvent keyevent, int j)
    {
        boolean flag1;
label0:
        {
            if(keyevent.isSystem() || panelfeaturestate == null)
                return false;
            boolean flag = false;
            if(!panelfeaturestate.isPrepared)
            {
                flag1 = flag;
                if(!preparePanel(panelfeaturestate, keyevent))
                    break label0;
            }
            flag1 = flag;
            if(panelfeaturestate.menu != null)
                flag1 = panelfeaturestate.menu.performShortcut(i, keyevent, j);
        }
        if(flag1)
        {
            panelfeaturestate.isHandled = true;
            if((j & 1) == 0 && mDecorContentParent == null)
                closePanel(panelfeaturestate, true);
        }
        return flag1;
    }

    public final boolean preparePanel(PanelFeatureState panelfeaturestate, KeyEvent keyevent)
    {
        if(isDestroyed())
            return false;
        if(panelfeaturestate.isPrepared)
            return true;
        if(mPreparedPanel != null && mPreparedPanel != panelfeaturestate)
            closePanel(mPreparedPanel, false);
        android.view.Window.Callback callback = getCallback();
        if(callback != null)
            panelfeaturestate.createdPanelView = callback.onCreatePanelView(panelfeaturestate.featureId);
        int i;
        if(panelfeaturestate.featureId == 0 || panelfeaturestate.featureId == 8)
            i = 1;
        else
            i = 0;
        if(i && mDecorContentParent != null)
            mDecorContentParent.setMenuPrepared();
        if(panelfeaturestate.createdPanelView == null)
        {
            if(panelfeaturestate.menu == null || panelfeaturestate.refreshMenuContent)
            {
                if(panelfeaturestate.menu == null && (!initializePanelMenu(panelfeaturestate) || panelfeaturestate.menu == null))
                    return false;
                if(i && mDecorContentParent != null)
                {
                    if(mActionMenuPresenterCallback == null)
                        mActionMenuPresenterCallback = new ActionMenuPresenterCallback(null);
                    mDecorContentParent.setMenu(panelfeaturestate.menu, mActionMenuPresenterCallback);
                }
                panelfeaturestate.menu.stopDispatchingItemsChanged();
                if(callback == null || callback.onCreatePanelMenu(panelfeaturestate.featureId, panelfeaturestate.menu) ^ true)
                {
                    panelfeaturestate.setMenu(null);
                    if(i && mDecorContentParent != null)
                        mDecorContentParent.setMenu(null, mActionMenuPresenterCallback);
                    return false;
                }
                panelfeaturestate.refreshMenuContent = false;
            }
            panelfeaturestate.menu.stopDispatchingItemsChanged();
            if(panelfeaturestate.frozenActionViewState != null)
            {
                panelfeaturestate.menu.restoreActionViewStates(panelfeaturestate.frozenActionViewState);
                panelfeaturestate.frozenActionViewState = null;
            }
            if(!callback.onPreparePanel(panelfeaturestate.featureId, panelfeaturestate.createdPanelView, panelfeaturestate.menu))
            {
                if(i && mDecorContentParent != null)
                    mDecorContentParent.setMenu(null, mActionMenuPresenterCallback);
                panelfeaturestate.menu.startDispatchingItemsChanged();
                return false;
            }
            boolean flag;
            if(keyevent != null)
                i = keyevent.getDeviceId();
            else
                i = -1;
            if(KeyCharacterMap.load(i).getKeyboardType() != 1)
                flag = true;
            else
                flag = false;
            panelfeaturestate.qwertyMode = flag;
            panelfeaturestate.menu.setQwertyMode(panelfeaturestate.qwertyMode);
            panelfeaturestate.menu.startDispatchingItemsChanged();
        }
        panelfeaturestate.isPrepared = true;
        panelfeaturestate.isHandled = false;
        mPreparedPanel = panelfeaturestate;
        return true;
    }

    public void reportActivityRelaunched()
    {
        if(mDecor != null && mDecor.getViewRootImpl() != null)
            mDecor.getViewRootImpl().reportActivityRelaunched();
    }

    public boolean requestFeature(int i)
    {
        if(mContentParentExplicitlySet)
            throw new AndroidRuntimeException("requestFeature() must be called before adding content");
        int j = getFeatures();
        int k = j | 1 << i;
        if((k & 0x80) != 0 && (k & 0xffffcb3e) != 0)
            throw new AndroidRuntimeException("You cannot combine custom titles with other title features");
        if((j & 2) != 0 && i == 8)
            return false;
        if((j & 0x100) != 0 && i == 1)
            removeFeature(8);
        if((j & 0x100) != 0 && i == 11)
            throw new AndroidRuntimeException("You cannot combine swipe dismissal and the action bar.");
        if((j & 0x800) != 0 && i == 8)
            throw new AndroidRuntimeException("You cannot combine swipe dismissal and the action bar.");
        if(i == 5 && getContext().getPackageManager().hasSystemFeature("android.hardware.type.watch"))
            throw new AndroidRuntimeException("You cannot use indeterminate progress on a watch.");
        else
            return super.requestFeature(i);
    }

    public void restoreHierarchyState(Bundle bundle)
    {
        if(mContentParent == null)
            return;
        SparseArray sparsearray = bundle.getSparseParcelableArray("android:views");
        if(sparsearray != null)
            mContentParent.restoreHierarchyState(sparsearray);
        int i = bundle.getInt("android:focusedViewId", -1);
        if(i != -1)
        {
            Object obj = mContentParent.findViewById(i);
            if(obj != null)
                ((View) (obj)).requestFocus();
            else
                Log.w("PhoneWindow", (new StringBuilder()).append("Previously focused view reported id ").append(i).append(" during save, but can't be found during restore.").toString());
        }
        obj = bundle.getSparseParcelableArray("android:Panels");
        if(obj != null)
            restorePanelState(((SparseArray) (obj)));
        if(mDecorContentParent != null)
        {
            bundle = bundle.getSparseParcelableArray("android:ActionBar");
            if(bundle != null)
            {
                doPendingInvalidatePanelMenu();
                mDecorContentParent.restoreToolbarHierarchyState(bundle);
            } else
            {
                Log.w("PhoneWindow", "Missing saved instance states for action bar views! State will not be restored.");
            }
        }
    }

    public Bundle saveHierarchyState()
    {
        Bundle bundle = new Bundle();
        if(mContentParent == null)
            return bundle;
        Object obj = new SparseArray();
        mContentParent.saveHierarchyState(((SparseArray) (obj)));
        bundle.putSparseParcelableArray("android:views", ((SparseArray) (obj)));
        obj = mContentParent.findFocus();
        if(obj != null && ((View) (obj)).getId() != -1)
            bundle.putInt("android:focusedViewId", ((View) (obj)).getId());
        obj = new SparseArray();
        savePanelState(((SparseArray) (obj)));
        if(((SparseArray) (obj)).size() > 0)
            bundle.putSparseParcelableArray("android:Panels", ((SparseArray) (obj)));
        if(mDecorContentParent != null)
        {
            SparseArray sparsearray = new SparseArray();
            mDecorContentParent.saveToolbarHierarchyState(sparsearray);
            bundle.putSparseParcelableArray("android:ActionBar", sparsearray);
        }
        return bundle;
    }

    void sendCloseSystemWindows()
    {
        sendCloseSystemWindows(getContext(), null);
    }

    void sendCloseSystemWindows(String s)
    {
        sendCloseSystemWindows(getContext(), s);
    }

    public void setAllowEnterTransitionOverlap(boolean flag)
    {
        mAllowEnterTransitionOverlap = Boolean.valueOf(flag);
    }

    public void setAllowReturnTransitionOverlap(boolean flag)
    {
        mAllowReturnTransitionOverlap = Boolean.valueOf(flag);
    }

    public void setAttributes(android.view.WindowManager.LayoutParams layoutparams)
    {
        super.setAttributes(layoutparams);
        if(mDecor != null)
            mDecor.updateLogTag(layoutparams);
    }

    public final void setBackgroundDrawable(Drawable drawable)
    {
        int i = 0;
        if(drawable != mBackgroundDrawable || mBackgroundResource != 0)
        {
            mBackgroundResource = 0;
            mBackgroundDrawable = drawable;
            if(mDecor != null)
                mDecor.setWindowBackground(drawable);
            if(mBackgroundFallbackResource != 0)
            {
                DecorView decorview = mDecor;
                if(drawable == null)
                    i = mBackgroundFallbackResource;
                decorview.setBackgroundFallback(i);
            }
        }
    }

    public final void setChildDrawable(int i, Drawable drawable)
    {
        DrawableFeatureState drawablefeaturestate = getDrawableState(i, true);
        drawablefeaturestate.child = drawable;
        updateDrawable(i, drawablefeaturestate, false);
    }

    public final void setChildInt(int i, int j)
    {
        updateInt(i, j, false);
    }

    public final void setClipToOutline(boolean flag)
    {
        mClipToOutline = flag;
        if(mDecor != null)
            mDecor.setClipToOutline(flag);
    }

    public void setCloseOnSwipeEnabled(boolean flag)
    {
        if(hasFeature(11) && (mContentParent instanceof SwipeDismissLayout))
            ((SwipeDismissLayout)mContentParent).setDismissable(flag);
        super.setCloseOnSwipeEnabled(flag);
    }

    public final void setContainer(Window window)
    {
        super.setContainer(window);
    }

    public void setContentView(int i)
    {
        android.view.Window.Callback callback;
        if(mContentParent == null)
            installDecor();
        else
        if(!hasFeature(12))
            mContentParent.removeAllViews();
        if(hasFeature(12))
            transitionTo(Scene.getSceneForLayout(mContentParent, i, getContext()));
        else
            mLayoutInflater.inflate(i, mContentParent);
        mContentParent.requestApplyInsets();
        callback = getCallback();
        if(callback != null && isDestroyed() ^ true)
            callback.onContentChanged();
        mContentParentExplicitlySet = true;
    }

    public void setContentView(View view)
    {
        setContentView(view, new android.view.ViewGroup.LayoutParams(-1, -1));
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(mContentParent == null)
            installDecor();
        else
        if(!hasFeature(12))
            mContentParent.removeAllViews();
        if(hasFeature(12))
        {
            view.setLayoutParams(layoutparams);
            transitionTo(new Scene(mContentParent, view));
        } else
        {
            mContentParent.addView(view, layoutparams);
        }
        mContentParent.requestApplyInsets();
        view = getCallback();
        if(view != null && isDestroyed() ^ true)
            view.onContentChanged();
        mContentParentExplicitlySet = true;
    }

    public void setDecorCaptionShade(int i)
    {
        mDecorCaptionShade = i;
        if(mDecor != null)
            mDecor.updateDecorCaptionShade();
    }

    public void setDefaultIcon(int i)
    {
        if((mResourcesSetFlags & 1) != 0)
            return;
        mIconRes = i;
        if(mDecorContentParent != null && (!mDecorContentParent.hasIcon() || (mResourcesSetFlags & 4) != 0))
            if(i != 0)
            {
                mDecorContentParent.setIcon(i);
                mResourcesSetFlags = mResourcesSetFlags & -5;
            } else
            {
                mDecorContentParent.setIcon(getContext().getPackageManager().getDefaultActivityIcon());
                mResourcesSetFlags = mResourcesSetFlags | 4;
            }
    }

    public void setDefaultLogo(int i)
    {
        if((mResourcesSetFlags & 2) != 0)
            return;
        mLogoRes = i;
        if(mDecorContentParent != null && mDecorContentParent.hasLogo() ^ true)
            mDecorContentParent.setLogo(i);
    }

    protected void setDefaultWindowFormat(int i)
    {
        super.setDefaultWindowFormat(i);
    }

    public final void setElevation(float f)
    {
        mElevation = f;
        android.view.WindowManager.LayoutParams layoutparams = getAttributes();
        if(mDecor != null)
        {
            mDecor.setElevation(f);
            layoutparams.setSurfaceInsets(mDecor, true, false);
        }
        dispatchWindowAttributesChanged(layoutparams);
    }

    public void setEnterTransition(Transition transition)
    {
        mEnterTransition = transition;
    }

    public void setExitTransition(Transition transition)
    {
        mExitTransition = transition;
    }

    protected final void setFeatureDefaultDrawable(int i, Drawable drawable)
    {
        DrawableFeatureState drawablefeaturestate = getDrawableState(i, true);
        if(drawablefeaturestate.def != drawable)
        {
            drawablefeaturestate.def = drawable;
            updateDrawable(i, drawablefeaturestate, false);
        }
    }

    public final void setFeatureDrawable(int i, Drawable drawable)
    {
        DrawableFeatureState drawablefeaturestate = getDrawableState(i, true);
        drawablefeaturestate.resid = 0;
        drawablefeaturestate.uri = null;
        if(drawablefeaturestate.local != drawable)
        {
            drawablefeaturestate.local = drawable;
            updateDrawable(i, drawablefeaturestate, false);
        }
    }

    public void setFeatureDrawableAlpha(int i, int j)
    {
        DrawableFeatureState drawablefeaturestate = getDrawableState(i, true);
        if(drawablefeaturestate.alpha != j)
        {
            drawablefeaturestate.alpha = j;
            updateDrawable(i, drawablefeaturestate, false);
        }
    }

    public final void setFeatureDrawableResource(int i, int j)
    {
        if(j != 0)
        {
            DrawableFeatureState drawablefeaturestate = getDrawableState(i, true);
            if(drawablefeaturestate.resid != j)
            {
                drawablefeaturestate.resid = j;
                drawablefeaturestate.uri = null;
                drawablefeaturestate.local = getContext().getDrawable(j);
                updateDrawable(i, drawablefeaturestate, false);
            }
        } else
        {
            setFeatureDrawable(i, null);
        }
    }

    public final void setFeatureDrawableUri(int i, Uri uri)
    {
        if(uri != null)
        {
            DrawableFeatureState drawablefeaturestate = getDrawableState(i, true);
            if(drawablefeaturestate.uri == null || drawablefeaturestate.uri.equals(uri) ^ true)
            {
                drawablefeaturestate.resid = 0;
                drawablefeaturestate.uri = uri;
                drawablefeaturestate.local = loadImageURI(uri);
                updateDrawable(i, drawablefeaturestate, false);
            }
        } else
        {
            setFeatureDrawable(i, null);
        }
    }

    public final void setFeatureInt(int i, int j)
    {
        updateInt(i, j, false);
    }

    public void setIcon(int i)
    {
        mIconRes = i;
        mResourcesSetFlags = mResourcesSetFlags | 1;
        mResourcesSetFlags = mResourcesSetFlags & -5;
        if(mDecorContentParent != null)
            mDecorContentParent.setIcon(i);
    }

    public void setIsStartingWindow(boolean flag)
    {
        mIsStartingWindow = flag;
    }

    public void setLocalFocus(boolean flag, boolean flag1)
    {
        getViewRootImpl().windowFocusChanged(flag, flag1);
    }

    public void setLogo(int i)
    {
        mLogoRes = i;
        mResourcesSetFlags = mResourcesSetFlags | 2;
        if(mDecorContentParent != null)
            mDecorContentParent.setLogo(i);
    }

    public void setMediaController(MediaController mediacontroller)
    {
        mMediaController = mediacontroller;
    }

    public void setNavigationBarColor(int i)
    {
        mNavigationBarColor = i;
        mForcedNavigationBarColor = true;
        if(mDecor != null)
        {
            mDecor.updateColorViews(null, false);
            mDecor.updateNavigationGuardColor();
        }
        PhoneWindowInjector.onNavigationBarColorChange(this);
    }

    public void setReenterTransition(Transition transition)
    {
        mReenterTransition = transition;
    }

    public void setResizingCaptionDrawable(Drawable drawable)
    {
        mDecor.setUserCaptionBackgroundDrawable(drawable);
    }

    public void setReturnTransition(Transition transition)
    {
        mReturnTransition = transition;
    }

    public void setSharedElementEnterTransition(Transition transition)
    {
        mSharedElementEnterTransition = transition;
    }

    public void setSharedElementExitTransition(Transition transition)
    {
        mSharedElementExitTransition = transition;
    }

    public void setSharedElementReenterTransition(Transition transition)
    {
        mSharedElementReenterTransition = transition;
    }

    public void setSharedElementReturnTransition(Transition transition)
    {
        mSharedElementReturnTransition = transition;
    }

    public void setSharedElementsUseOverlay(boolean flag)
    {
        mSharedElementsUseOverlay = Boolean.valueOf(flag);
    }

    public void setStatusBarColor(int i)
    {
        mStatusBarColor = i;
        mForcedStatusBarColor = true;
        if(mDecor != null)
            mDecor.updateColorViews(null, false);
    }

    public void setTheme(int i)
    {
        mTheme = i;
        if(mDecor != null)
        {
            Context context = mDecor.getContext();
            if(context instanceof DecorContext)
                context.setTheme(i);
        }
    }

    public void setTitle(CharSequence charsequence)
    {
        setTitle(charsequence, true);
    }

    public void setTitle(CharSequence charsequence, boolean flag)
    {
        if(mTitleView == null) goto _L2; else goto _L1
_L1:
        mTitleView.setText(charsequence);
_L4:
        mTitle = charsequence;
        if(flag)
        {
            android.view.WindowManager.LayoutParams layoutparams = getAttributes();
            if(!TextUtils.equals(charsequence, layoutparams.accessibilityTitle))
            {
                layoutparams.accessibilityTitle = TextUtils.stringOrSpannedString(charsequence);
                if(mDecor != null)
                {
                    charsequence = mDecor.getViewRootImpl();
                    if(charsequence != null)
                        charsequence.onWindowTitleChanged();
                }
                dispatchWindowAttributesChanged(getAttributes());
            }
        }
        return;
_L2:
        if(mDecorContentParent != null)
            mDecorContentParent.setWindowTitle(charsequence);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setTitleColor(int i)
    {
        if(mTitleView != null)
            mTitleView.setTextColor(i);
        mTitleColor = i;
    }

    public void setTransitionBackgroundFadeDuration(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException("negative durations are not allowed");
        } else
        {
            mBackgroundFadeDurationMillis = l;
            return;
        }
    }

    public void setTransitionManager(TransitionManager transitionmanager)
    {
        mTransitionManager = transitionmanager;
    }

    public void setUiOptions(int i)
    {
        mUiOptions = i;
    }

    public void setUiOptions(int i, int j)
    {
        mUiOptions = mUiOptions & j | i & j;
    }

    public void setVolumeControlStream(int i)
    {
        mVolumeControlStreamType = i;
    }

    public boolean superDispatchGenericMotionEvent(MotionEvent motionevent)
    {
        return mDecor.superDispatchGenericMotionEvent(motionevent);
    }

    public boolean superDispatchKeyEvent(KeyEvent keyevent)
    {
        return mDecor.superDispatchKeyEvent(keyevent);
    }

    public boolean superDispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        return mDecor.superDispatchKeyShortcutEvent(keyevent);
    }

    public boolean superDispatchTouchEvent(MotionEvent motionevent)
    {
        return mDecor.superDispatchTouchEvent(motionevent);
    }

    public boolean superDispatchTrackballEvent(MotionEvent motionevent)
    {
        return mDecor.superDispatchTrackballEvent(motionevent);
    }

    public void takeInputQueue(android.view.InputQueue.Callback callback)
    {
        mTakeInputQueueCallback = callback;
    }

    public void takeKeyEvents(boolean flag)
    {
        mDecor.setFocusable(flag);
    }

    public void takeSurface(android.view.SurfaceHolder.Callback2 callback2)
    {
        mTakeSurfaceCallback = callback2;
    }

    public final void togglePanel(int i, KeyEvent keyevent)
    {
        PanelFeatureState panelfeaturestate = getPanelState(i, true);
        if(panelfeaturestate.isOpen)
            closePanel(panelfeaturestate, true);
        else
            openPanel(panelfeaturestate, keyevent);
    }

    protected final void updateDrawable(int i, boolean flag)
    {
        DrawableFeatureState drawablefeaturestate = getDrawableState(i, false);
        if(drawablefeaturestate != null)
            updateDrawable(i, drawablefeaturestate, flag);
    }

    private static final String ACTION_BAR_TAG = "android:ActionBar";
    private static final int CUSTOM_TITLE_COMPATIBLE_FEATURES = 13505;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_BACKGROUND_FADE_DURATION_MS = 300;
    static final int FLAG_RESOURCE_SET_ICON = 1;
    static final int FLAG_RESOURCE_SET_ICON_FALLBACK = 4;
    static final int FLAG_RESOURCE_SET_LOGO = 2;
    private static final String FOCUSED_ID_TAG = "android:focusedViewId";
    private static final String PANELS_TAG = "android:Panels";
    private static final String TAG = "PhoneWindow";
    private static final Transition USE_DEFAULT_TRANSITION = new TransitionSet();
    private static final String VIEWS_TAG = "android:views";
    static final RotationWatcher sRotationWatcher = new RotationWatcher();
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    private android.view.ViewRootImpl.ActivityConfigCallback mActivityConfigCallback;
    private Boolean mAllowEnterTransitionOverlap;
    private Boolean mAllowReturnTransitionOverlap;
    private boolean mAlwaysReadCloseOnTouchAttr;
    private AudioManager mAudioManager;
    private Drawable mBackgroundDrawable;
    private long mBackgroundFadeDurationMillis;
    int mBackgroundFallbackResource;
    int mBackgroundResource;
    private ProgressBar mCircularProgressBar;
    private boolean mClipToOutline;
    private boolean mClosingActionMenu;
    ViewGroup mContentParent;
    private boolean mContentParentExplicitlySet;
    private Scene mContentScene;
    ContextMenuBuilder mContextMenu;
    final PhoneWindowMenuCallback mContextMenuCallback;
    MenuHelper mContextMenuHelper;
    private DecorView mDecor;
    private int mDecorCaptionShade;
    DecorContentParent mDecorContentParent;
    private DrawableFeatureState mDrawables[];
    private float mElevation;
    private Transition mEnterTransition;
    private Transition mExitTransition;
    TypedValue mFixedHeightMajor;
    TypedValue mFixedHeightMinor;
    TypedValue mFixedWidthMajor;
    TypedValue mFixedWidthMinor;
    private boolean mForceDecorInstall;
    private boolean mForcedNavigationBarColor;
    private boolean mForcedStatusBarColor;
    private int mFrameResource;
    private ProgressBar mHorizontalProgressBar;
    int mIconRes;
    private int mInvalidatePanelMenuFeatures;
    private boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    boolean mIsFloating;
    private boolean mIsStartingWindow;
    private boolean mIsTranslucent;
    private KeyguardManager mKeyguardManager;
    private LayoutInflater mLayoutInflater;
    private ImageView mLeftIconView;
    private boolean mLoadElevation;
    int mLogoRes;
    private MediaController mMediaController;
    final TypedValue mMinWidthMajor;
    final TypedValue mMinWidthMinor;
    int mNavigationBarColor;
    int mNavigationBarDividerColor;
    int mPanelChordingKey;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState mPanels[];
    PanelFeatureState mPreparedPanel;
    private Transition mReenterTransition;
    int mResourcesSetFlags;
    private Transition mReturnTransition;
    private ImageView mRightIconView;
    private Transition mSharedElementEnterTransition;
    private Transition mSharedElementExitTransition;
    private Transition mSharedElementReenterTransition;
    private Transition mSharedElementReturnTransition;
    private Boolean mSharedElementsUseOverlay;
    int mStatusBarColor;
    private boolean mSupportsPictureInPicture;
    android.view.InputQueue.Callback mTakeInputQueueCallback;
    android.view.SurfaceHolder.Callback2 mTakeSurfaceCallback;
    private int mTextColor;
    private int mTheme;
    private CharSequence mTitle;
    private int mTitleColor;
    private TextView mTitleView;
    private TransitionManager mTransitionManager;
    private int mUiOptions;
    private boolean mUseDecorContext;
    private int mVolumeControlStreamType;


    // Unreferenced inner class com/android/internal/policy/PhoneWindow$RotationWatcher$1

/* anonymous class */
    class RotationWatcher._cls1
        implements Runnable
    {

        public void run()
        {
            dispatchRotationChanged();
        }

        final RotationWatcher this$1;

            
            {
                this$1 = RotationWatcher.this;
                super();
            }
    }

}
