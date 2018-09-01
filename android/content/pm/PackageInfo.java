// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.content.pm:
//            ApplicationInfo, ActivityInfo, ServiceInfo, ProviderInfo, 
//            InstrumentationInfo, PermissionInfo, Signature, ConfigurationInfo, 
//            FeatureInfo, FeatureGroupInfo, ComponentInfo

public class PackageInfo
    implements Parcelable
{

    public PackageInfo()
    {
        installLocation = 1;
    }

    private PackageInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        installLocation = 1;
        packageName = parcel.readString();
        splitNames = parcel.createStringArray();
        versionCode = parcel.readInt();
        versionName = parcel.readString();
        baseRevisionCode = parcel.readInt();
        splitRevisionCodes = parcel.createIntArray();
        sharedUserId = parcel.readString();
        sharedUserLabel = parcel.readInt();
        if(parcel.readInt() != 0)
            applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel);
        firstInstallTime = parcel.readLong();
        lastUpdateTime = parcel.readLong();
        gids = parcel.createIntArray();
        activities = (ActivityInfo[])parcel.createTypedArray(ActivityInfo.CREATOR);
        receivers = (ActivityInfo[])parcel.createTypedArray(ActivityInfo.CREATOR);
        services = (ServiceInfo[])parcel.createTypedArray(ServiceInfo.CREATOR);
        providers = (ProviderInfo[])parcel.createTypedArray(ProviderInfo.CREATOR);
        instrumentation = (InstrumentationInfo[])parcel.createTypedArray(InstrumentationInfo.CREATOR);
        permissions = (PermissionInfo[])parcel.createTypedArray(PermissionInfo.CREATOR);
        requestedPermissions = parcel.createStringArray();
        requestedPermissionsFlags = parcel.createIntArray();
        signatures = (Signature[])parcel.createTypedArray(Signature.CREATOR);
        configPreferences = (ConfigurationInfo[])parcel.createTypedArray(ConfigurationInfo.CREATOR);
        reqFeatures = (FeatureInfo[])parcel.createTypedArray(FeatureInfo.CREATOR);
        featureGroups = (FeatureGroupInfo[])parcel.createTypedArray(FeatureGroupInfo.CREATOR);
        installLocation = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        isStub = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        coreApp = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        requiredForAllUsers = flag1;
        restrictedAccountType = parcel.readString();
        requiredAccountType = parcel.readString();
        overlayTarget = parcel.readString();
        overlayPriority = parcel.readInt();
        overlayFlags = parcel.readInt();
        if(applicationInfo != null)
        {
            propagateApplicationInfo(applicationInfo, activities);
            propagateApplicationInfo(applicationInfo, receivers);
            propagateApplicationInfo(applicationInfo, services);
            propagateApplicationInfo(applicationInfo, providers);
        }
    }

    PackageInfo(Parcel parcel, PackageInfo packageinfo)
    {
        this(parcel);
    }

    private void propagateApplicationInfo(ApplicationInfo applicationinfo, ComponentInfo acomponentinfo[])
    {
        if(acomponentinfo != null)
        {
            int i = 0;
            for(int j = acomponentinfo.length; i < j; i++)
                acomponentinfo[i].applicationInfo = applicationinfo;

        }
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("PackageInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(packageName).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(packageName);
        parcel.writeStringArray(splitNames);
        parcel.writeInt(versionCode);
        parcel.writeString(versionName);
        parcel.writeInt(baseRevisionCode);
        parcel.writeIntArray(splitRevisionCodes);
        parcel.writeString(sharedUserId);
        parcel.writeInt(sharedUserLabel);
        if(applicationInfo != null)
        {
            parcel.writeInt(1);
            applicationInfo.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeLong(firstInstallTime);
        parcel.writeLong(lastUpdateTime);
        parcel.writeIntArray(gids);
        parcel.writeTypedArray(activities, i | 2);
        parcel.writeTypedArray(receivers, i | 2);
        parcel.writeTypedArray(services, i | 2);
        parcel.writeTypedArray(providers, i | 2);
        parcel.writeTypedArray(instrumentation, i);
        parcel.writeTypedArray(permissions, i);
        parcel.writeStringArray(requestedPermissions);
        parcel.writeIntArray(requestedPermissionsFlags);
        parcel.writeTypedArray(signatures, i);
        parcel.writeTypedArray(configPreferences, i);
        parcel.writeTypedArray(reqFeatures, i);
        parcel.writeTypedArray(featureGroups, i);
        parcel.writeInt(installLocation);
        if(isStub)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(coreApp)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(requiredForAllUsers)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(restrictedAccountType);
        parcel.writeString(requiredAccountType);
        parcel.writeString(overlayTarget);
        parcel.writeInt(overlayPriority);
        parcel.writeInt(overlayFlags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PackageInfo createFromParcel(Parcel parcel)
        {
            return new PackageInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PackageInfo[] newArray(int i)
        {
            return new PackageInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_OVERLAY_STATIC = 2;
    public static final int FLAG_OVERLAY_TRUSTED = 4;
    public static final int INSTALL_LOCATION_AUTO = 0;
    public static final int INSTALL_LOCATION_INTERNAL_ONLY = 1;
    public static final int INSTALL_LOCATION_PREFER_EXTERNAL = 2;
    public static final int INSTALL_LOCATION_UNSPECIFIED = -1;
    public static final int REQUESTED_PERMISSION_GRANTED = 2;
    public static final int REQUESTED_PERMISSION_REQUIRED = 1;
    public ActivityInfo activities[];
    public ApplicationInfo applicationInfo;
    public int baseRevisionCode;
    public ConfigurationInfo configPreferences[];
    public boolean coreApp;
    public FeatureGroupInfo featureGroups[];
    public long firstInstallTime;
    public int gids[];
    public int installLocation;
    public InstrumentationInfo instrumentation[];
    public boolean isStub;
    public long lastUpdateTime;
    public int overlayFlags;
    public int overlayPriority;
    public String overlayTarget;
    public String packageName;
    public PermissionInfo permissions[];
    public ProviderInfo providers[];
    public ActivityInfo receivers[];
    public FeatureInfo reqFeatures[];
    public String requestedPermissions[];
    public int requestedPermissionsFlags[];
    public String requiredAccountType;
    public boolean requiredForAllUsers;
    public String restrictedAccountType;
    public ServiceInfo services[];
    public String sharedUserId;
    public int sharedUserLabel;
    public Signature signatures[];
    public String splitNames[];
    public int splitRevisionCodes[];
    public int versionCode;
    public String versionName;

}
