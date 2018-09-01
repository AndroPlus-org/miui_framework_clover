// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.app.Activity;
import android.app.Application;
import android.content.*;
import android.graphics.drawable.Icon;
import android.os.*;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.*;
import libcore.io.IoUtils;

// Referenced classes of package android.print:
//            IPrintManager, PrinterDiscoverySession, PrintJob, PrintJobInfo, 
//            PrintJobId, PrinterId, PrintDocumentAdapter, PrintAttributes, 
//            ILayoutResultCallback, IPrintDocumentAdapterObserver, IWriteResultCallback, PageRange, 
//            PrintDocumentInfo

public final class PrintManager
{
    public static final class PrintDocumentAdapterDelegate extends IPrintDocumentAdapter.Stub
        implements android.app.Application.ActivityLifecycleCallbacks
    {

        static Object _2D_get0(PrintDocumentAdapterDelegate printdocumentadapterdelegate)
        {
            return printdocumentadapterdelegate.mLock;
        }

        static DestroyableCallback _2D_set0(PrintDocumentAdapterDelegate printdocumentadapterdelegate, DestroyableCallback destroyablecallback)
        {
            printdocumentadapterdelegate.mPendingCallback = destroyablecallback;
            return destroyablecallback;
        }

        static void _2D_wrap0(PrintDocumentAdapterDelegate printdocumentadapterdelegate)
        {
            printdocumentadapterdelegate.destroyLocked();
        }

        private void destroyLocked()
        {
            mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            mActivity = null;
            mDocumentAdapter = null;
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            mHandler.removeMessages(3);
            mHandler.removeMessages(4);
            mHandler = null;
            mObserver = null;
            if(mPendingCallback != null)
            {
                mPendingCallback.destroy();
                mPendingCallback = null;
            }
        }

        private boolean isDestroyedLocked()
        {
            boolean flag;
            if(mActivity == null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void finish()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(!isDestroyedLocked())
                mHandler.obtainMessage(4, mDocumentAdapter).sendToTarget();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void kill(String s)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(!isDestroyedLocked())
                mHandler.obtainMessage(5, s).sendToTarget();
            obj;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public void layout(PrintAttributes printattributes, PrintAttributes printattributes1, ILayoutResultCallback ilayoutresultcallback, Bundle bundle, int i)
        {
            Object obj;
            Object obj1;
            obj = CancellationSignal.createTransport();
            boolean flag;
            try
            {
                ilayoutresultcallback.onLayoutStarted(((android.os.ICancellationSignal) (obj)), i);
            }
            // Misplaced declaration of an exception variable
            catch(PrintAttributes printattributes)
            {
                Log.e("PrintManager", "Error notifying for layout start", printattributes);
                return;
            }
            obj1 = mLock;
            obj1;
            JVM INSTR monitorenter ;
            flag = isDestroyedLocked();
            if(!flag)
                break MISSING_BLOCK_LABEL_50;
            obj1;
            JVM INSTR monitorexit ;
            return;
            CancellationSignal cancellationsignal = CancellationSignal.fromTransport(((android.os.ICancellationSignal) (obj)));
            obj = SomeArgs.obtain();
            obj.arg1 = mDocumentAdapter;
            obj.arg2 = printattributes;
            obj.arg3 = printattributes1;
            obj.arg4 = cancellationsignal;
            printattributes = JVM INSTR new #17  <Class PrintManager$PrintDocumentAdapterDelegate$MyLayoutResultCallback>;
            printattributes.this. MyLayoutResultCallback(ilayoutresultcallback, i);
            obj.arg5 = printattributes;
            obj.arg6 = bundle;
            mHandler.obtainMessage(2, obj).sendToTarget();
            obj1;
            JVM INSTR monitorexit ;
            return;
            printattributes;
            throw printattributes;
        }

        public void onActivityCreated(Activity activity, Bundle bundle)
        {
        }

        public void onActivityDestroyed(Activity activity)
        {
            IPrintDocumentAdapterObserver iprintdocumentadapterobserver = null;
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(activity == mActivity)
            {
                iprintdocumentadapterobserver = mObserver;
                destroyLocked();
            }
            obj;
            JVM INSTR monitorexit ;
            if(iprintdocumentadapterobserver == null)
                break MISSING_BLOCK_LABEL_38;
            iprintdocumentadapterobserver.onDestroy();
_L1:
            return;
            activity;
            throw activity;
            activity;
            Log.e("PrintManager", "Error announcing destroyed state", activity);
              goto _L1
        }

        public void onActivityPaused(Activity activity)
        {
        }

        public void onActivityResumed(Activity activity)
        {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
        {
        }

        public void onActivityStarted(Activity activity)
        {
        }

        public void onActivityStopped(Activity activity)
        {
        }

        public void setObserver(IPrintDocumentAdapterObserver iprintdocumentadapterobserver)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            mObserver = iprintdocumentadapterobserver;
            flag = isDestroyedLocked();
            obj;
            JVM INSTR monitorexit ;
            if(!flag || iprintdocumentadapterobserver == null)
                break MISSING_BLOCK_LABEL_33;
            iprintdocumentadapterobserver.onDestroy();
_L1:
            return;
            iprintdocumentadapterobserver;
            throw iprintdocumentadapterobserver;
            iprintdocumentadapterobserver;
            Log.e("PrintManager", "Error announcing destroyed state", iprintdocumentadapterobserver);
              goto _L1
        }

        public void start()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(!isDestroyedLocked())
                mHandler.obtainMessage(1, mDocumentAdapter).sendToTarget();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void write(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, IWriteResultCallback iwriteresultcallback, int i)
        {
            Object obj;
            Object obj1;
            obj = CancellationSignal.createTransport();
            boolean flag;
            try
            {
                iwriteresultcallback.onWriteStarted(((android.os.ICancellationSignal) (obj)), i);
            }
            // Misplaced declaration of an exception variable
            catch(PageRange apagerange[])
            {
                Log.e("PrintManager", "Error notifying for write start", apagerange);
                return;
            }
            obj1 = mLock;
            obj1;
            JVM INSTR monitorenter ;
            flag = isDestroyedLocked();
            if(!flag)
                break MISSING_BLOCK_LABEL_50;
            obj1;
            JVM INSTR monitorexit ;
            return;
            CancellationSignal cancellationsignal = CancellationSignal.fromTransport(((android.os.ICancellationSignal) (obj)));
            obj = SomeArgs.obtain();
            obj.arg1 = mDocumentAdapter;
            obj.arg2 = apagerange;
            obj.arg3 = parcelfiledescriptor;
            obj.arg4 = cancellationsignal;
            apagerange = JVM INSTR new #20  <Class PrintManager$PrintDocumentAdapterDelegate$MyWriteResultCallback>;
            apagerange.this. MyWriteResultCallback(iwriteresultcallback, parcelfiledescriptor, i);
            obj.arg5 = apagerange;
            mHandler.obtainMessage(3, obj).sendToTarget();
            obj1;
            JVM INSTR monitorexit ;
            return;
            apagerange;
            throw apagerange;
        }

        private Activity mActivity;
        private PrintDocumentAdapter mDocumentAdapter;
        private Handler mHandler;
        private final Object mLock = new Object();
        private IPrintDocumentAdapterObserver mObserver;
        private DestroyableCallback mPendingCallback;

        public PrintDocumentAdapterDelegate(Activity activity, PrintDocumentAdapter printdocumentadapter)
        {
            if(activity.isFinishing())
            {
                throw new IllegalStateException("Cannot start printing for finishing activity");
            } else
            {
                mActivity = activity;
                mDocumentAdapter = printdocumentadapter;
                mHandler = new MyHandler(mActivity.getMainLooper());
                mActivity.getApplication().registerActivityLifecycleCallbacks(this);
                return;
            }
        }
    }

    private static interface PrintDocumentAdapterDelegate.DestroyableCallback
    {

        public abstract void destroy();
    }

    private final class PrintDocumentAdapterDelegate.MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 5: default 40
        //                       1 70
        //                       2 81
        //                       3 161
        //                       4 230
        //                       5 267;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L6:
            break MISSING_BLOCK_LABEL_267;
_L1:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown message: ").append(message.what).toString());
_L2:
            ((PrintDocumentAdapter)message.obj).onStart();
_L8:
            return;
_L3:
            SomeArgs someargs = (SomeArgs)message.obj;
            message = (PrintDocumentAdapter)someargs.arg1;
            PrintAttributes printattributes = (PrintAttributes)someargs.arg2;
            PrintAttributes printattributes1 = (PrintAttributes)someargs.arg3;
            CancellationSignal cancellationsignal = (CancellationSignal)someargs.arg4;
            PrintDocumentAdapter.LayoutResultCallback layoutresultcallback = (PrintDocumentAdapter.LayoutResultCallback)someargs.arg5;
            Bundle bundle = (Bundle)someargs.arg6;
            someargs.recycle();
            message.onLayout(printattributes, printattributes1, cancellationsignal, layoutresultcallback, bundle);
            continue; /* Loop/switch isn't completed */
_L4:
            SomeArgs someargs1 = (SomeArgs)message.obj;
            PrintDocumentAdapter printdocumentadapter = (PrintDocumentAdapter)someargs1.arg1;
            PageRange apagerange[] = (PageRange[])someargs1.arg2;
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)someargs1.arg3;
            message = (CancellationSignal)someargs1.arg4;
            PrintDocumentAdapter.WriteResultCallback writeresultcallback = (PrintDocumentAdapter.WriteResultCallback)someargs1.arg5;
            someargs1.recycle();
            printdocumentadapter.onWrite(apagerange, parcelfiledescriptor, message, writeresultcallback);
            continue; /* Loop/switch isn't completed */
_L5:
            ((PrintDocumentAdapter)message.obj).onFinish();
            message = ((Message) (PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this)));
            message;
            JVM INSTR monitorenter ;
            PrintDocumentAdapterDelegate._2D_wrap0(PrintDocumentAdapterDelegate.this);
            message;
            JVM INSTR monitorexit ;
            if(true) goto _L8; else goto _L7
_L7:
            Exception exception;
            exception;
            throw exception;
            throw new RuntimeException((String)message.obj);
        }

        public static final int MSG_ON_FINISH = 4;
        public static final int MSG_ON_KILL = 5;
        public static final int MSG_ON_LAYOUT = 2;
        public static final int MSG_ON_START = 1;
        public static final int MSG_ON_WRITE = 3;
        final PrintDocumentAdapterDelegate this$1;

        public PrintDocumentAdapterDelegate.MyHandler(Looper looper)
        {
            this$1 = PrintDocumentAdapterDelegate.this;
            super(looper, null, true);
        }
    }

    private final class PrintDocumentAdapterDelegate.MyLayoutResultCallback extends PrintDocumentAdapter.LayoutResultCallback
        implements PrintDocumentAdapterDelegate.DestroyableCallback
    {

        public void destroy()
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            mCallback = null;
            PrintDocumentAdapterDelegate._2D_set0(PrintDocumentAdapterDelegate.this, null);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onLayoutCancelled()
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            Object obj2 = mCallback;
            obj;
            JVM INSTR monitorexit ;
            if(obj2 == null)
            {
                Log.e("PrintManager", "PrintDocumentAdapter is destroyed. Did you finish the printing activity before print completion or did you invoke a callback after finish?");
                return;
            }
            break MISSING_BLOCK_LABEL_35;
            obj2;
            throw obj2;
            ((ILayoutResultCallback) (obj2)).onLayoutCanceled(mSequence);
            destroy();
_L2:
            return;
            Object obj1;
            obj1;
            Log.e("PrintManager", "Error calling onLayoutFailed", ((Throwable) (obj1)));
            destroy();
            if(true) goto _L2; else goto _L1
_L1:
            obj1;
            destroy();
            throw obj1;
        }

        public void onLayoutFailed(CharSequence charsequence)
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            ILayoutResultCallback ilayoutresultcallback = mCallback;
            obj;
            JVM INSTR monitorexit ;
            if(ilayoutresultcallback == null)
            {
                Log.e("PrintManager", "PrintDocumentAdapter is destroyed. Did you finish the printing activity before print completion or did you invoke a callback after finish?");
                return;
            }
            break MISSING_BLOCK_LABEL_35;
            charsequence;
            throw charsequence;
            ilayoutresultcallback.onLayoutFailed(charsequence, mSequence);
            destroy();
