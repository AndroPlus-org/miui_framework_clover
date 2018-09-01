// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

// Referenced classes of package android.content.pm:
//            ActivityInfo, ApplicationInfo, PackageManager, PackageInfo

public class LauncherActivityInfo
{

    LauncherActivityInfo(Context context)
    {
        mPm = context.getPackageManager();
    }

    LauncherActivityInfo(Context context, ActivityInfo activityinfo, UserHandle userhandle)
    {
        this(context);
        mActivityInfo = activityinfo;
        mComponentName = new ComponentName(activityinfo.packageName, activityinfo.name);
        mUser = userhandle;
    }

    public int getApplicationFlags()
    {
        return mActivityInfo.applicationInfo.flags;
    }

    public ApplicationInfo getApplicationInfo()
    {
        return mActivityInfo.applicationInfo;
    }

    public Drawable getBadgedIcon(int i)
    {
        Drawable drawable = getIcon(i);
        return mPm.getUserBadgedIcon(drawable, mUser);
    }

    public ComponentName getComponentName()
    {
        return mComponentName;
    }

    public long getFirstInstallTime()
    {
        long l;
        try
        {
            l = mPm.getPackageInfo(mActivityInfo.packageName, 8192).firstInstallTime;
        }
        catch(PackageManager.NameNotFoundException namenotfoundexception)
        {
            return 0L;
        }
        return l;
    }

    public Drawable getIcon(int i)
    {
        int j;
        Drawable drawable;
        Object obj;
        j = mActivityInfo.getIconResource();
        drawable = null;
        obj = drawable;
        if(i == 0)
            break MISSING_BLOCK_LABEL_45;
        obj = drawable;
        if(j == 0)
            break MISSING_BLOCK_LABEL_45;
        try
        {
            obj = mPm.getResourcesForApplication(mActivityInfo.applicationInfo).getDrawableForDensity(j, i);
        }
        catch(PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception = drawable;
        }
        drawable = ((Drawable) (obj));
        if(obj == null)
            drawable = mActivityInfo.loadIcon(mPm);
        return drawable;
    }

    public CharSequence getLabel()
    {
        return mActivityInfo.loadLabel(mPm);
    }

    public String getName()
    {
        return mActivityInfo.name;
    }

    public UserHandle getUser()
    {
        return mUser;
    }

    private static final String TAG = "LauncherActivityInfo";
    private ActivityInfo mActivityInfo;
    private ComponentName mComponentName;
    private final PackageManager mPm;
    private UserHandle mUser;
}
