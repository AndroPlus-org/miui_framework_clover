// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security.appcompatibility;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import miui.security.SecurityManager;

public class AppCompatibilityManager
{

    public AppCompatibilityManager()
    {
    }

    public static Intent getAppErrorTipsDialogIntentForApptore(String s)
    {
        Intent intent = new Intent("com.miui.appcompatibility.LaunchDialog.appstore");
        if(!TextUtils.isEmpty(s))
            intent.putExtra("app_name", s);
        return intent;
    }

    public static Intent getAppErrorTipsDialogIntentForLauncher(String s)
    {
        Intent intent = new Intent("com.miui.appcompatibility.LaunchDialog.launcher");
        if(!TextUtils.isEmpty(s))
            intent.putExtra("app_name", s);
        return intent;
    }

    public static List getIncompatibleAppList(Context context)
    {
        return getSecurityManager(context).getIncompatibleAppList();
    }

    private static SecurityManager getSecurityManager(Context context)
    {
        return (SecurityManager)context.getSystemService("security");
    }

    public static boolean isAppCompatible(Context context, String s)
    {
        for(context = getSecurityManager(context).getIncompatibleAppList().iterator(); context.hasNext();)
            if(((String)context.next()).equals(s))
                return false;

        return true;
    }

    public static void setIncompatibleAppList(Context context, List list)
    {
        if(list != null)
            getSecurityManager(context).setIncompatibleAppList(list);
    }

    public static final String ACTION_BROADCAST_APPCOMPATIBILITY_UPDATE = "com.miui.action.appcompatibility.update";
    public static final String DEVICE_NAME;
    private static final String INTENT_ACTION_SHOW_DIALOG_FOR_APPSTORE = "com.miui.appcompatibility.LaunchDialog.appstore";
    private static final String INTENT_ACTION_SHOW_DIALOG_FOR_LAUNCHER = "com.miui.appcompatibility.LaunchDialog.launcher";
    private static final String INTENT_EXTRA_APPNAME = "app_name";

    static 
    {
        DEVICE_NAME = Build.DEVICE;
    }
}
