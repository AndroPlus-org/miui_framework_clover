// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import miui.security.SecurityManager;

// Referenced classes of package android.app:
//            Activity

class ActivityInjector
{

    ActivityInjector()
    {
    }

    static void checkAccessControl(Activity activity)
    {
        ((SecurityManager)activity.getSystemService("security")).checkAccessControl(activity, activity.getUserId());
    }
}
