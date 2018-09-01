// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.IBinder;
import android.transition.*;
import android.util.AttributeSet;
import android.view.*;
import java.lang.ref.WeakReference;
import java.util.List;

// Referenced classes of package android.widget:
//            FrameLayout

public class PopupWindow
{
    public static interface OnDismissListener
    {

        public abstract void onDismiss();
    }

    private class PopupBackgroundView extends FrameLayout
    {

        protected int[] onCreateDrawableState(int i)
        {
            if(PopupWindow._2D_get1(PopupWindow.this))
            {
                int ai[] = super.onCreateDrawableState(i + 1);
                View.mergeDrawableStates(ai, PopupWindow._2D_get0());
                return ai;
            } else
            {
                return super.onCreateDrawableState(i);
            }
        }

        final PopupWindow this$0;

        public PopupBackgroundView(Context context)
        {
            this$0 = PopupWindow.this;
            super(context);
        }
    }

    private class PopupDecorView extends FrameLayout
    {

        static Runnable _2D_get0(PopupDecorView popupdecorview)
        {
            return popupdecorview.mCleanupAfterExit;
        }

        static void _2D_wrap0(PopupDecorView popupdecorview, Transition transition)
        {
            popupdecorview.startEnterTransition(transition);
        }

        private void startEnterTransition(Transition transition)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
            {
                View view = getChildAt(j);
                transition.addTarget(view);
                view.setVisibility(4);
            }

            TransitionManager.beginDelayedTransition(this, transition);
            for(int k = 0; k < i; k++)
                getChildAt(k).setVisibility(0);

        }

        public void cancelTransitions()
        {
            TransitionManager.endTransitions(this);
            if(mCleanupAfterExit != null)
                mCleanupAfterExit.run();
        }

        public boolean dispatchKeyEvent(KeyEvent keyevent)
        {
            if(keyevent.getKeyCode() == 4)
            {
                if(getKeyDispatcherState() == null)
                    return super.dispatchKeyEvent(keyevent);
                if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
                {
                    android.view.KeyEvent.DispatcherState dispatcherstate = getKeyDispatcherState();
                    if(dispatcherstate != null)
                        dispatcherstate.startTracking(keyevent, this);
                    return true;
                }
                if(keyevent.getAction() == 1)
                {
                    android.view.KeyEvent.DispatcherState dispatcherstate1 = getKeyDispatcherState();
                    if(dispatcherstate1 != null && dispatcherstate1.isTracking(keyevent) && keyevent.isCanceled() ^ true)
                    {
                        dismiss();
                        return true;
                    }
                }
                return super.dispatchKeyEvent(keyevent);
            } else
            {
                return super.dispatchKeyEvent(keyevent);
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionevent)
        {
            if(PopupWindow._2D_get3(PopupWindow.this) != null && PopupWindow._2D_get3(PopupWindow.this).onTouch(this, motionevent))
                return true;
            else
                return super.dispatchTouchEvent(motionevent);
        }

        void lambda$_2D_android_widget_PopupWindow$PopupDecorView_93357(android.transition.Transition.TransitionListener transitionlistener, Transition transition, View view)
        {
            transitionlistener.onTransitionEnd(transition);
            if(view != null)
                view.removeOnAttachStateChangeListener(mOnAnchorRootDetachedListener);
            mCleanupAfterExit = null;
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            int i = (int)motionevent.getX();
            for(int j = (int)motionevent.getY(); motionevent.getAction() == 0 && (i < 0 || i >= getWidth() || j < 0 || j >= getHeight());)
            {
                dismiss();
                return true;
            }

            if(motionevent.getAction() == 4)
            {
                dismiss();
                return true;
            } else
            {
                return super.onTouchEvent(motionevent);
            }
        }

        public void requestEnterTransition(Transition transition)
        {
            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
            if(viewtreeobserver != null && transition != null)
                viewtreeobserver.addOnGlobalLayoutListener(transition.clone(). new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                    public void onGlobalLayout()
                    {
                        Object obj = getViewTreeObserver();
                        if(obj != null)
                            ((ViewTreeObserver) (obj)).removeOnGlobalLayoutListener(this);
                        obj = getTransitionEpicenter();
                        enterTransition.setEpicenterCallback(((_cls1) (obj)). new android.transition.Transition.EpicenterCallback() {

                            public Rect onGetEpicenter(Transition transition)
                            {
                                return epicenter;
                            }

                            final PopupDecorView._cls2 this$2;
                            final Rect val$epicenter;

            
            {
                this$2 = final__pcls2;
                epicenter = Rect.this;
                super();
            }
                        }
);
                        PopupDecorView._2D_wrap0(PopupDecorView.this, enterTransition);
                    }

                    final PopupDecorView this$1;
                    final Transition val$enterTransition;

            
            {
                this$1 = final_popupdecorview;
                enterTransition = Transition.this;
                super();
            }
                }
);
        }

        public void requestKeyboardShortcuts(List list, int i)
        {
            if(PopupWindow._2D_get2(PopupWindow.this) != null)
            {
                View view = (View)PopupWindow._2D_get2(PopupWindow.this).get();
                if(view != null)
                    view.requestKeyboardShortcuts(list, i);
            }
        }

        public void startExitTransition(Transition transition, View view, Rect rect, android.transition.Transition.TransitionListener transitionlistener)
        {
            if(transition == null)
                return;
            if(view != null)
                view.addOnAttachStateChangeListener(mOnAnchorRootDetachedListener);
            mCleanupAfterExit = new _.Lambda.ISuHLqeK_K4pmesAfzlFglc3xF4._cls2(this, transitionlistener, transition, view);
            transition = transition.clone();
            transition.addListener(new TransitionListenerAdapter() {

                public void onTransitionEnd(Transition transition)
                {
                    transition.removeListener(this);
                    if(PopupDecorView._2D_get0(PopupDecorView.this) != null)
                        PopupDecorView._2D_get0(PopupDecorView.this).run();
                }

                final PopupDecorView this$1;

            
            {
                this$1 = PopupDecorView.this;
                super();
            }
            }
);
            transition.setEpicenterCallback(rect. new android.transition.Transition.EpicenterCallback() {

                public Rect onGetEpicenter(Transition transition)
                {
                    return epicenter;
                }

                final PopupDecorView this$1;
                final Rect val$epicenter;

            
            {
                this$1 = final_popupdecorview;
                epicenter = Rect.this;
                super();
            }
            }
);
            int i = getChildCount();
            for(int j = 0; j < i; j++)
                transition.addTarget(getChildAt(j));

            TransitionManager.beginDelayedTransition(this, transition);
            for(int k = 0; k < i; k++)
                getChildAt(k).setVisibility(4);

        }

