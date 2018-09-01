// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.appwidget;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.*;
import java.util.concurrent.Executor;

// Referenced classes of package android.appwidget:
//            AppWidgetProviderInfo, AppWidgetManager

public class AppWidgetHostView extends FrameLayout
{
    private static class ParcelableSparseArray extends SparseArray
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            int j = size();
            parcel.writeInt(j);
            for(i = 0; i < j; i++)
            {
                parcel.writeInt(keyAt(i));
                parcel.writeParcelable((Parcelable)valueAt(i), 0);
            }

        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ParcelableSparseArray createFromParcel(Parcel parcel)
            {
                ParcelableSparseArray parcelablesparsearray = new ParcelableSparseArray(null);
                ClassLoader classloader = parcelablesparsearray.getClass().getClassLoader();
                int i = parcel.readInt();
                for(int j = 0; j < i; j++)
                    parcelablesparsearray.put(parcel.readInt(), parcel.readParcelable(classloader));

                return parcelablesparsearray;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ParcelableSparseArray[] newArray(int i)
            {
                return new ParcelableSparseArray[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;


        private ParcelableSparseArray()
        {
        }

        ParcelableSparseArray(ParcelableSparseArray parcelablesparsearray)
        {
            this();
        }
    }

    private class ViewApplyListener
        implements android.widget.RemoteViews.OnViewAppliedListener
    {

        public void onError(Exception exception)
        {
            if(mIsReapply)
                AppWidgetHostView._2D_set0(AppWidgetHostView.this, mViews.applyAsync(mContext, AppWidgetHostView.this, AppWidgetHostView._2D_get0(AppWidgetHostView.this), new ViewApplyListener(mViews, mLayoutId, false), AppWidgetHostView._2D_get1(AppWidgetHostView.this)));
            else
                AppWidgetHostView._2D_wrap0(AppWidgetHostView.this, null, false, exception);
        }

        public void onViewApplied(View view)
        {
            AppWidgetHostView.this.mLayoutId = mLayoutId;
            mViewMode = 1;
            AppWidgetHostView._2D_wrap0(AppWidgetHostView.this, view, mIsReapply, null);
        }

        private final boolean mIsReapply;
        private final int mLayoutId;
        private final RemoteViews mViews;
        final AppWidgetHostView this$0;

        public ViewApplyListener(RemoteViews remoteviews, int i, boolean flag)
        {
            this$0 = AppWidgetHostView.this;
            super();
            mViews = remoteviews;
            mLayoutId = i;
            mIsReapply = flag;
        }
    }


    static Executor _2D_get0(AppWidgetHostView appwidgethostview)
    {
        return appwidgethostview.mAsyncExecutor;
    }

    static android.widget.RemoteViews.OnClickHandler _2D_get1(AppWidgetHostView appwidgethostview)
    {
        return appwidgethostview.mOnClickHandler;
    }

    static CancellationSignal _2D_set0(AppWidgetHostView appwidgethostview, CancellationSignal cancellationsignal)
    {
        appwidgethostview.mLastExecutionSignal = cancellationsignal;
        return cancellationsignal;
    }

    static void _2D_wrap0(AppWidgetHostView appwidgethostview, View view, boolean flag, Exception exception)
    {
        appwidgethostview.applyContent(view, flag, exception);
    }

    public AppWidgetHostView(Context context)
    {
        this(context, 0x10a0000, 0x10a0001);
    }

    public AppWidgetHostView(Context context, int i, int j)
    {
        super(context);
        mViewMode = 0;
        mLayoutId = -1;
        mFadeStartTime = -1L;
        mOldPaint = new Paint();
        mContext = context;
        setIsRootNamespace(true);
    }

    public AppWidgetHostView(Context context, android.widget.RemoteViews.OnClickHandler onclickhandler)
    {
        this(context, 0x10a0000, 0x10a0001);
        mOnClickHandler = onclickhandler;
    }

    private void applyContent(View view, boolean flag, Exception exception)
    {
        View view1 = view;
        if(view == null)
        {
            if(mViewMode == 2)
                return;
            Log.w("AppWidgetHostView", "updateAppWidget couldn't find any view, using error view", exception);
            view1 = getErrorView();
            mViewMode = 2;
        }
        if(!flag)
        {
            prepareView(view1);
            addView(view1);
        }
        if(mView != view1)
        {
            removeView(mView);
            mView = view1;
        }
    }

    private int generateId()
    {
        int i = getId();
        int j = i;
        if(i == -1)
            j = mAppWidgetId;
        return j;
    }

    public static Rect getDefaultPaddingForWidget(Context context, ComponentName componentname, Rect rect)
    {
        PackageManager packagemanager = context.getPackageManager();
        if(rect == null)
            rect = new Rect(0, 0, 0, 0);
        else
            rect.set(0, 0, 0, 0);
        try
        {
            componentname = packagemanager.getApplicationInfo(componentname.getPackageName(), 0);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return rect;
        }
        if(((ApplicationInfo) (componentname)).targetSdkVersion >= 14)
        {
            context = context.getResources();
            rect.left = context.getDimensionPixelSize(0x1050066);
            rect.right = context.getDimensionPixelSize(0x1050067);
            rect.top = context.getDimensionPixelSize(0x1050068);
            rect.bottom = context.getDimensionPixelSize(0x1050065);
        }
        return rect;
    }

    private void inflateAsync(RemoteViews remoteviews)
    {
        mRemoteContext = getRemoteContext();
        int i = remoteviews.getLayoutId();
        if(i == mLayoutId && mView != null)
            try
            {
                Context context = mContext;
                View view = mView;
                Executor executor = mAsyncExecutor;
                ViewApplyListener viewapplylistener = JVM INSTR new #13  <Class AppWidgetHostView$ViewApplyListener>;
                viewapplylistener.this. ViewApplyListener(remoteviews, i, true);
                mLastExecutionSignal = remoteviews.reapplyAsync(context, view, executor, viewapplylistener, mOnClickHandler);
            }
            catch(Exception exception) { }
        if(mLastExecutionSignal == null)
            mLastExecutionSignal = remoteviews.applyAsync(mContext, this, mAsyncExecutor, new ViewApplyListener(remoteviews, i, false), mOnClickHandler);
    }

    private void updateContentDescription(AppWidgetProviderInfo appwidgetproviderinfo)
    {
        if(appwidgetproviderinfo == null) goto _L2; else goto _L1
_L1:
        Object obj;
        ApplicationInfo applicationinfo;
        obj = (LauncherApps)getContext().getSystemService(android/content/pm/LauncherApps);
        applicationinfo = null;
        obj = ((LauncherApps) (obj)).getApplicationInfo(appwidgetproviderinfo.provider.getPackageName(), 0, appwidgetproviderinfo.getProfile());
        applicationinfo = ((ApplicationInfo) (obj));
_L4:
        if(applicationinfo != null && (applicationinfo.flags & 0x40000000) != 0)
            setContentDescription(Resources.getSystem().getString(0x1040625, new Object[] {
                appwidgetproviderinfo.label
            }));
        else
            setContentDescription(appwidgetproviderinfo.label);
_L2:
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void applyRemoteViews(RemoteViews remoteviews, boolean flag)
    {
        boolean flag1;
        boolean flag2;
        Object obj;
        Object obj1;
        Object obj2;
        flag1 = false;
        flag2 = false;
        obj = null;
        obj1 = null;
        obj2 = null;
        if(mLastExecutionSignal != null)
        {
            mLastExecutionSignal.cancel();
            mLastExecutionSignal = null;
        }
        if(remoteviews != null) goto _L2; else goto _L1
_L1:
        if(mViewMode == 3)
            return;
        obj1 = getDefaultView();
        mLayoutId = -1;
        mViewMode = 3;
        flag = flag2;
        obj = obj2;
_L3:
        applyContent(((View) (obj1)), flag, ((Exception) (obj)));
        updateContentDescription(mInfo);
        return;
_L2:
        int i;
        Object obj3;
        if(mAsyncExecutor != null && flag)
        {
            inflateAsync(remoteviews);
            return;
        }
        mRemoteContext = getRemoteContext();
        i = remoteviews.getLayoutId();
        obj2 = obj;
        obj3 = obj1;
        flag = flag1;
        if(i != mLayoutId)
            break MISSING_BLOCK_LABEL_165;
        remoteviews.reapply(mContext, mView, mOnClickHandler);
        obj2 = mView;
        flag = true;
        obj3 = obj1;
_L4:
        obj1 = obj2;
        obj = obj3;
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_197;
        obj1 = remoteviews.apply(mContext, this, mOnClickHandler);
        obj = obj3;
_L5:
        mLayoutId = i;
        mViewMode = 1;
          goto _L3
        obj3;
        obj2 = obj;
        flag = flag1;
          goto _L4
        obj;
        obj1 = obj2;
          goto _L5
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        Object obj;
        Parcelable parcelable = (Parcelable)sparsearray.get(generateId());
        obj = null;
        sparsearray = ((SparseArray) (obj));
        if(parcelable != null)
        {
            sparsearray = ((SparseArray) (obj));
            if(parcelable instanceof ParcelableSparseArray)
                sparsearray = (ParcelableSparseArray)parcelable;
        }
        obj = sparsearray;
        if(sparsearray == null)
            obj = new ParcelableSparseArray(null);
        super.dispatchRestoreInstanceState(((SparseArray) (obj)));
_L1:
        return;
        Exception exception;
        exception;
        StringBuilder stringbuilder = (new StringBuilder()).append("failed to restoreInstanceState for widget id: ").append(mAppWidgetId).append(", ");
        if(mInfo == null)
            sparsearray = "null";
        else
            sparsearray = mInfo.provider;
        Log.e("AppWidgetHostView", stringbuilder.append(sparsearray).toString(), exception);
          goto _L1
    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        ParcelableSparseArray parcelablesparsearray = new ParcelableSparseArray(null);
        super.dispatchSaveInstanceState(parcelablesparsearray);
        sparsearray.put(generateId(), parcelablesparsearray);
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        return super.drawChild(canvas, view, l);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        Context context;
        if(mRemoteContext != null)
            context = mRemoteContext;
        else
            context = mContext;
        return new android.widget.FrameLayout.LayoutParams(context, attributeset);
    }

    public int getAppWidgetId()
    {
        return mAppWidgetId;
    }

    public AppWidgetProviderInfo getAppWidgetInfo()
    {
        return mInfo;
    }

    protected View getDefaultView()
    {
        View view;
        Object obj;
        view = null;
        obj = null;
        if(mInfo == null) goto _L2; else goto _L1
_L1:
        Object obj1;
        LayoutInflater layoutinflater;
        int i;
        obj1 = getRemoteContext();
        mRemoteContext = ((Context) (obj1));
        layoutinflater = ((LayoutInflater)((Context) (obj1)).getSystemService("layout_inflater")).cloneInContext(((Context) (obj1)));
        layoutinflater.setFilter(sInflaterFilter);
        obj1 = AppWidgetManager.getInstance(mContext).getAppWidgetOptions(mAppWidgetId);
        i = mInfo.initialLayout;
        int j = i;
        if(!((Bundle) (obj1)).containsKey("appWidgetCategory"))
            break MISSING_BLOCK_LABEL_116;
        j = i;
        if(((Bundle) (obj1)).getInt("appWidgetCategory") != 2)
            break MISSING_BLOCK_LABEL_116;
        j = mInfo.initialKeyguardLayout;
        if(j == 0)
            j = i;
        obj1 = layoutinflater.inflate(j, this, false);
        view = ((View) (obj1));
_L4:
        if(obj != null)
            Log.w("AppWidgetHostView", (new StringBuilder()).append("Error inflating AppWidget ").append(mInfo).append(": ").append(((Exception) (obj)).toString()).toString());
        obj = view;
        if(view == null)
            obj = getErrorView();
        return ((View) (obj));
_L2:
        try
        {
            Log.w("AppWidgetHostView", "can't inflate defaultView because mInfo is missing");
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected View getErrorView()
    {
        TextView textview = new TextView(mContext);
        textview.setText(0x1040243);
        textview.setBackgroundColor(Color.argb(127, 0, 0, 0));
        return textview;
    }

    protected Context getRemoteContext()
    {
        Context context;
        try
        {
            context = mContext.createApplicationContext(mInfo.providerInfo.applicationInfo, 4);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.e("AppWidgetHostView", (new StringBuilder()).append("Package name ").append(mInfo.providerInfo.packageName).append(" not found").toString());
            return mContext;
        }
        return context;
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/appwidget/AppWidgetHostView.getName());
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
_L1:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        Log.e("AppWidgetHostView", "Remote provider threw runtime exception, using error view instead.", runtimeexception);
        removeViewInLayout(mView);
        View view = getErrorView();
        prepareView(view);
        addViewInLayout(view, 0, view.getLayoutParams());
        measureChild(view, android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x40000000));
        view.layout(0, 0, view.getMeasuredWidth() + mPaddingLeft + mPaddingRight, view.getMeasuredHeight() + mPaddingTop + mPaddingBottom);
        mView = view;
        mViewMode = 2;
          goto _L1
    }

    protected void prepareView(View view)
    {
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
        android.widget.FrameLayout.LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = new android.widget.FrameLayout.LayoutParams(-1, -1);
        layoutparams1.gravity = 17;
        view.setLayoutParams(layoutparams1);
    }

    void resetAppWidget(AppWidgetProviderInfo appwidgetproviderinfo)
    {
        mInfo = appwidgetproviderinfo;
        mViewMode = 0;
        updateAppWidget(null);
    }

    public void setAppWidget(int i, AppWidgetProviderInfo appwidgetproviderinfo)
    {
        mAppWidgetId = i;
        mInfo = appwidgetproviderinfo;
        if(appwidgetproviderinfo != null)
        {
            Rect rect = getDefaultPaddingForWidget(mContext, appwidgetproviderinfo.provider, null);
            setPadding(rect.left, rect.top, rect.right, rect.bottom);
            updateContentDescription(appwidgetproviderinfo);
        }
    }

    public void setExecutor(Executor executor)
    {
        if(mLastExecutionSignal != null)
        {
            mLastExecutionSignal.cancel();
            mLastExecutionSignal = null;
        }
        mAsyncExecutor = executor;
    }

    public void setOnClickHandler(android.widget.RemoteViews.OnClickHandler onclickhandler)
    {
        mOnClickHandler = onclickhandler;
    }

    public void updateAppWidget(RemoteViews remoteviews)
    {
        applyRemoteViews(remoteviews, true);
    }

    public void updateAppWidgetOptions(Bundle bundle)
    {
        AppWidgetManager.getInstance(mContext).updateAppWidgetOptions(mAppWidgetId, bundle);
    }

    public void updateAppWidgetSize(Bundle bundle, int i, int j, int k, int l)
    {
        updateAppWidgetSize(bundle, i, j, k, l, false);
    }

    public void updateAppWidgetSize(Bundle bundle, int i, int j, int k, int l, boolean flag)
    {
        Bundle bundle1 = bundle;
        if(bundle == null)
            bundle1 = new Bundle();
        Rect rect = new Rect();
        bundle = rect;
        if(mInfo != null)
            bundle = getDefaultPaddingForWidget(mContext, mInfo.provider, rect);
        float f = getResources().getDisplayMetrics().density;
        int i1 = (int)((float)(((Rect) (bundle)).left + ((Rect) (bundle)).right) / f);
        int j1 = (int)((float)(((Rect) (bundle)).top + ((Rect) (bundle)).bottom) / f);
        int k1;
        if(flag)
            k1 = 0;
        else
            k1 = i1;
        k1 = i - k1;
        if(flag)
            i = 0;
        else
            i = j1;
        j -= i;
        if(flag)
            i1 = 0;
        k -= i1;
        if(flag)
            j1 = 0;
        l -= j1;
        bundle = AppWidgetManager.getInstance(mContext).getAppWidgetOptions(mAppWidgetId);
        i = 0;
        break MISSING_BLOCK_LABEL_168;
        if(k1 != bundle.getInt("appWidgetMinWidth") || j != bundle.getInt("appWidgetMinHeight") || k != bundle.getInt("appWidgetMaxWidth") || l != bundle.getInt("appWidgetMaxHeight"))
            i = 1;
        if(i != 0)
        {
            bundle1.putInt("appWidgetMinWidth", k1);
            bundle1.putInt("appWidgetMinHeight", j);
            bundle1.putInt("appWidgetMaxWidth", k);
            bundle1.putInt("appWidgetMaxHeight", l);
            updateAppWidgetOptions(bundle1);
        }
        return;
    }

    void viewDataChanged(int i)
    {
        Object obj = findViewById(i);
        if(obj == null || !(obj instanceof AdapterView)) goto _L2; else goto _L1
_L1:
        android.widget.Adapter adapter;
        obj = (AdapterView)obj;
        adapter = ((AdapterView) (obj)).getAdapter();
        if(!(adapter instanceof BaseAdapter)) goto _L4; else goto _L3
_L3:
        ((BaseAdapter)adapter).notifyDataSetChanged();
_L2:
        return;
_L4:
        if(adapter == null && (obj instanceof android.widget.RemoteViewsAdapter.RemoteAdapterConnectionCallback))
            ((android.widget.RemoteViewsAdapter.RemoteAdapterConnectionCallback)obj).deferNotifyDataSetChanged();
        if(true) goto _L2; else goto _L5
_L5:
    }

    static final boolean CROSSFADE = false;
    static final int FADE_DURATION = 1000;
    static final boolean LOGD = false;
    static final String TAG = "AppWidgetHostView";
    static final int VIEW_MODE_CONTENT = 1;
    static final int VIEW_MODE_DEFAULT = 3;
    static final int VIEW_MODE_ERROR = 2;
    static final int VIEW_MODE_NOINIT = 0;
    static final android.view.LayoutInflater.Filter sInflaterFilter = new android.view.LayoutInflater.Filter() {

        public boolean onLoadClass(Class class1)
        {
            return class1.isAnnotationPresent(android/widget/RemoteViews$RemoteView);
        }

    }
;
    int mAppWidgetId;
    private Executor mAsyncExecutor;
    Context mContext;
    long mFadeStartTime;
    AppWidgetProviderInfo mInfo;
    private CancellationSignal mLastExecutionSignal;
    int mLayoutId;
    Bitmap mOld;
    Paint mOldPaint;
    private android.widget.RemoteViews.OnClickHandler mOnClickHandler;
    Context mRemoteContext;
    View mView;
    int mViewMode;

}
