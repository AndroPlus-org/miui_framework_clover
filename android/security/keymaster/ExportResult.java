// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;
import android.os.Parcelable;

public class ExportResult
    implements Parcelable
{

    protected ExportResult(Parcel parcel)
    {
        resultCode = parcel.readInt();
        exportData = parcel.createByteArray();
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(resultCode);
        parcel.writeByteArray(exportData);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ExportResult createFromParcel(Parcel parcel)
        {
            return new ExportResult(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ExportResult[] newArray(int i)
        {
            return new ExportResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final byte exportData[];
    public final int resultCode;

}
