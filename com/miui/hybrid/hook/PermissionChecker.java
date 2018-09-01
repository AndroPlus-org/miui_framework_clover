// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.hybrid.hook;

import android.content.Context;

public class PermissionChecker
{

    public PermissionChecker()
    {
    }

    public static boolean check(Context context)
    {
        boolean flag = false;
        if(context.checkCallingPermission("com.miui.hybrid.hook.WRITE_PERMISSION") == 0)
            flag = true;
        return flag;
    }

    private static final String PERMISSION = "com.miui.hybrid.hook.WRITE_PERMISSION";
}
