// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.miui;

import android.app.AppOpsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManagerCompat;
import android.net.Uri;
import android.os.*;
import android.util.Log;

public class AppOpsUtils
{

    static Uri _2D_get0()
    {
        return CONTENT_URI;
    }

    public AppOpsUtils()
    {
    }

    public static int getApplicationAutoStart(Context context, String s)
    {
        int i;
        try
        {
            i = PackageManagerCompat.getPackageUidAsUser(context.getPackageManager(), s, UserHandle.getUserId(Binder.getCallingUid()));
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("AppOpsUtils", (new StringBuilder()).append("Can't obtain the uid for package: ").append(s).toString(), context);
            return 1;
        }
        return ((AppOpsManager)context.getSystemService("appops")).checkOpNoThrow(10008, i, s);
    }

    public static int getApplicationAutoStart(Context context, String s, int i)
    {
        return ((AppOpsManager)context.getSystemService("appops")).checkOpNoThrow(10008, i, s);
    }

    public static boolean isXOptMode()
    {
        return SystemProperties.getBoolean("persist.sys.miui_optimization", "1".equals(SystemProperties.get("ro.miui.cts")) ^ true) ^ true;
    }

    public static void setApplicationAutoStart(Context context, String s, boolean flag)
    {
        int i;
        int j;
        try
        {
            i = PackageManagerCompat.getPackageUidAsUser(context.getPackageManager(), s, UserHandle.getUserId(Binder.getCallingUid()));
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("AppOpsUtils", (new StringBuilder()).append("Can't obtain the uid for package: ").append(s).toString(), context);
            return;
        }
        context = (AppOpsManager)context.getSystemService("appops");
        if(flag)
            j = 0;
        else
            j = 2;
        context.setMode(10008, i, s, j);
    }

    public static void setMode(Context context, int i, String s, int j)
    {
        if(i != 11)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("not support code :").append(i).toString());
        } else
        {
            (new AsyncTask(j, s, context) {

                protected volatile Object doInBackground(Object aobj[])
                {
                    return doInBackground((Void[])aobj);
                }

                protected transient Void doInBackground(Void avoid[])
                {
                    boolean flag;
                    int k;
                    avoid = new Bundle();
                    flag = true;
                    k = ((flag) ? 1 : 0);
                    mode;
                    JVM INSTR tableswitch 0 4: default 52
                //                               0 117
                //                               1 127
                //                               2 127
                //                               3 54
                //                               4 122;
                       goto _L1 _L2 _L3 _L3 _L4 _L5
_L3:
                    break MISSING_BLOCK_LABEL_127;
_L4:
                    break; /* Loop/switch isn't completed */
_L1:
                    k = ((flag) ? 1 : 0);
_L6:
                    avoid.putLong("extra_permission", 32768L);
                    avoid.putInt("extra_action", k);
                    avoid.putStringArray("extra_package", new String[] {
                        packageName
                    });
                    avoid.putInt("extra_flags", 0);
                    try
                    {
                        context.getContentResolver().call(AppOpsUtils._2D_get0(), String.valueOf(6), null, avoid);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Void avoid[])
                    {
                        Log.e("AppOpsUtils", "SET_APPLICATION_PERMISSION : ", avoid);
                    }
                    return null;
_L2:
                    k = 3;
                      goto _L6
_L5:
                    k = 2;
                      goto _L6
                    k = 1;
                      goto _L6
                }

                final Context val$context;
                final int val$mode;
                final String val$packageName;

            
            {
                mode = i;
                packageName = s;
                context = context1;
                super();
            }
            }
).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
    }

    public static final int ACTION_ACCEPT = 3;
    public static final int ACTION_DEFAULT = 0;
    public static final int ACTION_PROMPT = 2;
    public static final int ACTION_REJECT = 1;
    private static final String AUTHORITY = "com.lbe.security.miui.permmgr";
    private static final Uri CONTENT_URI = Uri.parse("content://com.lbe.security.miui.permmgr");
    private static final String EXTRA_ACTION = "extra_action";
    private static final String EXTRA_FLAGS = "extra_flags";
    private static final String EXTRA_PACKAGE = "extra_package";
    private static final String EXTRA_PERMISSION = "extra_permission";
    public static final long PERM_ID_NOTIFICATION = 32768L;
    public static final int SET_APPLICATION_PERMISSION = 6;
    private static final String TAG = "AppOpsUtils";

}
