// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.ArrayUtils;
import java.io.ByteArrayInputStream;
import java.lang.ref.SoftReference;
import java.security.PublicKey;
import java.security.cert.*;
import java.util.Arrays;

public class Signature
    implements Parcelable
{

    private Signature(Parcel parcel)
    {
        mSignature = parcel.createByteArray();
    }

    Signature(Parcel parcel, Signature signature)
    {
        this(parcel);
    }

    public Signature(String s)
    {
        byte abyte0[] = s.getBytes();
        int i = abyte0.length;
        if(i % 2 != 0)
            throw new IllegalArgumentException((new StringBuilder()).append("text size ").append(i).append(" is not even").toString());
        s = new byte[i / 2];
        int j = 0;
        for(int k = 0; j < i; k++)
        {
            int l = j + 1;
            int i1 = parseHexDigit(abyte0[j]);
            j = l + 1;
            s[k] = (byte)(i1 << 4 | parseHexDigit(abyte0[l]));
        }

        mSignature = s;
    }

    public Signature(byte abyte0[])
    {
        mSignature = (byte[])abyte0.clone();
        mCertificateChain = null;
    }

    public Signature(Certificate acertificate[])
        throws CertificateEncodingException
    {
        mSignature = acertificate[0].getEncoded();
        if(acertificate.length > 1)
            mCertificateChain = (Certificate[])Arrays.copyOfRange(acertificate, 1, acertificate.length);
    }

    public static boolean areEffectiveMatch(Signature asignature[], Signature asignature1[])
        throws CertificateException
    {
        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        Signature asignature2[] = new Signature[asignature.length];
        for(int i = 0; i < asignature.length; i++)
            asignature2[i] = bounce(certificatefactory, asignature[i]);

        asignature = new Signature[asignature1.length];
        for(int j = 0; j < asignature1.length; j++)
            asignature[j] = bounce(certificatefactory, asignature1[j]);

        return areExactMatch(asignature2, asignature);
    }

    public static boolean areExactMatch(Signature asignature[], Signature asignature1[])
    {
        boolean flag;
        if(asignature.length == asignature1.length && ArrayUtils.containsAll(asignature, asignature1))
            flag = ArrayUtils.containsAll(asignature1, asignature);
        else
            flag = false;
        return flag;
    }

    public static Signature bounce(CertificateFactory certificatefactory, Signature signature)
        throws CertificateException
    {
        certificatefactory = new Signature(((X509Certificate)certificatefactory.generateCertificate(new ByteArrayInputStream(signature.mSignature))).getEncoded());
        if(Math.abs(((Signature) (certificatefactory)).mSignature.length - signature.mSignature.length) > 2)
            throw new CertificateException((new StringBuilder()).append("Bounced cert length looks fishy; before ").append(signature.mSignature.length).append(", after ").append(((Signature) (certificatefactory)).mSignature.length).toString());
        else
            return certificatefactory;
    }

    private static final int parseHexDigit(int i)
    {
        if(48 <= i && i <= 57)
            return i - 48;
        if(97 <= i && i <= 102)
            return (i - 97) + 10;
        if(65 <= i && i <= 70)
            return (i - 65) + 10;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid character ").append(i).append(" in hex string").toString());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            break MISSING_BLOCK_LABEL_34;
        obj = (Signature)obj;
        if(this == obj) goto _L2; else goto _L1
_L1:
        boolean flag = Arrays.equals(mSignature, ((Signature) (obj)).mSignature);
_L4:
        return flag;
_L2:
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        obj;
        return false;
    }

    public Signature[] getChainSignatures()
        throws CertificateEncodingException
    {
        int i = 0;
        if(mCertificateChain == null)
            return (new Signature[] {
                this
            });
        Signature asignature[] = new Signature[mCertificateChain.length + 1];
        asignature[0] = this;
        Certificate acertificate[] = mCertificateChain;
        int j = acertificate.length;
        for(int k = 1; i < j; k++)
        {
            asignature[k] = new Signature(acertificate[i].getEncoded());
            i++;
        }

        return asignature;
    }

    public PublicKey getPublicKey()
        throws CertificateException
    {
        return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(mSignature)).getPublicKey();
    }

    public int hashCode()
    {
        if(mHaveHashCode)
        {
            return mHashCode;
        } else
        {
            mHashCode = Arrays.hashCode(mSignature);
            mHaveHashCode = true;
            return mHashCode;
        }
    }

    public byte[] toByteArray()
    {
        byte abyte0[] = new byte[mSignature.length];
        System.arraycopy(mSignature, 0, abyte0, 0, mSignature.length);
        return abyte0;
    }

    public char[] toChars()
    {
        return toChars(null, null);
    }

    public char[] toChars(char ac[], int ai[])
    {
        byte abyte0[] = mSignature;
        int i = abyte0.length;
        int j = i * 2;
        if(ac == null || j > ac.length)
            ac = new char[j];
        j = 0;
        while(j < i) 
        {
            byte byte0 = abyte0[j];
            int k = byte0 >> 4 & 0xf;
            if(k >= 10)
                k = (k + 97) - 10;
            else
                k += 48;
            ac[j * 2] = (char)k;
            k = byte0 & 0xf;
            if(k >= 10)
                k = (k + 97) - 10;
            else
                k += 48;
            ac[j * 2 + 1] = (char)k;
            j++;
        }
        if(ai != null)
            ai[0] = i;
        return ac;
    }

    public String toCharsString()
    {
        String s;
        if(mStringRef == null)
            s = null;
        else
            s = (String)mStringRef.get();
        if(s != null)
        {
            return s;
        } else
        {
            String s1 = new String(toChars());
            mStringRef = new SoftReference(s1);
            return s1;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(mSignature);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Signature createFromParcel(Parcel parcel)
        {
            return new Signature(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Signature[] newArray(int i)
        {
            return new Signature[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private Certificate mCertificateChain[];
    private int mHashCode;
    private boolean mHaveHashCode;
    private final byte mSignature[];
    private SoftReference mStringRef;

}
