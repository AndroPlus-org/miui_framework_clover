// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import android.util.TimeUtils;

// Referenced classes of package android.location:
//            Criteria

public final class LocationRequest
    implements Parcelable
{

    public LocationRequest()
    {
        mQuality = 201;
        mInterval = 0x36ee80L;
        mFastestInterval = (long)((double)mInterval / 6D);
        mExplicitFastestInterval = false;
        mExpireAt = 0x7fffffffffffffffL;
        mNumUpdates = 0x7fffffff;
        mSmallestDisplacement = 0.0F;
        mWorkSource = null;
        mHideFromAppOps = false;
        mProvider = "fused";
    }

    public LocationRequest(LocationRequest locationrequest)
    {
        mQuality = 201;
        mInterval = 0x36ee80L;
        mFastestInterval = (long)((double)mInterval / 6D);
        mExplicitFastestInterval = false;
        mExpireAt = 0x7fffffffffffffffL;
        mNumUpdates = 0x7fffffff;
        mSmallestDisplacement = 0.0F;
        mWorkSource = null;
        mHideFromAppOps = false;
        mProvider = "fused";
        mQuality = locationrequest.mQuality;
        mInterval = locationrequest.mInterval;
        mFastestInterval = locationrequest.mFastestInterval;
        mExplicitFastestInterval = locationrequest.mExplicitFastestInterval;
        mExpireAt = locationrequest.mExpireAt;
        mNumUpdates = locationrequest.mNumUpdates;
        mSmallestDisplacement = locationrequest.mSmallestDisplacement;
        mProvider = locationrequest.mProvider;
        mWorkSource = locationrequest.mWorkSource;
        mHideFromAppOps = locationrequest.mHideFromAppOps;
    }

    private static void checkDisplacement(float f)
    {
        if(f < 0.0F)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid displacement: ").append(f).toString());
        else
            return;
    }

    private static void checkInterval(long l)
    {
        if(l < 0L)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid interval: ").append(l).toString());
        else
            return;
    }

    private static void checkProvider(String s)
    {
        if(s == null)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid provider: ").append(s).toString());
        else
            return;
    }

    private static void checkQuality(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("invalid quality: ").append(i).toString());

        case 100: // 'd'
        case 102: // 'f'
        case 104: // 'h'
        case 200: 
        case 201: 
        case 203: 
            return;
        }
    }

    public static LocationRequest create()
    {
        return new LocationRequest();
    }

    public static LocationRequest createFromDeprecatedCriteria(Criteria criteria, long l, float f, boolean flag)
    {
        long l1;
        float f1;
        l1 = l;
        if(l < 0L)
            l1 = 0L;
        f1 = f;
        if(f < 0.0F)
            f1 = 0.0F;
        criteria.getAccuracy();
        JVM INSTR tableswitch 1 2: default 52
    //                   1 129
    //                   2 122;
           goto _L1 _L2 _L3
_L1:
        criteria.getPowerRequirement();
        JVM INSTR tableswitch 3 3: default 76
    //                   3 136;
           goto _L4 _L5
_L4:
        char c = '\311';
_L7:
        criteria = (new LocationRequest()).setQuality(c).setInterval(l1).setFastestInterval(l1).setSmallestDisplacement(f1);
        if(flag)
            criteria.setNumUpdates(1);
        return criteria;
_L3:
        c = 'f';
        continue; /* Loop/switch isn't completed */
_L2:
        c = 'd';
        continue; /* Loop/switch isn't completed */
_L5:
        c = '\313';
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static LocationRequest createFromDeprecatedProvider(String s, long l, float f, boolean flag)
    {
        long l1 = l;
        if(l < 0L)
            l1 = 0L;
        float f1 = f;
        if(f < 0.0F)
            f1 = 0.0F;
        char c;
        if("passive".equals(s))
            c = '\310';
        else
        if("gps".equals(s))
            c = 'd';
        else
            c = '\311';
        s = (new LocationRequest()).setProvider(s).setQuality(c).setInterval(l1).setFastestInterval(l1).setSmallestDisplacement(f1);
        if(flag)
            s.setNumUpdates(1);
        return s;
    }

    public static String qualityToString(int i)
    {
        switch(i)
        {
        default:
            return "???";

        case 100: // 'd'
            return "ACCURACY_FINE";

        case 102: // 'f'
            return "ACCURACY_BLOCK";

        case 104: // 'h'
            return "ACCURACY_CITY";

        case 200: 
            return "POWER_NONE";

        case 201: 
            return "POWER_LOW";

        case 203: 
            return "POWER_HIGH";
        }
    }

    public void decrementNumUpdates()
    {
        if(mNumUpdates != 0x7fffffff)
            mNumUpdates = mNumUpdates - 1;
        if(mNumUpdates < 0)
            mNumUpdates = 0;
    }

    public int describeContents()
    {
        return 0;
    }

    public long getExpireAt()
    {
        return mExpireAt;
    }

    public long getFastestInterval()
    {
        return mFastestInterval;
    }

    public boolean getHideFromAppOps()
    {
        return mHideFromAppOps;
    }

    public long getInterval()
    {
        return mInterval;
    }

    public int getNumUpdates()
    {
        return mNumUpdates;
    }

    public String getProvider()
    {
        return mProvider;
    }

    public int getQuality()
    {
        return mQuality;
    }

    public float getSmallestDisplacement()
    {
        return mSmallestDisplacement;
    }

    public WorkSource getWorkSource()
    {
        return mWorkSource;
    }

    public LocationRequest setExpireAt(long l)
    {
        mExpireAt = l;
        if(mExpireAt < 0L)
            mExpireAt = 0L;
        return this;
    }

    public LocationRequest setExpireIn(long l)
    {
        long l1 = SystemClock.elapsedRealtime();
        if(l > 0x7fffffffffffffffL - l1)
            mExpireAt = 0x7fffffffffffffffL;
        else
            mExpireAt = l + l1;
        if(mExpireAt < 0L)
            mExpireAt = 0L;
        return this;
    }

    public LocationRequest setFastestInterval(long l)
    {
        checkInterval(l);
        mExplicitFastestInterval = true;
        mFastestInterval = l;
        return this;
    }

    public void setHideFromAppOps(boolean flag)
    {
        mHideFromAppOps = flag;
    }

    public LocationRequest setInterval(long l)
    {
        checkInterval(l);
        mInterval = l;
        if(!mExplicitFastestInterval)
            mFastestInterval = (long)((double)mInterval / 6D);
        return this;
    }

    public LocationRequest setNumUpdates(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("invalid numUpdates: ").append(i).toString());
        } else
        {
            mNumUpdates = i;
            return this;
        }
    }

    public LocationRequest setProvider(String s)
    {
        checkProvider(s);
        mProvider = s;
        return this;
    }

    public LocationRequest setQuality(int i)
    {
        checkQuality(i);
        mQuality = i;
        return this;
    }

    public LocationRequest setSmallestDisplacement(float f)
    {
        checkDisplacement(f);
        mSmallestDisplacement = f;
        return this;
    }

    public void setWorkSource(WorkSource worksource)
    {
        mWorkSource = worksource;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Request[").append(qualityToString(mQuality));
        if(mProvider != null)
            stringbuilder.append(' ').append(mProvider);
        if(mQuality != 200)
        {
            stringbuilder.append(" requested=");
            TimeUtils.formatDuration(mInterval, stringbuilder);
        }
        stringbuilder.append(" fastest=");
        TimeUtils.formatDuration(mFastestInterval, stringbuilder);
        if(mExpireAt != 0x7fffffffffffffffL)
        {
            long l = mExpireAt;
            long l1 = SystemClock.elapsedRealtime();
            stringbuilder.append(" expireIn=");
            TimeUtils.formatDuration(l - l1, stringbuilder);
        }
        if(mNumUpdates != 0x7fffffff)
            stringbuilder.append(" num=").append(mNumUpdates);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mQuality);
        parcel.writeLong(mFastestInterval);
        parcel.writeLong(mInterval);
        parcel.writeLong(mExpireAt);
        parcel.writeInt(mNumUpdates);
        parcel.writeFloat(mSmallestDisplacement);
        if(mHideFromAppOps)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(mProvider);
        parcel.writeParcelable(mWorkSource, 0);
    }

    public static final int ACCURACY_BLOCK = 102;
    public static final int ACCURACY_CITY = 104;
    public static final int ACCURACY_FINE = 100;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LocationRequest createFromParcel(Parcel parcel)
        {
            boolean flag = false;
            LocationRequest locationrequest = new LocationRequest();
            locationrequest.setQuality(parcel.readInt());
            locationrequest.setFastestInterval(parcel.readLong());
            locationrequest.setInterval(parcel.readLong());
            locationrequest.setExpireAt(parcel.readLong());
            locationrequest.setNumUpdates(parcel.readInt());
            locationrequest.setSmallestDisplacement(parcel.readFloat());
            if(parcel.readInt() != 0)
                flag = true;
            locationrequest.setHideFromAppOps(flag);
            String s = parcel.readString();
            if(s != null)
                locationrequest.setProvider(s);
            parcel = (WorkSource)parcel.readParcelable(null);
            if(parcel != null)
                locationrequest.setWorkSource(parcel);
            return locationrequest;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LocationRequest[] newArray(int i)
        {
            return new LocationRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final double FASTEST_INTERVAL_FACTOR = 6D;
    public static final int POWER_HIGH = 203;
    public static final int POWER_LOW = 201;
    public static final int POWER_NONE = 200;
    private long mExpireAt;
    private boolean mExplicitFastestInterval;
    private long mFastestInterval;
    private boolean mHideFromAppOps;
    private long mInterval;
    private int mNumUpdates;
    private String mProvider;
    private int mQuality;
    private float mSmallestDisplacement;
    private WorkSource mWorkSource;

}
