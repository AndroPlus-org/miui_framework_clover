// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.content.pm.Signature;
import android.os.Parcel;
import android.os.Parcelable;

public class KeyAttestationPackageInfo
    implements Parcelable
{

    private KeyAttestationPackageInfo(Parcel parcel)
    {
        mPackageName = parcel.readString();
        mPackageVersionCode = parcel.readInt();
        mPackageSignatures = (Signature[])parcel.createTypedArray(Signature.CREATOR);
    }

    KeyAttestationPackageInfo(Parcel parcel, KeyAttestationPackageInfo keyattestationpackageinfo)
    {
        this(parcel);
    }

    public KeyAttestationPackageInfo(String s, int i, Signature asignature[])
    {
        mPackageName = s;
        mPackageVersionCode = i;
        mPackageSignatures = asignature;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public Signature[] getPackageSignatures()
    {
        return mPackageSignatures;
    }

    public int getPackageVersionCode()
    {
        return mPackageVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackageName);
        parcel.writeInt(mPackageVersionCode);
        parcel.writeTypedArray(mPackageSignatures, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeyAttestationPackageInfo createFromParcel(Parcel parcel)
        {
            return new KeyAttestationPackageInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeyAttestationPackageInfo[] newArray(int i)
        {
            return new KeyAttestationPackageInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mPackageName;
    private final Signature mPackageSignatures[];
    private final int mPackageVersionCode;

}
