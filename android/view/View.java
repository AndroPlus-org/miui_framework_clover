// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManagerGlobal;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityEventSource;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.android.internal.view.TooltipPopup;
import com.android.internal.view.menu.MenuBuilder;
import com.android.internal.widget.ScrollBarUtils;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import com.miui.internal.contentcatcher.IInterceptor;
import java.lang.ref.WeakReference;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import miui.contentcatcher.InterceptorProxy;

// Referenced classes of package android.view:
//            ViewOutlineProvider, InputEventConsistencyVerifier, RenderNode, ViewConfiguration, 
//            PointerIcon, ViewGroup, ViewOverlay, ViewRootImpl, 
//            MotionEvent, ViewStructure, DisplayListCanvas, FrameMetricsObserver, 
//            ViewParent, HandlerActionQueue, LayoutInflater, ThreadedRenderer, 
//            ViewPropertyAnimator, DragEvent, IWindowSession, WindowInsets, 
//            GhostView, ViewTreeObserver, KeyEvent, Display, 
//            ViewHierarchyEncoder, ViewDebug, WindowId, FocusFinder, 
//            RoundScrollbarRenderer, Gravity, AbsSavedState, TouchDelegate, 
//            Choreographer, Surface, Window, ContextMenu, 
//            ActionMode, IWindow, IWindowId

public class View
    implements android.graphics.drawable.Drawable.Callback, KeyEvent.Callback, AccessibilityEventSource
{
    public static class AccessibilityDelegate
    {

        public void addExtraDataToAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo, String s, Bundle bundle)
        {
            view.addExtraDataToAccessibilityNodeInfo(accessibilitynodeinfo, s, bundle);
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(View view)
        {
            return view.createAccessibilityNodeInfoInternal();
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            return view.dispatchPopulateAccessibilityEventInternal(accessibilityevent);
        }

        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view)
        {
            return null;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            view.onInitializeAccessibilityEventInternal(accessibilityevent);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            view.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            view.onPopulateAccessibilityEventInternal(accessibilityevent);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            return viewgroup.onRequestSendAccessibilityEventInternal(view, accessibilityevent);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            return view.performAccessibilityActionInternal(i, bundle);
        }

        public void sendAccessibilityEvent(View view, int i)
        {
            view.sendAccessibilityEventInternal(i);
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
        {
            view.sendAccessibilityEventUncheckedInternal(accessibilityevent);
        }

        public AccessibilityDelegate()
        {
        }
    }

    static final class AttachInfo
    {

        int mAccessibilityFetchFlags;
        Drawable mAccessibilityFocusDrawable;
        int mAccessibilityWindowId;
        boolean mAlwaysConsumeNavBar;
        float mApplicationScale;
        Drawable mAutofilledDrawable;
        Canvas mCanvas;
        final Rect mContentInsets = new Rect();
        boolean mDebugLayout;
        int mDisabledSystemUiVisibility;
        Display mDisplay;
        int mDisplayState;
        public Surface mDragSurface;
        IBinder mDragToken;
        long mDrawingTime;
        List mEmptyPartialLayoutViews;
        boolean mForceReportNewAttributes;
        final ViewTreeObserver.InternalInsetsInfo mGivenInternalInsets = new ViewTreeObserver.InternalInsetsInfo();
        int mGlobalSystemUiVisibility;
        final Handler mHandler;
        boolean mHandlingPointerEvent;
        boolean mHardwareAccelerated;
        boolean mHardwareAccelerationRequested;
        boolean mHasNonEmptyGivenInternalInsets;
        boolean mHasSystemUiListeners;
        boolean mHasWindowFocus;
        boolean mHighContrastText;
        IWindowId mIWindowId;
        boolean mIgnoreDirtyState;
        boolean mInTouchMode;
        final int mInvalidateChildLocation[] = new int[2];
        boolean mKeepScreenOn;
        final KeyEvent.DispatcherState mKeyDispatchState = new KeyEvent.DispatcherState();
        boolean mNeedsUpdateLightCenter;
        final Rect mOutsets = new Rect();
        final Rect mOverscanInsets = new Rect();
        boolean mOverscanRequested;
        IBinder mPanelParentWindowToken;
        List mPartialLayoutViews;
        List mPendingAnimatingRenderNodes;
        final Point mPoint = new Point();
        boolean mRecomputeGlobalAttributes;
        final Callbacks mRootCallbacks;
        View mRootView;
        boolean mScalingRequired;
        final ArrayList mScrollContainers = new ArrayList();
        final IWindowSession mSession;
        boolean mSetIgnoreDirtyState;
        final Rect mStableInsets = new Rect();
        int mSystemUiVisibility;
        final ArrayList mTempArrayList = new ArrayList(24);
        ThreadedRenderer mThreadedRenderer;
        final Rect mTmpInvalRect = new Rect();
        final int mTmpLocation[] = new int[2];
        final Matrix mTmpMatrix = new Matrix();
        final Outline mTmpOutline = new Outline();
        final List mTmpRectList = new ArrayList();
        final float mTmpTransformLocation[] = new float[2];
        final RectF mTmpTransformRect = new RectF();
        final RectF mTmpTransformRect1 = new RectF();
        final Transformation mTmpTransformation = new Transformation();
        View mTooltipHost;
        final int mTransparentLocation[] = new int[2];
        final ViewTreeObserver mTreeObserver;
        boolean mUnbufferedDispatchRequested;
        boolean mUse32BitDrawingCache;
        View mViewRequestingLayout;
        final ViewRootImpl mViewRootImpl;
        boolean mViewScrollChanged;
        boolean mViewVisibilityChanged;
        final Rect mVisibleInsets = new Rect();
        final IWindow mWindow;
        WindowId mWindowId;
        int mWindowLeft;
        final IBinder mWindowToken;
        int mWindowTop;
        int mWindowVisibility;

        AttachInfo(IWindowSession iwindowsession, IWindow iwindow, Display display, ViewRootImpl viewrootimpl, Handler handler, Callbacks callbacks, Context context)
        {
            mDisplayState = 0;
            mSetIgnoreDirtyState = false;
            mGlobalSystemUiVisibility = -1;
            mAccessibilityWindowId = -1;
            mDebugLayout = SystemProperties.getBoolean("debug.layout", false);
            mPartialLayoutViews = new ArrayList();
            mSession = iwindowsession;
            mWindow = iwindow;
            mWindowToken = iwindow.asBinder();
            mDisplay = display;
            mViewRootImpl = viewrootimpl;
            mHandler = handler;
            mRootCallbacks = callbacks;
            mTreeObserver = new ViewTreeObserver(context);
        }
    }

    static interface AttachInfo.Callbacks
    {

        public abstract boolean performHapticFeedback(int i, boolean flag);

        public abstract void playSoundEffect(int i);
    }

    static class AttachInfo.InvalidateInfo
    {

        public static AttachInfo.InvalidateInfo obtain()
        {
            AttachInfo.InvalidateInfo invalidateinfo = (AttachInfo.InvalidateInfo)sPool.acquire();
            if(invalidateinfo == null)
                invalidateinfo = new AttachInfo.InvalidateInfo();
            return invalidateinfo;
        }

        public void recycle()
        {
            target = null;
            sPool.release(this);
        }

        private static final int POOL_LIMIT = 10;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(10);
        int bottom;
        int left;
        int right;
        View target;
        int top;


        AttachInfo.InvalidateInfo()
        {
        }
    }

    public static class BaseSavedState extends AbsSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(mSavedData);
            parcel.writeString(mStartActivityRequestWhoSaved);
            parcel.writeBoolean(mIsAutofilled);
            parcel.writeInt(mAutofillViewId);
        }

        static final int AUTOFILL_ID = 4;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

            public BaseSavedState createFromParcel(Parcel parcel)
            {
                return new BaseSavedState(parcel);
            }

            public BaseSavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new BaseSavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public BaseSavedState[] newArray(int i)
            {
                return new BaseSavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        static final int IS_AUTOFILLED = 2;
        static final int START_ACTIVITY_REQUESTED_WHO_SAVED = 1;
        int mAutofillViewId;
        boolean mIsAutofilled;
        int mSavedData;
        String mStartActivityRequestWhoSaved;


        public BaseSavedState(Parcel parcel)
        {
            this(parcel, null);
        }

        public BaseSavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            mSavedData = parcel.readInt();
            mStartActivityRequestWhoSaved = parcel.readString();
            mIsAutofilled = parcel.readBoolean();
            mAutofillViewId = parcel.readInt();
        }

        public BaseSavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    private final class CheckForLongPress
        implements Runnable
    {

        public void rememberPressedState()
        {
            mOriginalPressedState = isPressed();
        }

        public void rememberWindowAttachCount()
        {
            mOriginalWindowAttachCount = mWindowAttachCount;
        }

        public void run()
        {
            if(mOriginalPressedState == isPressed() && mParent != null && mOriginalWindowAttachCount == mWindowAttachCount && performLongClick(mX, mY))
                View._2D_set0(View.this, true);
        }

        public void setAnchor(float f, float f1)
        {
            mX = f;
            mY = f1;
        }

        private boolean mOriginalPressedState;
        private int mOriginalWindowAttachCount;
        private float mX;
        private float mY;
        final View this$0;

        private CheckForLongPress()
        {
            this$0 = View.this;
            super();
        }

        CheckForLongPress(CheckForLongPress checkforlongpress)
        {
            this();
        }
    }

    private final class CheckForTap
        implements Runnable
    {

        public void run()
        {
            View view = View.this;
            view.mPrivateFlags = view.mPrivateFlags & 0xfdffffff;
            View._2D_wrap1(View.this, true, x, y);
            View._2D_wrap0(View.this, ViewConfiguration.getTapTimeout(), x, y);
        }

        final View this$0;
        public float x;
        public float y;

        private CheckForTap()
        {
            this$0 = View.this;
            super();
        }

        CheckForTap(CheckForTap checkfortap)
        {
            this();
        }
    }

    private static class DeclaredOnClickListener
        implements OnClickListener
    {

        private void resolveMethod(Context context, String s)
        {
_L2:
            if(context == null)
                break; /* Loop/switch isn't completed */
            if(context.isRestricted())
                break MISSING_BLOCK_LABEL_48;
            s = context.getClass().getMethod(mMethodName, new Class[] {
                android/view/View
            });
            if(s != null)
                try
                {
                    mResolvedMethod = s;
                    mResolvedContext = context;
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(String s) { }
            if(context instanceof ContextWrapper)
                context = ((ContextWrapper)context).getBaseContext();
            else
                context = null;
            if(true) goto _L2; else goto _L1
_L1:
            int i = mHostView.getId();
            if(i == -1)
                context = "";
            else
                context = (new StringBuilder()).append(" with id '").append(mHostView.getContext().getResources().getResourceEntryName(i)).append("'").toString();
            throw new IllegalStateException((new StringBuilder()).append("Could not find method ").append(mMethodName).append("(View) in a parent or ancestor Context for android:onClick ").append("attribute defined on view ").append(mHostView.getClass()).append(context).toString());
        }

        public void onClick(View view)
        {
            if(mResolvedMethod == null)
                resolveMethod(mHostView.getContext(), mMethodName);
            try
            {
                mResolvedMethod.invoke(mResolvedContext, new Object[] {
                    view
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", view);
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw new IllegalStateException("Could not execute method for android:onClick", view);
            }
        }

        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(View view, String s)
        {
            mHostView = view;
            mMethodName = s;
        }
    }

    public static class DragShadowBuilder
    {

        public final View getView()
        {
            return (View)mView.get();
        }

        public void onDrawShadow(Canvas canvas)
        {
            View view = (View)mView.get();
            if(view != null)
                view.draw(canvas);
            else
                Log.e("View", "Asked to draw drag shadow but no view");
        }

        public void onProvideShadowMetrics(Point point, Point point1)
        {
            View view = (View)mView.get();
            if(view != null)
            {
                point.set(view.getWidth(), view.getHeight());
                point1.set(point.x / 2, point.y / 2);
            } else
            {
                Log.e("View", "Asked for drag thumb metrics but no view");
            }
        }

        private final WeakReference mView;

        public DragShadowBuilder()
        {
            mView = new WeakReference(null);
        }

        public DragShadowBuilder(View view)
        {
            mView = new WeakReference(view);
        }
    }

    private static class ForegroundInfo
    {

        static boolean _2D_get0(ForegroundInfo foregroundinfo)
        {
            return foregroundinfo.mBoundsChanged;
        }

        static Drawable _2D_get1(ForegroundInfo foregroundinfo)
        {
            return foregroundinfo.mDrawable;
        }

        static int _2D_get2(ForegroundInfo foregroundinfo)
        {
            return foregroundinfo.mGravity;
        }

        static boolean _2D_get3(ForegroundInfo foregroundinfo)
        {
            return foregroundinfo.mInsidePadding;
        }

        static Rect _2D_get4(ForegroundInfo foregroundinfo)
        {
            return foregroundinfo.mOverlayBounds;
        }

        static Rect _2D_get5(ForegroundInfo foregroundinfo)
        {
            return foregroundinfo.mSelfBounds;
        }

        static TintInfo _2D_get6(ForegroundInfo foregroundinfo)
        {
            return foregroundinfo.mTintInfo;
        }

        static boolean _2D_set0(ForegroundInfo foregroundinfo, boolean flag)
        {
            foregroundinfo.mBoundsChanged = flag;
            return flag;
        }

        static Drawable _2D_set1(ForegroundInfo foregroundinfo, Drawable drawable)
        {
            foregroundinfo.mDrawable = drawable;
            return drawable;
        }

        static int _2D_set2(ForegroundInfo foregroundinfo, int i)
        {
            foregroundinfo.mGravity = i;
            return i;
        }

        static boolean _2D_set3(ForegroundInfo foregroundinfo, boolean flag)
        {
            foregroundinfo.mInsidePadding = flag;
            return flag;
        }

        static TintInfo _2D_set4(ForegroundInfo foregroundinfo, TintInfo tintinfo)
        {
            foregroundinfo.mTintInfo = tintinfo;
            return tintinfo;
        }

        private boolean mBoundsChanged;
        private Drawable mDrawable;
        private int mGravity;
        private boolean mInsidePadding;
        private final Rect mOverlayBounds;
        private final Rect mSelfBounds;
        private TintInfo mTintInfo;

        private ForegroundInfo()
        {
            mGravity = 119;
            mInsidePadding = true;
            mBoundsChanged = true;
            mSelfBounds = new Rect();
            mOverlayBounds = new Rect();
        }

        ForegroundInfo(ForegroundInfo foregroundinfo)
        {
            this();
        }
    }

    static class ListenerInfo
    {

        static CopyOnWriteArrayList _2D_get0(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnAttachStateChangeListeners;
        }

        static OnDragListener _2D_get1(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnDragListener;
        }

        static OnGenericMotionListener _2D_get2(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnGenericMotionListener;
        }

        static OnHoverListener _2D_get3(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnHoverListener;
        }

        static OnKeyListener _2D_get4(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnKeyListener;
        }

        static ArrayList _2D_get5(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnLayoutChangeListeners;
        }

        static OnSystemUiVisibilityChangeListener _2D_get6(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnSystemUiVisibilityChangeListener;
        }

        static OnTouchListener _2D_get7(ListenerInfo listenerinfo)
        {
            return listenerinfo.mOnTouchListener;
        }

        static CopyOnWriteArrayList _2D_set0(ListenerInfo listenerinfo, CopyOnWriteArrayList copyonwritearraylist)
        {
            listenerinfo.mOnAttachStateChangeListeners = copyonwritearraylist;
            return copyonwritearraylist;
        }

        static OnDragListener _2D_set1(ListenerInfo listenerinfo, OnDragListener ondraglistener)
        {
            listenerinfo.mOnDragListener = ondraglistener;
            return ondraglistener;
        }

        static OnGenericMotionListener _2D_set2(ListenerInfo listenerinfo, OnGenericMotionListener ongenericmotionlistener)
        {
            listenerinfo.mOnGenericMotionListener = ongenericmotionlistener;
            return ongenericmotionlistener;
        }

        static OnHoverListener _2D_set3(ListenerInfo listenerinfo, OnHoverListener onhoverlistener)
        {
            listenerinfo.mOnHoverListener = onhoverlistener;
            return onhoverlistener;
        }

        static OnKeyListener _2D_set4(ListenerInfo listenerinfo, OnKeyListener onkeylistener)
        {
            listenerinfo.mOnKeyListener = onkeylistener;
            return onkeylistener;
        }

        static ArrayList _2D_set5(ListenerInfo listenerinfo, ArrayList arraylist)
        {
            listenerinfo.mOnLayoutChangeListeners = arraylist;
            return arraylist;
        }

        static OnSystemUiVisibilityChangeListener _2D_set6(ListenerInfo listenerinfo, OnSystemUiVisibilityChangeListener onsystemuivisibilitychangelistener)
        {
            listenerinfo.mOnSystemUiVisibilityChangeListener = onsystemuivisibilitychangelistener;
            return onsystemuivisibilitychangelistener;
        }

        static OnTouchListener _2D_set7(ListenerInfo listenerinfo, OnTouchListener ontouchlistener)
        {
            listenerinfo.mOnTouchListener = ontouchlistener;
            return ontouchlistener;
        }

        OnApplyWindowInsetsListener mOnApplyWindowInsetsListener;
        private CopyOnWriteArrayList mOnAttachStateChangeListeners;
        OnCapturedPointerListener mOnCapturedPointerListener;
        public OnClickListener mOnClickListener;
        protected OnContextClickListener mOnContextClickListener;
        protected OnCreateContextMenuListener mOnCreateContextMenuListener;
        private OnDragListener mOnDragListener;
        protected OnFocusChangeListener mOnFocusChangeListener;
        private OnGenericMotionListener mOnGenericMotionListener;
        private OnHoverListener mOnHoverListener;
        private OnKeyListener mOnKeyListener;
        private ArrayList mOnLayoutChangeListeners;
        protected OnLongClickListener mOnLongClickListener;
        protected OnScrollChangeListener mOnScrollChangeListener;
        private OnSystemUiVisibilityChangeListener mOnSystemUiVisibilityChangeListener;
        private OnTouchListener mOnTouchListener;

        ListenerInfo()
        {
        }
    }

    private static class MatchIdPredicate
        implements Predicate
    {

        public boolean test(View view)
        {
            boolean flag;
            if(view.mID == mId)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public volatile boolean test(Object obj)
        {
            return test((View)obj);
        }

        public int mId;

        private MatchIdPredicate()
        {
        }

        MatchIdPredicate(MatchIdPredicate matchidpredicate)
        {
            this();
        }
    }

    private static class MatchLabelForPredicate
        implements Predicate
    {

        static int _2D_set0(MatchLabelForPredicate matchlabelforpredicate, int i)
        {
            matchlabelforpredicate.mLabeledId = i;
            return i;
        }

        public boolean test(View view)
        {
            boolean flag;
            if(View._2D_get0(view) == mLabeledId)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public volatile boolean test(Object obj)
        {
            return test((View)obj);
        }

        private int mLabeledId;

        private MatchLabelForPredicate()
        {
        }

        MatchLabelForPredicate(MatchLabelForPredicate matchlabelforpredicate)
        {
            this();
        }
    }

    public static class MeasureSpec
    {

        static int adjust(int i, int j)
        {
            int k = getMode(i);
            int l = getSize(i);
            if(k == 0)
                return makeMeasureSpec(l, 0);
            int i1 = l + j;
            l = i1;
            if(i1 < 0)
            {
                Log.e("View", (new StringBuilder()).append("MeasureSpec.adjust: new size would be negative! (").append(i1).append(") spec: ").append(toString(i)).append(" delta: ").append(j).toString());
                l = 0;
            }
            return makeMeasureSpec(l, k);
        }

        public static int getMode(int i)
        {
            return 0xc0000000 & i;
        }

        public static int getSize(int i)
        {
            return 0x3fffffff & i;
        }

        public static int makeMeasureSpec(int i, int j)
        {
            if(View._2D_get1())
                return i + j;
            else
                return 0x3fffffff & i | 0xc0000000 & j;
        }

        public static int makeSafeMeasureSpec(int i, int j)
        {
            if(View.sUseZeroUnspecifiedMeasureSpec && j == 0)
                return 0;
            else
                return makeMeasureSpec(i, j);
        }

        public static String toString(int i)
        {
            int j = getMode(i);
            i = getSize(i);
            StringBuilder stringbuilder = new StringBuilder("MeasureSpec: ");
            if(j == 0)
                stringbuilder.append("UNSPECIFIED ");
            else
            if(j == 0x40000000)
                stringbuilder.append("EXACTLY ");
            else
            if(j == 0x80000000)
                stringbuilder.append("AT_MOST ");
            else
                stringbuilder.append(j).append(" ");
            stringbuilder.append(i);
            return stringbuilder.toString();
        }

        public static final int AT_MOST = 0x80000000;
        public static final int EXACTLY = 0x40000000;
        private static final int MODE_MASK = 0xc0000000;
        private static final int MODE_SHIFT = 30;
        public static final int UNSPECIFIED = 0;

        public MeasureSpec()
        {
        }
    }

    public static interface OnApplyWindowInsetsListener
    {

        public abstract WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets);
    }

    public static interface OnAttachStateChangeListener
    {

        public abstract void onViewAttachedToWindow(View view);

        public abstract void onViewDetachedFromWindow(View view);
    }

    public static interface OnCapturedPointerListener
    {

        public abstract boolean onCapturedPointer(View view, MotionEvent motionevent);
    }

    public static interface OnClickListener
    {

        public abstract void onClick(View view);
    }

    public static interface OnContextClickListener
    {

        public abstract boolean onContextClick(View view);
    }

    public static interface OnCreateContextMenuListener
    {

        public abstract void onCreateContextMenu(ContextMenu contextmenu, View view, ContextMenu.ContextMenuInfo contextmenuinfo);
    }

    public static interface OnDragListener
    {

        public abstract boolean onDrag(View view, DragEvent dragevent);
    }

    public static interface OnFocusChangeListener
    {

        public abstract void onFocusChange(View view, boolean flag);
    }

    public static interface OnGenericMotionListener
    {

        public abstract boolean onGenericMotion(View view, MotionEvent motionevent);
    }

    public static interface OnHoverListener
    {

        public abstract boolean onHover(View view, MotionEvent motionevent);
    }

    public static interface OnKeyListener
    {

        public abstract boolean onKey(View view, int i, KeyEvent keyevent);
    }

    public static interface OnLayoutChangeListener
    {

        public abstract void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1);
    }

    public static interface OnLongClickListener
    {

        public abstract boolean onLongClick(View view);
    }

    public static interface OnScrollChangeListener
    {

        public abstract void onScrollChange(View view, int i, int j, int k, int l);
    }

    public static interface OnSystemUiVisibilityChangeListener
    {

        public abstract void onSystemUiVisibilityChange(int i);
    }

    public static interface OnTouchListener
    {

        public abstract boolean onTouch(View view, MotionEvent motionevent);
    }

    private final class PerformClick
        implements Runnable
    {

        public void run()
        {
            performClick();
        }

        final View this$0;

        private PerformClick()
        {
            this$0 = View.this;
            super();
        }

        PerformClick(PerformClick performclick)
        {
            this();
        }
    }

    private static class ScrollabilityCache
        implements Runnable
    {

        public void run()
        {
            long l = AnimationUtils.currentAnimationTimeMillis();
            if(l >= fadeStartTime)
            {
                int i = (int)l;
                Interpolator interpolator = scrollBarInterpolator;
                interpolator.setKeyFrame(0, i, OPAQUE);
                interpolator.setKeyFrame(1, i + scrollBarFadeDuration, TRANSPARENT);
                state = 2;
                host.invalidate(true);
            }
        }

        public void setFadeColor(int i)
        {
            if(i != mLastColor)
            {
                mLastColor = i;
                if(i != 0)
                {
                    shader = new LinearGradient(0.0F, 0.0F, 0.0F, 1.0F, 0xff000000 | i, i & 0xffffff, android.graphics.Shader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setXfermode(null);
                } else
                {
                    shader = new LinearGradient(0.0F, 0.0F, 0.0F, 1.0F, 0xff000000, 0, android.graphics.Shader.TileMode.CLAMP);
                    paint.setShader(shader);
                    paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_OUT));
                }
            }
        }

        public static final int DRAGGING_HORIZONTAL_SCROLL_BAR = 2;
        public static final int DRAGGING_VERTICAL_SCROLL_BAR = 1;
        public static final int FADING = 2;
        public static final int NOT_DRAGGING = 0;
        public static final int OFF = 0;
        public static final int ON = 1;
        private static final float OPAQUE[] = {
            255F
        };
        private static final float TRANSPARENT[] = {
            0.0F
        };
        public boolean fadeScrollBars;
        public long fadeStartTime;
        public int fadingEdgeLength;
        public View host;
        public float interpolatorValues[];
        private int mLastColor;
        public final Rect mScrollBarBounds = new Rect();
        public float mScrollBarDraggingPos;
        public int mScrollBarDraggingState;
        public final Rect mScrollBarTouchBounds = new Rect();
        public final Matrix matrix = new Matrix();
        public final Paint paint = new Paint();
        public ScrollBarDrawable scrollBar;
        public int scrollBarDefaultDelayBeforeFade;
        public int scrollBarFadeDuration;
        public final Interpolator scrollBarInterpolator = new Interpolator(1, 2);
        public int scrollBarMinTouchTarget;
        public int scrollBarSize;
        public Shader shader;
        public int state;


        public ScrollabilityCache(ViewConfiguration viewconfiguration, View view)
        {
            state = 0;
            mScrollBarDraggingState = 0;
            mScrollBarDraggingPos = 0.0F;
            fadingEdgeLength = viewconfiguration.getScaledFadingEdgeLength();
            scrollBarSize = viewconfiguration.getScaledScrollBarSize();
            scrollBarMinTouchTarget = viewconfiguration.getScaledMinScrollbarTouchTarget();
            scrollBarDefaultDelayBeforeFade = ViewConfiguration.getScrollDefaultDelay();
            scrollBarFadeDuration = ViewConfiguration.getScrollBarFadeDuration();
            shader = new LinearGradient(0.0F, 0.0F, 0.0F, 1.0F, 0xff000000, 0, android.graphics.Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_OUT));
            host = view;
        }
    }

    private class SendViewScrolledAccessibilityEvent
        implements Runnable
    {

        public void run()
        {
            sendAccessibilityEvent(4096);
            mIsPending = false;
        }

        public volatile boolean mIsPending;
        final View this$0;

        private SendViewScrolledAccessibilityEvent()
        {
            this$0 = View.this;
            super();
        }

        SendViewScrolledAccessibilityEvent(SendViewScrolledAccessibilityEvent sendviewscrolledaccessibilityevent)
        {
            this();
        }
    }

    static class TintInfo
    {

        boolean mHasTintList;
        boolean mHasTintMode;
        ColorStateList mTintList;
        android.graphics.PorterDuff.Mode mTintMode;

        TintInfo()
        {
        }
    }

    private static class TooltipInfo
    {

        int mAnchorX;
        int mAnchorY;
        Runnable mHideTooltipRunnable;
        Runnable mShowTooltipRunnable;
        boolean mTooltipFromLongClick;
        TooltipPopup mTooltipPopup;
        CharSequence mTooltipText;

        private TooltipInfo()
        {
        }

        TooltipInfo(TooltipInfo tooltipinfo)
        {
            this();
        }
    }

    static class TransformationInfo
    {

        static Matrix _2D_get0(TransformationInfo transformationinfo)
        {
            return transformationinfo.mInverseMatrix;
        }

        static Matrix _2D_get1(TransformationInfo transformationinfo)
        {
            return transformationinfo.mMatrix;
        }

        static Matrix _2D_set0(TransformationInfo transformationinfo, Matrix matrix)
        {
            transformationinfo.mInverseMatrix = matrix;
            return matrix;
        }

        float mAlpha;
        private Matrix mInverseMatrix;
        private final Matrix mMatrix = new Matrix();
        float mTransitionAlpha;

        TransformationInfo()
        {
            mAlpha = 1.0F;
            mTransitionAlpha = 1.0F;
        }
    }

    private final class UnsetPressedState
        implements Runnable
    {

        public void run()
        {
            setPressed(false);
        }

        final View this$0;

        private UnsetPressedState()
        {
            this$0 = View.this;
            super();
        }

        UnsetPressedState(UnsetPressedState unsetpressedstate)
        {
            this();
        }
    }

    private static class VisibilityChangeForAutofillHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            mAfm.notifyViewVisibilityChanged(mView, mView.isShown());
        }

        private final AutofillManager mAfm;
        private final View mView;

        private VisibilityChangeForAutofillHandler(AutofillManager autofillmanager, View view)
        {
            mAfm = autofillmanager;
            mView = view;
        }

        VisibilityChangeForAutofillHandler(AutofillManager autofillmanager, View view, VisibilityChangeForAutofillHandler visibilitychangeforautofillhandler)
        {
            this(autofillmanager, view);
        }
    }


    static int _2D_get0(View view)
    {
        return view.mLabelForId;
    }

    static boolean _2D_get1()
    {
        return sUseBrokenMakeMeasureSpec;
    }

    static boolean _2D_set0(View view, boolean flag)
    {
        view.mHasPerformedLongPress = flag;
        return flag;
    }

    static void _2D_wrap0(View view, int i, float f, float f1)
    {
        view.checkForLongClick(i, f, f1);
    }

    static void _2D_wrap1(View view, boolean flag, float f, float f1)
    {
        view.setPressed(flag, f, f1);
    }

    View()
    {
        mCurrentAnimation = null;
        mRecreateDisplayList = false;
        mID = -1;
        mAutofillViewId = -1;
        mAccessibilityViewId = -1;
        mAccessibilityCursorPosition = -1;
        mTag = null;
        mTransientStateCount = 0;
        mClipBounds = null;
        mPaddingLeft = 0;
        mPaddingRight = 0;
        mLabelForId = -1;
        mAccessibilityTraversalBeforeId = -1;
        mAccessibilityTraversalAfterId = -1;
        mLeftPaddingDefined = false;
        mRightPaddingDefined = false;
        mOldWidthMeasureSpec = 0x80000000;
        mOldHeightMeasureSpec = 0x80000000;
        mLongClickX = (0.0F / 0.0F);
        mLongClickY = (0.0F / 0.0F);
        mDrawableState = null;
        mOutlineProvider = ViewOutlineProvider.BACKGROUND;
        mNextFocusLeftId = -1;
        mNextFocusRightId = -1;
        mNextFocusUpId = -1;
        mNextFocusDownId = -1;
        mNextFocusForwardId = -1;
        mNextClusterForwardId = -1;
        mDefaultFocusHighlightEnabled = true;
        mPendingCheckForTap = null;
        mTouchDelegate = null;
        mDrawingCacheBackgroundColor = 0;
        mAnimator = null;
        mLayerType = 0;
        InputEventConsistencyVerifier inputeventconsistencyverifier;
        if(InputEventConsistencyVerifier.isInstrumentationEnabled())
            inputeventconsistencyverifier = new InputEventConsistencyVerifier(this, 0);
        else
            inputeventconsistencyverifier = null;
        mInputEventConsistencyVerifier = inputeventconsistencyverifier;
        mFirst = true;
        mIsWebView = false;
        mResources = null;
        mRenderNode = RenderNode.create(getClass().getName(), this);
    }

    public View(Context context)
    {
        Object obj = null;
        boolean flag = false;
        super();
        mCurrentAnimation = null;
        mRecreateDisplayList = false;
        mID = -1;
        mAutofillViewId = -1;
        mAccessibilityViewId = -1;
        mAccessibilityCursorPosition = -1;
        mTag = null;
        mTransientStateCount = 0;
        mClipBounds = null;
        mPaddingLeft = 0;
        mPaddingRight = 0;
        mLabelForId = -1;
        mAccessibilityTraversalBeforeId = -1;
        mAccessibilityTraversalAfterId = -1;
        mLeftPaddingDefined = false;
        mRightPaddingDefined = false;
        mOldWidthMeasureSpec = 0x80000000;
        mOldHeightMeasureSpec = 0x80000000;
        mLongClickX = (0.0F / 0.0F);
        mLongClickY = (0.0F / 0.0F);
        mDrawableState = null;
        mOutlineProvider = ViewOutlineProvider.BACKGROUND;
        mNextFocusLeftId = -1;
        mNextFocusRightId = -1;
        mNextFocusUpId = -1;
        mNextFocusDownId = -1;
        mNextFocusForwardId = -1;
        mNextClusterForwardId = -1;
        mDefaultFocusHighlightEnabled = true;
        mPendingCheckForTap = null;
        mTouchDelegate = null;
        mDrawingCacheBackgroundColor = 0;
        mAnimator = null;
        mLayerType = 0;
        Object obj1;
        if(InputEventConsistencyVerifier.isInstrumentationEnabled())
            obj1 = new InputEventConsistencyVerifier(this, 0);
        else
            obj1 = null;
        mInputEventConsistencyVerifier = ((InputEventConsistencyVerifier) (obj1));
        mFirst = true;
        mIsWebView = false;
        mContext = context;
        obj1 = obj;
        if(context != null)
            obj1 = context.getResources();
        mResources = ((Resources) (obj1));
        mViewFlags = 0x18000010;
        mPrivateFlags2 = 0x22408;
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setOverScrollMode(1);
        mUserPaddingStart = 0x80000000;
        mUserPaddingEnd = 0x80000000;
        mRenderNode = RenderNode.create(getClass().getName(), this);
        if(!sCompatibilityDone && context != null)
        {
            int i = context.getApplicationInfo().targetSdkVersion;
            boolean flag1;
            if(i <= 17)
                flag1 = true;
            else
                flag1 = false;
            sUseBrokenMakeMeasureSpec = flag1;
            if(i < 19)
                flag1 = true;
            else
                flag1 = false;
            sIgnoreMeasureCache = flag1;
            if(i < 23)
                flag1 = true;
            else
                flag1 = false;
            Canvas.sCompatibilityRestore = flag1;
            if(i < 26)
                flag1 = true;
            else
                flag1 = false;
            Canvas.sCompatibilitySetBitmap = flag1;
            if(i < 23)
                flag1 = true;
            else
                flag1 = false;
            sUseZeroUnspecifiedMeasureSpec = flag1;
            if(i <= 23)
                flag1 = true;
            else
                flag1 = false;
            sAlwaysRemeasureExactly = flag1;
            if(i <= 23)
                flag1 = true;
            else
                flag1 = false;
            sLayoutParamsAlwaysChanged = flag1;
            if(i <= 23)
                flag1 = true;
            else
                flag1 = false;
            sTextureViewIgnoresDrawableSetters = flag1;
            if(i >= 24)
                flag1 = true;
            else
                flag1 = false;
            sPreserveMarginParamsInLayoutParamConversion = flag1;
            if(i < 24)
                flag1 = true;
            else
                flag1 = false;
            sCascadedDragDrop = flag1;
            if(i < 26)
                flag1 = true;
            else
                flag1 = false;
            sHasFocusableExcludeAutoFocusable = flag1;
            flag1 = flag;
            if(i < 26)
                flag1 = true;
            sAutoFocusableOffUIThreadWontNotifyParents = flag1;
            sUseDefaultFocusHighlight = context.getResources().getBoolean(0x11200c0);
            sCompatibilityDone = true;
        }
        mIsWebView = InterceptorProxy.checkAndInitWebView(this);
    }

    public View(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public View(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public View(Context context, AttributeSet attributeset, int i, int j)
    {
        TypedArray typedarray;
        AttributeSet attributeset1;
        int k;
        byte byte0;
        int l;
        byte byte1;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        boolean flag1;
        int j2;
        int k2;
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        boolean flag2;
        int l2;
        int i3;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        this(context);
        typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.View, i, j);
        if(mDebugViewAttributes)
            saveAttributeData(attributeset, typedarray);
        attributeset1 = null;
        k = -1;
        byte0 = -1;
        l = -1;
        byte1 = -1;
        i1 = 0x80000000;
        j1 = 0x80000000;
        k1 = -1;
        l1 = -1;
        i2 = -1;
        flag1 = false;
        j2 = 0;
        k2 = 0;
        f = 0.0F;
        f1 = 0.0F;
        f2 = 0.0F;
        f3 = 0.0F;
        f4 = 0.0F;
        f5 = 0.0F;
        f6 = 0.0F;
        f7 = 1.0F;
        f8 = 1.0F;
        flag2 = false;
        l2 = 0;
        i3 = mOverScrollMode;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        flag6 = false;
        flag7 = false;
        flag8 = false;
        j3 = context.getApplicationInfo().targetSdkVersion;
        k3 = 16;
        l3 = 16;
        i4 = typedarray.getIndexCount();
        j4 = 0;
_L89:
        int k4;
        int l4;
        float f9;
        int i5;
        boolean flag9;
        boolean flag10;
        boolean flag11;
        int j5;
        boolean flag12;
        int k5;
        int l5;
        int i6;
        int j6;
        int k6;
        boolean flag13;
        float f10;
        float f11;
        float f12;
        int l6;
        boolean flag14;
        int i7;
        boolean flag15;
        float f13;
        float f14;
        int j7;
        boolean flag16;
        float f15;
        float f16;
        float f17;
        int l7;
        int i8;
        if(j4 >= i4)
            break MISSING_BLOCK_LABEL_18713;
        k4 = typedarray.getIndex(j4);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        k4;
        JVM INSTR tableswitch 8 100: default 692
    //                   8 9778
    //                   9 4606
    //                   10 4751
    //                   11 2651
    //                   12 2790
    //                   13 958
    //                   14 1096
    //                   15 1533
    //                   16 1677
    //                   17 1816
    //                   18 1960
    //                   19 5173
    //                   20 5461
    //                   21 7154
    //                   22 4895
    //                   23 9066
    //                   24 9350
    //                   25 822
    //                   26 10913
    //                   27 11058
    //                   28 11203
    //                   29 11348
    //                   30 5746
    //                   31 6028
    //                   32 6590
    //                   33 7623
    //                   34 6872
    //                   35 14510
    //                   36 11783
    //                   37 11928
    //                   38 14798
    //                   39 8490
    //                   40 10349
    //                   41 10067
    //                   42 8772
    //                   43 12073
    //                   44 7911
    //                   45 822
    //                   46 822
    //                   47 822
    //                   48 12382
    //                   49 10631
    //                   50 2929
    //                   51 3074
    //                   52 3219
    //                   53 3364
    //                   54 3502
    //                   55 4330
    //                   56 4468
    //                   57 3916
    //                   58 4054
    //                   59 4192
    //                   60 12521
    //                   61 11493
    //                   62 12666
    //                   63 9487
    //                   64 13286
    //                   65 12812
    //                   66 13118
    //                   67 7442
    //                   68 2099
    //                   69 2375
    //                   70 8345
    //                   71 13431
    //                   72 3640
    //                   73 13576
    //                   74 13720
    //                   75 3778
    //                   76 13865
    //                   77 14014
    //                   78 14187
    //                   79 15380
    //                   80 15087
    //                   81 14365
    //                   82 8055
    //                   83 8200
    //                   84 15986
    //                   85 6310
    //                   86 16281
    //                   87 16728
    //                   88 17013
    //                   89 1245
    //                   90 1394
    //                   91 17157
    //                   92 11638
    //                   93 17442
    //                   94 17727
    //                   95 18143
    //                   96 18428
    //                   97 822
    //                   98 822
    //                   99 822
    //                   100 15668;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L19 _L19 _L19 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72 _L73 _L74 _L75 _L76 _L77 _L78 _L79 _L80 _L81 _L82 _L83 _L84 _L85 _L86 _L87 _L19 _L19 _L19 _L88
_L87:
        break MISSING_BLOCK_LABEL_18428;
_L19:
        break; /* Loop/switch isn't completed */
_L1:
        i8 = k2;
        l7 = j2;
        j = k3;
        i = l3;
        f17 = f2;
        f16 = f1;
        f15 = f;
        flag16 = flag2;
        j7 = byte0;
        f14 = f8;
        f13 = f7;
        flag15 = flag5;
        i7 = i1;
        flag14 = flag1;
        l6 = l2;
        f12 = f6;
        f11 = f5;
        f10 = f4;
        flag13 = flag8;
        k6 = l;
        j6 = i2;
        i6 = l1;
        l5 = k1;
        k5 = i3;
        flag12 = flag7;
        j5 = k;
        flag11 = flag3;
        flag10 = flag4;
        flag9 = flag6;
        i5 = j1;
        f9 = f3;
        l4 = byte1;
        attributeset = attributeset1;
_L90:
        j4++;
        attributeset1 = attributeset;
        byte1 = l4;
        f3 = f9;
        j1 = i5;
        flag6 = flag9;
        flag4 = flag10;
        flag3 = flag11;
        k = j5;
        flag7 = flag12;
        i3 = k5;
        k1 = l5;
        l1 = i6;
        i2 = j6;
        l = k6;
        flag8 = flag13;
        f4 = f10;
        f5 = f11;
        f6 = f12;
        l2 = l6;
        flag1 = flag14;
        i1 = i7;
        flag5 = flag15;
        f7 = f13;
        f8 = f14;
        byte0 = j7;
        flag2 = flag16;
        f = f15;
        f1 = f16;
        f2 = f17;
        l3 = i;
        k3 = j;
        j2 = l7;
        k2 = i8;
          goto _L89
_L7:
        attributeset = typedarray.getDrawable(k4);
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L8:
        l5 = typedarray.getDimensionPixelSize(k4, -1);
        mUserPaddingLeftInitial = l5;
        mUserPaddingRightInitial = l5;
        flag12 = true;
        flag13 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        k5 = i3;
        i6 = l1;
        j6 = i2;
        k6 = l;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L80:
        i6 = typedarray.getDimensionPixelSize(k4, -1);
        mUserPaddingLeftInitial = i6;
        mUserPaddingRightInitial = i6;
        flag12 = true;
        flag13 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        k5 = i3;
        l5 = k1;
        j6 = i2;
        k6 = l;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L81:
        j6 = typedarray.getDimensionPixelSize(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L9:
        j5 = typedarray.getDimensionPixelSize(k4, -1);
        mUserPaddingLeftInitial = j5;
        flag12 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L10:
        j7 = typedarray.getDimensionPixelSize(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L11:
        k6 = typedarray.getDimensionPixelSize(k4, -1);
        mUserPaddingRightInitial = k6;
        flag13 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L12:
        l4 = typedarray.getDimensionPixelSize(k4, -1);
        attributeset = attributeset1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L59:
        i7 = typedarray.getDimensionPixelSize(k4, 0x80000000);
        if(i7 != 0x80000000)
        {
            flag15 = true;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        } else
        {
            flag15 = false;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L60:
        i5 = typedarray.getDimensionPixelSize(k4, 0x80000000);
        if(i5 != 0x80000000)
        {
            flag9 = true;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        } else
        {
            flag9 = false;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L5:
        l7 = typedarray.getDimensionPixelOffset(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        i8 = k2;
          goto _L90
_L6:
        i8 = typedarray.getDimensionPixelOffset(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
          goto _L90
_L41:
        setAlpha(typedarray.getFloat(k4, 1.0F));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L42:
        setPivotX(typedarray.getDimension(k4, 0.0F));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L43:
        setPivotY(typedarray.getDimension(k4, 0.0F));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L44:
        f15 = typedarray.getDimension(k4, 0.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L45:
        f16 = typedarray.getDimension(k4, 0.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        f15 = f;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L63:
        f17 = typedarray.getDimension(k4, 0.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        f15 = f;
        f16 = f1;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L66:
        f9 = typedarray.getDimension(k4, 0.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L48:
        f10 = typedarray.getFloat(k4, 0.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L49:
        f11 = typedarray.getFloat(k4, 0.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L50:
        f12 = typedarray.getFloat(k4, 0.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L46:
        f13 = typedarray.getFloat(k4, 1.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f14 = f8;
        j7 = byte0;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L47:
        f14 = typedarray.getFloat(k4, 1.0F);
        flag16 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        j7 = byte0;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L3:
        mID = typedarray.getResourceId(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L4:
        mTag = typedarray.getText(k4);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L16:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 | 2;
            i = l3 | 2;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L13:
        k3 = k3 & 0xffffffee | getFocusableAttribute(typedarray);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if((k3 & 0x10) == 0)
        {
            i = l3 | 0x11;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L14:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 & 0xffffffef | 0x40001;
            i = l3 | 0x40011;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L24:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 | 0x4000;
            i = l3 | 0x4000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L25:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 | 0x200000;
            i = l3 | 0x200000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L76:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 | 0x800000;
            i = l3 | 0x800000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L26:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!typedarray.getBoolean(k4, true))
        {
            j = k3 | 0x10000;
            i = l3 | 0x10000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L28:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 | 0x400000;
            i = l3 | 0x400000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L15:
        k4 = typedarray.getInt(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(k4 != 0)
        {
            j = k3 | VISIBILITY_FLAGS[k4];
            i = l3 | 0xc;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L58:
        mPrivateFlags2 = mPrivateFlags2 & 0xffffffc3;
        i = typedarray.getInt(k4, -1);
        if(i != -1)
            i = LAYOUT_DIRECTION_FLAGS[i];
        else
            i = 2;
        mPrivateFlags2 = mPrivateFlags2 | i << 2;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L27:
        k4 = typedarray.getInt(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(k4 != 0)
        {
            j = k3 | DRAWING_CACHE_QUALITY_FLAGS[k4];
            i = l3 | 0x180000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L38:
        setContentDescription(typedarray.getString(k4));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L73:
        setAccessibilityTraversalBefore(typedarray.getResourceId(k4, -1));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L74:
        setAccessibilityTraversalAfter(typedarray.getResourceId(k4, -1));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L61:
        setLabelFor(typedarray.getResourceId(k4, -1));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L33:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!typedarray.getBoolean(k4, true))
        {
            j = k3 & 0xf7ffffff;
            i = l3 | 0x8000000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L36:
        mHapticEnabledExplicitly = typedarray.getBoolean(k4, false);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!typedarray.getBoolean(k4, true))
        {
            j = k3 & 0xefffffff;
            i = l3 | 0x10000000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L17:
        k4 = typedarray.getInt(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(k4 != 0)
        {
            j = k3 | k4;
            i = l3 | 0x300;
            flag11 = true;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L18:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(j3 >= 14) goto _L90; else goto _L54
_L54:
        k4 = typedarray.getInt(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(k4 != 0)
        {
            j = k3 | k4;
            i = l3 | 0x3000;
            initializeFadingEdgeInternal(typedarray);
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L2:
        l2 = typedarray.getInt(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(l2 != 0)
        {
            j = k3 | 0x3000000 & l2;
            i = l3 | 0x3000000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L35:
        flag1 = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            setScrollContainer(true);
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L34:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 | 0x4000000;
            i = l3 | 0x4000000;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L40:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.getBoolean(k4, false))
        {
            j = k3 | 0x400;
            i = l3 | 0x400;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L20:
        mNextFocusLeftId = typedarray.getResourceId(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L21:
        mNextFocusRightId = typedarray.getResourceId(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L22:
        mNextFocusUpId = typedarray.getResourceId(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L23:
        mNextFocusDownId = typedarray.getResourceId(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L52:
        mNextFocusForwardId = typedarray.getResourceId(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L83:
        mNextClusterForwardId = typedarray.getResourceId(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L30:
        mMinWidth = typedarray.getDimensionPixelSize(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L31:
        mMinHeight = typedarray.getDimensionPixelSize(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L37:
        if(context.isRestricted())
            throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
        String s = typedarray.getString(k4);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(s != null)
        {
            setOnClickListener(new DeclaredOnClickListener(this, s));
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L39:
        k5 = typedarray.getInt(k4, 1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L51:
        mVerticalScrollbarPosition = typedarray.getInt(k4, 0);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L53:
        setLayerType(typedarray.getInt(k4, 0), null);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L56:
        mPrivateFlags2 = mPrivateFlags2 & 0xfffffe3f;
        k4 = typedarray.getInt(k4, -1);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(k4 != -1)
        {
            mPrivateFlags2 = mPrivateFlags2 | PFLAG2_TEXT_DIRECTION_FLAGS[k4];
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L57:
        mPrivateFlags2 = mPrivateFlags2 & 0xffff1fff;
        i = typedarray.getInt(k4, 1);
        mPrivateFlags2 = mPrivateFlags2 | PFLAG2_TEXT_ALIGNMENT_FLAGS[i];
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L55:
        setImportantForAccessibility(typedarray.getInt(k4, 0));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L62:
        setAccessibilityLiveRegion(typedarray.getInt(k4, 0));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L64:
        setTransitionName(typedarray.getString(k4));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L65:
        setNestedScrollingEnabled(typedarray.getBoolean(k4, false));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L67:
        setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, typedarray.getResourceId(k4, 0)));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L68:
        if(mBackgroundTint == null)
            mBackgroundTint = new TintInfo();
        mBackgroundTint.mTintList = typedarray.getColorStateList(77);
        mBackgroundTint.mHasTintList = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L69:
        if(mBackgroundTint == null)
            mBackgroundTint = new TintInfo();
        mBackgroundTint.mTintMode = Drawable.parseTintMode(typedarray.getInt(78, -1), null);
        mBackgroundTint.mHasTintMode = true;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L72:
        setOutlineProviderFromAttribute(typedarray.getInt(81, 0));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L29:
        if(j3 >= 23) goto _L92; else goto _L91
_L91:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!(this instanceof FrameLayout)) goto _L90; else goto _L92
_L92:
        setForeground(typedarray.getDrawable(k4));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L32:
        if(j3 >= 23) goto _L94; else goto _L93
_L93:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!(this instanceof FrameLayout)) goto _L90; else goto _L94
_L94:
        setForegroundGravity(typedarray.getInt(k4, 0));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L71:
        if(j3 >= 23) goto _L96; else goto _L95
_L95:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!(this instanceof FrameLayout)) goto _L90; else goto _L96
_L96:
        setForegroundTintMode(Drawable.parseTintMode(typedarray.getInt(k4, -1), null));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L70:
        if(j3 >= 23) goto _L98; else goto _L97
_L97:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!(this instanceof FrameLayout)) goto _L90; else goto _L98
_L98:
        setForegroundTintList(typedarray.getColorStateList(k4));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L88:
        if(j3 >= 23) goto _L100; else goto _L99
_L99:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(!(this instanceof FrameLayout)) goto _L90; else goto _L100
_L100:
        if(mForegroundInfo == null)
            mForegroundInfo = new ForegroundInfo(null);
        ForegroundInfo._2D_set3(mForegroundInfo, typedarray.getBoolean(k4, ForegroundInfo._2D_get3(mForegroundInfo)));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L75:
        k4 = typedarray.getInt(k4, 0) << 8 & 0x3f00;
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(k4 != 0)
        {
            mPrivateFlags3 = mPrivateFlags3 | k4;
            flag10 = true;
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L77:
        i = typedarray.getResourceId(k4, 0);
        if(i != 0)
        {
            setPointerIcon(PointerIcon.load(context.getResources(), i));
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        } else
        {
            k4 = typedarray.getInt(k4, 1);
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
            if(k4 != 1)
            {
                setPointerIcon(PointerIcon.getSystemIcon(context, k4));
                attributeset = attributeset1;
                l4 = byte1;
                f9 = f3;
                i5 = j1;
                flag9 = flag6;
                flag10 = flag4;
                flag11 = flag3;
                j5 = k;
                flag12 = flag7;
                k5 = i3;
                l5 = k1;
                i6 = l1;
                j6 = i2;
                k6 = l;
                flag13 = flag8;
                f10 = f4;
                f11 = f5;
                f12 = f6;
                l6 = l2;
                flag14 = flag1;
                i7 = i1;
                flag15 = flag5;
                f13 = f7;
                f14 = f8;
                j7 = byte0;
                flag16 = flag2;
                f15 = f;
                f16 = f1;
                f17 = f2;
                i = l3;
                j = k3;
                l7 = j2;
                i8 = k2;
            }
        }
          goto _L90
_L78:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.peekValue(k4) != null)
        {
            forceHasOverlappingRendering(typedarray.getBoolean(k4, true));
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L79:
        setTooltipText(typedarray.getText(k4));
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L82:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.peekValue(k4) != null)
        {
            setKeyboardNavigationCluster(typedarray.getBoolean(k4, true));
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L84:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.peekValue(k4) != null)
        {
            setFocusedByDefault(typedarray.getBoolean(k4, true));
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
_L85:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.peekValue(k4) == null) goto _L90; else goto _L101
_L101:
        Object obj;
        attributeset = null;
        obj = null;
        if(typedarray.getType(k4) != 1) goto _L103; else goto _L102
_L102:
        Object obj1;
        i = typedarray.getResourceId(k4, 0);
        CharSequence acharsequence[];
        try
        {
            acharsequence = typedarray.getTextArray(k4);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            obj = getResources().getString(i);
            continue; /* Loop/switch isn't completed */
        }
        attributeset = acharsequence;
_L105:
        obj1 = attributeset;
        if(attributeset != null)
            break MISSING_BLOCK_LABEL_17964;
        if(obj == null)
            throw new IllegalArgumentException("Could not resolve autofillHints");
        break; /* Loop/switch isn't completed */
_L103:
        obj = typedarray.getString(k4);
        if(true) goto _L105; else goto _L104
_L104:
        obj1 = ((String) (obj)).split(",");
        attributeset = new String[obj1.length];
        j = obj1.length;
        for(i = 0; i < j; i++)
            attributeset[i] = obj1[i].toString().trim();

        setAutofillHints(attributeset);
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
          goto _L90
_L86:
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.peekValue(k4) != null)
        {
            setImportantForAutofill(typedarray.getInt(k4, 0));
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
        attributeset = attributeset1;
        l4 = byte1;
        f9 = f3;
        i5 = j1;
        flag9 = flag6;
        flag10 = flag4;
        flag11 = flag3;
        j5 = k;
        flag12 = flag7;
        k5 = i3;
        l5 = k1;
        i6 = l1;
        j6 = i2;
        k6 = l;
        flag13 = flag8;
        f10 = f4;
        f11 = f5;
        f12 = f6;
        l6 = l2;
        flag14 = flag1;
        i7 = i1;
        flag15 = flag5;
        f13 = f7;
        f14 = f8;
        j7 = byte0;
        flag16 = flag2;
        f15 = f;
        f16 = f1;
        f17 = f2;
        i = l3;
        j = k3;
        l7 = j2;
        i8 = k2;
        if(typedarray.peekValue(k4) != null)
        {
            setDefaultFocusHighlightEnabled(typedarray.getBoolean(k4, true));
            attributeset = attributeset1;
            l4 = byte1;
            f9 = f3;
            i5 = j1;
            flag9 = flag6;
            flag10 = flag4;
            flag11 = flag3;
            j5 = k;
            flag12 = flag7;
            k5 = i3;
            l5 = k1;
            i6 = l1;
            j6 = i2;
            k6 = l;
            flag13 = flag8;
            f10 = f4;
            f11 = f5;
            f12 = f6;
            l6 = l2;
            flag14 = flag1;
            i7 = i1;
            flag15 = flag5;
            f13 = f7;
            f14 = f8;
            j7 = byte0;
            flag16 = flag2;
            f15 = f;
            f16 = f1;
            f17 = f2;
            i = l3;
            j = k3;
            l7 = j2;
            i8 = k2;
        }
          goto _L90
        setOverScrollMode(i3);
        mUserPaddingStart = i1;
        mUserPaddingEnd = j1;
        if(attributeset1 != null)
            setBackground(attributeset1);
        mLeftPaddingDefined = flag7;
        mRightPaddingDefined = flag8;
        int k7;
        if(k1 >= 0)
        {
            i2 = k1;
            j = k1;
            k7 = k1;
            i = k1;
            mUserPaddingLeftInitial = k1;
            mUserPaddingRightInitial = k1;
            k1 = i2;
        } else
        {
            if(l1 >= 0)
            {
                k = l1;
                l = l1;
                mUserPaddingLeftInitial = l1;
                mUserPaddingRightInitial = l1;
            }
            i = byte1;
            k1 = k;
            k7 = l;
            j = byte0;
            if(i2 >= 0)
            {
                j = i2;
                i = i2;
                k1 = k;
                k7 = l;
            }
        }
        if(isRtlCompatibilityMode())
        {
            i2 = k1;
            if(!mLeftPaddingDefined)
            {
                i2 = k1;
                if(flag5)
                    i2 = i1;
            }
            if(i2 < 0)
                i2 = mUserPaddingLeftInitial;
            mUserPaddingLeftInitial = i2;
            k1 = k7;
            if(!mRightPaddingDefined)
            {
                k1 = k7;
                if(flag6)
                    k1 = j1;
            }
            if(k1 >= 0)
                k7 = k1;
            else
                k7 = mUserPaddingRightInitial;
            mUserPaddingRightInitial = k7;
        } else
        {
            boolean flag;
            if(!flag5)
                flag = flag6;
            else
                flag = true;
            if(mLeftPaddingDefined && flag ^ true)
                mUserPaddingLeftInitial = k1;
            if(mRightPaddingDefined && flag ^ true)
                mUserPaddingRightInitial = k7;
        }
        k7 = mUserPaddingLeftInitial;
        if(j < 0)
            j = mPaddingTop;
        k1 = mUserPaddingRightInitial;
        if(i < 0)
            i = mPaddingBottom;
        internalSetPadding(k7, j, k1, i);
        if(l3 != 0)
            setFlags(k3, l3);
        if(flag3)
            initializeScrollbarsInternal(typedarray);
        if(flag4)
            initializeScrollIndicatorsInternal();
        typedarray.recycle();
        if(l2 != 0)
            recomputePadding();
        if(j2 != 0 || k2 != 0)
            scrollTo(j2, k2);
        if(flag2)
        {
            setTranslationX(f);
            setTranslationY(f1);
            setTranslationZ(f2);
            setElevation(f3);
            setRotation(f4);
            setRotationX(f5);
            setRotationY(f6);
            setScaleX(f7);
            setScaleY(f8);
        }
        if(!flag1 && (k3 & 0x200) != 0)
            setScrollContainer(true);
        computeOpaqueFlags();
        return;
    }

    private void applyBackgroundTint()
    {
        if(mBackground != null && mBackgroundTint != null)
        {
            TintInfo tintinfo = mBackgroundTint;
            if(tintinfo.mHasTintList || tintinfo.mHasTintMode)
            {
                mBackground = mBackground.mutate();
                if(tintinfo.mHasTintList)
                    mBackground.setTintList(tintinfo.mTintList);
                if(tintinfo.mHasTintMode)
                    mBackground.setTintMode(tintinfo.mTintMode);
                if(mBackground.isStateful())
                    mBackground.setState(getDrawableState());
            }
        }
    }

    private void applyForegroundTint()
    {
        if(mForegroundInfo != null && ForegroundInfo._2D_get1(mForegroundInfo) != null && ForegroundInfo._2D_get6(mForegroundInfo) != null)
        {
            TintInfo tintinfo = ForegroundInfo._2D_get6(mForegroundInfo);
            if(tintinfo.mHasTintList || tintinfo.mHasTintMode)
            {
                ForegroundInfo._2D_set1(mForegroundInfo, ForegroundInfo._2D_get1(mForegroundInfo).mutate());
                if(tintinfo.mHasTintList)
                    ForegroundInfo._2D_get1(mForegroundInfo).setTintList(tintinfo.mTintList);
                if(tintinfo.mHasTintMode)
                    ForegroundInfo._2D_get1(mForegroundInfo).setTintMode(tintinfo.mTintMode);
                if(ForegroundInfo._2D_get1(mForegroundInfo).isStateful())
                    ForegroundInfo._2D_get1(mForegroundInfo).setState(getDrawableState());
            }
        }
    }

    private boolean applyLegacyAnimation(ViewGroup viewgroup, long l, Animation animation, boolean flag)
    {
        int i;
        Transformation transformation;
        boolean flag1;
        i = viewgroup.mGroupFlags;
        if(!animation.isInitialized())
        {
            animation.initialize(mRight - mLeft, mBottom - mTop, viewgroup.getWidth(), viewgroup.getHeight());
            animation.initializeInvalidateRegion(0, 0, mRight - mLeft, mBottom - mTop);
            if(mAttachInfo != null)
                animation.setListenerHandler(mAttachInfo.mHandler);
            onAnimationStart();
        }
        transformation = viewgroup.getChildTransformation();
        flag1 = animation.getTransformation(l, transformation, 1.0F);
        if(flag && mAttachInfo.mApplicationScale != 1.0F)
        {
            if(viewgroup.mInvalidationTransformation == null)
                viewgroup.mInvalidationTransformation = new Transformation();
            transformation = viewgroup.mInvalidationTransformation;
            animation.getTransformation(l, transformation, 1.0F);
        }
        if(!flag1) goto _L2; else goto _L1
_L1:
        if(animation.willChangeBounds()) goto _L4; else goto _L3
_L3:
        if((i & 0x90) != 128) goto _L6; else goto _L5
_L5:
        viewgroup.mGroupFlags = viewgroup.mGroupFlags | 4;
_L2:
        return flag1;
_L6:
        if((i & 4) == 0)
        {
            viewgroup.mPrivateFlags = viewgroup.mPrivateFlags | 0x40;
            viewgroup.invalidate(mLeft, mTop, mRight, mBottom);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(viewgroup.mInvalidateRegion == null)
            viewgroup.mInvalidateRegion = new RectF();
        RectF rectf = viewgroup.mInvalidateRegion;
        animation.getInvalidateRegion(0, 0, mRight - mLeft, mBottom - mTop, rectf, transformation);
        viewgroup.mPrivateFlags = viewgroup.mPrivateFlags | 0x40;
        int j = mLeft + (int)rectf.left;
        int k = mTop + (int)rectf.top;
        viewgroup.invalidate(j, k, (int)(rectf.width() + 0.5F) + j, (int)(rectf.height() + 0.5F) + k);
        if(true) goto _L2; else goto _L7
_L7:
    }

    private void buildDrawingCacheImpl(boolean flag)
    {
        int j;
        int k;
        boolean flag3;
        Object obj;
        Object obj1;
        mCachingFailed = false;
        int i = mRight - mLeft;
        j = mBottom - mTop;
        AttachInfo attachinfo = mAttachInfo;
        boolean flag1;
        int l;
        boolean flag2;
        long l1;
        if(attachinfo != null)
            flag1 = attachinfo.mScalingRequired;
        else
            flag1 = false;
        k = j;
        l = i;
        if(flag)
        {
            k = j;
            l = i;
            if(flag1)
            {
                l = (int)((float)i * attachinfo.mApplicationScale + 0.5F);
                k = (int)((float)j * attachinfo.mApplicationScale + 0.5F);
            }
        }
        i = mDrawingCacheBackgroundColor;
        if(i == 0)
            flag2 = isOpaque();
        else
            flag2 = true;
        if(attachinfo != null)
            flag3 = attachinfo.mUse32BitDrawingCache;
        else
            flag3 = false;
        if(flag2 && flag3 ^ true)
            j = 2;
        else
            j = 4;
        l1 = j * (l * k);
        for(long l2 = ViewConfiguration.get(mContext).getScaledMaximumDrawingCacheSize(); l <= 0 || k <= 0 || l1 > l2;)
        {
            if(l > 0 && k > 0)
                Log.w("View", (new StringBuilder()).append(getClass().getSimpleName()).append(" not displayed because it is").append(" too large to fit into a software layer (or drawing cache), needs ").append(l1).append(" bytes, only ").append(l2).append(" available").toString());
            destroyDrawingCache();
            mCachingFailed = true;
            return;
        }

        j = 1;
        Canvas canvas;
        float f;
        if(flag)
            obj = mDrawingCache;
        else
            obj = mUnscaledDrawingCache;
        if(obj != null && ((Bitmap) (obj)).getWidth() == l) goto _L2; else goto _L1
_L1:
        if(!flag2)
        {
            j = mViewFlags;
            obj1 = android.graphics.Bitmap.Config.ARGB_8888;
        } else
        if(flag3)
            obj1 = android.graphics.Bitmap.Config.ARGB_8888;
        else
            obj1 = android.graphics.Bitmap.Config.RGB_565;
        if(obj != null)
            ((Bitmap) (obj)).recycle();
        try
        {
            obj1 = Bitmap.createBitmap(mResources.getDisplayMetrics(), l, k, ((android.graphics.Bitmap.Config) (obj1)));
            ((Bitmap) (obj1)).setDensity(getResources().getDisplayMetrics().densityDpi);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            if(flag)
                mDrawingCache = null;
            else
                mUnscaledDrawingCache = null;
            mCachingFailed = true;
            return;
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_691;
        mDrawingCache = ((Bitmap) (obj1));
_L4:
        if(!flag2 || !flag3)
            break MISSING_BLOCK_LABEL_391;
        ((Bitmap) (obj1)).setHasAlpha(false);
        if(i != 0)
            j = 1;
        else
            j = 0;
        if(attachinfo != null)
        {
            canvas = attachinfo.mCanvas;
            obj = canvas;
            if(canvas == null)
                obj = new Canvas();
            ((Canvas) (obj)).setBitmap(((Bitmap) (obj1)));
            attachinfo.mCanvas = null;
        } else
        {
            obj = new Canvas(((Bitmap) (obj1)));
        }
        if(j != 0)
            ((Bitmap) (obj1)).eraseColor(i);
        computeScroll();
        j = ((Canvas) (obj)).save();
        if(flag && flag1)
        {
            f = attachinfo.mApplicationScale;
            ((Canvas) (obj)).scale(f, f);
        }
        ((Canvas) (obj)).translate(-mScrollX, -mScrollY);
        mPrivateFlags = mPrivateFlags | 0x20;
        if(mAttachInfo == null || mAttachInfo.mHardwareAccelerated ^ true || mLayerType != 0)
            mPrivateFlags = mPrivateFlags | 0x8000;
        if((mPrivateFlags & 0x80) == 128)
        {
            mPrivateFlags = mPrivateFlags & 0xff9fffff;
            dispatchDraw(((Canvas) (obj)));
            drawAutofilledHighlight(((Canvas) (obj)));
            if(mOverlay != null && mOverlay.isEmpty() ^ true)
                mOverlay.getOverlayView().draw(((Canvas) (obj)));
        } else
        {
            draw(((Canvas) (obj)));
        }
        ((Canvas) (obj)).restoreToCount(j);
        ((Canvas) (obj)).setBitmap(null);
        if(attachinfo != null)
            attachinfo.mCanvas = ((Canvas) (obj));
        return;
_L2:
        obj1 = obj;
        if(((Bitmap) (obj)).getHeight() == k) goto _L3; else goto _L1
_L3:
        break MISSING_BLOCK_LABEL_397;
        mUnscaledDrawingCache = ((Bitmap) (obj1));
          goto _L4
    }

    private void checkForLongClick(int i, float f, float f1)
    {
        if((mViewFlags & 0x200000) == 0x200000 || (mViewFlags & 0x40000000) == 0x40000000)
        {
            mHasPerformedLongPress = false;
            if(mPendingCheckForLongPress == null)
                mPendingCheckForLongPress = new CheckForLongPress(null);
            mPendingCheckForLongPress.setAnchor(f, f1);
            mPendingCheckForLongPress.rememberWindowAttachCount();
            mPendingCheckForLongPress.rememberPressedState();
            postDelayed(mPendingCheckForLongPress, ViewConfiguration.getLongPressTimeout() - i);
        }
    }

    private void cleanupDraw()
    {
        resetDisplayList();
        if(mAttachInfo != null)
            mAttachInfo.mViewRootImpl.cancelInvalidate(this);
    }

    public static int combineMeasuredStates(int i, int j)
    {
        return i | j;
    }

    private final void debugDrawFocus(Canvas canvas)
    {
        if(isFocused())
        {
            int i = dipsToPixels(8);
            int j = mScrollX;
            int k = (mRight + j) - mLeft;
            int l = mScrollY;
            int i1 = (mBottom + l) - mTop;
            Paint paint = getDebugPaint();
            paint.setColor(DEBUG_CORNERS_COLOR);
            paint.setStyle(android.graphics.Paint.Style.FILL);
            canvas.drawRect(j, l, j + i, l + i, paint);
            canvas.drawRect(k - i, l, k, l + i, paint);
            canvas.drawRect(j, i1 - i, j + i, i1, paint);
            canvas.drawRect(k - i, i1 - i, k, i1, paint);
            paint.setStyle(android.graphics.Paint.Style.STROKE);
            canvas.drawLine(j, l, k, i1, paint);
            canvas.drawLine(j, i1, k, l, paint);
        }
    }

    protected static String debugIndent(int i)
    {
        StringBuilder stringbuilder = new StringBuilder((i * 2 + 3) * 2);
        for(int j = 0; j < i * 2 + 3; j++)
            stringbuilder.append(' ').append(' ');

        return stringbuilder.toString();
    }

    private boolean dispatchGenericMotionEventInternal(MotionEvent motionevent)
    {
        int i;
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null && ListenerInfo._2D_get2(listenerinfo) != null && (mViewFlags & 0x20) == 0 && ListenerInfo._2D_get2(listenerinfo).onGenericMotion(this, motionevent))
            return true;
        if(onGenericMotionEvent(motionevent))
            return true;
        i = motionevent.getActionButton();
        motionevent.getActionMasked();
        JVM INSTR tableswitch 11 12: default 84
    //                   11 102
    //                   12 181;
           goto _L1 _L2 _L3
_L1:
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(motionevent, 0);
        return false;
_L2:
        if(isContextClickable() && mInContextButtonPress ^ true && mHasPerformedLongPress ^ true && (i == 32 || i == 2) && performContextClick(motionevent.getX(), motionevent.getY()))
        {
            mInContextButtonPress = true;
            setPressed(true, motionevent.getX(), motionevent.getY());
            removeTapCallback();
            removeLongPressCallback();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mInContextButtonPress && (i == 32 || i == 2))
        {
            mInContextButtonPress = false;
            mIgnoreNextUpEvent = true;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    private void dispatchProvideStructureForAssistOrAutofill(ViewStructure viewstructure, boolean flag, int i)
    {
        if(flag)
        {
            viewstructure.setAutofillId(getAutofillId());
            onProvideAutofillStructure(viewstructure, i);
            onProvideAutofillVirtualStructure(viewstructure, i);
        } else
        if(!isAssistBlocked())
        {
            onProvideStructure(viewstructure);
            onProvideVirtualStructure(viewstructure);
        } else
        {
            viewstructure.setClassName(getAccessibilityClassName().toString());
            viewstructure.setAssistBlocked(true);
        }
    }

    private void drawAutofilledHighlight(Canvas canvas)
    {
        if(isAutofilled())
        {
            Drawable drawable = getAutofilledDrawable();
            if(drawable != null)
            {
                drawable.setBounds(0, 0, getWidth(), getHeight());
                drawable.draw(canvas);
            }
        }
    }

    private void drawBackground(Canvas canvas)
    {
        Drawable drawable = mBackground;
        if(drawable == null)
            return;
        setBackgroundBounds();
        if(canvas.isHardwareAccelerated() && mAttachInfo != null && mAttachInfo.mThreadedRenderer != null)
        {
            mBackgroundRenderNode = getDrawableRenderNode(drawable, mBackgroundRenderNode);
            RenderNode rendernode = mBackgroundRenderNode;
            if(rendernode != null && rendernode.isValid())
            {
                setBackgroundRenderNodeProperties(rendernode);
                ((DisplayListCanvas)canvas).drawRenderNode(rendernode);
                return;
            }
        }
        int i = mScrollX;
        int j = mScrollY;
        if((i | j) == 0)
        {
            drawable.draw(canvas);
        } else
        {
            canvas.translate(i, j);
            drawable.draw(canvas);
            canvas.translate(-i, -j);
        }
    }

    private void drawDefaultFocusHighlight(Canvas canvas)
    {
        if(mDefaultFocusHighlight != null)
        {
            if(mDefaultFocusHighlightSizeChanged)
            {
                mDefaultFocusHighlightSizeChanged = false;
                int i = mScrollX;
                int j = mRight;
                int k = mLeft;
                int l = mScrollY;
                int i1 = mBottom;
                int j1 = mTop;
                mDefaultFocusHighlight.setBounds(i, l, (j + i) - k, (i1 + l) - j1);
            }
            mDefaultFocusHighlight.draw(canvas);
        }
    }

    private static void dumpFlag(HashMap hashmap, String s, int i)
    {
        String s1 = String.format("%32s", new Object[] {
            Integer.toBinaryString(i)
        }).replace('0', ' ');
        i = s.indexOf('_');
        StringBuilder stringbuilder = new StringBuilder();
        String s2;
        if(i > 0)
            s2 = s.substring(0, i);
        else
            s2 = s;
        hashmap.put(stringbuilder.append(s2).append(s1).append(s).toString(), (new StringBuilder()).append(s1).append(" ").append(s).toString());
    }

    private static void dumpFlags()
    {
        Object obj;
        obj = Maps.newHashMap();
        Field afield[];
        int i;
        int j;
        Field field;
        int k;
        int ai[];
        StringBuilder stringbuilder;
        try
        {
            afield = android/view/View.getDeclaredFields();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        i = 0;
        j = afield.length;
_L2:
        if(i >= j)
            break MISSING_BLOCK_LABEL_188;
        field = afield[i];
        k = field.getModifiers();
        if(Modifier.isStatic(k) && Modifier.isFinal(k))
        {
            if(!field.getType().equals(Integer.TYPE))
                break; /* Loop/switch isn't completed */
            k = field.getInt(null);
            dumpFlag(((HashMap) (obj)), field.getName(), k);
        }
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(!field.getType().equals([I)) goto _L4; else goto _L3
_L3:
        ai = (int[])field.get(null);
        k = 0;
_L6:
        if(k >= ai.length) goto _L4; else goto _L5
_L5:
        stringbuilder = JVM INSTR new #1733 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        dumpFlag(((HashMap) (obj)), stringbuilder.append(field.getName()).append("[").append(k).append("]").toString(), ai[k]);
        k++;
          goto _L6
          goto _L4
        Object obj1 = Lists.newArrayList();
        ((ArrayList) (obj1)).addAll(((HashMap) (obj)).keySet());
        Collections.sort(((List) (obj1)));
        for(obj1 = ((Iterable) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); Log.d("View", (String)((HashMap) (obj)).get((String)((Iterator) (obj1)).next())));
        return;
    }

    private View findAccessibilityFocusHost(boolean flag)
    {
        if(isAccessibilityFocusedViewOrHost())
            return this;
        if(flag)
        {
            Object obj = getViewRootImpl();
            if(obj != null)
            {
                obj = ((ViewRootImpl) (obj)).getAccessibilityFocusedHost();
                if(obj != null && ViewRootImpl.isViewDescendantOf(((View) (obj)), this))
                    return ((View) (obj));
            }
        }
        return null;
    }

    private FrameMetricsObserver findFrameMetricsObserver(Window.OnFrameMetricsAvailableListener onframemetricsavailablelistener)
    {
        for(int i = 0; i < mFrameMetricsObservers.size(); i++)
        {
            FrameMetricsObserver framemetricsobserver = (FrameMetricsObserver)mFrameMetricsObservers.get(i);
            if(framemetricsobserver.mListener == onframemetricsavailablelistener)
                return framemetricsobserver;
        }

        return null;
    }

    private View findLabelForView(View view, int i)
    {
        if(mMatchLabelForPredicate == null)
            mMatchLabelForPredicate = new MatchLabelForPredicate(null);
        MatchLabelForPredicate._2D_set0(mMatchLabelForPredicate, i);
        return findViewByPredicateInsideOut(view, mMatchLabelForPredicate);
    }

    private View findViewInsideOutShouldExist(View view, int i)
    {
        if(mMatchIdPredicate == null)
            mMatchIdPredicate = new MatchIdPredicate(null);
        mMatchIdPredicate.mId = i;
        view = view.findViewByPredicateInsideOut(this, mMatchIdPredicate);
        if(view == null)
            Log.w("View", (new StringBuilder()).append("couldn't find view with id ").append(i).toString());
        return view;
    }

    private boolean fitSystemWindowsInt(Rect rect)
    {
        if((mViewFlags & 2) == 2)
        {
            mUserPaddingStart = 0x80000000;
            mUserPaddingEnd = 0x80000000;
            Rect rect1 = (Rect)sThreadLocal.get();
            Rect rect2 = rect1;
            if(rect1 == null)
            {
                rect2 = new Rect();
                sThreadLocal.set(rect2);
            }
            boolean flag = computeFitSystemWindows(rect, rect2);
            mUserPaddingLeftInitial = rect2.left;
            mUserPaddingRightInitial = rect2.right;
            internalSetPadding(rect2.left, rect2.top, rect2.right, rect2.bottom);
            return flag;
        } else
        {
            return false;
        }
    }

    public static int generateViewId()
    {
        int i;
        int k;
        do
        {
            i = sNextGeneratedId.get();
            int j = i + 1;
            k = j;
            if(j > 0xffffff)
                k = 1;
        } while(!sNextGeneratedId.compareAndSet(i, k));
        return i;
    }

    private static SparseArray getAttributeMap()
    {
        if(mAttributeMap == null)
            mAttributeMap = new SparseArray();
        return mAttributeMap;
    }

    private AutofillManager getAutofillManager()
    {
        return (AutofillManager)mContext.getSystemService(android/view/autofill/AutofillManager);
    }

    private Drawable getAutofilledDrawable()
    {
        if(mAttachInfo == null)
            return null;
        if(mAttachInfo.mAutofilledDrawable == null)
        {
            Context context = getRootView().getContext();
            TypedArray typedarray = context.getTheme().obtainStyledAttributes(AUTOFILL_HIGHLIGHT_ATTR);
            int i = typedarray.getResourceId(0, 0);
            mAttachInfo.mAutofilledDrawable = context.getDrawable(i);
            typedarray.recycle();
        }
        return mAttachInfo.mAutofilledDrawable;
    }

    static Paint getDebugPaint()
    {
        if(sDebugPaint == null)
        {
            sDebugPaint = new Paint();
            sDebugPaint.setAntiAlias(false);
        }
        return sDebugPaint;
    }

    private Drawable getDefaultFocusHighlightDrawable()
    {
        if(mDefaultFocusHighlightCache == null && mContext != null)
        {
            TypedArray typedarray = mContext.obtainStyledAttributes(new int[] {
                0x101030e
            });
            mDefaultFocusHighlightCache = typedarray.getDrawable(0);
            typedarray.recycle();
        }
        return mDefaultFocusHighlightCache;
    }

    public static int getDefaultSize(int i, int j)
    {
        int k;
        int l;
        k = i;
        l = MeasureSpec.getMode(j);
        j = MeasureSpec.getSize(j);
        l;
        JVM INSTR lookupswitch 3: default 48
    //                   -2147483648: 55
    //                   0: 52
    //                   1073741824: 55;
           goto _L1 _L2 _L3 _L2
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        i = k;
_L5:
        return i;
_L2:
        i = j;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private RenderNode getDrawableRenderNode(Drawable drawable, RenderNode rendernode)
    {
        RenderNode rendernode1;
        Rect rect;
        rendernode1 = rendernode;
        if(rendernode == null)
            rendernode1 = RenderNode.create(drawable.getClass().getName(), this);
        rect = drawable.getBounds();
        rendernode = rendernode1.start(rect.width(), rect.height());
        rendernode.translate(-rect.left, -rect.top);
        drawable.draw(rendernode);
        rendernode1.end(rendernode);
        rendernode1.setLeftTopRightBottom(rect.left, rect.top, rect.right, rect.bottom);
        rendernode1.setProjectBackwards(drawable.isProjected());
        rendernode1.setProjectionReceiver(true);
        rendernode1.setClipToBounds(false);
        return rendernode1;
        drawable;
        rendernode1.end(rendernode);
        throw drawable;
    }

    private float getFinalAlpha()
    {
        if(mTransformationInfo != null)
            return mTransformationInfo.mAlpha * mTransformationInfo.mTransitionAlpha;
        else
            return 1.0F;
    }

    private int getFocusableAttribute(TypedArray typedarray)
    {
        int i = 0;
        TypedValue typedvalue = new TypedValue();
        if(typedarray.getValue(19, typedvalue))
        {
            if(typedvalue.type == 18)
            {
                if(typedvalue.data != 0)
                    i = 1;
                return i;
            } else
            {
                return typedvalue.data;
            }
        } else
        {
            return 16;
        }
    }

    private void getHorizontalScrollBarBounds(Rect rect, Rect rect1)
    {
        if(rect == null)
            rect = rect1;
        if(rect == null)
            return;
        int i;
        int j;
        int i1;
        int j1;
        int k1;
        int l1;
        if((mViewFlags & 0x2000000) == 0)
            i = -1;
        else
            i = 0;
        if(isVerticalScrollBarEnabled())
            j = isVerticalScrollBarHidden() ^ true;
        else
            j = 0;
        i1 = getHorizontalScrollbarHeight();
        if(j != 0)
            j = getVerticalScrollbarWidth();
        else
            j = 0;
        j1 = mRight;
        k1 = mLeft;
        l1 = mBottom - mTop;
        rect.top = (mScrollY + l1) - i1 - (mUserPaddingBottom & i);
        rect.left = mScrollX + (mPaddingLeft & i);
        rect.right = (mScrollX + (j1 - k1)) - (mUserPaddingRight & i) - j;
        rect.bottom = rect.top + i1;
        if(rect1 == null)
            return;
        if(rect1 != rect)
            rect1.set(rect);
        i = mScrollCache.scrollBarMinTouchTarget;
        if(rect1.height() < i)
        {
            int k = (i - rect1.height()) / 2;
            rect1.bottom = Math.min(rect1.bottom + k, mScrollY + l1);
            rect1.top = rect1.bottom - i;
        }
        if(rect1.width() < i)
        {
            int l = (i - rect1.width()) / 2;
            rect1.left = rect1.left - l;
            rect1.right = rect1.left + i;
        }
    }

    private View getProjectionReceiver()
    {
        for(ViewParent viewparent = getParent(); viewparent != null && (viewparent instanceof View); viewparent = viewparent.getParent())
        {
            View view = (View)viewparent;
            if(view.isProjectionReceiver())
                return view;
        }

        return null;
    }

    private void getRoundVerticalScrollBarBounds(Rect rect)
    {
        int i = mRight;
        int j = mLeft;
        int k = mBottom;
        int l = mTop;
        rect.left = mScrollX;
        rect.top = mScrollY;
        rect.right = rect.left + (i - j);
        rect.bottom = mScrollY + (k - l);
    }

    private HandlerActionQueue getRunQueue()
    {
        if(mRunQueue == null)
            mRunQueue = new HandlerActionQueue();
        return mRunQueue;
    }

    private ScrollabilityCache getScrollCache()
    {
        initScrollCache();
        return mScrollCache;
    }

    private void getStraightVerticalScrollBarBounds(Rect rect, Rect rect1)
    {
        byte byte0;
        int l;
        int i1;
        if(rect == null)
            rect = rect1;
        if(rect == null)
            return;
        int j;
        int j1;
        int k1;
        if((mViewFlags & 0x2000000) == 0)
            byte0 = -1;
        else
            byte0 = 0;
        j = getVerticalScrollbarWidth();
        l = mVerticalScrollbarPosition;
        i1 = l;
        if(l == 0)
            if(isLayoutRtl())
                i1 = 1;
            else
                i1 = 2;
        l = mRight - mLeft;
        j1 = mBottom;
        k1 = mTop;
        i1;
        JVM INSTR tableswitch 1 2: default 104
    //                   1 188
    //                   2 104;
           goto _L1 _L2 _L1
_L1:
        rect.left = (mScrollX + l) - j - (mUserPaddingRight & byte0);
_L4:
        rect.top = mScrollY + (mPaddingTop & byte0);
        rect.right = rect.left + j;
        rect.bottom = (mScrollY + (j1 - k1)) - (mUserPaddingBottom & byte0);
        if(rect1 == null)
            return;
        break; /* Loop/switch isn't completed */
_L2:
        rect.left = mScrollX + (mUserPaddingLeft & byte0);
        if(true) goto _L4; else goto _L3
_L3:
        if(rect1 != rect)
            rect1.set(rect);
        int i = mScrollCache.scrollBarMinTouchTarget;
        if(rect1.width() < i)
        {
            int k = (i - rect1.width()) / 2;
            if(i1 == 2)
            {
                rect1.right = Math.min(rect1.right + k, mScrollX + l);
                rect1.left = rect1.right - i;
            } else
            {
                rect1.left = Math.max(rect1.left + k, mScrollX);
                rect1.right = rect1.left + i;
            }
        }
        if(rect1.height() < i)
        {
            i1 = (i - rect1.height()) / 2;
            rect1.top = rect1.top - i1;
            rect1.bottom = rect1.top + i;
        }
        return;
    }

    private void getVerticalScrollBarBounds(Rect rect, Rect rect1)
    {
        if(mRoundScrollbarRenderer == null)
        {
            getStraightVerticalScrollBarBounds(rect, rect1);
        } else
        {
            if(rect == null)
                rect = rect1;
            getRoundVerticalScrollBarBounds(rect);
        }
    }

    private void handleTooltipUp()
    {
        if(mTooltipInfo == null || mTooltipInfo.mTooltipPopup == null)
        {
            return;
        } else
        {
            removeCallbacks(mTooltipInfo.mHideTooltipRunnable);
            postDelayed(mTooltipInfo.mHideTooltipRunnable, ViewConfiguration.getLongPressTooltipHideTimeout());
            return;
        }
    }

    private boolean hasAncestorThatBlocksDescendantFocus()
    {
        boolean flag = isFocusableInTouchMode();
        for(Object obj = mParent; obj instanceof ViewGroup; obj = ((ViewGroup) (obj)).getParent())
        {
            obj = (ViewGroup)obj;
            if(((ViewGroup) (obj)).getDescendantFocusability() == 0x60000 || !flag && ((ViewGroup) (obj)).shouldBlockFocusForTouchscreen())
                return true;
        }

        return false;
    }

    private boolean hasListenersForAccessibility()
    {
        boolean flag;
        ListenerInfo listenerinfo;
        boolean flag1;
        flag = true;
        listenerinfo = getListenerInfo();
        flag1 = flag;
        if(mTouchDelegate != null) goto _L2; else goto _L1
_L1:
        if(ListenerInfo._2D_get4(listenerinfo) == null) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(ListenerInfo._2D_get7(listenerinfo) == null)
        {
            flag1 = flag;
            if(ListenerInfo._2D_get2(listenerinfo) == null)
            {
                flag1 = flag;
                if(ListenerInfo._2D_get3(listenerinfo) == null)
                {
                    flag1 = flag;
                    if(ListenerInfo._2D_get1(listenerinfo) == null)
                        flag1 = false;
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean hasRtlSupport()
    {
        return mContext.getApplicationInfo().hasRtlSupport();
    }

    public static View inflate(Context context, int i, ViewGroup viewgroup)
    {
        return LayoutInflater.from(context).inflate(i, viewgroup);
    }

    private void initScrollCache()
    {
        if(mScrollCache == null)
            mScrollCache = new ScrollabilityCache(ViewConfiguration.get(mContext), this);
    }

    private boolean initialAwakenScrollBars()
    {
        boolean flag;
        if(mScrollCache != null)
            flag = awakenScrollBars(mScrollCache.scrollBarDefaultDelayBeforeFade * 4, true);
        else
            flag = false;
        return flag;
    }

    private void initializeScrollIndicatorsInternal()
    {
        if(mScrollIndicatorDrawable == null)
            mScrollIndicatorDrawable = mContext.getDrawable(0x1080719);
    }

    private boolean isAutofillable()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(getAutofillType() != 0)
        {
            flag1 = flag;
            if(isImportantForAutofill())
            {
                flag1 = flag;
                if(getAutofillViewId() > 0x3fffffff)
                    flag1 = true;
            }
        }
        return flag1;
    }

    private boolean isHoverable()
    {
        boolean flag;
        int i;
        boolean flag1;
        flag = true;
        i = mViewFlags;
        if((i & 0x20) == 32)
            return false;
        flag1 = flag;
        if((i & 0x4000) == 16384) goto _L2; else goto _L1
_L1:
        if((i & 0x200000) != 0x200000) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if((i & 0x800000) != 0x800000)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isLayoutModeOptical(Object obj)
    {
        boolean flag;
        if(obj instanceof ViewGroup)
            flag = ((ViewGroup)obj).isLayoutModeOptical();
        else
            flag = false;
        return flag;
    }

    private boolean isOnHorizontalScrollbarThumb(float f, float f1)
    {
        if(mScrollCache == null)
            return false;
        if(isHorizontalScrollBarEnabled())
        {
            f += getScrollX();
            f1 += getScrollY();
            Rect rect = mScrollCache.mScrollBarBounds;
            Rect rect1 = mScrollCache.mScrollBarTouchBounds;
            getHorizontalScrollBarBounds(rect, rect1);
            int i = computeHorizontalScrollRange();
            int j = computeHorizontalScrollOffset();
            int k = computeHorizontalScrollExtent();
            int l = ScrollBarUtils.getThumbLength(rect.width(), rect.height(), k, i);
            k = ScrollBarUtils.getThumbOffset(rect.width(), l, k, i, j);
            j = rect.left + k;
            k = Math.max(mScrollCache.scrollBarMinTouchTarget - l, 0) / 2;
            if(f >= (float)(j - k) && f <= (float)(j + l + k) && f1 >= (float)rect1.top && f1 <= (float)rect1.bottom)
                return true;
        }
        return false;
    }

    private boolean isOnVerticalScrollbarThumb(float f, float f1)
    {
        if(mScrollCache == null)
            return false;
        if(isVerticalScrollBarEnabled() && isVerticalScrollBarHidden() ^ true)
        {
            f += getScrollX();
            f1 += getScrollY();
            Rect rect = mScrollCache.mScrollBarBounds;
            Rect rect1 = mScrollCache.mScrollBarTouchBounds;
            getVerticalScrollBarBounds(rect, rect1);
            int i = computeVerticalScrollRange();
            int j = computeVerticalScrollOffset();
            int k = computeVerticalScrollExtent();
            int l = ScrollBarUtils.getThumbLength(rect.height(), rect.width(), k, i);
            j = ScrollBarUtils.getThumbOffset(rect.height(), l, k, i, j);
            j = rect.top + j;
            k = Math.max(mScrollCache.scrollBarMinTouchTarget - l, 0) / 2;
            if(f >= (float)rect1.left && f <= (float)rect1.right && f1 >= (float)(j - k) && f1 <= (float)(j + l + k))
                return true;
        }
        return false;
    }

    private boolean isProjectionReceiver()
    {
        boolean flag;
        if(mBackground != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isRtlCompatibilityMode()
    {
        boolean flag;
        if(getContext().getApplicationInfo().targetSdkVersion >= 17)
            flag = hasRtlSupport() ^ true;
        else
            flag = true;
        return flag;
    }

    private static boolean isViewIdGenerated(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((0xff000000 & i) == 0)
        {
            flag1 = flag;
            if((0xffffff & i) != 0)
                flag1 = true;
        }
        return flag1;
    }

    static boolean lambda$_2D_android_view_View_420576(int i, View view)
    {
        boolean flag;
        if(view.mNextClusterForwardId == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected static int[] mergeDrawableStates(int ai[], int ai1[])
    {
        int i;
        for(i = ai.length - 1; i >= 0 && ai[i] == 0; i--);
        System.arraycopy(ai1, 0, ai, i + 1, ai1.length);
        return ai;
    }

    private boolean needRtlPropertiesResolution()
    {
        boolean flag;
        if((mPrivateFlags2 & 0x60010220) != 0x60010220)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void notifyEnterOrExitForAutoFillIfNeeded(boolean flag)
    {
        if(!isAutofillable() || !isAttachedToWindow()) goto _L2; else goto _L1
_L1:
        AutofillManager autofillmanager = getAutofillManager();
        if(autofillmanager == null) goto _L2; else goto _L3
_L3:
        if(!flag || !hasWindowFocus() || !isFocused()) goto _L5; else goto _L4
_L4:
        if(isLaidOut()) goto _L7; else goto _L6
_L6:
        mPrivateFlags3 = mPrivateFlags3 | 0x8000000;
_L2:
        return;
_L7:
        if(isVisibleToUser())
            autofillmanager.notifyViewEntered(this);
        continue; /* Loop/switch isn't completed */
_L5:
        if(!hasWindowFocus() || isFocused() ^ true)
            autofillmanager.notifyViewExited(this);
        if(true) goto _L2; else goto _L8
_L8:
    }

    private boolean notifyWebView(View view, boolean flag)
    {
        if(view != null)
        {
            view = view.getAttachedActivityInstance();
            if(view != null)
                view = view.getInterceptor();
            else
                view = null;
            if(view != null)
            {
                view.notifyWebView(this, flag);
                return true;
            }
        }
        return false;
    }

    private static int numViewsForAccessibility(View view)
    {
        if(view != null)
        {
            if(view.includeForAccessibility())
                return 1;
            if(view instanceof ViewGroup)
                return ((ViewGroup)view).getNumChildrenForAccessibility();
        }
        return 0;
    }

    private void onDrawScrollIndicators(Canvas canvas)
    {
        if((mPrivateFlags3 & 0x3f00) == 0)
            return;
        Drawable drawable = mScrollIndicatorDrawable;
        if(drawable == null)
            return;
        int i = drawable.getIntrinsicHeight();
        int j = drawable.getIntrinsicWidth();
        Rect rect = mAttachInfo.mTmpInvalRect;
        getScrollIndicatorBounds(rect);
        if((mPrivateFlags3 & 0x100) != 0 && canScrollVertically(-1))
        {
            drawable.setBounds(rect.left, rect.top, rect.right, rect.top + i);
            drawable.draw(canvas);
        }
        if((mPrivateFlags3 & 0x200) != 0 && canScrollVertically(1))
        {
            drawable.setBounds(rect.left, rect.bottom - i, rect.right, rect.bottom);
            drawable.draw(canvas);
        }
        char c;
        if(getLayoutDirection() == 1)
        {
            i = 8192;
            c = '\u1000';
        } else
        {
            i = 4096;
            c = '\u2000';
        }
        if((mPrivateFlags3 & (i | 0x400)) != 0 && canScrollHorizontally(-1))
        {
            drawable.setBounds(rect.left, rect.top, rect.left + j, rect.bottom);
            drawable.draw(canvas);
        }
        if((mPrivateFlags3 & (c | 0x800)) != 0 && canScrollHorizontally(1))
        {
            drawable.setBounds(rect.right - j, rect.top, rect.right, rect.bottom);
            drawable.draw(canvas);
        }
    }

    private void onProvideStructureForAssistOrAutofill(ViewStructure viewstructure, boolean flag, int i)
    {
        int j = mID;
        int l;
        if(j != -1 && isViewIdGenerated(j) ^ true)
        {
            Object obj;
            String s;
            Object obj1;
            int k;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            try
            {
                obj = getResources();
                s = ((Resources) (obj)).getResourceEntryName(j);
                obj1 = ((Resources) (obj)).getResourceTypeName(j);
                obj = ((Resources) (obj)).getResourcePackageName(j);
            }
            catch(android.content.res.Resources.NotFoundException notfoundexception)
            {
                obj = null;
                notfoundexception = null;
                s = null;
            }
            viewstructure.setId(j, ((String) (obj)), ((String) (obj1)), s);
        } else
        {
            viewstructure.setId(j, null, null, null);
        }
        if(flag)
        {
            j = getAutofillType();
            if(j != 0)
            {
                viewstructure.setAutofillType(j);
                viewstructure.setAutofillHints(getAutofillHints());
                viewstructure.setAutofillValue(getAutofillValue());
            }
        }
        k = 0;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        l = k;
        j = ((flag2) ? 1 : 0);
        if(flag)
        {
            l = k;
            j = ((flag2) ? 1 : 0);
            if((i & 1) == 0)
            {
                obj1 = null;
                obj = getParent();
                k = ((flag1) ? 1 : 0);
                i = ((flag3) ? 1 : 0);
                if(obj instanceof View)
                {
                    obj1 = (View)obj;
                    i = ((flag3) ? 1 : 0);
                    k = ((flag1) ? 1 : 0);
                }
                do
                {
                    l = k;
                    j = i;
                    if(obj1 == null)
                        break;
                    l = k;
                    j = i;
                    if(!(((View) (obj1)).isImportantForAutofill() ^ true))
                        break;
                    k += ((View) (obj1)).mLeft;
                    i += ((View) (obj1)).mTop;
                    obj1 = ((View) (obj1)).getParent();
                    l = k;
                    j = i;
                    if(!(obj1 instanceof View))
                        break;
                    obj1 = (View)obj1;
                } while(true);
            }
        }
        viewstructure.setDimens(l + mLeft, j + mTop, mScrollX, mScrollY, mRight - mLeft, mBottom - mTop);
        if(!flag)
        {
            if(!hasIdentityMatrix())
                viewstructure.setTransformation(getMatrix());
            viewstructure.setElevation(getZ());
        }
        viewstructure.setVisibility(getVisibility());
        viewstructure.setEnabled(isEnabled());
        if(isClickable())
            viewstructure.setClickable(true);
        if(isFocusable())
            viewstructure.setFocusable(true);
        if(isFocused())
            viewstructure.setFocused(true);
        if(isAccessibilityFocused())
            viewstructure.setAccessibilityFocused(true);
        if(isSelected())
            viewstructure.setSelected(true);
        if(isActivated())
            viewstructure.setActivated(true);
        if(isLongClickable())
            viewstructure.setLongClickable(true);
        if(this instanceof Checkable)
        {
            viewstructure.setCheckable(true);
            if(((Checkable)this).isChecked())
                viewstructure.setChecked(true);
        }
        if(isOpaque())
            viewstructure.setOpaque(true);
        if(isContextClickable())
            viewstructure.setContextClickable(true);
        viewstructure.setClassName(getAccessibilityClassName().toString());
        viewstructure.setContentDescription(getContentDescription());
    }

    private boolean performLongClickInternal(float f, float f1)
    {
        sendAccessibilityEvent(2);
        boolean flag = false;
        ListenerInfo listenerinfo = mListenerInfo;
        boolean flag1 = flag;
        if(listenerinfo != null)
        {
            flag1 = flag;
            if(listenerinfo.mOnLongClickListener != null)
                flag1 = listenerinfo.mOnLongClickListener.onLongClick(this);
        }
        flag = flag1;
        if(!flag1)
        {
            boolean flag2;
            boolean flag3;
            if(!Float.isNaN(f))
                flag3 = Float.isNaN(f1) ^ true;
            else
                flag3 = false;
            if(flag3)
                flag = showContextMenu(f, f1);
            else
                flag = showContextMenu();
        }
        flag2 = flag;
        if((mViewFlags & 0x40000000) == 0x40000000)
        {
            flag2 = flag;
            if(!flag)
                flag2 = showLongClickTooltip((int)f, (int)f1);
        }
        if(flag2)
            performHapticFeedback(0);
        return flag2;
    }

    private void populateAccessibilityNodeInfoDrawingOrderInParent(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        if((mPrivateFlags & 0x10) == 0)
        {
            accessibilitynodeinfo.setDrawingOrder(0);
            return;
        }
        int i = 1;
        View view = this;
        ViewParent viewparent = getParentForAccessibility();
        do
        {
            ViewParent viewparent1;
label0:
            {
                int j = i;
                if(view != viewparent)
                {
                    viewparent1 = view.getParent();
                    if(viewparent1 instanceof ViewGroup)
                        break label0;
                    j = 0;
                }
                accessibilitynodeinfo.setDrawingOrder(j);
                return;
            }
            ViewGroup viewgroup = (ViewGroup)viewparent1;
            int i1 = viewgroup.getChildCount();
            int j1 = i;
            if(i1 > 1)
            {
                ArrayList arraylist = viewgroup.buildOrderedChildList();
                if(arraylist != null)
                {
                    int k1 = arraylist.indexOf(view);
                    int k = 0;
                    do
                    {
                        j1 = i;
                        if(k >= k1)
                            break;
                        i += numViewsForAccessibility((View)arraylist.get(k));
                        k++;
                    } while(true);
                } else
                {
                    int l = viewgroup.indexOfChild(view);
                    boolean flag = viewgroup.isChildrenDrawingOrderEnabled();
                    if(l >= 0 && flag)
                        l = viewgroup.getChildDrawingOrder(i1, l);
                    int i2;
                    if(flag)
                        i2 = i1;
                    else
                        i2 = l;
                    j1 = i;
                    if(l != 0)
                    {
                        int l1 = 0;
                        do
                        {
                            j1 = i;
                            if(l1 >= i2)
                                break;
                            int j2;
                            if(flag)
                                j2 = viewgroup.getChildDrawingOrder(i1, l1);
                            else
                                j2 = l1;
                            j1 = i;
                            if(j2 < l)
                                j1 = i + numViewsForAccessibility(viewgroup.getChildAt(l1));
                            l1++;
                            i = j1;
                        } while(true);
                    }
                }
            }
            view = (View)viewparent1;
            i = j1;
        } while(true);
    }

    private void populateVirtualStructure(ViewStructure viewstructure, AccessibilityNodeProvider accessibilitynodeprovider, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        viewstructure.setId(AccessibilityNodeInfo.getVirtualDescendantId(accessibilitynodeinfo.getSourceNodeId()), null, null, null);
        Object obj = viewstructure.getTempRect();
        accessibilitynodeinfo.getBoundsInParent(((Rect) (obj)));
        viewstructure.setDimens(((Rect) (obj)).left, ((Rect) (obj)).top, 0, 0, ((Rect) (obj)).width(), ((Rect) (obj)).height());
        viewstructure.setVisibility(0);
        viewstructure.setEnabled(accessibilitynodeinfo.isEnabled());
        if(accessibilitynodeinfo.isClickable())
            viewstructure.setClickable(true);
        if(accessibilitynodeinfo.isFocusable())
            viewstructure.setFocusable(true);
        if(accessibilitynodeinfo.isFocused())
            viewstructure.setFocused(true);
        if(accessibilitynodeinfo.isAccessibilityFocused())
            viewstructure.setAccessibilityFocused(true);
        if(accessibilitynodeinfo.isSelected())
            viewstructure.setSelected(true);
        if(accessibilitynodeinfo.isLongClickable())
            viewstructure.setLongClickable(true);
        if(accessibilitynodeinfo.isCheckable())
        {
            viewstructure.setCheckable(true);
            if(accessibilitynodeinfo.isChecked())
                viewstructure.setChecked(true);
        }
        if(accessibilitynodeinfo.isContextClickable())
            viewstructure.setContextClickable(true);
        obj = accessibilitynodeinfo.getClassName();
        int i;
        if(obj != null)
            obj = ((CharSequence) (obj)).toString();
        else
            obj = null;
        viewstructure.setClassName(((String) (obj)));
        viewstructure.setContentDescription(accessibilitynodeinfo.getContentDescription());
        if(accessibilitynodeinfo.getText() != null || accessibilitynodeinfo.getError() != null)
            viewstructure.setText(accessibilitynodeinfo.getText(), accessibilitynodeinfo.getTextSelectionStart(), accessibilitynodeinfo.getTextSelectionEnd());
        i = accessibilitynodeinfo.getChildCount();
        if(i > 0)
        {
            viewstructure.setChildCount(i);
            for(int j = 0; j < i; j++)
            {
                obj = accessibilitynodeprovider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(accessibilitynodeinfo.getChildId(j)));
                populateVirtualStructure(viewstructure.newChild(j), accessibilitynodeprovider, ((AccessibilityNodeInfo) (obj)));
                ((AccessibilityNodeInfo) (obj)).recycle();
            }

        }
    }

    private void postSendViewScrolledAccessibilityEventCallback()
    {
        if(mSendViewScrolledAccessibilityEvent == null)
            mSendViewScrolledAccessibilityEvent = new SendViewScrolledAccessibilityEvent(null);
        if(!mSendViewScrolledAccessibilityEvent.mIsPending)
        {
            mSendViewScrolledAccessibilityEvent.mIsPending = true;
            postDelayed(mSendViewScrolledAccessibilityEvent, ViewConfiguration.getSendRecurringAccessibilityEventsInterval());
        }
    }

    private static String printFlags(int i)
    {
        String s;
        int j;
        s = "";
        j = 0;
        if((i & 1) == 1)
        {
            s = (new StringBuilder()).append("").append("TAKES_FOCUS").toString();
            j = 1;
        }
        i & 0xc;
        JVM INSTR lookupswitch 2: default 68
    //                   4: 70
    //                   8: 121;
           goto _L1 _L2 _L3
_L1:
        return s;
_L2:
        String s1 = s;
        if(j > 0)
            s1 = (new StringBuilder()).append(s).append(" ").toString();
        s = (new StringBuilder()).append(s1).append("INVISIBLE").toString();
        continue; /* Loop/switch isn't completed */
_L3:
        String s2 = s;
        if(j > 0)
            s2 = (new StringBuilder()).append(s).append(" ").toString();
        s = (new StringBuilder()).append(s2).append("GONE").toString();
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static String printPrivateFlags(int i)
    {
        String s = "";
        int j = 0;
        if((i & 1) == 1)
        {
            s = (new StringBuilder()).append("").append("WANTS_FOCUS").toString();
            j = 1;
        }
        int k = j;
        String s1 = s;
        if((i & 2) == 2)
        {
            s1 = s;
            if(j > 0)
                s1 = (new StringBuilder()).append(s).append(" ").toString();
            s1 = (new StringBuilder()).append(s1).append("FOCUSED").toString();
            k = j + 1;
        }
        j = k;
        s = s1;
        if((i & 4) == 4)
        {
            s = s1;
            if(k > 0)
                s = (new StringBuilder()).append(s1).append(" ").toString();
            s = (new StringBuilder()).append(s).append("SELECTED").toString();
            j = k + 1;
        }
        k = j;
        s1 = s;
        if((i & 8) == 8)
        {
            s1 = s;
            if(j > 0)
                s1 = (new StringBuilder()).append(s).append(" ").toString();
            s1 = (new StringBuilder()).append(s1).append("IS_ROOT_NAMESPACE").toString();
            k = j + 1;
        }
        j = k;
        s = s1;
        if((i & 0x10) == 16)
        {
            s = s1;
            if(k > 0)
                s = (new StringBuilder()).append(s1).append(" ").toString();
            s = (new StringBuilder()).append(s).append("HAS_BOUNDS").toString();
            j = k + 1;
        }
        s1 = s;
        if((i & 0x20) == 32)
        {
            s1 = s;
            if(j > 0)
                s1 = (new StringBuilder()).append(s).append(" ").toString();
            s1 = (new StringBuilder()).append(s1).append("DRAWN").toString();
        }
        return s1;
    }

    private void rebuildOutline()
    {
        if(mAttachInfo == null)
            return;
        if(mOutlineProvider == null)
        {
            mRenderNode.setOutline(null);
        } else
        {
            Outline outline = mAttachInfo.mTmpOutline;
            outline.setEmpty();
            outline.setAlpha(1.0F);
            mOutlineProvider.getOutline(this, outline);
            mRenderNode.setOutline(outline);
        }
    }

    private void registerPendingFrameMetricsObservers()
    {
        if(mFrameMetricsObservers != null)
        {
            ThreadedRenderer threadedrenderer = getThreadedRenderer();
            if(threadedrenderer != null)
            {
                for(Iterator iterator = mFrameMetricsObservers.iterator(); iterator.hasNext(); threadedrenderer.addFrameMetricsObserver((FrameMetricsObserver)iterator.next()));
            } else
            {
                Log.w("View", "View not hardware-accelerated. Unable to observe frame stats");
            }
        }
    }

    private void removeLongPressCallback()
    {
        if(mPendingCheckForLongPress != null)
            removeCallbacks(mPendingCheckForLongPress);
    }

    private void removePerformClickCallback()
    {
        if(mPerformClick != null)
            removeCallbacks(mPerformClick);
    }

    private void removeSendViewScrolledAccessibilityEventCallback()
    {
        if(mSendViewScrolledAccessibilityEvent != null)
        {
            removeCallbacks(mSendViewScrolledAccessibilityEvent);
            mSendViewScrolledAccessibilityEvent.mIsPending = false;
        }
    }

    private void removeTapCallback()
    {
        if(mPendingCheckForTap != null)
        {
            mPrivateFlags = mPrivateFlags & 0xfdffffff;
            removeCallbacks(mPendingCheckForTap);
        }
    }

    private void removeUnsetPressCallback()
    {
        if((mPrivateFlags & 0x4000) != 0 && mUnsetPressedState != null)
        {
            setPressed(false);
            removeCallbacks(mUnsetPressedState);
        }
    }

    private boolean requestFocusNoSearch(int i, Rect rect)
    {
        if((mViewFlags & 1) != 1 || (mViewFlags & 0xc) != 0)
            return false;
        if(isInTouchMode() && 0x40000 != (mViewFlags & 0x40000))
            return false;
        if(hasAncestorThatBlocksDescendantFocus())
        {
            return false;
        } else
        {
            handleFocusGainInternal(i, rect);
            return true;
        }
    }

    private void resetDisplayList()
    {
        mRenderNode.discardDisplayList();
        if(mBackgroundRenderNode != null)
            mBackgroundRenderNode.discardDisplayList();
    }

    private void resetPressedState()
    {
        if((mViewFlags & 0x20) == 32)
            return;
        if(isPressed())
        {
            setPressed(false);
            if(!mHasPerformedLongPress)
                removeLongPressCallback();
        }
    }

    public static int resolveSize(int i, int j)
    {
        return resolveSizeAndState(i, j, 0) & 0xffffff;
    }

    public static int resolveSizeAndState(int i, int j, int k)
    {
        int l;
        l = MeasureSpec.getMode(j);
        j = MeasureSpec.getSize(j);
        l;
        JVM INSTR lookupswitch 2: default 36
    //                   -2147483648: 44
    //                   1073741824: 61;
           goto _L1 _L2 _L3
_L1:
        return 0xff000000 & k | i;
_L2:
        if(j < i)
            i = j | 0x1000000;
        continue; /* Loop/switch isn't completed */
_L3:
        i = j;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private void saveAttributeData(AttributeSet attributeset, TypedArray typedarray)
    {
        int i;
        int j;
        String as[];
        int k;
        if(attributeset == null)
            i = 0;
        else
            i = attributeset.getAttributeCount();
        j = typedarray.getIndexCount();
        as = new String[(i + j) * 2];
        k = 0;
        for(int i1 = 0; i1 < i; i1++)
        {
            as[k] = attributeset.getAttributeName(i1);
            as[k + 1] = attributeset.getAttributeValue(i1);
            k += 2;
        }

        Resources resources = typedarray.getResources();
        SparseArray sparsearray = getAttributeMap();
        i = 0;
        int j1 = k;
        while(i < j) 
        {
            int k1 = typedarray.getIndex(i);
            int l;
            if(!typedarray.hasValueOrEmpty(k1))
            {
                l = j1;
            } else
            {
                int l1 = typedarray.getResourceId(k1, 0);
                l = j1;
                if(l1 != 0)
                {
                    String s = (String)sparsearray.get(l1);
                    attributeset = s;
                    if(s == null)
                    {
                        try
                        {
                            attributeset = resources.getResourceName(l1);
                        }
                        // Misplaced declaration of an exception variable
                        catch(AttributeSet attributeset)
                        {
                            attributeset = (new StringBuilder()).append("0x").append(Integer.toHexString(l1)).toString();
                        }
                        sparsearray.put(l1, attributeset);
                    }
                    as[j1] = attributeset;
                    as[j1 + 1] = typedarray.getString(k1);
                    l = j1 + 2;
                }
            }
            i++;
            j1 = l;
        }
        attributeset = new String[j1];
        System.arraycopy(as, 0, attributeset, 0, j1);
        mAttributes = attributeset;
    }

    private void sendAccessibilityHoverEvent(int i)
    {
        Object obj = this;
        do
        {
            if(((View) (obj)).includeForAccessibility())
            {
                ((View) (obj)).sendAccessibilityEvent(i);
                return;
            }
            obj = ((View) (obj)).getParent();
            if(obj instanceof View)
                obj = (View)obj;
            else
                return;
        } while(true);
    }

    private void sendViewTextTraversedAtGranularityEvent(int i, int j, int k, int l)
    {
        if(mParent == null)
        {
            return;
        } else
        {
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(0x20000);
            onInitializeAccessibilityEvent(accessibilityevent);
            onPopulateAccessibilityEvent(accessibilityevent);
            accessibilityevent.setFromIndex(k);
            accessibilityevent.setToIndex(l);
            accessibilityevent.setAction(i);
            accessibilityevent.setMovementGranularity(j);
            mParent.requestSendAccessibilityEvent(this, accessibilityevent);
            return;
        }
    }

    private void setBackgroundRenderNodeProperties(RenderNode rendernode)
    {
        rendernode.setTranslationX(mScrollX);
        rendernode.setTranslationY(mScrollY);
    }

    private void setDefaultFocusHighlight(Drawable drawable)
    {
        mDefaultFocusHighlight = drawable;
        mDefaultFocusHighlightSizeChanged = true;
        if(drawable == null) goto _L2; else goto _L1
_L1:
        if((mPrivateFlags & 0x80) != 0)
            mPrivateFlags = mPrivateFlags & 0xffffff7f;
        drawable.setLayoutDirection(getLayoutDirection());
        if(drawable.isStateful())
            drawable.setState(getDrawableState());
        if(isAttachedToWindow())
        {
            boolean flag;
            if(getWindowVisibility() == 0)
                flag = isShown();
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
        drawable.setCallback(this);
_L4:
        invalidate();
        return;
_L2:
        if((mViewFlags & 0x80) != 0 && mBackground == null && (mForegroundInfo == null || ForegroundInfo._2D_get1(mForegroundInfo) == null))
            mPrivateFlags = mPrivateFlags | 0x80;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setFocusedInCluster(View view)
    {
        if(this instanceof ViewGroup)
            ((ViewGroup)this).mFocusedInCluster = null;
        if(view == this)
            return;
        ViewParent viewparent = mParent;
        View view1 = this;
        do
        {
label0:
            {
                if(viewparent instanceof ViewGroup)
                {
                    ((ViewGroup)viewparent).mFocusedInCluster = view1;
                    if(viewparent != view)
                        break label0;
                }
                return;
            }
            view1 = (View)viewparent;
            viewparent = viewparent.getParent();
        } while(true);
    }

    private void setKeyedTag(int i, Object obj)
    {
        if(mKeyedTags == null)
            mKeyedTags = new SparseArray(2);
        mKeyedTags.put(i, obj);
    }

    private void setMeasuredDimensionRaw(int i, int j)
    {
        mMeasuredWidth = i;
        mMeasuredHeight = j;
        mPrivateFlags = mPrivateFlags | 0x800;
    }

    private boolean setOpticalFrame(int i, int j, int k, int l)
    {
        Insets insets;
        Insets insets1;
        if(mParent instanceof View)
            insets = ((View)mParent).getOpticalInsets();
        else
            insets = Insets.NONE;
        insets1 = getOpticalInsets();
        return setFrame((insets.left + i) - insets1.left, (insets.top + j) - insets1.top, insets.left + k + insets1.right, insets.top + l + insets1.bottom);
    }

    private void setOutlineProviderFromAttribute(int i)
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 33
    //                   1 43
    //                   2 51
    //                   3 61;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        continue; /* Loop/switch isn't completed */
_L3:
        setOutlineProvider(null);
        continue; /* Loop/switch isn't completed */
_L4:
        setOutlineProvider(ViewOutlineProvider.BOUNDS);
        continue; /* Loop/switch isn't completed */
_L5:
        setOutlineProvider(ViewOutlineProvider.PADDED_BOUNDS);
        if(true) goto _L1; else goto _L6
_L6:
    }

    private void setPressed(boolean flag, float f, float f1)
    {
        if(flag)
            drawableHotspotChanged(f, f1);
        setPressed(flag);
    }

    private void showHoverTooltip()
    {
        showTooltip(mTooltipInfo.mAnchorX, mTooltipInfo.mAnchorY, false);
    }

    private boolean showLongClickTooltip(int i, int j)
    {
        removeCallbacks(mTooltipInfo.mShowTooltipRunnable);
        removeCallbacks(mTooltipInfo.mHideTooltipRunnable);
        return showTooltip(i, j, true);
    }

    private boolean showTooltip(int i, int j, boolean flag)
    {
        if(mAttachInfo == null || mTooltipInfo == null)
            return false;
        if((mViewFlags & 0x20) != 0)
            return false;
        if(TextUtils.isEmpty(mTooltipInfo.mTooltipText))
            return false;
        hideTooltip();
        mTooltipInfo.mTooltipFromLongClick = flag;
        mTooltipInfo.mTooltipPopup = new TooltipPopup(getContext());
        if((mPrivateFlags3 & 0x20000) == 0x20000)
            flag = true;
        else
            flag = false;
        mTooltipInfo.mTooltipPopup.show(this, i, j, flag, mTooltipInfo.mTooltipText);
        mAttachInfo.mTooltipHost = this;
        return true;
    }

    private void sizeChange(int i, int j, int k, int l)
    {
        onSizeChanged(i, j, k, l);
        if(mOverlay != null)
        {
            mOverlay.getOverlayView().setRight(i);
            mOverlay.getOverlayView().setBottom(j);
        }
        rebuildOutline();
    }

    private boolean skipInvalidate()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((mViewFlags & 0xc) != 0)
        {
            flag1 = flag;
            if(mCurrentAnimation == null)
                if(mParent instanceof ViewGroup)
                    flag1 = ((ViewGroup)mParent).isViewTransitioning(this) ^ true;
                else
                    flag1 = true;
        }
        return flag1;
    }

    private void switchDefaultFocusHighlight()
    {
        if(!isFocused()) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag1;
        Drawable drawable = mBackground;
        Drawable drawable1;
        if(mForegroundInfo == null)
            drawable1 = null;
        else
            drawable1 = ForegroundInfo._2D_get1(mForegroundInfo);
        flag = isDefaultFocusHighlightNeeded(drawable, drawable1);
        if(mDefaultFocusHighlight != null)
            flag1 = true;
        else
            flag1 = false;
        if(!flag || !(flag1 ^ true)) goto _L4; else goto _L3
_L3:
        setDefaultFocusHighlight(getDefaultFocusHighlightDrawable());
_L2:
        return;
_L4:
        if(!flag && flag1)
            setDefaultFocusHighlight(null);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean traverseAtGranularity(int i, boolean flag, boolean flag1)
    {
        CharSequence charsequence = getIterableTextForAccessibility();
        if(charsequence == null || charsequence.length() == 0)
            return false;
        Object obj = getIteratorForGranularity(i);
        if(obj == null)
            return false;
        int j = getAccessibilitySelectionEnd();
        int k = j;
        if(j == -1)
            if(flag)
                k = 0;
            else
                k = charsequence.length();
        if(flag)
            obj = ((AccessibilityIterators.TextSegmentIterator) (obj)).following(k);
        else
            obj = ((AccessibilityIterators.TextSegmentIterator) (obj)).preceding(k);
        if(obj == null)
            return false;
        j = obj[0];
        int l = obj[1];
        int i1;
        if(flag1 && isAccessibilitySelectionExtendable())
        {
            i1 = getAccessibilitySelectionStart();
            k = i1;
            if(i1 == -1)
                if(flag)
                    k = j;
                else
                    k = l;
            if(flag)
                i1 = l;
            else
                i1 = j;
        } else
        {
            int j1;
            if(flag)
                k = l;
            else
                k = j;
            j1 = k;
            i1 = k;
            k = j1;
        }
        setAccessibilitySelection(k, i1);
        if(flag)
            k = 256;
        else
            k = 512;
        sendViewTextTraversedAtGranularityEvent(k, i, j, l);
        return true;
    }

    private void updateFocusedInCluster(View view, int i)
    {
        if(view == null) goto _L2; else goto _L1
_L1:
        View view1 = view.findKeyboardNavigationCluster();
        if(view1 == findKeyboardNavigationCluster()) goto _L2; else goto _L3
_L3:
        view.setFocusedInCluster(view1);
        if(!(view.mParent instanceof ViewGroup))
            return;
        if(i != 2 && i != 1) goto _L5; else goto _L4
_L4:
        ((ViewGroup)view.mParent).clearFocusedInCluster(view);
_L2:
        return;
_L5:
        if((view instanceof ViewGroup) && ((ViewGroup)view).getDescendantFocusability() == 0x40000 && ViewRootImpl.isViewDescendantOf(this, view))
            ((ViewGroup)view.mParent).clearFocusedInCluster(view);
        if(true) goto _L2; else goto _L6
_L6:
    }

    void _2D_android_view_View_2D_mthref_2D_0()
    {
        showHoverTooltip();
    }

    void _2D_android_view_View_2D_mthref_2D_1()
    {
        hideTooltip();
    }

    public void addChildrenForAccessibility(ArrayList arraylist)
    {
    }

    public void addExtraDataToAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo, String s, Bundle bundle)
    {
    }

    public void addFocusables(ArrayList arraylist, int i)
    {
        int j;
        if(isInTouchMode())
            j = 1;
        else
            j = 0;
        addFocusables(arraylist, i, j);
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        if(arraylist == null)
            return;
        if(!isFocusable())
            return;
        if((j & 1) == 1 && isFocusableInTouchMode() ^ true)
        {
            return;
        } else
        {
            arraylist.add(this);
            return;
        }
    }

    public void addFrameMetricsListener(Window window, Window.OnFrameMetricsAvailableListener onframemetricsavailablelistener, Handler handler)
    {
        if(mAttachInfo != null)
        {
            if(mAttachInfo.mThreadedRenderer != null)
            {
                if(mFrameMetricsObservers == null)
                    mFrameMetricsObservers = new ArrayList();
                window = new FrameMetricsObserver(window, handler.getLooper(), onframemetricsavailablelistener);
                mFrameMetricsObservers.add(window);
                mAttachInfo.mThreadedRenderer.addFrameMetricsObserver(window);
            } else
            {
                Log.w("View", "View not hardware-accelerated. Unable to observe frame stats");
            }
        } else
        {
            if(mFrameMetricsObservers == null)
                mFrameMetricsObservers = new ArrayList();
            window = new FrameMetricsObserver(window, handler.getLooper(), onframemetricsavailablelistener);
            mFrameMetricsObservers.add(window);
        }
    }

    public void addKeyboardNavigationClusters(Collection collection, int i)
    {
        if(!isKeyboardNavigationCluster())
            return;
        if(!hasFocusable())
        {
            return;
        } else
        {
            collection.add(this);
            return;
        }
    }

    public void addOnAttachStateChangeListener(OnAttachStateChangeListener onattachstatechangelistener)
    {
        ListenerInfo listenerinfo = getListenerInfo();
        if(ListenerInfo._2D_get0(listenerinfo) == null)
            ListenerInfo._2D_set0(listenerinfo, new CopyOnWriteArrayList());
        ListenerInfo._2D_get0(listenerinfo).add(onattachstatechangelistener);
    }

    public void addOnLayoutChangeListener(OnLayoutChangeListener onlayoutchangelistener)
    {
        ListenerInfo listenerinfo = getListenerInfo();
        if(ListenerInfo._2D_get5(listenerinfo) == null)
            ListenerInfo._2D_set5(listenerinfo, new ArrayList());
        if(!ListenerInfo._2D_get5(listenerinfo).contains(onlayoutchangelistener))
            ListenerInfo._2D_get5(listenerinfo).add(onlayoutchangelistener);
    }

    public void addTouchables(ArrayList arraylist)
    {
        int i = mViewFlags;
        if(((i & 0x4000) == 16384 || (i & 0x200000) == 0x200000 || (i & 0x800000) == 0x800000) && (i & 0x20) == 0)
            arraylist.add(this);
    }

    public ViewPropertyAnimator animate()
    {
        if(mAnimator == null)
            mAnimator = new ViewPropertyAnimator(this);
        return mAnimator;
    }

    public void announceForAccessibility(CharSequence charsequence)
    {
        if(AccessibilityManager.getInstance(mContext).isEnabled() && mParent != null)
        {
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(16384);
            onInitializeAccessibilityEvent(accessibilityevent);
            accessibilityevent.getText().add(charsequence);
            accessibilityevent.setContentDescription(null);
            mParent.requestSendAccessibilityEvent(this, accessibilityevent);
        }
    }

    public void applyDrawableToTransparentRegion(Drawable drawable, Region region)
    {
        Region region1 = drawable.getTransparentRegion();
        drawable = drawable.getBounds();
        AttachInfo attachinfo = mAttachInfo;
        if(region1 != null && attachinfo != null)
        {
            int i = getRight() - getLeft();
            int j = getBottom() - getTop();
            if(((Rect) (drawable)).left > 0)
                region1.op(0, 0, ((Rect) (drawable)).left, j, android.graphics.Region.Op.UNION);
            if(((Rect) (drawable)).right < i)
                region1.op(((Rect) (drawable)).right, 0, i, j, android.graphics.Region.Op.UNION);
            if(((Rect) (drawable)).top > 0)
                region1.op(0, 0, i, ((Rect) (drawable)).top, android.graphics.Region.Op.UNION);
            if(((Rect) (drawable)).bottom < j)
                region1.op(0, ((Rect) (drawable)).bottom, i, j, android.graphics.Region.Op.UNION);
            drawable = attachinfo.mTransparentLocation;
            getLocationInWindow(drawable);
            region1.translate(drawable[0], drawable[1]);
            region.op(region1, android.graphics.Region.Op.INTERSECT);
        } else
        {
            region.op(drawable, android.graphics.Region.Op.DIFFERENCE);
        }
    }

    boolean areDrawablesResolved()
    {
        boolean flag;
        if((mPrivateFlags2 & 0x40000000) == 0x40000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void assignParent(ViewParent viewparent)
    {
        if(mParent == null)
            mParent = viewparent;
        else
        if(viewparent == null)
            mParent = null;
        else
            throw new RuntimeException((new StringBuilder()).append("view ").append(this).append(" being added, but").append(" it already has a parent").toString());
    }

    public void autofill(SparseArray sparsearray)
    {
    }

    public void autofill(AutofillValue autofillvalue)
    {
    }

    protected boolean awakenScrollBars()
    {
        boolean flag;
        if(mScrollCache != null)
            flag = awakenScrollBars(mScrollCache.scrollBarDefaultDelayBeforeFade, true);
        else
            flag = false;
        return flag;
    }

    protected boolean awakenScrollBars(int i)
    {
        return awakenScrollBars(i, true);
    }

    protected boolean awakenScrollBars(int i, boolean flag)
    {
        ScrollabilityCache scrollabilitycache = mScrollCache;
        if(scrollabilitycache == null || scrollabilitycache.fadeScrollBars ^ true)
            return false;
        if(scrollabilitycache.scrollBar == null)
        {
            scrollabilitycache.scrollBar = new ScrollBarDrawable();
            scrollabilitycache.scrollBar.setState(getDrawableState());
            scrollabilitycache.scrollBar.setCallback(this);
        }
        if(isHorizontalScrollBarEnabled() || isVerticalScrollBarEnabled())
        {
            if(flag)
                postInvalidateOnAnimation();
            int j = i;
            if(scrollabilitycache.state == 0)
                j = Math.max(750, i);
            long l = AnimationUtils.currentAnimationTimeMillis() + (long)j;
            scrollabilitycache.fadeStartTime = l;
            scrollabilitycache.state = 1;
            if(mAttachInfo != null)
            {
                mAttachInfo.mHandler.removeCallbacks(scrollabilitycache);
                mAttachInfo.mHandler.postAtTime(scrollabilitycache, l);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void bringToFront()
    {
        if(mParent != null)
            mParent.bringChildToFront(this);
    }

    public void buildDrawingCache()
    {
        buildDrawingCache(false);
    }

    public void buildDrawingCache(boolean flag)
    {
        if((mPrivateFlags & 0x8000) == 0 || (flag ? mDrawingCache == null : mUnscaledDrawingCache == null))
            break MISSING_BLOCK_LABEL_21;
        else
            break MISSING_BLOCK_LABEL_73;
        if(Trace.isTagEnabled(8L))
            Trace.traceBegin(8L, (new StringBuilder()).append("buildDrawingCache/SW Layer for ").append(getClass().getSimpleName()).toString());
        buildDrawingCacheImpl(flag);
        Trace.traceEnd(8L);
        return;
        Exception exception;
        exception;
        Trace.traceEnd(8L);
        throw exception;
    }

    public void buildLayer()
    {
        AttachInfo attachinfo;
        if(mLayerType == 0)
            return;
        attachinfo = mAttachInfo;
        if(attachinfo == null)
            throw new IllegalStateException("This view must be attached to a window first");
        if(getWidth() == 0 || getHeight() == 0)
            return;
        mLayerType;
        JVM INSTR tableswitch 1 2: default 68
    //                   1 105
    //                   2 69;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        updateDisplayListIfDirty();
        if(attachinfo.mThreadedRenderer != null && mRenderNode.isValid())
            attachinfo.mThreadedRenderer.buildLayer(mRenderNode);
        continue; /* Loop/switch isn't completed */
_L2:
        buildDrawingCache(true);
        if(true) goto _L1; else goto _L4
_L4:
    }

    final boolean callDragEventHandler(DragEvent dragevent)
    {
        ListenerInfo listenerinfo = mListenerInfo;
        boolean flag;
        if(listenerinfo != null && ListenerInfo._2D_get1(listenerinfo) != null && (mViewFlags & 0x20) == 0 && ListenerInfo._2D_get1(listenerinfo).onDrag(this, dragevent))
            flag = true;
        else
            flag = onDragEvent(dragevent);
        dragevent.mAction;
        JVM INSTR tableswitch 4 6: default 72
    //                   4 118
    //                   5 83
    //                   6 100;
           goto _L1 _L2 _L3 _L4
_L1:
        return flag;
_L3:
        mPrivateFlags2 = mPrivateFlags2 | 2;
        refreshDrawableState();
        continue; /* Loop/switch isn't completed */
_L4:
        mPrivateFlags2 = mPrivateFlags2 & -3;
        refreshDrawableState();
        continue; /* Loop/switch isn't completed */
_L2:
        mPrivateFlags2 = mPrivateFlags2 & -4;
        refreshDrawableState();
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean callOnClick()
    {
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null && listenerinfo.mOnClickListener != null)
        {
            listenerinfo.mOnClickListener.onClick(this);
            return true;
        } else
        {
            return false;
        }
    }

    boolean canAcceptDrag()
    {
        boolean flag = false;
        if((mPrivateFlags2 & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean canHaveDisplayList()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mAttachInfo != null)
        {
            flag1 = flag;
            if(mAttachInfo.mThreadedRenderer != null)
                flag1 = true;
        }
        return flag1;
    }

    public boolean canResolveLayoutDirection()
    {
        switch(getRawLayoutDirection())
        {
        default:
            return true;

        case 2: // '\002'
            break;
        }
        if(mParent == null)
            break MISSING_BLOCK_LABEL_83;
        boolean flag = mParent.canResolveLayoutDirection();
        return flag;
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
        return false;
    }

    public boolean canResolveTextAlignment()
    {
        switch(getRawTextAlignment())
        {
        default:
            return true;

        case 0: // '\0'
            break;
        }
        if(mParent == null)
            break MISSING_BLOCK_LABEL_83;
        boolean flag = mParent.canResolveTextAlignment();
        return flag;
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
        return false;
    }

    public boolean canResolveTextDirection()
    {
        switch(getRawTextDirection())
        {
        default:
            return true;

        case 0: // '\0'
            break;
        }
        if(mParent == null)
            break MISSING_BLOCK_LABEL_83;
        boolean flag = mParent.canResolveTextDirection();
        return flag;
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
        return false;
    }

    public boolean canScrollHorizontally(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        int j = computeHorizontalScrollOffset();
        int k = computeHorizontalScrollRange() - computeHorizontalScrollExtent();
        if(k == 0)
            return false;
        if(i < 0)
        {
            if(j <= 0)
                flag1 = false;
            return flag1;
        }
        if(j < k - 1)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    public boolean canScrollVertically(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        int j = computeVerticalScrollOffset();
        int k = computeVerticalScrollRange() - computeVerticalScrollExtent();
        if(k == 0)
            return false;
        if(i < 0)
        {
            if(j <= 0)
                flag1 = false;
            return flag1;
        }
        if(j < k - 1)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    public final void cancelDragAndDrop()
    {
        if(mAttachInfo == null)
        {
            Log.w("View", "cancelDragAndDrop called on a detached view.");
            return;
        }
        if(mAttachInfo.mDragToken != null)
        {
            try
            {
                mAttachInfo.mSession.cancelDragAndDrop(mAttachInfo.mDragToken);
            }
            catch(Exception exception)
            {
                Log.e("View", "Unable to cancel drag", exception);
            }
            mAttachInfo.mDragToken = null;
        } else
        {
            Log.e("View", "No active drag to cancel");
        }
    }

    public void cancelLongPress()
    {
        removeLongPressCallback();
        removeTapCallback();
    }

    public final void cancelPendingInputEvents()
    {
        dispatchCancelPendingInputEvents();
    }

    public void captureTransitioningViews(List list)
    {
        if(getVisibility() == 0)
            list.add(this);
    }

    public boolean checkInputConnectionProxy(View view)
    {
        return false;
    }

    public void clearAccessibilityFocus()
    {
        clearAccessibilityFocusNoCallbacks(0);
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl != null)
        {
            View view = viewrootimpl.getAccessibilityFocusedHost();
            if(view != null && ViewRootImpl.isViewDescendantOf(view, this))
                viewrootimpl.setAccessibilityFocus(null, null);
        }
    }

    void clearAccessibilityFocusNoCallbacks(int i)
    {
        if((mPrivateFlags2 & 0x4000000) != 0)
        {
            mPrivateFlags2 = mPrivateFlags2 & 0xfbffffff;
            invalidate();
            if(AccessibilityManager.getInstance(mContext).isEnabled())
            {
                AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(0x10000);
                accessibilityevent.setAction(i);
                if(mAccessibilityDelegate != null)
                    mAccessibilityDelegate.sendAccessibilityEventUnchecked(this, accessibilityevent);
                else
                    sendAccessibilityEventUnchecked(accessibilityevent);
            }
        }
    }

    public void clearAnimation()
    {
        if(mCurrentAnimation != null)
            mCurrentAnimation.detach();
        mCurrentAnimation = null;
        invalidateParentIfNeeded();
    }

    public void clearFocus()
    {
        clearFocusInternal(null, true, true);
    }

    void clearFocusInternal(View view, boolean flag, boolean flag1)
    {
        if((mPrivateFlags & 2) != 0)
        {
            mPrivateFlags = mPrivateFlags & -3;
            if(flag && mParent != null)
                mParent.clearChildFocus(this);
            onFocusChanged(false, 0, null);
            refreshDrawableState();
            if(flag && (!flag1 || rootViewRequestFocus() ^ true))
                notifyGlobalFocusCleared(this);
        }
    }

    int combineVisibility(int i, int j)
    {
        return Math.max(i, j);
    }

    protected boolean computeFitSystemWindows(Rect rect, Rect rect1)
    {
        while((mViewFlags & 0x800) == 0 || mAttachInfo == null || (mAttachInfo.mSystemUiVisibility & 0x600) == 0 && mAttachInfo.mOverscanRequested ^ true) 
        {
            rect1.set(rect);
            rect.set(0, 0, 0, 0);
            return true;
        }
        Rect rect2 = mAttachInfo.mOverscanInsets;
        rect1.set(rect2);
        rect.left = rect.left - rect2.left;
        rect.top = rect.top - rect2.top;
        rect.right = rect.right - rect2.right;
        rect.bottom = rect.bottom - rect2.bottom;
        return false;
    }

    protected int computeHorizontalScrollExtent()
    {
        return getWidth();
    }

    protected int computeHorizontalScrollOffset()
    {
        return mScrollX;
    }

    protected int computeHorizontalScrollRange()
    {
        return getWidth();
    }

    protected void computeOpaqueFlags()
    {
        int i;
        if(mBackground != null && mBackground.getOpacity() == -1)
            mPrivateFlags = mPrivateFlags | 0x800000;
        else
            mPrivateFlags = mPrivateFlags & 0xff7fffff;
        i = mViewFlags;
        break MISSING_BLOCK_LABEL_34;
        if((i & 0x200) == 0 && (i & 0x100) == 0 || (i & 0x3000000) == 0 || (i & 0x3000000) == 0x2000000)
            mPrivateFlags = mPrivateFlags | 0x1000000;
        else
            mPrivateFlags = mPrivateFlags & 0xfeffffff;
        return;
    }

    Insets computeOpticalInsets()
    {
        Insets insets;
        if(mBackground == null)
            insets = Insets.NONE;
        else
            insets = mBackground.getOpticalInsets();
        return insets;
    }

    public void computeScroll()
    {
    }

    public WindowInsets computeSystemWindowInsets(WindowInsets windowinsets, Rect rect)
    {
        while((mViewFlags & 0x800) == 0 || mAttachInfo == null || (mAttachInfo.mSystemUiVisibility & 0x600) == 0) 
        {
            rect.set(windowinsets.getSystemWindowInsets());
            return windowinsets.consumeSystemWindowInsets();
        }
        rect.set(0, 0, 0, 0);
        return windowinsets;
    }

    protected int computeVerticalScrollExtent()
    {
        return getHeight();
    }

    protected int computeVerticalScrollOffset()
    {
        return mScrollY;
    }

    protected int computeVerticalScrollRange()
    {
        return getHeight();
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo()
    {
        if(mAccessibilityDelegate != null)
            return mAccessibilityDelegate.createAccessibilityNodeInfo(this);
        else
            return createAccessibilityNodeInfoInternal();
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfoInternal()
    {
        AccessibilityNodeProvider accessibilitynodeprovider = getAccessibilityNodeProvider();
        if(accessibilitynodeprovider != null)
        {
            return accessibilitynodeprovider.createAccessibilityNodeInfo(-1);
        } else
        {
            AccessibilityNodeInfo accessibilitynodeinfo = AccessibilityNodeInfo.obtain(this);
            onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
            return accessibilitynodeinfo;
        }
    }

    public void createContextMenu(ContextMenu contextmenu)
    {
        ContextMenu.ContextMenuInfo contextmenuinfo = getContextMenuInfo();
        ((MenuBuilder)contextmenu).setCurrentMenuInfo(contextmenuinfo);
        onCreateContextMenu(contextmenu);
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null && listenerinfo.mOnCreateContextMenuListener != null)
            listenerinfo.mOnCreateContextMenuListener.onCreateContextMenu(contextmenu, this, contextmenuinfo);
        ((MenuBuilder)contextmenu).setCurrentMenuInfo(null);
        if(mParent != null)
            mParent.createContextMenu(contextmenu);
    }

    public Bitmap createSnapshot(android.graphics.Bitmap.Config config, int i, boolean flag)
    {
        int j = mRight;
        int k = mLeft;
        int l = mBottom;
        int i1 = mTop;
        AttachInfo attachinfo = mAttachInfo;
        float f;
        DisplayMetrics displaymetrics;
        Bitmap bitmap;
        if(attachinfo != null)
            f = attachinfo.mApplicationScale;
        else
            f = 1.0F;
        k = (int)((float)(j - k) * f + 0.5F);
        i1 = (int)((float)(l - i1) * f + 0.5F);
        displaymetrics = mResources.getDisplayMetrics();
        if(k <= 0)
            k = 1;
        if(i1 <= 0)
            i1 = 1;
        bitmap = Bitmap.createBitmap(displaymetrics, k, i1, config);
        if(bitmap == null)
            throw new OutOfMemoryError();
        config = getResources();
        if(config != null)
            bitmap.setDensity(config.getDisplayMetrics().densityDpi);
        if(attachinfo != null)
        {
            Canvas canvas = attachinfo.mCanvas;
            config = canvas;
            if(canvas == null)
                config = new Canvas();
            config.setBitmap(bitmap);
            attachinfo.mCanvas = null;
        } else
        {
            config = new Canvas(bitmap);
        }
        flag = config.isHwBitmapsInSwModeEnabled();
        config.setHwBitmapsInSwModeEnabled(true);
        if((0xff000000 & i) != 0)
            bitmap.eraseColor(i);
        computeScroll();
        k = config.save();
        config.scale(f, f);
        config.translate(-mScrollX, -mScrollY);
        i = mPrivateFlags;
        mPrivateFlags = mPrivateFlags & 0xff9fffff;
        if((mPrivateFlags & 0x80) == 128)
        {
            dispatchDraw(config);
            drawAutofilledHighlight(config);
            if(mOverlay != null && mOverlay.isEmpty() ^ true)
                mOverlay.getOverlayView().draw(config);
        } else
        {
            draw(config);
        }
        mPrivateFlags = i;
        config.restoreToCount(k);
        config.setBitmap(null);
        config.setHwBitmapsInSwModeEnabled(flag);
        if(attachinfo != null)
            attachinfo.mCanvas = config;
        return bitmap;
    }

    protected void damageInParent()
    {
        if(mParent != null && mAttachInfo != null)
            mParent.onDescendantInvalidated(this, this);
    }

    public void debug()
    {
        debug(0);
    }

    protected void debug(int i)
    {
        String s = debugIndent(i - 1);
        String s3 = (new StringBuilder()).append(s).append("+ ").append(this).toString();
        int j = getId();
        s = s3;
        if(j != -1)
            s = (new StringBuilder()).append(s3).append(" (id=").append(j).append(")").toString();
        Object obj = getTag();
        s3 = s;
        if(obj != null)
            s3 = (new StringBuilder()).append(s).append(" (tag=").append(obj).append(")").toString();
        Log.d("View", s3);
        if((mPrivateFlags & 2) != 0)
            Log.d("View", (new StringBuilder()).append(debugIndent(i)).append(" FOCUSED").toString());
        s = debugIndent(i);
        Log.d("View", (new StringBuilder()).append(s).append("frame={").append(mLeft).append(", ").append(mTop).append(", ").append(mRight).append(", ").append(mBottom).append("} scroll={").append(mScrollX).append(", ").append(mScrollY).append("} ").toString());
        break MISSING_BLOCK_LABEL_280;
        String s2;
        if(mPaddingLeft != 0 || mPaddingTop != 0 || mPaddingRight != 0 || mPaddingBottom != 0)
        {
            String s1 = debugIndent(i);
            Log.d("View", (new StringBuilder()).append(s1).append("padding={").append(mPaddingLeft).append(", ").append(mPaddingTop).append(", ").append(mPaddingRight).append(", ").append(mPaddingBottom).append("}").toString());
        }
        s2 = debugIndent(i);
        Log.d("View", (new StringBuilder()).append(s2).append("mMeasureWidth=").append(mMeasuredWidth).append(" mMeasureHeight=").append(mMeasuredHeight).toString());
        s2 = debugIndent(i);
        if(mLayoutParams == null)
            s2 = (new StringBuilder()).append(s2).append("BAD! no layout params").toString();
        else
            s2 = mLayoutParams.debug(s2);
        Log.d("View", s2);
        s2 = debugIndent(i);
        s2 = (new StringBuilder()).append(s2).append("flags={").toString();
        s2 = (new StringBuilder()).append(s2).append(printFlags(mViewFlags)).toString();
        Log.d("View", (new StringBuilder()).append(s2).append("}").toString());
        s2 = debugIndent(i);
        s2 = (new StringBuilder()).append(s2).append("privateFlags={").toString();
        s2 = (new StringBuilder()).append(s2).append(printPrivateFlags(mPrivateFlags)).toString();
        Log.d("View", (new StringBuilder()).append(s2).append("}").toString());
        return;
    }

    final boolean debugDraw()
    {
        boolean flag;
        if(!DEBUG_DRAW)
        {
            if(mAttachInfo != null)
                flag = mAttachInfo.mDebugLayout;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public void destroyDrawingCache()
    {
        if(mDrawingCache != null)
        {
            mDrawingCache.recycle();
            mDrawingCache = null;
        }
        if(mUnscaledDrawingCache != null)
        {
            mUnscaledDrawingCache.recycle();
            mUnscaledDrawingCache = null;
        }
    }

    protected void destroyHardwareResources()
    {
        if(mOverlay != null)
            mOverlay.getOverlayView().destroyHardwareResources();
        if(mGhostView != null)
            mGhostView.destroyHardwareResources();
    }

    final int dipsToPixels(int i)
    {
        float f = getContext().getResources().getDisplayMetrics().density;
        return (int)((float)i * f + 0.5F);
    }

    public boolean dispatchActivityResult(String s, int i, int j, Intent intent)
    {
        if(mStartActivityRequestWho != null && mStartActivityRequestWho.equals(s))
        {
            onActivityResult(i, j, intent);
            mStartActivityRequestWho = null;
            return true;
        } else
        {
            return false;
        }
    }

    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowinsets)
    {
        mPrivateFlags3 = mPrivateFlags3 | 0x20;
        if(mListenerInfo == null || mListenerInfo.mOnApplyWindowInsetsListener == null)
            break MISSING_BLOCK_LABEL_56;
        windowinsets = mListenerInfo.mOnApplyWindowInsetsListener.onApplyWindowInsets(this, windowinsets);
        mPrivateFlags3 = mPrivateFlags3 & 0xffffffdf;
        return windowinsets;
        windowinsets = onApplyWindowInsets(windowinsets);
        mPrivateFlags3 = mPrivateFlags3 & 0xffffffdf;
        return windowinsets;
        windowinsets;
        mPrivateFlags3 = mPrivateFlags3 & 0xffffffdf;
        throw windowinsets;
    }

    void dispatchAttachedToWindow(AttachInfo attachinfo, int i)
    {
        mAttachInfo = attachinfo;
        notifyConfirmedWebView(true);
        if(mOverlay != null)
            mOverlay.getOverlayView().dispatchAttachedToWindow(attachinfo, i);
        mWindowAttachCount = mWindowAttachCount + 1;
        mPrivateFlags = mPrivateFlags | 0x400;
        if(mFloatingTreeObserver != null)
        {
            attachinfo.mTreeObserver.merge(mFloatingTreeObserver);
            mFloatingTreeObserver = null;
        }
        registerPendingFrameMetricsObservers();
        if((mPrivateFlags & 0x80000) != 0)
        {
            mAttachInfo.mScrollContainers.add(this);
            mPrivateFlags = mPrivateFlags | 0x100000;
        }
        if(mRunQueue != null)
        {
            mRunQueue.executeActions(attachinfo.mHandler);
            mRunQueue = null;
        }
        performCollectViewAttributes(mAttachInfo, i);
        onAttachedToWindow();
        Object obj = mListenerInfo;
        if(obj != null)
            obj = ListenerInfo._2D_get0(((ListenerInfo) (obj)));
        else
            obj = null;
        if(obj != null && ((CopyOnWriteArrayList) (obj)).size() > 0)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((OnAttachStateChangeListener)((Iterator) (obj)).next()).onViewAttachedToWindow(this));
        int j = attachinfo.mWindowVisibility;
        if(j != 8)
        {
            onWindowVisibilityChanged(j);
            if(isShown())
            {
                boolean flag;
                if(j == 0)
                    flag = true;
                else
                    flag = false;
                onVisibilityAggregated(flag);
            }
        }
        onVisibilityChanged(this, i);
        if((mPrivateFlags & 0x400) != 0)
            refreshDrawableState();
        needGlobalAttributesUpdate(false);
        notifyEnterOrExitForAutoFillIfNeeded(true);
    }

    void dispatchCancelPendingInputEvents()
    {
        mPrivateFlags3 = mPrivateFlags3 & 0xffffffef;
        onCancelPendingInputEvents();
        if((mPrivateFlags3 & 0x10) != 16)
            throw new SuperNotCalledException((new StringBuilder()).append("View ").append(getClass().getSimpleName()).append(" did not call through to super.onCancelPendingInputEvents()").toString());
        else
            return;
    }

    public boolean dispatchCapturedPointerEvent(MotionEvent motionevent)
    {
        if(!hasPointerCapture())
            return false;
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null && listenerinfo.mOnCapturedPointerListener != null && listenerinfo.mOnCapturedPointerListener.onCapturedPointer(this, motionevent))
            return true;
        else
            return onCapturedPointerEvent(motionevent);
    }

    void dispatchCollectViewAttributes(AttachInfo attachinfo, int i)
    {
        performCollectViewAttributes(attachinfo, i);
    }

    public void dispatchConfigurationChanged(Configuration configuration)
    {
        onConfigurationChanged(configuration);
    }

    void dispatchDetachedFromWindow()
    {
        notifyConfirmedWebView(false);
        Object obj = mAttachInfo;
        if(obj != null && ((AttachInfo) (obj)).mWindowVisibility != 8)
        {
            onWindowVisibilityChanged(8);
            if(isShown())
                onVisibilityAggregated(false);
        }
        onDetachedFromWindow();
        onDetachedFromWindowInternal();
        obj = InputMethodManager.peekInstance();
        if(obj != null)
            ((InputMethodManager) (obj)).onViewDetachedFromWindow(this);
        obj = mListenerInfo;
        if(obj != null)
            obj = ListenerInfo._2D_get0(((ListenerInfo) (obj)));
        else
            obj = null;
        if(obj != null && ((CopyOnWriteArrayList) (obj)).size() > 0)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((OnAttachStateChangeListener)((Iterator) (obj)).next()).onViewDetachedFromWindow(this));
        if((mPrivateFlags & 0x100000) != 0)
        {
            mAttachInfo.mScrollContainers.remove(this);
            mPrivateFlags = mPrivateFlags & 0xffefffff;
        }
        mAttachInfo = null;
        if(mOverlay != null)
            mOverlay.getOverlayView().dispatchDetachedFromWindow();
        notifyEnterOrExitForAutoFillIfNeeded(false);
    }

    public void dispatchDisplayHint(int i)
    {
        onDisplayHint(i);
    }

    boolean dispatchDragEnterExitInPreN(DragEvent dragevent)
    {
        return callDragEventHandler(dragevent);
    }

    public boolean dispatchDragEvent(DragEvent dragevent)
    {
        dragevent.mEventHandlerWasCalled = true;
        if(dragevent.mAction == 2 || dragevent.mAction == 3)
            getViewRootImpl().setDragFocus(this, dragevent);
        return callDragEventHandler(dragevent);
    }

    protected void dispatchDraw(Canvas canvas)
    {
    }

    public void dispatchDrawableHotspotChanged(float f, float f1)
    {
    }

    public void dispatchFinishTemporaryDetach()
    {
        mPrivateFlags3 = mPrivateFlags3 & 0xfdffffff;
        onFinishTemporaryDetach();
        if(hasWindowFocus() && hasFocus())
            InputMethodManager.getInstance().focusIn(this);
        notifyEnterOrExitForAutoFillIfNeeded(true);
    }

    protected boolean dispatchGenericFocusedEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onGenericMotionEvent(motionevent, 0);
        if((motionevent.getSource() & 2) != 0)
        {
            int i = motionevent.getAction();
            if(i == 9 || i == 7 || i == 10)
            {
                if(dispatchHoverEvent(motionevent))
                    return true;
            } else
            if(dispatchGenericPointerEvent(motionevent))
                return true;
        } else
        if(dispatchGenericFocusedEvent(motionevent))
            return true;
        if(dispatchGenericMotionEventInternal(motionevent))
            return true;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(motionevent, 0);
        return false;
    }

    protected boolean dispatchGenericPointerEvent(MotionEvent motionevent)
    {
        return false;
    }

    protected void dispatchGetDisplayList()
    {
    }

    protected boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null && ListenerInfo._2D_get3(listenerinfo) != null && (mViewFlags & 0x20) == 0 && ListenerInfo._2D_get3(listenerinfo).onHover(this, motionevent))
            return true;
        else
            return onHoverEvent(motionevent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        KeyEvent.DispatcherState dispatcherstate = null;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onKeyEvent(keyevent, 0);
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null && ListenerInfo._2D_get4(listenerinfo) != null && (mViewFlags & 0x20) == 0 && ListenerInfo._2D_get4(listenerinfo).onKey(this, keyevent.getKeyCode(), keyevent))
            return true;
        if(mAttachInfo != null)
            dispatcherstate = mAttachInfo.mKeyDispatchState;
        if(keyevent.dispatch(this, dispatcherstate, this))
            return true;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(keyevent, 0);
        return false;
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyevent)
    {
        return onKeyPreIme(keyevent.getKeyCode(), keyevent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        return onKeyShortcut(keyevent.getKeyCode(), keyevent);
    }

    void dispatchMovedToDisplay(Display display, Configuration configuration)
    {
        mAttachInfo.mDisplay = display;
        mAttachInfo.mDisplayState = display.getState();
        onMovedToDisplay(display.getDisplayId(), configuration);
    }

    public boolean dispatchNestedFling(float f, float f1, boolean flag)
    {
        if(isNestedScrollingEnabled() && mNestedScrollingParent != null)
            return mNestedScrollingParent.onNestedFling(this, f, f1, flag);
        else
            return false;
    }

    public boolean dispatchNestedPreFling(float f, float f1)
    {
        if(isNestedScrollingEnabled() && mNestedScrollingParent != null)
            return mNestedScrollingParent.onNestedPreFling(this, f, f1);
        else
            return false;
    }

    public boolean dispatchNestedPrePerformAccessibilityAction(int i, Bundle bundle)
    {
        for(ViewParent viewparent = getParent(); viewparent != null; viewparent = viewparent.getParent())
            if(viewparent.onNestedPrePerformAccessibilityAction(this, i, bundle))
                return true;

        return false;
    }

    public boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[])
    {
        boolean flag = true;
        if(isNestedScrollingEnabled() && mNestedScrollingParent != null)
        {
            if(i != 0 || j != 0)
            {
                int k = 0;
                int l = 0;
                if(ai1 != null)
                {
                    getLocationInWindow(ai1);
                    k = ai1[0];
                    l = ai1[1];
                }
                int ai2[] = ai;
                if(ai == null)
                {
                    if(mTempNestedScrollConsumed == null)
                        mTempNestedScrollConsumed = new int[2];
                    ai2 = mTempNestedScrollConsumed;
                }
                ai2[0] = 0;
                ai2[1] = 0;
                mNestedScrollingParent.onNestedPreScroll(this, i, j, ai2);
                if(ai1 != null)
                {
                    getLocationInWindow(ai1);
                    ai1[0] = ai1[0] - k;
                    ai1[1] = ai1[1] - l;
                }
                boolean flag1 = flag;
                if(ai2[0] == 0)
                    if(ai2[1] != 0)
                        flag1 = flag;
                    else
                        flag1 = false;
                return flag1;
            }
            if(ai1 != null)
            {
                ai1[0] = 0;
                ai1[1] = 0;
            }
        }
        return false;
    }

    public boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[])
    {
        if(isNestedScrollingEnabled() && mNestedScrollingParent != null)
        {
            while(i != 0 || j != 0 || k != 0 || l != 0) 
            {
                int i1 = 0;
                int j1 = 0;
                if(ai != null)
                {
                    getLocationInWindow(ai);
                    i1 = ai[0];
                    j1 = ai[1];
                }
                mNestedScrollingParent.onNestedScroll(this, i, j, k, l);
                if(ai != null)
                {
                    getLocationInWindow(ai);
                    ai[0] = ai[0] - i1;
                    ai[1] = ai[1] - j1;
                }
                return true;
            }
            if(ai != null)
            {
                ai[0] = 0;
                ai[1] = 0;
            }
        }
        return false;
    }

    public void dispatchPointerCaptureChanged(boolean flag)
    {
        onPointerCaptureChange(flag);
    }

    public final boolean dispatchPointerEvent(MotionEvent motionevent)
    {
        if(motionevent.isTouchEvent())
        {
            dispatchTouchEventToContentCatcher(motionevent);
            return dispatchTouchEvent(motionevent);
        } else
        {
            return dispatchGenericMotionEvent(motionevent);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if(mAccessibilityDelegate != null)
            return mAccessibilityDelegate.dispatchPopulateAccessibilityEvent(this, accessibilityevent);
        else
            return dispatchPopulateAccessibilityEventInternal(accessibilityevent);
    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        onPopulateAccessibilityEvent(accessibilityevent);
        return false;
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        dispatchProvideStructureForAssistOrAutofill(viewstructure, true, i);
    }

    public void dispatchProvideStructure(ViewStructure viewstructure)
    {
        dispatchProvideStructureForAssistOrAutofill(viewstructure, false, 0);
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        if(mID != -1)
        {
            sparsearray = (Parcelable)sparsearray.get(mID);
            if(sparsearray != null)
            {
                mPrivateFlags = mPrivateFlags & 0xfffdffff;
                onRestoreInstanceState(sparsearray);
                if((mPrivateFlags & 0x20000) == 0)
                    throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
            }
        }
    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        if(mID != -1 && (mViewFlags & 0x10000) == 0)
        {
            mPrivateFlags = mPrivateFlags & 0xfffdffff;
            Parcelable parcelable = onSaveInstanceState();
            if((mPrivateFlags & 0x20000) == 0)
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            if(parcelable != null)
                sparsearray.put(mID, parcelable);
        }
    }

    void dispatchScreenStateChanged(int i)
    {
        onScreenStateChanged(i);
    }

    protected void dispatchSetActivated(boolean flag)
    {
    }

    protected void dispatchSetPressed(boolean flag)
    {
    }

    protected void dispatchSetSelected(boolean flag)
    {
    }

    public void dispatchStartTemporaryDetach()
    {
        mPrivateFlags3 = mPrivateFlags3 | 0x2000000;
        notifyEnterOrExitForAutoFillIfNeeded(false);
        onStartTemporaryDetach();
    }

    public void dispatchSystemUiVisibilityChanged(int i)
    {
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null && ListenerInfo._2D_get6(listenerinfo) != null)
            ListenerInfo._2D_get6(listenerinfo).onSystemUiVisibilityChange(i & 0x3ff7);
    }

    boolean dispatchTooltipHoverEvent(MotionEvent motionevent)
    {
        if(mTooltipInfo == null)
            return false;
        motionevent.getAction();
        JVM INSTR tableswitch 7 10: default 44
    //                   7 46
    //                   8 44
    //                   9 44
    //                   10 191;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        return false;
_L2:
        if((mViewFlags & 0x40000000) == 0x40000000 && (mViewFlags & 0x20) == 0)
        {
            if(!mTooltipInfo.mTooltipFromLongClick)
            {
                if(mTooltipInfo.mTooltipPopup == null)
                {
                    mTooltipInfo.mAnchorX = (int)motionevent.getX();
                    mTooltipInfo.mAnchorY = (int)motionevent.getY();
                    removeCallbacks(mTooltipInfo.mShowTooltipRunnable);
                    postDelayed(mTooltipInfo.mShowTooltipRunnable, ViewConfiguration.getHoverTooltipShowTimeout());
                }
                int i;
                if((getWindowSystemUiVisibility() & 1) == 1)
                    i = ViewConfiguration.getHoverTooltipHideShortTimeout();
                else
                    i = ViewConfiguration.getHoverTooltipHideTimeout();
                removeCallbacks(mTooltipInfo.mHideTooltipRunnable);
                postDelayed(mTooltipInfo.mHideTooltipRunnable, i);
            }
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!mTooltipInfo.mTooltipFromLongClick)
            hideTooltip();
        if(true) goto _L1; else goto _L4
_L4:
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        int i;
        if(motionevent.isTargetAccessibilityFocus())
        {
            if(!isAccessibilityFocusedViewOrHost())
                return false;
            motionevent.setTargetAccessibilityFocus(false);
        }
        flag = false;
        boolean flag1 = false;
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onTouchEvent(motionevent, 0);
        i = motionevent.getActionMasked();
        if(i == 0)
        {
            SeempLog.record(3);
            stopNestedScroll();
        }
        if(onFilterTouchEventForSecurity(motionevent))
        {
            flag = flag1;
            if((mViewFlags & 0x20) == 0)
            {
                flag = flag1;
                if(handleScrollBarDragging(motionevent))
                    flag = true;
            }
            ListenerInfo listenerinfo = mListenerInfo;
            flag1 = flag;
            if(listenerinfo != null)
            {
                flag1 = flag;
                if(ListenerInfo._2D_get7(listenerinfo) != null)
                {
                    flag1 = flag;
                    if((mViewFlags & 0x20) == 0)
                    {
                        flag1 = flag;
                        if(ListenerInfo._2D_get7(listenerinfo).onTouch(this, motionevent))
                            flag1 = true;
                    }
                }
            }
            flag = flag1;
            if(!flag1)
            {
                flag = flag1;
                if(onTouchEvent(motionevent))
                    flag = true;
            }
        }
        if(!flag && mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onUnhandledEvent(motionevent, 0);
        break MISSING_BLOCK_LABEL_185;
        if(i == 1 || i == 3 || i == 0 && flag ^ true)
            stopNestedScroll();
        return flag;
    }

    public final void dispatchTouchEventToContentCatcher(MotionEvent motionevent)
    {
        Activity activity = getAttachedActivityInstance();
        if(activity != null)
        {
            IInterceptor iinterceptor = activity.getInterceptor();
            if(iinterceptor != null)
                iinterceptor.dispatchTouchEvent(motionevent, this, activity);
        }
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onTrackballEvent(motionevent, 0);
        return onTrackballEvent(motionevent);
    }

    public boolean dispatchUnhandledMove(View view, int i)
    {
        return false;
    }

    boolean dispatchVisibilityAggregated(boolean flag)
    {
        boolean flag1;
        if(getVisibility() == 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 || flag ^ true)
            onVisibilityAggregated(flag);
        if(!flag1)
            flag = false;
        return flag;
    }

    protected void dispatchVisibilityChanged(View view, int i)
    {
        onVisibilityChanged(view, i);
    }

    public void dispatchWindowFocusChanged(boolean flag)
    {
        onWindowFocusChanged(flag);
    }

    public void dispatchWindowSystemUiVisiblityChanged(int i)
    {
        onWindowSystemUiVisibilityChanged(i);
    }

    public void dispatchWindowVisibilityChanged(int i)
    {
        onWindowVisibilityChanged(i);
    }

    public void draw(Canvas canvas)
    {
        int i = mPrivateFlags;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if((0x600000 & i) == 0x400000)
        {
            if(mAttachInfo != null)
                flag1 = mAttachInfo.mIgnoreDirtyState ^ true;
            else
                flag1 = true;
        } else
        {
            flag1 = false;
        }
        mPrivateFlags = 0xff9fffff & i | 0x20;
        if(!flag1)
            drawBackground(canvas);
        i = mViewFlags;
        if((i & 0x1000) != 0)
            flag2 = true;
        else
            flag2 = false;
        if((i & 0x2000) != 0)
            flag3 = true;
        else
            flag3 = false;
        if(!flag3 && flag2 ^ true)
        {
            if(!flag1)
                onDraw(canvas);
            dispatchDraw(canvas);
            drawAutofilledHighlight(canvas);
            if(mOverlay != null && mOverlay.isEmpty() ^ true)
                mOverlay.getOverlayView().dispatchDraw(canvas);
            onDrawForeground(canvas);
            drawDefaultFocusHighlight(canvas);
            if(debugDraw())
                debugDrawFocus(canvas);
            return;
        }
        boolean flag4 = false;
        boolean flag5 = false;
        int k = 0;
        int l = 0;
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        int i1 = mPaddingLeft;
        boolean flag6 = isPaddingOffsetRequired();
        i = i1;
        if(flag6)
            i = i1 + getLeftPaddingOffset();
        int j1 = mScrollX + i;
        int k1 = (mRight + j1) - mLeft - mPaddingRight - i;
        int l1 = mScrollY + getFadeTop(flag6);
        i = l1 + getFadeHeight(flag6);
        int i2 = i;
        i1 = k1;
        if(flag6)
        {
            i1 = k1 + getRightPaddingOffset();
            i2 = i + getBottomPaddingOffset();
        }
        Object obj = mScrollCache;
        float f4 = ((ScrollabilityCache) (obj)).fadingEdgeLength;
        k1 = (int)f4;
        i = k1;
        if(flag3)
        {
            i = k1;
            if(l1 + k1 > i2 - k1)
                i = (i2 - l1) / 2;
        }
        k1 = i;
        if(flag2)
        {
            k1 = i;
            if(j1 + i > i1 - i)
                k1 = (i1 - j1) / 2;
        }
        boolean flag = flag5;
        if(flag3)
        {
            f = Math.max(0.0F, Math.min(1.0F, getTopFadingEdgeStrength()));
            Paint paint;
            Matrix matrix;
            if(f * f4 > 1.0F)
                flag3 = true;
            else
                flag3 = false;
            f1 = Math.max(0.0F, Math.min(1.0F, getBottomFadingEdgeStrength()));
            if(f1 * f4 > 1.0F)
            {
                flag = true;
                flag4 = flag3;
            } else
            {
                flag = false;
                flag4 = flag3;
            }
        }
        flag3 = l;
        if(flag2)
        {
            f2 = Math.max(0.0F, Math.min(1.0F, getLeftFadingEdgeStrength()));
            int j;
            if(f2 * f4 > 1.0F)
                j = 1;
            else
                j = 0;
            f3 = Math.max(0.0F, Math.min(1.0F, getRightFadingEdgeStrength()));
            if(f3 * f4 > 1.0F)
            {
                flag3 = true;
                k = j;
            } else
            {
                flag3 = false;
                k = j;
            }
        }
        j = canvas.getSaveCount();
        l = getSolidColor();
        if(l == 0)
        {
            if(flag4)
                canvas.saveLayer(j1, l1, i1, l1 + k1, null, 4);
            if(flag)
                canvas.saveLayer(j1, i2 - k1, i1, i2, null, 4);
            if(k != 0)
                canvas.saveLayer(j1, l1, j1 + k1, i2, null, 4);
            if(flag3)
                canvas.saveLayer(i1 - k1, l1, i1, i2, null, 4);
        } else
        {
            ((ScrollabilityCache) (obj)).setFadeColor(l);
        }
        if(!flag1)
            onDraw(canvas);
        dispatchDraw(canvas);
        paint = ((ScrollabilityCache) (obj)).paint;
        matrix = ((ScrollabilityCache) (obj)).matrix;
        obj = ((ScrollabilityCache) (obj)).shader;
        if(flag4)
        {
            matrix.setScale(1.0F, f4 * f);
            matrix.postTranslate(j1, l1);
            ((Shader) (obj)).setLocalMatrix(matrix);
            paint.setShader(((Shader) (obj)));
            canvas.drawRect(j1, l1, i1, l1 + k1, paint);
        }
        if(flag)
        {
            matrix.setScale(1.0F, f4 * f1);
            matrix.postRotate(180F);
            matrix.postTranslate(j1, i2);
            ((Shader) (obj)).setLocalMatrix(matrix);
            paint.setShader(((Shader) (obj)));
            canvas.drawRect(j1, i2 - k1, i1, i2, paint);
        }
        if(k != 0)
        {
            matrix.setScale(1.0F, f4 * f2);
            matrix.postRotate(-90F);
            matrix.postTranslate(j1, l1);
            ((Shader) (obj)).setLocalMatrix(matrix);
            paint.setShader(((Shader) (obj)));
            canvas.drawRect(j1, l1, j1 + k1, i2, paint);
        }
        if(flag3)
        {
            matrix.setScale(1.0F, f4 * f3);
            matrix.postRotate(90F);
            matrix.postTranslate(i1, l1);
            ((Shader) (obj)).setLocalMatrix(matrix);
            paint.setShader(((Shader) (obj)));
            canvas.drawRect(i1 - k1, l1, i1, i2, paint);
        }
        canvas.restoreToCount(j);
        drawAutofilledHighlight(canvas);
        if(mOverlay != null && mOverlay.isEmpty() ^ true)
            mOverlay.getOverlayView().dispatchDraw(canvas);
        onDrawForeground(canvas);
        if(debugDraw())
            debugDrawFocus(canvas);
    }

    boolean draw(Canvas canvas, ViewGroup viewgroup, long l)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        int i;
        Object obj;
        boolean flag4;
        boolean flag5;
        Animation animation;
        boolean flag6;
        boolean flag7;
        Transformation transformation;
        boolean flag9;
        flag = canvas.isHardwareAccelerated();
        if(mAttachInfo != null && mAttachInfo.mHardwareAccelerated)
            flag1 = flag;
        else
            flag1 = false;
        flag2 = false;
        flag3 = hasIdentityMatrix();
        i = viewgroup.mGroupFlags;
        if((i & 0x100) != 0)
        {
            viewgroup.getChildTransformation().clear();
            viewgroup.mGroupFlags = viewgroup.mGroupFlags & 0xfffffeff;
        }
        obj = null;
        flag4 = false;
        if(mAttachInfo != null)
            flag5 = mAttachInfo.mScalingRequired;
        else
            flag5 = false;
        animation = getAnimation();
        if(animation == null) goto _L2; else goto _L1
_L1:
        flag6 = applyLegacyAnimation(viewgroup, l, animation, flag5);
        flag7 = animation.willChangeTransformationMatrix();
        if(flag7)
            mPrivateFlags3 = mPrivateFlags3 | 1;
        transformation = viewgroup.getChildTransformation();
_L4:
        flag9 = flag7 | flag3 ^ true;
        mPrivateFlags = mPrivateFlags | 0x20;
        if(!flag9 && (i & 0x801) == 1 && canvas.quickReject(mLeft, mTop, mRight, mBottom, android.graphics.Canvas.EdgeType.BW) && (mPrivateFlags & 0x40) == 0)
        {
            mPrivateFlags2 = mPrivateFlags2 | 0x10000000;
            return flag6;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if((mPrivateFlags3 & 1) != 0)
        {
            mRenderNode.setAnimationMatrix(null);
            mPrivateFlags3 = mPrivateFlags3 & -2;
        }
        flag7 = flag4;
        flag6 = flag2;
        transformation = obj;
        if(!flag1)
        {
            flag7 = flag4;
            flag6 = flag2;
            transformation = obj;
            if((i & 0x800) != 0)
            {
                Transformation transformation1 = viewgroup.getChildTransformation();
                flag7 = flag4;
                flag6 = flag2;
                transformation = obj;
                if(viewgroup.getChildStaticTransformation(this, transformation1))
                {
                    int j = transformation1.getTransformationType();
                    if(j != 0)
                        transformation = transformation1;
                    else
                        transformation = null;
                    if((j & 2) != 0)
                    {
                        flag7 = true;
                        flag6 = flag2;
                    } else
                    {
                        flag7 = false;
                        flag6 = flag2;
                    }
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        Bitmap bitmap;
        int k;
        int j1;
        int k1;
        int l1;
        float f;
        float f2;
        boolean flag8;
        boolean flag10;
        boolean flag11;
        int i2;
label0:
        {
            mPrivateFlags2 = mPrivateFlags2 & 0xefffffff;
            if(flag)
            {
                RenderNode rendernode;
                RenderNode rendernode1;
                if((mPrivateFlags & 0x80000000) != 0)
                    flag8 = true;
                else
                    flag8 = false;
                mRecreateDisplayList = flag8;
                mPrivateFlags = mPrivateFlags & 0x7fffffff;
            }
            rendernode = null;
            bitmap = null;
            j1 = getLayerType();
            if(j1 != 1)
            {
                k1 = j1;
                if(!(flag1 ^ true))
                    break label0;
            }
            k = j1;
            if(j1 != 0)
            {
                k = 1;
                buildDrawingCache(true);
            }
            bitmap = getDrawingCache(true);
            k1 = k;
        }
        flag8 = flag1;
        if(flag1)
        {
            rendernode1 = updateDisplayListIfDirty();
            flag8 = flag1;
            rendernode = rendernode1;
            if(!rendernode1.isValid())
            {
                rendernode = null;
                flag8 = false;
            }
        }
        j1 = 0;
        l1 = 0;
        if(!flag8)
        {
            computeScroll();
            j1 = mScrollX;
            l1 = mScrollY;
        }
        if(bitmap != null)
            flag10 = flag8 ^ true;
        else
            flag10 = false;
        if(bitmap == null)
            flag11 = flag8 ^ true;
        else
            flag11 = false;
        k = -1;
        if(!flag8 || transformation != null)
            k = canvas.save();
        if(flag11)
        {
            canvas.translate(mLeft - j1, mTop - l1);
            i2 = k;
        } else
        {
            if(!flag8)
                canvas.translate(mLeft, mTop);
            i2 = k;
            if(flag5)
            {
                if(flag8)
                    k = canvas.save();
                f = 1.0F / mAttachInfo.mApplicationScale;
                canvas.scale(f, f);
                i2 = k;
            }
        }
        if(flag8)
            f = 1.0F;
        else
            f = getAlpha() * getTransitionAlpha();
        break MISSING_BLOCK_LABEL_637;
        if(transformation != null || f < 1.0F || hasIdentityMatrix() ^ true || (mPrivateFlags3 & 2) != 0)
        {
            float f1;
label1:
            {
                if(transformation == null)
                {
                    f1 = f;
                    if(!(flag3 ^ true))
                        break label1;
                }
                k = 0;
                int j2 = 0;
                if(flag11)
                {
                    k = -j1;
                    j2 = -l1;
                }
                f2 = f;
                if(transformation != null)
                {
                    if(flag9)
                    {
                        if(flag8)
                        {
                            rendernode.setAnimationMatrix(transformation.getMatrix());
                        } else
                        {
                            canvas.translate(-k, -j2);
                            canvas.concat(transformation.getMatrix());
                            canvas.translate(k, j2);
                        }
                        viewgroup.mGroupFlags = viewgroup.mGroupFlags | 0x100;
                    }
                    f1 = transformation.getAlpha();
                    f2 = f;
                    if(f1 < 1.0F)
                    {
                        f2 = f * f1;
                        viewgroup.mGroupFlags = viewgroup.mGroupFlags | 0x100;
                    }
                }
                f1 = f2;
                if(!flag3)
                {
                    f1 = f2;
                    if(flag8 ^ true)
                    {
                        canvas.translate(-k, -j2);
                        canvas.concat(getMatrix());
                        canvas.translate(k, j2);
                        f1 = f2;
                    }
                }
            }
label2:
            {
                if(f1 >= 1.0F)
                {
                    f2 = f1;
                    if((mPrivateFlags3 & 2) == 0)
                        break label2;
                }
                if(f1 < 1.0F)
                    mPrivateFlags3 = mPrivateFlags3 | 2;
                else
                    mPrivateFlags3 = mPrivateFlags3 & -3;
                viewgroup.mGroupFlags = viewgroup.mGroupFlags | 0x100;
                f2 = f1;
                if(!flag10)
                {
                    k = (int)(255F * f1);
                    if(!onSetAlpha(k))
                    {
                        if(flag8)
                        {
                            rendernode.setAlpha(getAlpha() * f1 * getTransitionAlpha());
                            f2 = f1;
                        } else
                        {
                            f2 = f1;
                            if(k1 == 0)
                            {
                                canvas.saveLayerAlpha(j1, l1, getWidth() + j1, getHeight() + l1, k);
                                f2 = f1;
                            }
                        }
                    } else
                    {
                        mPrivateFlags = mPrivateFlags | 0x40000;
                        f2 = f1;
                    }
                }
            }
        } else
        {
            f2 = f;
            if((mPrivateFlags & 0x40000) == 0x40000)
            {
                onSetAlpha(255);
                mPrivateFlags = mPrivateFlags & 0xfffbffff;
                f2 = f;
            }
        }
        if(!flag8)
        {
            if((i & 1) != 0 && bitmap == null)
                if(flag11)
                    canvas.clipRect(j1, l1, getWidth() + j1, getHeight() + l1);
                else
                if(!flag5 || bitmap == null)
                    canvas.clipRect(0, 0, getWidth(), getHeight());
                else
                    canvas.clipRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            if(mClipBounds != null)
                canvas.clipRect(mClipBounds);
        }
        if(!flag10)
        {
            if(flag8)
            {
                mPrivateFlags = mPrivateFlags & 0xff9fffff;
                ((DisplayListCanvas)canvas).drawRenderNode(rendernode);
            } else
            if((mPrivateFlags & 0x80) == 128)
            {
                mPrivateFlags = mPrivateFlags & 0xff9fffff;
                dispatchDraw(canvas);
            } else
            {
                draw(canvas);
            }
        } else
        if(bitmap != null)
        {
            mPrivateFlags = mPrivateFlags & 0xff9fffff;
            if(k1 == 0 || mLayerPaint == null)
            {
                Paint paint = viewgroup.mCachePaint;
                Paint paint1 = paint;
                if(paint == null)
                {
                    paint1 = new Paint();
                    paint1.setDither(false);
                    viewgroup.mCachePaint = paint1;
                }
                paint1.setAlpha((int)(255F * f2));
                canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint1);
            } else
            {
                int i1 = mLayerPaint.getAlpha();
                if(f2 < 1.0F)
                    mLayerPaint.setAlpha((int)((float)i1 * f2));
                canvas.drawBitmap(bitmap, 0.0F, 0.0F, mLayerPaint);
                if(f2 < 1.0F)
                    mLayerPaint.setAlpha(i1);
            }
        }
        if(i2 >= 0)
            canvas.restoreToCount(i2);
        if(animation != null && flag6 ^ true)
        {
            if(!flag && animation.getFillAfter() ^ true)
                onSetAlpha(255);
            viewgroup.finishAnimatingView(this, animation);
        }
        if(flag6 && flag && animation.hasAlpha() && (mPrivateFlags & 0x40000) == 0x40000)
            invalidate(true);
        mRecreateDisplayList = false;
        return flag6;
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        if(mBackground != null)
            mBackground.setHotspot(f, f1);
        if(mDefaultFocusHighlight != null)
            mDefaultFocusHighlight.setHotspot(f, f1);
        if(mForegroundInfo != null && ForegroundInfo._2D_get1(mForegroundInfo) != null)
            ForegroundInfo._2D_get1(mForegroundInfo).setHotspot(f, f1);
        dispatchDrawableHotspotChanged(f, f1);
    }

    protected void drawableStateChanged()
    {
        boolean flag = false;
        int ai[] = getDrawableState();
        boolean flag1 = false;
        Object obj = mBackground;
        boolean flag2 = flag1;
        if(obj != null)
        {
            flag2 = flag1;
            if(((Drawable) (obj)).isStateful())
                flag2 = ((Drawable) (obj)).setState(ai);
        }
        obj = mDefaultFocusHighlight;
        flag1 = flag2;
        if(obj != null)
        {
            flag1 = flag2;
            if(((Drawable) (obj)).isStateful())
                flag1 = flag2 | ((Drawable) (obj)).setState(ai);
        }
        if(mForegroundInfo != null)
            obj = ForegroundInfo._2D_get1(mForegroundInfo);
        else
            obj = null;
        flag2 = flag1;
        if(obj != null)
        {
            flag2 = flag1;
            if(((Drawable) (obj)).isStateful())
                flag2 = flag1 | ((Drawable) (obj)).setState(ai);
        }
        flag1 = flag2;
        if(mScrollCache != null)
        {
            obj = mScrollCache.scrollBar;
            flag1 = flag2;
            if(obj != null)
            {
                flag1 = flag2;
                if(((Drawable) (obj)).isStateful())
                {
                    boolean flag3 = flag;
                    if(((Drawable) (obj)).setState(ai))
                    {
                        flag3 = flag;
                        if(mScrollCache.state != 0)
                            flag3 = true;
                    }
                    flag1 = flag2 | flag3;
                }
            }
        }
        if(mStateListAnimator != null)
            mStateListAnimator.setState(ai);
        if(flag1)
            invalidate();
    }

    public void encode(ViewHierarchyEncoder viewhierarchyencoder)
    {
        viewhierarchyencoder.beginObject(this);
        encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.endObject();
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        Object obj = ViewDebug.resolveId(getContext(), mID);
        float f;
        int i;
        if(obj instanceof String)
            viewhierarchyencoder.addProperty("id", (String)obj);
        else
            viewhierarchyencoder.addProperty("id", mID);
        if(mTransformationInfo != null)
            f = mTransformationInfo.mAlpha;
        else
            f = 0.0F;
        viewhierarchyencoder.addProperty("misc:transformation.alpha", f);
        viewhierarchyencoder.addProperty("misc:transitionName", getTransitionName());
        viewhierarchyencoder.addProperty("layout:left", mLeft);
        viewhierarchyencoder.addProperty("layout:right", mRight);
        viewhierarchyencoder.addProperty("layout:top", mTop);
        viewhierarchyencoder.addProperty("layout:bottom", mBottom);
        viewhierarchyencoder.addProperty("layout:width", getWidth());
        viewhierarchyencoder.addProperty("layout:height", getHeight());
        viewhierarchyencoder.addProperty("layout:layoutDirection", getLayoutDirection());
        viewhierarchyencoder.addProperty("layout:layoutRtl", isLayoutRtl());
        viewhierarchyencoder.addProperty("layout:hasTransientState", hasTransientState());
        viewhierarchyencoder.addProperty("layout:baseline", getBaseline());
        obj = getLayoutParams();
        if(obj != null)
        {
            viewhierarchyencoder.addPropertyKey("layoutParams");
            ((ViewGroup.LayoutParams) (obj)).encode(viewhierarchyencoder);
        }
        viewhierarchyencoder.addProperty("scrolling:scrollX", mScrollX);
        viewhierarchyencoder.addProperty("scrolling:scrollY", mScrollY);
        viewhierarchyencoder.addProperty("padding:paddingLeft", mPaddingLeft);
        viewhierarchyencoder.addProperty("padding:paddingRight", mPaddingRight);
        viewhierarchyencoder.addProperty("padding:paddingTop", mPaddingTop);
        viewhierarchyencoder.addProperty("padding:paddingBottom", mPaddingBottom);
        viewhierarchyencoder.addProperty("padding:userPaddingRight", mUserPaddingRight);
        viewhierarchyencoder.addProperty("padding:userPaddingLeft", mUserPaddingLeft);
        viewhierarchyencoder.addProperty("padding:userPaddingBottom", mUserPaddingBottom);
        viewhierarchyencoder.addProperty("padding:userPaddingStart", mUserPaddingStart);
        viewhierarchyencoder.addProperty("padding:userPaddingEnd", mUserPaddingEnd);
        viewhierarchyencoder.addProperty("measurement:minHeight", mMinHeight);
        viewhierarchyencoder.addProperty("measurement:minWidth", mMinWidth);
        viewhierarchyencoder.addProperty("measurement:measuredWidth", mMeasuredWidth);
        viewhierarchyencoder.addProperty("measurement:measuredHeight", mMeasuredHeight);
        viewhierarchyencoder.addProperty("drawing:elevation", getElevation());
        viewhierarchyencoder.addProperty("drawing:translationX", getTranslationX());
        viewhierarchyencoder.addProperty("drawing:translationY", getTranslationY());
        viewhierarchyencoder.addProperty("drawing:translationZ", getTranslationZ());
        viewhierarchyencoder.addProperty("drawing:rotation", getRotation());
        viewhierarchyencoder.addProperty("drawing:rotationX", getRotationX());
        viewhierarchyencoder.addProperty("drawing:rotationY", getRotationY());
        viewhierarchyencoder.addProperty("drawing:scaleX", getScaleX());
        viewhierarchyencoder.addProperty("drawing:scaleY", getScaleY());
        viewhierarchyencoder.addProperty("drawing:pivotX", getPivotX());
        viewhierarchyencoder.addProperty("drawing:pivotY", getPivotY());
        viewhierarchyencoder.addProperty("drawing:opaque", isOpaque());
        viewhierarchyencoder.addProperty("drawing:alpha", getAlpha());
        viewhierarchyencoder.addProperty("drawing:transitionAlpha", getTransitionAlpha());
        viewhierarchyencoder.addProperty("drawing:shadow", hasShadow());
        viewhierarchyencoder.addProperty("drawing:solidColor", getSolidColor());
        viewhierarchyencoder.addProperty("drawing:layerType", mLayerType);
        viewhierarchyencoder.addProperty("drawing:willNotDraw", willNotDraw());
        viewhierarchyencoder.addProperty("drawing:hardwareAccelerated", isHardwareAccelerated());
        viewhierarchyencoder.addProperty("drawing:willNotCacheDrawing", willNotCacheDrawing());
        viewhierarchyencoder.addProperty("drawing:drawingCacheEnabled", isDrawingCacheEnabled());
        viewhierarchyencoder.addProperty("drawing:overlappingRendering", hasOverlappingRendering());
        viewhierarchyencoder.addProperty("focus:hasFocus", hasFocus());
        viewhierarchyencoder.addProperty("focus:isFocused", isFocused());
        viewhierarchyencoder.addProperty("focus:focusable", getFocusable());
        viewhierarchyencoder.addProperty("focus:isFocusable", isFocusable());
        viewhierarchyencoder.addProperty("focus:isFocusableInTouchMode", isFocusableInTouchMode());
        viewhierarchyencoder.addProperty("misc:clickable", isClickable());
        viewhierarchyencoder.addProperty("misc:pressed", isPressed());
        viewhierarchyencoder.addProperty("misc:selected", isSelected());
        viewhierarchyencoder.addProperty("misc:touchMode", isInTouchMode());
        viewhierarchyencoder.addProperty("misc:hovered", isHovered());
        viewhierarchyencoder.addProperty("misc:activated", isActivated());
        viewhierarchyencoder.addProperty("misc:visibility", getVisibility());
        viewhierarchyencoder.addProperty("misc:fitsSystemWindows", getFitsSystemWindows());
        viewhierarchyencoder.addProperty("misc:filterTouchesWhenObscured", getFilterTouchesWhenObscured());
        viewhierarchyencoder.addProperty("misc:enabled", isEnabled());
        viewhierarchyencoder.addProperty("misc:soundEffectsEnabled", isSoundEffectsEnabled());
        viewhierarchyencoder.addProperty("misc:hapticFeedbackEnabled", isHapticFeedbackEnabled());
        obj = getContext().getTheme();
        if(obj != null)
        {
            viewhierarchyencoder.addPropertyKey("theme");
            ((android.content.res.Resources.Theme) (obj)).encode(viewhierarchyencoder);
        }
        if(mAttributes != null)
            i = mAttributes.length;
        else
            i = 0;
        viewhierarchyencoder.addProperty("meta:__attrCount__", i / 2);
        for(int j = 0; j < i; j += 2)
            viewhierarchyencoder.addProperty((new StringBuilder()).append("meta:__attr__").append(mAttributes[j]).toString(), mAttributes[j + 1]);

        viewhierarchyencoder.addProperty("misc:scrollBarStyle", getScrollBarStyle());
        viewhierarchyencoder.addProperty("text:textDirection", getTextDirection());
        viewhierarchyencoder.addProperty("text:textAlignment", getTextAlignment());
        obj = getContentDescription();
        if(obj == null)
            obj = "";
        else
            obj = ((CharSequence) (obj)).toString();
        viewhierarchyencoder.addProperty("accessibility:contentDescription", ((String) (obj)));
        viewhierarchyencoder.addProperty("accessibility:labelFor", getLabelFor());
        viewhierarchyencoder.addProperty("accessibility:importantForAccessibility", getImportantForAccessibility());
    }

    void ensureTransformationInfo()
    {
        if(mTransformationInfo == null)
            mTransformationInfo = new TransformationInfo();
    }

    public View findFocus()
    {
        View view;
        if((mPrivateFlags & 2) != 0)
            view = this;
        else
            view = null;
        return view;
    }

    View findKeyboardNavigationCluster()
    {
        if(mParent instanceof View)
        {
            View view = ((View)mParent).findKeyboardNavigationCluster();
            if(view != null)
                return view;
            if(isKeyboardNavigationCluster())
                return this;
        }
        return null;
    }

    public void findNamedViews(Map map)
    {
        if(getVisibility() == 0 || mGhostView != null)
        {
            String s = getTransitionName();
            if(s != null)
                map.put(s, this);
        }
    }

    View findUserSetNextFocus(View view, int i)
    {
        switch(i)
        {
        default:
            return null;

        case 17: // '\021'
            if(mNextFocusLeftId == -1)
                return null;
            else
                return findViewInsideOutShouldExist(view, mNextFocusLeftId);

        case 66: // 'B'
            if(mNextFocusRightId == -1)
                return null;
            else
                return findViewInsideOutShouldExist(view, mNextFocusRightId);

        case 33: // '!'
            if(mNextFocusUpId == -1)
                return null;
            else
                return findViewInsideOutShouldExist(view, mNextFocusUpId);

        case 130: 
            if(mNextFocusDownId == -1)
                return null;
            else
                return findViewInsideOutShouldExist(view, mNextFocusDownId);

        case 2: // '\002'
            if(mNextFocusForwardId == -1)
                return null;
            else
                return findViewInsideOutShouldExist(view, mNextFocusForwardId);

        case 1: // '\001'
            break;
        }
        if(mID == -1)
            return null;
        else
            return view.findViewByPredicateInsideOut(this, new Predicate() {

                public boolean test(View view1)
                {
                    boolean flag;
                    if(view1.mNextFocusForwardId == id)
                        flag = true;
                    else
                        flag = false;
                    return flag;
                }

                public volatile boolean test(Object obj)
                {
                    return test((View)obj);
                }

                final View this$0;
                final int val$id;

            
            {
                this$0 = View.this;
                id = i;
                super();
            }
            }
);
    }

    View findUserSetNextKeyboardNavigationCluster(View view, int i)
    {
        switch(i)
        {
        default:
            return null;

        case 2: // '\002'
            if(mNextClusterForwardId == -1)
                return null;
            else
                return findViewInsideOutShouldExist(view, mNextClusterForwardId);

        case 1: // '\001'
            break;
        }
        if(mID == -1)
            return null;
        else
            return view.findViewByPredicateInsideOut(this, new _.Lambda.iU_USrtPm1XIm5H9QYQvXfBGDE4(mID));
    }

    final View findViewByAccessibilityId(int i)
    {
        if(i < 0)
            return null;
        View view = findViewByAccessibilityIdTraversal(i);
        if(view != null)
        {
            if(!view.includeForAccessibility())
                view = null;
            return view;
        } else
        {
            return null;
        }
    }

    public View findViewByAccessibilityIdTraversal(int i)
    {
        if(getAccessibilityViewId() == i)
            return this;
        else
            return null;
    }

    public View findViewByAutofillIdTraversal(int i)
    {
        if(getAutofillViewId() == i)
            return this;
        else
            return null;
    }

    public final View findViewById(int i)
    {
        if(i == -1)
            return null;
        else
            return findViewTraversal(i);
    }

    public final View findViewByPredicate(Predicate predicate)
    {
        return findViewByPredicateTraversal(predicate, null);
    }

    public final View findViewByPredicateInsideOut(View view, Predicate predicate)
    {
        Object obj = null;
        do
        {
            obj = view.findViewByPredicateTraversal(predicate, ((View) (obj)));
            if(obj != null || view == this)
                return ((View) (obj));
            obj = view.getParent();
            if(obj == null || (obj instanceof View) ^ true)
                return null;
            View view1 = (View)obj;
            obj = view;
            view = view1;
        } while(true);
    }

    protected View findViewByPredicateTraversal(Predicate predicate, View view)
    {
        if(predicate.test(this))
            return this;
        else
            return null;
    }

    protected View findViewTraversal(int i)
    {
        if(i == mID)
            return this;
        else
            return null;
    }

    public final View findViewWithTag(Object obj)
    {
        if(obj == null)
            return null;
        else
            return findViewWithTagTraversal(obj);
    }

    protected View findViewWithTagTraversal(Object obj)
    {
        if(obj != null && obj.equals(mTag))
            return this;
        else
            return null;
    }

    public void findViewsWithText(ArrayList arraylist, CharSequence charsequence, int i)
    {
        if(getAccessibilityNodeProvider() == null) goto _L2; else goto _L1
_L1:
        if((i & 4) != 0)
            arraylist.add(this);
_L4:
        return;
_L2:
        if((i & 2) != 0 && charsequence != null && charsequence.length() > 0 && mContentDescription != null && mContentDescription.length() > 0)
        {
            charsequence = charsequence.toString().toLowerCase();
            if(mContentDescription.toString().toLowerCase().contains(charsequence))
                arraylist.add(this);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected boolean fitSystemWindows(Rect rect)
    {
        if((mPrivateFlags3 & 0x20) != 0)
            break MISSING_BLOCK_LABEL_72;
        if(rect == null)
            return false;
        boolean flag;
        mPrivateFlags3 = mPrivateFlags3 | 0x40;
        WindowInsets windowinsets = JVM INSTR new #3660 <Class WindowInsets>;
        windowinsets.WindowInsets(rect);
        flag = dispatchApplyWindowInsets(windowinsets).isConsumed();
        mPrivateFlags3 = mPrivateFlags3 & 0xffffffbf;
        return flag;
        rect;
        mPrivateFlags3 = mPrivateFlags3 & 0xffffffbf;
        throw rect;
        return fitSystemWindowsInt(rect);
    }

    public boolean fitsSystemWindows()
    {
        return getFitsSystemWindows();
    }

    public View focusSearch(int i)
    {
        if(mParent != null)
            return mParent.focusSearch(this, i);
        else
            return null;
    }

    public void forceHasOverlappingRendering(boolean flag)
    {
        mPrivateFlags3 = mPrivateFlags3 | 0x1000000;
        if(flag)
            mPrivateFlags3 = mPrivateFlags3 | 0x800000;
        else
            mPrivateFlags3 = mPrivateFlags3 & 0xff7fffff;
    }

    public void forceLayout()
    {
        if(mMeasureCache != null)
            mMeasureCache.clear();
        mPrivateFlags = mPrivateFlags | 0x1000;
        mPrivateFlags = mPrivateFlags | 0x80000000;
    }

    public boolean gatherTransparentRegion(Region region)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(region == null || attachinfo == null) goto _L2; else goto _L1
_L1:
        if((mPrivateFlags & 0x80) != 0) goto _L4; else goto _L3
_L3:
        int ai[] = attachinfo.mTransparentLocation;
        getLocationInWindow(ai);
        int i;
        if(getZ() > 0.0F)
            i = (int)getZ();
        else
            i = 0;
        region.op(ai[0] - i, ai[1] - i, ((ai[0] + mRight) - mLeft) + i, i * 3 + ((ai[1] + mBottom) - mTop), android.graphics.Region.Op.DIFFERENCE);
_L2:
        return true;
_L4:
        if(mBackground != null && mBackground.getOpacity() != -2)
            applyDrawableToTransparentRegion(mBackground, region);
        if(mForegroundInfo != null && ForegroundInfo._2D_get1(mForegroundInfo) != null && ForegroundInfo._2D_get1(mForegroundInfo).getOpacity() != -2)
            applyDrawableToTransparentRegion(ForegroundInfo._2D_get1(mForegroundInfo), region);
        if(mDefaultFocusHighlight != null && mDefaultFocusHighlight.getOpacity() != -2)
            applyDrawableToTransparentRegion(mDefaultFocusHighlight, region);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/view/View.getName();
    }

    public AccessibilityDelegate getAccessibilityDelegate()
    {
        return mAccessibilityDelegate;
    }

    public int getAccessibilityLiveRegion()
    {
        return (mPrivateFlags2 & 0x1800000) >> 23;
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider()
    {
        if(mAccessibilityDelegate != null)
            return mAccessibilityDelegate.getAccessibilityNodeProvider(this);
        else
            return null;
    }

    public int getAccessibilitySelectionEnd()
    {
        return getAccessibilitySelectionStart();
    }

    public int getAccessibilitySelectionStart()
    {
        return mAccessibilityCursorPosition;
    }

    public int getAccessibilityTraversalAfter()
    {
        return mAccessibilityTraversalAfterId;
    }

    public int getAccessibilityTraversalBefore()
    {
        return mAccessibilityTraversalBeforeId;
    }

    public int getAccessibilityViewId()
    {
        if(mAccessibilityViewId == -1)
        {
            int i = sNextAccessibilityViewId;
            sNextAccessibilityViewId = i + 1;
            mAccessibilityViewId = i;
        }
        return mAccessibilityViewId;
    }

    public int getAccessibilityWindowId()
    {
        int i;
        if(mAttachInfo != null)
            i = mAttachInfo.mAccessibilityWindowId;
        else
            i = -1;
        return i;
    }

    public float getAlpha()
    {
        float f;
        if(mTransformationInfo != null)
            f = mTransformationInfo.mAlpha;
        else
            f = 1.0F;
        return f;
    }

    public Animation getAnimation()
    {
        return mCurrentAnimation;
    }

    public IBinder getApplicationWindowToken()
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
        {
            IBinder ibinder = attachinfo.mPanelParentWindowToken;
            IBinder ibinder1 = ibinder;
            if(ibinder == null)
                ibinder1 = attachinfo.mWindowToken;
            return ibinder1;
        } else
        {
            return null;
        }
    }

    protected Activity getAttachedActivity()
    {
        Context context = getContext();
        do
        {
            Context context1 = context;
            if(!(context1 instanceof Activity) && (context1 instanceof ContextWrapper))
            {
                Context context2 = ((ContextWrapper)context1).getBaseContext();
                context = context2;
                if(context1 == context2)
                    return null;
            } else
            if(context1 instanceof Activity)
                return (Activity)context1;
            else
                return null;
        } while(true);
    }

    public Activity getAttachedActivityInstance()
    {
        Object obj = null;
        if(!mFirst) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if(mFirst)
        {
            Activity activity = getAttachedActivity();
            WeakReference weakreference = JVM INSTR new #4809 <Class WeakReference>;
            weakreference.WeakReference(activity);
            mAttachedActivity = weakreference;
            mFirst = false;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        if(mAttachedActivity != null)
            obj = (Activity)mAttachedActivity.get();
        return ((Activity) (obj));
        obj;
        throw obj;
    }

    public String[] getAutofillHints()
    {
        return mAutofillHints;
    }

    public final AutofillId getAutofillId()
    {
        if(mAutofillId == null)
            mAutofillId = new AutofillId(getAutofillViewId());
        return mAutofillId;
    }

    public int getAutofillType()
    {
        return 0;
    }

    public AutofillValue getAutofillValue()
    {
        return null;
    }

    public int getAutofillViewId()
    {
        if(mAutofillViewId == -1)
            mAutofillViewId = mContext.getNextAutofillId();
        return mAutofillViewId;
    }

    public Drawable getBackground()
    {
        return mBackground;
    }

    public ColorStateList getBackgroundTintList()
    {
        ColorStateList colorstatelist = null;
        if(mBackgroundTint != null)
            colorstatelist = mBackgroundTint.mTintList;
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getBackgroundTintMode()
    {
        android.graphics.PorterDuff.Mode mode = null;
        if(mBackgroundTint != null)
            mode = mBackgroundTint.mTintMode;
        return mode;
    }

    public int getBaseline()
    {
        return -1;
    }

    public final int getBottom()
    {
        return mBottom;
    }

    protected float getBottomFadingEdgeStrength()
    {
        float f;
        if(computeVerticalScrollOffset() + computeVerticalScrollExtent() < computeVerticalScrollRange())
            f = 1.0F;
        else
            f = 0.0F;
        return f;
    }

    protected int getBottomPaddingOffset()
    {
        return 0;
    }

    public void getBoundsOnScreen(Rect rect)
    {
        getBoundsOnScreen(rect, false);
    }

    public void getBoundsOnScreen(Rect rect, boolean flag)
    {
        if(mAttachInfo == null)
        {
            return;
        } else
        {
            RectF rectf = mAttachInfo.mTmpTransformRect;
            rectf.set(0.0F, 0.0F, mRight - mLeft, mBottom - mTop);
            mapRectFromViewToScreenCoords(rectf, flag);
            rect.set(Math.round(rectf.left), Math.round(rectf.top), Math.round(rectf.right), Math.round(rectf.bottom));
            return;
        }
    }

    public float getCameraDistance()
    {
        float f = mResources.getDisplayMetrics().densityDpi;
        return -(mRenderNode.getCameraDistance() * f);
    }

    public Rect getClipBounds()
    {
        Rect rect = null;
        if(mClipBounds != null)
            rect = new Rect(mClipBounds);
        return rect;
    }

    public boolean getClipBounds(Rect rect)
    {
        if(mClipBounds != null)
        {
            rect.set(mClipBounds);
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean getClipToOutline()
    {
        return mRenderNode.getClipToOutline();
    }

    public CharSequence getContentDescription()
    {
        return mContentDescription;
    }

    public final Context getContext()
    {
        return mContext;
    }

    protected ContextMenu.ContextMenuInfo getContextMenuInfo()
    {
        return null;
    }

    public final boolean getDefaultFocusHighlightEnabled()
    {
        return mDefaultFocusHighlightEnabled;
    }

    public Display getDisplay()
    {
        Display display = null;
        if(mAttachInfo != null)
            display = mAttachInfo.mDisplay;
        return display;
    }

    public final int[] getDrawableState()
    {
        if(mDrawableState != null && (mPrivateFlags & 0x400) == 0)
        {
            return mDrawableState;
        } else
        {
            mDrawableState = onCreateDrawableState(0);
            mPrivateFlags = mPrivateFlags & 0xfffffbff;
            return mDrawableState;
        }
    }

    public Bitmap getDrawingCache()
    {
        return getDrawingCache(false);
    }

    public Bitmap getDrawingCache(boolean flag)
    {
        if((mViewFlags & 0x20000) == 0x20000)
            return null;
        if((mViewFlags & 0x8000) == 32768)
            buildDrawingCache(flag);
        Bitmap bitmap;
        if(flag)
            bitmap = mDrawingCache;
        else
            bitmap = mUnscaledDrawingCache;
        return bitmap;
    }

    public int getDrawingCacheBackgroundColor()
    {
        return mDrawingCacheBackgroundColor;
    }

    public int getDrawingCacheQuality()
    {
        return mViewFlags & 0x180000;
    }

    public void getDrawingRect(Rect rect)
    {
        rect.left = mScrollX;
        rect.top = mScrollY;
        rect.right = mScrollX + (mRight - mLeft);
        rect.bottom = mScrollY + (mBottom - mTop);
    }

    public long getDrawingTime()
    {
        long l;
        if(mAttachInfo != null)
            l = mAttachInfo.mDrawingTime;
        else
            l = 0L;
        return l;
    }

    public float getElevation()
    {
        return mRenderNode.getElevation();
    }

    protected int getFadeHeight(boolean flag)
    {
        int i = mPaddingTop;
        int j = i;
        if(flag)
            j = i + getTopPaddingOffset();
        return mBottom - mTop - mPaddingBottom - j;
    }

    protected int getFadeTop(boolean flag)
    {
        int i = mPaddingTop;
        int j = i;
        if(flag)
            j = i + getTopPaddingOffset();
        return j;
    }

    public boolean getFilterTouchesWhenObscured()
    {
        boolean flag = false;
        if((mViewFlags & 0x400) != 0)
            flag = true;
        return flag;
    }

    public boolean getFitsSystemWindows()
    {
        boolean flag;
        if((mViewFlags & 2) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getFocusable()
    {
        int i;
        if((mViewFlags & 0x10) > 0)
            i = 16;
        else
            i = mViewFlags & 1;
        return i;
    }

    public ArrayList getFocusables(int i)
    {
        ArrayList arraylist = new ArrayList(24);
        addFocusables(arraylist, i);
        return arraylist;
    }

    public void getFocusedRect(Rect rect)
    {
        getDrawingRect(rect);
    }

    public Drawable getForeground()
    {
        Drawable drawable = null;
        if(mForegroundInfo != null)
            drawable = ForegroundInfo._2D_get1(mForegroundInfo);
        return drawable;
    }

    public int getForegroundGravity()
    {
        int i;
        if(mForegroundInfo != null)
            i = ForegroundInfo._2D_get2(mForegroundInfo);
        else
            i = 0x800033;
        return i;
    }

    public ColorStateList getForegroundTintList()
    {
        Object obj = null;
        ColorStateList colorstatelist = obj;
        if(mForegroundInfo != null)
        {
            colorstatelist = obj;
            if(ForegroundInfo._2D_get6(mForegroundInfo) != null)
                colorstatelist = ForegroundInfo._2D_get6(mForegroundInfo).mTintList;
        }
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getForegroundTintMode()
    {
        Object obj = null;
        android.graphics.PorterDuff.Mode mode = obj;
        if(mForegroundInfo != null)
        {
            mode = obj;
            if(ForegroundInfo._2D_get6(mForegroundInfo) != null)
                mode = ForegroundInfo._2D_get6(mForegroundInfo).mTintMode;
        }
        return mode;
    }

    public final boolean getGlobalVisibleRect(Rect rect)
    {
        return getGlobalVisibleRect(rect, null);
    }

    public boolean getGlobalVisibleRect(Rect rect, Point point)
    {
        int i = mRight - mLeft;
        int j = mBottom - mTop;
        if(i > 0 && j > 0)
        {
            rect.set(0, 0, i, j);
            if(point != null)
                point.set(-mScrollX, -mScrollY);
            boolean flag;
            if(mParent != null)
                flag = mParent.getChildVisibleRect(this, rect, point);
            else
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public Handler getHandler()
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
            return attachinfo.mHandler;
        else
            return null;
    }

    public final boolean getHasOverlappingRendering()
    {
        boolean flag = false;
        if((mPrivateFlags3 & 0x1000000) != 0)
        {
            if((mPrivateFlags3 & 0x800000) != 0)
                flag = true;
        } else
        {
            flag = hasOverlappingRendering();
        }
        return flag;
    }

    public final int getHeight()
    {
        return mBottom - mTop;
    }

    public void getHitRect(Rect rect)
    {
        if(hasIdentityMatrix() || mAttachInfo == null)
        {
            rect.set(mLeft, mTop, mRight, mBottom);
        } else
        {
            RectF rectf = mAttachInfo.mTmpTransformRect;
            rectf.set(0.0F, 0.0F, getWidth(), getHeight());
            getMatrix().mapRect(rectf);
            rect.set((int)rectf.left + mLeft, (int)rectf.top + mTop, (int)rectf.right + mLeft, (int)rectf.bottom + mTop);
        }
    }

    public int getHorizontalFadingEdgeLength()
    {
        if(isHorizontalFadingEdgeEnabled())
        {
            ScrollabilityCache scrollabilitycache = mScrollCache;
            if(scrollabilitycache != null)
                return scrollabilitycache.fadingEdgeLength;
        }
        return 0;
    }

    protected float getHorizontalScrollFactor()
    {
        return getVerticalScrollFactor();
    }

    protected int getHorizontalScrollbarHeight()
    {
        ScrollabilityCache scrollabilitycache = mScrollCache;
        if(scrollabilitycache != null)
        {
            ScrollBarDrawable scrollbardrawable = scrollabilitycache.scrollBar;
            if(scrollbardrawable != null)
            {
                int i = scrollbardrawable.getSize(false);
                int j = i;
                if(i <= 0)
                    j = scrollabilitycache.scrollBarSize;
                return j;
            } else
            {
                return 0;
            }
        } else
        {
            return 0;
        }
    }

    public void getHotspotBounds(Rect rect)
    {
        Drawable drawable = getBackground();
        if(drawable != null)
            drawable.getHotspotBounds(rect);
        else
            getBoundsOnScreen(rect);
    }

    public int getId()
    {
        return mID;
    }

    public int getImportantForAccessibility()
    {
        return (mPrivateFlags2 & 0x700000) >> 20;
    }

    public int getImportantForAutofill()
    {
        return (mPrivateFlags3 & 0x780000) >> 19;
    }

    public final Matrix getInverseMatrix()
    {
        ensureTransformationInfo();
        if(TransformationInfo._2D_get0(mTransformationInfo) == null)
            TransformationInfo._2D_set0(mTransformationInfo, new Matrix());
        Matrix matrix = TransformationInfo._2D_get0(mTransformationInfo);
        mRenderNode.getInverseMatrix(matrix);
        return matrix;
    }

    public CharSequence getIterableTextForAccessibility()
    {
        return getContentDescription();
    }

    public AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(int i)
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   1: 38
    //                   2: 85
    //                   8: 132;
           goto _L1 _L2 _L3 _L4
_L1:
        CharSequence charsequence;
        return null;
_L2:
        if((charsequence = getIterableTextForAccessibility()) != null && charsequence.length() > 0)
        {
            AccessibilityIterators.CharacterTextSegmentIterator charactertextsegmentiterator = AccessibilityIterators.CharacterTextSegmentIterator.getInstance(mContext.getResources().getConfiguration().locale);
            charactertextsegmentiterator.initialize(charsequence.toString());
            return charactertextsegmentiterator;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        CharSequence charsequence2 = getIterableTextForAccessibility();
        if(charsequence2 != null && charsequence2.length() > 0)
        {
            AccessibilityIterators.WordTextSegmentIterator wordtextsegmentiterator = AccessibilityIterators.WordTextSegmentIterator.getInstance(mContext.getResources().getConfiguration().locale);
            wordtextsegmentiterator.initialize(charsequence2.toString());
            return wordtextsegmentiterator;
        }
        if(true) goto _L1; else goto _L4
_L4:
        CharSequence charsequence1 = getIterableTextForAccessibility();
        if(charsequence1 != null && charsequence1.length() > 0)
        {
            AccessibilityIterators.ParagraphTextSegmentIterator paragraphtextsegmentiterator = AccessibilityIterators.ParagraphTextSegmentIterator.getInstance();
            paragraphtextsegmentiterator.initialize(charsequence1.toString());
            return paragraphtextsegmentiterator;
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean getKeepScreenOn()
    {
        boolean flag = false;
        if((mViewFlags & 0x4000000) != 0)
            flag = true;
        return flag;
    }

    public KeyEvent.DispatcherState getKeyDispatcherState()
    {
        KeyEvent.DispatcherState dispatcherstate = null;
        if(mAttachInfo != null)
            dispatcherstate = mAttachInfo.mKeyDispatchState;
        return dispatcherstate;
    }

    public int getLabelFor()
    {
        return mLabelForId;
    }

    public int getLayerType()
    {
        return mLayerType;
    }

    public int getLayoutDirection()
    {
        int i = 0;
        if(getContext().getApplicationInfo().targetSdkVersion < 17)
        {
            mPrivateFlags2 = mPrivateFlags2 | 0x20;
            return 0;
        }
        if((mPrivateFlags2 & 0x10) == 16)
            i = 1;
        return i;
    }

    public ViewGroup.LayoutParams getLayoutParams()
    {
        return mLayoutParams;
    }

    public final int getLeft()
    {
        return mLeft;
    }

    protected float getLeftFadingEdgeStrength()
    {
        float f;
        if(computeHorizontalScrollOffset() > 0)
            f = 1.0F;
        else
            f = 0.0F;
        return f;
    }

    protected int getLeftPaddingOffset()
    {
        return 0;
    }

    ListenerInfo getListenerInfo()
    {
        if(mListenerInfo != null)
        {
            return mListenerInfo;
        } else
        {
            mListenerInfo = new ListenerInfo();
            return mListenerInfo;
        }
    }

    public final boolean getLocalVisibleRect(Rect rect)
    {
        Point point;
        if(mAttachInfo != null)
            point = mAttachInfo.mPoint;
        else
            point = new Point();
        if(getGlobalVisibleRect(rect, point))
        {
            rect.offset(-point.x, -point.y);
            return true;
        } else
        {
            return false;
        }
    }

    public void getLocationInSurface(int ai[])
    {
        getLocationInWindow(ai);
        if(mAttachInfo != null && mAttachInfo.mViewRootImpl != null)
        {
            ai[0] = ai[0] + mAttachInfo.mViewRootImpl.mWindowAttributes.surfaceInsets.left;
            ai[1] = ai[1] + mAttachInfo.mViewRootImpl.mWindowAttributes.surfaceInsets.top;
        }
    }

    public void getLocationInWindow(int ai[])
    {
        if(ai == null || ai.length < 2)
        {
            throw new IllegalArgumentException("outLocation must be an array of two integers");
        } else
        {
            ai[0] = 0;
            ai[1] = 0;
            transformFromViewToWindowSpace(ai);
            return;
        }
    }

    public void getLocationOnScreen(int ai[])
    {
        getLocationInWindow(ai);
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
        {
            ai[0] = ai[0] + attachinfo.mWindowLeft;
            ai[1] = ai[1] + attachinfo.mWindowTop;
        }
    }

    public int[] getLocationOnScreen()
    {
        int ai[] = new int[2];
        getLocationOnScreen(ai);
        return ai;
    }

    public Matrix getMatrix()
    {
        ensureTransformationInfo();
        Matrix matrix = TransformationInfo._2D_get1(mTransformationInfo);
        mRenderNode.getMatrix(matrix);
        return matrix;
    }

    public final int getMeasuredHeight()
    {
        return mMeasuredHeight & 0xffffff;
    }

    public final int getMeasuredHeightAndState()
    {
        return mMeasuredHeight;
    }

    public final int getMeasuredState()
    {
        return mMeasuredWidth & 0xff000000 | mMeasuredHeight >> 16 & 0xffffff00;
    }

    public final int getMeasuredWidth()
    {
        return mMeasuredWidth & 0xffffff;
    }

    public final int getMeasuredWidthAndState()
    {
        return mMeasuredWidth;
    }

    public int getMinimumHeight()
    {
        return mMinHeight;
    }

    public int getMinimumWidth()
    {
        return mMinWidth;
    }

    public int getNextClusterForwardId()
    {
        return mNextClusterForwardId;
    }

    public int getNextFocusDownId()
    {
        return mNextFocusDownId;
    }

    public int getNextFocusForwardId()
    {
        return mNextFocusForwardId;
    }

    public int getNextFocusLeftId()
    {
        return mNextFocusLeftId;
    }

    public int getNextFocusRightId()
    {
        return mNextFocusRightId;
    }

    public int getNextFocusUpId()
    {
        return mNextFocusUpId;
    }

    public OnFocusChangeListener getOnFocusChangeListener()
    {
        OnFocusChangeListener onfocuschangelistener = null;
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo != null)
            onfocuschangelistener = listenerinfo.mOnFocusChangeListener;
        return onfocuschangelistener;
    }

    public Insets getOpticalInsets()
    {
        if(mLayoutInsets == null)
            mLayoutInsets = computeOpticalInsets();
        return mLayoutInsets;
    }

    public ViewOutlineProvider getOutlineProvider()
    {
        return mOutlineProvider;
    }

    public void getOutsets(Rect rect)
    {
        if(mAttachInfo != null)
            rect.set(mAttachInfo.mOutsets);
        else
            rect.setEmpty();
    }

    public int getOverScrollMode()
    {
        return mOverScrollMode;
    }

    public ViewOverlay getOverlay()
    {
        if(mOverlay == null)
            mOverlay = new ViewOverlay(mContext, this);
        return mOverlay;
    }

    public int getPaddingBottom()
    {
        return mPaddingBottom;
    }

    public int getPaddingEnd()
    {
        if(!isPaddingResolved())
            resolvePadding();
        int i;
        if(getLayoutDirection() == 1)
            i = mPaddingLeft;
        else
            i = mPaddingRight;
        return i;
    }

    public int getPaddingLeft()
    {
        if(!isPaddingResolved())
            resolvePadding();
        return mPaddingLeft;
    }

    public int getPaddingRight()
    {
        if(!isPaddingResolved())
            resolvePadding();
        return mPaddingRight;
    }

    public int getPaddingStart()
    {
        if(!isPaddingResolved())
            resolvePadding();
        int i;
        if(getLayoutDirection() == 1)
            i = mPaddingRight;
        else
            i = mPaddingLeft;
        return i;
    }

    public int getPaddingTop()
    {
        return mPaddingTop;
    }

    public final ViewParent getParent()
    {
        return mParent;
    }

    public ViewParent getParentForAccessibility()
    {
        if(mParent instanceof View)
        {
            if(((View)mParent).includeForAccessibility())
                return mParent;
            else
                return mParent.getParentForAccessibility();
        } else
        {
            return null;
        }
    }

    public float getPivotX()
    {
        return mRenderNode.getPivotX();
    }

    public float getPivotY()
    {
        return mRenderNode.getPivotY();
    }

    public PointerIcon getPointerIcon()
    {
        return mPointerIcon;
    }

    public int getRawLayoutDirection()
    {
        return (mPrivateFlags2 & 0xc) >> 2;
    }

    public int getRawTextAlignment()
    {
        return (mPrivateFlags2 & 0xe000) >> 13;
    }

    public int getRawTextDirection()
    {
        return (mPrivateFlags2 & 0x1c0) >> 6;
    }

    public Resources getResources()
    {
        return mResources;
    }

    public final boolean getRevealOnFocusHint()
    {
        boolean flag = false;
        if((mPrivateFlags3 & 0x4000000) == 0)
            flag = true;
        return flag;
    }

    public final int getRight()
    {
        return mRight;
    }

    protected float getRightFadingEdgeStrength()
    {
        float f;
        if(computeHorizontalScrollOffset() + computeHorizontalScrollExtent() < computeHorizontalScrollRange())
            f = 1.0F;
        else
            f = 0.0F;
        return f;
    }

    protected int getRightPaddingOffset()
    {
        return 0;
    }

    public View getRootView()
    {
        if(mAttachInfo != null)
        {
            View view = mAttachInfo.mRootView;
            if(view != null)
                return view;
        }
        View view1;
        for(view1 = this; view1.mParent != null && (view1.mParent instanceof View); view1 = (View)view1.mParent);
        return view1;
    }

    public WindowInsets getRootWindowInsets()
    {
        if(mAttachInfo != null)
            return mAttachInfo.mViewRootImpl.getWindowInsets(false);
        else
            return null;
    }

    public float getRotation()
    {
        return mRenderNode.getRotation();
    }

    public float getRotationX()
    {
        return mRenderNode.getRotationX();
    }

    public float getRotationY()
    {
        return mRenderNode.getRotationY();
    }

    public float getScaleX()
    {
        return mRenderNode.getScaleX();
    }

    public float getScaleY()
    {
        return mRenderNode.getScaleY();
    }

    public int getScrollBarDefaultDelayBeforeFade()
    {
        int i;
        if(mScrollCache == null)
            i = ViewConfiguration.getScrollDefaultDelay();
        else
            i = mScrollCache.scrollBarDefaultDelayBeforeFade;
        return i;
    }

    public int getScrollBarFadeDuration()
    {
        int i;
        if(mScrollCache == null)
            i = ViewConfiguration.getScrollBarFadeDuration();
        else
            i = mScrollCache.scrollBarFadeDuration;
        return i;
    }

    public int getScrollBarSize()
    {
        int i;
        if(mScrollCache == null)
            i = ViewConfiguration.get(mContext).getScaledScrollBarSize();
        else
            i = mScrollCache.scrollBarSize;
        return i;
    }

    public int getScrollBarStyle()
    {
        return mViewFlags & 0x3000000;
    }

    void getScrollIndicatorBounds(Rect rect)
    {
        rect.left = mScrollX;
        rect.right = (mScrollX + mRight) - mLeft;
        rect.top = mScrollY;
        rect.bottom = (mScrollY + mBottom) - mTop;
    }

    public int getScrollIndicators()
    {
        return (mPrivateFlags3 & 0x3f00) >>> 8;
    }

    public final int getScrollX()
    {
        return mScrollX;
    }

    public final int getScrollY()
    {
        return mScrollY;
    }

    public int getSolidColor()
    {
        return 0;
    }

    public StateListAnimator getStateListAnimator()
    {
        return mStateListAnimator;
    }

    protected int getSuggestedMinimumHeight()
    {
        int i;
        if(mBackground == null)
            i = mMinHeight;
        else
            i = Math.max(mMinHeight, mBackground.getMinimumHeight());
        return i;
    }

    protected int getSuggestedMinimumWidth()
    {
        int i;
        if(mBackground == null)
            i = mMinWidth;
        else
            i = Math.max(mMinWidth, mBackground.getMinimumWidth());
        return i;
    }

    public int getSystemUiVisibility()
    {
        return mSystemUiVisibility;
    }

    public Object getTag()
    {
        return mTag;
    }

    public Object getTag(int i)
    {
        if(mKeyedTags != null)
            return mKeyedTags.get(i);
        else
            return null;
    }

    public int getTextAlignment()
    {
        return (mPrivateFlags2 & 0xe0000) >> 17;
    }

    public int getTextDirection()
    {
        return (mPrivateFlags2 & 0x1c00) >> 10;
    }

    public ThreadedRenderer getThreadedRenderer()
    {
        ThreadedRenderer threadedrenderer = null;
        if(mAttachInfo != null)
            threadedrenderer = mAttachInfo.mThreadedRenderer;
        return threadedrenderer;
    }

    public CharSequence getTooltip()
    {
        return getTooltipText();
    }

    public CharSequence getTooltipText()
    {
        CharSequence charsequence = null;
        if(mTooltipInfo != null)
            charsequence = mTooltipInfo.mTooltipText;
        return charsequence;
    }

    public View getTooltipView()
    {
        if(mTooltipInfo == null || mTooltipInfo.mTooltipPopup == null)
            return null;
        else
            return mTooltipInfo.mTooltipPopup.getContentView();
    }

    public final int getTop()
    {
        return mTop;
    }

    protected float getTopFadingEdgeStrength()
    {
        float f;
        if(computeVerticalScrollOffset() > 0)
            f = 1.0F;
        else
            f = 0.0F;
        return f;
    }

    protected int getTopPaddingOffset()
    {
        return 0;
    }

    public TouchDelegate getTouchDelegate()
    {
        return mTouchDelegate;
    }

    public ArrayList getTouchables()
    {
        ArrayList arraylist = new ArrayList();
        addTouchables(arraylist);
        return arraylist;
    }

    public float getTransitionAlpha()
    {
        float f;
        if(mTransformationInfo != null)
            f = mTransformationInfo.mTransitionAlpha;
        else
            f = 1.0F;
        return f;
    }

    public String getTransitionName()
    {
        return mTransitionName;
    }

    public float getTranslationX()
    {
        return mRenderNode.getTranslationX();
    }

    public float getTranslationY()
    {
        return mRenderNode.getTranslationY();
    }

    public float getTranslationZ()
    {
        return mRenderNode.getTranslationZ();
    }

    public int getVerticalFadingEdgeLength()
    {
        if(isVerticalFadingEdgeEnabled())
        {
            ScrollabilityCache scrollabilitycache = mScrollCache;
            if(scrollabilitycache != null)
                return scrollabilitycache.fadingEdgeLength;
        }
        return 0;
    }

    protected float getVerticalScrollFactor()
    {
        if(mVerticalScrollFactor == 0.0F)
        {
            TypedValue typedvalue = new TypedValue();
            if(!mContext.getTheme().resolveAttribute(0x101004d, typedvalue, true))
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            mVerticalScrollFactor = typedvalue.getDimension(mContext.getResources().getDisplayMetrics());
        }
        return mVerticalScrollFactor;
    }

    public int getVerticalScrollbarPosition()
    {
        return mVerticalScrollbarPosition;
    }

    public int getVerticalScrollbarWidth()
    {
        ScrollabilityCache scrollabilitycache = mScrollCache;
        if(scrollabilitycache != null)
        {
            ScrollBarDrawable scrollbardrawable = scrollabilitycache.scrollBar;
            if(scrollbardrawable != null)
            {
                int i = scrollbardrawable.getSize(true);
                int j = i;
                if(i <= 0)
                    j = scrollabilitycache.scrollBarSize;
                return j;
            } else
            {
                return 0;
            }
        } else
        {
            return 0;
        }
    }

    public ViewRootImpl getViewRootImpl()
    {
        if(mAttachInfo != null)
            return mAttachInfo.mViewRootImpl;
        else
            return null;
    }

    public ViewTreeObserver getViewTreeObserver()
    {
        if(mAttachInfo != null)
            return mAttachInfo.mTreeObserver;
        if(mFloatingTreeObserver == null)
            mFloatingTreeObserver = new ViewTreeObserver(mContext);
        return mFloatingTreeObserver;
    }

    public int getVisibility()
    {
        return mViewFlags & 0xc;
    }

    public final int getWidth()
    {
        return mRight - mLeft;
    }

    protected int getWindowAttachCount()
    {
        return mWindowAttachCount;
    }

    public void getWindowDisplayFrame(Rect rect)
    {
        if(mAttachInfo != null)
        {
            try
            {
                mAttachInfo.mSession.getDisplayFrame(mAttachInfo.mWindow, rect);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Rect rect)
            {
                return;
            }
        } else
        {
            DisplayManagerGlobal.getInstance().getRealDisplay(0).getRectSize(rect);
            return;
        }
    }

    public WindowId getWindowId()
    {
        if(mAttachInfo == null)
            return null;
        if(mAttachInfo.mWindowId == null)
            try
            {
                mAttachInfo.mIWindowId = mAttachInfo.mSession.getWindowId(mAttachInfo.mWindowToken);
                AttachInfo attachinfo = mAttachInfo;
                WindowId windowid = JVM INSTR new #5225 <Class WindowId>;
                windowid.WindowId(mAttachInfo.mIWindowId);
                attachinfo.mWindowId = windowid;
            }
            catch(RemoteException remoteexception) { }
        return mAttachInfo.mWindowId;
    }

    IWindowSession getWindowSession()
    {
        IWindowSession iwindowsession = null;
        if(mAttachInfo != null)
            iwindowsession = mAttachInfo.mSession;
        return iwindowsession;
    }

    public int getWindowSystemUiVisibility()
    {
        int i;
        if(mAttachInfo != null)
            i = mAttachInfo.mSystemUiVisibility;
        else
            i = 0;
        return i;
    }

    public IBinder getWindowToken()
    {
        IBinder ibinder = null;
        if(mAttachInfo != null)
            ibinder = mAttachInfo.mWindowToken;
        return ibinder;
    }

    public int getWindowVisibility()
    {
        int i;
        if(mAttachInfo != null)
            i = mAttachInfo.mWindowVisibility;
        else
            i = 8;
        return i;
    }

    public void getWindowVisibleDisplayFrame(Rect rect)
    {
        if(mAttachInfo != null)
        {
            Rect rect1;
            try
            {
                mAttachInfo.mSession.getDisplayFrame(mAttachInfo.mWindow, rect);
            }
            // Misplaced declaration of an exception variable
            catch(Rect rect)
            {
                return;
            }
            rect1 = mAttachInfo.mVisibleInsets;
            rect.left = rect.left + rect1.left;
            rect.top = rect.top + rect1.top;
            rect.right = rect.right - rect1.right;
            rect.bottom = rect.bottom - rect1.bottom;
            return;
        } else
        {
            DisplayManagerGlobal.getInstance().getRealDisplay(0).getRectSize(rect);
            return;
        }
    }

    public float getX()
    {
        return (float)mLeft + getTranslationX();
    }

    public float getY()
    {
        return (float)mTop + getTranslationY();
    }

    public float getZ()
    {
        return getElevation() + getTranslationZ();
    }

    void handleFocusGainInternal(int i, Rect rect)
    {
        if((mPrivateFlags & 2) == 0)
        {
            mPrivateFlags = mPrivateFlags | 2;
            View view;
            if(mAttachInfo != null)
                view = getRootView().findFocus();
            else
                view = null;
            if(mParent != null)
            {
                mParent.requestChildFocus(this, this);
                updateFocusedInCluster(view, i);
            }
            if(mAttachInfo != null)
                mAttachInfo.mTreeObserver.dispatchOnGlobalFocusChange(view, this);
            onFocusChanged(true, i, rect);
            refreshDrawableState();
        }
    }

    protected boolean handleScrollBarDragging(MotionEvent motionevent)
    {
        float f;
        float f1;
        int i;
        if(mScrollCache == null)
            return false;
        f = motionevent.getX();
        f1 = motionevent.getY();
        for(i = motionevent.getAction(); mScrollCache.mScrollBarDraggingState == 0 && i != 0 || motionevent.isFromSource(8194) ^ true || motionevent.isButtonPressed(1) ^ true;)
        {
            mScrollCache.mScrollBarDraggingState = 0;
            return false;
        }

        i;
        JVM INSTR tableswitch 0 2: default 100
    //                   0 510
    //                   1 100
    //                   2 110;
           goto _L1 _L2 _L1 _L3
_L1:
        mScrollCache.mScrollBarDraggingState = 0;
        return false;
_L3:
        if(mScrollCache.mScrollBarDraggingState == 0)
            return false;
        if(mScrollCache.mScrollBarDraggingState == 1)
        {
            motionevent = mScrollCache.mScrollBarBounds;
            getVerticalScrollBarBounds(motionevent, null);
            int j = computeVerticalScrollRange();
            int l = computeVerticalScrollOffset();
            int j1 = computeVerticalScrollExtent();
            int l1 = ScrollBarUtils.getThumbLength(motionevent.height(), motionevent.width(), j1, j);
            l = ScrollBarUtils.getThumbOffset(motionevent.height(), l1, j1, j, l);
            float f2 = mScrollCache.mScrollBarDraggingPos;
            f = motionevent.height() - l1;
            f2 = Math.min(Math.max((float)l + (f1 - f2), 0.0F), f);
            l1 = getHeight();
            if(Math.round(f2) != l && f > 0.0F && l1 > 0 && j1 > 0)
            {
                j = Math.round(((float)(j - j1) / ((float)j1 / (float)l1)) * (f2 / f));
                if(j != getScrollY())
                {
                    mScrollCache.mScrollBarDraggingPos = f1;
                    setScrollY(j);
                }
            }
            return true;
        }
        if(mScrollCache.mScrollBarDraggingState == 2)
        {
            motionevent = mScrollCache.mScrollBarBounds;
            getHorizontalScrollBarBounds(motionevent, null);
            int k1 = computeHorizontalScrollRange();
            int i1 = computeHorizontalScrollOffset();
            int k = computeHorizontalScrollExtent();
            int i2 = ScrollBarUtils.getThumbLength(motionevent.width(), motionevent.height(), k, k1);
            i1 = ScrollBarUtils.getThumbOffset(motionevent.width(), i2, k, k1, i1);
            float f3 = mScrollCache.mScrollBarDraggingPos;
            f1 = motionevent.width() - i2;
            f3 = Math.min(Math.max((float)i1 + (f - f3), 0.0F), f1);
            i2 = getWidth();
            if(Math.round(f3) != i1 && f1 > 0.0F && i2 > 0 && k > 0)
            {
                k = Math.round(((float)(k1 - k) / ((float)k / (float)i2)) * (f3 / f1));
                if(k != getScrollX())
                {
                    mScrollCache.mScrollBarDraggingPos = f;
                    setScrollX(k);
                }
            }
            return true;
        }
_L2:
        if(mScrollCache.state == 0)
            return false;
        if(isOnVerticalScrollbarThumb(f, f1))
        {
            mScrollCache.mScrollBarDraggingState = 1;
            mScrollCache.mScrollBarDraggingPos = f1;
            return true;
        }
        if(isOnHorizontalScrollbarThumb(f, f1))
        {
            mScrollCache.mScrollBarDraggingState = 2;
            mScrollCache.mScrollBarDraggingPos = f;
            return true;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    void handleTooltipKey(KeyEvent keyevent)
    {
        keyevent.getAction();
        JVM INSTR tableswitch 0 1: default 28
    //                   0 29
    //                   1 43;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if(keyevent.getRepeatCount() == 0)
            hideTooltip();
        continue; /* Loop/switch isn't completed */
_L3:
        handleTooltipUp();
        if(true) goto _L1; else goto _L4
_L4:
    }

    boolean hasDefaultFocus()
    {
        return isFocusedByDefault();
    }

    public boolean hasExplicitFocusable()
    {
        return hasFocusable(false, true);
    }

    public boolean hasFocus()
    {
        boolean flag = false;
        if((mPrivateFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean hasFocusable()
    {
        return hasFocusable(sHasFocusableExcludeAutoFocusable ^ true, false);
    }

    boolean hasFocusable(boolean flag, boolean flag1)
    {
        if(!isFocusableInTouchMode())
        {
            for(ViewParent viewparent = mParent; viewparent instanceof ViewGroup; viewparent = viewparent.getParent())
                if(((ViewGroup)viewparent).shouldBlockFocusForTouchscreen())
                    return false;

        }
        if((mViewFlags & 0xc) != 0)
            return false;
        return (flag || getFocusable() != 16) && isFocusable();
    }

    protected boolean hasHoveredChild()
    {
        return false;
    }

    final boolean hasIdentityMatrix()
    {
        return mRenderNode.hasIdentityMatrix();
    }

    public boolean hasNestedScrollingParent()
    {
        boolean flag;
        if(mNestedScrollingParent != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasOnClickListeners()
    {
        boolean flag = false;
        ListenerInfo listenerinfo = mListenerInfo;
        boolean flag1 = flag;
        if(listenerinfo != null)
        {
            flag1 = flag;
            if(listenerinfo.mOnClickListener != null)
                flag1 = true;
        }
        return flag1;
    }

    protected boolean hasOpaqueScrollbars()
    {
        boolean flag;
        if((mPrivateFlags & 0x1000000) == 0x1000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasOverlappingRendering()
    {
        return true;
    }

    public boolean hasPointerCapture()
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl == null)
            return false;
        else
            return viewrootimpl.hasPointerCapture();
    }

    public boolean hasShadow()
    {
        return mRenderNode.hasShadow();
    }

    public boolean hasTransientState()
    {
        boolean flag;
        if((mPrivateFlags2 & 0x80000000) == 0x80000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasWindowFocus()
    {
        boolean flag;
        if(mAttachInfo != null)
            flag = mAttachInfo.mHasWindowFocus;
        else
            flag = false;
        return flag;
    }

    void hideTooltip()
    {
        if(mTooltipInfo == null)
            return;
        removeCallbacks(mTooltipInfo.mShowTooltipRunnable);
        if(mTooltipInfo.mTooltipPopup == null)
            return;
        mTooltipInfo.mTooltipPopup.hide();
        mTooltipInfo.mTooltipPopup = null;
        mTooltipInfo.mTooltipFromLongClick = false;
        if(mAttachInfo != null)
            mAttachInfo.mTooltipHost = null;
    }

    public boolean includeForAccessibility()
    {
        if(mAttachInfo != null)
        {
            boolean flag;
            if((mAttachInfo.mAccessibilityFetchFlags & 8) == 0)
                flag = isImportantForAccessibility();
            else
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    protected void initializeFadingEdge(TypedArray typedarray)
    {
        typedarray = mContext.obtainStyledAttributes(com.android.internal.R.styleable.View);
        initializeFadingEdgeInternal(typedarray);
        typedarray.recycle();
    }

    protected void initializeFadingEdgeInternal(TypedArray typedarray)
    {
        initScrollCache();
        mScrollCache.fadingEdgeLength = typedarray.getDimensionPixelSize(25, ViewConfiguration.get(mContext).getScaledFadingEdgeLength());
    }

    protected void initializeScrollbars(TypedArray typedarray)
    {
        typedarray = mContext.obtainStyledAttributes(com.android.internal.R.styleable.View);
        initializeScrollbarsInternal(typedarray);
        typedarray.recycle();
    }

    protected void initializeScrollbarsInternal(TypedArray typedarray)
    {
        initScrollCache();
        ScrollabilityCache scrollabilitycache = mScrollCache;
        if(scrollabilitycache.scrollBar == null)
        {
            scrollabilitycache.scrollBar = new ScrollBarDrawable();
            scrollabilitycache.scrollBar.setState(getDrawableState());
            scrollabilitycache.scrollBar.setCallback(this);
        }
        boolean flag = typedarray.getBoolean(47, true);
        if(!flag)
            scrollabilitycache.state = 1;
        scrollabilitycache.fadeScrollBars = flag;
        scrollabilitycache.scrollBarFadeDuration = typedarray.getInt(45, ViewConfiguration.getScrollBarFadeDuration());
        scrollabilitycache.scrollBarDefaultDelayBeforeFade = typedarray.getInt(46, ViewConfiguration.getScrollDefaultDelay());
        scrollabilitycache.scrollBarSize = typedarray.getDimensionPixelSize(1, ViewConfiguration.get(mContext).getScaledScrollBarSize());
        Drawable drawable = typedarray.getDrawable(4);
        scrollabilitycache.scrollBar.setHorizontalTrackDrawable(drawable);
        drawable = typedarray.getDrawable(2);
        if(drawable != null)
            scrollabilitycache.scrollBar.setHorizontalThumbDrawable(drawable);
        if(typedarray.getBoolean(6, false))
            scrollabilitycache.scrollBar.setAlwaysDrawHorizontalTrack(true);
        drawable = typedarray.getDrawable(5);
        scrollabilitycache.scrollBar.setVerticalTrackDrawable(drawable);
        Drawable drawable1 = typedarray.getDrawable(3);
        if(drawable1 != null)
            scrollabilitycache.scrollBar.setVerticalThumbDrawable(drawable1);
        if(typedarray.getBoolean(7, false))
            scrollabilitycache.scrollBar.setAlwaysDrawVerticalTrack(true);
        int i = getLayoutDirection();
        if(drawable != null)
            drawable.setLayoutDirection(i);
        if(drawable1 != null)
            drawable1.setLayoutDirection(i);
        resolvePadding();
    }

    protected void internalSetPadding(int i, int j, int k, int l)
    {
        boolean flag;
        int i1;
        boolean flag1;
        int j1;
        int k1;
        int l1;
        flag = false;
        mUserPaddingLeft = i;
        mUserPaddingRight = k;
        mUserPaddingBottom = l;
        i1 = mViewFlags;
        flag1 = false;
        j1 = i;
        k1 = k;
        l1 = l;
        if((i1 & 0x300) == 0) goto _L2; else goto _L1
_L1:
        int i2;
        int j2;
        i2 = i;
        j2 = k;
        if((i1 & 0x200) == 0) goto _L4; else goto _L3
_L3:
        if((i1 & 0x1000000) == 0)
            i2 = 0;
        else
            i2 = getVerticalScrollbarWidth();
        mVerticalScrollbarPosition;
        JVM INSTR tableswitch 0 2: default 104
    //                   0 248
    //                   1 291
    //                   2 279;
           goto _L5 _L6 _L7 _L8
_L7:
        break MISSING_BLOCK_LABEL_291;
_L5:
        j2 = k;
        i2 = i;
_L4:
        j1 = i2;
        k1 = j2;
        l1 = l;
        if((i1 & 0x100) != 0)
        {
            if((i1 & 0x1000000) == 0)
                i = ((flag) ? 1 : 0);
            else
                i = getHorizontalScrollbarHeight();
            l1 = l + i;
            k1 = j2;
            j1 = i2;
        }
_L2:
        i = ((flag1) ? 1 : 0);
        if(mPaddingLeft != j1)
        {
            i = 1;
            mPaddingLeft = j1;
        }
        if(mPaddingTop != j)
        {
            i = 1;
            mPaddingTop = j;
        }
        if(mPaddingRight != k1)
        {
            i = 1;
            mPaddingRight = k1;
        }
        if(mPaddingBottom != l1)
        {
            i = 1;
            mPaddingBottom = l1;
        }
        if(i != 0)
        {
            requestLayout();
            invalidateOutline();
        }
        return;
_L6:
        if(isLayoutRtl())
        {
            i2 = i + i2;
            j2 = k;
        } else
        {
            j2 = k + i2;
            i2 = i;
        }
          goto _L4
_L8:
        j2 = k + i2;
        i2 = i;
          goto _L4
        i2 = i + i2;
        j2 = k;
          goto _L4
    }

    public void invalidate()
    {
        invalidate(true);
    }

    public void invalidate(int i, int j, int k, int l)
    {
        int i1 = mScrollX;
        int j1 = mScrollY;
        invalidateInternal(i - i1, j - j1, k - i1, l - j1, true, false);
    }

    public void invalidate(Rect rect)
    {
        int i = mScrollX;
        int j = mScrollY;
        invalidateInternal(rect.left - i, rect.top - j, rect.right - i, rect.bottom - j, true, false);
    }

    public void invalidate(boolean flag)
    {
        invalidateInternal(0, 0, mRight - mLeft, mBottom - mTop, flag, true);
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if(verifyDrawable(drawable))
        {
            drawable = drawable.getDirtyBounds();
            int i = mScrollX;
            int j = mScrollY;
            invalidate(((Rect) (drawable)).left + i, ((Rect) (drawable)).top + j, ((Rect) (drawable)).right + i, ((Rect) (drawable)).bottom + j);
            rebuildOutline();
        }
    }

    void invalidateInheritedLayoutMode(int i)
    {
    }

    void invalidateInternal(int i, int j, int k, int l, boolean flag, boolean flag1)
    {
        if(mGhostView != null)
        {
            mGhostView.invalidate(true);
            return;
        }
        if(skipInvalidate())
            return;
        break MISSING_BLOCK_LABEL_24;
        if((mPrivateFlags & 0x30) == 48 || flag && (mPrivateFlags & 0x8000) == 32768 || (mPrivateFlags & 0x80000000) != 0x80000000 || flag1 && isOpaque() != mLastIsOpaque)
        {
            if(flag1)
            {
                mLastIsOpaque = isOpaque();
                mPrivateFlags = mPrivateFlags & 0xffffffdf;
            }
            mPrivateFlags = mPrivateFlags | 0x200000;
            if(flag)
            {
                mPrivateFlags = mPrivateFlags | 0x80000000;
                mPrivateFlags = mPrivateFlags & 0xffff7fff;
            }
            Object obj = mAttachInfo;
            ViewParent viewparent = mParent;
            if(viewparent != null && obj != null && i < k && j < l)
            {
                obj = ((AttachInfo) (obj)).mTmpInvalRect;
                ((Rect) (obj)).set(i, j, k, l);
                viewparent.invalidateChild(this, ((Rect) (obj)));
            }
            if(mBackground != null && mBackground.isProjected())
            {
                View view = getProjectionReceiver();
                if(view != null)
                    view.damageInParent();
            }
        }
        return;
    }

    public void invalidateOutline()
    {
        rebuildOutline();
        notifySubtreeAccessibilityStateChangedIfNeeded();
        invalidateViewProperty(false, false);
    }

    protected void invalidateParentCaches()
    {
        if(mParent instanceof View)
        {
            View view = (View)mParent;
            view.mPrivateFlags = view.mPrivateFlags | 0x80000000;
        }
    }

    protected void invalidateParentIfNeeded()
    {
        if(isHardwareAccelerated() && (mParent instanceof View))
            ((View)mParent).invalidate(true);
    }

    protected void invalidateParentIfNeededAndWasQuickRejected()
    {
        if((mPrivateFlags2 & 0x10000000) != 0)
            invalidateParentIfNeeded();
    }

    void invalidateViewProperty(boolean flag, boolean flag1)
    {
        if(!isHardwareAccelerated() || mRenderNode.isValid() ^ true || (mPrivateFlags & 0x40) != 0)
        {
            if(flag)
                invalidateParentCaches();
            if(flag1)
                mPrivateFlags = mPrivateFlags | 0x20;
            invalidate(false);
        } else
        {
            damageInParent();
        }
    }

    public boolean isAccessibilityFocused()
    {
        boolean flag = false;
        if((mPrivateFlags2 & 0x4000000) != 0)
            flag = true;
        return flag;
    }

    boolean isAccessibilityFocusedViewOrHost()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!isAccessibilityFocused())
            if(getViewRootImpl() != null && getViewRootImpl().getAccessibilityFocusedHost() == this)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isAccessibilitySelectionExtendable()
    {
        return false;
    }

    public boolean isActionableForAccessibility()
    {
        boolean flag;
        if(!isClickable() && !isLongClickable())
            flag = isFocusable();
        else
            flag = true;
        return flag;
    }

    public boolean isActivated()
    {
        boolean flag = false;
        if((mPrivateFlags & 0x40000000) != 0)
            flag = true;
        return flag;
    }

    public boolean isAssistBlocked()
    {
        boolean flag = false;
        if((mPrivateFlags3 & 0x4000) != 0)
            flag = true;
        return flag;
    }

    public boolean isAttachedToWindow()
    {
        boolean flag;
        if(mAttachInfo != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isAutofilled()
    {
        boolean flag = false;
        if((mPrivateFlags3 & 0x10000) != 0)
            flag = true;
        return flag;
    }

    public boolean isClickable()
    {
        boolean flag;
        if((mViewFlags & 0x4000) == 16384)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isContextClickable()
    {
        boolean flag;
        if((mViewFlags & 0x800000) == 0x800000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDefaultFocusHighlightNeeded(Drawable drawable, Drawable drawable1)
    {
        boolean flag;
        boolean flag1;
        if(drawable == null || drawable.isStateful() ^ true || drawable.hasFocusStateSpecified() ^ true)
        {
            if(drawable1 != null && !(drawable1.isStateful() ^ true))
                flag = drawable1.hasFocusStateSpecified() ^ true;
            else
                flag = true;
        } else
        {
            flag = false;
        }
        if(!isInTouchMode() && getDefaultFocusHighlightEnabled() && flag && isAttachedToWindow())
            flag1 = sUseDefaultFocusHighlight;
        else
            flag1 = false;
        return flag1;
    }

    public boolean isDirty()
    {
        boolean flag = false;
        if((mPrivateFlags & 0x600000) != 0)
            flag = true;
        return flag;
    }

    boolean isDraggingScrollBar()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mScrollCache != null)
        {
            flag1 = flag;
            if(mScrollCache.mScrollBarDraggingState != 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isDrawingCacheEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x8000) == 32768)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDuplicateParentStateEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x400000) == 0x400000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEnabled()
    {
        boolean flag = false;
        if((mViewFlags & 0x20) == 0)
            flag = true;
        return flag;
    }

    public final boolean isFocusable()
    {
        boolean flag = true;
        if(1 != (mViewFlags & 1))
            flag = false;
        return flag;
    }

    public final boolean isFocusableInTouchMode()
    {
        boolean flag;
        if(0x40000 == (mViewFlags & 0x40000))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isFocused()
    {
        boolean flag = false;
        if((mPrivateFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public final boolean isFocusedByDefault()
    {
        boolean flag = false;
        if((mPrivateFlags3 & 0x40000) != 0)
            flag = true;
        return flag;
    }

    public boolean isForegroundInsidePadding()
    {
        boolean flag;
        if(mForegroundInfo != null)
            flag = ForegroundInfo._2D_get3(mForegroundInfo);
        else
            flag = true;
        return flag;
    }

    public boolean isHapticFeedbackEnabled()
    {
        boolean flag;
        if(0x10000000 == (mViewFlags & 0x10000000))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isHardwareAccelerated()
    {
        boolean flag;
        if(mAttachInfo != null)
            flag = mAttachInfo.mHardwareAccelerated;
        else
            flag = false;
        return flag;
    }

    public boolean isHorizontalFadingEdgeEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x1000) == 4096)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isHorizontalScrollBarEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x100) == 256)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isHovered()
    {
        boolean flag = false;
        if((mPrivateFlags & 0x10000000) != 0)
            flag = true;
        return flag;
    }

    public boolean isImportantForAccessibility()
    {
        int i = (mPrivateFlags2 & 0x700000) >> 20;
        if(i == 2 || i == 4)
            return false;
        for(ViewParent viewparent = mParent; viewparent instanceof View; viewparent = viewparent.getParent())
            if(((View)viewparent).getImportantForAccessibility() == 4)
                return false;

        boolean flag;
        if(i == 1 || isActionableForAccessibility() || hasListenersForAccessibility() || getAccessibilityNodeProvider() != null)
            flag = true;
        else
        if(getAccessibilityLiveRegion() != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isImportantForAutofill()
    {
        int j;
        for(ViewParent viewparent = mParent; viewparent instanceof View; viewparent = viewparent.getParent())
        {
            int i = ((View)viewparent).getImportantForAutofill();
            if(i == 8 || i == 4)
                return false;
        }

        j = getImportantForAutofill();
        if(j == 4 || j == 1)
            return true;
        if(j == 8 || j == 2)
            return false;
        j = mID;
        if(j == -1 || !(isViewIdGenerated(j) ^ true)) goto _L2; else goto _L1
_L1:
        String s;
        Object obj;
        Object obj1;
        obj = getResources();
        s = null;
        obj1 = null;
        Object obj2 = ((Resources) (obj)).getResourceEntryName(j);
        s = ((String) (obj2));
        obj = ((Resources) (obj)).getResourcePackageName(j);
        s = ((String) (obj));
_L4:
        if(obj2 != null && s != null && s.equals(mContext.getPackageName()))
            return true;
_L2:
        return false;
        android.content.res.Resources.NotFoundException notfoundexception;
        notfoundexception;
        notfoundexception = s;
        s = obj1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean isInEditMode()
    {
        return false;
    }

    public boolean isInLayout()
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        boolean flag;
        if(viewrootimpl != null)
            flag = viewrootimpl.isInLayout();
        else
            flag = false;
        return flag;
    }

    public boolean isInScrollingContainer()
    {
        for(ViewParent viewparent = getParent(); viewparent != null && (viewparent instanceof ViewGroup); viewparent = viewparent.getParent())
            if(((ViewGroup)viewparent).shouldDelayChildPressedState())
                return true;

        return false;
    }

    public boolean isInTouchMode()
    {
        if(mAttachInfo != null)
            return mAttachInfo.mInTouchMode;
        else
            return ViewRootImpl.isInTouchMode();
    }

    public final boolean isKeyboardNavigationCluster()
    {
        boolean flag = false;
        if((mPrivateFlags3 & 0x8000) != 0)
            flag = true;
        return flag;
    }

    public boolean isLaidOut()
    {
        boolean flag;
        if((mPrivateFlags3 & 4) == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLayoutDirectionInherited()
    {
        boolean flag;
        if(getRawLayoutDirection() == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLayoutDirectionResolved()
    {
        boolean flag;
        if((mPrivateFlags2 & 0x20) == 32)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLayoutRequested()
    {
        boolean flag;
        if((mPrivateFlags & 0x1000) == 4096)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLayoutRtl()
    {
        boolean flag = true;
        if(getLayoutDirection() != 1)
            flag = false;
        return flag;
    }

    public boolean isLongClickable()
    {
        boolean flag;
        if((mViewFlags & 0x200000) == 0x200000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isNestedScrollingEnabled()
    {
        boolean flag;
        if((mPrivateFlags3 & 0x80) == 128)
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean isOnScrollbar(float f, float f1)
    {
        if(mScrollCache == null)
            return false;
        f += getScrollX();
        f1 += getScrollY();
        if(isVerticalScrollBarEnabled() && isVerticalScrollBarHidden() ^ true)
        {
            Rect rect = mScrollCache.mScrollBarTouchBounds;
            getVerticalScrollBarBounds(null, rect);
            if(rect.contains((int)f, (int)f1))
                return true;
        }
        if(isHorizontalScrollBarEnabled())
        {
            Rect rect1 = mScrollCache.mScrollBarTouchBounds;
            getHorizontalScrollBarBounds(null, rect1);
            if(rect1.contains((int)f, (int)f1))
                return true;
        }
        return false;
    }

    boolean isOnScrollbarThumb(float f, float f1)
    {
        boolean flag;
        if(!isOnVerticalScrollbarThumb(f, f1))
            flag = isOnHorizontalScrollbarThumb(f, f1);
        else
            flag = true;
        return flag;
    }

    public boolean isOpaque()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((mPrivateFlags & 0x1800000) == 0x1800000)
        {
            flag1 = flag;
            if(getFinalAlpha() >= 1.0F)
                flag1 = true;
        }
        return flag1;
    }

    protected boolean isPaddingOffsetRequired()
    {
        return false;
    }

    public boolean isPaddingRelative()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mUserPaddingStart == 0x80000000)
            if(mUserPaddingEnd != 0x80000000)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    boolean isPaddingResolved()
    {
        boolean flag;
        if((mPrivateFlags2 & 0x20000000) == 0x20000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPressed()
    {
        boolean flag;
        if((mPrivateFlags & 0x4000) == 16384)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isRootNamespace()
    {
        boolean flag = false;
        if((mPrivateFlags & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean isSaveEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x10000) != 0x10000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSaveFromParentEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x20000000) != 0x20000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isScrollContainer()
    {
        boolean flag = false;
        if((mPrivateFlags & 0x100000) != 0)
            flag = true;
        return flag;
    }

    public boolean isScrollbarFadingEnabled()
    {
        boolean flag;
        if(mScrollCache != null)
            flag = mScrollCache.fadeScrollBars;
        else
            flag = false;
        return flag;
    }

    public boolean isSelected()
    {
        boolean flag = false;
        if((mPrivateFlags & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean isShown()
    {
        Object obj = this;
        View view;
        do
        {
            if((((View) (obj)).mViewFlags & 0xc) != 0)
                return false;
            obj = ((View) (obj)).mParent;
            if(obj == null)
                return false;
            if(!(obj instanceof View))
                return true;
            view = (View)obj;
            obj = view;
        } while(view != null);
        return false;
    }

    public boolean isSoundEffectsEnabled()
    {
        boolean flag;
        if(0x8000000 == (mViewFlags & 0x8000000))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isTemporarilyDetached()
    {
        boolean flag = false;
        if((mPrivateFlags3 & 0x2000000) != 0)
            flag = true;
        return flag;
    }

    public boolean isTextAlignmentInherited()
    {
        boolean flag = false;
        if(getRawTextAlignment() == 0)
            flag = true;
        return flag;
    }

    public boolean isTextAlignmentResolved()
    {
        boolean flag;
        if((mPrivateFlags2 & 0x10000) == 0x10000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTextDirectionInherited()
    {
        boolean flag = false;
        if(getRawTextDirection() == 0)
            flag = true;
        return flag;
    }

    public boolean isTextDirectionResolved()
    {
        boolean flag;
        if((mPrivateFlags2 & 0x200) == 512)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isVerticalFadingEdgeEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x2000) == 8192)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isVerticalScrollBarEnabled()
    {
        boolean flag;
        if((mViewFlags & 0x200) == 512)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean isVerticalScrollBarHidden()
    {
        return false;
    }

    protected boolean isVisibleToUser()
    {
        return isVisibleToUser(null);
    }

    protected boolean isVisibleToUser(Rect rect)
    {
        if(mAttachInfo != null)
        {
            if(mAttachInfo.mWindowVisibility != 0)
                return false;
            for(Object obj = this; obj instanceof View; obj = ((View) (obj)).mParent)
                for(obj = (View)obj; ((View) (obj)).getAlpha() <= 0.0F || ((View) (obj)).getTransitionAlpha() <= 0.0F || ((View) (obj)).getVisibility() != 0;)
                    return false;


            Rect rect1 = mAttachInfo.mTmpInvalRect;
            Point point = mAttachInfo.mPoint;
            if(!getGlobalVisibleRect(rect1, point))
                return false;
            if(rect != null)
            {
                rect1.offset(-point.x, -point.y);
                return rect.intersect(rect1);
            } else
            {
                return true;
            }
        } else
        {
            return false;
        }
    }

    public void jumpDrawablesToCurrentState()
    {
        if(mBackground != null)
            mBackground.jumpToCurrentState();
        if(mStateListAnimator != null)
            mStateListAnimator.jumpToCurrentState();
        if(mDefaultFocusHighlight != null)
            mDefaultFocusHighlight.jumpToCurrentState();
        if(mForegroundInfo != null && ForegroundInfo._2D_get1(mForegroundInfo) != null)
            ForegroundInfo._2D_get1(mForegroundInfo).jumpToCurrentState();
    }

    public View keyboardNavigationClusterSearch(View view, int i)
    {
        if(isKeyboardNavigationCluster())
            view = this;
        if(isRootNamespace())
            return FocusFinder.getInstance().findNextKeyboardNavigationCluster(this, view, i);
        if(mParent != null)
            return mParent.keyboardNavigationClusterSearch(view, i);
        else
            return null;
    }

    public void layout(int i, int j, int k, int l)
    {
        if((mPrivateFlags3 & 8) != 0)
        {
            onMeasure(mOldWidthMeasureSpec, mOldHeightMeasureSpec);
            mPrivateFlags3 = mPrivateFlags3 & -9;
        }
        int i1 = mLeft;
        int j1 = mTop;
        int k1 = mBottom;
        int l1 = mRight;
        boolean flag;
        if(isLayoutModeOptical(mParent))
            flag = setOpticalFrame(i, j, k, l);
        else
            flag = setFrame(i, j, k, l);
        if(flag || (mPrivateFlags & 0x2000) == 8192)
        {
            onLayout(flag, i, j, k, l);
            Object obj;
            if(shouldDrawRoundScrollbar())
            {
                if(mRoundScrollbarRenderer == null)
                    mRoundScrollbarRenderer = new RoundScrollbarRenderer(this);
            } else
            {
                mRoundScrollbarRenderer = null;
            }
            mPrivateFlags = mPrivateFlags & 0xffffdfff;
            obj = mListenerInfo;
            if(obj != null && ListenerInfo._2D_get5(((ListenerInfo) (obj))) != null)
            {
                obj = (ArrayList)ListenerInfo._2D_get5(((ListenerInfo) (obj))).clone();
                int i2 = ((ArrayList) (obj)).size();
                for(int j2 = 0; j2 < i2; j2++)
                    ((OnLayoutChangeListener)((ArrayList) (obj)).get(j2)).onLayoutChange(this, i, j, k, l, i1, j1, l1, k1);

            }
        }
        mPrivateFlags = mPrivateFlags & 0xffffefff;
        mPrivateFlags3 = mPrivateFlags3 | 4;
        if((mPrivateFlags3 & 0x8000000) != 0)
        {
            mPrivateFlags3 = mPrivateFlags3 & 0xf7ffffff;
            notifyEnterOrExitForAutoFillIfNeeded(true);
        }
    }

    public void makeOptionalFitsSystemWindows()
    {
        setFlags(2048, 2048);
    }

    public void mapRectFromViewToScreenCoords(RectF rectf, boolean flag)
    {
        if(!hasIdentityMatrix())
            getMatrix().mapRect(rectf);
        rectf.offset(mLeft, mTop);
        Object obj;
        for(obj = mParent; obj instanceof View; obj = ((View) (obj)).mParent)
        {
            obj = (View)obj;
            rectf.offset(-((View) (obj)).mScrollX, -((View) (obj)).mScrollY);
            if(flag)
            {
                rectf.left = Math.max(rectf.left, 0.0F);
                rectf.top = Math.max(rectf.top, 0.0F);
                rectf.right = Math.min(rectf.right, ((View) (obj)).getWidth());
                rectf.bottom = Math.min(rectf.bottom, ((View) (obj)).getHeight());
            }
            if(!((View) (obj)).hasIdentityMatrix())
                ((View) (obj)).getMatrix().mapRect(rectf);
            rectf.offset(((View) (obj)).mLeft, ((View) (obj)).mTop);
        }

        if(obj instanceof ViewRootImpl)
            rectf.offset(0.0F, -((ViewRootImpl)obj).mCurScrollY);
        rectf.offset(mAttachInfo.mWindowLeft, mAttachInfo.mWindowTop);
    }

    public final void measure(int i, int j)
    {
        boolean flag = isLayoutModeOptical(this);
        int k = i;
        int l = j;
        if(flag != isLayoutModeOptical(mParent))
        {
            Insets insets = getOpticalInsets();
            k = insets.left + insets.right;
            l = insets.top + insets.bottom;
            int i1 = k;
            if(flag)
                i1 = -k;
            k = MeasureSpec.adjust(i, i1);
            i = l;
            if(flag)
                i = -l;
            l = MeasureSpec.adjust(j, i);
        }
        long l1 = (long)k << 32 | (long)l & 0xffffffffL;
        if(mMeasureCache == null)
            mMeasureCache = new LongSparseLongArray(2);
        boolean flag1;
        boolean flag2;
        if((mPrivateFlags & 0x1000) == 4096)
            flag2 = true;
        else
            flag2 = false;
        if(k == mOldWidthMeasureSpec)
        {
            if(l != mOldHeightMeasureSpec)
                i = 1;
            else
                i = 0;
        } else
        {
            i = 1;
        }
        if(MeasureSpec.getMode(k) == 0x40000000)
        {
            if(MeasureSpec.getMode(l) == 0x40000000)
                j = 1;
            else
                j = 0;
        } else
        {
            j = 0;
        }
        if(getMeasuredWidth() == MeasureSpec.getSize(k))
        {
            if(getMeasuredHeight() == MeasureSpec.getSize(l))
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = false;
        }
        if(i != 0)
        {
            if(!sAlwaysRemeasureExactly && (j ^ 1) == 0)
                i = flag1 ^ true;
            else
                i = 1;
        } else
        {
            i = 0;
        }
        if(flag2 || i != 0)
        {
            mPrivateFlags = mPrivateFlags & 0xfffff7ff;
            resolveRtlPropertiesIfNeeded();
            if(flag2)
                i = -1;
            else
                i = mMeasureCache.indexOfKey(l1);
            if(i < 0 || sIgnoreMeasureCache)
            {
                onMeasure(k, l);
                mPrivateFlags3 = mPrivateFlags3 & -9;
            } else
            {
                long l2 = mMeasureCache.valueAt(i);
                setMeasuredDimensionRaw((int)(l2 >> 32), (int)l2);
                mPrivateFlags3 = mPrivateFlags3 | 8;
            }
            if((mPrivateFlags & 0x800) != 2048)
                throw new IllegalStateException((new StringBuilder()).append("View with id ").append(getId()).append(": ").append(getClass().getName()).append("#onMeasure() did not set the").append(" measured dimension by calling").append(" setMeasuredDimension()").toString());
            mPrivateFlags = mPrivateFlags | 0x2000;
        }
        mOldWidthMeasureSpec = k;
        mOldHeightMeasureSpec = l;
        mMeasureCache.put(l1, (long)mMeasuredWidth << 32 | (long)mMeasuredHeight & 0xffffffffL);
    }

    void needGlobalAttributesUpdate(boolean flag)
    {
        AttachInfo attachinfo;
        attachinfo = mAttachInfo;
        break MISSING_BLOCK_LABEL_5;
        if(attachinfo != null && attachinfo.mRecomputeGlobalAttributes ^ true && (flag || attachinfo.mKeepScreenOn || attachinfo.mSystemUiVisibility != 0 || attachinfo.mHasSystemUiListeners))
            attachinfo.mRecomputeGlobalAttributes = true;
        return;
    }

    public void notifyConfirmedWebView(boolean flag)
    {
        View view;
        if(!mIsWebView || mAttachInfo == null || mAttachInfo.mRootView == null)
            break MISSING_BLOCK_LABEL_51;
        view = mAttachInfo.mRootView;
        if(!notifyWebView(view, flag))
            Log.w("ContentCatcher", "Failed to notify a WebView");
_L1:
        return;
        Exception exception;
        exception;
        Log.e("ContentCatcher", (new StringBuilder()).append("View.notifyConfirmedWebView-Exception: ").append(exception).toString());
          goto _L1
    }

    void notifyGlobalFocusCleared(View view)
    {
        if(view != null && mAttachInfo != null)
            mAttachInfo.mTreeObserver.dispatchOnGlobalFocusChange(view, null);
    }

    public void notifySubtreeAccessibilityStateChangedIfNeeded()
    {
        if(!AccessibilityManager.getInstance(mContext).isEnabled() || mAttachInfo == null)
            return;
        if((mPrivateFlags2 & 0x8000000) != 0)
            break MISSING_BLOCK_LABEL_63;
        mPrivateFlags2 = mPrivateFlags2 | 0x8000000;
        if(mParent == null)
            break MISSING_BLOCK_LABEL_63;
        mParent.notifySubtreeAccessibilityStateChanged(this, this, 1);
_L1:
        return;
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
          goto _L1
    }

    public void notifyViewAccessibilityStateChangedIfNeeded(int i)
    {
        if(!AccessibilityManager.getInstance(mContext).isEnabled() || mAttachInfo == null)
            return;
        if(getAccessibilityLiveRegion() == 0) goto _L2; else goto _L1
_L1:
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain();
        accessibilityevent.setEventType(2048);
        accessibilityevent.setContentChangeTypes(i);
        sendAccessibilityEventUnchecked(accessibilityevent);
_L4:
        return;
_L2:
        if(mParent != null)
            try
            {
                mParent.notifySubtreeAccessibilityStateChanged(this, this, i);
            }
            catch(AbstractMethodError abstractmethoderror)
            {
                Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void offsetLeftAndRight(int i)
    {
        if(i != 0)
        {
            boolean flag = hasIdentityMatrix();
            if(flag)
            {
                if(isHardwareAccelerated())
                {
                    invalidateViewProperty(false, false);
                } else
                {
                    ViewParent viewparent = mParent;
                    if(viewparent != null && mAttachInfo != null)
                    {
                        Rect rect = mAttachInfo.mTmpInvalRect;
                        int j;
                        int k;
                        if(i < 0)
                        {
                            j = mLeft + i;
                            k = mRight;
                        } else
                        {
                            j = mLeft;
                            k = mRight + i;
                        }
                        rect.set(0, 0, k - j, mBottom - mTop);
                        viewparent.invalidateChild(this, rect);
                    }
                }
            } else
            {
                invalidateViewProperty(false, false);
            }
            mLeft = mLeft + i;
            mRight = mRight + i;
            mRenderNode.offsetLeftAndRight(i);
            if(isHardwareAccelerated())
            {
                invalidateViewProperty(false, false);
                invalidateParentIfNeededAndWasQuickRejected();
            } else
            {
                if(!flag)
                    invalidateViewProperty(false, true);
                invalidateParentIfNeeded();
            }
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void offsetTopAndBottom(int i)
    {
        if(i != 0)
        {
            boolean flag = hasIdentityMatrix();
            if(flag)
            {
                if(isHardwareAccelerated())
                {
                    invalidateViewProperty(false, false);
                } else
                {
                    ViewParent viewparent = mParent;
                    if(viewparent != null && mAttachInfo != null)
                    {
                        Rect rect = mAttachInfo.mTmpInvalRect;
                        int j;
                        int k;
                        int l;
                        if(i < 0)
                        {
                            j = mTop + i;
                            k = mBottom;
                            l = i;
                        } else
                        {
                            j = mTop;
                            k = mBottom + i;
                            l = 0;
                        }
                        rect.set(0, l, mRight - mLeft, k - j);
                        viewparent.invalidateChild(this, rect);
                    }
                }
            } else
            {
                invalidateViewProperty(false, false);
            }
            mTop = mTop + i;
            mBottom = mBottom + i;
            mRenderNode.offsetTopAndBottom(i);
            if(isHardwareAccelerated())
            {
                invalidateViewProperty(false, false);
                invalidateParentIfNeededAndWasQuickRejected();
            } else
            {
                if(!flag)
                    invalidateViewProperty(false, true);
                invalidateParentIfNeeded();
            }
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
    }

    protected void onAnimationEnd()
    {
        mPrivateFlags = mPrivateFlags & 0xfffeffff;
    }

    protected void onAnimationStart()
    {
        mPrivateFlags = mPrivateFlags | 0x10000;
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowinsets)
    {
        if((mPrivateFlags3 & 0x40) == 0)
        {
            if(fitSystemWindows(windowinsets.getSystemWindowInsets()))
                return windowinsets.consumeSystemWindowInsets();
        } else
        if(fitSystemWindowsInt(windowinsets.getSystemWindowInsets()))
            return windowinsets.consumeSystemWindowInsets();
        return windowinsets;
    }

    protected void onAttachedToWindow()
    {
        if((mPrivateFlags & 0x200) != 0)
            mParent.requestTransparentRegion(this);
        mPrivateFlags3 = mPrivateFlags3 & -5;
        jumpDrawablesToCurrentState();
        resetSubtreeAccessibilityStateChanged();
        rebuildOutline();
        if(isFocused())
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null)
                inputmethodmanager.focusIn(this);
        }
    }

    public void onCancelPendingInputEvents()
    {
        removePerformClickCallback();
        cancelLongPress();
        mPrivateFlags3 = mPrivateFlags3 | 0x10;
    }

    public boolean onCapturedPointerEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onCheckIsTextEditor()
    {
        return false;
    }

    public void onCloseSystemDialogs(String s)
    {
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
    }

    protected void onCreateContextMenu(ContextMenu contextmenu)
    {
    }

    protected int[] onCreateDrawableState(int i)
    {
        if((mViewFlags & 0x400000) == 0x400000 && (mParent instanceof View))
            return ((View)mParent).onCreateDrawableState(i);
        int j = mPrivateFlags;
        int k = 0;
        if((j & 0x4000) != 0)
            k = 16;
        int l = k;
        if((mViewFlags & 0x20) == 0)
            l = k | 8;
        k = l;
        if(isFocused())
            k = l | 4;
        l = k;
        if((j & 4) != 0)
            l = k | 2;
        k = l;
        if(hasWindowFocus())
            k = l | 1;
        l = k;
        if((0x40000000 & j) != 0)
            l = k | 0x20;
        k = l;
        if(mAttachInfo != null)
        {
            k = l;
            if(mAttachInfo.mHardwareAccelerationRequested)
            {
                k = l;
                if(ThreadedRenderer.isAvailable())
                    k = l | 0x40;
            }
        }
        l = k;
        if((0x10000000 & j) != 0)
            l = k | 0x80;
        j = mPrivateFlags2;
        k = l;
        if((j & 1) != 0)
            k = l | 0x100;
        l = k;
        if((j & 2) != 0)
            l = k | 0x200;
        int ai[] = StateSet.get(l);
        if(i == 0)
            return ai;
        int ai1[];
        if(ai != null)
        {
            ai1 = new int[ai.length + i];
            System.arraycopy(ai, 0, ai1, 0, ai.length);
        } else
        {
            ai1 = new int[i];
        }
        return ai1;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        return null;
    }

    protected void onDetachedFromWindow()
    {
    }

    protected void onDetachedFromWindowInternal()
    {
        mPrivateFlags = mPrivateFlags & 0xfbffffff;
        mPrivateFlags3 = mPrivateFlags3 & -5;
        mPrivateFlags3 = mPrivateFlags3 & 0xfdffffff;
        removeUnsetPressCallback();
        removeLongPressCallback();
        removePerformClickCallback();
        removeSendViewScrolledAccessibilityEventCallback();
        stopNestedScroll();
        jumpDrawablesToCurrentState();
        destroyDrawingCache();
        cleanupDraw();
        mCurrentAnimation = null;
        if((mViewFlags & 0x40000000) == 0x40000000)
            hideTooltip();
    }

    protected void onDisplayHint(int i)
    {
    }

    public boolean onDragEvent(DragEvent dragevent)
    {
        return false;
    }

    protected void onDraw(Canvas canvas)
    {
    }

    public void onDrawForeground(Canvas canvas)
    {
        onDrawScrollIndicators(canvas);
        onDrawScrollBars(canvas);
        Drawable drawable;
        if(mForegroundInfo != null)
            drawable = ForegroundInfo._2D_get1(mForegroundInfo);
        else
            drawable = null;
        if(drawable != null)
        {
            if(ForegroundInfo._2D_get0(mForegroundInfo))
            {
                ForegroundInfo._2D_set0(mForegroundInfo, false);
                Rect rect = ForegroundInfo._2D_get5(mForegroundInfo);
                Rect rect1 = ForegroundInfo._2D_get4(mForegroundInfo);
                int i;
                if(ForegroundInfo._2D_get3(mForegroundInfo))
                    rect.set(0, 0, getWidth(), getHeight());
                else
                    rect.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
                i = getLayoutDirection();
                Gravity.apply(ForegroundInfo._2D_get2(mForegroundInfo), drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect1, i);
                drawable.setBounds(rect1);
            }
            drawable.draw(canvas);
        }
    }

    protected void onDrawHorizontalScrollBar(Canvas canvas, Drawable drawable, int i, int j, int k, int l)
    {
        drawable.setBounds(i, j, k, l);
        drawable.draw(canvas);
    }

    protected final void onDrawScrollBars(Canvas canvas)
    {
        Object obj = mScrollCache;
        if(obj == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag1;
        boolean flag2;
        int i = ((ScrollabilityCache) (obj)).state;
        if(i == 0)
            return;
        flag1 = false;
        if(i == 2)
        {
            if(((ScrollabilityCache) (obj)).interpolatorValues == null)
                obj.interpolatorValues = new float[1];
            Object obj1 = ((ScrollabilityCache) (obj)).interpolatorValues;
            if(((ScrollabilityCache) (obj)).scrollBarInterpolator.timeToValues(((float []) (obj1))) == android.graphics.Interpolator.Result.FREEZE_END)
                obj.state = 0;
            else
                ((ScrollabilityCache) (obj)).scrollBar.mutate().setAlpha(Math.round(obj1[0]));
            flag1 = true;
        } else
        {
            ((ScrollabilityCache) (obj)).scrollBar.mutate().setAlpha(255);
        }
        flag2 = isHorizontalScrollBarEnabled();
        if(isVerticalScrollBarEnabled())
            flag = isVerticalScrollBarHidden() ^ true;
        else
            flag = false;
        if(mRoundScrollbarRenderer == null) goto _L4; else goto _L3
_L3:
        if(flag)
        {
            obj1 = ((ScrollabilityCache) (obj)).mScrollBarBounds;
            getVerticalScrollBarBounds(((Rect) (obj1)), null);
            mRoundScrollbarRenderer.drawRoundScrollbars(canvas, (float)((ScrollabilityCache) (obj)).scrollBar.getAlpha() / 255F, ((Rect) (obj1)));
            if(flag1)
                invalidate();
        }
_L2:
        return;
_L4:
        if(flag || flag2)
        {
            ScrollBarDrawable scrollbardrawable = ((ScrollabilityCache) (obj)).scrollBar;
            if(flag2)
            {
                scrollbardrawable.setParameters(computeHorizontalScrollRange(), computeHorizontalScrollOffset(), computeHorizontalScrollExtent(), false);
                Rect rect = ((ScrollabilityCache) (obj)).mScrollBarBounds;
                getHorizontalScrollBarBounds(rect, null);
                onDrawHorizontalScrollBar(canvas, scrollbardrawable, rect.left, rect.top, rect.right, rect.bottom);
                if(flag1)
                    invalidate(rect);
            }
            if(flag)
            {
                scrollbardrawable.setParameters(computeVerticalScrollRange(), computeVerticalScrollOffset(), computeVerticalScrollExtent(), true);
                obj = ((ScrollabilityCache) (obj)).mScrollBarBounds;
                getVerticalScrollBarBounds(((Rect) (obj)), null);
                onDrawVerticalScrollBar(canvas, scrollbardrawable, ((Rect) (obj)).left, ((Rect) (obj)).top, ((Rect) (obj)).right, ((Rect) (obj)).bottom);
                if(flag1)
                    invalidate(((Rect) (obj)));
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void onDrawVerticalScrollBar(Canvas canvas, Drawable drawable, int i, int j, int k, int l)
    {
        drawable.setBounds(i, j, k, l);
        drawable.draw(canvas);
    }

    public boolean onFilterTouchEventForSecurity(MotionEvent motionevent)
    {
        return (mViewFlags & 0x400) == 0 || (motionevent.getFlags() & 1) == 0;
    }

    protected void onFinishInflate()
    {
    }

    public void onFinishTemporaryDetach()
    {
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        if(flag)
            sendAccessibilityEvent(8);
        else
            notifyViewAccessibilityStateChangedIfNeeded(0);
        switchDefaultFocusHighlight();
        rect = InputMethodManager.peekInstance();
        if(flag) goto _L2; else goto _L1
_L1:
        if(isPressed())
            setPressed(false);
        if(rect != null && mAttachInfo != null && mAttachInfo.mHasWindowFocus)
            rect.focusOut(this);
        onFocusLost();
_L4:
        invalidate(true);
        rect = mListenerInfo;
        if(rect != null && ((ListenerInfo) (rect)).mOnFocusChangeListener != null)
            ((ListenerInfo) (rect)).mOnFocusChangeListener.onFocusChange(this, flag);
        if(mAttachInfo != null)
            mAttachInfo.mKeyDispatchState.reset(this);
        notifyEnterOrExitForAutoFillIfNeeded(flag);
        return;
_L2:
        if(rect != null && mAttachInfo != null && mAttachInfo.mHasWindowFocus)
            rect.focusIn(this);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onFocusLost()
    {
        resetPressedState();
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        return false;
    }

    public void onHoverChanged(boolean flag)
    {
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if(mSendingHoverAccessibilityEvents) goto _L2; else goto _L1
_L1:
        if((i == 9 || i == 7) && hasHoveredChild() ^ true && pointInView(motionevent.getX(), motionevent.getY()))
        {
            sendAccessibilityHoverEvent(128);
            mSendingHoverAccessibilityEvents = true;
        }
_L9:
        if((i == 9 || i == 7) && motionevent.isFromSource(8194) && isOnScrollbar(motionevent.getX(), motionevent.getY()))
            awakenScrollBars();
        if(!isHoverable() && !isHovered()) goto _L4; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 9 10: default 140
    //                   9 191
    //                   10 199;
           goto _L5 _L6 _L7
_L5:
        dispatchGenericMotionEventInternal(motionevent);
        return true;
_L2:
        if(i == 10 || i == 2 && pointInView(motionevent.getX(), motionevent.getY()) ^ true)
        {
            mSendingHoverAccessibilityEvents = false;
            sendAccessibilityHoverEvent(256);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        setHovered(true);
        continue; /* Loop/switch isn't completed */
_L7:
        setHovered(false);
        if(true) goto _L5; else goto _L4
_L4:
        return false;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if(mAccessibilityDelegate != null)
            mAccessibilityDelegate.onInitializeAccessibilityEvent(this, accessibilityevent);
        else
            onInitializeAccessibilityEventInternal(accessibilityevent);
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        accessibilityevent.setSource(this);
        accessibilityevent.setClassName(getAccessibilityClassName());
        accessibilityevent.setPackageName(getContext().getPackageName());
        accessibilityevent.setEnabled(isEnabled());
        accessibilityevent.setContentDescription(mContentDescription);
        accessibilityevent.getEventType();
        JVM INSTR lookupswitch 2: default 72
    //                   8: 73
    //                   8192: 140;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        ArrayList arraylist;
        if(mAttachInfo != null)
            arraylist = mAttachInfo.mTempArrayList;
        else
            arraylist = new ArrayList();
        getRootView().addFocusables(arraylist, 2, 0);
        accessibilityevent.setItemCount(arraylist.size());
        accessibilityevent.setCurrentItemIndex(arraylist.indexOf(this));
        if(mAttachInfo != null)
            arraylist.clear();
        continue; /* Loop/switch isn't completed */
_L3:
        CharSequence charsequence = getIterableTextForAccessibility();
        if(charsequence != null && charsequence.length() > 0)
        {
            accessibilityevent.setFromIndex(getAccessibilitySelectionStart());
            accessibilityevent.setToIndex(getAccessibilitySelectionEnd());
            accessibilityevent.setItemCount(charsequence.length());
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        if(mAccessibilityDelegate != null)
            mAccessibilityDelegate.onInitializeAccessibilityNodeInfo(this, accessibilitynodeinfo);
        else
            onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        if(mAttachInfo == null)
            return;
        Object obj = mAttachInfo.mTmpInvalRect;
        getDrawingRect(((Rect) (obj)));
        accessibilitynodeinfo.setBoundsInParent(((Rect) (obj)));
        getBoundsOnScreen(((Rect) (obj)), true);
        accessibilitynodeinfo.setBoundsInScreen(((Rect) (obj)));
        obj = getParentForAccessibility();
        if(obj instanceof View)
            accessibilitynodeinfo.setParent((View)obj);
        if(mID != -1)
        {
            View view = getRootView();
            Object obj1 = view;
            if(view == null)
                obj1 = this;
            obj1 = ((View) (obj1)).findLabelForView(this, mID);
            if(obj1 != null)
                accessibilitynodeinfo.setLabeledBy(((View) (obj1)));
            if((mAttachInfo.mAccessibilityFetchFlags & 0x10) != 0 && Resources.resourceHasPackage(mID))
                try
                {
                    accessibilitynodeinfo.setViewIdResourceName(getResources().getResourceName(mID));
                }
                catch(android.content.res.Resources.NotFoundException notfoundexception) { }
        }
        if(mLabelForId != -1)
        {
            view = getRootView();
            obj1 = view;
            if(view == null)
                obj1 = this;
            obj1 = ((View) (obj1)).findViewInsideOutShouldExist(this, mLabelForId);
            if(obj1 != null)
                accessibilitynodeinfo.setLabelFor(((View) (obj1)));
        }
        if(mAccessibilityTraversalBeforeId != -1)
        {
            view = getRootView();
            obj1 = view;
            if(view == null)
                obj1 = this;
            obj1 = ((View) (obj1)).findViewInsideOutShouldExist(this, mAccessibilityTraversalBeforeId);
            if(obj1 != null && ((View) (obj1)).includeForAccessibility())
                accessibilitynodeinfo.setTraversalBefore(((View) (obj1)));
        }
        if(mAccessibilityTraversalAfterId != -1)
        {
            view = getRootView();
            obj1 = view;
            if(view == null)
                obj1 = this;
            obj1 = ((View) (obj1)).findViewInsideOutShouldExist(this, mAccessibilityTraversalAfterId);
            if(obj1 != null && ((View) (obj1)).includeForAccessibility())
                accessibilitynodeinfo.setTraversalAfter(((View) (obj1)));
        }
        accessibilitynodeinfo.setVisibleToUser(isVisibleToUser());
        accessibilitynodeinfo.setImportantForAccessibility(isImportantForAccessibility());
        accessibilitynodeinfo.setPackageName(mContext.getPackageName());
        accessibilitynodeinfo.setClassName(getAccessibilityClassName());
        accessibilitynodeinfo.setContentDescription(getContentDescription());
        accessibilitynodeinfo.setEnabled(isEnabled());
        accessibilitynodeinfo.setClickable(isClickable());
        accessibilitynodeinfo.setFocusable(isFocusable());
        accessibilitynodeinfo.setFocused(isFocused());
        accessibilitynodeinfo.setAccessibilityFocused(isAccessibilityFocused());
        accessibilitynodeinfo.setSelected(isSelected());
        accessibilitynodeinfo.setLongClickable(isLongClickable());
        accessibilitynodeinfo.setContextClickable(isContextClickable());
        accessibilitynodeinfo.setLiveRegion(getAccessibilityLiveRegion());
        accessibilitynodeinfo.addAction(4);
        accessibilitynodeinfo.addAction(8);
        if(isFocusable())
            if(isFocused())
                accessibilitynodeinfo.addAction(2);
            else
                accessibilitynodeinfo.addAction(1);
        if(!isAccessibilityFocused())
            accessibilitynodeinfo.addAction(64);
        else
            accessibilitynodeinfo.addAction(128);
        if(isClickable() && isEnabled())
            accessibilitynodeinfo.addAction(16);
        if(isLongClickable() && isEnabled())
            accessibilitynodeinfo.addAction(32);
        if(isContextClickable() && isEnabled())
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK);
        obj1 = getIterableTextForAccessibility();
        if(obj1 != null && ((CharSequence) (obj1)).length() > 0)
        {
            accessibilitynodeinfo.setTextSelection(getAccessibilitySelectionStart(), getAccessibilitySelectionEnd());
            accessibilitynodeinfo.addAction(0x20000);
            accessibilitynodeinfo.addAction(256);
            accessibilitynodeinfo.addAction(512);
            accessibilitynodeinfo.setMovementGranularities(11);
        }
        accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN);
        populateAccessibilityNodeInfoDrawingOrderInParent(accessibilitynodeinfo);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        SeempLog.record(4);
        if(KeyEvent.isConfirmKey(i))
        {
            if((mViewFlags & 0x20) == 32)
                return true;
            if(keyevent.getRepeatCount() == 0)
            {
                if((mViewFlags & 0x4000) != 16384)
                {
                    if((mViewFlags & 0x200000) == 0x200000)
                        i = 1;
                    else
                        i = 0;
                } else
                {
                    i = 1;
                }
                if(i != 0 || (mViewFlags & 0x40000000) == 0x40000000)
                {
                    float f = (float)getWidth() / 2.0F;
                    float f1 = (float)getHeight() / 2.0F;
                    if(i != 0)
                        setPressed(true, f, f1);
                    checkForLongClick(0, f, f1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        SeempLog.record(5);
        if(KeyEvent.isConfirmKey(i))
        {
            if((mViewFlags & 0x20) == 32)
                return true;
            if((mViewFlags & 0x4000) == 16384 && isPressed())
            {
                setPressed(false);
                if(!mHasPerformedLongPress)
                {
                    removeLongPressCallback();
                    if(!keyevent.isCanceled())
                        return performClick();
                }
            }
        }
        return false;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), j));
    }

    public void onMovedToDisplay(int i, Configuration configuration)
    {
    }

    protected void onOverScrolled(int i, int j, boolean flag, boolean flag1)
    {
    }

    public void onPointerCaptureChange(boolean flag)
    {
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if(mAccessibilityDelegate != null)
            mAccessibilityDelegate.onPopulateAccessibilityEvent(this, accessibilityevent);
        else
            onPopulateAccessibilityEventInternal(accessibilityevent);
    }

    public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
    }

    public void onProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        onProvideStructureForAssistOrAutofill(viewstructure, true, i);
    }

    public void onProvideAutofillVirtualStructure(ViewStructure viewstructure, int i)
    {
    }

    public void onProvideStructure(ViewStructure viewstructure)
    {
        onProvideStructureForAssistOrAutofill(viewstructure, false, 0);
    }

    public void onProvideVirtualStructure(ViewStructure viewstructure)
    {
        AccessibilityNodeProvider accessibilitynodeprovider = getAccessibilityNodeProvider();
        if(accessibilitynodeprovider != null)
        {
            AccessibilityNodeInfo accessibilitynodeinfo = createAccessibilityNodeInfo();
            viewstructure.setChildCount(1);
            populateVirtualStructure(viewstructure.newChild(0), accessibilitynodeprovider, accessibilitynodeinfo);
            accessibilitynodeinfo.recycle();
        }
    }

    public void onResolveDrawables(int i)
    {
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        float f = motionevent.getX(i);
        float f1 = motionevent.getY(i);
        if(isDraggingScrollBar() || isOnScrollbarThumb(f, f1))
            return PointerIcon.getSystemIcon(mContext, 1000);
        else
            return mPointerIcon;
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        mPrivateFlags = mPrivateFlags | 0x20000;
        if(parcelable != null && (parcelable instanceof AbsSavedState) ^ true)
            throw new IllegalArgumentException((new StringBuilder()).append("Wrong state class, expecting View State but received ").append(parcelable.getClass().toString()).append(" instead. This usually happens ").append("when two views of different type have the same id in the same hierarchy. ").append("This view's id is ").append(ViewDebug.resolveId(mContext, getId())).append(". Make sure ").append("other views do not use the same id.").toString());
        if(parcelable != null && (parcelable instanceof BaseSavedState))
        {
            BaseSavedState basesavedstate = (BaseSavedState)parcelable;
            if((basesavedstate.mSavedData & 1) != 0)
                mStartActivityRequestWho = basesavedstate.mStartActivityRequestWhoSaved;
            if((basesavedstate.mSavedData & 2) != 0)
                setAutofilled(basesavedstate.mIsAutofilled);
            if((basesavedstate.mSavedData & 4) != 0)
            {
                parcelable = (BaseSavedState)parcelable;
                parcelable.mSavedData = ((BaseSavedState) (parcelable)).mSavedData & -5;
                mAutofillViewId = basesavedstate.mAutofillViewId;
            }
        }
    }

    public void onRtlPropertiesChanged(int i)
    {
    }

    protected Parcelable onSaveInstanceState()
    {
        mPrivateFlags = mPrivateFlags | 0x20000;
        if(mStartActivityRequestWho != null || isAutofilled() || mAutofillViewId > 0x3fffffff)
        {
            BaseSavedState basesavedstate = new BaseSavedState(AbsSavedState.EMPTY_STATE);
            if(mStartActivityRequestWho != null)
                basesavedstate.mSavedData = basesavedstate.mSavedData | 1;
            if(isAutofilled())
                basesavedstate.mSavedData = basesavedstate.mSavedData | 2;
            if(mAutofillViewId > 0x3fffffff)
                basesavedstate.mSavedData = basesavedstate.mSavedData | 4;
            basesavedstate.mStartActivityRequestWhoSaved = mStartActivityRequestWho;
            basesavedstate.mIsAutofilled = isAutofilled();
            basesavedstate.mAutofillViewId = mAutofillViewId;
            return basesavedstate;
        } else
        {
            return BaseSavedState.EMPTY_STATE;
        }
    }

    public void onScreenStateChanged(int i)
    {
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        notifySubtreeAccessibilityStateChangedIfNeeded();
        if(AccessibilityManager.getInstance(mContext).isEnabled())
            postSendViewScrolledAccessibilityEventCallback();
        mBackgroundSizeChanged = true;
        mDefaultFocusHighlightSizeChanged = true;
        if(mForegroundInfo != null)
            ForegroundInfo._2D_set0(mForegroundInfo, true);
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
            attachinfo.mViewScrollChanged = true;
        if(mListenerInfo != null && mListenerInfo.mOnScrollChangeListener != null)
            mListenerInfo.mOnScrollChangeListener.onScrollChange(this, i, j, k, l);
    }

    protected boolean onSetAlpha(int i)
    {
        return false;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
    }

    public void onStartTemporaryDetach()
    {
        removeUnsetPressCallback();
        mPrivateFlags = mPrivateFlags | 0x4000000;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        float f;
        float f1;
        int i;
        int j;
        boolean flag1;
        SeempLog.record(3);
        f = motionevent.getX();
        f1 = motionevent.getY();
        i = mViewFlags;
        j = motionevent.getAction();
        if((i & 0x4000) == 16384 || (0x200000 & i) == 0x200000)
            flag1 = true;
        else
        if((0x800000 & i) == 0x800000)
            flag1 = true;
        else
            flag1 = false;
        if((i & 0x20) == 32)
        {
            if(j == 1 && (mPrivateFlags & 0x4000) != 0)
                setPressed(false);
            mPrivateFlags3 = mPrivateFlags3 & 0xfffdffff;
            return flag1;
        }
        if(mTouchDelegate != null && mTouchDelegate.onTouchEvent(motionevent))
            return true;
        if(!flag1 && (0x40000000 & i) != 0x40000000) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 0 3: default 192
    //                   0 477
    //                   1 194
    //                   2 676
    //                   3 628;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        return true;
_L5:
        mPrivateFlags3 = mPrivateFlags3 & 0xfffdffff;
        if((0x40000000 & i) == 0x40000000)
            handleTooltipUp();
        if(!flag1)
        {
            removeTapCallback();
            removeLongPressCallback();
            mInContextButtonPress = false;
            mHasPerformedLongPress = false;
            mIgnoreNextUpEvent = false;
            continue; /* Loop/switch isn't completed */
        }
        boolean flag;
        if((mPrivateFlags & 0x2000000) != 0)
            flag = true;
        else
            flag = false;
        if((mPrivateFlags & 0x4000) != 0 || flag)
        {
            boolean flag2 = false;
            flag1 = flag2;
            if(isFocusable())
            {
                flag1 = flag2;
                if(isFocusableInTouchMode())
                {
                    flag1 = flag2;
                    if(isFocused() ^ true)
                        flag1 = requestFocus();
                }
            }
            if(flag)
                setPressed(true, f, f1);
            if(!mHasPerformedLongPress && mIgnoreNextUpEvent ^ true)
            {
                removeLongPressCallback();
                if(!flag1)
                {
                    if(mPerformClick == null)
                        mPerformClick = new PerformClick(null);
                    if(!post(mPerformClick))
                        performClick();
                }
            }
            if(mUnsetPressedState == null)
                mUnsetPressedState = new UnsetPressedState(null);
            if(flag)
                postDelayed(mUnsetPressedState, ViewConfiguration.getPressedStateDuration());
            else
            if(!post(mUnsetPressedState))
                mUnsetPressedState.run();
            removeTapCallback();
        }
        mIgnoreNextUpEvent = false;
        continue; /* Loop/switch isn't completed */
_L4:
        if(motionevent.getSource() == 4098)
            mPrivateFlags3 = mPrivateFlags3 | 0x20000;
        mHasPerformedLongPress = false;
        if(!flag1)
            checkForLongClick(0, f, f1);
        else
        if(!performButtonActionOnTouchDown(motionevent))
            if(isInScrollingContainer())
            {
                mPrivateFlags = mPrivateFlags | 0x2000000;
                if(mPendingCheckForTap == null)
                    mPendingCheckForTap = new CheckForTap(null);
                mPendingCheckForTap.x = motionevent.getX();
                mPendingCheckForTap.y = motionevent.getY();
                postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
            } else
            {
                setPressed(true, f, f1);
                checkForLongClick(0, f, f1);
                performHapticFeedback(1, 4);
            }
        continue; /* Loop/switch isn't completed */
_L7:
        if(flag1)
            setPressed(false);
        removeTapCallback();
        removeLongPressCallback();
        mInContextButtonPress = false;
        mHasPerformedLongPress = false;
        mIgnoreNextUpEvent = false;
        mPrivateFlags3 = mPrivateFlags3 & 0xfffdffff;
        continue; /* Loop/switch isn't completed */
_L6:
        if(flag1)
            drawableHotspotChanged(f, f1);
        if(!pointInView(f, f1, mTouchSlop))
        {
            removeTapCallback();
            removeLongPressCallback();
            if((mPrivateFlags & 0x4000) != 0)
                setPressed(false);
            mPrivateFlags3 = mPrivateFlags3 & 0xfffdffff;
        }
        if(true) goto _L3; else goto _L2
_L2:
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        return false;
    }

    public void onVisibilityAggregated(boolean flag)
    {
        if(flag && mAttachInfo != null)
            initialAwakenScrollBars();
        Object obj = mBackground;
        if(obj != null && flag != ((Drawable) (obj)).isVisible())
            ((Drawable) (obj)).setVisible(flag, false);
        obj = mDefaultFocusHighlight;
        if(obj != null && flag != ((Drawable) (obj)).isVisible())
            ((Drawable) (obj)).setVisible(flag, false);
        if(mForegroundInfo != null)
            obj = ForegroundInfo._2D_get1(mForegroundInfo);
        else
            obj = null;
        if(obj != null && flag != ((Drawable) (obj)).isVisible())
            ((Drawable) (obj)).setVisible(flag, false);
        if(isAutofillable())
        {
            obj = getAutofillManager();
            if(obj != null && getAutofillViewId() > 0x3fffffff)
            {
                if(mVisibilityChangeForAutofillHandler != null)
                    mVisibilityChangeForAutofillHandler.removeMessages(0);
                if(flag)
                {
                    ((AutofillManager) (obj)).notifyViewVisibilityChanged(this, true);
                } else
                {
                    if(mVisibilityChangeForAutofillHandler == null)
                        mVisibilityChangeForAutofillHandler = new VisibilityChangeForAutofillHandler(((AutofillManager) (obj)), this, null);
                    mVisibilityChangeForAutofillHandler.obtainMessage(0, this).sendToTarget();
                }
            }
        }
    }

    protected void onVisibilityChanged(View view, int i)
    {
    }

    public void onWindowFocusChanged(boolean flag)
    {
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        if(flag) goto _L2; else goto _L1
_L1:
        if(isPressed())
            setPressed(false);
        mPrivateFlags3 = mPrivateFlags3 & 0xfffdffff;
        if(inputmethodmanager != null && (mPrivateFlags & 2) != 0)
            inputmethodmanager.focusOut(this);
        removeLongPressCallback();
        removeTapCallback();
        onFocusLost();
_L4:
        notifyEnterOrExitForAutoFillIfNeeded(flag);
        refreshDrawableState();
        return;
_L2:
        if(inputmethodmanager != null && (mPrivateFlags & 2) != 0)
            inputmethodmanager.focusIn(this);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onWindowSystemUiVisibilityChanged(int i)
    {
    }

    protected void onWindowVisibilityChanged(int i)
    {
        if(i == 0)
            initialAwakenScrollBars();
    }

    public void outputDirtyFlags(String s, boolean flag, int i)
    {
        Log.d("View", (new StringBuilder()).append(s).append(this).append("             DIRTY(").append(mPrivateFlags & 0x600000).append(") DRAWN(").append(mPrivateFlags & 0x20).append(")").append(" CACHE_VALID(").append(mPrivateFlags & 0x8000).append(") INVALIDATED(").append(mPrivateFlags & 0x80000000).append(")").toString());
        if(flag)
            mPrivateFlags = mPrivateFlags & i;
        if(this instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)this;
            int j = viewgroup.getChildCount();
            for(int k = 0; k < j; k++)
                viewgroup.getChildAt(k).outputDirtyFlags((new StringBuilder()).append(s).append("  ").toString(), flag, i);

        }
    }

    protected boolean overScrollBy(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, boolean flag)
    {
        int i2 = mOverScrollMode;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if(computeHorizontalScrollRange() > computeHorizontalScrollExtent())
            flag1 = true;
        else
            flag1 = false;
        if(computeVerticalScrollRange() > computeVerticalScrollExtent())
            flag2 = true;
        else
            flag2 = false;
        if(i2 != 0)
        {
            if(i2 != 1)
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        if(i2 != 0)
        {
            if(i2 != 1)
                flag2 = false;
        } else
        {
            flag2 = true;
        }
        k += i;
        if(!flag1)
            k1 = 0;
        l += j;
        if(!flag2)
            l1 = 0;
        j = -k1;
        i = k1 + i1;
        i1 = -l1;
        j1 = l1 + j1;
        flag3 = false;
        if(k > i)
        {
            flag3 = true;
        } else
        {
            i = k;
            if(k < j)
            {
                i = j;
                flag3 = true;
            }
        }
        flag = false;
        if(l > j1)
        {
            j = j1;
            flag = true;
        } else
        {
            j = l;
            if(l < i1)
            {
                j = i1;
                flag = true;
            }
        }
        onOverScrolled(i, j, flag3, flag);
        if(flag3)
            flag = true;
        return flag;
    }

    public boolean performAccessibilityAction(int i, Bundle bundle)
    {
        if(mAccessibilityDelegate != null)
            return mAccessibilityDelegate.performAccessibilityAction(this, i, bundle);
        else
            return performAccessibilityActionInternal(i, bundle);
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(isNestedScrollingEnabled() && (i == 8192 || i == 4096 || i == 0x1020038 || i == 0x1020039 || i == 0x102003a || i == 0x102003b) && dispatchNestedPrePerformAccessibilityAction(i, bundle))
            return true;
        i;
        JVM INSTR lookupswitch 13: default 176
    //                   1: 206
    //                   2: 227
    //                   4: 245
    //                   8: 262
    //                   16: 178
    //                   32: 192
    //                   64: 281
    //                   128: 293
    //                   256: 306
    //                   512: 330
    //                   131072: 354
    //                   16908342: 433
    //                   16908348: 460;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
        return false;
_L6:
        if(isClickable())
        {
            performClick();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if(isLongClickable())
        {
            performLongClick();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(!hasFocus())
        {
            getViewRootImpl().ensureTouchMode(false);
            return requestFocus();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(hasFocus())
        {
            clearFocus();
            return isFocused() ^ true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(!isSelected())
        {
            setSelected(true);
            return isSelected();
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(isSelected())
        {
            setSelected(false);
            return isSelected() ^ true;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if(!isAccessibilityFocused())
            return requestAccessibilityFocus();
        continue; /* Loop/switch isn't completed */
_L9:
        if(isAccessibilityFocused())
        {
            clearAccessibilityFocus();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if(bundle != null)
            return traverseAtGranularity(bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT"), true, bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN"));
        continue; /* Loop/switch isn't completed */
_L11:
        if(bundle != null)
            return traverseAtGranularity(bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT"), false, bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN"));
        if(true) goto _L1; else goto _L12
_L12:
        if(getIterableTextForAccessibility() == null)
            return false;
        int j;
        if(bundle != null)
            i = bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT", -1);
        else
            i = -1;
        if(bundle != null)
            j = bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT", -1);
        else
            j = -1;
        if((getAccessibilitySelectionStart() != i || getAccessibilitySelectionEnd() != j) && i == j)
        {
            setAccessibilitySelection(i, j);
            notifyViewAccessibilityStateChangedIfNeeded(0);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L13:
        if(mAttachInfo != null)
        {
            bundle = mAttachInfo.mTmpInvalRect;
            getDrawingRect(bundle);
            return requestRectangleOnScreen(bundle, true);
        }
        continue; /* Loop/switch isn't completed */
_L14:
        if(isContextClickable())
        {
            performContextClick();
            return true;
        }
        if(true) goto _L1; else goto _L15
_L15:
    }

    protected boolean performButtonActionOnTouchDown(MotionEvent motionevent)
    {
        if(motionevent.isFromSource(8194) && (motionevent.getButtonState() & 2) != 0)
        {
            showContextMenu(motionevent.getX(), motionevent.getY());
            mPrivateFlags = mPrivateFlags | 0x4000000;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean performClick()
    {
        ListenerInfo listenerinfo = mListenerInfo;
        boolean flag;
        if(listenerinfo != null && listenerinfo.mOnClickListener != null)
        {
            playSoundEffect(0);
            listenerinfo.mOnClickListener.onClick(this);
            flag = true;
        } else
        {
            flag = false;
        }
        sendAccessibilityEvent(1);
        notifyEnterOrExitForAutoFillIfNeeded(true);
        return flag;
    }

    void performCollectViewAttributes(AttachInfo attachinfo, int i)
    {
        if((i & 0xc) == 0)
        {
            if((mViewFlags & 0x4000000) == 0x4000000)
                attachinfo.mKeepScreenOn = true;
            attachinfo.mSystemUiVisibility = attachinfo.mSystemUiVisibility | mSystemUiVisibility;
            ListenerInfo listenerinfo = mListenerInfo;
            if(listenerinfo != null && ListenerInfo._2D_get6(listenerinfo) != null)
                attachinfo.mHasSystemUiListeners = true;
        }
    }

    public boolean performContextClick()
    {
        sendAccessibilityEvent(0x800000);
        boolean flag = false;
        ListenerInfo listenerinfo = mListenerInfo;
        boolean flag1 = flag;
        if(listenerinfo != null)
        {
            flag1 = flag;
            if(listenerinfo.mOnContextClickListener != null)
                flag1 = listenerinfo.mOnContextClickListener.onContextClick(this);
        }
        if(flag1)
            performHapticFeedback(6);
        return flag1;
    }

    public boolean performContextClick(float f, float f1)
    {
        return performContextClick();
    }

    public boolean performHapticFeedback(int i)
    {
        return performHapticFeedback(i, 0);
    }

    public boolean performHapticFeedback(int i, int j)
    {
        boolean flag = false;
        if(mAttachInfo == null)
            return false;
        if((j & 4) != 0 && mHapticEnabledExplicitly ^ true)
            return false;
        if((j & 1) == 0 && isHapticFeedbackEnabled() ^ true)
            return false;
        AttachInfo.Callbacks callbacks = mAttachInfo.mRootCallbacks;
        if((j & 2) != 0)
            flag = true;
        return callbacks.performHapticFeedback(i, flag);
    }

    public boolean performLongClick()
    {
        return performLongClickInternal(mLongClickX, mLongClickY);
    }

    public boolean performLongClick(float f, float f1)
    {
        mLongClickX = f;
        mLongClickY = f1;
        boolean flag = performLongClick();
        mLongClickX = (0.0F / 0.0F);
        mLongClickY = (0.0F / 0.0F);
        return flag;
    }

    public void playSoundEffect(int i)
    {
        while(mAttachInfo == null || mAttachInfo.mRootCallbacks == null || isSoundEffectsEnabled() ^ true) 
            return;
        mAttachInfo.mRootCallbacks.playSoundEffect(i);
    }

    final boolean pointInView(float f, float f1)
    {
        return pointInView(f, f1, 0.0F);
    }

    public boolean pointInView(float f, float f1, float f2)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(f >= -f2)
        {
            flag1 = flag;
            if(f1 >= -f2)
            {
                flag1 = flag;
                if(f < (float)(mRight - mLeft) + f2)
                {
                    flag1 = flag;
                    if(f1 < (float)(mBottom - mTop) + f2)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public boolean post(Runnable runnable)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
        {
            return attachinfo.mHandler.post(runnable);
        } else
        {
            getRunQueue().post(runnable);
            return true;
        }
    }

    public boolean postDelayed(Runnable runnable, long l)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
        {
            return attachinfo.mHandler.postDelayed(runnable, l);
        } else
        {
            getRunQueue().postDelayed(runnable, l);
            return true;
        }
    }

    public void postInvalidate()
    {
        postInvalidateDelayed(0L);
    }

    public void postInvalidate(int i, int j, int k, int l)
    {
        postInvalidateDelayed(0L, i, j, k, l);
    }

    public void postInvalidateDelayed(long l)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
            attachinfo.mViewRootImpl.dispatchInvalidateDelayed(this, l);
    }

    public void postInvalidateDelayed(long l, int i, int j, int k, int i1)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
        {
            AttachInfo.InvalidateInfo invalidateinfo = AttachInfo.InvalidateInfo.obtain();
            invalidateinfo.target = this;
            invalidateinfo.left = i;
            invalidateinfo.top = j;
            invalidateinfo.right = k;
            invalidateinfo.bottom = i1;
            attachinfo.mViewRootImpl.dispatchInvalidateRectDelayed(invalidateinfo, l);
        }
    }

    public void postInvalidateOnAnimation()
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
            attachinfo.mViewRootImpl.dispatchInvalidateOnAnimation(this);
    }

    public void postInvalidateOnAnimation(int i, int j, int k, int l)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
        {
            AttachInfo.InvalidateInfo invalidateinfo = AttachInfo.InvalidateInfo.obtain();
            invalidateinfo.target = this;
            invalidateinfo.left = i;
            invalidateinfo.top = j;
            invalidateinfo.right = k;
            invalidateinfo.bottom = l;
            attachinfo.mViewRootImpl.dispatchInvalidateRectOnAnimation(invalidateinfo);
        }
    }

    public void postOnAnimation(Runnable runnable)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
            attachinfo.mViewRootImpl.mChoreographer.postCallback(1, runnable, null);
        else
            getRunQueue().post(runnable);
    }

    public void postOnAnimationDelayed(Runnable runnable, long l)
    {
        AttachInfo attachinfo = mAttachInfo;
        if(attachinfo != null)
            attachinfo.mViewRootImpl.mChoreographer.postCallbackDelayed(1, runnable, null, l);
        else
            getRunQueue().postDelayed(runnable, l);
    }

    protected void recomputePadding()
    {
        internalSetPadding(mUserPaddingLeft, mPaddingTop, mUserPaddingRight, mUserPaddingBottom);
    }

    public void refreshDrawableState()
    {
        mPrivateFlags = mPrivateFlags | 0x400;
        drawableStateChanged();
        ViewParent viewparent = mParent;
        if(viewparent != null)
            viewparent.childDrawableStateChanged(this);
    }

    public void releasePointerCapture()
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl != null)
            viewrootimpl.requestPointerCapture(false);
    }

    public boolean removeCallbacks(Runnable runnable)
    {
        if(runnable != null)
        {
            AttachInfo attachinfo = mAttachInfo;
            if(attachinfo != null)
            {
                attachinfo.mHandler.removeCallbacks(runnable);
                attachinfo.mViewRootImpl.mChoreographer.removeCallbacks(1, runnable, null);
            }
            getRunQueue().removeCallbacks(runnable);
        }
        return true;
    }

    public void removeFrameMetricsListener(Window.OnFrameMetricsAvailableListener onframemetricsavailablelistener)
    {
        ThreadedRenderer threadedrenderer = getThreadedRenderer();
        onframemetricsavailablelistener = findFrameMetricsObserver(onframemetricsavailablelistener);
        if(onframemetricsavailablelistener == null)
            throw new IllegalArgumentException("attempt to remove OnFrameMetricsAvailableListener that was never added");
        if(mFrameMetricsObservers != null)
        {
            mFrameMetricsObservers.remove(onframemetricsavailablelistener);
            if(threadedrenderer != null)
                threadedrenderer.removeFrameMetricsObserver(onframemetricsavailablelistener);
        }
    }

    public void removeOnAttachStateChangeListener(OnAttachStateChangeListener onattachstatechangelistener)
    {
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo == null || ListenerInfo._2D_get0(listenerinfo) == null)
        {
            return;
        } else
        {
            ListenerInfo._2D_get0(listenerinfo).remove(onattachstatechangelistener);
            return;
        }
    }

    public void removeOnLayoutChangeListener(OnLayoutChangeListener onlayoutchangelistener)
    {
        ListenerInfo listenerinfo = mListenerInfo;
        if(listenerinfo == null || ListenerInfo._2D_get5(listenerinfo) == null)
        {
            return;
        } else
        {
            ListenerInfo._2D_get5(listenerinfo).remove(onlayoutchangelistener);
            return;
        }
    }

    public boolean requestAccessibilityFocus()
    {
        AccessibilityManager accessibilitymanager = AccessibilityManager.getInstance(mContext);
        if(!accessibilitymanager.isEnabled() || accessibilitymanager.isTouchExplorationEnabled() ^ true)
            return false;
        if((mViewFlags & 0xc) != 0)
            return false;
        if((mPrivateFlags2 & 0x4000000) == 0)
        {
            mPrivateFlags2 = mPrivateFlags2 | 0x4000000;
            ViewRootImpl viewrootimpl = getViewRootImpl();
            if(viewrootimpl != null)
                viewrootimpl.setAccessibilityFocus(this, null);
            invalidate();
            sendAccessibilityEvent(32768);
            return true;
        } else
        {
            return false;
        }
    }

    public void requestApplyInsets()
    {
        requestFitSystemWindows();
    }

    public void requestFitSystemWindows()
    {
        if(mParent != null)
            mParent.requestFitSystemWindows();
    }

    public final boolean requestFocus()
    {
        return requestFocus(130);
    }

    public final boolean requestFocus(int i)
    {
        return requestFocus(i, null);
    }

    public boolean requestFocus(int i, Rect rect)
    {
        return requestFocusNoSearch(i, rect);
    }

    public final boolean requestFocusFromTouch()
    {
        if(isInTouchMode())
        {
            ViewRootImpl viewrootimpl = getViewRootImpl();
            if(viewrootimpl != null)
                viewrootimpl.ensureTouchMode(false);
        }
        return requestFocus(130);
    }

    public void requestKeyboardShortcuts(List list, int i)
    {
    }

    public void requestLayout()
    {
        if(mMeasureCache != null)
            mMeasureCache.clear();
        if(mAttachInfo != null && mAttachInfo.mViewRequestingLayout == null)
        {
            ViewRootImpl viewrootimpl = getViewRootImpl();
            if(viewrootimpl != null && viewrootimpl.isInLayout() && !viewrootimpl.requestLayoutDuringLayout(this))
                return;
            mAttachInfo.mViewRequestingLayout = this;
        }
        mPrivateFlags = mPrivateFlags | 0x1000;
        mPrivateFlags = mPrivateFlags | 0x80000000;
        if(mParent != null && mParent.isLayoutRequested() ^ true)
            mParent.requestLayout();
        if(mAttachInfo != null && mAttachInfo.mViewRequestingLayout == this)
            mAttachInfo.mViewRequestingLayout = null;
    }

    public void requestPointerCapture()
    {
        ViewRootImpl viewrootimpl = getViewRootImpl();
        if(viewrootimpl != null)
            viewrootimpl.requestPointerCapture(true);
    }

    public boolean requestRectangleOnScreen(Rect rect)
    {
        return requestRectangleOnScreen(rect, false);
    }

    public boolean requestRectangleOnScreen(Rect rect, boolean flag)
    {
        if(mParent == null)
            return false;
        View view = this;
        RectF rectf;
        ViewParent viewparent;
        boolean flag1;
        if(mAttachInfo != null)
            rectf = mAttachInfo.mTmpTransformRect;
        else
            rectf = new RectF();
        rectf.set(rect);
        viewparent = mParent;
        flag1 = false;
        do
        {
label0:
            {
                boolean flag2 = flag1;
                if(viewparent != null)
                {
                    rect.set((int)rectf.left, (int)rectf.top, (int)rectf.right, (int)rectf.bottom);
                    flag1 |= viewparent.requestChildRectangleOnScreen(view, rect, flag);
                    if(viewparent instanceof View)
                        break label0;
                    flag2 = flag1;
                }
                return flag2;
            }
            rectf.offset(view.mLeft - view.getScrollX(), view.mTop - view.getScrollY());
            view = (View)viewparent;
            viewparent = view.getParent();
        } while(true);
    }

    public final void requestUnbufferedDispatch(MotionEvent motionevent)
    {
        for(int i = motionevent.getAction(); mAttachInfo == null || i != 0 && i != 2 || motionevent.isTouchEvent() ^ true;)
            return;

        mAttachInfo.mUnbufferedDispatchRequested = true;
    }

    public void resetPaddingToInitialValues()
    {
        if(isRtlCompatibilityMode())
        {
            mPaddingLeft = mUserPaddingLeftInitial;
            mPaddingRight = mUserPaddingRightInitial;
            return;
        }
        if(isLayoutRtl())
        {
            int i;
            if(mUserPaddingEnd >= 0)
                i = mUserPaddingEnd;
            else
                i = mUserPaddingLeftInitial;
            mPaddingLeft = i;
            if(mUserPaddingStart >= 0)
                i = mUserPaddingStart;
            else
                i = mUserPaddingRightInitial;
            mPaddingRight = i;
        } else
        {
            int j;
            if(mUserPaddingStart >= 0)
                j = mUserPaddingStart;
            else
                j = mUserPaddingLeftInitial;
            mPaddingLeft = j;
            if(mUserPaddingEnd >= 0)
                j = mUserPaddingEnd;
            else
                j = mUserPaddingRightInitial;
            mPaddingRight = j;
        }
    }

    protected void resetResolvedDrawables()
    {
        resetResolvedDrawablesInternal();
    }

    void resetResolvedDrawablesInternal()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xbfffffff;
    }

    public void resetResolvedLayoutDirection()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xffffffcf;
    }

    public void resetResolvedPadding()
    {
        resetResolvedPaddingInternal();
    }

    void resetResolvedPaddingInternal()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xdfffffff;
    }

    public void resetResolvedTextAlignment()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xfff0ffff;
        mPrivateFlags2 = mPrivateFlags2 | 0x20000;
    }

    public void resetResolvedTextDirection()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xffffe1ff;
        mPrivateFlags2 = mPrivateFlags2 | 0x400;
    }

    public void resetRtlProperties()
    {
        resetResolvedLayoutDirection();
        resetResolvedTextDirection();
        resetResolvedTextAlignment();
        resetResolvedPadding();
        resetResolvedDrawables();
    }

    void resetSubtreeAccessibilityStateChanged()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xf7ffffff;
    }

    protected void resolveDrawables()
    {
        if(!isLayoutDirectionResolved() && getRawLayoutDirection() == 2)
            return;
        int i;
        if(isLayoutDirectionResolved())
            i = getLayoutDirection();
        else
            i = getRawLayoutDirection();
        if(mBackground != null)
            mBackground.setLayoutDirection(i);
        if(mForegroundInfo != null && ForegroundInfo._2D_get1(mForegroundInfo) != null)
            ForegroundInfo._2D_get1(mForegroundInfo).setLayoutDirection(i);
        if(mDefaultFocusHighlight != null)
            mDefaultFocusHighlight.setLayoutDirection(i);
        mPrivateFlags2 = mPrivateFlags2 | 0x40000000;
        onResolveDrawables(i);
    }

    public boolean resolveLayoutDirection()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xffffffcf;
        if(!hasRtlSupport()) goto _L2; else goto _L1
_L1:
        (mPrivateFlags2 & 0xc) >> 2;
        JVM INSTR tableswitch 1 3: default 52
    //                   1 156
    //                   2 65
    //                   3 170;
           goto _L2 _L3 _L4 _L5
_L2:
        mPrivateFlags2 = mPrivateFlags2 | 0x20;
        return true;
_L4:
        if(!canResolveLayoutDirection())
            return false;
        if(!mParent.isLayoutDirectionResolved())
            return false;
        try
        {
            if(mParent.getLayoutDirection() == 1)
                mPrivateFlags2 = mPrivateFlags2 | 0x10;
        }
        catch(AbstractMethodError abstractmethoderror)
        {
            Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        mPrivateFlags2 = mPrivateFlags2 | 0x10;
        continue; /* Loop/switch isn't completed */
_L5:
        if(1 == TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()))
            mPrivateFlags2 = mPrivateFlags2 | 0x10;
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void resolveLayoutParams()
    {
        if(mLayoutParams != null)
            mLayoutParams.resolveLayoutDirection(getLayoutDirection());
    }

    public void resolvePadding()
    {
label0:
        {
label1:
            {
                int j;
                {
                    int i = getLayoutDirection();
                    if(isRtlCompatibilityMode())
                        break label1;
                    if(mBackground != null && (!mLeftPaddingDefined || mRightPaddingDefined ^ true))
                    {
                        Rect rect = (Rect)sThreadLocal.get();
                        Rect rect1 = rect;
                        if(rect == null)
                        {
                            rect1 = new Rect();
                            sThreadLocal.set(rect1);
                        }
                        mBackground.getPadding(rect1);
                        if(!mLeftPaddingDefined)
                            mUserPaddingLeftInitial = rect1.left;
                        if(!mRightPaddingDefined)
                            mUserPaddingRightInitial = rect1.right;
                    }
                    switch(i)
                    {
                    default:
                        if(mUserPaddingStart != 0x80000000)
                            mUserPaddingLeft = mUserPaddingStart;
                        else
                            mUserPaddingLeft = mUserPaddingLeftInitial;
                        if(mUserPaddingEnd != 0x80000000)
                            mUserPaddingRight = mUserPaddingEnd;
                        else
                            mUserPaddingRight = mUserPaddingRightInitial;
                        break;

                    case 1: // '\001'
                        break label0;
                    }
                }
                if(mUserPaddingBottom >= 0)
                    j = mUserPaddingBottom;
                else
                    j = mPaddingBottom;
                mUserPaddingBottom = j;
            }
            internalSetPadding(mUserPaddingLeft, mPaddingTop, mUserPaddingRight, mUserPaddingBottom);
            onRtlPropertiesChanged(i);
            mPrivateFlags2 = mPrivateFlags2 | 0x20000000;
            return;
        }
        if(mUserPaddingStart != 0x80000000)
            mUserPaddingRight = mUserPaddingStart;
        else
            mUserPaddingRight = mUserPaddingRightInitial;
        if(mUserPaddingEnd != 0x80000000)
            mUserPaddingLeft = mUserPaddingEnd;
        else
            mUserPaddingLeft = mUserPaddingLeftInitial;
        break MISSING_BLOCK_LABEL_160;
    }

    public boolean resolveRtlPropertiesIfNeeded()
    {
        if(!needRtlPropertiesResolution())
            return false;
        if(!isLayoutDirectionResolved())
        {
            resolveLayoutDirection();
            resolveLayoutParams();
        }
        if(!isTextDirectionResolved())
            resolveTextDirection();
        if(!isTextAlignmentResolved())
            resolveTextAlignment();
        if(!areDrawablesResolved())
            resolveDrawables();
        if(!isPaddingResolved())
            resolvePadding();
        onRtlPropertiesChanged(getLayoutDirection());
        return true;
    }

    public boolean resolveTextAlignment()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xfff0ffff;
        if(!hasRtlSupport()) goto _L2; else goto _L1
_L1:
        int i = getRawTextAlignment();
        i;
        JVM INSTR tableswitch 0 6: default 68
    //                   0 94
    //                   1 318
    //                   2 318
    //                   3 318
    //                   4 318
    //                   5 318
    //                   6 318;
           goto _L3 _L4 _L5 _L5 _L5 _L5 _L5 _L5
_L3:
        mPrivateFlags2 = mPrivateFlags2 | 0x20000;
_L7:
        mPrivateFlags2 = mPrivateFlags2 | 0x10000;
        return true;
_L4:
label0:
        {
            if(!canResolveTextAlignment())
            {
                mPrivateFlags2 = mPrivateFlags2 | 0x20000;
                return false;
            }
            try
            {
                if(mParent.isTextAlignmentResolved())
                    break label0;
                mPrivateFlags2 = mPrivateFlags2 | 0x20000;
            }
            catch(AbstractMethodError abstractmethoderror)
            {
                Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
                mPrivateFlags2 = mPrivateFlags2 | 0x30000;
                return true;
            }
            return false;
        }
        try
        {
            i = mParent.getTextAlignment();
        }
        catch(AbstractMethodError abstractmethoderror1)
        {
            Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror1);
            i = 1;
        }
        switch(i)
        {
        default:
            mPrivateFlags2 = mPrivateFlags2 | 0x20000;
            break;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            mPrivateFlags2 = mPrivateFlags2 | i << 17;
            break;
        }
        if(false)
            ;
        continue; /* Loop/switch isn't completed */
_L5:
        mPrivateFlags2 = mPrivateFlags2 | i << 17;
        continue; /* Loop/switch isn't completed */
_L2:
        mPrivateFlags2 = mPrivateFlags2 | 0x20000;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public boolean resolveTextDirection()
    {
        mPrivateFlags2 = mPrivateFlags2 & 0xffffe1ff;
        if(!hasRtlSupport()) goto _L2; else goto _L1
_L1:
        int i = getRawTextDirection();
        i;
        JVM INSTR tableswitch 0 7: default 72
    //                   0 98
    //                   1 326
    //                   2 326
    //                   3 326
    //                   4 326
    //                   5 326
    //                   6 326
    //                   7 326;
           goto _L3 _L4 _L5 _L5 _L5 _L5 _L5 _L5 _L5
_L3:
        mPrivateFlags2 = mPrivateFlags2 | 0x400;
_L7:
        mPrivateFlags2 = mPrivateFlags2 | 0x200;
        return true;
_L4:
label0:
        {
            if(!canResolveTextDirection())
            {
                mPrivateFlags2 = mPrivateFlags2 | 0x400;
                return false;
            }
            try
            {
                if(mParent.isTextDirectionResolved())
                    break label0;
                mPrivateFlags2 = mPrivateFlags2 | 0x400;
            }
            catch(AbstractMethodError abstractmethoderror)
            {
                Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
                mPrivateFlags2 = mPrivateFlags2 | 0x600;
                return true;
            }
            return false;
        }
        try
        {
            i = mParent.getTextDirection();
        }
        catch(AbstractMethodError abstractmethoderror1)
        {
            Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror1);
            i = 3;
        }
        switch(i)
        {
        default:
            mPrivateFlags2 = mPrivateFlags2 | 0x400;
            break;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
            mPrivateFlags2 = mPrivateFlags2 | i << 10;
            break;
        }
        if(false)
            ;
        continue; /* Loop/switch isn't completed */
_L5:
        mPrivateFlags2 = mPrivateFlags2 | i << 10;
        continue; /* Loop/switch isn't completed */
_L2:
        mPrivateFlags2 = mPrivateFlags2 | 0x400;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public boolean restoreDefaultFocus()
    {
        return requestFocus(130);
    }

    public boolean restoreFocusInCluster(int i)
    {
        if(restoreDefaultFocus())
            return true;
        else
            return requestFocus(i);
    }

    public boolean restoreFocusNotInCluster()
    {
        return requestFocus(130);
    }

    public void restoreHierarchyState(SparseArray sparsearray)
    {
        dispatchRestoreInstanceState(sparsearray);
    }

    boolean rootViewRequestFocus()
    {
        View view = getRootView();
        boolean flag;
        if(view != null)
            flag = view.requestFocus();
        else
            flag = false;
        return flag;
    }

    public void saveHierarchyState(SparseArray sparsearray)
    {
        dispatchSaveInstanceState(sparsearray);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        if(verifyDrawable(drawable) && runnable != null)
        {
            l -= SystemClock.uptimeMillis();
            if(mAttachInfo != null)
                mAttachInfo.mViewRootImpl.mChoreographer.postCallbackDelayed(1, runnable, drawable, Choreographer.subtractFrameDelay(l));
            else
                getRunQueue().postDelayed(runnable, l);
        }
    }

    public void scrollBy(int i, int j)
    {
        scrollTo(mScrollX + i, mScrollY + j);
    }

    public void scrollTo(int i, int j)
    {
        if(mScrollX != i || mScrollY != j)
        {
            int k = mScrollX;
            int l = mScrollY;
            mScrollX = i;
            mScrollY = j;
            invalidateParentCaches();
            onScrollChanged(mScrollX, mScrollY, k, l);
            if(!awakenScrollBars())
                postInvalidateOnAnimation();
        }
    }

    public void sendAccessibilityEvent(int i)
    {
        if(mAccessibilityDelegate != null)
            mAccessibilityDelegate.sendAccessibilityEvent(this, i);
        else
            sendAccessibilityEventInternal(i);
    }

    public void sendAccessibilityEventInternal(int i)
    {
        if(AccessibilityManager.getInstance(mContext).isEnabled())
            sendAccessibilityEventUnchecked(AccessibilityEvent.obtain(i));
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityevent)
    {
        if(mAccessibilityDelegate != null)
            mAccessibilityDelegate.sendAccessibilityEventUnchecked(this, accessibilityevent);
        else
            sendAccessibilityEventUncheckedInternal(accessibilityevent);
    }

    public void sendAccessibilityEventUncheckedInternal(AccessibilityEvent accessibilityevent)
    {
        if(!isShown())
            return;
        onInitializeAccessibilityEvent(accessibilityevent);
        if((accessibilityevent.getEventType() & 0x2a1bf) != 0)
            dispatchPopulateAccessibilityEvent(accessibilityevent);
        if(getParent() != null)
            getParent().requestSendAccessibilityEvent(this, accessibilityevent);
    }

    public void setAccessibilityDelegate(AccessibilityDelegate accessibilitydelegate)
    {
        mAccessibilityDelegate = accessibilitydelegate;
    }

    public void setAccessibilityLiveRegion(int i)
    {
        if(i != getAccessibilityLiveRegion())
        {
            mPrivateFlags2 = mPrivateFlags2 & 0xfe7fffff;
            mPrivateFlags2 = mPrivateFlags2 | i << 23 & 0x1800000;
            notifyViewAccessibilityStateChangedIfNeeded(0);
        }
    }

    public void setAccessibilitySelection(int i, int j)
    {
        if(i == j && j == mAccessibilityCursorPosition)
            return;
        if(i >= 0 && i == j && j <= getIterableTextForAccessibility().length())
            mAccessibilityCursorPosition = i;
        else
            mAccessibilityCursorPosition = -1;
        sendAccessibilityEvent(8192);
    }

    public void setAccessibilityTraversalAfter(int i)
    {
        if(mAccessibilityTraversalAfterId == i)
        {
            return;
        } else
        {
            mAccessibilityTraversalAfterId = i;
            notifyViewAccessibilityStateChangedIfNeeded(0);
            return;
        }
    }

    public void setAccessibilityTraversalBefore(int i)
    {
        if(mAccessibilityTraversalBeforeId == i)
        {
            return;
        } else
        {
            mAccessibilityTraversalBeforeId = i;
            notifyViewAccessibilityStateChangedIfNeeded(0);
            return;
        }
    }

    public void setActivated(boolean flag)
    {
        int i = 0x40000000;
        boolean flag1;
        if((mPrivateFlags & 0x40000000) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 != flag)
        {
            int j = mPrivateFlags;
            if(!flag)
                i = 0;
            mPrivateFlags = i | j & 0xbfffffff;
            invalidate(true);
            refreshDrawableState();
            dispatchSetActivated(flag);
        }
    }

    public void setAlpha(float f)
    {
        ensureTransformationInfo();
        if(mTransformationInfo.mAlpha != f)
        {
            boolean flag;
            boolean flag1;
            if(f == 0.0F)
                flag = true;
            else
                flag = false;
            if(mTransformationInfo.mAlpha == 0.0F)
                flag1 = true;
            else
                flag1 = false;
            if(flag ^ flag1)
                notifySubtreeAccessibilityStateChangedIfNeeded();
            mTransformationInfo.mAlpha = f;
            if(onSetAlpha((int)(255F * f)))
            {
                mPrivateFlags = mPrivateFlags | 0x40000;
                invalidateParentCaches();
                invalidate(true);
            } else
            {
                mPrivateFlags = mPrivateFlags & 0xfffbffff;
                invalidateViewProperty(true, false);
                mRenderNode.setAlpha(getFinalAlpha());
            }
        }
    }

    boolean setAlphaNoInvalidation(float f)
    {
        ensureTransformationInfo();
        if(mTransformationInfo.mAlpha != f)
        {
            mTransformationInfo.mAlpha = f;
            if(onSetAlpha((int)(255F * f)))
            {
                mPrivateFlags = mPrivateFlags | 0x40000;
                return true;
            }
            mPrivateFlags = mPrivateFlags & 0xfffbffff;
            mRenderNode.setAlpha(getFinalAlpha());
        }
        return false;
    }

    public void setAnimation(Animation animation)
    {
        mCurrentAnimation = animation;
        if(animation != null)
        {
            if(mAttachInfo != null && mAttachInfo.mDisplayState == 1 && animation.getStartTime() == -1L)
                animation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
            animation.reset();
        }
    }

    public void setAnimationMatrix(Matrix matrix)
    {
        invalidateViewProperty(true, false);
        mRenderNode.setAnimationMatrix(matrix);
        invalidateViewProperty(false, true);
        invalidateParentIfNeededAndWasQuickRejected();
    }

    public void setAssistBlocked(boolean flag)
    {
        if(flag)
            mPrivateFlags3 = mPrivateFlags3 | 0x4000;
        else
            mPrivateFlags3 = mPrivateFlags3 & 0xffffbfff;
    }

    public transient void setAutofillHints(String as[])
    {
        if(as == null || as.length == 0)
            mAutofillHints = null;
        else
            mAutofillHints = as;
    }

    public void setAutofilled(boolean flag)
    {
        boolean flag1;
        if(flag != isAutofilled())
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            if(flag)
                mPrivateFlags3 = mPrivateFlags3 | 0x10000;
            else
                mPrivateFlags3 = mPrivateFlags3 & 0xfffeffff;
            invalidate();
        }
    }

    public void setBackground(Drawable drawable)
    {
        setBackgroundDrawable(drawable);
    }

    void setBackgroundBounds()
    {
        if(mBackgroundSizeChanged && mBackground != null)
        {
            mBackground.setBounds(0, 0, mRight - mLeft, mBottom - mTop);
            mBackgroundSizeChanged = false;
            rebuildOutline();
        }
    }

    public void setBackgroundColor(int i)
    {
        if(mBackground instanceof ColorDrawable)
        {
            ((ColorDrawable)mBackground.mutate()).setColor(i);
            computeOpaqueFlags();
            mBackgroundResource = 0;
        } else
        {
            setBackground(new ColorDrawable(i));
        }
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        boolean flag;
        Rect rect1;
        computeOpaqueFlags();
        if(drawable == mBackground)
            return;
        flag = false;
        mBackgroundResource = 0;
        if(mBackground != null)
        {
            if(isAttachedToWindow())
                mBackground.setVisible(false, false);
            mBackground.setCallback(null);
            unscheduleDrawable(mBackground);
        }
        if(drawable == null)
            break MISSING_BLOCK_LABEL_401;
        Rect rect = (Rect)sThreadLocal.get();
        rect1 = rect;
        if(rect == null)
        {
            rect1 = new Rect();
            sThreadLocal.set(rect1);
        }
        resetResolvedDrawablesInternal();
        drawable.setLayoutDirection(getLayoutDirection());
        if(!drawable.getPadding(rect1)) goto _L2; else goto _L1
_L1:
        resetResolvedPaddingInternal();
        drawable.getLayoutDirection();
        JVM INSTR tableswitch 1 1: default 148
    //                   1 333;
           goto _L3 _L4
_L3:
        mUserPaddingLeftInitial = rect1.left;
        mUserPaddingRightInitial = rect1.right;
        internalSetPadding(rect1.left, rect1.top, rect1.right, rect1.bottom);
_L5:
        mLeftPaddingDefined = false;
        mRightPaddingDefined = false;
_L2:
        if(mBackground == null || mBackground.getMinimumHeight() != drawable.getMinimumHeight() || mBackground.getMinimumWidth() != drawable.getMinimumWidth())
            flag = true;
        mBackground = drawable;
        if(drawable.isStateful())
            drawable.setState(getDrawableState());
        if(isAttachedToWindow())
        {
            boolean flag1;
            if(getWindowVisibility() == 0)
                flag1 = isShown();
            else
                flag1 = false;
            drawable.setVisible(flag1, false);
        }
        applyBackgroundTint();
        drawable.setCallback(this);
        if((mPrivateFlags & 0x80) != 0)
        {
            mPrivateFlags = mPrivateFlags & 0xffffff7f;
            flag = true;
        }
_L6:
        computeOpaqueFlags();
        if(flag)
            requestLayout();
        mBackgroundSizeChanged = true;
        invalidate(true);
        invalidateOutline();
        return;
_L4:
        mUserPaddingLeftInitial = rect1.right;
        mUserPaddingRightInitial = rect1.left;
        internalSetPadding(rect1.right, rect1.top, rect1.left, rect1.bottom);
          goto _L5
        mBackground = null;
        if((mViewFlags & 0x80) != 0 && mDefaultFocusHighlight == null && (mForegroundInfo == null || ForegroundInfo._2D_get1(mForegroundInfo) == null))
            mPrivateFlags = mPrivateFlags | 0x80;
        flag = true;
          goto _L6
    }

    public void setBackgroundResource(int i)
    {
        if(i != 0 && i == mBackgroundResource)
            return;
        Drawable drawable = null;
        if(i != 0)
            drawable = mContext.getDrawable(i);
        setBackground(drawable);
        mBackgroundResource = i;
    }

    public void setBackgroundTintList(ColorStateList colorstatelist)
    {
        if(mBackgroundTint == null)
            mBackgroundTint = new TintInfo();
        mBackgroundTint.mTintList = colorstatelist;
        mBackgroundTint.mHasTintList = true;
        applyBackgroundTint();
    }

    public void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mBackgroundTint == null)
            mBackgroundTint = new TintInfo();
        mBackgroundTint.mTintMode = mode;
        mBackgroundTint.mHasTintMode = true;
        applyBackgroundTint();
    }

    public final void setBottom(int i)
    {
        if(i != mBottom)
        {
            boolean flag = hasIdentityMatrix();
            if(flag)
            {
                if(mAttachInfo != null)
                {
                    int j;
                    int k;
                    int l;
                    if(i < mBottom)
                        j = mBottom;
                    else
                        j = i;
                    invalidate(0, 0, mRight - mLeft, j - mTop);
                }
            } else
            {
                invalidate(true);
            }
            j = mRight - mLeft;
            k = mBottom;
            l = mTop;
            mBottom = i;
            mRenderNode.setBottom(mBottom);
            sizeChange(j, mBottom - mTop, j, k - l);
            if(!flag)
            {
                mPrivateFlags = mPrivateFlags | 0x20;
                invalidate(true);
            }
            mBackgroundSizeChanged = true;
            mDefaultFocusHighlightSizeChanged = true;
            if(mForegroundInfo != null)
                ForegroundInfo._2D_set0(mForegroundInfo, true);
            invalidateParentIfNeeded();
            if((mPrivateFlags2 & 0x10000000) == 0x10000000)
                invalidateParentIfNeeded();
        }
    }

    public void setCameraDistance(float f)
    {
        float f1 = mResources.getDisplayMetrics().densityDpi;
        invalidateViewProperty(true, false);
        mRenderNode.setCameraDistance(-Math.abs(f) / f1);
        invalidateViewProperty(false, false);
        invalidateParentIfNeededAndWasQuickRejected();
    }

    public void setClickable(boolean flag)
    {
        char c;
        if(flag)
            c = '\u4000';
        else
            c = '\0';
        setFlags(c, 16384);
    }

    public void setClipBounds(Rect rect)
    {
        if(rect == mClipBounds || rect != null && rect.equals(mClipBounds))
            return;
        if(rect != null)
        {
            if(mClipBounds == null)
                mClipBounds = new Rect(rect);
            else
                mClipBounds.set(rect);
        } else
        {
            mClipBounds = null;
        }
        mRenderNode.setClipBounds(mClipBounds);
        invalidateViewProperty(false, false);
    }

    public void setClipToOutline(boolean flag)
    {
        damageInParent();
        if(getClipToOutline() != flag)
            mRenderNode.setClipToOutline(flag);
    }

    public void setContentDescription(CharSequence charsequence)
    {
        if(mContentDescription == null)
        {
            if(charsequence == null)
                return;
        } else
        if(mContentDescription.equals(charsequence))
            return;
        mContentDescription = charsequence;
        boolean flag;
        if(charsequence != null && charsequence.length() > 0)
            flag = true;
        else
            flag = false;
        if(flag && getImportantForAccessibility() == 0)
        {
            setImportantForAccessibility(1);
            notifySubtreeAccessibilityStateChangedIfNeeded();
        } else
        {
            notifyViewAccessibilityStateChangedIfNeeded(4);
        }
    }

    public void setContextClickable(boolean flag)
    {
        int i;
        if(flag)
            i = 0x800000;
        else
            i = 0;
        setFlags(i, 0x800000);
    }

    public void setDefaultFocusHighlightEnabled(boolean flag)
    {
        mDefaultFocusHighlightEnabled = flag;
    }

    public void setDisabledSystemUiVisibility(int i)
    {
        if(mAttachInfo != null && mAttachInfo.mDisabledSystemUiVisibility != i)
        {
            mAttachInfo.mDisabledSystemUiVisibility = i;
            if(mParent != null)
                mParent.recomputeViewAttributes(this);
        }
    }

    void setDisplayListProperties(RenderNode rendernode)
    {
        boolean flag = false;
        if(rendernode == null) goto _L2; else goto _L1
_L1:
        float f2;
        rendernode.setHasOverlappingRendering(getHasOverlappingRendering());
        if(mParent instanceof ViewGroup)
            flag = ((ViewGroup)mParent).getClipChildren();
        rendernode.setClipToBounds(flag);
        float f = 1.0F;
        f2 = f;
        if(mParent instanceof ViewGroup)
        {
            f2 = f;
            if((((ViewGroup)mParent).mGroupFlags & 0x800) != 0)
            {
                ViewGroup viewgroup = (ViewGroup)mParent;
                Transformation transformation = viewgroup.getChildTransformation();
                f2 = f;
                if(viewgroup.getChildStaticTransformation(this, transformation))
                {
                    int i = transformation.getTransformationType();
                    f2 = f;
                    if(i != 0)
                    {
                        if((i & 1) != 0)
                            f = transformation.getAlpha();
                        f2 = f;
                        if((i & 2) != 0)
                        {
                            rendernode.setStaticMatrix(transformation.getMatrix());
                            f2 = f;
                        }
                    }
                }
            }
        }
        if(mTransformationInfo == null) goto _L4; else goto _L3
_L3:
        f2 *= getFinalAlpha();
        float f1 = f2;
        if(f2 < 1.0F)
        {
            f1 = f2;
            if(onSetAlpha((int)(255F * f2)))
                f1 = 1.0F;
        }
        rendernode.setAlpha(f1);
_L2:
        return;
_L4:
        if(f2 < 1.0F)
            rendernode.setAlpha(f2);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setDrawingCacheBackgroundColor(int i)
    {
        if(i != mDrawingCacheBackgroundColor)
        {
            mDrawingCacheBackgroundColor = i;
            mPrivateFlags = mPrivateFlags & 0xffff7fff;
        }
    }

    public void setDrawingCacheEnabled(boolean flag)
    {
        int i = 0;
        mCachingFailed = false;
        if(flag)
            i = 32768;
        setFlags(i, 32768);
    }

    public void setDrawingCacheQuality(int i)
    {
        setFlags(i, 0x180000);
    }

    public void setDuplicateParentStateEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 0x400000;
        else
            i = 0;
        setFlags(i, 0x400000);
    }

    public void setElevation(float f)
    {
        if(f != getElevation())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setElevation(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
        }
    }

    public void setEnabled(boolean flag)
    {
        if(flag == isEnabled())
            return;
        int i;
        if(flag)
            i = 0;
        else
            i = 32;
        setFlags(i, 32);
        refreshDrawableState();
        invalidate(true);
        if(!flag)
            cancelPendingInputEvents();
    }

    public void setFadingEdgeLength(int i)
    {
        initScrollCache();
        mScrollCache.fadingEdgeLength = i;
    }

    public void setFilterTouchesWhenObscured(boolean flag)
    {
        char c;
        if(flag)
            c = '\u0400';
        else
            c = '\0';
        setFlags(c, 1024);
    }

    public void setFitsSystemWindows(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        setFlags(byte0, 2);
    }

    void setFlags(int i, int j)
    {
        int k;
        int i1;
        int j1;
        boolean flag = AccessibilityManager.getInstance(mContext).isEnabled();
        boolean flag1;
        int l;
        if(flag)
            flag1 = includeForAccessibility();
        else
            flag1 = false;
        k = mViewFlags;
        mViewFlags = mViewFlags & j | i & j;
        l = mViewFlags ^ k;
        if(l == 0)
            return;
        i1 = mPrivateFlags;
        boolean flag2 = false;
        j1 = l;
        j = ((flag2) ? 1 : 0);
        if((mViewFlags & 0x10) != 0)
        {
            j1 = l;
            j = ((flag2) ? 1 : 0);
            if((l & 0x4011) != 0)
            {
                if((mViewFlags & 0x4000) != 0)
                    j = 1;
                else
                    j = 0;
                mViewFlags = mViewFlags & -2 | j;
                j = k & 1 ^ j & 1;
                j1 = l & -2 | j;
            }
        }
        if((j1 & 1) == 0 || (i1 & 0x10) == 0) goto _L2; else goto _L1
_L1:
        if((k & 1) != 1 || (i1 & 2) == 0) goto _L4; else goto _L3
_L3:
        clearFocus();
        if(mParent instanceof ViewGroup)
            ((ViewGroup)mParent).clearFocusedInCluster();
_L2:
        i &= 0xc;
        if(i == 0 && (j1 & 0xc) != 0)
        {
            mPrivateFlags = mPrivateFlags | 0x20;
            invalidate(true);
            needGlobalAttributesUpdate(true);
            if(mParent != null && mBottom > mTop && mRight > mLeft)
                mParent.focusableViewAvailable(this);
        }
        if((j1 & 8) != 0)
        {
            needGlobalAttributesUpdate(false);
            requestLayout();
            if((mViewFlags & 0xc) == 8)
            {
                if(hasFocus())
                {
                    clearFocus();
                    if(mParent instanceof ViewGroup)
                        ((ViewGroup)mParent).clearFocusedInCluster();
                }
                clearAccessibilityFocus();
                destroyDrawingCache();
                if(mParent instanceof View)
                    ((View)mParent).invalidate(true);
                mPrivateFlags = mPrivateFlags | 0x20;
            }
            if(mAttachInfo != null)
                mAttachInfo.mViewVisibilityChanged = true;
        }
        if((j1 & 4) != 0)
        {
            needGlobalAttributesUpdate(false);
            mPrivateFlags = mPrivateFlags | 0x20;
            if((mViewFlags & 0xc) == 4 && getRootView() != this)
            {
                if(hasFocus())
                {
                    clearFocus();
                    if(mParent instanceof ViewGroup)
                        ((ViewGroup)mParent).clearFocusedInCluster();
                }
                clearAccessibilityFocus();
            }
            if(mAttachInfo != null)
                mAttachInfo.mViewVisibilityChanged = true;
        }
        if((j1 & 0xc) != 0)
        {
            if(i != 0 && mAttachInfo != null)
                cleanupDraw();
            if(mParent instanceof ViewGroup)
            {
                ((ViewGroup)mParent).onChildVisibilityChanged(this, j1 & 0xc, i);
                ((View)mParent).invalidate(true);
            } else
            if(mParent != null)
                mParent.invalidateChild(this, null);
            if(mAttachInfo != null)
            {
                dispatchVisibilityChanged(this, i);
                if(mParent != null && getWindowVisibility() == 0 && (!(mParent instanceof ViewGroup) || ((ViewGroup)mParent).isShown()))
                {
                    boolean flag3;
                    ViewRootImpl viewrootimpl;
                    if(i == 0)
                        flag3 = true;
                    else
                        flag3 = false;
                    dispatchVisibilityAggregated(flag3);
                }
                notifySubtreeAccessibilityStateChangedIfNeeded();
            }
        }
        if((0x20000 & j1) != 0)
            destroyDrawingCache();
        if((0x8000 & j1) != 0)
        {
            destroyDrawingCache();
            mPrivateFlags = mPrivateFlags & 0xffff7fff;
            invalidateParentCaches();
        }
        if((0x180000 & j1) != 0)
        {
            destroyDrawingCache();
            mPrivateFlags = mPrivateFlags & 0xffff7fff;
        }
        if((j1 & 0x80) != 0)
        {
            if((mViewFlags & 0x80) != 0)
            {
                if(mBackground != null || mDefaultFocusHighlight != null || mForegroundInfo != null && ForegroundInfo._2D_get1(mForegroundInfo) != null)
                    mPrivateFlags = mPrivateFlags & 0xffffff7f;
                else
                    mPrivateFlags = mPrivateFlags | 0x80;
            } else
            {
                mPrivateFlags = mPrivateFlags & 0xffffff7f;
            }
            requestLayout();
            invalidate(true);
        }
        if((0x4000000 & j1) != 0 && mParent != null && mAttachInfo != null && mAttachInfo.mRecomputeGlobalAttributes ^ true)
            mParent.recomputeViewAttributes(this);
        if(flag)
            if((j1 & 1) != 0 || (j1 & 0xc) != 0 || (j1 & 0x4000) != 0 || (0x200000 & j1) != 0 || (0x800000 & j1) != 0)
            {
                if(flag1 != includeForAccessibility())
                    notifySubtreeAccessibilityStateChangedIfNeeded();
                else
                    notifyViewAccessibilityStateChangedIfNeeded(0);
            } else
            if((j1 & 0x20) != 0)
                notifyViewAccessibilityStateChangedIfNeeded(0);
        return;
_L4:
        if((k & 1) != 0 || (i1 & 2) != 0 || mParent == null) goto _L2; else goto _L5
_L5:
        viewrootimpl = getViewRootImpl();
          goto _L6
_L7:
        mParent.focusableViewAvailable(this);
          goto _L2
_L6:
        if(sAutoFocusableOffUIThreadWontNotifyParents && j != 0 && viewrootimpl != null && viewrootimpl.mThread != Thread.currentThread()) goto _L2; else goto _L7
    }

    public void setFocusable(int i)
    {
        if((i & 0x11) == 0)
            setFlags(0, 0x40000);
        setFlags(i, 17);
    }

    public void setFocusable(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        setFocusable(i);
    }

    public void setFocusableInTouchMode(boolean flag)
    {
        int i;
        if(flag)
            i = 0x40000;
        else
            i = 0;
        setFlags(i, 0x40000);
        if(flag)
            setFlags(1, 17);
    }

    public void setFocusedByDefault(boolean flag)
    {
        boolean flag1 = false;
        if((mPrivateFlags3 & 0x40000) != 0)
            flag1 = true;
        if(flag == flag1)
            return;
        if(flag)
            mPrivateFlags3 = mPrivateFlags3 | 0x40000;
        else
            mPrivateFlags3 = mPrivateFlags3 & 0xfffbffff;
        if(mParent instanceof ViewGroup)
            if(flag)
                ((ViewGroup)mParent).setDefaultFocus(this);
            else
                ((ViewGroup)mParent).clearDefaultFocus(this);
    }

    public final void setFocusedInCluster()
    {
        setFocusedInCluster(findKeyboardNavigationCluster());
    }

    public void setForeground(Drawable drawable)
    {
        if(mForegroundInfo == null)
        {
            if(drawable == null)
                return;
            mForegroundInfo = new ForegroundInfo(null);
        }
        if(drawable == ForegroundInfo._2D_get1(mForegroundInfo))
            return;
        if(ForegroundInfo._2D_get1(mForegroundInfo) != null)
        {
            if(isAttachedToWindow())
                ForegroundInfo._2D_get1(mForegroundInfo).setVisible(false, false);
            ForegroundInfo._2D_get1(mForegroundInfo).setCallback(null);
            unscheduleDrawable(ForegroundInfo._2D_get1(mForegroundInfo));
        }
        ForegroundInfo._2D_set1(mForegroundInfo, drawable);
        ForegroundInfo._2D_set0(mForegroundInfo, true);
        if(drawable == null) goto _L2; else goto _L1
_L1:
        if((mPrivateFlags & 0x80) != 0)
            mPrivateFlags = mPrivateFlags & 0xffffff7f;
        drawable.setLayoutDirection(getLayoutDirection());
        if(drawable.isStateful())
            drawable.setState(getDrawableState());
        applyForegroundTint();
        if(isAttachedToWindow())
        {
            boolean flag;
            if(getWindowVisibility() == 0)
                flag = isShown();
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
        drawable.setCallback(this);
_L4:
        requestLayout();
        invalidate();
        return;
_L2:
        if((mViewFlags & 0x80) != 0 && mBackground == null && mDefaultFocusHighlight == null)
            mPrivateFlags = mPrivateFlags | 0x80;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setForegroundGravity(int i)
    {
        if(mForegroundInfo == null)
            mForegroundInfo = new ForegroundInfo(null);
        if(ForegroundInfo._2D_get2(mForegroundInfo) != i)
        {
            int j = i;
            if((0x800007 & i) == 0)
                j = i | 0x800003;
            i = j;
            if((j & 0x70) == 0)
                i = j | 0x30;
            ForegroundInfo._2D_set2(mForegroundInfo, i);
            requestLayout();
        }
    }

    public void setForegroundTintList(ColorStateList colorstatelist)
    {
        if(mForegroundInfo == null)
            mForegroundInfo = new ForegroundInfo(null);
        if(ForegroundInfo._2D_get6(mForegroundInfo) == null)
            ForegroundInfo._2D_set4(mForegroundInfo, new TintInfo());
        ForegroundInfo._2D_get6(mForegroundInfo).mTintList = colorstatelist;
        ForegroundInfo._2D_get6(mForegroundInfo).mHasTintList = true;
        applyForegroundTint();
    }

    public void setForegroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mForegroundInfo == null)
            mForegroundInfo = new ForegroundInfo(null);
        if(ForegroundInfo._2D_get6(mForegroundInfo) == null)
            ForegroundInfo._2D_set4(mForegroundInfo, new TintInfo());
        ForegroundInfo._2D_get6(mForegroundInfo).mTintMode = mode;
        ForegroundInfo._2D_get6(mForegroundInfo).mHasTintMode = true;
        applyForegroundTint();
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_3;
        if(mLeft != i || mRight != k || mTop != j || mBottom != l)
        {
            boolean flag1 = true;
            int i1 = mPrivateFlags;
            int j1 = mRight - mLeft;
            int k1 = mBottom - mTop;
            int l1 = k - i;
            int i2 = l - j;
            if(l1 != j1 || i2 != k1)
                flag = true;
            else
                flag = false;
            invalidate(flag);
            mLeft = i;
            mTop = j;
            mRight = k;
            mBottom = l;
            mRenderNode.setLeftTopRightBottom(mLeft, mTop, mRight, mBottom);
            mPrivateFlags = mPrivateFlags | 0x10;
            if(flag)
                sizeChange(l1, i2, j1, k1);
            if((mViewFlags & 0xc) == 0 || mGhostView != null)
            {
                mPrivateFlags = mPrivateFlags | 0x20;
                invalidate(flag);
                invalidateParentCaches();
            }
            mPrivateFlags = mPrivateFlags | i1 & 0x20;
            mBackgroundSizeChanged = true;
            mDefaultFocusHighlightSizeChanged = true;
            if(mForegroundInfo != null)
                ForegroundInfo._2D_set0(mForegroundInfo, true);
            notifySubtreeAccessibilityStateChangedIfNeeded();
            flag = flag1;
        }
        return flag;
    }

    public void setHapticFeedbackEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 0x10000000;
        else
            i = 0;
        setFlags(i, 0x10000000);
        mHapticEnabledExplicitly = flag;
    }

    public void setHasTransientState(boolean flag)
    {
        boolean flag1 = hasTransientState();
        int i;
        if(flag)
            i = mTransientStateCount + 1;
        else
            i = mTransientStateCount - 1;
        mTransientStateCount = i;
        if(mTransientStateCount < 0)
        {
            mTransientStateCount = 0;
            Log.e("View", "hasTransientState decremented below 0: unmatched pair of setHasTransientState calls");
        } else
        {
            while(false) 
                if(flag && mTransientStateCount == 1 || !flag && mTransientStateCount == 0)
                {
                    int k = mPrivateFlags2;
                    int j;
                    if(flag)
                        j = 0x80000000;
                    else
                        j = 0;
                    mPrivateFlags2 = j | 0x7fffffff & k;
                    flag = hasTransientState();
                    if(mParent != null && flag != flag1)
                        try
                        {
                            mParent.childHasTransientStateChanged(this, flag);
                        }
                        catch(AbstractMethodError abstractmethoderror)
                        {
                            Log.e("View", (new StringBuilder()).append(mParent.getClass().getSimpleName()).append(" does not fully implement ViewParent").toString(), abstractmethoderror);
                        }
                }
        }
    }

    public void setHorizontalFadingEdgeEnabled(boolean flag)
    {
        if(isHorizontalFadingEdgeEnabled() != flag)
        {
            if(flag)
                initScrollCache();
            mViewFlags = mViewFlags ^ 0x1000;
        }
    }

    public void setHorizontalScrollBarEnabled(boolean flag)
    {
        if(isHorizontalScrollBarEnabled() != flag)
        {
            mViewFlags = mViewFlags ^ 0x100;
            computeOpaqueFlags();
            resolvePadding();
        }
    }

    public void setHovered(boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        if((mPrivateFlags & 0x10000000) == 0)
        {
            mPrivateFlags = mPrivateFlags | 0x10000000;
            refreshDrawableState();
            onHoverChanged(true);
        }
_L4:
        return;
_L2:
        if((mPrivateFlags & 0x10000000) != 0)
        {
            mPrivateFlags = mPrivateFlags & 0xefffffff;
            refreshDrawableState();
            onHoverChanged(false);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setId(int i)
    {
        mID = i;
        if(mID == -1 && mLabelForId != -1)
            mID = generateViewId();
    }

    public void setImportantForAccessibility(int i)
    {
        int j = getImportantForAccessibility();
        if(i != j)
        {
            boolean flag;
            boolean flag1;
            if(i == 4)
                flag1 = true;
            else
                flag1 = false;
            if(i == 2 || flag1)
            {
                View view = findAccessibilityFocusHost(flag1);
                if(view != null)
                    view.clearAccessibilityFocus();
            }
            if(j != 0)
            {
                if(i == 0)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            if(flag)
                flag1 = includeForAccessibility();
            else
                flag1 = false;
            mPrivateFlags2 = mPrivateFlags2 & 0xff8fffff;
            mPrivateFlags2 = mPrivateFlags2 | i << 20 & 0x700000;
            if(!flag || flag1 != includeForAccessibility())
                notifySubtreeAccessibilityStateChangedIfNeeded();
            else
                notifyViewAccessibilityStateChangedIfNeeded(0);
        }
    }

    public void setImportantForAutofill(int i)
    {
        mPrivateFlags3 = mPrivateFlags3 & 0xff87ffff;
        mPrivateFlags3 = mPrivateFlags3 | i << 19 & 0x780000;
    }

    public void setIsRootNamespace(boolean flag)
    {
        if(flag)
            mPrivateFlags = mPrivateFlags | 8;
        else
            mPrivateFlags = mPrivateFlags & -9;
    }

    public void setKeepScreenOn(boolean flag)
    {
        int i;
        if(flag)
            i = 0x4000000;
        else
            i = 0;
        setFlags(i, 0x4000000);
    }

    public void setKeyboardNavigationCluster(boolean flag)
    {
        if(flag)
            mPrivateFlags3 = mPrivateFlags3 | 0x8000;
        else
            mPrivateFlags3 = mPrivateFlags3 & 0xffff7fff;
    }

    public void setLabelFor(int i)
    {
        if(mLabelForId == i)
            return;
        mLabelForId = i;
        if(mLabelForId != -1 && mID == -1)
            mID = generateViewId();
        notifyViewAccessibilityStateChangedIfNeeded(0);
    }

    public void setLayerPaint(Paint paint)
    {
        int i = getLayerType();
        if(i != 0)
        {
            mLayerPaint = paint;
            if(i == 2)
            {
                if(mRenderNode.setLayerPaint(paint))
                    invalidateViewProperty(false, false);
            } else
            {
                invalidate();
            }
        }
    }

    public void setLayerType(int i, Paint paint)
    {
        if(i < 0 || i > 2)
            throw new IllegalArgumentException("Layer type can only be one of: LAYER_TYPE_NONE, LAYER_TYPE_SOFTWARE or LAYER_TYPE_HARDWARE");
        if(!mRenderNode.setLayerType(i))
        {
            setLayerPaint(paint);
            return;
        }
        if(i != 1)
            destroyDrawingCache();
        mLayerType = i;
        if(mLayerType == 0)
            paint = null;
        mLayerPaint = paint;
        mRenderNode.setLayerPaint(mLayerPaint);
        invalidateParentCaches();
        invalidate(true);
    }

    public void setLayoutDirection(int i)
    {
        if(getRawLayoutDirection() != i)
        {
            mPrivateFlags2 = mPrivateFlags2 & 0xfffffff3;
            resetRtlProperties();
            mPrivateFlags2 = mPrivateFlags2 | i << 2 & 0xc;
            resolveRtlPropertiesIfNeeded();
            requestLayout();
            invalidate(true);
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutparams)
    {
        if(layoutparams == null)
            throw new NullPointerException("Layout parameters cannot be null");
        mLayoutParams = layoutparams;
        resolveLayoutParams();
        if(mParent instanceof ViewGroup)
            ((ViewGroup)mParent).onSetLayoutParams(this, layoutparams);
        requestLayout();
    }

    public final void setLeft(int i)
    {
        if(i != mLeft)
        {
            boolean flag = hasIdentityMatrix();
            if(flag)
            {
                if(mAttachInfo != null)
                {
                    int j;
                    int k;
                    int l;
                    if(i < mLeft)
                    {
                        j = i;
                        k = i - mLeft;
                    } else
                    {
                        j = mLeft;
                        k = 0;
                    }
                    invalidate(k, 0, mRight - j, mBottom - mTop);
                }
            } else
            {
                invalidate(true);
            }
            k = mRight;
            l = mLeft;
            j = mBottom - mTop;
            mLeft = i;
            mRenderNode.setLeft(i);
            sizeChange(mRight - mLeft, j, k - l, j);
            if(!flag)
            {
                mPrivateFlags = mPrivateFlags | 0x20;
                invalidate(true);
            }
            mBackgroundSizeChanged = true;
            mDefaultFocusHighlightSizeChanged = true;
            if(mForegroundInfo != null)
                ForegroundInfo._2D_set0(mForegroundInfo, true);
            invalidateParentIfNeeded();
            if((mPrivateFlags2 & 0x10000000) == 0x10000000)
                invalidateParentIfNeeded();
        }
    }

    public void setLeftTopRightBottom(int i, int j, int k, int l)
    {
        setFrame(i, j, k, l);
    }

    public void setLongClickable(boolean flag)
    {
        int i;
        if(flag)
            i = 0x200000;
        else
            i = 0;
        setFlags(i, 0x200000);
    }

    protected final void setMeasuredDimension(int i, int j)
    {
        boolean flag = isLayoutModeOptical(this);
        int k = i;
        int l = j;
        if(flag != isLayoutModeOptical(mParent))
        {
            Insets insets = getOpticalInsets();
            l = insets.left + insets.right;
            int i1 = insets.top + insets.bottom;
            if(!flag)
                l = -l;
            k = i + l;
            if(flag)
                i = i1;
            else
                i = -i1;
            l = j + i;
        }
        setMeasuredDimensionRaw(k, l);
    }

    public void setMinimumHeight(int i)
    {
        mMinHeight = i;
        requestLayout();
    }

    public void setMinimumWidth(int i)
    {
        mMinWidth = i;
        requestLayout();
    }

    public void setNestedScrollingEnabled(boolean flag)
    {
        if(flag)
        {
            mPrivateFlags3 = mPrivateFlags3 | 0x80;
        } else
        {
            stopNestedScroll();
            mPrivateFlags3 = mPrivateFlags3 & 0xffffff7f;
        }
    }

    public void setNextClusterForwardId(int i)
    {
        mNextClusterForwardId = i;
    }

    public void setNextFocusDownId(int i)
    {
        mNextFocusDownId = i;
    }

    public void setNextFocusForwardId(int i)
    {
        mNextFocusForwardId = i;
    }

    public void setNextFocusLeftId(int i)
    {
        mNextFocusLeftId = i;
    }

    public void setNextFocusRightId(int i)
    {
        mNextFocusRightId = i;
    }

    public void setNextFocusUpId(int i)
    {
        mNextFocusUpId = i;
    }

    public void setOnApplyWindowInsetsListener(OnApplyWindowInsetsListener onapplywindowinsetslistener)
    {
        getListenerInfo().mOnApplyWindowInsetsListener = onapplywindowinsetslistener;
    }

    public void setOnCapturedPointerListener(OnCapturedPointerListener oncapturedpointerlistener)
    {
        getListenerInfo().mOnCapturedPointerListener = oncapturedpointerlistener;
    }

    public void setOnClickListener(OnClickListener onclicklistener)
    {
        if(!isClickable())
            setClickable(true);
        getListenerInfo().mOnClickListener = onclicklistener;
    }

    public void setOnContextClickListener(OnContextClickListener oncontextclicklistener)
    {
        if(!isContextClickable())
            setContextClickable(true);
        getListenerInfo().mOnContextClickListener = oncontextclicklistener;
    }

    public void setOnCreateContextMenuListener(OnCreateContextMenuListener oncreatecontextmenulistener)
    {
        if(!isLongClickable())
            setLongClickable(true);
        getListenerInfo().mOnCreateContextMenuListener = oncreatecontextmenulistener;
    }

    public void setOnDragListener(OnDragListener ondraglistener)
    {
        ListenerInfo._2D_set1(getListenerInfo(), ondraglistener);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onfocuschangelistener)
    {
        getListenerInfo().mOnFocusChangeListener = onfocuschangelistener;
    }

    public void setOnGenericMotionListener(OnGenericMotionListener ongenericmotionlistener)
    {
        ListenerInfo._2D_set2(getListenerInfo(), ongenericmotionlistener);
    }

    public void setOnHoverListener(OnHoverListener onhoverlistener)
    {
        ListenerInfo._2D_set3(getListenerInfo(), onhoverlistener);
    }

    public void setOnKeyListener(OnKeyListener onkeylistener)
    {
        ListenerInfo._2D_set4(getListenerInfo(), onkeylistener);
    }

    public void setOnLongClickListener(OnLongClickListener onlongclicklistener)
    {
        if(!isLongClickable())
            setLongClickable(true);
        getListenerInfo().mOnLongClickListener = onlongclicklistener;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onscrollchangelistener)
    {
        getListenerInfo().mOnScrollChangeListener = onscrollchangelistener;
    }

    public void setOnSystemUiVisibilityChangeListener(OnSystemUiVisibilityChangeListener onsystemuivisibilitychangelistener)
    {
        ListenerInfo._2D_set6(getListenerInfo(), onsystemuivisibilitychangelistener);
        if(mParent != null && mAttachInfo != null && mAttachInfo.mRecomputeGlobalAttributes ^ true)
            mParent.recomputeViewAttributes(this);
    }

    public void setOnTouchListener(OnTouchListener ontouchlistener)
    {
        ListenerInfo._2D_set7(getListenerInfo(), ontouchlistener);
    }

    public void setOpticalInsets(Insets insets)
    {
        mLayoutInsets = insets;
    }

    public void setOutlineProvider(ViewOutlineProvider viewoutlineprovider)
    {
        mOutlineProvider = viewoutlineprovider;
        invalidateOutline();
    }

    public void setOverScrollMode(int i)
    {
        if(i != 0 && i != 1 && i != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid overscroll mode ").append(i).toString());
        } else
        {
            mOverScrollMode = i;
            return;
        }
    }

    public void setPadding(int i, int j, int k, int l)
    {
        resetResolvedPaddingInternal();
        mUserPaddingStart = 0x80000000;
        mUserPaddingEnd = 0x80000000;
        mUserPaddingLeftInitial = i;
        mUserPaddingRightInitial = k;
        mLeftPaddingDefined = true;
        mRightPaddingDefined = true;
        internalSetPadding(i, j, k, l);
    }

    public void setPaddingRelative(int i, int j, int k, int l)
    {
        resetResolvedPaddingInternal();
        mUserPaddingStart = i;
        mUserPaddingEnd = k;
        mLeftPaddingDefined = true;
        mRightPaddingDefined = true;
        getLayoutDirection();
        JVM INSTR tableswitch 1 1: default 48
    //                   1 68;
           goto _L1 _L2
_L1:
        mUserPaddingLeftInitial = i;
        mUserPaddingRightInitial = k;
        internalSetPadding(i, j, k, l);
_L4:
        return;
_L2:
        mUserPaddingLeftInitial = k;
        mUserPaddingRightInitial = i;
        internalSetPadding(k, j, i, l);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setPivotX(float f)
    {
        if(!mRenderNode.isPivotExplicitlySet() || f != getPivotX())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setPivotX(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
        }
    }

    public void setPivotY(float f)
    {
        if(!mRenderNode.isPivotExplicitlySet() || f != getPivotY())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setPivotY(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
        }
    }

    public void setPointerIcon(PointerIcon pointericon)
    {
        mPointerIcon = pointericon;
        if(mAttachInfo == null || mAttachInfo.mHandlingPointerEvent)
            return;
        mAttachInfo.mSession.updatePointerIcon(mAttachInfo.mWindow);
_L2:
        return;
        pointericon;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setPressed(boolean flag)
    {
        boolean flag1;
        boolean flag2;
        if((mPrivateFlags & 0x4000) == 16384)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
            flag2 = true;
        else
            flag2 = false;
        if(flag)
            mPrivateFlags = mPrivateFlags | 0x4000;
        else
            mPrivateFlags = mPrivateFlags & 0xffffbfff;
        if(flag2)
            refreshDrawableState();
        dispatchSetPressed(flag);
    }

    public void setRevealClip(boolean flag, float f, float f1, float f2)
    {
        mRenderNode.setRevealClip(flag, f, f1, f2);
        invalidateViewProperty(false, false);
    }

    public final void setRevealOnFocusHint(boolean flag)
    {
        if(flag)
            mPrivateFlags3 = mPrivateFlags3 & 0xfbffffff;
        else
            mPrivateFlags3 = mPrivateFlags3 | 0x4000000;
    }

    public final void setRight(int i)
    {
        if(i != mRight)
        {
            boolean flag = hasIdentityMatrix();
            if(flag)
            {
                if(mAttachInfo != null)
                {
                    int j;
                    int k;
                    int l;
                    if(i < mRight)
                        j = mRight;
                    else
                        j = i;
                    invalidate(0, 0, j - mLeft, mBottom - mTop);
                }
            } else
            {
                invalidate(true);
            }
            k = mRight;
            l = mLeft;
            j = mBottom - mTop;
            mRight = i;
            mRenderNode.setRight(mRight);
            sizeChange(mRight - mLeft, j, k - l, j);
            if(!flag)
            {
                mPrivateFlags = mPrivateFlags | 0x20;
                invalidate(true);
            }
            mBackgroundSizeChanged = true;
            mDefaultFocusHighlightSizeChanged = true;
            if(mForegroundInfo != null)
                ForegroundInfo._2D_set0(mForegroundInfo, true);
            invalidateParentIfNeeded();
            if((mPrivateFlags2 & 0x10000000) == 0x10000000)
                invalidateParentIfNeeded();
        }
    }

    public void setRotation(float f)
    {
        if(f != getRotation())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setRotation(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void setRotationX(float f)
    {
        if(f != getRotationX())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setRotationX(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void setRotationY(float f)
    {
        if(f != getRotationY())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setRotationY(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void setSaveEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 0;
        else
            i = 0x10000;
        setFlags(i, 0x10000);
    }

    public void setSaveFromParentEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 0;
        else
            i = 0x20000000;
        setFlags(i, 0x20000000);
    }

    public void setScaleX(float f)
    {
        if(f != getScaleX())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setScaleX(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void setScaleY(float f)
    {
        if(f != getScaleY())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setScaleY(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void setScrollBarDefaultDelayBeforeFade(int i)
    {
        getScrollCache().scrollBarDefaultDelayBeforeFade = i;
    }

    public void setScrollBarFadeDuration(int i)
    {
        getScrollCache().scrollBarFadeDuration = i;
    }

    public void setScrollBarSize(int i)
    {
        getScrollCache().scrollBarSize = i;
    }

    public void setScrollBarStyle(int i)
    {
        if(i != (mViewFlags & 0x3000000))
        {
            mViewFlags = mViewFlags & 0xfcffffff | i & 0x3000000;
            computeOpaqueFlags();
            resolvePadding();
        }
    }

    public void setScrollContainer(boolean flag)
    {
        if(flag)
        {
            if(mAttachInfo != null && (mPrivateFlags & 0x100000) == 0)
            {
                mAttachInfo.mScrollContainers.add(this);
                mPrivateFlags = mPrivateFlags | 0x100000;
            }
            mPrivateFlags = mPrivateFlags | 0x80000;
        } else
        {
            if((mPrivateFlags & 0x100000) != 0)
                mAttachInfo.mScrollContainers.remove(this);
            mPrivateFlags = mPrivateFlags & 0xffe7ffff;
        }
    }

    public void setScrollIndicators(int i)
    {
        setScrollIndicators(i, 63);
    }

    public void setScrollIndicators(int i, int j)
    {
        j = j << 8 & 0x3f00;
        i = i << 8 & j;
        j = i | mPrivateFlags3 & j;
        if(mPrivateFlags3 != j)
        {
            mPrivateFlags3 = j;
            if(i != 0)
                initializeScrollIndicatorsInternal();
            invalidate();
        }
    }

    public void setScrollX(int i)
    {
        scrollTo(i, mScrollY);
    }

    public void setScrollY(int i)
    {
        scrollTo(mScrollX, i);
    }

    public void setScrollbarFadingEnabled(boolean flag)
    {
        initScrollCache();
        ScrollabilityCache scrollabilitycache = mScrollCache;
        scrollabilitycache.fadeScrollBars = flag;
        if(flag)
            scrollabilitycache.state = 0;
        else
            scrollabilitycache.state = 1;
    }

    public void setSelected(boolean flag)
    {
        boolean flag1;
        if((mPrivateFlags & 4) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 != flag)
        {
            int i = mPrivateFlags;
            byte byte0;
            if(flag)
                byte0 = 4;
            else
                byte0 = 0;
            mPrivateFlags = byte0 | i & -5;
            if(!flag)
                resetPressedState();
            invalidate(true);
            refreshDrawableState();
            dispatchSetSelected(flag);
            if(flag)
                sendAccessibilityEvent(4);
            else
                notifyViewAccessibilityStateChangedIfNeeded(0);
        }
    }

    public void setSoundEffectsEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 0x8000000;
        else
            i = 0;
        setFlags(i, 0x8000000);
    }

    public void setStateListAnimator(StateListAnimator statelistanimator)
    {
        if(mStateListAnimator == statelistanimator)
            return;
        if(mStateListAnimator != null)
            mStateListAnimator.setTarget(null);
        mStateListAnimator = statelistanimator;
        if(statelistanimator != null)
        {
            statelistanimator.setTarget(this);
            if(isAttachedToWindow())
                statelistanimator.setState(getDrawableState());
        }
    }

    public void setSystemUiVisibility(int i)
    {
        if(i != mSystemUiVisibility)
        {
            mSystemUiVisibility = i;
            if(mParent != null && mAttachInfo != null && mAttachInfo.mRecomputeGlobalAttributes ^ true)
                mParent.recomputeViewAttributes(this);
        }
    }

    public void setTag(int i, Object obj)
    {
        if(i >>> 24 < 2)
        {
            throw new IllegalArgumentException("The key must be an application-specific resource id.");
        } else
        {
            setKeyedTag(i, obj);
            return;
        }
    }

    public void setTag(Object obj)
    {
        mTag = obj;
    }

    public void setTagInternal(int i, Object obj)
    {
        if(i >>> 24 != 1)
        {
            throw new IllegalArgumentException("The key must be a framework-specific resource id.");
        } else
        {
            setKeyedTag(i, obj);
            return;
        }
    }

    public void setTextAlignment(int i)
    {
        if(i != getRawTextAlignment())
        {
            mPrivateFlags2 = mPrivateFlags2 & 0xffff1fff;
            resetResolvedTextAlignment();
            mPrivateFlags2 = mPrivateFlags2 | i << 13 & 0xe000;
            resolveTextAlignment();
            onRtlPropertiesChanged(getLayoutDirection());
            requestLayout();
            invalidate(true);
        }
    }

    public void setTextDirection(int i)
    {
        if(getRawTextDirection() != i)
        {
            mPrivateFlags2 = mPrivateFlags2 & 0xfffffe3f;
            resetResolvedTextDirection();
            mPrivateFlags2 = mPrivateFlags2 | i << 6 & 0x1c0;
            resolveTextDirection();
            onRtlPropertiesChanged(getLayoutDirection());
            requestLayout();
            invalidate(true);
        }
    }

    public void setTooltip(CharSequence charsequence)
    {
        setTooltipText(charsequence);
    }

    public void setTooltipText(CharSequence charsequence)
    {
        if(TextUtils.isEmpty(charsequence))
        {
            setFlags(0, 0x40000000);
            hideTooltip();
            mTooltipInfo = null;
        } else
        {
            setFlags(0x40000000, 0x40000000);
            if(mTooltipInfo == null)
            {
                mTooltipInfo = new TooltipInfo(null);
                mTooltipInfo.mShowTooltipRunnable = new _.Lambda.XmA8Y30pNAdQP9ujRlGx1qfDHH8((byte)2, this);
                mTooltipInfo.mHideTooltipRunnable = new _.Lambda.XmA8Y30pNAdQP9ujRlGx1qfDHH8((byte)3, this);
            }
            mTooltipInfo.mTooltipText = charsequence;
        }
    }

    public final void setTop(int i)
    {
        if(i != mTop)
        {
            boolean flag = hasIdentityMatrix();
            if(flag)
            {
                if(mAttachInfo != null)
                {
                    int j;
                    int k;
                    int l;
                    if(i < mTop)
                    {
                        j = i;
                        k = i - mTop;
                    } else
                    {
                        j = mTop;
                        k = 0;
                    }
                    invalidate(0, k, mRight - mLeft, mBottom - j);
                }
            } else
            {
                invalidate(true);
            }
            l = mRight - mLeft;
            j = mBottom;
            k = mTop;
            mTop = i;
            mRenderNode.setTop(mTop);
            sizeChange(l, mBottom - mTop, l, j - k);
            if(!flag)
            {
                mPrivateFlags = mPrivateFlags | 0x20;
                invalidate(true);
            }
            mBackgroundSizeChanged = true;
            mDefaultFocusHighlightSizeChanged = true;
            if(mForegroundInfo != null)
                ForegroundInfo._2D_set0(mForegroundInfo, true);
            invalidateParentIfNeeded();
            if((mPrivateFlags2 & 0x10000000) == 0x10000000)
                invalidateParentIfNeeded();
        }
    }

    public void setTouchDelegate(TouchDelegate touchdelegate)
    {
        mTouchDelegate = touchdelegate;
    }

    public void setTransitionAlpha(float f)
    {
        ensureTransformationInfo();
        if(mTransformationInfo.mTransitionAlpha != f)
        {
            mTransformationInfo.mTransitionAlpha = f;
            mPrivateFlags = mPrivateFlags & 0xfffbffff;
            invalidateViewProperty(true, false);
            mRenderNode.setAlpha(getFinalAlpha());
        }
    }

    public final void setTransitionName(String s)
    {
        mTransitionName = s;
    }

    public void setTransitionVisibility(int i)
    {
        mViewFlags = mViewFlags & 0xfffffff3 | i;
    }

    public void setTranslationX(float f)
    {
        if(f != getTranslationX())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setTranslationX(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void setTranslationY(float f)
    {
        if(f != getTranslationY())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setTranslationY(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
            notifySubtreeAccessibilityStateChangedIfNeeded();
        }
    }

    public void setTranslationZ(float f)
    {
        if(f != getTranslationZ())
        {
            invalidateViewProperty(true, false);
            mRenderNode.setTranslationZ(f);
            invalidateViewProperty(false, true);
            invalidateParentIfNeededAndWasQuickRejected();
        }
    }

    public void setVerticalFadingEdgeEnabled(boolean flag)
    {
        if(isVerticalFadingEdgeEnabled() != flag)
        {
            if(flag)
                initScrollCache();
            mViewFlags = mViewFlags ^ 0x2000;
        }
    }

    public void setVerticalScrollBarEnabled(boolean flag)
    {
        if(isVerticalScrollBarEnabled() != flag)
        {
            mViewFlags = mViewFlags ^ 0x200;
            computeOpaqueFlags();
            resolvePadding();
        }
    }

    public void setVerticalScrollbarPosition(int i)
    {
        if(mVerticalScrollbarPosition != i)
        {
            mVerticalScrollbarPosition = i;
            computeOpaqueFlags();
            resolvePadding();
        }
    }

    public void setVisibility(int i)
    {
        setFlags(i, 12);
    }

    public void setWillNotCacheDrawing(boolean flag)
    {
        int i;
        if(flag)
            i = 0x20000;
        else
            i = 0;
        setFlags(i, 0x20000);
    }

    public void setWillNotDraw(boolean flag)
    {
        char c;
        if(flag)
            c = '\200';
        else
            c = '\0';
        setFlags(c, 128);
    }

    public void setX(float f)
    {
        setTranslationX(f - (float)mLeft);
    }

    public void setY(float f)
    {
        setTranslationY(f - (float)mTop);
    }

    public void setZ(float f)
    {
        setTranslationZ(f - getElevation());
    }

    boolean shouldDrawRoundScrollbar()
    {
        boolean flag = true;
        if(!mResources.getConfiguration().isScreenRound() || mAttachInfo == null)
            return false;
        View view = getRootView();
        WindowInsets windowinsets = getRootWindowInsets();
        int i = getHeight();
        int j = getWidth();
        int k = view.getHeight();
        int l = view.getWidth();
        if(i != k || j != l)
            return false;
        getLocationInWindow(mAttachInfo.mTmpLocation);
        if(mAttachInfo.mTmpLocation[0] == windowinsets.getStableInsetLeft())
        {
            if(mAttachInfo.mTmpLocation[1] != windowinsets.getStableInsetTop())
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean showContextMenu()
    {
        return getParent().showContextMenuForChild(this);
    }

    public boolean showContextMenu(float f, float f1)
    {
        return getParent().showContextMenuForChild(this, f, f1);
    }

    public ActionMode startActionMode(ActionMode.Callback callback)
    {
        return startActionMode(callback, 0);
    }

    public ActionMode startActionMode(ActionMode.Callback callback, int i)
    {
        ViewParent viewparent = getParent();
        if(viewparent == null)
            return null;
        ActionMode actionmode;
        try
        {
            actionmode = viewparent.startActionModeForChild(this, callback, i);
        }
        catch(AbstractMethodError abstractmethoderror)
        {
            return viewparent.startActionModeForChild(this, callback);
        }
        return actionmode;
    }

    public void startActivityForResult(Intent intent, int i)
    {
        mStartActivityRequestWho = (new StringBuilder()).append("@android:view:").append(System.identityHashCode(this)).toString();
        getContext().startActivityForResult(mStartActivityRequestWho, intent, i, null);
    }

    public void startAnimation(Animation animation)
    {
        animation.setStartTime(-1L);
        setAnimation(animation);
        invalidateParentCaches();
        invalidate(true);
    }

    public final boolean startDrag(ClipData clipdata, DragShadowBuilder dragshadowbuilder, Object obj, int i)
    {
        return startDragAndDrop(clipdata, dragshadowbuilder, obj, i);
    }

    public final boolean startDragAndDrop(ClipData clipdata, DragShadowBuilder dragshadowbuilder, Object obj, int i)
    {
        boolean flag2;
        Point point;
        Point point1;
        if(mAttachInfo == null)
        {
            Log.w("View", "startDragAndDrop called on a detached view.");
            return false;
        }
        if(clipdata != null)
        {
            boolean flag;
            if((i & 0x100) != 0)
                flag = true;
            else
                flag = false;
            clipdata.prepareToLeaveProcess(flag);
        }
        flag2 = false;
        point = new Point();
        point1 = new Point();
        dragshadowbuilder.onProvideShadowMetrics(point, point1);
        while(point.x < 0 || point.y < 0 || point1.x < 0 || point1.y < 0) 
            throw new IllegalStateException("Drag shadow dimensions must not be negative");
        if(mAttachInfo.mDragSurface != null)
            mAttachInfo.mDragSurface.release();
        mAttachInfo.mDragSurface = new Surface();
        mAttachInfo.mDragToken = mAttachInfo.mSession.prepareDrag(mAttachInfo.mWindow, i, point.x, point.y, mAttachInfo.mDragSurface);
        boolean flag1 = flag2;
        Canvas canvas;
        if(mAttachInfo.mDragToken == null)
            break MISSING_BLOCK_LABEL_325;
        canvas = mAttachInfo.mDragSurface.lockCanvas(null);
        canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        dragshadowbuilder.onDrawShadow(canvas);
        mAttachInfo.mDragSurface.unlockCanvasAndPost(canvas);
        dragshadowbuilder = getViewRootImpl();
        dragshadowbuilder.setLocalDragState(obj);
        dragshadowbuilder.getLastTouchPoint(point);
        flag1 = mAttachInfo.mSession.performDrag(mAttachInfo.mWindow, mAttachInfo.mDragToken, dragshadowbuilder.getLastTouchSource(), point.x, point.y, point1.x, point1.y, clipdata);
_L2:
        return flag1;
        clipdata;
        try
        {
            mAttachInfo.mDragSurface.unlockCanvasAndPost(canvas);
            throw clipdata;
        }
        // Misplaced declaration of an exception variable
        catch(ClipData clipdata)
        {
            Log.e("View", "Unable to initiate drag", clipdata);
        }
        mAttachInfo.mDragSurface.destroy();
        mAttachInfo.mDragSurface = null;
        flag1 = flag2;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final boolean startMovingTask(float f, float f1)
    {
        boolean flag;
        try
        {
            flag = mAttachInfo.mSession.startMovingTask(mAttachInfo.mWindow, f, f1);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("View", "Unable to start moving", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean startNestedScroll(int i)
    {
        ViewParent viewparent;
        View view;
        if(hasNestedScrollingParent())
            return true;
        if(!isNestedScrollingEnabled())
            break MISSING_BLOCK_LABEL_120;
        viewparent = getParent();
        view = this;
_L3:
        if(viewparent == null) goto _L2; else goto _L1
_L1:
        if(!viewparent.onStartNestedScroll(view, this, i))
            break MISSING_BLOCK_LABEL_98;
        mNestedScrollingParent = viewparent;
        viewparent.onNestedScrollAccepted(view, this, i);
        return true;
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        Log.e("View", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onStartNestedScroll").toString(), abstractmethoderror);
        if(viewparent instanceof View)
            view = (View)viewparent;
        viewparent = viewparent.getParent();
          goto _L3
_L2:
        return false;
    }

    public void stopNestedScroll()
    {
        if(mNestedScrollingParent != null)
        {
            mNestedScrollingParent.onStopNestedScroll(this);
            mNestedScrollingParent = null;
        }
    }

    public boolean toGlobalMotionEvent(MotionEvent motionevent)
    {
        Object obj = mAttachInfo;
        if(obj == null)
        {
            return false;
        } else
        {
            obj = ((AttachInfo) (obj)).mTmpMatrix;
            ((Matrix) (obj)).set(Matrix.IDENTITY_MATRIX);
            transformMatrixToGlobal(((Matrix) (obj)));
            motionevent.transform(((Matrix) (obj)));
            return true;
        }
    }

    public boolean toLocalMotionEvent(MotionEvent motionevent)
    {
        Object obj = mAttachInfo;
        if(obj == null)
        {
            return false;
        } else
        {
            obj = ((AttachInfo) (obj)).mTmpMatrix;
            ((Matrix) (obj)).set(Matrix.IDENTITY_MATRIX);
            transformMatrixToLocal(((Matrix) (obj)));
            motionevent.transform(((Matrix) (obj)));
            return true;
        }
    }

    public String toString()
    {
        int i;
        StringBuilder stringbuilder;
        i = 68;
        stringbuilder = new StringBuilder(128);
        stringbuilder.append(getClass().getName());
        stringbuilder.append('{');
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(' ');
        mViewFlags & 0xc;
        JVM INSTR lookupswitch 3: default 92
    //                   0: 702
    //                   4: 712
    //                   8: 722;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuilder.append('.');
_L11:
        String s;
        char c;
        Object obj;
        String s1;
        if((mViewFlags & 1) == 1)
        {
            byte byte1 = 70;
            c = byte1;
        } else
        {
            byte byte15 = 46;
            c = byte15;
        }
        stringbuilder.append(c);
        if((mViewFlags & 0x20) == 0)
        {
            byte byte2 = 69;
            c = byte2;
        } else
        {
            byte byte16 = 46;
            c = byte16;
        }
        stringbuilder.append(c);
        if((mViewFlags & 0x80) == 128)
        {
            byte byte3 = 46;
            c = byte3;
        } else
        {
            byte byte17 = 68;
            c = byte17;
        }
        stringbuilder.append(c);
        if((mViewFlags & 0x100) != 0)
        {
            byte byte4 = 72;
            c = byte4;
        } else
        {
            byte byte18 = 46;
            c = byte18;
        }
        stringbuilder.append(c);
        if((mViewFlags & 0x200) != 0)
        {
            byte byte5 = 86;
            c = byte5;
        } else
        {
            byte byte19 = 46;
            c = byte19;
        }
        stringbuilder.append(c);
        if((mViewFlags & 0x4000) != 0)
        {
            byte byte6 = 67;
            c = byte6;
        } else
        {
            byte byte20 = 46;
            c = byte20;
        }
        stringbuilder.append(c);
        if((mViewFlags & 0x200000) != 0)
        {
            byte byte7 = 76;
            c = byte7;
        } else
        {
            byte byte21 = 46;
            c = byte21;
        }
        stringbuilder.append(c);
        if((mViewFlags & 0x800000) != 0)
        {
            byte byte8 = 88;
            c = byte8;
        } else
        {
            byte byte22 = 46;
            c = byte22;
        }
        stringbuilder.append(c);
        stringbuilder.append(' ');
        if((mPrivateFlags & 8) != 0)
        {
            byte byte9 = 82;
            c = byte9;
        } else
        {
            byte byte23 = 46;
            c = byte23;
        }
        stringbuilder.append(c);
        if((mPrivateFlags & 2) != 0)
        {
            byte byte10 = 70;
            c = byte10;
        } else
        {
            byte byte24 = 46;
            c = byte24;
        }
        stringbuilder.append(c);
        if((mPrivateFlags & 4) != 0)
        {
            byte byte11 = 83;
            c = byte11;
        } else
        {
            byte byte25 = 46;
            c = byte25;
        }
        stringbuilder.append(c);
        if((mPrivateFlags & 0x2000000) != 0)
        {
            stringbuilder.append('p');
        } else
        {
            if((mPrivateFlags & 0x4000) != 0)
            {
                byte byte26 = 80;
                c = byte26;
            } else
            {
                byte byte27 = 46;
                c = byte27;
            }
            stringbuilder.append(c);
        }
        if((mPrivateFlags & 0x10000000) != 0)
        {
            byte byte12 = 72;
            c = byte12;
        } else
        {
            byte byte28 = 46;
            c = byte28;
        }
        stringbuilder.append(c);
        if((mPrivateFlags & 0x40000000) != 0)
        {
            byte byte13 = 65;
            c = byte13;
        } else
        {
            byte byte29 = 46;
            c = byte29;
        }
        stringbuilder.append(c);
        if((mPrivateFlags & 0x80000000) != 0)
        {
            byte byte14 = 73;
            c = byte14;
        } else
        {
            byte byte30 = 46;
            c = byte30;
        }
        stringbuilder.append(c);
        if((mPrivateFlags & 0x600000) != 0)
        {
            c = i;
        } else
        {
            byte byte0 = 46;
            c = byte0;
        }
        stringbuilder.append(c);
        stringbuilder.append(' ');
        stringbuilder.append(mLeft);
        stringbuilder.append(',');
        stringbuilder.append(mTop);
        stringbuilder.append('-');
        stringbuilder.append(mRight);
        stringbuilder.append(',');
        stringbuilder.append(mBottom);
        i = getId();
        if(i == -1) goto _L6; else goto _L5
_L5:
        stringbuilder.append(" #");
        stringbuilder.append(Integer.toHexString(i));
        obj = mResources;
        if(i <= 0 || !Resources.resourceHasPackage(i) || obj == null) goto _L6; else goto _L7
_L7:
        0xff000000 & i;
        JVM INSTR lookupswitch 2: default 620
    //                   16777216: 911
    //                   2130706432: 903;
           goto _L8 _L9 _L10
_L8:
        s = ((Resources) (obj)).getResourcePackageName(i);
_L12:
        s1 = ((Resources) (obj)).getResourceTypeName(i);
        obj = ((Resources) (obj)).getResourceEntryName(i);
        stringbuilder.append(" ");
        stringbuilder.append(s);
        stringbuilder.append(":");
        stringbuilder.append(s1);
        stringbuilder.append("/");
        stringbuilder.append(((String) (obj)));
_L6:
        stringbuilder.append("}");
        return stringbuilder.toString();
_L2:
        stringbuilder.append('V');
          goto _L11
_L3:
        stringbuilder.append('I');
          goto _L11
_L4:
        stringbuilder.append('G');
          goto _L11
_L10:
        s = "app";
          goto _L12
_L9:
        s = "android";
          goto _L12
        android.content.res.Resources.NotFoundException notfoundexception;
        notfoundexception;
          goto _L6
    }

    public void transformFromViewToWindowSpace(int ai[])
    {
        if(ai == null || ai.length < 2)
            throw new IllegalArgumentException("inOutLocation must be an array of two integers");
        if(mAttachInfo == null)
        {
            ai[1] = 0;
            ai[0] = 0;
            return;
        }
        float af[] = mAttachInfo.mTmpTransformLocation;
        af[0] = ai[0];
        af[1] = ai[1];
        if(!hasIdentityMatrix())
            getMatrix().mapPoints(af);
        af[0] = af[0] + (float)mLeft;
        af[1] = af[1] + (float)mTop;
        Object obj;
        for(obj = mParent; obj instanceof View; obj = ((View) (obj)).mParent)
        {
            obj = (View)obj;
            af[0] = af[0] - (float)((View) (obj)).mScrollX;
            af[1] = af[1] - (float)((View) (obj)).mScrollY;
            if(!((View) (obj)).hasIdentityMatrix())
                ((View) (obj)).getMatrix().mapPoints(af);
            af[0] = af[0] + (float)((View) (obj)).mLeft;
            af[1] = af[1] + (float)((View) (obj)).mTop;
        }

        if(obj instanceof ViewRootImpl)
        {
            obj = (ViewRootImpl)obj;
            af[1] = af[1] - (float)((ViewRootImpl) (obj)).mCurScrollY;
        }
        ai[0] = Math.round(af[0]);
        ai[1] = Math.round(af[1]);
    }

    public void transformMatrixToGlobal(Matrix matrix)
    {
        Object obj = mParent;
        if(!(obj instanceof View)) goto _L2; else goto _L1
_L1:
        obj = (View)obj;
        ((View) (obj)).transformMatrixToGlobal(matrix);
        matrix.preTranslate(-((View) (obj)).mScrollX, -((View) (obj)).mScrollY);
_L4:
        matrix.preTranslate(mLeft, mTop);
        if(!hasIdentityMatrix())
            matrix.preConcat(getMatrix());
        return;
_L2:
        if(obj instanceof ViewRootImpl)
        {
            obj = (ViewRootImpl)obj;
            ((ViewRootImpl) (obj)).transformMatrixToGlobal(matrix);
            matrix.preTranslate(0.0F, -((ViewRootImpl) (obj)).mCurScrollY);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void transformMatrixToLocal(Matrix matrix)
    {
        Object obj = mParent;
        if(!(obj instanceof View)) goto _L2; else goto _L1
_L1:
        obj = (View)obj;
        ((View) (obj)).transformMatrixToLocal(matrix);
        matrix.postTranslate(((View) (obj)).mScrollX, ((View) (obj)).mScrollY);
_L4:
        matrix.postTranslate(-mLeft, -mTop);
        if(!hasIdentityMatrix())
            matrix.postConcat(getInverseMatrix());
        return;
_L2:
        if(obj instanceof ViewRootImpl)
        {
            obj = (ViewRootImpl)obj;
            ((ViewRootImpl) (obj)).transformMatrixToLocal(matrix);
            matrix.postTranslate(0.0F, ((ViewRootImpl) (obj)).mCurScrollY);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    void transformRect(Rect rect)
    {
        if(!getMatrix().isIdentity())
        {
            RectF rectf = mAttachInfo.mTmpTransformRect;
            rectf.set(rect);
            getMatrix().mapRect(rectf);
            rect.set((int)Math.floor(rectf.left), (int)Math.floor(rectf.top), (int)Math.ceil(rectf.right), (int)Math.ceil(rectf.bottom));
        }
    }

    void unFocus(View view)
    {
        clearFocusInternal(view, false, false);
    }

    public void unscheduleDrawable(Drawable drawable)
    {
        if(mAttachInfo != null && drawable != null)
            mAttachInfo.mViewRootImpl.mChoreographer.removeCallbacks(1, null, drawable);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        if(verifyDrawable(drawable) && runnable != null)
        {
            if(mAttachInfo != null)
                mAttachInfo.mViewRootImpl.mChoreographer.removeCallbacks(1, runnable, drawable);
            getRunQueue().removeCallbacks(runnable);
        }
    }

    public RenderNode updateDisplayListIfDirty()
    {
        RenderNode rendernode;
        int i1;
        DisplayListCanvas displaylistcanvas;
        rendernode = mRenderNode;
        if(!canHaveDisplayList())
            return rendernode;
        if((mPrivateFlags & 0x8000) != 0 && !(rendernode.isValid() ^ true) && !mRecreateDisplayList)
            break MISSING_BLOCK_LABEL_337;
        if(rendernode.isValid() && mRecreateDisplayList ^ true)
        {
            mPrivateFlags = mPrivateFlags | 0x8020;
            mPrivateFlags = mPrivateFlags & 0xff9fffff;
            dispatchGetDisplayList();
            return rendernode;
        }
        mRecreateDisplayList = true;
        int i = mRight;
        int j = mLeft;
        int k = mBottom;
        int l = mTop;
        i1 = getLayerType();
        displaylistcanvas = rendernode.start(i - j, k - l);
        displaylistcanvas.setHighContrastText(mAttachInfo.mHighContrastText);
        if(i1 != 1) goto _L2; else goto _L1
_L1:
        Bitmap bitmap;
        buildDrawingCache(true);
        bitmap = getDrawingCache(true);
        if(bitmap == null)
            break MISSING_BLOCK_LABEL_181;
        displaylistcanvas.drawBitmap(bitmap, 0.0F, 0.0F, mLayerPaint);
_L3:
        rendernode.end(displaylistcanvas);
        setDisplayListProperties(rendernode);
_L4:
        return rendernode;
_L2:
        computeScroll();
        displaylistcanvas.translate(-mScrollX, -mScrollY);
        mPrivateFlags = mPrivateFlags | 0x8020;
        mPrivateFlags = mPrivateFlags & 0xff9fffff;
        if((mPrivateFlags & 0x80) != 128)
            break MISSING_BLOCK_LABEL_328;
        dispatchDraw(displaylistcanvas);
        drawAutofilledHighlight(displaylistcanvas);
        if(mOverlay != null && mOverlay.isEmpty() ^ true)
            mOverlay.getOverlayView().draw(displaylistcanvas);
        if(debugDraw())
            debugDrawFocus(displaylistcanvas);
          goto _L3
        Exception exception;
        exception;
        rendernode.end(displaylistcanvas);
        setDisplayListProperties(rendernode);
        throw exception;
        draw(displaylistcanvas);
          goto _L3
        mPrivateFlags = mPrivateFlags | 0x8020;
        mPrivateFlags = mPrivateFlags & 0xff9fffff;
          goto _L4
    }

    public final void updateDragShadow(DragShadowBuilder dragshadowbuilder)
    {
        if(mAttachInfo == null)
        {
            Log.w("View", "updateDragShadow called on a detached view.");
            return;
        }
        if(mAttachInfo.mDragToken == null)
            break MISSING_BLOCK_LABEL_94;
        Canvas canvas = mAttachInfo.mDragSurface.lockCanvas(null);
        canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        dragshadowbuilder.onDrawShadow(canvas);
        mAttachInfo.mDragSurface.unlockCanvasAndPost(canvas);
_L1:
        return;
        dragshadowbuilder;
        try
        {
            mAttachInfo.mDragSurface.unlockCanvasAndPost(canvas);
            throw dragshadowbuilder;
        }
        // Misplaced declaration of an exception variable
        catch(DragShadowBuilder dragshadowbuilder)
        {
            Log.e("View", "Unable to update drag shadow", dragshadowbuilder);
        }
          goto _L1
        Log.e("View", "No active drag");
          goto _L1
    }

    boolean updateLocalSystemUiVisibility(int i, int j)
    {
        i = mSystemUiVisibility & j | i & j;
        if(i != mSystemUiVisibility)
        {
            setSystemUiVisibility(i);
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(drawable == mBackground) goto _L2; else goto _L1
_L1:
        if(mForegroundInfo == null || ForegroundInfo._2D_get1(mForegroundInfo) != drawable) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mDefaultFocusHighlight != drawable)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean willNotCacheDrawing()
    {
        boolean flag;
        if((mViewFlags & 0x20000) == 0x20000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean willNotDraw()
    {
        boolean flag;
        if((mViewFlags & 0x80) == 128)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final int ACCESSIBILITY_CURSOR_POSITION_UNDEFINED = -1;
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    static final int ACCESSIBILITY_LIVE_REGION_DEFAULT = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    static final int ALL_RTL_PROPERTIES_RESOLVED = 0x60010220;
    public static final Property ALPHA = new FloatProperty("alpha") {

        public Float get(View view)
        {
            return Float.valueOf(view.getAlpha());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setAlpha(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final int AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 1;
    private static final int AUTOFILL_HIGHLIGHT_ATTR[] = {
        0x1010568
    };
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE = "creditCardExpirationDate";
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY = "creditCardExpirationDay";
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH = "creditCardExpirationMonth";
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR = "creditCardExpirationYear";
    public static final String AUTOFILL_HINT_CREDIT_CARD_NUMBER = "creditCardNumber";
    public static final String AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE = "creditCardSecurityCode";
    public static final String AUTOFILL_HINT_EMAIL_ADDRESS = "emailAddress";
    public static final String AUTOFILL_HINT_NAME = "name";
    public static final String AUTOFILL_HINT_PASSWORD = "password";
    public static final String AUTOFILL_HINT_PHONE = "phone";
    public static final String AUTOFILL_HINT_POSTAL_ADDRESS = "postalAddress";
    public static final String AUTOFILL_HINT_POSTAL_CODE = "postalCode";
    public static final String AUTOFILL_HINT_USERNAME = "username";
    public static final int AUTOFILL_TYPE_DATE = 4;
    public static final int AUTOFILL_TYPE_LIST = 3;
    public static final int AUTOFILL_TYPE_NONE = 0;
    public static final int AUTOFILL_TYPE_TEXT = 1;
    public static final int AUTOFILL_TYPE_TOGGLE = 2;
    static final int CLICKABLE = 16384;
    static final int CONTEXT_CLICKABLE = 0x800000;
    private static final boolean DBG = false;
    static final int DEBUG_CORNERS_COLOR = Color.rgb(63, 127, 255);
    static final int DEBUG_CORNERS_SIZE_DIP = 8;
    public static boolean DEBUG_DRAW = false;
    public static final String DEBUG_LAYOUT_PROPERTY = "debug.layout";
    static final int DISABLED = 32;
    public static final int DRAG_FLAG_GLOBAL = 256;
    public static final int DRAG_FLAG_GLOBAL_PERSISTABLE_URI_PERMISSION = 64;
    public static final int DRAG_FLAG_GLOBAL_PREFIX_URI_PERMISSION = 128;
    public static final int DRAG_FLAG_GLOBAL_URI_READ = 1;
    public static final int DRAG_FLAG_GLOBAL_URI_WRITE = 2;
    public static final int DRAG_FLAG_OPAQUE = 512;
    static final int DRAG_MASK = 3;
    static final int DRAWING_CACHE_ENABLED = 32768;
    public static final int DRAWING_CACHE_QUALITY_AUTO = 0;
    private static final int DRAWING_CACHE_QUALITY_FLAGS[] = {
        0, 0x80000, 0x100000
    };
    public static final int DRAWING_CACHE_QUALITY_HIGH = 0x100000;
    public static final int DRAWING_CACHE_QUALITY_LOW = 0x80000;
    static final int DRAWING_CACHE_QUALITY_MASK = 0x180000;
    static final int DRAW_MASK = 128;
    static final int DUPLICATE_PARENT_STATE = 0x400000;
    protected static final int EMPTY_STATE_SET[] = StateSet.get(0);
    static final int ENABLED = 0;
    protected static final int ENABLED_FOCUSED_SELECTED_STATE_SET[] = StateSet.get(14);
    protected static final int ENABLED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(15);
    protected static final int ENABLED_FOCUSED_STATE_SET[] = StateSet.get(12);
    protected static final int ENABLED_FOCUSED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(13);
    static final int ENABLED_MASK = 32;
    protected static final int ENABLED_SELECTED_STATE_SET[] = StateSet.get(10);
    protected static final int ENABLED_SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(11);
    protected static final int ENABLED_STATE_SET[] = StateSet.get(8);
    protected static final int ENABLED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(9);
    static final int FADING_EDGE_HORIZONTAL = 4096;
    static final int FADING_EDGE_MASK = 12288;
    static final int FADING_EDGE_NONE = 0;
    static final int FADING_EDGE_VERTICAL = 8192;
    static final int FILTER_TOUCHES_WHEN_OBSCURED = 1024;
    public static final int FIND_VIEWS_WITH_ACCESSIBILITY_NODE_PROVIDERS = 4;
    public static final int FIND_VIEWS_WITH_CONTENT_DESCRIPTION = 2;
    public static final int FIND_VIEWS_WITH_TEXT = 1;
    private static final int FITS_SYSTEM_WINDOWS = 2;
    public static final int FOCUSABLE = 1;
    public static final int FOCUSABLES_ALL = 0;
    public static final int FOCUSABLES_TOUCH_MODE = 1;
    public static final int FOCUSABLE_AUTO = 16;
    static final int FOCUSABLE_IN_TOUCH_MODE = 0x40000;
    private static final int FOCUSABLE_MASK = 17;
    protected static final int FOCUSED_SELECTED_STATE_SET[] = StateSet.get(6);
    protected static final int FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(7);
    protected static final int FOCUSED_STATE_SET[] = StateSet.get(4);
    protected static final int FOCUSED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(5);
    public static final int FOCUS_BACKWARD = 1;
    public static final int FOCUS_DOWN = 130;
    public static final int FOCUS_FORWARD = 2;
    public static final int FOCUS_LEFT = 17;
    public static final int FOCUS_RIGHT = 66;
    public static final int FOCUS_UP = 33;
    public static final int GONE = 8;
    public static final int HAPTIC_FEEDBACK_ENABLED = 0x10000000;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    static final int IMPORTANT_FOR_ACCESSIBILITY_DEFAULT = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    public static final int IMPORTANT_FOR_AUTOFILL_AUTO = 0;
    public static final int IMPORTANT_FOR_AUTOFILL_NO = 2;
    public static final int IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS = 8;
    public static final int IMPORTANT_FOR_AUTOFILL_YES = 1;
    public static final int IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS = 4;
    public static final int INVISIBLE = 4;
    public static final int KEEP_SCREEN_ON = 0x4000000;
    public static final int LAST_APP_AUTOFILL_ID = 0x3fffffff;
    public static final int LAYER_TYPE_HARDWARE = 2;
    public static final int LAYER_TYPE_NONE = 0;
    public static final int LAYER_TYPE_SOFTWARE = 1;
    private static final int LAYOUT_DIRECTION_DEFAULT = 2;
    private static final int LAYOUT_DIRECTION_FLAGS[] = {
        0, 1, 2, 3
    };
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    static final int LAYOUT_DIRECTION_RESOLVED_DEFAULT = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    public static final int LAYOUT_DIRECTION_UNDEFINED = -1;
    static final int LONG_CLICKABLE = 0x200000;
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    public static final int MEASURED_SIZE_MASK = 0xffffff;
    public static final int MEASURED_STATE_MASK = 0xff000000;
    public static final int MEASURED_STATE_TOO_SMALL = 0x1000000;
    public static final int NAVIGATION_BAR_TRANSIENT = 0x8000000;
    public static final int NAVIGATION_BAR_TRANSLUCENT = 0x80000000;
    public static final int NAVIGATION_BAR_TRANSPARENT = 32768;
    public static final int NAVIGATION_BAR_UNHIDE = 0x20000000;
    public static final int NOT_FOCUSABLE = 0;
    public static final int NO_ID = -1;
    static final int OPTIONAL_FITS_SYSTEM_WINDOWS = 2048;
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_NEVER = 2;
    static final int PARENT_SAVE_DISABLED = 0x20000000;
    static final int PARENT_SAVE_DISABLED_MASK = 0x20000000;
    static final int PFLAG2_ACCESSIBILITY_FOCUSED = 0x4000000;
    static final int PFLAG2_ACCESSIBILITY_LIVE_REGION_MASK = 0x1800000;
    static final int PFLAG2_ACCESSIBILITY_LIVE_REGION_SHIFT = 23;
    static final int PFLAG2_DRAG_CAN_ACCEPT = 1;
    static final int PFLAG2_DRAG_HOVERED = 2;
    static final int PFLAG2_DRAWABLE_RESOLVED = 0x40000000;
    static final int PFLAG2_HAS_TRANSIENT_STATE = 0x80000000;
    static final int PFLAG2_IMPORTANT_FOR_ACCESSIBILITY_MASK = 0x700000;
    static final int PFLAG2_IMPORTANT_FOR_ACCESSIBILITY_SHIFT = 20;
    static final int PFLAG2_LAYOUT_DIRECTION_MASK = 12;
    static final int PFLAG2_LAYOUT_DIRECTION_MASK_SHIFT = 2;
    static final int PFLAG2_LAYOUT_DIRECTION_RESOLVED = 32;
    static final int PFLAG2_LAYOUT_DIRECTION_RESOLVED_MASK = 48;
    static final int PFLAG2_LAYOUT_DIRECTION_RESOLVED_RTL = 16;
    static final int PFLAG2_PADDING_RESOLVED = 0x20000000;
    static final int PFLAG2_SUBTREE_ACCESSIBILITY_STATE_CHANGED = 0x8000000;
    private static final int PFLAG2_TEXT_ALIGNMENT_FLAGS[] = {
        0, 8192, 16384, 24576, 32768, 40960, 49152
    };
    static final int PFLAG2_TEXT_ALIGNMENT_MASK = 57344;
    static final int PFLAG2_TEXT_ALIGNMENT_MASK_SHIFT = 13;
    static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED = 0x10000;
    private static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED_DEFAULT = 0x20000;
    static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED_MASK = 0xe0000;
    static final int PFLAG2_TEXT_ALIGNMENT_RESOLVED_MASK_SHIFT = 17;
    private static final int PFLAG2_TEXT_DIRECTION_FLAGS[] = {
        0, 64, 128, 192, 256, 320, 384, 448
    };
    static final int PFLAG2_TEXT_DIRECTION_MASK = 448;
    static final int PFLAG2_TEXT_DIRECTION_MASK_SHIFT = 6;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED = 512;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED_DEFAULT = 1024;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED_MASK = 7168;
    static final int PFLAG2_TEXT_DIRECTION_RESOLVED_MASK_SHIFT = 10;
    static final int PFLAG2_VIEW_QUICK_REJECTED = 0x10000000;
    static final int PFLAG3_APPLYING_INSETS = 32;
    static final int PFLAG3_ASSIST_BLOCKED = 16384;
    static final int PFLAG3_CALLED_SUPER = 16;
    private static final int PFLAG3_CLUSTER = 32768;
    private static final int PFLAG3_FINGER_DOWN = 0x20000;
    static final int PFLAG3_FITTING_SYSTEM_WINDOWS = 64;
    private static final int PFLAG3_FOCUSED_BY_DEFAULT = 0x40000;
    private static final int PFLAG3_HAS_OVERLAPPING_RENDERING_FORCED = 0x1000000;
    static final int PFLAG3_IMPORTANT_FOR_AUTOFILL_MASK = 0x780000;
    static final int PFLAG3_IMPORTANT_FOR_AUTOFILL_SHIFT = 19;
    private static final int PFLAG3_IS_AUTOFILLED = 0x10000;
    static final int PFLAG3_IS_LAID_OUT = 4;
    static final int PFLAG3_MEASURE_NEEDED_BEFORE_LAYOUT = 8;
    static final int PFLAG3_NESTED_SCROLLING_ENABLED = 128;
    static final int PFLAG3_NOTIFY_AUTOFILL_ENTER_ON_LAYOUT = 0x8000000;
    private static final int PFLAG3_NO_REVEAL_ON_FOCUS = 0x4000000;
    private static final int PFLAG3_OVERLAPPING_RENDERING_FORCED_VALUE = 0x800000;
    static final int PFLAG3_SCROLL_INDICATOR_BOTTOM = 512;
    static final int PFLAG3_SCROLL_INDICATOR_END = 8192;
    static final int PFLAG3_SCROLL_INDICATOR_LEFT = 1024;
    static final int PFLAG3_SCROLL_INDICATOR_RIGHT = 2048;
    static final int PFLAG3_SCROLL_INDICATOR_START = 4096;
    static final int PFLAG3_SCROLL_INDICATOR_TOP = 256;
    static final int PFLAG3_TEMPORARY_DETACH = 0x2000000;
    static final int PFLAG3_VIEW_IS_ANIMATING_ALPHA = 2;
    static final int PFLAG3_VIEW_IS_ANIMATING_TRANSFORM = 1;
    static final int PFLAG_ACTIVATED = 0x40000000;
    static final int PFLAG_ALPHA_SET = 0x40000;
    static final int PFLAG_ANIMATION_STARTED = 0x10000;
    private static final int PFLAG_AWAKEN_SCROLL_BARS_ON_ATTACH = 0x8000000;
    static final int PFLAG_CANCEL_NEXT_UP_EVENT = 0x4000000;
    static final int PFLAG_DIRTY = 0x200000;
    static final int PFLAG_DIRTY_MASK = 0x600000;
    static final int PFLAG_DIRTY_OPAQUE = 0x400000;
    private static final int PFLAG_DOES_NOTHING_REUSE_PLEASE = 0x20000000;
    static final int PFLAG_DRAWABLE_STATE_DIRTY = 1024;
    static final int PFLAG_DRAWING_CACHE_VALID = 32768;
    static final int PFLAG_DRAWN = 32;
    static final int PFLAG_DRAW_ANIMATION = 64;
    static final int PFLAG_FOCUSED = 2;
    static final int PFLAG_FORCE_LAYOUT = 4096;
    static final int PFLAG_HAS_BOUNDS = 16;
    private static final int PFLAG_HOVERED = 0x10000000;
    static final int PFLAG_INVALIDATED = 0x80000000;
    static final int PFLAG_IS_ROOT_NAMESPACE = 8;
    static final int PFLAG_LAYOUT_REQUIRED = 8192;
    static final int PFLAG_MEASURED_DIMENSION_SET = 2048;
    static final int PFLAG_OPAQUE_BACKGROUND = 0x800000;
    static final int PFLAG_OPAQUE_MASK = 0x1800000;
    static final int PFLAG_OPAQUE_SCROLLBARS = 0x1000000;
    private static final int PFLAG_PREPRESSED = 0x2000000;
    private static final int PFLAG_PRESSED = 16384;
    static final int PFLAG_REQUEST_TRANSPARENT_REGIONS = 512;
    private static final int PFLAG_SAVE_STATE_CALLED = 0x20000;
    static final int PFLAG_SCROLL_CONTAINER = 0x80000;
    static final int PFLAG_SCROLL_CONTAINER_ADDED = 0x100000;
    static final int PFLAG_SELECTED = 4;
    static final int PFLAG_SKIP_DRAW = 128;
    static final int PFLAG_WANTS_FOCUS = 1;
    private static final int POPULATING_ACCESSIBILITY_EVENT_TYPES = 0x2a1bf;
    protected static final int PRESSED_ENABLED_FOCUSED_SELECTED_STATE_SET[] = StateSet.get(30);
    protected static final int PRESSED_ENABLED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(31);
    protected static final int PRESSED_ENABLED_FOCUSED_STATE_SET[] = StateSet.get(28);
    protected static final int PRESSED_ENABLED_FOCUSED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(29);
    protected static final int PRESSED_ENABLED_SELECTED_STATE_SET[] = StateSet.get(26);
    protected static final int PRESSED_ENABLED_SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(27);
    protected static final int PRESSED_ENABLED_STATE_SET[] = StateSet.get(24);
    protected static final int PRESSED_ENABLED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(25);
    protected static final int PRESSED_FOCUSED_SELECTED_STATE_SET[] = StateSet.get(22);
    protected static final int PRESSED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(23);
    protected static final int PRESSED_FOCUSED_STATE_SET[] = StateSet.get(20);
    protected static final int PRESSED_FOCUSED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(21);
    protected static final int PRESSED_SELECTED_STATE_SET[] = StateSet.get(18);
    protected static final int PRESSED_SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(19);
    protected static final int PRESSED_STATE_SET[] = StateSet.get(16);
    protected static final int PRESSED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(17);
    private static final int PROVIDER_BACKGROUND = 0;
    private static final int PROVIDER_BOUNDS = 2;
    private static final int PROVIDER_NONE = 1;
    private static final int PROVIDER_PADDED_BOUNDS = 3;
    public static final int PUBLIC_STATUS_BAR_VISIBILITY_MASK = 16375;
    public static final Property ROTATION = new FloatProperty("rotation") {

        public Float get(View view)
        {
            return Float.valueOf(view.getRotation());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setRotation(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final Property ROTATION_X = new FloatProperty("rotationX") {

        public Float get(View view)
        {
            return Float.valueOf(view.getRotationX());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setRotationX(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final Property ROTATION_Y = new FloatProperty("rotationY") {

        public Float get(View view)
        {
            return Float.valueOf(view.getRotationY());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setRotationY(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    static final int SAVE_DISABLED = 0x10000;
    static final int SAVE_DISABLED_MASK = 0x10000;
    public static final Property SCALE_X = new FloatProperty("scaleX") {

        public Float get(View view)
        {
            return Float.valueOf(view.getScaleX());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setScaleX(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final Property SCALE_Y = new FloatProperty("scaleY") {

        public Float get(View view)
        {
            return Float.valueOf(view.getScaleY());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setScaleY(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final int SCREEN_STATE_OFF = 0;
    public static final int SCREEN_STATE_ON = 1;
    static final int SCROLLBARS_HORIZONTAL = 256;
    static final int SCROLLBARS_INSET_MASK = 0x1000000;
    public static final int SCROLLBARS_INSIDE_INSET = 0x1000000;
    public static final int SCROLLBARS_INSIDE_OVERLAY = 0;
    static final int SCROLLBARS_MASK = 768;
    static final int SCROLLBARS_NONE = 0;
    public static final int SCROLLBARS_OUTSIDE_INSET = 0x3000000;
    static final int SCROLLBARS_OUTSIDE_MASK = 0x2000000;
    public static final int SCROLLBARS_OUTSIDE_OVERLAY = 0x2000000;
    static final int SCROLLBARS_STYLE_MASK = 0x3000000;
    static final int SCROLLBARS_VERTICAL = 512;
    public static final int SCROLLBAR_POSITION_DEFAULT = 0;
    public static final int SCROLLBAR_POSITION_LEFT = 1;
    public static final int SCROLLBAR_POSITION_RIGHT = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    static final int SCROLL_INDICATORS_NONE = 0;
    static final int SCROLL_INDICATORS_PFLAG3_MASK = 16128;
    static final int SCROLL_INDICATORS_TO_PFLAGS3_LSHIFT = 8;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    protected static final int SELECTED_STATE_SET[] = StateSet.get(2);
    protected static final int SELECTED_WINDOW_FOCUSED_STATE_SET[] = StateSet.get(3);
    public static final int SOUND_EFFECTS_ENABLED = 0x8000000;
    public static final int STATUS_BAR_DISABLE_BACK = 0x400000;
    public static final int STATUS_BAR_DISABLE_CLOCK = 0x800000;
    public static final int STATUS_BAR_DISABLE_EXPAND = 0x10000;
    public static final int STATUS_BAR_DISABLE_HOME = 0x200000;
    public static final int STATUS_BAR_DISABLE_NOTIFICATION_ALERTS = 0x40000;
    public static final int STATUS_BAR_DISABLE_NOTIFICATION_ICONS = 0x20000;
    public static final int STATUS_BAR_DISABLE_NOTIFICATION_TICKER = 0x80000;
    public static final int STATUS_BAR_DISABLE_RECENT = 0x1000000;
    public static final int STATUS_BAR_DISABLE_SEARCH = 0x2000000;
    public static final int STATUS_BAR_DISABLE_SYSTEM_INFO = 0x100000;
    public static final int STATUS_BAR_HIDDEN = 1;
    public static final int STATUS_BAR_TRANSIENT = 0x4000000;
    public static final int STATUS_BAR_TRANSLUCENT = 0x40000000;
    public static final int STATUS_BAR_TRANSPARENT = 8;
    public static final int STATUS_BAR_UNHIDE = 0x10000000;
    public static final int STATUS_BAR_VISIBLE = 0;
    public static final int SYSTEM_UI_CLEARABLE_FLAGS = 7;
    public static final int SYSTEM_UI_FLAG_FULLSCREEN = 4;
    public static final int SYSTEM_UI_FLAG_HIDE_NAVIGATION = 2;
    public static final int SYSTEM_UI_FLAG_IMMERSIVE = 2048;
    public static final int SYSTEM_UI_FLAG_IMMERSIVE_GESTURE_ISOLATED = 32768;
    public static final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = 4096;
    public static final int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = 1024;
    public static final int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = 512;
    public static final int SYSTEM_UI_FLAG_LAYOUT_STABLE = 256;
    public static final int SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR = 16;
    public static final int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 8192;
    public static final int SYSTEM_UI_FLAG_LOW_PROFILE = 1;
    public static final int SYSTEM_UI_FLAG_VISIBLE = 0;
    public static final int SYSTEM_UI_LAYOUT_FLAGS = 1536;
    private static final int SYSTEM_UI_RESERVED_LEGACY1 = 16384;
    private static final int SYSTEM_UI_RESERVED_LEGACY2 = 0x10000;
    public static final int SYSTEM_UI_TRANSPARENT = 32776;
    public static final int TEXT_ALIGNMENT_CENTER = 4;
    private static final int TEXT_ALIGNMENT_DEFAULT = 1;
    public static final int TEXT_ALIGNMENT_GRAVITY = 1;
    public static final int TEXT_ALIGNMENT_INHERIT = 0;
    static final int TEXT_ALIGNMENT_RESOLVED_DEFAULT = 1;
    public static final int TEXT_ALIGNMENT_TEXT_END = 3;
    public static final int TEXT_ALIGNMENT_TEXT_START = 2;
    public static final int TEXT_ALIGNMENT_VIEW_END = 6;
    public static final int TEXT_ALIGNMENT_VIEW_START = 5;
    public static final int TEXT_DIRECTION_ANY_RTL = 2;
    private static final int TEXT_DIRECTION_DEFAULT = 0;
    public static final int TEXT_DIRECTION_FIRST_STRONG = 1;
    public static final int TEXT_DIRECTION_FIRST_STRONG_LTR = 6;
    public static final int TEXT_DIRECTION_FIRST_STRONG_RTL = 7;
    public static final int TEXT_DIRECTION_INHERIT = 0;
    public static final int TEXT_DIRECTION_LOCALE = 5;
    public static final int TEXT_DIRECTION_LTR = 3;
    static final int TEXT_DIRECTION_RESOLVED_DEFAULT = 1;
    public static final int TEXT_DIRECTION_RTL = 4;
    static final int TOOLTIP = 0x40000000;
    public static final Property TRANSLATION_X = new FloatProperty("translationX") {

        public Float get(View view)
        {
            return Float.valueOf(view.getTranslationX());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setTranslationX(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final Property TRANSLATION_Y = new FloatProperty("translationY") {

        public Float get(View view)
        {
            return Float.valueOf(view.getTranslationY());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setTranslationY(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final Property TRANSLATION_Z = new FloatProperty("translationZ") {

        public Float get(View view)
        {
            return Float.valueOf(view.getTranslationZ());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setTranslationZ(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    private static final int UNDEFINED_PADDING = 0x80000000;
    protected static final String VIEW_LOG_TAG = "View";
    private static final int VISIBILITY_FLAGS[] = {
        0, 4, 8
    };
    static final int VISIBILITY_MASK = 12;
    public static final int VISIBLE = 0;
    static final int WILL_NOT_CACHE_DRAWING = 0x20000;
    static final int WILL_NOT_DRAW = 128;
    protected static final int WINDOW_FOCUSED_STATE_SET[] = StateSet.get(1);
    public static final Property X = new FloatProperty("x") {

        public Float get(View view)
        {
            return Float.valueOf(view.getX());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setX(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final Property Y = new FloatProperty("y") {

        public Float get(View view)
        {
            return Float.valueOf(view.getY());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setY(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    public static final Property Z = new FloatProperty("z") {

        public Float get(View view)
        {
            return Float.valueOf(view.getZ());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, float f)
        {
            view.setZ(f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((View)obj, f);
        }

    }
;
    private static SparseArray mAttributeMap;
    public static boolean mDebugViewAttributes = false;
    private static boolean sAlwaysRemeasureExactly = false;
    private static boolean sAutoFocusableOffUIThreadWontNotifyParents;
    static boolean sCascadedDragDrop;
    private static boolean sCompatibilityDone = false;
    private static Paint sDebugPaint;
    static boolean sHasFocusableExcludeAutoFocusable;
    private static boolean sIgnoreMeasureCache = false;
    private static boolean sLayoutParamsAlwaysChanged = false;
    private static int sNextAccessibilityViewId;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    protected static boolean sPreserveMarginParamsInLayoutParamConversion;
    static boolean sTextureViewIgnoresDrawableSetters = false;
    static final ThreadLocal sThreadLocal = new ThreadLocal();
    private static boolean sUseBrokenMakeMeasureSpec = false;
    private static boolean sUseDefaultFocusHighlight;
    static boolean sUseZeroUnspecifiedMeasureSpec = false;
    private int mAccessibilityCursorPosition;
    AccessibilityDelegate mAccessibilityDelegate;
    private int mAccessibilityTraversalAfterId;
    private int mAccessibilityTraversalBeforeId;
    private int mAccessibilityViewId;
    private ViewPropertyAnimator mAnimator;
    AttachInfo mAttachInfo;
    protected WeakReference mAttachedActivity;
    public String mAttributes[];
    private String mAutofillHints[];
    private AutofillId mAutofillId;
    private int mAutofillViewId;
    private Drawable mBackground;
    private RenderNode mBackgroundRenderNode;
    private int mBackgroundResource;
    private boolean mBackgroundSizeChanged;
    private TintInfo mBackgroundTint;
    protected int mBottom;
    public boolean mCachingFailed;
    Rect mClipBounds;
    private CharSequence mContentDescription;
    protected Context mContext;
    protected Animation mCurrentAnimation;
    private Drawable mDefaultFocusHighlight;
    private Drawable mDefaultFocusHighlightCache;
    boolean mDefaultFocusHighlightEnabled;
    private boolean mDefaultFocusHighlightSizeChanged;
    private int mDrawableState[];
    private Bitmap mDrawingCache;
    private int mDrawingCacheBackgroundColor;
    protected volatile boolean mFirst;
    private ViewTreeObserver mFloatingTreeObserver;
    private ForegroundInfo mForegroundInfo;
    private ArrayList mFrameMetricsObservers;
    GhostView mGhostView;
    boolean mHapticEnabledExplicitly;
    private boolean mHasPerformedLongPress;
    int mID;
    private boolean mIgnoreNextUpEvent;
    private boolean mInContextButtonPress;
    protected final InputEventConsistencyVerifier mInputEventConsistencyVerifier;
    protected boolean mIsWebView;
    private SparseArray mKeyedTags;
    private int mLabelForId;
    private boolean mLastIsOpaque;
    Paint mLayerPaint;
    int mLayerType;
    private Insets mLayoutInsets;
    protected ViewGroup.LayoutParams mLayoutParams;
    protected int mLeft;
    private boolean mLeftPaddingDefined;
    ListenerInfo mListenerInfo;
    private float mLongClickX;
    private float mLongClickY;
    private MatchIdPredicate mMatchIdPredicate;
    private MatchLabelForPredicate mMatchLabelForPredicate;
    private LongSparseLongArray mMeasureCache;
    int mMeasuredHeight;
    int mMeasuredWidth;
    private int mMinHeight;
    private int mMinWidth;
    private ViewParent mNestedScrollingParent;
    int mNextClusterForwardId;
    private int mNextFocusDownId;
    int mNextFocusForwardId;
    private int mNextFocusLeftId;
    private int mNextFocusRightId;
    private int mNextFocusUpId;
    int mOldHeightMeasureSpec;
    int mOldWidthMeasureSpec;
    ViewOutlineProvider mOutlineProvider;
    private int mOverScrollMode;
    ViewOverlay mOverlay;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;
    protected ViewParent mParent;
    private CheckForLongPress mPendingCheckForLongPress;
    private CheckForTap mPendingCheckForTap;
    private PerformClick mPerformClick;
    private PointerIcon mPointerIcon;
    public int mPrivateFlags;
    int mPrivateFlags2;
    int mPrivateFlags3;
    boolean mRecreateDisplayList;
    final RenderNode mRenderNode;
    private final Resources mResources;
    protected int mRight;
    private boolean mRightPaddingDefined;
    private RoundScrollbarRenderer mRoundScrollbarRenderer;
    private HandlerActionQueue mRunQueue;
    private ScrollabilityCache mScrollCache;
    private Drawable mScrollIndicatorDrawable;
    protected int mScrollX;
    protected int mScrollY;
    private SendViewScrolledAccessibilityEvent mSendViewScrolledAccessibilityEvent;
    private boolean mSendingHoverAccessibilityEvents;
    String mStartActivityRequestWho;
    private StateListAnimator mStateListAnimator;
    int mSystemUiVisibility;
    protected Object mTag;
    private int mTempNestedScrollConsumed[];
    TooltipInfo mTooltipInfo;
    protected int mTop;
    private TouchDelegate mTouchDelegate;
    private int mTouchSlop;
    public TransformationInfo mTransformationInfo;
    int mTransientStateCount;
    private String mTransitionName;
    private Bitmap mUnscaledDrawingCache;
    private UnsetPressedState mUnsetPressedState;
    protected int mUserPaddingBottom;
    int mUserPaddingEnd;
    protected int mUserPaddingLeft;
    int mUserPaddingLeftInitial;
    protected int mUserPaddingRight;
    int mUserPaddingRightInitial;
    int mUserPaddingStart;
    private float mVerticalScrollFactor;
    private int mVerticalScrollbarPosition;
    int mViewFlags;
    private Handler mVisibilityChangeForAutofillHandler;
    int mWindowAttachCount;

    static 
    {
        DEBUG_DRAW = false;
    }
}
