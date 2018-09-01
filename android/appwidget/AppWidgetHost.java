// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.appwidget;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.content.res.Resources;
import android.os.*;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.widget.RemoteViews;
import com.android.internal.appwidget.IAppWidgetService;
import java.lang.ref.WeakReference;
import java.util.List;

// Referenced classes of package android.appwidget:
//            AppWidgetHostView, AppWidgetProviderInfo, PendingHostUpdate

public class AppWidgetHost
{
    static class Callbacks extends com.android.internal.appwidget.IAppWidgetHost.Stub
    {

        private static boolean isLocalBinder()
        {
            boolean flag;
            if(Process.myPid() == Binder.getCallingPid())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void providerChanged(int i, AppWidgetProviderInfo appwidgetproviderinfo)
        {
            AppWidgetProviderInfo appwidgetproviderinfo1 = appwidgetproviderinfo;
            if(isLocalBinder())
            {
                appwidgetproviderinfo1 = appwidgetproviderinfo;
                if(appwidgetproviderinfo != null)
                    appwidgetproviderinfo1 = appwidgetproviderinfo.clone();
            }
            appwidgetproviderinfo = (Handler)mWeakHandler.get();
            if(appwidgetproviderinfo == null)
            {
                return;
            } else
            {
                appwidgetproviderinfo.obtainMessage(2, i, 0, appwidgetproviderinfo1).sendToTarget();
                return;
            }
        }

        public void providersChanged()
        {
            Handler handler = (Handler)mWeakHandler.get();
            if(handler == null)
            {
                return;
            } else
            {
                handler.obtainMessage(3).sendToTarget();
                return;
            }
        }

        public void updateAppWidget(int i, RemoteViews remoteviews)
        {
            RemoteViews remoteviews1 = remoteviews;
            if(isLocalBinder())
            {
                remoteviews1 = remoteviews;
                if(remoteviews != null)
                    remoteviews1 = remoteviews.clone();
            }
            remoteviews = (Handler)mWeakHandler.get();
            if(remoteviews == null)
            {
                return;
            } else
            {
                remoteviews.obtainMessage(1, i, 0, remoteviews1).sendToTarget();
                return;
            }
        }

        public void viewDataChanged(int i, int j)
        {
            Handler handler = (Handler)mWeakHandler.get();
            if(handler == null)
            {
                return;
            } else
            {
                handler.obtainMessage(4, i, j).sendToTarget();
                return;
            }
        }

        private final WeakReference mWeakHandler;

        public Callbacks(Handler handler)
        {
            mWeakHandler = new WeakReference(handler);
        }
    }

    class UpdateHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 37
        //                       2 58
        //                       3 79
        //                       4 89;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L2:
            updateAppWidgetView(message.arg1, (RemoteViews)message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            onProviderChanged(message.arg1, (AppWidgetProviderInfo)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            onProvidersChanged();
            continue; /* Loop/switch isn't completed */
_L5:
            viewDataChanged(message.arg1, message.arg2);
            if(true) goto _L1; else goto _L6
_L6:
        }

        final AppWidgetHost this$0;

        public UpdateHandler(Looper looper)
        {
            this$0 = AppWidgetHost.this;
            super(looper);
        }
    }


    public AppWidgetHost(Context context, int i)
    {
        this(context, i, null, context.getMainLooper());
    }

    public AppWidgetHost(Context context, int i, android.widget.RemoteViews.OnClickHandler onclickhandler, Looper looper)
    {
        mViews = new SparseArray();
        mContextOpPackageName = context.getOpPackageName();
        mHostId = i;
        mOnClickHandler = onclickhandler;
        mHandler = new UpdateHandler(looper);
        mCallbacks = new Callbacks(mHandler);
        mDisplayMetrics = context.getResources().getDisplayMetrics();
        bindService(context);
    }