_L2:
            return;
            charsequence;
            Log.e("PrintManager", "Error calling onLayoutFailed", charsequence);
            destroy();
            if(true) goto _L2; else goto _L1
_L1:
            charsequence;
            destroy();
            throw charsequence;
        }

        public void onLayoutFinished(PrintDocumentInfo printdocumentinfo, boolean flag)
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            ILayoutResultCallback ilayoutresultcallback = mCallback;
            obj;
            JVM INSTR monitorexit ;
            if(ilayoutresultcallback == null)
            {
                Log.e("PrintManager", "PrintDocumentAdapter is destroyed. Did you finish the printing activity before print completion or did you invoke a callback after finish?");
                return;
            }
            break MISSING_BLOCK_LABEL_37;
            printdocumentinfo;
            throw printdocumentinfo;
            if(printdocumentinfo != null)
                break MISSING_BLOCK_LABEL_60;
            printdocumentinfo = JVM INSTR new #74  <Class NullPointerException>;
            printdocumentinfo.NullPointerException("document info cannot be null");
            throw printdocumentinfo;
            printdocumentinfo;
            destroy();
            throw printdocumentinfo;
            ilayoutresultcallback.onLayoutFinished(printdocumentinfo, flag, mSequence);
_L1:
            destroy();
            return;
            printdocumentinfo;
            Log.e("PrintManager", "Error calling onLayoutFinished", printdocumentinfo);
              goto _L1
        }

        private ILayoutResultCallback mCallback;
        private final int mSequence;
        final PrintDocumentAdapterDelegate this$1;

        public PrintDocumentAdapterDelegate.MyLayoutResultCallback(ILayoutResultCallback ilayoutresultcallback, int i)
        {
            this$1 = PrintDocumentAdapterDelegate.this;
            super();
            mCallback = ilayoutresultcallback;
            mSequence = i;
        }
    }

    private final class PrintDocumentAdapterDelegate.MyWriteResultCallback extends PrintDocumentAdapter.WriteResultCallback
        implements PrintDocumentAdapterDelegate.DestroyableCallback
    {

        public void destroy()
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            IoUtils.closeQuietly(mFd);
            mCallback = null;
            mFd = null;
            PrintDocumentAdapterDelegate._2D_set0(PrintDocumentAdapterDelegate.this, null);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onWriteCancelled()
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            Object obj2 = mCallback;
            obj;
            JVM INSTR monitorexit ;
            if(obj2 == null)
            {
                Log.e("PrintManager", "PrintDocumentAdapter is destroyed. Did you finish the printing activity before print completion or did you invoke a callback after finish?");
                return;
            }
            break MISSING_BLOCK_LABEL_35;
            obj2;
            throw obj2;
            ((IWriteResultCallback) (obj2)).onWriteCanceled(mSequence);
            destroy();
_L2:
            return;
            Object obj1;
            obj1;
            Log.e("PrintManager", "Error calling onWriteCanceled", ((Throwable) (obj1)));
            destroy();
            if(true) goto _L2; else goto _L1
_L1:
            obj1;
            destroy();
            throw obj1;
        }

        public void onWriteFailed(CharSequence charsequence)
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            IWriteResultCallback iwriteresultcallback = mCallback;
            obj;
            JVM INSTR monitorexit ;
            if(iwriteresultcallback == null)
            {
                Log.e("PrintManager", "PrintDocumentAdapter is destroyed. Did you finish the printing activity before print completion or did you invoke a callback after finish?");
                return;
            }
            break MISSING_BLOCK_LABEL_35;
            charsequence;
            throw charsequence;
            iwriteresultcallback.onWriteFailed(charsequence, mSequence);
            destroy();