        private Runnable mCleanupAfterExit;
        private final android.view.View.OnAttachStateChangeListener mOnAnchorRootDetachedListener = new _cls1();
        final PopupWindow this$0;

        public PopupDecorView(Context context)
        {
            this$0 = PopupWindow.this;
            super(context);
        }
    }


    static int[] _2D_get0()
    {
        return ABOVE_ANCHOR_STATE_SET;
    }

    static boolean _2D_get1(PopupWindow popupwindow)
    {
        return popupwindow.mAboveAnchor;
    }

    static WeakReference _2D_get2(PopupWindow popupwindow)
    {
        return popupwindow.mParentRootView;
    }

    static android.view.View.OnTouchListener _2D_get3(PopupWindow popupwindow)
    {
        return popupwindow.mTouchInterceptor;
    }

    static boolean _2D_set0(PopupWindow popupwindow, boolean flag)
    {
        popupwindow.mIsAnchorRootAttached = flag;
        return flag;
    }

    static void _2D_wrap0(PopupWindow popupwindow)
    {
        popupwindow.alignToAnchor();
    }

    static void _2D_wrap1(PopupWindow popupwindow, View view, ViewGroup viewgroup, View view1)
    {
        popupwindow.dismissImmediate(view, viewgroup, view1);
    }

    public PopupWindow()
    {
        this(((View) (null)), 0, 0);
    }

    public PopupWindow(int i, int j)
    {
        this(((View) (null)), i, j);
    }

    public PopupWindow(Context context)
    {
        this(context, ((AttributeSet) (null)));
    }

    public PopupWindow(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010076);
    }

    public PopupWindow(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public PopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        mTmpDrawingLocation = new int[2];
        mTmpScreenLocation = new int[2];
        mTmpAppLocation = new int[2];
        mTempRect = new Rect();
        mInputMethodMode = 0;
        mSoftInputMode = 1;
        mTouchable = true;
        mOutsideTouchable = false;
        mClippingEnabled = true;
        mSplitTouchEnabled = -1;
        mAllowScrollingAnchorParent = true;
        mLayoutInsetDecor = false;
        mAttachedInDecor = true;
        mAttachedInDecorSet = false;
        mWidth = -2;
        mHeight = -2;
        mWindowLayoutType = 1000;
        mIgnoreCheekPress = false;
        mAnimationStyle = -1;
        mGravity = 0;
        mOnAnchorDetachedListener = new android.view.View.OnAttachStateChangeListener() {

            public void onViewAttachedToWindow(View view)
            {
                PopupWindow._2D_wrap0(PopupWindow.this);
            }

            public void onViewDetachedFromWindow(View view)
            {
            }

            final PopupWindow this$0;

            
            {
                this$0 = PopupWindow.this;
                super();
            }
        }
;
        mOnAnchorRootDetachedListener = new android.view.View.OnAttachStateChangeListener() {

            public void onViewAttachedToWindow(View view)
            {
            }

            public void onViewDetachedFromWindow(View view)
            {
                PopupWindow._2D_set0(PopupWindow.this, false);
            }

            final PopupWindow this$0;

            
            {
                this$0 = PopupWindow.this;
                super();
            }
        }
;
        mOnScrollChangedListener = new _.Lambda.ISuHLqeK_K4pmesAfzlFglc3xF4._cls1((byte)0, this);
        mOnLayoutChangeListener = new _.Lambda.ISuHLqeK_K4pmesAfzlFglc3xF4((byte)0, this);
        mContext = context;
        mWindowManager = (WindowManager)context.getSystemService("window");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PopupWindow, i, j);
        Drawable drawable = typedarray.getDrawable(0);
        mElevation = typedarray.getDimension(3, 0.0F);
        mOverlapAnchor = typedarray.getBoolean(2, false);
        if(typedarray.hasValueOrEmpty(1))
        {
            i = typedarray.getResourceId(1, 0);
            if(i == 0x10302fa)
                mAnimationStyle = -1;
            else
                mAnimationStyle = i;
        } else
        {
            mAnimationStyle = -1;
        }
        attributeset = getTransition(typedarray.getResourceId(4, 0));
        if(typedarray.hasValueOrEmpty(5))
            context = getTransition(typedarray.getResourceId(5, 0));
        else
        if(attributeset == null)
            context = null;
        else
            context = attributeset.clone();
        typedarray.recycle();
        setEnterTransition(attributeset);
        setExitTransition(context);
        setBackgroundDrawable(drawable);
    }

    public PopupWindow(View view)
    {
        this(view, 0, 0);
    }

    public PopupWindow(View view, int i, int j)
    {
        this(view, i, j, false);
    }

    public PopupWindow(View view, int i, int j, boolean flag)
    {
        mTmpDrawingLocation = new int[2];
        mTmpScreenLocation = new int[2];
        mTmpAppLocation = new int[2];
        mTempRect = new Rect();
        mInputMethodMode = 0;
        mSoftInputMode = 1;
        mTouchable = true;
        mOutsideTouchable = false;
        mClippingEnabled = true;
        mSplitTouchEnabled = -1;
        mAllowScrollingAnchorParent = true;
        mLayoutInsetDecor = false;
        mAttachedInDecor = true;
        mAttachedInDecorSet = false;
        mWidth = -2;
        mHeight = -2;
        mWindowLayoutType = 1000;
        mIgnoreCheekPress = false;
        mAnimationStyle = -1;
        mGravity = 0;
        mOnAnchorDetachedListener = new _cls1();
        mOnAnchorRootDetachedListener = new _cls2();
        mOnScrollChangedListener = new _.Lambda.ISuHLqeK_K4pmesAfzlFglc3xF4._cls1((byte)1, this);
        mOnLayoutChangeListener = new _.Lambda.ISuHLqeK_K4pmesAfzlFglc3xF4((byte)1, this);
        if(view != null)
        {
            mContext = view.getContext();
            mWindowManager = (WindowManager)mContext.getSystemService("window");
        }
        setContentView(view);
        setWidth(i);
        setHeight(j);
        setFocusable(flag);
    }

    private void alignToAnchor()
    {
        View view;
        if(mAnchor != null)
            view = (View)mAnchor.get();
        else
            view = null;
        if(view != null && view.isAttachedToWindow() && hasDecorView())
        {
            android.view.WindowManager.LayoutParams layoutparams = getDecorViewLayoutParams();
            updateAboveAnchor(findDropDownPosition(view, layoutparams, mAnchorXoff, mAnchorYoff, layoutparams.width, layoutparams.height, mAnchoredGravity, false));
            update(layoutparams.x, layoutparams.y, -1, -1, true);
        }
    }

    private int computeAnimationResource()
    {
        if(mAnimationStyle == -1)
        {
            if(mIsDropdown)
            {
                int i;
                if(mAboveAnchor)
                    i = 0x10302ee;
                else
                    i = 0x10302ed;
                return i;
            } else
            {
                return 0;
            }
        } else
        {
            return mAnimationStyle;
        }
    }

    private int computeFlags(int i)
    {
        int j;
        i &= 0xff797de7;
        j = i;
        if(mIgnoreCheekPress)
            j = i | 0x8000;
        if(mFocusable) goto _L2; else goto _L1
_L1:
        j |= 8;
        i = j;
        if(mInputMethodMode == 1)
            i = j | 0x20000;
_L4:
label0:
        {
            j = i;
            if(!mTouchable)
                j = i | 0x10;
            i = j;
            if(mOutsideTouchable)
                i = j | 0x40000;
            if(mClippingEnabled)
            {
                j = i;
                if(!mClipToScreen)
                    break label0;
            }
            j = i | 0x200;
        }
        i = j;
        if(isSplitTouchEnabled())
            i = j | 0x800000;
        j = i;
        if(mLayoutInScreen)
            j = i | 0x100;
        i = j;
        if(mLayoutInsetDecor)
            i = j | 0x10000;
        j = i;
        if(mNotTouchModal)
            j = i | 0x20;
        i = j;
        if(mAttachedInDecor)
            i = j | 0x40000000;
        return i;
_L2:
        i = j;
        if(mInputMethodMode == 2)
            i = j | 0x20000;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int computeGravity()
    {
label0:
        {
            int i;
            int j;
            if(mGravity == 0)
                i = 0x800033;
            else
                i = mGravity;
            j = i;
            if(!mIsDropdown)
                break label0;
            if(!mClipToScreen)
            {
                j = i;
                if(!mClippingEnabled)
                    break label0;
            }
            j = i | 0x10000000;
        }
        return j;
    }

    private PopupBackgroundView createBackgroundView(View view)
    {
        Object obj = mContentView.getLayoutParams();
        byte byte0;
        if(obj != null && ((android.view.ViewGroup.LayoutParams) (obj)).height == -2)
            byte0 = -2;
        else
            byte0 = -1;
        obj = new PopupBackgroundView(mContext);
        ((PopupBackgroundView) (obj)).addView(view, new FrameLayout.LayoutParams(-1, byte0));
        return ((PopupBackgroundView) (obj));
    }

    private PopupDecorView createDecorView(View view)
    {
        Object obj = mContentView.getLayoutParams();
        byte byte0;
        if(obj != null && ((android.view.ViewGroup.LayoutParams) (obj)).height == -2)
            byte0 = -2;
        else
            byte0 = -1;
        obj = new PopupDecorView(mContext);
        ((PopupDecorView) (obj)).addView(view, -1, byte0);
        ((PopupDecorView) (obj)).setClipChildren(false);
        ((PopupDecorView) (obj)).setClipToPadding(false);
        return ((PopupDecorView) (obj));
    }

    private void dismissImmediate(View view, ViewGroup viewgroup, View view1)
    {
        if(view.getParent() != null)
            mWindowManager.removeViewImmediate(view);
        if(viewgroup != null)
            viewgroup.removeView(view1);
        mDecorView = null;
        mBackgroundView = null;
        mIsTransitioningToDismiss = false;
    }

    private View getAppRootView(View view)
    {
        View view1 = WindowManagerGlobal.getInstance().getWindowView(view.getApplicationWindowToken());
        if(view1 != null)
            return view1;
        else
            return view.getRootView();
    }

    private Transition getTransition(int i)
    {
        if(i != 0 && i != 0x10f0000)
        {
            Transition transition = TransitionInflater.from(mContext).inflateTransition(i);
            if(transition != null)
            {
                if(transition instanceof TransitionSet)
                {
                    if(((TransitionSet)transition).getTransitionCount() == 0)
                        i = 1;
                    else
                        i = 0;
                } else
                {
                    i = 0;
                }
                if(i == 0)
                    return transition;
            }
        }
        return null;
    }

    private void invokePopup(android.view.WindowManager.LayoutParams layoutparams)
    {
        if(mContext != null)
            layoutparams.packageName = mContext.getPackageName();
        PopupDecorView popupdecorview = mDecorView;
        popupdecorview.setFitsSystemWindows(mLayoutInsetDecor);
        setLayoutDirectionFromAnchor();
        mWindowManager.addView(popupdecorview, layoutparams);
        if(mEnterTransition != null)
            popupdecorview.requestEnterTransition(mEnterTransition);
    }

    private boolean positionInDisplayHorizontal(android.view.WindowManager.LayoutParams layoutparams, int i, int j, int k, int l, int i1, boolean flag)
    {
        boolean flag1 = true;
        j = k - j;
        layoutparams.x = layoutparams.x + j;
        k = layoutparams.x + i;
        if(k > i1)
            layoutparams.x = layoutparams.x - (k - i1);
        boolean flag2 = flag1;
        if(layoutparams.x < l)
        {
            layoutparams.x = l;
            k = i1 - l;
            if(flag && i > k)
            {
                layoutparams.width = k;
                flag2 = flag1;
            } else
            {
                flag2 = false;
            }
        }
        layoutparams.x = layoutparams.x - j;
        return flag2;
    }

    private boolean positionInDisplayVertical(android.view.WindowManager.LayoutParams layoutparams, int i, int j, int k, int l, int i1, boolean flag)
    {
        boolean flag1 = true;
        j = k - j;
        layoutparams.y = layoutparams.y + j;
        layoutparams.height = i;
        k = layoutparams.y + i;
        if(k > i1)
            layoutparams.y = layoutparams.y - (k - i1);
        boolean flag2 = flag1;
        if(layoutparams.y < l)
        {
            layoutparams.y = l;
            k = i1 - l;
            if(flag && i > k)
            {
                layoutparams.height = k;
                flag2 = flag1;
            } else
            {
                flag2 = false;
            }
        }
        layoutparams.y = layoutparams.y - j;
        return flag2;
    }

    private void preparePopup(android.view.WindowManager.LayoutParams layoutparams)
    {
        boolean flag;
        for(flag = true; mContentView == null || mContext == null || mWindowManager == null;)
            throw new IllegalStateException("You must specify a valid content view by calling setContentView() before attempting to show the popup.");

        if(layoutparams.accessibilityTitle == null)
            layoutparams.accessibilityTitle = mContext.getString(0x104053f);
        if(mDecorView != null)
            mDecorView.cancelTransitions();
        if(mBackground != null)
        {
            mBackgroundView = createBackgroundView(mContentView);
            mBackgroundView.setBackground(mBackground);
        } else
        {
            mBackgroundView = mContentView;
        }
        mDecorView = createDecorView(mBackgroundView);
        mBackgroundView.setElevation(mElevation);
        layoutparams.setSurfaceInsets(mBackgroundView, true, true);
        if(mContentView.getRawLayoutDirection() != 2)
            flag = false;
        mPopupViewInitialLayoutDirectionInherited = flag;
    }

    private void setLayoutDirectionFromAnchor()
    {
        if(mAnchor != null)
        {
            View view = (View)mAnchor.get();
            if(view != null && mPopupViewInitialLayoutDirectionInherited)
                mDecorView.setLayoutDirection(view.getLayoutDirection());
        }
    }

    private boolean tryFitHorizontal(android.view.WindowManager.LayoutParams layoutparams, int i, int j, int k, int l, int i1, int j1, 
            int k1, boolean flag)
    {
        i = layoutparams.x + (i1 - l);
        if(i >= 0 && j <= k1 - i)
            return true;
        return positionInDisplayHorizontal(layoutparams, j, l, i1, j1, k1, flag);
    }

    private boolean tryFitVertical(android.view.WindowManager.LayoutParams layoutparams, int i, int j, int k, int l, int i1, int j1, 
            int k1, boolean flag)
    {
        int l1 = layoutparams.y + (i1 - l);
        if(l1 >= 0 && j <= k1 - l1)
            return true;
        if(j <= l1 - k - j1)
        {
            i1 = i;
            if(mOverlapAnchor)
                i1 = i + k;
            layoutparams.y = (l - j) + i1;
            return true;
        }
        return positionInDisplayVertical(layoutparams, j, l, i1, j1, k1, flag);
    }

    private void update(View view, boolean flag, int i, int j, int k, int l)
    {
        Object obj;
        int j1;
        int l1;
        int j2;
        if(!isShowing() || hasContentView() ^ true)
            return;
        obj = mAnchor;
        int i1 = mAnchoredGravity;
        int k1;
        int i2;
        if(flag && (mAnchorXoff != i || mAnchorYoff != j))
            j1 = 1;
        else
            j1 = 0;
        break MISSING_BLOCK_LABEL_53;
        if(obj == null || ((WeakReference) (obj)).get() != view || j1 != 0 && mIsDropdown ^ true)
            attachToAnchor(view, i, j, i1);
        else
        if(j1 != 0)
        {
            mAnchorXoff = i;
            mAnchorYoff = j;
        }
        obj = getDecorViewLayoutParams();
        k1 = ((android.view.WindowManager.LayoutParams) (obj)).gravity;
        j1 = ((android.view.WindowManager.LayoutParams) (obj)).width;
        l1 = ((android.view.WindowManager.LayoutParams) (obj)).height;
        i2 = ((android.view.WindowManager.LayoutParams) (obj)).x;
        j2 = ((android.view.WindowManager.LayoutParams) (obj)).y;
        j = k;
        if(k < 0)
            j = mWidth;
        i = l;
        if(l < 0)
            i = mHeight;
        updateAboveAnchor(findDropDownPosition(view, ((android.view.WindowManager.LayoutParams) (obj)), mAnchorXoff, mAnchorYoff, j, i, i1, mAllowScrollingAnchorParent));
        break MISSING_BLOCK_LABEL_174;
        if(k1 != ((android.view.WindowManager.LayoutParams) (obj)).gravity || i2 != ((android.view.WindowManager.LayoutParams) (obj)).x || j2 != ((android.view.WindowManager.LayoutParams) (obj)).y || j1 != ((android.view.WindowManager.LayoutParams) (obj)).width)
            flag = true;
        else
        if(l1 != ((android.view.WindowManager.LayoutParams) (obj)).height)
            flag = true;
        else
            flag = false;
        if(j >= 0)
            j = ((android.view.WindowManager.LayoutParams) (obj)).width;
        if(i >= 0)
            i = ((android.view.WindowManager.LayoutParams) (obj)).height;
        update(((android.view.WindowManager.LayoutParams) (obj)).x, ((android.view.WindowManager.LayoutParams) (obj)).y, j, i, flag);
        return;
    }

    void _2D_android_widget_PopupWindow_2D_mthref_2D_0()
    {
        alignToAnchor();
    }

    protected void attachToAnchor(View view, int i, int j, int k)
    {
        detachFromAnchor();
        Object obj = view.getViewTreeObserver();
        if(obj != null)
            ((ViewTreeObserver) (obj)).addOnScrollChangedListener(mOnScrollChangedListener);
        view.addOnAttachStateChangeListener(mOnAnchorDetachedListener);
        obj = view.getRootView();
        ((View) (obj)).addOnAttachStateChangeListener(mOnAnchorRootDetachedListener);
        ((View) (obj)).addOnLayoutChangeListener(mOnLayoutChangeListener);
        mAnchor = new WeakReference(view);
        mAnchorRoot = new WeakReference(obj);
        mIsAnchorRootAttached = ((View) (obj)).isAttachedToWindow();
        mParentRootView = mAnchorRoot;
        mAnchorXoff = i;
        mAnchorYoff = j;
        mAnchoredGravity = k;
    }

    protected final android.view.WindowManager.LayoutParams createPopupLayoutParams(IBinder ibinder)
    {
        android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams();
        layoutparams.gravity = computeGravity();
        layoutparams.flags = computeFlags(layoutparams.flags);
        layoutparams.type = mWindowLayoutType;
        layoutparams.token = ibinder;
        layoutparams.softInputMode = mSoftInputMode;
        layoutparams.windowAnimations = computeAnimationResource();
        if(mBackground != null)
            layoutparams.format = mBackground.getOpacity();
        else
            layoutparams.format = -3;
        if(mHeightMode < 0)
        {
            int i = mHeightMode;
            mLastHeight = i;
            layoutparams.height = i;
        } else
        {
            int k = mHeight;
            mLastHeight = k;
            layoutparams.height = k;
        }
        if(mWidthMode < 0)
        {
            int j = mWidthMode;
            mLastWidth = j;
            layoutparams.width = j;
        } else
        {
            int l = mWidth;
            mLastWidth = l;
            layoutparams.width = l;
        }
        layoutparams.privateFlags = 0x18000;
        layoutparams.setTitle((new StringBuilder()).append("PopupWindow:").append(Integer.toHexString(hashCode())).toString());
        return layoutparams;
    }

    protected void detachFromAnchor()
    {
        View view = getAnchor();
        if(view != null)
        {
            view.getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
            view.removeOnAttachStateChangeListener(mOnAnchorDetachedListener);
        }
        if(mAnchorRoot != null)
            view = (View)mAnchorRoot.get();
        else
            view = null;
        if(view != null)
        {
            view.removeOnAttachStateChangeListener(mOnAnchorRootDetachedListener);
            view.removeOnLayoutChangeListener(mOnLayoutChangeListener);
        }
        mAnchor = null;
        mAnchorRoot = null;
        mIsAnchorRootAttached = false;
    }

    public void dismiss()
    {
        if(!isShowing() || isTransitioningToDismiss())
            return;
        final PopupDecorView decorView = mDecorView;
        final View contentView = mContentView;
        final ViewGroup contentHolder = contentView.getParent();
        Transition transition;
        if(contentHolder instanceof ViewGroup)
            contentHolder = (ViewGroup)contentHolder;
        else
            contentHolder = null;
        decorView.cancelTransitions();
        mIsShowing = false;
        mIsTransitioningToDismiss = true;
        transition = mExitTransition;
        if(transition != null && decorView.isLaidOut() && (mIsAnchorRootAttached || mAnchorRoot == null))
        {
            Object obj = (android.view.WindowManager.LayoutParams)decorView.getLayoutParams();
            obj.flags = ((android.view.WindowManager.LayoutParams) (obj)).flags | 0x10;
            obj.flags = ((android.view.WindowManager.LayoutParams) (obj)).flags | 8;
            obj.flags = ((android.view.WindowManager.LayoutParams) (obj)).flags & 0xfffdffff;
            mWindowManager.updateViewLayout(decorView, ((android.view.ViewGroup.LayoutParams) (obj)));
            if(mAnchorRoot != null)
                obj = (View)mAnchorRoot.get();
            else
                obj = null;
            decorView.startExitTransition(transition, ((View) (obj)), getTransitionEpicenter(), new TransitionListenerAdapter() {

                public void onTransitionEnd(Transition transition1)
                {
                    PopupWindow._2D_wrap1(PopupWindow.this, decorView, contentHolder, contentView);
                }

                final PopupWindow this$0;
                final ViewGroup val$contentHolder;
                final View val$contentView;
                final PopupDecorView val$decorView;

            
            {
                this$0 = PopupWindow.this;
                decorView = popupdecorview;
                contentHolder = viewgroup;
                contentView = view;
                super();
            }
            }
);
        } else
        {
            dismissImmediate(decorView, contentHolder, contentView);
        }
        detachFromAnchor();
        if(mOnDismissListener != null)
            mOnDismissListener.onDismiss();
    }

    protected final boolean findDropDownPosition(View view, android.view.WindowManager.LayoutParams layoutparams, int i, int j, int k, int l, int i1, 
            boolean flag)
    {
        int j1 = view.getHeight();
        int k1 = view.getWidth();
        int l1 = j;
        if(mOverlapAnchor)
            l1 = j - j1;
        int ai[] = mTmpAppLocation;
        View view1 = getAppRootView(view);
        view1.getLocationOnScreen(ai);
        int ai1[] = mTmpScreenLocation;
        view.getLocationOnScreen(ai1);
        int ai2[] = mTmpDrawingLocation;
        ai2[0] = ai1[0] - ai[0];
        ai2[1] = ai1[1] - ai[1];
        layoutparams.x = ai2[0] + i;
        layoutparams.y = ai2[1] + j1 + l1;
        Rect rect1 = new Rect();
        view1.getWindowVisibleDisplayFrame(rect1);
        j = k;
        if(k == -1)
            j = rect1.right - rect1.left;
        k = l;
        if(l == -1)
            k = rect1.bottom - rect1.top;
        layoutparams.gravity = computeGravity();
        layoutparams.width = j;
        layoutparams.height = k;
        l = Gravity.getAbsoluteGravity(i1, view.getLayoutDirection()) & 7;
        if(l == 5)
            layoutparams.x = layoutparams.x - (j - k1);
        boolean flag1 = tryFitVertical(layoutparams, l1, k, j1, ai2[1], ai1[1], rect1.top, rect1.bottom, false);
        boolean flag2 = tryFitHorizontal(layoutparams, i, j, k1, ai2[0], ai1[0], rect1.left, rect1.right, false);
        if(!flag1 || flag2 ^ true)
        {
            int i2 = view.getScrollX();
            i1 = view.getScrollY();
            Rect rect = new Rect(i2, i1, i2 + j + i, i1 + k + j1 + l1);
            if(flag && view.requestRectangleOnScreen(rect, true))
            {
                view.getLocationOnScreen(ai1);
                ai2[0] = ai1[0] - ai[0];
                ai2[1] = ai1[1] - ai[1];
                layoutparams.x = ai2[0] + i;
                layoutparams.y = ai2[1] + j1 + l1;
                if(l == 5)
                    layoutparams.x = layoutparams.x - (j - k1);
            }
            tryFitVertical(layoutparams, l1, k, j1, ai2[1], ai1[1], rect1.top, rect1.bottom, mClipToScreen);
            tryFitHorizontal(layoutparams, i, j, k1, ai2[0], ai1[0], rect1.left, rect1.right, mClipToScreen);
        }
        if(layoutparams.y < ai2[1])
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected final boolean getAllowScrollingAnchorParent()
    {
        return mAllowScrollingAnchorParent;
    }

    protected View getAnchor()
    {
        View view = null;
        if(mAnchor != null)
            view = (View)mAnchor.get();
        return view;
    }

    public int getAnimationStyle()
    {
        return mAnimationStyle;
    }

    public Drawable getBackground()
    {
        return mBackground;
    }

    public View getContentView()
    {
        return mContentView;
    }

    protected android.view.WindowManager.LayoutParams getDecorViewLayoutParams()
    {
        return (android.view.WindowManager.LayoutParams)mDecorView.getLayoutParams();
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

    public int getHeight()
    {
        return mHeight;
    }

    public int getInputMethodMode()
    {
        return mInputMethodMode;
    }

    public int getMaxAvailableHeight(View view)
    {
        return getMaxAvailableHeight(view, 0);
    }

    public int getMaxAvailableHeight(View view, int i)
    {
        return getMaxAvailableHeight(view, i, false);
    }

    public int getMaxAvailableHeight(View view, int i, boolean flag)
    {
        Rect rect = new Rect();
        getAppRootView(view).getWindowVisibleDisplayFrame(rect);
        if(flag)
        {
            Rect rect1 = new Rect();
            view.getWindowDisplayFrame(rect1);
            rect1.top = rect.top;
            rect1.right = rect.right;
            rect1.left = rect.left;
            rect = rect1;
        }
        int ai[] = mTmpDrawingLocation;
        view.getLocationOnScreen(ai);
        int j = rect.bottom;
        if(mOverlapAnchor)
            j = j - ai[1] - i;
        else
            j = j - (ai[1] + view.getHeight()) - i;
        j = Math.max(j, (ai[1] - rect.top) + i);
        i = j;
        if(mBackground != null)
        {
            mBackground.getPadding(mTempRect);
            i = j - (mTempRect.top + mTempRect.bottom);
        }
        return i;
    }

    protected final OnDismissListener getOnDismissListener()
    {
        return mOnDismissListener;
    }

    public boolean getOverlapAnchor()
    {
        return mOverlapAnchor;
    }

    public int getSoftInputMode()
    {
        return mSoftInputMode;
    }

    protected final Rect getTransitionEpicenter()
    {
        Object obj;
        PopupDecorView popupdecorview;
        if(mAnchor != null)
            obj = (View)mAnchor.get();
        else
            obj = null;
        popupdecorview = mDecorView;
        if(obj == null || popupdecorview == null)
            return null;
        int ai1[] = ((View) (obj)).getLocationOnScreen();
        int ai[] = mDecorView.getLocationOnScreen();
        obj = new Rect(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
        ((Rect) (obj)).offset(ai1[0] - ai[0], ai1[1] - ai[1]);
        if(mEpicenterBounds != null)
        {
            int i = ((Rect) (obj)).left;
            int j = ((Rect) (obj)).top;
            ((Rect) (obj)).set(mEpicenterBounds);
            ((Rect) (obj)).offset(i, j);
        }
        return ((Rect) (obj));
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int getWindowLayoutType()
    {
        return mWindowLayoutType;
    }

    protected boolean hasContentView()
    {
        boolean flag;
        if(mContentView != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean hasDecorView()
    {
        boolean flag;
        if(mDecorView != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isAboveAnchor()
    {
        return mAboveAnchor;
    }

    public boolean isAttachedInDecor()
    {
        return mAttachedInDecor;
    }

    public boolean isClippingEnabled()
    {
        return mClippingEnabled;
    }

    public boolean isFocusable()
    {
        return mFocusable;
    }

    public boolean isLayoutInScreenEnabled()
    {
        return mLayoutInScreen;
    }

    protected final boolean isLayoutInsetDecor()
    {
        return mLayoutInsetDecor;
    }

    public boolean isOutsideTouchable()
    {
        return mOutsideTouchable;
    }

    public boolean isShowing()
    {
        return mIsShowing;
    }

    public boolean isSplitTouchEnabled()
    {
        boolean flag = true;
        boolean flag1 = true;
        if(mSplitTouchEnabled < 0 && mContext != null)
        {
            if(mContext.getApplicationInfo().targetSdkVersion < 11)
                flag1 = false;
            return flag1;
        }
        if(mSplitTouchEnabled == 1)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    public boolean isTouchable()
    {
        return mTouchable;
    }

    protected final boolean isTransitioningToDismiss()
    {
        return mIsTransitioningToDismiss;
    }

    void lambda$_2D_android_widget_PopupWindow_9628(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        alignToAnchor();
    }

    void setAllowScrollingAnchorParent(boolean flag)
    {
        mAllowScrollingAnchorParent = flag;
    }

    public void setAnimationStyle(int i)
    {
        mAnimationStyle = i;
    }

    public void setAttachedInDecor(boolean flag)
    {
        mAttachedInDecor = flag;
        mAttachedInDecorSet = true;
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        mBackground = drawable;
        if(!(mBackground instanceof StateListDrawable)) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        byte byte0;
        int k;
        drawable = (StateListDrawable)mBackground;
        i = drawable.getStateDrawableIndex(ABOVE_ANCHOR_STATE_SET);
        j = drawable.getStateCount();
        byte0 = -1;
        k = 0;
_L8:
        int l = byte0;
        if(k >= j) goto _L4; else goto _L3
_L3:
        if(k == i) goto _L6; else goto _L5
_L5:
        l = k;
_L4:
        if(i != -1 && l != -1)
        {
            mAboveAnchorBackgroundDrawable = drawable.getStateDrawable(i);
            mBelowAnchorBackgroundDrawable = drawable.getStateDrawable(l);
        } else
        {
            mBelowAnchorBackgroundDrawable = null;
            mAboveAnchorBackgroundDrawable = null;
        }
_L2:
        return;
_L6:
        k++;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void setClipToScreenEnabled(boolean flag)
    {
        mClipToScreen = flag;
    }

    public void setClippingEnabled(boolean flag)
    {
        mClippingEnabled = flag;
    }

    public void setContentView(View view)
    {
        if(isShowing())
            return;
        mContentView = view;
        if(mContext == null && mContentView != null)
            mContext = mContentView.getContext();
        if(mWindowManager == null && mContentView != null)
            mWindowManager = (WindowManager)mContext.getSystemService("window");
        if(mContext != null && mAttachedInDecorSet ^ true)
        {
            boolean flag;
            if(mContext.getApplicationInfo().targetSdkVersion >= 22)
                flag = true;
            else
                flag = false;
            setAttachedInDecor(flag);
        }
    }

    protected final void setDropDown(boolean flag)
    {
        mIsDropdown = flag;
    }

    public void setElevation(float f)
    {
        mElevation = f;
    }

    public void setEnterTransition(Transition transition)
    {
        mEnterTransition = transition;
    }

    public void setEpicenterBounds(Rect rect)
    {
        mEpicenterBounds = rect;
    }

    public void setExitTransition(Transition transition)
    {
        mExitTransition = transition;
    }

    public void setFocusable(boolean flag)
    {
        mFocusable = flag;
    }

    public void setHeight(int i)
    {
        mHeight = i;
    }

    public void setIgnoreCheekPress()
    {
        mIgnoreCheekPress = true;
    }

    public void setInputMethodMode(int i)
    {
        mInputMethodMode = i;
    }

    public void setLayoutInScreenEnabled(boolean flag)
    {
        mLayoutInScreen = flag;
    }

    public void setLayoutInsetDecor(boolean flag)
    {
        mLayoutInsetDecor = flag;
    }

    public void setOnDismissListener(OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public void setOutsideTouchable(boolean flag)
    {
        mOutsideTouchable = flag;
    }

    public void setOverlapAnchor(boolean flag)
    {
        mOverlapAnchor = flag;
    }

    protected final void setShowing(boolean flag)
    {
        mIsShowing = flag;
    }

    public void setSoftInputMode(int i)
    {
        mSoftInputMode = i;
    }

    public void setSplitTouchEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        mSplitTouchEnabled = i;
    }

    public void setTouchInterceptor(android.view.View.OnTouchListener ontouchlistener)
    {
        mTouchInterceptor = ontouchlistener;
    }

    public void setTouchModal(boolean flag)
    {
        mNotTouchModal = flag ^ true;
    }

    public void setTouchable(boolean flag)
    {
        mTouchable = flag;
    }

    protected final void setTransitioningToDismiss(boolean flag)
    {
        mIsTransitioningToDismiss = flag;
    }

    public void setWidth(int i)
    {
        mWidth = i;
    }

    public void setWindowLayoutMode(int i, int j)
    {
        mWidthMode = i;
        mHeightMode = j;
    }

    public void setWindowLayoutType(int i)
    {
        mWindowLayoutType = i;
    }

    public void showAsDropDown(View view)
    {
        showAsDropDown(view, 0, 0);
    }

    public void showAsDropDown(View view, int i, int j)
    {
        showAsDropDown(view, i, j, 0x800033);
    }

    public void showAsDropDown(View view, int i, int j, int k)
    {
        if(isShowing() || hasContentView() ^ true)
            return;
        TransitionManager.endTransitions(mDecorView);
        attachToAnchor(view, i, j, k);
        mIsShowing = true;
        mIsDropdown = true;
        android.view.WindowManager.LayoutParams layoutparams = createPopupLayoutParams(view.getApplicationWindowToken());
        preparePopup(layoutparams);
        updateAboveAnchor(findDropDownPosition(view, layoutparams, i, j, layoutparams.width, layoutparams.height, k, mAllowScrollingAnchorParent));
        if(view != null)
            i = view.getAccessibilityViewId();
        else
            i = -1;
        layoutparams.accessibilityIdOfAnchor = i;
        invokePopup(layoutparams);
    }

    public void showAtLocation(IBinder ibinder, int i, int j, int k)
    {
        if(isShowing() || mContentView == null)
        {
            return;
        } else
        {
            TransitionManager.endTransitions(mDecorView);
            detachFromAnchor();
            mIsShowing = true;
            mIsDropdown = false;
            mGravity = i;
            ibinder = createPopupLayoutParams(ibinder);
            preparePopup(ibinder);
            ibinder.x = j;
            ibinder.y = k;
            invokePopup(ibinder);
            return;
        }
    }

    public void showAtLocation(View view, int i, int j, int k)
    {
        mParentRootView = new WeakReference(view.getRootView());
        showAtLocation(view.getWindowToken(), i, j, k);
    }

    public void update()
    {
        View view = null;
        if(!isShowing() || hasContentView() ^ true)
            return;
        android.view.WindowManager.LayoutParams layoutparams = getDecorViewLayoutParams();
        boolean flag = false;
        int i = computeAnimationResource();
        if(i != layoutparams.windowAnimations)
        {
            layoutparams.windowAnimations = i;
            flag = true;
        }
        i = computeFlags(layoutparams.flags);
        if(i != layoutparams.flags)
        {
            layoutparams.flags = i;
            flag = true;
        }
        i = computeGravity();
        if(i != layoutparams.gravity)
        {
            layoutparams.gravity = i;
            flag = true;
        }
        if(flag)
        {
            if(mAnchor != null)
                view = (View)mAnchor.get();
            update(view, layoutparams);
        }
    }

    public void update(int i, int j)
    {
        android.view.WindowManager.LayoutParams layoutparams = getDecorViewLayoutParams();
        update(layoutparams.x, layoutparams.y, i, j, false);
    }

    public void update(int i, int j, int k, int l)
    {
        update(i, j, k, l, false);
    }

    public void update(int i, int j, int k, int l, boolean flag)
    {
        if(k >= 0)
        {
            mLastWidth = k;
            setWidth(k);
        }
        if(l >= 0)
        {
            mLastHeight = l;
            setHeight(l);
        }
        if(!isShowing() || hasContentView() ^ true)
            return;
        android.view.WindowManager.LayoutParams layoutparams = getDecorViewLayoutParams();
        boolean flag1 = flag;
        int i1;
        Object obj;
        View view;
        if(mWidthMode < 0)
            i1 = mWidthMode;
        else
            i1 = mLastWidth;
        flag = flag1;
        if(k != -1)
        {
            flag = flag1;
            if(layoutparams.width != i1)
            {
                mLastWidth = i1;
                layoutparams.width = i1;
                flag = true;
            }
        }
        if(mHeightMode < 0)
            k = mHeightMode;
        else
            k = mLastHeight;
        flag1 = flag;
        if(l != -1)
        {
            flag1 = flag;
            if(layoutparams.height != k)
            {
                mLastHeight = k;
                layoutparams.height = k;
                flag1 = true;
            }
        }
        if(layoutparams.x != i)
        {
            layoutparams.x = i;
            flag1 = true;
        }
        flag = flag1;
        if(layoutparams.y != j)
        {
            layoutparams.y = j;
            flag = true;
        }
        i = computeAnimationResource();
        if(i != layoutparams.windowAnimations)
        {
            layoutparams.windowAnimations = i;
            flag = true;
        }
        i = computeFlags(layoutparams.flags);
        if(i != layoutparams.flags)
        {
            layoutparams.flags = i;
            flag = true;
        }
        i = computeGravity();
        if(i != layoutparams.gravity)
        {
            layoutparams.gravity = i;
            flag = true;
        }
        obj = null;
        j = -1;
        view = obj;
        i = j;
        if(mAnchor != null)
        {
            view = obj;
            i = j;
            if(mAnchor.get() != null)
            {
                view = (View)mAnchor.get();
                i = view.getAccessibilityViewId();
            }
        }
        if(i != layoutparams.accessibilityIdOfAnchor)
        {
            layoutparams.accessibilityIdOfAnchor = i;
            flag = true;
        }
        if(flag)
            update(view, layoutparams);
    }

    public void update(View view, int i, int j)
    {
        update(view, false, 0, 0, i, j);
    }

    public void update(View view, int i, int j, int k, int l)
    {
        update(view, true, i, j, k, l);
    }

    protected void update(View view, android.view.WindowManager.LayoutParams layoutparams)
    {
        setLayoutDirectionFromAnchor();
        mWindowManager.updateViewLayout(mDecorView, layoutparams);
    }

    protected final void updateAboveAnchor(boolean flag)
    {
        if(flag != mAboveAnchor)
        {
            mAboveAnchor = flag;
            if(mBackground != null && mBackgroundView != null)
                if(mAboveAnchorBackgroundDrawable != null)
                {
                    if(mAboveAnchor)
                        mBackgroundView.setBackground(mAboveAnchorBackgroundDrawable);
                    else
                        mBackgroundView.setBackground(mBelowAnchorBackgroundDrawable);
                } else
                {
                    mBackgroundView.refreshDrawableState();
                }
        }
    }

    private static final int ABOVE_ANCHOR_STATE_SET[] = {
        0x10100aa
    };
    private static final int ANIMATION_STYLE_DEFAULT = -1;
    private static final int DEFAULT_ANCHORED_GRAVITY = 0x800033;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    private boolean mAboveAnchor;
    private Drawable mAboveAnchorBackgroundDrawable;
    private boolean mAllowScrollingAnchorParent;
    private WeakReference mAnchor;
    private WeakReference mAnchorRoot;
    private int mAnchorXoff;
    private int mAnchorYoff;
    private int mAnchoredGravity;
    private int mAnimationStyle;
    private boolean mAttachedInDecor;
    private boolean mAttachedInDecorSet;
    private Drawable mBackground;
    private View mBackgroundView;
    private Drawable mBelowAnchorBackgroundDrawable;
    private boolean mClipToScreen;
    private boolean mClippingEnabled;
    private View mContentView;
    private Context mContext;
    private PopupDecorView mDecorView;
    private float mElevation;
    private Transition mEnterTransition;
    private Rect mEpicenterBounds;
    private Transition mExitTransition;
    private boolean mFocusable;
    private int mGravity;
    private int mHeight;
    private int mHeightMode;
    private boolean mIgnoreCheekPress;
    private int mInputMethodMode;
    private boolean mIsAnchorRootAttached;
    private boolean mIsDropdown;
    private boolean mIsShowing;
    private boolean mIsTransitioningToDismiss;
    private int mLastHeight;
    private int mLastWidth;
    private boolean mLayoutInScreen;
    private boolean mLayoutInsetDecor;
    private boolean mNotTouchModal;
    private final android.view.View.OnAttachStateChangeListener mOnAnchorDetachedListener;
    private final android.view.View.OnAttachStateChangeListener mOnAnchorRootDetachedListener;
    private OnDismissListener mOnDismissListener;
    private final android.view.View.OnLayoutChangeListener mOnLayoutChangeListener;
    private final android.view.ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    private boolean mOutsideTouchable;
    private boolean mOverlapAnchor;
    private WeakReference mParentRootView;
    private boolean mPopupViewInitialLayoutDirectionInherited;
    private int mSoftInputMode;
    private int mSplitTouchEnabled;
    private final Rect mTempRect;
    private final int mTmpAppLocation[];
    private final int mTmpDrawingLocation[];
    private final int mTmpScreenLocation[];
    private android.view.View.OnTouchListener mTouchInterceptor;
    private boolean mTouchable;
    private int mWidth;
    private int mWidthMode;
    private int mWindowLayoutType;
    private WindowManager mWindowManager;


    // Unreferenced inner class android/widget/PopupWindow$PopupDecorView$1

/* anonymous class */
    class PopupDecorView._cls1
        implements android.view.View.OnAttachStateChangeListener
    {

        public void onViewAttachedToWindow(View view)
        {
        }

        public void onViewDetachedFromWindow(View view)
        {
            view.removeOnAttachStateChangeListener(this);
            TransitionManager.endTransitions(PopupDecorView.this);
        }

        final PopupDecorView this$1;

            
            {
                this$1 = PopupDecorView.this;
                super();
            }
    }

}
