// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.collect.Lists;
import java.util.*;
import miui.util.AudioOutputHelper;

// Referenced classes of package android.app:
//            AppGlobals, ActivityManager

public class ExtraActivityManager
{
    public static class PackageNameList
    {

        public final List mOrderList;
        public final int mPlayingCount;

        public PackageNameList(List list, int i)
        {
            mOrderList = list;
            mPlayingCount = i;
        }
    }

    public static class PriorityComponent
    {

        public final ComponentName mComponent;
        public final int mPriority;

        public PriorityComponent(ComponentName componentname, int i)
        {
            mComponent = componentname;
            mPriority = i;
        }
    }


    public ExtraActivityManager()
    {
    }

    public static PriorityComponent getMediaButtonReceiver(Context context, List list, int i)
    {
        if(i == 0)
            return null;
        context = null;
        Object obj;
        IPackageManager ipackagemanager = AppGlobals.getPackageManager();
        obj = JVM INSTR new #41  <Class Intent>;
        ((Intent) (obj)).Intent("android.intent.action.MEDIA_BUTTON");
        obj = ipackagemanager.queryIntentReceivers(((Intent) (obj)), null, 0, 0);
        context = ((Context) (obj));
_L2:
        if(context != null && context.getList().size() > 0)
        {
            int j = 0;
            Iterator iterator1 = list.iterator();
            int k;
            do
            {
                if(!iterator1.hasNext())
                    break;
                String s = (String)iterator1.next();
                for(Iterator iterator = context.getList().iterator(); iterator.hasNext();)
                {
                    list = (ResolveInfo)iterator.next();
                    if(((ResolveInfo) (list)).activityInfo != null && ((ResolveInfo) (list)).activityInfo.name != null && s.equals(((ResolveInfo) (list)).activityInfo.packageName))
                        return new PriorityComponent(new ComponentName(((ResolveInfo) (list)).activityInfo.packageName, ((ResolveInfo) (list)).activityInfo.name), j);
                }

                k = j + 1;
                j = k;
            } while(k < i);
        }
        return null;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static String getPackageNameForTask(ActivityManager.RecentTaskInfo recenttaskinfo)
    {
        String s = null;
        Intent intent = recenttaskinfo.baseIntent;
        if(intent != null)
        {
            String s1 = intent.getPackage();
            s = s1;
            if(s1 == null)
            {
                s = s1;
                if(intent.getComponent() != null)
                    s = intent.getComponent().getPackageName();
            }
        }
        String s2 = s;
        if(s == null)
        {
            s2 = s;
            if(recenttaskinfo.origActivity != null)
                s2 = recenttaskinfo.origActivity.getPackageName();
        }
        return s2;
    }

    public static List getPackageNameListForRecentTasks(Context context)
    {
        long l;
        context = (ActivityManager)context.getSystemService("activity");
        l = Binder.clearCallingIdentity();
        Object obj = context.getRecentTasks(20, 3);
        Binder.restoreCallingIdentity(l);
        if(obj == null)
            return null;
        break MISSING_BLOCK_LABEL_39;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
        context = new ArrayList(((List) (obj)).size());
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            String s = getPackageNameForTask((ActivityManager.RecentTaskInfo)((Iterator) (obj)).next());
            if(s != null)
                context.add(s);
        } while(true);
        return context;
    }

    public static PackageNameList getPackageNameListOrderByReceivePriority(Context context)
    {
        ArrayList arraylist = Lists.newArrayList();
        int i = 0;
        Object obj = AudioOutputHelper.getActiveClientProcessList(((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses(), context, true);
        if(obj != null)
        {
            obj = ((Iterable) (obj)).iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                ActivityManager.RunningAppProcessInfo runningappprocessinfo = (ActivityManager.RunningAppProcessInfo)((Iterator) (obj)).next();
                if(runningappprocessinfo.pkgList != null)
                {
                    String as[] = runningappprocessinfo.pkgList;
                    i = 0;
                    int j = as.length;
                    while(i < j) 
                    {
                        arraylist.add(as[i]);
                        i++;
                    }
                }
            } while(true);
            i = arraylist.size();
        }
        context = getPackageNameListForRecentTasks(context);
        if(context != null)
            arraylist.addAll(context);
        return new PackageNameList(arraylist, i);
    }

    public static final int START_INCOMPATIBLE = 5;
    private static String TAG = android/app/ExtraActivityManager.getName();

}
