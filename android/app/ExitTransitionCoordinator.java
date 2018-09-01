// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.animation.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.transition.*;
import android.view.*;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            ActivityTransitionCoordinator, Activity, ActivityTransitionState, SharedElementCallback, 
//            ActivityOptions

class ExitTransitionCoordinator extends ActivityTransitionCoordinator
{
    static interface HideSharedElementsCallback
    {

        public abstract void hideSharedElements();
    }


    static Activity _2D_get0(ExitTransitionCoordinator exittransitioncoordinator)
    {
        return exittransitioncoordinator.mActivity;
    }

    static boolean _2D_get1(ExitTransitionCoordinator exittransitioncoordinator)
    {
        return exittransitioncoordinator.mIsCanceled;
    }

    static boolean _2D_get2(ExitTransitionCoordinator exittransitioncoordinator)
    {
        return exittransitioncoordinator.mIsHidden;
    }

    static Bundle _2D_get3(ExitTransitionCoordinator exittransitioncoordinator)
    {
        return exittransitioncoordinator.mSharedElementBundle;
    }

    static ObjectAnimator _2D_set0(ExitTransitionCoordinator exittransitioncoordinator, ObjectAnimator objectanimator)
    {
        exittransitioncoordinator.mBackgroundAnimator = objectanimator;
        return objectanimator;
    }

    static boolean _2D_set1(ExitTransitionCoordinator exittransitioncoordinator, boolean flag)
    {
        exittransitioncoordinator.mIsBackgroundReady = flag;
        return flag;
    }

    static boolean _2D_set2(ExitTransitionCoordinator exittransitioncoordinator, boolean flag)
    {
        exittransitioncoordinator.mIsCanceled = flag;
        return flag;
    }

    static void _2D_wrap0(ExitTransitionCoordinator exittransitioncoordinator)
    {
        exittransitioncoordinator.beginTransitions();
    }

    static void _2D_wrap1(ExitTransitionCoordinator exittransitioncoordinator)
    {
        exittransitioncoordinator.delayCancel();
    }

    static void _2D_wrap2(ExitTransitionCoordinator exittransitioncoordinator)
    {
        exittransitioncoordinator.fadeOutBackground();
    }

    static void _2D_wrap3(ExitTransitionCoordinator exittransitioncoordinator)
    {
        exittransitioncoordinator.finish();
    }

    static void _2D_wrap4(ExitTransitionCoordinator exittransitioncoordinator)
    {
        exittransitioncoordinator.notifyExitComplete();
    }

    static void _2D_wrap5(ExitTransitionCoordinator exittransitioncoordinator)
    {
        exittransitioncoordinator.startExitTransition();
    }

    static void _2D_wrap6(ExitTransitionCoordinator exittransitioncoordinator, ViewGroup viewgroup)
    {
        exittransitioncoordinator.startSharedElementExit(viewgroup);
    }

    public ExitTransitionCoordinator(Activity activity, Window window, SharedElementCallback sharedelementcallback, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, boolean flag)
    {
        super(window, arraylist, sharedelementcallback, flag);
        viewsReady(mapSharedElements(arraylist1, arraylist2));
        stripOffscreenViews();
        mIsBackgroundReady = flag ^ true;
        mActivity = activity;
    }

    private void beginTransitions()
    {
        Transition transition = getSharedElementExitTransition();
        Transition transition1 = getExitTransition();
        transition = mergeTransitions(transition, transition1);
        ViewGroup viewgroup = getDecor();
        if(transition != null && viewgroup != null)
        {
            setGhostVisibility(4);
            scheduleGhostVisibilityChange(4);
            if(transition1 != null)
                setTransitioningViewsVisiblity(0, false);
            TransitionManager.beginDelayedTransition(viewgroup, transition);
            scheduleGhostVisibilityChange(0);
            setGhostVisibility(0);
            if(transition1 != null)
                setTransitioningViewsVisiblity(4, false);
            viewgroup.invalidate();
        } else
        {
            transitionStarted();
        }
    }

