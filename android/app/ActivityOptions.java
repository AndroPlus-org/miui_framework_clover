// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.*;
import android.transition.*;
import android.util.Pair;
import android.util.Slog;
import android.view.*;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            PendingIntent, Activity, ActivityTransitionState, ExitTransitionCoordinator, 
//            SharedElementCallback

public class ActivityOptions
{
    private static class HideWindowListener extends TransitionListenerAdapter
        implements ExitTransitionCoordinator.HideSharedElementsCallback
    {

        private void hideWhenDone()
        {
            if(mSharedElementHidden && (!mWaitingForTransition || mTransitionEnded))
            {
                mExit.resetViews();
                int i = mSharedElements.size();
                for(int j = 0; j < i; j++)
                    ((View)mSharedElements.get(j)).requestLayout();

                View view = mWindow.getDecorView();
                if(view != null)
                {
                    view.setTagInternal(0x102020a, null);
                    view.setVisibility(8);
                }
            }
        }

        public void hideSharedElements()
        {
            mSharedElementHidden = true;
            hideWhenDone();
        }

        public void onTransitionEnd(Transition transition)
        {
            mTransitionEnded = true;
            hideWhenDone();
            transition.removeListener(this);
        }

        private final ExitTransitionCoordinator mExit;
        private boolean mSharedElementHidden;
        private ArrayList mSharedElements;
        private boolean mTransitionEnded;
        private final boolean mWaitingForTransition;
        private final Window mWindow;

        public HideWindowListener(Window window, ExitTransitionCoordinator exittransitioncoordinator)
        {
            mWindow = window;
            mExit = exittransitioncoordinator;
            mSharedElements = new ArrayList(exittransitioncoordinator.mSharedElements);
            window = mWindow.getExitTransition();
            if(window != null)
            {
                window.addListener(this);
                mWaitingForTransition = true;
            } else
            {
                mWaitingForTransition = false;
            }
            window = mWindow.getDecorView();
            if(window != null)
            {
                if(window.getTag(0x102020a) != null)
                    throw new IllegalStateException("Cannot start a transition while one is running");
                window.setTagInternal(0x102020a, exittransitioncoordinator);
            }
        }
    }

    public static interface OnAnimationFinishedListener
    {

        public abstract void onAnimationFinished();
    }

    public static interface OnAnimationStartedListener
    {

        public abstract void onAnimationStarted();
    }


    private ActivityOptions()
    {
        mAnimationType = 0;
        mLaunchDisplayId = -1;
        mLaunchStackId = -1;
        mLaunchTaskId = -1;
        mDockCreateMode = 0;
        mRotationAnimationHint = -1;
    }

