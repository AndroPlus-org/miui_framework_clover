// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.app.AppOpsManager;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;

// Referenced classes of package android.content:
//            Context

public final class PermissionChecker
{

    private PermissionChecker()
    {
    }

    public static int checkCallingOrSelfPermission(Context context, String s)
    {
        String s1;
        if(Binder.getCallingPid() == Process.myPid())
            s1 = context.getPackageName();
        else
            s1 = null;
        return checkPermission(context, s, Binder.getCallingPid(), Binder.getCallingUid(), s1);
    }

    public static int checkCallingPermission(Context context, String s, String s1)
    {
        if(Binder.getCallingPid() == Process.myPid())
            return -1;
        else
            return checkPermission(context, s, Binder.getCallingPid(), Binder.getCallingUid(), s1);
    }

    public static int checkPermission(Context context, String s, int i, int j, String s1)
    {
        if(context.checkPermission(s, i, j) == -1)
            return -1;
        AppOpsManager appopsmanager = (AppOpsManager)context.getSystemService(android/app/AppOpsManager);
        String s2 = AppOpsManager.permissionToOp(s);
        if(s2 == null)
            return 0;
        s = s1;
        if(s1 == null)
        {
            context = context.getPackageManager().getPackagesForUid(j);
            if(context == null || context.length <= 0)
                return -1;
            s = context[0];
        }
        return appopsmanager.noteProxyOpNoThrow(s2, s) == 0 ? 0 : -2;
    }

    public static int checkSelfPermission(Context context, String s)
    {
        return checkPermission(context, s, Process.myPid(), Process.myUid(), context.getPackageName());
    }

    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_DENIED_APP_OP = -2;
    public static final int PERMISSION_GRANTED = 0;
}
