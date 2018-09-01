// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.view.menu.ShowableListMenu;

// Referenced classes of package android.widget:
//            LinearLayout, FrameLayout, ImageView, ListPopupWindow, 
//            ListView, ActivityChooserModel, BaseAdapter, TextView, 
//            Toast, AdapterView, ForwardingListener

public class ActivityChooserView extends ViewGroup
    implements ActivityChooserModel.ActivityChooserModelClient
{
    private class ActivityChooserViewAdapter extends BaseAdapter
    {

        public int getActivityCount()
        {
            return mDataModel.getActivityCount();
        }

        public int getCount()
        {
            int i = mDataModel.getActivityCount();
            int j = i;
            if(!mShowDefaultActivity)
            {
                j = i;
                if(mDataModel.getDefaultActivity() != null)
                    j = i - 1;
            }
            i = Math.min(j, mMaxActivityCount);
            j = i;
            if(mShowFooterView)
                j = i + 1;
            return j;
        }

        public ActivityChooserModel getDataModel()
        {
            return mDataModel;
        }

        public ResolveInfo getDefaultActivity()
        {
            return mDataModel.getDefaultActivity();
        }

        public int getHistorySize()
        {
            return mDataModel.getHistorySize();
        }

        public Object getItem(int i)
        {
            int j;
            switch(getItemViewType(i))
            {
            default:
                throw new IllegalArgumentException();

            case 1: // '\001'
                return null;

            case 0: // '\0'
                j = i;
                break;
            }
            if(!mShowDefaultActivity)
            {
                j = i;
                if(mDataModel.getDefaultActivity() != null)
                    j = i + 1;
            }
            return mDataModel.getActivity(j);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public int getItemViewType(int i)
        {
            return !mShowFooterView || i != getCount() - 1 ? 0 : 1;
        }

        public boolean getShowDefaultActivity()
        {
            return mShowDefaultActivity;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            getItemViewType(i);
            JVM INSTR tableswitch 0 1: default 28
        //                       0 104
        //                       1 36;
               goto _L1 _L2 _L3
_L1:
            throw new IllegalArgumentException();
_L3:
            if(view == null) goto _L5; else goto _L4
_L4:
            View view1 = view;
            if(view.getId() == 1) goto _L6; else goto _L5
_L5:
            view1 = LayoutInflater.from(getContext()).inflate(0x1090024, viewgroup, false);
            view1.setId(1);
            ((TextView)view1.findViewById(0x1020016)).setText(ActivityChooserView._2D_get1(ActivityChooserView.this).getString(0x1040057));
_L6:
            return view1;
_L2:
            if(view == null) goto _L8; else goto _L7
_L7:
            view1 = view;
            if(view.getId() == 0x10202e2) goto _L9; else goto _L8
_L8:
            view1 = LayoutInflater.from(getContext()).inflate(0x1090024, viewgroup, false);
_L9:
            android.content.pm.PackageManager packagemanager = ActivityChooserView._2D_get1(ActivityChooserView.this).getPackageManager();
            view = (ImageView)view1.findViewById(0x1020006);
            viewgroup = (ResolveInfo)getItem(i);
            view.setImageDrawable(viewgroup.loadIcon(packagemanager));
            ((TextView)view1.findViewById(0x1020016)).setText(viewgroup.loadLabel(packagemanager));
            if(mShowDefaultActivity && i == 0 && mHighlightDefaultActivity)
                view1.setActivated(true);
            else
                view1.setActivated(false);
            return view1;
        }

        public int getViewTypeCount()
        {
            return 3;
        }

        public int measureContentWidth()
        {
            int i = mMaxActivityCount;
            mMaxActivityCount = 0x7fffffff;
            int j = 0;
            View view = null;
            int k = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            int l = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            int i1 = getCount();
            for(int j1 = 0; j1 < i1; j1++)
            {
                view = getView(j1, view, null);
                view.measure(k, l);
                j = Math.max(j, view.getMeasuredWidth());
            }

            mMaxActivityCount = i;
            return j;
        }

        public void setDataModel(ActivityChooserModel activitychoosermodel)
        {
            ActivityChooserModel activitychoosermodel1 = ActivityChooserView._2D_get0(ActivityChooserView.this).getDataModel();
            if(activitychoosermodel1 != null && isShown())
                activitychoosermodel1.unregisterObserver(ActivityChooserView._2D_get6(ActivityChooserView.this));
            mDataModel = activitychoosermodel;
            if(activitychoosermodel != null && isShown())
                activitychoosermodel.registerObserver(ActivityChooserView._2D_get6(ActivityChooserView.this));
            notifyDataSetChanged();
        }

        public void setMaxActivityCount(int i)
        {
            if(mMaxActivityCount != i)
            {
                mMaxActivityCount = i;
                notifyDataSetChanged();
            }
        }

        public void setShowDefaultActivity(boolean flag, boolean flag1)
        {
            if(mShowDefaultActivity != flag || mHighlightDefaultActivity != flag1)
            {
                mShowDefaultActivity = flag;
                mHighlightDefaultActivity = flag1;
                notifyDataSetChanged();
            }
        }

        public void setShowFooterView(boolean flag)
        {
            if(mShowFooterView != flag)
            {
                mShowFooterView = flag;
                notifyDataSetChanged();
            }
        }

        private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
        private static final int ITEM_VIEW_TYPE_COUNT = 3;
        private static final int ITEM_VIEW_TYPE_FOOTER = 1;
        public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
        public static final int MAX_ACTIVITY_COUNT_UNLIMITED = 0x7fffffff;
        private ActivityChooserModel mDataModel;
        private boolean mHighlightDefaultActivity;
        private int mMaxActivityCount;
        private boolean mShowDefaultActivity;
        private boolean mShowFooterView;
        final ActivityChooserView this$0;

        private ActivityChooserViewAdapter()
        {
            this$0 = ActivityChooserView.this;
            super();
            mMaxActivityCount = 4;
        }

        ActivityChooserViewAdapter(ActivityChooserViewAdapter activitychooserviewadapter)
        {
            this();
        }
    }

    private class Callbacks
        implements AdapterView.OnItemClickListener, android.view.View.OnClickListener, android.view.View.OnLongClickListener, PopupWindow.OnDismissListener
    {

        private void notifyOnDismissListener()
        {
            if(ActivityChooserView._2D_get7(ActivityChooserView.this) != null)
                ActivityChooserView._2D_get7(ActivityChooserView.this).onDismiss();
        }

        private void startActivity(Intent intent, ResolveInfo resolveinfo)
        {
            ActivityChooserView._2D_get1(ActivityChooserView.this).startActivity(intent);
_L1:
            return;
            intent;
            intent = resolveinfo.loadLabel(ActivityChooserView._2D_get1(ActivityChooserView.this).getPackageManager());
            intent = ActivityChooserView._2D_get1(ActivityChooserView.this).getString(0x104005d, new Object[] {
                intent
            });
            Log.e("ActivityChooserView", intent);
            Toast.makeText(ActivityChooserView._2D_get1(ActivityChooserView.this), intent, 0).show();
              goto _L1
        }

        public void onClick(View view)
        {
            if(view == ActivityChooserView._2D_get2(ActivityChooserView.this))
            {
                dismissPopup();
                view = ActivityChooserView._2D_get0(ActivityChooserView.this).getDefaultActivity();
                int i = ActivityChooserView._2D_get0(ActivityChooserView.this).getDataModel().getActivityIndex(view);
                Intent intent = ActivityChooserView._2D_get0(ActivityChooserView.this).getDataModel().chooseActivity(i);
                if(intent != null)
                {
                    intent.addFlags(0x80000);
                    startActivity(intent, view);
                }
            } else
            if(view == ActivityChooserView._2D_get3(ActivityChooserView.this))
            {
                ActivityChooserView._2D_set0(ActivityChooserView.this, false);
                ActivityChooserView._2D_wrap1(ActivityChooserView.this, ActivityChooserView._2D_get4(ActivityChooserView.this));
            } else
            {
                throw new IllegalArgumentException();
            }
        }

        public void onDismiss()
        {
            notifyOnDismissListener();
            if(mProvider != null)
                mProvider.subUiVisibilityChanged(false);
        }

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            ((ActivityChooserViewAdapter)adapterview.getAdapter()).getItemViewType(i);
            JVM INSTR tableswitch 0 1: default 32
        //                       0 50
        //                       1 40;
               goto _L1 _L2 _L3
_L1:
            throw new IllegalArgumentException();
_L3:
            ActivityChooserView._2D_wrap1(ActivityChooserView.this, 0x7fffffff);
_L5:
            return;
_L2:
            dismissPopup();
            if(ActivityChooserView._2D_get5(ActivityChooserView.this))
            {
                if(i > 0)
                    ActivityChooserView._2D_get0(ActivityChooserView.this).getDataModel().setDefaultActivity(i);
            } else
            {
                if(!ActivityChooserView._2D_get0(ActivityChooserView.this).getShowDefaultActivity())
                    i++;
                adapterview = ActivityChooserView._2D_get0(ActivityChooserView.this).getDataModel().chooseActivity(i);
                if(adapterview != null)
                {
                    adapterview.addFlags(0x80000);
                    startActivity(adapterview, ActivityChooserView._2D_get0(ActivityChooserView.this).getDataModel().getActivity(i));
                }
            }
            if(true) goto _L5; else goto _L4
_L4:
        }

        public boolean onLongClick(View view)
        {
            if(view == ActivityChooserView._2D_get2(ActivityChooserView.this))
            {
                if(ActivityChooserView._2D_get0(ActivityChooserView.this).getCount() > 0)
                {
                    ActivityChooserView._2D_set0(ActivityChooserView.this, true);
                    ActivityChooserView._2D_wrap1(ActivityChooserView.this, ActivityChooserView._2D_get4(ActivityChooserView.this));
                }
                return true;
            } else
            {
                throw new IllegalArgumentException();
            }
        }

        final ActivityChooserView this$0;

        private Callbacks()
        {
            this$0 = ActivityChooserView.this;
            super();
        }

        Callbacks(Callbacks callbacks)
        {
            this();
        }
    }


    static ActivityChooserViewAdapter _2D_get0(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mAdapter;
    }

    static Context _2D_get1(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mContext;
    }

    static FrameLayout _2D_get2(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mDefaultActivityButton;
    }

    static FrameLayout _2D_get3(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mExpandActivityOverflowButton;
    }

    static int _2D_get4(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mInitialActivityCount;
    }

    static boolean _2D_get5(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mIsSelectingDefaultActivity;
    }

    static DataSetObserver _2D_get6(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mModelDataSetOberver;
    }

    static PopupWindow.OnDismissListener _2D_get7(ActivityChooserView activitychooserview)
    {
        return activitychooserview.mOnDismissListener;
    }

    static boolean _2D_set0(ActivityChooserView activitychooserview, boolean flag)
    {
        activitychooserview.mIsSelectingDefaultActivity = flag;
        return flag;
    }

    static ListPopupWindow _2D_wrap0(ActivityChooserView activitychooserview)
    {
        return activitychooserview.getListPopupWindow();
    }

    static void _2D_wrap1(ActivityChooserView activitychooserview, int i)
    {
        activitychooserview.showPopupUnchecked(i);
    }

    static void _2D_wrap2(ActivityChooserView activitychooserview)
    {
        activitychooserview.updateAppearance();
    }

    public ActivityChooserView(Context context)
    {
        this(context, null);
    }

    public ActivityChooserView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mModelDataSetOberver = new DataSetObserver() {

            public void onChanged()
            {
                super.onChanged();
                ActivityChooserView._2D_get0(ActivityChooserView.this).notifyDataSetChanged();
            }

            public void onInvalidated()
            {
                super.onInvalidated();
                ActivityChooserView._2D_get0(ActivityChooserView.this).notifyDataSetInvalidated();
            }

            final ActivityChooserView this$0;

            
            {
                this$0 = ActivityChooserView.this;
                super();
            }
        }
;
        mOnGlobalLayoutListener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

            public void onGlobalLayout()
            {
                if(!isShowingPopup()) goto _L2; else goto _L1
_L1:
                if(isShown()) goto _L4; else goto _L3
_L3:
                ActivityChooserView._2D_wrap0(ActivityChooserView.this).dismiss();
_L2:
                return;
_L4:
                ActivityChooserView._2D_wrap0(ActivityChooserView.this).show();
                if(mProvider != null)
                    mProvider.subUiVisibilityChanged(true);
                if(true) goto _L2; else goto _L5
_L5:
            }

            final ActivityChooserView this$0;

            
            {
                this$0 = ActivityChooserView.this;
                super();
            }
        }