    public ActivityOptions(Bundle bundle)
    {
        mAnimationType = 0;
        mLaunchDisplayId = -1;
        mLaunchStackId = -1;
        mLaunchTaskId = -1;
        mDockCreateMode = 0;
        mRotationAnimationHint = -1;
        bundle.setDefusable(true);
        mPackageName = bundle.getString("android:activity.packageName");
        android.os.Parcelable aparcelable[];
        int i;
        try
        {
            mUsageTimeReport = (PendingIntent)bundle.getParcelable("android:activity.usageTimeReport");
        }
        catch(RuntimeException runtimeexception)
        {
            Slog.w("ActivityOptions", runtimeexception);
        }
        mLaunchBounds = (Rect)bundle.getParcelable("android:activity.launchBounds");
        mAnimationType = bundle.getInt("android:activity.animType");
        mAnimationType;
        JVM INSTR lookupswitch 10: default 180
    //                   1: 320
    //                   2: 372
    //                   3: 419
    //                   4: 419
    //                   5: 501
    //                   8: 419
    //                   9: 419
    //                   10: 358
    //                   11: 372
    //                   100: 372;
           goto _L1 _L2 _L3 _L4 _L4 _L5 _L4 _L4 _L6 _L3 _L3
_L1:
        mLaunchDisplayId = bundle.getInt("android.activity.launchDisplayId", -1);
        mLaunchStackId = bundle.getInt("android.activity.launchStackId", -1);
        mLaunchTaskId = bundle.getInt("android.activity.launchTaskId", -1);
        mTaskOverlay = bundle.getBoolean("android.activity.taskOverlay", false);
        mTaskOverlayCanResume = bundle.getBoolean("android.activity.taskOverlayCanResume", false);
        mDockCreateMode = bundle.getInt("android:activity.dockCreateMode", 0);
        mDisallowEnterPictureInPictureWhileLaunching = bundle.getBoolean("android:activity.disallowEnterPictureInPictureWhileLaunching", false);
        if(bundle.containsKey("android:activity.animSpecs"))
        {
            aparcelable = bundle.getParcelableArray("android:activity.animSpecs");
            mAnimSpecs = new AppTransitionAnimationSpec[aparcelable.length];
            for(i = aparcelable.length - 1; i >= 0; i--)
                mAnimSpecs[i] = (AppTransitionAnimationSpec)aparcelable[i];

        }
        break; /* Loop/switch isn't completed */
_L2:
        mCustomEnterResId = bundle.getInt("android:activity.animEnterRes", 0);
        mCustomExitResId = bundle.getInt("android:activity.animExitRes", 0);
        mAnimationStartedListener = android.os.IRemoteCallback.Stub.asInterface(bundle.getBinder("android:activity.animStartListener"));
        continue; /* Loop/switch isn't completed */
_L6:
        mCustomInPlaceResId = bundle.getInt("android:activity.animInPlaceRes", 0);
        continue; /* Loop/switch isn't completed */
_L3:
        mStartX = bundle.getInt("android:activity.animStartX", 0);
        mStartY = bundle.getInt("android:activity.animStartY", 0);
        mWidth = bundle.getInt("android:activity.animWidth", 0);
        mHeight = bundle.getInt("android:activity.animHeight", 0);
        continue; /* Loop/switch isn't completed */
_L4:
        GraphicBuffer graphicbuffer = (GraphicBuffer)bundle.getParcelable("android:activity.animThumbnail");
        if(graphicbuffer != null)
            mThumbnail = Bitmap.createHardwareBitmap(graphicbuffer);
        mStartX = bundle.getInt("android:activity.animStartX", 0);
        mStartY = bundle.getInt("android:activity.animStartY", 0);
        mWidth = bundle.getInt("android:activity.animWidth", 0);
        mHeight = bundle.getInt("android:activity.animHeight", 0);
        mAnimationStartedListener = android.os.IRemoteCallback.Stub.asInterface(bundle.getBinder("android:activity.animStartListener"));
        continue; /* Loop/switch isn't completed */
_L5:
        mTransitionReceiver = (ResultReceiver)bundle.getParcelable("android:activity.transitionCompleteListener");
        mIsReturning = bundle.getBoolean("android:activity.transitionIsReturning", false);
        mSharedElementNames = bundle.getStringArrayList("android:activity.sharedElementNames");
        mResultData = (Intent)bundle.getParcelable("android:activity.resultData");
        mResultCode = bundle.getInt("android:activity.resultCode");
        mExitCoordinatorIndex = bundle.getInt("android:activity.exitCoordinatorIndex");
        if(true) goto _L1; else goto _L7
_L7:
        if(bundle.containsKey("android:activity.animationFinishedListener"))
            mAnimationFinishedListener = android.os.IRemoteCallback.Stub.asInterface(bundle.getBinder("android:activity.animationFinishedListener"));
        mRotationAnimationHint = bundle.getInt("android:activity.rotationAnimationHint");
        mAppVerificationBundle = bundle.getBundle("android:instantapps.installerbundle");
        if(bundle.containsKey("android:activity.specsFuture"))
            mSpecsFuture = android.view.IAppTransitionAnimationSpecsFuture.Stub.asInterface(bundle.getBinder("android:activity.specsFuture"));
        return;
    }

    public static void abort(ActivityOptions activityoptions)
    {
        if(activityoptions != null)
            activityoptions.abort();
    }

    public static ActivityOptions fromBundle(Bundle bundle)
    {
        ActivityOptions activityoptions = null;
        if(bundle != null)
            activityoptions = new ActivityOptions(bundle);
        return activityoptions;
    }

