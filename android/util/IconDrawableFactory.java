// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.content.Context;
import android.content.pm.*;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;

// Referenced classes of package android.util:
//            LauncherIcons

public class IconDrawableFactory
{

    private IconDrawableFactory(Context context, boolean flag)
    {
        mContext = context;
        mPm = context.getPackageManager();
        mUm = (UserManager)context.getSystemService(android/os/UserManager);
        mLauncherIcons = new LauncherIcons(context);
        mEmbedShadow = flag;
    }

    public static int getUserBadgeColor(UserManager usermanager, int i)
    {
        int j = usermanager.getManagedProfileBadge(i);
        i = j;
        if(j < 0)
            i = 0;
        i = CORP_BADGE_COLORS[i % CORP_BADGE_COLORS.length];
        return Resources.getSystem().getColor(i, null);
    }

    public static IconDrawableFactory newInstance(Context context)
    {
        return new IconDrawableFactory(context, true);
    }

    public static IconDrawableFactory newInstance(Context context, boolean flag)
    {
        return new IconDrawableFactory(context, flag);
    }

    public Drawable getBadgedIcon(ApplicationInfo applicationinfo)
    {
        return getBadgedIcon(applicationinfo, UserHandle.getUserId(applicationinfo.uid));
    }

    public Drawable getBadgedIcon(ApplicationInfo applicationinfo, int i)
    {
        return getBadgedIcon(((PackageItemInfo) (applicationinfo)), applicationinfo, i);
    }

    public Drawable getBadgedIcon(PackageItemInfo packageiteminfo, ApplicationInfo applicationinfo, int i)
    {
        packageiteminfo = mPm.loadUnbadgedItemIcon(packageiteminfo, applicationinfo);
        if(!mEmbedShadow && needsBadging(applicationinfo, i) ^ true)
            return packageiteminfo;
        Drawable drawable = getShadowedIcon(packageiteminfo);
        packageiteminfo = drawable;
        if(applicationinfo.isInstantApp())
        {
            int j = Resources.getSystem().getColor(0x10600a6, null);
            packageiteminfo = mLauncherIcons.getBadgedDrawable(drawable, 0x108037a, j);
        }
        applicationinfo = packageiteminfo;
        if(mUm.isManagedProfile(i))
            applicationinfo = mLauncherIcons.getBadgedDrawable(packageiteminfo, 0x108033c, getUserBadgeColor(mUm, i));
        return applicationinfo;
    }

    public Drawable getShadowedIcon(Drawable drawable)
    {
        return mLauncherIcons.wrapIconDrawableWithShadow(drawable);
    }

    protected boolean needsBadging(ApplicationInfo applicationinfo, int i)
    {
        boolean flag;
        if(!applicationinfo.isInstantApp())
            flag = mUm.isManagedProfile(i);
        else
            flag = true;
        return flag;
    }

    public static final int CORP_BADGE_COLORS[] = {
        0x106011f, 0x1060120, 0x1060121
    };
    protected final Context mContext;
    protected final boolean mEmbedShadow;
    protected final LauncherIcons mLauncherIcons;
    protected final PackageManager mPm;
    protected final UserManager mUm;

}
