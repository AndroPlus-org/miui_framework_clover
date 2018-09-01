// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class ConfigRequest
    implements Parcelable
{
    public static final class Builder
    {

        public ConfigRequest build()
        {
            if(mClusterLow > mClusterHigh)
                throw new IllegalArgumentException("Invalid argument combination - must have Cluster Low <= Cluster High");
            else
                return new ConfigRequest(mSupport5gBand, mMasterPreference, mClusterLow, mClusterHigh, mDiscoveryWindowInterval, null);
        }

        public Builder setClusterHigh(int i)
        {
            if(i < 0)
                throw new IllegalArgumentException("Cluster specification must be non-negative");
            if(i > 65535)
            {
                throw new IllegalArgumentException("Cluster specification must not exceed 0xFFFF");
            } else
            {
                mClusterHigh = i;
                return this;
            }
        }

        public Builder setClusterLow(int i)
        {
            if(i < 0)
                throw new IllegalArgumentException("Cluster specification must be non-negative");
            if(i > 65535)
            {
                throw new IllegalArgumentException("Cluster specification must not exceed 0xFFFF");
            } else
            {
                mClusterLow = i;
                return this;
            }
        }

        public Builder setDiscoveryWindowInterval(int i, int j)
        {
            if(i != 0 && i != 1)
                throw new IllegalArgumentException("Invalid band value");
            while(i == 0 && (j < 1 || j > 5) || i == 1 && (j < 0 || j > 5)) 
                throw new IllegalArgumentException("Invalid interval value: 2.4 GHz [1,5] or 5GHz [0,5]");
            mDiscoveryWindowInterval[i] = j;
            return this;
        }

        public Builder setMasterPreference(int i)
        {
            if(i < 0)
                throw new IllegalArgumentException("Master Preference specification must be non-negative");
            while(i == 1 || i == 255 || i > 255) 
                throw new IllegalArgumentException("Master Preference specification must not exceed 255 or use 1 or 255 (reserved values)");
            mMasterPreference = i;
            return this;
        }

        public Builder setSupport5gBand(boolean flag)
        {
            mSupport5gBand = flag;
            return this;
        }

        private int mClusterHigh;
        private int mClusterLow;
        private int mDiscoveryWindowInterval[] = {
            -1, -1
        };
        private int mMasterPreference;
        private boolean mSupport5gBand;

        public Builder()
        {
            mSupport5gBand = false;
            mMasterPreference = 0;
            mClusterLow = 0;
            mClusterHigh = 65535;
        }
    }


    private ConfigRequest(boolean flag, int i, int j, int k, int ai[])
    {
        mSupport5gBand = flag;
        mMasterPreference = i;
        mClusterLow = j;
        mClusterHigh = k;
        mDiscoveryWindowInterval = ai;
    }

    ConfigRequest(boolean flag, int i, int j, int k, int ai[], ConfigRequest configrequest)
    {
        this(flag, i, j, k, ai);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof ConfigRequest))
            return false;
        obj = (ConfigRequest)obj;
        boolean flag1 = flag;
        if(mSupport5gBand == ((ConfigRequest) (obj)).mSupport5gBand)
        {
            flag1 = flag;
            if(mMasterPreference == ((ConfigRequest) (obj)).mMasterPreference)
            {
                flag1 = flag;
                if(mClusterLow == ((ConfigRequest) (obj)).mClusterLow)
                {
                    flag1 = flag;
                    if(mClusterHigh == ((ConfigRequest) (obj)).mClusterHigh)
                        flag1 = Arrays.equals(mDiscoveryWindowInterval, ((ConfigRequest) (obj)).mDiscoveryWindowInterval);
                }
            }
        }
        return flag1;
    }

    public int hashCode()
    {
        int i;
        if(mSupport5gBand)
            i = 1;
        else
            i = 0;
        return ((((i + 527) * 31 + mMasterPreference) * 31 + mClusterLow) * 31 + mClusterHigh) * 31 + Arrays.hashCode(mDiscoveryWindowInterval);
    }

    public String toString()
    {
        return (new StringBuilder()).append("ConfigRequest [mSupport5gBand=").append(mSupport5gBand).append(", mMasterPreference=").append(mMasterPreference).append(", mClusterLow=").append(mClusterLow).append(", mClusterHigh=").append(mClusterHigh).append(", mDiscoveryWindowInterval=").append(Arrays.toString(mDiscoveryWindowInterval)).append("]").toString();
    }

    public void validate()
        throws IllegalArgumentException
    {
        if(mMasterPreference < 0)
            throw new IllegalArgumentException("Master Preference specification must be non-negative");
        while(mMasterPreference == 1 || mMasterPreference == 255 || mMasterPreference > 255) 
            throw new IllegalArgumentException("Master Preference specification must not exceed 255 or use 1 or 255 (reserved values)");
        if(mClusterLow < 0)
            throw new IllegalArgumentException("Cluster specification must be non-negative");
        if(mClusterLow > 65535)
            throw new IllegalArgumentException("Cluster specification must not exceed 0xFFFF");
        if(mClusterHigh < 0)
            throw new IllegalArgumentException("Cluster specification must be non-negative");
        if(mClusterHigh > 65535)
            throw new IllegalArgumentException("Cluster specification must not exceed 0xFFFF");
        if(mClusterLow > mClusterHigh)
            throw new IllegalArgumentException("Invalid argument combination - must have Cluster Low <= Cluster High");
        if(mDiscoveryWindowInterval.length != 2)
            throw new IllegalArgumentException("Invalid discovery window interval: must have 2 elements (2.4 & 5");
        if(mDiscoveryWindowInterval[0] != -1 && (mDiscoveryWindowInterval[0] < 1 || mDiscoveryWindowInterval[0] > 5))
            throw new IllegalArgumentException("Invalid discovery window interval for 2.4GHz: valid is UNSET or [1,5]");
        if(mDiscoveryWindowInterval[1] != -1 && (mDiscoveryWindowInterval[1] < 0 || mDiscoveryWindowInterval[1] > 5))
            throw new IllegalArgumentException("Invalid discovery window interval for 5GHz: valid is UNSET or [0,5]");
        else
            return;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mSupport5gBand)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mMasterPreference);
        parcel.writeInt(mClusterLow);
        parcel.writeInt(mClusterHigh);
        parcel.writeIntArray(mDiscoveryWindowInterval);
    }

    public static final int CLUSTER_ID_MAX = 65535;
    public static final int CLUSTER_ID_MIN = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ConfigRequest createFromParcel(Parcel parcel)
        {
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            return new ConfigRequest(flag, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createIntArray(), null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ConfigRequest[] newArray(int i)
        {
            return new ConfigRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DW_DISABLE = 0;
    public static final int DW_INTERVAL_NOT_INIT = -1;
    public static final int NAN_BAND_24GHZ = 0;
    public static final int NAN_BAND_5GHZ = 1;
    public final int mClusterHigh;
    public final int mClusterLow;
    public final int mDiscoveryWindowInterval[];
    public final int mMasterPreference;
    public final boolean mSupport5gBand;

}
