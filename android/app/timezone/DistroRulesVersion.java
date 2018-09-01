// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.app.timezone:
//            Utils

public final class DistroRulesVersion
    implements Parcelable
{

    public DistroRulesVersion(String s, int i)
    {
        mRulesVersion = Utils.validateRulesVersion("rulesVersion", s);
        mRevision = Utils.validateVersion("revision", i);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (DistroRulesVersion)obj;
        if(mRevision != ((DistroRulesVersion) (obj)).mRevision)
            return false;
        else
            return mRulesVersion.equals(((DistroRulesVersion) (obj)).mRulesVersion);
    }

    public int getRevision()
    {
        return mRevision;
    }

    public String getRulesVersion()
    {
        return mRulesVersion;
    }

    public int hashCode()
    {
        return mRulesVersion.hashCode() * 31 + mRevision;
    }

    public boolean isOlderThan(DistroRulesVersion distrorulesversion)
    {
        boolean flag = true;
        int i = mRulesVersion.compareTo(distrorulesversion.mRulesVersion);
        if(i < 0)
            return true;
        if(i > 0)
            return false;
        if(mRevision >= distrorulesversion.mRevision)
            flag = false;
        return flag;
    }

    public String toDumpString()
    {
        return (new StringBuilder()).append(mRulesVersion).append(",").append(mRevision).toString();
    }

    public String toString()
    {
        return (new StringBuilder()).append("DistroRulesVersion{mRulesVersion='").append(mRulesVersion).append('\'').append(", mRevision='").append(mRevision).append('\'').append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mRulesVersion);
        parcel.writeInt(mRevision);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DistroRulesVersion createFromParcel(Parcel parcel)
        {
            return new DistroRulesVersion(parcel.readString(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DistroRulesVersion[] newArray(int i)
        {
            return new DistroRulesVersion[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mRevision;
    private final String mRulesVersion;

}