_L2:
            return;
            charsequence;
            Log.e("PrintManager", "Error calling onWriteFailed", charsequence);
            destroy();
            if(true) goto _L2; else goto _L1
_L1:
            charsequence;
            destroy();
            throw charsequence;
        }

        public void onWriteFinished(PageRange apagerange[])
        {
            Object obj = PrintDocumentAdapterDelegate._2D_get0(PrintDocumentAdapterDelegate.this);
            obj;
            JVM INSTR monitorenter ;
            IWriteResultCallback iwriteresultcallback = mCallback;
            obj;
            JVM INSTR monitorexit ;
            if(iwriteresultcallback == null)
            {
                Log.e("PrintManager", "PrintDocumentAdapter is destroyed. Did you finish the printing activity before print completion or did you invoke a callback after finish?");
                return;
            }
            break MISSING_BLOCK_LABEL_35;
            apagerange;
            throw apagerange;
            if(apagerange != null)
                break MISSING_BLOCK_LABEL_58;
            apagerange = JVM INSTR new #86  <Class IllegalArgumentException>;
            apagerange.IllegalArgumentException("pages cannot be null");
            throw apagerange;
            apagerange;
            destroy();
            throw apagerange;
            if(apagerange.length == 0)
            {
                apagerange = JVM INSTR new #86  <Class IllegalArgumentException>;
                apagerange.IllegalArgumentException("pages cannot be empty");
                throw apagerange;
            }
            iwriteresultcallback.onWriteFinished(apagerange, mSequence);
_L1:
            destroy();
            return;
            apagerange;
            Log.e("PrintManager", "Error calling onWriteFinished", apagerange);
              goto _L1
        }

        private IWriteResultCallback mCallback;
        private ParcelFileDescriptor mFd;
        private final int mSequence;
        final PrintDocumentAdapterDelegate this$1;

        public PrintDocumentAdapterDelegate.MyWriteResultCallback(IWriteResultCallback iwriteresultcallback, ParcelFileDescriptor parcelfiledescriptor, int i)
        {
            this$1 = PrintDocumentAdapterDelegate.this;
            super();
            mFd = parcelfiledescriptor;
            mSequence = i;
            mCallback = iwriteresultcallback;
        }
    }

    public static interface PrintJobStateChangeListener
    {

        public abstract void onPrintJobStateChanged(PrintJobId printjobid);
    }

    public static final class PrintJobStateChangeListenerWrapper extends IPrintJobStateChangeListener.Stub
    {

        public void destroy()
        {
            mWeakListener.clear();
        }

        public PrintJobStateChangeListener getListener()
        {
            return (PrintJobStateChangeListener)mWeakListener.get();
        }

        public void onPrintJobStateChanged(PrintJobId printjobid)
        {
            Handler handler = (Handler)mWeakHandler.get();
            PrintJobStateChangeListener printjobstatechangelistener = (PrintJobStateChangeListener)mWeakListener.get();
            if(handler != null && printjobstatechangelistener != null)
            {
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = this;
                someargs.arg2 = printjobid;
                handler.obtainMessage(1, someargs).sendToTarget();
            }
        }

        private final WeakReference mWeakHandler;
        private final WeakReference mWeakListener;

        public PrintJobStateChangeListenerWrapper(PrintJobStateChangeListener printjobstatechangelistener, Handler handler)
        {
            mWeakListener = new WeakReference(printjobstatechangelistener);
            mWeakHandler = new WeakReference(handler);
        }
    }

    public static interface PrintServiceRecommendationsChangeListener
    {

        public abstract void onPrintServiceRecommendationsChanged();
    }

    public static final class PrintServiceRecommendationsChangeListenerWrapper extends android.printservice.recommendation.IRecommendationsChangeListener.Stub
    {

        static void _2D_android_print_PrintManager$PrintServiceRecommendationsChangeListenerWrapper_2D_mthref_2D_0(PrintServiceRecommendationsChangeListener printservicerecommendationschangelistener)
        {
            printservicerecommendationschangelistener.onPrintServiceRecommendationsChanged();
        }

        public void destroy()
        {
            mWeakListener.clear();
        }

        public void onRecommendationsChanged()
        {
            Handler handler = (Handler)mWeakHandler.get();
            PrintServiceRecommendationsChangeListener printservicerecommendationschangelistener = (PrintServiceRecommendationsChangeListener)mWeakListener.get();
            if(handler != null && printservicerecommendationschangelistener != null)
            {
                printservicerecommendationschangelistener.getClass();
                handler.post(new _.Lambda.h7xjKnKsfVuRdZMcjh_0GBiXV30((byte)0, printservicerecommendationschangelistener));
            }
        }

        private final WeakReference mWeakHandler;
        private final WeakReference mWeakListener;

        public PrintServiceRecommendationsChangeListenerWrapper(PrintServiceRecommendationsChangeListener printservicerecommendationschangelistener, Handler handler)
        {
            mWeakListener = new WeakReference(printservicerecommendationschangelistener);
            mWeakHandler = new WeakReference(handler);
        }
    }

    public static interface PrintServicesChangeListener
    {

        public abstract void onPrintServicesChanged();
    }

    public static final class PrintServicesChangeListenerWrapper extends IPrintServicesChangeListener.Stub
    {

        static void _2D_android_print_PrintManager$PrintServicesChangeListenerWrapper_2D_mthref_2D_0(PrintServicesChangeListener printserviceschangelistener)
        {
            printserviceschangelistener.onPrintServicesChanged();
        }

        public void destroy()
        {
            mWeakListener.clear();
        }

        public void onPrintServicesChanged()
        {
            Handler handler = (Handler)mWeakHandler.get();
            PrintServicesChangeListener printserviceschangelistener = (PrintServicesChangeListener)mWeakListener.get();
            if(handler != null && printserviceschangelistener != null)
            {
                printserviceschangelistener.getClass();
                handler.post(new _.Lambda.h7xjKnKsfVuRdZMcjh_0GBiXV30((byte)1, printserviceschangelistener));
            }
        }

        private final WeakReference mWeakHandler;
        private final WeakReference mWeakListener;

        public PrintServicesChangeListenerWrapper(PrintServicesChangeListener printserviceschangelistener, Handler handler)
        {
            mWeakListener = new WeakReference(printserviceschangelistener);
            mWeakHandler = new WeakReference(handler);
        }
    }


    public PrintManager(Context context, IPrintManager iprintmanager, int i, int j)
    {
        mContext = context;
        mService = iprintmanager;
        mUserId = i;
        mAppId = j;
        mHandler = new Handler(context.getMainLooper(), null, false) {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 1: default 24
            //                           1 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                message = (SomeArgs)message.obj;
                PrintJobStateChangeListener printjobstatechangelistener = ((PrintJobStateChangeListenerWrapper)((SomeArgs) (message)).arg1).getListener();
                if(printjobstatechangelistener != null)
                    printjobstatechangelistener.onPrintJobStateChanged((PrintJobId)((SomeArgs) (message)).arg2);
                message.recycle();
                if(true) goto _L1; else goto _L3
_L3:
            }

            final PrintManager this$0;

            
            {
                this$0 = PrintManager.this;
                super(looper, callback, flag);
            }
        }
