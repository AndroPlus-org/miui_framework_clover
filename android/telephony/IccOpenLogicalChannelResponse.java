// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

public class IccOpenLogicalChannelResponse
    implements Parcelable
{

    public IccOpenLogicalChannelResponse(int i, int j, byte abyte0[])
    {
        mChannel = i;
        mStatus = j;
        mSelectResponse = abyte0;
    }

    private IccOpenLogicalChannelResponse(Parcel parcel)
    {
        mChannel = parcel.readInt();
        mStatus = parcel.readInt();
        int i = parcel.readInt();
        if(i > 0)
        {
            mSelectResponse = new byte[i];
            parcel.readByteArray(mSelectResponse);
        } else
        {
            mSelectResponse = null;
        }
    }

    IccOpenLogicalChannelResponse(Parcel parcel, IccOpenLogicalChannelResponse iccopenlogicalchannelresponse)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getChannel()
    {
        return mChannel;
    }

    public byte[] getSelectResponse()
    {
        return mSelectResponse;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Channel: ").append(mChannel).append(" Status: ").append(mStatus).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mChannel);
        parcel.writeInt(mStatus);
        if(mSelectResponse != null && mSelectResponse.length > 0)
        {
            parcel.writeInt(mSelectResponse.length);
            parcel.writeByteArray(mSelectResponse);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IccOpenLogicalChannelResponse createFromParcel(Parcel parcel)
        {
            return new IccOpenLogicalChannelResponse(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IccOpenLogicalChannelResponse[] newArray(int i)
        {
            return new IccOpenLogicalChannelResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int INVALID_CHANNEL = -1;
    public static final int STATUS_MISSING_RESOURCE = 2;
    public static final int STATUS_NO_ERROR = 1;
    public static final int STATUS_NO_SUCH_ELEMENT = 3;
    public static final int STATUS_UNKNOWN_ERROR = 4;
    private final int mChannel;
    private final byte mSelectResponse[];
    private final int mStatus;

}
