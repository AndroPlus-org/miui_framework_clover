// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.app.backup.BackupAgent;
import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDebug;
import android.ddm.DdmHandleAppName;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.display.DisplayManagerGlobal;
import android.net.*;
import android.os.*;
import android.provider.FontsContract;
import android.renderscript.RenderScriptCacheDir;
import android.security.NetworkSecurityPolicy;
import android.security.net.config.NetworkSecurityConfigProvider;
import android.util.*;
import android.view.*;
import android.webkit.WebView;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.content.ReferrerIntent;
import com.android.internal.os.*;
import com.android.internal.util.*;
import com.android.org.conscrypt.OpenSSLSocketImpl;
import com.android.org.conscrypt.TrustedCertificateStore;
import com.miui.whetstone.app.WhetstoneAppManager;
import dalvik.system.*;
import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import libcore.io.*;
import libcore.net.event.NetworkEventDispatcher;
import org.apache.harmony.dalvik.ddmc.DdmVmInternal;

// Referenced classes of package android.app:
//            ResourcesManager, ActivityManager, IActivityManager, Instrumentation, 
//            ContextImpl, LoadedApk, Application, Activity, 
//            FragmentController, ResultInfo, ProfilerInfo, ApplicationPackageManager, 
//            DexLoadReporter, ActivityThreadInjector, Service, QueuedWork, 
//            ContentProviderHolder, FilePinner, OnActivityPausedListener, ActivityOptions, 
//            IUiAutomationConnection, IInstrumentationWatcher, ServiceStartArgs, RemoteServiceException

public final class ActivityThread
{
    static final class ActivityClientRecord
    {

        static Configuration _2D_get0(ActivityClientRecord activityclientrecord)
        {
            return activityclientrecord.tmpConfig;
        }

        public String getStateString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("ActivityClientRecord{");
            stringbuilder.append("paused=").append(paused);
            stringbuilder.append(", stopped=").append(stopped);
            stringbuilder.append(", hideForNow=").append(hideForNow);
            stringbuilder.append(", startsNotResumed=").append(startsNotResumed);
            stringbuilder.append(", isForward=").append(isForward);
            stringbuilder.append(", pendingConfigChanges=").append(pendingConfigChanges);
            stringbuilder.append(", onlyLocalRequest=").append(onlyLocalRequest);
            stringbuilder.append(", preserveWindow=").append(mPreserveWindow);
            if(activity != null)
            {
                stringbuilder.append(", Activity{");
                stringbuilder.append("resumed=").append(activity.mResumed);
                stringbuilder.append(", stopped=").append(activity.mStopped);
                stringbuilder.append(", finished=").append(activity.isFinishing());
                stringbuilder.append(", destroyed=").append(activity.isDestroyed());
                stringbuilder.append(", startedActivity=").append(activity.mStartedActivity);
                stringbuilder.append(", temporaryPause=").append(activity.mTemporaryPause);
                stringbuilder.append(", changingConfigurations=").append(activity.mChangingConfigurations);
                stringbuilder.append("}");
            }
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        public boolean isPersistable()
        {
            boolean flag;
            if(activityInfo.persistableMode == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isPreHoneycomb()
        {
            boolean flag = false;
            if(activity != null)
            {
                if(activity.getApplicationInfo().targetSdkVersion < 11)
                    flag = true;
                return flag;
            } else
            {
                return false;
            }
        }

        void lambda$_2D_android_app_ActivityThread$ActivityClientRecord_15980(Configuration configuration, int i)
        {
            if(activity == null)
            {
                throw new IllegalStateException("Received config update for non-existing activity");
            } else
            {
                activity.mMainThread.handleActivityConfigurationChanged(new ActivityConfigChangeData(token, configuration), i);
                return;
            }
        }

        public String toString()
        {
            Object obj;
            StringBuilder stringbuilder;
            if(intent != null)
                obj = intent.getComponent();
            else
                obj = null;
            stringbuilder = (new StringBuilder()).append("ActivityRecord{").append(Integer.toHexString(System.identityHashCode(this))).append(" token=").append(token).append(" ");
            if(obj == null)
                obj = "no component name";
            else
                obj = ((ComponentName) (obj)).toShortString();
            return stringbuilder.append(((String) (obj))).append("}").toString();
        }

        Activity activity;
        ActivityInfo activityInfo;
        CompatibilityInfo compatInfo;
        android.view.ViewRootImpl.ActivityConfigCallback configCallback;
        Configuration createdConfig;
        String embeddedID;
        boolean hideForNow;
        int ident;
        Intent intent;
        boolean isForward;
        Activity.NonConfigurationInstances lastNonConfigurationInstances;
        int lastProcessedSeq;
        Window mPendingRemoveWindow;
        WindowManager mPendingRemoveWindowManager;
        boolean mPreserveWindow;
        Configuration newConfig;
        ActivityClientRecord nextIdle;
        boolean onlyLocalRequest;
        Configuration overrideConfig;
        LoadedApk packageInfo;
        Activity parent;
        boolean paused;
        int pendingConfigChanges;
        List pendingIntents;
        List pendingResults;
        PersistableBundle persistentState;
        ProfilerInfo profilerInfo;
        String referrer;
        int relaunchSeq;
        boolean startsNotResumed;
        Bundle state;
        boolean stopped;
        private Configuration tmpConfig;
        IBinder token;
        IVoiceInteractor voiceInteractor;
        Window window;

        ActivityClientRecord()
        {
            tmpConfig = new Configuration();
            relaunchSeq = 0;
            lastProcessedSeq = 0;
            parent = null;
            embeddedID = null;
            paused = false;
            stopped = false;
            hideForNow = false;
            nextIdle = null;
            configCallback = new _.Lambda._cls9I5WEMsoBc7l4QrNqZ4wx59yuHU(this);
        }
    }

    static final class ActivityConfigChangeData
    {

        final IBinder activityToken;
        final Configuration overrideConfig;

        public ActivityConfigChangeData(IBinder ibinder, Configuration configuration)
        {
            activityToken = ibinder;
            overrideConfig = configuration;
        }
    }

    static final class AppBindData
    {

        public String toString()
        {
            return (new StringBuilder()).append("AppBindData{appInfo=").append(appInfo).append("}").toString();
        }

        ApplicationInfo appInfo;
        String buildSerial;
        CompatibilityInfo compatInfo;
        Configuration config;
        int debugMode;
        boolean enableBinderTracking;
        LoadedApk info;
        ProfilerInfo initProfilerInfo;
        Bundle instrumentationArgs;
        ComponentName instrumentationName;
        IUiAutomationConnection instrumentationUiAutomationConnection;
        IInstrumentationWatcher instrumentationWatcher;
        boolean persistent;
        String processName;
        List providers;
        boolean restrictedBackupMode;
        boolean trackAllocation;

        AppBindData()
        {
        }
    }

    private class ApplicationThread extends IApplicationThread.Stub
    {

        static void _2D_wrap0(ApplicationThread applicationthread, ParcelFileDescriptor parcelfiledescriptor, String as[])
        {
            applicationthread.dumpDatabaseInfo(parcelfiledescriptor, as);
        }

        private void dumpDatabaseInfo(ParcelFileDescriptor parcelfiledescriptor, String as[])
        {
            parcelfiledescriptor = new FastPrintWriter(new FileOutputStream(parcelfiledescriptor.getFileDescriptor()));
            SQLiteDebug.dump(new PrintWriterPrinter(parcelfiledescriptor), as);
            parcelfiledescriptor.flush();
        }

        private void dumpMemInfo(PrintWriter printwriter, android.os.Debug.MemoryInfo memoryinfo, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4)
        {
            long l = Debug.getNativeHeapSize() / 1024L;
            long l1 = Debug.getNativeHeapAllocatedSize() / 1024L;
            long l2 = Debug.getNativeHeapFreeSize() / 1024L;
            Object obj = Runtime.getRuntime();
            ((Runtime) (obj)).gc();
            long l3 = ((Runtime) (obj)).totalMemory() / 1024L;
            long l4 = ((Runtime) (obj)).freeMemory() / 1024L;
            long al[] = VMDebug.countInstancesOfClasses(new Class[] {
                android/app/ContextImpl, android/app/Activity, android/webkit/WebView, com/android/org/conscrypt/OpenSSLSocketImpl
            }, true);
            long l5 = al[0];
            long l6 = al[1];
            long l7 = al[2];
            long l8 = al[3];
            long l9 = ViewDebug.getViewInstanceCount();
            long l10 = ViewDebug.getViewRootImplCount();
            int i = AssetManager.getGlobalAssetCount();
            int j = AssetManager.getGlobalAssetManagerCount();
            int k = Debug.getBinderLocalObjectCount();
            int i1 = Debug.getBinderProxyObjectCount();
            int k1 = Debug.getBinderDeathObjectCount();
            long l11 = Parcel.getGlobalAllocSize();
            long l12 = Parcel.getGlobalAllocCount();
            android.database.sqlite.SQLiteDebug.PagerStats pagerstats = SQLiteDebug.getDatabaseInfo();
            int i2 = Process.myPid();
            if(mBoundApplication != null)
                al = mBoundApplication.processName;
            else
                al = "unknown";
            ActivityThread.dumpMemInfoTable(printwriter, memoryinfo, flag, flag1, flag2, flag3, i2, al, l, l1, l2, l3, l3 - l4, l4);
            if(flag)
            {
                printwriter.print(l9);
                printwriter.print(',');
                printwriter.print(l10);
                printwriter.print(',');
                printwriter.print(l5);
                printwriter.print(',');
                printwriter.print(l6);
                printwriter.print(',');
                printwriter.print(i);
                printwriter.print(',');
                printwriter.print(j);
                printwriter.print(',');
                printwriter.print(k);
                printwriter.print(',');
                printwriter.print(i1);
                printwriter.print(',');
                printwriter.print(k1);
                printwriter.print(',');
                printwriter.print(l8);
                printwriter.print(',');
                printwriter.print(pagerstats.memoryUsed / 1024);
                printwriter.print(',');
                printwriter.print(pagerstats.memoryUsed / 1024);
                printwriter.print(',');
                printwriter.print(pagerstats.pageCacheOverflow / 1024);
                printwriter.print(',');
                printwriter.print(pagerstats.largestMemAlloc / 1024);
                for(i1 = 0; i1 < pagerstats.dbStats.size(); i1++)
                {
                    memoryinfo = (android.database.sqlite.SQLiteDebug.DbStats)pagerstats.dbStats.get(i1);
                    printwriter.print(',');
                    printwriter.print(((android.database.sqlite.SQLiteDebug.DbStats) (memoryinfo)).dbName);
                    printwriter.print(',');
                    printwriter.print(((android.database.sqlite.SQLiteDebug.DbStats) (memoryinfo)).pageSize);
                    printwriter.print(',');
                    printwriter.print(((android.database.sqlite.SQLiteDebug.DbStats) (memoryinfo)).dbSize);
                    printwriter.print(',');
                    printwriter.print(((android.database.sqlite.SQLiteDebug.DbStats) (memoryinfo)).lookaside);
                    printwriter.print(',');
                    printwriter.print(((android.database.sqlite.SQLiteDebug.DbStats) (memoryinfo)).cache);
                    printwriter.print(',');
                    printwriter.print(((android.database.sqlite.SQLiteDebug.DbStats) (memoryinfo)).cache);
                }

                printwriter.println();
                return;
            }
            printwriter.println(" ");
            printwriter.println(" Objects");
            ActivityThread.printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "Views:", Long.valueOf(l9), "ViewRootImpl:", Long.valueOf(l10)
            });
            ActivityThread.printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "AppContexts:", Long.valueOf(l5), "Activities:", Long.valueOf(l6)
            });
            ActivityThread.printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "Assets:", Integer.valueOf(i), "AssetManagers:", Integer.valueOf(j)
            });
            ActivityThread.printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "Local Binders:", Integer.valueOf(k), "Proxy Binders:", Integer.valueOf(i1)
            });
            ActivityThread.printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "Parcel memory:", Long.valueOf(l11 / 1024L), "Parcel count:", Long.valueOf(l12)
            });
            ActivityThread.printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "Death Recipients:", Integer.valueOf(k1), "OpenSSL Sockets:", Long.valueOf(l8)
            });
            ActivityThread.printRow(printwriter, "%21s %8d", new Object[] {
                "WebViews:", Long.valueOf(l7)
            });
            printwriter.println(" ");
            printwriter.println(" SQL");
            ActivityThread.printRow(printwriter, "%21s %8d", new Object[] {
                "MEMORY_USED:", Integer.valueOf(pagerstats.memoryUsed / 1024)
            });
            ActivityThread.printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "PAGECACHE_OVERFLOW:", Integer.valueOf(pagerstats.pageCacheOverflow / 1024), "MALLOC_SIZE:", Integer.valueOf(pagerstats.largestMemAlloc / 1024)
            });
            printwriter.println(" ");
            i2 = pagerstats.dbStats.size();
            if(i2 > 0)
            {
                printwriter.println(" DATABASES");
                ActivityThread.printRow(printwriter, "  %8s %8s %14s %14s  %s", new Object[] {
                    "pgsz", "dbsz", "Lookaside(b)", "cache", "Dbname"
                });
                int j1 = 0;
                while(j1 < i2) 
                {
                    android.database.sqlite.SQLiteDebug.DbStats dbstats = (android.database.sqlite.SQLiteDebug.DbStats)pagerstats.dbStats.get(j1);
                    String s;
                    String s1;
                    if(dbstats.pageSize > 0L)
                        memoryinfo = String.valueOf(dbstats.pageSize);
                    else
                        memoryinfo = " ";
                    if(dbstats.dbSize > 0L)
                        s = String.valueOf(dbstats.dbSize);
                    else
                        s = " ";
                    if(dbstats.lookaside > 0)
                        s1 = String.valueOf(dbstats.lookaside);
                    else
                        s1 = " ";
                    ActivityThread.printRow(printwriter, "  %8s %8s %14s %14s  %s", new Object[] {
                        memoryinfo, s, s1, dbstats.cache, dbstats.dbName
                    });
                    j1++;
                }
            }
            memoryinfo = AssetManager.getAssetAllocations();
            if(memoryinfo != null)
            {
                printwriter.println(" ");
                printwriter.println(" Asset Allocations");
                printwriter.print(memoryinfo);
            }
            if(flag4)
            {
                if(mBoundApplication != null && (mBoundApplication.appInfo.flags & 2) != 0)
                    flag = true;
                else
                    flag = Build.IS_DEBUGGABLE;
                printwriter.println(" ");
                printwriter.println(" Unreachable memory");
                printwriter.print(Debug.getUnreachableMemory(100, flag));
            }
        }

        private void updatePendingConfiguration(Configuration configuration)
        {
            ResourcesManager resourcesmanager = ActivityThread._2D_get1(ActivityThread.this);
            resourcesmanager;
            JVM INSTR monitorenter ;
            if(mPendingConfiguration == null || mPendingConfiguration.isOtherSeqNewer(configuration))
                mPendingConfiguration = configuration;
            resourcesmanager;
            JVM INSTR monitorexit ;
            return;
            configuration;
            throw configuration;
        }

        public void attachAgent(String s)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 155, s);
        }

        public final void bindApplication(String s, ApplicationInfo applicationinfo, List list, ComponentName componentname, ProfilerInfo profilerinfo, Bundle bundle, IInstrumentationWatcher iinstrumentationwatcher, 
                IUiAutomationConnection iuiautomationconnection, int i, boolean flag, boolean flag1, boolean flag2, boolean flag3, Configuration configuration, 
                CompatibilityInfo compatibilityinfo, Map map, Bundle bundle1, String s1)
        {
            ActivityThread._2D_set1(applicationinfo.waitingToUse);
            if(map != null)
                ServiceManager.initServiceCache(map);
            setCoreSettings(bundle1);
            map = new AppBindData();
            map.processName = s;
            map.appInfo = applicationinfo;
            map.providers = list;
            map.instrumentationName = componentname;
            map.instrumentationArgs = bundle;
            map.instrumentationWatcher = iinstrumentationwatcher;
            map.instrumentationUiAutomationConnection = iuiautomationconnection;
            map.debugMode = i;
            map.enableBinderTracking = flag;
            map.trackAllocation = flag1;
            map.restrictedBackupMode = flag2;
            map.persistent = flag3;
            map.config = configuration;
            map.compatInfo = compatibilityinfo;
            map.initProfilerInfo = profilerinfo;
            map.buildSerial = s1;
            ActivityThread._2D_wrap31(ActivityThread.this, 110, map);
        }

        public void clearDnsCache()
        {
            InetAddress.clearDnsCache();
            NetworkEventDispatcher.getInstance().onNetworkConfigurationChanged();
        }

        public void dispatchPackageBroadcast(int i, String as[])
        {
            ActivityThread._2D_wrap32(ActivityThread.this, 133, as, i);
        }

        public void dumpActivity(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String s, String as[])
        {
            DumpComponentInfo dumpcomponentinfo = new DumpComponentInfo();
            dumpcomponentinfo.fd = parcelfiledescriptor.dup();
            dumpcomponentinfo.token = ibinder;
            dumpcomponentinfo.prefix = s;
            dumpcomponentinfo.args = as;
            ActivityThread._2D_wrap33(ActivityThread.this, 136, dumpcomponentinfo, 0, 0, true);
            IoUtils.closeQuietly(parcelfiledescriptor);
_L2:
            return;
            ibinder;
            Slog.w("ActivityThread", "dumpActivity failed", ibinder);
            IoUtils.closeQuietly(parcelfiledescriptor);
            if(true) goto _L2; else goto _L1
_L1:
            ibinder;
            IoUtils.closeQuietly(parcelfiledescriptor);
            throw ibinder;
        }

        public void dumpDbInfo(ParcelFileDescriptor parcelfiledescriptor, String as[])
        {
            if(!mSystemThread) goto _L2; else goto _L1
_L1:
            final ParcelFileDescriptor dup = parcelfiledescriptor.dup();
            IoUtils.closeQuietly(parcelfiledescriptor);
            AsyncTask.THREAD_POOL_EXECUTOR.execute(as. new Runnable() {

                public void run()
                {
                    ApplicationThread._2D_wrap0(ApplicationThread.this, dup, args);
                    IoUtils.closeQuietly(dup);
                    return;
                    Exception exception;
                    exception;
                    IoUtils.closeQuietly(dup);
                    throw exception;
                }

                final ApplicationThread this$1;
                final String val$args[];
                final ParcelFileDescriptor val$dup;

            
            {
                this$1 = final_applicationthread;
                dup = parcelfiledescriptor;
                args = _5B_Ljava.lang.String_3B_.this;
                super();
            }
            }
);
_L4:
            return;
            as;
            as = JVM INSTR new #516 <Class StringBuilder>;
            as.StringBuilder();
            Log.w("ActivityThread", as.append("Could not dup FD ").append(parcelfiledescriptor.getFileDescriptor().getInt$()).toString());
            IoUtils.closeQuietly(parcelfiledescriptor);
            return;
            as;
            IoUtils.closeQuietly(parcelfiledescriptor);
            throw as;
_L2:
            dumpDatabaseInfo(parcelfiledescriptor, as);
            IoUtils.closeQuietly(parcelfiledescriptor);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void dumpGfxInfo(ParcelFileDescriptor parcelfiledescriptor, String as[])
        {
            ActivityThread._2D_wrap30(ActivityThread.this, parcelfiledescriptor.getFileDescriptor());
            WindowManagerGlobal.getInstance().dumpGfxInfo(parcelfiledescriptor.getFileDescriptor(), as);
            IoUtils.closeQuietly(parcelfiledescriptor);
        }

        public void dumpHeap(boolean flag, boolean flag1, boolean flag2, String s, ParcelFileDescriptor parcelfiledescriptor)
        {
            DumpHeapData dumpheapdata = new DumpHeapData();
            dumpheapdata.managed = flag;
            dumpheapdata.mallocInfo = flag1;
            dumpheapdata.runGc = flag2;
            dumpheapdata.path = s;
            dumpheapdata.fd = parcelfiledescriptor;
            ActivityThread._2D_wrap33(ActivityThread.this, 135, dumpheapdata, 0, 0, true);
        }

        public void dumpMemInfo(ParcelFileDescriptor parcelfiledescriptor, android.os.Debug.MemoryInfo memoryinfo, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, 
                String as[])
        {
            as = new FastPrintWriter(new FileOutputStream(parcelfiledescriptor.getFileDescriptor()));
            dumpMemInfo(((PrintWriter) (as)), memoryinfo, flag, flag1, flag2, flag3, flag4);
            as.flush();
            IoUtils.closeQuietly(parcelfiledescriptor);
            return;
            memoryinfo;
            as.flush();
            IoUtils.closeQuietly(parcelfiledescriptor);
            throw memoryinfo;
        }

        public void dumpProvider(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String as[])
        {
            DumpComponentInfo dumpcomponentinfo = new DumpComponentInfo();
            dumpcomponentinfo.fd = parcelfiledescriptor.dup();
            dumpcomponentinfo.token = ibinder;
            dumpcomponentinfo.args = as;
            ActivityThread._2D_wrap33(ActivityThread.this, 141, dumpcomponentinfo, 0, 0, true);
            IoUtils.closeQuietly(parcelfiledescriptor);
_L2:
            return;
            ibinder;
            Slog.w("ActivityThread", "dumpProvider failed", ibinder);
            IoUtils.closeQuietly(parcelfiledescriptor);
            if(true) goto _L2; else goto _L1
_L1:
            ibinder;
            IoUtils.closeQuietly(parcelfiledescriptor);
            throw ibinder;
        }

        public void dumpService(ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String as[])
        {
            DumpComponentInfo dumpcomponentinfo = new DumpComponentInfo();
            dumpcomponentinfo.fd = parcelfiledescriptor.dup();
            dumpcomponentinfo.token = ibinder;
            dumpcomponentinfo.args = as;
            ActivityThread._2D_wrap33(ActivityThread.this, 123, dumpcomponentinfo, 0, 0, true);
            IoUtils.closeQuietly(parcelfiledescriptor);
_L2:
            return;
            ibinder;
            Slog.w("ActivityThread", "dumpService failed", ibinder);
            IoUtils.closeQuietly(parcelfiledescriptor);
            if(true) goto _L2; else goto _L1
_L1:
            ibinder;
            IoUtils.closeQuietly(parcelfiledescriptor);
            throw ibinder;
        }

        public void handleTrustStorageUpdate()
        {
            NetworkSecurityPolicy.getInstance().handleTrustStorageUpdate();
        }

        public void notifyCleartextNetwork(byte abyte0[])
        {
            if(StrictMode.vmCleartextNetworkEnabled())
                StrictMode.onCleartextNetworkDetected(abyte0);
        }

        public void notifyPackageForeground()
        {
            if(ActivityThread._2D_get2())
                ActivityThread._2D_set1(false);
        }

        public void processInBackground()
        {
            mH.removeMessages(120);
            mH.sendMessage(mH.obtainMessage(120));
        }

        public void profilerControl(boolean flag, ProfilerInfo profilerinfo, int i)
        {
            ActivityThread activitythread = ActivityThread.this;
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            ActivityThread._2D_wrap34(activitythread, 127, profilerinfo, j, i);
        }

        public void requestAssistContextExtras(IBinder ibinder, IBinder ibinder1, int i, int j, int k)
        {
            RequestAssistContextExtras requestassistcontextextras = new RequestAssistContextExtras();
            requestassistcontextextras.activityToken = ibinder;
            requestassistcontextextras.requestToken = ibinder1;
            requestassistcontextextras.requestType = i;
            requestassistcontextextras.sessionId = j;
            requestassistcontextextras.flags = k;
            ActivityThread._2D_wrap31(ActivityThread.this, 143, requestassistcontextextras);
        }

        public void scheduleActivityConfigurationChanged(IBinder ibinder, Configuration configuration)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 125, new ActivityConfigChangeData(ibinder, configuration));
        }

        public void scheduleActivityMovedToDisplay(IBinder ibinder, int i, Configuration configuration)
        {
            ActivityThread._2D_wrap32(ActivityThread.this, 157, new ActivityConfigChangeData(ibinder, configuration), i);
        }

        public void scheduleApplicationInfoChanged(ApplicationInfo applicationinfo)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 156, applicationinfo);
        }

        public final void scheduleBindService(IBinder ibinder, Intent intent, boolean flag, int i)
        {
            updateProcessState(i, false);
            BindServiceData bindservicedata = new BindServiceData();
            bindservicedata.token = ibinder;
            bindservicedata.intent = intent;
            bindservicedata.rebind = flag;
            ActivityThread._2D_wrap31(ActivityThread.this, 121, bindservicedata);
        }

        public void scheduleConfigurationChanged(Configuration configuration)
        {
            updatePendingConfiguration(configuration);
            ActivityThread._2D_wrap31(ActivityThread.this, 118, configuration);
        }

        public void scheduleCrash(String s)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 134, s);
        }

        public final void scheduleCreateBackupAgent(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo, int i)
        {
            CreateBackupAgentData createbackupagentdata = new CreateBackupAgentData();
            createbackupagentdata.appInfo = applicationinfo;
            createbackupagentdata.compatInfo = compatibilityinfo;
            createbackupagentdata.backupMode = i;
            ActivityThread._2D_wrap31(ActivityThread.this, 128, createbackupagentdata);
        }

        public final void scheduleCreateService(IBinder ibinder, ServiceInfo serviceinfo, CompatibilityInfo compatibilityinfo, int i)
        {
            updateProcessState(i, false);
            CreateServiceData createservicedata = new CreateServiceData();
            createservicedata.token = ibinder;
            createservicedata.info = serviceinfo;
            createservicedata.compatInfo = compatibilityinfo;
            ActivityThread._2D_wrap31(ActivityThread.this, 114, createservicedata);
        }

        public final void scheduleDestroyActivity(IBinder ibinder, boolean flag, int i)
        {
            ActivityThread activitythread = ActivityThread.this;
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            ActivityThread._2D_wrap34(activitythread, 109, ibinder, j, i);
        }

        public final void scheduleDestroyBackupAgent(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo)
        {
            CreateBackupAgentData createbackupagentdata = new CreateBackupAgentData();
            createbackupagentdata.appInfo = applicationinfo;
            createbackupagentdata.compatInfo = compatibilityinfo;
            ActivityThread._2D_wrap31(ActivityThread.this, 129, createbackupagentdata);
        }

        public void scheduleEnterAnimationComplete(IBinder ibinder)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 149, ibinder);
        }

        public final void scheduleExit()
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 111, null);
        }

        public void scheduleInstallProvider(ProviderInfo providerinfo)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 145, providerinfo);
        }

        public final void scheduleLaunchActivity(Intent intent, IBinder ibinder, int i, ActivityInfo activityinfo, Configuration configuration, Configuration configuration1, CompatibilityInfo compatibilityinfo, 
                String s, IVoiceInteractor ivoiceinteractor, int j, Bundle bundle, PersistableBundle persistablebundle, List list, List list1, 
                boolean flag, boolean flag1, ProfilerInfo profilerinfo)
        {
            updateProcessState(j, false);
            ActivityClientRecord activityclientrecord = new ActivityClientRecord();
            activityclientrecord.token = ibinder;
            activityclientrecord.ident = i;
            activityclientrecord.intent = intent;
            activityclientrecord.referrer = s;
            activityclientrecord.voiceInteractor = ivoiceinteractor;
            activityclientrecord.activityInfo = activityinfo;
            activityclientrecord.compatInfo = compatibilityinfo;
            activityclientrecord.state = bundle;
            activityclientrecord.persistentState = persistablebundle;
            activityclientrecord.pendingResults = list;
            activityclientrecord.pendingIntents = list1;
            activityclientrecord.startsNotResumed = flag;
            activityclientrecord.isForward = flag1;
            activityclientrecord.profilerInfo = profilerinfo;
            activityclientrecord.overrideConfig = configuration1;
            updatePendingConfiguration(configuration);
            ActivityThread._2D_wrap31(ActivityThread.this, 100, activityclientrecord);
        }

        public void scheduleLocalVoiceInteractionStarted(IBinder ibinder, IVoiceInteractor ivoiceinteractor)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = ibinder;
            someargs.arg2 = ivoiceinteractor;
            ActivityThread._2D_wrap31(ActivityThread.this, 154, someargs);
        }

        public void scheduleLowMemory()
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 124, null);
        }

        public void scheduleMultiWindowModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = ibinder;
            someargs.arg2 = configuration;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            someargs.argi1 = i;
            ActivityThread._2D_wrap31(ActivityThread.this, 152, someargs);
        }

        public final void scheduleNewIntent(List list, IBinder ibinder, boolean flag)
        {
            NewIntentData newintentdata = new NewIntentData();
            newintentdata.intents = list;
            newintentdata.token = ibinder;
            newintentdata.andPause = flag;
            ActivityThread._2D_wrap31(ActivityThread.this, 112, newintentdata);
        }

        public void scheduleOnNewActivityOptions(IBinder ibinder, Bundle bundle)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 146, new Pair(ibinder, ActivityOptions.fromBundle(bundle)));
        }

        public final void schedulePauseActivity(IBinder ibinder, boolean flag, boolean flag1, int i, boolean flag2)
        {
            byte byte0 = 0;
            int j = ActivityThread._2D_wrap0(ActivityThread.this);
            ActivityThread activitythread = ActivityThread.this;
            byte byte1;
            boolean flag3;
            if(flag)
                byte1 = 102;
            else
                byte1 = 101;
            if(flag1)
                flag3 = true;
            else
                flag3 = false;
            if(flag2)
                byte0 = 2;
            ActivityThread._2D_wrap35(activitythread, byte1, ibinder, flag3 | byte0, i, j);
        }

        public void schedulePictureInPictureModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = ibinder;
            someargs.arg2 = configuration;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            someargs.argi1 = i;
            ActivityThread._2D_wrap31(ActivityThread.this, 153, someargs);
        }

        public final void scheduleReceiver(Intent intent, ActivityInfo activityinfo, CompatibilityInfo compatibilityinfo, int i, String s, Bundle bundle, boolean flag, 
                int j, int k)
        {
            updateProcessState(k, false);
            intent = new ReceiverData(intent, i, s, bundle, flag, false, mAppThread.asBinder(), j);
            intent.info = activityinfo;
            intent.compatInfo = compatibilityinfo;
            ActivityThread._2D_wrap31(ActivityThread.this, 113, intent);
        }

        public void scheduleRegisteredReceiver(IIntentReceiver iintentreceiver, Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, 
                int j, int k)
            throws RemoteException
        {
            updateProcessState(k, false);
            iintentreceiver.performReceive(intent, i, s, bundle, flag, flag1, j);
        }

        public final void scheduleRelaunchActivity(IBinder ibinder, List list, List list1, int i, boolean flag, Configuration configuration, Configuration configuration1, 
                boolean flag1)
        {
            requestRelaunchActivity(ibinder, list, list1, i, flag, configuration, configuration1, true, flag1);
        }

        public final void scheduleResumeActivity(IBinder ibinder, int i, boolean flag, Bundle bundle)
        {
            int j = ActivityThread._2D_wrap0(ActivityThread.this);
            updateProcessState(i, false);
            bundle = ActivityThread.this;
            if(flag)
                i = 1;
            else
                i = 0;
            ActivityThread._2D_wrap35(bundle, 107, ibinder, i, 0, j);
        }

        public final void scheduleSendResult(IBinder ibinder, List list)
        {
            ResultData resultdata = new ResultData();
            resultdata.token = ibinder;
            resultdata.results = list;
            ActivityThread._2D_wrap31(ActivityThread.this, 108, resultdata);
        }

        public final void scheduleServiceArgs(IBinder ibinder, ParceledListSlice parceledlistslice)
        {
            List list = parceledlistslice.getList();
            for(int i = 0; i < list.size(); i++)
            {
                parceledlistslice = (ServiceStartArgs)list.get(i);
                ServiceArgsData serviceargsdata = new ServiceArgsData();
                serviceargsdata.token = ibinder;
                serviceargsdata.taskRemoved = ((ServiceStartArgs) (parceledlistslice)).taskRemoved;
                serviceargsdata.startId = ((ServiceStartArgs) (parceledlistslice)).startId;
                serviceargsdata.flags = ((ServiceStartArgs) (parceledlistslice)).flags;
                serviceargsdata.args = ((ServiceStartArgs) (parceledlistslice)).args;
                ActivityThread._2D_wrap31(ActivityThread.this, 115, serviceargsdata);
            }

        }

        public final void scheduleSleeping(IBinder ibinder, boolean flag)
        {
            ActivityThread activitythread = ActivityThread.this;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            ActivityThread._2D_wrap32(activitythread, 137, ibinder, i);
        }

        public final void scheduleStopActivity(IBinder ibinder, boolean flag, int i)
        {
            int j = ActivityThread._2D_wrap0(ActivityThread.this);
            ActivityThread activitythread = ActivityThread.this;
            byte byte0;
            if(flag)
                byte0 = 103;
            else
                byte0 = 104;
            ActivityThread._2D_wrap35(activitythread, byte0, ibinder, 0, i, j);
        }

        public final void scheduleStopService(IBinder ibinder)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 116, ibinder);
        }

        public final void scheduleSuicide()
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 130, null);
        }

        public void scheduleTranslucentConversionComplete(IBinder ibinder, boolean flag)
        {
            ActivityThread activitythread = ActivityThread.this;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            ActivityThread._2D_wrap32(activitythread, 144, ibinder, i);
        }

        public void scheduleTrimMemory(int i)
        {
            ActivityThread._2D_wrap32(ActivityThread.this, 140, null, i);
        }

        public final void scheduleUnbindService(IBinder ibinder, Intent intent)
        {
            BindServiceData bindservicedata = new BindServiceData();
            bindservicedata.token = ibinder;
            bindservicedata.intent = intent;
            ActivityThread._2D_wrap31(ActivityThread.this, 122, bindservicedata);
        }

        public final void scheduleWindowVisibility(IBinder ibinder, boolean flag)
        {
            ActivityThread activitythread = ActivityThread.this;
            byte byte0;
            if(flag)
                byte0 = 105;
            else
                byte0 = 106;
            ActivityThread._2D_wrap31(activitythread, byte0, ibinder);
        }

        public void setCoreSettings(Bundle bundle)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 138, bundle);
        }

        public void setHttpProxy(String s, String s1, String s2, Uri uri)
        {
            ConnectivityManager connectivitymanager = ConnectivityManager.from(getSystemContext());
            if(connectivitymanager.getBoundNetworkForProcess() != null)
                Proxy.setHttpProxySystemProperty(connectivitymanager.getDefaultProxy());
            else
                Proxy.setHttpProxySystemProperty(s, s1, s2, uri);
        }

        public void setNetworkBlockSeq(long l)
        {
            Object obj = ActivityThread._2D_get0(ActivityThread.this);
            obj;
            JVM INSTR monitorenter ;
            ActivityThread._2D_set0(ActivityThread.this, l);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void setProcessState(int i)
        {
            updateProcessState(i, true);
        }

        public void setSchedulingGroup(int i)
        {
            Process.setProcessGroup(Process.myPid(), i);
_L1:
            return;
            Exception exception;
            exception;
            Slog.w("ActivityThread", (new StringBuilder()).append("Failed setting process group to ").append(i).toString(), exception);
              goto _L1
        }

        public void startBinderTracking()
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 150, null);
        }

        public void stopBinderTrackingAndDump(ParcelFileDescriptor parcelfiledescriptor)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 151, parcelfiledescriptor.dup());
            IoUtils.closeQuietly(parcelfiledescriptor);
