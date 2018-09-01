// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.telephony.uicc.IccUtils;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

// Referenced classes of package android.telephony:
//            Rlog

public final class UiccAccessRule
    implements Parcelable
{

    UiccAccessRule(Parcel parcel)
    {
        mCertificateHash = parcel.createByteArray();
        mPackageName = parcel.readString();
        mAccessType = parcel.readLong();
    }

    public UiccAccessRule(byte abyte0[], String s, long l)
    {
        mCertificateHash = abyte0;
        mPackageName = s;
        mAccessType = l;
    }

    public static UiccAccessRule[] decodeRules(byte abyte0[])
    {
        Object obj;
        Object obj2;
        Object obj3;
        byte abyte1[];
        UiccAccessRule auiccaccessrule[];
        if(abyte0 == null)
            return null;
        obj = new ByteArrayInputStream(abyte0);
        obj2 = null;
        obj3 = null;
        abyte1 = null;
        auiccaccessrule = null;
        abyte0 = JVM INSTR new #67  <Class DataInputStream>;
        abyte0.DataInputStream(((java.io.InputStream) (obj)));
        int i;
        abyte0.readInt();
        i = abyte0.readInt();
        auiccaccessrule = new UiccAccessRule[i];
        int j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_118;
        abyte1 = new byte[abyte0.readInt()];
        abyte0.readFully(abyte1);
        if(!abyte0.readBoolean())
            break MISSING_BLOCK_LABEL_113;
        obj = abyte0.readUTF();
_L2:
        auiccaccessrule[j] = new UiccAccessRule(abyte1, ((String) (obj)), abyte0.readLong());
        j++;
          goto _L1
        obj = null;
          goto _L2
        abyte0.close();
        obj = obj3;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_134;
        abyte0.close();
        obj = obj3;
_L5:
        if(obj == null) goto _L4; else goto _L3
_L3:
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
_L6:
        throw new IllegalStateException("ByteArrayInputStream should never lead to an IOException", abyte0);
        obj;
          goto _L5
_L4:
        return auiccaccessrule;
        Object obj1;
        obj1;
        abyte0 = auiccaccessrule;
_L10:
        throw obj1;
        Exception exception;
        exception;
        obj2 = obj1;
        obj1 = exception;
_L9:
        exception = ((Exception) (obj2));
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_182;
        abyte0.close();
        exception = ((Exception) (obj2));
_L7:
        if(exception == null)
            break MISSING_BLOCK_LABEL_219;
        try
        {
            throw exception;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
          goto _L6
        abyte0;
label0:
        {
            if(obj2 != null)
                break label0;
            exception = abyte0;
        }
          goto _L7
        exception = ((Exception) (obj2));
        if(obj2 == abyte0) goto _L7; else goto _L8
_L8:
        ((Throwable) (obj2)).addSuppressed(abyte0);
        exception = ((Exception) (obj2));
          goto _L7
        throw obj1;
        obj1;
        abyte0 = abyte1;
          goto _L9
        obj1;
          goto _L9
        obj1;
          goto _L10
    }

    public static byte[] encodeRules(UiccAccessRule auiccaccessrule[])
    {
        ByteArrayOutputStream bytearrayoutputstream;
        DataOutputStream dataoutputstream;
        int i = 0;
        if(auiccaccessrule == null)
            return null;
        int j;
        UiccAccessRule uiccaccessrule;
        try
        {
            bytearrayoutputstream = JVM INSTR new #105 <Class ByteArrayOutputStream>;
            bytearrayoutputstream.ByteArrayOutputStream();
            dataoutputstream = JVM INSTR new #108 <Class DataOutputStream>;
            dataoutputstream.DataOutputStream(bytearrayoutputstream);
            dataoutputstream.writeInt(1);
            dataoutputstream.writeInt(auiccaccessrule.length);
            j = auiccaccessrule.length;
        }
        // Misplaced declaration of an exception variable
        catch(UiccAccessRule auiccaccessrule[])
        {
            throw new IllegalStateException("ByteArrayOutputStream should never lead to an IOException", auiccaccessrule);
        }
_L3:
        if(i >= j)
            break MISSING_BLOCK_LABEL_127;
        uiccaccessrule = auiccaccessrule[i];
        dataoutputstream.writeInt(uiccaccessrule.mCertificateHash.length);
        dataoutputstream.write(uiccaccessrule.mCertificateHash);
        if(uiccaccessrule.mPackageName == null)
            break MISSING_BLOCK_LABEL_107;
        dataoutputstream.writeBoolean(true);
        dataoutputstream.writeUTF(uiccaccessrule.mPackageName);
_L1:
        dataoutputstream.writeLong(uiccaccessrule.mAccessType);
        i++;
        continue; /* Loop/switch isn't completed */
        dataoutputstream.writeBoolean(false);
          goto _L1
        dataoutputstream.close();
        auiccaccessrule = bytearrayoutputstream.toByteArray();
        return auiccaccessrule;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private static byte[] getCertHash(Signature signature, String s)
    {
        try
        {
            signature = MessageDigest.getInstance(s).digest(signature.toByteArray());
        }
        // Misplaced declaration of an exception variable
        catch(Signature signature)
        {
            Rlog.e("UiccAccessRule", (new StringBuilder()).append("NoSuchAlgorithmException: ").append(signature).toString());
            return null;
        }
        return signature;
    }

    private boolean matches(byte abyte0[], String s)
    {
        boolean flag;
        if(abyte0 != null && Arrays.equals(mCertificateHash, abyte0))
        {
            if(!TextUtils.isEmpty(mPackageName))
                flag = mPackageName.equals(s);
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCarrierPrivilegeStatus(PackageInfo packageinfo)
    {
        if(packageinfo.signatures == null || packageinfo.signatures.length == 0)
            throw new IllegalArgumentException("Must use GET_SIGNATURES when looking up package info");
        Signature asignature[] = packageinfo.signatures;
        int i = asignature.length;
        for(int j = 0; j < i; j++)
        {
            int k = getCarrierPrivilegeStatus(asignature[j], packageinfo.packageName);
            if(k != 0)
                return k;
        }

        return 0;
    }

    public int getCarrierPrivilegeStatus(Signature signature, String s)
    {
        byte abyte0[] = getCertHash(signature, "SHA-1");
        signature = getCertHash(signature, "SHA-256");
        return !matches(abyte0, s) && !matches(signature, s) ? 0 : 1;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public String toString()
    {
        return (new StringBuilder()).append("cert: ").append(IccUtils.bytesToHexString(mCertificateHash)).append(" pkg: ").append(mPackageName).append(" access: ").append(mAccessType).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(mCertificateHash);
        parcel.writeString(mPackageName);
        parcel.writeLong(mAccessType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UiccAccessRule createFromParcel(Parcel parcel)
        {
            return new UiccAccessRule(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UiccAccessRule[] newArray(int i)
        {
            return new UiccAccessRule[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int ENCODING_VERSION = 1;
    private static final String TAG = "UiccAccessRule";
    private final long mAccessType;
    private final byte mCertificateHash[];
    private final String mPackageName;

}
