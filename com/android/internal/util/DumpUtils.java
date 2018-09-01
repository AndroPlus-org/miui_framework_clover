// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import java.io.PrintWriter;
import java.io.StringWriter;

// Referenced classes of package com.android.internal.util:
//            FastPrintWriter

public final class DumpUtils
{
    public static interface Dump
    {

        public abstract void dump(PrintWriter printwriter, String s);
    }


    private DumpUtils()
    {
    }

    public static boolean checkDumpAndUsageStatsPermission(Context context, String s, PrintWriter printwriter)
    {
        boolean flag;
        if(checkDumpPermission(context, s, printwriter))
            flag = checkUsageStatsPermission(context, s, printwriter);
        else
            flag = false;
        return flag;
    }

    public static boolean checkDumpPermission(Context context, String s, PrintWriter printwriter)
    {
        if(context.checkCallingOrSelfPermission("android.permission.DUMP") != 0)
        {
            logMessage(printwriter, (new StringBuilder()).append("Permission Denial: can't dump ").append(s).append(" from from pid=").append(Binder.getCallingPid()).append(", uid=").append(Binder.getCallingUid()).append(" due to missing android.permission.DUMP permission").toString());
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean checkUsageStatsPermission(Context context, String s, PrintWriter printwriter)
    {
        int i = Binder.getCallingUid();
        switch(i)
        {
        default:
            if(context.checkCallingOrSelfPermission("android.permission.PACKAGE_USAGE_STATS") != 0)
            {
                logMessage(printwriter, (new StringBuilder()).append("Permission Denial: can't dump ").append(s).append(" from from pid=").append(Binder.getCallingPid()).append(", uid=").append(Binder.getCallingUid()).append(" due to missing android.permission.PACKAGE_USAGE_STATS permission").toString());
                return false;
            }
            break;

        case 0: // '\0'
        case 1000: 
        case 2000: 
            return true;
        }
        AppOpsManager appopsmanager = (AppOpsManager)context.getSystemService(android/app/AppOpsManager);
        context = context.getPackageManager().getPackagesForUid(i);
        if(context != null)
        {
            int j = context.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                switch(appopsmanager.checkOpNoThrow(43, i, context[k]))
                {
                case 1: // '\001'
                case 2: // '\002'
                default:
                    k++;
                    break;

                case 0: // '\0'
                    return true;

                case 3: // '\003'
                    return true;
                }
            } while(true);
        }
        logMessage(printwriter, (new StringBuilder()).append("Permission Denial: can't dump ").append(s).append(" from from pid=").append(Binder.getCallingPid()).append(", uid=").append(Binder.getCallingUid()).append(" due to android:get_usage_stats app-op not allowed").toString());
        return false;
    }

    public static void dumpAsync(Handler handler, Dump dump, PrintWriter printwriter, String s, long l)
    {
        StringWriter stringwriter = new StringWriter();
        if(handler.runWithScissors(new Runnable(stringwriter, dump, s) {

        public void run()
        {
            FastPrintWriter fastprintwriter = new FastPrintWriter(sw);
            dump.dump(fastprintwriter, prefix);
            fastprintwriter.close();
        }

        final Dump val$dump;
        final String val$prefix;
        final StringWriter val$sw;

            
            {
                sw = stringwriter;
                dump = dump1;
                prefix = s;
                super();
            }
    }
, l))
            printwriter.print(stringwriter.toString());
        else
            printwriter.println("... timed out");
    }

    private static void logMessage(PrintWriter printwriter, String s)
    {
        printwriter.println(s);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "DumpUtils";
}
