// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.*;
import android.content.pm.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.metrics.LogMaker;
import android.os.*;
import android.service.resolver.*;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import java.text.Collator;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.android.internal.app:
//            ResolverActivity

class ResolverComparator
    implements Comparator
{
    public static interface AfterCompute
    {

        public abstract void afterCompute();
    }

    private class ResolverRankerServiceConnection
        implements ServiceConnection
    {

        public void destroy()
        {
            Object obj = ResolverComparator._2D_get2(ResolverComparator.this);
            obj;
            JVM INSTR monitorenter ;
            ResolverComparator._2D_set0(ResolverComparator.this, null);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            componentname = ((ComponentName) (ResolverComparator._2D_get2(ResolverComparator.this)));
            componentname;
            JVM INSTR monitorenter ;
            ResolverComparator._2D_set0(ResolverComparator.this, android.service.resolver.IResolverRankerService.Stub.asInterface(ibinder));
            mConnectSignal.countDown();
            componentname;
            JVM INSTR monitorexit ;
            return;
            ibinder;
            throw ibinder;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Object obj = ResolverComparator._2D_get2(ResolverComparator.this);
            obj;
            JVM INSTR monitorenter ;
            destroy();
            obj;
            JVM INSTR monitorexit ;
            return;
            componentname;
            throw componentname;
        }

        private final CountDownLatch mConnectSignal;
        public final IResolverRankerResult resolverRankerResult = new _cls1();
        final ResolverComparator this$0;

        public ResolverRankerServiceConnection(CountDownLatch countdownlatch)
        {
            this$0 = ResolverComparator.this;
            super();
            mConnectSignal = countdownlatch;
        }
    }


    static AfterCompute _2D_get0(ResolverComparator resolvercomparator)
    {
        return resolvercomparator.mAfterCompute;
    }

    static Handler _2D_get1(ResolverComparator resolvercomparator)
    {
        return resolvercomparator.mHandler;
    }

    static Object _2D_get2(ResolverComparator resolvercomparator)
    {
        return resolvercomparator.mLock;
    }

    static ComponentName _2D_get3(ResolverComparator resolvercomparator)
    {
        return resolvercomparator.mResolvedRankerName;
    }

    static ArrayList _2D_get4(ResolverComparator resolvercomparator)
    {
        return resolvercomparator.mTargets;
    }

    static IResolverRankerService _2D_set0(ResolverComparator resolvercomparator, IResolverRankerService iresolverrankerservice)
    {
        resolvercomparator.mRanker = iresolverrankerservice;
        return iresolverrankerservice;
    }

    static ComponentName _2D_set1(ResolverComparator resolvercomparator, ComponentName componentname)
    {
        resolvercomparator.mRankerServiceName = componentname;
        return componentname;
    }

    public ResolverComparator(Context context, Intent intent, String s, AfterCompute aftercompute)
    {
        mCollator = Collator.getInstance(context.getResources().getConfiguration().locale);
        String s1 = intent.getScheme();
        boolean flag;
        if(!"http".equals(s1))
            flag = "https".equals(s1);
        else
            flag = true;
        mHttp = flag;
        mReferrerPackage = s;
        mAfterCompute = aftercompute;
        mContext = context;
        mPm = context.getPackageManager();
        mUsm = (UsageStatsManager)context.getSystemService("usagestats");
        mSinceTime = mCurrentTime - 0x240c8400L;
        mStats = mUsm.queryAndAggregateUsageStats(mSinceTime, mCurrentTime);
        mContentType = intent.getType();
        getContentAnnotations(intent);
        mAction = intent.getAction();
        mRankerServiceName = new ComponentName(mContext, getClass());
    }

    private void addDefaultSelectProbability(ResolverTarget resolvertarget)
    {
        resolvertarget.setSelectProbability((float)(1.0D / (Math.exp(1.6568F - (resolvertarget.getLaunchScore() * 2.5543F + resolvertarget.getTimeSpentScore() * 2.8412F + resolvertarget.getRecencyScore() * 0.269F + resolvertarget.getChooserScore() * 4.2222F)) + 1.0D)));
    }

    private void initRanker(Context context)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IResolverRankerService iresolverrankerservice;
        if(mConnection == null)
            break MISSING_BLOCK_LABEL_26;
        iresolverrankerservice = mRanker;
        if(iresolverrankerservice == null)
            break MISSING_BLOCK_LABEL_26;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        obj = resolveRankerService();
        if(obj == null)
        {
            return;
        } else
        {
            mConnectSignal = new CountDownLatch(1);
            mConnection = new ResolverRankerServiceConnection(mConnectSignal);
            context.bindServiceAsUser(((Intent) (obj)), mConnection, 1, UserHandle.SYSTEM);
            return;
        }
        context;
        throw context;
    }

    static boolean isPersistentProcess(ResolverActivity.ResolvedComponentInfo resolvedcomponentinfo)
    {
        boolean flag = false;
        if(resolvedcomponentinfo != null && resolvedcomponentinfo.getCount() > 0)
        {
            if((resolvedcomponentinfo.getResolveInfoAt(0).activityInfo.applicationInfo.flags & 8) != 0)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    private void logMetrics(int i)
    {
        if(mRankerServiceName != null)
        {
            MetricsLogger metricslogger = new MetricsLogger();
            LogMaker logmaker = new LogMaker(1085);
            logmaker.setComponentName(mRankerServiceName);
            int j;
            if(mAnnotations == null)
                j = 0;
            else
                j = 1;
            logmaker.addTaggedData(1086, Integer.valueOf(j));
            logmaker.addTaggedData(1087, Integer.valueOf(i));
            metricslogger.write(logmaker);
        }
    }

    private void predictSelectProbabilities(List list)
    {
        if(mConnection == null)
            return;
        mConnectSignal.await(200L, TimeUnit.MILLISECONDS);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mRanker == null)
            break MISSING_BLOCK_LABEL_56;
        mRanker.predict(list, mConnection.resolverRankerResult);
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
_L2:
        if(mAfterCompute != null)
            mAfterCompute.afterCompute();
        return;
        list;
        obj;
        JVM INSTR monitorexit ;
        throw list;
        list;
        Log.e("ResolverComparator", "Error in Wait for Service Connection.");
        continue; /* Loop/switch isn't completed */
        list;
        Log.e("ResolverComparator", (new StringBuilder()).append("Error in Predict: ").append(list).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void reset()
    {
        mTargetsDict.clear();
        mTargets = null;
        mRankerServiceName = new ComponentName(mContext, getClass());
        mResolvedRankerName = null;
        startWatchDog(500);
        initRanker(mContext);
    }

    private Intent resolveRankerService()
    {
        Intent intent;
        Iterator iterator;
        intent = new Intent("android.service.resolver.ResolverRankerService");
        iterator = mPm.queryIntentServices(intent, 0).iterator();
_L2:
        Object obj;
        ComponentName componentname;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_333;
            obj = (ResolveInfo)iterator.next();
        } while(obj == null || ((ResolveInfo) (obj)).serviceInfo == null || ((ResolveInfo) (obj)).serviceInfo.applicationInfo == null);
        componentname = new ComponentName(((ResolveInfo) (obj)).serviceInfo.applicationInfo.packageName, ((ResolveInfo) (obj)).serviceInfo.name);
        try
        {
            if(!"android.permission.BIND_RESOLVER_RANKER_SERVICE".equals(mPm.getServiceInfo(componentname, 0).permission))
            {
                obj = JVM INSTR new #392 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                Log.w("ResolverComparator", ((StringBuilder) (obj)).append("ResolverRankerService ").append(componentname).append(" does not require").append(" permission ").append("android.permission.BIND_RESOLVER_RANKER_SERVICE").append(" - this service will not be queried for ResolverComparator.").append(" add android:permission=\"").append("android.permission.BIND_RESOLVER_RANKER_SERVICE").append("\"").append(" to the <service> tag for ").append(componentname).append(" in the manifest.").toString());
                continue; /* Loop/switch isn't completed */
            }
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("ResolverComparator", (new StringBuilder()).append("Could not look up service ").append(componentname).append("; component name not found").toString());
            continue; /* Loop/switch isn't completed */
        }
        if(mPm.checkPermission("android.permission.PROVIDE_RESOLVER_RANKER_SERVICE", ((ResolveInfo) (obj)).serviceInfo.packageName) == 0)
            break; /* Loop/switch isn't completed */
        obj = JVM INSTR new #392 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.w("ResolverComparator", ((StringBuilder) (obj)).append("ResolverRankerService ").append(componentname).append(" does not hold").append(" permission ").append("android.permission.PROVIDE_RESOLVER_RANKER_SERVICE").append(" - this service will not be queried for ResolverComparator.").toString());
        if(true) goto _L2; else goto _L1
_L1:
        mResolvedRankerName = componentname;
        intent.setComponent(componentname);
        return intent;
        return null;
    }

    private void setFeatures(ResolverTarget resolvertarget, float f, float f1, float f2, float f3)
    {
        resolvertarget.setRecencyScore(f);
        resolvertarget.setLaunchScore(f1);
        resolvertarget.setTimeSpentScore(f2);
        resolvertarget.setChooserScore(f3);
    }

    private void startWatchDog(int i)
    {
        if(mHandler == null)
            Log.d("ResolverComparator", "Error: Handler is Null; Needs to be initialized.");
        mHandler.sendEmptyMessageDelayed(1, i);
    }

    public int compare(ResolverActivity.ResolvedComponentInfo resolvedcomponentinfo, ResolverActivity.ResolvedComponentInfo resolvedcomponentinfo1)
    {
        Object obj = resolvedcomponentinfo.getResolveInfoAt(0);
        ResolveInfo resolveinfo = resolvedcomponentinfo1.getResolveInfoAt(0);
        if(((ResolveInfo) (obj)).targetUserId != -2)
        {
            int i;
            if(resolveinfo.targetUserId != -2)
                i = 0;
            else
                i = 1;
            return i;
        }
        if(resolveinfo.targetUserId != -2)
            return -1;
        if(mHttp)
        {
            boolean flag = ResolverActivity.isSpecificUriMatch(((ResolveInfo) (obj)).match);
            if(flag != ResolverActivity.isSpecificUriMatch(resolveinfo.match))
            {
                byte byte0;
                if(flag)
                    byte0 = -1;
                else
                    byte0 = 1;
                return byte0;
            }
        }
        boolean flag2 = resolvedcomponentinfo.isPinned();
        boolean flag1 = resolvedcomponentinfo1.isPinned();
        if(flag2 && flag1 ^ true)
            return -1;
        if(!flag2 && flag1)
            return 1;
        if(!flag2 && flag1 ^ true && mStats != null)
        {
            resolvedcomponentinfo1 = (ResolverTarget)mTargetsDict.get(new ComponentName(((ResolveInfo) (obj)).activityInfo.packageName, ((ResolveInfo) (obj)).activityInfo.name));
            resolvedcomponentinfo = (ResolverTarget)mTargetsDict.get(new ComponentName(resolveinfo.activityInfo.packageName, resolveinfo.activityInfo.name));
            if(resolvedcomponentinfo1 != null && resolvedcomponentinfo != null)
            {
                int j = Float.compare(resolvedcomponentinfo.getSelectProbability(), resolvedcomponentinfo1.getSelectProbability());
                if(j != 0)
                {
                    if(j > 0)
                        j = 1;
                    else
                        j = -1;
                    return j;
                }
            }
        }
        resolvedcomponentinfo1 = ((ResolveInfo) (obj)).loadLabel(mPm);
        resolvedcomponentinfo = resolvedcomponentinfo1;
        if(resolvedcomponentinfo1 == null)
            resolvedcomponentinfo = ((ResolveInfo) (obj)).activityInfo.name;
        obj = resolveinfo.loadLabel(mPm);
        resolvedcomponentinfo1 = ((ResolverActivity.ResolvedComponentInfo) (obj));
        if(obj == null)
            resolvedcomponentinfo1 = resolveinfo.activityInfo.name;
        return mCollator.compare(resolvedcomponentinfo.toString().trim(), resolvedcomponentinfo1.toString().trim());
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((ResolverActivity.ResolvedComponentInfo)obj, (ResolverActivity.ResolvedComponentInfo)obj1);
    }

    public void compute(List list)
    {
        reset();
        long l = mCurrentTime;
        float f = 1.0F;
        float f1 = 1.0F;
        float f2 = 1.0F;
        float f3 = 1.0F;
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            ResolverActivity.ResolvedComponentInfo resolvedcomponentinfo = (ResolverActivity.ResolvedComponentInfo)list.next();
            ResolverTarget resolvertarget = new ResolverTarget();
            mTargetsDict.put(resolvedcomponentinfo.name, resolvertarget);
            UsageStats usagestats = (UsageStats)mStats.get(resolvedcomponentinfo.name.getPackageName());
            if(usagestats != null)
            {
                float f4 = f;
                if(!resolvedcomponentinfo.name.getPackageName().equals(mReferrerPackage))
                {
                    f4 = f;
                    if(isPersistentProcess(resolvedcomponentinfo) ^ true)
                    {
                        float f5 = Math.max(usagestats.getLastTimeUsed() - (l - 0x2932e00L), 0L);
                        resolvertarget.setRecencyScore(f5);
                        f4 = f;
                        if(f5 > f)
                            f4 = f5;
                    }
                }
                float f6 = usagestats.getTotalTimeInForeground();
                resolvertarget.setTimeSpentScore(f6);
                float f8 = f1;
                if(f6 > f1)
                    f8 = f6;
                f6 = usagestats.mLaunchCount;
                resolvertarget.setLaunchScore(f6);
                float f9 = f2;
                if(f6 > f2)
                    f9 = f6;
                f2 = 0.0F;
                f6 = f2;
                if(usagestats.mChooserCounts != null)
                {
                    f6 = f2;
                    if(mAction != null)
                    {
                        f6 = f2;
                        if(usagestats.mChooserCounts.get(mAction) != null)
                        {
                            f2 = ((Integer)((ArrayMap)usagestats.mChooserCounts.get(mAction)).getOrDefault(mContentType, Integer.valueOf(0))).intValue();
                            f6 = f2;
                            if(mAnnotations != null)
                            {
                                int i = mAnnotations.length;
                                int j = 0;
                                do
                                {
                                    f6 = f2;
                                    if(j >= i)
                                        break;
                                    f2 += ((Integer)((ArrayMap)usagestats.mChooserCounts.get(mAction)).getOrDefault(mAnnotations[j], Integer.valueOf(0))).intValue();
                                    j++;
                                } while(true);
                            }
                        }
                    }
                }
                resolvertarget.setChooserScore(f6);
                f2 = f9;
                f = f4;
                f1 = f8;
                if(f6 > f3)
                {
                    f3 = f6;
                    f2 = f9;
                    f = f4;
                    f1 = f8;
                }
            }
        } while(true);
        mTargets = new ArrayList(mTargetsDict.values());
        for(Iterator iterator = mTargets.iterator(); iterator.hasNext(); addDefaultSelectProbability(list))
        {
            list = (ResolverTarget)iterator.next();
            float f7 = list.getRecencyScore() / f;
            setFeatures(list, 2.0F * (f7 * f7), list.getLaunchScore() / f2, list.getTimeSpentScore() / f1, list.getChooserScore() / f3);
        }

        predictSelectProbabilities(mTargets);
    }

    public void destroy()
    {
        mHandler.removeMessages(0);
        mHandler.removeMessages(1);
        if(mConnection != null)
        {
            mContext.unbindService(mConnection);
            mConnection.destroy();
        }
        if(mAfterCompute != null)
            mAfterCompute.afterCompute();
    }

    public void getContentAnnotations(Intent intent)
    {
        intent = intent.getStringArrayListExtra("android.intent.extra.CONTENT_ANNOTATIONS");
        if(intent != null)
        {
            int i = intent.size();
            int k = i;
            if(i > 3)
                k = 3;
            mAnnotations = new String[k];
            for(int j = 0; j < k; j++)
                mAnnotations[j] = (String)intent.get(j);

        }
    }

    public float getScore(ComponentName componentname)
    {
        componentname = (ResolverTarget)mTargetsDict.get(componentname);
        if(componentname != null)
            return componentname.getSelectProbability();
        else
            return 0.0F;
    }

    public void setCallBack(AfterCompute aftercompute)
    {
        mAfterCompute = aftercompute;
    }

    public void updateChooserCounts(String s, int i, String s1)
    {
        if(mUsm != null)
            mUsm.reportChooserSelection(s, i, mContentType, mAnnotations, s1);
    }

    public void updateModel(ComponentName componentname)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IResolverRankerService iresolverrankerservice = mRanker;
        if(iresolverrankerservice == null)
            break MISSING_BLOCK_LABEL_124;
        int i;
        ArrayList arraylist = JVM INSTR new #624 <Class ArrayList>;
        arraylist.ArrayList(mTargetsDict.keySet());
        i = arraylist.indexOf(componentname);
        if(i < 0)
            break MISSING_BLOCK_LABEL_124;
        float f;
        if(mTargets == null)
            break MISSING_BLOCK_LABEL_124;
        f = getScore(componentname);
        int j = 0;
        for(componentname = mTargets.iterator(); componentname.hasNext();)
            if(((ResolverTarget)componentname.next()).getSelectProbability() > f)
                j++;

        logMetrics(j);
        mRanker.train(mTargets, i);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        RemoteException remoteexception;
        remoteexception;
        componentname = JVM INSTR new #392 <Class StringBuilder>;
        componentname.StringBuilder();
        Log.e("ResolverComparator", componentname.append("Error in Train: ").append(remoteexception).toString());
          goto _L1
        componentname;
        throw componentname;
    }

    private static final int CONNECTION_COST_TIMEOUT_MILLIS = 200;
    private static final boolean DEBUG = false;
    private static final int NUM_OF_TOP_ANNOTATIONS_TO_USE = 3;
    private static final float RECENCY_MULTIPLIER = 2F;
    private static final long RECENCY_TIME_PERIOD = 0x2932e00L;
    private static final int RESOLVER_RANKER_RESULT_TIMEOUT = 1;
    private static final int RESOLVER_RANKER_SERVICE_RESULT = 0;
    private static final String TAG = "ResolverComparator";
    private static final long USAGE_STATS_PERIOD = 0x240c8400L;
    private static final int WATCHDOG_TIMEOUT_MILLIS = 500;
    private String mAction;
    private AfterCompute mAfterCompute;
    private String mAnnotations[];
    private final Collator mCollator;
    private CountDownLatch mConnectSignal;
    private ResolverRankerServiceConnection mConnection;
    private String mContentType;
    private Context mContext;
    private final long mCurrentTime = System.currentTimeMillis();
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 1: default 28
        //                       0 34
        //                       1 253;
               goto _L1 _L2 _L3
