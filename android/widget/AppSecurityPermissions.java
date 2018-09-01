// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.*;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.UserHandle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import java.text.Collator;
import java.util.*;

// Referenced classes of package android.widget:
//            LinearLayout, TextView, ImageView

public class AppSecurityPermissions
{
    static class MyPermissionGroupInfo extends PermissionGroupInfo
    {

        public Drawable loadGroupIcon(Context context, PackageManager packagemanager)
        {
            if(icon != 0)
                return loadUnbadgedIcon(packagemanager);
            else
                return context.getDrawable(0x10804a5);
        }

        final ArrayList mAllPermissions;
        CharSequence mLabel;
        final ArrayList mNewPermissions;

        MyPermissionGroupInfo(PermissionGroupInfo permissiongroupinfo)
        {
            super(permissiongroupinfo);
            mNewPermissions = new ArrayList();
            mAllPermissions = new ArrayList();
        }

        MyPermissionGroupInfo(PermissionInfo permissioninfo)
        {
            mNewPermissions = new ArrayList();
            mAllPermissions = new ArrayList();
            name = permissioninfo.packageName;
            packageName = permissioninfo.packageName;
        }
    }

    private static class MyPermissionInfo extends PermissionInfo
    {

        int mExistingReqFlags;
        CharSequence mLabel;
        boolean mNew;
        int mNewReqFlags;

        MyPermissionInfo(PermissionInfo permissioninfo)
        {
            super(permissioninfo);
        }
    }

    private static class PermissionGroupInfoComparator
        implements Comparator
    {

        public final int compare(MyPermissionGroupInfo mypermissiongroupinfo, MyPermissionGroupInfo mypermissiongroupinfo1)
        {
            return sCollator.compare(mypermissiongroupinfo.mLabel, mypermissiongroupinfo1.mLabel);
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((MyPermissionGroupInfo)obj, (MyPermissionGroupInfo)obj1);
        }

        private final Collator sCollator;

        private PermissionGroupInfoComparator()
        {
            sCollator = Collator.getInstance();
        }

        PermissionGroupInfoComparator(PermissionGroupInfoComparator permissiongroupinfocomparator)
        {
            this();
        }
    }

    private static class PermissionInfoComparator
        implements Comparator
    {

        public final int compare(MyPermissionInfo mypermissioninfo, MyPermissionInfo mypermissioninfo1)
        {
            return sCollator.compare(mypermissioninfo.mLabel, mypermissioninfo1.mLabel);
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((MyPermissionInfo)obj, (MyPermissionInfo)obj1);
        }

        private final Collator sCollator = Collator.getInstance();

        PermissionInfoComparator()
        {
        }
    }

    public static class PermissionItemView extends LinearLayout
        implements android.view.View.OnClickListener
    {

        static Context _2D_get0(PermissionItemView permissionitemview)
        {
            return permissionitemview.mContext;
        }

        static String _2D_get1(PermissionItemView permissionitemview)
        {
            return permissionitemview.mPackageName;
        }

        private void addRevokeUIIfNecessary(android.app.AlertDialog.Builder builder)
        {
            if(!mShowRevokeUI)
                return;
            boolean flag;
            if((mPerm.mExistingReqFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                return;
            } else
            {
                builder.setNegativeButton(0x1040580, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        getContext().getPackageManager().revokeRuntimePermission(PermissionItemView._2D_get1(PermissionItemView.this), mPerm.name, new UserHandle(PermissionItemView._2D_get0(PermissionItemView.this).getUserId()));
                        setVisibility(8);
                    }

                    final PermissionItemView this$1;

            
            {
                this$1 = PermissionItemView.this;
                super();
            }
                }
);
                builder.setPositiveButton(0x104000a, null);
                return;
            }
        }

