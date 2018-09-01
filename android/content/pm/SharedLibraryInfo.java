// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.content.pm:
//            VersionedPackage

public final class SharedLibraryInfo
    implements Parcelable
{

    private SharedLibraryInfo(Parcel parcel)
    {
        this(parcel.readString(), parcel.readInt(), parcel.readInt(), (VersionedPackage)parcel.readParcelable(null), ((List) (parcel.readArrayList(null))));
    }

    SharedLibraryInfo(Parcel parcel, SharedLibraryInfo sharedlibraryinfo)
    {
        this(parcel);
    }

    public SharedLibraryInfo(String s, int i, int j, VersionedPackage versionedpackage, List list)
    {
        mName = s;
        mVersion = i;
        mType = j;
        mDeclaringPackage = versionedpackage;
        mDependentPackages = list;
    }

    private static String typeToString(int i)
    {
        switch(i)
        {
        default:
            return "unknown";

        case 0: // '\0'
            return "builtin";

        case 1: // '\001'
            return "dynamic";

        case 2: // '\002'
            return "static";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public VersionedPackage getDeclaringPackage()
    {
        return mDeclaringPackage;
    }

    public List getDependentPackages()
    {
        if(mDependentPackages == null)
            return Collections.emptyList();
        else
            return mDependentPackages;
    }

    public String getName()
    {
        return mName;
    }

    public int getType()
    {
        return mType;
    }

    public int getVersion()
    {
        return mVersion;
    }

    public boolean isBuiltin()
    {
        boolean flag = false;
        if(mType == 0)
            flag = true;
        return flag;
    }

    public boolean isDynamic()
    {
        boolean flag = true;
        if(mType != 1)
            flag = false;
        return flag;
    }

    public boolean isStatic()
    {
        boolean flag;
        if(mType == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("SharedLibraryInfo[name:").append(mName).append(", type:").append(typeToString(mType)).append(", version:").append(mVersion);
        String s;
        if(!getDependentPackages().isEmpty())
            s = " has dependents";
        else
            s = "";
        return stringbuilder.append(s).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mName);
        parcel.writeInt(mVersion);
        parcel.writeInt(mType);
        parcel.writeParcelable(mDeclaringPackage, i);
        parcel.writeList(mDependentPackages);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SharedLibraryInfo createFromParcel(Parcel parcel)
        {
            return new SharedLibraryInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SharedLibraryInfo[] newArray(int i)
        {
            return new SharedLibraryInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int TYPE_BUILTIN = 0;
    public static final int TYPE_DYNAMIC = 1;
    public static final int TYPE_STATIC = 2;
    public static final int VERSION_UNDEFINED = -1;
    private final VersionedPackage mDeclaringPackage;
    private final List mDependentPackages;
    private final String mName;
    private final int mType;
    private final int mVersion;

}