_L2:
            return;
            Object obj;
            obj;
            IoUtils.closeQuietly(parcelfiledescriptor);
            if(true) goto _L2; else goto _L1
_L1:
            obj;
            IoUtils.closeQuietly(parcelfiledescriptor);
            throw obj;
        }

        public void unstableProviderDied(IBinder ibinder)
        {
            ActivityThread._2D_wrap31(ActivityThread.this, 142, ibinder);
        }

        public void updatePackageCompatibilityInfo(String s, CompatibilityInfo compatibilityinfo)
        {
            UpdateCompatibilityData updatecompatibilitydata = new UpdateCompatibilityData();
            updatecompatibilitydata.pkg = s;
            updatecompatibilitydata.info = compatibilityinfo;
            ActivityThread._2D_wrap31(ActivityThread.this, 139, updatecompatibilitydata);
        }

        public void updateProcessState(int i, boolean flag)
        {
            this;
            JVM INSTR monitorenter ;
            if(mLastProcessState == i)
                break MISSING_BLOCK_LABEL_32;
            mLastProcessState = i;
            int j;
            j = 1;
            if(i <= 6)
                j = 0;
            VMRuntime.getRuntime().updateProcessState(j);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public final void updateTimePrefs(int i)
        {
            Boolean boolean1;
            if(i == 0)
                boolean1 = Boolean.FALSE;
            else
            if(i == 1)
                boolean1 = Boolean.TRUE;
            else
                boolean1 = null;
            DateFormat.set24HourTimePref(boolean1);
        }

        public void updateTimeZone()
        {
            TimeZone.setDefault(null);
        }

        private static final String DB_INFO_FORMAT = "  %8s %8s %14s %14s  %s";
        private int mLastProcessState;
        final ActivityThread this$0;

        private ApplicationThread()
        {
            this$0 = ActivityThread.this;
            super();
            mLastProcessState = -1;
        }

        ApplicationThread(ApplicationThread applicationthread)
        {
            this();
        }
    }

    static final class BindServiceData
    {

        public String toString()
        {
            return (new StringBuilder()).append("BindServiceData{token=").append(token).append(" intent=").append(intent).append("}").toString();
        }

        Intent intent;
        boolean rebind;
        IBinder token;

        BindServiceData()
        {
        }
    }

    static final class ContextCleanupInfo
    {

        ContextImpl context;
        String what;
        String who;

        ContextCleanupInfo()
        {
        }
    }

    static final class CreateBackupAgentData
    {

        public String toString()
        {
            return (new StringBuilder()).append("CreateBackupAgentData{appInfo=").append(appInfo).append(" backupAgent=").append(appInfo.backupAgentName).append(" mode=").append(backupMode).append("}").toString();
        }

        ApplicationInfo appInfo;
        int backupMode;
        CompatibilityInfo compatInfo;

        CreateBackupAgentData()
        {
        }
    }

    static final class CreateServiceData
    {

        public String toString()
        {
            return (new StringBuilder()).append("CreateServiceData{token=").append(token).append(" className=").append(info.name).append(" packageName=").append(info.packageName).append(" intent=").append(intent).append("}").toString();
        }

        CompatibilityInfo compatInfo;
        ServiceInfo info;
        Intent intent;
        IBinder token;

        CreateServiceData()
        {
        }
    }

    private class DropBoxReporter
        implements libcore.io.DropBox.Reporter
    {

        private void ensureInitialized()
        {
            this;
            JVM INSTR monitorenter ;
            if(dropBox == null)
                dropBox = (DropBoxManager)getSystemContext().getSystemService("dropbox");
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void addData(String s, byte abyte0[], int i)
        {
            ensureInitialized();
            dropBox.addData(s, abyte0, i);
        }

        public void addText(String s, String s1)
        {
            ensureInitialized();
            dropBox.addText(s, s1);
        }

        private DropBoxManager dropBox;
        final ActivityThread this$0;

        public DropBoxReporter()
        {
            this$0 = ActivityThread.this;
            super();
        }
    }

    static final class DumpComponentInfo
    {

        String args[];
        ParcelFileDescriptor fd;
        String prefix;
        IBinder token;

        DumpComponentInfo()
        {
        }
    }

    static final class DumpHeapData
    {

        ParcelFileDescriptor fd;
        public boolean mallocInfo;
        public boolean managed;
        String path;
        public boolean runGc;

        DumpHeapData()
        {
        }
    }

    private static class EventLoggingReporter
        implements libcore.io.EventLogger.Reporter
    {

        public transient void report(int i, Object aobj[])
        {
            EventLog.writeEvent(i, aobj);
        }

        private EventLoggingReporter()
        {
        }

        EventLoggingReporter(EventLoggingReporter eventloggingreporter)
        {
            this();
        }
    }

    final class GcIdler
        implements android.os.MessageQueue.IdleHandler
    {

        public final boolean queueIdle()
        {
            doGcIfNeeded();
            return false;
        }

        final ActivityThread this$0;

        GcIdler()
        {
            this$0 = ActivityThread.this;
            super();
        }
    }

    private class H extends Handler
    {

        String codeToString(int i)
        {
            return Integer.toString(i);
        }

        public void handleMessage(Message message)
        {
            long l = SystemClock.uptimeMillis();
            message.what;
            JVM INSTR tableswitch 100 157: default 256
        //                       100 284
        //                       101 385
        //                       102 491
        //                       103 597
        //                       104 649
        //                       105 701
        //                       106 733
        //                       107 765
        //                       108 842
        //                       109 874
        //                       110 937
        //                       111 973
        //                       112 1002
        //                       113 1034
        //                       114 1066
        //                       115 1185
        //                       116 1240
        //                       117 256
        //                       118 1272
        //                       119 1349
        //                       120 1379
        //                       121 1121
        //                       122 1153
        //                       123 1389
        //                       124 1406
        //                       125 1431
        //                       126 350
        //                       127 1500
        //                       128 1543
        //                       129 1575
        //                       130 1607
        //                       131 1616
        //                       132 1648
        //                       133 1658
        //                       134 1694
        //                       135 1709
        //                       136 1722
        //                       137 1756
        //                       138 1814
        //                       139 1846
        //                       140 1863
        //                       141 1739
        //                       142 1892
        //                       143 1910
        //                       144 1927
        //                       145 1971
        //                       146 1988
        //                       147 256
        //                       148 256
        //                       149 2023
        //                       150 2040
        //                       151 2050
        //                       152 2067
        //                       153 2136
        //                       154 2205
        //                       155 2241
        //                       156 2254
        //                       157 1464;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L1 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L1 _L1 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56
_L1:
            Object obj = message.obj;
            if(obj instanceof SomeArgs)
                ((SomeArgs)obj).recycle();
            ActivityThreadInjector.checkHandleMessageTime(l, message);
            return;
_L2:
            Trace.traceBegin(64L, "activityStart");
            ActivityClientRecord activityclientrecord = (ActivityClientRecord)message.obj;
            activityclientrecord.packageInfo = getPackageInfoNoCheck(activityclientrecord.activityInfo.applicationInfo, activityclientrecord.compatInfo);
            ActivityThread._2D_wrap11(ActivityThread.this, activityclientrecord, null, "LAUNCH_ACTIVITY");
            ActivityThreadInjector.clearCachedDrawables();
            Trace.traceEnd(64L);
              goto _L1
_L27:
            Trace.traceBegin(64L, "activityRestart");
            ActivityClientRecord activityclientrecord1 = (ActivityClientRecord)message.obj;
            ActivityThread._2D_wrap18(ActivityThread.this, activityclientrecord1);
            Trace.traceEnd(64L);
              goto _L1
_L3:
            Trace.traceBegin(64L, "activityPause");
            SomeArgs someargs3 = (SomeArgs)message.obj;
            ActivityThread activitythread = ActivityThread.this;
            IBinder ibinder5 = (IBinder)someargs3.arg1;
            boolean flag;
            int i;
            boolean flag9;
            if((someargs3.argi1 & 1) != 0)
                flag = true;
            else
                flag = false;
            i = someargs3.argi2;
            if((someargs3.argi1 & 2) != 0)
                flag9 = true;
            else
                flag9 = false;
            ActivityThread._2D_wrap15(activitythread, ibinder5, false, flag, i, flag9, someargs3.argi3);
            Trace.traceEnd(64L);
              goto _L1
_L4:
            Trace.traceBegin(64L, "activityPause");
            SomeArgs someargs4 = (SomeArgs)message.obj;
            ActivityThread activitythread1 = ActivityThread.this;
            IBinder ibinder6 = (IBinder)someargs4.arg1;
            boolean flag1;
            int j;
            boolean flag10;
            if((someargs4.argi1 & 1) != 0)
                flag1 = true;
            else
                flag1 = false;
            j = someargs4.argi2;
            if((someargs4.argi1 & 2) != 0)
                flag10 = true;
            else
                flag10 = false;
            ActivityThread._2D_wrap15(activitythread1, ibinder6, true, flag1, j, flag10, someargs4.argi3);
            Trace.traceEnd(64L);
              goto _L1
_L5:
            Trace.traceBegin(64L, "activityStop");
            SomeArgs someargs = (SomeArgs)message.obj;
            ActivityThread._2D_wrap24(ActivityThread.this, (IBinder)someargs.arg1, true, someargs.argi2, someargs.argi3);
            Trace.traceEnd(64L);
              goto _L1
_L6:
            Trace.traceBegin(64L, "activityStop");
            SomeArgs someargs1 = (SomeArgs)message.obj;
            ActivityThread._2D_wrap24(ActivityThread.this, (IBinder)someargs1.arg1, false, someargs1.argi2, someargs1.argi3);
            Trace.traceEnd(64L);
              goto _L1
_L7:
            Trace.traceBegin(64L, "activityShowWindow");
            ActivityThread._2D_wrap29(ActivityThread.this, (IBinder)message.obj, true);
            Trace.traceEnd(64L);
              goto _L1
_L8:
            Trace.traceBegin(64L, "activityHideWindow");
            ActivityThread._2D_wrap29(ActivityThread.this, (IBinder)message.obj, false);
            Trace.traceEnd(64L);
              goto _L1
_L9:
            Trace.traceBegin(64L, "activityResume");
            SomeArgs someargs2 = (SomeArgs)message.obj;
            ActivityThread activitythread5 = ActivityThread.this;
            IBinder ibinder7 = (IBinder)someargs2.arg1;
            boolean flag2;
            if(someargs2.argi1 != 0)
                flag2 = true;
            else
                flag2 = false;
            activitythread5.handleResumeActivity(ibinder7, true, flag2, true, someargs2.argi3, "RESUME_ACTIVITY");
            Trace.traceEnd(64L);
              goto _L1
_L10:
            Trace.traceBegin(64L, "activityDeliverResult");
            ActivityThread._2D_wrap19(ActivityThread.this, (ResultData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L11:
            Trace.traceBegin(64L, "activityDestroy");
            ActivityThread activitythread6 = ActivityThread.this;
            IBinder ibinder = (IBinder)message.obj;
            boolean flag3;
            if(message.arg1 != 0)
                flag3 = true;
            else
                flag3 = false;
            ActivityThread._2D_wrap5(activitythread6, ibinder, flag3, message.arg2, false);
            Trace.traceEnd(64L);
              goto _L1
_L12:
            Trace.traceBegin(64L, "bindApplication");
            AppBindData appbinddata = (AppBindData)message.obj;
            ActivityThread._2D_wrap1(ActivityThread.this, appbinddata);
            Trace.traceEnd(64L);
              goto _L1
_L13:
            if(mInitialApplication != null)
                mInitialApplication.onTerminate();
            Looper.myLooper().quit();
              goto _L1
_L14:
            Trace.traceBegin(64L, "activityNewIntent");
            ActivityThread._2D_wrap14(ActivityThread.this, (NewIntentData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L15:
            Trace.traceBegin(64L, "broadcastReceiveComp");
            ActivityThread._2D_wrap17(ActivityThread.this, (ReceiverData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L16:
            Trace.traceBegin(64L, (new StringBuilder()).append("serviceCreate: ").append(String.valueOf(message.obj)).toString());
            ActivityThread._2D_wrap4(ActivityThread.this, (CreateServiceData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L22:
            Trace.traceBegin(64L, "serviceBind");
            ActivityThread._2D_wrap2(ActivityThread.this, (BindServiceData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L23:
            Trace.traceBegin(64L, "serviceUnbind");
            ActivityThread._2D_wrap27(ActivityThread.this, (BindServiceData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L17:
            Trace.traceBegin(64L, (new StringBuilder()).append("serviceStart: ").append(String.valueOf(message.obj)).toString());
            ActivityThread._2D_wrap20(ActivityThread.this, (ServiceArgsData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L18:
            Trace.traceBegin(64L, "serviceStop");
            ActivityThread._2D_wrap26(ActivityThread.this, (IBinder)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L19:
            Trace.traceBegin(64L, "configChanged");
            mCurDefaultDisplayDpi = ((Configuration)message.obj).densityDpi;
            mUpdatingSystemConfig = true;
            handleConfigurationChanged((Configuration)message.obj, null);
            mUpdatingSystemConfig = false;
            Trace.traceEnd(64L);
              goto _L1
            message;
            mUpdatingSystemConfig = false;
            throw message;
_L20:
            ContextCleanupInfo contextcleanupinfo = (ContextCleanupInfo)message.obj;
            contextcleanupinfo.context.performFinalCleanup(contextcleanupinfo.who, contextcleanupinfo.what);
              goto _L1
_L21:
            scheduleGcIdler();
              goto _L1
_L24:
            ActivityThread._2D_wrap9(ActivityThread.this, (DumpComponentInfo)message.obj);
              goto _L1
_L25:
            Trace.traceBegin(64L, "lowMemory");
            handleLowMemory();
            Trace.traceEnd(64L);
              goto _L1
_L26:
            Trace.traceBegin(64L, "activityConfigChanged");
            handleActivityConfigurationChanged((ActivityConfigChangeData)message.obj, -1);
            Trace.traceEnd(64L);
              goto _L1
_L56:
            Trace.traceBegin(64L, "activityMovedToDisplay");
            handleActivityConfigurationChanged((ActivityConfigChangeData)message.obj, message.arg1);
            Trace.traceEnd(64L);
              goto _L1
_L28:
            ActivityThread activitythread2 = ActivityThread.this;
            boolean flag4;
            if(message.arg1 != 0)
                flag4 = true;
            else
                flag4 = false;
            activitythread2.handleProfilerControl(flag4, (ProfilerInfo)message.obj, message.arg2);
              goto _L1
_L29:
            Trace.traceBegin(64L, "backupCreateAgent");
            ActivityThread._2D_wrap3(ActivityThread.this, (CreateBackupAgentData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L30:
            Trace.traceBegin(64L, "backupDestroyAgent");
            ActivityThread._2D_wrap6(ActivityThread.this, (CreateBackupAgentData)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L31:
            Process.killProcess(Process.myPid());
              goto _L1
_L32:
            Trace.traceBegin(64L, "providerRemove");
            completeRemoveProvider((ProviderRefCount)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L33:
            ensureJitEnabled();
              goto _L1
_L34:
            Trace.traceBegin(64L, "broadcastPackage");
            handleDispatchPackageBroadcast(message.arg1, (String[])message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L35:
            throw new RemoteServiceException((String)message.obj);
_L36:
            ActivityThread.handleDumpHeap((DumpHeapData)message.obj);
              goto _L1
_L37:
            ActivityThread._2D_wrap7(ActivityThread.this, (DumpComponentInfo)message.obj);
              goto _L1
_L42:
            ActivityThread._2D_wrap8(ActivityThread.this, (DumpComponentInfo)message.obj);
              goto _L1
_L38:
            Trace.traceBegin(64L, "sleeping");
            ActivityThread activitythread7 = ActivityThread.this;
            IBinder ibinder1 = (IBinder)message.obj;
            boolean flag5;
            if(message.arg1 != 0)
                flag5 = true;
            else
                flag5 = false;
            ActivityThread._2D_wrap22(activitythread7, ibinder1, flag5);
            Trace.traceEnd(64L);
              goto _L1
_L39:
            Trace.traceBegin(64L, "setCoreSettings");
            ActivityThread._2D_wrap21(ActivityThread.this, (Bundle)message.obj);
            Trace.traceEnd(64L);
              goto _L1
_L40:
            ActivityThread._2D_wrap28(ActivityThread.this, (UpdateCompatibilityData)message.obj);
              goto _L1
_L41:
            Trace.traceBegin(64L, "trimMemory");
            handleTrimMemory(message.arg1);
            Trace.traceEnd(64L);
              goto _L1
_L43:
            handleUnstableProviderDied((IBinder)message.obj, false);
              goto _L1
_L44:
            handleRequestAssistContextExtras((RequestAssistContextExtras)message.obj);
              goto _L1
_L45:
            ActivityThread activitythread3 = ActivityThread.this;
            IBinder ibinder3 = (IBinder)message.obj;
            boolean flag6;
            if(message.arg1 == 1)
                flag6 = true;
            else
                flag6 = false;
            activitythread3.handleTranslucentConversionComplete(ibinder3, flag6);
              goto _L1
_L46:
            handleInstallProvider((ProviderInfo)message.obj);
              goto _L1
_L47:
            Pair pair = (Pair)message.obj;
            onNewActivityOptions((IBinder)pair.first, (ActivityOptions)pair.second);
              goto _L1
_L48:
            ActivityThread._2D_wrap10(ActivityThread.this, (IBinder)message.obj);
              goto _L1
_L49:
            ActivityThread._2D_wrap23(ActivityThread.this);
              goto _L1
_L50:
            ActivityThread._2D_wrap25(ActivityThread.this, (ParcelFileDescriptor)message.obj);
              goto _L1
_L51:
            ActivityThread activitythread8 = ActivityThread.this;
            IBinder ibinder2 = (IBinder)((SomeArgs)message.obj).arg1;
            boolean flag7;
            if(((SomeArgs)message.obj).argi1 == 1)
                flag7 = true;
            else
                flag7 = false;
            ActivityThread._2D_wrap13(activitythread8, ibinder2, flag7, (Configuration)((SomeArgs)message.obj).arg2);
              goto _L1
_L52:
            ActivityThread activitythread4 = ActivityThread.this;
            IBinder ibinder4 = (IBinder)((SomeArgs)message.obj).arg1;
            boolean flag8;
            if(((SomeArgs)message.obj).argi1 == 1)
                flag8 = true;
            else
                flag8 = false;
            ActivityThread._2D_wrap16(activitythread4, ibinder4, flag8, (Configuration)((SomeArgs)message.obj).arg2);
              goto _L1
_L53:
            ActivityThread._2D_wrap12(ActivityThread.this, (IBinder)((SomeArgs)message.obj).arg1, (IVoiceInteractor)((SomeArgs)message.obj).arg2);
              goto _L1
_L54:
            ActivityThread.handleAttachAgent((String)message.obj);
              goto _L1
_L55:
            mUpdatingSystemConfig = true;
            handleApplicationInfoChanged((ApplicationInfo)message.obj);
            mUpdatingSystemConfig = false;
              goto _L1
            message;
            mUpdatingSystemConfig = false;
            throw message;
        }

        public static final int ACTIVITY_CONFIGURATION_CHANGED = 125;
        public static final int ACTIVITY_MOVED_TO_DISPLAY = 157;
        public static final int APPLICATION_INFO_CHANGED = 156;
        public static final int ATTACH_AGENT = 155;
        public static final int BIND_APPLICATION = 110;
        public static final int BIND_SERVICE = 121;
        public static final int CLEAN_UP_CONTEXT = 119;
        public static final int CONFIGURATION_CHANGED = 118;
        public static final int CREATE_BACKUP_AGENT = 128;
        public static final int CREATE_SERVICE = 114;
        public static final int DESTROY_ACTIVITY = 109;
        public static final int DESTROY_BACKUP_AGENT = 129;
        public static final int DISPATCH_PACKAGE_BROADCAST = 133;
        public static final int DUMP_ACTIVITY = 136;
        public static final int DUMP_HEAP = 135;
        public static final int DUMP_PROVIDER = 141;
        public static final int DUMP_SERVICE = 123;
        public static final int ENABLE_JIT = 132;
        public static final int ENTER_ANIMATION_COMPLETE = 149;
        public static final int EXIT_APPLICATION = 111;
        public static final int GC_WHEN_IDLE = 120;
        public static final int HIDE_WINDOW = 106;
        public static final int INSTALL_PROVIDER = 145;
        public static final int LAUNCH_ACTIVITY = 100;
        public static final int LOCAL_VOICE_INTERACTION_STARTED = 154;
        public static final int LOW_MEMORY = 124;
        public static final int MULTI_WINDOW_MODE_CHANGED = 152;
        public static final int NEW_INTENT = 112;
        public static final int ON_NEW_ACTIVITY_OPTIONS = 146;
        public static final int PAUSE_ACTIVITY = 101;
        public static final int PAUSE_ACTIVITY_FINISHING = 102;
        public static final int PICTURE_IN_PICTURE_MODE_CHANGED = 153;
        public static final int PROFILER_CONTROL = 127;
        public static final int RECEIVER = 113;
        public static final int RELAUNCH_ACTIVITY = 126;
        public static final int REMOVE_PROVIDER = 131;
        public static final int REQUEST_ASSIST_CONTEXT_EXTRAS = 143;
        public static final int RESUME_ACTIVITY = 107;
        public static final int SCHEDULE_CRASH = 134;
        public static final int SEND_RESULT = 108;
        public static final int SERVICE_ARGS = 115;
        public static final int SET_CORE_SETTINGS = 138;
        public static final int SHOW_WINDOW = 105;
        public static final int SLEEPING = 137;
        public static final int START_BINDER_TRACKING = 150;
        public static final int STOP_ACTIVITY_HIDE = 104;
        public static final int STOP_ACTIVITY_SHOW = 103;
        public static final int STOP_BINDER_TRACKING_AND_DUMP = 151;
        public static final int STOP_SERVICE = 116;
        public static final int SUICIDE = 130;
        public static final int TRANSLUCENT_CONVERSION_COMPLETE = 144;
        public static final int TRIM_MEMORY = 140;
        public static final int UNBIND_SERVICE = 122;
        public static final int UNSTABLE_PROVIDER_DIED = 142;
        public static final int UPDATE_PACKAGE_COMPATIBILITY_INFO = 139;
        final ActivityThread this$0;

        private H()
        {
            this$0 = ActivityThread.this;
            super();
        }

        H(H h)
        {
            this();
        }
    }

    private class Idler
        implements android.os.MessageQueue.IdleHandler
    {

        public final boolean queueIdle()
        {
            ActivityClientRecord activityclientrecord = mNewActivities;
            boolean flag = false;
            boolean flag1 = flag;
            if(mBoundApplication != null)
            {
                flag1 = flag;
                if(mProfiler.profileFd != null)
                {
                    flag1 = flag;
                    if(mProfiler.autoStopProfiler)
                        flag1 = true;
                }
            }
            if(activityclientrecord != null)
            {
                mNewActivities = null;
                IActivityManager iactivitymanager = ActivityManager.getService();
                do
                {
                    ActivityClientRecord activityclientrecord1;
                    if(activityclientrecord.activity != null && activityclientrecord.activity.mFinished ^ true)
                        try
                        {
                            iactivitymanager.activityIdle(activityclientrecord.token, activityclientrecord.createdConfig, flag1);
                            activityclientrecord.createdConfig = null;
                        }
                        catch(RemoteException remoteexception)
                        {
                            throw remoteexception.rethrowFromSystemServer();
                        }
                    activityclientrecord1 = activityclientrecord.nextIdle;
                    activityclientrecord.nextIdle = null;
                    activityclientrecord = activityclientrecord1;
                } while(activityclientrecord1 != null);
            }
            if(flag1)
                mProfiler.stopProfiling();
            ensureJitEnabled();
            return false;
        }

        final ActivityThread this$0;

        private Idler()
        {
            this$0 = ActivityThread.this;
            super();
        }

        Idler(Idler idler)
        {
            this();
        }
    }

    static final class NewIntentData
    {

        public String toString()
        {
            return (new StringBuilder()).append("NewIntentData{intents=").append(intents).append(" token=").append(token).append(" andPause=").append(andPause).append("}").toString();
        }

        boolean andPause;
        List intents;
        IBinder token;

        NewIntentData()
        {
        }
    }

    static final class Profiler
    {

        public void setProfiler(ProfilerInfo profilerinfo)
        {
            ParcelFileDescriptor parcelfiledescriptor;
            parcelfiledescriptor = profilerinfo.profileFd;
            if(!profiling)
                break MISSING_BLOCK_LABEL_25;
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_20;
            parcelfiledescriptor.close();
_L2:
            return;
            profilerinfo;
            if(true) goto _L2; else goto _L1
_L1:
            if(profileFd != null)
                try
                {
                    profileFd.close();
                }
                catch(IOException ioexception) { }
            profileFile = profilerinfo.profileFile;
            profileFd = parcelfiledescriptor;
            samplingInterval = profilerinfo.samplingInterval;
            autoStopProfiler = profilerinfo.autoStopProfiler;
            streamingOutput = profilerinfo.streamingOutput;
            return;
        }

        public void startProfiling()
        {
            boolean flag;
            flag = true;
            if(profileFd == null || profiling)
                return;
            int i;
            String s;
            FileDescriptor filedescriptor;
            i = SystemProperties.getInt("debug.traceview-buffer-size-mb", 8);
            s = profileFile;
            filedescriptor = profileFd.getFileDescriptor();
            if(samplingInterval == 0)
                flag = false;
            VMDebug.startMethodTracing(s, filedescriptor, i * 1024 * 1024, 0, flag, samplingInterval, streamingOutput);
            profiling = true;
_L1:
            return;
            RuntimeException runtimeexception;
            runtimeexception;
            Slog.w("ActivityThread", (new StringBuilder()).append("Profiling failed on path ").append(profileFile).toString());
            try
            {
                profileFd.close();
                profileFd = null;
            }
            catch(IOException ioexception)
            {
                Slog.w("ActivityThread", "Failure closing profile fd", ioexception);
            }
              goto _L1
        }

        public void stopProfiling()
        {
            if(profiling)
            {
                profiling = false;
                Debug.stopMethodTracing();
                if(profileFd != null)
                    try
                    {
                        profileFd.close();
                    }
                    catch(IOException ioexception) { }
                profileFd = null;
                profileFile = null;
            }
        }

        boolean autoStopProfiler;
        boolean handlingProfiling;
        ParcelFileDescriptor profileFd;
        String profileFile;
        boolean profiling;
        int samplingInterval;
        boolean streamingOutput;

        Profiler()
        {
        }
    }

    static final class ProviderAcquiringCount
    {

        public int acquiringCount;

        ProviderAcquiringCount(int i)
        {
            acquiringCount = i;
        }
    }

    final class ProviderClientRecord
    {

        final ContentProviderHolder mHolder;
        final ContentProvider mLocalProvider;
        final String mNames[];
        final IContentProvider mProvider;
        final ActivityThread this$0;

        ProviderClientRecord(String as[], IContentProvider icontentprovider, ContentProvider contentprovider, ContentProviderHolder contentproviderholder)
        {
            this$0 = ActivityThread.this;
            super();
            mNames = as;
            mProvider = icontentprovider;
            mLocalProvider = contentprovider;
            mHolder = contentproviderholder;
        }
    }

    private static final class ProviderKey
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(obj instanceof ProviderKey)
            {
                obj = (ProviderKey)obj;
                boolean flag1 = flag;
                if(Objects.equals(authority, ((ProviderKey) (obj)).authority))
                {
                    flag1 = flag;
                    if(userId == ((ProviderKey) (obj)).userId)
                        flag1 = true;
                }
                return flag1;
            } else
            {
                return false;
            }
        }

        public int hashCode()
        {
            int i;
            if(authority != null)
                i = authority.hashCode();
            else
                i = 0;
            return i ^ userId;
        }

        final String authority;
        final int userId;

        public ProviderKey(String s, int i)
        {
            authority = s;
            userId = i;
        }
    }

    private static final class ProviderRefCount
    {

        public final ProviderClientRecord client;
        public final ContentProviderHolder holder;
        public boolean removePending;
        public int stableCount;
        public int unstableCount;

        ProviderRefCount(ContentProviderHolder contentproviderholder, ProviderClientRecord providerclientrecord, int i, int j)
        {
            holder = contentproviderholder;
            client = providerclientrecord;
            stableCount = i;
            unstableCount = j;
        }
    }

    static final class ReceiverData extends android.content.BroadcastReceiver.PendingResult
    {

        public String toString()
        {
            return (new StringBuilder()).append("ReceiverData{intent=").append(intent).append(" packageName=").append(info.packageName).append(" resultCode=").append(getResultCode()).append(" resultData=").append(getResultData()).append(" resultExtras=").append(getResultExtras(false)).append("}").toString();
        }

        CompatibilityInfo compatInfo;
        ActivityInfo info;
        Intent intent;

        public ReceiverData(Intent intent1, int i, String s, Bundle bundle, boolean flag, boolean flag1, IBinder ibinder, 
                int j)
        {
            super(i, s, bundle, 0, flag, flag1, ibinder, j, intent1.getFlags());
            intent = intent1;
        }
    }

    static final class RequestAssistContextExtras
    {

        IBinder activityToken;
        int flags;
        IBinder requestToken;
        int requestType;
        int sessionId;

        RequestAssistContextExtras()
        {
        }
    }

    static final class ResultData
    {

        public String toString()
        {
            return (new StringBuilder()).append("ResultData{token=").append(token).append(" results").append(results).append("}").toString();
        }

        List results;
        IBinder token;

        ResultData()
        {
        }
    }

    static final class ServiceArgsData
    {

        public String toString()
        {
            return (new StringBuilder()).append("ServiceArgsData{token=").append(token).append(" startId=").append(startId).append(" args=").append(args).append("}").toString();
        }

        Intent args;
        int flags;
        int startId;
        boolean taskRemoved;
        IBinder token;

        ServiceArgsData()
        {
        }
    }

    private static class StopInfo
        implements Runnable
    {

        public void run()
        {
            try
            {
                ActivityManager.getService().activityStopped(activity.token, state, persistentState, description);
                return;
            }
            catch(RemoteException remoteexception)
            {
                IndentingPrintWriter indentingprintwriter = new IndentingPrintWriter(new LogWriter(5, "ActivityThread"), "  ");
                indentingprintwriter.println("Bundle stats:");
                Bundle.dumpStats(indentingprintwriter, state);
                indentingprintwriter.println("PersistableBundle stats:");
                Bundle.dumpStats(indentingprintwriter, persistentState);
                if((remoteexception instanceof TransactionTooLargeException) && activity.packageInfo.getTargetSdkVersion() < 24)
                {
                    Log.e("ActivityThread", "App sent too much data in instance state, so it was ignored", remoteexception);
                    return;
                } else
                {
                    throw remoteexception.rethrowFromSystemServer();
                }
            }
        }

        ActivityClientRecord activity;
        CharSequence description;
        PersistableBundle persistentState;
        Bundle state;

        private StopInfo()
        {
        }

        StopInfo(StopInfo stopinfo)
        {
            this();
        }
    }

    static final class UpdateCompatibilityData
    {

        CompatibilityInfo info;
        String pkg;

        UpdateCompatibilityData()
        {
        }
    }


    static Object _2D_get0(ActivityThread activitythread)
    {
        return activitythread.mNetworkPolicyLock;
    }

    static ResourcesManager _2D_get1(ActivityThread activitythread)
    {
        return activitythread.mResourcesManager;
    }

    static boolean _2D_get2()
    {
        return sWaitingToUse;
    }

    static long _2D_set0(ActivityThread activitythread, long l)
    {
        activitythread.mNetworkBlockSeq = l;
        return l;
    }

    static boolean _2D_set1(boolean flag)
    {
        sWaitingToUse = flag;
        return flag;
    }

    static int _2D_wrap0(ActivityThread activitythread)
    {
        return activitythread.getLifecycleSeq();
    }

    static void _2D_wrap1(ActivityThread activitythread, AppBindData appbinddata)
    {
        activitythread.handleBindApplication(appbinddata);
    }

    static void _2D_wrap10(ActivityThread activitythread, IBinder ibinder)
    {
        activitythread.handleEnterAnimationComplete(ibinder);
    }

    static void _2D_wrap11(ActivityThread activitythread, ActivityClientRecord activityclientrecord, Intent intent, String s)
    {
        activitythread.handleLaunchActivity(activityclientrecord, intent, s);
    }

    static void _2D_wrap12(ActivityThread activitythread, IBinder ibinder, IVoiceInteractor ivoiceinteractor)
    {
        activitythread.handleLocalVoiceInteractionStarted(ibinder, ivoiceinteractor);
    }

    static void _2D_wrap13(ActivityThread activitythread, IBinder ibinder, boolean flag, Configuration configuration)
    {
        activitythread.handleMultiWindowModeChanged(ibinder, flag, configuration);
    }

    static void _2D_wrap14(ActivityThread activitythread, NewIntentData newintentdata)
    {
        activitythread.handleNewIntent(newintentdata);
    }

    static void _2D_wrap15(ActivityThread activitythread, IBinder ibinder, boolean flag, boolean flag1, int i, boolean flag2, int j)
    {
        activitythread.handlePauseActivity(ibinder, flag, flag1, i, flag2, j);
    }

    static void _2D_wrap16(ActivityThread activitythread, IBinder ibinder, boolean flag, Configuration configuration)
    {
        activitythread.handlePictureInPictureModeChanged(ibinder, flag, configuration);
    }

    static void _2D_wrap17(ActivityThread activitythread, ReceiverData receiverdata)
    {
        activitythread.handleReceiver(receiverdata);
    }

    static void _2D_wrap18(ActivityThread activitythread, ActivityClientRecord activityclientrecord)
    {
        activitythread.handleRelaunchActivity(activityclientrecord);
    }

    static void _2D_wrap19(ActivityThread activitythread, ResultData resultdata)
    {
        activitythread.handleSendResult(resultdata);
    }

    static void _2D_wrap2(ActivityThread activitythread, BindServiceData bindservicedata)
    {
        activitythread.handleBindService(bindservicedata);
    }

    static void _2D_wrap20(ActivityThread activitythread, ServiceArgsData serviceargsdata)
    {
        activitythread.handleServiceArgs(serviceargsdata);
    }

    static void _2D_wrap21(ActivityThread activitythread, Bundle bundle)
    {
        activitythread.handleSetCoreSettings(bundle);
    }

    static void _2D_wrap22(ActivityThread activitythread, IBinder ibinder, boolean flag)
    {
        activitythread.handleSleeping(ibinder, flag);
    }

    static void _2D_wrap23(ActivityThread activitythread)
    {
        activitythread.handleStartBinderTracking();
    }

    static void _2D_wrap24(ActivityThread activitythread, IBinder ibinder, boolean flag, int i, int j)
    {
        activitythread.handleStopActivity(ibinder, flag, i, j);
    }

    static void _2D_wrap25(ActivityThread activitythread, ParcelFileDescriptor parcelfiledescriptor)
    {
        activitythread.handleStopBinderTrackingAndDump(parcelfiledescriptor);
    }

    static void _2D_wrap26(ActivityThread activitythread, IBinder ibinder)
    {
        activitythread.handleStopService(ibinder);
    }

    static void _2D_wrap27(ActivityThread activitythread, BindServiceData bindservicedata)
    {
        activitythread.handleUnbindService(bindservicedata);
    }

    static void _2D_wrap28(ActivityThread activitythread, UpdateCompatibilityData updatecompatibilitydata)
    {
        activitythread.handleUpdatePackageCompatibilityInfo(updatecompatibilitydata);
    }

    static void _2D_wrap29(ActivityThread activitythread, IBinder ibinder, boolean flag)
    {
        activitythread.handleWindowVisibility(ibinder, flag);
    }

    static void _2D_wrap3(ActivityThread activitythread, CreateBackupAgentData createbackupagentdata)
    {
        activitythread.handleCreateBackupAgent(createbackupagentdata);
    }

    static void _2D_wrap30(ActivityThread activitythread, FileDescriptor filedescriptor)
    {
        activitythread.nDumpGraphicsInfo(filedescriptor);
    }

    static void _2D_wrap31(ActivityThread activitythread, int i, Object obj)
    {
        activitythread.sendMessage(i, obj);
    }

    static void _2D_wrap32(ActivityThread activitythread, int i, Object obj, int j)
    {
        activitythread.sendMessage(i, obj, j);
    }

    static void _2D_wrap33(ActivityThread activitythread, int i, Object obj, int j, int k, boolean flag)
    {
        activitythread.sendMessage(i, obj, j, k, flag);
    }

    static void _2D_wrap34(ActivityThread activitythread, int i, Object obj, int j, int k)
    {
        activitythread.sendMessage(i, obj, j, k);
    }

    static void _2D_wrap35(ActivityThread activitythread, int i, Object obj, int j, int k, int l)
    {
        activitythread.sendMessage(i, obj, j, k, l);
    }

    static void _2D_wrap4(ActivityThread activitythread, CreateServiceData createservicedata)
    {
        activitythread.handleCreateService(createservicedata);
    }

    static void _2D_wrap5(ActivityThread activitythread, IBinder ibinder, boolean flag, int i, boolean flag1)
    {
        activitythread.handleDestroyActivity(ibinder, flag, i, flag1);
    }

    static void _2D_wrap6(ActivityThread activitythread, CreateBackupAgentData createbackupagentdata)
    {
        activitythread.handleDestroyBackupAgent(createbackupagentdata);
    }

    static void _2D_wrap7(ActivityThread activitythread, DumpComponentInfo dumpcomponentinfo)
    {
        activitythread.handleDumpActivity(dumpcomponentinfo);
    }

    static void _2D_wrap8(ActivityThread activitythread, DumpComponentInfo dumpcomponentinfo)
    {
        activitythread.handleDumpProvider(dumpcomponentinfo);
    }

    static void _2D_wrap9(ActivityThread activitythread, DumpComponentInfo dumpcomponentinfo)
    {
        activitythread.handleDumpService(dumpcomponentinfo);
    }

    ActivityThread()
    {
        mNetworkBlockSeq = -1L;
        mNewActivities = null;
        mNumVisibleActivities = 0;
        mLastAssistStructures = new ArrayList();
        mInstrumentationPackageName = null;
        mInstrumentationAppDir = null;
        mInstrumentationSplitAppDirs = null;
        mInstrumentationLibDir = null;
        mInstrumentedAppDir = null;
        mInstrumentedSplitAppDirs = null;
        mInstrumentedLibDir = null;
        mSystemThread = false;
        mJitEnabled = false;
        mSomeActivitiesChanged = false;
        mUpdatingSystemConfig = false;
        mPendingConfiguration = null;
        mLifecycleSeq = 0;
        mGcIdlerScheduled = false;
        mCoreSettings = null;
        mMainThreadConfig = new Configuration();
        mThumbnailWidth = -1;
        mThumbnailHeight = -1;
        mAvailThumbnailBitmap = null;
        mThumbnailCanvas = null;
    }

    private void attach(boolean flag)
    {
        sCurrentActivityThread = this;
        mSystemThread = flag;
        if(!flag)
        {
            ViewRootImpl.addFirstDrawHandler(new Runnable() {

                public void run()
                {
                    ensureJitEnabled();
                }

                final ActivityThread this$0;

            
            {
                this$0 = ActivityThread.this;
                super();
            }
            }
);
            DdmHandleAppName.setAppName("<pre-initialized>", UserHandle.myUserId());
            RuntimeInit.setApplicationObject(mAppThread.asBinder());
            final IActivityManager mgr = ActivityManager.getService();
            try
            {
                mgr.attachApplication(mAppThread);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            BinderInternal.addGcWatcher(new Runnable() {

                public void run()
                {
                    if(!mSomeActivitiesChanged)
                        return;
                    Runtime runtime = Runtime.getRuntime();
                    long l = runtime.maxMemory();
                    if(runtime.totalMemory() - runtime.freeMemory() <= (3L * l) / 4L)
                        break MISSING_BLOCK_LABEL_66;
                    mSomeActivitiesChanged = false;
                    mgr.releaseSomeActivities(mAppThread);
                    return;
                    RemoteException remoteexception1;
                    remoteexception1;
                    throw remoteexception1.rethrowFromSystemServer();
                }

                final ActivityThread this$0;
                final IActivityManager val$mgr;

            
            {
                this$0 = ActivityThread.this;
                mgr = iactivitymanager;
                super();
            }
            }
);
        } else
        {
            DdmHandleAppName.setAppName("system_process", UserHandle.myUserId());
            try
            {
                Instrumentation instrumentation = JVM INSTR new #690 <Class Instrumentation>;
                instrumentation.Instrumentation();
                mInstrumentation = instrumentation;
                mInitialApplication = ContextImpl.createAppContext(this, getSystemContext().mPackageInfo).mPackageInfo.makeApplication(true, null);
                mInitialApplication.onCreate();
            }
            catch(Exception exception)
            {
                throw new RuntimeException((new StringBuilder()).append("Unable to instantiate Application():").append(exception.toString()).toString(), exception);
            }
        }
        DropBox.setReporter(new DropBoxReporter());
        ViewRootImpl.addConfigCallback(new _.Lambda._cls9I5WEMsoBc7l4QrNqZ4wx59yuHU._cls1(this));
    }

    private void callCallActivityOnSaveInstanceState(ActivityClientRecord activityclientrecord)
    {
        activityclientrecord.state = new Bundle();
        activityclientrecord.state.setAllowFds(false);
        if(activityclientrecord.isPersistable())
        {
            activityclientrecord.persistentState = new PersistableBundle();
            mInstrumentation.callActivityOnSaveInstanceState(activityclientrecord.activity, activityclientrecord.state, activityclientrecord.persistentState);
        } else
        {
            mInstrumentation.callActivityOnSaveInstanceState(activityclientrecord.activity, activityclientrecord.state);
        }
    }

    private void checkAndBlockForNetworkAccess()
    {
        Object obj = mNetworkPolicyLock;
        obj;
        JVM INSTR monitorenter ;
        long l = mNetworkBlockSeq;
        if(l == -1L)
            break MISSING_BLOCK_LABEL_39;
        Exception exception;
        try
        {
            ActivityManager.getService().waitForNetworkStateUpdate(mNetworkBlockSeq);
            mNetworkBlockSeq = -1L;
        }
        catch(RemoteException remoteexception) { }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    private static boolean checkAndUpdateLifecycleSeq(int i, ActivityClientRecord activityclientrecord, String s)
    {
        if(activityclientrecord == null)
            return true;
        if(i < activityclientrecord.lastProcessedSeq)
        {
            return false;
        } else
        {
            activityclientrecord.lastProcessedSeq = i;
            return true;
        }
    }

    static final void cleanUpPendingRemoveWindows(ActivityClientRecord activityclientrecord, boolean flag)
    {
        if(activityclientrecord.mPreserveWindow && flag ^ true)
            return;
        if(activityclientrecord.mPendingRemoveWindow != null)
        {
            activityclientrecord.mPendingRemoveWindowManager.removeViewImmediate(activityclientrecord.mPendingRemoveWindow.getDecorView());
            IBinder ibinder = activityclientrecord.mPendingRemoveWindow.getDecorView().getWindowToken();
            if(ibinder != null)
                WindowManagerGlobal.getInstance().closeAll(ibinder, activityclientrecord.activity.getClass().getName(), "Activity");
        }
        activityclientrecord.mPendingRemoveWindow = null;
        activityclientrecord.mPendingRemoveWindowManager = null;
    }

    private ContextImpl createBaseContextForActivity(ActivityClientRecord activityclientrecord)
    {
        int i;
        ContextImpl contextimpl;
        DisplayManagerGlobal displaymanagerglobal;
        String s;
        ContextImpl contextimpl1;
        int j;
        int k;
        try
        {
            i = ActivityManager.getService().getActivityDisplayId(activityclientrecord.token);
        }
        // Misplaced declaration of an exception variable
        catch(ActivityClientRecord activityclientrecord)
        {
            throw activityclientrecord.rethrowFromSystemServer();
        }
        contextimpl = ContextImpl.createActivityContext(this, activityclientrecord.packageInfo, activityclientrecord.activityInfo, activityclientrecord.token, i, activityclientrecord.overrideConfig);
        displaymanagerglobal = DisplayManagerGlobal.getInstance();
        s = SystemProperties.get("debug.second-display.pkg");
        contextimpl1 = contextimpl;
        if(s == null) goto _L2; else goto _L1
_L1:
        contextimpl1 = contextimpl;
        if(!(s.isEmpty() ^ true)) goto _L2; else goto _L3
_L3:
        contextimpl1 = contextimpl;
        if(!activityclientrecord.packageInfo.mPackageName.contains(s)) goto _L2; else goto _L4
_L4:
        activityclientrecord = displaymanagerglobal.getDisplayIds();
        j = activityclientrecord.length;
        i = 0;
_L9:
        contextimpl1 = contextimpl;
        if(i >= j) goto _L2; else goto _L5
_L5:
        k = activityclientrecord[i];
        if(k == 0) goto _L7; else goto _L6
_L6:
        contextimpl1 = (ContextImpl)contextimpl.createDisplayContext(displaymanagerglobal.getCompatibleDisplay(k, contextimpl.getResources()));
_L2:
        return contextimpl1;
_L7:
        i++;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static Configuration createNewConfigAndUpdateIfNotNull(Configuration configuration, Configuration configuration1)
    {
        if(configuration1 == null)
        {
            return configuration;
        } else
        {
            configuration = new Configuration(configuration);
            configuration.updateFrom(configuration1);
            return configuration;
        }
    }

    private Bitmap createThumbnailBitmap(ActivityClientRecord activityclientrecord)
    {
        Object obj;
        Object obj1;
        obj = mAvailThumbnailBitmap;
        obj1 = obj;
        if(obj != null) goto _L2; else goto _L1
_L1:
        int i = mThumbnailWidth;
        if(i >= 0)
            break MISSING_BLOCK_LABEL_170;
        int j;
        obj1 = activityclientrecord.activity.getResources();
        i = ((Resources) (obj1)).getDimensionPixelSize(0x1050002);
        mThumbnailWidth = i;
        j = ((Resources) (obj1)).getDimensionPixelSize(0x1050001);
        mThumbnailHeight = j;
_L5:
        obj1 = obj;
        if(i <= 0) goto _L2; else goto _L3
_L3:
        obj1 = obj;
        if(j <= 0) goto _L2; else goto _L4
_L4:
        obj1 = Bitmap.createBitmap(activityclientrecord.activity.getResources().getDisplayMetrics(), i, j, THUMBNAIL_FORMAT);
        ((Bitmap) (obj1)).eraseColor(0);
_L2:
        obj = obj1;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_168;
        obj = mThumbnailCanvas;
        Object obj2;
        obj2 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_134;
        obj2 = JVM INSTR new #931 <Class Canvas>;
        ((Canvas) (obj2)).Canvas();
        mThumbnailCanvas = ((Canvas) (obj2));
        ((Canvas) (obj2)).setBitmap(((Bitmap) (obj1)));
        obj = obj1;
        if(activityclientrecord.activity.onCreateThumbnail(((Bitmap) (obj1)), ((Canvas) (obj2))))
            break MISSING_BLOCK_LABEL_162;
        mAvailThumbnailBitmap = ((Bitmap) (obj1));
        obj = null;
        try
        {
            ((Canvas) (obj2)).setBitmap(null);
        }
        catch(Exception exception)
        {
            if(!mInstrumentation.onException(activityclientrecord.activity, exception))
                throw new RuntimeException((new StringBuilder()).append("Unable to create thumbnail of ").append(activityclientrecord.intent.getComponent().toShortString()).append(": ").append(exception.toString()).toString(), exception);
            obj = null;
        }
        return ((Bitmap) (obj));
        j = mThumbnailHeight;
          goto _L5
    }

    public static ActivityThread currentActivityThread()
    {
        return sCurrentActivityThread;
    }

    public static Application currentApplication()
    {
        Application application = null;
        ActivityThread activitythread = currentActivityThread();
        if(activitythread != null)
            application = activitythread.mInitialApplication;
        return application;
    }

    public static String currentOpPackageName()
    {
        Object obj = null;
        ActivityThread activitythread = currentActivityThread();
        String s = obj;
        if(activitythread != null)
        {
            s = obj;
            if(activitythread.getApplication() != null)
                s = activitythread.getApplication().getOpPackageName();
        }
        return s;
    }

    public static String currentPackageName()
    {
        Object obj = null;
        ActivityThread activitythread = currentActivityThread();
        String s = obj;
        if(activitythread != null)
        {
            s = obj;
            if(activitythread.mBoundApplication != null)
                s = activitythread.mBoundApplication.appInfo.packageName;
        }
        return s;
    }

    public static String currentProcessName()
    {
        Object obj = null;
        ActivityThread activitythread = currentActivityThread();
        String s = obj;
        if(activitythread != null)
        {
            s = obj;
            if(activitythread.mBoundApplication != null)
                s = activitythread.mBoundApplication.processName;
        }
        return s;
    }

    private void deliverNewIntents(ActivityClientRecord activityclientrecord, List list)
    {
        int i = list.size();
        for(int j = 0; j < i; j++)
        {
            ReferrerIntent referrerintent = (ReferrerIntent)list.get(j);
            referrerintent.setExtrasClassLoader(activityclientrecord.activity.getClassLoader());
            referrerintent.prepareToEnterProcess();
            activityclientrecord.activity.mFragments.noteStateNotSaved();
            mInstrumentation.callActivityOnNewIntent(activityclientrecord.activity, referrerintent);
        }

    }

    private void deliverResults(ActivityClientRecord activityclientrecord, List list)
    {
        int i = list.size();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            ResultInfo resultinfo = (ResultInfo)list.get(j);
            try
            {
                if(resultinfo.mData != null)
                {
                    resultinfo.mData.setExtrasClassLoader(activityclientrecord.activity.getClassLoader());
                    resultinfo.mData.prepareToEnterProcess();
                }
                activityclientrecord.activity.dispatchActivityResult(resultinfo.mResultWho, resultinfo.mRequestCode, resultinfo.mResultCode, resultinfo.mData);
            }
            catch(Exception exception)
            {
                if(!mInstrumentation.onException(activityclientrecord.activity, exception))
                    throw new RuntimeException((new StringBuilder()).append("Failure delivering result ").append(resultinfo).append(" to activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(": ").append(exception.toString()).toString(), exception);
            }
            j++;
        } while(true);
    }

    public static void dumpMemInfoTable(PrintWriter printwriter, android.os.Debug.MemoryInfo memoryinfo, boolean flag, boolean flag1, boolean flag2, boolean flag3, int i, String s, 
            long l, long l1, long l2, long l3, long l4, long l5)
    {
        if(flag)
        {
            printwriter.print(4);
            printwriter.print(',');
            printwriter.print(i);
            printwriter.print(',');
            printwriter.print(s);
            printwriter.print(',');
            printwriter.print(l);
            printwriter.print(',');
            printwriter.print(l3);
            printwriter.print(',');
            printwriter.print("N/A,");
            printwriter.print(l + l3);
            printwriter.print(',');
            printwriter.print(l1);
            printwriter.print(',');
            printwriter.print(l4);
            printwriter.print(',');
            printwriter.print("N/A,");
            printwriter.print(l1 + l4);
            printwriter.print(',');
            printwriter.print(l2);
            printwriter.print(',');
            printwriter.print(l5);
            printwriter.print(',');
            printwriter.print("N/A,");
            printwriter.print(l2 + l5);
            printwriter.print(',');
            printwriter.print(memoryinfo.nativePss);
            printwriter.print(',');
            printwriter.print(memoryinfo.dalvikPss);
            printwriter.print(',');
            printwriter.print(memoryinfo.otherPss);
            printwriter.print(',');
            printwriter.print(memoryinfo.getTotalPss());
            printwriter.print(',');
            printwriter.print(memoryinfo.nativeSwappablePss);
            printwriter.print(',');
            printwriter.print(memoryinfo.dalvikSwappablePss);
            printwriter.print(',');
            printwriter.print(memoryinfo.otherSwappablePss);
            printwriter.print(',');
            printwriter.print(memoryinfo.getTotalSwappablePss());
            printwriter.print(',');
            printwriter.print(memoryinfo.nativeSharedDirty);
            printwriter.print(',');
            printwriter.print(memoryinfo.dalvikSharedDirty);
            printwriter.print(',');
            printwriter.print(memoryinfo.otherSharedDirty);
            printwriter.print(',');
            printwriter.print(memoryinfo.getTotalSharedDirty());
            printwriter.print(',');
            printwriter.print(memoryinfo.nativeSharedClean);
            printwriter.print(',');
            printwriter.print(memoryinfo.dalvikSharedClean);
            printwriter.print(',');
            printwriter.print(memoryinfo.otherSharedClean);
            printwriter.print(',');
            printwriter.print(memoryinfo.getTotalSharedClean());
            printwriter.print(',');
            printwriter.print(memoryinfo.nativePrivateDirty);
            printwriter.print(',');
            printwriter.print(memoryinfo.dalvikPrivateDirty);
            printwriter.print(',');
            printwriter.print(memoryinfo.otherPrivateDirty);
            printwriter.print(',');
            printwriter.print(memoryinfo.getTotalPrivateDirty());
            printwriter.print(',');
            printwriter.print(memoryinfo.nativePrivateClean);
            printwriter.print(',');
            printwriter.print(memoryinfo.dalvikPrivateClean);
            printwriter.print(',');
            printwriter.print(memoryinfo.otherPrivateClean);
            printwriter.print(',');
            printwriter.print(memoryinfo.getTotalPrivateClean());
            printwriter.print(',');
            printwriter.print(memoryinfo.nativeSwappedOut);
            printwriter.print(',');
            printwriter.print(memoryinfo.dalvikSwappedOut);
            printwriter.print(',');
            printwriter.print(memoryinfo.otherSwappedOut);
            printwriter.print(',');
            printwriter.print(memoryinfo.getTotalSwappedOut());
            printwriter.print(',');
            if(memoryinfo.hasSwappedOutPss)
            {
                printwriter.print(memoryinfo.nativeSwappedOutPss);
                printwriter.print(',');
                printwriter.print(memoryinfo.dalvikSwappedOutPss);
                printwriter.print(',');
                printwriter.print(memoryinfo.otherSwappedOutPss);
                printwriter.print(',');
                printwriter.print(memoryinfo.getTotalSwappedOutPss());
                printwriter.print(',');
            } else
            {
                printwriter.print("N/A,");
                printwriter.print("N/A,");
                printwriter.print("N/A,");
                printwriter.print("N/A,");
            }
            i = 0;
            while(i < 17) 
            {
                printwriter.print(android.os.Debug.MemoryInfo.getOtherLabel(i));
                printwriter.print(',');
                printwriter.print(memoryinfo.getOtherPss(i));
                printwriter.print(',');
                printwriter.print(memoryinfo.getOtherSwappablePss(i));
                printwriter.print(',');
                printwriter.print(memoryinfo.getOtherSharedDirty(i));
                printwriter.print(',');
                printwriter.print(memoryinfo.getOtherSharedClean(i));
                printwriter.print(',');
                printwriter.print(memoryinfo.getOtherPrivateDirty(i));
                printwriter.print(',');
                printwriter.print(memoryinfo.getOtherPrivateClean(i));
                printwriter.print(',');
                printwriter.print(memoryinfo.getOtherSwappedOut(i));
                printwriter.print(',');
                if(memoryinfo.hasSwappedOutPss)
                {
                    printwriter.print(memoryinfo.getOtherSwappedOutPss(i));
                    printwriter.print(',');
                } else
                {
                    printwriter.print("N/A,");
                }
                i++;
            }
            return;
        }
        if(flag3) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        int i1;
        int j1;
        int k1;
        int i2;
        int j2;
        int k2;
        int i3;
        int j3;
        int i4;
        int i5;
        int j5;
        int k5;
        if(flag1)
        {
            int j4;
            int k4;
            if(memoryinfo.hasSwappedOutPss)
                s = "SwapPss";
            else
                s = "Swap";
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "", "Pss", "Pss", "Shared", "Private", "Shared", "Private", s, "Heap", "Heap", 
                "Heap"
            });
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "", "Total", "Clean", "Dirty", "Dirty", "Clean", "Clean", "Dirty", "Size", "Alloc", 
                "Free"
            });
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "", "------", "------", "------", "------", "------", "------", "------", "------", "------", 
                "------"
            });
            j = memoryinfo.nativePss;
            k = memoryinfo.nativeSwappablePss;
            i1 = memoryinfo.nativeSharedDirty;
            j1 = memoryinfo.nativePrivateDirty;
            k1 = memoryinfo.nativeSharedClean;
            i2 = memoryinfo.nativePrivateClean;
            if(memoryinfo.hasSwappedOutPss)
                i = memoryinfo.nativeSwappedOutPss;
            else
                i = memoryinfo.nativeSwappedOut;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "Native Heap", Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(i1), Integer.valueOf(j1), Integer.valueOf(k1), Integer.valueOf(i2), Integer.valueOf(i), Long.valueOf(l), Long.valueOf(l1), 
                Long.valueOf(l2)
            });
            j = memoryinfo.dalvikPss;
            i2 = memoryinfo.dalvikSwappablePss;
            k = memoryinfo.dalvikSharedDirty;
            k1 = memoryinfo.dalvikPrivateDirty;
            i1 = memoryinfo.dalvikSharedClean;
            j1 = memoryinfo.dalvikPrivateClean;
            if(memoryinfo.hasSwappedOutPss)
                i = memoryinfo.dalvikSwappedOutPss;
            else
                i = memoryinfo.dalvikSwappedOut;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "Dalvik Heap", Integer.valueOf(j), Integer.valueOf(i2), Integer.valueOf(k), Integer.valueOf(k1), Integer.valueOf(i1), Integer.valueOf(j1), Integer.valueOf(i), Long.valueOf(l3), Long.valueOf(l4), 
                Long.valueOf(l5)
            });
        } else
        {
            if(memoryinfo.hasSwappedOutPss)
                s = "SwapPss";
            else
                s = "Swap";
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "", "Pss", "Private", "Private", s, "Heap", "Heap", "Heap"
            });
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "", "Total", "Dirty", "Clean", "Dirty", "Size", "Alloc", "Free"
            });
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "", "------", "------", "------", "------", "------", "------", "------", "------"
            });
            j1 = memoryinfo.nativePss;
            k = memoryinfo.nativePrivateDirty;
            k1 = memoryinfo.nativePrivateClean;
            if(memoryinfo.hasSwappedOutPss)
                i = memoryinfo.nativeSwappedOutPss;
            else
                i = memoryinfo.nativeSwappedOut;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "Native Heap", Integer.valueOf(j1), Integer.valueOf(k), Integer.valueOf(k1), Integer.valueOf(i), Long.valueOf(l), Long.valueOf(l1), Long.valueOf(l2)
            });
            j1 = memoryinfo.dalvikPss;
            k1 = memoryinfo.dalvikPrivateDirty;
            k = memoryinfo.dalvikPrivateClean;
            if(memoryinfo.hasSwappedOutPss)
                i = memoryinfo.dalvikSwappedOutPss;
            else
                i = memoryinfo.dalvikSwappedOut;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "Dalvik Heap", Integer.valueOf(j1), Integer.valueOf(k1), Integer.valueOf(k), Integer.valueOf(i), Long.valueOf(l3), Long.valueOf(l4), Long.valueOf(l5)
            });
        }
        j2 = memoryinfo.otherPss;
        k2 = memoryinfo.otherSwappablePss;
        i2 = memoryinfo.otherSharedDirty;
        i3 = memoryinfo.otherPrivateDirty;
        j = memoryinfo.otherSharedClean;
        i1 = memoryinfo.otherPrivateClean;
        j1 = memoryinfo.otherSwappedOut;
        i = memoryinfo.otherSwappedOutPss;
        j3 = 0;
_L17:
        if(j3 >= 17) goto _L4; else goto _L3
_L3:
        i4 = memoryinfo.getOtherPss(j3);
        j4 = memoryinfo.getOtherSwappablePss(j3);
        k4 = memoryinfo.getOtherSharedDirty(j3);
        i5 = memoryinfo.getOtherPrivateDirty(j3);
        j5 = memoryinfo.getOtherSharedClean(j3);
        k5 = memoryinfo.getOtherPrivateClean(j3);
        k = memoryinfo.getOtherSwappedOut(j3);
        k1 = memoryinfo.getOtherSwappedOutPss(j3);
          goto _L5
_L7:
        if(flag1)
        {
            s = android.os.Debug.MemoryInfo.getOtherLabel(j3);
            int i6;
            int k6;
            int l6;
            int i7;
            int j7;
            int k7;
            int l7;
            int i8;
            int j8;
            if(memoryinfo.hasSwappedOutPss)
                i6 = k1;
            else
                i6 = k;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                s, Integer.valueOf(i4), Integer.valueOf(j4), Integer.valueOf(k4), Integer.valueOf(i5), Integer.valueOf(j5), Integer.valueOf(k5), Integer.valueOf(i6), "", "", 
                ""
            });
        } else
        {
            s = android.os.Debug.MemoryInfo.getOtherLabel(j3);
            int j6;
            if(memoryinfo.hasSwappedOutPss)
                j6 = k1;
            else
                j6 = k;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                s, Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(k5), Integer.valueOf(j6), "", "", ""
            });
        }
        k6 = j2 - i4;
        l6 = k2 - j4;
        i7 = i2 - k4;
        j7 = i3 - i5;
        k7 = j - j5;
        l7 = i1 - k5;
        i8 = j1 - k;
        i6 = i - k1;
