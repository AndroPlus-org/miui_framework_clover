// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.TypedValue;
import android.view.*;

// Referenced classes of package android.widget:
//            ActivityChooserModel, ActivityChooserView

public class ShareActionProvider extends ActionProvider
{
    public static interface OnShareTargetSelectedListener
    {

        public abstract boolean onShareTargetSelected(ShareActionProvider shareactionprovider, Intent intent);
    }

    private class ShareActivityChooserModelPolicy
        implements ActivityChooserModel.OnChooseActivityListener
    {

        public boolean onChooseActivity(ActivityChooserModel activitychoosermodel, Intent intent)
        {
            if(ShareActionProvider._2D_get1(ShareActionProvider.this) != null)
                ShareActionProvider._2D_get1(ShareActionProvider.this).onShareTargetSelected(ShareActionProvider.this, intent);
            return false;
        }

        final ShareActionProvider this$0;

        private ShareActivityChooserModelPolicy()
        {
            this$0 = ShareActionProvider.this;
            super();
        }

        ShareActivityChooserModelPolicy(ShareActivityChooserModelPolicy shareactivitychoosermodelpolicy)
        {
            this();
        }
    }

    private class ShareMenuItemOnMenuItemClickListener
        implements android.view.MenuItem.OnMenuItemClickListener
    {

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            menuitem = ActivityChooserModel.get(ShareActionProvider._2D_get0(ShareActionProvider.this), ShareActionProvider._2D_get2(ShareActionProvider.this)).chooseActivity(menuitem.getItemId());
            if(menuitem != null)
            {
                String s = menuitem.getAction();
                if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s))
                    menuitem.addFlags(0x8080000);
                ShareActionProvider._2D_get0(ShareActionProvider.this).startActivity(menuitem);
            }
            return true;
        }

        final ShareActionProvider this$0;

        private ShareMenuItemOnMenuItemClickListener()
        {
            this$0 = ShareActionProvider.this;
            super();
        }

        ShareMenuItemOnMenuItemClickListener(ShareMenuItemOnMenuItemClickListener sharemenuitemonmenuitemclicklistener)
        {
            this();
        }
    }


    static Context _2D_get0(ShareActionProvider shareactionprovider)
    {
        return shareactionprovider.mContext;
    }

    static OnShareTargetSelectedListener _2D_get1(ShareActionProvider shareactionprovider)
    {
        return shareactionprovider.mOnShareTargetSelectedListener;
    }

    static String _2D_get2(ShareActionProvider shareactionprovider)
    {
        return shareactionprovider.mShareHistoryFileName;
    }

    public ShareActionProvider(Context context)
    {
        super(context);
        mMaxShownActivityCount = 4;
        mShareHistoryFileName = "share_history.xml";
        mContext = context;
    }

    private void setActivityChooserPolicyIfNeeded()
    {
        if(mOnShareTargetSelectedListener == null)
            return;
        if(mOnChooseActivityListener == null)
            mOnChooseActivityListener = new ShareActivityChooserModelPolicy(null);
        ActivityChooserModel.get(mContext, mShareHistoryFileName).setOnChooseActivityListener(mOnChooseActivityListener);
    }

    public boolean hasSubMenu()
    {
        return true;
    }

    public View onCreateActionView()
    {
        ActivityChooserView activitychooserview = new ActivityChooserView(mContext);
        if(!activitychooserview.isInEditMode())
            activitychooserview.setActivityChooserModel(ActivityChooserModel.get(mContext, mShareHistoryFileName));
        TypedValue typedvalue = new TypedValue();
        mContext.getTheme().resolveAttribute(0x1010479, typedvalue, true);
        activitychooserview.setExpandActivityOverflowButtonDrawable(mContext.getDrawable(typedvalue.resourceId));
        activitychooserview.setProvider(this);
        activitychooserview.setDefaultActionButtonContentDescription(0x10405cd);
        activitychooserview.setExpandActivityOverflowButtonContentDescription(0x10405cc);
        return activitychooserview;
    }

    public void onPrepareSubMenu(SubMenu submenu)
    {
        submenu.clear();
        ActivityChooserModel activitychoosermodel = ActivityChooserModel.get(mContext, mShareHistoryFileName);
        android.content.pm.PackageManager packagemanager = mContext.getPackageManager();
        int i = activitychoosermodel.getActivityCount();
        int j = Math.min(i, mMaxShownActivityCount);
        for(int k = 0; k < j; k++)
        {
            ResolveInfo resolveinfo = activitychoosermodel.getActivity(k);
            submenu.add(0, k, k, resolveinfo.loadLabel(packagemanager)).setIcon(resolveinfo.loadIcon(packagemanager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
        }

        if(j < i)
        {
            SubMenu submenu1 = submenu.addSubMenu(0, j, j, mContext.getString(0x1040057));
            for(int l = 0; l < i; l++)
            {
                submenu = activitychoosermodel.getActivity(l);
                submenu1.add(0, l, l, submenu.loadLabel(packagemanager)).setIcon(submenu.loadIcon(packagemanager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
            }

        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener onsharetargetselectedlistener)
    {
        mOnShareTargetSelectedListener = onsharetargetselectedlistener;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareHistoryFileName(String s)
    {
        mShareHistoryFileName = s;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareIntent(Intent intent)
    {
        if(intent != null)
        {
            String s = intent.getAction();
            if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s))
                intent.addFlags(0x8080000);
        }
        ActivityChooserModel.get(mContext, mShareHistoryFileName).setIntent(intent);
    }

    private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    private final Context mContext;
    private int mMaxShownActivityCount;
    private ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;
    private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener = new ShareMenuItemOnMenuItemClickListener(null);
    private OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    private String mShareHistoryFileName;
}
