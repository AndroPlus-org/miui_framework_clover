// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class GeocoderParams
    implements Parcelable
{

    static Locale _2D_set0(GeocoderParams geocoderparams, Locale locale)
    {
        geocoderparams.mLocale = locale;
        return locale;
    }

    static String _2D_set1(GeocoderParams geocoderparams, String s)
    {
        geocoderparams.mPackageName = s;
        return s;
    }

    private GeocoderParams()
    {
    }

    public GeocoderParams(Context context, Locale locale)
    {
        mLocale = locale;
        mPackageName = context.getPackageName();
    }

    GeocoderParams(GeocoderParams geocoderparams)
    {
        this();
    }

    public int describeContents()
    {
        return 0;
    }

    public String getClientPackage()
    {
        return mPackageName;
    }

    public Locale getLocale()
    {
        return mLocale;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mLocale.getLanguage());
        parcel.writeString(mLocale.getCountry());
        parcel.writeString(mLocale.getVariant());
        parcel.writeString(mPackageName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GeocoderParams createFromParcel(Parcel parcel)
        {
            GeocoderParams geocoderparams = new GeocoderParams(null);
            GeocoderParams._2D_set0(geocoderparams, new Locale(parcel.readString(), parcel.readString(), parcel.readString()));
            GeocoderParams._2D_set1(geocoderparams, parcel.readString());
            return geocoderparams;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GeocoderParams[] newArray(int i)
        {
            return new GeocoderParams[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private Locale mLocale;
    private String mPackageName;

}
