// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class PeriodicAdvertisingParameters
    implements Parcelable
{
    public static final class Builder
    {

        public PeriodicAdvertisingParameters build()
        {
            return new PeriodicAdvertisingParameters(includeTxPower, interval, null);
        }

        public Builder setIncludeTxPower(boolean flag)
        {
            includeTxPower = flag;
            return this;
        }

        public Builder setInterval(int i)
        {
            if(i < 80 || i > 65519)
            {
                throw new IllegalArgumentException("Invalid interval (must be 80-65519)");
            } else
            {
                interval = i;
                return this;
            }
        }

        private boolean includeTxPower;
        private int interval;

        public Builder()
        {
            includeTxPower = false;
            interval = 65519;
        }
    }


    private PeriodicAdvertisingParameters(Parcel parcel)
    {
        boolean flag = false;
        super();
        if(parcel.readInt() != 0)
            flag = true;
        includeTxPower = flag;
        interval = parcel.readInt();
    }

    PeriodicAdvertisingParameters(Parcel parcel, PeriodicAdvertisingParameters periodicadvertisingparameters)
    {
        this(parcel);
    }

    private PeriodicAdvertisingParameters(boolean flag, int i)
    {
        includeTxPower = flag;
        interval = i;
    }

    PeriodicAdvertisingParameters(boolean flag, int i, PeriodicAdvertisingParameters periodicadvertisingparameters)
    {
        this(flag, i);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getIncludeTxPower()
    {
        return includeTxPower;
    }

    public int getInterval()
    {
        return interval;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(includeTxPower)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(interval);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PeriodicAdvertisingParameters createFromParcel(Parcel parcel)
        {
            return new PeriodicAdvertisingParameters(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PeriodicAdvertisingParameters[] newArray(int i)
        {
            return new PeriodicAdvertisingParameters[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int INTERVAL_MAX = 65519;
    private static final int INTERVAL_MIN = 80;
    private final boolean includeTxPower;
    private final int interval;

}
