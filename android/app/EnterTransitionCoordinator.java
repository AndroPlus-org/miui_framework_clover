// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.animation.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.transition.*;
import android.util.ArrayMap;
import android.view.*;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            ActivityTransitionCoordinator, Activity, SharedElementCallback

class EnterTransitionCoordinator extends ActivityTransitionCoordinator
{

    static boolean _2D_get0(EnterTransitionCoordinator entertransitioncoordinator)
    {
        return entertransitioncoordinator.mIsReadyForTransition;
    }

    static Transition _2D_set0(EnterTransitionCoordinator entertransitioncoordinator, Transition transition)
    {
        entertransitioncoordinator.mEnterViewsTransition = transition;
        return transition;
    }

    static Transition _2D_wrap0(EnterTransitionCoordinator entertransitioncoordinator, ViewGroup viewgroup, boolean flag, boolean flag1)
    {
        return entertransitioncoordinator.beginTransition(viewgroup, flag, flag1);
    }

    static void _2D_wrap1(EnterTransitionCoordinator entertransitioncoordinator)
    {
        entertransitioncoordinator.makeOpaque();
    }

    static void _2D_wrap2(EnterTransitionCoordinator entertransitioncoordinator)
    {
        entertransitioncoordinator.sharedElementTransitionStarted();
    }

    static void _2D_wrap3(EnterTransitionCoordinator entertransitioncoordinator, Transition transition)
    {
        entertransitioncoordinator.startEnterTransition(transition);
    }

    static void _2D_wrap4(EnterTransitionCoordinator entertransitioncoordinator, Bundle bundle)
    {
        entertransitioncoordinator.startSharedElementTransition(bundle);
    }

