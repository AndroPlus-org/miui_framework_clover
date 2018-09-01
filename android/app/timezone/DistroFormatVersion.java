// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.app.timezone:
//            Utils

public final class DistroFormatVersion
    implements Parcelable
{

    public DistroFormatVersion(int i, int j)
    {
        mMajorVersion = Utils.validateVersion("major", i);
        mMinorVersion = Utils.validateVersion("minor", j);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (DistroFormatVersion)obj;
        if(mMajorVersion != ((DistroFormatVersion) (obj)).mMajorVersion)
            return false;
        if(mMinorVersion != ((DistroFormatVersion) (obj)).mMinorVersion)
            flag = false;
        return flag;
    }

    public int getMajorVersion()
    {
        return mMajorVersion;
    }

    public int getMinorVersion()
    {
        return mMinorVersion;
    }

    public int hashCode()
    {
        return mMajorVersion * 31 + mMinorVersion;
    }

    public boolean supports(DistroFormatVersion distroformatversion)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mMajorVersion == distroformatversion.mMajorVersion)
        {
            flag1 = flag;
            if(mMinorVersion <= distroformatversion.mMinorVersion)
                flag1 = true;
        }
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("DistroFormatVersion{mMajorVersion=").append(mMajorVersion).append(", mMinorVersion=").append(mMinorVersion).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMajorVersion);
        parcel.writeInt(mMinorVersion);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DistroFormatVersion createFromParcel(Parcel parcel)
        {
            return new DistroFormatVersion(parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DistroFormatVersion[] newArray(int i)
        {
            return new DistroFormatVersion[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mMajorVersion;
    private final int mMinorVersion;

}
