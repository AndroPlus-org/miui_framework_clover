// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import com.android.internal.view.OneShotPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            ExitTransitionCoordinator, EnterTransitionCoordinator, ActivityOptions, Activity

class ActivityTransitionState
{

    static EnterTransitionCoordinator _2D_get0(ActivityTransitionState activitytransitionstate)
    {
        return activitytransitionstate.mEnterTransitionCoordinator;
    }

    static void _2D_wrap0(ActivityTransitionState activitytransitionstate)
    {
        activitytransitionstate.restoreExitedViews();
    }

    static void _2D_wrap1(ActivityTransitionState activitytransitionstate)
    {
        activitytransitionstate.restoreReenteringViews();
    }

    public ActivityTransitionState()
    {
        mExitTransitionCoordinatorsKey = 1;
    }

    private void restoreExitedViews()
    {
        if(mCalledExitCoordinator != null)
        {
            mCalledExitCoordinator.resetViews();
            mCalledExitCoordinator = null;
        }
    }

    private void restoreReenteringViews()
    {
        if(mEnterTransitionCoordinator != null && mEnterTransitionCoordinator.isReturning() && mEnterTransitionCoordinator.isCrossTask() ^ true)
        {
            mEnterTransitionCoordinator.forceViewsToAppear();
            mExitingFrom = null;
            mExitingTo = null;
            mExitingToView = null;
        }
    }

    private void startEnter()
    {
        if(mEnterTransitionCoordinator.isReturning())
        {
            if(mExitingToView != null)
                mEnterTransitionCoordinator.viewInstancesReady(mExitingFrom, mExitingTo, mExitingToView);
            else
                mEnterTransitionCoordinator.namedViewsReady(mExitingFrom, mExitingTo);
        } else
        {
            mEnterTransitionCoordinator.namedViewsReady(null, null);
            mEnteringNames = mEnterTransitionCoordinator.getAllSharedElementNames();
        }
        mExitingFrom = null;
        mExitingTo = null;
        mExitingToView = null;
        mEnterActivityOptions = null;
    }

    public int addExitTransitionCoordinator(ExitTransitionCoordinator exittransitioncoordinator)
    {
        if(mExitTransitionCoordinators == null)
            mExitTransitionCoordinators = new SparseArray();
        exittransitioncoordinator = new WeakReference(exittransitioncoordinator);
        for(int i = mExitTransitionCoordinators.size() - 1; i >= 0; i--)
            if(((WeakReference)mExitTransitionCoordinators.valueAt(i)).get() == null)
                mExitTransitionCoordinators.removeAt(i);

        int j = mExitTransitionCoordinatorsKey;
        mExitTransitionCoordinatorsKey = j + 1;
        mExitTransitionCoordinators.append(j, exittransitioncoordinator);
        return j;
    }

    public void clear()
    {
        mEnteringNames = null;
        mExitingFrom = null;
        mExitingTo = null;
        mExitingToView = null;
        mCalledExitCoordinator = null;
        mEnterTransitionCoordinator = null;
        mEnterActivityOptions = null;
        mExitTransitionCoordinators = null;
    }

    public void enterReady(Activity activity)
    {
        if(mEnterActivityOptions == null || mIsEnterTriggered)
            return;
        mIsEnterTriggered = true;
        mHasExited = false;
        ArrayList arraylist = mEnterActivityOptions.getSharedElementNames();
        android.os.ResultReceiver resultreceiver = mEnterActivityOptions.getResultReceiver();
        if(mEnterActivityOptions.isReturning())
        {
            restoreExitedViews();
            activity.getWindow().getDecorView().setVisibility(0);
        }
        mEnterTransitionCoordinator = new EnterTransitionCoordinator(activity, resultreceiver, arraylist, mEnterActivityOptions.isReturning(), mEnterActivityOptions.isCrossTask());
        if(mEnterActivityOptions.isCrossTask())
        {
            mExitingFrom = new ArrayList(mEnterActivityOptions.getSharedElementNames());
            mExitingTo = new ArrayList(mEnterActivityOptions.getSharedElementNames());
        }
        if(!mIsEnterPostponed)
            startEnter();
    }

