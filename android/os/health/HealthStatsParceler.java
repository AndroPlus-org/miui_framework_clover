// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.health;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.os.health:
//            HealthStats, HealthStatsWriter

public class HealthStatsParceler
    implements Parcelable
{

    public HealthStatsParceler(Parcel parcel)
    {
        mHealthStats = new HealthStats(parcel);
    }

    public HealthStatsParceler(HealthStatsWriter healthstatswriter)
    {
        mWriter = healthstatswriter;
    }

    public int describeContents()
    {
        return 0;
    }

    public HealthStats getHealthStats()
    {
        if(mWriter != null)
        {
            Parcel parcel = Parcel.obtain();
            mWriter.flattenToParcel(parcel);
            parcel.setDataPosition(0);
            mHealthStats = new HealthStats(parcel);
            parcel.recycle();
        }
        return mHealthStats;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mWriter != null)
        {
            mWriter.flattenToParcel(parcel);
            return;
        } else
        {
            throw new RuntimeException("Can not re-parcel HealthStatsParceler that was constructed from a Parcel");
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public HealthStatsParceler createFromParcel(Parcel parcel)
        {
            return new HealthStatsParceler(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public HealthStatsParceler[] newArray(int i)
        {
            return new HealthStatsParceler[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private HealthStats mHealthStats;
    private HealthStatsWriter mWriter;

}