_L8:
        j3++;
        i1 = l7;
        i3 = j7;
        j2 = k6;
        j = k7;
        i2 = i7;
        k2 = l6;
        j1 = i8;
        i = i6;
        continue; /* Loop/switch isn't completed */
_L5:
        if(i4 != 0 || k4 != 0 || i5 != 0 || j5 != 0 || k5 != 0) goto _L7; else goto _L6
_L6:
        if(memoryinfo.hasSwappedOutPss)
            j8 = k1;
        else
            j8 = k;
        l7 = i1;
        j7 = i3;
        k6 = j2;
        k7 = j;
        i7 = i2;
        l6 = k2;
        i8 = j1;
        i6 = i;
        if(j8 == 0) goto _L8; else goto _L7
_L4:
        if(flag1)
        {
            int k3;
            if(!memoryinfo.hasSwappedOutPss)
                i = j1;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "Unknown", Integer.valueOf(j2), Integer.valueOf(k2), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(j), Integer.valueOf(i1), Integer.valueOf(i), "", "", 
                ""
            });
            k = memoryinfo.getTotalPss();
            k1 = memoryinfo.getTotalSwappablePss();
            i2 = memoryinfo.getTotalSharedDirty();
            j = memoryinfo.getTotalPrivateDirty();
            j1 = memoryinfo.getTotalSharedClean();
            i1 = memoryinfo.getTotalPrivateClean();
            if(memoryinfo.hasSwappedOutPss)
                i = memoryinfo.getTotalSwappedOutPss();
            else
                i = memoryinfo.getTotalSwappedOut();
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "TOTAL", Integer.valueOf(k), Integer.valueOf(k1), Integer.valueOf(i2), Integer.valueOf(j), Integer.valueOf(j1), Integer.valueOf(i1), Integer.valueOf(i), Long.valueOf(l + l3), Long.valueOf(l1 + l4), 
                Long.valueOf(l2 + l5)
            });
        } else
        {
            if(!memoryinfo.hasSwappedOutPss)
                i = j1;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "Unknown", Integer.valueOf(j2), Integer.valueOf(i3), Integer.valueOf(i1), Integer.valueOf(i), "", "", ""
            });
            k = memoryinfo.getTotalPss();
            j1 = memoryinfo.getTotalPrivateDirty();
            k1 = memoryinfo.getTotalPrivateClean();
            if(memoryinfo.hasSwappedOutPss)
                i = memoryinfo.getTotalSwappedOutPss();
            else
                i = memoryinfo.getTotalSwappedOut();
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                "TOTAL", Integer.valueOf(k), Integer.valueOf(j1), Integer.valueOf(k1), Integer.valueOf(i), Long.valueOf(l + l3), Long.valueOf(l1 + l4), Long.valueOf(l2 + l5)
            });
        }
        if(!flag2) goto _L2; else goto _L9
