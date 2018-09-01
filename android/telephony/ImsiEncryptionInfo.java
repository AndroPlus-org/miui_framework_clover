// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public final class ImsiEncryptionInfo
    implements Parcelable
{

    public ImsiEncryptionInfo(Parcel parcel)
    {
        byte abyte0[] = new byte[parcel.readInt()];
        parcel.readByteArray(abyte0);
        publicKey = makeKeyObject(abyte0);
        mcc = parcel.readString();
        mnc = parcel.readString();
        keyIdentifier = parcel.readString();
        keyType = parcel.readInt();
        expirationTime = new Date(parcel.readLong());
    }

    public ImsiEncryptionInfo(String s, String s1, int i, String s2, PublicKey publickey, Date date)
    {
        mcc = s;
        mnc = s1;
        keyType = i;
        publicKey = publickey;
        keyIdentifier = s2;
        expirationTime = date;
    }

    public ImsiEncryptionInfo(String s, String s1, int i, String s2, byte abyte0[], Date date)
    {
        this(s, s1, i, s2, makeKeyObject(abyte0), date);
    }

    private static PublicKey makeKeyObject(byte abyte0[])
    {
        try
        {
            X509EncodedKeySpec x509encodedkeyspec = JVM INSTR new #83  <Class X509EncodedKeySpec>;
            x509encodedkeyspec.X509EncodedKeySpec(abyte0);
            abyte0 = KeyFactory.getInstance("RSA").generatePublic(x509encodedkeyspec);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.e("ImsiEncryptionInfo", "Error makeKeyObject: unable to convert into PublicKey", abyte0);
            throw new IllegalArgumentException();
        }
        return abyte0;
    }

    public int describeContents()
    {
        return 0;
    }

    public Date getExpirationTime()
    {
        return expirationTime;
    }

    public String getKeyIdentifier()
    {
        return keyIdentifier;
    }

    public int getKeyType()
    {
        return keyType;
    }

    public String getMcc()
    {
        return mcc;
    }

    public String getMnc()
    {
        return mnc;
    }

    public PublicKey getPublicKey()
    {
        return publicKey;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[ImsiEncryptionInfo mcc=").append(mcc).append("mnc=").append(mnc).append("publicKey=").append(publicKey).append(", keyIdentifier=").append(keyIdentifier).append(", keyType=").append(keyType).append(", expirationTime=").append(expirationTime).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        byte abyte0[] = publicKey.getEncoded();
        parcel.writeInt(abyte0.length);
        parcel.writeByteArray(abyte0);
        parcel.writeString(mcc);
        parcel.writeString(mnc);
        parcel.writeString(keyIdentifier);
        parcel.writeInt(keyType);
        parcel.writeLong(expirationTime.getTime());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsiEncryptionInfo createFromParcel(Parcel parcel)
        {
            return new ImsiEncryptionInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsiEncryptionInfo[] newArray(int i)
        {
            return new ImsiEncryptionInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String LOG_TAG = "ImsiEncryptionInfo";
    private final Date expirationTime;
    private final String keyIdentifier;
    private final int keyType;
    private final String mcc;
    private final String mnc;
    private final PublicKey publicKey;

}
