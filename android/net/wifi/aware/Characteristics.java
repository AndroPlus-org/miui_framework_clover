// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.os.*;

public final class Characteristics
    implements Parcelable
{

    public Characteristics(Bundle bundle)
    {
        mCharacteristics = new Bundle();
        mCharacteristics = bundle;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getMaxMatchFilterLength()
    {
        return mCharacteristics.getInt("key_max_match_filter_length");
    }

    public int getMaxServiceNameLength()
    {
        return mCharacteristics.getInt("key_max_service_name_length");
    }

    public int getMaxServiceSpecificInfoLength()
    {
        return mCharacteristics.getInt("key_max_service_specific_info_length");
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeBundle(mCharacteristics);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Characteristics createFromParcel(Parcel parcel)
        {
            return new Characteristics(parcel.readBundle());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Characteristics[] newArray(int i)
        {
            return new Characteristics[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String KEY_MAX_MATCH_FILTER_LENGTH = "key_max_match_filter_length";
    public static final String KEY_MAX_SERVICE_NAME_LENGTH = "key_max_service_name_length";
    public static final String KEY_MAX_SERVICE_SPECIFIC_INFO_LENGTH = "key_max_service_specific_info_length";
    private Bundle mCharacteristics;

}
