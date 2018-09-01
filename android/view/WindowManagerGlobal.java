// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.os.*;
import android.util.ArraySet;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.util.FastPrintWriter;
import java.io.*;
import java.util.ArrayList;
import miui.util.ScreenshotDrawable;

// Referenced classes of package android.view:
//            ViewRootImpl, ThreadedRenderer, IWindowManager, View, 
//            Window, WindowLeaked, IWindowSession, Display

public final class WindowManagerGlobal
{

    static Object _2D_get0(WindowManagerGlobal windowmanagerglobal)
    {
        return windowmanagerglobal.mLock;
    }

    static ArrayList _2D_get1(WindowManagerGlobal windowmanagerglobal)
    {
        return windowmanagerglobal.mRoots;
    }

    private WindowManagerGlobal()
    {
    }

    private void doTrimForeground()
    {
        boolean flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mRoots.size() - 1;
_L2:
        if(i < 0)
            break MISSING_BLOCK_LABEL_86;
        ViewRootImpl viewrootimpl;
        viewrootimpl = (ViewRootImpl)mRoots.get(i);
        if(viewrootimpl.mView == null || viewrootimpl.getHostVisibility() != 0 || viewrootimpl.mAttachInfo.mThreadedRenderer == null)
            break; /* Loop/switch isn't completed */
        flag = true;
_L3:
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        viewrootimpl.destroyHardwareResources();
          goto _L3
        Exception exception;
        exception;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        if(!flag)
            ThreadedRenderer.trimMemory(80);
        return;
    }

    private int findViewLocked(View view, boolean flag)
    {
        int i = mViews.indexOf(view);
        if(flag && i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("View=").append(view).append(" not attached to window manager").toString());
        else
            return i;
    }

    public static WindowManagerGlobal getInstance()
    {
        android/view/WindowManagerGlobal;
        JVM INSTR monitorenter ;
        WindowManagerGlobal windowmanagerglobal1;
        if(sDefaultWindowManager == null)
        {
            WindowManagerGlobal windowmanagerglobal = JVM INSTR new #2   <Class WindowManagerGlobal>;
            windowmanagerglobal.WindowManagerGlobal();
            sDefaultWindowManager = windowmanagerglobal;
        }
        windowmanagerglobal1 = sDefaultWindowManager;
        android/view/WindowManagerGlobal;
        JVM INSTR monitorexit ;
        return windowmanagerglobal1;
        Exception exception;
        exception;
        throw exception;
    }

    public static IWindowManager getWindowManagerService()
    {
        android/view/WindowManagerGlobal;
        JVM INSTR monitorenter ;
        if(sWindowManagerService != null)
            break MISSING_BLOCK_LABEL_37;
        sWindowManagerService = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        if(sWindowManagerService != null)
            ValueAnimator.setDurationScale(sWindowManagerService.getCurrentAnimatorScale());
        IWindowManager iwindowmanager = sWindowManagerService;
        android/view/WindowManagerGlobal;
        JVM INSTR monitorexit ;
        return iwindowmanager;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        android/view/WindowManagerGlobal;
        JVM INSTR monitorexit ;
        throw obj;
    }

    private static String getWindowName(ViewRootImpl viewrootimpl)
    {
        return (new StringBuilder()).append(viewrootimpl.mWindowAttributes.getTitle()).append("/").append(viewrootimpl.getClass().getName()).append('@').append(Integer.toHexString(viewrootimpl.hashCode())).toString();
    }

