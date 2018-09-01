// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfigurationInfo
    implements Parcelable
{

    public ConfigurationInfo()
    {
        reqInputFeatures = 0;
    }

    public ConfigurationInfo(ConfigurationInfo configurationinfo)
    {
        reqInputFeatures = 0;
        reqTouchScreen = configurationinfo.reqTouchScreen;
        reqKeyboardType = configurationinfo.reqKeyboardType;
        reqNavigation = configurationinfo.reqNavigation;
        reqInputFeatures = configurationinfo.reqInputFeatures;
        reqGlEsVersion = configurationinfo.reqGlEsVersion;
    }

    private ConfigurationInfo(Parcel parcel)
    {
        reqInputFeatures = 0;
        reqTouchScreen = parcel.readInt();
        reqKeyboardType = parcel.readInt();
        reqNavigation = parcel.readInt();
        reqInputFeatures = parcel.readInt();
        reqGlEsVersion = parcel.readInt();
    }

    ConfigurationInfo(Parcel parcel, ConfigurationInfo configurationinfo)
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
        return (new StringBuilder()).append("ConfigurationInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" touchscreen = ").append(reqTouchScreen).append(" inputMethod = ").append(reqKeyboardType).append(" navigation = ").append(reqNavigation).append(" reqInputFeatures = ").append(reqInputFeatures).append(" reqGlEsVersion = ").append(reqGlEsVersion).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(reqTouchScreen);
        parcel.writeInt(reqKeyboardType);
        parcel.writeInt(reqNavigation);
        parcel.writeInt(reqInputFeatures);
        parcel.writeInt(reqGlEsVersion);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ConfigurationInfo createFromParcel(Parcel parcel)
        {
            return new ConfigurationInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ConfigurationInfo[] newArray(int i)
        {
            return new ConfigurationInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int GL_ES_VERSION_UNDEFINED = 0;
    public static final int INPUT_FEATURE_FIVE_WAY_NAV = 2;
    public static final int INPUT_FEATURE_HARD_KEYBOARD = 1;
    public int reqGlEsVersion;
    public int reqInputFeatures;
    public int reqKeyboardType;
    public int reqNavigation;
    public int reqTouchScreen;

}
