// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.*;
import android.graphics.drawable.*;
import android.miui.ResourcesManager;
import android.net.Uri;
import android.os.*;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.system.*;
import android.util.*;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.Preconditions;
import com.android.internal.util.UserIcons;
import dalvik.system.VMRuntime;
import java.lang.ref.WeakReference;
import java.util.*;
import libcore.util.EmptyArray;
import miui.securityspace.XSpaceUserHandle;

// Referenced classes of package android.app:
//            ApplicationPackageManagerInjector, ContextImpl, ActivityThread, PackageInstallObserver

public class ApplicationPackageManager extends PackageManager
{
    private static class DexModuleRegisterCallbackDelegate extends android.content.pm.IDexModuleRegisterCallback.Stub
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            if(message.what != 1)
            {
                return false;
            } else
            {
                message = (DexModuleRegisterResult)message.obj;
                callback.onDexModuleRegistered(((DexModuleRegisterResult) (message)).dexModulePath, ((DexModuleRegisterResult) (message)).success, ((DexModuleRegisterResult) (message)).message);
                return true;
            }
        }

        public void onDexModuleRegistered(String s, boolean flag, String s1)
            throws RemoteException
        {
            mHandler.obtainMessage(1, new DexModuleRegisterResult(s, flag, s1, null)).sendToTarget();
        }

        private static final int MSG_DEX_MODULE_REGISTERED = 1;
        private final android.content.pm.PackageManager.DexModuleRegisterCallback callback;
        private final Handler mHandler = new Handler(Looper.getMainLooper(), this);

        DexModuleRegisterCallbackDelegate(android.content.pm.PackageManager.DexModuleRegisterCallback dexmoduleregistercallback)
        {
            callback = dexmoduleregistercallback;
        }
    }

    private static class DexModuleRegisterResult
    {

        final String dexModulePath;
        final String message;
        final boolean success;

        private DexModuleRegisterResult(String s, boolean flag, String s1)
        {
            dexModulePath = s;
            success = flag;
            message = s1;
        }

        DexModuleRegisterResult(String s, boolean flag, String s1, DexModuleRegisterResult dexmoduleregisterresult)
        {
            this(s, flag, s1);
        }
    }

    private static class MoveCallbackDelegate extends android.content.pm.IPackageMoveObserver.Stub
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                return false;

            case 1: // '\001'
                message = (SomeArgs)message.obj;
                mCallback.onCreated(((SomeArgs) (message)).argi1, (Bundle)((SomeArgs) (message)).arg2);
                message.recycle();
                return true;

            case 2: // '\002'
                message = (SomeArgs)message.obj;
                break;
            }
            mCallback.onStatusChanged(((SomeArgs) (message)).argi1, ((SomeArgs) (message)).argi2, ((Long)((SomeArgs) (message)).arg3).longValue());
            message.recycle();
            return true;
        }

        public void onCreated(int i, Bundle bundle)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.argi1 = i;
            someargs.arg2 = bundle;
            mHandler.obtainMessage(1, someargs).sendToTarget();
        }

        public void onStatusChanged(int i, int j, long l)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.argi1 = i;
            someargs.argi2 = j;
            someargs.arg3 = Long.valueOf(l);
            mHandler.obtainMessage(2, someargs).sendToTarget();
        }

        private static final int MSG_CREATED = 1;
        private static final int MSG_STATUS_CHANGED = 2;
        final android.content.pm.PackageManager.MoveCallback mCallback;
        final Handler mHandler;

        public MoveCallbackDelegate(android.content.pm.PackageManager.MoveCallback movecallback, Looper looper)
        {
            mCallback = movecallback;
            mHandler = new Handler(looper, this);
        }
    }

    public class OnPermissionsChangeListenerDelegate extends android.content.pm.IOnPermissionsChangeListener.Stub
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            int i;
            switch(message.what)
            {
            default:
                return false;

            case 1: // '\001'
                i = message.arg1;
                break;
            }
            mListener.onPermissionsChanged(i);
            return true;
        }

        public void onPermissionsChanged(int i)
        {
            mHandler.obtainMessage(1, i, 0).sendToTarget();
        }

        private static final int MSG_PERMISSIONS_CHANGED = 1;
        private final Handler mHandler;
        private final android.content.pm.PackageManager.OnPermissionsChangedListener mListener;
        final ApplicationPackageManager this$0;

        public OnPermissionsChangeListenerDelegate(android.content.pm.PackageManager.OnPermissionsChangedListener onpermissionschangedlistener, Looper looper)
        {
            this$0 = ApplicationPackageManager.this;
            super();
            mListener = onpermissionschangedlistener;
            mHandler = new Handler(looper, this);
        }
    }

    private static final class ResourceName
    {

        public boolean equals(Object obj)
        {
            boolean flag;
            flag = true;
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (ResourceName)obj;
            if(iconId != ((ResourceName) (obj)).iconId)
                return false;
            if(packageName == null) goto _L2; else goto _L1
_L1:
            flag = packageName.equals(((ResourceName) (obj)).packageName) ^ true;
_L4:
            return flag ^ true;
_L2:
            if(((ResourceName) (obj)).packageName == null)
                flag = false;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int hashCode()
        {
            return packageName.hashCode() * 31 + iconId;
        }

        public String toString()
        {
            return (new StringBuilder()).append("{ResourceName ").append(packageName).append(" / ").append(iconId).append("}").toString();
        }

        final int iconId;
        final String packageName;

        ResourceName(ApplicationInfo applicationinfo, int i)
        {
            this(applicationinfo.packageName, i);
        }

        ResourceName(ComponentInfo componentinfo, int i)
        {
            this(componentinfo.applicationInfo.packageName, i);
        }

        ResourceName(ResolveInfo resolveinfo, int i)
        {
            this(resolveinfo.activityInfo.applicationInfo.packageName, i);
        }

        ResourceName(String s, int i)
        {
            packageName = s;
            iconId = i;
        }
    }


    protected ApplicationPackageManager(ContextImpl contextimpl, IPackageManager ipackagemanager)
    {
        mCachedSafeMode = -1;
        mContext = contextimpl;
        mPM = ipackagemanager;
    }

    static void configurationChanged()
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        sIconCache.clear();
        sStringCache.clear();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private int getBadgeResIdForUser(int i)
    {
        UserInfo userinfo = getUserIfProfile(i);
        if(isManagedProfile(i))
            return 0x108033c;
        return !XSpaceUserHandle.isXSpaceUser(userinfo) ? 0 : 0x11020066;
    }

    private Drawable getBadgedDrawable(Drawable drawable, Drawable drawable1, Rect rect, boolean flag)
    {
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        if(flag && (drawable instanceof BitmapDrawable))
            flag = ((BitmapDrawable)drawable).getBitmap().isMutable();
        else
            flag = false;
        if(ApplicationPackageManagerInjector.hookGetBadgedDrawable(drawable, drawable1, rect))
        {
            Log.d("ApplicationPackageManager", "MIUILOG-Maml ApplicationPackageManagerInjector.hookGetBadgedDrawable return true");
            return drawable;
        }
        Bitmap bitmap;
        Canvas canvas;
        if(flag)
            bitmap = ((BitmapDrawable)drawable).getBitmap();
        else
            bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        if(!flag)
        {
            drawable.setBounds(0, 0, i, j);
            drawable.draw(canvas);
        }
        if(rect != null)
        {
            while(rect.left < 0 || rect.top < 0 || rect.width() > i || rect.height() > j) 
                throw new IllegalArgumentException((new StringBuilder()).append("Badge location ").append(rect).append(" not in badged drawable bounds ").append(new Rect(0, 0, i, j)).toString());
            drawable1.setBounds(0, 0, rect.width(), rect.height());
            canvas.save();
            canvas.translate(rect.left, rect.top);
            drawable1.draw(canvas);
            canvas.restore();
        } else
        {
            drawable1.setBounds(0, 0, i, j);
            drawable1.draw(canvas);
        }
        if(!flag)
        {
            drawable1 = new BitmapDrawable(mContext.getResources(), bitmap);
            if(drawable instanceof BitmapDrawable)
                drawable1.setTargetDensity(((BitmapDrawable)drawable).getBitmap().getDensity());
            return drawable1;
        } else
        {
            return drawable;
        }
    }

    private Drawable getCachedIcon(ResourceName resourcename)
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (WeakReference)sIconCache.get(resourcename);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_50;
        obj1 = (android.graphics.drawable.Drawable.ConstantState)((WeakReference) (obj1)).get();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_42;
        resourcename = ((android.graphics.drawable.Drawable.ConstantState) (obj1)).newDrawable();
        obj;
        JVM INSTR monitorexit ;
        return resourcename;
        sIconCache.remove(resourcename);
        obj;
        JVM INSTR monitorexit ;
        return null;
        resourcename;
        throw resourcename;
    }

    private CharSequence getCachedString(ResourceName resourcename)
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (WeakReference)sStringCache.get(resourcename);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_45;
        obj1 = (CharSequence)((WeakReference) (obj1)).get();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_37;
        obj;
        JVM INSTR monitorexit ;
        return ((CharSequence) (obj1));
        sStringCache.remove(resourcename);
        obj;
        JVM INSTR monitorexit ;
        return null;
        resourcename;
        throw resourcename;
    }

    private Drawable getDrawableForDensity(int i, int j)
    {
        int k = j;
        if(j <= 0)
            k = mContext.getResources().getDisplayMetrics().densityDpi;
        return Resources.getSystem().getDrawableForDensity(i, k);
    }

    private Drawable getManagedProfileIconForDensity(UserHandle userhandle, int i, int j)
    {
        if(isManagedProfile(userhandle.getIdentifier()))
            return getDrawableForDensity(i, j);
        else
            return null;
    }

    private int getUserBadgeColor(UserHandle userhandle)
    {
        return IconDrawableFactory.getUserBadgeColor(getUserManager(), userhandle.getIdentifier());
    }

    private UserInfo getUserIfProfile(int i)
    {
        for(Iterator iterator = getUserManager().getProfiles(UserHandle.myUserId()).iterator(); iterator.hasNext();)
        {
            UserInfo userinfo = (UserInfo)iterator.next();
            if(userinfo.id == i)
                return userinfo;
        }

        return null;
    }

    static void handlePackageBroadcast(int i, String as[], boolean flag)
    {
        int j;
        boolean flag1;
        int k;
        j = 0;
        flag1 = false;
        if(i == 1)
            flag1 = true;
        if(as == null || as.length <= 0)
            break MISSING_BLOCK_LABEL_189;
        i = 0;
        k = as.length;
_L8:
        String s;
        if(j >= k)
            break; /* Loop/switch isn't completed */
        s = as[j];
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        int l = sIconCache.size() - 1;
_L3:
        if(l < 0) goto _L2; else goto _L1
_L1:
        if(!((ResourceName)sIconCache.keyAt(l)).packageName.equals(s))
            continue; /* Loop/switch isn't completed */
        sIconCache.removeAt(l);
        i = 1;
        l--;
          goto _L3
_L2:
        l = sStringCache.size() - 1;
_L6:
        if(l < 0) goto _L5; else goto _L4
_L4:
        if(!((ResourceName)sStringCache.keyAt(l)).packageName.equals(s))
            continue; /* Loop/switch isn't completed */
        sStringCache.removeAt(l);
        i = 1;
        l--;
          goto _L6
_L5:
        obj;
        JVM INSTR monitorexit ;
        j++;
        if(true) goto _L8; else goto _L7
        as;
        throw as;
_L7:
        if(i != 0 || flag)
            if(flag1)
                Runtime.getRuntime().gc();
            else
                ActivityThread.currentActivityThread().scheduleGcIdler();
    }

    private void installCommon(Uri uri, PackageInstallObserver packageinstallobserver, int i, String s, int j)
    {
        if(!"file".equals(uri.getScheme()))
            throw new UnsupportedOperationException("Only file:// URIs are supported");
        uri = uri.getPath();
        try
        {
            mPM.installPackageAsUser(uri, packageinstallobserver.getBinder(), i, s, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
    }

    private int installExistingPackageAsUser(String s, int i, int j)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        StringBuilder stringbuilder;
        try
        {
            i = mPM.installExistingPackageAsUser(s, j, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(i != -3)
            break MISSING_BLOCK_LABEL_70;
        namenotfoundexception = JVM INSTR new #412 <Class android.content.pm.PackageManager$NameNotFoundException>;
        stringbuilder = JVM INSTR new #182 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        namenotfoundexception.android.content.pm.PackageManager.NameNotFoundException(stringbuilder.append("Package ").append(s).append(" doesn't exist").toString());
        throw namenotfoundexception;
        return i;
    }

    private boolean isManagedProfile(int i)
    {
        return getUserManager().isManagedProfile(i);
    }

    private boolean isPackageCandidateVolume(ContextImpl contextimpl, ApplicationInfo applicationinfo, VolumeInfo volumeinfo, IPackageManager ipackagemanager)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = isForceAllowOnExternal(contextimpl);
        if("private".equals(volumeinfo.getId()))
        {
            flag = flag1;
            if(!applicationinfo.isSystemApp())
                flag = isAllow3rdPartyOnInternal(contextimpl);
            return flag;
        }
        if(applicationinfo.isSystemApp())
            return false;
        if(!flag2 && (applicationinfo.installLocation == 1 || applicationinfo.installLocation == -1))
            return false;
        if(!volumeinfo.isMountedWritable())
            return false;
        if(volumeinfo.isPrimaryPhysical())
            return applicationinfo.isInternal();
        try
        {
            flag1 = ipackagemanager.isPackageDeviceAdminOnAnyUser(applicationinfo.packageName);
        }
        // Misplaced declaration of an exception variable
        catch(ContextImpl contextimpl)
        {
            throw contextimpl.rethrowFromSystemServer();
        }
        if(flag1)
            return false;
        if(volumeinfo.getType() != 1)
            flag = false;
        return flag;
    }

    private static boolean isPrimaryStorageCandidateVolume(VolumeInfo volumeinfo)
    {
        boolean flag = true;
        if("private".equals(volumeinfo.getId()))
            return true;
        if(!volumeinfo.isMountedWritable())
            return false;
        if(volumeinfo.getType() != 1)
            flag = false;
        return flag;
    }

    private static ApplicationInfo maybeAdjustApplicationInfo(ApplicationInfo applicationinfo)
    {
        if(applicationinfo.primaryCpuAbi != null && applicationinfo.secondaryCpuAbi != null)
        {
            String s = VMRuntime.getRuntime().vmInstructionSet();
            Object obj = VMRuntime.getInstructionSet(applicationinfo.secondaryCpuAbi);
            String s1 = SystemProperties.get((new StringBuilder()).append("ro.dalvik.vm.isa.").append(((String) (obj))).toString());
            if(!s1.isEmpty())
                obj = s1;
            if(s.equals(obj))
            {
                obj = new ApplicationInfo(applicationinfo);
                obj.nativeLibraryDir = applicationinfo.secondaryNativeLibraryDir;
                return ((ApplicationInfo) (obj));
            }
        }
        return applicationinfo;
    }

    private void putCachedIcon(ResourceName resourcename, Drawable drawable)
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = sIconCache;
        WeakReference weakreference = JVM INSTR new #253 <Class WeakReference>;
        weakreference.WeakReference(drawable.getConstantState());
        arraymap.put(resourcename, weakreference);
        obj;
        JVM INSTR monitorexit ;
        return;
        resourcename;
        throw resourcename;
    }

    private void putCachedString(ResourceName resourcename, CharSequence charsequence)
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = sStringCache;
        WeakReference weakreference = JVM INSTR new #253 <Class WeakReference>;
        weakreference.WeakReference(charsequence);
        arraymap.put(resourcename, weakreference);
        obj;
        JVM INSTR monitorexit ;
        return;
        resourcename;
        throw resourcename;
    }

    public void addCrossProfileIntentFilter(IntentFilter intentfilter, int i, int j, int k)
    {
        try
        {
            mPM.addCrossProfileIntentFilter(intentfilter, mContext.getOpPackageName(), i, j, k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IntentFilter intentfilter)
        {
            throw intentfilter.rethrowFromSystemServer();
        }
    }

    public void addOnPermissionsChangeListener(android.content.pm.PackageManager.OnPermissionsChangedListener onpermissionschangedlistener)
    {
        Map map = mPermissionListeners;
        map;
        JVM INSTR monitorenter ;
        Object obj = mPermissionListeners.get(onpermissionschangedlistener);
        if(obj == null)
            break MISSING_BLOCK_LABEL_25;
        map;
        JVM INSTR monitorexit ;
        return;
        obj = JVM INSTR new #15  <Class ApplicationPackageManager$OnPermissionsChangeListenerDelegate>;
        ((OnPermissionsChangeListenerDelegate) (obj)).this. OnPermissionsChangeListenerDelegate(onpermissionschangedlistener, Looper.getMainLooper());
        mPM.addOnPermissionsChangeListener(((IOnPermissionsChangeListener) (obj)));
        mPermissionListeners.put(onpermissionschangedlistener, obj);
        map;
        JVM INSTR monitorexit ;
        return;
        onpermissionschangedlistener;
        throw onpermissionschangedlistener.rethrowFromSystemServer();
        onpermissionschangedlistener;
        map;
        JVM INSTR monitorexit ;
        throw onpermissionschangedlistener;
    }

    public void addPackageToPreferred(String s)
    {
        Log.w("ApplicationPackageManager", "addPackageToPreferred() is a no-op");
    }

    public boolean addPermission(PermissionInfo permissioninfo)
    {
        boolean flag;
        try
        {
            flag = mPM.addPermission(permissioninfo);
        }
        // Misplaced declaration of an exception variable
        catch(PermissionInfo permissioninfo)
        {
            throw permissioninfo.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean addPermissionAsync(PermissionInfo permissioninfo)
    {
        boolean flag;
        try
        {
            flag = mPM.addPermissionAsync(permissioninfo);
        }
        // Misplaced declaration of an exception variable
        catch(PermissionInfo permissioninfo)
        {
            throw permissioninfo.rethrowFromSystemServer();
        }
        return flag;
    }

    public void addPreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname)
    {
        try
        {
            mPM.addPreferredActivity(intentfilter, i, acomponentname, componentname, mContext.getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IntentFilter intentfilter)
        {
            throw intentfilter.rethrowFromSystemServer();
        }
    }

    public void addPreferredActivityAsUser(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
    {
        try
        {
            mPM.addPreferredActivity(intentfilter, i, acomponentname, componentname, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IntentFilter intentfilter)
        {
            throw intentfilter.rethrowFromSystemServer();
        }
    }

    public boolean canRequestPackageInstalls()
    {
        boolean flag;
        try
        {
            flag = mPM.canRequestPackageInstalls(mContext.getPackageName(), mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return flag;
    }

    public String[] canonicalToCurrentPackageNames(String as[])
    {
        try
        {
            as = mPM.canonicalToCurrentPackageNames(as);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw as.rethrowFromSystemServer();
        }
        return as;
    }

    public int checkPermission(String s, String s1)
    {
        int i;
        try
        {
            i = mPM.checkPermission(s, s1, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkSignatures(int i, int j)
    {
        try
        {
            i = mPM.checkUidSignatures(i, j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkSignatures(String s, String s1)
    {
        int i;
        try
        {
            i = mPM.checkSignatures(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public void clearApplicationUserData(String s, IPackageDataObserver ipackagedataobserver)
    {
        try
        {
            mPM.clearApplicationUserData(s, ipackagedataobserver, mContext.getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void clearCrossProfileIntentFilters(int i)
    {
        try
        {
            mPM.clearCrossProfileIntentFilters(i, mContext.getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void clearInstantAppCookie()
    {
        updateInstantAppCookie(null);
    }

    public void clearPackagePreferredActivities(String s)
    {
        try
        {
            mPM.clearPackagePreferredActivities(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public String[] currentToCanonicalPackageNames(String as[])
    {
        try
        {
            as = mPM.currentToCanonicalPackageNames(as);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw as.rethrowFromSystemServer();
        }
        return as;
    }

    public void deleteApplicationCacheFiles(String s, IPackageDataObserver ipackagedataobserver)
    {
        try
        {
            mPM.deleteApplicationCacheFiles(s, ipackagedataobserver);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void deleteApplicationCacheFilesAsUser(String s, int i, IPackageDataObserver ipackagedataobserver)
    {
        try
        {
            mPM.deleteApplicationCacheFilesAsUser(s, i, ipackagedataobserver);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void deletePackage(String s, IPackageDeleteObserver ipackagedeleteobserver, int i)
    {
        deletePackageAsUser(s, ipackagedeleteobserver, i, mContext.getUserId());
    }

    public void deletePackageAsUser(String s, IPackageDeleteObserver ipackagedeleteobserver, int i, int j)
    {
        try
        {
            mPM.deletePackageAsUser(s, -1, ipackagedeleteobserver, j, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void extendVerificationTimeout(int i, int j, long l)
    {
        try
        {
            mPM.extendVerificationTimeout(i, j, l);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void flushPackageRestrictionsAsUser(int i)
    {
        try
        {
            mPM.flushPackageRestrictionsAsUser(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void freeStorage(String s, long l, IntentSender intentsender)
    {
        try
        {
            mPM.freeStorage(s, l, 0, intentsender);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void freeStorageAndNotify(String s, long l, IPackageDataObserver ipackagedataobserver)
    {
        try
        {
            mPM.freeStorageAndNotify(s, l, 0, ipackagedataobserver);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public Drawable getActivityBanner(ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getActivityInfo(componentname, 1024).loadBanner(this);
    }

    public Drawable getActivityBanner(Intent intent)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(intent.getComponent() != null)
            return getActivityBanner(intent.getComponent());
        ResolveInfo resolveinfo = resolveActivity(intent, 0x10000);
        if(resolveinfo != null)
            return resolveinfo.activityInfo.loadBanner(this);
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(intent.toUri(0));
    }

    public Drawable getActivityIcon(ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getActivityInfo(componentname, 1024).loadIcon(this);
    }

    public Drawable getActivityIcon(Intent intent)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(intent.getComponent() != null)
            return getActivityIcon(intent.getComponent());
        ResolveInfo resolveinfo = resolveActivity(intent, 0x10000);
        if(resolveinfo != null)
            return resolveinfo.activityInfo.loadIcon(this);
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(intent.toUri(0));
    }

    public ActivityInfo getActivityInfo(ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ActivityInfo activityinfo;
        try
        {
            activityinfo = mPM.getActivityInfo(componentname, i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(activityinfo != null)
            return activityinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(componentname.toString());
    }

    public Drawable getActivityLogo(ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getActivityInfo(componentname, 1024).loadLogo(this);
    }

    public Drawable getActivityLogo(Intent intent)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(intent.getComponent() != null)
            return getActivityLogo(intent.getComponent());
        ResolveInfo resolveinfo = resolveActivity(intent, 0x10000);
        if(resolveinfo != null)
            return resolveinfo.activityInfo.loadLogo(this);
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(intent.toUri(0));
    }

    public List getAllIntentFilters(String s)
    {
        try
        {
            s = mPM.getAllIntentFilters(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s != null)
            break MISSING_BLOCK_LABEL_19;
        return Collections.emptyList();
        s = s.getList();
        return s;
    }

    public List getAllPermissionGroups(int i)
    {
        Object obj;
        try
        {
            obj = mPM.getAllPermissionGroups(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_19;
        return Collections.emptyList();
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
    }

    public Drawable getApplicationBanner(ApplicationInfo applicationinfo)
    {
        return applicationinfo.loadBanner(this);
    }

    public Drawable getApplicationBanner(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getApplicationBanner(getApplicationInfo(s, 1024));
    }

    public int getApplicationEnabledSetting(String s)
    {
        int i;
        try
        {
            i = mPM.getApplicationEnabledSetting(s, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public boolean getApplicationHiddenSettingAsUser(String s, UserHandle userhandle)
    {
        boolean flag;
        try
        {
            flag = mPM.getApplicationHiddenSettingAsUser(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public Drawable getApplicationIcon(ApplicationInfo applicationinfo)
    {
        return applicationinfo.loadIcon(this);
    }

    public Drawable getApplicationIcon(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getApplicationIcon(getApplicationInfo(s, 1024));
    }

    public ApplicationInfo getApplicationInfo(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getApplicationInfoAsUser(s, i, mContext.getUserId());
    }

    public ApplicationInfo getApplicationInfoAsUser(String s, int i, int j)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ApplicationInfo applicationinfo;
        try
        {
            applicationinfo = mPM.getApplicationInfo(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(applicationinfo == null)
            break MISSING_BLOCK_LABEL_33;
        s = maybeAdjustApplicationInfo(applicationinfo);
        return s;
        throw new android.content.pm.PackageManager.NameNotFoundException(s);
    }

    public CharSequence getApplicationLabel(ApplicationInfo applicationinfo)
    {
        return applicationinfo.loadLabel(this);
    }

    public Drawable getApplicationLogo(ApplicationInfo applicationinfo)
    {
        return applicationinfo.loadLogo(this);
    }

    public Drawable getApplicationLogo(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getApplicationLogo(getApplicationInfo(s, 1024));
    }

    public ChangedPackages getChangedPackages(int i)
    {
        ChangedPackages changedpackages;
        try
        {
            changedpackages = mPM.getChangedPackages(i, mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return changedpackages;
    }

    public int getComponentEnabledSetting(ComponentName componentname)
    {
        int i;
        try
        {
            i = mPM.getComponentEnabledSetting(componentname, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return i;
    }

    ContextImpl getContext()
    {
        return mContext;
    }

    public Drawable getDefaultActivityIcon()
    {
        return Resources.getSystem().getDrawable(0x1080093);
    }

    public String getDefaultBrowserPackageNameAsUser(int i)
    {
        String s;
        try
        {
            s = mPM.getDefaultBrowserPackageName(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public Drawable getDrawable(String s, int i, ApplicationInfo applicationinfo)
    {
        Object obj;
        ResourceName resourcename = new ResourceName(s, i);
        obj = getCachedIcon(resourcename);
        if(obj != null)
            return ((Drawable) (obj));
        obj = applicationinfo;
        if(applicationinfo == null)
            try
            {
                obj = getApplicationInfo(s, 1024);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return null;
            }
        if(i == 0)
            break MISSING_BLOCK_LABEL_118;
        applicationinfo = getResourcesForApplication(((ApplicationInfo) (obj))).getDrawable(i, null);
        if(applicationinfo == null)
            break MISSING_BLOCK_LABEL_71;
        putCachedIcon(resourcename, applicationinfo);
        return applicationinfo;
        applicationinfo;
        Log.w("PackageManager", (new StringBuilder()).append("Failure retrieving icon 0x").append(Integer.toHexString(i)).append(" in package ").append(s).toString(), applicationinfo);
_L2:
        return null;
        s;
        Log.w("PackageManager", (new StringBuilder()).append("Failure retrieving resources for ").append(((ApplicationInfo) (obj)).packageName).append(": ").append(s.getMessage()).toString());
        continue; /* Loop/switch isn't completed */
        s;
        Log.w("PackageManager", (new StringBuilder()).append("Failure retrieving resources for ").append(((ApplicationInfo) (obj)).packageName).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public ComponentName getHomeActivities(List list)
    {
        try
        {
            list = mPM.getHomeActivities(list);
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
        return list;
    }

    public int getInstallReason(String s, UserHandle userhandle)
    {
        int i;
        try
        {
            i = mPM.getInstallReason(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public List getInstalledApplications(int i)
    {
        return getInstalledApplicationsAsUser(i, mContext.getUserId());
    }

    public List getInstalledApplicationsAsUser(int i, int j)
    {
        Object obj;
        try
        {
            obj = mPM.getInstalledApplications(i, j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_20;
        return Collections.emptyList();
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
    }

    public List getInstalledPackages(int i)
    {
        return getInstalledPackagesAsUser(i, mContext.getUserId());
    }

    public List getInstalledPackagesAsUser(int i, int j)
    {
        Object obj;
        try
        {
            obj = mPM.getInstalledPackages(i, j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_20;
        return Collections.emptyList();
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
    }

    public String getInstallerPackageName(String s)
    {
        try
        {
            s = mPM.getInstallerPackageName(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public String getInstantAppAndroidId(String s, UserHandle userhandle)
    {
        try
        {
            s = mPM.getInstantAppAndroidId(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowAsRuntimeException();
        }
        return s;
    }

    public byte[] getInstantAppCookie()
    {
        byte abyte0[];
        try
        {
            abyte0 = mPM.getInstantAppCookie(mContext.getPackageName(), mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(abyte0 != null)
            return abyte0;
        abyte0 = EmptyArray.BYTE;
        return abyte0;
    }

    public int getInstantAppCookieMaxBytes()
    {
        return android.provider.Settings.Global.getInt(mContext.getContentResolver(), "ephemeral_cookie_max_size_bytes", 16384);
    }

    public int getInstantAppCookieMaxSize()
    {
        return getInstantAppCookieMaxBytes();
    }

    public Drawable getInstantAppIcon(String s)
    {
        try
        {
            s = mPM.getInstantAppIcon(s, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_34;
        s = new BitmapDrawable(null, s);
        return s;
        return null;
    }

    public ComponentName getInstantAppInstallerComponent()
    {
        ComponentName componentname;
        try
        {
            componentname = mPM.getInstantAppInstallerComponent();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return componentname;
    }

    public ComponentName getInstantAppResolverSettingsComponent()
    {
        ComponentName componentname;
        try
        {
            componentname = mPM.getInstantAppResolverSettingsComponent();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return componentname;
    }

    public List getInstantApps()
    {
        Object obj;
        try
        {
            obj = mPM.getInstantApps(mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_26;
        return ((ParceledListSlice) (obj)).getList();
        obj = Collections.emptyList();
        return ((List) (obj));
    }

    public InstrumentationInfo getInstrumentationInfo(ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        InstrumentationInfo instrumentationinfo;
        try
        {
            instrumentationinfo = mPM.getInstrumentationInfo(componentname, i);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(instrumentationinfo != null)
            return instrumentationinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(componentname.toString());
    }

    public List getIntentFilterVerifications(String s)
    {
        try
        {
            s = mPM.getIntentFilterVerifications(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s != null)
            break MISSING_BLOCK_LABEL_19;
        return Collections.emptyList();
        s = s.getList();
        return s;
    }

    public int getIntentVerificationStatusAsUser(String s, int i)
    {
        try
        {
            i = mPM.getIntentVerificationStatus(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public KeySet getKeySetByAlias(String s, String s1)
    {
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(s1);
        try
        {
            s = mPM.getKeySetByAlias(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Intent getLaunchIntentForPackage(String s)
    {
        Intent intent;
        List list1;
label0:
        {
            intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.INFO");
            intent.setPackage(s);
            List list = queryIntentActivities(intent, 0);
            if(list != null)
            {
                list1 = list;
                if(list.size() > 0)
                    break label0;
            }
            intent.removeCategory("android.intent.category.INFO");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(s);
            list1 = queryIntentActivities(intent, 0);
        }
        if(list1 == null || list1.size() <= 0)
        {
            return null;
        } else
        {
            s = new Intent(intent);
            s.setFlags(0x10000000);
            s.setClassName(((ResolveInfo)list1.get(0)).activityInfo.packageName, ((ResolveInfo)list1.get(0)).activityInfo.name);
            return s;
        }
    }

    public Intent getLeanbackLaunchIntentForPackage(String s)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LEANBACK_LAUNCHER");
        intent.setPackage(s);
        s = queryIntentActivities(intent, 0);
        if(s == null || s.size() <= 0)
        {
            return null;
        } else
        {
            intent = new Intent(intent);
            intent.setFlags(0x10000000);
            intent.setClassName(((ResolveInfo)s.get(0)).activityInfo.packageName, ((ResolveInfo)s.get(0)).activityInfo.name);
            return intent;
        }
    }

    public int getMoveStatus(int i)
    {
        try
        {
            i = mPM.getMoveStatus(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public String getNameForUid(int i)
    {
        String s;
        try
        {
            s = mPM.getNameForUid(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public String[] getNamesForUids(int ai[])
    {
        try
        {
            ai = mPM.getNamesForUids(ai);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
        return ai;
    }

    public List getPackageCandidateVolumes(ApplicationInfo applicationinfo)
    {
        return getPackageCandidateVolumes(applicationinfo, (StorageManager)mContext.getSystemService(android/os/storage/StorageManager), mPM);
    }

    protected List getPackageCandidateVolumes(ApplicationInfo applicationinfo, StorageManager storagemanager, IPackageManager ipackagemanager)
    {
        VolumeInfo volumeinfo = getPackageCurrentVolume(applicationinfo, storagemanager);
        List list = storagemanager.getVolumes();
        storagemanager = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            VolumeInfo volumeinfo1 = (VolumeInfo)iterator.next();
            if(Objects.equals(volumeinfo1, volumeinfo) || isPackageCandidateVolume(mContext, applicationinfo, volumeinfo1, ipackagemanager))
                storagemanager.add(volumeinfo1);
        } while(true);
        return storagemanager;
    }

    public VolumeInfo getPackageCurrentVolume(ApplicationInfo applicationinfo)
    {
        return getPackageCurrentVolume(applicationinfo, (StorageManager)mContext.getSystemService(android/os/storage/StorageManager));
    }

    protected VolumeInfo getPackageCurrentVolume(ApplicationInfo applicationinfo, StorageManager storagemanager)
    {
        if(applicationinfo.isInternal())
            return storagemanager.findVolumeById("private");
        if(applicationinfo.isExternalAsec())
            return storagemanager.getPrimaryPhysicalVolume();
        else
            return storagemanager.findVolumeByUuid(applicationinfo.volumeUuid);
    }

    public int[] getPackageGids(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getPackageGids(s, 0);
    }

    public int[] getPackageGids(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        int ai[];
        try
        {
            ai = mPM.getPackageGids(s, i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(ai != null)
            return ai;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(s);
    }

    public PackageInfo getPackageInfo(VersionedPackage versionedpackage, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        PackageInfo packageinfo;
        try
        {
            packageinfo = mPM.getPackageInfoVersioned(versionedpackage, i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(VersionedPackage versionedpackage)
        {
            throw versionedpackage.rethrowFromSystemServer();
        }
        if(packageinfo != null)
            return packageinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(versionedpackage.toString());
    }

    public PackageInfo getPackageInfo(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getPackageInfoAsUser(s, i, mContext.getUserId());
    }

    public PackageInfo getPackageInfoAsUser(String s, int i, int j)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        PackageInfo packageinfo;
        try
        {
            packageinfo = mPM.getPackageInfo(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(packageinfo != null)
            return packageinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(s);
    }

    public PackageInstaller getPackageInstaller()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        PackageInstaller packageinstaller = mInstaller;
        if(packageinstaller != null)
            break MISSING_BLOCK_LABEL_52;
        packageinstaller = JVM INSTR new #1055 <Class PackageInstaller>;
        packageinstaller.PackageInstaller(mPM.getPackageInstaller(), mContext.getPackageName(), mContext.getUserId());
        mInstaller = packageinstaller;
        packageinstaller = mInstaller;
        obj;
        JVM INSTR monitorexit ;
        return packageinstaller;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public void getPackageSizeInfoAsUser(String s, int i, IPackageStatsObserver ipackagestatsobserver)
    {
        if(mContext.getApplicationInfo().targetSdkVersion >= 26)
            throw new UnsupportedOperationException("Shame on you for calling the hidden API getPackageSizeInfoAsUser(). Shame!");
        if(ipackagestatsobserver == null)
            break MISSING_BLOCK_LABEL_47;
        Log.d("ApplicationPackageManager", "Shame on you for calling the hidden API getPackageSizeInfoAsUser(). Shame!");
        ipackagestatsobserver.onGetStatsCompleted(null, false);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int getPackageUid(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getPackageUidAsUser(s, i, mContext.getUserId());
    }

    public int getPackageUidAsUser(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getPackageUidAsUser(s, 0, i);
    }

    public int getPackageUidAsUser(String s, int i, int j)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        try
        {
            i = mPM.getPackageUid(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(i >= 0)
            return i;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(s);
    }

    public String[] getPackagesForUid(int i)
    {
        String as[];
        try
        {
            as = mPM.getPackagesForUid(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public List getPackagesHoldingPermissions(String as[], int i)
    {
        int j = mContext.getUserId();
        try
        {
            as = mPM.getPackagesHoldingPermissions(as, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw as.rethrowFromSystemServer();
        }
        if(as != null)
            break MISSING_BLOCK_LABEL_29;
        return Collections.emptyList();
        as = as.getList();
        return as;
    }

    public String getPermissionControllerPackageName()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        String s = mPermissionsControllerPackageName;
        if(s != null)
            break MISSING_BLOCK_LABEL_29;
        mPermissionsControllerPackageName = mPM.getPermissionControllerPackageName();
        s = mPermissionsControllerPackageName;
        obj;
        JVM INSTR monitorexit ;
        return s;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public int getPermissionFlags(String s, String s1, UserHandle userhandle)
    {
        int i;
        try
        {
            i = mPM.getPermissionFlags(s, s1, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public PermissionGroupInfo getPermissionGroupInfo(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        PermissionGroupInfo permissiongroupinfo;
        try
        {
            permissiongroupinfo = mPM.getPermissionGroupInfo(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(permissiongroupinfo != null)
            return permissiongroupinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(s);
    }

    public PermissionInfo getPermissionInfo(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        PermissionInfo permissioninfo;
        try
        {
            permissioninfo = mPM.getPermissionInfo(s, mContext.getOpPackageName(), i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(permissioninfo != null)
            return permissioninfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(s);
    }

    public int getPreferredActivities(List list, List list1, String s)
    {
        int i;
        try
        {
            i = mPM.getPreferredActivities(list, list1, s);
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
        return i;
    }

    public List getPreferredPackages(int i)
    {
        Log.w("ApplicationPackageManager", "getPreferredPackages() is a no-op");
        return Collections.emptyList();
    }

    public List getPrimaryStorageCandidateVolumes()
    {
        StorageManager storagemanager = (StorageManager)mContext.getSystemService(android/os/storage/StorageManager);
        VolumeInfo volumeinfo = getPrimaryStorageCurrentVolume();
        List list = storagemanager.getVolumes();
        ArrayList arraylist = new ArrayList();
        if(Objects.equals("primary_physical", storagemanager.getPrimaryStorageUuid()) && volumeinfo != null)
        {
            arraylist.add(volumeinfo);
        } else
        {
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
            {
                VolumeInfo volumeinfo1 = (VolumeInfo)iterator.next();
                if(Objects.equals(volumeinfo1, volumeinfo) || isPrimaryStorageCandidateVolume(volumeinfo1))
                    arraylist.add(volumeinfo1);
            }
        }
        return arraylist;
    }

    public VolumeInfo getPrimaryStorageCurrentVolume()
    {
        StorageManager storagemanager = (StorageManager)mContext.getSystemService(android/os/storage/StorageManager);
        return storagemanager.findVolumeByQualifiedUuid(storagemanager.getPrimaryStorageUuid());
    }

    public ProviderInfo getProviderInfo(ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ProviderInfo providerinfo;
        try
        {
            providerinfo = mPM.getProviderInfo(componentname, i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(providerinfo != null)
            return providerinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(componentname.toString());
    }

    public ActivityInfo getReceiverInfo(ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ActivityInfo activityinfo;
        try
        {
            activityinfo = mPM.getReceiverInfo(componentname, i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(activityinfo != null)
            return activityinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(componentname.toString());
    }

    public Resources getResourcesForActivity(ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getResourcesForApplication(getActivityInfo(componentname, 1024).applicationInfo);
    }

    public Resources getResourcesForApplication(ApplicationInfo applicationinfo)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(applicationinfo.packageName.equals("system"))
            return mContext.mMainThread.getSystemUiContext().getResources();
        boolean flag;
        ActivityThread activitythread;
        Object obj;
        String as[];
        if(applicationinfo.uid == Process.myUid())
            flag = true;
        else
            flag = false;
        activitythread = mContext.mMainThread;
        if(flag)
            obj = applicationinfo.sourceDir;
        else
            obj = applicationinfo.publicSourceDir;
        if(flag)
            as = applicationinfo.splitSourceDirs;
        else
            as = applicationinfo.splitPublicSourceDirs;
        obj = activitythread.getTopLevelResources(((String) (obj)), as, applicationinfo.resourceDirs, applicationinfo.sharedLibraryFiles, 0, mContext.mPackageInfo);
        ResourcesManager.initMiuiResource(((Resources) (obj)), applicationinfo.packageName);
        if(obj != null)
            return ((Resources) (obj));
        else
            throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("Unable to open ").append(applicationinfo.publicSourceDir).toString());
    }

    public Resources getResourcesForApplication(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return getResourcesForApplication(getApplicationInfo(s, 1024));
    }

    public Resources getResourcesForApplicationAsUser(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Call does not support special user #").append(i).toString());
        if("system".equals(s))
            return mContext.mMainThread.getSystemUiContext().getResources();
        ApplicationInfo applicationinfo;
        try
        {
            applicationinfo = mPM.getApplicationInfo(s, 1024, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(applicationinfo == null)
            break MISSING_BLOCK_LABEL_89;
        s = getResourcesForApplication(applicationinfo);
        return s;
        throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("Package ").append(s).append(" doesn't exist").toString());
    }

    public ServiceInfo getServiceInfo(ComponentName componentname, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        ServiceInfo serviceinfo;
        try
        {
            serviceinfo = mPM.getServiceInfo(componentname, i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(serviceinfo != null)
            return serviceinfo;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException(componentname.toString());
    }

    public String getServicesSystemSharedLibraryPackageName()
    {
        String s;
        try
        {
            s = mPM.getServicesSystemSharedLibraryPackageName();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public List getSharedLibraries(int i)
    {
        return getSharedLibrariesAsUser(i, mContext.getUserId());
    }

    public List getSharedLibrariesAsUser(int i, int j)
    {
        Object obj;
        try
        {
            obj = mPM.getSharedLibraries(mContext.getOpPackageName(), i, j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_27;
        return Collections.emptyList();
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
    }

    public String getSharedSystemSharedLibraryPackageName()
    {
        String s;
        try
        {
            s = mPM.getSharedSystemSharedLibraryPackageName();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public KeySet getSigningKeySet(String s)
    {
        Preconditions.checkNotNull(s);
        try
        {
            s = mPM.getSigningKeySet(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public FeatureInfo[] getSystemAvailableFeatures()
    {
        Object obj;
        FeatureInfo afeatureinfo[];
        int i;
        try
        {
            obj = mPM.getSystemAvailableFeatures();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_19;
        return new FeatureInfo[0];
        obj = ((ParceledListSlice) (obj)).getList();
        afeatureinfo = new FeatureInfo[((List) (obj)).size()];
        i = 0;
_L2:
        if(i >= afeatureinfo.length)
            break; /* Loop/switch isn't completed */
        afeatureinfo[i] = (FeatureInfo)((List) (obj)).get(i);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return afeatureinfo;
    }

    public String[] getSystemSharedLibraryNames()
    {
        String as[];
        try
        {
            as = mPM.getSystemSharedLibraryNames();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public CharSequence getText(String s, int i, ApplicationInfo applicationinfo)
    {
        Object obj;
        ResourceName resourcename = new ResourceName(s, i);
        obj = getCachedString(resourcename);
        if(obj != null)
            return ((CharSequence) (obj));
        obj = applicationinfo;
        if(applicationinfo == null)
            try
            {
                obj = getApplicationInfo(s, 1024);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return null;
            }
        applicationinfo = getResourcesForApplication(((ApplicationInfo) (obj))).getText(i);
        putCachedString(resourcename, applicationinfo);
        return applicationinfo;
        applicationinfo;
        Log.w("PackageManager", (new StringBuilder()).append("Failure retrieving text 0x").append(Integer.toHexString(i)).append(" in package ").append(s).toString(), applicationinfo);
_L2:
        return null;
        s;
        Log.w("PackageManager", (new StringBuilder()).append("Failure retrieving resources for ").append(((ApplicationInfo) (obj)).packageName).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int getUidForSharedUser(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        int i;
        try
        {
            i = mPM.getUidForSharedUser(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(i != -1)
            return i;
        else
            throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("No shared userid for user:").append(s).toString());
    }

    public Drawable getUserBadgeForDensity(UserHandle userhandle, int i)
    {
        Drawable drawable = getManagedProfileIconForDensity(userhandle, 0x1080338, i);
        if(drawable == null)
        {
            return null;
        } else
        {
            drawable.setTint(getUserBadgeColor(userhandle));
            return new LayerDrawable(new Drawable[] {
                drawable, getDrawableForDensity(0x1080337, i)
            });
        }
    }

    public Drawable getUserBadgeForDensityNoBackground(UserHandle userhandle, int i)
    {
        Drawable drawable = getManagedProfileIconForDensity(userhandle, 0x1080339, i);
        if(drawable != null)
            drawable.setTint(getUserBadgeColor(userhandle));
        return drawable;
    }

    public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle userhandle, Rect rect, int i)
    {
        userhandle = getUserBadgeForDensity(userhandle, i);
        if(userhandle == null)
            return drawable;
        else
            return getBadgedDrawable(drawable, userhandle, rect, true);
    }

    public Drawable getUserBadgedIcon(Drawable drawable, UserHandle userhandle)
    {
        int i = getBadgeResIdForUser(userhandle.getIdentifier());
        if(i == 0)
            return drawable;
        if(XSpaceUserHandle.isXSpaceUser(userhandle))
            userhandle = getDrawable("system", i, null);
        else
            userhandle = (new LauncherIcons(mContext)).getBadgeDrawable(i, getUserBadgeColor(userhandle));
        return getBadgedDrawable(drawable, userhandle, null, true);
    }

    public CharSequence getUserBadgedLabel(CharSequence charsequence, UserHandle userhandle)
    {
        if(isManagedProfile(userhandle.getIdentifier()))
        {
            int i = getUserManager().getManagedProfileBadge(userhandle.getIdentifier());
            i = CORP_BADGE_LABEL_RES_ID[i % CORP_BADGE_LABEL_RES_ID.length];
            return Resources.getSystem().getString(i, new Object[] {
                charsequence
            });
        } else
        {
            return charsequence;
        }
    }

    UserManager getUserManager()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        UserManager usermanager;
        if(mUserManager == null)
            mUserManager = UserManager.get(mContext);
        usermanager = mUserManager;
        obj;
        JVM INSTR monitorexit ;
        return usermanager;
        Exception exception;
        exception;
        throw exception;
    }

    public VerifierDeviceIdentity getVerifierDeviceIdentity()
    {
        VerifierDeviceIdentity verifierdeviceidentity;
        try
        {
            verifierdeviceidentity = mPM.getVerifierDeviceIdentity();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return verifierdeviceidentity;
    }

    public XmlResourceParser getXml(String s, int i, ApplicationInfo applicationinfo)
    {
        ApplicationInfo applicationinfo1;
        applicationinfo1 = applicationinfo;
        if(applicationinfo == null)
            try
            {
                applicationinfo1 = getApplicationInfo(s, 1024);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return null;
            }
        applicationinfo = getResourcesForApplication(applicationinfo1).getXml(i);
        return applicationinfo;
        s;
        Log.w("PackageManager", (new StringBuilder()).append("Failure retrieving resources for ").append(applicationinfo1.packageName).toString());
_L2:
        return null;
        applicationinfo;
        Log.w("PackageManager", (new StringBuilder()).append("Failure retrieving xml 0x").append(Integer.toHexString(i)).append(" in package ").append(s).toString(), applicationinfo);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void grantRuntimePermission(String s, String s1, UserHandle userhandle)
    {
        try
        {
            mPM.grantRuntimePermission(s, s1, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean hasSystemFeature(String s)
    {
        return hasSystemFeature(s, 0);
    }

    public boolean hasSystemFeature(String s, int i)
    {
        boolean flag;
        try
        {
            flag = mPM.hasSystemFeature(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public int installExistingPackage(String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return installExistingPackage(s, 0);
    }

    public int installExistingPackage(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return installExistingPackageAsUser(s, i, mContext.getUserId());
    }

    public int installExistingPackageAsUser(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        return installExistingPackageAsUser(s, 0, i);
    }

    public void installPackage(Uri uri, PackageInstallObserver packageinstallobserver, int i, String s)
    {
        installCommon(uri, packageinstallobserver, i, s, mContext.getUserId());
    }

    public void installPackage(Uri uri, IPackageInstallObserver ipackageinstallobserver, int i, String s)
    {
        installCommon(uri, new android.content.pm.PackageManager.LegacyPackageInstallObserver(ipackageinstallobserver), i, s, mContext.getUserId());
    }

    protected boolean isAllow3rdPartyOnInternal(Context context)
    {
        return context.getResources().getBoolean(0x1120005);
    }

    protected boolean isForceAllowOnExternal(Context context)
    {
        boolean flag = false;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "force_allow_on_external", 0) != 0)
            flag = true;
        return flag;
    }

    public boolean isInstantApp()
    {
        return isInstantApp(mContext.getPackageName());
    }

    public boolean isInstantApp(String s)
    {
        boolean flag;
        try
        {
            flag = mPM.isInstantApp(s, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isPackageAvailable(String s)
    {
        boolean flag;
        try
        {
            flag = mPM.isPackageAvailable(s, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isPackageSuspendedForUser(String s, int i)
    {
        boolean flag;
        try
        {
            flag = mPM.isPackageSuspendedForUser(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isPermissionReviewModeEnabled()
    {
        return mContext.getResources().getBoolean(0x1120086);
    }

    public boolean isPermissionRevokedByPolicy(String s, String s1)
    {
        boolean flag;
        try
        {
            flag = mPM.isPermissionRevokedByPolicy(s, s1, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSafeMode()
    {
        boolean flag = true;
        if(mCachedSafeMode >= 0)
            break MISSING_BLOCK_LABEL_28;
        int i;
        if(mPM.isSafeMode())
            i = 1;
        else
            i = 0;
        mCachedSafeMode = i;
        i = mCachedSafeMode;
        if(i == 0)
            flag = false;
        return flag;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public boolean isSignedBy(String s, KeySet keyset)
    {
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(keyset);
        boolean flag;
        try
        {
            flag = mPM.isPackageSignedByKeySet(s, keyset);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSignedByExactly(String s, KeySet keyset)
    {
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(keyset);
        boolean flag;
        try
        {
            flag = mPM.isPackageSignedByKeySetExactly(s, keyset);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUpgrade()
    {
        boolean flag;
        try
        {
            flag = mPM.isUpgrade();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public Drawable loadItemIcon(PackageItemInfo packageiteminfo, ApplicationInfo applicationinfo)
    {
        applicationinfo = loadUnbadgedItemIcon(packageiteminfo, applicationinfo);
        if(packageiteminfo.showUserIcon != -10000)
            return applicationinfo;
        else
            return getUserBadgedIcon(applicationinfo, new UserHandle(mContext.getUserId()));
    }

    public Drawable loadUnbadgedItemIcon(PackageItemInfo packageiteminfo, ApplicationInfo applicationinfo)
    {
        if(packageiteminfo.showUserIcon != -10000)
        {
            applicationinfo = getUserManager().getUserIcon(packageiteminfo.showUserIcon);
            if(applicationinfo == null)
                return UserIcons.getDefaultUserIcon(packageiteminfo.showUserIcon, false);
            else
                return new BitmapDrawable(applicationinfo);
        }
        Drawable drawable = null;
        if(packageiteminfo.packageName != null)
            drawable = getDrawable(packageiteminfo.packageName, packageiteminfo.icon, applicationinfo);
        applicationinfo = drawable;
        if(drawable == null)
            applicationinfo = packageiteminfo.loadDefaultIcon(this);
        return applicationinfo;
    }

    public int movePackage(String s, VolumeInfo volumeinfo)
    {
        if(!"private".equals(volumeinfo.id))
            break MISSING_BLOCK_LABEL_29;
        volumeinfo = StorageManager.UUID_PRIVATE_INTERNAL;
_L1:
        return mPM.movePackage(s, volumeinfo);
        if(volumeinfo.isPrimaryPhysical())
            volumeinfo = "primary_physical";
        else
            try
            {
                volumeinfo = (String)Preconditions.checkNotNull(volumeinfo.fsUuid);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
          goto _L1
    }

    public int movePrimaryStorage(VolumeInfo volumeinfo)
    {
        if(!"private".equals(volumeinfo.id))
            break MISSING_BLOCK_LABEL_28;
        volumeinfo = StorageManager.UUID_PRIVATE_INTERNAL;
_L1:
        return mPM.movePrimaryStorage(volumeinfo);
        if(volumeinfo.isPrimaryPhysical())
            volumeinfo = "primary_physical";
        else
            try
            {
                volumeinfo = (String)Preconditions.checkNotNull(volumeinfo.fsUuid);
            }
            // Misplaced declaration of an exception variable
            catch(VolumeInfo volumeinfo)
            {
                throw volumeinfo.rethrowFromSystemServer();
            }
          goto _L1
    }

    public List queryBroadcastReceivers(Intent intent, int i)
    {
        return queryBroadcastReceiversAsUser(intent, i, mContext.getUserId());
    }

    public List queryBroadcastReceiversAsUser(Intent intent, int i, int j)
    {
        try
        {
            intent = mPM.queryIntentReceivers(intent, intent.resolveTypeIfNeeded(mContext.getContentResolver()), i, j);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(intent != null)
            break MISSING_BLOCK_LABEL_32;
        return Collections.emptyList();
        intent = intent.getList();
        return intent;
    }

    public List queryContentProviders(String s, int i, int j)
    {
        return queryContentProviders(s, i, j, null);
    }

    public List queryContentProviders(String s, int i, int j, String s1)
    {
        try
        {
            s = mPM.queryContentProviders(s, i, j, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_26;
        s = s.getList();
_L1:
        return s;
        s = Collections.emptyList();
          goto _L1
    }

    public List queryInstrumentation(String s, int i)
    {
        try
        {
            s = mPM.queryInstrumentation(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s != null)
            break MISSING_BLOCK_LABEL_20;
        return Collections.emptyList();
        s = s.getList();
        return s;
    }

    public List queryIntentActivities(Intent intent, int i)
    {
        return queryIntentActivitiesAsUser(intent, i, mContext.getUserId());
    }

    public List queryIntentActivitiesAsUser(Intent intent, int i, int j)
    {
        try
        {
            intent = mPM.queryIntentActivities(intent, intent.resolveTypeIfNeeded(mContext.getContentResolver()), i, j);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(intent != null)
            break MISSING_BLOCK_LABEL_32;
        return Collections.emptyList();
        intent = intent.getList();
        return intent;
    }

    public List queryIntentActivityOptions(ComponentName componentname, Intent aintent[], Intent intent, int i)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        String as[] = null;
        String as1[] = null;
        if(aintent != null)
        {
            int j = aintent.length;
            int k = 0;
            do
            {
                as = as1;
                if(k >= j)
                    break;
                Object obj = aintent[k];
                as = as1;
                if(obj != null)
                {
                    obj = ((Intent) (obj)).resolveTypeIfNeeded(contentresolver);
                    as = as1;
                    if(obj != null)
                    {
                        as = as1;
                        if(as1 == null)
                            as = new String[j];
                        as[k] = ((String) (obj));
                    }
                }
                k++;
                as1 = as;
            } while(true);
        }
        try
        {
            componentname = mPM.queryIntentActivityOptions(componentname, aintent, as, intent, intent.resolveTypeIfNeeded(contentresolver), i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(componentname != null)
            break MISSING_BLOCK_LABEL_141;
        return Collections.emptyList();
        componentname = componentname.getList();
        return componentname;
    }

    public List queryIntentContentProviders(Intent intent, int i)
    {
        return queryIntentContentProvidersAsUser(intent, i, mContext.getUserId());
    }

    public List queryIntentContentProvidersAsUser(Intent intent, int i, int j)
    {
        try
        {
            intent = mPM.queryIntentContentProviders(intent, intent.resolveTypeIfNeeded(mContext.getContentResolver()), i, j);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(intent != null)
            break MISSING_BLOCK_LABEL_32;
        return Collections.emptyList();
        intent = intent.getList();
        return intent;
    }

    public List queryIntentServices(Intent intent, int i)
    {
        return queryIntentServicesAsUser(intent, i, mContext.getUserId());
    }

    public List queryIntentServicesAsUser(Intent intent, int i, int j)
    {
        try
        {
            intent = mPM.queryIntentServices(intent, intent.resolveTypeIfNeeded(mContext.getContentResolver()), i, j);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(intent != null)
            break MISSING_BLOCK_LABEL_32;
        return Collections.emptyList();
        intent = intent.getList();
        return intent;
    }

    public List queryPermissionsByGroup(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        Object obj;
        try
        {
            obj = mPM.queryPermissionsByGroup(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_33;
        obj = ((ParceledListSlice) (obj)).getList();
        if(obj != null)
            return ((List) (obj));
        throw new android.content.pm.PackageManager.NameNotFoundException(s);
    }

    public void registerDexModule(String s, android.content.pm.PackageManager.DexModuleRegisterCallback dexmoduleregistercallback)
    {
        boolean flag = false;
        DexModuleRegisterCallbackDelegate dexmoduleregistercallbackdelegate;
        int i;
        int j;
        try
        {
            StructStat structstat = Os.stat(s);
            i = OsConstants.S_IROTH;
            j = structstat.st_mode;
        }
        catch(ErrnoException errnoexception)
        {
            dexmoduleregistercallback.onDexModuleRegistered(s, false, (new StringBuilder()).append("Could not get stat the module file: ").append(errnoexception.getMessage()).toString());
            return;
        }
        if((i & j) != 0)
            flag = true;
        dexmoduleregistercallbackdelegate = null;
        if(dexmoduleregistercallback != null)
            dexmoduleregistercallbackdelegate = new DexModuleRegisterCallbackDelegate(dexmoduleregistercallback);
        try
        {
            mPM.registerDexModule(mContext.getPackageName(), s, flag, dexmoduleregistercallbackdelegate);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowAsRuntimeException();
        }
    }

    public void registerMoveCallback(android.content.pm.PackageManager.MoveCallback movecallback, Handler handler)
    {
        ArrayList arraylist = mDelegates;
        arraylist;
        JVM INSTR monitorenter ;
        MoveCallbackDelegate movecallbackdelegate;
        movecallbackdelegate = JVM INSTR new #12  <Class ApplicationPackageManager$MoveCallbackDelegate>;
        movecallbackdelegate.MoveCallbackDelegate(movecallback, handler.getLooper());
        mPM.registerMoveCallback(movecallbackdelegate);
        mDelegates.add(movecallbackdelegate);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        movecallback;
        throw movecallback.rethrowFromSystemServer();
        movecallback;
        arraylist;
        JVM INSTR monitorexit ;
        throw movecallback;
    }

    public void removeOnPermissionsChangeListener(android.content.pm.PackageManager.OnPermissionsChangedListener onpermissionschangedlistener)
    {
        Map map = mPermissionListeners;
        map;
        JVM INSTR monitorenter ;
        IOnPermissionsChangeListener ionpermissionschangelistener = (IOnPermissionsChangeListener)mPermissionListeners.get(onpermissionschangedlistener);
        if(ionpermissionschangelistener == null)
            break MISSING_BLOCK_LABEL_46;
        mPM.removeOnPermissionsChangeListener(ionpermissionschangelistener);
        mPermissionListeners.remove(onpermissionschangedlistener);
        map;
        JVM INSTR monitorexit ;
        return;
        onpermissionschangedlistener;
        throw onpermissionschangedlistener.rethrowFromSystemServer();
        onpermissionschangedlistener;
        map;
        JVM INSTR monitorexit ;
        throw onpermissionschangedlistener;
    }

    public void removePackageFromPreferred(String s)
    {
        Log.w("ApplicationPackageManager", "removePackageFromPreferred() is a no-op");
    }

    public void removePermission(String s)
    {
        try
        {
            mPM.removePermission(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void replacePreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname)
    {
        try
        {
            mPM.replacePreferredActivity(intentfilter, i, acomponentname, componentname, mContext.getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IntentFilter intentfilter)
        {
            throw intentfilter.rethrowFromSystemServer();
        }
    }

    public void replacePreferredActivityAsUser(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
    {
        try
        {
            mPM.replacePreferredActivity(intentfilter, i, acomponentname, componentname, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IntentFilter intentfilter)
        {
            throw intentfilter.rethrowFromSystemServer();
        }
    }

    public ResolveInfo resolveActivity(Intent intent, int i)
    {
        return resolveActivityAsUser(intent, i, mContext.getUserId());
    }

    public ResolveInfo resolveActivityAsUser(Intent intent, int i, int j)
    {
        try
        {
            intent = mPM.resolveIntent(intent, intent.resolveTypeIfNeeded(mContext.getContentResolver()), i, j);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        return intent;
    }

    public ProviderInfo resolveContentProvider(String s, int i)
    {
        return resolveContentProviderAsUser(s, i, mContext.getUserId());
    }

    public ProviderInfo resolveContentProviderAsUser(String s, int i, int j)
    {
        try
        {
            s = mPM.resolveContentProvider(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public ResolveInfo resolveService(Intent intent, int i)
    {
        try
        {
            intent = mPM.resolveService(intent, intent.resolveTypeIfNeeded(mContext.getContentResolver()), i, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        return intent;
    }

    public void revokeRuntimePermission(String s, String s1, UserHandle userhandle)
    {
        try
        {
            mPM.revokeRuntimePermission(s, s1, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setApplicationCategoryHint(String s, int i)
    {
        try
        {
            mPM.setApplicationCategoryHint(s, i, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setApplicationEnabledSetting(String s, int i, int j)
    {
        try
        {
            mPM.setApplicationEnabledSetting(s, i, j, mContext.getUserId(), mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean setApplicationHiddenSettingAsUser(String s, boolean flag, UserHandle userhandle)
    {
        try
        {
            flag = mPM.setApplicationHiddenSettingAsUser(s, flag, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setComponentEnabledSetting(ComponentName componentname, int i, int j)
    {
        try
        {
            mPM.setComponentEnabledSetting(componentname, i, j, mContext.getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public boolean setDefaultBrowserPackageNameAsUser(String s, int i)
    {
        boolean flag;
        try
        {
            flag = mPM.setDefaultBrowserPackageName(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setInstallerPackageName(String s, String s1)
    {
        try
        {
            mPM.setInstallerPackageName(s, s1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean setInstantAppCookie(byte abyte0[])
    {
        boolean flag;
        try
        {
            flag = mPM.setInstantAppCookie(mContext.getPackageName(), abyte0, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
        return flag;
    }

    public String[] setPackagesSuspendedAsUser(String as[], boolean flag, int i)
    {
        try
        {
            as = mPM.setPackagesSuspendedAsUser(as, flag, i);
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw as.rethrowFromSystemServer();
        }
        return as;
    }

    public void setUpdateAvailable(String s, boolean flag)
    {
        try
        {
            mPM.setUpdateAvailable(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean shouldShowRequestPermissionRationale(String s)
    {
        boolean flag;
        try
        {
            flag = mPM.shouldShowRequestPermissionRationale(s, mContext.getPackageName(), mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void unregisterMoveCallback(android.content.pm.PackageManager.MoveCallback movecallback)
    {
        ArrayList arraylist = mDelegates;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mDelegates.iterator();
_L2:
        MoveCallbackDelegate movecallbackdelegate;
        android.content.pm.PackageManager.MoveCallback movecallback1;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_79;
            movecallbackdelegate = (MoveCallbackDelegate)iterator.next();
            movecallback1 = movecallbackdelegate.mCallback;
        } while(movecallback1 != movecallback);
        mPM.unregisterMoveCallback(movecallbackdelegate);
        iterator.remove();
        if(true) goto _L2; else goto _L1
_L1:
        movecallback;
        throw movecallback;
        movecallback;
        throw movecallback.rethrowFromSystemServer();
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void updateInstantAppCookie(byte abyte0[])
    {
        if(abyte0 != null && abyte0.length > getInstantAppCookieMaxBytes())
            throw new IllegalArgumentException((new StringBuilder()).append("instant cookie longer than ").append(getInstantAppCookieMaxBytes()).toString());
        try
        {
            mPM.setInstantAppCookie(mContext.getPackageName(), abyte0, mContext.getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
    }

    public boolean updateIntentVerificationStatusAsUser(String s, int i, int j)
    {
        boolean flag;
        try
        {
            flag = mPM.updateIntentVerificationStatus(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void updatePermissionFlags(String s, String s1, int i, int j, UserHandle userhandle)
    {
        try
        {
            mPM.updatePermissionFlags(s, s1, i, j, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void verifyIntentFilter(int i, int j, List list)
    {
        try
        {
            mPM.verifyIntentFilter(i, j, list);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public void verifyPendingInstall(int i, int j)
    {
        try
        {
            mPM.verifyPendingInstall(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public static final int CORP_BADGE_LABEL_RES_ID[] = {
        0x1040340, 0x1040341, 0x1040342
    };
    private static final boolean DEBUG_ICONS = false;
    private static final int DEFAULT_EPHEMERAL_COOKIE_MAX_SIZE_BYTES = 16384;
    private static final String TAG = "ApplicationPackageManager";
    private static final int sDefaultFlags = 1024;
    private static ArrayMap sIconCache = new ArrayMap();
    private static ArrayMap sStringCache = new ArrayMap();
    private static final Object sSync = new Object();
    volatile int mCachedSafeMode;
    private final ContextImpl mContext;
    private final ArrayList mDelegates = new ArrayList();
    private PackageInstaller mInstaller;
    private final Object mLock = new Object();
    private final IPackageManager mPM;
    private final Map mPermissionListeners = new ArrayMap();
    private String mPermissionsControllerPackageName;
    private UserManager mUserManager;

}