    public boolean isTransitionRunning()
    {
        if(mEnterTransitionCoordinator != null && mEnterTransitionCoordinator.isTransitionRunning())
            return true;
        if(mCalledExitCoordinator != null && mCalledExitCoordinator.isTransitionRunning())
            return true;
        return mReturnExitCoordinator != null && mReturnExitCoordinator.isTransitionRunning();
    }

    void lambda$_2D_android_app_ActivityTransitionState_12157(Activity activity)
    {
        if(mReturnExitCoordinator != null)
            mReturnExitCoordinator.startExit(activity.mResultCode, activity.mResultData);
    }

    public void onResume(Activity activity, boolean flag)
    {
        if(flag || mEnterTransitionCoordinator == null)
        {
            restoreExitedViews();
            restoreReenteringViews();
        } else
        {
            activity.mHandler.postDelayed(new Runnable() {

                public void run()
                {
                    if(ActivityTransitionState._2D_get0(ActivityTransitionState.this) == null || ActivityTransitionState._2D_get0(ActivityTransitionState.this).isWaitingForRemoteExit())
                    {
                        ActivityTransitionState._2D_wrap0(ActivityTransitionState.this);
                        ActivityTransitionState._2D_wrap1(ActivityTransitionState.this);
                    }
                }

                final ActivityTransitionState this$0;

            
            {
                this$0 = ActivityTransitionState.this;
                super();
            }
            }
, 1000L);
        }
    }

    public void onStop()
    {
        restoreExitedViews();
        if(mEnterTransitionCoordinator != null)
        {
            mEnterTransitionCoordinator.stop();
            mEnterTransitionCoordinator = null;
        }
        if(mReturnExitCoordinator != null)
        {
            mReturnExitCoordinator.stop();
            mReturnExitCoordinator = null;
        }
    }

    public void postponeEnterTransition()
    {
        mIsEnterPostponed = true;
    }

    public void readState(Bundle bundle)
    {
        if(bundle != null)
        {
            if(mEnterTransitionCoordinator == null || mEnterTransitionCoordinator.isReturning())
                mEnteringNames = bundle.getStringArrayList("android:enteringSharedElements");
            if(mEnterTransitionCoordinator == null)
            {
                mExitingFrom = bundle.getStringArrayList("android:exitingMappedFrom");
                mExitingTo = bundle.getStringArrayList("android:exitingMappedTo");
            }
        }
    }

    public void saveState(Bundle bundle)
    {
        if(mEnteringNames != null)
            bundle.putStringArrayList("android:enteringSharedElements", mEnteringNames);
        if(mExitingFrom != null)
        {
            bundle.putStringArrayList("android:exitingMappedFrom", mExitingFrom);
            bundle.putStringArrayList("android:exitingMappedTo", mExitingTo);
        }
    }

    public void setEnterActivityOptions(Activity activity, ActivityOptions activityoptions)
    {
        Window window = activity.getWindow();
        if(window == null)
            return;
        window.getDecorView();
        if(window.hasFeature(13) && activityoptions != null && mEnterActivityOptions == null && mEnterTransitionCoordinator == null && activityoptions.getAnimationType() == 5)
        {
            mEnterActivityOptions = activityoptions;
            mIsEnterTriggered = false;
            if(mEnterActivityOptions.isReturning())
            {
                restoreExitedViews();
                int i = mEnterActivityOptions.getResultCode();
                if(i != 0)
                {
                    activityoptions = mEnterActivityOptions.getResultData();
                    if(activityoptions != null)
                        activityoptions.setExtrasClassLoader(activity.getClassLoader());
                    activity.onActivityReenter(i, activityoptions);
                }
            }
        }
    }