_L1:
            super.handleMessage(message);
_L5:
            return;
_L2:
            if(ResolverComparator._2D_get1(ResolverComparator.this).hasMessages(1))
            {
                if(message.obj != null)
                {
                    message = (List)message.obj;
                    if(message != null && ResolverComparator._2D_get4(ResolverComparator.this) != null && message.size() == ResolverComparator._2D_get4(ResolverComparator.this).size())
                    {
                        int i = ResolverComparator._2D_get4(ResolverComparator.this).size();
                        boolean flag1 = false;
                        for(int j = 0; j < i; j++)
                        {
                            float f = ((ResolverTarget)message.get(j)).getSelectProbability();
                            if(f != ((ResolverTarget)ResolverComparator._2D_get4(ResolverComparator.this).get(j)).getSelectProbability())
                            {
                                ((ResolverTarget)ResolverComparator._2D_get4(ResolverComparator.this).get(j)).setSelectProbability(f);
                                flag1 = true;
                            }
                        }

                        if(flag1)
                            ResolverComparator._2D_set1(ResolverComparator.this, ResolverComparator._2D_get3(ResolverComparator.this));
                    } else
                    {
                        Log.e("ResolverComparator", "Sizes of sent and received ResolverTargets diff.");
                    }
                } else
                {
                    Log.e("ResolverComparator", "Receiving null prediction results.");
                }
                ResolverComparator._2D_get1(ResolverComparator.this).removeMessages(1);
                ResolverComparator._2D_get0(ResolverComparator.this).afterCompute();
            }
            continue; /* Loop/switch isn't completed */
