// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.*;
import android.net.Uri;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import java.util.*;

// Referenced classes of package android.telecom:
//            TelecomManager

public class DefaultDialerManager
{

    public DefaultDialerManager()
    {
    }

    private static List filterByIntent(Context context, List list, Intent intent, int i)
    {
        if(list == null || list.isEmpty())
            return new ArrayList();
        ArrayList arraylist = new ArrayList();
        context = context.getPackageManager().queryIntentActivitiesAsUser(intent, 0, i);
        int j = context.size();
        for(i = 0; i < j; i++)
        {
            intent = ((ResolveInfo)context.get(i)).activityInfo;
            if(intent != null && list.contains(((ActivityInfo) (intent)).packageName) && arraylist.contains(((ActivityInfo) (intent)).packageName) ^ true)
                arraylist.add(((ActivityInfo) (intent)).packageName);
        }

        return arraylist;
    }

    public static String getDefaultDialerApplication(Context context)
    {
        return getDefaultDialerApplication(context, context.getUserId());
    }

    public static String getDefaultDialerApplication(Context context, int i)
    {
        String s = android.provider.Settings.Secure.getStringForUser(context.getContentResolver(), "dialer_default_application", i);
        List list = getInstalledDialerApplications(context, i);
        if(list.contains(s))
            return s;
        context = getTelecomManager(context).getSystemDialerPackage();
        if(TextUtils.isEmpty(context))
            return null;
        if(list.contains(context))
            return context;
        else
            return null;
    }

    public static List getInstalledDialerApplications(Context context)
    {
        return getInstalledDialerApplications(context, Process.myUserHandle().getIdentifier());
    }

    public static List getInstalledDialerApplications(Context context, int i)
    {
        Object obj = context.getPackageManager().queryIntentActivitiesAsUser(new Intent("android.intent.action.DIAL"), 0, i);
        ArrayList arraylist = new ArrayList();
        Iterator iterator = ((Iterable) (obj)).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ResolveInfo resolveinfo = (ResolveInfo)iterator.next();
            obj = resolveinfo.activityInfo;
            if(obj != null && arraylist.contains(((ActivityInfo) (obj)).packageName) ^ true && resolveinfo.targetUserId == -2)
                arraylist.add(((ActivityInfo) (obj)).packageName);
        } while(true);
        obj = new Intent("android.intent.action.DIAL");
        ((Intent) (obj)).setData(Uri.fromParts("tel", "", null));
        return filterByIntent(context, arraylist, ((Intent) (obj)), i);
    }

    private static TelecomManager getTelecomManager(Context context)
    {
        return (TelecomManager)context.getSystemService("telecom");
    }

    public static boolean isDefaultOrSystemDialer(Context context, String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        context = getTelecomManager(context);
        boolean flag;
        if(!s.equals(context.getDefaultDialerPackage()))
            flag = s.equals(context.getSystemDialerPackage());
        else
            flag = true;
        return flag;
    }

    public static boolean setDefaultDialerApplication(Context context, String s)
    {
        return setDefaultDialerApplication(context, s, ActivityManager.getCurrentUser());
    }

    public static boolean setDefaultDialerApplication(Context context, String s, int i)
    {
        String s1 = android.provider.Settings.Secure.getStringForUser(context.getContentResolver(), "dialer_default_application", i);
        if(s != null && s1 != null && s.equals(s1))
            return false;
        if(getInstalledDialerApplications(context).contains(s))
        {
            android.provider.Settings.Secure.putStringForUser(context.getContentResolver(), "dialer_default_application", s, i);
            return true;
        } else
        {
            return false;
        }
    }

    private static final String TAG = "DefaultDialerManager";
}