    private static ActivityOptions makeAspectScaledThumbnailAnimation(View view, Bitmap bitmap, int i, int j, int k, int l, Handler handler, OnAnimationStartedListener onanimationstartedlistener, 
            boolean flag)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mPackageName = view.getContext().getPackageName();
        int i1;
        if(flag)
            i1 = 8;
        else
            i1 = 9;
        activityoptions.mAnimationType = i1;
        activityoptions.mThumbnail = bitmap;
        bitmap = new int[2];
        view.getLocationOnScreen(bitmap);
        activityoptions.mStartX = bitmap[0] + i;
        activityoptions.mStartY = bitmap[1] + j;
        activityoptions.mWidth = k;
        activityoptions.mHeight = l;
        activityoptions.setOnAnimationStartedListener(handler, onanimationstartedlistener);
        return activityoptions;
    }

    public static ActivityOptions makeBasic()
    {
        return new ActivityOptions();
    }

    public static ActivityOptions makeClipRevealAnimation(View view, int i, int j, int k, int l)
    {
        return makeClipRevealAnimation(view, i, j, k, l, false);
    }

    public static ActivityOptions makeClipRevealAnimation(View view, int i, int j, int k, int l, boolean flag)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        int i1;
        int ai[];
        if(flag)
            i1 = 100;
        else
            i1 = 11;
        activityoptions.mAnimationType = i1;
        ai = new int[2];
        view.getLocationOnScreen(ai);
        activityoptions.mStartX = ai[0] + i;
        activityoptions.mStartY = ai[1] + j;
        activityoptions.mWidth = k;
        activityoptions.mHeight = l;
        return activityoptions;
    }

    public static ActivityOptions makeCustomAnimation(Context context, int i, int j)
    {
        return makeCustomAnimation(context, i, j, null, null);
    }

    public static ActivityOptions makeCustomAnimation(Context context, int i, int j, Handler handler, OnAnimationStartedListener onanimationstartedlistener)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mPackageName = context.getPackageName();
        activityoptions.mAnimationType = 1;
        activityoptions.mCustomEnterResId = i;
        activityoptions.mCustomExitResId = j;
        activityoptions.setOnAnimationStartedListener(handler, onanimationstartedlistener);
        return activityoptions;
    }

    public static ActivityOptions makeCustomInPlaceAnimation(Context context, int i)
    {
        if(i == 0)
        {
            throw new RuntimeException("You must specify a valid animation.");
        } else
        {
            ActivityOptions activityoptions = new ActivityOptions();
            activityoptions.mPackageName = context.getPackageName();
            activityoptions.mAnimationType = 10;
            activityoptions.mCustomInPlaceResId = i;
            return activityoptions;
        }
    }

    public static ActivityOptions makeMultiThumbFutureAspectScaleAnimation(Context context, Handler handler, IAppTransitionAnimationSpecsFuture iapptransitionanimationspecsfuture, OnAnimationStartedListener onanimationstartedlistener, boolean flag)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mPackageName = context.getPackageName();
        int i;
        if(flag)
            i = 8;
        else
            i = 9;
        activityoptions.mAnimationType = i;
        activityoptions.mSpecsFuture = iapptransitionanimationspecsfuture;
        activityoptions.setOnAnimationStartedListener(handler, onanimationstartedlistener);
        return activityoptions;
    }

    public static ActivityOptions makeScaleUpAnimation(View view, int i, int j, int k, int l)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mPackageName = view.getContext().getPackageName();
        activityoptions.mAnimationType = 2;
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        activityoptions.mStartX = ai[0] + i;
        activityoptions.mStartY = ai[1] + j;
        activityoptions.mWidth = k;
        activityoptions.mHeight = l;
        return activityoptions;
    }

    static ActivityOptions makeSceneTransitionAnimation(Activity activity, ExitTransitionCoordinator exittransitioncoordinator, ArrayList arraylist, int i, Intent intent)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mAnimationType = 5;
        activityoptions.mSharedElementNames = arraylist;
        activityoptions.mTransitionReceiver = exittransitioncoordinator;
        activityoptions.mIsReturning = true;
        activityoptions.mResultCode = i;
        activityoptions.mResultData = intent;
        activityoptions.mExitCoordinatorIndex = activity.mActivityTransitionState.addExitTransitionCoordinator(exittransitioncoordinator);
        return activityoptions;
    }

    public static ActivityOptions makeSceneTransitionAnimation(Activity activity, View view, String s)
    {
        return makeSceneTransitionAnimation(activity, new Pair[] {
            Pair.create(view, s)
        });
    }

    public static transient ActivityOptions makeSceneTransitionAnimation(Activity activity, Pair apair[])
    {
        ActivityOptions activityoptions = new ActivityOptions();
        makeSceneTransitionAnimation(activity, activity.getWindow(), activityoptions, activity.mExitTransitionListener, apair);
        return activityoptions;
    }

    static ExitTransitionCoordinator makeSceneTransitionAnimation(Activity activity, Window window, ActivityOptions activityoptions, SharedElementCallback sharedelementcallback, Pair apair[])
    {
        if(!window.hasFeature(13))
        {
            activityoptions.mAnimationType = 6;
            return null;
        }
        activityoptions.mAnimationType = 5;
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        if(apair != null)
        {
            for(int i = 0; i < apair.length; i++)
            {
                Pair pair = apair[i];
                String s = (String)pair.second;
                if(s == null)
                    throw new IllegalArgumentException("Shared element name must not be null");
                arraylist.add(s);
                if((View)pair.first == null)
                    throw new IllegalArgumentException("Shared element must not be null");
                arraylist1.add((View)pair.first);
            }

        }
        window = new ExitTransitionCoordinator(activity, window, sharedelementcallback, arraylist, arraylist, arraylist1, false);
        activityoptions.mTransitionReceiver = window;
        activityoptions.mSharedElementNames = arraylist;
        boolean flag;
        if(activity == null)
            flag = true;
        else
            flag = false;
        activityoptions.mIsReturning = flag;
        if(activity == null)
            activityoptions.mExitCoordinatorIndex = -1;
        else
            activityoptions.mExitCoordinatorIndex = activity.mActivityTransitionState.addExitTransitionCoordinator(window);
        return window;
    }

    public static ActivityOptions makeTaskLaunchBehind()
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mAnimationType = 7;
        return activityoptions;
    }

    private static ActivityOptions makeThumbnailAnimation(View view, Bitmap bitmap, int i, int j, OnAnimationStartedListener onanimationstartedlistener, boolean flag)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mPackageName = view.getContext().getPackageName();
        int k;
        if(flag)
            k = 3;
        else
            k = 4;
        activityoptions.mAnimationType = k;
        activityoptions.mThumbnail = bitmap;
        bitmap = new int[2];
        view.getLocationOnScreen(bitmap);
        activityoptions.mStartX = bitmap[0] + i;
        activityoptions.mStartY = bitmap[1] + j;
        activityoptions.setOnAnimationStartedListener(view.getHandler(), onanimationstartedlistener);
        return activityoptions;
    }

    public static ActivityOptions makeThumbnailAspectScaleDownAnimation(View view, Bitmap bitmap, int i, int j, int k, int l, Handler handler, OnAnimationStartedListener onanimationstartedlistener)
    {
        return makeAspectScaledThumbnailAnimation(view, bitmap, i, j, k, l, handler, onanimationstartedlistener, false);
    }

    public static ActivityOptions makeThumbnailAspectScaleDownAnimation(View view, AppTransitionAnimationSpec aapptransitionanimationspec[], Handler handler, OnAnimationStartedListener onanimationstartedlistener, OnAnimationFinishedListener onanimationfinishedlistener)
    {
        ActivityOptions activityoptions = new ActivityOptions();
        activityoptions.mPackageName = view.getContext().getPackageName();
        activityoptions.mAnimationType = 9;
        activityoptions.mAnimSpecs = aapptransitionanimationspec;
        activityoptions.setOnAnimationStartedListener(handler, onanimationstartedlistener);
        activityoptions.setOnAnimationFinishedListener(handler, onanimationfinishedlistener);
        return activityoptions;
    }

    public static ActivityOptions makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int j)
    {
        return makeThumbnailScaleUpAnimation(view, bitmap, i, j, null);
    }

    private static ActivityOptions makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int j, OnAnimationStartedListener onanimationstartedlistener)
    {
        return makeThumbnailAnimation(view, bitmap, i, j, onanimationstartedlistener, true);
    }

    private void setOnAnimationFinishedListener(final Handler handler, final OnAnimationFinishedListener listener)
    {
        if(listener != null)
            mAnimationFinishedListener = new android.os.IRemoteCallback.Stub() {

                public void sendResult(Bundle bundle)
                    throws RemoteException
                {
                    handler.post(listener. new Runnable() {

                        public void run()
                        {
                            listener.onAnimationFinished();
                        }

                        final _cls2 this$1;
                        final OnAnimationFinishedListener val$listener;

            
            {
                this$1 = final__pcls2;
                listener = OnAnimationFinishedListener.this;
                super();
            }
                    }
);
                }

                final ActivityOptions this$0;
                final Handler val$handler;
                final OnAnimationFinishedListener val$listener;

            
            {
                this$0 = ActivityOptions.this;
                handler = handler1;
                listener = onanimationfinishedlistener;
                super();
            }
            }
;
    }

    private void setOnAnimationStartedListener(final Handler handler, final OnAnimationStartedListener listener)
    {
        if(listener != null)
            mAnimationStartedListener = new android.os.IRemoteCallback.Stub() {

                public void sendResult(Bundle bundle)
                    throws RemoteException
                {
                    handler.post(listener. new Runnable() {

                        public void run()
                        {
                            listener.onAnimationStarted();
                        }

                        final _cls1 this$1;
                        final OnAnimationStartedListener val$listener;

            
            {
                this$1 = final__pcls1;
                listener = OnAnimationStartedListener.this;
                super();
            }
                    }
);
                }

                final ActivityOptions this$0;
                final Handler val$handler;
                final OnAnimationStartedListener val$listener;

            
            {
                this$0 = ActivityOptions.this;
                handler = handler1;
                listener = onanimationstartedlistener;
                super();
            }
            }
;
    }

    public static transient ActivityOptions startSharedElementAnimation(Window window, Pair apair[])
    {
        ActivityOptions activityoptions = new ActivityOptions();
        if(window.getDecorView() == null)
            return activityoptions;
        apair = makeSceneTransitionAnimation(null, window, activityoptions, ((SharedElementCallback) (null)), apair);
        if(apair != null)
        {
            apair.setHideSharedElementsCallback(new HideWindowListener(window, apair));
            apair.startExit();
        }
        return activityoptions;
    }

    public static void stopSharedElementAnimation(Window window)
    {
        window = window.getDecorView();
        if(window == null)
            return;
        ExitTransitionCoordinator exittransitioncoordinator = (ExitTransitionCoordinator)window.getTag(0x102020a);
        if(exittransitioncoordinator != null)
        {
            exittransitioncoordinator.cancelPendingTransitions();
            window.setTagInternal(0x102020a, null);
            TransitionManager.endTransitions((ViewGroup)window);
            exittransitioncoordinator.resetViews();
            exittransitioncoordinator.clearState();
            window.setVisibility(0);
        }
    }

    public void abort()
    {
        if(mAnimationStartedListener == null)
            break MISSING_BLOCK_LABEL_17;
        mAnimationStartedListener.sendResult(null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean canTaskOverlayResume()
    {
        return mTaskOverlayCanResume;
    }

    public boolean disallowEnterPictureInPictureWhileLaunching()
    {
        return mDisallowEnterPictureInPictureWhileLaunching;
    }

    public ActivityOptions forTargetActivity()
    {
        if(mAnimationType == 5)
        {
            ActivityOptions activityoptions = new ActivityOptions();
            activityoptions.update(this);
            return activityoptions;
        } else
        {
            return null;
        }
    }

    public AppTransitionAnimationSpec[] getAnimSpecs()
    {
        return mAnimSpecs;
    }

    public IRemoteCallback getAnimationFinishedListener()
    {
        return mAnimationFinishedListener;
    }

    public int getAnimationType()
    {
        return mAnimationType;
    }

    public int getCustomEnterResId()
    {
        return mCustomEnterResId;
    }

    public int getCustomExitResId()
    {
        return mCustomExitResId;
    }

    public int getCustomInPlaceResId()
    {
        return mCustomInPlaceResId;
    }

    public int getDockCreateMode()
    {
        return mDockCreateMode;
    }

    public int getExitCoordinatorKey()
    {
        return mExitCoordinatorIndex;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public Rect getLaunchBounds()
    {
        return mLaunchBounds;
    }

    public int getLaunchDisplayId()
    {
        return mLaunchDisplayId;
    }

    public int getLaunchStackId()
    {
        return mLaunchStackId;
    }

    public boolean getLaunchTaskBehind()
    {
        boolean flag;
        if(mAnimationType == 7)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getLaunchTaskId()
    {
        return mLaunchTaskId;
    }

    public IRemoteCallback getOnAnimationStartListener()
    {
        return mAnimationStartedListener;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getResultCode()
    {
        return mResultCode;
    }

    public Intent getResultData()
    {
        return mResultData;
    }

    public ResultReceiver getResultReceiver()
    {
        return mTransitionReceiver;
    }

    public int getRotationAnimationHint()
    {
        return mRotationAnimationHint;
    }

    public ArrayList getSharedElementNames()
    {
        return mSharedElementNames;
    }

    public IAppTransitionAnimationSpecsFuture getSpecsFuture()
    {
        return mSpecsFuture;
    }

    public int getStartX()
    {
        return mStartX;
    }

    public int getStartY()
    {
        return mStartY;
    }

    public boolean getTaskOverlay()
    {
        return mTaskOverlay;
    }

    public GraphicBuffer getThumbnail()
    {
        GraphicBuffer graphicbuffer = null;
        if(mThumbnail != null)
            graphicbuffer = mThumbnail.createGraphicBufferHandle();
        return graphicbuffer;
    }

    public PendingIntent getUsageTimeReport()
    {
        return mUsageTimeReport;
    }

    public int getWidth()
    {
        return mWidth;
    }

    boolean isCrossTask()
    {
        boolean flag = false;
        if(mExitCoordinatorIndex < 0)
            flag = true;
        return flag;
    }

    public boolean isReturning()
    {
        return mIsReturning;
    }

    public Bundle popAppVerificationBundle()
    {
        Bundle bundle = mAppVerificationBundle;
        mAppVerificationBundle = null;
        return bundle;
    }

    public void requestUsageTimeReport(PendingIntent pendingintent)
    {
        mUsageTimeReport = pendingintent;
    }

    public ActivityOptions setAppVerificationBundle(Bundle bundle)
    {
        mAppVerificationBundle = bundle;
        return this;
    }

    public void setDisallowEnterPictureInPictureWhileLaunching(boolean flag)
    {
        mDisallowEnterPictureInPictureWhileLaunching = flag;
    }

    public void setDockCreateMode(int i)
    {
        mDockCreateMode = i;
    }

    public ActivityOptions setLaunchBounds(Rect rect)
    {
        Rect rect1 = null;
        if(rect != null)
            rect1 = new Rect(rect);
        mLaunchBounds = rect1;
        return this;
    }

    public ActivityOptions setLaunchDisplayId(int i)
    {
        mLaunchDisplayId = i;
        return this;
    }

    public void setLaunchStackId(int i)
    {
        mLaunchStackId = i;
    }

    public void setLaunchTaskId(int i)
    {
        mLaunchTaskId = i;
    }

    public void setRotationAnimationHint(int i)
    {
        mRotationAnimationHint = i;
    }

    public void setTaskOverlay(boolean flag, boolean flag1)
    {
        mTaskOverlay = flag;
        mTaskOverlayCanResume = flag1;
    }

    public Bundle toBundle()
    {
        android.os.IBinder ibinder;
        Object obj;
        Bundle bundle;
        ibinder = null;
        obj = null;
        bundle = new Bundle();
        if(mPackageName != null)
            bundle.putString("android:activity.packageName", mPackageName);
        if(mLaunchBounds != null)
            bundle.putParcelable("android:activity.launchBounds", mLaunchBounds);
        bundle.putInt("android:activity.animType", mAnimationType);
        if(mUsageTimeReport != null)
            bundle.putParcelable("android:activity.usageTimeReport", mUsageTimeReport);
        mAnimationType;
        JVM INSTR lookupswitch 10: default 168
    //                   1: 328
    //                   2: 390
    //                   3: 433
    //                   4: 433
    //                   5: 545
    //                   8: 433
    //                   9: 433
    //                   10: 377
    //                   11: 390
    //                   100: 390;
           goto _L1 _L2 _L3 _L4 _L4 _L5 _L4 _L4 _L6 _L3 _L3
_L1:
        bundle.putInt("android.activity.launchDisplayId", mLaunchDisplayId);
        bundle.putInt("android.activity.launchStackId", mLaunchStackId);
        bundle.putInt("android.activity.launchTaskId", mLaunchTaskId);
        bundle.putBoolean("android.activity.taskOverlay", mTaskOverlay);
        bundle.putBoolean("android.activity.taskOverlayCanResume", mTaskOverlayCanResume);
        bundle.putInt("android:activity.dockCreateMode", mDockCreateMode);
        bundle.putBoolean("android:activity.disallowEnterPictureInPictureWhileLaunching", mDisallowEnterPictureInPictureWhileLaunching);
        if(mAnimSpecs != null)
            bundle.putParcelableArray("android:activity.animSpecs", mAnimSpecs);
        if(mAnimationFinishedListener != null)
            bundle.putBinder("android:activity.animationFinishedListener", mAnimationFinishedListener.asBinder());
        if(mSpecsFuture != null)
            bundle.putBinder("android:activity.specsFuture", mSpecsFuture.asBinder());
        bundle.putInt("android:activity.rotationAnimationHint", mRotationAnimationHint);
        if(mAppVerificationBundle != null)
            bundle.putBundle("android:instantapps.installerbundle", mAppVerificationBundle);
        return bundle;
_L2:
        bundle.putInt("android:activity.animEnterRes", mCustomEnterResId);
        bundle.putInt("android:activity.animExitRes", mCustomExitResId);
        ibinder = obj;
        if(mAnimationStartedListener != null)
            ibinder = mAnimationStartedListener.asBinder();
        bundle.putBinder("android:activity.animStartListener", ibinder);
        continue; /* Loop/switch isn't completed */
_L6:
        bundle.putInt("android:activity.animInPlaceRes", mCustomInPlaceResId);
        continue; /* Loop/switch isn't completed */
_L3:
        bundle.putInt("android:activity.animStartX", mStartX);
        bundle.putInt("android:activity.animStartY", mStartY);
        bundle.putInt("android:activity.animWidth", mWidth);
        bundle.putInt("android:activity.animHeight", mHeight);
        continue; /* Loop/switch isn't completed */
_L4:
        if(mThumbnail != null)
        {
            Bitmap bitmap = mThumbnail.copy(android.graphics.Bitmap.Config.HARDWARE, false);
            if(bitmap != null)
                bundle.putParcelable("android:activity.animThumbnail", bitmap.createGraphicBufferHandle());
            else
                Slog.w("ActivityOptions", "Failed to copy thumbnail");
        }
        bundle.putInt("android:activity.animStartX", mStartX);
        bundle.putInt("android:activity.animStartY", mStartY);
        bundle.putInt("android:activity.animWidth", mWidth);
        bundle.putInt("android:activity.animHeight", mHeight);
        if(mAnimationStartedListener != null)
            ibinder = mAnimationStartedListener.asBinder();
        bundle.putBinder("android:activity.animStartListener", ibinder);
        continue; /* Loop/switch isn't completed */
_L5:
        if(mTransitionReceiver != null)
            bundle.putParcelable("android:activity.transitionCompleteListener", mTransitionReceiver);
        bundle.putBoolean("android:activity.transitionIsReturning", mIsReturning);
        bundle.putStringArrayList("android:activity.sharedElementNames", mSharedElementNames);
        bundle.putParcelable("android:activity.resultData", mResultData);
        bundle.putInt("android:activity.resultCode", mResultCode);
        bundle.putInt("android:activity.exitCoordinatorIndex", mExitCoordinatorIndex);
        if(true) goto _L1; else goto _L7
_L7:
    }

    public String toString()
    {
        return (new StringBuilder()).append("ActivityOptions(").append(hashCode()).append("), mPackageName=").append(mPackageName).append(", mAnimationType=").append(mAnimationType).append(", mStartX=").append(mStartX).append(", mStartY=").append(mStartY).append(", mWidth=").append(mWidth).append(", mHeight=").append(mHeight).toString();
    }

    public void update(ActivityOptions activityoptions)
    {
        if(activityoptions.mPackageName != null)
            mPackageName = activityoptions.mPackageName;
        mUsageTimeReport = activityoptions.mUsageTimeReport;
        mTransitionReceiver = null;
        mSharedElementNames = null;
        mIsReturning = false;
        mResultData = null;
        mResultCode = 0;
        mExitCoordinatorIndex = 0;
        mAnimationType = activityoptions.mAnimationType;
        activityoptions.mAnimationType;
        JVM INSTR tableswitch 1 10: default 120
    //                   1 145
    //                   2 205
    //                   3 262
    //                   4 262
    //                   5 330
    //                   6 120
    //                   7 120
    //                   8 262
    //                   9 262
    //                   10 194;
           goto _L1 _L2 _L3 _L4 _L4 _L5 _L1 _L1 _L4 _L4 _L6
_L1:
        mAnimSpecs = activityoptions.mAnimSpecs;
        mAnimationFinishedListener = activityoptions.mAnimationFinishedListener;
        mSpecsFuture = activityoptions.mSpecsFuture;
        return;
_L2:
        mCustomEnterResId = activityoptions.mCustomEnterResId;
        mCustomExitResId = activityoptions.mCustomExitResId;
        mThumbnail = null;
        RemoteException remoteexception;
        if(mAnimationStartedListener != null)
            try
            {
                mAnimationStartedListener.sendResult(null);
            }
            catch(RemoteException remoteexception1) { }
        mAnimationStartedListener = activityoptions.mAnimationStartedListener;
        continue; /* Loop/switch isn't completed */
_L6:
        mCustomInPlaceResId = activityoptions.mCustomInPlaceResId;
        continue; /* Loop/switch isn't completed */
_L3:
        mStartX = activityoptions.mStartX;
        mStartY = activityoptions.mStartY;
        mWidth = activityoptions.mWidth;
        mHeight = activityoptions.mHeight;
        if(mAnimationStartedListener != null)
            try
            {
                mAnimationStartedListener.sendResult(null);
            }
            // Misplaced declaration of an exception variable
            catch(RemoteException remoteexception) { }
        mAnimationStartedListener = null;
        continue; /* Loop/switch isn't completed */
_L4:
        mThumbnail = activityoptions.mThumbnail;
        mStartX = activityoptions.mStartX;
        mStartY = activityoptions.mStartY;
        mWidth = activityoptions.mWidth;
        mHeight = activityoptions.mHeight;
        if(mAnimationStartedListener != null)
            try
            {
                mAnimationStartedListener.sendResult(null);
            }
            // Misplaced declaration of an exception variable
            catch(RemoteException remoteexception) { }
        mAnimationStartedListener = activityoptions.mAnimationStartedListener;
        continue; /* Loop/switch isn't completed */
_L5:
        mTransitionReceiver = activityoptions.mTransitionReceiver;
        mSharedElementNames = activityoptions.mSharedElementNames;
        mIsReturning = activityoptions.mIsReturning;
        mThumbnail = null;
        mAnimationStartedListener = null;
        mResultData = activityoptions.mResultData;
        mResultCode = activityoptions.mResultCode;
        mExitCoordinatorIndex = activityoptions.mExitCoordinatorIndex;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public static final int ANIM_CLIP_REVEAL = 11;
    public static final int ANIM_CUSTOM = 1;
    public static final int ANIM_CUSTOM_IN_PLACE = 10;
    public static final int ANIM_DEFAULT = 6;
    public static final int ANIM_LAUNCH_APP_FROM_HOME = 100;
    public static final int ANIM_LAUNCH_TASK_BEHIND = 7;
    public static final int ANIM_NONE = 0;
    public static final int ANIM_SCALE_UP = 2;
    public static final int ANIM_SCENE_TRANSITION = 5;
    public static final int ANIM_THUMBNAIL_ASPECT_SCALE_DOWN = 9;
    public static final int ANIM_THUMBNAIL_ASPECT_SCALE_UP = 8;
    public static final int ANIM_THUMBNAIL_SCALE_DOWN = 4;
    public static final int ANIM_THUMBNAIL_SCALE_UP = 3;
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";
    private static final String KEY_ANIMATION_FINISHED_LISTENER = "android:activity.animationFinishedListener";
    public static final String KEY_ANIM_ENTER_RES_ID = "android:activity.animEnterRes";
    public static final String KEY_ANIM_EXIT_RES_ID = "android:activity.animExitRes";
    public static final String KEY_ANIM_HEIGHT = "android:activity.animHeight";
    public static final String KEY_ANIM_IN_PLACE_RES_ID = "android:activity.animInPlaceRes";
    private static final String KEY_ANIM_SPECS = "android:activity.animSpecs";
    public static final String KEY_ANIM_START_LISTENER = "android:activity.animStartListener";
    public static final String KEY_ANIM_START_X = "android:activity.animStartX";
    public static final String KEY_ANIM_START_Y = "android:activity.animStartY";
    public static final String KEY_ANIM_THUMBNAIL = "android:activity.animThumbnail";
    public static final String KEY_ANIM_TYPE = "android:activity.animType";
    public static final String KEY_ANIM_WIDTH = "android:activity.animWidth";
    private static final String KEY_DISALLOW_ENTER_PICTURE_IN_PICTURE_WHILE_LAUNCHING = "android:activity.disallowEnterPictureInPictureWhileLaunching";
    private static final String KEY_DOCK_CREATE_MODE = "android:activity.dockCreateMode";
    private static final String KEY_EXIT_COORDINATOR_INDEX = "android:activity.exitCoordinatorIndex";
    private static final String KEY_INSTANT_APP_VERIFICATION_BUNDLE = "android:instantapps.installerbundle";
    public static final String KEY_LAUNCH_BOUNDS = "android:activity.launchBounds";
    private static final String KEY_LAUNCH_DISPLAY_ID = "android.activity.launchDisplayId";
    private static final String KEY_LAUNCH_STACK_ID = "android.activity.launchStackId";
    private static final String KEY_LAUNCH_TASK_ID = "android.activity.launchTaskId";
    public static final String KEY_PACKAGE_NAME = "android:activity.packageName";
    private static final String KEY_RESULT_CODE = "android:activity.resultCode";
    private static final String KEY_RESULT_DATA = "android:activity.resultData";
    private static final String KEY_ROTATION_ANIMATION_HINT = "android:activity.rotationAnimationHint";
    private static final String KEY_SPECS_FUTURE = "android:activity.specsFuture";
    private static final String KEY_TASK_OVERLAY = "android.activity.taskOverlay";
    private static final String KEY_TASK_OVERLAY_CAN_RESUME = "android.activity.taskOverlayCanResume";
    private static final String KEY_TRANSITION_COMPLETE_LISTENER = "android:activity.transitionCompleteListener";
    private static final String KEY_TRANSITION_IS_RETURNING = "android:activity.transitionIsReturning";
    private static final String KEY_TRANSITION_SHARED_ELEMENTS = "android:activity.sharedElementNames";
    private static final String KEY_USAGE_TIME_REPORT = "android:activity.usageTimeReport";
    private static final String TAG = "ActivityOptions";
    private AppTransitionAnimationSpec mAnimSpecs[];
    private IRemoteCallback mAnimationFinishedListener;
    private IRemoteCallback mAnimationStartedListener;
    private int mAnimationType;
    private Bundle mAppVerificationBundle;
    private int mCustomEnterResId;
    private int mCustomExitResId;
    private int mCustomInPlaceResId;
    private boolean mDisallowEnterPictureInPictureWhileLaunching;
    private int mDockCreateMode;
    private int mExitCoordinatorIndex;
    private int mHeight;
    private boolean mIsReturning;
    private Rect mLaunchBounds;
    private int mLaunchDisplayId;
    private int mLaunchStackId;
    private int mLaunchTaskId;
    private String mPackageName;
    private int mResultCode;
    private Intent mResultData;
    private int mRotationAnimationHint;
    private ArrayList mSharedElementNames;
    private IAppTransitionAnimationSpecsFuture mSpecsFuture;
    private int mStartX;
    private int mStartY;
    private boolean mTaskOverlay;
    private boolean mTaskOverlayCanResume;
    private Bitmap mThumbnail;
    private ResultReceiver mTransitionReceiver;
    private PendingIntent mUsageTimeReport;
    private int mWidth;
}
