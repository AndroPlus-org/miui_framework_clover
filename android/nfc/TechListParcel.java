// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.os.Parcel;
import android.os.Parcelable;

public class TechListParcel
    implements Parcelable
{

    public transient TechListParcel(String as[][])
    {
        mTechLists = as;
    }

    public int describeContents()
    {
        return 0;
    }

    public String[][] getTechLists()
    {
        return mTechLists;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = mTechLists.length;
        parcel.writeInt(j);
        for(i = 0; i < j; i++)
            parcel.writeStringArray(mTechLists[i]);

    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TechListParcel createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            String as[][] = new String[i][];
            for(int j = 0; j < i; j++)
                as[j] = parcel.readStringArray();

            return new TechListParcel(as);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TechListParcel[] newArray(int i)
        {
            return new TechListParcel[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String mTechLists[][];

}