_L9:
        printwriter.println(" ");
        printwriter.println(" Dalvik Details");
        k1 = 17;
_L12:
        if(k1 >= 31) goto _L2; else goto _L10
_L10:
        j = memoryinfo.getOtherPss(k1);
        i3 = memoryinfo.getOtherSwappablePss(k1);
        k3 = memoryinfo.getOtherSharedDirty(k1);
        i1 = memoryinfo.getOtherPrivateDirty(k1);
        k2 = memoryinfo.getOtherSharedClean(k1);
        i2 = memoryinfo.getOtherPrivateClean(k1);
        i = memoryinfo.getOtherSwappedOut(k1);
        j1 = memoryinfo.getOtherSwappedOutPss(k1);
          goto _L11
_L14:
        if(flag1)
        {
            s = android.os.Debug.MemoryInfo.getOtherLabel(k1);
            if(memoryinfo.hasSwappedOutPss)
                i = j1;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                s, Integer.valueOf(j), Integer.valueOf(i3), Integer.valueOf(k3), Integer.valueOf(i1), Integer.valueOf(k2), Integer.valueOf(i2), Integer.valueOf(i), "", "", 
                ""
            });
        } else
        {
            s = android.os.Debug.MemoryInfo.getOtherLabel(k1);
            if(!memoryinfo.hasSwappedOutPss)
                j1 = i;
            printRow(printwriter, "%13s %8s %8s %8s %8s %8s %8s %8s", new Object[] {
                s, Integer.valueOf(j), Integer.valueOf(i1), Integer.valueOf(i2), Integer.valueOf(j1), "", "", ""
            });
        }
_L15:
        k1++;
          goto _L12
_L11:
        if(j != 0 || k3 != 0 || i1 != 0 || k2 != 0 || i2 != 0) goto _L14; else goto _L13
_L13:
        if(memoryinfo.hasSwappedOutPss)
            k = j1;
        else
            k = i;
        if(k == 0) goto _L15; else goto _L14
_L2:
        printwriter.println(" ");
        printwriter.println(" App Summary");
        printRow(printwriter, "%21s %8s", new Object[] {
            "", "Pss(KB)"
        });
        printRow(printwriter, "%21s %8s", new Object[] {
            "", "------"
        });
        printRow(printwriter, "%21s %8d", new Object[] {
            "Java Heap:", Integer.valueOf(memoryinfo.getSummaryJavaHeap())
        });
        printRow(printwriter, "%21s %8d", new Object[] {
            "Native Heap:", Integer.valueOf(memoryinfo.getSummaryNativeHeap())
        });
        printRow(printwriter, "%21s %8d", new Object[] {
            "Code:", Integer.valueOf(memoryinfo.getSummaryCode())
        });
        printRow(printwriter, "%21s %8d", new Object[] {
            "Stack:", Integer.valueOf(memoryinfo.getSummaryStack())
        });
        printRow(printwriter, "%21s %8d", new Object[] {
            "Graphics:", Integer.valueOf(memoryinfo.getSummaryGraphics())
        });
        printRow(printwriter, "%21s %8d", new Object[] {
            "Private Other:", Integer.valueOf(memoryinfo.getSummaryPrivateOther())
        });
        printRow(printwriter, "%21s %8d", new Object[] {
            "System:", Integer.valueOf(memoryinfo.getSummarySystem())
        });
        printwriter.println(" ");
        if(memoryinfo.hasSwappedOutPss)
            printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "TOTAL:", Integer.valueOf(memoryinfo.getSummaryTotalPss()), "TOTAL SWAP PSS:", Integer.valueOf(memoryinfo.getSummaryTotalSwapPss())
            });
        else
            printRow(printwriter, "%21s %8d %21s %8d", new Object[] {
                "TOTAL:", Integer.valueOf(memoryinfo.getSummaryTotalPss()), "TOTAL SWAP (KB):", Integer.valueOf(memoryinfo.getSummaryTotalSwap())
            });
        return;
        if(true) goto _L17; else goto _L16
_L16:
    }

    static void freeTextLayoutCachesIfNeeded(int i)
    {
        if(i != 0)
        {
            if((i & 4) != 0)
                i = 1;
            else
                i = 0;
            if(i != 0)
                Canvas.freeTextLayoutCaches();
        }
    }

    private String getInstrumentationLibrary(ApplicationInfo applicationinfo, InstrumentationInfo instrumentationinfo)
    {
        if(applicationinfo.primaryCpuAbi != null && applicationinfo.secondaryCpuAbi != null)
        {
            String s = VMRuntime.getInstructionSet(applicationinfo.secondaryCpuAbi);
            applicationinfo = SystemProperties.get((new StringBuilder()).append("ro.dalvik.vm.isa.").append(s).toString());
            if(applicationinfo.isEmpty())
                applicationinfo = s;
            if(VMRuntime.getRuntime().vmInstructionSet().equals(applicationinfo))
                return instrumentationinfo.secondaryNativeLibraryDir;
        }
        return instrumentationinfo.nativeLibraryDir;
    }

    public static Intent getIntentBeingBroadcast()
    {
        return (Intent)sCurrentBroadcastIntent.get();
    }

    private int getLifecycleSeq()
    {
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        int i;
        i = mLifecycleSeq;
        mLifecycleSeq = i + 1;
        resourcesmanager;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    private LoadedApk getPackageInfo(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo, ClassLoader classloader, boolean flag, boolean flag1, boolean flag2)
    {
        boolean flag3;
        ResourcesManager resourcesmanager;
        Object obj;
        LoadedApk loadedapk;
        boolean flag4;
        if(UserHandle.myUserId() != UserHandle.getUserId(applicationinfo.uid))
            flag3 = true;
        else
            flag3 = false;
        resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        if(!flag3) goto _L2; else goto _L1
_L1:
        obj = null;
_L12:
        if(obj == null) goto _L4; else goto _L3
_L3:
        obj = (LoadedApk)((WeakReference) (obj)).get();
_L13:
        if(obj == null) goto _L6; else goto _L5
_L5:
        loadedapk = ((LoadedApk) (obj));
        if(((LoadedApk) (obj)).mResources == null) goto _L8; else goto _L7
_L7:
        loadedapk = ((LoadedApk) (obj));
        if(!(((LoadedApk) (obj)).mResources.getAssets().isUpToDate() ^ true)) goto _L8; else goto _L6
_L6:
        loadedapk = JVM INSTR new #709 <Class LoadedApk>;
        if(!flag1) goto _L10; else goto _L9
_L9:
        if((applicationinfo.flags & 4) != 0)
            flag4 = true;
        else
            flag4 = false;
_L14:
        loadedapk.LoadedApk(this, applicationinfo, compatibilityinfo, classloader, flag, flag4, flag2);
        if(mSystemThread && "android".equals(applicationinfo.packageName))
            loadedapk.installSystemApplicationInfo(applicationinfo, getSystemContext().mPackageInfo.getClassLoader());
        if(!flag3) goto _L11; else goto _L8
_L8:
        resourcesmanager;
        JVM INSTR monitorexit ;
        return loadedapk;
_L2:
        if(!flag1)
            break MISSING_BLOCK_LABEL_199;
        obj = (WeakReference)mPackages.get(applicationinfo.packageName);
          goto _L12
        obj = (WeakReference)mResourcePackages.get(applicationinfo.packageName);
          goto _L12
_L4:
        obj = null;
          goto _L13
_L10:
        flag4 = false;
          goto _L14
_L11:
        if(!flag1)
            break MISSING_BLOCK_LABEL_277;
        compatibilityinfo = mPackages;
        classloader = applicationinfo.packageName;
        applicationinfo = JVM INSTR new #1370 <Class WeakReference>;
        applicationinfo.WeakReference(loadedapk);
        compatibilityinfo.put(classloader, applicationinfo);
          goto _L8
        applicationinfo;
        throw applicationinfo;
        compatibilityinfo = mResourcePackages;
        applicationinfo = applicationinfo.packageName;
        classloader = JVM INSTR new #1370 <Class WeakReference>;
        classloader.WeakReference(loadedapk);
        compatibilityinfo.put(applicationinfo, classloader);
          goto _L8
    }

    public static IPackageManager getPackageManager()
    {
        if(sPackageManager != null)
        {
            return sPackageManager;
        } else
        {
            sPackageManager = android.content.pm.IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
            return sPackageManager;
        }
    }

    static final void handleAttachAgent(String s)
    {
        VMDebug.attachAgent(s);
_L1:
        return;
        IOException ioexception;
        ioexception;
        Slog.e("ActivityThread", (new StringBuilder()).append("Attaching agent failed: ").append(s).toString());
          goto _L1
    }

    private void handleBindApplication(AppBindData appbinddata)
    {
        long l;
        Object obj3;
        l = SystemClock.uptimeMillis();
        Object obj = null;
        obj3 = obj;
        if(enable_uxe != 0)
        {
            obj3 = obj;
            if(Process.isIsolated() ^ true)
                obj3 = new BoostFramework();
        }
        VMRuntime.registerSensitiveThread();
        if(appbinddata.trackAllocation)
            DdmVmInternal.enableRecentAllocations(true);
        Process.setStartTimes(SystemClock.elapsedRealtime(), SystemClock.uptimeMillis());
        mBoundApplication = appbinddata;
        mConfiguration = new Configuration(appbinddata.config);
        mCompatConfiguration = new Configuration(appbinddata.config);
        mProfiler = new Profiler();
        if(appbinddata.initProfilerInfo != null)
        {
            mProfiler.profileFile = appbinddata.initProfilerInfo.profileFile;
            mProfiler.profileFd = appbinddata.initProfilerInfo.profileFd;
            mProfiler.samplingInterval = appbinddata.initProfilerInfo.samplingInterval;
            mProfiler.autoStopProfiler = appbinddata.initProfilerInfo.autoStopProfiler;
            mProfiler.streamingOutput = appbinddata.initProfilerInfo.streamingOutput;
        }
        Process.setArgV0(appbinddata.processName);
        DdmHandleAppName.setAppName(appbinddata.processName, UserHandle.myUserId());
        WhetstoneAppManager.setHardwareRendererIfNeeded();
        if(mProfiler.profileFd != null)
            mProfiler.startProfiling();
        if(appbinddata.appInfo.targetSdkVersion <= 12)
            AsyncTask.setDefaultExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Message.updateCheckRecycle(appbinddata.appInfo.targetSdkVersion);
        TimeZone.setDefault(null);
        LocaleList.setDefault(appbinddata.config.getLocales());
        Object obj1 = mResourcesManager;
        obj1;
        JVM INSTR monitorenter ;
        mResourcesManager.applyConfigurationToResourcesLocked(appbinddata.config, appbinddata.compatInfo);
        mCurDefaultDisplayDpi = appbinddata.config.densityDpi;
        applyCompatConfiguration(mCurDefaultDisplayDpi);
        obj1;
        JVM INSTR monitorexit ;
        boolean flag;
        Object obj5;
        Application application;
        appbinddata.info = getPackageInfoNoCheck(appbinddata.appInfo, appbinddata.compatInfo);
        Object obj4;
        ContextImpl contextimpl;
        int i;
        if((appbinddata.appInfo.flags & 0x2000) == 0)
        {
            mDensityCompatMode = true;
            Bitmap.setDefaultDensity(160);
        } else
        {
            int j = appbinddata.appInfo.getOverrideDensity();
            if(j != 0)
            {
                Log.d("ActivityThread", (new StringBuilder()).append("override app density from ").append(DisplayMetrics.DENSITY_DEVICE).append(" to ").append(j).toString());
                mDensityCompatMode = true;
                Bitmap.setDefaultDensity(j);
            }
        }
        updateDefaultDensity();
        obj4 = mCoreSettings.getString("time_12_24");
        obj1 = null;
        if(obj4 != null)
            if("24".equals(obj4))
                obj1 = Boolean.TRUE;
            else
                obj1 = Boolean.FALSE;
        DateFormat.set24HourTimePref(((Boolean) (obj1)));
        if(mCoreSettings.getInt("debug_view_attributes", 0) != 0)
            flag = true;
        else
            flag = false;
        View.mDebugViewAttributes = flag;
        if((appbinddata.appInfo.flags & 0x81) != 0)
            StrictMode.conditionallyEnableDebugLogging();
        if(appbinddata.appInfo.targetSdkVersion >= 11)
            StrictMode.enableDeathOnNetwork();
        if(appbinddata.appInfo.targetSdkVersion >= 24)
            StrictMode.enableDeathOnFileUriExposure();
        Exception exception;
        Exception exception1;
        StringBuilder stringbuilder;
        RuntimeException runtimeexception;
        Exception exception2;
        try
        {
            obj1 = android/os/Build.getDeclaredField("SERIAL");
            ((Field) (obj1)).setAccessible(true);
            ((Field) (obj1)).set(android/os/Build, appbinddata.buildSerial);
        }
        catch(Object obj2) { }
        if(appbinddata.debugMode != 0)
        {
            Debug.changeDebugPort(8100);
            if(appbinddata.debugMode == 2)
            {
                Slog.w("ActivityThread", (new StringBuilder()).append("Application ").append(appbinddata.info.getPackageName()).append(" is waiting for the debugger on port 8100...").toString());
                obj1 = ActivityManager.getService();
                try
                {
                    ((IActivityManager) (obj1)).showWaitingForDebugger(mAppThread, true);
                }
                // Misplaced declaration of an exception variable
                catch(AppBindData appbinddata)
                {
                    throw appbinddata.rethrowFromSystemServer();
                }
                Debug.waitForDebugger();
                try
                {
                    ((IActivityManager) (obj1)).showWaitingForDebugger(mAppThread, false);
                }
                // Misplaced declaration of an exception variable
                catch(AppBindData appbinddata)
                {
                    throw appbinddata.rethrowFromSystemServer();
                }
            } else
            {
                Slog.w("ActivityThread", (new StringBuilder()).append("Application ").append(appbinddata.info.getPackageName()).append(" can be debugged on port 8100...").toString());
            }
        }
        if((appbinddata.appInfo.flags & 2) != 0)
            flag = true;
        else
            flag = false;
        Trace.setAppTracingAllowed(flag);
        if(flag && appbinddata.enableBinderTracking)
            Binder.enableTracing();
        Trace.traceBegin(64L, "Setup proxies");
        obj1 = ServiceManager.getService("connectivity");
        if(obj1 != null)
        {
            obj1 = android.net.IConnectivityManager.Stub.asInterface(((IBinder) (obj1)));
            try
            {
                Proxy.setHttpProxySystemProperty(((IConnectivityManager) (obj1)).getProxyForNetwork(null));
            }
            // Misplaced declaration of an exception variable
            catch(AppBindData appbinddata)
            {
                Trace.traceEnd(64L);
                throw appbinddata.rethrowFromSystemServer();
            }
        }
        Trace.traceEnd(64L);
        if(appbinddata.instrumentationName != null)
        {
            try
            {
                obj1 = JVM INSTR new #1717 <Class ApplicationPackageManager>;
                ((ApplicationPackageManager) (obj1)).ApplicationPackageManager(null, getPackageManager());
                obj1 = ((ApplicationPackageManager) (obj1)).getInstrumentationInfo(appbinddata.instrumentationName, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1)
            {
                throw new RuntimeException((new StringBuilder()).append("Unable to find instrumentation info for: ").append(appbinddata.instrumentationName).toString());
            }
            mInstrumentationPackageName = ((InstrumentationInfo) (obj1)).packageName;
            mInstrumentationAppDir = ((InstrumentationInfo) (obj1)).sourceDir;
            mInstrumentationSplitAppDirs = ((InstrumentationInfo) (obj1)).splitSourceDirs;
            mInstrumentationLibDir = getInstrumentationLibrary(appbinddata.appInfo, ((InstrumentationInfo) (obj1)));
            mInstrumentedAppDir = appbinddata.info.getAppDir();
            mInstrumentedSplitAppDirs = appbinddata.info.getSplitAppDirs();
            mInstrumentedLibDir = appbinddata.info.getLibDir();
        } else
        {
            obj1 = null;
        }
        obj4 = ContextImpl.createAppContext(this, appbinddata.info);
        updateLocaleListFromAppContext(((Context) (obj4)), mResourcesManager.getConfiguration().getLocales());
        if(!Process.isIsolated())
            setupGraphicsSupport(((Context) (obj4)));
        if(SystemProperties.getBoolean("dalvik.vm.usejitprofiles", false))
            BaseDexClassLoader.setReporter(DexLoadReporter.getInstance());
        Trace.traceBegin(64L, "NetworkSecurityConfigProvider.install");
        NetworkSecurityConfigProvider.install(((Context) (obj4)));
        Trace.traceEnd(64L);
        if(obj1 != null)
        {
            obj5 = new ApplicationInfo();
            ((InstrumentationInfo) (obj1)).copyTo(((ApplicationInfo) (obj5)));
            ((ApplicationInfo) (obj5)).initForUser(UserHandle.myUserId());
            contextimpl = ContextImpl.createAppContext(this, getPackageInfo(((ApplicationInfo) (obj5)), appbinddata.compatInfo, ((ContextImpl) (obj4)).getClassLoader(), false, true, false));
            try
            {
                mInstrumentation = (Instrumentation)contextimpl.getClassLoader().loadClass(appbinddata.instrumentationName.getClassName()).newInstance();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1)
            {
                throw new RuntimeException((new StringBuilder()).append("Unable to instantiate instrumentation ").append(appbinddata.instrumentationName).append(": ").append(((Exception) (obj1)).toString()).toString(), ((Throwable) (obj1)));
            }
            obj5 = new ComponentName(((InstrumentationInfo) (obj1)).packageName, ((InstrumentationInfo) (obj1)).name);
            mInstrumentation.init(this, contextimpl, ((Context) (obj4)), ((ComponentName) (obj5)), appbinddata.instrumentationWatcher, appbinddata.instrumentationUiAutomationConnection);
            if(mProfiler.profileFile != null && ((InstrumentationInfo) (obj1)).handleProfiling ^ true && mProfiler.profileFd == null)
            {
                mProfiler.handlingProfiling = true;
                obj1 = new File(mProfiler.profileFile);
                ((File) (obj1)).getParentFile().mkdirs();
                Debug.startMethodTracing(((File) (obj1)).toString(), 0x800000);
            }
        } else
        {
            mInstrumentation = new Instrumentation();
        }
        if((appbinddata.appInfo.flags & 0x100000) != 0)
            VMRuntime.getRuntime().clearGrowthLimit();
        else
            VMRuntime.getRuntime().clampGrowthLimit();
        WhetstoneAppManager.trimHeapSizeIfNeeded(appbinddata.appInfo);
        ActivityThreadInjector.preloadSubActivity(this, appbinddata.appInfo.nextActivityTheme, appbinddata.info);
        ActivityThreadInjector.bindApplicationInjector(((Context) (obj4)), appbinddata.appInfo);
        obj5 = StrictMode.allowThreadDiskWrites();
        obj1 = StrictMode.getThreadPolicy();
        application = appbinddata.info.makeApplication(appbinddata.restrictedBackupMode, null);
        mInitialApplication = application;
        if(!appbinddata.restrictedBackupMode && !ArrayUtils.isEmpty(appbinddata.providers))
        {
            installContentProviders(application, appbinddata.providers);
            mH.sendEmptyMessageDelayed(132, 10000L);
        }
        mInstrumentation.onCreate(appbinddata.instrumentationArgs);
        mInstrumentation.callApplicationOnCreate(application);
_L2:
        if(appbinddata.appInfo.targetSdkVersion <= 26 || StrictMode.getThreadPolicy().equals(obj1))
            StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj5)));
        FontsContract.setApplicationContextForResources(((Context) (obj4)));
        obj1 = getPackageManager().getApplicationInfo(appbinddata.appInfo.packageName, 128, UserHandle.myUserId());
        if(((ApplicationInfo) (obj1)).metaData == null)
            break MISSING_BLOCK_LABEL_1280;
        i = ((ApplicationInfo) (obj1)).metaData.getInt("preloaded_fonts", 0);
        if(i != 0)
            try
            {
                appbinddata.info.getResources().preloadFonts(i);
            }
            // Misplaced declaration of an exception variable
            catch(AppBindData appbinddata)
            {
                throw appbinddata.rethrowFromSystemServer();
            }
        i = (int)(SystemClock.uptimeMillis() - l);
        appbinddata = null;
        if(obj4 != null)
            appbinddata = ((ContextImpl) (obj4)).getPackageName();
        if(obj3 != null && Process.isIsolated() ^ true && appbinddata != null)
            ((BoostFramework) (obj3)).perfUXEngine_events(2, 0, appbinddata, i);
        return;
        appbinddata;
        throw appbinddata;
        exception1;
        runtimeexception = JVM INSTR new #722 <Class RuntimeException>;
        obj3 = JVM INSTR new #724 <Class StringBuilder>;
        ((StringBuilder) (obj3)).StringBuilder();
        runtimeexception.RuntimeException(((StringBuilder) (obj3)).append("Exception thrown in onCreate() of ").append(appbinddata.instrumentationName).append(": ").append(exception1.toString()).toString(), exception1);
        throw runtimeexception;
        exception;
        if(appbinddata.appInfo.targetSdkVersion <= 26 || StrictMode.getThreadPolicy().equals(obj1))
            StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj5)));
        throw exception;
        exception2;
        if(mInstrumentation.onException(application, exception2)) goto _L2; else goto _L1
