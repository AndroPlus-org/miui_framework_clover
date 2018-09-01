// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class ModemActivityInfo
    implements Parcelable
{

    public ModemActivityInfo(long l, int i, int j, int ai[], int k, int i1)
    {
        mTimestamp = l;
        mSleepTimeMs = i;
        mIdleTimeMs = j;
        if(ai != null)
            System.arraycopy(ai, 0, mTxTimeMs, 0, Math.min(ai.length, 5));
        mRxTimeMs = k;
        mEnergyUsed = i1;
    }

    private boolean isEmpty()
    {
        boolean flag = false;
        int ai[] = getTxTimeMillis();
        int i = ai.length;
        for(int j = 0; j < i; j++)
            if(ai[j] != 0)
                return false;

        boolean flag1 = flag;
        if(getIdleTimeMillis() == 0)
        {
            flag1 = flag;
            if(getSleepTimeMillis() == 0)
            {
                flag1 = flag;
                if(getRxTimeMillis() == 0)
                {
                    flag1 = flag;
                    if(getEnergyUsed() == 0)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getEnergyUsed()
    {
        return mEnergyUsed;
    }

    public int getIdleTimeMillis()
    {
        return mIdleTimeMs;
    }

    public int getRxTimeMillis()
    {
        return mRxTimeMs;
    }

    public int getSleepTimeMillis()
    {
        return mSleepTimeMs;
    }

    public long getTimestamp()
    {
        return mTimestamp;
    }

    public int[] getTxTimeMillis()
    {
        return mTxTimeMs;
    }

    public boolean isValid()
    {
        boolean flag = false;
        int ai[] = getTxTimeMillis();
        int i = ai.length;
        for(int j = 0; j < i; j++)
            if(ai[j] < 0)
                return false;

        boolean flag1 = flag;
        if(getIdleTimeMillis() >= 0)
        {
            flag1 = flag;
            if(getSleepTimeMillis() >= 0)
            {
                flag1 = flag;
                if(getRxTimeMillis() >= 0)
                {
                    flag1 = flag;
                    if(getEnergyUsed() >= 0)
                        flag1 = isEmpty() ^ true;
                }
            }
        }
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ModemActivityInfo{ mTimestamp=").append(mTimestamp).append(" mSleepTimeMs=").append(mSleepTimeMs).append(" mIdleTimeMs=").append(mIdleTimeMs).append(" mTxTimeMs[]=").append(Arrays.toString(mTxTimeMs)).append(" mRxTimeMs=").append(mRxTimeMs).append(" mEnergyUsed=").append(mEnergyUsed).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mTimestamp);
        parcel.writeInt(mSleepTimeMs);
        parcel.writeInt(mIdleTimeMs);
        for(i = 0; i < 5; i++)
            parcel.writeInt(mTxTimeMs[i]);

        parcel.writeInt(mRxTimeMs);
        parcel.writeInt(mEnergyUsed);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ModemActivityInfo createFromParcel(Parcel parcel)
        {
            long l = parcel.readLong();
            int i = parcel.readInt();
            int j = parcel.readInt();
            int ai[] = new int[5];
            for(int k = 0; k < 5; k++)
                ai[k] = parcel.readInt();

            return new ModemActivityInfo(l, i, j, ai, parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ModemActivityInfo[] newArray(int i)
        {
            return new ModemActivityInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int TX_POWER_LEVELS = 5;
    private final int mEnergyUsed;
    private final int mIdleTimeMs;
    private final int mRxTimeMs;
    private final int mSleepTimeMs;
    private final long mTimestamp;
    private final int mTxTimeMs[] = new int[5];

}
