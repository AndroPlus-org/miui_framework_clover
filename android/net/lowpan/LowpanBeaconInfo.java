// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.HexDump;
import java.util.*;

// Referenced classes of package android.net.lowpan:
//            LowpanIdentity

public class LowpanBeaconInfo
    implements Parcelable
{
    public static class Builder
    {

        public LowpanBeaconInfo build()
        {
            LowpanBeaconInfo._2D_set1(mBeaconInfo, mIdentityBuilder.build());
            if(LowpanBeaconInfo._2D_get0(mBeaconInfo) == null)
                LowpanBeaconInfo._2D_set0(mBeaconInfo, new byte[0]);
            return mBeaconInfo;
        }

        public Builder setBeaconAddress(byte abyte0[])
        {
            byte abyte1[] = null;
            LowpanBeaconInfo lowpanbeaconinfo = mBeaconInfo;
            if(abyte0 != null)
                abyte1 = (byte[])abyte0.clone();
            LowpanBeaconInfo._2D_set0(lowpanbeaconinfo, abyte1);
            return this;
        }

        public Builder setChannel(int i)
        {
            mIdentityBuilder.setChannel(i);
            return this;
        }

        public Builder setFlag(int i)
        {
            LowpanBeaconInfo._2D_get1(mBeaconInfo).add(Integer.valueOf(i));
            return this;
        }

        public Builder setFlags(Collection collection)
        {
            LowpanBeaconInfo._2D_get1(mBeaconInfo).addAll(collection);
            return this;
        }

        public Builder setLowpanIdentity(LowpanIdentity lowpanidentity)
        {
            mIdentityBuilder.setLowpanIdentity(lowpanidentity);
            return this;
        }

        public Builder setLqi(int i)
        {
            LowpanBeaconInfo._2D_set2(mBeaconInfo, i);
            return this;
        }

        public Builder setName(String s)
        {
            mIdentityBuilder.setName(s);
            return this;
        }

        public Builder setPanid(int i)
        {
            mIdentityBuilder.setPanid(i);
            return this;
        }

        public Builder setRssi(int i)
        {
            LowpanBeaconInfo._2D_set3(mBeaconInfo, i);
            return this;
        }

        public Builder setType(String s)
        {
            mIdentityBuilder.setType(s);
            return this;
        }

        public Builder setXpanid(byte abyte0[])
        {
            mIdentityBuilder.setXpanid(abyte0);
            return this;
        }

        final LowpanBeaconInfo mBeaconInfo = new LowpanBeaconInfo(null);
        final LowpanIdentity.Builder mIdentityBuilder = new LowpanIdentity.Builder();

        public Builder()
        {
        }
    }


    static byte[] _2D_get0(LowpanBeaconInfo lowpanbeaconinfo)
    {
        return lowpanbeaconinfo.mBeaconAddress;
    }

    static TreeSet _2D_get1(LowpanBeaconInfo lowpanbeaconinfo)
    {
        return lowpanbeaconinfo.mFlags;
    }

    static byte[] _2D_set0(LowpanBeaconInfo lowpanbeaconinfo, byte abyte0[])
    {
        lowpanbeaconinfo.mBeaconAddress = abyte0;
        return abyte0;
    }

    static LowpanIdentity _2D_set1(LowpanBeaconInfo lowpanbeaconinfo, LowpanIdentity lowpanidentity)
    {
        lowpanbeaconinfo.mIdentity = lowpanidentity;
        return lowpanidentity;
    }

    static int _2D_set2(LowpanBeaconInfo lowpanbeaconinfo, int i)
    {
        lowpanbeaconinfo.mLqi = i;
        return i;
    }

    static int _2D_set3(LowpanBeaconInfo lowpanbeaconinfo, int i)
    {
        lowpanbeaconinfo.mRssi = i;
        return i;
    }

    private LowpanBeaconInfo()
    {
        mRssi = 0x7fffffff;
        mLqi = 0;
        mBeaconAddress = null;
        mFlags = new TreeSet();
    }

    LowpanBeaconInfo(LowpanBeaconInfo lowpanbeaconinfo)
    {
        this();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof LowpanBeaconInfo))
            return false;
        obj = (LowpanBeaconInfo)obj;
        boolean flag1 = flag;
        if(mIdentity.equals(((LowpanBeaconInfo) (obj)).mIdentity))
        {
            flag1 = flag;
            if(Arrays.equals(mBeaconAddress, ((LowpanBeaconInfo) (obj)).mBeaconAddress))
            {
                flag1 = flag;
                if(mRssi == ((LowpanBeaconInfo) (obj)).mRssi)
                {
                    flag1 = flag;
                    if(mLqi == ((LowpanBeaconInfo) (obj)).mLqi)
                        flag1 = mFlags.equals(((LowpanBeaconInfo) (obj)).mFlags);
                }
            }
        }
        return flag1;
    }

    public byte[] getBeaconAddress()
    {
        return (byte[])mBeaconAddress.clone();
    }

    public Collection getFlags()
    {
        return (Collection)mFlags.clone();
    }

    public LowpanIdentity getLowpanIdentity()
    {
        return mIdentity;
    }

    public int getLqi()
    {
        return mLqi;
    }

    public int getRssi()
    {
        return mRssi;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mIdentity, Integer.valueOf(mRssi), Integer.valueOf(mLqi), Integer.valueOf(Arrays.hashCode(mBeaconAddress)), mFlags
        });
    }

    public boolean isFlagSet(int i)
    {
        return mFlags.contains(Integer.valueOf(i));
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(mIdentity.toString());
        if(mRssi != 0x7fffffff)
            stringbuffer.append(", RSSI:").append(mRssi).append("dBm");
        if(mLqi != 0)
            stringbuffer.append(", LQI:").append(mLqi);
        if(mBeaconAddress.length > 0)
            stringbuffer.append(", BeaconAddress:").append(HexDump.toHexString(mBeaconAddress));
        Iterator iterator = mFlags.iterator();
        do
            if(iterator.hasNext())
            {
                Integer integer = (Integer)iterator.next();
                switch(integer.intValue())
                {
                default:
                    stringbuffer.append(", FLAG_").append(Integer.toHexString(integer.intValue()));
                    break;

                case 1: // '\001'
                    stringbuffer.append(", CAN_ASSIST");
                    break;
                }
            } else
            {
                return stringbuffer.toString();
            }
        while(true);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mIdentity.writeToParcel(parcel, i);
        parcel.writeInt(mRssi);
        parcel.writeInt(mLqi);
        parcel.writeByteArray(mBeaconAddress);
        parcel.writeInt(mFlags.size());
        for(Iterator iterator = mFlags.iterator(); iterator.hasNext(); parcel.writeInt(((Integer)iterator.next()).intValue()));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LowpanBeaconInfo createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder();
            builder.setLowpanIdentity((LowpanIdentity)LowpanIdentity.CREATOR.createFromParcel(parcel));
            builder.setRssi(parcel.readInt());
            builder.setLqi(parcel.readInt());
            builder.setBeaconAddress(parcel.createByteArray());
            for(int i = parcel.readInt(); i > 0; i--)
                builder.setFlag(parcel.readInt());

            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LowpanBeaconInfo[] newArray(int i)
        {
            return new LowpanBeaconInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_CAN_ASSIST = 1;
    public static final int UNKNOWN_LQI = 0;
    public static final int UNKNOWN_RSSI = 0x7fffffff;
    private byte mBeaconAddress[];
    private final TreeSet mFlags;
    private LowpanIdentity mIdentity;
    private int mLqi;
    private int mRssi;

}
