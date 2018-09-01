// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.commands.pm;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.RemoteException;
import android.os.UserHandle;

public class IActivityManagerCompat
{

    public IActivityManagerCompat()
    {
    }

    public static int startActivity(Intent intent)
        throws RemoteException
    {
        IActivityManager iactivitymanager = ActivityManagerNative.getDefault();
        return iactivitymanager.startActivityAsUser(null, "pm", intent, null, null, null, 0, 0, null, null, iactivitymanager.getCurrentUser().getUserHandle().getIdentifier());
    }
}
