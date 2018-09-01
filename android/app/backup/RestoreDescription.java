// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.Parcel;
import android.os.Parcelable;

public class RestoreDescription
    implements Parcelable
{

    static String _2D_get0(RestoreDescription restoredescription)
    {
        return restoredescription.mPackageName;
    }

    private RestoreDescription(Parcel parcel)
    {
        mPackageName = parcel.readString();
        mDataType = parcel.readInt();
    }

    RestoreDescription(Parcel parcel, RestoreDescription restoredescription)
    {
        this(parcel);
    }

    public RestoreDescription(String s, int i)
    {
        mPackageName = s;
        mDataType = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getDataType()
    {
        return mDataType;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("RestoreDescription{").append(mPackageName).append(" : ");
        String s;
        if(mDataType == 1)
            s = "KEY_VALUE";
        else
            s = "STREAM";
        return stringbuilder.append(s).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackageName);
        parcel.writeInt(mDataType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RestoreDescription createFromParcel(Parcel parcel)
        {
            RestoreDescription restoredescription = new RestoreDescription(parcel, null);
            parcel = restoredescription;
            if("NO_MORE_PACKAGES".equals(RestoreDescription._2D_get0(restoredescription)))
                parcel = RestoreDescription.NO_MORE_PACKAGES;
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RestoreDescription[] newArray(int i)
        {
            return new RestoreDescription[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final RestoreDescription NO_MORE_PACKAGES = new RestoreDescription("NO_MORE_PACKAGES", 0);
    private static final String NO_MORE_PACKAGES_SENTINEL = "NO_MORE_PACKAGES";
    public static final int TYPE_FULL_STREAM = 2;
    public static final int TYPE_KEY_VALUE = 1;
    private final int mDataType;
    private final String mPackageName;

}
