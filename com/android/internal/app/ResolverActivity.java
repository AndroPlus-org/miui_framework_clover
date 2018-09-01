// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.view.*;
import android.widget.*;
import com.android.internal.content.PackageMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.MaskLayout;
import com.android.internal.widget.ResolverDrawerLayout;
import java.util.*;
import miui.app.AlertActivity;
import miui.os.Build;
import miui.securityspace.XSpaceResolverActivityHelper;
import miui.widget.ScreenView;

// Referenced classes of package com.android.internal.app:
//            ResolverListController

public class ResolverActivity extends AlertActivity
{
    private static final class ActionTitle extends Enum
    {

        public static ActionTitle forAction(String s)
        {
            ActionTitle aactiontitle[] = values();
            int i = 0;
            for(int j = aactiontitle.length; i < j; i++)
            {
                ActionTitle actiontitle = aactiontitle[i];
                if(actiontitle != HOME && s != null && s.equals(actiontitle.action))
                    return actiontitle;
            }

            return DEFAULT;
        }

        public static ActionTitle valueOf(String s)
        {
            return (ActionTitle)Enum.valueOf(com/android/internal/app/ResolverActivity$ActionTitle, s);
        }

        public static ActionTitle[] values()
        {
            return $VALUES;
        }

        private static final ActionTitle $VALUES[];
        public static final ActionTitle CAPTURE_IMAGE;
        public static final ActionTitle DEFAULT;
        public static final ActionTitle EDIT;
        public static final ActionTitle HOME;
        public static final ActionTitle SEND;
        public static final ActionTitle SENDTO;
        public static final ActionTitle SEND_MULTIPLE;
        public static final ActionTitle VIEW;
        public final String action;
        public final int labelRes;
        public final int namedTitleRes;
        public final int titleRes;

        static 
        {
            VIEW = new ActionTitle("VIEW", 0, "android.intent.action.VIEW", 0x10406b3, 0x10406b5, 0x10406b4);
            EDIT = new ActionTitle("EDIT", 1, "android.intent.action.EDIT", 0x10406a4, 0x10406a6, 0x10406a5);
            SEND = new ActionTitle("SEND", 2, "android.intent.action.SEND", 0x10406ad, 0x10406af, 0x10406ae);
            SENDTO = new ActionTitle("SENDTO", 3, "android.intent.action.SENDTO", 0x10406b0, 0x10406b2, 0x10406b1);
            SEND_MULTIPLE = new ActionTitle("SEND_MULTIPLE", 4, "android.intent.action.SEND_MULTIPLE", 0x10406ad, 0x10406af, 0x10406ae);
            CAPTURE_IMAGE = new ActionTitle("CAPTURE_IMAGE", 5, "android.media.action.IMAGE_CAPTURE", 0x10406aa, 0x10406ac, 0x10406ab);
            DEFAULT = new ActionTitle("DEFAULT", 6, null, 0x10406a1, 0x10406a3, 0x10406a2);
            HOME = new ActionTitle("HOME", 7, "android.intent.action.MAIN", 0x10406a7, 0x10406a9, 0x10406a8);
            $VALUES = (new ActionTitle[] {
                VIEW, EDIT, SEND, SENDTO, SEND_MULTIPLE, CAPTURE_IMAGE, DEFAULT, HOME
            });
        }

        private ActionTitle(String s, int i, String s1, int j, int k, int l)
        {
            super(s, i);
            action = s1;
            titleRes = j;
            namedTitleRes = k;
            labelRes = l;
        }
    }

    public final class DisplayResolveInfo
        implements TargetInfo
    {

        static ResolveInfo _2D_get0(DisplayResolveInfo displayresolveinfo)
        {
            return displayresolveinfo.mResolveInfo;
        }

        static boolean _2D_get1(DisplayResolveInfo displayresolveinfo)
        {
            return displayresolveinfo.mShowMore;
        }

        public void addAlternateSourceIntent(Intent intent)
        {
            mSourceIntents.add(intent);
        }

        public TargetInfo cloneFilledIn(Intent intent, int i)
        {
            return new DisplayResolveInfo(this, intent, i);
        }

        public List getAllSourceIntents()
        {
            return mSourceIntents;
        }

        public CharSequence getBadgeContentDescription()
        {
            return null;
        }

        public Drawable getBadgeIcon()
        {
            return null;
        }

        public Drawable getDisplayIcon()
        {
            return mDisplayIcon;
        }

        public CharSequence getDisplayLabel()
        {
            return mDisplayLabel;
        }

        public CharSequence getExtendedInfo()
        {
            return mExtendedInfo;
        }

        public boolean getIsShowMore()
        {
            return mShowMore;
        }

        public ResolveInfo getResolveInfo()
        {
            return mResolveInfo;
        }

        public ComponentName getResolvedComponentName()
        {
            return new ComponentName(mResolveInfo.activityInfo.packageName, mResolveInfo.activityInfo.name);
        }

        public Intent getResolvedIntent()
        {
            return mResolvedIntent;
        }

        public boolean hasDisplayIcon()
        {
            boolean flag;
            if(mDisplayIcon != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isPinned()
        {
            return mPinned;
        }

        public void setDisplayIcon(Drawable drawable)
        {
            mDisplayIcon = drawable;
        }

        public void setPinned(boolean flag)
        {
            mPinned = flag;
        }

        public boolean start(Activity activity, Bundle bundle)
        {
            activity.startActivity(mResolvedIntent, bundle);
            return true;
        }

        public boolean startAsCaller(Activity activity, Bundle bundle, int i)
        {
            activity.startActivityAsCaller(mResolvedIntent, bundle, false, i);
            return true;
        }

        public boolean startAsUser(Activity activity, Bundle bundle, UserHandle userhandle)
        {
            activity.startActivityAsUser(mResolvedIntent, bundle, userhandle);
            return false;
        }

        private Drawable mDisplayIcon;
        private final CharSequence mDisplayLabel;
        private final CharSequence mExtendedInfo;
        private boolean mPinned;
        private final ResolveInfo mResolveInfo;
        private final Intent mResolvedIntent;
        private boolean mShowMore;
        private final List mSourceIntents;
        final ResolverActivity this$0;

        public DisplayResolveInfo(Intent intent, ResolveInfo resolveinfo, CharSequence charsequence, CharSequence charsequence1, Intent intent1)
        {
            this$0 = ResolverActivity.this;
            super();
            mSourceIntents = new ArrayList();
            mShowMore = false;
            mSourceIntents.add(intent);
            mResolveInfo = resolveinfo;
            mDisplayLabel = charsequence;
            mExtendedInfo = charsequence1;
            if(intent1 == null)
                intent1 = getReplacementIntent(resolveinfo.activityInfo, getTargetIntent());
            resolveractivity = new Intent(intent1);
            addFlags(0x3000000);
            intent = mResolveInfo.activityInfo;
            setComponent(new ComponentName(((ActivityInfo) (intent)).applicationInfo.packageName, ((ActivityInfo) (intent)).name));
            mResolvedIntent = ResolverActivity.this;
            mShowMore = false;
        }

        private DisplayResolveInfo(DisplayResolveInfo displayresolveinfo, Intent intent, int i)
        {
            this$0 = ResolverActivity.this;
            super();
            mSourceIntents = new ArrayList();
            mShowMore = false;
            mSourceIntents.addAll(displayresolveinfo.getAllSourceIntents());
            mResolveInfo = displayresolveinfo.mResolveInfo;
            mDisplayLabel = displayresolveinfo.mDisplayLabel;
            mDisplayIcon = displayresolveinfo.mDisplayIcon;
            mExtendedInfo = displayresolveinfo.mExtendedInfo;
            mResolvedIntent = new Intent(displayresolveinfo.mResolvedIntent);
            mResolvedIntent.fillIn(intent, i);
            mPinned = displayresolveinfo.mPinned;
            mShowMore = false;
        }

        private DisplayResolveInfo(DisplayResolveInfo displayresolveinfo, Drawable drawable, CharSequence charsequence)
        {
            this$0 = ResolverActivity.this;
            super();
            mSourceIntents = new ArrayList();
            mShowMore = false;
            mDisplayIcon = drawable;
            mDisplayLabel = charsequence;
            mExtendedInfo = charsequence;
            mSourceIntents.addAll(displayresolveinfo.getAllSourceIntents());
            mResolveInfo = displayresolveinfo.mResolveInfo;
            mResolvedIntent = displayresolveinfo.getResolvedIntent();
            mPinned = displayresolveinfo.mPinned;
            mShowMore = true;
        }

        DisplayResolveInfo(DisplayResolveInfo displayresolveinfo, Drawable drawable, CharSequence charsequence, DisplayResolveInfo displayresolveinfo1)
        {
            this(displayresolveinfo, drawable, charsequence);
        }
    }

    class ItemClickListener
        implements android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            int j;
            if(adapterview instanceof ListView)
                adapterview = (ListView)adapterview;
            else
                adapterview = null;
            j = i;
            if(adapterview != null)
                j = i - adapterview.getHeaderViewsCount();
            if(j < 0)
                return;
            adapterview = (CheckBox)findViewById(0x110c0032);
            boolean flag;
            if(adapterview.getVisibility() == 0)
                flag = adapterview.isChecked();
            else
                flag = false;
            startSelected(start + j, flag, true);
        }

        public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
        {
            int j;
            if(adapterview instanceof ListView)
                adapterview = (ListView)adapterview;
            else
                adapterview = null;
            j = i;
            if(adapterview != null)
                j = i - adapterview.getHeaderViewsCount();
            if(j < 0)
                return false;
            adapterview = mAdapter.targetInfoForPosition(start + j, true);
            if((adapterview instanceof DisplayResolveInfo) && Build.IS_INTERNATIONAL_BUILD && DisplayResolveInfo._2D_get1((DisplayResolveInfo)adapterview))
            {
                ResolverActivity._2D_wrap1(ResolverActivity.this);
                return true;
            } else
            {
                adapterview = mAdapter.resolveInfoForPosition(start + j, true);
                showTargetDetails(adapterview);
                return true;
            }
        }

        private int start;
        final ResolverActivity this$0;

        public ItemClickListener(int i)
        {
            this$0 = ResolverActivity.this;
            super();
            start = (i - 1) * 8;
        }
    }

