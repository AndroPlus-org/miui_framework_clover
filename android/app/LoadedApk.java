// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.content.pm.split.SplitDependencyLoader;
import android.content.res.*;
import android.miui.ResourcesManager;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import android.view.DisplayAdjustments;
import com.android.internal.util.ArrayUtils;
import dalvik.system.VMRuntime;
import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

// Referenced classes of package android.app:
//            ActivityThread, ActivityManager, IActivityManager, ApplicationLoaders, 
//            DexLoadReporter, ResourcesManager, ContextImpl, Instrumentation, 
//            Application, IntentReceiverLeaked, ServiceConnectionLeaked, IServiceConnection, 
//            ActivityThreadInjector

public final class LoadedApk
{
    static final class ReceiverDispatcher
    {

        IIntentReceiver getIIntentReceiver()
        {
            return mIIntentReceiver;
        }

        BroadcastReceiver getIntentReceiver()
        {
            return mReceiver;
        }

        IntentReceiverLeaked getLocation()
        {
            return mLocation;
        }

        RuntimeException getUnregisterLocation()
        {
            return mUnregisterLocation;
        }

        public void performReceive(Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, int j)
        {
            s = new Args(intent, i, s, bundle, flag, flag1, j);
            if(intent == null)
                Log.wtf("LoadedApk", "Null intent received");
            if((intent == null || mActivityThread.post(s.getRunnable()) ^ true) && mRegistered && flag)
                s.sendFinished(ActivityManager.getService());
        }

        void setUnregisterLocation(RuntimeException runtimeexception)
        {
            mUnregisterLocation = runtimeexception;
        }

        void validate(Context context, Handler handler)
        {
            if(mContext != context)
                throw new IllegalStateException((new StringBuilder()).append("Receiver ").append(mReceiver).append(" registered with differing Context (was ").append(mContext).append(" now ").append(context).append(")").toString());
            if(mActivityThread != handler)
                throw new IllegalStateException((new StringBuilder()).append("Receiver ").append(mReceiver).append(" registered with differing handler (was ").append(mActivityThread).append(" now ").append(handler).append(")").toString());
            else
                return;
        }

        final Handler mActivityThread;
        final Context mContext;
        boolean mForgotten;
        final android.content.IIntentReceiver.Stub mIIntentReceiver;
        final Instrumentation mInstrumentation;
        final IntentReceiverLeaked mLocation;
        final BroadcastReceiver mReceiver;
        final boolean mRegistered;
        RuntimeException mUnregisterLocation;

        ReceiverDispatcher(BroadcastReceiver broadcastreceiver, Context context, Handler handler, Instrumentation instrumentation, boolean flag)
        {
            if(handler == null)
            {
                throw new NullPointerException("Handler must not be null");
            } else
            {
                mIIntentReceiver = new InnerReceiver(this, flag ^ true);
                mReceiver = broadcastreceiver;
                mContext = context;
                mActivityThread = handler;
                mInstrumentation = instrumentation;
                mRegistered = flag;
                mLocation = new IntentReceiverLeaked(null);
                mLocation.fillInStackTrace();
                return;
            }
        }
    }

    final class ReceiverDispatcher.Args extends android.content.BroadcastReceiver.PendingResult
    {

