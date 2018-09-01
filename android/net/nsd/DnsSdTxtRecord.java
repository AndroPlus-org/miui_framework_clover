// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.nsd;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class DnsSdTxtRecord
    implements Parcelable
{

    static byte[] _2D_get0(DnsSdTxtRecord dnssdtxtrecord)
    {
        return dnssdtxtrecord.mData;
    }

    public DnsSdTxtRecord()
    {
        mData = new byte[0];
    }

    public DnsSdTxtRecord(DnsSdTxtRecord dnssdtxtrecord)
    {
        if(dnssdtxtrecord != null && dnssdtxtrecord.mData != null)
            mData = (byte[])dnssdtxtrecord.mData.clone();
    }

    public DnsSdTxtRecord(byte abyte0[])
    {
        mData = (byte[])abyte0.clone();
    }

    private String getKey(int i)
    {
        int j = 0;
        for(int k = 0; k < i && j < mData.length; k++)
            j += mData[j] + 1;

        if(j < mData.length)
        {
            byte byte0 = mData[j];
            i = 0;
            do
            {
                if(i >= byte0 || mData[j + i + 1] == 61)
                    return new String(mData, j + 1, i);
                i++;
            } while(true);
        } else
        {
            return null;
        }
    }

    private byte[] getValue(int i)
    {
        int j;
        Object obj;
        byte abyte0[];
        j = 0;
        obj = null;
        for(int k = 0; k < i && j < mData.length; k++)
            j += mData[j] + 1;

        abyte0 = obj;
        if(j >= mData.length) goto _L2; else goto _L1
_L1:
        byte byte0;
        byte0 = mData[j];
        i = 0;
_L7:
        abyte0 = obj;
        if(i >= byte0) goto _L2; else goto _L3
_L3:
        if(mData[j + i + 1] != 61) goto _L5; else goto _L4
_L4:
        abyte0 = new byte[byte0 - i - 1];
        System.arraycopy(mData, j + i + 2, abyte0, 0, byte0 - i - 1);
_L2:
        return abyte0;
_L5:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private byte[] getValue(String s)
    {
        int i = 0;
        do
        {
            String s1 = getKey(i);
            if(s1 != null)
            {
                if(s.compareToIgnoreCase(s1) == 0)
                    return getValue(i);
                i++;
            } else
            {
                return null;
            }
        } while(true);
    }

    private String getValueAsString(int i)
    {
        String s = null;
        byte abyte0[] = getValue(i);
        if(abyte0 != null)
            s = new String(abyte0);
        return s;
    }

    private void insert(byte abyte0[], byte abyte1[], int i)
    {
        byte abyte2[] = mData;
        int j;
        int k;
        if(abyte1 != null)
            j = abyte1.length;
        else
            j = 0;
        k = 0;
        for(int l = 0; l < i && k < mData.length; l++)
            k += mData[k] + 1 & 0xff;

        int i1 = abyte0.length;
        int j1;
        if(abyte1 != null)
            i = 1;
        else
            i = 0;
        j1 = i1 + j + i;
        i = abyte2.length + j1 + 1;
        mData = new byte[i];
        System.arraycopy(abyte2, 0, mData, 0, k);
        i1 = abyte2.length - k;
        System.arraycopy(abyte2, k, mData, i - i1, i1);
        mData[k] = (byte)j1;
        System.arraycopy(abyte0, 0, mData, k + 1, abyte0.length);
        if(abyte1 != null)
        {
            mData[k + 1 + abyte0.length] = (byte)61;
            System.arraycopy(abyte1, 0, mData, abyte0.length + k + 2, j);
        }
    }

    public boolean contains(String s)
    {
        int i = 0;
        do
        {
            String s1 = getKey(i);
            if(s1 != null)
            {
                if(s.compareToIgnoreCase(s1) == 0)
                    return true;
                i++;
            } else
            {
                return false;
            }
        } while(true);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof DnsSdTxtRecord))
            return false;
        else
            return Arrays.equals(((DnsSdTxtRecord)obj).mData, mData);
    }

    public String get(String s)
    {
        Object obj = null;
        byte abyte0[] = getValue(s);
        s = obj;
        if(abyte0 != null)
            s = new String(abyte0);
        return s;
    }

    public byte[] getRawData()
    {
        return (byte[])mData.clone();
    }

    public int hashCode()
    {
        return Arrays.hashCode(mData);
    }

    public int keyCount()
    {
        int i = 0;
        for(int j = 0; j < mData.length;)
        {
            j += mData[j] + 1 & 0xff;
            i++;
        }

        return i;
    }

    public int remove(String s)
    {
        int i = 0;
        for(int j = 0; i < mData.length; j++)
        {
            byte byte0 = mData[i];
            if(s.length() <= byte0 && (s.length() == byte0 || mData[s.length() + i + 1] == 61) && s.compareToIgnoreCase(new String(mData, i + 1, s.length())) == 0)
            {
                s = mData;
                mData = new byte[s.length - byte0 - 1];
                System.arraycopy(s, 0, mData, 0, i);
                System.arraycopy(s, i + byte0 + 1, mData, i, s.length - i - byte0 - 1);
                return j;
            }
            i += byte0 + 1 & 0xff;
        }

        return -1;
    }

    public void set(String s, String s1)
    {
        int i;
        byte abyte0[];
        int j;
        if(s1 != null)
        {
            s1 = s1.getBytes();
            i = s1.length;
        } else
        {
            s1 = null;
            i = 0;
        }
        try
        {
            abyte0 = s.getBytes("US-ASCII");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException("key should be US-ASCII");
        }
        for(j = 0; j < abyte0.length; j++)
            if(abyte0[j] == 61)
                throw new IllegalArgumentException("= is not a valid character in key");

        if(abyte0.length + i >= 255)
            throw new IllegalArgumentException("Key and Value length cannot exceed 255 bytes");
        j = remove(s);
        i = j;
        if(j == -1)
            i = keyCount();
        insert(abyte0, s1, i);
    }

    public int size()
    {
        return mData.length;
    }

    public String toString()
    {
        String s = null;
        int i = 0;
        do
        {
            String s1 = getKey(i);
            if(s1 == null)
                break;
            String s2 = (new StringBuilder()).append("{").append(s1).toString();
            s1 = getValueAsString(i);
            if(s1 != null)
                s1 = (new StringBuilder()).append(s2).append("=").append(s1).append("}").toString();
            else
                s1 = (new StringBuilder()).append(s2).append("}").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(", ").append(s1).toString();
            i++;
        } while(true);
        if(s == null)
            s = "";
        return s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(mData);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DnsSdTxtRecord createFromParcel(Parcel parcel)
        {
            DnsSdTxtRecord dnssdtxtrecord = new DnsSdTxtRecord();
            parcel.readByteArray(DnsSdTxtRecord._2D_get0(dnssdtxtrecord));
            return dnssdtxtrecord;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DnsSdTxtRecord[] newArray(int i)
        {
            return new DnsSdTxtRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final byte mSeperator = 61;
    private byte mData[];

}
