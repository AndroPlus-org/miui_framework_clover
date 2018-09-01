// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.util.*;
import android.view.*;
import com.android.internal.widget.IRemoteViewsFactory;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.Executor;

// Referenced classes of package android.widget:
//            BaseAdapter, RemoteViews, TextView

public class RemoteViewsAdapter extends BaseAdapter
    implements android.os.Handler.Callback
{
    public static class AsyncRemoteAdapterAction
        implements Runnable
    {

        public void run()
        {
            mCallback.setRemoteViewsAdapter(mIntent, true);
        }

        private final RemoteAdapterConnectionCallback mCallback;
        private final Intent mIntent;

        public AsyncRemoteAdapterAction(RemoteAdapterConnectionCallback remoteadapterconnectioncallback, Intent intent)
        {
            mCallback = remoteadapterconnectioncallback;
            mIntent = intent;
        }
    }

    private static class FixedSizeRemoteViewsCache
    {

        static SparseArray _2D_get0(FixedSizeRemoteViewsCache fixedsizeremoteviewscache)
        {
            return fixedsizeremoteviewscache.mIndexRemoteViews;
        }

        static RemoteViewsMetaData _2D_get1(FixedSizeRemoteViewsCache fixedsizeremoteviewscache)
        {
            return fixedsizeremoteviewscache.mMetaData;
        }

        private int getFarthestPositionFrom(int i, int ai[])
        {
            int j = 0;
            int k = -1;
            int l = 0;
            int i1 = -1;
            for(int j1 = mIndexRemoteViews.size() - 1; j1 >= 0;)
            {
                int k1 = mIndexRemoteViews.keyAt(j1);
                int l1 = Math.abs(k1 - i);
                int i2 = i1;
                int j2 = l;
                if(l1 > l)
                {
                    i2 = i1;
                    j2 = l;
                    if(Arrays.binarySearch(ai, k1) < 0)
                    {
                        i2 = k1;
                        j2 = l1;
                    }
                }
                l = j;
                if(l1 >= j)
                {
                    k = k1;
                    l = l1;
                }
                j1--;
                j = l;
                i1 = i2;
                l = j2;
            }

            if(i1 > -1)
                return i1;
            else
                return k;
        }

        private int getRemoteViewsBitmapMemoryUsage()
        {
            int i = 0;
            for(int j = mIndexRemoteViews.size() - 1; j >= 0;)
            {
                RemoteViews remoteviews = (RemoteViews)mIndexRemoteViews.valueAt(j);
                int k = i;
                if(remoteviews != null)
                    k = i + remoteviews.estimateMemoryUsage();
                j--;
                i = k;
            }

            return i;
        }

        public void commitTemporaryMetaData()
        {
            RemoteViewsMetaData remoteviewsmetadata = mTemporaryMetaData;
            remoteviewsmetadata;
            JVM INSTR monitorenter ;
            RemoteViewsMetaData remoteviewsmetadata1 = mMetaData;
            remoteviewsmetadata1;
            JVM INSTR monitorenter ;
            mMetaData.set(mTemporaryMetaData);
            remoteviewsmetadata1;
            JVM INSTR monitorexit ;
            remoteviewsmetadata;
            JVM INSTR monitorexit ;
            return;
            Exception exception1;
            exception1;
            remoteviewsmetadata1;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            remoteviewsmetadata;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public boolean containsMetaDataAt(int i)
        {
            boolean flag = false;
            if(mIndexMetaData.indexOfKey(i) >= 0)
                flag = true;
            return flag;
        }

        public boolean containsRemoteViewAt(int i)
        {
            boolean flag = false;
            if(mIndexRemoteViews.indexOfKey(i) >= 0)
                flag = true;
            return flag;
        }

        public RemoteViewsMetaData getMetaData()
        {
            return mMetaData;
        }

        public RemoteViewsIndexMetaData getMetaDataAt(int i)
        {
            return (RemoteViewsIndexMetaData)mIndexMetaData.get(i);
        }

        public int getNextIndexToLoad()
        {
            SparseBooleanArray sparsebooleanarray = mIndicesToLoad;
            sparsebooleanarray;
            JVM INSTR monitorenter ;
            int i = mIndicesToLoad.indexOfValue(true);
            int j;
            j = i;
            if(i >= 0)
                break MISSING_BLOCK_LABEL_31;
            j = mIndicesToLoad.indexOfValue(false);
            if(j >= 0)
                break MISSING_BLOCK_LABEL_39;
            sparsebooleanarray;
            JVM INSTR monitorexit ;
            return -1;
            i = mIndicesToLoad.keyAt(j);
            mIndicesToLoad.removeAt(j);
            sparsebooleanarray;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            throw exception;
        }

        public RemoteViews getRemoteViewsAt(int i)
        {
            return (RemoteViews)mIndexRemoteViews.get(i);
        }

        public RemoteViewsMetaData getTemporaryMetaData()
        {
            return mTemporaryMetaData;
        }

        public void insert(int i, RemoteViews remoteviews, long l, int ai[])
        {
            if(mIndexRemoteViews.size() >= mMaxCount)
                mIndexRemoteViews.remove(getFarthestPositionFrom(i, ai));
            int j;
            if(mLastRequestedIndex > -1)
                j = mLastRequestedIndex;
            else
                j = i;
            do
            {
label0:
                {
                    int k;
                    if(getRemoteViewsBitmapMemoryUsage() >= 0x200000)
                    {
                        k = getFarthestPositionFrom(j, ai);
                        if(k >= 0)
                            break label0;
                    }
                    ai = (RemoteViewsIndexMetaData)mIndexMetaData.get(i);
                    if(ai != null)
                        ai.set(remoteviews, l);
                    else
                        mIndexMetaData.put(i, new RemoteViewsIndexMetaData(remoteviews, l));
                    mIndexRemoteViews.put(i, remoteviews);
                    return;
                }
                mIndexRemoteViews.remove(k);
            } while(true);
        }

        public boolean queuePositionsToBePreloadedFromRequestedPosition(int i)
        {
            if(mPreloadLowerBound <= i && i <= mPreloadUpperBound && Math.abs(i - (mPreloadUpperBound + mPreloadLowerBound) / 2) < mMaxCountSlack)
                return false;
            Object obj = mMetaData;
            obj;
            JVM INSTR monitorenter ;
            int j = mMetaData.count;
            obj;
            JVM INSTR monitorexit ;
            obj = mIndicesToLoad;
            obj;
            JVM INSTR monitorenter ;
            int k = mIndicesToLoad.size() - 1;
_L2:
            if(k < 0)
                break; /* Loop/switch isn't completed */
            if(!mIndicesToLoad.valueAt(k))
                mIndicesToLoad.removeAt(k);
            k--;
            if(true) goto _L2; else goto _L1
            Exception exception;
            exception;
            throw exception;
_L1:
            k = mMaxCount / 2;
            mPreloadLowerBound = i - k;
            mPreloadUpperBound = i + k;
            i = Math.max(0, mPreloadLowerBound);
            k = Math.min(mPreloadUpperBound, j - 1);
_L5:
            if(i > k) goto _L4; else goto _L3
_L3:
            if(mIndexRemoteViews.indexOfKey(i) < 0 && mIndicesToLoad.get(i) ^ true)
                mIndicesToLoad.put(i, false);
            i++;
              goto _L5
_L4:
            obj;
            JVM INSTR monitorexit ;
            return true;
            exception;
            throw exception;
        }

        public void queueRequestedPositionToLoad(int i)
        {
            mLastRequestedIndex = i;
            SparseBooleanArray sparsebooleanarray = mIndicesToLoad;
            sparsebooleanarray;
            JVM INSTR monitorenter ;
            mIndicesToLoad.put(i, true);
            sparsebooleanarray;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void reset()
        {
            mPreloadLowerBound = 0;
            mPreloadUpperBound = -1;
            mLastRequestedIndex = -1;
            mIndexRemoteViews.clear();
            mIndexMetaData.clear();
            SparseBooleanArray sparsebooleanarray = mIndicesToLoad;
            sparsebooleanarray;
            JVM INSTR monitorenter ;
            mIndicesToLoad.clear();
            sparsebooleanarray;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static final String TAG = "FixedSizeRemoteViewsCache";
        private static final float sMaxCountSlackPercent = 0.75F;
        private static final int sMaxMemoryLimitInBytes = 0x200000;
        private final SparseArray mIndexMetaData = new SparseArray();
        private final SparseArray mIndexRemoteViews = new SparseArray();
        private final SparseBooleanArray mIndicesToLoad = new SparseBooleanArray();
        private int mLastRequestedIndex;
        private final int mMaxCount;
        private final int mMaxCountSlack;
        private final RemoteViewsMetaData mMetaData = new RemoteViewsMetaData();
        private int mPreloadLowerBound;
        private int mPreloadUpperBound;
        private final RemoteViewsMetaData mTemporaryMetaData = new RemoteViewsMetaData();

        public FixedSizeRemoteViewsCache(int i)
        {
            mMaxCount = i;
            mMaxCountSlack = Math.round((float)(mMaxCount / 2) * 0.75F);
            mPreloadLowerBound = 0;
            mPreloadUpperBound = -1;
            mLastRequestedIndex = -1;
        }
    }

    private static class HandlerThreadExecutor
        implements Executor
    {

        public void execute(Runnable runnable)
        {
            if(Thread.currentThread().getId() == mThread.getId())
                runnable.run();
            else
                (new Handler(mThread.getLooper())).post(runnable);
        }

        private final HandlerThread mThread;

        HandlerThreadExecutor(HandlerThread handlerthread)
        {
            mThread = handlerthread;
        }
    }

    private static class LoadingViewTemplate
    {

        public void loadFirstViewHeight(RemoteViews remoteviews, Context context, Executor executor)
        {
            remoteviews.applyAsync(context, new RemoteViewsFrameLayout(context, null), executor, new RemoteViews.OnViewAppliedListener() {

                public void onError(Exception exception)
                {
                    Log.w("RemoteViewsAdapter", "Error inflating first RemoteViews", exception);
                }

                public void onViewApplied(View view)
                {
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(0, 0), android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
                    defaultHeight = view.getMeasuredHeight();
_L1:
                    return;
                    view;
                    onError(view);
                      goto _L1
                }

                final LoadingViewTemplate this$1;

            
            {
                this$1 = LoadingViewTemplate.this;
                super();
            }
            }
);
        }

        public int defaultHeight;
        public final RemoteViews remoteViews;

        LoadingViewTemplate(RemoteViews remoteviews, Context context)
        {
            remoteViews = remoteviews;
            defaultHeight = Math.round(50F * context.getResources().getDisplayMetrics().density);
        }
    }

    public static interface RemoteAdapterConnectionCallback
    {

        public abstract void deferNotifyDataSetChanged();

        public abstract boolean onRemoteAdapterConnected();

        public abstract void onRemoteAdapterDisconnected();

        public abstract void setRemoteViewsAdapter(Intent intent, boolean flag);
    }

    private static class RemoteViewsAdapterServiceConnection extends com.android.internal.widget.IRemoteViewsAdapterConnection.Stub
    {

        static boolean _2D_set0(RemoteViewsAdapterServiceConnection remoteviewsadapterserviceconnection, boolean flag)
        {
            remoteviewsadapterserviceconnection.mIsConnected = flag;
            return flag;
        }

        static boolean _2D_set1(RemoteViewsAdapterServiceConnection remoteviewsadapterserviceconnection, boolean flag)
        {
            remoteviewsadapterserviceconnection.mIsConnecting = flag;
            return flag;
        }

        public void bind(Context context, int i, Intent intent)
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mIsConnecting;
            if(flag) goto _L2; else goto _L1
_L1:
            AppWidgetManager appwidgetmanager = AppWidgetManager.getInstance(context);
            if((RemoteViewsAdapter)mAdapter.get() == null) goto _L4; else goto _L3
_L3:
            appwidgetmanager.bindRemoteViewsService(context.getOpPackageName(), i, intent, asBinder());
_L5:
            mIsConnecting = true;
_L2:
            this;
            JVM INSTR monitorexit ;
            return;
_L4:
            Slog.w("RemoteViewsAdapter", "bind: adapter was null");
              goto _L5
            context;
            intent = JVM INSTR new #81  <Class StringBuilder>;
            intent.StringBuilder();
            Log.e("RVAServiceConnection", intent.append("bind(): ").append(context.getMessage()).toString());
            mIsConnecting = false;
            mIsConnected = false;
              goto _L2
            context;
            throw context;
              goto _L5
        }

        public IRemoteViewsFactory getRemoteViewsFactory()
        {
            this;
            JVM INSTR monitorenter ;
            IRemoteViewsFactory iremoteviewsfactory = mRemoteViewsFactory;
            this;
            JVM INSTR monitorexit ;
            return iremoteviewsfactory;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isConnected()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mIsConnected;
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void onServiceConnected(IBinder ibinder)
        {
            this;
            JVM INSTR monitorenter ;
            RemoteViewsAdapter remoteviewsadapter;
            mRemoteViewsFactory = com.android.internal.widget.IRemoteViewsFactory.Stub.asInterface(ibinder);
            remoteviewsadapter = (RemoteViewsAdapter)mAdapter.get();
            if(remoteviewsadapter != null)
                break MISSING_BLOCK_LABEL_28;
            this;
            JVM INSTR monitorexit ;
            return;
            ibinder = RemoteViewsAdapter._2D_get7(remoteviewsadapter);
            Runnable runnable = JVM INSTR new #9   <Class RemoteViewsAdapter$RemoteViewsAdapterServiceConnection$1>;
            remoteviewsadapter. super();
            ibinder.post(runnable);
            this;
            JVM INSTR monitorexit ;
            return;
            ibinder;
            throw ibinder;
        }

        public void onServiceDisconnected()
        {
            this;
            JVM INSTR monitorenter ;
            RemoteViewsAdapter remoteviewsadapter;
            mIsConnected = false;
            mIsConnecting = false;
            mRemoteViewsFactory = null;
            remoteviewsadapter = (RemoteViewsAdapter)mAdapter.get();
            if(remoteviewsadapter != null)
                break MISSING_BLOCK_LABEL_35;
            this;
            JVM INSTR monitorexit ;
            return;
            Handler handler = RemoteViewsAdapter._2D_get2(remoteviewsadapter);
            Runnable runnable = JVM INSTR new #13  <Class RemoteViewsAdapter$RemoteViewsAdapterServiceConnection$2>;
            remoteviewsadapter. super();
            handler.post(runnable);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void unbind(Context context, int i, Intent intent)
        {
            this;
            JVM INSTR monitorenter ;
            AppWidgetManager appwidgetmanager = AppWidgetManager.getInstance(context);
            if((RemoteViewsAdapter)mAdapter.get() == null) goto _L2; else goto _L1
_L1:
            appwidgetmanager.unbindRemoteViewsService(context.getOpPackageName(), i, intent);
_L3:
            mIsConnecting = false;
_L4:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            Slog.w("RemoteViewsAdapter", "unbind: adapter was null");
              goto _L3
            intent;
            context = JVM INSTR new #81  <Class StringBuilder>;
            context.StringBuilder();
            Log.e("RVAServiceConnection", context.append("unbind(): ").append(intent.getMessage()).toString());
            mIsConnecting = false;
            mIsConnected = false;
              goto _L4
            context;
            throw context;
              goto _L3
        }

        private WeakReference mAdapter;
        private boolean mIsConnected;
        private boolean mIsConnecting;
        private IRemoteViewsFactory mRemoteViewsFactory;

        public RemoteViewsAdapterServiceConnection(RemoteViewsAdapter remoteviewsadapter)
        {
            mAdapter = new WeakReference(remoteviewsadapter);
        }
    }

    static class RemoteViewsCacheKey
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof RemoteViewsCacheKey))
                return false;
            obj = (RemoteViewsCacheKey)obj;
            boolean flag1 = flag;
            if(((RemoteViewsCacheKey) (obj)).filter.equals(filter))
            {
                flag1 = flag;
                if(((RemoteViewsCacheKey) (obj)).widgetId == widgetId)
                    flag1 = true;
            }
            return flag1;
        }

        public int hashCode()
        {
            int i;
            if(filter == null)
                i = 0;
            else
                i = filter.hashCode();
            return i ^ widgetId << 2;
        }

        final android.content.Intent.FilterComparison filter;
        final int widgetId;

        RemoteViewsCacheKey(android.content.Intent.FilterComparison filtercomparison, int i)
        {
            filter = filtercomparison;
            widgetId = i;
        }
    }

    static class RemoteViewsFrameLayout extends AppWidgetHostView
    {

        protected View getDefaultView()
        {
            int i = mCache.getMetaData().getLoadingTemplate(getContext()).defaultHeight;
            TextView textview = (TextView)LayoutInflater.from(getContext()).inflate(0x10900d7, this, false);
            textview.setHeight(i);
            return textview;
        }

        protected View getErrorView()
        {
            return getDefaultView();
        }

        protected Context getRemoteContext()
        {
            return null;
        }

        public void onRemoteViewsLoaded(RemoteViews remoteviews, RemoteViews.OnClickHandler onclickhandler, boolean flag)
        {
            setOnClickHandler(onclickhandler);
            if(!flag)
            {
                if(remoteviews != null)
                    flag = remoteviews.prefersAsyncApply();
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            applyRemoteViews(remoteviews, flag);
        }

        private final FixedSizeRemoteViewsCache mCache;

        public RemoteViewsFrameLayout(Context context, FixedSizeRemoteViewsCache fixedsizeremoteviewscache)
        {
            super(context);
            mCache = fixedsizeremoteviewscache;
        }
    }

    private class RemoteViewsFrameLayoutRefSet
    {

        public void add(int i, RemoteViewsFrameLayout remoteviewsframelayout)
        {
            LinkedList linkedlist = (LinkedList)mReferences.get(i);
            LinkedList linkedlist1 = linkedlist;
            if(linkedlist == null)
            {
                linkedlist1 = new LinkedList();
                mReferences.put(i, linkedlist1);
            }
            mViewToLinkedList.put(remoteviewsframelayout, linkedlist1);
            linkedlist1.add(remoteviewsframelayout);
        }

        public void clear()
        {
            mReferences.clear();
            mViewToLinkedList.clear();
        }

        public void notifyOnRemoteViewsLoaded(int i, RemoteViews remoteviews)
        {
            if(remoteviews == null)
                return;
            LinkedList linkedlist = (LinkedList)mReferences.get(i);
            if(linkedlist != null)
            {
                Iterator iterator = linkedlist.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    RemoteViewsFrameLayout remoteviewsframelayout = (RemoteViewsFrameLayout)iterator.next();
                    remoteviewsframelayout.onRemoteViewsLoaded(remoteviews, RemoteViewsAdapter._2D_get4(RemoteViewsAdapter.this), true);
                    if(mViewToLinkedList.containsKey(remoteviewsframelayout))
                        mViewToLinkedList.remove(remoteviewsframelayout);
                } while(true);
                linkedlist.clear();
                mReferences.remove(i);
            }
        }

        public void removeView(RemoteViewsFrameLayout remoteviewsframelayout)
        {
            if(mViewToLinkedList.containsKey(remoteviewsframelayout))
            {
                ((LinkedList)mViewToLinkedList.get(remoteviewsframelayout)).remove(remoteviewsframelayout);
                mViewToLinkedList.remove(remoteviewsframelayout);
            }
        }

        private final SparseArray mReferences;
        private final HashMap mViewToLinkedList;
        final RemoteViewsAdapter this$0;

        private RemoteViewsFrameLayoutRefSet()
        {
            this$0 = RemoteViewsAdapter.this;
            super();
            mReferences = new SparseArray();
            mViewToLinkedList = new HashMap();
        }

        RemoteViewsFrameLayoutRefSet(RemoteViewsFrameLayoutRefSet remoteviewsframelayoutrefset)
        {
            this();
        }
    }

    private static class RemoteViewsIndexMetaData
    {

        public void set(RemoteViews remoteviews, long l)
        {
            itemId = l;
            if(remoteviews != null)
                typeId = remoteviews.getLayoutId();
            else
                typeId = 0;
        }

        long itemId;
        int typeId;

        public RemoteViewsIndexMetaData(RemoteViews remoteviews, long l)
        {
            set(remoteviews, l);
        }
    }

    private static class RemoteViewsMetaData
    {

        public LoadingViewTemplate getLoadingTemplate(Context context)
        {
            this;
            JVM INSTR monitorenter ;
            if(loadingTemplate == null)
            {
                LoadingViewTemplate loadingviewtemplate = JVM INSTR new #35  <Class RemoteViewsAdapter$LoadingViewTemplate>;
                loadingviewtemplate.LoadingViewTemplate(null, context);
                loadingTemplate = loadingviewtemplate;
            }
            context = loadingTemplate;
            this;
            JVM INSTR monitorexit ;
            return context;
            context;
            throw context;
        }

        public int getMappedViewType(int i)
        {
            int j = mTypeIdIndexMap.get(i, -1);
            int k = j;
            if(j == -1)
            {
                k = mTypeIdIndexMap.size() + 1;
                mTypeIdIndexMap.put(i, k);
            }
            return k;
        }

        public boolean isViewTypeInRange(int i)
        {
            boolean flag;
            if(getMappedViewType(i) < viewTypeCount)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void reset()
        {
            count = 0;
            viewTypeCount = 1;
            hasStableIds = true;
            loadingTemplate = null;
            mTypeIdIndexMap.clear();
        }

        public void set(RemoteViewsMetaData remoteviewsmetadata)
        {
            remoteviewsmetadata;
            JVM INSTR monitorenter ;
            count = remoteviewsmetadata.count;
            viewTypeCount = remoteviewsmetadata.viewTypeCount;
            hasStableIds = remoteviewsmetadata.hasStableIds;
            loadingTemplate = remoteviewsmetadata.loadingTemplate;
            remoteviewsmetadata;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        int count;
        boolean hasStableIds;
        LoadingViewTemplate loadingTemplate;
        private final SparseIntArray mTypeIdIndexMap = new SparseIntArray();
        int viewTypeCount;

        public RemoteViewsMetaData()
        {
            reset();
        }
    }


    static FixedSizeRemoteViewsCache _2D_get0(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mCache;
    }

    static WeakReference _2D_get1(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mCallback;
    }

    static Handler _2D_get2(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mMainQueue;
    }

    static boolean _2D_get3(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mNotifyDataSetChangedAfterOnServiceConnected;
    }

    static RemoteViews.OnClickHandler _2D_get4(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mRemoteViewsOnClickHandler;
    }

    static RemoteViewsFrameLayoutRefSet _2D_get5(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mRequestedViews;
    }

    static RemoteViewsAdapterServiceConnection _2D_get6(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mServiceConnection;
    }

    static Handler _2D_get7(RemoteViewsAdapter remoteviewsadapter)
    {
        return remoteviewsadapter.mWorkerQueue;
    }

    static HashMap _2D_get8()
    {
        return sCachedRemoteViewsCaches;
    }

    static HashMap _2D_get9()
    {
        return sRemoteViewsCacheRemoveRunnables;
    }

    static void _2D_wrap0(RemoteViewsAdapter remoteviewsadapter)
    {
        remoteviewsadapter.enqueueDeferredUnbindServiceMessage();
    }

    static void _2D_wrap1(RemoteViewsAdapter remoteviewsadapter)
    {
        remoteviewsadapter.loadNextIndexInBackground();
    }

    static void _2D_wrap2(RemoteViewsAdapter remoteviewsadapter)
    {
        remoteviewsadapter.onNotifyDataSetChanged();
    }

    static void _2D_wrap3(RemoteViewsAdapter remoteviewsadapter, int i, boolean flag)
    {
        remoteviewsadapter.updateRemoteViews(i, flag);
    }

    static void _2D_wrap4(RemoteViewsAdapter remoteviewsadapter)
    {
        remoteviewsadapter.updateTemporaryMetaData();
    }

    public RemoteViewsAdapter(Context context, Intent intent, RemoteAdapterConnectionCallback remoteadapterconnectioncallback, boolean flag)
    {
        Object obj = null;
        super();
        mNotifyDataSetChangedAfterOnServiceConnected = false;
        mDataReady = false;
        mContext = context;
        mIntent = intent;
        if(mIntent == null)
            throw new IllegalArgumentException("Non-null Intent must be specified.");
        mAppWidgetId = intent.getIntExtra("remoteAdapterAppWidgetId", -1);
        mRequestedViews = new RemoteViewsFrameLayoutRefSet(null);
        if(intent.hasExtra("remoteAdapterAppWidgetId"))
            intent.removeExtra("remoteAdapterAppWidgetId");
        mWorkerThread = new HandlerThread("RemoteViewsCache-loader");
        mWorkerThread.start();
        mWorkerQueue = new Handler(mWorkerThread.getLooper());
        mMainQueue = new Handler(Looper.myLooper(), this);
        context = obj;
        if(flag)
            context = new HandlerThreadExecutor(mWorkerThread);
        mAsyncViewLoadExecutor = context;
        if(sCacheRemovalThread == null)
        {
            sCacheRemovalThread = new HandlerThread("RemoteViewsAdapter-cachePruner");
            sCacheRemovalThread.start();
            sCacheRemovalQueue = new Handler(sCacheRemovalThread.getLooper());
        }
        mCallback = new WeakReference(remoteadapterconnectioncallback);
        mServiceConnection = new RemoteViewsAdapterServiceConnection(this);
        intent = new RemoteViewsCacheKey(new android.content.Intent.FilterComparison(mIntent), mAppWidgetId);
        context = sCachedRemoteViewsCaches;
        context;
        JVM INSTR monitorenter ;
        if(!sCachedRemoteViewsCaches.containsKey(intent))
            break MISSING_BLOCK_LABEL_333;
        mCache = (FixedSizeRemoteViewsCache)sCachedRemoteViewsCaches.get(intent);
        remoteadapterconnectioncallback = FixedSizeRemoteViewsCache._2D_get1(mCache);
        remoteadapterconnectioncallback;
        JVM INSTR monitorenter ;
        if(FixedSizeRemoteViewsCache._2D_get1(mCache).count > 0)
            mDataReady = true;
        remoteadapterconnectioncallback;
        JVM INSTR monitorexit ;
_L1:
        if(!mDataReady)
            requestBindService();
        context;
        JVM INSTR monitorexit ;
        return;
        intent;
        remoteadapterconnectioncallback;
        JVM INSTR monitorexit ;
        throw intent;
        intent;
        context;
        JVM INSTR monitorexit ;
        throw intent;
        intent = JVM INSTR new #23  <Class RemoteViewsAdapter$FixedSizeRemoteViewsCache>;
        intent.FixedSizeRemoteViewsCache(40);
        mCache = intent;
          goto _L1
    }

    private void enqueueDeferredUnbindServiceMessage()
    {
        mMainQueue.removeMessages(1);
        mMainQueue.sendEmptyMessageDelayed(1, 5000L);
    }

    private int[] getVisibleWindow(int i, int j, int k)
    {
        while(i == 0 && j == 0 || i < 0 || j < 0) 
            return new int[0];
        int ai2[];
        if(i <= j)
        {
            int ai[] = new int[(j + 1) - i];
            k = i;
            i = 0;
            do
            {
                ai2 = ai;
                if(k > j)
                    break;
                ai[i] = k;
                k++;
                i++;
            } while(true);
        } else
        {
            int l = Math.max(k, i);
            int ai1[] = new int[(l - i) + j + 1];
            k = 0;
            for(int i1 = 0; i1 <= j;)
            {
                ai1[k] = i1;
                i1++;
                k++;
            }

            do
            {
                ai2 = ai1;
                if(i >= l)
                    break;
                ai1[k] = i;
                i++;
                k++;
            } while(true);
        }
        return ai2;
    }

    private void loadNextIndexInBackground()
    {
        mWorkerQueue.post(new Runnable() {

            public void run()
            {
                if(!RemoteViewsAdapter._2D_get6(RemoteViewsAdapter.this).isConnected())
                    break MISSING_BLOCK_LABEL_57;
                FixedSizeRemoteViewsCache fixedsizeremoteviewscache = RemoteViewsAdapter._2D_get0(RemoteViewsAdapter.this);
                fixedsizeremoteviewscache;
                JVM INSTR monitorenter ;
                int i = RemoteViewsAdapter._2D_get0(RemoteViewsAdapter.this).getNextIndexToLoad();
                fixedsizeremoteviewscache;
                JVM INSTR monitorexit ;
                Exception exception;
                if(i > -1)
                {
                    RemoteViewsAdapter._2D_wrap3(RemoteViewsAdapter.this, i, true);
                    RemoteViewsAdapter._2D_wrap1(RemoteViewsAdapter.this);
                } else
                {
                    RemoteViewsAdapter._2D_wrap0(RemoteViewsAdapter.this);
                }
                return;
                exception;
                throw exception;
            }

            final RemoteViewsAdapter this$0;

            
            {
                this$0 = RemoteViewsAdapter.this;
                super();
            }
        }
);
    }

    private void onNotifyDataSetChanged()
    {
        Object obj;
        obj = mServiceConnection.getRemoteViewsFactory();
        int i;
        int ai[];
        int j;
        int k;
        int l;
        try
        {
            ((IRemoteViewsFactory) (obj)).onDataSetChanged();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error in updateNotifyDataSetChanged(): ").append(((RemoteException) (obj)).getMessage()).toString());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error in updateNotifyDataSetChanged(): ").append(((RuntimeException) (obj)).getMessage()).toString());
            return;
        }
        obj = mCache;
        obj;
        JVM INSTR monitorenter ;
        mCache.reset();
        obj;
        JVM INSTR monitorexit ;
        updateTemporaryMetaData();
        obj = mCache.getTemporaryMetaData();
        obj;
        JVM INSTR monitorenter ;
        i = mCache.getTemporaryMetaData().count;
        ai = getVisibleWindow(mVisibleWindowLowerBound, mVisibleWindowUpperBound, i);
        obj;
        JVM INSTR monitorexit ;
        j = ai.length;
        for(k = 0; k < j; k++)
        {
            l = ai[k];
            if(l < i)
                updateRemoteViews(l, false);
        }

        break MISSING_BLOCK_LABEL_182;
        Exception exception;
        exception;
        throw exception;
        exception;
        throw exception;
        mMainQueue.post(new Runnable() {

            public void run()
            {
                FixedSizeRemoteViewsCache fixedsizeremoteviewscache = RemoteViewsAdapter._2D_get0(RemoteViewsAdapter.this);
                fixedsizeremoteviewscache;
                JVM INSTR monitorenter ;
                RemoteViewsAdapter._2D_get0(RemoteViewsAdapter.this).commitTemporaryMetaData();
                fixedsizeremoteviewscache;
                JVM INSTR monitorexit ;
                superNotifyDataSetChanged();
                RemoteViewsAdapter._2D_wrap0(RemoteViewsAdapter.this);
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            final RemoteViewsAdapter this$0;

            
            {
                this$0 = RemoteViewsAdapter.this;
                super();
            }
        }
);
        mNotifyDataSetChangedAfterOnServiceConnected = false;
        return;
    }

    private void processException(String s, Exception exception)
    {
        Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error in ").append(s).append(": ").append(exception.getMessage()).toString());
        s = mCache.getMetaData();
        s;
        JVM INSTR monitorenter ;
        s.reset();
        s;
        JVM INSTR monitorexit ;
        exception = mCache;
        exception;
        JVM INSTR monitorenter ;
        mCache.reset();
        exception;
        JVM INSTR monitorexit ;
        mMainQueue.post(new Runnable() {

            public void run()
            {
                superNotifyDataSetChanged();
            }

            final RemoteViewsAdapter this$0;

            
            {
                this$0 = RemoteViewsAdapter.this;
                super();
            }
        }
);
        return;
        exception;
        throw exception;
        s;
        throw s;
    }

    private boolean requestBindService()
    {
        if(!mServiceConnection.isConnected())
            mServiceConnection.bind(mContext, mAppWidgetId, mIntent);
        mMainQueue.removeMessages(1);
        return mServiceConnection.isConnected();
    }

    private void updateRemoteViews(int i, boolean flag)
    {
        Object obj1;
        long l;
        int j;
        IRemoteViewsFactory iremoteviewsfactory = mServiceConnection.getRemoteViewsFactory();
        try
        {
            obj1 = iremoteviewsfactory.getViewAt(i);
            l = iremoteviewsfactory.getItemId(i);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error in updateRemoteViews(").append(i).append("): ").append(((RemoteException) (obj1)).getMessage()).toString());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error in updateRemoteViews(").append(i).append("): ").append(((RuntimeException) (obj1)).getMessage()).toString());
            return;
        }
        if(obj1 == null)
        {
            Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error in updateRemoteViews(").append(i).append("): ").append(" null RemoteViews ").append("returned from RemoteViewsFactory.").toString());
            return;
        }
        j = ((RemoteViews) (obj1)).getLayoutId();
        Object obj = mCache.getMetaData();
        obj;
        JVM INSTR monitorenter ;
        boolean flag1;
        flag1 = ((RemoteViewsMetaData) (obj)).isViewTypeInRange(j);
        j = FixedSizeRemoteViewsCache._2D_get1(mCache).count;
        obj;
        JVM INSTR monitorexit ;
        obj = mCache;
        obj;
        JVM INSTR monitorenter ;
        if(!flag1)
            break MISSING_BLOCK_LABEL_285;
        int ai[] = getVisibleWindow(mVisibleWindowLowerBound, mVisibleWindowUpperBound, j);
        mCache.insert(i, ((RemoteViews) (obj1)), l, ai);
        if(!flag)
            break MISSING_BLOCK_LABEL_275;
        Handler handler = mMainQueue;
        Runnable runnable = JVM INSTR new #14  <Class RemoteViewsAdapter$4>;
        runnable.this. _cls4();
        handler.post(runnable);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
        Log.e("RemoteViewsAdapter", "Error: widget's RemoteViewsFactory returns more view types than  indicated by getViewTypeCount() ");
          goto _L1
        exception;
        throw exception;
    }

    private void updateTemporaryMetaData()
    {
        Object obj = mServiceConnection.getRemoteViewsFactory();
        boolean flag;
        int i;
        int j;
        LoadingViewTemplate loadingviewtemplate;
        flag = ((IRemoteViewsFactory) (obj)).hasStableIds();
        i = ((IRemoteViewsFactory) (obj)).getViewTypeCount();
        j = ((IRemoteViewsFactory) (obj)).getCount();
        loadingviewtemplate = JVM INSTR new #29  <Class RemoteViewsAdapter$LoadingViewTemplate>;
        loadingviewtemplate.LoadingViewTemplate(((IRemoteViewsFactory) (obj)).getLoadingView(), mContext);
        if(j <= 0)
            break MISSING_BLOCK_LABEL_106;
        RemoteViews remoteviews;
        if(loadingviewtemplate.remoteViews != null)
            break MISSING_BLOCK_LABEL_106;
        remoteviews = ((IRemoteViewsFactory) (obj)).getViewAt(0);
        if(remoteviews == null)
            break MISSING_BLOCK_LABEL_106;
        obj = mContext;
        HandlerThreadExecutor handlerthreadexecutor = JVM INSTR new #26  <Class RemoteViewsAdapter$HandlerThreadExecutor>;
        handlerthreadexecutor.HandlerThreadExecutor(mWorkerThread);
        loadingviewtemplate.loadFirstViewHeight(remoteviews, ((Context) (obj)), handlerthreadexecutor);
        obj = mCache.getTemporaryMetaData();
        obj;
        JVM INSTR monitorenter ;
        obj.hasStableIds = flag;
        obj.viewTypeCount = i + 1;
        obj.count = j;
        obj.loadingTemplate = loadingviewtemplate;
        obj;
        JVM INSTR monitorexit ;
_L1:
        return;
        Object obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1;
        processException("updateMetaData", ((Exception) (obj1)));
          goto _L1
        obj1;
        processException("updateMetaData", ((Exception) (obj1)));
          goto _L1
    }

    protected void finalize()
        throws Throwable
    {
        if(mWorkerThread != null)
            mWorkerThread.quit();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getCount()
    {
        RemoteViewsMetaData remoteviewsmetadata = mCache.getMetaData();
        remoteviewsmetadata;
        JVM INSTR monitorenter ;
        int i = remoteviewsmetadata.count;
        remoteviewsmetadata;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public Object getItem(int i)
    {
        return null;
    }

    public long getItemId(int i)
    {
        FixedSizeRemoteViewsCache fixedsizeremoteviewscache = mCache;
        fixedsizeremoteviewscache;
        JVM INSTR monitorenter ;
        long l;
        if(!mCache.containsMetaDataAt(i))
            break MISSING_BLOCK_LABEL_34;
        l = mCache.getMetaDataAt(i).itemId;
        return l;
        fixedsizeremoteviewscache;
        JVM INSTR monitorexit ;
        return 0L;
        Exception exception;
        exception;
        throw exception;
    }

    public int getItemViewType(int i)
    {
        Object obj = mCache;
        obj;
        JVM INSTR monitorenter ;
        if(!mCache.containsMetaDataAt(i))
            break MISSING_BLOCK_LABEL_52;
        i = mCache.getMetaDataAt(i).typeId;
        obj;
        JVM INSTR monitorexit ;
        obj = mCache.getMetaData();
        obj;
        JVM INSTR monitorenter ;
        i = ((RemoteViewsMetaData) (obj)).getMappedViewType(i);
        obj;
        JVM INSTR monitorexit ;
        return i;
        obj;
        JVM INSTR monitorexit ;
        return 0;
        Exception exception;
        exception;
        throw exception;
        exception;
        throw exception;
    }

    public Intent getRemoteViewsServiceIntent()
    {
        return mIntent;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        FixedSizeRemoteViewsCache fixedsizeremoteviewscache = mCache;
        fixedsizeremoteviewscache;
        JVM INSTR monitorenter ;
        RemoteViews remoteviews = mCache.getRemoteViewsAt(i);
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(remoteviews != null)
            flag = true;
        else
            flag = false;
        flag1 = mServiceConnection.isConnected();
        flag2 = false;
        if(view == null)
            break MISSING_BLOCK_LABEL_61;
        if(view instanceof RemoteViewsFrameLayout)
            mRequestedViews.removeView((RemoteViewsFrameLayout)view);
        if(flag || !(flag1 ^ true)) goto _L2; else goto _L1
_L1:
        requestBindService();
_L5:
        if(!(view instanceof RemoteViewsFrameLayout)) goto _L4; else goto _L3
_L3:
        view = (RemoteViewsFrameLayout)view;
_L6:
        if(!flag)
            break MISSING_BLOCK_LABEL_172;
        view.onRemoteViewsLoaded(remoteviews, mRemoteViewsOnClickHandler, false);
        if(!flag2)
            break MISSING_BLOCK_LABEL_115;
        loadNextIndexInBackground();
_L7:
        fixedsizeremoteviewscache;
        JVM INSTR monitorexit ;
        return view;
_L2:
        flag2 = mCache.queuePositionsToBePreloadedFromRequestedPosition(i);
          goto _L5
_L4:
        view = JVM INSTR new #49  <Class RemoteViewsAdapter$RemoteViewsFrameLayout>;
        view.RemoteViewsFrameLayout(viewgroup.getContext(), mCache);
        view.setExecutor(mAsyncViewLoadExecutor);
          goto _L6
        view;
        throw view;
        view.onRemoteViewsLoaded(mCache.getMetaData().getLoadingTemplate(mContext).remoteViews, mRemoteViewsOnClickHandler, false);
        mRequestedViews.add(i, view);
        mCache.queueRequestedPositionToLoad(i);
        loadNextIndexInBackground();
          goto _L7
    }

    public int getViewTypeCount()
    {
        RemoteViewsMetaData remoteviewsmetadata = mCache.getMetaData();
        remoteviewsmetadata;
        JVM INSTR monitorenter ;
        int i = remoteviewsmetadata.viewTypeCount;
        remoteviewsmetadata;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean handleMessage(Message message)
    {
        boolean flag = false;
        message.what;
        JVM INSTR tableswitch 1 1: default 24
    //                   1 26;
           goto _L1 _L2
_L1:
        return flag;
_L2:
        if(mServiceConnection.isConnected())
            mServiceConnection.unbind(mContext, mAppWidgetId, mIntent);
        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean hasStableIds()
    {
        RemoteViewsMetaData remoteviewsmetadata = mCache.getMetaData();
        remoteviewsmetadata;
        JVM INSTR monitorenter ;
        boolean flag = remoteviewsmetadata.hasStableIds;
        remoteviewsmetadata;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isDataReady()
    {
        return mDataReady;
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        if(getCount() <= 0)
            flag = true;
        return flag;
    }

    public void notifyDataSetChanged()
    {
        mMainQueue.removeMessages(1);
        if(!mServiceConnection.isConnected())
        {
            mNotifyDataSetChangedAfterOnServiceConnected = true;
            requestBindService();
            return;
        } else
        {
            mWorkerQueue.post(new Runnable() {

                public void run()
                {
                    RemoteViewsAdapter._2D_wrap2(RemoteViewsAdapter.this);
                }

                final RemoteViewsAdapter this$0;

            
            {
                this$0 = RemoteViewsAdapter.this;
                super();
            }
            }
);
            return;
        }
    }

    public void saveRemoteViewsCache()
    {
        RemoteViewsCacheKey remoteviewscachekey = new RemoteViewsCacheKey(new android.content.Intent.FilterComparison(mIntent), mAppWidgetId);
        HashMap hashmap = sCachedRemoteViewsCaches;
        hashmap;
        JVM INSTR monitorenter ;
        if(sRemoteViewsCacheRemoveRunnables.containsKey(remoteviewscachekey))
        {
            sCacheRemovalQueue.removeCallbacks((Runnable)sRemoteViewsCacheRemoveRunnables.get(remoteviewscachekey));
            sRemoteViewsCacheRemoveRunnables.remove(remoteviewscachekey);
        }
        Object obj = FixedSizeRemoteViewsCache._2D_get1(mCache);
        obj;
        JVM INSTR monitorenter ;
        int i = FixedSizeRemoteViewsCache._2D_get1(mCache).count;
        obj;
        JVM INSTR monitorexit ;
        obj = mCache;
        obj;
        JVM INSTR monitorenter ;
        int j = FixedSizeRemoteViewsCache._2D_get0(mCache).size();
        obj;
        JVM INSTR monitorexit ;
        if(i <= 0 || j <= 0)
            break MISSING_BLOCK_LABEL_130;
        sCachedRemoteViewsCaches.put(remoteviewscachekey, mCache);
        obj = JVM INSTR new #8   <Class RemoteViewsAdapter$1>;
        ((_cls1) (obj)).this. _cls1();
        sRemoteViewsCacheRemoveRunnables.put(remoteviewscachekey, obj);
        sCacheRemovalQueue.postDelayed(((Runnable) (obj)), 5000L);
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setRemoteViewsOnClickHandler(RemoteViews.OnClickHandler onclickhandler)
    {
        mRemoteViewsOnClickHandler = onclickhandler;
    }

    public void setVisibleRangeHint(int i, int j)
    {
        mVisibleWindowLowerBound = i;
        mVisibleWindowUpperBound = j;
    }

    void superNotifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    private static final String MULTI_USER_PERM = "android.permission.INTERACT_ACROSS_USERS_FULL";
    private static final int REMOTE_VIEWS_CACHE_DURATION = 5000;
    private static final String TAG = "RemoteViewsAdapter";
    private static Handler sCacheRemovalQueue;
    private static HandlerThread sCacheRemovalThread;
    private static final HashMap sCachedRemoteViewsCaches = new HashMap();
    private static final int sDefaultCacheSize = 40;
    private static final int sDefaultLoadingViewHeight = 50;
    private static final int sDefaultMessageType = 0;
    private static final HashMap sRemoteViewsCacheRemoveRunnables = new HashMap();
    private static final int sUnbindServiceDelay = 5000;
    private static final int sUnbindServiceMessageType = 1;
    private final int mAppWidgetId;
    private final Executor mAsyncViewLoadExecutor;
    private final FixedSizeRemoteViewsCache mCache;
    private WeakReference mCallback;
    private final Context mContext;
    private boolean mDataReady;
    private final Intent mIntent;
    private Handler mMainQueue;
    private boolean mNotifyDataSetChangedAfterOnServiceConnected;
    private RemoteViews.OnClickHandler mRemoteViewsOnClickHandler;
    private RemoteViewsFrameLayoutRefSet mRequestedViews;
    private RemoteViewsAdapterServiceConnection mServiceConnection;
    private int mVisibleWindowLowerBound;
    private int mVisibleWindowUpperBound;
    private Handler mWorkerQueue;
    private HandlerThread mWorkerThread;


    // Unreferenced inner class android/widget/RemoteViewsAdapter$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            HashMap hashmap = RemoteViewsAdapter._2D_get8();
            hashmap;
            JVM INSTR monitorenter ;
            if(RemoteViewsAdapter._2D_get8().containsKey(key))
                RemoteViewsAdapter._2D_get8().remove(key);
            if(RemoteViewsAdapter._2D_get9().containsKey(key))
                RemoteViewsAdapter._2D_get9().remove(key);
            hashmap;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final RemoteViewsAdapter this$0;
        final RemoteViewsCacheKey val$key;

            
            {
                this$0 = RemoteViewsAdapter.this;
                key = remoteviewscachekey;
                super();
            }
    }


    // Unreferenced inner class android/widget/RemoteViewsAdapter$4

/* anonymous class */
    class _cls4
        implements Runnable
    {

        public void run()
        {
            RemoteViewsAdapter._2D_get5(RemoteViewsAdapter.this).notifyOnRemoteViewsLoaded(position, rv);
        }

        final RemoteViewsAdapter this$0;
        final int val$position;
        final RemoteViews val$rv;

            
            {
                this$0 = RemoteViewsAdapter.this;
                position = i;
                rv = remoteviews;
                super();
            }
    }


    // Unreferenced inner class android/widget/RemoteViewsAdapter$RemoteViewsAdapterServiceConnection$1

/* anonymous class */
    class RemoteViewsAdapterServiceConnection._cls1
        implements Runnable
    {

        public void run()
        {
            if(!RemoteViewsAdapter._2D_get3(adapter)) goto _L2; else goto _L1
_L1:
            RemoteViewsAdapter._2D_wrap2(adapter);
_L4:
            RemoteViewsAdapter._2D_wrap0(adapter);
            RemoteViewsAdapterServiceConnection._2D_set0(RemoteViewsAdapterServiceConnection.this, true);
            RemoteViewsAdapterServiceConnection._2D_set1(RemoteViewsAdapterServiceConnection.this, false);
            return;
_L2:
            IRemoteViewsFactory iremoteviewsfactory = RemoteViewsAdapter._2D_get6(adapter).getRemoteViewsFactory();
            try
            {
                if(!iremoteviewsfactory.isCreated())
                    iremoteviewsfactory.onDataSetChanged();
            }
            catch(RemoteException remoteexception)
            {
                Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error notifying factory of data set changed in onServiceConnected(): ").append(remoteexception.getMessage()).toString());
                return;
            }
            catch(RuntimeException runtimeexception)
            {
                Log.e("RemoteViewsAdapter", (new StringBuilder()).append("Error notifying factory of data set changed in onServiceConnected(): ").append(runtimeexception.getMessage()).toString());
            }
            RemoteViewsAdapter._2D_wrap4(adapter);
            RemoteViewsAdapter._2D_get2(adapter).post(adapter. new Runnable() {

                public void run()
                {
                    FixedSizeRemoteViewsCache fixedsizeremoteviewscache = RemoteViewsAdapter._2D_get0(adapter);
                    fixedsizeremoteviewscache;
                    JVM INSTR monitorenter ;
                    RemoteViewsAdapter._2D_get0(adapter).commitTemporaryMetaData();
                    fixedsizeremoteviewscache;
                    JVM INSTR monitorexit ;
                    RemoteAdapterConnectionCallback remoteadapterconnectioncallback = (RemoteAdapterConnectionCallback)RemoteViewsAdapter._2D_get1(adapter).get();
                    if(remoteadapterconnectioncallback != null)
                        remoteadapterconnectioncallback.onRemoteAdapterConnected();
                    return;
                    Exception exception;
                    exception;
                    throw exception;
                }

                final RemoteViewsAdapterServiceConnection._cls1 this$2;
                final RemoteViewsAdapter val$adapter;

            
            {
                this$2 = final__pcls1;
                adapter = RemoteViewsAdapter.this;
                super();
            }
            }
);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final RemoteViewsAdapterServiceConnection this$1;
        final RemoteViewsAdapter val$adapter;

            
            {
                this$1 = final_remoteviewsadapterserviceconnection;
                adapter = RemoteViewsAdapter.this;
                super();
            }
    }


    // Unreferenced inner class android/widget/RemoteViewsAdapter$RemoteViewsAdapterServiceConnection$2

/* anonymous class */
    class RemoteViewsAdapterServiceConnection._cls2
        implements Runnable
    {

        public void run()
        {
            RemoteViewsAdapter._2D_get2(adapter).removeMessages(1);
            RemoteAdapterConnectionCallback remoteadapterconnectioncallback = (RemoteAdapterConnectionCallback)RemoteViewsAdapter._2D_get1(adapter).get();
            if(remoteadapterconnectioncallback != null)
                remoteadapterconnectioncallback.onRemoteAdapterDisconnected();
        }

        final RemoteViewsAdapterServiceConnection this$1;
        final RemoteViewsAdapter val$adapter;

            
            {
                this$1 = final_remoteviewsadapterserviceconnection;
                adapter = RemoteViewsAdapter.this;
                super();
            }
    }

}
