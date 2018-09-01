// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.notification;

import android.content.ComponentName;
import android.content.Intent;

public final class NotificationAccessConfirmationActivityContract
{

    public NotificationAccessConfirmationActivityContract()
    {
    }

    public static Intent launcherIntent(int i, ComponentName componentname, String s)
    {
        return (new Intent()).setComponent(COMPONENT_NAME).putExtra("user_id", i).putExtra("component_name", componentname).putExtra("package_title", s);
    }

    private static final ComponentName COMPONENT_NAME = new ComponentName("com.android.settings", "com.android.settings.notification.NotificationAccessConfirmationActivity");
    public static final String EXTRA_COMPONENT_NAME = "component_name";
    public static final String EXTRA_PACKAGE_TITLE = "package_title";
    public static final String EXTRA_USER_ID = "user_id";

}
