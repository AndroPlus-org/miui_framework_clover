// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class WifiActivityEnergyInfo
    implements Parcelable
{

    public WifiActivityEnergyInfo(long l, int i, long l1, long al[], long l2, long l3, long l4)
    {
        mTimestamp = l;
        mStackState = i;
        mControllerTxTimeMs = l1;
        mControllerTxTimePerLevelMs = al;
        mControllerRxTimeMs = l2;
        mControllerIdleTimeMs = l3;
        mControllerEnergyUsed = l4;
    }

    public int describeContents()
    {
        return 0;
    }

    public long getControllerEnergyUsed()
    {
        return mControllerEnergyUsed;
    }

    public long getControllerIdleTimeMillis()
    {
        return mControllerIdleTimeMs;
    }

    public long getControllerRxTimeMillis()
    {
        return mControllerRxTimeMs;
    }

    public long getControllerTxTimeMillis()
    {
        return mControllerTxTimeMs;
    }

    public long getControllerTxTimeMillisAtLevel(int i)
    {
        if(i < mControllerTxTimePerLevelMs.length)
            return mControllerTxTimePerLevelMs[i];
        else
            return 0L;
    }

    public int getStackState()
    {
        return mStackState;
    }

    public long getTimeStamp()
    {
        return mTimestamp;
    }

    public boolean isValid()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mControllerTxTimeMs >= 0L)
        {
            flag1 = flag;
            if(mControllerRxTimeMs >= 0L)
            {
                flag1 = flag;
                if(mControllerIdleTimeMs >= 0L)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("WifiActivityEnergyInfo{ timestamp=").append(mTimestamp).append(" mStackState=").append(mStackState).append(" mControllerTxTimeMs=").append(mControllerTxTimeMs).append(" mControllerTxTimePerLevelMs=").append(Arrays.toString(mControllerTxTimePerLevelMs)).append(" mControllerRxTimeMs=").append(mControllerRxTimeMs).append(" mControllerIdleTimeMs=").append(mControllerIdleTimeMs).append(" mControllerEnergyUsed=").append(mControllerEnergyUsed).append(" }").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mTimestamp);
        parcel.writeInt(mStackState);
        parcel.writeLong(mControllerTxTimeMs);
        parcel.writeLongArray(mControllerTxTimePerLevelMs);
        parcel.writeLong(mControllerRxTimeMs);
        parcel.writeLong(mControllerIdleTimeMs);
        parcel.writeLong(mControllerEnergyUsed);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiActivityEnergyInfo createFromParcel(Parcel parcel)
        {
            return new WifiActivityEnergyInfo(parcel.readLong(), parcel.readInt(), parcel.readLong(), parcel.createLongArray(), parcel.readLong(), parcel.readLong(), parcel.readLong());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiActivityEnergyInfo[] newArray(int i)
        {
            return new WifiActivityEnergyInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STACK_STATE_INVALID = 0;
    public static final int STACK_STATE_STATE_ACTIVE = 1;
    public static final int STACK_STATE_STATE_IDLE = 3;
    public static final int STACK_STATE_STATE_SCANNING = 2;
    public long mControllerEnergyUsed;
    public long mControllerIdleTimeMs;
    public long mControllerRxTimeMs;
    public long mControllerTxTimeMs;
    public long mControllerTxTimePerLevelMs[];
    public int mStackState;
    public long mTimestamp;

}
