// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import com.android.internal.location.ProviderProperties;

// Referenced classes of package android.location:
//            Criteria

public class LocationProvider
{

    public LocationProvider(String s, ProviderProperties providerproperties)
    {
        if(s.matches("[^a-zA-Z0-9]"))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("provider name contains illegal character: ").append(s).toString());
        } else
        {
            mName = s;
            mProperties = providerproperties;
            return;
        }
    }

    public static boolean propertiesMeetCriteria(String s, ProviderProperties providerproperties, Criteria criteria)
    {
        if("passive".equals(s))
            return false;
        if(providerproperties == null)
            return false;
        if(criteria.getAccuracy() != 0 && criteria.getAccuracy() < providerproperties.mAccuracy)
            return false;
        if(criteria.getPowerRequirement() != 0 && criteria.getPowerRequirement() < providerproperties.mPowerRequirement)
            return false;
        if(criteria.isAltitudeRequired() && providerproperties.mSupportsAltitude ^ true)
            return false;
        if(criteria.isSpeedRequired() && providerproperties.mSupportsSpeed ^ true)
            return false;
        if(criteria.isBearingRequired() && providerproperties.mSupportsBearing ^ true)
            return false;
        return criteria.isCostAllowed() || !providerproperties.mHasMonetaryCost;
    }

    public int getAccuracy()
    {
        return mProperties.mAccuracy;
    }

    public String getName()
    {
        return mName;
    }

    public int getPowerRequirement()
    {
        return mProperties.mPowerRequirement;
    }

    public boolean hasMonetaryCost()
    {
        return mProperties.mHasMonetaryCost;
    }

    public boolean meetsCriteria(Criteria criteria)
    {
        return propertiesMeetCriteria(mName, mProperties, criteria);
    }

    public boolean requiresCell()
    {
        return mProperties.mRequiresCell;
    }

    public boolean requiresNetwork()
    {
        return mProperties.mRequiresNetwork;
    }

    public boolean requiresSatellite()
    {
        return mProperties.mRequiresSatellite;
    }

    public boolean supportsAltitude()
    {
        return mProperties.mSupportsAltitude;
    }

    public boolean supportsBearing()
    {
        return mProperties.mSupportsBearing;
    }

    public boolean supportsSpeed()
    {
        return mProperties.mSupportsSpeed;
    }

    public static final int AVAILABLE = 2;
    public static final String BAD_CHARS_REGEX = "[^a-zA-Z0-9]";
    public static final int OUT_OF_SERVICE = 0;
    public static final int TEMPORARILY_UNAVAILABLE = 1;
    private final String mName;
    private final ProviderProperties mProperties;
}
