// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.HashMap;

// Referenced classes of package android.widget:
//            RemoteViews

public abstract class RemoteViewsService extends Service
{
    public static interface RemoteViewsFactory
    {

        public abstract int getCount();

        public abstract long getItemId(int i);

        public abstract RemoteViews getLoadingView();

        public abstract RemoteViews getViewAt(int i);

        public abstract int getViewTypeCount();

        public abstract boolean hasStableIds();

        public abstract void onCreate();

        public abstract void onDataSetChanged();

        public abstract void onDestroy();
    }

    private static class RemoteViewsFactoryAdapter extends com.android.internal.widget.IRemoteViewsFactory.Stub
    {

        public int getCount()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = false;
            int i = mFactory.getCount();
_L1:
            this;
            JVM INSTR monitorexit ;
            return i;
            Object obj;
            obj;
            Thread thread = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, ((Throwable) (obj)));
            i = ((flag) ? 1 : 0);
              goto _L1
            obj;
            throw obj;
        }

        public long getItemId(int i)
        {
            this;
            JVM INSTR monitorenter ;
            long l = 0L;
            long l1 = mFactory.getItemId(i);
            l = l1;
_L2:
            this;
            JVM INSTR monitorexit ;
            return l;
            Exception exception;
            exception;
            Thread thread = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, exception);
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception1;
            exception1;
            throw exception1;
        }

        public RemoteViews getLoadingView()
        {
            this;
            JVM INSTR monitorenter ;
            RemoteViews remoteviews = null;
            RemoteViews remoteviews1 = mFactory.getLoadingView();
            remoteviews = remoteviews1;
_L2:
            this;
            JVM INSTR monitorexit ;
            return remoteviews;
            Exception exception1;
            exception1;
            Thread thread = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, exception1);
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            throw exception;
        }

        public RemoteViews getViewAt(int i)
        {
            this;
            JVM INSTR monitorenter ;
            RemoteViews remoteviews = null;
            RemoteViews remoteviews1 = mFactory.getViewAt(i);
            remoteviews = remoteviews1;
            if(remoteviews1 == null)
                break MISSING_BLOCK_LABEL_30;
            remoteviews = remoteviews1;
            remoteviews1.setIsWidgetCollectionChild(true);
            remoteviews = remoteviews1;
_L2:
            this;
            JVM INSTR monitorexit ;
            return remoteviews;
            Exception exception1;
            exception1;
            Thread thread = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, exception1);
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            throw exception;
        }

        public int getViewTypeCount()
        {
            this;
            JVM INSTR monitorenter ;
            int i = 0;
            int j = mFactory.getViewTypeCount();
            i = j;
_L2:
            this;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            Thread thread = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, exception);
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception1;
            exception1;
            throw exception1;
        }

        public boolean hasStableIds()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = false;
            boolean flag1 = mFactory.hasStableIds();
            flag = flag1;
_L2:
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            Thread thread = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, exception);
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception1;
            exception1;
            throw exception1;
        }

        public boolean isCreated()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mIsCreated;
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void onDataSetChanged()
        {
            this;
            JVM INSTR monitorenter ;
            mFactory.onDataSetChanged();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            Thread thread = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, exception);
              goto _L1
            Exception exception1;
            exception1;
            throw exception1;
        }

        public void onDataSetChangedAsync()
        {
            this;
            JVM INSTR monitorenter ;
            onDataSetChanged();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onDestroy(Intent intent)
        {
            Object obj = RemoteViewsService._2D_get0();
            obj;
            JVM INSTR monitorenter ;
            android.content.Intent.FilterComparison filtercomparison;
            filtercomparison = JVM INSTR new #84  <Class android.content.Intent$FilterComparison>;
            filtercomparison.android.content.Intent.FilterComparison(intent);
            if(!RemoteViewsService._2D_get1().containsKey(filtercomparison)) goto _L2; else goto _L1
_L1:
            intent = (RemoteViewsFactory)RemoteViewsService._2D_get1().get(filtercomparison);
            intent.onDestroy();
_L3:
            RemoteViewsService._2D_get1().remove(filtercomparison);
_L2:
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            intent = Thread.currentThread();
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(intent, exception);
              goto _L3
            intent;
            throw intent;
        }

        private RemoteViewsFactory mFactory;
        private boolean mIsCreated;

        public RemoteViewsFactoryAdapter(RemoteViewsFactory remoteviewsfactory, boolean flag)
        {
            mFactory = remoteviewsfactory;
            mIsCreated = flag;
        }
    }


    static Object _2D_get0()
    {
        return sLock;
    }

    static HashMap _2D_get1()
    {
        return sRemoteViewFactories;
    }

    public RemoteViewsService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        android.content.Intent.FilterComparison filtercomparison;
        filtercomparison = JVM INSTR new #43  <Class android.content.Intent$FilterComparison>;
        filtercomparison.android.content.Intent.FilterComparison(intent);
        if(sRemoteViewFactories.containsKey(filtercomparison)) goto _L2; else goto _L1
_L1:
        intent = onGetViewFactory(intent);
        sRemoteViewFactories.put(filtercomparison, intent);
        intent.onCreate();
        boolean flag = false;
_L4:
        intent = new RemoteViewsFactoryAdapter(intent, flag);
        obj;
        JVM INSTR monitorexit ;
        return intent;
_L2:
        intent = (RemoteViewsFactory)sRemoteViewFactories.get(filtercomparison);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        intent;
        throw intent;
    }

    public abstract RemoteViewsFactory onGetViewFactory(Intent intent);

    private static final String LOG_TAG = "RemoteViewsService";
    private static final Object sLock = new Object();
    private static final HashMap sRemoteViewFactories = new HashMap();

}
