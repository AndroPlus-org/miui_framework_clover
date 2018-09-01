// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcelable, Parcel

public class Temperature
    implements Parcelable
{

    public Temperature()
    {
        mType = 0x80000000;
        mValue = -3.402823E+038F;
    }

    public Temperature(float f, int i)
    {
        mValue = f;
        mType = i;
    }

    private Temperature(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    Temperature(Parcel parcel, Temperature temperature)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getType()
    {
        return mType;
    }

    public float getValue()
    {
        return mValue;
    }

    public void readFromParcel(Parcel parcel)
    {
        mValue = parcel.readFloat();
        mType = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeFloat(mValue);
        parcel.writeInt(mType);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Temperature createFromParcel(Parcel parcel)
        {
            return new Temperature(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Temperature[] newArray(int i)
        {
            return new Temperature[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mType;
    private float mValue;

}