;
        mInitialActivityCount = 4;
        Object obj = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ActivityChooserView, i, j);
        mInitialActivityCount = ((TypedArray) (obj)).getInt(1, 4);
        attributeset = ((TypedArray) (obj)).getDrawable(0);
        ((TypedArray) (obj)).recycle();
        LayoutInflater.from(mContext).inflate(0x1090023, this, true);
        mCallbacks = new Callbacks(null);
        mActivityChooserContent = (LinearLayout)findViewById(0x102018b);
        mActivityChooserContentBackground = mActivityChooserContent.getBackground();
        mDefaultActivityButton = (FrameLayout)findViewById(0x1020221);
        mDefaultActivityButton.setOnClickListener(mCallbacks);
        mDefaultActivityButton.setOnLongClickListener(mCallbacks);
        mDefaultActivityButtonImage = (ImageView)mDefaultActivityButton.findViewById(0x1020297);
        obj = (FrameLayout)findViewById(0x1020237);
        ((FrameLayout) (obj)).setOnClickListener(mCallbacks);
        ((FrameLayout) (obj)).setAccessibilityDelegate(new android.view.View.AccessibilityDelegate() {

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
            {
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
                accessibilitynodeinfo.setCanOpenPopup(true);
            }

            final ActivityChooserView this$0;

            
            {
                this$0 = ActivityChooserView.this;
                super();
            }
        }
);
        ((FrameLayout) (obj)).setOnTouchListener(new ForwardingListener(((View) (obj))) {

            public ShowableListMenu getPopup()
            {
                return ActivityChooserView._2D_wrap0(ActivityChooserView.this);
            }

            protected boolean onForwardingStarted()
            {
                showPopup();
                return true;
            }

            protected boolean onForwardingStopped()
            {
                dismissPopup();
                return true;
            }

            final ActivityChooserView this$0;

            
            {
                this$0 = ActivityChooserView.this;
                super(view);
            }
        }
);
        mExpandActivityOverflowButton = ((FrameLayout) (obj));
        mExpandActivityOverflowButtonImage = (ImageView)((FrameLayout) (obj)).findViewById(0x1020297);
        mExpandActivityOverflowButtonImage.setImageDrawable(attributeset);
        mAdapter = new ActivityChooserViewAdapter(null);
        mAdapter.registerDataSetObserver(new DataSetObserver() {

            public void onChanged()
            {
                super.onChanged();
                ActivityChooserView._2D_wrap2(ActivityChooserView.this);
            }

            final ActivityChooserView this$0;

            
            {
                this$0 = ActivityChooserView.this;
                super();
            }
        }
);
        context = context.getResources();
        mListPopupMaxWidth = Math.max(context.getDisplayMetrics().widthPixels / 2, context.getDimensionPixelSize(0x1050041));
    }

    private ListPopupWindow getListPopupWindow()
    {
        if(mListPopupWindow == null)
        {
            mListPopupWindow = new ListPopupWindow(getContext());
            mListPopupWindow.setAdapter(mAdapter);
            mListPopupWindow.setAnchorView(this);
            mListPopupWindow.setModal(true);
            mListPopupWindow.setOnItemClickListener(mCallbacks);
            mListPopupWindow.setOnDismissListener(mCallbacks);
        }
        return mListPopupWindow;
    }

    private void showPopupUnchecked(int i)
    {
        if(mAdapter.getDataModel() == null)
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
        boolean flag;
        int j;
        int k;
        ListPopupWindow listpopupwindow;
        if(mDefaultActivityButton.getVisibility() == 0)
            flag = true;
        else
            flag = false;
        j = mAdapter.getActivityCount();
        if(flag)
            k = 1;
        else
            k = 0;
        if(i != 0x7fffffff && j > i + k)
        {
            mAdapter.setShowFooterView(true);
            mAdapter.setMaxActivityCount(i - 1);
        } else
        {
            mAdapter.setShowFooterView(false);
            mAdapter.setMaxActivityCount(i);
        }
        listpopupwindow = getListPopupWindow();
        if(!listpopupwindow.isShowing())
        {
            if(mIsSelectingDefaultActivity || flag ^ true)
                mAdapter.setShowDefaultActivity(true, flag);
            else
                mAdapter.setShowDefaultActivity(false, false);
            listpopupwindow.setContentWidth(Math.min(mAdapter.measureContentWidth(), mListPopupMaxWidth));
            listpopupwindow.show();
            if(mProvider != null)
                mProvider.subUiVisibilityChanged(true);
            listpopupwindow.getListView().setContentDescription(mContext.getString(0x104005c));
            listpopupwindow.getListView().setSelector(new ColorDrawable(0));
        }
    }

    private void updateAppearance()
    {
        int i;
        int j;
        if(mAdapter.getCount() > 0)
            mExpandActivityOverflowButton.setEnabled(true);
        else
            mExpandActivityOverflowButton.setEnabled(false);
        i = mAdapter.getActivityCount();
        j = mAdapter.getHistorySize();
        if(i == 1 || i > 1 && j > 0)
        {
            mDefaultActivityButton.setVisibility(0);
            Object obj = mAdapter.getDefaultActivity();
            android.content.pm.PackageManager packagemanager = mContext.getPackageManager();
            mDefaultActivityButtonImage.setImageDrawable(((ResolveInfo) (obj)).loadIcon(packagemanager));
            if(mDefaultActionButtonContentDescription != 0)
            {
                obj = ((ResolveInfo) (obj)).loadLabel(packagemanager);
                obj = mContext.getString(mDefaultActionButtonContentDescription, new Object[] {
                    obj
                });
                mDefaultActivityButton.setContentDescription(((CharSequence) (obj)));
            }
        } else
        {
            mDefaultActivityButton.setVisibility(8);
        }
        if(mDefaultActivityButton.getVisibility() == 0)
            mActivityChooserContent.setBackground(mActivityChooserContentBackground);
        else
            mActivityChooserContent.setBackground(null);
    }

    public boolean dismissPopup()
    {
        if(isShowingPopup())
        {
            getListPopupWindow().dismiss();
            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
            if(viewtreeobserver.isAlive())
                viewtreeobserver.removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
        }
        return true;
    }

    public ActivityChooserModel getDataModel()
    {
        return mAdapter.getDataModel();
    }

    public boolean isShowingPopup()
    {
        return getListPopupWindow().isShowing();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        ActivityChooserModel activitychoosermodel = mAdapter.getDataModel();
        if(activitychoosermodel != null)
            activitychoosermodel.registerObserver(mModelDataSetOberver);
        mIsAttachedToWindow = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        Object obj = mAdapter.getDataModel();
        if(obj != null)
            ((ActivityChooserModel) (obj)).unregisterObserver(mModelDataSetOberver);
        obj = getViewTreeObserver();
        if(((ViewTreeObserver) (obj)).isAlive())
            ((ViewTreeObserver) (obj)).removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
        if(isShowingPopup())
            dismissPopup();
        mIsAttachedToWindow = false;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        mActivityChooserContent.layout(0, 0, k - i, l - j);
        if(!isShowingPopup())
            dismissPopup();
    }

    protected void onMeasure(int i, int j)
    {
        LinearLayout linearlayout = mActivityChooserContent;
        int k = j;
        if(mDefaultActivityButton.getVisibility() != 0)
            k = android.view.View.MeasureSpec.makeMeasureSpec(android.view.View.MeasureSpec.getSize(j), 0x40000000);
        measureChild(linearlayout, i, k);
        setMeasuredDimension(linearlayout.getMeasuredWidth(), linearlayout.getMeasuredHeight());
    }

    public void setActivityChooserModel(ActivityChooserModel activitychoosermodel)
    {
        mAdapter.setDataModel(activitychoosermodel);
        if(isShowingPopup())
        {
            dismissPopup();
            showPopup();
        }
    }

    public void setDefaultActionButtonContentDescription(int i)
    {
        mDefaultActionButtonContentDescription = i;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i)
    {
        String s = mContext.getString(i);
        mExpandActivityOverflowButtonImage.setContentDescription(s);
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable)
    {
        mExpandActivityOverflowButtonImage.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i)
    {
        mInitialActivityCount = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public void setProvider(ActionProvider actionprovider)
    {
        mProvider = actionprovider;
    }

    public boolean showPopup()
    {
        if(isShowingPopup() || mIsAttachedToWindow ^ true)
        {
            return false;
        } else
        {
            mIsSelectingDefaultActivity = false;
            showPopupUnchecked(mInitialActivityCount);
            return true;
        }
    }

    private static final String LOG_TAG = "ActivityChooserView";
    private final LinearLayout mActivityChooserContent;
    private final Drawable mActivityChooserContentBackground;
    private final ActivityChooserViewAdapter mAdapter;
    private final Callbacks mCallbacks;
    private int mDefaultActionButtonContentDescription;
    private final FrameLayout mDefaultActivityButton;
    private final ImageView mDefaultActivityButtonImage;
    private final FrameLayout mExpandActivityOverflowButton;
    private final ImageView mExpandActivityOverflowButtonImage;
    private int mInitialActivityCount;
    private boolean mIsAttachedToWindow;
    private boolean mIsSelectingDefaultActivity;
    private final int mListPopupMaxWidth;
    private ListPopupWindow mListPopupWindow;
    private final DataSetObserver mModelDataSetOberver;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final android.view.ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    ActionProvider mProvider;
}
