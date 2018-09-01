// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.miui.ResourcesManager;
import android.net.Uri;
import android.os.*;
import android.os.storage.IStorageManager;
import android.system.*;
import android.util.*;
import android.view.Display;
import android.view.DisplayAdjustments;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Objects;
import libcore.io.Memory;

// Referenced classes of package android.app:
//            SystemServiceRegistry, LoadedApk, ResourcesManager, ActivityThread, 
//            ActivityManager, IActivityManager, WallpaperManager, SharedPreferencesImpl, 
//            ApplicationPackageManager, ReceiverRestrictedContext, Activity, Instrumentation, 
//            ActivityOptions, IApplicationThread, IServiceConnection

class ContextImpl extends Context
{
    private static final class ApplicationContentResolver extends ContentResolver
    {

        protected IContentProvider acquireExistingProvider(Context context, String s)
        {
            return mMainThread.acquireExistingProvider(context, ContentProvider.getAuthorityWithoutUserId(s), resolveUserIdFromAuthority(s), true);
        }

        protected IContentProvider acquireProvider(Context context, String s)
        {
            return mMainThread.acquireProvider(context, ContentProvider.getAuthorityWithoutUserId(s), resolveUserIdFromAuthority(s), true);
        }

        protected IContentProvider acquireUnstableProvider(Context context, String s)
        {
            return mMainThread.acquireProvider(context, ContentProvider.getAuthorityWithoutUserId(s), resolveUserIdFromAuthority(s), false);
        }

        public void appNotRespondingViaProvider(IContentProvider icontentprovider)
        {
            mMainThread.appNotRespondingViaProvider(icontentprovider.asBinder());
        }

        public boolean releaseProvider(IContentProvider icontentprovider)
        {
            return mMainThread.releaseProvider(icontentprovider, true);
        }

        public boolean releaseUnstableProvider(IContentProvider icontentprovider)
        {
            return mMainThread.releaseProvider(icontentprovider, false);
        }

        protected int resolveUserIdFromAuthority(String s)
        {
            return ContentProvider.getUserIdFromAuthority(s, mUser.getIdentifier());
        }

        public void unstableProviderDied(IContentProvider icontentprovider)
        {
            mMainThread.handleUnstableProviderDied(icontentprovider.asBinder(), true);
        }

        private final ActivityThread mMainThread;
        private final UserHandle mUser;

        public ApplicationContentResolver(Context context, ActivityThread activitythread, UserHandle userhandle)
        {
            super(context);
            mMainThread = (ActivityThread)Preconditions.checkNotNull(activitythread);
            mUser = (UserHandle)Preconditions.checkNotNull(userhandle);
        }
    }


    private ContextImpl(ContextImpl contextimpl, ActivityThread activitythread, LoadedApk loadedapk, String s, IBinder ibinder, UserHandle userhandle, int i, 
            ClassLoader classloader)
    {
        mThemeResource = 0;
        mTheme = null;
        mReceiverRestrictedContext = null;
        mSplitName = null;
        mAutofillClient = null;
        mOuterContext = this;
        int j = i;
        if((i & 0x18) == 0)
        {
            File file = loadedapk.getDataDirFile();
            if(Objects.equals(file, loadedapk.getCredentialProtectedDataDirFile()))
            {
                j = i | 0x10;
            } else
            {
                j = i;
                if(Objects.equals(file, loadedapk.getDeviceProtectedDataDirFile()))
                    j = i | 8;
            }
        }
        mMainThread = activitythread;
        mActivityToken = ibinder;
        mFlags = j;
        ibinder = userhandle;
        if(userhandle == null)
            ibinder = Process.myUserHandle();
        mUser = ibinder;
        mPackageInfo = loadedapk;
        mSplitName = s;
        mClassLoader = classloader;
        if(contextimpl != null)
        {
            ResourcesManager.initMiuiResource(contextimpl.mResources, mPackageInfo.mPackageName);
            mBasePackageName = contextimpl.mBasePackageName;
            mOpPackageName = contextimpl.mOpPackageName;
            setResources(contextimpl.mResources);
            mDisplay = contextimpl.mDisplay;
        } else
        {
            mBasePackageName = loadedapk.mPackageName;
            contextimpl = loadedapk.getApplicationInfo();
            if(((ApplicationInfo) (contextimpl)).uid == 1000 && ((ApplicationInfo) (contextimpl)).uid != Process.myUid())
                mOpPackageName = ActivityThread.currentPackageName();
            else
                mOpPackageName = mBasePackageName;
        }
        mContentResolver = new ApplicationContentResolver(this, activitythread, ibinder);
    }

