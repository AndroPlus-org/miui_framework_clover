// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import android.app.Activity;
import android.app.MiuiThemeHelper;
import android.content.*;
import android.content.pm.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import java.util.Iterator;

// Referenced classes of package miui.securityspace:
//            CrossUserUtils, XSpaceUserHandle

class CrossUserUtilsCompat
{

    CrossUserUtilsCompat()
    {
    }

    public static Uri addUserIdForUri(Uri uri, int i)
    {
        if(i != -1)
            uri = ContentProvider.maybeAddUserId(uri, i);
        return uri;
    }

    public static Uri addUserIdForUri(Uri uri, Context context, String s, Intent intent)
    {
        int i = intent.getIntExtra("android.intent.extra.picked_user_id", -1);
        if(i != -1 && checkUidPermission(context, s))
            return addUserIdForUri(uri, i);
        else
            return uri;
    }

    public static boolean checkUidPermission(Context context, String s)
    {
        context = context.getPackageManager().getApplicationInfo(s, 0);
        if(context == null)
            break MISSING_BLOCK_LABEL_38;
        int i = UserHandle.getAppId(((ApplicationInfo) (context)).uid);
        return i <= 1000;
        context;
        context.printStackTrace();
_L2:
        return false;
        context;
        context.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Drawable getOriginalAppIcon(Context context, String s)
    {
        PackageManager packagemanager = context.getPackageManager();
        ApplicationInfo applicationinfo;
        try
        {
            applicationinfo = packagemanager.getApplicationInfo(s, 0);
            s = MiuiThemeHelper.getDrawable(packagemanager, s, applicationinfo.name, applicationinfo.icon, applicationinfo);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return null;
        }
        context = s;
        if(s != null)
            break MISSING_BLOCK_LABEL_40;
        context = packagemanager.loadUnbadgedItemIcon(applicationinfo, applicationinfo);
        return context;
    }

    public static Drawable getXSpaceIcon(Context context, Drawable drawable, UserHandle userhandle)
    {
        PackageManager packagemanager = context.getPackageManager();
        Drawable drawable1 = drawable;
        if(drawable instanceof BitmapDrawable)
            drawable1 = CrossUserUtils.createDrawableWithCache(context, ((BitmapDrawable)drawable).getBitmap());
        return packagemanager.getUserBadgedIcon(drawable1, userhandle);
    }

    static boolean hasAirSpace(Context context)
    {
        for(context = ((UserManager)context.getSystemService("user")).getUsers().iterator(); context.hasNext();)
            if(((UserInfo)context.next()).isAirSpace())
                return true;

        return false;
    }

    static boolean hasSecondSpace(Context context)
    {
        boolean flag;
        if(android.provider.Settings.Secure.getInt(context.getContentResolver(), "second_user_id", -10000) != -10000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static boolean hasSpace(Context context)
    {
        context = ((UserManager)context.getSystemService("user")).getUsers().iterator();
        do
        {
            if(!context.hasNext())
                break;
            UserInfo userinfo = (UserInfo)context.next();
            if(userinfo.id != 99)
                XSpaceUserHandle.isXSpaceUserId(userinfo.id);
        } while(true);
        return false;
    }

    static boolean hasXSpaceUser(Context context)
    {
        for(context = ((UserManager)context.getSystemService("user")).getProfiles(0).iterator(); context.hasNext();)
            if(XSpaceUserHandle.isXSpaceUser((UserInfo)context.next()))
                return true;

        return false;
    }

    static boolean isAirSpace(Context context, int i)
    {
        boolean flag = false;
        context = ((UserManager)context.getSystemService("user")).getUserInfo(i);
        if(context == null)
            return false;
        if((((UserInfo) (context)).flags & 0x400000) == 0x400000)
            flag = true;
        return flag;
    }

    public static void startActivityAsCaller(Activity activity, Intent intent, Bundle bundle, boolean flag, int i)
    {
        StrictMode.disableDeathOnFileUriExposure();
        activity.startActivityAsCaller(intent, bundle, flag, i);
        StrictMode.enableDeathOnFileUriExposure();
        return;
        activity;
        StrictMode.enableDeathOnFileUriExposure();
        throw activity;
    }

    public static final int AIRLOCK_USER_ID = 99;
    public static final int FLAG_XSPACE_PROFILE = 0x800000;
    public static final int OWNER_SHARED_USER_GID = UserHandle.getUserGid(0);
    public static final int XSPACE_SHARED_USER_GID = UserHandle.getUserGid(999);

}
