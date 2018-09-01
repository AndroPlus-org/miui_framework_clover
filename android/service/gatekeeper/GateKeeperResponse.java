// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.gatekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class GateKeeperResponse
    implements Parcelable
{

    static GateKeeperResponse _2D_wrap0(int i)
    {
        return createRetryResponse(i);
    }

    private GateKeeperResponse(int i)
    {
        mResponseCode = i;
    }

    public static GateKeeperResponse createGenericResponse(int i)
    {
        return new GateKeeperResponse(i);
    }

    public static GateKeeperResponse createOkResponse(byte abyte0[], boolean flag)
    {
        GateKeeperResponse gatekeeperresponse = new GateKeeperResponse(0);
        gatekeeperresponse.mPayload = abyte0;
        gatekeeperresponse.mShouldReEnroll = flag;
        return gatekeeperresponse;
    }

    private static GateKeeperResponse createRetryResponse(int i)
    {
        GateKeeperResponse gatekeeperresponse = new GateKeeperResponse(1);
        gatekeeperresponse.mTimeout = i;
        return gatekeeperresponse;
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getPayload()
    {
        return mPayload;
    }

    public int getResponseCode()
    {
        return mResponseCode;
    }

    public boolean getShouldReEnroll()
    {
        return mShouldReEnroll;
    }

    public int getTimeout()
    {
        return mTimeout;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(mResponseCode);
        if(mResponseCode != 1) goto _L2; else goto _L1
_L1:
        parcel.writeInt(mTimeout);
_L4:
        return;
_L2:
        if(mResponseCode == 0)
        {
            if(!mShouldReEnroll)
                i = 0;
            parcel.writeInt(i);
            if(mPayload != null)
            {
                parcel.writeInt(mPayload.length);
                parcel.writeByteArray(mPayload);
            } else
            {
                parcel.writeInt(0);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GateKeeperResponse createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == 1)
                parcel = GateKeeperResponse._2D_wrap0(parcel.readInt());
            else
            if(i == 0)
            {
                boolean flag;
                byte abyte0[];
                if(parcel.readInt() == 1)
                    flag = true;
                else
                    flag = false;
                abyte0 = null;
                i = parcel.readInt();
                if(i > 0)
                {
                    abyte0 = new byte[i];
                    parcel.readByteArray(abyte0);
                }
                parcel = GateKeeperResponse.createOkResponse(abyte0, flag);
            } else
            {
                parcel = GateKeeperResponse.createGenericResponse(i);
            }
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GateKeeperResponse[] newArray(int i)
        {
            return new GateKeeperResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int RESPONSE_ERROR = -1;
    public static final int RESPONSE_OK = 0;
    public static final int RESPONSE_RETRY = 1;
    private byte mPayload[];
    private final int mResponseCode;
    private boolean mShouldReEnroll;
    private int mTimeout;

}