;
    }

    public void addPrintJobStateChangeListener(PrintJobStateChangeListener printjobstatechangelistener)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        if(mPrintJobStateChangeListeners == null)
            mPrintJobStateChangeListeners = new ArrayMap();
        PrintJobStateChangeListenerWrapper printjobstatechangelistenerwrapper = new PrintJobStateChangeListenerWrapper(printjobstatechangelistener, mHandler);
        try
        {
            mService.addPrintJobStateChangeListener(printjobstatechangelistenerwrapper, mAppId, mUserId);
            mPrintJobStateChangeListeners.put(printjobstatechangelistener, printjobstatechangelistenerwrapper);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PrintJobStateChangeListener printjobstatechangelistener)
        {
            throw printjobstatechangelistener.rethrowFromSystemServer();
        }
    }

    public void addPrintServiceRecommendationsChangeListener(PrintServiceRecommendationsChangeListener printservicerecommendationschangelistener, Handler handler)
    {
        Preconditions.checkNotNull(printservicerecommendationschangelistener);
        Handler handler1 = handler;
        if(handler == null)
            handler1 = mHandler;
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        if(mPrintServiceRecommendationsChangeListeners == null)
            mPrintServiceRecommendationsChangeListeners = new ArrayMap();
        handler = new PrintServiceRecommendationsChangeListenerWrapper(printservicerecommendationschangelistener, handler1);
        try
        {
            mService.addPrintServiceRecommendationsChangeListener(handler, mUserId);
            mPrintServiceRecommendationsChangeListeners.put(printservicerecommendationschangelistener, handler);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PrintServiceRecommendationsChangeListener printservicerecommendationschangelistener)
        {
            throw printservicerecommendationschangelistener.rethrowFromSystemServer();
        }
    }

    public void addPrintServicesChangeListener(PrintServicesChangeListener printserviceschangelistener, Handler handler)
    {
        Preconditions.checkNotNull(printserviceschangelistener);
        Handler handler1 = handler;
        if(handler == null)
            handler1 = mHandler;
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        if(mPrintServicesChangeListeners == null)
            mPrintServicesChangeListeners = new ArrayMap();
        handler = new PrintServicesChangeListenerWrapper(printserviceschangelistener, handler1);
        try
        {
            mService.addPrintServicesChangeListener(handler, mUserId);
            mPrintServicesChangeListeners.put(printserviceschangelistener, handler);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PrintServicesChangeListener printserviceschangelistener)
        {
            throw printserviceschangelistener.rethrowFromSystemServer();
        }
    }

    void cancelPrintJob(PrintJobId printjobid)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        try
        {
            mService.cancelPrintJob(printjobid, mAppId, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PrintJobId printjobid)
        {
            throw printjobid.rethrowFromSystemServer();
        }
    }

    public PrinterDiscoverySession createPrinterDiscoverySession()
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return null;
        } else
        {
            return new PrinterDiscoverySession(mService, mContext, mUserId);
        }
    }

    public Icon getCustomPrinterIcon(PrinterId printerid)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return null;
        }
        try
        {
            printerid = mService.getCustomPrinterIcon(printerid, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(PrinterId printerid)
        {
            throw printerid.rethrowFromSystemServer();
        }
        return printerid;
    }

    public PrintManager getGlobalPrintManagerForUser(int i)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return null;
        } else
        {
            return new PrintManager(mContext, mService, i, -2);
        }
    }

    public PrintJob getPrintJob(PrintJobId printjobid)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return null;
        }
        try
        {
            printjobid = mService.getPrintJobInfo(printjobid, mAppId, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(PrintJobId printjobid)
        {
            throw printjobid.rethrowFromSystemServer();
        }
        if(printjobid == null)
            break MISSING_BLOCK_LABEL_58;
        printjobid = new PrintJob(printjobid, this);
        return printjobid;
        return null;
    }

    PrintJobInfo getPrintJobInfo(PrintJobId printjobid)
    {
        try
        {
            printjobid = mService.getPrintJobInfo(printjobid, mAppId, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(PrintJobId printjobid)
        {
            throw printjobid.rethrowFromSystemServer();
        }
        return printjobid;
    }

    public List getPrintJobs()
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return Collections.emptyList();
        }
        List list;
        int i;
        ArrayList arraylist;
        int j;
        PrintJob printjob;
        try
        {
            list = mService.getPrintJobInfos(mAppId, mUserId);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(list != null)
            break MISSING_BLOCK_LABEL_45;
        return Collections.emptyList();
        i = list.size();
        arraylist = JVM INSTR new #222 <Class ArrayList>;
        arraylist.ArrayList(i);
        j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        printjob = JVM INSTR new #199 <Class PrintJob>;
        printjob.PrintJob((PrintJobInfo)list.get(j), this);
        arraylist.add(printjob);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return arraylist;
    }

    public List getPrintServiceRecommendations()
    {
        List list;
        try
        {
            list = mService.getPrintServiceRecommendations(mUserId);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(list != null)
            return list;
        else
            return Collections.emptyList();
    }

    public List getPrintServices(int i)
    {
        Preconditions.checkFlagsArgument(i, 3);
        List list;
        try
        {
            list = mService.getPrintServices(i, mUserId);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(list != null)
            return list;
        else
            return Collections.emptyList();
    }

    public PrintJob print(String s, PrintDocumentAdapter printdocumentadapter, PrintAttributes printattributes)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return null;
        }
        if(!(mContext instanceof Activity))
            throw new IllegalStateException("Can print only from an activity");
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("printJobName cannot be empty");
        if(printdocumentadapter == null)
            throw new IllegalArgumentException("documentAdapter cannot be null");
        printdocumentadapter = new PrintDocumentAdapterDelegate((Activity)mContext, printdocumentadapter);
        try
        {
            printdocumentadapter = mService.print(s, printdocumentadapter, printattributes, mContext.getPackageName(), mAppId, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(printdocumentadapter == null)
            break MISSING_BLOCK_LABEL_184;
        s = (PrintJobInfo)printdocumentadapter.getParcelable("android.print.intent.extra.EXTRA_PRINT_JOB");
        printdocumentadapter = (IntentSender)printdocumentadapter.getParcelable("android.print.intent.extra.EXTRA_PRINT_DIALOG_INTENT");
        if(s == null || printdocumentadapter == null)
            return null;
        mContext.startIntentSender(printdocumentadapter, null, 0, 0, 0);
        s = new PrintJob(s, this);
        return s;
        s;
        Log.e("PrintManager", "Couldn't start print job config activity.", s);
        return null;
    }

    public void removePrintJobStateChangeListener(PrintJobStateChangeListener printjobstatechangelistener)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        if(mPrintJobStateChangeListeners == null)
            return;
        printjobstatechangelistener = (PrintJobStateChangeListenerWrapper)mPrintJobStateChangeListeners.remove(printjobstatechangelistener);
        if(printjobstatechangelistener == null)
            return;
        if(mPrintJobStateChangeListeners.isEmpty())
            mPrintJobStateChangeListeners = null;
        printjobstatechangelistener.destroy();
        try
        {
            mService.removePrintJobStateChangeListener(printjobstatechangelistener, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PrintJobStateChangeListener printjobstatechangelistener)
        {
            throw printjobstatechangelistener.rethrowFromSystemServer();
        }
    }

    public void removePrintServiceRecommendationsChangeListener(PrintServiceRecommendationsChangeListener printservicerecommendationschangelistener)
    {
        Preconditions.checkNotNull(printservicerecommendationschangelistener);
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        if(mPrintServiceRecommendationsChangeListeners == null)
            return;
        printservicerecommendationschangelistener = (PrintServiceRecommendationsChangeListenerWrapper)mPrintServiceRecommendationsChangeListeners.remove(printservicerecommendationschangelistener);
        if(printservicerecommendationschangelistener == null)
            return;
        if(mPrintServiceRecommendationsChangeListeners.isEmpty())
            mPrintServiceRecommendationsChangeListeners = null;
        printservicerecommendationschangelistener.destroy();
        try
        {
            mService.removePrintServiceRecommendationsChangeListener(printservicerecommendationschangelistener, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PrintServiceRecommendationsChangeListener printservicerecommendationschangelistener)
        {
            throw printservicerecommendationschangelistener.rethrowFromSystemServer();
        }
    }

    public void removePrintServicesChangeListener(PrintServicesChangeListener printserviceschangelistener)
    {
        Preconditions.checkNotNull(printserviceschangelistener);
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        if(mPrintServicesChangeListeners == null)
            return;
        printserviceschangelistener = (PrintServicesChangeListenerWrapper)mPrintServicesChangeListeners.remove(printserviceschangelistener);
        if(printserviceschangelistener == null)
            return;
        if(mPrintServicesChangeListeners.isEmpty())
            mPrintServicesChangeListeners = null;
        printserviceschangelistener.destroy();
        mService.removePrintServicesChangeListener(printserviceschangelistener, mUserId);
_L1:
        return;
        printserviceschangelistener;
        Log.e("PrintManager", "Error removing print services change listener", printserviceschangelistener);
          goto _L1
    }

    void restartPrintJob(PrintJobId printjobid)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        try
        {
            mService.restartPrintJob(printjobid, mAppId, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PrintJobId printjobid)
        {
            throw printjobid.rethrowFromSystemServer();
        }
    }

    public void setPrintServiceEnabled(ComponentName componentname, boolean flag)
    {
        if(mService == null)
        {
            Log.w("PrintManager", "Feature android.software.print not available");
            return;
        }
        mService.setPrintServiceEnabled(componentname, flag, mUserId);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("PrintManager", (new StringBuilder()).append("Error enabling or disabling ").append(componentname).toString(), remoteexception);
          goto _L1
    }

    public static final String ACTION_PRINT_DIALOG = "android.print.PRINT_DIALOG";
    public static final int ALL_SERVICES = 3;
    public static final int APP_ID_ANY = -2;
    private static final boolean DEBUG = false;
    public static final int DISABLED_SERVICES = 2;
    public static final int ENABLED_SERVICES = 1;
    public static final String EXTRA_PRINT_DIALOG_INTENT = "android.print.intent.extra.EXTRA_PRINT_DIALOG_INTENT";
    public static final String EXTRA_PRINT_DOCUMENT_ADAPTER = "android.print.intent.extra.EXTRA_PRINT_DOCUMENT_ADAPTER";
    public static final String EXTRA_PRINT_JOB = "android.print.intent.extra.EXTRA_PRINT_JOB";
    private static final String LOG_TAG = "PrintManager";
    private static final int MSG_NOTIFY_PRINT_JOB_STATE_CHANGED = 1;
    public static final String PRINT_SPOOLER_PACKAGE_NAME = "com.android.printspooler";
    private final int mAppId;
    private final Context mContext;
    private final Handler mHandler;
    private Map mPrintJobStateChangeListeners;
    private Map mPrintServiceRecommendationsChangeListeners;
    private Map mPrintServicesChangeListeners;
    private final IPrintManager mService;
    private final int mUserId;
}