        public void onClick(View view)
        {
            if(mGroup != null && mPerm != null)
            {
                if(mDialog != null)
                    mDialog.dismiss();
                PackageManager packagemanager = getContext().getPackageManager();
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                builder.setTitle(mGroup.mLabel);
                if(mPerm.descriptionRes != 0)
                {
                    builder.setMessage(mPerm.loadDescription(packagemanager));
                } else
                {
                    StringBuilder stringbuilder;
                    try
                    {
                        view = packagemanager.getApplicationInfo(mPerm.packageName, 0).loadLabel(packagemanager);
                    }
                    // Misplaced declaration of an exception variable
                    catch(View view)
                    {
                        view = mPerm.packageName;
                    }
                    stringbuilder = new StringBuilder(128);
                    stringbuilder.append(getContext().getString(0x104050f, new Object[] {
                        view
                    }));
                    stringbuilder.append("\n\n");
                    stringbuilder.append(mPerm.name);
                    builder.setMessage(stringbuilder.toString());
                }
                builder.setCancelable(true);
                builder.setIcon(mGroup.loadGroupIcon(getContext(), packagemanager));
                addRevokeUIIfNecessary(builder);
                mDialog = builder.show();
                mDialog.setCanceledOnTouchOutside(true);
            }
        }

        protected void onDetachedFromWindow()
        {
            super.onDetachedFromWindow();
            if(mDialog != null)
                mDialog.dismiss();
        }

        public void setPermission(MyPermissionGroupInfo mypermissiongroupinfo, MyPermissionInfo mypermissioninfo, boolean flag, CharSequence charsequence, String s, boolean flag1)
        {
            mGroup = mypermissiongroupinfo;
            mPerm = mypermissioninfo;
            mShowRevokeUI = flag1;
            mPackageName = s;
            ImageView imageview = (ImageView)findViewById(0x1020366);
            TextView textview = (TextView)findViewById(0x1020369);
            Object obj = getContext().getPackageManager();
            s = null;
            if(flag)
                s = mypermissiongroupinfo.loadGroupIcon(getContext(), ((PackageManager) (obj)));
            obj = mypermissioninfo.mLabel;
            mypermissiongroupinfo = ((MyPermissionGroupInfo) (obj));
            if(mypermissioninfo.mNew)
            {
                mypermissiongroupinfo = ((MyPermissionGroupInfo) (obj));
                if(charsequence != null)
                {
                    mypermissiongroupinfo = new SpannableStringBuilder();
                    mypermissioninfo = Parcel.obtain();
                    TextUtils.writeToParcel(charsequence, mypermissioninfo, 0);
                    mypermissioninfo.setDataPosition(0);
                    charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(mypermissioninfo);
                    mypermissioninfo.recycle();
                    mypermissiongroupinfo.append(charsequence);
                    mypermissiongroupinfo.append(((CharSequence) (obj)));
                }
            }
            imageview.setImageDrawable(s);
            textview.setText(mypermissiongroupinfo);
            setOnClickListener(this);
        }

        AlertDialog mDialog;
        MyPermissionGroupInfo mGroup;
        private String mPackageName;
        MyPermissionInfo mPerm;
        private boolean mShowRevokeUI;