    private Bundle captureExitSharedElementsState()
    {
        Bundle bundle = new Bundle();
        RectF rectf = new RectF();
        Matrix matrix = new Matrix();
        int i = 0;
        while(i < mSharedElements.size()) 
        {
            String s = (String)mSharedElementNames.get(i);
            Bundle bundle1 = mExitSharedElementBundle.getBundle(s);
            if(bundle1 != null)
                bundle.putBundle(s, bundle1);
            else
                captureSharedElementState((View)mSharedElements.get(i), s, bundle, matrix, rectf);
            i++;
        }
        return bundle;
    }

    private void delayCancel()
    {
        if(mHandler != null)
            mHandler.sendEmptyMessageDelayed(106, 1000L);
    }

    private void fadeOutBackground()
    {
        if(mBackgroundAnimator != null) goto _L2; else goto _L1
_L1:
        Object obj = getDecor();
        if(obj == null) goto _L4; else goto _L3
_L3:
        obj = ((ViewGroup) (obj)).getBackground();
        if(obj == null) goto _L4; else goto _L5
_L5:
        obj = ((Drawable) (obj)).mutate();
        getWindow().setBackgroundDrawable(((Drawable) (obj)));
        mBackgroundAnimator = ObjectAnimator.ofInt(obj, "alpha", new int[] {
            0
        });
        mBackgroundAnimator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                ExitTransitionCoordinator._2D_set0(ExitTransitionCoordinator.this, null);
                if(!ExitTransitionCoordinator._2D_get1(ExitTransitionCoordinator.this))
                {
                    ExitTransitionCoordinator._2D_set1(ExitTransitionCoordinator.this, true);
                    notifyComplete();
                }
                backgroundAnimatorComplete();
            }

            final ExitTransitionCoordinator this$0;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                super();
            }
        }
);
        mBackgroundAnimator.setDuration(getFadeDuration());
        mBackgroundAnimator.start();
_L2:
        return;
_L4:
        backgroundAnimatorComplete();
        mIsBackgroundReady = true;
        if(true) goto _L2; else goto _L6