_L3:
            ResolverComparator._2D_get1(ResolverComparator.this).removeMessages(0);
            ResolverComparator._2D_get0(ResolverComparator.this).afterCompute();
            if(true) goto _L5; else goto _L4
_L4:
        }

        final ResolverComparator this$0;

            
            {
                this$0 = ResolverComparator.this;
                super(looper);
            }
    }
;
    private final boolean mHttp;
    private final Object mLock = new Object();
    private final PackageManager mPm;
    private IResolverRankerService mRanker;
    private ComponentName mRankerServiceName;
    private final String mReferrerPackage;
    private ComponentName mResolvedRankerName;
    private final long mSinceTime;
    private final Map mStats;
    private ArrayList mTargets;
    private final LinkedHashMap mTargetsDict = new LinkedHashMap();
    private final UsageStatsManager mUsm;

    // Unreferenced inner class com/android/internal/app/ResolverComparator$ResolverRankerServiceConnection$1

/* anonymous class */
    class ResolverRankerServiceConnection._cls1 extends android.service.resolver.IResolverRankerResult.Stub
    {

        public void sendResult(List list)
            throws RemoteException
        {
            Object obj = ResolverComparator._2D_get2(_fld0);
            obj;
            JVM INSTR monitorenter ;
            Message message = Message.obtain();
            message.what = 0;
            message.obj = list;
            ResolverComparator._2D_get1(_fld0).sendMessage(message);
            obj;
            JVM INSTR monitorexit ;
            return;
            list;
            throw list;
        }

        final ResolverRankerServiceConnection this$1;

            
            {
                this$1 = ResolverRankerServiceConnection.this;
                super();
            }
    }

}