    public static IWindowSession getWindowSession()
    {
        android/view/WindowManagerGlobal;
        JVM INSTR monitorenter ;
        Object obj = sWindowSession;
        if(obj != null)
            break MISSING_BLOCK_LABEL_45;
        InputMethodManager inputmethodmanager = InputMethodManager.getInstance();
        obj = getWindowManagerService();
        IWindowSessionCallback.Stub stub = JVM INSTR new #6   <Class WindowManagerGlobal$1>;
        stub._cls1();
        sWindowSession = ((IWindowManager) (obj)).openSession(stub, inputmethodmanager.getClient(), inputmethodmanager.getInputContext());
        obj = sWindowSession;
        android/view/WindowManagerGlobal;
        JVM INSTR monitorexit ;
        return ((IWindowSession) (obj));
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        android/view/WindowManagerGlobal;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public static void initialize()
    {
        getWindowManagerService();
    }

    public static IWindowSession peekWindowSession()
    {
        android/view/WindowManagerGlobal;
        JVM INSTR monitorenter ;
        IWindowSession iwindowsession = sWindowSession;
        android/view/WindowManagerGlobal;
        JVM INSTR monitorexit ;
        return iwindowsession;
        Exception exception;
        exception;
        throw exception;
    }

    private void removeViewLocked(int i, boolean flag)
    {
        ViewRootImpl viewrootimpl = (ViewRootImpl)mRoots.get(i);
        View view = viewrootimpl.getView();
        if(view != null)
        {
            InputMethodManager inputmethodmanager = InputMethodManager.getInstance();
            if(inputmethodmanager != null)
                inputmethodmanager.windowDismissed(((View)mViews.get(i)).getWindowToken());
        }
        flag = viewrootimpl.die(flag);
        if(view != null)
        {
            view.assignParent(null);
            if(flag)
                mDyingViews.add(view);
        }
    }

    public static boolean shouldDestroyEglContext(int i)
    {
        if(i >= 80)
            return true;
        return i >= 60 && ActivityManager.isHighEndGfx() ^ true;
    }

    public static void trimForeground()
    {
        if(ThreadedRenderer.sTrimForeground && ThreadedRenderer.isAvailable())
            getInstance().doTrimForeground();
    }

    public void addView(View view, ViewGroup.LayoutParams layoutparams, Display display, Window window)
    {
        WindowManager.LayoutParams layoutparams1;
        Object obj1;
        int i;
        if(view == null)
            throw new IllegalArgumentException("view must not be null");
        if(display == null)
            throw new IllegalArgumentException("display must not be null");
        if(!(layoutparams instanceof WindowManager.LayoutParams))
            throw new IllegalArgumentException("Params must be WindowManager.LayoutParams");
        ScreenshotDrawable.processBlurBehindFlag(view, layoutparams, false);
        layoutparams1 = (WindowManager.LayoutParams)layoutparams;
        Object obj;
        int j;
        int k;
        if(window != null)
        {
            window.adjustLayoutParamsForSubWindow(layoutparams1);
        } else
        {
            layoutparams = view.getContext();
            if(layoutparams != null && (layoutparams.getApplicationInfo().flags & 0x20000000) != 0)
                layoutparams1.flags = layoutparams1.flags | 0x1000000;
        }
        obj = null;
        layoutparams = null;
        obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mSystemPropertyUpdater == null)
        {
            window = JVM INSTR new #8   <Class WindowManagerGlobal$2>;
            window.this. _cls2();
            mSystemPropertyUpdater = window;
            SystemProperties.addChangeCallback(mSystemPropertyUpdater);
        }
        i = findViewLocked(view, false);
        if(i < 0) goto _L2; else goto _L1
_L1:
        if(!mDyingViews.contains(view)) goto _L4; else goto _L3
_L3:
        ((ViewRootImpl)mRoots.get(i)).doDie();
_L2:
        window = obj;
        if(layoutparams1.type < 1000) goto _L6; else goto _L5
_L5:
        window = obj;
        if(layoutparams1.type > 1999) goto _L6; else goto _L7
_L7:
        j = mViews.size();
        k = 0;
_L8:
        window = layoutparams;
        if(k >= j)
            break; /* Loop/switch isn't completed */
        if(((ViewRootImpl)mRoots.get(k)).mWindow.asBinder() == layoutparams1.token)
            layoutparams = (View)mViews.get(k);
        k++;
        if(true) goto _L8; else goto _L6
_L4:
        layoutparams = JVM INSTR new #395 <Class IllegalStateException>;
        display = JVM INSTR new #146 <Class StringBuilder>;
        display.StringBuilder();
        layoutparams.IllegalStateException(display.append("View ").append(view).append(" has already been added to the window manager.").toString());
        throw layoutparams;
        view;
        obj1;
        JVM INSTR monitorexit ;
        throw view;
_L6:
        layoutparams = JVM INSTR new #110 <Class ViewRootImpl>;
        layoutparams.ViewRootImpl(view.getContext(), display);
        view.setLayoutParams(layoutparams1);
        mViews.add(view);
        mRoots.add(layoutparams);
        mParams.add(layoutparams1);
        layoutparams.setView(view, layoutparams1, window);
        obj1;
        JVM INSTR monitorexit ;
        return;
        view;
        if(i < 0)
            break MISSING_BLOCK_LABEL_410;
        removeViewLocked(i, true);
        throw view;
    }

