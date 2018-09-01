// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

public final class IpSecAlgorithm
    implements Parcelable
{

    private IpSecAlgorithm(Parcel parcel)
    {
        mName = parcel.readString();
        mKey = parcel.createByteArray();
        mTruncLenBits = parcel.readInt();
    }

    IpSecAlgorithm(Parcel parcel, IpSecAlgorithm ipsecalgorithm)
    {
        this(parcel);
    }

    public IpSecAlgorithm(String s, byte abyte0[])
    {
        this(s, abyte0, abyte0.length * 8);
    }

    public IpSecAlgorithm(String s, byte abyte0[], int i)
    {
        if(!isTruncationLengthValid(s, i))
        {
            throw new IllegalArgumentException("Unknown algorithm or invalid length");
        } else
        {
            mName = s;
            mKey = (byte[])abyte0.clone();
            mTruncLenBits = Math.min(i, abyte0.length * 8);
            return;
        }
    }

    private static boolean isTruncationLengthValid(String s, int i)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        flag = true;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        if(!s.equals("cbc(aes)")) goto _L2; else goto _L1
_L1:
        boolean flag6 = flag;
        if(i == 128) goto _L4; else goto _L3
_L3:
        if(i != 192) goto _L6; else goto _L5
_L5:
        flag6 = flag;
_L4:
        return flag6;
_L2:
        if(s.equals("hmac(md5)"))
        {
            flag6 = flag5;
            if(i >= 96)
            {
                flag6 = flag5;
                if(i <= 128)
                    flag6 = true;
            }
            return flag6;
        }
        if(s.equals("hmac(sha1)"))
        {
            flag6 = flag1;
            if(i >= 96)
            {
                flag6 = flag1;
                if(i <= 160)
                    flag6 = true;
            }
            return flag6;
        }
        if(s.equals("hmac(sha256)"))
        {
            flag6 = flag2;
            if(i >= 96)
            {
                flag6 = flag2;
                if(i <= 256)
                    flag6 = true;
            }
            return flag6;
        }
        if(s.equals("hmac(sha384)"))
        {
            flag6 = flag3;
            if(i >= 192)
            {
                flag6 = flag3;
                if(i <= 384)
                    flag6 = true;
            }
            return flag6;
        }
        if(s.equals("hmac(sha512)"))
        {
            flag6 = flag4;
            if(i >= 256)
            {
                flag6 = flag4;
                if(i <= 512)
                    flag6 = true;
            }
            return flag6;
        } else
        {
            return false;
        }
_L6:
        flag6 = flag;
        if(i != 256)
            flag6 = false;
        if(true) goto _L4; else goto _L7
_L7:
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getKey()
    {
        return (byte[])mKey.clone();
    }

    public String getName()
    {
        return mName;
    }

    public int getTruncationLengthBits()
    {
        return mTruncLenBits;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mName);
        parcel.writeByteArray(mKey);
        parcel.writeInt(mTruncLenBits);
    }

    public static final String AUTH_HMAC_MD5 = "hmac(md5)";
    public static final String AUTH_HMAC_SHA1 = "hmac(sha1)";
    public static final String AUTH_HMAC_SHA256 = "hmac(sha256)";
    public static final String AUTH_HMAC_SHA384 = "hmac(sha384)";
    public static final String AUTH_HMAC_SHA512 = "hmac(sha512)";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpSecAlgorithm createFromParcel(Parcel parcel)
        {
            return new IpSecAlgorithm(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpSecAlgorithm[] newArray(int i)
        {
            return new IpSecAlgorithm[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String CRYPT_AES_CBC = "cbc(aes)";
    private final byte mKey[];
    private final String mName;
    private final int mTruncLenBits;

}
