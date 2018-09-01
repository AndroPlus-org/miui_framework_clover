// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class FeatureInfo
    implements Parcelable
{

    public FeatureInfo()
    {
    }

    public FeatureInfo(FeatureInfo featureinfo)
    {
        name = featureinfo.name;
        version = featureinfo.version;
        reqGlEsVersion = featureinfo.reqGlEsVersion;
        flags = featureinfo.flags;
    }

    private FeatureInfo(Parcel parcel)
    {
        name = parcel.readString();
        version = parcel.readInt();
        reqGlEsVersion = parcel.readInt();
        flags = parcel.readInt();
    }

    FeatureInfo(Parcel parcel, FeatureInfo featureinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getGlEsVersion()
    {
        int i = reqGlEsVersion;
        int j = reqGlEsVersion;
        return (new StringBuilder()).append(String.valueOf((i & 0xffff0000) >> 16)).append(".").append(String.valueOf(j & 0xffff)).toString();
    }

    public String toString()
    {
        if(name != null)
            return (new StringBuilder()).append("FeatureInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(name).append(" v=").append(version).append(" fl=0x").append(Integer.toHexString(flags)).append("}").toString();
        else
            return (new StringBuilder()).append("FeatureInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" glEsVers=").append(getGlEsVersion()).append(" fl=0x").append(Integer.toHexString(flags)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeInt(version);
        parcel.writeInt(reqGlEsVersion);
        parcel.writeInt(flags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FeatureInfo createFromParcel(Parcel parcel)
        {
            return new FeatureInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FeatureInfo[] newArray(int i)
        {
            return new FeatureInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_REQUIRED = 1;
    public static final int GL_ES_VERSION_UNDEFINED = 0;
    public int flags;
    public String name;
    public int reqGlEsVersion;
    public int version;

}