    public void changeCanvasOpacity(IBinder ibinder, boolean flag)
    {
        if(ibinder == null)
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mParams.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        if(((WindowManager.LayoutParams)mParams.get(i)).token != ibinder)
            break MISSING_BLOCK_LABEL_66;
        ((ViewRootImpl)mRoots.get(i)).changeCanvasOpacity(flag);
        obj;
        JVM INSTR monitorexit ;
        return;
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        ibinder;
        throw ibinder;
    }

    public void closeAll(IBinder ibinder, String s, String s1)
    {
        closeAllExceptView(ibinder, null, s, s1);
    }

    public void closeAllExceptView(IBinder ibinder, View view, String s, String s1)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mViews.size();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        if(view == null)
            break MISSING_BLOCK_LABEL_45;
        if(mViews.get(j) == view)
            continue; /* Loop/switch isn't completed */
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_68;
        if(((WindowManager.LayoutParams)mParams.get(j)).token != ibinder)
            continue; /* Loop/switch isn't completed */
        ViewRootImpl viewrootimpl = (ViewRootImpl)mRoots.get(j);
        if(s == null)
            break MISSING_BLOCK_LABEL_170;
        WindowLeaked windowleaked = JVM INSTR new #427 <Class WindowLeaked>;
        StringBuilder stringbuilder = JVM INSTR new #146 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        windowleaked.WindowLeaked(stringbuilder.append(s1).append(" ").append(s).append(" has leaked window ").append(viewrootimpl.getView()).append(" that was originally added here").toString());
        windowleaked.setStackTrace(viewrootimpl.getLocation().getStackTrace());
        Log.e("WindowManager", "", windowleaked);
        removeViewLocked(j, false);
        j++;
          goto _L3
_L2:
        return;
        ibinder;
        throw ibinder;
    }

    void doRemoveView(ViewRootImpl viewrootimpl)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mRoots.indexOf(viewrootimpl);
        if(i < 0)
            break MISSING_BLOCK_LABEL_59;
        mRoots.remove(i);
        mParams.remove(i);
        viewrootimpl = (View)mViews.remove(i);
        mDyingViews.remove(viewrootimpl);
        obj;
        JVM INSTR monitorexit ;
        if(ThreadedRenderer.sTrimForeground && ThreadedRenderer.isAvailable())
            doTrimForeground();
        return;
        viewrootimpl;
        throw viewrootimpl;
    }

    public void dumpGfxInfo(FileDescriptor filedescriptor, String as[])
    {
        FastPrintWriter fastprintwriter = new FastPrintWriter(new FileOutputStream(filedescriptor));
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        i = mViews.size();
        fastprintwriter.println("Profile data in ms:");
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = (ViewRootImpl)mRoots.get(j);
        fastprintwriter.printf("\n\t%s (visibility=%d)", new Object[] {
            getWindowName(((ViewRootImpl) (obj1))), Integer.valueOf(((ViewRootImpl) (obj1)).getHostVisibility())
        });
        obj1 = ((ViewRootImpl) (obj1)).getView().mAttachInfo.mThreadedRenderer;
        if(obj1 == null)
            continue; /* Loop/switch isn't completed */
        ((ThreadedRenderer) (obj1)).dumpGfxInfo(fastprintwriter, filedescriptor, as);
        j++;
          goto _L3
_L2:
        fastprintwriter.println("\nView hierarchy:\n");
        int k;
        int l;
        k = 0;
        l = 0;
        filedescriptor = new int[2];
        j = 0;
_L5:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        as = (ViewRootImpl)mRoots.get(j);
        as.dumpGfxInfo(filedescriptor);
        fastprintwriter.printf("  %s\n  %d views, %.2f kB of display lists", new Object[] {
            getWindowName(as), Integer.valueOf(filedescriptor[0]), Float.valueOf((float)filedescriptor[1] / 1024F)
        });
        fastprintwriter.printf("\n\n", new Object[0]);
        k += filedescriptor[0];
        l += filedescriptor[1];
        j++;
        if(true) goto _L5; else goto _L4
_L4:
        fastprintwriter.printf("\nTotal ViewRootImpl: %d\n", new Object[] {
            Integer.valueOf(i)
        });
        fastprintwriter.printf("Total Views:        %d\n", new Object[] {
            Integer.valueOf(k)
        });
        fastprintwriter.printf("Total DisplayList:  %.2f kB\n\n", new Object[] {
            Float.valueOf((float)l / 1024F)
        });
        obj;
        JVM INSTR monitorexit ;
        fastprintwriter.flush();
        return;
        filedescriptor;
        obj;
        JVM INSTR monitorexit ;
        throw filedescriptor;
        filedescriptor;
        fastprintwriter.flush();
        throw filedescriptor;
    }

    public View getRootView(String s)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mRoots.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        ViewRootImpl viewrootimpl = (ViewRootImpl)mRoots.get(i);
        if(!s.equals(getWindowName(viewrootimpl)))
            break MISSING_BLOCK_LABEL_56;
        s = viewrootimpl.getView();
        obj;
        JVM INSTR monitorexit ;
        return s;
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        return null;
        s;
        throw s;
    }

    public ArrayList getRootViews(IBinder ibinder)
    {
        ArrayList arraylist = new ArrayList();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mRoots.size();
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_208;
        WindowManager.LayoutParams layoutparams;
        layoutparams = (WindowManager.LayoutParams)mParams.get(j);
        if(layoutparams.token != null)
            break; /* Loop/switch isn't completed */
_L9:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(layoutparams.token == ibinder) goto _L4; else goto _L3
_L3:
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(layoutparams.type < 1000) goto _L6; else goto _L5
_L5:
        flag1 = flag;
        if(layoutparams.type > 1999) goto _L6; else goto _L7
_L7:
        int k = 0;
_L10:
        flag1 = flag;
        if(k >= i) goto _L6; else goto _L8
_L8:
        View view = (View)mViews.get(k);
        WindowManager.LayoutParams layoutparams1 = (WindowManager.LayoutParams)mParams.get(k);
        if(layoutparams.token != view.getWindowToken() || layoutparams1.token != ibinder)
            break MISSING_BLOCK_LABEL_201;
        flag1 = true;
_L6:
        if(!flag1) goto _L9; else goto _L4
_L4:
        arraylist.add((ViewRootImpl)mRoots.get(j));
          goto _L9
        ibinder;
        throw ibinder;
        k++;
          goto _L10
        return arraylist;
          goto _L9
    }

    public String[] getViewRootNames()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        String as[];
        i = mRoots.size();
        as = new String[i];
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        as[j] = getWindowName((ViewRootImpl)mRoots.get(j));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return as;
        Exception exception;
        exception;
        throw exception;
    }

    public View getWindowView(IBinder ibinder)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mViews.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        View view;
        IBinder ibinder1;
        view = (View)mViews.get(j);
        ibinder1 = view.getWindowToken();
        if(ibinder1 != ibinder)
            break MISSING_BLOCK_LABEL_56;
        obj;
        JVM INSTR monitorexit ;
        return view;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return null;
        ibinder;
        throw ibinder;
    }

    public void removeView(View view, boolean flag)
    {
        if(view == null)
            throw new IllegalArgumentException("view must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        View view1;
        int i = findViewLocked(view, true);
        view1 = ((ViewRootImpl)mRoots.get(i)).getView();
        removeViewLocked(i, flag);
        if(view1 != view)
            break MISSING_BLOCK_LABEL_63;
        obj;
        JVM INSTR monitorexit ;
        return;
        IllegalStateException illegalstateexception = JVM INSTR new #395 <Class IllegalStateException>;
        StringBuilder stringbuilder = JVM INSTR new #146 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        illegalstateexception.IllegalStateException(stringbuilder.append("Calling with view ").append(view).append(" but the ViewAncestor is attached to ").append(view1).toString());
        throw illegalstateexception;
        view;
        obj;
        JVM INSTR monitorexit ;
        throw view;
    }

    public void reportNewConfiguration(Configuration configuration)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        Configuration configuration1;
        i = mViews.size();
        configuration1 = JVM INSTR new #545 <Class Configuration>;
        configuration1.Configuration(configuration);
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((ViewRootImpl)mRoots.get(j)).requestUpdateConfiguration(configuration1);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        configuration;
_L4:
        obj;
        JVM INSTR monitorexit ;
        throw configuration;
        configuration;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setStoppedState(IBinder ibinder, boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mViews.size();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_49;
        if(((WindowManager.LayoutParams)mParams.get(j)).token != ibinder)
            continue; /* Loop/switch isn't completed */
        ((ViewRootImpl)mRoots.get(j)).setWindowStopped(flag);
        j++;
          goto _L3
_L2:
        return;
        ibinder;
        throw ibinder;
    }

    public void trimMemory(int i)
    {
        int j;
        if(!ThreadedRenderer.isAvailable())
            break MISSING_BLOCK_LABEL_75;
        j = i;
        if(!shouldDestroyEglContext(i))
            break MISSING_BLOCK_LABEL_61;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        i = mRoots.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        ((ViewRootImpl)mRoots.get(i)).destroyHardwareResources();
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        j = 80;
        ThreadedRenderer.trimMemory(j);
        if(ThreadedRenderer.sTrimForeground)
            doTrimForeground();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void updateViewLayout(View view, ViewGroup.LayoutParams layoutparams)
    {
        WindowManager.LayoutParams layoutparams1;
        if(view == null)
            throw new IllegalArgumentException("view must not be null");
        if(!(layoutparams instanceof WindowManager.LayoutParams))
            throw new IllegalArgumentException("Params must be WindowManager.LayoutParams");
        ScreenshotDrawable.processBlurBehindFlag(view, layoutparams, true);
        layoutparams1 = (WindowManager.LayoutParams)layoutparams;
        view.setLayoutParams(layoutparams1);
        layoutparams = ((ViewGroup.LayoutParams) (mLock));
        layoutparams;
        JVM INSTR monitorenter ;
        int i = findViewLocked(view, true);
        view = (ViewRootImpl)mRoots.get(i);
        mParams.remove(i);
        mParams.add(i, layoutparams1);
        view.setLayoutParams(layoutparams1, false);
        layoutparams;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    public static final int ADD_APP_EXITING = -4;
    public static final int ADD_BAD_APP_TOKEN = -1;
    public static final int ADD_BAD_SUBWINDOW_TOKEN = -2;
    public static final int ADD_DUPLICATE_ADD = -5;
    public static final int ADD_FLAG_ALWAYS_CONSUME_NAV_BAR = 4;
    public static final int ADD_FLAG_APP_VISIBLE = 2;
    public static final int ADD_FLAG_IN_TOUCH_MODE = 1;
    public static final int ADD_INVALID_DISPLAY = -9;
    public static final int ADD_INVALID_TYPE = -10;
    public static final int ADD_MULTIPLE_SINGLETON = -7;
    public static final int ADD_NOT_APP_TOKEN = -3;
    public static final int ADD_OKAY = 0;
    public static final int ADD_PERMISSION_DENIED = -8;
    public static final int ADD_STARTING_NOT_NEEDED = -6;
    public static final int RELAYOUT_DEFER_SURFACE_DESTROY = 2;
    public static final int RELAYOUT_INSETS_PENDING = 1;
    public static final int RELAYOUT_RES_CONSUME_ALWAYS_NAV_BAR = 64;
    public static final int RELAYOUT_RES_DRAG_RESIZING_DOCKED = 8;
    public static final int RELAYOUT_RES_DRAG_RESIZING_FREEFORM = 16;
    public static final int RELAYOUT_RES_FIRST_TIME = 2;
    public static final int RELAYOUT_RES_IN_TOUCH_MODE = 1;
    public static final int RELAYOUT_RES_SURFACE_CHANGED = 4;
    public static final int RELAYOUT_RES_SURFACE_RESIZED = 32;
    private static final String TAG = "WindowManager";
    private static WindowManagerGlobal sDefaultWindowManager;
    private static IWindowManager sWindowManagerService;
    private static IWindowSession sWindowSession;
    private final ArraySet mDyingViews = new ArraySet();
    private final Object mLock = new Object();
    private final ArrayList mParams = new ArrayList();
    private final ArrayList mRoots = new ArrayList();
    private Runnable mSystemPropertyUpdater;
    private final ArrayList mViews = new ArrayList();

    // Unreferenced inner class android/view/WindowManagerGlobal$1

/* anonymous class */
    static final class _cls1 extends IWindowSessionCallback.Stub
    {

        public void onAnimatorScaleChanged(float f)
        {
            ValueAnimator.setDurationScale(f);
        }

    }


    // Unreferenced inner class android/view/WindowManagerGlobal$2

/* anonymous class */
    class _cls2
        implements Runnable
    {

        public void run()
        {
            Object obj = WindowManagerGlobal._2D_get0(WindowManagerGlobal.this);
            obj;
            JVM INSTR monitorenter ;
            int i = WindowManagerGlobal._2D_get1(WindowManagerGlobal.this).size() - 1;
_L2:
            if(i < 0)
                break; /* Loop/switch isn't completed */
            ((ViewRootImpl)WindowManagerGlobal._2D_get1(WindowManagerGlobal.this).get(i)).loadSystemProperties();
            i--;
            if(true) goto _L2; else goto _L1
_L1:
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final WindowManagerGlobal this$0;

            
            {
                this$0 = WindowManagerGlobal.this;
                super();
            }
    }

}
