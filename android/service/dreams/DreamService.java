// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.dreams;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.*;
import android.util.MathUtils;
import android.util.Slog;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.policy.MiuiPhoneWindow;
import com.android.internal.util.DumpUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.service.dreams:
//            IDreamManager

public class DreamService extends Service
    implements android.view.Window.Callback
{
    private final class DreamServiceWrapper extends IDreamService.Stub
    {

        public void attach(final IBinder windowToken, final boolean canDoze, IRemoteCallback iremotecallback)
        {
            DreamService._2D_get2(DreamService.this).post(iremotecallback. new Runnable() {

                public void run()
                {
                    DreamService._2D_wrap0(_fld0, windowToken, canDoze, started);
                }

                final DreamServiceWrapper this$1;
                final boolean val$canDoze;
                final IRemoteCallback val$started;
                final IBinder val$windowToken;

            
            {
                this$1 = final_dreamservicewrapper;
                windowToken = ibinder;
                canDoze = flag;
                started = IRemoteCallback.this;
                super();
            }
            }
);
        }

        public void detach()
        {
            DreamService._2D_get2(DreamService.this).post(new Runnable() {

                public void run()
                {
                    DreamService._2D_wrap1(_fld0);
                }

                final DreamServiceWrapper this$1;

            
            {
                this$1 = DreamServiceWrapper.this;
                super();
            }
            }
);
        }

        public void wakeUp()
        {
            DreamService._2D_get2(DreamService.this).post(new Runnable() {

                public void run()
                {
                    DreamService._2D_wrap2(_fld0, true);
                }

                final DreamServiceWrapper this$1;

            
            {
                this$1 = DreamServiceWrapper.this;
                super();
            }
            }
);
        }

        final DreamService this$0;

        private DreamServiceWrapper()
        {
            this$0 = DreamService.this;
            super();
        }

        DreamServiceWrapper(DreamServiceWrapper dreamservicewrapper)
        {
            this();
        }
    }


    static String _2D_get0(DreamService dreamservice)
    {
        return dreamservice.TAG;
    }

    static boolean _2D_get1(DreamService dreamservice)
    {
        return dreamservice.mDebug;
    }

    static Handler _2D_get2(DreamService dreamservice)
    {
        return dreamservice.mHandler;
    }

    static Window _2D_get3(DreamService dreamservice)
    {
        return dreamservice.mWindow;
    }

    static boolean _2D_get4(DreamService dreamservice)
    {
        return dreamservice.mWindowless;
    }

    static boolean _2D_set0(DreamService dreamservice, boolean flag)
    {
        dreamservice.mStarted = flag;
        return flag;
    }

    static void _2D_wrap0(DreamService dreamservice, IBinder ibinder, boolean flag, IRemoteCallback iremotecallback)
    {
        dreamservice.attach(ibinder, flag, iremotecallback);
    }

    static void _2D_wrap1(DreamService dreamservice)
    {
        dreamservice.detach();
    }

    static void _2D_wrap2(DreamService dreamservice, boolean flag)
    {
        dreamservice.wakeUp(flag);
    }

    public DreamService()
    {
        mLowProfile = true;
        mScreenBright = true;
        mDozeScreenState = 0;
        mDozeScreenBrightness = -1;
        mDebug = false;
    }

    private int applyFlags(int i, int j, int k)
    {
        return k & i | j & k;
    }

    private void applySystemUiVisibilityFlags(int i, int j)
    {
        View view;
        if(mWindow == null)
            view = null;
        else
            view = mWindow.getDecorView();
        if(view != null)
            view.setSystemUiVisibility(applyFlags(view.getSystemUiVisibility(), i, j));
    }

    private void applyWindowFlags(int i, int j)
    {
        if(mWindow != null)
        {
            android.view.WindowManager.LayoutParams layoutparams = mWindow.getAttributes();
            layoutparams.flags = applyFlags(layoutparams.flags, i, j);
            mWindow.setAttributes(layoutparams);
            mWindow.getWindowManager().updateViewLayout(mWindow.getDecorView(), layoutparams);
        }
    }

    private final void attach(IBinder ibinder, boolean flag, final IRemoteCallback started)
    {
        boolean flag1;
        flag1 = false;
        if(mWindowToken != null)
        {
            Slog.e(TAG, (new StringBuilder()).append("attach() called when already attached with token=").append(mWindowToken).toString());
            return;
        }
        if(!mFinished && !mWaking) goto _L2; else goto _L1
_L1:
        Slog.w(TAG, "attach() called after dream already finished");
        mSandman.finishSelf(ibinder, true);
_L4:
        return;
_L2:
        mWindowToken = ibinder;
        mCanDoze = flag;
        if(mWindowless && mCanDoze ^ true)
            throw new IllegalStateException("Only doze dreams can be windowless");
        if(!mWindowless)
        {
            mWindow = new MiuiPhoneWindow(this);
            mWindow.setCallback(this);
            mWindow.requestFeature(1);
            mWindow.setBackgroundDrawable(new ColorDrawable(0xff000000));
            mWindow.setFormat(-1);
            if(mDebug)
                Slog.v(TAG, String.format("Attaching window token: %s to window of type %s", new Object[] {
                    ibinder, Integer.valueOf(2023)
                }));
            android.view.WindowManager.LayoutParams layoutparams = mWindow.getAttributes();
            layoutparams.type = 2023;
            layoutparams.token = ibinder;
            layoutparams.windowAnimations = 0x10302ec;
            int i = layoutparams.flags;
            int j;
            char c;
            if(mFullscreen)
                j = 1024;
            else
                j = 0;
            if(mScreenBright)
                c = '\200';
            else
                c = '\0';
            layoutparams.flags = c | (0x490101 | j) | i;
            mWindow.setAttributes(layoutparams);
            mWindow.clearFlags(0x80000000);
            mWindow.setWindowManager(null, ibinder, "dream", true);
            j = ((flag1) ? 1 : 0);
            if(mLowProfile)
                j = 1;
            applySystemUiVisibilityFlags(j, 1);
            try
            {
                getWindowManager().addView(mWindow.getDecorView(), mWindow.getAttributes());
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder)
            {
                Slog.i(TAG, "attach() called after window token already removed, dream will finish soon");
                mWindow = null;
                return;
            }
        }
        mHandler.post(new Runnable() {

            public void run()
            {
                if(DreamService._2D_get3(DreamService.this) == null && !DreamService._2D_get4(DreamService.this))
                    break MISSING_BLOCK_LABEL_69;
                if(DreamService._2D_get1(DreamService.this))
                    Slog.v(DreamService._2D_get0(DreamService.this), "Calling onDreamingStarted()");
                DreamService._2D_set0(DreamService.this, true);
                onDreamingStarted();
                started.sendResult(null);
                return;
                Object obj;
                obj;
                throw ((RemoteException) (obj)).rethrowFromSystemServer();
                obj;
                try
                {
                    started.sendResult(null);
                }
                catch(RemoteException remoteexception)
                {
                    throw remoteexception.rethrowFromSystemServer();
                }
                throw obj;
            }

            final DreamService this$0;
            final IRemoteCallback val$started;

            
            {
                this$0 = DreamService.this;
                started = iremotecallback;
                super();
            }
        }
);
        return;
        ibinder;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static int clampAbsoluteBrightness(int i)
    {
        return MathUtils.constrain(i, 0, 255);
    }

    private final void detach()
    {
        if(mStarted)
        {
            if(mDebug)
                Slog.v(TAG, "detach(): Calling onDreamingStopped()");
            mStarted = false;
            onDreamingStopped();
        }
        if(mWindow != null)
        {
            if(mDebug)
                Slog.v(TAG, "detach(): Removing window from window manager");
            mWindow.getWindowManager().removeViewImmediate(mWindow.getDecorView());
            mWindow = null;
        }
        if(mWindowToken != null)
        {
            WindowManagerGlobal.getInstance().closeAll(mWindowToken, getClass().getName(), "Dream");
            mWindowToken = null;
            mCanDoze = false;
        }
    }

    private boolean getSystemUiVisibilityFlagValue(int i, boolean flag)
    {
        View view;
        if(mWindow == null)
            view = null;
        else
            view = mWindow.getDecorView();
        if(view != null)
            if((view.getSystemUiVisibility() & i) != 0)
                flag = true;
            else
                flag = false;
        return flag;
    }

    private boolean getWindowFlagValue(int i, boolean flag)
    {
        if(mWindow != null)
            if((mWindow.getAttributes().flags & i) != 0)
                flag = true;
            else
                flag = false;
        return flag;
    }

    private void updateDoze()
    {
        if(!mDozing)
            break MISSING_BLOCK_LABEL_28;
        mSandman.startDozing(mWindowToken, mDozeScreenState, mDozeScreenBrightness);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void wakeUp(boolean flag)
    {
        if(mDebug)
            Slog.v(TAG, (new StringBuilder()).append("wakeUp(): fromSystem=").append(flag).append(", mWaking=").append(mWaking).append(", mFinished=").append(mFinished).toString());
        if(!mWaking && mFinished ^ true)
        {
            mWaking = true;
            onWakeUp();
            if(!flag && mFinished ^ true)
                if(mWindowToken == null)
                    Slog.w(TAG, "WakeUp was called before the dream was attached.");
                else
                    try
                    {
                        mSandman.finishSelf(mWindowToken, false);
                    }
                    catch(RemoteException remoteexception) { }
        }
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getWindow().addContentView(view, layoutparams);
    }

    public boolean canDoze()
    {
        return mCanDoze;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        if(!mInteractive)
        {
            if(mDebug)
                Slog.v(TAG, "Waking up on genericMotionEvent");
            wakeUp();
            return true;
        } else
        {
            return mWindow.superDispatchGenericMotionEvent(motionevent);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if(!mInteractive)
        {
            if(mDebug)
                Slog.v(TAG, "Waking up on keyEvent");
            wakeUp();
            return true;
        }
        if(keyevent.getKeyCode() == 4)
        {
            if(mDebug)
                Slog.v(TAG, "Waking up on back key");
            wakeUp();
            return true;
        } else
        {
            return mWindow.superDispatchKeyEvent(keyevent);
        }
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        if(!mInteractive)
        {
            if(mDebug)
                Slog.v(TAG, "Waking up on keyShortcutEvent");
            wakeUp();
            return true;
        } else
        {
            return mWindow.superDispatchKeyShortcutEvent(keyevent);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if(!mInteractive)
        {
            if(mDebug)
                Slog.v(TAG, "Waking up on touchEvent");
            wakeUp();
            return true;
        } else
        {
            return mWindow.superDispatchTouchEvent(motionevent);
        }
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        if(!mInteractive)
        {
            if(mDebug)
                Slog.v(TAG, "Waking up on trackballEvent");
            wakeUp();
            return true;
        } else
        {
            return mWindow.superDispatchTrackballEvent(motionevent);
        }
    }

    protected void dump(final FileDescriptor fd, PrintWriter printwriter, final String args[])
    {
        DumpUtils.dumpAsync(mHandler, new com.android.internal.util.DumpUtils.Dump() {

            public void dump(PrintWriter printwriter1, String s)
            {
                dumpOnHandler(fd, printwriter1, args);
            }

            final DreamService this$0;
            final String val$args[];
            final FileDescriptor val$fd;

            
            {
                this$0 = DreamService.this;
                fd = filedescriptor;
                args = as;
                super();
            }
        }
, printwriter, "", 1000L);
    }

    protected void dumpOnHandler(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print((new StringBuilder()).append(TAG).append(": ").toString());
        if(mWindowToken == null)
            printwriter.println("stopped");
        else
            printwriter.println((new StringBuilder()).append("running (token=").append(mWindowToken).append(")").toString());
        printwriter.println((new StringBuilder()).append("  window: ").append(mWindow).toString());
        printwriter.print("  flags:");
        if(isInteractive())
            printwriter.print(" interactive");
        if(isLowProfile())
            printwriter.print(" lowprofile");
        if(isFullscreen())
            printwriter.print(" fullscreen");
        if(isScreenBright())
            printwriter.print(" bright");
        if(isWindowless())
            printwriter.print(" windowless");
        if(!isDozing()) goto _L2; else goto _L1
_L1:
        printwriter.print(" dozing");
_L4:
        printwriter.println();
        if(canDoze())
        {
            printwriter.println((new StringBuilder()).append("  doze screen state: ").append(Display.stateToString(mDozeScreenState)).toString());
            printwriter.println((new StringBuilder()).append("  doze screen brightness: ").append(mDozeScreenBrightness).toString());
        }
        return;
_L2:
        if(canDoze())
            printwriter.print(" candoze");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public View findViewById(int i)
    {
        return getWindow().findViewById(i);
    }

    public final void finish()
    {
        if(mDebug)
            Slog.v(TAG, (new StringBuilder()).append("finish(): mFinished=").append(mFinished).toString());
        if(!mFinished)
        {
            mFinished = true;
            if(mWindowToken == null)
                Slog.w(TAG, "Finish was called before the dream was attached.");
            else
                try
                {
                    mSandman.finishSelf(mWindowToken, true);
                }
                catch(RemoteException remoteexception) { }
            stopSelf();
        }
    }

    public int getDozeScreenBrightness()
    {
        return mDozeScreenBrightness;
    }

    public int getDozeScreenState()
    {
        return mDozeScreenState;
    }

    public Window getWindow()
    {
        return mWindow;
    }

    public WindowManager getWindowManager()
    {
        WindowManager windowmanager = null;
        if(mWindow != null)
            windowmanager = mWindow.getWindowManager();
        return windowmanager;
    }

    public boolean isDozing()
    {
        return mDozing;
    }

    public boolean isFullscreen()
    {
        return mFullscreen;
    }

    public boolean isInteractive()
    {
        return mInteractive;
    }

    public boolean isLowProfile()
    {
        return getSystemUiVisibilityFlagValue(1, mLowProfile);
    }

    public boolean isScreenBright()
    {
        return getWindowFlagValue(128, mScreenBright);
    }

    public boolean isWindowless()
    {
        return mWindowless;
    }

    public void onActionModeFinished(ActionMode actionmode)
    {
    }

    public void onActionModeStarted(ActionMode actionmode)
    {
    }

    public void onAttachedToWindow()
    {
    }

    public final IBinder onBind(Intent intent)
    {
        if(mDebug)
            Slog.v(TAG, (new StringBuilder()).append("onBind() intent = ").append(intent).toString());
        return new DreamServiceWrapper(null);
    }

    public void onContentChanged()
    {
    }

    public void onCreate()
    {
        if(mDebug)
            Slog.v(TAG, "onCreate()");
        super.onCreate();
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        return false;
    }

    public View onCreatePanelView(int i)
    {
        return null;
    }

    public void onDestroy()
    {
        if(mDebug)
            Slog.v(TAG, "onDestroy()");
        detach();
        super.onDestroy();
    }

    public void onDetachedFromWindow()
    {
    }

    public void onDreamingStarted()
    {
        if(mDebug)
            Slog.v(TAG, "onDreamingStarted()");
    }

    public void onDreamingStopped()
    {
        if(mDebug)
            Slog.v(TAG, "onDreamingStopped()");
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        return false;
    }

    public boolean onMenuOpened(int i, Menu menu)
    {
        return false;
    }

    public void onPanelClosed(int i, Menu menu)
    {
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        return false;
    }

    public boolean onSearchRequested()
    {
        return false;
    }

    public boolean onSearchRequested(SearchEvent searchevent)
    {
        return onSearchRequested();
    }

    public void onWakeUp()
    {
        finish();
    }

    public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams layoutparams)
    {
    }

    public void onWindowFocusChanged(boolean flag)
    {
    }

    public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
    {
        return null;
    }

    public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int i)
    {
        return null;
    }

    public void setContentView(int i)
    {
        getWindow().setContentView(i);
    }

    public void setContentView(View view)
    {
        getWindow().setContentView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getWindow().setContentView(view, layoutparams);
    }

    public void setDebug(boolean flag)
    {
        mDebug = flag;
    }

    public void setDozeScreenBrightness(int i)
    {
        int j = i;
        if(i != -1)
            j = clampAbsoluteBrightness(i);
        if(mDozeScreenBrightness != j)
        {
            mDozeScreenBrightness = j;
            updateDoze();
        }
    }

    public void setDozeScreenState(int i)
    {
        if(mDozeScreenState != i)
        {
            mDozeScreenState = i;
            updateDoze();
        }
    }

    public void setFullscreen(boolean flag)
    {
        if(mFullscreen != flag)
        {
            mFullscreen = flag;
            char c;
            if(mFullscreen)
                c = '\u0400';
            else
                c = '\0';
            applyWindowFlags(c, 1024);
        }
    }

    public void setInteractive(boolean flag)
    {
        mInteractive = flag;
    }

    public void setLowProfile(boolean flag)
    {
        if(mLowProfile != flag)
        {
            mLowProfile = flag;
            int i;
            if(mLowProfile)
                i = 1;
            else
                i = 0;
            applySystemUiVisibilityFlags(i, 1);
        }
    }

    public void setScreenBright(boolean flag)
    {
        if(mScreenBright != flag)
        {
            mScreenBright = flag;
            char c;
            if(mScreenBright)
                c = '\200';
            else
                c = '\0';
            applyWindowFlags(c, 128);
        }
    }

    public void setWindowless(boolean flag)
    {
        mWindowless = flag;
    }

    public void startDozing()
    {
        if(mCanDoze && mDozing ^ true)
        {
            mDozing = true;
            updateDoze();
        }
    }

    public void stopDozing()
    {
        if(!mDozing)
            break MISSING_BLOCK_LABEL_25;
        mDozing = false;
        mSandman.stopDozing(mWindowToken);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void wakeUp()
    {
        wakeUp(false);
    }

    public static final String DREAM_META_DATA = "android.service.dream";
    public static final String DREAM_SERVICE = "dreams";
    public static final String SERVICE_INTERFACE = "android.service.dreams.DreamService";
    private final String TAG = (new StringBuilder()).append(android/service/dreams/DreamService.getSimpleName()).append("[").append(getClass().getSimpleName()).append("]").toString();
    private boolean mCanDoze;
    private boolean mDebug;
    private int mDozeScreenBrightness;
    private int mDozeScreenState;
    private boolean mDozing;
    private boolean mFinished;
    private boolean mFullscreen;
    private final Handler mHandler = new Handler();
    private boolean mInteractive;
    private boolean mLowProfile;
    private final IDreamManager mSandman = IDreamManager.Stub.asInterface(ServiceManager.getService("dreams"));
    private boolean mScreenBright;
    private boolean mStarted;
    private boolean mWaking;
    private Window mWindow;
    private IBinder mWindowToken;
    private boolean mWindowless;
}
