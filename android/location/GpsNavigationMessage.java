// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;

public class GpsNavigationMessage
    implements Parcelable
{

    GpsNavigationMessage()
    {
        initialize();
    }

    private String getStatusString()
    {
        switch(mStatus)
        {
        default:
            return (new StringBuilder()).append("<Invalid:").append(mStatus).append(">").toString();

        case 0: // '\0'
            return "Unknown";

        case 1: // '\001'
            return "ParityPassed";

        case 2: // '\002'
            return "ParityRebuilt";
        }
    }

    private String getTypeString()
    {
        switch(mType)
        {
        default:
            return (new StringBuilder()).append("<Invalid:").append(mType).append(">").toString();

        case 0: // '\0'
            return "Unknown";

        case 1: // '\001'
            return "L1 C/A";

        case 2: // '\002'
            return "L2-CNAV";

        case 3: // '\003'
            return "L5-CNAV";

        case 4: // '\004'
            return "CNAV-2";
        }
    }

    private void initialize()
    {
        mType = (byte)0;
        mPrn = (byte)0;
        mMessageId = (short)-1;
        mSubmessageId = (short)-1;
        mData = EMPTY_ARRAY;
        mStatus = (short)0;
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getData()
    {
        return mData;
    }

    public short getMessageId()
    {
        return mMessageId;
    }

    public byte getPrn()
    {
        return mPrn;
    }

    public short getStatus()
    {
        return mStatus;
    }

    public short getSubmessageId()
    {
        return mSubmessageId;
    }

    public byte getType()
    {
        return mType;
    }

    public void reset()
    {
        initialize();
    }

    public void set(GpsNavigationMessage gpsnavigationmessage)
    {
        mType = gpsnavigationmessage.mType;
        mPrn = gpsnavigationmessage.mPrn;
        mMessageId = gpsnavigationmessage.mMessageId;
        mSubmessageId = gpsnavigationmessage.mSubmessageId;
        mData = gpsnavigationmessage.mData;
        mStatus = gpsnavigationmessage.mStatus;
    }

    public void setData(byte abyte0[])
    {
        if(abyte0 == null)
        {
            throw new InvalidParameterException("Data must be a non-null array");
        } else
        {
            mData = abyte0;
            return;
        }
    }

    public void setMessageId(short word0)
    {
        mMessageId = word0;
    }

    public void setPrn(byte byte0)
    {
        mPrn = byte0;
    }

    public void setStatus(short word0)
    {
        mStatus = word0;
    }

    public void setSubmessageId(short word0)
    {
        mSubmessageId = word0;
    }

    public void setType(byte byte0)
    {
        mType = byte0;
    }

    public String toString()
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder("GpsNavigationMessage:\n");
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Type", getTypeString()
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Prn", Byte.valueOf(mPrn)
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Status", getStatusString()
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "MessageId", Short.valueOf(mMessageId)
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "SubmessageId", Short.valueOf(mSubmessageId)
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Data", "{"
        }));
        String s = "        ";
        byte abyte0[] = mData;
        for(int j = abyte0.length; i < j; i++)
        {
            byte byte0 = abyte0[i];
            stringbuilder.append(s);
            stringbuilder.append(byte0);
            s = ", ";
        }

        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByte(mType);
        parcel.writeByte(mPrn);
        parcel.writeInt(mMessageId);
        parcel.writeInt(mSubmessageId);
        parcel.writeInt(mData.length);
        parcel.writeByteArray(mData);
        parcel.writeInt(mStatus);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GpsNavigationMessage createFromParcel(Parcel parcel)
        {
            GpsNavigationMessage gpsnavigationmessage = new GpsNavigationMessage();
            gpsnavigationmessage.setType(parcel.readByte());
            gpsnavigationmessage.setPrn(parcel.readByte());
            gpsnavigationmessage.setMessageId((short)parcel.readInt());
            gpsnavigationmessage.setSubmessageId((short)parcel.readInt());
            byte abyte0[] = new byte[parcel.readInt()];
            parcel.readByteArray(abyte0);
            gpsnavigationmessage.setData(abyte0);
            if(parcel.dataAvail() >= 32)
                gpsnavigationmessage.setStatus((short)parcel.readInt());
            else
                gpsnavigationmessage.setStatus((short)0);
            return gpsnavigationmessage;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GpsNavigationMessage[] newArray(int i)
        {
            return new GpsNavigationMessage[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final byte EMPTY_ARRAY[] = new byte[0];
    public static final short STATUS_PARITY_PASSED = 1;
    public static final short STATUS_PARITY_REBUILT = 2;
    public static final short STATUS_UNKNOWN = 0;
    public static final byte TYPE_CNAV2 = 4;
    public static final byte TYPE_L1CA = 1;
    public static final byte TYPE_L2CNAV = 2;
    public static final byte TYPE_L5CNAV = 3;
    public static final byte TYPE_UNKNOWN = 0;
    private byte mData[];
    private short mMessageId;
    private byte mPrn;
    private short mStatus;
    private short mSubmessageId;
    private byte mType;

}
