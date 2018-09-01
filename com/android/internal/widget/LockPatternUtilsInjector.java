// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.UserHandle;
import android.os.UserManager;

public class LockPatternUtilsInjector
{

    private LockPatternUtilsInjector()
    {
    }

    public static boolean isUserAllowed(UserManager usermanager)
    {
        usermanager.getUserInfo(UserHandle.myUserId());
        return false;
    }
}
