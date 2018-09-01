// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import android.content.pm.*;
import android.os.UserManager;
import java.util.*;

public class UserPackage
{

    public UserPackage(UserInfo userinfo, PackageInfo packageinfo)
    {
        mUserInfo = userinfo;
        mPackageInfo = packageinfo;
    }

    private static List getAllUsers(Context context)
    {
        return ((UserManager)context.getSystemService("user")).getUsers(false);
    }

    public static List getPackageInfosAllUsers(Context context, String s, int i)
    {
        ArrayList arraylist;
        Iterator iterator;
        List list = getAllUsers(context);
        arraylist = new ArrayList(list.size());
        iterator = list.iterator();
_L2:
        PackageInfo packageinfo;
        UserInfo userinfo;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        userinfo = (UserInfo)iterator.next();
        packageinfo = null;
        PackageInfo packageinfo1 = context.getPackageManager().getPackageInfoAsUser(s, i, userinfo.id);
        packageinfo = packageinfo1;
_L3:
        arraylist.add(new UserPackage(userinfo, packageinfo));
        if(true) goto _L2; else goto _L1
_L1:
        return arraylist;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
          goto _L3
    }

    public static boolean hasCorrectTargetSdkVersion(PackageInfo packageinfo)
    {
        boolean flag;
        if(packageinfo.applicationInfo.targetSdkVersion >= 27)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public PackageInfo getPackageInfo()
    {
        return mPackageInfo;
    }

    public UserInfo getUserInfo()
    {
        return mUserInfo;
    }

    public boolean isEnabledPackage()
    {
        if(mPackageInfo == null)
            return false;
        else
            return mPackageInfo.applicationInfo.enabled;
    }

    public boolean isInstalledPackage()
    {
        boolean flag = false;
        if(mPackageInfo == null)
            return false;
        boolean flag1 = flag;
        if((mPackageInfo.applicationInfo.flags & 0x800000) != 0)
        {
            flag1 = flag;
            if((mPackageInfo.applicationInfo.privateFlags & 1) == 0)
                flag1 = true;
        }
        return flag1;
    }

    private final PackageInfo mPackageInfo;
    private final UserInfo mUserInfo;
}
