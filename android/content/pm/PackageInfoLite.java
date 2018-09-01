// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.content.pm:
//            VerifierInfo

public class PackageInfoLite
    implements Parcelable
{

    public PackageInfoLite()
    {
    }

    private PackageInfoLite(Parcel parcel)
    {
        packageName = parcel.readString();
        splitNames = parcel.createStringArray();
        versionCode = parcel.readInt();
        baseRevisionCode = parcel.readInt();
        splitRevisionCodes = parcel.createIntArray();
        recommendedInstallLocation = parcel.readInt();
        installLocation = parcel.readInt();
        boolean flag;
        int i;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        multiArch = flag;
        i = parcel.readInt();
        if(i == 0)
        {
            verifiers = new VerifierInfo[0];
        } else
        {
            verifiers = new VerifierInfo[i];
            parcel.readTypedArray(verifiers, VerifierInfo.CREATOR);
        }
    }

    PackageInfoLite(Parcel parcel, PackageInfoLite packageinfolite)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("PackageInfoLite{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(packageName).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(packageName);
        parcel.writeStringArray(splitNames);
        parcel.writeInt(versionCode);
        parcel.writeInt(baseRevisionCode);
        parcel.writeIntArray(splitRevisionCodes);
        parcel.writeInt(recommendedInstallLocation);
        parcel.writeInt(installLocation);
        int j;
        if(multiArch)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(verifiers == null || verifiers.length == 0)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(verifiers.length);
            parcel.writeTypedArray(verifiers, i);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PackageInfoLite createFromParcel(Parcel parcel)
        {
            return new PackageInfoLite(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PackageInfoLite[] newArray(int i)
        {
            return new PackageInfoLite[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int baseRevisionCode;
    public int installLocation;
    public boolean multiArch;
    public String packageName;
    public int recommendedInstallLocation;
    public String splitNames[];
    public int splitRevisionCodes[];
    public VerifierInfo verifiers[];
    public int versionCode;

}
