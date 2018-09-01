// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.app.PackageDeleteObserver;
import android.app.PackageInstallObserver;
import android.content.*;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.os.storage.VolumeInfo;
import android.util.AndroidException;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import dalvik.system.VMRuntime;
import java.io.File;
import java.util.List;

// Referenced classes of package android.content.pm:
//            PackageManagerInjector, PackageParser, PackageUserState, PermissionInfo, 
//            IPackageDataObserver, IPackageDeleteObserver, ActivityInfo, ApplicationInfo, 
//            ChangedPackages, InstrumentationInfo, KeySet, PackageInfo, 
//            VersionedPackage, PackageInstaller, IPackageStatsObserver, PermissionGroupInfo, 
//            ProviderInfo, ServiceInfo, FeatureInfo, VerifierDeviceIdentity, 
//            IPackageInstallObserver, PackageItemInfo, ResolveInfo

public abstract class PackageManager
{
    public static abstract class DexModuleRegisterCallback
    {

        public abstract void onDexModuleRegistered(String s, boolean flag, String s1);

        public DexModuleRegisterCallback()
        {
        }
    }

    public static class LegacyPackageDeleteObserver extends PackageDeleteObserver
    {

        public void onPackageDeleted(String s, int i, String s1)
        {
            if(mLegacy == null)
                return;
            mLegacy.packageDeleted(s, i);
_L2:
            return;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private final IPackageDeleteObserver mLegacy;

        public LegacyPackageDeleteObserver(IPackageDeleteObserver ipackagedeleteobserver)
        {
            mLegacy = ipackagedeleteobserver;
        }
    }

    public static class LegacyPackageInstallObserver extends PackageInstallObserver
    {

        public void onPackageInstalled(String s, int i, String s1, Bundle bundle)
        {
            if(mLegacy == null)
                return;
            mLegacy.packageInstalled(s, i);
_L2:
            return;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private final IPackageInstallObserver mLegacy;

        public LegacyPackageInstallObserver(IPackageInstallObserver ipackageinstallobserver)
        {
            mLegacy = ipackageinstallobserver;
        }
    }

    public static abstract class MoveCallback
    {

        public void onCreated(int i, Bundle bundle)
        {
        }

        public abstract void onStatusChanged(int i, int j, long l);

        public MoveCallback()
        {
        }
    }

    public static class NameNotFoundException extends AndroidException
    {

        public NameNotFoundException()
        {
        }

        public NameNotFoundException(String s)
        {
            super(s);
        }
    }

    public static interface OnPermissionsChangedListener
    {

        public abstract void onPermissionsChanged(int i);
    }


    public PackageManager()
    {
    }

    public static int deleteStatusToPublicStatus(int i)
    {
        switch(i)
        {
        case 0: // '\0'
        default:
            return 1;

        case 1: // '\001'
            return 0;

        case -1: 
            return 1;

        case -2: 
            return 2;

        case -3: 
            return 2;

        case -4: 
            return 2;

        case -5: 
            return 3;

        case -6: 
            return 5;
        }
    }

    public static String deleteStatusToString(int i)
    {
        switch(i)
        {
        case 0: // '\0'
        default:
            return Integer.toString(i);

        case 1: // '\001'
            return "DELETE_SUCCEEDED";

        case -1: 
            return "DELETE_FAILED_INTERNAL_ERROR";

        case -2: 
            return "DELETE_FAILED_DEVICE_POLICY_MANAGER";

        case -3: 
            return "DELETE_FAILED_USER_RESTRICTED";

        case -4: 
            return "DELETE_FAILED_OWNER_BLOCKED";

        case -5: 
            return "DELETE_FAILED_ABORTED";

        case -6: 
            return "DELETE_FAILED_USED_SHARED_LIBRARY";
        }
    }

    public static String deleteStatusToString(int i, String s)
    {
        String s1 = deleteStatusToString(i);
        if(s != null)
            return (new StringBuilder()).append(s1).append(": ").append(s).toString();
        else
            return s1;
    }

    public static int installStatusToPublicStatus(int i)
    {
        switch(i)
        {
        default:
            return 1;

        case 1: // '\001'
            return 0;

        case -1: 
            return 5;

        case -2: 
            return 4;

        case -3: 
            return 4;

        case -4: 
            return 6;

        case -5: 
            return 5;

        case -6: 
            return 5;

        case -7: 
            return 5;

        case -8: 
            return 5;

        case -9: 
            return 7;

        case -10: 
            return 5;

        case -11: 
            return 4;

        case -12: 
            return 7;

        case -13: 
            return 5;

        case -14: 
            return 7;

        case -15: 
            return 4;

        case -16: 
            return 7;

        case -17: 
            return 7;

        case -18: 
            return 6;

        case -19: 
            return 6;

        case -20: 
            return 6;

        case -21: 
            return 3;

        case -22: 
            return 3;

        case -23: 
            return 4;

        case -24: 
            return 4;

        case -25: 
            return 4;

        case -26: 
            return 4;

        case -100: 
            return 4;

        case -101: 
            return 4;

        case -102: 
            return 4;

        case -103: 
            return 4;

        case -104: 
            return 4;

        case -105: 
            return 4;

        case -106: 
            return 4;

        case -107: 
            return 4;

        case -108: 
            return 4;

        case -109: 
            return 4;

        case -110: 
            return 1;

        case -111: 
            return 7;

        case -112: 
            return 5;

        case -113: 
            return 7;

        case -115: 
            return 3;
        }
    }

    public static String installStatusToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 1: // '\001'
            return "INSTALL_SUCCEEDED";

        case -1: 
            return "INSTALL_FAILED_ALREADY_EXISTS";

        case -2: 
            return "INSTALL_FAILED_INVALID_APK";

        case -3: 
            return "INSTALL_FAILED_INVALID_URI";

        case -4: 
            return "INSTALL_FAILED_INSUFFICIENT_STORAGE";

        case -5: 
            return "INSTALL_FAILED_DUPLICATE_PACKAGE";

        case -6: 
            return "INSTALL_FAILED_NO_SHARED_USER";

        case -7: 
            return "INSTALL_FAILED_UPDATE_INCOMPATIBLE";

        case -8: 
            return "INSTALL_FAILED_SHARED_USER_INCOMPATIBLE";

        case -9: 
            return "INSTALL_FAILED_MISSING_SHARED_LIBRARY";

        case -10: 
            return "INSTALL_FAILED_REPLACE_COULDNT_DELETE";