    public EnterTransitionCoordinator(final Activity viewTreeObserver, final ResultReceiver decorView, ArrayList arraylist, boolean flag, boolean flag1)
    {
        Window window = viewTreeObserver.getWindow();
        boolean flag2;
        if(flag)
            flag2 = flag1 ^ true;
        else
            flag2 = false;
        super(window, arraylist, getListener(viewTreeObserver, flag2), flag);
        mActivity = viewTreeObserver;
        mIsCrossTask = flag1;
        setResultReceiver(decorView);
        prepareEnter();
        viewTreeObserver = new Bundle();
        viewTreeObserver.putParcelable("android:remoteReceiver", this);
        mResultReceiver.send(100, viewTreeObserver);
        decorView = getDecor();
        if(decorView != null)
        {
            viewTreeObserver = decorView.getViewTreeObserver();
            viewTreeObserver.addOnPreDrawListener(new android.view.ViewTreeObserver.OnPreDrawListener() {

                public boolean onPreDraw()
                {
                    if(EnterTransitionCoordinator._2D_get0(EnterTransitionCoordinator.this))
                        if(viewTreeObserver.isAlive())
                            viewTreeObserver.removeOnPreDrawListener(this);
                        else
                            decorView.getViewTreeObserver().removeOnPreDrawListener(this);
                    return false;
                }

                final EnterTransitionCoordinator this$0;
                final View val$decorView;
                final ViewTreeObserver val$viewTreeObserver;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                viewTreeObserver = viewtreeobserver;
                decorView = view;
                super();
            }
            }
);
        }
    }

    private boolean allowOverlappingTransitions()
    {
        boolean flag;
        if(mIsReturning)
            flag = getWindow().getAllowReturnTransitionOverlap();
        else
            flag = getWindow().getAllowEnterTransitionOverlap();
        return flag;
    }

    private Transition beginTransition(ViewGroup viewgroup, boolean flag, boolean flag1)
    {
        Transition transition = null;
        Transition transition1 = null;
        if(flag1)
        {
            if(!mSharedElementNames.isEmpty())
                transition1 = configureTransition(getSharedElementTransition(), false);
            Object obj;
            if(transition1 == null)
            {
                sharedElementTransitionStarted();
                sharedElementTransitionComplete();
                transition = transition1;
            } else
            {
                transition1.addListener(new TransitionListenerAdapter() {

                    public void onTransitionEnd(Transition transition2)
                    {
                        transition2.removeListener(this);
                        sharedElementTransitionComplete();
                    }

                    public void onTransitionStart(Transition transition2)
                    {
                        EnterTransitionCoordinator._2D_wrap2(EnterTransitionCoordinator.this);
                    }

                    final EnterTransitionCoordinator this$0;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                super();
            }
                }
);
                transition = transition1;
            }
        }
        transition1 = null;
        obj = null;
        if(flag)
        {
            mIsViewsTransitionStarted = true;
            transition1 = obj;
            if(mTransitioningViews != null)
            {
                transition1 = obj;
                if(mTransitioningViews.isEmpty() ^ true)
                    transition1 = configureTransition(getViewsTransition(), true);
            }
            if(transition1 == null)
                viewsTransitionComplete();
            else
                transition1.addListener(new ActivityTransitionCoordinator.ContinueTransitionListener(mTransitioningViews) {

                    public void onTransitionEnd(Transition transition2)
                    {
                        EnterTransitionCoordinator._2D_set0(EnterTransitionCoordinator.this, null);
                        transition2.removeListener(this);
                        viewsTransitionComplete();
                        super.onTransitionEnd(transition2);
                    }

                    public void onTransitionStart(Transition transition2)
                    {
                        EnterTransitionCoordinator._2D_set0(EnterTransitionCoordinator.this, transition2);
                        if(transitioningViews != null)
                            showViews(transitioningViews, false);
                        super.onTransitionStart(transition2);
                    }

                    final EnterTransitionCoordinator this$0;
                    final ArrayList val$transitioningViews;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                transitioningViews = arraylist;
                super(final_activitytransitioncoordinator);
            }
                }
);
        }
        transition1 = mergeTransitions(transition, transition1);
        if(transition1 != null)
        {
            transition1.addListener(new ActivityTransitionCoordinator.ContinueTransitionListener(this));
            if(flag)
                setTransitioningViewsVisiblity(4, false);
            TransitionManager.beginDelayedTransition(viewgroup, transition1);
            if(flag)
                setTransitioningViewsVisiblity(0, false);
            viewgroup.invalidate();
        } else
        {
            transitionStarted();
        }
        return transition1;
    }

    private void cancel()
    {
        if(mIsCanceled) goto _L2; else goto _L1
_L1:
        mIsCanceled = true;
        if(getViewsTransition() != null && !mIsViewsTransitionStarted) goto _L4; else goto _L3
_L3:
        showViews(mSharedElements, true);
_L6:
        moveSharedElementsFromOverlay();
        mSharedElementNames.clear();
        mSharedElements.clear();
        mAllSharedElementNames.clear();
        startSharedElementTransition(null);
        onRemoteExitTransitionComplete();
_L2:
        return;
_L4:
        if(mTransitioningViews != null)
            mTransitioningViews.addAll(mSharedElements);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static SharedElementCallback getListener(Activity activity, boolean flag)
    {
        if(flag)
            activity = activity.mExitTransitionListener;
        else
            activity = activity.mEnterTransitionListener;
        return activity;
    }

    private void makeOpaque()
    {
        if(!mHasStopped && mActivity != null)
        {
            if(mWasOpaque)
                mActivity.convertFromTranslucent();
            mActivity = null;
        }
    }

    private ArrayMap mapNamedElements(ArrayList arraylist, ArrayList arraylist1)
    {
        ArrayMap arraymap = new ArrayMap();
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
            viewgroup.findNamedViews(arraymap);
        if(arraylist != null)
        {
            for(int i = 0; i < arraylist1.size(); i++)
            {
                Object obj = (String)arraylist1.get(i);
                String s = (String)arraylist.get(i);
                if(obj == null || !(((String) (obj)).equals(s) ^ true))
                    continue;
                obj = (View)arraymap.get(obj);
                if(obj != null)
                    arraymap.put(s, obj);
            }

        }
        return arraymap;
    }

    private void onTakeSharedElements()
    {
        if(!mIsReadyForTransition || mSharedElementsBundle == null)
            return;
        final Bundle sharedElementState = mSharedElementsBundle;
        mSharedElementsBundle = null;
        sharedElementState = new SharedElementCallback.OnSharedElementsReadyListener() {

            void lambda$_2D_android_app_EnterTransitionCoordinator$3_17761(Bundle bundle)
            {
                startTransition(new _.Lambda.Pcw_0289sroTvc5U7X_pS90OouM((byte)2, this, bundle));
            }

            void lambda$_2D_android_app_EnterTransitionCoordinator$3_17809(Bundle bundle)
            {
                EnterTransitionCoordinator._2D_wrap4(EnterTransitionCoordinator.this, bundle);
            }

            public void onSharedElementsReady()
            {
                ViewGroup viewgroup = getDecor();
                if(viewgroup != null)
                {
                    OneShotPreDrawListener.add(viewgroup, false, new _.Lambda.Pcw_0289sroTvc5U7X_pS90OouM((byte)3, this, sharedElementState));
                    viewgroup.invalidate();
                }
            }

            final EnterTransitionCoordinator this$0;
            final Bundle val$sharedElementState;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                sharedElementState = bundle;
                super();
            }
        }
;
        if(mListener == null)
            sharedElementState.onSharedElementsReady();
        else
            mListener.onSharedElementsArrived(mSharedElementNames, mSharedElements, sharedElementState);
    }

    private static void removeNullViews(ArrayList arraylist)
    {
        if(arraylist != null)
        {
            for(int i = arraylist.size() - 1; i >= 0; i--)
                if(arraylist.get(i) == null)
                    arraylist.remove(i);

        }
    }

    private void requestLayoutForSharedElements()
    {
        int i = mSharedElements.size();
        for(int j = 0; j < i; j++)
            ((View)mSharedElements.get(j)).requestLayout();

    }

    private void sendSharedElementDestination()
    {
        Object obj;
        boolean flag;
        obj = getDecor();
        if(allowOverlappingTransitions() && getEnterViewsTransition() != null)
        {
            flag = false;
        } else
        {
label0:
            {
                if(obj != null)
                    break label0;
                flag = true;
            }
        }
_L2:
        boolean flag1;
        int i;
        if(flag)
        {
            obj = captureSharedElementState();
            moveSharedElementsToOverlay();
            mResultReceiver.send(107, ((Bundle) (obj)));
        } else
        if(obj != null)
            OneShotPreDrawListener.add(((View) (obj)), new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)1, this));
        if(allowOverlappingTransitions())
            startEnterTransitionOnly();
        return;
        flag1 = ((View) (obj)).isLayoutRequested() ^ true;
        flag = flag1;
        if(!flag1) goto _L2; else goto _L1
