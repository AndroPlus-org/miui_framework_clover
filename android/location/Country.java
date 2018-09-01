// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import java.util.Locale;

public class Country
    implements Parcelable
{

    public Country(Country country)
    {
        mCountryIso = country.mCountryIso;
        mSource = country.mSource;
        mTimestamp = country.mTimestamp;
    }

    public Country(String s, int i)
    {
        while(s == null || i < 0 || i > 3) 
            throw new IllegalArgumentException();
        mCountryIso = s.toUpperCase(Locale.US);
        mSource = i;
        mTimestamp = SystemClock.elapsedRealtime();
    }

    private Country(String s, int i, long l)
    {
        while(s == null || i < 0 || i > 3) 
            throw new IllegalArgumentException();
        mCountryIso = s.toUpperCase(Locale.US);
        mSource = i;
        mTimestamp = l;
    }

    Country(String s, int i, long l, Country country)
    {
        this(s, i, l);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == this)
            return true;
        if(obj instanceof Country)
        {
            obj = (Country)obj;
            if(!mCountryIso.equals(((Country) (obj)).getCountryIso()) || mSource != ((Country) (obj)).getSource())
                flag = false;
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean equalsIgnoreSource(Country country)
    {
        boolean flag;
        if(country != null)
            flag = mCountryIso.equals(country.getCountryIso());
        else
            flag = false;
        return flag;
    }

    public final String getCountryIso()
    {
        return mCountryIso;
    }

    public final int getSource()
    {
        return mSource;
    }

    public final long getTimestamp()
    {
        return mTimestamp;
    }

    public int hashCode()
    {
        if(mHashCode == 0)
            mHashCode = (mCountryIso.hashCode() + 221) * 13 + mSource;
        return mHashCode;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Country {ISO=").append(mCountryIso).append(", source=").append(mSource).append(", time=").append(mTimestamp).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mCountryIso);
        parcel.writeInt(mSource);
        parcel.writeLong(mTimestamp);
    }

    public static final int COUNTRY_SOURCE_LOCALE = 3;
    public static final int COUNTRY_SOURCE_LOCATION = 1;
    public static final int COUNTRY_SOURCE_NETWORK = 0;
    public static final int COUNTRY_SOURCE_SIM = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Country createFromParcel(Parcel parcel)
        {
            return new Country(parcel.readString(), parcel.readInt(), parcel.readLong(), null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Country[] newArray(int i)
        {
            return new Country[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mCountryIso;
    private int mHashCode;
    private final int mSource;
    private final long mTimestamp;

}
