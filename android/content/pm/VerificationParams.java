// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class VerificationParams
    implements Parcelable
{

    public VerificationParams(Uri uri, Uri uri1, Uri uri2, int i)
    {
        mVerificationURI = uri;
        mOriginatingURI = uri1;
        mReferrer = uri2;
        mOriginatingUid = i;
        mInstallerUid = -1;
    }

    private VerificationParams(Parcel parcel)
    {
        mVerificationURI = (Uri)parcel.readParcelable(android/net/Uri.getClassLoader());
        mOriginatingURI = (Uri)parcel.readParcelable(android/net/Uri.getClassLoader());
        mReferrer = (Uri)parcel.readParcelable(android/net/Uri.getClassLoader());
        mOriginatingUid = parcel.readInt();
        mInstallerUid = parcel.readInt();
    }

    VerificationParams(Parcel parcel, VerificationParams verificationparams)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof VerificationParams))
            return false;
        obj = (VerificationParams)obj;
        if(mVerificationURI == null)
        {
            if(((VerificationParams) (obj)).mVerificationURI != null)
                return false;
        } else
        if(!mVerificationURI.equals(((VerificationParams) (obj)).mVerificationURI))
            return false;
        if(mOriginatingURI == null)
        {
            if(((VerificationParams) (obj)).mOriginatingURI != null)
                return false;
        } else
        if(!mOriginatingURI.equals(((VerificationParams) (obj)).mOriginatingURI))
            return false;
        if(mReferrer == null)
        {
            if(((VerificationParams) (obj)).mReferrer != null)
                return false;
        } else
        if(!mReferrer.equals(((VerificationParams) (obj)).mReferrer))
            return false;
        if(mOriginatingUid != ((VerificationParams) (obj)).mOriginatingUid)
            return false;
        return mInstallerUid == ((VerificationParams) (obj)).mInstallerUid;
    }

    public int getInstallerUid()
    {
        return mInstallerUid;
    }

    public Uri getOriginatingURI()
    {
        return mOriginatingURI;
    }

    public int getOriginatingUid()
    {
        return mOriginatingUid;
    }

    public Uri getReferrer()
    {
        return mReferrer;
    }

    public Uri getVerificationURI()
    {
        return mVerificationURI;
    }

    public int hashCode()
    {
        int i = 1;
        int j;
        int k;
        if(mVerificationURI == null)
            j = 1;
        else
            j = mVerificationURI.hashCode();
        if(mOriginatingURI == null)
            k = 1;
        else
            k = mOriginatingURI.hashCode();
        if(mReferrer != null)
            i = mReferrer.hashCode();
        return j * 5 + 3 + k * 7 + i * 11 + mOriginatingUid * 13 + mInstallerUid * 17;
    }

    public void setInstallerUid(int i)
    {
        mInstallerUid = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("VerificationParams{");
        stringbuilder.append("mVerificationURI=");
        stringbuilder.append(mVerificationURI.toString());
        stringbuilder.append(",mOriginatingURI=");
        stringbuilder.append(mOriginatingURI.toString());
        stringbuilder.append(",mReferrer=");
        stringbuilder.append(mReferrer.toString());
        stringbuilder.append(",mOriginatingUid=");
        stringbuilder.append(mOriginatingUid);
        stringbuilder.append(",mInstallerUid=");
        stringbuilder.append(mInstallerUid);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mVerificationURI, 0);
        parcel.writeParcelable(mOriginatingURI, 0);
        parcel.writeParcelable(mReferrer, 0);
        parcel.writeInt(mOriginatingUid);
        parcel.writeInt(mInstallerUid);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VerificationParams createFromParcel(Parcel parcel)
        {
            return new VerificationParams(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VerificationParams[] newArray(int i)
        {
            return new VerificationParams[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int NO_UID = -1;
    private static final String TO_STRING_PREFIX = "VerificationParams{";
    private int mInstallerUid;
    private final Uri mOriginatingURI;
    private final int mOriginatingUid;
    private final Uri mReferrer;
    private final Uri mVerificationURI;

}