        case -11: 
            return "INSTALL_FAILED_DEXOPT";

        case -12: 
            return "INSTALL_FAILED_OLDER_SDK";

        case -13: 
            return "INSTALL_FAILED_CONFLICTING_PROVIDER";

        case -14: 
            return "INSTALL_FAILED_NEWER_SDK";

        case -15: 
            return "INSTALL_FAILED_TEST_ONLY";

        case -16: 
            return "INSTALL_FAILED_CPU_ABI_INCOMPATIBLE";

        case -17: 
            return "INSTALL_FAILED_MISSING_FEATURE";

        case -18: 
            return "INSTALL_FAILED_CONTAINER_ERROR";

        case -19: 
            return "INSTALL_FAILED_INVALID_INSTALL_LOCATION";

        case -20: 
            return "INSTALL_FAILED_MEDIA_UNAVAILABLE";

        case -21: 
            return "INSTALL_FAILED_VERIFICATION_TIMEOUT";

        case -22: 
            return "INSTALL_FAILED_VERIFICATION_FAILURE";

        case -23: 
            return "INSTALL_FAILED_PACKAGE_CHANGED";

        case -24: 
            return "INSTALL_FAILED_UID_CHANGED";

        case -25: 
            return "INSTALL_FAILED_VERSION_DOWNGRADE";

        case -100: 
            return "INSTALL_PARSE_FAILED_NOT_APK";

        case -101: 
            return "INSTALL_PARSE_FAILED_BAD_MANIFEST";

        case -102: 
            return "INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION";

        case -103: 
            return "INSTALL_PARSE_FAILED_NO_CERTIFICATES";

        case -104: 
            return "INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES";

        case -105: 
            return "INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING";

        case -106: 
            return "INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME";

        case -107: 
            return "INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID";

        case -108: 
            return "INSTALL_PARSE_FAILED_MANIFEST_MALFORMED";

        case -109: 
            return "INSTALL_PARSE_FAILED_MANIFEST_EMPTY";

        case -110: 
            return "INSTALL_FAILED_INTERNAL_ERROR";

        case -111: 
            return "INSTALL_FAILED_USER_RESTRICTED";

        case -112: 
            return "INSTALL_FAILED_DUPLICATE_PERMISSION";

        case -113: 
            return "INSTALL_FAILED_NO_MATCHING_ABIS";

        case -115: 
            return "INSTALL_FAILED_ABORTED";
        }
    }

    public static String installStatusToString(int i, String s)
    {
        String s1 = installStatusToString(i);
        if(s != null)
            return (new StringBuilder()).append(s1).append(": ").append(s).toString();
        else
            return s1;
    }

    public static boolean isMoveStatusFinished(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i >= 0)
            if(i > 100)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static String permissionFlagToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 32: // ' '
            return "GRANTED_BY_DEFAULT";

        case 4: // '\004'
            return "POLICY_FIXED";

        case 16: // '\020'
            return "SYSTEM_FIXED";

        case 1: // '\001'
            return "USER_SET";

        case 8: // '\b'
            return "REVOKE_ON_UPGRADE";

        case 2: // '\002'
            return "USER_FIXED";

