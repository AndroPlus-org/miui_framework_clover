// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.animation.*;
import android.app.Activity;
import android.content.*;
import android.content.pm.*;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.*;
import android.os.storage.StorageManager;
import android.service.chooser.*;
import android.text.TextUtils;
import android.util.*;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.ResolverDrawerLayout;
import java.io.File;
import java.util.*;

// Referenced classes of package com.android.internal.app:
//            ResolverActivity, IntentForwarderActivity, ChooserActivityInjector, ResolverListController

public class ChooserActivity extends ResolverActivity
{
    static class BaseChooserTargetComparator
        implements Comparator
    {

        public int compare(ChooserTarget choosertarget, ChooserTarget choosertarget1)
        {
            return (int)Math.signum(choosertarget1.getScore() - choosertarget.getScore());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ChooserTarget)obj, (ChooserTarget)obj1);
        }

        BaseChooserTargetComparator()
        {
        }
    }

    public class ChooserListAdapter extends ResolverActivity.ResolveListAdapter
    {

        private void insertServiceTarget(ChooserTargetInfo choosertargetinfo)
        {
            float f = choosertargetinfo.getModifiedScore();
            int i = 0;
            for(int j = mServiceTargets.size(); i < j; i++)
                if(f > ((ChooserTargetInfo)mServiceTargets.get(i)).getModifiedScore())
                {
                    mServiceTargets.add(i, choosertargetinfo);
                    return;
                }

            mServiceTargets.add(choosertargetinfo);
        }

        private void pruneServiceTargets()
        {
            for(int i = mServiceTargets.size() - 1; i >= 0; i--)
                if(!hasResolvedTarget(((ChooserTargetInfo)mServiceTargets.get(i)).getResolveInfo()))
                    mServiceTargets.remove(i);

        }

        public void addServiceResults(ResolverActivity.DisplayResolveInfo displayresolveinfo, List list)
        {
            float f = getScore(displayresolveinfo);
            Collections.sort(list, mBaseTargetComparator);
            float f1 = 0.0F;
            int i = 0;
            for(int j = Math.min(list.size(), 4); i < j;)
            {
                ChooserTarget choosertarget = (ChooserTarget)list.get(i);
                float f2 = choosertarget.getScore() * f * mLateFee;
                float f3 = f2;
                if(i > 0)
                {
                    f3 = f2;
                    if(f2 >= f1)
                        f3 = f1 * 0.95F;
                }
                insertServiceTarget(new ChooserTargetInfo(displayresolveinfo, choosertarget, f3));
                i++;
                f1 = f3;
            }

            mLateFee = mLateFee * 0.95F;
            notifyDataSetChanged();
        }

        public int getCallerTargetCount()
        {
            return mCallerTargets.size();
        }

        public int getCount()
        {
            return super.getCount() + getServiceTargetCount() + getCallerTargetCount();
        }

        public ResolverActivity.TargetInfo getItem(int i)
        {
            return targetInfoForPosition(i, true);
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        public int getPositionTargetType(int i)
        {
            int j = getCallerTargetCount();
            if(i < j)
                return 0;
            j += 0;
            int k = getServiceTargetCount();
            if(i - j < k)
                return 1;
            return i - (j + k) >= super.getCount() ? -1 : 2;
        }

        public float getScore(ResolverActivity.DisplayResolveInfo displayresolveinfo)
        {
            if(displayresolveinfo == null)
                return 900F;
            float f = super.getScore(displayresolveinfo);
            float f1 = f;
            if(displayresolveinfo.isPinned())
                f1 = f + 1000F;
            return f1;
        }

        public int getServiceTargetCount()
        {
            if(!mShowServiceTargets)
                return 0;
            else
                return Math.min(mServiceTargets.size(), 8);
        }

        public int getStandardTargetCount()
        {
            return super.getCount();
        }

        public int getUnfilteredCount()
        {
            return super.getUnfilteredCount() + getServiceTargetCount() + getCallerTargetCount();
        }

        public boolean isComponentPinned(ComponentName componentname)
        {
            return ChooserActivity._2D_get4(ChooserActivity.this).getBoolean(componentname.flattenToString(), false);
        }

        public View onCreateView(ViewGroup viewgroup)
        {
            return mInflater.inflate(0x10900d8, viewgroup, false);
        }

        public void onListRebuilt()
        {
            if(mServiceTargets != null)
                pruneServiceTargets();
            queryTargetServices(this);
        }

        public void setShowServiceTargets(boolean flag)
        {
            mShowServiceTargets = flag;
            notifyDataSetChanged();
        }

        public boolean shouldGetResolvedFilter()
        {
            return true;
        }

        public boolean showsExtendedInfo(ResolverActivity.TargetInfo targetinfo)
        {
            return false;
        }

        public ResolverActivity.TargetInfo targetInfoForPosition(int i, boolean flag)
        {
            int j = getCallerTargetCount();
            if(i < j)
                return (ResolverActivity.TargetInfo)mCallerTargets.get(i);
            int l = j + 0;
            if(l <= i && i < super.getCount() + l)
            {
                Object obj;
                if(flag)
                    obj = super.getItem(i - l);
                else
                    obj = getDisplayInfoAt(i - l);
                return ((ResolverActivity.TargetInfo) (obj));
            } else
            {
                int k = super.getCount();
                return (ResolverActivity.TargetInfo)mServiceTargets.get(i - (l + k));
            }
        }

        private static final int MAX_SERVICE_TARGETS = 8;
        private static final int MAX_TARGETS_PER_SERVICE = 4;
        public static final int TARGET_BAD = -1;
        public static final int TARGET_CALLER = 0;
        public static final int TARGET_SERVICE = 1;
        public static final int TARGET_STANDARD = 2;
        private final BaseChooserTargetComparator mBaseTargetComparator;
        private final List mCallerTargets;
        private float mLateFee;
        private final List mServiceTargets;
        private boolean mShowServiceTargets;
        final ChooserActivity this$0;

        public ChooserListAdapter(Context context, List list, Intent aintent[], List list1, int i, boolean flag, 
                ResolverListController resolverlistcontroller)
        {
            this$0 = ChooserActivity.this;
            super(ChooserActivity.this, context, list, null, list1, i, flag, resolverlistcontroller);
            mServiceTargets = new ArrayList();
            mCallerTargets = new ArrayList();
            mLateFee = 1.0F;
            mBaseTargetComparator = new BaseChooserTargetComparator();
            if(aintent == null) goto _L2; else goto _L1
_L1:
            PackageManager packagemanager;
            packagemanager = getPackageManager();
            i = 0;
_L6:
            if(i >= aintent.length) goto _L2; else goto _L3
_L3:
            Intent intent = aintent[i];
            if(intent != null) goto _L5; else goto _L4
_L4:
            i++;
              goto _L6
_L5:
            resolverlistcontroller = null;
            list1 = null;
            context = null;
            list = resolverlistcontroller;
            if(intent.getComponent() == null)
                break MISSING_BLOCK_LABEL_157;
            context = list1;
            list1 = packagemanager.getActivityInfo(intent.getComponent(), 0);
            context = list1;
            list = JVM INSTR new #76  <Class ResolveInfo>;
            context = list1;
            list.ResolveInfo();
            list.activityInfo = list1;
            context = list1;
_L7:
            list1 = list;
            list = context;
            if(context == null)
            {
                list1 = packagemanager.resolveActivity(intent, 0x10000);
                if(list1 != null)
                    list = ((ResolveInfo) (list1)).activityInfo;
                else
                    list = null;
            }
            if(list == null)
            {
                Log.w("ChooserActivity", (new StringBuilder()).append("No activity found for ").append(intent).toString());
            } else
            {
                context = (UserManager)getSystemService("user");
                if(intent instanceof LabeledIntent)
                {
                    list = (LabeledIntent)intent;
                    list1.resolvePackageName = list.getSourcePackage();
                    list1.labelRes = list.getLabelResource();
                    list1.nonLocalizedLabel = list.getNonLocalizedLabel();
                    list1.icon = list.getIconResource();
                    list1.iconResourceId = ((ResolveInfo) (list1)).icon;
                }
                if(context.isManagedProfile())
                {
                    list1.noResourceId = true;
                    list1.icon = 0;
                }
                mCallerTargets.add(new ResolverActivity.DisplayResolveInfo(ChooserActivity.this, intent, list1, list1.loadLabel(packagemanager), null, intent));
            }
              goto _L4
_L2:
            return;
            list;
            list = resolverlistcontroller;
              goto _L7
            context;
            context = list1;
              goto _L7
        }
    }

    public class ChooserListController extends ResolverListController
    {

        public float getScore(ResolverActivity.DisplayResolveInfo displayresolveinfo)
        {
            if(displayresolveinfo == null)
                return 900F;
            float f = super.getScore(displayresolveinfo);
            float f1 = f;
            if(displayresolveinfo.isPinned())
                f1 = f + 1000F;
            return f1;
        }

        boolean isComponentFiltered(ComponentName componentname)
        {
            if(ChooserActivity._2D_get3(ChooserActivity.this) == null)
                return false;
            ComponentName acomponentname[] = ChooserActivity._2D_get3(ChooserActivity.this);
            int i = acomponentname.length;
            for(int j = 0; j < i; j++)
                if(componentname.equals(acomponentname[j]))
                    return true;

            return false;
        }

        boolean isComponentPinned(ComponentName componentname)
        {
            return ChooserActivity._2D_get4(ChooserActivity.this).getBoolean(componentname.flattenToString(), false);
        }

        final ChooserActivity this$0;

        public ChooserListController(Context context, PackageManager packagemanager, Intent intent, String s, int i)
        {
            this$0 = ChooserActivity.this;
            super(context, packagemanager, intent, s, i);
        }
    }

    class ChooserRowAdapter extends BaseAdapter
    {

        static ChooserListAdapter _2D_get0(ChooserRowAdapter chooserrowadapter)
        {
            return chooserrowadapter.mChooserListAdapter;
        }

        static Interpolator _2D_get1(ChooserRowAdapter chooserrowadapter)
        {
            return chooserrowadapter.mInterpolator;
        }

        static RowScale[] _2D_get2(ChooserRowAdapter chooserrowadapter)
        {
            return chooserrowadapter.mServiceTargetScale;
        }

        static RowScale[] _2D_set0(ChooserRowAdapter chooserrowadapter, RowScale arowscale[])
        {
            chooserrowadapter.mServiceTargetScale = arowscale;
            return arowscale;
        }

        static float _2D_wrap0(ChooserRowAdapter chooserrowadapter, int i)
        {
            return chooserrowadapter.getRowScale(i);
        }

        private float getRowScale(int i)
        {
            int j = getCallerTargetRowCount();
            int k = getServiceTargetRowCount();
            if(i >= j && i < j + k)
                return mServiceTargetScale[i - j].get();
            else
                return 1.0F;
        }

        void bindViewHolder(int i, RowViewHolder rowviewholder)
        {
            int j = getFirstRowPosition(i);
            int k = mChooserListAdapter.getPositionTargetType(j);
            int l;
            for(l = (j + 4) - 1; mChooserListAdapter.getPositionTargetType(l) != k && l >= j; l--);
            if(k == 1)
                rowviewholder.row.setBackgroundColor(getColor(0x1060058));
            else
                rowviewholder.row.setBackgroundColor(0);
            k = rowviewholder.row.getLayoutParams().height;
            rowviewholder.row.getLayoutParams().height = Math.max(1, (int)((float)rowviewholder.measuredRowHeight * getRowScale(i)));
            if(rowviewholder.row.getLayoutParams().height != k)
                rowviewholder.row.requestLayout();
            i = 0;
            while(i < 4) 
            {
                View view = rowviewholder.cells[i];
                if(j + i <= l)
                {
                    view.setVisibility(0);
                    rowviewholder.itemIndices[i] = j + i;
                    mChooserListAdapter.bindView(rowviewholder.itemIndices[i], view);
                } else
                {
                    view.setVisibility(8);
                }
                i++;
            }
        }

        RowViewHolder createViewHolder(ViewGroup viewgroup)
        {
            viewgroup = (ViewGroup)mLayoutInflater.inflate(0x1090048, viewgroup, false);
            final RowViewHolder holder = new RowViewHolder(viewgroup, 4);
            int i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            int j = 0;
            while(j < 4) 
            {
                View view = mChooserListAdapter.createView(viewgroup);
                view.setOnClickListener(j. new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        startSelected(holder.itemIndices[column], false, true);
                    }

                    final ChooserRowAdapter this$1;
                    final int val$column;
                    final RowViewHolder val$holder;

            
            {
                this$1 = final_chooserrowadapter;
                holder = rowviewholder;
                column = I.this;
                super();
            }
                }
);
                view.setOnLongClickListener(j. new android.view.View.OnLongClickListener() {

                    public boolean onLongClick(View view)
                    {
                        showTargetDetails(ChooserRowAdapter._2D_get0(ChooserRowAdapter.this).resolveInfoForPosition(holder.itemIndices[column], true));
                        return true;
                    }

                    final ChooserRowAdapter this$1;
                    final int val$column;
                    final RowViewHolder val$holder;

            
            {
                this$1 = final_chooserrowadapter;
                holder = rowviewholder;
                column = I.this;
                super();
            }
                }
);
                viewgroup.addView(view);
                holder.cells[j] = view;
                android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
                view.measure(i, i);
                if(layoutparams == null)
                    viewgroup.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, view.getMeasuredHeight()));
                else
                    layoutparams.height = view.getMeasuredHeight();
                j++;
            }
            holder.measure();
            android.view.ViewGroup.LayoutParams layoutparams1 = viewgroup.getLayoutParams();
            if(layoutparams1 == null)
                viewgroup.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, holder.measuredRowHeight));
            else
                layoutparams1.height = holder.measuredRowHeight;
            viewgroup.setTag(holder);
            return holder;
        }

        public int getCallerTargetRowCount()
        {
            return (int)Math.ceil((float)mChooserListAdapter.getCallerTargetCount() / 4F);
        }

        public int getCount()
        {
            return (int)((double)(getCallerTargetRowCount() + getServiceTargetRowCount()) + Math.ceil((float)mChooserListAdapter.getStandardTargetCount() / 4F));
        }

        int getFirstRowPosition(int i)
        {
            int j = mChooserListAdapter.getCallerTargetCount();
            int k = (int)Math.ceil((float)j / 4F);
            if(i < k)
                return i * 4;
            int l = mChooserListAdapter.getServiceTargetCount();
            int i1 = (int)Math.ceil((float)l / 4F);
            if(i < k + i1)
                return (i - k) * 4 + j;
            else
                return j + l + (i - k - i1) * 4;
        }

        public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public int getServiceTargetRowCount()
        {
            return (int)Math.ceil((float)mChooserListAdapter.getServiceTargetCount() / 4F);
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
                view = createViewHolder(viewgroup);
            else
                view = (RowViewHolder)view.getTag();
            bindViewHolder(i, view);
            return ((RowViewHolder) (view)).row;
        }

        public void onAnimationEnd()
        {
            mAnimationCount = mAnimationCount - 1;
            if(mAnimationCount == 0)
                mResolverDrawerLayout.setDismissLocked(false);
        }

        public void onAnimationStart()
        {
            boolean flag;
            if(mAnimationCount == 0)
                flag = true;
            else
                flag = false;
            mAnimationCount = mAnimationCount + 1;
            if(flag)
                mResolverDrawerLayout.setDismissLocked(true);
        }

        private int mAnimationCount;
        private ChooserListAdapter mChooserListAdapter;
        private final int mColumnCount = 4;
        private final Interpolator mInterpolator;
        private final LayoutInflater mLayoutInflater;
        private RowScale mServiceTargetScale[];
        final ChooserActivity this$0;

        public ChooserRowAdapter(ChooserListAdapter chooserlistadapter)
        {
            this$0 = ChooserActivity.this;
            super();
            mAnimationCount = 0;
            mChooserListAdapter = chooserlistadapter;
            mLayoutInflater = LayoutInflater.from(ChooserActivity.this);
            mInterpolator = AnimationUtils.loadInterpolator(ChooserActivity.this, 0x10c0005);
            chooserlistadapter.registerDataSetObserver(new _cls1());
        }
    }

    final class ChooserTargetInfo
        implements ResolverActivity.TargetInfo
    {

        private Intent getBaseIntentToSend()
        {
            Intent intent = getResolvedIntent();
            if(intent == null)
            {
                Log.e("ChooserActivity", "ChooserTargetInfo: no base intent available to send");
            } else
            {
                intent = new Intent(intent);
                if(mFillInIntent != null)
                    intent.fillIn(mFillInIntent, mFillInFlags);
                intent.fillIn(ChooserActivity._2D_get5(ChooserActivity.this), 0);
            }
            return intent;
        }

        public ResolverActivity.TargetInfo cloneFilledIn(Intent intent, int i)
        {
            return new ChooserTargetInfo(this, intent, i);
        }

        public List getAllSourceIntents()
        {
            ArrayList arraylist = new ArrayList();
            if(mSourceInfo != null)
                arraylist.add((Intent)mSourceInfo.getAllSourceIntents().get(0));
            return arraylist;
        }

        public CharSequence getBadgeContentDescription()
        {
            return mBadgeContentDescription;
        }

        public Drawable getBadgeIcon()
        {
            return mBadgeIcon;
        }

        public Drawable getDisplayIcon()
        {
            return mDisplayIcon;
        }

        public CharSequence getDisplayLabel()
        {
            return mChooserTarget.getTitle();
        }

        public CharSequence getExtendedInfo()
        {
            return null;
        }

        public float getModifiedScore()
        {
            return mModifiedScore;
        }

        public ResolveInfo getResolveInfo()
        {
            ResolveInfo resolveinfo;
            if(mSourceInfo != null)
                resolveinfo = mSourceInfo.getResolveInfo();
            else
                resolveinfo = mBackupResolveInfo;
            return resolveinfo;
        }

        public ComponentName getResolvedComponentName()
        {
            if(mSourceInfo != null)
                return mSourceInfo.getResolvedComponentName();
            if(mBackupResolveInfo != null)
                return new ComponentName(mBackupResolveInfo.activityInfo.packageName, mBackupResolveInfo.activityInfo.name);
            else
                return null;
        }

        public Intent getResolvedIntent()
        {
            if(mSourceInfo != null)
            {
                return mSourceInfo.getResolvedIntent();
            } else
            {
                Intent intent = new Intent(getTargetIntent());
                intent.setComponent(mChooserTarget.getComponentName());
                intent.putExtras(mChooserTarget.getIntentExtras());
                return intent;
            }
        }

        public boolean isPinned()
        {
            boolean flag;
            if(mSourceInfo != null)
                flag = mSourceInfo.isPinned();
            else
                flag = false;
            return flag;
        }

        public boolean start(Activity activity, Bundle bundle)
        {
            throw new RuntimeException("ChooserTargets should be started as caller.");
        }

        public boolean startAsCaller(Activity activity, Bundle bundle, int i)
        {
            Intent intent = getBaseIntentToSend();
            if(intent == null)
                return false;
            intent.setComponent(mChooserTarget.getComponentName());
            intent.putExtras(mChooserTarget.getIntentExtras());
            boolean flag;
            if(mSourceInfo != null)
                flag = mSourceInfo.getResolvedComponentName().getPackageName().equals(mChooserTarget.getComponentName().getPackageName());
            else
                flag = false;
            activity.startActivityAsCaller(intent, bundle, flag, i);
            return true;
        }

        public boolean startAsUser(Activity activity, Bundle bundle, UserHandle userhandle)
        {
            throw new RuntimeException("ChooserTargets should be started as caller.");
        }

        private final ResolveInfo mBackupResolveInfo;
        private CharSequence mBadgeContentDescription;
        private Drawable mBadgeIcon;
        private final ChooserTarget mChooserTarget;
        private Drawable mDisplayIcon;
        private final int mFillInFlags;
        private final Intent mFillInIntent;
        private final float mModifiedScore;
        private final ResolverActivity.DisplayResolveInfo mSourceInfo;
        final ChooserActivity this$0;

        private ChooserTargetInfo(ChooserTargetInfo choosertargetinfo, Intent intent, int i)
        {
            this$0 = ChooserActivity.this;
            super();
            mBadgeIcon = null;
            mSourceInfo = choosertargetinfo.mSourceInfo;
            mBackupResolveInfo = choosertargetinfo.mBackupResolveInfo;
            mChooserTarget = choosertargetinfo.mChooserTarget;
            mBadgeIcon = choosertargetinfo.mBadgeIcon;
            mBadgeContentDescription = choosertargetinfo.mBadgeContentDescription;
            mDisplayIcon = choosertargetinfo.mDisplayIcon;
            mFillInIntent = intent;
            mFillInFlags = i;
            mModifiedScore = choosertargetinfo.mModifiedScore;
        }

        public ChooserTargetInfo(ResolverActivity.DisplayResolveInfo displayresolveinfo, ChooserTarget choosertarget, float f)
        {
            this$0 = ChooserActivity.this;
            super();
            mBadgeIcon = null;
            mSourceInfo = displayresolveinfo;
            mChooserTarget = choosertarget;
            mModifiedScore = f;
            if(displayresolveinfo != null)
            {
                Object obj = displayresolveinfo.getResolveInfo();
                if(obj != null)
                {
                    obj = ((ResolveInfo) (obj)).activityInfo;
                    if(obj != null && ((ActivityInfo) (obj)).applicationInfo != null)
                    {
                        PackageManager packagemanager = getPackageManager();
                        mBadgeIcon = packagemanager.getApplicationIcon(((ActivityInfo) (obj)).applicationInfo);
                        mBadgeContentDescription = packagemanager.getApplicationLabel(((ActivityInfo) (obj)).applicationInfo);
                    }
                }
            }
            choosertarget = choosertarget.getIcon();
            if(choosertarget != null)
                choosertarget = choosertarget.loadDrawable(ChooserActivity.this);
            else
                choosertarget = null;
            mDisplayIcon = choosertarget;
            if(displayresolveinfo != null)
                mBackupResolveInfo = null;
            else
                mBackupResolveInfo = getPackageManager().resolveActivity(getResolvedIntent(), 0);
            mFillInIntent = null;
            mFillInFlags = 0;
        }
    }

    static class ChooserTargetServiceConnection
        implements ServiceConnection
    {

        static ChooserActivity _2D_get0(ChooserTargetServiceConnection choosertargetserviceconnection)
        {
            return choosertargetserviceconnection.mChooserActivity;
        }

        static ComponentName _2D_get1(ChooserTargetServiceConnection choosertargetserviceconnection)
        {
            return choosertargetserviceconnection.mConnectedComponent;
        }

        static Object _2D_get2(ChooserTargetServiceConnection choosertargetserviceconnection)
        {
            return choosertargetserviceconnection.mLock;
        }

        static ResolverActivity.DisplayResolveInfo _2D_get3(ChooserTargetServiceConnection choosertargetserviceconnection)
        {
            return choosertargetserviceconnection.mOriginalTarget;
        }

        public void destroy()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mChooserActivity = null;
            mOriginalTarget = null;
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(mChooserActivity != null)
                break MISSING_BLOCK_LABEL_25;
            Log.e("ChooserActivity", "destroyed ChooserTargetServiceConnection got onServiceConnected");
            obj;
            JVM INSTR monitorexit ;
            return;
            ibinder = android.service.chooser.IChooserTargetService.Stub.asInterface(ibinder);
            ibinder.getChooserTargets(mOriginalTarget.getResolvedComponentName(), mOriginalTarget.getResolveInfo().filter, mChooserTargetResult);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            RemoteException remoteexception;
            remoteexception;
            ibinder = JVM INSTR new #93  <Class StringBuilder>;
            ibinder.StringBuilder();
            Log.e("ChooserActivity", ibinder.append("Querying ChooserTargetService ").append(componentname).append(" failed.").toString(), remoteexception);
            mChooserActivity.unbindService(this);
            destroy();
            ChooserActivity._2D_get6(mChooserActivity).remove(this);
              goto _L1
            componentname;
            throw componentname;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            componentname = ((ComponentName) (mLock));
            componentname;
            JVM INSTR monitorenter ;
            if(mChooserActivity != null)
                break MISSING_BLOCK_LABEL_25;
            Log.e("ChooserActivity", "destroyed ChooserTargetServiceConnection got onServiceDisconnected");
            componentname;
            JVM INSTR monitorexit ;
            return;
            mChooserActivity.unbindService(this);
            destroy();
            ChooserActivity._2D_get6(mChooserActivity).remove(this);
            if(ChooserActivity._2D_get6(mChooserActivity).isEmpty())
            {
                ChooserActivity._2D_get0(mChooserActivity).removeMessages(2);
                mChooserActivity.sendVoiceChoicesIfNeeded();
            }
            mConnectedComponent = null;
            componentname;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("ChooserTargetServiceConnection{service=").append(mConnectedComponent).append(", activity=");
            String s;
            if(mOriginalTarget != null)
                s = mOriginalTarget.getResolveInfo().activityInfo.toString();
            else
                s = "<connection destroyed>";
            return stringbuilder.append(s).append("}").toString();
        }

        private ChooserActivity mChooserActivity;
        private final IChooserTargetResult mChooserTargetResult = new _cls1();
        private ComponentName mConnectedComponent;
        private final Object mLock = new Object();
        private ResolverActivity.DisplayResolveInfo mOriginalTarget;

        public ChooserTargetServiceConnection(ChooserActivity chooseractivity, ResolverActivity.DisplayResolveInfo displayresolveinfo)
        {
            mChooserActivity = chooseractivity;
            mOriginalTarget = displayresolveinfo;
        }
    }

    class OffsetDataSetObserver extends DataSetObserver
    {

        public void onChanged()
        {
            if(mResolverDrawerLayout == null)
                return;
            int i = ChooserActivity._2D_get2(ChooserActivity.this).getServiceTargetRowCount();
            int j = 0;
            int k = 0;
            while(k < i) 
            {
                int l = ChooserActivity._2D_get2(ChooserActivity.this).getCallerTargetRowCount() + k;
                int i1 = ChooserActivity._2D_get2(ChooserActivity.this).getItemViewType(l);
                if(i1 != mCachedViewType)
                    mCachedView = null;
                View view = ChooserActivity._2D_get2(ChooserActivity.this).getView(l, mCachedView, mListView);
                j += (int)((float)((RowViewHolder)view.getTag()).measuredRowHeight * ChooserRowAdapter._2D_wrap0(ChooserActivity._2D_get2(ChooserActivity.this), l));
                if(i1 >= 0)
                {
                    mCachedViewType = i1;
                    mCachedView = view;
                } else
                {
                    mCachedViewType = -1;
                }
                k++;
            }
            mResolverDrawerLayout.setCollapsibleHeightReserved(j);
        }

        private View mCachedView;
        private int mCachedViewType;
        private final AbsListView mListView;
        final ChooserActivity this$0;

        public OffsetDataSetObserver(AbsListView abslistview)
        {
            this$0 = ChooserActivity.this;
            super();
            mCachedViewType = -1;
            mListView = abslistview;
        }
    }

    static class RefinementResultReceiver extends ResultReceiver
    {

        public void destroy()
        {
            mChooserActivity = null;
            mSelectedTarget = null;
        }

        protected void onReceiveResult(int i, Bundle bundle)
        {
            if(mChooserActivity == null)
            {
                Log.e("ChooserActivity", "Destroyed RefinementResultReceiver received a result");
                return;
            }
            if(bundle == null)
            {
                Log.e("ChooserActivity", "RefinementResultReceiver received null resultData");
                return;
            }
            i;
            JVM INSTR tableswitch -1 0: default 52
        //                       -1 93
        //                       0 83;
               goto _L1 _L2 _L3
_L1:
            Log.w("ChooserActivity", (new StringBuilder()).append("Unknown result code ").append(i).append(" sent to RefinementResultReceiver").toString());
_L5:
            return;
_L3:
            mChooserActivity.onRefinementCanceled();
            continue; /* Loop/switch isn't completed */
_L2:
            bundle = bundle.getParcelable("android.intent.extra.INTENT");
            if(bundle instanceof Intent)
                mChooserActivity.onRefinementResult(mSelectedTarget, (Intent)bundle);
            else
                Log.e("ChooserActivity", "RefinementResultReceiver received RESULT_OK but no Intent in resultData with key Intent.EXTRA_INTENT");
            if(true) goto _L5; else goto _L4
_L4:
        }

        private ChooserActivity mChooserActivity;
        private ResolverActivity.TargetInfo mSelectedTarget;

        public RefinementResultReceiver(ChooserActivity chooseractivity, ResolverActivity.TargetInfo targetinfo, Handler handler)
        {
            super(handler);
            mChooserActivity = chooseractivity;
            mSelectedTarget = targetinfo;
        }
    }

    static class RowScale
    {

        public void cancelAnimation()
        {
            if(mAnimator != null)
                mAnimator.cancel();
        }

        public float get()
        {
            return mScale;
        }

        public RowScale setInterpolator(Interpolator interpolator)
        {
            if(mAnimator != null)
                mAnimator.setInterpolator(interpolator);
            return this;
        }

        public void startAnimation()
        {
            if(mAnimator != null)
                mAnimator.start();
        }

        private static final int DURATION = 400;
        public static final FloatProperty PROPERTY = new FloatProperty("scale") {

            public Float get(RowScale rowscale)
            {
                return Float.valueOf(rowscale.mScale);
            }

            public volatile Object get(Object obj)
            {
                return get((RowScale)obj);
            }

            public void setValue(RowScale rowscale, float f)
            {
                rowscale.mScale = f;
                rowscale.mAdapter.notifyDataSetChanged();
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((RowScale)obj, f);
            }

        }
;
        ChooserRowAdapter mAdapter;
        private final ObjectAnimator mAnimator;
        float mScale;


        public RowScale(ChooserRowAdapter chooserrowadapter, float f, float f1)
        {
            mAdapter = chooserrowadapter;
            mScale = f;
            if(f == f1)
            {
                mAnimator = null;
                return;
            } else
            {
                mAnimator = ObjectAnimator.ofFloat(this, PROPERTY, new float[] {
                    f, f1
                }).setDuration(400L);
                mAnimator.addListener(new _cls2());
                return;
            }
        }
    }

    static class RowViewHolder
    {

        public void measure()
        {
            int i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            row.measure(i, i);
            measuredRowHeight = row.getMeasuredHeight();
        }

        final View cells[];
        int itemIndices[];
        int measuredRowHeight;
        final ViewGroup row;

        public RowViewHolder(ViewGroup viewgroup, int i)
        {
            row = viewgroup;
            cells = new View[i];
            itemIndices = new int[i];
        }
    }

    static class ServiceResultInfo
    {

        public final ChooserTargetServiceConnection connection;
        public final ResolverActivity.DisplayResolveInfo originalTarget;
        public final List resultTargets;

        public ServiceResultInfo(ResolverActivity.DisplayResolveInfo displayresolveinfo, List list, ChooserTargetServiceConnection choosertargetserviceconnection)
        {
            originalTarget = displayresolveinfo;
            resultTargets = list;
            connection = choosertargetserviceconnection;
        }
    }


    static Handler _2D_get0(ChooserActivity chooseractivity)
    {
        return chooseractivity.mChooserHandler;
    }

    static ChooserListAdapter _2D_get1(ChooserActivity chooseractivity)
    {
        return chooseractivity.mChooserListAdapter;
    }

    static ChooserRowAdapter _2D_get2(ChooserActivity chooseractivity)
    {
        return chooseractivity.mChooserRowAdapter;
    }

    static ComponentName[] _2D_get3(ChooserActivity chooseractivity)
    {
        return chooseractivity.mFilteredComponentNames;
    }

    static SharedPreferences _2D_get4(ChooserActivity chooseractivity)
    {
        return chooseractivity.mPinnedSharedPrefs;
    }

    static Intent _2D_get5(ChooserActivity chooseractivity)
    {
        return chooseractivity.mReferrerFillInIntent;
    }

    static List _2D_get6(ChooserActivity chooseractivity)
    {
        return chooseractivity.mServiceConnections;
    }

    public ChooserActivity()
    {
    }

    private String convertServiceName(String s, String s1)
    {
        if(TextUtils.isEmpty(s1))
            return null;
        if(s1.startsWith("."))
            s = (new StringBuilder()).append(s).append(s1).toString();
        else
        if(s1.indexOf('.') >= 0)
            s = s1;
        else
            s = null;
        return s;
    }

    static SharedPreferences getPinnedSharedPrefs(Context context)
    {
        return context.getSharedPreferences(new File(new File(Environment.getDataUserCePackageDirectory(StorageManager.UUID_PRIVATE_INTERNAL, context.getUserId(), context.getPackageName()), "shared_prefs"), "chooser_pin_settings.xml"), 0);
    }

    private void modifyTargetIntent(Intent intent)
    {
        String s = intent.getAction();
        if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s))
            intent.addFlags(0x8080000);
    }

    boolean checkTargetSourceIntent(ResolverActivity.TargetInfo targetinfo, Intent intent)
    {
        targetinfo = targetinfo.getAllSourceIntents();
        int i = 0;
        for(int j = targetinfo.size(); i < j; i++)
            if(((Intent)targetinfo.get(i)).filterEquals(intent))
                return true;

        return false;
    }

    public ResolverActivity.ResolveListAdapter createAdapter(Context context, List list, Intent aintent[], List list1, int i, boolean flag)
    {
        return new ChooserListAdapter(context, list, aintent, list1, i, flag, createListController());
    }

    protected ResolverListController createListController()
    {
        return new ChooserListController(this, mPm, getTargetIntent(), getReferrerPackageName(), mLaunchedFromUid);
    }

    void filterServiceTargets(String s, List list)
    {
        PackageManager packagemanager;
        int i;
        if(list == null)
            return;
        packagemanager = getPackageManager();
        i = list.size() - 1;
_L2:
        ChooserTarget choosertarget;
        Object obj;
        if(i < 0)
            break MISSING_BLOCK_LABEL_173;
        choosertarget = (ChooserTarget)list.get(i);
        obj = choosertarget.getComponentName();
        if(s == null || !s.equals(((ComponentName) (obj)).getPackageName()))
            break; /* Loop/switch isn't completed */
_L6:
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        obj = packagemanager.getActivityInfo(((ComponentName) (obj)), 0);
        if(!((ActivityInfo) (obj)).exported) goto _L4; else goto _L3
_L3:
        obj = ((ActivityInfo) (obj)).permission;
        if(obj == null) goto _L5; else goto _L4
_L4:
        boolean flag = true;
_L7:
        if(flag)
            list.remove(i);
          goto _L6
_L5:
        flag = false;
          goto _L7
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        Log.e("ChooserActivity", (new StringBuilder()).append("Target ").append(choosertarget).append(" returned by ").append(s).append(" component not found").toString());
        flag = true;
          goto _L7
          goto _L6
    }

    public int getLayoutResource()
    {
        return 0x1090047;
    }

    public Intent getReplacementIntent(ActivityInfo activityinfo, Intent intent)
    {
label0:
        {
            Intent intent1 = intent;
            Intent intent2 = intent1;
            if(mReplacementExtras != null)
            {
                Bundle bundle = mReplacementExtras.getBundle(activityinfo.packageName);
                intent2 = intent1;
                if(bundle != null)
                {
                    intent2 = new Intent(intent);
                    intent2.putExtras(bundle);
                }
            }
            if(!activityinfo.name.equals(IntentForwarderActivity.FORWARD_INTENT_TO_PARENT))
            {
                intent = intent2;
                if(!activityinfo.name.equals(IntentForwarderActivity.FORWARD_INTENT_TO_MANAGED_PROFILE))
                    break label0;
            }
            intent = Intent.createChooser(intent2, getIntent().getCharSequenceExtra("android.intent.extra.TITLE"));
            intent.putExtra("android.intent.extra.AUTO_LAUNCH_SINGLE_CHOICE", false);
        }
        return intent;
    }

    public void onActivityStarted(ResolverActivity.TargetInfo targetinfo)
    {
        if(mChosenComponentSender == null)
            break MISSING_BLOCK_LABEL_45;
        targetinfo = targetinfo.getResolvedComponentName();
        if(targetinfo == null)
            break MISSING_BLOCK_LABEL_45;
        targetinfo = (new Intent()).putExtra("android.intent.extra.CHOSEN_COMPONENT", targetinfo);
        mChosenComponentSender.sendIntent(this, -1, targetinfo, null, null);
_L1:
        return;
        targetinfo;
        Slog.e("ChooserActivity", (new StringBuilder()).append("Unable to launch supplied IntentSender to report the chosen component: ").append(targetinfo).toString());
          goto _L1
    }

    protected void onCreate(Bundle bundle)
    {
        long l;
        Intent intent;
        Object obj;
        Intent aintent[];
        int i1;
        CharSequence charsequence;
        Parcelable aparcelable2[];
        l = System.currentTimeMillis();
        mIsSuccessfullySelected = false;
        intent = getIntent();
        obj = intent.getParcelableExtra("android.intent.extra.INTENT");
        if(!(obj instanceof Intent))
        {
            Log.w("ChooserActivity", (new StringBuilder()).append("Target is not an intent: ").append(obj).toString());
            finish();
            super.onCreate(null);
            return;
        }
        Intent intent1 = (Intent)obj;
        if(intent1 != null)
            modifyTargetIntent(intent1);
        Parcelable aparcelable[] = intent.getParcelableArrayExtra("android.intent.extra.ALTERNATE_INTENTS");
        obj = intent1;
        if(aparcelable != null)
        {
            boolean flag;
            int k;
            Intent aintent2[];
            if(intent1 == null)
                flag = true;
            else
                flag = false;
            if(flag)
                k = aparcelable.length - 1;
            else
                k = aparcelable.length;
            aintent2 = new Intent[k];
            k = 0;
            obj = intent1;
            while(k < aparcelable.length) 
            {
                if(!(aparcelable[k] instanceof Intent))
                {
                    Log.w("ChooserActivity", (new StringBuilder()).append("EXTRA_ALTERNATE_INTENTS array entry #").append(k).append(" is not an Intent: ").append(aparcelable[k]).toString());
                    finish();
                    super.onCreate(null);
                    return;
                }
                Intent intent2 = (Intent)aparcelable[k];
                if(k == 0 && obj == null)
                {
                    obj = intent2;
                    modifyTargetIntent(intent2);
                } else
                {
                    int j1;
                    if(flag)
                        j1 = k - 1;
                    else
                        j1 = k;
                    aintent2[j1] = intent2;
                    modifyTargetIntent(intent2);
                }
                k++;
            }
            setAdditionalTargets(aintent2);
        }
        mReplacementExtras = intent.getBundleExtra("android.intent.extra.REPLACEMENT_EXTRAS");
        charsequence = intent.getCharSequenceExtra("android.intent.extra.TITLE");
        i1 = 0;
        if(charsequence == null)
            i1 = 0x104010b;
        Parcelable aparcelable1[] = intent.getParcelableArrayExtra("android.intent.extra.INITIAL_INTENTS");
        aintent = null;
        if(aparcelable1 != null)
        {
            Intent aintent1[] = new Intent[aparcelable1.length];
            int i = 0;
            do
            {
                aintent = aintent1;
                if(i >= aparcelable1.length)
                    break;
                if(!(aparcelable1[i] instanceof Intent))
                {
                    Log.w("ChooserActivity", (new StringBuilder()).append("Initial intent #").append(i).append(" not an Intent: ").append(aparcelable1[i]).toString());
                    finish();
                    super.onCreate(null);
                    return;
                }
                aintent = (Intent)aparcelable1[i];
                modifyTargetIntent(aintent);
                aintent1[i] = aintent;
                i++;
            } while(true);
        }
        mReferrerFillInIntent = (new Intent()).putExtra("android.intent.extra.REFERRER", getReferrer());
        mChosenComponentSender = (IntentSender)intent.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER");
        mRefinementIntentSender = (IntentSender)intent.getParcelableExtra("android.intent.extra.CHOOSER_REFINEMENT_INTENT_SENDER");
        setSafeForwardingMode(true);
        aparcelable2 = intent.getParcelableArrayExtra("android.intent.extra.EXCLUDE_COMPONENTS");
        if(aparcelable2 == null) goto _L2; else goto _L1
_L1:
        int j;
        Object aobj1[];
        aobj1 = new ComponentName[aparcelable2.length];
        j = 0;
_L9:
        ComponentName acomponentname[] = ((ComponentName []) (aobj1));
        if(j >= aparcelable2.length) goto _L4; else goto _L3
_L3:
        if(aparcelable2[j] instanceof ComponentName) goto _L6; else goto _L5
_L5:
        Log.w("ChooserActivity", (new StringBuilder()).append("Filtered component #").append(j).append(" not a ComponentName: ").append(aparcelable2[j]).toString());
        acomponentname = null;
_L4:
        mFilteredComponentNames = acomponentname;
_L2:
        aparcelable2 = intent.getParcelableArrayExtra("android.intent.extra.CHOOSER_TARGETS");
        if(aparcelable2 == null) goto _L8; else goto _L7
_L7:
        aobj1 = new ChooserTarget[aparcelable2.length];
        j = 0;
_L10:
        Object aobj[] = aobj1;
        if(j < aparcelable2.length)
        {
            if(aparcelable2[j] instanceof ChooserTarget)
                break MISSING_BLOCK_LABEL_805;
            Log.w("ChooserActivity", (new StringBuilder()).append("Chooser target #").append(j).append(" not a ChooserTarget: ").append(aparcelable2[j]).toString());
            aobj = null;
        }
        mCallerChooserTargets = ((ChooserTarget []) (aobj));
_L8:
        mPinnedSharedPrefs = getPinnedSharedPrefs(this);
        setRetainInOnStop(intent.getBooleanExtra("com.android.internal.app.ChooserActivity.EXTRA_PRIVATE_RETAIN_IN_ON_STOP", false));
        super.onCreate(bundle, ((Intent) (obj)), charsequence, i1, aintent, null, false);
        MetricsLogger.action(this, 214);
        mChooserShownTime = System.currentTimeMillis();
        MetricsLogger.histogram(null, "system_cost_for_smart_sharing", (int)(mChooserShownTime - l));
        return;
_L6:
        aobj1[j] = (ComponentName)aparcelable2[j];
        j++;
          goto _L9
        aobj1[j] = (ChooserTarget)aparcelable2[j];
        j++;
          goto _L10
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(mRefinementResultReceiver != null)
        {
            mRefinementResultReceiver.destroy();
            mRefinementResultReceiver = null;
        }
        unbindRemainingServices();
        mChooserHandler.removeMessages(1);
    }

    public void onPrepareAdapterView(AbsListView abslistview, ResolverActivity.ResolveListAdapter resolvelistadapter)
    {
        mChooserListAdapter = (ChooserListAdapter)resolvelistadapter;
    }

    void onRefinementCanceled()
    {
        if(mRefinementResultReceiver != null)
        {
            mRefinementResultReceiver.destroy();
            mRefinementResultReceiver = null;
        }
        finish();
    }

    void onRefinementResult(ResolverActivity.TargetInfo targetinfo, Intent intent)
    {
        if(mRefinementResultReceiver != null)
        {
            mRefinementResultReceiver.destroy();
            mRefinementResultReceiver = null;
        }
        if(targetinfo != null) goto _L2; else goto _L1
_L1:
        Log.e("ChooserActivity", "Refinement result intent did not match any known targets; canceling");
_L4:
        onRefinementCanceled();
        return;
_L2:
        if(checkTargetSourceIntent(targetinfo, intent))
            break; /* Loop/switch isn't completed */
        Log.e("ChooserActivity", (new StringBuilder()).append("onRefinementResult: Selected target ").append(targetinfo).append(" cannot match refined source intent ").append(intent).toString());
        if(true) goto _L4; else goto _L3
_L3:
        targetinfo = targetinfo.cloneFilledIn(intent, 0);
        if(super.onTargetSelected(targetinfo, false))
        {
            updateModelAndChooserCounts(targetinfo);
            finish();
            return;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public void onSetupVoiceInteraction()
    {
    }

    protected boolean onTargetSelected(ResolverActivity.TargetInfo targetinfo, boolean flag)
    {
        Intent intent;
        if(mRefinementIntentSender == null)
            break MISSING_BLOCK_LABEL_198;
        intent = new Intent();
        List list = targetinfo.getAllSourceIntents();
        if(list.isEmpty())
            break MISSING_BLOCK_LABEL_198;
        intent.putExtra("android.intent.extra.INTENT", (Parcelable)list.get(0));
        if(list.size() > 1)
        {
            Intent aintent[] = new Intent[list.size() - 1];
            int i = 1;
            for(int j = list.size(); i < j; i++)
                aintent[i - 1] = (Intent)list.get(i);

            intent.putExtra("android.intent.extra.ALTERNATE_INTENTS", aintent);
        }
        if(mRefinementResultReceiver != null)
            mRefinementResultReceiver.destroy();
        mRefinementResultReceiver = new RefinementResultReceiver(this, targetinfo, null);
        intent.putExtra("android.intent.extra.RESULT_RECEIVER", mRefinementResultReceiver);
        mRefinementIntentSender.sendIntent(this, 0, intent, null, null);
        return false;
        android.content.IntentSender.SendIntentException sendintentexception;
        sendintentexception;
        Log.e("ChooserActivity", "Refinement IntentSender failed to send", sendintentexception);
        updateModelAndChooserCounts(targetinfo);
        return super.onTargetSelected(targetinfo, flag);
    }

    void queryTargetServices(ChooserListAdapter chooserlistadapter)
    {
        PackageManager packagemanager;
        int i;
        int j;
        int k;
        packagemanager = getPackageManager();
        i = 0;
        j = 0;
        k = chooserlistadapter.getDisplayResolveInfoCount();
_L7:
        if(j >= k) goto _L2; else goto _L1
_L1:
        Object obj;
        int l;
        obj = chooserlistadapter.getDisplayResolveInfo(j);
        l = i;
        if(((ResolverActivity.DisplayResolveInfo) (obj)).getIsShowMore()) goto _L4; else goto _L3
_L3:
        if(chooserlistadapter.getScore(((ResolverActivity.DisplayResolveInfo) (obj))) != 0.0F) goto _L6; else goto _L5
_L5:
        l = i;
_L4:
        j++;
        i = l;
          goto _L7
_L6:
        Object obj1;
        int i1;
        obj1 = ((ResolverActivity.DisplayResolveInfo) (obj)).getResolveInfo().activityInfo;
        Object obj2 = ((ActivityInfo) (obj1)).metaData;
        if(obj2 != null)
            obj2 = convertServiceName(((ActivityInfo) (obj1)).packageName, ((Bundle) (obj2)).getString("android.service.chooser.chooser_target_service"));
        else
            obj2 = null;
        i1 = i;
        if(obj2 == null)
            break; /* Loop/switch isn't completed */
        obj2 = new ComponentName(((ActivityInfo) (obj1)).packageName, ((String) (obj2)));
        obj1 = (new Intent("android.service.chooser.ChooserTargetService")).setComponent(((ComponentName) (obj2)));
        try
        {
            if("android.permission.BIND_CHOOSER_TARGET_SERVICE".equals(packagemanager.getServiceInfo(((ComponentName) (obj2)), 0).permission))
                break MISSING_BLOCK_LABEL_310;
            obj = JVM INSTR new #165 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.w("ChooserActivity", ((StringBuilder) (obj)).append("ChooserTargetService ").append(obj2).append(" does not require").append(" permission ").append("android.permission.BIND_CHOOSER_TARGET_SERVICE").append(" - this service will not be queried for ChooserTargets.").append(" add android:permission=\"").append("android.permission.BIND_CHOOSER_TARGET_SERVICE").append("\"").append(" to the <service> tag for ").append(obj2).append(" in the manifest.").toString());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("ChooserActivity", (new StringBuilder()).append("Could not look up service ").append(obj2).append("; component name not found").toString());
            l = i;
            continue; /* Loop/switch isn't completed */
        }
        l = i;
        continue; /* Loop/switch isn't completed */
        l = i;
        if(!ChooserActivityInjector.canBindService(this, ((Intent) (obj1)), UserHandle.myUserId()))
            continue; /* Loop/switch isn't completed */
        ChooserTargetServiceConnection choosertargetserviceconnection = new ChooserTargetServiceConnection(this, ((ResolverActivity.DisplayResolveInfo) (obj)));
        i1 = i;
        if(bindServiceAsUser(((Intent) (obj1)), choosertargetserviceconnection, 5, Process.myUserHandle()))
        {
            mServiceConnections.add(choosertargetserviceconnection);
            i1 = i + 1;
        }
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L8
_L8:
        l = i1;
        if(i1 < 5) goto _L4; else goto _L2
_L2:
        if(!mServiceConnections.isEmpty())
            mChooserHandler.sendEmptyMessageDelayed(2, 5000L);
        else
            sendVoiceChoicesIfNeeded();
        return;
    }

    public boolean shouldAutoLaunchSingleChoice(ResolverActivity.TargetInfo targetinfo)
    {
        return getIntent().getBooleanExtra("android.intent.extra.AUTO_LAUNCH_SINGLE_CHOICE", super.shouldAutoLaunchSingleChoice(targetinfo));
    }

    public boolean shouldGetActivityMetadata()
    {
        return true;
    }

    public void showTargetDetails(ResolveInfo resolveinfo)
    {
        super.showTargetDetails(resolveinfo);
    }

    public void startSelected(int i, boolean flag, boolean flag1)
    {
        long l;
        long l1;
        l = System.currentTimeMillis();
        l1 = mChooserShownTime;
        super.startSelected(i, flag, flag1);
        if(mChooserListAdapter == null) goto _L2; else goto _L1
_L1:
        boolean flag2;
        int j;
        flag2 = false;
        j = i;
        mChooserListAdapter.getPositionTargetType(i);
        JVM INSTR tableswitch 0 2: default 64
    //                   0 108
    //                   1 115
    //                   2 137;
           goto _L3 _L4 _L5 _L6
_L3:
        i = ((flag2) ? 1 : 0);
_L8:
        if(i != 0)
            MetricsLogger.action(this, i, j);
        if(mIsSuccessfullySelected)
        {
            MetricsLogger.histogram(null, "user_selection_cost_for_smart_sharing", (int)(l - l1));
            MetricsLogger.histogram(null, "app_position_for_smart_sharing", j);
        }
_L2:
        return;
_L4:
        i = 215;
        continue; /* Loop/switch isn't completed */
_L5:
        char c = '\330';
        j = i - mChooserListAdapter.getCallerTargetCount();
        i = c;
        continue; /* Loop/switch isn't completed */
_L6:
        char c1 = '\331';
        j = i - (mChooserListAdapter.getCallerTargetCount() + mChooserListAdapter.getServiceTargetCount());
        i = c1;
        if(true) goto _L8; else goto _L7
_L7:
    }

    void unbindRemainingServices()
    {
        int i = 0;
        for(int j = mServiceConnections.size(); i < j; i++)
        {
            ChooserTargetServiceConnection choosertargetserviceconnection = (ChooserTargetServiceConnection)mServiceConnections.get(i);
            unbindService(choosertargetserviceconnection);
            choosertargetserviceconnection.destroy();
        }

        mServiceConnections.clear();
        mChooserHandler.removeMessages(2);
    }

    void updateModelAndChooserCounts(ResolverActivity.TargetInfo targetinfo)
    {
        if(targetinfo != null)
        {
            ResolveInfo resolveinfo = targetinfo.getResolveInfo();
            Intent intent = getTargetIntent();
            if(resolveinfo != null && resolveinfo.activityInfo != null && intent != null && mAdapter != null)
            {
                mAdapter.updateModel(targetinfo.getResolvedComponentName());
                mAdapter.updateChooserCounts(resolveinfo.activityInfo.packageName, getUserId(), intent.getAction());
            }
        }
        mIsSuccessfullySelected = true;
    }

    private static final float CALLER_TARGET_SCORE_BOOST = 900F;
    private static final int CHOOSER_TARGET_SERVICE_RESULT = 1;
    private static final int CHOOSER_TARGET_SERVICE_WATCHDOG_TIMEOUT = 2;
    private static final boolean DEBUG = false;
    public static final String EXTRA_PRIVATE_RETAIN_IN_ON_STOP = "com.android.internal.app.ChooserActivity.EXTRA_PRIVATE_RETAIN_IN_ON_STOP";
    private static final String PINNED_SHARED_PREFS_NAME = "chooser_pin_settings";
    private static final float PINNED_TARGET_SCORE_BOOST = 1000F;
    private static final int QUERY_TARGET_SERVICE_LIMIT = 5;
    private static final String TAG = "ChooserActivity";
    private static final String TARGET_DETAILS_FRAGMENT_TAG = "targetDetailsFragment";
    private static final int WATCHDOG_TIMEOUT_MILLIS = 5000;
    private ChooserTarget mCallerChooserTargets[];
    private final Handler mChooserHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 34
        //                       2 219;
               goto _L1 _L2 _L3
_L1:
            super.handleMessage(message);
_L5:
            return;
_L2:
            if(!isDestroyed())
            {
                message = (ServiceResultInfo)message.obj;
                if(!ChooserActivity._2D_get6(ChooserActivity.this).contains(((ServiceResultInfo) (message)).connection))
                {
                    Log.w("ChooserActivity", (new StringBuilder()).append("ChooserTargetServiceConnection ").append(((ServiceResultInfo) (message)).connection).append(" returned after being removed from active connections.").append(" Have you considered returning results faster?").toString());
                } else
                {
                    if(((ServiceResultInfo) (message)).resultTargets != null)
                        ChooserActivity._2D_get1(ChooserActivity.this).addServiceResults(((ServiceResultInfo) (message)).originalTarget, ((ServiceResultInfo) (message)).resultTargets);
                    unbindService(((ServiceResultInfo) (message)).connection);
                    ((ServiceResultInfo) (message)).connection.destroy();
                    ChooserActivity._2D_get6(ChooserActivity.this).remove(((ServiceResultInfo) (message)).connection);
                    if(ChooserActivity._2D_get6(ChooserActivity.this).isEmpty())
                    {
                        ChooserActivity._2D_get0(ChooserActivity.this).removeMessages(2);
                        sendVoiceChoicesIfNeeded();
                        ChooserActivity._2D_get1(ChooserActivity.this).setShowServiceTargets(true);
                    }
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            unbindRemainingServices();
            sendVoiceChoicesIfNeeded();
            ChooserActivity._2D_get1(ChooserActivity.this).setShowServiceTargets(true);
            if(true) goto _L5; else goto _L4
_L4:
        }

        final ChooserActivity this$0;

            
            {
                this$0 = ChooserActivity.this;
                super();
            }
    }
;
    private ChooserListAdapter mChooserListAdapter;
    private ChooserRowAdapter mChooserRowAdapter;
    private long mChooserShownTime;
    private IntentSender mChosenComponentSender;
    private ComponentName mFilteredComponentNames[];
    protected boolean mIsSuccessfullySelected;
    private SharedPreferences mPinnedSharedPrefs;
    private Intent mReferrerFillInIntent;
    private IntentSender mRefinementIntentSender;
    private RefinementResultReceiver mRefinementResultReceiver;
    private Bundle mReplacementExtras;
    private final List mServiceConnections = new ArrayList();

    // Unreferenced inner class com/android/internal/app/ChooserActivity$ChooserRowAdapter$1

/* anonymous class */
    class ChooserRowAdapter._cls1 extends DataSetObserver
    {

        public void onChanged()
        {
            super.onChanged();
            int i = getServiceTargetRowCount();
            if(ChooserRowAdapter._2D_get2(ChooserRowAdapter.this) == null || ChooserRowAdapter._2D_get2(ChooserRowAdapter.this).length != i)
            {
                RowScale arowscale[] = ChooserRowAdapter._2D_get2(ChooserRowAdapter.this);
                int j;
                if(arowscale != null)
                    j = arowscale.length;
                else
                    j = 0;
                ChooserRowAdapter._2D_set0(ChooserRowAdapter.this, new RowScale[i]);
                if(arowscale != null && i > 0)
                    System.arraycopy(arowscale, 0, ChooserRowAdapter._2D_get2(ChooserRowAdapter.this), 0, Math.min(arowscale.length, i));
                for(int k = i; k < j; k++)
                    arowscale[k].cancelAnimation();

                for(int l = j; l < i; l++)
                {
                    RowScale rowscale = (new RowScale(ChooserRowAdapter.this, 0.0F, 1.0F)).setInterpolator(ChooserRowAdapter._2D_get1(ChooserRowAdapter.this));
                    ChooserRowAdapter._2D_get2(ChooserRowAdapter.this)[l] = rowscale;
                }

                for(; j < i; j++)
                    ChooserRowAdapter._2D_get2(ChooserRowAdapter.this)[j].startAnimation();

            }
            notifyDataSetChanged();
        }

        public void onInvalidated()
        {
            super.onInvalidated();
            notifyDataSetInvalidated();
            if(ChooserRowAdapter._2D_get2(ChooserRowAdapter.this) != null)
            {
                RowScale arowscale[] = ChooserRowAdapter._2D_get2(ChooserRowAdapter.this);
                int i = 0;
                for(int j = arowscale.length; i < j; i++)
                    arowscale[i].cancelAnimation();

            }
        }

        final ChooserRowAdapter this$1;

            
            {
                this$1 = ChooserRowAdapter.this;
                super();
            }
    }


    // Unreferenced inner class com/android/internal/app/ChooserActivity$ChooserTargetServiceConnection$1

/* anonymous class */
    class ChooserTargetServiceConnection._cls1 extends android.service.chooser.IChooserTargetResult.Stub
    {

        public void sendResult(List list)
            throws RemoteException
        {
            Object obj = ChooserTargetServiceConnection._2D_get2(ChooserTargetServiceConnection.this);
            obj;
            JVM INSTR monitorenter ;
            if(ChooserTargetServiceConnection._2D_get0(ChooserTargetServiceConnection.this) != null)
                break MISSING_BLOCK_LABEL_61;
            list = JVM INSTR new #33  <Class StringBuilder>;
            list.StringBuilder();
            Log.e("ChooserActivity", list.append("destroyed ChooserTargetServiceConnection received result from ").append(ChooserTargetServiceConnection._2D_get1(ChooserTargetServiceConnection.this)).append("; ignoring...").toString());
            obj;
            JVM INSTR monitorexit ;
            return;
            ChooserTargetServiceConnection._2D_get0(ChooserTargetServiceConnection.this).filterServiceTargets(ChooserTargetServiceConnection._2D_get3(ChooserTargetServiceConnection.this).getResolveInfo().activityInfo.packageName, list);
            Message message = Message.obtain();
            message.what = 1;
            ServiceResultInfo serviceresultinfo = JVM INSTR new #99  <Class ChooserActivity$ServiceResultInfo>;
            serviceresultinfo.ServiceResultInfo(ChooserTargetServiceConnection._2D_get3(ChooserTargetServiceConnection.this), list, ChooserTargetServiceConnection.this);
            message.obj = serviceresultinfo;
            ChooserActivity._2D_get0(ChooserTargetServiceConnection._2D_get0(ChooserTargetServiceConnection.this)).sendMessage(message);
            obj;
            JVM INSTR monitorexit ;
            return;
            list;
            throw list;
        }

        final ChooserTargetServiceConnection this$1;

            
            {
                this$1 = ChooserTargetServiceConnection.this;
                super();
            }
    }


    // Unreferenced inner class com/android/internal/app/ChooserActivity$RowScale$2

/* anonymous class */
    class RowScale._cls2 extends AnimatorListenerAdapter
    {

        public void onAnimationEnd(Animator animator)
        {
            mAdapter.onAnimationEnd();
        }

        public void onAnimationStart(Animator animator)
        {
            mAdapter.onAnimationStart();
        }

        final RowScale this$1;

            
            {
                this$1 = RowScale.this;
                super();
            }
    }

}
