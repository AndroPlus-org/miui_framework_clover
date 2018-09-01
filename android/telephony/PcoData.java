// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

public class PcoData
    implements Parcelable
{

    public PcoData(int i, String s, int j, byte abyte0[])
    {
        cid = i;
        bearerProto = s;
        pcoId = j;
        contents = abyte0;
    }

    public PcoData(Parcel parcel)
    {
        cid = parcel.readInt();
        bearerProto = parcel.readString();
        pcoId = parcel.readInt();
        contents = parcel.createByteArray();
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("PcoData(").append(cid).append(", ").append(bearerProto).append(", ").append(pcoId).append(", contents[").append(contents.length).append("])").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(cid);
        parcel.writeString(bearerProto);
        parcel.writeInt(pcoId);
        parcel.writeByteArray(contents);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PcoData createFromParcel(Parcel parcel)
        {
            return new PcoData(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PcoData[] newArray(int i)
        {
            return new PcoData[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final String bearerProto;
    public final int cid;
    public final byte contents[];
    public final int pcoId;

}
