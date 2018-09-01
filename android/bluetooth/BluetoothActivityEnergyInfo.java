// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package android.bluetooth:
//            UidTraffic

public final class BluetoothActivityEnergyInfo
    implements Parcelable
{

    public BluetoothActivityEnergyInfo(long l, int i, long l1, long l2, 
            long l3, long l4)
    {
        mTimestamp = l;
        mBluetoothStackState = i;
        mControllerTxTimeMs = l1;
        mControllerRxTimeMs = l2;
        mControllerIdleTimeMs = l3;
        mControllerEnergyUsed = l4;
    }

    BluetoothActivityEnergyInfo(Parcel parcel)
    {
        mTimestamp = parcel.readLong();
        mBluetoothStackState = parcel.readInt();
        mControllerTxTimeMs = parcel.readLong();
        mControllerRxTimeMs = parcel.readLong();
        mControllerIdleTimeMs = parcel.readLong();
        mControllerEnergyUsed = parcel.readLong();
        mUidTraffic = (UidTraffic[])parcel.createTypedArray(UidTraffic.CREATOR);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getBluetoothStackState()
    {
        return mBluetoothStackState;
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

    public long getTimeStamp()
    {
        return mTimestamp;
    }

    public UidTraffic[] getUidTraffic()
    {
        return mUidTraffic;
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

    public void setUidTraffic(UidTraffic auidtraffic[])
    {
        mUidTraffic = auidtraffic;
    }

    public String toString()
    {
        return (new StringBuilder()).append("BluetoothActivityEnergyInfo{ mTimestamp=").append(mTimestamp).append(" mBluetoothStackState=").append(mBluetoothStackState).append(" mControllerTxTimeMs=").append(mControllerTxTimeMs).append(" mControllerRxTimeMs=").append(mControllerRxTimeMs).append(" mControllerIdleTimeMs=").append(mControllerIdleTimeMs).append(" mControllerEnergyUsed=").append(mControllerEnergyUsed).append(" mUidTraffic=").append(Arrays.toString(mUidTraffic)).append(" }").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mTimestamp);
        parcel.writeInt(mBluetoothStackState);
        parcel.writeLong(mControllerTxTimeMs);
        parcel.writeLong(mControllerRxTimeMs);
        parcel.writeLong(mControllerIdleTimeMs);
        parcel.writeLong(mControllerEnergyUsed);
        parcel.writeTypedArray(mUidTraffic, i);
    }

    public static final int BT_STACK_STATE_INVALID = 0;
    public static final int BT_STACK_STATE_STATE_ACTIVE = 1;
    public static final int BT_STACK_STATE_STATE_IDLE = 3;
    public static final int BT_STACK_STATE_STATE_SCANNING = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothActivityEnergyInfo createFromParcel(Parcel parcel)
        {
            return new BluetoothActivityEnergyInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothActivityEnergyInfo[] newArray(int i)
        {
            return new BluetoothActivityEnergyInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mBluetoothStackState;
    private long mControllerEnergyUsed;
    private long mControllerIdleTimeMs;
    private long mControllerRxTimeMs;
    private long mControllerTxTimeMs;
    private final long mTimestamp;
    private UidTraffic mUidTraffic[];

}
