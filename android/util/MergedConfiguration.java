// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;

public class MergedConfiguration
    implements Parcelable
{

    public MergedConfiguration()
    {
        mGlobalConfig = new Configuration();
        mOverrideConfig = new Configuration();
        mMergedConfig = new Configuration();
    }

    public MergedConfiguration(Configuration configuration)
    {
        mGlobalConfig = new Configuration();
        mOverrideConfig = new Configuration();
        mMergedConfig = new Configuration();
        setGlobalConfiguration(configuration);
    }

    public MergedConfiguration(Configuration configuration, Configuration configuration1)
    {
        mGlobalConfig = new Configuration();
        mOverrideConfig = new Configuration();
        mMergedConfig = new Configuration();
        setConfiguration(configuration, configuration1);
    }

    private MergedConfiguration(Parcel parcel)
    {
        mGlobalConfig = new Configuration();
        mOverrideConfig = new Configuration();
        mMergedConfig = new Configuration();
        readFromParcel(parcel);
    }

    MergedConfiguration(Parcel parcel, MergedConfiguration mergedconfiguration)
    {
        this(parcel);
    }

    public MergedConfiguration(MergedConfiguration mergedconfiguration)
    {
        mGlobalConfig = new Configuration();
        mOverrideConfig = new Configuration();
        mMergedConfig = new Configuration();
        setConfiguration(mergedconfiguration.getGlobalConfiguration(), mergedconfiguration.getOverrideConfiguration());
    }

    private void updateMergedConfig()
    {
        mMergedConfig.setTo(mGlobalConfig);
        mMergedConfig.updateFrom(mOverrideConfig);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(PrintWriter printwriter, String s)
    {
        printwriter.println((new StringBuilder()).append(s).append("mGlobalConfig=").append(mGlobalConfig).toString());
        printwriter.println((new StringBuilder()).append(s).append("mOverrideConfig=").append(mOverrideConfig).toString());
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof MergedConfiguration))
            return false;
        if(obj == this)
            return true;
        else
            return mMergedConfig.equals(((MergedConfiguration)obj).mMergedConfig);
    }

    public Configuration getGlobalConfiguration()
    {
        return mGlobalConfig;
    }

    public Configuration getMergedConfiguration()
    {
        return mMergedConfig;
    }

    public Configuration getOverrideConfiguration()
    {
        return mOverrideConfig;
    }

    public int hashCode()
    {
        return mMergedConfig.hashCode();
    }

    public void readFromParcel(Parcel parcel)
    {
        mGlobalConfig = (Configuration)parcel.readParcelable(android/content/res/Configuration.getClassLoader());
        mOverrideConfig = (Configuration)parcel.readParcelable(android/content/res/Configuration.getClassLoader());
        mMergedConfig = (Configuration)parcel.readParcelable(android/content/res/Configuration.getClassLoader());
    }

    public void setConfiguration(Configuration configuration, Configuration configuration1)
    {
        mGlobalConfig.setTo(configuration);
        mOverrideConfig.setTo(configuration1);
        updateMergedConfig();
    }

    public void setGlobalConfiguration(Configuration configuration)
    {
        mGlobalConfig.setTo(configuration);
        updateMergedConfig();
    }

    public void setOverrideConfiguration(Configuration configuration)
    {
        mOverrideConfig.setTo(configuration);
        updateMergedConfig();
    }

    public void setTo(MergedConfiguration mergedconfiguration)
    {
        setConfiguration(mergedconfiguration.mGlobalConfig, mergedconfiguration.mOverrideConfig);
    }

    public String toString()
    {
        return (new StringBuilder()).append("{mGlobalConfig=").append(mGlobalConfig).append(" mOverrideConfig=").append(mOverrideConfig).append("}").toString();
    }

    public void unset()
    {
        mGlobalConfig.unset();
        mOverrideConfig.unset();
        updateMergedConfig();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mGlobalConfig, i);
        parcel.writeParcelable(mOverrideConfig, i);
        parcel.writeParcelable(mMergedConfig, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MergedConfiguration createFromParcel(Parcel parcel)
        {
            return new MergedConfiguration(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MergedConfiguration[] newArray(int i)
        {
            return new MergedConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private Configuration mGlobalConfig;
    private Configuration mMergedConfig;
    private Configuration mOverrideConfig;

}
