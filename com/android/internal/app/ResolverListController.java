// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.content.*;
import android.content.pm.*;
import android.os.RemoteException;
import android.util.Log;
import java.util.*;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package com.android.internal.app:
//            ResolverComparator

public class ResolverListController
{
    private class ComputeCallback
        implements ResolverComparator.AfterCompute
    {

        public void afterCompute()
        {
            mFinishComputeSignal.countDown();
        }

        private CountDownLatch mFinishComputeSignal;
        final ResolverListController this$0;

        public ComputeCallback(CountDownLatch countdownlatch)
        {
            this$0 = ResolverListController.this;
            super();
            mFinishComputeSignal = countdownlatch;
        }
    }


    public ResolverListController(Context context, PackageManager packagemanager, Intent intent, String s, int i)
    {
        mLock = new Object();
        isComputed = false;
        mContext = context;
        mpm = packagemanager;
        mLaunchedFromUid = i;
        mTargetIntent = intent;
        mReferrerPackage = s;
        context = ((Context) (mLock));
        context;
        JVM INSTR monitorenter ;
        packagemanager = JVM INSTR new #51  <Class ResolverComparator>;
        packagemanager.ResolverComparator(mContext, mTargetIntent, mReferrerPackage, null);
        mResolverComparator = packagemanager;
        context;
        JVM INSTR monitorexit ;
        return;
        packagemanager;
        throw packagemanager;
    }

    private static boolean isSameResolvedComponent(ResolveInfo resolveinfo, ResolverActivity.ResolvedComponentInfo resolvedcomponentinfo)
    {
        resolveinfo = resolveinfo.activityInfo;
        boolean flag;
        if(((ActivityInfo) (resolveinfo)).packageName.equals(resolvedcomponentinfo.name.getPackageName()))
            flag = ((ActivityInfo) (resolveinfo)).name.equals(resolvedcomponentinfo.name.getClassName());
        else
            flag = false;
        return flag;
    }

    public void addResolveListDedupe(List list, Intent intent, List list1)
    {
        int i = list1.size();
        int j = list.size();
        int k = 0;
label0:
        do
        {
            if(k < i)
            {
                Object obj = (ResolveInfo)list1.get(k);
                boolean flag = false;
                int l = 0;
                do
                {
label1:
                    {
                        boolean flag1 = flag;
                        if(l < j)
                        {
                            ResolverActivity.ResolvedComponentInfo resolvedcomponentinfo = (ResolverActivity.ResolvedComponentInfo)list.get(l);
                            if(!isSameResolvedComponent(((ResolveInfo) (obj)), resolvedcomponentinfo))
                                break label1;
                            flag1 = true;
                            resolvedcomponentinfo.add(intent, ((ResolveInfo) (obj)));
                        }
                        if(!flag1)
                        {
                            ComponentName componentname = new ComponentName(((ResolveInfo) (obj)).activityInfo.packageName, ((ResolveInfo) (obj)).activityInfo.name);
                            obj = new ResolverActivity.ResolvedComponentInfo(componentname, intent, ((ResolveInfo) (obj)));
                            ((ResolverActivity.ResolvedComponentInfo) (obj)).setPinned(isComponentPinned(componentname));
                            list.add(obj);
                        }
                        k++;
                        continue label0;
                    }
                    l++;
                } while(true);
            }
            return;
        } while(true);
    }

