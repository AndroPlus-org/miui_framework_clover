// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.euicc;

import android.os.Parcel;
import android.os.Parcelable;

public final class EuiccInfo
    implements Parcelable
{

    private EuiccInfo(Parcel parcel)
    {
        osVersion = parcel.readString();
    }

    EuiccInfo(Parcel parcel, EuiccInfo euiccinfo)
    {
        this(parcel);
    }

    public EuiccInfo(String s)
    {
        osVersion = s;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(osVersion);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public EuiccInfo createFromParcel(Parcel parcel)
        {
            return new EuiccInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public EuiccInfo[] newArray(int i)
        {
            return new EuiccInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final String osVersion;

}
