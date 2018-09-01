// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.fingerprint;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class FingerprintFidoIn
    implements Parcelable
{

    public FingerprintFidoIn()
    {
    }

    public FingerprintFidoIn(Parcel parcel)
    {
        AAID = parcel.readString();
        byte abyte0[] = parcel.createByteArray();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        nonce = abyte0;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getAAID()
    {
        return AAID;
    }

    public byte[] getNonce()
    {
        return nonce;
    }

    public void setAAID(String s)
    {
        AAID = s;
    }

    public void setNonce(byte abyte0[])
    {
        nonce = abyte0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("FingerprintFidoIn [AAID=").append(AAID).append(", nonce=").append(Arrays.toString(nonce)).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(AAID);
        parcel.writeByteArray(nonce);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FingerprintFidoIn createFromParcel(Parcel parcel)
        {
            return new FingerprintFidoIn(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FingerprintFidoIn[] newArray(int i)
        {
            return new FingerprintFidoIn[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public String AAID;
    private byte nonce[];

}
