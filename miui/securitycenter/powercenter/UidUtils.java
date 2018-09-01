// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.powercenter;

import android.os.UserHandle;

class UidUtils
{

    UidUtils()
    {
    }

    static int getRealUid(int i)
    {
        if(isSharedGid(i))
            return UserHandle.getUid(0, UserHandle.getAppIdFromSharedAppGid(i));
        else
            return i;
    }

    private static boolean isSharedGid(int i)
    {
        boolean flag = false;
        if(UserHandle.getAppIdFromSharedAppGid(i) > 0)
            flag = true;
        return flag;
    }
}
