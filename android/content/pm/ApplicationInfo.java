// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Printer;
import android.util.SparseArray;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.text.Collator;
import java.util.*;

// Referenced classes of package android.content.pm:
//            PackageItemInfo, PackageManager

public class ApplicationInfo extends PackageItemInfo
    implements Parcelable
{
    public static class DisplayNameComparator
        implements Comparator
    {

        public final int compare(ApplicationInfo applicationinfo, ApplicationInfo applicationinfo1)
        {
            CharSequence charsequence = mPM.getApplicationLabel(applicationinfo);
            Object obj = charsequence;
            if(charsequence == null)
                obj = applicationinfo.packageName;
            charsequence = mPM.getApplicationLabel(applicationinfo1);
            applicationinfo = charsequence;
            if(charsequence == null)
                applicationinfo = applicationinfo1.packageName;
            return sCollator.compare(((CharSequence) (obj)).toString(), applicationinfo.toString());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ApplicationInfo)obj, (ApplicationInfo)obj1);
        }

        private PackageManager mPM;
        private final Collator sCollator = Collator.getInstance();

        public DisplayNameComparator(PackageManager packagemanager)
        {
            mPM = packagemanager;
        }
    }


    public ApplicationInfo()
    {
        fullBackupContent = 0;
        uiOptions = 0;
        flags = 0;
        overrideRes = 0;
        overrideDensity = 0;
        whiteListed = 0;
        requiresSmallestWidthDp = 0;
        compatibleWidthLimitDp = 0;
        largestWidthLimitDp = 0;
        seInfo = "default";
        enabled = true;
        enabledSetting = 0;
        installLocation = -1;
        category = -1;
    }

    public ApplicationInfo(ApplicationInfo applicationinfo)
    {
        super(applicationinfo);
        fullBackupContent = 0;
        uiOptions = 0;
        flags = 0;
        overrideRes = 0;
        overrideDensity = 0;
        whiteListed = 0;
        requiresSmallestWidthDp = 0;
        compatibleWidthLimitDp = 0;
        largestWidthLimitDp = 0;
        seInfo = "default";
        enabled = true;
        enabledSetting = 0;
        installLocation = -1;
        category = -1;
        taskAffinity = applicationinfo.taskAffinity;
        permission = applicationinfo.permission;
        processName = applicationinfo.processName;
        className = applicationinfo.className;
        theme = applicationinfo.theme;
        flags = applicationinfo.flags;
        privateFlags = applicationinfo.privateFlags;
        overrideRes = applicationinfo.overrideRes;
        overrideDensity = applicationinfo.overrideDensity;
        whiteListed = applicationinfo.whiteListed;
        requiresSmallestWidthDp = applicationinfo.requiresSmallestWidthDp;
        compatibleWidthLimitDp = applicationinfo.compatibleWidthLimitDp;
        largestWidthLimitDp = applicationinfo.largestWidthLimitDp;
        volumeUuid = applicationinfo.volumeUuid;
        storageUuid = applicationinfo.storageUuid;
        scanSourceDir = applicationinfo.scanSourceDir;
        scanPublicSourceDir = applicationinfo.scanPublicSourceDir;
        sourceDir = applicationinfo.sourceDir;
        publicSourceDir = applicationinfo.publicSourceDir;
        splitNames = applicationinfo.splitNames;
        splitSourceDirs = applicationinfo.splitSourceDirs;
        splitPublicSourceDirs = applicationinfo.splitPublicSourceDirs;
        splitDependencies = applicationinfo.splitDependencies;
        nativeLibraryDir = applicationinfo.nativeLibraryDir;
        secondaryNativeLibraryDir = applicationinfo.secondaryNativeLibraryDir;
        nativeLibraryRootDir = applicationinfo.nativeLibraryRootDir;
        nativeLibraryRootRequiresIsa = applicationinfo.nativeLibraryRootRequiresIsa;
        primaryCpuAbi = applicationinfo.primaryCpuAbi;
        secondaryCpuAbi = applicationinfo.secondaryCpuAbi;
        resourceDirs = applicationinfo.resourceDirs;
        seInfo = applicationinfo.seInfo;
        seInfoUser = applicationinfo.seInfoUser;
        sharedLibraryFiles = applicationinfo.sharedLibraryFiles;
        dataDir = applicationinfo.dataDir;
        deviceProtectedDataDir = applicationinfo.deviceProtectedDataDir;
        credentialProtectedDataDir = applicationinfo.credentialProtectedDataDir;
        uid = applicationinfo.uid;
        minSdkVersion = applicationinfo.minSdkVersion;
        targetSdkVersion = applicationinfo.targetSdkVersion;
        versionCode = applicationinfo.versionCode;
        enabled = applicationinfo.enabled;
        enabledSetting = applicationinfo.enabledSetting;
        installLocation = applicationinfo.installLocation;
        manageSpaceActivityName = applicationinfo.manageSpaceActivityName;
        descriptionRes = applicationinfo.descriptionRes;
        uiOptions = applicationinfo.uiOptions;
        backupAgentName = applicationinfo.backupAgentName;
        fullBackupContent = applicationinfo.fullBackupContent;
        networkSecurityConfigRes = applicationinfo.networkSecurityConfigRes;
        category = applicationinfo.category;
        targetSandboxVersion = applicationinfo.targetSandboxVersion;
        classLoaderName = applicationinfo.classLoaderName;
        splitClassLoaderNames = applicationinfo.splitClassLoaderNames;
    }

    private ApplicationInfo(Parcel parcel)
    {
        boolean flag = true;
        super(parcel);
        fullBackupContent = 0;
        uiOptions = 0;
        flags = 0;
        overrideRes = 0;
        overrideDensity = 0;
        whiteListed = 0;
        requiresSmallestWidthDp = 0;
        compatibleWidthLimitDp = 0;
        largestWidthLimitDp = 0;
        seInfo = "default";
        enabled = true;
        enabledSetting = 0;
        installLocation = -1;
        category = -1;
        taskAffinity = parcel.readString();
        permission = parcel.readString();
        processName = parcel.readString();
        className = parcel.readString();
        theme = parcel.readInt();
        flags = parcel.readInt();
        privateFlags = parcel.readInt();
        overrideRes = parcel.readInt();
        overrideDensity = parcel.readInt();
        whiteListed = parcel.readInt();
        requiresSmallestWidthDp = parcel.readInt();
        compatibleWidthLimitDp = parcel.readInt();
        largestWidthLimitDp = parcel.readInt();
        if(parcel.readInt() != 0)
        {
            storageUuid = new UUID(parcel.readLong(), parcel.readLong());
            volumeUuid = StorageManager.convert(storageUuid);
        }
        scanSourceDir = parcel.readString();
        scanPublicSourceDir = parcel.readString();
        sourceDir = parcel.readString();
        publicSourceDir = parcel.readString();
        splitNames = parcel.readStringArray();
        splitSourceDirs = parcel.readStringArray();
        splitPublicSourceDirs = parcel.readStringArray();
        splitDependencies = parcel.readSparseArray(null);
        nativeLibraryDir = parcel.readString();
        secondaryNativeLibraryDir = parcel.readString();
        nativeLibraryRootDir = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        nativeLibraryRootRequiresIsa = flag1;
        primaryCpuAbi = parcel.readString();
        secondaryCpuAbi = parcel.readString();
        resourceDirs = parcel.readStringArray();
        seInfo = parcel.readString();
        seInfoUser = parcel.readString();
        sharedLibraryFiles = parcel.readStringArray();
        dataDir = parcel.readString();
        deviceProtectedDataDir = parcel.readString();
        credentialProtectedDataDir = parcel.readString();
        uid = parcel.readInt();
        minSdkVersion = parcel.readInt();
        targetSdkVersion = parcel.readInt();
        versionCode = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        enabled = flag1;
        enabledSetting = parcel.readInt();
        installLocation = parcel.readInt();
        manageSpaceActivityName = parcel.readString();
        backupAgentName = parcel.readString();
        descriptionRes = parcel.readInt();
        uiOptions = parcel.readInt();
        fullBackupContent = parcel.readInt();
        networkSecurityConfigRes = parcel.readInt();
        category = parcel.readInt();
        targetSandboxVersion = parcel.readInt();
        classLoaderName = parcel.readString();
        splitClassLoaderNames = parcel.readStringArray();
        nextActivityTheme = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        waitingToUse = flag1;
    }

    ApplicationInfo(Parcel parcel, ApplicationInfo applicationinfo)
    {
        this(parcel);
    }

    public static CharSequence getCategoryTitle(Context context, int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return context.getText(0x1040088);

        case 1: // '\001'
            return context.getText(0x1040087);

        case 2: // '\002'
            return context.getText(0x104008e);

        case 3: // '\003'
            return context.getText(0x1040089);

        case 4: // '\004'
            return context.getText(0x104008d);

        case 5: // '\005'
            return context.getText(0x104008b);

        case 6: // '\006'
            return context.getText(0x104008a);

        case 7: // '\007'
            return context.getText(0x104008c);
        }
    }

    private boolean isPackageUnavailable(PackageManager packagemanager)
    {
        boolean flag = true;
        try
        {
            packagemanager = packagemanager.getPackageInfo(packageName, 0);
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            return true;
        }
        if(packagemanager != null)
            flag = false;
        return flag;
    }

    public int canOverrideRes()
    {
        return overrideRes;
    }

    public int describeContents()
    {
        return 0;
    }

    public void disableCompatibilityMode()
    {
        flags = flags | 0x83e00;
    }

    public void dump(Printer printer, String s)
    {
        dump(printer, s, 3);
    }

    public void dump(Printer printer, String s, int i)
    {
        super.dumpFront(printer, s);
        if((i & 1) != 0 && className != null)
            printer.println((new StringBuilder()).append(s).append("className=").append(className).toString());
        if(permission != null)
            printer.println((new StringBuilder()).append(s).append("permission=").append(permission).toString());
        printer.println((new StringBuilder()).append(s).append("processName=").append(processName).toString());
        if((i & 1) != 0)
            printer.println((new StringBuilder()).append(s).append("taskAffinity=").append(taskAffinity).toString());
        printer.println((new StringBuilder()).append(s).append("uid=").append(uid).append(" flags=0x").append(Integer.toHexString(flags)).append(" privateFlags=0x").append(Integer.toHexString(privateFlags)).append(" theme=0x").append(Integer.toHexString(theme)).toString());
        if((i & 1) != 0)
            printer.println((new StringBuilder()).append(s).append("requiresSmallestWidthDp=").append(requiresSmallestWidthDp).append(" compatibleWidthLimitDp=").append(compatibleWidthLimitDp).append(" largestWidthLimitDp=").append(largestWidthLimitDp).toString());
        printer.println((new StringBuilder()).append(s).append("sourceDir=").append(sourceDir).toString());
        if(!Objects.equals(sourceDir, publicSourceDir))
            printer.println((new StringBuilder()).append(s).append("publicSourceDir=").append(publicSourceDir).toString());
        if(!ArrayUtils.isEmpty(splitSourceDirs))
            printer.println((new StringBuilder()).append(s).append("splitSourceDirs=").append(Arrays.toString(splitSourceDirs)).toString());
        if(!ArrayUtils.isEmpty(splitPublicSourceDirs) && Arrays.equals(splitSourceDirs, splitPublicSourceDirs) ^ true)
            printer.println((new StringBuilder()).append(s).append("splitPublicSourceDirs=").append(Arrays.toString(splitPublicSourceDirs)).toString());
        if(resourceDirs != null)
            printer.println((new StringBuilder()).append(s).append("resourceDirs=").append(Arrays.toString(resourceDirs)).toString());
        if((i & 1) != 0 && seInfo != null)
        {
            printer.println((new StringBuilder()).append(s).append("seinfo=").append(seInfo).toString());
            printer.println((new StringBuilder()).append(s).append("seinfoUser=").append(seInfoUser).toString());
        }
        printer.println((new StringBuilder()).append(s).append("dataDir=").append(dataDir).toString());
        if((i & 1) != 0)
        {
            printer.println((new StringBuilder()).append(s).append("deviceProtectedDataDir=").append(deviceProtectedDataDir).toString());
            printer.println((new StringBuilder()).append(s).append("credentialProtectedDataDir=").append(credentialProtectedDataDir).toString());
            if(sharedLibraryFiles != null)
                printer.println((new StringBuilder()).append(s).append("sharedLibraryFiles=").append(Arrays.toString(sharedLibraryFiles)).toString());
        }
        if(classLoaderName != null)
            printer.println((new StringBuilder()).append(s).append("classLoaderName=").append(classLoaderName).toString());
        if(!ArrayUtils.isEmpty(splitClassLoaderNames))
            printer.println((new StringBuilder()).append(s).append("splitClassLoaderNames=").append(Arrays.toString(splitClassLoaderNames)).toString());
        printer.println((new StringBuilder()).append(s).append("enabled=").append(enabled).append(" minSdkVersion=").append(minSdkVersion).append(" targetSdkVersion=").append(targetSdkVersion).append(" versionCode=").append(versionCode).append(" targetSandboxVersion=").append(targetSandboxVersion).toString());
        if((i & 1) != 0)
        {
            if(manageSpaceActivityName != null)
                printer.println((new StringBuilder()).append(s).append("manageSpaceActivityName=").append(manageSpaceActivityName).toString());
            if(descriptionRes != 0)
                printer.println((new StringBuilder()).append(s).append("description=0x").append(Integer.toHexString(descriptionRes)).toString());
            if(uiOptions != 0)
                printer.println((new StringBuilder()).append(s).append("uiOptions=0x").append(Integer.toHexString(uiOptions)).toString());
            StringBuilder stringbuilder = (new StringBuilder()).append(s).append("supportsRtl=");
            String s1;
            if(hasRtlSupport())
                s1 = "true";
            else
                s1 = "false";
            printer.println(stringbuilder.append(s1).toString());
            if(fullBackupContent > 0)
            {
                printer.println((new StringBuilder()).append(s).append("fullBackupContent=@xml/").append(fullBackupContent).toString());
            } else
            {
                StringBuilder stringbuilder1 = (new StringBuilder()).append(s).append("fullBackupContent=");
                String s2;
                if(fullBackupContent < 0)
                    s2 = "false";
                else
                    s2 = "true";
                printer.println(stringbuilder1.append(s2).toString());
            }
            if(networkSecurityConfigRes != 0)
                printer.println((new StringBuilder()).append(s).append("networkSecurityConfigRes=0x").append(Integer.toHexString(networkSecurityConfigRes)).toString());
            if(category != -1)
                printer.println((new StringBuilder()).append(s).append("category=").append(category).toString());
        }
        super.dumpBack(printer, s);
    }

    protected ApplicationInfo getApplicationInfo()
    {
        return this;
    }

    public String getBaseCodePath()
    {
        return sourceDir;
    }

    public String getBaseResourcePath()
    {
        return publicSourceDir;
    }

    public String getCodePath()
    {
        return scanSourceDir;
    }

    public int getOverrideDensity()
    {
        return overrideDensity;
    }

    public String getResourcePath()
    {
        return scanPublicSourceDir;
    }

    public String[] getSplitCodePaths()
    {
        return splitSourceDirs;
    }

    public String[] getSplitResourcePaths()
    {
        return splitPublicSourceDirs;
    }

    public boolean hasCode()
    {
        boolean flag = false;
        if((flags & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean hasRtlSupport()
    {
        boolean flag;
        if((flags & 0x400000) == 0x400000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void initForUser(int i)
    {
        uid = UserHandle.getUid(i, UserHandle.getAppId(uid));
        if("android".equals(packageName))
        {
            dataDir = Environment.getDataSystemDirectory().getAbsolutePath();
            return;
        }
        deviceProtectedDataDir = Environment.getDataUserDePackageDirectory(volumeUuid, i, packageName).getAbsolutePath();
        credentialProtectedDataDir = Environment.getDataUserCePackageDirectory(volumeUuid, i, packageName).getAbsolutePath();
        if((privateFlags & 0x20) != 0)
            dataDir = deviceProtectedDataDir;
        else
            dataDir = credentialProtectedDataDir;
    }

    public boolean isAppWhiteListed()
    {
        boolean flag = true;
        if(whiteListed != 1)
            flag = false;
        return flag;
    }

    public boolean isDefaultToDeviceProtectedStorage()
    {
        boolean flag = false;
        if((privateFlags & 0x20) != 0)
            flag = true;
        return flag;
    }

    public boolean isDirectBootAware()
    {
        boolean flag = false;
        if((privateFlags & 0x40) != 0)
            flag = true;
        return flag;
    }

    public boolean isEncryptionAware()
    {
        boolean flag;
        if(!isDirectBootAware())
            flag = isPartiallyDirectBootAware();
        else
            flag = true;
        return flag;
    }

    public boolean isExternalAsec()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(TextUtils.isEmpty(volumeUuid))
        {
            flag1 = flag;
            if((flags & 0x40000) != 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isForwardLocked()
    {
        boolean flag = false;
        if((privateFlags & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean isInstantApp()
    {
        boolean flag = false;
        if((privateFlags & 0x80) != 0)
            flag = true;
        return flag;
    }

    public boolean isInternal()
    {
        boolean flag = false;
        if((flags & 0x40000) == 0)
            flag = true;
        return flag;
    }

    public boolean isPartiallyDirectBootAware()
    {
        boolean flag = false;
        if((privateFlags & 0x100) != 0)
            flag = true;
        return flag;
    }

    public boolean isPrivilegedApp()
    {
        boolean flag = false;
        if((privateFlags & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean isRequiredForSystemUser()
    {
        boolean flag = false;
        if((privateFlags & 0x200) != 0)
            flag = true;
        return flag;
    }

    public boolean isStaticSharedLibrary()
    {
        boolean flag = false;
        if((privateFlags & 0x4000) != 0)
            flag = true;
        return flag;
    }

    public boolean isSystemApp()
    {
        boolean flag = false;
        if((flags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isUpdatedSystemApp()
    {
        boolean flag = false;
        if((flags & 0x80) != 0)
            flag = true;
        return flag;
    }

    public boolean isVirtualPreload()
    {
        boolean flag = false;
        if((privateFlags & 0x10000) != 0)
            flag = true;
        return flag;
    }

    public Drawable loadDefaultIcon(PackageManager packagemanager)
    {
        if((flags & 0x40000) != 0 && isPackageUnavailable(packagemanager))
            return Resources.getSystem().getDrawable(0x1080806);
        else
            return packagemanager.getDefaultActivityIcon();
    }

    public CharSequence loadDescription(PackageManager packagemanager)
    {
        if(descriptionRes != 0)
        {
            packagemanager = packagemanager.getText(packageName, descriptionRes, this);
            if(packagemanager != null)
                return packagemanager;
        }
        return null;
    }

    public boolean requestsIsolatedSplitLoading()
    {
        boolean flag = false;
        if((privateFlags & 0x8000) != 0)
            flag = true;
        return flag;
    }

    public void setAppOverrideDensity()
    {
        int j;
label0:
        {
            int i = 0;
            String s = SystemProperties.get("persist.vendor.qti.debug.appdensity");
            j = i;
            if(s == null)
                break label0;
            j = i;
            if(!(s.isEmpty() ^ true))
                break label0;
            i = Integer.parseInt(s);
            if(i >= 120)
            {
                j = i;
                if(i <= 480)
                    break label0;
            }
            j = 0;
        }
        setOverrideDensity(j);
    }

    public void setAppWhiteListed(int i)
    {
        whiteListed = i;
    }

    public void setBaseCodePath(String s)
    {
        sourceDir = s;
    }

    public void setBaseResourcePath(String s)
    {
        publicSourceDir = s;
    }

    public void setCodePath(String s)
    {
        scanSourceDir = s;
    }

    public void setOverrideDensity(int i)
    {
        overrideDensity = i;
    }

    public void setOverrideRes(int i)
    {
        overrideRes = i;
    }

    public void setResourcePath(String s)
    {
        scanPublicSourceDir = s;
    }

    public void setSplitCodePaths(String as[])
    {
        splitSourceDirs = as;
    }

    public void setSplitResourcePaths(String as[])
    {
        splitPublicSourceDirs = as;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ApplicationInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(packageName).append("}").toString();
    }

    public boolean usesCompatibilityMode()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(targetSdkVersion >= 4)
            if((flags & 0x83e00) == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        super.writeToParcel(parcel, i);
        parcel.writeString(taskAffinity);
        parcel.writeString(permission);
        parcel.writeString(processName);
        parcel.writeString(className);
        parcel.writeInt(theme);
        parcel.writeInt(flags);
        parcel.writeInt(privateFlags);
        parcel.writeInt(overrideRes);
        parcel.writeInt(overrideDensity);
        parcel.writeInt(whiteListed);
        parcel.writeInt(requiresSmallestWidthDp);
        parcel.writeInt(compatibleWidthLimitDp);
        parcel.writeInt(largestWidthLimitDp);
        if(storageUuid != null)
        {
            parcel.writeInt(1);
            parcel.writeLong(storageUuid.getMostSignificantBits());
            parcel.writeLong(storageUuid.getLeastSignificantBits());
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeString(scanSourceDir);
        parcel.writeString(scanPublicSourceDir);
        parcel.writeString(sourceDir);
        parcel.writeString(publicSourceDir);
        parcel.writeStringArray(splitNames);
        parcel.writeStringArray(splitSourceDirs);
        parcel.writeStringArray(splitPublicSourceDirs);
        parcel.writeSparseArray(splitDependencies);
        parcel.writeString(nativeLibraryDir);
        parcel.writeString(secondaryNativeLibraryDir);
        parcel.writeString(nativeLibraryRootDir);
        if(nativeLibraryRootRequiresIsa)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(primaryCpuAbi);
        parcel.writeString(secondaryCpuAbi);
        parcel.writeStringArray(resourceDirs);
        parcel.writeString(seInfo);
        parcel.writeString(seInfoUser);
        parcel.writeStringArray(sharedLibraryFiles);
        parcel.writeString(dataDir);
        parcel.writeString(deviceProtectedDataDir);
        parcel.writeString(credentialProtectedDataDir);
        parcel.writeInt(uid);
        parcel.writeInt(minSdkVersion);
        parcel.writeInt(targetSdkVersion);
        parcel.writeInt(versionCode);
        if(enabled)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(enabledSetting);
        parcel.writeInt(installLocation);
        parcel.writeString(manageSpaceActivityName);
        parcel.writeString(backupAgentName);
        parcel.writeInt(descriptionRes);
        parcel.writeInt(uiOptions);
        parcel.writeInt(fullBackupContent);
        parcel.writeInt(networkSecurityConfigRes);
        parcel.writeInt(category);
        parcel.writeInt(targetSandboxVersion);
        parcel.writeString(classLoaderName);
        parcel.writeStringArray(splitClassLoaderNames);
        parcel.writeInt(nextActivityTheme);
        if(waitingToUse)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final int CATEGORY_AUDIO = 1;
    public static final int CATEGORY_GAME = 0;
    public static final int CATEGORY_IMAGE = 3;
    public static final int CATEGORY_MAPS = 6;
    public static final int CATEGORY_NEWS = 5;
    public static final int CATEGORY_PRODUCTIVITY = 7;
    public static final int CATEGORY_SOCIAL = 4;
    public static final int CATEGORY_UNDEFINED = -1;
    public static final int CATEGORY_VIDEO = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ApplicationInfo createFromParcel(Parcel parcel)
        {
            return new ApplicationInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ApplicationInfo[] newArray(int i)
        {
            return new ApplicationInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_ALLOW_BACKUP = 32768;
    public static final int FLAG_ALLOW_CLEAR_USER_DATA = 64;
    public static final int FLAG_ALLOW_TASK_REPARENTING = 32;
    public static final int FLAG_DEBUGGABLE = 2;
    public static final int FLAG_EXTERNAL_STORAGE = 0x40000;
    public static final int FLAG_EXTRACT_NATIVE_LIBS = 0x10000000;
    public static final int FLAG_FACTORY_TEST = 16;
    public static final int FLAG_FULL_BACKUP_ONLY = 0x4000000;
    public static final int FLAG_HARDWARE_ACCELERATED = 0x20000000;
    public static final int FLAG_HAS_CODE = 4;
    public static final int FLAG_INSTALLED = 0x800000;
    public static final int FLAG_IS_DATA_ONLY = 0x1000000;
    public static final int FLAG_IS_GAME = 0x2000000;
    public static final int FLAG_KILL_AFTER_RESTORE = 0x10000;
    public static final int FLAG_LARGE_HEAP = 0x100000;
    public static final int FLAG_MULTIARCH = 0x80000000;
    public static final int FLAG_PERSISTENT = 8;
    public static final int FLAG_RESIZEABLE_FOR_SCREENS = 4096;
    public static final int FLAG_RESTORE_ANY_VERSION = 0x20000;
    public static final int FLAG_STOPPED = 0x200000;
    public static final int FLAG_SUPPORTS_LARGE_SCREENS = 2048;
    public static final int FLAG_SUPPORTS_NORMAL_SCREENS = 1024;
    public static final int FLAG_SUPPORTS_RTL = 0x400000;
    public static final int FLAG_SUPPORTS_SCREEN_DENSITIES = 8192;
    public static final int FLAG_SUPPORTS_SMALL_SCREENS = 512;
    public static final int FLAG_SUPPORTS_XLARGE_SCREENS = 0x80000;
    public static final int FLAG_SUSPENDED = 0x40000000;
    public static final int FLAG_SYSTEM = 1;
    public static final int FLAG_TEST_ONLY = 256;
    public static final int FLAG_UPDATED_SYSTEM_APP = 128;
    public static final int FLAG_USES_CLEARTEXT_TRAFFIC = 0x8000000;
    public static final int FLAG_VM_SAFE_MODE = 16384;
    public static final String METADATA_PRELOADED_FONTS = "preloaded_fonts";
    public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_RESIZEABLE = 1024;
    public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION = 4096;
    public static final int PRIVATE_FLAG_ACTIVITIES_RESIZE_MODE_UNRESIZEABLE = 2048;
    public static final int PRIVATE_FLAG_BACKUP_IN_FOREGROUND = 8192;
    public static final int PRIVATE_FLAG_CANT_SAVE_STATE = 2;
    public static final int PRIVATE_FLAG_DEFAULT_TO_DEVICE_PROTECTED_STORAGE = 32;
    public static final int PRIVATE_FLAG_DIRECT_BOOT_AWARE = 64;
    public static final int PRIVATE_FLAG_FORWARD_LOCK = 4;
    public static final int PRIVATE_FLAG_HAS_DOMAIN_URLS = 16;
    public static final int PRIVATE_FLAG_HIDDEN = 1;
    public static final int PRIVATE_FLAG_INSTANT = 128;
    public static final int PRIVATE_FLAG_ISOLATED_SPLIT_LOADING = 32768;
    public static final int PRIVATE_FLAG_PARTIALLY_DIRECT_BOOT_AWARE = 256;
    public static final int PRIVATE_FLAG_PRIVILEGED = 8;
    public static final int PRIVATE_FLAG_REQUIRED_FOR_SYSTEM_USER = 512;
    public static final int PRIVATE_FLAG_STATIC_SHARED_LIBRARY = 16384;
    public static final int PRIVATE_FLAG_VIRTUAL_PRELOAD = 0x10000;
    public String backupAgentName;
    public int category;
    public String classLoaderName;
    public String className;
    public int compatibleWidthLimitDp;
    public String credentialProtectedDataDir;
    public String dataDir;
    public int descriptionRes;
    public String deviceProtectedDataDir;
    public boolean enabled;
    public int enabledSetting;
    public int flags;
    public int fullBackupContent;
    public int installLocation;
    public int largestWidthLimitDp;
    public String manageSpaceActivityName;
    public float maxAspectRatio;
    public int minSdkVersion;
    public String nativeLibraryDir;
    public String nativeLibraryRootDir;
    public boolean nativeLibraryRootRequiresIsa;
    public int networkSecurityConfigRes;
    public int nextActivityTheme;
    public int overrideDensity;
    public int overrideRes;
    public String permission;
    public String primaryCpuAbi;
    public int privateFlags;
    public String processName;
    public String publicSourceDir;
    public int requiresSmallestWidthDp;
    public String resourceDirs[];
    public String scanPublicSourceDir;
    public String scanSourceDir;
    public String seInfo;
    public String seInfoUser;
    public String secondaryCpuAbi;
    public String secondaryNativeLibraryDir;
    public String sharedLibraryFiles[];
    public String sourceDir;
    public String splitClassLoaderNames[];
    public SparseArray splitDependencies;
    public String splitNames[];
    public String splitPublicSourceDirs[];
    public String splitSourceDirs[];
    public UUID storageUuid;
    public int targetSandboxVersion;
    public int targetSdkVersion;
    public String taskAffinity;
    public int theme;
    public int uiOptions;
    public int uid;
    public int versionCode;
    public String volumeUuid;
    public boolean waitingToUse;
    public int whiteListed;

}
