// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class LocationPolicy
    implements Parcelable
{

    public LocationPolicy(int i, String s, boolean flag)
    {
        mMatchRule = i;
        mProvider = s;
        mHighCost = flag;
        mMinIntervalMs = 0;
    }

    public LocationPolicy(int i, String s, boolean flag, int j)
    {
        mMatchRule = i;
        mProvider = s;
        mHighCost = flag;
        mMinIntervalMs = j;
    }

    private LocationPolicy(Parcel parcel)
    {
        boolean flag = false;
        super();
        mMatchRule = parcel.readInt();
        mProvider = parcel.readString();
        if(parcel.readInt() != 0)
            flag = true;
        mHighCost = flag;
        mMinIntervalMs = parcel.readInt();
    }

    LocationPolicy(Parcel parcel, LocationPolicy locationpolicy)
    {
        this(parcel);
    }

    public static LocationPolicy buildDefaultPolicyFusedLocation()
    {
        return new LocationPolicy(3, "fused", true);
    }

    public static LocationPolicy buildDefaultPolicyGPSLocation()
    {
        return new LocationPolicy(2, "gps", true);
    }

    public static LocationPolicy buildDefaultPolicyNetworkLocation()
    {
        return new LocationPolicy(1, "network", false);
    }

    public static LocationPolicy buildDefaultPolicyPassiveLocation()
    {
        return new LocationPolicy(4, "passive", false);
    }

    public static LocationPolicy getLocationPolicy(String s, int i)
    {
        if(s.equals("network"))
            return new LocationPolicy(1, "network", false, i);
        if(s.equals("gps"))
            return new LocationPolicy(2, "gps", true, i);
        if(s.equals("passive"))
            return new LocationPolicy(4, "passive", false, i);
        if(s.equals("fused"))
            return new LocationPolicy(3, "fused", true, i);
        else
            throw new IllegalArgumentException("unknown location provider");
    }

    private static String getMatchRuleName(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 1: // '\001'
            return "NETWORK_PROVIDER";

        case 2: // '\002'
            return "GPS_PROVIDER";

        case 3: // '\003'
            return "FUSED_PROVIDER";

        case 4: // '\004'
            return "PASSIVE_PROVIDER";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof LocationPolicy)
        {
            obj = (LocationPolicy)obj;
            boolean flag1 = flag;
            if(mMatchRule == ((LocationPolicy) (obj)).mMatchRule)
            {
                flag1 = flag;
                if(mProvider == ((LocationPolicy) (obj)).mProvider)
                {
                    flag1 = flag;
                    if(mHighCost == ((LocationPolicy) (obj)).mHighCost)
                    {
                        flag1 = flag;
                        if(mMinIntervalMs == ((LocationPolicy) (obj)).mMinIntervalMs)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public int getMatchRule()
    {
        return mMatchRule;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mMatchRule), Boolean.valueOf(mHighCost), Integer.valueOf(mMinIntervalMs)
        });
    }

    public boolean matches(String s)
    {
        switch(mMatchRule)
        {
        default:
            throw new IllegalArgumentException("unknown location provider");

        case 1: // '\001'
            return s.equals("network");

        case 2: // '\002'
            return s.equals("gps");

        case 3: // '\003'
            return s.equals("fused");

        case 4: // '\004'
            return s.equals("passive");
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("LocationPolicy: ");
        stringbuilder.append("matchRule=").append(getMatchRuleName(mMatchRule));
        stringbuilder.append(", provider=").append(mProvider);
        stringbuilder.append(", highCost=").append(mHighCost);
        stringbuilder.append(", minInterval=").append(mMinIntervalMs);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMatchRule);
        parcel.writeString(mProvider);
        if(mHighCost)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mMinIntervalMs);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LocationPolicy createFromParcel(Parcel parcel)
        {
            return new LocationPolicy(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LocationPolicy[] newArray(int i)
        {
            return new LocationPolicy[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MATCH_FUSED_PROVIDER = 3;
    public static final int MATCH_GPS_PROVIDER = 2;
    public static final int MATCH_NETWORK_PROVIDER = 1;
    public static final int MATCH_PASSIVE_PROVIDER = 4;
    public final boolean mHighCost;
    private final int mMatchRule;
    public final int mMinIntervalMs;
    public final String mProvider;

}
