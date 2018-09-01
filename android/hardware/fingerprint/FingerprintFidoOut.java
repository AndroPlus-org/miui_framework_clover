// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.fingerprint;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class FingerprintFidoOut
    implements Parcelable
{

    public FingerprintFidoOut()
    {
    }

    public FingerprintFidoOut(Parcel parcel)
    {
        byte abyte0[] = parcel.createByteArray();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        uvt = abyte0;
        fingerId = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getFingerId()
    {
        return fingerId;
    }

    public byte[] getUvt()
    {
        return uvt;
    }

    public void setFingerId(int i)
    {
        fingerId = i;
    }

    public void setUvt(byte abyte0[])
    {
        uvt = abyte0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("FingerprintFidoOut [uvt=").append(Arrays.toString(uvt)).append(", fingerId=").append(fingerId).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(uvt);
        parcel.writeInt(fingerId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FingerprintFidoOut createFromParcel(Parcel parcel)
        {
            return new FingerprintFidoOut(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FingerprintFidoOut[] newArray(int i)
        {
            return new FingerprintFidoOut[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int fingerId;
    private byte uvt[];

}
