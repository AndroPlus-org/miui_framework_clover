// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.util.TypedValue;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.app.WindowDecorActionBar;
import com.android.internal.policy.MiuiPhoneWindow;
import java.lang.ref.WeakReference;

// Referenced classes of package android.app:
//            Activity, ActionBar, SearchManager

public class Dialog
    implements DialogInterface, android.view.Window.Callback, android.view.KeyEvent.Callback, android.view.View.OnCreateContextMenuListener, android.view.Window.OnWindowDismissedCallback
{
    private static final class ListenersHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 67 69: default 32
        //                       67 33
        //                       68 58
        //                       69 83;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            ((android.content.DialogInterface.OnDismissListener)message.obj).onDismiss((DialogInterface)mDialog.get());
            continue; /* Loop/switch isn't completed */
_L3:
            ((android.content.DialogInterface.OnCancelListener)message.obj).onCancel((DialogInterface)mDialog.get());
            continue; /* Loop/switch isn't completed */
_L4:
            ((android.content.DialogInterface.OnShowListener)message.obj).onShow((DialogInterface)mDialog.get());
            if(true) goto _L1; else goto _L5
_L5:
        }

        private final WeakReference mDialog;

        public ListenersHandler(Dialog dialog)
        {
            mDialog = new WeakReference(dialog);
        }
    }


    public Dialog(Context context)
    {
        this(context, 0, true);
    }

    public Dialog(Context context, int i)
    {
        this(context, i, true);
    }

    Dialog(Context context, int i, boolean flag)
    {
        mCancelable = true;
        mCreated = false;
        mShowing = false;
        mCanceled = false;
        mHandler = new Handler();
        mActionModeTypeStarting = 0;
        mDismissAction = new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)0, this);
        if(flag)
        {
            int j = i;
            if(i == 0)
            {
                TypedValue typedvalue = new TypedValue();
                context.getTheme().resolveAttribute(0x1010308, typedvalue, true);
                j = typedvalue.resourceId;
            }
            mContext = new ContextThemeWrapper(context, j);
        } else
        {
            mContext = context;
        }
        mWindowManager = (WindowManager)context.getSystemService("window");
        context = new MiuiPhoneWindow(mContext);
        mWindow = context;
        context.setCallback(this);
        context.setOnWindowDismissedCallback(this);
        context.setOnWindowSwipeDismissedCallback(new _.Lambda.c44uHH2WE4sJvw5tZZB6gRzEaHI(this));
        context.setWindowManager(mWindowManager, null, null);
        context.setGravity(17);
        mListenersHandler = new ListenersHandler(this);
    }

    protected Dialog(Context context, boolean flag, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        this(context);
        mCancelable = flag;
        updateWindowForCancelable();
        setOnCancelListener(oncancellistener);
    }

    protected Dialog(Context context, boolean flag, Message message)
    {
        this(context);
        mCancelable = flag;
        updateWindowForCancelable();
        mCancelMessage = message;
    }

    private ComponentName getAssociatedActivity()
    {
        Object obj = null;
        Activity activity = mOwnerActivity;
        for(Context context = getContext(); activity == null && context != null;)
            if(context instanceof Activity)
                activity = (Activity)context;
            else
            if(context instanceof ContextWrapper)
                context = ((ContextWrapper)context).getBaseContext();
            else
                context = null;

        ComponentName componentname;
        if(activity == null)
            componentname = obj;
        else
            componentname = activity.getComponentName();
        return componentname;
    }

    private void sendDismissMessage()
    {
        if(mDismissMessage != null)
            Message.obtain(mDismissMessage).sendToTarget();
    }

    private void sendShowMessage()
    {
        if(mShowMessage != null)
            Message.obtain(mShowMessage).sendToTarget();
    }

    private void updateWindowForCancelable()
    {
        mWindow.setCloseOnSwipeEnabled(mCancelable);
    }

    void _2D_android_app_Dialog_2D_mthref_2D_0()
    {
        dismissDialog();
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        mWindow.addContentView(view, layoutparams);
    }

    public void cancel()
    {
        if(!mCanceled && mCancelMessage != null)
        {
            mCanceled = true;
            Message.obtain(mCancelMessage).sendToTarget();
        }
        dismiss();
    }

    public void closeOptionsMenu()
    {
        if(mWindow.hasFeature(0))
            mWindow.closePanel(0);
    }

    public void create()
    {
        if(!mCreated)
            dispatchOnCreate(null);
    }

    public void dismiss()
    {
        if(Looper.myLooper() == mHandler.getLooper())
            dismissDialog();
        else
            mHandler.post(mDismissAction);
    }

    void dismissDialog()
    {
        if(mDecor == null || mShowing ^ true)
            return;
        if(mWindow.isDestroyed())
        {
            Log.e("Dialog", "Tried to dismissDialog() but the Dialog's window was already destroyed!");
            return;
        }
        mWindowManager.removeViewImmediate(mDecor);
        if(mActionMode != null)
            mActionMode.finish();
        mDecor = null;
        mWindow.closeAllPanels();
        onStop();
        mShowing = false;
        sendDismissMessage();
        return;
        Exception exception;
        exception;
        if(mActionMode != null)
            mActionMode.finish();
        mDecor = null;
        mWindow.closeAllPanels();
        onStop();
        mShowing = false;
        sendDismissMessage();
        throw exception;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        if(mWindow.superDispatchGenericMotionEvent(motionevent))
            return true;
        else
            return onGenericMotionEvent(motionevent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        android.view.KeyEvent.DispatcherState dispatcherstate = null;
        if(mOnKeyListener != null && mOnKeyListener.onKey(this, keyevent.getKeyCode(), keyevent))
            return true;
        if(mWindow.superDispatchKeyEvent(keyevent))
            return true;
        if(mDecor != null)
            dispatcherstate = mDecor.getKeyDispatcherState();
        return keyevent.dispatch(this, dispatcherstate, this);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        if(mWindow.superDispatchKeyShortcutEvent(keyevent))
            return true;
        else
            return onKeyShortcut(keyevent.getKeyCode(), keyevent);
    }

    void dispatchOnCreate(Bundle bundle)
    {
        if(!mCreated)
        {
            onCreate(bundle);
            mCreated = true;
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        accessibilityevent.setClassName(getClass().getName());
        accessibilityevent.setPackageName(mContext.getPackageName());
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        boolean flag;
        if(((android.view.ViewGroup.LayoutParams) (layoutparams)).width == -1)
        {
            if(((android.view.ViewGroup.LayoutParams) (layoutparams)).height == -1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        accessibilityevent.setFullScreen(flag);
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if(mWindow.superDispatchTouchEvent(motionevent))
            return true;
        else
            return onTouchEvent(motionevent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        if(mWindow.superDispatchTrackballEvent(motionevent))
            return true;
        else
            return onTrackballEvent(motionevent);
    }

    public View findViewById(int i)
    {
        return mWindow.findViewById(i);
    }

    public ActionBar getActionBar()
    {
        return mActionBar;
    }

    public final Context getContext()
    {
        return mContext;
    }

    public View getCurrentFocus()
    {
        View view = null;
        if(mWindow != null)
            view = mWindow.getCurrentFocus();
        return view;
    }

    public LayoutInflater getLayoutInflater()
    {
        return getWindow().getLayoutInflater();
    }

    public final Activity getOwnerActivity()
    {
        return mOwnerActivity;
    }

    public final SearchEvent getSearchEvent()
    {
        return mSearchEvent;
    }

    public final int getVolumeControlStream()
    {
        return getWindow().getVolumeControlStream();
    }

    public Window getWindow()
    {
        return mWindow;
    }

    public void hide()
    {
        if(mDecor != null)
            mDecor.setVisibility(8);
    }

    public void invalidateOptionsMenu()
    {
        if(mWindow.hasFeature(0))
            mWindow.invalidatePanelMenu(0);
    }

    public boolean isShowing()
    {
        return mShowing;
    }

    void lambda$_2D_android_app_Dialog_6926()
    {
        if(mCancelable)
            cancel();
    }

    public void onActionModeFinished(ActionMode actionmode)
    {
        if(actionmode == mActionMode)
            mActionMode = null;
    }

    public void onActionModeStarted(ActionMode actionmode)
    {
        mActionMode = actionmode;
    }

    public void onAttachedToWindow()
    {
    }

    public void onBackPressed()
    {
        if(mCancelable)
            cancel();
    }

    public void onContentChanged()
    {
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onContextMenuClosed(Menu menu)
    {
    }

    protected void onCreate(Bundle bundle)
    {
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        if(i == 0)
            return onCreateOptionsMenu(menu);
        else
            return false;
    }

    public View onCreatePanelView(int i)
    {
        return null;
    }

    public void onDetachedFromWindow()
    {
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            keyevent.startTracking();
            return true;
        } else
        {
            return false;
        }
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
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(i == 4 && keyevent.isTracking() && keyevent.isCanceled() ^ true)
        {
            onBackPressed();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        return false;
    }

    public boolean onMenuOpened(int i, Menu menu)
    {
        if(i == 8)
            mActionBar.dispatchMenuVisibilityChanged(true);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu)
    {
    }

    public void onPanelClosed(int i, Menu menu)
    {
        if(i == 8)
            mActionBar.dispatchMenuVisibilityChanged(false);
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        return true;
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        boolean flag = false;
        if(i == 0 && menu != null)
        {
            if(onPrepareOptionsMenu(menu))
                flag = menu.hasVisibleItems();
            return flag;
        } else
        {
            return true;
        }
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        Bundle bundle1 = bundle.getBundle("android:dialogHierarchy");
        if(bundle1 == null)
            return;
        dispatchOnCreate(bundle);
        mWindow.restoreHierarchyState(bundle1);
        if(bundle.getBoolean("android:dialogShowing"))
            show();
    }

    public Bundle onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("android:dialogShowing", mShowing);
        if(mCreated)
            bundle.putBundle("android:dialogHierarchy", mWindow.saveHierarchyState());
        return bundle;
    }

    public boolean onSearchRequested()
    {
        SearchManager searchmanager = (SearchManager)mContext.getSystemService("search");
        ComponentName componentname = getAssociatedActivity();
        if(componentname != null && searchmanager.getSearchableInfo(componentname) != null)
        {
            searchmanager.startSearch(null, false, componentname, null, false);
            dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onSearchRequested(SearchEvent searchevent)
    {
        mSearchEvent = searchevent;
        return onSearchRequested();
    }

    protected void onStart()
    {
        if(mActionBar != null)
            mActionBar.setShowHideAnimationEnabled(true);
    }

    protected void onStop()
    {
        if(mActionBar != null)
            mActionBar.setShowHideAnimationEnabled(false);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mCancelable && mShowing && mWindow.shouldCloseOnTouch(mContext, motionevent))
        {
            cancel();
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

    public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams layoutparams)
    {
        if(mDecor != null)
            mWindowManager.updateViewLayout(mDecor, layoutparams);
    }

    public void onWindowDismissed(boolean flag, boolean flag1)
    {
        dismiss();
    }

    public void onWindowFocusChanged(boolean flag)
    {
    }

    public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
    {
        if(mActionBar != null && mActionModeTypeStarting == 0)
            return mActionBar.startActionMode(callback);
        else
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
        if(mWindow.hasFeature(0))
            mWindow.openPanel(0, null);
    }

    public void registerForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(this);
    }

    public final boolean requestWindowFeature(int i)
    {
        return getWindow().requestFeature(i);
    }

    public void setCancelMessage(Message message)
    {
        mCancelMessage = message;
    }

    public void setCancelable(boolean flag)
    {
        mCancelable = flag;
        updateWindowForCancelable();
    }

    public void setCanceledOnTouchOutside(boolean flag)
    {
        if(flag && mCancelable ^ true)
        {
            mCancelable = true;
            updateWindowForCancelable();
        }
        mWindow.setCloseOnTouchOutside(flag);
    }

    public void setContentView(int i)
    {
        mWindow.setContentView(i);
    }

    public void setContentView(View view)
    {
        mWindow.setContentView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        mWindow.setContentView(view, layoutparams);
    }

    public void setDismissMessage(Message message)
    {
        mDismissMessage = message;
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

    public void setOnCancelListener(android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        if(mCancelAndDismissTaken != null)
            throw new IllegalStateException((new StringBuilder()).append("OnCancelListener is already taken by ").append(mCancelAndDismissTaken).append(" and can not be replaced.").toString());
        if(oncancellistener != null)
            mCancelMessage = mListenersHandler.obtainMessage(68, oncancellistener);
        else
            mCancelMessage = null;
    }

    public void setOnDismissListener(android.content.DialogInterface.OnDismissListener ondismisslistener)
    {
        if(mCancelAndDismissTaken != null)
            throw new IllegalStateException((new StringBuilder()).append("OnDismissListener is already taken by ").append(mCancelAndDismissTaken).append(" and can not be replaced.").toString());
        if(ondismisslistener != null)
            mDismissMessage = mListenersHandler.obtainMessage(67, ondismisslistener);
        else
            mDismissMessage = null;
    }

    public void setOnKeyListener(android.content.DialogInterface.OnKeyListener onkeylistener)
    {
        mOnKeyListener = onkeylistener;
    }

    public void setOnShowListener(android.content.DialogInterface.OnShowListener onshowlistener)
    {
        if(onshowlistener != null)
            mShowMessage = mListenersHandler.obtainMessage(69, onshowlistener);
        else
            mShowMessage = null;
    }

    public final void setOwnerActivity(Activity activity)
    {
        mOwnerActivity = activity;
        getWindow().setVolumeControlStream(mOwnerActivity.getVolumeControlStream());
    }

    public void setTitle(int i)
    {
        setTitle(mContext.getText(i));
    }

    public void setTitle(CharSequence charsequence)
    {
        mWindow.setTitle(charsequence);
        mWindow.getAttributes().setTitle(charsequence);
    }

    public final void setVolumeControlStream(int i)
    {
        getWindow().setVolumeControlStream(i);
    }

    public void show()
    {
        if(mShowing)
        {
            if(mDecor != null)
            {
                if(mWindow.hasFeature(8))
                    mWindow.invalidatePanelMenu(8);
                mDecor.setVisibility(0);
            }
            return;
        }
        mCanceled = false;
        android.view.WindowManager.LayoutParams layoutparams;
        android.view.WindowManager.LayoutParams layoutparams1;
        if(!mCreated)
        {
            dispatchOnCreate(null);
        } else
        {
            android.content.res.Configuration configuration = mContext.getResources().getConfiguration();
            mWindow.getDecorView().dispatchConfigurationChanged(configuration);
        }
        onStart();
        mDecor = mWindow.getDecorView();
        if(mActionBar == null && mWindow.hasFeature(8))
        {
            ApplicationInfo applicationinfo = mContext.getApplicationInfo();
            mWindow.setDefaultIcon(applicationinfo.icon);
            mWindow.setDefaultLogo(applicationinfo.logo);
            mActionBar = new WindowDecorActionBar(this);
        }
        layoutparams1 = mWindow.getAttributes();
        layoutparams = layoutparams1;
        if((layoutparams1.softInputMode & 0x100) == 0)
        {
            layoutparams = new android.view.WindowManager.LayoutParams();
            layoutparams.copyFrom(layoutparams1);
            layoutparams.softInputMode = layoutparams.softInputMode | 0x100;
        }
        mWindowManager.addView(mDecor, layoutparams);
        mShowing = true;
        sendShowMessage();
    }

    public boolean takeCancelAndDismissListeners(String s, android.content.DialogInterface.OnCancelListener oncancellistener, android.content.DialogInterface.OnDismissListener ondismisslistener)
    {
        if(mCancelAndDismissTaken != null)
            mCancelAndDismissTaken = null;
        else
        if(mCancelMessage != null || mDismissMessage != null)
            return false;
        setOnCancelListener(oncancellistener);
        setOnDismissListener(ondismisslistener);
        mCancelAndDismissTaken = s;
        return true;
    }

    public void takeKeyEvents(boolean flag)
    {
        mWindow.takeKeyEvents(flag);
    }

    public void unregisterForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(null);
    }

    private static final int CANCEL = 68;
    private static final String DIALOG_HIERARCHY_TAG = "android:dialogHierarchy";
    private static final String DIALOG_SHOWING_TAG = "android:dialogShowing";
    private static final int DISMISS = 67;
    private static final int SHOW = 69;
    private static final String TAG = "Dialog";
    private ActionBar mActionBar;
    private ActionMode mActionMode;
    private int mActionModeTypeStarting;
    private String mCancelAndDismissTaken;
    private Message mCancelMessage;
    protected boolean mCancelable;
    private boolean mCanceled;
    final Context mContext;
    private boolean mCreated;
    View mDecor;
    private final Runnable mDismissAction;
    private Message mDismissMessage;
    private final Handler mHandler;
    private final Handler mListenersHandler;
    private android.content.DialogInterface.OnKeyListener mOnKeyListener;
    private Activity mOwnerActivity;
    private SearchEvent mSearchEvent;
    private Message mShowMessage;
    private boolean mShowing;
    final Window mWindow;
    private final WindowManager mWindowManager;
}