_L6:
    }

    private void finish()
    {
        stopCancel();
        if(mActivity != null)
        {
            mActivity.mActivityTransitionState.clear();
            mActivity.finish();
            mActivity.overridePendingTransition(0, 0);
            mActivity = null;
        }
        clearState();
    }

    private void finishIfNecessary()
    {
        if(mIsReturning && mExitNotified && mActivity != null && (mSharedElements.isEmpty() || mSharedElementsHidden))
            finish();
        if(!mIsReturning && mExitNotified)
            mActivity = null;
    }

    private Transition getExitTransition()
    {
        Object obj = null;
        Transition transition = obj;
        if(mTransitioningViews != null)
        {
            transition = obj;
            if(mTransitioningViews.isEmpty() ^ true)
            {
                transition = configureTransition(getViewsTransition(), true);
                removeExcludedViews(transition, mTransitioningViews);
                if(mTransitioningViews.isEmpty())
                    transition = null;
            }
        }
        if(transition == null)
            viewsTransitionComplete();
        else
            transition.addListener(new ActivityTransitionCoordinator.ContinueTransitionListener(mTransitioningViews) {

                public void onTransitionEnd(Transition transition1)
                {
                    viewsTransitionComplete();
                    if(ExitTransitionCoordinator._2D_get2(ExitTransitionCoordinator.this) && transitioningViews != null)
                    {
                        showViews(transitioningViews, true);
                        setTransitioningViewsVisiblity(0, true);
                    }
                    if(ExitTransitionCoordinator._2D_get3(ExitTransitionCoordinator.this) != null)
                        ExitTransitionCoordinator._2D_wrap1(ExitTransitionCoordinator.this);
                    super.onTransitionEnd(transition1);
                }

                final ExitTransitionCoordinator this$0;
                final ArrayList val$transitioningViews;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                transitioningViews = arraylist;
                super(final_activitytransitioncoordinator);
            }
            }
);
        return transition;
    }

    private Transition getSharedElementExitTransition()
    {
        Transition transition = null;
        if(!mSharedElements.isEmpty())
            transition = configureTransition(getSharedElementTransition(), false);
        if(transition == null)
        {
            sharedElementTransitionComplete();
        } else
        {
            transition.addListener(new ActivityTransitionCoordinator.ContinueTransitionListener(this) {

                public void onTransitionEnd(Transition transition1)
                {
                    sharedElementTransitionComplete();
                    if(ExitTransitionCoordinator._2D_get2(ExitTransitionCoordinator.this))
                        showViews(mSharedElements, true);
                    super.onTransitionEnd(transition1);
                }

                final ExitTransitionCoordinator this$0;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                super(activitytransitioncoordinator);
            }
            }
);
            ((View)mSharedElements.get(0)).invalidate();
        }
        return transition;
    }

    private void hideSharedElements()
    {
        moveSharedElementsFromOverlay();
        if(mHideSharedElementsCallback != null)
            mHideSharedElementsCallback.hideSharedElements();
        if(!mIsHidden)
            hideViews(mSharedElements);
        mSharedElementsHidden = true;
        finishIfNecessary();
    }

    private void notifyExitComplete()
    {
        if(!mExitNotified && isViewsTransitionComplete())
        {
            mExitNotified = true;
            mResultReceiver.send(104, null);
            mResultReceiver = null;
            ViewGroup viewgroup = getDecor();
            if(!mIsReturning && viewgroup != null)
                viewgroup.suppressLayout(false);
            finishIfNecessary();
        }
    }

    private void sharedElementExitBack()
    {
        final ViewGroup decorView = getDecor();
        if(decorView != null)
            decorView.suppressLayout(true);
        if(decorView != null && mExitSharedElementBundle != null && mExitSharedElementBundle.isEmpty() ^ true && mSharedElements.isEmpty() ^ true && getSharedElementTransition() != null)
            startTransition(new Runnable() {

                public void run()
                {
                    ExitTransitionCoordinator._2D_wrap6(ExitTransitionCoordinator.this, decorView);
                }

                final ExitTransitionCoordinator this$0;
                final ViewGroup val$decorView;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                decorView = viewgroup;
                super();
            }
            }
);
        else
            sharedElementTransitionComplete();
    }

    private void startExitTransition()
    {
        Transition transition = getExitTransition();
        ViewGroup viewgroup = getDecor();
        if(transition != null && viewgroup != null && mTransitioningViews != null)
        {
            setTransitioningViewsVisiblity(0, false);
            TransitionManager.beginDelayedTransition(viewgroup, transition);
            setTransitioningViewsVisiblity(4, false);
            viewgroup.invalidate();
        } else
        {
            transitionStarted();
        }
    }

    private void startSharedElementExit(ViewGroup viewgroup)
    {
        Transition transition = getSharedElementExitTransition();
        transition.addListener(new TransitionListenerAdapter() {

            public void onTransitionEnd(Transition transition1)
            {
                transition1.removeListener(this);
                if(isViewsTransitionComplete())
                    ExitTransitionCoordinator._2D_wrap1(ExitTransitionCoordinator.this);
            }

            final ExitTransitionCoordinator this$0;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                super();
            }
        }
);
        ArrayList arraylist = createSnapshots(mExitSharedElementBundle, mSharedElementNames);
        OneShotPreDrawListener.add(viewgroup, new _.Lambda.Pcw_0289sroTvc5U7X_pS90OouM((byte)5, this, arraylist));
        setGhostVisibility(4);
        scheduleGhostVisibilityChange(4);
        if(mListener != null)
            mListener.onSharedElementEnd(mSharedElementNames, mSharedElements, arraylist);
        TransitionManager.beginDelayedTransition(viewgroup, transition);
        scheduleGhostVisibilityChange(0);
        setGhostVisibility(0);
        viewgroup.invalidate();
    }

    private void stopCancel()
    {
        if(mHandler != null)
            mHandler.removeMessages(106);
    }

    protected void clearState()
    {
        mHandler = null;
        mSharedElementBundle = null;
        if(mBackgroundAnimator != null)
        {
            mBackgroundAnimator.cancel();
            mBackgroundAnimator = null;
        }
        mExitSharedElementBundle = null;
        super.clearState();
    }

    protected Transition getSharedElementTransition()
    {
        if(mIsReturning)
            return getWindow().getSharedElementReturnTransition();
        else
            return getWindow().getSharedElementExitTransition();
    }

    protected Transition getViewsTransition()
    {
        if(mIsReturning)
            return getWindow().getReturnTransition();
        else
            return getWindow().getExitTransition();
    }

    protected boolean isReadyToNotify()
    {
        boolean flag;
        if(mSharedElementBundle != null && mResultReceiver != null)
            flag = mIsBackgroundReady;
        else
            flag = false;
        return flag;
    }

    void lambda$_2D_android_app_ExitTransitionCoordinator_6391(ArrayList arraylist)
    {
        setSharedElementState(mExitSharedElementBundle, arraylist);
    }

    protected boolean moveSharedElementWithParent()
    {
        return mIsReturning ^ true;
    }

    protected void notifyComplete()
    {
        if(isReadyToNotify())
            if(!mSharedElementNotified)
            {
                mSharedElementNotified = true;
                delayCancel();
                if(mListener == null)
                {
                    mResultReceiver.send(103, mSharedElementBundle);
                    notifyExitComplete();
                } else
                {
                    final ResultReceiver resultReceiver = mResultReceiver;
                    final Bundle sharedElementBundle = mSharedElementBundle;
                    mListener.onSharedElementsArrived(mSharedElementNames, mSharedElements, new SharedElementCallback.OnSharedElementsReadyListener() {

                        public void onSharedElementsReady()
                        {
                            resultReceiver.send(103, sharedElementBundle);
                            ExitTransitionCoordinator._2D_wrap4(ExitTransitionCoordinator.this);
                        }

                        final ExitTransitionCoordinator this$0;
                        final ResultReceiver val$resultReceiver;
                        final Bundle val$sharedElementBundle;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                resultReceiver = resultreceiver;
                sharedElementBundle = bundle;
                super();
            }
                    }
);
                }
            } else
            {
                notifyExitComplete();
            }
    }

    protected void onReceiveResult(int i, Bundle bundle)
    {
        i;
        JVM INSTR tableswitch 100 107: default 48
    //                   100 49
    //                   101 99
    //                   102 48
    //                   103 48
    //                   104 48
    //                   105 117
    //                   106 145
    //                   107 133;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L4 _L5 _L6
_L1:
        return;
_L2:
        stopCancel();
        mResultReceiver = (ResultReceiver)bundle.getParcelable("android:remoteReceiver");
        if(mIsCanceled)
        {
            mResultReceiver.send(106, null);
            mResultReceiver = null;
        } else
        {
            notifyComplete();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        stopCancel();
        if(!mIsCanceled)
            hideSharedElements();
        continue; /* Loop/switch isn't completed */
_L4:
        mHandler.removeMessages(106);
        startExit();
        continue; /* Loop/switch isn't completed */
_L6:
        mExitSharedElementBundle = bundle;
        sharedElementExitBack();
        continue; /* Loop/switch isn't completed */
_L5:
        mIsCanceled = true;
        finish();
        if(true) goto _L1; else goto _L7
_L7:
    }

    protected void onTransitionsComplete()
    {
        notifyComplete();
    }

    public void resetViews()
    {
        ViewGroup viewgroup = getDecor();
        if(viewgroup != null)
            TransitionManager.endTransitions(viewgroup);
        if(mTransitioningViews != null)
        {
            showViews(mTransitioningViews, true);
            setTransitioningViewsVisiblity(0, true);
        }
        showViews(mSharedElements, true);
        mIsHidden = true;
        if(!mIsReturning && viewgroup != null)
            viewgroup.suppressLayout(false);
        moveSharedElementsFromOverlay();
        clearState();
    }

    void setHideSharedElementsCallback(HideSharedElementsCallback hidesharedelementscallback)
    {
        mHideSharedElementsCallback = hidesharedelementscallback;
    }

    protected void sharedElementTransitionComplete()
    {
        Bundle bundle;
        if(mExitSharedElementBundle == null)
            bundle = captureSharedElementState();
        else
            bundle = captureExitSharedElementsState();
        mSharedElementBundle = bundle;
        super.sharedElementTransitionComplete();
    }

    public void startExit()
    {
        if(!mIsExitStarted)
        {
            backgroundAnimatorComplete();
            mIsExitStarted = true;
            pauseInput();
            ViewGroup viewgroup = getDecor();
            if(viewgroup != null)
                viewgroup.suppressLayout(true);
            moveSharedElementsToOverlay();
            startTransition(new Runnable() {

                public void run()
                {
                    if(ExitTransitionCoordinator._2D_get0(ExitTransitionCoordinator.this) != null)
                        ExitTransitionCoordinator._2D_wrap0(ExitTransitionCoordinator.this);
                    else
                        ExitTransitionCoordinator._2D_wrap5(ExitTransitionCoordinator.this);
                }

                final ExitTransitionCoordinator this$0;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                super();
            }
            }
);
        }
    }

    public void startExit(int i, Intent intent)
    {
        if(!mIsExitStarted)
        {
            mIsExitStarted = true;
            pauseInput();
            Object obj = getDecor();
            if(obj != null)
                ((ViewGroup) (obj)).suppressLayout(true);
            mHandler = new Handler() {

                public void handleMessage(Message message)
                {
                    ExitTransitionCoordinator._2D_set2(ExitTransitionCoordinator.this, true);
                    ExitTransitionCoordinator._2D_wrap3(ExitTransitionCoordinator.this);
                }

                final ExitTransitionCoordinator this$0;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                super();
            }
            }
;
            delayCancel();
            moveSharedElementsToOverlay();
            if(obj != null && ((ViewGroup) (obj)).getBackground() == null)
                getWindow().setBackgroundDrawable(new ColorDrawable(0));
            boolean flag;
            if(obj == null || ((ViewGroup) (obj)).getContext().getApplicationInfo().targetSdkVersion >= 23)
                flag = true;
            else
                flag = false;
            if(flag)
                obj = mSharedElementNames;
            else
                obj = mAllSharedElementNames;
            intent = ActivityOptions.makeSceneTransitionAnimation(mActivity, this, ((ArrayList) (obj)), i, intent);
            mActivity.convertToTranslucent(new Activity.TranslucentConversionListener() {

                public void onTranslucentConversionComplete(boolean flag1)
                {
                    if(!ExitTransitionCoordinator._2D_get1(ExitTransitionCoordinator.this))
                        ExitTransitionCoordinator._2D_wrap2(ExitTransitionCoordinator.this);
                }

                final ExitTransitionCoordinator this$0;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                super();
            }
            }
, intent);
            startTransition(new Runnable() {

                public void run()
                {
                    ExitTransitionCoordinator._2D_wrap5(ExitTransitionCoordinator.this);
                }

                final ExitTransitionCoordinator this$0;

            
            {
                this$0 = ExitTransitionCoordinator.this;
                super();
            }
            }
);
        }
    }

    public void stop()
    {
        if(mIsReturning && mActivity != null)
        {
            mActivity.convertToTranslucent(null, null);
            finish();
        }
    }

    private static final long MAX_WAIT_MS = 1000L;
    private static final String TAG = "ExitTransitionCoordinator";
    private Activity mActivity;
    private ObjectAnimator mBackgroundAnimator;
    private boolean mExitNotified;
    private Bundle mExitSharedElementBundle;
    private Handler mHandler;
    private HideSharedElementsCallback mHideSharedElementsCallback;
    private boolean mIsBackgroundReady;
    private boolean mIsCanceled;
    private boolean mIsExitStarted;
    private boolean mIsHidden;
    private Bundle mSharedElementBundle;
    private boolean mSharedElementNotified;
    private boolean mSharedElementsHidden;
}