    private static void bindService(Context context)
    {
        Object obj = sServiceLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = sServiceInitialized;
        if(!flag)
            break MISSING_BLOCK_LABEL_17;
        obj;
        JVM INSTR monitorexit ;
        return;
        sServiceInitialized = true;
        flag = context.getPackageManager().hasSystemFeature("android.software.app_widgets");
        if(flag)
            break MISSING_BLOCK_LABEL_38;
        obj;
        JVM INSTR monitorexit ;
        return;
        sService = com.android.internal.appwidget.IAppWidgetService.Stub.asInterface(ServiceManager.getService("appwidget"));
        obj;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }

    public static void deleteAllHosts()
    {
        if(sService == null)
            return;
        try
        {
            sService.deleteAllHosts();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("system server dead?", remoteexception);
        }
    }

    public int allocateAppWidgetId()
    {
        if(sService == null)
            return -1;
        int i;
        try
        {
            i = sService.allocateAppWidgetId(mContextOpPackageName, mHostId);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("system server dead?", remoteexception);
        }
        return i;
    }

    protected void clearViews()
    {
        SparseArray sparsearray = mViews;
        sparsearray;
        JVM INSTR monitorenter ;
        mViews.clear();
        sparsearray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final AppWidgetHostView createView(Context context, int i, AppWidgetProviderInfo appwidgetproviderinfo)
    {
        if(sService == null)
            return null;
        context = onCreateView(context, i, appwidgetproviderinfo);
        context.setOnClickHandler(mOnClickHandler);
        context.setAppWidget(i, appwidgetproviderinfo);
        appwidgetproviderinfo = mViews;
        appwidgetproviderinfo;
        JVM INSTR monitorenter ;
        mViews.put(i, context);
        appwidgetproviderinfo;
        JVM INSTR monitorexit ;
        try
        {
            appwidgetproviderinfo = sService.getAppWidgetViews(mContextOpPackageName, i);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("system server dead?", context);
        }
        context.updateAppWidget(appwidgetproviderinfo);
        return context;
        context;
        throw context;
    }

    public void deleteAppWidgetId(int i)
    {
        if(sService == null)
            return;
        SparseArray sparsearray = mViews;
        sparsearray;
        JVM INSTR monitorenter ;
        mViews.remove(i);
        sService.deleteAppWidgetId(mContextOpPackageName, i);
        sparsearray;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        RuntimeException runtimeexception = JVM INSTR new #137 <Class RuntimeException>;
        runtimeexception.RuntimeException("system server dead?", ((Throwable) (obj)));
        throw runtimeexception;
        obj;
        sparsearray;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public void deleteHost()
    {
        if(sService == null)
            return;
        try
        {
            sService.deleteHost(mContextOpPackageName, mHostId);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("system server dead?", remoteexception);
        }
    }

    public int[] getAppWidgetIds()
    {
        if(sService == null)
            return new int[0];
        int ai[];
        try
        {
            ai = sService.getAppWidgetIdsForHost(mContextOpPackageName, mHostId);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("system server dead?", remoteexception);
        }
        return ai;
    }

    protected AppWidgetHostView onCreateView(Context context, int i, AppWidgetProviderInfo appwidgetproviderinfo)
    {
        return new AppWidgetHostView(context, mOnClickHandler);
    }

    protected void onProviderChanged(int i, AppWidgetProviderInfo appwidgetproviderinfo)
    {
        appwidgetproviderinfo.updateDimensions(mDisplayMetrics);
        SparseArray sparsearray = mViews;
        sparsearray;
        JVM INSTR monitorenter ;
        AppWidgetHostView appwidgethostview = (AppWidgetHostView)mViews.get(i);
        sparsearray;
        JVM INSTR monitorexit ;
        if(appwidgethostview != null)
            appwidgethostview.resetAppWidget(appwidgetproviderinfo);
        return;
        appwidgetproviderinfo;
        throw appwidgetproviderinfo;
    }

    protected void onProvidersChanged()
    {
    }

    public final void startAppWidgetConfigureActivityForResult(Activity activity, int i, int j, int k, Bundle bundle)
    {
        if(sService == null)
            return;
        android.content.IntentSender intentsender;
        try
        {
            intentsender = sService.createAppWidgetConfigIntentSender(mContextOpPackageName, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            throw new ActivityNotFoundException();
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            throw new RuntimeException("system server dead?", activity);
        }
        if(intentsender == null)
            break MISSING_BLOCK_LABEL_43;
        activity.startIntentSenderForResult(intentsender, k, null, 0, 0, 0, bundle);
        return;
        activity = JVM INSTR new #230 <Class ActivityNotFoundException>;
        activity.ActivityNotFoundException();
        throw activity;
    }

    public void startListening()
    {
        if(sService == null)
            return;
        Object obj = mViews;
        obj;
        JVM INSTR monitorenter ;
        int i;
        int ai[];
        i = mViews.size();
        ai = new int[i];
        int k = 0;
_L2:
        if(k >= i)
            break; /* Loop/switch isn't completed */
        ai[k] = mViews.keyAt(k);
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        int j;
        List list;
        Exception exception;
        int l;
        try
        {
            list = sService.startListening(mCallbacks, mContextOpPackageName, mHostId, ai).getList();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException("system server dead?", ((Throwable) (obj)));
        }
        j = list.size();
        l = 0;
        if(l >= j)
            break MISSING_BLOCK_LABEL_208;
        obj = (PendingHostUpdate)list.get(l);
        switch(((PendingHostUpdate) (obj)).type)
        {
        default:
            break;

        case 0: // '\0'
            break; /* Loop/switch isn't completed */

        case 1: // '\001'
            onProviderChanged(((PendingHostUpdate) (obj)).appWidgetId, ((PendingHostUpdate) (obj)).widgetInfo);
            continue; /* Loop/switch isn't completed */

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_193;
_L6:
        l++;
        if(true) goto _L4; else goto _L3
_L4:
        break MISSING_BLOCK_LABEL_91;
        exception;
        throw exception;
_L3:
        updateAppWidgetView(((PendingHostUpdate) (obj)).appWidgetId, ((PendingHostUpdate) (obj)).views);
        continue; /* Loop/switch isn't completed */
        viewDataChanged(((PendingHostUpdate) (obj)).appWidgetId, ((PendingHostUpdate) (obj)).viewId);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void stopListening()
    {
        if(sService == null)
            return;
        try
        {
            sService.stopListening(mContextOpPackageName, mHostId);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("system server dead?", remoteexception);
        }
    }

    void updateAppWidgetView(int i, RemoteViews remoteviews)
    {
        SparseArray sparsearray = mViews;
        sparsearray;
        JVM INSTR monitorenter ;
        AppWidgetHostView appwidgethostview = (AppWidgetHostView)mViews.get(i);
        sparsearray;
        JVM INSTR monitorexit ;
        if(appwidgethostview != null)
            appwidgethostview.updateAppWidget(remoteviews);
        return;
        remoteviews;
        throw remoteviews;
    }

    void viewDataChanged(int i, int j)
    {
        SparseArray sparsearray = mViews;
        sparsearray;
        JVM INSTR monitorenter ;
        AppWidgetHostView appwidgethostview = (AppWidgetHostView)mViews.get(i);
        sparsearray;
        JVM INSTR monitorexit ;
        if(appwidgethostview != null)
            appwidgethostview.viewDataChanged(j);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static final int HANDLE_PROVIDERS_CHANGED = 3;
    static final int HANDLE_PROVIDER_CHANGED = 2;
    static final int HANDLE_UPDATE = 1;
    static final int HANDLE_VIEW_DATA_CHANGED = 4;
    static IAppWidgetService sService;
    static boolean sServiceInitialized = false;
    static final Object sServiceLock = new Object();
    private final Callbacks mCallbacks;
    private String mContextOpPackageName;
    private DisplayMetrics mDisplayMetrics;
    private final Handler mHandler;
    private final int mHostId;
    private android.widget.RemoteViews.OnClickHandler mOnClickHandler;
    private final SparseArray mViews;

}