_L1:
        exception = JVM INSTR new #722 <Class RuntimeException>;
        stringbuilder = JVM INSTR new #724 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        exception.RuntimeException(stringbuilder.append("Unable to create application ").append(application.getClass().getName()).append(": ").append(exception2.toString()).toString(), exception2);
        throw exception;
    }

    private void handleBindService(BindServiceData bindservicedata)
    {
        Service service = (Service)mServices.get(bindservicedata.token);
        if(service == null) goto _L2; else goto _L1
_L1:
        bindservicedata.intent.setExtrasClassLoader(service.getClassLoader());
        bindservicedata.intent.prepareToEnterProcess();
        if(bindservicedata.rebind) goto _L4; else goto _L3
_L3:
        IBinder ibinder = service.onBind(bindservicedata.intent);
        ActivityManager.getService().publishService(bindservicedata.token, bindservicedata.intent, ibinder);
_L5:
        ensureJitEnabled();
_L2:
        return;
_L4:
        service.onRebind(bindservicedata.intent);
        ActivityManager.getService().serviceDoneExecuting(bindservicedata.token, 0, 0, 0);
          goto _L5
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        if(mInstrumentation.onException(service, ((Throwable) (obj)))) goto _L2; else goto _L6
_L6:
        throw new RuntimeException((new StringBuilder()).append("Unable to bind to service ").append(service).append(" with ").append(bindservicedata.intent).append(": ").append(((Exception) (obj)).toString()).toString(), ((Throwable) (obj)));
          goto _L5
    }

    private void handleCreateBackupAgent(CreateBackupAgentData createbackupagentdata)
    {
        Object obj1;
        String s1;
        String s2;
label0:
        {
            try
            {
                if(getPackageManager().getPackageInfo(createbackupagentdata.appInfo.packageName, 0, UserHandle.myUserId()).applicationInfo.uid != Process.myUid())
                {
                    StringBuilder stringbuilder = JVM INSTR new #724 <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    Slog.w("ActivityThread", stringbuilder.append("Asked to instantiate non-matching package ").append(createbackupagentdata.appInfo.packageName).toString());
                    return;
                }
            }
            // Misplaced declaration of an exception variable
            catch(CreateBackupAgentData createbackupagentdata)
            {
                throw createbackupagentdata.rethrowFromSystemServer();
            }
            unscheduleGcIdler();
            obj1 = getPackageInfoNoCheck(createbackupagentdata.appInfo, createbackupagentdata.compatInfo);
            s1 = ((LoadedApk) (obj1)).mPackageName;
            if(s1 == null)
            {
                Slog.d("ActivityThread", "Asked to create backup agent for nonexistent package");
                return;
            }
            String s = createbackupagentdata.appInfo.backupAgentName;
            s2 = s;
            if(s != null)
                break label0;
            if(createbackupagentdata.backupMode != 1)
            {
                s2 = s;
                if(createbackupagentdata.backupMode != 3)
                    break label0;
            }
            s2 = "android.app.backup.FullBackupAgent";
        }
        Object obj2 = null;
        Object obj;
        Exception exception;
        BackupAgent backupagent;
        try
        {
            obj = (BackupAgent)mBackupAgents.get(s1);
        }
        // Misplaced declaration of an exception variable
        catch(CreateBackupAgentData createbackupagentdata)
        {
            throw new RuntimeException((new StringBuilder()).append("Unable to create BackupAgent ").append(s2).append(": ").append(createbackupagentdata.toString()).toString(), createbackupagentdata);
        }
        if(obj == null) goto _L2; else goto _L1
_L1:
        obj2 = ((BackupAgent) (obj)).onBind();
_L3:
        try
        {
            ActivityManager.getService().backupAgentCreated(s1, ((IBinder) (obj2)));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(CreateBackupAgentData createbackupagentdata) { }
        break MISSING_BLOCK_LABEL_372;
_L2:
        obj = obj2;
        backupagent = (BackupAgent)((LoadedApk) (obj1)).getClassLoader().loadClass(s2).newInstance();
        obj = obj2;
        obj1 = ContextImpl.createAppContext(this, ((LoadedApk) (obj1)));
        obj = obj2;
        ((ContextImpl) (obj1)).setOuterContext(backupagent);
        obj = obj2;
        backupagent.attach(((Context) (obj1)));
        obj = obj2;
        backupagent.onCreate();
        obj = obj2;
        obj2 = backupagent.onBind();
        obj = obj2;
        mBackupAgents.put(s1, backupagent);
          goto _L3
        exception;
        obj2 = JVM INSTR new #724 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        Slog.e("ActivityThread", ((StringBuilder) (obj2)).append("Agent threw during creation: ").append(exception).toString());
        obj2 = obj;
        if(createbackupagentdata.backupMode == 2) goto _L3; else goto _L4
_L4:
        obj2 = obj;
        if(createbackupagentdata.backupMode == 3) goto _L3; else goto _L5
_L5:
        throw exception;
        throw createbackupagentdata.rethrowFromSystemServer();
    }

    private void handleCreateService(CreateServiceData createservicedata)
    {
        Service service;
        unscheduleGcIdler();
        Object obj = getPackageInfoNoCheck(createservicedata.info.applicationInfo, createservicedata.compatInfo);
        service = null;
        Object obj1;
        try
        {
            obj1 = (Service)((LoadedApk) (obj)).getClassLoader().loadClass(createservicedata.info.name).newInstance();
        }
        catch(Exception exception)
        {
            if(!mInstrumentation.onException(null, exception))
                throw new RuntimeException((new StringBuilder()).append("Unable to instantiate service ").append(createservicedata.info.name).append(": ").append(exception.toString()).toString(), exception);
            continue; /* Loop/switch isn't completed */
        }
        service = ((Service) (obj1));
_L4:
        obj1 = ContextImpl.createAppContext(this, ((LoadedApk) (obj)));
        ((ContextImpl) (obj1)).setOuterContext(service);
        obj = ((LoadedApk) (obj)).makeApplication(false, mInstrumentation);
        service.attach(((Context) (obj1)), this, createservicedata.info.name, createservicedata.token, ((Application) (obj)), ActivityManager.getService());
        service.onCreate();
        mServices.put(createservicedata.token, service);
        ActivityManager.getService().serviceDoneExecuting(createservicedata.token, 0, 0, 0);
_L2:
        return;
        Object obj2;
        obj2;
        throw ((RemoteException) (obj2)).rethrowFromSystemServer();
        obj2;
        if(mInstrumentation.onException(service, ((Throwable) (obj2)))) goto _L2; else goto _L1
_L1:
        throw new RuntimeException((new StringBuilder()).append("Unable to create service ").append(createservicedata.info.name).append(": ").append(((Exception) (obj2)).toString()).toString(), ((Throwable) (obj2)));
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void handleDestroyActivity(IBinder ibinder, boolean flag, int i, boolean flag1)
    {
        ActivityClientRecord activityclientrecord = performDestroyActivity(ibinder, flag, i, flag1);
        if(activityclientrecord == null) goto _L2; else goto _L1
_L1:
        WindowManager windowmanager;
        View view;
        cleanUpPendingRemoveWindows(activityclientrecord, flag);
        windowmanager = activityclientrecord.activity.getWindowManager();
        view = activityclientrecord.activity.mDecor;
        if(view == null) goto _L4; else goto _L3
_L3:
        if(activityclientrecord.activity.mVisibleFromServer)
            mNumVisibleActivities = mNumVisibleActivities - 1;
        Object obj = view.getWindowToken();
        if(activityclientrecord.activity.mWindowAdded)
            if(activityclientrecord.mPreserveWindow)
            {
                activityclientrecord.mPendingRemoveWindow = activityclientrecord.window;
                activityclientrecord.mPendingRemoveWindowManager = windowmanager;
                activityclientrecord.window.clearContentView();
            } else
            {
                windowmanager.removeViewImmediate(view);
            }
        if(obj == null || activityclientrecord.mPendingRemoveWindow != null) goto _L6; else goto _L5
_L5:
        WindowManagerGlobal.getInstance().closeAll(((IBinder) (obj)), activityclientrecord.activity.getClass().getName(), "Activity");
_L8:
        activityclientrecord.activity.mDecor = null;
_L4:
        if(activityclientrecord.mPendingRemoveWindow == null)
            WindowManagerGlobal.getInstance().closeAll(ibinder, activityclientrecord.activity.getClass().getName(), "Activity");
        obj = activityclientrecord.activity.getBaseContext();
        if(obj instanceof ContextImpl)
            ((ContextImpl)obj).scheduleFinalCleanup(activityclientrecord.activity.getClass().getName(), "Activity");
_L2:
        if(flag)
            try
            {
                ActivityManager.getService().activityDestroyed(ibinder);
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder)
            {
                throw ibinder.rethrowFromSystemServer();
            }
        mSomeActivitiesChanged = true;
        return;
_L6:
        if(activityclientrecord.mPendingRemoveWindow != null)
            WindowManagerGlobal.getInstance().closeAllExceptView(ibinder, view, activityclientrecord.activity.getClass().getName(), "Activity");
        if(true) goto _L8; else goto _L7
_L7:
    }

    private void handleDestroyBackupAgent(CreateBackupAgentData createbackupagentdata)
    {
        String s = getPackageInfoNoCheck(createbackupagentdata.appInfo, createbackupagentdata.compatInfo).mPackageName;
        BackupAgent backupagent = (BackupAgent)mBackupAgents.get(s);
        if(backupagent != null)
        {
            try
            {
                backupagent.onDestroy();
            }
            catch(Exception exception)
            {
                Slog.w("ActivityThread", (new StringBuilder()).append("Exception thrown in onDestroy by backup agent of ").append(createbackupagentdata.appInfo).toString());
                exception.printStackTrace();
            }
            mBackupAgents.remove(s);
        } else
        {
            Slog.w("ActivityThread", (new StringBuilder()).append("Attempt to destroy unknown backup agent ").append(createbackupagentdata).toString());
        }
    }

    private void handleDumpActivity(DumpComponentInfo dumpcomponentinfo)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy = StrictMode.allowThreadDiskWrites();
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mActivities.get(dumpcomponentinfo.token);
        if(activityclientrecord == null)
            break MISSING_BLOCK_LABEL_88;
        if(activityclientrecord.activity != null)
        {
            FastPrintWriter fastprintwriter = JVM INSTR new #2122 <Class FastPrintWriter>;
            FileOutputStream fileoutputstream = JVM INSTR new #2124 <Class FileOutputStream>;
            fileoutputstream.FileOutputStream(dumpcomponentinfo.fd.getFileDescriptor());
            fastprintwriter.FastPrintWriter(fileoutputstream);
            activityclientrecord.activity.dump(dumpcomponentinfo.prefix, dumpcomponentinfo.fd.getFileDescriptor(), fastprintwriter, dumpcomponentinfo.args);
            fastprintwriter.flush();
        }
        IoUtils.closeQuietly(dumpcomponentinfo.fd);
        StrictMode.setThreadPolicy(threadpolicy);
        return;
        Exception exception;
        exception;
        IoUtils.closeQuietly(dumpcomponentinfo.fd);
        StrictMode.setThreadPolicy(threadpolicy);
        throw exception;
    }

    static void handleDumpHeap(DumpHeapData dumpheapdata)
    {
        if(dumpheapdata.runGc)
        {
            System.gc();
            System.runFinalization();
            System.gc();
        }
        if(!dumpheapdata.managed)
            break MISSING_BLOCK_LABEL_157;
        Debug.dumpHprofData(dumpheapdata.path, dumpheapdata.fd.getFileDescriptor());
        try
        {
            dumpheapdata.fd.close();
        }
        catch(IOException ioexception)
        {
            Slog.w("ActivityThread", "Failure closing profile fd", ioexception);
        }
_L1:
        Object obj;
        try
        {
            ActivityManager.getService().dumpHeapFinished(dumpheapdata.path);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(DumpHeapData dumpheapdata)
        {
            throw dumpheapdata.rethrowFromSystemServer();
        }
        obj;
        StringBuilder stringbuilder = JVM INSTR new #724 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.w("ActivityThread", stringbuilder.append("Managed heap dump failed on path ").append(dumpheapdata.path).append(" -- can the process access this path?").toString());
        try
        {
            dumpheapdata.fd.close();
        }
        // Misplaced declaration of an exception variable
        catch(StringBuilder stringbuilder)
        {
            Slog.w("ActivityThread", "Failure closing profile fd", stringbuilder);
        }
          goto _L1
        stringbuilder;
        try
        {
            dumpheapdata.fd.close();
        }
        // Misplaced declaration of an exception variable
        catch(DumpHeapData dumpheapdata)
        {
            Slog.w("ActivityThread", "Failure closing profile fd", dumpheapdata);
        }
        throw stringbuilder;
        if(dumpheapdata.mallocInfo)
            Debug.dumpNativeMallocInfo(dumpheapdata.fd.getFileDescriptor());
        else
            Debug.dumpNativeHeap(dumpheapdata.fd.getFileDescriptor());
          goto _L1
    }

    private void handleDumpProvider(DumpComponentInfo dumpcomponentinfo)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy = StrictMode.allowThreadDiskWrites();
        ProviderClientRecord providerclientrecord = (ProviderClientRecord)mLocalProviders.get(dumpcomponentinfo.token);
        if(providerclientrecord == null)
            break MISSING_BLOCK_LABEL_84;
        if(providerclientrecord.mLocalProvider != null)
        {
            FastPrintWriter fastprintwriter = JVM INSTR new #2122 <Class FastPrintWriter>;
            FileOutputStream fileoutputstream = JVM INSTR new #2124 <Class FileOutputStream>;
            fileoutputstream.FileOutputStream(dumpcomponentinfo.fd.getFileDescriptor());
            fastprintwriter.FastPrintWriter(fileoutputstream);
            providerclientrecord.mLocalProvider.dump(dumpcomponentinfo.fd.getFileDescriptor(), fastprintwriter, dumpcomponentinfo.args);
            fastprintwriter.flush();
        }
        IoUtils.closeQuietly(dumpcomponentinfo.fd);
        StrictMode.setThreadPolicy(threadpolicy);
        return;
        Exception exception;
        exception;
        IoUtils.closeQuietly(dumpcomponentinfo.fd);
        StrictMode.setThreadPolicy(threadpolicy);
        throw exception;
    }

    private void handleDumpService(DumpComponentInfo dumpcomponentinfo)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy = StrictMode.allowThreadDiskWrites();
        Service service = (Service)mServices.get(dumpcomponentinfo.token);
        if(service == null)
            break MISSING_BLOCK_LABEL_74;
        FastPrintWriter fastprintwriter = JVM INSTR new #2122 <Class FastPrintWriter>;
        FileOutputStream fileoutputstream = JVM INSTR new #2124 <Class FileOutputStream>;
        fileoutputstream.FileOutputStream(dumpcomponentinfo.fd.getFileDescriptor());
        fastprintwriter.FastPrintWriter(fileoutputstream);
        service.dump(dumpcomponentinfo.fd.getFileDescriptor(), fastprintwriter, dumpcomponentinfo.args);
        fastprintwriter.flush();
        IoUtils.closeQuietly(dumpcomponentinfo.fd);
        StrictMode.setThreadPolicy(threadpolicy);
        return;
        Exception exception;
        exception;
        IoUtils.closeQuietly(dumpcomponentinfo.fd);
        StrictMode.setThreadPolicy(threadpolicy);
        throw exception;
    }

    private void handleEnterAnimationComplete(IBinder ibinder)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(ibinder != null)
            ((ActivityClientRecord) (ibinder)).activity.dispatchEnterAnimationComplete();
    }

    private void handleLaunchActivity(ActivityClientRecord activityclientrecord, Intent intent, String s)
    {
        unscheduleGcIdler();
        mSomeActivitiesChanged = true;
        if(activityclientrecord.profilerInfo != null)
        {
            mProfiler.setProfiler(activityclientrecord.profilerInfo);
            mProfiler.startProfiling();
        }
        handleConfigurationChanged(null, null);
        if(!ThreadedRenderer.sRendererDisabled)
            GraphicsEnvironment.earlyInitEGL();
        WindowManagerGlobal.initialize();
        if(performLaunchActivity(activityclientrecord, intent) != null)
        {
            activityclientrecord.createdConfig = new Configuration(mConfiguration);
            reportSizeConfigurations(activityclientrecord);
            Bundle bundle = activityclientrecord.state;
            intent = activityclientrecord.token;
            boolean flag = activityclientrecord.isForward;
            boolean flag1;
            if(!activityclientrecord.activity.mFinished)
                flag1 = activityclientrecord.startsNotResumed ^ true;
            else
                flag1 = false;
            handleResumeActivity(intent, false, flag, flag1, activityclientrecord.lastProcessedSeq, s);
            if(!activityclientrecord.activity.mFinished && activityclientrecord.startsNotResumed)
            {
                performPauseActivityIfNeeded(activityclientrecord, s);
                if(activityclientrecord.isPreHoneycomb())
                    activityclientrecord.state = bundle;
            }
        } else
        {
            try
            {
                ActivityManager.getService().finishActivity(activityclientrecord.token, 0, null, 0);
            }
            // Misplaced declaration of an exception variable
            catch(ActivityClientRecord activityclientrecord)
            {
                throw activityclientrecord.rethrowFromSystemServer();
            }
        }
    }

    private void handleLocalVoiceInteractionStarted(IBinder ibinder, IVoiceInteractor ivoiceinteractor)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(ibinder != null)
        {
            ibinder.voiceInteractor = ivoiceinteractor;
            ((ActivityClientRecord) (ibinder)).activity.setVoiceInteractor(ivoiceinteractor);
            if(ivoiceinteractor == null)
                ((ActivityClientRecord) (ibinder)).activity.onLocalVoiceInteractionStopped();
            else
                ((ActivityClientRecord) (ibinder)).activity.onLocalVoiceInteractionStarted();
        }
    }

    private void handleMultiWindowModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(ibinder != null)
        {
            Configuration configuration1 = new Configuration(mConfiguration);
            if(configuration != null)
                configuration1.updateFrom(configuration);
            ((ActivityClientRecord) (ibinder)).activity.dispatchMultiWindowModeChanged(flag, configuration1);
        }
    }

    private void handleNewIntent(NewIntentData newintentdata)
    {
        performNewIntents(newintentdata.token, newintentdata.intents, newintentdata.andPause);
    }

    private void handlePauseActivity(IBinder ibinder, boolean flag, boolean flag1, int i, boolean flag2, int j)
    {
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mActivities.get(ibinder);
        if(!checkAndUpdateLifecycleSeq(j, activityclientrecord, "pauseActivity"))
            return;
        if(activityclientrecord != null)
        {
            if(flag1)
                performUserLeavingActivity(activityclientrecord);
            Activity activity = activityclientrecord.activity;
            activity.mConfigChangeFlags = activity.mConfigChangeFlags | i;
            performPauseActivity(ibinder, flag, activityclientrecord.isPreHoneycomb(), "handlePauseActivity");
            if(activityclientrecord.isPreHoneycomb())
                QueuedWork.waitToFinish();
            if(!flag2)
                try
                {
                    ActivityManager.getService().activityPaused(ibinder);
                }
                // Misplaced declaration of an exception variable
                catch(IBinder ibinder)
                {
                    throw ibinder.rethrowFromSystemServer();
                }
            mSomeActivitiesChanged = true;
        }
    }

    private void handlePictureInPictureModeChanged(IBinder ibinder, boolean flag, Configuration configuration)
    {
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mActivities.get(ibinder);
        if(activityclientrecord != null)
        {
            ibinder = new Configuration(mConfiguration);
            if(configuration != null)
                ibinder.updateFrom(configuration);
            activityclientrecord.activity.dispatchPictureInPictureModeChanged(flag, ibinder);
        }
    }

    private void handleReceiver(ReceiverData receiverdata)
    {
        String s;
        IActivityManager iactivitymanager;
        Object obj1;
        unscheduleGcIdler();
        s = receiverdata.intent.getComponent().getClassName();
        Object obj = getPackageInfoNoCheck(receiverdata.info.applicationInfo, receiverdata.compatInfo);
        iactivitymanager = ActivityManager.getService();
        try
        {
            obj1 = (ContextImpl)((LoadedApk) (obj)).makeApplication(false, mInstrumentation).getBaseContext();
        }
        catch(Exception exception)
        {
            receiverdata.sendFinished(iactivitymanager);
            throw new RuntimeException((new StringBuilder()).append("Unable to instantiate receiver ").append(s).append(": ").append(exception.toString()).toString(), exception);
        }
        obj = obj1;
        if(receiverdata.info.splitName != null)
            obj = (ContextImpl)((ContextImpl) (obj1)).createContextForSplit(receiverdata.info.splitName);
        obj1 = ((ContextImpl) (obj)).getClassLoader();
        receiverdata.intent.setExtrasClassLoader(((ClassLoader) (obj1)));
        receiverdata.intent.prepareToEnterProcess();
        receiverdata.setExtrasClassLoader(((ClassLoader) (obj1)));
        obj1 = (BroadcastReceiver)((ClassLoader) (obj1)).loadClass(s).newInstance();
        sCurrentBroadcastIntent.set(receiverdata.intent);
        ((BroadcastReceiver) (obj1)).setPendingResult(receiverdata);
        ((BroadcastReceiver) (obj1)).onReceive(((ContextImpl) (obj)).getReceiverRestrictedContext(), receiverdata.intent);
        sCurrentBroadcastIntent.set(null);
_L2:
        if(((BroadcastReceiver) (obj1)).getPendingResult() != null)
            receiverdata.finish();
        return;
        Exception exception1;
        exception1;
        receiverdata.sendFinished(iactivitymanager);
        if(!mInstrumentation.onException(obj1, exception1))
        {
            RuntimeException runtimeexception = JVM INSTR new #722 <Class RuntimeException>;
            receiverdata = JVM INSTR new #724 <Class StringBuilder>;
            receiverdata.StringBuilder();
            runtimeexception.RuntimeException(receiverdata.append("Unable to start receiver ").append(s).append(": ").append(exception1.toString()).toString(), exception1);
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_301;
        receiverdata;
        sCurrentBroadcastIntent.set(null);
        throw receiverdata;
        sCurrentBroadcastIntent.set(null);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void handleRelaunchActivity(ActivityClientRecord activityclientrecord)
    {
        Object obj;
        int i;
        unscheduleGcIdler();
        mSomeActivitiesChanged = true;
        obj = null;
        i = 0;
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        int j;
        IBinder ibinder;
        j = mRelaunchingActivities.size();
        ibinder = activityclientrecord.token;
        ActivityClientRecord activityclientrecord1;
        int k;
        activityclientrecord1 = null;
        k = 0;
_L3:
        if(k >= j) goto _L2; else goto _L1
_L1:
        activityclientrecord = (ActivityClientRecord)mRelaunchingActivities.get(k);
        int l;
        int i1;
        int j1;
        l = j;
        i1 = i;
        j1 = k;
        if(activityclientrecord.token != ibinder)
            break MISSING_BLOCK_LABEL_116;
        activityclientrecord1 = activityclientrecord;
        i1 = i | activityclientrecord.pendingConfigChanges;
        mRelaunchingActivities.remove(k);
        j1 = k - 1;
        l = j - 1;
        k = j1 + 1;
        j = l;
        i = i1;
          goto _L3
_L2:
        if(activityclientrecord1 == null)
            return;
        activityclientrecord = obj;
        if(mPendingConfiguration != null)
        {
            activityclientrecord = mPendingConfiguration;
            mPendingConfiguration = null;
        }
        resourcesmanager;
        JVM INSTR monitorexit ;
label0:
        {
            Object obj1;
            if(activityclientrecord1.lastProcessedSeq > activityclientrecord1.relaunchSeq)
                Slog.wtf("ActivityThread", (new StringBuilder()).append("For some reason target: ").append(activityclientrecord1).append(" has lower sequence: ").append(activityclientrecord1.relaunchSeq).append(" than current sequence: ").append(activityclientrecord1.lastProcessedSeq).toString());
            else
                activityclientrecord1.lastProcessedSeq = activityclientrecord1.relaunchSeq;
            obj1 = activityclientrecord;
            if(activityclientrecord1.createdConfig == null)
                break label0;
            if(mConfiguration != null)
            {
                obj1 = activityclientrecord;
                if(!activityclientrecord1.createdConfig.isOtherSeqNewer(mConfiguration))
                    break label0;
                obj1 = activityclientrecord;
                if(mConfiguration.diff(activityclientrecord1.createdConfig) == 0)
                    break label0;
            }
            if(activityclientrecord != null)
            {
                obj1 = activityclientrecord;
                if(!activityclientrecord1.createdConfig.isOtherSeqNewer(activityclientrecord))
                    break label0;
            }
            obj1 = activityclientrecord1.createdConfig;
        }
        if(obj1 != null)
        {
            mCurDefaultDisplayDpi = ((Configuration) (obj1)).densityDpi;
            updateDefaultDensity();
            handleConfigurationChanged(((Configuration) (obj1)), null);
        }
        activityclientrecord = (ActivityClientRecord)mActivities.get(activityclientrecord1.token);
        if(activityclientrecord != null)
            break MISSING_BLOCK_LABEL_395;
        if(activityclientrecord1.onlyLocalRequest)
            break MISSING_BLOCK_LABEL_369;
        ActivityManager.getService().activityRelaunched(activityclientrecord1.token);
        return;
        activityclientrecord;
        throw activityclientrecord;
        activityclientrecord;
        throw activityclientrecord.rethrowFromSystemServer();
        Object obj2 = activityclientrecord.activity;
        obj2.mConfigChangeFlags = ((Activity) (obj2)).mConfigChangeFlags | i;
        activityclientrecord.onlyLocalRequest = activityclientrecord1.onlyLocalRequest;
        activityclientrecord.mPreserveWindow = activityclientrecord1.mPreserveWindow;
        activityclientrecord.lastProcessedSeq = activityclientrecord1.lastProcessedSeq;
        activityclientrecord.relaunchSeq = activityclientrecord1.relaunchSeq;
        obj2 = activityclientrecord.activity.mIntent;
        activityclientrecord.activity.mChangingConfigurations = true;
        try
        {
            if(activityclientrecord.mPreserveWindow || activityclientrecord.onlyLocalRequest)
                WindowManagerGlobal.getWindowSession().prepareToReplaceWindows(activityclientrecord.token, activityclientrecord.onlyLocalRequest ^ true);
        }
        // Misplaced declaration of an exception variable
        catch(ActivityClientRecord activityclientrecord)
        {
            throw activityclientrecord.rethrowFromSystemServer();
        }
        if(!activityclientrecord.paused)
            performPauseActivity(activityclientrecord.token, false, activityclientrecord.isPreHoneycomb(), "handleRelaunchActivity");
        if(activityclientrecord.state == null && activityclientrecord.stopped ^ true && activityclientrecord.isPreHoneycomb() ^ true)
            callCallActivityOnSaveInstanceState(activityclientrecord);
        handleDestroyActivity(activityclientrecord.token, false, i, true);
        activityclientrecord.activity = null;
        activityclientrecord.window = null;
        activityclientrecord.hideForNow = false;
        activityclientrecord.nextIdle = null;
        if(activityclientrecord1.pendingResults != null)
            if(activityclientrecord.pendingResults == null)
                activityclientrecord.pendingResults = activityclientrecord1.pendingResults;
            else
                activityclientrecord.pendingResults.addAll(activityclientrecord1.pendingResults);
        if(activityclientrecord1.pendingIntents != null)
            if(activityclientrecord.pendingIntents == null)
                activityclientrecord.pendingIntents = activityclientrecord1.pendingIntents;
            else
                activityclientrecord.pendingIntents.addAll(activityclientrecord1.pendingIntents);
        activityclientrecord.startsNotResumed = activityclientrecord1.startsNotResumed;
        activityclientrecord.overrideConfig = activityclientrecord1.overrideConfig;
        handleLaunchActivity(activityclientrecord, ((Intent) (obj2)), "handleRelaunchActivity");
        if(activityclientrecord1.onlyLocalRequest)
            break MISSING_BLOCK_LABEL_688;
        ActivityManager.getService().activityRelaunched(activityclientrecord.token);
        if(activityclientrecord.window != null)
            activityclientrecord.window.reportActivityRelaunched();
        return;
        activityclientrecord;
        throw activityclientrecord.rethrowFromSystemServer();
    }

    private void handleSendResult(ResultData resultdata)
    {
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mActivities.get(resultdata.token);
        if(activityclientrecord != null)
        {
            boolean flag = activityclientrecord.paused ^ true;
            if(!activityclientrecord.activity.mFinished && activityclientrecord.activity.mDecor != null && activityclientrecord.hideForNow && flag)
                updateVisibility(activityclientrecord, true);
            if(flag)
                try
                {
                    activityclientrecord.activity.mCalled = false;
                    activityclientrecord.activity.mTemporaryPause = true;
                    mInstrumentation.callActivityOnPause(activityclientrecord.activity);
                    if(!activityclientrecord.activity.mCalled)
                    {
                        SuperNotCalledException supernotcalledexception = JVM INSTR new #2453 <Class SuperNotCalledException>;
                        StringBuilder stringbuilder = JVM INSTR new #724 <Class StringBuilder>;
                        stringbuilder.StringBuilder();
                        supernotcalledexception.SuperNotCalledException(stringbuilder.append("Activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(" did not call through to super.onPause()").toString());
                        throw supernotcalledexception;
                    }
                }
                // Misplaced declaration of an exception variable
                catch(ResultData resultdata)
                {
                    throw resultdata;
                }
                catch(Exception exception)
                {
                    if(!mInstrumentation.onException(activityclientrecord.activity, exception))
                        throw new RuntimeException((new StringBuilder()).append("Unable to pause activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(": ").append(exception.toString()).toString(), exception);
                }
            checkAndBlockForNetworkAccess();
            deliverResults(activityclientrecord, resultdata.results);
            if(flag)
            {
                activityclientrecord.activity.performResume();
                activityclientrecord.activity.mTemporaryPause = false;
            }
        }
    }

    private void handleServiceArgs(ServiceArgsData serviceargsdata)
    {
        Service service = (Service)mServices.get(serviceargsdata.token);
        if(service == null) goto _L2; else goto _L1
_L1:
        if(serviceargsdata.args != null)
        {
            serviceargsdata.args.setExtrasClassLoader(service.getClassLoader());
            serviceargsdata.args.prepareToEnterProcess();
        }
        if(serviceargsdata.taskRemoved) goto _L4; else goto _L3
_L3:
        int i = service.onStartCommand(serviceargsdata.args, serviceargsdata.flags, serviceargsdata.startId);
_L5:
        QueuedWork.waitToFinish();
        ActivityManager.getService().serviceDoneExecuting(serviceargsdata.token, 1, serviceargsdata.startId, i);
        ensureJitEnabled();
_L2:
        return;
_L4:
        service.onTaskRemoved(serviceargsdata.args);
        i = 1000;
          goto _L5
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        if(mInstrumentation.onException(service, ((Throwable) (obj)))) goto _L2; else goto _L6
_L6:
        throw new RuntimeException((new StringBuilder()).append("Unable to start service ").append(service).append(" with ").append(serviceargsdata.args).append(": ").append(((Exception) (obj)).toString()).toString(), ((Throwable) (obj)));
          goto _L5
    }

    private void handleSetCoreSettings(Bundle bundle)
    {
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        mCoreSettings = bundle;
        resourcesmanager;
        JVM INSTR monitorexit ;
        onCoreSettingsChange();
        return;
        bundle;
        throw bundle;
    }

    private void handleSleeping(IBinder ibinder, boolean flag)
    {
        ActivityClientRecord activityclientrecord;
        activityclientrecord = (ActivityClientRecord)mActivities.get(ibinder);
        if(activityclientrecord == null)
        {
            Log.w("ActivityThread", (new StringBuilder()).append("handleSleeping: no activity for token ").append(ibinder).toString());
            return;
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_233;
        if(!activityclientrecord.stopped && activityclientrecord.isPreHoneycomb() ^ true)
        {
            if(!activityclientrecord.activity.mFinished && activityclientrecord.state == null)
                callCallActivityOnSaveInstanceState(activityclientrecord);
            try
            {
                activityclientrecord.activity.performStop(false);
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder)
            {
                if(!mInstrumentation.onException(activityclientrecord.activity, ibinder))
                    throw new RuntimeException((new StringBuilder()).append("Unable to stop activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(": ").append(ibinder.toString()).toString(), ibinder);
            }
            activityclientrecord.stopped = true;
            EventLog.writeEvent(30049, new Object[] {
                Integer.valueOf(UserHandle.myUserId()), activityclientrecord.activity.getComponentName().getClassName(), "sleeping"
            });
        }
        if(!activityclientrecord.isPreHoneycomb())
            QueuedWork.waitToFinish();
        ActivityManager.getService().activitySlept(activityclientrecord.token);
_L1:
        return;
        ibinder;
        throw ibinder.rethrowFromSystemServer();
        if(activityclientrecord.stopped && activityclientrecord.activity.mVisibleFromServer)
        {
            activityclientrecord.activity.performRestart();
            activityclientrecord.stopped = false;
        }
          goto _L1
    }

    private void handleStartBinderTracking()
    {
        Binder.enableTracing();
    }

    private void handleStopActivity(IBinder ibinder, boolean flag, int i, int j)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(!checkAndUpdateLifecycleSeq(j, ibinder, "stopActivity"))
            return;
        Object obj = ((ActivityClientRecord) (ibinder)).activity;
        obj.mConfigChangeFlags = ((Activity) (obj)).mConfigChangeFlags | i;
        obj = new StopInfo(null);
        performStopActivityInner(ibinder, ((StopInfo) (obj)), flag, true, "handleStopActivity");
        updateVisibility(ibinder, flag);
        if(!ibinder.isPreHoneycomb())
            QueuedWork.waitToFinish();
        obj.activity = ibinder;
        obj.state = ((ActivityClientRecord) (ibinder)).state;
        obj.persistentState = ((ActivityClientRecord) (ibinder)).persistentState;
        mH.post(((Runnable) (obj)));
        mSomeActivitiesChanged = true;
    }

    private void handleStopBinderTrackingAndDump(ParcelFileDescriptor parcelfiledescriptor)
    {
        Binder.disableTracing();
        Binder.getTransactionTracker().writeTracesToFile(parcelfiledescriptor);
        IoUtils.closeQuietly(parcelfiledescriptor);
        Binder.getTransactionTracker().clearTraces();
        return;
        Exception exception;
        exception;
        IoUtils.closeQuietly(parcelfiledescriptor);
        Binder.getTransactionTracker().clearTraces();
        throw exception;
    }

    private void handleStopService(IBinder ibinder)
    {
        Service service;
        service = (Service)mServices.remove(ibinder);
        if(service == null)
            break MISSING_BLOCK_LABEL_168;
        service.onDestroy();
        service.detachAndCleanUp();
        Context context = service.getBaseContext();
        if(context instanceof ContextImpl)
        {
            String s = service.getClassName();
            ((ContextImpl)context).scheduleFinalCleanup(s, "Service");
        }
        QueuedWork.waitToFinish();
        ActivityManager.getService().serviceDoneExecuting(ibinder, 2, 0, 0);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        try
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        catch(Exception exception)
        {
            if(!mInstrumentation.onException(service, exception))
                throw new RuntimeException((new StringBuilder()).append("Unable to stop service ").append(service).append(": ").append(exception.toString()).toString(), exception);
            Slog.i("ActivityThread", (new StringBuilder()).append("handleStopService: exception for ").append(ibinder).toString(), exception);
        }
          goto _L1
        Slog.i("ActivityThread", (new StringBuilder()).append("handleStopService: token=").append(ibinder).append(" not found.").toString());
          goto _L1
    }

    private void handleUnbindService(BindServiceData bindservicedata)
    {
        Service service = (Service)mServices.get(bindservicedata.token);
        if(service == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        bindservicedata.intent.setExtrasClassLoader(service.getClassLoader());
        bindservicedata.intent.prepareToEnterProcess();
        flag = service.onUnbind(bindservicedata.intent);
        if(!flag) goto _L4; else goto _L3
_L3:
        ActivityManager.getService().unbindFinished(bindservicedata.token, bindservicedata.intent, flag);
_L2:
        return;
_L4:
        ActivityManager.getService().serviceDoneExecuting(bindservicedata.token, 0, 0, 0);
          goto _L2
        RemoteException remoteexception;
        remoteexception;
        try
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        catch(Exception exception)
        {
            if(!mInstrumentation.onException(service, exception))
                throw new RuntimeException((new StringBuilder()).append("Unable to unbind to service ").append(service).append(" with ").append(bindservicedata.intent).append(": ").append(exception.toString()).toString(), exception);
        }
          goto _L2
    }

    private void handleUpdatePackageCompatibilityInfo(UpdateCompatibilityData updatecompatibilitydata)
    {
        LoadedApk loadedapk = peekPackageInfo(updatecompatibilitydata.pkg, false);
        if(loadedapk != null)
            loadedapk.setCompatibilityInfo(updatecompatibilitydata.info);
        loadedapk = peekPackageInfo(updatecompatibilitydata.pkg, true);
        if(loadedapk != null)
            loadedapk.setCompatibilityInfo(updatecompatibilitydata.info);
        handleConfigurationChanged(mConfiguration, updatecompatibilitydata.info);
        WindowManagerGlobal.getInstance().reportNewConfiguration(mConfiguration);
    }

    private void handleWindowVisibility(IBinder ibinder, boolean flag)
    {
        ActivityClientRecord activityclientrecord;
        activityclientrecord = (ActivityClientRecord)mActivities.get(ibinder);
        if(activityclientrecord == null)
        {
            Log.w("ActivityThread", (new StringBuilder()).append("handleWindowVisibility: no activity for token ").append(ibinder).toString());
            return;
        }
        if(flag || !(activityclientrecord.stopped ^ true)) goto _L2; else goto _L1
_L1:
        performStopActivityInner(activityclientrecord, null, flag, false, "handleWindowVisibility");
_L4:
        if(activityclientrecord.activity.mDecor != null)
            updateVisibility(activityclientrecord, flag);
        mSomeActivitiesChanged = true;
        return;
_L2:
        if(flag && activityclientrecord.stopped)
        {
            unscheduleGcIdler();
            activityclientrecord.activity.performRestart();
            activityclientrecord.stopped = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private final void incProviderRefLocked(ProviderRefCount providerrefcount, boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        providerrefcount.stableCount = providerrefcount.stableCount + 1;
        if(providerrefcount.stableCount != 1)
            break MISSING_BLOCK_LABEL_65;
        byte byte0;
        if(providerrefcount.removePending)
        {
            byte0 = -1;
            providerrefcount.removePending = false;
            mH.removeMessages(131, providerrefcount);
        } else
        {
            byte0 = 0;
        }
        ActivityManager.getService().refContentProvider(providerrefcount.holder.connection, 1, byte0);
_L4:
        return;
_L2:
        providerrefcount.unstableCount = providerrefcount.unstableCount + 1;
        if(providerrefcount.unstableCount == 1)
            if(providerrefcount.removePending)
            {
                providerrefcount.removePending = false;
                mH.removeMessages(131, providerrefcount);
            } else
            {
                try
                {
                    ActivityManager.getService().refContentProvider(providerrefcount.holder.connection, 0, 1);
                }
                // Misplaced declaration of an exception variable
                catch(ProviderRefCount providerrefcount) { }
            }
        continue; /* Loop/switch isn't completed */
        providerrefcount;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void installContentProviders(Context context, List list)
    {
        ArrayList arraylist = new ArrayList();
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            ContentProviderHolder contentproviderholder = installProvider(context, null, (ProviderInfo)list.next(), false, true, true);
            if(contentproviderholder != null)
            {
                contentproviderholder.noReleaseNeeded = true;
                arraylist.add(contentproviderholder);
            }
        } while(true);
        try
        {
            ActivityManager.getService().publishContentProviders(getApplicationThread(), arraylist);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
    }

    private ContentProviderHolder installProvider(Context context, ContentProviderHolder contentproviderholder, ProviderInfo providerinfo, boolean flag, boolean flag1, boolean flag2)
    {
        Object obj = null;
        if(contentproviderholder != null && contentproviderholder.provider != null) goto _L2; else goto _L1
_L1:
        if(flag)
            Slog.d("ActivityThread", (new StringBuilder()).append("Loading provider ").append(providerinfo.authority).append(": ").append(providerinfo.name).toString());
        obj = null;
        Object obj1 = providerinfo.applicationInfo;
        IBinder ibinder;
        ComponentName componentname;
        if(!context.getPackageName().equals(((ApplicationInfo) (obj1)).packageName))
            if(mInitialApplication != null && mInitialApplication.getPackageName().equals(((ApplicationInfo) (obj1)).packageName))
                context = mInitialApplication;
            else
                try
                {
                    context = context.createPackageContext(((ApplicationInfo) (obj1)).packageName, 1);
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    context = ((Context) (obj));
                }
        if(context == null)
        {
            Slog.w("ActivityThread", (new StringBuilder()).append("Unable to get context for package ").append(((ApplicationInfo) (obj1)).packageName).append(" while loading content provider ").append(providerinfo.name).toString());
            return null;
        }
        obj = context;
        if(providerinfo.splitName != null)
            try
            {
                obj = context.createContextForSplit(providerinfo.splitName);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new RuntimeException(context);
            }
        try
        {
            context = (ContentProvider)((Context) (obj)).getClassLoader().loadClass(providerinfo.name).newInstance();
            obj1 = context.getIContentProvider();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            if(!mInstrumentation.onException(null, context))
                throw new RuntimeException((new StringBuilder()).append("Unable to get provider ").append(providerinfo.name).append(": ").append(context.toString()).toString(), context);
            else
                return null;
        }
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_290;
        context = JVM INSTR new #724 <Class StringBuilder>;
        context.StringBuilder();
        Slog.e("ActivityThread", context.append("Failed to instantiate class ").append(providerinfo.name).append(" from sourceDir ").append(providerinfo.applicationInfo.sourceDir).toString());
        return null;
        context.attachInfo(((Context) (obj)), providerinfo);
        obj = obj1;
_L7:
        obj1 = mProviderMap;
        obj1;
        JVM INSTR monitorenter ;
        ibinder = ((IContentProvider) (obj)).asBinder();
        if(context == null) goto _L4; else goto _L3
_L3:
        componentname = JVM INSTR new #958 <Class ComponentName>;
        componentname.ComponentName(providerinfo.packageName, providerinfo.name);
        contentproviderholder = (ProviderClientRecord)mLocalProvidersByName.get(componentname);
        if(contentproviderholder == null) goto _L6; else goto _L5
_L5:
        context = ((ProviderClientRecord) (contentproviderholder)).mProvider;
        context = contentproviderholder;
_L8:
        context = ((ProviderClientRecord) (context)).mHolder;
_L11:
        obj1;
        JVM INSTR monitorexit ;
        return context;
_L2:
        obj1 = contentproviderholder.provider;
        context = ((Context) (obj));
        obj = obj1;
          goto _L7
_L6:
        contentproviderholder = new ContentProviderHolder(providerinfo);
        contentproviderholder.provider = ((IContentProvider) (obj));
        contentproviderholder.noReleaseNeeded = true;
        context = installProviderAuthoritiesLocked(((IContentProvider) (obj)), context, contentproviderholder);
        mLocalProviders.put(ibinder, context);
        mLocalProvidersByName.put(componentname, context);
          goto _L8
_L4:
        providerinfo = (ProviderRefCount)mProviderRefCountMap.get(ibinder);
        if(providerinfo == null) goto _L10; else goto _L9
_L9:
        context = providerinfo;
        if(flag1)
            break MISSING_BLOCK_LABEL_552;
        incProviderRefLocked(providerinfo, flag2);
        ActivityManager.getService().removeContentProvider(contentproviderholder.connection, flag2);
        context = providerinfo;
_L14:
        context = ((ProviderRefCount) (context)).holder;
          goto _L11
_L10:
        providerinfo = installProviderAuthoritiesLocked(((IContentProvider) (obj)), context, contentproviderholder);
        if(!flag1) goto _L13; else goto _L12
_L12:
        context = JVM INSTR new #72  <Class ActivityThread$ProviderRefCount>;
        context.ProviderRefCount(contentproviderholder, providerinfo, 1000, 1000);
_L15:
        mProviderRefCountMap.put(ibinder, context);
          goto _L14
        context;
_L16:
        obj1;
        JVM INSTR monitorexit ;
        throw context;
_L13:
        if(!flag2)
            break MISSING_BLOCK_LABEL_630;
        context = new ProviderRefCount(contentproviderholder, providerinfo, 1, 0);
          goto _L15
        context = new ProviderRefCount(contentproviderholder, providerinfo, 0, 1);
          goto _L15
        context;
          goto _L16
        context;
        context = providerinfo;
          goto _L14
    }

    private ProviderClientRecord installProviderAuthoritiesLocked(IContentProvider icontentprovider, ContentProvider contentprovider, ContentProviderHolder contentproviderholder)
    {
        String as[];
        int i;
        as = contentproviderholder.info.authority.split(";");
        i = UserHandle.getUserId(contentproviderholder.info.applicationInfo.uid);
        if(icontentprovider == null) goto _L2; else goto _L1
_L1:
        int j;
        int l;
        j = as.length;
        l = 0;
_L4:
        String s;
        if(l < j)
        {
            s = as[l];
            break MISSING_BLOCK_LABEL_56;
        }
          goto _L2
        if(s.equals("com.android.contacts") || s.equals("call_log") || s.equals("call_log_shadow") || s.equals("com.android.blockednumber") || s.equals("com.android.calendar") || s.equals("downloads") || s.equals("telephony"))
            Binder.allowBlocking(icontentprovider.asBinder());
        l++;
        continue; /* Loop/switch isn't completed */
_L2:
        icontentprovider = new ProviderClientRecord(as, icontentprovider, contentprovider, contentproviderholder);
        int k = as.length;
        int i1 = 0;
        while(i1 < k) 
        {
            contentproviderholder = as[i1];
            contentprovider = new ProviderKey(contentproviderholder, i);
            if((ProviderClientRecord)mProviderMap.get(contentprovider) != null)
                Slog.w("ActivityThread", (new StringBuilder()).append("Content provider ").append(((ProviderClientRecord) (icontentprovider)).mHolder.info.name).append(" already published as ").append(contentproviderholder).toString());
            else
                mProviderMap.put(contentprovider, icontentprovider);
            i1++;
        }
        return icontentprovider;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isSystem()
    {
        boolean flag;
        if(sCurrentActivityThread != null)
            flag = sCurrentActivityThread.mSystemThread;
        else
            flag = false;
        return flag;
    }

    public static boolean isWaitingToUse()
    {
        return sWaitingToUse;
    }

    public static void main(String args[])
    {
        Trace.traceBegin(64L, "ActivityThreadMain");
        CloseGuard.setEnabled(false);
        Environment.initForCurrentUser();
        EventLogger.setReporter(new EventLoggingReporter(null));
        TrustedCertificateStore.setDefaultUserDirectory(Environment.getUserConfigDirectory(UserHandle.myUserId()));
        Process.setArgV0("<pre-initialized>");
        Looper.prepareMainLooper();
        args = new ActivityThread();
        WhetstoneAppManager.getInstance();
        args.attach(false);
        if(sMainThreadHandler == null)
            sMainThreadHandler = args.getHandler();
        Trace.traceEnd(64L);
        Looper.loop();
        throw new RuntimeException("Main thread loop unexpectedly exited");
    }

    private native void nDumpGraphicsInfo(FileDescriptor filedescriptor);

    private void onCoreSettingsChange()
    {
        boolean flag;
        if(mCoreSettings.getInt("debug_view_attributes", 0) != 0)
            flag = true;
        else
            flag = false;
        if(flag != View.mDebugViewAttributes)
        {
            View.mDebugViewAttributes = flag;
            requestRelaunchAllActivities();
        }
    }

    private Configuration performActivityConfigurationChanged(Activity activity, Configuration configuration, Configuration configuration1, int i, boolean flag)
    {
        IBinder ibinder;
        boolean flag1;
        if(activity == null)
            throw new IllegalArgumentException("No activity provided.");
        ibinder = activity.getActivityToken();
        if(ibinder == null)
            throw new IllegalArgumentException("Activity token not set. Is the activity attached?");
        flag1 = false;
        if(activity.mCurrentConfig != null) goto _L2; else goto _L1
_L1:
        boolean flag2 = true;
_L4:
        if(!flag2 && flag ^ true)
            return null;
        break; /* Loop/switch isn't completed */
_L2:
        int j = activity.mCurrentConfig.diffPublicOnly(configuration);
        if(j == 0)
        {
            flag2 = flag1;
            if(!(mResourcesManager.isSameResourcesOverrideConfig(ibinder, configuration1) ^ true))
                continue; /* Loop/switch isn't completed */
        }
        if(mUpdatingSystemConfig)
        {
            flag2 = flag1;
            if((activity.mActivityInfo.getRealConfigChanged() & j) != 0)
                continue; /* Loop/switch isn't completed */
        }
        flag2 = true;
        if(true) goto _L4; else goto _L3
_L3:
        Configuration configuration2 = activity.getOverrideConfiguration();
        configuration1 = createNewConfigAndUpdateIfNotNull(configuration1, configuration2);
        mResourcesManager.updateResourcesForActivity(ibinder, configuration1, i, flag);
        activity.mConfigChangeFlags = 0;
        activity.mCurrentConfig = new Configuration(configuration);
        configuration = createNewConfigAndUpdateIfNotNull(configuration, configuration2);
        if(flag)
            activity.dispatchMovedToDisplay(i, configuration);
        if(flag2)
        {
            activity.mCalled = false;
            activity.onConfigurationChanged(configuration);
            if(!activity.mCalled)
                throw new SuperNotCalledException((new StringBuilder()).append("Activity ").append(activity.getLocalClassName()).append(" did not call through to super.onConfigurationChanged()").toString());
        }
        return configuration;
    }

    private void performConfigurationChanged(ComponentCallbacks2 componentcallbacks2, Configuration configuration)
    {
        Configuration configuration1 = null;
        if(componentcallbacks2 instanceof ContextThemeWrapper)
            configuration1 = ((ContextThemeWrapper)componentcallbacks2).getOverrideConfiguration();
        componentcallbacks2.onConfigurationChanged(createNewConfigAndUpdateIfNotNull(configuration, configuration1));
    }

    private Configuration performConfigurationChangedForActivity(ActivityClientRecord activityclientrecord, Configuration configuration, int i, boolean flag)
    {
        ActivityClientRecord._2D_get0(activityclientrecord).setTo(configuration);
        if(activityclientrecord.overrideConfig != null)
            ActivityClientRecord._2D_get0(activityclientrecord).updateFrom(activityclientrecord.overrideConfig);
        configuration = performActivityConfigurationChanged(activityclientrecord.activity, ActivityClientRecord._2D_get0(activityclientrecord), activityclientrecord.overrideConfig, i, flag);
        freeTextLayoutCachesIfNeeded(activityclientrecord.activity.mCurrentConfig.diff(ActivityClientRecord._2D_get0(activityclientrecord)));
        return configuration;
    }

    private void performConfigurationChangedForActivity(ActivityClientRecord activityclientrecord, Configuration configuration)
    {
        performConfigurationChangedForActivity(activityclientrecord, configuration, activityclientrecord.activity.getDisplay().getDisplayId(), false);
    }

    private ActivityClientRecord performDestroyActivity(IBinder ibinder, boolean flag, int i, boolean flag1)
    {
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mActivities.get(ibinder);
        Object obj = null;
        if(activityclientrecord == null)
            break MISSING_BLOCK_LABEL_413;
        Class class1 = activityclientrecord.activity.getClass();
        obj = activityclientrecord.activity;
        obj.mConfigChangeFlags = ((Activity) (obj)).mConfigChangeFlags | i;
        if(flag)
            activityclientrecord.activity.mFinished = true;
        performPauseActivityIfNeeded(activityclientrecord, "destroy");
        if(!activityclientrecord.stopped)
        {
            SuperNotCalledException supernotcalledexception;
            try
            {
                activityclientrecord.activity.performStop(activityclientrecord.mPreserveWindow);
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder)
            {
                throw ibinder;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                if(!mInstrumentation.onException(activityclientrecord.activity, ((Throwable) (obj))))
                    throw new RuntimeException((new StringBuilder()).append("Unable to stop activity ").append(safeToComponentShortString(activityclientrecord.intent)).append(": ").append(((Exception) (obj)).toString()).toString(), ((Throwable) (obj)));
            }
            activityclientrecord.stopped = true;
            EventLog.writeEvent(30049, new Object[] {
                Integer.valueOf(UserHandle.myUserId()), activityclientrecord.activity.getComponentName().getClassName(), "destroy"
            });
        }
        if(flag1)
            try
            {
                activityclientrecord.lastNonConfigurationInstances = activityclientrecord.activity.retainNonConfigurationInstances();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                if(!mInstrumentation.onException(activityclientrecord.activity, ((Throwable) (obj))))
                    throw new RuntimeException((new StringBuilder()).append("Unable to retain activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(": ").append(((Exception) (obj)).toString()).toString(), ((Throwable) (obj)));
            }
        try
        {
            activityclientrecord.activity.mCalled = false;
            mInstrumentation.callActivityOnDestroy(activityclientrecord.activity);
            if(!activityclientrecord.activity.mCalled)
            {
                supernotcalledexception = JVM INSTR new #2453 <Class SuperNotCalledException>;
                obj = JVM INSTR new #724 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                supernotcalledexception.SuperNotCalledException(((StringBuilder) (obj)).append("Activity ").append(safeToComponentShortString(activityclientrecord.intent)).append(" did not call through to super.onDestroy()").toString());
                throw supernotcalledexception;
            }
            break MISSING_BLOCK_LABEL_389;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder;
        }
        catch(Exception exception)
        {
            obj = class1;
            if(!mInstrumentation.onException(activityclientrecord.activity, exception))
                throw new RuntimeException((new StringBuilder()).append("Unable to destroy activity ").append(safeToComponentShortString(activityclientrecord.intent)).append(": ").append(exception.toString()).toString(), exception);
        }
          goto _L1
        obj = class1;
        if(activityclientrecord.window == null)
            break MISSING_BLOCK_LABEL_413;
        activityclientrecord.window.closeAllPanels();
        obj = class1;
_L3:
        mActivities.remove(ibinder);
        StrictMode.decrementExpectedActivityCount(((Class) (obj)));
        return activityclientrecord;
_L1:
        if(true) goto _L3; else goto _L2
_L2:
    }

    private Activity performLaunchActivity(ActivityClientRecord activityclientrecord, Intent intent)
    {
        Object obj;
        ComponentName componentname;
        ContextImpl contextimpl;
        Object obj1;
        obj = activityclientrecord.activityInfo;
        if(activityclientrecord.packageInfo == null)
            activityclientrecord.packageInfo = getPackageInfo(((ActivityInfo) (obj)).applicationInfo, activityclientrecord.compatInfo, 1);
        componentname = activityclientrecord.intent.getComponent();
        obj = componentname;
        if(componentname == null)
        {
            obj = activityclientrecord.intent.resolveActivity(mInitialApplication.getPackageManager());
            activityclientrecord.intent.setComponent(((ComponentName) (obj)));
        }
        componentname = ((ComponentName) (obj));
        if(activityclientrecord.activityInfo.targetActivity != null)
            componentname = new ComponentName(activityclientrecord.activityInfo.packageName, activityclientrecord.activityInfo.targetActivity);
        contextimpl = createBaseContextForActivity(activityclientrecord);
        obj1 = null;
        obj = obj1;
        Object obj2 = contextimpl.getClassLoader();
        obj = obj1;
        Activity activity = mInstrumentation.newActivity(((ClassLoader) (obj2)), componentname.getClassName(), activityclientrecord.intent);
        obj = activity;
        StrictMode.incrementExpectedActivityCount(activity.getClass());
        obj = activity;
        activityclientrecord.intent.setExtrasClassLoader(((ClassLoader) (obj2)));
        obj = activity;
        activityclientrecord.intent.prepareToEnterProcess();
        obj1 = activity;
        obj = activity;
        if(activityclientrecord.state == null)
            break MISSING_BLOCK_LABEL_212;
        obj = activity;
        activityclientrecord.state.setClassLoader(((ClassLoader) (obj2)));
        obj1 = activity;
_L2:
        Application application = activityclientrecord.packageInfo.makeApplication(false, mInstrumentation);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_863;
        Configuration configuration;
        obj2 = activityclientrecord.activityInfo.loadLabel(contextimpl.getPackageManager());
        configuration = JVM INSTR new #592 <Class Configuration>;
        configuration.Configuration(mCompatConfiguration);
        if(activityclientrecord.overrideConfig != null)
            configuration.updateFrom(activityclientrecord.overrideConfig);
        activity = null;
        obj = activity;
        if(activityclientrecord.mPendingRemoveWindow == null)
            break MISSING_BLOCK_LABEL_314;
        obj = activity;
        if(activityclientrecord.mPreserveWindow)
        {
            obj = activityclientrecord.mPendingRemoveWindow;
            activityclientrecord.mPendingRemoveWindow = null;
            activityclientrecord.mPendingRemoveWindowManager = null;
        }
        contextimpl.setOuterContext(((Context) (obj1)));
        ((Activity) (obj1)).attach(contextimpl, this, getInstrumentation(), activityclientrecord.token, activityclientrecord.ident, application, activityclientrecord.intent, activityclientrecord.activityInfo, ((CharSequence) (obj2)), activityclientrecord.parent, activityclientrecord.embeddedID, activityclientrecord.lastNonConfigurationInstances, configuration, activityclientrecord.referrer, activityclientrecord.voiceInteractor, ((Window) (obj)), activityclientrecord.configCallback);
        if(intent == null)
            break MISSING_BLOCK_LABEL_390;
        obj1.mIntent = intent;
        int i;
        activityclientrecord.lastNonConfigurationInstances = null;
        checkAndBlockForNetworkAccess();
        obj1.mStartedActivity = false;
        i = activityclientrecord.activityInfo.getThemeResource();
        if(i == 0)
            break MISSING_BLOCK_LABEL_426;
        ((Activity) (obj1)).setTheme(i);
        obj1.mCalled = false;
        if(!activityclientrecord.isPersistable())
            break MISSING_BLOCK_LABEL_577;
        mInstrumentation.callActivityOnCreate(((Activity) (obj1)), activityclientrecord.state, activityclientrecord.persistentState);
_L3:
        if(!((Activity) (obj1)).mCalled)
        {
            intent = JVM INSTR new #2453 <Class SuperNotCalledException>;
            obj = JVM INSTR new #724 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            intent.SuperNotCalledException(((StringBuilder) (obj)).append("Activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(" did not call through to super.onCreate()").toString());
            throw intent;
        }
        break MISSING_BLOCK_LABEL_650;
        Exception exception;
        exception;
        obj1 = obj;
        if(mInstrumentation.onException(obj, exception)) goto _L2; else goto _L1
_L1:
        throw new RuntimeException((new StringBuilder()).append("Unable to instantiate activity ").append(componentname).append(": ").append(exception.toString()).toString(), exception);
        mInstrumentation.callActivityOnCreate(((Activity) (obj1)), activityclientrecord.state);
          goto _L3
        activityclientrecord.activity = ((Activity) (obj1));
        activityclientrecord.stopped = true;
        if(!activityclientrecord.activity.mFinished)
        {
            ((Activity) (obj1)).performStart();
            activityclientrecord.stopped = false;
        }
        SuperNotCalledException supernotcalledexception;
        if(!activityclientrecord.activity.mFinished)
            if(activityclientrecord.isPersistable())
            {
                if(activityclientrecord.state != null || activityclientrecord.persistentState != null)
                    mInstrumentation.callActivityOnRestoreInstanceState(((Activity) (obj1)), activityclientrecord.state, activityclientrecord.persistentState);
            } else
            if(activityclientrecord.state != null)
                mInstrumentation.callActivityOnRestoreInstanceState(((Activity) (obj1)), activityclientrecord.state);
_L7:
        if(activityclientrecord.activity.mFinished)
            break MISSING_BLOCK_LABEL_863;
        obj1.mCalled = false;
        if(!activityclientrecord.isPersistable()) goto _L5; else goto _L4
_L4:
        mInstrumentation.callActivityOnPostCreate(((Activity) (obj1)), activityclientrecord.state, activityclientrecord.persistentState);
_L6:
        if(!((Activity) (obj1)).mCalled)
        {
            supernotcalledexception = JVM INSTR new #2453 <Class SuperNotCalledException>;
            intent = JVM INSTR new #724 <Class StringBuilder>;
            intent.StringBuilder();
            supernotcalledexception.SuperNotCalledException(intent.append("Activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(" did not call through to super.onPostCreate()").toString());
            throw supernotcalledexception;
        }
        break MISSING_BLOCK_LABEL_863;
_L5:
        mInstrumentation.callActivityOnPostCreate(((Activity) (obj1)), activityclientrecord.state);
          goto _L6
        try
        {
            activityclientrecord.paused = true;
            mActivities.put(activityclientrecord.token, activityclientrecord);
        }
        // Misplaced declaration of an exception variable
        catch(ActivityClientRecord activityclientrecord)
        {
            throw activityclientrecord;
        }
        // Misplaced declaration of an exception variable
        catch(ActivityClientRecord activityclientrecord)
        {
            if(!mInstrumentation.onException(obj1, activityclientrecord))
                throw new RuntimeException((new StringBuilder()).append("Unable to start activity ").append(componentname).append(": ").append(activityclientrecord.toString()).toString(), activityclientrecord);
        }
        return ((Activity) (obj1));
          goto _L7
    }

    private void performPauseActivityIfNeeded(ActivityClientRecord activityclientrecord, String s)
    {
        if(activityclientrecord.paused)
            return;
        try
        {
            activityclientrecord.activity.mCalled = false;
            mInstrumentation.callActivityOnPause(activityclientrecord.activity);
            EventLog.writeEvent(30021, new Object[] {
                Integer.valueOf(UserHandle.myUserId()), activityclientrecord.activity.getComponentName().getClassName(), s
            });
            if(!activityclientrecord.activity.mCalled)
            {
                SuperNotCalledException supernotcalledexception = JVM INSTR new #2453 <Class SuperNotCalledException>;
                s = JVM INSTR new #724 <Class StringBuilder>;
                s.StringBuilder();
                supernotcalledexception.SuperNotCalledException(s.append("Activity ").append(safeToComponentShortString(activityclientrecord.intent)).append(" did not call through to super.onPause()").toString());
                throw supernotcalledexception;
            }
        }
        // Misplaced declaration of an exception variable
        catch(ActivityClientRecord activityclientrecord)
        {
            throw activityclientrecord;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            if(!mInstrumentation.onException(activityclientrecord.activity, s))
                throw new RuntimeException((new StringBuilder()).append("Unable to pause activity ").append(safeToComponentShortString(activityclientrecord.intent)).append(": ").append(s.toString()).toString(), s);
        }
        activityclientrecord.paused = true;
    }

    private void performStopActivityInner(ActivityClientRecord activityclientrecord, StopInfo stopinfo, boolean flag, boolean flag1, String s)
    {
        if(activityclientrecord != null)
        {
            if(!flag && activityclientrecord.stopped)
            {
                if(activityclientrecord.activity.mFinished)
                    return;
                RuntimeException runtimeexception = new RuntimeException((new StringBuilder()).append("Performing stop of activity that is already stopped: ").append(activityclientrecord.intent.getComponent().toShortString()).toString());
                Slog.e("ActivityThread", runtimeexception.getMessage(), runtimeexception);
                Slog.e("ActivityThread", activityclientrecord.getStateString());
            }
            performPauseActivityIfNeeded(activityclientrecord, s);
            if(stopinfo != null)
                try
                {
                    stopinfo.description = activityclientrecord.activity.onCreateDescription();
                }
                // Misplaced declaration of an exception variable
                catch(StopInfo stopinfo)
                {
                    if(!mInstrumentation.onException(activityclientrecord.activity, stopinfo))
                        throw new RuntimeException((new StringBuilder()).append("Unable to save state of activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(": ").append(stopinfo.toString()).toString(), stopinfo);
                }
            if(!activityclientrecord.activity.mFinished && flag1 && activityclientrecord.state == null)
                callCallActivityOnSaveInstanceState(activityclientrecord);
            if(!flag)
            {
                try
                {
                    activityclientrecord.activity.performStop(false);
                }
                // Misplaced declaration of an exception variable
                catch(StopInfo stopinfo)
                {
                    if(!mInstrumentation.onException(activityclientrecord.activity, stopinfo))
                        throw new RuntimeException((new StringBuilder()).append("Unable to stop activity ").append(activityclientrecord.intent.getComponent().toShortString()).append(": ").append(stopinfo.toString()).toString(), stopinfo);
                }
                activityclientrecord.stopped = true;
                EventLog.writeEvent(30049, new Object[] {
                    Integer.valueOf(UserHandle.myUserId()), activityclientrecord.activity.getComponentName().getClassName(), s
                });
            }
        }
    }

    static transient void printRow(PrintWriter printwriter, String s, Object aobj[])
    {
        printwriter.println(String.format(s, aobj));
    }

    private void reportSizeConfigurations(ActivityClientRecord activityclientrecord)
    {
        Configuration aconfiguration[] = activityclientrecord.activity.getResources().getSizeConfigurations();
        if(aconfiguration == null)
            return;
        SparseIntArray sparseintarray = new SparseIntArray();
        SparseIntArray sparseintarray1 = new SparseIntArray();
        SparseIntArray sparseintarray2 = new SparseIntArray();
        for(int i = aconfiguration.length - 1; i >= 0; i--)
        {
            Configuration configuration = aconfiguration[i];
            if(configuration.screenHeightDp != 0)
                sparseintarray1.put(configuration.screenHeightDp, 0);
            if(configuration.screenWidthDp != 0)
                sparseintarray.put(configuration.screenWidthDp, 0);
            if(configuration.smallestScreenWidthDp != 0)
                sparseintarray2.put(configuration.smallestScreenWidthDp, 0);
        }

        try
        {
            ActivityManager.getService().reportSizeConfigurations(activityclientrecord.token, sparseintarray.copyKeys(), sparseintarray1.copyKeys(), sparseintarray2.copyKeys());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ActivityClientRecord activityclientrecord)
        {
            throw activityclientrecord.rethrowFromSystemServer();
        }
    }

    private void requestRelaunchAllActivities()
    {
        Iterator iterator = mActivities.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(!((ActivityClientRecord)entry.getValue()).activity.mFinished)
                try
                {
                    ActivityManager.getService().requestActivityRelaunch((IBinder)entry.getKey());
                }
                catch(RemoteException remoteexception)
                {
                    throw remoteexception.rethrowFromSystemServer();
                }
        } while(true);
    }

    private static String safeToComponentShortString(Intent intent)
    {
        intent = intent.getComponent();
        if(intent == null)
            intent = "[Unknown]";
        else
            intent = intent.toShortString();
        return intent;
    }

    private void sendMessage(int i, Object obj)
    {
        sendMessage(i, obj, 0, 0, false);
    }

    private void sendMessage(int i, Object obj, int j)
    {
        sendMessage(i, obj, j, 0, false);
    }

    private void sendMessage(int i, Object obj, int j, int k)
    {
        sendMessage(i, obj, j, k, false);
    }

    private void sendMessage(int i, Object obj, int j, int k, int l)
    {
        Message message = Message.obtain();
        message.what = i;
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.argi1 = j;
        someargs.argi2 = k;
        someargs.argi3 = l;
        message.obj = someargs;
        mH.sendMessage(message);
    }

    private void sendMessage(int i, Object obj, int j, int k, boolean flag)
    {
        Message message = Message.obtain();
        message.what = i;
        message.obj = obj;
        message.arg1 = j;
        message.arg2 = k;
        if(flag)
            message.setAsynchronous(true);
        mH.sendMessage(message);
    }

    private void setupGraphicsSupport(Context context)
    {
        Trace.traceBegin(64L, "setupGraphicsSupport");
        if(!"android".equals(context.getPackageName()))
        {
            File file = context.getCacheDir();
            if(file != null)
                System.setProperty("java.io.tmpdir", file.getAbsolutePath());
            else
                Log.v("ActivityThread", "Unable to initialize \"java.io.tmpdir\" property due to missing cache directory");
            file = context.createDeviceProtectedStorageContext().getCodeCacheDir();
            if(file != null)
                try
                {
                    int i = Process.myUid();
                    if(getPackageManager().getPackagesForUid(i) != null)
                    {
                        ThreadedRenderer.setupDiskCache(file);
                        RenderScriptCacheDir.setupDiskCache(file);
                    }
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    Trace.traceEnd(64L);
                    throw context.rethrowFromSystemServer();
                }
            else
                Log.w("ActivityThread", "Unable to use shader/script cache: missing code-cache directory");
        }
        GraphicsEnvironment.chooseDriver(context);
        Trace.traceEnd(64L);
    }

    public static ActivityThread systemMain()
    {
        ActivityThread activitythread;
        if(!ActivityManager.isHighEndGfx())
            ThreadedRenderer.disable(true);
        else
            ThreadedRenderer.enableForegroundTrimming();
        activitythread = new ActivityThread();
        activitythread.attach(true);
        return activitythread;
    }

    private void updateDefaultDensity()
    {
        int i = mCurDefaultDisplayDpi;
        if(!mDensityCompatMode && i != 0 && i != DisplayMetrics.DENSITY_DEVICE)
        {
            DisplayMetrics.DENSITY_DEVICE = i;
            Bitmap.setDefaultDensity(i);
        }
    }

    private void updateLocaleListFromAppContext(Context context, LocaleList localelist)
    {
        context = context.getResources().getConfiguration().getLocales().get(0);
        int i = localelist.size();
        for(int j = 0; j < i; j++)
            if(context.equals(localelist.get(j)))
            {
                LocaleList.setDefault(localelist, j);
                return;
            }

        LocaleList.setDefault(new LocaleList(context, localelist));
    }

    private void updateVisibility(ActivityClientRecord activityclientrecord, boolean flag)
    {
        View view = activityclientrecord.activity.mDecor;
        if(view == null) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        if(!activityclientrecord.activity.mVisibleFromServer)
        {
            activityclientrecord.activity.mVisibleFromServer = true;
            mNumVisibleActivities = mNumVisibleActivities + 1;
            if(activityclientrecord.activity.mVisibleFromClient)
                activityclientrecord.activity.makeVisible();
        }
        if(activityclientrecord.newConfig != null)
        {
            performConfigurationChangedForActivity(activityclientrecord, activityclientrecord.newConfig);
            activityclientrecord.newConfig = null;
        }
_L2:
        return;
_L4:
        if(activityclientrecord.activity.mVisibleFromServer)
        {
            activityclientrecord.activity.mVisibleFromServer = false;
            mNumVisibleActivities = mNumVisibleActivities - 1;
            view.setVisibility(4);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public final IContentProvider acquireExistingProvider(Context context, ProviderKey providerkey, boolean flag)
    {
        context = mProviderMap;
        context;
        JVM INSTR monitorenter ;
        Object obj = (ProviderClientRecord)mProviderMap.get(providerkey);
        if(obj != null)
            break MISSING_BLOCK_LABEL_29;
        context;
        JVM INSTR monitorexit ;
        return null;
        Object obj1;
        obj1 = ((ProviderClientRecord) (obj)).mProvider;
        obj = ((IContentProvider) (obj1)).asBinder();
        if(((IBinder) (obj)).isBinderAlive())
            break MISSING_BLOCK_LABEL_119;
        obj1 = JVM INSTR new #724 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.i("ActivityThread", ((StringBuilder) (obj1)).append("Acquiring provider ").append(providerkey.authority).append(" for user ").append(providerkey.userId).append(": existing object's process dead").toString());
        handleUnstableProviderDiedLocked(((IBinder) (obj)), true);
        context;
        JVM INSTR monitorexit ;
        return null;
        providerkey = (ProviderRefCount)mProviderRefCountMap.get(obj);
        if(providerkey == null)
            break MISSING_BLOCK_LABEL_142;
        incProviderRefLocked(providerkey, flag);
        context;
        JVM INSTR monitorexit ;
        return ((IContentProvider) (obj1));
        providerkey;
        throw providerkey;
    }

    public final IContentProvider acquireExistingProvider(Context context, String s, int i, boolean flag)
    {
        return acquireExistingProvider(context, new ProviderKey(s, i), flag);
    }

    public final IContentProvider acquireProvider(Context context, String s, int i, boolean flag)
    {
        ProviderKey providerkey;
        Object obj;
        providerkey = new ProviderKey(s, i);
        obj = acquireExistingProvider(context, providerkey, flag);
        if(obj != null)
            return ((IContentProvider) (obj));
        obj = null;
        if(Process.myTid() != mainThreadId) goto _L2; else goto _L1
_L1:
        try
        {
            obj = ActivityManager.getService().getContentProvider(getApplicationThread(), s, i, flag);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
_L5:
        Object obj1;
        ProviderAcquiringCount provideracquiringcount;
        if(obj == null)
        {
            Slog.e("ActivityThread", (new StringBuilder()).append("Failed to find provider info for ").append(s).toString());
            return null;
        } else
        {
            return installProvider(context, ((ContentProviderHolder) (obj)), ((ContentProviderHolder) (obj)).info, true, ((ContentProviderHolder) (obj)).noReleaseNeeded, flag).provider;
        }
_L2:
        obj1 = mProviderAcquiringCountMap;
        obj1;
        JVM INSTR monitorenter ;
        provideracquiringcount = (ProviderAcquiringCount)mProviderAcquiringCountMap.get(providerkey);
        if(provideracquiringcount != null) goto _L4; else goto _L3
_L3:
        provideracquiringcount = JVM INSTR new #63  <Class ActivityThread$ProviderAcquiringCount>;
        provideracquiringcount.ProviderAcquiringCount(1);
        mProviderAcquiringCountMap.put(providerkey, provideracquiringcount);
_L6:
        obj1;
        JVM INSTR monitorexit ;
        provideracquiringcount;
        JVM INSTR monitorenter ;
        obj1 = ActivityManager.getService().getContentProvider(getApplicationThread(), s, i, flag);
        obj = obj1;
_L7:
        provideracquiringcount;
        JVM INSTR monitorexit ;
        obj1 = mProviderAcquiringCountMap;
        obj1;
        JVM INSTR monitorenter ;
        i = provideracquiringcount.acquiringCount - 1;
        provideracquiringcount.acquiringCount = i;
        if(i != 0)
            break MISSING_BLOCK_LABEL_219;
        mProviderAcquiringCountMap.remove(providerkey);
        obj1;
        JVM INSTR monitorexit ;
          goto _L5
_L4:
        provideracquiringcount.acquiringCount = provideracquiringcount.acquiringCount + 1;
        StringBuilder stringbuilder = JVM INSTR new #724 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("ActivityThread", stringbuilder.append(s).append(" acquiringCount ").append(provideracquiringcount.acquiringCount).toString());
          goto _L6
        context;
        throw context;
        context;
        throw context;
        context;
        throw context;
        RemoteException remoteexception;
        remoteexception;
          goto _L7
    }

    final void appNotRespondingViaProvider(IBinder ibinder)
    {
        ArrayMap arraymap = mProviderMap;
        arraymap;
        JVM INSTR monitorenter ;
        ibinder = (ProviderRefCount)mProviderRefCountMap.get(ibinder);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_38;
        ActivityManager.getService().appNotRespondingViaProvider(((ProviderRefCount) (ibinder)).holder.connection);
        arraymap;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        throw ibinder.rethrowFromSystemServer();
        ibinder;
        arraymap;
        JVM INSTR monitorexit ;
        throw ibinder;
    }

    final Configuration applyCompatConfiguration(int i)
    {
        Configuration configuration = mConfiguration;
        if(mCompatConfiguration == null)
            mCompatConfiguration = new Configuration();
        mCompatConfiguration.setTo(mConfiguration);
        if(mResourcesManager.applyCompatConfigurationLocked(i, mCompatConfiguration))
            configuration = mCompatConfiguration;
        return configuration;
    }

    Configuration applyConfigCompatMainThread(int i, Configuration configuration, CompatibilityInfo compatibilityinfo)
    {
        if(configuration == null)
            return null;
        Configuration configuration1 = configuration;
        if(!compatibilityinfo.supportsScreen())
        {
            mMainThreadConfig.setTo(configuration);
            configuration1 = mMainThreadConfig;
            compatibilityinfo.applyToConfiguration(i, configuration1);
        }
        return configuration1;
    }

    public final void applyConfigurationToResources(Configuration configuration)
    {
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        mResourcesManager.applyConfigurationToResourcesLocked(configuration, null);
        resourcesmanager;
        JVM INSTR monitorexit ;
        return;
        configuration;
        throw configuration;
    }

    ArrayList collectComponentCallbacks(boolean flag, Configuration configuration)
    {
        ArrayList arraylist = new ArrayList();
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        int i = mAllApplications.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        arraylist.add((ComponentCallbacks2)mAllApplications.get(j));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        i = mActivities.size();
        j = 0;
_L4:
        if(j >= i)
            break MISSING_BLOCK_LABEL_182;
        ActivityClientRecord activityclientrecord;
        Activity activity;
        activityclientrecord = (ActivityClientRecord)mActivities.valueAt(j);
        activity = activityclientrecord.activity;
        if(activity == null)
            break MISSING_BLOCK_LABEL_155;
        Configuration configuration1;
        configuration1 = applyConfigCompatMainThread(mCurDefaultDisplayDpi, configuration, activityclientrecord.packageInfo.getCompatibilityInfo());
        if(activityclientrecord.activity.mFinished)
            break; /* Loop/switch isn't completed */
        if(flag)
            break MISSING_BLOCK_LABEL_148;
        if(!(activityclientrecord.paused ^ true))
            break; /* Loop/switch isn't completed */
        arraylist.add(activity);
_L6:
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        if(configuration1 == null) goto _L6; else goto _L5
_L5:
        activityclientrecord.newConfig = configuration1;
          goto _L6
        configuration;
        throw configuration;
        i = mServices.size();
        j = 0;
_L8:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        arraylist.add((ComponentCallbacks2)mServices.valueAt(j));
        j++;
        if(true) goto _L8; else goto _L7
_L7:
        configuration = mProviderMap;
        configuration;
        JVM INSTR monitorenter ;
        i = mLocalProviders.size();
        j = 0;
_L10:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        arraylist.add(((ProviderClientRecord)mLocalProviders.valueAt(j)).mLocalProvider);
        j++;
        if(true) goto _L10; else goto _L9
_L9:
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    final void completeRemoveProvider(ProviderRefCount providerrefcount)
    {
        ArrayMap arraymap = mProviderMap;
        arraymap;
        JVM INSTR monitorenter ;
        boolean flag = providerrefcount.removePending;
        if(flag)
            break MISSING_BLOCK_LABEL_19;
        arraymap;
        JVM INSTR monitorexit ;
        return;
        IBinder ibinder;
        int i;
        providerrefcount.removePending = false;
        ibinder = providerrefcount.holder.provider.asBinder();
        if((ProviderRefCount)mProviderRefCountMap.get(ibinder) == providerrefcount)
            mProviderRefCountMap.remove(ibinder);
        i = mProviderMap.size() - 1;
_L3:
        if(i < 0) goto _L2; else goto _L1
_L1:
        if(((ProviderClientRecord)mProviderMap.valueAt(i)).mProvider.asBinder() == ibinder)
            mProviderMap.removeAt(i);
        i--;
          goto _L3
_L2:
        arraymap;
        JVM INSTR monitorexit ;
        ActivityManager.getService().removeContentProvider(providerrefcount.holder.connection, false);
_L5:
        return;
        providerrefcount;
        throw providerrefcount;
        providerrefcount;
        if(true) goto _L5; else goto _L4
_L4:
    }

    void doGcIfNeeded()
    {
        mGcIdlerScheduled = false;
        long l = SystemClock.uptimeMillis();
        if(BinderInternal.getLastGcTime() + 5000L < l)
            BinderInternal.forceGc("bg");
    }

    void ensureJitEnabled()
    {
        if(!mJitEnabled)
        {
            mJitEnabled = true;
            VMRuntime.getRuntime().startJitCompilation();
        }
    }

    final void finishInstrumentation(int i, Bundle bundle)
    {
        IActivityManager iactivitymanager = ActivityManager.getService();
        if(mProfiler.profileFile != null && mProfiler.handlingProfiling && mProfiler.profileFd == null)
            Debug.stopMethodTracing();
        try
        {
            iactivitymanager.finishInstrumentation(mAppThread, i, bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
    }

    public final Activity getActivity(IBinder ibinder)
    {
        return ((ActivityClientRecord)mActivities.get(ibinder)).activity;
    }

    public Application getApplication()
    {
        return mInitialApplication;
    }

    public ApplicationThread getApplicationThread()
    {
        return mAppThread;
    }

    final Handler getHandler()
    {
        return mH;
    }

    public Instrumentation getInstrumentation()
    {
        return mInstrumentation;
    }

    public int getIntCoreSetting(String s, int i)
    {
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        if(mCoreSettings == null)
            break MISSING_BLOCK_LABEL_28;
        i = mCoreSettings.getInt(s, i);
        return i;
        resourcesmanager;
        JVM INSTR monitorexit ;
        return i;
        s;
        throw s;
    }

    public Looper getLooper()
    {
        return mLooper;
    }

    public final LoadedApk getPackageInfo(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo, int i)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        if((i & 1) != 0)
            flag = true;
        else
            flag = false;
        if(flag && applicationinfo.uid != 0 && applicationinfo.uid != 1000)
        {
            if(mBoundApplication != null)
                flag1 = UserHandle.isSameApp(applicationinfo.uid, mBoundApplication.appInfo.uid) ^ true;
            else
                flag1 = true;
        } else
        {
            flag1 = false;
        }
        if(flag && (0x40000000 & i) != 0)
            flag2 = true;
        else
            flag2 = false;
        if((i & 3) == 1 && flag1)
        {
            compatibilityinfo = (new StringBuilder()).append("Requesting code from ").append(applicationinfo.packageName).append(" (with uid ").append(applicationinfo.uid).append(")").toString();
            applicationinfo = compatibilityinfo;
            if(mBoundApplication != null)
                applicationinfo = (new StringBuilder()).append(compatibilityinfo).append(" to be run in process ").append(mBoundApplication.processName).append(" (with uid ").append(mBoundApplication.appInfo.uid).append(")").toString();
            throw new SecurityException(applicationinfo);
        } else
        {
            return getPackageInfo(applicationinfo, compatibilityinfo, null, flag1, flag, flag2);
        }
    }

    public final LoadedApk getPackageInfo(String s, CompatibilityInfo compatibilityinfo, int i)
    {
        return getPackageInfo(s, compatibilityinfo, i, UserHandle.myUserId());
    }

    public final LoadedApk getPackageInfo(String s, CompatibilityInfo compatibilityinfo, int i, int j)
    {
        boolean flag;
        ResourcesManager resourcesmanager;
        Object obj;
        if(UserHandle.myUserId() != j)
            flag = true;
        else
            flag = false;
        resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        if(!flag) goto _L2; else goto _L1
_L1:
        obj = null;
_L12:
        if(obj == null) goto _L4; else goto _L3
_L3:
        obj = (LoadedApk)((WeakReference) (obj)).get();
_L10:
        if(obj == null) goto _L6; else goto _L5
_L5:
        if(((LoadedApk) (obj)).mResources != null && !((LoadedApk) (obj)).mResources.getAssets().isUpToDate()) goto _L6; else goto _L7
_L7:
        if(!((LoadedApk) (obj)).isSecurityViolation() || (i & 2) != 0) goto _L9; else goto _L8
_L8:
        obj = JVM INSTR new #3341 <Class SecurityException>;
        compatibilityinfo = JVM INSTR new #724 <Class StringBuilder>;
        compatibilityinfo.StringBuilder();
        ((SecurityException) (obj)).SecurityException(compatibilityinfo.append("Requesting code from ").append(s).append(" to be run in process ").append(mBoundApplication.processName).append("/").append(mBoundApplication.appInfo.uid).toString());
        throw obj;
        s;
        resourcesmanager;
        JVM INSTR monitorexit ;
        throw s;
_L2:
        if((i & 1) == 0)
            break MISSING_BLOCK_LABEL_188;
        obj = (WeakReference)mPackages.get(s);
        continue; /* Loop/switch isn't completed */
        obj = (WeakReference)mResourcePackages.get(s);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = null;
          goto _L10
_L9:
        resourcesmanager;
        JVM INSTR monitorexit ;
        return ((LoadedApk) (obj));
_L6:
        resourcesmanager;
        JVM INSTR monitorexit ;
        try
        {
            s = getPackageManager().getApplicationInfo(s, 0x10000400, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s != null)
            return getPackageInfo(((ApplicationInfo) (s)), compatibilityinfo, i);
        return null;
        if(true) goto _L12; else goto _L11
_L11:
    }

    public final LoadedApk getPackageInfoNoCheck(ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo)
    {
        return getPackageInfo(applicationinfo, compatibilityinfo, null, false, true, false);
    }

    public String getProcessName()
    {
        return mBoundApplication.processName;
    }

    public String getProfileFilePath()
    {
        return mProfiler.profileFile;
    }

    public ContextImpl getSystemContext()
    {
        this;
        JVM INSTR monitorenter ;
        ContextImpl contextimpl;
        if(mSystemContext == null)
            mSystemContext = ContextImpl.createSystemContext(this);
        contextimpl = mSystemContext;
        this;
        JVM INSTR monitorexit ;
        return contextimpl;
        Exception exception;
        exception;
        throw exception;
    }

    public ContextImpl getSystemUiContext()
    {
        this;
        JVM INSTR monitorenter ;
        ContextImpl contextimpl;
        if(mSystemUiContext == null)
            mSystemUiContext = ContextImpl.createSystemUiContext(getSystemContext());
        contextimpl = mSystemUiContext;
        this;
        JVM INSTR monitorexit ;
        return contextimpl;
        Exception exception;
        exception;
        throw exception;
    }

    Resources getTopLevelResources(String s, String as[], String as1[], String as2[], int i, LoadedApk loadedapk)
    {
        return mResourcesManager.getResources(null, s, as, as1, as2, i, null, loadedapk.getCompatibilityInfo(), loadedapk.getClassLoader());
    }

    void handleActivityConfigurationChanged(ActivityConfigChangeData activityconfigchangedata, int i)
    {
        Object obj = (ActivityClientRecord)mActivities.get(activityconfigchangedata.activityToken);
        if(obj == null || ((ActivityClientRecord) (obj)).activity == null)
            return;
        boolean flag;
        if(i != -1)
        {
            if(i != ((ActivityClientRecord) (obj)).activity.getDisplay().getDisplayId())
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        obj.overrideConfig = activityconfigchangedata.overrideConfig;
        if(((ActivityClientRecord) (obj)).activity.mDecor != null)
            activityconfigchangedata = ((ActivityClientRecord) (obj)).activity.mDecor.getViewRootImpl();
        else
            activityconfigchangedata = null;
        if(flag)
        {
            obj = performConfigurationChangedForActivity(((ActivityClientRecord) (obj)), mCompatConfiguration, i, true);
            if(activityconfigchangedata != null)
                activityconfigchangedata.onMovedToDisplay(i, ((Configuration) (obj)));
        } else
        {
            performConfigurationChangedForActivity(((ActivityClientRecord) (obj)), mCompatConfiguration);
        }
        if(activityconfigchangedata != null)
            activityconfigchangedata.updateConfiguration(i);
        mSomeActivitiesChanged = true;
    }

    void handleApplicationInfoChanged(ApplicationInfo applicationinfo)
    {
        Object obj = mResourcesManager;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (WeakReference)mPackages.get(applicationinfo.packageName);
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        obj1 = (LoadedApk)((WeakReference) (obj1)).get();
_L5:
        Object obj2 = (WeakReference)mResourcePackages.get(applicationinfo.packageName);
        if(obj2 == null) goto _L4; else goto _L3
_L3:
        obj2 = (LoadedApk)((WeakReference) (obj2)).get();
_L6:
        obj;
        JVM INSTR monitorexit ;
        if(obj1 != null)
        {
            obj = new ArrayList();
            LoadedApk.makePaths(this, ((LoadedApk) (obj1)).getApplicationInfo(), ((List) (obj)));
            ((LoadedApk) (obj1)).updateApplicationInfo(applicationinfo, ((List) (obj)));
        }
        if(obj2 != null)
        {
            obj1 = new ArrayList();
            LoadedApk.makePaths(this, ((LoadedApk) (obj2)).getApplicationInfo(), ((List) (obj1)));
            ((LoadedApk) (obj2)).updateApplicationInfo(applicationinfo, ((List) (obj1)));
        }
        obj1 = mResourcesManager;
        obj1;
        JVM INSTR monitorenter ;
        mResourcesManager.applyNewResourceDirsLocked(applicationinfo.sourceDir, applicationinfo.resourceDirs);
        obj1;
        JVM INSTR monitorexit ;
        ApplicationPackageManager.configurationChanged();
        applicationinfo = new Configuration();
        int i;
        if(mConfiguration != null)
            i = mConfiguration.assetsSeq;
        else
            i = 0;
        applicationinfo.assetsSeq = i + 1;
        handleConfigurationChanged(applicationinfo, null);
        requestRelaunchAllActivities();
        return;
_L2:
        obj1 = null;
          goto _L5
_L4:
        obj2 = null;
          goto _L6
        applicationinfo;
        throw applicationinfo;
        applicationinfo;
        throw applicationinfo;
          goto _L5
    }

    final void handleConfigurationChanged(Configuration configuration, CompatibilityInfo compatibilityinfo)
    {
        ContextImpl contextimpl;
        boolean flag;
        ResourcesManager resourcesmanager;
        Configuration configuration1;
        contextimpl = getSystemContext();
        if(configuration != null && mConfiguration != null)
        {
            if(mConfiguration.diffPublicOnly(configuration) == 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        configuration1 = configuration;
        if(mPendingConfiguration == null)
            break MISSING_BLOCK_LABEL_87;
        configuration1 = configuration;
        if(!mPendingConfiguration.isOtherSeqNewer(configuration))
        {
            configuration1 = mPendingConfiguration;
            mCurDefaultDisplayDpi = configuration1.densityDpi;
            updateDefaultDensity();
        }
        mPendingConfiguration = null;
        if(configuration1 != null)
            break MISSING_BLOCK_LABEL_108;
        resourcesmanager;
        JVM INSTR monitorexit ;
        return;
        boolean flag1;
        mResourcesManager.applyConfigurationToResourcesLocked(configuration1, compatibilityinfo);
        updateLocaleListFromAppContext(mInitialApplication.getApplicationContext(), mResourcesManager.getConfiguration().getLocales());
        if(mConfiguration == null)
        {
            configuration = JVM INSTR new #592 <Class Configuration>;
            configuration.Configuration();
            mConfiguration = configuration;
        }
        flag1 = mConfiguration.isOtherSeqNewer(configuration1);
        if(flag1 || compatibilityinfo != null)
            break MISSING_BLOCK_LABEL_184;
        resourcesmanager;
        JVM INSTR monitorexit ;
        return;
        int i;
        i = mConfiguration.updateFrom(configuration1);
        configuration = applyCompatConfiguration(mCurDefaultDisplayDpi);
        compatibilityinfo = contextimpl.getTheme();
        if((compatibilityinfo.getChangingConfigurations() & i) != 0)
            compatibilityinfo.rebase();
        compatibilityinfo = getSystemUiContext().getTheme();
        if((compatibilityinfo.getChangingConfigurations() & i) != 0)
            compatibilityinfo.rebase();
        resourcesmanager;
        JVM INSTR monitorexit ;
        compatibilityinfo = collectComponentCallbacks(false, configuration);
        freeTextLayoutCachesIfNeeded(i);
        if(compatibilityinfo != null)
        {
            int k = compatibilityinfo.size();
            int j = 0;
            do
            {
                if(j >= k)
                    break;
                Object obj = (ComponentCallbacks2)compatibilityinfo.get(j);
                if(obj instanceof Activity)
                {
                    obj = (Activity)obj;
                    performConfigurationChangedForActivity((ActivityClientRecord)mActivities.get(((Activity) (obj)).getActivityToken()), configuration);
                } else
                if(!flag)
                    performConfigurationChanged(((ComponentCallbacks2) (obj)), configuration);
                j++;
            } while(true);
        }
        break MISSING_BLOCK_LABEL_353;
        configuration;
        throw configuration;
    }

    final void handleDispatchPackageBroadcast(int i, String as[])
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        flag = false;
        flag1 = false;
        flag2 = false;
        flag3 = flag2;
        i;
        JVM INSTR tableswitch 0 3: default 44
    //                   0 56
    //                   1 48
    //                   2 56
    //                   3 247;
           goto _L1 _L2 _L3 _L2 _L4
_L1:
        flag3 = flag2;
_L3:
        ApplicationPackageManager.handlePackageBroadcast(i, as, flag3);
        return;
_L2:
        Object obj;
        int k;
        Object obj1;
        boolean flag4;
        if(i == 0)
            flag4 = true;
        else
            flag4 = false;
        flag3 = flag2;
        if(as == null)
            continue; /* Loop/switch isn't completed */
        obj = mResourcesManager;
        obj;
        JVM INSTR monitorenter ;
        k = as.length - 1;
        flag2 = flag;
_L14:
        flag3 = flag2;
        obj1 = obj;
        if(k < 0) goto _L6; else goto _L5
_L5:
        flag3 = flag2;
        if(flag2) goto _L8; else goto _L7
_L7:
        obj1 = (WeakReference)mPackages.get(as[k]);
        if(obj1 == null) goto _L10; else goto _L9
_L9:
        if(((WeakReference) (obj1)).get() == null) goto _L10; else goto _L11
_L11:
        flag3 = true;
_L8:
        if(!flag4) goto _L13; else goto _L12
_L12:
        mPackages.remove(as[k]);
        mResourcePackages.remove(as[k]);
_L13:
        k--;
        flag2 = flag3;
          goto _L14
_L10:
        obj1 = (WeakReference)mResourcePackages.get(as[k]);
        flag3 = flag2;
        if(obj1 == null) goto _L8; else goto _L15
_L15:
        obj1 = ((WeakReference) (obj1)).get();
        flag3 = flag2;
        if(obj1 != null)
            flag3 = true;
          goto _L8
_L6:
        obj1;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        as;
        throw as;
_L4:
        flag3 = flag2;
        if(as == null)
            continue; /* Loop/switch isn't completed */
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        int j = as.length - 1;
        flag2 = flag1;
_L24:
        flag3 = flag2;
        obj1 = resourcesmanager;
        if(j < 0) goto _L6; else goto _L16
_L16:
        obj1 = (WeakReference)mPackages.get(as[j]);
        if(obj1 == null) goto _L18; else goto _L17
_L17:
        obj1 = (LoadedApk)((WeakReference) (obj1)).get();
_L25:
        if(obj1 == null) goto _L20; else goto _L19
_L19:
        flag2 = true;
        obj = obj1;
_L28:
        if(obj == null) goto _L22; else goto _L21
_L21:
        String s = as[j];
        obj1 = sPackageManager.getApplicationInfo(s, 1024, UserHandle.myUserId());
        if(mActivities.size() > 0)
        {
            Iterator iterator = mActivities.values().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                ActivityClientRecord activityclientrecord = (ActivityClientRecord)iterator.next();
                if(activityclientrecord.activityInfo.applicationInfo.packageName.equals(s))
                {
                    activityclientrecord.activityInfo.applicationInfo = ((ApplicationInfo) (obj1));
                    activityclientrecord.packageInfo = ((LoadedApk) (obj));
                }
            } while(true);
        }
          goto _L23
_L22:
        j--;
          goto _L24
_L18:
        obj1 = null;
          goto _L25
_L20:
        obj1 = (WeakReference)mResourcePackages.get(as[j]);
        if(obj1 == null) goto _L27; else goto _L26
_L26:
        obj1 = (LoadedApk)((WeakReference) (obj1)).get();
_L29:
        obj = obj1;
        if(obj1 != null)
        {
            flag2 = true;
            obj = obj1;
        }
          goto _L28
_L27:
        obj1 = null;
          goto _L29
_L23:
        try
        {
            ((LoadedApk) (obj)).updateApplicationInfo(((ApplicationInfo) (obj1)), sPackageManager.getPreviousCodePaths(s));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1) { }
          goto _L22
        as;
        throw as;
        if(true) goto _L3; else goto _L30
_L30:
    }

    public void handleInstallProvider(ProviderInfo providerinfo)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy = StrictMode.allowThreadDiskWrites();
        installContentProviders(mInitialApplication, Arrays.asList(new ProviderInfo[] {
            providerinfo
        }));
        StrictMode.setThreadPolicy(threadpolicy);
        return;
        providerinfo;
        StrictMode.setThreadPolicy(threadpolicy);
        throw providerinfo;
    }

    final void handleLowMemory()
    {
        ArrayList arraylist = collectComponentCallbacks(true, null);
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            ((ComponentCallbacks2)arraylist.get(j)).onLowMemory();

        if(Process.myUid() != 1000)
            EventLog.writeEvent(0x124fb, SQLiteDatabase.releaseMemory());
        Canvas.freeCaches();
        Canvas.freeTextLayoutCaches();
        BinderInternal.forceGc("mem");
    }

    final void handleProfilerControl(boolean flag, ProfilerInfo profilerinfo, int i)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        mProfiler.setProfiler(profilerinfo);
        mProfiler.startProfiling();
        profilerinfo.closeFd();
_L4:
        return;
        Object obj;
        obj;
        StringBuilder stringbuilder = JVM INSTR new #724 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.w("ActivityThread", stringbuilder.append("Profiling failed on path ").append(profilerinfo.profileFile).append(" -- can the process access this path?").toString());
        profilerinfo.closeFd();
        continue; /* Loop/switch isn't completed */
        stringbuilder;
        profilerinfo.closeFd();
        throw stringbuilder;
_L2:
        mProfiler.stopProfiling();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void handleRequestAssistContextExtras(RequestAssistContextExtras requestassistcontextextras)
    {
        boolean flag;
        AssistContent assistcontent;
        if(requestassistcontextextras.requestType == 2)
            flag = true;
        else
            flag = false;
        if(mLastSessionId != requestassistcontextextras.sessionId)
        {
            mLastSessionId = requestassistcontextextras.sessionId;
            for(int i = mLastAssistStructures.size() - 1; i >= 0; i--)
            {
                AssistStructure assiststructure = (AssistStructure)((WeakReference)mLastAssistStructures.get(i)).get();
                if(assiststructure != null)
                    assiststructure.clearSendChannel();
                mLastAssistStructures.remove(i);
            }

        }
        Bundle bundle = new Bundle();
        AssistStructure assiststructure1 = null;
        Object obj;
        long l;
        ActivityClientRecord activityclientrecord;
        Uri uri;
        Object obj1;
        if(flag)
            assistcontent = null;
        else
            assistcontent = new AssistContent();
        l = SystemClock.uptimeMillis();
        activityclientrecord = (ActivityClientRecord)mActivities.get(requestassistcontextextras.activityToken);
        uri = null;
        obj1 = null;
        obj = assiststructure1;
        if(activityclientrecord == null) goto _L2; else goto _L1
_L1:
        if(!flag)
        {
            activityclientrecord.activity.getApplication().dispatchOnProvideAssistData(activityclientrecord.activity, bundle);
            activityclientrecord.activity.onProvideAssistData(bundle);
            obj1 = activityclientrecord.activity.onProvideReferrer();
        }
        if(requestassistcontextextras.requestType == 1) goto _L4; else goto _L3
_L3:
        obj = assiststructure1;
        uri = ((Uri) (obj1));
        if(!flag) goto _L2; else goto _L4
_L4:
        assiststructure1 = new AssistStructure(activityclientrecord.activity, flag, requestassistcontextextras.flags);
        obj = activityclientrecord.activity.getIntent();
        boolean flag1;
        if(activityclientrecord.window != null)
        {
            if((activityclientrecord.window.getAttributes().flags & 0x2000) == 0)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        if(obj == null || !flag1) goto _L6; else goto _L5
_L5:
        if(!flag)
        {
            obj = new Intent(((Intent) (obj)));
            ((Intent) (obj)).setFlags(((Intent) (obj)).getFlags() & 0xffffffbd);
            ((Intent) (obj)).removeUnsafeExtras();
            assistcontent.setDefaultIntent(((Intent) (obj)));
        }
_L8:
        obj = assiststructure1;
        uri = ((Uri) (obj1));
        if(!flag)
        {
            activityclientrecord.activity.onProvideAssistContent(assistcontent);
            uri = ((Uri) (obj1));
            obj = assiststructure1;
        }
_L2:
        obj1 = obj;
        if(obj == null)
            obj1 = new AssistStructure();
        ((AssistStructure) (obj1)).setAcquisitionStartTime(l);
        ((AssistStructure) (obj1)).setAcquisitionEndTime(SystemClock.uptimeMillis());
        mLastAssistStructures.add(new WeakReference(obj1));
        obj = ActivityManager.getService();
        try
        {
            ((IActivityManager) (obj)).reportAssistContextExtras(requestassistcontextextras.requestToken, bundle, ((AssistStructure) (obj1)), assistcontent, uri);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(RequestAssistContextExtras requestassistcontextextras)
        {
            throw requestassistcontextextras.rethrowFromSystemServer();
        }
_L6:
        if(!flag)
            assistcontent.setDefaultIntent(new Intent());
        if(true) goto _L8; else goto _L7
_L7:
    }

    final void handleResumeActivity(IBinder ibinder, boolean flag, boolean flag1, boolean flag2, int i, String s)
    {
        if(!checkAndUpdateLifecycleSeq(i, (ActivityClientRecord)mActivities.get(ibinder), "resumeActivity"))
            return;
        unscheduleGcIdler();
        mSomeActivitiesChanged = true;
        s = performResumeActivity(ibinder, flag, s);
        if(s != null)
        {
            Activity activity = ((ActivityClientRecord) (s)).activity;
            if(flag1)
                i = 256;
            else
                i = 0;
            flag1 = activity.mStartedActivity ^ true;
            flag = flag1;
            if(!flag1)
                try
                {
                    flag = ActivityManager.getService().willActivityBeVisible(activity.getActivityToken());
                }
                // Misplaced declaration of an exception variable
                catch(IBinder ibinder)
                {
                    throw ibinder.rethrowFromSystemServer();
                }
            if(((ActivityClientRecord) (s)).window == null && activity.mFinished ^ true && flag)
            {
                s.window = ((ActivityClientRecord) (s)).activity.getWindow();
                Object obj = ((ActivityClientRecord) (s)).window.getDecorView();
                ((View) (obj)).setVisibility(4);
                WindowManager windowmanager = activity.getWindowManager();
                android.view.WindowManager.LayoutParams layoutparams = ((ActivityClientRecord) (s)).window.getAttributes();
                activity.mDecor = ((View) (obj));
                layoutparams.type = 1;
                layoutparams.softInputMode = layoutparams.softInputMode | i;
                if(((ActivityClientRecord) (s)).mPreserveWindow)
                {
                    activity.mWindowAdded = true;
                    s.mPreserveWindow = false;
                    ViewRootImpl viewrootimpl = ((View) (obj)).getViewRootImpl();
                    if(viewrootimpl != null)
                        viewrootimpl.notifyChildRebuilt();
                }
                if(activity.mVisibleFromClient)
                    if(!activity.mWindowAdded)
                    {
                        activity.mWindowAdded = true;
                        windowmanager.addView(((View) (obj)), layoutparams);
                    } else
                    {
                        activity.onWindowAttributesChanged(layoutparams);
                    }
            } else
            if(!flag)
                s.hideForNow = true;
            cleanUpPendingRemoveWindows(s, false);
            if(!((ActivityClientRecord) (s)).activity.mFinished && flag && ((ActivityClientRecord) (s)).activity.mDecor != null && ((ActivityClientRecord) (s)).hideForNow ^ true)
            {
                if(((ActivityClientRecord) (s)).newConfig != null)
                {
                    performConfigurationChangedForActivity(s, ((ActivityClientRecord) (s)).newConfig);
                    s.newConfig = null;
                }
                obj = ((ActivityClientRecord) (s)).window.getAttributes();
                if((((android.view.WindowManager.LayoutParams) (obj)).softInputMode & 0x100) != i)
                {
                    obj.softInputMode = ((android.view.WindowManager.LayoutParams) (obj)).softInputMode & 0xfffffeff | i;
                    if(((ActivityClientRecord) (s)).activity.mVisibleFromClient)
                        activity.getWindowManager().updateViewLayout(((ActivityClientRecord) (s)).window.getDecorView(), ((android.view.ViewGroup.LayoutParams) (obj)));
                }
                ((ActivityClientRecord) (s)).activity.mVisibleFromServer = true;
                mNumVisibleActivities = mNumVisibleActivities + 1;
                if(((ActivityClientRecord) (s)).activity.mVisibleFromClient)
                    ((ActivityClientRecord) (s)).activity.makeVisible();
            }
            if(!((ActivityClientRecord) (s)).onlyLocalRequest)
            {
                s.nextIdle = mNewActivities;
                mNewActivities = s;
                Looper.myQueue().addIdleHandler(new Idler(null));
            }
            s.onlyLocalRequest = false;
            if(flag2)
                try
                {
                    ActivityManager.getService().activityResumed(ibinder);
                }
                // Misplaced declaration of an exception variable
                catch(IBinder ibinder)
                {
                    throw ibinder.rethrowFromSystemServer();
                }
            FilePinner.handlePinAppFile(activity.getApplication(), ((ActivityClientRecord) (s)).packageInfo, ((ActivityClientRecord) (s)).activity.getResources());
        } else
        {
            try
            {
                ActivityManager.getService().finishActivity(ibinder, 0, null, 0);
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder)
            {
                throw ibinder.rethrowFromSystemServer();
            }
        }
    }

    public void handleTranslucentConversionComplete(IBinder ibinder, boolean flag)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(ibinder != null)
            ((ActivityClientRecord) (ibinder)).activity.onTranslucentConversionComplete(flag);
    }

    final void handleTrimMemory(int i)
    {
        if(WhetstoneAppManager.handleTrimMemory(i))
            return;
        ArrayList arraylist = collectComponentCallbacks(true, null);
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
            ((ComponentCallbacks2)arraylist.get(k)).onTrimMemory(i);

        WindowManagerGlobal.getInstance().trimMemory(i);
    }

    final void handleUnstableProviderDied(IBinder ibinder, boolean flag)
    {
        ArrayMap arraymap = mProviderMap;
        arraymap;
        JVM INSTR monitorenter ;
        handleUnstableProviderDiedLocked(ibinder, flag);
        arraymap;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        throw ibinder;
    }

    final void handleUnstableProviderDiedLocked(IBinder ibinder, boolean flag)
    {
        ProviderRefCount providerrefcount;
        providerrefcount = (ProviderRefCount)mProviderRefCountMap.get(ibinder);
        if(providerrefcount == null)
            break MISSING_BLOCK_LABEL_142;
        mProviderRefCountMap.remove(ibinder);
        for(int i = mProviderMap.size() - 1; i >= 0; i--)
        {
            ProviderClientRecord providerclientrecord = (ProviderClientRecord)mProviderMap.valueAt(i);
            if(providerclientrecord != null && providerclientrecord.mProvider.asBinder() == ibinder)
            {
                Slog.i("ActivityThread", (new StringBuilder()).append("Removing dead content provider:").append(providerclientrecord.mProvider.toString()).toString());
                mProviderMap.removeAt(i);
            }
        }

        if(!flag)
            break MISSING_BLOCK_LABEL_142;
        ActivityManager.getService().unstableProviderDied(providerrefcount.holder.connection);
_L2:
        return;
        ibinder;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void installSystemApplicationInfo(ApplicationInfo applicationinfo, ClassLoader classloader)
    {
        this;
        JVM INSTR monitorenter ;
        getSystemContext().installSystemApplicationInfo(applicationinfo, classloader);
        getSystemUiContext().installSystemApplicationInfo(applicationinfo, classloader);
        applicationinfo = JVM INSTR new #60  <Class ActivityThread$Profiler>;
        applicationinfo.Profiler();
        mProfiler = applicationinfo;
        this;
        JVM INSTR monitorexit ;
        return;
        applicationinfo;
        throw applicationinfo;
    }

    public final void installSystemProviders(List list)
    {
        if(list != null)
            installContentProviders(mInitialApplication, list);
    }

    public boolean isProfiling()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mProfiler != null)
        {
            flag1 = flag;
            if(mProfiler.profileFile != null)
            {
                flag1 = flag;
                if(mProfiler.profileFd == null)
                    flag1 = true;
            }
        }
        return flag1;
    }

    void lambda$_2D_android_app_ActivityThread_284258(Configuration configuration)
    {
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        if(mResourcesManager.applyConfigurationToResourcesLocked(configuration, null))
        {
            updateLocaleListFromAppContext(mInitialApplication.getApplicationContext(), mResourcesManager.getConfiguration().getLocales());
            if(mPendingConfiguration == null || mPendingConfiguration.isOtherSeqNewer(configuration))
            {
                mPendingConfiguration = configuration;
                sendMessage(118, configuration);
            }
        }
        resourcesmanager;
        JVM INSTR monitorexit ;
        return;
        configuration;
        throw configuration;
    }

    public void onNewActivityOptions(IBinder ibinder, ActivityOptions activityoptions)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(ibinder != null)
            ((ActivityClientRecord) (ibinder)).activity.onNewActivityOptions(activityoptions);
    }

    public final LoadedApk peekPackageInfo(String s, boolean flag)
    {
        LoadedApk loadedapk = null;
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        if(!flag) goto _L2; else goto _L1
_L1:
        s = (WeakReference)mPackages.get(s);
_L4:
        if(s == null)
            break MISSING_BLOCK_LABEL_39;
        loadedapk = (LoadedApk)s.get();
        resourcesmanager;
        JVM INSTR monitorexit ;
        return loadedapk;
_L2:
        s = (WeakReference)mResourcePackages.get(s);
        if(true) goto _L4; else goto _L3
_L3:
        s;
        throw s;
    }

    public final ActivityClientRecord performDestroyActivity(IBinder ibinder, boolean flag)
    {
        return performDestroyActivity(ibinder, flag, 0, false);
    }

    void performNewIntents(IBinder ibinder, List list, boolean flag)
    {
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mActivities.get(ibinder);
        if(activityclientrecord == null)
            return;
        boolean flag1 = activityclientrecord.paused ^ true;
        if(flag1)
        {
            activityclientrecord.activity.mTemporaryPause = true;
            mInstrumentation.callActivityOnPause(activityclientrecord.activity);
        }
        checkAndBlockForNetworkAccess();
        deliverNewIntents(activityclientrecord, list);
        if(flag1)
        {
            activityclientrecord.activity.performResume();
            activityclientrecord.activity.mTemporaryPause = false;
        }
        if(activityclientrecord.paused && flag)
        {
            performResumeActivity(ibinder, false, "performNewIntents");
            performPauseActivityIfNeeded(activityclientrecord, "performNewIntents");
        }
    }

    final Bundle performPauseActivity(ActivityClientRecord activityclientrecord, boolean flag, boolean flag1, String s)
    {
        if(activityclientrecord.paused)
        {
            if(activityclientrecord.activity.mFinished)
                return null;
            RuntimeException runtimeexception = new RuntimeException((new StringBuilder()).append("Performing pause of activity that is not resumed: ").append(activityclientrecord.intent.getComponent().toShortString()).toString());
            Slog.e("ActivityThread", runtimeexception.getMessage(), runtimeexception);
        }
        if(flag)
            activityclientrecord.activity.mFinished = true;
        if(!activityclientrecord.activity.mFinished && flag1)
            callCallActivityOnSaveInstanceState(activityclientrecord);
        performPauseActivityIfNeeded(activityclientrecord, s);
        s = mOnPauseListeners;
        s;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mOnPauseListeners.remove(activityclientrecord.activity);
        s;
        JVM INSTR monitorexit ;
        int i;
        int j;
        if(arraylist != null)
            i = arraylist.size();
        else
            i = 0;
        for(j = 0; j < i; j++)
            ((OnActivityPausedListener)arraylist.get(j)).onPaused(activityclientrecord.activity);

        break MISSING_BLOCK_LABEL_195;
        activityclientrecord;
        throw activityclientrecord;
        if(!activityclientrecord.activity.mFinished && flag1)
            activityclientrecord = activityclientrecord.state;
        else
            activityclientrecord = null;
        return activityclientrecord;
    }

    final Bundle performPauseActivity(IBinder ibinder, boolean flag, boolean flag1, String s)
    {
        Object obj = null;
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mActivities.get(ibinder);
        ibinder = obj;
        if(activityclientrecord != null)
            ibinder = performPauseActivity(activityclientrecord, flag, flag1, s);
        return ibinder;
    }

    final void performRestartActivity(IBinder ibinder)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(((ActivityClientRecord) (ibinder)).stopped)
        {
            ((ActivityClientRecord) (ibinder)).activity.performRestart();
            ibinder.stopped = false;
        }
    }

    public final ActivityClientRecord performResumeActivity(IBinder ibinder, boolean flag, String s)
    {
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(ibinder == null || !(((ActivityClientRecord) (ibinder)).activity.mFinished ^ true))
            break MISSING_BLOCK_LABEL_254;
        if(flag)
        {
            ibinder.hideForNow = false;
            ((ActivityClientRecord) (ibinder)).activity.mStartedActivity = false;
        }
        ((ActivityClientRecord) (ibinder)).activity.onStateNotSaved();
        ((ActivityClientRecord) (ibinder)).activity.mFragments.noteStateNotSaved();
        checkAndBlockForNetworkAccess();
        if(((ActivityClientRecord) (ibinder)).pendingIntents != null)
        {
            deliverNewIntents(ibinder, ((ActivityClientRecord) (ibinder)).pendingIntents);
            ibinder.pendingIntents = null;
        }
        if(((ActivityClientRecord) (ibinder)).pendingResults != null)
        {
            deliverResults(ibinder, ((ActivityClientRecord) (ibinder)).pendingResults);
            ibinder.pendingResults = null;
        }
        ((ActivityClientRecord) (ibinder)).activity.performResume();
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        int i = mRelaunchingActivities.size() - 1;
_L3:
        if(i < 0) goto _L2; else goto _L1
_L1:
        ActivityClientRecord activityclientrecord = (ActivityClientRecord)mRelaunchingActivities.get(i);
        if(activityclientrecord.token == ((ActivityClientRecord) (ibinder)).token && activityclientrecord.onlyLocalRequest && activityclientrecord.startsNotResumed)
            activityclientrecord.startsNotResumed = false;
        i--;
          goto _L3
_L2:
        resourcesmanager;
        JVM INSTR monitorexit ;
        EventLog.writeEvent(30022, new Object[] {
            Integer.valueOf(UserHandle.myUserId()), ((ActivityClientRecord) (ibinder)).activity.getComponentName().getClassName(), s
        });
        ibinder.paused = false;
        ibinder.stopped = false;
        ibinder.state = null;
        ibinder.persistentState = null;
_L5:
        return ibinder;
        s;
        resourcesmanager;
        JVM INSTR monitorexit ;
        throw s;
        s;
        if(!mInstrumentation.onException(((ActivityClientRecord) (ibinder)).activity, s))
            throw new RuntimeException((new StringBuilder()).append("Unable to resume activity ").append(((ActivityClientRecord) (ibinder)).intent.getComponent().toShortString()).append(": ").append(s.toString()).toString(), s);
        if(true) goto _L5; else goto _L4
_L4:
    }

    final void performStopActivity(IBinder ibinder, boolean flag, String s)
    {
        performStopActivityInner((ActivityClientRecord)mActivities.get(ibinder), null, false, flag, s);
    }

    final void performUserLeavingActivity(ActivityClientRecord activityclientrecord)
    {
        mInstrumentation.callActivityOnUserLeaving(activityclientrecord.activity);
    }

    public void registerOnActivityPausedListener(Activity activity, OnActivityPausedListener onactivitypausedlistener)
    {
        ArrayMap arraymap = mOnPauseListeners;
        arraymap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mOnPauseListeners.get(activity);
        ArrayList arraylist1;
        arraylist1 = arraylist;
        if(arraylist != null)
            break MISSING_BLOCK_LABEL_50;
        arraylist1 = JVM INSTR new #512 <Class ArrayList>;
        arraylist1.ArrayList();
        mOnPauseListeners.put(activity, arraylist1);
        arraylist1.add(onactivitypausedlistener);
        arraymap;
        JVM INSTR monitorexit ;
        return;
        activity;
        throw activity;
    }

    public final boolean releaseProvider(IContentProvider icontentprovider, boolean flag)
    {
        int i;
        Object obj;
        i = 0;
        if(icontentprovider == null)
            return false;
        obj = icontentprovider.asBinder();
        icontentprovider = mProviderMap;
        icontentprovider;
        JVM INSTR monitorenter ;
        obj = (ProviderRefCount)mProviderRefCountMap.get(obj);
        if(obj != null)
            break MISSING_BLOCK_LABEL_46;
        icontentprovider;
        JVM INSTR monitorexit ;
        return false;
        int j = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        int k = ((ProviderRefCount) (obj)).stableCount;
        if(k != 0)
            break MISSING_BLOCK_LABEL_69;
        icontentprovider;
        JVM INSTR monitorexit ;
        return false;
        obj.stableCount = ((ProviderRefCount) (obj)).stableCount - 1;
        if(((ProviderRefCount) (obj)).stableCount != 0)
            break MISSING_BLOCK_LABEL_138;
        j = ((ProviderRefCount) (obj)).unstableCount;
        IActivityManager iactivitymanager;
        IBinder ibinder;
        if(j == 0)
            j = 1;
        else
            j = 0;
        iactivitymanager = ActivityManager.getService();
        ibinder = ((ProviderRefCount) (obj)).holder.connection;
        if(j != 0)
            i = 1;
        Exception exception;
        try
        {
            iactivitymanager.refContentProvider(ibinder, -1, i);
        }
        catch(RemoteException remoteexception1) { }
        if(j == 0)
            break MISSING_BLOCK_LABEL_181;
        if(((ProviderRefCount) (obj)).removePending)
            break MISSING_BLOCK_LABEL_284;
        obj.removePending = true;
        obj = mH.obtainMessage(131, obj);
        mH.sendMessage(((Message) (obj)));
_L3:
        icontentprovider;
        JVM INSTR monitorexit ;
        return true;
_L2:
        i = ((ProviderRefCount) (obj)).unstableCount;
        if(i != 0)
            break MISSING_BLOCK_LABEL_205;
        icontentprovider;
        JVM INSTR monitorexit ;
        return false;
        obj.unstableCount = ((ProviderRefCount) (obj)).unstableCount - 1;
        if(((ProviderRefCount) (obj)).unstableCount != 0)
            continue; /* Loop/switch isn't completed */
        j = ((ProviderRefCount) (obj)).stableCount;
        boolean flag1;
        RemoteException remoteexception;
        if(j == 0)
            flag1 = true;
        else
            flag1 = false;
        j = ((flag1) ? 1 : 0);
        if(flag1)
            continue; /* Loop/switch isn't completed */
        try
        {
            ActivityManager.getService().refContentProvider(((ProviderRefCount) (obj)).holder.connection, 0, -1);
        }
        // Misplaced declaration of an exception variable
        catch(RemoteException remoteexception)
        {
            j = ((flag1) ? 1 : 0);
            continue; /* Loop/switch isn't completed */
        }
        j = ((flag1) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
        StringBuilder stringbuilder = JVM INSTR new #724 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.w("ActivityThread", stringbuilder.append("Duplicate remove pending of provider ").append(((ProviderRefCount) (obj)).holder.info.name).toString());
          goto _L3
        exception;
        throw exception;
        if(true) goto _L5; else goto _L4
_L5:
        break MISSING_BLOCK_LABEL_138;
_L4:
    }

    public final void requestRelaunchActivity(IBinder ibinder, List list, List list1, int i, boolean flag, Configuration configuration, Configuration configuration1, 
            boolean flag1, boolean flag2)
    {
        ResourcesManager resourcesmanager = mResourcesManager;
        resourcesmanager;
        JVM INSTR monitorenter ;
        int j = 0;
_L11:
        ActivityClientRecord activityclientrecord;
        if(j >= mRelaunchingActivities.size())
            break MISSING_BLOCK_LABEL_335;
        activityclientrecord = (ActivityClientRecord)mRelaunchingActivities.get(j);
        if(activityclientrecord.token != ibinder) goto _L2; else goto _L1
_L1:
        if(list == null) goto _L4; else goto _L3
_L3:
        if(activityclientrecord.pendingResults == null) goto _L6; else goto _L5
_L5:
        activityclientrecord.pendingResults.addAll(list);
_L4:
        if(list1 == null) goto _L8; else goto _L7
_L7:
        if(activityclientrecord.pendingIntents == null) goto _L10; else goto _L9
_L9:
        activityclientrecord.pendingIntents.addAll(list1);
_L8:
        boolean flag3 = activityclientrecord.onlyLocalRequest;
        if(flag3 || !flag1)
            break MISSING_BLOCK_LABEL_121;
        ActivityManager.getService().activityRelaunched(ibinder);
        for(; activityclientrecord != null; activityclientrecord = null)
            break MISSING_BLOCK_LABEL_224;

        activityclientrecord = JVM INSTR new #10  <Class ActivityThread$ActivityClientRecord>;
        activityclientrecord.ActivityClientRecord();
        activityclientrecord.token = ibinder;
        activityclientrecord.pendingResults = list;
        activityclientrecord.pendingIntents = list1;
        activityclientrecord.mPreserveWindow = flag2;
        if(flag1)
            break MISSING_BLOCK_LABEL_206;
        ibinder = (ActivityClientRecord)mActivities.get(ibinder);
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_200;
        activityclientrecord.startsNotResumed = ((ActivityClientRecord) (ibinder)).paused;
        activityclientrecord.overrideConfig = ((ActivityClientRecord) (ibinder)).overrideConfig;
        activityclientrecord.onlyLocalRequest = true;
        mRelaunchingActivities.add(activityclientrecord);
        sendMessage(126, activityclientrecord);
        if(!flag1)
            break MISSING_BLOCK_LABEL_242;
        activityclientrecord.startsNotResumed = flag;
        activityclientrecord.onlyLocalRequest = false;
        if(configuration == null)
            break MISSING_BLOCK_LABEL_254;
        activityclientrecord.createdConfig = configuration;
        if(configuration1 == null)
            break MISSING_BLOCK_LABEL_266;
        activityclientrecord.overrideConfig = configuration1;
        activityclientrecord.pendingConfigChanges = activityclientrecord.pendingConfigChanges | i;
        activityclientrecord.relaunchSeq = getLifecycleSeq();
        resourcesmanager;
        JVM INSTR monitorexit ;
        return;
_L6:
        activityclientrecord.pendingResults = list;
          goto _L4
        ibinder;
_L12:
        resourcesmanager;
        JVM INSTR monitorexit ;
        throw ibinder;
_L10:
        activityclientrecord.pendingIntents = list1;
          goto _L8
        ibinder;
        throw ibinder.rethrowFromSystemServer();
_L2:
        j++;
          goto _L11
        ibinder;
          goto _L12
    }

    public final ActivityInfo resolveActivityInfo(Intent intent)
    {
        ActivityInfo activityinfo = intent.resolveActivityInfo(mInitialApplication.getPackageManager(), 1024);
        if(activityinfo == null)
            Instrumentation.checkStartActivityResult(-92, intent);
        return activityinfo;
    }

    final void scheduleContextCleanup(ContextImpl contextimpl, String s, String s1)
    {
        ContextCleanupInfo contextcleanupinfo = new ContextCleanupInfo();
        contextcleanupinfo.context = contextimpl;
        contextcleanupinfo.who = s;
        contextcleanupinfo.what = s1;
        sendMessage(119, contextcleanupinfo);
    }

    void scheduleGcIdler()
    {
        if(!mGcIdlerScheduled)
        {
            mGcIdlerScheduled = true;
            Looper.myQueue().addIdleHandler(mGcIdler);
        }
        mH.removeMessages(120);
    }

    public final void sendActivityResult(IBinder ibinder, String s, int i, int j, Intent intent)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new ResultInfo(s, i, j, intent));
        mAppThread.scheduleSendResult(ibinder, arraylist);
    }

    public final Activity startActivityNow(Activity activity, String s, Intent intent, ActivityInfo activityinfo, IBinder ibinder, Bundle bundle, Activity.NonConfigurationInstances nonconfigurationinstances)
    {
        ActivityClientRecord activityclientrecord = new ActivityClientRecord();
        activityclientrecord.token = ibinder;
        activityclientrecord.ident = 0;
        activityclientrecord.intent = intent;
        activityclientrecord.state = bundle;
        activityclientrecord.parent = activity;
        activityclientrecord.embeddedID = s;
        activityclientrecord.activityInfo = activityinfo;
        activityclientrecord.lastNonConfigurationInstances = nonconfigurationinstances;
        return performLaunchActivity(activityclientrecord, null);
    }

    public void stopProfiling()
    {
        if(mProfiler != null)
            mProfiler.stopProfiling();
    }

    public void unregisterOnActivityPausedListener(Activity activity, OnActivityPausedListener onactivitypausedlistener)
    {
        ArrayMap arraymap = mOnPauseListeners;
        arraymap;
        JVM INSTR monitorenter ;
        activity = (ArrayList)mOnPauseListeners.get(activity);
        if(activity == null)
            break MISSING_BLOCK_LABEL_29;
        activity.remove(onactivitypausedlistener);
        arraymap;
        JVM INSTR monitorexit ;
        return;
        activity;
        throw activity;
    }

    void unscheduleGcIdler()
    {
        if(mGcIdlerScheduled)
        {
            mGcIdlerScheduled = false;
            Looper.myQueue().removeIdleHandler(mGcIdler);
        }
        mH.removeMessages(120);
    }

    private static final int ACTIVITY_THREAD_CHECKIN_VERSION = 4;
    private static final boolean DEBUG_BACKUP = false;
    public static final boolean DEBUG_BROADCAST = false;
    public static final boolean DEBUG_CONFIGURATION = false;
    private static final boolean DEBUG_MEMORY_TRIM = false;
    static final boolean DEBUG_MESSAGES = false;
    private static final boolean DEBUG_ORDER = false;
    private static final boolean DEBUG_PROVIDER = false;
    private static final boolean DEBUG_RESULTS = false;
    private static final boolean DEBUG_SERVICE = false;
    private static final int DONT_REPORT = 2;
    private static final String HEAP_COLUMN = "%13s %8s %8s %8s %8s %8s %8s %8s";
    private static final String HEAP_FULL_COLUMN = "%13s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s";
    public static final long INVALID_PROC_STATE_SEQ = -1L;
    private static final int LOG_AM_ON_PAUSE_CALLED = 30021;
    private static final int LOG_AM_ON_RESUME_CALLED = 30022;
    private static final int LOG_AM_ON_STOP_CALLED = 30049;
    private static final long MIN_TIME_BETWEEN_GCS = 5000L;
    private static final String ONE_COUNT_COLUMN = "%21s %8d";
    private static final String ONE_COUNT_COLUMN_HEADER = "%21s %8s";
    private static final boolean REPORT_TO_ACTIVITY = true;
    public static final int SERVICE_DONE_EXECUTING_ANON = 0;
    public static final int SERVICE_DONE_EXECUTING_START = 1;
    public static final int SERVICE_DONE_EXECUTING_STOP = 2;
    private static final int SQLITE_MEM_RELEASED_EVENT_LOG_TAG = 0x124fb;
    public static final String TAG = "ActivityThread";
    private static final android.graphics.Bitmap.Config THUMBNAIL_FORMAT;
    private static final String TWO_COUNT_COLUMNS = "%21s %8d %21s %8d";
    private static final int USER_LEAVING = 1;
    static final boolean localLOGV = false;
    private static volatile ActivityThread sCurrentActivityThread;
    private static final ThreadLocal sCurrentBroadcastIntent = new ThreadLocal();
    static volatile Handler sMainThreadHandler;
    static volatile IPackageManager sPackageManager;
    private static boolean sWaitingToUse;
    private final int enable_uxe = SystemProperties.getInt("iop.enable_uxe", 0);
    final ArrayMap mActivities = new ArrayMap();
    final ArrayList mAllApplications = new ArrayList();
    final ApplicationThread mAppThread = new ApplicationThread(null);
    private Bitmap mAvailThumbnailBitmap;
    final ArrayMap mBackupAgents = new ArrayMap();
    AppBindData mBoundApplication;
    Configuration mCompatConfiguration;
    Configuration mConfiguration;
    Bundle mCoreSettings;
    int mCurDefaultDisplayDpi;
    boolean mDensityCompatMode;
    final GcIdler mGcIdler = new GcIdler();
    boolean mGcIdlerScheduled;
    final H mH = new H(null);
    Application mInitialApplication;
    Instrumentation mInstrumentation;
    String mInstrumentationAppDir;
    String mInstrumentationLibDir;
    String mInstrumentationPackageName;
    String mInstrumentationSplitAppDirs[];
    String mInstrumentedAppDir;
    String mInstrumentedLibDir;
    String mInstrumentedSplitAppDirs[];
    boolean mJitEnabled;
    ArrayList mLastAssistStructures;
    private int mLastSessionId;
    int mLifecycleSeq;
    final ArrayMap mLocalProviders = new ArrayMap();
    final ArrayMap mLocalProvidersByName = new ArrayMap();
    final Looper mLooper = Looper.myLooper();
    private Configuration mMainThreadConfig;
    private long mNetworkBlockSeq;
    private final Object mNetworkPolicyLock = new Object();
    ActivityClientRecord mNewActivities;
    int mNumVisibleActivities;
    final ArrayMap mOnPauseListeners = new ArrayMap();
    final ArrayMap mPackages = new ArrayMap();
    Configuration mPendingConfiguration;
    Profiler mProfiler;
    final ArrayMap mProviderAcquiringCountMap = new ArrayMap();
    final ArrayMap mProviderMap = new ArrayMap();
    final ArrayMap mProviderRefCountMap = new ArrayMap();
    final ArrayList mRelaunchingActivities = new ArrayList();
    final ArrayMap mResourcePackages = new ArrayMap();
    private final ResourcesManager mResourcesManager = ResourcesManager.getInstance();
    final ArrayMap mServices = new ArrayMap();
    boolean mSomeActivitiesChanged;
    private ContextImpl mSystemContext;
    boolean mSystemThread;
    private ContextImpl mSystemUiContext;
    private Canvas mThumbnailCanvas;
    private int mThumbnailHeight;
    private int mThumbnailWidth;
    boolean mUpdatingSystemConfig;
    private final int mainThreadId = Process.myPid();

    static 
    {
        THUMBNAIL_FORMAT = android.graphics.Bitmap.Config.RGB_565;
    }
}