    class LoadAdapterIconTask extends LoadIconTask
    {

        protected void onPostExecute(Drawable drawable)
        {
            super.onPostExecute(drawable);
            if(ResolverActivity._2D_get5(ResolverActivity.this) != null && mAdapter.getOtherProfile() == mDisplayResolveInfo)
                bindProfileView();
            adapter.notifyDataSetChanged();
        }

        private BaseAdapter adapter;
        final ResolverActivity this$0;

        public LoadAdapterIconTask(BaseAdapter baseadapter, DisplayResolveInfo displayresolveinfo)
        {
            this$0 = ResolverActivity.this;
            super(displayresolveinfo);
            adapter = baseadapter;
        }

        public LoadAdapterIconTask(DisplayResolveInfo displayresolveinfo)
        {
            this(null, displayresolveinfo);
        }
    }

    class LoadIconIntoViewTask extends LoadIconTask
    {

        protected void onPostExecute(Drawable drawable)
        {
            super.onPostExecute(drawable);
            mTargetView.setImageDrawable(drawable);
        }

        private final ImageView mTargetView;
        final ResolverActivity this$0;

        public LoadIconIntoViewTask(DisplayResolveInfo displayresolveinfo, ImageView imageview)
        {
            this$0 = ResolverActivity.this;
            super(displayresolveinfo);
            mTargetView = imageview;
        }
    }

    abstract class LoadIconTask extends AsyncTask
    {

        protected transient Drawable doInBackground(Void avoid[])
        {
            return mResolveInfo.activityInfo.loadIcon(getPackageManager());
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected void onPostExecute(Drawable drawable)
        {
            mDisplayResolveInfo.setDisplayIcon(drawable);
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Drawable)obj);
        }

        protected final DisplayResolveInfo mDisplayResolveInfo;
        private final ResolveInfo mResolveInfo;
        final ResolverActivity this$0;

