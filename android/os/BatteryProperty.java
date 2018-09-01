// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcelable, Parcel

public class BatteryProperty
    implements Parcelable
{

    public BatteryProperty()
    {
        mValueLong = 0x8000000000000000L;
    }

    private BatteryProperty(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    BatteryProperty(Parcel parcel, BatteryProperty batteryproperty)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getLong()
    {
        return mValueLong;
    }

    public void readFromParcel(Parcel parcel)
    {
        mValueLong = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mValueLong);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public BatteryProperty createFromParcel(Parcel parcel)
        {
            return new BatteryProperty(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BatteryProperty[] newArray(int i)
        {
            return new BatteryProperty[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mValueLong;

}