    public boolean startExitBackTransition(Activity activity)
    {
        if(mEnteringNames == null || mCalledExitCoordinator != null)
            return false;
        if(!mHasExited)
        {
            mHasExited = true;
            Transition transition = null;
            android.view.ViewGroup viewgroup = null;
            boolean flag = false;
            if(mEnterTransitionCoordinator != null)
            {
                Transition transition1 = mEnterTransitionCoordinator.getEnterViewsTransition();
                android.view.ViewGroup viewgroup1 = mEnterTransitionCoordinator.getDecor();
                boolean flag1 = mEnterTransitionCoordinator.cancelEnter();
                mEnterTransitionCoordinator = null;
                viewgroup = viewgroup1;
                flag = flag1;
                transition = transition1;
                if(transition1 != null)
                {
                    viewgroup = viewgroup1;
                    flag = flag1;
                    transition = transition1;
                    if(viewgroup1 != null)
                    {
                        transition1.pause(viewgroup1);
                        transition = transition1;
                        flag = flag1;
                        viewgroup = viewgroup1;
                    }
                }
            }
            mReturnExitCoordinator = new ExitTransitionCoordinator(activity, activity.getWindow(), activity.mEnterTransitionListener, mEnteringNames, null, null, true);
            if(transition != null && viewgroup != null)
                transition.resume(viewgroup);
            if(flag && viewgroup != null)
                OneShotPreDrawListener.add(viewgroup, new _.Lambda.Pcw_0289sroTvc5U7X_pS90OouM((byte)1, this, activity));
            else
                mReturnExitCoordinator.startExit(activity.mResultCode, activity.mResultData);
        }
        return true;
    }

    public void startExitOutTransition(Activity activity, Bundle bundle)
    {
        mEnterTransitionCoordinator = null;
        if(!activity.getWindow().hasFeature(13) || mExitTransitionCoordinators == null)
            return;
        activity = new ActivityOptions(bundle);
        if(activity.getAnimationType() == 5)
        {
            int i = activity.getExitCoordinatorKey();
            i = mExitTransitionCoordinators.indexOfKey(i);
            if(i >= 0)
            {
                mCalledExitCoordinator = (ExitTransitionCoordinator)((WeakReference)mExitTransitionCoordinators.valueAt(i)).get();
                mExitTransitionCoordinators.removeAt(i);
                if(mCalledExitCoordinator != null)
                {
                    mExitingFrom = mCalledExitCoordinator.getAcceptedNames();
                    mExitingTo = mCalledExitCoordinator.getMappedNames();
                    mExitingToView = mCalledExitCoordinator.copyMappedViews();
                    mCalledExitCoordinator.startExit();
                }
            }
        }
    }

    public void startPostponedEnterTransition()
    {
        if(mIsEnterPostponed)
        {
            mIsEnterPostponed = false;
            if(mEnterTransitionCoordinator != null)
                startEnter();
        }
    }

    private static final String ENTERING_SHARED_ELEMENTS = "android:enteringSharedElements";
    private static final String EXITING_MAPPED_FROM = "android:exitingMappedFrom";
    private static final String EXITING_MAPPED_TO = "android:exitingMappedTo";
    private ExitTransitionCoordinator mCalledExitCoordinator;
    private ActivityOptions mEnterActivityOptions;
    private EnterTransitionCoordinator mEnterTransitionCoordinator;
    private ArrayList mEnteringNames;
    private SparseArray mExitTransitionCoordinators;
    private int mExitTransitionCoordinatorsKey;
    private ArrayList mExitingFrom;
    private ArrayList mExitingTo;
    private ArrayList mExitingToView;
    private boolean mHasExited;
    private boolean mIsEnterPostponed;
    private boolean mIsEnterTriggered;
    private ExitTransitionCoordinator mReturnExitCoordinator;
}
