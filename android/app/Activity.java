// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.app.assist.AssistContent;
import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.method.TextKeyListener;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.autofill.*;
import android.widget.Toast;
import android.widget.Toolbar;
import com.android.internal.app.*;
import com.android.internal.policy.DecorView;
import com.android.internal.policy.MiuiPhoneWindow;
import com.miui.internal.contentcatcher.IInterceptor;
import com.miui.whetstone.app.WhetstoneAppManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;
import miui.contentcatcher.InterceptorProxy;

// Referenced classes of package android.app:
//            FragmentController, ActivityTransitionState, SharedElementCallback, Dialog, 
//            Fragment, SearchManager, ActivityManager, IActivityManager, 
//            ActionBar, ActivityThread, Instrumentation, ActivityOptions, 
//            VoiceInteractor, PendingIntent, FragmentManager, PictureInPictureArgs, 
//            ActivityManagerNative, Application, TaskStackBuilder, ActivityInjector, 
//            PictureInPictureParams, LoaderManager, FragmentHostCallback, FragmentManagerNonConfig

public class Activity extends ContextThemeWrapper
    implements android.view.LayoutInflater.Factory2, android.view.Window.Callback, android.view.KeyEvent.Callback, android.view.View.OnCreateContextMenuListener, ComponentCallbacks2, android.view.Window.OnWindowDismissedCallback, android.view.Window.WindowControllerCallback, android.view.autofill.AutofillManager.AutofillClient
{
    class HostCallbacks extends FragmentHostCallback
    {

        public void onAttachFragment(Fragment fragment)
        {
            Activity.this.onAttachFragment(fragment);
        }

        public void onDump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            dump(s, filedescriptor, printwriter, as);
        }

        public View onFindViewById(int i)
        {
            return findViewById(i);
        }

        public Activity onGetHost()
        {
            return Activity.this;
        }

        public volatile Object onGetHost()
        {
            return onGetHost();
        }

        public LayoutInflater onGetLayoutInflater()
        {
            LayoutInflater layoutinflater = getLayoutInflater();
            if(onUseFragmentManagerInflaterFactory())
                return layoutinflater.cloneInContext(Activity.this);
            else
                return layoutinflater;
        }

        public int onGetWindowAnimations()
        {
            Window window = getWindow();
            int i;
            if(window == null)
                i = 0;
            else
                i = window.getAttributes().windowAnimations;
            return i;
        }

        public boolean onHasView()
        {
            boolean flag = false;
            Window window = getWindow();
            boolean flag1 = flag;
            if(window != null)
            {
                flag1 = flag;
                if(window.peekDecorView() != null)
                    flag1 = true;
            }
            return flag1;
        }

        public boolean onHasWindowAnimations()
        {
            boolean flag;
            if(getWindow() != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void onInvalidateOptionsMenu()
        {
            invalidateOptionsMenu();
        }

        public void onRequestPermissionsFromFragment(Fragment fragment, String as[], int i)
        {
            fragment = (new StringBuilder()).append("@android:requestPermissions:").append(fragment.mWho).toString();
            as = getPackageManager().buildRequestPermissionsIntent(as);
            startActivityForResult(fragment, as, i, null);
        }

        public boolean onShouldSaveFragmentState(Fragment fragment)
        {
            return isFinishing() ^ true;
        }

        public void onStartActivityAsUserFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle, UserHandle userhandle)
        {
            startActivityAsUserFromFragment(fragment, intent, i, bundle, userhandle);
        }

        public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
        {
            startActivityFromFragment(fragment, intent, i, bundle);
        }

        public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intentsender, int i, Intent intent, int j, int k, int l, 
                Bundle bundle)
            throws android.content.IntentSender.SendIntentException
        {
            if(mParent != null) goto _L2; else goto _L1
_L1:
            Activity._2D_wrap0(Activity.this, intentsender, fragment.mWho, i, intent, j, k, bundle);
_L4:
            return;
_L2:
            if(bundle != null)
                mParent.startIntentSenderFromChildFragment(fragment, intentsender, i, intent, j, k, l, bundle);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public boolean onUseFragmentManagerInflaterFactory()
        {
            boolean flag;
            if(getApplicationInfo().targetSdkVersion >= 21)
                flag = true;
            else
                flag = false;
            return flag;
        }

        final Activity this$0;

        public HostCallbacks()
        {
            this$0 = Activity.this;
            super(Activity.this);
        }
    }

    private static final class ManagedCursor
    {

        static Cursor _2D_get0(ManagedCursor managedcursor)
        {
            return managedcursor.mCursor;
        }

        static boolean _2D_get1(ManagedCursor managedcursor)
        {
            return managedcursor.mReleased;
        }

        static boolean _2D_get2(ManagedCursor managedcursor)
        {
            return managedcursor.mUpdated;
        }

        static boolean _2D_set0(ManagedCursor managedcursor, boolean flag)
        {
            managedcursor.mReleased = flag;
            return flag;
        }

        static boolean _2D_set1(ManagedCursor managedcursor, boolean flag)
        {
            managedcursor.mUpdated = flag;
            return flag;
        }

        private final Cursor mCursor;
        private boolean mReleased;
        private boolean mUpdated;

        ManagedCursor(Cursor cursor)
        {
            mCursor = cursor;
            mReleased = false;
            mUpdated = false;
        }
    }

    private static class ManagedDialog
    {

        Bundle mArgs;
        Dialog mDialog;

        private ManagedDialog()
        {
        }

        ManagedDialog(ManagedDialog manageddialog)
        {
            this();
        }
    }

    static final class NonConfigurationInstances
    {

        Object activity;
        HashMap children;
        FragmentManagerNonConfig fragments;
        ArrayMap loaders;
        VoiceInteractor voiceInteractor;

        NonConfigurationInstances()
        {
        }
    }

    public static interface TranslucentConversionListener
    {

        public abstract void onTranslucentConversionComplete(boolean flag);
    }


    static void _2D_wrap0(Activity activity, IntentSender intentsender, String s, int i, Intent intent, int j, int k, Bundle bundle)
    {
        activity.startIntentSenderForResultInner(intentsender, s, i, intent, j, k, bundle);
    }

    public Activity()
    {
        mDoReportFullyDrawn = true;
        mCanEnterPictureInPicture = false;
        mTemporaryPause = false;
        mChangingConfigurations = false;
        mDecor = null;
        mWindowAdded = false;
        mVisibleFromServer = false;
        mVisibleFromClient = true;
        mActionBar = null;
        mTitleColor = 0;
        mResultCode = 0;
        mResultData = null;
        mTitleReady = false;
        mActionModeTypeStarting = 0;
        mDefaultKeyMode = 0;
        mDefaultKeySsb = null;
        mTaskDescription = new ActivityManager.TaskDescription();
        mActivityTransitionState = new ActivityTransitionState();
        mEnterTransitionListener = SharedElementCallback.NULL_CALLBACK;
        mExitTransitionListener = SharedElementCallback.NULL_CALLBACK;
        mLastAutofillId = 0x3fffffff;
        mInterceptor = null;
    }

    private void cancelInputsAndStartExitTransition(Bundle bundle)
    {
        View view;
        if(mWindow != null)
            view = mWindow.peekDecorView();
        else
            view = null;
        if(view != null)
            view.cancelPendingInputEvents();
        if(bundle != null && isTopOfTask() ^ true)
            mActivityTransitionState.startExitOutTransition(this, bundle);
    }

    private Dialog createDialog(Integer integer, Bundle bundle, Bundle bundle1)
    {
        integer = onCreateDialog(integer.intValue(), bundle1);
        if(integer == null)
        {
            return null;
        } else
        {
            integer.dispatchOnCreate(bundle);
            return integer;
        }
    }

    private boolean deviceSupportsPictureInPictureMode()
    {
        return getPackageManager().hasSystemFeature("android.software.picture_in_picture");
    }

    private void dispatchRequestPermissionsResult(int i, Intent intent)
    {
        mHasCurrentPermissionsRequest = false;
        String as[];
        if(intent != null)
            as = intent.getStringArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES");
        else
            as = new String[0];
        if(intent != null)
            intent = intent.getIntArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS");
        else
            intent = new int[0];
        onRequestPermissionsResult(i, as, intent);
    }

    private void dispatchRequestPermissionsResultToFragment(int i, Intent intent, Fragment fragment)
    {
        String as[];
        if(intent != null)
            as = intent.getStringArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES");
        else
            as = new String[0];
        if(intent != null)
            intent = intent.getIntArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS");
        else
            intent = new int[0];
        fragment.onRequestPermissionsResult(i, as, intent);
    }

    private void ensureSearchManager()
    {
        if(mSearchManager != null)
            return;
        try
        {
            SearchManager searchmanager = JVM INSTR new #387 <Class SearchManager>;
            searchmanager.SearchManager(this, null);
            mSearchManager = searchmanager;
            return;
        }
        catch(android.os.ServiceManager.ServiceNotFoundException servicenotfoundexception)
        {
            throw new IllegalStateException(servicenotfoundexception);
        }
    }

    private void finish(int i)
    {
        if(mParent != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        int j;
        Intent intent;
        j = mResultCode;
        intent = mResultData;
        this;
        JVM INSTR monitorexit ;
        if(intent == null)
            break MISSING_BLOCK_LABEL_30;
        intent.prepareToLeaveProcess(this);
        if(ActivityManager.getService().finishActivity(mToken, j, intent, i))
            mFinished = true;
_L4:
        if(mIntent != null && mIntent.hasExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN"))
            getAutofillManager().onPendingSaveUi(2, mIntent.getIBinderExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN"));
        return;
        Object obj;
        obj;
        throw obj;
_L2:
        mParent.finishFromChild(this);
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private AutofillManager getAutofillManager()
    {
        if(mAutofillManager == null)
            mAutofillManager = (AutofillManager)getSystemService(android/view/autofill/AutofillManager);
        return mAutofillManager;
    }

    private static native String getDlWarning();

    private void initWindowDecorActionBar()
    {
        Window window = getWindow();
        window.getDecorView();
        if(isChild() || window.hasFeature(8) ^ true || mActionBar != null)
        {
            return;
        } else
        {
            mActionBar = new WindowDecorActionBar(this);
            mActionBar.setDefaultDisplayHomeAsUpEnabled(mEnableDefaultActionBarUp);
            mWindow.setDefaultIcon(mActivityInfo.getIconResource());
            mWindow.setDefaultLogo(mActivityInfo.getLogoResource());
            return;
        }
    }

    private boolean isTopOfTask()
    {
        if(mToken == null || mWindow == null)
            return false;
        boolean flag;
        try
        {
            flag = ActivityManager.getService().isTopOfTask(getActivityToken());
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    private IllegalArgumentException missingDialog(int i)
    {
        return new IllegalArgumentException((new StringBuilder()).append("no dialog with id ").append(i).append(" was ever ").append("shown via Activity#showDialog").toString());
    }

    private void restoreHasCurrentPermissionRequest(Bundle bundle)
    {
        if(bundle != null)
            mHasCurrentPermissionsRequest = bundle.getBoolean("android:hasCurrentPermissionsRequest", false);
    }

    private void restoreManagedDialogs(Bundle bundle)
    {
        Bundle bundle1 = bundle.getBundle("android:savedDialogs");
        if(bundle1 == null)
            return;
        int ai[] = bundle1.getIntArray("android:savedDialogIds");
        int i = ai.length;
        mManagedDialogs = new SparseArray(i);
        for(int j = 0; j < i; j++)
        {
            Integer integer = Integer.valueOf(ai[j]);
            Bundle bundle2 = bundle1.getBundle(savedDialogKeyFor(integer.intValue()));
            if(bundle2 == null)
                continue;
            bundle = new ManagedDialog(null);
            bundle.mArgs = bundle1.getBundle(savedDialogArgsKeyFor(integer.intValue()));
            bundle.mDialog = createDialog(integer, bundle2, ((ManagedDialog) (bundle)).mArgs);
            if(((ManagedDialog) (bundle)).mDialog != null)
            {
                mManagedDialogs.put(integer.intValue(), bundle);
                onPrepareDialog(integer.intValue(), ((ManagedDialog) (bundle)).mDialog, ((ManagedDialog) (bundle)).mArgs);
                ((ManagedDialog) (bundle)).mDialog.onRestoreInstanceState(bundle2);
            }
        }

    }

    private void saveManagedDialogs(Bundle bundle)
    {
        if(mManagedDialogs == null)
            return;
        int i = mManagedDialogs.size();
        if(i == 0)
            return;
        Bundle bundle1 = new Bundle();
        int ai[] = new int[mManagedDialogs.size()];
        for(int j = 0; j < i; j++)
        {
            int k = mManagedDialogs.keyAt(j);
            ai[j] = k;
            ManagedDialog manageddialog = (ManagedDialog)mManagedDialogs.valueAt(j);
            bundle1.putBundle(savedDialogKeyFor(k), manageddialog.mDialog.onSaveInstanceState());
            if(manageddialog.mArgs != null)
                bundle1.putBundle(savedDialogArgsKeyFor(k), manageddialog.mArgs);
        }

        bundle1.putIntArray("android:savedDialogIds", ai);
        bundle.putBundle("android:savedDialogs", bundle1);
    }

    private static String savedDialogArgsKeyFor(int i)
    {
        return (new StringBuilder()).append("android:dialog_args_").append(i).toString();
    }

    private static String savedDialogKeyFor(int i)
    {
        return (new StringBuilder()).append("android:dialog_").append(i).toString();
    }

    private void startIntentSenderForResultInner(IntentSender intentsender, String s, int i, Intent intent, int j, int k, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        String s1;
        s1 = null;
        if(intent == null)
            break MISSING_BLOCK_LABEL_31;
        intent.migrateExtraStreamToClipData();
        intent.prepareToLeaveProcess(this);
        s1 = intent.resolveTypeIfNeeded(getContentResolver());
        IActivityManager iactivitymanager;
        ActivityThread.ApplicationThread applicationthread;
        iactivitymanager = ActivityManager.getService();
        applicationthread = mMainThread.getApplicationThread();
        if(intentsender == null) goto _L2; else goto _L1
_L1:
        android.content.IIntentSender iintentsender = intentsender.getTarget();
_L5:
        if(intentsender == null) goto _L4; else goto _L3
_L3:
        intentsender = intentsender.getWhitelistToken();
_L6:
        j = iactivitymanager.startActivityIntentSender(applicationthread, iintentsender, intentsender, intent, s1, mToken, s, i, j, k, bundle);
        if(j != -96)
            break MISSING_BLOCK_LABEL_133;
        try
        {
            intentsender = JVM INSTR new #611 <Class android.content.IntentSender$SendIntentException>;
            intentsender.android.content.IntentSender.SendIntentException();
            throw intentsender;
        }
        // Misplaced declaration of an exception variable
        catch(IntentSender intentsender) { }
_L7:
        if(i >= 0)
            mStartedActivity = true;
        return;
_L2:
        iintentsender = null;
          goto _L5
_L4:
        intentsender = null;
          goto _L6
        Instrumentation.checkStartActivityResult(j, null);
          goto _L7
    }

    private void storeHasCurrentPermissionRequest(Bundle bundle)
    {
        if(bundle != null && mHasCurrentPermissionsRequest)
            bundle.putBoolean("android:hasCurrentPermissionsRequest", true);
    }

    private Bundle transferSpringboardActivityOptions(Bundle bundle)
    {
        if(bundle == null && mWindow != null && mWindow.isActive() ^ true)
        {
            ActivityOptions activityoptions = getActivityOptions();
            if(activityoptions != null && activityoptions.getAnimationType() == 5)
                return activityoptions.toBundle();
        }
        return bundle;
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getWindow().addContentView(view, layoutparams);
        initWindowDecorActionBar();
    }

    final void attach(Context context, ActivityThread activitythread, Instrumentation instrumentation, IBinder ibinder, int i, Application application, Intent intent, 
            ActivityInfo activityinfo, CharSequence charsequence, Activity activity, String s, NonConfigurationInstances nonconfigurationinstances, Configuration configuration, String s1, 
            IVoiceInteractor ivoiceinteractor, Window window, android.view.ViewRootImpl.ActivityConfigCallback activityconfigcallback)
    {
        attachBaseContext(context);
        mFragments.attachHost(null);
        mWindow = new MiuiPhoneWindow(this, window, activityconfigcallback);
        mWindow.setWindowControllerCallback(this);
        mWindow.setCallback(this);
        mWindow.setOnWindowDismissedCallback(this);
        mWindow.getLayoutInflater().setPrivateFactory(this);
        if(activityinfo.softInputMode != 0)
            mWindow.setSoftInputMode(activityinfo.softInputMode);
        if(activityinfo.uiOptions != 0)
            mWindow.setUiOptions(activityinfo.uiOptions);
        mUiThread = Thread.currentThread();
        mMainThread = activitythread;
        mInstrumentation = instrumentation;
        mToken = ibinder;
        mIdent = i;
        mApplication = application;
        mIntent = intent;
        mReferrer = s1;
        mComponent = intent.getComponent();
        mActivityInfo = activityinfo;
        mTitle = charsequence;
        mParent = activity;
        mEmbeddedID = s;
        mLastNonConfigurationInstances = nonconfigurationinstances;
        boolean flag;
        if(ivoiceinteractor != null)
            if(nonconfigurationinstances != null)
                mVoiceInteractor = nonconfigurationinstances.voiceInteractor;
            else
                mVoiceInteractor = new VoiceInteractor(ivoiceinteractor, this, this, Looper.myLooper());
        activitythread = mWindow;
        instrumentation = (WindowManager)context.getSystemService("window");
        ibinder = mToken;
        context = mComponent.flattenToString();
        if((activityinfo.flags & 0x200) != 0)
            flag = true;
        else
            flag = false;
        activitythread.setWindowManager(instrumentation, ibinder, context, flag);
        if(mParent != null)
            mWindow.setContainer(mParent.getWindow());
        mWindowManager = mWindow.getWindowManager();
        mCurrentConfig = configuration;
        mWindow.setColorMode(activityinfo.colorMode);
    }

    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        context.setAutofillClient(this);
    }

    public final void autofillCallbackAuthenticate(int i, IntentSender intentsender, Intent intent)
    {
        startIntentSenderForResultInner(intentsender, "@android:autoFillAuth:", i, intent, 0, 0, null);
_L1:
        return;
        intent;
        Log.e("Activity", (new StringBuilder()).append("authenticate() failed for intent:").append(intentsender).toString(), intent);
          goto _L1
    }

    public final boolean autofillCallbackRequestHideFillUi()
    {
        if(mAutofillPopupWindow == null)
        {
            return false;
        } else
        {
            mAutofillPopupWindow.dismiss();
            mAutofillPopupWindow = null;
            return true;
        }
    }

    public final boolean autofillCallbackRequestShowFillUi(View view, int i, int j, Rect rect, IAutofillWindowPresenter iautofillwindowpresenter)
    {
        boolean flag = false;
        boolean flag1;
        if(mAutofillPopupWindow == null)
        {
            flag1 = false;
            mAutofillPopupWindow = new AutofillPopupWindow(iautofillwindowpresenter);
        } else
        {
            flag1 = mAutofillPopupWindow.isShowing();
        }
        mAutofillPopupWindow.update(view, 0, 0, i, j, rect);
        if(!flag1)
            flag = mAutofillPopupWindow.isShowing();
        return flag;
    }

    public final void autofillCallbackResetableStateAvailable()
    {
        mAutoFillResetNeeded = true;
    }

    public boolean canStartActivityForResult()
    {
        return true;
    }

    public void closeContextMenu()
    {
        if(mWindow.hasFeature(6))
            mWindow.closePanel(6);
    }

    public void closeOptionsMenu()
    {
        if(mWindow.hasFeature(0) && (mActionBar == null || mActionBar.closeOptionsMenu() ^ true))
            mWindow.closePanel(0);
    }

    public void convertFromTranslucent()
    {
        mTranslucentCallback = null;
        if(ActivityManager.getService().convertFromTranslucent(mToken))
            WindowManagerGlobal.getInstance().changeCanvasOpacity(mToken, true);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean convertToTranslucent(TranslucentConversionListener translucentconversionlistener, ActivityOptions activityoptions)
    {
        Object obj = null;
        IActivityManager iactivitymanager;
        IBinder ibinder;
        mTranslucentCallback = translucentconversionlistener;
        iactivitymanager = ActivityManager.getService();
        ibinder = mToken;
        if(activityoptions != null) goto _L2; else goto _L1
_L1:
        translucentconversionlistener = obj;
_L3:
        mChangeCanvasToTranslucent = iactivitymanager.convertToTranslucent(ibinder, translucentconversionlistener);
        WindowManagerGlobal.getInstance().changeCanvasOpacity(mToken, false);
        boolean flag = true;
_L4:
        if(!mChangeCanvasToTranslucent && mTranslucentCallback != null)
            mTranslucentCallback.onTranslucentConversionComplete(flag);
        return mChangeCanvasToTranslucent;
_L2:
        translucentconversionlistener = activityoptions.toBundle();
          goto _L3
        translucentconversionlistener;
        mChangeCanvasToTranslucent = false;
        flag = false;
          goto _L4
    }

    public PendingIntent createPendingResult(int i, Intent intent, int j)
    {
        String s = getPackageName();
        IActivityManager iactivitymanager;
        IBinder ibinder;
        intent.prepareToLeaveProcess(this);
        iactivitymanager = ActivityManager.getService();
        if(mParent != null)
            break MISSING_BLOCK_LABEL_84;
        ibinder = mToken;
_L1:
        String s1 = mEmbeddedID;
        int k = UserHandle.myUserId();
        intent = iactivitymanager.getIntentSender(3, s, ibinder, s1, i, new Intent[] {
            intent
        }, null, j, null, k);
        if(intent != null)
            try
            {
                intent = new PendingIntent(intent);
            }
            // Misplaced declaration of an exception variable
            catch(Intent intent)
            {
                return null;
            }
        else
            intent = null;
        return intent;
        ibinder = mParent.mToken;
          goto _L1
    }

    public final void dismissDialog(int i)
    {
        if(mManagedDialogs == null)
            throw missingDialog(i);
        ManagedDialog manageddialog = (ManagedDialog)mManagedDialogs.get(i);
        if(manageddialog == null)
        {
            throw missingDialog(i);
        } else
        {
            manageddialog.mDialog.dismiss();
            return;
        }
    }

    public final void dismissKeyboardShortcutsHelper()
    {
        Intent intent = new Intent("com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS");
        intent.setPackage("com.android.systemui");
        sendBroadcastAsUser(intent, UserHandle.SYSTEM);
    }

    void dispatchActivityResult(String s, int i, int j, Intent intent)
    {
        mFragments.noteStateNotSaved();
        if(s != null) goto _L2; else goto _L1
_L1:
        onActivityResult(i, j, intent);
_L4:
        return;
_L2:
        if(s.startsWith("@android:requestPermissions:"))
        {
            s = s.substring("@android:requestPermissions:".length());
            if(TextUtils.isEmpty(s))
            {
                dispatchRequestPermissionsResult(i, intent);
            } else
            {
                s = mFragments.findFragmentByWho(s);
                if(s != null)
                    dispatchRequestPermissionsResultToFragment(i, intent, s);
            }
            continue; /* Loop/switch isn't completed */
        }
        if(s.startsWith("@android:view:"))
        {
            Iterator iterator = WindowManagerGlobal.getInstance().getRootViews(getActivityToken()).iterator();
            ViewRootImpl viewrootimpl;
            do
            {
                if(!iterator.hasNext())
                    continue; /* Loop/switch isn't completed */
                viewrootimpl = (ViewRootImpl)iterator.next();
            } while(viewrootimpl.getView() == null || !viewrootimpl.getView().dispatchActivityResult(s, i, j, intent));
            return;
        }
        if(s.startsWith("@android:autoFillAuth:"))
        {
            if(j == -1)
                s = intent;
            else
                s = null;
            getAutofillManager().onAuthenticationResult(i, s);
        } else
        {
            s = mFragments.findFragmentByWho(s);
            if(s != null)
                s.onActivityResult(i, j, intent);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void dispatchEnterAnimationComplete()
    {
        onEnterAnimationComplete();
        if(getWindow() != null && getWindow().getDecorView() != null)
            getWindow().getDecorView().getViewTreeObserver().dispatchOnEnterAnimationComplete();
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        onUserInteraction();
        if(getWindow().superDispatchGenericMotionEvent(motionevent))
            return true;
        else
            return onGenericMotionEvent(motionevent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        android.view.KeyEvent.DispatcherState dispatcherstate = null;
        onUserInteraction();
        if(keyevent.getKeyCode() == 82 && mActionBar != null && mActionBar.onMenuKeyEvent(keyevent))
            return true;
        Window window = getWindow();
        if(window.superDispatchKeyEvent(keyevent))
            return true;
        View view = mDecor;
        View view1 = view;
        if(view == null)
            view1 = window.getDecorView();
        if(view1 != null)
            dispatcherstate = view1.getKeyDispatcherState();
        return keyevent.dispatch(this, dispatcherstate, this);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        onUserInteraction();
        if(getWindow().superDispatchKeyShortcutEvent(keyevent))
            return true;
        else
            return onKeyShortcut(keyevent.getKeyCode(), keyevent);
    }

    void dispatchMovedToDisplay(int i, Configuration configuration)
    {
        updateDisplay(i);
        onMovedToDisplay(i, configuration);
    }

    final void dispatchMultiWindowModeChanged(boolean flag, Configuration configuration)
    {
        mFragments.dispatchMultiWindowModeChanged(flag, configuration);
        if(mWindow != null)
            mWindow.onMultiWindowModeChanged();
        onMultiWindowModeChanged(flag, configuration);
    }

    final void dispatchPictureInPictureModeChanged(boolean flag, Configuration configuration)
    {
        mFragments.dispatchPictureInPictureModeChanged(flag, configuration);
        if(mWindow != null)
            mWindow.onPictureInPictureModeChanged(flag);
        onPictureInPictureModeChanged(flag, configuration);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        accessibilityevent.setClassName(getClass().getName());
        accessibilityevent.setPackageName(getPackageName());
        Object obj = getWindow().getAttributes();
        boolean flag;
        if(((android.view.ViewGroup.LayoutParams) (obj)).width == -1)
        {
            if(((android.view.ViewGroup.LayoutParams) (obj)).height == -1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        accessibilityevent.setFullScreen(flag);
        obj = getTitle();
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            accessibilityevent.getText().add(obj);
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if(motionevent.getAction() == 0)
            onUserInteraction();
        if(getWindow().superDispatchTouchEvent(motionevent))
            return true;
        else
            return onTouchEvent(motionevent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        onUserInteraction();
        if(getWindow().superDispatchTrackballEvent(motionevent))
            return true;
        else
            return onTrackballEvent(motionevent);
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dumpInner(s, filedescriptor, printwriter, as);
    }

    void dumpInner(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print(s);
        printwriter.print("Local Activity ");
        printwriter.print(Integer.toHexString(System.identityHashCode(this)));
        printwriter.println(" State:");
        String s1 = (new StringBuilder()).append(s).append("  ").toString();
        printwriter.print(s1);
        printwriter.print("mResumed=");
        printwriter.print(mResumed);
        printwriter.print(" mStopped=");
        printwriter.print(mStopped);
        printwriter.print(" mFinished=");
        printwriter.println(mFinished);
        printwriter.print(s1);
        printwriter.print("mChangingConfigurations=");
        printwriter.println(mChangingConfigurations);
        printwriter.print(s1);
        printwriter.print("mCurrentConfig=");
        printwriter.println(mCurrentConfig);
        mFragments.dumpLoaders(s1, filedescriptor, printwriter, as);
        mFragments.getFragmentManager().dump(s1, filedescriptor, printwriter, as);
        if(mVoiceInteractor != null)
            mVoiceInteractor.dump(s1, filedescriptor, printwriter, as);
        if(getWindow() != null && getWindow().peekDecorView() != null && getWindow().peekDecorView().getViewRootImpl() != null)
            getWindow().peekDecorView().getViewRootImpl().dump(s, filedescriptor, printwriter, as);
        mHandler.getLooper().dump(new PrintWriterPrinter(printwriter), s);
        filedescriptor = getAutofillManager();
        if(filedescriptor != null)
            filedescriptor.dump(s, printwriter);
    }

    public void enterPictureInPictureMode()
    {
        enterPictureInPictureMode((new PictureInPictureParams.Builder()).build());
    }

    public boolean enterPictureInPictureMode(PictureInPictureArgs pictureinpictureargs)
    {
        return enterPictureInPictureMode(PictureInPictureArgs.convert(pictureinpictureargs));
    }

    public boolean enterPictureInPictureMode(PictureInPictureParams pictureinpictureparams)
    {
        if(!deviceSupportsPictureInPictureMode())
            return false;
        if(pictureinpictureparams == null)
            try
            {
                pictureinpictureparams = JVM INSTR new #506 <Class IllegalArgumentException>;
                pictureinpictureparams.IllegalArgumentException("Expected non-null picture-in-picture params");
                throw pictureinpictureparams;
            }
            // Misplaced declaration of an exception variable
            catch(PictureInPictureParams pictureinpictureparams)
            {
                return false;
            }
        boolean flag;
        if(!mCanEnterPictureInPicture)
        {
            pictureinpictureparams = JVM INSTR new #392 <Class IllegalStateException>;
            pictureinpictureparams.IllegalStateException("Activity must be resumed to enter picture-in-picture");
            throw pictureinpictureparams;
        }
        flag = ActivityManagerNative.getDefault().enterPictureInPictureMode(mToken, pictureinpictureparams);
        return flag;
    }

    public void enterPictureInPictureModeIfPossible()
    {
        if(mActivityInfo.supportsPictureInPicture())
            enterPictureInPictureMode();
    }

    public void exitFreeformMode()
        throws RemoteException
    {
        ActivityManager.getService().exitFreeformMode(mToken);
    }

    public View findViewByAutofillIdTraversal(int i)
    {
        ArrayList arraylist = WindowManagerGlobal.getInstance().getRootViews(getActivityToken());
        for(int j = 0; j < arraylist.size(); j++)
        {
            View view = ((ViewRootImpl)arraylist.get(j)).getView();
            if(view == null)
                continue;
            view = view.findViewByAutofillIdTraversal(i);
            if(view != null)
                return view;
        }

        return null;
    }

    public View findViewById(int i)
    {
        return getWindow().findViewById(i);
    }

    public View[] findViewsByAutofillIdTraversal(int ai[])
    {
        View aview[] = new View[ai.length];
        ArrayList arraylist = WindowManagerGlobal.getInstance().getRootViews(getActivityToken());
        for(int i = 0; i < arraylist.size(); i++)
        {
            View view = ((ViewRootImpl)arraylist.get(i)).getView();
            if(view == null)
                continue;
            for(int j = 0; j < ai.length; j++)
                if(aview[j] == null)
                    aview[j] = view.findViewByAutofillIdTraversal(ai[j]);

        }

        return aview;
    }

    public void finish()
    {
        finish(0);
    }

    public void finishActivity(int i)
    {
        if(mParent != null) goto _L2; else goto _L1
_L1:
        ActivityManager.getService().finishSubActivity(mToken, mEmbeddedID, i);
_L4:
        return;
_L2:
        mParent.finishActivityFromChild(this, i);
        continue; /* Loop/switch isn't completed */
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void finishActivityFromChild(Activity activity, int i)
    {
        ActivityManager.getService().finishSubActivity(mToken, activity.mEmbeddedID, i);
_L2:
        return;
        activity;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void finishAffinity()
    {
        if(mParent != null)
            throw new IllegalStateException("Can not be called from an embedded activity");
        if(mResultCode != 0 || mResultData != null)
            throw new IllegalStateException("Can not be called to deliver a result");
        if(ActivityManager.getService().finishActivityAffinity(mToken))
            mFinished = true;
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void finishAfterTransition()
    {
        if(!mActivityTransitionState.startExitBackTransition(this))
            finish();
    }

    public void finishAndRemoveTask()
    {
        finish(1);
    }

    public void finishFromChild(Activity activity)
    {
        finish();
    }

    public ActionBar getActionBar()
    {
        initWindowDecorActionBar();
        return mActionBar;
    }

    ActivityOptions getActivityOptions()
    {
        ActivityOptions activityoptions;
        try
        {
            activityoptions = ActivityOptions.fromBundle(ActivityManager.getService().getActivityOptions(mToken));
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return activityoptions;
    }

    public final IBinder getActivityToken()
    {
        IBinder ibinder;
        if(mParent != null)
            ibinder = mParent.getActivityToken();
        else
            ibinder = mToken;
        return ibinder;
    }

    public final Application getApplication()
    {
        return mApplication;
    }

    public final android.view.autofill.AutofillManager.AutofillClient getAutofillClient()
    {
        return this;
    }

    public ComponentName getCallingActivity()
    {
        ComponentName componentname;
        try
        {
            componentname = ActivityManager.getService().getCallingActivity(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return componentname;
    }

    public String getCallingPackage()
    {
        String s;
        try
        {
            s = ActivityManager.getService().getCallingPackage(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return s;
    }

    public int getChangingConfigurations()
    {
        return mConfigChangeFlags;
    }

    public ComponentName getComponentName()
    {
        return mComponent;
    }

    public ComponentName getComponentNameForAutofill()
    {
        return mComponent;
    }

    public Scene getContentScene()
    {
        return getWindow().getContentScene();
    }

    public TransitionManager getContentTransitionManager()
    {
        return getWindow().getTransitionManager();
    }

    public View getCurrentFocus()
    {
        View view = null;
        if(mWindow != null)
            view = mWindow.getCurrentFocus();
        return view;
    }

    public FragmentManager getFragmentManager()
    {
        return mFragments.getFragmentManager();
    }

    public Intent getIntent()
    {
        return mIntent;
    }

    public IInterceptor getInterceptor()
    {
        return mInterceptor;
    }

    HashMap getLastNonConfigurationChildInstances()
    {
        HashMap hashmap = null;
        if(mLastNonConfigurationInstances != null)
            hashmap = mLastNonConfigurationInstances.children;
        return hashmap;
    }

    public Object getLastNonConfigurationInstance()
    {
        Object obj = null;
        if(mLastNonConfigurationInstances != null)
            obj = mLastNonConfigurationInstances.activity;
        return obj;
    }

    public LayoutInflater getLayoutInflater()
    {
        return getWindow().getLayoutInflater();
    }

    public LoaderManager getLoaderManager()
    {
        return mFragments.getLoaderManager();
    }

    public String getLocalClassName()
    {
        String s = getPackageName();
        String s1 = mComponent.getClassName();
        int i;
        for(i = s.length(); !s1.startsWith(s) || s1.length() <= i || s1.charAt(i) != '.';)
            return s1;

        return s1.substring(i + 1);
    }

    public int getMaxNumPictureInPictureActions()
    {
        int i;
        try
        {
            i = ActivityManagerNative.getDefault().getMaxNumPictureInPictureActions(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        return i;
    }

    public final MediaController getMediaController()
    {
        return getWindow().getMediaController();
    }

    public MenuInflater getMenuInflater()
    {
        if(mMenuInflater == null)
        {
            initWindowDecorActionBar();
            if(mActionBar != null)
                mMenuInflater = new MenuInflater(mActionBar.getThemedContext(), this);
            else
                mMenuInflater = new MenuInflater(this);
        }
        return mMenuInflater;
    }

    public int getNextAutofillId()
    {
        if(mLastAutofillId == 0x7ffffffe)
            mLastAutofillId = 0x3fffffff;
        mLastAutofillId = mLastAutofillId + 1;
        return mLastAutofillId;
    }

    public final Activity getParent()
    {
        return mParent;
    }

    public Intent getParentActivityIntent()
    {
        String s;
        ComponentName componentname;
        s = mActivityInfo.parentActivityName;
        if(TextUtils.isEmpty(s))
            return null;
        componentname = new ComponentName(this, s);
        if(getPackageManager().getActivityInfo(componentname, 0).parentActivityName != null) goto _L2; else goto _L1
_L1:
        Intent intent = Intent.makeMainActivity(componentname);
_L4:
        return intent;
_L2:
        try
        {
            intent = JVM INSTR new #359 <Class Intent>;
            intent.Intent();
            intent = intent.setComponent(componentname);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.e("Activity", (new StringBuilder()).append("getParentActivityIntent: bad parentActivityName '").append(s).append("' in manifest").toString());
            return null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public SharedPreferences getPreferences(int i)
    {
        return getSharedPreferences(getLocalClassName(), i);
    }

    public Uri getReferrer()
    {
        Object obj = getIntent();
        Uri uri = (Uri)((Intent) (obj)).getParcelableExtra("android.intent.extra.REFERRER");
        if(uri != null)
            return uri;
        obj = ((Intent) (obj)).getStringExtra("android.intent.extra.REFERRER_NAME");
        if(obj == null)
            break MISSING_BLOCK_LABEL_51;
        obj = Uri.parse(((String) (obj)));
        return ((Uri) (obj));
        BadParcelableException badparcelableexception;
        badparcelableexception;
        Log.w("Activity", "Cannot read referrer from intent; intent extras contain unknown custom Parcelable objects");
        if(mReferrer != null)
            return (new android.net.Uri.Builder()).scheme("android-app").authority(mReferrer).build();
        else
            return null;
    }

    public int getRequestedOrientation()
    {
        if(mParent == null)
        {
            int i;
            try
            {
                i = ActivityManager.getService().getRequestedOrientation(mToken);
            }
            catch(RemoteException remoteexception)
            {
                return -1;
            }
            return i;
        } else
        {
            return mParent.getRequestedOrientation();
        }
    }

    public final SearchEvent getSearchEvent()
    {
        return mSearchEvent;
    }

    public Object getSystemService(String s)
    {
        if(getBaseContext() == null)
            throw new IllegalStateException("System services not available to Activities before onCreate()");
        if("window".equals(s))
            return mWindowManager;
        if("search".equals(s))
        {
            ensureSearchManager();
            return mSearchManager;
        } else
        {
            return super.getSystemService(s);
        }
    }

    public int getTaskId()
    {
        int i;
        try
        {
            i = ActivityManager.getService().getTaskForActivity(mToken, false);
        }
        catch(RemoteException remoteexception)
        {
            return -1;
        }
        return i;
    }

    public final CharSequence getTitle()
    {
        return mTitle;
    }

    public final int getTitleColor()
    {
        return mTitleColor;
    }

    public boolean[] getViewVisibility(int ai[])
    {
        boolean aflag[];
        View aview[];
        int i;
        aflag = new boolean[ai.length];
        aview = findViewsByAutofillIdTraversal(ai);
        i = 0;
_L2:
        View view;
        if(i >= ai.length)
            break MISSING_BLOCK_LABEL_109;
        view = aview[i];
        if(view != null)
            break; /* Loop/switch isn't completed */
        aflag[i] = false;
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        aflag[i] = true;
_L8:
        if((view instanceof DecorView) && view.getViewRootImpl() == view.getParent()) goto _L4; else goto _L3
_L3:
        if(view.getVisibility() == 0) goto _L6; else goto _L5
_L5:
        aflag[i] = false;
          goto _L4
_L6:
        if(!(view.getParent() instanceof View)) goto _L4; else goto _L7
_L7:
        view = (View)view.getParent();
          goto _L8
        return aflag;
          goto _L4
    }

    public VoiceInteractor getVoiceInteractor()
    {
        return mVoiceInteractor;
    }

    public final int getVolumeControlStream()
    {
        return getWindow().getVolumeControlStream();
    }

    public Window getWindow()
    {
        return mWindow;
    }

    public WindowManager getWindowManager()
    {
        return mWindowManager;
    }

    public int getWindowStackId()
        throws RemoteException
    {
        return ActivityManager.getService().getActivityStackId(mToken);
    }

    public boolean hasWindowFocus()
    {
        Object obj = getWindow();
        if(obj != null)
        {
            obj = ((Window) (obj)).getDecorView();
            if(obj != null)
                return ((View) (obj)).hasWindowFocus();
        }
        return false;
    }

    public void invalidateOptionsMenu()
    {
        if(mWindow.hasFeature(0) && (mActionBar == null || mActionBar.invalidateOptionsMenu() ^ true))
            mWindow.invalidatePanelMenu(0);
    }

    public boolean isActivityTransitionRunning()
    {
        return mActivityTransitionState.isTransitionRunning();
    }

    public boolean isBackgroundVisibleBehind()
    {
        return false;
    }

    public boolean isChangingConfigurations()
    {
        return mChangingConfigurations;
    }

    public final boolean isChild()
    {
        boolean flag;
        if(mParent != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDestroyed()
    {
        return mDestroyed;
    }

    public boolean isFinishing()
    {
        return mFinished;
    }

    public boolean isImmersive()
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().isImmersive(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isInMultiWindowMode()
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().isInMultiWindowMode(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isInPictureInPictureMode()
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().isInPictureInPictureMode(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isLocalVoiceInteractionSupported()
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().supportsLocalVoiceInteraction();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isOverlayWithDecorCaptionEnabled()
    {
        return mWindow.isOverlayWithDecorCaptionEnabled();
    }

    public final boolean isResumed()
    {
        return mResumed;
    }

    public boolean isTaskRoot()
    {
        boolean flag = true;
        int i;
        try
        {
            i = ActivityManager.getService().getTaskForActivity(mToken, true);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        if(i < 0)
            flag = false;
        return flag;
    }

    public boolean isVisibleForAutofill()
    {
        return mStopped ^ true;
    }

    public boolean isVoiceInteraction()
    {
        boolean flag;
        if(mVoiceInteractor != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isVoiceInteractionRoot()
    {
        boolean flag = false;
        try
        {
            if(mVoiceInteractor != null)
                flag = ActivityManager.getService().isRootVoiceInteraction(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    void makeVisible()
    {
        if(!mWindowAdded)
        {
            getWindowManager().addView(mDecor, getWindow().getAttributes());
            mWindowAdded = true;
        }
        mDecor.setVisibility(0);
    }

    public final Cursor managedQuery(Uri uri, String as[], String s, String s1)
    {
        uri = getContentResolver().query(uri, as, s, null, s1);
        if(uri != null)
            startManagingCursor(uri);
        return uri;
    }

    public final Cursor managedQuery(Uri uri, String as[], String s, String as1[], String s1)
    {
        uri = getContentResolver().query(uri, as, s, as1, s1);
        if(uri != null)
            startManagingCursor(uri);
        return uri;
    }

    public boolean moveTaskToBack(boolean flag)
    {
        try
        {
            flag = ActivityManager.getService().moveActivityTaskToBack(mToken, flag);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean navigateUpTo(Intent intent)
    {
        Intent intent1;
        if(mParent != null)
            break MISSING_BLOCK_LABEL_104;
        intent1 = intent;
        if(intent.getComponent() == null)
        {
            ComponentName componentname = intent.resolveActivity(getPackageManager());
            if(componentname == null)
                return false;
            intent1 = new Intent(intent);
            intent1.setComponent(componentname);
        }
        this;
        JVM INSTR monitorenter ;
        int i;
        i = mResultCode;
        intent = mResultData;
        this;
        JVM INSTR monitorexit ;
        if(intent != null)
            intent.prepareToLeaveProcess(this);
        boolean flag;
        try
        {
            intent1.prepareToLeaveProcess(this);
            flag = ActivityManager.getService().navigateUpTo(mToken, intent1, i, intent);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            return false;
        }
        return flag;
        intent;
        throw intent;
        return mParent.navigateUpToFromChild(this, intent);
    }

    public boolean navigateUpToFromChild(Activity activity, Intent intent)
    {
        return navigateUpTo(intent);
    }

    public void onActionModeFinished(ActionMode actionmode)
    {
    }

    public void onActionModeStarted(ActionMode actionmode)
    {
    }

    public void onActivityReenter(int i, Intent intent)
    {
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
    }

    protected void onApplyThemeResource(android.content.res.Resources.Theme theme, int i, boolean flag)
    {
        if(mParent == null)
        {
            super.onApplyThemeResource(theme, i, flag);
        } else
        {
            try
            {
                theme.setTo(mParent.getTheme());
            }
            catch(Exception exception) { }
            theme.applyStyle(i, false);
        }
        theme = theme.obtainStyledAttributes(com.android.internal.R.styleable.ActivityTaskDescription);
        if(mTaskDescription.getPrimaryColor() == 0)
        {
            i = theme.getColor(1, 0);
            if(i != 0 && Color.alpha(i) == 255)
                mTaskDescription.setPrimaryColor(i);
        }
        i = theme.getColor(0, 0);
        if(i != 0 && Color.alpha(i) == 255)
            mTaskDescription.setBackgroundColor(i);
        i = theme.getColor(2, 0);
        if(i != 0)
            mTaskDescription.setStatusBarColor(i);
        i = theme.getColor(3, 0);
        if(i != 0)
            mTaskDescription.setNavigationBarColor(i);
        theme.recycle();
        setTaskDescription(mTaskDescription);
    }

    public void onAttachFragment(Fragment fragment)
    {
    }

    public void onAttachedToWindow()
    {
    }

    public void onBackPressed()
    {
        if(mActionBar != null && mActionBar.collapseActionView())
            return;
        FragmentManager fragmentmanager = mFragments.getFragmentManager();
        if(fragmentmanager.isStateSaved() || fragmentmanager.popBackStackImmediate() ^ true)
            finishAfterTransition();
    }

    public void onBackgroundVisibleBehindChanged(boolean flag)
    {
    }

    protected void onChildTitleChanged(Activity activity, CharSequence charsequence)
    {
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        mCalled = true;
        mFragments.dispatchConfigurationChanged(configuration);
        if(mWindow != null)
            mWindow.onConfigurationChanged(configuration);
        if(mActionBar != null)
            mActionBar.onConfigurationChanged(configuration);
    }

    public void onContentChanged()
    {
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        if(mParent != null)
            return mParent.onContextItemSelected(menuitem);
        else
            return false;
    }

    public void onContextMenuClosed(Menu menu)
    {
        if(mParent != null)
            mParent.onContextMenuClosed(menu);
    }

    protected void onCreate(Bundle bundle)
    {
        FragmentManagerNonConfig fragmentmanagernonconfig = null;
        if(mLastNonConfigurationInstances != null)
            mFragments.restoreLoaderNonConfig(mLastNonConfigurationInstances.loaders);
        boolean flag;
        if(mActivityInfo.parentActivityName != null)
            if(mActionBar == null)
                mEnableDefaultActionBarUp = true;
            else
                mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        if(bundle != null)
        {
            mAutoFillResetNeeded = bundle.getBoolean("@android:autofillResetNeeded", false);
            mLastAutofillId = bundle.getInt("android:lastAutofillId", 0x3fffffff);
            if(mAutoFillResetNeeded)
                getAutofillManager().onCreate(bundle);
            android.os.Parcelable parcelable = bundle.getParcelable("android:fragments");
            FragmentController fragmentcontroller = mFragments;
            if(mLastNonConfigurationInstances != null)
                fragmentmanagernonconfig = mLastNonConfigurationInstances.fragments;
            fragmentcontroller.restoreAllState(parcelable, fragmentmanagernonconfig);
        }
        mFragments.dispatchCreate();
        getApplication().dispatchActivityCreated(this, bundle);
        if(mVoiceInteractor != null)
            mVoiceInteractor.attachActivity(this);
        if(bundle != null)
            flag = true;
        else
            flag = false;
        mRestoredFromBundle = flag;
        mCalled = true;
        if(mInterceptor == null)
            mInterceptor = InterceptorProxy.create(this);
        if(mInterceptor != null)
            mInterceptor.notifyActivityCreate();
    }

    public void onCreate(Bundle bundle, PersistableBundle persistablebundle)
    {
        onCreate(bundle);
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
    }

    public CharSequence onCreateDescription()
    {
        return null;
    }

    protected Dialog onCreateDialog(int i)
    {
        return null;
    }

    protected Dialog onCreateDialog(int i, Bundle bundle)
    {
        return onCreateDialog(i);
    }

    public void onCreateNavigateUpTaskStack(TaskStackBuilder taskstackbuilder)
    {
        taskstackbuilder.addParentStack(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(mParent != null)
            return mParent.onCreateOptionsMenu(menu);
        else
            return true;
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        if(i == 0)
            return onCreateOptionsMenu(menu) | mFragments.dispatchCreateOptionsMenu(menu, getMenuInflater());
        else
            return false;
    }

    public View onCreatePanelView(int i)
    {
        return null;
    }

    public boolean onCreateThumbnail(Bitmap bitmap, Canvas canvas)
    {
        return false;
    }

    public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        if(!"fragment".equals(s))
            return onCreateView(s, context, attributeset);
        else
            return mFragments.onCreateView(view, s, context, attributeset);
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        return null;
    }

    protected void onDestroy()
    {
        mCalled = true;
        if(mManagedDialogs != null)
        {
            int i = mManagedDialogs.size();
            for(int k = 0; k < i; k++)
            {
                ManagedDialog manageddialog = (ManagedDialog)mManagedDialogs.valueAt(k);
                if(manageddialog.mDialog.isShowing())
                    manageddialog.mDialog.dismiss();
            }

            mManagedDialogs = null;
        }
        ArrayList arraylist = mManagedCursors;
        arraylist;
        JVM INSTR monitorenter ;
        int j = mManagedCursors.size();
        int l = 0;
_L3:
        if(l >= j) goto _L2; else goto _L1
_L1:
        ManagedCursor managedcursor = (ManagedCursor)mManagedCursors.get(l);
        if(managedcursor == null)
            continue; /* Loop/switch isn't completed */
        ManagedCursor._2D_get0(managedcursor).close();
        l++;
          goto _L3
_L2:
        mManagedCursors.clear();
        arraylist;
        JVM INSTR monitorexit ;
        if(mSearchManager != null)
            mSearchManager.stopSearch();
        if(mActionBar != null)
            mActionBar.onDestroy();
        getApplication().dispatchActivityDestroyed(this);
        if(mInterceptor != null)
            mInterceptor.notifyActivityDestroy();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onDetachedFromWindow()
    {
    }

    public void onEnterAnimationComplete()
    {
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            if(getApplicationInfo().targetSdkVersion >= 5)
                keyevent.startTracking();
            else
                onBackPressed();
            return true;
        }
        if(mDefaultKeyMode == 0)
            return false;
        if(mDefaultKeyMode == 2)
        {
            Window window = getWindow();
            return window.hasFeature(0) && window.performPanelShortcut(0, i, keyevent, 2);
        }
        if(i == 61)
            return false;
        flag = false;
        if(keyevent.getRepeatCount() == 0 && !keyevent.isSystem()) goto _L2; else goto _L1
_L1:
        boolean flag1;
        i = 1;
        flag1 = false;
_L4:
        if(i != 0)
        {
            mDefaultKeySsb.clear();
            mDefaultKeySsb.clearSpans();
            Selection.setSelection(mDefaultKeySsb, 0);
        }
        return flag1;
_L2:
        boolean flag2;
        flag2 = TextKeyListener.getInstance().onKeyDown(null, mDefaultKeySsb, i, keyevent);
        i = ((flag) ? 1 : 0);
        flag1 = flag2;
        if(!flag2) goto _L4; else goto _L3
_L3:
        i = ((flag) ? 1 : 0);
        flag1 = flag2;
        if(mDefaultKeySsb.length() <= 0) goto _L4; else goto _L5
_L5:
        keyevent = mDefaultKeySsb.toString();
        i = 1;
        switch(mDefaultKeyMode)
        {
        case 2: // '\002'
        default:
            flag1 = flag2;
            break;

        case 1: // '\001'
            keyevent = new Intent("android.intent.action.DIAL", Uri.parse((new StringBuilder()).append("tel:").append(keyevent).toString()));
            keyevent.addFlags(0x10000000);
            startActivity(keyevent);
            flag1 = flag2;
            break;

        case 3: // '\003'
            startSearch(keyevent, false, null, false);
            flag1 = flag2;
            break;

        case 4: // '\004'
            startSearch(keyevent, false, null, true);
            flag1 = flag2;
            break;
        }
        if(true) goto _L4; else goto _L6
_L6:
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        ActionBar actionbar = getActionBar();
        boolean flag;
        if(actionbar != null)
            flag = actionbar.onKeyShortcut(i, keyevent);
        else
            flag = false;
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(getApplicationInfo().targetSdkVersion >= 5 && i == 4 && keyevent.isTracking() && keyevent.isCanceled() ^ true)
        {
            onBackPressed();
            return true;
        } else
        {
            return false;
        }
    }

    public void onLocalVoiceInteractionStarted()
    {
    }

    public void onLocalVoiceInteractionStopped()
    {
    }

    public void onLowMemory()
    {
        mCalled = true;
        mFragments.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        CharSequence charsequence = menuitem.getTitleCondensed();
        switch(i)
        {
        default:
            return false;

        case 0: // '\0'
            if(charsequence != null)
                EventLog.writeEvent(50000, new Object[] {
                    Integer.valueOf(0), charsequence.toString()
                });
            if(onOptionsItemSelected(menuitem))
                return true;
            if(mFragments.dispatchOptionsItemSelected(menuitem))
                return true;
            if(menuitem.getItemId() == 0x102002c && mActionBar != null && (mActionBar.getDisplayOptions() & 4) != 0)
            {
                if(mParent == null)
                    return onNavigateUp();
                else
                    return mParent.onNavigateUpFromChild(this);
            } else
            {
                return false;
            }

        case 6: // '\006'
            break;
        }
        if(charsequence != null)
            EventLog.writeEvent(50000, new Object[] {
                Integer.valueOf(1), charsequence.toString()
            });
        if(onContextItemSelected(menuitem))
            return true;
        else
            return mFragments.dispatchContextItemSelected(menuitem);
    }

    public boolean onMenuOpened(int i, Menu menu)
    {
        if(i == 8)
        {
            initWindowDecorActionBar();
            if(mActionBar != null)
                mActionBar.dispatchMenuVisibilityChanged(true);
            else
                Log.e("Activity", "Tried to open action bar menu with no action bar");
        }
        return true;
    }

    public void onMovedToDisplay(int i, Configuration configuration)
    {
    }

    public void onMultiWindowModeChanged(boolean flag)
    {
    }

    public void onMultiWindowModeChanged(boolean flag, Configuration configuration)
    {
        onMultiWindowModeChanged(flag);
    }

    public boolean onNavigateUp()
    {
        Object obj = getParentActivityIntent();
        if(obj != null)
        {
            if(mActivityInfo.taskAffinity == null)
                finish();
            else
            if(shouldUpRecreateTask(((Intent) (obj))))
            {
                obj = TaskStackBuilder.create(this);
                onCreateNavigateUpTaskStack(((TaskStackBuilder) (obj)));
                onPrepareNavigateUpTaskStack(((TaskStackBuilder) (obj)));
                ((TaskStackBuilder) (obj)).startActivities();
                if(mResultCode != 0 || mResultData != null)
                {
                    Log.i("Activity", "onNavigateUp only finishing topmost activity to return a result");
                    finish();
                } else
                {
                    finishAffinity();
                }
            } else
            {
                navigateUpTo(((Intent) (obj)));
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onNavigateUpFromChild(Activity activity)
    {
        return onNavigateUp();
    }

    public void onNewActivityOptions(ActivityOptions activityoptions)
    {
        mActivityTransitionState.setEnterActivityOptions(this, activityoptions);
        if(!mStopped)
            mActivityTransitionState.enterReady(this);
    }

    protected void onNewIntent(Intent intent)
    {
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if(mParent != null)
            return mParent.onOptionsItemSelected(menuitem);
        else
            return false;
    }

    public void onOptionsMenuClosed(Menu menu)
    {
        if(mParent != null)
            mParent.onOptionsMenuClosed(menu);
    }

    public void onPanelClosed(int i, Menu menu)
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   0: 37
    //                   6: 53
    //                   8: 61;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        mFragments.dispatchOptionsMenuClosed(menu);
        onOptionsMenuClosed(menu);
        continue; /* Loop/switch isn't completed */
_L3:
        onContextMenuClosed(menu);
        continue; /* Loop/switch isn't completed */
_L4:
        initWindowDecorActionBar();
        mActionBar.dispatchMenuVisibilityChanged(false);
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onPause()
    {
        getApplication().dispatchActivityPaused(this);
        mCalled = true;
        if(mInterceptor != null)
            mInterceptor.notifyActivityPause();
    }

    public void onPictureInPictureModeChanged(boolean flag)
    {
    }

    public void onPictureInPictureModeChanged(boolean flag, Configuration configuration)
    {
        onPictureInPictureModeChanged(flag);
    }

    protected void onPostCreate(Bundle bundle)
    {
        if(!isChild())
        {
            mTitleReady = true;
            onTitleChanged(getTitle(), getTitleColor());
        }
        mCalled = true;
    }

    public void onPostCreate(Bundle bundle, PersistableBundle persistablebundle)
    {
        onPostCreate(bundle);
    }

    protected void onPostResume()
    {
        Window window = getWindow();
        if(window != null)
            window.makeActive();
        if(mActionBar != null)
            mActionBar.setShowHideAnimationEnabled(true);
        mCalled = true;
    }

    protected void onPrepareDialog(int i, Dialog dialog)
    {
        dialog.setOwnerActivity(this);
    }

    protected void onPrepareDialog(int i, Dialog dialog, Bundle bundle)
    {
        onPrepareDialog(i, dialog);
    }

    public void onPrepareNavigateUpTaskStack(TaskStackBuilder taskstackbuilder)
    {
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if(mParent != null)
            return mParent.onPrepareOptionsMenu(menu);
        else
            return true;
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        if(i == 0 && menu != null)
            return onPrepareOptionsMenu(menu) | mFragments.dispatchPrepareOptionsMenu(menu);
        else
            return true;
    }

    public void onProvideAssistContent(AssistContent assistcontent)
    {
    }

    public void onProvideAssistData(Bundle bundle)
    {
    }

    public void onProvideKeyboardShortcuts(List list, Menu menu, int i)
    {
        if(menu == null)
            return;
        Object obj = null;
        int j = menu.size();
        i = 0;
        while(i < j) 
        {
            Object obj1 = menu.getItem(i);
            CharSequence charsequence = ((MenuItem) (obj1)).getTitle();
            char c = ((MenuItem) (obj1)).getAlphabeticShortcut();
            int k = ((MenuItem) (obj1)).getAlphabeticModifiers();
            obj1 = obj;
            if(charsequence != null)
            {
                obj1 = obj;
                if(c != 0)
                {
                    obj1 = obj;
                    if(obj == null)
                    {
                        int l = mApplication.getApplicationInfo().labelRes;
                        if(l != 0)
                            obj = getString(l);
                        else
                            obj = null;
                        obj1 = new KeyboardShortcutGroup(((CharSequence) (obj)));
                    }
                    ((KeyboardShortcutGroup) (obj1)).addItem(new KeyboardShortcutInfo(charsequence, c, k));
                }
            }
            i++;
            obj = obj1;
        }
        if(obj != null)
            list.add(obj);
    }

    public Uri onProvideReferrer()
    {
        return null;
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
    }

    protected void onRestart()
    {
        mCalled = true;
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        if(mWindow != null)
        {
            bundle = bundle.getBundle("android:viewHierarchyState");
            if(bundle != null)
                mWindow.restoreHierarchyState(bundle);
        }
    }

    public void onRestoreInstanceState(Bundle bundle, PersistableBundle persistablebundle)
    {
        if(bundle != null)
            onRestoreInstanceState(bundle);
    }

    protected void onResume()
    {
        WhetstoneAppManager.getInstance().onResume(this);
        getApplication().dispatchActivityResumed(this);
        mActivityTransitionState.onResume(this, isTopOfTask());
        mCalled = true;
        if(mInterceptor != null)
            mInterceptor.notifyActivityResume();
        ActivityInjector.checkAccessControl(this);
    }

    HashMap onRetainNonConfigurationChildInstances()
    {
        return null;
    }

    public Object onRetainNonConfigurationInstance()
    {
        return null;
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        bundle.putBundle("android:viewHierarchyState", mWindow.saveHierarchyState());
        bundle.putInt("android:lastAutofillId", mLastAutofillId);
        android.os.Parcelable parcelable = mFragments.saveAllState();
        if(parcelable != null)
            bundle.putParcelable("android:fragments", parcelable);
        if(mAutoFillResetNeeded)
        {
            bundle.putBoolean("@android:autofillResetNeeded", true);
            getAutofillManager().onSaveInstanceState(bundle);
        }
        getApplication().dispatchActivitySaveInstanceState(this, bundle);
    }

    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistablebundle)
    {
        onSaveInstanceState(bundle);
    }

    public boolean onSearchRequested()
    {
        if((getResources().getConfiguration().uiMode & 0xf) != 4)
        {
            startSearch(null, false, null, false);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onSearchRequested(SearchEvent searchevent)
    {
        mSearchEvent = searchevent;
        boolean flag = onSearchRequested();
        mSearchEvent = null;
        return flag;
    }

    protected void onStart()
    {
        mCalled = true;
        mFragments.doLoaderStart();
        getApplication().dispatchActivityStarted(this);
        if(mAutoFillResetNeeded)
        {
            AutofillManager autofillmanager = getAutofillManager();
            if(autofillmanager != null)
                autofillmanager.onVisibleForAutofill();
        }
        if(mInterceptor != null)
            mInterceptor.notifyActivityStart();
    }

    public void onStateNotSaved()
    {
    }

    protected void onStop()
    {
        if(mActionBar != null)
            mActionBar.setShowHideAnimationEnabled(false);
        mActivityTransitionState.onStop();
        getApplication().dispatchActivityStopped(this);
        mTranslucentCallback = null;
        mCalled = true;
        if(!isFinishing()) goto _L2; else goto _L1
_L1:
        if(!mAutoFillResetNeeded) goto _L4; else goto _L3
_L3:
        getAutofillManager().commit();
_L2:
        if(mInterceptor != null)
            mInterceptor.notifyActivityStop();
        return;
_L4:
        if(mIntent != null && mIntent.hasExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN"))
            getAutofillManager().onPendingSaveUi(1, mIntent.getIBinderExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN"));
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void onTitleChanged(CharSequence charsequence, int i)
    {
        if(mTitleReady)
        {
            Window window = getWindow();
            if(window != null)
            {
                window.setTitle(charsequence);
                if(i != 0)
                    window.setTitleColor(i);
            }
            if(mActionBar != null)
                mActionBar.setWindowTitle(charsequence);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mWindow.shouldCloseOnTouch(this, motionevent))
        {
            finish();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        return false;
    }

    void onTranslucentConversionComplete(boolean flag)
    {
        if(mTranslucentCallback != null)
        {
            mTranslucentCallback.onTranslucentConversionComplete(flag);
            mTranslucentCallback = null;
        }
        if(mChangeCanvasToTranslucent)
            WindowManagerGlobal.getInstance().changeCanvasOpacity(mToken, false);
    }

    public void onTrimMemory(int i)
    {
        mCalled = true;
        mFragments.dispatchTrimMemory(i);
    }

    public void onUserInteraction()
    {
    }

    protected void onUserLeaveHint()
    {
    }

    public void onVisibleBehindCanceled()
    {
        mCalled = true;
    }

    public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams layoutparams)
    {
        if(mParent == null)
        {
            View view = mDecor;
            if(view != null && view.getParent() != null)
                getWindowManager().updateViewLayout(view, layoutparams);
        }
    }

    public void onWindowDismissed(boolean flag, boolean flag1)
    {
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        finish(byte0);
        if(flag1)
            overridePendingTransition(0, 0);
    }

    public void onWindowFocusChanged(boolean flag)
    {
    }

    public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
    {
        if(mActionModeTypeStarting == 0)
        {
            initWindowDecorActionBar();
            if(mActionBar != null)
                return mActionBar.startActionMode(callback);
        }
        return null;
    }

    public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int i)
    {
        mActionModeTypeStarting = i;
        callback = onWindowStartingActionMode(callback);
        mActionModeTypeStarting = 0;
        return callback;
        callback;
        mActionModeTypeStarting = 0;
        throw callback;
    }

    public void openContextMenu(View view)
    {
        view.showContextMenu();
    }

    public void openOptionsMenu()
    {
        if(mWindow.hasFeature(0) && (mActionBar == null || mActionBar.openOptionsMenu() ^ true))
            mWindow.openPanel(0, null);
    }

    public void overridePendingTransition(int i, int j)
    {
        ActivityManager.getService().overridePendingTransition(mToken, getPackageName(), i, j);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    final void performCreate(Bundle bundle)
    {
        performCreate(bundle, null);
    }

    final void performCreate(Bundle bundle, PersistableBundle persistablebundle)
    {
        mCanEnterPictureInPicture = true;
        restoreHasCurrentPermissionRequest(bundle);
        if(persistablebundle != null)
            onCreate(bundle, persistablebundle);
        else
            onCreate(bundle);
        mActivityTransitionState.readState(bundle);
        mVisibleFromClient = mWindow.getWindowStyle().getBoolean(10, false) ^ true;
        mFragments.dispatchActivityCreated();
        mActivityTransitionState.setEnterActivityOptions(this, getActivityOptions());
    }

    final void performDestroy()
    {
        mDestroyed = true;
        mWindow.destroy();
        mFragments.dispatchDestroy();
        onDestroy();
        mFragments.doLoaderDestroy();
        if(mVoiceInteractor != null)
            mVoiceInteractor.detachActivity();
    }

    final void performNewIntent(Intent intent)
    {
        mCanEnterPictureInPicture = true;
        onNewIntent(intent);
    }

    final void performPause()
    {
        mDoReportFullyDrawn = false;
        mFragments.dispatchPause();
        mCalled = false;
        onPause();
        mResumed = false;
        if(!mCalled && getApplicationInfo().targetSdkVersion >= 9)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Activity ").append(mComponent.toShortString()).append(" did not call through to super.onPause()").toString());
        } else
        {
            mResumed = false;
            return;
        }
    }

    final void performRestart()
    {
        mCanEnterPictureInPicture = true;
        mFragments.noteStateNotSaved();
        if(mToken != null && mParent == null)
            WindowManagerGlobal.getInstance().setStoppedState(mToken, false);
        if(!mStopped)
            break MISSING_BLOCK_LABEL_260;
        mStopped = false;
        ArrayList arraylist = mManagedCursors;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mManagedCursors.size();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        Object obj;
        obj = (ManagedCursor)mManagedCursors.get(j);
        if(!ManagedCursor._2D_get1(((ManagedCursor) (obj))) && !ManagedCursor._2D_get2(((ManagedCursor) (obj))))
            continue; /* Loop/switch isn't completed */
        if(!ManagedCursor._2D_get0(((ManagedCursor) (obj))).requery() && getApplicationInfo().targetSdkVersion >= 14)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #392 <Class IllegalStateException>;
            StringBuilder stringbuilder = JVM INSTR new #508 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalstateexception.IllegalStateException(stringbuilder.append("trying to requery an already closed cursor  ").append(ManagedCursor._2D_get0(((ManagedCursor) (obj)))).toString());
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_174;
        obj;
        arraylist;
        JVM INSTR monitorexit ;
        throw obj;
        ManagedCursor._2D_set0(((ManagedCursor) (obj)), false);
        ManagedCursor._2D_set1(((ManagedCursor) (obj)), false);
        j++;
          goto _L3
_L2:
        mCalled = false;
        mInstrumentation.callActivityOnRestart(this);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Activity ").append(mComponent.toShortString()).append(" did not call through to super.onRestart()").toString());
        performStart();
    }

    final void performRestoreInstanceState(Bundle bundle)
    {
        onRestoreInstanceState(bundle);
        restoreManagedDialogs(bundle);
    }

    final void performRestoreInstanceState(Bundle bundle, PersistableBundle persistablebundle)
    {
        onRestoreInstanceState(bundle, persistablebundle);
        if(bundle != null)
            restoreManagedDialogs(bundle);
    }

    final void performResume()
    {
        performRestart();
        mFragments.execPendingActions();
        mLastNonConfigurationInstances = null;
        mCalled = false;
        mInstrumentation.callActivityOnResume(this);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Activity ").append(mComponent.toShortString()).append(" did not call through to super.onResume()").toString());
        if(!mVisibleFromClient && mFinished ^ true)
        {
            Log.w("Activity", "An activity without a UI must call finish() before onResume() completes");
            if(getApplicationInfo().targetSdkVersion > 22)
                throw new IllegalStateException((new StringBuilder()).append("Activity ").append(mComponent.toShortString()).append(" did not call finish() prior to onResume() completing").toString());
        }
        mCalled = false;
        mFragments.dispatchResume();
        mFragments.execPendingActions();
        onPostResume();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Activity ").append(mComponent.toShortString()).append(" did not call through to super.onPostResume()").toString());
        else
            return;
    }

    final void performSaveInstanceState(Bundle bundle)
    {
        onSaveInstanceState(bundle);
        saveManagedDialogs(bundle);
        mActivityTransitionState.saveState(bundle);
        storeHasCurrentPermissionRequest(bundle);
    }

    final void performSaveInstanceState(Bundle bundle, PersistableBundle persistablebundle)
    {
        onSaveInstanceState(bundle, persistablebundle);
        saveManagedDialogs(bundle);
        storeHasCurrentPermissionRequest(bundle);
    }

    final void performStart()
    {
        mActivityTransitionState.setEnterActivityOptions(this, getActivityOptions());
        mFragments.noteStateNotSaved();
        mCalled = false;
        mFragments.execPendingActions();
        mInstrumentation.callActivityOnStart(this);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Activity ").append(mComponent.toShortString()).append(" did not call through to super.onStart()").toString());
        mFragments.dispatchStart();
        mFragments.reportLoaderStart();
        boolean flag;
        boolean flag1;
        if(SystemProperties.getInt("ro.bionic.ld.warning", 0) == 1)
            flag = true;
        else
            flag = false;
        if((mApplication.getApplicationInfo().flags & 2) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 || flag)
        {
            String s = getDlWarning();
            if(s != null)
            {
                String s1 = getApplicationInfo().loadLabel(getPackageManager()).toString();
                s = (new StringBuilder()).append("Detected problems with app native libraries\n(please consult log for detail):\n").append(s).toString();
                if(flag1)
                    (new AlertDialog.Builder(this)).setTitle(s1).setMessage(s).setPositiveButton(0x104000a, null).setCancelable(false).show();
                else
                    Toast.makeText(this, (new StringBuilder()).append(s1).append("\n").append(s).toString(), 1).show();
            }
        }
        mActivityTransitionState.enterReady(this);
    }

    final void performStop(boolean flag)
    {
        mDoReportFullyDrawn = false;
        mFragments.doLoaderStop(mChangingConfigurations);
        mCanEnterPictureInPicture = false;
        if(mStopped)
            break MISSING_BLOCK_LABEL_214;
        if(mWindow != null)
            mWindow.closeAllPanels();
        if(!flag && mToken != null && mParent == null)
            WindowManagerGlobal.getInstance().setStoppedState(mToken, true);
        mFragments.dispatchStop();
        mCalled = false;
        mInstrumentation.callActivityOnStop(this);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Activity ").append(mComponent.toShortString()).append(" did not call through to super.onStop()").toString());
        ArrayList arraylist = mManagedCursors;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mManagedCursors.size();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        ManagedCursor managedcursor = (ManagedCursor)mManagedCursors.get(j);
        if(!ManagedCursor._2D_get1(managedcursor))
        {
            ManagedCursor._2D_get0(managedcursor).deactivate();
            ManagedCursor._2D_set0(managedcursor, true);
        }
        j++;
          goto _L3
_L2:
        mStopped = true;
        mResumed = false;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void performUserLeaving()
    {
        onUserInteraction();
        onUserLeaveHint();
    }

    public void postponeEnterTransition()
    {
        mActivityTransitionState.postponeEnterTransition();
    }

    public void recreate()
    {
        if(mParent != null)
            throw new IllegalStateException("Can only be called on top-level activity");
        if(Looper.myLooper() != mMainThread.getLooper())
            throw new IllegalStateException("Must be called from main thread");
        ActivityManager.getService().requestActivityRelaunch(mToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void registerForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(this);
    }

    public boolean releaseInstance()
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().releaseActivityInstance(mToken);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public final void removeDialog(int i)
    {
        if(mManagedDialogs != null)
        {
            ManagedDialog manageddialog = (ManagedDialog)mManagedDialogs.get(i);
            if(manageddialog != null)
            {
                manageddialog.mDialog.dismiss();
                mManagedDialogs.remove(i);
            }
        }
    }

    public void reportFullyDrawn()
    {
        if(!mDoReportFullyDrawn)
            break MISSING_BLOCK_LABEL_28;
        mDoReportFullyDrawn = false;
        ActivityManager.getService().reportActivityFullyDrawn(mToken, mRestoredFromBundle);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public DragAndDropPermissions requestDragAndDropPermissions(DragEvent dragevent)
    {
        dragevent = DragAndDropPermissions.obtain(dragevent);
        if(dragevent != null && dragevent.take(getActivityToken()))
            return dragevent;
        else
            return null;
    }

    public final void requestPermissions(String as[], int i)
    {
        if(i < 0)
            throw new IllegalArgumentException("requestCode should be >= 0");
        if(mHasCurrentPermissionsRequest)
        {
            Log.w("Activity", "Can reqeust only one set of permissions at a time");
            onRequestPermissionsResult(i, new String[0], new int[0]);
            return;
        } else
        {
            startActivityForResult("@android:requestPermissions:", getPackageManager().buildRequestPermissionsIntent(as), i, null);
            mHasCurrentPermissionsRequest = true;
            return;
        }
    }

    public final void requestShowKeyboardShortcuts()
    {
        Intent intent = new Intent("com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS");
        intent.setPackage("com.android.systemui");
        sendBroadcastAsUser(intent, UserHandle.SYSTEM);
    }

    public boolean requestVisibleBehind(boolean flag)
    {
        return false;
    }

    public final boolean requestWindowFeature(int i)
    {
        return getWindow().requestFeature(i);
    }

    NonConfigurationInstances retainNonConfigurationInstances()
    {
        Object obj = onRetainNonConfigurationInstance();
        HashMap hashmap = onRetainNonConfigurationChildInstances();
        FragmentManagerNonConfig fragmentmanagernonconfig = mFragments.retainNestedNonConfig();
        mFragments.doLoaderStart();
        mFragments.doLoaderStop(true);
        ArrayMap arraymap = mFragments.retainLoaderNonConfig();
        if(obj == null && hashmap == null && fragmentmanagernonconfig == null && arraymap == null && mVoiceInteractor == null)
            return null;
        NonConfigurationInstances nonconfigurationinstances = new NonConfigurationInstances();
        nonconfigurationinstances.activity = obj;
        nonconfigurationinstances.children = hashmap;
        nonconfigurationinstances.fragments = fragmentmanagernonconfig;
        nonconfigurationinstances.loaders = arraymap;
        if(mVoiceInteractor != null)
        {
            mVoiceInteractor.retainInstance();
            nonconfigurationinstances.voiceInteractor = mVoiceInteractor;
        }
        return nonconfigurationinstances;
    }

    public final void runOnUiThread(Runnable runnable)
    {
        if(Thread.currentThread() != mUiThread)
            mHandler.post(runnable);
        else
            runnable.run();
    }

    public void setActionBar(Toolbar toolbar)
    {
        ActionBar actionbar = getActionBar();
        if(actionbar instanceof WindowDecorActionBar)
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set android:windowActionBar to false in your theme to use a Toolbar instead.");
        mMenuInflater = null;
        if(actionbar != null)
            actionbar.onDestroy();
        if(toolbar != null)
        {
            toolbar = new ToolbarActionBar(toolbar, getTitle(), this);
            mActionBar = toolbar;
            mWindow.setCallback(toolbar.getWrappedWindowCallback());
        } else
        {
            mActionBar = null;
            mWindow.setCallback(this);
        }
        invalidateOptionsMenu();
    }

    public void setContentTransitionManager(TransitionManager transitionmanager)
    {
        getWindow().setTransitionManager(transitionmanager);
    }

    public void setContentView(int i)
    {
        getWindow().setContentView(i);
        initWindowDecorActionBar();
    }

    public void setContentView(View view)
    {
        getWindow().setContentView(view);
        initWindowDecorActionBar();
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getWindow().setContentView(view, layoutparams);
        initWindowDecorActionBar();
    }

    public final void setDefaultKeyMode(int i)
    {
        mDefaultKeyMode = i;
        i;
        JVM INSTR tableswitch 0 4: default 40
    //                   0 48
    //                   1 54
    //                   2 48
    //                   3 54
    //                   4 54;
           goto _L1 _L2 _L3 _L2 _L3 _L3
_L1:
        throw new IllegalArgumentException();
_L2:
        mDefaultKeySsb = null;
_L5:
        return;
_L3:
        mDefaultKeySsb = new SpannableStringBuilder();
        Selection.setSelection(mDefaultKeySsb, 0);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setDisablePreviewScreenshots(boolean flag)
    {
        ActivityManager.getService().setDisablePreviewScreenshots(mToken, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("Activity", "Failed to call setDisablePreviewScreenshots", remoteexception);
          goto _L1
    }

    public void setDummyTranslucent(boolean flag)
    {
        ActivityManager.getService().setDummyTranslucent(mToken, flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        SharedElementCallback sharedelementcallback1 = sharedelementcallback;
        if(sharedelementcallback == null)
            sharedelementcallback1 = SharedElementCallback.NULL_CALLBACK;
        mEnterTransitionListener = sharedelementcallback1;
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        SharedElementCallback sharedelementcallback1 = sharedelementcallback;
        if(sharedelementcallback == null)
            sharedelementcallback1 = SharedElementCallback.NULL_CALLBACK;
        mExitTransitionListener = sharedelementcallback1;
    }

    public final void setFeatureDrawable(int i, Drawable drawable)
    {
        getWindow().setFeatureDrawable(i, drawable);
    }

    public final void setFeatureDrawableAlpha(int i, int j)
    {
        getWindow().setFeatureDrawableAlpha(i, j);
    }

    public final void setFeatureDrawableResource(int i, int j)
    {
        getWindow().setFeatureDrawableResource(i, j);
    }

    public final void setFeatureDrawableUri(int i, Uri uri)
    {
        getWindow().setFeatureDrawableUri(i, uri);
    }

    public void setFinishOnTouchOutside(boolean flag)
    {
        mWindow.setCloseOnTouchOutside(flag);
    }

    public void setImmersive(boolean flag)
    {
        ActivityManager.getService().setImmersive(mToken, flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setIntent(Intent intent)
    {
        mIntent = intent;
    }

    public final void setMediaController(MediaController mediacontroller)
    {
        getWindow().setMediaController(mediacontroller);
    }

    public void setOverlayWithDecorCaptionEnabled(boolean flag)
    {
        mWindow.setOverlayWithDecorCaptionEnabled(flag);
    }

    final void setParent(Activity activity)
    {
        mParent = activity;
    }

    public void setPersistent(boolean flag)
    {
    }

    public void setPictureInPictureArgs(PictureInPictureArgs pictureinpictureargs)
    {
        setPictureInPictureParams(PictureInPictureArgs.convert(pictureinpictureargs));
    }

    public void setPictureInPictureParams(PictureInPictureParams pictureinpictureparams)
    {
        if(!deviceSupportsPictureInPictureMode())
            return;
        if(pictureinpictureparams != null)
            break MISSING_BLOCK_LABEL_27;
        try
        {
            pictureinpictureparams = JVM INSTR new #506 <Class IllegalArgumentException>;
            pictureinpictureparams.IllegalArgumentException("Expected non-null picture-in-picture params");
            throw pictureinpictureparams;
        }
        // Misplaced declaration of an exception variable
        catch(PictureInPictureParams pictureinpictureparams) { }
_L1:
        return;
        ActivityManagerNative.getDefault().setPictureInPictureParams(mToken, pictureinpictureparams);
          goto _L1
    }

    public final void setProgress(int i)
    {
        getWindow().setFeatureInt(2, i + 0);
    }

    public final void setProgressBarIndeterminate(boolean flag)
    {
        Window window = getWindow();
        byte byte0;
        if(flag)
            byte0 = -3;
        else
            byte0 = -4;
        window.setFeatureInt(2, byte0);
    }

    public final void setProgressBarIndeterminateVisibility(boolean flag)
    {
        Window window = getWindow();
        byte byte0;
        if(flag)
            byte0 = -1;
        else
            byte0 = -2;
        window.setFeatureInt(5, byte0);
    }

    public final void setProgressBarVisibility(boolean flag)
    {
        Window window = getWindow();
        byte byte0;
        if(flag)
            byte0 = -1;
        else
            byte0 = -2;
        window.setFeatureInt(2, byte0);
    }

    public void setRequestedOrientation(int i)
    {
        if(mParent != null) goto _L2; else goto _L1
_L1:
        ActivityManager.getService().setRequestedOrientation(mToken, i);
_L4:
        return;
_L2:
        mParent.setRequestedOrientation(i);
        continue; /* Loop/switch isn't completed */
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public final void setResult(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mResultCode = i;
        mResultData = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void setResult(int i, Intent intent)
    {
        this;
        JVM INSTR monitorenter ;
        mResultCode = i;
        mResultData = intent;
        this;
        JVM INSTR monitorexit ;
        return;
        intent;
        throw intent;
    }

    public final void setSecondaryProgress(int i)
    {
        getWindow().setFeatureInt(2, i + 20000);
    }

    public void setShowWhenLocked(boolean flag)
    {
        ActivityManager.getService().setShowWhenLocked(mToken, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("Activity", "Failed to call setShowWhenLocked", remoteexception);
          goto _L1
    }

    public void setTaskDescription(ActivityManager.TaskDescription taskdescription)
    {
        if(mTaskDescription != taskdescription)
        {
            mTaskDescription.copyFromPreserveHiddenFields(taskdescription);
            if(taskdescription.getIconFilename() == null && taskdescription.getIcon() != null)
            {
                int i = ActivityManager.getLauncherLargeIconSizeInner(this);
                taskdescription = Bitmap.createScaledBitmap(taskdescription.getIcon(), i, i, true);
                mTaskDescription.setIcon(taskdescription);
            }
        }
        ActivityManager.getService().setTaskDescription(mToken, mTaskDescription);
_L2:
        return;
        taskdescription;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setTheme(int i)
    {
        super.setTheme(i);
        mWindow.setTheme(i);
    }

    public void setTitle(int i)
    {
        setTitle(getText(i));
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        onTitleChanged(charsequence, mTitleColor);
        if(mParent != null)
            mParent.onChildTitleChanged(this, charsequence);
    }

    public void setTitleColor(int i)
    {
        mTitleColor = i;
        onTitleChanged(mTitle, i);
    }

    public void setTurnScreenOn(boolean flag)
    {
        ActivityManager.getService().setTurnScreenOn(mToken, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("Activity", "Failed to call setTurnScreenOn", remoteexception);
          goto _L1
    }

    public void setVisible(boolean flag)
    {
        if(mVisibleFromClient != flag)
        {
            mVisibleFromClient = flag;
            if(mVisibleFromServer)
                if(flag)
                    makeVisible();
                else
                    mDecor.setVisibility(4);
        }
    }

    void setVoiceInteractor(IVoiceInteractor ivoiceinteractor)
    {
        if(mVoiceInteractor != null)
        {
            VoiceInteractor.Request arequest[] = mVoiceInteractor.getActiveRequests();
            int i = 0;
            for(int j = arequest.length; i < j; i++)
            {
                VoiceInteractor.Request request = arequest[i];
                request.cancel();
                request.clear();
            }

        }
        if(ivoiceinteractor == null)
            mVoiceInteractor = null;
        else
            mVoiceInteractor = new VoiceInteractor(ivoiceinteractor, this, this, Looper.myLooper());
    }

    public final void setVolumeControlStream(int i)
    {
        getWindow().setVolumeControlStream(i);
    }

    public void setVrModeEnabled(boolean flag, ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        try
        {
            if(ActivityManager.getService().setVrMode(mToken, flag, componentname) != 0)
            {
                android.content.pm.PackageManager.NameNotFoundException namenotfoundexception = JVM INSTR new #1397 <Class android.content.pm.PackageManager$NameNotFoundException>;
                namenotfoundexception.android.content.pm.PackageManager.NameNotFoundException(componentname.flattenToString());
                throw namenotfoundexception;
            }
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname) { }
    }

    public boolean shouldShowRequestPermissionRationale(String s)
    {
        return getPackageManager().shouldShowRequestPermissionRationale(s);
    }

    public boolean shouldUpRecreateTask(Intent intent)
    {
        PackageManager packagemanager;
        ComponentName componentname;
        ComponentName componentname1;
        boolean flag;
        try
        {
            packagemanager = getPackageManager();
            componentname = intent.getComponent();
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            return false;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            return false;
        }
        componentname1 = componentname;
        if(componentname != null)
            break MISSING_BLOCK_LABEL_24;
        componentname1 = intent.resolveActivity(packagemanager);
        intent = packagemanager.getActivityInfo(componentname1, 0);
        if(((ActivityInfo) (intent)).taskAffinity == null)
            return false;
        flag = ActivityManager.getService().shouldUpRecreateTask(mToken, ((ActivityInfo) (intent)).taskAffinity);
        return flag;
    }

    public boolean showAssist(Bundle bundle)
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().showAssistFromActivity(mToken, bundle);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            return false;
        }
        return flag;
    }

    public final void showDialog(int i)
    {
        showDialog(i, null);
    }

    public final boolean showDialog(int i, Bundle bundle)
    {
        if(mManagedDialogs == null)
            mManagedDialogs = new SparseArray();
        ManagedDialog manageddialog = (ManagedDialog)mManagedDialogs.get(i);
        ManagedDialog manageddialog1 = manageddialog;
        if(manageddialog == null)
        {
            manageddialog1 = new ManagedDialog(null);
            manageddialog1.mDialog = createDialog(Integer.valueOf(i), null, bundle);
            if(manageddialog1.mDialog == null)
                return false;
            mManagedDialogs.put(i, manageddialog1);
        }
        manageddialog1.mArgs = bundle;
        onPrepareDialog(i, manageddialog1.mDialog, bundle);
        manageddialog1.mDialog.show();
        return true;
    }

    public void showLockTaskEscapeMessage()
    {
        ActivityManager.getService().showLockTaskEscapeMessage(mToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public ActionMode startActionMode(android.view.ActionMode.Callback callback)
    {
        return mWindow.getDecorView().startActionMode(callback);
    }

    public ActionMode startActionMode(android.view.ActionMode.Callback callback, int i)
    {
        return mWindow.getDecorView().startActionMode(callback, i);
    }

    public void startActivities(Intent aintent[])
    {
        startActivities(aintent, null);
    }

    public void startActivities(Intent aintent[], Bundle bundle)
    {
        mInstrumentation.execStartActivities(this, mMainThread.getApplicationThread(), mToken, this, aintent, bundle);
    }

    public void startActivity(Intent intent)
    {
        startActivity(intent, null);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        if(bundle != null)
            startActivityForResult(intent, -1, bundle);
        else
            startActivityForResult(intent, -1);
    }

    public void startActivityAsCaller(Intent intent, Bundle bundle, boolean flag, int i)
    {
        if(mParent != null)
            throw new RuntimeException("Can't be called from a child");
        bundle = transferSpringboardActivityOptions(bundle);
        intent = mInstrumentation.execStartActivityAsCaller(this, mMainThread.getApplicationThread(), mToken, this, intent, -1, bundle, flag, i);
        if(intent != null)
            mMainThread.sendActivityResult(mToken, mEmbeddedID, -1, intent.getResultCode(), intent.getResultData());
        cancelInputsAndStartExitTransition(bundle);
    }

    public void startActivityAsUser(Intent intent, Bundle bundle, UserHandle userhandle)
    {
        if(mParent != null)
            throw new RuntimeException("Can't be called from a child");
        bundle = transferSpringboardActivityOptions(bundle);
        intent = mInstrumentation.execStartActivity(this, mMainThread.getApplicationThread(), mToken, mEmbeddedID, intent, -1, bundle, userhandle);
        if(intent != null)
            mMainThread.sendActivityResult(mToken, mEmbeddedID, -1, intent.getResultCode(), intent.getResultData());
        cancelInputsAndStartExitTransition(bundle);
    }

    public void startActivityAsUser(Intent intent, UserHandle userhandle)
    {
        startActivityAsUser(intent, null, userhandle);
    }

    public void startActivityAsUserFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle, UserHandle userhandle)
    {
        startActivityForResultAsUser(intent, fragment.mWho, i, bundle, userhandle);
    }

    public void startActivityForResult(Intent intent, int i)
    {
        startActivityForResult(intent, i, null);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle)
    {
        if(mParent == null)
        {
            bundle = transferSpringboardActivityOptions(bundle);
            intent = mInstrumentation.execStartActivity(this, mMainThread.getApplicationThread(), mToken, this, intent, i, bundle);
            if(intent != null)
                mMainThread.sendActivityResult(mToken, mEmbeddedID, i, intent.getResultCode(), intent.getResultData());
            if(i >= 0)
                mStartedActivity = true;
            cancelInputsAndStartExitTransition(bundle);
        } else
        if(bundle != null)
            mParent.startActivityFromChild(this, intent, i, bundle);
        else
            mParent.startActivityFromChild(this, intent, i);
    }

    public void startActivityForResult(String s, Intent intent, int i, Bundle bundle)
    {
        Uri uri = onProvideReferrer();
        if(uri != null)
            intent.putExtra("android.intent.extra.REFERRER", uri);
        bundle = transferSpringboardActivityOptions(bundle);
        intent = mInstrumentation.execStartActivity(this, mMainThread.getApplicationThread(), mToken, s, intent, i, bundle);
        if(intent != null)
            mMainThread.sendActivityResult(mToken, s, i, intent.getResultCode(), intent.getResultData());
        cancelInputsAndStartExitTransition(bundle);
    }

    public void startActivityForResultAsUser(Intent intent, int i, Bundle bundle, UserHandle userhandle)
    {
        startActivityForResultAsUser(intent, mEmbeddedID, i, bundle, userhandle);
    }

    public void startActivityForResultAsUser(Intent intent, int i, UserHandle userhandle)
    {
        startActivityForResultAsUser(intent, i, null, userhandle);
    }

    public void startActivityForResultAsUser(Intent intent, String s, int i, Bundle bundle, UserHandle userhandle)
    {
        if(mParent != null)
            throw new RuntimeException("Can't be called from a child");
        bundle = transferSpringboardActivityOptions(bundle);
        intent = mInstrumentation.execStartActivity(this, mMainThread.getApplicationThread(), mToken, s, intent, i, bundle, userhandle);
        if(intent != null)
            mMainThread.sendActivityResult(mToken, mEmbeddedID, i, intent.getResultCode(), intent.getResultData());
        if(i >= 0)
            mStartedActivity = true;
        cancelInputsAndStartExitTransition(bundle);
    }

    public void startActivityFromChild(Activity activity, Intent intent, int i)
    {
        startActivityFromChild(activity, intent, i, null);
    }

    public void startActivityFromChild(Activity activity, Intent intent, int i, Bundle bundle)
    {
        bundle = transferSpringboardActivityOptions(bundle);
        intent = mInstrumentation.execStartActivity(this, mMainThread.getApplicationThread(), mToken, activity, intent, i, bundle);
        if(intent != null)
            mMainThread.sendActivityResult(mToken, activity.mEmbeddedID, i, intent.getResultCode(), intent.getResultData());
        cancelInputsAndStartExitTransition(bundle);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i)
    {
        startActivityFromFragment(fragment, intent, i, null);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
    {
        startActivityForResult(fragment.mWho, intent, i, bundle);
    }

    public boolean startActivityIfNeeded(Intent intent, int i)
    {
        return startActivityIfNeeded(intent, i, null);
    }

    public boolean startActivityIfNeeded(Intent intent, int i, Bundle bundle)
    {
        if(mParent != null) goto _L2; else goto _L1
_L1:
        boolean flag = true;
        Uri uri = onProvideReferrer();
        if(uri == null)
            break MISSING_BLOCK_LABEL_31;
        intent.putExtra("android.intent.extra.REFERRER", uri);
        int j;
        intent.migrateExtraStreamToClipData();
        intent.prepareToLeaveProcess(this);
        j = ActivityManager.getService().startActivity(mMainThread.getApplicationThread(), getBasePackageName(), intent, intent.resolveTypeIfNeeded(getContentResolver()), mToken, mEmbeddedID, i, 1, null, bundle);
_L4:
        Instrumentation.checkStartActivityResult(j, intent);
        if(i >= 0)
            mStartedActivity = true;
        boolean flag1;
        if(j != 1)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
_L2:
        throw new UnsupportedOperationException("startActivityIfNeeded can only be called from a top-level activity");
        bundle;
        j = ((flag) ? 1 : 0);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k)
        throws android.content.IntentSender.SendIntentException
    {
        startIntentSender(intentsender, intent, i, j, k, null);
    }

    public void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if(bundle != null)
            startIntentSenderForResult(intentsender, -1, intent, i, j, k, bundle);
        else
            startIntentSenderForResult(intentsender, -1, intent, i, j, k);
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l)
        throws android.content.IntentSender.SendIntentException
    {
        startIntentSenderForResult(intentsender, i, intent, j, k, l, null);
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if(mParent == null)
            startIntentSenderForResultInner(intentsender, mEmbeddedID, i, intent, j, k, bundle);
        else
        if(bundle != null)
            mParent.startIntentSenderFromChild(this, intentsender, i, intent, j, k, l, bundle);
        else
            mParent.startIntentSenderFromChild(this, intentsender, i, intent, j, k, l);
    }

    public void startIntentSenderFromChild(Activity activity, IntentSender intentsender, int i, Intent intent, int j, int k, int l)
        throws android.content.IntentSender.SendIntentException
    {
        startIntentSenderFromChild(activity, intentsender, i, intent, j, k, l, null);
    }

    public void startIntentSenderFromChild(Activity activity, IntentSender intentsender, int i, Intent intent, int j, int k, int l, 
            Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        startIntentSenderForResultInner(intentsender, activity.mEmbeddedID, i, intent, j, k, bundle);
    }

    public void startIntentSenderFromChildFragment(Fragment fragment, IntentSender intentsender, int i, Intent intent, int j, int k, int l, 
            Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        startIntentSenderForResultInner(intentsender, fragment.mWho, i, intent, j, k, bundle);
    }

    public void startLocalVoiceInteraction(Bundle bundle)
    {
        ActivityManager.getService().startLocalVoiceInteraction(mToken, bundle);
_L2:
        return;
        bundle;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void startLockTask()
    {
        ActivityManager.getService().startLockTaskModeByToken(mToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void startManagingCursor(Cursor cursor)
    {
        ArrayList arraylist = mManagedCursors;
        arraylist;
        JVM INSTR monitorenter ;
        ArrayList arraylist1 = mManagedCursors;
        ManagedCursor managedcursor = JVM INSTR new #25  <Class Activity$ManagedCursor>;
        managedcursor.ManagedCursor(cursor);
        arraylist1.add(managedcursor);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        cursor;
        throw cursor;
    }

    public boolean startNextMatchingActivity(Intent intent)
    {
        return startNextMatchingActivity(intent, null);
    }

    public boolean startNextMatchingActivity(Intent intent, Bundle bundle)
    {
        if(mParent == null)
        {
            boolean flag;
            try
            {
                intent.migrateExtraStreamToClipData();
                intent.prepareToLeaveProcess(this);
                flag = ActivityManager.getService().startNextMatchingActivity(mToken, intent, bundle);
            }
            // Misplaced declaration of an exception variable
            catch(Intent intent)
            {
                return false;
            }
            return flag;
        } else
        {
            throw new UnsupportedOperationException("startNextMatchingActivity can only be called from a top-level activity");
        }
    }

    public void startPostponedEnterTransition()
    {
        mActivityTransitionState.startPostponedEnterTransition();
    }

    public void startSearch(String s, boolean flag, Bundle bundle, boolean flag1)
    {
        ensureSearchManager();
        mSearchManager.startSearch(s, flag, getComponentName(), bundle, flag1);
    }

    public void stopLocalVoiceInteraction()
    {
        ActivityManager.getService().stopLocalVoiceInteraction(mToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopLockTask()
    {
        ActivityManager.getService().stopLockTaskMode();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopManagingCursor(Cursor cursor)
    {
        ArrayList arraylist = mManagedCursors;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mManagedCursors.size();
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_53;
        if(ManagedCursor._2D_get0((ManagedCursor)mManagedCursors.get(j)) != cursor)
            break MISSING_BLOCK_LABEL_56;
        mManagedCursors.remove(j);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        cursor;
        throw cursor;
    }

    public void takeKeyEvents(boolean flag)
    {
        getWindow().takeKeyEvents(flag);
    }

    public void triggerSearch(String s, Bundle bundle)
    {
        ensureSearchManager();
        mSearchManager.triggerSearch(s, getComponentName(), bundle);
    }

    public void unregisterForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(null);
    }

    private static final String AUTOFILL_RESET_NEEDED = "@android:autofillResetNeeded";
    private static final String AUTO_FILL_AUTH_WHO_PREFIX = "@android:autoFillAuth:";
    private static final boolean DEBUG_LIFECYCLE = false;
    public static final int DEFAULT_KEYS_DIALER = 1;
    public static final int DEFAULT_KEYS_DISABLE = 0;
    public static final int DEFAULT_KEYS_SEARCH_GLOBAL = 4;
    public static final int DEFAULT_KEYS_SEARCH_LOCAL = 3;
    public static final int DEFAULT_KEYS_SHORTCUT = 2;
    public static final int DONT_FINISH_TASK_WITH_ACTIVITY = 0;
    public static final int FINISH_TASK_WITH_ACTIVITY = 2;
    public static final int FINISH_TASK_WITH_ROOT_ACTIVITY = 1;
    protected static final int FOCUSED_STATE_SET[] = {
        0x101009c
    };
    static final String FRAGMENTS_TAG = "android:fragments";
    private static final String HAS_CURENT_PERMISSIONS_REQUEST_KEY = "android:hasCurrentPermissionsRequest";
    private static final String KEYBOARD_SHORTCUTS_RECEIVER_PKG_NAME = "com.android.systemui";
    private static final String LAST_AUTOFILL_ID = "android:lastAutofillId";
    private static final String REQUEST_PERMISSIONS_WHO_PREFIX = "@android:requestPermissions:";
    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_FIRST_USER = 1;
    public static final int RESULT_OK = -1;
    private static final String SAVED_DIALOGS_TAG = "android:savedDialogs";
    private static final String SAVED_DIALOG_ARGS_KEY_PREFIX = "android:dialog_args_";
    private static final String SAVED_DIALOG_IDS_KEY = "android:savedDialogIds";
    private static final String SAVED_DIALOG_KEY_PREFIX = "android:dialog_";
    private static final String TAG = "Activity";
    private static final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    ActionBar mActionBar;
    private int mActionModeTypeStarting;
    ActivityInfo mActivityInfo;
    ActivityTransitionState mActivityTransitionState;
    private Application mApplication;
    private boolean mAutoFillResetNeeded;
    private AutofillManager mAutofillManager;
    private AutofillPopupWindow mAutofillPopupWindow;
    boolean mCalled;
    private boolean mCanEnterPictureInPicture;
    private boolean mChangeCanvasToTranslucent;
    boolean mChangingConfigurations;
    private ComponentName mComponent;
    int mConfigChangeFlags;
    Configuration mCurrentConfig;
    View mDecor;
    private int mDefaultKeyMode;
    private SpannableStringBuilder mDefaultKeySsb;
    private boolean mDestroyed;
    private boolean mDoReportFullyDrawn;
    String mEmbeddedID;
    private boolean mEnableDefaultActionBarUp;
    SharedElementCallback mEnterTransitionListener;
    SharedElementCallback mExitTransitionListener;
    boolean mFinished;
    final FragmentController mFragments = FragmentController.createController(new HostCallbacks());
    final Handler mHandler = new Handler();
    private boolean mHasCurrentPermissionsRequest;
    private int mIdent;
    private final Object mInstanceTracker = StrictMode.trackActivity(this);
    private Instrumentation mInstrumentation;
    Intent mIntent;
    private IInterceptor mInterceptor;
    private int mLastAutofillId;
    NonConfigurationInstances mLastNonConfigurationInstances;
    ActivityThread mMainThread;
    private final ArrayList mManagedCursors = new ArrayList();
    private SparseArray mManagedDialogs;
    private MenuInflater mMenuInflater;
    Activity mParent;
    String mReferrer;
    private boolean mRestoredFromBundle;
    int mResultCode;
    Intent mResultData;
    boolean mResumed;
    private SearchEvent mSearchEvent;
    private SearchManager mSearchManager;
    boolean mStartedActivity;
    boolean mStopped;
    private ActivityManager.TaskDescription mTaskDescription;
    boolean mTemporaryPause;
    private CharSequence mTitle;
    private int mTitleColor;
    private boolean mTitleReady;
    private IBinder mToken;
    private TranslucentConversionListener mTranslucentCallback;
    private Thread mUiThread;
    boolean mVisibleFromClient;
    boolean mVisibleFromServer;
    private VoiceInteractor mVoiceInteractor;
    private Window mWindow;
    boolean mWindowAdded;
    private WindowManager mWindowManager;

}