        public PermissionItemView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mShowRevokeUI = false;
            setClickable(true);
        }
    }


    private AppSecurityPermissions(Context context)
    {
        mPermGroups = new HashMap();
        mPermGroupsList = new ArrayList();
        mPermGroupComparator = new PermissionGroupInfoComparator(null);
        mPermComparator = new PermissionInfoComparator();
        mPermsList = new ArrayList();
        mContext = context;
        mInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
        mPm = mContext.getPackageManager();
        mNewPermPrefix = mContext.getText(0x1040510);
    }

    public AppSecurityPermissions(Context context, PackageInfo packageinfo)
    {
        HashSet hashset;
        this(context);
        hashset = new HashSet();
        if(packageinfo == null)
            return;
        mPackageName = packageinfo.packageName;
        context = null;
        if(packageinfo.requestedPermissions == null) goto _L2; else goto _L1
_L1:
        PackageInfo packageinfo1 = mPm.getPackageInfo(packageinfo.packageName, 4096);
        context = packageinfo1;
_L4:
        extractPerms(packageinfo, hashset, context);
_L2:
        if(packageinfo.sharedUserId != null)
            try
            {
                getAllUsedPermissions(mPm.getUidForSharedUser(packageinfo.sharedUserId), hashset);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.w("AppSecurityPermissions", (new StringBuilder()).append("Couldn't retrieve shared user id for: ").append(packageinfo.packageName).toString());
            }
        mPermsList.addAll(hashset);
        setPermissions(mPermsList);
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public AppSecurityPermissions(Context context, String s)
    {
        this(context);
        mPackageName = s;
        context = new HashSet();
        PackageInfo packageinfo;
        try
        {
            packageinfo = mPm.getPackageInfo(s, 4096);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.w("AppSecurityPermissions", (new StringBuilder()).append("Couldn't retrieve permissions for package:").append(s).toString());
            return;
        }
        if(packageinfo.applicationInfo != null && packageinfo.applicationInfo.uid != -1)
            getAllUsedPermissions(packageinfo.applicationInfo.uid, context);
        mPermsList.addAll(context);
        setPermissions(mPermsList);
    }

    private void addPermToList(List list, MyPermissionInfo mypermissioninfo)
    {
        if(mypermissioninfo.mLabel == null)
            mypermissioninfo.mLabel = mypermissioninfo.loadLabel(mPm);
        int i = Collections.binarySearch(list, mypermissioninfo, mPermComparator);
        if(i < 0)
            list.add(-i - 1, mypermissioninfo);
    }

    private void displayPermissions(List list, LinearLayout linearlayout, int i, boolean flag)
    {
        linearlayout.removeAllViews();
        int j = (int)(mContext.getResources().getDisplayMetrics().density * 8F);
        for(int k = 0; k < list.size(); k++)
        {
            MyPermissionGroupInfo mypermissiongroupinfo = (MyPermissionGroupInfo)list.get(k);
            List list1 = getPermissionList(mypermissiongroupinfo, i);
            int l = 0;
            while(l < list1.size()) 
            {
                Object obj = (MyPermissionInfo)list1.get(l);
                boolean flag1;
                Object obj1;
                if(l == 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(i != 4)
                    obj1 = mNewPermPrefix;
                else
                    obj1 = null;
                obj1 = getPermissionItemView(mypermissiongroupinfo, ((MyPermissionInfo) (obj)), flag1, ((CharSequence) (obj1)), flag);
                obj = new LinearLayout.LayoutParams(-1, -2);
                if(l == 0)
                    obj.topMargin = j;
                if(l == mypermissiongroupinfo.mAllPermissions.size() - 1)
                    obj.bottomMargin = j;
                if(linearlayout.getChildCount() == 0)
                    obj.topMargin = ((LinearLayout.LayoutParams) (obj)).topMargin * 2;
                linearlayout.addView(((View) (obj1)), ((android.view.ViewGroup.LayoutParams) (obj)));
                l++;
            }
        }

    }

    private void extractPerms(PackageInfo packageinfo, Set set, PackageInfo packageinfo1)
    {
        String as[];
        int ai[];
        int i;
        as = packageinfo.requestedPermissions;
        ai = packageinfo.requestedPermissionsFlags;
        if(as == null || as.length == 0)
            return;
        i = 0;
_L2:
        String s;
        if(i >= as.length)
            break MISSING_BLOCK_LABEL_410;
        s = as[i];
        PermissionInfo permissioninfo = mPm.getPermissionInfo(s, 0);
        if(permissioninfo != null)
            break; /* Loop/switch isn't completed */
_L12:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        byte byte0;
        int j;
        byte0 = -1;
        j = byte0;
        if(packageinfo1 == null) goto _L4; else goto _L3
_L3:
        j = byte0;
        if(packageinfo1.requestedPermissions == null) goto _L4; else goto _L5
_L5:
        int k = 0;
_L15:
        j = byte0;
        if(k >= packageinfo1.requestedPermissions.length) goto _L4; else goto _L6
_L6:
        if(!s.equals(packageinfo1.requestedPermissions[k])) goto _L8; else goto _L7
_L7:
        j = k;
_L4:
        if(j < 0) goto _L10; else goto _L9
_L9:
        k = packageinfo1.requestedPermissionsFlags[j];
_L16:
        if(!isDisplayablePermission(permissioninfo, ai[i], k)) goto _L12; else goto _L11
_L11:
        Object obj = permissioninfo.group;
        packageinfo = ((PackageInfo) (obj));
        if(obj != null)
            break MISSING_BLOCK_LABEL_180;
        packageinfo = permissioninfo.packageName;
        permissioninfo.group = packageinfo;
        if((MyPermissionGroupInfo)mPermGroups.get(packageinfo) != null) goto _L14; else goto _L13
_L13:
        packageinfo = null;
        if(obj == null)
            break MISSING_BLOCK_LABEL_214;
        packageinfo = mPm.getPermissionGroupInfo(((String) (obj)), 0);
        if(packageinfo == null)
            break MISSING_BLOCK_LABEL_347;
        obj = JVM INSTR new #6   <Class AppSecurityPermissions$MyPermissionGroupInfo>;
        ((MyPermissionGroupInfo) (obj)).MyPermissionGroupInfo(packageinfo);
        packageinfo = ((PackageInfo) (obj));
_L17:
        mPermGroups.put(permissioninfo.group, packageinfo);
_L14:
        boolean flag;
        if(packageinfo1 != null)
        {
            if((k & 2) == 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        try
        {
            packageinfo = JVM INSTR new #9   <Class AppSecurityPermissions$MyPermissionInfo>;
            packageinfo.MyPermissionInfo(permissioninfo);
            packageinfo.mNewReqFlags = ai[i];
            packageinfo.mExistingReqFlags = k;
            packageinfo.mNew = flag;
            set.add(packageinfo);
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo)
        {
            Log.i("AppSecurityPermissions", (new StringBuilder()).append("Ignoring unknown permission:").append(s).toString());
        }
          goto _L12
_L8:
        k++;
          goto _L15
_L10:
        k = 0;
          goto _L16
        permissioninfo.group = permissioninfo.packageName;
        if((MyPermissionGroupInfo)mPermGroups.get(permissioninfo.group) == null)
            new MyPermissionGroupInfo(permissioninfo);
        packageinfo = new MyPermissionGroupInfo(permissioninfo);
          goto _L17
          goto _L12
    }

    private void getAllUsedPermissions(int i, Set set)
    {
        boolean flag = false;
        String as[] = mPm.getPackagesForUid(i);
        if(as == null || as.length == 0)
            return;
        int j = as.length;
        for(i = ((flag) ? 1 : 0); i < j; i++)
            getPermissionsForPackage(as[i], set);

    }

    public static View getPermissionItemView(Context context, CharSequence charsequence, CharSequence charsequence1, boolean flag)
    {
        LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
        int i;
        if(flag)
            i = 0x108030f;
        else
            i = 0x10804e8;
        return getPermissionItemViewOld(context, layoutinflater, charsequence, charsequence1, flag, context.getDrawable(i));
    }

    private static PermissionItemView getPermissionItemView(Context context, LayoutInflater layoutinflater, MyPermissionGroupInfo mypermissiongroupinfo, MyPermissionInfo mypermissioninfo, boolean flag, CharSequence charsequence, String s, boolean flag1)
    {
        int i;
        if((mypermissioninfo.flags & 1) != 0)
            i = 0x1090038;
        else
            i = 0x1090037;
        context = (PermissionItemView)layoutinflater.inflate(i, null);
        context.setPermission(mypermissiongroupinfo, mypermissioninfo, flag, charsequence, s, flag1);
        return context;
    }

    private PermissionItemView getPermissionItemView(MyPermissionGroupInfo mypermissiongroupinfo, MyPermissionInfo mypermissioninfo, boolean flag, CharSequence charsequence, boolean flag1)
    {
        return getPermissionItemView(mContext, mInflater, mypermissiongroupinfo, mypermissioninfo, flag, charsequence, mPackageName, flag1);
    }

    private static View getPermissionItemViewOld(Context context, LayoutInflater layoutinflater, CharSequence charsequence, CharSequence charsequence1, boolean flag, Drawable drawable)
    {
        View view = layoutinflater.inflate(0x1090039, null);
        context = (TextView)view.findViewById(0x102036a);
        layoutinflater = (TextView)view.findViewById(0x102036c);
        ((ImageView)view.findViewById(0x1020366)).setImageDrawable(drawable);
        if(charsequence != null)
        {
            context.setText(charsequence);
            layoutinflater.setText(charsequence1);
        } else
        {
            context.setText(charsequence1);
            layoutinflater.setVisibility(8);
        }
        return view;
    }

    private List getPermissionList(MyPermissionGroupInfo mypermissiongroupinfo, int i)
    {
        if(i == 4)
            return mypermissiongroupinfo.mNewPermissions;
        else
            return mypermissiongroupinfo.mAllPermissions;
    }

    private void getPermissionsForPackage(String s, Set set)
    {
        PackageInfo packageinfo = mPm.getPackageInfo(s, 4096);
        extractPerms(packageinfo, set, packageinfo);
_L1:
        return;
        set;
        Log.w("AppSecurityPermissions", (new StringBuilder()).append("Couldn't retrieve permissions for package: ").append(s).toString());
          goto _L1
    }

    private View getPermissionsView(int i, boolean flag)
    {
        LinearLayout linearlayout = (LinearLayout)mInflater.inflate(0x109003a, null);
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x1020371);
        View view = linearlayout.findViewById(0x102032a);
        displayPermissions(mPermGroupsList, linearlayout1, i, flag);
        if(linearlayout1.getChildCount() <= 0)
            view.setVisibility(0);
        return linearlayout;
    }

    private boolean isDisplayablePermission(PermissionInfo permissioninfo, int i, int j)
    {
        int k = permissioninfo.protectionLevel & 0xf;
        boolean flag1;
        if(k == 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            return false;
        boolean flag;
        boolean flag2;
        if(k != 1)
        {
            if((permissioninfo.protectionLevel & 0x80) != 0)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        if((i & 1) != 0)
            flag = true;
        else
            flag = false;
        if((permissioninfo.protectionLevel & 0x20) != 0)
            flag2 = true;
        else
            flag2 = false;
        if((j & 2) != 0)
            j = 1;
        else
            j = 0;
        if((i & 2) != 0)
            i = 1;
        else
            i = 0;
        if(flag1 && (flag || j != 0 || i != 0))
            return true;
        return flag2 && j != 0;
    }

    private void setPermissions(List list)
    {
        if(list != null)
        {
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                list = (MyPermissionInfo)iterator.next();
                if(isDisplayablePermission(list, ((MyPermissionInfo) (list)).mNewReqFlags, ((MyPermissionInfo) (list)).mExistingReqFlags))
                {
                    MyPermissionGroupInfo mypermissiongroupinfo = (MyPermissionGroupInfo)mPermGroups.get(((MyPermissionInfo) (list)).group);
                    if(mypermissiongroupinfo != null)
                    {
                        list.mLabel = list.loadLabel(mPm);
                        addPermToList(mypermissiongroupinfo.mAllPermissions, list);
                        if(((MyPermissionInfo) (list)).mNew)
                            addPermToList(mypermissiongroupinfo.mNewPermissions, list);
                    }
                }
            } while(true);
        }
        Iterator iterator1 = mPermGroups.values().iterator();
        while(iterator1.hasNext()) 
        {
            list = (MyPermissionGroupInfo)iterator1.next();
            if(((MyPermissionGroupInfo) (list)).labelRes != 0 || ((MyPermissionGroupInfo) (list)).nonLocalizedLabel != null)
                list.mLabel = list.loadLabel(mPm);
            else
                try
                {
                    list.mLabel = mPm.getApplicationInfo(((MyPermissionGroupInfo) (list)).packageName, 0).loadLabel(mPm);
                }
                catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                {
                    list.mLabel = list.loadLabel(mPm);
                }
            mPermGroupsList.add(list);
        }
        Collections.sort(mPermGroupsList, mPermGroupComparator);
    }

    public int getPermissionCount()
    {
        return getPermissionCount(65535);
    }

    public int getPermissionCount(int i)
    {
        int j = 0;
        for(int k = 0; k < mPermGroupsList.size(); k++)
            j += getPermissionList((MyPermissionGroupInfo)mPermGroupsList.get(k), i).size();

        return j;
    }

    public View getPermissionsView()
    {
        return getPermissionsView(65535, false);
    }

    public View getPermissionsView(int i)
    {
        return getPermissionsView(i, false);
    }

    public View getPermissionsViewWithRevokeButtons()
    {
        return getPermissionsView(65535, true);
    }

    private static final String TAG = "AppSecurityPermissions";
    public static final int WHICH_ALL = 65535;
    public static final int WHICH_NEW = 4;
    private static final boolean localLOGV = false;
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final CharSequence mNewPermPrefix;
    private String mPackageName;
    private final PermissionInfoComparator mPermComparator;
    private final PermissionGroupInfoComparator mPermGroupComparator;
    private final Map mPermGroups;
    private final List mPermGroupsList;
    private final List mPermsList;
    private final PackageManager mPm;
}
