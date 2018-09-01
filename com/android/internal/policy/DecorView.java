// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.animation.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.RemoteException;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.android.internal.view.*;
import com.android.internal.view.menu.*;
import com.android.internal.widget.*;
import java.util.List;

// Referenced classes of package com.android.internal.policy:
//            PhoneWindow, BackdropFrameRenderer, DecorContext

public class DecorView extends FrameLayout
    implements RootViewSurfaceTaker, WindowCallbacks
{
    private class ActionModeCallback2Wrapper extends android.view.ActionMode.Callback2
    {

        public boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
        {
            return mWrapped.onActionItemClicked(actionmode, menuitem);
        }

        public boolean onCreateActionMode(ActionMode actionmode, Menu menu)
        {
            return mWrapped.onCreateActionMode(actionmode, menu);
        }

        public void onDestroyActionMode(ActionMode actionmode)
        {
            mWrapped.onDestroyActionMode(actionmode);
            boolean flag;
            boolean flag3;
            boolean flag4;
            if(DecorView._2D_get0(DecorView.this).getApplicationInfo().targetSdkVersion >= 23)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                boolean flag2;
                ActionBarContextView actionbarcontextview;
                if(actionmode == mPrimaryActionMode)
                    flag = true;
                else
                    flag = false;
                if(actionmode == DecorView._2D_get2(DecorView.this))
                    flag2 = true;
                else
                    flag2 = false;
                if(!flag && actionmode.getType() == 0)
                    Log.e(mLogTag, (new StringBuilder()).append("Destroying unexpected ActionMode instance of TYPE_PRIMARY; ").append(actionmode).append(" was not the current primary action mode! Expected ").append(mPrimaryActionMode).toString());
                flag3 = flag2;
                flag4 = flag;
                if(!flag2)
                {
                    flag3 = flag2;
                    flag4 = flag;
                    if(actionmode.getType() == 1)
                    {
                        Log.e(mLogTag, (new StringBuilder()).append("Destroying unexpected ActionMode instance of TYPE_FLOATING; ").append(actionmode).append(" was not the current floating action mode! Expected ").append(DecorView._2D_get2(DecorView.this)).toString());
                        flag4 = flag;
                        flag3 = flag2;
                    }
                }
            } else
            {
                boolean flag1;
                if(actionmode.getType() == 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(actionmode.getType() == 1)
                {
                    flag3 = true;
                    flag4 = flag1;
                } else
                {
                    flag3 = false;
                    flag4 = flag1;
                }
            }
            if(flag4)
            {
                if(DecorView._2D_get3(DecorView.this) != null)
                    removeCallbacks(DecorView._2D_get5(DecorView.this));
                if(DecorView._2D_get4(DecorView.this) != null)
                {
                    DecorView._2D_wrap1(DecorView.this);
                    actionbarcontextview = DecorView._2D_get4(DecorView.this);
                    DecorView._2D_set0(DecorView.this, ObjectAnimator.ofFloat(DecorView._2D_get4(DecorView.this), View.ALPHA, new float[] {
                        1.0F, 0.0F
                    }));
                    DecorView._2D_get1(DecorView.this).addListener(actionbarcontextview. new android.animation.Animator.AnimatorListener() {

                        public void onAnimationCancel(Animator animator)
                        {
                        }

                        public void onAnimationEnd(Animator animator)
                        {
                            if(lastActionModeView == DecorView._2D_get4(_fld0))
                            {
                                lastActionModeView.setVisibility(8);
                                if(DecorView._2D_get3(_fld0) != null)
                                    DecorView._2D_get3(_fld0).dismiss();
                                lastActionModeView.killMode();
                                DecorView._2D_set0(_fld0, null);
                            }
                        }

                        public void onAnimationRepeat(Animator animator)
                        {
                        }

                        public void onAnimationStart(Animator animator)
                        {
                        }

                        final ActionModeCallback2Wrapper this$1;
                        final ActionBarContextView val$lastActionModeView;

            
            {
                this$1 = final_actionmodecallback2wrapper;
                lastActionModeView = ActionBarContextView.this;
                super();
            }
                    }
);
                    DecorView._2D_get1(DecorView.this).start();
                }
                mPrimaryActionMode = null;
            } else
            if(flag3)
            {
                DecorView._2D_wrap0(DecorView.this);
                DecorView._2D_set1(DecorView.this, null);
            }
            if(DecorView._2D_get6(DecorView.this).getCallback() != null && DecorView._2D_get6(DecorView.this).isDestroyed() ^ true)
                try
                {
                    DecorView._2D_get6(DecorView.this).getCallback().onActionModeFinished(actionmode);
                }
                // Misplaced declaration of an exception variable
                catch(ActionMode actionmode) { }
            requestFitSystemWindows();
        }

        public void onGetContentRect(ActionMode actionmode, View view, Rect rect)
        {
            if(mWrapped instanceof android.view.ActionMode.Callback2)
                ((android.view.ActionMode.Callback2)mWrapped).onGetContentRect(actionmode, view, rect);
            else
                super.onGetContentRect(actionmode, view, rect);
        }

        public boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
        {
            requestFitSystemWindows();
            return mWrapped.onPrepareActionMode(actionmode, menu);
        }

        private final android.view.ActionMode.Callback mWrapped;
        final DecorView this$0;

        public ActionModeCallback2Wrapper(android.view.ActionMode.Callback callback)
        {
            this$0 = DecorView.this;
            super();
            mWrapped = callback;
        }
    }

    public static class ColorViewAttributes
    {

        public boolean isPresent(int i, int j, boolean flag)
        {
            if((systemUiHideFlag & i) == 0 && (hideWindowFlag & j) == 0)
            {
                if((0x80000000 & j) != 0)
                    flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean isVisible(int i, int j, int k, boolean flag)
        {
            return isVisible(isPresent(i, k, flag), j, k, flag);
        }

        public boolean isVisible(boolean flag, int i, int j, boolean flag1)
        {
            if(flag && (0xff000000 & i) != 0)
            {
                if((translucentFlag & j) == 0)
                    flag1 = true;
            } else
            {
                flag1 = false;
            }
            return flag1;
        }

        final int hideWindowFlag;
        final int horizontalGravity;
        final int id;
        final int seascapeGravity;
        final int systemUiHideFlag;
        final String transitionName;
        final int translucentFlag;
        final int verticalGravity;

        private ColorViewAttributes(int i, int j, int k, int l, int i1, String s, int j1, 
                int k1)
        {
            id = j1;
            systemUiHideFlag = i;
            translucentFlag = j;
            verticalGravity = k;
            horizontalGravity = l;
            seascapeGravity = i1;
            transitionName = s;
            hideWindowFlag = k1;
        }

        ColorViewAttributes(int i, int j, int k, int l, int i1, String s, int j1, 
                int k1, ColorViewAttributes colorviewattributes)
        {
            this(i, j, k, l, i1, s, j1, k1);
        }
    }

    private static class ColorViewState
    {

        final ColorViewAttributes attributes;
        int color;
        boolean present;
        int targetVisibility;
        View view;
        boolean visible;

        ColorViewState(ColorViewAttributes colorviewattributes)
        {
            view = null;
            targetVisibility = 4;
            present = false;
            attributes = colorviewattributes;
        }
    }


    static Context _2D_get0(DecorView decorview)
    {
        return decorview.mContext;
    }

    static ObjectAnimator _2D_get1(DecorView decorview)
    {
        return decorview.mFadeAnim;
    }

    static ActionMode _2D_get2(DecorView decorview)
    {
        return decorview.mFloatingActionMode;
    }

    static PopupWindow _2D_get3(DecorView decorview)
    {
        return decorview.mPrimaryActionModePopup;
    }

    static ActionBarContextView _2D_get4(DecorView decorview)
    {
        return decorview.mPrimaryActionModeView;
    }

    static Runnable _2D_get5(DecorView decorview)
    {
        return decorview.mShowPrimaryActionModePopup;
    }

    static PhoneWindow _2D_get6(DecorView decorview)
    {
        return decorview.mWindow;
    }

    static ObjectAnimator _2D_set0(DecorView decorview, ObjectAnimator objectanimator)
    {
        decorview.mFadeAnim = objectanimator;
        return objectanimator;
    }

    static ActionMode _2D_set1(DecorView decorview, ActionMode actionmode)
    {
        decorview.mFloatingActionMode = actionmode;
        return actionmode;
    }

    static void _2D_wrap0(DecorView decorview)
    {
        decorview.cleanupFloatingActionModeViews();
    }

    static void _2D_wrap1(DecorView decorview)
    {
        decorview.endOnGoingFadeAnimation();
    }

    DecorView(Context context, int i, PhoneWindow phonewindow, android.view.WindowManager.LayoutParams layoutparams)
    {
        boolean flag = false;
        super(context);
        mAllowUpdateElevation = false;
        mElevationAdjustedForStack = false;
        mDefaultOpacity = -1;
        mHasCaption = false;
        mStatusColorViewState = new ColorViewState(STATUS_BAR_COLOR_VIEW_ATTRIBUTES);
        mNavigationColorViewState = new ColorViewState(NAVIGATION_BAR_COLOR_VIEW_ATTRIBUTES);
        mLastTopInset = 0;
        mLastBottomInset = 0;
        mLastRightInset = 0;
        mLastLeftInset = 0;
        mLastHasTopStableInset = false;
        mLastHasBottomStableInset = false;
        mLastHasRightStableInset = false;
        mLastHasLeftStableInset = false;
        mLastWindowFlags = 0;
        mLastShouldAlwaysConsumeNavBar = false;
        mRootScrollY = 0;
        mOutsets = new Rect();
        mWindowResizeCallbacksAdded = false;
        mLastBackgroundDrawableCb = null;
        mBackdropFrameRenderer = null;
        mLogTag = "DecorView";
        mApplyFloatingVerticalInsets = false;
        mApplyFloatingHorizontalInsets = false;
        mResizeMode = -1;
        mFeatureId = i;
        mShowInterpolator = AnimationUtils.loadInterpolator(context, 0x10c000e);
        mHideInterpolator = AnimationUtils.loadInterpolator(context, 0x10c000f);
        mBarEnterExitDuration = context.getResources().getInteger(0x10e00ee);
        boolean flag1 = flag;
        if(context.getResources().getBoolean(0x112006f))
        {
            flag1 = flag;
            if(context.getApplicationInfo().targetSdkVersion >= 24)
                flag1 = true;
        }
        mForceWindowDrawsStatusBarBackground = flag1;
        mSemiTransparentStatusBarColor = context.getResources().getColor(0x1060154, null);
        updateAvailableWidth();
        setWindow(phonewindow);
        updateLogTag(layoutparams);
        mResizeShadowSize = context.getResources().getDimensionPixelSize(0x1050165);
        initResizingPaints();
    }

    private int calculateStatusBarColor()
    {
        return calculateStatusBarColor(mWindow.getAttributes().flags, mSemiTransparentStatusBarColor, mWindow.mStatusBarColor);
    }

    public static int calculateStatusBarColor(int i, int j, int k)
    {
        if((0x4000000 & i) == 0)
            if((0x80000000 & i) != 0)
                j = k;
            else
                j = 0xff000000;
        return j;
    }

    private void cleanupFloatingActionModeViews()
    {
        if(mFloatingToolbar != null)
        {
            mFloatingToolbar.dismiss();
            mFloatingToolbar = null;
        }
        if(mFloatingActionModeOriginatingView != null)
        {
            if(mFloatingToolbarPreDrawListener != null)
            {
                mFloatingActionModeOriginatingView.getViewTreeObserver().removeOnPreDrawListener(mFloatingToolbarPreDrawListener);
                mFloatingToolbarPreDrawListener = null;
            }
            mFloatingActionModeOriginatingView = null;
        }
    }

    private void cleanupPrimaryActionMode()
    {
        if(mPrimaryActionMode != null)
        {
            mPrimaryActionMode.finish();
            mPrimaryActionMode = null;
        }
        if(mPrimaryActionModeView != null)
            mPrimaryActionModeView.killMode();
    }

    private ActionMode createActionMode(int i, android.view.ActionMode.Callback2 callback2, View view)
    {
        switch(i)
        {
        default:
            return createStandaloneActionMode(callback2);

        case 1: // '\001'
            return createFloatingActionMode(view, callback2);
        }
    }

    private DecorCaptionView createDecorCaptionView(LayoutInflater layoutinflater)
    {
        boolean flag = true;
        DecorCaptionView decorcaptionview = null;
        for(int i = getChildCount() - 1; i >= 0 && decorcaptionview == null; i--)
        {
            View view = getChildAt(i);
            if(view instanceof DecorCaptionView)
            {
                decorcaptionview = (DecorCaptionView)view;
                removeViewAt(i);
            }
        }

        Object obj = mWindow.getAttributes();
        boolean flag1;
        if(((android.view.WindowManager.LayoutParams) (obj)).type == 1 || ((android.view.WindowManager.LayoutParams) (obj)).type == 2)
            flag1 = true;
        else
        if(((android.view.WindowManager.LayoutParams) (obj)).type == 4)
            flag1 = true;
        else
            flag1 = false;
        if(!mWindow.isFloating() && flag1 && android.app.ActivityManager.StackId.hasWindowDecor(mStackId))
        {
            obj = decorcaptionview;
            if(decorcaptionview == null)
                obj = inflateDecorCaptionView(layoutinflater);
            ((DecorCaptionView) (obj)).setPhoneWindow(mWindow, true);
        } else
        {
            obj = null;
        }
        if(obj == null)
            flag = false;
        enableCaption(flag);
        return ((DecorCaptionView) (obj));
    }

    private ActionMode createFloatingActionMode(View view, final android.view.ActionMode.Callback2 mode)
    {
        if(mFloatingActionMode != null)
            mFloatingActionMode.finish();
        cleanupFloatingActionModeViews();
        mFloatingToolbar = new FloatingToolbar(mWindow);
        mode = new FloatingActionMode(mContext, mode, view, mFloatingToolbar);
        mFloatingActionModeOriginatingView = view;
        mFloatingToolbarPreDrawListener = new android.view.ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw()
            {
                mode.updateViewLocationInWindow();
                return true;
            }

            final DecorView this$0;
            final FloatingActionMode val$mode;

            
            {
                this$0 = DecorView.this;
                mode = floatingactionmode;
                super();
            }
        }
;
        return mode;
    }

    private ActionMode createStandaloneActionMode(android.view.ActionMode.Callback callback)
    {
        endOnGoingFadeAnimation();
        cleanupPrimaryActionMode();
        if(mPrimaryActionModeView == null || mPrimaryActionModeView.isAttachedToWindow() ^ true)
            if(mWindow.isFloating())
            {
                Object obj = new TypedValue();
                Object obj1 = mContext.getTheme();
                ((android.content.res.Resources.Theme) (obj1)).resolveAttribute(0x1010431, ((TypedValue) (obj)), true);
                int i;
                if(((TypedValue) (obj)).resourceId != 0)
                {
                    android.content.res.Resources.Theme theme = mContext.getResources().newTheme();
                    theme.setTo(((android.content.res.Resources.Theme) (obj1)));
                    theme.applyStyle(((TypedValue) (obj)).resourceId, true);
                    obj1 = new ContextThemeWrapper(mContext, 0);
                    ((Context) (obj1)).getTheme().setTo(theme);
                } else
                {
                    obj1 = mContext;
                }
                mPrimaryActionModeView = new ActionBarContextView(((Context) (obj1)));
                mPrimaryActionModePopup = new PopupWindow(((Context) (obj1)), null, 0x1110005);
                mPrimaryActionModePopup.setWindowLayoutType(2);
                mPrimaryActionModePopup.setContentView(mPrimaryActionModeView);
                mPrimaryActionModePopup.setWidth(-1);
                ((Context) (obj1)).getTheme().resolveAttribute(0x10102eb, ((TypedValue) (obj)), true);
                i = TypedValue.complexToDimensionPixelSize(((TypedValue) (obj)).data, ((Context) (obj1)).getResources().getDisplayMetrics());
                mPrimaryActionModeView.setContentHeight(i);
                mPrimaryActionModePopup.setHeight(-2);
                mShowPrimaryActionModePopup = new Runnable() {

                    public void run()
                    {
                        DecorView._2D_get3(DecorView.this).showAtLocation(DecorView._2D_get4(DecorView.this).getApplicationWindowToken(), 55, 0, 0);
                        DecorView._2D_wrap1(DecorView.this);
                        if(shouldAnimatePrimaryActionModeView())
                        {
                            DecorView._2D_set0(DecorView.this, ObjectAnimator.ofFloat(DecorView._2D_get4(DecorView.this), View.ALPHA, new float[] {
                                0.0F, 1.0F
                            }));
                            DecorView._2D_get1(DecorView.this).addListener(new AnimatorListenerAdapter() {

                                public void onAnimationEnd(Animator animator)
                                {
                                    DecorView._2D_get4(_fld0).setAlpha(1.0F);
                                    DecorView._2D_set0(_fld0, null);
                                }

                                public void onAnimationStart(Animator animator)
                                {
                                    DecorView._2D_get4(_fld0).setVisibility(0);
                                }

                                final _cls3 this$1;

            
            {
                this$1 = _cls3.this;
                super();
            }
                            }
);
                            DecorView._2D_get1(DecorView.this).start();
                        } else
                        {
                            DecorView._2D_get4(DecorView.this).setAlpha(1.0F);
                            DecorView._2D_get4(DecorView.this).setVisibility(0);
                        }
                    }

                    final DecorView this$0;

            
            {
                this$0 = DecorView.this;
                super();
            }
                }
;
            } else
            {
                ViewStub viewstub = (ViewStub)findViewById(0x1020187);
                if(viewstub != null)
                {
                    mPrimaryActionModeView = (ActionBarContextView)viewstub.inflate();
                    mPrimaryActionModePopup = null;
                }
            }
        if(mPrimaryActionModeView != null)
        {
            mPrimaryActionModeView.killMode();
            obj = mPrimaryActionModeView.getContext();
            obj1 = mPrimaryActionModeView;
            boolean flag;
            if(mPrimaryActionModePopup == null)
                flag = true;
            else
                flag = false;
            return new StandaloneActionMode(((Context) (obj)), ((ActionBarContextView) (obj1)), callback, flag);
        } else
        {
            return null;
        }
    }

    private float dipToPx(float f)
    {
        return TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    private void drawResizingShadowIfNeeded(DisplayListCanvas displaylistcanvas)
    {
        if(mResizeMode != 1 || mWindow.mIsFloating || mWindow.isTranslucent() || mWindow.isShowingWallpaper())
        {
            return;
        } else
        {
            displaylistcanvas.save();
            displaylistcanvas.translate(0.0F, getHeight() - mFrameOffsets.bottom);
            displaylistcanvas.drawRect(0.0F, 0.0F, getWidth(), mResizeShadowSize, mHorizontalResizeShadowPaint);
            displaylistcanvas.restore();
            displaylistcanvas.save();
            displaylistcanvas.translate(getWidth() - mFrameOffsets.right, 0.0F);
            displaylistcanvas.drawRect(0.0F, 0.0F, mResizeShadowSize, getHeight(), mVerticalResizeShadowPaint);
            displaylistcanvas.restore();
            return;
        }
    }

    private void drawableChanged()
    {
        int i;
        if(mChanging)
            return;
        setPadding(mFramePadding.left + mBackgroundPadding.left, mFramePadding.top + mBackgroundPadding.top, mFramePadding.right + mBackgroundPadding.right, mFramePadding.bottom + mBackgroundPadding.bottom);
        requestLayout();
        invalidate();
        i = -1;
        if(!android.app.ActivityManager.StackId.hasWindowShadow(mStackId)) goto _L2; else goto _L1
_L1:
        i = -3;
_L4:
        mDefaultOpacity = i;
        if(mFeatureId < 0)
            mWindow.setDefaultWindowFormat(i);
        return;
_L2:
        Drawable drawable = getBackground();
        Drawable drawable1 = getForeground();
        if(drawable != null)
            if(drawable1 == null)
                i = drawable.getOpacity();
            else
            if(mFramePadding.left <= 0 && mFramePadding.top <= 0 && mFramePadding.right <= 0 && mFramePadding.bottom <= 0)
            {
                int j = drawable1.getOpacity();
                i = drawable.getOpacity();
                if(j == -1 || i == -1)
                    i = -1;
                else
                if(j != 0)
                    if(i == 0)
                        i = j;
                    else
                        i = Drawable.resolveOpacity(j, i);
            } else
            {
                i = -3;
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void endOnGoingFadeAnimation()
    {
        if(mFadeAnim != null)
            mFadeAnim.end();
    }

    private static Drawable enforceNonTranslucentBackground(Drawable drawable, boolean flag)
    {
        if(!flag && (drawable instanceof ColorDrawable))
        {
            ColorDrawable colordrawable = (ColorDrawable)drawable;
            int i = colordrawable.getColor();
            if(Color.alpha(i) != 255)
            {
                drawable = (ColorDrawable)colordrawable.getConstantState().newDrawable().mutate();
                drawable.setColor(Color.argb(255, Color.red(i), Color.green(i), Color.blue(i)));
                return drawable;
            }
        }
        return drawable;
    }

    public static int getColorViewBottomInset(int i, int j)
    {
        return Math.min(i, j);
    }

    public static int getColorViewLeftInset(int i, int j)
    {
        return Math.min(i, j);
    }

    public static int getColorViewRightInset(int i, int j)
    {
        return Math.min(i, j);
    }

    public static int getColorViewTopInset(int i, int j)
    {
        return Math.min(i, j);
    }

    private int getCurrentColor(ColorViewState colorviewstate)
    {
        if(colorviewstate.visible)
            return colorviewstate.color;
        else
            return 0;
    }

    public static int getNavBarSize(int i, int j, int k)
    {
        if(!isNavBarToRightEdge(i, j))
            if(isNavBarToLeftEdge(i, k))
                j = k;
            else
                j = i;
        return j;
    }

    public static void getNavigationBarRect(int i, int j, Rect rect, Rect rect1, Rect rect2)
    {
        int k = getColorViewBottomInset(rect.bottom, rect1.bottom);
        int l = getColorViewLeftInset(rect.left, rect1.left);
        int i1 = getColorViewLeftInset(rect.right, rect1.right);
        int j1 = getNavBarSize(k, i1, l);
        if(isNavBarToRightEdge(k, i1))
            rect2.set(i - j1, 0, i, j);
        else
        if(isNavBarToLeftEdge(k, l))
            rect2.set(0, 0, j1, j);
        else
            rect2.set(0, j - j1, i, j);
    }

    public static Drawable getResizingBackgroundDrawable(Context context, int i, int j, boolean flag)
    {
        if(i != 0)
        {
            Drawable drawable = context.getDrawable(i);
            if(drawable != null)
                return enforceNonTranslucentBackground(drawable, flag);
        }
        if(j != 0)
        {
            context = context.getDrawable(j);
            if(context != null)
                return enforceNonTranslucentBackground(context, flag);
        }
        return new ColorDrawable(0xff000000);
    }

    private int getStackId()
    {
        byte byte0 = -1;
        android.view.Window.WindowControllerCallback windowcontrollercallback = mWindow.getWindowControllerCallback();
        int i = byte0;
        if(windowcontrollercallback != null)
            try
            {
                i = windowcontrollercallback.getWindowStackId();
            }
            catch(RemoteException remoteexception)
            {
                Log.e(mLogTag, "Failed to get the workspace ID of a PhoneWindow.");
                i = byte0;
            }
        if(i == -1)
            return 1;
        else
            return i;
    }

    private static String getTitleSuffix(android.view.WindowManager.LayoutParams layoutparams)
    {
        if(layoutparams == null)
            return "";
        layoutparams = layoutparams.getTitle().toString().split("\\.");
        if(layoutparams.length > 0)
            return layoutparams[layoutparams.length - 1];
        else
            return "";
    }

    private DecorCaptionView inflateDecorCaptionView(LayoutInflater layoutinflater)
    {
        Context context = getContext();
        layoutinflater = (DecorCaptionView)LayoutInflater.from(context).inflate(0x1090052, null);
        setDecorCaptionShade(context, layoutinflater);
        return layoutinflater;
    }

    private void initResizingPaints()
    {
        int i = mContext.getResources().getColor(0x1060127, null);
        int j = mContext.getResources().getColor(0x1060126, null);
        int k = (i + j) / 2;
        Paint paint = mHorizontalResizeShadowPaint;
        float f = mResizeShadowSize;
        android.graphics.Shader.TileMode tilemode = android.graphics.Shader.TileMode.CLAMP;
        paint.setShader(new LinearGradient(0.0F, 0.0F, 0.0F, f, new int[] {
            i, k, j
        }, new float[] {
            0.0F, 0.3F, 1.0F
        }, tilemode));
        paint = mVerticalResizeShadowPaint;
        f = mResizeShadowSize;
        tilemode = android.graphics.Shader.TileMode.CLAMP;
        paint.setShader(new LinearGradient(0.0F, 0.0F, f, 0.0F, new int[] {
            i, k, j
        }, new float[] {
            0.0F, 0.3F, 1.0F
        }, tilemode));
    }

    private void initializeElevation()
    {
        mAllowUpdateElevation = false;
        updateElevation();
    }

    public static boolean isNavBarToLeftEdge(int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i == 0)
        {
            flag1 = flag;
            if(j > 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNavBarToRightEdge(int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i == 0)
        {
            flag1 = flag;
            if(j > 0)
                flag1 = true;
        }
        return flag1;
    }

    private boolean isOutOfBounds(int i, int j)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i < -5) goto _L2; else goto _L1
_L1:
        if(j >= -5) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i <= getWidth() + 5)
        {
            flag1 = flag;
            if(j <= getHeight() + 5)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean isOutOfInnerBounds(int i, int j)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i < 0) goto _L2; else goto _L1
_L1:
        if(j >= 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i <= getWidth())
        {
            flag1 = flag;
            if(j <= getHeight())
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean isResizing()
    {
        boolean flag;
        if(mBackdropFrameRenderer != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void loadBackgroundDrawablesIfNeeded()
    {
        if(mResizingBackgroundDrawable == null)
        {
            Context context = getContext();
            int i = mWindow.mBackgroundResource;
            int j = mWindow.mBackgroundFallbackResource;
            boolean flag;
            if(!mWindow.isTranslucent())
                flag = mWindow.isShowingWallpaper();
            else
                flag = true;
            mResizingBackgroundDrawable = getResizingBackgroundDrawable(context, i, j, flag);
            if(mResizingBackgroundDrawable == null)
                Log.w(mLogTag, (new StringBuilder()).append("Failed to find background drawable for PhoneWindow=").append(mWindow).toString());
        }
        if(mCaptionBackgroundDrawable == null)
            mCaptionBackgroundDrawable = getContext().getDrawable(0x108025e);
        if(mResizingBackgroundDrawable != null)
        {
            mLastBackgroundDrawableCb = mResizingBackgroundDrawable.getCallback();
            mResizingBackgroundDrawable.setCallback(null);
        }
    }

    private void releaseThreadedRenderer()
    {
        if(mResizingBackgroundDrawable != null && mLastBackgroundDrawableCb != null)
        {
            mResizingBackgroundDrawable.setCallback(mLastBackgroundDrawableCb);
            mLastBackgroundDrawableCb = null;
        }
        if(mBackdropFrameRenderer != null)
        {
            mBackdropFrameRenderer.releaseRenderer();
            mBackdropFrameRenderer = null;
            updateElevation();
        }
    }

    private static void setColor(View view, int i, int j, boolean flag, boolean flag1)
    {
_L1:
        return;
        if(obj == null || ((Boolean)((Pair) (obj)).first).booleanValue() != flag || ((Boolean)((Pair) (obj)).second).booleanValue() != flag1)
        {
            int k = Math.round(TypedValue.applyDimension(1, 1.0F, view.getContext().getResources().getDisplayMetrics()));
            obj = new ColorDrawable(i);
            int l;
            if(flag && flag1 ^ true)
                i = k;
            else
                i = 0;
            if(!flag)
                l = k;
            else
                l = 0;
            if(!flag || !flag1)
                k = 0;
            obj = new InsetDrawable(((Drawable) (obj)), i, l, k, 0);
            view.setBackground(new LayerDrawable(new Drawable[] {
                new ColorDrawable(j), obj
            }));
            view.setTag(new Pair(Boolean.valueOf(flag), Boolean.valueOf(flag1)));
        } else
        {
            view = (LayerDrawable)view.getBackground();
            ((ColorDrawable)((InsetDrawable)view.getDrawable(1)).getDrawable()).setColor(i);
            ((ColorDrawable)view.getDrawable(0)).setColor(j);
        }
          goto _L1
        Object obj;
        if(j != 0)
        {
            obj = (Pair)view.getTag();
            break MISSING_BLOCK_LABEL_13;
        }
        view.setTag(null);
        view.setBackgroundColor(i);
        if(true) goto _L1; else goto _L2
_L2:
    }

    private void setDarkDecorCaptionShade(DecorCaptionView decorcaptionview)
    {
        decorcaptionview.findViewById(0x10202f4).setBackgroundResource(0x1080262);
        decorcaptionview.findViewById(0x10201fd).setBackgroundResource(0x1080260);
    }

    private void setDecorCaptionShade(Context context, DecorCaptionView decorcaptionview)
    {
        mWindow.getDecorCaptionShade();
        JVM INSTR tableswitch 1 2: default 28
    //                   1 70
    //                   2 78;
           goto _L1 _L2 _L3
_L1:
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x1010433, typedvalue, true);
        if((double)Color.luminance(typedvalue.data) < 0.5D)
            setLightDecorCaptionShade(decorcaptionview);
        else
            setDarkDecorCaptionShade(decorcaptionview);
_L5:
        return;
_L2:
        setLightDecorCaptionShade(decorcaptionview);
        continue; /* Loop/switch isn't completed */
_L3:
        setDarkDecorCaptionShade(decorcaptionview);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void setHandledActionMode(ActionMode actionmode)
    {
        if(actionmode.getType() != 0) goto _L2; else goto _L1
_L1:
        setHandledPrimaryActionMode(actionmode);
_L4:
        return;
_L2:
        if(actionmode.getType() == 1)
            setHandledFloatingActionMode(actionmode);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setHandledFloatingActionMode(ActionMode actionmode)
    {
        mFloatingActionMode = actionmode;
        mFloatingActionMode.invalidate();
        mFloatingActionModeOriginatingView.getViewTreeObserver().addOnPreDrawListener(mFloatingToolbarPreDrawListener);
    }

    private void setHandledPrimaryActionMode(ActionMode actionmode)
    {
        endOnGoingFadeAnimation();
        mPrimaryActionMode = actionmode;
        mPrimaryActionMode.invalidate();
        mPrimaryActionModeView.initForMode(mPrimaryActionMode);
        if(mPrimaryActionModePopup != null)
            post(mShowPrimaryActionModePopup);
        else
        if(shouldAnimatePrimaryActionModeView())
        {
            mFadeAnim = ObjectAnimator.ofFloat(mPrimaryActionModeView, View.ALPHA, new float[] {
                0.0F, 1.0F
            });
            mFadeAnim.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    DecorView._2D_get4(DecorView.this).setAlpha(1.0F);
                    DecorView._2D_set0(DecorView.this, null);
                }

                public void onAnimationStart(Animator animator)
                {
                    DecorView._2D_get4(DecorView.this).setVisibility(0);
                }

                final DecorView this$0;

            
            {
                this$0 = DecorView.this;
                super();
            }
            }
);
            mFadeAnim.start();
        } else
        {
            mPrimaryActionModeView.setAlpha(1.0F);
            mPrimaryActionModeView.setVisibility(0);
        }
        mPrimaryActionModeView.sendAccessibilityEvent(32);
    }

    private void setLightDecorCaptionShade(DecorCaptionView decorcaptionview)
    {
        decorcaptionview.findViewById(0x10202f4).setBackgroundResource(0x1080263);
        decorcaptionview.findViewById(0x10201fd).setBackgroundResource(0x1080261);
    }

    private boolean showContextMenuForChildInternal(View view, float f, float f1)
    {
        if(mWindow.mContextMenuHelper != null)
        {
            mWindow.mContextMenuHelper.dismiss();
            mWindow.mContextMenuHelper = null;
        }
        PhoneWindow.PhoneWindowMenuCallback phonewindowmenucallback = mWindow.mContextMenuCallback;
        boolean flag;
        boolean flag1;
        if(mWindow.mContextMenu == null)
        {
            mWindow.mContextMenu = new ContextMenuBuilder(getContext());
            mWindow.mContextMenu.setCallback(phonewindowmenucallback);
        } else
        {
            mWindow.mContextMenu.clearAll();
        }
        if(!Float.isNaN(f))
            flag = Float.isNaN(f1) ^ true;
        else
            flag = false;
        if(flag)
            view = mWindow.mContextMenu.showPopup(getContext(), view, f, f1);
        else
            view = mWindow.mContextMenu.showDialog(view, view.getWindowToken());
        if(view != null)
        {
            phonewindowmenucallback.setShowDialogForSubmenu(flag ^ true);
            view.setPresenterCallback(phonewindowmenucallback);
        }
        mWindow.mContextMenuHelper = view;
        if(view != null)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
    }

    private ActionMode startActionMode(View view, android.view.ActionMode.Callback callback, int i)
    {
        ActionModeCallback2Wrapper actionmodecallback2wrapper = new ActionModeCallback2Wrapper(callback);
        Object obj = null;
        callback = obj;
        if(mWindow.getCallback() != null)
        {
            callback = obj;
            if(mWindow.isDestroyed() ^ true)
                try
                {
                    callback = mWindow.getCallback().onWindowStartingActionMode(actionmodecallback2wrapper, i);
                }
                // Misplaced declaration of an exception variable
                catch(android.view.ActionMode.Callback callback)
                {
                    callback = obj;
                    if(i == 0)
                        try
                        {
                            callback = mWindow.getCallback().onWindowStartingActionMode(actionmodecallback2wrapper);
                        }
                        // Misplaced declaration of an exception variable
                        catch(android.view.ActionMode.Callback callback)
                        {
                            callback = obj;
                        }
                }
        }
        if(callback != null)
        {
            if(callback.getType() == 0)
            {
                cleanupPrimaryActionMode();
                mPrimaryActionMode = callback;
                view = callback;
            } else
            {
                view = callback;
                if(callback.getType() == 1)
                {
                    if(mFloatingActionMode != null)
                        mFloatingActionMode.finish();
                    mFloatingActionMode = callback;
                    view = callback;
                }
            }
        } else
        {
            view = createActionMode(i, actionmodecallback2wrapper, view);
            if(view != null && actionmodecallback2wrapper.onCreateActionMode(view, view.getMenu()))
                setHandledActionMode(view);
            else
                view = null;
        }
        if(view != null && mWindow.getCallback() != null && mWindow.isDestroyed() ^ true)
            try
            {
                mWindow.getCallback().onActionModeStarted(view);
            }
            // Misplaced declaration of an exception variable
            catch(android.view.ActionMode.Callback callback) { }
        return view;
    }

    private void updateAvailableWidth()
    {
        Resources resources = getResources();
        mAvailableWidth = TypedValue.applyDimension(1, resources.getConfiguration().screenWidthDp, resources.getDisplayMetrics());
    }

    private void updateColorViewInt(final ColorViewState state, int i, int j, int k, int l, boolean flag, boolean flag1, 
            int i1, boolean flag2, boolean flag3)
    {
        boolean flag4;
        int j1;
        Object obj;
        int k1;
        int l1;
        Object obj1;
        state.present = state.attributes.isPresent(i, mWindow.getAttributes().flags, flag3);
        flag3 = state.attributes.isVisible(state.present, j, mWindow.getAttributes().flags, flag3);
        if(flag3 && isResizing() ^ true && l > 0)
            flag4 = true;
        else
            flag4 = false;
        j1 = 0;
        obj = state.view;
        if(flag)
            k1 = -1;
        else
            k1 = l;
        if(flag)
            l1 = l;
        else
            l1 = -1;
        if(flag)
        {
            if(flag1)
                i = state.attributes.seascapeGravity;
            else
                i = state.attributes.horizontalGravity;
        } else
        {
            i = state.attributes.verticalGravity;
        }
        if(obj != null) goto _L2; else goto _L1
_L1:
        obj1 = obj;
        if(flag4)
        {
            obj1 = new View(mContext);
            state.view = ((View) (obj1));
            setColor(((View) (obj1)), j, k, flag, flag1);
            ((View) (obj1)).setTransitionName(state.attributes.transitionName);
            ((View) (obj1)).setId(state.attributes.id);
            j1 = 1;
            ((View) (obj1)).setVisibility(4);
            state.targetVisibility = 0;
            obj = new android.widget.FrameLayout.LayoutParams(l1, k1, i);
            if(flag1)
                obj.leftMargin = i1;
            else
                obj.rightMargin = i1;
            addView(((View) (obj1)), ((android.view.ViewGroup.LayoutParams) (obj)));
            updateColorViewTranslations();
        }
_L3:
        if(j1 != 0)
        {
            ((View) (obj1)).animate().cancel();
            if(flag2 && isResizing() ^ true)
            {
                if(flag4)
                {
                    if(((View) (obj1)).getVisibility() != 0)
                    {
                        ((View) (obj1)).setVisibility(0);
                        ((View) (obj1)).setAlpha(0.0F);
                    }
                    ((View) (obj1)).animate().alpha(1.0F).setInterpolator(mShowInterpolator).setDuration(mBarEnterExitDuration);
                } else
                {
                    ((View) (obj1)).animate().alpha(0.0F).setInterpolator(mHideInterpolator).setDuration(mBarEnterExitDuration).withEndAction(new Runnable() {

                        public void run()
                        {
                            state.view.setAlpha(1.0F);
                            state.view.setVisibility(4);
                        }

                        final DecorView this$0;
                        final ColorViewState val$state;

            
            {
                this$0 = DecorView.this;
                state = colorviewstate;
                super();
            }
                    }
);
                }
            } else
            {
                ((View) (obj1)).setAlpha(1.0F);
                if(flag4)
                    i = 0;
                else
                    i = 4;
                ((View) (obj1)).setVisibility(i);
            }
        }
        state.visible = flag3;
        state.color = j;
        return;
        obj1 = obj;
        j1 = l;
        if(flag4)
        {
            setColor(((View) (obj)), j, k, flag, flag1);
            obj1 = obj;
            j1 = l;
        }
          goto _L3
_L2:
        if(flag4)
            j1 = 0;
        else
            j1 = 4;
        if(state.targetVisibility != j1)
            l = 1;
        else
            l = 0;
        state.targetVisibility = j1;
        obj1 = (android.widget.FrameLayout.LayoutParams)((View) (obj)).getLayoutParams();
        if(flag1)
            j1 = 0;
        else
            j1 = i1;
        if(!flag1)
            i1 = 0;
        if(((android.widget.FrameLayout.LayoutParams) (obj1)).height != k1 || ((android.widget.FrameLayout.LayoutParams) (obj1)).width != l1 || ((android.widget.FrameLayout.LayoutParams) (obj1)).gravity != i || ((android.widget.FrameLayout.LayoutParams) (obj1)).rightMargin != j1 || ((android.widget.FrameLayout.LayoutParams) (obj1)).leftMargin != i1)
        {
            obj1.height = k1;
            obj1.width = l1;
            obj1.gravity = i;
            obj1.rightMargin = j1;
            obj1.leftMargin = i1;
            ((View) (obj)).setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj1)));
        }
        break MISSING_BLOCK_LABEL_487;
    }

    private void updateColorViewTranslations()
    {
        int i = mRootScrollY;
        if(mStatusColorViewState.view != null)
        {
            View view = mStatusColorViewState.view;
            int j;
            if(i > 0)
                j = i;
            else
                j = 0;
            view.setTranslationY(j);
        }
        if(mNavigationColorViewState.view != null)
        {
            view = mNavigationColorViewState.view;
            if(i >= 0)
                i = 0;
            view.setTranslationY(i);
        }
    }

    private void updateElevation()
    {
        float f = 0.0F;
        boolean flag = mElevationAdjustedForStack;
        if(mStackId == 2 && isResizing() ^ true)
        {
            int i;
            if(hasWindowFocus())
                i = 20;
            else
                i = 5;
            f = i;
            if(!mAllowUpdateElevation)
                f = 20F;
            f = dipToPx(f);
            mElevationAdjustedForStack = true;
        } else
        if(mStackId == 4)
        {
            f = dipToPx(5F);
            mElevationAdjustedForStack = true;
        } else
        {
            mElevationAdjustedForStack = false;
        }
        if((flag || mElevationAdjustedForStack) && getElevation() != f)
            mWindow.setElevation(f);
    }

    private WindowInsets updateNavigationGuard(WindowInsets windowinsets)
    {
        Object obj = windowinsets;
        if(mWindow.getAttributes().type == 2011)
        {
            obj = windowinsets;
            if((mWindow.getAttributes().flags & 0x2000000) == 0)
            {
                if(mWindow.mContentParent != null && (mWindow.mContentParent.getLayoutParams() instanceof android.view.ViewGroup.MarginLayoutParams))
                {
                    obj = (android.view.ViewGroup.MarginLayoutParams)mWindow.mContentParent.getLayoutParams();
                    obj.bottomMargin = windowinsets.getSystemWindowInsetBottom();
                    mWindow.mContentParent.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                }
                if(mNavigationGuard == null)
                {
                    mNavigationGuard = new View(mContext);
                    mNavigationGuard.setBackgroundColor(mContext.getColor(0x10600a5));
                    addView(mNavigationGuard, indexOfChild(mNavigationColorViewState.view), new android.widget.FrameLayout.LayoutParams(-1, windowinsets.getSystemWindowInsetBottom(), 0x800053));
                } else
                {
                    android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)mNavigationGuard.getLayoutParams();
                    layoutparams.height = windowinsets.getSystemWindowInsetBottom();
                    mNavigationGuard.setLayoutParams(layoutparams);
                }
                updateNavigationGuardColor();
                obj = windowinsets.consumeSystemWindowInsets(false, false, false, true);
            }
        }
        return ((WindowInsets) (obj));
    }

    private WindowInsets updateStatusGuard(WindowInsets windowinsets)
    {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag3 = false;
        boolean flag4 = flag1;
        WindowInsets windowinsets1 = windowinsets;
        int j;
        if(mPrimaryActionModeView != null)
        {
            flag4 = flag1;
            windowinsets1 = windowinsets;
            if(mPrimaryActionModeView.getLayoutParams() instanceof android.view.ViewGroup.MarginLayoutParams)
            {
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)mPrimaryActionModeView.getLayoutParams();
                int i = 0;
                j = 0;
                boolean flag2;
                Object obj;
                if(mPrimaryActionModeView.isShown())
                {
                    if(mTempRect == null)
                        mTempRect = new Rect();
                    obj = mTempRect;
                    mWindow.mContentParent.computeSystemWindowInsets(windowinsets, ((Rect) (obj)));
                    if(((Rect) (obj)).top == 0)
                        i = windowinsets.getSystemWindowInsetTop();
                    else
                        i = 0;
                    if(marginlayoutparams.topMargin != i)
                    {
                        i = 1;
                        marginlayoutparams.topMargin = windowinsets.getSystemWindowInsetTop();
                        if(mStatusGuard == null)
                        {
                            mStatusGuard = new View(mContext);
                            mStatusGuard.setBackgroundColor(mContext.getColor(0x10600a5));
                            addView(mStatusGuard, indexOfChild(mStatusColorViewState.view), new android.widget.FrameLayout.LayoutParams(-1, marginlayoutparams.topMargin, 0x800033));
                            j = i;
                        } else
                        {
                            obj = (android.widget.FrameLayout.LayoutParams)mStatusGuard.getLayoutParams();
                            j = i;
                            if(((android.widget.FrameLayout.LayoutParams) (obj)).height != marginlayoutparams.topMargin)
                            {
                                obj.height = marginlayoutparams.topMargin;
                                mStatusGuard.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                                j = i;
                            }
                        }
                    }
                    if(mStatusGuard != null)
                        flag2 = true;
                    else
                        flag2 = false;
                    if((mWindow.getLocalFeaturesPrivate() & 0x400) == 0)
                        i = 1;
                    else
                        i = 0;
                    if(i != 0)
                        flag4 = flag2;
                    else
                        flag4 = false;
                    obj = windowinsets.consumeSystemWindowInsets(false, flag4, false, false);
                } else
                {
                    j = i;
                    flag2 = flag3;
                    obj = windowinsets;
                    if(marginlayoutparams.topMargin != 0)
                    {
                        j = 1;
                        marginlayoutparams.topMargin = 0;
                        flag2 = flag3;
                        obj = windowinsets;
                    }
                }
                flag4 = flag2;
                windowinsets1 = ((WindowInsets) (obj));
                if(j != 0)
                {
                    mPrimaryActionModeView.setLayoutParams(marginlayoutparams);
                    windowinsets1 = ((WindowInsets) (obj));
                    flag4 = flag2;
                }
            }
        }
        if(mStatusGuard != null)
        {
            windowinsets = mStatusGuard;
            if(flag4)
                j = ((flag) ? 1 : 0);
            else
                j = 8;
            windowinsets.setVisibility(j);
        }
        return windowinsets1;
    }

    void clearContentView()
    {
        if(mDecorCaptionView != null)
        {
            mDecorCaptionView.removeContentView();
        } else
        {
            int i = getChildCount() - 1;
            while(i >= 0) 
            {
                View view = getChildAt(i);
                if(view != mStatusColorViewState.view && view != mNavigationColorViewState.view && view != mStatusGuard && view != mNavigationGuard)
                    removeViewAt(i);
                i--;
            }
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        android.view.Window.Callback callback = mWindow.getCallback();
        boolean flag;
        if(callback != null && mWindow.isDestroyed() ^ true && mFeatureId < 0)
            flag = callback.dispatchGenericMotionEvent(motionevent);
        else
            flag = super.dispatchGenericMotionEvent(motionevent);
        return flag;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCode();
        boolean flag;
        if(keyevent.getAction() == 0)
            flag = true;
        else
            flag = false;
        if(flag && keyevent.getRepeatCount() == 0)
        {
            if(mWindow.mPanelChordingKey > 0 && mWindow.mPanelChordingKey != i && dispatchKeyShortcutEvent(keyevent))
                return true;
            if(mWindow.mPreparedPanel != null && mWindow.mPreparedPanel.isOpen && mWindow.performPanelShortcut(mWindow.mPreparedPanel, i, keyevent, 0))
                return true;
        }
        if(!mWindow.isDestroyed())
        {
            android.view.Window.Callback callback = mWindow.getCallback();
            boolean flag1;
            if(callback != null && mFeatureId < 0)
                flag1 = callback.dispatchKeyEvent(keyevent);
            else
                flag1 = super.dispatchKeyEvent(keyevent);
            if(flag1)
                return true;
        }
        boolean flag2;
        if(flag)
            flag2 = mWindow.onKeyDown(mFeatureId, keyevent.getKeyCode(), keyevent);
        else
            flag2 = mWindow.onKeyUp(mFeatureId, keyevent.getKeyCode(), keyevent);
        return flag2;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        if(mWindow.mPreparedPanel != null && mWindow.performPanelShortcut(mWindow.mPreparedPanel, keyevent.getKeyCode(), keyevent, 1))
        {
            if(mWindow.mPreparedPanel != null)
                mWindow.mPreparedPanel.isHandled = true;
            return true;
        }
        Object obj = mWindow.getCallback();
        boolean flag;
        if(obj != null && mWindow.isDestroyed() ^ true && mFeatureId < 0)
            flag = ((android.view.Window.Callback) (obj)).dispatchKeyShortcutEvent(keyevent);
        else
            flag = super.dispatchKeyShortcutEvent(keyevent);
        if(flag)
            return true;
        obj = mWindow.getPanelState(0, false);
        if(obj != null && mWindow.mPreparedPanel == null)
        {
            mWindow.preparePanel(((PhoneWindow.PanelFeatureState) (obj)), keyevent);
            boolean flag1 = mWindow.performPanelShortcut(((PhoneWindow.PanelFeatureState) (obj)), keyevent.getKeyCode(), keyevent, 1);
            obj.isPrepared = false;
            if(flag1)
                return true;
        }
        return false;
    }

    public void dispatchPointerCaptureChanged(boolean flag)
    {
        super.dispatchPointerCaptureChanged(flag);
        if(!mWindow.isDestroyed() && mWindow.getCallback() != null)
            mWindow.getCallback().onPointerCaptureChanged(flag);
    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        android.view.Window.Callback callback = mWindow.getCallback();
        if(callback != null && mWindow.isDestroyed() ^ true && callback.dispatchPopulateAccessibilityEvent(accessibilityevent))
            return true;
        else
            return super.dispatchPopulateAccessibilityEventInternal(accessibilityevent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        android.view.Window.Callback callback = mWindow.getCallback();
        boolean flag;
        if(callback != null && mWindow.isDestroyed() ^ true && mFeatureId < 0)
            flag = callback.dispatchTouchEvent(motionevent);
        else
            flag = super.dispatchTouchEvent(motionevent);
        return flag;
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        android.view.Window.Callback callback = mWindow.getCallback();
        boolean flag;
        if(callback != null && mWindow.isDestroyed() ^ true && mFeatureId < 0)
            flag = callback.dispatchTrackballEvent(motionevent);
        else
            flag = super.dispatchTrackballEvent(motionevent);
        return flag;
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(mMenuBackground != null)
            mMenuBackground.draw(canvas);
    }

    void enableCaption(boolean flag)
    {
        if(mHasCaption != flag)
        {
            mHasCaption = flag;
            if(getForeground() != null)
                drawableChanged();
        }
    }

    void finishChanging()
    {
        mChanging = false;
        drawableChanged();
    }

    public boolean gatherTransparentRegion(Region region)
    {
        boolean flag = gatherTransparentRegion(mStatusColorViewState, region);
        boolean flag1 = gatherTransparentRegion(mNavigationColorViewState, region);
        boolean flag2 = super.gatherTransparentRegion(region);
        if(flag || flag1)
            flag2 = true;
        return flag2;
    }

    boolean gatherTransparentRegion(ColorViewState colorviewstate, Region region)
    {
        if(colorviewstate.view != null && colorviewstate.visible && isResizing())
            return colorviewstate.view.gatherTransparentRegion(region);
        else
            return false;
    }

    public int getAccessibilityViewId()
    {
        return 0x7ffffffe;
    }

    public Activity getAttachedActivity()
    {
        if(getContext() instanceof DecorContext)
        {
            Context context = getWindowContext();
            if(context != null && (context instanceof Activity))
                return (Activity)context;
        }
        return super.getAttachedActivity();
    }

    int getCaptionHeight()
    {
        int i;
        if(isShowingCaption())
            i = mDecorCaptionView.getCaptionHeight();
        else
            i = 0;
        return i;
    }

    public Context getWindowContext()
    {
        Context context = null;
        if(mWindow != null)
            context = mWindow.getContext();
        return context;
    }

    boolean isShowingCaption()
    {
        boolean flag;
        if(mDecorCaptionView != null)
            flag = mDecorCaptionView.isCaptionShowing();
        else
            flag = false;
        return flag;
    }

    public boolean isTransitionGroup()
    {
        return false;
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowinsets)
    {
        android.view.WindowManager.LayoutParams layoutparams = mWindow.getAttributes();
        mFloatingInsets.setEmpty();
        WindowInsets windowinsets1 = windowinsets;
        if((layoutparams.flags & 0x100) == 0)
        {
            WindowInsets windowinsets2 = windowinsets;
            if(layoutparams.height == -2)
            {
                mFloatingInsets.top = windowinsets.getSystemWindowInsetTop();
                mFloatingInsets.bottom = windowinsets.getSystemWindowInsetBottom();
                windowinsets2 = windowinsets.replaceSystemWindowInsets(windowinsets.getSystemWindowInsetLeft(), 0, windowinsets.getSystemWindowInsetRight(), 0);
            }
            windowinsets1 = windowinsets2;
            if(mWindow.getAttributes().width == -2)
            {
                mFloatingInsets.left = windowinsets2.getSystemWindowInsetTop();
                mFloatingInsets.right = windowinsets2.getSystemWindowInsetBottom();
                windowinsets1 = windowinsets2.replaceSystemWindowInsets(0, windowinsets2.getSystemWindowInsetTop(), 0, windowinsets2.getSystemWindowInsetBottom());
            }
        }
        mFrameOffsets.set(windowinsets1.getSystemWindowInsets());
        windowinsets = updateNavigationGuard(updateStatusGuard(updateColorViews(windowinsets1, true)));
        if(getForeground() != null)
            drawableChanged();
        return windowinsets;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        android.view.Window.Callback callback = mWindow.getCallback();
        if(callback != null && mWindow.isDestroyed() ^ true && mFeatureId < 0)
            callback.onAttachedToWindow();
        if(mFeatureId == -1)
            mWindow.openPanelsAfterRestore();
        if(mWindowResizeCallbacksAdded) goto _L2; else goto _L1
_L1:
        getViewRootImpl().addWindowCallbacks(this);
        mWindowResizeCallbacksAdded = true;
_L4:
        mWindow.onViewRootImplSet(getViewRootImpl());
        return;
_L2:
        if(mBackdropFrameRenderer != null)
            mBackdropFrameRenderer.onConfigurationChange();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onCloseSystemDialogs(String s)
    {
        if(mFeatureId >= 0)
            mWindow.closeAllPanels();
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        int i;
        super.onConfigurationChanged(configuration);
        i = getStackId();
        if(mStackId == i) goto _L2; else goto _L1
_L1:
        mStackId = i;
        if(mDecorCaptionView != null || !android.app.ActivityManager.StackId.hasWindowDecor(mStackId)) goto _L4; else goto _L3
_L3:
        mDecorCaptionView = createDecorCaptionView(mWindow.getLayoutInflater());
        if(mDecorCaptionView != null)
        {
            if(mDecorCaptionView.getParent() == null)
                addView(mDecorCaptionView, 0, new android.view.ViewGroup.LayoutParams(-1, -1));
            removeView(mContentRoot);
            mDecorCaptionView.addView(mContentRoot, new android.view.ViewGroup.MarginLayoutParams(-1, -1));
        }
_L2:
        updateAvailableWidth();
        initializeElevation();
        return;
_L4:
        if(mDecorCaptionView != null)
        {
            mDecorCaptionView.onConfigurationChanged(android.app.ActivityManager.StackId.hasWindowDecor(mStackId));
            enableCaption(android.app.ActivityManager.StackId.hasWindowDecor(i));
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean onContentDrawn(int i, int j, int k, int l)
    {
        if(mBackdropFrameRenderer == null)
            return false;
        else
            return mBackdropFrameRenderer.onContentDrawn(i, j, k, l);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        Object obj = mWindow.getCallback();
        if(obj != null && mFeatureId < 0)
            ((android.view.Window.Callback) (obj)).onDetachedFromWindow();
        if(mWindow.mDecorContentParent != null)
            mWindow.mDecorContentParent.dismissPopups();
        if(mPrimaryActionModePopup != null)
        {
            removeCallbacks(mShowPrimaryActionModePopup);
            if(mPrimaryActionModePopup.isShowing())
                mPrimaryActionModePopup.dismiss();
            mPrimaryActionModePopup = null;
        }
        if(mFloatingToolbar != null)
        {
            mFloatingToolbar.dismiss();
            mFloatingToolbar = null;
        }
        obj = mWindow.getPanelState(0, false);
        if(obj != null && ((PhoneWindow.PanelFeatureState) (obj)).menu != null && mFeatureId < 0)
            ((PhoneWindow.PanelFeatureState) (obj)).menu.close();
        releaseThreadedRenderer();
        if(mWindowResizeCallbacksAdded)
        {
            getViewRootImpl().removeWindowCallbacks(this);
            mWindowResizeCallbacksAdded = false;
        }
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        BackgroundFallback backgroundfallback = mBackgroundFallback;
        Object obj;
        if(isResizing())
            obj = this;
        else
            obj = mContentRoot;
        backgroundfallback.draw(((ViewGroup) (obj)), mContentRoot, canvas, mWindow.mContentParent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if(mHasCaption && isShowingCaption() && i == 0 && isOutOfInnerBounds((int)motionevent.getX(), (int)motionevent.getY()))
            return true;
        if(mFeatureId >= 0 && i == 0 && isOutOfBounds((int)motionevent.getX(), (int)motionevent.getY()))
        {
            mWindow.closePanel(mFeatureId);
            return true;
        } else
        {
            return false;
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        getOutsets(mOutsets);
        if(mOutsets.left > 0)
            offsetLeftAndRight(-mOutsets.left);
        if(mOutsets.top > 0)
            offsetTopAndBottom(-mOutsets.top);
        if(mApplyFloatingVerticalInsets)
            offsetTopAndBottom(mFloatingInsets.top);
        if(mApplyFloatingHorizontalInsets)
            offsetLeftAndRight(mFloatingInsets.left);
        updateElevation();
        mAllowUpdateElevation = true;
        if(flag && mResizeMode == 1)
            getViewRootImpl().requestInvalidateRootRenderNode();
    }

    protected void onMeasure(int i, int j)
    {
        DisplayMetrics displaymetrics;
        int i1;
        TypedValue typedvalue;
        displaymetrics = getContext().getResources().getDisplayMetrics();
        boolean flag;
        int k;
        int l;
        int j1;
        int k1;
        if(getResources().getConfiguration().orientation == 1)
            flag = true;
        else
            flag = false;
        k = android.view.View.MeasureSpec.getMode(i);
        l = android.view.View.MeasureSpec.getMode(j);
        i1 = 0;
        mApplyFloatingHorizontalInsets = false;
        j1 = i1;
        k1 = i;
        if(k == 0x80000000)
        {
            if(flag)
                typedvalue = mWindow.mFixedWidthMinor;
            else
                typedvalue = mWindow.mFixedWidthMajor;
            j1 = i1;
            k1 = i;
            if(typedvalue != null)
            {
                j1 = i1;
                k1 = i;
                if(typedvalue.type != 0)
                {
                    int l1;
                    boolean flag1;
                    if(typedvalue.type == 5)
                        k1 = (int)typedvalue.getDimension(displaymetrics);
                    else
                    if(typedvalue.type == 6)
                        k1 = (int)typedvalue.getFraction(displaymetrics.widthPixels, displaymetrics.widthPixels);
                    else
                        k1 = 0;
                    i = android.view.View.MeasureSpec.getSize(i);
                    if(k1 > 0)
                    {
                        k1 = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(k1, i), 0x40000000);
                        j1 = 1;
                    } else
                    {
                        k1 = android.view.View.MeasureSpec.makeMeasureSpec(i - mFloatingInsets.left - mFloatingInsets.right, 0x80000000);
                        mApplyFloatingHorizontalInsets = true;
                        j1 = i1;
                    }
                }
            }
        }
        mApplyFloatingVerticalInsets = false;
        i = j;
        if(l != 0x80000000) goto _L2; else goto _L1
_L1:
        if(flag)
            typedvalue = mWindow.mFixedHeightMajor;
        else
            typedvalue = mWindow.mFixedHeightMinor;
        i = j;
        if(typedvalue == null) goto _L2; else goto _L3
_L3:
        i = j;
        if(typedvalue.type == 0) goto _L2; else goto _L4
_L4:
        if(typedvalue.type == 5)
            i = (int)typedvalue.getDimension(displaymetrics);
        else
        if(typedvalue.type == 6)
            i = (int)typedvalue.getFraction(displaymetrics.heightPixels, displaymetrics.heightPixels);
        else
            i = 0;
        i1 = android.view.View.MeasureSpec.getSize(j);
        if(i <= 0) goto _L6; else goto _L5
_L5:
        i = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(i, i1), 0x40000000);
_L2:
label0:
        {
            getOutsets(mOutsets);
            if(mOutsets.top <= 0)
            {
                j = i;
                if(mOutsets.bottom <= 0)
                    break label0;
            }
            i1 = android.view.View.MeasureSpec.getMode(i);
            j = i;
            if(i1 != 0)
            {
                i = android.view.View.MeasureSpec.getSize(i);
                j = android.view.View.MeasureSpec.makeMeasureSpec(mOutsets.top + i + mOutsets.bottom, i1);
            }
        }
label1:
        {
            if(mOutsets.left <= 0)
            {
                i = k1;
                if(mOutsets.right <= 0)
                    break label1;
            }
            i1 = android.view.View.MeasureSpec.getMode(k1);
            i = k1;
            if(i1 != 0)
            {
                i = android.view.View.MeasureSpec.getSize(k1);
                i = android.view.View.MeasureSpec.makeMeasureSpec(mOutsets.left + i + mOutsets.right, i1);
            }
        }
        super.onMeasure(i, j);
        l1 = getMeasuredWidth();
        flag1 = false;
        l = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x40000000);
        i1 = ((flag1) ? 1 : 0);
        k1 = l;
        if(j1 == 0)
        {
            i1 = ((flag1) ? 1 : 0);
            k1 = l;
            if(k == 0x80000000)
            {
                if(flag)
                    typedvalue = mWindow.mMinWidthMinor;
                else
                    typedvalue = mWindow.mMinWidthMajor;
                i1 = ((flag1) ? 1 : 0);
                k1 = l;
                if(typedvalue.type != 0)
                {
                    if(typedvalue.type == 5)
                        i = (int)typedvalue.getDimension(displaymetrics);
                    else
                    if(typedvalue.type == 6)
                        i = (int)typedvalue.getFraction(mAvailableWidth, mAvailableWidth);
                    else
                        i = 0;
                    i1 = ((flag1) ? 1 : 0);
                    k1 = l;
                    if(l1 < i)
                    {
                        k1 = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
                        i1 = 1;
                    }
                }
            }
        }
        if(i1 != 0)
            super.onMeasure(k1, j);
        return;
_L6:
        i = j;
        if((mWindow.getAttributes().flags & 0x100) == 0)
        {
            i = android.view.View.MeasureSpec.makeMeasureSpec(i1 - mFloatingInsets.top - mFloatingInsets.bottom, 0x80000000);
            mApplyFloatingVerticalInsets = true;
        }
        if(true) goto _L2; else goto _L7
_L7:
    }

    public void onPostDraw(DisplayListCanvas displaylistcanvas)
    {
        drawResizingShadowIfNeeded(displaylistcanvas);
    }

    public void onRequestDraw(boolean flag)
    {
        if(mBackdropFrameRenderer == null) goto _L2; else goto _L1
_L1:
        mBackdropFrameRenderer.onRequestDraw(flag);
_L4:
        return;
_L2:
        if(flag && isAttachedToWindow())
            getViewRootImpl().reportDrawFinish();
        if(true) goto _L4; else goto _L3
_L3:
    }

    void onResourcesLoaded(LayoutInflater layoutinflater, int i)
    {
        mStackId = getStackId();
        if(mBackdropFrameRenderer != null)
        {
            loadBackgroundDrawablesIfNeeded();
            mBackdropFrameRenderer.onResourcesLoaded(this, mResizingBackgroundDrawable, mCaptionBackgroundDrawable, mUserCaptionBackgroundDrawable, getCurrentColor(mStatusColorViewState), getCurrentColor(mNavigationColorViewState));
        }
        mDecorCaptionView = createDecorCaptionView(layoutinflater);
        layoutinflater = layoutinflater.inflate(i, null);
        if(mDecorCaptionView != null)
        {
            if(mDecorCaptionView.getParent() == null)
                addView(mDecorCaptionView, new android.view.ViewGroup.LayoutParams(-1, -1));
            mDecorCaptionView.addView(layoutinflater, new android.view.ViewGroup.MarginLayoutParams(-1, -1));
        } else
        {
            addView(layoutinflater, 0, new android.view.ViewGroup.LayoutParams(-1, -1));
        }
        mContentRoot = (ViewGroup)layoutinflater;
        initializeElevation();
    }

    public void onRootViewScrollYChanged(int i)
    {
        mRootScrollY = i;
        updateColorViewTranslations();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return onInterceptTouchEvent(motionevent);
    }

    public void onWindowDragResizeEnd()
    {
        releaseThreadedRenderer();
        updateColorViews(null, false);
        mResizeMode = -1;
        getViewRootImpl().requestInvalidateRootRenderNode();
    }

    public void onWindowDragResizeStart(Rect rect, boolean flag, Rect rect1, Rect rect2, int i)
    {
        if(mWindow.isDestroyed())
        {
            releaseThreadedRenderer();
            return;
        }
        if(mBackdropFrameRenderer != null)
            return;
        android.view.ThreadedRenderer threadedrenderer = getThreadedRenderer();
        if(threadedrenderer != null)
        {
            loadBackgroundDrawablesIfNeeded();
            mBackdropFrameRenderer = new BackdropFrameRenderer(this, threadedrenderer, rect, mResizingBackgroundDrawable, mCaptionBackgroundDrawable, mUserCaptionBackgroundDrawable, getCurrentColor(mStatusColorViewState), getCurrentColor(mNavigationColorViewState), flag, rect1, rect2, i);
            updateElevation();
            updateColorViews(null, false);
        }
        mResizeMode = i;
        getViewRootImpl().requestInvalidateRootRenderNode();
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if(mWindow.hasFeature(0) && flag ^ true && mWindow.mPanelChordingKey != 0)
            mWindow.closePanel(0);
        android.view.Window.Callback callback = mWindow.getCallback();
        if(callback != null && mWindow.isDestroyed() ^ true && mFeatureId < 0)
            callback.onWindowFocusChanged(flag);
        if(mPrimaryActionMode != null)
            mPrimaryActionMode.onWindowFocusChanged(flag);
        if(mFloatingActionMode != null)
            mFloatingActionMode.onWindowFocusChanged(flag);
        updateElevation();
    }

    public void onWindowSizeIsChanging(Rect rect, boolean flag, Rect rect1, Rect rect2)
    {
        if(mBackdropFrameRenderer != null)
            mBackdropFrameRenderer.setTargetRect(rect, flag, rect1, rect2);
    }

    public void onWindowSystemUiVisibilityChanged(int i)
    {
        updateColorViews(null, true);
    }

    public void requestKeyboardShortcuts(List list, int i)
    {
        Object obj = mWindow.getPanelState(0, false);
        if(obj != null)
            obj = ((PhoneWindow.PanelFeatureState) (obj)).menu;
        else
            obj = null;
        if(!mWindow.isDestroyed() && mWindow.getCallback() != null)
            mWindow.getCallback().onProvideKeyboardShortcuts(list, ((Menu) (obj)), i);
    }

    public void sendAccessibilityEvent(int i)
    {
        if(!AccessibilityManager.getInstance(mContext).isEnabled())
            return;
        if((mFeatureId == 0 || mFeatureId == 6 || mFeatureId == 2 || mFeatureId == 5) && getChildCount() == 1)
            getChildAt(0).sendAccessibilityEvent(i);
        else
            super.sendAccessibilityEvent(i);
    }

    void setBackgroundFallback(int i)
    {
        Drawable drawable = null;
        BackgroundFallback backgroundfallback = mBackgroundFallback;
        if(i != 0)
            drawable = getContext().getDrawable(i);
        backgroundfallback.setDrawable(drawable);
        boolean flag;
        if(getBackground() == null)
            flag = mBackgroundFallback.hasFallback() ^ true;
        else
            flag = false;
        setWillNotDraw(flag);
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag = super.setFrame(i, j, k, l);
        if(flag)
        {
            Rect rect = mDrawingBounds;
            getDrawingRect(rect);
            Object obj = getForeground();
            if(obj != null)
            {
                Rect rect1 = mFrameOffsets;
                rect.left = rect.left + rect1.left;
                rect.top = rect.top + rect1.top;
                rect.right = rect.right - rect1.right;
                rect.bottom = rect.bottom - rect1.bottom;
                ((Drawable) (obj)).setBounds(rect);
                obj = mFramePadding;
                rect.left = rect.left + (((Rect) (obj)).left - rect1.left);
                rect.top = rect.top + (((Rect) (obj)).top - rect1.top);
                rect.right = rect.right - (((Rect) (obj)).right - rect1.right);
                rect.bottom = rect.bottom - (((Rect) (obj)).bottom - rect1.bottom);
            }
            Drawable drawable = getBackground();
            if(drawable != null)
                drawable.setBounds(rect);
        }
        return flag;
    }

    public void setOutlineProvider(ViewOutlineProvider viewoutlineprovider)
    {
        super.setOutlineProvider(viewoutlineprovider);
        mLastOutlineProvider = viewoutlineprovider;
    }

    public void setSurfaceFormat(int i)
    {
        mWindow.setFormat(i);
    }

    public void setSurfaceKeepScreenOn(boolean flag)
    {
        if(flag)
            mWindow.addFlags(128);
        else
            mWindow.clearFlags(128);
    }

    public void setSurfaceType(int i)
    {
        mWindow.setType(i);
    }

    void setUserCaptionBackgroundDrawable(Drawable drawable)
    {
        mUserCaptionBackgroundDrawable = drawable;
        if(mBackdropFrameRenderer != null)
            mBackdropFrameRenderer.setUserCaptionBackgroundDrawable(drawable);
    }

    void setWindow(PhoneWindow phonewindow)
    {
        mWindow = phonewindow;
        phonewindow = getContext();
        if(phonewindow instanceof DecorContext)
            ((DecorContext)phonewindow).setPhoneWindow(mWindow);
        mFirst = true;
    }

    public void setWindowBackground(Drawable drawable)
    {
        boolean flag = true;
        boolean flag1 = true;
        if(getBackground() != drawable)
        {
            setBackgroundDrawable(drawable);
            if(drawable != null)
            {
                if(!mWindow.isTranslucent())
                    flag1 = mWindow.isShowingWallpaper();
                mResizingBackgroundDrawable = enforceNonTranslucentBackground(drawable, flag1);
            } else
            {
                drawable = getContext();
                int i = mWindow.mBackgroundFallbackResource;
                boolean flag2 = flag;
                if(!mWindow.isTranslucent())
                    flag2 = mWindow.isShowingWallpaper();
                mResizingBackgroundDrawable = getResizingBackgroundDrawable(drawable, 0, i, flag2);
            }
            if(mResizingBackgroundDrawable != null)
                mResizingBackgroundDrawable.getPadding(mBackgroundPadding);
            else
                mBackgroundPadding.setEmpty();
            drawableChanged();
        }
    }

    public void setWindowFrame(Drawable drawable)
    {
        if(getForeground() != drawable)
        {
            setForeground(drawable);
            if(drawable != null)
                drawable.getPadding(mFramePadding);
            else
                mFramePadding.setEmpty();
            drawableChanged();
        }
    }

    boolean shouldAnimatePrimaryActionModeView()
    {
        return isLaidOut();
    }

    public boolean showContextMenuForChild(View view)
    {
        return showContextMenuForChildInternal(view, (0.0F / 0.0F), (0.0F / 0.0F));
    }

    public boolean showContextMenuForChild(View view, float f, float f1)
    {
        return showContextMenuForChildInternal(view, f, f1);
    }

    public ActionMode startActionMode(android.view.ActionMode.Callback callback)
    {
        return startActionMode(callback, 0);
    }

    public ActionMode startActionMode(android.view.ActionMode.Callback callback, int i)
    {
        return startActionMode(((View) (this)), callback, i);
    }

    public ActionMode startActionModeForChild(View view, android.view.ActionMode.Callback callback)
    {
        return startActionModeForChild(view, callback, 0);
    }

    public ActionMode startActionModeForChild(View view, android.view.ActionMode.Callback callback, int i)
    {
        return startActionMode(view, callback, i);
    }

    void startChanging()
    {
        mChanging = true;
    }

    public boolean superDispatchGenericMotionEvent(MotionEvent motionevent)
    {
        return super.dispatchGenericMotionEvent(motionevent);
    }

    public boolean superDispatchKeyEvent(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 4)
        {
            int i = keyevent.getAction();
            if(mPrimaryActionMode != null)
            {
                if(i == 1)
                    mPrimaryActionMode.finish();
                return true;
            }
        }
        return super.dispatchKeyEvent(keyevent);
    }

    public boolean superDispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        return super.dispatchKeyShortcutEvent(keyevent);
    }

    public boolean superDispatchTouchEvent(MotionEvent motionevent)
    {
        return super.dispatchTouchEvent(motionevent);
    }

    public boolean superDispatchTrackballEvent(MotionEvent motionevent)
    {
        return super.dispatchTrackballEvent(motionevent);
    }

    public String toString()
    {
        return (new StringBuilder()).append("DecorView@").append(Integer.toHexString(hashCode())).append("[").append(getTitleSuffix(mWindow.getAttributes())).append("]").toString();
    }

    WindowInsets updateColorViews(WindowInsets windowinsets, boolean flag)
    {
        Object obj = mWindow.getAttributes();
        int i = ((android.view.WindowManager.LayoutParams) (obj)).systemUiVisibility | getWindowSystemUiVisibility();
        int j;
        int k;
        int l;
        int i1;
        if(!mWindow.mIsFloating)
        {
            boolean flag1 = isLaidOut();
            boolean flag2;
            boolean flag5;
            if(((mLastWindowFlags ^ ((android.view.WindowManager.LayoutParams) (obj)).flags) & 0x80000000) != 0)
                j = 1;
            else
                j = 0;
            flag2 = flag1 ^ true | j;
            mLastWindowFlags = ((android.view.WindowManager.LayoutParams) (obj)).flags;
            k = ((flag2) ? 1 : 0);
            if(windowinsets != null)
            {
                mLastTopInset = getColorViewTopInset(windowinsets.getStableInsetTop(), windowinsets.getSystemWindowInsetTop());
                mLastBottomInset = getColorViewBottomInset(windowinsets.getStableInsetBottom(), windowinsets.getSystemWindowInsetBottom());
                mLastRightInset = getColorViewRightInset(windowinsets.getStableInsetRight(), windowinsets.getSystemWindowInsetRight());
                mLastLeftInset = getColorViewRightInset(windowinsets.getStableInsetLeft(), windowinsets.getSystemWindowInsetLeft());
                boolean flag3;
                boolean flag4;
                ColorViewState colorviewstate;
                if(windowinsets.getStableInsetTop() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag1 != mLastHasTopStableInset)
                    j = 1;
                else
                    j = 0;
                mLastHasTopStableInset = flag1;
                if(windowinsets.getStableInsetBottom() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag1 != mLastHasBottomStableInset)
                    k = 1;
                else
                    k = 0;
                mLastHasBottomStableInset = flag1;
                if(windowinsets.getStableInsetRight() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag1 != mLastHasRightStableInset)
                    l = 1;
                else
                    l = 0;
                mLastHasRightStableInset = flag1;
                if(windowinsets.getStableInsetLeft() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag1 != mLastHasLeftStableInset)
                    i1 = 1;
                else
                    i1 = 0;
                k = flag2 | j | k | l | i1;
                mLastHasLeftStableInset = flag1;
                mLastShouldAlwaysConsumeNavBar = windowinsets.shouldAlwaysConsumeNavBar();
            }
            flag3 = isNavBarToRightEdge(mLastBottomInset, mLastRightInset);
            flag4 = isNavBarToLeftEdge(mLastBottomInset, mLastLeftInset);
            i1 = getNavBarSize(mLastBottomInset, mLastRightInset, mLastLeftInset);
            colorviewstate = mNavigationColorViewState;
            l = mWindow.mNavigationBarColor;
            j = mWindow.mNavigationBarDividerColor;
            if(!flag3)
                flag1 = flag4;
            else
                flag1 = true;
            if(flag)
                flag5 = k ^ true;
            else
                flag5 = false;
            updateColorViewInt(colorviewstate, i, l, j, i1, flag1, flag4, 0, flag5, false);
            if(flag3)
                flag5 = mNavigationColorViewState.present;
            else
                flag5 = false;
            if(flag4)
                flag1 = mNavigationColorViewState.present;
            else
                flag1 = false;
            if(flag5)
                j = mLastRightInset;
            else
            if(flag1)
                j = mLastLeftInset;
            else
                j = 0;
            colorviewstate = mStatusColorViewState;
            i1 = calculateStatusBarColor();
            l = mLastTopInset;
            if(flag)
                flag = k ^ true;
            else
                flag = false;
            updateColorViewInt(colorviewstate, i, i1, 0, l, false, flag1, j, flag, mForceWindowDrawsStatusBarBackground);
        }
        if((((android.view.WindowManager.LayoutParams) (obj)).flags & 0x80000000) != 0 && (i & 0x200) == 0 && (i & 2) == 0)
            flag = true;
        else
            flag = mLastShouldAlwaysConsumeNavBar;
        if((i & 0x400) == 0 && (0x80000000 & i) == 0 && (((android.view.WindowManager.LayoutParams) (obj)).flags & 0x100) == 0 && (((android.view.WindowManager.LayoutParams) (obj)).flags & 0x10000) == 0 && mForceWindowDrawsStatusBarBackground)
        {
            if(mLastTopInset != 0)
                j = 1;
            else
                j = 0;
        } else
        {
            j = 0;
        }
        if(j != 0)
            j = mLastTopInset;
        else
            j = 0;
        if(flag)
            k = mLastRightInset;
        else
            k = 0;
        if(flag)
            l = mLastBottomInset;
        else
            l = 0;
        if(flag)
            i1 = mLastLeftInset;
        else
            i1 = 0;
        obj = windowinsets;
        if(mContentRoot != null)
        {
            obj = windowinsets;
            if(mContentRoot.getLayoutParams() instanceof android.view.ViewGroup.MarginLayoutParams)
            {
                obj = (android.view.ViewGroup.MarginLayoutParams)mContentRoot.getLayoutParams();
                if(((android.view.ViewGroup.MarginLayoutParams) (obj)).topMargin != j || ((android.view.ViewGroup.MarginLayoutParams) (obj)).rightMargin != k || ((android.view.ViewGroup.MarginLayoutParams) (obj)).bottomMargin != l || ((android.view.ViewGroup.MarginLayoutParams) (obj)).leftMargin != i1)
                {
                    obj.topMargin = j;
                    obj.rightMargin = k;
                    obj.bottomMargin = l;
                    obj.leftMargin = i1;
                    mContentRoot.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                    if(windowinsets == null)
                        requestApplyInsets();
                }
                obj = windowinsets;
                if(windowinsets != null)
                    obj = windowinsets.replaceSystemWindowInsets(windowinsets.getSystemWindowInsetLeft() - i1, windowinsets.getSystemWindowInsetTop() - j, windowinsets.getSystemWindowInsetRight() - k, windowinsets.getSystemWindowInsetBottom() - l);
            }
        }
        windowinsets = ((WindowInsets) (obj));
        if(obj != null)
            windowinsets = ((WindowInsets) (obj)).consumeStableInsets();
        return windowinsets;
    }

    void updateDecorCaptionShade()
    {
        if(mDecorCaptionView != null)
            setDecorCaptionShade(getContext(), mDecorCaptionView);
    }

    void updateLogTag(android.view.WindowManager.LayoutParams layoutparams)
    {
        mLogTag = (new StringBuilder()).append("DecorView[").append(getTitleSuffix(layoutparams)).append("]").toString();
    }

    void updateNavigationGuardColor()
    {
        byte byte0 = 0;
        if(mNavigationGuard != null)
        {
            View view = mNavigationGuard;
            if(mWindow.getNavigationBarColor() == 0)
                byte0 = 4;
            view.setVisibility(byte0);
        }
    }

    public void updatePictureInPictureOutlineProvider(boolean flag)
    {
        if(mIsInPictureInPictureMode == flag)
            return;
        if(!flag) goto _L2; else goto _L1
_L1:
        android.view.Window.WindowControllerCallback windowcontrollercallback = mWindow.getWindowControllerCallback();
        if(windowcontrollercallback != null && windowcontrollercallback.isTaskRoot())
            super.setOutlineProvider(PIP_OUTLINE_PROVIDER);
_L4:
        mIsInPictureInPictureMode = flag;
        return;
_L2:
        if(getOutlineProvider() != mLastOutlineProvider)
            setOutlineProvider(mLastOutlineProvider);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public android.view.InputQueue.Callback willYouTakeTheInputQueue()
    {
        android.view.InputQueue.Callback callback;
        if(mFeatureId < 0)
            callback = mWindow.mTakeInputQueueCallback;
        else
            callback = null;
        return callback;
    }

    public android.view.SurfaceHolder.Callback2 willYouTakeTheSurface()
    {
        android.view.SurfaceHolder.Callback2 callback2;
        if(mFeatureId < 0)
            callback2 = mWindow.mTakeSurfaceCallback;
        else
            callback2 = null;
        return callback2;
    }

    private static final boolean DEBUG_MEASURE = false;
    private static final int DECOR_SHADOW_FOCUSED_HEIGHT_IN_DIP = 20;
    private static final int DECOR_SHADOW_UNFOCUSED_HEIGHT_IN_DIP = 5;
    public static final ColorViewAttributes NAVIGATION_BAR_COLOR_VIEW_ATTRIBUTES = new ColorViewAttributes(2, 0x8000000, 80, 5, 3, "android:navigation:background", 0x1020030, 0, null);
    private static final ViewOutlineProvider PIP_OUTLINE_PROVIDER = new ViewOutlineProvider() {

        public void getOutline(View view, Outline outline)
        {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
            outline.setAlpha(1.0F);
        }

    }
;
    public static final ColorViewAttributes STATUS_BAR_COLOR_VIEW_ATTRIBUTES = new ColorViewAttributes(4, 0x4000000, 48, 3, 5, "android:status:background", 0x102002f, 1024, null);
    private static final boolean SWEEP_OPEN_MENU = false;
    private static final String TAG = "DecorView";
    private boolean mAllowUpdateElevation;
    private boolean mApplyFloatingHorizontalInsets;
    private boolean mApplyFloatingVerticalInsets;
    private float mAvailableWidth;
    private BackdropFrameRenderer mBackdropFrameRenderer;
    private final BackgroundFallback mBackgroundFallback = new BackgroundFallback();
    private final Rect mBackgroundPadding = new Rect();
    private final int mBarEnterExitDuration;
    private Drawable mCaptionBackgroundDrawable;
    private boolean mChanging;
    ViewGroup mContentRoot;
    DecorCaptionView mDecorCaptionView;
    int mDefaultOpacity;
    private int mDownY;
    private final Rect mDrawingBounds = new Rect();
    private boolean mElevationAdjustedForStack;
    private ObjectAnimator mFadeAnim;
    private final int mFeatureId;
    private ActionMode mFloatingActionMode;
    private View mFloatingActionModeOriginatingView;
    private final Rect mFloatingInsets = new Rect();
    private FloatingToolbar mFloatingToolbar;
    private android.view.ViewTreeObserver.OnPreDrawListener mFloatingToolbarPreDrawListener;
    final boolean mForceWindowDrawsStatusBarBackground;
    private final Rect mFrameOffsets = new Rect();
    private final Rect mFramePadding = new Rect();
    private boolean mHasCaption;
    private final Interpolator mHideInterpolator;
    private final Paint mHorizontalResizeShadowPaint = new Paint();
    private boolean mIsInPictureInPictureMode;
    private android.graphics.drawable.Drawable.Callback mLastBackgroundDrawableCb;
    private int mLastBottomInset;
    private boolean mLastHasBottomStableInset;
    private boolean mLastHasLeftStableInset;
    private boolean mLastHasRightStableInset;
    private boolean mLastHasTopStableInset;
    private int mLastLeftInset;
    private ViewOutlineProvider mLastOutlineProvider;
    private int mLastRightInset;
    private boolean mLastShouldAlwaysConsumeNavBar;
    private int mLastTopInset;
    private int mLastWindowFlags;
    String mLogTag;
    private Drawable mMenuBackground;
    private final ColorViewState mNavigationColorViewState;
    private View mNavigationGuard;
    private Rect mOutsets;
    ActionMode mPrimaryActionMode;
    private PopupWindow mPrimaryActionModePopup;
    private ActionBarContextView mPrimaryActionModeView;
    private int mResizeMode;
    private final int mResizeShadowSize;
    private Drawable mResizingBackgroundDrawable;
    private int mRootScrollY;
    private final int mSemiTransparentStatusBarColor;
    private final Interpolator mShowInterpolator;
    private Runnable mShowPrimaryActionModePopup;
    int mStackId;
    private final ColorViewState mStatusColorViewState;
    private View mStatusGuard;
    private Rect mTempRect;
    private Drawable mUserCaptionBackgroundDrawable;
    private final Paint mVerticalResizeShadowPaint = new Paint();
    private boolean mWatchingForMenu;
    private PhoneWindow mWindow;
    private boolean mWindowResizeCallbacksAdded;

}
