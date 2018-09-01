// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.health;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package android.os.health:
//            TimerStat

public class HealthStats
{

    private HealthStats()
    {
        throw new RuntimeException("unsupported");
    }

    public HealthStats(Parcel parcel)
    {
        mDataType = parcel.readString();
        int i = parcel.readInt();
        mTimerKeys = new int[i];
        mTimerCounts = new int[i];
        mTimerTimes = new long[i];
        for(int j = 0; j < i; j++)
        {
            mTimerKeys[j] = parcel.readInt();
            mTimerCounts[j] = parcel.readInt();
            mTimerTimes[j] = parcel.readLong();
        }

        i = parcel.readInt();
        mMeasurementKeys = new int[i];
        mMeasurementValues = new long[i];
        for(int k = 0; k < i; k++)
        {
            mMeasurementKeys[k] = parcel.readInt();
            mMeasurementValues[k] = parcel.readLong();
        }

        i = parcel.readInt();
        mStatsKeys = new int[i];
        mStatsValues = new ArrayMap[i];
        for(int l = 0; l < i; l++)
        {
            mStatsKeys[l] = parcel.readInt();
            mStatsValues[l] = createHealthStatsMap(parcel);
        }

        i = parcel.readInt();
        mTimersKeys = new int[i];
        mTimersValues = new ArrayMap[i];
        for(int i1 = 0; i1 < i; i1++)
        {
            mTimersKeys[i1] = parcel.readInt();
            mTimersValues[i1] = createParcelableMap(parcel, TimerStat.CREATOR);
        }

        i = parcel.readInt();
        mMeasurementsKeys = new int[i];
        mMeasurementsValues = new ArrayMap[i];
        for(int j1 = 0; j1 < i; j1++)
        {
            mMeasurementsKeys[j1] = parcel.readInt();
            mMeasurementsValues[j1] = createLongsMap(parcel);
        }

    }

    private static ArrayMap createHealthStatsMap(Parcel parcel)
    {
        int i = parcel.readInt();
        ArrayMap arraymap = new ArrayMap(i);
        for(int j = 0; j < i; j++)
            arraymap.put(parcel.readString(), new HealthStats(parcel));

        return arraymap;
    }

    private static ArrayMap createLongsMap(Parcel parcel)
    {
        int i = parcel.readInt();
        ArrayMap arraymap = new ArrayMap(i);
        for(int j = 0; j < i; j++)
            arraymap.put(parcel.readString(), Long.valueOf(parcel.readLong()));

        return arraymap;
    }

    private static ArrayMap createParcelableMap(Parcel parcel, android.os.Parcelable.Creator creator)
    {
        int i = parcel.readInt();
        ArrayMap arraymap = new ArrayMap(i);
        for(int j = 0; j < i; j++)
            arraymap.put(parcel.readString(), (Parcelable)creator.createFromParcel(parcel));

        return arraymap;
    }

    private static int getIndex(int ai[], int i)
    {
        return Arrays.binarySearch(ai, i);
    }

    public String getDataType()
    {
        return mDataType;
    }

    public long getMeasurement(int i)
    {
        int j = getIndex(mMeasurementKeys, i);
        if(j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Bad measurement key dataType=").append(mDataType).append(" key=").append(i).toString());
        else
            return mMeasurementValues[j];
    }

    public int getMeasurementKeyAt(int i)
    {
        return mMeasurementKeys[i];
    }

    public int getMeasurementKeyCount()
    {
        return mMeasurementKeys.length;
    }

    public Map getMeasurements(int i)
    {
        int j = getIndex(mMeasurementsKeys, i);
        if(j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Bad measurements key dataType=").append(mDataType).append(" key=").append(i).toString());
        else
            return mMeasurementsValues[j];
    }

    public int getMeasurementsKeyAt(int i)
    {
        return mMeasurementsKeys[i];
    }

    public int getMeasurementsKeyCount()
    {
        return mMeasurementsKeys.length;
    }

    public Map getStats(int i)
    {
        int j = getIndex(mStatsKeys, i);
        if(j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Bad stats key dataType=").append(mDataType).append(" key=").append(i).toString());
        else
            return mStatsValues[j];
    }

    public int getStatsKeyAt(int i)
    {
        return mStatsKeys[i];
    }

    public int getStatsKeyCount()
    {
        return mStatsKeys.length;
    }

    public TimerStat getTimer(int i)
    {
        int j = getIndex(mTimerKeys, i);
        if(j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Bad timer key dataType=").append(mDataType).append(" key=").append(i).toString());
        else
            return new TimerStat(mTimerCounts[j], mTimerTimes[j]);
    }

    public int getTimerCount(int i)
    {
        int j = getIndex(mTimerKeys, i);
        if(j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Bad timer key dataType=").append(mDataType).append(" key=").append(i).toString());
        else
            return mTimerCounts[j];
    }

    public int getTimerKeyAt(int i)
    {
        return mTimerKeys[i];
    }

    public int getTimerKeyCount()
    {
        return mTimerKeys.length;
    }

    public long getTimerTime(int i)
    {
        int j = getIndex(mTimerKeys, i);
        if(j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Bad timer key dataType=").append(mDataType).append(" key=").append(i).toString());
        else
            return mTimerTimes[j];
    }

    public Map getTimers(int i)
    {
        int j = getIndex(mTimersKeys, i);
        if(j < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Bad timers key dataType=").append(mDataType).append(" key=").append(i).toString());
        else
            return mTimersValues[j];
    }

    public int getTimersKeyAt(int i)
    {
        return mTimersKeys[i];
    }

    public int getTimersKeyCount()
    {
        return mTimersKeys.length;
    }

    public boolean hasMeasurement(int i)
    {
        boolean flag = false;
        if(getIndex(mMeasurementKeys, i) >= 0)
            flag = true;
        return flag;
    }

    public boolean hasMeasurements(int i)
    {
        boolean flag = false;
        if(getIndex(mMeasurementsKeys, i) >= 0)
            flag = true;
        return flag;
    }

    public boolean hasStats(int i)
    {
        boolean flag = false;
        if(getIndex(mStatsKeys, i) >= 0)
            flag = true;
        return flag;
    }

    public boolean hasTimer(int i)
    {
        boolean flag = false;
        if(getIndex(mTimerKeys, i) >= 0)
            flag = true;
        return flag;
    }

    public boolean hasTimers(int i)
    {
        boolean flag = false;
        if(getIndex(mTimersKeys, i) >= 0)
            flag = true;
        return flag;
    }

    private String mDataType;
    private int mMeasurementKeys[];
    private long mMeasurementValues[];
    private int mMeasurementsKeys[];
    private ArrayMap mMeasurementsValues[];
    private int mStatsKeys[];
    private ArrayMap mStatsValues[];
    private int mTimerCounts[];
    private int mTimerKeys[];
    private long mTimerTimes[];
    private int mTimersKeys[];
    private ArrayMap mTimersValues[];
}
