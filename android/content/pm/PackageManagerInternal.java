// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import java.util.List;

// Referenced classes of package android.content.pm:
//            ActivityInfo, ApplicationInfo, PackageInfo, AuxiliaryResolveInfo, 
//            ResolveInfo

public abstract class PackageManagerInternal
{
    public static interface ExternalSourcesPolicy
    {

        public abstract int getPackageTrustedToInstallApps(String s, int i);

        public static final int USER_BLOCKED = 1;
        public static final int USER_DEFAULT = 2;
        public static final int USER_TRUSTED = 0;
    }

    public static interface PackagesProvider
    {

        public abstract String[] getPackages(int i);
    }

    public static interface SyncAdapterPackagesProvider
    {

        public abstract String[] getPackages(String s, int i);
    }


    public PackageManagerInternal()
    {
    }

    public abstract void addIsolatedUid(int i, int j);

    public abstract boolean canAccessInstantApps(int i, int j);

    public abstract ActivityInfo getActivityInfo(ComponentName componentname, int i, int j, int k);

    public abstract ApplicationInfo getApplicationInfo(String s, int i, int j, int k);

    public abstract ComponentName getHomeActivitiesAsUser(List list, int i);

    public abstract String getNameForUid(int i);

    public abstract List getOverlayPackages(int i);

    public abstract PackageInfo getPackageInfo(String s, int i, int j, int k);

    public abstract String getSetupWizardPackageName();

    public abstract List getTargetPackageNames(int i);

    public abstract int getUidTargetSdkVersion(int i);

    public abstract void grantDefaultPermissionsToDefaultDialerApp(String s, int i);

    public abstract void grantDefaultPermissionsToDefaultSimCallManager(String s, int i);

    public abstract void grantDefaultPermissionsToDefaultSmsApp(String s, int i);

    public abstract void grantEphemeralAccess(int i, Intent intent, int j, int k);

    public abstract void grantRuntimePermission(String s, String s1, int i, boolean flag);

    public abstract boolean hasInstantApplicationMetadata(String s, int i);

    public abstract boolean isInstantAppInstallerComponent(ComponentName componentname);

    public abstract boolean isPackageDataProtected(int i, String s);

    public abstract boolean isPackageEphemeral(int i, String s);

    public abstract boolean isPackagePersistent(String s);

    public abstract boolean isPermissionsReviewRequired(String s, int i);

    public abstract void notifyPackageUse(String s, int i);

    public abstract void pruneInstantApps();

    public abstract List queryIntentActivities(Intent intent, int i, int j, int k);

    public abstract void removeIsolatedUid(int i);

    public abstract void requestInstantAppResolutionPhaseTwo(AuxiliaryResolveInfo auxiliaryresolveinfo, Intent intent, String s, String s1, Bundle bundle, int i);

    public abstract ResolveInfo resolveIntent(Intent intent, String s, int i, int j);

    public abstract ResolveInfo resolveService(Intent intent, String s, int i, int j, int k);

    public abstract void revokeRuntimePermission(String s, String s1, int i, boolean flag);

    public abstract void setDeviceAndProfileOwnerPackages(int i, String s, SparseArray sparsearray);

    public abstract void setDialerAppPackagesProvider(PackagesProvider packagesprovider);

    public abstract boolean setEnabledOverlayPackages(int i, String s, List list);

    public abstract void setExternalSourcesPolicy(ExternalSourcesPolicy externalsourcespolicy);

    public abstract void setKeepUninstalledPackages(List list);

    public abstract void setLocationPackagesProvider(PackagesProvider packagesprovider);

    public abstract void setSimCallManagerPackagesProvider(PackagesProvider packagesprovider);

    public abstract void setSmsAppPackagesProvider(PackagesProvider packagesprovider);

    public abstract void setSyncAdapterPackagesprovider(SyncAdapterPackagesProvider syncadapterpackagesprovider);

    public abstract void setVoiceInteractionPackagesProvider(PackagesProvider packagesprovider);

    public abstract boolean wasPackageEverLaunched(String s, int i);
}
