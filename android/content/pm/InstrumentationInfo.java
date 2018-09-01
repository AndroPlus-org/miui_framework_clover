// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

// Referenced classes of package android.content.pm:
//            PackageItemInfo, ApplicationInfo

public class InstrumentationInfo extends PackageItemInfo
    implements Parcelable
{

    public InstrumentationInfo()
    {
    }

    public InstrumentationInfo(InstrumentationInfo instrumentationinfo)
    {
        super(instrumentationinfo);
        targetPackage = instrumentationinfo.targetPackage;
        targetProcesses = instrumentationinfo.targetProcesses;
        sourceDir = instrumentationinfo.sourceDir;
        publicSourceDir = instrumentationinfo.publicSourceDir;
        splitNames = instrumentationinfo.splitNames;
        splitSourceDirs = instrumentationinfo.splitSourceDirs;
        splitPublicSourceDirs = instrumentationinfo.splitPublicSourceDirs;
        splitDependencies = instrumentationinfo.splitDependencies;
        dataDir = instrumentationinfo.dataDir;
        deviceProtectedDataDir = instrumentationinfo.deviceProtectedDataDir;
        credentialProtectedDataDir = instrumentationinfo.credentialProtectedDataDir;
        nativeLibraryDir = instrumentationinfo.nativeLibraryDir;
        secondaryNativeLibraryDir = instrumentationinfo.secondaryNativeLibraryDir;
        handleProfiling = instrumentationinfo.handleProfiling;
        functionalTest = instrumentationinfo.functionalTest;
    }

    private InstrumentationInfo(Parcel parcel)
    {
        boolean flag = true;
        super(parcel);
        targetPackage = parcel.readString();
        targetProcesses = parcel.readString();
        sourceDir = parcel.readString();
        publicSourceDir = parcel.readString();
        splitNames = parcel.readStringArray();
        splitSourceDirs = parcel.readStringArray();
        splitPublicSourceDirs = parcel.readStringArray();
        splitDependencies = parcel.readSparseArray(null);
        dataDir = parcel.readString();
        deviceProtectedDataDir = parcel.readString();
        credentialProtectedDataDir = parcel.readString();
        nativeLibraryDir = parcel.readString();
        secondaryNativeLibraryDir = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        handleProfiling = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        functionalTest = flag1;
    }

    InstrumentationInfo(Parcel parcel, InstrumentationInfo instrumentationinfo)
    {
        this(parcel);
    }

    public void copyTo(ApplicationInfo applicationinfo)
    {
        applicationinfo.packageName = packageName;
        applicationinfo.sourceDir = sourceDir;
        applicationinfo.publicSourceDir = publicSourceDir;
        applicationinfo.splitNames = splitNames;
        applicationinfo.splitSourceDirs = splitSourceDirs;
        applicationinfo.splitPublicSourceDirs = splitPublicSourceDirs;
        applicationinfo.splitDependencies = splitDependencies;
        applicationinfo.dataDir = dataDir;
        applicationinfo.deviceProtectedDataDir = deviceProtectedDataDir;
        applicationinfo.credentialProtectedDataDir = credentialProtectedDataDir;
        applicationinfo.nativeLibraryDir = nativeLibraryDir;
        applicationinfo.secondaryNativeLibraryDir = secondaryNativeLibraryDir;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("InstrumentationInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(packageName).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = false;
        super.writeToParcel(parcel, i);
        parcel.writeString(targetPackage);
        parcel.writeString(targetProcesses);
        parcel.writeString(sourceDir);
        parcel.writeString(publicSourceDir);
        parcel.writeStringArray(splitNames);
        parcel.writeStringArray(splitSourceDirs);
        parcel.writeStringArray(splitPublicSourceDirs);
        parcel.writeSparseArray(splitDependencies);
        parcel.writeString(dataDir);
        parcel.writeString(deviceProtectedDataDir);
        parcel.writeString(credentialProtectedDataDir);
        parcel.writeString(nativeLibraryDir);
        parcel.writeString(secondaryNativeLibraryDir);
        if(!handleProfiling)
            i = 0;
        else
            i = 1;
        parcel.writeInt(i);
        if(!functionalTest)
            i = ((flag) ? 1 : 0);
        else
            i = 1;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InstrumentationInfo createFromParcel(Parcel parcel)
        {
            return new InstrumentationInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InstrumentationInfo[] newArray(int i)
        {
            return new InstrumentationInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public String credentialProtectedDataDir;
    public String dataDir;
    public String deviceProtectedDataDir;
    public boolean functionalTest;
    public boolean handleProfiling;
    public String nativeLibraryDir;
    public String publicSourceDir;
    public String secondaryNativeLibraryDir;
    public String sourceDir;
    public SparseArray splitDependencies;
    public String splitNames[];
    public String splitPublicSourceDirs[];
    public String splitSourceDirs[];
    public String targetPackage;
    public String targetProcesses;

}
