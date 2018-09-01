// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class AdvertisingSetParameters
    implements Parcelable
{
    public static final class Builder
    {

        public AdvertisingSetParameters build()
        {
            if(isLegacy)
            {
                if(isAnonymous)
                    throw new IllegalArgumentException("Legacy advertising can't be anonymous");
                if(connectable && !scannable)
                    throw new IllegalStateException("Legacy advertisement can't be connectable and non-scannable");
                if(includeTxPower)
                    throw new IllegalStateException("Legacy advertising can't include TX power level in header");
            } else
            {
                if(connectable && scannable)
                    throw new IllegalStateException("Advertising can't be both connectable and scannable");
                if(isAnonymous && connectable)
                    throw new IllegalStateException("Advertising can't be both connectable and anonymous");
            }
            return new AdvertisingSetParameters(connectable, scannable, isLegacy, isAnonymous, includeTxPower, primaryPhy, secondaryPhy, interval, txPowerLevel, null);
        }

        public Builder setAnonymous(boolean flag)
        {
            isAnonymous = flag;
            return this;
        }

        public Builder setConnectable(boolean flag)
        {
            connectable = flag;
            return this;
        }

        public Builder setIncludeTxPower(boolean flag)
        {
            includeTxPower = flag;
            return this;
        }

        public Builder setInterval(int i)
        {
            if(i < 160 || i > 0xffffff)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unknown interval ").append(i).toString());
            } else
            {
                interval = i;
                return this;
            }
        }

        public Builder setLegacyMode(boolean flag)
        {
            isLegacy = flag;
            return this;
        }

        public Builder setPrimaryPhy(int i)
        {
            if(i != 1 && i != 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("bad primaryPhy ").append(i).toString());
            } else
            {
                primaryPhy = i;
                return this;
            }
        }

        public Builder setScannable(boolean flag)
        {
            scannable = flag;
            return this;
        }

        public Builder setSecondaryPhy(int i)
        {
            if(i != 1 && i != 2 && i != 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("bad secondaryPhy ").append(i).toString());
            } else
            {
                secondaryPhy = i;
                return this;
            }
        }

        public Builder setTxPowerLevel(int i)
        {
            if(i < -127 || i > 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unknown txPowerLevel ").append(i).toString());
            } else
            {
                txPowerLevel = i;
                return this;
            }
        }

        private boolean connectable;
        private boolean includeTxPower;
        private int interval;
        private boolean isAnonymous;
        private boolean isLegacy;
        private int primaryPhy;
        private boolean scannable;
        private int secondaryPhy;
        private int txPowerLevel;

        public Builder()
        {
            connectable = false;
            scannable = false;
            isLegacy = false;
            isAnonymous = false;
            includeTxPower = false;
            primaryPhy = 1;
            secondaryPhy = 1;
            interval = 160;
            txPowerLevel = -7;
        }
    }


    private AdvertisingSetParameters(Parcel parcel)
    {
        boolean flag = true;
        super();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        connectable = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        scannable = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        isLegacy = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        isAnonymous = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        includeTxPower = flag1;
        primaryPhy = parcel.readInt();
        secondaryPhy = parcel.readInt();
        interval = parcel.readInt();
        txPowerLevel = parcel.readInt();
    }

    AdvertisingSetParameters(Parcel parcel, AdvertisingSetParameters advertisingsetparameters)
    {
        this(parcel);
    }

    private AdvertisingSetParameters(boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, int i, int j, 
            int k, int l)
    {
        connectable = flag;
        scannable = flag1;
        isLegacy = flag2;
        isAnonymous = flag3;
        includeTxPower = flag4;
        primaryPhy = i;
        secondaryPhy = j;
        interval = k;
        txPowerLevel = l;
    }

    AdvertisingSetParameters(boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, int i, int j, 
            int k, int l, AdvertisingSetParameters advertisingsetparameters)
    {
        this(flag, flag1, flag2, flag3, flag4, i, j, k, l);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getInterval()
    {
        return interval;
    }

    public int getPrimaryPhy()
    {
        return primaryPhy;
    }

    public int getSecondaryPhy()
    {
        return secondaryPhy;
    }

    public int getTxPowerLevel()
    {
        return txPowerLevel;
    }

    public boolean includeTxPower()
    {
        return includeTxPower;
    }

    public boolean isAnonymous()
    {
        return isAnonymous;
    }

    public boolean isConnectable()
    {
        return connectable;
    }

    public boolean isLegacy()
    {
        return isLegacy;
    }

    public boolean isScannable()
    {
        return scannable;
    }

    public String toString()
    {
        return (new StringBuilder()).append("AdvertisingSetParameters [connectable=").append(connectable).append(", isLegacy=").append(isLegacy).append(", isAnonymous=").append(isAnonymous).append(", includeTxPower=").append(includeTxPower).append(", primaryPhy=").append(primaryPhy).append(", secondaryPhy=").append(secondaryPhy).append(", interval=").append(interval).append(", txPowerLevel=").append(txPowerLevel).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(connectable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(scannable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(isLegacy)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(isAnonymous)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(includeTxPower)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(primaryPhy);
        parcel.writeInt(secondaryPhy);
        parcel.writeInt(interval);
        parcel.writeInt(txPowerLevel);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AdvertisingSetParameters createFromParcel(Parcel parcel)
        {
            return new AdvertisingSetParameters(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AdvertisingSetParameters[] newArray(int i)
        {
            return new AdvertisingSetParameters[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int INTERVAL_HIGH = 1600;
    public static final int INTERVAL_LOW = 160;
    public static final int INTERVAL_MAX = 0xffffff;
    public static final int INTERVAL_MEDIUM = 400;
    public static final int INTERVAL_MIN = 160;
    private static final int LIMITED_ADVERTISING_MAX_MILLIS = 0x2bf20;
    public static final int TX_POWER_HIGH = 1;
    public static final int TX_POWER_LOW = -15;
    public static final int TX_POWER_MAX = 1;
    public static final int TX_POWER_MEDIUM = -7;
    public static final int TX_POWER_MIN = -127;
    public static final int TX_POWER_ULTRA_LOW = -21;
    private final boolean connectable;
    private final boolean includeTxPower;
    private final int interval;
    private final boolean isAnonymous;
    private final boolean isLegacy;
    private final int primaryPhy;
    private final boolean scannable;
    private final int secondaryPhy;
    private final int txPowerLevel;

}
