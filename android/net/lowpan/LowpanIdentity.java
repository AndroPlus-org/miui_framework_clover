// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.icu.text.StringPrep;
import android.icu.text.StringPrepParseException;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.android.internal.util.HexDump;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class LowpanIdentity
    implements Parcelable
{
    public static class Builder
    {

        private static String escape(byte abyte0[])
        {
            StringBuffer stringbuffer = new StringBuffer();
            int i = abyte0.length;
            int j = 0;
            while(j < i) 
            {
                byte byte0 = abyte0[j];
                if(byte0 >= 32 && byte0 <= 126)
                    stringbuffer.append((char)byte0);
                else
                    stringbuffer.append(String.format("\\0x%02x", new Object[] {
                        Integer.valueOf(byte0 & 0xff)
                    }));
                j++;
            }
            return stringbuffer.toString();
        }

        public LowpanIdentity build()
        {
            return mIdentity;
        }

        public Builder setChannel(int i)
        {
            LowpanIdentity._2D_set0(mIdentity, i);
            return this;
        }

        public Builder setLowpanIdentity(LowpanIdentity lowpanidentity)
        {
            Objects.requireNonNull(lowpanidentity);
            setRawName(lowpanidentity.getRawName());
            setXpanid(lowpanidentity.getXpanid());
            setPanid(lowpanidentity.getPanid());
            setChannel(lowpanidentity.getChannel());
            setType(lowpanidentity.getType());
            return this;
        }

        public Builder setName(String s)
        {
            Objects.requireNonNull(s);
            try
            {
                LowpanIdentity._2D_set2(mIdentity, stringPrep.prepare(s, 0));
                LowpanIdentity._2D_set4(mIdentity, LowpanIdentity._2D_get2(mIdentity).getBytes(StandardCharsets.UTF_8));
                LowpanIdentity._2D_set1(mIdentity, true);
            }
            catch(StringPrepParseException stringprepparseexception)
            {
                Log.w(LowpanIdentity._2D_get0(), stringprepparseexception.toString());
                setRawName(s.getBytes(StandardCharsets.UTF_8));
            }
            return this;
        }

        public Builder setPanid(int i)
        {
            LowpanIdentity._2D_set3(mIdentity, i);
            return this;
        }

        public Builder setRawName(byte abyte0[])
        {
            Objects.requireNonNull(abyte0);
            LowpanIdentity._2D_set4(mIdentity, (byte[])abyte0.clone());
            LowpanIdentity._2D_set2(mIdentity, new String(abyte0, StandardCharsets.UTF_8));
            try
            {
                String s = stringPrep.prepare(LowpanIdentity._2D_get2(mIdentity), 0);
                LowpanIdentity._2D_set1(mIdentity, Arrays.equals(s.getBytes(StandardCharsets.UTF_8), abyte0));
            }
            catch(StringPrepParseException stringprepparseexception)
            {
                Log.w(LowpanIdentity._2D_get0(), stringprepparseexception.toString());
                LowpanIdentity._2D_set1(mIdentity, false);
            }
            if(!LowpanIdentity._2D_get1(mIdentity))
                LowpanIdentity._2D_set2(mIdentity, (new StringBuilder()).append("\253").append(escape(abyte0)).append("\273").toString());
            return this;
        }

        public Builder setType(String s)
        {
            LowpanIdentity._2D_set5(mIdentity, s);
            return this;
        }

        public Builder setXpanid(byte abyte0[])
        {
            byte abyte1[] = null;
            LowpanIdentity lowpanidentity = mIdentity;
            if(abyte0 != null)
                abyte1 = (byte[])abyte0.clone();
            LowpanIdentity._2D_set6(lowpanidentity, abyte1);
            return this;
        }

        private static final StringPrep stringPrep = StringPrep.getInstance(8);
        final LowpanIdentity mIdentity = new LowpanIdentity();


        public Builder()
        {
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static boolean _2D_get1(LowpanIdentity lowpanidentity)
    {
        return lowpanidentity.mIsNameValid;
    }

    static String _2D_get2(LowpanIdentity lowpanidentity)
    {
        return lowpanidentity.mName;
    }

    static int _2D_set0(LowpanIdentity lowpanidentity, int i)
    {
        lowpanidentity.mChannel = i;
        return i;
    }

    static boolean _2D_set1(LowpanIdentity lowpanidentity, boolean flag)
    {
        lowpanidentity.mIsNameValid = flag;
        return flag;
    }

    static String _2D_set2(LowpanIdentity lowpanidentity, String s)
    {
        lowpanidentity.mName = s;
        return s;
    }

    static int _2D_set3(LowpanIdentity lowpanidentity, int i)
    {
        lowpanidentity.mPanid = i;
        return i;
    }

    static byte[] _2D_set4(LowpanIdentity lowpanidentity, byte abyte0[])
    {
        lowpanidentity.mRawName = abyte0;
        return abyte0;
    }

    static String _2D_set5(LowpanIdentity lowpanidentity, String s)
    {
        lowpanidentity.mType = s;
        return s;
    }

    static byte[] _2D_set6(LowpanIdentity lowpanidentity, byte abyte0[])
    {
        lowpanidentity.mXpanid = abyte0;
        return abyte0;
    }

    LowpanIdentity()
    {
        mName = "";
        mIsNameValid = true;
        mRawName = new byte[0];
        mType = "";
        mXpanid = new byte[0];
        mPanid = -1;
        mChannel = -1;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof LowpanIdentity))
            return false;
        obj = (LowpanIdentity)obj;
        boolean flag1 = flag;
        if(Arrays.equals(mRawName, ((LowpanIdentity) (obj)).mRawName))
        {
            flag1 = flag;
            if(Arrays.equals(mXpanid, ((LowpanIdentity) (obj)).mXpanid))
            {
                flag1 = flag;
                if(mType.equals(((LowpanIdentity) (obj)).mType))
                {
                    flag1 = flag;
                    if(mPanid == ((LowpanIdentity) (obj)).mPanid)
                    {
                        flag1 = flag;
                        if(mChannel == ((LowpanIdentity) (obj)).mChannel)
                            flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public int getChannel()
    {
        return mChannel;
    }

    public String getName()
    {
        return mName;
    }

    public int getPanid()
    {
        return mPanid;
    }

    public byte[] getRawName()
    {
        return (byte[])mRawName.clone();
    }

    public String getType()
    {
        return mType;
    }

    public byte[] getXpanid()
    {
        return (byte[])mXpanid.clone();
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(Arrays.hashCode(mRawName)), mType, Integer.valueOf(Arrays.hashCode(mXpanid)), Integer.valueOf(mPanid), Integer.valueOf(mChannel)
        });
    }

    public boolean isNameValid()
    {
        return mIsNameValid;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("Name:").append(getName());
        if(mType.length() > 0)
            stringbuffer.append(", Type:").append(mType);
        if(mXpanid.length > 0)
            stringbuffer.append(", XPANID:").append(HexDump.toHexString(mXpanid));
        if(mPanid != -1)
            stringbuffer.append(", PANID:").append(String.format("0x%04X", new Object[] {
                Integer.valueOf(mPanid)
            }));
        if(mChannel != -1)
            stringbuffer.append(", Channel:").append(mChannel);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(mRawName);
        parcel.writeString(mType);
        parcel.writeByteArray(mXpanid);
        parcel.writeInt(mPanid);
        parcel.writeInt(mChannel);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LowpanIdentity createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder();
            builder.setRawName(parcel.createByteArray());
            builder.setType(parcel.readString());
            builder.setXpanid(parcel.createByteArray());
            builder.setPanid(parcel.readInt());
            builder.setChannel(parcel.readInt());
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LowpanIdentity[] newArray(int i)
        {
            return new LowpanIdentity[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = android/net/lowpan/LowpanIdentity.getSimpleName();
    public static final int UNSPECIFIED_CHANNEL = -1;
    public static final int UNSPECIFIED_PANID = -1;
    private int mChannel;
    private boolean mIsNameValid;
    private String mName;
    private int mPanid;
    private byte mRawName[];
    private String mType;
    private byte mXpanid[];

}