        public final Runnable getRunnable()
        {
            return new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)7, this);
        }

        void lambda$_2D_android_app_LoadedApk$ReceiverDispatcher$Args_52651()
        {
            BroadcastReceiver broadcastreceiver = mReceiver;
            boolean flag = mOrdered;
            IActivityManager iactivitymanager = ActivityManager.getService();
            Intent intent = mCurIntent;
            if(intent == null)
                Log.wtf("LoadedApk", (new StringBuilder()).append("Null intent being dispatched, mDispatched=").append(mDispatched).append(": run() previously called at ").append(Log.getStackTraceString(mPreviousRunStacktrace)).toString());
            mCurIntent = null;
            mDispatched = true;
            for(mPreviousRunStacktrace = new Throwable("Previous stacktrace"); broadcastreceiver == null || intent == null || mForgotten;)
            {
                if(mRegistered && flag)
                    sendFinished(iactivitymanager);
                return;
            }

            Trace.traceBegin(64L, "broadcastReceiveReg");
            long l = SystemClock.uptimeMillis();
            try
            {
                ClassLoader classloader = mReceiver.getClass().getClassLoader();
                intent.setExtrasClassLoader(classloader);
                intent.prepareToEnterProcess();
                setExtrasClassLoader(classloader);
                broadcastreceiver.setPendingResult(this);
                broadcastreceiver.onReceive(mContext, intent);
            }
            catch(Exception exception)
            {
                if(mRegistered && flag)
                    sendFinished(iactivitymanager);
                if(mInstrumentation == null || mInstrumentation.onException(mReceiver, exception) ^ true)
                {
                    Trace.traceEnd(64L);
                    throw new RuntimeException((new StringBuilder()).append("Error receiving broadcast ").append(intent).append(" in ").append(mReceiver).toString(), exception);
                }
            }
            if(broadcastreceiver.getPendingResult() != null)
                finish();
            ActivityThreadInjector.checkHandleMessageTime(l, 200);
            Trace.traceEnd(64L);
        }

        private Intent mCurIntent;
        private boolean mDispatched;
        private final boolean mOrdered;
        private Throwable mPreviousRunStacktrace;
        final ReceiverDispatcher this$1;

        public ReceiverDispatcher.Args(Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, 
                int j)
        {
            this$1 = ReceiverDispatcher.this;
            int k;
            if(mRegistered)
                k = 1;
            else
                k = 2;
            super(i, s, bundle, k, flag, flag1, mIIntentReceiver.asBinder(), j, intent.getFlags());
            mCurIntent = intent;
            mOrdered = flag;
        }
    }

    static final class ReceiverDispatcher.InnerReceiver extends android.content.IIntentReceiver.Stub
    {

        public void performReceive(Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, int j)
        {
            Object obj;
            if(intent == null)
            {
                Log.wtf("LoadedApk", "Null intent received");
                obj = null;
            } else
            {
                obj = (ReceiverDispatcher)mDispatcher.get();
            }
            if(obj == null) goto _L2; else goto _L1
_L1:
            ((ReceiverDispatcher) (obj)).performReceive(intent, i, s, bundle, flag, flag1, j);
_L4:
            return;
_L2:
            obj = ActivityManager.getService();
            if(bundle == null)
                break MISSING_BLOCK_LABEL_69;
            bundle.setAllowFds(false);
            ((IActivityManager) (obj)).finishReceiver(this, i, s, bundle, false, intent.getFlags());
            if(true) goto _L4; else goto _L3
_L3:
            intent;
            throw intent.rethrowFromSystemServer();
        }

        final WeakReference mDispatcher;
        final ReceiverDispatcher mStrongRef;

        ReceiverDispatcher.InnerReceiver(ReceiverDispatcher receiverdispatcher, boolean flag)
        {
            mDispatcher = new WeakReference(receiverdispatcher);
            if(!flag)
                receiverdispatcher = null;
            mStrongRef = receiverdispatcher;
        }
    }

    static final class ServiceDispatcher
    {

        public void connected(ComponentName componentname, IBinder ibinder, boolean flag)
        {
            if(mActivityThread != null)
                mActivityThread.post(new RunConnection(componentname, ibinder, 0, flag));
            else
                doConnected(componentname, ibinder, flag);
        }

        public void death(ComponentName componentname, IBinder ibinder)
        {
            if(mActivityThread != null)
                mActivityThread.post(new RunConnection(componentname, ibinder, 1, false));
            else
                doDeath(componentname, ibinder);
        }

        public void doConnected(ComponentName componentname, IBinder ibinder, boolean flag)
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag1 = mForgotten;
            if(!flag1)
                break MISSING_BLOCK_LABEL_16;
            this;
            JVM INSTR monitorexit ;
            return;
            ConnectionInfo connectioninfo = (ConnectionInfo)mActiveConnections.get(componentname);
            if(connectioninfo == null)
                break MISSING_BLOCK_LABEL_50;
            Object obj = connectioninfo.binder;
            if(obj != ibinder)
                break MISSING_BLOCK_LABEL_50;
            this;
            JVM INSTR monitorexit ;
            return;
            if(ibinder == null) goto _L2; else goto _L1
_L1:
            obj = JVM INSTR new #9   <Class LoadedApk$ServiceDispatcher$ConnectionInfo>;
            ((ConnectionInfo) (obj)).ConnectionInfo(null);
            obj.binder = ibinder;
            DeathMonitor deathmonitor = JVM INSTR new #12  <Class LoadedApk$ServiceDispatcher$DeathMonitor>;
            deathmonitor.this. DeathMonitor(componentname, ibinder);
            obj.deathMonitor = deathmonitor;
            ibinder.linkToDeath(((ConnectionInfo) (obj)).deathMonitor, 0);
            mActiveConnections.put(componentname, obj);
_L4:
            if(connectioninfo == null)
                break MISSING_BLOCK_LABEL_136;
            connectioninfo.binder.unlinkToDeath(connectioninfo.deathMonitor, 0);
            this;
            JVM INSTR monitorexit ;
            if(connectioninfo != null)
                mConnection.onServiceDisconnected(componentname);
            if(flag)
                mConnection.onBindingDied(componentname);
            if(ibinder != null)
                mConnection.onServiceConnected(componentname, ibinder);
            return;
            ibinder;
            mActiveConnections.remove(componentname);
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            mActiveConnections.remove(componentname);
            if(true) goto _L4; else goto _L3
_L3:
            componentname;
            throw componentname;
        }

        public void doDeath(ComponentName componentname, IBinder ibinder)
        {
            this;
            JVM INSTR monitorenter ;
            ConnectionInfo connectioninfo = (ConnectionInfo)mActiveConnections.get(componentname);
            if(connectioninfo == null)
                break MISSING_BLOCK_LABEL_30;
            IBinder ibinder1 = connectioninfo.binder;
            if(ibinder1 == ibinder)
                break MISSING_BLOCK_LABEL_33;
            this;
            JVM INSTR monitorexit ;
            return;
            mActiveConnections.remove(componentname);
            connectioninfo.binder.unlinkToDeath(connectioninfo.deathMonitor, 0);
            this;
            JVM INSTR monitorexit ;
            mConnection.onServiceDisconnected(componentname);
            return;
            componentname;
            throw componentname;
        }

        void doForget()
        {
            this;
            JVM INSTR monitorenter ;
            int i = 0;
_L2:
            if(i >= mActiveConnections.size())
                break; /* Loop/switch isn't completed */
            ConnectionInfo connectioninfo = (ConnectionInfo)mActiveConnections.valueAt(i);
            connectioninfo.binder.unlinkToDeath(connectioninfo.deathMonitor, 0);
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            mActiveConnections.clear();
            mForgotten = true;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        int getFlags()
        {
            return mFlags;
        }

        IServiceConnection getIServiceConnection()
        {
            return mIServiceConnection;
        }

        ServiceConnectionLeaked getLocation()
        {
            return mLocation;
        }

        ServiceConnection getServiceConnection()
        {
            return mConnection;
        }

        RuntimeException getUnbindLocation()
        {
            return mUnbindLocation;
        }

        void setUnbindLocation(RuntimeException runtimeexception)
        {
            mUnbindLocation = runtimeexception;
        }

        void validate(Context context, Handler handler)
        {
            if(mContext != context)
                throw new RuntimeException((new StringBuilder()).append("ServiceConnection ").append(mConnection).append(" registered with differing Context (was ").append(mContext).append(" now ").append(context).append(")").toString());
            if(mActivityThread != handler)
                throw new RuntimeException((new StringBuilder()).append("ServiceConnection ").append(mConnection).append(" registered with differing handler (was ").append(mActivityThread).append(" now ").append(handler).append(")").toString());
            else
                return;
        }

        private final ArrayMap mActiveConnections = new ArrayMap();
        private final Handler mActivityThread;
        private final ServiceConnection mConnection;
        private final Context mContext;
        private final int mFlags;
        private boolean mForgotten;
        private final InnerConnection mIServiceConnection = new InnerConnection(this);
        private final ServiceConnectionLeaked mLocation = new ServiceConnectionLeaked(null);
        private RuntimeException mUnbindLocation;

        ServiceDispatcher(ServiceConnection serviceconnection, Context context, Handler handler, int i)
        {
            mConnection = serviceconnection;
            mContext = context;
            mActivityThread = handler;
            mLocation.fillInStackTrace();
            mFlags = i;
        }
    }

    private static class ServiceDispatcher.ConnectionInfo
    {

        IBinder binder;
        android.os.IBinder.DeathRecipient deathMonitor;

        private ServiceDispatcher.ConnectionInfo()
        {
        }

        ServiceDispatcher.ConnectionInfo(ServiceDispatcher.ConnectionInfo connectioninfo)
        {
            this();
        }
    }

    private final class ServiceDispatcher.DeathMonitor
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            death(mName, mService);
        }

        final ComponentName mName;
        final IBinder mService;
        final ServiceDispatcher this$1;

        ServiceDispatcher.DeathMonitor(ComponentName componentname, IBinder ibinder)
        {
            this$1 = ServiceDispatcher.this;
            super();
            mName = componentname;
            mService = ibinder;
        }
    }

    private static class ServiceDispatcher.InnerConnection extends IServiceConnection.Stub
    {

        public void connected(ComponentName componentname, IBinder ibinder, boolean flag)
            throws RemoteException
        {
            ServiceDispatcher servicedispatcher = (ServiceDispatcher)mDispatcher.get();
            if(servicedispatcher != null)
                servicedispatcher.connected(componentname, ibinder, flag);
        }

        final WeakReference mDispatcher;

        ServiceDispatcher.InnerConnection(ServiceDispatcher servicedispatcher)
        {
            mDispatcher = new WeakReference(servicedispatcher);
        }
    }

    private final class ServiceDispatcher.RunConnection
        implements Runnable
    {

        public void run()
        {
            if(mCommand != 0) goto _L2; else goto _L1
_L1:
            doConnected(mName, mService, mDead);
_L4:
            return;
_L2:
            if(mCommand == 1)
                doDeath(mName, mService);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final int mCommand;
        final boolean mDead;
        final ComponentName mName;
        final IBinder mService;
        final ServiceDispatcher this$1;

        ServiceDispatcher.RunConnection(ComponentName componentname, IBinder ibinder, int i, boolean flag)
        {
            this$1 = ServiceDispatcher.this;
            super();
            mName = componentname;
            mService = ibinder;
            mCommand = i;
            mDead = flag;
        }
    }

    private class SplitDependencyLoaderImpl extends SplitDependencyLoader
    {

        private int ensureSplitLoaded(String s)
            throws android.content.pm.PackageManager.NameNotFoundException
        {
            int i = 0;
            if(s != null)
            {
                i = Arrays.binarySearch(LoadedApk._2D_get3(LoadedApk.this), s);
                if(i < 0)
                    throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("Split name '").append(s).append("' is not installed").toString());
                i++;
            }
            loadDependenciesForSplit(i);
            return i;
        }

        protected void constructSplit(int i, int ai[], int j)
            throws android.content.pm.PackageManager.NameNotFoundException
        {
            int k = 0;
            ArrayList arraylist = new ArrayList();
            if(i == 0)
            {
                LoadedApk._2D_wrap0(LoadedApk.this, null);
                mCachedClassLoaders[0] = LoadedApk._2D_get0(LoadedApk.this);
                j = ai.length;
                for(i = 0; i < j; i++)
                {
                    k = ai[i];
                    arraylist.add(LoadedApk._2D_get4(LoadedApk.this)[k - 1]);
                }

                mCachedResourcePaths[0] = (String[])arraylist.toArray(new String[arraylist.size()]);
                return;
            }
            ClassLoader classloader = mCachedClassLoaders[j];
            mCachedClassLoaders[i] = ApplicationLoaders.getDefault().getClassLoader(LoadedApk._2D_get1(LoadedApk.this)[i - 1], getTargetSdkVersion(), false, null, null, classloader, LoadedApk._2D_get2(LoadedApk.this)[i - 1]);
            Collections.addAll(arraylist, mCachedResourcePaths[j]);
            arraylist.add(LoadedApk._2D_get4(LoadedApk.this)[i - 1]);
            int i1 = ai.length;
            for(j = k; j < i1; j++)
            {
                int l = ai[j];
                arraylist.add(LoadedApk._2D_get4(LoadedApk.this)[l - 1]);
            }

            mCachedResourcePaths[i] = (String[])arraylist.toArray(new String[arraylist.size()]);
        }

        ClassLoader getClassLoaderForSplit(String s)
            throws android.content.pm.PackageManager.NameNotFoundException
        {
            return mCachedClassLoaders[ensureSplitLoaded(s)];
        }

        String[] getSplitPathsForSplit(String s)
            throws android.content.pm.PackageManager.NameNotFoundException
        {
            return mCachedResourcePaths[ensureSplitLoaded(s)];
        }

        protected boolean isSplitCached(int i)
        {
            boolean flag;
            if(mCachedClassLoaders[i] != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private final ClassLoader mCachedClassLoaders[];
        private final String mCachedResourcePaths[][];
        final LoadedApk this$0;

        SplitDependencyLoaderImpl(SparseArray sparsearray)
        {
            this$0 = LoadedApk.this;
            super(sparsearray);
            mCachedResourcePaths = new String[LoadedApk._2D_get3(LoadedApk.this).length + 1][];
            mCachedClassLoaders = new ClassLoader[LoadedApk._2D_get3(LoadedApk.this).length + 1];
        }
    }

    private static class WarningContextClassLoader extends ClassLoader
    {

        private void warn(String s)
        {
            if(warned)
            {
                return;
            } else
            {
                warned = true;
                Thread.currentThread().setContextClassLoader(getParent());
                Slog.w("ActivityThread", (new StringBuilder()).append("ClassLoader.").append(s).append(": ").append("The class loader returned by ").append("Thread.getContextClassLoader() may fail for processes ").append("that host multiple applications. You should explicitly ").append("specify a context class loader. For example: ").append("Thread.setContextClassLoader(getClass().getClassLoader());").toString());
                return;
            }
        }

        public void clearAssertionStatus()
        {
            warn("clearAssertionStatus");
            getParent().clearAssertionStatus();
        }

        public URL getResource(String s)
        {
            warn("getResource");
            return getParent().getResource(s);
        }

        public InputStream getResourceAsStream(String s)
        {
            warn("getResourceAsStream");
            return getParent().getResourceAsStream(s);
        }

        public Enumeration getResources(String s)
            throws IOException
        {
            warn("getResources");
            return getParent().getResources(s);
        }

        public Class loadClass(String s)
            throws ClassNotFoundException
        {
            warn("loadClass");
            return getParent().loadClass(s);
        }

        public void setClassAssertionStatus(String s, boolean flag)
        {
            warn("setClassAssertionStatus");
            getParent().setClassAssertionStatus(s, flag);
        }

        public void setDefaultAssertionStatus(boolean flag)
        {
            warn("setDefaultAssertionStatus");
            getParent().setDefaultAssertionStatus(flag);
        }

        public void setPackageAssertionStatus(String s, boolean flag)
        {
            warn("setPackageAssertionStatus");
            getParent().setPackageAssertionStatus(s, flag);
        }

        private static boolean warned = false;


        private WarningContextClassLoader()
        {
        }

        WarningContextClassLoader(WarningContextClassLoader warningcontextclassloader)
        {
            this();
        }
    }


    static ClassLoader _2D_get0(LoadedApk loadedapk)
    {
        return loadedapk.mClassLoader;
    }

    static String[] _2D_get1(LoadedApk loadedapk)
    {
        return loadedapk.mSplitAppDirs;
    }

    static String[] _2D_get2(LoadedApk loadedapk)
    {
        return loadedapk.mSplitClassLoaderNames;
    }

    static String[] _2D_get3(LoadedApk loadedapk)
    {
        return loadedapk.mSplitNames;
    }

    static String[] _2D_get4(LoadedApk loadedapk)
    {
        return loadedapk.mSplitResDirs;
    }

    static void _2D_wrap0(LoadedApk loadedapk, List list)
    {
        loadedapk.createOrUpdateClassLoaderLocked(list);
    }

    LoadedApk(ActivityThread activitythread)
    {
        mDisplayAdjustments = new DisplayAdjustments();
        mReceivers = new ArrayMap();
        mUnregisteredReceivers = new ArrayMap();
        mServices = new ArrayMap();
        mUnboundServices = new ArrayMap();
        mActivityThread = activitythread;
        mApplicationInfo = new ApplicationInfo();
        mApplicationInfo.packageName = "android";
        mPackageName = "android";
        mAppDir = null;
        mResDir = null;
        mSplitAppDirs = null;
        mSplitResDirs = null;
        mSplitClassLoaderNames = null;
        mOverlayDirs = null;
        mDataDir = null;
        mDataDirFile = null;
        mDeviceProtectedDataDirFile = null;
        mCredentialProtectedDataDirFile = null;
        mLibDir = null;
        mBaseClassLoader = null;
        mSecurityViolation = false;
        mIncludeCode = true;
        mRegisterPackage = false;
        mClassLoader = ClassLoader.getSystemClassLoader();
        mResources = Resources.getSystem();
    }

    public LoadedApk(ActivityThread activitythread, ApplicationInfo applicationinfo, CompatibilityInfo compatibilityinfo, ClassLoader classloader, boolean flag, boolean flag1, boolean flag2)
    {
        mDisplayAdjustments = new DisplayAdjustments();
        mReceivers = new ArrayMap();
        mUnregisteredReceivers = new ArrayMap();
        mServices = new ArrayMap();
        mUnboundServices = new ArrayMap();
        mActivityThread = activitythread;
        setApplicationInfo(applicationinfo);
        mPackageName = applicationinfo.packageName;
        mBaseClassLoader = classloader;
        mSecurityViolation = flag;
        mIncludeCode = flag1;
        mRegisterPackage = flag2;
        mDisplayAdjustments.setCompatibilityInfo(compatibilityinfo);
    }

    private static ApplicationInfo adjustNativeLibraryPaths(ApplicationInfo applicationinfo)
    {
        if(applicationinfo.primaryCpuAbi != null && applicationinfo.secondaryCpuAbi != null)
        {
            String s = VMRuntime.getRuntime().vmInstructionSet();
            String s1 = VMRuntime.getInstructionSet(applicationinfo.secondaryCpuAbi);
            String s2 = SystemProperties.get((new StringBuilder()).append("ro.dalvik.vm.isa.").append(s1).toString());
            if(!s2.isEmpty())
                s1 = s2;
            if(s.equals(s1))
            {
                applicationinfo = new ApplicationInfo(applicationinfo);
                applicationinfo.nativeLibraryDir = applicationinfo.secondaryNativeLibraryDir;
                applicationinfo.primaryCpuAbi = applicationinfo.secondaryCpuAbi;
                return applicationinfo;
            }
        }
        return applicationinfo;
    }

    private static void appendApkLibPathIfNeeded(String s, ApplicationInfo applicationinfo, List list)
    {
        if(list != null && applicationinfo.primaryCpuAbi != null && s.endsWith(".apk") && applicationinfo.targetSdkVersion >= 26)
            list.add((new StringBuilder()).append(s).append("!/lib/").append(applicationinfo.primaryCpuAbi).toString());
    }

    private void createOrUpdateClassLoaderLocked(List list)
    {
        if(mPackageName.equals("android"))
        {
            if(mClassLoader != null)
                return;
            if(mBaseClassLoader != null)
                mClassLoader = mBaseClassLoader;
            else
                mClassLoader = ClassLoader.getSystemClassLoader();
            return;
        }
        Object obj;
        Object obj1;
        boolean flag;
        String s;
        boolean flag1;
        boolean flag2;
        String s1;
        String s2;
        if(!Objects.equals(mPackageName, ActivityThread.currentPackageName()) && mIncludeCode)
            try
            {
                ActivityThread.getPackageManager().notifyPackageUse(mPackageName, 6);
            }
            // Misplaced declaration of an exception variable
            catch(List list)
            {
                throw list.rethrowFromSystemServer();
            }
        if(mRegisterPackage)
            try
            {
                ActivityManager.getService().addPackageDependency(mPackageName);
            }
            // Misplaced declaration of an exception variable
            catch(List list)
            {
                throw list.rethrowFromSystemServer();
            }
        obj = new ArrayList(10);
        obj1 = new ArrayList(10);
        if(mApplicationInfo.isSystemApp())
            flag = mApplicationInfo.isUpdatedSystemApp() ^ true;
        else
            flag = false;
        s = System.getProperty("java.library.path");
        flag1 = s.contains("/vendor/lib");
        flag2 = flag;
        if(mApplicationInfo.getCodePath() != null)
        {
            flag2 = flag;
            if(mApplicationInfo.getCodePath().startsWith("/vendor/"))
            {
                flag2 = flag;
                if(flag1 ^ true)
                    flag2 = false;
            }
        }
        makePaths(mActivityThread, flag2, mApplicationInfo, ((List) (obj)), ((List) (obj1)));
        s1 = mDataDir;
        s2 = s1;
        if(flag2)
            s2 = (new StringBuilder()).append(s1).append(File.pathSeparator).append(s).toString();
        obj1 = TextUtils.join(File.pathSeparator, ((Iterable) (obj1)));
        if(!mIncludeCode)
        {
            if(mClassLoader == null)
            {
                list = StrictMode.allowThreadDiskReads();
                mClassLoader = ApplicationLoaders.getDefault().getClassLoader("", mApplicationInfo.targetSdkVersion, flag2, ((String) (obj1)), s2, mBaseClassLoader, null);
                StrictMode.setThreadPolicy(list);
            }
            return;
        }
        boolean flag3;
        boolean flag4;
        if(((List) (obj)).size() == 1)
            s1 = (String)((List) (obj)).get(0);
        else
            s1 = TextUtils.join(File.pathSeparator, ((Iterable) (obj)));
        flag3 = false;
        if(mClassLoader == null)
        {
            obj = StrictMode.allowThreadDiskReads();
            mClassLoader = ApplicationLoaders.getDefault().getClassLoader(s1, mApplicationInfo.targetSdkVersion, flag2, ((String) (obj1)), s2, mBaseClassLoader, mApplicationInfo.classLoaderName);
            StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj)));
            flag3 = true;
        }
        flag4 = flag3;
        if(list != null)
        {
            flag4 = flag3;
            if(list.size() > 0)
            {
                list = TextUtils.join(File.pathSeparator, list);
                ApplicationLoaders.getDefault().addPath(mClassLoader, list);
                flag4 = true;
            }
        }
        if(flag4 && ActivityThread.isSystem() ^ true)
            setupJitProfileSupport();
    }

    private static String[] getLibrariesFor(String s)
    {
        try
        {
            s = ActivityThread.getPackageManager().getApplicationInfo(s, 1024, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s == null)
            return null;
        else
            return ((ApplicationInfo) (s)).sharedLibraryFiles;
    }

    private static File getPrimaryProfileFile(String s)
    {
        return new File(Environment.getDataProfilesDePackageDirectory(UserHandle.myUserId(), s), "primary.prof");
    }

    private void initializeJavaContextClassLoader()
    {
        Object obj = ActivityThread.getPackageManager();
        try
        {
            obj = ((IPackageManager) (obj)).getPackageInfo(mPackageName, 0x10000000, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
        }
        if(obj == null)
            throw new IllegalStateException((new StringBuilder()).append("Unable to get package info for ").append(mPackageName).append("; is package not installed?").toString());
        boolean flag;
        boolean flag1;
        if(((PackageInfo) (obj)).sharedUserId != null)
            flag = true;
        else
            flag = false;
        if(((PackageInfo) (obj)).applicationInfo != null)
            flag1 = mPackageName.equals(((PackageInfo) (obj)).applicationInfo.processName) ^ true;
        else
            flag1 = false;
        if(flag)
            flag1 = true;
        if(flag1)
            obj = new WarningContextClassLoader(null);
        else
            obj = mClassLoader;
        Thread.currentThread().setContextClassLoader(((ClassLoader) (obj)));
    }

    public static void makePaths(ActivityThread activitythread, ApplicationInfo applicationinfo, List list)
    {
        makePaths(activitythread, false, applicationinfo, list, null);
    }

    public static void makePaths(ActivityThread activitythread, boolean flag, ApplicationInfo applicationinfo, List list, List list1)
    {
        String s1;
        String as[];
        String as1[];
label0:
        {
            String s = applicationinfo.sourceDir;
            s1 = applicationinfo.nativeLibraryDir;
            as = applicationinfo.sharedLibraryFiles;
            list.clear();
            list.add(s);
            if(applicationinfo.splitSourceDirs != null && applicationinfo.requestsIsolatedSplitLoading() ^ true)
                Collections.addAll(list, applicationinfo.splitSourceDirs);
            if(list1 != null)
                list1.clear();
            Object obj = null;
            as1 = obj;
            if(activitythread == null)
                break label0;
            String s2 = activitythread.mInstrumentationPackageName;
            String s3 = activitythread.mInstrumentationAppDir;
            String as2[] = activitythread.mInstrumentationSplitAppDirs;
            String s4 = activitythread.mInstrumentationLibDir;
            String s5 = activitythread.mInstrumentedAppDir;
            String as3[] = activitythread.mInstrumentedSplitAppDirs;
            activitythread = activitythread.mInstrumentedLibDir;
            if(!s.equals(s3))
            {
                as1 = obj;
                if(!s.equals(s5))
                    break label0;
            }
            list.clear();
            list.add(s3);
            if(!applicationinfo.requestsIsolatedSplitLoading())
            {
                if(as2 != null)
                    Collections.addAll(list, as2);
                if(!s3.equals(s5))
                {
                    list.add(s5);
                    if(as3 != null)
                        Collections.addAll(list, as3);
                }
            }
            if(list1 != null)
            {
                list1.add(s4);
                if(!s4.equals(activitythread))
                    list1.add(activitythread);
            }
            as1 = obj;
            if(!s5.equals(s3))
                as1 = getLibrariesFor(s2);
        }
        if(list1 != null)
        {
            if(list1.isEmpty())
                list1.add(s1);
            if(applicationinfo.primaryCpuAbi != null)
            {
                if(applicationinfo.targetSdkVersion < 24)
                {
                    Object obj1 = (new StringBuilder()).append("/system/fake-libs");
                    if(VMRuntime.is64BitAbi(applicationinfo.primaryCpuAbi))
                        activitythread = "64";
                    else
                        activitythread = "";
                    list1.add(((StringBuilder) (obj1)).append(activitythread).toString());
                }
                for(obj1 = list.iterator(); ((Iterator) (obj1)).hasNext(); list1.add((new StringBuilder()).append(activitythread).append("!/lib/").append(applicationinfo.primaryCpuAbi).toString()))
                    activitythread = (String)((Iterator) (obj1)).next();

            }
            if(flag)
                list1.add(System.getProperty("java.library.path"));
        }
        if(as != null)
        {
            int i = 0;
            int k = 0;
            for(int i1 = as.length; k < i1;)
            {
                activitythread = as[k];
                int j1 = i;
                if(!list.contains(activitythread))
                {
                    list.add(i, activitythread);
                    j1 = i + 1;
                    appendApkLibPathIfNeeded(activitythread, applicationinfo, list1);
                }
                k++;
                i = j1;
            }

        }
        if(as1 != null)
        {
            int l = 0;
            for(int j = as1.length; l < j; l++)
            {
                activitythread = as1[l];
                if(!list.contains(activitythread))
                {
                    list.add(0, activitythread);
                    appendApkLibPathIfNeeded(activitythread, applicationinfo, list1);
                }
            }

        }
    }

    private void rewriteRValues(ClassLoader classloader, String s, int i)
    {
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #221 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            classloader = classloader.loadClass(stringbuilder.append(s).append(".R").toString());
        }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader)
        {
            Log.i("LoadedApk", (new StringBuilder()).append("No resource references to update in package ").append(s).toString());
            return;
        }
        try
        {
            classloader = classloader.getMethod("onResourcesLoaded", new Class[] {
                Integer.TYPE
            });
        }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader)
        {
            return;
        }
        try
        {
            classloader.invoke(null, new Object[] {
                Integer.valueOf(i)
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader) { }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader)
        {
            classloader = classloader.getCause();
        }
        throw new RuntimeException((new StringBuilder()).append("Failed to rewrite resource references for ").append(s).toString(), classloader);
    }

    private void setApplicationInfo(ApplicationInfo applicationinfo)
    {
        int i = Process.myUid();
        ApplicationInfo applicationinfo1 = adjustNativeLibraryPaths(applicationinfo);
        mApplicationInfo = applicationinfo1;
        mAppDir = applicationinfo1.sourceDir;
        if(applicationinfo1.uid == i)
            applicationinfo = applicationinfo1.sourceDir;
        else
            applicationinfo = applicationinfo1.publicSourceDir;
        mResDir = applicationinfo;
        mOverlayDirs = applicationinfo1.resourceDirs;
        mDataDir = applicationinfo1.dataDir;
        mLibDir = applicationinfo1.nativeLibraryDir;
        mDataDirFile = FileUtils.newFileOrNull(applicationinfo1.dataDir);
        mDeviceProtectedDataDirFile = FileUtils.newFileOrNull(applicationinfo1.deviceProtectedDataDir);
        mCredentialProtectedDataDirFile = FileUtils.newFileOrNull(applicationinfo1.credentialProtectedDataDir);
        mSplitNames = applicationinfo1.splitNames;
        mSplitAppDirs = applicationinfo1.splitSourceDirs;
        if(applicationinfo1.uid == i)
            applicationinfo = applicationinfo1.splitSourceDirs;
        else
            applicationinfo = applicationinfo1.splitPublicSourceDirs;
        mSplitResDirs = applicationinfo;
        mSplitClassLoaderNames = applicationinfo1.splitClassLoaderNames;
        if(applicationinfo1.requestsIsolatedSplitLoading() && ArrayUtils.isEmpty(mSplitNames) ^ true)
            mSplitLoader = new SplitDependencyLoaderImpl(applicationinfo1.splitDependencies);
    }

    private void setupJitProfileSupport()
    {
        if(!SystemProperties.getBoolean("dalvik.vm.usejitprofiles", false))
            return;
        if(mApplicationInfo.uid != Process.myUid())
            return;
        ArrayList arraylist = new ArrayList();
        if((mApplicationInfo.flags & 4) != 0)
            arraylist.add(mApplicationInfo.sourceDir);
        if(mApplicationInfo.splitSourceDirs != null)
            Collections.addAll(arraylist, mApplicationInfo.splitSourceDirs);
        if(arraylist.isEmpty())
        {
            return;
        } else
        {
            VMRuntime.registerAppInfo(getPrimaryProfileFile(mPackageName).getPath(), (String[])arraylist.toArray(new String[arraylist.size()]));
            DexLoadReporter.getInstance().registerAppDataDir(mPackageName, mDataDir);
            return;
        }
    }

    public IIntentReceiver forgetReceiverDispatcher(Context context, BroadcastReceiver broadcastreceiver)
    {
        ArrayMap arraymap = mReceivers;
        arraymap;
        JVM INSTR monitorenter ;
        Object obj = (ArrayMap)mReceivers.get(context);
        if(obj == null)
            break MISSING_BLOCK_LABEL_162;
        ReceiverDispatcher receiverdispatcher = (ReceiverDispatcher)((ArrayMap) (obj)).get(broadcastreceiver);
        if(receiverdispatcher == null)
            break MISSING_BLOCK_LABEL_162;
        ArrayMap arraymap1;
        ((ArrayMap) (obj)).remove(broadcastreceiver);
        if(((ArrayMap) (obj)).size() == 0)
            mReceivers.remove(context);
        if(!broadcastreceiver.getDebugUnregister())
            break MISSING_BLOCK_LABEL_146;
        arraymap1 = (ArrayMap)mUnregisteredReceivers.get(context);
        obj = arraymap1;
        if(arraymap1 != null)
            break MISSING_BLOCK_LABEL_115;
        obj = JVM INSTR new #127 <Class ArrayMap>;
        ((ArrayMap) (obj)).ArrayMap();
        mUnregisteredReceivers.put(context, obj);
        context = JVM INSTR new #705 <Class IllegalArgumentException>;
        context.IllegalArgumentException("Originally unregistered here:");
        context.fillInStackTrace();
        receiverdispatcher.setUnregisterLocation(context);
        ((ArrayMap) (obj)).put(broadcastreceiver, receiverdispatcher);
        receiverdispatcher.mForgotten = true;
        context = receiverdispatcher.getIIntentReceiver();
        arraymap;
        JVM INSTR monitorexit ;
        return context;
        obj = (ArrayMap)mUnregisteredReceivers.get(context);
        if(obj == null)
            break MISSING_BLOCK_LABEL_252;
        obj = (ReceiverDispatcher)((ArrayMap) (obj)).get(broadcastreceiver);
        if(obj == null)
            break MISSING_BLOCK_LABEL_252;
        context = ((ReceiverDispatcher) (obj)).getUnregisterLocation();
        IllegalArgumentException illegalargumentexception1 = JVM INSTR new #705 <Class IllegalArgumentException>;
        obj = JVM INSTR new #221 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        illegalargumentexception1.IllegalArgumentException(((StringBuilder) (obj)).append("Unregistering Receiver ").append(broadcastreceiver).append(" that was already unregistered").toString(), context);
        throw illegalargumentexception1;
        context;
        arraymap;
        JVM INSTR monitorexit ;
        throw context;
        if(context != null)
            break MISSING_BLOCK_LABEL_304;
        IllegalStateException illegalstateexception = JVM INSTR new #437 <Class IllegalStateException>;
        StringBuilder stringbuilder = JVM INSTR new #221 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        illegalstateexception.IllegalStateException(stringbuilder.append("Unbinding Receiver ").append(broadcastreceiver).append(" from Context that is no longer in use: ").append(context).toString());
        throw illegalstateexception;
        IllegalArgumentException illegalargumentexception = JVM INSTR new #705 <Class IllegalArgumentException>;
        context = JVM INSTR new #221 <Class StringBuilder>;
        context.StringBuilder();
        illegalargumentexception.IllegalArgumentException(context.append("Receiver not registered: ").append(broadcastreceiver).toString());
        throw illegalargumentexception;
    }

    public final IServiceConnection forgetServiceDispatcher(Context context, ServiceConnection serviceconnection)
    {
        ArrayMap arraymap = mServices;
        arraymap;
        JVM INSTR monitorenter ;
        Object obj = (ArrayMap)mServices.get(context);
        if(obj == null)
            break MISSING_BLOCK_LABEL_164;
        ServiceDispatcher servicedispatcher = (ServiceDispatcher)((ArrayMap) (obj)).get(serviceconnection);
        if(servicedispatcher == null)
            break MISSING_BLOCK_LABEL_164;
        ArrayMap arraymap1;
        ((ArrayMap) (obj)).remove(serviceconnection);
        servicedispatcher.doForget();
        if(((ArrayMap) (obj)).size() == 0)
            mServices.remove(context);
        if((servicedispatcher.getFlags() & 2) == 0)
            break MISSING_BLOCK_LABEL_154;
        arraymap1 = (ArrayMap)mUnboundServices.get(context);
        obj = arraymap1;
        if(arraymap1 != null)
            break MISSING_BLOCK_LABEL_123;
        obj = JVM INSTR new #127 <Class ArrayMap>;
        ((ArrayMap) (obj)).ArrayMap();
        mUnboundServices.put(context, obj);
        context = JVM INSTR new #705 <Class IllegalArgumentException>;
        context.IllegalArgumentException("Originally unbound here:");
        context.fillInStackTrace();
        servicedispatcher.setUnbindLocation(context);
        ((ArrayMap) (obj)).put(serviceconnection, servicedispatcher);
        context = servicedispatcher.getIServiceConnection();
        arraymap;
        JVM INSTR monitorexit ;
        return context;
        obj = (ArrayMap)mUnboundServices.get(context);
        if(obj == null)
            break MISSING_BLOCK_LABEL_253;
        obj = (ServiceDispatcher)((ArrayMap) (obj)).get(serviceconnection);
        if(obj == null)
            break MISSING_BLOCK_LABEL_253;
        RuntimeException runtimeexception = ((ServiceDispatcher) (obj)).getUnbindLocation();
        context = JVM INSTR new #705 <Class IllegalArgumentException>;
        obj = JVM INSTR new #221 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        context.IllegalArgumentException(((StringBuilder) (obj)).append("Unbinding Service ").append(serviceconnection).append(" that was already unbound").toString(), runtimeexception);
        throw context;
        context;
        arraymap;
        JVM INSTR monitorexit ;
        throw context;
        if(context != null)
            break MISSING_BLOCK_LABEL_305;
        IllegalStateException illegalstateexception = JVM INSTR new #437 <Class IllegalStateException>;
        StringBuilder stringbuilder = JVM INSTR new #221 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        illegalstateexception.IllegalStateException(stringbuilder.append("Unbinding Service ").append(serviceconnection).append(" from Context that is no longer in use: ").append(context).toString());
        throw illegalstateexception;
        IllegalArgumentException illegalargumentexception = JVM INSTR new #705 <Class IllegalArgumentException>;
        context = JVM INSTR new #221 <Class StringBuilder>;
        context.StringBuilder();
        illegalargumentexception.IllegalArgumentException(context.append("Service not registered: ").append(serviceconnection).toString());
        throw illegalargumentexception;
    }

    public String getAppDir()
    {
        return mAppDir;
    }

    Application getApplication()
    {
        return mApplication;
    }

    public ApplicationInfo getApplicationInfo()
    {
        return mApplicationInfo;
    }

    public AssetManager getAssets()
    {
        return getResources().getAssets();
    }

    public ClassLoader getClassLoader()
    {
        this;
        JVM INSTR monitorenter ;
        ClassLoader classloader;
        if(mClassLoader == null)
            createOrUpdateClassLoaderLocked(null);
        classloader = mClassLoader;
        this;
        JVM INSTR monitorexit ;
        return classloader;
        Exception exception;
        exception;
        throw exception;
    }

    public CompatibilityInfo getCompatibilityInfo()
    {
        return mDisplayAdjustments.getCompatibilityInfo();
    }

    public File getCredentialProtectedDataDirFile()
    {
        return mCredentialProtectedDataDirFile;
    }

    public String getDataDir()
    {
        return mDataDir;
    }

    public File getDataDirFile()
    {
        return mDataDirFile;
    }

    public File getDeviceProtectedDataDirFile()
    {
        return mDeviceProtectedDataDirFile;
    }

    public String getLibDir()
    {
        return mLibDir;
    }

    public String[] getOverlayDirs()
    {
        return mOverlayDirs;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public IIntentReceiver getReceiverDispatcher(BroadcastReceiver broadcastreceiver, Context context, Handler handler, Instrumentation instrumentation, boolean flag)
    {
        ArrayMap arraymap = mReceivers;
        arraymap;
        JVM INSTR monitorenter ;
        ArrayMap arraymap1 = null;
        if(!flag) goto _L2; else goto _L1
_L1:
        Object obj = (ArrayMap)mReceivers.get(context);
        arraymap1 = ((ArrayMap) (obj));
        if(obj == null) goto _L2; else goto _L3
_L3:
        ReceiverDispatcher receiverdispatcher = (ReceiverDispatcher)((ArrayMap) (obj)).get(broadcastreceiver);
        arraymap1 = ((ArrayMap) (obj));
        obj = receiverdispatcher;
_L12:
        if(obj != null) goto _L5; else goto _L4
_L4:
        obj = JVM INSTR new #6   <Class LoadedApk$ReceiverDispatcher>;
        ((ReceiverDispatcher) (obj)).ReceiverDispatcher(broadcastreceiver, context, handler, instrumentation, flag);
        if(!flag)
            break MISSING_BLOCK_LABEL_169;
        if(arraymap1 != null) goto _L7; else goto _L6
_L6:
        handler = JVM INSTR new #127 <Class ArrayMap>;
        handler.ArrayMap();
        mReceivers.put(context, handler);
        context = handler;
_L10:
        context.put(broadcastreceiver, obj);
        broadcastreceiver = ((BroadcastReceiver) (obj));
_L8:
        broadcastreceiver.mForgotten = false;
        broadcastreceiver = broadcastreceiver.getIIntentReceiver();
        arraymap;
        JVM INSTR monitorexit ;
        return broadcastreceiver;
_L5:
        ((ReceiverDispatcher) (obj)).validate(context, handler);
        broadcastreceiver = ((BroadcastReceiver) (obj));
          goto _L8
        broadcastreceiver;
_L9:
        arraymap;
        JVM INSTR monitorexit ;
        throw broadcastreceiver;
        broadcastreceiver;
        continue; /* Loop/switch isn't completed */
        broadcastreceiver;
        if(true) goto _L9; else goto _L7
_L7:
        context = arraymap1;
          goto _L10
        broadcastreceiver = ((BroadcastReceiver) (obj));
          goto _L8
_L2:
        obj = null;
        if(true) goto _L12; else goto _L11
_L11:
    }

    public String getResDir()
    {
        return mResDir;
    }

    public Resources getResources()
    {
        if(mResources == null)
        {
            String as[];
            try
            {
                as = getSplitPaths(null);
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                throw new AssertionError("null split not found");
            }
            mResources = ResourcesManager.getInstance().getResources(null, mResDir, as, mOverlayDirs, mApplicationInfo.sharedLibraryFiles, 0, null, getCompatibilityInfo(), getClassLoader());
            ResourcesManager.initMiuiResource(mResources, mPackageName);
        }
        return mResources;
    }

    public final IServiceConnection getServiceDispatcher(ServiceConnection serviceconnection, Context context, Handler handler, int i)
    {
        ArrayMap arraymap = mServices;
        arraymap;
        JVM INSTR monitorenter ;
        ArrayMap arraymap1 = (ArrayMap)mServices.get(context);
        if(arraymap1 == null) goto _L2; else goto _L1
_L1:
        ServiceDispatcher servicedispatcher = (ServiceDispatcher)arraymap1.get(serviceconnection);
_L9:
        if(servicedispatcher != null) goto _L4; else goto _L3
_L3:
        servicedispatcher = JVM INSTR new #15  <Class LoadedApk$ServiceDispatcher>;
        servicedispatcher.ServiceDispatcher(serviceconnection, context, handler, i);
        handler = arraymap1;
        if(arraymap1 != null)
            break MISSING_BLOCK_LABEL_84;
        handler = JVM INSTR new #127 <Class ArrayMap>;
        handler.ArrayMap();
        mServices.put(context, handler);
        handler.put(serviceconnection, servicedispatcher);
        serviceconnection = servicedispatcher;
_L6:
        serviceconnection = serviceconnection.getIServiceConnection();
        arraymap;
        JVM INSTR monitorexit ;
        return serviceconnection;
_L4:
        servicedispatcher.validate(context, handler);
        serviceconnection = servicedispatcher;
        if(true) goto _L6; else goto _L5
_L5:
        serviceconnection;
_L7:
        arraymap;
        JVM INSTR monitorexit ;
        throw serviceconnection;
        serviceconnection;
        if(true) goto _L7; else goto _L2
_L2:
        servicedispatcher = null;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public String[] getSplitAppDirs()
    {
        return mSplitAppDirs;
    }

    ClassLoader getSplitClassLoader(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(mSplitLoader == null)
            return mClassLoader;
        else
            return mSplitLoader.getClassLoaderForSplit(s);
    }

    String[] getSplitPaths(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(mSplitLoader == null)
            return mSplitResDirs;
        else
            return mSplitLoader.getSplitPathsForSplit(s);
    }

    public String[] getSplitResDirs()
    {
        return mSplitResDirs;
    }

    public int getTargetSdkVersion()
    {
        return mApplicationInfo.targetSdkVersion;
    }

    void installSystemApplicationInfo(ApplicationInfo applicationinfo, ClassLoader classloader)
    {
        if(!_2D_assertionsDisabled && !applicationinfo.packageName.equals("android"))
        {
            throw new AssertionError();
        } else
        {
            mApplicationInfo = applicationinfo;
            mClassLoader = classloader;
            return;
        }
    }

    public boolean isSecurityViolation()
    {
        return mSecurityViolation;
    }

    public Application makeApplication(boolean flag, Instrumentation instrumentation)
    {
        Application application;
        Application application1;
        String s1;
label0:
        {
            if(mApplication != null)
                return mApplication;
            Trace.traceBegin(64L, "makeApplication");
            application = null;
            String s = mApplicationInfo.className;
            if(!flag)
            {
                s1 = s;
                if(s != null)
                    break label0;
            }
            s1 = "android.app.Application";
        }
        application1 = application;
        ClassLoader classloader = getClassLoader();
        application1 = application;
        if(mPackageName.equals("android"))
            break MISSING_BLOCK_LABEL_102;
        application1 = application;
        Trace.traceBegin(64L, "initializeJavaContextClassLoader");
        application1 = application;
        initializeJavaContextClassLoader();
        application1 = application;
        Trace.traceEnd(64L);
        application1 = application;
        ContextImpl contextimpl = ContextImpl.createAppContext(mActivityThread, this);
        application1 = application;
        application = mActivityThread.mInstrumentation.newApplication(classloader, s1, contextimpl);
        application1 = application;
        int i;
        int j;
        try
        {
            contextimpl.setOuterContext(application);
        }
        catch(Exception exception1)
        {
            application = application1;
            if(!mActivityThread.mInstrumentation.onException(application1, exception1))
            {
                Trace.traceEnd(64L);
                throw new RuntimeException((new StringBuilder()).append("Unable to instantiate application ").append(s1).append(": ").append(exception1.toString()).toString(), exception1);
            }
        }
        mActivityThread.mAllApplications.add(application);
        mApplication = application;
        if(instrumentation != null)
            try
            {
                instrumentation.callApplicationOnCreate(application);
            }
            catch(Exception exception)
            {
                if(!instrumentation.onException(application, exception))
                {
                    Trace.traceEnd(64L);
                    throw new RuntimeException((new StringBuilder()).append("Unable to create application ").append(application.getClass().getName()).append(": ").append(exception.toString()).toString(), exception);
                }
            }
        instrumentation = getAssets().getAssignedPackageIdentifiers();
        i = instrumentation.size();
        j = 0;
        while(j < i) 
        {
            int k = instrumentation.keyAt(j);
            if(k != 1 && k != 127)
                rewriteRValues(getClassLoader(), (String)instrumentation.valueAt(j), k);
            j++;
        }
        Trace.traceEnd(64L);
        return application;
    }

    public void removeContextRegistrations(Context context, String s, String s1)
    {
        boolean flag = StrictMode.vmRegistrationLeaksEnabled();
        ArrayMap arraymap = mReceivers;
        arraymap;
        JVM INSTR monitorenter ;
        ArrayMap arraymap1 = (ArrayMap)mReceivers.remove(context);
        int i;
        if(arraymap1 == null)
            break MISSING_BLOCK_LABEL_196;
        i = 0;
_L1:
        ReceiverDispatcher receiverdispatcher;
        Object obj;
        if(i >= arraymap1.size())
            break MISSING_BLOCK_LABEL_196;
        receiverdispatcher = (ReceiverDispatcher)arraymap1.valueAt(i);
        obj = JVM INSTR new #953 <Class IntentReceiverLeaked>;
        StringBuilder stringbuilder1 = JVM INSTR new #221 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        ((IntentReceiverLeaked) (obj)).IntentReceiverLeaked(stringbuilder1.append(s1).append(" ").append(s).append(" has leaked IntentReceiver ").append(receiverdispatcher.getIntentReceiver()).append(" that was ").append("originally registered here. Are you missing a ").append("call to unregisterReceiver()?").toString());
        ((IntentReceiverLeaked) (obj)).setStackTrace(receiverdispatcher.getLocation().getStackTrace());
        Slog.e("ActivityThread", ((IntentReceiverLeaked) (obj)).getMessage(), ((Throwable) (obj)));
        if(!flag)
            break MISSING_BLOCK_LABEL_165;
        StrictMode.onIntentReceiverLeaked(((Throwable) (obj)));
        ActivityManager.getService().unregisterReceiver(receiverdispatcher.getIIntentReceiver());
        i++;
          goto _L1
        context;
        throw context.rethrowFromSystemServer();
        context;
        arraymap;
        JVM INSTR monitorexit ;
        throw context;
        mUnregisteredReceivers.remove(context);
        arraymap;
        JVM INSTR monitorexit ;
        arraymap = mServices;
        arraymap;
        JVM INSTR monitorenter ;
        arraymap1 = (ArrayMap)mServices.remove(context);
        if(arraymap1 == null) goto _L3; else goto _L2
_L2:
        i = 0;
_L4:
        ServiceConnectionLeaked serviceconnectionleaked;
        if(i >= arraymap1.size())
            break; /* Loop/switch isn't completed */
        obj = (ServiceDispatcher)arraymap1.valueAt(i);
        serviceconnectionleaked = JVM INSTR new #1001 <Class ServiceConnectionLeaked>;
        StringBuilder stringbuilder = JVM INSTR new #221 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        serviceconnectionleaked.ServiceConnectionLeaked(stringbuilder.append(s1).append(" ").append(s).append(" has leaked ServiceConnection ").append(((ServiceDispatcher) (obj)).getServiceConnection()).append(" that was originally bound here").toString());
        serviceconnectionleaked.setStackTrace(((ServiceDispatcher) (obj)).getLocation().getStackTrace());
        Slog.e("ActivityThread", serviceconnectionleaked.getMessage(), serviceconnectionleaked);
        if(!flag)
            break MISSING_BLOCK_LABEL_356;
        StrictMode.onServiceConnectionLeaked(serviceconnectionleaked);
        ActivityManager.getService().unbindService(((ServiceDispatcher) (obj)).getIServiceConnection());
        ((ServiceDispatcher) (obj)).doForget();
        i++;
        if(true) goto _L4; else goto _L3
        context;
        throw context.rethrowFromSystemServer();
        context;
        arraymap;
        JVM INSTR monitorexit ;
        throw context;
_L3:
        mUnboundServices.remove(context);
        arraymap;
        JVM INSTR monitorexit ;
    }

    public void setCompatibilityInfo(CompatibilityInfo compatibilityinfo)
    {
        mDisplayAdjustments.setCompatibilityInfo(compatibilityinfo);
    }

    public void updateApplicationInfo(ApplicationInfo applicationinfo, List list)
    {
        setApplicationInfo(applicationinfo);
        Object obj = new ArrayList();
        makePaths(mActivityThread, applicationinfo, ((List) (obj)));
        applicationinfo = new ArrayList(((List) (obj)).size());
        if(list != null)
        {
            obj = ((Iterable) (obj)).iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                String s = (String)((Iterator) (obj)).next();
                String s1 = s.substring(s.lastIndexOf(File.separator));
                boolean flag = false;
                Iterator iterator = list.iterator();
                boolean flag1;
                do
                {
                    flag1 = flag;
                    if(!iterator.hasNext())
                        break;
                    String s2 = (String)iterator.next();
                    if(!s1.equals(s2.substring(s2.lastIndexOf(File.separator))))
                        continue;
                    flag1 = true;
                    break;
                } while(true);
                if(!flag1)
                    applicationinfo.add(s);
            } while(true);
        } else
        {
            applicationinfo.addAll(((java.util.Collection) (obj)));
        }
        this;
        JVM INSTR monitorenter ;
        createOrUpdateClassLoaderLocked(applicationinfo);
        applicationinfo = mResources;
        if(applicationinfo == null)
            break MISSING_BLOCK_LABEL_227;
        applicationinfo = getSplitPaths(null);
        mResources = ResourcesManager.getInstance().getResources(null, mResDir, applicationinfo, mOverlayDirs, mApplicationInfo.sharedLibraryFiles, 0, null, getCompatibilityInfo(), getClassLoader());
        this;
        JVM INSTR monitorexit ;
        return;
        applicationinfo;
        applicationinfo = JVM INSTR new #825 <Class AssertionError>;
        applicationinfo.AssertionError("null split not found");
        throw applicationinfo;
        applicationinfo;
        this;
        JVM INSTR monitorexit ;
        throw applicationinfo;
    }

    static final boolean _2D_assertionsDisabled = android/app/LoadedApk.desiredAssertionStatus() ^ true;
    static final boolean DEBUG = false;
    static final String TAG = "LoadedApk";
    private final ActivityThread mActivityThread;
    private String mAppDir;
    private Application mApplication;
    private ApplicationInfo mApplicationInfo;
    private final ClassLoader mBaseClassLoader;
    private ClassLoader mClassLoader;
    private File mCredentialProtectedDataDirFile;
    private String mDataDir;
    private File mDataDirFile;
    private File mDeviceProtectedDataDirFile;
    private final DisplayAdjustments mDisplayAdjustments;
    private final boolean mIncludeCode;
    private String mLibDir;
    private String mOverlayDirs[];
    final String mPackageName;
    private final ArrayMap mReceivers;
    private final boolean mRegisterPackage;
    private String mResDir;
    Resources mResources;
    private final boolean mSecurityViolation;
    private final ArrayMap mServices;
    private String mSplitAppDirs[];
    private String mSplitClassLoaderNames[];
    private SplitDependencyLoaderImpl mSplitLoader;
    private String mSplitNames[];
    private String mSplitResDirs[];
    private final ArrayMap mUnboundServices;
    private final ArrayMap mUnregisteredReceivers;

}