    public void destroy()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mResolverComparator != null)
            mResolverComparator.destroy();
        mResolverComparator = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public ArrayList filterIneligibleActivities(List list, boolean flag)
    {
        ArrayList arraylist = null;
        int i = list.size() - 1;
        while(i >= 0) 
        {
label0:
            {
                ActivityInfo activityinfo = ((ResolverActivity.ResolvedComponentInfo)list.get(i)).getResolveInfoAt(0).activityInfo;
                int j = ActivityManager.checkComponentPermission(activityinfo.permission, mLaunchedFromUid, activityinfo.applicationInfo.uid, activityinfo.exported);
                boolean flag1;
                ArrayList arraylist1;
                if((activityinfo.applicationInfo.flags & 0x40000000) != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(j == 0 && !flag1)
                {
                    arraylist1 = arraylist;
                    if(!isComponentFiltered(activityinfo.getComponentName()))
                        break label0;
                }
                arraylist1 = arraylist;
                if(flag)
                {
                    arraylist1 = arraylist;
                    if(arraylist == null)
                        arraylist1 = new ArrayList(list);
                }
                list.remove(i);
            }
            i--;
            arraylist = arraylist1;
        }
        return arraylist;
    }

    public ArrayList filterLowPriority(List list, boolean flag)
    {
        ArrayList arraylist = null;
        ResolveInfo resolveinfo = ((ResolverActivity.ResolvedComponentInfo)list.get(0)).getResolveInfoAt(0);
        int i = list.size();
        for(int j = 1; j < i;)
        {
            int l;
            ArrayList arraylist2;
label0:
            {
                ResolveInfo resolveinfo1 = ((ResolverActivity.ResolvedComponentInfo)list.get(j)).getResolveInfoAt(0);
                int k = i;
                ArrayList arraylist1 = arraylist;
                if(resolveinfo.priority == resolveinfo1.priority)
                {
                    l = i;
                    arraylist2 = arraylist;
                    if(resolveinfo.isDefault == resolveinfo1.isDefault)
                        break label0;
                    arraylist1 = arraylist;
                    k = i;
                }
                do
                {
                    l = k;
                    arraylist2 = arraylist1;
                    if(j >= k)
                        break;
                    arraylist = arraylist1;
                    if(flag)
                    {
                        arraylist = arraylist1;
                        if(arraylist1 == null)
                            arraylist = new ArrayList(list);
                    }
                    list.remove(j);
                    k--;
                    arraylist1 = arraylist;
                } while(true);
            }
            j++;
            i = l;
            arraylist = arraylist2;
        }

        return arraylist;
    }

    public ResolveInfo getLastChosen()
        throws RemoteException
    {
        return AppGlobals.getPackageManager().getLastChosenActivity(mTargetIntent, mTargetIntent.resolveTypeIfNeeded(mContext.getContentResolver()), 0x10000);
    }

    public List getResolversForIntent(boolean flag, boolean flag1, List list)
    {
        ArrayList arraylist = null;
        int i = 0;
        for(int j = list.size(); i < j;)
        {
            Intent intent = (Intent)list.get(i);
            Object obj = mpm;
            int k;
            char c;
            List list1;
            if(flag)
                k = 64;
            else
                k = 0;
            if(flag1)
                c = '\200';
            else
                c = '\0';
            list1 = ((PackageManager) (obj)).queryIntentActivities(intent, c | (0x10000 | k) | 0x800000);
            for(k = list1.size() - 1; k >= 0; k--)
            {
                obj = (ResolveInfo)list1.get(k);
                if(((ResolveInfo) (obj)).activityInfo != null && ((ResolveInfo) (obj)).activityInfo.exported ^ true)
                    list1.remove(k);
            }

            obj = arraylist;
            if(list1 != null)
            {
                obj = arraylist;
                if(arraylist == null)
                    obj = new ArrayList();
                addResolveListDedupe(((List) (obj)), intent, list1);
            }
            i++;
            arraylist = ((ArrayList) (obj));
        }

        return arraylist;
    }

    public float getScore(ResolverActivity.DisplayResolveInfo displayresolveinfo)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ResolverComparator resolvercomparator = mResolverComparator;
        if(resolvercomparator != null)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return 0.0F;
        float f = mResolverComparator.getScore(displayresolveinfo.getResolvedComponentName());
        obj;
        JVM INSTR monitorexit ;
        return f;
        displayresolveinfo;
        throw displayresolveinfo;
    }

    boolean isComponentFiltered(ComponentName componentname)
    {
        return false;
    }

    boolean isComponentPinned(ComponentName componentname)
    {
        return false;
    }

    public void setLastChosen(Intent intent, IntentFilter intentfilter, int i)
        throws RemoteException
    {
        AppGlobals.getPackageManager().setLastChosenActivity(intent, intent.resolveType(mContext.getContentResolver()), 0x10000, intentfilter, i, intent.getComponent());
    }

    public void sort(List list)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mResolverComparator != null)
            break MISSING_BLOCK_LABEL_26;
        Log.d("ResolverListController", "Comparator has already been destroyed; skipped.");
        obj;
        JVM INSTR monitorexit ;
        return;
        CountDownLatch countdownlatch;
        countdownlatch = JVM INSTR new #265 <Class CountDownLatch>;
        countdownlatch.CountDownLatch(1);
        ComputeCallback computecallback = JVM INSTR new #6   <Class ResolverListController$ComputeCallback>;
        computecallback.this. ComputeCallback(countdownlatch);
        mResolverComparator.setCallBack(computecallback);
        System.currentTimeMillis();
        if(!isComputed)
        {
            mResolverComparator.compute(list);
            countdownlatch.await();
            isComputed = true;
        }
        Collections.sort(list, mResolverComparator);
        System.currentTimeMillis();
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        list;
        StringBuilder stringbuilder = JVM INSTR new #294 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("ResolverListController", stringbuilder.append("Compute & Sort was interrupted: ").append(list).toString());
          goto _L1
        list;
        throw list;
    }

    public void updateChooserCounts(String s, int i, String s1)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mResolverComparator != null)
            mResolverComparator.updateChooserCounts(s, i, s1);
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void updateModel(ComponentName componentname)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mResolverComparator != null)
            mResolverComparator.updateModel(componentname);
        obj;
        JVM INSTR monitorexit ;
        return;
        componentname;
        throw componentname;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "ResolverListController";
    private boolean isComputed;
    private final Context mContext;
    private final int mLaunchedFromUid;
    Object mLock;
    private final String mReferrerPackage;
    private ResolverComparator mResolverComparator;
    private final Intent mTargetIntent;
    private final PackageManager mpm;
}