    private boolean bindServiceCommon(Intent intent, ServiceConnection serviceconnection, int i, Handler handler, UserHandle userhandle)
    {
        int j;
        if(serviceconnection == null)
            throw new IllegalArgumentException("connection is null");
        if(mPackageInfo == null)
            break MISSING_BLOCK_LABEL_186;
        serviceconnection = mPackageInfo.getServiceDispatcher(serviceconnection, getOuterContext(), handler, i);
        validateServiceIntent(intent);
        j = i;
        if(getActivityToken() != null)
            break MISSING_BLOCK_LABEL_95;
        j = i;
        if((i & 1) != 0)
            break MISSING_BLOCK_LABEL_95;
        j = i;
        if(mPackageInfo == null)
            break MISSING_BLOCK_LABEL_95;
        j = i;
        if(mPackageInfo.getApplicationInfo().targetSdkVersion < 14)
            j = i | 0x20;
        try
        {
            intent.prepareToLeaveProcess(this);
            i = ActivityManager.getService().bindService(mMainThread.getApplicationThread(), getActivityToken(), intent, intent.resolveTypeIfNeeded(getContentResolver()), serviceconnection, j, getOpPackageName(), userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(i >= 0)
            break MISSING_BLOCK_LABEL_197;
        handler = JVM INSTR new #264 <Class SecurityException>;
        serviceconnection = JVM INSTR new #266 <Class StringBuilder>;
        serviceconnection.StringBuilder();
        handler.SecurityException(serviceconnection.append("Not allowed to bind to service ").append(intent).toString());
        throw handler;
        throw new RuntimeException("Not supported in system context");
        boolean flag;
        if(i != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void checkMode(int i)
    {
        if(getApplicationInfo().targetSdkVersion >= 24)
        {
            if((i & 1) != 0)
                throw new SecurityException("MODE_WORLD_READABLE no longer supported");
            if((i & 2) != 0)
                throw new SecurityException("MODE_WORLD_WRITEABLE no longer supported");
        }
    }

    static ContextImpl createActivityContext(ActivityThread activitythread, LoadedApk loadedapk, ActivityInfo activityinfo, IBinder ibinder, int i, Configuration configuration)
    {
        String as[];
        ClassLoader classloader;
        if(loadedapk == null)
            throw new IllegalArgumentException("packageInfo");
        as = loadedapk.getSplitResDirs();
        classloader = loadedapk.getClassLoader();
        if(!loadedapk.getApplicationInfo().requestsIsolatedSplitLoading())
            break MISSING_BLOCK_LABEL_72;
        Trace.traceBegin(8192L, "SplitDependencies");
        classloader = loadedapk.getSplitClassLoader(activityinfo.splitName);
        as = loadedapk.getSplitPaths(activityinfo.splitName);
        Trace.traceEnd(8192L);
        activityinfo = new ContextImpl(null, activitythread, loadedapk, activityinfo.splitName, ibinder, null, 0, classloader);
        android.app.ResourcesManager resourcesmanager;
        if(i == -1)
            i = 0;
        if(i == 0)
            activitythread = loadedapk.getCompatibilityInfo();
        else
            activitythread = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        resourcesmanager = ResourcesManager.getInstance();
        activityinfo.setResources(resourcesmanager.createBaseActivityResources(ibinder, loadedapk.getResDir(), as, loadedapk.getOverlayDirs(), loadedapk.getApplicationInfo().sharedLibraryFiles, i, configuration, activitythread, classloader));
        activityinfo.mDisplay = resourcesmanager.getAdjustedDisplay(i, activityinfo.getResources());
        return activityinfo;
        loadedapk;
        activitythread = JVM INSTR new #286 <Class RuntimeException>;
        activitythread.RuntimeException(loadedapk);
        throw activitythread;
        activitythread;
        Trace.traceEnd(8192L);
        throw activitythread;
    }

    static ContextImpl createAppContext(ActivityThread activitythread, LoadedApk loadedapk)
    {
        if(loadedapk == null)
        {
            throw new IllegalArgumentException("packageInfo");
        } else
        {
            activitythread = new ContextImpl(null, activitythread, loadedapk, null, null, null, 0, null);
            activitythread.setResources(loadedapk.getResources());
            return activitythread;
        }
    }

    private static Resources createResources(IBinder ibinder, LoadedApk loadedapk, String s, int i, Configuration configuration, CompatibilityInfo compatibilityinfo)
    {
        String as[];
        try
        {
            as = loadedapk.getSplitPaths(s);
            s = loadedapk.getSplitClassLoader(s);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw new RuntimeException(ibinder);
        }
        return ResourcesManager.getInstance().getResources(ibinder, loadedapk.getResDir(), as, loadedapk.getOverlayDirs(), loadedapk.getApplicationInfo().sharedLibraryFiles, i, configuration, compatibilityinfo, s);
    }

    static ContextImpl createSystemContext(ActivityThread activitythread)
    {
        LoadedApk loadedapk = new LoadedApk(activitythread);
        activitythread = new ContextImpl(null, activitythread, loadedapk, null, null, null, 0, null);
        activitythread.setResources(loadedapk.getResources());
        ((ContextImpl) (activitythread)).mResources.updateConfiguration(((ContextImpl) (activitythread)).mResourcesManager.getConfiguration(), ((ContextImpl) (activitythread)).mResourcesManager.getDisplayMetrics());
        return activitythread;
    }

    static ContextImpl createSystemUiContext(ContextImpl contextimpl)
    {
        LoadedApk loadedapk = contextimpl.mPackageInfo;
        contextimpl = new ContextImpl(null, contextimpl.mMainThread, loadedapk, null, null, null, 0, null);
        contextimpl.setResources(createResources(null, loadedapk, null, 0, null, loadedapk.getCompatibilityInfo()));
        return contextimpl;
    }

    private void enforce(String s, int i, boolean flag, int j, String s1)
    {
        if(i != 0)
        {
            StringBuilder stringbuilder = new StringBuilder();
            if(s1 != null)
                s1 = (new StringBuilder()).append(s1).append(": ").toString();
            else
                s1 = "";
            stringbuilder = stringbuilder.append(s1);
            if(flag)
                s1 = (new StringBuilder()).append("Neither user ").append(j).append(" nor current process has ").toString();
            else
                s1 = (new StringBuilder()).append("uid ").append(j).append(" does not have ").toString();
            throw new SecurityException(stringbuilder.append(s1).append(s).append(".").toString());
        } else
        {
            return;
        }
    }

    private void enforceForUri(int i, int j, boolean flag, int k, Uri uri, String s)
    {
        if(j != 0)
        {
            StringBuilder stringbuilder = new StringBuilder();
            if(s != null)
                s = (new StringBuilder()).append(s).append(": ").toString();
            else
                s = "";
            stringbuilder = stringbuilder.append(s);
            if(flag)
                s = (new StringBuilder()).append("Neither user ").append(k).append(" nor current process has ").toString();
            else
                s = (new StringBuilder()).append("User ").append(k).append(" does not have ").toString();
            throw new SecurityException(stringbuilder.append(s).append(uriModeFlagToString(i)).append(" permission on ").append(uri).append(".").toString());
        } else
        {
            return;
        }
    }

    private File[] ensureExternalDirsExistOrFilter(File afile[])
    {
        File afile1[];
        int i;
        afile1 = new File[afile.length];
        i = 0;
_L2:
        File file;
        Object obj;
        if(i >= afile.length)
            break; /* Loop/switch isn't completed */
        file = afile[i];
        obj = file;
        if(file.exists())
            break MISSING_BLOCK_LABEL_139;
        obj = file;
        if(file.mkdirs())
            break MISSING_BLOCK_LABEL_139;
        obj = file;
        if(file.exists())
            break MISSING_BLOCK_LABEL_139;
        obj = android.os.storage.IStorageManager.Stub.asInterface(ServiceManager.getService("mount"));
        int j = ((IStorageManager) (obj)).mkdirs(getPackageName(), file.getAbsolutePath());
        obj = file;
        if(j == 0)
            break MISSING_BLOCK_LABEL_139;
        obj = JVM INSTR new #266 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.w("ContextImpl", ((StringBuilder) (obj)).append("Failed to ensure ").append(file).append(": ").append(j).toString());
        obj = null;
_L3:
        afile1[i] = ((File) (obj));
        i++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        Log.w("ContextImpl", (new StringBuilder()).append("Failed to ensure ").append(file).append(": ").append(exception).toString());
        exception = null;
          goto _L3
_L1:
        return afile1;
    }

    private static File ensurePrivateCacheDirExists(File file, String s)
    {
        return ensurePrivateDirExists(file, 1529, UserHandle.getCacheAppGid(Process.myUid()), s);
    }

    private static File ensurePrivateDirExists(File file)
    {
        return ensurePrivateDirExists(file, 505, -1, null);
    }

    private static File ensurePrivateDirExists(File file, int i, int j, String s)
    {
        String s1;
        if(file.exists())
            break MISSING_BLOCK_LABEL_84;
        s1 = file.getAbsolutePath();
        Os.mkdir(s1, i);
        Os.chmod(s1, i);
        if(j != -1)
            try
            {
                Os.chown(s1, -1, j);
            }
            catch(ErrnoException errnoexception)
            {
                if(errnoexception.errno != OsConstants.EEXIST)
                    Log.w("ContextImpl", (new StringBuilder()).append("Failed to ensure ").append(file).append(": ").append(errnoexception.getMessage()).toString());
            }
        if(s != null)
            try
            {
                StructStat structstat = Os.stat(file.getAbsolutePath());
                byte abyte0[] = new byte[8];
                Memory.pokeLong(abyte0, 0, structstat.st_ino, ByteOrder.nativeOrder());
                Os.setxattr(file.getParentFile().getAbsolutePath(), s, abyte0, 0);
            }
            catch(ErrnoException errnoexception1)
            {
                Log.w("ContextImpl", (new StringBuilder()).append("Failed to update ").append(s).append(": ").append(errnoexception1.getMessage()).toString());
            }
        return file;
    }

    private File getDatabasesDir()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        if(mDatabasesDir == null)
        {
            if(!"android".equals(getPackageName()))
                break MISSING_BLOCK_LABEL_55;
            File file = JVM INSTR new #443 <Class File>;
            file.File("/data/system");
            mDatabasesDir = file;
        }
_L1:
        File file1 = ensurePrivateDirExists(mDatabasesDir);
        obj;
        JVM INSTR monitorexit ;
        return file1;
        File file2 = JVM INSTR new #443 <Class File>;
        file2.File(getDataDir(), "databases");
        mDatabasesDir = file2;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    static ContextImpl getImpl(Context context)
    {
        do
        {
            if(!(context instanceof ContextWrapper))
                break;
            Context context1 = ((ContextWrapper)context).getBaseContext();
            if(context1 == null)
                break;
            context = context1;
        } while(true);
        return (ContextImpl)context;
    }

    private File getPreferencesDir()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File file1;
        if(mPreferencesDir == null)
        {
            File file = JVM INSTR new #443 <Class File>;
            file.File(getDataDir(), "shared_prefs");
            mPreferencesDir = file;
        }
        file1 = ensurePrivateDirExists(mPreferencesDir);
        obj;
        JVM INSTR monitorexit ;
        return file1;
        Exception exception;
        exception;
        throw exception;
    }

    private ArrayMap getSharedPreferencesCacheLocked()
    {
        if(sSharedPrefsCache == null)
            sSharedPrefsCache = new ArrayMap();
        String s = getPackageName();
        ArrayMap arraymap = (ArrayMap)sSharedPrefsCache.get(s);
        ArrayMap arraymap1 = arraymap;
        if(arraymap == null)
        {
            arraymap1 = new ArrayMap();
            sSharedPrefsCache.put(s, arraymap1);
        }
        return arraymap1;
    }

    private WallpaperManager getWallpaperManager()
    {
        return (WallpaperManager)getSystemService(android/app/WallpaperManager);
    }

    private void initializeTheme()
    {
        if(mTheme == null)
            mTheme = mResources.newTheme();
        mTheme.applyStyle(mThemeResource, true);
    }

    private File makeFilename(File file, String s)
    {
        if(s.indexOf(File.separatorChar) < 0)
            return new File(file, s);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("File ").append(s).append(" contains a path separator").toString());
    }

    private static int moveFiles(File file, File file1, String s)
    {
        int i;
        int j;
        int k;
        file = FileUtils.listFilesOrEmpty(file, new FilenameFilter(s) {

            public boolean accept(File file3, String s1)
            {
                return s1.startsWith(prefix);
            }

            final String val$prefix;

            
            {
                prefix = s;
                super();
            }
        }
);
        i = 0;
        j = 0;
        k = file.length;
_L6:
        if(j >= k) goto _L2; else goto _L1
_L1:
        File file2;
        s = file[j];
        file2 = new File(file1, s.getName());
        Log.d("ContextImpl", (new StringBuilder()).append("Migrating ").append(s).append(" to ").append(file2).toString());
        FileUtils.copyFileOrThrow(s, file2);
        FileUtils.copyPermissions(s, file2);
        if(!s.delete())
        {
            IOException ioexception1 = JVM INSTR new #635 <Class IOException>;
            StringBuilder stringbuilder = JVM INSTR new #266 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            ioexception1.IOException(stringbuilder.append("Failed to clean up ").append(s).toString());
            throw ioexception1;
        }
          goto _L3
        IOException ioexception;
        ioexception;
        int l;
        Log.w("ContextImpl", (new StringBuilder()).append("Failed to migrate ").append(s).append(": ").append(ioexception).toString());
        l = -1;
_L4:
        j++;
        i = l;
        continue; /* Loop/switch isn't completed */
_L3:
        l = i;
        if(i != -1)
            l = i + 1;
        if(true) goto _L4; else goto _L2
_L2:
        return i;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private Intent registerReceiverInternal(BroadcastReceiver broadcastreceiver, int i, IntentFilter intentfilter, String s, Handler handler, Context context, int j)
    {
        Object obj = null;
        if(broadcastreceiver != null)
            if(mPackageInfo != null && context != null)
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = mPackageInfo.getReceiverDispatcher(broadcastreceiver, context, ((Handler) (obj)), mMainThread.getInstrumentation(), true);
            } else
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = (new LoadedApk.ReceiverDispatcher(broadcastreceiver, context, ((Handler) (obj)), null, true)).getIIntentReceiver();
            }
        try
        {
            broadcastreceiver = ActivityManager.getService().registerReceiver(mMainThread.getApplicationThread(), mBasePackageName, ((android.content.IIntentReceiver) (obj)), intentfilter, s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(BroadcastReceiver broadcastreceiver)
        {
            throw broadcastreceiver.rethrowFromSystemServer();
        }
        if(broadcastreceiver == null)
            break MISSING_BLOCK_LABEL_103;
        broadcastreceiver.setExtrasClassLoader(getClassLoader());
        broadcastreceiver.prepareToEnterProcess();
        return broadcastreceiver;
    }

    private int resolveUserId(Uri uri)
    {
        return ContentProvider.getUserIdFromUri(uri, getUserId());
    }

    static void setFilePermissionsFromMode(String s, int i, int j)
    {
        int k = j | 0x1b0;
        j = k;
        if((i & 1) != 0)
            j = k | 4;
        k = j;
        if((i & 2) != 0)
            k = j | 2;
        FileUtils.setPermissions(s, k, -1, -1);
    }

    private ComponentName startServiceCommon(Intent intent, boolean flag, UserHandle userhandle)
    {
        SecurityException securityexception;
        StringBuilder stringbuilder2;
        try
        {
            validateServiceIntent(intent);
            intent.prepareToLeaveProcess(this);
            userhandle = ActivityManager.getService().startService(mMainThread.getApplicationThread(), intent, intent.resolveTypeIfNeeded(getContentResolver()), flag, getOpPackageName(), userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(userhandle == null)
            break MISSING_BLOCK_LABEL_246;
        if(userhandle.getPackageName().equals("!"))
        {
            securityexception = JVM INSTR new #264 <Class SecurityException>;
            stringbuilder2 = JVM INSTR new #266 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            securityexception.SecurityException(stringbuilder2.append("Not allowed to start service ").append(intent).append(" without permission ").append(userhandle.getClassName()).toString());
            throw securityexception;
        }
        if(userhandle.getPackageName().equals("!!"))
        {
            SecurityException securityexception1 = JVM INSTR new #264 <Class SecurityException>;
            StringBuilder stringbuilder = JVM INSTR new #266 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            securityexception1.SecurityException(stringbuilder.append("Unable to start service ").append(intent).append(": ").append(userhandle.getClassName()).toString());
            throw securityexception1;
        }
        if(userhandle.getPackageName().equals("?"))
        {
            IllegalStateException illegalstateexception = JVM INSTR new #744 <Class IllegalStateException>;
            StringBuilder stringbuilder1 = JVM INSTR new #266 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            illegalstateexception.IllegalStateException(stringbuilder1.append("Not allowed to start service ").append(intent).append(": ").append(userhandle.getClassName()).toString());
            throw illegalstateexception;
        }
        return userhandle;
    }

    private boolean stopServiceCommon(Intent intent, UserHandle userhandle)
    {
        boolean flag;
        int i;
        flag = false;
        StringBuilder stringbuilder;
        try
        {
            validateServiceIntent(intent);
            intent.prepareToLeaveProcess(this);
            i = ActivityManager.getService().stopService(mMainThread.getApplicationThread(), intent, intent.resolveTypeIfNeeded(getContentResolver()), userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(i >= 0)
            break MISSING_BLOCK_LABEL_88;
        userhandle = JVM INSTR new #264 <Class SecurityException>;
        stringbuilder = JVM INSTR new #266 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        userhandle.SecurityException(stringbuilder.append("Not allowed to stop service ").append(intent).toString());
        throw userhandle;
        if(i != 0)
            flag = true;
        return flag;
    }

    private String uriModeFlagToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if((i & 1) != 0)
            stringbuilder.append("read and ");
        if((i & 2) != 0)
            stringbuilder.append("write and ");
        if((i & 0x40) != 0)
            stringbuilder.append("persistable and ");
        if((i & 0x80) != 0)
            stringbuilder.append("prefix and ");
        if(stringbuilder.length() > 5)
        {
            stringbuilder.setLength(stringbuilder.length() - 5);
            return stringbuilder.toString();
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown permission mode flags: ").append(i).toString());
        }
    }

    private void validateServiceIntent(Intent intent)
    {
        if(intent.getComponent() == null && intent.getPackage() == null)
        {
            if(getApplicationInfo().targetSdkVersion >= 21)
                throw new IllegalArgumentException((new StringBuilder()).append("Service Intent must be explicit: ").append(intent).toString());
            Log.w("ContextImpl", (new StringBuilder()).append("Implicit intents with startService are not safe: ").append(intent).append(" ").append(Debug.getCallers(2, 3)).toString());
        }
    }

    private void warnIfCallingFromSystemProcess()
    {
        if(ActivityThread.isSystem())
            Slog.w("ContextImpl", (new StringBuilder()).append("Calling a method in the system process without a qualified user: ").append(Debug.getCallers(5)).toString());
    }

    public boolean bindService(Intent intent, ServiceConnection serviceconnection, int i)
    {
        warnIfCallingFromSystemProcess();
        return bindServiceCommon(intent, serviceconnection, i, mMainThread.getHandler(), Process.myUserHandle());
    }

    public boolean bindServiceAsUser(Intent intent, ServiceConnection serviceconnection, int i, Handler handler, UserHandle userhandle)
    {
        if(handler == null)
            throw new IllegalArgumentException("handler must not be null.");
        else
            return bindServiceCommon(intent, serviceconnection, i, handler, userhandle);
    }

    public boolean bindServiceAsUser(Intent intent, ServiceConnection serviceconnection, int i, UserHandle userhandle)
    {
        return bindServiceCommon(intent, serviceconnection, i, mMainThread.getHandler(), userhandle);
    }

    public boolean canLoadUnsafeResources()
    {
        boolean flag = true;
        if(getPackageName().equals(getOpPackageName()))
            return true;
        if((mFlags & 2) == 0)
            flag = false;
        return flag;
    }

    public int checkCallingOrSelfPermission(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("permission is null");
        else
            return checkPermission(s, Binder.getCallingPid(), Binder.getCallingUid());
    }

    public int checkCallingOrSelfUriPermission(Uri uri, int i)
    {
        return checkUriPermission(uri, Binder.getCallingPid(), Binder.getCallingUid(), i);
    }

    public int checkCallingPermission(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("permission is null");
        int i = Binder.getCallingPid();
        if(i != Process.myPid())
            return checkPermission(s, i, Binder.getCallingUid());
        else
            return -1;
    }

    public int checkCallingUriPermission(Uri uri, int i)
    {
        int j = Binder.getCallingPid();
        if(j != Process.myPid())
            return checkUriPermission(uri, j, Binder.getCallingUid(), i);
        else
            return -1;
    }

    public int checkPermission(String s, int i, int j)
    {
        if(s == null)
            throw new IllegalArgumentException("permission is null");
        IActivityManager iactivitymanager = ActivityManager.getService();
        if(iactivitymanager == null)
        {
            int k = UserHandle.getAppId(j);
            if(k == 0 || k == 1000)
            {
                Slog.w("ContextImpl", (new StringBuilder()).append("Missing ActivityManager; assuming ").append(j).append(" holds ").append(s).toString());
                return 0;
            }
        }
        try
        {
            i = iactivitymanager.checkPermission(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkPermission(String s, int i, int j, IBinder ibinder)
    {
        if(s == null)
            throw new IllegalArgumentException("permission is null");
        try
        {
            i = ActivityManager.getService().checkPermissionWithToken(s, i, j, ibinder);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkSelfPermission(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("permission is null");
        else
            return checkPermission(s, Process.myPid(), Process.myUid());
    }

    public int checkUriPermission(Uri uri, int i, int j, int k)
    {
        try
        {
            i = ActivityManager.getService().checkUriPermission(ContentProvider.getUriWithoutUserId(uri), i, j, k, resolveUserId(uri), null);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkUriPermission(Uri uri, int i, int j, int k, IBinder ibinder)
    {
        try
        {
            i = ActivityManager.getService().checkUriPermission(ContentProvider.getUriWithoutUserId(uri), i, j, k, resolveUserId(uri), ibinder);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkUriPermission(Uri uri, String s, String s1, int i, int j, int k)
    {
        if((k & 1) != 0 && (s == null || checkPermission(s, i, j) == 0))
            return 0;
        if((k & 2) != 0 && (s1 == null || checkPermission(s1, i, j) == 0))
            return 0;
        if(uri != null)
            i = checkUriPermission(uri, i, j, k);
        else
            i = -1;
        return i;
    }

    public void clearWallpaper()
        throws IOException
    {
        getWallpaperManager().clear();
    }

    public Context createApplicationContext(ApplicationInfo applicationinfo, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        LoadedApk loadedapk = mMainThread.getPackageInfo(applicationinfo, mResources.getCompatibilityInfo(), 0x40000000 | i);
        if(loadedapk != null)
        {
            ContextImpl contextimpl = new ContextImpl(this, mMainThread, loadedapk, null, mActivityToken, new UserHandle(UserHandle.getUserId(applicationinfo.uid)), i, null);
            if(mDisplay != null)
                i = mDisplay.getDisplayId();
            else
                i = 0;
            contextimpl.setResources(createResources(mActivityToken, loadedapk, null, i, null, getDisplayAdjustments(i).getCompatibilityInfo()));
            if(contextimpl.mResources != null)
                return contextimpl;
        }
        throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("Application package ").append(applicationinfo.packageName).append(" not found").toString());
    }

    public Context createConfigurationContext(Configuration configuration)
    {
        if(configuration == null)
            throw new IllegalArgumentException("overrideConfiguration must not be null");
        ContextImpl contextimpl = new ContextImpl(this, mMainThread, mPackageInfo, mSplitName, mActivityToken, mUser, mFlags, mClassLoader);
        int i;
        if(mDisplay != null)
            i = mDisplay.getDisplayId();
        else
            i = 0;
        contextimpl.setResources(createResources(mActivityToken, mPackageInfo, mSplitName, i, configuration, getDisplayAdjustments(i).getCompatibilityInfo()));
        return contextimpl;
    }

    public Context createContextForSplit(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(!mPackageInfo.getApplicationInfo().requestsIsolatedSplitLoading())
            return this;
        ClassLoader classloader = mPackageInfo.getSplitClassLoader(s);
        String as[] = mPackageInfo.getSplitPaths(s);
        s = new ContextImpl(this, mMainThread, mPackageInfo, s, mActivityToken, mUser, mFlags, classloader);
        int i;
        if(mDisplay != null)
            i = mDisplay.getDisplayId();
        else
            i = 0;
        s.setResources(ResourcesManager.getInstance().getResources(mActivityToken, mPackageInfo.getResDir(), as, mPackageInfo.getOverlayDirs(), mPackageInfo.getApplicationInfo().sharedLibraryFiles, i, null, mPackageInfo.getCompatibilityInfo(), classloader));
        return s;
    }

    public Context createCredentialProtectedStorageContext()
    {
        int i = mFlags;
        return new ContextImpl(this, mMainThread, mPackageInfo, mSplitName, mActivityToken, mUser, i & -9 | 0x10, mClassLoader);
    }

    public Context createDeviceProtectedStorageContext()
    {
        int i = mFlags;
        return new ContextImpl(this, mMainThread, mPackageInfo, mSplitName, mActivityToken, mUser, i & 0xffffffef | 8, mClassLoader);
    }

    public Context createDisplayContext(Display display)
    {
        if(display == null)
        {
            throw new IllegalArgumentException("display must not be null");
        } else
        {
            ContextImpl contextimpl = new ContextImpl(this, mMainThread, mPackageInfo, mSplitName, mActivityToken, mUser, mFlags, mClassLoader);
            int i = display.getDisplayId();
            contextimpl.setResources(createResources(mActivityToken, mPackageInfo, mSplitName, i, null, getDisplayAdjustments(i).getCompatibilityInfo()));
            contextimpl.mDisplay = display;
            return contextimpl;
        }
    }

    public Context createPackageContext(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        UserHandle userhandle;
        if(mUser != null)
            userhandle = mUser;
        else
            userhandle = Process.myUserHandle();
        return createPackageContextAsUser(s, i, userhandle);
    }

    public Context createPackageContextAsUser(String s, int i, UserHandle userhandle)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(s.equals("system") || s.equals("android"))
            return new ContextImpl(this, mMainThread, mPackageInfo, null, mActivityToken, userhandle, i, null);
        LoadedApk loadedapk = mMainThread.getPackageInfo(s, mResources.getCompatibilityInfo(), 0x40000000 | i, userhandle.getIdentifier());
        if(loadedapk != null)
        {
            userhandle = new ContextImpl(this, mMainThread, loadedapk, null, mActivityToken, userhandle, i, null);
            if(mDisplay != null)
                i = mDisplay.getDisplayId();
            else
                i = 0;
            userhandle.setResources(createResources(mActivityToken, loadedapk, null, i, null, getDisplayAdjustments(i).getCompatibilityInfo()));
            if(((ContextImpl) (userhandle)).mResources != null)
                return userhandle;
        }
        throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("Application package ").append(s).append(" not found").toString());
    }

    public String[] databaseList()
    {
        return FileUtils.listOrEmpty(getDatabasesDir());
    }

    public boolean deleteDatabase(String s)
    {
        boolean flag;
        try
        {
            flag = SQLiteDatabase.deleteDatabase(getDatabasePath(s));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    public boolean deleteFile(String s)
    {
        return makeFilename(getFilesDir(), s).delete();
    }

    public boolean deleteSharedPreferences(String s)
    {
        android/app/ContextImpl;
        JVM INSTR monitorenter ;
        File file;
        file = getSharedPreferencesPath(s);
        s = SharedPreferencesImpl.makeBackupFile(file);
        getSharedPreferencesCacheLocked().remove(file);
        file.delete();
        s.delete();
        if(file.exists()) goto _L2; else goto _L1
_L1:
        boolean flag = s.exists();
_L4:
        android/app/ContextImpl;
        JVM INSTR monitorexit ;
        return flag ^ true;
_L2:
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        s;
        throw s;
    }

    public void enforceCallingOrSelfPermission(String s, String s1)
    {
        enforce(s, checkCallingOrSelfPermission(s), true, Binder.getCallingUid(), s1);
    }

    public void enforceCallingOrSelfUriPermission(Uri uri, int i, String s)
    {
        enforceForUri(i, checkCallingOrSelfUriPermission(uri, i), true, Binder.getCallingUid(), uri, s);
    }

    public void enforceCallingPermission(String s, String s1)
    {
        enforce(s, checkCallingPermission(s), false, Binder.getCallingUid(), s1);
    }

    public void enforceCallingUriPermission(Uri uri, int i, String s)
    {
        enforceForUri(i, checkCallingUriPermission(uri, i), false, Binder.getCallingUid(), uri, s);
    }

    public void enforcePermission(String s, int i, int j, String s1)
    {
        enforce(s, checkPermission(s, i, j), false, j, s1);
    }

    public void enforceUriPermission(Uri uri, int i, int j, int k, String s)
    {
        enforceForUri(k, checkUriPermission(uri, i, j, k), false, j, uri, s);
    }

    public void enforceUriPermission(Uri uri, String s, String s1, int i, int j, int k, String s2)
    {
        enforceForUri(k, checkUriPermission(uri, s, s1, i, j, k), false, j, uri, s2);
    }

    public String[] fileList()
    {
        return FileUtils.listOrEmpty(getFilesDir());
    }

    public IBinder getActivityToken()
    {
        return mActivityToken;
    }

    public Context getApplicationContext()
    {
        Application application;
        if(mPackageInfo != null)
            application = mPackageInfo.getApplication();
        else
            application = mMainThread.getApplication();
        return application;
    }

    public ApplicationInfo getApplicationInfo()
    {
        if(mPackageInfo != null)
            return mPackageInfo.getApplicationInfo();
        else
            throw new RuntimeException("Not supported in system context");
    }

    public AssetManager getAssets()
    {
        return getResources().getAssets();
    }

    public android.view.autofill.AutofillManager.AutofillClient getAutofillClient()
    {
        return mAutofillClient;
    }

    public String getBasePackageName()
    {
        String s;
        if(mBasePackageName != null)
            s = mBasePackageName;
        else
            s = getPackageName();
        return s;
    }

    public File getCacheDir()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File file1;
        if(mCacheDir == null)
        {
            File file = JVM INSTR new #443 <Class File>;
            file.File(getDataDir(), "cache");
            mCacheDir = file;
        }
        file1 = ensurePrivateCacheDirExists(mCacheDir, "user.inode_cache");
        obj;
        JVM INSTR monitorexit ;
        return file1;
        Exception exception;
        exception;
        throw exception;
    }

    public ClassLoader getClassLoader()
    {
        ClassLoader classloader;
        if(mClassLoader != null)
            classloader = mClassLoader;
        else
        if(mPackageInfo != null)
            classloader = mPackageInfo.getClassLoader();
        else
            classloader = ClassLoader.getSystemClassLoader();
        return classloader;
    }

    public File getCodeCacheDir()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File file1;
        if(mCodeCacheDir == null)
        {
            File file = JVM INSTR new #443 <Class File>;
            file.File(getDataDir(), "code_cache");
            mCodeCacheDir = file;
        }
        file1 = ensurePrivateCacheDirExists(mCodeCacheDir, "user.inode_code_cache");
        obj;
        JVM INSTR monitorexit ;
        return file1;
        Exception exception;
        exception;
        throw exception;
    }

    public ContentResolver getContentResolver()
    {
        return mContentResolver;
    }

    public ContentResolver getContentResolverForUser(UserHandle userhandle)
    {
        return new ApplicationContentResolver(this, mMainThread, userhandle);
    }

    public File getDataDir()
    {
        if(mPackageInfo != null)
        {
            File file;
            if(isCredentialProtectedStorage())
                file = mPackageInfo.getCredentialProtectedDataDirFile();
            else
            if(isDeviceProtectedStorage())
                file = mPackageInfo.getDeviceProtectedDataDirFile();
            else
                file = mPackageInfo.getDataDirFile();
            if(file != null)
            {
                if(!file.exists() && Process.myUid() == 1000)
                    Log.wtf("ContextImpl", (new StringBuilder()).append("Data directory doesn't exist for package ").append(getPackageName()).toString(), new Throwable());
                return file;
            } else
            {
                throw new RuntimeException((new StringBuilder()).append("No data directory found for package ").append(getPackageName()).toString());
            }
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("No package details found for package ").append(getPackageName()).toString());
        }
    }

    public File getDatabasePath(String s)
    {
        if(s.charAt(0) == File.separatorChar)
        {
            File file = new File(s.substring(0, s.lastIndexOf(File.separatorChar)));
            File file1 = new File(file, s.substring(s.lastIndexOf(File.separatorChar)));
            s = file1;
            if(!file.isDirectory())
            {
                s = file1;
                if(file.mkdir())
                {
                    FileUtils.setPermissions(file.getPath(), 505, -1, -1);
                    s = file1;
                }
            }
        } else
        {
            s = makeFilename(getDatabasesDir(), s);
        }
        return s;
    }

    public File getDir(String s, int i)
    {
        checkMode(i);
        s = (new StringBuilder()).append("app_").append(s).toString();
        s = makeFilename(getDataDir(), s);
        if(!s.exists())
        {
            s.mkdir();
            setFilePermissionsFromMode(s.getPath(), i, 505);
        }
        return s;
    }

    public Display getDisplay()
    {
        if(mDisplay == null)
            return mResourcesManager.getAdjustedDisplay(0, mResources);
        else
            return mDisplay;
    }

    public DisplayAdjustments getDisplayAdjustments(int i)
    {
        return mResources.getDisplayAdjustments();
    }

    public File getExternalCacheDir()
    {
        Object obj = null;
        File afile[] = getExternalCacheDirs();
        File file = obj;
        if(afile != null)
        {
            file = obj;
            if(afile.length > 0)
                file = afile[0];
        }
        return file;
    }

    public File[] getExternalCacheDirs()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File afile[] = ensureExternalDirsExistOrFilter(Environment.buildExternalStorageAppCacheDirs(getPackageName()));
        obj;
        JVM INSTR monitorexit ;
        return afile;
        Exception exception;
        exception;
        throw exception;
    }

    public File getExternalFilesDir(String s)
    {
        Object obj = null;
        File afile[] = getExternalFilesDirs(s);
        s = obj;
        if(afile != null)
        {
            s = obj;
            if(afile.length > 0)
                s = afile[0];
        }
        return s;
    }

    public File[] getExternalFilesDirs(String s)
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File afile[] = Environment.buildExternalStorageAppFilesDirs(getPackageName());
        File afile1[];
        afile1 = afile;
        if(s == null)
            break MISSING_BLOCK_LABEL_36;
        afile1 = Environment.buildPaths(afile, new String[] {
            s
        });
        s = ensureExternalDirsExistOrFilter(afile1);
        obj;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public File[] getExternalMediaDirs()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File afile[] = ensureExternalDirsExistOrFilter(Environment.buildExternalStorageAppMediaDirs(getPackageName()));
        obj;
        JVM INSTR monitorexit ;
        return afile;
        Exception exception;
        exception;
        throw exception;
    }

    public File getFileStreamPath(String s)
    {
        return makeFilename(getFilesDir(), s);
    }

    public File getFilesDir()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File file1;
        if(mFilesDir == null)
        {
            File file = JVM INSTR new #443 <Class File>;
            file.File(getDataDir(), "files");
            mFilesDir = file;
        }
        file1 = ensurePrivateDirExists(mFilesDir);
        obj;
        JVM INSTR monitorexit ;
        return file1;
        Exception exception;
        exception;
        throw exception;
    }

    public IApplicationThread getIApplicationThread()
    {
        return mMainThread.getApplicationThread();
    }

    public Looper getMainLooper()
    {
        return mMainThread.getLooper();
    }

    public Handler getMainThreadHandler()
    {
        return mMainThread.getHandler();
    }

    public File getNoBackupFilesDir()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File file1;
        if(mNoBackupFilesDir == null)
        {
            File file = JVM INSTR new #443 <Class File>;
            file.File(getDataDir(), "no_backup");
            mNoBackupFilesDir = file;
        }
        file1 = ensurePrivateDirExists(mNoBackupFilesDir);
        obj;
        JVM INSTR monitorexit ;
        return file1;
        Exception exception;
        exception;
        throw exception;
    }

    public File getObbDir()
    {
        Object obj = null;
        File afile[] = getObbDirs();
        File file = obj;
        if(afile != null)
        {
            file = obj;
            if(afile.length > 0)
                file = afile[0];
        }
        return file;
    }

    public File[] getObbDirs()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        File afile[] = ensureExternalDirsExistOrFilter(Environment.buildExternalStorageAppObbDirs(getPackageName()));
        obj;
        JVM INSTR monitorexit ;
        return afile;
        Exception exception;
        exception;
        throw exception;
    }

    public String getOpPackageName()
    {
        String s;
        if(mOpPackageName != null)
            s = mOpPackageName;
        else
            s = getBasePackageName();
        return s;
    }

    final Context getOuterContext()
    {
        return mOuterContext;
    }

    public String getPackageCodePath()
    {
        if(mPackageInfo != null)
            return mPackageInfo.getAppDir();
        else
            throw new RuntimeException("Not supported in system context");
    }

    public PackageManager getPackageManager()
    {
        if(mPackageManager != null)
            return mPackageManager;
        Object obj = ActivityThread.getPackageManager();
        if(obj != null)
        {
            obj = new ApplicationPackageManager(this, ((android.content.pm.IPackageManager) (obj)));
            mPackageManager = ((PackageManager) (obj));
            return ((PackageManager) (obj));
        } else
        {
            return null;
        }
    }

    public String getPackageName()
    {
        if(mPackageInfo != null)
            return mPackageInfo.getPackageName();
        else
            return "android";
    }

    public String getPackageResourcePath()
    {
        if(mPackageInfo != null)
            return mPackageInfo.getResDir();
        else
            throw new RuntimeException("Not supported in system context");
    }

    public File getPreloadsFileCache()
    {
        return Environment.getDataPreloadsFileCacheDirectory(getPackageName());
    }

    final Context getReceiverRestrictedContext()
    {
        if(mReceiverRestrictedContext != null)
        {
            return mReceiverRestrictedContext;
        } else
        {
            ReceiverRestrictedContext receiverrestrictedcontext = new ReceiverRestrictedContext(getOuterContext());
            mReceiverRestrictedContext = receiverrestrictedcontext;
            return receiverrestrictedcontext;
        }
    }

    public Resources getResources()
    {
        return mResources;
    }

    public IServiceConnection getServiceDispatcher(ServiceConnection serviceconnection, Handler handler, int i)
    {
        return mPackageInfo.getServiceDispatcher(serviceconnection, getOuterContext(), handler, i);
    }

    public SharedPreferences getSharedPreferences(File file, int i)
    {
        android/app/ContextImpl;
        JVM INSTR monitorenter ;
        ArrayMap arraymap;
        SharedPreferencesImpl sharedpreferencesimpl;
        arraymap = getSharedPreferencesCacheLocked();
        sharedpreferencesimpl = (SharedPreferencesImpl)arraymap.get(file);
        if(sharedpreferencesimpl != null)
            break MISSING_BLOCK_LABEL_113;
        checkMode(i);
        if(getApplicationInfo().targetSdkVersion >= 26 && isCredentialProtectedStorage() && ((UserManager)getSystemService(android/os/UserManager)).isUserUnlockingOrUnlocked(UserHandle.myUserId()) ^ true)
        {
            file = JVM INSTR new #744 <Class IllegalStateException>;
            file.IllegalStateException("SharedPreferences in credential encrypted storage are not available until after user is unlocked");
            throw file;
        }
        break MISSING_BLOCK_LABEL_87;
        file;
        android/app/ContextImpl;
        JVM INSTR monitorexit ;
        throw file;
        sharedpreferencesimpl = JVM INSTR new #953 <Class SharedPreferencesImpl>;
        sharedpreferencesimpl.SharedPreferencesImpl(file, i);
        arraymap.put(file, sharedpreferencesimpl);
        android/app/ContextImpl;
        JVM INSTR monitorexit ;
        return sharedpreferencesimpl;
        android/app/ContextImpl;
        JVM INSTR monitorexit ;
        if((i & 4) != 0 || getApplicationInfo().targetSdkVersion < 11)
            sharedpreferencesimpl.startReloadIfChangedUnexpectedly();
        return sharedpreferencesimpl;
    }

    public SharedPreferences getSharedPreferences(String s, int i)
    {
        String s1;
        s1 = s;
        if(mPackageInfo.getApplicationInfo().targetSdkVersion < 19)
        {
            s1 = s;
            if(s == null)
                s1 = "null";
        }
        android/app/ContextImpl;
        JVM INSTR monitorenter ;
        File file;
        if(mSharedPrefsPaths == null)
        {
            s = JVM INSTR new #589 <Class ArrayMap>;
            s.ArrayMap();
            mSharedPrefsPaths = s;
        }
        file = (File)mSharedPrefsPaths.get(s1);
        s = file;
        if(file != null)
            break MISSING_BLOCK_LABEL_87;
        s = getSharedPreferencesPath(s1);
        mSharedPrefsPaths.put(s1, s);
        android/app/ContextImpl;
        JVM INSTR monitorexit ;
        return getSharedPreferences(((File) (s)), i);
        s;
        throw s;
    }

    public File getSharedPreferencesPath(String s)
    {
        return makeFilename(getPreferencesDir(), (new StringBuilder()).append(s).append(".xml").toString());
    }

    public Object getSystemService(String s)
    {
        return SystemServiceRegistry.getSystemService(this, s);
    }

    public String getSystemServiceName(Class class1)
    {
        return SystemServiceRegistry.getSystemServiceName(class1);
    }

    public android.content.res.Resources.Theme getTheme()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        android.content.res.Resources.Theme theme;
        if(mTheme == null)
            break MISSING_BLOCK_LABEL_23;
        theme = mTheme;
        obj;
        JVM INSTR monitorexit ;
        return theme;
        mThemeResource = Resources.selectDefaultTheme(mThemeResource, getOuterContext().getApplicationInfo().targetSdkVersion);
        initializeTheme();
        theme = mTheme;
        obj;
        JVM INSTR monitorexit ;
        return theme;
        Exception exception;
        exception;
        throw exception;
    }

    public int getThemeResId()
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        int i = mThemeResource;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getUserId()
    {
        return mUser.getIdentifier();
    }

    public Drawable getWallpaper()
    {
        return getWallpaperManager().getDrawable();
    }

    public int getWallpaperDesiredMinimumHeight()
    {
        return getWallpaperManager().getDesiredMinimumHeight();
    }

    public int getWallpaperDesiredMinimumWidth()
    {
        return getWallpaperManager().getDesiredMinimumWidth();
    }

    public void grantUriPermission(String s, Uri uri, int i)
    {
        try
        {
            ActivityManager.getService().grantUriPermission(mMainThread.getApplicationThread(), s, ContentProvider.getUriWithoutUserId(uri), i, resolveUserId(uri));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    void installSystemApplicationInfo(ApplicationInfo applicationinfo, ClassLoader classloader)
    {
        mPackageInfo.installSystemApplicationInfo(applicationinfo, classloader);
    }

    public boolean isCredentialProtectedStorage()
    {
        boolean flag = false;
        if((mFlags & 0x10) != 0)
            flag = true;
        return flag;
    }

    public boolean isDeviceProtectedStorage()
    {
        boolean flag = false;
        if((mFlags & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean isRestricted()
    {
        boolean flag = false;
        if((mFlags & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean moveDatabaseFrom(Context context, String s)
    {
        android/app/ContextImpl;
        JVM INSTR monitorenter ;
        int i;
        context = context.getDatabasePath(s);
        s = getDatabasePath(s);
        i = moveFiles(context.getParentFile(), s.getParentFile(), context.getName());
        boolean flag;
        if(i != -1)
            flag = true;
        else
            flag = false;
        android/app/ContextImpl;
        JVM INSTR monitorexit ;
        return flag;
        context;
        throw context;
    }

    public boolean moveSharedPreferencesFrom(Context context, String s)
    {
        boolean flag = false;
        android/app/ContextImpl;
        JVM INSTR monitorenter ;
        int i;
        context = context.getSharedPreferencesPath(s);
        s = getSharedPreferencesPath(s);
        i = moveFiles(context.getParentFile(), s.getParentFile(), context.getName());
        if(i <= 0)
            break MISSING_BLOCK_LABEL_59;
        ArrayMap arraymap = getSharedPreferencesCacheLocked();
        arraymap.remove(context);
        arraymap.remove(s);
        if(i != -1)
            flag = true;
        android/app/ContextImpl;
        JVM INSTR monitorexit ;
        return flag;
        context;
        throw context;
    }

    public FileInputStream openFileInput(String s)
        throws FileNotFoundException
    {
        return new FileInputStream(makeFilename(getFilesDir(), s));
    }

    public FileOutputStream openFileOutput(String s, int i)
        throws FileNotFoundException
    {
        checkMode(i);
        boolean flag;
        FileOutputStream fileoutputstream;
        if((0x8000 & i) != 0)
            flag = true;
        else
            flag = false;
        s = makeFilename(getFilesDir(), s);
        try
        {
            fileoutputstream = JVM INSTR new #1246 <Class FileOutputStream>;
            fileoutputstream.FileOutputStream(s, flag);
            setFilePermissionsFromMode(s.getPath(), i, 0);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Object obj = s.getParentFile();
            ((File) (obj)).mkdir();
            FileUtils.setPermissions(((File) (obj)).getPath(), 505, -1, -1);
            obj = new FileOutputStream(s, flag);
            setFilePermissionsFromMode(s.getPath(), i, 0);
            return ((FileOutputStream) (obj));
        }
        return fileoutputstream;
    }

    public SQLiteDatabase openOrCreateDatabase(String s, int i, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory)
    {
        return openOrCreateDatabase(s, i, cursorfactory, null);
    }

    public SQLiteDatabase openOrCreateDatabase(String s, int i, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, DatabaseErrorHandler databaseerrorhandler)
    {
        checkMode(i);
        s = getDatabasePath(s);
        int j = 0x10000000;
        if((i & 8) != 0)
            j = 0x30000000;
        int k = j;
        if((i & 0x10) != 0)
            k = j | 0x10;
        cursorfactory = SQLiteDatabase.openDatabase(s.getPath(), cursorfactory, k, databaseerrorhandler);
        setFilePermissionsFromMode(s.getPath(), i, 0);
        return cursorfactory;
    }

    public Drawable peekWallpaper()
    {
        return getWallpaperManager().peekDrawable();
    }

    final void performFinalCleanup(String s, String s1)
    {
        mPackageInfo.removeContextRegistrations(getOuterContext(), s, s1);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter)
    {
        return registerReceiver(broadcastreceiver, intentfilter, null, null);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, int i)
    {
        return registerReceiver(broadcastreceiver, intentfilter, null, null, i);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, String s, Handler handler)
    {
        return registerReceiverInternal(broadcastreceiver, getUserId(), intentfilter, s, handler, getOuterContext(), 0);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, String s, Handler handler, int i)
    {
        return registerReceiverInternal(broadcastreceiver, getUserId(), intentfilter, s, handler, getOuterContext(), i);
    }

    public Intent registerReceiverAsUser(BroadcastReceiver broadcastreceiver, UserHandle userhandle, IntentFilter intentfilter, String s, Handler handler)
    {
        return registerReceiverInternal(broadcastreceiver, userhandle.getIdentifier(), intentfilter, s, handler, getOuterContext(), 0);
    }

    public void reloadSharedPreferences()
    {
        ArrayList arraylist = new ArrayList();
        android/app/ContextImpl;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = getSharedPreferencesCacheLocked();
        int i = 0;
_L2:
        SharedPreferencesImpl sharedpreferencesimpl;
        if(i >= arraymap.size())
            break; /* Loop/switch isn't completed */
        sharedpreferencesimpl = (SharedPreferencesImpl)arraymap.valueAt(i);
        if(sharedpreferencesimpl == null)
            break MISSING_BLOCK_LABEL_48;
        arraylist.add(sharedpreferencesimpl);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        for(int j = 0; j < arraylist.size(); j++)
            ((SharedPreferencesImpl)arraylist.get(j)).startReloadIfChangedUnexpectedly();

        break MISSING_BLOCK_LABEL_90;
        Exception exception;
        exception;
        throw exception;
    }

    public void removeStickyBroadcast(Intent intent)
    {
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        Intent intent1 = intent;
        if(s != null)
        {
            intent1 = new Intent(intent);
            intent1.setDataAndType(intent1.getData(), s);
        }
        try
        {
            intent1.prepareToLeaveProcess(this);
            ActivityManager.getService().unbroadcastIntent(mMainThread.getApplicationThread(), intent1, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void removeStickyBroadcastAsUser(Intent intent, UserHandle userhandle)
    {
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        Intent intent1 = intent;
        if(s != null)
        {
            intent1 = new Intent(intent);
            intent1.setDataAndType(intent1.getData(), s);
        }
        try
        {
            intent1.prepareToLeaveProcess(this);
            ActivityManager.getService().unbroadcastIntent(mMainThread.getApplicationThread(), intent1, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void revokeUriPermission(Uri uri, int i)
    {
        try
        {
            ActivityManager.getService().revokeUriPermission(mMainThread.getApplicationThread(), null, ContentProvider.getUriWithoutUserId(uri), i, resolveUserId(uri));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
    }

    public void revokeUriPermission(String s, Uri uri, int i)
    {
        try
        {
            ActivityManager.getService().revokeUriPermission(mMainThread.getApplicationThread(), s, ContentProvider.getUriWithoutUserId(uri), i, resolveUserId(uri));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    final void scheduleFinalCleanup(String s, String s1)
    {
        mMainThread.scheduleContextCleanup(this, s, s1);
    }

    public void sendBroadcast(Intent intent)
    {
        warnIfCallingFromSystemProcess();
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s, null, -1, null, null, null, -1, null, false, false, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendBroadcast(Intent intent, String s)
    {
        warnIfCallingFromSystemProcess();
        String s1 = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            String as[] = new String[1];
            as[0] = s;
            s = as;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s1, null, -1, null, null, s, -1, null, false, false, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendBroadcast(Intent intent, String s, int i)
    {
        warnIfCallingFromSystemProcess();
        String s1 = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            String as[] = new String[1];
            as[0] = s;
            s = as;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s1, null, -1, null, null, s, i, null, false, false, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendBroadcast(Intent intent, String s, Bundle bundle)
    {
        warnIfCallingFromSystemProcess();
        String s1 = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            String as[] = new String[1];
            as[0] = s;
            s = as;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s1, null, -1, null, null, s, -1, bundle, false, false, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle)
    {
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s, null, -1, null, null, null, -1, null, false, false, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s)
    {
        sendBroadcastAsUser(intent, userhandle, s, -1);
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i)
    {
        String s1 = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            String as[] = new String[1];
            as[0] = s;
            s = as;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s1, null, -1, null, null, s, i, null, false, false, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s, Bundle bundle)
    {
        String s1 = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            String as[] = new String[1];
            as[0] = s;
            s = as;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s1, null, -1, null, null, s, -1, bundle, false, false, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendBroadcastMultiplePermissions(Intent intent, String as[])
    {
        warnIfCallingFromSystemProcess();
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s, null, -1, null, null, as, -1, null, false, false, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendOrderedBroadcast(Intent intent, String s)
    {
        warnIfCallingFromSystemProcess();
        String s1 = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            String as[] = new String[1];
            as[0] = s;
            s = as;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s1, null, -1, null, null, s, -1, null, true, false, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendOrderedBroadcast(Intent intent, String s, int i, BroadcastReceiver broadcastreceiver, Handler handler, int j, String s1, 
            Bundle bundle)
    {
        sendOrderedBroadcast(intent, s, i, broadcastreceiver, handler, j, s1, bundle, null);
    }

    void sendOrderedBroadcast(Intent intent, String s, int i, BroadcastReceiver broadcastreceiver, Handler handler, int j, String s1, 
            Bundle bundle, Bundle bundle1)
    {
        warnIfCallingFromSystemProcess();
        Object obj = null;
        if(broadcastreceiver != null)
            if(mPackageInfo != null)
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = mPackageInfo.getReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), mMainThread.getInstrumentation(), false);
            } else
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = (new LoadedApk.ReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), null, false)).getIIntentReceiver();
            }
        handler = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            broadcastreceiver = new String[1];
            broadcastreceiver[0] = s;
            s = broadcastreceiver;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, handler, ((android.content.IIntentReceiver) (obj)), j, s1, bundle, s, i, bundle1, true, false, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendOrderedBroadcast(Intent intent, String s, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, Bundle bundle)
    {
        sendOrderedBroadcast(intent, s, -1, broadcastreceiver, handler, i, s1, bundle, null);
    }

    public void sendOrderedBroadcast(Intent intent, String s, Bundle bundle, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, 
            Bundle bundle1)
    {
        sendOrderedBroadcast(intent, s, -1, broadcastreceiver, handler, i, s1, bundle1, bundle);
    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i, BroadcastReceiver broadcastreceiver, Handler handler, int j, 
            String s1, Bundle bundle)
    {
        sendOrderedBroadcastAsUser(intent, userhandle, s, i, null, broadcastreceiver, handler, j, s1, bundle);
    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i, Bundle bundle, BroadcastReceiver broadcastreceiver, Handler handler, 
            int j, String s1, Bundle bundle1)
    {
        Object obj = null;
        if(broadcastreceiver != null)
            if(mPackageInfo != null)
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = mPackageInfo.getReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), mMainThread.getInstrumentation(), false);
            } else
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = (new LoadedApk.ReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), null, false)).getIIntentReceiver();
            }
        handler = intent.resolveTypeIfNeeded(getContentResolver());
        if(s == null)
        {
            s = null;
        } else
        {
            broadcastreceiver = new String[1];
            broadcastreceiver[0] = s;
            s = broadcastreceiver;
        }
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, handler, ((android.content.IIntentReceiver) (obj)), j, s1, bundle1, s, i, bundle, true, false, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, 
            Bundle bundle)
    {
        sendOrderedBroadcastAsUser(intent, userhandle, s, -1, null, broadcastreceiver, handler, i, s1, bundle);
    }

    public void sendStickyBroadcast(Intent intent)
    {
        warnIfCallingFromSystemProcess();
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s, null, -1, null, null, null, -1, null, false, true, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendStickyBroadcastAsUser(Intent intent, UserHandle userhandle)
    {
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s, null, -1, null, null, null, -1, null, false, true, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendStickyBroadcastAsUser(Intent intent, UserHandle userhandle, Bundle bundle)
    {
        String s = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, s, null, -1, null, null, null, -1, bundle, false, true, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s, Bundle bundle)
    {
        warnIfCallingFromSystemProcess();
        Object obj = null;
        if(broadcastreceiver != null)
            if(mPackageInfo != null)
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = mPackageInfo.getReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), mMainThread.getInstrumentation(), false);
            } else
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = (new LoadedApk.ReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), null, false)).getIIntentReceiver();
            }
        broadcastreceiver = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, broadcastreceiver, ((android.content.IIntentReceiver) (obj)), i, s, bundle, null, -1, null, true, true, getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s, Bundle bundle)
    {
        Object obj = null;
        if(broadcastreceiver != null)
            if(mPackageInfo != null)
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = mPackageInfo.getReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), mMainThread.getInstrumentation(), false);
            } else
            {
                obj = handler;
                if(handler == null)
                    obj = mMainThread.getHandler();
                obj = (new LoadedApk.ReceiverDispatcher(broadcastreceiver, getOuterContext(), ((Handler) (obj)), null, false)).getIIntentReceiver();
            }
        broadcastreceiver = intent.resolveTypeIfNeeded(getContentResolver());
        try
        {
            intent.prepareToLeaveProcess(this);
            ActivityManager.getService().broadcastIntent(mMainThread.getApplicationThread(), intent, broadcastreceiver, ((android.content.IIntentReceiver) (obj)), i, s, bundle, null, -1, null, true, true, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void setAutofillClient(android.view.autofill.AutofillManager.AutofillClient autofillclient)
    {
        mAutofillClient = autofillclient;
    }

    final void setOuterContext(Context context)
    {
        mOuterContext = context;
    }

    void setResources(Resources resources)
    {
        if(resources instanceof CompatResources)
            ((CompatResources)resources).setContext(this);
        mResources = resources;
    }

    public void setTheme(int i)
    {
        Object obj = mSync;
        obj;
        JVM INSTR monitorenter ;
        if(mThemeResource != i)
        {
            mThemeResource = i;
            initializeTheme();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setWallpaper(Bitmap bitmap)
        throws IOException
    {
        getWallpaperManager().setBitmap(bitmap);
    }

    public void setWallpaper(InputStream inputstream)
        throws IOException
    {
        getWallpaperManager().setStream(inputstream);
    }

    public void startActivities(Intent aintent[])
    {
        warnIfCallingFromSystemProcess();
        startActivities(aintent, null);
    }

    public void startActivities(Intent aintent[], Bundle bundle)
    {
        warnIfCallingFromSystemProcess();
        if((aintent[0].getFlags() & 0x10000000) == 0)
        {
            throw new AndroidRuntimeException("Calling startActivities() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag on first Intent. Is this really what you want?");
        } else
        {
            mMainThread.getInstrumentation().execStartActivities(getOuterContext(), mMainThread.getApplicationThread(), null, (Activity)null, aintent, bundle);
            return;
        }
    }

    public void startActivitiesAsUser(Intent aintent[], Bundle bundle, UserHandle userhandle)
    {
        if((aintent[0].getFlags() & 0x10000000) == 0)
        {
            throw new AndroidRuntimeException("Calling startActivities() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag on first Intent. Is this really what you want?");
        } else
        {
            mMainThread.getInstrumentation().execStartActivitiesAsUser(getOuterContext(), mMainThread.getApplicationThread(), null, (Activity)null, aintent, bundle, userhandle.getIdentifier());
            return;
        }
    }

    public void startActivity(Intent intent)
    {
        warnIfCallingFromSystemProcess();
        startActivity(intent, null);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        warnIfCallingFromSystemProcess();
        if((intent.getFlags() & 0x10000000) == 0 && bundle != null && ActivityOptions.fromBundle(bundle).getLaunchTaskId() == -1)
        {
            throw new AndroidRuntimeException("Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?");
        } else
        {
            mMainThread.getInstrumentation().execStartActivity(getOuterContext(), mMainThread.getApplicationThread(), null, (Activity)null, intent, -1, bundle);
            return;
        }
    }

    public void startActivityAsUser(Intent intent, Bundle bundle, UserHandle userhandle)
    {
        try
        {
            ActivityManager.getService().startActivityAsUser(mMainThread.getApplicationThread(), getBasePackageName(), intent, intent.resolveTypeIfNeeded(getContentResolver()), null, null, 0, 0x10000000, null, bundle, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
    }

    public void startActivityAsUser(Intent intent, UserHandle userhandle)
    {
        startActivityAsUser(intent, null, userhandle);
    }

    public ComponentName startForegroundService(Intent intent)
    {
        warnIfCallingFromSystemProcess();
        return startServiceCommon(intent, true, mUser);
    }

    public ComponentName startForegroundServiceAsUser(Intent intent, UserHandle userhandle)
    {
        return startServiceCommon(intent, true, userhandle);
    }

    public boolean startInstrumentation(ComponentName componentname, String s, Bundle bundle)
    {
        if(bundle == null)
            break MISSING_BLOCK_LABEL_10;
        bundle.setAllowFds(false);
        boolean flag = ActivityManager.getService().startInstrumentation(componentname, s, 0, bundle, null, null, getUserId(), null);
        return flag;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k)
        throws android.content.IntentSender.SendIntentException
    {
        startIntentSender(intentsender, intent, i, j, k, null);
    }

    public void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        String s;
        s = null;
        if(intent == null)
            break MISSING_BLOCK_LABEL_27;
        intent.migrateExtraStreamToClipData();
        intent.prepareToLeaveProcess(this);
        s = intent.resolveTypeIfNeeded(getContentResolver());
        IActivityManager iactivitymanager;
        ActivityThread.ApplicationThread applicationthread;
        iactivitymanager = ActivityManager.getService();
        applicationthread = mMainThread.getApplicationThread();
        android.content.IIntentSender iintentsender;
        if(intentsender != null)
            try
            {
                iintentsender = intentsender.getTarget();
            }
            // Misplaced declaration of an exception variable
            catch(IntentSender intentsender)
            {
                throw intentsender.rethrowFromSystemServer();
            }
        else
            iintentsender = null;
        if(intentsender == null)
            break MISSING_BLOCK_LABEL_112;
        intentsender = intentsender.getWhitelistToken();
_L1:
        i = iactivitymanager.startActivityIntentSender(applicationthread, iintentsender, intentsender, intent, s, null, null, 0, i, j, bundle);
        if(i != -96)
            break MISSING_BLOCK_LABEL_117;
        intentsender = JVM INSTR new #1452 <Class android.content.IntentSender$SendIntentException>;
        intentsender.android.content.IntentSender.SendIntentException();
        throw intentsender;
        intentsender = null;
          goto _L1
        Instrumentation.checkStartActivityResult(i, null);
        return;
    }

    public ComponentName startService(Intent intent)
    {
        warnIfCallingFromSystemProcess();
        return startServiceCommon(intent, false, mUser);
    }

    public ComponentName startServiceAsUser(Intent intent, UserHandle userhandle)
    {
        return startServiceCommon(intent, false, userhandle);
    }

    public boolean stopService(Intent intent)
    {
        warnIfCallingFromSystemProcess();
        return stopServiceCommon(intent, mUser);
    }

    public boolean stopServiceAsUser(Intent intent, UserHandle userhandle)
    {
        return stopServiceCommon(intent, userhandle);
    }

    public void unbindService(ServiceConnection serviceconnection)
    {
        if(serviceconnection == null)
            throw new IllegalArgumentException("connection is null");
        if(mPackageInfo != null)
        {
            serviceconnection = mPackageInfo.forgetServiceDispatcher(getOuterContext(), serviceconnection);
            try
            {
                ActivityManager.getService().unbindService(serviceconnection);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(ServiceConnection serviceconnection)
            {
                throw serviceconnection.rethrowFromSystemServer();
            }
        } else
        {
            throw new RuntimeException("Not supported in system context");
        }
    }

    public void unregisterReceiver(BroadcastReceiver broadcastreceiver)
    {
        if(mPackageInfo != null)
        {
            broadcastreceiver = mPackageInfo.forgetReceiverDispatcher(getOuterContext(), broadcastreceiver);
            try
            {
                ActivityManager.getService().unregisterReceiver(broadcastreceiver);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(BroadcastReceiver broadcastreceiver)
            {
                throw broadcastreceiver.rethrowFromSystemServer();
            }
        } else
        {
            throw new RuntimeException("Not supported in system context");
        }
    }

    public void updateDisplay(int i)
    {
        mDisplay = mResourcesManager.getAdjustedDisplay(i, mResources);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "ContextImpl";
    private static final String XATTR_INODE_CACHE = "user.inode_cache";
    private static final String XATTR_INODE_CODE_CACHE = "user.inode_code_cache";
    private static ArrayMap sSharedPrefsCache;
    private final IBinder mActivityToken;
    private android.view.autofill.AutofillManager.AutofillClient mAutofillClient;
    private final String mBasePackageName;
    private File mCacheDir;
    private ClassLoader mClassLoader;
    private File mCodeCacheDir;
    private final ApplicationContentResolver mContentResolver;
    private File mDatabasesDir;
    private Display mDisplay;
    private File mFilesDir;
    private final int mFlags;
    final ActivityThread mMainThread;
    private File mNoBackupFilesDir;
    private final String mOpPackageName;
    private Context mOuterContext;
    final LoadedApk mPackageInfo;
    private PackageManager mPackageManager;
    private File mPreferencesDir;
    private Context mReceiverRestrictedContext;
    private Resources mResources;
    private final android.app.ResourcesManager mResourcesManager = ResourcesManager.getInstance();
    final Object mServiceCache[] = SystemServiceRegistry.createServiceCache();
    private ArrayMap mSharedPrefsPaths;
    private String mSplitName;
    private final Object mSync = new Object();
    private android.content.res.Resources.Theme mTheme;
    private int mThemeResource;
    private final UserHandle mUser;
}
