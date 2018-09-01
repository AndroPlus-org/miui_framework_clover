// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

public class VerifierDeviceIdentity
    implements Parcelable
{

    public VerifierDeviceIdentity(long l)
    {
        mIdentity = l;
        mIdentityString = encodeBase32(l);
    }

    private VerifierDeviceIdentity(Parcel parcel)
    {
        long l = parcel.readLong();
        mIdentity = l;
        mIdentityString = encodeBase32(l);
    }

    VerifierDeviceIdentity(Parcel parcel, VerifierDeviceIdentity verifierdeviceidentity)
    {
        this(parcel);
    }

    private static final long decodeBase32(byte abyte0[])
        throws IllegalArgumentException
    {
        long l;
        int i;
        int j;
        int k;
        l = 0L;
        i = 0;
        j = abyte0.length;
        k = 0;
_L5:
        int i1;
        int j1;
        if(k >= j)
            break MISSING_BLOCK_LABEL_212;
        i1 = abyte0[k];
        if(65 <= i1 && i1 <= 90)
        {
            i1 -= 65;
        } else
        {
label0:
            {
                if(50 > i1 || i1 > 55)
                    break label0;
                i1 -= 24;
            }
        }
_L6:
        l = l << 5 | (long)i1;
        j1 = i + 1;
        if(j1 != 1) goto _L2; else goto _L1
_L1:
        i = j1;
        if((i1 & 0xf) != i1)
            throw new IllegalArgumentException("illegal start character; will overflow");
          goto _L3
        if(i1 != 45) goto _L4; else goto _L3
_L3:
        k++;
          goto _L5
_L4:
        if(97 <= i1 && i1 <= 122)
            i1 -= 97;
        else
        if(i1 == 48)
            i1 = 14;
        else
        if(i1 == 49)
            i1 = 8;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("base base-32 character: ").append(i1).toString());
          goto _L6
_L2:
        i = j1;
        if(j1 <= 13) goto _L3; else goto _L7
_L7:
        throw new IllegalArgumentException("too long; should have 13 characters");
        if(i != 13)
            throw new IllegalArgumentException("too short; should have 13 characters");
        return l;
          goto _L6
    }

    private static final String encodeBase32(long l)
    {
        char ac[] = ENCODE;
        char ac1[] = new char[16];
        int i = ac1.length;
        for(int j = 0; j < 13; j++)
        {
            int k = i;
            if(j > 0)
            {
                k = i;
                if(j % 4 == 1)
                {
                    k = i - 1;
                    ac1[k] = (char)45;
                }
            }
            int i1 = (int)(31L & l);
            l >>>= 5;
            i = k - 1;
            ac1[i] = ac[i1];
        }

        return String.valueOf(ac1);
    }

    public static VerifierDeviceIdentity generate()
    {
        return generate(((Random) (new SecureRandom())));
    }

    static VerifierDeviceIdentity generate(Random random)
    {
        return new VerifierDeviceIdentity(random.nextLong());
    }

    public static VerifierDeviceIdentity parse(String s)
        throws IllegalArgumentException
    {
        try
        {
            s = s.getBytes("US-ASCII");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException("bad base-32 characters in input");
        }
        return new VerifierDeviceIdentity(decodeBase32(s));
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof VerifierDeviceIdentity))
            return false;
        obj = (VerifierDeviceIdentity)obj;
        if(mIdentity == ((VerifierDeviceIdentity) (obj)).mIdentity)
            flag = true;
        return flag;
    }

    public int hashCode()
    {
        return (int)mIdentity;
    }

    public String toString()
    {
        return mIdentityString;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mIdentity);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VerifierDeviceIdentity createFromParcel(Parcel parcel)
        {
            return new VerifierDeviceIdentity(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VerifierDeviceIdentity[] newArray(int i)
        {
            return new VerifierDeviceIdentity[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final char ENCODE[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
        'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', 
        '6', '7'
    };
    private static final int GROUP_SIZE = 4;
    private static final int LONG_SIZE = 13;
    private static final char SEPARATOR = 45;
    private final long mIdentity;
    private final String mIdentityString;

}
