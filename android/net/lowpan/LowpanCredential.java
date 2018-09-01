// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.HexDump;
import java.util.Arrays;
import java.util.Objects;

public class LowpanCredential
    implements Parcelable
{

    static byte[] _2D_set0(LowpanCredential lowpancredential, byte abyte0[])
    {
        lowpancredential.mMasterKey = abyte0;
        return abyte0;
    }

    static int _2D_set1(LowpanCredential lowpancredential, int i)
    {
        lowpancredential.mMasterKeyIndex = i;
        return i;
    }

    LowpanCredential()
    {
        mMasterKey = null;
        mMasterKeyIndex = 0;
    }

    private LowpanCredential(byte abyte0[])
    {
        mMasterKey = null;
        mMasterKeyIndex = 0;
        setMasterKey(abyte0);
    }

    private LowpanCredential(byte abyte0[], int i)
    {
        mMasterKey = null;
        mMasterKeyIndex = 0;
        setMasterKey(abyte0, i);
    }

    public static LowpanCredential createMasterKey(byte abyte0[])
    {
        return new LowpanCredential(abyte0);
    }

    public static LowpanCredential createMasterKey(byte abyte0[], int i)
    {
        return new LowpanCredential(abyte0, i);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof LowpanCredential))
            return false;
        obj = (LowpanCredential)obj;
        boolean flag1 = flag;
        if(Arrays.equals(mMasterKey, ((LowpanCredential) (obj)).mMasterKey))
        {
            flag1 = flag;
            if(mMasterKeyIndex == ((LowpanCredential) (obj)).mMasterKeyIndex)
                flag1 = true;
        }
        return flag1;
    }

    public byte[] getMasterKey()
    {
        if(mMasterKey != null)
            return (byte[])mMasterKey.clone();
        else
            return null;
    }

    public int getMasterKeyIndex()
    {
        return mMasterKeyIndex;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(Arrays.hashCode(mMasterKey)), Integer.valueOf(mMasterKeyIndex)
        });
    }

    public boolean isMasterKey()
    {
        boolean flag;
        if(mMasterKey != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void setMasterKey(byte abyte0[])
    {
        byte abyte1[] = abyte0;
        if(abyte0 != null)
            abyte1 = (byte[])abyte0.clone();
        mMasterKey = abyte1;
    }

    void setMasterKey(byte abyte0[], int i)
    {
        setMasterKey(abyte0);
        setMasterKeyIndex(i);
    }

    void setMasterKeyIndex(int i)
    {
        mMasterKeyIndex = i;
    }

    public String toSensitiveString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<LowpanCredential");
        if(isMasterKey())
        {
            stringbuffer.append(" MasterKey:").append(HexDump.toHexString(mMasterKey));
            if(mMasterKeyIndex != 0)
                stringbuffer.append(", Index:").append(mMasterKeyIndex);
        } else
        {
            stringbuffer.append(" empty");
        }
        stringbuffer.append(">");
        return stringbuffer.toString();
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<LowpanCredential");
        if(isMasterKey())
        {
            stringbuffer.append(" MasterKey");
            if(mMasterKeyIndex != 0)
                stringbuffer.append(", Index:").append(mMasterKeyIndex);
        } else
        {
            stringbuffer.append(" empty");
        }
        stringbuffer.append(">");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(mMasterKey);
        parcel.writeInt(mMasterKeyIndex);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LowpanCredential createFromParcel(Parcel parcel)
        {
            LowpanCredential lowpancredential = new LowpanCredential();
            LowpanCredential._2D_set0(lowpancredential, parcel.createByteArray());
            LowpanCredential._2D_set1(lowpancredential, parcel.readInt());
            return lowpancredential;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LowpanCredential[] newArray(int i)
        {
            return new LowpanCredential[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UNSPECIFIED_KEY_INDEX = 0;
    private byte mMasterKey[];
    private int mMasterKeyIndex;

}
