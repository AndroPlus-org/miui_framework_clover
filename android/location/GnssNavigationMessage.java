// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;

public final class GnssNavigationMessage
    implements Parcelable
{
    public static abstract class Callback
    {

        public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssnavigationmessage)
        {
        }

        public void onStatusChanged(int i)
        {
        }

        public static final int STATUS_LOCATION_DISABLED = 2;
        public static final int STATUS_NOT_SUPPORTED = 0;
        public static final int STATUS_READY = 1;

        public Callback()
        {
        }
    }


    public GnssNavigationMessage()
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

        case 257: 
            return "GPS L1 C/A";

        case 258: 
            return "GPS L2-CNAV";

        case 259: 
            return "GPS L5-CNAV";

        case 260: 
            return "GPS CNAV2";

        case 769: 
            return "Glonass L1 C/A";

        case 1281: 
            return "Beidou D1";

        case 1282: 
            return "Beidou D2";

        case 1537: 
            return "Galileo I";

        case 1538: 
            return "Galileo F";
        }
    }

    private void initialize()
    {
        mType = 0;
        mSvid = 0;
        mMessageId = -1;
        mSubmessageId = -1;
        mData = EMPTY_ARRAY;
        mStatus = 0;
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getData()
    {
        return mData;
    }

    public int getMessageId()
    {
        return mMessageId;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public int getSubmessageId()
    {
        return mSubmessageId;
    }

    public int getSvid()
    {
        return mSvid;
    }

    public int getType()
    {
        return mType;
    }

    public void reset()
    {
        initialize();
    }

    public void set(GnssNavigationMessage gnssnavigationmessage)
    {
        mType = gnssnavigationmessage.mType;
        mSvid = gnssnavigationmessage.mSvid;
        mMessageId = gnssnavigationmessage.mMessageId;
        mSubmessageId = gnssnavigationmessage.mSubmessageId;
        mData = gnssnavigationmessage.mData;
        mStatus = gnssnavigationmessage.mStatus;
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

    public void setMessageId(int i)
    {
        mMessageId = i;
    }

    public void setStatus(int i)
    {
        mStatus = i;
    }

    public void setSubmessageId(int i)
    {
        mSubmessageId = i;
    }

    public void setSvid(int i)
    {
        mSvid = i;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public String toString()
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder("GnssNavigationMessage:\n");
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Type", getTypeString()
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Svid", Integer.valueOf(mSvid)
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "Status", getStatusString()
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "MessageId", Integer.valueOf(mMessageId)
        }));
        stringbuilder.append(String.format("   %-15s = %s\n", new Object[] {
            "SubmessageId", Integer.valueOf(mSubmessageId)
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
        parcel.writeInt(mType);
        parcel.writeInt(mSvid);
        parcel.writeInt(mMessageId);
        parcel.writeInt(mSubmessageId);
        parcel.writeInt(mData.length);
        parcel.writeByteArray(mData);
        parcel.writeInt(mStatus);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GnssNavigationMessage createFromParcel(Parcel parcel)
        {
            GnssNavigationMessage gnssnavigationmessage = new GnssNavigationMessage();
            gnssnavigationmessage.setType(parcel.readInt());
            gnssnavigationmessage.setSvid(parcel.readInt());
            gnssnavigationmessage.setMessageId(parcel.readInt());
            gnssnavigationmessage.setSubmessageId(parcel.readInt());
            byte abyte0[] = new byte[parcel.readInt()];
            parcel.readByteArray(abyte0);
            gnssnavigationmessage.setData(abyte0);
            gnssnavigationmessage.setStatus(parcel.readInt());
            return gnssnavigationmessage;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GnssNavigationMessage[] newArray(int i)
        {
            return new GnssNavigationMessage[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final byte EMPTY_ARRAY[] = new byte[0];
    public static final int STATUS_PARITY_PASSED = 1;
    public static final int STATUS_PARITY_REBUILT = 2;
    public static final int STATUS_UNKNOWN = 0;
    public static final int TYPE_BDS_D1 = 1281;
    public static final int TYPE_BDS_D2 = 1282;
    public static final int TYPE_GAL_F = 1538;
    public static final int TYPE_GAL_I = 1537;
    public static final int TYPE_GLO_L1CA = 769;
    public static final int TYPE_GPS_CNAV2 = 260;
    public static final int TYPE_GPS_L1CA = 257;
    public static final int TYPE_GPS_L2CNAV = 258;
    public static final int TYPE_GPS_L5CNAV = 259;
    public static final int TYPE_UNKNOWN = 0;
    private byte mData[];
    private int mMessageId;
    private int mStatus;
    private int mSubmessageId;
    private int mSvid;
    private int mType;

}