        public LoadIconTask(DisplayResolveInfo displayresolveinfo)
        {
            this$0 = ResolverActivity.this;
            super();
            mDisplayResolveInfo = displayresolveinfo;
            mResolveInfo = displayresolveinfo.getResolveInfo();
        }
    }

    private final class PageGridAdapter extends ArrayAdapter
    {

        private final void bindView(View view, DisplayResolveInfo displayresolveinfo)
        {
            ViewHolder viewholder = (ViewHolder)view.getTag();
            viewholder.text2.setVisibility(8);
            if(displayresolveinfo == null)
            {
                viewholder.icon.setImageDrawable(getDrawable(0x1080711));
                return;
            }
            viewholder.text.setText(displayresolveinfo.getDisplayLabel());
            if((displayresolveinfo instanceof DisplayResolveInfo) && displayresolveinfo.hasDisplayIcon() ^ true)
                (new LoadAdapterIconTask(this, displayresolveinfo)).execute(new Void[0]);
            viewholder.icon.setImageDrawable(displayresolveinfo.getDisplayIcon());
            if(viewholder.badge != null)
            {
                view = displayresolveinfo.getBadgeIcon();
                if(view != null)
                {
                    viewholder.badge.setImageDrawable(view);
                    viewholder.badge.setContentDescription(displayresolveinfo.getBadgeContentDescription());
                    viewholder.badge.setVisibility(0);
                } else
                {
                    viewholder.badge.setVisibility(8);
                }
            }
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
            {
                view = LayoutInflater.from(getContext()).inflate(0x11030011, viewgroup, false);
                ((MaskLayout)view.findViewById(0x110c002c)).setMaskColor(ResolverActivity._2D_get3(ResolverActivity.this));
                viewgroup = new ViewHolder(view);
                view.setTag(viewgroup);
                viewgroup = ((ViewHolder) (viewgroup)).icon.getLayoutParams();
                int j = ResolverActivity._2D_get2(ResolverActivity.this);
                viewgroup.height = j;
                viewgroup.width = j;
            }
            bindView(view, (DisplayResolveInfo)getItem(i));
            return view;
        }

        final ResolverActivity this$0;

        public PageGridAdapter(Context context, int i)
        {
            this$0 = ResolverActivity.this;
            super(context, 0);
            int j = mAdapter.getCount();
            i = (i - 1) * 8;
            for(j = Math.min((i + 8) - 1, j - 1); i <= j; i++)
                add((DisplayResolveInfo)mAdapter.getItem(i));

        }
    }

    static class PickTargetOptionRequest extends android.app.VoiceInteractor.PickOptionRequest
    {

        public void onCancel()
        {
            super.onCancel();
            ResolverActivity resolveractivity = (ResolverActivity)getActivity();
            if(resolveractivity != null)
            {
                ResolverActivity._2D_set1(resolveractivity, null);
                resolveractivity.finish();
            }
        }

        public void onPickOptionResult(boolean flag, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        {
            super.onPickOptionResult(flag, aoption, bundle);
            if(aoption.length != 1)
                return;
            bundle = (ResolverActivity)getActivity();
            if(bundle != null && bundle.onTargetSelected(((ResolverActivity) (bundle)).mAdapter.getItem(aoption[0].getIndex()), false))
            {
                ResolverActivity._2D_set1(bundle, null);
                bundle.finish();
            }
        }

        public PickTargetOptionRequest(android.app.VoiceInteractor.Prompt prompt, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        {
            super(prompt, aoption, bundle);
        }
    }

    public class ResolveListAdapter extends BaseAdapter
    {

        static DisplayResolveInfo _2D_get0(ResolveListAdapter resolvelistadapter)
        {
            return resolvelistadapter.mOtherProfile;
        }

        static int _2D_get1(ResolveListAdapter resolvelistadapter)
        {
            return resolvelistadapter.mPlaceholderCount;
        }

        static ResolverListController _2D_get2(ResolveListAdapter resolvelistadapter)
        {
            return resolvelistadapter.mResolverListController;
        }

        static void _2D_wrap0(ResolveListAdapter resolvelistadapter, List list)
        {
            resolvelistadapter.processSortedList(list);
        }

        static void _2D_wrap1(ResolveListAdapter resolvelistadapter)
        {
            resolvelistadapter.resetAlwaysUseButton();
        }

        private boolean addMoreResolverButton()
        {
            if(!ResolverActivity._2D_get6(ResolverActivity.this) || Build.IS_INTERNATIONAL_BUILD ^ true)
                return false;
            for(int i = 0; i < mDisplayList.size(); i++)
            {
                DisplayResolveInfo displayresolveinfo = (DisplayResolveInfo)mDisplayList.get(i);
                if("com.android.browser".equals(DisplayResolveInfo._2D_get0(displayresolveinfo).activityInfo.packageName))
                {
                    mDisplayList.clear();
                    mDisplayList.add(displayresolveinfo);
                    displayresolveinfo = new DisplayResolveInfo(displayresolveinfo, getResources().getDrawable(0x1080712, getTheme()), getResources().getString(0x10403b9), null);
                    mDisplayList.add(displayresolveinfo);
                    return true;
                }
            }

            return false;
        }

        private void addResolveInfo(DisplayResolveInfo displayresolveinfo)
        {
            if(DisplayResolveInfo._2D_get0(displayresolveinfo).targetUserId == -2)
                mDisplayList.add(displayresolveinfo);
        }

        private void addResolveInfoWithAlternates(ResolvedComponentInfo resolvedcomponentinfo, CharSequence charsequence, CharSequence charsequence1)
        {
            int i = resolvedcomponentinfo.getCount();
            Intent intent = resolvedcomponentinfo.getIntentAt(0);
            ResolveInfo resolveinfo = resolvedcomponentinfo.getResolveInfoAt(0);
            Intent intent1 = getReplacementIntent(resolveinfo.activityInfo, intent);
            charsequence = new DisplayResolveInfo(intent, resolveinfo, charsequence1, charsequence, intent1);
            charsequence.setPinned(resolvedcomponentinfo.isPinned());
            addResolveInfo(charsequence);
            if(intent1 == intent)
            {
                for(int j = 1; j < i; j++)
                    charsequence.addAlternateSourceIntent(resolvedcomponentinfo.getIntentAt(j));

            }
            updateLastChosenPosition(resolveinfo);
        }

        private void customResolver()
        {
            if(mDisplayList == null || mDisplayList.size() <= 1)
                return;
            if(!addMoreResolverButton())
            {
                for(int i = 0; i < mDisplayList.size(); i++)
                {
                    DisplayResolveInfo displayresolveinfo = (DisplayResolveInfo)mDisplayList.get(i);
                    if(ResolverActivity._2D_get0().contains(DisplayResolveInfo._2D_get0(displayresolveinfo).activityInfo.packageName))
                    {
                        mDisplayList.add(0, displayresolveinfo);
                        mDisplayList.remove(i + 1);
                    }
                }

            }
        }

        private void onBindView(View view, TargetInfo targetinfo)
        {
            view = (ViewHolder)view.getTag();
            ((ViewHolder) (view)).text.setText(targetinfo.getDisplayLabel());
            if(showsExtendedInfo(targetinfo))
            {
                ((ViewHolder) (view)).text2.setVisibility(0);
                ((ViewHolder) (view)).text2.setText(targetinfo.getExtendedInfo());
            } else
            {
                ((ViewHolder) (view)).text2.setVisibility(8);
            }
            if((targetinfo instanceof DisplayResolveInfo) && ((DisplayResolveInfo)targetinfo).hasDisplayIcon() ^ true)
                (new LoadAdapterIconTask((DisplayResolveInfo)targetinfo)).execute(new Void[0]);
            ((ViewHolder) (view)).icon.setImageDrawable(targetinfo.getDisplayIcon());
            if(((ViewHolder) (view)).badge != null)
            {
                Drawable drawable = targetinfo.getBadgeIcon();
                if(drawable != null)
                {
                    ((ViewHolder) (view)).badge.setImageDrawable(drawable);
                    ((ViewHolder) (view)).badge.setContentDescription(targetinfo.getBadgeContentDescription());
                    ((ViewHolder) (view)).badge.setVisibility(0);
                } else
                {
                    ((ViewHolder) (view)).badge.setVisibility(8);
                }
            }
        }

        private void postListReadyRunnable()
        {
            if(ResolverActivity._2D_get4(ResolverActivity.this) == null)
            {
                ResolverActivity._2D_set2(ResolverActivity.this, new Runnable() {

                    public void run()
                    {
                        setTitleAndIcon();
                        resetAlwaysOrOnceButtonBar();
                        onListRebuilt();
                        ResolverActivity._2D_set2(_fld0, null);
                    }

                    final ResolveListAdapter this$1;

            
            {
                this$1 = ResolveListAdapter.this;
                super();
            }
                }
);
                getMainThreadHandler().post(ResolverActivity._2D_get4(ResolverActivity.this));
            }
        }

        private void processGroup(List list, int i, int j, ResolvedComponentInfo resolvedcomponentinfo, CharSequence charsequence)
        {
            if((j - i) + 1 != 1) goto _L2; else goto _L1
_L1:
            addResolveInfoWithAlternates(resolvedcomponentinfo, null, charsequence);
_L5:
            return;
_L2:
            boolean flag;
            Object obj;
            boolean flag1;
            mHasExtendedInfo = true;
            flag = false;
            obj = resolvedcomponentinfo.getResolveInfoAt(0).activityInfo.applicationInfo.loadLabel(mPm);
            if(obj == null)
                flag = true;
            flag1 = flag;
            if(flag) goto _L4; else goto _L3
_L3:
            int k;
            resolvedcomponentinfo = new HashSet();
            resolvedcomponentinfo.add(obj);
            k = i + 1;
_L6:
            flag1 = flag;
            if(k <= j)
            {
                obj = ((ResolvedComponentInfo)list.get(k)).getResolveInfoAt(0).activityInfo.applicationInfo.loadLabel(mPm);
                if(obj != null && !resolvedcomponentinfo.contains(obj))
                    break MISSING_BLOCK_LABEL_212;
                flag1 = true;
            }
            resolvedcomponentinfo.clear();
_L4:
            while(i <= j) 
            {
                obj = (ResolvedComponentInfo)list.get(i);
                resolvedcomponentinfo = ((ResolvedComponentInfo) (obj)).getResolveInfoAt(0);
                if(flag1)
                    resolvedcomponentinfo = ((ResolveInfo) (resolvedcomponentinfo)).activityInfo.packageName;
                else
                    resolvedcomponentinfo = ((ResolveInfo) (resolvedcomponentinfo)).activityInfo.applicationInfo.loadLabel(mPm);
                addResolveInfoWithAlternates(((ResolvedComponentInfo) (obj)), resolvedcomponentinfo, charsequence);
                i++;
            }
              goto _L5
            resolvedcomponentinfo.add(obj);
            k++;
              goto _L6
        }

        private void processSortedList(List list)
        {
            if(list != null)
            {
                int i = list.size();
                if(i != 0)
                {
                    if(mInitialIntents != null)
                    {
                        int j = 0;
                        while(j < mInitialIntents.length) 
                        {
                            Intent intent = mInitialIntents[j];
                            if(intent != null)
                            {
                                Object obj1 = intent.resolveActivityInfo(getPackageManager(), 0);
                                if(obj1 == null)
                                {
                                    Log.w("ResolverActivity", (new StringBuilder()).append("No activity found for ").append(intent).toString());
                                } else
                                {
                                    ResolveInfo resolveinfo1 = new ResolveInfo();
                                    resolveinfo1.activityInfo = ((ActivityInfo) (obj1));
                                    obj1 = (UserManager)getSystemService("user");
                                    if(intent instanceof LabeledIntent)
                                    {
                                        LabeledIntent labeledintent = (LabeledIntent)intent;
                                        resolveinfo1.resolvePackageName = labeledintent.getSourcePackage();
                                        resolveinfo1.labelRes = labeledintent.getLabelResource();
                                        resolveinfo1.nonLocalizedLabel = labeledintent.getNonLocalizedLabel();
                                        resolveinfo1.icon = labeledintent.getIconResource();
                                        resolveinfo1.iconResourceId = resolveinfo1.icon;
                                    }
                                    if(((UserManager) (obj1)).isManagedProfile())
                                    {
                                        resolveinfo1.noResourceId = true;
                                        resolveinfo1.icon = 0;
                                    }
                                    addResolveInfo(new DisplayResolveInfo(intent, resolveinfo1, resolveinfo1.loadLabel(getPackageManager()), null, intent));
                                }
                            }
                            j++;
                        }
                    }
                    ResolvedComponentInfo resolvedcomponentinfo = (ResolvedComponentInfo)list.get(0);
                    ResolveInfo resolveinfo = resolvedcomponentinfo.getResolveInfoAt(0);
                    int l = 0;
                    Object obj2 = resolveinfo.loadLabel(mPm);
                    mHasExtendedInfo = false;
                    int k = 1;
                    while(k < i) 
                    {
                        Object obj = obj2;
                        if(obj2 == null)
                            obj = resolveinfo.activityInfo.packageName;
                        ResolvedComponentInfo resolvedcomponentinfo1 = (ResolvedComponentInfo)list.get(k);
                        ResolveInfo resolveinfo2 = resolvedcomponentinfo1.getResolveInfoAt(0);
                        CharSequence charsequence = resolveinfo2.loadLabel(mPm);
                        obj2 = charsequence;
                        if(charsequence == null)
                            obj2 = resolveinfo2.activityInfo.packageName;
                        if(!obj2.equals(obj))
                        {
                            processGroup(list, l, k - 1, resolvedcomponentinfo, ((CharSequence) (obj)));
                            resolvedcomponentinfo = resolvedcomponentinfo1;
                            resolveinfo = resolveinfo2;
                            obj = obj2;
                            l = k;
                        }
                        k++;
                        obj2 = obj;
                    }
                    processGroup(list, l, i - 1, resolvedcomponentinfo, ((CharSequence) (obj2)));
                }
            }
            customResolver();
            if(mOtherProfile != null && mLastChosenPosition >= 0)
            {
                mLastChosenPosition = -1;
                mFilterLastUsed = false;
            }
            postListReadyRunnable();
        }

        private void resetAlwaysUseButton()
        {
            if(ResolverActivity._2D_get1(ResolverActivity.this))
            {
                CheckBox checkbox = (CheckBox)findViewById(0x110c0032);
                if(checkbox != null)
                {
                    checkbox.setVisibility(0);
                    if(ResolverActivity._2D_get6(ResolverActivity.this) && Build.IS_INTERNATIONAL_BUILD && mAdapter.mDisplayList != null && mAdapter.mDisplayList.size() > 1 && "com.android.browser".equals(DisplayResolveInfo._2D_get0((DisplayResolveInfo)mAdapter.mDisplayList.get(0)).activityInfo.packageName))
                        checkbox.setChecked(true);
                    else
                        checkbox.setChecked(false);
                } else
                {
                    ResolverActivity._2D_set0(ResolverActivity.this, false);
                }
            }
        }

        private void updateLastChosenPosition(ResolveInfo resolveinfo)
        {
            if(mOtherProfile != null)
            {
                mLastChosenPosition = -1;
                return;
            }
            if(mLastChosen != null && mLastChosen.activityInfo.packageName.equals(resolveinfo.activityInfo.packageName) && mLastChosen.activityInfo.name.equals(resolveinfo.activityInfo.name))
                mLastChosenPosition = mDisplayList.size() - 1;
        }

        public final void bindView(int i, View view)
        {
            onBindView(view, getItem(i));
        }

        public final View createView(ViewGroup viewgroup)
        {
            viewgroup = onCreateView(viewgroup);
            viewgroup.setTag(new ViewHolder(viewgroup));
            return viewgroup;
        }

        public int getCount()
        {
            int i;
            if(mDisplayList == null || mDisplayList.isEmpty())
                i = mPlaceholderCount;
            else
                i = mDisplayList.size();
            return i;
        }

        public DisplayResolveInfo getDisplayInfoAt(int i)
        {
            return (DisplayResolveInfo)mDisplayList.get(i);
        }

        public int getDisplayInfoCount()
        {
            return mDisplayList.size();
        }

        public List getDisplayList()
        {
            return mDisplayList;
        }

        public DisplayResolveInfo getDisplayResolveInfo(int i)
        {
            return (DisplayResolveInfo)mDisplayList.get(i);
        }

        public int getDisplayResolveInfoCount()
        {
            return mDisplayList.size();
        }

        public DisplayResolveInfo getFilteredItem()
        {
            if(mFilterLastUsed && mLastChosenPosition >= 0)
                return (DisplayResolveInfo)mDisplayList.get(mLastChosenPosition);
            else
                return null;
        }

        public int getFilteredPosition()
        {
            if(mFilterLastUsed && mLastChosenPosition >= 0)
                return mLastChosenPosition;
            else
                return -1;
        }

        public TargetInfo getItem(int i)
        {
            if(mDisplayList.size() > i)
                return (TargetInfo)mDisplayList.get(i);
            else
                return null;
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public DisplayResolveInfo getOtherProfile()
        {
            return mOtherProfile;
        }

        public float getScore(DisplayResolveInfo displayresolveinfo)
        {
            return mResolverListController.getScore(displayresolveinfo);
        }

        public int getUnfilteredCount()
        {
            return mDisplayList.size();
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view == null)
                view1 = createView(viewgroup);
            onBindView(view1, getItem(i));
            return view1;
        }

        public void handlePackagesChanged()
        {
            rebuildList();
            if(getCount() == 0)
                finish();
        }

        public boolean hasExtendedInfo()
        {
            return mHasExtendedInfo;
        }

        public boolean hasFilteredItem()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mFilterLastUsed)
            {
                flag1 = flag;
                if(mLastChosenPosition >= 0)
                    flag1 = true;
            }
            return flag1;
        }

        public boolean hasResolvedTarget(ResolveInfo resolveinfo)
        {
            int i = 0;
            for(int j = mDisplayList.size(); i < j; i++)
                if(ResolverActivity.resolveInfoMatch(resolveinfo, ((DisplayResolveInfo)mDisplayList.get(i)).getResolveInfo()))
                    return true;

            return false;
        }

        public boolean isComponentPinned(ComponentName componentname)
        {
            return false;
        }

        public View onCreateView(ViewGroup viewgroup)
        {
            return mInflater.inflate(0x10900d9, viewgroup, false);
        }

        public void onListRebuilt()
        {
        }

        protected boolean rebuildList()
        {
            Object obj;
            Iterator iterator;
            Object obj1;
            try
            {
                Intent intent = getTargetIntent();
                mLastChosen = AppGlobals.getPackageManager().getLastChosenActivity(intent, intent.resolveTypeIfNeeded(getContentResolver()), 0x10000);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.d("ResolverActivity", (new StringBuilder()).append("Error calling setLastChosenActivity\n").append(obj).toString());
            }
            mOtherProfile = null;
            mLastChosen = null;
            mLastChosenPosition = -1;
            mDisplayList.clear();
            if(mBaseResolveList == null) goto _L2; else goto _L1
_L1:
            obj = new ArrayList();
            mOrigResolveList = ((List) (obj));
            mResolverListController.addResolveListDedupe(((List) (obj)), getTargetIntent(), mBaseResolveList);
_L4:
            iterator = ((Iterable) (obj)).iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                obj1 = (ResolvedComponentInfo)iterator.next();
                if(((ResolvedComponentInfo) (obj1)).getResolveInfoAt(0).targetUserId == -2)
                    continue;
                mOtherProfile = new DisplayResolveInfo(((ResolvedComponentInfo) (obj1)).getIntentAt(0), ((ResolvedComponentInfo) (obj1)).getResolveInfoAt(0), ((ResolvedComponentInfo) (obj1)).getResolveInfoAt(0).loadLabel(mPm), ((ResolvedComponentInfo) (obj1)).getResolveInfoAt(0).loadLabel(mPm), getReplacementIntent(((ResolvedComponentInfo) (obj1)).getResolveInfoAt(0).activityInfo, ((ResolvedComponentInfo) (obj1)).getIntentAt(0)));
                ((List) (obj)).remove(obj1);
                break;
            } while(true);
            if(mOtherProfile == null)
                try
                {
                    mLastChosen = mResolverListController.getLastChosen();
                }
                catch(RemoteException remoteexception)
                {
                    Log.d("ResolverActivity", (new StringBuilder()).append("Error calling getLastChosenActivity\n").append(remoteexception).toString());
                }
            if(obj != null && ((List) (obj)).size() > 0)
            {
                obj1 = mResolverListController;
                ArrayList arraylist;
                List list;
                boolean flag;
                if(mOrigResolveList == obj)
                    flag = true;
                else
                    flag = false;
                obj1 = ((ResolverListController) (obj1)).filterLowPriority(((List) (obj)), flag);
                if(obj1 != null)
                    mOrigResolveList = ((List) (obj1));
                if(((List) (obj)).size() > 1)
                {
                    setPlaceholderCount(((List) (obj)).size());
                    (new AsyncTask() {

                        protected volatile Object doInBackground(Object aobj[])
                        {
                            return doInBackground((List[])aobj);
                        }

                        protected transient List doInBackground(List alist[])
                        {
                            ResolveListAdapter._2D_get2(ResolveListAdapter.this).sort(alist[0]);
                            return alist[0];
                        }

                        protected volatile void onPostExecute(Object obj)
                        {
                            onPostExecute((List)obj);
                        }

                        protected void onPostExecute(List list)
                        {
                            ResolveListAdapter._2D_wrap0(ResolveListAdapter.this, list);
                            ResolveListAdapter._2D_wrap1(ResolveListAdapter.this);
                            if(ResolverActivity._2D_get5(_fld0) != null)
                                bindProfileView();
                            notifyDataSetChanged();
                            ResolverActivity._2D_wrap0(_fld0);
                        }

                        final ResolveListAdapter this$1;

            
            {
                this$1 = ResolveListAdapter.this;
                super();
            }
                    }
).execute(new List[] {
                        obj
                    });
                    postListReadyRunnable();
                    return false;
                } else
                {
                    processSortedList(((List) (obj)));
                    return true;
                }
            } else
            {
                processSortedList(((List) (obj)));
                return true;
            }
_L2:
            list = mResolverListController.getResolversForIntent(shouldGetResolvedFilter(), shouldGetActivityMetadata(), mIntents);
            mOrigResolveList = list;
            if(list == null)
            {
                processSortedList(list);
                return true;
            }
            arraylist = mResolverListController.filterIneligibleActivities(list, true);
            obj = list;
            if(arraylist != null)
            {
                mOrigResolveList = arraylist;
                obj = list;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public ResolveInfo resolveInfoForPosition(int i, boolean flag)
        {
            TargetInfo targetinfo = targetInfoForPosition(i, flag);
            if(targetinfo != null)
                return targetinfo.getResolveInfo();
            else
                return null;
        }

        public void setPlaceholderCount(int i)
        {
            mPlaceholderCount = i;
        }

        public boolean shouldGetResolvedFilter()
        {
            return mFilterLastUsed;
        }

        public boolean showsExtendedInfo(TargetInfo targetinfo)
        {
            return TextUtils.isEmpty(targetinfo.getExtendedInfo()) ^ true;
        }

        public TargetInfo targetInfoForPosition(int i, boolean flag)
        {
            if(flag)
                return getItem(i);
            if(mDisplayList.size() > i)
                return (TargetInfo)mDisplayList.get(i);
            else
                return null;
        }

        public void updateChooserCounts(String s, int i, String s1)
        {
            mResolverListController.updateChooserCounts(s, i, s1);
        }

        public void updateModel(ComponentName componentname)
        {
            mResolverListController.updateModel(componentname);
        }

        private final List mBaseResolveList;
        List mDisplayList;
        private boolean mFilterLastUsed;
        private boolean mHasExtendedInfo;
        protected final LayoutInflater mInflater;
        private final Intent mInitialIntents[];
        private final List mIntents;
        protected ResolveInfo mLastChosen;
        private int mLastChosenPosition;
        List mOrigResolveList;
        private DisplayResolveInfo mOtherProfile;
        private int mPlaceholderCount;
        private ResolverListController mResolverListController;
        final ResolverActivity this$0;

        public ResolveListAdapter(Context context, List list, Intent aintent[], List list1, int i, boolean flag, 
                ResolverListController resolverlistcontroller)
        {
            this$0 = ResolverActivity.this;
            super();
            mLastChosenPosition = -1;
            mIntents = list;
            mInitialIntents = aintent;
            mBaseResolveList = list1;
            mLaunchedFromUid = i;
            mInflater = LayoutInflater.from(context);
            mDisplayList = new ArrayList();
            mFilterLastUsed = flag;
            mResolverListController = resolverlistcontroller;
        }
    }

    public static final class ResolvedComponentInfo
    {

        public void add(Intent intent, ResolveInfo resolveinfo)
        {
            mIntents.add(intent);
            mResolveInfos.add(resolveinfo);
        }

        public int findIntent(Intent intent)
        {
            int i = 0;
            for(int j = mIntents.size(); i < j; i++)
                if(intent.equals(mIntents.get(i)))
                    return i;

            return -1;
        }

        public int findResolveInfo(ResolveInfo resolveinfo)
        {
            int i = 0;
            for(int j = mResolveInfos.size(); i < j; i++)
                if(resolveinfo.equals(mResolveInfos.get(i)))
                    return i;

            return -1;
        }

        public int getCount()
        {
            return mIntents.size();
        }

        public Intent getIntentAt(int i)
        {
            Intent intent;
            if(i >= 0)
                intent = (Intent)mIntents.get(i);
            else
                intent = null;
            return intent;
        }

        public ResolveInfo getResolveInfoAt(int i)
        {
            ResolveInfo resolveinfo;
            if(i >= 0)
                resolveinfo = (ResolveInfo)mResolveInfos.get(i);
            else
                resolveinfo = null;
            return resolveinfo;
        }

        public boolean isPinned()
        {
            return mPinned;
        }

        public void setPinned(boolean flag)
        {
            mPinned = flag;
        }

        private final List mIntents = new ArrayList();
        private boolean mPinned;
        private final List mResolveInfos = new ArrayList();
        public final ComponentName name;

        public ResolvedComponentInfo(ComponentName componentname, Intent intent, ResolveInfo resolveinfo)
        {
            name = componentname;
            add(intent, resolveinfo);
        }
    }

    public static interface TargetInfo
    {

        public abstract TargetInfo cloneFilledIn(Intent intent, int i);

        public abstract List getAllSourceIntents();

        public abstract CharSequence getBadgeContentDescription();

        public abstract Drawable getBadgeIcon();

        public abstract Drawable getDisplayIcon();

        public abstract CharSequence getDisplayLabel();

        public abstract CharSequence getExtendedInfo();

        public abstract ResolveInfo getResolveInfo();

        public abstract ComponentName getResolvedComponentName();

        public abstract Intent getResolvedIntent();

        public abstract boolean isPinned();

        public abstract boolean start(Activity activity, Bundle bundle);

        public abstract boolean startAsCaller(Activity activity, Bundle bundle, int i);

        public abstract boolean startAsUser(Activity activity, Bundle bundle, UserHandle userhandle);
    }

    static class ViewHolder
    {

        public ImageView badge;
        public ImageView icon;
        public TextView text;
        public TextView text2;

        public ViewHolder(View view)
        {
            text = (TextView)view.findViewById(0x1020014);
            text2 = (TextView)view.findViewById(0x1020015);
            icon = (ImageView)view.findViewById(0x1020006);
            badge = (ImageView)view.findViewById(0x110c002d);
        }
    }


    static Set _2D_get0()
    {
        return PRIV_PACKAGES;
    }

    static boolean _2D_get1(ResolverActivity resolveractivity)
    {
        return resolveractivity.mAlwaysUseOption;
    }

    static int _2D_get2(ResolverActivity resolveractivity)
    {
        return resolveractivity.mIconSize;
    }

    static int _2D_get3(ResolverActivity resolveractivity)
    {
        return resolveractivity.mMaskColor;
    }

    static Runnable _2D_get4(ResolverActivity resolveractivity)
    {
        return resolveractivity.mPostListReadyRunnable;
    }

    static View _2D_get5(ResolverActivity resolveractivity)
    {
        return resolveractivity.mProfileView;
    }

    static boolean _2D_get6(ResolverActivity resolveractivity)
    {
        return resolveractivity.mShowMoreResolverButton;
    }

    static boolean _2D_set0(ResolverActivity resolveractivity, boolean flag)
    {
        resolveractivity.mAlwaysUseOption = flag;
        return flag;
    }

    static PickTargetOptionRequest _2D_set1(ResolverActivity resolveractivity, PickTargetOptionRequest picktargetoptionrequest)
    {
        resolveractivity.mPickOptionRequest = picktargetoptionrequest;
        return picktargetoptionrequest;
    }

    static Runnable _2D_set2(ResolverActivity resolveractivity, Runnable runnable)
    {
        resolveractivity.mPostListReadyRunnable = runnable;
        return runnable;
    }

    static int _2D_set3(ResolverActivity resolveractivity, int i)
    {
        resolveractivity.mProfileSwitchMessageId = i;
        return i;
    }

    static void _2D_wrap0(ResolverActivity resolveractivity)
    {
        resolveractivity.initGridViews();
    }

    static void _2D_wrap1(ResolverActivity resolveractivity)
    {
        resolveractivity.showMore();
    }

    public ResolverActivity()
    {
        mLastSelected = -1;
        mResolvingHome = false;
        mProfileSwitchMessageId = -1;
        mShowMoreResolverButton = true;
    }

    public static int getLabelRes(String s)
    {
        return ActionTitle.forAction(s).labelRes;
    }

    private boolean hasManagedProfile()
    {
        Object obj;
        obj = (UserManager)getSystemService("user");
        if(obj == null)
            return false;
        obj = ((UserManager) (obj)).getProfiles(getUserId()).iterator();
_L1:
        UserInfo userinfo;
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_67;
        userinfo = (UserInfo)((Iterator) (obj)).next();
        if(userinfo != null)
        {
            boolean flag;
            try
            {
                flag = userinfo.isManagedProfile();
            }
            catch(SecurityException securityexception)
            {
                return false;
            }
            if(flag)
                return true;
        }
          goto _L1
        return false;
    }

    private GridView inflateGridView(int i)
    {
        GridView gridview = (GridView)LayoutInflater.from(this).inflate(0x11030012, mScreenView, false);
        gridview.setAdapter(new PageGridAdapter(this, i));
        ItemClickListener itemclicklistener = new ItemClickListener(i);
        gridview.setOnItemClickListener(itemclicklistener);
        gridview.setOnItemLongClickListener(itemclicklistener);
        return gridview;
    }

    private void initGridViews()
    {
        mScreenView.removeAllScreens();
        int i = mAdapter.getCount();
        int k = i / 8;
        if(i % 8 != 0)
            k++;
        mScreenView.removeAllViews();
        if(k == 1)
        {
            mScreenView.setSeekBarVisibility(8);
            GridView gridview = inflateGridView(k);
            gridview.setPadding(gridview.getPaddingLeft(), gridview.getPaddingTop(), gridview.getPaddingRight(), 0);
            gridview.setNumColumns(Math.min(gridview.getCount(), 4));
            mScreenView.addView(gridview);
        } else
        {
            android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-2, -2, 81);
            mScreenView.setSeekBarPosition(layoutparams);
            mScreenView.setSeekBarVisibility(0);
            int j = 0;
            while(j < k) 
            {
                GridView gridview1 = inflateGridView(j + 1);
                gridview1.setNumColumns(4);
                mScreenView.addView(gridview1);
                j++;
            }
        }
        mScreenView.setCurrentScreen(0);
    }

    static final boolean isSpecificUriMatch(int i)
    {
        boolean flag = false;
        i &= 0xfff0000;
        boolean flag1 = flag;
        if(i >= 0x300000)
        {
            flag1 = flag;
            if(i <= 0x500000)
                flag1 = true;
        }
        return flag1;
    }

    private Intent makeMyIntent()
    {
        Intent intent = new Intent(getIntent());
        intent.setComponent(null);
        intent.setFlags(intent.getFlags() & 0xff7fffff);
        return intent;
    }

    static boolean resolveInfoMatch(ResolveInfo resolveinfo, ResolveInfo resolveinfo1)
    {
        boolean flag = true;
        if(resolveinfo != null) goto _L2; else goto _L1
_L1:
        if(resolveinfo1 != null)
            flag = false;
_L4:
        return flag;
_L2:
        if(resolveinfo.activityInfo == null)
        {
            if(resolveinfo1.activityInfo != null)
                flag = false;
        } else
        if(Objects.equals(resolveinfo.activityInfo.name, resolveinfo1.activityInfo.name))
            flag = Objects.equals(resolveinfo.activityInfo.packageName, resolveinfo1.activityInfo.packageName);
        else
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void safelyStartActivityInternal(TargetInfo targetinfo)
    {
        if(mProfileSwitchMessageId != -1)
            Toast.makeText(this, getString(mProfileSwitchMessageId), 1).show();
        if(!mSafeForwardingMode)
        {
            if(targetinfo.start(this, null))
                onActivityStarted(targetinfo);
            return;
        }
        if(targetinfo.startAsCaller(this, null, -10000))
            onActivityStarted(targetinfo);
_L1:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        try
        {
            targetinfo = ActivityManagerNative.getDefault().getLaunchedFromPackage(getActivityToken());
        }
        // Misplaced declaration of an exception variable
        catch(TargetInfo targetinfo)
        {
            targetinfo = "??";
        }
        Slog.wtf("ResolverActivity", (new StringBuilder()).append("Unable to launch as uid ").append(mLaunchedFromUid).append(" package ").append(targetinfo).append(", while running in ").append(ActivityThread.currentProcessName()).toString(), runtimeexception);
          goto _L1
    }

    private void setAlwaysButtonEnabled(boolean flag, int i, boolean flag1)
    {
        boolean flag2 = false;
        if(flag)
        {
            ResolveInfo resolveinfo = mAdapter.resolveInfoForPosition(i, flag1);
            if(resolveinfo == null)
            {
                Log.e("ResolverActivity", "Invalid position supplied to setAlwaysButtonEnabled");
                return;
            }
            if(resolveinfo.targetUserId != -2)
            {
                Log.e("ResolverActivity", "Attempted to set selection to resolve info for another user");
                return;
            }
            flag2 = true;
        }
        mAlwaysButton.setEnabled(flag2);
    }

    private void setProfileSwitchMessageId(int i)
    {
        if(i == -2 || i == UserHandle.myUserId()) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag1;
        UserManager usermanager = (UserManager)getSystemService("user");
        UserInfo userinfo = usermanager.getUserInfo(i);
        if(userinfo != null)
            flag = userinfo.isManagedProfile();
        else
            flag = false;
        flag1 = usermanager.isManagedProfile();
        if(!flag || !(flag1 ^ true)) goto _L4; else goto _L3
_L3:
        mProfileSwitchMessageId = 0x110800f3;
_L2:
        return;
_L4:
        if(!flag && flag1)
            mProfileSwitchMessageId = 0x110800f4;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void showMore()
    {
        mShowMoreResolverButton = false;
        CheckBox checkbox = (CheckBox)findViewById(0x110c0032);
        if(checkbox != null && checkbox.getVisibility() == 0)
            checkbox.setChecked(false);
        mAdapter.handlePackagesChanged();
        initGridViews();
        for(int i = 0; i < mScreenView.getScreenCount(); i++)
            ((ArrayAdapter)((GridView)mScreenView.getScreen(i)).getAdapter()).notifyDataSetChanged();

    }

    private boolean supportsManagedProfiles(ResolveInfo resolveinfo)
    {
        boolean flag = false;
        int i;
        try
        {
            i = getPackageManager().getApplicationInfo(resolveinfo.activityInfo.packageName, 0).targetSdkVersion;
        }
        // Misplaced declaration of an exception variable
        catch(ResolveInfo resolveinfo)
        {
            return false;
        }
        if(i >= 21)
            flag = true;
        return flag;
    }

    private boolean useLayoutWithDefault()
    {
        boolean flag;
        if(mAlwaysUseOption)
            flag = mAdapter.hasFilteredItem();
        else
            flag = false;
        return flag;
    }

    void bindProfileView()
    {
        DisplayResolveInfo displayresolveinfo = mAdapter.getOtherProfile();
        if(displayresolveinfo != null)
        {
            mProfileView.setVisibility(0);
            ImageView imageview = (ImageView)mProfileView.findViewById(0x110c0030);
            TextView textview = (TextView)mProfileView.findViewById(0x1020014);
            if(!displayresolveinfo.hasDisplayIcon())
                (new LoadIconIntoViewTask(displayresolveinfo, imageview)).execute(new Void[0]);
            imageview.setImageDrawable(displayresolveinfo.getDisplayIcon());
            textview.setText(displayresolveinfo.getDisplayLabel());
        } else
        {
            mProfileView.setVisibility(8);
        }
    }

    public void configureContentView(List list, Intent aintent[], List list1)
    {
        mAlertParams.mView = getLayoutInflater().inflate(0x11030013, null);
        mScreenView = (ScreenView)mAlertParams.mView.findViewById(0x110c0031);
        mScreenView.setScreenTransitionType(1);
        mAlertParams.mNegativeButtonText = getResources().getString(0x1040000);
        mAlertParams.mNegativeButtonListener = new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                finish();
            }

            final ResolverActivity this$0;

            
            {
                this$0 = ResolverActivity.this;
                super();
            }
        }
;
        int i = mLaunchedFromUid;
        boolean flag;
        if(mAlwaysUseOption)
            flag = isVoiceInteraction() ^ true;
        else
            flag = false;
        mAdapter = createAdapter(this, list, aintent, list1, i, flag);
        flag = mAdapter.rebuildList();
        i = mAdapter.getUnfilteredCount();
        if(flag && i == 1 && mAdapter.getOtherProfile() == null)
        {
            list = mAdapter.targetInfoForPosition(0, false);
            if(shouldAutoLaunchSingleChoice(list))
            {
                safelyStartActivity(list);
                mPackageMonitor.unregister();
                mRegistered = false;
                finish();
                return;
            }
        }
        if(i == 0 && ResolveListAdapter._2D_get1(mAdapter) == 0)
        {
            mAlertParams.mMessage = getText(0x1108003d);
        } else
        {
            initGridViews();
            onPrepareAdapterView(null, mAdapter);
        }
    }

    public ResolveListAdapter createAdapter(Context context, List list, Intent aintent[], List list1, int i, boolean flag)
    {
        return new ResolveListAdapter(context, list, aintent, list1, i, flag, createListController());
    }

    protected ResolverListController createListController()
    {
        return new ResolverListController(this, mPm, getTargetIntent(), getReferrerPackageName(), mLaunchedFromUid);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(0, 0);
    }

    Drawable getIcon(Resources resources, int i)
    {
        try
        {
            resources = resources.getDrawableForDensity(i, mIconDpi);
        }
        // Misplaced declaration of an exception variable
        catch(Resources resources)
        {
            resources = null;
        }
        return resources;
    }

    public int getLayoutResource()
    {
        return 0;
    }

    protected String getReferrerPackageName()
    {
        Uri uri = getReferrer();
        if(uri != null && "android-app".equals(uri.getScheme()))
            return uri.getHost();
        else
            return null;
    }

    public Intent getReplacementIntent(ActivityInfo activityinfo, Intent intent)
    {
        return intent;
    }

    public Intent getTargetIntent()
    {
        Intent intent;
        if(mIntents.isEmpty())
            intent = null;
        else
            intent = (Intent)mIntents.get(0);
        return intent;
    }

    protected CharSequence getTitleForAction(String s, int i)
    {
        if(mResolvingHome)
            s = ActionTitle.HOME;
        else
            s = ActionTitle.forAction(s);
        if(s == ActionTitle.DEFAULT && i != 0)
            return getString(i);
        else
            return getString(((ActionTitle) (s)).titleRes);
    }

    Drawable loadIconForResolveInfo(ResolveInfo resolveinfo)
    {
        Drawable drawable;
        if(resolveinfo.resolvePackageName == null || resolveinfo.icon == 0)
            break MISSING_BLOCK_LABEL_40;
        drawable = getIcon(mPm.getResourcesForApplication(resolveinfo.resolvePackageName), resolveinfo.icon);
        if(drawable != null)
            return drawable;
        int i = resolveinfo.getIconResource();
        if(i == 0)
            break MISSING_BLOCK_LABEL_86;
        drawable = getIcon(mPm.getResourcesForApplication(resolveinfo.activityInfo.packageName), i);
        if(drawable != null)
            return drawable;
        break MISSING_BLOCK_LABEL_86;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        Log.e("ResolverActivity", "Couldn't find resources for package", namenotfoundexception);
        return resolveinfo.loadIcon(mPm);
    }

    public void onActivityStarted(TargetInfo targetinfo)
    {
    }

    protected void onCreate(Bundle bundle)
    {
        Intent intent = makeMyIntent();
        Set set = intent.getCategories();
        if("android.intent.action.MAIN".equals(intent.getAction()) && set != null && set.size() == 1 && set.contains("android.intent.category.HOME"))
            mResolvingHome = true;
        setSafeForwardingMode(true);
        onCreate(bundle, intent, null, 0, null, null, true);
    }

    protected void onCreate(Bundle bundle, Intent intent, CharSequence charsequence, int i, Intent aintent[], List list, boolean flag)
    {
        setTheme(miui.R.style.Theme_Light_Dialog_Alert);
        if(!"android.intent.action.VIEW".equals(intent.getAction()) && "android.intent.action.WEB_SEARCH".equals(intent.getAction()) ^ true)
            mShowMoreResolverButton = false;
        if(!mShowMoreResolverButton || aintent == null) goto _L2; else goto _L1
_L1:
        int j = 0;
_L7:
        if(j >= aintent.length) goto _L2; else goto _L3
_L3:
        Intent intent1 = aintent[j];
        if("android.intent.action.VIEW".equals(intent1.getAction()) || !("android.intent.action.WEB_SEARCH".equals(intent1.getAction()) ^ true)) goto _L5; else goto _L4
_L4:
        mShowMoreResolverButton = false;
_L2:
        BaseBundle.setShouldDefuse(true);
        super.onCreate(bundle);
        setProfileSwitchMessageId(intent.getContentUserHint());
        try
        {
            mLaunchedFromUid = ActivityManager.getService().getLaunchedFromUid(getActivityToken());
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            mLaunchedFromUid = -1;
        }
        if(mLaunchedFromUid < 0 || UserHandle.isIsolated(mLaunchedFromUid))
        {
            finish();
            return;
        }
        break; /* Loop/switch isn't completed */
_L5:
        j++;
        if(true) goto _L7; else goto _L6
_L6:
        mPm = getPackageManager();
        mPackageMonitor.register(this, getMainLooper(), false);
        mRegistered = true;
        mReferrerPackage = getReferrerPackageName();
        mAlwaysUseOption = flag;
        bundle = (ActivityManager)getSystemService("activity");
        mIconDpi = bundle.getLauncherLargeIconDensity();
        mIconSize = bundle.getLauncherLargeIconSize();
        mMaskColor = getResources().getColor(0x11060012);
        mIntents.add(0, new Intent(intent));
        mTitle = charsequence;
        mDefaultTitleResId = i;
        configureContentView(mIntents, aintent, list);
        if(XSpaceResolverActivityHelper.checkAndResolve(this, getIntent(), mAlertParams))
        {
            setupAlert();
            if(mRegistered)
            {
                mPackageMonitor.unregister();
                mRegistered = false;
            }
            return;
        }
        bundle = (ResolverDrawerLayout)findViewById(0x1020206);
        if(bundle != null)
        {
            bundle.setOnDismissedListener(new com.android.internal.widget.ResolverDrawerLayout.OnDismissedListener() {

                public void onDismissed()
                {
                    finish();
                }

                final ResolverActivity this$0;

            
            {
                this$0 = ResolverActivity.this;
                super();
            }
            }
);
            if(isVoiceInteraction())
                bundle.setCollapsed(false);
            mResolverDrawerLayout = bundle;
        }
        bundle = charsequence;
        if(charsequence == null)
            bundle = getTitleForAction(intent.getAction(), i);
        mAlertParams.mTitle = bundle;
        setupAlert();
        getWindow().clearFlags(0x20000);
        if(flag)
        {
            bundle = (CheckBox)findViewById(0x110c0032);
            if(bundle != null)
            {
                bundle.setVisibility(0);
                if(mShowMoreResolverButton && Build.IS_INTERNATIONAL_BUILD && mAdapter.mDisplayList != null && mAdapter.mDisplayList.size() > 1 && "com.android.browser".equals(DisplayResolveInfo._2D_get0((DisplayResolveInfo)mAdapter.mDisplayList.get(0)).activityInfo.packageName))
                    bundle.setChecked(true);
                else
                    bundle.setChecked(false);
            } else
            {
                mAlwaysUseOption = false;
            }
        }
        mProfileView = findViewById(0x110c002f);
        if(mProfileView != null)
        {
            mProfileView.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    view = mAdapter.getOtherProfile();
                    if(view == null)
                    {
                        return;
                    } else
                    {
                        ResolverActivity._2D_set3(ResolverActivity.this, -1);
                        onTargetSelected(view, false);
                        finish();
                        return;
                    }
                }

                final ResolverActivity this$0;

            
            {
                this$0 = ResolverActivity.this;
                super();
            }
            }
);
            bindProfileView();
        }
        if(isVoiceInteraction())
            onSetupVoiceInteraction();
        bundle = intent.getCategories();
        if(mAdapter.hasFilteredItem())
            i = 451;
        else
            i = 453;
        intent = (new StringBuilder()).append(intent.getAction()).append(":").append(intent.getType()).append(":");
        if(bundle != null)
            bundle = Arrays.toString(bundle.toArray());
        else
            bundle = "";
        MetricsLogger.action(this, i, intent.append(bundle).toString());
        return;
    }

    protected void onCreate(Bundle bundle, Intent intent, CharSequence charsequence, Intent aintent[], List list, boolean flag)
    {
        onCreate(bundle, intent, charsequence, 0, aintent, list, flag);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(!isChangingConfigurations() && mPickOptionRequest != null)
            mPickOptionRequest.cancel();
        if(mPostListReadyRunnable != null)
        {
            getMainThreadHandler().removeCallbacks(mPostListReadyRunnable);
            mPostListReadyRunnable = null;
        }
        if(mAdapter != null && ResolveListAdapter._2D_get2(mAdapter) != null)
            ResolveListAdapter._2D_get2(mAdapter).destroy();
    }

    public void onPrepareAdapterView(AbsListView abslistview, ResolveListAdapter resolvelistadapter)
    {
    }

    protected void onRestart()
    {
        super.onRestart();
        if(!mRegistered)
        {
            mPackageMonitor.register(this, getMainLooper(), false);
            mRegistered = true;
        }
        mAdapter.handlePackagesChanged();
        if(mProfileView != null)
            bindProfileView();
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        resetAlwaysOrOnceButtonBar();
    }

    public void onSetupVoiceInteraction()
    {
        sendVoiceChoicesIfNeeded();
    }

    protected void onStop()
    {
        super.onStop();
        if(mRegistered)
        {
            mPackageMonitor.unregister();
            mRegistered = false;
        }
        if((getIntent().getFlags() & 0x10000000) != 0 && isVoiceInteraction() ^ true && mResolvingHome ^ true && mRetainInOnStop ^ true && !isChangingConfigurations())
            finish();
    }

    protected boolean onTargetSelected(TargetInfo targetinfo, boolean flag)
    {
        ResolveInfo resolveinfo;
        Object obj;
        Object obj1;
        Object obj2;
        String s;
        Set set;
        resolveinfo = targetinfo.getResolveInfo();
        Iterator iterator;
        if(targetinfo != null)
            obj = targetinfo.getResolvedIntent();
        else
            obj = null;
        if(obj == null || !mAlwaysUseOption && !mAdapter.hasFilteredItem() || mAdapter.mOrigResolveList == null) goto _L2; else goto _L1
_L1:
        Object obj3;
        int i;
        Uri uri;
        obj1 = new IntentFilter();
        if(((Intent) (obj)).getSelector() != null)
            obj2 = ((Intent) (obj)).getSelector();
        else
            obj2 = obj;
        s = ((Intent) (obj2)).getAction();
        if(s != null)
            ((IntentFilter) (obj1)).addAction(s);
        set = ((Intent) (obj2)).getCategories();
        if(set != null)
            for(iterator = set.iterator(); iterator.hasNext(); ((IntentFilter) (obj1)).addCategory((String)iterator.next()));
        ((IntentFilter) (obj1)).addCategory("android.intent.category.DEFAULT");
        i = resolveinfo.match & 0xfff0000;
        uri = ((Intent) (obj2)).getData();
        obj3 = obj1;
        if(i != 0x600000)
            break MISSING_BLOCK_LABEL_221;
        obj2 = ((Intent) (obj2)).resolveType(this);
        obj3 = obj1;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_221;
        ((IntentFilter) (obj1)).addDataType(((String) (obj2)));
        obj3 = obj1;
_L3:
        int j;
        boolean flag1;
label0:
        {
label1:
            {
                if(uri == null || uri.getScheme() == null || i == 0x600000 && ("file".equals(uri.getScheme()) || !("content".equals(uri.getScheme()) ^ true)))
                    break label0;
                ((IntentFilter) (obj3)).addDataScheme(uri.getScheme());
                Iterator iterator1 = resolveinfo.filter.schemeSpecificPartsIterator();
                if(iterator1 == null)
                    break label1;
                obj2 = uri.getSchemeSpecificPart();
                do
                {
                    if(obj2 == null || !iterator1.hasNext())
                        break label1;
                    obj1 = (PatternMatcher)iterator1.next();
                } while(!((PatternMatcher) (obj1)).match(((String) (obj2))));
                ((IntentFilter) (obj3)).addDataSchemeSpecificPart(((PatternMatcher) (obj1)).getPath(), ((PatternMatcher) (obj1)).getType());
            }
            obj2 = resolveinfo.filter.authoritiesIterator();
            if(obj2 != null)
                do
                {
                    if(!((Iterator) (obj2)).hasNext())
                        break;
                    obj1 = (android.content.IntentFilter.AuthorityEntry)((Iterator) (obj2)).next();
                    if(((android.content.IntentFilter.AuthorityEntry) (obj1)).match(uri) < 0)
                        continue;
                    i = ((android.content.IntentFilter.AuthorityEntry) (obj1)).getPort();
                    obj1 = ((android.content.IntentFilter.AuthorityEntry) (obj1)).getHost();
                    if(i >= 0)
                        obj2 = Integer.toString(i);
                    else
                        obj2 = null;
                    ((IntentFilter) (obj3)).addDataAuthority(((String) (obj1)), ((String) (obj2)));
                    break;
                } while(true);
            obj2 = resolveinfo.filter.pathsIterator();
            if(obj2 == null)
                break label0;
            String s1 = uri.getPath();
            do
            {
                if(s1 == null || !((Iterator) (obj2)).hasNext())
                    break label0;
                obj1 = (PatternMatcher)((Iterator) (obj2)).next();
            } while(!((PatternMatcher) (obj1)).match(s1));
            ((IntentFilter) (obj3)).addDataPath(((PatternMatcher) (obj1)).getPath(), ((PatternMatcher) (obj1)).getType());
        }
        if(obj3 != null)
        {
            j = mAdapter.mOrigResolveList.size();
            if(ResolveListAdapter._2D_get0(mAdapter) != null)
                flag1 = true;
            else
                flag1 = false;
            if(!flag1)
                obj2 = new ComponentName[j];
            else
                obj2 = new ComponentName[j + 1];
            i = 0;
            for(int l = 0; l < j;)
            {
                obj1 = ((ResolvedComponentInfo)mAdapter.mOrigResolveList.get(l)).getResolveInfoAt(0);
                obj2[l] = new ComponentName(((ResolveInfo) (obj1)).activityInfo.packageName, ((ResolveInfo) (obj1)).activityInfo.name);
                int j1 = i;
                if(((ResolveInfo) (obj1)).match > i)
                    j1 = ((ResolveInfo) (obj1)).match;
                l++;
                i = j1;
            }

            break MISSING_BLOCK_LABEL_688;
        }
        break; /* Loop/switch isn't completed */
        obj3;
        Log.w("ResolverActivity", ((Throwable) (obj3)));
        obj3 = null;
        if(true) goto _L3; else goto _L2
        int i1;
        i1 = i;
        if(flag1)
        {
            obj2[j] = ResolveListAdapter._2D_get0(mAdapter).getResolvedComponentName();
            int k = ResolveListAdapter._2D_get0(mAdapter).getResolveInfo().match;
            i1 = i;
            if(k > i)
                i1 = k;
        }
        if(!flag) goto _L5; else goto _L4
_L4:
        i = getUserId();
        obj1 = getPackageManager();
        ((PackageManager) (obj1)).addPreferredActivity(((IntentFilter) (obj3)), i1, ((ComponentName []) (obj2)), ((Intent) (obj)).getComponent());
        if(!resolveinfo.handleAllWebDataURI) goto _L7; else goto _L6
_L6:
        if(TextUtils.isEmpty(((PackageManager) (obj1)).getDefaultBrowserPackageNameAsUser(i)))
            ((PackageManager) (obj1)).setDefaultBrowserPackageNameAsUser(resolveinfo.activityInfo.packageName, i);
_L2:
        if(targetinfo != null)
            safelyStartActivity(targetinfo);
        return true;
_L7:
        obj3 = ((Intent) (obj)).getComponent().getPackageName();
        boolean flag2;
        boolean flag3;
        if(uri != null)
            obj = uri.getScheme();
        else
            obj = null;
        if(obj != null)
        {
            if(!((String) (obj)).equals("http"))
                flag = ((String) (obj)).equals("https");
            else
                flag = true;
        } else
        {
            flag = false;
        }
        if(s != null)
            flag2 = s.equals("android.intent.action.VIEW");
        else
            flag2 = false;
        if(set != null)
            flag3 = set.contains("android.intent.category.BROWSABLE");
        else
            flag3 = false;
        if(flag && flag2 && flag3)
            ((PackageManager) (obj1)).updateIntentVerificationStatusAsUser(((String) (obj3)), 2, i);
        continue; /* Loop/switch isn't completed */
_L5:
        try
        {
            ResolveListAdapter._2D_get2(mAdapter).setLastChosen(((Intent) (obj)), ((IntentFilter) (obj3)), i1);
        }
        catch(RemoteException remoteexception)
        {
            Log.d("ResolverActivity", (new StringBuilder()).append("Error calling setLastChosenActivity\n").append(remoteexception).toString());
        }
        if(true) goto _L2; else goto _L8
_L8:
    }

    android.app.VoiceInteractor.PickOptionRequest.Option optionForChooserTarget(TargetInfo targetinfo, int i)
    {
        return new android.app.VoiceInteractor.PickOptionRequest.Option(targetinfo.getDisplayLabel(), i);
    }

    public void resetAlwaysOrOnceButtonBar()
    {
    }

    public void safelyStartActivity(TargetInfo targetinfo)
    {
        StrictMode.disableDeathOnFileUriExposure();
        safelyStartActivityInternal(targetinfo);
        StrictMode.enableDeathOnFileUriExposure();
        return;
        targetinfo;
        StrictMode.enableDeathOnFileUriExposure();
        throw targetinfo;
    }

    public void sendVoiceChoicesIfNeeded()
    {
        if(!isVoiceInteraction())
            return;
        android.app.VoiceInteractor.PickOptionRequest.Option aoption[] = new android.app.VoiceInteractor.PickOptionRequest.Option[mAdapter.getCount()];
        int i = 0;
        for(int j = aoption.length; i < j; i++)
            aoption[i] = optionForChooserTarget(mAdapter.getItem(i), i);

        mPickOptionRequest = new PickTargetOptionRequest(new android.app.VoiceInteractor.Prompt(getTitle()), aoption, null);
        getVoiceInteractor().submitRequest(mPickOptionRequest);
    }

    protected final void setAdditionalTargets(Intent aintent[])
    {
        if(aintent != null)
        {
            int i = 0;
            for(int j = aintent.length; i < j; i++)
            {
                Intent intent = aintent[i];
                mIntents.add(intent);
            }

        }
    }

    protected void setRetainInOnStop(boolean flag)
    {
        mRetainInOnStop = flag;
    }

    public void setSafeForwardingMode(boolean flag)
    {
        mSafeForwardingMode = flag;
    }

    public void setTitleAndIcon()
    {
    }

    public boolean shouldAutoLaunchSingleChoice(TargetInfo targetinfo)
    {
        return true;
    }

    public boolean shouldGetActivityMetadata()
    {
        return false;
    }

    public void showTargetDetails(ResolveInfo resolveinfo)
    {
        startActivity((new Intent()).setAction("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", resolveinfo.activityInfo.packageName, null)).addFlags(0x80000));
    }

    public void startSelected(int i, boolean flag, boolean flag1)
    {
        if(isFinishing())
            return;
        TargetInfo targetinfo = mAdapter.targetInfoForPosition(i, flag1);
        if(targetinfo == null)
            return;
        if((targetinfo instanceof DisplayResolveInfo) && Build.IS_INTERNATIONAL_BUILD && DisplayResolveInfo._2D_get1((DisplayResolveInfo)targetinfo))
        {
            showMore();
            return;
        }
        ResolveInfo resolveinfo = mAdapter.resolveInfoForPosition(i, flag1);
        if(mResolvingHome && hasManagedProfile() && supportsManagedProfiles(resolveinfo) ^ true)
        {
            Toast.makeText(this, String.format(getResources().getString(0x104005b), new Object[] {
                resolveinfo.activityInfo.loadLabel(getPackageManager()).toString()
            }), 1).show();
            return;
        }
        if(onTargetSelected(targetinfo, flag))
        {
            if(flag && mAlwaysUseOption)
                MetricsLogger.action(this, 455);
            else
            if(mAlwaysUseOption)
                MetricsLogger.action(this, 456);
            else
                MetricsLogger.action(this, 457);
            if(mAdapter.hasFilteredItem())
                i = 452;
            else
                i = 454;
            MetricsLogger.action(this, i);
            finish();
        }
    }

    private static final boolean DEBUG = false;
    private static final String EXTRA_RESOLVE_INFOS = "rlist";
    private static final int MAX_PER_SCREEN = 8;
    private static final Set PRIV_PACKAGES;
    private static final String TAG = "ResolverActivity";
    private final String PACKAGE_NAME_BROWSER = "com.android.browser";
    protected ResolveListAdapter mAdapter;
    private AbsListView mAdapterView;
    private Button mAlwaysButton;
    private boolean mAlwaysUseOption;
    private int mDefaultTitleResId;
    private ComponentName mFilteredComponents[];
    private int mIconDpi;
    private int mIconSize;
    private final ArrayList mIntents = new ArrayList();
    private int mLastSelected;
    protected int mLaunchedFromUid;
    private int mLayoutId;
    private int mMaskColor;
    private Button mOnceButton;
    private final PackageMonitor mPackageMonitor = new PackageMonitor() {

        public boolean onPackageChanged(String s, int i, String as[])
        {
            return true;
        }

        public void onSomePackagesChanged()
        {
            mAdapter.handlePackagesChanged();
            if(ResolverActivity._2D_get5(ResolverActivity.this) != null)
                bindProfileView();
        }

        final ResolverActivity this$0;

            
            {
                this$0 = ResolverActivity.this;
                super();
            }
    }
;
    private PickTargetOptionRequest mPickOptionRequest;
    protected PackageManager mPm;
    private Runnable mPostListReadyRunnable;
    private int mProfileSwitchMessageId;
    private View mProfileView;
    private String mReferrerPackage;
    private boolean mRegistered;
    protected ResolverDrawerLayout mResolverDrawerLayout;
    private boolean mResolvingHome;
    private boolean mRetainInOnStop;
    private boolean mSafeForwardingMode;
    private ScreenView mScreenView;
    private boolean mShowMoreResolverButton;
    private CharSequence mTitle;

    static 
    {
        PRIV_PACKAGES = new HashSet();
        PRIV_PACKAGES.add("com.miui.personalassistant.favorite");
        if(Build.IS_INTERNATIONAL_BUILD)
        {
            PRIV_PACKAGES.add("com.xiaomi.midrop");
            PRIV_PACKAGES.add("com.android.browser");
        }
    }
}
