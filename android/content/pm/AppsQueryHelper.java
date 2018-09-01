// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.app.AppGlobals;
import android.content.Intent;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.content.pm:
//            IPackageManager, ParceledListSlice, ApplicationInfo, ResolveInfo, 
//            ActivityInfo, PackageInfo, ServiceInfo

public class AppsQueryHelper
{

    public AppsQueryHelper()
    {
        this(AppGlobals.getPackageManager());
    }

    public AppsQueryHelper(IPackageManager ipackagemanager)
    {
        mPackageManager = ipackagemanager;
    }

    protected List getAllApps(int i)
    {
        List list;
        try
        {
            list = mPackageManager.getInstalledApplications(8704, i).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    protected List getPackagesHoldingPermission(String s, int i)
    {
        try
        {
            s = mPackageManager.getPackagesHoldingPermissions(new String[] {
                s
            }, 0, i).getList();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public List queryApps(int i, boolean flag, UserHandle userhandle)
    {
        boolean flag1;
        boolean flag2;
        boolean flag3;
        int i1;
        ArrayList arraylist;
        if((GET_NON_LAUNCHABLE_APPS & i) > 0)
            flag1 = true;
        else
            flag1 = false;
        if((GET_APPS_WITH_INTERACT_ACROSS_USERS_PERM & i) > 0)
            flag2 = true;
        else
            flag2 = false;
        if((GET_IMES & i) > 0)
            flag3 = true;
        else
            flag3 = false;
        if((GET_REQUIRED_FOR_SYSTEM_USER & i) > 0)
            i1 = 1;
        else
            i1 = 0;
        if(mAllApps == null)
            mAllApps = getAllApps(userhandle.getIdentifier());
        arraylist = new ArrayList();
        if(i == 0)
        {
            i1 = mAllApps.size();
            i = 0;
            while(i < i1) 
            {
                userhandle = (ApplicationInfo)mAllApps.get(i);
                if(!flag || !(userhandle.isSystemApp() ^ true))
                    arraylist.add(((ApplicationInfo) (userhandle)).packageName);
                i++;
            }
            return arraylist;
        }
        if(flag1)
        {
            List list = queryIntentActivitiesAsUser((new Intent("android.intent.action.MAIN")).addCategory("android.intent.category.LAUNCHER"), userhandle.getIdentifier());
            ArraySet arrayset = new ArraySet();
            int j = list.size();
            for(i = 0; i < j; i++)
                arrayset.add(((ResolveInfo)list.get(i)).activityInfo.packageName);

            j = mAllApps.size();
            i = 0;
            while(i < j) 
            {
                Object obj = (ApplicationInfo)mAllApps.get(i);
                if(!flag || !(((ApplicationInfo) (obj)).isSystemApp() ^ true))
                {
                    obj = ((ApplicationInfo) (obj)).packageName;
                    if(!arrayset.contains(obj))
                        arraylist.add(obj);
                }
                i++;
            }
        }
        if(flag2)
        {
            List list1 = getPackagesHoldingPermission("android.permission.INTERACT_ACROSS_USERS", userhandle.getIdentifier());
            int k = list1.size();
            i = 0;
            do
            {
                if(i >= k)
                    break;
                PackageInfo packageinfo = (PackageInfo)list1.get(i);
                if((!flag || !(packageinfo.applicationInfo.isSystemApp() ^ true)) && !arraylist.contains(packageinfo.packageName))
                    arraylist.add(packageinfo.packageName);
                i++;
            } while(true);
        }
        if(flag3)
        {
            userhandle = queryIntentServicesAsUser(new Intent("android.view.InputMethod"), userhandle.getIdentifier());
            int l = userhandle.size();
            i = 0;
            do
            {
                if(i >= l)
                    break;
                ServiceInfo serviceinfo = ((ResolveInfo)userhandle.get(i)).serviceInfo;
                if((!flag || !(serviceinfo.applicationInfo.isSystemApp() ^ true)) && !arraylist.contains(serviceinfo.packageName))
                    arraylist.add(serviceinfo.packageName);
                i++;
            } while(true);
        }
        if(i1 != 0)
        {
            int j1 = mAllApps.size();
            i = 0;
            do
            {
                if(i >= j1)
                    break;
                userhandle = (ApplicationInfo)mAllApps.get(i);
                if((!flag || !(userhandle.isSystemApp() ^ true)) && userhandle.isRequiredForSystemUser())
                    arraylist.add(((ApplicationInfo) (userhandle)).packageName);
                i++;
            } while(true);
        }
        return arraylist;
    }

    protected List queryIntentActivitiesAsUser(Intent intent, int i)
    {
        try
        {
            intent = mPackageManager.queryIntentActivities(intent, null, 0xc2200, i).getList();
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        return intent;
    }

    protected List queryIntentServicesAsUser(Intent intent, int i)
    {
        try
        {
            intent = mPackageManager.queryIntentServices(intent, null, 0xc8080, i).getList();
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        return intent;
    }

    public static int GET_APPS_WITH_INTERACT_ACROSS_USERS_PERM = 2;
    public static int GET_IMES = 4;
    public static int GET_NON_LAUNCHABLE_APPS = 1;
    public static int GET_REQUIRED_FOR_SYSTEM_USER = 8;
    private List mAllApps;
    private final IPackageManager mPackageManager;

}