        case 64: // '@'
            return "REVIEW_REQUIRED";
        }
    }

    public abstract void addCrossProfileIntentFilter(IntentFilter intentfilter, int i, int j, int k);

    public abstract void addOnPermissionsChangeListener(OnPermissionsChangedListener onpermissionschangedlistener);

    public abstract void addPackageToPreferred(String s);

    public abstract boolean addPermission(PermissionInfo permissioninfo);

    public abstract boolean addPermissionAsync(PermissionInfo permissioninfo);

    public abstract void addPreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname);

    public void addPreferredActivityAsUser(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public Intent buildRequestPermissionsIntent(String as[])
    {
        if(ArrayUtils.isEmpty(as))
        {
            throw new IllegalArgumentException("permission cannot be null or empty");
        } else
        {
            Intent intent = new Intent("android.content.pm.action.REQUEST_PERMISSIONS");
            intent.putExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES", as);
            intent.setPackage(PackageManagerInjector.getPermissionControllerPackageName());
            return intent;
        }
    }

    public abstract boolean canRequestPackageInstalls();

    public abstract String[] canonicalToCurrentPackageNames(String as[]);

    public abstract int checkPermission(String s, String s1);

    public abstract int checkSignatures(int i, int j);

    public abstract int checkSignatures(String s, String s1);

    public abstract void clearApplicationUserData(String s, IPackageDataObserver ipackagedataobserver);

    public abstract void clearCrossProfileIntentFilters(int i);

    public abstract void clearInstantAppCookie();

    public abstract void clearPackagePreferredActivities(String s);

    public abstract String[] currentToCanonicalPackageNames(String as[]);

    public abstract void deleteApplicationCacheFiles(String s, IPackageDataObserver ipackagedataobserver);

    public abstract void deleteApplicationCacheFilesAsUser(String s, int i, IPackageDataObserver ipackagedataobserver);

    public abstract void deletePackage(String s, IPackageDeleteObserver ipackagedeleteobserver, int i);

    public abstract void deletePackageAsUser(String s, IPackageDeleteObserver ipackagedeleteobserver, int i, int j);

    public abstract void extendVerificationTimeout(int i, int j, long l);

    public abstract void flushPackageRestrictionsAsUser(int i);

    public void freeStorage(long l, IntentSender intentsender)
    {
        freeStorage(null, l, intentsender);
    }

    public abstract void freeStorage(String s, long l, IntentSender intentsender);

    public void freeStorageAndNotify(long l, IPackageDataObserver ipackagedataobserver)
    {
        freeStorageAndNotify(null, l, ipackagedataobserver);
    }

    public abstract void freeStorageAndNotify(String s, long l, IPackageDataObserver ipackagedataobserver);

    public abstract Drawable getActivityBanner(ComponentName componentname)
        throws NameNotFoundException;

    public abstract Drawable getActivityBanner(Intent intent)
        throws NameNotFoundException;

    public abstract Drawable getActivityIcon(ComponentName componentname)
        throws NameNotFoundException;

    public abstract Drawable getActivityIcon(Intent intent)
        throws NameNotFoundException;

    public abstract ActivityInfo getActivityInfo(ComponentName componentname, int i)
        throws NameNotFoundException;

    public abstract Drawable getActivityLogo(ComponentName componentname)
        throws NameNotFoundException;

    public abstract Drawable getActivityLogo(Intent intent)
        throws NameNotFoundException;

    public abstract List getAllIntentFilters(String s);

    public abstract List getAllPermissionGroups(int i);

    public abstract Drawable getApplicationBanner(ApplicationInfo applicationinfo);

    public abstract Drawable getApplicationBanner(String s)
        throws NameNotFoundException;

    public abstract int getApplicationEnabledSetting(String s);

    public abstract boolean getApplicationHiddenSettingAsUser(String s, UserHandle userhandle);

    public abstract Drawable getApplicationIcon(ApplicationInfo applicationinfo);

    public abstract Drawable getApplicationIcon(String s)
        throws NameNotFoundException;

    public abstract ApplicationInfo getApplicationInfo(String s, int i)
        throws NameNotFoundException;

    public abstract ApplicationInfo getApplicationInfoAsUser(String s, int i, int j)
        throws NameNotFoundException;

    public abstract CharSequence getApplicationLabel(ApplicationInfo applicationinfo);

    public abstract Drawable getApplicationLogo(ApplicationInfo applicationinfo);

    public abstract Drawable getApplicationLogo(String s)
        throws NameNotFoundException;

    public abstract ChangedPackages getChangedPackages(int i);

    public abstract int getComponentEnabledSetting(ComponentName componentname);

    public abstract Drawable getDefaultActivityIcon();

    public abstract String getDefaultBrowserPackageNameAsUser(int i);

    public abstract Drawable getDrawable(String s, int i, ApplicationInfo applicationinfo);

    public abstract ComponentName getHomeActivities(List list);

    public abstract int getInstallReason(String s, UserHandle userhandle);

    public abstract List getInstalledApplications(int i);

    public abstract List getInstalledApplicationsAsUser(int i, int j);

    public abstract List getInstalledPackages(int i);

    public abstract List getInstalledPackagesAsUser(int i, int j);

    public abstract String getInstallerPackageName(String s);

    public abstract String getInstantAppAndroidId(String s, UserHandle userhandle);

    public abstract byte[] getInstantAppCookie();

    public abstract int getInstantAppCookieMaxBytes();

    public abstract int getInstantAppCookieMaxSize();

    public abstract Drawable getInstantAppIcon(String s);

    public abstract ComponentName getInstantAppInstallerComponent();

    public abstract ComponentName getInstantAppResolverSettingsComponent();

    public abstract List getInstantApps();

    public abstract InstrumentationInfo getInstrumentationInfo(ComponentName componentname, int i)
        throws NameNotFoundException;

    public abstract List getIntentFilterVerifications(String s);

    public abstract int getIntentVerificationStatusAsUser(String s, int i);

    public abstract KeySet getKeySetByAlias(String s, String s1);

    public abstract Intent getLaunchIntentForPackage(String s);

    public abstract Intent getLeanbackLaunchIntentForPackage(String s);

    public abstract int getMoveStatus(int i);

    public abstract String getNameForUid(int i);

    public abstract String[] getNamesForUids(int ai[]);

    public PackageInfo getPackageArchiveInfo(String s, int i)
    {
        Object obj = new PackageParser();
        ((PackageParser) (obj)).setCallback(new PackageParser.CallbackImpl(this));
        s = new File(s);
        if((i & 0xc0000) == 0)
            i |= 0xc0000;
        try
        {
            obj = ((PackageParser) (obj)).parseMonolithicPackage(s, 0);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        if((i & 0x40) == 0)
            break MISSING_BLOCK_LABEL_56;
        PackageParser.collectCertificates(((PackageParser.Package) (obj)), 0);
        s = JVM INSTR new #949 <Class PackageUserState>;
        s.PackageUserState();
        s = PackageParser.generatePackageInfo(((PackageParser.Package) (obj)), null, i, 0L, 0L, null, s);
        return s;
    }

    public abstract List getPackageCandidateVolumes(ApplicationInfo applicationinfo);

    public abstract VolumeInfo getPackageCurrentVolume(ApplicationInfo applicationinfo);

    public abstract int[] getPackageGids(String s)
        throws NameNotFoundException;

    public abstract int[] getPackageGids(String s, int i)
        throws NameNotFoundException;

    public abstract PackageInfo getPackageInfo(VersionedPackage versionedpackage, int i)
        throws NameNotFoundException;

    public abstract PackageInfo getPackageInfo(String s, int i)
        throws NameNotFoundException;

    public abstract PackageInfo getPackageInfoAsUser(String s, int i, int j)
        throws NameNotFoundException;

    public abstract PackageInstaller getPackageInstaller();

    public void getPackageSizeInfo(String s, IPackageStatsObserver ipackagestatsobserver)
    {
        getPackageSizeInfoAsUser(s, UserHandle.myUserId(), ipackagestatsobserver);
    }

    public abstract void getPackageSizeInfoAsUser(String s, int i, IPackageStatsObserver ipackagestatsobserver);

    public abstract int getPackageUid(String s, int i)
        throws NameNotFoundException;

    public abstract int getPackageUidAsUser(String s, int i)
        throws NameNotFoundException;

    public abstract int getPackageUidAsUser(String s, int i, int j)
        throws NameNotFoundException;

    public abstract String[] getPackagesForUid(int i);

    public abstract List getPackagesHoldingPermissions(String as[], int i);

    public abstract String getPermissionControllerPackageName();

    public abstract int getPermissionFlags(String s, String s1, UserHandle userhandle);

    public abstract PermissionGroupInfo getPermissionGroupInfo(String s, int i)
        throws NameNotFoundException;

    public abstract PermissionInfo getPermissionInfo(String s, int i)
        throws NameNotFoundException;

    public abstract int getPreferredActivities(List list, List list1, String s);

    public abstract List getPreferredPackages(int i);

    public abstract List getPrimaryStorageCandidateVolumes();

    public abstract VolumeInfo getPrimaryStorageCurrentVolume();

    public abstract ProviderInfo getProviderInfo(ComponentName componentname, int i)
        throws NameNotFoundException;

    public abstract ActivityInfo getReceiverInfo(ComponentName componentname, int i)
        throws NameNotFoundException;

    public abstract Resources getResourcesForActivity(ComponentName componentname)
        throws NameNotFoundException;

    public abstract Resources getResourcesForApplication(ApplicationInfo applicationinfo)
        throws NameNotFoundException;

    public abstract Resources getResourcesForApplication(String s)
        throws NameNotFoundException;

    public abstract Resources getResourcesForApplicationAsUser(String s, int i)
        throws NameNotFoundException;

    public abstract ServiceInfo getServiceInfo(ComponentName componentname, int i)
        throws NameNotFoundException;

    public abstract String getServicesSystemSharedLibraryPackageName();

    public abstract List getSharedLibraries(int i);

    public abstract List getSharedLibrariesAsUser(int i, int j);

    public abstract String getSharedSystemSharedLibraryPackageName();

    public abstract KeySet getSigningKeySet(String s);

    public abstract FeatureInfo[] getSystemAvailableFeatures();

    public abstract String[] getSystemSharedLibraryNames();

    public abstract CharSequence getText(String s, int i, ApplicationInfo applicationinfo);

    public abstract int getUidForSharedUser(String s)
        throws NameNotFoundException;

    public abstract Drawable getUserBadgeForDensity(UserHandle userhandle, int i);

    public abstract Drawable getUserBadgeForDensityNoBackground(UserHandle userhandle, int i);

    public abstract Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle userhandle, Rect rect, int i);

    public abstract Drawable getUserBadgedIcon(Drawable drawable, UserHandle userhandle);

    public abstract CharSequence getUserBadgedLabel(CharSequence charsequence, UserHandle userhandle);

    public abstract VerifierDeviceIdentity getVerifierDeviceIdentity();

    public abstract XmlResourceParser getXml(String s, int i, ApplicationInfo applicationinfo);

    public abstract void grantRuntimePermission(String s, String s1, UserHandle userhandle);

    public abstract boolean hasSystemFeature(String s);

    public abstract boolean hasSystemFeature(String s, int i);

    public abstract int installExistingPackage(String s)
        throws NameNotFoundException;

    public abstract int installExistingPackage(String s, int i)
        throws NameNotFoundException;

    public abstract int installExistingPackageAsUser(String s, int i)
        throws NameNotFoundException;

    public abstract void installPackage(Uri uri, PackageInstallObserver packageinstallobserver, int i, String s);

    public abstract void installPackage(Uri uri, IPackageInstallObserver ipackageinstallobserver, int i, String s);

    public abstract boolean isInstantApp();

    public abstract boolean isInstantApp(String s);

    public abstract boolean isPackageAvailable(String s);

    public abstract boolean isPackageSuspendedForUser(String s, int i);

    public abstract boolean isPermissionReviewModeEnabled();

    public abstract boolean isPermissionRevokedByPolicy(String s, String s1);

    public abstract boolean isSafeMode();

    public abstract boolean isSignedBy(String s, KeySet keyset);

    public abstract boolean isSignedByExactly(String s, KeySet keyset);

    public abstract boolean isUpgrade();

    public abstract Drawable loadItemIcon(PackageItemInfo packageiteminfo, ApplicationInfo applicationinfo);

    public abstract Drawable loadUnbadgedItemIcon(PackageItemInfo packageiteminfo, ApplicationInfo applicationinfo);

    public abstract int movePackage(String s, VolumeInfo volumeinfo);

    public abstract int movePrimaryStorage(VolumeInfo volumeinfo);

    public abstract List queryBroadcastReceivers(Intent intent, int i);

    public List queryBroadcastReceivers(Intent intent, int i, int j)
    {
        if(VMRuntime.getRuntime().getTargetSdkVersion() >= 26)
        {
            throw new UnsupportedOperationException("Shame on you for calling the hidden API queryBroadcastReceivers(). Shame!");
        } else
        {
            Log.d("PackageManager", "Shame on you for calling the hidden API queryBroadcastReceivers(). Shame!");
            return queryBroadcastReceiversAsUser(intent, i, j);
        }
    }

    public abstract List queryBroadcastReceiversAsUser(Intent intent, int i, int j);

    public List queryBroadcastReceiversAsUser(Intent intent, int i, UserHandle userhandle)
    {
        return queryBroadcastReceiversAsUser(intent, i, userhandle.getIdentifier());
    }

    public abstract List queryContentProviders(String s, int i, int j);

    public List queryContentProviders(String s, int i, int j, String s1)
    {
        return queryContentProviders(s, i, j);
    }

    public abstract List queryInstrumentation(String s, int i);

    public abstract List queryIntentActivities(Intent intent, int i);

    public abstract List queryIntentActivitiesAsUser(Intent intent, int i, int j);

    public abstract List queryIntentActivityOptions(ComponentName componentname, Intent aintent[], Intent intent, int i);

    public abstract List queryIntentContentProviders(Intent intent, int i);

    public abstract List queryIntentContentProvidersAsUser(Intent intent, int i, int j);

    public abstract List queryIntentServices(Intent intent, int i);

    public abstract List queryIntentServicesAsUser(Intent intent, int i, int j);

    public abstract List queryPermissionsByGroup(String s, int i)
        throws NameNotFoundException;

    public abstract void registerDexModule(String s, DexModuleRegisterCallback dexmoduleregistercallback);

    public abstract void registerMoveCallback(MoveCallback movecallback, Handler handler);

    public abstract void removeOnPermissionsChangeListener(OnPermissionsChangedListener onpermissionschangedlistener);

    public abstract void removePackageFromPreferred(String s);

    public abstract void removePermission(String s);

    public abstract void replacePreferredActivity(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname);

    public void replacePreferredActivityAsUser(IntentFilter intentfilter, int i, ComponentName acomponentname[], ComponentName componentname, int j)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public abstract ResolveInfo resolveActivity(Intent intent, int i);

    public abstract ResolveInfo resolveActivityAsUser(Intent intent, int i, int j);

    public abstract ProviderInfo resolveContentProvider(String s, int i);

    public abstract ProviderInfo resolveContentProviderAsUser(String s, int i, int j);

    public abstract ResolveInfo resolveService(Intent intent, int i);

    public abstract void revokeRuntimePermission(String s, String s1, UserHandle userhandle);

    public abstract void setApplicationCategoryHint(String s, int i);

    public abstract void setApplicationEnabledSetting(String s, int i, int j);

    public abstract boolean setApplicationHiddenSettingAsUser(String s, boolean flag, UserHandle userhandle);

    public abstract void setComponentEnabledSetting(ComponentName componentname, int i, int j);

    public abstract boolean setDefaultBrowserPackageNameAsUser(String s, int i);

    public abstract void setInstallerPackageName(String s, String s1);

    public abstract boolean setInstantAppCookie(byte abyte0[]);

    public abstract String[] setPackagesSuspendedAsUser(String as[], boolean flag, int i);

    public abstract void setUpdateAvailable(String s, boolean flag);

    public abstract boolean shouldShowRequestPermissionRationale(String s);

    public abstract void unregisterMoveCallback(MoveCallback movecallback);

    public abstract void updateInstantAppCookie(byte abyte0[]);

    public abstract boolean updateIntentVerificationStatusAsUser(String s, int i, int j);

    public abstract void updatePermissionFlags(String s, String s1, int i, int j, UserHandle userhandle);

    public abstract void verifyIntentFilter(int i, int j, List list);

    public abstract void verifyPendingInstall(int i, int j);

    public static final String ACTION_CLEAN_EXTERNAL_STORAGE = "android.content.pm.CLEAN_EXTERNAL_STORAGE";
    public static final String ACTION_REQUEST_PERMISSIONS = "android.content.pm.action.REQUEST_PERMISSIONS";
    public static final boolean APPLY_DEFAULT_TO_DEVICE_PROTECTED_STORAGE = true;
    public static final int COMPONENT_ENABLED_STATE_DEFAULT = 0;
    public static final int COMPONENT_ENABLED_STATE_DISABLED = 2;
    public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED = 4;
    public static final int COMPONENT_ENABLED_STATE_DISABLED_USER = 3;
    public static final int COMPONENT_ENABLED_STATE_ENABLED = 1;
    public static final int DELETE_ALL_USERS = 2;
    public static final int DELETE_DONT_KILL_APP = 8;
    public static final int DELETE_FAILED_ABORTED = -5;
    public static final int DELETE_FAILED_DEVICE_POLICY_MANAGER = -2;
    public static final int DELETE_FAILED_INTERNAL_ERROR = -1;
    public static final int DELETE_FAILED_OWNER_BLOCKED = -4;
    public static final int DELETE_FAILED_USED_SHARED_LIBRARY = -6;
    public static final int DELETE_FAILED_USER_RESTRICTED = -3;
    public static final int DELETE_KEEP_DATA = 1;
    public static final int DELETE_SUCCEEDED = 1;
    public static final int DELETE_SYSTEM_APP = 4;
    public static final int DONT_KILL_APP = 1;
    public static final String EXTRA_FAILURE_EXISTING_PACKAGE = "android.content.pm.extra.FAILURE_EXISTING_PACKAGE";
    public static final String EXTRA_FAILURE_EXISTING_PERMISSION = "android.content.pm.extra.FAILURE_EXISTING_PERMISSION";
    public static final String EXTRA_INTENT_FILTER_VERIFICATION_HOSTS = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_HOSTS";
    public static final String EXTRA_INTENT_FILTER_VERIFICATION_ID = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_ID";
    public static final String EXTRA_INTENT_FILTER_VERIFICATION_PACKAGE_NAME = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_PACKAGE_NAME";
    public static final String EXTRA_INTENT_FILTER_VERIFICATION_URI_SCHEME = "android.content.pm.extra.INTENT_FILTER_VERIFICATION_URI_SCHEME";
    public static final String EXTRA_MOVE_ID = "android.content.pm.extra.MOVE_ID";
    public static final String EXTRA_REQUEST_PERMISSIONS_NAMES = "android.content.pm.extra.REQUEST_PERMISSIONS_NAMES";
    public static final String EXTRA_REQUEST_PERMISSIONS_RESULTS = "android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS";
    public static final String EXTRA_VERIFICATION_ID = "android.content.pm.extra.VERIFICATION_ID";
    public static final String EXTRA_VERIFICATION_INSTALLER_PACKAGE = "android.content.pm.extra.VERIFICATION_INSTALLER_PACKAGE";
    public static final String EXTRA_VERIFICATION_INSTALLER_UID = "android.content.pm.extra.VERIFICATION_INSTALLER_UID";
    public static final String EXTRA_VERIFICATION_INSTALL_FLAGS = "android.content.pm.extra.VERIFICATION_INSTALL_FLAGS";
    public static final String EXTRA_VERIFICATION_PACKAGE_NAME = "android.content.pm.extra.VERIFICATION_PACKAGE_NAME";
    public static final String EXTRA_VERIFICATION_RESULT = "android.content.pm.extra.VERIFICATION_RESULT";
    public static final String EXTRA_VERIFICATION_URI = "android.content.pm.extra.VERIFICATION_URI";
    public static final String EXTRA_VERIFICATION_VERSION_CODE = "android.content.pm.extra.VERIFICATION_VERSION_CODE";
    public static final String FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS = "android.software.activities_on_secondary_displays";
    public static final String FEATURE_APP_WIDGETS = "android.software.app_widgets";
    public static final String FEATURE_AUDIO_LOW_LATENCY = "android.hardware.audio.low_latency";
    public static final String FEATURE_AUDIO_OUTPUT = "android.hardware.audio.output";
    public static final String FEATURE_AUDIO_PRO = "android.hardware.audio.pro";
    public static final String FEATURE_AUTOFILL = "android.software.autofill";
    public static final String FEATURE_AUTOMOTIVE = "android.hardware.type.automotive";
    public static final String FEATURE_BACKUP = "android.software.backup";
    public static final String FEATURE_BLUETOOTH = "android.hardware.bluetooth";
    public static final String FEATURE_BLUETOOTH_LE = "android.hardware.bluetooth_le";
    public static final String FEATURE_BROADCAST_RADIO = "android.hardware.broadcastradio";
    public static final String FEATURE_CAMERA = "android.hardware.camera";
    public static final String FEATURE_CAMERA_ANY = "android.hardware.camera.any";
    public static final String FEATURE_CAMERA_AUTOFOCUS = "android.hardware.camera.autofocus";
    public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_POST_PROCESSING = "android.hardware.camera.capability.manual_post_processing";
    public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_SENSOR = "android.hardware.camera.capability.manual_sensor";
    public static final String FEATURE_CAMERA_CAPABILITY_RAW = "android.hardware.camera.capability.raw";
    public static final String FEATURE_CAMERA_EXTERNAL = "android.hardware.camera.external";
    public static final String FEATURE_CAMERA_FLASH = "android.hardware.camera.flash";
    public static final String FEATURE_CAMERA_FRONT = "android.hardware.camera.front";
    public static final String FEATURE_CAMERA_LEVEL_FULL = "android.hardware.camera.level.full";
    public static final String FEATURE_COMPANION_DEVICE_SETUP = "android.software.companion_device_setup";
    public static final String FEATURE_CONNECTION_SERVICE = "android.software.connectionservice";
    public static final String FEATURE_CONSUMER_IR = "android.hardware.consumerir";
    public static final String FEATURE_CTS = "android.software.cts";
    public static final String FEATURE_DEVICE_ADMIN = "android.software.device_admin";
    public static final String FEATURE_EMBEDDED = "android.hardware.type.embedded";
    public static final String FEATURE_ETHERNET = "android.hardware.ethernet";
    public static final String FEATURE_FAKETOUCH = "android.hardware.faketouch";
    public static final String FEATURE_FAKETOUCH_MULTITOUCH_DISTINCT = "android.hardware.faketouch.multitouch.distinct";
    public static final String FEATURE_FAKETOUCH_MULTITOUCH_JAZZHAND = "android.hardware.faketouch.multitouch.jazzhand";
    public static final String FEATURE_FILE_BASED_ENCRYPTION = "android.software.file_based_encryption";
    public static final String FEATURE_FINGERPRINT = "android.hardware.fingerprint";
    public static final String FEATURE_FREEFORM_WINDOW_MANAGEMENT = "android.software.freeform_window_management";
    public static final String FEATURE_GAMEPAD = "android.hardware.gamepad";
    public static final String FEATURE_HDMI_CEC = "android.hardware.hdmi.cec";
    public static final String FEATURE_HIFI_SENSORS = "android.hardware.sensor.hifi_sensors";
    public static final String FEATURE_HOME_SCREEN = "android.software.home_screen";
    public static final String FEATURE_INPUT_METHODS = "android.software.input_methods";
    public static final String FEATURE_LEANBACK = "android.software.leanback";
    public static final String FEATURE_LEANBACK_ONLY = "android.software.leanback_only";
    public static final String FEATURE_LIVE_TV = "android.software.live_tv";
    public static final String FEATURE_LIVE_WALLPAPER = "android.software.live_wallpaper";
    public static final String FEATURE_LOCATION = "android.hardware.location";
    public static final String FEATURE_LOCATION_GPS = "android.hardware.location.gps";
    public static final String FEATURE_LOCATION_NETWORK = "android.hardware.location.network";
    public static final String FEATURE_LOWPAN = "android.hardware.lowpan";
    public static final String FEATURE_MANAGED_PROFILES = "android.software.managed_users";
    public static final String FEATURE_MANAGED_USERS = "android.software.managed_users";
    public static final String FEATURE_MICROPHONE = "android.hardware.microphone";
    public static final String FEATURE_MIDI = "android.software.midi";
    public static final String FEATURE_NFC = "android.hardware.nfc";
    public static final String FEATURE_NFC_ANY = "android.hardware.nfc.any";
    public static final String FEATURE_NFC_HCE = "android.hardware.nfc.hce";
    public static final String FEATURE_NFC_HOST_CARD_EMULATION = "android.hardware.nfc.hce";
    public static final String FEATURE_NFC_HOST_CARD_EMULATION_NFCF = "android.hardware.nfc.hcef";
    public static final String FEATURE_OPENGLES_EXTENSION_PACK = "android.hardware.opengles.aep";
    public static final String FEATURE_PC = "android.hardware.type.pc";
    public static final String FEATURE_PICTURE_IN_PICTURE = "android.software.picture_in_picture";
    public static final String FEATURE_PRINTING = "android.software.print";
    public static final String FEATURE_RAM_LOW = "android.hardware.ram.low";
    public static final String FEATURE_RAM_NORMAL = "android.hardware.ram.normal";
    public static final String FEATURE_SCREEN_LANDSCAPE = "android.hardware.screen.landscape";
    public static final String FEATURE_SCREEN_PORTRAIT = "android.hardware.screen.portrait";
    public static final String FEATURE_SECURELY_REMOVES_USERS = "android.software.securely_removes_users";
    public static final String FEATURE_SENSOR_ACCELEROMETER = "android.hardware.sensor.accelerometer";
    public static final String FEATURE_SENSOR_AMBIENT_TEMPERATURE = "android.hardware.sensor.ambient_temperature";
    public static final String FEATURE_SENSOR_BAROMETER = "android.hardware.sensor.barometer";
    public static final String FEATURE_SENSOR_COMPASS = "android.hardware.sensor.compass";
    public static final String FEATURE_SENSOR_GYROSCOPE = "android.hardware.sensor.gyroscope";
    public static final String FEATURE_SENSOR_HEART_RATE = "android.hardware.sensor.heartrate";
    public static final String FEATURE_SENSOR_HEART_RATE_ECG = "android.hardware.sensor.heartrate.ecg";
    public static final String FEATURE_SENSOR_LIGHT = "android.hardware.sensor.light";
    public static final String FEATURE_SENSOR_PROXIMITY = "android.hardware.sensor.proximity";
    public static final String FEATURE_SENSOR_RELATIVE_HUMIDITY = "android.hardware.sensor.relative_humidity";
    public static final String FEATURE_SENSOR_STEP_COUNTER = "android.hardware.sensor.stepcounter";
    public static final String FEATURE_SENSOR_STEP_DETECTOR = "android.hardware.sensor.stepdetector";
    public static final String FEATURE_SIP = "android.software.sip";
    public static final String FEATURE_SIP_VOIP = "android.software.sip.voip";
    public static final String FEATURE_TELEPHONY = "android.hardware.telephony";
    public static final String FEATURE_TELEPHONY_CARRIERLOCK = "android.hardware.telephony.carrierlock";
    public static final String FEATURE_TELEPHONY_CDMA = "android.hardware.telephony.cdma";
    public static final String FEATURE_TELEPHONY_EUICC = "android.hardware.telephony.euicc";
    public static final String FEATURE_TELEPHONY_GSM = "android.hardware.telephony.gsm";
    public static final String FEATURE_TELEVISION = "android.hardware.type.television";
    public static final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen";
    public static final String FEATURE_TOUCHSCREEN_MULTITOUCH = "android.hardware.touchscreen.multitouch";
    public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT = "android.hardware.touchscreen.multitouch.distinct";
    public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND = "android.hardware.touchscreen.multitouch.jazzhand";
    public static final String FEATURE_USB_ACCESSORY = "android.hardware.usb.accessory";
    public static final String FEATURE_USB_HOST = "android.hardware.usb.host";
    public static final String FEATURE_VERIFIED_BOOT = "android.software.verified_boot";
    public static final String FEATURE_VOICE_RECOGNIZERS = "android.software.voice_recognizers";
    public static final String FEATURE_VR_HEADTRACKING = "android.hardware.vr.headtracking";
    public static final String FEATURE_VR_MODE = "android.software.vr.mode";
    public static final String FEATURE_VR_MODE_HIGH_PERFORMANCE = "android.hardware.vr.high_performance";
    public static final String FEATURE_VULKAN_HARDWARE_COMPUTE = "android.hardware.vulkan.compute";
    public static final String FEATURE_VULKAN_HARDWARE_LEVEL = "android.hardware.vulkan.level";
    public static final String FEATURE_VULKAN_HARDWARE_VERSION = "android.hardware.vulkan.version";
    public static final String FEATURE_WATCH = "android.hardware.type.watch";
    public static final String FEATURE_WEBVIEW = "android.software.webview";
    public static final String FEATURE_WIFI = "android.hardware.wifi";
    public static final String FEATURE_WIFI_AWARE = "android.hardware.wifi.aware";
    public static final String FEATURE_WIFI_DIRECT = "android.hardware.wifi.direct";
    public static final String FEATURE_WIFI_PASSPOINT = "android.hardware.wifi.passpoint";
    public static final int FLAG_PERMISSION_GRANTED_BY_DEFAULT = 32;
    public static final int FLAG_PERMISSION_POLICY_FIXED = 4;
    public static final int FLAG_PERMISSION_REVIEW_REQUIRED = 64;
    public static final int FLAG_PERMISSION_REVOKE_ON_UPGRADE = 8;
    public static final int FLAG_PERMISSION_SYSTEM_FIXED = 16;
    public static final int FLAG_PERMISSION_USER_FIXED = 2;
    public static final int FLAG_PERMISSION_USER_SET = 1;
    public static final int GET_ACTIVITIES = 1;
    public static final int GET_CONFIGURATIONS = 16384;
    public static final int GET_DISABLED_COMPONENTS = 512;
    public static final int GET_DISABLED_UNTIL_USED_COMPONENTS = 32768;
    public static final int GET_GIDS = 256;
    public static final int GET_INSTRUMENTATION = 16;
    public static final int GET_INTENT_FILTERS = 32;
    public static final int GET_META_DATA = 128;
    public static final int GET_PERMISSIONS = 4096;
    public static final int GET_PROVIDERS = 8;
    public static final int GET_RECEIVERS = 2;
    public static final int GET_RESOLVED_FILTER = 64;
    public static final int GET_SERVICES = 4;
    public static final int GET_SHARED_LIBRARY_FILES = 1024;
    public static final int GET_SIGNATURES = 64;
    public static final int GET_UNINSTALLED_PACKAGES = 8192;
    public static final int GET_URI_PERMISSION_PATTERNS = 2048;
    public static final int INSTALL_ALLOCATE_AGGRESSIVE = 32768;
    public static final int INSTALL_ALLOW_DOWNGRADE = 128;
    public static final int INSTALL_ALLOW_TEST = 4;
    public static final int INSTALL_ALL_USERS = 64;
    public static final int INSTALL_DONT_KILL_APP = 4096;
    public static final int INSTALL_EXTERNAL = 8;
    public static final int INSTALL_FAILED_ABORTED = -115;
    public static final int INSTALL_FAILED_ALREADY_EXISTS = -1;
    public static final int INSTALL_FAILED_CONFLICTING_PROVIDER = -13;
    public static final int INSTALL_FAILED_CONTAINER_ERROR = -18;
    public static final int INSTALL_FAILED_CONTAIN_VIRUS = -28;
    public static final int INSTALL_FAILED_CPU_ABI_INCOMPATIBLE = -16;
    public static final int INSTALL_FAILED_DEXOPT = -11;
    public static final int INSTALL_FAILED_DUPLICATE_PACKAGE = -5;
    public static final int INSTALL_FAILED_DUPLICATE_PERMISSION = -112;
    public static final int INSTALL_FAILED_INSTANT_APP_INVALID = -116;
    public static final int INSTALL_FAILED_INSUFFICIENT_STORAGE = -4;
    public static final int INSTALL_FAILED_INTERNAL_ERROR = -110;
    public static final int INSTALL_FAILED_INVALID_APK = -2;
    public static final int INSTALL_FAILED_INVALID_INSTALL_LOCATION = -19;
    public static final int INSTALL_FAILED_INVALID_URI = -3;
    public static final int INSTALL_FAILED_MEDIA_UNAVAILABLE = -20;
    public static final int INSTALL_FAILED_MISSING_FEATURE = -17;
    public static final int INSTALL_FAILED_MISSING_SHARED_LIBRARY = -9;
    public static final int INSTALL_FAILED_MIUI_CPU_ABI_INCOMPATIBLE = -53;
    public static final int INSTALL_FAILED_MIUI_SDK_INCOMPATIBLE = -51;
    public static final int INSTALL_FAILED_NEWER_SDK = -14;
    public static final int INSTALL_FAILED_NO_MATCHING_ABIS = -113;
    public static final int INSTALL_FAILED_NO_SHARED_USER = -6;
    public static final int INSTALL_FAILED_OLDER_SDK = -12;
    public static final int INSTALL_FAILED_PACKAGE_CHANGED = -23;
    public static final int INSTALL_FAILED_PERMISSION_MODEL_DOWNGRADE = -26;
    public static final int INSTALL_FAILED_REPLACE_COULDNT_DELETE = -10;
    public static final int INSTALL_FAILED_SANDBOX_VERSION_DOWNGRADE = -27;
    public static final int INSTALL_FAILED_SHARED_USER_INCOMPATIBLE = -8;
    public static final int INSTALL_FAILED_SYSTEM_INCOMPATIBLE = -29;
    public static final int INSTALL_FAILED_TEST_ONLY = -15;
    public static final int INSTALL_FAILED_UID_CHANGED = -24;
    public static final int INSTALL_FAILED_UPDATE_INCOMPATIBLE = -7;
    public static final int INSTALL_FAILED_USER_RESTRICTED = -111;
    public static final int INSTALL_FAILED_VERIFICATION_FAILURE = -22;
    public static final int INSTALL_FAILED_VERIFICATION_TIMEOUT = -21;
    public static final int INSTALL_FAILED_VERSION_DOWNGRADE = -25;
    public static final int INSTALL_FORCE_PERMISSION_PROMPT = 1024;
    public static final int INSTALL_FORCE_SDK = 8192;
    public static final int INSTALL_FORCE_VOLUME_UUID = 512;
    public static final int INSTALL_FORWARD_LOCK = 1;
    public static final int INSTALL_FROM_ADB = 32;
    public static final int INSTALL_FROM_XIAOMI = 16384;
    public static final int INSTALL_FULL_APP = 16384;
    public static final int INSTALL_GRANT_RUNTIME_PERMISSIONS = 256;
    public static final int INSTALL_INSTANT_APP = 2048;
    public static final int INSTALL_INTERNAL = 16;
    public static final int INSTALL_PARSE_FAILED_BAD_MANIFEST = -101;
    public static final int INSTALL_PARSE_FAILED_BAD_MIUI_MANIFEST = -52;
    public static final int INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME = -106;
    public static final int INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID = -107;
    public static final int INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING = -105;
    public static final int INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES = -104;
    public static final int INSTALL_PARSE_FAILED_MANIFEST_EMPTY = -109;
    public static final int INSTALL_PARSE_FAILED_MANIFEST_MALFORMED = -108;
    public static final int INSTALL_PARSE_FAILED_NOT_APK = -100;
    public static final int INSTALL_PARSE_FAILED_NO_CERTIFICATES = -103;
    public static final int INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION = -102;
    public static final int INSTALL_REASON_DEVICE_RESTORE = 2;
    public static final int INSTALL_REASON_DEVICE_SETUP = 3;
    public static final int INSTALL_REASON_POLICY = 1;
    public static final int INSTALL_REASON_UNKNOWN = 0;
    public static final int INSTALL_REASON_USER = 4;
    public static final int INSTALL_REPLACE_EXISTING = 2;
    public static final int INSTALL_SUCCEEDED = 1;
    public static final int INSTALL_VIRTUAL_PRELOAD = 0x10000;
    public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ALWAYS = 2;
    public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ALWAYS_ASK = 4;
    public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_ASK = 1;
    public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_NEVER = 3;
    public static final int INTENT_FILTER_DOMAIN_VERIFICATION_STATUS_UNDEFINED = 0;
    public static final int INTENT_FILTER_VERIFICATION_FAILURE = -1;
    public static final int INTENT_FILTER_VERIFICATION_SUCCESS = 1;
    public static final int MASK_PERMISSION_FLAGS = 255;
    public static final int MATCH_ALL = 0x20000;
    public static final int MATCH_ANY_USER = 0x400000;
    public static final int MATCH_DEBUG_TRIAGED_MISSING = 0x10000000;
    public static final int MATCH_DEFAULT_ONLY = 0x10000;
    public static final int MATCH_DIRECT_BOOT_AWARE = 0x80000;
    public static final int MATCH_DIRECT_BOOT_UNAWARE = 0x40000;
    public static final int MATCH_DISABLED_COMPONENTS = 512;
    public static final int MATCH_DISABLED_UNTIL_USED_COMPONENTS = 32768;
    public static final int MATCH_EXPLICITLY_VISIBLE_ONLY = 0x2000000;
    public static final int MATCH_FACTORY_ONLY = 0x200000;
    public static final int MATCH_INSTANT = 0x800000;
    public static final int MATCH_KNOWN_PACKAGES = 0x402000;
    public static final int MATCH_STATIC_SHARED_LIBRARIES = 0x4000000;
    public static final int MATCH_SYSTEM_ONLY = 0x100000;
    public static final int MATCH_UNINSTALLED_PACKAGES = 8192;
    public static final int MATCH_VISIBLE_TO_INSTANT_APP_ONLY = 0x1000000;
    public static final long MAXIMUM_VERIFICATION_TIMEOUT = 0x36ee80L;
    public static final int MOVE_EXTERNAL_MEDIA = 2;
    public static final int MOVE_FAILED_3RD_PARTY_NOT_ALLOWED_ON_INTERNAL = -9;
    public static final int MOVE_FAILED_DEVICE_ADMIN = -8;
    public static final int MOVE_FAILED_DOESNT_EXIST = -2;
    public static final int MOVE_FAILED_FORWARD_LOCKED = -4;
    public static final int MOVE_FAILED_INSUFFICIENT_STORAGE = -1;
    public static final int MOVE_FAILED_INTERNAL_ERROR = -6;
    public static final int MOVE_FAILED_INVALID_LOCATION = -5;
    public static final int MOVE_FAILED_LOCKED_USER = -10;
    public static final int MOVE_FAILED_OPERATION_PENDING = -7;
    public static final int MOVE_FAILED_SYSTEM_PACKAGE = -3;
    public static final int MOVE_INTERNAL = 1;
    public static final int MOVE_SUCCEEDED = -100;
    public static final int NOTIFY_PACKAGE_USE_ACTIVITY = 0;
    public static final int NOTIFY_PACKAGE_USE_BACKUP = 5;
    public static final int NOTIFY_PACKAGE_USE_BROADCAST_RECEIVER = 3;
    public static final int NOTIFY_PACKAGE_USE_CONTENT_PROVIDER = 4;
    public static final int NOTIFY_PACKAGE_USE_CROSS_PACKAGE = 6;
    public static final int NOTIFY_PACKAGE_USE_FOREGROUND_SERVICE = 2;
    public static final int NOTIFY_PACKAGE_USE_INSTRUMENTATION = 7;
    public static final int NOTIFY_PACKAGE_USE_REASONS_COUNT = 8;
    public static final int NOTIFY_PACKAGE_USE_SERVICE = 1;
    public static final int NO_NATIVE_LIBRARIES = -114;
    public static final int ONLY_IF_NO_MATCH_FOUND = 4;
    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_GRANTED = 0;
    public static final int SIGNATURE_FIRST_NOT_SIGNED = -1;
    public static final int SIGNATURE_MATCH = 0;
    public static final int SIGNATURE_NEITHER_SIGNED = 1;
    public static final int SIGNATURE_NO_MATCH = -3;
    public static final int SIGNATURE_SECOND_NOT_SIGNED = -2;
    public static final int SIGNATURE_UNKNOWN_PACKAGE = -4;
    public static final int SKIP_CURRENT_PROFILE = 2;
    public static final String SYSTEM_SHARED_LIBRARY_SERVICES = "android.ext.services";
    public static final String SYSTEM_SHARED_LIBRARY_SHARED = "android.ext.shared";
    private static final String TAG = "PackageManager";
    public static final int VERIFICATION_ALLOW = 1;
    public static final int VERIFICATION_ALLOW_WITHOUT_SUFFICIENT = 2;
    public static final int VERIFICATION_REJECT = -1;
    public static final int VERSION_CODE_HIGHEST = -1;
}
