// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import android.content.Context;
import android.content.pm.IPackageManager;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.*;

// Referenced classes of package miui.securityspace:
//            CrossUserUtilsCompat

public class XSpaceUserHandle
{

    public XSpaceUserHandle()
    {
    }

    public static int checkAndGetXSpaceUserId(int i, int j)
    {
        if(isXSpaceUserFlag(i))
            return 999;
        if(isXSpaceUserId(j))
            return j + 1;
        else
            return j;
    }

    public static Drawable getXSpaceIcon(Context context, Drawable drawable)
    {
        return getXSpaceIcon(context, drawable, new UserHandle(999));
    }

    public static Drawable getXSpaceIcon(Context context, Drawable drawable, int i)
    {
        return getXSpaceIcon(context, drawable, new UserHandle(UserHandle.getUserId(i)));
    }

    public static Drawable getXSpaceIcon(Context context, Drawable drawable, UserHandle userhandle)
    {
        if(isXSpaceUser(userhandle))
            return CrossUserUtilsCompat.getXSpaceIcon(context, drawable, userhandle);
        else
            return drawable;
    }

    public static boolean isAppInXSpace(Context context, String s)
    {
        boolean flag;
        for(flag = false; context == null || s == null || context.getApplicationContext() == null || context.getApplicationContext().getContentResolver() == null;)
            return false;

        try
        {
            context = android.content.pm.IPackageManager.Stub.asInterface(ServiceManager.getService("package")).getPackageInfo(s, 0, 999);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return false;
        }
        if(context != null)
            flag = true;
        return flag;
    }

    public static boolean isSelfXSpaceUser()
    {
        return isXSpaceUserId(UserHandle.getUserId(Process.myUid()));
    }

    public static boolean isUidBelongtoXSpace(int i)
    {
        return isXSpaceUserId(UserHandle.getUserId(i));
    }

    public static boolean isXSpaceUser(UserInfo userinfo)
    {
        boolean flag;
        if(userinfo != null)
            flag = isXSpaceUserFlag(userinfo.flags);
        else
            flag = false;
        return flag;
    }

    public static boolean isXSpaceUser(UserHandle userhandle)
    {
        boolean flag;
        if(userhandle != null)
            flag = isXSpaceUserId(userhandle.getIdentifier());
        else
            flag = false;
        return flag;
    }

    public static boolean isXSpaceUserCalling()
    {
        return isXSpaceUserId(UserHandle.getCallingUserId());
    }

    public static boolean isXSpaceUserFlag(int i)
    {
        boolean flag;
        if((i & 0x800000) == 0x800000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isXSpaceUserId(int i)
    {
        boolean flag;
        if(i == 999)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final String EXTRA_AUTH_CALL_XSPACE = "android.intent.extra.auth_to_call_xspace";
    public static final int FLAG_XSPACE_PROFILE = 0x800000;
    public static final int OWNER_SHARED_USER_GID;
    public static final int USER_XSPACE = 999;
    public static final int XSPACE_ICON_MASK_ID = 0x11020066;
    public static final int XSPACE_SHARED_USER_GID;

    static 
    {
        OWNER_SHARED_USER_GID = CrossUserUtilsCompat.OWNER_SHARED_USER_GID;
        XSPACE_SHARED_USER_GID = CrossUserUtilsCompat.XSPACE_SHARED_USER_GID;
    }
}
