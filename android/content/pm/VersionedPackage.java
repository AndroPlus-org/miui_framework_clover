// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Annotation;

public final class VersionedPackage
    implements Parcelable
{
    public static interface VersionCode
        extends Annotation
    {
    }


    private VersionedPackage(Parcel parcel)
    {
        mPackageName = parcel.readString();
        mVersionCode = parcel.readInt();
    }

    VersionedPackage(Parcel parcel, VersionedPackage versionedpackage)
    {
        this(parcel);
    }

    public VersionedPackage(String s, int i)
    {
        mPackageName = s;
        mVersionCode = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getVersionCode()
    {
        return mVersionCode;
    }

    public String toString()
    {
        return (new StringBuilder()).append("VersionedPackage[").append(mPackageName).append("/").append(mVersionCode).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackageName);
        parcel.writeInt(mVersionCode);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VersionedPackage createFromParcel(Parcel parcel)
        {
            return new VersionedPackage(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VersionedPackage[] newArray(int i)
        {
            return new VersionedPackage[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mPackageName;
    private final int mVersionCode;

}
