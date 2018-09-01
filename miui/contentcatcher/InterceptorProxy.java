// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.*;
import android.util.Log;
import android.view.*;
import com.miui.internal.contentcatcher.IInterceptor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import miui.contentcatcher.sdk.WebViewDetector;
import miui.contentcatcher.sdk.utils.WebViewUtils;
import miui.process.*;
import miui.util.ObjectReference;
import miui.util.ReflectionUtils;

// Referenced classes of package miui.contentcatcher:
//            IInterceptorContainer, InterceptorFactory

public class InterceptorProxy
    implements IInterceptor, IInterceptorContainer
{
    private static class H extends Handler
    {

        public void handleMessage(Message message)
        {
            IInterceptorContainer iinterceptorcontainer;
label0:
            {
                if(mInterceptorProxyRef != null)
                {
                    iinterceptorcontainer = (IInterceptorContainer)mInterceptorProxyRef.get();
                    if(iinterceptorcontainer != null)
                        break label0;
                }
                return;
            }
            message.what;
            JVM INSTR tableswitch 0 7: default 72
        //                       0 73
        //                       1 108
        //                       2 131
        //                       3 154
        //                       4 177
        //                       5 200
        //                       6 223
        //                       7 246;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
            return;
_L2:
            if(mActivityRef != null)
            {
                message = (Activity)mActivityRef.get();
                if(message != null)
                    iinterceptorcontainer.setInterceptor(InterceptorFactory.createInterceptor(message));
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if(iinterceptorcontainer.getInterceptor() != null)
                iinterceptorcontainer.getInterceptor().notifyActivityCreate();
            continue; /* Loop/switch isn't completed */
_L4:
            if(iinterceptorcontainer.getInterceptor() != null)
                iinterceptorcontainer.getInterceptor().notifyActivityStart();
            continue; /* Loop/switch isn't completed */
_L5:
            if(iinterceptorcontainer.getInterceptor() != null)
                iinterceptorcontainer.getInterceptor().notifyActivityResume();
            continue; /* Loop/switch isn't completed */
_L6:
            if(iinterceptorcontainer.getInterceptor() != null)
                iinterceptorcontainer.getInterceptor().notifyActivityPause();
            continue; /* Loop/switch isn't completed */
_L7:
            if(iinterceptorcontainer.getInterceptor() != null)
                iinterceptorcontainer.getInterceptor().notifyActivityStop();
            continue; /* Loop/switch isn't completed */
_L8:
            if(iinterceptorcontainer.getInterceptor() != null)
                iinterceptorcontainer.getInterceptor().notifyActivityDestroy();
            continue; /* Loop/switch isn't completed */
_L9:
            if(iinterceptorcontainer.getInterceptor() != null)
                iinterceptorcontainer.getInterceptor().notifyContentChange();
            if(true) goto _L1; else goto _L10
_L10:
        }

        public static final int ACTIVITY_CREATE = 1;
        public static final int ACTIVITY_DESTROY = 6;
        public static final int ACTIVITY_PAUSE = 4;
        public static final int ACTIVITY_RESUME = 3;
        public static final int ACTIVITY_START = 2;
        public static final int ACTIVITY_STOP = 5;
        public static final int CONTENT_CHANGED = 7;
        public static final int CREATE_INJECTOR = 0;
        private WeakReference mActivityRef;
        private WeakReference mInterceptorProxyRef;

        public H(Looper looper, Activity activity, IInterceptorContainer iinterceptorcontainer)
        {
            super(looper);
            mActivityRef = new WeakReference(activity);
            mInterceptorProxyRef = new WeakReference(iinterceptorcontainer);
        }
    }


    static IInterceptor _2D_get0(InterceptorProxy interceptorproxy)
    {
        return interceptorproxy.mInterceptor;
    }

    private InterceptorProxy(Activity activity)
    {
        if(DBG)
            Log.d("InterceptorProxy", "InterceptorProxy create");
        mHandler = new H(getWorkThread().getLooper(), activity, this);
        mHandler.sendEmptyMessage(0);
    }

    private static void addMiuiApplication()
    {
        if(mMiuiApplicationThread == null)
        {
            mMiuiApplicationThread = new MiuiApplicationThread();
            ProcessManager.addMiuiApplicationThread(mMiuiApplicationThread);
        }
    }

    public static boolean checkAndInitWebView(View view)
    {
        if(!WebViewUtils.isWebView(view))
            break MISSING_BLOCK_LABEL_48;
        Runnable runnable = JVM INSTR new #10  <Class InterceptorProxy$1>;
        runnable._cls1(view);
        postToUiHandler(runnable);
        return true;
        view;
        Log.e("ContentCatcher", (new StringBuilder()).append("checkAndInitWebView-Exception: ").append(view).toString());
        return false;
    }

    public static InterceptorProxy create(Activity activity)
    {
        addMiuiApplication();
        return null;
    }

    public static Activity getAttachedActivity(View view)
    {
label0:
        {
            if(view == null)
                return null;
            view = view.getContext();
            do
            {
                View view1 = view;
                view = view1;
                if(view1 == null)
                    break;
                view = view1;
                if(!((view1 instanceof Activity) ^ true))
                    break;
                view = view1;
                if(!(view1 instanceof ContextWrapper))
                    break;
                Context context = ((ContextWrapper)view1).getBaseContext();
                view = context;
                if(view1 != context)
                    continue;
                if(!sSpecialContexts.contains(context.getClass().getName()))
                    break label0;
                view = ReflectionUtils.tryGetObjectField(context, "mBase", android/content/Context);
                if(view != null)
                    view = (Context)view.get();
                else
                    view = null;
                if(view == null || context == view)
                    break label0;
                Log.i("InterceptorProxy", (new StringBuilder()).append("Get New base context : ").append(view).append(" and Cur base context is:").append(context.getClass().getName()).toString());
                break;
            } while(true);
            if(view != null && (view instanceof Activity))
                return (Activity)view;
            else
                return null;
        }
        return null;
    }

    public static Handler getWorkHandler()
    {
        if(sWorkHandler != null) goto _L2; else goto _L1
_L1:
        miui/contentcatcher/InterceptorProxy;
        JVM INSTR monitorenter ;
        if(sWorkHandler == null)
        {
            Handler handler = JVM INSTR new #62  <Class Handler>;
            handler.Handler(getWorkThread().getLooper());
            sWorkHandler = handler;
        }
        miui/contentcatcher/InterceptorProxy;
        JVM INSTR monitorexit ;
_L2:
        return sWorkHandler;
        Exception exception;
        exception;
        throw exception;
    }

    public static HandlerThread getWorkThread()
    {
        if(sWorkerThread != null) goto _L2; else goto _L1
_L1:
        miui/contentcatcher/InterceptorProxy;
        JVM INSTR monitorenter ;
        if(sWorkerThread == null)
        {
            HandlerThread handlerthread = JVM INSTR new #112 <Class HandlerThread>;
            handlerthread.HandlerThread("Binder:interceptor", -4);
            handlerthread.start();
            sWorkerThread = handlerthread;
        }
        miui/contentcatcher/InterceptorProxy;
        JVM INSTR monitorexit ;
_L2:
        return sWorkerThread;
        Exception exception;
        exception;
        throw exception;
    }

    public static void postToUiHandler(Runnable runnable)
    {
        postToUiHandler(runnable, 0L);
    }

    public static void postToUiHandler(Runnable runnable, long l)
    {
        if(runnable != null)
            sUiHandler.postDelayed(runnable, l);
    }

    public static void postToWorkHandler(Runnable runnable)
    {
        postToWorkHandler(runnable, 0L);
    }

    public static void postToWorkHandler(Runnable runnable, long l)
    {
        if(runnable == null)
            break MISSING_BLOCK_LABEL_19;
        Handler handler = getWorkHandler();
        if(handler == null)
            break MISSING_BLOCK_LABEL_19;
        handler.postDelayed(runnable, l);
_L1:
        return;
        runnable;
        Log.w("InterceptorProxy", (new StringBuilder()).append("postToWorkHandler: ").append(runnable.toString()).toString());
          goto _L1
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent, View view, Activity activity)
    {
        if(mInterceptor != null)
        {
            if(DBG)
                Log.d("InterceptorProxy", (new StringBuilder()).append("dispatchKeyEvent event ").append(keyevent).append(" rootView ").append(view).toString());
            return mInterceptor.dispatchKeyEvent(keyevent, view, activity);
        } else
        {
            return false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent, View view, Activity activity)
    {
        if(mInterceptor != null)
            return mInterceptor.dispatchTouchEvent(motionevent, view, activity);
        else
            return false;
    }

    public IInterceptor getInterceptor()
    {
        return mInterceptor;
    }

    public Handler getUiHandler()
    {
        return sUiHandler;
    }

    public void notifyActivityCreate()
    {
        if(DBG)
            Log.d("InterceptorProxy", "notifyActivityCreate");
        mHandler.sendEmptyMessage(1);
    }

    public void notifyActivityDestroy()
    {
        if(DBG)
            Log.d("InterceptorProxy", "notifyActivityDestroy");
        mHandler.sendEmptyMessage(6);
    }

    public void notifyActivityPause()
    {
        if(DBG)
            Log.d("InterceptorProxy", "notifyActivityPause");
        mHandler.sendEmptyMessage(4);
    }

    public void notifyActivityResume()
    {
        if(DBG)
            Log.d("InterceptorProxy", "notifyActivityResume");
        mHandler.sendEmptyMessage(3);
    }

    public void notifyActivityStart()
    {
        if(DBG)
            Log.d("InterceptorProxy", "notifyActivityStart");
        mHandler.sendEmptyMessage(2);
    }

    public void notifyActivityStop()
    {
        if(DBG)
            Log.d("InterceptorProxy", "notifyActivityStop");
        mHandler.sendEmptyMessage(5);
    }

    public void notifyContentChange()
    {
        if(mInterceptor != null)
            mInterceptor.notifyContentChange();
    }

    public void notifyWebView(View view, boolean flag)
    {
        if(DBG)
            Log.d("InterceptorProxy", (new StringBuilder()).append("notifyWebView ").append(view).toString());
        if(mInterceptor != null)
            mInterceptor.notifyWebView(view, flag);
    }

    public void setInterceptor(IInterceptor iinterceptor)
    {
        mInterceptor = iinterceptor;
    }

    public void setWebView(final View view, final boolean isLoad)
    {
        if(DBG)
            Log.d("InterceptorProxy", (new StringBuilder()).append("setWebView ").append(view).toString());
        sUiHandler.post(new Runnable() {

            public void run()
            {
                if(isLoad)
                    WebViewUtils.initWebViewJsInterface(view, WebViewDetector.getInstance(), "MiWebViewDetector");
                if(InterceptorProxy._2D_get0(InterceptorProxy.this) != null)
                    InterceptorProxy._2D_get0(InterceptorProxy.this).setWebView(view, isLoad);
            }

            final InterceptorProxy this$0;
            final boolean val$isLoad;
            final View val$view;

            
            {
                this$0 = InterceptorProxy.this;
                isLoad = flag;
                view = view1;
                super();
            }
        }
);
    }

    static final boolean DBG = SystemProperties.getBoolean("interceptor.debug.on", false);
    static final boolean INTERCEPTOR_ENABLED = SystemProperties.getBoolean("interceptor.enabled", true);
    private static final String TAG = "InterceptorProxy";
    private static IMiuiApplicationThread mMiuiApplicationThread = null;
    private static final ArrayList sBlackList;
    private static final ArrayList sSpecialContexts;
    private static Handler sUiHandler = new Handler(Looper.getMainLooper());
    private static volatile Handler sWorkHandler;
    private static volatile HandlerThread sWorkerThread;
    private Handler mHandler;
    private IInterceptor mInterceptor;

    static 
    {
        sBlackList = new ArrayList();
        sBlackList.add("com.miui.home.launcher.Launcher");
        sBlackList.add("com.android.settings.FallbackHome");
        sBlackList.add("com.android.settings.CryptKeeper");
        sSpecialContexts = new ArrayList();
        sSpecialContexts.add("com.tencent.tbs.common.resources.PluginContextWrapper");
    }

    // Unreferenced inner class miui/contentcatcher/InterceptorProxy$1

/* anonymous class */
    static final class _cls1
        implements Runnable
    {

        public void run()
        {
            WebViewUtils.initWebViewJsInterface(view, WebViewDetector.getInstance(), "MiWebViewDetector");
_L1:
            return;
            Exception exception;
            exception;
            Log.e("ContentCatcher", (new StringBuilder()).append("checkAndInitWebView-Runnable:").append(exception).toString());
              goto _L1
        }

        final View val$view;

            
            {
                view = view1;
                super();
            }
    }

}
