// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.health;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

// Referenced classes of package android.os.health:
//            TimerStat

public class HealthStatsWriter
{

    public HealthStatsWriter(HealthKeys.Constants constants)
    {
        mConstants = constants;
        int i = constants.getSize(0);
        mTimerFields = new boolean[i];
        mTimerCounts = new int[i];
        mTimerTimes = new long[i];
        i = constants.getSize(1);
        mMeasurementFields = new boolean[i];
        mMeasurementValues = new long[i];
        mStatsValues = new ArrayMap[constants.getSize(2)];
        mTimersValues = new ArrayMap[constants.getSize(3)];
        mMeasurementsValues = new ArrayMap[constants.getSize(4)];
    }

    private static int countBooleanArray(boolean aflag[])
    {
        int i = 0;
        int j = aflag.length;
        for(int k = 0; k < j;)
        {
            int l = i;
            if(aflag[k])
                l = i + 1;
            k++;
            i = l;
        }

        return i;
    }

    private static int countObjectArray(Object aobj[])
    {
        int i = 0;
        int j = aobj.length;
        for(int k = 0; k < j;)
        {
            int l = i;
            if(aobj[k] != null)
                l = i + 1;
            k++;
            i = l;
        }

        return i;
    }

    private static void writeHealthStatsWriterMap(Parcel parcel, ArrayMap arraymap)
    {
        int i = arraymap.size();
        parcel.writeInt(i);
        for(int j = 0; j < i; j++)
        {
            parcel.writeString((String)arraymap.keyAt(j));
            ((HealthStatsWriter)arraymap.valueAt(j)).flattenToParcel(parcel);
        }

    }

    private static void writeLongsMap(Parcel parcel, ArrayMap arraymap)
    {
        int i = arraymap.size();
        parcel.writeInt(i);
        for(int j = 0; j < i; j++)
        {
            parcel.writeString((String)arraymap.keyAt(j));
            parcel.writeLong(((Long)arraymap.valueAt(j)).longValue());
        }

    }

    private static void writeParcelableMap(Parcel parcel, ArrayMap arraymap)
    {
        int i = arraymap.size();
        parcel.writeInt(i);
        for(int j = 0; j < i; j++)
        {
            parcel.writeString((String)arraymap.keyAt(j));
            ((Parcelable)arraymap.valueAt(j)).writeToParcel(parcel, 0);
        }

    }

    public void addMeasurement(int i, long l)
    {
        i = mConstants.getIndex(1, i);
        mMeasurementFields[i] = true;
        mMeasurementValues[i] = l;
    }

    public void addMeasurements(int i, String s, long l)
    {
        i = mConstants.getIndex(4, i);
        ArrayMap arraymap = mMeasurementsValues[i];
        ArrayMap arraymap1 = arraymap;
        if(arraymap == null)
        {
            arraymap1 = new ArrayMap(1);
            mMeasurementsValues[i] = arraymap1;
        }
        arraymap1.put(s, Long.valueOf(l));
    }

    public void addStats(int i, String s, HealthStatsWriter healthstatswriter)
    {
        i = mConstants.getIndex(2, i);
        ArrayMap arraymap = mStatsValues[i];
        ArrayMap arraymap1 = arraymap;
        if(arraymap == null)
        {
            arraymap1 = new ArrayMap(1);
            mStatsValues[i] = arraymap1;
        }
        arraymap1.put(s, healthstatswriter);
    }

    public void addTimer(int i, int j, long l)
    {
        i = mConstants.getIndex(0, i);
        mTimerFields[i] = true;
        mTimerCounts[i] = j;
        mTimerTimes[i] = l;
    }

    public void addTimers(int i, String s, TimerStat timerstat)
    {
        i = mConstants.getIndex(3, i);
        ArrayMap arraymap = mTimersValues[i];
        ArrayMap arraymap1 = arraymap;
        if(arraymap == null)
        {
            arraymap1 = new ArrayMap(1);
            mTimersValues[i] = arraymap1;
        }
        arraymap1.put(s, timerstat);
    }

    public void flattenToParcel(Parcel parcel)
    {
        parcel.writeString(mConstants.getDataType());
        parcel.writeInt(countBooleanArray(mTimerFields));
        int ai[] = mConstants.getKeys(0);
        for(int i = 0; i < ai.length; i++)
            if(mTimerFields[i])
            {
                parcel.writeInt(ai[i]);
                parcel.writeInt(mTimerCounts[i]);
                parcel.writeLong(mTimerTimes[i]);
            }

        parcel.writeInt(countBooleanArray(mMeasurementFields));
        ai = mConstants.getKeys(1);
        for(int j = 0; j < ai.length; j++)
            if(mMeasurementFields[j])
            {
                parcel.writeInt(ai[j]);
                parcel.writeLong(mMeasurementValues[j]);
            }

        parcel.writeInt(countObjectArray(mStatsValues));
        ai = mConstants.getKeys(2);
        for(int k = 0; k < ai.length; k++)
            if(mStatsValues[k] != null)
            {
                parcel.writeInt(ai[k]);
                writeHealthStatsWriterMap(parcel, mStatsValues[k]);
            }

        parcel.writeInt(countObjectArray(mTimersValues));
        ai = mConstants.getKeys(3);
        for(int l = 0; l < ai.length; l++)
            if(mTimersValues[l] != null)
            {
                parcel.writeInt(ai[l]);
                writeParcelableMap(parcel, mTimersValues[l]);
            }

        parcel.writeInt(countObjectArray(mMeasurementsValues));
        ai = mConstants.getKeys(4);
        for(int i1 = 0; i1 < ai.length; i1++)
            if(mMeasurementsValues[i1] != null)
            {
                parcel.writeInt(ai[i1]);
                writeLongsMap(parcel, mMeasurementsValues[i1]);
            }

    }

    private final HealthKeys.Constants mConstants;
    private final boolean mMeasurementFields[];
    private final long mMeasurementValues[];
    private final ArrayMap mMeasurementsValues[];
    private final ArrayMap mStatsValues[];
    private final int mTimerCounts[];
    private final boolean mTimerFields[];
    private final long mTimerTimes[];
    private final ArrayMap mTimersValues[];
}