_L1:
        i = 0;
_L3:
        flag = flag1;
        if(i < mSharedElements.size())
        {
label1:
            {
                if(!((View)mSharedElements.get(i)).isLayoutRequested())
                    break label1;
                flag = false;
            }
        }
          goto _L2
        i++;
          goto _L3
    }

    private void sharedElementTransitionStarted()
    {
        mSharedElementTransitionStarted = true;
        if(mIsExitTransitionComplete)
            send(104, null);
    }

    private void startEnterTransition(Transition transition)
    {
        Object obj = getDecor();
        if(!mIsReturning && obj != null)
        {
            obj = ((ViewGroup) (obj)).getBackground();
            if(obj != null)
            {
                transition = ((Drawable) (obj)).mutate();
                getWindow().setBackgroundDrawable(transition);
                mBackgroundAnimator = ObjectAnimator.ofInt(transition, "alpha", new int[] {
                    255
                });
                mBackgroundAnimator.setDuration(getFadeDuration());
                mBackgroundAnimator.addListener(new AnimatorListenerAdapter() {

                    public void onAnimationEnd(Animator animator)
                    {
                        EnterTransitionCoordinator._2D_wrap1(EnterTransitionCoordinator.this);
                        backgroundAnimatorComplete();
                    }

                    final EnterTransitionCoordinator this$0;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                super();
            }
                }
);
                mBackgroundAnimator.start();
            } else
            if(transition != null)
            {
                transition.addListener(new TransitionListenerAdapter() {

                    public void onTransitionEnd(Transition transition1)
                    {
                        transition1.removeListener(this);
                        EnterTransitionCoordinator._2D_wrap1(EnterTransitionCoordinator.this);
                    }

                    final EnterTransitionCoordinator this$0;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                super();
            }
                }
);
                backgroundAnimatorComplete();
            } else
            {
                makeOpaque();
                backgroundAnimatorComplete();
            }
        } else
        {
            backgroundAnimatorComplete();
        }
    }

    private void startEnterTransitionOnly()
    {
        startTransition(new Runnable() {

            public void run()
            {
                Object obj = getDecor();
                if(obj != null)
                {
                    obj = EnterTransitionCoordinator._2D_wrap0(EnterTransitionCoordinator.this, ((ViewGroup) (obj)), true, false);
                    EnterTransitionCoordinator._2D_wrap3(EnterTransitionCoordinator.this, ((Transition) (obj)));
                }
            }

            final EnterTransitionCoordinator this$0;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                super();
            }
        }
);
    }

    private void startRejectedAnimations(final ArrayList rejectedSnapshots)
    {
        if(rejectedSnapshots == null || rejectedSnapshots.isEmpty())
            return;
        final ViewGroup decorView = getDecor();
        if(decorView != null)
        {
            ViewGroupOverlay viewgroupoverlay = decorView.getOverlay();
            Object obj = null;
            int i = rejectedSnapshots.size();
            for(int j = 0; j < i; j++)
            {
                obj = (View)rejectedSnapshots.get(j);
                viewgroupoverlay.add(((View) (obj)));
                obj = ObjectAnimator.ofFloat(obj, View.ALPHA, new float[] {
                    1.0F, 0.0F
                });
                ((ObjectAnimator) (obj)).start();
            }

            ((ObjectAnimator) (obj)).addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    animator = decorView.getOverlay();
                    int k = rejectedSnapshots.size();
                    for(int l = 0; l < k; l++)
                        animator.remove((View)rejectedSnapshots.get(l));

                }

                final EnterTransitionCoordinator this$0;
                final ViewGroup val$decorView;
                final ArrayList val$rejectedSnapshots;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                decorView = viewgroup;
                rejectedSnapshots = arraylist;
                super();
            }
            }
);
        }
    }

    private void startSharedElementTransition(Bundle bundle)
    {
        ViewGroup viewgroup = getDecor();
        if(viewgroup == null)
            return;
        Object obj = new ArrayList(mAllSharedElementNames);
        ((ArrayList) (obj)).removeAll(mSharedElementNames);
        obj = createSnapshots(bundle, ((java.util.Collection) (obj)));
        if(mListener != null)
            mListener.onRejectSharedElements(((java.util.List) (obj)));
        removeNullViews(((ArrayList) (obj)));
        startRejectedAnimations(((ArrayList) (obj)));
        obj = createSnapshots(bundle, mSharedElementNames);
        showViews(mSharedElements, true);
        scheduleSetSharedElementEnd(((ArrayList) (obj)));
        bundle = setSharedElementState(bundle, ((ArrayList) (obj)));
        requestLayoutForSharedElements();
        boolean flag;
        if(allowOverlappingTransitions())
            flag = mIsReturning ^ true;
        else
            flag = false;
        setGhostVisibility(4);
        scheduleGhostVisibilityChange(4);
        pauseInput();
        obj = beginTransition(viewgroup, flag, true);
        scheduleGhostVisibilityChange(0);
        setGhostVisibility(0);
        if(flag)
            startEnterTransition(((Transition) (obj)));
        setOriginalSharedElementState(mSharedElements, bundle);
        if(mResultReceiver != null)
            viewgroup.postOnAnimation(new Runnable() {

                public void run()
                {
                    int i;
                    i = mAnimations;
                    mAnimations = i + 1;
                    if(i >= 2) goto _L2; else goto _L1
_L1:
                    ViewGroup viewgroup1 = getDecor();
                    if(viewgroup1 != null)
                        viewgroup1.postOnAnimation(this);
_L4:
                    return;
_L2:
                    if(mResultReceiver != null)
                    {
                        mResultReceiver.send(101, null);
                        mResultReceiver = null;
                    }
                    if(true) goto _L4; else goto _L3
_L3:
                }

                int mAnimations;
                final EnterTransitionCoordinator this$0;

            
            {
                this$0 = EnterTransitionCoordinator.this;
                super();
            }
            }
);
    }

    private void triggerViewsReady(ArrayMap arraymap)
    {
        if(mAreViewsReady)
            return;
        mAreViewsReady = true;
        ViewGroup viewgroup = getDecor();
        if(viewgroup == null || viewgroup.isAttachedToWindow() && (arraymap.isEmpty() || ((View)arraymap.valueAt(0)).isLayoutRequested() ^ true))
        {
            viewsReady(arraymap);
        } else
        {
            mViewsReadyListener = OneShotPreDrawListener.add(viewgroup, new _.Lambda.Pcw_0289sroTvc5U7X_pS90OouM((byte)4, this, arraymap));
            viewgroup.invalidate();
        }
    }

    public boolean cancelEnter()
    {
        setGhostVisibility(4);
        mHasStopped = true;
        mIsCanceled = true;
        clearState();
        return super.cancelPendingTransitions();
    }

    protected void clearState()
    {
        mSharedElementsBundle = null;
        mEnterViewsTransition = null;
        mResultReceiver = null;
        if(mBackgroundAnimator != null)
        {
            mBackgroundAnimator.cancel();
            mBackgroundAnimator = null;
        }
        super.clearState();
    }

    public void forceViewsToAppear()
    {
        if(!mIsReturning)
            return;
        if(!mIsReadyForTransition)
        {
            mIsReadyForTransition = true;
            if(getDecor() != null && mViewsReadyListener != null)
            {
                mViewsReadyListener.removeListener();
                mViewsReadyListener = null;
            }
            showViews(mTransitioningViews, true);
            setTransitioningViewsVisiblity(0, true);
            mSharedElements.clear();
            mAllSharedElementNames.clear();
            mTransitioningViews.clear();
            mIsReadyForTransition = true;
            viewsTransitionComplete();
            sharedElementTransitionComplete();
        } else
        {
            if(!mSharedElementTransitionStarted)
            {
                moveSharedElementsFromOverlay();
                mSharedElementTransitionStarted = true;
                showViews(mSharedElements, true);
                mSharedElements.clear();
                sharedElementTransitionComplete();
            }
            if(!mIsViewsTransitionStarted)
            {
                mIsViewsTransitionStarted = true;
                showViews(mTransitioningViews, true);
                setTransitioningViewsVisiblity(0, true);
                mTransitioningViews.clear();
                viewsTransitionComplete();
            }
            cancelPendingTransitions();
        }
        mAreViewsReady = true;
        if(mResultReceiver != null)
        {
            mResultReceiver.send(106, null);
            mResultReceiver = null;
        }
    }

    public Transition getEnterViewsTransition()
    {
        return mEnterViewsTransition;
    }

    protected Transition getSharedElementTransition()
    {
        Window window = getWindow();
        if(window == null)
            return null;
        if(mIsReturning)
            return window.getSharedElementReenterTransition();
        else
            return window.getSharedElementEnterTransition();
    }

    protected Transition getViewsTransition()
    {
        Window window = getWindow();
        if(window == null)
            return null;
        if(mIsReturning)
            return window.getReenterTransition();
        else
            return window.getEnterTransition();
    }

    boolean isCrossTask()
    {
        return mIsCrossTask;
    }

    public boolean isReturning()
    {
        return mIsReturning;
    }

    public boolean isWaitingForRemoteExit()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mIsReturning)
        {
            flag1 = flag;
            if(mResultReceiver != null)
                flag1 = true;
        }
        return flag1;
    }

    void lambda$_2D_android_app_EnterTransitionCoordinator_6436(ArrayMap arraymap)
    {
        mViewsReadyListener = null;
        viewsReady(arraymap);
    }

    void lambda$_2D_android_app_EnterTransitionCoordinator_8442()
    {
        if(mResultReceiver != null)
        {
            Bundle bundle = captureSharedElementState();
            moveSharedElementsToOverlay();
            mResultReceiver.send(107, bundle);
        }
    }

    public void namedViewsReady(ArrayList arraylist, ArrayList arraylist1)
    {
        triggerViewsReady(mapNamedElements(arraylist, arraylist1));
    }

    protected void onReceiveResult(int i, Bundle bundle)
    {
        i;
        JVM INSTR tableswitch 103 106: default 32
    //                   103 33
    //                   104 52
    //                   105 32
    //                   106 78;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return;
_L2:
        if(!mIsCanceled)
        {
            mSharedElementsBundle = bundle;
            onTakeSharedElements();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!mIsCanceled)
        {
            mIsExitTransitionComplete = true;
            if(mSharedElementTransitionStarted)
                onRemoteExitTransitionComplete();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        cancel();
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onRemoteExitTransitionComplete()
    {
        if(!allowOverlappingTransitions())
            startEnterTransitionOnly();
    }

    protected void onTransitionsComplete()
    {
        moveSharedElementsFromOverlay();
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
        {
            viewgroup.sendAccessibilityEvent(2048);
            Window window = getWindow();
            if(window != null && mReplacedBackground == viewgroup.getBackground())
                window.setBackgroundDrawable(null);
        }
    }

    protected void prepareEnter()
    {
        Object obj = getDecor();
        if(mActivity == null || obj == null)
            return;
        if(!isCrossTask())
            mActivity.overridePendingTransition(0, 0);
        if(!mIsReturning)
        {
            mWasOpaque = mActivity.convertToTranslucent(null, null);
            obj = ((ViewGroup) (obj)).getBackground();
            if(obj == null)
            {
                obj = new ColorDrawable(0);
                mReplacedBackground = ((Drawable) (obj));
            } else
            {
                getWindow().setBackgroundDrawable(null);
                obj = ((Drawable) (obj)).mutate();
                ((Drawable) (obj)).setAlpha(0);
            }
            getWindow().setBackgroundDrawable(((Drawable) (obj)));
        } else
        {
            mActivity = null;
        }
    }

    public void stop()
    {
        if(mBackgroundAnimator == null) goto _L2; else goto _L1
_L1:
        mBackgroundAnimator.end();
        mBackgroundAnimator = null;
_L4:
        makeOpaque();
        mIsCanceled = true;
        mResultReceiver = null;
        mActivity = null;
        moveSharedElementsFromOverlay();
        if(mTransitioningViews != null)
        {
            showViews(mTransitioningViews, true);
            setTransitioningViewsVisiblity(0, true);
        }
        showViews(mSharedElements, true);
        clearState();
        return;
_L2:
        if(mWasOpaque)
        {
            Object obj = getDecor();
            if(obj != null)
            {
                obj = ((ViewGroup) (obj)).getBackground();
                if(obj != null)
                    ((Drawable) (obj)).setAlpha(1);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void viewInstancesReady(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2)
    {
        boolean flag = false;
        int i = 0;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(i < arraylist2.size())
                {
                    View view = (View)arraylist2.get(i);
                    if(TextUtils.equals(view.getTransitionName(), (CharSequence)arraylist1.get(i)) && !(view.isAttachedToWindow() ^ true))
                        break label0;
                    flag1 = true;
                }
                if(flag1)
                    triggerViewsReady(mapNamedElements(arraylist, arraylist1));
                else
                    triggerViewsReady(mapSharedElements(arraylist, arraylist2));
                return;
            }
            i++;
        } while(true);
    }

    protected void viewsReady(ArrayMap arraymap)
    {
        super.viewsReady(arraymap);
        mIsReadyForTransition = true;
        hideViews(mSharedElements);
        arraymap = getViewsTransition();
        if(arraymap != null && mTransitioningViews != null)
        {
            removeExcludedViews(arraymap, mTransitioningViews);
            stripOffscreenViews();
            hideViews(mTransitioningViews);
        }
        if(mIsReturning)
            sendSharedElementDestination();
        else
            moveSharedElementsToOverlay();
        if(mSharedElementsBundle != null)
            onTakeSharedElements();
    }

    private static final int MIN_ANIMATION_FRAMES = 2;
    private static final String TAG = "EnterTransitionCoordinator";
    private Activity mActivity;
    private boolean mAreViewsReady;
    private ObjectAnimator mBackgroundAnimator;
    private Transition mEnterViewsTransition;
    private boolean mHasStopped;
    private boolean mIsCanceled;
    private final boolean mIsCrossTask;
    private boolean mIsExitTransitionComplete;
    private boolean mIsReadyForTransition;
    private boolean mIsViewsTransitionStarted;
    private Drawable mReplacedBackground;
    private boolean mSharedElementTransitionStarted;
    private Bundle mSharedElementsBundle;
    private OneShotPreDrawListener mViewsReadyListener;
    private boolean mWasOpaque;
}
